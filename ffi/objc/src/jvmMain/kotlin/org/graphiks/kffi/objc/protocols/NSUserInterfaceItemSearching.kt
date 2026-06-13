package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSUserInterfaceItemSearching
 * Inherits protocols: NSObject
 */
interface NSUserInterfaceItemSearching {
    fun searchForItemsWithSearchString_resultLimit_matchedItemHandler(searchString: MemorySegment, resultLimit: Long, handleMatchedItems: MemorySegment): Unit
    
    /** @return NSArray<NSString *> * */
    fun localizedTitlesForItem(item: MemorySegment): MemorySegment
    
    // @optional
    fun performActionForItem(item: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'performActionForItem:' not implemented")
    
    // @optional
    fun showAllHelpTopicsForSearchString(searchString: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'showAllHelpTopicsForSearchString:' not implemented")
    
}

