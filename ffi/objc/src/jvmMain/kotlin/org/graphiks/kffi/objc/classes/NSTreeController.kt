package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTreeController
 * Superclass: NSObjectController
 */
open class NSTreeController(override val ptr: MemorySegment) : NSObjectController(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTreeController") }
        
    }
    
    open fun rearrangeObjects(): Unit {
        val sel = ObjCRuntime.sel("rearrangeObjects")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    override fun add(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("add:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    override fun remove(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("remove:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun addChild(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addChild:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun insert(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insert:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun insertChild(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertChild:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun insertObject_atArrangedObjectIndexPath(`object`: MemorySegment, indexPath: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertObject:atArrangedObjectIndexPath:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`, indexPath)
    }
    
    open fun insertObjects_atArrangedObjectIndexPaths(objects: MemorySegment, indexPaths: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertObjects:atArrangedObjectIndexPaths:")
        ObjCRuntime.msgSend(null, ptr, sel, objects, indexPaths)
    }
    
    open fun removeObjectAtArrangedObjectIndexPath(indexPath: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObjectAtArrangedObjectIndexPath:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPath)
    }
    
    open fun removeObjectsAtArrangedObjectIndexPaths(indexPaths: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObjectsAtArrangedObjectIndexPaths:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPaths)
    }
    
    open fun setSelectionIndexPaths(indexPaths: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setSelectionIndexPaths:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, indexPaths) as Boolean
    }
    
    open fun setSelectionIndexPath(indexPath: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setSelectionIndexPath:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, indexPath) as Boolean
    }
    
    open fun addSelectionIndexPaths(indexPaths: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("addSelectionIndexPaths:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, indexPaths) as Boolean
    }
    
    open fun removeSelectionIndexPaths(indexPaths: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("removeSelectionIndexPaths:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, indexPaths) as Boolean
    }
    
    open fun moveNode_toIndexPath(node: MemorySegment, indexPath: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveNode:toIndexPath:")
        ObjCRuntime.msgSend(null, ptr, sel, node, indexPath)
    }
    
    open fun moveNodes_toIndexPath(nodes: MemorySegment, startingIndexPath: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveNodes:toIndexPath:")
        ObjCRuntime.msgSend(null, ptr, sel, nodes, startingIndexPath)
    }
    
    open fun childrenKeyPathForNode(node: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("childrenKeyPathForNode:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, node) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun childrenKeyPathForNodeAsString(node: MemorySegment): String = ObjCRuntime.toJavaString(childrenKeyPathForNode(node))
    
    open fun countKeyPathForNode(node: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("countKeyPathForNode:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, node) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun countKeyPathForNodeAsString(node: MemorySegment): String = ObjCRuntime.toJavaString(countKeyPathForNode(node))
    
    open fun leafKeyPathForNode(node: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("leafKeyPathForNode:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, node) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun leafKeyPathForNodeAsString(node: MemorySegment): String = ObjCRuntime.toJavaString(leafKeyPathForNode(node))
    
    // @property arrangedObjects
    open fun arrangedObjects(): MemorySegment {
        val sel = ObjCRuntime.sel("arrangedObjects")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property childrenKeyPath
    open fun childrenKeyPath(): MemorySegment {
        val sel = ObjCRuntime.sel("childrenKeyPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setChildrenKeyPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setChildrenKeyPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun childrenKeyPathAsString(): String = ObjCRuntime.toJavaString(childrenKeyPath())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setChildrenKeyPath(value: String) = setChildrenKeyPath(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property countKeyPath
    open fun countKeyPath(): MemorySegment {
        val sel = ObjCRuntime.sel("countKeyPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCountKeyPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCountKeyPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun countKeyPathAsString(): String = ObjCRuntime.toJavaString(countKeyPath())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setCountKeyPath(value: String) = setCountKeyPath(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property leafKeyPath
    open fun leafKeyPath(): MemorySegment {
        val sel = ObjCRuntime.sel("leafKeyPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLeafKeyPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLeafKeyPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun leafKeyPathAsString(): String = ObjCRuntime.toJavaString(leafKeyPath())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setLeafKeyPath(value: String) = setLeafKeyPath(ObjCRuntime.newNSString(Arena.global(), value))
    
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
    
    // @property content
    override fun content(): MemorySegment {
        val sel = ObjCRuntime.sel("content")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setContent(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canInsert
    open fun canInsert(): Boolean {
        val sel = ObjCRuntime.sel("canInsert")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canInsertChild
    open fun canInsertChild(): Boolean {
        val sel = ObjCRuntime.sel("canInsertChild")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canAddChild
    open fun canAddChild(): Boolean {
        val sel = ObjCRuntime.sel("canAddChild")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
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
    
    // @property selectedObjects
    override fun selectedObjects(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedObjects")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectionIndexPaths
    /** @return NSArray<NSIndexPath *> * */
    open fun selectionIndexPaths(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionIndexPaths")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectionIndexPath
    open fun selectionIndexPath(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionIndexPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectedNodes
    /** @return NSArray<NSTreeNode *> * */
    open fun selectedNodes(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedNodes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

