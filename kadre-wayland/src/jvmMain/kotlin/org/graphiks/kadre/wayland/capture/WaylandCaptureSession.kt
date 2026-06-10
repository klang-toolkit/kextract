package org.graphiks.kadre.wayland.capture

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.*
import kotlinx.coroutines.*
import org.graphiks.kadre.ffi.wayland.*
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class WaylandCaptureSession(
    source: CaptureSource,
    config: CaptureConfig,
) : CaptureSession(source, config) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val arena = Arena.ofShared()

    private var displayPtr: Long = 0L
    private var registryPtr: Long = 0L
    private var shmPtr: Long = 0L
    private var screencopyManagerPtr: Long = 0L
    private var outputPtr: Long = 0L

    private var currentFd: Int = -1
    private var currentShmPoolPtr: Long = 0L
    private var currentBufferPtr: Long = 0L
    private var currentMmapAddr: MemorySegment? = null
    private var currentMmapSize: Int = 0

    init {
        val ptr = connectWayland()
            ?: throw CaptureError.Internal(RuntimeException("Failed to connect to Wayland display"))
        displayPtr = ptr
        try {
            discoverGlobals()
        } catch (e: Exception) {
            disconnectWayland(displayPtr)
            scope.cancel()
            throw e
        }

        scope.launch {
            try {
                captureLoop()
            } catch (_: CancellationException) {
            } catch (_: Exception) {
            }
        }
    }

    private fun discoverGlobals() {
        val collector = OutputNameCollector()
        registryPtr = getRegistryProxy(displayPtr)
        if (registryPtr == 0L) throw CaptureError.Internal(RuntimeException("Failed to get registry"))

        val regListener = captureRegistryListener(collector, arena)
        if (!proxyAddListener(registryPtr, regListener))
            throw CaptureError.Internal(RuntimeException("Failed to add registry listener"))
        if (!roundtripWayland(displayPtr))
            throw CaptureError.Internal(RuntimeException("Registry roundtrip failed"))

        if (collector.screencopyManagerName < 0)
            throw CaptureError.Unsupported("zwlr_screencopy_manager_v1 not available on this compositor")
        if (collector.shmName < 0)
            throw CaptureError.Unsupported("wl_shm not available")

        val registrySeg = MemorySegment.ofAddress(registryPtr)
        val bind = wlProxyMarshalBind
            ?: throw CaptureError.Internal(RuntimeException("wl_proxy_marshal_flags not available"))

        // Bind wl_shm (version 1)
        val shmIface = wlShmInterface
            ?: throw CaptureError.Internal(RuntimeException("wl_shm_interface not found"))
        val shmNamePtr = shmIface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
        shmPtr = try {
            (bind.invokeExact(
                registrySeg, 0, shmIface, 1, 0,
                collector.shmName, shmNamePtr, 1, MemorySegment.NULL,
            ) as MemorySegment).address()
        } catch (e: Throwable) {
            throw CaptureError.Internal(RuntimeException("Failed to bind wl_shm", e))
        }
        if (shmPtr == 0L) throw CaptureError.Internal(RuntimeException("wl_shm bind returned null"))

        // Bind zwlr_screencopy_manager_v1 (version 3)
        val scIface = zwlrScreencopyManagerV1Interface
        val scNamePtr = scIface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
        screencopyManagerPtr = try {
            (bind.invokeExact(
                registrySeg, 0, scIface, 3, 0,
                collector.screencopyManagerName, scNamePtr, 3, MemorySegment.NULL,
            ) as MemorySegment).address()
        } catch (e: Throwable) {
            throw CaptureError.Internal(RuntimeException("Failed to bind zwlr_screencopy_manager_v1", e))
        }
        if (screencopyManagerPtr == 0L)
            throw CaptureError.Internal(RuntimeException("zwlr_screencopy_manager_v1 bind returned null"))

        // Find target output by index matching CaptureSource.Display.id
        val displayIndex = when (val src = source) {
            is CaptureSource.Display -> src.id.toInt()
            is CaptureSource.Window -> throw CaptureError.Unsupported(
                "Window capture via xdg-desktop-portal is not yet implemented"
            )
        }

        val outputIface = wlOutputInterface
            ?: throw CaptureError.Internal(RuntimeException("wl_output_interface not found"))
        val outputNamePtr = outputIface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)

        val outputs = collector.outputNames
        if (displayIndex < 0 || displayIndex >= outputs.size)
            throw CaptureError.NoSuchSource(source)

        val (outName, outVersion) = outputs[displayIndex]
        val boundVersion = outVersion.coerceAtMost(4)
        outputPtr = try {
            (bind.invokeExact(
                registrySeg, 0, outputIface, boundVersion, 0,
                outName, outputNamePtr, boundVersion, MemorySegment.NULL,
            ) as MemorySegment).address()
        } catch (e: Throwable) {
            throw CaptureError.Internal(RuntimeException("Failed to bind wl_output", e))
        }
        if (outputPtr == 0L)
            throw CaptureError.Internal(RuntimeException("wl_output bind returned null"))
    }

    private suspend fun captureLoop() {
        while (isActive()) {
            val framePtr = requestFrame()
            if (framePtr == 0L) {
                sleepDelay()
                continue
            }

            val frameCollector = ScreencopyFrameCollector()
            val frameListener = buildScreencopyFrameListener(frameCollector, arena)
            if (!proxyAddListener(framePtr, frameListener)) {
                proxyDestroy(framePtr)
                sleepDelay()
                continue
            }
            flushDisplay()

            if (!dispatchUntilFrameReady(frameCollector)) {
                proxyDestroy(framePtr)
                sleepDelay()
                continue
            }

            if (!createShmBufferForFrame(frameCollector)) {
                cleanupFrameResources()
                proxyDestroy(framePtr)
                sleepDelay()
                continue
            }

            val copyFn = zwlrScreencopyFrameCopy
            if (copyFn == null) {
                cleanupFrameResources()
                proxyDestroy(framePtr)
                sleepDelay()
                continue
            }
            try {
                copyFn.invokeExact(
                    MemorySegment.ofAddress(framePtr),
                    SCREENCOPY_FRAME_COPY,
                    MemorySegment.NULL,
                    3,
                    0,
                    MemorySegment.ofAddress(currentBufferPtr),
                )
            } catch (_: Throwable) {
                cleanupFrameResources()
                proxyDestroy(framePtr)
                sleepDelay()
                continue
            }
            flushDisplay()

            dispatchUntil { frameCollector.complete }

            handleFrameResult(frameCollector, framePtr)
        }
    }

    private suspend fun isActive(): Boolean =
        scope.isActive

    private suspend fun requestFrame(): Long {
        val captureFn = zwlrScreencopyManagerCaptureOutput ?: return 0L
        return try {
            val overlayCursor = if (config.captureCursor) 1 else 0
            (captureFn.invokeExact(
                MemorySegment.ofAddress(screencopyManagerPtr),
                SCREENCOPY_MANAGER_CAPTURE_OUTPUT,
                zwlrScreencopyFrameV1Interface,
                3,
                0,
                overlayCursor,
                MemorySegment.ofAddress(outputPtr),
                MemorySegment.NULL,
            ) as MemorySegment).address()
        } catch (_: Throwable) { 0L }
    }

    private fun dispatchUntilFrameReady(collector: ScreencopyFrameCollector): Boolean {
        val deadline = System.currentTimeMillis() + 5000L
        while (!collector.complete && collector.format < 0) {
            if (System.currentTimeMillis() > deadline) return false
            if (!dispatchWayland(displayPtr)) return false
        }
        return collector.format >= 0
    }

    private fun dispatchUntil(condition: () -> Boolean) {
        val deadline = System.currentTimeMillis() + 10000L
        while (!condition()) {
            if (System.currentTimeMillis() > deadline) return
            dispatchWayland(displayPtr)
        }
    }

    private fun createShmBufferForFrame(collector: ScreencopyFrameCollector): Boolean {
        val dataSize = collector.stride * collector.height
        val fd = createMemFd("kadre-capture", dataSize)
        if (fd < 0) return false
        currentFd = fd

        val mmapAddr = mmapFd(fd, dataSize)
        if (mmapAddr == null) {
            closeFd(fd)
            currentFd = -1
            return false
        }
        currentMmapAddr = mmapAddr
        currentMmapSize = dataSize

        val poolPtr = createShmPool(shmPtr, fd, dataSize)
        if (poolPtr == 0L) {
            cleanupFrameResources()
            return false
        }
        currentShmPoolPtr = poolPtr

        val bufferPtr = createShmBuffer(
            shmPtr, poolPtr, 0,
            collector.width, collector.height,
            collector.stride, collector.format,
        )
        if (bufferPtr == 0L) {
            cleanupFrameResources()
            return false
        }
        currentBufferPtr = bufferPtr
        return true
    }

    private fun handleFrameResult(collector: ScreencopyFrameCollector, framePtr: Long) {
        if (!collector.failed) {
            val dataSize = collector.stride * collector.height
            val pixelData = ByteArray(dataSize)
            val mmap = currentMmapAddr
            if (mmap != null) {
                MemorySegment.copy(mmap, ValueLayout.JAVA_BYTE, 0, pixelData, 0, dataSize)
            }

            val finalData = convertPixelFormat(pixelData, collector.format, config.pixelFormat)
            val sourceFormat = mapFormat(collector.format)

            val tvSec = (collector.tvSecHi.toLong() shl 32) or (collector.tvSecLo.toLong() and 0xFFFFFFFFL)
            val tsNanos = tvSec * 1_000_000_000L + collector.tvNsec.toLong()

            _frames.tryEmit(CaptureFrame(
                size = PhysicalSize(collector.width, collector.height),
                format = if (finalData !== pixelData) config.pixelFormat else sourceFormat,
                stride = collector.stride,
                data = finalData,
                timestampNanos = tsNanos,
            ))
        }
        cleanupFrameResources()
        proxyDestroy(framePtr)
    }

    private fun cleanupFrameResources() {
        if (currentBufferPtr != 0L) {
            destroyWaylandProxy(currentBufferPtr, 0, 1)
            currentBufferPtr = 0L
        }
        if (currentShmPoolPtr != 0L) {
            destroyWaylandProxy(currentShmPoolPtr, 1, 1)
            currentShmPoolPtr = 0L
        }
        currentMmapAddr?.let { munmap(it, currentMmapSize) }
        currentMmapAddr = null
        currentMmapSize = 0
        if (currentFd >= 0) {
            closeFd(currentFd)
            currentFd = -1
        }
    }

    private fun destroyWaylandProxy(proxy: Long, opcode: Int, version: Int) {
        val marshal = wlProxyMarshalFlagsVoid ?: return
        try {
            marshal.invokeExact(MemorySegment.ofAddress(proxy), opcode, MemorySegment.NULL, version, 0)
        } catch (_: Throwable) {}
    }

    private suspend fun sleepDelay() {
        val delayMs = if (config.frameRate > 0) 1000L / config.frameRate else 33L
        delay(delayMs)
    }

    private fun flushDisplay() {
        val flush = wlDisplayFlush ?: return
        try { flush.invokeExact(MemorySegment.ofAddress(displayPtr)) as Int } catch (_: Throwable) {}
    }

    private fun mapFormat(wlFormat: Int): PixelFormat = when (wlFormat) {
        WL_SHM_FORMAT_XRGB8888 -> PixelFormat.BGRX8
        WL_SHM_FORMAT_ARGB8888 -> PixelFormat.BGRA8
        WL_SHM_FORMAT_XBGR8888 -> PixelFormat.BGRX8
        WL_SHM_FORMAT_ABGR8888 -> PixelFormat.RGBA8
        else -> PixelFormat.BGRA8
    }

    private fun convertPixelFormat(data: ByteArray, srcWlFormat: Int, targetFormat: PixelFormat): ByteArray {
        if (targetFormat == PixelFormat.RGBA8) {
            val isSourceBgra = when (srcWlFormat) {
                WL_SHM_FORMAT_XRGB8888, WL_SHM_FORMAT_ARGB8888 -> true
                else -> false
            }
            if (isSourceBgra) return bgraToRgba(data)
        }
        if (targetFormat == PixelFormat.BGRA8) {
            val isSourceRgba = when (srcWlFormat) {
                WL_SHM_FORMAT_XBGR8888, WL_SHM_FORMAT_ABGR8888 -> true
                else -> false
            }
            if (isSourceRgba) return rgbaToBgra(data)
        }
        return data
    }

    override fun close() {
        scope.cancel()
        cleanupFrameResources()
        if (registryPtr != 0L) proxyDestroy(registryPtr)
        if (shmPtr != 0L) proxyDestroy(shmPtr)
        if (screencopyManagerPtr != 0L) proxyDestroy(screencopyManagerPtr)
        if (outputPtr != 0L) proxyDestroy(outputPtr)
        if (displayPtr != 0L) disconnectWayland(displayPtr)
        arena.close()
    }
}

internal fun bgraToRgba(data: ByteArray): ByteArray {
    val result = ByteArray(data.size)
    for (i in data.indices step 4) {
        result[i] = data[i + 2]
        result[i + 1] = data[i + 1]
        result[i + 2] = data[i]
        result[i + 3] = data[i + 3]
    }
    return result
}

internal fun rgbaToBgra(data: ByteArray): ByteArray {
    val result = ByteArray(data.size)
    for (i in data.indices step 4) {
        result[i] = data[i + 2]
        result[i + 1] = data[i + 1]
        result[i + 2] = data[i]
        result[i + 3] = data[i + 3]
    }
    return result
}
