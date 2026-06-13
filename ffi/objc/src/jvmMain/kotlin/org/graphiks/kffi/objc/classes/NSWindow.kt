package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSWindow
 * Superclass: NSResponder
 * Protocols: NSAnimatablePropertyContainer, NSMenuItemValidation, NSUserInterfaceValidations, NSUserInterfaceItemIdentification, NSAppearanceCustomization, NSAccessibilityElement, NSAccessibility
 */
open class NSWindow(override val ptr: MemorySegment) : NSResponder(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSWindow") }
        
        fun frameRectForContentRect_styleMask(cRect: MemorySegment, style: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("frameRectForContentRect:styleMask:")
            return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), _class, sel, ObjCRuntime.ObjCStructArg(cRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), style) as MemorySegment
        }
        
        fun contentRectForFrameRect_styleMask(fRect: MemorySegment, style: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("contentRectForFrameRect:styleMask:")
            return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), _class, sel, ObjCRuntime.ObjCStructArg(fRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), style) as MemorySegment
        }
        
        fun minFrameWidthWithTitle_styleMask(title: MemorySegment, style: MemorySegment): Double {
            val sel = ObjCRuntime.sel("minFrameWidthWithTitle:styleMask:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel, title, style) as Double
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun minFrameWidthWithTitle_styleMask(title: String, style: MemorySegment): Double = minFrameWidthWithTitle_styleMask(ObjCRuntime.newNSString(Arena.global(), title), style)
        
        fun removeFrameUsingName(name: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("removeFrameUsingName:")
            ObjCRuntime.msgSend(null, _class, sel, name)
        }
        
        fun standardWindowButton_forStyleMask(b: MemorySegment, styleMask: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("standardWindowButton:forStyleMask:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, b, styleMask) as MemorySegment
        }
        
        /** @return NSArray<NSNumber *> * */
        fun windowNumbersWithOptions(options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("windowNumbersWithOptions:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, options) as MemorySegment
        }
        
        fun windowNumberAtPoint_belowWindowWithWindowNumber(point: MemorySegment, windowNumber: Long): Long {
            val sel = ObjCRuntime.sel("windowNumberAtPoint:belowWindowWithWindowNumber:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, _class, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), windowNumber) as Long
        }
        
        fun windowWithContentViewController(contentViewController: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("windowWithContentViewController:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, contentViewController) as MemorySegment
        }
        
        fun defaultDepthLimit(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultDepthLimit")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun allowsAutomaticWindowTabbing(): Boolean {
            val sel = ObjCRuntime.sel("allowsAutomaticWindowTabbing")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun setAllowsAutomaticWindowTabbing(allowsAutomaticWindowTabbing: Boolean): Unit {
            val sel = ObjCRuntime.sel("setAllowsAutomaticWindowTabbing:")
            ObjCRuntime.msgSend(null, _class, sel, allowsAutomaticWindowTabbing)
        }
        
        fun userTabbingPreference(): MemorySegment {
            val sel = ObjCRuntime.sel("userTabbingPreference")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun frameRectForContentRect(contentRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("frameRectForContentRect:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(contentRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun contentRectForFrameRect(frameRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("contentRectForFrameRect:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun initWithContentRect_styleMask_backing_defer(contentRect: MemorySegment, style: MemorySegment, backingStoreType: MemorySegment, flag: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentRect:styleMask:backing:defer:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(contentRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), style, backingStoreType, flag) as MemorySegment
    }
    
    open fun initWithContentRect_styleMask_backing_defer_screen(contentRect: MemorySegment, style: MemorySegment, backingStoreType: MemorySegment, flag: Boolean, screen: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentRect:styleMask:backing:defer:screen:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(contentRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), style, backingStoreType, flag, screen) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun addTitlebarAccessoryViewController(childViewController: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTitlebarAccessoryViewController:")
        ObjCRuntime.msgSend(null, ptr, sel, childViewController)
    }
    
    open fun insertTitlebarAccessoryViewController_atIndex(childViewController: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertTitlebarAccessoryViewController:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, childViewController, index)
    }
    
    open fun removeTitlebarAccessoryViewControllerAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeTitlebarAccessoryViewControllerAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun setTitleWithRepresentedFilename(filename: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTitleWithRepresentedFilename:")
        ObjCRuntime.msgSend(null, ptr, sel, filename)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setTitleWithRepresentedFilename(filename: String): Unit = setTitleWithRepresentedFilename(ObjCRuntime.newNSString(Arena.global(), filename))
    
    open fun fieldEditor_forObject(createFlag: Boolean, `object`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("fieldEditor:forObject:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, createFlag, `object`) as MemorySegment
    }
    
    open fun endEditingFor(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("endEditingFor:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun constrainFrameRect_toScreen(frameRect: MemorySegment, screen: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("constrainFrameRect:toScreen:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), screen) as MemorySegment
    }
    
    open fun setFrame_display(frameRect: MemorySegment, flag: Boolean): Unit {
        val sel = ObjCRuntime.sel("setFrame:display:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), flag)
    }
    
    open fun setContentSize(size: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setContentSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    open fun setFrameOrigin(point: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setFrameOrigin:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun setFrameTopLeftPoint(point: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setFrameTopLeftPoint:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun cascadeTopLeftFromPoint(topLeftPoint: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("cascadeTopLeftFromPoint:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, ObjCRuntime.ObjCStructArg(topLeftPoint, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun animationResizeTime(newFrame: MemorySegment): Double {
        val sel = ObjCRuntime.sel("animationResizeTime:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, ObjCRuntime.ObjCStructArg(newFrame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as Double
    }
    
    open fun setFrame_display_animate(frameRect: MemorySegment, displayFlag: Boolean, animateFlag: Boolean): Unit {
        val sel = ObjCRuntime.sel("setFrame:display:animate:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), displayFlag, animateFlag)
    }
    
    open fun displayIfNeeded(): Unit {
        val sel = ObjCRuntime.sel("displayIfNeeded")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun display(): Unit {
        val sel = ObjCRuntime.sel("display")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun update(): Unit {
        val sel = ObjCRuntime.sel("update")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun makeFirstResponder(responder: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("makeFirstResponder:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, responder) as Boolean
    }
    
    open fun close(): Unit {
        val sel = ObjCRuntime.sel("close")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun miniaturize(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("miniaturize:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun deminiaturize(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("deminiaturize:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun zoom(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("zoom:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    override fun tryToPerform_with(action: MemorySegment, `object`: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("tryToPerform:with:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, action, `object`) as Boolean
    }
    
    override fun validRequestorForSendType_returnType(sendType: MemorySegment, returnType: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("validRequestorForSendType:returnType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, sendType, returnType) as MemorySegment
    }
    
    open fun setContentBorderThickness_forEdge(thickness: Double, edge: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setContentBorderThickness:forEdge:")
        ObjCRuntime.msgSend(null, ptr, sel, thickness, edge)
    }
    
    open fun contentBorderThicknessForEdge(edge: MemorySegment): Double {
        val sel = ObjCRuntime.sel("contentBorderThicknessForEdge:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, edge) as Double
    }
    
    open fun setAutorecalculatesContentBorderThickness_forEdge(flag: Boolean, edge: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setAutorecalculatesContentBorderThickness:forEdge:")
        ObjCRuntime.msgSend(null, ptr, sel, flag, edge)
    }
    
    open fun autorecalculatesContentBorderThicknessForEdge(edge: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("autorecalculatesContentBorderThicknessForEdge:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, edge) as Boolean
    }
    
    open fun center(): Unit {
        val sel = ObjCRuntime.sel("center")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun makeKeyAndOrderFront(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("makeKeyAndOrderFront:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun orderFront(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("orderFront:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun orderBack(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("orderBack:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun orderOut(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("orderOut:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun orderWindow_relativeTo(place: MemorySegment, otherWin: Long): Unit {
        val sel = ObjCRuntime.sel("orderWindow:relativeTo:")
        ObjCRuntime.msgSend(null, ptr, sel, place, otherWin)
    }
    
    open fun orderFrontRegardless(): Unit {
        val sel = ObjCRuntime.sel("orderFrontRegardless")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun makeKeyWindow(): Unit {
        val sel = ObjCRuntime.sel("makeKeyWindow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun makeMainWindow(): Unit {
        val sel = ObjCRuntime.sel("makeMainWindow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun becomeKeyWindow(): Unit {
        val sel = ObjCRuntime.sel("becomeKeyWindow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resignKeyWindow(): Unit {
        val sel = ObjCRuntime.sel("resignKeyWindow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun becomeMainWindow(): Unit {
        val sel = ObjCRuntime.sel("becomeMainWindow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resignMainWindow(): Unit {
        val sel = ObjCRuntime.sel("resignMainWindow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun convertRectToScreen(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRectToScreen:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun convertRectFromScreen(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRectFromScreen:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun convertPointToScreen(point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertPointToScreen:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun convertPointFromScreen(point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertPointFromScreen:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun convertRectToBacking(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRectToBacking:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun convertRectFromBacking(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("convertRectFromBacking:")
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
    
    open fun backingAlignedRect_options(rect: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("backingAlignedRect:options:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), options) as MemorySegment
    }
    
    open fun performClose(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performClose:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun performMiniaturize(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performMiniaturize:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun performZoom(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performZoom:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun dataWithEPSInsideRect(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dataWithEPSInsideRect:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun dataWithPDFInsideRect(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dataWithPDFInsideRect:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun print(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("print:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun setDynamicDepthLimit(flag: Boolean): Unit {
        val sel = ObjCRuntime.sel("setDynamicDepthLimit:")
        ObjCRuntime.msgSend(null, ptr, sel, flag)
    }
    
    open fun invalidateShadow(): Unit {
        val sel = ObjCRuntime.sel("invalidateShadow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun toggleFullScreen(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("toggleFullScreen:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun setFrameFromString(string: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setFrameFromString:")
        ObjCRuntime.msgSend(null, ptr, sel, string)
    }
    
    open fun saveFrameUsingName(name: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("saveFrameUsingName:")
        ObjCRuntime.msgSend(null, ptr, sel, name)
    }
    
    open fun setFrameUsingName_force(name: MemorySegment, force: Boolean): Boolean {
        val sel = ObjCRuntime.sel("setFrameUsingName:force:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, name, force) as Boolean
    }
    
    open fun setFrameUsingName(name: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setFrameUsingName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, name) as Boolean
    }
    
    open fun setFrameAutosaveName(name: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setFrameAutosaveName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, name) as Boolean
    }
    
    open fun beginSheet_completionHandler(sheetWindow: MemorySegment, handler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginSheet:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, sheetWindow, handler)
    }
    
    open fun beginCriticalSheet_completionHandler(sheetWindow: MemorySegment, handler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginCriticalSheet:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, sheetWindow, handler)
    }
    
    open fun endSheet(sheetWindow: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("endSheet:")
        ObjCRuntime.msgSend(null, ptr, sel, sheetWindow)
    }
    
    open fun endSheet_returnCode(sheetWindow: MemorySegment, returnCode: Long): Unit {
        val sel = ObjCRuntime.sel("endSheet:returnCode:")
        ObjCRuntime.msgSend(null, ptr, sel, sheetWindow, returnCode)
    }
    
    open fun standardWindowButton(b: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("standardWindowButton:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, b) as MemorySegment
    }
    
    open fun addChildWindow_ordered(childWin: MemorySegment, place: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addChildWindow:ordered:")
        ObjCRuntime.msgSend(null, ptr, sel, childWin, place)
    }
    
    open fun removeChildWindow(childWin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeChildWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, childWin)
    }
    
    open fun canRepresentDisplayGamut(displayGamut: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("canRepresentDisplayGamut:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, displayGamut) as Boolean
    }
    
    open fun performWindowDragWithEvent(event: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performWindowDragWithEvent:")
        ObjCRuntime.msgSend(null, ptr, sel, event)
    }
    
    open fun selectNextKeyView(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectNextKeyView:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectPreviousKeyView(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectPreviousKeyView:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectKeyViewFollowingView(view: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectKeyViewFollowingView:")
        ObjCRuntime.msgSend(null, ptr, sel, view)
    }
    
    open fun selectKeyViewPrecedingView(view: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectKeyViewPrecedingView:")
        ObjCRuntime.msgSend(null, ptr, sel, view)
    }
    
    open fun disableKeyEquivalentForDefaultButtonCell(): Unit {
        val sel = ObjCRuntime.sel("disableKeyEquivalentForDefaultButtonCell")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun enableKeyEquivalentForDefaultButtonCell(): Unit {
        val sel = ObjCRuntime.sel("enableKeyEquivalentForDefaultButtonCell")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun recalculateKeyViewLoop(): Unit {
        val sel = ObjCRuntime.sel("recalculateKeyViewLoop")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun toggleToolbarShown(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("toggleToolbarShown:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun runToolbarCustomizationPalette(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("runToolbarCustomizationPalette:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectNextTab(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectNextTab:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectPreviousTab(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectPreviousTab:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun moveTabToNewWindow(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveTabToNewWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun mergeAllWindows(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("mergeAllWindows:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun toggleTabBar(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("toggleTabBar:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun toggleTabOverview(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("toggleTabOverview:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun addTabbedWindow_ordered(window: MemorySegment, ordered: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTabbedWindow:ordered:")
        ObjCRuntime.msgSend(null, ptr, sel, window, ordered)
    }
    
    open fun transferWindowSharingToWindow_completionHandler(window: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("transferWindowSharingToWindow:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, window, completionHandler)
    }
    
    open fun requestSharingOfWindow_completionHandler(window: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("requestSharingOfWindow:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, window, completionHandler)
    }
    
    open fun requestSharingOfWindowUsingPreview_title_completionHandler(image: MemorySegment, title: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("requestSharingOfWindowUsingPreview:title:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, image, title, completionHandler)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun requestSharingOfWindowUsingPreview_title_completionHandler(image: MemorySegment, title: String, completionHandler: MemorySegment): Unit = requestSharingOfWindowUsingPreview_title_completionHandler(image, ObjCRuntime.newNSString(Arena.global(), title), completionHandler)
    
    // @property defaultDepthLimit
    open fun defaultDepthLimit(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultDepthLimit")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property title
    open fun title(): MemorySegment {
        val sel = ObjCRuntime.sel("title")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun titleAsString(): String = ObjCRuntime.toJavaString(title())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setTitle(value: String) = setTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property subtitle
    open fun subtitle(): MemorySegment {
        val sel = ObjCRuntime.sel("subtitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSubtitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSubtitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun subtitleAsString(): String = ObjCRuntime.toJavaString(subtitle())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setSubtitle(value: String) = setSubtitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property titleVisibility
    open fun titleVisibility(): MemorySegment {
        val sel = ObjCRuntime.sel("titleVisibility")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTitleVisibility(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTitleVisibility:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property titlebarAppearsTransparent
    open fun titlebarAppearsTransparent(): Boolean {
        val sel = ObjCRuntime.sel("titlebarAppearsTransparent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setTitlebarAppearsTransparent(value: Boolean) {
        val sel = ObjCRuntime.sel("setTitlebarAppearsTransparent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property toolbarStyle
    open fun toolbarStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("toolbarStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setToolbarStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setToolbarStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property contentLayoutRect
    open fun contentLayoutRect(): MemorySegment {
        val sel = ObjCRuntime.sel("contentLayoutRect")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property contentLayoutGuide
    open fun contentLayoutGuide(): MemorySegment {
        val sel = ObjCRuntime.sel("contentLayoutGuide")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property titlebarAccessoryViewControllers
    /** @return NSArray<__kindof NSTitlebarAccessoryViewController *> * */
    open fun titlebarAccessoryViewControllers(): MemorySegment {
        val sel = ObjCRuntime.sel("titlebarAccessoryViewControllers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTitlebarAccessoryViewControllers(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTitlebarAccessoryViewControllers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property representedURL
    open fun representedURL(): MemorySegment {
        val sel = ObjCRuntime.sel("representedURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRepresentedURL(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRepresentedURL:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property representedFilename
    open fun representedFilename(): MemorySegment {
        val sel = ObjCRuntime.sel("representedFilename")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRepresentedFilename(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRepresentedFilename:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun representedFilenameAsString(): String = ObjCRuntime.toJavaString(representedFilename())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setRepresentedFilename(value: String) = setRepresentedFilename(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property excludedFromWindowsMenu
    open fun isExcludedFromWindowsMenu(): Boolean {
        val sel = ObjCRuntime.sel("isExcludedFromWindowsMenu")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setExcludedFromWindowsMenu(value: Boolean) {
        val sel = ObjCRuntime.sel("setExcludedFromWindowsMenu:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property contentView
    open fun contentView(): MemorySegment {
        val sel = ObjCRuntime.sel("contentView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContentView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSWindowDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property windowNumber
    open fun windowNumber(): Long {
        val sel = ObjCRuntime.sel("windowNumber")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property styleMask
    open fun styleMask(): MemorySegment {
        val sel = ObjCRuntime.sel("styleMask")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStyleMask(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStyleMask:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property cascadingReferenceFrame
    open fun cascadingReferenceFrame(): MemorySegment {
        val sel = ObjCRuntime.sel("cascadingReferenceFrame")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property frame
    open fun frame(): MemorySegment {
        val sel = ObjCRuntime.sel("frame")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property inLiveResize
    open fun inLiveResize(): Boolean {
        val sel = ObjCRuntime.sel("inLiveResize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property resizeIncrements
    open fun resizeIncrements(): MemorySegment {
        val sel = ObjCRuntime.sel("resizeIncrements")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setResizeIncrements(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setResizeIncrements:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property aspectRatio
    open fun aspectRatio(): MemorySegment {
        val sel = ObjCRuntime.sel("aspectRatio")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setAspectRatio(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAspectRatio:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property contentResizeIncrements
    open fun contentResizeIncrements(): MemorySegment {
        val sel = ObjCRuntime.sel("contentResizeIncrements")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setContentResizeIncrements(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentResizeIncrements:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property contentAspectRatio
    open fun contentAspectRatio(): MemorySegment {
        val sel = ObjCRuntime.sel("contentAspectRatio")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setContentAspectRatio(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentAspectRatio:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property viewsNeedDisplay
    open fun viewsNeedDisplay(): Boolean {
        val sel = ObjCRuntime.sel("viewsNeedDisplay")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setViewsNeedDisplay(value: Boolean) {
        val sel = ObjCRuntime.sel("setViewsNeedDisplay:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property preservesContentDuringLiveResize
    open fun preservesContentDuringLiveResize(): Boolean {
        val sel = ObjCRuntime.sel("preservesContentDuringLiveResize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPreservesContentDuringLiveResize(value: Boolean) {
        val sel = ObjCRuntime.sel("setPreservesContentDuringLiveResize:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property firstResponder
    open fun firstResponder(): MemorySegment {
        val sel = ObjCRuntime.sel("firstResponder")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property resizeFlags
    open fun resizeFlags(): MemorySegment {
        val sel = ObjCRuntime.sel("resizeFlags")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property releasedWhenClosed
    open fun isReleasedWhenClosed(): Boolean {
        val sel = ObjCRuntime.sel("isReleasedWhenClosed")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setReleasedWhenClosed(value: Boolean) {
        val sel = ObjCRuntime.sel("setReleasedWhenClosed:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property zoomed
    open fun isZoomed(): Boolean {
        val sel = ObjCRuntime.sel("isZoomed")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property miniaturized
    open fun isMiniaturized(): Boolean {
        val sel = ObjCRuntime.sel("isMiniaturized")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
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
    
    // @property movable
    open fun isMovable(): Boolean {
        val sel = ObjCRuntime.sel("isMovable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setMovable(value: Boolean) {
        val sel = ObjCRuntime.sel("setMovable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property movableByWindowBackground
    open fun isMovableByWindowBackground(): Boolean {
        val sel = ObjCRuntime.sel("isMovableByWindowBackground")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setMovableByWindowBackground(value: Boolean) {
        val sel = ObjCRuntime.sel("setMovableByWindowBackground:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hidesOnDeactivate
    open fun hidesOnDeactivate(): Boolean {
        val sel = ObjCRuntime.sel("hidesOnDeactivate")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHidesOnDeactivate(value: Boolean) {
        val sel = ObjCRuntime.sel("setHidesOnDeactivate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canHide
    open fun canHide(): Boolean {
        val sel = ObjCRuntime.sel("canHide")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanHide(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanHide:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property miniwindowImage
    open fun miniwindowImage(): MemorySegment {
        val sel = ObjCRuntime.sel("miniwindowImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMiniwindowImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMiniwindowImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property miniwindowTitle
    open fun miniwindowTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("miniwindowTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMiniwindowTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMiniwindowTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun miniwindowTitleAsString(): String = ObjCRuntime.toJavaString(miniwindowTitle())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setMiniwindowTitle(value: String) = setMiniwindowTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property dockTile
    open fun dockTile(): MemorySegment {
        val sel = ObjCRuntime.sel("dockTile")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property documentEdited
    open fun isDocumentEdited(): Boolean {
        val sel = ObjCRuntime.sel("isDocumentEdited")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDocumentEdited(value: Boolean) {
        val sel = ObjCRuntime.sel("setDocumentEdited:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property visible
    open fun isVisible(): Boolean {
        val sel = ObjCRuntime.sel("isVisible")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property keyWindow
    open fun isKeyWindow(): Boolean {
        val sel = ObjCRuntime.sel("isKeyWindow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property mainWindow
    open fun isMainWindow(): Boolean {
        val sel = ObjCRuntime.sel("isMainWindow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canBecomeKeyWindow
    open fun canBecomeKeyWindow(): Boolean {
        val sel = ObjCRuntime.sel("canBecomeKeyWindow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canBecomeMainWindow
    open fun canBecomeMainWindow(): Boolean {
        val sel = ObjCRuntime.sel("canBecomeMainWindow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property worksWhenModal
    open fun worksWhenModal(): Boolean {
        val sel = ObjCRuntime.sel("worksWhenModal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property preventsApplicationTerminationWhenModal
    open fun preventsApplicationTerminationWhenModal(): Boolean {
        val sel = ObjCRuntime.sel("preventsApplicationTerminationWhenModal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPreventsApplicationTerminationWhenModal(value: Boolean) {
        val sel = ObjCRuntime.sel("setPreventsApplicationTerminationWhenModal:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backingScaleFactor
    open fun backingScaleFactor(): Double {
        val sel = ObjCRuntime.sel("backingScaleFactor")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property allowsToolTipsWhenApplicationIsInactive
    open fun allowsToolTipsWhenApplicationIsInactive(): Boolean {
        val sel = ObjCRuntime.sel("allowsToolTipsWhenApplicationIsInactive")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsToolTipsWhenApplicationIsInactive(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsToolTipsWhenApplicationIsInactive:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backingType
    open fun backingType(): MemorySegment {
        val sel = ObjCRuntime.sel("backingType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackingType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackingType:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property level
    open fun level(): Long {
        val sel = ObjCRuntime.sel("level")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setLevel(value: Long) {
        val sel = ObjCRuntime.sel("setLevel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property depthLimit
    open fun depthLimit(): MemorySegment {
        val sel = ObjCRuntime.sel("depthLimit")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDepthLimit(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDepthLimit:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasDynamicDepthLimit
    open fun hasDynamicDepthLimit(): Boolean {
        val sel = ObjCRuntime.sel("hasDynamicDepthLimit")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property screen
    open fun screen(): MemorySegment {
        val sel = ObjCRuntime.sel("screen")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property deepestScreen
    open fun deepestScreen(): MemorySegment {
        val sel = ObjCRuntime.sel("deepestScreen")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property hasShadow
    open fun hasShadow(): Boolean {
        val sel = ObjCRuntime.sel("hasShadow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHasShadow(value: Boolean) {
        val sel = ObjCRuntime.sel("setHasShadow:")
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
    
    // @property opaque
    open fun isOpaque(): Boolean {
        val sel = ObjCRuntime.sel("isOpaque")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setOpaque(value: Boolean) {
        val sel = ObjCRuntime.sel("setOpaque:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sharingType
    open fun sharingType(): MemorySegment {
        val sel = ObjCRuntime.sel("sharingType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSharingType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSharingType:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsConcurrentViewDrawing
    open fun allowsConcurrentViewDrawing(): Boolean {
        val sel = ObjCRuntime.sel("allowsConcurrentViewDrawing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsConcurrentViewDrawing(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsConcurrentViewDrawing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property displaysWhenScreenProfileChanges
    open fun displaysWhenScreenProfileChanges(): Boolean {
        val sel = ObjCRuntime.sel("displaysWhenScreenProfileChanges")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDisplaysWhenScreenProfileChanges(value: Boolean) {
        val sel = ObjCRuntime.sel("setDisplaysWhenScreenProfileChanges:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canBecomeVisibleWithoutLogin
    open fun canBecomeVisibleWithoutLogin(): Boolean {
        val sel = ObjCRuntime.sel("canBecomeVisibleWithoutLogin")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanBecomeVisibleWithoutLogin(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanBecomeVisibleWithoutLogin:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property collectionBehavior
    open fun collectionBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("collectionBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCollectionBehavior(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCollectionBehavior:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property animationBehavior
    open fun animationBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("animationBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAnimationBehavior(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAnimationBehavior:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property onActiveSpace
    open fun isOnActiveSpace(): Boolean {
        val sel = ObjCRuntime.sel("isOnActiveSpace")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property stringWithSavedFrame
    open fun stringWithSavedFrame(): MemorySegment {
        val sel = ObjCRuntime.sel("stringWithSavedFrame")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property frameAutosaveName
    open fun frameAutosaveName(): MemorySegment {
        val sel = ObjCRuntime.sel("frameAutosaveName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property minSize
    open fun minSize(): MemorySegment {
        val sel = ObjCRuntime.sel("minSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setMinSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMinSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property maxSize
    open fun maxSize(): MemorySegment {
        val sel = ObjCRuntime.sel("maxSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setMaxSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMaxSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property contentMinSize
    open fun contentMinSize(): MemorySegment {
        val sel = ObjCRuntime.sel("contentMinSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setContentMinSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentMinSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property contentMaxSize
    open fun contentMaxSize(): MemorySegment {
        val sel = ObjCRuntime.sel("contentMaxSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setContentMaxSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentMaxSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property minFullScreenContentSize
    open fun minFullScreenContentSize(): MemorySegment {
        val sel = ObjCRuntime.sel("minFullScreenContentSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setMinFullScreenContentSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMinFullScreenContentSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property maxFullScreenContentSize
    open fun maxFullScreenContentSize(): MemorySegment {
        val sel = ObjCRuntime.sel("maxFullScreenContentSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setMaxFullScreenContentSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMaxFullScreenContentSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property deviceDescription
    /** @return NSDictionary<NSDeviceDescriptionKey,id> * */
    open fun deviceDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("deviceDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property windowController
    open fun windowController(): MemorySegment {
        val sel = ObjCRuntime.sel("windowController")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setWindowController(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setWindowController:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sheets
    /** @return NSArray<__kindof NSWindow *> * */
    open fun sheets(): MemorySegment {
        val sel = ObjCRuntime.sel("sheets")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property attachedSheet
    open fun attachedSheet(): MemorySegment {
        val sel = ObjCRuntime.sel("attachedSheet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sheet
    open fun isSheet(): Boolean {
        val sel = ObjCRuntime.sel("isSheet")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property sheetParent
    open fun sheetParent(): MemorySegment {
        val sel = ObjCRuntime.sel("sheetParent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property childWindows
    /** @return NSArray<__kindof NSWindow *> * */
    open fun childWindows(): MemorySegment {
        val sel = ObjCRuntime.sel("childWindows")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property parentWindow
    open fun parentWindow(): MemorySegment {
        val sel = ObjCRuntime.sel("parentWindow")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setParentWindow(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setParentWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property appearanceSource
    /** @return NSObject<NSAppearanceCustomization> * */
    open fun appearanceSource(): MemorySegment {
        val sel = ObjCRuntime.sel("appearanceSource")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAppearanceSource(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAppearanceSource:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property colorSpace
    open fun colorSpace(): MemorySegment {
        val sel = ObjCRuntime.sel("colorSpace")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setColorSpace(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setColorSpace:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property occlusionState
    open fun occlusionState(): MemorySegment {
        val sel = ObjCRuntime.sel("occlusionState")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
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
    
    // @property contentViewController
    open fun contentViewController(): MemorySegment {
        val sel = ObjCRuntime.sel("contentViewController")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContentViewController(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentViewController:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property initialFirstResponder
    open fun initialFirstResponder(): MemorySegment {
        val sel = ObjCRuntime.sel("initialFirstResponder")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInitialFirstResponder(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInitialFirstResponder:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property keyViewSelectionDirection
    open fun keyViewSelectionDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("keyViewSelectionDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property defaultButtonCell
    open fun defaultButtonCell(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultButtonCell")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDefaultButtonCell(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDefaultButtonCell:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autorecalculatesKeyViewLoop
    open fun autorecalculatesKeyViewLoop(): Boolean {
        val sel = ObjCRuntime.sel("autorecalculatesKeyViewLoop")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutorecalculatesKeyViewLoop(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutorecalculatesKeyViewLoop:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property toolbar
    open fun toolbar(): MemorySegment {
        val sel = ObjCRuntime.sel("toolbar")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setToolbar(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setToolbar:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsToolbarButton
    open fun showsToolbarButton(): Boolean {
        val sel = ObjCRuntime.sel("showsToolbarButton")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsToolbarButton(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsToolbarButton:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsAutomaticWindowTabbing
    open fun allowsAutomaticWindowTabbing(): Boolean {
        val sel = ObjCRuntime.sel("allowsAutomaticWindowTabbing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsAutomaticWindowTabbing(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsAutomaticWindowTabbing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property userTabbingPreference
    open fun userTabbingPreference(): MemorySegment {
        val sel = ObjCRuntime.sel("userTabbingPreference")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property tabbingMode
    open fun tabbingMode(): MemorySegment {
        val sel = ObjCRuntime.sel("tabbingMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTabbingMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTabbingMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tabbingIdentifier
    open fun tabbingIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("tabbingIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTabbingIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTabbingIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tabbedWindows
    /** @return NSArray<NSWindow *> * */
    open fun tabbedWindows(): MemorySegment {
        val sel = ObjCRuntime.sel("tabbedWindows")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property tab
    open fun tab(): MemorySegment {
        val sel = ObjCRuntime.sel("tab")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property tabGroup
    open fun tabGroup(): MemorySegment {
        val sel = ObjCRuntime.sel("tabGroup")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property hasActiveWindowSharingSession
    open fun hasActiveWindowSharingSession(): Boolean {
        val sel = ObjCRuntime.sel("hasActiveWindowSharingSession")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property windowTitlebarLayoutDirection
    open fun windowTitlebarLayoutDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("windowTitlebarLayoutDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSEvent on NSWindow ─────────────────────────────────────────

fun NSWindow.trackEventsMatchingMask_timeout_mode_handler(mask: MemorySegment, timeout: Double, mode: MemorySegment, trackingHandler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("trackEventsMatchingMask:timeout:mode:handler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, mask, timeout, mode, trackingHandler)
}

fun NSWindow.nextEventMatchingMask(mask: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("nextEventMatchingMask:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, mask) as MemorySegment
}

fun NSWindow.nextEventMatchingMask_untilDate_inMode_dequeue(mask: MemorySegment, expiration: MemorySegment, mode: MemorySegment, deqFlag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("nextEventMatchingMask:untilDate:inMode:dequeue:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, mask, expiration, mode, deqFlag) as MemorySegment
}

fun NSWindow.discardEventsMatchingMask_beforeEvent(mask: MemorySegment, lastEvent: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("discardEventsMatchingMask:beforeEvent:")
    ObjCRuntime.msgSend(null, this.ptr, sel, mask, lastEvent)
}

fun NSWindow.postEvent_atStart(event: MemorySegment, flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("postEvent:atStart:")
    ObjCRuntime.msgSend(null, this.ptr, sel, event, flag)
}

fun NSWindow.sendEvent(event: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sendEvent:")
    ObjCRuntime.msgSend(null, this.ptr, sel, event)
}

fun NSWindow.currentEvent(): MemorySegment {
    val sel = ObjCRuntime.sel("currentEvent")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSWindow.acceptsMouseMovedEvents(): Boolean {
    val sel = ObjCRuntime.sel("acceptsMouseMovedEvents")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.setAcceptsMouseMovedEvents(acceptsMouseMovedEvents: Boolean): Unit {
    val sel = ObjCRuntime.sel("setAcceptsMouseMovedEvents:")
    ObjCRuntime.msgSend(null, this.ptr, sel, acceptsMouseMovedEvents)
}

fun NSWindow.ignoresMouseEvents(): Boolean {
    val sel = ObjCRuntime.sel("ignoresMouseEvents")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.setIgnoresMouseEvents(ignoresMouseEvents: Boolean): Unit {
    val sel = ObjCRuntime.sel("setIgnoresMouseEvents:")
    ObjCRuntime.msgSend(null, this.ptr, sel, ignoresMouseEvents)
}

fun NSWindow.mouseLocationOutsideOfEventStream(): MemorySegment {
    val sel = ObjCRuntime.sel("mouseLocationOutsideOfEventStream")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel) as MemorySegment
}

// ── Category: NSCursorRect on NSWindow ─────────────────────────────────────────

fun NSWindow.disableCursorRects(): Unit {
    val sel = ObjCRuntime.sel("disableCursorRects")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.enableCursorRects(): Unit {
    val sel = ObjCRuntime.sel("enableCursorRects")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.discardCursorRects(): Unit {
    val sel = ObjCRuntime.sel("discardCursorRects")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.invalidateCursorRectsForView(view: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("invalidateCursorRectsForView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, view)
}

fun NSWindow.resetCursorRects(): Unit {
    val sel = ObjCRuntime.sel("resetCursorRects")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.areCursorRectsEnabled(): Boolean {
    val sel = ObjCRuntime.sel("areCursorRectsEnabled")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSDrag on NSWindow ─────────────────────────────────────────

fun NSWindow.beginDraggingSessionWithItems_event_source(items: MemorySegment, event: MemorySegment, source: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("beginDraggingSessionWithItems:event:source:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, items, event, source) as MemorySegment
}

fun NSWindow.dragImage_at_offset_event_pasteboard_source_slideBack(image: MemorySegment, baseLocation: MemorySegment, initialOffset: MemorySegment, event: MemorySegment, pboard: MemorySegment, sourceObj: MemorySegment, slideFlag: Boolean): Unit {
    val sel = ObjCRuntime.sel("dragImage:at:offset:event:pasteboard:source:slideBack:")
    ObjCRuntime.msgSend(null, this.ptr, sel, image, baseLocation, initialOffset, event, pboard, sourceObj, slideFlag)
}

fun NSWindow.registerForDraggedTypes(newTypes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("registerForDraggedTypes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, newTypes)
}

fun NSWindow.unregisterDraggedTypes(): Unit {
    val sel = ObjCRuntime.sel("unregisterDraggedTypes")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

// ── Category: NSDisplayLink on NSWindow ─────────────────────────────────────────

fun NSWindow.displayLinkWithTarget_selector(target: MemorySegment, selector: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("displayLinkWithTarget:selector:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, target, selector) as MemorySegment
}

// ── Category: NSDeprecated on NSWindow ─────────────────────────────────────────

fun NSWindow.cacheImageInRect(rect: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("cacheImageInRect:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect)
}

fun NSWindow.restoreCachedImage(): Unit {
    val sel = ObjCRuntime.sel("restoreCachedImage")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.discardCachedImage(): Unit {
    val sel = ObjCRuntime.sel("discardCachedImage")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.gState(): Long {
    val sel = ObjCRuntime.sel("gState")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSWindow.convertBaseToScreen(point: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("convertBaseToScreen:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel, point) as MemorySegment
}

fun NSWindow.convertScreenToBase(point: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("convertScreenToBase:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel, point) as MemorySegment
}

fun NSWindow.userSpaceScaleFactor(): Double {
    val sel = ObjCRuntime.sel("userSpaceScaleFactor")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSWindow.useOptimizedDrawing(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("useOptimizedDrawing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSWindow.canStoreColor(): Boolean {
    val sel = ObjCRuntime.sel("canStoreColor")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.disableFlushWindow(): Unit {
    val sel = ObjCRuntime.sel("disableFlushWindow")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.enableFlushWindow(): Unit {
    val sel = ObjCRuntime.sel("enableFlushWindow")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.flushWindow(): Unit {
    val sel = ObjCRuntime.sel("flushWindow")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.flushWindowIfNeeded(): Unit {
    val sel = ObjCRuntime.sel("flushWindowIfNeeded")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.initWithWindowRef(windowRef: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithWindowRef:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, windowRef) as MemorySegment
}

fun NSWindow.disableScreenUpdatesUntilFlush(): Unit {
    val sel = ObjCRuntime.sel("disableScreenUpdatesUntilFlush")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.isFlushWindowDisabled(): Boolean {
    val sel = ObjCRuntime.sel("isFlushWindowDisabled")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.isAutodisplay(): Boolean {
    val sel = ObjCRuntime.sel("isAutodisplay")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.setAutodisplay(autodisplay: Boolean): Unit {
    val sel = ObjCRuntime.sel("setAutodisplay:")
    ObjCRuntime.msgSend(null, this.ptr, sel, autodisplay)
}

fun NSWindow.graphicsContext(): MemorySegment {
    val sel = ObjCRuntime.sel("graphicsContext")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSWindow.isOneShot(): Boolean {
    val sel = ObjCRuntime.sel("isOneShot")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.setOneShot(oneShot: Boolean): Unit {
    val sel = ObjCRuntime.sel("setOneShot:")
    ObjCRuntime.msgSend(null, this.ptr, sel, oneShot)
}

fun NSWindow.preferredBackingLocation(): MemorySegment {
    val sel = ObjCRuntime.sel("preferredBackingLocation")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSWindow.setPreferredBackingLocation(preferredBackingLocation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setPreferredBackingLocation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, preferredBackingLocation)
}

fun NSWindow.backingLocation(): MemorySegment {
    val sel = ObjCRuntime.sel("backingLocation")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSWindow.showsResizeIndicator(): Boolean {
    val sel = ObjCRuntime.sel("showsResizeIndicator")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.setShowsResizeIndicator(showsResizeIndicator: Boolean): Unit {
    val sel = ObjCRuntime.sel("setShowsResizeIndicator:")
    ObjCRuntime.msgSend(null, this.ptr, sel, showsResizeIndicator)
}

fun NSWindow.windowRef(): MemorySegment {
    val sel = ObjCRuntime.sel("windowRef")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSWindow menuChanged:]
fun NSWindow_menuChanged(menu: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("menuChanged:")
    val cls = ObjCRuntime.getClass("NSWindow")
    ObjCRuntime.msgSend(null, cls, sel, menu)
}

// ── Category: NSConstraintBasedLayoutCoreMethods on NSWindow ─────────────────────────────────────────

fun NSWindow.updateConstraintsIfNeeded(): Unit {
    val sel = ObjCRuntime.sel("updateConstraintsIfNeeded")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.layoutIfNeeded(): Unit {
    val sel = ObjCRuntime.sel("layoutIfNeeded")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

// ── Category: NSConstraintBasedLayoutAnchoring on NSWindow ─────────────────────────────────────────

fun NSWindow.anchorAttributeForOrientation(orientation: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("anchorAttributeForOrientation:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, orientation) as MemorySegment
}

fun NSWindow.setAnchorAttribute_forOrientation(attr: MemorySegment, orientation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAnchorAttribute:forOrientation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attr, orientation)
}

// ── Category: NSConstraintBasedLayoutDebugging on NSWindow ─────────────────────────────────────────

fun NSWindow.visualizeConstraints(constraints: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("visualizeConstraints:")
    ObjCRuntime.msgSend(null, this.ptr, sel, constraints)
}

// ── Category: NSDrawers on NSWindow ─────────────────────────────────────────

/** @return NSArray<NSDrawer *> * */
fun NSWindow.drawers(): MemorySegment {
    val sel = ObjCRuntime.sel("drawers")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSScripting on NSWindow ─────────────────────────────────────────

fun NSWindow.setIsMiniaturized(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setIsMiniaturized:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSWindow.setIsVisible(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setIsVisible:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSWindow.setIsZoomed(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setIsZoomed:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSWindow.handleCloseScriptCommand(command: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("handleCloseScriptCommand:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, command) as MemorySegment
}

fun NSWindow.handlePrintScriptCommand(command: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("handlePrintScriptCommand:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, command) as MemorySegment
}

fun NSWindow.handleSaveScriptCommand(command: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("handleSaveScriptCommand:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, command) as MemorySegment
}

fun NSWindow.hasCloseBox(): Boolean {
    val sel = ObjCRuntime.sel("hasCloseBox")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.hasTitleBar(): Boolean {
    val sel = ObjCRuntime.sel("hasTitleBar")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.isFloatingPanel(): Boolean {
    val sel = ObjCRuntime.sel("isFloatingPanel")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.isMiniaturizable(): Boolean {
    val sel = ObjCRuntime.sel("isMiniaturizable")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.isModalPanel(): Boolean {
    val sel = ObjCRuntime.sel("isModalPanel")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.isResizable(): Boolean {
    val sel = ObjCRuntime.sel("isResizable")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.isZoomable(): Boolean {
    val sel = ObjCRuntime.sel("isZoomable")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.orderedIndex(): Long {
    val sel = ObjCRuntime.sel("orderedIndex")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSWindow.setOrderedIndex(orderedIndex: Long): Unit {
    val sel = ObjCRuntime.sel("setOrderedIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, orderedIndex)
}

// ── Category: NSUserInterfaceRestoration on NSWindow ─────────────────────────────────────────

fun NSWindow.disableSnapshotRestoration(): Unit {
    val sel = ObjCRuntime.sel("disableSnapshotRestoration")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.enableSnapshotRestoration(): Unit {
    val sel = ObjCRuntime.sel("enableSnapshotRestoration")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSWindow.isRestorable(): Boolean {
    val sel = ObjCRuntime.sel("isRestorable")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSWindow.setRestorable(restorable: Boolean): Unit {
    val sel = ObjCRuntime.sel("setRestorable:")
    ObjCRuntime.msgSend(null, this.ptr, sel, restorable)
}

/** @return Class<NSWindowRestoration> */
fun NSWindow.restorationClass(): MemorySegment {
    val sel = ObjCRuntime.sel("restorationClass")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSWindow.setRestorationClass(restorationClass: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setRestorationClass:")
    ObjCRuntime.msgSend(null, this.ptr, sel, restorationClass)
}

