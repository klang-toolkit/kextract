package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextAttachmentContainer
 * Inherits protocols: NSObject
 */
interface NSTextAttachmentContainer {
    fun imageForBounds_textContainer_characterIndex(imageBounds: MemorySegment, textContainer: MemorySegment, charIndex: Long): MemorySegment
    
    fun attachmentBoundsForTextContainer_proposedLineFragment_glyphPosition_characterIndex(textContainer: MemorySegment, lineFrag: MemorySegment, position: MemorySegment, charIndex: Long): MemorySegment
    
}

