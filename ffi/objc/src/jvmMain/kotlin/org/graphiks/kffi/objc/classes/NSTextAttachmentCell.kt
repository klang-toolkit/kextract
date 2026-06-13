package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextAttachmentCell
 * Superclass: NSCell
 * Protocols: NSTextAttachmentCell
 */
open class NSTextAttachmentCell(override val ptr: MemorySegment) : NSCell(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextAttachmentCell") }
        
    }
    
}

