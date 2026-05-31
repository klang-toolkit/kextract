/**
 * Win32 implementation of the [Window] interface for Windows Desktop.
 *
 * Uses the Foreign Function & Memory API (JEP 454, JDK 25) to interact
 * with user32.dll and kernel32.dll without JNA or any other intermediate layer.
 *
 * Creation flow:
 *  1. [companion.registerClassOnce] → RegisterClassExW (run only once)
 *  2. [createWindow]                → CreateWindowExW
 *  3. ShowWindow / UpdateWindow     → initial display
 *
 * GRA-141: Win32Window — complete implementation of the Window interface.
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
 * Native Win32 window implementing [Window].
 *
 * The constructor is private: use [Win32Window.create] to instantiate.
 *
 * @param hwnd      Native window handle (HWND), represented by a MemorySegment.
 * @param hInstance Handle of the current module (HINSTANCE), represented by a MemorySegment.
 * @param attrs     Window creation attributes.
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
     * Redraw flag — set by [requestRedraw], consumed by the message loop.
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
     * Inner size (render surface) in physical pixels.
     *
     * On Windows, returns the size configured in the attributes,
     * or a default of 800 × 600 if not specified.
     *
     * TODO GRA-142: use GetClientRect to read the real size.
     */
    override val innerSize: PhysicalSize<Int>
        get() = attrs.size ?: PhysicalSize(800, 600)

    /**
     * Outer size (surface + decorations) in physical pixels.
     *
     * TODO GRA-142: use GetWindowRect to read the real size.
     */
    override val outerSize: PhysicalSize<Int>
        get() = attrs.size ?: PhysicalSize(800, 600)

    /**
     * DPI scale factor of this window.
     *
     * Calls GetDpiForWindow(hwnd) and divides by 96 (Windows reference logical
     * DPI). Returns 1.0 when unavailable (non-Windows or invalid window)
     * for graceful cross-platform behavior.
     *
     * GRA-12: DPI awareness PerMonitorV2.
     */
    override val scaleFactor: Double
        get() = try {
            // GetDpiForWindow returns UINT; capture as Int (an `as? Int` would make invokeExact
            // expect an Object return and throw WrongMethodTypeException, silently forcing 1.0).
            val dpi = getDpiForWindow?.let { it.invokeExact(hwnd) as Int } ?: 0
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

        /** Name of the registered Win32 window class. */
        private const val CLASS_NAME = "KadreWin32Window"

        /**
         * Atomic guard for window class registration.
         *
         * RegisterClassExW must be called only once per process
         * for a given class name.
         */
        private val classRegistered = AtomicBoolean(false)

        /**
         * Upcall stub for the WndProc — must stay alive as long
         * as windows of this class exist.
         *
         * Allocated in [Win32WndProcArena.arena] (Arena.ofShared).
         */
        @Volatile
        private var wndProcStub: MemorySegment? = null

        /**
         * Registers the Win32 window class only once.
         *
         * Thread-safe via [classRegistered] (AtomicBoolean compare-and-set).
         * No-op on macOS/Linux (the MethodHandles are null).
         *
         * @param hInstance Handle of the current module.
         * @param classNamePtr Wide-string pointer to the class name.
         */
        private fun registerClassOnce(hInstance: MemorySegment, classNamePtr: MemorySegment) {
            if (!classRegistered.compareAndSet(false, true)) return

            val registerHandle = registerClassExW ?: return

            // Create the upcall stub for the WndProc
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

            // Allocate and fill the WNDCLASSEXW structure in a temporary arena
            Arena.ofConfined().use { arena ->
                val wndClass = WndClassExW(arena)
                wndClass.cbSize = WndClassExW.SIZEOF
                wndClass.style = CS_HREDRAW_VREDRAW
                wndClass.lpfnWndProc = stub
                wndClass.cbClsExtra = 0
                wndClass.cbWndExtra = 0
                wndClass.hInstance = hInstance
                wndClass.hIcon = MemorySegment.NULL
                // Give the class the standard arrow cursor (IDC_ARROW). Without it, Windows never
                // resets the cursor over the client area, leaving the resize cursor "stuck" from
                // the window border. MAKEINTRESOURCE(IDC_ARROW=32512) is encoded as a small address.
                wndClass.hCursor = loadCursorW
                    ?.let { it.invokeExact(MemorySegment.NULL, MemorySegment.ofAddress(IDC_ARROW)) as MemorySegment }
                    ?: MemorySegment.NULL
                wndClass.hbrBackground = MemorySegment.NULL
                wndClass.lpszMenuName = MemorySegment.NULL
                wndClass.lpszClassName = classNamePtr
                wndClass.hIconSm = MemorySegment.NULL

                val atom = registerHandle.invokeExact(wndClass.segment) as Short
                if (atom.toInt() == 0) {
                    // Reset to allow a future attempt
                    classRegistered.set(false)
                    wndProcStub = null
                    error("RegisterClassExW a échoué (atom = 0)")
                }
            }
        }

        /**
         * Win32 window procedure (WndProc).
         *
         * Called by the Windows system for each message sent to a window
         * of the KadreWin32Window class. Delegates the entire dispatch to
         * [KadreWndProc.dispatch] which translates the Win32 messages into kadre
         * [WindowEvent]s and forwards them to the installed handler.
         *
         * ⚠️ This method is called from the Win32 message thread —
         * it must be @JvmStatic so that MethodHandles.lookup() can find it.
         */
        @JvmStatic
        fun wndProc(hwnd: MemorySegment, msg: Int, wParam: Long, lParam: Long): Long {
            return KadreWndProc.dispatch(hwnd.address(), msg, wParam, lParam)
        }

        /**
         * Creates a native Win32 window.
         *
         * Registers the window class if necessary, then calls
         * CreateWindowExW to create the native window.
         *
         * @param attrs Window attributes (title, size, visibility, etc.).
         * @return The created window, or null if the Win32 bindings are not available
         *         (macOS/Linux) or if creation fails.
         */
        fun create(attrs: WindowAttributes): Win32Window? {
            // Check the availability of the Win32 bindings
            val createHandle = createWindowExW ?: return null
            val getModuleHandle = getModuleHandleW ?: return null

            // Get the handle of the current module (GetModuleHandleW(NULL))
            val hInstance = getModuleHandle.invokeExact(MemorySegment.NULL) as MemorySegment
            if (hInstance == MemorySegment.NULL) return null

            // Allocate the class name in a long-lived arena
            // (must stay valid for the entire lifetime of the windows of this class)
            val classArena = Win32WndProcArena.arena
            val classNamePtr = classArena.allocateWString(CLASS_NAME)

            // Register the class only once
            registerClassOnce(hInstance, classNamePtr)

            // Create the window
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

            // Initial display.
            // ShowWindow/UpdateWindow return BOOL (int) — invokeExact requires the exact
            // return type, so the result must be captured (as Int) or it throws
            // WrongMethodTypeException ("…)int but found …)void").
            if (attrs.visible) {
                @Suppress("UNUSED_EXPRESSION")
                showWindow?.let { it.invokeExact(hwnd, SW_SHOW) as Int }
                updateWindow?.let { it.invokeExact(hwnd) as Int }
            }

            return window
        }
    }
}
