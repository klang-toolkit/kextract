package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextPreview
 * Superclass: NSObject
 */
open class NSTextPreview(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextPreview") }
        
    }
    
    open fun initWithSnapshotImage_presentationFrame_candidateRects(snapshotImage: MemorySegment, presentationFrame: MemorySegment, candidateRects: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSnapshotImage:presentationFrame:candidateRects:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, snapshotImage, ObjCRuntime.ObjCStructArg(presentationFrame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), candidateRects) as MemorySegment
    }
    
    open fun initWithSnapshotImage_presentationFrame(snapshotImage: MemorySegment, presentationFrame: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSnapshotImage:presentationFrame:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, snapshotImage, ObjCRuntime.ObjCStructArg(presentationFrame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property previewImage
    open fun previewImage(): MemorySegment {
        val sel = ObjCRuntime.sel("previewImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property presentationFrame
    open fun presentationFrame(): MemorySegment {
        val sel = ObjCRuntime.sel("presentationFrame")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property candidateRects
    /** @return NSArray<NSValue *> * */
    open fun candidateRects(): MemorySegment {
        val sel = ObjCRuntime.sel("candidateRects")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

