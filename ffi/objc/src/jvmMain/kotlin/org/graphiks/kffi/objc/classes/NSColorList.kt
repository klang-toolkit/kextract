package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSColorList
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSColorList(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSColorList") }
        
        fun colorListNamed(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("colorListNamed:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        /** @return NSArray<NSColorList *> * */
        fun availableColorLists(): MemorySegment {
            val sel = ObjCRuntime.sel("availableColorLists")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    open fun initWithName_fromFile(name: MemorySegment, path: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithName:fromFile:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, path) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithName_fromFile(name: MemorySegment, path: String): MemorySegment = initWithName_fromFile(name, ObjCRuntime.newNSString(Arena.global(), path))
    
    open fun setColor_forKey(color: MemorySegment, key: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setColor:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, color, key)
    }
    
    open fun insertColor_key_atIndex(color: MemorySegment, key: MemorySegment, loc: Long): Unit {
        val sel = ObjCRuntime.sel("insertColor:key:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, color, key, loc)
    }
    
    open fun removeColorWithKey(key: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeColorWithKey:")
        ObjCRuntime.msgSend(null, ptr, sel, key)
    }
    
    open fun colorWithKey(key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("colorWithKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key) as MemorySegment
    }
    
    open fun writeToURL_error(url: MemorySegment, errPtr: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("writeToURL:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url, errPtr) as Boolean
    }
    
    open fun writeToFile(path: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("writeToFile:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, path) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun writeToFile(path: String): Boolean = writeToFile(ObjCRuntime.newNSString(Arena.global(), path))
    
    open fun removeFile(): Unit {
        val sel = ObjCRuntime.sel("removeFile")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property availableColorLists
    /** @return NSArray<NSColorList *> * */
    open fun availableColorLists(): MemorySegment {
        val sel = ObjCRuntime.sel("availableColorLists")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property allKeys
    /** @return NSArray<NSColorName> * */
    open fun allKeys(): MemorySegment {
        val sel = ObjCRuntime.sel("allKeys")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property editable
    open fun isEditable(): Boolean {
        val sel = ObjCRuntime.sel("isEditable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

