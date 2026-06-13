package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMutableAttributedString
 * Superclass: NSAttributedString
 */
open class NSMutableAttributedString(override val ptr: MemorySegment) : NSAttributedString(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMutableAttributedString") }
        
    }
    
    open fun replaceCharactersInRange_withString(range: MemorySegment, str: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceCharactersInRange:withString:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), str)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun replaceCharactersInRange_withString(range: MemorySegment, str: String): Unit = replaceCharactersInRange_withString(range, ObjCRuntime.newNSString(Arena.global(), str))
    
    open fun setAttributes_range(attrs: MemorySegment, range: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setAttributes:range:")
        ObjCRuntime.msgSend(null, ptr, sel, attrs, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
}

// ── Category: NSExtendedMutableAttributedString on NSMutableAttributedString ─────────────────────────────────────────

fun NSMutableAttributedString.addAttribute_value_range(name: MemorySegment, value: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addAttribute:value:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, name, value, range)
}

fun NSMutableAttributedString.addAttributes_range(attrs: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addAttributes:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attrs, range)
}

fun NSMutableAttributedString.removeAttribute_range(name: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeAttribute:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, name, range)
}

fun NSMutableAttributedString.replaceCharactersInRange_withAttributedString(range: MemorySegment, attrString: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("replaceCharactersInRange:withAttributedString:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range, attrString)
}

fun NSMutableAttributedString.insertAttributedString_atIndex(attrString: MemorySegment, loc: Long): Unit {
    val sel = ObjCRuntime.sel("insertAttributedString:atIndex:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attrString, loc)
}

fun NSMutableAttributedString.appendAttributedString(attrString: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("appendAttributedString:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attrString)
}

fun NSMutableAttributedString.deleteCharactersInRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("deleteCharactersInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

fun NSMutableAttributedString.setAttributedString(attrString: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAttributedString:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attrString)
}

fun NSMutableAttributedString.beginEditing(): Unit {
    val sel = ObjCRuntime.sel("beginEditing")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSMutableAttributedString.endEditing(): Unit {
    val sel = ObjCRuntime.sel("endEditing")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSMutableAttributedString.mutableString(): MemorySegment {
    val sel = ObjCRuntime.sel("mutableString")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSMutableAttributedStringFormatting on NSMutableAttributedString ─────────────────────────────────────────

fun NSMutableAttributedString.appendLocalizedFormat(format: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("appendLocalizedFormat:")
    ObjCRuntime.msgSend(null, this.ptr, sel, format)
}

// ── Category: NSAttributedStringAttributeFixing on NSMutableAttributedString ─────────────────────────────────────────

fun NSMutableAttributedString.fixAttributesInRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("fixAttributesInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

// ── Category: NSMutableAttributedStringDocumentFormats on NSMutableAttributedString ─────────────────────────────────────────

fun NSMutableAttributedString.readFromURL_options_documentAttributes_error(url: MemorySegment, opts: MemorySegment, dict: MemorySegment, error: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("readFromURL:options:documentAttributes:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, opts, dict, error) as Boolean
}

fun NSMutableAttributedString.readFromData_options_documentAttributes_error(`data`: MemorySegment, opts: MemorySegment, dict: MemorySegment, error: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("readFromData:options:documentAttributes:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `data`, opts, dict, error) as Boolean
}

// ── Category: NSAttributedStringAppKitAttributeFixing on NSMutableAttributedString ─────────────────────────────────────────

fun NSMutableAttributedString.fixFontAttributeInRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("fixFontAttributeInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

fun NSMutableAttributedString.fixParagraphStyleAttributeInRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("fixParagraphStyleAttributeInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

fun NSMutableAttributedString.fixAttachmentAttributeInRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("fixAttachmentAttributeInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

// ── Category: NSMutableAttributedStringAppKitAdditions on NSMutableAttributedString ─────────────────────────────────────────

fun NSMutableAttributedString.superscriptRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("superscriptRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

fun NSMutableAttributedString.subscriptRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("subscriptRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

fun NSMutableAttributedString.unscriptRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("unscriptRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

fun NSMutableAttributedString.applyFontTraits_range(traitMask: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("applyFontTraits:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, traitMask, range)
}

fun NSMutableAttributedString.setAlignment_range(alignment: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAlignment:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, alignment, range)
}

fun NSMutableAttributedString.setBaseWritingDirection_range(writingDirection: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setBaseWritingDirection:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, writingDirection, range)
}

// ── Category: NSDeprecatedKitAdditions on NSMutableAttributedString ─────────────────────────────────────────

fun NSMutableAttributedString.readFromURL_options_documentAttributes(url: MemorySegment, options: MemorySegment, dict: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("readFromURL:options:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, options, dict) as Boolean
}

fun NSMutableAttributedString.readFromData_options_documentAttributes(`data`: MemorySegment, options: MemorySegment, dict: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("readFromData:options:documentAttributes:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `data`, options, dict) as Boolean
}

// ── Category: NSMutableAttributedStringAttachmentConveniences on NSMutableAttributedString ─────────────────────────────────────────

fun NSMutableAttributedString.updateAttachmentsFromPath(path: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("updateAttachmentsFromPath:")
    ObjCRuntime.msgSend(null, this.ptr, sel, path)
}

