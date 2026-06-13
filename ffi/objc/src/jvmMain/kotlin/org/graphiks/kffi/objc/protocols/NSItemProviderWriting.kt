package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSItemProviderWriting
 * Inherits protocols: NSObject
 */
interface NSItemProviderWriting {
    // @optional
    fun itemProviderVisibilityForRepresentationWithTypeIdentifier(typeIdentifier: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'itemProviderVisibilityForRepresentationWithTypeIdentifier:' not implemented")
    
    fun loadDataWithTypeIdentifier_forItemProviderCompletionHandler(typeIdentifier: MemorySegment, completionHandler: MemorySegment): MemorySegment
    
    /** @return NSArray<NSString *> * */
    fun writableTypeIdentifiersForItemProvider(): MemorySegment
    
}

