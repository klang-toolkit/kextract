/**
 * WaylandEventLoop — boucle d'événements Wayland pour kadre.
 *
 * Implémente la séquence canonique Wayland prepare_read / poll / read_events
 * avec un eventfd pour le réveil inter-thread (wakeUp).
 *
 * Séquence de pompe :
 *  1. while (wl_display_prepare_read != 0) → wl_display_dispatch_pending
 *  2. wl_display_flush
 *  3. poll([displayFd, eventfdFd], timeout)
 *  4. Si displayFd prêt → wl_display_read_events + dispatch_pending
 *     Sinon            → wl_display_cancel_read
 *  5. Si eventfdFd prêt → read(eventfd) pour vider le compteur
 *
 * Redmine #66 — WaylandEventLoop.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

// ── Singleton guard ───────────────────────────────────────────────────────────

/**
 * Garantit qu'une seule boucle Wayland tourne à la fois.
 * compareAndSet(false, true) avant de démarrer, set(false) dans finally.
 */
internal val waylandRunning = AtomicBoolean(false)

// ── WaylandEventLoop ──────────────────────────────────────────────────────────

/**
 * Boucle d'événements active Wayland, passée aux callbacks de [ApplicationHandler].
 *
 * Créée et contrôlée par [runApp]. Les fenêtres créées ici sont stockées dans
 * [windows] pour permettre la distribution des événements.
 *
 * @param displayPtr  Adresse du wl_display* (Long, jamais 0).
 * @param compositorPtr Adresse du wl_compositor* (Long, 0 si non disponible).
 * @param xdgWmBasePtr  Adresse du xdg_wm_base* (Long, 0 si non disponible).
 * @param eventFd     Descripteur eventfd pour le réveil inter-thread.
 */
class WaylandEventLoop internal constructor(
    internal val displayPtr: Long,
    internal val compositorPtr: Long,
    internal val xdgWmBasePtr: Long,
    internal val eventFd: Int,
) : ActiveEventLoop {

    /** Fenêtres actives indexées par l'adresse de leur wl_surface*. */
    internal val windows = ConcurrentHashMap<Long, WaylandWindow>()

    @Volatile private var _isExiting = false
    override val isExiting: Boolean get() = _isExiting

    @Volatile private var _controlFlow: ControlFlow = ControlFlow.Wait
    override val controlFlow: ControlFlow get() = _controlFlow

    override fun setControlFlow(controlFlow: ControlFlow) {
        _controlFlow = controlFlow
    }

    override fun exit() {
        _isExiting = true
    }

    /**
     * Crée une fenêtre Wayland native et l'enregistre dans [windows].
     *
     * @param attributes Paramètres de configuration de la fenêtre.
     * @return La fenêtre créée, ou lève IllegalStateException si libwayland absent.
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        val window = WaylandWindow.create(
            display = displayPtr,
            compositor = compositorPtr,
            xdgWmBase = xdgWmBasePtr,
            attrs = attributes,
        ) ?: error("WaylandWindow.create failed — libwayland-client.so.0 absent or display invalid")
        windows[window.id.value] = window
        return window
    }

    /**
     * Crée un proxy thread-safe vers cette boucle d'événements.
     *
     * Le proxy utilise l'eventfd pour réveiller la boucle depuis n'importe quel thread.
     */
    override fun createProxy(): EventLoopProxy = WaylandEventLoopProxy(eventFd)
}

// ── runApp ────────────────────────────────────────────────────────────────────

/**
 * Démarre la boucle d'événements Wayland et délègue le cycle de vie à [handler].
 *
 * Bloquant : ne retourne qu'à la fin de la boucle (via [ActiveEventLoop.exit]
 * ou fermeture de toutes les fenêtres).
 *
 * @throws IllegalStateException si une boucle Wayland est déjà en cours.
 * @throws IllegalStateException si wl_display_connect échoue.
 */
fun runApp(handler: ApplicationHandler) {
    if (!waylandRunning.compareAndSet(false, true)) {
        error("WaylandEventLoop déjà en cours d'exécution")
    }
    try {
        runAppInternal(handler)
    } finally {
        waylandRunning.set(false)
    }
}

// ── Implémentation interne ────────────────────────────────────────────────────

private fun runAppInternal(handler: ApplicationHandler) {
    // ── 1. Connexion au serveur Wayland ───────────────────────────────────────
    val connectHandle = wlDisplayConnect
        ?: error("wl_display_connect non disponible — libwayland-client.so.0 absent")

    val displaySeg: MemorySegment = Arena.ofConfined().use { arena ->
        val nullSeg = MemorySegment.NULL
        try {
            connectHandle.invokeExact(nullSeg) as MemorySegment
        } catch (t: Throwable) {
            error("wl_display_connect a levé une exception : $t")
        }
    }

    if (displaySeg == MemorySegment.NULL || displaySeg.address() == 0L) {
        error("wl_display_connect a retourné NULL — serveur Wayland non disponible (WAYLAND_DISPLAY ?)")
    }

    val displayPtr = displaySeg.address()

    // ── 2. Descripteur de fichier du socket Wayland ───────────────────────────
    val displayFd: Int = try {
        val fdHandle = wlDisplayGetFd
            ?: error("wl_display_get_fd non disponible")
        fdHandle.invokeExact(displaySeg) as Int
    } catch (t: Throwable) {
        // Fermeture propre avant de propager
        disconnectDisplay(displaySeg)
        throw t
    }

    // ── 3. Création de l'eventfd pour wakeUp ──────────────────────────────────
    val eventFd: Int = try {
        val efdHandle = nativeEventfd
            ?: error("eventfd non disponible — libc.so.6 absent")
        val fd = efdHandle.invokeExact(0, 0) as Int
        if (fd < 0) error("eventfd() a retourné $fd")
        fd
    } catch (t: Throwable) {
        disconnectDisplay(displaySeg)
        throw t
    }

    // ── 4. Découverte des globaux Wayland (compositor) ────────────────────────
    // get_registry + listener(global) + roundtrip + bind(wl_compositor) (Redmine #88).
    // xdg_wm_base reste à négocier (non requis pour créer une wl_surface / surface wgpu).
    val compositorPtr = discoverCompositor(displayPtr)
    val xdgWmBasePtr = 0L

    val eventLoop = WaylandEventLoop(displayPtr, compositorPtr, xdgWmBasePtr, eventFd)

    try {
        // ── 5. Cycle de vie : resumed ─────────────────────────────────────────
        handler.resumed(eventLoop)

        // ── 6. Premier newEvents (Init) ───────────────────────────────────────
        handler.newEvents(eventLoop, StartCause.Init)

        // ── 7. canCreateSurfaces ──────────────────────────────────────────────
        handler.canCreateSurfaces(eventLoop)

        // ── 8. Boucle principale ──────────────────────────────────────────────
        while (!eventLoop.isExiting) {
            // aboutToWait — le handler peut changer controlFlow ici
            handler.aboutToWait(eventLoop)

            // Calcul du timeout en millisecondes
            val timeoutMs: Int = when (val cf = eventLoop.controlFlow) {
                is ControlFlow.Wait -> -1
                is ControlFlow.Poll -> 0
                is ControlFlow.WaitUntil -> {
                    val delta = cf.instant - System.currentTimeMillis()
                    if (delta <= 0) 0 else delta.coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
                }
            }

            // Séquence canonique Wayland prepare_read / poll / read_events
            val startCause = pumpOnce(displaySeg, displayFd, eventFd, timeoutMs, eventLoop)

            handler.newEvents(eventLoop, startCause)
        }

        // ── 9. Fermeture ──────────────────────────────────────────────────────
        handler.destroySurfaces(eventLoop)
        handler.suspended(eventLoop)
    } finally {
        // Fermeture de l'eventfd
        try { nativeClose?.invokeExact(eventFd) } catch (_: Throwable) {}
        // Déconnexion du serveur Wayland
        disconnectDisplay(displaySeg)
    }
}

/**
 * Effectue une itération de la pompe Wayland canonique.
 *
 * Séquence :
 *  1. Vider la file d'attente (prepare_read retry)
 *  2. Flush
 *  3. poll([displayFd, eventFd], timeoutMs)
 *  4. Traitement conditionnel des résultats
 *
 * @return [StartCause] décrivant la cause du réveil.
 */
private fun pumpOnce(
    displaySeg: MemorySegment,
    displayFd: Int,
    eventFd: Int,
    timeoutMs: Int,
    eventLoop: WaylandEventLoop,
): StartCause {
    val prepareRead = wlDisplayPrepareRead
    val dispatchPending = wlDisplayDispatchPending
    val flush = wlDisplayFlush
    val readEvents = wlDisplayReadEvents
    val cancelRead = wlDisplayCancelRead

    // ── Étape 1 : préparer la lecture (vider la file d'abord si nécessaire) ──
    if (prepareRead != null && dispatchPending != null) {
        while (true) {
            val rc = try {
                prepareRead.invokeExact(displaySeg) as Int
            } catch (_: Throwable) { 0 }
            if (rc == 0) break
            // Des événements sont en file d'attente — les traiter avant de réessayer
            try { dispatchPending.invokeExact(displaySeg) } catch (_: Throwable) {}
        }
    }

    // ── Étape 2 : flush ───────────────────────────────────────────────────────
    try { flush?.invokeExact(displaySeg) } catch (_: Throwable) {}

    // ── Étape 3 : poll ───────────────────────────────────────────────────────
    val (displayReady, eventFdReady) = Arena.ofConfined().use { arena ->
        val fds = allocPollFd(arena)
        setPollFd(fds, 0, displayFd, POLLIN)
        setPollFd(fds, 1, eventFd, POLLIN)

        val pollRc = try {
            nativePoll?.invokeExact(fds, 2, timeoutMs) as? Int ?: 0
        } catch (_: Throwable) { 0 }

        if (pollRc > 0) {
            val rev0 = getPollRevents(fds, 0)
            val rev1 = getPollRevents(fds, 1)
            Pair(
                (rev0.toInt() and POLLIN.toInt()) != 0,
                (rev1.toInt() and POLLIN.toInt()) != 0,
            )
        } else {
            Pair(false, false)
        }
    }

    // ── Étape 4a : lire les événements Wayland si disponibles ────────────────
    if (displayReady && readEvents != null && dispatchPending != null) {
        try { readEvents.invokeExact(displaySeg) } catch (_: Throwable) {}
        try { dispatchPending.invokeExact(displaySeg) } catch (_: Throwable) {}
    } else if (!displayReady && cancelRead != null && wlDisplayPrepareRead != null) {
        // Annuler la lecture annoncée à l'étape 1 uniquement si prepareRead est disponible
        try { cancelRead.invokeExact(displaySeg) } catch (_: Throwable) {}
    }

    // ── Étape 4b : vider l'eventfd si déclenché ──────────────────────────────
    if (eventFdReady) {
        Arena.ofConfined().use { arena ->
            val buf = arena.allocate(8L, 8L)
            try { nativeRead?.invokeExact(eventFd, buf, 8L) } catch (_: Throwable) {}
        }
    }

    // ── Détermination de la StartCause ───────────────────────────────────────
    return when {
        eventFdReady -> StartCause.WaitCancelled()
        displayReady -> StartCause.Poll
        else -> when (val cf = eventLoop.controlFlow) {
            is ControlFlow.WaitUntil -> {
                val now = System.currentTimeMillis()
                if (now >= cf.instant) StartCause.ResumeTimeReached(cf.instant, now)
                else StartCause.Poll
            }
            else -> StartCause.Poll
        }
    }
}

/** Ferme la connexion au serveur Wayland proprement. */
private fun disconnectDisplay(displaySeg: MemorySegment) {
    try {
        wlDisplayDisconnect?.invokeExact(displaySeg)
    } catch (_: Throwable) {}
}
