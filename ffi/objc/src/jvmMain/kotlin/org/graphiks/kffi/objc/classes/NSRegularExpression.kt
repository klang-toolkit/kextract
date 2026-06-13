package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSRegularExpression
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSRegularExpression(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSRegularExpression") }
        
        fun regularExpressionWithPattern_options_error(pattern: MemorySegment, options: MemorySegment, error: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("regularExpressionWithPattern:options:error:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, pattern, options, error) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun regularExpressionWithPattern_options_error(pattern: String, options: MemorySegment, error: MemorySegment): MemorySegment = regularExpressionWithPattern_options_error(ObjCRuntime.newNSString(Arena.global(), pattern), options, error)
        
        fun escapedPatternForString(string: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("escapedPatternForString:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, string) as MemorySegment
        }
        
        /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
        fun escapedPatternForStringAsString(string: MemorySegment): String = ObjCRuntime.toJavaString(escapedPatternForString(string))
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun escapedPatternForString(string: String): MemorySegment = escapedPatternForString(ObjCRuntime.newNSString(Arena.global(), string))
        
        /** Convenience overload — [String] parameters and [String] return type. */
        fun escapedPatternForStringAsString(string: String): String = ObjCRuntime.toJavaString(escapedPatternForString(ObjCRuntime.newNSString(Arena.global(), string)))
        
    }
    
    open fun initWithPattern_options_error(pattern: MemorySegment, options: MemorySegment, error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPattern:options:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pattern, options, error) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithPattern_options_error(pattern: String, options: MemorySegment, error: MemorySegment): MemorySegment = initWithPattern_options_error(ObjCRuntime.newNSString(Arena.global(), pattern), options, error)
    
    // @property pattern
    open fun pattern(): MemorySegment {
        val sel = ObjCRuntime.sel("pattern")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun patternAsString(): String = ObjCRuntime.toJavaString(pattern())
    
    // @property options
    open fun options(): MemorySegment {
        val sel = ObjCRuntime.sel("options")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property numberOfCaptureGroups
    open fun numberOfCaptureGroups(): Long {
        val sel = ObjCRuntime.sel("numberOfCaptureGroups")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _pattern: MemorySegment
    // ivar: _options: Long
    // ivar: _internal: MemorySegment
    // ivar: _checkout: Int
}

// ── Category: NSMatching on NSRegularExpression ─────────────────────────────────────────

fun NSRegularExpression.enumerateMatchesInString_options_range_usingBlock(string: MemorySegment, options: MemorySegment, range: MemorySegment, block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateMatchesInString:options:range:usingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, string, options, range, block)
}

/** @return NSArray<NSTextCheckingResult *> * */
fun NSRegularExpression.matchesInString_options_range(string: MemorySegment, options: MemorySegment, range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("matchesInString:options:range:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, string, options, range) as MemorySegment
}

fun NSRegularExpression.numberOfMatchesInString_options_range(string: MemorySegment, options: MemorySegment, range: MemorySegment): Long {
    val sel = ObjCRuntime.sel("numberOfMatchesInString:options:range:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, string, options, range) as Long
}

fun NSRegularExpression.firstMatchInString_options_range(string: MemorySegment, options: MemorySegment, range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("firstMatchInString:options:range:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, string, options, range) as MemorySegment
}

fun NSRegularExpression.rangeOfFirstMatchInString_options_range(string: MemorySegment, options: MemorySegment, range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("rangeOfFirstMatchInString:options:range:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel, string, options, range) as MemorySegment
}

// ── Category: NSReplacement on NSRegularExpression ─────────────────────────────────────────

fun NSRegularExpression.stringByReplacingMatchesInString_options_range_withTemplate(string: MemorySegment, options: MemorySegment, range: MemorySegment, templ: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("stringByReplacingMatchesInString:options:range:withTemplate:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, string, options, range, templ) as MemorySegment
}

fun NSRegularExpression.replaceMatchesInString_options_range_withTemplate(string: MemorySegment, options: MemorySegment, range: MemorySegment, templ: MemorySegment): Long {
    val sel = ObjCRuntime.sel("replaceMatchesInString:options:range:withTemplate:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, string, options, range, templ) as Long
}

fun NSRegularExpression.replacementStringForResult_inString_offset_template(result: MemorySegment, string: MemorySegment, offset: Long, templ: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("replacementStringForResult:inString:offset:template:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, result, string, offset, templ) as MemorySegment
}

// Class method: +[NSRegularExpression escapedTemplateForString:]
fun NSRegularExpression_escapedTemplateForString(string: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("escapedTemplateForString:")
    val cls = ObjCRuntime.getClass("NSRegularExpression")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, string) as MemorySegment
}

