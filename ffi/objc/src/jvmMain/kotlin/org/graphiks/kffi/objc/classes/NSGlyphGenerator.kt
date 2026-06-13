package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSGlyphGenerator
 * Superclass: NSObject
 */
open class NSGlyphGenerator(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSGlyphGenerator") }
        
        fun sharedGlyphGenerator(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedGlyphGenerator")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun generateGlyphsForGlyphStorage_desiredNumberOfCharacters_glyphIndex_characterIndex(glyphStorage: MemorySegment, nChars: Long, glyphIndex: MemorySegment, charIndex: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("generateGlyphsForGlyphStorage:desiredNumberOfCharacters:glyphIndex:characterIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, glyphStorage, nChars, glyphIndex, charIndex)
    }
    
    // @property sharedGlyphGenerator
    open fun sharedGlyphGenerator(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedGlyphGenerator")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

