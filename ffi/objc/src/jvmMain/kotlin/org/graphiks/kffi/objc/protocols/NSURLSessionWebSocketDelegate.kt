package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSURLSessionWebSocketDelegate
 * Inherits protocols: NSURLSessionTaskDelegate
 */
interface NSURLSessionWebSocketDelegate : NSURLSessionTaskDelegate {
    // @optional
    fun URLSession_webSocketTask_didOpenWithProtocol(session: MemorySegment, webSocketTask: MemorySegment, protocol: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:webSocketTask:didOpenWithProtocol:' not implemented")
    
    // @optional
    fun URLSession_webSocketTask_didCloseWithCode_reason(session: MemorySegment, webSocketTask: MemorySegment, closeCode: MemorySegment, reason: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:webSocketTask:didCloseWithCode:reason:' not implemented")
    
}

