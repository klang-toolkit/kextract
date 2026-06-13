package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCoder
 * Superclass: NSObject
 */
open class NSCoder(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCoder") }
        
    }
    
    open fun encodeValueOfObjCType_at(type: MemorySegment, addr: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("encodeValueOfObjCType:at:")
        ObjCRuntime.msgSend(null, ptr, sel, type, addr)
    }
    
    open fun encodeDataObject(`data`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("encodeDataObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `data`)
    }
    
    open fun decodeDataObject(): MemorySegment {
        val sel = ObjCRuntime.sel("decodeDataObject")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun decodeValueOfObjCType_at_size(type: MemorySegment, `data`: MemorySegment, size: Long): Unit {
        val sel = ObjCRuntime.sel("decodeValueOfObjCType:at:size:")
        ObjCRuntime.msgSend(null, ptr, sel, type, `data`, size)
    }
    
    open fun versionForClassName(className: MemorySegment): Long {
        val sel = ObjCRuntime.sel("versionForClassName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, className) as Long
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun versionForClassName(className: String): Long = versionForClassName(ObjCRuntime.newNSString(Arena.global(), className))
    
}

// ── Category: NSExtendedCoder on NSCoder ─────────────────────────────────────────

fun NSCoder.encodeObject(`object`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, `object`)
}

fun NSCoder.encodeRootObject(rootObject: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeRootObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rootObject)
}

fun NSCoder.encodeBycopyObject(anObject: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeBycopyObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, anObject)
}

fun NSCoder.encodeByrefObject(anObject: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeByrefObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, anObject)
}

fun NSCoder.encodeConditionalObject(`object`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeConditionalObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, `object`)
}

fun NSCoder.encodeValuesOfObjCTypes(types: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeValuesOfObjCTypes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, types)
}

fun NSCoder.encodeArrayOfObjCType_count_at(type: MemorySegment, count: Long, array: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeArrayOfObjCType:count:at:")
    ObjCRuntime.msgSend(null, this.ptr, sel, type, count, array)
}

fun NSCoder.encodeBytes_length(byteaddr: MemorySegment, length: Long): Unit {
    val sel = ObjCRuntime.sel("encodeBytes:length:")
    ObjCRuntime.msgSend(null, this.ptr, sel, byteaddr, length)
}

fun NSCoder.decodeObject(): MemorySegment {
    val sel = ObjCRuntime.sel("decodeObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSCoder.decodeTopLevelObjectAndReturnError(error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeTopLevelObjectAndReturnError:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, error) as MemorySegment
}

fun NSCoder.decodeValuesOfObjCTypes(types: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("decodeValuesOfObjCTypes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, types)
}

fun NSCoder.decodeArrayOfObjCType_count_at(itemType: MemorySegment, count: Long, array: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("decodeArrayOfObjCType:count:at:")
    ObjCRuntime.msgSend(null, this.ptr, sel, itemType, count, array)
}

fun NSCoder.decodeBytesWithReturnedLength(lengthp: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeBytesWithReturnedLength:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, lengthp) as MemorySegment
}

fun NSCoder.encodePropertyList(aPropertyList: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodePropertyList:")
    ObjCRuntime.msgSend(null, this.ptr, sel, aPropertyList)
}

fun NSCoder.decodePropertyList(): MemorySegment {
    val sel = ObjCRuntime.sel("decodePropertyList")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSCoder.setObjectZone(zone: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setObjectZone:")
    ObjCRuntime.msgSend(null, this.ptr, sel, zone)
}

fun NSCoder.objectZone(): MemorySegment {
    val sel = ObjCRuntime.sel("objectZone")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSCoder.encodeObject_forKey(`object`: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeObject:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, `object`, key)
}

fun NSCoder.encodeConditionalObject_forKey(`object`: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeConditionalObject:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, `object`, key)
}

fun NSCoder.encodeBool_forKey(value: Boolean, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeBool:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSCoder.encodeInt_forKey(value: Int, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeInt:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSCoder.encodeInt32_forKey(value: Int, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeInt32:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSCoder.encodeInt64_forKey(value: Long, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeInt64:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSCoder.encodeFloat_forKey(value: Float, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeFloat:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSCoder.encodeDouble_forKey(value: Double, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeDouble:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSCoder.encodeBytes_length_forKey(bytes: MemorySegment, length: Long, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeBytes:length:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, bytes, length, key)
}

fun NSCoder.containsValueForKey(key: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("containsValueForKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, key) as Boolean
}

fun NSCoder.decodeObjectForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeObjectForKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSCoder.decodeTopLevelObjectForKey_error(key: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeTopLevelObjectForKey:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key, error) as MemorySegment
}

fun NSCoder.decodeBoolForKey(key: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("decodeBoolForKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, key) as Boolean
}

fun NSCoder.decodeIntForKey(key: MemorySegment): Int {
    val sel = ObjCRuntime.sel("decodeIntForKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel, key) as Int
}

fun NSCoder.decodeInt32ForKey(key: MemorySegment): Int {
    val sel = ObjCRuntime.sel("decodeInt32ForKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel, key) as Int
}

fun NSCoder.decodeInt64ForKey(key: MemorySegment): Long {
    val sel = ObjCRuntime.sel("decodeInt64ForKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, key) as Long
}

fun NSCoder.decodeFloatForKey(key: MemorySegment): Float {
    val sel = ObjCRuntime.sel("decodeFloatForKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel, key) as Float
}

fun NSCoder.decodeDoubleForKey(key: MemorySegment): Double {
    val sel = ObjCRuntime.sel("decodeDoubleForKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel, key) as Double
}

fun NSCoder.decodeBytesForKey_returnedLength(key: MemorySegment, lengthp: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeBytesForKey:returnedLength:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key, lengthp) as MemorySegment
}

fun NSCoder.decodeBytesWithMinimumLength(length: Long): MemorySegment {
    val sel = ObjCRuntime.sel("decodeBytesWithMinimumLength:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, length) as MemorySegment
}

fun NSCoder.decodeBytesForKey_minimumLength(key: MemorySegment, length: Long): MemorySegment {
    val sel = ObjCRuntime.sel("decodeBytesForKey:minimumLength:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key, length) as MemorySegment
}

fun NSCoder.encodeInteger_forKey(value: Long, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeInteger:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value, key)
}

fun NSCoder.decodeIntegerForKey(key: MemorySegment): Long {
    val sel = ObjCRuntime.sel("decodeIntegerForKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, key) as Long
}

fun NSCoder.decodeObjectOfClass_forKey(aClass: MemorySegment, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeObjectOfClass:forKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, aClass, key) as MemorySegment
}

fun NSCoder.decodeTopLevelObjectOfClass_forKey_error(aClass: MemorySegment, key: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeTopLevelObjectOfClass:forKey:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, aClass, key, error) as MemorySegment
}

fun NSCoder.decodeArrayOfObjectsOfClass_forKey(cls: MemorySegment, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeArrayOfObjectsOfClass:forKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, cls, key) as MemorySegment
}

fun NSCoder.decodeDictionaryWithKeysOfClass_objectsOfClass_forKey(keyCls: MemorySegment, objectCls: MemorySegment, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeDictionaryWithKeysOfClass:objectsOfClass:forKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, keyCls, objectCls, key) as MemorySegment
}

fun NSCoder.decodeObjectOfClasses_forKey(classes: MemorySegment, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeObjectOfClasses:forKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, classes, key) as MemorySegment
}

fun NSCoder.decodeTopLevelObjectOfClasses_forKey_error(classes: MemorySegment, key: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeTopLevelObjectOfClasses:forKey:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, classes, key, error) as MemorySegment
}

fun NSCoder.decodeArrayOfObjectsOfClasses_forKey(classes: MemorySegment, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeArrayOfObjectsOfClasses:forKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, classes, key) as MemorySegment
}

fun NSCoder.decodeDictionaryWithKeysOfClasses_objectsOfClasses_forKey(keyClasses: MemorySegment, objectClasses: MemorySegment, key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeDictionaryWithKeysOfClasses:objectsOfClasses:forKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, keyClasses, objectClasses, key) as MemorySegment
}

fun NSCoder.decodePropertyListForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodePropertyListForKey:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key) as MemorySegment
}

fun NSCoder.failWithError(error: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("failWithError:")
    ObjCRuntime.msgSend(null, this.ptr, sel, error)
}

fun NSCoder.systemVersion(): Int {
    val sel = ObjCRuntime.sel("systemVersion")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel) as Int
}

fun NSCoder.allowsKeyedCoding(): Boolean {
    val sel = ObjCRuntime.sel("allowsKeyedCoding")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSCoder.requiresSecureCoding(): Boolean {
    val sel = ObjCRuntime.sel("requiresSecureCoding")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

/** @return NSSet<Class> * */
fun NSCoder.allowedClasses(): MemorySegment {
    val sel = ObjCRuntime.sel("allowedClasses")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSCoder.decodingFailurePolicy(): MemorySegment {
    val sel = ObjCRuntime.sel("decodingFailurePolicy")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSCoder.error(): MemorySegment {
    val sel = ObjCRuntime.sel("error")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSTypedstreamCompatibility on NSCoder ─────────────────────────────────────────

fun NSCoder.encodeNXObject(`object`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeNXObject:")
    ObjCRuntime.msgSend(null, this.ptr, sel, `object`)
}

fun NSCoder.decodeNXObject(): MemorySegment {
    val sel = ObjCRuntime.sel("decodeNXObject")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSDeprecated on NSCoder ─────────────────────────────────────────

fun NSCoder.decodeValueOfObjCType_at(type: MemorySegment, `data`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("decodeValueOfObjCType:at:")
    ObjCRuntime.msgSend(null, this.ptr, sel, type, `data`)
}

// ── Category: NSGeometryCoding on NSCoder ─────────────────────────────────────────

fun NSCoder.encodePoint(point: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodePoint:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point)
}

fun NSCoder.decodePoint(): MemorySegment {
    val sel = ObjCRuntime.sel("decodePoint")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel) as MemorySegment
}

fun NSCoder.encodeSize(size: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeSize:")
    ObjCRuntime.msgSend(null, this.ptr, sel, size)
}

fun NSCoder.decodeSize(): MemorySegment {
    val sel = ObjCRuntime.sel("decodeSize")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel) as MemorySegment
}

fun NSCoder.encodeRect(rect: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeRect:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect)
}

fun NSCoder.decodeRect(): MemorySegment {
    val sel = ObjCRuntime.sel("decodeRect")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel) as MemorySegment
}

// ── Category: NSGeometryKeyedCoding on NSCoder ─────────────────────────────────────────

fun NSCoder.encodePoint_forKey(point: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodePoint:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point, key)
}

fun NSCoder.encodeSize_forKey(size: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeSize:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, size, key)
}

fun NSCoder.encodeRect_forKey(rect: MemorySegment, key: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeRect:forKey:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, key)
}

fun NSCoder.decodePointForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodePointForKey:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel, key) as MemorySegment
}

fun NSCoder.decodeSizeForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeSizeForKey:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel, key) as MemorySegment
}

fun NSCoder.decodeRectForKey(key: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decodeRectForKey:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, key) as MemorySegment
}

// ── Category: NSAppKitColorExtensions on NSCoder ─────────────────────────────────────────

fun NSCoder.decodeNXColor(): MemorySegment {
    val sel = ObjCRuntime.sel("decodeNXColor")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

