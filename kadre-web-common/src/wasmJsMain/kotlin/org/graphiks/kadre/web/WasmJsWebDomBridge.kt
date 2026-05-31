/**
 * Implémentation wasmJs de [WebDomBridge].
 *
 * Fournit un stub fonctionnel basé sur l'interop JS Wasm (external interface).
 * Les écouteurs DOM sont enregistrés via [JsAny] et [addEventListener] Wasm,
 * et les champs d'événements sont extraits via les external interfaces ci-dessous.
 *
 * Ce fichier PEUT utiliser les interops JS Wasm car il est dans wasmJsMain.
 *
 * @since 0.1.0
 */
package org.graphiks.kadre.web

// ---------------------------------------------------------------------------
// External interfaces — accès DOM côté wasmJs
// ---------------------------------------------------------------------------

/**
 * Représentation externe d'un [EventTarget] DOM (canvas, document, etc.).
 */
@JsName("EventTarget")
external interface JsEventTarget : JsAny {
    fun addEventListener(type: JsString, listener: JsAny)
    fun removeEventListener(type: JsString, listener: JsAny)
}

/**
 * Propriétés communes à tous les événements DOM.
 */
@JsName("Event")
external interface JsDomEvent : JsAny {
    val type: JsString
}

/**
 * Propriétés d'un KeyboardEvent DOM.
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
 * Propriétés d'un PointerEvent DOM (inclut les champs MouseEvent).
 */
@JsName("PointerEvent")
external interface JsPointerEvent : JsDomEvent {
    val clientX: JsNumber
    val clientY: JsNumber
    val button: JsNumber
}

/**
 * Propriétés d'un WheelEvent DOM.
 */
@JsName("WheelEvent")
external interface JsWheelEvent : JsDomEvent {
    val deltaX: JsNumber
    val deltaY: JsNumber
    val deltaMode: JsNumber
}

// ---------------------------------------------------------------------------
// Fonctions d'interop JS Wasm — accès au DOM global
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
 * Wrappe une lambda Kotlin `(JsAny) -> Unit` en une fonction JS appelable depuis
 * `addEventListener`. Le `(fn) => fn` du `@JsFun` enclenche la conversion par le
 * compilateur Kotlin/Wasm qui produit une vraie closure JS — équivalent du pattern
 * utilisé pour le `ResizeObserver` ci-dessus.
 *
 * Sans ce wrapper, `handler.toJsReference()` produisait une référence opaque non
 * directement appelable par le DOM (les listeners étaient enregistrés mais jamais
 * invoqués → clavier/pointer/wheel inertes côté wasmJs).
 */
@JsFun("(fn) => fn")
private external fun wrapEventHandler(fn: (JsAny) -> Unit): JsAny

/**
 * Crée un canvas (id + dimensions) et l'append au parent (parentId ou body).
 * Si un canvas portant cet id existe déjà, le retourne tel quel sans recréer.
 * Retourne `true` si le canvas existe désormais dans le DOM (créé ou préexistant).
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
// Implémentation
// ---------------------------------------------------------------------------

/**
 * Pont DOM wasmJs vers le moteur Kadre.
 *
 * Utilise l'interop JS Wasm via [JsFun] pour accéder aux API DOM non directement
 * disponibles dans le runtime wasmJs de Kotlin/Wasm.
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

        // --- Clavier ---
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

        // --- Pointeur ---
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

        // --- Molette ---
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

        // --- Visibilité ---
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
    // Helpers privés
    // -----------------------------------------------------------------------

    private fun addDomListener(target: JsEventTarget, type: String, handler: (JsAny) -> Unit) {
        // `wrapEventHandler` enclenche le wrapping JS qui rend la lambda Kotlin
        // appelable depuis `addEventListener` (cf. doc du wrapper plus haut).
        val ref = wrapEventHandler(handler)
        jsAddEventListener(target, type.toJsString(), ref)
        listenerRefs.add(Triple(target, type, ref))
    }

    private fun dispatch(event: WebWindowEvent) {
        onWindowEvent?.invoke(event)
    }
}
