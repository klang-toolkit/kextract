package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSGridView
 * Superclass: NSView
 */
open class NSGridView(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSGridView") }
        
        fun gridViewWithNumberOfColumns_rows(columnCount: Long, rowCount: Long): MemorySegment {
            val sel = ObjCRuntime.sel("gridViewWithNumberOfColumns:rows:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, columnCount, rowCount) as MemorySegment
        }
        
        fun gridViewWithViews(rows: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("gridViewWithViews:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, rows) as MemorySegment
        }
        
    }
    
    override fun initWithFrame(frameRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun rowAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("rowAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun indexOfRow(row: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfRow:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, row) as Long
    }
    
    open fun columnAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("columnAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun indexOfColumn(column: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfColumn:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, column) as Long
    }
    
    open fun cellAtColumnIndex_rowIndex(columnIndex: Long, rowIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("cellAtColumnIndex:rowIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, columnIndex, rowIndex) as MemorySegment
    }
    
    open fun cellForView(view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("cellForView:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, view) as MemorySegment
    }
    
    open fun addRowWithViews(views: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("addRowWithViews:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, views) as MemorySegment
    }
    
    open fun insertRowAtIndex_withViews(index: Long, views: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("insertRowAtIndex:withViews:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index, views) as MemorySegment
    }
    
    open fun moveRowAtIndex_toIndex(fromIndex: Long, toIndex: Long): Unit {
        val sel = ObjCRuntime.sel("moveRowAtIndex:toIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, fromIndex, toIndex)
    }
    
    open fun removeRowAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeRowAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun addColumnWithViews(views: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("addColumnWithViews:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, views) as MemorySegment
    }
    
    open fun insertColumnAtIndex_withViews(index: Long, views: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("insertColumnAtIndex:withViews:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index, views) as MemorySegment
    }
    
    open fun moveColumnAtIndex_toIndex(fromIndex: Long, toIndex: Long): Unit {
        val sel = ObjCRuntime.sel("moveColumnAtIndex:toIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, fromIndex, toIndex)
    }
    
    open fun removeColumnAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeColumnAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun mergeCellsInHorizontalRange_verticalRange(hRange: MemorySegment, vRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("mergeCellsInHorizontalRange:verticalRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(hRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(vRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    // @property numberOfRows
    open fun numberOfRows(): Long {
        val sel = ObjCRuntime.sel("numberOfRows")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property numberOfColumns
    open fun numberOfColumns(): Long {
        val sel = ObjCRuntime.sel("numberOfColumns")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property xPlacement
    open fun xPlacement(): MemorySegment {
        val sel = ObjCRuntime.sel("xPlacement")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setXPlacement(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setXPlacement:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property yPlacement
    open fun yPlacement(): MemorySegment {
        val sel = ObjCRuntime.sel("yPlacement")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setYPlacement(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setYPlacement:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rowAlignment
    open fun rowAlignment(): MemorySegment {
        val sel = ObjCRuntime.sel("rowAlignment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRowAlignment(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRowAlignment:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rowSpacing
    open fun rowSpacing(): Double {
        val sel = ObjCRuntime.sel("rowSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setRowSpacing(value: Double) {
        val sel = ObjCRuntime.sel("setRowSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property columnSpacing
    open fun columnSpacing(): Double {
        val sel = ObjCRuntime.sel("columnSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setColumnSpacing(value: Double) {
        val sel = ObjCRuntime.sel("setColumnSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

