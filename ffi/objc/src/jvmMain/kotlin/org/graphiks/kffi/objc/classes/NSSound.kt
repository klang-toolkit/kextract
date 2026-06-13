package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSound
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding, NSPasteboardReading, NSPasteboardWriting
 */
open class NSSound(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSound") }
        
        fun soundNamed(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("soundNamed:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        fun canInitWithPasteboard(pasteboard: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("canInitWithPasteboard:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, pasteboard) as Boolean
        }
        
        /** @return NSArray<NSString *> * */
        fun soundUnfilteredTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("soundUnfilteredTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithContentsOfURL_byReference(url: MemorySegment, byRef: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentsOfURL:byReference:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, byRef) as MemorySegment
    }
    
    open fun initWithContentsOfFile_byReference(path: MemorySegment, byRef: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentsOfFile:byReference:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, path, byRef) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithContentsOfFile_byReference(path: String, byRef: Boolean): MemorySegment = initWithContentsOfFile_byReference(ObjCRuntime.newNSString(Arena.global(), path), byRef)
    
    open fun initWithData(`data`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`) as MemorySegment
    }
    
    open fun setName(string: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, string) as Boolean
    }
    
    open fun initWithPasteboard(pasteboard: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPasteboard:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pasteboard) as MemorySegment
    }
    
    open fun writeToPasteboard(pasteboard: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("writeToPasteboard:")
        ObjCRuntime.msgSend(null, ptr, sel, pasteboard)
    }
    
    open fun play(): Boolean {
        val sel = ObjCRuntime.sel("play")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun pause(): Boolean {
        val sel = ObjCRuntime.sel("pause")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun resume(): Boolean {
        val sel = ObjCRuntime.sel("resume")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun stop(): Boolean {
        val sel = ObjCRuntime.sel("stop")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun setChannelMapping(channelMapping: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setChannelMapping:")
        ObjCRuntime.msgSend(null, ptr, sel, channelMapping)
    }
    
    open fun channelMapping(): MemorySegment {
        val sel = ObjCRuntime.sel("channelMapping")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property soundUnfilteredTypes
    /** @return NSArray<NSString *> * */
    open fun soundUnfilteredTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("soundUnfilteredTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property playing
    open fun isPlaying(): Boolean {
        val sel = ObjCRuntime.sel("isPlaying")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property delegate
    /** @return id<NSSoundDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property duration
    open fun duration(): Double {
        val sel = ObjCRuntime.sel("duration")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
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
    
    // @property currentTime
    open fun currentTime(): Double {
        val sel = ObjCRuntime.sel("currentTime")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setCurrentTime(value: Double) {
        val sel = ObjCRuntime.sel("setCurrentTime:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property loops
    open fun loops(): Boolean {
        val sel = ObjCRuntime.sel("loops")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setLoops(value: Boolean) {
        val sel = ObjCRuntime.sel("setLoops:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property playbackDeviceIdentifier
    open fun playbackDeviceIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("playbackDeviceIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPlaybackDeviceIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPlaybackDeviceIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSDeprecated on NSSound ─────────────────────────────────────────

// Class method: +[NSSound soundUnfilteredFileTypes]
fun NSSound_soundUnfilteredFileTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("soundUnfilteredFileTypes")
    val cls = ObjCRuntime.getClass("NSSound")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSSound soundUnfilteredPasteboardTypes]
fun NSSound_soundUnfilteredPasteboardTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("soundUnfilteredPasteboardTypes")
    val cls = ObjCRuntime.getClass("NSSound")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

