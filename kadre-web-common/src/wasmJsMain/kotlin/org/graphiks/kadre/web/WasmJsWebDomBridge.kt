/**
 * wasmJs implementation of [WebDomBridge].
 *
 * Provides a functional stub based on Wasm JS interop (external interface).
 * DOM listeners are registered via [JsAny] and Wasm [addEventListener],
 * and event fields are extracted via the external interfaces below.
 *
 * This file MAY use Wasm JS interop since it is in wasmJsMain.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

// ---------------------------------------------------------------------------
// External interfaces — wasmJs-side DOM access
// ---------------------------------------------------------------------------

/**
 * External representation of a DOM [EventTarget] (canvas, document, etc.).
 */
@JsName("EventTarget")
external interface JsEventTarget : JsAny {
    fun addEventListener(type: JsString, listener: JsAny)
    fun removeEventListener(type: JsString, listener: JsAny)
}

/**
 * Properties common to all DOM events.
 */
@JsName("Event")
external interface JsDomEvent : JsAny {
    val type: JsString
}

/**
 * Properties of a DOM KeyboardEvent.
 */
@JsName("KeyboardEvent")
external interface JsKeyboardEvent : JsDomEvent {
    val code: JsString
    val shiftKey: JsBoolean
    val ctrlKey: JsBoolean
    val altKey: JsBoolean
    val metaKey: JsBoolean
    val repeat: JsBoolean
}

/**
 * Properties of a DOM PointerEvent (includes the MouseEvent fields).
 */
@JsName("PointerEvent")
external interface JsPointerEvent : JsDomEvent {
    val clientX: JsNumber
    val clientY: JsNumber
    val button: JsNumber
}

/**
 * Properties of a DOM WheelEvent.
 */
@JsName("WheelEvent")
external interface JsWheelEvent : JsDomEvent {
    val deltaX: JsNumber
    val deltaY: JsNumber
    val deltaMode: JsNumber
}

// ---------------------------------------------------------------------------
// Wasm JS interop functions — global DOM access
// ---------------------------------------------------------------------------

@JsFun("(id) => document.getElementById(id)")
private external fun getElementById(id: JsString): JsEventTarget?

@JsFun("(target, type, listener) => { target.addEventListener(type, listener); }")
private external fun jsAddEventListener(target: JsEventTarget, type: JsString, listener: JsAny)

@JsFun("(target, type, listener) => { target.removeEventListener(type, listener); }")
private external fun jsRemoveEventListener(target: JsEventTarget, type: JsString, listener: JsAny)

@JsFun("() => document")
private external fun getDocument(): JsEventTarget

@JsFun("() => document.hidden")
private external fun isDocumentHidden(): JsBoolean

@JsFun("""(canvas, callback) => {
    const ro = new ResizeObserver((entries) => {
        for (const entry of entries) {
            const rect = entry.contentRect;
            callback(Math.round(rect.width), Math.round(rect.height));
        }
    });
    ro.observe(canvas);
    return ro;
}""")
private external fun createResizeObserver(canvas: JsEventTarget, callback: JsAny): JsAny

@JsFun("(ro) => { ro.disconnect(); }")
private external fun disconnectResizeObserver(ro: JsAny)

@JsFun("(fn) => fn")
private external fun wrapCallback(fn: (Int, Int) -> Unit): JsAny

/**
 * Wraps a Kotlin `(JsAny) -> Unit` lambda into a JS function callable from
 * `addEventListener`. The `(fn) => fn` of the `@JsFun` triggers the conversion by the
 * Kotlin/Wasm compiler, which produces a real JS closure — equivalent to the pattern
 * used for the `ResizeObserver` above.
 *
 * Without this wrapper, `handler.toJsReference()` produced an opaque reference not
 * directly callable by the DOM (the listeners were registered but never
 * invoked → keyboard/pointer/wheel inert on the wasmJs side).
 */
@JsFun("(fn) => fn")
private external fun wrapEventHandler(fn: (JsAny) -> Unit): JsAny

/**
 * Creates a canvas (id + dimensions) and appends it to the parent (parentId or body).
 * If a canvas with that id already exists, returns it as is without recreating.
 * Returns `true` if the canvas now exists in the DOM (created or pre-existing).
 */
@JsFun("""(id, width, height, parentId) => {
    let canvas = document.getElementById(id);
    if (canvas) return true;
    canvas = document.createElement('canvas');
    canvas.id = id;
    canvas.width = width;
    canvas.height = height;
    canvas.tabIndex = 0;
    const parent = parentId ? document.getElementById(parentId) : document.body;
    if (!parent) return false;
    parent.appendChild(canvas);
    return true;
}""")
private external fun ensureCanvasInDom(
    id: JsString,
    width: Int,
    height: Int,
    parentId: JsString?,
): JsBoolean

// ---------------------------------------------------------------------------
// Implementation
// ---------------------------------------------------------------------------

/**
 * wasmJs DOM bridge to the Kadre engine.
 *
 * Uses Wasm JS interop via [JsFun] to access DOM APIs not directly
 * available in the Kotlin/Wasm wasmJs runtime.
 */
class WasmJsWebDomBridge : WebDomBridge {

    override var onWindowEvent: ((WebWindowEvent) -> Unit)? = null

    override fun ensureCanvas(attrs: WebWindowAttributes): String {
        val id = attrs.effectiveCanvasId
        val existing = getElementById(id.toJsString())
        if (existing != null) return id
        if (!attrs.appendToBody) {
            println("[WasmJsWebDomBridge] Canvas '$id' introuvable (appendToBody=false → pas de création)")
            return id
        }
        val ok = ensureCanvasInDom(
            id.toJsString(),
            attrs.width,
            attrs.height,
            attrs.parentElementId?.toJsString(),
        ).toBoolean()
        if (!ok) {
            println("[WasmJsWebDomBridge] Aucun parent disponible (parentElementId='${attrs.parentElementId}', body absent)")
        } else {
            println("[WasmJsWebDomBridge] Canvas '$id' (${attrs.width}×${attrs.height}) créé et ajouté")
        }
        return id
    }

    private var targetElement: JsEventTarget? = null
    private val listenerRefs = mutableListOf<Triple<JsEventTarget, String, JsAny>>()
    private var resizeObserverRef: JsAny? = null

    override fun attach(targetElementId: String) {
        val canvas = getElementById(targetElementId.toJsString()) ?: return
        targetElement = canvas

        // --- Keyboard ---
        addDomListener(canvas, "keydown") { e ->
            val ke = e.unsafeCast<JsKeyboardEvent>()
            dispatch(
                WebWindowEvent.KeyboardInput(
                    key = domCodeToKey(ke.code.toString()),
                    state = WebKeyState.Pressed,
                    modifiers = domModifiers(
                        shiftKey = ke.shiftKey.toBoolean(),
                        ctrlKey  = ke.ctrlKey.toBoolean(),
                        altKey   = ke.altKey.toBoolean(),
                        metaKey  = ke.metaKey.toBoolean(),
                    ),
                    isRepeat = ke.repeat.toBoolean(),
                )
            )
        }

        addDomListener(canvas, "keyup") { e ->
            val ke = e.unsafeCast<JsKeyboardEvent>()
            dispatch(
                WebWindowEvent.KeyboardInput(
                    key = domCodeToKey(ke.code.toString()),
                    state = WebKeyState.Released,
                    modifiers = domModifiers(
                        shiftKey = ke.shiftKey.toBoolean(),
                        ctrlKey  = ke.ctrlKey.toBoolean(),
                        altKey   = ke.altKey.toBoolean(),
                        metaKey  = ke.metaKey.toBoolean(),
                    ),
                    isRepeat = false,
                )
            )
        }

        // --- Pointer ---
        addDomListener(canvas, "pointermove") { e ->
            val pe = e.unsafeCast<JsPointerEvent>()
            dispatch(WebWindowEvent.PointerMoved(x = pe.clientX.toDouble(), y = pe.clientY.toDouble()))
        }

        addDomListener(canvas, "pointerdown") { e ->
            val pe = e.unsafeCast<JsPointerEvent>()
            dispatch(
                WebWindowEvent.MouseInput(
                    button = domButtonToMouseButton(pe.button.toDouble().toInt().toShort()),
                    state = WebKeyState.Pressed,
                )
            )
        }

        addDomListener(canvas, "pointerup") { e ->
            val pe = e.unsafeCast<JsPointerEvent>()
            dispatch(
                WebWindowEvent.MouseInput(
                    button = domButtonToMouseButton(pe.button.toDouble().toInt().toShort()),
                    state = WebKeyState.Released,
                )
            )
        }

        addDomListener(canvas, "pointerenter") { _ ->
            dispatch(WebWindowEvent.PointerEntered)
        }

        addDomListener(canvas, "pointerleave") { _ ->
            dispatch(WebWindowEvent.PointerLeft)
        }

        // --- Wheel ---
        addDomListener(canvas, "wheel") { e ->
            val we = e.unsafeCast<JsWheelEvent>()
            val mode = we.deltaMode.toDouble().toInt()
            dispatch(
                WebWindowEvent.MouseWheel(
                    deltaX = normalizeWheelDelta(we.deltaX.toDouble(), mode),
                    deltaY = normalizeWheelDelta(we.deltaY.toDouble(), mode),
                )
            )
        }

        // --- ResizeObserver ---
        val callback: (Int, Int) -> Unit = { w, h ->
            dispatch(WebWindowEvent.Resized(width = w, height = h))
        }
        resizeObserverRef = createResizeObserver(canvas, wrapCallback(callback))

        // --- Visibility ---
        val doc = getDocument()
        addDomListener(doc, "visibilitychange") { _ ->
            val hidden = isDocumentHidden().toBoolean()
            dispatch(WebWindowEvent.Focused(gained = !hidden))
        }
    }

    override fun detach() {
        for ((target, type, ref) in listenerRefs) {
            jsRemoveEventListener(target, type.toJsString(), ref)
        }
        listenerRefs.clear()

        resizeObserverRef?.let { disconnectResizeObserver(it) }
        resizeObserverRef = null
        targetElement = null
    }

    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    private fun addDomListener(target: JsEventTarget, type: String, handler: (JsAny) -> Unit) {
        // `wrapEventHandler` triggers the JS wrapping that makes the Kotlin lambda
        // callable from `addEventListener` (see the wrapper doc above).
        val ref = wrapEventHandler(handler)
        jsAddEventListener(target, type.toJsString(), ref)
        listenerRefs.add(Triple(target, type, ref))
    }

    private fun dispatch(event: WebWindowEvent) {
        onWindowEvent?.invoke(event)
    }
}
