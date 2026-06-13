package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSURLConnectionDataDelegate
 * Inherits protocols: NSURLConnectionDelegate
 */
interface NSURLConnectionDataDelegate : NSURLConnectionDelegate {
    // @optional
    fun connection_willSendRequest_redirectResponse(connection: MemorySegment, request: MemorySegment, response: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'connection:willSendRequest:redirectResponse:' not implemented")
    
    // @optional
    fun connection_didReceiveResponse(connection: MemorySegment, response: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'connection:didReceiveResponse:' not implemented")
    
    // @optional
    fun connection_didReceiveData(connection: MemorySegment, `data`: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'connection:didReceiveData:' not implemented")
    
    // @optional
    fun connection_needNewBodyStream(connection: MemorySegment, request: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'connection:needNewBodyStream:' not implemented")
    
    // @optional
    fun connection_didSendBodyData_totalBytesWritten_totalBytesExpectedToWrite(connection: MemorySegment, bytesWritten: Long, totalBytesWritten: Long, totalBytesExpectedToWrite: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'connection:didSendBodyData:totalBytesWritten:totalBytesExpectedToWrite:' not implemented")
    
    // @optional
    fun connection_willCacheResponse(connection: MemorySegment, cachedResponse: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'connection:willCacheResponse:' not implemented")
    
    // @optional
    fun connectionDidFinishLoading(connection: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'connectionDidFinishLoading:' not implemented")
    
}

