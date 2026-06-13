package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSharingServicePickerToolbarItemDelegate
 * Inherits protocols: NSSharingServicePickerDelegate
 */
interface NSSharingServicePickerToolbarItemDelegate : NSSharingServicePickerDelegate {
    fun itemsForSharingServicePickerToolbarItem(pickerToolbarItem: MemorySegment): MemorySegment
    
}

