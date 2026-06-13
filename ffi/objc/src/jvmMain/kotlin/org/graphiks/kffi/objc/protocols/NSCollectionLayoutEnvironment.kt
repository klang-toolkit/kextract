package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCollectionLayoutEnvironment
 * Inherits protocols: NSObject
 */
interface NSCollectionLayoutEnvironment {
    /** @return id<NSCollectionLayoutContainer> */
    fun container(): MemorySegment
    
}

