/**
 * Bindings FFM pour les fonctions Wayland nécessaires à la gestion de fenêtres.
 *
 * Charge libwayland-client.so.0 et libxkbcommon.so.0 via SymbolLookup.libraryLookup
 * avec un pattern tryCreate (try/catch Throwable) pour que le build passe sur
 * macOS/Windows sans les bibliothèques Wayland installées.
 *
 * Fonctions exposées (libwayland-client) :
 *  - wl_display_connect
 *  - wl_display_disconnect
 *  - wl_display_get_fd
 *  - wl_display_dispatch
 *  - wl_display_dispatch_pending
 *  - wl_display_prepare_read
 *  - wl_display_read_events
 *  - wl_display_cancel_read
 *  - wl_display_flush
 *  - wl_display_get_registry
 *  - wl_registry_add_listener
 *  - wl_registry_bind
 *  - wl_proxy_destroy
 *  - wl_proxy_add_listener
 *  - wl_proxy_marshal_flags (variante xdg_wm_base_get_xdg_surface)
 *  - wl_proxy_marshal_flags (variante wl_compositor_create_surface)
 *
 * Référence : https://wayland.freedesktop.org/docs/html/
 */
package org.graphiks.kadre.wayland

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

// ── Lazy loading des bibliothèques ────────────────────────────────────────────

/**
 * Lookup libwayland-client.so.0 — null sur les plateformes non-Wayland.
 *
 * Le try/catch sur Throwable est intentionnel : SymbolLookup.libraryLookup
 * peut lever IllegalArgumentException ou UnsatisfiedLinkError sur macOS/Windows,
 * et on veut que le build reste vert dans tous les cas.
 */
internal val libWaylandClient: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("libwayland-client.so.0", Arena.global())
    } catch (e: Throwable) {
        null
    }
}

/**
 * Lookup libc.so.6 — null sur les plateformes non-Linux ou si absent.
 *
 * Utilisé pour poll(), eventfd(), read(), write(), close() dans WaylandEventLoop.
 */
internal val libC: SymbolLookup? by lazy {
    try { SymbolLookup.libraryLookup("libc.so.6", Arena.global()) } catch (_: Throwable) { null }
}

/**
 * Lookup libxkbcommon.so.0 — null sur les plateformes non-Wayland.
 */
internal val libXkbCommon: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("libxkbcommon.so.0", Arena.global())
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

/** Adresse d'un symbole *donnée* (ex. struct wl_interface) exporté par la lib, ou null. */
private fun SymbolLookup?.symbol(name: String): MemorySegment? =
    this?.find(name)?.orElse(null)

/**
 * Crée un stub d'upcall natif (pointeur de fonction C → MethodHandle Kotlin).
 * Utilisé pour les listeners Wayland (wl_registry.global, etc.).
 */
internal fun upcallStub(
    handle: MethodHandle,
    descriptor: FunctionDescriptor,
    arena: java.lang.foreign.Arena,
): MemorySegment = linker.upcallStub(handle, descriptor, arena)

// ── Découverte des globaux : registry / bind via wl_proxy_marshal_flags ──
//
// wl_display_get_registry et wl_registry_bind sont des `static inline` du header (PAS
// des symboles exportés) : on doit donc passer par wl_proxy_marshal_flags. Les structures
// d'interface, elles, SONT exportées par libwayland-client.so.0.

/** &wl_registry_interface — requis par get_registry (requête new_id). */
internal val wlRegistryInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_registry_interface") }

/** &wl_compositor_interface — requis par bind(wl_compositor). */
internal val wlCompositorInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_compositor_interface") }

/** uint32_t wl_proxy_get_version(struct wl_proxy *proxy). */
internal val wlProxyGetVersion: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_get_version",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS))
}

/** int wl_display_roundtrip(struct wl_display *display) — bloque jusqu'au traitement des requêtes. */
internal val wlDisplayRoundtrip: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_display_roundtrip",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS))
}

/**
 * wl_proxy_marshal_flags pour une requête new_id simple (proxy, opcode, interface, version,
 * flags, new_id=NULL) — sert à wl_display.get_registry (opcode 1).
 */
internal val wlProxyMarshalNewId: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // proxy
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // interface
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // new_id = NULL
        ))
}

/**
 * wl_proxy_marshal_flags pour wl_registry.bind (opcode 0), signature variadique étendue :
 * (registry, 0, interface, version, flags, name(u), interface->name(s), version(u), new_id=NULL).
 */
internal val wlProxyMarshalBind: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // proxy (registry)
            ValueLayout.JAVA_INT,  // opcode (0 = bind)
            ValueLayout.ADDRESS,   // interface (&wl_compositor_interface)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // arg: name (uint)
            ValueLayout.ADDRESS,   // arg: interface->name (const char*)
            ValueLayout.JAVA_INT,  // arg: version (uint)
            ValueLayout.ADDRESS,   // arg: new_id = NULL
        ))
}

// ── wl_display_connect ────────────────────────────────────────────────────────

/**
 * struct wl_display *wl_display_connect(const char *name);
 *
 * Se connecte au serveur Wayland. Passer NULL pour utiliser WAYLAND_DISPLAY
 * (habituellement "wayland-0"). Retourne un pointeur wl_display* ou NULL en
 * cas d'échec.
 */
internal val wlDisplayConnect: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_connect",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS, // wl_display*
            ValueLayout.ADDRESS, // const char* name (ou NULL)
        )
    )
}

// ── wl_display_disconnect ─────────────────────────────────────────────────────

/**
 * void wl_display_disconnect(struct wl_display *display);
 *
 * Ferme la connexion au serveur Wayland et libère les ressources associées.
 */
internal val wlDisplayDisconnect: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_disconnect",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS, // wl_display*
        )
    )
}

// ── wl_display_get_fd ─────────────────────────────────────────────────────────

/**
 * int wl_display_get_fd(struct wl_display *display);
 *
 * Retourne le descripteur de fichier du socket de connexion Wayland.
 * Utile pour intégrer la boucle d'événements Wayland dans un sélecteur (epoll).
 */
internal val wlDisplayGetFd: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_get_fd",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int fd
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_dispatch ───────────────────────────────────────────────────────

/**
 * int wl_display_dispatch(struct wl_display *display);
 *
 * Traite les événements en attente et bloque jusqu'à en recevoir un.
 * Retourne le nombre d'événements traités, ou -1 en cas d'erreur.
 */
internal val wlDisplayDispatch: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_dispatch",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (nb événements ou -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_dispatch_pending ───────────────────────────────────────────────

/**
 * int wl_display_dispatch_pending(struct wl_display *display);
 *
 * Traite uniquement les événements déjà en file d'attente, sans bloquer.
 * Retourne le nombre d'événements traités, ou -1 en cas d'erreur.
 */
internal val wlDisplayDispatchPending: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_dispatch_pending",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (nb événements ou -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_prepare_read ───────────────────────────────────────────────────

/**
 * int wl_display_prepare_read(struct wl_display *display);
 *
 * Annonce l'intention de lire des événements depuis le socket Wayland.
 * Doit être suivi de wl_display_read_events() ou wl_display_cancel_read().
 * Retourne 0 en cas de succès, -1 si des événements sont déjà en attente.
 */
internal val wlDisplayPrepareRead: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_prepare_read",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (0 ou -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_read_events ────────────────────────────────────────────────────

/**
 * int wl_display_read_events(struct wl_display *display);
 *
 * Lit les événements depuis le socket et les place dans la file d'attente.
 * À appeler après wl_display_prepare_read(). Retourne 0 ou -1.
 */
internal val wlDisplayReadEvents: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_read_events",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (0 ou -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_cancel_read ────────────────────────────────────────────────────

/**
 * void wl_display_cancel_read(struct wl_display *display);
 *
 * Annule l'intention de lecture déclarée par wl_display_prepare_read().
 * À appeler si on décide de ne pas lire (ex. sélecteur timeout).
 */
internal val wlDisplayCancelRead: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_cancel_read",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS, // wl_display*
        )
    )
}

// ── wl_display_flush ──────────────────────────────────────────────────────────

/**
 * int wl_display_flush(struct wl_display *display);
 *
 * Envoie les données en attente dans le tampon d'envoi vers le serveur.
 * Retourne le nombre d'octets envoyés, ou -1 en cas d'erreur (errno = EAGAIN
 * si le socket est non-bloquant et le tampon plein).
 */
internal val wlDisplayFlush: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_flush",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (octets envoyés ou -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_get_registry ───────────────────────────────────────────────────

/**
 * struct wl_registry *wl_display_get_registry(struct wl_display *display);
 *
 * Retourne le registre global Wayland, point d'entrée pour lier des interfaces
 * globales (wl_compositor, xdg_wm_base, etc.) via wl_registry_bind.
 */
internal val wlDisplayGetRegistry: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_display_get_registry",
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS))
}

// ── wl_registry_add_listener ──────────────────────────────────────────────────

/**
 * int wl_registry_add_listener(struct wl_registry *registry,
 *     const struct wl_registry_listener *listener, void *data);
 *
 * Enregistre un listener pour les événements du registre (global/global_remove).
 * Retourne 0 en cas de succès, -1 en cas d'erreur.
 */
internal val wlRegistryAddListener: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_registry_add_listener",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS))
}

// ── wl_registry_bind ──────────────────────────────────────────────────────────

/**
 * void *wl_registry_bind(struct wl_registry *registry, uint32_t name,
 *     const struct wl_interface *interface, uint32_t version);
 *
 * Lie une interface globale par son nom numérique (annoncé via wl_registry.global).
 * Retourne un proxy Wayland opaque (wl_compositor*, xdg_wm_base*, etc.).
 */
internal val wlRegistryBind: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_registry_bind",
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT))
}

// ── wl_proxy_destroy ──────────────────────────────────────────────────────────

/**
 * void wl_proxy_destroy(struct wl_proxy *proxy);
 *
 * Détruit un proxy Wayland et libère ses ressources. À appeler avant de
 * déconnecter l'affichage pour tout proxy non détruit par une opération destroy.
 */
internal val wlProxyDestroy: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_destroy",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
}

// ── wl_proxy_add_listener ─────────────────────────────────────────────────────

/**
 * int wl_proxy_add_listener(struct wl_proxy *proxy,
 *     void (**implementation)(void), void *data);
 *
 * Associe une table de fonctions (vtable) et des données utilisateur à un proxy.
 * Utilisé pour recevoir les événements côté client (configure, ping, etc.).
 * Retourne 0 en cas de succès, -1 en cas d'erreur.
 */
internal val wlProxyAddListener: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_add_listener",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS))
}

// ── wl_proxy_marshal_flags (variante xdg_wm_base_get_xdg_surface) ────────────

/**
 * struct wl_proxy *wl_proxy_marshal_flags(struct wl_proxy *proxy,
 *     uint32_t opcode, const struct wl_interface *interface,
 *     uint32_t version, uint32_t flags,
 *     struct wl_proxy *new_id, struct wl_proxy *surface);
 *
 * Variante à 7 arguments pour wl_proxy_marshal_flags utilisée par
 * xdg_wm_base_get_xdg_surface (opcode XDG_WM_BASE_GET_XDG_SURFACE).
 *
 * La fonction C est variadique ; on bind ici la forme concrète à 2 arguments
 * supplémentaires (new_id + surface) qui correspond exactement à cet opcode.
 */
internal val wlProxyMarshalFlagsGetXdgSurface: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // proxy
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // interface
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg: new_id
            ValueLayout.ADDRESS,   // arg: surface
        ))
}

// ── wl_compositor_create_surface ──────────────────────────────────────────────

/**
 * Adresse de la structure `wl_surface_interface` exportée par libwayland-client.so.0.
 *
 * `wl_proxy_marshal_flags` exige un `wl_interface*` NON-NULL pour les requêtes new_id
 * (comme create_surface) afin de typer le proxy retourné : il déréférence cette
 * structure. Passer NULL provoque un SIGSEGV. On récupère donc le vrai symbole exporté.
 */
internal val wlSurfaceInterface: MemorySegment? by lazy {
    libWaylandClient?.find("wl_surface_interface")?.orElse(null)
}

/**
 * wl_compositor_create_surface: crée une wl_surface depuis un wl_compositor.
 *
 * Appelle wl_proxy_marshal_flags(compositor, 0, &wl_surface_interface, version, 0)
 * où l'opcode 0 correspond à wl_compositor.create_surface dans le protocole Wayland.
 *
 * La variante à 5 arguments fixe (sans new_id supplémentaire) correspond
 * à la forme variadique de wl_proxy_marshal_flags pour un opcode new_id simple :
 * le proxy retourné est la nouvelle wl_surface*.
 *
 * @see XdgShellConstants — opcodes du protocole xdg_shell associés.
 */
internal val wlCompositorCreateSurface: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_proxy* (compositor)
            ValueLayout.JAVA_INT,  // opcode (0 = create_surface)
            ValueLayout.ADDRESS,   // wl_interface* (&wl_surface_interface — DOIT être non-NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags (0)
            ValueLayout.ADDRESS,   // arg new_id : NULL (placeholder, généré par libwayland)
        ))
}

// ── wl_proxy_marshal_flags (variante wl_surface_commit) ───────────────────────

/**
 * Variante sans argument supplémentaire de wl_proxy_marshal_flags utilisée pour
 * wl_surface.commit (opcode 6) et d'autres opcodes sans paramètre de retour.
 *
 * Signature : void wl_proxy_marshal_flags(proxy, opcode, NULL, version, 0)
 * avec NULL comme wl_interface* pour les appels sans new_id.
 */
internal val wlProxyMarshalFlagsVoid: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy* (surface / proxy cible)
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags (0)
        ))
}

// ── libc : poll ───────────────────────────────────────────────────────────────

/**
 * int poll(struct pollfd *fds, nfds_t nfds, int timeout);
 *
 * Attend des événements sur plusieurs descripteurs de fichiers.
 *  - fds     : tableau de structures pollfd (fd, events, revents)
 *  - nfds    : nombre d'entrées dans fds
 *  - timeout : délai en millisecondes (-1 = attente infinie, 0 = retour immédiat)
 * Retourne le nombre de descripteurs prêts, 0 si timeout, -1 si erreur.
 */
internal val nativePoll: MethodHandle? by lazy {
    libC.downcall("poll", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.ADDRESS,   // struct pollfd *fds
        ValueLayout.JAVA_INT,  // nfds_t nfds
        ValueLayout.JAVA_INT,  // int timeout
    ))
}

// ── libc : eventfd ────────────────────────────────────────────────────────────

/**
 * int eventfd(unsigned int initval, int flags);
 *
 * Crée un descripteur de fichier de notification d'événements (Linux).
 * En mode compteur (flags=0), write() ajoute à la valeur, read() lit et remet à 0.
 * Utilisé pour réveiller la boucle d'événements depuis un autre thread.
 * Retourne le descripteur de fichier, ou -1 si erreur.
 */
internal val nativeEventfd: MethodHandle? by lazy {
    libC.downcall("eventfd", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT,  // unsigned int initval
        ValueLayout.JAVA_INT,  // int flags
    ))
}

// ── libc : read ───────────────────────────────────────────────────────────────

/**
 * ssize_t read(int fd, void *buf, size_t count);
 *
 * Lit jusqu'à count octets depuis fd dans buf.
 * Pour un eventfd en mode compteur, lit 8 octets (uint64_t) et remet le compteur à 0.
 * Retourne le nombre d'octets lus, ou -1 si erreur.
 */
internal val nativeRead: MethodHandle? by lazy {
    libC.downcall("read", FunctionDescriptor.of(
        ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_INT,  // int fd
        ValueLayout.ADDRESS,   // void *buf
        ValueLayout.JAVA_LONG, // size_t count
    ))
}

// ── libc : write ──────────────────────────────────────────────────────────────

/**
 * ssize_t write(int fd, const void *buf, size_t count);
 *
 * Écrit count octets depuis buf dans fd.
 * Pour un eventfd en mode compteur, écrit 8 octets (uint64_t) pour incrémenter le compteur.
 * Retourne le nombre d'octets écrits, ou -1 si erreur.
 */
internal val nativeWrite: MethodHandle? by lazy {
    libC.downcall("write", FunctionDescriptor.of(
        ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_INT,  // int fd
        ValueLayout.ADDRESS,   // const void *buf
        ValueLayout.JAVA_LONG, // size_t count
    ))
}

// ── libc : close ──────────────────────────────────────────────────────────────

/**
 * int close(int fd);
 *
 * Ferme un descripteur de fichier et libère les ressources associées.
 * Retourne 0 en cas de succès, -1 si erreur.
 */
internal val nativeClose: MethodHandle? by lazy {
    libC.downcall("close", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT,  // int fd
    ))
}

// ── pollfd layout helpers ─────────────────────────────────────────────────────

/**
 * Valeur POLLIN : le descripteur est prêt en lecture.
 * Positionnée dans pollfd.events pour indiquer l'intérêt en lecture.
 */
internal const val POLLIN: Short = 1

/**
 * Alloue un tableau de 2 structures pollfd dans l'arène fournie.
 *
 * Layout Linux 64-bit d'une struct pollfd :
 *  - offset 0 : fd      (int, 4 octets)
 *  - offset 4 : events  (short, 2 octets)
 *  - offset 6 : revents (short, 2 octets)
 *  - taille   : 8 octets
 *
 * @return MemorySegment de 16 octets aligné sur 4 octets.
 */
internal fun allocPollFd(arena: java.lang.foreign.Arena): java.lang.foreign.MemorySegment =
    arena.allocate(8L * 2, 4L)

/**
 * Initialise une entrée pollfd dans le tableau.
 *
 * @param seg    Tableau de pollfd alloué par [allocPollFd].
 * @param idx    Indice de l'entrée (0 ou 1).
 * @param fd     Descripteur de fichier à surveiller.
 * @param events Masque d'événements (ex. [POLLIN]).
 */
internal fun setPollFd(seg: java.lang.foreign.MemorySegment, idx: Int, fd: Int, events: Short) {
    seg.set(ValueLayout.JAVA_INT, idx * 8L, fd)
    seg.set(ValueLayout.JAVA_SHORT, idx * 8L + 4, events)
}

/**
 * Lit le champ revents d'une entrée pollfd.
 *
 * @param seg Tableau de pollfd.
 * @param idx Indice de l'entrée (0 ou 1).
 * @return Masque revents positionné par le noyau après poll().
 */
internal fun getPollRevents(seg: java.lang.foreign.MemorySegment, idx: Int): Short =
    seg.get(ValueLayout.JAVA_SHORT, idx * 8L + 6)
