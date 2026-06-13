package org.graphiks.kadre.appkit.capture

import org.graphiks.kffi.objc.ObjCRuntime
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.CaptureConfig
import org.graphiks.kadre.core.capture.CaptureError
import org.graphiks.kadre.core.capture.CapturePermission
import org.graphiks.kadre.core.capture.CaptureSession
import org.graphiks.kadre.core.capture.CaptureSource
import org.graphiks.kadre.core.capture.DisplayInfo
import org.graphiks.kadre.core.capture.DisplayId
import org.graphiks.kadre.core.capture.ScreenCapturer
import org.graphiks.kadre.core.capture.WindowInfo
import org.graphiks.kadre.core.capture.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.GroupLayout
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BOOLEAN
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle

class AppKitScreenCapturer : ScreenCapturer {

    private val arena = Arena.global()
    private val linker = Linker.nativeLinker()

    private val scShareableContentClass: MemorySegment
    private val scDisplayClass: MemorySegment
    private val scWindowClass: MemorySegment
    private val scContentFilterClass: MemorySegment
    private val scStreamConfigurationClass: MemorySegment
    private val scStreamClass: MemorySegment

    private val selDisplays = ObjCRuntime.sel("displays")
    private val selWindows = ObjCRuntime.sel("windows")
    private val selDisplayID = ObjCRuntime.sel("displayID")
    private val selWidth = ObjCRuntime.sel("width")
    private val selHeight = ObjCRuntime.sel("height")
    private val selScaleFactor = ObjCRuntime.sel("scaleFactor")
    private val selDisplayName = ObjCRuntime.sel("displayName")
    private val selWindowID = ObjCRuntime.sel("windowID")
    private val selTitle = ObjCRuntime.sel("title")
    private val selFrame = ObjCRuntime.sel("frame")
    private val selIsOnScreen = ObjCRuntime.sel("isOnScreen")
    private val selOwningApplication = ObjCRuntime.sel("owningApplication")
    private val selBundleIdentifier = ObjCRuntime.sel("bundleIdentifier")
    private val selApplicationName = ObjCRuntime.sel("applicationName")
    private val selCount = ObjCRuntime.sel("count")
    private val selObjectAtIndex = ObjCRuntime.sel("objectAtIndex:")
    private val selAlloc = ObjCRuntime.sel("alloc")
    private val selNew = ObjCRuntime.sel("new")
    private val selInit = ObjCRuntime.sel("init")

    init {
        SymbolLookup.libraryLookup(
            "/System/Library/Frameworks/ScreenCaptureKit.framework/ScreenCaptureKit",
            arena,
        )
        scShareableContentClass = ObjCRuntime.getClass("SCShareableContent")
        scDisplayClass = ObjCRuntime.getClass("SCDisplay")
        scWindowClass = ObjCRuntime.getClass("SCWindow")
        scContentFilterClass = ObjCRuntime.getClass("SCContentFilter")
        scStreamConfigurationClass = ObjCRuntime.getClass("SCStreamConfiguration")
        scStreamClass = ObjCRuntime.getClass("SCStream")

        fun require(expected: MemorySegment, name: String) {
            if (expected == MemorySegment.NULL) {
                throw UnsatisfiedLinkError("ScreenCaptureKit class not found: $name")
            }
        }
        require(scShareableContentClass, "SCShareableContent")
        require(scDisplayClass, "SCDisplay")
        require(scWindowClass, "SCWindow")
        require(scContentFilterClass, "SCContentFilter")
        require(scStreamConfigurationClass, "SCStreamConfiguration")
        require(scStreamClass, "SCStream")
    }

    override suspend fun enumerateDisplays(): List<DisplayInfo> {
        return ObjCRuntime.autoreleasePool {
            val displaysArray = enumerateDisplaysCG()
            if (displaysArray.isEmpty()) return emptyList()
            displaysArray
        }
    }

    override suspend fun enumerateWindows(): List<WindowInfo> {
        return ObjCRuntime.autoreleasePool {
            enumerateWindowsCG()
        }
    }

    override suspend fun createSession(source: CaptureSource, config: CaptureConfig): CaptureSession {
        return when (source) {
            is CaptureSource.Display -> {
                // Use CGDisplayCreateImage (no TCC permission required)
                CGDisplayCaptureSession(source, config, source.id)
            }
            is CaptureSource.Window -> {
                // ScreenCaptureKit-based window capture
                val nativeStream = buildStream(source, config)
                AppKitCaptureSession(source, config, nativeStream)
            }
        }
    }

    override suspend fun requestPermission(): CapturePermission {
        return ObjCRuntime.autoreleasePool {
            val content = getShareableContent()
            if (content != null) CapturePermission.Granted
            else CapturePermission.Denied("Screen capture permission denied")
        }
    }

    private val preflightHandle: java.util.function.Supplier<Boolean>? by lazy {
        try {
            val framework = SymbolLookup.libraryLookup(
                "/System/Library/Frameworks/CoreGraphics.framework/CoreGraphics",
                arena,
            )
            val preflight = framework.find("CGPreflightScreenCaptureAccess").orElse(null) ?: return@lazy null
            val handle = linker.downcallHandle(preflight, FunctionDescriptor.of(JAVA_BOOLEAN))
            java.util.function.Supplier { handle.invokeExact() as Boolean }
        } catch (_: Throwable) { null }
    }

    override fun permissionStatus(): CapturePermission {
        val granted = preflightHandle?.get() ?: return CapturePermission.Pending
        return if (granted) CapturePermission.Granted else CapturePermission.Pending
    }

    // ── NSScreen-based enumeration (no TCC permission required) ─────────

    private val nsScreenClass: MemorySegment by lazy {
        ObjCRuntime.getClass("NSScreen")
    }
    private val selScreens = ObjCRuntime.sel("screens")
    private val selBackingScaleFactor = ObjCRuntime.sel("backingScaleFactor")

    private val selFrameOrigin = ObjCRuntime.sel("frameOrigin")
    private val selFrameSize = ObjCRuntime.sel("frameSize")

    private val objcMsgSendStretFixed: MethodHandle? by lazy {
        val libObjc = SymbolLookup.libraryLookup("/usr/lib/libobjc.A.dylib", Arena.global())
        val addr = libObjc.find("objc_msgSend_stret").orElse(null)
            ?: libObjc.find("objc_msgSend").orElse(null)
        addr?.let { linker.downcallHandle(it, FunctionDescriptor.of(rectLayout, ADDRESS, ADDRESS)) }
    }

    private fun enumerateDisplaysCG(): List<DisplayInfo> {
        val stret = objcMsgSendStretFixed ?: return emptyList()
        return ObjCRuntime.autoreleasePool {
            val screensArray = ObjCRuntime.msgSend(ADDRESS, nsScreenClass, selScreens) as MemorySegment
            if (screensArray == MemorySegment.NULL) return@autoreleasePool emptyList()
            val count = (ObjCRuntime.msgSend(JAVA_LONG, screensArray, selCount) as Long).toInt()
            if (count <= 0) return@autoreleasePool emptyList()

            (0 until count).map { i ->
                val screen = ObjCRuntime.msgSend(ADDRESS, screensArray, selObjectAtIndex, i.toLong()) as MemorySegment

                // Get NSRect via direct objc_msgSend on native arena
                var ox = 0.0; var oy = 0.0; var sw = 0.0; var sh = 0.0
                Arena.ofConfined().use { rectArena ->
                    val allocator = SegmentAllocator.prefixAllocator(rectArena.allocate(32L))
                    val rectSeg = stret.invokeWithArguments(allocator, screen, selFrame) as MemorySegment
                    ox = rectSeg.get(JAVA_DOUBLE, 0L)
                    oy = rectSeg.get(JAVA_DOUBLE, 8L)
                    sw = rectSeg.get(JAVA_DOUBLE, 16L)
                    sh = rectSeg.get(JAVA_DOUBLE, 24L)
                }

                val scaleFactor = ObjCRuntime.msgSend(JAVA_DOUBLE, screen, selBackingScaleFactor) as Double

                // Device description for display ID
                val desc = ObjCRuntime.msgSend(ADDRESS, screen, ObjCRuntime.sel("deviceDescription")) as MemorySegment
                val displayIdKey = ObjCRuntime.msgSend(ADDRESS, ObjCRuntime.getClass("NSString"), ObjCRuntime.sel("stringWithUTF8String:"), arena.allocateFrom("NSScreenNumber")) as MemorySegment
                val displayIdNum = ObjCRuntime.msgSend(ADDRESS, desc, ObjCRuntime.sel("objectForKey:"), displayIdKey) as MemorySegment
                val displayId = if (displayIdNum != MemorySegment.NULL) {
                    (ObjCRuntime.msgSend(JAVA_LONG, displayIdNum, ObjCRuntime.sel("integerValue")) as Long)
                } else i.toLong()

                DisplayInfo(
                    id = displayId,
                    name = "Display $i",
                    position = PhysicalPosition(ox.toInt(), oy.toInt()),
                    resolution = PhysicalSize(
                        (sw * scaleFactor).toInt(),
                        (sh * scaleFactor).toInt(),
                    ),
                    scaleFactor = scaleFactor,
                )
            }
        }
    }

    private fun enumerateWindowsCG(): List<WindowInfo> {
        // Window enumeration via CoreGraphics window list: TCC permission required.
        // Return empty list for now — users can specify window by ID if known.
        return emptyList()
    }

    // ── Private helpers ────────────────────────────────────────────────

    private fun getShareableContent(): MemorySegment? {
        val callback = ObjCCallback2()
        val blockArena = Arena.ofShared()
        try {
            val upcallStub = linker.upcallStub(callback.methodHandle, callback.fnDescriptor, blockArena)
            val block = ObjCBlocks.copy(ObjCBlocks.create(upcallStub, blockArena))
            val sel = ObjCRuntime.sel("getShareableContentWithCompletionHandler:")
            ObjCRuntime.msgSend(null, scShareableContentClass, sel, block)

            if (!callback.await(10000)) {
                System.err.println("[AppKitScreenCapturer] Timeout waiting for SCShareableContent callback")
                return null
            }
            val cbErr = callback.error
            if (cbErr != null && cbErr != MemorySegment.NULL) {
                System.err.println("[AppKitScreenCapturer] SCShareableContent returned error (pointer @ 0x%x)".format(cbErr.address()))
                System.err.println("[AppKitScreenCapturer] Hint: grant Screen Recording permission in System Settings > Privacy & Security")
                return null
            }
            if (callback.result == null || callback.result == MemorySegment.NULL) {
                System.err.println("[AppKitScreenCapturer] SCShareableContent returned null content")
                return null
            }
            return callback.result
        } finally {
            blockArena.close()
        }
    }

    private fun extractDisplays(content: MemorySegment): List<DisplayInfo> {
        return ObjCRuntime.autoreleasePool {
            val displaysArray = ObjCRuntime.msgSend(ADDRESS, content, selDisplays) as MemorySegment
            val count = (ObjCRuntime.msgSend(JAVA_LONG, displaysArray, selCount) as Long).toInt()
            (0 until count).map { i ->
                val scDisplay = ObjCRuntime.msgSend(ADDRESS, displaysArray, selObjectAtIndex, i.toLong()) as MemorySegment
                scDisplayToInfo(scDisplay)
            }
        }
    }

    private fun extractWindows(content: MemorySegment): List<WindowInfo> {
        return ObjCRuntime.autoreleasePool {
            val windowsArray = ObjCRuntime.msgSend(ADDRESS, content, selWindows) as MemorySegment
            val count = (ObjCRuntime.msgSend(JAVA_LONG, windowsArray, selCount) as Long).toInt()
            (0 until count).mapNotNull { i ->
                val scWindow = ObjCRuntime.msgSend(ADDRESS, windowsArray, selObjectAtIndex, i.toLong()) as MemorySegment
                scWindowToInfo(scWindow)
            }
        }
    }

    private fun scDisplayToInfo(scDisplay: MemorySegment): DisplayInfo {
        val displayId = (ObjCRuntime.msgSend(JAVA_INT, scDisplay, selDisplayID) as Int).toLong()
        val width = (ObjCRuntime.msgSend(JAVA_LONG, scDisplay, selWidth) as Long).toInt()
        val height = (ObjCRuntime.msgSend(JAVA_LONG, scDisplay, selHeight) as Long).toInt()
        val scaleFactor = ObjCRuntime.msgSend(JAVA_DOUBLE, scDisplay, selScaleFactor) as Double
        val nameSegment = ObjCRuntime.msgSend(ADDRESS, scDisplay, selDisplayName) as MemorySegment
        val name = if (nameSegment == MemorySegment.NULL) null else ObjCRuntime.toJavaString(nameSegment)

        val frameRect = ObjCRuntime.msgSendStret(rectLayout, scDisplay, selFrame)
        val frameOriginX = rectOriginX.get(frameRect) as Double
        val frameOriginY = rectOriginY.get(frameRect) as Double

        return DisplayInfo(
            id = displayId,
            name = name,
            position = PhysicalPosition(frameOriginX.toInt(), frameOriginY.toInt()),
            resolution = PhysicalSize(width, height),
            scaleFactor = scaleFactor,
        )
    }

    private val rectLayout: GroupLayout = MemoryLayout.structLayout(
        JAVA_DOUBLE.withName("origin_x"),
        JAVA_DOUBLE.withName("origin_y"),
        JAVA_DOUBLE.withName("size_width"),
        JAVA_DOUBLE.withName("size_height"),
    ) as GroupLayout

    private val rectOriginX = rectLayout.varHandle(MemoryLayout.PathElement.groupElement("origin_x"))
    private val rectOriginY = rectLayout.varHandle(MemoryLayout.PathElement.groupElement("origin_y"))
    private val rectSizeWidth = rectLayout.varHandle(MemoryLayout.PathElement.groupElement("size_width"))
    private val rectSizeHeight = rectLayout.varHandle(MemoryLayout.PathElement.groupElement("size_height"))

    private fun getRectValues(rect: MemorySegment): NSRectValues {
        return NSRectValues(
            originX = rectOriginX.get(rect) as Double,
            originY = rectOriginY.get(rect) as Double,
            sizeWidth = rectSizeWidth.get(rect) as Double,
            sizeHeight = rectSizeHeight.get(rect) as Double,
        )
    }

    private class NSRectValues(
        val originX: Double,
        val originY: Double,
        val sizeWidth: Double,
        val sizeHeight: Double,
    )

    private fun scWindowToInfo(scWindow: MemorySegment): WindowInfo? {
        val isOnScreen = ObjCRuntime.msgSend(JAVA_BOOLEAN, scWindow, selIsOnScreen) as Boolean
        if (!isOnScreen) return null

        val windowId = (ObjCRuntime.msgSend(JAVA_INT, scWindow, selWindowID) as Int).toLong()

        val titleSegment = ObjCRuntime.msgSend(ADDRESS, scWindow, selTitle) as MemorySegment
        val title = if (titleSegment == MemorySegment.NULL) null else ObjCRuntime.toJavaString(titleSegment)

        var applicationName: String? = null
        val app = ObjCRuntime.msgSend(ADDRESS, scWindow, selOwningApplication) as MemorySegment
        if (app != MemorySegment.NULL) {
            val appNameSegment = ObjCRuntime.msgSend(ADDRESS, app, selApplicationName) as MemorySegment
            if (appNameSegment != MemorySegment.NULL) {
                applicationName = ObjCRuntime.toJavaString(appNameSegment)
            }
        }

        val rect = ObjCRuntime.msgSendStret(rectLayout, scWindow, selFrame)
        val r = getRectValues(rect)

        return WindowInfo(
            id = windowId,
            title = title,
            applicationName = applicationName,
            position = PhysicalPosition(r.originX.toInt(), r.originY.toInt()),
            size = PhysicalSize(r.sizeWidth.toInt(), r.sizeHeight.toInt()),
        )
    }

    private fun findDisplay(displaysArray: MemorySegment, id: DisplayId): MemorySegment? {
        val count = (ObjCRuntime.msgSend(JAVA_LONG, displaysArray, selCount) as Long).toInt()
        for (i in 0 until count) {
            val scDisplay = ObjCRuntime.msgSend(ADDRESS, displaysArray, selObjectAtIndex, i.toLong()) as MemorySegment
            val did = (ObjCRuntime.msgSend(JAVA_INT, scDisplay, selDisplayID) as Int).toLong()
            if (did == id) return scDisplay
        }
        return null
    }

    private fun findWindow(windowsArray: MemorySegment, id: WindowId): MemorySegment? {
        val count = (ObjCRuntime.msgSend(JAVA_LONG, windowsArray, selCount) as Long).toInt()
        for (i in 0 until count) {
            val scWindow = ObjCRuntime.msgSend(ADDRESS, windowsArray, selObjectAtIndex, i.toLong()) as MemorySegment
            val wid = (ObjCRuntime.msgSend(JAVA_INT, scWindow, selWindowID) as Int).toLong()
            if (wid == id) return scWindow
        }
        return null
    }

    private fun buildStream(source: CaptureSource, config: CaptureConfig): MemorySegment {
        return ObjCRuntime.autoreleasePool {
            val content = getShareableContent()
                ?: throw CaptureError.Internal(RuntimeException("Failed to get shareable content"))

            val filter = buildFilter(source, content)
            val streamConfig = buildStreamConfig(config)

            val stream = ObjCRuntime.msgSend(ADDRESS, scStreamClass, selAlloc) as MemorySegment
            val initSel = ObjCRuntime.sel("initWithFilter:configuration:delegate:delegateQueue:")
            ObjCRuntime.msgSend(ADDRESS, stream, initSel, filter, streamConfig, MemorySegment.NULL, MemorySegment.NULL) as MemorySegment
        }
    }

    private fun buildFilter(source: CaptureSource, content: MemorySegment): MemorySegment {
        return when (source) {
            is CaptureSource.Display -> {
                val displaysArray = ObjCRuntime.msgSend(ADDRESS, content, selDisplays) as MemorySegment
                val display = findDisplay(displaysArray, source.id)
                    ?: throw CaptureError.NoSuchSource(source)
                val sel = ObjCRuntime.sel("filterWithDisplay:excludingWindows:")
                ObjCRuntime.msgSend(ADDRESS, scContentFilterClass, sel, display, MemorySegment.NULL) as MemorySegment
            }
            is CaptureSource.Window -> {
                val windowsArray = ObjCRuntime.msgSend(ADDRESS, content, selWindows) as MemorySegment
                val window = findWindow(windowsArray, source.id)
                    ?: throw CaptureError.NoSuchSource(source)
                val sel = ObjCRuntime.sel("filterWithDesktopIndependentWindow:")
                ObjCRuntime.msgSend(ADDRESS, scContentFilterClass, sel, window) as MemorySegment
            }
        }
    }

    private fun buildStreamConfig(config: CaptureConfig): MemorySegment {
        val scConfig = ObjCRuntime.msgSend(ADDRESS, scStreamConfigurationClass, selAlloc) as MemorySegment
        ObjCRuntime.msgSend(ADDRESS, scConfig, selInit)

        val setShowsCursorSel = ObjCRuntime.sel("setShowsCursor:")
        ObjCRuntime.msgSend(null, scConfig, setShowsCursorSel, config.captureCursor)

        config.region?.let { region ->
            val setWidthSel = ObjCRuntime.sel("setWidth:")
            val setHeightSel = ObjCRuntime.sel("setHeight:")
            ObjCRuntime.msgSend(null, scConfig, setWidthSel, region.size.width.toLong())
            ObjCRuntime.msgSend(null, scConfig, setHeightSel, region.size.height.toLong())
        }

        val setMinIntervalSel = ObjCRuntime.sel("setMinimumFrameInterval:")
        val cmTime = ObjCRuntime.msgSend(ADDRESS, ObjCRuntime.getClass("CMTime"), ObjCRuntime.sel("CMTimeMakeWithSeconds:"),
            1.0 / config.frameRate, 600) as MemorySegment
        ObjCRuntime.msgSend(null, scConfig, setMinIntervalSel, cmTime)

        val setPixelFormatSel = ObjCRuntime.sel("setPixelFormat:")
        ObjCRuntime.msgSend(null, scConfig, setPixelFormatSel, 0x42475241) // kCVPixelFormatType_32BGRA 'BGRA'

        return scConfig
    }
}
