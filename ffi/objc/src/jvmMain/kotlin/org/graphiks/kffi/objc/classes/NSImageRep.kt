package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSImageRep
 * Superclass: NSObject
 * Protocols: NSCopying, NSCoding
 */
open class NSImageRep(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSImageRep") }
        
        fun registerImageRepClass(imageRepClass: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("registerImageRepClass:")
            ObjCRuntime.msgSend(null, _class, sel, imageRepClass)
        }
        
        fun unregisterImageRepClass(imageRepClass: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("unregisterImageRepClass:")
            ObjCRuntime.msgSend(null, _class, sel, imageRepClass)
        }
        
        fun imageRepClassForFileType(type: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepClassForFileType:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, type) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun imageRepClassForFileType(type: String): MemorySegment = imageRepClassForFileType(ObjCRuntime.newNSString(Arena.global(), type))
        
        fun imageRepClassForPasteboardType(type: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepClassForPasteboardType:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, type) as MemorySegment
        }
        
        fun imageRepClassForType(type: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepClassForType:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, type) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun imageRepClassForType(type: String): MemorySegment = imageRepClassForType(ObjCRuntime.newNSString(Arena.global(), type))
        
        fun imageRepClassForData(`data`: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepClassForData:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, `data`) as MemorySegment
        }
        
        fun canInitWithData(`data`: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("canInitWithData:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, `data`) as Boolean
        }
        
        /** @return NSArray<NSString *> * */
        fun imageUnfilteredFileTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("imageUnfilteredFileTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSPasteboardType> * */
        fun imageUnfilteredPasteboardTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("imageUnfilteredPasteboardTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSString *> * */
        fun imageFileTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("imageFileTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSPasteboardType> * */
        fun imagePasteboardTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("imagePasteboardTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun canInitWithPasteboard(pasteboard: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("canInitWithPasteboard:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, pasteboard) as Boolean
        }
        
        /** @return NSArray<NSImageRep *> * */
        fun imageRepsWithContentsOfFile(filename: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepsWithContentsOfFile:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, filename) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun imageRepsWithContentsOfFile(filename: String): MemorySegment = imageRepsWithContentsOfFile(ObjCRuntime.newNSString(Arena.global(), filename))
        
        fun imageRepWithContentsOfFile(filename: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepWithContentsOfFile:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, filename) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun imageRepWithContentsOfFile(filename: String): MemorySegment = imageRepWithContentsOfFile(ObjCRuntime.newNSString(Arena.global(), filename))
        
        /** @return NSArray<NSImageRep *> * */
        fun imageRepsWithContentsOfURL(url: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepsWithContentsOfURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url) as MemorySegment
        }
        
        fun imageRepWithContentsOfURL(url: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepWithContentsOfURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url) as MemorySegment
        }
        
        /** @return NSArray<NSImageRep *> * */
        fun imageRepsWithPasteboard(pasteboard: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepsWithPasteboard:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, pasteboard) as MemorySegment
        }
        
        fun imageRepWithPasteboard(pasteboard: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageRepWithPasteboard:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, pasteboard) as MemorySegment
        }
        
        /** @return NSArray<Class> * */
        fun registeredImageRepClasses(): MemorySegment {
            val sel = ObjCRuntime.sel("registeredImageRepClasses")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSString *> * */
        fun imageUnfilteredTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("imageUnfilteredTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSString *> * */
        fun imageTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("imageTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
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
    
    open fun draw(): Boolean {
        val sel = ObjCRuntime.sel("draw")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun drawAtPoint(point: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("drawAtPoint:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as Boolean
    }
    
    open fun drawInRect(rect: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("drawInRect:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as Boolean
    }
    
    open fun drawInRect_fromRect_operation_fraction_respectFlipped_hints(dstSpacePortionRect: MemorySegment, srcSpacePortionRect: MemorySegment, op: MemorySegment, requestedAlpha: Double, respectContextIsFlipped: Boolean, hints: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("drawInRect:fromRect:operation:fraction:respectFlipped:hints:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(dstSpacePortionRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(srcSpacePortionRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), op, requestedAlpha, respectContextIsFlipped, hints) as Boolean
    }
    
    open fun CGImageForProposedRect_context_hints(proposedDestRect: MemorySegment, context: MemorySegment, hints: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("CGImageForProposedRect:context:hints:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, proposedDestRect, context, hints) as MemorySegment
    }
    
    // @property size
    open fun size(): MemorySegment {
        val sel = ObjCRuntime.sel("size")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property alpha
    open fun hasAlpha(): Boolean {
        val sel = ObjCRuntime.sel("hasAlpha")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAlpha(value: Boolean) {
        val sel = ObjCRuntime.sel("setAlpha:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property opaque
    open fun isOpaque(): Boolean {
        val sel = ObjCRuntime.sel("isOpaque")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setOpaque(value: Boolean) {
        val sel = ObjCRuntime.sel("setOpaque:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property colorSpaceName
    open fun colorSpaceName(): MemorySegment {
        val sel = ObjCRuntime.sel("colorSpaceName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setColorSpaceName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setColorSpaceName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property bitsPerSample
    open fun bitsPerSample(): Long {
        val sel = ObjCRuntime.sel("bitsPerSample")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setBitsPerSample(value: Long) {
        val sel = ObjCRuntime.sel("setBitsPerSample:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pixelsWide
    open fun pixelsWide(): Long {
        val sel = ObjCRuntime.sel("pixelsWide")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setPixelsWide(value: Long) {
        val sel = ObjCRuntime.sel("setPixelsWide:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pixelsHigh
    open fun pixelsHigh(): Long {
        val sel = ObjCRuntime.sel("pixelsHigh")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setPixelsHigh(value: Long) {
        val sel = ObjCRuntime.sel("setPixelsHigh:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property layoutDirection
    open fun layoutDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("layoutDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLayoutDirection(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLayoutDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property registeredImageRepClasses
    /** @return NSArray<Class> * */
    open fun registeredImageRepClasses(): MemorySegment {
        val sel = ObjCRuntime.sel("registeredImageRepClasses")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property imageUnfilteredTypes
    /** @return NSArray<NSString *> * */
    open fun imageUnfilteredTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("imageUnfilteredTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property imageTypes
    /** @return NSArray<NSString *> * */
    open fun imageTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("imageTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

