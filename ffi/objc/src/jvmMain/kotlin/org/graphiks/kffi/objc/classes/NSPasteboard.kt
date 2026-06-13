package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPasteboard
 * Superclass: NSObject
 */
open class NSPasteboard(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPasteboard") }
        
        fun pasteboardWithName(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("pasteboardWithName:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        fun pasteboardWithUniqueName(): MemorySegment {
            val sel = ObjCRuntime.sel("pasteboardWithUniqueName")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun generalPasteboard(): MemorySegment {
            val sel = ObjCRuntime.sel("generalPasteboard")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun releaseGlobally(): Unit {
        val sel = ObjCRuntime.sel("releaseGlobally")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun prepareForNewContentsWithOptions(options: MemorySegment): Long {
        val sel = ObjCRuntime.sel("prepareForNewContentsWithOptions:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, options) as Long
    }
    
    open fun clearContents(): Long {
        val sel = ObjCRuntime.sel("clearContents")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun writeObjects(objects: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("writeObjects:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, objects) as Boolean
    }
    
    open fun readObjectsForClasses_options(classArray: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("readObjectsForClasses:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, classArray, options) as MemorySegment
    }
    
    open fun indexOfPasteboardItem(pasteboardItem: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfPasteboardItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, pasteboardItem) as Long
    }
    
    open fun canReadItemWithDataConformingToTypes(types: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("canReadItemWithDataConformingToTypes:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, types) as Boolean
    }
    
    open fun canReadObjectForClasses_options(classArray: MemorySegment, options: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("canReadObjectForClasses:options:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, classArray, options) as Boolean
    }
    
    open fun declareTypes_owner(newTypes: MemorySegment, newOwner: MemorySegment): Long {
        val sel = ObjCRuntime.sel("declareTypes:owner:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, newTypes, newOwner) as Long
    }
    
    open fun addTypes_owner(newTypes: MemorySegment, newOwner: MemorySegment): Long {
        val sel = ObjCRuntime.sel("addTypes:owner:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, newTypes, newOwner) as Long
    }
    
    open fun availableTypeFromArray(types: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("availableTypeFromArray:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, types) as MemorySegment
    }
    
    open fun setData_forType(`data`: MemorySegment, dataType: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setData:forType:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, `data`, dataType) as Boolean
    }
    
    open fun setPropertyList_forType(plist: MemorySegment, dataType: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setPropertyList:forType:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, plist, dataType) as Boolean
    }
    
    open fun setString_forType(string: MemorySegment, dataType: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setString:forType:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, string, dataType) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setString_forType(string: String, dataType: MemorySegment): Boolean = setString_forType(ObjCRuntime.newNSString(Arena.global(), string), dataType)
    
    open fun dataForType(dataType: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dataForType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, dataType) as MemorySegment
    }
    
    open fun propertyListForType(dataType: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("propertyListForType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, dataType) as MemorySegment
    }
    
    open fun stringForType(dataType: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringForType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, dataType) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringForTypeAsString(dataType: MemorySegment): String = ObjCRuntime.toJavaString(stringForType(dataType))
    
    open fun detectPatternsForPatterns_completionHandler(patterns: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("detectPatternsForPatterns:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, patterns, completionHandler)
    }
    
    open fun detectValuesForPatterns_completionHandler(patterns: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("detectValuesForPatterns:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, patterns, completionHandler)
    }
    
    open fun detectMetadataForTypes_completionHandler(types: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("detectMetadataForTypes:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, types, completionHandler)
    }
    
    // @property generalPasteboard
    open fun generalPasteboard(): MemorySegment {
        val sel = ObjCRuntime.sel("generalPasteboard")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property changeCount
    open fun changeCount(): Long {
        val sel = ObjCRuntime.sel("changeCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property accessBehavior
    open fun accessBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("accessBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property pasteboardItems
    /** @return NSArray<NSPasteboardItem *> * */
    open fun pasteboardItems(): MemorySegment {
        val sel = ObjCRuntime.sel("pasteboardItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property types
    /** @return NSArray<NSPasteboardType> * */
    open fun types(): MemorySegment {
        val sel = ObjCRuntime.sel("types")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: FilterServices on NSPasteboard ─────────────────────────────────────────

// Class method: +[NSPasteboard typesFilterableTo:]
fun NSPasteboard_typesFilterableTo(type: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("typesFilterableTo:")
    val cls = ObjCRuntime.getClass("NSPasteboard")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, type) as MemorySegment
}

// Class method: +[NSPasteboard pasteboardByFilteringFile:]
fun NSPasteboard_pasteboardByFilteringFile(filename: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("pasteboardByFilteringFile:")
    val cls = ObjCRuntime.getClass("NSPasteboard")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, filename) as MemorySegment
}

// Class method: +[NSPasteboard pasteboardByFilteringData:ofType:]
fun NSPasteboard_pasteboardByFilteringData_ofType(`data`: MemorySegment, type: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("pasteboardByFilteringData:ofType:")
    val cls = ObjCRuntime.getClass("NSPasteboard")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `data`, type) as MemorySegment
}

// Class method: +[NSPasteboard pasteboardByFilteringTypesInPasteboard:]
fun NSPasteboard_pasteboardByFilteringTypesInPasteboard(pboard: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("pasteboardByFilteringTypesInPasteboard:")
    val cls = ObjCRuntime.getClass("NSPasteboard")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, pboard) as MemorySegment
}

// ── Category: NSFileContents on NSPasteboard ─────────────────────────────────────────

fun NSPasteboard.writeFileContents(filename: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("writeFileContents:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, filename) as Boolean
}

fun NSPasteboard.readFileContentsType_toFile(type: MemorySegment, filename: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("readFileContentsType:toFile:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, type, filename) as MemorySegment
}

fun NSPasteboard.writeFileWrapper(wrapper: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("writeFileWrapper:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, wrapper) as Boolean
}

fun NSPasteboard.readFileWrapper(): MemorySegment {
    val sel = ObjCRuntime.sel("readFileWrapper")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

