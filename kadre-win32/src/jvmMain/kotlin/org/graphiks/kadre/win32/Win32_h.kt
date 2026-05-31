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
