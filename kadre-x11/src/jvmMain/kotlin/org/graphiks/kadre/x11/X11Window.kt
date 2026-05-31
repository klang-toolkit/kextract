/**
 * Implémentation X11 de l'interface [Window] pour Linux Desktop.
 *
 * Utilise la Foreign Function & Memory API (JEP 454, JDK 25) pour interagir
 * avec libX11.so.6 sans JNA ni autre couche intermédiaire.
 *
 * Flux de création :
 *  1. XCreateSimpleWindow     — crée la fenêtre enfant de la fenêtre racine
 *  2. XSelectInput            — sélectionne le masque d'événements complet
 *  3. XInternAtom             — obtient l'atome WM_DELETE_WINDOW
 *  4. XSetWMProtocols         — installe le protocole de fermeture propre
 *  5. XStoreName              — définit le titre
 *  6. XMapWindow              — rend la fenêtre visible (si attrs.visible = true)
 *
 * X11Window — implémentation complète de l'interface Window.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Masque d'événements combiné sélectionné pour chaque fenêtre X11.
 *
 * Inclut : Expose, KeyPress, KeyRelease, ButtonPress, ButtonRelease,
 * PointerMotion, StructureNotify (ConfigureNotify, DestroyNotify, …).
 */
private val FULL_EVENT_MASK: Long =
    ExposureMask or
    KeyPressMask or
    KeyReleaseMask or
    ButtonPressMask or
    ButtonReleaseMask or
    PointerMotionMask or
    StructureNotifyMask

/**
 * Fenêtre X11 native implémentant [Window].
 *
 * Le constructeur est interne : utilisez [X11Window.create] pour instancier.
 *
 * @param displayPtr Pointeur vers la structure Display X11 (valeur Long du MemorySegment.address()).
 * @param xWindowId  Identifiant XID de la fenêtre créée (unsigned long → Long).
 * @param attrs      Attributs de création de la fenêtre.
 */
class X11Window private constructor(
    private val displayPtr: Long,
    private val xWindowId: Long,
    private val attrs: WindowAttributes,
) : Window {

    override val id: WindowId = WindowId(xWindowId)

    override val rawWindowHandle: Any
        get() = RawWindowHandle.Xlib(window = xWindowId, display = displayPtr)

    override val rawDisplayHandle: Any
        get() = RawDisplayHandle.Xlib(display = displayPtr)

    /**
     * Taille interne courante en pixels physiques.
     *
     * Initialisée depuis attrs.size ; mise à jour par les événements ConfigureNotify
     * via [onConfigureNotify].
     */
    @Volatile
    private var _innerSize: PhysicalSize<Int> = attrs.size ?: PhysicalSize(800, 600)

    override val innerSize: PhysicalSize<Int>
        get() = _innerSize

    /**
     * Taille externe (surface + décorations WM) en pixels physiques.
     *
     * Sur X11, les décorations sont gérées par le gestionnaire de fenêtres et
     * inconnues sans appel à XGetGeometry + XQueryTree. On retourne la même
     * valeur que [innerSize] pour l'instant.
     *
     * TODO : utiliser XGetGeometry pour distinguer inner/outer au besoin.
     */
    override val outerSize: PhysicalSize<Int>
        get() = _innerSize

    /**
     * Facteur d'échelle DPI de cette fenêtre.
     *
     * Retourne 1.0 (heuristique DPI prévue ultérieurement).
     */
    override val scaleFactor: Double = 1.0

    override fun requestRedraw() {
        // Aucune action directe nécessaire : la boucle d'événements relève les Expose.
        // Éventuellement, on pourrait envoyer un XSendEvent Expose — reporté ultérieurement.
    }

    override fun setTitle(title: String) {
        val handle = xStoreName ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        Arena.ofConfined().use { arena ->
            val nameBytes = title.toByteArray(Charsets.ISO_8859_1)
            val namePtr = arena.allocate(nameBytes.size.toLong() + 1)
            for (i in nameBytes.indices) namePtr.set(ValueLayout.JAVA_BYTE, i.toLong(), nameBytes[i])
            namePtr.set(ValueLayout.JAVA_BYTE, nameBytes.size.toLong(), 0)
            handle.invokeExact(display, xWindowId, namePtr) as Int
        }
    }

    override fun setVisible(visible: Boolean) {
        if (visible) {
            val handle = xMapWindow ?: return
            val display = MemorySegment.ofAddress(displayPtr)
            handle.invokeExact(display, xWindowId) as Int
            xFlush?.invokeExact(display) as? Int
        }
        // XUnmapWindow n'est pas encore dans les bindings — reporté ultérieurement.
    }

    override fun close() {
        val handle = xDestroyWindow ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        handle.invokeExact(display, xWindowId) as Int
        xFlush?.invokeExact(display) as? Int
    }

    /**
     * Met à jour la taille interne lors de la réception d'un événement ConfigureNotify.
     *
     * @param width  Nouvelle largeur en pixels.
     * @param height Nouvelle hauteur en pixels.
     */
    fun onConfigureNotify(width: Int, height: Int) {
        if (width > 0 && height > 0) {
            _innerSize = PhysicalSize(width, height)
        }
    }

    // ── Companion ─────────────────────────────────────────────────────────────

    companion object {

        /**
         * Crée une fenêtre X11 native.
         *
         * Effectue toutes les initialisations nécessaires :
         * XCreateSimpleWindow → XSelectInput → WM_DELETE_WINDOW → XStoreName → XMapWindow.
         *
         * @param display Long représentant le pointeur Display* (adresse du MemorySegment).
         * @param screen  Numéro de l'écran X11 (DefaultScreen).
         * @param attrs   Attributs de la fenêtre (titre, taille, visibilité, etc.).
         * @return La fenêtre créée, ou null si les bindings libX11 ne sont pas disponibles
         *         (macOS/Windows) ou si la création échoue.
         */
        fun create(display: Long, screen: Int, attrs: WindowAttributes): X11Window? {
            // Les bindings sont null sur non-Linux — retourner null gracieusement.
            val createHandle = xCreateSimpleWindow ?: return null

            val displaySeg = MemorySegment.ofAddress(display)

            // ── 1. Fenêtre racine via XRootWindow(display, screen) ────────────
            // Équivalent de DefaultRootWindow(display). Le vrai XID racine est requis
            // comme parent de XCreateSimpleWindow : une valeur conventionnelle en dur
            // provoque BadWindow (X_CreateWindow) sur les serveurs X réels.
            val rootHandle = xRootWindow ?: return null
            val rootWindow: Long = rootHandle.invokeExact(displaySeg, screen) as Long
            if (rootWindow == 0L) return null

            val width = attrs.size?.width ?: 800
            val height = attrs.size?.height ?: 600

            // ── 2. XCreateSimpleWindow ────────────────────────────────────────
            val xWindowId: Long = createHandle.invokeExact(
                displaySeg,     // Display*
                rootWindow,     // Window parent
                0,              // int x
                0,              // int y
                width,          // unsigned int width
                height,         // unsigned int height
                1,              // unsigned int border_width
                0L,             // unsigned long border (BlackPixel = 0)
                0L,             // unsigned long background (BlackPixel = 0)
            ) as Long

            if (xWindowId == 0L) return null

            // ── 3. XSelectInput ───────────────────────────────────────────────
            xSelectInput?.invokeExact(displaySeg, xWindowId, FULL_EVENT_MASK) as? Int

            // ── 4. WM_DELETE_WINDOW (protocole de fermeture propre) ───────────
            Arena.ofConfined().use { arena ->
                val atomName = "WM_DELETE_WINDOW".toByteArray(Charsets.US_ASCII)
                val atomNamePtr = arena.allocate(atomName.size.toLong() + 1)
                for (i in atomName.indices) atomNamePtr.set(ValueLayout.JAVA_BYTE, i.toLong(), atomName[i])
                atomNamePtr.set(ValueLayout.JAVA_BYTE, atomName.size.toLong(), 0)

                val wmDeleteWindow: Long = xInternAtom?.invokeExact(
                    displaySeg,
                    atomNamePtr,
                    0,  // Bool only_if_exists = False → crée si absent
                ) as? Long ?: 0L

                if (wmDeleteWindow != 0L) {
                    // Allouer un tableau de 1 Atom (unsigned long = 8 octets) pour XSetWMProtocols
                    val atomArray = arena.allocate(ValueLayout.JAVA_LONG, 1L)
                    atomArray.set(ValueLayout.JAVA_LONG, 0L, wmDeleteWindow)
                    xSetWMProtocols?.invokeExact(displaySeg, xWindowId, atomArray, 1) as? Int
                }
            }

            // ── 5. XStoreName ─────────────────────────────────────────────────
            Arena.ofConfined().use { arena ->
                val nameBytes = attrs.title.toByteArray(Charsets.ISO_8859_1)
                val namePtr = arena.allocate(nameBytes.size.toLong() + 1)
                for (i in nameBytes.indices) namePtr.set(ValueLayout.JAVA_BYTE, i.toLong(), nameBytes[i])
                namePtr.set(ValueLayout.JAVA_BYTE, nameBytes.size.toLong(), 0)
                xStoreName?.invokeExact(displaySeg, xWindowId, namePtr) as? Int
            }

            val window = X11Window(display, xWindowId, attrs)

            // ── 6. XMapWindow (si visible) ────────────────────────────────────
            if (attrs.visible) {
                xMapWindow?.invokeExact(displaySeg, xWindowId) as? Int
                xFlush?.invokeExact(displaySeg) as? Int
            }

            return window
        }
    }
}
