/**
 * FFM bindings for the Win32 functions required for window management.
 *
 * Loads user32.dll and kernel32.dll via SymbolLookup.libraryLookup with a
 * tryCreate pattern (try/catch Throwable) so the build passes on macOS/Linux.
 *
 * Exposed functions:
 *  - RegisterClassExW  (user32)
 *  - CreateWindowExW   (user32)
 *  - ShowWindow        (user32)
 *  - UpdateWindow      (user32)
 *  - DestroyWindow     (user32)
 *  - DefWindowProcW    (user32)
 *  - SetWindowTextW    (user32)
 *  - PostQuitMessage   (user32)
 *  - GetKeyState       (user32)
 *  - GetModuleHandleW  (kernel32)
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/learnwin32/
 */
package org.graphiks.kadre.win32

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

// ── Lazy loading of the libraries ─────────────────────────────────────────────

/**
 * Lookup of user32.dll — null on non-Windows platforms.
 *
 * The try/catch on Throwable is intentional: SymbolLookup.libraryLookup
 * may throw IllegalArgumentException or UnsatisfiedLinkError on macOS/Linux,
 * and we want the build to stay green in all cases.
 */
internal val user32: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("user32.dll", Arena.global())
    } catch (e: Throwable) {
        null
    }
}

/**
 * Lookup of kernel32.dll — null on non-Windows platforms.
 */
internal val kernel32: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("kernel32.dll", Arena.global())
    } catch (e: Throwable) {
        null
    }
}

/**
 * Lookup of gdi32.dll — null on non-Windows platforms.
 */
internal val gdi32: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("gdi32.dll", Arena.global())
    } catch (e: Throwable) {
        null
    }
}

private val linker: Linker = Linker.nativeLinker()

// ── Helpers ───────────────────────────────────────────────────────────────────

/**
 * Looks up a symbol in a SymbolLookup and creates a downcall MethodHandle.
 * Returns null if the lookup is null or if the symbol cannot be found.
 */
private fun SymbolLookup?.downcall(name: String, desc: FunctionDescriptor): MethodHandle? {
    this ?: return null
    return this.find(name).map { linker.downcallHandle(it, desc) }.orElse(null)
}

// ── Thread helpers ───────────────────────────────────────────────────────────

/**
 * DWORD GetCurrentThreadId(void);
 *
 * Used to guard thread-affine Win32 requests such as ReleaseCapture.
 */
internal val getCurrentThreadIdHandle: MethodHandle? by lazy {
    kernel32.downcall(
        "GetCurrentThreadId",
        FunctionDescriptor.of(ValueLayout.JAVA_INT)
    )
}

internal fun currentWin32ThreadId(): Int =
    try {
        val handle = getCurrentThreadIdHandle ?: return 0
        handle.invokeExact() as Int
    } catch (_: Throwable) {
        0
    }

// ── RegisterClassExW ──────────────────────────────────────────────────────────

/**
 * ATOM RegisterClassExW(const WNDCLASSEXW *lpwcx);
 *
 * Registers a Win32 window class. Takes a pointer to WNDCLASSEXW,
 * returns an ATOM (Short): non-zero on success.
 */
internal val registerClassExW: MethodHandle? by lazy {
    user32.downcall(
        "RegisterClassExW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_SHORT,  // ATOM (WORD = unsigned short)
            ValueLayout.ADDRESS,     // const WNDCLASSEXW*
        )
    )
}

// ── CreateWindowExW ───────────────────────────────────────────────────────────

/**
 * HWND CreateWindowExW(
 *     DWORD     dwExStyle,
 *     LPCWSTR   lpClassName,
 *     LPCWSTR   lpWindowName,
 *     DWORD     dwStyle,
 *     int       X,
 *     int       Y,
 *     int       nWidth,
 *     int       nHeight,
 *     HWND      hWndParent,
 *     HMENU     hMenu,
 *     HINSTANCE hInstance,
 *     LPVOID    lpParam
 * );
 */
internal val createWindowExW: MethodHandle? by lazy {
    user32.downcall(
        "CreateWindowExW",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // HWND return
            ValueLayout.JAVA_INT,   // dwExStyle (DWORD → int in C on Win64)
            ValueLayout.ADDRESS,    // lpClassName (LPCWSTR)
            ValueLayout.ADDRESS,    // lpWindowName (LPCWSTR)
            ValueLayout.JAVA_INT,   // dwStyle (DWORD)
            ValueLayout.JAVA_INT,   // X
            ValueLayout.JAVA_INT,   // Y
            ValueLayout.JAVA_INT,   // nWidth
            ValueLayout.JAVA_INT,   // nHeight
            ValueLayout.ADDRESS,    // hWndParent (HWND)
            ValueLayout.ADDRESS,    // hMenu (HMENU)
            ValueLayout.ADDRESS,    // hInstance (HINSTANCE)
            ValueLayout.ADDRESS,    // lpParam (LPVOID)
        )
    )
}

// ── ShowWindow ────────────────────────────────────────────────────────────────

/**
 * BOOL ShowWindow(HWND hWnd, int nCmdShow);
 */
internal val showWindow: MethodHandle? by lazy {
    user32.downcall(
        "ShowWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // nCmdShow
        )
    )
}

// ── UpdateWindow ──────────────────────────────────────────────────────────────

/**
 * BOOL UpdateWindow(HWND hWnd);
 */
internal val updateWindow: MethodHandle? by lazy {
    user32.downcall(
        "UpdateWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

// ── DestroyWindow ─────────────────────────────────────────────────────────────

/**
 * BOOL DestroyWindow(HWND hWnd);
 */
internal val destroyWindow: MethodHandle? by lazy {
    user32.downcall(
        "DestroyWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

// ── DefWindowProcW ────────────────────────────────────────────────────────────

/**
 * LRESULT DefWindowProcW(HWND hWnd, UINT Msg, WPARAM wParam, LPARAM lParam);
 */
internal val defWindowProcW: MethodHandle? by lazy {
    user32.downcall(
        "DefWindowProcW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // LRESULT
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // UINT (message)
            ValueLayout.JAVA_LONG,  // WPARAM
            ValueLayout.JAVA_LONG,  // LPARAM
        )
    )
}

// ── SetWindowTextW ────────────────────────────────────────────────────────────

/**
 * BOOL SetWindowTextW(HWND hWnd, LPCWSTR lpString);
 */
internal val setWindowTextW: MethodHandle? by lazy {
    user32.downcall(
        "SetWindowTextW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.ADDRESS,    // LPCWSTR
        )
    )
}

// ── PostQuitMessage ───────────────────────────────────────────────────────────

/**
 * void PostQuitMessage(int nExitCode);
 *
 * Places a WM_QUIT message in the current thread's message queue, which
 * causes the GetMessage loop to exit.
 */
internal val postQuitMessage: MethodHandle? by lazy {
    user32.downcall(
        "PostQuitMessage",
        FunctionDescriptor.ofVoid(
            ValueLayout.JAVA_INT,   // nExitCode
        )
    )
}

// ── GetKeyState ───────────────────────────────────────────────────────────────

/**
 * SHORT GetKeyState(int nVirtKey);
 *
 * Returns the state of a virtual key at the moment the last message
 * extracted by GetMessage was processed. Bit 15 = key down, bit 0 = toggle.
 */
internal val getKeyState: MethodHandle? by lazy {
    user32.downcall(
        "GetKeyState",
        FunctionDescriptor.of(
            ValueLayout.JAVA_SHORT, // SHORT
            ValueLayout.JAVA_INT,   // nVirtKey
        )
    )
}

// ── PeekMessageW ─────────────────────────────────────────────────────────────

/**
 * BOOL PeekMessageW(LPMSG lpMsg, HWND hWnd, UINT wMsgFilterMin, UINT wMsgFilterMax, UINT wRemoveMsg);
 *
 * Checks whether a message is available in the queue and, if PM_REMOVE is specified,
 * removes it. Returns non-zero if a message is available, 0 otherwise.
 * Non-blocking — returns immediately.
 */
internal val peekMessageW: MethodHandle? by lazy {
    user32.downcall(
        "PeekMessageW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // LPMSG lpMsg
            ValueLayout.ADDRESS,    // HWND hWnd (NULL = all thread messages)
            ValueLayout.JAVA_INT,   // UINT wMsgFilterMin
            ValueLayout.JAVA_INT,   // UINT wMsgFilterMax
            ValueLayout.JAVA_INT,   // UINT wRemoveMsg
        )
    )
}

// ── GetMessageW ──────────────────────────────────────────────────────────────

/**
 * BOOL GetMessageW(LPMSG lpMsg, HWND hWnd, UINT wMsgFilterMin, UINT wMsgFilterMax);
 *
 * Extracts a message from the thread's message queue. Blocking — waits until
 * a message is available.
 * Returns > 0 if message, 0 if WM_QUIT, -1 on error.
 */
internal val getMessageW: MethodHandle? by lazy {
    user32.downcall(
        "GetMessageW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // LPMSG lpMsg
            ValueLayout.ADDRESS,    // HWND hWnd (NULL = all thread messages)
            ValueLayout.JAVA_INT,   // UINT wMsgFilterMin
            ValueLayout.JAVA_INT,   // UINT wMsgFilterMax
        )
    )
}

// ── TranslateMessage ──────────────────────────────────────────────────────────

/**
 * BOOL TranslateMessage(const MSG *lpMsg);
 *
 * Translates virtual-key messages into character messages (WM_CHAR).
 * Must be called before DispatchMessageW in the message loop.
 */
internal val translateMessage: MethodHandle? by lazy {
    user32.downcall(
        "TranslateMessage",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // const MSG*
        )
    )
}

// ── DispatchMessageW ──────────────────────────────────────────────────────────

/**
 * LRESULT DispatchMessageW(const MSG *lpMsg);
 *
 * Dispatches a message to the window procedure (WndProc).
 */
internal val dispatchMessageW: MethodHandle? by lazy {
    user32.downcall(
        "DispatchMessageW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // LRESULT
            ValueLayout.ADDRESS,    // const MSG*
        )
    )
}

// ── MsgWaitForMultipleObjectsEx ───────────────────────────────────────────────

/**
 * DWORD MsgWaitForMultipleObjectsEx(DWORD nCount, const HANDLE *pHandles,
 *     DWORD dwMilliseconds, DWORD dwWakeMask, DWORD dwFlags);
 *
 * Waits until a message arrives or the timeout expires.
 */
internal val msgWaitForMultipleObjectsEx: MethodHandle? by lazy {
    user32.downcall(
        "MsgWaitForMultipleObjectsEx",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // DWORD (WAIT_*)
            ValueLayout.JAVA_INT,   // DWORD nCount
            ValueLayout.ADDRESS,    // const HANDLE* pHandles
            ValueLayout.JAVA_INT,   // DWORD dwMilliseconds
            ValueLayout.JAVA_INT,   // DWORD dwWakeMask
            ValueLayout.JAVA_INT,   // DWORD dwFlags
        )
    )
}

// ── GetModuleHandleW ──────────────────────────────────────────────────────────

/**
 * HMODULE GetModuleHandleW(LPCWSTR lpModuleName);
 *
 * Pass NULL to get the handle of the current module.
 */
internal val getModuleHandleW: MethodHandle? by lazy {
    kernel32.downcall(
        "GetModuleHandleW",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // HMODULE
            ValueLayout.ADDRESS,    // LPCWSTR (ou NULL)
        )
    )
}

// ── LoadCursorW ───────────────────────────────────────────────────────────────

/**
 * HCURSOR LoadCursorW(HINSTANCE hInstance, LPCWSTR lpCursorName);
 *
 * With hInstance = NULL and a predefined cursor id (e.g. IDC_ARROW via MAKEINTRESOURCE),
 * loads a system cursor. Used to give the window class a real cursor so the client area
 * shows the arrow instead of keeping the sizing cursor from the window border.
 */
internal val loadCursorW: MethodHandle? by lazy {
    user32.downcall(
        "LoadCursorW",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // HCURSOR
            ValueLayout.ADDRESS,    // HINSTANCE (NULL for system cursors)
            ValueLayout.ADDRESS,    // LPCWSTR  (MAKEINTRESOURCE id)
        )
    )
}

// ── SetProcessDpiAwarenessContext ─────────────────────────────────────────────

/**
 * BOOL SetProcessDpiAwarenessContext(DPI_AWARENESS_CONTEXT value);
 *
 * Sets the process DPI awareness mode. To be called before any
 * window creation. Available since Windows 10 RS1 (1607, build 14393).
 *
 * DPI_AWARENESS_CONTEXT is a pseudo-handle: a negative integer constant
 * passed as ADDRESS (pointer-sized) on Win64.
 *
 * Useful constant:
 *   DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2 = -4
 */
internal val setProcessDpiAwarenessContext: MethodHandle? by lazy {
    user32.downcall(
        "SetProcessDpiAwarenessContext",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // DPI_AWARENESS_CONTEXT (HANDLE, pointer-sized)
        )
    )
}

/** DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2 — Win32 pseudo-handle value. */
internal const val DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2: Long = -4L

/**
 * Enables Per-Monitor-V2 DPI awareness mode for the process.
 *
 * To be called only once at EventLoop startup, before any window
 * creation. Idempotent in practice: if DPI awareness is already set
 * (by the manifest or a previous call), Windows returns FALSE and sets
 * `ERROR_ACCESS_DENIED` — which is silently ignored.
 *
 * No-op if the binding is not available (Windows < 10 RS1) or
 * on a non-Windows platform where the lookup fails.
 *
 * @return `true` if the call succeeded, `false` otherwise (already set, unavailable…).
 */
internal fun enablePerMonitorV2DpiAwareness(): Boolean {
    val handle = setProcessDpiAwarenessContext ?: return false
    return try {
        val context = MemorySegment.ofAddress(DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2)
        (handle.invokeExact(context) as Int) != 0
    } catch (_: Throwable) {
        false
    }
}

// ── GetDpiForWindow ───────────────────────────────────────────────────────────

/**
 * UINT GetDpiForWindow(HWND hwnd);
 *
 * Returns the effective DPI of the window. Available since Windows 10 RS1.
 * Returns 0 if hwnd is invalid.
 */
internal val getDpiForWindow: MethodHandle? by lazy {
    user32.downcall(
        "GetDpiForWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // UINT
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

// ── TrackMouseEvent ───────────────────────────────────────────────────────────

/**
 * BOOL TrackMouseEvent(LPTRACKMOUSEEVENT lpEventTrack);
 *
 * Arms the receipt of WM_MOUSELEAVE / WM_MOUSEHOVER for the specified window.
 * Must be re-armed after each WM_MOUSELEAVE received.
 *
 * TRACKMOUSEEVENT layout (48 bytes on Win64):
 *   DWORD cbSize     (offset 0,  4 bytes)
 *   DWORD dwFlags    (offset 4,  4 bytes)
 *   HWND  hwndTrack  (offset 8,  8 bytes)
 *   DWORD dwHoverTime (offset 16, 4 bytes)
 *   [4 bytes padding for 8-byte alignment]
 */
internal val trackMouseEvent: MethodHandle? by lazy {
    user32.downcall(
        "TrackMouseEvent",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // LPTRACKMOUSEEVENT
        )
    )
}

/** Size of the TRACKMOUSEEVENT structure in bytes (DWORD+DWORD+HWND+DWORD+padding). */
internal const val TRACKMOUSEEVENT_SIZE: Int = 24 // 4+4+8+4+4 (with padding)

/** TME_LEAVE: dwFlags flag to receive WM_MOUSELEAVE. */
internal const val TME_LEAVE: Int = 0x00000002

// ── RegisterTouchWindow ───────────────────────────────────────────────────────

/**
 * BOOL RegisterTouchWindow(HWND hWnd, ULONG ulFlags);
 *
 * Registers a window to receive WM_TOUCH messages instead of the legacy
 * WM_GESTURE / mouse-emulation messages. Must be called once after the
 * window is created. ulFlags = 0 for the default behavior.
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-registertouchwindow
 */
internal val registerTouchWindow: MethodHandle? by lazy {
    user32.downcall(
        "RegisterTouchWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // ULONG ulFlags
        )
    )
}

// ── GetTouchInputInfo ─────────────────────────────────────────────────────────

/**
 * BOOL GetTouchInputInfo(HTOUCHINPUT hTouchInput, UINT cInputs, PTOUCHINPUT pInputs, int cbSize);
 *
 * Fills [pInputs] with [cInputs] TOUCHINPUT structures describing the contacts
 * of the current WM_TOUCH message. `hTouchInput` is the WM_TOUCH `lParam`.
 * `cbSize` is the size of a single TOUCHINPUT ([TOUCHINPUT_SIZE]).
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-gettouchinputinfo
 */
internal val getTouchInputInfo: MethodHandle? by lazy {
    user32.downcall(
        "GetTouchInputInfo",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HTOUCHINPUT
            ValueLayout.JAVA_INT,   // UINT cInputs
            ValueLayout.ADDRESS,    // PTOUCHINPUT pInputs
            ValueLayout.JAVA_INT,   // int cbSize
        )
    )
}

// ── CloseTouchInputHandle ─────────────────────────────────────────────────────

/**
 * BOOL CloseTouchInputHandle(HTOUCHINPUT hTouchInput);
 *
 * Releases the touch-input handle obtained from a WM_TOUCH message. Must be
 * called exactly once per WM_TOUCH after GetTouchInputInfo, otherwise the
 * handle leaks.
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-closetouchinputhandle
 */
internal val closeTouchInputHandle: MethodHandle? by lazy {
    user32.downcall(
        "CloseTouchInputHandle",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HTOUCHINPUT
        )
    )
}

// ── ScreenToClient ────────────────────────────────────────────────────────────

/**
 * BOOL ScreenToClient(HWND hWnd, LPPOINT lpPoint);
 *
 * Converts a point from screen coordinates to client-area coordinates for the
 * specified window. Used for WM_TOUCH because TOUCHINPUT.x/y are screen
 * coordinates while kadre pointer positions are client coordinates.
 *
 * POINT layout: { LONG x, LONG y } = 8 bytes.
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-screentoclient
 */
internal val screenToClient: MethodHandle? by lazy {
    user32.downcall(
        "ScreenToClient",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.ADDRESS,    // LPPOINT
        )
    )
}

/**
 * BOOL ClientToScreen(HWND hWnd, LPPOINT lpPoint);
 *
 * Converts a point from client-area coordinates to screen coordinates.
 */
internal val clientToScreen: MethodHandle? by lazy {
    user32.downcall(
        "ClientToScreen",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.ADDRESS,    // LPPOINT
        )
    )
}

// ── GetCursorPos ──────────────────────────────────────────────────────────────

/**
 * BOOL GetCursorPos(LPPOINT lpPoint);
 *
 * Returns the current cursor position in screen coordinates.
 * POINT = {LONG x, LONG y} = 8 bytes.
 */
internal val getCursorPos: MethodHandle? by lazy {
    user32.downcall(
        "GetCursorPos",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // LPPOINT
        )
    )
}

// ── Window system menu / interactive move-resize ─────────────────────────────

/**
 * HMENU GetSystemMenu(HWND hWnd, BOOL bRevert);
 *
 * Returns the system menu associated with the window.
 */
internal val getSystemMenu: MethodHandle? by lazy {
    user32.downcall(
        "GetSystemMenu",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // HMENU
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // BOOL bRevert
        )
    )
}

/**
 * BOOL TrackPopupMenu(HMENU hMenu, UINT uFlags, int x, int y, int nReserved, HWND hWnd, const RECT *prcRect);
 *
 * With TPM_RETURNCMD, returns the selected command id instead of posting it.
 */
internal val trackPopupMenu: MethodHandle? by lazy {
    user32.downcall(
        "TrackPopupMenu",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL or command id with TPM_RETURNCMD
            ValueLayout.ADDRESS,    // HMENU
            ValueLayout.JAVA_INT,   // UINT uFlags
            ValueLayout.JAVA_INT,   // x
            ValueLayout.JAVA_INT,   // y
            ValueLayout.JAVA_INT,   // nReserved
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.ADDRESS,    // const RECT*
        )
    )
}

/**
 * BOOL EnableMenuItem(HMENU hMenu, UINT uIDEnableItem, UINT uEnable);
 *
 * Enables/disables system-menu commands before TrackPopupMenu.
 */
internal val enableMenuItem: MethodHandle? by lazy {
    user32.downcall(
        "EnableMenuItem",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL / previous item state
            ValueLayout.ADDRESS,    // HMENU
            ValueLayout.JAVA_INT,   // UINT uIDEnableItem
            ValueLayout.JAVA_INT,   // UINT uEnable
        )
    )
}

/**
 * BOOL SetMenuDefaultItem(HMENU hMenu, UINT uItem, UINT fByPos);
 */
internal val setMenuDefaultItem: MethodHandle? by lazy {
    user32.downcall(
        "SetMenuDefaultItem",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HMENU
            ValueLayout.JAVA_INT,   // UINT uItem
            ValueLayout.JAVA_INT,   // UINT fByPos
        )
    )
}

/**
 * BOOL ReleaseCapture(void);
 *
 * Releases mouse capture before asking the non-client area to start a move/resize drag.
 */
internal val releaseCapture: MethodHandle? by lazy {
    user32.downcall(
        "ReleaseCapture",
        FunctionDescriptor.of(ValueLayout.JAVA_INT)
    )
}

// ── Wide String encoding helpers ──────────────────────────────────────────────

/**
 * Allocates a Wide string (UTF-16 LE, null-terminated) in the given arena.
 *
 * Each Java character (UTF-16) is written directly — characters outside
 * the BMP are not supported (sufficient for window titles).
 */
internal fun Arena.allocateWString(value: String): MemorySegment {
    // 2 bytes per character + 2 bytes for the null terminator
    val seg = this.allocate((value.length + 1) * 2L, 2L)
    for (i in value.indices) {
        seg.setAtIndex(ValueLayout.JAVA_SHORT, i.toLong(), value[i].code.toShort())
    }
    // null terminator (already 0 by default, but written explicitly)
    seg.setAtIndex(ValueLayout.JAVA_SHORT, value.length.toLong(), 0)
    return seg
}

// ── Win32 constants ───────────────────────────────────────────────────────────

/** WS_OVERLAPPEDWINDOW = WS_OVERLAPPED|WS_CAPTION|WS_SYSMENU|WS_THICKFRAME|WS_MINIMIZEBOX|WS_MAXIMIZEBOX */
@Suppress("INTEGER_OVERFLOW")
internal const val WS_OVERLAPPEDWINDOW: Int = 0x00CF0000

/** WS_EX_APPWINDOW — button in the taskbar */
internal const val WS_EX_APPWINDOW: Int = 0x00040000

/** SW_SHOW */
internal const val SW_SHOW: Int = 5

/** SW_HIDE */
internal const val SW_HIDE: Int = 0

/** IDC_ARROW — standard arrow cursor id (passed to LoadCursorW via MAKEINTRESOURCE). */
internal const val IDC_ARROW: Long = 32512L

/** CS_HREDRAW | CS_VREDRAW */
internal const val CS_HREDRAW_VREDRAW: Int = 0x0003

/** WM_DESTROY */
internal const val WM_DESTROY: Int = 0x0002

/** WM_NCLBUTTONDOWN — non-client left-button press used to start system move/resize. */
internal const val WM_NCLBUTTONDOWN: Int = 0x00A1

/** WM_SYSCOMMAND — dispatch selected system-menu commands. */
internal const val WM_SYSCOMMAND: Int = 0x0112

/** WM_APP base for application-private messages. */
internal const val WM_APP: Int = 0x8000

/** Kadre-private request: run native non-client move/resize drag on the HWND owner thread. */
internal const val WM_KADRE_NON_CLIENT_DRAG: Int = WM_APP + 0x4D1

/** TrackPopupMenu: return selected command id. */
internal const val TPM_RETURNCMD: Int = 0x0100

/** TrackPopupMenu: align menu left-to-right, matching the current winit Win32 path. */
internal const val TPM_LEFTALIGN: Int = 0x0000

/** System-menu command ids. */
internal const val SC_SIZE: Int = 0xF000
internal const val SC_MOVE: Int = 0xF010
internal const val SC_MINIMIZE: Int = 0xF020
internal const val SC_MAXIMIZE: Int = 0xF030
internal const val SC_CLOSE: Int = 0xF060
internal const val SC_RESTORE: Int = 0xF120

/** EnableMenuItem flags. */
internal const val MF_BYCOMMAND: Int = 0x0000
internal const val MF_ENABLED: Int = 0x0000
internal const val MF_DISABLED: Int = 0x0002
internal const val MFS_ENABLED: Int = 0x0000
internal const val MFS_DISABLED: Int = 0x0003

/** Hit-test code for title-bar move drag. */
internal const val HTCAPTION: Long = 2L

/** Hit-test codes for window resize borders/corners. */
internal const val HTLEFT: Long = 10L
internal const val HTRIGHT: Long = 11L
internal const val HTTOP: Long = 12L
internal const val HTTOPLEFT: Long = 13L
internal const val HTTOPRIGHT: Long = 14L
internal const val HTBOTTOM: Long = 15L
internal const val HTBOTTOMLEFT: Long = 16L
internal const val HTBOTTOMRIGHT: Long = 17L

// ── GetClientRect ─────────────────────────────────────────────────────────────

/**
 * BOOL GetClientRect(HWND hWnd, LPRECT lpRect);
 *
 * Fills [lpRect] with the coordinates of the client area (rendering surface)
 * of the window in client coordinates (left/top are always 0).
 * right = width, bottom = height — no decorations included.
 *
 * RECT layout (16 bytes): {LONG left, LONG top, LONG right, LONG bottom}
 */
internal val getClientRect: MethodHandle? by lazy {
    user32.downcall(
        "GetClientRect",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.ADDRESS,    // LPRECT
        )
    )
}

// ── GetWindowRect ─────────────────────────────────────────────────────────────

/**
 * BOOL GetWindowRect(HWND hWnd, LPRECT lpRect);
 *
 * Fills [lpRect] with the bounding rectangle of the window in screen
 * coordinates — includes title bar, borders, and other decorations.
 * width  = right  - left
 * height = bottom - top
 *
 * RECT layout (16 bytes): {LONG left, LONG top, LONG right, LONG bottom}
 */
internal val getWindowRect: MethodHandle? by lazy {
    user32.downcall(
        "GetWindowRect",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.ADDRESS,    // LPRECT
        )
    )
}

/** Size of the RECT structure in bytes (4 × LONG = 4 × 4 bytes). */
internal const val RECT_SIZE: Long = 16L

// ── SetWindowLongPtrW ─────────────────────────────────────────────────────────

/**
 * LONG_PTR SetWindowLongPtrW(HWND hWnd, int nIndex, LONG_PTR dwNewLong);
 *
 * Sets a value in the extra window information. Used to change the window style
 * (GWL_STYLE / GWL_EXSTYLE) and to set the resizable flag (WS_THICKFRAME).
 */
internal val setWindowLongPtrW: MethodHandle? by lazy {
    user32.downcall(
        "SetWindowLongPtrW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // LONG_PTR (return)
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // int nIndex
            ValueLayout.JAVA_LONG,  // LONG_PTR dwNewLong
        )
    )
}

// ── GetWindowLongPtrW ─────────────────────────────────────────────────────────

/**
 * LONG_PTR GetWindowLongPtrW(HWND hWnd, int nIndex);
 *
 * Retrieves information about the specified window.
 * nIndex = GWL_STYLE (-16) to get the window style flags.
 */
internal val getWindowLongPtrW: MethodHandle? by lazy {
    user32.downcall(
        "GetWindowLongPtrW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // LONG_PTR (return)
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // int nIndex
        )
    )
}

// ── Last-error helpers ───────────────────────────────────────────────────────

/**
 * void SetLastError(DWORD dwErrCode);
 */
internal val setLastError: MethodHandle? by lazy {
    kernel32.downcall(
        "SetLastError",
        FunctionDescriptor.ofVoid(
            ValueLayout.JAVA_INT, // DWORD
        )
    )
}

/**
 * DWORD GetLastError(void);
 */
internal val getLastError: MethodHandle? by lazy {
    kernel32.downcall(
        "GetLastError",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // DWORD
        )
    )
}

// ── IsZoomed ──────────────────────────────────────────────────────────────────

/**
 * BOOL IsZoomed(HWND hWnd);
 *
 * Returns non-zero if the window is maximized.
 */
internal val isZoomed: MethodHandle? by lazy {
    user32.downcall(
        "IsZoomed",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

// ── IsIconic ──────────────────────────────────────────────────────────────────

/**
 * BOOL IsIconic(HWND hWnd);
 *
 * Returns non-zero if the window is minimized (iconic).
 */
internal val isIconic: MethodHandle? by lazy {
    user32.downcall(
        "IsIconic",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

// ── IsWindowVisible ───────────────────────────────────────────────────────────

/**
 * BOOL IsWindowVisible(HWND hWnd);
 *
 * Returns non-zero if the window is visible (WS_VISIBLE style flag is set).
 */
internal val isWindowVisible: MethodHandle? by lazy {
    user32.downcall(
        "IsWindowVisible",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

// ── GetWindowTextW ────────────────────────────────────────────────────────────

/**
 * int GetWindowTextW(HWND hWnd, LPWSTR lpString, int nMaxCount);
 *
 * Copies the text of the specified window's title bar into a buffer.
 * Returns the number of characters copied (0 on failure).
 */
internal val getWindowTextW: MethodHandle? by lazy {
    user32.downcall(
        "GetWindowTextW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int (char count)
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.ADDRESS,    // LPWSTR lpString
            ValueLayout.JAVA_INT,   // int nMaxCount
        )
    )
}

// ── SetWindowPos ──────────────────────────────────────────────────────────────

/**
 * BOOL SetWindowPos(HWND hWnd, HWND hWndInsertAfter, int X, int Y, int cx, int cy, UINT uFlags);
 *
 * Changes the size, position, and Z order of a child, pop-up, or top-level window.
 * Pass SWP_NOSIZE to move without resizing; SWP_NOZORDER to keep the Z order.
 */
internal val setWindowPos: MethodHandle? by lazy {
    user32.downcall(
        "SetWindowPos",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND hWnd
            ValueLayout.ADDRESS,    // HWND hWndInsertAfter
            ValueLayout.JAVA_INT,   // int X
            ValueLayout.JAVA_INT,   // int Y
            ValueLayout.JAVA_INT,   // int cx
            ValueLayout.JAVA_INT,   // int cy
            ValueLayout.JAVA_INT,   // UINT uFlags
        )
    )
}

// ── Foreground activation helpers ────────────────────────────────────────────

/**
 * HWND GetForegroundWindow(void);
 */
internal val getForegroundWindow: MethodHandle? by lazy {
    user32.downcall(
        "GetForegroundWindow",
        FunctionDescriptor.of(ValueLayout.ADDRESS)
    )
}

/**
 * BOOL SetForegroundWindow(HWND hWnd);
 */
internal val setForegroundWindow: MethodHandle? by lazy {
    user32.downcall(
        "SetForegroundWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

/**
 * UINT MapVirtualKeyW(UINT uCode, UINT uMapType);
 */
internal val mapVirtualKeyW: MethodHandle? by lazy {
    user32.downcall(
        "MapVirtualKeyW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // UINT
            ValueLayout.JAVA_INT,   // UINT uCode
            ValueLayout.JAVA_INT,   // UINT uMapType
        )
    )
}

/**
 * UINT SendInput(UINT cInputs, LPINPUT pInputs, int cbSize);
 */
internal val sendInput: MethodHandle? by lazy {
    user32.downcall(
        "SendInput",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // UINT
            ValueLayout.JAVA_INT,   // UINT cInputs
            ValueLayout.ADDRESS,    // LPINPUT pInputs
            ValueLayout.JAVA_INT,   // int cbSize
        )
    )
}

// ── Win32 style / ShowWindow constants for R1 ────────────────────────────────

/** GWL_STYLE — window style index for Get/SetWindowLongPtrW. */
internal const val GWL_STYLE: Int = -16

/** WS_THICKFRAME — resizable border / size box. */
internal const val WS_THICKFRAME: Int = 0x00040000

/** WS_CAPTION — title bar and border (= WS_BORDER | WS_DLGFRAME). */
internal const val WS_CAPTION: Int = 0x00C00000

/** WS_BORDER — thin border. */
internal const val WS_BORDER: Int = 0x00800000

/** WS_SYSMENU — system menu in title bar. */
internal const val WS_SYSMENU: Int = 0x00080000

/** WS_MINIMIZEBOX — minimize button. */
internal const val WS_MINIMIZEBOX: Int = 0x00020000

/** WS_MAXIMIZEBOX — maximize button. */
internal const val WS_MAXIMIZEBOX: Int = 0x00010000

/** SW_MINIMIZE — minimize (iconic) state. */
internal const val SW_MINIMIZE: Int = 6

/** SW_RESTORE — restore minimized/maximized window to normal. */
internal const val SW_RESTORE: Int = 9

/** SW_MAXIMIZE — maximize the window. */
internal const val SW_MAXIMIZE: Int = 3

/** SWP_NOSIZE — retain the current size when calling SetWindowPos. */
internal const val SWP_NOSIZE: Int = 0x0001

/** SWP_NOMOVE — retain the current position when calling SetWindowPos. */
internal const val SWP_NOMOVE: Int = 0x0002

/** SWP_NOZORDER — retain the current Z order when calling SetWindowPos. */
internal const val SWP_NOZORDER: Int = 0x0004

/** SWP_NOACTIVATE — do not activate the window when moving it. */
internal const val SWP_NOACTIVATE: Int = 0x0010

/** SWP_FRAMECHANGED — apply non-client frame changes after style updates. */
internal const val SWP_FRAMECHANGED: Int = 0x0020

/** INPUT / KEYBDINPUT constants and layout for Win32 foreground activation. */
internal const val INPUT_KEYBOARD: Int = 1
internal const val INPUT_SIZE: Long = 40L
internal const val INPUT_ALIGN: Long = 8L
internal const val INPUT_OFFSET_TYPE: Long = 0L
internal const val INPUT_OFFSET_KI_WVK: Long = 8L
internal const val INPUT_OFFSET_KI_WSCAN: Long = 10L
internal const val INPUT_OFFSET_KI_DWFLAGS: Long = 12L
internal const val INPUT_OFFSET_KI_TIME: Long = 16L
internal const val INPUT_OFFSET_KI_DWEXTRAINFO: Long = 24L
internal const val MAPVK_VK_TO_VSC: Int = 0
internal const val KEYEVENTF_EXTENDEDKEY: Int = 0x0001
internal const val KEYEVENTF_KEYUP: Int = 0x0002

/** WS_VISIBLE — window is visible. */
internal const val WS_VISIBLE: Int = 0x10000000

/** HWND_TOP — place the window at the top of the Z-order (non-topmost). */
internal val HWND_TOP: MemorySegment = MemorySegment.ofAddress(0L)

/** Byte alignment of RECT (LONG = 4 bytes). */
internal const val RECT_ALIGN: Long = 4L

/** Byte offset of RECT.left */
internal const val RECT_OFFSET_LEFT: Long = 0L

/** Byte offset of RECT.top */
internal const val RECT_OFFSET_TOP: Long = 4L

/** Byte offset of RECT.right */
internal const val RECT_OFFSET_RIGHT: Long = 8L

/** Byte offset of RECT.bottom */
internal const val RECT_OFFSET_BOTTOM: Long = 12L

/** Byte size/alignment and offsets of POINT { LONG x, LONG y }. */
internal const val POINT_SIZE: Long = 8L
internal const val POINT_ALIGN: Long = 4L
internal const val POINT_OFFSET_X: Long = 0L
internal const val POINT_OFFSET_Y: Long = 4L

// ── R3 bindings ──────────────────────────────────────────────────────────────

/**
 * BOOL SetCursorPos(int X, int Y);
 *
 * Moves the cursor to the specified screen coordinates.
 */
internal val setCursorPos: MethodHandle? by lazy {
    user32.downcall(
        "SetCursorPos",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.JAVA_INT,   // X
            ValueLayout.JAVA_INT,   // Y
        )
    )
}

/**
 * HCURSOR SetCursor(HCURSOR hCursor);
 *
 * Sets the cursor shape for the current thread.
 */
internal val setCursor: MethodHandle? by lazy {
    user32.downcall(
        "SetCursor",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // HCURSOR (previous cursor)
            ValueLayout.ADDRESS,    // HCURSOR hCursor
        )
    )
}

/**
 * int ShowCursor(BOOL bShow);
 *
 * Shows or hides the cursor. Returns the display counter (>= 0 = visible).
 */
internal val showCursorHandle: MethodHandle? by lazy {
    user32.downcall(
        "ShowCursor",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int (display counter)
            ValueLayout.JAVA_INT,   // BOOL bShow
        )
    )
}

/**
 * BOOL ClipCursor(const RECT *lpRect);
 *
 * Confines the cursor to the given rectangle. Pass NULL to release.
 */
internal val clipCursor: MethodHandle? by lazy {
    user32.downcall(
        "ClipCursor",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // const RECT* (NULL to release)
        )
    )
}

/**
 * BOOL GetClientRect(HWND hWnd, LPRECT lpRect) — re-exported as getClientRectW for R3 use.
 * Already declared above as [getClientRect].
 */

/**
 * BOOL SendMessageW(HWND hWnd, UINT Msg, WPARAM wParam, LPARAM lParam);
 *
 * Used here for WM_SETICON.
 */
internal val sendMessageW: MethodHandle? by lazy {
    user32.downcall(
        "SendMessageW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // LRESULT
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // UINT Msg
            ValueLayout.JAVA_LONG,  // WPARAM
            ValueLayout.JAVA_LONG,  // LPARAM
        )
    )
}

/**
 * HICON CreateIcon(HINSTANCE hInstance, int nWidth, int nHeight, BYTE cPlanes,
 *                  BYTE cBitsPixel, const BYTE *lpbANDbits, const BYTE *lpbXORbits);
 *
 * Creates an HICON from Kadre RGBA pixels converted to Win32 BGRA pixels.
 */
internal val createIcon: MethodHandle? by lazy {
    user32.downcall(
        "CreateIcon",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // HICON
            ValueLayout.ADDRESS,    // HINSTANCE
            ValueLayout.JAVA_INT,   // int nWidth
            ValueLayout.JAVA_INT,   // int nHeight
            ValueLayout.JAVA_BYTE,  // BYTE cPlanes
            ValueLayout.JAVA_BYTE,  // BYTE cBitsPixel
            ValueLayout.ADDRESS,    // const BYTE *lpbANDbits
            ValueLayout.ADDRESS,    // const BYTE *lpbXORbits
        )
    )
}

/**
 * BOOL DestroyIcon(HICON hIcon);
 *
 * Releases HICON handles created by [createIcon].
 */
internal val destroyIcon: MethodHandle? by lazy {
    user32.downcall(
        "DestroyIcon",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HICON
        )
    )
}

/**
 * BOOL PostMessageW(HWND hWnd, UINT Msg, WPARAM wParam, LPARAM lParam);
 *
 * Queues a message without re-entering the current window procedure.
 */
internal val postMessageW: MethodHandle? by lazy {
    user32.downcall(
        "PostMessageW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // UINT Msg
            ValueLayout.JAVA_LONG,  // WPARAM
            ValueLayout.JAVA_LONG,  // LPARAM
        )
    )
}

/**
 * HRESULT DwmSetWindowAttribute(HWND hwnd, DWORD dwAttribute, LPCVOID pvAttribute, DWORD cbAttribute);
 *
 * Used for DWMWA_USE_IMMERSIVE_DARK_MODE (= 20) to apply dark mode title bar.
 * Available since Windows 11 Build 22000; silently fails on older versions.
 *
 * Risk (FFM): dwmapi.dll may not be available in all configurations; the lazy
 * lookup and try/catch guard against this.
 */
internal val dwmapi: SymbolLookup? by lazy {
    try { SymbolLookup.libraryLookup("dwmapi.dll", Arena.global()) } catch (_: Throwable) { null }
}

internal val dwmSetWindowAttribute: MethodHandle? by lazy {
    dwmapi.downcall(
        "DwmSetWindowAttribute",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // HRESULT
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // DWORD dwAttribute
            ValueLayout.ADDRESS,    // LPCVOID pvAttribute
            ValueLayout.JAVA_INT,   // DWORD cbAttribute
        )
    )
}

/**
 * HRESULT DwmEnableBlurBehindWindow(HWND hWnd, const DWM_BLURBEHIND *pBlurBehind);
 *
 * Used by winit's Win32 transparent-window creation path.
 */
internal val dwmEnableBlurBehindWindow: MethodHandle? by lazy {
    dwmapi.downcall(
        "DwmEnableBlurBehindWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // HRESULT
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.ADDRESS,    // const DWM_BLURBEHIND*
        )
    )
}

/** DWMWA_USE_IMMERSIVE_DARK_MODE — enables dark title bar (Windows 11+). */
internal const val DWMWA_USE_IMMERSIVE_DARK_MODE: Int = 20

internal const val DWM_BB_ENABLE: Int = 0x00000001
internal const val DWM_BB_BLURREGION: Int = 0x00000002

internal const val DWM_BLURBEHIND_SIZE: Long = 24L
internal const val DWM_BLURBEHIND_ALIGN: Long = 8L
internal const val DWM_BLURBEHIND_OFFSET_DW_FLAGS: Long = 0L
internal const val DWM_BLURBEHIND_OFFSET_F_ENABLE: Long = 4L
internal const val DWM_BLURBEHIND_OFFSET_H_RGN_BLUR: Long = 8L
internal const val DWM_BLURBEHIND_OFFSET_F_TRANSITION_ON_MAXIMIZED: Long = 16L

/**
 * HRGN CreateRectRgn(int x1, int y1, int x2, int y2);
 */
internal val createRectRgn: MethodHandle? by lazy {
    gdi32.downcall(
        "CreateRectRgn",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // HRGN
            ValueLayout.JAVA_INT,
            ValueLayout.JAVA_INT,
            ValueLayout.JAVA_INT,
            ValueLayout.JAVA_INT,
        )
    )
}

/**
 * BOOL DeleteObject(HGDIOBJ ho);
 */
internal val deleteObject: MethodHandle? by lazy {
    gdi32.downcall(
        "DeleteObject",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HGDIOBJ
        )
    )
}

// IDC cursor resource IDs (passed to LoadCursorW via MAKEINTRESOURCE)
internal const val IDC_WAIT: Long     = 32514L
internal const val IDC_IBEAM: Long    = 32513L
internal const val IDC_CROSS: Long    = 32515L
internal const val IDC_SIZEALL: Long  = 32646L
internal const val IDC_NO: Long       = 32648L
internal const val IDC_HAND: Long     = 32649L
internal const val IDC_APPSTARTING: Long = 32650L
internal const val IDC_SIZENS: Long   = 32645L
internal const val IDC_SIZEWE: Long   = 32644L
internal const val IDC_SIZENWSE: Long = 32642L
internal const val IDC_SIZENESW: Long = 32643L
internal const val IDC_SIZENWS: Long  = 32642L
internal const val IDC_SIZENORTH: Long = 32645L  // same as SIZENS
internal const val IDC_SIZESOUTH: Long = 32645L
internal const val IDC_SIZEEAST: Long  = 32644L
internal const val IDC_SIZEWEST: Long  = 32644L

// HWND Z-order constants
/** HWND_TOPMOST = (HWND)(LONG_PTR)-1 */
internal val HWND_TOPMOST: MemorySegment = MemorySegment.ofAddress(-1L)
/** HWND_NOTOPMOST = (HWND)(LONG_PTR)-2 */
internal val HWND_NOTOPMOST: MemorySegment = MemorySegment.ofAddress(-2L)
/** HWND_BOTTOM = (HWND)(LONG_PTR)1 */
internal val HWND_BOTTOM: MemorySegment = MemorySegment.ofAddress(1L)

/** WM_SETICON message. */
internal const val WM_SETICON: Int = 0x0080

// ── R4: ToUnicode / GetKeyboardState ─────────────────────────────────────────

/**
 * int ToUnicode(UINT wVirtKey, UINT wScanCode, const BYTE *lpKeyState,
 *               LPWSTR pwszBuff, int cchBuff, UINT wFlags);
 *
 * Translates the virtual-key code and keyboard state into a Unicode character.
 * Returns the number of wide characters written into [pwszBuff]:
 *  > 0 : character(s) produced
 *  = 0 : key does not produce a character
 *  < 0 : dead key (diacritical)
 *
 * **FFM risk**: ToUnicode has a side-effect — it may consume the dead-key state.
 * Use only for non-repeat key-down events; see KadreWndProc.win32KeyText.
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-tounicode
 */
internal val toUnicode: MethodHandle? by lazy {
    user32.downcall(
        "ToUnicode",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int (chars written)
            ValueLayout.JAVA_INT,   // UINT wVirtKey
            ValueLayout.JAVA_INT,   // UINT wScanCode
            ValueLayout.ADDRESS,    // const BYTE *lpKeyState (256 bytes)
            ValueLayout.ADDRESS,    // LPWSTR pwszBuff
            ValueLayout.JAVA_INT,   // int cchBuff
            ValueLayout.JAVA_INT,   // UINT wFlags
        )
    )
}

/**
 * BOOL GetKeyboardState(PBYTE lpKeyState);
 *
 * Fills a 256-byte buffer with the current state of all virtual keys.
 * Each byte: bit 7 = key down, bit 0 = toggled (Caps Lock etc.).
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-getkeyboardstate
 */
internal val getKeyboardState: MethodHandle? by lazy {
    user32.downcall(
        "GetKeyboardState",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // PBYTE lpKeyState (256 bytes)
        )
    )
}
/** ICON_SMALL (0) and ICON_BIG (1) for WM_SETICON. */
internal const val ICON_SMALL: Long = 0L
internal const val ICON_BIG: Long = 1L

/** WS_EX_LAYERED — required to use SetLayeredWindowAttributes. */
internal const val WS_EX_LAYERED: Int = 0x00080000

/** GWL_EXSTYLE — window extended style index. */
internal const val GWL_EXSTYLE: Int = -20

/**
 * BOOL SetLayeredWindowAttributes(HWND hwnd, COLORREF crKey, BYTE bAlpha, DWORD dwFlags);
 * LWA_ALPHA = 0x2 → use bAlpha for the entire window.
 */
internal val setLayeredWindowAttributes: MethodHandle? by lazy {
    user32.downcall(
        "SetLayeredWindowAttributes",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // COLORREF crKey (0 = no color key)
            ValueLayout.JAVA_BYTE,  // BYTE bAlpha (ignored when LWA_COLORKEY only)
            ValueLayout.JAVA_INT,   // DWORD dwFlags
        )
    )
}

/**
 * BOOL SetWindowDisplayAffinity(HWND hWnd, DWORD dwAffinity);
 *
 * Excludes a window from screen capture where supported by the OS/compositor.
 */
internal val setWindowDisplayAffinity: MethodHandle? by lazy {
    user32.downcall(
        "SetWindowDisplayAffinity",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // DWORD dwAffinity
        )
    )
}

/** WDA_NONE disables display-affinity protection. */
internal const val WDA_NONE: Int = 0x00000000

/** WDA_EXCLUDEFROMCAPTURE excludes the window from screen capture on Windows 10 2004+. */
internal const val WDA_EXCLUDEFROMCAPTURE: Int = 0x00000011

/**
 * BOOL FlashWindowEx(const FLASHWINFO *pfwi);
 *
 * Used by Window.requestUserAttention on Win32.
 */
internal val flashWindowEx: MethodHandle? by lazy {
    user32.downcall(
        "FlashWindowEx",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // const FLASHWINFO*
        )
    )
}

/**
 * HWND GetActiveWindow(void);
 *
 * Used to match winit's request_user_attention behavior: an already-active
 * window should not be flashed.
 */
internal val getActiveWindow: MethodHandle? by lazy {
    user32.downcall(
        "GetActiveWindow",
        FunctionDescriptor.of(ValueLayout.ADDRESS)
    )
}

/**
 * FLASHWINFO layout for Kadre's supported 64-bit Windows/JVM target:
 * UINT, padding, HWND, DWORD, UINT, DWORD.
 */
internal const val FLASHWINFO_SIZE: Long = 32L
internal const val FLASHWINFO_ALIGN: Long = 8L
internal const val FLASHWINFO_CB_SIZE_OFFSET: Long = 0L
internal const val FLASHWINFO_HWND_OFFSET: Long = 8L
internal const val FLASHWINFO_FLAGS_OFFSET: Long = 16L
internal const val FLASHWINFO_COUNT_OFFSET: Long = 20L
internal const val FLASHWINFO_TIMEOUT_OFFSET: Long = 24L

internal const val FLASHW_STOP: Int = 0x00000000
internal const val FLASHW_CAPTION: Int = 0x00000001
internal const val FLASHW_TRAY: Int = 0x00000002
internal const val FLASHW_ALL: Int = FLASHW_CAPTION or FLASHW_TRAY
internal const val FLASHW_TIMERNOFG: Int = 0x0000000C
