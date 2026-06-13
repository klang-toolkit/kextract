package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMorphologyCustomPronoun
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSMorphologyCustomPronoun(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMorphologyCustomPronoun") }
        
        fun isSupportedForLanguage(language: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("isSupportedForLanguage:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, language) as Boolean
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun isSupportedForLanguage(language: String): Boolean = isSupportedForLanguage(ObjCRuntime.newNSString(Arena.global(), language))
        
        /** @return NSArray<NSString *> * */
        fun requiredKeysForLanguage(language: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("requiredKeysForLanguage:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, language) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun requiredKeysForLanguage(language: String): MemorySegment = requiredKeysForLanguage(ObjCRuntime.newNSString(Arena.global(), language))
        
    }
    
    // @property subjectForm
    open fun subjectForm(): MemorySegment {
        val sel = ObjCRuntime.sel("subjectForm")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSubjectForm(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSubjectForm:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun subjectFormAsString(): String = ObjCRuntime.toJavaString(subjectForm())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setSubjectForm(value: String) = setSubjectForm(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property objectForm
    open fun objectForm(): MemorySegment {
        val sel = ObjCRuntime.sel("objectForm")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setObjectForm(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setObjectForm:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun objectFormAsString(): String = ObjCRuntime.toJavaString(objectForm())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setObjectForm(value: String) = setObjectForm(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property possessiveForm
    open fun possessiveForm(): MemorySegment {
        val sel = ObjCRuntime.sel("possessiveForm")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPossessiveForm(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPossessiveForm:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun possessiveFormAsString(): String = ObjCRuntime.toJavaString(possessiveForm())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPossessiveForm(value: String) = setPossessiveForm(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property possessiveAdjectiveForm
    open fun possessiveAdjectiveForm(): MemorySegment {
        val sel = ObjCRuntime.sel("possessiveAdjectiveForm")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPossessiveAdjectiveForm(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPossessiveAdjectiveForm:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun possessiveAdjectiveFormAsString(): String = ObjCRuntime.toJavaString(possessiveAdjectiveForm())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPossessiveAdjectiveForm(value: String) = setPossessiveAdjectiveForm(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property reflexiveForm
    open fun reflexiveForm(): MemorySegment {
        val sel = ObjCRuntime.sel("reflexiveForm")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setReflexiveForm(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setReflexiveForm:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun reflexiveFormAsString(): String = ObjCRuntime.toJavaString(reflexiveForm())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setReflexiveForm(value: String) = setReflexiveForm(ObjCRuntime.newNSString(Arena.global(), value))
    
}

