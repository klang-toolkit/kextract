package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSURLConnectionDelegate
 * Inherits protocols: NSObject
 */
interface NSURLConnectionDelegate {
    // @optional
    fun connection_didFailWithError(connection: MemorySegment, error: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'connection:didFailWithError:' not implemented")
    
    // @optional
    fun connectionShouldUseCredentialStorage(connection: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'connectionShouldUseCredentialStorage:' not implemented")
    
    // @optional
    fun connection_willSendRequestForAuthenticationChallenge(connection: MemorySegment, challenge: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'connection:willSendRequestForAuthenticationChallenge:' not implemented")
    
    // @optional
    fun connection_canAuthenticateAgainstProtectionSpace(connection: MemorySegment, protectionSpace: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'connection:canAuthenticateAgainstProtectionSpace:' not implemented")
    
    // @optional
    fun connection_didReceiveAuthenticationChallenge(connection: MemorySegment, challenge: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'connection:didReceiveAuthenticationChallenge:' not implemented")
    
    // @optional
    fun connection_didCancelAuthenticationChallenge(connection: MemorySegment, challenge: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'connection:didCancelAuthenticationChallenge:' not implemented")
    
}

