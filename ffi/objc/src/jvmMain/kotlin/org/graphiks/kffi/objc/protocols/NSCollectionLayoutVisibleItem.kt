package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCollectionLayoutVisibleItem
 * Inherits protocols: NSObject
 */
interface NSCollectionLayoutVisibleItem {
    fun alpha(): Double
    
    fun setAlpha(alpha: Double): Unit
    
    fun zIndex(): Long
    
    fun setZIndex(zIndex: Long): Unit
    
    fun isHidden(): Boolean
    
    fun setHidden(hidden: Boolean): Unit
    
    fun center(): MemorySegment
    
    fun setCenter(center: MemorySegment): Unit
    
    fun name(): MemorySegment
    
    fun indexPath(): MemorySegment
    
    fun frame(): MemorySegment
    
    fun bounds(): MemorySegment
    
    fun representedElementCategory(): MemorySegment
    
    fun representedElementKind(): MemorySegment
    
}

