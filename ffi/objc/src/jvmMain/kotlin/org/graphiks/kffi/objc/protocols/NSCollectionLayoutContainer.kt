package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCollectionLayoutContainer
 * Inherits protocols: NSObject
 */
interface NSCollectionLayoutContainer {
    fun contentSize(): MemorySegment
    
    fun effectiveContentSize(): MemorySegment
    
    fun contentInsets(): MemorySegment
    
    fun effectiveContentInsets(): MemorySegment
    
}

