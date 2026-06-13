package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSocketPortNameServer
 * Superclass: NSPortNameServer
 */
open class NSSocketPortNameServer(override val ptr: MemorySegment) : NSPortNameServer(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSocketPortNameServer") }
        
        fun sharedInstance(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedInstance")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    override fun portForName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("portForName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    override fun portForName_host(name: MemorySegment, host: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("portForName:host:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, host) as MemorySegment
    }
    
    override fun registerPort_name(port: MemorySegment, name: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("registerPort:name:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, port, name) as Boolean
    }
    
    override fun removePortForName(name: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("removePortForName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, name) as Boolean
    }
    
    open fun portForName_host_nameServerPortNumber(name: MemorySegment, host: MemorySegment, portNumber: Short): MemorySegment {
        val sel = ObjCRuntime.sel("portForName:host:nameServerPortNumber:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, host, portNumber) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun portForName_host_nameServerPortNumber(name: String, host: String, portNumber: Short): MemorySegment = portForName_host_nameServerPortNumber(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), host), portNumber)
    
    open fun registerPort_name_nameServerPortNumber(port: MemorySegment, name: MemorySegment, portNumber: Short): Boolean {
        val sel = ObjCRuntime.sel("registerPort:name:nameServerPortNumber:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, port, name, portNumber) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun registerPort_name_nameServerPortNumber(port: MemorySegment, name: String, portNumber: Short): Boolean = registerPort_name_nameServerPortNumber(port, ObjCRuntime.newNSString(Arena.global(), name), portNumber)
    
    // @property defaultNameServerPortNumber
    open fun defaultNameServerPortNumber(): Short {
        val sel = ObjCRuntime.sel("defaultNameServerPortNumber")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, ptr, sel) as Short
    }
    open fun setDefaultNameServerPortNumber(value: Short) {
        val sel = ObjCRuntime.sel("setDefaultNameServerPortNumber:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

