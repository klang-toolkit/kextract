package org.graphiks.kadre.samples.hellotouchandroid

import android.app.Application
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.samples.hellotouch.HelloTouchHandler

/**
 * Application Android de démonstration.
 *
 * Instancie [HelloTouchHandler] depuis `:samples:hello-touch` (commonMain)
 * et démarre l'EventLoop Kadre.
 */
class HelloTouchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        EventLoop().runApp(HelloTouchHandler())
    }
}
