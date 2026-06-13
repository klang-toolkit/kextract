package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSPathCellDelegate
 * Inherits protocols: NSObject
 */
interface NSPathCellDelegate {
    // @optional
    fun pathCell_willDisplayOpenPanel(pathCell: MemorySegment, openPanel: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pathCell:willDisplayOpenPanel:' not implemented")
    
    // @optional
    fun pathCell_willPopUpMenu(pathCell: MemorySegment, menu: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pathCell:willPopUpMenu:' not implemented")
    
}

