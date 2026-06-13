package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextTableBlock
 * Superclass: NSTextBlock
 */
open class NSTextTableBlock(override val ptr: MemorySegment) : NSTextBlock(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextTableBlock") }
        
    }
    
    open fun initWithTable_startingRow_rowSpan_startingColumn_columnSpan(table: MemorySegment, row: Long, rowSpan: Long, col: Long, colSpan: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTable:startingRow:rowSpan:startingColumn:columnSpan:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, table, row, rowSpan, col, colSpan) as MemorySegment
    }
    
    // @property table
    open fun table(): MemorySegment {
        val sel = ObjCRuntime.sel("table")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property startingRow
    open fun startingRow(): Long {
        val sel = ObjCRuntime.sel("startingRow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property rowSpan
    open fun rowSpan(): Long {
        val sel = ObjCRuntime.sel("rowSpan")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property startingColumn
    open fun startingColumn(): Long {
        val sel = ObjCRuntime.sel("startingColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property columnSpan
    open fun columnSpan(): Long {
        val sel = ObjCRuntime.sel("columnSpan")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
}

