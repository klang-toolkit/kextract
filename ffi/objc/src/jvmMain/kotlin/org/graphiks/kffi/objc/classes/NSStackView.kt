package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSStackView
 * Superclass: NSView
 */
open class NSStackView(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSStackView") }
        
        fun stackViewWithViews(views: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("stackViewWithViews:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, views) as MemorySegment
        }
        
    }
    
    open fun setCustomSpacing_afterView(spacing: Double, view: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setCustomSpacing:afterView:")
        ObjCRuntime.msgSend(null, ptr, sel, spacing, view)
    }
    
    open fun customSpacingAfterView(view: MemorySegment): Double {
        val sel = ObjCRuntime.sel("customSpacingAfterView:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, view) as Double
    }
    
    open fun addArrangedSubview(view: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addArrangedSubview:")
        ObjCRuntime.msgSend(null, ptr, sel, view)
    }
    
    open fun insertArrangedSubview_atIndex(view: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertArrangedSubview:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, view, index)
    }
    
    open fun removeArrangedSubview(view: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeArrangedSubview:")
        ObjCRuntime.msgSend(null, ptr, sel, view)
    }
    
    open fun setVisibilityPriority_forView(priority: Float, view: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setVisibilityPriority:forView:")
        ObjCRuntime.msgSend(null, ptr, sel, priority, view)
    }
    
    open fun visibilityPriorityForView(view: MemorySegment): Float {
        val sel = ObjCRuntime.sel("visibilityPriorityForView:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel, view) as Float
    }
    
    open fun clippingResistancePriorityForOrientation(orientation: MemorySegment): Float {
        val sel = ObjCRuntime.sel("clippingResistancePriorityForOrientation:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel, orientation) as Float
    }
    
    open fun setClippingResistancePriority_forOrientation(clippingResistancePriority: Float, orientation: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setClippingResistancePriority:forOrientation:")
        ObjCRuntime.msgSend(null, ptr, sel, clippingResistancePriority, orientation)
    }
    
    open fun huggingPriorityForOrientation(orientation: MemorySegment): Float {
        val sel = ObjCRuntime.sel("huggingPriorityForOrientation:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel, orientation) as Float
    }
    
    open fun setHuggingPriority_forOrientation(huggingPriority: Float, orientation: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setHuggingPriority:forOrientation:")
        ObjCRuntime.msgSend(null, ptr, sel, huggingPriority, orientation)
    }
    
    // @property delegate
    /** @return id<NSStackViewDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property orientation
    open fun orientation(): MemorySegment {
        val sel = ObjCRuntime.sel("orientation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setOrientation(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setOrientation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property alignment
    open fun alignment(): MemorySegment {
        val sel = ObjCRuntime.sel("alignment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAlignment(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAlignment:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property edgeInsets
    open fun edgeInsets(): MemorySegment {
        val sel = ObjCRuntime.sel("edgeInsets")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), ptr, sel) as MemorySegment
    }
    open fun setEdgeInsets(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEdgeInsets:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets")))
    }
    
    // @property distribution
    open fun distribution(): MemorySegment {
        val sel = ObjCRuntime.sel("distribution")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDistribution(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDistribution:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property spacing
    open fun spacing(): Double {
        val sel = ObjCRuntime.sel("spacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setSpacing(value: Double) {
        val sel = ObjCRuntime.sel("setSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property detachesHiddenViews
    open fun detachesHiddenViews(): Boolean {
        val sel = ObjCRuntime.sel("detachesHiddenViews")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDetachesHiddenViews(value: Boolean) {
        val sel = ObjCRuntime.sel("setDetachesHiddenViews:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property arrangedSubviews
    /** @return NSArray<__kindof NSView *> * */
    open fun arrangedSubviews(): MemorySegment {
        val sel = ObjCRuntime.sel("arrangedSubviews")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property detachedViews
    /** @return NSArray<__kindof NSView *> * */
    open fun detachedViews(): MemorySegment {
        val sel = ObjCRuntime.sel("detachedViews")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSStackViewGravityAreas on NSStackView ─────────────────────────────────────────

fun NSStackView.addView_inGravity(view: MemorySegment, gravity: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addView:inGravity:")
    ObjCRuntime.msgSend(null, this.ptr, sel, view, gravity)
}

fun NSStackView.insertView_atIndex_inGravity(view: MemorySegment, index: Long, gravity: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("insertView:atIndex:inGravity:")
    ObjCRuntime.msgSend(null, this.ptr, sel, view, index, gravity)
}

fun NSStackView.removeView(view: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, view)
}

/** @return NSArray<__kindof NSView *> * */
fun NSStackView.viewsInGravity(gravity: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("viewsInGravity:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, gravity) as MemorySegment
}

fun NSStackView.setViews_inGravity(views: MemorySegment, gravity: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setViews:inGravity:")
    ObjCRuntime.msgSend(null, this.ptr, sel, views, gravity)
}

/** @return NSArray<__kindof NSView *> * */
fun NSStackView.views(): MemorySegment {
    val sel = ObjCRuntime.sel("views")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSStackViewDeprecated on NSStackView ─────────────────────────────────────────

fun NSStackView.hasEqualSpacing(): Boolean {
    val sel = ObjCRuntime.sel("hasEqualSpacing")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSStackView.setHasEqualSpacing(hasEqualSpacing: Boolean): Unit {
    val sel = ObjCRuntime.sel("setHasEqualSpacing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, hasEqualSpacing)
}

