package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTabViewDelegate
 * Inherits protocols: NSObject
 */
interface NSTabViewDelegate {
    // @optional
    fun tabView_shouldSelectTabViewItem(tabView: MemorySegment, tabViewItem: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'tabView:shouldSelectTabViewItem:' not implemented")
    
    // @optional
    fun tabView_willSelectTabViewItem(tabView: MemorySegment, tabViewItem: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'tabView:willSelectTabViewItem:' not implemented")
    
    // @optional
    fun tabView_didSelectTabViewItem(tabView: MemorySegment, tabViewItem: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'tabView:didSelectTabViewItem:' not implemented")
    
    // @optional
    fun tabViewDidChangeNumberOfTabViewItems(tabView: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'tabViewDidChangeNumberOfTabViewItems:' not implemented")
    
}

