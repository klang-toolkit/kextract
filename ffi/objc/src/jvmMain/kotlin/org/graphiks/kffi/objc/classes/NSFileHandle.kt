package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFileHandle
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSFileHandle(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFileHandle") }
        
    }
    
    open fun initWithFileDescriptor_closeOnDealloc(fd: Int, closeopt: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFileDescriptor:closeOnDealloc:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, fd, closeopt) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun readDataToEndOfFileAndReturnError(error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("readDataToEndOfFileAndReturnError:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, error) as MemorySegment
    }
    
    open fun readDataUpToLength_error(length: Long, error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("readDataUpToLength:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, length, error) as MemorySegment
    }
    
    open fun writeData_error(`data`: MemorySegment, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("writeData:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, `data`, error) as Boolean
    }
    
    open fun getOffset_error(offsetInFile: MemorySegment, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("getOffset:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, offsetInFile, error) as Boolean
    }
    
    open fun seekToEndReturningOffset_error(offsetInFile: MemorySegment, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("seekToEndReturningOffset:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, offsetInFile, error) as Boolean
    }
    
    open fun seekToOffset_error(offset: Long, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("seekToOffset:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, offset, error) as Boolean
    }
    
    open fun truncateAtOffset_error(offset: Long, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("truncateAtOffset:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, offset, error) as Boolean
    }
    
    open fun synchronizeAndReturnError(error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("synchronizeAndReturnError:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, error) as Boolean
    }
    
    open fun closeAndReturnError(error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("closeAndReturnError:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, error) as Boolean
    }
    
    // @property availableData
    open fun availableData(): MemorySegment {
        val sel = ObjCRuntime.sel("availableData")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSFileHandleCreation on NSFileHandle ─────────────────────────────────────────

// Class method: +[NSFileHandle fileHandleForReadingAtPath:]
fun NSFileHandle_fileHandleForReadingAtPath(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleForReadingAtPath:")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path) as MemorySegment
}

// Class method: +[NSFileHandle fileHandleForWritingAtPath:]
fun NSFileHandle_fileHandleForWritingAtPath(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleForWritingAtPath:")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path) as MemorySegment
}

// Class method: +[NSFileHandle fileHandleForUpdatingAtPath:]
fun NSFileHandle_fileHandleForUpdatingAtPath(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleForUpdatingAtPath:")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path) as MemorySegment
}

// Class method: +[NSFileHandle fileHandleForReadingFromURL:error:]
fun NSFileHandle_fileHandleForReadingFromURL_error(url: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleForReadingFromURL:error:")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url, error) as MemorySegment
}

// Class method: +[NSFileHandle fileHandleForWritingToURL:error:]
fun NSFileHandle_fileHandleForWritingToURL_error(url: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleForWritingToURL:error:")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url, error) as MemorySegment
}

// Class method: +[NSFileHandle fileHandleForUpdatingURL:error:]
fun NSFileHandle_fileHandleForUpdatingURL_error(url: MemorySegment, error: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleForUpdatingURL:error:")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url, error) as MemorySegment
}

// Class method: +[NSFileHandle fileHandleWithStandardInput]
fun NSFileHandle_fileHandleWithStandardInput(): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleWithStandardInput")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSFileHandle fileHandleWithStandardOutput]
fun NSFileHandle_fileHandleWithStandardOutput(): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleWithStandardOutput")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSFileHandle fileHandleWithStandardError]
fun NSFileHandle_fileHandleWithStandardError(): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleWithStandardError")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSFileHandle fileHandleWithNullDevice]
fun NSFileHandle_fileHandleWithNullDevice(): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleWithNullDevice")
    val cls = ObjCRuntime.getClass("NSFileHandle")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// @property fileHandleWithStandardInput
fun NSFileHandle.fileHandleWithStandardInput(): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleWithStandardInput")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property fileHandleWithStandardOutput
fun NSFileHandle.fileHandleWithStandardOutput(): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleWithStandardOutput")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property fileHandleWithStandardError
fun NSFileHandle.fileHandleWithStandardError(): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleWithStandardError")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// @property fileHandleWithNullDevice
fun NSFileHandle.fileHandleWithNullDevice(): MemorySegment {
    val sel = ObjCRuntime.sel("fileHandleWithNullDevice")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSFileHandleAsynchronousAccess on NSFileHandle ─────────────────────────────────────────

fun NSFileHandle.readInBackgroundAndNotifyForModes(modes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("readInBackgroundAndNotifyForModes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, modes)
}

fun NSFileHandle.readInBackgroundAndNotify(): Unit {
    val sel = ObjCRuntime.sel("readInBackgroundAndNotify")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSFileHandle.readToEndOfFileInBackgroundAndNotifyForModes(modes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("readToEndOfFileInBackgroundAndNotifyForModes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, modes)
}

fun NSFileHandle.readToEndOfFileInBackgroundAndNotify(): Unit {
    val sel = ObjCRuntime.sel("readToEndOfFileInBackgroundAndNotify")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSFileHandle.acceptConnectionInBackgroundAndNotifyForModes(modes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("acceptConnectionInBackgroundAndNotifyForModes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, modes)
}

fun NSFileHandle.acceptConnectionInBackgroundAndNotify(): Unit {
    val sel = ObjCRuntime.sel("acceptConnectionInBackgroundAndNotify")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSFileHandle.waitForDataInBackgroundAndNotifyForModes(modes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("waitForDataInBackgroundAndNotifyForModes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, modes)
}

fun NSFileHandle.waitForDataInBackgroundAndNotify(): Unit {
    val sel = ObjCRuntime.sel("waitForDataInBackgroundAndNotify")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSFileHandle.readabilityHandler(): MemorySegment {
    val sel = ObjCRuntime.sel("readabilityHandler")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSFileHandle.setReadabilityHandler(readabilityHandler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setReadabilityHandler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, readabilityHandler)
}

fun NSFileHandle.writeabilityHandler(): MemorySegment {
    val sel = ObjCRuntime.sel("writeabilityHandler")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSFileHandle.setWriteabilityHandler(writeabilityHandler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setWriteabilityHandler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, writeabilityHandler)
}

// ── Category: NSFileHandlePlatformSpecific on NSFileHandle ─────────────────────────────────────────

fun NSFileHandle.initWithFileDescriptor(fd: Int): MemorySegment {
    val sel = ObjCRuntime.sel("initWithFileDescriptor:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, fd) as MemorySegment
}

fun NSFileHandle.fileDescriptor(): Int {
    val sel = ObjCRuntime.sel("fileDescriptor")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel) as Int
}

// ── Category:  on NSFileHandle ─────────────────────────────────────────

fun NSFileHandle.readDataToEndOfFile(): MemorySegment {
    val sel = ObjCRuntime.sel("readDataToEndOfFile")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSFileHandle.readDataOfLength(length: Long): MemorySegment {
    val sel = ObjCRuntime.sel("readDataOfLength:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, length) as MemorySegment
}

fun NSFileHandle.writeData(`data`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("writeData:")
    ObjCRuntime.msgSend(null, this.ptr, sel, `data`)
}

fun NSFileHandle.offsetInFile(): Long {
    val sel = ObjCRuntime.sel("offsetInFile")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSFileHandle.seekToEndOfFile(): Long {
    val sel = ObjCRuntime.sel("seekToEndOfFile")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSFileHandle.seekToFileOffset(offset: Long): Unit {
    val sel = ObjCRuntime.sel("seekToFileOffset:")
    ObjCRuntime.msgSend(null, this.ptr, sel, offset)
}

fun NSFileHandle.truncateFileAtOffset(offset: Long): Unit {
    val sel = ObjCRuntime.sel("truncateFileAtOffset:")
    ObjCRuntime.msgSend(null, this.ptr, sel, offset)
}

fun NSFileHandle.synchronizeFile(): Unit {
    val sel = ObjCRuntime.sel("synchronizeFile")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSFileHandle.closeFile(): Unit {
    val sel = ObjCRuntime.sel("closeFile")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

