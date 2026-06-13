package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSNumber
 * Superclass: NSValue
 */
open class NSNumber(override val ptr: MemorySegment) : NSValue(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSNumber") }
        
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun initWithChar(value: Byte): MemorySegment {
        val sel = ObjCRuntime.sel("initWithChar:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithUnsignedChar(value: Byte): MemorySegment {
        val sel = ObjCRuntime.sel("initWithUnsignedChar:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithShort(value: Short): MemorySegment {
        val sel = ObjCRuntime.sel("initWithShort:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithUnsignedShort(value: Short): MemorySegment {
        val sel = ObjCRuntime.sel("initWithUnsignedShort:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithInt(value: Int): MemorySegment {
        val sel = ObjCRuntime.sel("initWithInt:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithUnsignedInt(value: Int): MemorySegment {
        val sel = ObjCRuntime.sel("initWithUnsignedInt:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithLong(value: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithLong:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithUnsignedLong(value: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithUnsignedLong:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithLongLong(value: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithLongLong:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithUnsignedLongLong(value: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithUnsignedLongLong:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithFloat(value: Float): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFloat:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithDouble(value: Double): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDouble:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithBool(value: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithBool:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithInteger(value: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithInteger:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun initWithUnsignedInteger(value: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithUnsignedInteger:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value) as MemorySegment
    }
    
    open fun compare(otherNumber: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("compare:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, otherNumber) as MemorySegment
    }
    
    open fun isEqualToNumber(number: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isEqualToNumber:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, number) as Boolean
    }
    
    open fun descriptionWithLocale(locale: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("descriptionWithLocale:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, locale) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun descriptionWithLocaleAsString(locale: MemorySegment): String = ObjCRuntime.toJavaString(descriptionWithLocale(locale))
    
    // @property charValue
    open fun charValue(): Byte {
        val sel = ObjCRuntime.sel("charValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BYTE, ptr, sel) as Byte
    }
    
    // @property unsignedCharValue
    open fun unsignedCharValue(): Byte {
        val sel = ObjCRuntime.sel("unsignedCharValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BYTE, ptr, sel) as Byte
    }
    
    // @property shortValue
    open fun shortValue(): Short {
        val sel = ObjCRuntime.sel("shortValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, ptr, sel) as Short
    }
    
    // @property unsignedShortValue
    open fun unsignedShortValue(): Short {
        val sel = ObjCRuntime.sel("unsignedShortValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, ptr, sel) as Short
    }
    
    // @property intValue
    open fun intValue(): Int {
        val sel = ObjCRuntime.sel("intValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property unsignedIntValue
    open fun unsignedIntValue(): Int {
        val sel = ObjCRuntime.sel("unsignedIntValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property longValue
    open fun longValue(): Long {
        val sel = ObjCRuntime.sel("longValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property unsignedLongValue
    open fun unsignedLongValue(): Long {
        val sel = ObjCRuntime.sel("unsignedLongValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property longLongValue
    open fun longLongValue(): Long {
        val sel = ObjCRuntime.sel("longLongValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property unsignedLongLongValue
    open fun unsignedLongLongValue(): Long {
        val sel = ObjCRuntime.sel("unsignedLongLongValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property floatValue
    open fun floatValue(): Float {
        val sel = ObjCRuntime.sel("floatValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    
    // @property doubleValue
    open fun doubleValue(): Double {
        val sel = ObjCRuntime.sel("doubleValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property boolValue
    open fun boolValue(): Boolean {
        val sel = ObjCRuntime.sel("boolValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property integerValue
    open fun integerValue(): Long {
        val sel = ObjCRuntime.sel("integerValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property unsignedIntegerValue
    open fun unsignedIntegerValue(): Long {
        val sel = ObjCRuntime.sel("unsignedIntegerValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property stringValue
    open fun stringValue(): MemorySegment {
        val sel = ObjCRuntime.sel("stringValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun stringValueAsString(): String = ObjCRuntime.toJavaString(stringValue())
    
}

// ── Category: NSNumberCreation on NSNumber ─────────────────────────────────────────

// Class method: +[NSNumber numberWithChar:]
fun NSNumber_numberWithChar(value: Byte): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithChar:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithUnsignedChar:]
fun NSNumber_numberWithUnsignedChar(value: Byte): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithUnsignedChar:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithShort:]
fun NSNumber_numberWithShort(value: Short): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithShort:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithUnsignedShort:]
fun NSNumber_numberWithUnsignedShort(value: Short): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithUnsignedShort:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithInt:]
fun NSNumber_numberWithInt(value: Int): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithInt:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithUnsignedInt:]
fun NSNumber_numberWithUnsignedInt(value: Int): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithUnsignedInt:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithLong:]
fun NSNumber_numberWithLong(value: Long): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithLong:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithUnsignedLong:]
fun NSNumber_numberWithUnsignedLong(value: Long): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithUnsignedLong:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithLongLong:]
fun NSNumber_numberWithLongLong(value: Long): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithLongLong:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithUnsignedLongLong:]
fun NSNumber_numberWithUnsignedLongLong(value: Long): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithUnsignedLongLong:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithFloat:]
fun NSNumber_numberWithFloat(value: Float): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithFloat:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithDouble:]
fun NSNumber_numberWithDouble(value: Double): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithDouble:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithBool:]
fun NSNumber_numberWithBool(value: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithBool:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithInteger:]
fun NSNumber_numberWithInteger(value: Long): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithInteger:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// Class method: +[NSNumber numberWithUnsignedInteger:]
fun NSNumber_numberWithUnsignedInteger(value: Long): MemorySegment {
    val sel = ObjCRuntime.sel("numberWithUnsignedInteger:")
    val cls = ObjCRuntime.getClass("NSNumber")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value) as MemorySegment
}

// ── Category: NSDecimalNumberExtensions on NSNumber ─────────────────────────────────────────

fun NSNumber.decimalValue(): MemorySegment {
    val sel = ObjCRuntime.sel("decimalValue")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.ADDRESS.withName("_mantissa")).withName("NSDecimal"), this.ptr, sel) as MemorySegment
}

