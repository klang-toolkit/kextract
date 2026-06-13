package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSOpenSavePanelDelegate
 * Inherits protocols: NSObject
 */
interface NSOpenSavePanelDelegate {
    // @optional
    fun panel_shouldEnableURL(sender: MemorySegment, url: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'panel:shouldEnableURL:' not implemented")
    
    // @optional
    fun panel_validateURL_error(sender: MemorySegment, url: MemorySegment, outError: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'panel:validateURL:error:' not implemented")
    
    // @optional
    fun panel_didChangeToDirectoryURL(sender: MemorySegment, url: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'panel:didChangeToDirectoryURL:' not implemented")
    
    // @optional
    fun panel_userEnteredFilename_confirmed(sender: MemorySegment, filename: MemorySegment, okFlag: Boolean): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'panel:userEnteredFilename:confirmed:' not implemented")
    
    // @optional
    fun panel_willExpand(sender: MemorySegment, expanding: Boolean): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'panel:willExpand:' not implemented")
    
    // @optional
    fun panelSelectionDidChange(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'panelSelectionDidChange:' not implemented")
    
    // @optional
    fun panel_displayNameForType(sender: MemorySegment, type: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'panel:displayNameForType:' not implemented")
    
    // @optional
    fun panel_didSelectType(sender: MemorySegment, type: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'panel:didSelectType:' not implemented")
    
}

