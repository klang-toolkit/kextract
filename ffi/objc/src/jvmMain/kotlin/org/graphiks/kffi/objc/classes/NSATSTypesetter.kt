package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSATSTypesetter
 * Superclass: NSTypesetter
 */
open class NSATSTypesetter(override val ptr: MemorySegment) : NSTypesetter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSATSTypesetter") }
        
        fun sharedTypesetter(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedTypesetter")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property sharedTypesetter
    open fun sharedTypesetter(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedTypesetter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSPantherCompatibility on NSATSTypesetter ─────────────────────────────────────────

fun NSATSTypesetter.lineFragmentRectForProposedRect_remainingRect(proposedRect: MemorySegment, remainingRect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("lineFragmentRectForProposedRect:remainingRect:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, proposedRect, remainingRect) as MemorySegment
}

// ── Category: NSPrimitiveInterface on NSATSTypesetter ─────────────────────────────────────────

fun NSATSTypesetter.substituteFontForFont(originalFont: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("substituteFontForFont:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, originalFont) as MemorySegment
}

fun NSATSTypesetter.textTabForGlyphLocation_writingDirection_maxLocation(glyphLocation: Double, direction: MemorySegment, maxLocation: Double): MemorySegment {
    val sel = ObjCRuntime.sel("textTabForGlyphLocation:writingDirection:maxLocation:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, glyphLocation, direction, maxLocation) as MemorySegment
}

fun NSATSTypesetter.setParagraphGlyphRange_separatorGlyphRange(paragraphRange: MemorySegment, paragraphSeparatorRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setParagraphGlyphRange:separatorGlyphRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, paragraphRange, paragraphSeparatorRange)
}

fun NSATSTypesetter.layoutParagraphAtPoint(lineFragmentOrigin: MemorySegment): Long {
    val sel = ObjCRuntime.sel("layoutParagraphAtPoint:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, lineFragmentOrigin) as Long
}

fun NSATSTypesetter.lineSpacingAfterGlyphAtIndex_withProposedLineFragmentRect(glyphIndex: Long, rect: MemorySegment): Double {
    val sel = ObjCRuntime.sel("lineSpacingAfterGlyphAtIndex:withProposedLineFragmentRect:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel, glyphIndex, rect) as Double
}

fun NSATSTypesetter.paragraphSpacingBeforeGlyphAtIndex_withProposedLineFragmentRect(glyphIndex: Long, rect: MemorySegment): Double {
    val sel = ObjCRuntime.sel("paragraphSpacingBeforeGlyphAtIndex:withProposedLineFragmentRect:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel, glyphIndex, rect) as Double
}

fun NSATSTypesetter.paragraphSpacingAfterGlyphAtIndex_withProposedLineFragmentRect(glyphIndex: Long, rect: MemorySegment): Double {
    val sel = ObjCRuntime.sel("paragraphSpacingAfterGlyphAtIndex:withProposedLineFragmentRect:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel, glyphIndex, rect) as Double
}

fun NSATSTypesetter.setHardInvalidation_forGlyphRange(flag: Boolean, glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setHardInvalidation:forGlyphRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag, glyphRange)
}

fun NSATSTypesetter.getLineFragmentRect_usedRect_forParagraphSeparatorGlyphRange_atProposedOrigin(lineFragmentRect: MemorySegment, lineFragmentUsedRect: MemorySegment, paragraphSeparatorGlyphRange: MemorySegment, lineOrigin: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getLineFragmentRect:usedRect:forParagraphSeparatorGlyphRange:atProposedOrigin:")
    ObjCRuntime.msgSend(null, this.ptr, sel, lineFragmentRect, lineFragmentUsedRect, paragraphSeparatorGlyphRange, lineOrigin)
}

fun NSATSTypesetter.usesFontLeading(): Boolean {
    val sel = ObjCRuntime.sel("usesFontLeading")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSATSTypesetter.setUsesFontLeading(usesFontLeading: Boolean): Unit {
    val sel = ObjCRuntime.sel("setUsesFontLeading:")
    ObjCRuntime.msgSend(null, this.ptr, sel, usesFontLeading)
}

fun NSATSTypesetter.typesetterBehavior(): MemorySegment {
    val sel = ObjCRuntime.sel("typesetterBehavior")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSATSTypesetter.setTypesetterBehavior(typesetterBehavior: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTypesetterBehavior:")
    ObjCRuntime.msgSend(null, this.ptr, sel, typesetterBehavior)
}

fun NSATSTypesetter.hyphenationFactor(): Float {
    val sel = ObjCRuntime.sel("hyphenationFactor")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel) as Float
}

fun NSATSTypesetter.setHyphenationFactor(hyphenationFactor: Float): Unit {
    val sel = ObjCRuntime.sel("setHyphenationFactor:")
    ObjCRuntime.msgSend(null, this.ptr, sel, hyphenationFactor)
}

fun NSATSTypesetter.lineFragmentPadding(): Double {
    val sel = ObjCRuntime.sel("lineFragmentPadding")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSATSTypesetter.setLineFragmentPadding(lineFragmentPadding: Double): Unit {
    val sel = ObjCRuntime.sel("setLineFragmentPadding:")
    ObjCRuntime.msgSend(null, this.ptr, sel, lineFragmentPadding)
}

fun NSATSTypesetter.bidiProcessingEnabled(): Boolean {
    val sel = ObjCRuntime.sel("bidiProcessingEnabled")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSATSTypesetter.setBidiProcessingEnabled(bidiProcessingEnabled: Boolean): Unit {
    val sel = ObjCRuntime.sel("setBidiProcessingEnabled:")
    ObjCRuntime.msgSend(null, this.ptr, sel, bidiProcessingEnabled)
}

fun NSATSTypesetter.attributedString(): MemorySegment {
    val sel = ObjCRuntime.sel("attributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSATSTypesetter.setAttributedString(attributedString: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAttributedString:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attributedString)
}

fun NSATSTypesetter.paragraphGlyphRange(): MemorySegment {
    val sel = ObjCRuntime.sel("paragraphGlyphRange")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel) as MemorySegment
}

fun NSATSTypesetter.paragraphSeparatorGlyphRange(): MemorySegment {
    val sel = ObjCRuntime.sel("paragraphSeparatorGlyphRange")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel) as MemorySegment
}

fun NSATSTypesetter.layoutManager(): MemorySegment {
    val sel = ObjCRuntime.sel("layoutManager")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSATSTypesetter.currentTextContainer(): MemorySegment {
    val sel = ObjCRuntime.sel("currentTextContainer")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSLayoutPhaseInterface on NSATSTypesetter ─────────────────────────────────────────

fun NSATSTypesetter.willSetLineFragmentRect_forGlyphRange_usedRect_baselineOffset(lineRect: MemorySegment, glyphRange: MemorySegment, usedRect: MemorySegment, baselineOffset: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("willSetLineFragmentRect:forGlyphRange:usedRect:baselineOffset:")
    ObjCRuntime.msgSend(null, this.ptr, sel, lineRect, glyphRange, usedRect, baselineOffset)
}

fun NSATSTypesetter.shouldBreakLineByWordBeforeCharacterAtIndex(charIndex: Long): Boolean {
    val sel = ObjCRuntime.sel("shouldBreakLineByWordBeforeCharacterAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, charIndex) as Boolean
}

fun NSATSTypesetter.shouldBreakLineByHyphenatingBeforeCharacterAtIndex(charIndex: Long): Boolean {
    val sel = ObjCRuntime.sel("shouldBreakLineByHyphenatingBeforeCharacterAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, charIndex) as Boolean
}

fun NSATSTypesetter.hyphenationFactorForGlyphAtIndex(glyphIndex: Long): Float {
    val sel = ObjCRuntime.sel("hyphenationFactorForGlyphAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel, glyphIndex) as Float
}

fun NSATSTypesetter.hyphenCharacterForGlyphAtIndex(glyphIndex: Long): Int {
    val sel = ObjCRuntime.sel("hyphenCharacterForGlyphAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel, glyphIndex) as Int
}

fun NSATSTypesetter.boundingBoxForControlGlyphAtIndex_forTextContainer_proposedLineFragment_glyphPosition_characterIndex(glyphIndex: Long, textContainer: MemorySegment, proposedRect: MemorySegment, glyphPosition: MemorySegment, charIndex: Long): MemorySegment {
    val sel = ObjCRuntime.sel("boundingBoxForControlGlyphAtIndex:forTextContainer:proposedLineFragment:glyphPosition:characterIndex:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, glyphIndex, textContainer, proposedRect, glyphPosition, charIndex) as MemorySegment
}

// ── Category: NSGlyphStorageInterface on NSATSTypesetter ─────────────────────────────────────────

fun NSATSTypesetter.getGlyphsInRange_glyphs_characterIndexes_glyphInscriptions_elasticBits(glyphsRange: MemorySegment, glyphBuffer: MemorySegment, charIndexBuffer: MemorySegment, inscribeBuffer: MemorySegment, elasticBuffer: MemorySegment): Long {
    val sel = ObjCRuntime.sel("getGlyphsInRange:glyphs:characterIndexes:glyphInscriptions:elasticBits:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, glyphsRange, glyphBuffer, charIndexBuffer, inscribeBuffer, elasticBuffer) as Long
}

