package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSharingServiceDelegate
 * Inherits protocols: NSObject
 */
interface NSSharingServiceDelegate {
    // @optional
    fun sharingService_willShareItems(sharingService: MemorySegment, items: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'sharingService:willShareItems:' not implemented")
    
    // @optional
    fun sharingService_didFailToShareItems_error(sharingService: MemorySegment, items: MemorySegment, error: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'sharingService:didFailToShareItems:error:' not implemented")
    
    // @optional
    fun sharingService_didShareItems(sharingService: MemorySegment, items: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'sharingService:didShareItems:' not implemented")
    
    // @optional
    fun sharingService_sourceFrameOnScreenForShareItem(sharingService: MemorySegment, item: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'sharingService:sourceFrameOnScreenForShareItem:' not implemented")
    
    // @optional
    fun sharingService_transitionImageForShareItem_contentRect(sharingService: MemorySegment, item: MemorySegment, contentRect: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'sharingService:transitionImageForShareItem:contentRect:' not implemented")
    
    // @optional
    fun sharingService_sourceWindowForShareItems_sharingContentScope(sharingService: MemorySegment, items: MemorySegment, sharingContentScope: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'sharingService:sourceWindowForShareItems:sharingContentScope:' not implemented")
    
    // @optional
    fun anchoringViewForSharingService_showRelativeToRect_preferredEdge(sharingService: MemorySegment, positioningRect: MemorySegment, preferredEdge: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'anchoringViewForSharingService:showRelativeToRect:preferredEdge:' not implemented")
    
}

