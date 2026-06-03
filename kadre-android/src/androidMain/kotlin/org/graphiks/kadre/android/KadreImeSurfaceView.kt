package org.graphiks.kadre.android

import android.content.Context
import android.view.SurfaceView
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection

internal class KadreImeSurfaceView(context: Context) : SurfaceView(context) {

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    @Volatile
    var imeConnectionFactory: ((EditorInfo) -> InputConnection?)? = null

    override fun onCheckIsTextEditor(): Boolean = true

    override fun onCreateInputConnection(editorInfo: EditorInfo): InputConnection? {
        return imeConnectionFactory?.invoke(editorInfo)
    }
}
