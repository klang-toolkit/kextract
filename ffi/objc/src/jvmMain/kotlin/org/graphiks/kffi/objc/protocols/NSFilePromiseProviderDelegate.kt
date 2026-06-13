package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSFilePromiseProviderDelegate
 * Inherits protocols: NSObject
 */
interface NSFilePromiseProviderDelegate {
    fun filePromiseProvider_fileNameForType(filePromiseProvider: MemorySegment, fileType: MemorySegment): MemorySegment
    
    fun filePromiseProvider_writePromiseToURL_completionHandler(filePromiseProvider: MemorySegment, url: MemorySegment, completionHandler: MemorySegment): Unit
    
    // @optional
    fun operationQueueForFilePromiseProvider(filePromiseProvider: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'operationQueueForFilePromiseProvider:' not implemented")
    
}

