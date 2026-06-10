/**
 * NSTextInputClient implementation for AppKit IME support.
 *
 * Creates an ObjC NSView subclass (KadreTextInputView) that conforms to the
 * NSTextInputClient informal protocol. When the IME calls back (insertText:,
 * setMarkedText:, unmarkText), the callbacks dispatch [WindowEvent.Ime] events
 * to the application handler.
 *
 * Uses a global ConcurrentHashMap (view address -> ImeViewRecord) to route
 * ObjC callbacks to the correct Kotlin handler, following the same pattern
 * as [KadreWindowDelegate].
 *
 * R5-IME: AppKit backend.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.ffi.objc.ObjCRuntime
import org.graphiks.kadre.ffi.objc.ObjCSubclassing
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap

/**
 * Per-view record stored in the IME callbacks table.
 * Holds the handler, event loop, window id, and the IME cursor rect
 * (in screen points, Cocoa bottom-left origin).
 */
internal class ImeViewRecord(
    val handler: ApplicationHandler,
    val eventLoop: ActiveEventLoop,
    val windowId: WindowId,
    /** Cursor rect in screen coordinates (points, bottom-left origin). Updated by [AppKitWindow.setImeCursorArea]. */
    @Volatile
    var imeCursorScreenRect: MemorySegment,
)

/**
 * Manages the ObjC NSView subclass (`KadreTextInputView`) that implements
 * the NSTextInputClient informal protocol.
 */
internal object AppKitImeTextInputClient {

    /** Global table: ObjC NSView address -> ImeViewRecord. */
    private val imeViewTable = ConcurrentHashMap<Long, ImeViewRecord>()

    /** NSNotFound constant for range location. */
    private const val NS_NOT_FOUND = Long.MAX_VALUE

    /** MemoryLayout for NSRange = {unsigned long location, unsigned long length}. */
    internal val NS_RANGE_LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG.withName("location"),
        ValueLayout.JAVA_LONG.withName("length"),
    ).withName("_NSRange")

    private val NS_RECT_LAYOUT_SRET: java.lang.foreign.GroupLayout = MemoryLayout.structLayout(
        MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("x"),
            ValueLayout.JAVA_DOUBLE.withName("y"),
        ).withName("origin"),
        MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("width"),
            ValueLayout.JAVA_DOUBLE.withName("height"),
        ).withName("size"),
    ).withName("CGRect")

    /** CGPoint layout for draggingLocation struct return. */
    private val CG_POINT_LAYOUT: java.lang.foreign.GroupLayout = MemoryLayout.structLayout(
        ValueLayout.JAVA_DOUBLE.withName("x"),
        ValueLayout.JAVA_DOUBLE.withName("y"),
    ).withName("CGPoint")

    /**
     * Registers a view in the IME table.
     * The ObjC class must be registered before creating instances.
     */
    fun registerView(viewPtr: MemorySegment, record: ImeViewRecord) {
        imeViewTable[viewPtr.address()] = record
    }

    /**
     * Removes a view from the IME table.
     */
    fun unregisterView(viewPtr: MemorySegment) {
        imeViewTable.remove(viewPtr.address())
    }

    /**
     * Updates the cursor screen rect for a given view.
     */
    fun updateCursorRect(viewPtr: MemorySegment, screenRect: MemorySegment) {
        imeViewTable[viewPtr.address()]?.imeCursorScreenRect = screenRect
    }

    /**
     * Ensures the KadreTextInputView ObjC class is registered.
     * Idempotent — safe to call multiple times.
     */
    fun ensureClassRegistered() {
        if (classRegistered) return

        val arena = Arena.global()
        val linker: Linker = Linker.nativeLinker()
        val lookup = MethodHandles.lookup()

        val cls = ObjCSubclassing.allocateClass("NSView", "KadreTextInputView")

        // ── acceptsFirstResponder → YES ──────────────────────────────────────
        val acceptsHandle = lookup.findStatic(
            Callbacks::class.java,
            "acceptsFirstResponder",
            MethodType.methodType(java.lang.Byte.TYPE, MemorySegment::class.java, MemorySegment::class.java),
        )
        val acceptsStub = linker.upcallStub(
            acceptsHandle,
            FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "acceptsFirstResponder", acceptsStub, "c@:")

        // ── insertText:replacementRange: ─────────────────────────────────────
        // Called when the IME commits text.
        // Encoding: v@:@{_NSRange=QQ}
        val insertTextHandle = lookup.findStatic(
            Callbacks::class.java,
            "insertText_replacementRange",
            MethodType.methodType(
                java.lang.Void.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val insertTextStub = linker.upcallStub(
            insertTextHandle,
            FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                NS_RANGE_LAYOUT,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "insertText:replacementRange:", insertTextStub, "v@:@{_NSRange=QQ}")

        // ── setMarkedText:selectedRange:replacementRange: ────────────────────
        // Called when the IME updates the preedit string.
        // Encoding: v@:@{_NSRange=QQ}{_NSRange=QQ}
        val setMarkedHandle = lookup.findStatic(
            Callbacks::class.java,
            "setMarkedText_selectedRange_replacementRange",
            MethodType.methodType(
                java.lang.Void.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val setMarkedStub = linker.upcallStub(
            setMarkedHandle,
            FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                NS_RANGE_LAYOUT,
                NS_RANGE_LAYOUT,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "setMarkedText:selectedRange:replacementRange:", setMarkedStub, "v@:@{_NSRange=QQ}{_NSRange=QQ}")

        // ── hasMarkedText ─────────────────────────────────────────────────────
        // Returns whether the view has marked text.
        // Encoding: c@:
        val hasMarkedTextHandle = lookup.findStatic(
            Callbacks::class.java,
            "hasMarkedText",
            MethodType.methodType(java.lang.Byte.TYPE, MemorySegment::class.java, MemorySegment::class.java),
        )
        val hasMarkedTextStub = linker.upcallStub(
            hasMarkedTextHandle,
            FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "hasMarkedText", hasMarkedTextStub, "c@:")

        // ── markedRange ───────────────────────────────────────────────────────
        // Returns the current marked range.
        // Encoding: {_NSRange=QQ}@:
        val markedRangeHandle = lookup.findStatic(
            Callbacks::class.java,
            "markedRange",
            MethodType.methodType(
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val markedRangeStub = linker.upcallStub(
            markedRangeHandle,
            FunctionDescriptor.of(
                NS_RANGE_LAYOUT,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "markedRange", markedRangeStub, "{_NSRange=QQ}@:")

        // ── selectedRange ─────────────────────────────────────────────────────
        // Returns the current selection range.
        // Encoding: {_NSRange=QQ}@:
        val selectedRangeHandle = lookup.findStatic(
            Callbacks::class.java,
            "selectedRange",
            MethodType.methodType(
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val selectedRangeStub = linker.upcallStub(
            selectedRangeHandle,
            FunctionDescriptor.of(
                NS_RANGE_LAYOUT,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "selectedRange", selectedRangeStub, "{_NSRange=QQ}@:")

        // ── unmarkText ────────────────────────────────────────────────────────
        // Called when the IME cancels the preedit session.
        // Encoding: v@:
        val unmarkTextHandle = lookup.findStatic(
            Callbacks::class.java,
            "unmarkText",
            MethodType.methodType(
                java.lang.Void.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val unmarkTextStub = linker.upcallStub(
            unmarkTextHandle,
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "unmarkText", unmarkTextStub, "v@:")

        // ── firstRectForCharacterRange:actualRange: ───────────────────────────
        // Returns the cursor rect in screen coordinates for the given character range.
        // Encoding: {CGRect={CGPoint=dd}{CGSize=dd}}@:{_NSRange=QQ}^{_NSRange=QQ}
        // sret: first parameter is return buffer pointer (ADDRESS before self)
        val firstRectHandle = lookup.findStatic(
            Callbacks::class.java,
            "firstRectForCharacterRange_actualRange",
            MethodType.methodType(
                java.lang.Void.TYPE, // sret — writes to return buffer
                MemorySegment::class.java, // return buffer (CGRect*)
                MemorySegment::class.java, // self
                MemorySegment::class.java, // cmd
                MemorySegment::class.java, // characterRange (NSRange)
                MemorySegment::class.java, // actualRange (NSRange*)
            ),
        )
        val firstRectStub = linker.upcallStub(
            firstRectHandle,
            FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS, // sret buffer for CGRect
                ValueLayout.ADDRESS, // self
                ValueLayout.ADDRESS, // cmd
                NS_RANGE_LAYOUT, // characterRange
                ValueLayout.ADDRESS, // actualRange (pointer)
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "firstRectForCharacterRange:actualRange:", firstRectStub, "{CGRect={CGPoint=dd}{CGSize=dd}}@:{_NSRange=QQ}^{_NSRange=QQ}")

        // ── characterIndexForPoint: ───────────────────────────────────────────
        // Returns the character index closest to the given point.
        // Encoding: Q@:{CGPoint=dd}
        val charIndexHandle = lookup.findStatic(
            Callbacks::class.java,
            "characterIndexForPoint",
            MethodType.methodType(
                java.lang.Long.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val charIndexStub = linker.upcallStub(
            charIndexHandle,
            FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                MemoryLayout.structLayout(
                    ValueLayout.JAVA_DOUBLE.withName("x"),
                    ValueLayout.JAVA_DOUBLE.withName("y"),
                ).withName("CGPoint"),
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "characterIndexForPoint:", charIndexStub, "Q@:{CGPoint=dd}")

        // ── validAttributesForMarkedText ──────────────────────────────────────
        // Returns the array of attribute names supported for attributed strings.
        // Encoding: @@:
        val validAttributesHandle = lookup.findStatic(
            Callbacks::class.java,
            "validAttributesForMarkedText",
            MethodType.methodType(
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val validAttributesStub = linker.upcallStub(
            validAttributesHandle,
            FunctionDescriptor.of(
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "validAttributesForMarkedText", validAttributesStub, "@@:")

        // ── attributedSubstringForProposedRange:actualRange: ──────────────────
        // Returns an attributed string for the given range.
        // Encoding: @@:{_NSRange=QQ}^{_NSRange=QQ}
        val attributedSubstringHandle = lookup.findStatic(
            Callbacks::class.java,
            "attributedSubstringForProposedRange_actualRange",
            MethodType.methodType(
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val attributedSubstringStub = linker.upcallStub(
            attributedSubstringHandle,
            FunctionDescriptor.of(
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                NS_RANGE_LAYOUT,
                ValueLayout.ADDRESS,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "attributedSubstringForProposedRange:actualRange:", attributedSubstringStub, "@:{_NSRange=QQ}^{_NSRange=QQ}")

        // ── NSDraggingDestination protocol ──────────────────────────────────
        ObjCSubclassing.addProtocol(cls, "NSDraggingDestination")

        // draggingEntered: → NSDragOperation (unsigned long)
        // Encoding: Q@:@
        val dragEnteredHandle = lookup.findStatic(
            Callbacks::class.java,
            "draggingEntered",
            MethodType.methodType(
                java.lang.Long.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val dragEnteredStub = linker.upcallStub(
            dragEnteredHandle,
            FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "draggingEntered:", dragEnteredStub, "Q@:@")

        // draggingUpdated: → NSDragOperation (unsigned long)
        // Encoding: Q@:@
        val dragUpdatedHandle = lookup.findStatic(
            Callbacks::class.java,
            "draggingUpdated",
            MethodType.methodType(
                java.lang.Long.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val dragUpdatedStub = linker.upcallStub(
            dragUpdatedHandle,
            FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "draggingUpdated:", dragUpdatedStub, "Q@:@")

        // draggingExited: → void
        // Encoding: v@:@
        val dragExitedHandle = lookup.findStatic(
            Callbacks::class.java,
            "draggingExited",
            MethodType.methodType(
                java.lang.Void.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val dragExitedStub = linker.upcallStub(
            dragExitedHandle,
            FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "draggingExited:", dragExitedStub, "v@:@")

        // performDragOperation: → BOOL (signed char)
        // Encoding: c@:@
        val performDragHandle = lookup.findStatic(
            Callbacks::class.java,
            "performDragOperation",
            MethodType.methodType(
                java.lang.Byte.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val performDragStub = linker.upcallStub(
            performDragHandle,
            FunctionDescriptor.of(
                ValueLayout.JAVA_BYTE,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "performDragOperation:", performDragStub, "c@:@")

        // draggingEnded: → void
        // Encoding: v@:@
        val dragEndedHandle = lookup.findStatic(
            Callbacks::class.java,
            "draggingEnded",
            MethodType.methodType(
                java.lang.Void.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
            ),
        )
        val dragEndedStub = linker.upcallStub(
            dragEndedHandle,
            FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,
            ),
            arena,
        )
        ObjCSubclassing.addMethod(cls, "draggingEnded:", dragEndedStub, "v@:@")

        ObjCSubclassing.registerClass(cls)
        classRegistered = true
    }

    @Volatile
    private var classRegistered: Boolean = false

    /**
     * Creates an instance of KadreTextInputView.
     */
    fun createInstance(frame: MemorySegment): MemorySegment {
        ensureClassRegistered()
        val viewClass = ObjCRuntime.getClass("KadreTextInputView")
        val alloced = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            viewClass,
            ObjCRuntime.sel("alloc"),
        ) as MemorySegment
        return ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            alloced,
            ObjCRuntime.sel("initWithFrame:"),
            ObjCRuntime.ObjCStructArg(frame, NS_RECT_LAYOUT_SRET),
        ) as MemorySegment
    }

    /**
     * Converts an ObjC `id` (NSString or NSAttributedString) to a Kotlin String.
     */
    private fun idToNSString(obj: MemorySegment): String? {
        if (obj == MemorySegment.NULL) return null
        return try {
            // First try UTF8String directly (works for NSString)
            val utf8Ptr = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                obj,
                ObjCRuntime.sel("UTF8String"),
            ) as MemorySegment
            if (utf8Ptr != MemorySegment.NULL) {
                utf8Ptr.reinterpret(4096L).getString(0L, java.nio.charset.StandardCharsets.UTF_8)
            } else null
        } catch (_: Throwable) {
            // If that fails, it might be NSAttributedString - get its .string property
            try {
                val strObj = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    obj,
                    ObjCRuntime.sel("string"),
                ) as MemorySegment
                if (strObj != MemorySegment.NULL) {
                    val utf8Ptr = ObjCRuntime.msgSend(
                        ValueLayout.ADDRESS,
                        strObj,
                        ObjCRuntime.sel("UTF8String"),
                    ) as MemorySegment
                    if (utf8Ptr != MemorySegment.NULL) {
                        utf8Ptr.reinterpret(4096L).getString(0L, java.nio.charset.StandardCharsets.UTF_8)
                    } else null
                } else null
            } catch (_: Throwable) { null }
        }
    }

    /**
     * Extracts file paths from an NSPasteboard during a drag operation.
     *
     * Uses the deprecated [NSFilenamesPboardType] property list approach which
     * is the most reliable method for file drags from Finder and other apps.
     * Returns an empty list if no file paths are available.
     */
    private fun extractFilePathsFromPasteboard(pasteboard: MemorySegment): List<String> {
        if (pasteboard == MemorySegment.NULL) return emptyList()
        val arena = Arena.ofAuto()
        val filenamesType = arena.allocateFrom("NSFilenamesPboardType")
        val propertyList = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            pasteboard,
            ObjCRuntime.sel("propertyListForType:"),
            filenamesType,
        ) as MemorySegment
        if (propertyList == MemorySegment.NULL) return emptyList()
        val count = ObjCRuntime.msgSend(
            ValueLayout.JAVA_LONG,
            propertyList,
            ObjCRuntime.sel("count"),
        ) as Long
        if (count <= 0L) return emptyList()
        val paths = mutableListOf<String>()
        val charset = java.nio.charset.StandardCharsets.UTF_8
        for (i in 0L until count) {
            val pathObj = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                propertyList,
                ObjCRuntime.sel("objectAtIndex:"),
                i,
            ) as MemorySegment
            if (pathObj == MemorySegment.NULL) continue
            val utf8 = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                pathObj,
                ObjCRuntime.sel("UTF8String"),
            ) as MemorySegment
            if (utf8 != MemorySegment.NULL) {
                try {
                    paths.add(utf8.reinterpret(4096L).getString(0L, charset))
                } catch (_: Throwable) { /* skip unreadable path */ }
            }
        }
        return paths
    }

    /**
     * `@JvmStatic` trampolines invoked by Panama upcall stubs.
     * Each method looks up the target [ImeViewRecord] via [imeViewTable]
     * and dispatches the appropriate event.
     */
    object Callbacks {
        @JvmStatic
        fun acceptsFirstResponder(
            @Suppress("UNUSED_PARAMETER") self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
        ): Byte = 1 // YES

        /**
         * insertText:replacementRange: — the IME committed text.
         */
        @JvmStatic
        fun insertText_replacementRange(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            textObj: MemorySegment,
            @Suppress("UNUSED_PARAMETER") replacementRange: MemorySegment,
        ) {
            val record = imeViewTable[self.address()] ?: return
            val text = idToNSString(textObj) ?: return
            record.handler.windowEvent(
                record.eventLoop,
                record.windowId,
                WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Commit(text)),
            )
        }

        /**
         * setMarkedText:selectedRange:replacementRange: — the IME updated the preedit string.
         */
        @JvmStatic
        fun setMarkedText_selectedRange_replacementRange(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            textObj: MemorySegment,
            selectedRange: MemorySegment,
            @Suppress("UNUSED_PARAMETER") replacementRange: MemorySegment,
        ) {
            val record = imeViewTable[self.address()] ?: return
            val text = idToNSString(textObj) ?: return
            val selStart = selectedRange.getAtIndex(ValueLayout.JAVA_LONG, 0)
            val selLen = selectedRange.getAtIndex(ValueLayout.JAVA_LONG, 1)
            val cursorRange: Pair<Int, Int>? = if (selStart != NS_NOT_FOUND) {
                Pair(selStart.toInt(), (selStart + selLen).toInt())
            } else null
            record.handler.windowEvent(
                record.eventLoop,
                record.windowId,
                WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Preedit(text, cursorRange)),
            )
        }

        /**
         * unmarkText — the IME cancelled the preedit session.
         * Dispatch Disabled to signal the preedit session ended.
         */
        @JvmStatic
        fun unmarkText(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
        ) {
            val record = imeViewTable[self.address()] ?: return
            record.handler.windowEvent(
                record.eventLoop,
                record.windowId,
                WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Disabled),
            )
        }

        /**
         * hasMarkedText - returns NO (we don't track marked text state).
         */
        @JvmStatic
        fun hasMarkedText(
            @Suppress("UNUSED_PARAMETER") self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
        ): Byte = 0 // NO

        /**
         * markedRange - returns {NSNotFound, 0}.
         */
        @JvmStatic
        fun markedRange(
            @Suppress("UNUSED_PARAMETER") self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
        ): MemorySegment {
            val arena = Arena.ofAuto()
            val range = arena.allocate(NS_RANGE_LAYOUT)
            range.setAtIndex(ValueLayout.JAVA_LONG, 0, NS_NOT_FOUND)
            range.setAtIndex(ValueLayout.JAVA_LONG, 1, 0L)
            return range
        }

        /**
         * selectedRange - returns {NSNotFound, 0}.
         */
        @JvmStatic
        fun selectedRange(
            @Suppress("UNUSED_PARAMETER") self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
        ): MemorySegment {
            val arena = Arena.ofAuto()
            val range = arena.allocate(NS_RANGE_LAYOUT)
            range.setAtIndex(ValueLayout.JAVA_LONG, 0, NS_NOT_FOUND)
            range.setAtIndex(ValueLayout.JAVA_LONG, 1, 0L)
            return range
        }

        /**
         * firstRectForCharacterRange:actualRange: — returns the IME cursor rect
         * in screen coordinates (Cocoa bottom-left origin).
         *
         * sret: the first argument is the return buffer (CGRect*).
         */
        @JvmStatic
        fun firstRectForCharacterRange_actualRange(
            returnRect: MemorySegment,
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") characterRange: MemorySegment,
            actualRange: MemorySegment,
        ) {
            val record = imeViewTable[self.address()]
            val screenRect = record?.imeCursorScreenRect
            if (screenRect != null && screenRect != MemorySegment.NULL) {
                returnRect.setAtIndex(ValueLayout.JAVA_DOUBLE, 0, screenRect.getAtIndex(ValueLayout.JAVA_DOUBLE, 0))
                returnRect.setAtIndex(ValueLayout.JAVA_DOUBLE, 1, screenRect.getAtIndex(ValueLayout.JAVA_DOUBLE, 1))
                returnRect.setAtIndex(ValueLayout.JAVA_DOUBLE, 2, screenRect.getAtIndex(ValueLayout.JAVA_DOUBLE, 2))
                returnRect.setAtIndex(ValueLayout.JAVA_DOUBLE, 3, screenRect.getAtIndex(ValueLayout.JAVA_DOUBLE, 3))
            } else {
                // Fallback: return zero rect at origin
                returnRect.setAtIndex(ValueLayout.JAVA_DOUBLE, 0, 0.0)
                returnRect.setAtIndex(ValueLayout.JAVA_DOUBLE, 1, 0.0)
                returnRect.setAtIndex(ValueLayout.JAVA_DOUBLE, 2, 0.0)
                returnRect.setAtIndex(ValueLayout.JAVA_DOUBLE, 3, 0.0)
            }
            if (actualRange != MemorySegment.NULL) {
                actualRange.setAtIndex(ValueLayout.JAVA_LONG, 0, characterRange.getAtIndex(ValueLayout.JAVA_LONG, 0))
                actualRange.setAtIndex(ValueLayout.JAVA_LONG, 1, characterRange.getAtIndex(ValueLayout.JAVA_LONG, 1))
            }
        }

        /**
         * characterIndexForPoint: — returns 0 (no special mapping).
         */
        @JvmStatic
        fun characterIndexForPoint(
            @Suppress("UNUSED_PARAMETER") self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") point: MemorySegment,
        ): Long = 0L

        /**
         * validAttributesForMarkedText — returns an empty NSArray (no attributes).
         */
        @JvmStatic
        fun validAttributesForMarkedText(
            @Suppress("UNUSED_PARAMETER") self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
        ): MemorySegment {
            val nsArrayClass = ObjCRuntime.getClass("NSArray")
            return ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                nsArrayClass,
                ObjCRuntime.sel("array"),
            ) as MemorySegment
        }

        /**
         * attributedSubstringForProposedRange:actualRange: — returns nil.
         */
        @JvmStatic
        fun attributedSubstringForProposedRange_actualRange(
            @Suppress("UNUSED_PARAMETER") self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") proposedRange: MemorySegment,
            @Suppress("UNUSED_PARAMETER") actualRange: MemorySegment,
        ): MemorySegment = MemorySegment.NULL

        // ── NSDraggingDestination callbacks ──────────────────────────────────

        /**
         * draggingEntered: — a drag operation entered the view.
         *
         * Extracts the drop position and file paths from the pasteboard,
         * dispatches [WindowEvent.DragEntered], and returns NSDragOperationGeneric
         * to accept the drag.
         */
        @JvmStatic
        fun draggingEntered(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            sender: MemorySegment,
        ): Long {
            val record = imeViewTable[self.address()] ?: return 0L
            try {
                val point = ObjCRuntime.msgSendStret(
                    CG_POINT_LAYOUT,
                    sender,
                    ObjCRuntime.sel("draggingLocation"),
                )
                val cocoaX = point.getAtIndex(ValueLayout.JAVA_DOUBLE, 0)
                val cocoaY = point.getAtIndex(ValueLayout.JAVA_DOUBLE, 1)

                val frame = ObjCRuntime.msgSendStret(
                    NS_RECT_LAYOUT_SRET,
                    self,
                    ObjCRuntime.sel("frame"),
                )
                val viewHeight = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 3)
                val pos = PhysicalPosition(cocoaX, viewHeight - cocoaY)

                val pasteboard = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    sender,
                    ObjCRuntime.sel("draggingPasteboard"),
                ) as MemorySegment
                val paths = extractFilePathsFromPasteboard(pasteboard)

                record.handler.windowEvent(
                    record.eventLoop,
                    record.windowId,
                    WindowEvent.DragEntered(pos, paths),
                )
            } catch (_: Throwable) { /* best-effort */ }
            return 4L // NSDragOperationGeneric
        }

        /**
         * draggingUpdated: — the drag cursor moved within the view.
         *
         * Dispatches [WindowEvent.DragMoved] with the current position.
         */
        @JvmStatic
        fun draggingUpdated(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            sender: MemorySegment,
        ): Long {
            val record = imeViewTable[self.address()] ?: return 0L
            try {
                val point = ObjCRuntime.msgSendStret(
                    CG_POINT_LAYOUT,
                    sender,
                    ObjCRuntime.sel("draggingLocation"),
                )
                val cocoaX = point.getAtIndex(ValueLayout.JAVA_DOUBLE, 0)
                val cocoaY = point.getAtIndex(ValueLayout.JAVA_DOUBLE, 1)

                val frame = ObjCRuntime.msgSendStret(
                    NS_RECT_LAYOUT_SRET,
                    self,
                    ObjCRuntime.sel("frame"),
                )
                val viewHeight = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 3)
                val pos = PhysicalPosition(cocoaX, viewHeight - cocoaY)

                record.handler.windowEvent(
                    record.eventLoop,
                    record.windowId,
                    WindowEvent.DragMoved(pos),
                )
            } catch (_: Throwable) { /* best-effort */ }
            return 4L // NSDragOperationGeneric
        }

        /**
         * draggingExited: — the drag cursor left the view without dropping.
         *
         * Dispatches [WindowEvent.DragLeft].
         */
        @JvmStatic
        fun draggingExited(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") sender: MemorySegment,
        ) {
            val record = imeViewTable[self.address()] ?: return
            try {
                record.handler.windowEvent(
                    record.eventLoop,
                    record.windowId,
                    WindowEvent.DragLeft,
                )
            } catch (_: Throwable) { /* best-effort */ }
        }

        /**
         * performDragOperation: — the user dropped items onto the view.
         *
         * Extracts the drop position and file paths, dispatches
         * [WindowEvent.DragDropped], and returns YES to accept the drop.
         */
        @JvmStatic
        fun performDragOperation(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            sender: MemorySegment,
        ): Byte {
            val record = imeViewTable[self.address()] ?: return 0
            try {
                val point = ObjCRuntime.msgSendStret(
                    CG_POINT_LAYOUT,
                    sender,
                    ObjCRuntime.sel("draggingLocation"),
                )
                val cocoaX = point.getAtIndex(ValueLayout.JAVA_DOUBLE, 0)
                val cocoaY = point.getAtIndex(ValueLayout.JAVA_DOUBLE, 1)

                val frame = ObjCRuntime.msgSendStret(
                    NS_RECT_LAYOUT_SRET,
                    self,
                    ObjCRuntime.sel("frame"),
                )
                val viewHeight = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 3)
                val pos = PhysicalPosition(cocoaX, viewHeight - cocoaY)

                val pasteboard = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    sender,
                    ObjCRuntime.sel("draggingPasteboard"),
                ) as MemorySegment
                val paths = extractFilePathsFromPasteboard(pasteboard)

                record.handler.windowEvent(
                    record.eventLoop,
                    record.windowId,
                    WindowEvent.DragDropped(pos, paths),
                )
            } catch (_: Throwable) { /* best-effort */ }
            return 1 // YES
        }

        /**
         * draggingEnded: — the drag session ended (drop completed or cancelled).
         *
         * No event is dispatched for this callback; it is a notification that
         * the drag session is over.
         */
        @JvmStatic
        fun draggingEnded(
            @Suppress("UNUSED_PARAMETER") self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") sender: MemorySegment,
        ) {
            // No event — session end notification only
        }
    }
}
