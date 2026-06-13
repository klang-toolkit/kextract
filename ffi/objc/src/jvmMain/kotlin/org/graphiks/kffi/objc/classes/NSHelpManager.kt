package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSHelpManager
 * Superclass: NSObject
 */
open class NSHelpManager(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSHelpManager") }
        
        fun sharedHelpManager(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedHelpManager")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun isContextHelpModeActive(): Boolean {
            val sel = ObjCRuntime.sel("isContextHelpModeActive")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun setContextHelpModeActive(contextHelpModeActive: Boolean): Unit {
            val sel = ObjCRuntime.sel("setContextHelpModeActive:")
            ObjCRuntime.msgSend(null, _class, sel, contextHelpModeActive)
        }
        
    }
    
    open fun setContextHelp_forObject(attrString: MemorySegment, `object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setContextHelp:forObject:")
        ObjCRuntime.msgSend(null, ptr, sel, attrString, `object`)
    }
    
    open fun removeContextHelpForObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeContextHelpForObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun contextHelpForObject(`object`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("contextHelpForObject:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `object`) as MemorySegment
    }
    
    open fun showContextHelpForObject_locationHint(`object`: MemorySegment, pt: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("showContextHelpForObject:locationHint:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, `object`, ObjCRuntime.ObjCStructArg(pt, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as Boolean
    }
    
    open fun openHelpAnchor_inBook(anchor: MemorySegment, book: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("openHelpAnchor:inBook:")
        ObjCRuntime.msgSend(null, ptr, sel, anchor, book)
    }
    
    open fun findString_inBook(query: MemorySegment, book: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("findString:inBook:")
        ObjCRuntime.msgSend(null, ptr, sel, query, book)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun findString_inBook(query: String, book: MemorySegment): Unit = findString_inBook(ObjCRuntime.newNSString(Arena.global(), query), book)
    
    open fun registerBooksInBundle(bundle: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("registerBooksInBundle:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, bundle) as Boolean
    }
    
    // @property sharedHelpManager
    open fun sharedHelpManager(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedHelpManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property contextHelpModeActive
    open fun isContextHelpModeActive(): Boolean {
        val sel = ObjCRuntime.sel("isContextHelpModeActive")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setContextHelpModeActive(value: Boolean) {
        val sel = ObjCRuntime.sel("setContextHelpModeActive:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

