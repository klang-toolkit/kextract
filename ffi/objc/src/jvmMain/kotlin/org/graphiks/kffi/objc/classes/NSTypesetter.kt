package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTypesetter
 * Superclass: NSObject
 */
open class NSTypesetter(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTypesetter") }
        
        fun printingAdjustmentInLayoutManager_forNominallySpacedGlyphRange_packedGlyphs_count(layoutMgr: MemorySegment, nominallySpacedGlyphsRange: MemorySegment, packedGlyphs: MemorySegment, packedGlyphsCount: Long): MemorySegment {
            val sel = ObjCRuntime.sel("printingAdjustmentInLayoutManager:forNominallySpacedGlyphRange:packedGlyphs:count:")
            return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), _class, sel, layoutMgr, ObjCRuntime.ObjCStructArg(nominallySpacedGlyphsRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), packedGlyphs, packedGlyphsCount) as MemorySegment
        }
        
        fun sharedSystemTypesetterForBehavior(behavior: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("sharedSystemTypesetterForBehavior:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, behavior) as MemorySegment
        }
        
        fun sharedSystemTypesetter(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedSystemTypesetter")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun defaultTypesetterBehavior(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultTypesetterBehavior")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun substituteFontForFont(originalFont: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("substituteFontForFont:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, originalFont) as MemorySegment
    }
    
    open fun textTabForGlyphLocation_writingDirection_maxLocation(glyphLocation: Double, direction: MemorySegment, maxLocation: Double): MemorySegment {
        val sel = ObjCRuntime.sel("textTabForGlyphLocation:writingDirection:maxLocation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, glyphLocation, direction, maxLocation) as MemorySegment
    }
    
    open fun setParagraphGlyphRange_separatorGlyphRange(paragraphRange: MemorySegment, paragraphSeparatorRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setParagraphGlyphRange:separatorGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(paragraphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(paragraphSeparatorRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun layoutParagraphAtPoint(lineFragmentOrigin: MemorySegment): Long {
        val sel = ObjCRuntime.sel("layoutParagraphAtPoint:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, lineFragmentOrigin) as Long
    }
    
    open fun beginParagraph(): Unit {
        val sel = ObjCRuntime.sel("beginParagraph")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun endParagraph(): Unit {
        val sel = ObjCRuntime.sel("endParagraph")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun beginLineWithGlyphAtIndex(glyphIndex: Long): Unit {
        val sel = ObjCRuntime.sel("beginLineWithGlyphAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, glyphIndex)
    }
    
    open fun endLineWithGlyphRange(lineGlyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("endLineWithGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(lineGlyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun lineSpacingAfterGlyphAtIndex_withProposedLineFragmentRect(glyphIndex: Long, rect: MemorySegment): Double {
        val sel = ObjCRuntime.sel("lineSpacingAfterGlyphAtIndex:withProposedLineFragmentRect:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, glyphIndex, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as Double
    }
    
    open fun paragraphSpacingBeforeGlyphAtIndex_withProposedLineFragmentRect(glyphIndex: Long, rect: MemorySegment): Double {
        val sel = ObjCRuntime.sel("paragraphSpacingBeforeGlyphAtIndex:withProposedLineFragmentRect:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, glyphIndex, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as Double
    }
    
    open fun paragraphSpacingAfterGlyphAtIndex_withProposedLineFragmentRect(glyphIndex: Long, rect: MemorySegment): Double {
        val sel = ObjCRuntime.sel("paragraphSpacingAfterGlyphAtIndex:withProposedLineFragmentRect:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, glyphIndex, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as Double
    }
    
    open fun getLineFragmentRect_usedRect_forParagraphSeparatorGlyphRange_atProposedOrigin(lineFragmentRect: MemorySegment, lineFragmentUsedRect: MemorySegment, paragraphSeparatorGlyphRange: MemorySegment, lineOrigin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getLineFragmentRect:usedRect:forParagraphSeparatorGlyphRange:atProposedOrigin:")
        ObjCRuntime.msgSend(null, ptr, sel, lineFragmentRect, lineFragmentUsedRect, ObjCRuntime.ObjCStructArg(paragraphSeparatorGlyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(lineOrigin, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun setHardInvalidation_forGlyphRange(flag: Boolean, glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setHardInvalidation:forGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, flag, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun layoutGlyphsInLayoutManager_startingAtGlyphIndex_maxNumberOfLineFragments_nextGlyphIndex(layoutManager: MemorySegment, startGlyphIndex: Long, maxNumLines: Long, nextGlyph: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("layoutGlyphsInLayoutManager:startingAtGlyphIndex:maxNumberOfLineFragments:nextGlyphIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, layoutManager, startGlyphIndex, maxNumLines, nextGlyph)
    }
    
    open fun layoutCharactersInRange_forLayoutManager_maximumNumberOfLineFragments(characterRange: MemorySegment, layoutManager: MemorySegment, maxNumLines: Long): MemorySegment {
        val sel = ObjCRuntime.sel("layoutCharactersInRange:forLayoutManager:maximumNumberOfLineFragments:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, ObjCRuntime.ObjCStructArg(characterRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), layoutManager, maxNumLines) as MemorySegment
    }
    
    open fun baselineOffsetInLayoutManager_glyphIndex(layoutMgr: MemorySegment, glyphIndex: Long): Double {
        val sel = ObjCRuntime.sel("baselineOffsetInLayoutManager:glyphIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, layoutMgr, glyphIndex) as Double
    }
    
    // @property usesFontLeading
    open fun usesFontLeading(): Boolean {
        val sel = ObjCRuntime.sel("usesFontLeading")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesFontLeading(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesFontLeading:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property typesetterBehavior
    open fun typesetterBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("typesetterBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTypesetterBehavior(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTypesetterBehavior:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hyphenationFactor
    open fun hyphenationFactor(): Float {
        val sel = ObjCRuntime.sel("hyphenationFactor")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    open fun setHyphenationFactor(value: Float) {
        val sel = ObjCRuntime.sel("setHyphenationFactor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property lineFragmentPadding
    open fun lineFragmentPadding(): Double {
        val sel = ObjCRuntime.sel("lineFragmentPadding")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setLineFragmentPadding(value: Double) {
        val sel = ObjCRuntime.sel("setLineFragmentPadding:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property bidiProcessingEnabled
    open fun bidiProcessingEnabled(): Boolean {
        val sel = ObjCRuntime.sel("bidiProcessingEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setBidiProcessingEnabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setBidiProcessingEnabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property attributedString
    open fun attributedString(): MemorySegment {
        val sel = ObjCRuntime.sel("attributedString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttributedString(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttributedString:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property paragraphGlyphRange
    open fun paragraphGlyphRange(): MemorySegment {
        val sel = ObjCRuntime.sel("paragraphGlyphRange")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property paragraphSeparatorGlyphRange
    open fun paragraphSeparatorGlyphRange(): MemorySegment {
        val sel = ObjCRuntime.sel("paragraphSeparatorGlyphRange")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property paragraphCharacterRange
    open fun paragraphCharacterRange(): MemorySegment {
        val sel = ObjCRuntime.sel("paragraphCharacterRange")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property paragraphSeparatorCharacterRange
    open fun paragraphSeparatorCharacterRange(): MemorySegment {
        val sel = ObjCRuntime.sel("paragraphSeparatorCharacterRange")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property attributesForExtraLineFragment
    /** @return NSDictionary<NSAttributedStringKey,id> * */
    open fun attributesForExtraLineFragment(): MemorySegment {
        val sel = ObjCRuntime.sel("attributesForExtraLineFragment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property layoutManager
    open fun layoutManager(): MemorySegment {
        val sel = ObjCRuntime.sel("layoutManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property textContainers
    /** @return NSArray<NSTextContainer *> * */
    open fun textContainers(): MemorySegment {
        val sel = ObjCRuntime.sel("textContainers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property currentTextContainer
    open fun currentTextContainer(): MemorySegment {
        val sel = ObjCRuntime.sel("currentTextContainer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property currentParagraphStyle
    open fun currentParagraphStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("currentParagraphStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sharedSystemTypesetter
    open fun sharedSystemTypesetter(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedSystemTypesetter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property defaultTypesetterBehavior
    open fun defaultTypesetterBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultTypesetterBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSLayoutPhaseInterface on NSTypesetter ─────────────────────────────────────────

fun NSTypesetter.willSetLineFragmentRect_forGlyphRange_usedRect_baselineOffset(lineRect: MemorySegment, glyphRange: MemorySegment, usedRect: MemorySegment, baselineOffset: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("willSetLineFragmentRect:forGlyphRange:usedRect:baselineOffset:")
    ObjCRuntime.msgSend(null, this.ptr, sel, lineRect, glyphRange, usedRect, baselineOffset)
}

fun NSTypesetter.shouldBreakLineByWordBeforeCharacterAtIndex(charIndex: Long): Boolean {
    val sel = ObjCRuntime.sel("shouldBreakLineByWordBeforeCharacterAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, charIndex) as Boolean
}

fun NSTypesetter.shouldBreakLineByHyphenatingBeforeCharacterAtIndex(charIndex: Long): Boolean {
    val sel = ObjCRuntime.sel("shouldBreakLineByHyphenatingBeforeCharacterAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, charIndex) as Boolean
}

fun NSTypesetter.hyphenationFactorForGlyphAtIndex(glyphIndex: Long): Float {
    val sel = ObjCRuntime.sel("hyphenationFactorForGlyphAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel, glyphIndex) as Float
}

fun NSTypesetter.hyphenCharacterForGlyphAtIndex(glyphIndex: Long): Int {
    val sel = ObjCRuntime.sel("hyphenCharacterForGlyphAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel, glyphIndex) as Int
}

fun NSTypesetter.boundingBoxForControlGlyphAtIndex_forTextContainer_proposedLineFragment_glyphPosition_characterIndex(glyphIndex: Long, textContainer: MemorySegment, proposedRect: MemorySegment, glyphPosition: MemorySegment, charIndex: Long): MemorySegment {
    val sel = ObjCRuntime.sel("boundingBoxForControlGlyphAtIndex:forTextContainer:proposedLineFragment:glyphPosition:characterIndex:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, glyphIndex, textContainer, proposedRect, glyphPosition, charIndex) as MemorySegment
}

// ── Category: NSGlyphStorageInterface on NSTypesetter ─────────────────────────────────────────

fun NSTypesetter.characterRangeForGlyphRange_actualGlyphRange(glyphRange: MemorySegment, actualGlyphRange: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("characterRangeForGlyphRange:actualGlyphRange:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel, glyphRange, actualGlyphRange) as MemorySegment
}

fun NSTypesetter.glyphRangeForCharacterRange_actualCharacterRange(charRange: MemorySegment, actualCharRange: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("glyphRangeForCharacterRange:actualCharacterRange:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel, charRange, actualCharRange) as MemorySegment
}

fun NSTypesetter.getLineFragmentRect_usedRect_remainingRect_forStartingGlyphAtIndex_proposedRect_lineSpacing_paragraphSpacingBefore_paragraphSpacingAfter(lineFragmentRect: MemorySegment, lineFragmentUsedRect: MemorySegment, remainingRect: MemorySegment, startingGlyphIndex: Long, proposedRect: MemorySegment, lineSpacing: Double, paragraphSpacingBefore: Double, paragraphSpacingAfter: Double): Unit {
    val sel = ObjCRuntime.sel("getLineFragmentRect:usedRect:remainingRect:forStartingGlyphAtIndex:proposedRect:lineSpacing:paragraphSpacingBefore:paragraphSpacingAfter:")
    ObjCRuntime.msgSend(null, this.ptr, sel, lineFragmentRect, lineFragmentUsedRect, remainingRect, startingGlyphIndex, proposedRect, lineSpacing, paragraphSpacingBefore, paragraphSpacingAfter)
}

fun NSTypesetter.setLineFragmentRect_forGlyphRange_usedRect_baselineOffset(fragmentRect: MemorySegment, glyphRange: MemorySegment, usedRect: MemorySegment, baselineOffset: Double): Unit {
    val sel = ObjCRuntime.sel("setLineFragmentRect:forGlyphRange:usedRect:baselineOffset:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fragmentRect, glyphRange, usedRect, baselineOffset)
}

fun NSTypesetter.setNotShownAttribute_forGlyphRange(flag: Boolean, glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setNotShownAttribute:forGlyphRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag, glyphRange)
}

fun NSTypesetter.setDrawsOutsideLineFragment_forGlyphRange(flag: Boolean, glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setDrawsOutsideLineFragment:forGlyphRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag, glyphRange)
}

fun NSTypesetter.setLocation_withAdvancements_forStartOfGlyphRange(location: MemorySegment, advancements: MemorySegment, glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setLocation:withAdvancements:forStartOfGlyphRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, location, advancements, glyphRange)
}

fun NSTypesetter.setAttachmentSize_forGlyphRange(attachmentSize: MemorySegment, glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAttachmentSize:forGlyphRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attachmentSize, glyphRange)
}

fun NSTypesetter.setBidiLevels_forGlyphRange(levels: MemorySegment, glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setBidiLevels:forGlyphRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, levels, glyphRange)
}

// ── Category: NSTypesetter_Deprecated on NSTypesetter ─────────────────────────────────────────

fun NSTypesetter.actionForControlCharacterAtIndex(charIndex: Long): MemorySegment {
    val sel = ObjCRuntime.sel("actionForControlCharacterAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, charIndex) as MemorySegment
}

fun NSTypesetter.getGlyphsInRange_glyphs_characterIndexes_glyphInscriptions_elasticBits_bidiLevels(glyphsRange: MemorySegment, glyphBuffer: MemorySegment, charIndexBuffer: MemorySegment, inscribeBuffer: MemorySegment, elasticBuffer: MemorySegment, bidiLevelBuffer: MemorySegment): Long {
    val sel = ObjCRuntime.sel("getGlyphsInRange:glyphs:characterIndexes:glyphInscriptions:elasticBits:bidiLevels:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, glyphsRange, glyphBuffer, charIndexBuffer, inscribeBuffer, elasticBuffer, bidiLevelBuffer) as Long
}

fun NSTypesetter.substituteGlyphsInRange_withGlyphs(glyphRange: MemorySegment, glyphs: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("substituteGlyphsInRange:withGlyphs:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyphRange, glyphs)
}

fun NSTypesetter.insertGlyph_atGlyphIndex_characterIndex(glyph: Int, glyphIndex: Long, characterIndex: Long): Unit {
    val sel = ObjCRuntime.sel("insertGlyph:atGlyphIndex:characterIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyph, glyphIndex, characterIndex)
}

fun NSTypesetter.deleteGlyphsInRange(glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("deleteGlyphsInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyphRange)
}

