package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSLayoutConstraint
 * Superclass: NSObject
 */
open class NSLayoutConstraint(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSLayoutConstraint") }
        
        /** @return NSArray<NSLayoutConstraint *> * */
        fun constraintsWithVisualFormat_options_metrics_views(format: MemorySegment, opts: MemorySegment, metrics: MemorySegment, views: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("constraintsWithVisualFormat:options:metrics:views:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, format, opts, metrics, views) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun constraintsWithVisualFormat_options_metrics_views(format: String, opts: MemorySegment, metrics: MemorySegment, views: MemorySegment): MemorySegment = constraintsWithVisualFormat_options_metrics_views(ObjCRuntime.newNSString(Arena.global(), format), opts, metrics, views)
        
        fun constraintWithItem_attribute_relatedBy_toItem_attribute_multiplier_constant(view1: MemorySegment, attr1: MemorySegment, relation: MemorySegment, view2: MemorySegment, attr2: MemorySegment, multiplier: Double, c: Double): MemorySegment {
            val sel = ObjCRuntime.sel("constraintWithItem:attribute:relatedBy:toItem:attribute:multiplier:constant:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, view1, attr1, relation, view2, attr2, multiplier, c) as MemorySegment
        }
        
        fun activateConstraints(constraints: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("activateConstraints:")
            ObjCRuntime.msgSend(null, _class, sel, constraints)
        }
        
        fun deactivateConstraints(constraints: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("deactivateConstraints:")
            ObjCRuntime.msgSend(null, _class, sel, constraints)
        }
        
    }
    
    // @property priority
    open fun priority(): Float {
        val sel = ObjCRuntime.sel("priority")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    open fun setPriority(value: Float) {
        val sel = ObjCRuntime.sel("setPriority:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property shouldBeArchived
    open fun shouldBeArchived(): Boolean {
        val sel = ObjCRuntime.sel("shouldBeArchived")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShouldBeArchived(value: Boolean) {
        val sel = ObjCRuntime.sel("setShouldBeArchived:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property firstItem
    open fun firstItem(): MemorySegment {
        val sel = ObjCRuntime.sel("firstItem")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property secondItem
    open fun secondItem(): MemorySegment {
        val sel = ObjCRuntime.sel("secondItem")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property firstAttribute
    open fun firstAttribute(): MemorySegment {
        val sel = ObjCRuntime.sel("firstAttribute")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property secondAttribute
    open fun secondAttribute(): MemorySegment {
        val sel = ObjCRuntime.sel("secondAttribute")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property firstAnchor
    open fun firstAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("firstAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property secondAnchor
    open fun secondAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("secondAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property relation
    open fun relation(): MemorySegment {
        val sel = ObjCRuntime.sel("relation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property multiplier
    open fun multiplier(): Double {
        val sel = ObjCRuntime.sel("multiplier")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property constant
    open fun constant(): Double {
        val sel = ObjCRuntime.sel("constant")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setConstant(value: Double) {
        val sel = ObjCRuntime.sel("setConstant:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property active
    open fun isActive(): Boolean {
        val sel = ObjCRuntime.sel("isActive")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setActive(value: Boolean) {
        val sel = ObjCRuntime.sel("setActive:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSIdentifier on NSLayoutConstraint ─────────────────────────────────────────

fun NSLayoutConstraint.identifier(): MemorySegment {
    val sel = ObjCRuntime.sel("identifier")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLayoutConstraint.setIdentifier(identifier: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setIdentifier:")
    ObjCRuntime.msgSend(null, this.ptr, sel, identifier)
}

// ── Category:  on NSLayoutConstraint ─────────────────────────────────────────

