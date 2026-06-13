package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSParagraphStyle
 * Superclass: NSObject
 * Protocols: NSCopying, NSMutableCopying, NSSecureCoding
 */
open class NSParagraphStyle(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSParagraphStyle") }
        
        fun defaultWritingDirectionForLanguage(languageName: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("defaultWritingDirectionForLanguage:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, languageName) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun defaultWritingDirectionForLanguage(languageName: String): MemorySegment = defaultWritingDirectionForLanguage(ObjCRuntime.newNSString(Arena.global(), languageName))
        
        fun defaultParagraphStyle(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultParagraphStyle")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property defaultParagraphStyle
    open fun defaultParagraphStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultParagraphStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property lineSpacing
    open fun lineSpacing(): Double {
        val sel = ObjCRuntime.sel("lineSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property paragraphSpacing
    open fun paragraphSpacing(): Double {
        val sel = ObjCRuntime.sel("paragraphSpacing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property headIndent
    open fun headIndent(): Double {
        val sel = ObjCRuntime.sel("headIndent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property tailIndent
    open fun tailIndent(): Double {
        val sel = ObjCRuntime.sel("tailIndent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property firstLineHeadIndent
    open fun firstLineHeadIndent(): Double {
        val sel = ObjCRuntime.sel("firstLineHeadIndent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property minimumLineHeight
    open fun minimumLineHeight(): Double {
        val sel = ObjCRuntime.sel("minimumLineHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property maximumLineHeight
    open fun maximumLineHeight(): Double {
        val sel = ObjCRuntime.sel("maximumLineHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property lineBreakMode
    open fun lineBreakMode(): MemorySegment {
        val sel = ObjCRuntime.sel("lineBreakMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property baseWritingDirection
    open fun baseWritingDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("baseWritingDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property lineHeightMultiple
    open fun lineHeightMultiple(): Double {
        val sel = ObjCRuntime.sel("lineHeightMultiple")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property paragraphSpacingBefore
    open fun paragraphSpacingBefore(): Double {
        val sel = ObjCRuntime.sel("paragraphSpacingBefore")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property hyphenationFactor
    open fun hyphenationFactor(): Float {
        val sel = ObjCRuntime.sel("hyphenationFactor")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    
    // @property usesDefaultHyphenation
    open fun usesDefaultHyphenation(): Boolean {
        val sel = ObjCRuntime.sel("usesDefaultHyphenation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property tabStops
    /** @return NSArray<NSTextTab *> * */
    open fun tabStops(): MemorySegment {
        val sel = ObjCRuntime.sel("tabStops")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property defaultTabInterval
    open fun defaultTabInterval(): Double {
        val sel = ObjCRuntime.sel("defaultTabInterval")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property textLists
    /** @return NSArray<NSTextList *> * */
    open fun textLists(): MemorySegment {
        val sel = ObjCRuntime.sel("textLists")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property allowsDefaultTighteningForTruncation
    open fun allowsDefaultTighteningForTruncation(): Boolean {
        val sel = ObjCRuntime.sel("allowsDefaultTighteningForTruncation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property lineBreakStrategy
    open fun lineBreakStrategy(): MemorySegment {
        val sel = ObjCRuntime.sel("lineBreakStrategy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category:  on NSParagraphStyle ─────────────────────────────────────────

fun NSParagraphStyle.alignment(): MemorySegment {
    val sel = ObjCRuntime.sel("alignment")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSParagraphStyle.tighteningFactorForTruncation(): Float {
    val sel = ObjCRuntime.sel("tighteningFactorForTruncation")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel) as Float
}

/** @return NSArray<__kindof NSTextBlock *> * */
fun NSParagraphStyle.textBlocks(): MemorySegment {
    val sel = ObjCRuntime.sel("textBlocks")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSParagraphStyle.headerLevel(): Long {
    val sel = ObjCRuntime.sel("headerLevel")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

