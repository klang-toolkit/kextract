package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextSelectionNavigation
 * Superclass: NSObject
 */
open class NSTextSelectionNavigation(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextSelectionNavigation") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithDataSource(dataSource: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDataSource:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, dataSource) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun flushLayoutCache(): Unit {
        val sel = ObjCRuntime.sel("flushLayoutCache")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun destinationSelectionForTextSelection_direction_destination_extending_confined(textSelection: MemorySegment, direction: MemorySegment, destination: MemorySegment, extending: Boolean, confined: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("destinationSelectionForTextSelection:direction:destination:extending:confined:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textSelection, direction, destination, extending, confined) as MemorySegment
    }
    
    /** @return NSArray<NSTextSelection *> * */
    open fun textSelectionsInteractingAtPoint_inContainerAtLocation_anchors_modifiers_selecting_bounds(point: MemorySegment, containerLocation: MemorySegment, anchors: MemorySegment, modifiers: MemorySegment, selecting: Boolean, bounds: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("textSelectionsInteractingAtPoint:inContainerAtLocation:anchors:modifiers:selecting:bounds:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), containerLocation, anchors, modifiers, selecting, ObjCRuntime.ObjCStructArg(bounds, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun textSelectionForSelectionGranularity_enclosingTextSelection(selectionGranularity: MemorySegment, textSelection: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("textSelectionForSelectionGranularity:enclosingTextSelection:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, selectionGranularity, textSelection) as MemorySegment
    }
    
    open fun textSelectionForSelectionGranularity_enclosingPoint_inContainerAtLocation(selectionGranularity: MemorySegment, point: MemorySegment, location: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("textSelectionForSelectionGranularity:enclosingPoint:inContainerAtLocation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, selectionGranularity, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), location) as MemorySegment
    }
    
    /** @return id<NSTextLocation> */
    open fun resolvedInsertionLocationForTextSelection_writingDirection(textSelection: MemorySegment, writingDirection: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("resolvedInsertionLocationForTextSelection:writingDirection:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textSelection, writingDirection) as MemorySegment
    }
    
    /** @return NSArray<NSTextRange *> * */
    open fun deletionRangesForTextSelection_direction_destination_allowsDecomposition(textSelection: MemorySegment, direction: MemorySegment, destination: MemorySegment, allowsDecomposition: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("deletionRangesForTextSelection:direction:destination:allowsDecomposition:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textSelection, direction, destination, allowsDecomposition) as MemorySegment
    }
    
    // @property textSelectionDataSource
    /** @return id<NSTextSelectionDataSource> */
    open fun textSelectionDataSource(): MemorySegment {
        val sel = ObjCRuntime.sel("textSelectionDataSource")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property allowsNonContiguousRanges
    open fun allowsNonContiguousRanges(): Boolean {
        val sel = ObjCRuntime.sel("allowsNonContiguousRanges")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsNonContiguousRanges(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsNonContiguousRanges:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rotatesCoordinateSystemForLayoutOrientation
    open fun rotatesCoordinateSystemForLayoutOrientation(): Boolean {
        val sel = ObjCRuntime.sel("rotatesCoordinateSystemForLayoutOrientation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setRotatesCoordinateSystemForLayoutOrientation(value: Boolean) {
        val sel = ObjCRuntime.sel("setRotatesCoordinateSystemForLayoutOrientation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

