package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextCheckingClient
 * Inherits protocols: NSTextInputClient, NSTextInputTraits
 */
interface NSTextCheckingClient : NSTextInputClient, NSTextInputTraits {
    fun annotatedSubstringForProposedRange_actualRange(range: MemorySegment, actualRange: MemorySegment): MemorySegment
    
    fun setAnnotations_range(annotations: MemorySegment, range: MemorySegment): Unit
    
    fun addAnnotations_range(annotations: MemorySegment, range: MemorySegment): Unit
    
    fun removeAnnotation_range(annotationName: MemorySegment, range: MemorySegment): Unit
    
    fun replaceCharactersInRange_withAnnotatedString(range: MemorySegment, annotatedString: MemorySegment): Unit
    
    fun selectAndShowRange(range: MemorySegment): Unit
    
    fun viewForRange_firstRect_actualRange(range: MemorySegment, firstRect: MemorySegment, actualRange: MemorySegment): MemorySegment
    
    fun candidateListTouchBarItem(): MemorySegment
    
}

