package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSpeechRecognizerDelegate
 * Inherits protocols: NSObject
 */
interface NSSpeechRecognizerDelegate {
    // @optional
    fun speechRecognizer_didRecognizeCommand(sender: MemorySegment, command: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'speechRecognizer:didRecognizeCommand:' not implemented")
    
}

