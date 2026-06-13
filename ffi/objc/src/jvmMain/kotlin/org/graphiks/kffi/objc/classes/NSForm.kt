package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSForm
 * Superclass: NSMatrix
 */
open class NSForm(override val ptr: MemorySegment) : NSMatrix(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSForm") }
        
    }
    
    open fun indexOfSelectedItem(): Long {
        val sel = ObjCRuntime.sel("indexOfSelectedItem")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun setEntryWidth(width: Double): Unit {
        val sel = ObjCRuntime.sel("setEntryWidth:")
        ObjCRuntime.msgSend(null, ptr, sel, width)
    }
    
    open fun setInterlineSpacing(spacing: Double): Unit {
        val sel = ObjCRuntime.sel("setInterlineSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, spacing)
    }
    
    open fun setBordered(flag: Boolean): Unit {
        val sel = ObjCRuntime.sel("setBordered:")
        ObjCRuntime.msgSend(null, ptr, sel, flag)
    }
    
    open fun setBezeled(flag: Boolean): Unit {
        val sel = ObjCRuntime.sel("setBezeled:")
        ObjCRuntime.msgSend(null, ptr, sel, flag)
    }
    
    open fun setTitleAlignment(mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTitleAlignment:")
        ObjCRuntime.msgSend(null, ptr, sel, mode)
    }
    
    open fun setTextAlignment(mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTextAlignment:")
        ObjCRuntime.msgSend(null, ptr, sel, mode)
    }
    
    open fun setTitleFont(fontObj: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTitleFont:")
        ObjCRuntime.msgSend(null, ptr, sel, fontObj)
    }
    
    open fun setTextFont(fontObj: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTextFont:")
        ObjCRuntime.msgSend(null, ptr, sel, fontObj)
    }
    
    open fun cellAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("cellAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun drawCellAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("drawCellAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun addEntry(title: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("addEntry:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, title) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun addEntry(title: String): MemorySegment = addEntry(ObjCRuntime.newNSString(Arena.global(), title))
    
    open fun insertEntry_atIndex(title: MemorySegment, index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("insertEntry:atIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, title, index) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun insertEntry_atIndex(title: String, index: Long): MemorySegment = insertEntry_atIndex(ObjCRuntime.newNSString(Arena.global(), title), index)
    
    open fun removeEntryAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeEntryAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun indexOfCellWithTag(tag: Long): Long {
        val sel = ObjCRuntime.sel("indexOfCellWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, tag) as Long
    }
    
    open fun selectTextAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("selectTextAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    override fun setFrameSize(newSize: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setFrameSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(newSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    open fun setTitleBaseWritingDirection(writingDirection: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTitleBaseWritingDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, writingDirection)
    }
    
    open fun setTextBaseWritingDirection(writingDirection: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTextBaseWritingDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, writingDirection)
    }
    
    open fun setPreferredTextFieldWidth(preferredWidth: Double): Unit {
        val sel = ObjCRuntime.sel("setPreferredTextFieldWidth:")
        ObjCRuntime.msgSend(null, ptr, sel, preferredWidth)
    }
    
    open fun preferredTextFieldWidth(): Double {
        val sel = ObjCRuntime.sel("preferredTextFieldWidth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
}

