package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAttributedStringMarkdownSourcePosition
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSAttributedStringMarkdownSourcePosition(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAttributedStringMarkdownSourcePosition") }
        
    }
    
    open fun initWithStartLine_startColumn_endLine_endColumn(startLine: Long, startColumn: Long, endLine: Long, endColumn: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithStartLine:startColumn:endLine:endColumn:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, startLine, startColumn, endLine, endColumn) as MemorySegment
    }
    
    open fun rangeInString(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("rangeInString:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel, string) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun rangeInString(string: String): MemorySegment = rangeInString(ObjCRuntime.newNSString(Arena.global(), string))
    
    // @property startLine
    open fun startLine(): Long {
        val sel = ObjCRuntime.sel("startLine")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property startColumn
    open fun startColumn(): Long {
        val sel = ObjCRuntime.sel("startColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property endLine
    open fun endLine(): Long {
        val sel = ObjCRuntime.sel("endLine")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property endColumn
    open fun endColumn(): Long {
        val sel = ObjCRuntime.sel("endColumn")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
}

