package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSXMLDTD
 * Superclass: NSXMLNode
 */
open class NSXMLDTD(override val ptr: MemorySegment) : NSXMLNode(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSXMLDTD") }
        
        fun predefinedEntityDeclarationForName(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("predefinedEntityDeclarationForName:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun predefinedEntityDeclarationForName(name: String): MemorySegment = predefinedEntityDeclarationForName(ObjCRuntime.newNSString(Arena.global(), name))
        
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    override fun initWithKind_options(kind: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithKind:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, kind, options) as MemorySegment
    }
    
    open fun initWithContentsOfURL_options_error(url: MemorySegment, mask: MemorySegment, error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentsOfURL:options:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, mask, error) as MemorySegment
    }
    
    open fun initWithData_options_error(`data`: MemorySegment, mask: MemorySegment, error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithData:options:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`, mask, error) as MemorySegment
    }
    
    open fun insertChild_atIndex(child: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertChild:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, child, index)
    }
    
    open fun insertChildren_atIndex(children: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertChildren:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, children, index)
    }
    
    open fun removeChildAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeChildAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun setChildren(children: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setChildren:")
        ObjCRuntime.msgSend(null, ptr, sel, children)
    }
    
    open fun addChild(child: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addChild:")
        ObjCRuntime.msgSend(null, ptr, sel, child)
    }
    
    open fun replaceChildAtIndex_withNode(index: Long, node: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceChildAtIndex:withNode:")
        ObjCRuntime.msgSend(null, ptr, sel, index, node)
    }
    
    open fun entityDeclarationForName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("entityDeclarationForName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun entityDeclarationForName(name: String): MemorySegment = entityDeclarationForName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun notationDeclarationForName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("notationDeclarationForName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun notationDeclarationForName(name: String): MemorySegment = notationDeclarationForName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun elementDeclarationForName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("elementDeclarationForName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun elementDeclarationForName(name: String): MemorySegment = elementDeclarationForName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun attributeDeclarationForName_elementName(name: MemorySegment, elementName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("attributeDeclarationForName:elementName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, elementName) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun attributeDeclarationForName_elementName(name: String, elementName: String): MemorySegment = attributeDeclarationForName_elementName(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), elementName))
    
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
    
}

