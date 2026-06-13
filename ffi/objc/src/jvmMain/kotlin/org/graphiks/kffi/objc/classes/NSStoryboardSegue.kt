package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSStoryboardSegue
 * Superclass: NSObject
 */
open class NSStoryboardSegue(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSStoryboardSegue") }
        
        fun segueWithIdentifier_source_destination_performHandler(identifier: MemorySegment, sourceController: MemorySegment, destinationController: MemorySegment, performHandler: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("segueWithIdentifier:source:destination:performHandler:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, sourceController, destinationController, performHandler) as MemorySegment
        }
        
    }
    
    open fun initWithIdentifier_source_destination(identifier: MemorySegment, sourceController: MemorySegment, destinationController: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIdentifier:source:destination:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, identifier, sourceController, destinationController) as MemorySegment
    }
    
    open fun perform(): Unit {
        val sel = ObjCRuntime.sel("perform")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property identifier
    open fun identifier(): MemorySegment {
        val sel = ObjCRuntime.sel("identifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sourceController
    open fun sourceController(): MemorySegment {
        val sel = ObjCRuntime.sel("sourceController")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property destinationController
    open fun destinationController(): MemorySegment {
        val sel = ObjCRuntime.sel("destinationController")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

