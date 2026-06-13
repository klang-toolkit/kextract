package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAnimationDelegate
 * Inherits protocols: NSObject
 */
interface NSAnimationDelegate {
    // @optional
    fun animationShouldStart(animation: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'animationShouldStart:' not implemented")
    
    // @optional
    fun animationDidStop(animation: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'animationDidStop:' not implemented")
    
    // @optional
    fun animationDidEnd(animation: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'animationDidEnd:' not implemented")
    
    // @optional
    fun animation_valueForProgress(animation: MemorySegment, progress: Float): Float =
        throw UnsupportedOperationException("Optional ObjC method 'animation:valueForProgress:' not implemented")
    
    // @optional
    fun animation_didReachProgressMark(animation: MemorySegment, progress: Float): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'animation:didReachProgressMark:' not implemented")
    
}

