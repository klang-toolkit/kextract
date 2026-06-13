package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTableViewDiffableDataSource
 * Superclass: NSObject
 * Protocols: NSTableViewDataSource
 */
open class NSTableViewDiffableDataSource(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTableViewDiffableDataSource") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithTableView_cellProvider(tableView: MemorySegment, cellProvider: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTableView:cellProvider:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, tableView, cellProvider) as MemorySegment
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
    
    open fun applySnapshot_animatingDifferences_completion(snapshot: MemorySegment, animatingDifferences: Boolean, completion: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("applySnapshot:animatingDifferences:completion:")
        ObjCRuntime.msgSend(null, ptr, sel, snapshot, animatingDifferences, completion)
    }
    
    open fun itemIdentifierForRow(row: Long): MemorySegment {
        val sel = ObjCRuntime.sel("itemIdentifierForRow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row) as MemorySegment
    }
    
    open fun rowForItemIdentifier(identifier: MemorySegment): Long {
        val sel = ObjCRuntime.sel("rowForItemIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, identifier) as Long
    }
    
    open fun sectionIdentifierForRow(row: Long): MemorySegment {
        val sel = ObjCRuntime.sel("sectionIdentifierForRow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row) as MemorySegment
    }
    
    open fun rowForSectionIdentifier(identifier: MemorySegment): Long {
        val sel = ObjCRuntime.sel("rowForSectionIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, identifier) as Long
    }
    
    // @property rowViewProvider
    open fun rowViewProvider(): MemorySegment {
        val sel = ObjCRuntime.sel("rowViewProvider")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRowViewProvider(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRowViewProvider:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sectionHeaderViewProvider
    open fun sectionHeaderViewProvider(): MemorySegment {
        val sel = ObjCRuntime.sel("sectionHeaderViewProvider")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSectionHeaderViewProvider(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSectionHeaderViewProvider:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property defaultRowAnimation
    open fun defaultRowAnimation(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultRowAnimation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDefaultRowAnimation(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDefaultRowAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

