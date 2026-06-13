package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOutlineView
 * Superclass: NSTableView
 * Protocols: NSAccessibilityOutline
 */
open class NSOutlineView(override val ptr: MemorySegment) : NSTableView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOutlineView") }
        
    }
    
    open fun isExpandable(item: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isExpandable:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, item) as Boolean
    }
    
    open fun numberOfChildrenOfItem(item: MemorySegment): Long {
        val sel = ObjCRuntime.sel("numberOfChildrenOfItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, item) as Long
    }
    
    open fun child_ofItem(index: Long, item: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("child:ofItem:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index, item) as MemorySegment
    }
    
    open fun expandItem_expandChildren(item: MemorySegment, expandChildren: Boolean): Unit {
        val sel = ObjCRuntime.sel("expandItem:expandChildren:")
        ObjCRuntime.msgSend(null, ptr, sel, item, expandChildren)
    }
    
    open fun expandItem(item: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("expandItem:")
        ObjCRuntime.msgSend(null, ptr, sel, item)
    }
    
    open fun collapseItem_collapseChildren(item: MemorySegment, collapseChildren: Boolean): Unit {
        val sel = ObjCRuntime.sel("collapseItem:collapseChildren:")
        ObjCRuntime.msgSend(null, ptr, sel, item, collapseChildren)
    }
    
    open fun collapseItem(item: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("collapseItem:")
        ObjCRuntime.msgSend(null, ptr, sel, item)
    }
    
    open fun reloadItem_reloadChildren(item: MemorySegment, reloadChildren: Boolean): Unit {
        val sel = ObjCRuntime.sel("reloadItem:reloadChildren:")
        ObjCRuntime.msgSend(null, ptr, sel, item, reloadChildren)
    }
    
    open fun reloadItem(item: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reloadItem:")
        ObjCRuntime.msgSend(null, ptr, sel, item)
    }
    
    open fun parentForItem(item: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("parentForItem:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, item) as MemorySegment
    }
    
    open fun childIndexForItem(item: MemorySegment): Long {
        val sel = ObjCRuntime.sel("childIndexForItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, item) as Long
    }
    
    open fun itemAtRow(row: Long): MemorySegment {
        val sel = ObjCRuntime.sel("itemAtRow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row) as MemorySegment
    }
    
    open fun rowForItem(item: MemorySegment): Long {
        val sel = ObjCRuntime.sel("rowForItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, item) as Long
    }
    
    open fun levelForItem(item: MemorySegment): Long {
        val sel = ObjCRuntime.sel("levelForItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, item) as Long
    }
    
    open fun levelForRow(row: Long): Long {
        val sel = ObjCRuntime.sel("levelForRow:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, row) as Long
    }
    
    open fun isItemExpanded(item: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isItemExpanded:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, item) as Boolean
    }
    
    open fun frameOfOutlineCellAtRow(row: Long): MemorySegment {
        val sel = ObjCRuntime.sel("frameOfOutlineCellAtRow:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, row) as MemorySegment
    }
    
    open fun setDropItem_dropChildIndex(item: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("setDropItem:dropChildIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, item, index)
    }
    
    open fun shouldCollapseAutoExpandedItemsForDeposited(deposited: Boolean): Boolean {
        val sel = ObjCRuntime.sel("shouldCollapseAutoExpandedItemsForDeposited:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, deposited) as Boolean
    }
    
    open fun insertItemsAtIndexes_inParent_withAnimation(indexes: MemorySegment, parent: MemorySegment, animationOptions: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertItemsAtIndexes:inParent:withAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, parent, animationOptions)
    }
    
    open fun removeItemsAtIndexes_inParent_withAnimation(indexes: MemorySegment, parent: MemorySegment, animationOptions: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeItemsAtIndexes:inParent:withAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, parent, animationOptions)
    }
    
    open fun moveItemAtIndex_inParent_toIndex_inParent(fromIndex: Long, oldParent: MemorySegment, toIndex: Long, newParent: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveItemAtIndex:inParent:toIndex:inParent:")
        ObjCRuntime.msgSend(null, ptr, sel, fromIndex, oldParent, toIndex, newParent)
    }
    
    override fun insertRowsAtIndexes_withAnimation(indexes: MemorySegment, animationOptions: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertRowsAtIndexes:withAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, animationOptions)
    }
    
    override fun removeRowsAtIndexes_withAnimation(indexes: MemorySegment, animationOptions: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeRowsAtIndexes:withAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, animationOptions)
    }
    
    override fun moveRowAtIndex_toIndex(oldIndex: Long, newIndex: Long): Unit {
        val sel = ObjCRuntime.sel("moveRowAtIndex:toIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, oldIndex, newIndex)
    }
    
    // @property delegate
    /** @return id<NSOutlineViewDelegate> */
    override fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property dataSource
    /** @return id<NSOutlineViewDataSource> */
    override fun dataSource(): MemorySegment {
        val sel = ObjCRuntime.sel("dataSource")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setDataSource(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDataSource:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property outlineTableColumn
    open fun outlineTableColumn(): MemorySegment {
        val sel = ObjCRuntime.sel("outlineTableColumn")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setOutlineTableColumn(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setOutlineTableColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property indentationPerLevel
    open fun indentationPerLevel(): Double {
        val sel = ObjCRuntime.sel("indentationPerLevel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setIndentationPerLevel(value: Double) {
        val sel = ObjCRuntime.sel("setIndentationPerLevel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property indentationMarkerFollowsCell
    open fun indentationMarkerFollowsCell(): Boolean {
        val sel = ObjCRuntime.sel("indentationMarkerFollowsCell")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIndentationMarkerFollowsCell(value: Boolean) {
        val sel = ObjCRuntime.sel("setIndentationMarkerFollowsCell:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autoresizesOutlineColumn
    open fun autoresizesOutlineColumn(): Boolean {
        val sel = ObjCRuntime.sel("autoresizesOutlineColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutoresizesOutlineColumn(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutoresizesOutlineColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autosaveExpandedItems
    open fun autosaveExpandedItems(): Boolean {
        val sel = ObjCRuntime.sel("autosaveExpandedItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutosaveExpandedItems(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutosaveExpandedItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property userInterfaceLayoutDirection
    override fun userInterfaceLayoutDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("userInterfaceLayoutDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setUserInterfaceLayoutDirection(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUserInterfaceLayoutDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property stronglyReferencesItems
    open fun stronglyReferencesItems(): Boolean {
        val sel = ObjCRuntime.sel("stronglyReferencesItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setStronglyReferencesItems(value: Boolean) {
        val sel = ObjCRuntime.sel("setStronglyReferencesItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

