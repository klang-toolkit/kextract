package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCursor
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSCursor(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCursor") }
        
        fun hide(): Unit {
            val sel = ObjCRuntime.sel("hide")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun unhide(): Unit {
            val sel = ObjCRuntime.sel("unhide")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun setHiddenUntilMouseMoves(flag: Boolean): Unit {
            val sel = ObjCRuntime.sel("setHiddenUntilMouseMoves:")
            ObjCRuntime.msgSend(null, _class, sel, flag)
        }
        
        fun pop(): Unit {
            val sel = ObjCRuntime.sel("pop")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun columnResizeCursorInDirections(directions: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("columnResizeCursorInDirections:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, directions) as MemorySegment
        }
        
        fun rowResizeCursorInDirections(directions: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("rowResizeCursorInDirections:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, directions) as MemorySegment
        }
        
        fun frameResizeCursorFromPosition_inDirections(position: MemorySegment, directions: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("frameResizeCursorFromPosition:inDirections:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, position, directions) as MemorySegment
        }
        
        fun currentCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("currentCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun arrowCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("arrowCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun crosshairCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("crosshairCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun disappearingItemCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("disappearingItemCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun operationNotAllowedCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("operationNotAllowedCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun dragLinkCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("dragLinkCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun dragCopyCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("dragCopyCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun contextualMenuCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("contextualMenuCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun pointingHandCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("pointingHandCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun closedHandCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("closedHandCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun openHandCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("openHandCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun IBeamCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("IBeamCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun IBeamCursorForVerticalLayout(): MemorySegment {
            val sel = ObjCRuntime.sel("IBeamCursorForVerticalLayout")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun zoomInCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("zoomInCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun zoomOutCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("zoomOutCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun columnResizeCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("columnResizeCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun rowResizeCursor(): MemorySegment {
            val sel = ObjCRuntime.sel("rowResizeCursor")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithImage_hotSpot(newImage: MemorySegment, point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithImage:hotSpot:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, newImage, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun pop(): Unit {
        val sel = ObjCRuntime.sel("pop")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun push(): Unit {
        val sel = ObjCRuntime.sel("push")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun `set`(): Unit {
        val sel = ObjCRuntime.sel("set")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property image
    open fun image(): MemorySegment {
        val sel = ObjCRuntime.sel("image")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property hotSpot
    open fun hotSpot(): MemorySegment {
        val sel = ObjCRuntime.sel("hotSpot")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel) as MemorySegment
    }
    
    // @property currentCursor
    open fun currentCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("currentCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property arrowCursor
    open fun arrowCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("arrowCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property crosshairCursor
    open fun crosshairCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("crosshairCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property disappearingItemCursor
    open fun disappearingItemCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("disappearingItemCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property operationNotAllowedCursor
    open fun operationNotAllowedCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("operationNotAllowedCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property dragLinkCursor
    open fun dragLinkCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("dragLinkCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property dragCopyCursor
    open fun dragCopyCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("dragCopyCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property contextualMenuCursor
    open fun contextualMenuCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("contextualMenuCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property pointingHandCursor
    open fun pointingHandCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("pointingHandCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property closedHandCursor
    open fun closedHandCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("closedHandCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property openHandCursor
    open fun openHandCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("openHandCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property IBeamCursor
    open fun IBeamCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("IBeamCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property IBeamCursorForVerticalLayout
    open fun IBeamCursorForVerticalLayout(): MemorySegment {
        val sel = ObjCRuntime.sel("IBeamCursorForVerticalLayout")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property zoomInCursor
    open fun zoomInCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("zoomInCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property zoomOutCursor
    open fun zoomOutCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("zoomOutCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property columnResizeCursor
    open fun columnResizeCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("columnResizeCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property rowResizeCursor
    open fun rowResizeCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("rowResizeCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: Deprecated on NSCursor ─────────────────────────────────────────

// Class method: +[NSCursor currentSystemCursor]
fun NSCursor_currentSystemCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("currentSystemCursor")
    val cls = ObjCRuntime.getClass("NSCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCursor resizeLeftCursor]
fun NSCursor_resizeLeftCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeLeftCursor")
    val cls = ObjCRuntime.getClass("NSCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCursor resizeRightCursor]
fun NSCursor_resizeRightCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeRightCursor")
    val cls = ObjCRuntime.getClass("NSCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCursor resizeLeftRightCursor]
fun NSCursor_resizeLeftRightCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeLeftRightCursor")
    val cls = ObjCRuntime.getClass("NSCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCursor resizeUpCursor]
fun NSCursor_resizeUpCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeUpCursor")
    val cls = ObjCRuntime.getClass("NSCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCursor resizeDownCursor]
fun NSCursor_resizeDownCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeDownCursor")
    val cls = ObjCRuntime.getClass("NSCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCursor resizeUpDownCursor]
fun NSCursor_resizeUpDownCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeUpDownCursor")
    val cls = ObjCRuntime.getClass("NSCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// @property currentSystemCursor
fun NSCursor.currentSystemCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("currentSystemCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property resizeLeftCursor
fun NSCursor.resizeLeftCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeLeftCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property resizeRightCursor
fun NSCursor.resizeRightCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeRightCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property resizeLeftRightCursor
fun NSCursor.resizeLeftRightCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeLeftRightCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property resizeUpCursor
fun NSCursor.resizeUpCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeUpCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property resizeDownCursor
fun NSCursor.resizeDownCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeDownCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property resizeUpDownCursor
fun NSCursor.resizeUpDownCursor(): MemorySegment {
    val sel = ObjCRuntime.sel("resizeUpDownCursor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSDeprecated on NSCursor ─────────────────────────────────────────

fun NSCursor.initWithImage_foregroundColorHint_backgroundColorHint_hotSpot(newImage: MemorySegment, fg: MemorySegment, bg: MemorySegment, hotSpot: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithImage:foregroundColorHint:backgroundColorHint:hotSpot:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, newImage, fg, bg, hotSpot) as MemorySegment
}

fun NSCursor.setOnMouseExited(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setOnMouseExited:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSCursor.setOnMouseEntered(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setOnMouseEntered:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSCursor.mouseEntered(event: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("mouseEntered:")
    ObjCRuntime.msgSend(null, this.ptr, sel, event)
}

fun NSCursor.mouseExited(event: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("mouseExited:")
    ObjCRuntime.msgSend(null, this.ptr, sel, event)
}

fun NSCursor.isSetOnMouseExited(): Boolean {
    val sel = ObjCRuntime.sel("isSetOnMouseExited")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSCursor.isSetOnMouseEntered(): Boolean {
    val sel = ObjCRuntime.sel("isSetOnMouseEntered")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

