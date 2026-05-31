/**
 * Implémentation Win32 de l'interface [Window] pour Windows Desktop.
 *
 * Utilise la Foreign Function & Memory API (JEP 454, JDK 25) pour interagir
 * avec user32.dll et kernel32.dll sans JNA ni autre couche intermédiaire.
 *
 * Flux de création :
 *  1. [companion.registerClassOnce] → RegisterClassExW (exécuté une seule fois)
 *  2. [createWindow]                → CreateWindowExW
 *  3. ShowWindow / UpdateWindow     → affichage initial
 *
 * GRA-141 : Win32Window — implémentation complète de l'interface Window.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Fenêtre Win32 native implémentant [Window].
 *
 * Le constructeur est privé : utilisez [Win32Window.create] pour instancier.
 *
 * @param hwnd      Handle natif de la fenêtre (HWND), représenté par MemorySegment.
 * @param hInstance Handle du module courant (HINSTANCE), représenté par MemorySegment.
 * @param attrs     Attributs de création de la fenêtre.
 */
class Win32Window private constructor(
    private val hwnd: MemorySegment,
    private val hInstance: MemorySegment,
    private val attrs: WindowAttributes,
) : Window {

    override val id: WindowId = WindowId(hwnd.address())

    override val rawWindowHandle: Any
        get() = RawWindowHandle.Win32(
            hwnd = hwnd.address(),
            hinstance = hInstance.address(),
        )

    override val rawDisplayHandle: Any
        get() = RawDisplayHandle.Win32(hinstance = hInstance.address())

    /**
     * Flag de redraw — positionné par [requestRedraw], consommé par la boucle de messages.
     */
    @Volatile
    private var needsRedraw: Boolean = false

    override fun requestRedraw() {
        needsRedraw = true
    }

    override fun setTitle(title: String) {
        val handle = setWindowTextW ?: return
        Arena.ofConfined().use { arena ->
            val titleW = arena.allocateWString(title)
            handle.invokeExact(hwnd, titleW) as Int
        }
    }

    /**
     * Taille interne (surface de rendu) en pixels physiques.
     *
     * Sur Windows, on retourne la taille configurée dans les attributs,
     * ou une valeur par défaut de 800 × 600 si non spécifiée.
     *
     * TODO GRA-142 : utiliser GetClientRect pour lire la taille réelle.
     */
    override val innerSize: PhysicalSize<Int>
        get() = attrs.size ?: PhysicalSize(800, 600)

    /**
     * Taille externe (surface + décorations) en pixels physiques.
     *
     * TODO GRA-142 : utiliser GetWindowRect pour lire la taille réelle.
     */
    override val outerSize: PhysicalSize<Int>
        get() = attrs.size ?: PhysicalSize(800, 600)

    /**
     * Facteur d'échelle DPI de cette fenêtre.
     *
     * Appelle GetDpiForWindow(hwnd) et divise par 96 (DPI logique de référence
     * Windows). Retourne 1.0 en cas d'indisponibilité (non-Windows ou fenêtre
     * invalide) pour un comportement gracieux cross-plateforme.
     *
     * GRA-12 : DPI awareness PerMonitorV2.
     */
    override val scaleFactor: Double
        get() = try {
            val dpi = getDpiForWindow?.invokeExact(hwnd) as? Int ?: 0
            if (dpi > 0) dpi.toDouble() / 96.0 else 1.0
        } catch (_: Throwable) {
            1.0
        }

    override fun setVisible(visible: Boolean) {
        val handle = showWindow ?: return
        val nCmdShow = if (visible) SW_SHOW else SW_HIDE
        handle.invokeExact(hwnd, nCmdShow) as Int
    }

    override fun close() {
        val handle = destroyWindow ?: return
        handle.invokeExact(hwnd) as Int
    }

    // ── Companion ─────────────────────────────────────────────────────────────

    companion object {

        /** Nom de la classe de fenêtre Win32 enregistrée. */
        private const val CLASS_NAME = "KadreWin32Window"

        /**
         * Garde atomique pour l'enregistrement de la classe de fenêtre.
         *
         * RegisterClassExW ne doit être appelé qu'une seule fois par processus
         * pour un nom de classe donné.
         */
        private val classRegistered = AtomicBoolean(false)

        /**
         * Stub d'upcall pour la WndProc — doit rester en vie aussi longtemps
         * que des fenêtres de cette classe existent.
         *
         * Alloué dans [Win32WndProcArena.arena] (Arena.ofShared).
         */
        @Volatile
        private var wndProcStub: MemorySegment? = null

        /**
         * Enregistre la classe de fenêtre Win32 une seule fois.
         *
         * Thread-safe via [classRegistered] (AtomicBoolean compare-and-set).
         * No-op sur macOS/Linux (les MethodHandle sont null).
         *
         * @param hInstance Handle du module courant.
         * @param classNamePtr Pointeur wide-string vers le nom de la classe.
         */
        private fun registerClassOnce(hInstance: MemorySegment, classNamePtr: MemorySegment) {
            if (!classRegistered.compareAndSet(false, true)) return

            val registerHandle = registerClassExW ?: return

            // Créer le stub d'upcall pour la WndProc
            val wndProcMH = MethodHandles.lookup().findStatic(
                Win32Window::class.java,
                "wndProc",
                MethodType.methodType(Long::class.java, MemorySegment::class.java, Int::class.java, Long::class.java, Long::class.java)
            )

            val wndProcDesc = FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,  // LRESULT
                ValueLayout.ADDRESS,    // HWND
                ValueLayout.JAVA_INT,   // UINT (message)
                ValueLayout.JAVA_LONG,  // WPARAM
                ValueLayout.JAVA_LONG,  // LPARAM
            )

            val linker = Linker.nativeLinker()
            val stub = linker.upcallStub(wndProcMH, wndProcDesc, Win32WndProcArena.arena)
            wndProcStub = stub

            // Allouer et remplir la structure WNDCLASSEXW dans une arène temporaire
            Arena.ofConfined().use { arena ->
                val wndClass = WndClassExW(arena)
                wndClass.cbSize = WndClassExW.SIZEOF
                wndClass.style = CS_HREDRAW_VREDRAW
                wndClass.lpfnWndProc = stub
                wndClass.cbClsExtra = 0
                wndClass.cbWndExtra = 0
                wndClass.hInstance = hInstance
                wndClass.hIcon = MemorySegment.NULL
                wndClass.hCursor = MemorySegment.NULL
                wndClass.hbrBackground = MemorySegment.NULL
                wndClass.lpszMenuName = MemorySegment.NULL
                wndClass.lpszClassName = classNamePtr
                wndClass.hIconSm = MemorySegment.NULL

                val atom = registerHandle.invokeExact(wndClass.segment) as Short
                if (atom.toInt() == 0) {
                    // Réinitialiser pour permettre une future tentative
                    classRegistered.set(false)
                    wndProcStub = null
                    error("RegisterClassExW a échoué (atom = 0)")
                }
            }
        }

        /**
         * Procédure de fenêtre Win32 (WndProc).
         *
         * Appelée par le système Windows pour chaque message envoyé à une fenêtre
         * de la classe KadreWin32Window. Délègue l'intégralité du dispatch à
         * [KadreWndProc.dispatch] qui traduit les messages Win32 en [WindowEvent]
         * kadre et les transmet au handler installé.
         *
         * ⚠️ Cette méthode est appelée depuis le thread de messages Win32 —
         * elle doit être @JvmStatic pour que MethodHandles.lookup() la trouve.
         */
        @JvmStatic
        fun wndProc(hwnd: MemorySegment, msg: Int, wParam: Long, lParam: Long): Long {
            return KadreWndProc.dispatch(hwnd.address(), msg, wParam, lParam)
        }

        /**
         * Crée une fenêtre Win32 native.
         *
         * Enregistre la classe de fenêtre si nécessaire, puis appelle
         * CreateWindowExW pour créer la fenêtre native.
         *
         * @param attrs Attributs de la fenêtre (titre, taille, visibilité, etc.).
         * @return La fenêtre créée, ou null si les bindings Win32 ne sont pas disponibles
         *         (macOS/Linux) ou si la création échoue.
         */
        fun create(attrs: WindowAttributes): Win32Window? {
            // Vérifier la disponibilité des bindings Win32
            val createHandle = createWindowExW ?: return null
            val getModuleHandle = getModuleHandleW ?: return null

            // Obtenir le handle du module courant (GetModuleHandleW(NULL))
            val hInstance = getModuleHandle.invokeExact(MemorySegment.NULL) as MemorySegment
            if (hInstance == MemorySegment.NULL) return null

            // Allouer le nom de classe dans une arène à longue durée de vie
            // (doit rester valide pendant toute la durée de vie des fenêtres de la classe)
            val classArena = Win32WndProcArena.arena
            val classNamePtr = classArena.allocateWString(CLASS_NAME)

            // Enregistrer la classe une seule fois
            registerClassOnce(hInstance, classNamePtr)

            // Créer la fenêtre
            val width = attrs.size?.width ?: 800
            val height = attrs.size?.height ?: 600

            val hwnd: MemorySegment = Arena.ofConfined().use { arena ->
                val titlePtr = arena.allocateWString(attrs.title)
                createHandle.invokeExact(
                    WS_EX_APPWINDOW,        // dwExStyle
                    classNamePtr,           // lpClassName
                    titlePtr,               // lpWindowName
                    WS_OVERLAPPEDWINDOW,    // dwStyle
                    100,                    // X
                    100,                    // Y
                    width,                  // nWidth
                    height,                 // nHeight
                    MemorySegment.NULL,     // hWndParent
                    MemorySegment.NULL,     // hMenu
                    hInstance,              // hInstance
                    MemorySegment.NULL,     // lpParam
                ) as MemorySegment
            }

            if (hwnd == MemorySegment.NULL) return null

            val window = Win32Window(hwnd, hInstance, attrs)

            // Affichage initial
            if (attrs.visible) {
                showWindow?.invokeExact(hwnd, SW_SHOW)
                updateWindow?.invokeExact(hwnd)
            }

            return window
        }
    }
}
