package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPrintPanel
 * Superclass: NSObject
 */
open class NSPrintPanel(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPrintPanel") }
        
        fun printPanel(): MemorySegment {
            val sel = ObjCRuntime.sel("printPanel")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun addAccessoryController(accessoryController: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addAccessoryController:")
        ObjCRuntime.msgSend(null, ptr, sel, accessoryController)
    }
    
    open fun removeAccessoryController(accessoryController: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeAccessoryController:")
        ObjCRuntime.msgSend(null, ptr, sel, accessoryController)
    }
    
    open fun setDefaultButtonTitle(defaultButtonTitle: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setDefaultButtonTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, defaultButtonTitle)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setDefaultButtonTitle(defaultButtonTitle: String): Unit = setDefaultButtonTitle(ObjCRuntime.newNSString(Arena.global(), defaultButtonTitle))
    
    open fun defaultButtonTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultButtonTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun defaultButtonTitleAsString(): String = ObjCRuntime.toJavaString(defaultButtonTitle())
    
    open fun beginSheetUsingPrintInfo_onWindow_completionHandler(printInfo: MemorySegment, parentWindow: MemorySegment, handler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginSheetUsingPrintInfo:onWindow:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, printInfo, parentWindow, handler)
    }
    
    open fun beginSheetWithPrintInfo_modalForWindow_delegate_didEndSelector_contextInfo(printInfo: MemorySegment, docWindow: MemorySegment, delegate: MemorySegment, didEndSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginSheetWithPrintInfo:modalForWindow:delegate:didEndSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, printInfo, docWindow, delegate, didEndSelector, contextInfo)
    }
    
    open fun runModalWithPrintInfo(printInfo: MemorySegment): Long {
        val sel = ObjCRuntime.sel("runModalWithPrintInfo:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, printInfo) as Long
    }
    
    open fun runModal(): Long {
        val sel = ObjCRuntime.sel("runModal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property accessoryControllers
    /** @return NSArray<__kindof NSViewController *> * */
    open fun accessoryControllers(): MemorySegment {
        val sel = ObjCRuntime.sel("accessoryControllers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property options
    open fun options(): MemorySegment {
        val sel = ObjCRuntime.sel("options")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setOptions(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setOptions:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property helpAnchor
    open fun helpAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("helpAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHelpAnchor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHelpAnchor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property jobStyleHint
    open fun jobStyleHint(): MemorySegment {
        val sel = ObjCRuntime.sel("jobStyleHint")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setJobStyleHint(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setJobStyleHint:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property printInfo
    open fun printInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("printInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSDeprecated on NSPrintPanel ─────────────────────────────────────────

fun NSPrintPanel.setAccessoryView(accessoryView: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAccessoryView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, accessoryView)
}

fun NSPrintPanel.accessoryView(): MemorySegment {
    val sel = ObjCRuntime.sel("accessoryView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSPrintPanel.updateFromPrintInfo(): Unit {
    val sel = ObjCRuntime.sel("updateFromPrintInfo")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSPrintPanel.finalWritePrintInfo(): Unit {
    val sel = ObjCRuntime.sel("finalWritePrintInfo")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

