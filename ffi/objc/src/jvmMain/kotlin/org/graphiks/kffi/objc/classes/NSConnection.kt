package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSConnection
 * Superclass: NSObject
 */
open class NSConnection(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSConnection") }
        
        /** @return NSArray<NSConnection *> * */
        fun allConnections(): MemorySegment {
            val sel = ObjCRuntime.sel("allConnections")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun defaultConnection(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultConnection")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun connectionWithRegisteredName_host(name: MemorySegment, hostName: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("connectionWithRegisteredName:host:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, hostName) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun connectionWithRegisteredName_host(name: String, hostName: String): MemorySegment = connectionWithRegisteredName_host(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), hostName))
        
        fun connectionWithRegisteredName_host_usingNameServer(name: MemorySegment, hostName: MemorySegment, server: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("connectionWithRegisteredName:host:usingNameServer:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, hostName, server) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun connectionWithRegisteredName_host_usingNameServer(name: String, hostName: String, server: MemorySegment): MemorySegment = connectionWithRegisteredName_host_usingNameServer(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), hostName), server)
        
        fun rootProxyForConnectionWithRegisteredName_host(name: MemorySegment, hostName: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("rootProxyForConnectionWithRegisteredName:host:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, hostName) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun rootProxyForConnectionWithRegisteredName_host(name: String, hostName: String): MemorySegment = rootProxyForConnectionWithRegisteredName_host(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), hostName))
        
        fun rootProxyForConnectionWithRegisteredName_host_usingNameServer(name: MemorySegment, hostName: MemorySegment, server: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("rootProxyForConnectionWithRegisteredName:host:usingNameServer:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, hostName, server) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun rootProxyForConnectionWithRegisteredName_host_usingNameServer(name: String, hostName: String, server: MemorySegment): MemorySegment = rootProxyForConnectionWithRegisteredName_host_usingNameServer(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), hostName), server)
        
        fun serviceConnectionWithName_rootObject_usingNameServer(name: MemorySegment, root: MemorySegment, server: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("serviceConnectionWithName:rootObject:usingNameServer:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, root, server) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun serviceConnectionWithName_rootObject_usingNameServer(name: String, root: MemorySegment, server: MemorySegment): MemorySegment = serviceConnectionWithName_rootObject_usingNameServer(ObjCRuntime.newNSString(Arena.global(), name), root, server)
        
        fun serviceConnectionWithName_rootObject(name: MemorySegment, root: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("serviceConnectionWithName:rootObject:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, root) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun serviceConnectionWithName_rootObject(name: String, root: MemorySegment): MemorySegment = serviceConnectionWithName_rootObject(ObjCRuntime.newNSString(Arena.global(), name), root)
        
        fun connectionWithReceivePort_sendPort(receivePort: MemorySegment, sendPort: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("connectionWithReceivePort:sendPort:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, receivePort, sendPort) as MemorySegment
        }
        
        fun currentConversation(): MemorySegment {
            val sel = ObjCRuntime.sel("currentConversation")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun invalidate(): Unit {
        val sel = ObjCRuntime.sel("invalidate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun addRequestMode(rmode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addRequestMode:")
        ObjCRuntime.msgSend(null, ptr, sel, rmode)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun addRequestMode(rmode: String): Unit = addRequestMode(ObjCRuntime.newNSString(Arena.global(), rmode))
    
    open fun removeRequestMode(rmode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeRequestMode:")
        ObjCRuntime.msgSend(null, ptr, sel, rmode)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun removeRequestMode(rmode: String): Unit = removeRequestMode(ObjCRuntime.newNSString(Arena.global(), rmode))
    
    open fun registerName(name: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("registerName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, name) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun registerName(name: String): Boolean = registerName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun registerName_withNameServer(name: MemorySegment, server: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("registerName:withNameServer:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, name, server) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun registerName_withNameServer(name: String, server: MemorySegment): Boolean = registerName_withNameServer(ObjCRuntime.newNSString(Arena.global(), name), server)
    
    open fun initWithReceivePort_sendPort(receivePort: MemorySegment, sendPort: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithReceivePort:sendPort:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, receivePort, sendPort) as MemorySegment
    }
    
    open fun enableMultipleThreads(): Unit {
        val sel = ObjCRuntime.sel("enableMultipleThreads")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun addRunLoop(runloop: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addRunLoop:")
        ObjCRuntime.msgSend(null, ptr, sel, runloop)
    }
    
    open fun removeRunLoop(runloop: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeRunLoop:")
        ObjCRuntime.msgSend(null, ptr, sel, runloop)
    }
    
    open fun runInNewThread(): Unit {
        val sel = ObjCRuntime.sel("runInNewThread")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun dispatchWithComponents(components: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("dispatchWithComponents:")
        ObjCRuntime.msgSend(null, ptr, sel, components)
    }
    
    // @property statistics
    /** @return NSDictionary<NSString *,NSNumber *> * */
    open fun statistics(): MemorySegment {
        val sel = ObjCRuntime.sel("statistics")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property requestTimeout
    open fun requestTimeout(): Double {
        val sel = ObjCRuntime.sel("requestTimeout")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setRequestTimeout(value: Double) {
        val sel = ObjCRuntime.sel("setRequestTimeout:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property replyTimeout
    open fun replyTimeout(): Double {
        val sel = ObjCRuntime.sel("replyTimeout")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setReplyTimeout(value: Double) {
        val sel = ObjCRuntime.sel("setReplyTimeout:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rootObject
    open fun rootObject(): MemorySegment {
        val sel = ObjCRuntime.sel("rootObject")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRootObject(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRootObject:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSConnectionDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property independentConversationQueueing
    open fun independentConversationQueueing(): Boolean {
        val sel = ObjCRuntime.sel("independentConversationQueueing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIndependentConversationQueueing(value: Boolean) {
        val sel = ObjCRuntime.sel("setIndependentConversationQueueing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property valid
    open fun isValid(): Boolean {
        val sel = ObjCRuntime.sel("isValid")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property rootProxy
    open fun rootProxy(): MemorySegment {
        val sel = ObjCRuntime.sel("rootProxy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property requestModes
    /** @return NSArray<NSString *> * */
    open fun requestModes(): MemorySegment {
        val sel = ObjCRuntime.sel("requestModes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sendPort
    open fun sendPort(): MemorySegment {
        val sel = ObjCRuntime.sel("sendPort")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property receivePort
    open fun receivePort(): MemorySegment {
        val sel = ObjCRuntime.sel("receivePort")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property multipleThreadsEnabled
    open fun multipleThreadsEnabled(): Boolean {
        val sel = ObjCRuntime.sel("multipleThreadsEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property remoteObjects
    open fun remoteObjects(): MemorySegment {
        val sel = ObjCRuntime.sel("remoteObjects")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property localObjects
    open fun localObjects(): MemorySegment {
        val sel = ObjCRuntime.sel("localObjects")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: receivePort: MemorySegment
    // ivar: sendPort: MemorySegment
    // ivar: delegate: MemorySegment
    // ivar: busy: Int
    // ivar: localProxyCount: Int
    // ivar: waitCount: Int
    // ivar: delayedRL: MemorySegment
    // ivar: statistics: MemorySegment
    // ivar: isDead: Byte
    // ivar: isValid: Byte
    // ivar: wantsInvalid: Byte
    // ivar: authGen: Byte
    // ivar: authCheck: Byte
    // ivar: _reserved1: Byte
    // ivar: _reserved2: Byte
    // ivar: doRequest: Byte
    // ivar: isQueueing: Byte
    // ivar: isMulti: Byte
    // ivar: invalidateRP: Byte
    // ivar: ___1: MemorySegment
    // ivar: ___2: MemorySegment
    // ivar: runLoops: MemorySegment
    // ivar: requestModes: MemorySegment
    // ivar: rootObject: MemorySegment
    // ivar: registerInfo: MemorySegment
    // ivar: replMode: MemorySegment
    // ivar: classInfoImported: MemorySegment
    // ivar: releasedProxies: MemorySegment
    // ivar: reserved: MemorySegment
}

