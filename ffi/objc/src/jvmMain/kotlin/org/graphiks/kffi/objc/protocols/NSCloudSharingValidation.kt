package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCloudSharingValidation
 * Inherits protocols: NSObject
 */
interface NSCloudSharingValidation {
    fun cloudShareForUserInterfaceItem(item: MemorySegment): MemorySegment
    
}

