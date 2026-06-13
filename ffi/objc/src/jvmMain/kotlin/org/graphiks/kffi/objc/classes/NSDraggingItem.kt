package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDraggingItem
 * Superclass: NSObject
 */
open class NSDraggingItem(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDraggingItem") }
        
    }
    
    open fun initWithPasteboardWriter(pasteboardWriter: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPasteboardWriter:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pasteboardWriter) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun setDraggingFrame_contents(frame: MemorySegment, contents: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setDraggingFrame:contents:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(frame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), contents)
    }
    
    // @property item
    open fun item(): MemorySegment {
        val sel = ObjCRuntime.sel("item")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property draggingFrame
    open fun draggingFrame(): MemorySegment {
        val sel = ObjCRuntime.sel("draggingFrame")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    open fun setDraggingFrame(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDraggingFrame:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    // @property imageComponentsProvider
    /** @return NSArray<NSDraggingImageComponent *> * _Nonnull (^)(void) */
    open fun imageComponentsProvider(): MemorySegment {
        val sel = ObjCRuntime.sel("imageComponentsProvider")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setImageComponentsProvider(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setImageComponentsProvider:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property imageComponents
    /** @return NSArray<NSDraggingImageComponent *> * */
    open fun imageComponents(): MemorySegment {
        val sel = ObjCRuntime.sel("imageComponents")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

