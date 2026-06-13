package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSComparisonPredicate
 * Superclass: NSPredicate
 */
open class NSComparisonPredicate(override val ptr: MemorySegment) : NSPredicate(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSComparisonPredicate") }
        
        fun predicateWithLeftExpression_rightExpression_modifier_type_options(lhs: MemorySegment, rhs: MemorySegment, modifier: MemorySegment, type: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("predicateWithLeftExpression:rightExpression:modifier:type:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, lhs, rhs, modifier, type, options) as MemorySegment
        }
        
        fun predicateWithLeftExpression_rightExpression_customSelector(lhs: MemorySegment, rhs: MemorySegment, selector: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("predicateWithLeftExpression:rightExpression:customSelector:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, lhs, rhs, selector) as MemorySegment
        }
        
    }
    
    open fun initWithLeftExpression_rightExpression_modifier_type_options(lhs: MemorySegment, rhs: MemorySegment, modifier: MemorySegment, type: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithLeftExpression:rightExpression:modifier:type:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, lhs, rhs, modifier, type, options) as MemorySegment
    }
    
    open fun initWithLeftExpression_rightExpression_customSelector(lhs: MemorySegment, rhs: MemorySegment, selector: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithLeftExpression:rightExpression:customSelector:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, lhs, rhs, selector) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    // @property predicateOperatorType
    open fun predicateOperatorType(): MemorySegment {
        val sel = ObjCRuntime.sel("predicateOperatorType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property comparisonPredicateModifier
    open fun comparisonPredicateModifier(): MemorySegment {
        val sel = ObjCRuntime.sel("comparisonPredicateModifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property leftExpression
    open fun leftExpression(): MemorySegment {
        val sel = ObjCRuntime.sel("leftExpression")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property rightExpression
    open fun rightExpression(): MemorySegment {
        val sel = ObjCRuntime.sel("rightExpression")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property customSelector
    open fun customSelector(): MemorySegment {
        val sel = ObjCRuntime.sel("customSelector")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property options
    open fun options(): MemorySegment {
        val sel = ObjCRuntime.sel("options")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

