package org.graphiks.kadre.ffi.wayland

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

val zwlrScreencopyManagerV1Interface: MemorySegment by lazy {
    buildWaylandInterface("zwlr_screencopy_manager_v1", version = 3, methodCount = 3, eventCount = 0)
}

val zwlrScreencopyFrameV1Interface: MemorySegment by lazy {
    buildWaylandInterface("zwlr_screencopy_frame_v1", version = 3, methodCount = 2, eventCount = 4)
}

const val SCREENCOPY_MANAGER_CAPTURE_OUTPUT: Int = 0
const val SCREENCOPY_MANAGER_CAPTURE_OUTPUT_REGION: Int = 1
const val SCREENCOPY_MANAGER_DESTROY: Int = 2

const val SCREENCOPY_FRAME_COPY: Int = 0
const val SCREENCOPY_FRAME_DESTROY: Int = 1

const val SCREENCOPY_FRAME_EVENT_BUFFER: Int = 0
const val SCREENCOPY_FRAME_EVENT_FLAGS: Int = 1
const val SCREENCOPY_FRAME_EVENT_READY: Int = 2
const val SCREENCOPY_FRAME_EVENT_FAILED: Int = 3

const val WL_SHM_FORMAT_XRGB8888: Int = 1
const val WL_SHM_FORMAT_ARGB8888: Int = 2
const val WL_SHM_FORMAT_XBGR8888: Int = 5
const val WL_SHM_FORMAT_ABGR8888: Int = 6

const val ZWLR_SCREENCOPY_FRAME_FLAGS_Y_INVERT: Int = 1

val zwlrScreencopyManagerCaptureOutput: MethodHandle? by lazy {
    libWaylandDowncall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT,
            ValueLayout.JAVA_INT,
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
        ))
}

val zwlrScreencopyFrameCopy: MethodHandle? by lazy {
    libWaylandDowncall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT,
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
        ))
}

class ScreencopyFrameCollector {
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

fun buildScreencopyFrameListener(
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

class OutputDataCollector(
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

fun buildOutputListener(
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

class OutputNameCollector {
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

fun captureRegistryListener(
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

fun registryBind(
    registry: MemorySegment,
    iface: MemorySegment,
    name: Int,
    requestedVersion: Int,
): Long {
    val bind = wlProxyMarshalBind ?: return 0L
    return try {
        val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
        val version = requestedVersion.coerceAtMost(4)
        (bind.invokeExact(
            registry, 0, iface, version, 0,
            name, namePtr, version, MemorySegment.NULL,
        ) as MemorySegment).address()
    } catch (_: Throwable) { 0L }
}

fun connectWayland(): Long? {
    val connect = wlDisplayConnect ?: return null
    return try {
        val result = connect.invokeExact(MemorySegment.NULL) as MemorySegment
        if (result == MemorySegment.NULL || result.address() == 0L) null
        else result.address()
    } catch (_: Throwable) { null }
}

fun disconnectWayland(ptr: Long) {
    val disconnect = wlDisplayDisconnect ?: return
    try { disconnect.invokeExact(MemorySegment.ofAddress(ptr)) } catch (_: Throwable) {}
}

fun roundtripWayland(display: Long): Boolean {
    val rt = wlDisplayRoundtrip ?: return false
    return try { (rt.invokeExact(MemorySegment.ofAddress(display)) as Int) >= 0 } catch (_: Throwable) { false }
}

fun dispatchWayland(display: Long): Boolean {
    val dispatch = wlDisplayDispatch ?: return false
    return try { (dispatch.invokeExact(MemorySegment.ofAddress(display)) as Int) >= 0 } catch (_: Throwable) { false }
}

fun getRegistryProxy(display: Long): Long {
    val marshalNewId = wlProxyMarshalNewId ?: return 0L
    val registryIface = wlRegistryInterface ?: return 0L
    val getVersion = wlProxyGetVersion ?: return 0L
    val displaySeg = MemorySegment.ofAddress(display)
    return try {
        val version = getVersion.invokeExact(displaySeg) as Int
        (marshalNewId.invokeExact(displaySeg, 1, registryIface, version, 0, MemorySegment.NULL) as MemorySegment).address()
    } catch (_: Throwable) { 0L }
}

fun proxyAddListener(proxy: Long, listener: MemorySegment): Boolean {
    val add = wlProxyAddListener ?: return false
    return try {
        (add.invokeExact(MemorySegment.ofAddress(proxy), listener, MemorySegment.NULL) as Int) == 0
    } catch (_: Throwable) { false }
}

fun proxyDestroy(proxy: Long) {
    val destroy = wlProxyDestroy ?: return
    try { destroy.invokeExact(MemorySegment.ofAddress(proxy)) } catch (_: Throwable) {}
}

fun createShmBuffer(shmPtr: Long, poolPtr: Long, offset: Int, width: Int, height: Int, stride: Int, format: Int): Long {
    val createBuffer = wlShmPoolCreateBuffer ?: return 0L
    val bufferIface = wlBufferInterface ?: return 0L
    return try {
        (createBuffer.invokeExact(
            MemorySegment.ofAddress(poolPtr), 0, bufferIface, 1, 0,
            offset, width, height, stride, format, MemorySegment.NULL,
        ) as MemorySegment).address()
    } catch (_: Throwable) { 0L }
}

fun createShmPool(shmPtr: Long, fd: Int, size: Int): Long {
    val createPool = wlShmCreatePool ?: return 0L
    val poolIface = wlShmPoolInterface ?: return 0L
    return try {
        (createPool.invokeExact(
            MemorySegment.ofAddress(shmPtr), 0, poolIface, 1, 0,
            fd, size, MemorySegment.NULL,
        ) as MemorySegment).address()
    } catch (_: Throwable) { 0L }
}

fun createMemFd(name: String, size: Int): Int {
    val memfd = nativeMemfdCreate ?: return -1
    val ftruncate = nativeFtruncate ?: return -1
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

fun mmapFd(fd: Int, size: Int): MemorySegment? {
    val mmap = nativeMmap ?: return null
    return try {
        val addr = mmap.invokeExact(
            MemorySegment.NULL, size.toLong(),
            PROT_READ or PROT_WRITE,
            MAP_SHARED, fd, 0L,
        ) as MemorySegment
        if (addr.address() == MAP_FAILED_PTR) null else addr
    } catch (_: Throwable) { null }
}

fun munmap(addr: MemorySegment, size: Int) {
    val unmap = nativeMunmap ?: return
    try { unmap.invokeExact(addr, size.toLong()) as Int } catch (_: Throwable) {}
}

fun closeFd(fd: Int) {
    val close = nativeClose ?: return
    try { close.invokeExact(fd) as Int } catch (_: Throwable) {}
}

fun libWaylandDowncall(name: String, desc: FunctionDescriptor): MethodHandle? {
    val lib = libWaylandClient ?: return null
    val linker = java.lang.foreign.Linker.nativeLinker()
    return lib.find(name).map { linker.downcallHandle(it, desc) }.orElse(null)
}
