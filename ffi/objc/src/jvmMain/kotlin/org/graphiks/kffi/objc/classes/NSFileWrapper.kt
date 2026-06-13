package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFileWrapper
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSFileWrapper(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFileWrapper") }
        
    }
    
    open fun initWithURL_options_error(url: MemorySegment, options: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithURL:options:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, options, outError) as MemorySegment
    }
    
    open fun initDirectoryWithFileWrappers(childrenByPreferredName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initDirectoryWithFileWrappers:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, childrenByPreferredName) as MemorySegment
    }
    
    open fun initRegularFileWithContents(contents: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initRegularFileWithContents:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, contents) as MemorySegment
    }
    
    open fun initSymbolicLinkWithDestinationURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initSymbolicLinkWithDestinationURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun initWithSerializedRepresentation(serializeRepresentation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSerializedRepresentation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, serializeRepresentation) as MemorySegment
    }
    
    open fun initWithCoder(inCoder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inCoder) as MemorySegment
    }
    
    open fun matchesContentsOfURL(url: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("matchesContentsOfURL:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url) as Boolean
    }
    
    open fun readFromURL_options_error(url: MemorySegment, options: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("readFromURL:options:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url, options, outError) as Boolean
    }
    
    open fun writeToURL_options_originalContentsURL_error(url: MemorySegment, options: MemorySegment, originalContentsURL: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("writeToURL:options:originalContentsURL:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url, options, originalContentsURL, outError) as Boolean
    }
    
    open fun addFileWrapper(child: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("addFileWrapper:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, child) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun addFileWrapperAsString(child: MemorySegment): String = ObjCRuntime.toJavaString(addFileWrapper(child))
    
    open fun addRegularFileWithContents_preferredFilename(`data`: MemorySegment, fileName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("addRegularFileWithContents:preferredFilename:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`, fileName) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun addRegularFileWithContents_preferredFilenameAsString(`data`: MemorySegment, fileName: MemorySegment): String = ObjCRuntime.toJavaString(addRegularFileWithContents_preferredFilename(`data`, fileName))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun addRegularFileWithContents_preferredFilename(`data`: MemorySegment, fileName: String): MemorySegment = addRegularFileWithContents_preferredFilename(`data`, ObjCRuntime.newNSString(Arena.global(), fileName))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun addRegularFileWithContents_preferredFilenameAsString(`data`: MemorySegment, fileName: String): String = ObjCRuntime.toJavaString(addRegularFileWithContents_preferredFilename(`data`, ObjCRuntime.newNSString(Arena.global(), fileName)))
    
    open fun removeFileWrapper(child: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeFileWrapper:")
        ObjCRuntime.msgSend(null, ptr, sel, child)
    }
    
    open fun keyForFileWrapper(child: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("keyForFileWrapper:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, child) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun keyForFileWrapperAsString(child: MemorySegment): String = ObjCRuntime.toJavaString(keyForFileWrapper(child))
    
    // @property directory
    open fun isDirectory(): Boolean {
        val sel = ObjCRuntime.sel("isDirectory")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property regularFile
    open fun isRegularFile(): Boolean {
        val sel = ObjCRuntime.sel("isRegularFile")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property symbolicLink
    open fun isSymbolicLink(): Boolean {
        val sel = ObjCRuntime.sel("isSymbolicLink")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property preferredFilename
    open fun preferredFilename(): MemorySegment {
        val sel = ObjCRuntime.sel("preferredFilename")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPreferredFilename(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPreferredFilename:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun preferredFilenameAsString(): String = ObjCRuntime.toJavaString(preferredFilename())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPreferredFilename(value: String) = setPreferredFilename(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property filename
    open fun filename(): MemorySegment {
        val sel = ObjCRuntime.sel("filename")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFilename(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFilename:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun filenameAsString(): String = ObjCRuntime.toJavaString(filename())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setFilename(value: String) = setFilename(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property fileAttributes
    /** @return NSDictionary<NSString *,id> * */
    open fun fileAttributes(): MemorySegment {
        val sel = ObjCRuntime.sel("fileAttributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFileAttributes(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFileAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property serializedRepresentation
    open fun serializedRepresentation(): MemorySegment {
        val sel = ObjCRuntime.sel("serializedRepresentation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property fileWrappers
    /** @return NSDictionary<NSString *,NSFileWrapper *> * */
    open fun fileWrappers(): MemorySegment {
        val sel = ObjCRuntime.sel("fileWrappers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property regularFileContents
    open fun regularFileContents(): MemorySegment {
        val sel = ObjCRuntime.sel("regularFileContents")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property symbolicLinkDestinationURL
    open fun symbolicLinkDestinationURL(): MemorySegment {
        val sel = ObjCRuntime.sel("symbolicLinkDestinationURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSDeprecated on NSFileWrapper ─────────────────────────────────────────

fun NSFileWrapper.initWithPath(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithPath:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path) as MemorySegment
}

fun NSFileWrapper.initSymbolicLinkWithDestination(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initSymbolicLinkWithDestination:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path) as MemorySegment
}

fun NSFileWrapper.needsToBeUpdatedFromPath(path: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("needsToBeUpdatedFromPath:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, path) as Boolean
}

fun NSFileWrapper.updateFromPath(path: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("updateFromPath:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, path) as Boolean
}

fun NSFileWrapper.writeToFile_atomically_updateFilenames(path: MemorySegment, atomicFlag: Boolean, updateFilenamesFlag: Boolean): Boolean {
    val sel = ObjCRuntime.sel("writeToFile:atomically:updateFilenames:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, path, atomicFlag, updateFilenamesFlag) as Boolean
}

fun NSFileWrapper.addFileWithPath(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("addFileWithPath:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path) as MemorySegment
}

fun NSFileWrapper.addSymbolicLinkWithDestination_preferredFilename(path: MemorySegment, filename: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("addSymbolicLinkWithDestination:preferredFilename:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path, filename) as MemorySegment
}

fun NSFileWrapper.symbolicLinkDestination(): MemorySegment {
    val sel = ObjCRuntime.sel("symbolicLinkDestination")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSExtensions on NSFileWrapper ─────────────────────────────────────────

fun NSFileWrapper.icon(): MemorySegment {
    val sel = ObjCRuntime.sel("icon")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSFileWrapper.setIcon(icon: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setIcon:")
    ObjCRuntime.msgSend(null, this.ptr, sel, icon)
}

