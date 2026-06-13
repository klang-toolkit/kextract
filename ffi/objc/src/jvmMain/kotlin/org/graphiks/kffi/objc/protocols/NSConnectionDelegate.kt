package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSConnectionDelegate
 * Inherits protocols: NSObject
 */
interface NSConnectionDelegate {
    // @optional
    fun makeNewConnection_sender(conn: MemorySegment, ancestor: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'makeNewConnection:sender:' not implemented")
    
    // @optional
    fun connection_shouldMakeNewConnection(ancestor: MemorySegment, conn: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'connection:shouldMakeNewConnection:' not implemented")
    
    // @optional
    fun authenticationDataForComponents(components: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'authenticationDataForComponents:' not implemented")
    
    // @optional
    fun authenticateComponents_withData(components: MemorySegment, signature: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'authenticateComponents:withData:' not implemented")
    
    // @optional
    fun createConversationForConnection(conn: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'createConversationForConnection:' not implemented")
    
    // @optional
    fun connection_handleRequest(connection: MemorySegment, doreq: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'connection:handleRequest:' not implemented")
    
}

