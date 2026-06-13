package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSplitView
 * Superclass: NSView
 */
open class NSSplitView(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSplitView") }
        
    }
    
    open fun drawDividerInRect(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawDividerInRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun adjustSubviews(): Unit {
        val sel = ObjCRuntime.sel("adjustSubviews")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun isSubviewCollapsed(subview: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isSubviewCollapsed:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, subview) as Boolean
    }
    
    open fun minPossiblePositionOfDividerAtIndex(dividerIndex: Long): Double {
        val sel = ObjCRuntime.sel("minPossiblePositionOfDividerAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, dividerIndex) as Double
    }
    
    open fun maxPossiblePositionOfDividerAtIndex(dividerIndex: Long): Double {
        val sel = ObjCRuntime.sel("maxPossiblePositionOfDividerAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, dividerIndex) as Double
    }
    
    open fun setPosition_ofDividerAtIndex(position: Double, dividerIndex: Long): Unit {
        val sel = ObjCRuntime.sel("setPosition:ofDividerAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, position, dividerIndex)
    }
    
    open fun holdingPriorityForSubviewAtIndex(subviewIndex: Long): Float {
        val sel = ObjCRuntime.sel("holdingPriorityForSubviewAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel, subviewIndex) as Float
    }
    
    open fun setHoldingPriority_forSubviewAtIndex(priority: Float, subviewIndex: Long): Unit {
        val sel = ObjCRuntime.sel("setHoldingPriority:forSubviewAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, priority, subviewIndex)
    }
    
    // @property vertical
    open fun isVertical(): Boolean {
        val sel = ObjCRuntime.sel("isVertical")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setVertical(value: Boolean) {
        val sel = ObjCRuntime.sel("setVertical:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property dividerStyle
    open fun dividerStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("dividerStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDividerStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDividerStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autosaveName
    open fun autosaveName(): MemorySegment {
        val sel = ObjCRuntime.sel("autosaveName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAutosaveName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAutosaveName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSSplitViewDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property dividerColor
    open fun dividerColor(): MemorySegment {
        val sel = ObjCRuntime.sel("dividerColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property dividerThickness
    open fun dividerThickness(): Double {
        val sel = ObjCRuntime.sel("dividerThickness")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
}

// ── Category: NSSplitViewArrangedSubviews on NSSplitView ─────────────────────────────────────────

fun NSSplitView.addArrangedSubview(view: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addArrangedSubview:")
    ObjCRuntime.msgSend(null, this.ptr, sel, view)
}

fun NSSplitView.insertArrangedSubview_atIndex(view: MemorySegment, index: Long): Unit {
    val sel = ObjCRuntime.sel("insertArrangedSubview:atIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, view, index)
}

fun NSSplitView.removeArrangedSubview(view: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeArrangedSubview:")
    ObjCRuntime.msgSend(null, this.ptr, sel, view)
}

fun NSSplitView.arrangesAllSubviews(): Boolean {
    val sel = ObjCRuntime.sel("arrangesAllSubviews")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSSplitView.setArrangesAllSubviews(arrangesAllSubviews: Boolean): Unit {
    val sel = ObjCRuntime.sel("setArrangesAllSubviews:")
    ObjCRuntime.msgSend(null, this.ptr, sel, arrangesAllSubviews)
}

/** @return NSArray<__kindof NSView *> * */
fun NSSplitView.arrangedSubviews(): MemorySegment {
    val sel = ObjCRuntime.sel("arrangedSubviews")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSDeprecated on NSSplitView ─────────────────────────────────────────

fun NSSplitView.setIsPaneSplitter(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setIsPaneSplitter:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSSplitView.isPaneSplitter(): Boolean {
    val sel = ObjCRuntime.sel("isPaneSplitter")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

