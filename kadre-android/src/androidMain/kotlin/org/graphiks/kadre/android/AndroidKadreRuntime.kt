package org.graphiks.kadre.android

import org.graphiks.kadre.core.ApplicationHandler

/**
 * Global registry for the Kadre runtime on Android.
 *
 * Stores the [ApplicationHandler] registered via [org.graphiks.kadre.EventLoop.runApp]
 * so that it is retrieved by [KadreActivity.createHandler].
 */
object AndroidKadreRuntime {
    var currentHandler: ApplicationHandler? = null
}
