package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSLayoutManager
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSLayoutManager(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSLayoutManager") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun replaceTextStorage(newTextStorage: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceTextStorage:")
        ObjCRuntime.msgSend(null, ptr, sel, newTextStorage)
    }
    
    open fun addTextContainer(container: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTextContainer:")
        ObjCRuntime.msgSend(null, ptr, sel, container)
    }
    
    open fun insertTextContainer_atIndex(container: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertTextContainer:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, container, index)
    }
    
    open fun removeTextContainerAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeTextContainerAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun textContainerChangedGeometry(container: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("textContainerChangedGeometry:")
        ObjCRuntime.msgSend(null, ptr, sel, container)
    }
    
    open fun textContainerChangedTextView(container: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("textContainerChangedTextView:")
        ObjCRuntime.msgSend(null, ptr, sel, container)
    }
    
    open fun invalidateGlyphsForCharacterRange_changeInLength_actualCharacterRange(charRange: MemorySegment, delta: Long, actualCharRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("invalidateGlyphsForCharacterRange:changeInLength:actualCharacterRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), delta, actualCharRange)
    }
    
    open fun invalidateLayoutForCharacterRange_actualCharacterRange(charRange: MemorySegment, actualCharRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("invalidateLayoutForCharacterRange:actualCharacterRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), actualCharRange)
    }
    
    open fun invalidateDisplayForCharacterRange(charRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("invalidateDisplayForCharacterRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun invalidateDisplayForGlyphRange(glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("invalidateDisplayForGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun processEditingForTextStorage_edited_range_changeInLength_invalidatedRange(textStorage: MemorySegment, editMask: MemorySegment, newCharRange: MemorySegment, delta: Long, invalidatedCharRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("processEditingForTextStorage:edited:range:changeInLength:invalidatedRange:")
        ObjCRuntime.msgSend(null, ptr, sel, textStorage, editMask, ObjCRuntime.ObjCStructArg(newCharRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), delta, ObjCRuntime.ObjCStructArg(invalidatedCharRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun ensureGlyphsForCharacterRange(charRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("ensureGlyphsForCharacterRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun ensureGlyphsForGlyphRange(glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("ensureGlyphsForGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun ensureLayoutForCharacterRange(charRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("ensureLayoutForCharacterRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun ensureLayoutForGlyphRange(glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("ensureLayoutForGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun ensureLayoutForTextContainer(container: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("ensureLayoutForTextContainer:")
        ObjCRuntime.msgSend(null, ptr, sel, container)
    }
    
    open fun ensureLayoutForBoundingRect_inTextContainer(bounds: MemorySegment, container: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("ensureLayoutForBoundingRect:inTextContainer:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(bounds, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), container)
    }
    
    open fun setGlyphs_properties_characterIndexes_font_forGlyphRange(glyphs: MemorySegment, props: MemorySegment, charIndexes: MemorySegment, aFont: MemorySegment, glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setGlyphs:properties:characterIndexes:font:forGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, glyphs, props, charIndexes, aFont, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun CGGlyphAtIndex_isValidIndex(glyphIndex: Long, isValidIndex: MemorySegment): Short {
        val sel = ObjCRuntime.sel("CGGlyphAtIndex:isValidIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, ptr, sel, glyphIndex, isValidIndex) as Short
    }
    
    open fun CGGlyphAtIndex(glyphIndex: Long): Short {
        val sel = ObjCRuntime.sel("CGGlyphAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, ptr, sel, glyphIndex) as Short
    }
    
    open fun isValidGlyphIndex(glyphIndex: Long): Boolean {
        val sel = ObjCRuntime.sel("isValidGlyphIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, glyphIndex) as Boolean
    }
    
    open fun propertyForGlyphAtIndex(glyphIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("propertyForGlyphAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, glyphIndex) as MemorySegment
    }
    
    open fun characterIndexForGlyphAtIndex(glyphIndex: Long): Long {
        val sel = ObjCRuntime.sel("characterIndexForGlyphAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, glyphIndex) as Long
    }
    
    open fun glyphIndexForCharacterAtIndex(charIndex: Long): Long {
        val sel = ObjCRuntime.sel("glyphIndexForCharacterAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, charIndex) as Long
    }
    
    open fun getGlyphsInRange_glyphs_properties_characterIndexes_bidiLevels(glyphRange: MemorySegment, glyphBuffer: MemorySegment, props: MemorySegment, charIndexBuffer: MemorySegment, bidiLevelBuffer: MemorySegment): Long {
        val sel = ObjCRuntime.sel("getGlyphsInRange:glyphs:properties:characterIndexes:bidiLevels:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), glyphBuffer, props, charIndexBuffer, bidiLevelBuffer) as Long
    }
    
    open fun setTextContainer_forGlyphRange(container: MemorySegment, glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTextContainer:forGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, container, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun setLineFragmentRect_forGlyphRange_usedRect(fragmentRect: MemorySegment, glyphRange: MemorySegment, usedRect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setLineFragmentRect:forGlyphRange:usedRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(fragmentRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(usedRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun setExtraLineFragmentRect_usedRect_textContainer(fragmentRect: MemorySegment, usedRect: MemorySegment, container: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setExtraLineFragmentRect:usedRect:textContainer:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(fragmentRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(usedRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), container)
    }
    
    open fun setLocation_forStartOfGlyphRange(location: MemorySegment, glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setLocation:forStartOfGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(location, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun setNotShownAttribute_forGlyphAtIndex(flag: Boolean, glyphIndex: Long): Unit {
        val sel = ObjCRuntime.sel("setNotShownAttribute:forGlyphAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, flag, glyphIndex)
    }
    
    open fun setDrawsOutsideLineFragment_forGlyphAtIndex(flag: Boolean, glyphIndex: Long): Unit {
        val sel = ObjCRuntime.sel("setDrawsOutsideLineFragment:forGlyphAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, flag, glyphIndex)
    }
    
    open fun setAttachmentSize_forGlyphRange(attachmentSize: MemorySegment, glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setAttachmentSize:forGlyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(attachmentSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun getFirstUnlaidCharacterIndex_glyphIndex(charIndex: MemorySegment, glyphIndex: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getFirstUnlaidCharacterIndex:glyphIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, charIndex, glyphIndex)
    }
    
    open fun firstUnlaidCharacterIndex(): Long {
        val sel = ObjCRuntime.sel("firstUnlaidCharacterIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun firstUnlaidGlyphIndex(): Long {
        val sel = ObjCRuntime.sel("firstUnlaidGlyphIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun textContainerForGlyphAtIndex_effectiveRange(glyphIndex: Long, effectiveGlyphRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("textContainerForGlyphAtIndex:effectiveRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, glyphIndex, effectiveGlyphRange) as MemorySegment
    }
    
    open fun textContainerForGlyphAtIndex_effectiveRange_withoutAdditionalLayout(glyphIndex: Long, effectiveGlyphRange: MemorySegment, flag: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("textContainerForGlyphAtIndex:effectiveRange:withoutAdditionalLayout:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, glyphIndex, effectiveGlyphRange, flag) as MemorySegment
    }
    
    open fun usedRectForTextContainer(container: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("usedRectForTextContainer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, container) as MemorySegment
    }
    
    open fun lineFragmentRectForGlyphAtIndex_effectiveRange(glyphIndex: Long, effectiveGlyphRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("lineFragmentRectForGlyphAtIndex:effectiveRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, glyphIndex, effectiveGlyphRange) as MemorySegment
    }
    
    open fun lineFragmentRectForGlyphAtIndex_effectiveRange_withoutAdditionalLayout(glyphIndex: Long, effectiveGlyphRange: MemorySegment, flag: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("lineFragmentRectForGlyphAtIndex:effectiveRange:withoutAdditionalLayout:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, glyphIndex, effectiveGlyphRange, flag) as MemorySegment
    }
    
    open fun lineFragmentUsedRectForGlyphAtIndex_effectiveRange(glyphIndex: Long, effectiveGlyphRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("lineFragmentUsedRectForGlyphAtIndex:effectiveRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, glyphIndex, effectiveGlyphRange) as MemorySegment
    }
    
    open fun lineFragmentUsedRectForGlyphAtIndex_effectiveRange_withoutAdditionalLayout(glyphIndex: Long, effectiveGlyphRange: MemorySegment, flag: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("lineFragmentUsedRectForGlyphAtIndex:effectiveRange:withoutAdditionalLayout:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, glyphIndex, effectiveGlyphRange, flag) as MemorySegment
    }
    
    open fun locationForGlyphAtIndex(glyphIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("locationForGlyphAtIndex:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel, glyphIndex) as MemorySegment
    }
    
    open fun notShownAttributeForGlyphAtIndex(glyphIndex: Long): Boolean {
        val sel = ObjCRuntime.sel("notShownAttributeForGlyphAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, glyphIndex) as Boolean
    }
    
    open fun drawsOutsideLineFragmentForGlyphAtIndex(glyphIndex: Long): Boolean {
        val sel = ObjCRuntime.sel("drawsOutsideLineFragmentForGlyphAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, glyphIndex) as Boolean
    }
    
    open fun attachmentSizeForGlyphAtIndex(glyphIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("attachmentSizeForGlyphAtIndex:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, glyphIndex) as MemorySegment
    }
    
    open fun truncatedGlyphRangeInLineFragmentForGlyphAtIndex(glyphIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("truncatedGlyphRangeInLineFragmentForGlyphAtIndex:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, glyphIndex) as MemorySegment
    }
    
    open fun glyphRangeForCharacterRange_actualCharacterRange(charRange: MemorySegment, actualCharRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("glyphRangeForCharacterRange:actualCharacterRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), actualCharRange) as MemorySegment
    }
    
    open fun characterRangeForGlyphRange_actualGlyphRange(glyphRange: MemorySegment, actualGlyphRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("characterRangeForGlyphRange:actualGlyphRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), actualGlyphRange) as MemorySegment
    }
    
    open fun glyphRangeForTextContainer(container: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("glyphRangeForTextContainer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, container) as MemorySegment
    }
    
    open fun rangeOfNominallySpacedGlyphsContainingIndex(glyphIndex: Long): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfNominallySpacedGlyphsContainingIndex:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, glyphIndex) as MemorySegment
    }
    
    open fun boundingRectForGlyphRange_inTextContainer(glyphRange: MemorySegment, container: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("boundingRectForGlyphRange:inTextContainer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), container) as MemorySegment
    }
    
    open fun glyphRangeForBoundingRect_inTextContainer(bounds: MemorySegment, container: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("glyphRangeForBoundingRect:inTextContainer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, ObjCRuntime.ObjCStructArg(bounds, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), container) as MemorySegment
    }
    
    open fun glyphRangeForBoundingRectWithoutAdditionalLayout_inTextContainer(bounds: MemorySegment, container: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("glyphRangeForBoundingRectWithoutAdditionalLayout:inTextContainer:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, ObjCRuntime.ObjCStructArg(bounds, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), container) as MemorySegment
    }
    
    open fun glyphIndexForPoint_inTextContainer_fractionOfDistanceThroughGlyph(point: MemorySegment, container: MemorySegment, partialFraction: MemorySegment): Long {
        val sel = ObjCRuntime.sel("glyphIndexForPoint:inTextContainer:fractionOfDistanceThroughGlyph:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), container, partialFraction) as Long
    }
    
    open fun glyphIndexForPoint_inTextContainer(point: MemorySegment, container: MemorySegment): Long {
        val sel = ObjCRuntime.sel("glyphIndexForPoint:inTextContainer:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), container) as Long
    }
    
    open fun fractionOfDistanceThroughGlyphForPoint_inTextContainer(point: MemorySegment, container: MemorySegment): Double {
        val sel = ObjCRuntime.sel("fractionOfDistanceThroughGlyphForPoint:inTextContainer:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), container) as Double
    }
    
    open fun characterIndexForPoint_inTextContainer_fractionOfDistanceBetweenInsertionPoints(point: MemorySegment, container: MemorySegment, partialFraction: MemorySegment): Long {
        val sel = ObjCRuntime.sel("characterIndexForPoint:inTextContainer:fractionOfDistanceBetweenInsertionPoints:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), container, partialFraction) as Long
    }
    
    open fun getLineFragmentInsertionPointsForCharacterAtIndex_alternatePositions_inDisplayOrder_positions_characterIndexes(charIndex: Long, aFlag: Boolean, dFlag: Boolean, positions: MemorySegment, charIndexes: MemorySegment): Long {
        val sel = ObjCRuntime.sel("getLineFragmentInsertionPointsForCharacterAtIndex:alternatePositions:inDisplayOrder:positions:characterIndexes:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, charIndex, aFlag, dFlag, positions, charIndexes) as Long
    }
    
    open fun enumerateLineFragmentsForGlyphRange_usingBlock(glyphRange: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateLineFragmentsForGlyphRange:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), block)
    }
    
    open fun enumerateEnclosingRectsForGlyphRange_withinSelectedGlyphRange_inTextContainer_usingBlock(glyphRange: MemorySegment, selectedRange: MemorySegment, textContainer: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateEnclosingRectsForGlyphRange:withinSelectedGlyphRange:inTextContainer:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(selectedRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), textContainer, block)
    }
    
    open fun drawBackgroundForGlyphRange_atPoint(glyphsToShow: MemorySegment, origin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawBackgroundForGlyphRange:atPoint:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphsToShow, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(origin, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun drawGlyphsForGlyphRange_atPoint(glyphsToShow: MemorySegment, origin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawGlyphsForGlyphRange:atPoint:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphsToShow, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(origin, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun showCGGlyphs_positions_count_font_textMatrix_attributes_inContext(glyphs: MemorySegment, positions: MemorySegment, glyphCount: Long, font: MemorySegment, textMatrix: MemorySegment, attributes: MemorySegment, CGContext: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("showCGGlyphs:positions:count:font:textMatrix:attributes:inContext:")
        ObjCRuntime.msgSend(null, ptr, sel, glyphs, positions, glyphCount, font, textMatrix, attributes, CGContext)
    }
    
    open fun fillBackgroundRectArray_count_forCharacterRange_color(rectArray: MemorySegment, rectCount: Long, charRange: MemorySegment, color: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("fillBackgroundRectArray:count:forCharacterRange:color:")
        ObjCRuntime.msgSend(null, ptr, sel, rectArray, rectCount, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), color)
    }
    
    open fun drawUnderlineForGlyphRange_underlineType_baselineOffset_lineFragmentRect_lineFragmentGlyphRange_containerOrigin(glyphRange: MemorySegment, underlineVal: MemorySegment, baselineOffset: Double, lineRect: MemorySegment, lineGlyphRange: MemorySegment, containerOrigin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawUnderlineForGlyphRange:underlineType:baselineOffset:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), underlineVal, baselineOffset, ObjCRuntime.ObjCStructArg(lineRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(lineGlyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(containerOrigin, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun underlineGlyphRange_underlineType_lineFragmentRect_lineFragmentGlyphRange_containerOrigin(glyphRange: MemorySegment, underlineVal: MemorySegment, lineRect: MemorySegment, lineGlyphRange: MemorySegment, containerOrigin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("underlineGlyphRange:underlineType:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), underlineVal, ObjCRuntime.ObjCStructArg(lineRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(lineGlyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(containerOrigin, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun drawStrikethroughForGlyphRange_strikethroughType_baselineOffset_lineFragmentRect_lineFragmentGlyphRange_containerOrigin(glyphRange: MemorySegment, strikethroughVal: MemorySegment, baselineOffset: Double, lineRect: MemorySegment, lineGlyphRange: MemorySegment, containerOrigin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawStrikethroughForGlyphRange:strikethroughType:baselineOffset:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), strikethroughVal, baselineOffset, ObjCRuntime.ObjCStructArg(lineRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(lineGlyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(containerOrigin, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun strikethroughGlyphRange_strikethroughType_lineFragmentRect_lineFragmentGlyphRange_containerOrigin(glyphRange: MemorySegment, strikethroughVal: MemorySegment, lineRect: MemorySegment, lineGlyphRange: MemorySegment, containerOrigin: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("strikethroughGlyphRange:strikethroughType:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), strikethroughVal, ObjCRuntime.ObjCStructArg(lineRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(lineGlyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), ObjCRuntime.ObjCStructArg(containerOrigin, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun showAttachmentCell_inRect_characterIndex(cell: MemorySegment, rect: MemorySegment, attachmentIndex: Long): Unit {
        val sel = ObjCRuntime.sel("showAttachmentCell:inRect:characterIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, cell, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), attachmentIndex)
    }
    
    open fun setLayoutRect_forTextBlock_glyphRange(rect: MemorySegment, block: MemorySegment, glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setLayoutRect:forTextBlock:glyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), block, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun setBoundsRect_forTextBlock_glyphRange(rect: MemorySegment, block: MemorySegment, glyphRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setBoundsRect:forTextBlock:glyphRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), block, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun layoutRectForTextBlock_glyphRange(block: MemorySegment, glyphRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("layoutRectForTextBlock:glyphRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, block, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
    }
    
    open fun boundsRectForTextBlock_glyphRange(block: MemorySegment, glyphRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("boundsRectForTextBlock:glyphRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, block, ObjCRuntime.ObjCStructArg(glyphRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
    }
    
    open fun layoutRectForTextBlock_atIndex_effectiveRange(block: MemorySegment, glyphIndex: Long, effectiveGlyphRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("layoutRectForTextBlock:atIndex:effectiveRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, block, glyphIndex, effectiveGlyphRange) as MemorySegment
    }
    
    open fun boundsRectForTextBlock_atIndex_effectiveRange(block: MemorySegment, glyphIndex: Long, effectiveGlyphRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("boundsRectForTextBlock:atIndex:effectiveRange:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, block, glyphIndex, effectiveGlyphRange) as MemorySegment
    }
    
    /** @return NSDictionary<NSAttributedStringKey,id> * */
    open fun temporaryAttributesAtCharacterIndex_effectiveRange(charIndex: Long, effectiveCharRange: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("temporaryAttributesAtCharacterIndex:effectiveRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, charIndex, effectiveCharRange) as MemorySegment
    }
    
    open fun setTemporaryAttributes_forCharacterRange(attrs: MemorySegment, charRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTemporaryAttributes:forCharacterRange:")
        ObjCRuntime.msgSend(null, ptr, sel, attrs, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun addTemporaryAttributes_forCharacterRange(attrs: MemorySegment, charRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTemporaryAttributes:forCharacterRange:")
        ObjCRuntime.msgSend(null, ptr, sel, attrs, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun removeTemporaryAttribute_forCharacterRange(attrName: MemorySegment, charRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeTemporaryAttribute:forCharacterRange:")
        ObjCRuntime.msgSend(null, ptr, sel, attrName, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun temporaryAttribute_atCharacterIndex_effectiveRange(attrName: MemorySegment, location: Long, range: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("temporaryAttribute:atCharacterIndex:effectiveRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, attrName, location, range) as MemorySegment
    }
    
    open fun temporaryAttribute_atCharacterIndex_longestEffectiveRange_inRange(attrName: MemorySegment, location: Long, range: MemorySegment, rangeLimit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("temporaryAttribute:atCharacterIndex:longestEffectiveRange:inRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, attrName, location, range, ObjCRuntime.ObjCStructArg(rangeLimit, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
    }
    
    /** @return NSDictionary<NSAttributedStringKey,id> * */
    open fun temporaryAttributesAtCharacterIndex_longestEffectiveRange_inRange(location: Long, range: MemorySegment, rangeLimit: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("temporaryAttributesAtCharacterIndex:longestEffectiveRange:inRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, location, range, ObjCRuntime.ObjCStructArg(rangeLimit, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
    }
    
    open fun addTemporaryAttribute_value_forCharacterRange(attrName: MemorySegment, value: MemorySegment, charRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTemporaryAttribute:value:forCharacterRange:")
        ObjCRuntime.msgSend(null, ptr, sel, attrName, value, ObjCRuntime.ObjCStructArg(charRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun defaultLineHeightForFont(theFont: MemorySegment): Double {
        val sel = ObjCRuntime.sel("defaultLineHeightForFont:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, theFont) as Double
    }
    
    open fun defaultBaselineOffsetForFont(theFont: MemorySegment): Double {
        val sel = ObjCRuntime.sel("defaultBaselineOffsetForFont:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, theFont) as Double
    }
    
    // @property textStorage
    open fun textStorage(): MemorySegment {
        val sel = ObjCRuntime.sel("textStorage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTextStorage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTextStorage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property textContainers
    /** @return NSArray<NSTextContainer *> * */
    open fun textContainers(): MemorySegment {
        val sel = ObjCRuntime.sel("textContainers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property delegate
    /** @return id<NSLayoutManagerDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsInvisibleCharacters
    open fun showsInvisibleCharacters(): Boolean {
        val sel = ObjCRuntime.sel("showsInvisibleCharacters")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsInvisibleCharacters(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsInvisibleCharacters:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsControlCharacters
    open fun showsControlCharacters(): Boolean {
        val sel = ObjCRuntime.sel("showsControlCharacters")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsControlCharacters(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsControlCharacters:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesDefaultHyphenation
    open fun usesDefaultHyphenation(): Boolean {
        val sel = ObjCRuntime.sel("usesDefaultHyphenation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesDefaultHyphenation(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesDefaultHyphenation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
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
    
    // @property allowsNonContiguousLayout
    open fun allowsNonContiguousLayout(): Boolean {
        val sel = ObjCRuntime.sel("allowsNonContiguousLayout")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsNonContiguousLayout(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsNonContiguousLayout:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasNonContiguousLayout
    open fun hasNonContiguousLayout(): Boolean {
        val sel = ObjCRuntime.sel("hasNonContiguousLayout")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property limitsLayoutForSuspiciousContents
    open fun limitsLayoutForSuspiciousContents(): Boolean {
        val sel = ObjCRuntime.sel("limitsLayoutForSuspiciousContents")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setLimitsLayoutForSuspiciousContents(value: Boolean) {
        val sel = ObjCRuntime.sel("setLimitsLayoutForSuspiciousContents:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backgroundLayoutEnabled
    open fun backgroundLayoutEnabled(): Boolean {
        val sel = ObjCRuntime.sel("backgroundLayoutEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setBackgroundLayoutEnabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setBackgroundLayoutEnabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property defaultAttachmentScaling
    open fun defaultAttachmentScaling(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultAttachmentScaling")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDefaultAttachmentScaling(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDefaultAttachmentScaling:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property typesetter
    open fun typesetter(): MemorySegment {
        val sel = ObjCRuntime.sel("typesetter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTypesetter(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTypesetter:")
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
    
    // @property numberOfGlyphs
    open fun numberOfGlyphs(): Long {
        val sel = ObjCRuntime.sel("numberOfGlyphs")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property extraLineFragmentRect
    open fun extraLineFragmentRect(): MemorySegment {
        val sel = ObjCRuntime.sel("extraLineFragmentRect")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property extraLineFragmentUsedRect
    open fun extraLineFragmentUsedRect(): MemorySegment {
        val sel = ObjCRuntime.sel("extraLineFragmentUsedRect")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property extraLineFragmentTextContainer
    open fun extraLineFragmentTextContainer(): MemorySegment {
        val sel = ObjCRuntime.sel("extraLineFragmentTextContainer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSTextViewSupport on NSLayoutManager ─────────────────────────────────────────

/** @return NSArray<NSRulerMarker *> * */
fun NSLayoutManager.rulerMarkersForTextView_paragraphStyle_ruler(view: MemorySegment, style: MemorySegment, ruler: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("rulerMarkersForTextView:paragraphStyle:ruler:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, view, style, ruler) as MemorySegment
}

fun NSLayoutManager.rulerAccessoryViewForTextView_paragraphStyle_ruler_enabled(view: MemorySegment, style: MemorySegment, ruler: MemorySegment, isEnabled: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("rulerAccessoryViewForTextView:paragraphStyle:ruler:enabled:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, view, style, ruler, isEnabled) as MemorySegment
}

fun NSLayoutManager.layoutManagerOwnsFirstResponderInWindow(window: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("layoutManagerOwnsFirstResponderInWindow:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, window) as Boolean
}

fun NSLayoutManager.firstTextView(): MemorySegment {
    val sel = ObjCRuntime.sel("firstTextView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLayoutManager.textViewForBeginningOfSelection(): MemorySegment {
    val sel = ObjCRuntime.sel("textViewForBeginningOfSelection")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSLayoutManagerDeprecated on NSLayoutManager ─────────────────────────────────────────

fun NSLayoutManager.glyphAtIndex_isValidIndex(glyphIndex: Long, isValidIndex: MemorySegment): Int {
    val sel = ObjCRuntime.sel("glyphAtIndex:isValidIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel, glyphIndex, isValidIndex) as Int
}

fun NSLayoutManager.glyphAtIndex(glyphIndex: Long): Int {
    val sel = ObjCRuntime.sel("glyphAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel, glyphIndex) as Int
}

fun NSLayoutManager.rectArrayForCharacterRange_withinSelectedCharacterRange_inTextContainer_rectCount(charRange: MemorySegment, selCharRange: MemorySegment, container: MemorySegment, rectCount: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("rectArrayForCharacterRange:withinSelectedCharacterRange:inTextContainer:rectCount:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, charRange, selCharRange, container, rectCount) as MemorySegment
}

fun NSLayoutManager.rectArrayForGlyphRange_withinSelectedGlyphRange_inTextContainer_rectCount(glyphRange: MemorySegment, selGlyphRange: MemorySegment, container: MemorySegment, rectCount: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("rectArrayForGlyphRange:withinSelectedGlyphRange:inTextContainer:rectCount:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, glyphRange, selGlyphRange, container, rectCount) as MemorySegment
}

fun NSLayoutManager.substituteFontForFont(originalFont: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("substituteFontForFont:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, originalFont) as MemorySegment
}

fun NSLayoutManager.insertGlyphs_length_forStartingGlyphAtIndex_characterIndex(glyphs: MemorySegment, length: Long, glyphIndex: Long, charIndex: Long): Unit {
    val sel = ObjCRuntime.sel("insertGlyphs:length:forStartingGlyphAtIndex:characterIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyphs, length, glyphIndex, charIndex)
}

fun NSLayoutManager.insertGlyph_atGlyphIndex_characterIndex(glyph: Int, glyphIndex: Long, charIndex: Long): Unit {
    val sel = ObjCRuntime.sel("insertGlyph:atGlyphIndex:characterIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyph, glyphIndex, charIndex)
}

fun NSLayoutManager.replaceGlyphAtIndex_withGlyph(glyphIndex: Long, newGlyph: Int): Unit {
    val sel = ObjCRuntime.sel("replaceGlyphAtIndex:withGlyph:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyphIndex, newGlyph)
}

fun NSLayoutManager.deleteGlyphsInRange(glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("deleteGlyphsInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyphRange)
}

fun NSLayoutManager.setCharacterIndex_forGlyphAtIndex(charIndex: Long, glyphIndex: Long): Unit {
    val sel = ObjCRuntime.sel("setCharacterIndex:forGlyphAtIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, charIndex, glyphIndex)
}

fun NSLayoutManager.setIntAttribute_value_forGlyphAtIndex(attributeTag: Long, `val`: Long, glyphIndex: Long): Unit {
    val sel = ObjCRuntime.sel("setIntAttribute:value:forGlyphAtIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attributeTag, `val`, glyphIndex)
}

fun NSLayoutManager.invalidateGlyphsOnLayoutInvalidationForGlyphRange(glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("invalidateGlyphsOnLayoutInvalidationForGlyphRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyphRange)
}

fun NSLayoutManager.intAttribute_forGlyphAtIndex(attributeTag: Long, glyphIndex: Long): Long {
    val sel = ObjCRuntime.sel("intAttribute:forGlyphAtIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, attributeTag, glyphIndex) as Long
}

fun NSLayoutManager.getGlyphsInRange_glyphs_characterIndexes_glyphInscriptions_elasticBits(glyphRange: MemorySegment, glyphBuffer: MemorySegment, charIndexBuffer: MemorySegment, inscribeBuffer: MemorySegment, elasticBuffer: MemorySegment): Long {
    val sel = ObjCRuntime.sel("getGlyphsInRange:glyphs:characterIndexes:glyphInscriptions:elasticBits:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, glyphRange, glyphBuffer, charIndexBuffer, inscribeBuffer, elasticBuffer) as Long
}

fun NSLayoutManager.getGlyphsInRange_glyphs_characterIndexes_glyphInscriptions_elasticBits_bidiLevels(glyphRange: MemorySegment, glyphBuffer: MemorySegment, charIndexBuffer: MemorySegment, inscribeBuffer: MemorySegment, elasticBuffer: MemorySegment, bidiLevelBuffer: MemorySegment): Long {
    val sel = ObjCRuntime.sel("getGlyphsInRange:glyphs:characterIndexes:glyphInscriptions:elasticBits:bidiLevels:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, glyphRange, glyphBuffer, charIndexBuffer, inscribeBuffer, elasticBuffer, bidiLevelBuffer) as Long
}

fun NSLayoutManager.getGlyphs_range(glyphArray: MemorySegment, glyphRange: MemorySegment): Long {
    val sel = ObjCRuntime.sel("getGlyphs:range:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, glyphArray, glyphRange) as Long
}

fun NSLayoutManager.invalidateLayoutForCharacterRange_isSoft_actualCharacterRange(charRange: MemorySegment, flag: Boolean, actualCharRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("invalidateLayoutForCharacterRange:isSoft:actualCharacterRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, charRange, flag, actualCharRange)
}

fun NSLayoutManager.textStorage_edited_range_changeInLength_invalidatedRange(str: MemorySegment, editedMask: Long, newCharRange: MemorySegment, delta: Long, invalidatedCharRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("textStorage:edited:range:changeInLength:invalidatedRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, str, editedMask, newCharRange, delta, invalidatedCharRange)
}

fun NSLayoutManager.setLocations_startingGlyphIndexes_count_forGlyphRange(locations: MemorySegment, glyphIndexes: MemorySegment, count: Long, glyphRange: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setLocations:startingGlyphIndexes:count:forGlyphRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, locations, glyphIndexes, count, glyphRange)
}

fun NSLayoutManager.showPackedGlyphs_length_glyphRange_atPoint_font_color_printingAdjustment(glyphs: MemorySegment, glyphLen: Long, glyphRange: MemorySegment, point: MemorySegment, font: MemorySegment, color: MemorySegment, printingAdjustment: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("showPackedGlyphs:length:glyphRange:atPoint:font:color:printingAdjustment:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyphs, glyphLen, glyphRange, point, font, color, printingAdjustment)
}

fun NSLayoutManager.showCGGlyphs_positions_count_font_matrix_attributes_inContext(glyphs: MemorySegment, positions: MemorySegment, glyphCount: Long, font: MemorySegment, textMatrix: MemorySegment, attributes: MemorySegment, graphicsContext: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("showCGGlyphs:positions:count:font:matrix:attributes:inContext:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyphs, positions, glyphCount, font, textMatrix, attributes, graphicsContext)
}

fun NSLayoutManager.usesScreenFonts(): Boolean {
    val sel = ObjCRuntime.sel("usesScreenFonts")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSLayoutManager.setUsesScreenFonts(usesScreenFonts: Boolean): Unit {
    val sel = ObjCRuntime.sel("setUsesScreenFonts:")
    ObjCRuntime.msgSend(null, this.ptr, sel, usesScreenFonts)
}

fun NSLayoutManager.hyphenationFactor(): Float {
    val sel = ObjCRuntime.sel("hyphenationFactor")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel) as Float
}

fun NSLayoutManager.setHyphenationFactor(hyphenationFactor: Float): Unit {
    val sel = ObjCRuntime.sel("setHyphenationFactor:")
    ObjCRuntime.msgSend(null, this.ptr, sel, hyphenationFactor)
}

// ── Category: NSGlyphGeneration on NSLayoutManager ─────────────────────────────────────────

fun NSLayoutManager.glyphGenerator(): MemorySegment {
    val sel = ObjCRuntime.sel("glyphGenerator")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSLayoutManager.setGlyphGenerator(glyphGenerator: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setGlyphGenerator:")
    ObjCRuntime.msgSend(null, this.ptr, sel, glyphGenerator)
}

