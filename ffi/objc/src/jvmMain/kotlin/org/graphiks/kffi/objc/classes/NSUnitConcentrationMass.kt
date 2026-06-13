package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitConcentrationMass
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitConcentrationMass(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitConcentrationMass") }
        
        fun millimolesPerLiterWithGramsPerMole(gramsPerMole: Double): MemorySegment {
            val sel = ObjCRuntime.sel("millimolesPerLiterWithGramsPerMole:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, gramsPerMole) as MemorySegment
        }
        
        fun gramsPerLiter(): MemorySegment {
            val sel = ObjCRuntime.sel("gramsPerLiter")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun milligramsPerDeciliter(): MemorySegment {
            val sel = ObjCRuntime.sel("milligramsPerDeciliter")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property gramsPerLiter
    open fun gramsPerLiter(): MemorySegment {
        val sel = ObjCRuntime.sel("gramsPerLiter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property milligramsPerDeciliter
    open fun milligramsPerDeciliter(): MemorySegment {
        val sel = ObjCRuntime.sel("milligramsPerDeciliter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

