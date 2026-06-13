package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCloudSharingServiceDelegate
 * Inherits protocols: NSSharingServiceDelegate
 */
interface NSCloudSharingServiceDelegate : NSSharingServiceDelegate {
    // @optional
    fun sharingService_didCompleteForItems_error(sharingService: MemorySegment, items: MemorySegment, error: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'sharingService:didCompleteForItems:error:' not implemented")
    
    // @optional
    fun optionsForSharingService_shareProvider(cloudKitSharingService: MemorySegment, provider: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'optionsForSharingService:shareProvider:' not implemented")
    
    // @optional
    fun sharingService_didSaveShare(sharingService: MemorySegment, share: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'sharingService:didSaveShare:' not implemented")
    
    // @optional
    fun sharingService_didStopSharing(sharingService: MemorySegment, share: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'sharingService:didStopSharing:' not implemented")
    
}

