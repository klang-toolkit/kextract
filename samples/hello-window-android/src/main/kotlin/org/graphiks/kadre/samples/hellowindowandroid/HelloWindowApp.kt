package org.graphiks.kadre.samples.hellowindowandroid

import android.app.Application
import org.graphiks.kadre.EventLoop

class HelloWindowApp : Application() {
    override fun onCreate() {
        super.onCreate()
        EventLoop().runApp(HelloApp())
    }
}
