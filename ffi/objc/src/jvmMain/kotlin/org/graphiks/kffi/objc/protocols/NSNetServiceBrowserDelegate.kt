package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSNetServiceBrowserDelegate
 * Inherits protocols: NSObject
 */
interface NSNetServiceBrowserDelegate {
    // @optional
    fun netServiceBrowserWillSearch(browser: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceBrowserWillSearch:' not implemented")
    
    // @optional
    fun netServiceBrowserDidStopSearch(browser: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceBrowserDidStopSearch:' not implemented")
    
    // @optional
    fun netServiceBrowser_didNotSearch(browser: MemorySegment, errorDict: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceBrowser:didNotSearch:' not implemented")
    
    // @optional
    fun netServiceBrowser_didFindDomain_moreComing(browser: MemorySegment, domainString: MemorySegment, moreComing: Boolean): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceBrowser:didFindDomain:moreComing:' not implemented")
    
    // @optional
    fun netServiceBrowser_didFindService_moreComing(browser: MemorySegment, service: MemorySegment, moreComing: Boolean): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceBrowser:didFindService:moreComing:' not implemented")
    
    // @optional
    fun netServiceBrowser_didRemoveDomain_moreComing(browser: MemorySegment, domainString: MemorySegment, moreComing: Boolean): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceBrowser:didRemoveDomain:moreComing:' not implemented")
    
    // @optional
    fun netServiceBrowser_didRemoveService_moreComing(browser: MemorySegment, service: MemorySegment, moreComing: Boolean): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'netServiceBrowser:didRemoveService:moreComing:' not implemented")
    
}

