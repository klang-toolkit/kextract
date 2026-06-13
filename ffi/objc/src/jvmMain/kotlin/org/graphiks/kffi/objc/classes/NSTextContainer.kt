package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextContainer
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSTextContainer(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextContainer") }
        
    }
    
    open fun initWithSize(size: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSize:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"))) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun lineFragmentRectForProposedRect_atIndex_writingDirection_remainingRect(proposedRect: MemorySegment, characterIndex: Long, baseWritingDirection: MemorySegment, remainingRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("lineFragmentRectForProposedRect:atIndex:writingDirection:remainingRect:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(proposedRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), characterIndex, baseWritingDirection, remainingRect) as MemorySegment
    }
    
    // @property textLayoutManager
    open fun textLayoutManager(): MemorySegment {
        val sel = ObjCRuntime.sel("textLayoutManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property size
    open fun size(): MemorySegment {
        val sel = ObjCRuntime.sel("size")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property lineBreakMode
    open fun lineBreakMode(): MemorySegment {
        val sel = ObjCRuntime.sel("lineBreakMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLineBreakMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLineBreakMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property lineFragmentPadding
    open fun lineFragmentPadding(): Double {
        val sel = ObjCRuntime.sel("lineFragmentPadding")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setLineFragmentPadding(value: Double) {
        val sel = ObjCRuntime.sel("setLineFragmentPadding:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maximumNumberOfLines
    open fun maximumNumberOfLines(): Long {
        val sel = ObjCRuntime.sel("maximumNumberOfLines")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setMaximumNumberOfLines(value: Long) {
        val sel = ObjCRuntime.sel("setMaximumNumberOfLines:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property simpleRectangularTextContainer
    open fun isSimpleRectangularTextContainer(): Boolean {
        val sel = ObjCRuntime.sel("isSimpleRectangularTextContainer")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property widthTracksTextView
    open fun widthTracksTextView(): Boolean {
        val sel = ObjCRuntime.sel("widthTracksTextView")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setWidthTracksTextView(value: Boolean) {
        val sel = ObjCRuntime.sel("setWidthTracksTextView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property heightTracksTextView
    open fun heightTracksTextView(): Boolean {
        val sel = ObjCRuntime.sel("heightTracksTextView")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHeightTracksTextView(value: Boolean) {
        val sel = ObjCRuntime.sel("setHeightTracksTextView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category:  on NSTextContainer ─────────────────────────────────────────

fun NSTextContainer.layoutManager(): MemorySegment {
    val sel = ObjCRuntime.sel("layoutManager")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSTextContainer.setLayoutManager(layoutManager: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setLayoutManager:")
    ObjCRuntime.msgSend(null, this.ptr, sel, layoutManager)
}

fun NSTextContainer.replaceLayoutManager(newLayoutManager: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("replaceLayoutManager:")
    ObjCRuntime.msgSend(null, this.ptr, sel, newLayoutManager)
}

/** @return NSArray<NSBezierPath *> * */
fun NSTextContainer.exclusionPaths(): MemorySegment {
    val sel = ObjCRuntime.sel("exclusionPaths")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSTextContainer.setExclusionPaths(exclusionPaths: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setExclusionPaths:")
    ObjCRuntime.msgSend(null, this.ptr, sel, exclusionPaths)
}

fun NSTextContainer.textView(): MemorySegment {
    val sel = ObjCRuntime.sel("textView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSTextContainer.setTextView(textView: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTextView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, textView)
}

// ── Category: NSTextContainerDeprecated on NSTextContainer ─────────────────────────────────────────

fun NSTextContainer.initWithContainerSize(aContainerSize: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContainerSize:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, aContainerSize) as MemorySegment
}

fun NSTextContainer.lineFragmentRectForProposedRect_sweepDirection_movementDirection_remainingRect(proposedRect: MemorySegment, sweepDirection: MemorySegment, movementDirection: MemorySegment, remainingRect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("lineFragmentRectForProposedRect:sweepDirection:movementDirection:remainingRect:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, proposedRect, sweepDirection, movementDirection, remainingRect) as MemorySegment
}

fun NSTextContainer.containsPoint(point: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("containsPoint:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, point) as Boolean
}

fun NSTextContainer.containerSize(): MemorySegment {
    val sel = ObjCRuntime.sel("containerSize")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel) as MemorySegment
}

fun NSTextContainer.setContainerSize(containerSize: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setContainerSize:")
    ObjCRuntime.msgSend(null, this.ptr, sel, containerSize)
}

