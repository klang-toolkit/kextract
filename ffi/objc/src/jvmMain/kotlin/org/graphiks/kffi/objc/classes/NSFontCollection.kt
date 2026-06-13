package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFontCollection
 * Superclass: NSObject
 * Protocols: NSCopying, NSMutableCopying, NSCoding
 */
open class NSFontCollection(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFontCollection") }
        
        fun fontCollectionWithDescriptors(queryDescriptors: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithDescriptors:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, queryDescriptors) as MemorySegment
        }
        
        fun fontCollectionWithLocale(locale: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithLocale:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, locale) as MemorySegment
        }
        
        fun showFontCollection_withName_visibility_error(collection: MemorySegment, name: MemorySegment, visibility: MemorySegment, error: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("showFontCollection:withName:visibility:error:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, collection, name, visibility, error) as Boolean
        }
        
        fun hideFontCollectionWithName_visibility_error(name: MemorySegment, visibility: MemorySegment, error: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("hideFontCollectionWithName:visibility:error:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, name, visibility, error) as Boolean
        }
        
        fun renameFontCollectionWithName_visibility_toName_error(oldName: MemorySegment, visibility: MemorySegment, newName: MemorySegment, outError: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("renameFontCollectionWithName:visibility:toName:error:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, oldName, visibility, newName, outError) as Boolean
        }
        
        fun fontCollectionWithName(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithName:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        fun fontCollectionWithName_visibility(name: MemorySegment, visibility: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithName:visibility:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, visibility) as MemorySegment
        }
        
        fun fontCollectionWithAllAvailableDescriptors(): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithAllAvailableDescriptors")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSFontCollectionName> * */
        fun allFontCollectionNames(): MemorySegment {
            val sel = ObjCRuntime.sel("allFontCollectionNames")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    /** @return NSArray<NSFontDescriptor *> * */
    open fun matchingDescriptorsWithOptions(options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("matchingDescriptorsWithOptions:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, options) as MemorySegment
    }
    
    /** @return NSArray<NSFontDescriptor *> * */
    open fun matchingDescriptorsForFamily(family: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("matchingDescriptorsForFamily:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, family) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun matchingDescriptorsForFamily(family: String): MemorySegment = matchingDescriptorsForFamily(ObjCRuntime.newNSString(Arena.global(), family))
    
    /** @return NSArray<NSFontDescriptor *> * */
    open fun matchingDescriptorsForFamily_options(family: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("matchingDescriptorsForFamily:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, family, options) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun matchingDescriptorsForFamily_options(family: String, options: MemorySegment): MemorySegment = matchingDescriptorsForFamily_options(ObjCRuntime.newNSString(Arena.global(), family), options)
    
    // @property fontCollectionWithAllAvailableDescriptors
    open fun fontCollectionWithAllAvailableDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("fontCollectionWithAllAvailableDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property allFontCollectionNames
    /** @return NSArray<NSFontCollectionName> * */
    open fun allFontCollectionNames(): MemorySegment {
        val sel = ObjCRuntime.sel("allFontCollectionNames")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property queryDescriptors
    /** @return NSArray<NSFontDescriptor *> * */
    open fun queryDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("queryDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property exclusionDescriptors
    /** @return NSArray<NSFontDescriptor *> * */
    open fun exclusionDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("exclusionDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property matchingDescriptors
    /** @return NSArray<NSFontDescriptor *> * */
    open fun matchingDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("matchingDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

