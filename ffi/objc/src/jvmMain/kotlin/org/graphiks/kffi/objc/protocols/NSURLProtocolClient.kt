package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSURLProtocolClient
 * Inherits protocols: NSObject
 */
interface NSURLProtocolClient {
    fun URLProtocol_wasRedirectedToRequest_redirectResponse(protocol: MemorySegment, request: MemorySegment, redirectResponse: MemorySegment): Unit
    
    fun URLProtocol_cachedResponseIsValid(protocol: MemorySegment, cachedResponse: MemorySegment): Unit
    
    fun URLProtocol_didReceiveResponse_cacheStoragePolicy(protocol: MemorySegment, response: MemorySegment, policy: MemorySegment): Unit
    
    fun URLProtocol_didLoadData(protocol: MemorySegment, `data`: MemorySegment): Unit
    
    fun URLProtocolDidFinishLoading(protocol: MemorySegment): Unit
    
    fun URLProtocol_didFailWithError(protocol: MemorySegment, error: MemorySegment): Unit
    
    fun URLProtocol_didReceiveAuthenticationChallenge(protocol: MemorySegment, challenge: MemorySegment): Unit
    
    fun URLProtocol_didCancelAuthenticationChallenge(protocol: MemorySegment, challenge: MemorySegment): Unit
    
}

