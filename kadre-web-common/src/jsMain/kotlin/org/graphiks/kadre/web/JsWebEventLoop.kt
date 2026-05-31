/**
 * Implémentation JS de [WebEventLoop] via `window.requestAnimationFrame`.
 *
 * Ce fichier réside dans `jsMain` — il peut utiliser `kotlinx.browser`
 * et `org.w3c.dom.*` pour accéder aux API DOM du navigateur.
 *
 * ## requestAnimationFrame
 * Le navigateur appelle le callback RAF avant chaque repaint, typiquement à 60 Hz
 * (ou au taux de rafraîchissement de l'écran). Le paramètre `timestamp` est
 * passé en millisecondes depuis l'origine de la page.
 *
 * ## setTimeout (mode WaitUntil)
 * En mode [ControlFlow.WaitUntil], un `setTimeout` est planifié pour l'instant cible.
 * Le délai est calculé en millisecondes depuis `Date.now()`. Si l'instant est déjà
 * passé, le délai est de 0 (exécution dès que possible).
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import kotlinx.browser.window
import kotlin.js.Date

/**
 * Boucle d'événements JS — orchestre les frames via `window.requestAnimationFrame`.
 */
class JsWebEventLoop : WebEventLoop() {

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
                window.requestAnimationFrame { timestamp ->
                    rafPending = false
                    tick(handler, timestamp)
                }
            }
            is ControlFlow.Wait -> {
                // En mode Wait, on attend un événement DOM.
                // scheduleWakeUp() sera appelé par le pont DOM lorsqu'un événement arrive.
            }
            is ControlFlow.WaitUntil -> {
                val delayMs = maxOf(0L, cf.instant - Date.now().toLong()).toInt()
                window.setTimeout({
                    if (!rafPending) {
                        rafPending = true
                        window.requestAnimationFrame { timestamp ->
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
            window.requestAnimationFrame { timestamp ->
                rafPending = false
                // Récupère le handler via un champ mémorisé — voir runApp
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
     * Crée un [JsWebDomBridge] — pont DOM JS vers le moteur Kadre.
     */
    override fun createDomBridge(): WebDomBridge = JsWebDomBridge()
}
