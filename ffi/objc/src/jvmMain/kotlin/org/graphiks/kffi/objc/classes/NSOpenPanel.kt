package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOpenPanel
 * Superclass: NSSavePanel
 */
open class NSOpenPanel(override val ptr: MemorySegment) : NSSavePanel(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOpenPanel") }
        
        fun openPanel(): MemorySegment {
            val sel = ObjCRuntime.sel("openPanel")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property URLs
    /** @return NSArray<NSURL *> * */
    open fun URLs(): MemorySegment {
        val sel = ObjCRuntime.sel("URLs")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property resolvesAliases
    open fun resolvesAliases(): Boolean {
        val sel = ObjCRuntime.sel("resolvesAliases")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setResolvesAliases(value: Boolean) {
        val sel = ObjCRuntime.sel("setResolvesAliases:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canChooseDirectories
    open fun canChooseDirectories(): Boolean {
        val sel = ObjCRuntime.sel("canChooseDirectories")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanChooseDirectories(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanChooseDirectories:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsMultipleSelection
    open fun allowsMultipleSelection(): Boolean {
        val sel = ObjCRuntime.sel("allowsMultipleSelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsMultipleSelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsMultipleSelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canChooseFiles
    open fun canChooseFiles(): Boolean {
        val sel = ObjCRuntime.sel("canChooseFiles")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanChooseFiles(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanChooseFiles:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canResolveUbiquitousConflicts
    open fun canResolveUbiquitousConflicts(): Boolean {
        val sel = ObjCRuntime.sel("canResolveUbiquitousConflicts")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanResolveUbiquitousConflicts(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanResolveUbiquitousConflicts:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canDownloadUbiquitousContents
    open fun canDownloadUbiquitousContents(): Boolean {
        val sel = ObjCRuntime.sel("canDownloadUbiquitousContents")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanDownloadUbiquitousContents(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanDownloadUbiquitousContents:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property accessoryViewDisclosed
    open fun isAccessoryViewDisclosed(): Boolean {
        val sel = ObjCRuntime.sel("isAccessoryViewDisclosed")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAccessoryViewDisclosed(value: Boolean) {
        val sel = ObjCRuntime.sel("setAccessoryViewDisclosed:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: InheritedAndUnavailable on NSOpenPanel ─────────────────────────────────────────

fun NSOpenPanel.showsContentTypes(): Boolean {
    val sel = ObjCRuntime.sel("showsContentTypes")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSOpenPanel.setShowsContentTypes(showsContentTypes: Boolean): Unit {
    val sel = ObjCRuntime.sel("setShowsContentTypes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, showsContentTypes)
}

// ── Category: NSDeprecated on NSOpenPanel ─────────────────────────────────────────

fun NSOpenPanel.filenames(): MemorySegment {
    val sel = ObjCRuntime.sel("filenames")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSOpenPanel.beginSheetForDirectory_file_types_modalForWindow_modalDelegate_didEndSelector_contextInfo(path: MemorySegment, name: MemorySegment, fileTypes: MemorySegment, docWindow: MemorySegment, delegate: MemorySegment, didEndSelector: MemorySegment, contextInfo: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("beginSheetForDirectory:file:types:modalForWindow:modalDelegate:didEndSelector:contextInfo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, path, name, fileTypes, docWindow, delegate, didEndSelector, contextInfo)
}

fun NSOpenPanel.beginForDirectory_file_types_modelessDelegate_didEndSelector_contextInfo(path: MemorySegment, name: MemorySegment, fileTypes: MemorySegment, delegate: MemorySegment, didEndSelector: MemorySegment, contextInfo: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("beginForDirectory:file:types:modelessDelegate:didEndSelector:contextInfo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, path, name, fileTypes, delegate, didEndSelector, contextInfo)
}

fun NSOpenPanel.runModalForDirectory_file_types(path: MemorySegment, name: MemorySegment, fileTypes: MemorySegment): Long {
    val sel = ObjCRuntime.sel("runModalForDirectory:file:types:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, path, name, fileTypes) as Long
}

fun NSOpenPanel.runModalForTypes(fileTypes: MemorySegment): Long {
    val sel = ObjCRuntime.sel("runModalForTypes:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, fileTypes) as Long
}

