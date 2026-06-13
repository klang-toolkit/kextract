package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnarchiver
 * Superclass: NSCoder
 */
open class NSUnarchiver(override val ptr: MemorySegment) : NSCoder(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnarchiver") }
        
        fun unarchiveObjectWithData(`data`: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("unarchiveObjectWithData:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, `data`) as MemorySegment
        }
        
        fun unarchiveObjectWithFile(path: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("unarchiveObjectWithFile:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, path) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun unarchiveObjectWithFile(path: String): MemorySegment = unarchiveObjectWithFile(ObjCRuntime.newNSString(Arena.global(), path))
        
        fun decodeClassName_asClassName(inArchiveName: MemorySegment, trueName: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("decodeClassName:asClassName:")
            ObjCRuntime.msgSend(null, _class, sel, inArchiveName, trueName)
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun decodeClassName_asClassName(inArchiveName: String, trueName: String): Unit = decodeClassName_asClassName(ObjCRuntime.newNSString(Arena.global(), inArchiveName), ObjCRuntime.newNSString(Arena.global(), trueName))
        
        fun classNameDecodedForArchiveClassName(inArchiveName: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("classNameDecodedForArchiveClassName:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, inArchiveName) as MemorySegment
        }
        
        /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
        fun classNameDecodedForArchiveClassNameAsString(inArchiveName: MemorySegment): String = ObjCRuntime.toJavaString(classNameDecodedForArchiveClassName(inArchiveName))
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun classNameDecodedForArchiveClassName(inArchiveName: String): MemorySegment = classNameDecodedForArchiveClassName(ObjCRuntime.newNSString(Arena.global(), inArchiveName))
        
        /** Convenience overload — [String] parameters and [String] return type. */
        fun classNameDecodedForArchiveClassNameAsString(inArchiveName: String): String = ObjCRuntime.toJavaString(classNameDecodedForArchiveClassName(ObjCRuntime.newNSString(Arena.global(), inArchiveName)))
        
    }
    
    open fun initForReadingWithData(`data`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initForReadingWithData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`) as MemorySegment
    }
    
    open fun setObjectZone(zone: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setObjectZone:")
        ObjCRuntime.msgSend(null, ptr, sel, zone)
    }
    
    open fun objectZone(): MemorySegment {
        val sel = ObjCRuntime.sel("objectZone")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun decodeClassName_asClassName(inArchiveName: MemorySegment, trueName: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("decodeClassName:asClassName:")
        ObjCRuntime.msgSend(null, ptr, sel, inArchiveName, trueName)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun decodeClassName_asClassName(inArchiveName: String, trueName: String): Unit = decodeClassName_asClassName(ObjCRuntime.newNSString(Arena.global(), inArchiveName), ObjCRuntime.newNSString(Arena.global(), trueName))
    
    open fun classNameDecodedForArchiveClassName(inArchiveName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("classNameDecodedForArchiveClassName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inArchiveName) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun classNameDecodedForArchiveClassNameAsString(inArchiveName: MemorySegment): String = ObjCRuntime.toJavaString(classNameDecodedForArchiveClassName(inArchiveName))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun classNameDecodedForArchiveClassName(inArchiveName: String): MemorySegment = classNameDecodedForArchiveClassName(ObjCRuntime.newNSString(Arena.global(), inArchiveName))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun classNameDecodedForArchiveClassNameAsString(inArchiveName: String): String = ObjCRuntime.toJavaString(classNameDecodedForArchiveClassName(ObjCRuntime.newNSString(Arena.global(), inArchiveName)))
    
    open fun replaceObject_withObject(`object`: MemorySegment, newObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceObject:withObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`, newObject)
    }
    
    // @property atEnd
    open fun isAtEnd(): Boolean {
        val sel = ObjCRuntime.sel("isAtEnd")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property systemVersion
    open fun systemVersion(): Int {
        val sel = ObjCRuntime.sel("systemVersion")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: datax: MemorySegment
    // ivar: cursor: Long
    // ivar: objectZone: MemorySegment
    // ivar: systemVersion: Long
    // ivar: streamerVersion: Byte
    // ivar: swap: Byte
    // ivar: unused1: Byte
    // ivar: unused2: Byte
    // ivar: pointerTable: MemorySegment
    // ivar: stringTable: MemorySegment
    // ivar: classVersions: MemorySegment
    // ivar: lastLabel: Long
    // ivar: map: MemorySegment
    // ivar: allUnarchivedObjects: MemorySegment
    // ivar: reserved: MemorySegment
}

