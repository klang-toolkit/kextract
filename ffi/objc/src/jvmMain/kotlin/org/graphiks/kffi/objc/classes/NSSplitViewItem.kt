package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSplitViewItem
 * Superclass: NSObject
 * Protocols: NSAnimatablePropertyContainer, NSCoding
 */
open class NSSplitViewItem(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSplitViewItem") }
        
        fun splitViewItemWithViewController(viewController: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("splitViewItemWithViewController:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, viewController) as MemorySegment
        }
        
        fun sidebarWithViewController(viewController: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("sidebarWithViewController:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, viewController) as MemorySegment
        }
        
        fun contentListWithViewController(viewController: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("contentListWithViewController:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, viewController) as MemorySegment
        }
        
        fun inspectorWithViewController(viewController: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("inspectorWithViewController:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, viewController) as MemorySegment
        }
        
    }
    
    open fun addTopAlignedAccessoryViewController(childViewController: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTopAlignedAccessoryViewController:")
        ObjCRuntime.msgSend(null, ptr, sel, childViewController)
    }
    
    open fun insertTopAlignedAccessoryViewController_atIndex(childViewController: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertTopAlignedAccessoryViewController:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, childViewController, index)
    }
    
    open fun removeTopAlignedAccessoryViewControllerAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeTopAlignedAccessoryViewControllerAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun addBottomAlignedAccessoryViewController(childViewController: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addBottomAlignedAccessoryViewController:")
        ObjCRuntime.msgSend(null, ptr, sel, childViewController)
    }
    
    open fun insertBottomAlignedAccessoryViewController_atIndex(childViewController: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertBottomAlignedAccessoryViewController:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, childViewController, index)
    }
    
    open fun removeBottomAlignedAccessoryViewControllerAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeBottomAlignedAccessoryViewControllerAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    // @property behavior
    open fun behavior(): MemorySegment {
        val sel = ObjCRuntime.sel("behavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property viewController
    open fun viewController(): MemorySegment {
        val sel = ObjCRuntime.sel("viewController")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setViewController(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setViewController:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property collapsed
    open fun isCollapsed(): Boolean {
        val sel = ObjCRuntime.sel("isCollapsed")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCollapsed(value: Boolean) {
        val sel = ObjCRuntime.sel("setCollapsed:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canCollapse
    open fun canCollapse(): Boolean {
        val sel = ObjCRuntime.sel("canCollapse")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanCollapse(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanCollapse:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property collapseBehavior
    open fun collapseBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("collapseBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCollapseBehavior(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCollapseBehavior:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minimumThickness
    open fun minimumThickness(): Double {
        val sel = ObjCRuntime.sel("minimumThickness")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinimumThickness(value: Double) {
        val sel = ObjCRuntime.sel("setMinimumThickness:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maximumThickness
    open fun maximumThickness(): Double {
        val sel = ObjCRuntime.sel("maximumThickness")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMaximumThickness(value: Double) {
        val sel = ObjCRuntime.sel("setMaximumThickness:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property preferredThicknessFraction
    open fun preferredThicknessFraction(): Double {
        val sel = ObjCRuntime.sel("preferredThicknessFraction")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setPreferredThicknessFraction(value: Double) {
        val sel = ObjCRuntime.sel("setPreferredThicknessFraction:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property holdingPriority
    open fun holdingPriority(): Float {
        val sel = ObjCRuntime.sel("holdingPriority")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    open fun setHoldingPriority(value: Float) {
        val sel = ObjCRuntime.sel("setHoldingPriority:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property automaticMaximumThickness
    open fun automaticMaximumThickness(): Double {
        val sel = ObjCRuntime.sel("automaticMaximumThickness")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setAutomaticMaximumThickness(value: Double) {
        val sel = ObjCRuntime.sel("setAutomaticMaximumThickness:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property springLoaded
    open fun isSpringLoaded(): Boolean {
        val sel = ObjCRuntime.sel("isSpringLoaded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSpringLoaded(value: Boolean) {
        val sel = ObjCRuntime.sel("setSpringLoaded:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canCollapseFromWindowResize
    open fun canCollapseFromWindowResize(): Boolean {
        val sel = ObjCRuntime.sel("canCollapseFromWindowResize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanCollapseFromWindowResize(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanCollapseFromWindowResize:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsFullHeightLayout
    open fun allowsFullHeightLayout(): Boolean {
        val sel = ObjCRuntime.sel("allowsFullHeightLayout")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsFullHeightLayout(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsFullHeightLayout:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property titlebarSeparatorStyle
    open fun titlebarSeparatorStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("titlebarSeparatorStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTitlebarSeparatorStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTitlebarSeparatorStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property automaticallyAdjustsSafeAreaInsets
    open fun automaticallyAdjustsSafeAreaInsets(): Boolean {
        val sel = ObjCRuntime.sel("automaticallyAdjustsSafeAreaInsets")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticallyAdjustsSafeAreaInsets(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticallyAdjustsSafeAreaInsets:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property topAlignedAccessoryViewControllers
    /** @return NSArray<NSSplitViewItemAccessoryViewController *> * */
    open fun topAlignedAccessoryViewControllers(): MemorySegment {
        val sel = ObjCRuntime.sel("topAlignedAccessoryViewControllers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTopAlignedAccessoryViewControllers(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTopAlignedAccessoryViewControllers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property bottomAlignedAccessoryViewControllers
    /** @return NSArray<NSSplitViewItemAccessoryViewController *> * */
    open fun bottomAlignedAccessoryViewControllers(): MemorySegment {
        val sel = ObjCRuntime.sel("bottomAlignedAccessoryViewControllers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBottomAlignedAccessoryViewControllers(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBottomAlignedAccessoryViewControllers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

