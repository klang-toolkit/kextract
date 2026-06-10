/**
 * Wayland text-input (IME) integration via zwp_text_input_v3.
 *
 * ### Protocol flow
 * 1. `zwp_text_input_manager_v3.create_text_input` → get a `zwp_text_input_v3` proxy per seat.
 * 2. Listeners on `zwp_text_input_v3` receive compositor-driven IME events.
 * 3. `done(serial)` signals the end of a batch — stored events are dispatched.
 * 4. Window IME methods (`setImeAllowed`, `setImeCursorArea`, `setImePurpose`)
 *    call the corresponding `zwp_text_input_v3` requests.
 *
 * ### Thread safety
 * All Wayland protocol calls happen on the loop thread.
 * The state holder ([WaylandTextInput]) is effectively single-threaded.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.ffi.wayland.*
import org.graphiks.kadre.core.ImePurpose
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

// ── zwp_text_input_v3 request opcodes ─────────────────────────────────────────

internal const val ZWP_TEXT_INPUT_V3_ENABLE: Int = 0
internal const val ZWP_TEXT_INPUT_V3_DISABLE: Int = 1
internal const val ZWP_TEXT_INPUT_V3_SET_CURSOR_RECTANGLE: Int = 2
internal const val ZWP_TEXT_INPUT_V3_SET_INPUT_PURPOSE: Int = 3

// zwp_text_input_manager_v3 request opcodes
private const val ZWP_TEXT_INPUT_MANAGER_V3_DESTROY: Int = 0
private const val ZWP_TEXT_INPUT_MANAGER_V3_CREATE_TEXT_INPUT: Int = 1

// ── zwp_text_input_v3 input_purpose enum values ───────────────────────────────

private const val ZWP_TEXT_INPUT_PURPOSE_DEFAULT: Int = 0
private const val ZWP_TEXT_INPUT_PURPOSE_PASSWORD: Int = 8
private const val ZWP_TEXT_INPUT_PURPOSE_TERMINAL: Int = 13

// ── State tracking ───────────────────────────────────────────────────────────

/**
 * Holds the per-seat `zwp_text_input_v3` proxy and the current IME state.
 *
 * Only one seat is typically present; this single instance is sufficient
 * for the common case. The pointer is 0 when the protocol extension is
 * unavailable on the compositor.
 */
internal object WaylandTextInput {
    /** Address of the `zwp_text_input_v3` proxy (0 if not available). */
    var textInputPtr: Long = 0L

    /** The display pointer needed for flush after requests. */
    var displayPtr: Long = 0L

    /** Version of the bound zwp_text_input_v3. */
    var version: Int = 1

    /**
     * Current focused surface (set by `enter`, cleared by `leave`).
     * 0 when text input is not active on any of our surfaces.
     */
    var focusedSurfacePtr: Long = 0L

    /** Whether IME is currently enabled for the focused surface. */
    var imeEnabled: Boolean = false

    /** Event sink routed from the event loop to the window event queue. */
    var onImeEvent: ((surfacePtr: Long, event: WindowEvent) -> Unit)? = null
}

// ── Factory ───────────────────────────────────────────────────────────────────

/**
 * Creates a `zwp_text_input_v3` from the bound manager and installs
 * its event listener.
 *
 * @param managerPtr  Address of the bound `zwp_text_input_manager_v3`.
 * @param display     Display pointer for flush after requests.
 * @param onEvent     Sink for IME events routed to window event queues.
 */
internal fun createTextInput(
    managerPtr: Long,
    display: Long,
    onEvent: (surfacePtr: Long, event: WindowEvent) -> Unit,
) {
    val createHandle = zwpInputManagerV3CreateTextInput ?: return
    val iface = zwpTextInputV3Interface
    val addListener = wlProxyAddListener ?: return
    val getVersion = wlProxyGetVersion ?: return

    val textInput: MemorySegment
    val tiVersion: Int
    try {
        val manager = MemorySegment.ofAddress(managerPtr)
        textInput = createHandle.invokeExact(
            manager,
            ZWP_TEXT_INPUT_MANAGER_V3_CREATE_TEXT_INPUT,
            iface,
            1, // request version
            0, // flags
            MemorySegment.NULL,
        ) as MemorySegment
        if (textInput.address() == 0L) return

        tiVersion = runCatching {
            getVersion.invokeExact(textInput) as Int
        }.getOrDefault(1)

        installTextInputListener(textInput, addListener, tiVersion, onEvent)

        WaylandTextInput.textInputPtr = textInput.address()
        WaylandTextInput.displayPtr = display
        WaylandTextInput.version = tiVersion
        WaylandTextInput.onImeEvent = onEvent
    } catch (_: Throwable) {
        // Protocol extension unavailable — leave textInputPtr = 0
    }
}

/**
 * Installs the `zwp_text_input_v3` listener (vtable-based upcall).
 *
 * Listener vtable order (version 1):
 *   0: enter, 1: leave, 2: preedit_string, 3: commit_string,
 *   4: delete_surrounding, 5: done
 */
private fun installTextInputListener(
    textInput: MemorySegment,
    addListener: java.lang.invoke.MethodHandle,
    version: Int,
    onEvent: (surfacePtr: Long, event: WindowEvent) -> Unit,
) {
    val listener = TextInputListener(onEvent)
    val arena = Arena.ofShared()
    val lookup = MethodHandles.lookup()
    val ptr = ValueLayout.ADDRESS.byteSize()
    val eventCount = 6

    val enterStub = upcallStub(
        lookup.findVirtual(TextInputListener::class.java, "onEnter",
            MethodType.methodType(Void.TYPE,
                MemorySegment::class.java, MemorySegment::class.java,
                MemorySegment::class.java,
            )).bindTo(listener),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
        arena,
    )
    val leaveStub = upcallStub(
        lookup.findVirtual(TextInputListener::class.java, "onLeave",
            MethodType.methodType(Void.TYPE,
                MemorySegment::class.java, MemorySegment::class.java,
                MemorySegment::class.java,
            )).bindTo(listener),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
        arena,
    )
    val preeditStub = upcallStub(
        lookup.findVirtual(TextInputListener::class.java, "onPreeditString",
            MethodType.methodType(Void.TYPE,
                MemorySegment::class.java, MemorySegment::class.java,
                MemorySegment::class.java, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
            )).bindTo(listener),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
        arena,
    )
    val commitStub = upcallStub(
        lookup.findVirtual(TextInputListener::class.java, "onCommitString",
            MethodType.methodType(Void.TYPE,
                MemorySegment::class.java, MemorySegment::class.java,
                MemorySegment::class.java,
            )).bindTo(listener),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
        arena,
    )
    val deleteStub = upcallStub(
        lookup.findVirtual(TextInputListener::class.java, "onDeleteSurrounding",
            MethodType.methodType(Void.TYPE,
                MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
            )).bindTo(listener),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
        arena,
    )
    val doneStub = upcallStub(
        lookup.findVirtual(TextInputListener::class.java, "onDone",
            MethodType.methodType(Void.TYPE,
                MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType,
            )).bindTo(listener),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
        arena,
    )

    val vtable = arena.allocate(ptr * eventCount)
    vtable.set(ValueLayout.ADDRESS, 0L,      enterStub)
    vtable.set(ValueLayout.ADDRESS, ptr,     leaveStub)
    vtable.set(ValueLayout.ADDRESS, ptr * 2, preeditStub)
    vtable.set(ValueLayout.ADDRESS, ptr * 3, commitStub)
    vtable.set(ValueLayout.ADDRESS, ptr * 4, deleteStub)
    vtable.set(ValueLayout.ADDRESS, ptr * 5, doneStub)

    runCatching {
        addListener.invokeExact(textInput, vtable, MemorySegment.NULL) as Int
    }
}

// ── Listener implementation ───────────────────────────────────────────────────

/**
 * wl_text_input_v3 listener that accumulates IME events in the batch
 * and dispatches them on `done(serial)`.
 */
private class TextInputListener(
    private val onEvent: (surfacePtr: Long, event: WindowEvent) -> Unit,
) {
    // Pending events accumulated between preedit_string/commit_string/delete_surrounding
    // and the next done event.
    private val pending = mutableListOf<WindowEvent.Ime.ImeEvent>()

    @Suppress("UNUSED_PARAMETER")
    fun onEnter(data: MemorySegment, textInput: MemorySegment, surface: MemorySegment) {
        val surfacePtr = surface.address()
        WaylandTextInput.focusedSurfacePtr = surfacePtr
        if (!WaylandTextInput.imeEnabled) {
            // The compositor entered our surface — emit Enabled if not already
            onEvent(surfacePtr, WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Enabled))
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onLeave(data: MemorySegment, textInput: MemorySegment, surface: MemorySegment) {
        val surfacePtr = surface.address().takeIf { it != 0L } ?: WaylandTextInput.focusedSurfacePtr
        WaylandTextInput.focusedSurfacePtr = 0L
        WaylandTextInput.imeEnabled = false
        onEvent(surfacePtr, WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Disabled))
    }

    @Suppress("UNUSED_PARAMETER")
    fun onPreeditString(
        data: MemorySegment, textInput: MemorySegment,
        text: MemorySegment, cursorBegin: Int, cursorEnd: Int,
    ) {
        val str = try {
            text.getString(0)
        } catch (_: Throwable) {
            ""
        }
        val cursorRange = if (cursorBegin >= 0 && cursorEnd >= 0) {
            Pair(cursorBegin, cursorEnd)
        } else {
            null
        }
        pending.add(WindowEvent.Ime.ImeEvent.Preedit(str, cursorRange))
    }

    @Suppress("UNUSED_PARAMETER")
    fun onCommitString(
        data: MemorySegment, textInput: MemorySegment,
        text: MemorySegment,
    ) {
        val str = try {
            text.getString(0)
        } catch (_: Throwable) {
            ""
        }
        pending.add(WindowEvent.Ime.ImeEvent.Commit(str))
    }

    @Suppress("UNUSED_PARAMETER")
    fun onDeleteSurrounding(
        data: MemorySegment, textInput: MemorySegment,
        beforeLength: Int, afterLength: Int,
    ) {
        pending.add(WindowEvent.Ime.ImeEvent.DeleteSurrounding(beforeLength, afterLength))
    }

    @Suppress("UNUSED_PARAMETER")
    fun onDone(data: MemorySegment, textInput: MemorySegment, serial: Int) {
        val surfacePtr = WaylandTextInput.focusedSurfacePtr
        if (surfacePtr == 0L) {
            pending.clear()
            return
        }
        // Dispatch all events accumulated since the last done.
        for (event in pending) {
            onEvent(surfacePtr, WindowEvent.Ime(event))
        }
        pending.clear()
    }
}

// ── IME helper methods ────────────────────────────────────────────────────────

/**
 * Maps a kadre [ImePurpose] to the zwp_text_input_v3 `input_purpose` enum value.
 */
internal fun imePurposeToWayland(purpose: ImePurpose): Int = when (purpose) {
    ImePurpose.Normal -> ZWP_TEXT_INPUT_PURPOSE_DEFAULT
    ImePurpose.Password -> ZWP_TEXT_INPUT_PURPOSE_PASSWORD
    ImePurpose.Terminal -> ZWP_TEXT_INPUT_PURPOSE_TERMINAL
}

/**
 * Sends `zwp_text_input_v3.enable` — must be called when the app requests IME.
 */
internal fun waylandTextInputEnable() {
    val handle = wlProxyMarshalFlagsVoid ?: return
    val ptr = WaylandTextInput.textInputPtr
    if (ptr == 0L) return
    try {
        handle.invokeExact(
            MemorySegment.ofAddress(ptr),
            ZWP_TEXT_INPUT_V3_ENABLE,
            MemorySegment.NULL,
            WaylandTextInput.version,
            0,
        )
        WaylandTextInput.imeEnabled = true
        wlDisplayFlush?.let { flush ->
            flush.invokeExact(MemorySegment.ofAddress(WaylandTextInput.displayPtr)) as Int
        }
    } catch (_: Throwable) {}
}

/**
 * Sends `zwp_text_input_v3.disable` — must be called when the app stops IME.
 */
internal fun waylandTextInputDisable() {
    val handle = wlProxyMarshalFlagsVoid ?: return
    val ptr = WaylandTextInput.textInputPtr
    if (ptr == 0L) return
    try {
        handle.invokeExact(
            MemorySegment.ofAddress(ptr),
            ZWP_TEXT_INPUT_V3_DISABLE,
            MemorySegment.NULL,
            WaylandTextInput.version,
            0,
        )
        WaylandTextInput.imeEnabled = false
        wlDisplayFlush?.let { flush ->
            flush.invokeExact(MemorySegment.ofAddress(WaylandTextInput.displayPtr)) as Int
        }
    } catch (_: Throwable) {}
}

/**
 * Sends `zwp_text_input_v3.set_cursor_rectangle(x, y, width, height)`.
 *
 * @param position Top-left corner in surface-local coordinates (physical pixels).
 * @param size     Size of the cursor area in physical pixels.
 * @param scale    Window scale factor for converting to buffer coordinates.
 */
internal fun waylandTextInputSetCursorRectangle(
    position: PhysicalPosition<Int>,
    size: PhysicalSize<Int>,
    scale: Double,
) {
    val handle = wlProxyMarshalFlagsFourInt ?: return
    val ptr = WaylandTextInput.textInputPtr
    if (ptr == 0L) return
    val invScale = if (scale > 0.0) 1.0 / scale else 1.0
    val x = (position.x * invScale).toInt()
    val y = (position.y * invScale).toInt()
    val w = (size.width * invScale).toInt()
    val h = (size.height * invScale).toInt()
    try {
        handle.invokeExact(
            MemorySegment.ofAddress(ptr),
            ZWP_TEXT_INPUT_V3_SET_CURSOR_RECTANGLE,
            MemorySegment.NULL,
            WaylandTextInput.version,
            0,
            x, y, w, h,
        )
        wlDisplayFlush?.let { flush ->
            flush.invokeExact(MemorySegment.ofAddress(WaylandTextInput.displayPtr)) as Int
        }
    } catch (_: Throwable) {}
}

/**
 * Sends `zwp_text_input_v3.set_input_purpose(purpose)`.
 */
internal fun waylandTextInputSetPurpose(purpose: ImePurpose) {
    val handle = wlProxyMarshalFlagsUint ?: return
    val ptr = WaylandTextInput.textInputPtr
    if (ptr == 0L) return
    val wp = imePurposeToWayland(purpose)
    try {
        handle.invokeExact(
            MemorySegment.ofAddress(ptr),
            ZWP_TEXT_INPUT_V3_SET_INPUT_PURPOSE,
            MemorySegment.NULL,
            WaylandTextInput.version,
            0,
            wp,
        )
        wlDisplayFlush?.let { flush ->
            flush.invokeExact(MemorySegment.ofAddress(WaylandTextInput.displayPtr)) as Int
        }
    } catch (_: Throwable) {}
}
