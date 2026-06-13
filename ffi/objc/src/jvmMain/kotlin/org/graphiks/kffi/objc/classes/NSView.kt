package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSView
 * Superclass: NSResponder
 * Protocols: NSAnimatablePropertyContainer, NSUserInterfaceItemIdentification, NSDraggingDestination, NSAppearanceCustomization, NSAccessibilityElement, NSAccessibility
 */
open class NSView(override val ptr: MemorySegment) : NSResponder(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSView") }
        
        fun focusView(): MemorySegment {
            val sel = ObjCRuntime.sel("focusView")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun defaultMenu(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultMenu")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun isCompatibleWithResponsiveScrolling(): Boolean {
            val sel = ObjCRuntime.sel("isCompatibleWithResponsiveScrolling")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
    }
    
    open fun initWithFrame(frameRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun isDescendantOf(view: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isDescendantOf:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, view) as Boolean
    }
    
    open fun ancestorSharedWithView(view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("ancestorSharedWithView:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, view) as MemorySegment
    }
    
    open fun getRectsBeingDrawn_count(rects: MemorySegment, count: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getRectsBeingDrawn:count:")
        ObjCRuntime.msgSend(null, ptr, sel, rects, count)
    }
    
    open fun needsToDrawRect(rect: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("needsToDrawRect:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as Boolean
    }
    
    open fun viewDidHide(): Unit {
        val sel = ObjCRuntime.sel("viewDidHide")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun viewDidUnhide(): Unit {
        val sel = ObjCRuntime.sel("viewDidUnhide")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun addSubview(view: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addSubview:")
        ObjCRuntime.msgSend(null, ptr, sel, view)
    }
    
    open fun addSubview_positioned_relativeTo(view: MemorySegment, place: MemorySegment, otherView: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addSubview:positioned:relativeTo:")
        ObjCRuntime.msgSend(null, ptr, sel, view, place, otherView)
    }
    
    open fun sortSubviewsUsingFunction_context(compare: MemorySegment, context: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("sortSubviewsUsingFunction:context:")
        ObjCRuntime.msgSend(null, ptr, sel, compare, context)
    }
    
    open fun viewWillMoveToWindow(newWindow: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("viewWillMoveToWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, newWindow)
    }
    
    open fun viewDidMoveToWindow(): Unit {
        val sel = ObjCRuntime.sel("viewDidMoveToWindow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun viewWillMoveToSuperview(newSuperview: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("viewWillMoveToSuperview:")
        ObjCRuntime.msgSend(null, ptr, sel, newSuperview)
    }
    
    open fun viewDidMoveToSuperview(): Unit {
        val sel = ObjCRuntime.sel("viewDidMoveToSuperview")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun didAddSubview(subview: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("didAddSubview:")
        ObjCRuntime.msgSend(null, ptr, sel, subview)
    }
    
    open fun willRemoveSubview(subview: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("willRemoveSubview:")
        ObjCRuntime.msgSend(null, ptr, sel, subview)
    }
    
    open fun removeFromSuperview(): Unit {
        val sel = ObjCRuntime.sel("removeFromSuperview")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun replaceSubview_with(oldView: MemorySegment, newView: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceSubview:with:")
        ObjCRuntime.msgSend(null, ptr, sel, oldView, newView)
    }
    
    open fun removeFromSuperviewWithoutNeedingDisplay(): Unit {
        val sel = ObjCRuntime.sel("removeFromSuperviewWithoutNeedingDisplay")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun viewDidChangeBackingProperties(): Unit {
        val sel = ObjCRuntime.sel("viewDidChangeBackingProperties")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resizeSubviewsWithOldSize(oldSize: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("resizeSubviewsWithOldSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(oldSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    open fun resizeWithOldSuperviewSize(oldSize: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("resizeWithOldSuperviewSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(oldSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    open fun setFrameOrigin(newOrigin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setFrameOrigin:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(newOrigin, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun setFrameSize(newSize: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setFrameSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(newSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    open fun setBoundsOrigin(newOrigin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setBoundsOrigin:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(newOrigin, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun setBoundsSize(newSize: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setBoundsSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(newSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    open fun translateOriginToPoint(translation: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("translateOriginToPoint:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(translation, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun scaleUnitSquareToSize(newUnitSize: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scaleUnitSquareToSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(newUnitSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    open fun rotateByAngle(angle: Double): Unit {
        val sel = ObjCRuntime.sel("rotateByAngle:")
        ObjCRuntime.msgSend(null, ptr, sel, angle)
    }
    
    open fun convertPoint_fromView(point: MemorySegment, view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertPoint:fromView:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), view) as MemorySegment
    }
    
    open fun convertPoint_toView(point: MemorySegment, view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertPoint:toView:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), view) as MemorySegment
    }
    
    open fun convertSize_fromView(size: MemorySegment, view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertSize:fromView:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), view) as MemorySegment
    }
    
    open fun convertSize_toView(size: MemorySegment, view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertSize:toView:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), view) as MemorySegment
    }
    
    open fun convertRect_fromView(rect: MemorySegment, view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRect:fromView:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), view) as MemorySegment
    }
    
    open fun convertRect_toView(rect: MemorySegment, view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRect:toView:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), view) as MemorySegment
    }
    
    open fun backingAlignedRect_options(rect: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("backingAlignedRect:options:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), options) as MemorySegment
    }
    
    open fun centerScanRect(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("centerScanRect:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun convertPointToBacking(point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertPointToBacking:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun convertPointFromBacking(point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertPointFromBacking:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun convertSizeToBacking(size: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertSizeToBacking:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"))) as MemorySegment
    }
    
    open fun convertSizeFromBacking(size: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertSizeFromBacking:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"))) as MemorySegment
    }
    
    open fun convertRectToBacking(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRectToBacking:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun convertRectFromBacking(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRectFromBacking:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun convertPointToLayer(point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertPointToLayer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun convertPointFromLayer(point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertPointFromLayer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun convertSizeToLayer(size: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertSizeToLayer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"))) as MemorySegment
    }
    
    open fun convertSizeFromLayer(size: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertSizeFromLayer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"))) as MemorySegment
    }
    
    open fun convertRectToLayer(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRectToLayer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun convertRectFromLayer(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRectFromLayer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun setNeedsDisplayInRect(invalidRect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setNeedsDisplayInRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(invalidRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun lockFocus(): Unit {
        val sel = ObjCRuntime.sel("lockFocus")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun unlockFocus(): Unit {
        val sel = ObjCRuntime.sel("unlockFocus")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun lockFocusIfCanDraw(): Boolean {
        val sel = ObjCRuntime.sel("lockFocusIfCanDraw")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun lockFocusIfCanDrawInContext(context: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("lockFocusIfCanDrawInContext:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, context) as Boolean
    }
    
    open fun display(): Unit {
        val sel = ObjCRuntime.sel("display")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun displayIfNeeded(): Unit {
        val sel = ObjCRuntime.sel("displayIfNeeded")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun displayIfNeededIgnoringOpacity(): Unit {
        val sel = ObjCRuntime.sel("displayIfNeededIgnoringOpacity")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun displayRect(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("displayRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun displayIfNeededInRect(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("displayIfNeededInRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun displayRectIgnoringOpacity(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("displayRectIgnoringOpacity:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun displayIfNeededInRectIgnoringOpacity(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("displayIfNeededInRectIgnoringOpacity:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun drawRect(dirtyRect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(dirtyRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun displayRectIgnoringOpacity_inContext(rect: MemorySegment, context: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("displayRectIgnoringOpacity:inContext:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), context)
    }
    
    open fun bitmapImageRepForCachingDisplayInRect(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("bitmapImageRepForCachingDisplayInRect:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun cacheDisplayInRect_toBitmapImageRep(rect: MemorySegment, bitmapImageRep: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("cacheDisplayInRect:toBitmapImageRep:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), bitmapImageRep)
    }
    
    open fun viewWillDraw(): Unit {
        val sel = ObjCRuntime.sel("viewWillDraw")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun scrollPoint(point: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scrollPoint:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun scrollRectToVisible(rect: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("scrollRectToVisible:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as Boolean
    }
    
    open fun autoscroll(event: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("autoscroll:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, event) as Boolean
    }
    
    open fun adjustScroll(newVisible: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("adjustScroll:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(newVisible, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun scrollRect_by(rect: MemorySegment, delta: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scrollRect:by:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(delta, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    open fun translateRectsNeedingDisplayInRect_by(clipRect: MemorySegment, delta: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("translateRectsNeedingDisplayInRect:by:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(clipRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(delta, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    open fun hitTest(point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("hitTest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun mouse_inRect(point: MemorySegment, rect: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("mouse:inRect:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as Boolean
    }
    
    open fun viewWithTag(tag: Long): MemorySegment {
        val sel = ObjCRuntime.sel("viewWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, tag) as MemorySegment
    }
    
    override fun performKeyEquivalent(event: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("performKeyEquivalent:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, event) as Boolean
    }
    
    open fun acceptsFirstMouse(event: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("acceptsFirstMouse:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, event) as Boolean
    }
    
    open fun shouldDelayWindowOrderingForEvent(event: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("shouldDelayWindowOrderingForEvent:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, event) as Boolean
    }
    
    open fun makeBackingLayer(): MemorySegment {
        val sel = ObjCRuntime.sel("makeBackingLayer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun updateLayer(): Unit {
        val sel = ObjCRuntime.sel("updateLayer")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun layoutSubtreeIfNeeded(): Unit {
        val sel = ObjCRuntime.sel("layoutSubtreeIfNeeded")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun layout(): Unit {
        val sel = ObjCRuntime.sel("layout")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun menuForEvent(event: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("menuForEvent:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, event) as MemorySegment
    }
    
    open fun willOpenMenu_withEvent(menu: MemorySegment, event: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("willOpenMenu:withEvent:")
        ObjCRuntime.msgSend(null, ptr, sel, menu, event)
    }
    
    open fun didCloseMenu_withEvent(menu: MemorySegment, event: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("didCloseMenu:withEvent:")
        ObjCRuntime.msgSend(null, ptr, sel, menu, event)
    }
    
    open fun addToolTipRect_owner_userData(rect: MemorySegment, owner: MemorySegment, `data`: MemorySegment): Long {
        val sel = ObjCRuntime.sel("addToolTipRect:owner:userData:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), owner, `data`) as Long
    }
    
    open fun removeToolTip(tag: Long): Unit {
        val sel = ObjCRuntime.sel("removeToolTip:")
        ObjCRuntime.msgSend(null, ptr, sel, tag)
    }
    
    open fun removeAllToolTips(): Unit {
        val sel = ObjCRuntime.sel("removeAllToolTips")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun viewWillStartLiveResize(): Unit {
        val sel = ObjCRuntime.sel("viewWillStartLiveResize")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun viewDidEndLiveResize(): Unit {
        val sel = ObjCRuntime.sel("viewDidEndLiveResize")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun getRectsExposedDuringLiveResize_count(exposedRects: MemorySegment, count: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getRectsExposedDuringLiveResize:count:")
        ObjCRuntime.msgSend(null, ptr, sel, exposedRects, count)
    }
    
    open fun rectForSmartMagnificationAtPoint_inRect(location: MemorySegment, visibleRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("rectForSmartMagnificationAtPoint:inRect:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(location, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), ObjCRuntime.ObjCStructArg(visibleRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun prepareForReuse(): Unit {
        val sel = ObjCRuntime.sel("prepareForReuse")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun prepareContentInRect(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("prepareContentInRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun viewDidChangeEffectiveAppearance(): Unit {
        val sel = ObjCRuntime.sel("viewDidChangeEffectiveAppearance")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property window
    open fun window(): MemorySegment {
        val sel = ObjCRuntime.sel("window")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property superview
    open fun superview(): MemorySegment {
        val sel = ObjCRuntime.sel("superview")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property subviews
    /** @return NSArray<__kindof NSView *> * */
    open fun subviews(): MemorySegment {
        val sel = ObjCRuntime.sel("subviews")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSubviews(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSubviews:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property opaqueAncestor
    open fun opaqueAncestor(): MemorySegment {
        val sel = ObjCRuntime.sel("opaqueAncestor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property hidden
    open fun isHidden(): Boolean {
        val sel = ObjCRuntime.sel("isHidden")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHidden(value: Boolean) {
        val sel = ObjCRuntime.sel("setHidden:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hiddenOrHasHiddenAncestor
    open fun isHiddenOrHasHiddenAncestor(): Boolean {
        val sel = ObjCRuntime.sel("isHiddenOrHasHiddenAncestor")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property wantsDefaultClipping
    open fun wantsDefaultClipping(): Boolean {
        val sel = ObjCRuntime.sel("wantsDefaultClipping")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property postsFrameChangedNotifications
    open fun postsFrameChangedNotifications(): Boolean {
        val sel = ObjCRuntime.sel("postsFrameChangedNotifications")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPostsFrameChangedNotifications(value: Boolean) {
        val sel = ObjCRuntime.sel("setPostsFrameChangedNotifications:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autoresizesSubviews
    open fun autoresizesSubviews(): Boolean {
        val sel = ObjCRuntime.sel("autoresizesSubviews")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutoresizesSubviews(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutoresizesSubviews:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autoresizingMask
    open fun autoresizingMask(): MemorySegment {
        val sel = ObjCRuntime.sel("autoresizingMask")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAutoresizingMask(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAutoresizingMask:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property frame
    open fun frame(): MemorySegment {
        val sel = ObjCRuntime.sel("frame")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    open fun setFrame(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFrame:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    // @property frameRotation
    open fun frameRotation(): Double {
        val sel = ObjCRuntime.sel("frameRotation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setFrameRotation(value: Double) {
        val sel = ObjCRuntime.sel("setFrameRotation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property frameCenterRotation
    open fun frameCenterRotation(): Double {
        val sel = ObjCRuntime.sel("frameCenterRotation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setFrameCenterRotation(value: Double) {
        val sel = ObjCRuntime.sel("setFrameCenterRotation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property boundsRotation
    open fun boundsRotation(): Double {
        val sel = ObjCRuntime.sel("boundsRotation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setBoundsRotation(value: Double) {
        val sel = ObjCRuntime.sel("setBoundsRotation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property bounds
    open fun bounds(): MemorySegment {
        val sel = ObjCRuntime.sel("bounds")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    open fun setBounds(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBounds:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    // @property flipped
    open fun isFlipped(): Boolean {
        val sel = ObjCRuntime.sel("isFlipped")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property rotatedFromBase
    open fun isRotatedFromBase(): Boolean {
        val sel = ObjCRuntime.sel("isRotatedFromBase")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property rotatedOrScaledFromBase
    open fun isRotatedOrScaledFromBase(): Boolean {
        val sel = ObjCRuntime.sel("isRotatedOrScaledFromBase")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property opaque
    open fun isOpaque(): Boolean {
        val sel = ObjCRuntime.sel("isOpaque")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canDrawConcurrently
    open fun canDrawConcurrently(): Boolean {
        val sel = ObjCRuntime.sel("canDrawConcurrently")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanDrawConcurrently(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanDrawConcurrently:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canDraw
    open fun canDraw(): Boolean {
        val sel = ObjCRuntime.sel("canDraw")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property needsDisplay
    open fun needsDisplay(): Boolean {
        val sel = ObjCRuntime.sel("needsDisplay")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setNeedsDisplay(value: Boolean) {
        val sel = ObjCRuntime.sel("setNeedsDisplay:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property focusView
    open fun focusView(): MemorySegment {
        val sel = ObjCRuntime.sel("focusView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property visibleRect
    open fun visibleRect(): MemorySegment {
        val sel = ObjCRuntime.sel("visibleRect")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property tag
    open fun tag(): Long {
        val sel = ObjCRuntime.sel("tag")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property needsPanelToBecomeKey
    open fun needsPanelToBecomeKey(): Boolean {
        val sel = ObjCRuntime.sel("needsPanelToBecomeKey")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property mouseDownCanMoveWindow
    open fun mouseDownCanMoveWindow(): Boolean {
        val sel = ObjCRuntime.sel("mouseDownCanMoveWindow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property acceptsTouchEvents
    open fun acceptsTouchEvents(): Boolean {
        val sel = ObjCRuntime.sel("acceptsTouchEvents")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAcceptsTouchEvents(value: Boolean) {
        val sel = ObjCRuntime.sel("setAcceptsTouchEvents:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property wantsRestingTouches
    open fun wantsRestingTouches(): Boolean {
        val sel = ObjCRuntime.sel("wantsRestingTouches")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setWantsRestingTouches(value: Boolean) {
        val sel = ObjCRuntime.sel("setWantsRestingTouches:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property layerContentsRedrawPolicy
    open fun layerContentsRedrawPolicy(): MemorySegment {
        val sel = ObjCRuntime.sel("layerContentsRedrawPolicy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLayerContentsRedrawPolicy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLayerContentsRedrawPolicy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property layerContentsPlacement
    open fun layerContentsPlacement(): MemorySegment {
        val sel = ObjCRuntime.sel("layerContentsPlacement")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLayerContentsPlacement(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLayerContentsPlacement:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property wantsLayer
    open fun wantsLayer(): Boolean {
        val sel = ObjCRuntime.sel("wantsLayer")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setWantsLayer(value: Boolean) {
        val sel = ObjCRuntime.sel("setWantsLayer:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property layer
    open fun layer(): MemorySegment {
        val sel = ObjCRuntime.sel("layer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLayer(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLayer:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property wantsUpdateLayer
    open fun wantsUpdateLayer(): Boolean {
        val sel = ObjCRuntime.sel("wantsUpdateLayer")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canDrawSubviewsIntoLayer
    open fun canDrawSubviewsIntoLayer(): Boolean {
        val sel = ObjCRuntime.sel("canDrawSubviewsIntoLayer")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanDrawSubviewsIntoLayer(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanDrawSubviewsIntoLayer:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property needsLayout
    open fun needsLayout(): Boolean {
        val sel = ObjCRuntime.sel("needsLayout")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setNeedsLayout(value: Boolean) {
        val sel = ObjCRuntime.sel("setNeedsLayout:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property alphaValue
    open fun alphaValue(): Double {
        val sel = ObjCRuntime.sel("alphaValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setAlphaValue(value: Double) {
        val sel = ObjCRuntime.sel("setAlphaValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property layerUsesCoreImageFilters
    open fun layerUsesCoreImageFilters(): Boolean {
        val sel = ObjCRuntime.sel("layerUsesCoreImageFilters")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setLayerUsesCoreImageFilters(value: Boolean) {
        val sel = ObjCRuntime.sel("setLayerUsesCoreImageFilters:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backgroundFilters
    /** @return NSArray<__kindof CIFilter *> * */
    open fun backgroundFilters(): MemorySegment {
        val sel = ObjCRuntime.sel("backgroundFilters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackgroundFilters(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackgroundFilters:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property compositingFilter
    open fun compositingFilter(): MemorySegment {
        val sel = ObjCRuntime.sel("compositingFilter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCompositingFilter(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCompositingFilter:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property contentFilters
    /** @return NSArray<__kindof CIFilter *> * */
    open fun contentFilters(): MemorySegment {
        val sel = ObjCRuntime.sel("contentFilters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContentFilters(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentFilters:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property shadow
    open fun shadow(): MemorySegment {
        val sel = ObjCRuntime.sel("shadow")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setShadow(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setShadow:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property clipsToBounds
    open fun clipsToBounds(): Boolean {
        val sel = ObjCRuntime.sel("clipsToBounds")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setClipsToBounds(value: Boolean) {
        val sel = ObjCRuntime.sel("setClipsToBounds:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property postsBoundsChangedNotifications
    open fun postsBoundsChangedNotifications(): Boolean {
        val sel = ObjCRuntime.sel("postsBoundsChangedNotifications")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPostsBoundsChangedNotifications(value: Boolean) {
        val sel = ObjCRuntime.sel("setPostsBoundsChangedNotifications:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property enclosingScrollView
    open fun enclosingScrollView(): MemorySegment {
        val sel = ObjCRuntime.sel("enclosingScrollView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property defaultMenu
    open fun defaultMenu(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultMenu")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property toolTip
    open fun toolTip(): MemorySegment {
        val sel = ObjCRuntime.sel("toolTip")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setToolTip(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setToolTip:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun toolTipAsString(): String = ObjCRuntime.toJavaString(toolTip())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setToolTip(value: String) = setToolTip(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property inLiveResize
    open fun inLiveResize(): Boolean {
        val sel = ObjCRuntime.sel("inLiveResize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property preservesContentDuringLiveResize
    open fun preservesContentDuringLiveResize(): Boolean {
        val sel = ObjCRuntime.sel("preservesContentDuringLiveResize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property rectPreservedDuringLiveResize
    open fun rectPreservedDuringLiveResize(): MemorySegment {
        val sel = ObjCRuntime.sel("rectPreservedDuringLiveResize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property inputContext
    open fun inputContext(): MemorySegment {
        val sel = ObjCRuntime.sel("inputContext")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property userInterfaceLayoutDirection
    open fun userInterfaceLayoutDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("userInterfaceLayoutDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUserInterfaceLayoutDirection(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUserInterfaceLayoutDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property compatibleWithResponsiveScrolling
    open fun isCompatibleWithResponsiveScrolling(): Boolean {
        val sel = ObjCRuntime.sel("isCompatibleWithResponsiveScrolling")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property preparedContentRect
    open fun preparedContentRect(): MemorySegment {
        val sel = ObjCRuntime.sel("preparedContentRect")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    open fun setPreparedContentRect(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPreparedContentRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    // @property allowsVibrancy
    open fun allowsVibrancy(): Boolean {
        val sel = ObjCRuntime.sel("allowsVibrancy")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSKeyboardUI on NSView ─────────────────────────────────────────

fun NSView.setKeyboardFocusRingNeedsDisplayInRect(rect: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setKeyboardFocusRingNeedsDisplayInRect:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect)
}

fun NSView.drawFocusRingMask(): Unit {
    val sel = ObjCRuntime.sel("drawFocusRingMask")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.noteFocusRingMaskChanged(): Unit {
    val sel = ObjCRuntime.sel("noteFocusRingMaskChanged")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.nextKeyView(): MemorySegment {
    val sel = ObjCRuntime.sel("nextKeyView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.setNextKeyView(nextKeyView: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setNextKeyView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, nextKeyView)
}

fun NSView.previousKeyView(): MemorySegment {
    val sel = ObjCRuntime.sel("previousKeyView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.nextValidKeyView(): MemorySegment {
    val sel = ObjCRuntime.sel("nextValidKeyView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.previousValidKeyView(): MemorySegment {
    val sel = ObjCRuntime.sel("previousValidKeyView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.canBecomeKeyView(): Boolean {
    val sel = ObjCRuntime.sel("canBecomeKeyView")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSView.focusRingType(): MemorySegment {
    val sel = ObjCRuntime.sel("focusRingType")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.setFocusRingType(focusRingType: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setFocusRingType:")
    ObjCRuntime.msgSend(null, this.ptr, sel, focusRingType)
}

fun NSView.focusRingMaskBounds(): MemorySegment {
    val sel = ObjCRuntime.sel("focusRingMaskBounds")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel) as MemorySegment
}

// Class method: +[NSView defaultFocusRingType]
fun NSView_defaultFocusRingType(): MemorySegment {
    val sel = ObjCRuntime.sel("defaultFocusRingType")
    val cls = ObjCRuntime.getClass("NSView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// @property defaultFocusRingType
fun NSView.defaultFocusRingType(): MemorySegment {
    val sel = ObjCRuntime.sel("defaultFocusRingType")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSPrinting on NSView ─────────────────────────────────────────

fun NSView.writeEPSInsideRect_toPasteboard(rect: MemorySegment, pasteboard: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("writeEPSInsideRect:toPasteboard:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, pasteboard)
}

fun NSView.dataWithEPSInsideRect(rect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithEPSInsideRect:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, rect) as MemorySegment
}

fun NSView.writePDFInsideRect_toPasteboard(rect: MemorySegment, pasteboard: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("writePDFInsideRect:toPasteboard:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, pasteboard)
}

fun NSView.dataWithPDFInsideRect(rect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithPDFInsideRect:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, rect) as MemorySegment
}

fun NSView.print(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("print:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSView.knowsPageRange(range: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("knowsPageRange:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, range) as Boolean
}

fun NSView.adjustPageWidthNew_left_right_limit(newRight: MemorySegment, oldLeft: Double, oldRight: Double, rightLimit: Double): Unit {
    val sel = ObjCRuntime.sel("adjustPageWidthNew:left:right:limit:")
    ObjCRuntime.msgSend(null, this.ptr, sel, newRight, oldLeft, oldRight, rightLimit)
}

fun NSView.adjustPageHeightNew_top_bottom_limit(newBottom: MemorySegment, oldTop: Double, oldBottom: Double, bottomLimit: Double): Unit {
    val sel = ObjCRuntime.sel("adjustPageHeightNew:top:bottom:limit:")
    ObjCRuntime.msgSend(null, this.ptr, sel, newBottom, oldTop, oldBottom, bottomLimit)
}

fun NSView.rectForPage(page: Long): MemorySegment {
    val sel = ObjCRuntime.sel("rectForPage:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, page) as MemorySegment
}

fun NSView.locationOfPrintRect(rect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("locationOfPrintRect:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel, rect) as MemorySegment
}

fun NSView.drawPageBorderWithSize(borderSize: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("drawPageBorderWithSize:")
    ObjCRuntime.msgSend(null, this.ptr, sel, borderSize)
}

fun NSView.drawSheetBorderWithSize(borderSize: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("drawSheetBorderWithSize:")
    ObjCRuntime.msgSend(null, this.ptr, sel, borderSize)
}

fun NSView.beginDocument(): Unit {
    val sel = ObjCRuntime.sel("beginDocument")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.endDocument(): Unit {
    val sel = ObjCRuntime.sel("endDocument")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.beginPageInRect_atPlacement(rect: MemorySegment, location: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("beginPageInRect:atPlacement:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, location)
}

fun NSView.endPage(): Unit {
    val sel = ObjCRuntime.sel("endPage")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.heightAdjustLimit(): Double {
    val sel = ObjCRuntime.sel("heightAdjustLimit")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSView.widthAdjustLimit(): Double {
    val sel = ObjCRuntime.sel("widthAdjustLimit")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSView.pageHeader(): MemorySegment {
    val sel = ObjCRuntime.sel("pageHeader")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.pageFooter(): MemorySegment {
    val sel = ObjCRuntime.sel("pageFooter")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.printJobTitle(): MemorySegment {
    val sel = ObjCRuntime.sel("printJobTitle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSDrag on NSView ─────────────────────────────────────────

fun NSView.beginDraggingSessionWithItems_event_source(items: MemorySegment, event: MemorySegment, source: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("beginDraggingSessionWithItems:event:source:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, items, event, source) as MemorySegment
}

fun NSView.registerForDraggedTypes(newTypes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("registerForDraggedTypes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, newTypes)
}

fun NSView.unregisterDraggedTypes(): Unit {
    val sel = ObjCRuntime.sel("unregisterDraggedTypes")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

/** @return NSArray<NSPasteboardType> * */
fun NSView.registeredDraggedTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("registeredDraggedTypes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSFullScreenMode on NSView ─────────────────────────────────────────

fun NSView.enterFullScreenMode_withOptions(screen: MemorySegment, options: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("enterFullScreenMode:withOptions:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, screen, options) as Boolean
}

fun NSView.exitFullScreenModeWithOptions(options: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("exitFullScreenModeWithOptions:")
    ObjCRuntime.msgSend(null, this.ptr, sel, options)
}

fun NSView.isInFullScreenMode(): Boolean {
    val sel = ObjCRuntime.sel("isInFullScreenMode")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSDefinition on NSView ─────────────────────────────────────────

fun NSView.showDefinitionForAttributedString_atPoint(attrString: MemorySegment, textBaselineOrigin: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("showDefinitionForAttributedString:atPoint:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attrString, textBaselineOrigin)
}

fun NSView.showDefinitionForAttributedString_range_options_baselineOriginProvider(attrString: MemorySegment, targetRange: MemorySegment, options: MemorySegment, originProvider: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("showDefinitionForAttributedString:range:options:baselineOriginProvider:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attrString, targetRange, options, originProvider)
}

// ── Category: NSFindIndicator on NSView ─────────────────────────────────────────

fun NSView.isDrawingFindIndicator(): Boolean {
    val sel = ObjCRuntime.sel("isDrawingFindIndicator")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSGestureRecognizer on NSView ─────────────────────────────────────────

fun NSView.addGestureRecognizer(gestureRecognizer: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addGestureRecognizer:")
    ObjCRuntime.msgSend(null, this.ptr, sel, gestureRecognizer)
}

fun NSView.removeGestureRecognizer(gestureRecognizer: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeGestureRecognizer:")
    ObjCRuntime.msgSend(null, this.ptr, sel, gestureRecognizer)
}

/** @return NSArray<__kindof NSGestureRecognizer *> * */
fun NSView.gestureRecognizers(): MemorySegment {
    val sel = ObjCRuntime.sel("gestureRecognizers")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.setGestureRecognizers(gestureRecognizers: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setGestureRecognizers:")
    ObjCRuntime.msgSend(null, this.ptr, sel, gestureRecognizers)
}

// ── Category: NSTouchBar on NSView ─────────────────────────────────────────

fun NSView.allowedTouchTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("allowedTouchTypes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.setAllowedTouchTypes(allowedTouchTypes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAllowedTouchTypes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, allowedTouchTypes)
}

// ── Category: NSSafeAreas on NSView ─────────────────────────────────────────

fun NSView.safeAreaInsets(): MemorySegment {
    val sel = ObjCRuntime.sel("safeAreaInsets")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), this.ptr, sel) as MemorySegment
}

fun NSView.additionalSafeAreaInsets(): MemorySegment {
    val sel = ObjCRuntime.sel("additionalSafeAreaInsets")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), this.ptr, sel) as MemorySegment
}

fun NSView.setAdditionalSafeAreaInsets(additionalSafeAreaInsets: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAdditionalSafeAreaInsets:")
    ObjCRuntime.msgSend(null, this.ptr, sel, additionalSafeAreaInsets)
}

fun NSView.safeAreaLayoutGuide(): MemorySegment {
    val sel = ObjCRuntime.sel("safeAreaLayoutGuide")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.safeAreaRect(): MemorySegment {
    val sel = ObjCRuntime.sel("safeAreaRect")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel) as MemorySegment
}

fun NSView.layoutMarginsGuide(): MemorySegment {
    val sel = ObjCRuntime.sel("layoutMarginsGuide")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSCompactControlSizeMetrics on NSView ─────────────────────────────────────────

fun NSView.prefersCompactControlSizeMetrics(): Boolean {
    val sel = ObjCRuntime.sel("prefersCompactControlSizeMetrics")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSView.setPrefersCompactControlSizeMetrics(prefersCompactControlSizeMetrics: Boolean): Unit {
    val sel = ObjCRuntime.sel("setPrefersCompactControlSizeMetrics:")
    ObjCRuntime.msgSend(null, this.ptr, sel, prefersCompactControlSizeMetrics)
}

// ── Category: NSTrackingArea on NSView ─────────────────────────────────────────

fun NSView.addTrackingArea(trackingArea: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addTrackingArea:")
    ObjCRuntime.msgSend(null, this.ptr, sel, trackingArea)
}

fun NSView.removeTrackingArea(trackingArea: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeTrackingArea:")
    ObjCRuntime.msgSend(null, this.ptr, sel, trackingArea)
}

fun NSView.updateTrackingAreas(): Unit {
    val sel = ObjCRuntime.sel("updateTrackingAreas")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.addCursorRect_cursor(rect: MemorySegment, `object`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addCursorRect:cursor:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, `object`)
}

fun NSView.removeCursorRect_cursor(rect: MemorySegment, `object`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeCursorRect:cursor:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, `object`)
}

fun NSView.discardCursorRects(): Unit {
    val sel = ObjCRuntime.sel("discardCursorRects")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.resetCursorRects(): Unit {
    val sel = ObjCRuntime.sel("resetCursorRects")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.addTrackingRect_owner_userData_assumeInside(rect: MemorySegment, owner: MemorySegment, `data`: MemorySegment, flag: Boolean): Long {
    val sel = ObjCRuntime.sel("addTrackingRect:owner:userData:assumeInside:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, rect, owner, `data`, flag) as Long
}

fun NSView.removeTrackingRect(tag: Long): Unit {
    val sel = ObjCRuntime.sel("removeTrackingRect:")
    ObjCRuntime.msgSend(null, this.ptr, sel, tag)
}

/** @return NSArray<NSTrackingArea *> * */
fun NSView.trackingAreas(): MemorySegment {
    val sel = ObjCRuntime.sel("trackingAreas")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSDisplayLink on NSView ─────────────────────────────────────────

fun NSView.displayLinkWithTarget_selector(target: MemorySegment, selector: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("displayLinkWithTarget:selector:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, target, selector) as MemorySegment
}

// ── Category: NSDeprecated on NSView ─────────────────────────────────────────

fun NSView.dragImage_at_offset_event_pasteboard_source_slideBack(image: MemorySegment, viewLocation: MemorySegment, initialOffset: MemorySegment, event: MemorySegment, pboard: MemorySegment, sourceObj: MemorySegment, slideFlag: Boolean): Unit {
    val sel = ObjCRuntime.sel("dragImage:at:offset:event:pasteboard:source:slideBack:")
    ObjCRuntime.msgSend(null, this.ptr, sel, image, viewLocation, initialOffset, event, pboard, sourceObj, slideFlag)
}

fun NSView.dragFile_fromRect_slideBack_event(filename: MemorySegment, rect: MemorySegment, flag: Boolean, event: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("dragFile:fromRect:slideBack:event:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, filename, rect, flag, event) as Boolean
}

fun NSView.dragPromisedFilesOfTypes_fromRect_source_slideBack_event(typeArray: MemorySegment, rect: MemorySegment, sourceObject: MemorySegment, flag: Boolean, event: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("dragPromisedFilesOfTypes:fromRect:source:slideBack:event:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, typeArray, rect, sourceObject, flag, event) as Boolean
}

fun NSView.convertPointToBase(point: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("convertPointToBase:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel, point) as MemorySegment
}

fun NSView.convertPointFromBase(point: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("convertPointFromBase:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel, point) as MemorySegment
}

fun NSView.convertSizeToBase(size: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("convertSizeToBase:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel, size) as MemorySegment
}

fun NSView.convertSizeFromBase(size: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("convertSizeFromBase:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel, size) as MemorySegment
}

fun NSView.convertRectToBase(rect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("convertRectToBase:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, rect) as MemorySegment
}

fun NSView.convertRectFromBase(rect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("convertRectFromBase:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, rect) as MemorySegment
}

fun NSView.performMnemonic(string: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("performMnemonic:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, string) as Boolean
}

fun NSView.shouldDrawColor(): Boolean {
    val sel = ObjCRuntime.sel("shouldDrawColor")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSView.gState(): Long {
    val sel = ObjCRuntime.sel("gState")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSView.allocateGState(): Unit {
    val sel = ObjCRuntime.sel("allocateGState")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.releaseGState(): Unit {
    val sel = ObjCRuntime.sel("releaseGState")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.setUpGState(): Unit {
    val sel = ObjCRuntime.sel("setUpGState")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.renewGState(): Unit {
    val sel = ObjCRuntime.sel("renewGState")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

// ── Category: NSWritingToolsCoordinator on NSView ─────────────────────────────────────────

fun NSView.writingToolsCoordinator(): MemorySegment {
    val sel = ObjCRuntime.sel("writingToolsCoordinator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.setWritingToolsCoordinator(writingToolsCoordinator: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setWritingToolsCoordinator:")
    ObjCRuntime.msgSend(null, this.ptr, sel, writingToolsCoordinator)
}

// ── Category: NSViewEnclosingMenuItem on NSView ─────────────────────────────────────────

fun NSView.enclosingMenuItem(): MemorySegment {
    val sel = ObjCRuntime.sel("enclosingMenuItem")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSCandidateListTouchBarItem on NSView ─────────────────────────────────────────

fun NSView.candidateListTouchBarItem(): MemorySegment {
    val sel = ObjCRuntime.sel("candidateListTouchBarItem")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSClipViewSuperview on NSView ─────────────────────────────────────────

fun NSView.reflectScrolledClipView(clipView: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("reflectScrolledClipView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, clipView)
}

fun NSView.scrollClipView_toPoint(clipView: MemorySegment, point: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("scrollClipView:toPoint:")
    ObjCRuntime.msgSend(null, this.ptr, sel, clipView, point)
}

// ── Category: NSConstraintBasedLayoutInstallingConstraints on NSView ─────────────────────────────────────────

fun NSView.addConstraint(constraint: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addConstraint:")
    ObjCRuntime.msgSend(null, this.ptr, sel, constraint)
}

fun NSView.addConstraints(constraints: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addConstraints:")
    ObjCRuntime.msgSend(null, this.ptr, sel, constraints)
}

fun NSView.removeConstraint(constraint: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeConstraint:")
    ObjCRuntime.msgSend(null, this.ptr, sel, constraint)
}

fun NSView.removeConstraints(constraints: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeConstraints:")
    ObjCRuntime.msgSend(null, this.ptr, sel, constraints)
}

fun NSView.leadingAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("leadingAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.trailingAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("trailingAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.leftAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("leftAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.rightAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("rightAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.topAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("topAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.bottomAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("bottomAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.widthAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("widthAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.heightAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("heightAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.centerXAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("centerXAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.centerYAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("centerYAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.firstBaselineAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("firstBaselineAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.lastBaselineAnchor(): MemorySegment {
    val sel = ObjCRuntime.sel("lastBaselineAnchor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<NSLayoutConstraint *> * */
fun NSView.constraints(): MemorySegment {
    val sel = ObjCRuntime.sel("constraints")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSConstraintBasedLayoutCoreMethods on NSView ─────────────────────────────────────────

fun NSView.updateConstraintsForSubtreeIfNeeded(): Unit {
    val sel = ObjCRuntime.sel("updateConstraintsForSubtreeIfNeeded")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.updateConstraints(): Unit {
    val sel = ObjCRuntime.sel("updateConstraints")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.needsUpdateConstraints(): Boolean {
    val sel = ObjCRuntime.sel("needsUpdateConstraints")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSView.setNeedsUpdateConstraints(needsUpdateConstraints: Boolean): Unit {
    val sel = ObjCRuntime.sel("setNeedsUpdateConstraints:")
    ObjCRuntime.msgSend(null, this.ptr, sel, needsUpdateConstraints)
}

// ── Category: NSConstraintBasedCompatibility on NSView ─────────────────────────────────────────

fun NSView.translatesAutoresizingMaskIntoConstraints(): Boolean {
    val sel = ObjCRuntime.sel("translatesAutoresizingMaskIntoConstraints")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSView.setTranslatesAutoresizingMaskIntoConstraints(translatesAutoresizingMaskIntoConstraints: Boolean): Unit {
    val sel = ObjCRuntime.sel("setTranslatesAutoresizingMaskIntoConstraints:")
    ObjCRuntime.msgSend(null, this.ptr, sel, translatesAutoresizingMaskIntoConstraints)
}

// Class method: +[NSView requiresConstraintBasedLayout]
fun NSView_requiresConstraintBasedLayout(): Boolean {
    val sel = ObjCRuntime.sel("requiresConstraintBasedLayout")
    val cls = ObjCRuntime.getClass("NSView")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, cls, sel) as Boolean
}

// @property requiresConstraintBasedLayout
fun NSView.requiresConstraintBasedLayout(): Boolean {
    val sel = ObjCRuntime.sel("requiresConstraintBasedLayout")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSConstraintBasedLayoutLayering on NSView ─────────────────────────────────────────

fun NSView.alignmentRectForFrame(frame: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("alignmentRectForFrame:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, frame) as MemorySegment
}

fun NSView.frameForAlignmentRect(alignmentRect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("frameForAlignmentRect:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, alignmentRect) as MemorySegment
}

fun NSView.invalidateIntrinsicContentSize(): Unit {
    val sel = ObjCRuntime.sel("invalidateIntrinsicContentSize")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.contentHuggingPriorityForOrientation(orientation: MemorySegment): Float {
    val sel = ObjCRuntime.sel("contentHuggingPriorityForOrientation:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel, orientation) as Float
}

fun NSView.setContentHuggingPriority_forOrientation(priority: Float, orientation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setContentHuggingPriority:forOrientation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, priority, orientation)
}

fun NSView.contentCompressionResistancePriorityForOrientation(orientation: MemorySegment): Float {
    val sel = ObjCRuntime.sel("contentCompressionResistancePriorityForOrientation:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel, orientation) as Float
}

fun NSView.setContentCompressionResistancePriority_forOrientation(priority: Float, orientation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setContentCompressionResistancePriority:forOrientation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, priority, orientation)
}

fun NSView.alignmentRectInsets(): MemorySegment {
    val sel = ObjCRuntime.sel("alignmentRectInsets")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), this.ptr, sel) as MemorySegment
}

fun NSView.firstBaselineOffsetFromTop(): Double {
    val sel = ObjCRuntime.sel("firstBaselineOffsetFromTop")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSView.lastBaselineOffsetFromBottom(): Double {
    val sel = ObjCRuntime.sel("lastBaselineOffsetFromBottom")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSView.baselineOffsetFromBottom(): Double {
    val sel = ObjCRuntime.sel("baselineOffsetFromBottom")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSView.intrinsicContentSize(): MemorySegment {
    val sel = ObjCRuntime.sel("intrinsicContentSize")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel) as MemorySegment
}

fun NSView.isHorizontalContentSizeConstraintActive(): Boolean {
    val sel = ObjCRuntime.sel("isHorizontalContentSizeConstraintActive")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSView.setHorizontalContentSizeConstraintActive(horizontalContentSizeConstraintActive: Boolean): Unit {
    val sel = ObjCRuntime.sel("setHorizontalContentSizeConstraintActive:")
    ObjCRuntime.msgSend(null, this.ptr, sel, horizontalContentSizeConstraintActive)
}

fun NSView.isVerticalContentSizeConstraintActive(): Boolean {
    val sel = ObjCRuntime.sel("isVerticalContentSizeConstraintActive")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSView.setVerticalContentSizeConstraintActive(verticalContentSizeConstraintActive: Boolean): Unit {
    val sel = ObjCRuntime.sel("setVerticalContentSizeConstraintActive:")
    ObjCRuntime.msgSend(null, this.ptr, sel, verticalContentSizeConstraintActive)
}

// ── Category: NSConstraintBasedLayoutFittingSize on NSView ─────────────────────────────────────────

fun NSView.fittingSize(): MemorySegment {
    val sel = ObjCRuntime.sel("fittingSize")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel) as MemorySegment
}

// ── Category: NSConstraintBasedLayoutDebugging on NSView ─────────────────────────────────────────

/** @return NSArray<NSLayoutConstraint *> * */
fun NSView.constraintsAffectingLayoutForOrientation(orientation: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("constraintsAffectingLayoutForOrientation:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, orientation) as MemorySegment
}

fun NSView.exerciseAmbiguityInLayout(): Unit {
    val sel = ObjCRuntime.sel("exerciseAmbiguityInLayout")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSView.hasAmbiguousLayout(): Boolean {
    val sel = ObjCRuntime.sel("hasAmbiguousLayout")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSLayoutGuideSupport on NSView ─────────────────────────────────────────

fun NSView.addLayoutGuide(guide: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addLayoutGuide:")
    ObjCRuntime.msgSend(null, this.ptr, sel, guide)
}

fun NSView.removeLayoutGuide(guide: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeLayoutGuide:")
    ObjCRuntime.msgSend(null, this.ptr, sel, guide)
}

/** @return NSArray<NSLayoutGuide *> * */
fun NSView.layoutGuides(): MemorySegment {
    val sel = ObjCRuntime.sel("layoutGuides")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: LayoutRegions on NSView ─────────────────────────────────────────

fun NSView.layoutGuideForLayoutRegion(layoutRegion: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("layoutGuideForLayoutRegion:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, layoutRegion) as MemorySegment
}

fun NSView.edgeInsetsForLayoutRegion(layoutRegion: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("edgeInsetsForLayoutRegion:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), this.ptr, sel, layoutRegion) as MemorySegment
}

fun NSView.rectForLayoutRegion(layoutRegion: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("rectForLayoutRegion:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, layoutRegion) as MemorySegment
}

// ── Category: NSRulerMarkerClientViewDelegation on NSView ─────────────────────────────────────────

fun NSView.rulerView_shouldMoveMarker(ruler: MemorySegment, marker: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("rulerView:shouldMoveMarker:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, ruler, marker) as Boolean
}

fun NSView.rulerView_willMoveMarker_toLocation(ruler: MemorySegment, marker: MemorySegment, location: Double): Double {
    val sel = ObjCRuntime.sel("rulerView:willMoveMarker:toLocation:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel, ruler, marker, location) as Double
}

fun NSView.rulerView_didMoveMarker(ruler: MemorySegment, marker: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("rulerView:didMoveMarker:")
    ObjCRuntime.msgSend(null, this.ptr, sel, ruler, marker)
}

fun NSView.rulerView_shouldRemoveMarker(ruler: MemorySegment, marker: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("rulerView:shouldRemoveMarker:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, ruler, marker) as Boolean
}

fun NSView.rulerView_didRemoveMarker(ruler: MemorySegment, marker: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("rulerView:didRemoveMarker:")
    ObjCRuntime.msgSend(null, this.ptr, sel, ruler, marker)
}

fun NSView.rulerView_shouldAddMarker(ruler: MemorySegment, marker: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("rulerView:shouldAddMarker:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, ruler, marker) as Boolean
}

fun NSView.rulerView_willAddMarker_atLocation(ruler: MemorySegment, marker: MemorySegment, location: Double): Double {
    val sel = ObjCRuntime.sel("rulerView:willAddMarker:atLocation:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel, ruler, marker, location) as Double
}

fun NSView.rulerView_didAddMarker(ruler: MemorySegment, marker: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("rulerView:didAddMarker:")
    ObjCRuntime.msgSend(null, this.ptr, sel, ruler, marker)
}

fun NSView.rulerView_handleMouseDown(ruler: MemorySegment, event: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("rulerView:handleMouseDown:")
    ObjCRuntime.msgSend(null, this.ptr, sel, ruler, event)
}

fun NSView.rulerView_willSetClientView(ruler: MemorySegment, newClient: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("rulerView:willSetClientView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, ruler, newClient)
}

fun NSView.rulerView_locationForPoint(ruler: MemorySegment, point: MemorySegment): Double {
    val sel = ObjCRuntime.sel("rulerView:locationForPoint:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel, ruler, point) as Double
}

fun NSView.rulerView_pointForLocation(ruler: MemorySegment, point: Double): MemorySegment {
    val sel = ObjCRuntime.sel("rulerView:pointForLocation:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel, ruler, point) as MemorySegment
}

// ── Category: NSOpenGLSurfaceResolution on NSView ─────────────────────────────────────────

fun NSView.wantsBestResolutionOpenGLSurface(): Boolean {
    val sel = ObjCRuntime.sel("wantsBestResolutionOpenGLSurface")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSView.setWantsBestResolutionOpenGLSurface(wantsBestResolutionOpenGLSurface: Boolean): Unit {
    val sel = ObjCRuntime.sel("setWantsBestResolutionOpenGLSurface:")
    ObjCRuntime.msgSend(null, this.ptr, sel, wantsBestResolutionOpenGLSurface)
}

// ── Category: NSExtendedDynamicRange on NSView ─────────────────────────────────────────

fun NSView.wantsExtendedDynamicRangeOpenGLSurface(): Boolean {
    val sel = ObjCRuntime.sel("wantsExtendedDynamicRangeOpenGLSurface")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSView.setWantsExtendedDynamicRangeOpenGLSurface(wantsExtendedDynamicRangeOpenGLSurface: Boolean): Unit {
    val sel = ObjCRuntime.sel("setWantsExtendedDynamicRangeOpenGLSurface:")
    ObjCRuntime.msgSend(null, this.ptr, sel, wantsExtendedDynamicRangeOpenGLSurface)
}

// ── Category: NSPressureConfiguration on NSView ─────────────────────────────────────────

fun NSView.pressureConfiguration(): MemorySegment {
    val sel = ObjCRuntime.sel("pressureConfiguration")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSView.setPressureConfiguration(pressureConfiguration: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setPressureConfiguration:")
    ObjCRuntime.msgSend(null, this.ptr, sel, pressureConfiguration)
}

