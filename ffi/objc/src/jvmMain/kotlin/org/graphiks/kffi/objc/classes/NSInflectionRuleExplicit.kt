package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSInflectionRuleExplicit
 * Superclass: NSInflectionRule
 */
open class NSInflectionRuleExplicit(override val ptr: MemorySegment) : NSInflectionRule(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSInflectionRuleExplicit") }
        
    }
    
    open fun initWithMorphology(morphology: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMorphology:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, morphology) as MemorySegment
    }
    
    // @property morphology
    open fun morphology(): MemorySegment {
        val sel = ObjCRuntime.sel("morphology")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

