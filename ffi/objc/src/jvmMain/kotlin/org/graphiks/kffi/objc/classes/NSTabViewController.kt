package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTabViewController
 * Superclass: NSViewController
 * Protocols: NSTabViewDelegate, NSToolbarDelegate
 */
open class NSTabViewController(override val ptr: MemorySegment) : NSViewController(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTabViewController") }
        
    }
    
    open fun addTabViewItem(tabViewItem: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTabViewItem:")
        ObjCRuntime.msgSend(null, ptr, sel, tabViewItem)
    }
    
    open fun insertTabViewItem_atIndex(tabViewItem: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertTabViewItem:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, tabViewItem, index)
    }
    
    open fun removeTabViewItem(tabViewItem: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeTabViewItem:")
        ObjCRuntime.msgSend(null, ptr, sel, tabViewItem)
    }
    
    open fun tabViewItemForViewController(viewController: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("tabViewItemForViewController:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, viewController) as MemorySegment
    }
    
    override fun viewDidLoad(): Unit {
        val sel = ObjCRuntime.sel("viewDidLoad")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun tabView_willSelectTabViewItem(tabView: MemorySegment, tabViewItem: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("tabView:willSelectTabViewItem:")
        ObjCRuntime.msgSend(null, ptr, sel, tabView, tabViewItem)
    }
    
    open fun tabView_didSelectTabViewItem(tabView: MemorySegment, tabViewItem: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("tabView:didSelectTabViewItem:")
        ObjCRuntime.msgSend(null, ptr, sel, tabView, tabViewItem)
    }
    
    open fun tabView_shouldSelectTabViewItem(tabView: MemorySegment, tabViewItem: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("tabView:shouldSelectTabViewItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, tabView, tabViewItem) as Boolean
    }
    
    open fun toolbar_itemForItemIdentifier_willBeInsertedIntoToolbar(toolbar: MemorySegment, itemIdentifier: MemorySegment, flag: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("toolbar:itemForItemIdentifier:willBeInsertedIntoToolbar:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, toolbar, itemIdentifier, flag) as MemorySegment
    }
    
    /** @return NSArray<NSToolbarItemIdentifier> * */
    open fun toolbarDefaultItemIdentifiers(toolbar: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("toolbarDefaultItemIdentifiers:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, toolbar) as MemorySegment
    }
    
    /** @return NSArray<NSToolbarItemIdentifier> * */
    open fun toolbarAllowedItemIdentifiers(toolbar: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("toolbarAllowedItemIdentifiers:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, toolbar) as MemorySegment
    }
    
    /** @return NSArray<NSToolbarItemIdentifier> * */
    open fun toolbarSelectableItemIdentifiers(toolbar: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("toolbarSelectableItemIdentifiers:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, toolbar) as MemorySegment
    }
    
    // @property tabStyle
    open fun tabStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("tabStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTabStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTabStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tabView
    open fun tabView(): MemorySegment {
        val sel = ObjCRuntime.sel("tabView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTabView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTabView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property transitionOptions
    open fun transitionOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("transitionOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTransitionOptions(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTransitionOptions:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canPropagateSelectedChildViewControllerTitle
    open fun canPropagateSelectedChildViewControllerTitle(): Boolean {
        val sel = ObjCRuntime.sel("canPropagateSelectedChildViewControllerTitle")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanPropagateSelectedChildViewControllerTitle(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanPropagateSelectedChildViewControllerTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tabViewItems
    /** @return NSArray<__kindof NSTabViewItem *> * */
    open fun tabViewItems(): MemorySegment {
        val sel = ObjCRuntime.sel("tabViewItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTabViewItems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTabViewItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectedTabViewItemIndex
    open fun selectedTabViewItemIndex(): Long {
        val sel = ObjCRuntime.sel("selectedTabViewItemIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setSelectedTabViewItemIndex(value: Long) {
        val sel = ObjCRuntime.sel("setSelectedTabViewItemIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

