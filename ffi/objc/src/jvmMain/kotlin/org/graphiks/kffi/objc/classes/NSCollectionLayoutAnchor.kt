package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionLayoutAnchor
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSCollectionLayoutAnchor(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionLayoutAnchor") }
        
        fun layoutAnchorWithEdges(edges: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("layoutAnchorWithEdges:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, edges) as MemorySegment
        }
        
        fun layoutAnchorWithEdges_absoluteOffset(edges: MemorySegment, absoluteOffset: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("layoutAnchorWithEdges:absoluteOffset:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, edges, ObjCRuntime.ObjCStructArg(absoluteOffset, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
        }
        
        fun layoutAnchorWithEdges_fractionalOffset(edges: MemorySegment, fractionalOffset: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("layoutAnchorWithEdges:fractionalOffset:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, edges, ObjCRuntime.ObjCStructArg(fractionalOffset, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
        }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property edges
    open fun edges(): MemorySegment {
        val sel = ObjCRuntime.sel("edges")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property offset
    open fun offset(): MemorySegment {
        val sel = ObjCRuntime.sel("offset")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel) as MemorySegment
    }
    
    // @property isAbsoluteOffset
    open fun isAbsoluteOffset(): Boolean {
        val sel = ObjCRuntime.sel("isAbsoluteOffset")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property isFractionalOffset
    open fun isFractionalOffset(): Boolean {
        val sel = ObjCRuntime.sel("isFractionalOffset")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

