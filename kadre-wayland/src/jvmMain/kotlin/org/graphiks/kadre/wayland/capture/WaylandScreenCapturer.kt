package org.graphiks.kadre.wayland.capture

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.*
import org.graphiks.kadre.wayland.wlOutputInterface
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class WaylandScreenCapturer : ScreenCapturer {

    override suspend fun enumerateDisplays(): List<DisplayInfo> {
        val displayPtr = connectWayland() ?: return emptyList()
        val arena = Arena.ofShared()
        return try {
            enumerateDisplaysInner(displayPtr, arena)
        } catch (_: Throwable) {
            emptyList()
        } finally {
            arena.close()
            disconnectWayland(displayPtr)
        }
    }

    private fun enumerateDisplaysInner(displayPtr: Long, arena: Arena): List<DisplayInfo> {
        val collector = OutputNameCollector()
        val registryPtr = getRegistryProxy(displayPtr)
        if (registryPtr == 0L) return emptyList()

        val regListener = captureRegistryListener(collector, arena)
        if (!proxyAddListener(registryPtr, regListener)) return emptyList()
        if (!roundtripWayland(displayPtr)) return emptyList()
        if (collector.outputNames.isEmpty()) return emptyList()

        val outputIface = wlOutputInterface ?: return emptyList()
        val bind = org.graphiks.kadre.wayland.wlProxyMarshalBind ?: return emptyList()
        val registrySeg = MemorySegment.ofAddress(registryPtr)

        val outputCollectors = collector.outputNames.mapNotNull { (name, version) ->
            val boundVersion = version.coerceAtMost(4)
            val namePtr = outputIface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
            val outputPtr = try {
                (bind.invokeExact(
                    registrySeg, 0, outputIface, boundVersion, 0,
                    name, namePtr, boundVersion, MemorySegment.NULL,
                ) as MemorySegment).address()
            } catch (_: Throwable) { 0L }
            if (outputPtr == 0L) return@mapNotNull null
            val oc = OutputDataCollector(outputPtr)
            val outputListener = buildOutputListener(oc, arena)
            if (proxyAddListener(outputPtr, outputListener)) oc else null
        }

        roundtripWayland(displayPtr)

        return outputCollectors.filter { it.doneReceived }.mapIndexed { index, oc ->
            DisplayInfo(
                id = index.toLong(),
                name = oc.name ?: "Output-$index",
                position = PhysicalPosition(oc.geometryX, oc.geometryY),
                resolution = PhysicalSize(
                    if (oc.modeWidth > 0) oc.modeWidth else 1920,
                    if (oc.modeHeight > 0) oc.modeHeight else 1080,
                ),
                scaleFactor = oc.scale.toDouble(),
            )
        }
    }

    override suspend fun enumerateWindows(): List<WindowInfo> = emptyList()

    override suspend fun createSession(
        source: CaptureSource,
        config: CaptureConfig,
    ): CaptureSession {
        when (source) {
            is CaptureSource.Display -> {}
            is CaptureSource.Window -> throw CaptureError.Unsupported(
                "Window capture via xdg-desktop-portal is not yet implemented"
            )
        }
        return WaylandCaptureSession(source, config)
    }

    override suspend fun requestPermission(): CapturePermission =
        CapturePermission.Pending

    override fun permissionStatus(): CapturePermission =
        CapturePermission.Pending
}
