/**
 * Implémentation JVM de la boucle d'événements — délègue à AppKitEventLoop.
 *
 * Ce module (kadre-core) fournit la déclaration `actual` ; l'implémentation
 * concrète est dans kadre-appkit via la fonction top-level `runApp`.
 *
 * L'indirection par réflexion évite une dépendance directe de kadre-core
 * vers kadre-appkit, conformément à l'architecture modulaire du projet.
 */
package org.graphiks.kadre.core

/**
 * Implémentation JVM de [EventLoop].
 *
 * Délègue à `org.graphiks.kadre.appkit.AppKitEventLoopKt.runApp` via
 * réflexion pour éviter un couplage direct de kadre-core → kadre-appkit.
 * Cette délégation est résolue à l'exécution : kadre-appkit doit être sur
 * le classpath.
 */
actual class EventLoop actual constructor() {

    /**
     * Démarre la boucle d'événements AppKit et délègue les rappels au gestionnaire fourni.
     *
     * Bloquant — ne retourne qu'à la fermeture de l'application.
     *
     * @param handler Gestionnaire du cycle de vie et des événements de l'application.
     * @throws UnsupportedOperationException si kadre-appkit n'est pas sur le classpath.
     */
    actual fun runApp(handler: ApplicationHandler) {
        val klass = try {
            Class.forName("org.graphiks.kadre.appkit.AppKitEventLoopKt")
        } catch (e: ClassNotFoundException) {
            throw UnsupportedOperationException(
                "kadre-appkit introuvable sur le classpath. " +
                "Ajoutez la dépendance implementation(project(\":kadre-appkit\")).",
                e,
            )
        }
        val method = klass.getMethod("runApp", ApplicationHandler::class.java)
        method.invoke(null, handler)
    }
}
