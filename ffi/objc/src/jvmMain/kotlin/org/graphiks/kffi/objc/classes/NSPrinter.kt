package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPrinter
 * Superclass: NSObject
 * Protocols: NSCopying, NSCoding
 */
open class NSPrinter(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPrinter") }
        
        fun printerWithName(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("printerWithName:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun printerWithName(name: String): MemorySegment = printerWithName(ObjCRuntime.newNSString(Arena.global(), name))
        
        fun printerWithType(type: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("printerWithType:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, type) as MemorySegment
        }
        
        /** @return NSArray<NSString *> * */
        fun printerNames(): MemorySegment {
            val sel = ObjCRuntime.sel("printerNames")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSPrinterTypeName> * */
        fun printerTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("printerTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun pageSizeForPaper(paperName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("pageSizeForPaper:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, paperName) as MemorySegment
    }
    
    // @property printerNames
    /** @return NSArray<NSString *> * */
    open fun printerNames(): MemorySegment {
        val sel = ObjCRuntime.sel("printerNames")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property printerTypes
    /** @return NSArray<NSPrinterTypeName> * */
    open fun printerTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("printerTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun nameAsString(): String = ObjCRuntime.toJavaString(name())
    
    // @property type
    open fun type(): MemorySegment {
        val sel = ObjCRuntime.sel("type")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property languageLevel
    open fun languageLevel(): Long {
        val sel = ObjCRuntime.sel("languageLevel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property deviceDescription
    /** @return NSDictionary<NSDeviceDescriptionKey,id> * */
    open fun deviceDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("deviceDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSDeprecated on NSPrinter ─────────────────────────────────────────

fun NSPrinter.statusForTable(tableName: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("statusForTable:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, tableName) as MemorySegment
}

fun NSPrinter.isKey_inTable(key: MemorySegment, table: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isKey:inTable:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, key, table) as Boolean
}

fun NSPrinter.booleanForKey_inTable(key: MemorySegment, table: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("booleanForKey:inTable:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, key, table) as Boolean
}

fun NSPrinter.floatForKey_inTable(key: MemorySegment, table: MemorySegment): Float {
    val sel = ObjCRuntime.sel("floatForKey:inTable:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, this.ptr, sel, key, table) as Float
}

fun NSPrinter.intForKey_inTable(key: MemorySegment, table: MemorySegment): Int {
    val sel = ObjCRuntime.sel("intForKey:inTable:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel, key, table) as Int
}

fun NSPrinter.rectForKey_inTable(key: MemorySegment, table: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("rectForKey:inTable:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, key, table) as MemorySegment
}

fun NSPrinter.sizeForKey_inTable(key: MemorySegment, table: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sizeForKey:inTable:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel, key, table) as MemorySegment
}

fun NSPrinter.stringForKey_inTable(key: MemorySegment, table: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("stringForKey:inTable:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key, table) as MemorySegment
}

fun NSPrinter.stringListForKey_inTable(key: MemorySegment, table: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("stringListForKey:inTable:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, key, table) as MemorySegment
}

fun NSPrinter.imageRectForPaper(paperName: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("imageRectForPaper:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, paperName) as MemorySegment
}

fun NSPrinter.acceptsBinary(): Boolean {
    val sel = ObjCRuntime.sel("acceptsBinary")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSPrinter.isColor(): Boolean {
    val sel = ObjCRuntime.sel("isColor")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSPrinter.isFontAvailable(faceName: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isFontAvailable:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, faceName) as Boolean
}

fun NSPrinter.isOutputStackInReverseOrder(): Boolean {
    val sel = ObjCRuntime.sel("isOutputStackInReverseOrder")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSPrinter.domain(): MemorySegment {
    val sel = ObjCRuntime.sel("domain")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSPrinter.host(): MemorySegment {
    val sel = ObjCRuntime.sel("host")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSPrinter.note(): MemorySegment {
    val sel = ObjCRuntime.sel("note")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSPrinter printerWithName:domain:includeUnavailable:]
fun NSPrinter_printerWithName_domain_includeUnavailable(name: MemorySegment, domain: MemorySegment, flag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("printerWithName:domain:includeUnavailable:")
    val cls = ObjCRuntime.getClass("NSPrinter")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, name, domain, flag) as MemorySegment
}

