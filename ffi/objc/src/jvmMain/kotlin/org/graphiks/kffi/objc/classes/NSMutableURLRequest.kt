package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMutableURLRequest
 * Superclass: NSURLRequest
 */
open class NSMutableURLRequest(override val ptr: MemorySegment) : NSURLRequest(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMutableURLRequest") }
        
    }
    
    // @property URL
    override fun URL(): MemorySegment {
        val sel = ObjCRuntime.sel("URL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setURL(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setURL:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property cachePolicy
    override fun cachePolicy(): MemorySegment {
        val sel = ObjCRuntime.sel("cachePolicy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCachePolicy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCachePolicy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property timeoutInterval
    override fun timeoutInterval(): Double {
        val sel = ObjCRuntime.sel("timeoutInterval")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setTimeoutInterval(value: Double) {
        val sel = ObjCRuntime.sel("setTimeoutInterval:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property mainDocumentURL
    override fun mainDocumentURL(): MemorySegment {
        val sel = ObjCRuntime.sel("mainDocumentURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMainDocumentURL(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMainDocumentURL:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property networkServiceType
    override fun networkServiceType(): MemorySegment {
        val sel = ObjCRuntime.sel("networkServiceType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setNetworkServiceType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setNetworkServiceType:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsCellularAccess
    override fun allowsCellularAccess(): Boolean {
        val sel = ObjCRuntime.sel("allowsCellularAccess")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsCellularAccess(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsCellularAccess:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsExpensiveNetworkAccess
    override fun allowsExpensiveNetworkAccess(): Boolean {
        val sel = ObjCRuntime.sel("allowsExpensiveNetworkAccess")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsExpensiveNetworkAccess(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsExpensiveNetworkAccess:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsConstrainedNetworkAccess
    override fun allowsConstrainedNetworkAccess(): Boolean {
        val sel = ObjCRuntime.sel("allowsConstrainedNetworkAccess")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsConstrainedNetworkAccess(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsConstrainedNetworkAccess:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsUltraConstrainedNetworkAccess
    override fun allowsUltraConstrainedNetworkAccess(): Boolean {
        val sel = ObjCRuntime.sel("allowsUltraConstrainedNetworkAccess")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsUltraConstrainedNetworkAccess(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsUltraConstrainedNetworkAccess:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property assumesHTTP3Capable
    override fun assumesHTTP3Capable(): Boolean {
        val sel = ObjCRuntime.sel("assumesHTTP3Capable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAssumesHTTP3Capable(value: Boolean) {
        val sel = ObjCRuntime.sel("setAssumesHTTP3Capable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property attribution
    override fun attribution(): MemorySegment {
        val sel = ObjCRuntime.sel("attribution")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttribution(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttribution:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property requiresDNSSECValidation
    override fun requiresDNSSECValidation(): Boolean {
        val sel = ObjCRuntime.sel("requiresDNSSECValidation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setRequiresDNSSECValidation(value: Boolean) {
        val sel = ObjCRuntime.sel("setRequiresDNSSECValidation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsPersistentDNS
    override fun allowsPersistentDNS(): Boolean {
        val sel = ObjCRuntime.sel("allowsPersistentDNS")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsPersistentDNS(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsPersistentDNS:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property cookiePartitionIdentifier
    override fun cookiePartitionIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("cookiePartitionIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCookiePartitionIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCookiePartitionIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSMutableHTTPURLRequest on NSMutableURLRequest ─────────────────────────────────────────

fun NSMutableURLRequest.setValue_forHTTPHeaderField(value: MemorySegment, field: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setValue:forHTTPHeaderField:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, field)
}

fun NSMutableURLRequest.addValue_forHTTPHeaderField(value: MemorySegment, field: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("addValue:forHTTPHeaderField:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, field)
}

fun NSMutableURLRequest.HTTPMethod(): MemorySegment {
    val sel = ObjCRuntime.sel("HTTPMethod")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSMutableURLRequest.setHTTPMethod(HTTPMethod: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setHTTPMethod:")
    ObjCRuntime.msgSend(null, this.ptr, sel, HTTPMethod)
}

/** @return NSDictionary<NSString *,NSString *> * */
fun NSMutableURLRequest.allHTTPHeaderFields(): MemorySegment {
    val sel = ObjCRuntime.sel("allHTTPHeaderFields")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSMutableURLRequest.setAllHTTPHeaderFields(allHTTPHeaderFields: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAllHTTPHeaderFields:")
    ObjCRuntime.msgSend(null, this.ptr, sel, allHTTPHeaderFields)
}

fun NSMutableURLRequest.HTTPBody(): MemorySegment {
    val sel = ObjCRuntime.sel("HTTPBody")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSMutableURLRequest.setHTTPBody(HTTPBody: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setHTTPBody:")
    ObjCRuntime.msgSend(null, this.ptr, sel, HTTPBody)
}

fun NSMutableURLRequest.HTTPBodyStream(): MemorySegment {
    val sel = ObjCRuntime.sel("HTTPBodyStream")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSMutableURLRequest.setHTTPBodyStream(HTTPBodyStream: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setHTTPBodyStream:")
    ObjCRuntime.msgSend(null, this.ptr, sel, HTTPBodyStream)
}

fun NSMutableURLRequest.HTTPShouldHandleCookies(): Boolean {
    val sel = ObjCRuntime.sel("HTTPShouldHandleCookies")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSMutableURLRequest.setHTTPShouldHandleCookies(HTTPShouldHandleCookies: Boolean): Unit {
    val sel = ObjCRuntime.sel("setHTTPShouldHandleCookies:")
    ObjCRuntime.msgSend(null, this.ptr, sel, HTTPShouldHandleCookies)
}

fun NSMutableURLRequest.HTTPShouldUsePipelining(): Boolean {
    val sel = ObjCRuntime.sel("HTTPShouldUsePipelining")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSMutableURLRequest.setHTTPShouldUsePipelining(HTTPShouldUsePipelining: Boolean): Unit {
    val sel = ObjCRuntime.sel("setHTTPShouldUsePipelining:")
    ObjCRuntime.msgSend(null, this.ptr, sel, HTTPShouldUsePipelining)
}

