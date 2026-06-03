package org.graphiks.kadre.android

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.BaseInputConnection
import android.view.inputmethod.EditorInfo
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowEvent.Ime.ImeEvent

internal class KadreInputConnection(
    private val dispatchEvent: (WindowEvent) -> Unit,
    targetView: View,
    editorInfo: EditorInfo,
) : BaseInputConnection(targetView, true) {

    private val mainHandler = Handler(Looper.getMainLooper())
    private var composing = false
    private var closed = false

    init {
        postEvent(WindowEvent.Ime(ImeEvent.Enabled))
    }

    override fun commitText(text: CharSequence, newCursorPosition: Int): Boolean {
        postEvent(WindowEvent.Ime(ImeEvent.Commit(text.toString())))
        composing = false
        return true
    }

    override fun setComposingText(text: CharSequence, newCursorPosition: Int): Boolean {
        composing = text.isNotEmpty()
        postEvent(WindowEvent.Ime(ImeEvent.Preedit(text.toString(), null)))
        return true
    }

    override fun finishComposingText(): Boolean {
        composing = false
        return true
    }

    override fun deleteSurroundingText(beforeLength: Int, afterLength: Int): Boolean {
        postEvent(WindowEvent.Ime(ImeEvent.DeleteSurrounding(beforeLength, afterLength)))
        return true
    }

    override fun closeConnection() {
        if (!closed) {
            closed = true
            composing = false
            postEvent(WindowEvent.Ime(ImeEvent.Disabled))
        }
        super.closeConnection()
    }

    private fun postEvent(event: WindowEvent) {
        mainHandler.post { dispatchEvent(event) }
    }
}
