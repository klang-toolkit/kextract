package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSlider
 * Superclass: NSControl
 * Protocols: NSAccessibilitySlider
 */
open class NSSlider(override val ptr: MemorySegment) : NSControl(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSlider") }
        
    }
    
    override fun acceptsFirstMouse(event: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("acceptsFirstMouse:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, event) as Boolean
    }
    
    // @property sliderType
    open fun sliderType(): MemorySegment {
        val sel = ObjCRuntime.sel("sliderType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSliderType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSliderType:")
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
    
    // @property neutralValue
    open fun neutralValue(): Double {
        val sel = ObjCRuntime.sel("neutralValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setNeutralValue(value: Double) {
        val sel = ObjCRuntime.sel("setNeutralValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property altIncrementValue
    open fun altIncrementValue(): Double {
        val sel = ObjCRuntime.sel("altIncrementValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setAltIncrementValue(value: Double) {
        val sel = ObjCRuntime.sel("setAltIncrementValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property knobThickness
    open fun knobThickness(): Double {
        val sel = ObjCRuntime.sel("knobThickness")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
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
    
    // @property trackFillColor
    open fun trackFillColor(): MemorySegment {
        val sel = ObjCRuntime.sel("trackFillColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTrackFillColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTrackFillColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tintProminence
    open fun tintProminence(): MemorySegment {
        val sel = ObjCRuntime.sel("tintProminence")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTintProminence(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTintProminence:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSSliderVerticalGetter on NSSlider ─────────────────────────────────────────

// ── Category: NSTickMarkSupport on NSSlider ─────────────────────────────────────────

fun NSSlider.tickMarkValueAtIndex(index: Long): Double {
    val sel = ObjCRuntime.sel("tickMarkValueAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel, index) as Double
}

fun NSSlider.rectOfTickMarkAtIndex(index: Long): MemorySegment {
    val sel = ObjCRuntime.sel("rectOfTickMarkAtIndex:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, index) as MemorySegment
}

fun NSSlider.indexOfTickMarkAtPoint(point: MemorySegment): Long {
    val sel = ObjCRuntime.sel("indexOfTickMarkAtPoint:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, point) as Long
}

fun NSSlider.closestTickMarkValueToValue(value: Double): Double {
    val sel = ObjCRuntime.sel("closestTickMarkValueToValue:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel, value) as Double
}

fun NSSlider.numberOfTickMarks(): Long {
    val sel = ObjCRuntime.sel("numberOfTickMarks")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSSlider.setNumberOfTickMarks(numberOfTickMarks: Long): Unit {
    val sel = ObjCRuntime.sel("setNumberOfTickMarks:")
    ObjCRuntime.msgSend(null, this.ptr, sel, numberOfTickMarks)
}

fun NSSlider.tickMarkPosition(): MemorySegment {
    val sel = ObjCRuntime.sel("tickMarkPosition")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSSlider.setTickMarkPosition(tickMarkPosition: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTickMarkPosition:")
    ObjCRuntime.msgSend(null, this.ptr, sel, tickMarkPosition)
}

fun NSSlider.allowsTickMarkValuesOnly(): Boolean {
    val sel = ObjCRuntime.sel("allowsTickMarkValuesOnly")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSSlider.setAllowsTickMarkValuesOnly(allowsTickMarkValuesOnly: Boolean): Unit {
    val sel = ObjCRuntime.sel("setAllowsTickMarkValuesOnly:")
    ObjCRuntime.msgSend(null, this.ptr, sel, allowsTickMarkValuesOnly)
}

// ── Category: NSSliderConvenience on NSSlider ─────────────────────────────────────────

// Class method: +[NSSlider sliderWithTarget:action:]
fun NSSlider_sliderWithTarget_action(target: MemorySegment, action: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sliderWithTarget:action:")
    val cls = ObjCRuntime.getClass("NSSlider")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, target, action) as MemorySegment
}

// Class method: +[NSSlider sliderWithValue:minValue:maxValue:target:action:]
fun NSSlider_sliderWithValue_minValue_maxValue_target_action(value: Double, minValue: Double, maxValue: Double, target: MemorySegment, action: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sliderWithValue:minValue:maxValue:target:action:")
    val cls = ObjCRuntime.getClass("NSSlider")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value, minValue, maxValue, target, action) as MemorySegment
}

// ── Category: NSSliderDeprecated on NSSlider ─────────────────────────────────────────

fun NSSlider.setTitleCell(cell: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTitleCell:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cell)
}

fun NSSlider.titleCell(): MemorySegment {
    val sel = ObjCRuntime.sel("titleCell")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSSlider.setTitleColor(newColor: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTitleColor:")
    ObjCRuntime.msgSend(null, this.ptr, sel, newColor)
}

fun NSSlider.titleColor(): MemorySegment {
    val sel = ObjCRuntime.sel("titleColor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSSlider.setTitleFont(fontObj: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTitleFont:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fontObj)
}

fun NSSlider.titleFont(): MemorySegment {
    val sel = ObjCRuntime.sel("titleFont")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSSlider.title(): MemorySegment {
    val sel = ObjCRuntime.sel("title")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSSlider.setTitle(string: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTitle:")
    ObjCRuntime.msgSend(null, this.ptr, sel, string)
}

fun NSSlider.setKnobThickness(thickness: Double): Unit {
    val sel = ObjCRuntime.sel("setKnobThickness:")
    ObjCRuntime.msgSend(null, this.ptr, sel, thickness)
}

fun NSSlider.setImage(backgroundImage: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setImage:")
    ObjCRuntime.msgSend(null, this.ptr, sel, backgroundImage)
}

fun NSSlider.image(): MemorySegment {
    val sel = ObjCRuntime.sel("image")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

