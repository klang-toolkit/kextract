package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSScanner
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSScanner(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSScanner") }
        
    }
    
    open fun initWithString(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString(string: String): MemorySegment = initWithString(ObjCRuntime.newNSString(Arena.global(), string))
    
    // @property string
    open fun string(): MemorySegment {
        val sel = ObjCRuntime.sel("string")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun stringAsString(): String = ObjCRuntime.toJavaString(string())
    
    // @property scanLocation
    open fun scanLocation(): Long {
        val sel = ObjCRuntime.sel("scanLocation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setScanLocation(value: Long) {
        val sel = ObjCRuntime.sel("setScanLocation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property charactersToBeSkipped
    open fun charactersToBeSkipped(): MemorySegment {
        val sel = ObjCRuntime.sel("charactersToBeSkipped")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCharactersToBeSkipped(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCharactersToBeSkipped:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property caseSensitive
    open fun caseSensitive(): Boolean {
        val sel = ObjCRuntime.sel("caseSensitive")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCaseSensitive(value: Boolean) {
        val sel = ObjCRuntime.sel("setCaseSensitive:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property locale
    open fun locale(): MemorySegment {
        val sel = ObjCRuntime.sel("locale")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLocale(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLocale:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSExtendedScanner on NSScanner ─────────────────────────────────────────

fun NSScanner.scanInt(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanInt:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanInteger(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanInteger:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanLongLong(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanLongLong:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanUnsignedLongLong(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanUnsignedLongLong:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanFloat(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanFloat:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanDouble(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanDouble:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanHexInt(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanHexInt:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanHexLongLong(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanHexLongLong:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanHexFloat(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanHexFloat:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanHexDouble(result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanHexDouble:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, result) as Boolean
}

fun NSScanner.scanString_intoString(string: MemorySegment, result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanString:intoString:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, string, result) as Boolean
}

fun NSScanner.scanCharactersFromSet_intoString(`set`: MemorySegment, result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanCharactersFromSet:intoString:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `set`, result) as Boolean
}

fun NSScanner.scanUpToString_intoString(string: MemorySegment, result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanUpToString:intoString:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, string, result) as Boolean
}

fun NSScanner.scanUpToCharactersFromSet_intoString(`set`: MemorySegment, result: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanUpToCharactersFromSet:intoString:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `set`, result) as Boolean
}

fun NSScanner.isAtEnd(): Boolean {
    val sel = ObjCRuntime.sel("isAtEnd")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// Class method: +[NSScanner scannerWithString:]
fun NSScanner_scannerWithString(string: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("scannerWithString:")
    val cls = ObjCRuntime.getClass("NSScanner")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, string) as MemorySegment
}

// Class method: +[NSScanner localizedScannerWithString:]
fun NSScanner_localizedScannerWithString(string: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("localizedScannerWithString:")
    val cls = ObjCRuntime.getClass("NSScanner")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, string) as MemorySegment
}

// ── Category: NSDecimalNumberScanning on NSScanner ─────────────────────────────────────────

fun NSScanner.scanDecimal(dcm: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("scanDecimal:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, dcm) as Boolean
}

