package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFilePromiseReceiver
 * Superclass: NSObject
 * Protocols: NSPasteboardReading
 */
open class NSFilePromiseReceiver(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFilePromiseReceiver") }
        
        /** @return NSArray<NSString *> * */
        fun readableDraggedTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("readableDraggedTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun receivePromisedFilesAtDestination_options_operationQueue_reader(destinationDir: MemorySegment, options: MemorySegment, operationQueue: MemorySegment, reader: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("receivePromisedFilesAtDestination:options:operationQueue:reader:")
        ObjCRuntime.msgSend(null, ptr, sel, destinationDir, options, operationQueue, reader)
    }
    
    // @property readableDraggedTypes
    /** @return NSArray<NSString *> * */
    open fun readableDraggedTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("readableDraggedTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property fileTypes
    /** @return NSArray<NSString *> * */
    open fun fileTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("fileTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property fileNames
    /** @return NSArray<NSString *> * */
    open fun fileNames(): MemorySegment {
        val sel = ObjCRuntime.sel("fileNames")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

