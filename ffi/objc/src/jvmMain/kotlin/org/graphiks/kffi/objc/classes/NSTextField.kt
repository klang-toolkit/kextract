package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextField
 * Superclass: NSControl
 * Protocols: NSUserInterfaceValidations, NSAccessibilityNavigableStaticText, NSTextContent
 */
open class NSTextField(override val ptr: MemorySegment) : NSControl(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextField") }
        
    }
    
    open fun selectText(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectText:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
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
    
    // @property placeholderString
    open fun placeholderString(): MemorySegment {
        val sel = ObjCRuntime.sel("placeholderString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPlaceholderString(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPlaceholderString:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun placeholderStringAsString(): String = ObjCRuntime.toJavaString(placeholderString())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPlaceholderString(value: String) = setPlaceholderString(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property placeholderAttributedString
    open fun placeholderAttributedString(): MemorySegment {
        val sel = ObjCRuntime.sel("placeholderAttributedString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPlaceholderAttributedString(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPlaceholderAttributedString:")
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
    
    // @property drawsBackground
    open fun drawsBackground(): Boolean {
        val sel = ObjCRuntime.sel("drawsBackground")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDrawsBackground(value: Boolean) {
        val sel = ObjCRuntime.sel("setDrawsBackground:")
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
    
    // @property bordered
    open fun isBordered(): Boolean {
        val sel = ObjCRuntime.sel("isBordered")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setBordered(value: Boolean) {
        val sel = ObjCRuntime.sel("setBordered:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property bezeled
    open fun isBezeled(): Boolean {
        val sel = ObjCRuntime.sel("isBezeled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setBezeled(value: Boolean) {
        val sel = ObjCRuntime.sel("setBezeled:")
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
    
    // @property delegate
    /** @return id<NSTextFieldDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property acceptsFirstResponder
    override fun acceptsFirstResponder(): Boolean {
        val sel = ObjCRuntime.sel("acceptsFirstResponder")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property bezelStyle
    open fun bezelStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("bezelStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBezelStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBezelStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property preferredMaxLayoutWidth
    open fun preferredMaxLayoutWidth(): Double {
        val sel = ObjCRuntime.sel("preferredMaxLayoutWidth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setPreferredMaxLayoutWidth(value: Double) {
        val sel = ObjCRuntime.sel("setPreferredMaxLayoutWidth:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maximumNumberOfLines
    open fun maximumNumberOfLines(): Long {
        val sel = ObjCRuntime.sel("maximumNumberOfLines")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setMaximumNumberOfLines(value: Long) {
        val sel = ObjCRuntime.sel("setMaximumNumberOfLines:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsDefaultTighteningForTruncation
    open fun allowsDefaultTighteningForTruncation(): Boolean {
        val sel = ObjCRuntime.sel("allowsDefaultTighteningForTruncation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsDefaultTighteningForTruncation(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsDefaultTighteningForTruncation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property lineBreakStrategy
    open fun lineBreakStrategy(): MemorySegment {
        val sel = ObjCRuntime.sel("lineBreakStrategy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLineBreakStrategy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLineBreakStrategy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsWritingTools
    open fun allowsWritingTools(): Boolean {
        val sel = ObjCRuntime.sel("allowsWritingTools")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsWritingTools(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsWritingTools:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsWritingToolsAffordance
    open fun allowsWritingToolsAffordance(): Boolean {
        val sel = ObjCRuntime.sel("allowsWritingToolsAffordance")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsWritingToolsAffordance(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsWritingToolsAffordance:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property placeholderStrings
    /** @return NSArray<NSString *> * */
    open fun placeholderStrings(): MemorySegment {
        val sel = ObjCRuntime.sel("placeholderStrings")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPlaceholderStrings(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPlaceholderStrings:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property placeholderAttributedStrings
    /** @return NSArray<NSAttributedString *> * */
    open fun placeholderAttributedStrings(): MemorySegment {
        val sel = ObjCRuntime.sel("placeholderAttributedStrings")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPlaceholderAttributedStrings(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPlaceholderAttributedStrings:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property resolvesNaturalAlignmentWithBaseWritingDirection
    open fun resolvesNaturalAlignmentWithBaseWritingDirection(): Boolean {
        val sel = ObjCRuntime.sel("resolvesNaturalAlignmentWithBaseWritingDirection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setResolvesNaturalAlignmentWithBaseWritingDirection(value: Boolean) {
        val sel = ObjCRuntime.sel("setResolvesNaturalAlignmentWithBaseWritingDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSTouchBar on NSTextField ─────────────────────────────────────────

fun NSTextField.isAutomaticTextCompletionEnabled(): Boolean {
    val sel = ObjCRuntime.sel("isAutomaticTextCompletionEnabled")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSTextField.setAutomaticTextCompletionEnabled(automaticTextCompletionEnabled: Boolean): Unit {
    val sel = ObjCRuntime.sel("setAutomaticTextCompletionEnabled:")
    ObjCRuntime.msgSend(null, this.ptr, sel, automaticTextCompletionEnabled)
}

fun NSTextField.allowsCharacterPickerTouchBarItem(): Boolean {
    val sel = ObjCRuntime.sel("allowsCharacterPickerTouchBarItem")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSTextField.setAllowsCharacterPickerTouchBarItem(allowsCharacterPickerTouchBarItem: Boolean): Unit {
    val sel = ObjCRuntime.sel("setAllowsCharacterPickerTouchBarItem:")
    ObjCRuntime.msgSend(null, this.ptr, sel, allowsCharacterPickerTouchBarItem)
}

// ── Category: NSTextFieldConvenience on NSTextField ─────────────────────────────────────────

// Class method: +[NSTextField labelWithString:]
fun NSTextField_labelWithString(stringValue: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("labelWithString:")
    val cls = ObjCRuntime.getClass("NSTextField")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, stringValue) as MemorySegment
}

// Class method: +[NSTextField wrappingLabelWithString:]
fun NSTextField_wrappingLabelWithString(stringValue: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("wrappingLabelWithString:")
    val cls = ObjCRuntime.getClass("NSTextField")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, stringValue) as MemorySegment
}

// Class method: +[NSTextField labelWithAttributedString:]
fun NSTextField_labelWithAttributedString(attributedStringValue: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("labelWithAttributedString:")
    val cls = ObjCRuntime.getClass("NSTextField")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, attributedStringValue) as MemorySegment
}

// Class method: +[NSTextField textFieldWithString:]
fun NSTextField_textFieldWithString(stringValue: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("textFieldWithString:")
    val cls = ObjCRuntime.getClass("NSTextField")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, stringValue) as MemorySegment
}

// ── Category: NSTextFieldAttributedStringMethods on NSTextField ─────────────────────────────────────────

fun NSTextField.allowsEditingTextAttributes(): Boolean {
    val sel = ObjCRuntime.sel("allowsEditingTextAttributes")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSTextField.setAllowsEditingTextAttributes(allowsEditingTextAttributes: Boolean): Unit {
    val sel = ObjCRuntime.sel("setAllowsEditingTextAttributes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, allowsEditingTextAttributes)
}

fun NSTextField.importsGraphics(): Boolean {
    val sel = ObjCRuntime.sel("importsGraphics")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSTextField.setImportsGraphics(importsGraphics: Boolean): Unit {
    val sel = ObjCRuntime.sel("setImportsGraphics:")
    ObjCRuntime.msgSend(null, this.ptr, sel, importsGraphics)
}

// ── Category: NSDeprecated on NSTextField ─────────────────────────────────────────

fun NSTextField.setTitleWithMnemonic(stringWithAmpersand: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTitleWithMnemonic:")
    ObjCRuntime.msgSend(null, this.ptr, sel, stringWithAmpersand)
}

