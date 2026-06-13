package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSXPCConnection
 * Superclass: NSObject
 * Protocols: NSXPCProxyCreating
 */
open class NSXPCConnection(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSXPCConnection") }
        
        fun currentConnection(): MemorySegment {
            val sel = ObjCRuntime.sel("currentConnection")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithServiceName(serviceName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithServiceName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, serviceName) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithServiceName(serviceName: String): MemorySegment = initWithServiceName(ObjCRuntime.newNSString(Arena.global(), serviceName))
    
    open fun initWithMachServiceName_options(name: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMachServiceName:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, options) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithMachServiceName_options(name: String, options: MemorySegment): MemorySegment = initWithMachServiceName_options(ObjCRuntime.newNSString(Arena.global(), name), options)
    
    open fun initWithListenerEndpoint(endpoint: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithListenerEndpoint:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, endpoint) as MemorySegment
    }
    
    open fun remoteObjectProxyWithErrorHandler(handler: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("remoteObjectProxyWithErrorHandler:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, handler) as MemorySegment
    }
    
    open fun synchronousRemoteObjectProxyWithErrorHandler(handler: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("synchronousRemoteObjectProxyWithErrorHandler:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, handler) as MemorySegment
    }
    
    open fun resume(): Unit {
        val sel = ObjCRuntime.sel("resume")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun suspend(): Unit {
        val sel = ObjCRuntime.sel("suspend")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun activate(): Unit {
        val sel = ObjCRuntime.sel("activate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun invalidate(): Unit {
        val sel = ObjCRuntime.sel("invalidate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun scheduleSendBarrierBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scheduleSendBarrierBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun setCodeSigningRequirement(requirement: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setCodeSigningRequirement:")
        ObjCRuntime.msgSend(null, ptr, sel, requirement)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setCodeSigningRequirement(requirement: String): Unit = setCodeSigningRequirement(ObjCRuntime.newNSString(Arena.global(), requirement))
    
    // @property serviceName
    open fun serviceName(): MemorySegment {
        val sel = ObjCRuntime.sel("serviceName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun serviceNameAsString(): String = ObjCRuntime.toJavaString(serviceName())
    
    // @property endpoint
    open fun endpoint(): MemorySegment {
        val sel = ObjCRuntime.sel("endpoint")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property exportedInterface
    open fun exportedInterface(): MemorySegment {
        val sel = ObjCRuntime.sel("exportedInterface")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setExportedInterface(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setExportedInterface:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property exportedObject
    open fun exportedObject(): MemorySegment {
        val sel = ObjCRuntime.sel("exportedObject")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setExportedObject(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setExportedObject:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property remoteObjectInterface
    open fun remoteObjectInterface(): MemorySegment {
        val sel = ObjCRuntime.sel("remoteObjectInterface")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRemoteObjectInterface(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRemoteObjectInterface:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property remoteObjectProxy
    open fun remoteObjectProxy(): MemorySegment {
        val sel = ObjCRuntime.sel("remoteObjectProxy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property interruptionHandler
    open fun interruptionHandler(): MemorySegment {
        val sel = ObjCRuntime.sel("interruptionHandler")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInterruptionHandler(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInterruptionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property invalidationHandler
    open fun invalidationHandler(): MemorySegment {
        val sel = ObjCRuntime.sel("invalidationHandler")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInvalidationHandler(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInvalidationHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property auditSessionIdentifier
    open fun auditSessionIdentifier(): Int {
        val sel = ObjCRuntime.sel("auditSessionIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property processIdentifier
    open fun processIdentifier(): Int {
        val sel = ObjCRuntime.sel("processIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property effectiveUserIdentifier
    open fun effectiveUserIdentifier(): Int {
        val sel = ObjCRuntime.sel("effectiveUserIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property effectiveGroupIdentifier
    open fun effectiveGroupIdentifier(): Int {
        val sel = ObjCRuntime.sel("effectiveGroupIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
}

