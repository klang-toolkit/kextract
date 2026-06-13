package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAlert
 * Superclass: NSObject
 */
open class NSAlert(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAlert") }
        
        fun alertWithError(error: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("alertWithError:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, error) as MemorySegment
        }
        
    }
    
    open fun addButtonWithTitle(title: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("addButtonWithTitle:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, title) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun addButtonWithTitle(title: String): MemorySegment = addButtonWithTitle(ObjCRuntime.newNSString(Arena.global(), title))
    
    open fun layout(): Unit {
        val sel = ObjCRuntime.sel("layout")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun runModal(): Long {
        val sel = ObjCRuntime.sel("runModal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun beginSheetModalForWindow_completionHandler(sheetWindow: MemorySegment, handler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginSheetModalForWindow:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, sheetWindow, handler)
    }
    
    // @property messageText
    open fun messageText(): MemorySegment {
        val sel = ObjCRuntime.sel("messageText")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMessageText(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMessageText:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun messageTextAsString(): String = ObjCRuntime.toJavaString(messageText())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setMessageText(value: String) = setMessageText(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property informativeText
    open fun informativeText(): MemorySegment {
        val sel = ObjCRuntime.sel("informativeText")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInformativeText(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInformativeText:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun informativeTextAsString(): String = ObjCRuntime.toJavaString(informativeText())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setInformativeText(value: String) = setInformativeText(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property icon
    open fun icon(): MemorySegment {
        val sel = ObjCRuntime.sel("icon")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setIcon(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setIcon:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property buttons
    /** @return NSArray<NSButton *> * */
    open fun buttons(): MemorySegment {
        val sel = ObjCRuntime.sel("buttons")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property alertStyle
    open fun alertStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("alertStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAlertStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAlertStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsHelp
    open fun showsHelp(): Boolean {
        val sel = ObjCRuntime.sel("showsHelp")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsHelp(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsHelp:")
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
    
    // @property delegate
    /** @return id<NSAlertDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property accessoryView
    open fun accessoryView(): MemorySegment {
        val sel = ObjCRuntime.sel("accessoryView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAccessoryView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAccessoryView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsSuppressionButton
    open fun showsSuppressionButton(): Boolean {
        val sel = ObjCRuntime.sel("showsSuppressionButton")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsSuppressionButton(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsSuppressionButton:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property suppressionButton
    open fun suppressionButton(): MemorySegment {
        val sel = ObjCRuntime.sel("suppressionButton")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property window
    open fun window(): MemorySegment {
        val sel = ObjCRuntime.sel("window")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSAlertDeprecated on NSAlert ─────────────────────────────────────────

fun NSAlert.beginSheetModalForWindow_modalDelegate_didEndSelector_contextInfo(window: MemorySegment, delegate: MemorySegment, didEndSelector: MemorySegment, contextInfo: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("beginSheetModalForWindow:modalDelegate:didEndSelector:contextInfo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, window, delegate, didEndSelector, contextInfo)
}

// Class method: +[NSAlert alertWithMessageText:defaultButton:alternateButton:otherButton:informativeTextWithFormat:]
fun NSAlert_alertWithMessageText_defaultButton_alternateButton_otherButton_informativeTextWithFormat(message: MemorySegment, defaultButton: MemorySegment, alternateButton: MemorySegment, otherButton: MemorySegment, format: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("alertWithMessageText:defaultButton:alternateButton:otherButton:informativeTextWithFormat:")
    val cls = ObjCRuntime.getClass("NSAlert")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, message, defaultButton, alternateButton, otherButton, format) as MemorySegment
}

