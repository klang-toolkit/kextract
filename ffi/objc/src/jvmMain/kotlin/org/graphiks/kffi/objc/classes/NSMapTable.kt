package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMapTable
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding, NSFastEnumeration
 */
open class NSMapTable(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMapTable") }
        
        /** @return NSMapTable<KeyType,ObjectType> * */
        fun mapTableWithKeyOptions_valueOptions(keyOptions: MemorySegment, valueOptions: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("mapTableWithKeyOptions:valueOptions:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, keyOptions, valueOptions) as MemorySegment
        }
        
        fun mapTableWithStrongToStrongObjects(): MemorySegment {
            val sel = ObjCRuntime.sel("mapTableWithStrongToStrongObjects")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun mapTableWithWeakToStrongObjects(): MemorySegment {
            val sel = ObjCRuntime.sel("mapTableWithWeakToStrongObjects")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun mapTableWithStrongToWeakObjects(): MemorySegment {
            val sel = ObjCRuntime.sel("mapTableWithStrongToWeakObjects")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun mapTableWithWeakToWeakObjects(): MemorySegment {
            val sel = ObjCRuntime.sel("mapTableWithWeakToWeakObjects")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSMapTable<KeyType,ObjectType> * */
        fun strongToStrongObjectsMapTable(): MemorySegment {
            val sel = ObjCRuntime.sel("strongToStrongObjectsMapTable")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSMapTable<KeyType,ObjectType> * */
        fun weakToStrongObjectsMapTable(): MemorySegment {
            val sel = ObjCRuntime.sel("weakToStrongObjectsMapTable")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSMapTable<KeyType,ObjectType> * */
        fun strongToWeakObjectsMapTable(): MemorySegment {
            val sel = ObjCRuntime.sel("strongToWeakObjectsMapTable")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSMapTable<KeyType,ObjectType> * */
        fun weakToWeakObjectsMapTable(): MemorySegment {
            val sel = ObjCRuntime.sel("weakToWeakObjectsMapTable")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithKeyOptions_valueOptions_capacity(keyOptions: MemorySegment, valueOptions: MemorySegment, initialCapacity: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithKeyOptions:valueOptions:capacity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, keyOptions, valueOptions, initialCapacity) as MemorySegment
    }
    
    open fun initWithKeyPointerFunctions_valuePointerFunctions_capacity(keyFunctions: MemorySegment, valueFunctions: MemorySegment, initialCapacity: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithKeyPointerFunctions:valuePointerFunctions:capacity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, keyFunctions, valueFunctions, initialCapacity) as MemorySegment
    }
    
    open fun objectForKey(aKey: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("objectForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, aKey) as MemorySegment
    }
    
    open fun removeObjectForKey(aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObjectForKey:")
        ObjCRuntime.msgSend(null, ptr, sel, aKey)
    }
    
    open fun setObject_forKey(anObject: MemorySegment, aKey: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setObject:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, anObject, aKey)
    }
    
    /** @return NSEnumerator<KeyType> * */
    open fun keyEnumerator(): MemorySegment {
        val sel = ObjCRuntime.sel("keyEnumerator")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** @return NSEnumerator<ObjectType> * */
    open fun objectEnumerator(): MemorySegment {
        val sel = ObjCRuntime.sel("objectEnumerator")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun removeAllObjects(): Unit {
        val sel = ObjCRuntime.sel("removeAllObjects")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    /** @return NSDictionary<KeyType,ObjectType> * */
    open fun dictionaryRepresentation(): MemorySegment {
        val sel = ObjCRuntime.sel("dictionaryRepresentation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property keyPointerFunctions
    open fun keyPointerFunctions(): MemorySegment {
        val sel = ObjCRuntime.sel("keyPointerFunctions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property valuePointerFunctions
    open fun valuePointerFunctions(): MemorySegment {
        val sel = ObjCRuntime.sel("valuePointerFunctions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property count
    open fun count(): Long {
        val sel = ObjCRuntime.sel("count")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
}

