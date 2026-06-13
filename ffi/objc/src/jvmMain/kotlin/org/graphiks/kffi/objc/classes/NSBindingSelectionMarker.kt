package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSBindingSelectionMarker
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSBindingSelectionMarker(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSBindingSelectionMarker") }
        
        fun setDefaultPlaceholder_forMarker_onClass_withBinding(placeholder: MemorySegment, marker: MemorySegment, objectClass: MemorySegment, binding: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setDefaultPlaceholder:forMarker:onClass:withBinding:")
            ObjCRuntime.msgSend(null, _class, sel, placeholder, marker, objectClass, binding)
        }
        
        fun defaultPlaceholderForMarker_onClass_withBinding(marker: MemorySegment, objectClass: MemorySegment, binding: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("defaultPlaceholderForMarker:onClass:withBinding:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, marker, objectClass, binding) as MemorySegment
        }
        
        fun multipleValuesSelectionMarker(): MemorySegment {
            val sel = ObjCRuntime.sel("multipleValuesSelectionMarker")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun noSelectionMarker(): MemorySegment {
            val sel = ObjCRuntime.sel("noSelectionMarker")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun notApplicableSelectionMarker(): MemorySegment {
            val sel = ObjCRuntime.sel("notApplicableSelectionMarker")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property multipleValuesSelectionMarker
    open fun multipleValuesSelectionMarker(): MemorySegment {
        val sel = ObjCRuntime.sel("multipleValuesSelectionMarker")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property noSelectionMarker
    open fun noSelectionMarker(): MemorySegment {
        val sel = ObjCRuntime.sel("noSelectionMarker")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property notApplicableSelectionMarker
    open fun notApplicableSelectionMarker(): MemorySegment {
        val sel = ObjCRuntime.sel("notApplicableSelectionMarker")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

