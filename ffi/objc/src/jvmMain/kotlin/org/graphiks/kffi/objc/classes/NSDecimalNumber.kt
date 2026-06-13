package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDecimalNumber
 * Superclass: NSNumber
 */
open class NSDecimalNumber(override val ptr: MemorySegment) : NSNumber(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDecimalNumber") }
        
        fun decimalNumberWithMantissa_exponent_isNegative(mantissa: Long, exponent: Short, flag: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("decimalNumberWithMantissa:exponent:isNegative:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, mantissa, exponent, flag) as MemorySegment
        }
        
        fun decimalNumberWithDecimal(dcm: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("decimalNumberWithDecimal:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ObjCRuntime.ObjCStructArg(dcm, MemoryLayout.structLayout(ValueLayout.ADDRESS.withName("_mantissa")).withName("NSDecimal"))) as MemorySegment
        }
        
        fun decimalNumberWithString(numberValue: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("decimalNumberWithString:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, numberValue) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun decimalNumberWithString(numberValue: String): MemorySegment = decimalNumberWithString(ObjCRuntime.newNSString(Arena.global(), numberValue))
        
        fun decimalNumberWithString_locale(numberValue: MemorySegment, locale: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("decimalNumberWithString:locale:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, numberValue, locale) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun decimalNumberWithString_locale(numberValue: String, locale: MemorySegment): MemorySegment = decimalNumberWithString_locale(ObjCRuntime.newNSString(Arena.global(), numberValue), locale)
        
        fun zero(): MemorySegment {
            val sel = ObjCRuntime.sel("zero")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun one(): MemorySegment {
            val sel = ObjCRuntime.sel("one")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun minimumDecimalNumber(): MemorySegment {
            val sel = ObjCRuntime.sel("minimumDecimalNumber")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun maximumDecimalNumber(): MemorySegment {
            val sel = ObjCRuntime.sel("maximumDecimalNumber")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun notANumber(): MemorySegment {
            val sel = ObjCRuntime.sel("notANumber")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return id<NSDecimalNumberBehaviors> */
        fun defaultBehavior(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultBehavior")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun setDefaultBehavior(defaultBehavior: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setDefaultBehavior:")
            ObjCRuntime.msgSend(null, _class, sel, defaultBehavior)
        }
        
    }
    
    open fun initWithMantissa_exponent_isNegative(mantissa: Long, exponent: Short, flag: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMantissa:exponent:isNegative:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, mantissa, exponent, flag) as MemorySegment
    }
    
    open fun initWithDecimal(dcm: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDecimal:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(dcm, MemoryLayout.structLayout(ValueLayout.ADDRESS.withName("_mantissa")).withName("NSDecimal"))) as MemorySegment
    }
    
    open fun initWithString(numberValue: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, numberValue) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString(numberValue: String): MemorySegment = initWithString(ObjCRuntime.newNSString(Arena.global(), numberValue))
    
    open fun initWithString_locale(numberValue: MemorySegment, locale: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:locale:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, numberValue, locale) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString_locale(numberValue: String, locale: MemorySegment): MemorySegment = initWithString_locale(ObjCRuntime.newNSString(Arena.global(), numberValue), locale)
    
    override fun descriptionWithLocale(locale: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("descriptionWithLocale:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, locale) as MemorySegment
    }
    
    open fun decimalNumberByAdding(decimalNumber: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByAdding:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, decimalNumber) as MemorySegment
    }
    
    open fun decimalNumberByAdding_withBehavior(decimalNumber: MemorySegment, behavior: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByAdding:withBehavior:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, decimalNumber, behavior) as MemorySegment
    }
    
    open fun decimalNumberBySubtracting(decimalNumber: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberBySubtracting:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, decimalNumber) as MemorySegment
    }
    
    open fun decimalNumberBySubtracting_withBehavior(decimalNumber: MemorySegment, behavior: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberBySubtracting:withBehavior:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, decimalNumber, behavior) as MemorySegment
    }
    
    open fun decimalNumberByMultiplyingBy(decimalNumber: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByMultiplyingBy:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, decimalNumber) as MemorySegment
    }
    
    open fun decimalNumberByMultiplyingBy_withBehavior(decimalNumber: MemorySegment, behavior: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByMultiplyingBy:withBehavior:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, decimalNumber, behavior) as MemorySegment
    }
    
    open fun decimalNumberByDividingBy(decimalNumber: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByDividingBy:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, decimalNumber) as MemorySegment
    }
    
    open fun decimalNumberByDividingBy_withBehavior(decimalNumber: MemorySegment, behavior: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByDividingBy:withBehavior:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, decimalNumber, behavior) as MemorySegment
    }
    
    open fun decimalNumberByRaisingToPower(power: Long): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByRaisingToPower:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, power) as MemorySegment
    }
    
    open fun decimalNumberByRaisingToPower_withBehavior(power: Long, behavior: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByRaisingToPower:withBehavior:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, power, behavior) as MemorySegment
    }
    
    open fun decimalNumberByMultiplyingByPowerOf10(power: Short): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByMultiplyingByPowerOf10:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, power) as MemorySegment
    }
    
    open fun decimalNumberByMultiplyingByPowerOf10_withBehavior(power: Short, behavior: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByMultiplyingByPowerOf10:withBehavior:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, power, behavior) as MemorySegment
    }
    
    open fun decimalNumberByRoundingAccordingToBehavior(behavior: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("decimalNumberByRoundingAccordingToBehavior:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, behavior) as MemorySegment
    }
    
    override fun compare(decimalNumber: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("compare:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, decimalNumber) as MemorySegment
    }
    
    // @property decimalValue
    open fun decimalValue(): MemorySegment {
        val sel = ObjCRuntime.sel("decimalValue")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.ADDRESS.withName("_mantissa")).withName("NSDecimal"), ptr, sel) as MemorySegment
    }
    
    // @property zero
    open fun zero(): MemorySegment {
        val sel = ObjCRuntime.sel("zero")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property one
    open fun one(): MemorySegment {
        val sel = ObjCRuntime.sel("one")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property minimumDecimalNumber
    open fun minimumDecimalNumber(): MemorySegment {
        val sel = ObjCRuntime.sel("minimumDecimalNumber")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property maximumDecimalNumber
    open fun maximumDecimalNumber(): MemorySegment {
        val sel = ObjCRuntime.sel("maximumDecimalNumber")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property notANumber
    open fun notANumber(): MemorySegment {
        val sel = ObjCRuntime.sel("notANumber")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property defaultBehavior
    /** @return id<NSDecimalNumberBehaviors> */
    open fun defaultBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDefaultBehavior(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDefaultBehavior:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property objCType
    override fun objCType(): MemorySegment {
        val sel = ObjCRuntime.sel("objCType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property doubleValue
    override fun doubleValue(): Double {
        val sel = ObjCRuntime.sel("doubleValue")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _exponent: Int
    // ivar: _length: Int
    // ivar: _isNegative: Int
    // ivar: _isCompact: Int
    // ivar: _reserved: Int
    // ivar: _hasExternalRefCount: Int
    // ivar: _refs: Int
    // ivar: _mantissa: MemorySegment
}

