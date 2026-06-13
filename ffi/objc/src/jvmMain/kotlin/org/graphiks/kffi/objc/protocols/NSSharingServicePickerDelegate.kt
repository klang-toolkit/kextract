package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSharingServicePickerDelegate
 * Inherits protocols: NSObject
 */
interface NSSharingServicePickerDelegate {
    /** @return NSArray<NSSharingService *> * */
    // @optional
    fun sharingServicePicker_sharingServicesForItems_proposedSharingServices(sharingServicePicker: MemorySegment, items: MemorySegment, proposedServices: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'sharingServicePicker:sharingServicesForItems:proposedSharingServices:' not implemented")
    
    /** @return id<NSSharingServiceDelegate> */
    // @optional
    fun sharingServicePicker_delegateForSharingService(sharingServicePicker: MemorySegment, sharingService: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'sharingServicePicker:delegateForSharingService:' not implemented")
    
    // @optional
    fun sharingServicePicker_didChooseSharingService(sharingServicePicker: MemorySegment, service: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'sharingServicePicker:didChooseSharingService:' not implemented")
    
    /** @return NSArray<NSSharingCollaborationModeRestriction *> * */
    // @optional
    fun sharingServicePickerCollaborationModeRestrictions(sharingServicePicker: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'sharingServicePickerCollaborationModeRestrictions:' not implemented")
    
}

