package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAttributedString
 * Superclass: NSObject
 * Protocols: NSCopying, NSMutableCopying, NSSecureCoding
 */
open class NSAttributedString(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAttributedString") }
        
    }
    
    /** @return NSDictionary<NSAttributedStringKey,id> * */
    open fun attributesAtIndex_effectiveRange(location: Long, range: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("attributesAtIndex:effectiveRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, location, range) as MemorySegment
    }
    
    // @property string
    open fun string(): MemorySegment {
        val sel = ObjCRuntime.sel("string")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun stringAsString(): String = ObjCRuntime.toJavaString(string())
    
}

// ── Category: NSExtendedAttributedString on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.attribute_atIndex_effectiveRange(attrName: MemorySegment, location: Long, range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("attribute:atIndex:effectiveRange:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, attrName, location, range) as MemorySegment
}

fun NSAttributedString.attributedSubstringFromRange(range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("attributedSubstringFromRange:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range) as MemorySegment
}

/** @return NSDictionary<NSAttributedStringKey,id> * */
fun NSAttributedString.attributesAtIndex_longestEffectiveRange_inRange(location: Long, range: MemorySegment, rangeLimit: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("attributesAtIndex:longestEffectiveRange:inRange:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, location, range, rangeLimit) as MemorySegment
}

fun NSAttributedString.attribute_atIndex_longestEffectiveRange_inRange(attrName: MemorySegment, location: Long, range: MemorySegment, rangeLimit: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("attribute:atIndex:longestEffectiveRange:inRange:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, attrName, location, range, rangeLimit) as MemorySegment
}

fun NSAttributedString.isEqualToAttributedString(other: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isEqualToAttributedString:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, other) as Boolean
}

fun NSAttributedString.initWithString(str: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithString:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, str) as MemorySegment
}

fun NSAttributedString.initWithString_attributes(str: MemorySegment, attrs: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithString:attributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, str, attrs) as MemorySegment
}

fun NSAttributedString.initWithAttributedString(attrStr: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithAttributedString:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, attrStr) as MemorySegment
}

fun NSAttributedString.enumerateAttributesInRange_options_usingBlock(enumerationRange: MemorySegment, opts: MemorySegment, block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateAttributesInRange:options:usingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, enumerationRange, opts, block)
}

fun NSAttributedString.enumerateAttribute_inRange_options_usingBlock(attrName: MemorySegment, enumerationRange: MemorySegment, opts: MemorySegment, block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateAttribute:inRange:options:usingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attrName, enumerationRange, opts, block)
}

fun NSAttributedString.length(): Long {
    val sel = ObjCRuntime.sel("length")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

// ── Category: NSAttributedStringCreateFromMarkdown on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.initWithContentsOfMarkdownFileAtURL_options_baseURL_error(markdownFile: MemorySegment, options: MemorySegment, baseURL: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfMarkdownFileAtURL:options:baseURL:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, markdownFile, options, baseURL, error) as MemorySegment
}

fun NSAttributedString.initWithMarkdown_options_baseURL_error(markdown: MemorySegment, options: MemorySegment, baseURL: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithMarkdown:options:baseURL:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, markdown, options, baseURL, error) as MemorySegment
}

fun NSAttributedString.initWithMarkdownString_options_baseURL_error(markdownString: MemorySegment, options: MemorySegment, baseURL: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithMarkdownString:options:baseURL:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, markdownString, options, baseURL, error) as MemorySegment
}

// ── Category: NSAttributedStringFormatting on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.initWithFormat_options_locale(format: MemorySegment, options: MemorySegment, locale: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithFormat:options:locale:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, format, options, locale) as MemorySegment
}

fun NSAttributedString.initWithFormat_options_locale_arguments(format: MemorySegment, options: MemorySegment, locale: MemorySegment, arguments: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithFormat:options:locale:arguments:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, format, options, locale, arguments) as MemorySegment
}

fun NSAttributedString.initWithFormat_options_locale_context(format: MemorySegment, options: MemorySegment, locale: MemorySegment, context: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithFormat:options:locale:context:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, format, options, locale, context) as MemorySegment
}

fun NSAttributedString.initWithFormat_options_locale_context_arguments(format: MemorySegment, options: MemorySegment, locale: MemorySegment, context: MemorySegment, arguments: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithFormat:options:locale:context:arguments:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, format, options, locale, context, arguments) as MemorySegment
}

// Class method: +[NSAttributedString localizedAttributedStringWithFormat:]
fun NSAttributedString_localizedAttributedStringWithFormat(format: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedAttributedStringWithFormat:")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, format) as MemorySegment
}

// Class method: +[NSAttributedString localizedAttributedStringWithFormat:options:]
fun NSAttributedString_localizedAttributedStringWithFormat_options(format: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedAttributedStringWithFormat:options:")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, format, options) as MemorySegment
}

// Class method: +[NSAttributedString localizedAttributedStringWithFormat:context:]
fun NSAttributedString_localizedAttributedStringWithFormat_context(format: MemorySegment, context: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedAttributedStringWithFormat:context:")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, format, context) as MemorySegment
}

// Class method: +[NSAttributedString localizedAttributedStringWithFormat:options:context:]
fun NSAttributedString_localizedAttributedStringWithFormat_options_context(format: MemorySegment, options: MemorySegment, context: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedAttributedStringWithFormat:options:context:")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, format, options, context) as MemorySegment
}

// ── Category: NSMorphology on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.attributedStringByInflectingString(): MemorySegment {
    val sel = ObjCRuntime.sel("attributedStringByInflectingString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSAttributedStringDocumentFormats on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.initWithURL_options_documentAttributes_error(url: MemorySegment, options: MemorySegment, dict: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithURL:options:documentAttributes:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, options, dict, error) as MemorySegment
}

fun NSAttributedString.initWithData_options_documentAttributes_error(`data`: MemorySegment, options: MemorySegment, dict: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithData:options:documentAttributes:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`, options, dict, error) as MemorySegment
}

fun NSAttributedString.dataFromRange_documentAttributes_error(range: MemorySegment, dict: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataFromRange:documentAttributes:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range, dict, error) as MemorySegment
}

fun NSAttributedString.fileWrapperFromRange_documentAttributes_error(range: MemorySegment, dict: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileWrapperFromRange:documentAttributes:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range, dict, error) as MemorySegment
}

// ── Category: NSAttributedStringKitAdditions on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.containsAttachmentsInRange(range: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("containsAttachmentsInRange:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, range) as Boolean
}

fun NSAttributedString.prefersRTFDInRange(range: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("prefersRTFDInRange:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, range) as Boolean
}

// ── Category: NSAttributedStringAppKitDocumentFormats on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.initWithRTF_documentAttributes(`data`: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithRTF:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`, dict) as MemorySegment
}

fun NSAttributedString.initWithRTFD_documentAttributes(`data`: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithRTFD:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`, dict) as MemorySegment
}

fun NSAttributedString.initWithHTML_documentAttributes(`data`: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithHTML:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`, dict) as MemorySegment
}

fun NSAttributedString.initWithHTML_baseURL_documentAttributes(`data`: MemorySegment, base: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithHTML:baseURL:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`, base, dict) as MemorySegment
}

fun NSAttributedString.initWithDocFormat_documentAttributes(`data`: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithDocFormat:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`, dict) as MemorySegment
}

fun NSAttributedString.initWithHTML_options_documentAttributes(`data`: MemorySegment, options: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithHTML:options:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`, options, dict) as MemorySegment
}

fun NSAttributedString.initWithRTFDFileWrapper_documentAttributes(wrapper: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithRTFDFileWrapper:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, wrapper, dict) as MemorySegment
}

fun NSAttributedString.RTFFromRange_documentAttributes(range: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("RTFFromRange:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range, dict) as MemorySegment
}

fun NSAttributedString.RTFDFromRange_documentAttributes(range: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("RTFDFromRange:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range, dict) as MemorySegment
}

fun NSAttributedString.RTFDFileWrapperFromRange_documentAttributes(range: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("RTFDFileWrapperFromRange:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range, dict) as MemorySegment
}

fun NSAttributedString.docFormatFromRange_documentAttributes(range: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("docFormatFromRange:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range, dict) as MemorySegment
}

// ── Category: NSAttributedStringAppKitAdditions on NSAttributedString ─────────────────────────────────────────

/** @return NSDictionary<NSAttributedStringKey,id> * */
fun NSAttributedString.fontAttributesInRange(range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fontAttributesInRange:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range) as MemorySegment
}

/** @return NSDictionary<NSAttributedStringKey,id> * */
fun NSAttributedString.rulerAttributesInRange(range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("rulerAttributesInRange:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range) as MemorySegment
}

fun NSAttributedString.lineBreakBeforeIndex_withinRange(location: Long, aRange: MemorySegment): Long {
    val sel = ObjCRuntime.sel("lineBreakBeforeIndex:withinRange:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, location, aRange) as Long
}

fun NSAttributedString.lineBreakByHyphenatingBeforeIndex_withinRange(location: Long, aRange: MemorySegment): Long {
    val sel = ObjCRuntime.sel("lineBreakByHyphenatingBeforeIndex:withinRange:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, location, aRange) as Long
}

fun NSAttributedString.doubleClickAtIndex(location: Long): MemorySegment {
    val sel = ObjCRuntime.sel("doubleClickAtIndex:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel, location) as MemorySegment
}

fun NSAttributedString.nextWordFromIndex_forward(location: Long, isForward: Boolean): Long {
    val sel = ObjCRuntime.sel("nextWordFromIndex:forward:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, location, isForward) as Long
}

fun NSAttributedString.rangeOfTextBlock_atIndex(block: MemorySegment, location: Long): MemorySegment {
    val sel = ObjCRuntime.sel("rangeOfTextBlock:atIndex:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel, block, location) as MemorySegment
}

fun NSAttributedString.rangeOfTextTable_atIndex(table: MemorySegment, location: Long): MemorySegment {
    val sel = ObjCRuntime.sel("rangeOfTextTable:atIndex:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel, table, location) as MemorySegment
}

fun NSAttributedString.rangeOfTextList_atIndex(list: MemorySegment, location: Long): MemorySegment {
    val sel = ObjCRuntime.sel("rangeOfTextList:atIndex:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel, list, location) as MemorySegment
}

fun NSAttributedString.itemNumberInTextList_atIndex(list: MemorySegment, location: Long): Long {
    val sel = ObjCRuntime.sel("itemNumberInTextList:atIndex:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, list, location) as Long
}

// ── Category: NSAttributedStringPasteboardAdditions on NSAttributedString ─────────────────────────────────────────

// Class method: +[NSAttributedString textTypes]
fun NSAttributedString_textTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("textTypes")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSAttributedString textUnfilteredTypes]
fun NSAttributedString_textUnfilteredTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("textUnfilteredTypes")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// @property textTypes
/** @return NSArray<NSString *> * */
fun NSAttributedString.textTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("textTypes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property textUnfilteredTypes
/** @return NSArray<NSString *> * */
fun NSAttributedString.textUnfilteredTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("textUnfilteredTypes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSDeprecatedKitAdditions on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.initWithURL_documentAttributes(url: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithURL:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, dict) as MemorySegment
}

fun NSAttributedString.initWithPath_documentAttributes(path: MemorySegment, dict: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithPath:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path, dict) as MemorySegment
}

fun NSAttributedString.URLAtIndex_effectiveRange(location: Long, effectiveRange: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("URLAtIndex:effectiveRange:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, location, effectiveRange) as MemorySegment
}

fun NSAttributedString.containsAttachments(): Boolean {
    val sel = ObjCRuntime.sel("containsAttachments")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// Class method: +[NSAttributedString textFileTypes]
fun NSAttributedString_textFileTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("textFileTypes")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSAttributedString textPasteboardTypes]
fun NSAttributedString_textPasteboardTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("textPasteboardTypes")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSAttributedString textUnfilteredFileTypes]
fun NSAttributedString_textUnfilteredFileTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("textUnfilteredFileTypes")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSAttributedString textUnfilteredPasteboardTypes]
fun NSAttributedString_textUnfilteredPasteboardTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("textUnfilteredPasteboardTypes")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// ── Category: NSAttributedStringAttachmentConveniences on NSAttributedString ─────────────────────────────────────────

// Class method: +[NSAttributedString attributedStringWithAttachment:]
fun NSAttributedString_attributedStringWithAttachment(attachment: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("attributedStringWithAttachment:")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, attachment) as MemorySegment
}

// Class method: +[NSAttributedString attributedStringWithAttachment:attributes:]
fun NSAttributedString_attributedStringWithAttachment_attributes(attachment: MemorySegment, attributes: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("attributedStringWithAttachment:attributes:")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, attachment, attributes) as MemorySegment
}

// ── Category: NSStringDrawing on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.size(): MemorySegment {
    val sel = ObjCRuntime.sel("size")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel) as MemorySegment
}

fun NSAttributedString.drawAtPoint(point: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("drawAtPoint:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point)
}

fun NSAttributedString.drawInRect(rect: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("drawInRect:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect)
}

// ── Category: NSExtendedStringDrawing on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.drawWithRect_options_context(rect: MemorySegment, options: MemorySegment, context: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("drawWithRect:options:context:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, options, context)
}

fun NSAttributedString.boundingRectWithSize_options_context(size: MemorySegment, options: MemorySegment, context: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("boundingRectWithSize:options:context:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, size, options, context) as MemorySegment
}

// ── Category: NSStringDrawingDeprecated on NSAttributedString ─────────────────────────────────────────

fun NSAttributedString.drawWithRect_options(rect: MemorySegment, options: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("drawWithRect:options:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, options)
}

fun NSAttributedString.boundingRectWithSize_options(size: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("boundingRectWithSize:options:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, size, options) as MemorySegment
}

// ── Category: NSAttributedStringAdaptiveImageGlyphConveniences on NSAttributedString ─────────────────────────────────────────

// Class method: +[NSAttributedString attributedStringWithAdaptiveImageGlyph:attributes:]
fun NSAttributedString_attributedStringWithAdaptiveImageGlyph_attributes(adaptiveImageGlyph: MemorySegment, attributes: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("attributedStringWithAdaptiveImageGlyph:attributes:")
    val cls = ObjCRuntime.getClass("NSAttributedString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, adaptiveImageGlyph, attributes) as MemorySegment
}

