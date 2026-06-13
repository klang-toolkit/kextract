package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMutableCharacterSet
 * Superclass: NSCharacterSet
 * Protocols: NSCopying, NSMutableCopying, NSSecureCoding
 */
open class NSMutableCharacterSet(override val ptr: MemorySegment) : NSCharacterSet(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMutableCharacterSet") }
        
        fun controlCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("controlCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun whitespaceCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("whitespaceCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun whitespaceAndNewlineCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("whitespaceAndNewlineCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun decimalDigitCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("decimalDigitCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun letterCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("letterCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun lowercaseLetterCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("lowercaseLetterCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun uppercaseLetterCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("uppercaseLetterCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun nonBaseCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("nonBaseCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun alphanumericCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("alphanumericCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun decomposableCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("decomposableCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun illegalCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("illegalCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun punctuationCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("punctuationCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun capitalizedLetterCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("capitalizedLetterCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun symbolCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("symbolCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun newlineCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("newlineCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun characterSetWithRange(aRange: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("characterSetWithRange:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ObjCRuntime.ObjCStructArg(aRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"))) as MemorySegment
        }
        
        fun characterSetWithCharactersInString(aString: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("characterSetWithCharactersInString:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, aString) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun characterSetWithCharactersInString(aString: String): MemorySegment = characterSetWithCharactersInString(ObjCRuntime.newNSString(Arena.global(), aString))
        
        fun characterSetWithBitmapRepresentation(`data`: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("characterSetWithBitmapRepresentation:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, `data`) as MemorySegment
        }
        
        fun characterSetWithContentsOfFile(fName: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("characterSetWithContentsOfFile:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fName) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun characterSetWithContentsOfFile(fName: String): MemorySegment = characterSetWithContentsOfFile(ObjCRuntime.newNSString(Arena.global(), fName))
        
    }
    
    open fun addCharactersInRange(aRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addCharactersInRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(aRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun removeCharactersInRange(aRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeCharactersInRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(aRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    open fun addCharactersInString(aString: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addCharactersInString:")
        ObjCRuntime.msgSend(null, ptr, sel, aString)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun addCharactersInString(aString: String): Unit = addCharactersInString(ObjCRuntime.newNSString(Arena.global(), aString))
    
    open fun removeCharactersInString(aString: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeCharactersInString:")
        ObjCRuntime.msgSend(null, ptr, sel, aString)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun removeCharactersInString(aString: String): Unit = removeCharactersInString(ObjCRuntime.newNSString(Arena.global(), aString))
    
    open fun formUnionWithCharacterSet(otherSet: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("formUnionWithCharacterSet:")
        ObjCRuntime.msgSend(null, ptr, sel, otherSet)
    }
    
    open fun formIntersectionWithCharacterSet(otherSet: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("formIntersectionWithCharacterSet:")
        ObjCRuntime.msgSend(null, ptr, sel, otherSet)
    }
    
    open fun invert(): Unit {
        val sel = ObjCRuntime.sel("invert")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
}

