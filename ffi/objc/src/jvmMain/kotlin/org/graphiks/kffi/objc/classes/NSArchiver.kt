package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSArchiver
 * Superclass: NSCoder
 */
open class NSArchiver(override val ptr: MemorySegment) : NSCoder(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSArchiver") }
        
        fun archivedDataWithRootObject(rootObject: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("archivedDataWithRootObject:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, rootObject) as MemorySegment
        }
        
        fun archiveRootObject_toFile(rootObject: MemorySegment, path: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("archiveRootObject:toFile:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, rootObject, path) as Boolean
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun archiveRootObject_toFile(rootObject: MemorySegment, path: String): Boolean = archiveRootObject_toFile(rootObject, ObjCRuntime.newNSString(Arena.global(), path))
        
    }
    
    open fun initForWritingWithMutableData(mdata: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initForWritingWithMutableData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, mdata) as MemorySegment
    }
    
    open fun encodeRootObject(rootObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("encodeRootObject:")
        ObjCRuntime.msgSend(null, ptr, sel, rootObject)
    }
    
    open fun encodeConditionalObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("encodeConditionalObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun encodeClassName_intoClassName(trueName: MemorySegment, inArchiveName: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("encodeClassName:intoClassName:")
        ObjCRuntime.msgSend(null, ptr, sel, trueName, inArchiveName)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun encodeClassName_intoClassName(trueName: String, inArchiveName: String): Unit = encodeClassName_intoClassName(ObjCRuntime.newNSString(Arena.global(), trueName), ObjCRuntime.newNSString(Arena.global(), inArchiveName))
    
    open fun classNameEncodedForTrueClassName(trueName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("classNameEncodedForTrueClassName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, trueName) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun classNameEncodedForTrueClassNameAsString(trueName: MemorySegment): String = ObjCRuntime.toJavaString(classNameEncodedForTrueClassName(trueName))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun classNameEncodedForTrueClassName(trueName: String): MemorySegment = classNameEncodedForTrueClassName(ObjCRuntime.newNSString(Arena.global(), trueName))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun classNameEncodedForTrueClassNameAsString(trueName: String): String = ObjCRuntime.toJavaString(classNameEncodedForTrueClassName(ObjCRuntime.newNSString(Arena.global(), trueName)))
    
    open fun replaceObject_withObject(`object`: MemorySegment, newObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceObject:withObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`, newObject)
    }
    
    // @property archiverData
    open fun archiverData(): MemorySegment {
        val sel = ObjCRuntime.sel("archiverData")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: mdata: MemorySegment
    // ivar: pointerTable: MemorySegment
    // ivar: stringTable: MemorySegment
    // ivar: ids: MemorySegment
    // ivar: map: MemorySegment
    // ivar: replacementTable: MemorySegment
    // ivar: reserved: MemorySegment
}

