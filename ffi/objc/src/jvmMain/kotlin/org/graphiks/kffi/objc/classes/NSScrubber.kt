package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSScrubber
 * Superclass: NSView
 */
open class NSScrubber(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSScrubber") }
        
    }
    
    override fun initWithFrame(frameRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun reloadData(): Unit {
        val sel = ObjCRuntime.sel("reloadData")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun performSequentialBatchUpdates(updateBlock: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performSequentialBatchUpdates:")
        ObjCRuntime.msgSend(null, ptr, sel, updateBlock)
    }
    
    open fun insertItemsAtIndexes(indexes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertItemsAtIndexes:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes)
    }
    
    open fun removeItemsAtIndexes(indexes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeItemsAtIndexes:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes)
    }
    
    open fun reloadItemsAtIndexes(indexes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reloadItemsAtIndexes:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes)
    }
    
    open fun moveItemAtIndex_toIndex(oldIndex: Long, newIndex: Long): Unit {
        val sel = ObjCRuntime.sel("moveItemAtIndex:toIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, oldIndex, newIndex)
    }
    
    open fun scrollItemAtIndex_toAlignment(index: Long, alignment: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scrollItemAtIndex:toAlignment:")
        ObjCRuntime.msgSend(null, ptr, sel, index, alignment)
    }
    
    open fun itemViewForItemAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("itemViewForItemAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun registerClass_forItemIdentifier(itemViewClass: MemorySegment, itemIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("registerClass:forItemIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, itemViewClass, itemIdentifier)
    }
    
    open fun registerNib_forItemIdentifier(nib: MemorySegment, itemIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("registerNib:forItemIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, nib, itemIdentifier)
    }
    
    open fun makeItemWithIdentifier_owner(itemIdentifier: MemorySegment, owner: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("makeItemWithIdentifier:owner:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, itemIdentifier, owner) as MemorySegment
    }
    
    // @property dataSource
    /** @return id<NSScrubberDataSource> */
    open fun dataSource(): MemorySegment {
        val sel = ObjCRuntime.sel("dataSource")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDataSource(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDataSource:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSScrubberDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property scrubberLayout
    open fun scrubberLayout(): MemorySegment {
        val sel = ObjCRuntime.sel("scrubberLayout")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScrubberLayout(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScrubberLayout:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberOfItems
    open fun numberOfItems(): Long {
        val sel = ObjCRuntime.sel("numberOfItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property highlightedIndex
    open fun highlightedIndex(): Long {
        val sel = ObjCRuntime.sel("highlightedIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property selectedIndex
    open fun selectedIndex(): Long {
        val sel = ObjCRuntime.sel("selectedIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setSelectedIndex(value: Long) {
        val sel = ObjCRuntime.sel("setSelectedIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property mode
    open fun mode(): MemorySegment {
        val sel = ObjCRuntime.sel("mode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property itemAlignment
    open fun itemAlignment(): MemorySegment {
        val sel = ObjCRuntime.sel("itemAlignment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setItemAlignment(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setItemAlignment:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property continuous
    open fun isContinuous(): Boolean {
        val sel = ObjCRuntime.sel("isContinuous")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setContinuous(value: Boolean) {
        val sel = ObjCRuntime.sel("setContinuous:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property floatsSelectionViews
    open fun floatsSelectionViews(): Boolean {
        val sel = ObjCRuntime.sel("floatsSelectionViews")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setFloatsSelectionViews(value: Boolean) {
        val sel = ObjCRuntime.sel("setFloatsSelectionViews:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectionBackgroundStyle
    open fun selectionBackgroundStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionBackgroundStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionBackgroundStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionBackgroundStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectionOverlayStyle
    open fun selectionOverlayStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionOverlayStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionOverlayStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionOverlayStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsArrowButtons
    open fun showsArrowButtons(): Boolean {
        val sel = ObjCRuntime.sel("showsArrowButtons")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsArrowButtons(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsArrowButtons:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsAdditionalContentIndicators
    open fun showsAdditionalContentIndicators(): Boolean {
        val sel = ObjCRuntime.sel("showsAdditionalContentIndicators")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsAdditionalContentIndicators(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsAdditionalContentIndicators:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backgroundColor
    open fun backgroundColor(): MemorySegment {
        val sel = ObjCRuntime.sel("backgroundColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackgroundColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackgroundColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backgroundView
    open fun backgroundView(): MemorySegment {
        val sel = ObjCRuntime.sel("backgroundView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackgroundView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackgroundView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

