/**
 * JS implementation of [WebDomBridge].
 *
 * Attaches all the DOM listeners required by the target canvas and removes them
 * on detach. DOM events are converted into [WebWindowEvent]
 * via the pure functions of [DomEventMapper].
 *
 * This file MAY use kotlinx.browser and org.w3c.dom.* since it is in jsMain.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.WheelEvent

/**
 * JS DOM bridge to the Kadre engine.
 *
 * Listens to and dispatches the following DOM events:
 * - Keyboard: `keydown` / `keyup` → [WebWindowEvent.KeyboardInput]
 * - Pointer: `pointermove` → [WebWindowEvent.PointerMoved]
 * - Pointer: `pointerdown` / `pointerup` → [WebWindowEvent.MouseInput]
 * - Pointer: `pointerenter` / `pointerleave` → [WebWindowEvent.PointerEntered] / [WebWindowEvent.PointerLeft]
 * - Wheel: `wheel` → [WebWindowEvent.MouseWheel]
 * - Touch: `touchstart` / `touchmove` / `touchend` / `touchcancel` → [WebWindowEvent.Touch]
 * - Resize: `ResizeObserver` on the canvas → [WebWindowEvent.Resized]
 * - Visibility: `visibilitychange` → [WebWindowEvent.Focused]
 * - Scale: re-arming `matchMedia` on `devicePixelRatio` → [WebWindowEvent.ScaleFactorChanged]
 * - Unload: `beforeunload` → [WebWindowEvent.CloseRequested], `pagehide` → [WebWindowEvent.Destroyed]
 */
class JsWebDomBridge : WebDomBridge {

    override var onWindowEvent: ((WebWindowEvent) -> Unit)? = null

    override fun ensureCanvas(attrs: WebWindowAttributes): String {
        val id = attrs.effectiveCanvasId
        val existing = document.getElementById(id)
        if (existing != null) return id
        if (!attrs.appendToBody) {
            println("[JsWebDomBridge] Canvas '$id' introuvable (appendToBody=false → pas de création)")
            return id
        }
        val canvas = document.createElement("canvas").asDynamic()
        canvas.id = id
        canvas.width = attrs.width
        canvas.height = attrs.height
        // tabIndex to make the canvas focusable (without it, keydown/keyup do not fire).
        canvas.tabIndex = 0
        val parent = attrs.parentElementId?.let { document.getElementById(it) }
            ?: document.body
        if (parent == null) {
            println("[JsWebDomBridge] Aucun parent disponible (parentElementId='${attrs.parentElementId}', body absent)")
            return id
        }
        parent.appendChild(canvas)
        println("[JsWebDomBridge] Canvas '$id' (${attrs.width}×${attrs.height}) créé et ajouté")
        return id
    }

    private var targetElement: Element? = null
    private val canvasListeners = mutableListOf<Pair<String, (Event) -> Unit>>()
    private val documentListeners = mutableListOf<Pair<String, (Event) -> Unit>>()
    private val windowListeners = mutableListOf<Pair<String, (Event) -> Unit>>()
    private var resizeObserver: dynamic = null

    /** `false` once [detach] runs — stops the re-arming devicePixelRatio chain. */
    private var attached = false

    override fun attach(targetElementId: String) {
        val canvas = document.getElementById(targetElementId) ?: return
        targetElement = canvas
        attached = true

        // --- Keyboard ---
        addListener(canvas, "keydown") { e ->
            val ke = e as KeyboardEvent
            dispatch(
                WebWindowEvent.KeyboardInput(
                    key = domCodeToKey(ke.code),
                    state = WebKeyState.Pressed,
                    modifiers = domModifiers(ke.shiftKey, ke.ctrlKey, ke.altKey, ke.metaKey),
                    isRepeat = ke.repeat,
                )
            )
        }

        addListener(canvas, "keyup") { e ->
            val ke = e as KeyboardEvent
            dispatch(
                WebWindowEvent.KeyboardInput(
                    key = domCodeToKey(ke.code),
                    state = WebKeyState.Released,
                    modifiers = domModifiers(ke.shiftKey, ke.ctrlKey, ke.altKey, ke.metaKey),
                    isRepeat = false,
                )
            )
        }

        // --- Pointer (unified PointerEvent) ---
        addListener(canvas, "pointermove") { e ->
            val pe = e.unsafeCast<PointerEventData>()
            dispatch(WebWindowEvent.PointerMoved(x = pe.clientX, y = pe.clientY))
        }

        addListener(canvas, "pointerdown") { e ->
            val pe = e.unsafeCast<PointerEventData>()
            dispatch(
                WebWindowEvent.MouseInput(
                    button = domButtonToMouseButton(pe.button),
                    state = WebKeyState.Pressed,
                )
            )
        }

        addListener(canvas, "pointerup") { e ->
            val pe = e.unsafeCast<PointerEventData>()
            dispatch(
                WebWindowEvent.MouseInput(
                    button = domButtonToMouseButton(pe.button),
                    state = WebKeyState.Released,
                )
            )
        }

        addListener(canvas, "pointerenter") { _ ->
            dispatch(WebWindowEvent.PointerEntered)
        }

        addListener(canvas, "pointerleave") { _ ->
            dispatch(WebWindowEvent.PointerLeft)
        }

        // --- Wheel ---
        addListener(canvas, "wheel") { e ->
            val we = e as WheelEvent
            dispatch(
                WebWindowEvent.MouseWheel(
                    deltaX = normalizeWheelDelta(we.deltaX, we.deltaMode),
                    deltaY = normalizeWheelDelta(we.deltaY, we.deltaMode),
                )
            )
        }

        // --- Resize via ResizeObserver ---
        resizeObserver = js("new ResizeObserver(function(entries) { return entries; })")
        val self = this
        resizeObserver = js("""(function(callback) {
            return new ResizeObserver(function(entries) {
                for (var i = 0; i < entries.length; i++) {
                    var rect = entries[i].contentRect;
                    callback(Math.round(rect.width), Math.round(rect.height));
                }
            });
        })(function(w, h) { self.dispatchResized(w, h); })""")
        resizeObserver.observe(canvas)

        // --- Touch (touchscreen / mobile) ---
        for (type in listOf("touchstart", "touchmove", "touchend", "touchcancel")) {
            addListener(canvas, type) { e -> dispatchTouches(e) }
        }

        // --- Page visibility → Suspended/Resumed via Focused ---
        addDocumentListener("visibilitychange") { _ ->
            val hidden: Boolean = js("document.hidden")
            dispatch(WebWindowEvent.Focused(gained = !hidden))
        }

        // --- Unload: beforeunload → CloseRequested, pagehide → Destroyed ---
        addWindowListener("beforeunload") { _ ->
            dispatch(WebWindowEvent.CloseRequested)
        }
        addWindowListener("pagehide") { _ ->
            dispatch(WebWindowEvent.Destroyed)
        }

        // --- devicePixelRatio changes → ScaleFactorChanged ---
        observeDevicePixelRatio()
    }

    /**
     * Dispatches a [WebWindowEvent.Touch] for each contact in `event.changedTouches`.
     *
     * Reads the DOM `TouchEvent` dynamically (the Kotlin/JS stdlib type for
     * `TouchEvent` is incomplete in IR). `preventDefault()` stops the browser
     * from also synthesizing mouse events and page scrolling for the contacts.
     */
    private fun dispatchTouches(e: Event) {
        val phase = domTouchTypeToPhase(e.type)
        val touches = e.asDynamic().changedTouches
        val count = (touches.length as Number).toInt()
        for (i in 0 until count) {
            val t = touches[i]
            dispatch(
                WebWindowEvent.Touch(
                    phase = phase,
                    x = (t.clientX as Number).toDouble(),
                    y = (t.clientY as Number).toDouble(),
                    id = (t.identifier as Number).toDouble().toLong(),
                )
            )
        }
        e.preventDefault()
    }

    /**
     * Observes `window.devicePixelRatio` via a re-arming `matchMedia` listener.
     *
     * A `(resolution: <dpr>dppx)` media query only fires once when the ratio
     * leaves the current value, so the handler re-arms a fresh query each time.
     * The chain stops when [attached] becomes false (see [detach]).
     */
    private fun observeDevicePixelRatio() {
        val self = this
        js(
            """(function(cb, isAttached) {
                function arm() {
                    var dpr = window.devicePixelRatio || 1;
                    var mq = window.matchMedia('(resolution: ' + dpr + 'dppx)');
                    var handler = function() {
                        mq.removeEventListener('change', handler);
                        if (!isAttached()) return;
                        cb(window.devicePixelRatio || 1);
                        arm();
                    };
                    mq.addEventListener('change', handler);
                }
                arm();
            })(function(f) { self.dispatchScaleFactor(f); }, function() { return self.isAttached(); })"""
        )
    }

    /** Called from the re-arming matchMedia handler with the new devicePixelRatio. */
    @JsName("dispatchScaleFactor")
    fun dispatchScaleFactor(factor: Double) {
        dispatch(WebWindowEvent.ScaleFactorChanged(factor))
    }

    /** Predicate exposed to JS so the re-arming DPR chain stops after [detach]. */
    @JsName("isAttached")
    fun isAttached(): Boolean = attached

    /**
     * Called from the JS ResizeObserver with the new dimensions.
     */
    @JsName("dispatchResized")
    fun dispatchResized(width: Int, height: Int) {
        dispatch(WebWindowEvent.Resized(width = width, height = height))
    }

    override fun detach() {
        // Stop the re-arming devicePixelRatio chain before tearing down listeners.
        attached = false

        val canvas = targetElement

        if (canvas != null) {
            for ((type, handler) in canvasListeners) {
                canvas.removeEventListener(type, handler)
            }
        }
        canvasListeners.clear()

        val docDynamic = document.asDynamic()
        for ((type, handler) in documentListeners) {
            docDynamic.removeEventListener(type, handler)
        }
        documentListeners.clear()

        val winDynamic = window.asDynamic()
        for ((type, handler) in windowListeners) {
            winDynamic.removeEventListener(type, handler)
        }
        windowListeners.clear()

        resizeObserver?.disconnect()
        resizeObserver = null
        targetElement = null
    }

    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    private fun addListener(target: Element, type: String, handler: (Event) -> Unit) {
        target.addEventListener(type, handler)
        canvasListeners.add(Pair(type, handler))
    }

    private fun addDocumentListener(type: String, handler: (Event) -> Unit) {
        document.asDynamic().addEventListener(type, handler)
        documentListeners.add(Pair(type, handler))
    }

    private fun addWindowListener(type: String, handler: (Event) -> Unit) {
        window.asDynamic().addEventListener(type, handler)
        windowListeners.add(Pair(type, handler))
    }

    private fun dispatch(event: WebWindowEvent) {
        onWindowEvent?.invoke(event)
    }
}

/**
 * External interface describing the fields of a DOM PointerEvent accessible from JS.
 *
 * Used to access `clientX`, `clientY` and `button` on `pointerdown`,
 * `pointerup` and `pointermove` events, which inherit from MouseEvent
 * but whose direct cast via `as MouseEvent` is not always stable in IR.
 */
private external interface PointerEventData {
    val clientX: Double
    val clientY: Double
    val button: Short
}
