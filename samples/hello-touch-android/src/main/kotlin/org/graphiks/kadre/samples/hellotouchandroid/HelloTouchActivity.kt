package org.graphiks.kadre.samples.hellotouchandroid

import org.graphiks.kadre.android.AndroidKadreRuntime
import org.graphiks.kadre.android.KadreActivity
import org.graphiks.kadre.core.ApplicationHandler

class HelloTouchActivity : KadreActivity() {
    override fun createHandler(): ApplicationHandler =
        AndroidKadreRuntime.currentHandler
            ?: error("EventLoop.runApp() must be called in Application.onCreate() before HelloTouchActivity starts")
}
