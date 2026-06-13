package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSLevelIndicator
 * Superclass: NSControl
 */
open class NSLevelIndicator(override val ptr: MemorySegment) : NSControl(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSLevelIndicator") }
        
    }
    
    open fun tickMarkValueAtIndex(index: Long): Double {
        val sel = ObjCRuntime.sel("tickMarkValueAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, index) as Double
    }
    
    open fun rectOfTickMarkAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("rectOfTickMarkAtIndex:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, index) as MemorySegment
    }
    
    // @property levelIndicatorStyle
    open fun levelIndicatorStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("levelIndicatorStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLevelIndicatorStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLevelIndicatorStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property editable
    open fun isEditable(): Boolean {
        val sel = ObjCRuntime.sel("isEditable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEditable(value: Boolean) {
        val sel = ObjCRuntime.sel("setEditable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minValue
    open fun minValue(): Double {
        val sel = ObjCRuntime.sel("minValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinValue(value: Double) {
        val sel = ObjCRuntime.sel("setMinValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maxValue
    open fun maxValue(): Double {
        val sel = ObjCRuntime.sel("maxValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMaxValue(value: Double) {
        val sel = ObjCRuntime.sel("setMaxValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property warningValue
    open fun warningValue(): Double {
        val sel = ObjCRuntime.sel("warningValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setWarningValue(value: Double) {
        val sel = ObjCRuntime.sel("setWarningValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property criticalValue
    open fun criticalValue(): Double {
        val sel = ObjCRuntime.sel("criticalValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setCriticalValue(value: Double) {
        val sel = ObjCRuntime.sel("setCriticalValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tickMarkPosition
    open fun tickMarkPosition(): MemorySegment {
        val sel = ObjCRuntime.sel("tickMarkPosition")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTickMarkPosition(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTickMarkPosition:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberOfTickMarks
    open fun numberOfTickMarks(): Long {
        val sel = ObjCRuntime.sel("numberOfTickMarks")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setNumberOfTickMarks(value: Long) {
        val sel = ObjCRuntime.sel("setNumberOfTickMarks:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberOfMajorTickMarks
    open fun numberOfMajorTickMarks(): Long {
        val sel = ObjCRuntime.sel("numberOfMajorTickMarks")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setNumberOfMajorTickMarks(value: Long) {
        val sel = ObjCRuntime.sel("setNumberOfMajorTickMarks:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property fillColor
    open fun fillColor(): MemorySegment {
        val sel = ObjCRuntime.sel("fillColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFillColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFillColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property warningFillColor
    open fun warningFillColor(): MemorySegment {
        val sel = ObjCRuntime.sel("warningFillColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setWarningFillColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setWarningFillColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property criticalFillColor
    open fun criticalFillColor(): MemorySegment {
        val sel = ObjCRuntime.sel("criticalFillColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCriticalFillColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCriticalFillColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property drawsTieredCapacityLevels
    open fun drawsTieredCapacityLevels(): Boolean {
        val sel = ObjCRuntime.sel("drawsTieredCapacityLevels")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDrawsTieredCapacityLevels(value: Boolean) {
        val sel = ObjCRuntime.sel("setDrawsTieredCapacityLevels:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property placeholderVisibility
    open fun placeholderVisibility(): MemorySegment {
        val sel = ObjCRuntime.sel("placeholderVisibility")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPlaceholderVisibility(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPlaceholderVisibility:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property ratingImage
    open fun ratingImage(): MemorySegment {
        val sel = ObjCRuntime.sel("ratingImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRatingImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRatingImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property ratingPlaceholderImage
    open fun ratingPlaceholderImage(): MemorySegment {
        val sel = ObjCRuntime.sel("ratingPlaceholderImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRatingPlaceholderImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRatingPlaceholderImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

