package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityElementLoading
 * Inherits protocols: NSObject
 */
interface NSAccessibilityElementLoading {
    /** @return id<NSAccessibilityElement> */
    fun accessibilityElementWithToken(token: MemorySegment): MemorySegment
    
    // @optional
    fun accessibilityRangeInTargetElementWithToken(token: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityRangeInTargetElementWithToken:' not implemented")
    
}

