package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSImageSymbolConfiguration
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSImageSymbolConfiguration(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSImageSymbolConfiguration") }
        
        fun configurationWithPointSize_weight_scale(pointSize: Double, weight: Double, scale: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("configurationWithPointSize:weight:scale:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, pointSize, weight, scale) as MemorySegment
        }
        
        fun configurationWithPointSize_weight(pointSize: Double, weight: Double): MemorySegment {
            val sel = ObjCRuntime.sel("configurationWithPointSize:weight:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, pointSize, weight) as MemorySegment
        }
        
        fun configurationWithTextStyle_scale(style: MemorySegment, scale: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("configurationWithTextStyle:scale:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, style, scale) as MemorySegment
        }
        
        fun configurationWithTextStyle(style: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("configurationWithTextStyle:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, style) as MemorySegment
        }
        
        fun configurationWithScale(scale: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("configurationWithScale:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, scale) as MemorySegment
        }
        
        fun configurationPreferringMonochrome(): MemorySegment {
            val sel = ObjCRuntime.sel("configurationPreferringMonochrome")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun configurationPreferringHierarchical(): MemorySegment {
            val sel = ObjCRuntime.sel("configurationPreferringHierarchical")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun configurationWithHierarchicalColor(hierarchicalColor: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("configurationWithHierarchicalColor:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, hierarchicalColor) as MemorySegment
        }
        
        fun configurationWithPaletteColors(paletteColors: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("configurationWithPaletteColors:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, paletteColors) as MemorySegment
        }
        
        fun configurationPreferringMulticolor(): MemorySegment {
            val sel = ObjCRuntime.sel("configurationPreferringMulticolor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun configurationWithVariableValueMode(variableValueMode: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("configurationWithVariableValueMode:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, variableValueMode) as MemorySegment
        }
        
        fun configurationWithColorRenderingMode(mode: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("configurationWithColorRenderingMode:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, mode) as MemorySegment
        }
        
    }
    
    open fun configurationByApplyingConfiguration(configuration: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("configurationByApplyingConfiguration:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, configuration) as MemorySegment
    }
    
}

