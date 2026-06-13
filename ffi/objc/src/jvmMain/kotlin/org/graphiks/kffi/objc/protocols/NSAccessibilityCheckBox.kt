package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityCheckBox
 * Inherits protocols: NSAccessibilityButton
 */
interface NSAccessibilityCheckBox : NSAccessibilityButton {
    fun accessibilityValue(): MemorySegment
    
}

