package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFileVersion
 * Superclass: NSObject
 */
open class NSFileVersion(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFileVersion") }
        
        fun currentVersionOfItemAtURL(url: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("currentVersionOfItemAtURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url) as MemorySegment
        }
        
        /** @return NSArray<NSFileVersion *> * */
        fun otherVersionsOfItemAtURL(url: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("otherVersionsOfItemAtURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url) as MemorySegment
        }
        
        /** @return NSArray<NSFileVersion *> * */
        fun unresolvedConflictVersionsOfItemAtURL(url: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("unresolvedConflictVersionsOfItemAtURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url) as MemorySegment
        }
        
        fun getNonlocalVersionsOfItemAtURL_completionHandler(url: MemorySegment, completionHandler: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("getNonlocalVersionsOfItemAtURL:completionHandler:")
            ObjCRuntime.msgSend(null, _class, sel, url, completionHandler)
        }
        
        fun versionOfItemAtURL_forPersistentIdentifier(url: MemorySegment, persistentIdentifier: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("versionOfItemAtURL:forPersistentIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url, persistentIdentifier) as MemorySegment
        }
        
        fun addVersionOfItemAtURL_withContentsOfURL_options_error(url: MemorySegment, contentsURL: MemorySegment, options: MemorySegment, outError: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("addVersionOfItemAtURL:withContentsOfURL:options:error:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url, contentsURL, options, outError) as MemorySegment
        }
        
        fun temporaryDirectoryURLForNewVersionOfItemAtURL(url: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("temporaryDirectoryURLForNewVersionOfItemAtURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url) as MemorySegment
        }
        
        fun removeOtherVersionsOfItemAtURL_error(url: MemorySegment, outError: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("removeOtherVersionsOfItemAtURL:error:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, url, outError) as Boolean
        }
        
    }
    
    open fun replaceItemAtURL_options_error(url: MemorySegment, options: MemorySegment, error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("replaceItemAtURL:options:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, options, error) as MemorySegment
    }
    
    open fun removeAndReturnError(outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("removeAndReturnError:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, outError) as Boolean
    }
    
    // @property URL
    open fun URL(): MemorySegment {
        val sel = ObjCRuntime.sel("URL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property localizedName
    open fun localizedName(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun localizedNameAsString(): String = ObjCRuntime.toJavaString(localizedName())
    
    // @property localizedNameOfSavingComputer
    open fun localizedNameOfSavingComputer(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedNameOfSavingComputer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun localizedNameOfSavingComputerAsString(): String = ObjCRuntime.toJavaString(localizedNameOfSavingComputer())
    
    // @property originatorNameComponents
    open fun originatorNameComponents(): MemorySegment {
        val sel = ObjCRuntime.sel("originatorNameComponents")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property modificationDate
    open fun modificationDate(): MemorySegment {
        val sel = ObjCRuntime.sel("modificationDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property persistentIdentifier
    /** @return id<NSCoding> */
    open fun persistentIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("persistentIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property conflict
    open fun isConflict(): Boolean {
        val sel = ObjCRuntime.sel("isConflict")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property resolved
    open fun isResolved(): Boolean {
        val sel = ObjCRuntime.sel("isResolved")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setResolved(value: Boolean) {
        val sel = ObjCRuntime.sel("setResolved:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property discardable
    open fun isDiscardable(): Boolean {
        val sel = ObjCRuntime.sel("isDiscardable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDiscardable(value: Boolean) {
        val sel = ObjCRuntime.sel("setDiscardable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasLocalContents
    open fun hasLocalContents(): Boolean {
        val sel = ObjCRuntime.sel("hasLocalContents")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property hasThumbnail
    open fun hasThumbnail(): Boolean {
        val sel = ObjCRuntime.sel("hasThumbnail")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

