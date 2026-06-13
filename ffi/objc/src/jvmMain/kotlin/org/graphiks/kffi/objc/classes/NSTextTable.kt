package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextTable
 * Superclass: NSTextBlock
 */
open class NSTextTable(override val ptr: MemorySegment) : NSTextBlock(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextTable") }
        
    }
    
    open fun rectForBlock_layoutAtPoint_inRect_textContainer_characterRange(block: MemorySegment, startingPoint: MemorySegment, rect: MemorySegment, textContainer: MemorySegment, charRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("rectForBlock:layoutAtPoint:inRect:textContainer:characterRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, block, ObjCRuntime.ObjCStructArg(startingPoint, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), textContainer, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
    }
    
    open fun boundsRectForBlock_contentRect_inRect_textContainer_characterRange(block: MemorySegment, contentRect: MemorySegment, rect: MemorySegment, textContainer: MemorySegment, charRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("boundsRectForBlock:contentRect:inRect:textContainer:characterRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, block, ObjCRuntime.ObjCStructArg(contentRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), textContainer, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
    }
    
    open fun drawBackgroundForBlock_withFrame_inView_characterRange_layoutManager(block: MemorySegment, frameRect: MemorySegment, controlView: MemorySegment, charRange: MemorySegment, layoutManager: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawBackgroundForBlock:withFrame:inView:characterRange:layoutManager:")
        ObjCRuntime.msgSend(null, ptr, sel, block, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), controlView, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), layoutManager)
    }
    
    // @property numberOfColumns
    open fun numberOfColumns(): Long {
        val sel = ObjCRuntime.sel("numberOfColumns")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setNumberOfColumns(value: Long) {
        val sel = ObjCRuntime.sel("setNumberOfColumns:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property layoutAlgorithm
    open fun layoutAlgorithm(): MemorySegment {
        val sel = ObjCRuntime.sel("layoutAlgorithm")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLayoutAlgorithm(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLayoutAlgorithm:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property collapsesBorders
    open fun collapsesBorders(): Boolean {
        val sel = ObjCRuntime.sel("collapsesBorders")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCollapsesBorders(value: Boolean) {
        val sel = ObjCRuntime.sel("setCollapsesBorders:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hidesEmptyCells
    open fun hidesEmptyCells(): Boolean {
        val sel = ObjCRuntime.sel("hidesEmptyCells")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHidesEmptyCells(value: Boolean) {
        val sel = ObjCRuntime.sel("setHidesEmptyCells:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

