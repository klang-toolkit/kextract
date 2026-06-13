package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSRuleEditor
 * Superclass: NSControl
 */
open class NSRuleEditor(override val ptr: MemorySegment) : NSControl(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSRuleEditor") }
        
    }
    
    open fun reloadCriteria(): Unit {
        val sel = ObjCRuntime.sel("reloadCriteria")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun reloadPredicate(): Unit {
        val sel = ObjCRuntime.sel("reloadPredicate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun predicateForRow(row: Long): MemorySegment {
        val sel = ObjCRuntime.sel("predicateForRow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row) as MemorySegment
    }
    
    open fun subrowIndexesForRow(rowIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("subrowIndexesForRow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, rowIndex) as MemorySegment
    }
    
    open fun criteriaForRow(row: Long): MemorySegment {
        val sel = ObjCRuntime.sel("criteriaForRow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row) as MemorySegment
    }
    
    open fun displayValuesForRow(row: Long): MemorySegment {
        val sel = ObjCRuntime.sel("displayValuesForRow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row) as MemorySegment
    }
    
    open fun rowForDisplayValue(displayValue: MemorySegment): Long {
        val sel = ObjCRuntime.sel("rowForDisplayValue:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, displayValue) as Long
    }
    
    open fun rowTypeForRow(rowIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("rowTypeForRow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, rowIndex) as MemorySegment
    }
    
    open fun parentRowForRow(rowIndex: Long): Long {
        val sel = ObjCRuntime.sel("parentRowForRow:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, rowIndex) as Long
    }
    
    open fun addRow(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addRow:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun insertRowAtIndex_withType_asSubrowOfRow_animate(rowIndex: Long, rowType: MemorySegment, parentRow: Long, shouldAnimate: Boolean): Unit {
        val sel = ObjCRuntime.sel("insertRowAtIndex:withType:asSubrowOfRow:animate:")
        ObjCRuntime.msgSend(null, ptr, sel, rowIndex, rowType, parentRow, shouldAnimate)
    }
    
    open fun setCriteria_andDisplayValues_forRowAtIndex(criteria: MemorySegment, values: MemorySegment, rowIndex: Long): Unit {
        val sel = ObjCRuntime.sel("setCriteria:andDisplayValues:forRowAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, criteria, values, rowIndex)
    }
    
    open fun removeRowAtIndex(rowIndex: Long): Unit {
        val sel = ObjCRuntime.sel("removeRowAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, rowIndex)
    }
    
    open fun removeRowsAtIndexes_includeSubrows(rowIndexes: MemorySegment, includeSubrows: Boolean): Unit {
        val sel = ObjCRuntime.sel("removeRowsAtIndexes:includeSubrows:")
        ObjCRuntime.msgSend(null, ptr, sel, rowIndexes, includeSubrows)
    }
    
    open fun selectRowIndexes_byExtendingSelection(indexes: MemorySegment, extend: Boolean): Unit {
        val sel = ObjCRuntime.sel("selectRowIndexes:byExtendingSelection:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, extend)
    }
    
    // @property delegate
    /** @return id<NSRuleEditorDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property formattingStringsFilename
    open fun formattingStringsFilename(): MemorySegment {
        val sel = ObjCRuntime.sel("formattingStringsFilename")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFormattingStringsFilename(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFormattingStringsFilename:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun formattingStringsFilenameAsString(): String = ObjCRuntime.toJavaString(formattingStringsFilename())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setFormattingStringsFilename(value: String) = setFormattingStringsFilename(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property formattingDictionary
    /** @return NSDictionary<NSString *,NSString *> * */
    open fun formattingDictionary(): MemorySegment {
        val sel = ObjCRuntime.sel("formattingDictionary")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFormattingDictionary(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFormattingDictionary:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property nestingMode
    open fun nestingMode(): MemorySegment {
        val sel = ObjCRuntime.sel("nestingMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setNestingMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setNestingMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rowHeight
    open fun rowHeight(): Double {
        val sel = ObjCRuntime.sel("rowHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setRowHeight(value: Double) {
        val sel = ObjCRuntime.sel("setRowHeight:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property editable
    open fun isEditable(): Boolean {
        val sel = ObjCRuntime.sel("isEditable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEditable(value: Boolean) {
        val sel = ObjCRuntime.sel("setEditable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canRemoveAllRows
    open fun canRemoveAllRows(): Boolean {
        val sel = ObjCRuntime.sel("canRemoveAllRows")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanRemoveAllRows(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanRemoveAllRows:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property predicate
    open fun predicate(): MemorySegment {
        val sel = ObjCRuntime.sel("predicate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property numberOfRows
    open fun numberOfRows(): Long {
        val sel = ObjCRuntime.sel("numberOfRows")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property selectedRowIndexes
    open fun selectedRowIndexes(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedRowIndexes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property rowClass
    open fun rowClass(): MemorySegment {
        val sel = ObjCRuntime.sel("rowClass")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRowClass(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRowClass:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rowTypeKeyPath
    open fun rowTypeKeyPath(): MemorySegment {
        val sel = ObjCRuntime.sel("rowTypeKeyPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRowTypeKeyPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRowTypeKeyPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun rowTypeKeyPathAsString(): String = ObjCRuntime.toJavaString(rowTypeKeyPath())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setRowTypeKeyPath(value: String) = setRowTypeKeyPath(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property subrowsKeyPath
    open fun subrowsKeyPath(): MemorySegment {
        val sel = ObjCRuntime.sel("subrowsKeyPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSubrowsKeyPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSubrowsKeyPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun subrowsKeyPathAsString(): String = ObjCRuntime.toJavaString(subrowsKeyPath())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setSubrowsKeyPath(value: String) = setSubrowsKeyPath(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property criteriaKeyPath
    open fun criteriaKeyPath(): MemorySegment {
        val sel = ObjCRuntime.sel("criteriaKeyPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCriteriaKeyPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCriteriaKeyPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun criteriaKeyPathAsString(): String = ObjCRuntime.toJavaString(criteriaKeyPath())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setCriteriaKeyPath(value: String) = setCriteriaKeyPath(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property displayValuesKeyPath
    open fun displayValuesKeyPath(): MemorySegment {
        val sel = ObjCRuntime.sel("displayValuesKeyPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDisplayValuesKeyPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDisplayValuesKeyPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun displayValuesKeyPathAsString(): String = ObjCRuntime.toJavaString(displayValuesKeyPath())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setDisplayValuesKeyPath(value: String) = setDisplayValuesKeyPath(ObjCRuntime.newNSString(Arena.global(), value))
    
}

