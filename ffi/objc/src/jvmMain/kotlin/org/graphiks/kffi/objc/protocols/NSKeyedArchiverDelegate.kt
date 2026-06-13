package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSKeyedArchiverDelegate
 * Inherits protocols: NSObject
 */
interface NSKeyedArchiverDelegate {
    // @optional
    fun archiver_willEncodeObject(archiver: MemorySegment, `object`: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'archiver:willEncodeObject:' not implemented")
    
    // @optional
    fun archiver_didEncodeObject(archiver: MemorySegment, `object`: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'archiver:didEncodeObject:' not implemented")
    
    // @optional
    fun archiver_willReplaceObject_withObject(archiver: MemorySegment, `object`: MemorySegment, newObject: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'archiver:willReplaceObject:withObject:' not implemented")
    
    // @optional
    fun archiverWillFinish(archiver: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'archiverWillFinish:' not implemented")
    
    // @optional
    fun archiverDidFinish(archiver: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'archiverDidFinish:' not implemented")
    
}

