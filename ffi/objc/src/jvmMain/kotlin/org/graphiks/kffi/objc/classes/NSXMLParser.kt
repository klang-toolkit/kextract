package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSXMLParser
 * Superclass: NSObject
 */
open class NSXMLParser(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSXMLParser") }
        
    }
    
    open fun initWithContentsOfURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentsOfURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun initWithData(`data`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`) as MemorySegment
    }
    
    open fun initWithStream(stream: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithStream:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, stream) as MemorySegment
    }
    
    open fun parse(): Boolean {
        val sel = ObjCRuntime.sel("parse")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun abortParsing(): Unit {
        val sel = ObjCRuntime.sel("abortParsing")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property delegate
    /** @return id<NSXMLParserDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property shouldProcessNamespaces
    open fun shouldProcessNamespaces(): Boolean {
        val sel = ObjCRuntime.sel("shouldProcessNamespaces")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShouldProcessNamespaces(value: Boolean) {
        val sel = ObjCRuntime.sel("setShouldProcessNamespaces:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property shouldReportNamespacePrefixes
    open fun shouldReportNamespacePrefixes(): Boolean {
        val sel = ObjCRuntime.sel("shouldReportNamespacePrefixes")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShouldReportNamespacePrefixes(value: Boolean) {
        val sel = ObjCRuntime.sel("setShouldReportNamespacePrefixes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property externalEntityResolvingPolicy
    open fun externalEntityResolvingPolicy(): MemorySegment {
        val sel = ObjCRuntime.sel("externalEntityResolvingPolicy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setExternalEntityResolvingPolicy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setExternalEntityResolvingPolicy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowedExternalEntityURLs
    /** @return NSSet<NSURL *> * */
    open fun allowedExternalEntityURLs(): MemorySegment {
        val sel = ObjCRuntime.sel("allowedExternalEntityURLs")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAllowedExternalEntityURLs(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAllowedExternalEntityURLs:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property parserError
    open fun parserError(): MemorySegment {
        val sel = ObjCRuntime.sel("parserError")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property shouldResolveExternalEntities
    open fun shouldResolveExternalEntities(): Boolean {
        val sel = ObjCRuntime.sel("shouldResolveExternalEntities")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShouldResolveExternalEntities(value: Boolean) {
        val sel = ObjCRuntime.sel("setShouldResolveExternalEntities:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSXMLParserLocatorAdditions on NSXMLParser ─────────────────────────────────────────

fun NSXMLParser.publicID(): MemorySegment {
    val sel = ObjCRuntime.sel("publicID")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSXMLParser.systemID(): MemorySegment {
    val sel = ObjCRuntime.sel("systemID")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSXMLParser.lineNumber(): Long {
    val sel = ObjCRuntime.sel("lineNumber")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSXMLParser.columnNumber(): Long {
    val sel = ObjCRuntime.sel("columnNumber")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

