package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCollectionViewSectionHeaderView
 * Inherits protocols: NSCollectionViewElement
 */
interface NSCollectionViewSectionHeaderView : NSCollectionViewElement {
    // @optional
    fun sectionCollapseButton(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'sectionCollapseButton' not implemented")
    
    // @optional
    fun setSectionCollapseButton(sectionCollapseButton: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'setSectionCollapseButton:' not implemented")
    
}

