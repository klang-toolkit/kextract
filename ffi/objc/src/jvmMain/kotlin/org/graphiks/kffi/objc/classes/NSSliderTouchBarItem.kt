package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSliderTouchBarItem
 * Superclass: NSTouchBarItem
 */
open class NSSliderTouchBarItem(override val ptr: MemorySegment) : NSTouchBarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSliderTouchBarItem") }
        
    }
    
    // @property view
    /** @return NSView<NSUserInterfaceCompression> * */
    override fun view(): MemorySegment {
        val sel = ObjCRuntime.sel("view")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property slider
    open fun slider(): MemorySegment {
        val sel = ObjCRuntime.sel("slider")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSlider(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSlider:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property doubleValue
    open fun doubleValue(): Double {
        val sel = ObjCRuntime.sel("doubleValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setDoubleValue(value: Double) {
        val sel = ObjCRuntime.sel("setDoubleValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minimumSliderWidth
    open fun minimumSliderWidth(): Double {
        val sel = ObjCRuntime.sel("minimumSliderWidth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinimumSliderWidth(value: Double) {
        val sel = ObjCRuntime.sel("setMinimumSliderWidth:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maximumSliderWidth
    open fun maximumSliderWidth(): Double {
        val sel = ObjCRuntime.sel("maximumSliderWidth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMaximumSliderWidth(value: Double) {
        val sel = ObjCRuntime.sel("setMaximumSliderWidth:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property label
    open fun label(): MemorySegment {
        val sel = ObjCRuntime.sel("label")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun labelAsString(): String = ObjCRuntime.toJavaString(label())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setLabel(value: String) = setLabel(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property minimumValueAccessory
    open fun minimumValueAccessory(): MemorySegment {
        val sel = ObjCRuntime.sel("minimumValueAccessory")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMinimumValueAccessory(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMinimumValueAccessory:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maximumValueAccessory
    open fun maximumValueAccessory(): MemorySegment {
        val sel = ObjCRuntime.sel("maximumValueAccessory")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMaximumValueAccessory(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMaximumValueAccessory:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property valueAccessoryWidth
    open fun valueAccessoryWidth(): Double {
        val sel = ObjCRuntime.sel("valueAccessoryWidth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setValueAccessoryWidth(value: Double) {
        val sel = ObjCRuntime.sel("setValueAccessoryWidth:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property target
    open fun target(): MemorySegment {
        val sel = ObjCRuntime.sel("target")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTarget(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTarget:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property action
    open fun action(): MemorySegment {
        val sel = ObjCRuntime.sel("action")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAction(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAction:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property customizationLabel
    override fun customizationLabel(): MemorySegment {
        val sel = ObjCRuntime.sel("customizationLabel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCustomizationLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCustomizationLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

