package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSegmentedControl
 * Superclass: NSControl
 * Protocols: NSUserInterfaceCompression
 */
open class NSSegmentedControl(override val ptr: MemorySegment) : NSControl(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSegmentedControl") }
        
    }
    
    open fun selectSegmentWithTag(tag: Long): Boolean {
        val sel = ObjCRuntime.sel("selectSegmentWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, tag) as Boolean
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
    
    open fun setMenu_forSegment(menu: MemorySegment, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setMenu:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, menu, segment)
    }
    
    open fun menuForSegment(segment: Long): MemorySegment {
        val sel = ObjCRuntime.sel("menuForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, segment) as MemorySegment
    }
    
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
    
    open fun setShowsMenuIndicator_forSegment(showsMenuIndicator: Boolean, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setShowsMenuIndicator:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, showsMenuIndicator, segment)
    }
    
    open fun showsMenuIndicatorForSegment(segment: Long): Boolean {
        val sel = ObjCRuntime.sel("showsMenuIndicatorForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, segment) as Boolean
    }
    
    open fun setAlignment_forSegment(alignment: MemorySegment, segment: Long): Unit {
        val sel = ObjCRuntime.sel("setAlignment:forSegment:")
        ObjCRuntime.msgSend(null, ptr, sel, alignment, segment)
    }
    
    open fun alignmentForSegment(segment: Long): MemorySegment {
        val sel = ObjCRuntime.sel("alignmentForSegment:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, segment) as MemorySegment
    }
    
    open fun compressWithPrioritizedCompressionOptions(prioritizedOptions: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("compressWithPrioritizedCompressionOptions:")
        ObjCRuntime.msgSend(null, ptr, sel, prioritizedOptions)
    }
    
    open fun minimumSizeWithPrioritizedCompressionOptions(prioritizedOptions: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("minimumSizeWithPrioritizedCompressionOptions:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, prioritizedOptions) as MemorySegment
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
    
    // @property segmentStyle
    open fun segmentStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("segmentStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSegmentStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSegmentStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property springLoaded
    open fun isSpringLoaded(): Boolean {
        val sel = ObjCRuntime.sel("isSpringLoaded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSpringLoaded(value: Boolean) {
        val sel = ObjCRuntime.sel("setSpringLoaded:")
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
    
    // @property doubleValueForSelectedSegment
    open fun doubleValueForSelectedSegment(): Double {
        val sel = ObjCRuntime.sel("doubleValueForSelectedSegment")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property selectedSegmentBezelColor
    open fun selectedSegmentBezelColor(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedSegmentBezelColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectedSegmentBezelColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectedSegmentBezelColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property indexOfSelectedItem
    open fun indexOfSelectedItem(): Long {
        val sel = ObjCRuntime.sel("indexOfSelectedItem")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property segmentDistribution
    open fun segmentDistribution(): MemorySegment {
        val sel = ObjCRuntime.sel("segmentDistribution")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSegmentDistribution(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSegmentDistribution:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property activeCompressionOptions
    open fun activeCompressionOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("activeCompressionOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property borderShape
    open fun borderShape(): MemorySegment {
        val sel = ObjCRuntime.sel("borderShape")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBorderShape(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBorderShape:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSSegmentedControlConvenience on NSSegmentedControl ─────────────────────────────────────────

// Class method: +[NSSegmentedControl segmentedControlWithLabels:trackingMode:target:action:]
fun NSSegmentedControl_segmentedControlWithLabels_trackingMode_target_action(labels: MemorySegment, trackingMode: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("segmentedControlWithLabels:trackingMode:target:action:")
    val cls = ObjCRuntime.getClass("NSSegmentedControl")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, labels, trackingMode, target, action) as MemorySegment
}

// Class method: +[NSSegmentedControl segmentedControlWithImages:trackingMode:target:action:]
fun NSSegmentedControl_segmentedControlWithImages_trackingMode_target_action(images: MemorySegment, trackingMode: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("segmentedControlWithImages:trackingMode:target:action:")
    val cls = ObjCRuntime.getClass("NSSegmentedControl")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, images, trackingMode, target, action) as MemorySegment
}

