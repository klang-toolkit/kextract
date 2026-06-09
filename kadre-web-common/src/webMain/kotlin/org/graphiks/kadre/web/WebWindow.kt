/**
 * Web implementation of the [Window] interface.
 *
 * ## Canvas
 * The target canvas is identified by [attrs.title] used as the CSS `id`.
 * If the title is empty, the default identifier `"kadre-canvas"` is used.
 * The actual DOM attachment is delegated to [WebDomBridge.attach].
 *
 * ## webMain constraint
 * This file resides in `webMain` — NO DOM import is allowed here.
 * The DOM types (HTMLCanvasElement, etc.) are handled exclusively in
 * `jsMain` via [JsWebDomBridge] and in `wasmJsMain` via `WasmJsWebDomBridge`.
 *
 * ## Dynamic size and scale
 * [innerSize], [outerSize] and [scaleFactor] reflect the real canvas dimensions
 * and `devicePixelRatio`. The values are updated via [updatePhysicalSize] and
 * [updateScaleFactor], called by [WebEventLoop] when the bridge emits
 * [WebWindowEvent.Resized] and [WebWindowEvent.ScaleFactorChanged].
 *
 * @param attrs   Window creation attributes (title used as CSS id).
 * @param bridge  DOM bridge used to attach / detach the canvas.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.CustomCursor
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.WindowRequestResult

/**
 * Maps a [CursorIcon] to the corresponding CSS cursor property value.
 */
internal fun CursorIcon.toCssCursorValue(): String = when (this) {
    CursorIcon.Default        -> "default"
    CursorIcon.Pointer        -> "pointer"
    CursorIcon.Text           -> "text"
    CursorIcon.Crosshair      -> "crosshair"
    CursorIcon.Move           -> "move"
    CursorIcon.ResizeNorth    -> "n-resize"
    CursorIcon.ResizeSouth    -> "s-resize"
    CursorIcon.ResizeEast     -> "e-resize"
    CursorIcon.ResizeWest     -> "w-resize"
    CursorIcon.ResizeNorthEast -> "ne-resize"
    CursorIcon.ResizeNorthWest -> "nw-resize"
    CursorIcon.ResizeSouthEast -> "se-resize"
    CursorIcon.ResizeSouthWest -> "sw-resize"
    CursorIcon.NotAllowed     -> "not-allowed"
    CursorIcon.Grab           -> "grab"
    CursorIcon.Grabbing       -> "grabbing"
    CursorIcon.Wait           -> "wait"
    CursorIcon.Progress       -> "progress"
    CursorIcon.EwResize       -> "ew-resize"
    CursorIcon.NsResize       -> "ns-resize"
    CursorIcon.NeswResize     -> "nesw-resize"
    CursorIcon.NwseResize     -> "nwse-resize"
    CursorIcon.ColResize      -> "col-resize"
    CursorIcon.RowResize      -> "row-resize"
    CursorIcon.AllScroll      -> "all-scroll"
    CursorIcon.ZoomIn         -> "zoom-in"
    CursorIcon.ZoomOut        -> "zoom-out"
    CursorIcon.Copy           -> "copy"
    CursorIcon.Alias          -> "alias"
    CursorIcon.ContextMenu    -> "context-menu"
    CursorIcon.Cell           -> "cell"
    CursorIcon.NoDrop         -> "no-drop"
    CursorIcon.Help           -> "help"
    CursorIcon.Hidden         -> "none"
    CursorIcon.NoneReset      -> "default"
    CursorIcon.WaitCursor     -> "wait"
    CursorIcon.VerticalText   -> "vertical-text"
}

/**
 * [Window] implementation for the web backends (JS and wasmJs).
 *
 * Resolves the canvas via [attrs.title] as a CSS identifier, or dynamically
 * creates a `"kadre-canvas"` canvas if the title is empty.
 *
 * [setTitle] is a no-op on the Web side (pages have no title bar
 * in the native-window sense).
 */
class WebWindow(
    override val id: WindowId,
    /**
     * CSS identifier of the target canvas element.
     *
     * Must match a `<canvas>` already present in the DOM. Use
     * [WebDomBridge.ensureCanvas] (or [WebEventLoop.createWindow] with a
     * [WebWindowAttributes]) for auto-creation.
     */
    private val canvasElementId: String,
    internal val bridge: WebDomBridge,
) : Window {

    constructor(canvasElementId: String, bridge: WebDomBridge)
            : this(
                id = WindowId(canvasElementId.hashCode().toLong()),
                canvasElementId = canvasElementId,
                bridge = bridge,
            )

    /**
     * Builds a Web window from the core [WindowAttributes] contract.
     *
     * **Legacy**: uses `attrs.title` as the canvas CSS `id`, or
     * `"kadre-canvas"` by default — a non-idiomatic convention (the title is
     * semantically unrelated to a DOM `id`). Prefer
     * `WebEventLoop.createWindow(WebWindowAttributes)`.
     */
    @Deprecated(
        "Convention title-as-canvasId. Use WebEventLoop.createWindow(WebWindowAttributes) " +
                "to explicitly target a DOM canvas by its id.",
    )
    constructor(attrs: WindowAttributes, bridge: WebDomBridge)
            : this(
                id = WindowId(1L),
                canvasElementId = attrs.title.ifEmpty { WebWindowAttributes.DEFAULT_CANVAS_ID },
                bridge = bridge,
            )

    /**
     * Raw handle of the rendering surface — identifies the canvas by its CSS id.
     *
     * Returns [RawWindowHandle.Web] with [canvasElementId].
     */
    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.Web(canvasElementId = canvasElementId)

    /**
     * Raw handle of the display — web singleton with no additional pointer.
     *
     * Returns [RawDisplayHandle.Web].
     */
    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.Web

    // -------------------------------------------------------------------------
    // Dynamic size / scale state — updated by WebEventLoop via updatePhysicalSize
    // and updateScaleFactor when the bridge emits Resized / ScaleFactorChanged.
    // -------------------------------------------------------------------------

    /** Current physical size of the canvas in pixels. Default 0×0 until first Resized event. */
    internal var _physicalSize: PhysicalSize<Int> = PhysicalSize(0, 0)
        private set

    /** Current device pixel ratio. Default 1.0 until first ScaleFactorChanged event. */
    internal var _scaleFactor: Double = 1.0
        private set

    /**
     * Updates the stored physical size.
     *
     * Called by [WebEventLoop] when the bridge fires [WebWindowEvent.Resized].
     * Dimensions are already in physical pixels (CSS pixels × devicePixelRatio).
     */
    internal fun updatePhysicalSize(width: Int, height: Int) {
        _physicalSize = PhysicalSize(width, height)
    }

    /**
     * Updates the stored scale factor.
     *
     * Called by [WebEventLoop] when the bridge fires [WebWindowEvent.ScaleFactorChanged].
     */
    internal fun updateScaleFactor(factor: Double) {
        _scaleFactor = factor
    }

    /**
     * Inner size of the window (rendering surface) in physical pixels.
     *
     * Reflects the real canvas dimensions as reported by the ResizeObserver.
     * Returns 0×0 until the first [WebWindowEvent.Resized] event.
     */
    override val innerSize: PhysicalSize<Int>
        get() = _physicalSize

    /**
     * Outer size of the window in physical pixels.
     *
     * Identical to [innerSize] on the Web side (no native decorations).
     */
    override val outerSize: PhysicalSize<Int>
        get() = _physicalSize

    /**
     * Scale factor between logical and physical pixels (`window.devicePixelRatio`).
     *
     * Returns 1.0 until the first [WebWindowEvent.ScaleFactorChanged] event,
     * then reflects the actual device pixel ratio.
     */
    override val scaleFactor: Double
        get() = _scaleFactor

    /**
     * Requests a redraw of the window via [WebWindowEvent.RedrawRequested].
     *
     * Emits the event to [WebDomBridge.onWindowEvent] if it is registered.
     */
    override fun requestRedraw() {
        bridge.onWindowEvent?.invoke(WebWindowEvent.RedrawRequested)
    }

    /**
     * Handles canvas visibility via CSS (future) — no-op in this version.
     *
     * @param visible true to show, false to hide.
     */
    override fun setVisible(visible: Boolean) {
        // no-op Web — CSS display could be driven here (out of scope for #25)
    }

    /**
     * Closes the web window by detaching the DOM bridge.
     *
     * Delegates to [WebDomBridge.detach] to remove the DOM listeners
     * and release the associated resources.
     */
    override fun close() {
        bridge.detach()
    }

    // ── R1: window state & geometry — mostly no-ops on Web ────────────────────
    //
    // Web browsers control the browser window — the canvas/page has no
    // access to minimize, maximize, resize, or decorate the OS window.
    // `title` delegates to `document.title`.

    private var _title: String = canvasElementId

    /**
     * Sets the browser tab / document title via the bridge.
     *
     * @param title New title to display in the browser tab.
     */
    override fun setTitle(title: String) {
        _title = title
        bridge.setDocumentTitle(title)
    }

    /**
     * Returns the current document title (last value passed to [setTitle]).
     */
    override val title: String get() = _title

    /**
     * Browsers do not expose a reliable winit-style window visibility state.
     */
    override val isVisible: Boolean? get() = null

    /**
     * Web browsers do not support programmatic window resizing.
     * This is a no-op.
     */
    override fun setResizable(resizable: Boolean) { /* no-op: Web does not support programmatic window resizing */ }

    /** Web windows are not programmatically resizable. Always returns false. */
    override val isResizable: Boolean get() = false

    /**
     * Web browsers do not support programmatic window minimization.
     * This is a no-op.
     */
    override fun setMinimized(minimized: Boolean) { /* no-op: Web does not support programmatic window minimization */ }

    /** Web browsers do not expose a reliable minimized state. */
    override val isMinimized: Boolean? get() = null

    /**
     * Web browsers do not support programmatic window maximization.
     * This is a no-op.
     */
    override fun setMaximized(maximized: Boolean) { /* no-op: Web does not support programmatic window maximization */ }

    /** Web windows cannot be maximized programmatically. Always returns false. */
    override val isMaximized: Boolean get() = false

    /**
     * Web pages have no platform window decorations.
     * This is a no-op.
     */
    override fun setDecorations(decorated: Boolean) { /* no-op: Web pages have no platform window decorations */ }

    /** Web pages have no platform window decorations. Always returns false. */
    override val isDecorated: Boolean get() = false

    /**
     * Web canvases do not have surface size constraints.
     * This is a no-op.
     */
    override fun setMinSurfaceSize(size: PhysicalSize<Int>?) { /* no-op: Web does not support canvas size constraints */ }

    /**
     * Web canvases do not have surface size constraints.
     * This is a no-op.
     */
    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) { /* no-op: Web does not support canvas size constraints */ }

    /**
     * Web pages do not expose the browser window's screen position.
     * Returns PhysicalPosition(0, 0).
     */
    override val outerPosition: PhysicalPosition<Int> get() = PhysicalPosition(0, 0)

    /**
     * Web browsers do not support programmatic window positioning.
     * This is a no-op.
     */
    override fun setOuterPosition(position: PhysicalPosition<Int>) { /* no-op: Web does not support programmatic window positioning */ }

    /**
     * No-op on Web: there is no Wayland-style pre-commit concept in the browser.
     */
    override fun prePresentNotify() { /* no-op on Web */ }

    // ── R2: monitor & fullscreen ──────────────────────────────────────────────

    /**
     * Returns a synthetic monitor representing the browser window.
     */
    override fun currentMonitor(): MonitorHandle = syntheticWebMonitor(_scaleFactor, _physicalSize)

    override fun availableMonitors(): List<MonitorHandle> =
        listOf(currentMonitor())

    override fun primaryMonitor(): MonitorHandle? =
        currentMonitor()

    /** In-memory fullscreen state (R2). */
    private var _fullscreen: Fullscreen? = null

    override val fullscreen: Fullscreen? get() = _fullscreen

    // ── R3: cursor, theme & appearance ───────────────────────────────────────

    /**
     * Sets the CSS cursor style on the canvas via the bridge.
     *
     * Maps [CursorIcon] to the corresponding CSS cursor value.
     */
    override fun setCursor(cursor: CursorIcon) {
        bridge.setCssCursor(canvasElementId, cursor.toCssCursorValue())
    }

    /**
     * Applies a previously created custom cursor via a CSS cursor URL.
     *
     * Resolves the data URL from [WebCustomCursorCache] and sets
     * `cursor: url(<dataUrl>) <hx> <hy>, auto` on the canvas.
     * Never throws.
     */
    override fun setCustomCursor(cursor: CustomCursor) {
        try {
            val dataUrl = WebCustomCursorCache.resolve(cursor.id) ?: return
            bridge.setCssCursor(canvasElementId, "url($dataUrl) auto")
        } catch (_: Throwable) {}
    }

    /**
     * Shows or hides the cursor by setting CSS `cursor: none` or restoring it.
     */
    override fun setCursorVisible(visible: Boolean) {
        if (!visible) {
            bridge.setCssCursor(canvasElementId, "none")
        } else {
            bridge.setCssCursor(canvasElementId, CursorIcon.Default.toCssCursorValue())
        }
    }

    /**
     * Sets the cursor grab mode.
     *
     * - [CursorGrabMode.Locked]: unsupported until Pointer Lock is wired in concrete bridges.
     * - [CursorGrabMode.Confined]: no-op (browsers do not expose canvas-confined grab).
     * - [CursorGrabMode.None]: calls `exitPointerLock()`.
     */
    override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult {
        when (mode) {
            CursorGrabMode.Locked -> return WindowRequestResult.Failure(
                RequestError.Unsupported("Web Pointer Lock is not wired in the DOM bridges"),
            )
            CursorGrabMode.Confined -> return WindowRequestResult.Failure(
                RequestError.Unsupported("Browsers do not expose canvas-confined cursor grab"),
            )
            CursorGrabMode.None     -> bridge.exitPointerLock()
        }
        return WindowRequestResult.Success
    }

    /**
     * No-op on Web: cursor warping is not exposed by browser APIs.
     */
    override fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Browsers do not allow cursor warping"))

    /**
     * No-op on Web.
     *
     * TODO(R3-web-hittest): implement via CSS pointer-events: none.
     */
    override fun setCursorHittest(hittest: Boolean): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Web cursor hit-testing is not implemented"))

    /**
     * Returns the system theme via the bridge's `prefersDarkColorScheme`.
     */
    override val theme: Theme?
        get() = if (bridge.prefersDarkColorScheme()) Theme.Dark else Theme.Light

    /**
     * No-op on Web: individual elements do not control the OS theme.
     */
    override fun setTheme(theme: Theme?) {
        // No-op: Web pages cannot override the OS theme.
    }

    /**
     * No-op on Web: Z-ordering is managed by the browser.
     */
    override fun setWindowLevel(level: WindowLevel) {
        // No-op: browser controls window stacking.
    }

    /**
     * No-op on Web: canvas transparency is a renderer concern (WebGL blending).
     */
    override fun setTransparent(transparent: Boolean) {
        // No-op: canvas background transparency is set via CSS / WebGL context attributes.
    }

    /**
     * No-op on Web.
     */
    override fun setBlur(blur: Boolean) {
        // No-op: CSS backdrop-filter blur could be applied but is out of scope for R3.
    }

    /**
     * No-op on Web: the page icon is set via `<link rel="icon">` in the HTML.
     */
    override fun setWindowIcon(icon: Icon?) {
        // No-op: Web page icons are managed via <link rel="icon"> in the HTML document.
    }

    // ── R4: keyboard ──────────────────────────────────────────────────────────

    /**
     * No-op on Web: dead-key state is managed by the browser's IME.
     *
     * The DOM provides no API to reset the input method's dead-key buffer.
     */
    override fun resetDeadKeys() {
        // no-op: browser IME state is not accessible from JavaScript
    }

    // ── R5-IME ──────────────────────────────────────────────────────────────────

    /** Stored cursor area position for potential use. */
    private var _imeCursorPosition: PhysicalPosition<Int> = PhysicalPosition(0, 0)

    /** Stored cursor area size for potential use. */
    private var _imeCursorSize: PhysicalSize<Int> = PhysicalSize(0, 0)

    /**
     * Enables or disables IME input.
     *
     * On the Web, IME is managed entirely by the browser when the canvas is
     * focused. This override is informational — the browser controls IME
     * composition independently of this flag.
     */
    override fun setImeAllowed(allowed: Boolean) {
        bridge.setImeAllowed(allowed)
    }

    /**
     * Notifies the IME of the text cursor's current position and bounding box.
     *
     * Stores the values so the bridge can retrieve them via [WebDomBridge.getImeCursorArea]
     * if needed. On the Web the browser manages candidate-window positioning
     * automatically, but the stored area is available for future use.
     */
    override fun setImeCursorArea(position: PhysicalPosition<Int>, size: PhysicalSize<Int>) {
        _imeCursorPosition = position
        _imeCursorSize = size
    }

    /**
     * Hints the IME about the intended purpose of the focused text field.
     *
     * On the Web this is informational — the browser does not expose a DOM API
     * to control IME behaviour per-element without an actual `<input>` or
     * `<textarea>`. The `ime-mode` CSS property (deprecated) is the closest
     * approximation, but it is not wired in this version.
     */
    override fun setImePurpose(purpose: org.graphiks.kadre.core.ImePurpose) {
        // No-op: Web does not expose a standard API to control IME purpose
        // on a canvas element. The deprecated `ime-mode` CSS property could
        // be applied via the bridge in a future milestone.
    }

    /**
     * Requests fullscreen via the browser Fullscreen API (delegate to bridge).
     *
     * **Exclusive fullscreen is not supported in browsers** — the Web Fullscreen API only
     * supports a borderless mode. [Fullscreen.Exclusive] is silently treated as
     * [Fullscreen.Borderless].
     *
     * The actual fullscreen transition is asynchronous (the browser may ask for user
     * permission); [fullscreen] is updated eagerly to reflect the requested state.
     *
     * @param fullscreen New fullscreen state, or null to exit fullscreen.
     */
    override fun setFullscreen(fullscreen: Fullscreen?) {
        when (fullscreen) {
            null -> {
                bridge.exitFullscreen()
                _fullscreen = null
            }
            is Fullscreen.Borderless,
            is Fullscreen.Exclusive -> {
                // Exclusive is not supported on the Web — fall back to borderless silently.
                bridge.requestFullscreen(canvasElementId)
                _fullscreen = fullscreen
            }
        }
    }
}
