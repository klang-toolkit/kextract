package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSplitViewDelegate
 * Inherits protocols: NSObject
 */
interface NSSplitViewDelegate {
    // @optional
    fun splitView_canCollapseSubview(splitView: MemorySegment, subview: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:canCollapseSubview:' not implemented")
    
    // @optional
    fun splitView_shouldCollapseSubview_forDoubleClickOnDividerAtIndex(splitView: MemorySegment, subview: MemorySegment, dividerIndex: Long): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:shouldCollapseSubview:forDoubleClickOnDividerAtIndex:' not implemented")
    
    // @optional
    fun splitView_constrainMinCoordinate_ofSubviewAt(splitView: MemorySegment, proposedMinimumPosition: Double, dividerIndex: Long): Double =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:constrainMinCoordinate:ofSubviewAt:' not implemented")
    
    // @optional
    fun splitView_constrainMaxCoordinate_ofSubviewAt(splitView: MemorySegment, proposedMaximumPosition: Double, dividerIndex: Long): Double =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:constrainMaxCoordinate:ofSubviewAt:' not implemented")
    
    // @optional
    fun splitView_constrainSplitPosition_ofSubviewAt(splitView: MemorySegment, proposedPosition: Double, dividerIndex: Long): Double =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:constrainSplitPosition:ofSubviewAt:' not implemented")
    
    // @optional
    fun splitView_resizeSubviewsWithOldSize(splitView: MemorySegment, oldSize: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:resizeSubviewsWithOldSize:' not implemented")
    
    // @optional
    fun splitView_shouldAdjustSizeOfSubview(splitView: MemorySegment, view: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:shouldAdjustSizeOfSubview:' not implemented")
    
    // @optional
    fun splitView_shouldHideDividerAtIndex(splitView: MemorySegment, dividerIndex: Long): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:shouldHideDividerAtIndex:' not implemented")
    
    // @optional
    fun splitView_effectiveRect_forDrawnRect_ofDividerAtIndex(splitView: MemorySegment, proposedEffectiveRect: MemorySegment, drawnRect: MemorySegment, dividerIndex: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:effectiveRect:forDrawnRect:ofDividerAtIndex:' not implemented")
    
    // @optional
    fun splitView_additionalEffectiveRectOfDividerAtIndex(splitView: MemorySegment, dividerIndex: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'splitView:additionalEffectiveRectOfDividerAtIndex:' not implemented")
    
    // @optional
    fun splitViewWillResizeSubviews(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'splitViewWillResizeSubviews:' not implemented")
    
    // @optional
    fun splitViewDidResizeSubviews(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'splitViewDidResizeSubviews:' not implemented")
    
}

