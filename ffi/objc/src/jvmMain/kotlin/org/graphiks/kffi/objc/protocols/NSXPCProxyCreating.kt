package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSXPCProxyCreating
 */
interface NSXPCProxyCreating {
    fun remoteObjectProxy(): MemorySegment
    
    fun remoteObjectProxyWithErrorHandler(handler: MemorySegment): MemorySegment
    
    // @optional
    fun synchronousRemoteObjectProxyWithErrorHandler(handler: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'synchronousRemoteObjectProxyWithErrorHandler:' not implemented")
    
}

