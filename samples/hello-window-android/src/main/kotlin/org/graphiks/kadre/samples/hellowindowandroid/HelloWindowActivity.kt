package org.graphiks.kadre.samples.hellowindowandroid

import org.graphiks.kadre.android.AndroidKadreRuntime
import org.graphiks.kadre.android.KadreActivity
import org.graphiks.kadre.core.ApplicationHandler

class HelloWindowActivity : KadreActivity() {
    override fun createHandler(): ApplicationHandler =
        AndroidKadreRuntime.currentHandler
            ?: error("EventLoop.runApp() must be called in Application.onCreate() before HelloWindowActivity starts")
}
