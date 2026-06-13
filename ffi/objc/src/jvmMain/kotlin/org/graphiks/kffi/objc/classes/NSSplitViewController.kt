package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSplitViewController
 * Superclass: NSViewController
 * Protocols: NSSplitViewDelegate, NSUserInterfaceValidations
 */
open class NSSplitViewController(override val ptr: MemorySegment) : NSViewController(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSplitViewController") }
        
    }
    
    open fun addSplitViewItem(splitViewItem: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addSplitViewItem:")
        ObjCRuntime.msgSend(null, ptr, sel, splitViewItem)
    }
    
    open fun insertSplitViewItem_atIndex(splitViewItem: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertSplitViewItem:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, splitViewItem, index)
    }
    
    open fun removeSplitViewItem(splitViewItem: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeSplitViewItem:")
        ObjCRuntime.msgSend(null, ptr, sel, splitViewItem)
    }
    
    open fun splitViewItemForViewController(viewController: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("splitViewItemForViewController:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, viewController) as MemorySegment
    }
    
    open fun validateUserInterfaceItem(item: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("validateUserInterfaceItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, item) as Boolean
    }
    
    override fun viewDidLoad(): Unit {
        val sel = ObjCRuntime.sel("viewDidLoad")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun splitView_canCollapseSubview(splitView: MemorySegment, subview: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("splitView:canCollapseSubview:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, splitView, subview) as Boolean
    }
    
    open fun splitView_shouldCollapseSubview_forDoubleClickOnDividerAtIndex(splitView: MemorySegment, subview: MemorySegment, dividerIndex: Long): Boolean {
        val sel = ObjCRuntime.sel("splitView:shouldCollapseSubview:forDoubleClickOnDividerAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, splitView, subview, dividerIndex) as Boolean
    }
    
    open fun splitView_shouldHideDividerAtIndex(splitView: MemorySegment, dividerIndex: Long): Boolean {
        val sel = ObjCRuntime.sel("splitView:shouldHideDividerAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, splitView, dividerIndex) as Boolean
    }
    
    open fun splitView_effectiveRect_forDrawnRect_ofDividerAtIndex(splitView: MemorySegment, proposedEffectiveRect: MemorySegment, drawnRect: MemorySegment, dividerIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("splitView:effectiveRect:forDrawnRect:ofDividerAtIndex:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, splitView, ObjCRuntime.ObjCStructArg(proposedEffectiveRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(drawnRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), dividerIndex) as MemorySegment
    }
    
    open fun splitView_additionalEffectiveRectOfDividerAtIndex(splitView: MemorySegment, dividerIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("splitView:additionalEffectiveRectOfDividerAtIndex:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, splitView, dividerIndex) as MemorySegment
    }
    
    // @property splitView
    open fun splitView(): MemorySegment {
        val sel = ObjCRuntime.sel("splitView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSplitView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSplitView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property splitViewItems
    /** @return NSArray<__kindof NSSplitViewItem *> * */
    open fun splitViewItems(): MemorySegment {
        val sel = ObjCRuntime.sel("splitViewItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSplitViewItems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSplitViewItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minimumThicknessForInlineSidebars
    open fun minimumThicknessForInlineSidebars(): Double {
        val sel = ObjCRuntime.sel("minimumThicknessForInlineSidebars")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinimumThicknessForInlineSidebars(value: Double) {
        val sel = ObjCRuntime.sel("setMinimumThicknessForInlineSidebars:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSSplitViewControllerToggleSidebarAction on NSSplitViewController ─────────────────────────────────────────

fun NSSplitViewController.toggleSidebar(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("toggleSidebar:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSSplitViewController.toggleInspector(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("toggleInspector:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

