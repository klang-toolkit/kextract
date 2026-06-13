package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSNetServiceDelegate
 * Inherits protocols: NSObject
 */
interface NSNetServiceDelegate {
    // @optional
    fun netServiceWillPublish(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceWillPublish:' not implemented")
    
    // @optional
    fun netServiceDidPublish(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceDidPublish:' not implemented")
    
    // @optional
    fun netService_didNotPublish(sender: MemorySegment, errorDict: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netService:didNotPublish:' not implemented")
    
    // @optional
    fun netServiceWillResolve(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceWillResolve:' not implemented")
    
    // @optional
    fun netServiceDidResolveAddress(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceDidResolveAddress:' not implemented")
    
    // @optional
    fun netService_didNotResolve(sender: MemorySegment, errorDict: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netService:didNotResolve:' not implemented")
    
    // @optional
    fun netServiceDidStop(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceDidStop:' not implemented")
    
    // @optional
    fun netService_didUpdateTXTRecordData(sender: MemorySegment, `data`: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netService:didUpdateTXTRecordData:' not implemented")
    
    // @optional
    fun netService_didAcceptConnectionWithInputStream_outputStream(sender: MemorySegment, inputStream: MemorySegment, outputStream: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netService:didAcceptConnectionWithInputStream:outputStream:' not implemented")
    
}

