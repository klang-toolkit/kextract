package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDiffableDataSourceSnapshot
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSDiffableDataSourceSnapshot(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDiffableDataSourceSnapshot") }
        
    }
    
    open fun numberOfItemsInSection(sectionIdentifier: MemorySegment): Long {
        val sel = ObjCRuntime.sel("numberOfItemsInSection:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, sectionIdentifier) as Long
    }
    
    /** @return NSArray<ItemIdentifierType> * */
    open fun itemIdentifiersInSectionWithIdentifier(sectionIdentifier: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("itemIdentifiersInSectionWithIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, sectionIdentifier) as MemorySegment
    }
    
    open fun sectionIdentifierForSectionContainingItemIdentifier(itemIdentifier: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("sectionIdentifierForSectionContainingItemIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, itemIdentifier) as MemorySegment
    }
    
    open fun indexOfItemIdentifier(itemIdentifier: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfItemIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, itemIdentifier) as Long
    }
    
    open fun indexOfSectionIdentifier(sectionIdentifier: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfSectionIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, sectionIdentifier) as Long
    }
    
    open fun appendItemsWithIdentifiers(identifiers: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("appendItemsWithIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, identifiers)
    }
    
    open fun appendItemsWithIdentifiers_intoSectionWithIdentifier(identifiers: MemorySegment, sectionIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("appendItemsWithIdentifiers:intoSectionWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, identifiers, sectionIdentifier)
    }
    
    open fun insertItemsWithIdentifiers_beforeItemWithIdentifier(identifiers: MemorySegment, itemIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertItemsWithIdentifiers:beforeItemWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, identifiers, itemIdentifier)
    }
    
    open fun insertItemsWithIdentifiers_afterItemWithIdentifier(identifiers: MemorySegment, itemIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertItemsWithIdentifiers:afterItemWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, identifiers, itemIdentifier)
    }
    
    open fun deleteItemsWithIdentifiers(identifiers: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("deleteItemsWithIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, identifiers)
    }
    
    open fun deleteAllItems(): Unit {
        val sel = ObjCRuntime.sel("deleteAllItems")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun moveItemWithIdentifier_beforeItemWithIdentifier(fromIdentifier: MemorySegment, toIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveItemWithIdentifier:beforeItemWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, fromIdentifier, toIdentifier)
    }
    
    open fun moveItemWithIdentifier_afterItemWithIdentifier(fromIdentifier: MemorySegment, toIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveItemWithIdentifier:afterItemWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, fromIdentifier, toIdentifier)
    }
    
    open fun reloadItemsWithIdentifiers(identifiers: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reloadItemsWithIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, identifiers)
    }
    
    open fun appendSectionsWithIdentifiers(sectionIdentifiers: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("appendSectionsWithIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, sectionIdentifiers)
    }
    
    open fun insertSectionsWithIdentifiers_beforeSectionWithIdentifier(sectionIdentifiers: MemorySegment, toSectionIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertSectionsWithIdentifiers:beforeSectionWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, sectionIdentifiers, toSectionIdentifier)
    }
    
    open fun insertSectionsWithIdentifiers_afterSectionWithIdentifier(sectionIdentifiers: MemorySegment, toSectionIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertSectionsWithIdentifiers:afterSectionWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, sectionIdentifiers, toSectionIdentifier)
    }
    
    open fun deleteSectionsWithIdentifiers(sectionIdentifiers: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("deleteSectionsWithIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, sectionIdentifiers)
    }
    
    open fun moveSectionWithIdentifier_beforeSectionWithIdentifier(fromSectionIdentifier: MemorySegment, toSectionIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveSectionWithIdentifier:beforeSectionWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, fromSectionIdentifier, toSectionIdentifier)
    }
    
    open fun moveSectionWithIdentifier_afterSectionWithIdentifier(fromSectionIdentifier: MemorySegment, toSectionIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveSectionWithIdentifier:afterSectionWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, fromSectionIdentifier, toSectionIdentifier)
    }
    
    open fun reloadSectionsWithIdentifiers(sectionIdentifiers: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reloadSectionsWithIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, sectionIdentifiers)
    }
    
    // @property numberOfItems
    open fun numberOfItems(): Long {
        val sel = ObjCRuntime.sel("numberOfItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property numberOfSections
    open fun numberOfSections(): Long {
        val sel = ObjCRuntime.sel("numberOfSections")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property sectionIdentifiers
    /** @return NSArray<SectionIdentifierType> * */
    open fun sectionIdentifiers(): MemorySegment {
        val sel = ObjCRuntime.sel("sectionIdentifiers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property itemIdentifiers
    /** @return NSArray<ItemIdentifierType> * */
    open fun itemIdentifiers(): MemorySegment {
        val sel = ObjCRuntime.sel("itemIdentifiers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

