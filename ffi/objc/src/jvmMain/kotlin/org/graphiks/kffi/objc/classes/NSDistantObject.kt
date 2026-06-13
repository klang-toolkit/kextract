package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDistantObject
 * Superclass: NSProxy
 * Protocols: NSCoding
 */
open class NSDistantObject(override val ptr: MemorySegment) : NSProxy(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDistantObject") }
        
        fun proxyWithTarget_connection(target: MemorySegment, connection: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("proxyWithTarget:connection:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, target, connection) as MemorySegment
        }
        
        fun proxyWithLocal_connection(target: MemorySegment, connection: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("proxyWithLocal:connection:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, target, connection) as MemorySegment
        }
        
    }
    
    open fun initWithTarget_connection(target: MemorySegment, connection: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTarget:connection:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, target, connection) as MemorySegment
    }
    
    open fun initWithLocal_connection(target: MemorySegment, connection: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithLocal:connection:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, target, connection) as MemorySegment
    }
    
    open fun initWithCoder(inCoder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inCoder) as MemorySegment
    }
    
    open fun setProtocolForProxy(proto: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setProtocolForProxy:")
        ObjCRuntime.msgSend(null, ptr, sel, proto)
    }
    
    // @property connectionForProxy
    open fun connectionForProxy(): MemorySegment {
        val sel = ObjCRuntime.sel("connectionForProxy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _knownSelectors: MemorySegment
    // ivar: _wireCount: Long
    // ivar: _refCount: Long
    // ivar: _proto: MemorySegment
    // ivar: ___2: Short
    // ivar: ___1: Byte
    // ivar: _wireType: Byte
    // ivar: _remoteClass: MemorySegment
}

