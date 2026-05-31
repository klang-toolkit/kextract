package org.graphiks.kadre.samples.hellotouchandroid

import android.app.Application
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.samples.hellotouch.HelloTouchHandler

/**
 * Android demonstration application.
 *
 * Instantiates [HelloTouchHandler] from `:samples:hello-touch` (commonMain)
 * and starts the Kadre EventLoop.
 */
class HelloTouchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        EventLoop().runApp(HelloTouchHandler())
    }
}
