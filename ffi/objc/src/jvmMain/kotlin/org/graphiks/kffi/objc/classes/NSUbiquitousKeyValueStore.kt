package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUbiquitousKeyValueStore
 * Superclass: NSObject
 */
open class NSUbiquitousKeyValueStore(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUbiquitousKeyValueStore") }
        
        fun defaultStore(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultStore")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun objectForKey(aKey: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("objectForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, aKey) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun objectForKey(aKey: String): MemorySegment = objectForKey(ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun setObject_forKey(anObject: MemorySegment, aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setObject:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, anObject, aKey)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setObject_forKey(anObject: MemorySegment, aKey: String): Unit = setObject_forKey(anObject, ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun removeObjectForKey(aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObjectForKey:")
        ObjCRuntime.msgSend(null, ptr, sel, aKey)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun removeObjectForKey(aKey: String): Unit = removeObjectForKey(ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun stringForKey(aKey: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, aKey) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringForKeyAsString(aKey: MemorySegment): String = ObjCRuntime.toJavaString(stringForKey(aKey))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun stringForKey(aKey: String): MemorySegment = stringForKey(ObjCRuntime.newNSString(Arena.global(), aKey))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun stringForKeyAsString(aKey: String): String = ObjCRuntime.toJavaString(stringForKey(ObjCRuntime.newNSString(Arena.global(), aKey)))
    
    open fun arrayForKey(aKey: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("arrayForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, aKey) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun arrayForKey(aKey: String): MemorySegment = arrayForKey(ObjCRuntime.newNSString(Arena.global(), aKey))
    
    /** @return NSDictionary<NSString *,id> * */
    open fun dictionaryForKey(aKey: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dictionaryForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, aKey) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun dictionaryForKey(aKey: String): MemorySegment = dictionaryForKey(ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun dataForKey(aKey: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dataForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, aKey) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun dataForKey(aKey: String): MemorySegment = dataForKey(ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun longLongForKey(aKey: MemorySegment): Long {
        val sel = ObjCRuntime.sel("longLongForKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, aKey) as Long
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun longLongForKey(aKey: String): Long = longLongForKey(ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun doubleForKey(aKey: MemorySegment): Double {
        val sel = ObjCRuntime.sel("doubleForKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, aKey) as Double
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun doubleForKey(aKey: String): Double = doubleForKey(ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun boolForKey(aKey: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("boolForKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, aKey) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun boolForKey(aKey: String): Boolean = boolForKey(ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun setString_forKey(aString: MemorySegment, aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setString:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, aString, aKey)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setString_forKey(aString: String, aKey: String): Unit = setString_forKey(ObjCRuntime.newNSString(Arena.global(), aString), ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun setData_forKey(aData: MemorySegment, aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setData:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, aData, aKey)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setData_forKey(aData: MemorySegment, aKey: String): Unit = setData_forKey(aData, ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun setArray_forKey(anArray: MemorySegment, aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setArray:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, anArray, aKey)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setArray_forKey(anArray: MemorySegment, aKey: String): Unit = setArray_forKey(anArray, ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun setDictionary_forKey(aDictionary: MemorySegment, aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setDictionary:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, aDictionary, aKey)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setDictionary_forKey(aDictionary: MemorySegment, aKey: String): Unit = setDictionary_forKey(aDictionary, ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun setLongLong_forKey(value: Long, aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setLongLong:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, value, aKey)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setLongLong_forKey(value: Long, aKey: String): Unit = setLongLong_forKey(value, ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun setDouble_forKey(value: Double, aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setDouble:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, value, aKey)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setDouble_forKey(value: Double, aKey: String): Unit = setDouble_forKey(value, ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun setBool_forKey(value: Boolean, aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setBool:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, value, aKey)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setBool_forKey(value: Boolean, aKey: String): Unit = setBool_forKey(value, ObjCRuntime.newNSString(Arena.global(), aKey))
    
    open fun synchronize(): Boolean {
        val sel = ObjCRuntime.sel("synchronize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property defaultStore
    open fun defaultStore(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultStore")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property dictionaryRepresentation
    /** @return NSDictionary<NSString *,id> * */
    open fun dictionaryRepresentation(): MemorySegment {
        val sel = ObjCRuntime.sel("dictionaryRepresentation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

