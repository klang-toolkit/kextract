package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSText
 * Superclass: NSView
 * Protocols: NSChangeSpelling, NSIgnoreMisspelledWords
 */
open class NSText(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSText") }
        
    }
    
    override fun initWithFrame(frameRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun replaceCharactersInRange_withString(range: MemorySegment, string: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceCharactersInRange:withString:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), string)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun replaceCharactersInRange_withString(range: MemorySegment, string: String): Unit = replaceCharactersInRange_withString(range, ObjCRuntime.newNSString(Arena.global(), string))
    
    open fun replaceCharactersInRange_withRTF(range: MemorySegment, rtfData: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceCharactersInRange:withRTF:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), rtfData)
    }
    
    open fun replaceCharactersInRange_withRTFD(range: MemorySegment, rtfdData: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceCharactersInRange:withRTFD:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), rtfdData)
    }
    
    open fun RTFFromRange(range: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("RTFFromRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
    }
    
    open fun RTFDFromRange(range: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("RTFDFromRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
    }
    
    open fun writeRTFDToFile_atomically(path: MemorySegment, flag: Boolean): Boolean {
        val sel = ObjCRuntime.sel("writeRTFDToFile:atomically:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, path, flag) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun writeRTFDToFile_atomically(path: String, flag: Boolean): Boolean = writeRTFDToFile_atomically(ObjCRuntime.newNSString(Arena.global(), path), flag)
    
    open fun readRTFDFromFile(path: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("readRTFDFromFile:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, path) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun readRTFDFromFile(path: String): Boolean = readRTFDFromFile(ObjCRuntime.newNSString(Arena.global(), path))
    
    open fun scrollRangeToVisible(range: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scrollRangeToVisible:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun setTextColor_range(color: MemorySegment, range: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTextColor:range:")
        ObjCRuntime.msgSend(null, ptr, sel, color, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun setFont_range(font: MemorySegment, range: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setFont:range:")
        ObjCRuntime.msgSend(null, ptr, sel, font, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun sizeToFit(): Unit {
        val sel = ObjCRuntime.sel("sizeToFit")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun copy(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("copy:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun copyFont(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("copyFont:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun copyRuler(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("copyRuler:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun cut(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("cut:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun delete(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("delete:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun paste(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("paste:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun pasteFont(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("pasteFont:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun pasteRuler(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("pasteRuler:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun selectAll(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectAll:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun changeFont(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("changeFont:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun alignLeft(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("alignLeft:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun alignRight(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("alignRight:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun alignCenter(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("alignCenter:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun subscript(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("subscript:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun superscript(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("superscript:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun underline(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("underline:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun unscript(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unscript:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun showGuessPanel(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("showGuessPanel:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun checkSpelling(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("checkSpelling:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun toggleRuler(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("toggleRuler:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    // @property string
    open fun string(): MemorySegment {
        val sel = ObjCRuntime.sel("string")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setString(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setString:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun stringAsString(): String = ObjCRuntime.toJavaString(string())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setString(value: String) = setString(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property delegate
    /** @return id<NSTextDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
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
    
    // @property selectable
    open fun isSelectable(): Boolean {
        val sel = ObjCRuntime.sel("isSelectable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSelectable(value: Boolean) {
        val sel = ObjCRuntime.sel("setSelectable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property richText
    open fun isRichText(): Boolean {
        val sel = ObjCRuntime.sel("isRichText")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setRichText(value: Boolean) {
        val sel = ObjCRuntime.sel("setRichText:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property importsGraphics
    open fun importsGraphics(): Boolean {
        val sel = ObjCRuntime.sel("importsGraphics")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setImportsGraphics(value: Boolean) {
        val sel = ObjCRuntime.sel("setImportsGraphics:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property fieldEditor
    open fun isFieldEditor(): Boolean {
        val sel = ObjCRuntime.sel("isFieldEditor")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setFieldEditor(value: Boolean) {
        val sel = ObjCRuntime.sel("setFieldEditor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesFontPanel
    open fun usesFontPanel(): Boolean {
        val sel = ObjCRuntime.sel("usesFontPanel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesFontPanel(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesFontPanel:")
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
    
    // @property backgroundColor
    open fun backgroundColor(): MemorySegment {
        val sel = ObjCRuntime.sel("backgroundColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackgroundColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackgroundColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rulerVisible
    open fun isRulerVisible(): Boolean {
        val sel = ObjCRuntime.sel("isRulerVisible")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property selectedRange
    open fun selectedRange(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedRange")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    open fun setSelectedRange(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectedRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
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
    
    // @property textColor
    open fun textColor(): MemorySegment {
        val sel = ObjCRuntime.sel("textColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTextColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTextColor:")
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
    
    // @property maxSize
    open fun maxSize(): MemorySegment {
        val sel = ObjCRuntime.sel("maxSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setMaxSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMaxSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property minSize
    open fun minSize(): MemorySegment {
        val sel = ObjCRuntime.sel("minSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setMinSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMinSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property horizontallyResizable
    open fun isHorizontallyResizable(): Boolean {
        val sel = ObjCRuntime.sel("isHorizontallyResizable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHorizontallyResizable(value: Boolean) {
        val sel = ObjCRuntime.sel("setHorizontallyResizable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property verticallyResizable
    open fun isVerticallyResizable(): Boolean {
        val sel = ObjCRuntime.sel("isVerticallyResizable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setVerticallyResizable(value: Boolean) {
        val sel = ObjCRuntime.sel("setVerticallyResizable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

