package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSXMLElement
 * Superclass: NSXMLNode
 */
open class NSXMLElement(override val ptr: MemorySegment) : NSXMLNode(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSXMLElement") }
        
    }
    
    open fun initWithName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithName(name: String): MemorySegment = initWithName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun initWithName_URI(name: MemorySegment, URI: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithName:URI:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, URI) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithName_URI(name: String, URI: String): MemorySegment = initWithName_URI(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), URI))
    
    open fun initWithName_stringValue(name: MemorySegment, string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithName:stringValue:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, string) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithName_stringValue(name: String, string: String): MemorySegment = initWithName_stringValue(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), string))
    
    open fun initWithXMLString_error(string: MemorySegment, error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithXMLString:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string, error) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithXMLString_error(string: String, error: MemorySegment): MemorySegment = initWithXMLString_error(ObjCRuntime.newNSString(Arena.global(), string), error)
    
    override fun initWithKind_options(kind: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithKind:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, kind, options) as MemorySegment
    }
    
    /** @return NSArray<NSXMLElement *> * */
    open fun elementsForName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("elementsForName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun elementsForName(name: String): MemorySegment = elementsForName(ObjCRuntime.newNSString(Arena.global(), name))
    
    /** @return NSArray<NSXMLElement *> * */
    open fun elementsForLocalName_URI(localName: MemorySegment, URI: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("elementsForLocalName:URI:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, localName, URI) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun elementsForLocalName_URI(localName: String, URI: String): MemorySegment = elementsForLocalName_URI(ObjCRuntime.newNSString(Arena.global(), localName), ObjCRuntime.newNSString(Arena.global(), URI))
    
    open fun addAttribute(attribute: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addAttribute:")
        ObjCRuntime.msgSend(null, ptr, sel, attribute)
    }
    
    open fun removeAttributeForName(name: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeAttributeForName:")
        ObjCRuntime.msgSend(null, ptr, sel, name)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun removeAttributeForName(name: String): Unit = removeAttributeForName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun setAttributesWithDictionary(attributes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setAttributesWithDictionary:")
        ObjCRuntime.msgSend(null, ptr, sel, attributes)
    }
    
    open fun attributeForName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("attributeForName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun attributeForName(name: String): MemorySegment = attributeForName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun attributeForLocalName_URI(localName: MemorySegment, URI: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("attributeForLocalName:URI:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, localName, URI) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun attributeForLocalName_URI(localName: String, URI: String): MemorySegment = attributeForLocalName_URI(ObjCRuntime.newNSString(Arena.global(), localName), ObjCRuntime.newNSString(Arena.global(), URI))
    
    open fun addNamespace(aNamespace: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addNamespace:")
        ObjCRuntime.msgSend(null, ptr, sel, aNamespace)
    }
    
    open fun removeNamespaceForPrefix(name: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeNamespaceForPrefix:")
        ObjCRuntime.msgSend(null, ptr, sel, name)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun removeNamespaceForPrefix(name: String): Unit = removeNamespaceForPrefix(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun namespaceForPrefix(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("namespaceForPrefix:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun namespaceForPrefix(name: String): MemorySegment = namespaceForPrefix(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun resolveNamespaceForName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("resolveNamespaceForName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun resolveNamespaceForName(name: String): MemorySegment = resolveNamespaceForName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun resolvePrefixForNamespaceURI(namespaceURI: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("resolvePrefixForNamespaceURI:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, namespaceURI) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun resolvePrefixForNamespaceURIAsString(namespaceURI: MemorySegment): String = ObjCRuntime.toJavaString(resolvePrefixForNamespaceURI(namespaceURI))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun resolvePrefixForNamespaceURI(namespaceURI: String): MemorySegment = resolvePrefixForNamespaceURI(ObjCRuntime.newNSString(Arena.global(), namespaceURI))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun resolvePrefixForNamespaceURIAsString(namespaceURI: String): String = ObjCRuntime.toJavaString(resolvePrefixForNamespaceURI(ObjCRuntime.newNSString(Arena.global(), namespaceURI)))
    
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
    
    open fun normalizeAdjacentTextNodesPreservingCDATA(preserve: Boolean): Unit {
        val sel = ObjCRuntime.sel("normalizeAdjacentTextNodesPreservingCDATA:")
        ObjCRuntime.msgSend(null, ptr, sel, preserve)
    }
    
    // @property attributes
    /** @return NSArray<NSXMLNode *> * */
    open fun attributes(): MemorySegment {
        val sel = ObjCRuntime.sel("attributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttributes(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property namespaces
    /** @return NSArray<NSXMLNode *> * */
    open fun namespaces(): MemorySegment {
        val sel = ObjCRuntime.sel("namespaces")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setNamespaces(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setNamespaces:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _name: MemorySegment
    // ivar: _attributes: MemorySegment
    // ivar: _namespaces: MemorySegment
    // ivar: _children: MemorySegment
    // ivar: _childrenHaveMutated: Boolean
    // ivar: _zeroOrOneAttributes: Boolean
    // ivar: _zeroOrOneNamespaces: Boolean
    // ivar: _padding: Byte
    // ivar: _URI: MemorySegment
    // ivar: _prefixIndex: Long
}

// ── Category: NSDeprecated on NSXMLElement ─────────────────────────────────────────

fun NSXMLElement.setAttributesAsDictionary(attributes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAttributesAsDictionary:")
    ObjCRuntime.msgSend(null, this.ptr, sel, attributes)
}

