package org.graphiks.kadre.wayland.capture

import org.graphiks.kadre.wayland.buildWaylandInterface
import org.graphiks.kadre.wayland.libWaylandClient
import org.graphiks.kadre.wayland.upcallStub
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

// ── Interface structs (protocol extension, not in libwayland-client) ──────────

internal val zwlrScreencopyManagerV1Interface: MemorySegment by lazy {
    buildWaylandInterface("zwlr_screencopy_manager_v1", version = 3, methodCount = 3, eventCount = 0)
}

internal val zwlrScreencopyFrameV1Interface: MemorySegment by lazy {
    buildWaylandInterface("zwlr_screencopy_frame_v1", version = 3, methodCount = 2, eventCount = 4)
}

// ── Opcodes ────────────────────────────────────────────────────────────────────

internal const val SCREENCOPY_MANAGER_CAPTURE_OUTPUT: Int = 0
internal const val SCREENCOPY_MANAGER_CAPTURE_OUTPUT_REGION: Int = 1
internal const val SCREENCOPY_MANAGER_DESTROY: Int = 2

internal const val SCREENCOPY_FRAME_COPY: Int = 0
internal const val SCREENCOPY_FRAME_DESTROY: Int = 1

internal const val SCREENCOPY_FRAME_EVENT_BUFFER: Int = 0
internal const val SCREENCOPY_FRAME_EVENT_FLAGS: Int = 1
internal const val SCREENCOPY_FRAME_EVENT_READY: Int = 2
internal const val SCREENCOPY_FRAME_EVENT_FAILED: Int = 3

// ── wl_shm format constants ────────────────────────────────────────────────────

internal const val WL_SHM_FORMAT_XRGB8888: Int = 1
internal const val WL_SHM_FORMAT_ARGB8888: Int = 2
internal const val WL_SHM_FORMAT_XBGR8888: Int = 5
internal const val WL_SHM_FORMAT_ABGR8888: Int = 6

// ── Frame flags ────────────────────────────────────────────────────────────────

internal const val ZWLR_SCREENCOPY_FRAME_FLAGS_Y_INVERT: Int = 1

// ── wl_proxy_marshal_flags for zwlr_screencopy_manager_v1.capture_output ──────

internal val zwlrScreencopyManagerCaptureOutput: MethodHandle? by lazy {
    libWaylandDowncall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // proxy (zwlr_screencopy_manager_v1*)
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // &zwlr_screencopy_frame_v1_interface
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // arg: overlay_cursor (int)
            ValueLayout.ADDRESS,   // arg: output (wl_output*)
            ValueLayout.ADDRESS,   // new_id = NULL
        ))
}

// ── wl_proxy_marshal_flags for zwlr_screencopy_frame_v1.copy(buffer) ─────────

internal val zwlrScreencopyFrameCopy: MethodHandle? by lazy {
    libWaylandDowncall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // proxy (zwlr_screencopy_frame_v1*)
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg: wl_buffer*
        ))
}

// ── Listener for zwlr_screencopy_frame_v1 events ───────────────────────────────
//
// struct zwlr_screencopy_frame_v1_listener {
//     void (*buffer)(void*, zwlr_screencopy_frame_v1*, uint32_t, uint32_t, uint32_t, uint32_t);
//     void (*flags)(void*, zwlr_screencopy_frame_v1*, uint32_t);
//     void (*ready)(void*, zwlr_screencopy_frame_v1*, uint32_t, uint32_t, uint32_t);
//     void (*failed)(void*, zwlr_screencopy_frame_v1*);
// };

internal class ScreencopyFrameCollector {
    var format: Int = -1
    var width: Int = 0
    var height: Int = 0
    var stride: Int = 0
    var flags: Int = 0
    var tvSecHi: Int = 0
    var tvSecLo: Int = 0
    var tvNsec: Int = 0
    var failed: Boolean = false
    var complete: Boolean = false

    @Suppress("UNUSED_PARAMETER")
    fun onBuffer(data: MemorySegment, frame: MemorySegment, format: Int, width: Int, height: Int, stride: Int) {
        this.format = format
        this.width = width
        this.height = height
        this.stride = stride
    }

    @Suppress("UNUSED_PARAMETER")
    fun onFlags(data: MemorySegment, frame: MemorySegment, flags: Int) {
        this.flags = flags
    }

    @Suppress("UNUSED_PARAMETER")
    fun onReady(data: MemorySegment, frame: MemorySegment, tvSecHi: Int, tvSecLo: Int, tvNsec: Int) {
        this.tvSecHi = tvSecHi
        this.tvSecLo = tvSecLo
        this.tvNsec = tvNsec
        this.complete = true
    }

    @Suppress("UNUSED_PARAMETER")
    fun onFailed(data: MemorySegment, frame: MemorySegment) {
        this.failed = true
        this.complete = true
    }
}

internal fun buildScreencopyFrameListener(
    collector: ScreencopyFrameCollector,
    arena: Arena,
): MemorySegment {
    val lookup = MethodHandles.lookup()
    val ptrSize = ValueLayout.ADDRESS.byteSize()

    val onBufferStub = upcallStub(
        lookup.findVirtual(ScreencopyFrameCollector::class.java, "onBuffer",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
        arena,
    )
    val onFlagsStub = upcallStub(
        lookup.findVirtual(ScreencopyFrameCollector::class.java, "onFlags",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
        arena,
    )
    val onReadyStub = upcallStub(
        lookup.findVirtual(ScreencopyFrameCollector::class.java, "onReady",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
        arena,
    )
    val onFailedStub = upcallStub(
        lookup.findVirtual(ScreencopyFrameCollector::class.java, "onFailed",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
        arena,
    )

    val listener = arena.allocate(ptrSize * 4)
    listener.set(ValueLayout.ADDRESS, 0L, onBufferStub)
    listener.set(ValueLayout.ADDRESS, ptrSize * 1, onFlagsStub)
    listener.set(ValueLayout.ADDRESS, ptrSize * 2, onReadyStub)
    listener.set(ValueLayout.ADDRESS, ptrSize * 3, onFailedStub)
    return listener
}

// ── Output listener for wl_output events ───────────────────────────────────────
//
// struct wl_output_listener {
//     void (*geometry)(void*, wl_output*, int32_t, int32_t, int32_t, int32_t, int32_t, const char*, const char*, int32_t);
//     void (*mode)(void*, wl_output*, uint32_t, int32_t, int32_t, int32_t);
//     void (*done)(void*, wl_output*);
//     void (*scale)(void*, wl_output*, int32_t);
//     void (*name)(void*, wl_output*, const char*);
//     void (*description)(void*, wl_output*, const char*);
// };

internal class OutputDataCollector(
    val outputPtr: Long,
) {
    var name: String? = null
    var description: String? = null
    var geometryX: Int = 0
    var geometryY: Int = 0
    var physicalWidth: Int = 0
    var physicalHeight: Int = 0
    var modeWidth: Int = 0
    var modeHeight: Int = 0
    var scale: Int = 1
    var doneReceived: Boolean = false

    @Suppress("UNUSED_PARAMETER")
    fun onGeometry(data: MemorySegment, output: MemorySegment, x: Int, y: Int,
                   physW: Int, physH: Int, subpixel: Int, make: MemorySegment,
                   model: MemorySegment, transform: Int) {
        geometryX = x
        geometryY = y
        physicalWidth = physW
        physicalHeight = physH
    }

    @Suppress("UNUSED_PARAMETER")
    fun onMode(data: MemorySegment, output: MemorySegment, flags: Int, width: Int, height: Int, refresh: Int) {
        modeWidth = width
        modeHeight = height
    }

    @Suppress("UNUSED_PARAMETER")
    fun onDone(data: MemorySegment, output: MemorySegment) {
        doneReceived = true
    }

    @Suppress("UNUSED_PARAMETER")
    fun onScale(data: MemorySegment, output: MemorySegment, factor: Int) {
        scale = factor
    }

    @Suppress("UNUSED_PARAMETER")
    fun onName(data: MemorySegment, output: MemorySegment, namePtr: MemorySegment) {
        name = try { namePtr.reinterpret(256).getString(0) } catch (_: Throwable) { null }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onDescription(data: MemorySegment, output: MemorySegment, descPtr: MemorySegment) {
        description = try { descPtr.reinterpret(512).getString(0) } catch (_: Throwable) { null }
    }
}

internal fun buildOutputListener(
    collector: OutputDataCollector,
    arena: Arena,
): MemorySegment {
    val lookup = MethodHandles.lookup()
    val ptrSize = ValueLayout.ADDRESS.byteSize()

    val geometryStub = upcallStub(
        lookup.findVirtual(OutputDataCollector::class.java, "onGeometry",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType, MemorySegment::class.java,
                MemorySegment::class.java, Int::class.javaPrimitiveType)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT,
            ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
        arena,
    )
    val modeStub = upcallStub(
        lookup.findVirtual(OutputDataCollector::class.java, "onMode",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
        arena,
    )
    val doneStub = upcallStub(
        lookup.findVirtual(OutputDataCollector::class.java, "onDone",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
        arena,
    )
    val scaleStub = upcallStub(
        lookup.findVirtual(OutputDataCollector::class.java, "onScale",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
        arena,
    )
    val nameStub = upcallStub(
        lookup.findVirtual(OutputDataCollector::class.java, "onName",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                MemorySegment::class.java)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
        arena,
    )
    val descStub = upcallStub(
        lookup.findVirtual(OutputDataCollector::class.java, "onDescription",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                MemorySegment::class.java)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
        arena,
    )

    val listener = arena.allocate(ptrSize * 6)
    listener.set(ValueLayout.ADDRESS, 0L, geometryStub)
    listener.set(ValueLayout.ADDRESS, ptrSize * 1, modeStub)
    listener.set(ValueLayout.ADDRESS, ptrSize * 2, doneStub)
    listener.set(ValueLayout.ADDRESS, ptrSize * 3, scaleStub)
    listener.set(ValueLayout.ADDRESS, ptrSize * 4, nameStub)
    listener.set(ValueLayout.ADDRESS, ptrSize * 5, descStub)
    return listener
}

// ── Global collector for registry discovery ─────────────────────────────────────

internal class OutputNameCollector {
    val outputNames = mutableListOf<Pair<Int, Int>>()
    var screencopyManagerName: Int = -1
    var shmName: Int = -1

    @Suppress("UNUSED_PARAMETER")
    fun onGlobal(data: MemorySegment, registry: MemorySegment, name: Int, iface: MemorySegment, version: Int) {
        val ifaceName = try { iface.reinterpret(128).getString(0) } catch (_: Throwable) { return }
        when (ifaceName) {
            "wl_output" -> outputNames.add(name to version)
            "zwlr_screencopy_manager_v1" -> if (screencopyManagerName < 0) screencopyManagerName = name
            "wl_shm" -> if (shmName < 0) shmName = name
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onGlobalRemove(data: MemorySegment, registry: MemorySegment, name: Int) {}
}

internal fun captureRegistryListener(
    collector: OutputNameCollector,
    arena: Arena,
): MemorySegment {
    val lookup = MethodHandles.lookup()
    val ptrSize = ValueLayout.ADDRESS.byteSize()

    val globalStub = upcallStub(
        lookup.findVirtual(OutputNameCollector::class.java, "onGlobal",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType, MemorySegment::class.java,
                Int::class.javaPrimitiveType)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
        arena,
    )
    val globalRemoveStub = upcallStub(
        lookup.findVirtual(OutputNameCollector::class.java, "onGlobalRemove",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType)).bindTo(collector),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
        arena,
    )

    val listener = arena.allocate(ptrSize * 2)
    listener.set(ValueLayout.ADDRESS, 0L, globalStub)
    listener.set(ValueLayout.ADDRESS, ptrSize * 1, globalRemoveStub)
    return listener
}

// ── Registry bind helper using wl_proxy_marshal_flags ───────────────────────────

internal fun registryBind(
    registry: MemorySegment,
    iface: MemorySegment,
    name: Int,
    requestedVersion: Int,
): Long {
    val bind = org.graphiks.kadre.wayland.wlProxyMarshalBind ?: return 0L
    return try {
        val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
        val version = requestedVersion.coerceAtMost(4)
        (bind.invokeExact(
            registry, 0, iface, version, 0,
            name, namePtr, version, MemorySegment.NULL,
        ) as MemorySegment).address()
    } catch (_: Throwable) { 0L }
}

// ── Wayland connector helper ────────────────────────────────────────────────────

internal fun connectWayland(): Long? {
    val connect = org.graphiks.kadre.wayland.wlDisplayConnect ?: return null
    return try {
        val result = connect.invokeExact(MemorySegment.NULL) as MemorySegment
        if (result == MemorySegment.NULL || result.address() == 0L) null
        else result.address()
    } catch (_: Throwable) { null }
}

internal fun disconnectWayland(ptr: Long) {
    val disconnect = org.graphiks.kadre.wayland.wlDisplayDisconnect ?: return
    try { disconnect.invokeExact(MemorySegment.ofAddress(ptr)) } catch (_: Throwable) {}
}

internal fun roundtripWayland(display: Long): Boolean {
    val rt = org.graphiks.kadre.wayland.wlDisplayRoundtrip ?: return false
    return try { (rt.invokeExact(MemorySegment.ofAddress(display)) as Int) >= 0 } catch (_: Throwable) { false }
}

internal fun dispatchWayland(display: Long): Boolean {
    val dispatch = org.graphiks.kadre.wayland.wlDisplayDispatch ?: return false
    return try { (dispatch.invokeExact(MemorySegment.ofAddress(display)) as Int) >= 0 } catch (_: Throwable) { false }
}

internal fun getRegistryProxy(display: Long): Long {
    val marshalNewId = org.graphiks.kadre.wayland.wlProxyMarshalNewId ?: return 0L
    val registryIface = org.graphiks.kadre.wayland.wlRegistryInterface ?: return 0L
    val getVersion = org.graphiks.kadre.wayland.wlProxyGetVersion ?: return 0L
    val displaySeg = MemorySegment.ofAddress(display)
    return try {
        val version = getVersion.invokeExact(displaySeg) as Int
        (marshalNewId.invokeExact(displaySeg, 1, registryIface, version, 0, MemorySegment.NULL) as MemorySegment).address()
    } catch (_: Throwable) { 0L }
}

internal fun proxyAddListener(proxy: Long, listener: MemorySegment): Boolean {
    val add = org.graphiks.kadre.wayland.wlProxyAddListener ?: return false
    return try {
        (add.invokeExact(MemorySegment.ofAddress(proxy), listener, MemorySegment.NULL) as Int) == 0
    } catch (_: Throwable) { false }
}

internal fun proxyDestroy(proxy: Long) {
    val destroy = org.graphiks.kadre.wayland.wlProxyDestroy ?: return
    try { destroy.invokeExact(MemorySegment.ofAddress(proxy)) } catch (_: Throwable) {}
}

// ── SHM helpers ─────────────────────────────────────────────────────────────────

internal fun createShmBuffer(shmPtr: Long, poolPtr: Long, offset: Int, width: Int, height: Int, stride: Int, format: Int): Long {
    val createBuffer = org.graphiks.kadre.wayland.wlShmPoolCreateBuffer ?: return 0L
    val bufferIface = org.graphiks.kadre.wayland.wlBufferInterface ?: return 0L
    return try {
        (createBuffer.invokeExact(
            MemorySegment.ofAddress(poolPtr), 0, bufferIface, 1, 0,
            offset, width, height, stride, format, MemorySegment.NULL,
        ) as MemorySegment).address()
    } catch (_: Throwable) { 0L }
}

internal fun createShmPool(shmPtr: Long, fd: Int, size: Int): Long {
    val createPool = org.graphiks.kadre.wayland.wlShmCreatePool ?: return 0L
    val poolIface = org.graphiks.kadre.wayland.wlShmPoolInterface ?: return 0L
    return try {
        (createPool.invokeExact(
            MemorySegment.ofAddress(shmPtr), 0, poolIface, 1, 0,
            fd, size, MemorySegment.NULL,
        ) as MemorySegment).address()
    } catch (_: Throwable) { 0L }
}

internal fun createMemFd(name: String, size: Int): Int {
    val memfd = org.graphiks.kadre.wayland.nativeMemfdCreate ?: return -1
    val ftruncate = org.graphiks.kadre.wayland.nativeFtruncate ?: return -1
    return try {
        Arena.ofConfined().use { arena ->
            val nameSeg = arena.allocateFrom(name)
            val fd = memfd.invokeExact(nameSeg, 0) as Int
            if (fd < 0) return -1
            ftruncate.invokeExact(fd, size.toLong()) as Int
            fd
        }
    } catch (_: Throwable) { -1 }
}

internal fun mmapFd(fd: Int, size: Int): MemorySegment? {
    val mmap = org.graphiks.kadre.wayland.nativeMmap ?: return null
    return try {
        val addr = mmap.invokeExact(
            MemorySegment.NULL, size.toLong(),
            org.graphiks.kadre.wayland.PROT_READ or org.graphiks.kadre.wayland.PROT_WRITE,
            org.graphiks.kadre.wayland.MAP_SHARED, fd, 0L,
        ) as MemorySegment
        if (addr.address() == org.graphiks.kadre.wayland.MAP_FAILED_PTR) null else addr
    } catch (_: Throwable) { null }
}

internal fun munmap(addr: MemorySegment, size: Int) {
    val unmap = org.graphiks.kadre.wayland.nativeMunmap ?: return
    try { unmap.invokeExact(addr, size.toLong()) as Int } catch (_: Throwable) {}
}

internal fun closeFd(fd: Int) {
    val close = org.graphiks.kadre.wayland.nativeClose ?: return
    try { close.invokeExact(fd) as Int } catch (_: Throwable) {}
}

/** Downcall helper: looks up a symbol in libWaylandClient. */
internal fun libWaylandDowncall(name: String, desc: FunctionDescriptor): MethodHandle? {
    val lib = libWaylandClient ?: return null
    val linker = java.lang.foreign.Linker.nativeLinker()
    return lib.find(name).map { linker.downcallHandle(it, desc) }.orElse(null)
}
