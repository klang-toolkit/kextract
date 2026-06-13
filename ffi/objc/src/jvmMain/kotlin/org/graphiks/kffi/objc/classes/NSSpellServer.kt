package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSpellServer
 * Superclass: NSObject
 */
open class NSSpellServer(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSpellServer") }
        
    }
    
    open fun registerLanguage_byVendor(language: MemorySegment, vendor: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("registerLanguage:byVendor:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, language, vendor) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun registerLanguage_byVendor(language: String, vendor: String): Boolean = registerLanguage_byVendor(ObjCRuntime.newNSString(Arena.global(), language), ObjCRuntime.newNSString(Arena.global(), vendor))
    
    open fun isWordInUserDictionaries_caseSensitive(word: MemorySegment, flag: Boolean): Boolean {
        val sel = ObjCRuntime.sel("isWordInUserDictionaries:caseSensitive:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, word, flag) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun isWordInUserDictionaries_caseSensitive(word: String, flag: Boolean): Boolean = isWordInUserDictionaries_caseSensitive(ObjCRuntime.newNSString(Arena.global(), word), flag)
    
    open fun run(): Unit {
        val sel = ObjCRuntime.sel("run")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property delegate
    /** @return id<NSSpellServerDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

