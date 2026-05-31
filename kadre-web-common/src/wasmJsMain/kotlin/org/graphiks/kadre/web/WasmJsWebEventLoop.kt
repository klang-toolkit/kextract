/**
 * Implémentation wasmJs de [WebEventLoop] via `requestAnimationFrame` interop Wasm.
 *
 * Ce fichier réside dans `wasmJsMain` — il peut utiliser les déclarations `external`
 * et l'interopérabilité JS Wasm (JsAny, JsReference, etc.).
 *
 * ## requestAnimationFrame via interop Wasm
 * L'API `window.requestAnimationFrame` est exposée via une déclaration `external`
 * puisque les bindings automatiques DOM ne sont pas disponibles en wasmJs comme en JS.
 *
 * ## setTimeout (mode WaitUntil)
 * En mode [ControlFlow.WaitUntil], un `setTimeout` est planifié pour l'instant cible.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow

// ---------------------------------------------------------------------------
// Interop JS Wasm — requestAnimationFrame et setTimeout
// ---------------------------------------------------------------------------

/** Callback passé à requestAnimationFrame : reçoit le timestamp en ms. */
private external fun requestAnimationFrame(callback: (Double) -> Unit): Int

/**
 * Planifie l'exécution d'un callback après [delayMs] millisecondes.
 *
 * @param callback Callback à exécuter.
 * @param delayMs  Délai en millisecondes (0 = dès que possible).
 * @return Identifiant du timer (non utilisé ici).
 */
private external fun setTimeout(callback: () -> Unit, delayMs: Int): Int

/** Retourne le timestamp actuel en millisecondes depuis l'époque Unix. */
private external fun dateNow(): Double

// ---------------------------------------------------------------------------
// WasmJsWebEventLoop
// ---------------------------------------------------------------------------

/**
 * Boucle d'événements wasmJs — orchestre les frames via `requestAnimationFrame` interop Wasm.
 */
class WasmJsWebEventLoop : WebEventLoop() {

    /** true si un RAF est déjà en file d'attente, pour éviter les doublons en mode Wait. */
    private var rafPending = false

    /**
     * Planifie la prochaine frame selon le [controlFlow] courant.
     *
     * - [ControlFlow.Poll]      → RAF immédiat
     * - [ControlFlow.Wait]      → pas de RAF (sera déclenché par [scheduleWakeUp])
     * - [ControlFlow.WaitUntil] → setTimeout jusqu'à [ControlFlow.WaitUntil.instant], puis RAF
     */
    override fun scheduleNextFrame(handler: ApplicationHandler) {
        when (val cf = controlFlow) {
            is ControlFlow.Poll -> {
                rafPending = true
                requestAnimationFrame { timestamp ->
                    rafPending = false
                    tick(handler, timestamp)
                }
            }
            is ControlFlow.Wait -> {
                // En mode Wait, on attend un événement DOM.
                // scheduleWakeUp() sera appelé par le pont DOM lorsqu'un événement arrive.
            }
            is ControlFlow.WaitUntil -> {
                val delayMs = maxOf(0L, cf.instant - dateNow().toLong()).toInt()
                setTimeout({
                    if (!rafPending) {
                        rafPending = true
                        requestAnimationFrame { timestamp ->
                            rafPending = false
                            tick(handler, timestamp)
                        }
                    }
                }, delayMs)
            }
        }
    }

    /**
     * Réveille la boucle via un RAF unique.
     *
     * Appelé en mode [ControlFlow.Wait] lorsqu'un événement DOM arrive,
     * ou depuis [createProxy] pour notifier depuis un autre contexte.
     * Guard [rafPending] pour éviter les RAF en double.
     */
    override fun scheduleWakeUp() {
        if (!rafPending) {
            rafPending = true
            requestAnimationFrame { timestamp ->
                rafPending = false
                _pendingWakeUpHandler?.let { tick(it, timestamp) }
            }
        }
    }

    /** Handler mémorisé pour [scheduleWakeUp] hors du contexte de [scheduleNextFrame]. */
    private var _pendingWakeUpHandler: ApplicationHandler? = null

    override fun runApp(handler: ApplicationHandler) {
        _pendingWakeUpHandler = handler
        super.runApp(handler)
    }

    /**
     * Crée un [WasmJsWebDomBridge] — pont DOM wasmJs vers le moteur Kadre.
     */
    override fun createDomBridge(): WebDomBridge = WasmJsWebDomBridge()
}
