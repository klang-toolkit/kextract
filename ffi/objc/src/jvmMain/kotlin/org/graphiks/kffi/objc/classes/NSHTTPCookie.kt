package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSHTTPCookie
 * Superclass: NSObject
 */
open class NSHTTPCookie(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSHTTPCookie") }
        
        fun cookieWithProperties(properties: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("cookieWithProperties:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, properties) as MemorySegment
        }
        
        /** @return NSDictionary<NSString *,NSString *> * */
        fun requestHeaderFieldsWithCookies(cookies: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("requestHeaderFieldsWithCookies:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, cookies) as MemorySegment
        }
        
        /** @return NSArray<NSHTTPCookie *> * */
        fun cookiesWithResponseHeaderFields_forURL(headerFields: MemorySegment, URL: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("cookiesWithResponseHeaderFields:forURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, headerFields, URL) as MemorySegment
        }
        
    }
    
    open fun initWithProperties(properties: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithProperties:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, properties) as MemorySegment
    }
    
    // @property properties
    /** @return NSDictionary<NSHTTPCookiePropertyKey,id> * */
    open fun properties(): MemorySegment {
        val sel = ObjCRuntime.sel("properties")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property version
    open fun version(): Long {
        val sel = ObjCRuntime.sel("version")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun nameAsString(): String = ObjCRuntime.toJavaString(name())
    
    // @property value
    open fun value(): MemorySegment {
        val sel = ObjCRuntime.sel("value")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun valueAsString(): String = ObjCRuntime.toJavaString(value())
    
    // @property expiresDate
    open fun expiresDate(): MemorySegment {
        val sel = ObjCRuntime.sel("expiresDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sessionOnly
    open fun isSessionOnly(): Boolean {
        val sel = ObjCRuntime.sel("isSessionOnly")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property domain
    open fun domain(): MemorySegment {
        val sel = ObjCRuntime.sel("domain")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun domainAsString(): String = ObjCRuntime.toJavaString(domain())
    
    // @property path
    open fun path(): MemorySegment {
        val sel = ObjCRuntime.sel("path")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun pathAsString(): String = ObjCRuntime.toJavaString(path())
    
    // @property secure
    open fun isSecure(): Boolean {
        val sel = ObjCRuntime.sel("isSecure")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property HTTPOnly
    open fun isHTTPOnly(): Boolean {
        val sel = ObjCRuntime.sel("isHTTPOnly")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property comment
    open fun comment(): MemorySegment {
        val sel = ObjCRuntime.sel("comment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun commentAsString(): String = ObjCRuntime.toJavaString(comment())
    
    // @property commentURL
    open fun commentURL(): MemorySegment {
        val sel = ObjCRuntime.sel("commentURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property portList
    /** @return NSArray<NSNumber *> * */
    open fun portList(): MemorySegment {
        val sel = ObjCRuntime.sel("portList")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sameSitePolicy
    open fun sameSitePolicy(): MemorySegment {
        val sel = ObjCRuntime.sel("sameSitePolicy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _cookiePrivate: MemorySegment
}

