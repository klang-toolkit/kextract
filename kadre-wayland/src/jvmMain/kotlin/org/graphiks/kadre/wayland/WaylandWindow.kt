/**
 * Implémentation Wayland de l'interface [Window] pour Linux Desktop.
 *
 * Utilise la Foreign Function & Memory API (JEP 454, JDK 25) pour interagir
 * avec libwayland-client.so.0 sans JNA ni autre couche intermédiaire.
 *
 * Flux de création (simplifié — xdg_shell complet délégué à WaylandEventLoop) :
 *  1. wl_compositor_create_surface  — crée la wl_surface (opcode 0 sur compositor)
 *  2. wl_display_flush              — envoie la requête au serveur
 *
 * Les appels xdg_surface / xdg_toplevel nécessitent des pointeurs vers les
 * structures wl_interface (non disponibles via FFM pur) ; ils sont implémentés
 * en stub et délégués à WaylandEventLoop (ticket #66).
 *
 * WaylandWindow — implémentation de l'interface Window.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.MemorySegment

/** Opcode wl_compositor.create_surface dans le protocole Wayland de base. */
private const val WL_COMPOSITOR_CREATE_SURFACE_OPCODE: Int = 0

/** Opcode wl_surface.commit dans le protocole Wayland de base. */
private const val WL_SURFACE_COMMIT_OPCODE: Int = 6


/**
 * Fenêtre Wayland native implémentant [Window].
 *
 * Le constructeur est interne : utilisez [WaylandWindow.create] pour instancier.
 * Les instances sont créées par WaylandEventLoop (ticket #66) qui fournit
 * les pointeurs display, compositor et xdgWmBase déjà initialisés.
 *
 * @param displayPtr   Pointeur wl_display* (adresse Long du MemorySegment).
 * @param compositorPtr Pointeur wl_compositor* (proxy Wayland retourné par wl_registry_bind).
 * @param xdgWmBasePtr  Pointeur xdg_wm_base* (proxy Wayland, ou 0 si non disponible).
 * @param attrs         Attributs de création de la fenêtre.
 */
class WaylandWindow private constructor(
    private val displayPtr: Long,
    private val compositorPtr: Long,
    private val xdgWmBasePtr: Long,
    private val surfacePtr: Long,
    private val attrs: WindowAttributes,
) : Window {

    /** Identifiant unique basé sur l'adresse de la wl_surface. */
    override val id: WindowId = WindowId(surfacePtr)

    override val rawWindowHandle: Any
        get() = RawWindowHandle.Wayland(surface = surfacePtr, display = displayPtr)

    override val rawDisplayHandle: Any
        get() = RawDisplayHandle.Wayland(display = displayPtr)

    /**
     * Taille interne courante en pixels physiques.
     *
     * Initialisée depuis attrs.size ; mise à jour par les événements xdg_surface.configure
     * via [onConfigure].
     */
    @Volatile
    private var _innerSize: PhysicalSize<Int> = attrs.size ?: PhysicalSize(800, 600)

    override val innerSize: PhysicalSize<Int>
        get() = _innerSize

    /**
     * Taille externe (surface + décorations WM) en pixels physiques.
     *
     * Sur Wayland, les décorations côté serveur (SSD) sont gérées par le compositeur.
     * Sans accès aux événements de configuration de décoration, on retourne la même
     * valeur que [innerSize].
     */
    override val outerSize: PhysicalSize<Int>
        get() = _innerSize

    /**
     * Facteur d'échelle DPI de cette fenêtre.
     *
     * Retourne 1.0 par défaut ; mis à jour via les événements wl_output.scale (prévu ultérieurement).
     */
    override val scaleFactor: Double = 1.0

    /**
     * Demande un rafraîchissement en commitant la surface Wayland.
     *
     * Sur Wayland, le rendu est déclenché par wl_surface.commit (opcode 6).
     * Si les bindings ne sont pas disponibles (non-Linux), l'appel est ignoré.
     */
    override fun requestRedraw() {
        if (surfacePtr == 0L) return
        val handle = wlProxyMarshalFlagsVoid ?: return
        try {
            val surfaceSeg = MemorySegment.ofAddress(surfacePtr)
            handle.invokeExact(
                surfaceSeg,
                WL_SURFACE_COMMIT_OPCODE,
                MemorySegment.NULL,  // wl_interface* = NULL pour les appels sans new_id
                WL_COMPOSITOR_VERSION,
                0,                   // flags = 0
            )
            // Flush pour envoyer la requête au serveur
            wlDisplayFlush?.let { flush ->
                val displaySeg = MemorySegment.ofAddress(displayPtr)
                flush.invokeExact(displaySeg) as Int
            }
        } catch (_: Throwable) {
            // Ignore — surface invalide ou bibliothèque absente
        }
    }

    /**
     * Définit le titre de la fenêtre via xdg_toplevel.set_title.
     *
     * L'appel xdg_toplevel.set_title nécessite un pointeur xdg_toplevel* créé
     * lors de la négociation xdg_shell. Cette implémentation est un stub logué ;
     * le support complet sera fourni par WaylandEventLoop (ticket #66).
     *
     * @param title Nouveau titre de la fenêtre.
     */
    override fun setTitle(title: String) {
        // Stub : xdg_toplevel nécessite une négociation xdg_shell complète (ticket #66)
    }

    /**
     * Rend la fenêtre visible ou invisible.
     *
     * Sur Wayland, la visibilité d'une surface toplevel est contrôlée via
     * xdg_surface / xdg_toplevel. Le commit initial rend la surface visible ;
     * wl_surface.attach(NULL) + commit la masque.
     * Cette implémentation effectue un commit pour la rendre visible.
     *
     * @param visible true pour afficher la fenêtre, false ignoré (stub).
     */
    override fun setVisible(visible: Boolean) {
        if (visible) requestRedraw()
        // setInvisible nécessite wl_surface.attach(NULL) + commit — reporté à plus tard
    }

    /**
     * Ferme la fenêtre en détruisant la wl_surface via wl_proxy_destroy.
     *
     * Sur Wayland, la fermeture propre passe par xdg_toplevel.destroy →
     * xdg_surface.destroy → wl_surface.destroy. Cette implémentation simplifée
     * appelle directement wl_proxy_destroy sur la surface.
     */
    override fun close() {
        if (surfacePtr == 0L) return
        val handle = wlProxyDestroy ?: return
        try {
            val surfaceSeg = MemorySegment.ofAddress(surfacePtr)
            handle.invokeExact(surfaceSeg)
            wlDisplayFlush?.let { flush ->
                val displaySeg = MemorySegment.ofAddress(displayPtr)
                flush.invokeExact(displaySeg) as Int
            }
        } catch (_: Throwable) {
            // Ignore — proxy déjà détruit ou bibliothèque absente
        }
    }

    /**
     * Met à jour la taille interne lors de la réception d'un événement xdg_surface.configure.
     *
     * @param width  Nouvelle largeur suggérée par le compositeur en pixels (0 = laisser inchangé).
     * @param height Nouvelle hauteur suggérée par le compositeur en pixels (0 = laisser inchangé).
     */
    fun onConfigure(width: Int, height: Int) {
        if (width > 0 && height > 0) {
            _innerSize = PhysicalSize(width, height)
        }
    }

    // ── Companion ─────────────────────────────────────────────────────────────

    companion object {

        /**
         * Crée une fenêtre Wayland native.
         *
         * Effectue la création de la wl_surface depuis le wl_compositor.
         * Les étapes xdg_surface / xdg_toplevel sont déléguées à WaylandEventLoop (ticket #66).
         *
         * @param display     Pointeur wl_display* (adresse Long du MemorySegment).
         * @param compositor  Pointeur wl_compositor* (proxy retourné par wl_registry_bind).
         * @param xdgWmBase   Pointeur xdg_wm_base* (proxy, ou 0 si non disponible).
         * @param attrs       Attributs de la fenêtre (titre, taille, visibilité, etc.).
         * @return La fenêtre créée, ou null si les bindings libwayland-client ne sont pas
         *         disponibles (macOS/Windows) ou si la création de surface échoue.
         */
        fun create(
            display: Long,
            compositor: Long,
            xdgWmBase: Long,
            attrs: WindowAttributes,
        ): WaylandWindow? {
            // Les bindings sont null sur les plateformes non-Wayland — retourner null.
            val createSurface = wlCompositorCreateSurface ?: return null
            // wl_proxy_marshal_flags déréférence le wl_interface* pour une requête new_id :
            // il DOIT être non-NULL, sinon SIGSEGV natif (non rattrapable par try/catch).
            val surfaceInterface = wlSurfaceInterface ?: return null

            // ── 1. wl_compositor_create_surface ──────────────────────────────
            val surface: Long = try {
                val compositorSeg = MemorySegment.ofAddress(compositor)
                (createSurface.invokeExact(
                    compositorSeg,
                    WL_COMPOSITOR_CREATE_SURFACE_OPCODE,
                    surfaceInterface,    // &wl_surface_interface (symbole libwayland)
                    WL_COMPOSITOR_VERSION,
                    0,                   // flags = 0
                    MemorySegment.NULL,  // new_id placeholder (libwayland crée le proxy)
                ) as MemorySegment).address()
            } catch (_: Throwable) {
                0L
            }

            if (surface == 0L && compositor != 0L) {
                // Sur non-Wayland ou en test avec pointeurs mock (compositor = 0),
                // on autorise surface = 0 pour les tests unitaires.
                // Si compositor != 0 et surface = 0 : échec réel de création.
                return null
            }

            val window = WaylandWindow(display, compositor, xdgWmBase, surface, attrs)

            // ── 2. Commit initial (rend la surface visible au compositeur) ────
            if (attrs.visible && surface != 0L) {
                window.requestRedraw()
            }

            return window
        }

        /**
         * Crée une [WaylandWindow] avec des pointeurs mock, pour les tests unitaires.
         *
         * Utilisable sans libwayland-client.so.0 — ne tente aucun appel FFM.
         *
         * @param display    Pointeur mock wl_display* (peut être 0 en test).
         * @param compositor Pointeur mock wl_compositor* (peut être 0 en test).
         * @param xdgWmBase  Pointeur mock xdg_wm_base* (peut être 0 en test).
         * @param surface    Pointeur mock wl_surface* (peut être 0 en test).
         * @param attrs      Attributs de la fenêtre.
         * @return Instance [WaylandWindow] directement construite, sans appels FFM.
         */
        internal fun createForTest(
            display: Long = 0L,
            compositor: Long = 0L,
            xdgWmBase: Long = 0L,
            surface: Long = 0L,
            attrs: WindowAttributes = WindowAttributes(),
        ): WaylandWindow = WaylandWindow(display, compositor, xdgWmBase, surface, attrs)
    }
}
