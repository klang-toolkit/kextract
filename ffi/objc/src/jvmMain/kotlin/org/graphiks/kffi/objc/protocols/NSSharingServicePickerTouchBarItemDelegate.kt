package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSharingServicePickerTouchBarItemDelegate
 * Inherits protocols: NSSharingServicePickerDelegate
 */
interface NSSharingServicePickerTouchBarItemDelegate : NSSharingServicePickerDelegate {
    fun itemsForSharingServicePickerTouchBarItem(pickerTouchBarItem: MemorySegment): MemorySegment
    
}

