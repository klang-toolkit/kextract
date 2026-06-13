package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTableView
 * Superclass: NSControl
 * Protocols: NSUserInterfaceValidations, NSTextViewDelegate, NSDraggingSource, NSAccessibilityTable
 */
open class NSTableView(override val ptr: MemorySegment) : NSControl(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTableView") }
        
    }
    
    override fun initWithFrame(frameRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun noteHeightOfRowsWithIndexesChanged(indexSet: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("noteHeightOfRowsWithIndexesChanged:")
        ObjCRuntime.msgSend(null, ptr, sel, indexSet)
    }
    
    open fun addTableColumn(tableColumn: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTableColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, tableColumn)
    }
    
    open fun removeTableColumn(tableColumn: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeTableColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, tableColumn)
    }
    
    open fun moveColumn_toColumn(oldIndex: Long, newIndex: Long): Unit {
        val sel = ObjCRuntime.sel("moveColumn:toColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, oldIndex, newIndex)
    }
    
    open fun columnWithIdentifier(identifier: MemorySegment): Long {
        val sel = ObjCRuntime.sel("columnWithIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, identifier) as Long
    }
    
    open fun tableColumnWithIdentifier(identifier: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("tableColumnWithIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, identifier) as MemorySegment
    }
    
    open fun tile(): Unit {
        val sel = ObjCRuntime.sel("tile")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    override fun sizeToFit(): Unit {
        val sel = ObjCRuntime.sel("sizeToFit")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun sizeLastColumnToFit(): Unit {
        val sel = ObjCRuntime.sel("sizeLastColumnToFit")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun scrollRowToVisible(row: Long): Unit {
        val sel = ObjCRuntime.sel("scrollRowToVisible:")
        ObjCRuntime.msgSend(null, ptr, sel, row)
    }
    
    open fun scrollColumnToVisible(column: Long): Unit {
        val sel = ObjCRuntime.sel("scrollColumnToVisible:")
        ObjCRuntime.msgSend(null, ptr, sel, column)
    }
    
    open fun reloadData(): Unit {
        val sel = ObjCRuntime.sel("reloadData")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun noteNumberOfRowsChanged(): Unit {
        val sel = ObjCRuntime.sel("noteNumberOfRowsChanged")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun reloadDataForRowIndexes_columnIndexes(rowIndexes: MemorySegment, columnIndexes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reloadDataForRowIndexes:columnIndexes:")
        ObjCRuntime.msgSend(null, ptr, sel, rowIndexes, columnIndexes)
    }
    
    open fun setIndicatorImage_inTableColumn(image: MemorySegment, tableColumn: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setIndicatorImage:inTableColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, image, tableColumn)
    }
    
    open fun indicatorImageInTableColumn(tableColumn: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("indicatorImageInTableColumn:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, tableColumn) as MemorySegment
    }
    
    open fun canDragRowsWithIndexes_atPoint(rowIndexes: MemorySegment, mouseDownPoint: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("canDragRowsWithIndexes:atPoint:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, rowIndexes, ObjCRuntime.ObjCStructArg(mouseDownPoint, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as Boolean
    }
    
    open fun dragImageForRowsWithIndexes_tableColumns_event_offset(dragRows: MemorySegment, tableColumns: MemorySegment, dragEvent: MemorySegment, dragImageOffset: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dragImageForRowsWithIndexes:tableColumns:event:offset:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, dragRows, tableColumns, dragEvent, dragImageOffset) as MemorySegment
    }
    
    open fun setDraggingSourceOperationMask_forLocal(mask: MemorySegment, isLocal: Boolean): Unit {
        val sel = ObjCRuntime.sel("setDraggingSourceOperationMask:forLocal:")
        ObjCRuntime.msgSend(null, ptr, sel, mask, isLocal)
    }
    
    open fun setDropRow_dropOperation(row: Long, dropOperation: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setDropRow:dropOperation:")
        ObjCRuntime.msgSend(null, ptr, sel, row, dropOperation)
    }
    
    open fun selectAll(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectAll:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun deselectAll(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("deselectAll:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectColumnIndexes_byExtendingSelection(indexes: MemorySegment, extend: Boolean): Unit {
        val sel = ObjCRuntime.sel("selectColumnIndexes:byExtendingSelection:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, extend)
    }
    
    open fun selectRowIndexes_byExtendingSelection(indexes: MemorySegment, extend: Boolean): Unit {
        val sel = ObjCRuntime.sel("selectRowIndexes:byExtendingSelection:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, extend)
    }
    
    open fun deselectColumn(column: Long): Unit {
        val sel = ObjCRuntime.sel("deselectColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, column)
    }
    
    open fun deselectRow(row: Long): Unit {
        val sel = ObjCRuntime.sel("deselectRow:")
        ObjCRuntime.msgSend(null, ptr, sel, row)
    }
    
    open fun isColumnSelected(column: Long): Boolean {
        val sel = ObjCRuntime.sel("isColumnSelected:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, column) as Boolean
    }
    
    open fun isRowSelected(row: Long): Boolean {
        val sel = ObjCRuntime.sel("isRowSelected:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, row) as Boolean
    }
    
    open fun rectOfColumn(column: Long): MemorySegment {
        val sel = ObjCRuntime.sel("rectOfColumn:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, column) as MemorySegment
    }
    
    open fun rectOfRow(row: Long): MemorySegment {
        val sel = ObjCRuntime.sel("rectOfRow:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, row) as MemorySegment
    }
    
    open fun columnIndexesInRect(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("columnIndexesInRect:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun rowsInRect(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("rowsInRect:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun columnAtPoint(point: MemorySegment): Long {
        val sel = ObjCRuntime.sel("columnAtPoint:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as Long
    }
    
    open fun rowAtPoint(point: MemorySegment): Long {
        val sel = ObjCRuntime.sel("rowAtPoint:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as Long
    }
    
    open fun frameOfCellAtColumn_row(column: Long, row: Long): MemorySegment {
        val sel = ObjCRuntime.sel("frameOfCellAtColumn:row:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, column, row) as MemorySegment
    }
    
    open fun editColumn_row_withEvent_select(column: Long, row: Long, event: MemorySegment, select: Boolean): Unit {
        val sel = ObjCRuntime.sel("editColumn:row:withEvent:select:")
        ObjCRuntime.msgSend(null, ptr, sel, column, row, event, select)
    }
    
    open fun drawRow_clipRect(row: Long, clipRect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawRow:clipRect:")
        ObjCRuntime.msgSend(null, ptr, sel, row, ObjCRuntime.ObjCStructArg(clipRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun highlightSelectionInClipRect(clipRect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("highlightSelectionInClipRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(clipRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun drawGridInClipRect(clipRect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawGridInClipRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(clipRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun drawBackgroundInClipRect(clipRect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawBackgroundInClipRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(clipRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun viewAtColumn_row_makeIfNecessary(column: Long, row: Long, makeIfNecessary: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("viewAtColumn:row:makeIfNecessary:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, column, row, makeIfNecessary) as MemorySegment
    }
    
    open fun rowViewAtRow_makeIfNecessary(row: Long, makeIfNecessary: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("rowViewAtRow:makeIfNecessary:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row, makeIfNecessary) as MemorySegment
    }
    
    open fun rowForView(view: MemorySegment): Long {
        val sel = ObjCRuntime.sel("rowForView:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, view) as Long
    }
    
    open fun columnForView(view: MemorySegment): Long {
        val sel = ObjCRuntime.sel("columnForView:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, view) as Long
    }
    
    open fun makeViewWithIdentifier_owner(identifier: MemorySegment, owner: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("makeViewWithIdentifier:owner:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, identifier, owner) as MemorySegment
    }
    
    open fun enumerateAvailableRowViewsUsingBlock(handler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateAvailableRowViewsUsingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, handler)
    }
    
    open fun beginUpdates(): Unit {
        val sel = ObjCRuntime.sel("beginUpdates")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun endUpdates(): Unit {
        val sel = ObjCRuntime.sel("endUpdates")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun insertRowsAtIndexes_withAnimation(indexes: MemorySegment, animationOptions: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertRowsAtIndexes:withAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, animationOptions)
    }
    
    open fun removeRowsAtIndexes_withAnimation(indexes: MemorySegment, animationOptions: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeRowsAtIndexes:withAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, animationOptions)
    }
    
    open fun moveRowAtIndex_toIndex(oldIndex: Long, newIndex: Long): Unit {
        val sel = ObjCRuntime.sel("moveRowAtIndex:toIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, oldIndex, newIndex)
    }
    
    open fun hideRowsAtIndexes_withAnimation(indexes: MemorySegment, rowAnimation: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("hideRowsAtIndexes:withAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, rowAnimation)
    }
    
    open fun unhideRowsAtIndexes_withAnimation(indexes: MemorySegment, rowAnimation: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unhideRowsAtIndexes:withAnimation:")
        ObjCRuntime.msgSend(null, ptr, sel, indexes, rowAnimation)
    }
    
    open fun registerNib_forIdentifier(nib: MemorySegment, identifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("registerNib:forIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, nib, identifier)
    }
    
    open fun didAddRowView_forRow(rowView: MemorySegment, row: Long): Unit {
        val sel = ObjCRuntime.sel("didAddRowView:forRow:")
        ObjCRuntime.msgSend(null, ptr, sel, rowView, row)
    }
    
    open fun didRemoveRowView_forRow(rowView: MemorySegment, row: Long): Unit {
        val sel = ObjCRuntime.sel("didRemoveRowView:forRow:")
        ObjCRuntime.msgSend(null, ptr, sel, rowView, row)
    }
    
    // @property dataSource
    /** @return id<NSTableViewDataSource> */
    open fun dataSource(): MemorySegment {
        val sel = ObjCRuntime.sel("dataSource")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDataSource(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDataSource:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSTableViewDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property headerView
    open fun headerView(): MemorySegment {
        val sel = ObjCRuntime.sel("headerView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHeaderView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHeaderView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property cornerView
    open fun cornerView(): MemorySegment {
        val sel = ObjCRuntime.sel("cornerView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCornerView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCornerView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsColumnReordering
    open fun allowsColumnReordering(): Boolean {
        val sel = ObjCRuntime.sel("allowsColumnReordering")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsColumnReordering(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsColumnReordering:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsColumnResizing
    open fun allowsColumnResizing(): Boolean {
        val sel = ObjCRuntime.sel("allowsColumnResizing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsColumnResizing(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsColumnResizing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property columnAutoresizingStyle
    open fun columnAutoresizingStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("columnAutoresizingStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setColumnAutoresizingStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setColumnAutoresizingStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property gridStyleMask
    open fun gridStyleMask(): MemorySegment {
        val sel = ObjCRuntime.sel("gridStyleMask")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setGridStyleMask(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setGridStyleMask:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property intercellSpacing
    open fun intercellSpacing(): MemorySegment {
        val sel = ObjCRuntime.sel("intercellSpacing")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setIntercellSpacing(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setIntercellSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property usesAlternatingRowBackgroundColors
    open fun usesAlternatingRowBackgroundColors(): Boolean {
        val sel = ObjCRuntime.sel("usesAlternatingRowBackgroundColors")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesAlternatingRowBackgroundColors(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesAlternatingRowBackgroundColors:")
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
    
    // @property gridColor
    open fun gridColor(): MemorySegment {
        val sel = ObjCRuntime.sel("gridColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setGridColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setGridColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rowSizeStyle
    open fun rowSizeStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("rowSizeStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRowSizeStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRowSizeStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property effectiveRowSizeStyle
    open fun effectiveRowSizeStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("effectiveRowSizeStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
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
    
    // @property tableColumns
    /** @return NSArray<NSTableColumn *> * */
    open fun tableColumns(): MemorySegment {
        val sel = ObjCRuntime.sel("tableColumns")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property numberOfColumns
    open fun numberOfColumns(): Long {
        val sel = ObjCRuntime.sel("numberOfColumns")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property numberOfRows
    open fun numberOfRows(): Long {
        val sel = ObjCRuntime.sel("numberOfRows")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property editedColumn
    open fun editedColumn(): Long {
        val sel = ObjCRuntime.sel("editedColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property editedRow
    open fun editedRow(): Long {
        val sel = ObjCRuntime.sel("editedRow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
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
    
    // @property doubleAction
    open fun doubleAction(): MemorySegment {
        val sel = ObjCRuntime.sel("doubleAction")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDoubleAction(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDoubleAction:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sortDescriptors
    /** @return NSArray<NSSortDescriptor *> * */
    open fun sortDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("sortDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSortDescriptors(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSortDescriptors:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property highlightedTableColumn
    open fun highlightedTableColumn(): MemorySegment {
        val sel = ObjCRuntime.sel("highlightedTableColumn")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHighlightedTableColumn(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHighlightedTableColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property verticalMotionCanBeginDrag
    open fun verticalMotionCanBeginDrag(): Boolean {
        val sel = ObjCRuntime.sel("verticalMotionCanBeginDrag")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setVerticalMotionCanBeginDrag(value: Boolean) {
        val sel = ObjCRuntime.sel("setVerticalMotionCanBeginDrag:")
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
    
    // @property allowsEmptySelection
    open fun allowsEmptySelection(): Boolean {
        val sel = ObjCRuntime.sel("allowsEmptySelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsEmptySelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsEmptySelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsColumnSelection
    open fun allowsColumnSelection(): Boolean {
        val sel = ObjCRuntime.sel("allowsColumnSelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsColumnSelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsColumnSelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectedColumnIndexes
    open fun selectedColumnIndexes(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedColumnIndexes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectedRowIndexes
    open fun selectedRowIndexes(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedRowIndexes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectedColumn
    open fun selectedColumn(): Long {
        val sel = ObjCRuntime.sel("selectedColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property selectedRow
    open fun selectedRow(): Long {
        val sel = ObjCRuntime.sel("selectedRow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property numberOfSelectedColumns
    open fun numberOfSelectedColumns(): Long {
        val sel = ObjCRuntime.sel("numberOfSelectedColumns")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property numberOfSelectedRows
    open fun numberOfSelectedRows(): Long {
        val sel = ObjCRuntime.sel("numberOfSelectedRows")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
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
    
    // @property style
    open fun style(): MemorySegment {
        val sel = ObjCRuntime.sel("style")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property effectiveStyle
    open fun effectiveStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("effectiveStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectionHighlightStyle
    open fun selectionHighlightStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionHighlightStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionHighlightStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionHighlightStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property draggingDestinationFeedbackStyle
    open fun draggingDestinationFeedbackStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("draggingDestinationFeedbackStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDraggingDestinationFeedbackStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDraggingDestinationFeedbackStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autosaveName
    open fun autosaveName(): MemorySegment {
        val sel = ObjCRuntime.sel("autosaveName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAutosaveName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAutosaveName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autosaveTableColumns
    open fun autosaveTableColumns(): Boolean {
        val sel = ObjCRuntime.sel("autosaveTableColumns")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutosaveTableColumns(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutosaveTableColumns:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property floatsGroupRows
    open fun floatsGroupRows(): Boolean {
        val sel = ObjCRuntime.sel("floatsGroupRows")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setFloatsGroupRows(value: Boolean) {
        val sel = ObjCRuntime.sel("setFloatsGroupRows:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rowActionsVisible
    open fun rowActionsVisible(): Boolean {
        val sel = ObjCRuntime.sel("rowActionsVisible")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setRowActionsVisible(value: Boolean) {
        val sel = ObjCRuntime.sel("setRowActionsVisible:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hiddenRowIndexes
    open fun hiddenRowIndexes(): MemorySegment {
        val sel = ObjCRuntime.sel("hiddenRowIndexes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property registeredNibsByIdentifier
    /** @return NSDictionary<NSUserInterfaceItemIdentifier,NSNib *> * */
    open fun registeredNibsByIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("registeredNibsByIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property usesStaticContents
    open fun usesStaticContents(): Boolean {
        val sel = ObjCRuntime.sel("usesStaticContents")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesStaticContents(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesStaticContents:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property userInterfaceLayoutDirection
    override fun userInterfaceLayoutDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("userInterfaceLayoutDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setUserInterfaceLayoutDirection(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUserInterfaceLayoutDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesAutomaticRowHeights
    open fun usesAutomaticRowHeights(): Boolean {
        val sel = ObjCRuntime.sel("usesAutomaticRowHeights")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesAutomaticRowHeights(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesAutomaticRowHeights:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSDeprecated on NSTableView ─────────────────────────────────────────

fun NSTableView.setDrawsGrid(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setDrawsGrid:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSTableView.drawsGrid(): Boolean {
    val sel = ObjCRuntime.sel("drawsGrid")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSTableView.selectColumn_byExtendingSelection(column: Long, extend: Boolean): Unit {
    val sel = ObjCRuntime.sel("selectColumn:byExtendingSelection:")
    ObjCRuntime.msgSend(null, this.ptr, sel, column, extend)
}

fun NSTableView.selectRow_byExtendingSelection(row: Long, extend: Boolean): Unit {
    val sel = ObjCRuntime.sel("selectRow:byExtendingSelection:")
    ObjCRuntime.msgSend(null, this.ptr, sel, row, extend)
}

fun NSTableView.selectedColumnEnumerator(): MemorySegment {
    val sel = ObjCRuntime.sel("selectedColumnEnumerator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSTableView.selectedRowEnumerator(): MemorySegment {
    val sel = ObjCRuntime.sel("selectedRowEnumerator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSTableView.dragImageForRows_event_dragImageOffset(dragRows: MemorySegment, dragEvent: MemorySegment, dragImageOffset: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dragImageForRows:event:dragImageOffset:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, dragRows, dragEvent, dragImageOffset) as MemorySegment
}

fun NSTableView.setAutoresizesAllColumnsToFit(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setAutoresizesAllColumnsToFit:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSTableView.autoresizesAllColumnsToFit(): Boolean {
    val sel = ObjCRuntime.sel("autoresizesAllColumnsToFit")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSTableView.columnsInRect(rect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("columnsInRect:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel, rect) as MemorySegment
}

fun NSTableView.preparedCellAtColumn_row(column: Long, row: Long): MemorySegment {
    val sel = ObjCRuntime.sel("preparedCellAtColumn:row:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, column, row) as MemorySegment
}

fun NSTableView.textShouldBeginEditing(textObject: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("textShouldBeginEditing:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, textObject) as Boolean
}

fun NSTableView.textShouldEndEditing(textObject: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("textShouldEndEditing:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, textObject) as Boolean
}

fun NSTableView.textDidBeginEditing(notification: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("textDidBeginEditing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, notification)
}

fun NSTableView.textDidEndEditing(notification: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("textDidEndEditing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, notification)
}

fun NSTableView.textDidChange(notification: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("textDidChange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, notification)
}

fun NSTableView.shouldFocusCell_atColumn_row(cell: MemorySegment, column: Long, row: Long): Boolean {
    val sel = ObjCRuntime.sel("shouldFocusCell:atColumn:row:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, cell, column, row) as Boolean
}

fun NSTableView.focusedColumn(): Long {
    val sel = ObjCRuntime.sel("focusedColumn")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSTableView.setFocusedColumn(focusedColumn: Long): Unit {
    val sel = ObjCRuntime.sel("setFocusedColumn:")
    ObjCRuntime.msgSend(null, this.ptr, sel, focusedColumn)
}

fun NSTableView.performClickOnCellAtColumn_row(column: Long, row: Long): Unit {
    val sel = ObjCRuntime.sel("performClickOnCellAtColumn:row:")
    ObjCRuntime.msgSend(null, this.ptr, sel, column, row)
}

