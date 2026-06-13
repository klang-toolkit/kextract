package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMutableParagraphStyle
 * Superclass: NSParagraphStyle
 */
open class NSMutableParagraphStyle(override val ptr: MemorySegment) : NSParagraphStyle(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMutableParagraphStyle") }
        
    }
    
    open fun addTabStop(anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTabStop:")
        ObjCRuntime.msgSend(null, ptr, sel, anObject)
    }
    
    open fun removeTabStop(anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeTabStop:")
        ObjCRuntime.msgSend(null, ptr, sel, anObject)
    }
    
    open fun setParagraphStyle(obj: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setParagraphStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, obj)
    }
    
    // @property lineSpacing
    override fun lineSpacing(): Double {
        val sel = ObjCRuntime.sel("lineSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setLineSpacing(value: Double) {
        val sel = ObjCRuntime.sel("setLineSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property paragraphSpacing
    override fun paragraphSpacing(): Double {
        val sel = ObjCRuntime.sel("paragraphSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setParagraphSpacing(value: Double) {
        val sel = ObjCRuntime.sel("setParagraphSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property firstLineHeadIndent
    override fun firstLineHeadIndent(): Double {
        val sel = ObjCRuntime.sel("firstLineHeadIndent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setFirstLineHeadIndent(value: Double) {
        val sel = ObjCRuntime.sel("setFirstLineHeadIndent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property headIndent
    override fun headIndent(): Double {
        val sel = ObjCRuntime.sel("headIndent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setHeadIndent(value: Double) {
        val sel = ObjCRuntime.sel("setHeadIndent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tailIndent
    override fun tailIndent(): Double {
        val sel = ObjCRuntime.sel("tailIndent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setTailIndent(value: Double) {
        val sel = ObjCRuntime.sel("setTailIndent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property lineBreakMode
    override fun lineBreakMode(): MemorySegment {
        val sel = ObjCRuntime.sel("lineBreakMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLineBreakMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLineBreakMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minimumLineHeight
    override fun minimumLineHeight(): Double {
        val sel = ObjCRuntime.sel("minimumLineHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinimumLineHeight(value: Double) {
        val sel = ObjCRuntime.sel("setMinimumLineHeight:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maximumLineHeight
    override fun maximumLineHeight(): Double {
        val sel = ObjCRuntime.sel("maximumLineHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMaximumLineHeight(value: Double) {
        val sel = ObjCRuntime.sel("setMaximumLineHeight:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property baseWritingDirection
    override fun baseWritingDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("baseWritingDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBaseWritingDirection(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBaseWritingDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property lineHeightMultiple
    override fun lineHeightMultiple(): Double {
        val sel = ObjCRuntime.sel("lineHeightMultiple")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setLineHeightMultiple(value: Double) {
        val sel = ObjCRuntime.sel("setLineHeightMultiple:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property paragraphSpacingBefore
    override fun paragraphSpacingBefore(): Double {
        val sel = ObjCRuntime.sel("paragraphSpacingBefore")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setParagraphSpacingBefore(value: Double) {
        val sel = ObjCRuntime.sel("setParagraphSpacingBefore:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hyphenationFactor
    override fun hyphenationFactor(): Float {
        val sel = ObjCRuntime.sel("hyphenationFactor")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    open fun setHyphenationFactor(value: Float) {
        val sel = ObjCRuntime.sel("setHyphenationFactor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesDefaultHyphenation
    override fun usesDefaultHyphenation(): Boolean {
        val sel = ObjCRuntime.sel("usesDefaultHyphenation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesDefaultHyphenation(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesDefaultHyphenation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tabStops
    /** @return NSArray<NSTextTab *> * */
    override fun tabStops(): MemorySegment {
        val sel = ObjCRuntime.sel("tabStops")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTabStops(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTabStops:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property defaultTabInterval
    override fun defaultTabInterval(): Double {
        val sel = ObjCRuntime.sel("defaultTabInterval")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setDefaultTabInterval(value: Double) {
        val sel = ObjCRuntime.sel("setDefaultTabInterval:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsDefaultTighteningForTruncation
    override fun allowsDefaultTighteningForTruncation(): Boolean {
        val sel = ObjCRuntime.sel("allowsDefaultTighteningForTruncation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsDefaultTighteningForTruncation(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsDefaultTighteningForTruncation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property lineBreakStrategy
    override fun lineBreakStrategy(): MemorySegment {
        val sel = ObjCRuntime.sel("lineBreakStrategy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLineBreakStrategy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLineBreakStrategy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property textLists
    /** @return NSArray<NSTextList *> * */
    override fun textLists(): MemorySegment {
        val sel = ObjCRuntime.sel("textLists")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTextLists(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTextLists:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category:  on NSMutableParagraphStyle ─────────────────────────────────────────

fun NSMutableParagraphStyle.alignment(): MemorySegment {
    val sel = ObjCRuntime.sel("alignment")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSMutableParagraphStyle.setAlignment(alignment: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAlignment:")
    ObjCRuntime.msgSend(null, this.ptr, sel, alignment)
}

fun NSMutableParagraphStyle.tighteningFactorForTruncation(): Float {
    val sel = ObjCRuntime.sel("tighteningFactorForTruncation")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel) as Float
}

fun NSMutableParagraphStyle.setTighteningFactorForTruncation(tighteningFactorForTruncation: Float): Unit {
    val sel = ObjCRuntime.sel("setTighteningFactorForTruncation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, tighteningFactorForTruncation)
}

/** @return NSArray<__kindof NSTextBlock *> * */
fun NSMutableParagraphStyle.textBlocks(): MemorySegment {
    val sel = ObjCRuntime.sel("textBlocks")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSMutableParagraphStyle.setTextBlocks(textBlocks: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTextBlocks:")
    ObjCRuntime.msgSend(null, this.ptr, sel, textBlocks)
}

fun NSMutableParagraphStyle.headerLevel(): Long {
    val sel = ObjCRuntime.sel("headerLevel")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSMutableParagraphStyle.setHeaderLevel(headerLevel: Long): Unit {
    val sel = ObjCRuntime.sel("setHeaderLevel:")
    ObjCRuntime.msgSend(null, this.ptr, sel, headerLevel)
}

