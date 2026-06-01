/**
 * Offscreen GPU capture on Linux.
 *
 * Unlike macOS/Windows where the surface is built "by hand" (hidden CAMetalLayer /
 * HWND), creating a Wayland `wl_surface` (or an X11 window) by hand in FFM is
 * heavy. So we reuse the **kadre EventLoop**: it already knows how to create a
 * Wayland/X11 window and exposes its [RawWindowHandle]. We derive the wgpu surface from it, render into
 * an offscreen texture, read back the framebuffer (common [renderSurfaceToPng] pipeline),
 * then exit the loop.
 *
 * CI requirements (headless):
 * - a headless Wayland compositor (e.g. `weston --backend=headless`) with `WAYLAND_DISPLAY`
 *   and `XDG_RUNTIME_DIR` set, OR a virtual X11 server (`Xvfb`);
 * - software Vulkan (Mesa lavapipe) via `VK_ICD_FILENAMES` — the runners have no GPU.
 *
 * wgpu backend: `Vulkan` (lavapipe).
 */
package org.graphiks.kadre.samples.hellotriangle

import ffi.JvmNativeAddress
import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.WindowEvent
import io.ygdrasil.webgpu.WGPU
import io.ygdrasil.webgpu.WGPUInstanceBackend
import io.ygdrasil.webgpu.WGPULowLevelApi
import java.lang.foreign.MemorySegment

/** Wraps a native address (pointer) into a [JvmNativeAddress] for wgpu4k. */
private fun nativeAddr(addr: Long): JvmNativeAddress =
    JvmNativeAddress(MemorySegment.ofAddress(addr))

/**
 * Linux capture: creates a window via the kadre EventLoop (Wayland or X11 depending on the
 * detected backend), derives the wgpu surface from its [RawWindowHandle], renders + readback, then exits.
 */
@OptIn(WGPULowLevelApi::class)
internal fun captureLinux(path: String) {
    var captureError: Throwable? = null

    val handler = object : ApplicationHandler {
        override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
            try {
                val window = eventLoop.createWindow(
                    WindowAttributes(
                        title = "kadre-capture",
                        size = PhysicalSize(CAPTURE_WIDTH, CAPTURE_HEIGHT),
                        visible = false,
                        resizable = false,
                    )
                )

                // Primary = Vulkan + GL: lets wgpu fall back to GL/EGL (llvmpipe) if
                // Vulkan surface creation fails (lavapipe has limited WSI support).
                val instance = WGPU.createInstance(WGPUInstanceBackend.Primary)
                    ?: error("Échec création WGPU Instance (Primary)")

                val surface = when (val handle = window.rawWindowHandle) {
                    is RawWindowHandle.Wayland ->
                        instance.getSurfaceFromWaylandWindow(
                            nativeAddr(handle.display),
                            nativeAddr(handle.surface),
                        )
                    is RawWindowHandle.Xlib ->
                        instance.getSurfaceFromX11Window(
                            nativeAddr(handle.display),
                            handle.window.toULong(),
                        )
                    else -> {
                        instance.close()
                        error("RawWindowHandle non supporté pour la capture Linux : $handle")
                    }
                } ?: run {
                    instance.close()
                    error("Échec création Surface depuis le handle de fenêtre Linux")
                }

                renderSurfaceToPng(instance, surface, path)
            } catch (t: Throwable) {
                captureError = t
            } finally {
                eventLoop.exit()
            }
        }

        override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
            // No event to handle: the capture is done in canCreateSurfaces.
        }
    }

    EventLoop().runApp(handler)
    captureError?.let { throw it }
}
