package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSWindowController
 * Superclass: NSResponder
 * Protocols: NSSeguePerforming
 */
open class NSWindowController(override val ptr: MemorySegment) : NSResponder(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSWindowController") }
        
    }
    
    open fun initWithWindow(window: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithWindow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, window) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun initWithWindowNibName(windowNibName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithWindowNibName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, windowNibName) as MemorySegment
    }
    
    open fun initWithWindowNibName_owner(windowNibName: MemorySegment, owner: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithWindowNibName:owner:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, windowNibName, owner) as MemorySegment
    }
    
    open fun initWithWindowNibPath_owner(windowNibPath: MemorySegment, owner: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithWindowNibPath:owner:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, windowNibPath, owner) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithWindowNibPath_owner(windowNibPath: String, owner: MemorySegment): MemorySegment = initWithWindowNibPath_owner(ObjCRuntime.newNSString(Arena.global(), windowNibPath), owner)
    
    open fun setDocumentEdited(dirtyFlag: Boolean): Unit {
        val sel = ObjCRuntime.sel("setDocumentEdited:")
        ObjCRuntime.msgSend(null, ptr, sel, dirtyFlag)
    }
    
    open fun synchronizeWindowTitleWithDocumentName(): Unit {
        val sel = ObjCRuntime.sel("synchronizeWindowTitleWithDocumentName")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun windowTitleForDocumentDisplayName(displayName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("windowTitleForDocumentDisplayName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, displayName) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun windowTitleForDocumentDisplayNameAsString(displayName: MemorySegment): String = ObjCRuntime.toJavaString(windowTitleForDocumentDisplayName(displayName))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun windowTitleForDocumentDisplayName(displayName: String): MemorySegment = windowTitleForDocumentDisplayName(ObjCRuntime.newNSString(Arena.global(), displayName))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun windowTitleForDocumentDisplayNameAsString(displayName: String): String = ObjCRuntime.toJavaString(windowTitleForDocumentDisplayName(ObjCRuntime.newNSString(Arena.global(), displayName)))
    
    open fun windowWillLoad(): Unit {
        val sel = ObjCRuntime.sel("windowWillLoad")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun windowDidLoad(): Unit {
        val sel = ObjCRuntime.sel("windowDidLoad")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun loadWindow(): Unit {
        val sel = ObjCRuntime.sel("loadWindow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun close(): Unit {
        val sel = ObjCRuntime.sel("close")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun showWindow(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("showWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    // @property windowNibName
    open fun windowNibName(): MemorySegment {
        val sel = ObjCRuntime.sel("windowNibName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property windowNibPath
    open fun windowNibPath(): MemorySegment {
        val sel = ObjCRuntime.sel("windowNibPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun windowNibPathAsString(): String = ObjCRuntime.toJavaString(windowNibPath())
    
    // @property owner
    open fun owner(): MemorySegment {
        val sel = ObjCRuntime.sel("owner")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property windowFrameAutosaveName
    open fun windowFrameAutosaveName(): MemorySegment {
        val sel = ObjCRuntime.sel("windowFrameAutosaveName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setWindowFrameAutosaveName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setWindowFrameAutosaveName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property shouldCascadeWindows
    open fun shouldCascadeWindows(): Boolean {
        val sel = ObjCRuntime.sel("shouldCascadeWindows")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShouldCascadeWindows(value: Boolean) {
        val sel = ObjCRuntime.sel("setShouldCascadeWindows:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property previewRepresentableActivityItems
    /** @return NSArray<id<NSPreviewRepresentableActivityItem>> * */
    open fun previewRepresentableActivityItems(): MemorySegment {
        val sel = ObjCRuntime.sel("previewRepresentableActivityItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPreviewRepresentableActivityItems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPreviewRepresentableActivityItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property document
    open fun document(): MemorySegment {
        val sel = ObjCRuntime.sel("document")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDocument(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property shouldCloseDocument
    open fun shouldCloseDocument(): Boolean {
        val sel = ObjCRuntime.sel("shouldCloseDocument")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShouldCloseDocument(value: Boolean) {
        val sel = ObjCRuntime.sel("setShouldCloseDocument:")
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
    
    // @property window
    open fun window(): MemorySegment {
        val sel = ObjCRuntime.sel("window")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setWindow(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property windowLoaded
    open fun isWindowLoaded(): Boolean {
        val sel = ObjCRuntime.sel("isWindowLoaded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSWindowControllerStoryboardingMethods on NSWindowController ─────────────────────────────────────────

fun NSWindowController.storyboard(): MemorySegment {
    val sel = ObjCRuntime.sel("storyboard")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSWindowControllerDismissing on NSWindowController ─────────────────────────────────────────

fun NSWindowController.dismissController(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("dismissController:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

