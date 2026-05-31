/**
 * Procédure de fenêtre Win32 centralisée (WndProc) pour kadre.
 *
 * Ce fichier définit [KadreWndProc], objet singleton qui dispatche les messages Win32
 * vers les [WindowEvent] kadre-core, et les transmet à l'instance [Win32Window] associée
 * via un [WindowEventHandler] installé par l'appelant.
 *
 * ## Architecture
 *
 * ```
 *  Win32 OS  ─[WM_*]→  KadreWndProc.dispatch()
 *                           │
 *                           ├─ traduit le message en WindowEvent
 *                           │
 *                           └─ résout Win32Window via windowResolver(hwnd)
 *                                   │
 *                                   └─ appelle WindowEventHandler.onEvent(window, event)
 * ```
 *
 * ## Utilisation
 *
 * ```kotlin
 * KadreWndProc.install { hwnd -> win32WindowMap[hwnd] }
 * // …
 * // Le stub FFM appelle KadreWndProc.dispatch(hwnd, msg, wParam, lParam)
 * ```
 *
 * ## Contraintes de plateforme
 *
 * Sur macOS/Linux, aucun appel FFM réel n'est effectué. La méthode [dispatch]
 * peut être appelée en test avec des valeurs synthétiques.
 *
 * @see WindowEvent
 * @see Win32KeyMapper
 * @see Win32Constants
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

// ── Interface handler ─────────────────────────────────────────────────────────

/**
 * Récepteur d'événements de fenêtre Win32.
 *
 * Implémenté par la couche applicative (EventLoop Win32) pour recevoir les [WindowEvent]
 * traduits par [KadreWndProc] et les dispatcher vers les [org.graphiks.kadre.core.ApplicationHandler].
 */
fun interface WindowEventHandler {
    /**
     * Appelé pour chaque événement de fenêtre traduit depuis un message Win32.
     *
     * @param hwnd  Handle natif de la fenêtre source (adresse entière 64 bits).
     * @param event Événement kadre traduit.
     */
    fun onEvent(hwnd: Long, event: WindowEvent)
}

// ── KadreWndProc ─────────────────────────────────────────────────────────────

/**
 * Dispatcher central des messages Win32 → [WindowEvent] kadre.
 *
 * ### Installation
 * Avant que la boucle de messages ne démarre, appelez [install] pour enregistrer
 * le handler qui recevra les événements :
 *
 * ```kotlin
 * KadreWndProc.install { hwnd, event ->
 *     win32WindowMap[hwnd]?.let { appHandler.onWindowEvent(it.id, event) }
 * }
 * ```
 *
 * ### Appel par Win32Window
 * [Win32Window] doit router son `wndProc` statique vers [dispatch] :
 *
 * ```kotlin
 * @JvmStatic
 * fun wndProc(hwnd: MemorySegment, msg: Int, wParam: Long, lParam: Long): Long =
 *     KadreWndProc.dispatch(hwnd.address(), msg, wParam, lParam)
 * ```
 */
object KadreWndProc {

    /**
     * Handler installé par l'appelant via [install].
     *
     * Null jusqu'à ce que [install] soit appelé — les événements sont silencieusement
     * ignorés (mais DefWindowProcW est toujours appelé pour les messages non gérés).
     */
    @Volatile
    private var handler: WindowEventHandler? = null

    // ── Installation ──────────────────────────────────────────────────────────

    /**
     * Enregistre le [WindowEventHandler] qui recevra les [WindowEvent] traduits.
     *
     * Doit être appelé avant le démarrage de la boucle de messages Win32.
     * Thread-safe (écriture volatile).
     *
     * @param handler Implémentation du handler (lambda ou objet).
     */
    fun install(handler: WindowEventHandler) {
        this.handler = handler
    }

    /**
     * Désenregistre le handler actuel.
     *
     * Utile en fin de boucle de messages pour éviter les fuites d'objets.
     */
    fun uninstall() {
        handler = null
    }

    // ── Dispatch ──────────────────────────────────────────────────────────────

    /**
     * Dispatche un message Win32 vers un [WindowEvent] kadre et le transmet au handler.
     *
     * Signature compatible avec la WndProc Win32 :
     * `LRESULT CALLBACK KadreWndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)`
     *
     * Les messages non reconnus sont délégués à [defWindowProcW].
     *
     * @param hwnd   Adresse entière du HWND (MemorySegment.address()).
     * @param msg    Identifiant du message (WM_*).
     * @param wParam Premier paramètre du message.
     * @param lParam Second paramètre du message.
     * @return LRESULT à retourner à Windows.
     */
    fun dispatch(hwnd: Long, msg: Int, wParam: Long, lParam: Long): Long {
        return when (msg.toUInt()) {

            // ── Redraw ────────────────────────────────────────────────────────
            WM_PAINT.toUInt() -> {
                emit(hwnd, WindowEvent.RedrawRequested)
                // Retourner 0 sans appeler BeginPaint/EndPaint — la fenêtre sera
                // redessinée par le moteur de rendu (wgpu4k) lors du prochain frame.
                0L
            }

            // ── Redimensionnement ─────────────────────────────────────────────
            WM_SIZE.toUInt() -> {
                // lParam : LOWORD = nouvelle largeur, HIWORD = nouvelle hauteur (pixels clients)
                val width  = (lParam and 0xFFFF).toInt()
                val height = ((lParam ushr 16) and 0xFFFF).toInt()
                emit(hwnd, WindowEvent.Resized(PhysicalSize(width, height)))
                0L
            }

            // ── Clavier ───────────────────────────────────────────────────────
            WM_KEYDOWN.toUInt(),
            WM_SYSKEYDOWN.toUInt() -> {
                val key      = Win32KeyMapper.fromVkCode(wParam.toInt())
                val isRepeat = (lParam and KF_REPEAT) != 0L
                val mods     = currentModifiers()
                emit(hwnd, WindowEvent.KeyboardInput(key, KeyState.Pressed, mods, isRepeat))
                0L
            }

            WM_KEYUP.toUInt(),
            WM_SYSKEYUP.toUInt() -> {
                val key  = Win32KeyMapper.fromVkCode(wParam.toInt())
                val mods = currentModifiers()
                emit(hwnd, WindowEvent.KeyboardInput(key, KeyState.Released, mods, isRepeat = false))
                0L
            }

            // ── Mouvement du curseur ──────────────────────────────────────────
            WM_MOUSEMOVE.toUInt() -> {
                val x = (lParam and 0xFFFF).toDouble()
                val y = ((lParam ushr 16) and 0xFFFF).toDouble()
                emit(hwnd, WindowEvent.PointerMoved(PhysicalPosition(x, y)))
                // Armer TrackMouseEvent pour détecter WM_MOUSELEAVE
                armMouseLeaveTracking(hwnd)
                0L
            }

            // ── Sortie du curseur ─────────────────────────────────────────────
            WM_MOUSELEAVE.toUInt() -> {
                // Le tracking WM_MOUSELEAVE est automatiquement désarmé après réception.
                // Il sera ré-armé au prochain WM_MOUSEMOVE si le curseur revient.
                emit(hwnd, WindowEvent.PointerLeft)
                0L
            }

            // ── Boutons de souris ─────────────────────────────────────────────
            WM_LBUTTONDOWN.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Left, KeyState.Pressed))
                0L
            }
            WM_LBUTTONUP.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Left, KeyState.Released))
                0L
            }
            WM_RBUTTONDOWN.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Right, KeyState.Pressed))
                0L
            }
            WM_RBUTTONUP.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Right, KeyState.Released))
                0L
            }
            WM_MBUTTONDOWN.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Middle, KeyState.Pressed))
                0L
            }
            WM_MBUTTONUP.toUInt() -> {
                emit(hwnd, WindowEvent.MouseInput(MouseButton.Middle, KeyState.Released))
                0L
            }

            // ── Boutons supplémentaires (X1 / X2) ────────────────────────────
            WM_XBUTTONDOWN.toUInt() -> {
                // HIWORD(wParam) = numéro du bouton X (XBUTTON1 = 1, XBUTTON2 = 2)
                val xButton = ((wParam ushr 16) and 0xFFFF).toInt()
                val button = MouseButton.Other(xButton)
                emit(hwnd, WindowEvent.MouseInput(button, KeyState.Pressed))
                // WM_XBUTTONDOWN doit retourner TRUE (non-zéro) selon la doc Win32
                1L
            }
            WM_XBUTTONUP.toUInt() -> {
                val xButton = ((wParam ushr 16) and 0xFFFF).toInt()
                val button = MouseButton.Other(xButton)
                emit(hwnd, WindowEvent.MouseInput(button, KeyState.Released))
                // WM_XBUTTONUP doit retourner TRUE (non-zéro) selon la doc Win32
                1L
            }

            // ── Molette ───────────────────────────────────────────────────────
            WM_MOUSEWHEEL.toUInt() -> {
                // wParam : HIWORD = delta signé (multiple de WHEEL_DELTA = 120)
                val rawDelta = ((wParam ushr 16) and 0xFFFF).toShort().toInt()
                val deltaY   = rawDelta.toDouble() / WHEEL_DELTA
                emit(hwnd, WindowEvent.MouseWheel(deltaX = 0.0, deltaY = deltaY))
                0L
            }

            // ── Fermeture ─────────────────────────────────────────────────────
            WM_CLOSE.toUInt() -> {
                emit(hwnd, WindowEvent.CloseRequested)
                // Ne pas appeler DefWindowProcW ici — l'application décide si elle détruit la fenêtre.
                0L
            }

            // ── Destruction ───────────────────────────────────────────────────
            WM_DESTROY.toUInt() -> {
                emit(hwnd, WindowEvent.Destroyed)
                // PostQuitMessage(0) — signal de fin de la boucle de messages Win32.
                postQuitMessage(0)
                0L
            }

            // ── Changement DPI ────────────────────────────────────────────────
            WM_DPICHANGED.toUInt() -> {
                // wParam : LOWORD = nouveau DPI X, HIWORD = nouveau DPI Y
                val dpiX   = (wParam and DPI_WPARAM_MASK).toInt()
                val factor = dpiX.toDouble() / 96.0  // 96 DPI = facteur 1.0 (100 %)
                emit(hwnd, WindowEvent.ScaleFactorChanged(factor))
                0L
            }

            // ── Défaut ────────────────────────────────────────────────────────
            else -> defWindowProcW(hwnd, msg, wParam, lParam)
        }
    }

    // ── Helpers privés ────────────────────────────────────────────────────────

    /**
     * Transmet un [WindowEvent] au [handler] installé, si présent.
     */
    private fun emit(hwnd: Long, event: WindowEvent) {
        handler?.onEvent(hwnd, event)
    }

    /**
     * Appelle DefWindowProcW via le binding FFM (lazy, null sur macOS/Linux).
     *
     * @param hwnd  Adresse entière du HWND.
     * @param msg   Identifiant du message.
     * @param wParam Premier paramètre.
     * @param lParam Second paramètre.
     * @return Valeur de retour de DefWindowProcW, ou 0 si le binding est indisponible.
     */
    private fun defWindowProcW(hwnd: Long, msg: Int, wParam: Long, lParam: Long): Long {
        val handle = defWindowProcW ?: return 0L
        val hwndSeg = MemorySegment.ofAddress(hwnd)
        return handle.invokeExact(hwndSeg, msg, wParam, lParam) as Long
    }

    /**
     * Appelle PostQuitMessage(nExitCode) via le binding FFM (lazy, null sur macOS/Linux).
     *
     * Initialise la sortie de la boucle de messages GetMessage quand WM_DESTROY est reçu.
     *
     * @param nExitCode Code de sortie (0 = succès normal).
     */
    private fun postQuitMessage(nExitCode: Int) {
        postQuitMessage?.invoke(nExitCode)
    }

    /**
     * Lit l'état actuel des touches modificatrices via GetKeyState (lazy, null sur macOS/Linux).
     *
     * GetKeyState lit l'état des touches au moment du traitement du message — cohérent
     * avec le thread de messages Win32.
     *
     * @return [Modifiers] représentant les modificateurs actifs.
     */
    private fun currentModifiers(): Modifiers {
        if (getKeyState == null) return Modifiers.NONE
        var bits = 0
        // GetKeyState retourne un Short : bit 15 = touche enfoncée, bit 0 = toggle
        if ((getKeyState!!.invokeExact(VK_SHIFT)   as Short).toInt() and 0x8000 != 0) bits = bits or 0x1
        if ((getKeyState!!.invokeExact(VK_CONTROL) as Short).toInt() and 0x8000 != 0) bits = bits or 0x2
        if ((getKeyState!!.invokeExact(VK_MENU)    as Short).toInt() and 0x8000 != 0) bits = bits or 0x4
        if ((getKeyState!!.invokeExact(VK_LWIN)    as Short).toInt() and 0x8000 != 0 ||
            (getKeyState!!.invokeExact(VK_RWIN)    as Short).toInt() and 0x8000 != 0) bits = bits or 0x8
        return Modifiers(bits)
    }

    /**
     * Arme le tracking WM_MOUSELEAVE pour la fenêtre [hwnd] via TrackMouseEvent.
     *
     * Doit être appelé à chaque WM_MOUSEMOVE pour maintenir le tracking actif,
     * car Windows désarme automatiquement TrackMouseEvent après l'envoi de
     * WM_MOUSELEAVE. Les appels redondants (tracking déjà actif) sont ignorés
     * gracieusement par Windows.
     *
     * Sur macOS/Linux (trackMouseEvent null), cette méthode est un no-op.
     *
     * @param hwndAddr Adresse entière du HWND (MemorySegment.address()).
     */
    private fun armMouseLeaveTracking(hwndAddr: Long) {
        val handle = trackMouseEvent ?: return
        try {
            val hwndSeg = MemorySegment.ofAddress(hwndAddr)
            // Allouer TRACKMOUSEEVENT sur le stack (arène confinée auto-libérée)
            // Layout : DWORD cbSize (4) + DWORD dwFlags (4) + HWND hwndTrack (8) + DWORD dwHoverTime (4) + pad(4) = 24 bytes
            val tme = MemorySegment.ofArray(LongArray(3)) // 24 bytes, aligné 8
            tme.set(ValueLayout.JAVA_INT,  0L, TRACKMOUSEEVENT_SIZE)  // cbSize
            tme.set(ValueLayout.JAVA_INT,  4L, TME_LEAVE)             // dwFlags
            tme.set(ValueLayout.ADDRESS,   8L, hwndSeg)               // hwndTrack
            tme.set(ValueLayout.JAVA_INT, 16L, 0)                     // dwHoverTime (HOVER_DEFAULT)
            handle.invokeExact(tme) as Int
        } catch (_: Throwable) {
            // Dégradation gracieuse : WM_MOUSELEAVE ne sera pas reçu
        }
    }
}
