/**
 * Proxy thread-safe vers la boucle d'événements Win32.
 *
 * Permet à des fils d'exécution secondaires de réveiller la boucle
 * de messages Win32 via PostMessageW (ou PostThreadMessageW si le HWND est NULL).
 *
 * Implémentation :
 * - Utilise un WM_NULL posté au thread de messages Win32 via PostThreadMessageW
 *   pour débloquer MsgWaitForMultipleObjectsEx ou GetMessageW.
 * - Le thread ID est capturé au moment de la création du proxy (thread principal).
 *
 * GRA-11 : Win32EventLoopProxy — wakeUp thread-safe.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.EventLoopProxy
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * WM_NULL — message nul Win32, ignoré par la WndProc.
 *
 * Posté par [Win32EventLoopProxy.wakeUp] pour réveiller la boucle de messages
 * sans déclencher de traitement applicatif.
 */
private const val WM_NULL: Int = 0x0000

/**
 * Binding lazy pour GetCurrentThreadId (kernel32).
 *
 * Retourne l'identifiant du thread appelant.
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
 * Binding lazy pour PostThreadMessageW (user32).
 *
 * BOOL PostThreadMessageW(DWORD idThread, UINT Msg, WPARAM wParam, LPARAM lParam);
 * Poste un message dans la file de messages d'un thread spécifique.
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
 * Proxy thread-safe vers une boucle d'événements Win32.
 *
 * [wakeUp] poste un WM_NULL au thread de messages Win32 capturé à la construction,
 * ce qui débloque immédiatement [GetMessageW] ou [MsgWaitForMultipleObjectsEx].
 *
 * @param messageThreadId Identifiant du thread de messages Win32 (capturé au démarrage).
 */
internal class Win32EventLoopProxy(
    private val messageThreadId: Int,
) : EventLoopProxy {

    /**
     * Réveille la boucle de messages Win32 en postant un WM_NULL au thread de messages.
     *
     * Thread-safe — peut être appelé depuis n'importe quel fil d'exécution.
     * No-op sur macOS/Linux (PostThreadMessageW est null).
     */
    override fun wakeUp() {
        postThreadMessageW?.invokeExact(messageThreadId, WM_NULL, 0L, 0L)
    }

    companion object {
        /**
         * Crée un proxy capturant l'identifiant du thread appelant.
         *
         * Doit être appelé depuis le thread de messages Win32 (thread principal).
         * Retourne un proxy no-op si GetCurrentThreadId n'est pas disponible
         * (macOS/Linux).
         */
        fun create(): Win32EventLoopProxy {
            val threadId = try {
                getCurrentThreadId?.invokeExact() as? Int ?: 0
            } catch (_: Throwable) { 0 }
            return Win32EventLoopProxy(threadId)
        }
    }
}
