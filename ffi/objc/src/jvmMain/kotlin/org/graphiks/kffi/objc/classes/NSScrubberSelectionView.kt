package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSScrubberSelectionView
 * Superclass: NSScrubberArrangedView
 */
open class NSScrubberSelectionView(override val ptr: MemorySegment) : NSScrubberArrangedView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSScrubberSelectionView") }
        
    }
    
}

