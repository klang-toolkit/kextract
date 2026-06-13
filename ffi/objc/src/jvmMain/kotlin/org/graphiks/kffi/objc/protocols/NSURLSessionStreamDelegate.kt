package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSURLSessionStreamDelegate
 * Inherits protocols: NSURLSessionTaskDelegate
 */
interface NSURLSessionStreamDelegate : NSURLSessionTaskDelegate {
    // @optional
    fun URLSession_readClosedForStreamTask(session: MemorySegment, streamTask: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:readClosedForStreamTask:' not implemented")
    
    // @optional
    fun URLSession_writeClosedForStreamTask(session: MemorySegment, streamTask: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:writeClosedForStreamTask:' not implemented")
    
    // @optional
    fun URLSession_betterRouteDiscoveredForStreamTask(session: MemorySegment, streamTask: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:betterRouteDiscoveredForStreamTask:' not implemented")
    
    // @optional
    fun URLSession_streamTask_didBecomeInputStream_outputStream(session: MemorySegment, streamTask: MemorySegment, inputStream: MemorySegment, outputStream: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:streamTask:didBecomeInputStream:outputStream:' not implemented")
    
}

