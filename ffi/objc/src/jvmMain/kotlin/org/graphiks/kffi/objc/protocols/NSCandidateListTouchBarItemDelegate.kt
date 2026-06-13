package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCandidateListTouchBarItemDelegate
 * Inherits protocols: NSObject
 */
interface NSCandidateListTouchBarItemDelegate {
    // @optional
    fun candidateListTouchBarItem_beginSelectingCandidateAtIndex(anItem: MemorySegment, index: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'candidateListTouchBarItem:beginSelectingCandidateAtIndex:' not implemented")
    
    // @optional
    fun candidateListTouchBarItem_changeSelectionFromCandidateAtIndex_toIndex(anItem: MemorySegment, previousIndex: Long, index: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'candidateListTouchBarItem:changeSelectionFromCandidateAtIndex:toIndex:' not implemented")
    
    // @optional
    fun candidateListTouchBarItem_endSelectingCandidateAtIndex(anItem: MemorySegment, index: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'candidateListTouchBarItem:endSelectingCandidateAtIndex:' not implemented")
    
    // @optional
    fun candidateListTouchBarItem_changedCandidateListVisibility(anItem: MemorySegment, isVisible: Boolean): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'candidateListTouchBarItem:changedCandidateListVisibility:' not implemented")
    
}

