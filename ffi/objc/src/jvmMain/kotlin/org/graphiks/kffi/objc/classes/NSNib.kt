package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSNib
 * Superclass: NSObject
 * Protocols: NSCoding
 */
open class NSNib(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSNib") }
        
    }
    
    open fun initWithNibNamed_bundle(nibName: MemorySegment, bundle: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithNibNamed:bundle:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, nibName, bundle) as MemorySegment
    }
    
    open fun initWithNibData_bundle(nibData: MemorySegment, bundle: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithNibData:bundle:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, nibData, bundle) as MemorySegment
    }
    
    open fun instantiateWithOwner_topLevelObjects(owner: MemorySegment, topLevelObjects: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("instantiateWithOwner:topLevelObjects:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, owner, topLevelObjects) as Boolean
    }
    
}

// ── Category: NSDeprecated on NSNib ─────────────────────────────────────────

fun NSNib.initWithContentsOfURL(nibFileURL: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfURL:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, nibFileURL) as MemorySegment
}

fun NSNib.instantiateNibWithExternalNameTable(externalNameTable: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("instantiateNibWithExternalNameTable:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, externalNameTable) as Boolean
}

fun NSNib.instantiateNibWithOwner_topLevelObjects(owner: MemorySegment, topLevelObjects: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("instantiateNibWithOwner:topLevelObjects:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, owner, topLevelObjects) as Boolean
}

