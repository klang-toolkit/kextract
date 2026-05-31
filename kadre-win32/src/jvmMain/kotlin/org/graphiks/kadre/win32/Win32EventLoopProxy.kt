/**
 * Thread-safe proxy to the Win32 event loop.
 *
 * Allows secondary threads to wake the Win32 message
 * loop via PostMessageW (or PostThreadMessageW if the HWND is NULL).
 *
 * Implementation:
 * - Uses a WM_NULL posted to the Win32 message thread via PostThreadMessageW
 *   to unblock MsgWaitForMultipleObjectsEx or GetMessageW.
 * - The thread ID is captured at proxy creation time (main thread).
 *
 * GRA-11: Win32EventLoopProxy — thread-safe wakeUp.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.EventLoopProxy
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * WM_NULL — Win32 null message, ignored by the WndProc.
 *
 * Posted by [Win32EventLoopProxy.wakeUp] to wake the message loop
 * without triggering any application processing.
 */
private const val WM_NULL: Int = 0x0000

/**
 * Lazy binding for GetCurrentThreadId (kernel32).
 *
 * Returns the identifier of the calling thread.
 */
private val getCurrentThreadId by lazy {
    kernel32?.let { lookup ->
        try {
            val linker = java.lang.foreign.Linker.nativeLinker()
            lookup.find("GetCurrentThreadId").map { sym ->
                linker.downcallHandle(
                    sym,
                    FunctionDescriptor.of(ValueLayout.JAVA_INT)
                )
            }.orElse(null)
        } catch (_: Throwable) { null }
    }
}

/**
 * Lazy binding for PostThreadMessageW (user32).
 *
 * BOOL PostThreadMessageW(DWORD idThread, UINT Msg, WPARAM wParam, LPARAM lParam);
 * Posts a message into the message queue of a specific thread.
 */
private val postThreadMessageW by lazy {
    user32?.let { lookup ->
        try {
            val linker = java.lang.foreign.Linker.nativeLinker()
            lookup.find("PostThreadMessageW").map { sym ->
                linker.downcallHandle(
                    sym,
                    FunctionDescriptor.of(
                        ValueLayout.JAVA_INT,  // BOOL
                        ValueLayout.JAVA_INT,  // DWORD idThread
                        ValueLayout.JAVA_INT,  // UINT Msg
                        ValueLayout.JAVA_LONG, // WPARAM
                        ValueLayout.JAVA_LONG, // LPARAM
                    )
                )
            }.orElse(null)
        } catch (_: Throwable) { null }
    }
}

/**
 * Thread-safe proxy to a Win32 event loop.
 *
 * [wakeUp] posts a WM_NULL to the Win32 message thread captured at construction,
 * which immediately unblocks [GetMessageW] or [MsgWaitForMultipleObjectsEx].
 *
 * @param messageThreadId Identifier of the Win32 message thread (captured at startup).
 */
internal class Win32EventLoopProxy(
    private val messageThreadId: Int,
) : EventLoopProxy {

    /**
     * Wakes the Win32 message loop by posting a WM_NULL to the message thread.
     *
     * Thread-safe — can be called from any thread.
     * No-op on macOS/Linux (PostThreadMessageW is null).
     */
    override fun wakeUp() {
        // PostThreadMessageW returns BOOL (int) — must be captured for invokeExact's exact type.
        postThreadMessageW?.let { it.invokeExact(messageThreadId, WM_NULL, 0L, 0L) as Int }
    }

    companion object {
        /**
         * Creates a proxy capturing the identifier of the calling thread.
         *
         * Must be called from the Win32 message thread (main thread).
         * Returns a no-op proxy if GetCurrentThreadId is not available
         * (macOS/Linux).
         */
        fun create(): Win32EventLoopProxy {
            val threadId = try {
                getCurrentThreadId?.let { it.invokeExact() as Int } ?: 0
            } catch (_: Throwable) { 0 }
            return Win32EventLoopProxy(threadId)
        }
    }
}
