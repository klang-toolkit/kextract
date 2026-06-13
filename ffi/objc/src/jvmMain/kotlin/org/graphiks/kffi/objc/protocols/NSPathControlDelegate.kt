package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSPathControlDelegate
 * Inherits protocols: NSObject
 */
interface NSPathControlDelegate {
    // @optional
    fun pathControl_shouldDragItem_withPasteboard(pathControl: MemorySegment, pathItem: MemorySegment, pasteboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'pathControl:shouldDragItem:withPasteboard:' not implemented")
    
    // @optional
    fun pathControl_shouldDragPathComponentCell_withPasteboard(pathControl: MemorySegment, pathComponentCell: MemorySegment, pasteboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'pathControl:shouldDragPathComponentCell:withPasteboard:' not implemented")
    
    // @optional
    fun pathControl_validateDrop(pathControl: MemorySegment, info: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'pathControl:validateDrop:' not implemented")
    
    // @optional
    fun pathControl_acceptDrop(pathControl: MemorySegment, info: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'pathControl:acceptDrop:' not implemented")
    
    // @optional
    fun pathControl_willDisplayOpenPanel(pathControl: MemorySegment, openPanel: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pathControl:willDisplayOpenPanel:' not implemented")
    
    // @optional
    fun pathControl_willPopUpMenu(pathControl: MemorySegment, menu: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pathControl:willPopUpMenu:' not implemented")
    
}

