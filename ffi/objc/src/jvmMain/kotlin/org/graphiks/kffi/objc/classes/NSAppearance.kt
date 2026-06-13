package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAppearance
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSAppearance(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAppearance") }
        
        fun appearanceNamed(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("appearanceNamed:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        fun currentAppearance(): MemorySegment {
            val sel = ObjCRuntime.sel("currentAppearance")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun setCurrentAppearance(currentAppearance: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setCurrentAppearance:")
            ObjCRuntime.msgSend(null, _class, sel, currentAppearance)
        }
        
        fun currentDrawingAppearance(): MemorySegment {
            val sel = ObjCRuntime.sel("currentDrawingAppearance")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun performAsCurrentDrawingAppearance(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performAsCurrentDrawingAppearance:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun initWithAppearanceNamed_bundle(name: MemorySegment, bundle: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithAppearanceNamed:bundle:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, bundle) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun bestMatchFromAppearancesWithNames(appearances: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("bestMatchFromAppearancesWithNames:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, appearances) as MemorySegment
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property currentAppearance
    open fun currentAppearance(): MemorySegment {
        val sel = ObjCRuntime.sel("currentAppearance")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCurrentAppearance(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCurrentAppearance:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property currentDrawingAppearance
    open fun currentDrawingAppearance(): MemorySegment {
        val sel = ObjCRuntime.sel("currentDrawingAppearance")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property allowsVibrancy
    open fun allowsVibrancy(): Boolean {
        val sel = ObjCRuntime.sel("allowsVibrancy")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

