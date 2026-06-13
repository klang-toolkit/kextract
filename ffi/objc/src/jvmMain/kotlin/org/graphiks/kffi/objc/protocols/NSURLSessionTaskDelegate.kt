package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSURLSessionTaskDelegate
 * Inherits protocols: NSURLSessionDelegate
 */
interface NSURLSessionTaskDelegate : NSURLSessionDelegate {
    // @optional
    fun URLSession_didCreateTask(session: MemorySegment, task: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:didCreateTask:' not implemented")
    
    // @optional
    fun URLSession_task_willBeginDelayedRequest_completionHandler(session: MemorySegment, task: MemorySegment, request: MemorySegment, completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:task:willBeginDelayedRequest:completionHandler:' not implemented")
    
    // @optional
    fun URLSession_taskIsWaitingForConnectivity(session: MemorySegment, task: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:taskIsWaitingForConnectivity:' not implemented")
    
    // @optional
    fun URLSession_task_willPerformHTTPRedirection_newRequest_completionHandler(session: MemorySegment, task: MemorySegment, response: MemorySegment, request: MemorySegment, completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:task:willPerformHTTPRedirection:newRequest:completionHandler:' not implemented")
    
    // @optional
    fun URLSession_task_didReceiveChallenge_completionHandler(session: MemorySegment, task: MemorySegment, challenge: MemorySegment, completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:task:didReceiveChallenge:completionHandler:' not implemented")
    
    // @optional
    fun URLSession_task_needNewBodyStream(session: MemorySegment, task: MemorySegment, completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:task:needNewBodyStream:' not implemented")
    
    // @optional
    fun URLSession_task_needNewBodyStreamFromOffset_completionHandler(session: MemorySegment, task: MemorySegment, offset: Long, completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:task:needNewBodyStreamFromOffset:completionHandler:' not implemented")
    
    // @optional
    fun URLSession_task_didSendBodyData_totalBytesSent_totalBytesExpectedToSend(session: MemorySegment, task: MemorySegment, bytesSent: Long, totalBytesSent: Long, totalBytesExpectedToSend: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:task:didSendBodyData:totalBytesSent:totalBytesExpectedToSend:' not implemented")
    
    // @optional
    fun URLSession_task_didReceiveInformationalResponse(session: MemorySegment, task: MemorySegment, response: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:task:didReceiveInformationalResponse:' not implemented")
    
    // @optional
    fun URLSession_task_didFinishCollectingMetrics(session: MemorySegment, task: MemorySegment, metrics: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:task:didFinishCollectingMetrics:' not implemented")
    
    // @optional
    fun URLSession_task_didCompleteWithError(session: MemorySegment, task: MemorySegment, error: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'URLSession:task:didCompleteWithError:' not implemented")
    
}

