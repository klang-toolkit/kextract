/**
 * Implémentation JS de [WebDomBridge].
 *
 * Attache tous les écouteurs DOM nécessaires au canvas cible et les retire
 * lors du détachement. Les événements DOM sont convertis en [WebWindowEvent]
 * via les fonctions pures de [DomEventMapper].
 *
 * Ce fichier PEUT utiliser kotlinx.browser et org.w3c.dom.* car il est dans jsMain.
 *
 * @since 0.1.0
 */
package org.graphiks.kadre.web

import kotlinx.browser.document
import org.w3c.dom.Element
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.WheelEvent

/**
 * Pont DOM JS vers le moteur Kadre.
 *
 * Écoute et dispatche les événements DOM suivants :
 * - Clavier : `keydown` / `keyup` → [WebWindowEvent.KeyboardInput]
 * - Pointeur : `pointermove` → [WebWindowEvent.PointerMoved]
 * - Pointeur : `pointerdown` / `pointerup` → [WebWindowEvent.MouseInput]
 * - Pointeur : `pointerenter` / `pointerleave` → [WebWindowEvent.PointerEntered] / [WebWindowEvent.PointerLeft]
 * - Molette : `wheel` → [WebWindowEvent.MouseWheel]
 * - Redimensionnement : `ResizeObserver` sur le canvas → [WebWindowEvent.Resized]
 * - Visibilité : `visibilitychange` → [WebWindowEvent.Focused]
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
        // tabIndex pour rendre le canvas focusable (sans cela, keydown/keyup ne remontent pas).
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
    private var resizeObserver: dynamic = null

    override fun attach(targetElementId: String) {
        val canvas = document.getElementById(targetElementId) ?: return
        targetElement = canvas

        // --- Clavier ---
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

        // --- Pointeur (PointerEvent unifié) ---
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

        // --- Molette ---
        addListener(canvas, "wheel") { e ->
            val we = e as WheelEvent
            dispatch(
                WebWindowEvent.MouseWheel(
                    deltaX = normalizeWheelDelta(we.deltaX, we.deltaMode),
                    deltaY = normalizeWheelDelta(we.deltaY, we.deltaMode),
                )
            )
        }

        // --- Redimensionnement via ResizeObserver ---
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

        // --- Visibilité de page → Suspended/Resumed via Focused ---
        addDocumentListener("visibilitychange") { _ ->
            val hidden: Boolean = js("document.hidden")
            dispatch(WebWindowEvent.Focused(gained = !hidden))
        }
    }

    /**
     * Appelé depuis le ResizeObserver JS avec les nouvelles dimensions.
     */
    @JsName("dispatchResized")
    fun dispatchResized(width: Int, height: Int) {
        dispatch(WebWindowEvent.Resized(width = width, height = height))
    }

    override fun detach() {
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

        resizeObserver?.disconnect()
        resizeObserver = null
        targetElement = null
    }

    // -----------------------------------------------------------------------
    // Helpers privés
    // -----------------------------------------------------------------------

    private fun addListener(target: Element, type: String, handler: (Event) -> Unit) {
        target.addEventListener(type, handler)
        canvasListeners.add(Pair(type, handler))
    }

    private fun addDocumentListener(type: String, handler: (Event) -> Unit) {
        document.asDynamic().addEventListener(type, handler)
        documentListeners.add(Pair(type, handler))
    }

    private fun dispatch(event: WebWindowEvent) {
        onWindowEvent?.invoke(event)
    }
}

/**
 * Interface externe décrivant les champs d'un PointerEvent DOM accessibles en JS.
 *
 * Utilisée pour accéder à `clientX`, `clientY` et `button` sur les événements
 * de type `pointerdown`, `pointerup` et `pointermove`, qui héritent de MouseEvent
 * mais dont le cast direct via `as MouseEvent` n'est pas toujours stable en IR.
 */
private external interface PointerEventData {
    val clientX: Double
    val clientY: Double
    val button: Short
}
