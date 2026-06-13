package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMorphologyPronoun
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSMorphologyPronoun(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMorphologyPronoun") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithPronoun_morphology_dependentMorphology(pronoun: MemorySegment, morphology: MemorySegment, dependentMorphology: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPronoun:morphology:dependentMorphology:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pronoun, morphology, dependentMorphology) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithPronoun_morphology_dependentMorphology(pronoun: String, morphology: MemorySegment, dependentMorphology: MemorySegment): MemorySegment = initWithPronoun_morphology_dependentMorphology(ObjCRuntime.newNSString(Arena.global(), pronoun), morphology, dependentMorphology)
    
    // @property pronoun
    open fun pronoun(): MemorySegment {
        val sel = ObjCRuntime.sel("pronoun")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun pronounAsString(): String = ObjCRuntime.toJavaString(pronoun())
    
    // @property morphology
    open fun morphology(): MemorySegment {
        val sel = ObjCRuntime.sel("morphology")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property dependentMorphology
    open fun dependentMorphology(): MemorySegment {
        val sel = ObjCRuntime.sel("dependentMorphology")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

