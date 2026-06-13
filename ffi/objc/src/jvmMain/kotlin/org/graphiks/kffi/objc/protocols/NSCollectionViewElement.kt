package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCollectionViewElement
 * Inherits protocols: NSObject, NSUserInterfaceItemIdentification
 */
interface NSCollectionViewElement : NSUserInterfaceItemIdentification {
    // @optional
    fun prepareForReuse(): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'prepareForReuse' not implemented")
    
    // @optional
    fun applyLayoutAttributes(layoutAttributes: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'applyLayoutAttributes:' not implemented")
    
    // @optional
    fun willTransitionFromLayout_toLayout(oldLayout: MemorySegment, newLayout: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'willTransitionFromLayout:toLayout:' not implemented")
    
    // @optional
    fun didTransitionFromLayout_toLayout(oldLayout: MemorySegment, newLayout: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'didTransitionFromLayout:toLayout:' not implemented")
    
    // @optional
    fun preferredLayoutAttributesFittingAttributes(layoutAttributes: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'preferredLayoutAttributesFittingAttributes:' not implemented")
    
}

