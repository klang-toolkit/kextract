package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSItemProviderReading
 * Inherits protocols: NSObject
 */
interface NSItemProviderReading {
    fun objectWithItemProviderData_typeIdentifier_error(`data`: MemorySegment, typeIdentifier: MemorySegment, outError: MemorySegment): MemorySegment
    
    /** @return NSArray<NSString *> * */
    fun readableTypeIdentifiersForItemProvider(): MemorySegment
    
}

