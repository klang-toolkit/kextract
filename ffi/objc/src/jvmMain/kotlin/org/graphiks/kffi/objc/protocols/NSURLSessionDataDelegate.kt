package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSURLSessionDataDelegate
 * Inherits protocols: NSURLSessionTaskDelegate
 */
interface NSURLSessionDataDelegate : NSURLSessionTaskDelegate {
    // @optional
    fun URLSession_dataTask_didReceiveResponse_completionHandler(session: MemorySegment, dataTask: MemorySegment, response: MemorySegment, completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:dataTask:didReceiveResponse:completionHandler:' not implemented")
    
    // @optional
    fun URLSession_dataTask_didBecomeDownloadTask(session: MemorySegment, dataTask: MemorySegment, downloadTask: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:dataTask:didBecomeDownloadTask:' not implemented")
    
    // @optional
    fun URLSession_dataTask_didBecomeStreamTask(session: MemorySegment, dataTask: MemorySegment, streamTask: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:dataTask:didBecomeStreamTask:' not implemented")
    
    // @optional
    fun URLSession_dataTask_didReceiveData(session: MemorySegment, dataTask: MemorySegment, `data`: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:dataTask:didReceiveData:' not implemented")
    
    // @optional
    fun URLSession_dataTask_willCacheResponse_completionHandler(session: MemorySegment, dataTask: MemorySegment, proposedResponse: MemorySegment, completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:dataTask:willCacheResponse:completionHandler:' not implemented")
    
}

