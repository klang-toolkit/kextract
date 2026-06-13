package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSGestureRecognizerDelegate
 * Inherits protocols: NSObject
 */
interface NSGestureRecognizerDelegate {
    // @optional
    fun gestureRecognizer_shouldAttemptToRecognizeWithEvent(gestureRecognizer: MemorySegment, event: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'gestureRecognizer:shouldAttemptToRecognizeWithEvent:' not implemented")
    
    // @optional
    fun gestureRecognizerShouldBegin(gestureRecognizer: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'gestureRecognizerShouldBegin:' not implemented")
    
    // @optional
    fun gestureRecognizer_shouldRecognizeSimultaneouslyWithGestureRecognizer(gestureRecognizer: MemorySegment, otherGestureRecognizer: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'gestureRecognizer:shouldRecognizeSimultaneouslyWithGestureRecognizer:' not implemented")
    
    // @optional
    fun gestureRecognizer_shouldRequireFailureOfGestureRecognizer(gestureRecognizer: MemorySegment, otherGestureRecognizer: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'gestureRecognizer:shouldRequireFailureOfGestureRecognizer:' not implemented")
    
    // @optional
    fun gestureRecognizer_shouldBeRequiredToFailByGestureRecognizer(gestureRecognizer: MemorySegment, otherGestureRecognizer: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'gestureRecognizer:shouldBeRequiredToFailByGestureRecognizer:' not implemented")
    
    // @optional
    fun gestureRecognizer_shouldReceiveTouch(gestureRecognizer: MemorySegment, touch: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'gestureRecognizer:shouldReceiveTouch:' not implemented")
    
}

