/**
 * Bindings FFM pour les fonctions X11 nécessaires à la gestion de fenêtres.
 *
 * Charge libX11.so.6 via SymbolLookup.libraryLookup avec un pattern tryCreate
 * (try/catch Throwable) pour que le build passe sur macOS/Windows.
 *
 * Fonctions exposées :
 *  - XOpenDisplay      — ouvre la connexion au serveur X
 *  - XCloseDisplay     — ferme la connexion au serveur X
 *  - XCreateSimpleWindow — crée une fenêtre simple
 *  - XSelectInput      — sélectionne les événements à recevoir
 *  - XDestroyWindow    — détruit une fenêtre
 *  - XFlush            — vide la file de commandes vers le serveur X
 *  - XPending          — retourne le nombre d'événements en attente
 *  - XNextEvent        — lit le prochain événement
 *  - XStoreName        — définit le titre de la fenêtre
 *  - XInternAtom       — obtient un atome par nom
 *  - XSetWMProtocols   — définit les protocoles WM (ex. WM_DELETE_WINDOW)
 *  - XMapWindow        — rend une fenêtre visible
 *  - XSendEvent        — envoie un événement synthétique (wakeUp ClientMessage)
 *
 * Référence : https://www.x.org/releases/current/doc/libX11/libX11/libX11.html
 */
package org.graphiks.kadre.x11

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

// ── Lazy loading de la bibliothèque ───────────────────────────────────────────

/**
 * Lookup libX11.so.6 — null sur les plateformes non-Linux (macOS, Windows).
 *
 * Le try/catch sur Throwable est intentionnel : SymbolLookup.libraryLookup
 * peut lever IllegalArgumentException ou UnsatisfiedLinkError sur macOS/Windows,
 * et on veut que le build reste vert dans tous les cas.
 */
internal val libX11: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("libX11.so.6", Arena.global())
    } catch (e: Throwable) {
        null
    }
}

private val linker: Linker = Linker.nativeLinker()

// ── Helpers ───────────────────────────────────────────────────────────────────

/**
 * Recherche un symbole dans un SymbolLookup et crée un MethodHandle downcall.
 * Retourne null si le lookup est null ou si le symbole est introuvable.
 */
private fun SymbolLookup?.downcall(name: String, desc: FunctionDescriptor): MethodHandle? {
    this ?: return null
    return this.find(name).map { linker.downcallHandle(it, desc) }.orElse(null)
}

// ── XOpenDisplay ──────────────────────────────────────────────────────────────

/**
 * Display *XOpenDisplay(char *display_name);
 *
 * Ouvre la connexion au serveur X. Passer NULL pour utiliser la variable
 * d'environnement DISPLAY. Retourne un pointeur Display* (NULL en cas d'échec).
 */
internal val xOpenDisplay: MethodHandle? by lazy {
    libX11.downcall(
        "XOpenDisplay",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // Display* retour
            ValueLayout.ADDRESS,    // char* display_name (ou NULL)
        )
    )
}

// ── XCloseDisplay ─────────────────────────────────────────────────────────────

/**
 * int XCloseDisplay(Display *display);
 *
 * Ferme la connexion au serveur X et libère les ressources associées.
 */
internal val xCloseDisplay: MethodHandle? by lazy {
    libX11.downcall(
        "XCloseDisplay",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int retour
            ValueLayout.ADDRESS,    // Display*
        )
    )
}

// ── XCreateSimpleWindow ───────────────────────────────────────────────────────

/**
 * Window XCreateSimpleWindow(
 *     Display *display,
 *     Window parent,
 *     int x, int y,
 *     unsigned int width, unsigned int height,
 *     unsigned int border_width,
 *     unsigned long border,
 *     unsigned long background
 * );
 *
 * Crée une fenêtre enfant simple. Window est un XID = unsigned long (64 bits).
 */
/**
 * Window XRootWindow(Display *display, int screen_number);
 *
 * Retourne l'XID de la fenêtre racine de l'écran (équivalent macro DefaultRootWindow).
 * Indispensable comme parent de XCreateSimpleWindow : une valeur conventionnelle erronée
 * provoque `BadWindow`.
 */
internal val xRootWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XRootWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_INT,   // int screen_number
        )
    )
}

internal val xCreateSimpleWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XCreateSimpleWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // Window (XID = unsigned long)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window parent (XID)
            ValueLayout.JAVA_INT,   // int x
            ValueLayout.JAVA_INT,   // int y
            ValueLayout.JAVA_INT,   // unsigned int width
            ValueLayout.JAVA_INT,   // unsigned int height
            ValueLayout.JAVA_INT,   // unsigned int border_width
            ValueLayout.JAVA_LONG,  // unsigned long border
            ValueLayout.JAVA_LONG,  // unsigned long background
        )
    )
}

// ── XSelectInput ──────────────────────────────────────────────────────────────

/**
 * int XSelectInput(Display *display, Window w, long event_mask);
 *
 * Sélectionne les types d'événements à recevoir pour la fenêtre donnée.
 */
internal val xSelectInput: MethodHandle? by lazy {
    libX11.downcall(
        "XSelectInput",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int retour
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.JAVA_LONG,  // long event_mask
        )
    )
}

// ── XDestroyWindow ────────────────────────────────────────────────────────────

/**
 * int XDestroyWindow(Display *display, Window w);
 *
 * Détruit la fenêtre et toutes ses sous-fenêtres.
 */
internal val xDestroyWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XDestroyWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int retour
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
        )
    )
}

// ── XFlush ────────────────────────────────────────────────────────────────────

/**
 * int XFlush(Display *display);
 *
 * Vide la file de commandes en attente vers le serveur X.
 */
internal val xFlush: MethodHandle? by lazy {
    libX11.downcall(
        "XFlush",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int retour
            ValueLayout.ADDRESS,    // Display*
        )
    )
}

// ── XPending ──────────────────────────────────────────────────────────────────

/**
 * int XPending(Display *display);
 *
 * Retourne le nombre d'événements en attente dans la file côté client.
 * Retourne 0 si la file est vide (sans bloquer).
 */
internal val xPending: MethodHandle? by lazy {
    libX11.downcall(
        "XPending",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int (nombre d'événements)
            ValueLayout.ADDRESS,    // Display*
        )
    )
}

// ── XNextEvent ────────────────────────────────────────────────────────────────

/**
 * int XNextEvent(Display *display, XEvent *event_return);
 *
 * Lit le prochain événement de la file, en bloquant si nécessaire.
 * L'événement est écrit dans le MemorySegment pointé par event_return.
 */
internal val xNextEvent: MethodHandle? by lazy {
    libX11.downcall(
        "XNextEvent",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int retour
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.ADDRESS,    // XEvent* event_return
        )
    )
}

// ── XStoreName ────────────────────────────────────────────────────────────────

/**
 * int XStoreName(Display *display, Window w, char *window_name);
 *
 * Définit le titre (nom) de la fenêtre dans la barre de titre du gestionnaire
 * de fenêtres. La chaîne doit être encodée en Latin-1 ou UTF-8 selon le WM.
 */
internal val xStoreName: MethodHandle? by lazy {
    libX11.downcall(
        "XStoreName",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int retour
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.ADDRESS,    // char* window_name
        )
    )
}

// ── XInternAtom ───────────────────────────────────────────────────────────────

/**
 * Atom XInternAtom(Display *display, char *atom_name, Bool only_if_exists);
 *
 * Obtient l'identifiant (Atom) d'une propriété par son nom.
 * Utilisé notamment pour WM_DELETE_WINDOW et WM_PROTOCOLS.
 * Atom = unsigned long (64 bits).
 */
internal val xInternAtom: MethodHandle? by lazy {
    libX11.downcall(
        "XInternAtom",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // Atom (unsigned long)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.ADDRESS,    // char* atom_name
            ValueLayout.JAVA_INT,   // Bool only_if_exists
        )
    )
}

// ── XSetWMProtocols ───────────────────────────────────────────────────────────

/**
 * Status XSetWMProtocols(Display *display, Window w, Atom *protocols, int count);
 *
 * Définit la propriété WM_PROTOCOLS de la fenêtre. Utilisé pour demander au
 * gestionnaire de fenêtres d'envoyer un ClientMessage au lieu de détruire
 * directement la fenêtre (WM_DELETE_WINDOW).
 */
internal val xSetWMProtocols: MethodHandle? by lazy {
    libX11.downcall(
        "XSetWMProtocols",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Status
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.ADDRESS,    // Atom* protocols
            ValueLayout.JAVA_INT,   // int count
        )
    )
}

// ── XMapWindow ────────────────────────────────────────────────────────────────

/**
 * int XMapWindow(Display *display, Window w);
 *
 * Rend la fenêtre visible à l'écran. La fenêtre doit d'abord avoir été créée
 * avec XCreateSimpleWindow ou XCreateWindow.
 */
internal val xMapWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XMapWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int retour
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
        )
    )
}

// ── XSendEvent ────────────────────────────────────────────────────────────────

/**
 * Status XSendEvent(
 *     Display *display,
 *     Window w,
 *     Bool propagate,
 *     long event_mask,
 *     XEvent *event_send
 * );
 *
 * Envoie un événement synthétique à une fenêtre.
 * Utilisé par X11EventLoopProxy.wakeUp() pour débloquer XNextEvent via
 * un ClientMessage envoyé à la fenêtre principale.
 *
 * Retourne Status (int) : 0 en cas d'échec, non-nul en cas de succès.
 */
internal val xSendEvent: MethodHandle? by lazy {
    libX11.downcall(
        "XSendEvent",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Status (int)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window w (XID)
            ValueLayout.JAVA_INT,   // Bool propagate
            ValueLayout.JAVA_LONG,  // long event_mask
            ValueLayout.ADDRESS,    // XEvent* event_send
        )
    )
}

// ── XkbSetDetectableAutoRepeat ────────────────────────────────────────────────

/**
 * Bool XkbSetDetectableAutoRepeat(Display *display, Bool detectable, Bool *supported_rtrn);
 *
 * Active le mode "detectable auto-repeat" permettant de distinguer les vraies
 * répétitions automatiques des frappes et relâchements réels.
 */
internal val xkbSetDetectableAutoRepeat: MethodHandle? by lazy {
    libX11.downcall(
        "XkbSetDetectableAutoRepeat",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Bool retour
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_INT,   // Bool detectable
            ValueLayout.ADDRESS,    // Bool* supported_rtrn (peut être NULL)
        )
    )
}

// ── XResourceManagerString ────────────────────────────────────────────────────

/**
 * char *XResourceManagerString(Display *display);
 *
 * Retourne la chaîne de la base de données de ressources X11 (RESOURCE_MANAGER).
 * Utilisée pour lire les préférences DPI (Xft.dpi), la police, etc.
 */
internal val xResourceManagerString: MethodHandle? by lazy {
    libX11.downcall(
        "XResourceManagerString",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // char* retour
            ValueLayout.ADDRESS,    // Display*
        )
    )
}
