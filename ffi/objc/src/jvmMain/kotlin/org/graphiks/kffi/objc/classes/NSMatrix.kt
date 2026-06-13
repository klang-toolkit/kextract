package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMatrix
 * Superclass: NSControl
 * Protocols: NSUserInterfaceValidations, NSViewToolTipOwner
 */
open class NSMatrix(override val ptr: MemorySegment) : NSControl(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMatrix") }
        
    }
    
    override fun initWithFrame(frameRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun initWithFrame_mode_prototype_numberOfRows_numberOfColumns(frameRect: MemorySegment, mode: MemorySegment, cell: MemorySegment, rowsHigh: Long, colsWide: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:mode:prototype:numberOfRows:numberOfColumns:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), mode, cell, rowsHigh, colsWide) as MemorySegment
    }
    
    open fun initWithFrame_mode_cellClass_numberOfRows_numberOfColumns(frameRect: MemorySegment, mode: MemorySegment, factoryId: MemorySegment, rowsHigh: Long, colsWide: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:mode:cellClass:numberOfRows:numberOfColumns:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), mode, factoryId, rowsHigh, colsWide) as MemorySegment
    }
    
    open fun makeCellAtRow_column(row: Long, col: Long): MemorySegment {
        val sel = ObjCRuntime.sel("makeCellAtRow:column:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row, col) as MemorySegment
    }
    
    open fun sendAction_to_forAllCells(selector: MemorySegment, `object`: MemorySegment, flag: Boolean): Unit {
        val sel = ObjCRuntime.sel("sendAction:to:forAllCells:")
        ObjCRuntime.msgSend(null, ptr, sel, selector, `object`, flag)
    }
    
    open fun sortUsingSelector(comparator: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("sortUsingSelector:")
        ObjCRuntime.msgSend(null, ptr, sel, comparator)
    }
    
    open fun sortUsingFunction_context(compare: MemorySegment, context: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("sortUsingFunction:context:")
        ObjCRuntime.msgSend(null, ptr, sel, compare, context)
    }
    
    open fun setSelectionFrom_to_anchor_highlight(startPos: Long, endPos: Long, anchorPos: Long, lit: Boolean): Unit {
        val sel = ObjCRuntime.sel("setSelectionFrom:to:anchor:highlight:")
        ObjCRuntime.msgSend(null, ptr, sel, startPos, endPos, anchorPos, lit)
    }
    
    open fun deselectSelectedCell(): Unit {
        val sel = ObjCRuntime.sel("deselectSelectedCell")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun deselectAllCells(): Unit {
        val sel = ObjCRuntime.sel("deselectAllCells")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun selectCellAtRow_column(row: Long, col: Long): Unit {
        val sel = ObjCRuntime.sel("selectCellAtRow:column:")
        ObjCRuntime.msgSend(null, ptr, sel, row, col)
    }
    
    open fun selectAll(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectAll:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectCellWithTag(tag: Long): Boolean {
        val sel = ObjCRuntime.sel("selectCellWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, tag) as Boolean
    }
    
    open fun setScrollable(flag: Boolean): Unit {
        val sel = ObjCRuntime.sel("setScrollable:")
        ObjCRuntime.msgSend(null, ptr, sel, flag)
    }
    
    open fun setState_atRow_column(value: Long, row: Long, col: Long): Unit {
        val sel = ObjCRuntime.sel("setState:atRow:column:")
        ObjCRuntime.msgSend(null, ptr, sel, value, row, col)
    }
    
    open fun getNumberOfRows_columns(rowCount: MemorySegment, colCount: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getNumberOfRows:columns:")
        ObjCRuntime.msgSend(null, ptr, sel, rowCount, colCount)
    }
    
    open fun cellAtRow_column(row: Long, col: Long): MemorySegment {
        val sel = ObjCRuntime.sel("cellAtRow:column:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row, col) as MemorySegment
    }
    
    open fun cellFrameAtRow_column(row: Long, col: Long): MemorySegment {
        val sel = ObjCRuntime.sel("cellFrameAtRow:column:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, row, col) as MemorySegment
    }
    
    open fun getRow_column_ofCell(row: MemorySegment, col: MemorySegment, cell: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("getRow:column:ofCell:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, row, col, cell) as Boolean
    }
    
    open fun getRow_column_forPoint(row: MemorySegment, col: MemorySegment, point: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("getRow:column:forPoint:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, row, col, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as Boolean
    }
    
    open fun renewRows_columns(newRows: Long, newCols: Long): Unit {
        val sel = ObjCRuntime.sel("renewRows:columns:")
        ObjCRuntime.msgSend(null, ptr, sel, newRows, newCols)
    }
    
    open fun putCell_atRow_column(newCell: MemorySegment, row: Long, col: Long): Unit {
        val sel = ObjCRuntime.sel("putCell:atRow:column:")
        ObjCRuntime.msgSend(null, ptr, sel, newCell, row, col)
    }
    
    open fun addRow(): Unit {
        val sel = ObjCRuntime.sel("addRow")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun addRowWithCells(newCells: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addRowWithCells:")
        ObjCRuntime.msgSend(null, ptr, sel, newCells)
    }
    
    open fun insertRow(row: Long): Unit {
        val sel = ObjCRuntime.sel("insertRow:")
        ObjCRuntime.msgSend(null, ptr, sel, row)
    }
    
    open fun insertRow_withCells(row: Long, newCells: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertRow:withCells:")
        ObjCRuntime.msgSend(null, ptr, sel, row, newCells)
    }
    
    open fun removeRow(row: Long): Unit {
        val sel = ObjCRuntime.sel("removeRow:")
        ObjCRuntime.msgSend(null, ptr, sel, row)
    }
    
    open fun addColumn(): Unit {
        val sel = ObjCRuntime.sel("addColumn")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun addColumnWithCells(newCells: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addColumnWithCells:")
        ObjCRuntime.msgSend(null, ptr, sel, newCells)
    }
    
    open fun insertColumn(column: Long): Unit {
        val sel = ObjCRuntime.sel("insertColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, column)
    }
    
    open fun insertColumn_withCells(column: Long, newCells: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertColumn:withCells:")
        ObjCRuntime.msgSend(null, ptr, sel, column, newCells)
    }
    
    open fun removeColumn(col: Long): Unit {
        val sel = ObjCRuntime.sel("removeColumn:")
        ObjCRuntime.msgSend(null, ptr, sel, col)
    }
    
    open fun cellWithTag(tag: Long): MemorySegment {
        val sel = ObjCRuntime.sel("cellWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, tag) as MemorySegment
    }
    
    open fun sizeToCells(): Unit {
        val sel = ObjCRuntime.sel("sizeToCells")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setValidateSize(flag: Boolean): Unit {
        val sel = ObjCRuntime.sel("setValidateSize:")
        ObjCRuntime.msgSend(null, ptr, sel, flag)
    }
    
    open fun drawCellAtRow_column(row: Long, col: Long): Unit {
        val sel = ObjCRuntime.sel("drawCellAtRow:column:")
        ObjCRuntime.msgSend(null, ptr, sel, row, col)
    }
    
    open fun highlightCell_atRow_column(flag: Boolean, row: Long, col: Long): Unit {
        val sel = ObjCRuntime.sel("highlightCell:atRow:column:")
        ObjCRuntime.msgSend(null, ptr, sel, flag, row, col)
    }
    
    open fun scrollCellToVisibleAtRow_column(row: Long, col: Long): Unit {
        val sel = ObjCRuntime.sel("scrollCellToVisibleAtRow:column:")
        ObjCRuntime.msgSend(null, ptr, sel, row, col)
    }
    
    override fun mouseDown(event: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("mouseDown:")
        ObjCRuntime.msgSend(null, ptr, sel, event)
    }
    
    override fun performKeyEquivalent(event: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("performKeyEquivalent:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, event) as Boolean
    }
    
    open fun sendAction(): Boolean {
        val sel = ObjCRuntime.sel("sendAction")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun sendDoubleAction(): Unit {
        val sel = ObjCRuntime.sel("sendDoubleAction")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun textShouldBeginEditing(textObject: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("textShouldBeginEditing:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, textObject) as Boolean
    }
    
    open fun textShouldEndEditing(textObject: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("textShouldEndEditing:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, textObject) as Boolean
    }
    
    open fun textDidBeginEditing(notification: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("textDidBeginEditing:")
        ObjCRuntime.msgSend(null, ptr, sel, notification)
    }
    
    open fun textDidEndEditing(notification: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("textDidEndEditing:")
        ObjCRuntime.msgSend(null, ptr, sel, notification)
    }
    
    open fun textDidChange(notification: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("textDidChange:")
        ObjCRuntime.msgSend(null, ptr, sel, notification)
    }
    
    open fun selectText(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectText:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectTextAtRow_column(row: Long, col: Long): MemorySegment {
        val sel = ObjCRuntime.sel("selectTextAtRow:column:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, row, col) as MemorySegment
    }
    
    override fun acceptsFirstMouse(event: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("acceptsFirstMouse:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, event) as Boolean
    }
    
    open fun resetCursorRects(): Unit {
        val sel = ObjCRuntime.sel("resetCursorRects")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setToolTip_forCell(toolTipString: MemorySegment, cell: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setToolTip:forCell:")
        ObjCRuntime.msgSend(null, ptr, sel, toolTipString, cell)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setToolTip_forCell(toolTipString: String, cell: MemorySegment): Unit = setToolTip_forCell(ObjCRuntime.newNSString(Arena.global(), toolTipString), cell)
    
    open fun toolTipForCell(cell: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("toolTipForCell:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, cell) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun toolTipForCellAsString(cell: MemorySegment): String = ObjCRuntime.toJavaString(toolTipForCell(cell))
    
    // @property cellClass
    open fun cellClass(): MemorySegment {
        val sel = ObjCRuntime.sel("cellClass")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCellClass(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCellClass:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property prototype
    open fun prototype(): MemorySegment {
        val sel = ObjCRuntime.sel("prototype")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPrototype(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPrototype:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property mode
    open fun mode(): MemorySegment {
        val sel = ObjCRuntime.sel("mode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMode:")
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
    
    // @property cells
    /** @return NSArray<NSCell *> * */
    open fun cells(): MemorySegment {
        val sel = ObjCRuntime.sel("cells")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectedCell
    open fun selectedCell(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedCell")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectedCells
    /** @return NSArray<__kindof NSCell *> * */
    open fun selectedCells(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedCells")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectedRow
    open fun selectedRow(): Long {
        val sel = ObjCRuntime.sel("selectedRow")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property selectedColumn
    open fun selectedColumn(): Long {
        val sel = ObjCRuntime.sel("selectedColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property selectionByRect
    open fun isSelectionByRect(): Boolean {
        val sel = ObjCRuntime.sel("isSelectionByRect")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSelectionByRect(value: Boolean) {
        val sel = ObjCRuntime.sel("setSelectionByRect:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property cellSize
    open fun cellSize(): MemorySegment {
        val sel = ObjCRuntime.sel("cellSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setCellSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCellSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
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
    
    // @property backgroundColor
    open fun backgroundColor(): MemorySegment {
        val sel = ObjCRuntime.sel("backgroundColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackgroundColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackgroundColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property cellBackgroundColor
    open fun cellBackgroundColor(): MemorySegment {
        val sel = ObjCRuntime.sel("cellBackgroundColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCellBackgroundColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCellBackgroundColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property drawsCellBackground
    open fun drawsCellBackground(): Boolean {
        val sel = ObjCRuntime.sel("drawsCellBackground")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDrawsCellBackground(value: Boolean) {
        val sel = ObjCRuntime.sel("setDrawsCellBackground:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property drawsBackground
    open fun drawsBackground(): Boolean {
        val sel = ObjCRuntime.sel("drawsBackground")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDrawsBackground(value: Boolean) {
        val sel = ObjCRuntime.sel("setDrawsBackground:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
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
    
    // @property doubleAction
    open fun doubleAction(): MemorySegment {
        val sel = ObjCRuntime.sel("doubleAction")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDoubleAction(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDoubleAction:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autosizesCells
    open fun autosizesCells(): Boolean {
        val sel = ObjCRuntime.sel("autosizesCells")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutosizesCells(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutosizesCells:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autoscroll
    open fun isAutoscroll(): Boolean {
        val sel = ObjCRuntime.sel("isAutoscroll")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutoscroll(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutoscroll:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property mouseDownFlags
    open fun mouseDownFlags(): Long {
        val sel = ObjCRuntime.sel("mouseDownFlags")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property delegate
    /** @return id<NSMatrixDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autorecalculatesCellSize
    open fun autorecalculatesCellSize(): Boolean {
        val sel = ObjCRuntime.sel("autorecalculatesCellSize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutorecalculatesCellSize(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutorecalculatesCellSize:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSKeyboardUI on NSMatrix ─────────────────────────────────────────

fun NSMatrix.tabKeyTraversesCells(): Boolean {
    val sel = ObjCRuntime.sel("tabKeyTraversesCells")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSMatrix.setTabKeyTraversesCells(tabKeyTraversesCells: Boolean): Unit {
    val sel = ObjCRuntime.sel("setTabKeyTraversesCells:")
    ObjCRuntime.msgSend(null, this.ptr, sel, tabKeyTraversesCells)
}

fun NSMatrix.keyCell(): MemorySegment {
    val sel = ObjCRuntime.sel("keyCell")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSMatrix.setKeyCell(keyCell: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setKeyCell:")
    ObjCRuntime.msgSend(null, this.ptr, sel, keyCell)
}

