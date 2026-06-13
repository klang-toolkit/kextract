package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLSessionConfiguration
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSURLSessionConfiguration(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLSessionConfiguration") }
        
        fun backgroundSessionConfigurationWithIdentifier(identifier: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("backgroundSessionConfigurationWithIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun backgroundSessionConfigurationWithIdentifier(identifier: String): MemorySegment = backgroundSessionConfigurationWithIdentifier(ObjCRuntime.newNSString(Arena.global(), identifier))
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun defaultSessionConfiguration(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultSessionConfiguration")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun ephemeralSessionConfiguration(): MemorySegment {
            val sel = ObjCRuntime.sel("ephemeralSessionConfiguration")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property defaultSessionConfiguration
    open fun defaultSessionConfiguration(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultSessionConfiguration")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property ephemeralSessionConfiguration
    open fun ephemeralSessionConfiguration(): MemorySegment {
        val sel = ObjCRuntime.sel("ephemeralSessionConfiguration")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property identifier
    open fun identifier(): MemorySegment {
        val sel = ObjCRuntime.sel("identifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun identifierAsString(): String = ObjCRuntime.toJavaString(identifier())
    
    // @property requestCachePolicy
    open fun requestCachePolicy(): MemorySegment {
        val sel = ObjCRuntime.sel("requestCachePolicy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRequestCachePolicy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRequestCachePolicy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property timeoutIntervalForRequest
    open fun timeoutIntervalForRequest(): Double {
        val sel = ObjCRuntime.sel("timeoutIntervalForRequest")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setTimeoutIntervalForRequest(value: Double) {
        val sel = ObjCRuntime.sel("setTimeoutIntervalForRequest:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property timeoutIntervalForResource
    open fun timeoutIntervalForResource(): Double {
        val sel = ObjCRuntime.sel("timeoutIntervalForResource")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setTimeoutIntervalForResource(value: Double) {
        val sel = ObjCRuntime.sel("setTimeoutIntervalForResource:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property networkServiceType
    open fun networkServiceType(): MemorySegment {
        val sel = ObjCRuntime.sel("networkServiceType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setNetworkServiceType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setNetworkServiceType:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsCellularAccess
    open fun allowsCellularAccess(): Boolean {
        val sel = ObjCRuntime.sel("allowsCellularAccess")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsCellularAccess(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsCellularAccess:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsExpensiveNetworkAccess
    open fun allowsExpensiveNetworkAccess(): Boolean {
        val sel = ObjCRuntime.sel("allowsExpensiveNetworkAccess")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsExpensiveNetworkAccess(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsExpensiveNetworkAccess:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsConstrainedNetworkAccess
    open fun allowsConstrainedNetworkAccess(): Boolean {
        val sel = ObjCRuntime.sel("allowsConstrainedNetworkAccess")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsConstrainedNetworkAccess(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsConstrainedNetworkAccess:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsUltraConstrainedNetworkAccess
    open fun allowsUltraConstrainedNetworkAccess(): Boolean {
        val sel = ObjCRuntime.sel("allowsUltraConstrainedNetworkAccess")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsUltraConstrainedNetworkAccess(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsUltraConstrainedNetworkAccess:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property requiresDNSSECValidation
    open fun requiresDNSSECValidation(): Boolean {
        val sel = ObjCRuntime.sel("requiresDNSSECValidation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setRequiresDNSSECValidation(value: Boolean) {
        val sel = ObjCRuntime.sel("setRequiresDNSSECValidation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property waitsForConnectivity
    open fun waitsForConnectivity(): Boolean {
        val sel = ObjCRuntime.sel("waitsForConnectivity")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setWaitsForConnectivity(value: Boolean) {
        val sel = ObjCRuntime.sel("setWaitsForConnectivity:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property discretionary
    open fun isDiscretionary(): Boolean {
        val sel = ObjCRuntime.sel("isDiscretionary")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDiscretionary(value: Boolean) {
        val sel = ObjCRuntime.sel("setDiscretionary:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sharedContainerIdentifier
    open fun sharedContainerIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedContainerIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSharedContainerIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSharedContainerIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun sharedContainerIdentifierAsString(): String = ObjCRuntime.toJavaString(sharedContainerIdentifier())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setSharedContainerIdentifier(value: String) = setSharedContainerIdentifier(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property sessionSendsLaunchEvents
    open fun sessionSendsLaunchEvents(): Boolean {
        val sel = ObjCRuntime.sel("sessionSendsLaunchEvents")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSessionSendsLaunchEvents(value: Boolean) {
        val sel = ObjCRuntime.sel("setSessionSendsLaunchEvents:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property connectionProxyDictionary
    open fun connectionProxyDictionary(): MemorySegment {
        val sel = ObjCRuntime.sel("connectionProxyDictionary")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setConnectionProxyDictionary(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setConnectionProxyDictionary:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property TLSMinimumSupportedProtocol
    open fun TLSMinimumSupportedProtocol(): MemorySegment {
        val sel = ObjCRuntime.sel("TLSMinimumSupportedProtocol")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTLSMinimumSupportedProtocol(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTLSMinimumSupportedProtocol:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property TLSMaximumSupportedProtocol
    open fun TLSMaximumSupportedProtocol(): MemorySegment {
        val sel = ObjCRuntime.sel("TLSMaximumSupportedProtocol")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTLSMaximumSupportedProtocol(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTLSMaximumSupportedProtocol:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property TLSMinimumSupportedProtocolVersion
    open fun TLSMinimumSupportedProtocolVersion(): MemorySegment {
        val sel = ObjCRuntime.sel("TLSMinimumSupportedProtocolVersion")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTLSMinimumSupportedProtocolVersion(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTLSMinimumSupportedProtocolVersion:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property TLSMaximumSupportedProtocolVersion
    open fun TLSMaximumSupportedProtocolVersion(): MemorySegment {
        val sel = ObjCRuntime.sel("TLSMaximumSupportedProtocolVersion")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTLSMaximumSupportedProtocolVersion(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTLSMaximumSupportedProtocolVersion:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property HTTPShouldUsePipelining
    open fun HTTPShouldUsePipelining(): Boolean {
        val sel = ObjCRuntime.sel("HTTPShouldUsePipelining")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHTTPShouldUsePipelining(value: Boolean) {
        val sel = ObjCRuntime.sel("setHTTPShouldUsePipelining:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property HTTPShouldSetCookies
    open fun HTTPShouldSetCookies(): Boolean {
        val sel = ObjCRuntime.sel("HTTPShouldSetCookies")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHTTPShouldSetCookies(value: Boolean) {
        val sel = ObjCRuntime.sel("setHTTPShouldSetCookies:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property HTTPCookieAcceptPolicy
    open fun HTTPCookieAcceptPolicy(): MemorySegment {
        val sel = ObjCRuntime.sel("HTTPCookieAcceptPolicy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHTTPCookieAcceptPolicy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHTTPCookieAcceptPolicy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property HTTPAdditionalHeaders
    open fun HTTPAdditionalHeaders(): MemorySegment {
        val sel = ObjCRuntime.sel("HTTPAdditionalHeaders")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHTTPAdditionalHeaders(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHTTPAdditionalHeaders:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property HTTPMaximumConnectionsPerHost
    open fun HTTPMaximumConnectionsPerHost(): Long {
        val sel = ObjCRuntime.sel("HTTPMaximumConnectionsPerHost")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setHTTPMaximumConnectionsPerHost(value: Long) {
        val sel = ObjCRuntime.sel("setHTTPMaximumConnectionsPerHost:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property HTTPCookieStorage
    open fun HTTPCookieStorage(): MemorySegment {
        val sel = ObjCRuntime.sel("HTTPCookieStorage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHTTPCookieStorage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHTTPCookieStorage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property URLCredentialStorage
    open fun URLCredentialStorage(): MemorySegment {
        val sel = ObjCRuntime.sel("URLCredentialStorage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setURLCredentialStorage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setURLCredentialStorage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property URLCache
    open fun URLCache(): MemorySegment {
        val sel = ObjCRuntime.sel("URLCache")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setURLCache(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setURLCache:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property shouldUseExtendedBackgroundIdleMode
    open fun shouldUseExtendedBackgroundIdleMode(): Boolean {
        val sel = ObjCRuntime.sel("shouldUseExtendedBackgroundIdleMode")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShouldUseExtendedBackgroundIdleMode(value: Boolean) {
        val sel = ObjCRuntime.sel("setShouldUseExtendedBackgroundIdleMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property protocolClasses
    /** @return NSArray<Class> * */
    open fun protocolClasses(): MemorySegment {
        val sel = ObjCRuntime.sel("protocolClasses")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setProtocolClasses(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setProtocolClasses:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property multipathServiceType
    open fun multipathServiceType(): MemorySegment {
        val sel = ObjCRuntime.sel("multipathServiceType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMultipathServiceType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMultipathServiceType:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesClassicLoadingMode
    open fun usesClassicLoadingMode(): Boolean {
        val sel = ObjCRuntime.sel("usesClassicLoadingMode")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesClassicLoadingMode(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesClassicLoadingMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property enablesEarlyData
    open fun enablesEarlyData(): Boolean {
        val sel = ObjCRuntime.sel("enablesEarlyData")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEnablesEarlyData(value: Boolean) {
        val sel = ObjCRuntime.sel("setEnablesEarlyData:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSURLSessionDeprecated on NSURLSessionConfiguration ─────────────────────────────────────────

// Class method: +[NSURLSessionConfiguration backgroundSessionConfiguration:]
fun NSURLSessionConfiguration_backgroundSessionConfiguration(identifier: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("backgroundSessionConfiguration:")
    val cls = ObjCRuntime.getClass("NSURLSessionConfiguration")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, identifier) as MemorySegment
}

