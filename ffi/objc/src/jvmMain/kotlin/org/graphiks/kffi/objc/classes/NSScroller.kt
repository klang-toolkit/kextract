package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSScroller
 * Superclass: NSControl
 */
open class NSScroller(override val ptr: MemorySegment) : NSControl(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSScroller") }
        
        fun scrollerWidthForControlSize_scrollerStyle(controlSize: MemorySegment, scrollerStyle: MemorySegment): Double {
            val sel = ObjCRuntime.sel("scrollerWidthForControlSize:scrollerStyle:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel, controlSize, scrollerStyle) as Double
        }
        
        fun isCompatibleWithOverlayScrollers(): Boolean {
            val sel = ObjCRuntime.sel("isCompatibleWithOverlayScrollers")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun preferredScrollerStyle(): MemorySegment {
            val sel = ObjCRuntime.sel("preferredScrollerStyle")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun rectForPart(partCode: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("rectForPart:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, partCode) as MemorySegment
    }
    
    open fun checkSpaceForParts(): Unit {
        val sel = ObjCRuntime.sel("checkSpaceForParts")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun drawKnob(): Unit {
        val sel = ObjCRuntime.sel("drawKnob")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun drawKnobSlotInRect_highlight(slotRect: MemorySegment, flag: Boolean): Unit {
        val sel = ObjCRuntime.sel("drawKnobSlotInRect:highlight:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(slotRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), flag)
    }
    
    open fun testPart(point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("testPart:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun trackKnob(event: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("trackKnob:")
        ObjCRuntime.msgSend(null, ptr, sel, event)
    }
    
    // @property compatibleWithOverlayScrollers
    open fun isCompatibleWithOverlayScrollers(): Boolean {
        val sel = ObjCRuntime.sel("isCompatibleWithOverlayScrollers")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property preferredScrollerStyle
    open fun preferredScrollerStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("preferredScrollerStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property scrollerStyle
    open fun scrollerStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("scrollerStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScrollerStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScrollerStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property knobStyle
    open fun knobStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("knobStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setKnobStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setKnobStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usableParts
    open fun usableParts(): MemorySegment {
        val sel = ObjCRuntime.sel("usableParts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property controlSize
    override fun controlSize(): MemorySegment {
        val sel = ObjCRuntime.sel("controlSize")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setControlSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setControlSize:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hitPart
    open fun hitPart(): MemorySegment {
        val sel = ObjCRuntime.sel("hitPart")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property knobProportion
    open fun knobProportion(): Double {
        val sel = ObjCRuntime.sel("knobProportion")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setKnobProportion(value: Double) {
        val sel = ObjCRuntime.sel("setKnobProportion:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSDeprecated on NSScroller ─────────────────────────────────────────

fun NSScroller.setFloatValue_knobProportion(value: Float, proportion: Double): Unit {
    val sel = ObjCRuntime.sel("setFloatValue:knobProportion:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, proportion)
}

fun NSScroller.highlight(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("highlight:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSScroller.trackScrollButtons(event: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("trackScrollButtons:")
    ObjCRuntime.msgSend(null, this.ptr, sel, event)
}

fun NSScroller.drawParts(): Unit {
    val sel = ObjCRuntime.sel("drawParts")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSScroller.drawArrow_highlight(whichArrow: MemorySegment, flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("drawArrow:highlight:")
    ObjCRuntime.msgSend(null, this.ptr, sel, whichArrow, flag)
}

fun NSScroller.arrowsPosition(): MemorySegment {
    val sel = ObjCRuntime.sel("arrowsPosition")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSScroller.setArrowsPosition(arrowsPosition: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setArrowsPosition:")
    ObjCRuntime.msgSend(null, this.ptr, sel, arrowsPosition)
}

fun NSScroller.controlTint(): MemorySegment {
    val sel = ObjCRuntime.sel("controlTint")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSScroller.setControlTint(controlTint: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setControlTint:")
    ObjCRuntime.msgSend(null, this.ptr, sel, controlTint)
}

// Class method: +[NSScroller scrollerWidthForControlSize:]
fun NSScroller_scrollerWidthForControlSize(controlSize: MemorySegment): Double {
    val sel = ObjCRuntime.sel("scrollerWidthForControlSize:")
    val cls = ObjCRuntime.getClass("NSScroller")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, cls, sel, controlSize) as Double
}

// Class method: +[NSScroller scrollerWidth]
fun NSScroller_scrollerWidth(): Double {
    val sel = ObjCRuntime.sel("scrollerWidth")
    val cls = ObjCRuntime.getClass("NSScroller")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, cls, sel) as Double
}

