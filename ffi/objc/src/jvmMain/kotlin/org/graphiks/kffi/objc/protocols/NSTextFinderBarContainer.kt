package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextFinderBarContainer
 * Inherits protocols: NSObject
 */
interface NSTextFinderBarContainer {
    fun findBarViewDidChangeHeight(): Unit
    
    // @optional
    fun contentView(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'contentView' not implemented")
    
    fun findBarView(): MemorySegment
    
    fun setFindBarView(findBarView: MemorySegment): Unit
    
    fun isFindBarVisible(): Boolean
    
    fun setFindBarVisible(findBarVisible: Boolean): Unit
    
}

