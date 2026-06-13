package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSavePanel
 * Superclass: NSPanel
 */
open class NSSavePanel(override val ptr: MemorySegment) : NSPanel(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSavePanel") }
        
        fun savePanel(): MemorySegment {
            val sel = ObjCRuntime.sel("savePanel")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun validateVisibleColumns(): Unit {
        val sel = ObjCRuntime.sel("validateVisibleColumns")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun ok(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("ok:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun cancel(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("cancel:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun beginSheetModalForWindow_completionHandler(window: MemorySegment, handler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginSheetModalForWindow:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, window, handler)
    }
    
    open fun beginWithCompletionHandler(handler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, handler)
    }
    
    open fun runModal(): Long {
        val sel = ObjCRuntime.sel("runModal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property URL
    open fun URL(): MemorySegment {
        val sel = ObjCRuntime.sel("URL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property identifier
    open fun identifier(): MemorySegment {
        val sel = ObjCRuntime.sel("identifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property directoryURL
    open fun directoryURL(): MemorySegment {
        val sel = ObjCRuntime.sel("directoryURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDirectoryURL(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDirectoryURL:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowedContentTypes
    /** @return NSArray<UTType *> * */
    open fun allowedContentTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("allowedContentTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAllowedContentTypes(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAllowedContentTypes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsOtherFileTypes
    open fun allowsOtherFileTypes(): Boolean {
        val sel = ObjCRuntime.sel("allowsOtherFileTypes")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsOtherFileTypes(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsOtherFileTypes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property currentContentType
    open fun currentContentType(): MemorySegment {
        val sel = ObjCRuntime.sel("currentContentType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCurrentContentType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCurrentContentType:")
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
    
    // @property delegate
    /** @return id<NSOpenSavePanelDelegate> */
    override fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property expanded
    open fun isExpanded(): Boolean {
        val sel = ObjCRuntime.sel("isExpanded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property canCreateDirectories
    open fun canCreateDirectories(): Boolean {
        val sel = ObjCRuntime.sel("canCreateDirectories")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanCreateDirectories(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanCreateDirectories:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canSelectHiddenExtension
    open fun canSelectHiddenExtension(): Boolean {
        val sel = ObjCRuntime.sel("canSelectHiddenExtension")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanSelectHiddenExtension(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanSelectHiddenExtension:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property extensionHidden
    open fun isExtensionHidden(): Boolean {
        val sel = ObjCRuntime.sel("isExtensionHidden")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setExtensionHidden(value: Boolean) {
        val sel = ObjCRuntime.sel("setExtensionHidden:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property treatsFilePackagesAsDirectories
    open fun treatsFilePackagesAsDirectories(): Boolean {
        val sel = ObjCRuntime.sel("treatsFilePackagesAsDirectories")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setTreatsFilePackagesAsDirectories(value: Boolean) {
        val sel = ObjCRuntime.sel("setTreatsFilePackagesAsDirectories:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property prompt
    open fun prompt(): MemorySegment {
        val sel = ObjCRuntime.sel("prompt")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPrompt(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPrompt:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun promptAsString(): String = ObjCRuntime.toJavaString(prompt())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPrompt(value: String) = setPrompt(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property title
    override fun title(): MemorySegment {
        val sel = ObjCRuntime.sel("title")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property nameFieldLabel
    open fun nameFieldLabel(): MemorySegment {
        val sel = ObjCRuntime.sel("nameFieldLabel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setNameFieldLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setNameFieldLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun nameFieldLabelAsString(): String = ObjCRuntime.toJavaString(nameFieldLabel())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setNameFieldLabel(value: String) = setNameFieldLabel(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property nameFieldStringValue
    open fun nameFieldStringValue(): MemorySegment {
        val sel = ObjCRuntime.sel("nameFieldStringValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setNameFieldStringValue(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setNameFieldStringValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun nameFieldStringValueAsString(): String = ObjCRuntime.toJavaString(nameFieldStringValue())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setNameFieldStringValue(value: String) = setNameFieldStringValue(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property message
    open fun message(): MemorySegment {
        val sel = ObjCRuntime.sel("message")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMessage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMessage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun messageAsString(): String = ObjCRuntime.toJavaString(message())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setMessage(value: String) = setMessage(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property showsHiddenFiles
    open fun showsHiddenFiles(): Boolean {
        val sel = ObjCRuntime.sel("showsHiddenFiles")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsHiddenFiles(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsHiddenFiles:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsTagField
    open fun showsTagField(): Boolean {
        val sel = ObjCRuntime.sel("showsTagField")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsTagField(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsTagField:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tagNames
    /** @return NSArray<NSString *> * */
    open fun tagNames(): MemorySegment {
        val sel = ObjCRuntime.sel("tagNames")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTagNames(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTagNames:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsContentTypes
    open fun showsContentTypes(): Boolean {
        val sel = ObjCRuntime.sel("showsContentTypes")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsContentTypes(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsContentTypes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSDeprecated on NSSavePanel ─────────────────────────────────────────

fun NSSavePanel.filename(): MemorySegment {
    val sel = ObjCRuntime.sel("filename")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSSavePanel.directory(): MemorySegment {
    val sel = ObjCRuntime.sel("directory")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSSavePanel.setDirectory(path: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setDirectory:")
    ObjCRuntime.msgSend(null, this.ptr, sel, path)
}

fun NSSavePanel.requiredFileType(): MemorySegment {
    val sel = ObjCRuntime.sel("requiredFileType")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSSavePanel.setRequiredFileType(type: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setRequiredFileType:")
    ObjCRuntime.msgSend(null, this.ptr, sel, type)
}

fun NSSavePanel.beginSheetForDirectory_file_modalForWindow_modalDelegate_didEndSelector_contextInfo(path: MemorySegment, name: MemorySegment, docWindow: MemorySegment, delegate: MemorySegment, didEndSelector: MemorySegment, contextInfo: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("beginSheetForDirectory:file:modalForWindow:modalDelegate:didEndSelector:contextInfo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, path, name, docWindow, delegate, didEndSelector, contextInfo)
}

fun NSSavePanel.runModalForDirectory_file(path: MemorySegment, name: MemorySegment): Long {
    val sel = ObjCRuntime.sel("runModalForDirectory:file:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, path, name) as Long
}

fun NSSavePanel.selectText(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("selectText:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

/** @return NSArray<NSString *> * */
fun NSSavePanel.allowedFileTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("allowedFileTypes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSSavePanel.setAllowedFileTypes(allowedFileTypes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAllowedFileTypes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, allowedFileTypes)
}

