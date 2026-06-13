package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAlignmentFeedbackFilter
 * Superclass: NSObject
 */
open class NSAlignmentFeedbackFilter(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAlignmentFeedbackFilter") }
        
        fun inputEventMask(): MemorySegment {
            val sel = ObjCRuntime.sel("inputEventMask")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun updateWithEvent(event: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("updateWithEvent:")
        ObjCRuntime.msgSend(null, ptr, sel, event)
    }
    
    open fun updateWithPanRecognizer(panRecognizer: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("updateWithPanRecognizer:")
        ObjCRuntime.msgSend(null, ptr, sel, panRecognizer)
    }
    
    /** @return id<NSAlignmentFeedbackToken> */
    open fun alignmentFeedbackTokenForMovementInView_previousPoint_alignedPoint_defaultPoint(view: MemorySegment, previousPoint: MemorySegment, alignedPoint: MemorySegment, defaultPoint: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("alignmentFeedbackTokenForMovementInView:previousPoint:alignedPoint:defaultPoint:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, view, ObjCRuntime.ObjCStructArg(previousPoint, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), ObjCRuntime.ObjCStructArg(alignedPoint, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), ObjCRuntime.ObjCStructArg(defaultPoint, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    /** @return id<NSAlignmentFeedbackToken> */
    open fun alignmentFeedbackTokenForHorizontalMovementInView_previousX_alignedX_defaultX(view: MemorySegment, previousX: Double, alignedX: Double, defaultX: Double): MemorySegment {
        val sel = ObjCRuntime.sel("alignmentFeedbackTokenForHorizontalMovementInView:previousX:alignedX:defaultX:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, view, previousX, alignedX, defaultX) as MemorySegment
    }
    
    /** @return id<NSAlignmentFeedbackToken> */
    open fun alignmentFeedbackTokenForVerticalMovementInView_previousY_alignedY_defaultY(view: MemorySegment, previousY: Double, alignedY: Double, defaultY: Double): MemorySegment {
        val sel = ObjCRuntime.sel("alignmentFeedbackTokenForVerticalMovementInView:previousY:alignedY:defaultY:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, view, previousY, alignedY, defaultY) as MemorySegment
    }
    
    open fun performFeedback_performanceTime(alignmentFeedbackTokens: MemorySegment, performanceTime: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performFeedback:performanceTime:")
        ObjCRuntime.msgSend(null, ptr, sel, alignmentFeedbackTokens, performanceTime)
    }
    
    // @property inputEventMask
    open fun inputEventMask(): MemorySegment {
        val sel = ObjCRuntime.sel("inputEventMask")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

