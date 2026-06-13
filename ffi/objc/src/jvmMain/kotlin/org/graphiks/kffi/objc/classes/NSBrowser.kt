package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSBrowser
 * Superclass: NSControl
 */
open class NSBrowser(override val ptr: MemorySegment) : NSControl(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSBrowser") }
        
        fun removeSavedColumnsWithAutosaveName(name: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("removeSavedColumnsWithAutosaveName:")
            ObjCRuntime.msgSend(null, _class, sel, name)
        }
        
        fun cellClass(): MemorySegment {
            val sel = ObjCRuntime.sel("cellClass")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun loadColumnZero(): Unit {
        val sel = ObjCRuntime.sel("loadColumnZero")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setCellClass(factoryId: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setCellClass:")
        ObjCRuntime.msgSend(null, ptr, sel, factoryId)
    }
    
    open fun itemAtIndexPath(indexPath: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("itemAtIndexPath:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, indexPath) as MemorySegment
    }
    
    open fun itemAtRow_inColumn(row: Long, column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("itemAtRow:inColumn:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row, column) as MemorySegment
    }
    
    open fun indexPathForColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("indexPathForColumn:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, column) as MemorySegment
    }
    
    open fun isLeafItem(item: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isLeafItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, item) as Boolean
    }
    
    open fun reloadDataForRowIndexes_inColumn(rowIndexes: MemorySegment, column: Long): Unit {
        val sel = ObjCRuntime.sel("reloadDataForRowIndexes:inColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, rowIndexes, column)
    }
    
    open fun parentForItemsInColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("parentForItemsInColumn:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, column) as MemorySegment
    }
    
    open fun scrollRowToVisible_inColumn(row: Long, column: Long): Unit {
        val sel = ObjCRuntime.sel("scrollRowToVisible:inColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, row, column)
    }
    
    open fun setTitle_ofColumn(string: MemorySegment, column: Long): Unit {
        val sel = ObjCRuntime.sel("setTitle:ofColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, string, column)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setTitle_ofColumn(string: String, column: Long): Unit = setTitle_ofColumn(ObjCRuntime.newNSString(Arena.global(), string), column)
    
    open fun titleOfColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("titleOfColumn:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, column) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun titleOfColumnAsString(column: Long): String = ObjCRuntime.toJavaString(titleOfColumn(column))
    
    open fun setPath(path: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setPath:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, path) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setPath(path: String): Boolean = setPath(ObjCRuntime.newNSString(Arena.global(), path))
    
    open fun path(): MemorySegment {
        val sel = ObjCRuntime.sel("path")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun pathAsString(): String = ObjCRuntime.toJavaString(path())
    
    open fun pathToColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("pathToColumn:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, column) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun pathToColumnAsString(column: Long): String = ObjCRuntime.toJavaString(pathToColumn(column))
    
    open fun selectedCellInColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("selectedCellInColumn:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, column) as MemorySegment
    }
    
    open fun selectRow_inColumn(row: Long, column: Long): Unit {
        val sel = ObjCRuntime.sel("selectRow:inColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, row, column)
    }
    
    open fun selectedRowInColumn(column: Long): Long {
        val sel = ObjCRuntime.sel("selectedRowInColumn:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, column) as Long
    }
    
    open fun selectRowIndexes_inColumn(indexes: MemorySegment, column: Long): Unit {
        val sel = ObjCRuntime.sel("selectRowIndexes:inColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, column)
    }
    
    open fun selectedRowIndexesInColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("selectedRowIndexesInColumn:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, column) as MemorySegment
    }
    
    open fun reloadColumn(column: Long): Unit {
        val sel = ObjCRuntime.sel("reloadColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, column)
    }
    
    open fun validateVisibleColumns(): Unit {
        val sel = ObjCRuntime.sel("validateVisibleColumns")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun scrollColumnsRightBy(shiftAmount: Long): Unit {
        val sel = ObjCRuntime.sel("scrollColumnsRightBy:")
        ObjCRuntime.msgSend(null, ptr, sel, shiftAmount)
    }
    
    open fun scrollColumnsLeftBy(shiftAmount: Long): Unit {
        val sel = ObjCRuntime.sel("scrollColumnsLeftBy:")
        ObjCRuntime.msgSend(null, ptr, sel, shiftAmount)
    }
    
    open fun scrollColumnToVisible(column: Long): Unit {
        val sel = ObjCRuntime.sel("scrollColumnToVisible:")
        ObjCRuntime.msgSend(null, ptr, sel, column)
    }
    
    open fun addColumn(): Unit {
        val sel = ObjCRuntime.sel("addColumn")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun loadedCellAtRow_column(row: Long, col: Long): MemorySegment {
        val sel = ObjCRuntime.sel("loadedCellAtRow:column:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row, col) as MemorySegment
    }
    
    open fun selectAll(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectAll:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun tile(): Unit {
        val sel = ObjCRuntime.sel("tile")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun doClick(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("doClick:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun doDoubleClick(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("doDoubleClick:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun sendAction(): Boolean {
        val sel = ObjCRuntime.sel("sendAction")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun titleFrameOfColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("titleFrameOfColumn:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, column) as MemorySegment
    }
    
    open fun drawTitleOfColumn_inRect(column: Long, rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawTitleOfColumn:inRect:")
        ObjCRuntime.msgSend(null, ptr, sel, column, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun frameOfColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("frameOfColumn:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, column) as MemorySegment
    }
    
    open fun frameOfInsideOfColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("frameOfInsideOfColumn:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, column) as MemorySegment
    }
    
    open fun frameOfRow_inColumn(row: Long, column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("frameOfRow:inColumn:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, row, column) as MemorySegment
    }
    
    open fun getRow_column_forPoint(row: MemorySegment, column: MemorySegment, point: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("getRow:column:forPoint:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, row, column, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as Boolean
    }
    
    open fun columnWidthForColumnContentWidth(columnContentWidth: Double): Double {
        val sel = ObjCRuntime.sel("columnWidthForColumnContentWidth:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, columnContentWidth) as Double
    }
    
    open fun columnContentWidthForColumnWidth(columnWidth: Double): Double {
        val sel = ObjCRuntime.sel("columnContentWidthForColumnWidth:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, columnWidth) as Double
    }
    
    open fun setWidth_ofColumn(columnWidth: Double, columnIndex: Long): Unit {
        val sel = ObjCRuntime.sel("setWidth:ofColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, columnWidth, columnIndex)
    }
    
    open fun widthOfColumn(column: Long): Double {
        val sel = ObjCRuntime.sel("widthOfColumn:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, column) as Double
    }
    
    open fun noteHeightOfRowsWithIndexesChanged_inColumn(indexSet: MemorySegment, columnIndex: Long): Unit {
        val sel = ObjCRuntime.sel("noteHeightOfRowsWithIndexesChanged:inColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, indexSet, columnIndex)
    }
    
    open fun setDefaultColumnWidth(columnWidth: Double): Unit {
        val sel = ObjCRuntime.sel("setDefaultColumnWidth:")
        ObjCRuntime.msgSend(null, ptr, sel, columnWidth)
    }
    
    open fun defaultColumnWidth(): Double {
        val sel = ObjCRuntime.sel("defaultColumnWidth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    open fun canDragRowsWithIndexes_inColumn_withEvent(rowIndexes: MemorySegment, column: Long, event: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("canDragRowsWithIndexes:inColumn:withEvent:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, rowIndexes, column, event) as Boolean
    }
    
    open fun draggingImageForRowsWithIndexes_inColumn_withEvent_offset(rowIndexes: MemorySegment, column: Long, event: MemorySegment, dragImageOffset: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("draggingImageForRowsWithIndexes:inColumn:withEvent:offset:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, rowIndexes, column, event, dragImageOffset) as MemorySegment
    }
    
    open fun setDraggingSourceOperationMask_forLocal(mask: MemorySegment, isLocal: Boolean): Unit {
        val sel = ObjCRuntime.sel("setDraggingSourceOperationMask:forLocal:")
        ObjCRuntime.msgSend(null, ptr, sel, mask, isLocal)
    }
    
    open fun editItemAtIndexPath_withEvent_select(indexPath: MemorySegment, event: MemorySegment, select: Boolean): Unit {
        val sel = ObjCRuntime.sel("editItemAtIndexPath:withEvent:select:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPath, event, select)
    }
    
    // @property cellClass
    open fun cellClass(): MemorySegment {
        val sel = ObjCRuntime.sel("cellClass")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property loaded
    open fun isLoaded(): Boolean {
        val sel = ObjCRuntime.sel("isLoaded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property doubleAction
    open fun doubleAction(): MemorySegment {
        val sel = ObjCRuntime.sel("doubleAction")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDoubleAction(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDoubleAction:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property cellPrototype
    open fun cellPrototype(): MemorySegment {
        val sel = ObjCRuntime.sel("cellPrototype")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCellPrototype(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCellPrototype:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSBrowserDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property reusesColumns
    open fun reusesColumns(): Boolean {
        val sel = ObjCRuntime.sel("reusesColumns")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setReusesColumns(value: Boolean) {
        val sel = ObjCRuntime.sel("setReusesColumns:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasHorizontalScroller
    open fun hasHorizontalScroller(): Boolean {
        val sel = ObjCRuntime.sel("hasHorizontalScroller")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHasHorizontalScroller(value: Boolean) {
        val sel = ObjCRuntime.sel("setHasHorizontalScroller:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autohidesScroller
    open fun autohidesScroller(): Boolean {
        val sel = ObjCRuntime.sel("autohidesScroller")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutohidesScroller(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutohidesScroller:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property separatesColumns
    open fun separatesColumns(): Boolean {
        val sel = ObjCRuntime.sel("separatesColumns")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSeparatesColumns(value: Boolean) {
        val sel = ObjCRuntime.sel("setSeparatesColumns:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property titled
    open fun isTitled(): Boolean {
        val sel = ObjCRuntime.sel("isTitled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setTitled(value: Boolean) {
        val sel = ObjCRuntime.sel("setTitled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minColumnWidth
    open fun minColumnWidth(): Double {
        val sel = ObjCRuntime.sel("minColumnWidth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinColumnWidth(value: Double) {
        val sel = ObjCRuntime.sel("setMinColumnWidth:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maxVisibleColumns
    open fun maxVisibleColumns(): Long {
        val sel = ObjCRuntime.sel("maxVisibleColumns")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setMaxVisibleColumns(value: Long) {
        val sel = ObjCRuntime.sel("setMaxVisibleColumns:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsMultipleSelection
    open fun allowsMultipleSelection(): Boolean {
        val sel = ObjCRuntime.sel("allowsMultipleSelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsMultipleSelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsMultipleSelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsBranchSelection
    open fun allowsBranchSelection(): Boolean {
        val sel = ObjCRuntime.sel("allowsBranchSelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsBranchSelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsBranchSelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsEmptySelection
    open fun allowsEmptySelection(): Boolean {
        val sel = ObjCRuntime.sel("allowsEmptySelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsEmptySelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsEmptySelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property takesTitleFromPreviousColumn
    open fun takesTitleFromPreviousColumn(): Boolean {
        val sel = ObjCRuntime.sel("takesTitleFromPreviousColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setTakesTitleFromPreviousColumn(value: Boolean) {
        val sel = ObjCRuntime.sel("setTakesTitleFromPreviousColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sendsActionOnArrowKeys
    open fun sendsActionOnArrowKeys(): Boolean {
        val sel = ObjCRuntime.sel("sendsActionOnArrowKeys")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSendsActionOnArrowKeys(value: Boolean) {
        val sel = ObjCRuntime.sel("setSendsActionOnArrowKeys:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pathSeparator
    open fun pathSeparator(): MemorySegment {
        val sel = ObjCRuntime.sel("pathSeparator")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPathSeparator(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPathSeparator:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun pathSeparatorAsString(): String = ObjCRuntime.toJavaString(pathSeparator())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPathSeparator(value: String) = setPathSeparator(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property clickedColumn
    open fun clickedColumn(): Long {
        val sel = ObjCRuntime.sel("clickedColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property clickedRow
    open fun clickedRow(): Long {
        val sel = ObjCRuntime.sel("clickedRow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property selectedColumn
    open fun selectedColumn(): Long {
        val sel = ObjCRuntime.sel("selectedColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property selectedCell
    open fun selectedCell(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedCell")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectedCells
    /** @return NSArray<NSCell *> * */
    open fun selectedCells(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedCells")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectionIndexPath
    open fun selectionIndexPath(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionIndexPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionIndexPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionIndexPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectionIndexPaths
    /** @return NSArray<NSIndexPath *> * */
    open fun selectionIndexPaths(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionIndexPaths")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionIndexPaths(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionIndexPaths:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property lastColumn
    open fun lastColumn(): Long {
        val sel = ObjCRuntime.sel("lastColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setLastColumn(value: Long) {
        val sel = ObjCRuntime.sel("setLastColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberOfVisibleColumns
    open fun numberOfVisibleColumns(): Long {
        val sel = ObjCRuntime.sel("numberOfVisibleColumns")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property firstVisibleColumn
    open fun firstVisibleColumn(): Long {
        val sel = ObjCRuntime.sel("firstVisibleColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property lastVisibleColumn
    open fun lastVisibleColumn(): Long {
        val sel = ObjCRuntime.sel("lastVisibleColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property titleHeight
    open fun titleHeight(): Double {
        val sel = ObjCRuntime.sel("titleHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property columnResizingType
    open fun columnResizingType(): MemorySegment {
        val sel = ObjCRuntime.sel("columnResizingType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setColumnResizingType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setColumnResizingType:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property prefersAllColumnUserResizing
    open fun prefersAllColumnUserResizing(): Boolean {
        val sel = ObjCRuntime.sel("prefersAllColumnUserResizing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPrefersAllColumnUserResizing(value: Boolean) {
        val sel = ObjCRuntime.sel("setPrefersAllColumnUserResizing:")
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
    
    // @property columnsAutosaveName
    open fun columnsAutosaveName(): MemorySegment {
        val sel = ObjCRuntime.sel("columnsAutosaveName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setColumnsAutosaveName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setColumnsAutosaveName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsTypeSelect
    open fun allowsTypeSelect(): Boolean {
        val sel = ObjCRuntime.sel("allowsTypeSelect")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsTypeSelect(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsTypeSelect:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backgroundColor
    open fun backgroundColor(): MemorySegment {
        val sel = ObjCRuntime.sel("backgroundColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackgroundColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackgroundColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSDeprecated on NSBrowser ─────────────────────────────────────────

fun NSBrowser.setAcceptsArrowKeys(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setAcceptsArrowKeys:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSBrowser.acceptsArrowKeys(): Boolean {
    val sel = ObjCRuntime.sel("acceptsArrowKeys")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSBrowser.displayColumn(column: Long): Unit {
    val sel = ObjCRuntime.sel("displayColumn:")
    ObjCRuntime.msgSend(null, this.ptr, sel, column)
}

fun NSBrowser.displayAllColumns(): Unit {
    val sel = ObjCRuntime.sel("displayAllColumns")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSBrowser.scrollViaScroller(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("scrollViaScroller:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSBrowser.updateScroller(): Unit {
    val sel = ObjCRuntime.sel("updateScroller")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSBrowser.setMatrixClass(factoryId: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setMatrixClass:")
    ObjCRuntime.msgSend(null, this.ptr, sel, factoryId)
}

fun NSBrowser.matrixClass(): MemorySegment {
    val sel = ObjCRuntime.sel("matrixClass")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSBrowser.columnOfMatrix(matrix: MemorySegment): Long {
    val sel = ObjCRuntime.sel("columnOfMatrix:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, matrix) as Long
}

fun NSBrowser.matrixInColumn(column: Long): MemorySegment {
    val sel = ObjCRuntime.sel("matrixInColumn:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, column) as MemorySegment
}

