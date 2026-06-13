package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCollectionViewDataSource
 * Inherits protocols: NSObject
 */
interface NSCollectionViewDataSource {
    fun collectionView_numberOfItemsInSection(collectionView: MemorySegment, section: Long): Long
    
    fun collectionView_itemForRepresentedObjectAtIndexPath(collectionView: MemorySegment, indexPath: MemorySegment): MemorySegment
    
    // @optional
    fun numberOfSectionsInCollectionView(collectionView: MemorySegment): Long =
        throw UnsupportedOperationException("Optional ObjC method 'numberOfSectionsInCollectionView:' not implemented")
    
    // @optional
    fun collectionView_viewForSupplementaryElementOfKind_atIndexPath(collectionView: MemorySegment, kind: MemorySegment, indexPath: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'collectionView:viewForSupplementaryElementOfKind:atIndexPath:' not implemented")
    
}

