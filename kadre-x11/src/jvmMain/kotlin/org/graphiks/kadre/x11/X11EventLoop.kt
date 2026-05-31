/**
 * Implémentation X11 de [ActiveEventLoop] et point d'entrée [runApp].
 *
 * [X11EventLoop] implémente [ActiveEventLoop] et est passé à chaque
 * callback de [ApplicationHandler]. La fonction [runApp] orchestre
 * l'initialisation X11 (XOpenDisplay) et la boucle d'événements
 * avec commutation dynamique selon [ControlFlow] :
 *
 * - [ControlFlow.Poll]      → XFlush + while(XPending > 0) { XNextEvent } — non-bloquant
 * - [ControlFlow.Wait]      → XNextEvent bloquant — CPU < 1 % en idle
 * - [ControlFlow.WaitUntil] → poll avec Thread.sleep(1) jusqu'au deadline
 *
 * Pattern Lazy FFM (tryCreate) : tous les MethodHandle sont null sur macOS/Windows,
 * ce qui permet au build de passer sur toutes les plateformes.
 *
 * Redmine #60 : X11EventLoop — boucle d'événements X11 avec commutation ControlFlow.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

// ── Constantes XEvent ─────────────────────────────────────────────────────────

/** Taille de XEvent en octets sur les systèmes 64-bit (96 octets). */
private const val XEVENT_SIZE: Long = 96L

/** Alignement de XEvent (8 octets pour les pointeurs 64-bit). */
private const val XEVENT_ALIGN: Long = 8L

// Offsets dans XEvent pour les champs communs (XAnyEvent)
private const val XEVENT_TYPE_OFFSET: Long = 0L     // int type
private const val XEVENT_WINDOW_OFFSET: Long = 16L  // Window window (unsigned long)

// Offsets XKeyEvent (type=KeyPress ou KeyRelease)
private const val XKEY_STATE_OFFSET: Long = 28L     // unsigned int state (modificateurs)
private const val XKEY_KEYCODE_OFFSET: Long = 32L   // unsigned int keycode

// Offsets XButtonEvent (type=ButtonPress ou ButtonRelease)
private const val XBUTTON_X_OFFSET: Long = 20L      // int x
private const val XBUTTON_Y_OFFSET: Long = 24L      // int y
private const val XBUTTON_BUTTON_OFFSET: Long = 32L // unsigned int button

// Offsets XMotionEvent (type=MotionNotify)
private const val XMOTION_X_OFFSET: Long = 20L      // int x
private const val XMOTION_Y_OFFSET: Long = 24L      // int y

// Offsets XConfigureEvent (type=ConfigureNotify)
private const val XCONFIGURE_WIDTH_OFFSET: Long = 28L   // int width
private const val XCONFIGURE_HEIGHT_OFFSET: Long = 32L  // int height

// Offsets XClientMessageEvent (type=ClientMessage)
private const val XCLIENT_MESSAGE_TYPE_OFFSET: Long = 20L  // Atom message_type (unsigned long)
private const val XCLIENT_DATA_L0_OFFSET: Long = 32L       // long data.l[0]

// Modificateurs X11
private const val X11_SHIFT_MASK: Int = 0x0001
private const val X11_CONTROL_MASK: Int = 0x0004
private const val X11_MOD1_MASK: Int = 0x0008  // Alt

// Boutons X11
private const val X11_BUTTON1: Int = 1
private const val X11_BUTTON2: Int = 2
private const val X11_BUTTON3: Int = 3
private const val X11_BUTTON4: Int = 4  // molette haut
private const val X11_BUTTON5: Int = 5  // molette bas

// Keysym → Key mapping (keysyms courants)
// Référence : /usr/include/X11/keysymdef.h
private const val XK_BackSpace: Int = 0xFF08
private const val XK_Tab: Int = 0xFF09
private const val XK_Return: Int = 0xFF0D
private const val XK_Escape: Int = 0xFF1B
private const val XK_space: Int = 0x0020
private const val XK_F1: Int = 0xFFBE
private const val XK_F12: Int = 0xFFC9
private const val XK_Left: Int = 0xFF51
private const val XK_Up: Int = 0xFF52
private const val XK_Right: Int = 0xFF53
private const val XK_Down: Int = 0xFF54
private const val XK_Shift_L: Int = 0xFFE1
private const val XK_Shift_R: Int = 0xFFE2
private const val XK_Control_L: Int = 0xFFE3
private const val XK_Control_R: Int = 0xFFE4
private const val XK_Alt_L: Int = 0xFFE9
private const val XK_Alt_R: Int = 0xFFEA
private const val XK_Meta_L: Int = 0xFFE7
private const val XK_Meta_R: Int = 0xFFE8
private const val XK_Super_L: Int = 0xFFEB
private const val XK_Super_R: Int = 0xFFEC

// ── Verrou d'instance unique ──────────────────────────────────────────────────

/**
 * Verrou global garantissant qu'une seule boucle d'événements X11 est active
 * à la fois dans le processus.
 */
internal val x11Running = AtomicBoolean(false)

// ── X11EventLoop ──────────────────────────────────────────────────────────────

/**
 * Implémentation interne de [ActiveEventLoop] pour la plateforme X11 (Linux).
 *
 * Une instance est créée par appel à [runApp] et passée comme récepteur
 * à tous les callbacks [ApplicationHandler].
 *
 * ### Cycle de vie
 * ```
 * runApp(handler)
 *   └─ handler.resumed(this)
 *   └─ handler.canCreateSurfaces(this)
 *   └─ boucle d'événements
 *        ├─ handler.newEvents(this, cause)
 *        ├─ dispatch events selon ControlFlow
 *        └─ handler.aboutToWait(this)
 *   └─ handler.suspended(this)
 * ```
 *
 * ### Thread-safety
 * - [_controlFlow] est @Volatile : lisible depuis tout thread.
 * - [_isExiting] est @Volatile : lisible depuis tout thread.
 * - [windows] est un ConcurrentHashMap.
 * - La boucle d'événements elle-même s'exécute dans le thread appelant.
 */
class X11EventLoop internal constructor(
    internal val displayPtr: Long,
    internal val screen: Int,
) : ActiveEventLoop {

    /** Fenêtres vivantes : windowId (XID) → X11Window. */
    internal val windows = ConcurrentHashMap<Long, X11Window>()

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
     * Crée une nouvelle fenêtre X11 native et l'enregistre dans la table de fenêtres.
     *
     * @param attributes Attributs de configuration de la fenêtre.
     * @return La fenêtre créée.
     * @throws IllegalStateException si les bindings libX11 ne sont pas disponibles.
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        val window = X11Window.create(displayPtr, screen, attributes)
            ?: error(
                "X11Window.create() a retourné null — les bindings libX11.so.6 " +
                "ne sont pas disponibles sur cette plateforme."
            )
        windows[window.id.value] = window
        return window
    }

    /**
     * Demande l'arrêt de la boucle d'événements X11.
     */
    override fun exit() {
        _isExiting = true
    }

    /**
     * Crée un proxy thread-safe vers cette boucle d'événements.
     */
    override fun createProxy(): EventLoopProxy =
        X11EventLoopProxy(this, displayPtr)
}

// ── Dispatch X11 events ───────────────────────────────────────────────────────

/**
 * Traduit un keysym X11 en [Key] kadre.
 *
 * Keysym X11 = code symbolique de touche (défini dans keysymdef.h).
 * Pour les lettres a-z et chiffres 0-9, les keysyms sont identiques aux codes ASCII.
 *
 * @param keysym Keysym X11 (INT dans XKeyEvent.keycode, traduit via XLookupKeysym).
 * @return Touche kadre correspondante, ou [Key.Unknown] si non reconnue.
 */
private fun keysymToKey(keysym: Int): Key = when (keysym) {
    // Lettres a-z (keysyms = codes ASCII minuscules 0x61–0x7A)
    in 0x61..0x7A -> enumValues<Key>()[keysym - 0x61]  // A-Z ont les même indices 0-25

    // Lettres A-Z (keysyms = codes ASCII majuscules 0x41–0x5A)
    in 0x41..0x5A -> enumValues<Key>()[keysym - 0x41]

    // Chiffres 0-9 (keysyms = codes ASCII 0x30–0x39)
    0x30 -> Key.Digit0
    0x31 -> Key.Digit1
    0x32 -> Key.Digit2
    0x33 -> Key.Digit3
    0x34 -> Key.Digit4
    0x35 -> Key.Digit5
    0x36 -> Key.Digit6
    0x37 -> Key.Digit7
    0x38 -> Key.Digit8
    0x39 -> Key.Digit9

    // Touches de fonction F1-F12
    in XK_F1..XK_F12 -> enumValues<Key>()[Key.F1.ordinal + (keysym - XK_F1)]

    // Touches de navigation
    XK_Left  -> Key.ArrowLeft
    XK_Right -> Key.ArrowRight
    XK_Up    -> Key.ArrowUp
    XK_Down  -> Key.ArrowDown

    // Touches spéciales
    XK_space     -> Key.Space
    XK_Return    -> Key.Enter
    XK_Escape    -> Key.Escape
    XK_BackSpace -> Key.Backspace
    XK_Tab       -> Key.Tab

    // Modificateurs
    XK_Shift_L   -> Key.ShiftLeft
    XK_Shift_R   -> Key.ShiftRight
    XK_Control_L -> Key.ControlLeft
    XK_Control_R -> Key.ControlRight
    XK_Alt_L     -> Key.AltLeft
    XK_Alt_R     -> Key.AltRight
    XK_Meta_L,
    XK_Super_L   -> Key.MetaLeft
    XK_Meta_R,
    XK_Super_R   -> Key.MetaRight

    else -> Key.Unknown
}

/**
 * Traduit un champ state X11 (modificateurs) en [Modifiers] kadre.
 *
 * @param state Champ state de XKeyEvent ou XButtonEvent.
 * @return [Modifiers] correspondant.
 */
private fun x11StateToModifiers(state: Int): Modifiers {
    var bits = 0
    if (state and X11_SHIFT_MASK != 0) bits = bits or 0x1
    if (state and X11_CONTROL_MASK != 0) bits = bits or 0x2
    if (state and X11_MOD1_MASK != 0) bits = bits or 0x4
    return Modifiers(bits)
}

/**
 * Lecture d'un keycode X11 depuis l'event buffer.
 *
 * Note : une implémentation correcte utiliserait XLookupKeysym ou XkbKeycodeToKeysym
 * pour convertir un keycode en keysym. Pour simplifier (pas de binding XLookupKeysym),
 * on mappe directement keycode → keysym de façon heuristique basée sur la disposition
 * clavier standard PC (X11 ajoute 8 au keycode hardware).
 *
 * Par exemple : keycode 38 → 'a' (keysym 0x61) sur un clavier QWERTY.
 * Cette heuristique fonctionne pour les claviers standard PC.
 */
private fun keycodeToKeysym(keycode: Int): Int {
    // Mapping keycode X11 (hardware + 8) → keysym approximatif pour QWERTY
    // Les keycodes X11 sont layout-dependent — ceci est une approximation
    return when (keycode) {
        // Lettres (disposition QWERTY standard)
        38 -> 0x61  // a
        56 -> 0x62  // b
        54 -> 0x63  // c
        40 -> 0x64  // d
        26 -> 0x65  // e
        41 -> 0x66  // f
        42 -> 0x67  // g
        43 -> 0x68  // h
        31 -> 0x69  // i
        44 -> 0x6A  // j
        45 -> 0x6B  // k
        46 -> 0x6C  // l
        58 -> 0x6D  // m
        57 -> 0x6E  // n
        32 -> 0x6F  // o
        33 -> 0x70  // p
        24 -> 0x71  // q
        27 -> 0x72  // r
        39 -> 0x73  // s
        28 -> 0x74  // t
        30 -> 0x75  // u
        55 -> 0x76  // v
        25 -> 0x77  // w
        53 -> 0x78  // x
        29 -> 0x79  // y
        52 -> 0x7A  // z
        // Chiffres
        19 -> 0x30  // 0
        10 -> 0x31  // 1
        11 -> 0x32  // 2
        12 -> 0x33  // 3
        13 -> 0x34  // 4
        14 -> 0x35  // 5
        15 -> 0x36  // 6
        16 -> 0x37  // 7
        17 -> 0x38  // 8
        18 -> 0x39  // 9
        // Touches spéciales
        65  -> XK_space
        36  -> XK_Return
        9   -> XK_Escape
        22  -> XK_BackSpace
        23  -> XK_Tab
        // Fonctions
        67  -> XK_F1
        68  -> XK_F1 + 1   // F2
        69  -> XK_F1 + 2   // F3
        70  -> XK_F1 + 3   // F4
        71  -> XK_F1 + 4   // F5
        72  -> XK_F1 + 5   // F6
        73  -> XK_F1 + 6   // F7
        74  -> XK_F1 + 7   // F8
        75  -> XK_F1 + 8   // F9
        76  -> XK_F1 + 9   // F10
        95  -> XK_F1 + 10  // F11
        96  -> XK_F1 + 11  // F12
        // Navigation
        113 -> XK_Left
        114 -> XK_Right
        111 -> XK_Up
        116 -> XK_Down
        // Modificateurs
        50  -> XK_Shift_L
        62  -> XK_Shift_R
        37  -> XK_Control_L
        105 -> XK_Control_R
        64  -> XK_Alt_L
        108 -> XK_Alt_R
        133 -> XK_Super_L
        134 -> XK_Super_R
        else -> 0
    }
}

/**
 * Dispatche un seul XEvent vers les callbacks [ApplicationHandler].
 *
 * @param eventBuf Buffer contenant l'XEvent lu par XNextEvent.
 * @param loop     Boucle d'événements active.
 * @param handler  Gestionnaire d'application à notifier.
 * @param wmDeleteWindow Atome WM_DELETE_WINDOW pour détecter la fermeture de fenêtre.
 */
private fun dispatchEvent(
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    wmDeleteWindow: Long,
) {
    val eventType = eventBuf.get(ValueLayout.JAVA_INT, XEVENT_TYPE_OFFSET)
    val windowXid = eventBuf.get(ValueLayout.JAVA_LONG, XEVENT_WINDOW_OFFSET)
    val windowId = WindowId(windowXid)

    when (eventType) {

        // ── Exposition (redraw) ───────────────────────────────────────────────
        Expose -> {
            handler.windowEvent(loop, windowId, WindowEvent.RedrawRequested)
        }

        // ── Redimensionnement ─────────────────────────────────────────────────
        ConfigureNotify -> {
            val width  = eventBuf.get(ValueLayout.JAVA_INT, XCONFIGURE_WIDTH_OFFSET)
            val height = eventBuf.get(ValueLayout.JAVA_INT, XCONFIGURE_HEIGHT_OFFSET)
            loop.windows[windowXid]?.onConfigureNotify(width, height)
            if (width > 0 && height > 0) {
                handler.windowEvent(loop, windowId, WindowEvent.Resized(PhysicalSize(width, height)))
            }
        }

        // ── Clavier ───────────────────────────────────────────────────────────
        KeyPress -> {
            val keycode = eventBuf.get(ValueLayout.JAVA_INT, XKEY_KEYCODE_OFFSET)
            val state   = eventBuf.get(ValueLayout.JAVA_INT, XKEY_STATE_OFFSET)
            val keysym  = keycodeToKeysym(keycode)
            val key     = keysymToKey(keysym)
            val mods    = x11StateToModifiers(state)
            handler.windowEvent(loop, windowId, WindowEvent.KeyboardInput(key, KeyState.Pressed, mods))
        }

        KeyRelease -> {
            val keycode = eventBuf.get(ValueLayout.JAVA_INT, XKEY_KEYCODE_OFFSET)
            val state   = eventBuf.get(ValueLayout.JAVA_INT, XKEY_STATE_OFFSET)
            val keysym  = keycodeToKeysym(keycode)
            val key     = keysymToKey(keysym)
            val mods    = x11StateToModifiers(state)
            handler.windowEvent(loop, windowId, WindowEvent.KeyboardInput(key, KeyState.Released, mods))
        }

        // ── Boutons souris ────────────────────────────────────────────────────
        ButtonPress -> {
            val button = eventBuf.get(ValueLayout.JAVA_INT, XBUTTON_BUTTON_OFFSET)
            when (button) {
                X11_BUTTON1 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseInput(MouseButton.Left, KeyState.Pressed))
                X11_BUTTON2 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseInput(MouseButton.Middle, KeyState.Pressed))
                X11_BUTTON3 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseInput(MouseButton.Right, KeyState.Pressed))
                X11_BUTTON4 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseWheel(deltaX = 0.0, deltaY = 1.0))
                X11_BUTTON5 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseWheel(deltaX = 0.0, deltaY = -1.0))
                else -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseInput(MouseButton.Other(button), KeyState.Pressed))
            }
        }

        ButtonRelease -> {
            val button = eventBuf.get(ValueLayout.JAVA_INT, XBUTTON_BUTTON_OFFSET)
            // Ne pas émettre MouseInput Released pour les événements de molette (4 et 5)
            when (button) {
                X11_BUTTON1 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseInput(MouseButton.Left, KeyState.Released))
                X11_BUTTON2 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseInput(MouseButton.Middle, KeyState.Released))
                X11_BUTTON3 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseInput(MouseButton.Right, KeyState.Released))
                X11_BUTTON4, X11_BUTTON5 -> { /* molette — pas de Released */ }
                else -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseInput(MouseButton.Other(button), KeyState.Released))
            }
        }

        // ── Mouvement de souris ───────────────────────────────────────────────
        MotionNotify -> {
            val x = eventBuf.get(ValueLayout.JAVA_INT, XMOTION_X_OFFSET).toDouble()
            val y = eventBuf.get(ValueLayout.JAVA_INT, XMOTION_Y_OFFSET).toDouble()
            handler.windowEvent(loop, windowId, WindowEvent.PointerMoved(PhysicalPosition(x, y)))
        }

        // ── Entrée/sortie du curseur ──────────────────────────────────────────
        EnterNotify -> {
            handler.windowEvent(loop, windowId, WindowEvent.PointerEntered)
        }

        LeaveNotify -> {
            handler.windowEvent(loop, windowId, WindowEvent.PointerLeft)
        }

        // ── Destruction de fenêtre ────────────────────────────────────────────
        DestroyNotify -> {
            handler.windowEvent(loop, windowId, WindowEvent.Destroyed)
            loop.windows.remove(windowXid)
        }

        // ── ClientMessage (fermeture WM + wakeUp) ─────────────────────────────
        ClientMessage -> {
            val messageType = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_MESSAGE_TYPE_OFFSET)
            if (messageType == wmDeleteWindow) {
                handler.windowEvent(loop, windowId, WindowEvent.CloseRequested)
            }
            // Les ClientMessage de wakeUp (KADRE_WAKEUP_TYPE) sont simplement ignorés —
            // leur seul rôle est de débloquer XNextEvent.
        }
    }
}

// ── Modes de dispatch ─────────────────────────────────────────────────────────

/**
 * Mode Poll : XFlush + while(XPending > 0) { XNextEvent; dispatch } — non-bloquant.
 *
 * Vide la file d'événements en une passe et retourne immédiatement.
 */
private fun dispatchPoll(
    displaySeg: MemorySegment,
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    wmDeleteWindow: Long,
): StartCause {
    xFlush?.invokeExact(displaySeg) as? Int

    val pendingHandle = xPending
    val nextHandle    = xNextEvent

    if (pendingHandle != null && nextHandle != null) {
        while (!loop.isExiting) {
            val pending = pendingHandle.invokeExact(displaySeg) as Int
            if (pending <= 0) break
            nextHandle.invokeExact(displaySeg, eventBuf) as Int
            dispatchEvent(eventBuf, loop, handler, wmDeleteWindow)
        }
    }

    return StartCause.Poll
}

/**
 * Mode Wait : XNextEvent bloquant — CPU < 1 % en idle.
 *
 * Bloque le thread jusqu'à la réception du prochain événement.
 */
private fun dispatchWait(
    displaySeg: MemorySegment,
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    wmDeleteWindow: Long,
): StartCause {
    val nextHandle = xNextEvent ?: return StartCause.WaitCancelled()

    // XNextEvent bloque jusqu'à l'arrivée d'un événement
    nextHandle.invokeExact(displaySeg, eventBuf) as Int
    dispatchEvent(eventBuf, loop, handler, wmDeleteWindow)

    // Drainer les événements supplémentaires déjà disponibles
    val pendingHandle = xPending
    if (pendingHandle != null) {
        while (!loop.isExiting) {
            val pending = pendingHandle.invokeExact(displaySeg) as Int
            if (pending <= 0) break
            nextHandle.invokeExact(displaySeg, eventBuf) as Int
            dispatchEvent(eventBuf, loop, handler, wmDeleteWindow)
        }
    }

    return StartCause.WaitCancelled()
}

/**
 * Mode WaitUntil : poll avec Thread.sleep(1ms) jusqu'au deadline.
 *
 * Dispatche les événements disponibles et dort 1ms entre chaque vérification,
 * jusqu'à l'expiration du délai ou la réception d'un événement.
 */
private fun dispatchWaitUntil(
    displaySeg: MemorySegment,
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    cf: ControlFlow.WaitUntil,
    wmDeleteWindow: Long,
): StartCause {
    val deadline = cf.instant
    val pendingHandle = xPending
    val nextHandle    = xNextEvent

    while (!loop.isExiting) {
        val now = System.currentTimeMillis()
        if (now >= deadline) {
            return StartCause.ResumeTimeReached(
                requestedResume = deadline,
                start = now,
            )
        }

        // Tenter de dispatcher les événements disponibles
        if (pendingHandle != null && nextHandle != null) {
            val pending = pendingHandle.invokeExact(displaySeg) as Int
            if (pending > 0) {
                nextHandle.invokeExact(displaySeg, eventBuf) as Int
                dispatchEvent(eventBuf, loop, handler, wmDeleteWindow)
                return StartCause.WaitCancelled(deadline)
            }
        }

        Thread.sleep(1L)
    }

    return StartCause.WaitCancelled(deadline)
}

// ── Point d'entrée ────────────────────────────────────────────────────────────

/**
 * Point d'entrée de la boucle d'événements kadre sur Linux (X11).
 *
 * Ouvre la connexion au serveur X (XOpenDisplay), crée une [X11EventLoop],
 * appelle [ApplicationHandler.resumed], puis lance la boucle d'événements
 * bloquante. Ne retourne qu'à la fermeture de l'application.
 *
 * Doit être appelé depuis le thread principal (celui qui a ouvert le display).
 *
 * @param handler Gestionnaire du cycle de vie et des événements.
 * @throws IllegalStateException si une boucle X11 est déjà active dans ce processus.
 */
fun runApp(handler: ApplicationHandler) {
    check(x11Running.compareAndSet(false, true)) {
        "X11EventLoop.runApp() ne peut être appelé qu'une seule fois par processus. " +
        "Une boucle d'événements X11 est déjà active."
    }

    try {
        val openHandle = xOpenDisplay
        if (openHandle == null) {
            // libX11 indisponible (macOS/Windows) — no-op gracieux
            return
        }

        // XOpenDisplay(NULL) → utilise la variable d'environnement DISPLAY
        val displaySeg = openHandle.invokeExact(MemorySegment.NULL) as MemorySegment
        if (displaySeg == MemorySegment.NULL || displaySeg.address() == 0L) {
            return  // Aucun serveur X disponible
        }
        val displayPtr = displaySeg.address()
        val screen = 0  // écran par défaut

        // Obtenir l'atome WM_DELETE_WINDOW pour détecter la fermeture propre
        val wmDeleteWindow: Long = Arena.ofConfined().use { arena ->
            val atomName = "WM_DELETE_WINDOW".toByteArray(Charsets.US_ASCII)
            val namePtr = arena.allocate(atomName.size.toLong() + 1)
            for (i in atomName.indices) namePtr.set(ValueLayout.JAVA_BYTE, i.toLong(), atomName[i])
            namePtr.set(ValueLayout.JAVA_BYTE, atomName.size.toLong(), 0)
            xInternAtom?.invokeExact(displaySeg, namePtr, 0) as? Long ?: 0L
        }

        val loop = X11EventLoop(displayPtr, screen)

        // Allouer le buffer XEvent (96 bytes, aligné 8) pour la durée de la boucle
        val arena = Arena.ofConfined()
        try {
            val eventBuf = arena.allocate(XEVENT_SIZE, XEVENT_ALIGN)

            // Notifier le gestionnaire que l'application reprend
            handler.resumed(loop)

            // Notifier que les surfaces peuvent être créées
            handler.canCreateSurfaces(loop)

            var startCause: StartCause = StartCause.Init

            while (!loop.isExiting) {
                // Notifier le gestionnaire du début d'itération
                handler.newEvents(loop, startCause)
                if (loop.isExiting) break

                // Dispatcher les événements selon le ControlFlow courant
                startCause = when (val cf = loop.controlFlow) {
                    is ControlFlow.Poll      -> dispatchPoll(displaySeg, eventBuf, loop, handler, wmDeleteWindow)
                    is ControlFlow.Wait      -> dispatchWait(displaySeg, eventBuf, loop, handler, wmDeleteWindow)
                    is ControlFlow.WaitUntil -> dispatchWaitUntil(displaySeg, eventBuf, loop, handler, cf, wmDeleteWindow)
                }

                // Notifier le gestionnaire que la boucle est sur le point d'attendre
                handler.aboutToWait(loop)
            }

            handler.suspended(loop)
        } finally {
            arena.close()
            xCloseDisplay?.invokeExact(displaySeg) as? Int
        }
    } finally {
        x11Running.set(false)
    }
}
