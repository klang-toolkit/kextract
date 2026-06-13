package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCollectionViewPrefetching
 * Inherits protocols: NSObject
 */
interface NSCollectionViewPrefetching {
    fun collectionView_prefetchItemsAtIndexPaths(collectionView: MemorySegment, indexPaths: MemorySegment): Unit
    
    // @optional
    fun collectionView_cancelPrefetchingForItemsAtIndexPaths(collectionView: MemorySegment, indexPaths: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:cancelPrefetchingForItemsAtIndexPaths:' not implemented")
    
}

