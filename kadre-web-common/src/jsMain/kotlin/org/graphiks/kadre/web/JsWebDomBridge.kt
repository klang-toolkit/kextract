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
import org.graphiks.kadre.core.Insets
import org.w3c.dom.Element
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.WheelEvent
import kotlin.math.roundToInt

/**
 * JS DOM bridge to the Kadre engine.
 *
 * Listens to and dispatches the following DOM events:
 * - Keyboard: `keydown` / `keyup` → [WebWindowEvent.KeyInput]
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

    override var preventDefaultEnabled: Boolean = true

    override fun readDevicePixelRatio(): Double = window.devicePixelRatio

    override fun readCanvasPhysicalSize(canvasId: String): Pair<Int, Int> {
        val canvas = document.getElementById(canvasId) ?: return Pair(0, 0)
        val dpr = readDevicePixelRatio()
        val w = ((canvas.asDynamic().clientWidth as Double) * dpr).roundToInt()
        val h = ((canvas.asDynamic().clientHeight as Double) * dpr).roundToInt()
        return Pair(w, h)
    }

    override fun ensureCanvas(attrs: WebWindowAttributes): String {
        val id = attrs.effectiveCanvasId
        val existing = document.getElementById(id)
        if (existing != null) return id
        if (!attrs.appendToBody) {
            println("[JsWebDomBridge] Canvas '$id' not found (appendToBody=false → no creation)")
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
        println("[JsWebDomBridge] Canvas '$id' (${attrs.width}×${attrs.height}) created and appended")
        return id
    }

    private var targetElement: Element? = null
    private var canvasElement: Element? = null
    private val canvasListeners = mutableListOf<Pair<String, (Event) -> Unit>>()
    private val documentListeners = mutableListOf<Pair<String, (Event) -> Unit>>()
    private val windowListeners = mutableListOf<Pair<String, (Event) -> Unit>>()
    private var resizeObserver: dynamic = null

    /** Hidden <input> used for IME composition events. */
    private var imeInput: HTMLInputElement? = null

    /** `false` once [detach] runs — stops the re-arming devicePixelRatio chain. */
    private var attached = false

    override fun attach(targetElementId: String) {
        val canvas = document.getElementById(targetElementId) ?: return
        targetElement = canvas
        canvasElement = canvas
        attached = true

        // --- Keyboard ---
        addListener(canvas, "keydown") { e ->
            val ke = e as KeyboardEvent
            val mods = domModifiers(ke.shiftKey, ke.ctrlKey, ke.altKey, ke.metaKey)
            dispatch(
                WebWindowEvent.KeyInput(
                    domKeyEvent(
                        code = ke.code,
                        key = ke.key,
                        eventType = "keydown",
                        shiftKey = ke.shiftKey,
                        ctrlKey = ke.ctrlKey,
                        altKey = ke.altKey,
                        metaKey = ke.metaKey,
                        repeat = ke.repeat,
                    ),
                ),
            )
            // R4: emit ModifiersChanged when a modifier key is pressed
            if (ke.key in setOf("Shift", "Control", "Alt", "Meta")) {
                dispatch(WebWindowEvent.ModifiersChanged(mods))
            }
        }

        addListener(canvas, "keyup") { e ->
            val ke = e as KeyboardEvent
            val mods = domModifiers(ke.shiftKey, ke.ctrlKey, ke.altKey, ke.metaKey)
            dispatch(
                WebWindowEvent.KeyInput(
                    domKeyEvent(
                        code = ke.code,
                        key = ke.key,
                        eventType = "keyup",
                        shiftKey = ke.shiftKey,
                        ctrlKey = ke.ctrlKey,
                        altKey = ke.altKey,
                        metaKey = ke.metaKey,
                        repeat = false,
                    ),
                ),
            )
            // R4: emit ModifiersChanged when a modifier key is released
            if (ke.key in setOf("Shift", "Control", "Alt", "Meta")) {
                dispatch(WebWindowEvent.ModifiersChanged(mods))
            }
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
            // Ctrl+Wheel → pinch zoom (works across all browsers)
            if (we.ctrlKey) {
                dispatch(
                    WebWindowEvent.WebPinchZoom(
                        delta = (-we.deltaY / 100.0).toFloat(),
                        centerX = we.clientX.toDouble(),
                        centerY = we.clientY.toDouble(),
                    )
                )
            } else {
                dispatch(
                    WebWindowEvent.MouseWheel(
                        deltaX = normalizeWheelDelta(we.deltaX, we.deltaMode),
                        deltaY = normalizeWheelDelta(we.deltaY, we.deltaMode),
                    )
                )
            }
        }

        // --- DnD ---
        addListener(canvas, "dragenter") { e ->
            e.preventDefault()
            val pe = e.unsafeCast<PointerEventData>()
            val dt = e.asDynamic().dataTransfer
            val files = if (dt != null) {
                val items = dt.items
                if (items != null && items.length > 0) {
                    (0 until items.length).mapNotNull { i ->
                        items[i].asDynamic().type as? String
                    }
                } else emptyList()
            } else emptyList()
            dispatch(WebWindowEvent.DragEntered(x = pe.clientX, y = pe.clientY, files = files))
        }

        addListener(canvas, "dragover") { e ->
            e.preventDefault()
            val pe = e.unsafeCast<PointerEventData>()
            dispatch(WebWindowEvent.DragMoved(x = pe.clientX, y = pe.clientY))
        }

        addListener(canvas, "drop") { e ->
            e.preventDefault()
            val pe = e.unsafeCast<PointerEventData>()
            val dt = e.asDynamic().dataTransfer
            val files = if (dt != null && dt.files != null) {
                (0 until dt.files.length).mapNotNull { i ->
                    dt.files[i].asDynamic().name as? String
                }
            } else emptyList()
            dispatch(WebWindowEvent.DragDropped(x = pe.clientX, y = pe.clientY, files = files))
        }

        addListener(canvas, "dragleave") { _ ->
            dispatch(WebWindowEvent.DragLeft)
        }

        // --- Gesture (Safari trackpad: gesturestart/change/end) ---
        addListener(canvas, "gesturestart") { e ->
            e.preventDefault()
            dispatch(
                WebWindowEvent.WebGestureStart(
                    scale = e.asDynamic().scale as? Float ?: 1.0f,
                    rotation = e.asDynamic().rotation as? Float ?: 0.0f,
                )
            )
        }
        addListener(canvas, "gesturechange") { e ->
            e.preventDefault()
            dispatch(
                WebWindowEvent.WebGestureChange(
                    scale = e.asDynamic().scale as? Float ?: 1.0f,
                    rotation = e.asDynamic().rotation as? Float ?: 0.0f,
                )
            )
        }
        addListener(canvas, "gestureend") { e ->
            e.preventDefault()
            dispatch(
                WebWindowEvent.WebGestureEnd(
                    scale = e.asDynamic().scale as? Float ?: 1.0f,
                    rotation = e.asDynamic().rotation as? Float ?: 0.0f,
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

        // --- Page visibility → Focused + Occluded ---
        addDocumentListener("visibilitychange") { _ ->
            val hidden: Boolean = js("document.hidden")
            dispatch(WebWindowEvent.Focused(gained = !hidden))
            dispatch(WebWindowEvent.WebOccluded(hidden))
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
        if (preventDefaultEnabled) e.preventDefault()
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

    // ── R5-IME: hidden input overlay ─────────────────────────────────────────

    override fun setImeAllowed(allowed: Boolean) {
        if (allowed) {
            val input = imeInput ?: createImeInputBox().also { imeInput = it }
            input.focus()
        } else {
            imeInput?.blur()
        }
    }

    override fun setImePurpose(purpose: String) {
        imeInput?.let { it.asDynamic().inputMode = purpose }
    }

    override fun setImeCursorArea(x: Int, y: Int, width: Int, height: Int) {
        val input = imeInput ?: return
        val s = input.style
        s.left = "${x}px"
        s.top = "${y}px"
        s.width = "${width}px"
        s.height = "${height}px"
    }

    /**
     * Creates the hidden <input> element and wires IME composition event
     * listeners. Appends it to the canvas parent (or document.body as fallback).
     */
    private fun createImeInputBox(): HTMLInputElement {
        val input = document.createElement("input").unsafeCast<HTMLInputElement>().apply {
            style.position = "absolute"
            style.opacity = "0"
            style.height = "0px"
            style.width = "0px"
            style.left = "0px"
            style.top = "0px"
            style.zIndex = "-1"
        }
        input.style.asDynamic().pointerEvents = "none"
        // Suppress browser auto-correction / auto-fill on the hidden input
        input.asDynamic().autocapitalize = "off"
        input.asDynamic().autocomplete = "off"
        input.asDynamic().autocorrect = "off"
        input.asDynamic().spellcheck = false

        canvasElement?.let { it.parentElement?.appendChild(input) }
            ?: document.body?.appendChild(input)

        input.addEventListener("compositionstart", EventListener {
            dispatch(WebWindowEvent.Ime(WebImeEvent.Enabled))
        })

        input.addEventListener("compositionupdate", EventListener { event ->
            val data = event.asDynamic().data as? String ?: ""
            dispatch(WebWindowEvent.Ime(WebImeEvent.Preedit(text = data, cursorRange = null)))
        })

        input.addEventListener("compositionend", EventListener { event ->
            val data = event.asDynamic().data as? String ?: ""
            dispatch(WebWindowEvent.Ime(WebImeEvent.Commit(text = data)))
            dispatch(WebWindowEvent.Ime(WebImeEvent.Disabled))
            input.value = ""
        })

        return input
    }

    override fun getCanvasElement(): Any? = canvasElement

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

        imeInput?.remove()
        imeInput = null

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

    // ── Task 14: safeArea insets + ownedDisplayHandle ─────────────────────────

    override fun getSafeAreaInsets(): Insets<Int> {
        val body = document.body ?: return Insets(0, 0, 0, 0)
        val div = document.createElement("div").asDynamic()
        body.asDynamic().appendChild(div)
        div.style.setProperty("padding-top", "env(safe-area-inset-top, 0px)")
        div.style.setProperty("padding-bottom", "env(safe-area-inset-bottom, 0px)")
        div.style.setProperty("padding-left", "env(safe-area-inset-left, 0px)")
        div.style.setProperty("padding-right", "env(safe-area-inset-right, 0px)")
        val cs = window.asDynamic().getComputedStyle(div)
        fun parsePx(v: Any?): Int {
            val s = v as? String ?: "0px"
            return if (s.endsWith("px")) s.removeSuffix("px").trim().toIntOrNull() ?: 0 else 0
        }
        val insets = Insets(
            top = parsePx(cs.paddingTop),
            bottom = parsePx(cs.paddingBottom),
            left = parsePx(cs.paddingLeft),
            right = parsePx(cs.paddingRight),
        )
        body.asDynamic().removeChild(div)
        return insets
    }

    override fun getDisplayHandle(): Long {
        val screen = window.asDynamic().screen
        return ((screen.availWidth as Int).toLong() shl 32) or (screen.availHeight as Int).toLong()
    }

    // ── R2: Fullscreen API ────────────────────────────────────────────────────

    /**
     * Calls `element.requestFullscreen()` on the target canvas.
     * Uses the dynamic API to handle browser prefixes gracefully.
     */
    override fun requestFullscreen(canvasId: String) {
        val el = document.getElementById(canvasId) ?: targetElement ?: return
        try {
            val d = el.asDynamic()
            when {
                d.requestFullscreen != null -> d.requestFullscreen()
                d.webkitRequestFullscreen != null -> d.webkitRequestFullscreen()
                d.mozRequestFullScreen != null -> d.mozRequestFullScreen()
            }
        } catch (_: Throwable) {}
    }

    // ── R5-CustomCursor ─────────────────────────────────────────────────────────

    /**
     * Creates a data URL from RGBA pixel data via an off-screen canvas.
     *
     * Creates a `<canvas>`, paints the RGBA pixels via `putImageData`,
     * and returns `canvas.toDataURL("image/png")`.
     */
    override fun createCursorDataUrl(rgba: ByteArray, width: Int, height: Int, hotspotX: Int, hotspotY: Int): String {
        try {
            val canvas = document.createElement("canvas").asDynamic()
            canvas.width = width
            canvas.height = height
            val ctx = canvas.getContext("2d").asDynamic()
            val imageData = ctx.createImageData(width, height).asDynamic()
            val data = imageData.data
            for (i in rgba.indices) {
                data[i] = rgba[i]
            }
            ctx.putImageData(imageData, 0.0, 0.0)
            val url: String = canvas.toDataURL("image/png") as String
            return url
        } catch (_: Throwable) { return "" }
    }

    /**
     * Calls `document.exitFullscreen()`.
     */
    override fun exitFullscreen() {
        try {
            val d = document.asDynamic()
            when {
                d.exitFullscreen != null -> d.exitFullscreen()
                d.webkitExitFullscreen != null -> d.webkitExitFullscreen()
                d.mozCancelFullScreen != null -> d.mozCancelFullScreen()
            }
        } catch (_: Throwable) {}
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
