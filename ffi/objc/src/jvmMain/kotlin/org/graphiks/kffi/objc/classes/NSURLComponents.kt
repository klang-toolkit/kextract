package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLComponents
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSURLComponents(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLComponents") }
        
        fun componentsWithURL_resolvingAgainstBaseURL(url: MemorySegment, resolve: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("componentsWithURL:resolvingAgainstBaseURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url, resolve) as MemorySegment
        }
        
        fun componentsWithString(URLString: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("componentsWithString:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, URLString) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun componentsWithString(URLString: String): MemorySegment = componentsWithString(ObjCRuntime.newNSString(Arena.global(), URLString))
        
        fun componentsWithString_encodingInvalidCharacters(URLString: MemorySegment, encodingInvalidCharacters: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("componentsWithString:encodingInvalidCharacters:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, URLString, encodingInvalidCharacters) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun componentsWithString_encodingInvalidCharacters(URLString: String, encodingInvalidCharacters: Boolean): MemorySegment = componentsWithString_encodingInvalidCharacters(ObjCRuntime.newNSString(Arena.global(), URLString), encodingInvalidCharacters)
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithURL_resolvingAgainstBaseURL(url: MemorySegment, resolve: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithURL:resolvingAgainstBaseURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, resolve) as MemorySegment
    }
    
    open fun initWithString(URLString: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, URLString) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString(URLString: String): MemorySegment = initWithString(ObjCRuntime.newNSString(Arena.global(), URLString))
    
    open fun initWithString_encodingInvalidCharacters(URLString: MemorySegment, encodingInvalidCharacters: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:encodingInvalidCharacters:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, URLString, encodingInvalidCharacters) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString_encodingInvalidCharacters(URLString: String, encodingInvalidCharacters: Boolean): MemorySegment = initWithString_encodingInvalidCharacters(ObjCRuntime.newNSString(Arena.global(), URLString), encodingInvalidCharacters)
    
    open fun URLRelativeToURL(baseURL: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("URLRelativeToURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, baseURL) as MemorySegment
    }
    
    // @property URL
    open fun URL(): MemorySegment {
        val sel = ObjCRuntime.sel("URL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property string
    open fun string(): MemorySegment {
        val sel = ObjCRuntime.sel("string")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun stringAsString(): String = ObjCRuntime.toJavaString(string())
    
    // @property scheme
    open fun scheme(): MemorySegment {
        val sel = ObjCRuntime.sel("scheme")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScheme(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScheme:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun schemeAsString(): String = ObjCRuntime.toJavaString(scheme())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setScheme(value: String) = setScheme(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property user
    open fun user(): MemorySegment {
        val sel = ObjCRuntime.sel("user")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUser(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUser:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun userAsString(): String = ObjCRuntime.toJavaString(user())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setUser(value: String) = setUser(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property password
    open fun password(): MemorySegment {
        val sel = ObjCRuntime.sel("password")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPassword(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPassword:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun passwordAsString(): String = ObjCRuntime.toJavaString(password())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPassword(value: String) = setPassword(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property host
    open fun host(): MemorySegment {
        val sel = ObjCRuntime.sel("host")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHost(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHost:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun hostAsString(): String = ObjCRuntime.toJavaString(host())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setHost(value: String) = setHost(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property port
    open fun port(): MemorySegment {
        val sel = ObjCRuntime.sel("port")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPort(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPort:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property path
    open fun path(): MemorySegment {
        val sel = ObjCRuntime.sel("path")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun pathAsString(): String = ObjCRuntime.toJavaString(path())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPath(value: String) = setPath(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property query
    open fun query(): MemorySegment {
        val sel = ObjCRuntime.sel("query")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setQuery(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setQuery:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun queryAsString(): String = ObjCRuntime.toJavaString(query())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setQuery(value: String) = setQuery(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property fragment
    open fun fragment(): MemorySegment {
        val sel = ObjCRuntime.sel("fragment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFragment(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFragment:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun fragmentAsString(): String = ObjCRuntime.toJavaString(fragment())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setFragment(value: String) = setFragment(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property percentEncodedUser
    open fun percentEncodedUser(): MemorySegment {
        val sel = ObjCRuntime.sel("percentEncodedUser")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPercentEncodedUser(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPercentEncodedUser:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun percentEncodedUserAsString(): String = ObjCRuntime.toJavaString(percentEncodedUser())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPercentEncodedUser(value: String) = setPercentEncodedUser(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property percentEncodedPassword
    open fun percentEncodedPassword(): MemorySegment {
        val sel = ObjCRuntime.sel("percentEncodedPassword")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPercentEncodedPassword(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPercentEncodedPassword:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun percentEncodedPasswordAsString(): String = ObjCRuntime.toJavaString(percentEncodedPassword())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPercentEncodedPassword(value: String) = setPercentEncodedPassword(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property percentEncodedHost
    open fun percentEncodedHost(): MemorySegment {
        val sel = ObjCRuntime.sel("percentEncodedHost")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPercentEncodedHost(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPercentEncodedHost:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun percentEncodedHostAsString(): String = ObjCRuntime.toJavaString(percentEncodedHost())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPercentEncodedHost(value: String) = setPercentEncodedHost(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property percentEncodedPath
    open fun percentEncodedPath(): MemorySegment {
        val sel = ObjCRuntime.sel("percentEncodedPath")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPercentEncodedPath(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPercentEncodedPath:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun percentEncodedPathAsString(): String = ObjCRuntime.toJavaString(percentEncodedPath())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPercentEncodedPath(value: String) = setPercentEncodedPath(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property percentEncodedQuery
    open fun percentEncodedQuery(): MemorySegment {
        val sel = ObjCRuntime.sel("percentEncodedQuery")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPercentEncodedQuery(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPercentEncodedQuery:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun percentEncodedQueryAsString(): String = ObjCRuntime.toJavaString(percentEncodedQuery())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPercentEncodedQuery(value: String) = setPercentEncodedQuery(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property percentEncodedFragment
    open fun percentEncodedFragment(): MemorySegment {
        val sel = ObjCRuntime.sel("percentEncodedFragment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPercentEncodedFragment(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPercentEncodedFragment:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun percentEncodedFragmentAsString(): String = ObjCRuntime.toJavaString(percentEncodedFragment())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPercentEncodedFragment(value: String) = setPercentEncodedFragment(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property encodedHost
    open fun encodedHost(): MemorySegment {
        val sel = ObjCRuntime.sel("encodedHost")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setEncodedHost(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEncodedHost:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun encodedHostAsString(): String = ObjCRuntime.toJavaString(encodedHost())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setEncodedHost(value: String) = setEncodedHost(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property rangeOfScheme
    open fun rangeOfScheme(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfScheme")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property rangeOfUser
    open fun rangeOfUser(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfUser")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property rangeOfPassword
    open fun rangeOfPassword(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfPassword")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property rangeOfHost
    open fun rangeOfHost(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfHost")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property rangeOfPort
    open fun rangeOfPort(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfPort")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property rangeOfPath
    open fun rangeOfPath(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfPath")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property rangeOfQuery
    open fun rangeOfQuery(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfQuery")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property rangeOfFragment
    open fun rangeOfFragment(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeOfFragment")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property queryItems
    /** @return NSArray<NSURLQueryItem *> * */
    open fun queryItems(): MemorySegment {
        val sel = ObjCRuntime.sel("queryItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setQueryItems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setQueryItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property percentEncodedQueryItems
    /** @return NSArray<NSURLQueryItem *> * */
    open fun percentEncodedQueryItems(): MemorySegment {
        val sel = ObjCRuntime.sel("percentEncodedQueryItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPercentEncodedQueryItems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPercentEncodedQueryItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

