package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSProgressIndicator
 * Superclass: NSView
 * Protocols: NSAccessibilityProgressIndicator
 */
open class NSProgressIndicator(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSProgressIndicator") }
        
    }
    
    open fun incrementBy(delta: Double): Unit {
        val sel = ObjCRuntime.sel("incrementBy:")
        ObjCRuntime.msgSend(null, ptr, sel, delta)
    }
    
    open fun startAnimation(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("startAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun stopAnimation(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("stopAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun sizeToFit(): Unit {
        val sel = ObjCRuntime.sel("sizeToFit")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property indeterminate
    open fun isIndeterminate(): Boolean {
        val sel = ObjCRuntime.sel("isIndeterminate")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIndeterminate(value: Boolean) {
        val sel = ObjCRuntime.sel("setIndeterminate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property controlSize
    open fun controlSize(): MemorySegment {
        val sel = ObjCRuntime.sel("controlSize")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setControlSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setControlSize:")
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
    
    // @property observedProgress
    open fun observedProgress(): MemorySegment {
        val sel = ObjCRuntime.sel("observedProgress")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setObservedProgress(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setObservedProgress:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesThreadedAnimation
    open fun usesThreadedAnimation(): Boolean {
        val sel = ObjCRuntime.sel("usesThreadedAnimation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesThreadedAnimation(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesThreadedAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property style
    open fun style(): MemorySegment {
        val sel = ObjCRuntime.sel("style")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property displayedWhenStopped
    open fun isDisplayedWhenStopped(): Boolean {
        val sel = ObjCRuntime.sel("isDisplayedWhenStopped")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDisplayedWhenStopped(value: Boolean) {
        val sel = ObjCRuntime.sel("setDisplayedWhenStopped:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSProgressIndicatorDeprecated on NSProgressIndicator ─────────────────────────────────────────

fun NSProgressIndicator.animationDelay(): Double {
    val sel = ObjCRuntime.sel("animationDelay")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSProgressIndicator.setAnimationDelay(delay: Double): Unit {
    val sel = ObjCRuntime.sel("setAnimationDelay:")
    ObjCRuntime.msgSend(null, this.ptr, sel, delay)
}

fun NSProgressIndicator.animate(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("animate:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSProgressIndicator.isBezeled(): Boolean {
    val sel = ObjCRuntime.sel("isBezeled")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSProgressIndicator.setBezeled(bezeled: Boolean): Unit {
    val sel = ObjCRuntime.sel("setBezeled:")
    ObjCRuntime.msgSend(null, this.ptr, sel, bezeled)
}

fun NSProgressIndicator.controlTint(): MemorySegment {
    val sel = ObjCRuntime.sel("controlTint")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSProgressIndicator.setControlTint(controlTint: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setControlTint:")
    ObjCRuntime.msgSend(null, this.ptr, sel, controlTint)
}

