package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionViewDiffableDataSource
 * Superclass: NSObject
 * Protocols: NSCollectionViewDataSource
 */
open class NSCollectionViewDiffableDataSource(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionViewDiffableDataSource") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithCollectionView_itemProvider(collectionView: MemorySegment, itemProvider: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCollectionView:itemProvider:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, collectionView, itemProvider) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** @return NSDiffableDataSourceSnapshot<SectionIdentifierType,ItemIdentifierType> * */
    open fun snapshot(): MemorySegment {
        val sel = ObjCRuntime.sel("snapshot")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun applySnapshot_animatingDifferences(snapshot: MemorySegment, animatingDifferences: Boolean): Unit {
        val sel = ObjCRuntime.sel("applySnapshot:animatingDifferences:")
        ObjCRuntime.msgSend(null, ptr, sel, snapshot, animatingDifferences)
    }
    
    open fun itemIdentifierForIndexPath(indexPath: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("itemIdentifierForIndexPath:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, indexPath) as MemorySegment
    }
    
    open fun indexPathForItemIdentifier(identifier: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("indexPathForItemIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, identifier) as MemorySegment
    }
    
    // @property supplementaryViewProvider
    open fun supplementaryViewProvider(): MemorySegment {
        val sel = ObjCRuntime.sel("supplementaryViewProvider")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSupplementaryViewProvider(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSupplementaryViewProvider:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

