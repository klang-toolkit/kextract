package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSData
 * Superclass: NSObject
 * Protocols: NSCopying, NSMutableCopying, NSSecureCoding
 */
open class NSData(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSData") }
        
    }
    
    // @property length
    open fun length(): Long {
        val sel = ObjCRuntime.sel("length")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property bytes
    open fun bytes(): MemorySegment {
        val sel = ObjCRuntime.sel("bytes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSExtendedData on NSData ─────────────────────────────────────────

fun NSData.getBytes_length(buffer: MemorySegment, length: Long): Unit {
    val sel = ObjCRuntime.sel("getBytes:length:")
    ObjCRuntime.msgSend(null, this.ptr, sel, buffer, length)
}

fun NSData.getBytes_range(buffer: MemorySegment, range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getBytes:range:")
    ObjCRuntime.msgSend(null, this.ptr, sel, buffer, range)
}

fun NSData.isEqualToData(other: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isEqualToData:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, other) as Boolean
}

fun NSData.subdataWithRange(range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("subdataWithRange:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, range) as MemorySegment
}

fun NSData.writeToFile_atomically(path: MemorySegment, useAuxiliaryFile: Boolean): Boolean {
    val sel = ObjCRuntime.sel("writeToFile:atomically:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, path, useAuxiliaryFile) as Boolean
}

fun NSData.writeToURL_atomically(url: MemorySegment, atomically: Boolean): Boolean {
    val sel = ObjCRuntime.sel("writeToURL:atomically:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, atomically) as Boolean
}

fun NSData.writeToFile_options_error(path: MemorySegment, writeOptionsMask: MemorySegment, errorPtr: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("writeToFile:options:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, path, writeOptionsMask, errorPtr) as Boolean
}

fun NSData.writeToURL_options_error(url: MemorySegment, writeOptionsMask: MemorySegment, errorPtr: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("writeToURL:options:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, writeOptionsMask, errorPtr) as Boolean
}

fun NSData.rangeOfData_options_range(dataToFind: MemorySegment, mask: MemorySegment, searchRange: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("rangeOfData:options:range:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel, dataToFind, mask, searchRange) as MemorySegment
}

fun NSData.enumerateByteRangesUsingBlock(block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("enumerateByteRangesUsingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, block)
}

fun NSData.description(): MemorySegment {
    val sel = ObjCRuntime.sel("description")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSDataCreation on NSData ─────────────────────────────────────────

fun NSData.initWithBytes_length(bytes: MemorySegment, length: Long): MemorySegment {
    val sel = ObjCRuntime.sel("initWithBytes:length:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, bytes, length) as MemorySegment
}

fun NSData.initWithBytesNoCopy_length(bytes: MemorySegment, length: Long): MemorySegment {
    val sel = ObjCRuntime.sel("initWithBytesNoCopy:length:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, bytes, length) as MemorySegment
}

fun NSData.initWithBytesNoCopy_length_freeWhenDone(bytes: MemorySegment, length: Long, b: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("initWithBytesNoCopy:length:freeWhenDone:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, bytes, length, b) as MemorySegment
}

fun NSData.initWithBytesNoCopy_length_deallocator(bytes: MemorySegment, length: Long, deallocator: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithBytesNoCopy:length:deallocator:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, bytes, length, deallocator) as MemorySegment
}

fun NSData.initWithContentsOfFile_options_error(path: MemorySegment, readOptionsMask: MemorySegment, errorPtr: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfFile:options:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path, readOptionsMask, errorPtr) as MemorySegment
}

fun NSData.initWithContentsOfURL_options_error(url: MemorySegment, readOptionsMask: MemorySegment, errorPtr: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfURL:options:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, readOptionsMask, errorPtr) as MemorySegment
}

fun NSData.initWithContentsOfFile(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfFile:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path) as MemorySegment
}

fun NSData.initWithContentsOfURL(url: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfURL:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url) as MemorySegment
}

fun NSData.initWithData(`data`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithData:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`) as MemorySegment
}

// Class method: +[NSData data]
fun NSData_data(): MemorySegment {
    val sel = ObjCRuntime.sel("data")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSData dataWithBytes:length:]
fun NSData_dataWithBytes_length(bytes: MemorySegment, length: Long): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithBytes:length:")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, bytes, length) as MemorySegment
}

// Class method: +[NSData dataWithBytesNoCopy:length:]
fun NSData_dataWithBytesNoCopy_length(bytes: MemorySegment, length: Long): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithBytesNoCopy:length:")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, bytes, length) as MemorySegment
}

// Class method: +[NSData dataWithBytesNoCopy:length:freeWhenDone:]
fun NSData_dataWithBytesNoCopy_length_freeWhenDone(bytes: MemorySegment, length: Long, b: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithBytesNoCopy:length:freeWhenDone:")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, bytes, length, b) as MemorySegment
}

// Class method: +[NSData dataWithContentsOfFile:options:error:]
fun NSData_dataWithContentsOfFile_options_error(path: MemorySegment, readOptionsMask: MemorySegment, errorPtr: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithContentsOfFile:options:error:")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path, readOptionsMask, errorPtr) as MemorySegment
}

// Class method: +[NSData dataWithContentsOfURL:options:error:]
fun NSData_dataWithContentsOfURL_options_error(url: MemorySegment, readOptionsMask: MemorySegment, errorPtr: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithContentsOfURL:options:error:")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url, readOptionsMask, errorPtr) as MemorySegment
}

// Class method: +[NSData dataWithContentsOfFile:]
fun NSData_dataWithContentsOfFile(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithContentsOfFile:")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path) as MemorySegment
}

// Class method: +[NSData dataWithContentsOfURL:]
fun NSData_dataWithContentsOfURL(url: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithContentsOfURL:")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url) as MemorySegment
}

// Class method: +[NSData dataWithData:]
fun NSData_dataWithData(`data`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithData:")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `data`) as MemorySegment
}

// ── Category: NSDataBase64Encoding on NSData ─────────────────────────────────────────

fun NSData.initWithBase64EncodedString_options(base64String: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithBase64EncodedString:options:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, base64String, options) as MemorySegment
}

fun NSData.base64EncodedStringWithOptions(options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("base64EncodedStringWithOptions:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, options) as MemorySegment
}

fun NSData.initWithBase64EncodedData_options(base64Data: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithBase64EncodedData:options:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, base64Data, options) as MemorySegment
}

fun NSData.base64EncodedDataWithOptions(options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("base64EncodedDataWithOptions:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, options) as MemorySegment
}

// ── Category: NSDataCompression on NSData ─────────────────────────────────────────

fun NSData.decompressedDataUsingAlgorithm_error(algorithm: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("decompressedDataUsingAlgorithm:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, algorithm, error) as MemorySegment
}

fun NSData.compressedDataUsingAlgorithm_error(algorithm: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("compressedDataUsingAlgorithm:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, algorithm, error) as MemorySegment
}

// ── Category: NSDeprecated on NSData ─────────────────────────────────────────

fun NSData.getBytes(buffer: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getBytes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, buffer)
}

fun NSData.initWithContentsOfMappedFile(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfMappedFile:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path) as MemorySegment
}

fun NSData.initWithBase64Encoding(base64String: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithBase64Encoding:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, base64String) as MemorySegment
}

fun NSData.base64Encoding(): MemorySegment {
    val sel = ObjCRuntime.sel("base64Encoding")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSData dataWithContentsOfMappedFile:]
fun NSData_dataWithContentsOfMappedFile(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithContentsOfMappedFile:")
    val cls = ObjCRuntime.getClass("NSData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path) as MemorySegment
}

