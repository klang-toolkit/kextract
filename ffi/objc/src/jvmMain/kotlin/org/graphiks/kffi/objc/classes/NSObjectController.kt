package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSObjectController
 * Superclass: NSController
 */
open class NSObjectController(override val ptr: MemorySegment) : NSController(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSObjectController") }
        
    }
    
    open fun initWithContent(content: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContent:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, content) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun prepareContent(): Unit {
        val sel = ObjCRuntime.sel("prepareContent")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun newObject(): MemorySegment {
        val sel = ObjCRuntime.sel("newObject")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun addObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun removeObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun add(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("add:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun remove(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("remove:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun validateUserInterfaceItem(item: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("validateUserInterfaceItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, item) as Boolean
    }
    
    // @property content
    open fun content(): MemorySegment {
        val sel = ObjCRuntime.sel("content")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContent(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selection
    open fun selection(): MemorySegment {
        val sel = ObjCRuntime.sel("selection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectedObjects
    open fun selectedObjects(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedObjects")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property automaticallyPreparesContent
    open fun automaticallyPreparesContent(): Boolean {
        val sel = ObjCRuntime.sel("automaticallyPreparesContent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticallyPreparesContent(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticallyPreparesContent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property objectClass
    open fun objectClass(): MemorySegment {
        val sel = ObjCRuntime.sel("objectClass")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setObjectClass(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setObjectClass:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property editable
    open fun isEditable(): Boolean {
        val sel = ObjCRuntime.sel("isEditable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEditable(value: Boolean) {
        val sel = ObjCRuntime.sel("setEditable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canAdd
    open fun canAdd(): Boolean {
        val sel = ObjCRuntime.sel("canAdd")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canRemove
    open fun canRemove(): Boolean {
        val sel = ObjCRuntime.sel("canRemove")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSManagedController on NSObjectController ─────────────────────────────────────────

fun NSObjectController.fetchWithRequest_merge_error(fetchRequest: MemorySegment, merge: Boolean, error: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("fetchWithRequest:merge:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, fetchRequest, merge, error) as Boolean
}

fun NSObjectController.fetch(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("fetch:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSObjectController.defaultFetchRequest(): MemorySegment {
    val sel = ObjCRuntime.sel("defaultFetchRequest")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObjectController.managedObjectContext(): MemorySegment {
    val sel = ObjCRuntime.sel("managedObjectContext")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObjectController.setManagedObjectContext(managedObjectContext: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setManagedObjectContext:")
    ObjCRuntime.msgSend(null, this.ptr, sel, managedObjectContext)
}

fun NSObjectController.entityName(): MemorySegment {
    val sel = ObjCRuntime.sel("entityName")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObjectController.setEntityName(entityName: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setEntityName:")
    ObjCRuntime.msgSend(null, this.ptr, sel, entityName)
}

fun NSObjectController.fetchPredicate(): MemorySegment {
    val sel = ObjCRuntime.sel("fetchPredicate")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSObjectController.setFetchPredicate(fetchPredicate: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setFetchPredicate:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fetchPredicate)
}

fun NSObjectController.usesLazyFetching(): Boolean {
    val sel = ObjCRuntime.sel("usesLazyFetching")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSObjectController.setUsesLazyFetching(usesLazyFetching: Boolean): Unit {
    val sel = ObjCRuntime.sel("setUsesLazyFetching:")
    ObjCRuntime.msgSend(null, this.ptr, sel, usesLazyFetching)
}

