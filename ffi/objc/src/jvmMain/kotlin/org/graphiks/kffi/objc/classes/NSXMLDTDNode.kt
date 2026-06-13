package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSXMLDTDNode
 * Superclass: NSXMLNode
 */
open class NSXMLDTDNode(override val ptr: MemorySegment) : NSXMLNode(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSXMLDTDNode") }
        
    }
    
    open fun initWithXMLString(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithXMLString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithXMLString(string: String): MemorySegment = initWithXMLString(ObjCRuntime.newNSString(Arena.global(), string))
    
    override fun initWithKind_options(kind: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithKind:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, kind, options) as MemorySegment
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property DTDKind
    open fun DTDKind(): MemorySegment {
        val sel = ObjCRuntime.sel("DTDKind")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDTDKind(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDTDKind:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property external
    open fun isExternal(): Boolean {
        val sel = ObjCRuntime.sel("isExternal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property publicID
    open fun publicID(): MemorySegment {
        val sel = ObjCRuntime.sel("publicID")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPublicID(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPublicID:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun publicIDAsString(): String = ObjCRuntime.toJavaString(publicID())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPublicID(value: String) = setPublicID(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property systemID
    open fun systemID(): MemorySegment {
        val sel = ObjCRuntime.sel("systemID")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSystemID(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSystemID:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun systemIDAsString(): String = ObjCRuntime.toJavaString(systemID())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setSystemID(value: String) = setSystemID(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property notationName
    open fun notationName(): MemorySegment {
        val sel = ObjCRuntime.sel("notationName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setNotationName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setNotationName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun notationNameAsString(): String = ObjCRuntime.toJavaString(notationName())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setNotationName(value: String) = setNotationName(ObjCRuntime.newNSString(Arena.global(), value))
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _DTDKind: MemorySegment
    // ivar: _name: MemorySegment
    // ivar: _notationName: MemorySegment
    // ivar: _publicID: MemorySegment
    // ivar: _systemID: MemorySegment
}

