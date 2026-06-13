package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSKeyedUnarchiverDelegate
 * Inherits protocols: NSObject
 */
interface NSKeyedUnarchiverDelegate {
    // @optional
    fun unarchiver_cannotDecodeObjectOfClassName_originalClasses(unarchiver: MemorySegment, name: MemorySegment, classNames: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'unarchiver:cannotDecodeObjectOfClassName:originalClasses:' not implemented")
    
    // @optional
    fun unarchiver_didDecodeObject(unarchiver: MemorySegment, `object`: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'unarchiver:didDecodeObject:' not implemented")
    
    // @optional
    fun unarchiver_willReplaceObject_withObject(unarchiver: MemorySegment, `object`: MemorySegment, newObject: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'unarchiver:willReplaceObject:withObject:' not implemented")
    
    // @optional
    fun unarchiverWillFinish(unarchiver: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'unarchiverWillFinish:' not implemented")
    
    // @optional
    fun unarchiverDidFinish(unarchiver: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'unarchiverDidFinish:' not implemented")
    
}

