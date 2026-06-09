/**
 * Raw window and display handles, independent of any platform.
 *
 * These types are used to pass native pointers between the common code
 * and the platform-specific modules without importing native types into commonMain.
 * The pointers are represented as [Long]; the consumer performs the cast
 * to the appropriate native type at the point of use.
 *
 * Reference specification: Specs §6.1
 */
package org.graphiks.kadre.core

/**
 * Raw handle of a native window.
 *
 * Each variant corresponds to a target platform. The pointers are exposed
 * as [Long] so that commonMain remains 100% pure Kotlin (no native import).
 */
sealed interface RawWindowHandle {

    /**
     * AppKit window handle (macOS).
     *
     * @property nsView   Pointer to the `NSView` instance (cast to `NSView*` at the point of use).
     * @property nsWindow Pointer to the `NSWindow` instance (cast to `NSWindow*` at the point of use).
     * @property nsLayer  Pointer to the `CAMetalLayer` instance attached to the `NSView`.
     *                    Exposed directly to avoid going through `[nsView layer]`,
     *                    which may return the generic layer created by AppKit if
     *                    the `setLayer`/`setWantsLayer` order is not respected.
     */
    data class AppKit(val nsView: Long, val nsWindow: Long, val nsLayer: Long = 0L) : RawWindowHandle

    /**
     * UIKit window handle (iOS / tvOS).
     *
     * @property uiView           Pointer to the `UIView` instance (cast to `UIView*` at the point of use).
     * @property uiViewController Optional pointer to the `UIViewController` instance
     *                            (cast to `UIViewController*` at the point of use), or `null`
     *                            if no controller is associated.
     */
    data class UiKit(val uiView: Long, val uiViewController: Long?) : RawWindowHandle

    /**
     * Android window handle.
     *
     * @property surface Instance of the native surface. At runtime, this parameter is
     *                   necessarily an instance of `android.view.Surface`; the type
     *                   is declared [Any] so as not to introduce an Android import into
     *                   commonMain — the consumer performs the explicit cast.
     */
    data class Android(val surface: Any) : RawWindowHandle

    /**
     * Win32 window handle (Windows).
     *
     * @property hwnd      Win32 window handle (HWND), represented as Long for FFM compatibility.
     * @property hinstance Win32 instance handle (HINSTANCE), represented as Long for FFM compatibility.
     */
    data class Win32(val hwnd: Long, val hinstance: Long) : RawWindowHandle

    /**
     * X11/Xlib window handle (Linux).
     *
     * @property window  XID of the X11 window (returned by XCreateWindow).
     * @property display Pointer to the X11 Display (returned by XOpenDisplay), represented as Long.
     */
    data class Xlib(val window: Long, val display: Long) : RawWindowHandle

    /**
     * Wayland window handle (Linux).
     *
     * @property surface Pointer to the Wayland surface (wl_surface*), represented as Long.
     * @property display Pointer to the Wayland display (wl_display*), represented as Long.
     */
    data class Wayland(val surface: Long, val display: Long) : RawWindowHandle

    /**
     * Web window handle (browser / Wasm).
     *
     * Two modes of identifying the target canvas are supported:
     * - [canvasElementId]: CSS identifier of the `<canvas>` in the DOM
     *   (e.g. `"my-canvas"`). Convenient for a declarative configuration.
     * - [canvasElement]: direct reference to the `HTMLCanvasElement` element.
     *   Declared [Any] in commonMain to avoid any DOM import; on the JS/Wasm side,
     *   perform the explicit cast: `canvasElement as HTMLCanvasElement`.
     *
     * At least one of the two parameters must be non-null. If both are
     * provided, [canvasElement] takes precedence over [canvasElementId].
     *
     * @property canvasElementId CSS identifier of the canvas in the DOM, or `null`.
     * @property canvasElement   Direct reference to the `HTMLCanvasElement`, or `null`.
     *                           On the JS/Wasm side, cast with `canvasElement as HTMLCanvasElement`.
     * @throws IllegalArgumentException if [canvasElementId] and [canvasElement] are both `null`.
     */
    data class Web(
        val canvasElementId: String? = null,
        val canvasElement: Any? = null
    ) : RawWindowHandle {
        init {
            require(canvasElementId != null || canvasElement != null) {
                "RawWindowHandle.Web: at least one of canvasElementId or canvasElement must be non-null"
            }
        }
    }
}

/**
 * Raw handle of a native display.
 *
 * Each variant is a singleton corresponding to a target platform.
 * On these platforms, the display has no pointer handle distinct from the window.
 */
sealed interface RawDisplayHandle {

    /**
     * AppKit display handle (macOS).
     *
     * On macOS, the display is implicitly managed by AppKit; no additional
     * pointer is needed.
     */
    data object AppKit : RawDisplayHandle

    /**
     * UIKit display handle (iOS / tvOS).
     *
     * On iOS, the display is implicitly managed by UIKit; no additional
     * pointer is needed.
     */
    data object UiKit : RawDisplayHandle

    /**
     * Android display handle.
     *
     * On Android, the display is managed by the system; no additional
     * pointer is needed at this level of abstraction.
     */
    data object Android : RawDisplayHandle

    /**
     * Win32 display handle (Windows).
     *
     * @property hinstance Win32 instance handle (HINSTANCE), represented as Long for FFM compatibility.
     */
    data class Win32(val hinstance: Long) : RawDisplayHandle

    /**
     * Web display handle (browser / Wasm).
     *
     * In the Web context, the display is implicitly managed by the browser;
     * no additional pointer is needed at this level of abstraction.
     * This singleton simply marks the display target as being the Web context.
     */
    data object Web : RawDisplayHandle

    /**
     * X11/Xlib display handle (Linux).
     *
     * @property display Pointer to the X11 Display (returned by XOpenDisplay), represented as Long.
     */
    data class Xlib(val display: Long) : RawDisplayHandle

    /**
     * Wayland display handle (Linux).
     *
     * @property display Pointer to the Wayland display (wl_display*), represented as Long.
     */
    data class Wayland(val display: Long) : RawDisplayHandle
}
