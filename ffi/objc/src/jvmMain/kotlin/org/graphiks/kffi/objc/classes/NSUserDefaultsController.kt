package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUserDefaultsController
 * Superclass: NSController
 */
open class NSUserDefaultsController(override val ptr: MemorySegment) : NSController(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUserDefaultsController") }
        
        fun sharedUserDefaultsController(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedUserDefaultsController")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithDefaults_initialValues(defaults: MemorySegment, initialValues: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDefaults:initialValues:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, defaults, initialValues) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun revert(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("revert:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun save(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("save:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun revertToInitialValues(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("revertToInitialValues:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    // @property sharedUserDefaultsController
    open fun sharedUserDefaultsController(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedUserDefaultsController")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property defaults
    open fun defaults(): MemorySegment {
        val sel = ObjCRuntime.sel("defaults")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property initialValues
    /** @return NSDictionary<NSString *,id> * */
    open fun initialValues(): MemorySegment {
        val sel = ObjCRuntime.sel("initialValues")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInitialValues(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInitialValues:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property appliesImmediately
    open fun appliesImmediately(): Boolean {
        val sel = ObjCRuntime.sel("appliesImmediately")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAppliesImmediately(value: Boolean) {
        val sel = ObjCRuntime.sel("setAppliesImmediately:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasUnappliedChanges
    open fun hasUnappliedChanges(): Boolean {
        val sel = ObjCRuntime.sel("hasUnappliedChanges")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property values
    open fun values(): MemorySegment {
        val sel = ObjCRuntime.sel("values")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

