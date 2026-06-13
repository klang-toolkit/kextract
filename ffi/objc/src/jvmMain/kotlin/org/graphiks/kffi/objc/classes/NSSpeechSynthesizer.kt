package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSpeechSynthesizer
 * Superclass: NSObject
 */
open class NSSpeechSynthesizer(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSpeechSynthesizer") }
        
        /** @return NSDictionary<NSVoiceAttributeKey,id> * */
        fun attributesForVoice(voice: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("attributesForVoice:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, voice) as MemorySegment
        }
        
        fun isAnyApplicationSpeaking(): Boolean {
            val sel = ObjCRuntime.sel("isAnyApplicationSpeaking")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun defaultVoice(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultVoice")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSSpeechSynthesizerVoiceName> * */
        fun availableVoices(): MemorySegment {
            val sel = ObjCRuntime.sel("availableVoices")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithVoice(voice: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithVoice:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, voice) as MemorySegment
    }
    
    open fun startSpeakingString(string: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("startSpeakingString:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, string) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun startSpeakingString(string: String): Boolean = startSpeakingString(ObjCRuntime.newNSString(Arena.global(), string))
    
    open fun startSpeakingString_toURL(string: MemorySegment, url: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("startSpeakingString:toURL:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, string, url) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun startSpeakingString_toURL(string: String, url: MemorySegment): Boolean = startSpeakingString_toURL(ObjCRuntime.newNSString(Arena.global(), string), url)
    
    open fun stopSpeaking(): Unit {
        val sel = ObjCRuntime.sel("stopSpeaking")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun stopSpeakingAtBoundary(boundary: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("stopSpeakingAtBoundary:")
        ObjCRuntime.msgSend(null, ptr, sel, boundary)
    }
    
    open fun pauseSpeakingAtBoundary(boundary: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("pauseSpeakingAtBoundary:")
        ObjCRuntime.msgSend(null, ptr, sel, boundary)
    }
    
    open fun continueSpeaking(): Unit {
        val sel = ObjCRuntime.sel("continueSpeaking")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun voice(): MemorySegment {
        val sel = ObjCRuntime.sel("voice")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun setVoice(voice: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setVoice:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, voice) as Boolean
    }
    
    open fun addSpeechDictionary(speechDictionary: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addSpeechDictionary:")
        ObjCRuntime.msgSend(null, ptr, sel, speechDictionary)
    }
    
    open fun phonemesFromText(text: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("phonemesFromText:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, text) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun phonemesFromTextAsString(text: MemorySegment): String = ObjCRuntime.toJavaString(phonemesFromText(text))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun phonemesFromText(text: String): MemorySegment = phonemesFromText(ObjCRuntime.newNSString(Arena.global(), text))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun phonemesFromTextAsString(text: String): String = ObjCRuntime.toJavaString(phonemesFromText(ObjCRuntime.newNSString(Arena.global(), text)))
    
    open fun objectForProperty_error(property: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("objectForProperty:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, property, outError) as MemorySegment
    }
    
    open fun setObject_forProperty_error(`object`: MemorySegment, property: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setObject:forProperty:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, `object`, property, outError) as Boolean
    }
    
    // @property speaking
    open fun isSpeaking(): Boolean {
        val sel = ObjCRuntime.sel("isSpeaking")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property delegate
    /** @return id<NSSpeechSynthesizerDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rate
    open fun rate(): Float {
        val sel = ObjCRuntime.sel("rate")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    open fun setRate(value: Float) {
        val sel = ObjCRuntime.sel("setRate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property volume
    open fun volume(): Float {
        val sel = ObjCRuntime.sel("volume")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    open fun setVolume(value: Float) {
        val sel = ObjCRuntime.sel("setVolume:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesFeedbackWindow
    open fun usesFeedbackWindow(): Boolean {
        val sel = ObjCRuntime.sel("usesFeedbackWindow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesFeedbackWindow(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesFeedbackWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property anyApplicationSpeaking
    open fun isAnyApplicationSpeaking(): Boolean {
        val sel = ObjCRuntime.sel("isAnyApplicationSpeaking")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property defaultVoice
    open fun defaultVoice(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultVoice")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property availableVoices
    /** @return NSArray<NSSpeechSynthesizerVoiceName> * */
    open fun availableVoices(): MemorySegment {
        val sel = ObjCRuntime.sel("availableVoices")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

