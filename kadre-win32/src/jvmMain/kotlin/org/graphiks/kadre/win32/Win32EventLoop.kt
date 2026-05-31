/**
 * Implémentation Win32 de [ActiveEventLoop] et point d'entrée [runApp].
 *
 * [Win32EventLoop] implémente [ActiveEventLoop] et est passé à chaque
 * callback de [ApplicationHandler]. La méthode [runApp] orchestre
 * l'initialisation Win32 (enregistrement KadreWndProc) et la boucle de
 * messages avec commutation dynamique selon [ControlFlow] :
 *
 * - [ControlFlow.Poll]      → PeekMessageW (PM_REMOVE) — non-bloquant
 * - [ControlFlow.Wait]      → GetMessageW — bloquant jusqu'au prochain message
 * - [ControlFlow.WaitUntil] → MsgWaitForMultipleObjectsEx avec timeout en ms
 *
 * Pattern Lazy FFM (tryCreate) : tous les MethodHandle sont null sur macOS/Linux,
 * ce qui permet au build de passer sur toutes les plateformes.
 *
 * GRA-11 : Win32EventLoop — boucle de messages Win32 avec commutation ControlFlow.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

// ── Verrou d'instance unique ──────────────────────────────────────────────────

/**
 * Verrou global garantissant qu'une seule boucle d'événements Win32 est active
 * à la fois dans le processus.
 *
 * Utilise AtomicBoolean pour la thread-safety : [runApp] fait un CAS atomique
 * false→true au démarrage et lève [IllegalStateException] si déjà true.
 */
internal val win32Running = AtomicBoolean(false)

// ── Win32EventLoop ────────────────────────────────────────────────────────────

/**
 * Implémentation interne de [ActiveEventLoop] pour la plateforme Win32 (Windows).
 *
 * Une instance est créée par appel à [runApp] et passée comme récepteur
 * à tous les callbacks [ApplicationHandler].
 *
 * ### Cycle de vie
 * ```
 * runApp(handler)
 *   └─ handler.resumed(this)
 *   └─ boucle de messages
 *        ├─ handler.newEvents(this, cause)
 *        ├─ pump messages selon ControlFlow
 *        └─ handler.aboutToWait(this)
 *   └─ handler.suspended(this)
 * ```
 *
 * ### Thread-safety
 * - [_controlFlow] est @Volatile : lisible depuis tout thread.
 * - [_isExiting] est @Volatile : lisible depuis tout thread.
 * - [windows] est un ConcurrentHashMap.
 * - La boucle de messages elle-même s'exécute dans le thread appelant.
 */
internal class Win32EventLoop : ActiveEventLoop {

    /** Fenêtres vivantes : windowId (HWND address) → Win32Window. */
    internal val windows = ConcurrentHashMap<Long, Win32Window>()

    @Volatile
    private var _isExiting = false

    override val isExiting: Boolean
        get() = _isExiting

    @Volatile
    private var _controlFlow: ControlFlow = ControlFlow.Wait

    override val controlFlow: ControlFlow
        get() = _controlFlow

    override fun setControlFlow(controlFlow: ControlFlow) {
        _controlFlow = controlFlow
    }

    /**
     * Crée une nouvelle fenêtre Win32 native et l'enregistre dans la table de fenêtres.
     *
     * Installe également le handler [KadreWndProc] si ce n'est pas encore fait.
     *
     * @param attributes Attributs de configuration de la fenêtre.
     * @return La fenêtre créée.
     * @throws IllegalStateException si les bindings Win32 ne sont pas disponibles
     *         (macOS/Linux) ou si la création de la fenêtre échoue.
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        val window = Win32Window.create(attributes)
            ?: error(
                "Win32Window.create() a retourné null — les bindings Win32 (user32.dll) " +
                "ne sont pas disponibles sur cette plateforme."
            )
        windows[window.id.value] = window
        return window
    }

    /**
     * Demande l'arrêt de la boucle d'événements Win32.
     *
     * Lève [_isExiting] puis appelle PostQuitMessage(0) pour insérer WM_QUIT
     * dans la file de messages, ce qui provoque la sortie de GetMessageW.
     */
    override fun exit() {
        _isExiting = true
        postQuitMessage?.invoke(0)
    }

    /**
     * Crée un proxy thread-safe vers cette boucle d'événements.
     *
     * Le proxy utilise PostThreadMessageW(WM_NULL) pour réveiller la boucle
     * depuis un thread secondaire.
     */
    override fun createProxy(): EventLoopProxy = Win32EventLoopProxy.create()

    // ── Boucle de messages ────────────────────────────────────────────────────

    /**
     * Lance la boucle de messages Win32.
     *
     * Doit être appelé depuis le thread principal Windows (thread qui a créé
     * les fenêtres). Bloquant — ne retourne qu'à la fermeture de l'application.
     *
     * @param handler Gestionnaire du cycle de vie et des événements.
     */
    internal fun runMessageLoop(handler: ApplicationHandler) {
        // Arène confinée pour le segment MSG — libérée à la sortie de la boucle
        Arena.ofConfined().use { arena ->
            val msg = arena.allocateMsg()
            var startCause: StartCause = StartCause.Init

            while (!_isExiting) {
                // Notifier le gestionnaire du début d'itération
                handler.newEvents(this, startCause)
                if (_isExiting) break

                // Dispatcher les messages selon le ControlFlow courant
                startCause = dispatchMessages(msg, handler)

                // Notifier le gestionnaire que la boucle est sur le point d'attendre
                handler.aboutToWait(this)
            }
        }
    }

    /**
     * Dispatche les messages Win32 selon le [ControlFlow] courant.
     *
     * @param msg    Segment mémoire MSG pré-alloué.
     * @param handler Gestionnaire d'événements (pour router les événements de fenêtre).
     * @return La [StartCause] de la prochaine itération.
     */
    private fun dispatchMessages(msg: MemorySegment, handler: ApplicationHandler): StartCause {
        return when (val cf = _controlFlow) {
            is ControlFlow.Poll -> dispatchPoll(msg, handler)
            is ControlFlow.Wait -> dispatchWait(msg, handler)
            is ControlFlow.WaitUntil -> dispatchWaitUntil(msg, handler, cf.instant)
        }
    }

    /**
     * Mode Poll : PeekMessageW (PM_REMOVE) — non-bloquant.
     *
     * Vide la file de messages en une passe (traite tous les messages disponibles)
     * et retourne immédiatement même si la file est vide.
     */
    private fun dispatchPoll(msg: MemorySegment, handler: ApplicationHandler): StartCause {
        val peekHandle = peekMessageW
        val translateHandle = translateMessage
        val dispatchHandle = dispatchMessageW

        if (peekHandle != null && translateHandle != null && dispatchHandle != null) {
            while (!_isExiting) {
                val hasMsg = peekHandle.invokeExact(
                    msg,
                    MemorySegment.NULL,  // hWnd: NULL = tous les messages du thread
                    0,                   // wMsgFilterMin: aucun filtre
                    0,                   // wMsgFilterMax: aucun filtre
                    PM_REMOVE,           // wRemoveMsg: retirer de la file
                ) as Int
                if (hasMsg == 0) break  // file vide, sortir du pump
                translateHandle.invokeExact(msg) as Int
                dispatchHandle.invokeExact(msg) as Long
            }
        }
        return StartCause.Poll
    }

    /**
     * Mode Wait : GetMessageW — bloquant jusqu'au prochain message.
     *
     * Bloque le thread jusqu'à la réception d'un message (ou WM_QUIT).
     * Retourne false (StartCause.WaitCancelled) si WM_QUIT est reçu.
     */
    private fun dispatchWait(msg: MemorySegment, handler: ApplicationHandler): StartCause {
        val getHandle = getMessageW
        val translateHandle = translateMessage
        val dispatchHandle = dispatchMessageW

        if (getHandle != null && translateHandle != null && dispatchHandle != null) {
            val result = getHandle.invokeExact(
                msg,
                MemorySegment.NULL,  // hWnd: NULL = tous les messages du thread
                0,                   // wMsgFilterMin
                0,                   // wMsgFilterMax
            ) as Int

            when {
                result > 0 -> {
                    // Message normal → translate + dispatch
                    translateHandle.invokeExact(msg) as Int
                    dispatchHandle.invokeExact(msg) as Long
                }
                result == 0 -> {
                    // WM_QUIT → sortie propre
                    _isExiting = true
                }
                // result < 0 : erreur — on ignore et on continue
            }
        }
        return StartCause.WaitCancelled()
    }

    /**
     * Mode WaitUntil : MsgWaitForMultipleObjectsEx avec timeout.
     *
     * Calcule le timeout restant jusqu'à [targetInstant] (en ms depuis l'époque Unix)
     * et attend soit un message, soit l'expiration du timeout.
     *
     * @param targetInstant Instant cible en millisecondes depuis l'époque Unix.
     */
    private fun dispatchWaitUntil(
        msg: MemorySegment,
        handler: ApplicationHandler,
        targetInstant: Long,
    ): StartCause {
        val waitHandle = msgWaitForMultipleObjectsEx
        val peekHandle = peekMessageW
        val translateHandle = translateMessage
        val dispatchHandle = dispatchMessageW

        val now = System.currentTimeMillis()
        val timeoutMs = (targetInstant - now).coerceIn(0L, Int.MAX_VALUE.toLong()).toInt()

        if (waitHandle != null) {
            val result = waitHandle.invokeExact(
                0,                   // nCount: aucun objet kernel à attendre
                MemorySegment.NULL,  // pHandles: NULL car nCount = 0
                timeoutMs,           // dwMilliseconds: timeout calculé
                QS_ALLINPUT,         // dwWakeMask: tous les messages d'entrée
                MWMO_INPUTAVAILABLE, // dwFlags: réveiller si messages déjà disponibles
            ) as Int

            when (result) {
                WAIT_OBJECT_0 -> {
                    // Messages disponibles → pump avec PeekMessageW
                    if (peekHandle != null && translateHandle != null && dispatchHandle != null) {
                        while (!_isExiting) {
                            val hasMsg = peekHandle.invokeExact(
                                msg,
                                MemorySegment.NULL,
                                0, 0,
                                PM_REMOVE,
                            ) as Int
                            if (hasMsg == 0) break
                            translateHandle.invokeExact(msg) as Int
                            dispatchHandle.invokeExact(msg) as Long
                        }
                    }
                    return StartCause.WaitCancelled(targetInstant)
                }
                WAIT_TIMEOUT -> {
                    // Timeout expiré → instant cible atteint
                    return StartCause.ResumeTimeReached(
                        requestedResume = targetInstant,
                        start = System.currentTimeMillis(),
                    )
                }
                else -> {
                    // Autre code de retour (erreur ou signal inattendu)
                    return StartCause.WaitCancelled(targetInstant)
                }
            }
        } else {
            // Bindings indisponibles (macOS/Linux) — simuler Poll
            return StartCause.Poll
        }
    }
}

// ── Point d'entrée ────────────────────────────────────────────────────────────

/**
 * Point d'entrée de la boucle d'événements kadre sur Windows.
 *
 * Initialise Win32 (installe KadreWndProc), crée une [Win32EventLoop],
 * appelle [ApplicationHandler.resumed], puis lance la boucle de messages
 * bloquante. Ne retourne qu'à la fermeture de l'application.
 *
 * Doit être appelé depuis le thread principal Windows (thread de messages).
 *
 * @param handler Gestionnaire du cycle de vie et des événements.
 * @throws IllegalStateException si une boucle Win32 est déjà active dans ce processus.
 */
fun runApp(handler: ApplicationHandler) {
    check(win32Running.compareAndSet(false, true)) {
        "Win32EventLoop.runApp() ne peut être appelé qu'une seule fois par processus. " +
        "Une boucle d'événements Win32 est déjà active."
    }

    // Activer Per-Monitor-V2 avant toute création de fenêtre, sinon Windows
    // virtualise le DPI et rend le contenu flou sur les écrans haute densité.
    enablePerMonitorV2DpiAwareness()

    val eventLoop = Win32EventLoop()

    // Installer le handler KadreWndProc pour router les messages vers les fenêtres
    KadreWndProc.install { hwnd, event ->
        val windowId = WindowId(hwnd)
        handler.windowEvent(eventLoop, windowId, event)

        // Gérer la destruction de fenêtre : retirer de la table + éventuellement quitter
        if (event is WindowEvent.Destroyed) {
            eventLoop.windows.remove(hwnd)
        }
    }

    try {
        // Notifier le gestionnaire que l'application reprend
        handler.resumed(eventLoop)

        // Notifier que les surfaces peuvent être créées
        handler.canCreateSurfaces(eventLoop)

        // Lancer la boucle de messages bloquante
        eventLoop.runMessageLoop(handler)
    } finally {
        // Nettoyage final
        handler.suspended(eventLoop)
        KadreWndProc.uninstall()
        win32Running.set(false)
    }
}
