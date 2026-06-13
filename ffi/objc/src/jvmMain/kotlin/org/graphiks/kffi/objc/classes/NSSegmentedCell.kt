package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSegmentedCell
 * Superclass: NSActionCell
 */
open class NSSegmentedCell(override val ptr: MemorySegment) : NSActionCell(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSegmentedCell") }
        
    }
    
    open fun selectSegmentWithTag(tag: Long): Boolean {
        val sel = ObjCRuntime.sel("selectSegmentWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, tag) as Boolean
    }
    
    open fun makeNextSegmentKey(): Unit {
        val sel = ObjCRuntime.sel("makeNextSegmentKey")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun makePreviousSegmentKey(): Unit {
        val sel = ObjCRuntime.sel("makePreviousSegmentKey")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setWidth_forSegment(width: Double, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setWidth:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, width, segment)
    }
    
    open fun widthForSegment(segment: Long): Double {
        val sel = ObjCRuntime.sel("widthForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, segment) as Double
    }
    
    open fun setImage_forSegment(image: MemorySegment, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setImage:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, image, segment)
    }
    
    open fun imageForSegment(segment: Long): MemorySegment {
        val sel = ObjCRuntime.sel("imageForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, segment) as MemorySegment
    }
    
    open fun setImageScaling_forSegment(scaling: MemorySegment, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setImageScaling:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, scaling, segment)
    }
    
    open fun imageScalingForSegment(segment: Long): MemorySegment {
        val sel = ObjCRuntime.sel("imageScalingForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, segment) as MemorySegment
    }
    
    open fun setLabel_forSegment(label: MemorySegment, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setLabel:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, label, segment)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setLabel_forSegment(label: String, segment: Long): Unit = setLabel_forSegment(ObjCRuntime.newNSString(Arena.global(), label), segment)
    
    open fun labelForSegment(segment: Long): MemorySegment {
        val sel = ObjCRuntime.sel("labelForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, segment) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun labelForSegmentAsString(segment: Long): String = ObjCRuntime.toJavaString(labelForSegment(segment))
    
    open fun setSelected_forSegment(selected: Boolean, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setSelected:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, selected, segment)
    }
    
    open fun isSelectedForSegment(segment: Long): Boolean {
        val sel = ObjCRuntime.sel("isSelectedForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, segment) as Boolean
    }
    
    open fun setEnabled_forSegment(enabled: Boolean, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setEnabled:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, enabled, segment)
    }
    
    open fun isEnabledForSegment(segment: Long): Boolean {
        val sel = ObjCRuntime.sel("isEnabledForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, segment) as Boolean
    }
    
    open fun setMenu_forSegment(menu: MemorySegment, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setMenu:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, menu, segment)
    }
    
    open fun menuForSegment(segment: Long): MemorySegment {
        val sel = ObjCRuntime.sel("menuForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, segment) as MemorySegment
    }
    
    open fun setToolTip_forSegment(toolTip: MemorySegment, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setToolTip:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, toolTip, segment)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setToolTip_forSegment(toolTip: String, segment: Long): Unit = setToolTip_forSegment(ObjCRuntime.newNSString(Arena.global(), toolTip), segment)
    
    open fun toolTipForSegment(segment: Long): MemorySegment {
        val sel = ObjCRuntime.sel("toolTipForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, segment) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun toolTipForSegmentAsString(segment: Long): String = ObjCRuntime.toJavaString(toolTipForSegment(segment))
    
    open fun setTag_forSegment(tag: Long, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setTag:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, tag, segment)
    }
    
    open fun tagForSegment(segment: Long): Long {
        val sel = ObjCRuntime.sel("tagForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, segment) as Long
    }
    
    open fun drawSegment_inFrame_withView(segment: Long, frame: MemorySegment, controlView: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawSegment:inFrame:withView:")
        ObjCRuntime.msgSend(null, ptr, sel, segment, ObjCRuntime.ObjCStructArg(frame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), controlView)
    }
    
    // @property segmentCount
    open fun segmentCount(): Long {
        val sel = ObjCRuntime.sel("segmentCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setSegmentCount(value: Long) {
        val sel = ObjCRuntime.sel("setSegmentCount:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectedSegment
    open fun selectedSegment(): Long {
        val sel = ObjCRuntime.sel("selectedSegment")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setSelectedSegment(value: Long) {
        val sel = ObjCRuntime.sel("setSelectedSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property trackingMode
    open fun trackingMode(): MemorySegment {
        val sel = ObjCRuntime.sel("trackingMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTrackingMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTrackingMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property segmentStyle
    open fun segmentStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("segmentStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSegmentStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSegmentStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSSegmentBackgroundStyle on NSSegmentedCell ─────────────────────────────────────────

fun NSSegmentedCell.interiorBackgroundStyleForSegment(segment: Long): MemorySegment {
    val sel = ObjCRuntime.sel("interiorBackgroundStyleForSegment:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, segment) as MemorySegment
}

