package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSpeechSynthesizerDelegate
 * Inherits protocols: NSObject
 */
interface NSSpeechSynthesizerDelegate {
    // @optional
    fun speechSynthesizer_didFinishSpeaking(sender: MemorySegment, finishedSpeaking: Boolean): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'speechSynthesizer:didFinishSpeaking:' not implemented")
    
    // @optional
    fun speechSynthesizer_willSpeakWord_ofString(sender: MemorySegment, characterRange: MemorySegment, string: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'speechSynthesizer:willSpeakWord:ofString:' not implemented")
    
    // @optional
    fun speechSynthesizer_willSpeakPhoneme(sender: MemorySegment, phonemeOpcode: Short): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'speechSynthesizer:willSpeakPhoneme:' not implemented")
    
    // @optional
    fun speechSynthesizer_didEncounterErrorAtIndex_ofString_message(sender: MemorySegment, characterIndex: Long, string: MemorySegment, message: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'speechSynthesizer:didEncounterErrorAtIndex:ofString:message:' not implemented")
    
    // @optional
    fun speechSynthesizer_didEncounterSyncMessage(sender: MemorySegment, message: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'speechSynthesizer:didEncounterSyncMessage:' not implemented")
    
}

