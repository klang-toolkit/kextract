package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPersonNameComponentsFormatter
 * Superclass: NSFormatter
 */
open class NSPersonNameComponentsFormatter(override val ptr: MemorySegment) : NSFormatter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPersonNameComponentsFormatter") }
        
        fun localizedStringFromPersonNameComponents_style_options(components: MemorySegment, nameFormatStyle: MemorySegment, nameOptions: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("localizedStringFromPersonNameComponents:style:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, components, nameFormatStyle, nameOptions) as MemorySegment
        }
        
        /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
        fun localizedStringFromPersonNameComponents_style_optionsAsString(components: MemorySegment, nameFormatStyle: MemorySegment, nameOptions: MemorySegment): String = ObjCRuntime.toJavaString(localizedStringFromPersonNameComponents_style_options(components, nameFormatStyle, nameOptions))
        
    }
    
    open fun stringFromPersonNameComponents(components: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromPersonNameComponents:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, components) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromPersonNameComponentsAsString(components: MemorySegment): String = ObjCRuntime.toJavaString(stringFromPersonNameComponents(components))
    
    open fun annotatedStringFromPersonNameComponents(components: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("annotatedStringFromPersonNameComponents:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, components) as MemorySegment
    }
    
    open fun personNameComponentsFromString(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("personNameComponentsFromString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun personNameComponentsFromString(string: String): MemorySegment = personNameComponentsFromString(ObjCRuntime.newNSString(Arena.global(), string))
    
    override fun getObjectValue_forString_errorDescription(obj: MemorySegment, string: MemorySegment, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("getObjectValue:forString:errorDescription:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, obj, string, error) as Boolean
    }
    
    // @property style
    open fun style(): MemorySegment {
        val sel = ObjCRuntime.sel("style")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property phonetic
    open fun isPhonetic(): Boolean {
        val sel = ObjCRuntime.sel("isPhonetic")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPhonetic(value: Boolean) {
        val sel = ObjCRuntime.sel("setPhonetic:")
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
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _private: MemorySegment
}

