package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSControl
 * Superclass: NSView
 */
open class NSControl(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSControl") }
        
    }
    
    override fun initWithFrame(frameRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun sizeThatFits(size: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("sizeThatFits:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"))) as MemorySegment
    }
    
    open fun sizeToFit(): Unit {
        val sel = ObjCRuntime.sel("sizeToFit")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun sendActionOn(mask: MemorySegment): Long {
        val sel = ObjCRuntime.sel("sendActionOn:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, mask) as Long
    }
    
    open fun sendAction_to(action: MemorySegment, target: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("sendAction:to:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, action, target) as Boolean
    }
    
    open fun takeIntValueFrom(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("takeIntValueFrom:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun takeFloatValueFrom(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("takeFloatValueFrom:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun takeDoubleValueFrom(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("takeDoubleValueFrom:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun takeStringValueFrom(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("takeStringValueFrom:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun takeObjectValueFrom(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("takeObjectValueFrom:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun takeIntegerValueFrom(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("takeIntegerValueFrom:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun performClick(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performClick:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun expansionFrameWithFrame(contentFrame: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("expansionFrameWithFrame:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(contentFrame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun drawWithExpansionFrame_inView(contentFrame: MemorySegment, view: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawWithExpansionFrame:inView:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(contentFrame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), view)
    }
    
    // @property target
    open fun target(): MemorySegment {
        val sel = ObjCRuntime.sel("target")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTarget(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTarget:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property action
    open fun action(): MemorySegment {
        val sel = ObjCRuntime.sel("action")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAction(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAction:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tag
    override fun tag(): Long {
        val sel = ObjCRuntime.sel("tag")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setTag(value: Long) {
        val sel = ObjCRuntime.sel("setTag:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property ignoresMultiClick
    open fun ignoresMultiClick(): Boolean {
        val sel = ObjCRuntime.sel("ignoresMultiClick")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIgnoresMultiClick(value: Boolean) {
        val sel = ObjCRuntime.sel("setIgnoresMultiClick:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property continuous
    open fun isContinuous(): Boolean {
        val sel = ObjCRuntime.sel("isContinuous")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setContinuous(value: Boolean) {
        val sel = ObjCRuntime.sel("setContinuous:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property enabled
    open fun isEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEnabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setEnabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property refusesFirstResponder
    open fun refusesFirstResponder(): Boolean {
        val sel = ObjCRuntime.sel("refusesFirstResponder")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setRefusesFirstResponder(value: Boolean) {
        val sel = ObjCRuntime.sel("setRefusesFirstResponder:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property highlighted
    open fun isHighlighted(): Boolean {
        val sel = ObjCRuntime.sel("isHighlighted")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHighlighted(value: Boolean) {
        val sel = ObjCRuntime.sel("setHighlighted:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property controlSize
    open fun controlSize(): MemorySegment {
        val sel = ObjCRuntime.sel("controlSize")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setControlSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setControlSize:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property formatter
    open fun formatter(): MemorySegment {
        val sel = ObjCRuntime.sel("formatter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFormatter(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFormatter:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property objectValue
    open fun objectValue(): MemorySegment {
        val sel = ObjCRuntime.sel("objectValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setObjectValue(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setObjectValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property stringValue
    open fun stringValue(): MemorySegment {
        val sel = ObjCRuntime.sel("stringValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStringValue(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStringValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun stringValueAsString(): String = ObjCRuntime.toJavaString(stringValue())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setStringValue(value: String) = setStringValue(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property attributedStringValue
    open fun attributedStringValue(): MemorySegment {
        val sel = ObjCRuntime.sel("attributedStringValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttributedStringValue(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttributedStringValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property intValue
    open fun intValue(): Int {
        val sel = ObjCRuntime.sel("intValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    open fun setIntValue(value: Int) {
        val sel = ObjCRuntime.sel("setIntValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property integerValue
    open fun integerValue(): Long {
        val sel = ObjCRuntime.sel("integerValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setIntegerValue(value: Long) {
        val sel = ObjCRuntime.sel("setIntegerValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property floatValue
    open fun floatValue(): Float {
        val sel = ObjCRuntime.sel("floatValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    open fun setFloatValue(value: Float) {
        val sel = ObjCRuntime.sel("setFloatValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property doubleValue
    open fun doubleValue(): Double {
        val sel = ObjCRuntime.sel("doubleValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setDoubleValue(value: Double) {
        val sel = ObjCRuntime.sel("setDoubleValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property font
    open fun font(): MemorySegment {
        val sel = ObjCRuntime.sel("font")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFont(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFont:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesSingleLineMode
    open fun usesSingleLineMode(): Boolean {
        val sel = ObjCRuntime.sel("usesSingleLineMode")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesSingleLineMode(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesSingleLineMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
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
    
    // @property alignment
    open fun alignment(): MemorySegment {
        val sel = ObjCRuntime.sel("alignment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAlignment(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAlignment:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property baseWritingDirection
    open fun baseWritingDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("baseWritingDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBaseWritingDirection(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBaseWritingDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsExpansionToolTips
    open fun allowsExpansionToolTips(): Boolean {
        val sel = ObjCRuntime.sel("allowsExpansionToolTips")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsExpansionToolTips(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsExpansionToolTips:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSControlEditableTextMethods on NSControl ─────────────────────────────────────────

fun NSControl.currentEditor(): MemorySegment {
    val sel = ObjCRuntime.sel("currentEditor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSControl.abortEditing(): Boolean {
    val sel = ObjCRuntime.sel("abortEditing")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSControl.validateEditing(): Unit {
    val sel = ObjCRuntime.sel("validateEditing")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSControl.editWithFrame_editor_delegate_event(rect: MemorySegment, textObj: MemorySegment, delegate: MemorySegment, event: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("editWithFrame:editor:delegate:event:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, textObj, delegate, event)
}

fun NSControl.selectWithFrame_editor_delegate_start_length(rect: MemorySegment, textObj: MemorySegment, delegate: MemorySegment, selStart: Long, selLength: Long): Unit {
    val sel = ObjCRuntime.sel("selectWithFrame:editor:delegate:start:length:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, textObj, delegate, selStart, selLength)
}

fun NSControl.endEditing(textObj: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("endEditing:")
    ObjCRuntime.msgSend(null, this.ptr, sel, textObj)
}

// ── Category: NSDeprecated on NSControl ─────────────────────────────────────────

fun NSControl.setFloatingPointFormat_left_right(autoRange: Boolean, leftDigits: Long, rightDigits: Long): Unit {
    val sel = ObjCRuntime.sel("setFloatingPointFormat:left:right:")
    ObjCRuntime.msgSend(null, this.ptr, sel, autoRange, leftDigits, rightDigits)
}

fun NSControl.selectedCell(): MemorySegment {
    val sel = ObjCRuntime.sel("selectedCell")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSControl.selectedTag(): Long {
    val sel = ObjCRuntime.sel("selectedTag")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSControl.setNeedsDisplay(): Unit {
    val sel = ObjCRuntime.sel("setNeedsDisplay")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSControl.calcSize(): Unit {
    val sel = ObjCRuntime.sel("calcSize")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSControl.updateCell(cell: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("updateCell:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cell)
}

fun NSControl.updateCellInside(cell: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("updateCellInside:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cell)
}

fun NSControl.drawCellInside(cell: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("drawCellInside:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cell)
}

fun NSControl.drawCell(cell: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("drawCell:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cell)
}

fun NSControl.selectCell(cell: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("selectCell:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cell)
}

fun NSControl.cell(): MemorySegment {
    val sel = ObjCRuntime.sel("cell")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSControl.setCell(cell: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setCell:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cell)
}

// Class method: +[NSControl cellClass]
fun NSControl_cellClass(): MemorySegment {
    val sel = ObjCRuntime.sel("cellClass")
    val cls = ObjCRuntime.getClass("NSControl")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSControl setCellClass:]
fun NSControl_setCellClass(cellClass: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setCellClass:")
    val cls = ObjCRuntime.getClass("NSControl")
    ObjCRuntime.msgSend(null, cls, sel, cellClass)
}

// @property cellClass
fun NSControl.cellClass(): MemorySegment {
    val sel = ObjCRuntime.sel("cellClass")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}
fun NSControl.setCellClass(value: MemorySegment) {
    val sel = ObjCRuntime.sel("setCellClass:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value)
}

// ── Category: NSConstraintBasedLayoutLayering on NSControl ─────────────────────────────────────────

fun NSControl.invalidateIntrinsicContentSizeForCell(cell: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("invalidateIntrinsicContentSizeForCell:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cell)
}

