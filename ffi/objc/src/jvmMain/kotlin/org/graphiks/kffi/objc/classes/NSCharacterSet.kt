package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCharacterSet
 * Superclass: NSObject
 * Protocols: NSCopying, NSMutableCopying, NSSecureCoding
 */
open class NSCharacterSet(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCharacterSet") }
        
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
        
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun characterIsMember(aCharacter: Short): Boolean {
        val sel = ObjCRuntime.sel("characterIsMember:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, aCharacter) as Boolean
    }
    
    open fun longCharacterIsMember(theLongChar: Int): Boolean {
        val sel = ObjCRuntime.sel("longCharacterIsMember:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, theLongChar) as Boolean
    }
    
    open fun isSupersetOfSet(theOtherSet: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isSupersetOfSet:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, theOtherSet) as Boolean
    }
    
    open fun hasMemberInPlane(thePlane: Byte): Boolean {
        val sel = ObjCRuntime.sel("hasMemberInPlane:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, thePlane) as Boolean
    }
    
    // @property controlCharacterSet
    open fun controlCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("controlCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property whitespaceCharacterSet
    open fun whitespaceCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("whitespaceCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property whitespaceAndNewlineCharacterSet
    open fun whitespaceAndNewlineCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("whitespaceAndNewlineCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property decimalDigitCharacterSet
    open fun decimalDigitCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("decimalDigitCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property letterCharacterSet
    open fun letterCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("letterCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property lowercaseLetterCharacterSet
    open fun lowercaseLetterCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("lowercaseLetterCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property uppercaseLetterCharacterSet
    open fun uppercaseLetterCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("uppercaseLetterCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property nonBaseCharacterSet
    open fun nonBaseCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("nonBaseCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property alphanumericCharacterSet
    open fun alphanumericCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("alphanumericCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property decomposableCharacterSet
    open fun decomposableCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("decomposableCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property illegalCharacterSet
    open fun illegalCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("illegalCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property punctuationCharacterSet
    open fun punctuationCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("punctuationCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property capitalizedLetterCharacterSet
    open fun capitalizedLetterCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("capitalizedLetterCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property symbolCharacterSet
    open fun symbolCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("symbolCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property newlineCharacterSet
    open fun newlineCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("newlineCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property bitmapRepresentation
    open fun bitmapRepresentation(): MemorySegment {
        val sel = ObjCRuntime.sel("bitmapRepresentation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property invertedSet
    open fun invertedSet(): MemorySegment {
        val sel = ObjCRuntime.sel("invertedSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSURLUtilities on NSCharacterSet ─────────────────────────────────────────

// Class method: +[NSCharacterSet URLUserAllowedCharacterSet]
fun NSCharacterSet_URLUserAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLUserAllowedCharacterSet")
    val cls = ObjCRuntime.getClass("NSCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCharacterSet URLPasswordAllowedCharacterSet]
fun NSCharacterSet_URLPasswordAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLPasswordAllowedCharacterSet")
    val cls = ObjCRuntime.getClass("NSCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCharacterSet URLHostAllowedCharacterSet]
fun NSCharacterSet_URLHostAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLHostAllowedCharacterSet")
    val cls = ObjCRuntime.getClass("NSCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCharacterSet URLPathAllowedCharacterSet]
fun NSCharacterSet_URLPathAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLPathAllowedCharacterSet")
    val cls = ObjCRuntime.getClass("NSCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCharacterSet URLQueryAllowedCharacterSet]
fun NSCharacterSet_URLQueryAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLQueryAllowedCharacterSet")
    val cls = ObjCRuntime.getClass("NSCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSCharacterSet URLFragmentAllowedCharacterSet]
fun NSCharacterSet_URLFragmentAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLFragmentAllowedCharacterSet")
    val cls = ObjCRuntime.getClass("NSCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// @property URLUserAllowedCharacterSet
fun NSCharacterSet.URLUserAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLUserAllowedCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property URLPasswordAllowedCharacterSet
fun NSCharacterSet.URLPasswordAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLPasswordAllowedCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property URLHostAllowedCharacterSet
fun NSCharacterSet.URLHostAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLHostAllowedCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property URLPathAllowedCharacterSet
fun NSCharacterSet.URLPathAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLPathAllowedCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property URLQueryAllowedCharacterSet
fun NSCharacterSet.URLQueryAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLQueryAllowedCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property URLFragmentAllowedCharacterSet
fun NSCharacterSet.URLFragmentAllowedCharacterSet(): MemorySegment {
    val sel = ObjCRuntime.sel("URLFragmentAllowedCharacterSet")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

