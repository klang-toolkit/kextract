package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSStepperTouchBarItem
 * Superclass: NSTouchBarItem
 */
open class NSStepperTouchBarItem(override val ptr: MemorySegment) : NSTouchBarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSStepperTouchBarItem") }
        
        fun stepperTouchBarItemWithIdentifier_formatter(identifier: MemorySegment, formatter: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("stepperTouchBarItemWithIdentifier:formatter:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, formatter) as MemorySegment
        }
        
        fun stepperTouchBarItemWithIdentifier_drawingHandler(identifier: MemorySegment, drawingHandler: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("stepperTouchBarItemWithIdentifier:drawingHandler:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, drawingHandler) as MemorySegment
        }
        
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
    
    // @property minValue
    open fun minValue(): Double {
        val sel = ObjCRuntime.sel("minValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinValue(value: Double) {
        val sel = ObjCRuntime.sel("setMinValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property increment
    open fun increment(): Double {
        val sel = ObjCRuntime.sel("increment")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setIncrement(value: Double) {
        val sel = ObjCRuntime.sel("setIncrement:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property value
    open fun value(): Double {
        val sel = ObjCRuntime.sel("value")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setValue(value: Double) {
        val sel = ObjCRuntime.sel("setValue:")
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

