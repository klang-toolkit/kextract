package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSComboBox
 * Superclass: NSTextField
 */
open class NSComboBox(override val ptr: MemorySegment) : NSTextField(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSComboBox") }
        
    }
    
    open fun reloadData(): Unit {
        val sel = ObjCRuntime.sel("reloadData")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun noteNumberOfItemsChanged(): Unit {
        val sel = ObjCRuntime.sel("noteNumberOfItemsChanged")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun scrollItemAtIndexToTop(index: Long): Unit {
        val sel = ObjCRuntime.sel("scrollItemAtIndexToTop:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun scrollItemAtIndexToVisible(index: Long): Unit {
        val sel = ObjCRuntime.sel("scrollItemAtIndexToVisible:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun selectItemAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("selectItemAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun deselectItemAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("deselectItemAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun addItemWithObjectValue(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addItemWithObjectValue:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun addItemsWithObjectValues(objects: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addItemsWithObjectValues:")
        ObjCRuntime.msgSend(null, ptr, sel, objects)
    }
    
    open fun insertItemWithObjectValue_atIndex(`object`: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertItemWithObjectValue:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`, index)
    }
    
    open fun removeItemWithObjectValue(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeItemWithObjectValue:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun removeItemAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeItemAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun removeAllItems(): Unit {
        val sel = ObjCRuntime.sel("removeAllItems")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun selectItemWithObjectValue(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectItemWithObjectValue:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun itemObjectValueAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("itemObjectValueAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun indexOfItemWithObjectValue(`object`: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfItemWithObjectValue:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, `object`) as Long
    }
    
    // @property hasVerticalScroller
    open fun hasVerticalScroller(): Boolean {
        val sel = ObjCRuntime.sel("hasVerticalScroller")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHasVerticalScroller(value: Boolean) {
        val sel = ObjCRuntime.sel("setHasVerticalScroller:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property intercellSpacing
    open fun intercellSpacing(): MemorySegment {
        val sel = ObjCRuntime.sel("intercellSpacing")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setIntercellSpacing(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setIntercellSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property itemHeight
    open fun itemHeight(): Double {
        val sel = ObjCRuntime.sel("itemHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setItemHeight(value: Double) {
        val sel = ObjCRuntime.sel("setItemHeight:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberOfVisibleItems
    open fun numberOfVisibleItems(): Long {
        val sel = ObjCRuntime.sel("numberOfVisibleItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setNumberOfVisibleItems(value: Long) {
        val sel = ObjCRuntime.sel("setNumberOfVisibleItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property buttonBordered
    open fun isButtonBordered(): Boolean {
        val sel = ObjCRuntime.sel("isButtonBordered")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setButtonBordered(value: Boolean) {
        val sel = ObjCRuntime.sel("setButtonBordered:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesDataSource
    open fun usesDataSource(): Boolean {
        val sel = ObjCRuntime.sel("usesDataSource")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesDataSource(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesDataSource:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property indexOfSelectedItem
    open fun indexOfSelectedItem(): Long {
        val sel = ObjCRuntime.sel("indexOfSelectedItem")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property numberOfItems
    open fun numberOfItems(): Long {
        val sel = ObjCRuntime.sel("numberOfItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property completes
    open fun completes(): Boolean {
        val sel = ObjCRuntime.sel("completes")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCompletes(value: Boolean) {
        val sel = ObjCRuntime.sel("setCompletes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSComboBoxDelegate> */
    override fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property dataSource
    /** @return id<NSComboBoxDataSource> */
    open fun dataSource(): MemorySegment {
        val sel = ObjCRuntime.sel("dataSource")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDataSource(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDataSource:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property objectValueOfSelectedItem
    open fun objectValueOfSelectedItem(): MemorySegment {
        val sel = ObjCRuntime.sel("objectValueOfSelectedItem")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property objectValues
    open fun objectValues(): MemorySegment {
        val sel = ObjCRuntime.sel("objectValues")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

