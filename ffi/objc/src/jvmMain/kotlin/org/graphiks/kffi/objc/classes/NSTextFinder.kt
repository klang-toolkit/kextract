package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextFinder
 * Superclass: NSObject
 * Protocols: NSCoding
 */
open class NSTextFinder(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextFinder") }
        
        fun drawIncrementalMatchHighlightInRect(rect: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("drawIncrementalMatchHighlightInRect:")
            ObjCRuntime.msgSend(null, _class, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun performAction(op: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performAction:")
        ObjCRuntime.msgSend(null, ptr, sel, op)
    }
    
    open fun validateAction(op: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("validateAction:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, op) as Boolean
    }
    
    open fun cancelFindIndicator(): Unit {
        val sel = ObjCRuntime.sel("cancelFindIndicator")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun noteClientStringWillChange(): Unit {
        val sel = ObjCRuntime.sel("noteClientStringWillChange")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property client
    /** @return id<NSTextFinderClient> */
    open fun client(): MemorySegment {
        val sel = ObjCRuntime.sel("client")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setClient(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setClient:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property findBarContainer
    /** @return id<NSTextFinderBarContainer> */
    open fun findBarContainer(): MemorySegment {
        val sel = ObjCRuntime.sel("findBarContainer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFindBarContainer(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFindBarContainer:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property findIndicatorNeedsUpdate
    open fun findIndicatorNeedsUpdate(): Boolean {
        val sel = ObjCRuntime.sel("findIndicatorNeedsUpdate")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setFindIndicatorNeedsUpdate(value: Boolean) {
        val sel = ObjCRuntime.sel("setFindIndicatorNeedsUpdate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property incrementalSearchingEnabled
    open fun isIncrementalSearchingEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isIncrementalSearchingEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIncrementalSearchingEnabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setIncrementalSearchingEnabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property incrementalSearchingShouldDimContentView
    open fun incrementalSearchingShouldDimContentView(): Boolean {
        val sel = ObjCRuntime.sel("incrementalSearchingShouldDimContentView")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIncrementalSearchingShouldDimContentView(value: Boolean) {
        val sel = ObjCRuntime.sel("setIncrementalSearchingShouldDimContentView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property incrementalMatchRanges
    /** @return NSArray<NSValue *> * */
    open fun incrementalMatchRanges(): MemorySegment {
        val sel = ObjCRuntime.sel("incrementalMatchRanges")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

