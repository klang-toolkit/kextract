/**
 * Arène partagée pour les upcall stubs WndProc.
 *
 * Les upcall stubs (callbacks C → JVM) doivent vivre dans une arène dont la
 * durée de vie est au moins égale à celle de la fenêtre qui les utilise.
 * Arena.ofShared() est thread-safe et reste valide jusqu'à la fermeture explicite.
 *
 * GRA-141 : cet objet expose l'arène partagée utilisée par [Win32Window] pour
 * enregistrer son stub WNDPROC via [Linker.upcallStub].
 */
package org.graphiks.kadre.win32

import java.lang.foreign.Arena

/**
 * Singleton fournissant l'[Arena] partagée pour les upcall stubs Win32.
 *
 * L'arène est créée paresseusement (lazy) pour éviter tout appel FFM au
 * chargement de classe sur macOS/Linux.
 */
internal object Win32WndProcArena {

    /**
     * Arène partagée (thread-safe) pour les stubs WndProc.
     *
     * Utilise [Arena.ofShared] conformément à la recommandation FFM :
     * les upcall stubs doivent utiliser une arène accessible depuis plusieurs
     * threads (le thread de création et le thread de message Win32).
     */
    val arena: Arena by lazy {
        Arena.ofShared()
    }
}
