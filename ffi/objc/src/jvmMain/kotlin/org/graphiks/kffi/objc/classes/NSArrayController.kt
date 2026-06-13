package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSArrayController
 * Superclass: NSObjectController
 */
open class NSArrayController(override val ptr: MemorySegment) : NSObjectController(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSArrayController") }
        
    }
    
    open fun rearrangeObjects(): Unit {
        val sel = ObjCRuntime.sel("rearrangeObjects")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun didChangeArrangementCriteria(): Unit {
        val sel = ObjCRuntime.sel("didChangeArrangementCriteria")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun arrangeObjects(objects: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("arrangeObjects:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, objects) as MemorySegment
    }
    
    open fun setSelectionIndexes(indexes: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setSelectionIndexes:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, indexes) as Boolean
    }
    
    open fun setSelectionIndex(index: Long): Boolean {
        val sel = ObjCRuntime.sel("setSelectionIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, index) as Boolean
    }
    
    open fun addSelectionIndexes(indexes: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("addSelectionIndexes:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, indexes) as Boolean
    }
    
    open fun removeSelectionIndexes(indexes: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("removeSelectionIndexes:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, indexes) as Boolean
    }
    
    open fun setSelectedObjects(objects: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setSelectedObjects:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, objects) as Boolean
    }
    
    open fun addSelectedObjects(objects: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("addSelectedObjects:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, objects) as Boolean
    }
    
    open fun removeSelectedObjects(objects: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("removeSelectedObjects:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, objects) as Boolean
    }
    
    override fun add(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("add:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    override fun remove(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("remove:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun insert(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insert:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectNext(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectNext:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectPrevious(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectPrevious:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    override fun addObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun addObjects(objects: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addObjects:")
        ObjCRuntime.msgSend(null, ptr, sel, objects)
    }
    
    open fun insertObject_atArrangedObjectIndex(`object`: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertObject:atArrangedObjectIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`, index)
    }
    
    open fun insertObjects_atArrangedObjectIndexes(objects: MemorySegment, indexes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertObjects:atArrangedObjectIndexes:")
        ObjCRuntime.msgSend(null, ptr, sel, objects, indexes)
    }
    
    open fun removeObjectAtArrangedObjectIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeObjectAtArrangedObjectIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun removeObjectsAtArrangedObjectIndexes(indexes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObjectsAtArrangedObjectIndexes:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes)
    }
    
    override fun removeObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun removeObjects(objects: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObjects:")
        ObjCRuntime.msgSend(null, ptr, sel, objects)
    }
    
    // @property automaticallyRearrangesObjects
    open fun automaticallyRearrangesObjects(): Boolean {
        val sel = ObjCRuntime.sel("automaticallyRearrangesObjects")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticallyRearrangesObjects(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticallyRearrangesObjects:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property automaticRearrangementKeyPaths
    /** @return NSArray<NSString *> * */
    open fun automaticRearrangementKeyPaths(): MemorySegment {
        val sel = ObjCRuntime.sel("automaticRearrangementKeyPaths")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sortDescriptors
    /** @return NSArray<NSSortDescriptor *> * */
    open fun sortDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("sortDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSortDescriptors(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSortDescriptors:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property filterPredicate
    open fun filterPredicate(): MemorySegment {
        val sel = ObjCRuntime.sel("filterPredicate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFilterPredicate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFilterPredicate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property clearsFilterPredicateOnInsertion
    open fun clearsFilterPredicateOnInsertion(): Boolean {
        val sel = ObjCRuntime.sel("clearsFilterPredicateOnInsertion")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setClearsFilterPredicateOnInsertion(value: Boolean) {
        val sel = ObjCRuntime.sel("setClearsFilterPredicateOnInsertion:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property arrangedObjects
    open fun arrangedObjects(): MemorySegment {
        val sel = ObjCRuntime.sel("arrangedObjects")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property avoidsEmptySelection
    open fun avoidsEmptySelection(): Boolean {
        val sel = ObjCRuntime.sel("avoidsEmptySelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAvoidsEmptySelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setAvoidsEmptySelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property preservesSelection
    open fun preservesSelection(): Boolean {
        val sel = ObjCRuntime.sel("preservesSelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPreservesSelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setPreservesSelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectsInsertedObjects
    open fun selectsInsertedObjects(): Boolean {
        val sel = ObjCRuntime.sel("selectsInsertedObjects")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSelectsInsertedObjects(value: Boolean) {
        val sel = ObjCRuntime.sel("setSelectsInsertedObjects:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property alwaysUsesMultipleValuesMarker
    open fun alwaysUsesMultipleValuesMarker(): Boolean {
        val sel = ObjCRuntime.sel("alwaysUsesMultipleValuesMarker")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAlwaysUsesMultipleValuesMarker(value: Boolean) {
        val sel = ObjCRuntime.sel("setAlwaysUsesMultipleValuesMarker:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectionIndexes
    open fun selectionIndexes(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionIndexes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectionIndex
    open fun selectionIndex(): Long {
        val sel = ObjCRuntime.sel("selectionIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property selectedObjects
    override fun selectedObjects(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedObjects")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property canInsert
    open fun canInsert(): Boolean {
        val sel = ObjCRuntime.sel("canInsert")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canSelectNext
    open fun canSelectNext(): Boolean {
        val sel = ObjCRuntime.sel("canSelectNext")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canSelectPrevious
    open fun canSelectPrevious(): Boolean {
        val sel = ObjCRuntime.sel("canSelectPrevious")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

