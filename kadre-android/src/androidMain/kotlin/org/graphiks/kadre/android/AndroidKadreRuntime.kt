package org.graphiks.kadre.android

import org.graphiks.kadre.core.ApplicationHandler

/**
 * Registre global pour le runtime Kadre sur Android.
 *
 * Stocke le [ApplicationHandler] enregistré via [org.graphiks.kadre.EventLoop.runApp]
 * afin qu'il soit récupéré par [KadreActivity.createHandler].
 */
object AndroidKadreRuntime {
    var currentHandler: ApplicationHandler? = null
}
