/**
 * Capture offscreen GPU sur Linux (Redmine #88).
 *
 * Contrairement à macOS/Windows où l'on fabrique la surface « à la main » (CAMetalLayer /
 * HWND cachés), créer une `wl_surface` Wayland (ou une fenêtre X11) à la main en FFM est
 * lourd. On réutilise donc l'**EventLoop kadre** : il sait déjà créer une fenêtre
 * Wayland/X11 et expose son [RawWindowHandle]. On en dérive la surface wgpu, on rend dans
 * une texture offscreen, on relit le framebuffer (pipeline commun [renderSurfaceToPng]),
 * puis on quitte la boucle.
 *
 * Prérequis CI (headless) :
 * - un compositor Wayland headless (ex. `weston --backend=headless`) avec `WAYLAND_DISPLAY`
 *   et `XDG_RUNTIME_DIR` positionnés, OU un serveur X11 virtuel (`Xvfb`) ;
 * - un Vulkan logiciel (Mesa lavapipe) via `VK_ICD_FILENAMES` — les runners n'ont pas de GPU.
 *
 * Backend wgpu : `Vulkan` (lavapipe).
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
import io.ygdrasil.webgpu.WGPU
import io.ygdrasil.webgpu.WGPUInstanceBackend
import io.ygdrasil.webgpu.WGPULowLevelApi
import java.lang.foreign.MemorySegment

/** Enveloppe une adresse native (pointeur) en [JvmNativeAddress] pour wgpu4k. */
private fun nativeAddr(addr: Long): JvmNativeAddress =
    JvmNativeAddress(MemorySegment.ofAddress(addr))

/**
 * Capture Linux : crée une fenêtre via l'EventLoop kadre (Wayland ou X11 selon le backend
 * détecté), dérive la surface wgpu de son [RawWindowHandle], rend + readback, puis quitte.
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

                // Primary = Vulkan + GL : laisse wgpu retomber sur GL/EGL (llvmpipe) si la
                // création de surface Vulkan échoue (lavapipe a un support WSI limité).
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

        override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
            // Aucun événement à traiter : la capture est faite dans canCreateSurfaces.
        }
    }

    EventLoop().runApp(handler)
    captureError?.let { throw it }
}
