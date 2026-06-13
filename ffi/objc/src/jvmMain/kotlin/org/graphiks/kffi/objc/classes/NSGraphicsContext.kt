package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSGraphicsContext
 * Superclass: NSObject
 */
open class NSGraphicsContext(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSGraphicsContext") }
        
        fun graphicsContextWithAttributes(attributes: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("graphicsContextWithAttributes:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, attributes) as MemorySegment
        }
        
        fun graphicsContextWithBitmapImageRep(bitmapRep: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("graphicsContextWithBitmapImageRep:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, bitmapRep) as MemorySegment
        }
        
        fun graphicsContextWithCGContext_flipped(graphicsPort: MemorySegment, initialFlippedState: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("graphicsContextWithCGContext:flipped:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, graphicsPort, initialFlippedState) as MemorySegment
        }
        
        fun currentContextDrawingToScreen(): Boolean {
            val sel = ObjCRuntime.sel("currentContextDrawingToScreen")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun saveGraphicsState(): Unit {
            val sel = ObjCRuntime.sel("saveGraphicsState")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun restoreGraphicsState(): Unit {
            val sel = ObjCRuntime.sel("restoreGraphicsState")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun currentContext(): MemorySegment {
            val sel = ObjCRuntime.sel("currentContext")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun setCurrentContext(currentContext: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setCurrentContext:")
            ObjCRuntime.msgSend(null, _class, sel, currentContext)
        }
        
    }
    
    open fun saveGraphicsState(): Unit {
        val sel = ObjCRuntime.sel("saveGraphicsState")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun restoreGraphicsState(): Unit {
        val sel = ObjCRuntime.sel("restoreGraphicsState")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun flushGraphics(): Unit {
        val sel = ObjCRuntime.sel("flushGraphics")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property currentContext
    open fun currentContext(): MemorySegment {
        val sel = ObjCRuntime.sel("currentContext")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCurrentContext(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCurrentContext:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property attributes
    /** @return NSDictionary<NSGraphicsContextAttributeKey,id> * */
    open fun attributes(): MemorySegment {
        val sel = ObjCRuntime.sel("attributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property drawingToScreen
    open fun isDrawingToScreen(): Boolean {
        val sel = ObjCRuntime.sel("isDrawingToScreen")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property CGContext
    open fun CGContext(): MemorySegment {
        val sel = ObjCRuntime.sel("CGContext")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property flipped
    open fun isFlipped(): Boolean {
        val sel = ObjCRuntime.sel("isFlipped")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSGraphicsContext_RenderingOptions on NSGraphicsContext ─────────────────────────────────────────

fun NSGraphicsContext.shouldAntialias(): Boolean {
    val sel = ObjCRuntime.sel("shouldAntialias")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSGraphicsContext.setShouldAntialias(shouldAntialias: Boolean): Unit {
    val sel = ObjCRuntime.sel("setShouldAntialias:")
    ObjCRuntime.msgSend(null, this.ptr, sel, shouldAntialias)
}

fun NSGraphicsContext.imageInterpolation(): MemorySegment {
    val sel = ObjCRuntime.sel("imageInterpolation")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSGraphicsContext.setImageInterpolation(imageInterpolation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setImageInterpolation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, imageInterpolation)
}

fun NSGraphicsContext.patternPhase(): MemorySegment {
    val sel = ObjCRuntime.sel("patternPhase")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel) as MemorySegment
}

fun NSGraphicsContext.setPatternPhase(patternPhase: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setPatternPhase:")
    ObjCRuntime.msgSend(null, this.ptr, sel, patternPhase)
}

fun NSGraphicsContext.compositingOperation(): MemorySegment {
    val sel = ObjCRuntime.sel("compositingOperation")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSGraphicsContext.setCompositingOperation(compositingOperation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setCompositingOperation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, compositingOperation)
}

fun NSGraphicsContext.colorRenderingIntent(): MemorySegment {
    val sel = ObjCRuntime.sel("colorRenderingIntent")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSGraphicsContext.setColorRenderingIntent(colorRenderingIntent: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setColorRenderingIntent:")
    ObjCRuntime.msgSend(null, this.ptr, sel, colorRenderingIntent)
}

// ── Category: NSQuartzCoreAdditions on NSGraphicsContext ─────────────────────────────────────────

fun NSGraphicsContext.CIContext(): MemorySegment {
    val sel = ObjCRuntime.sel("CIContext")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSGraphicsContextDeprecated on NSGraphicsContext ─────────────────────────────────────────

fun NSGraphicsContext.focusStack(): MemorySegment {
    val sel = ObjCRuntime.sel("focusStack")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSGraphicsContext.setFocusStack(stack: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setFocusStack:")
    ObjCRuntime.msgSend(null, this.ptr, sel, stack)
}

fun NSGraphicsContext.graphicsPort(): MemorySegment {
    val sel = ObjCRuntime.sel("graphicsPort")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSGraphicsContext setGraphicsState:]
fun NSGraphicsContext_setGraphicsState(gState: Long): Unit {
    val sel = ObjCRuntime.sel("setGraphicsState:")
    val cls = ObjCRuntime.getClass("NSGraphicsContext")
    ObjCRuntime.msgSend(null, cls, sel, gState)
}

// Class method: +[NSGraphicsContext graphicsContextWithGraphicsPort:flipped:]
fun NSGraphicsContext_graphicsContextWithGraphicsPort_flipped(graphicsPort: MemorySegment, initialFlippedState: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("graphicsContextWithGraphicsPort:flipped:")
    val cls = ObjCRuntime.getClass("NSGraphicsContext")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, graphicsPort, initialFlippedState) as MemorySegment
}

// Class method: +[NSGraphicsContext graphicsContextWithWindow:]
fun NSGraphicsContext_graphicsContextWithWindow(window: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("graphicsContextWithWindow:")
    val cls = ObjCRuntime.getClass("NSGraphicsContext")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, window) as MemorySegment
}

