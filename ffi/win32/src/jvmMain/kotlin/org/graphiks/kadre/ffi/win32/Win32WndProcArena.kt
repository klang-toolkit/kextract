/**
 * Shared arena for the WndProc upcall stubs.
 *
 * The upcall stubs (C → JVM callbacks) must live in an arena whose
 * lifetime is at least as long as that of the window using them.
 * Arena.ofShared() is thread-safe and stays valid until explicitly closed.
 *
 * GRA-141: this object exposes the shared arena used by [Win32Window] to
 * register its WNDPROC stub via [Linker.upcallStub].
 */
package org.graphiks.kadre.ffi.win32

import java.lang.foreign.Arena

/**
 * Singleton providing the shared [Arena] for the Win32 upcall stubs.
 *
 * The arena is created lazily to avoid any FFM call at
 * class load time on macOS/Linux.
 */
object Win32WndProcArena {

    /**
     * Shared (thread-safe) arena for the WndProc stubs.
     *
     * Uses [Arena.ofShared] in line with the FFM recommendation:
     * upcall stubs must use an arena accessible from multiple
     * threads (the creation thread and the Win32 message thread).
     */
    val arena: Arena by lazy {
        Arena.ofShared()
    }
}
