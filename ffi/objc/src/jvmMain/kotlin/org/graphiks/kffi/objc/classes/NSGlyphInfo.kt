package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSGlyphInfo
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSGlyphInfo(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSGlyphInfo") }
        
        fun glyphInfoWithCGGlyph_forFont_baseString(glyph: Short, font: MemorySegment, string: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("glyphInfoWithCGGlyph:forFont:baseString:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, glyph, font, string) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun glyphInfoWithCGGlyph_forFont_baseString(glyph: Short, font: MemorySegment, string: String): MemorySegment = glyphInfoWithCGGlyph_forFont_baseString(glyph, font, ObjCRuntime.newNSString(Arena.global(), string))
        
    }
    
    // @property glyphID
    open fun glyphID(): Short {
        val sel = ObjCRuntime.sel("glyphID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, ptr, sel) as Short
    }
    
    // @property baseString
    open fun baseString(): MemorySegment {
        val sel = ObjCRuntime.sel("baseString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun baseStringAsString(): String = ObjCRuntime.toJavaString(baseString())
    
}

// ── Category: NSGlyphInfo_Deprecated on NSGlyphInfo ─────────────────────────────────────────

fun NSGlyphInfo.glyphName(): MemorySegment {
    val sel = ObjCRuntime.sel("glyphName")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSGlyphInfo.characterIdentifier(): Long {
    val sel = ObjCRuntime.sel("characterIdentifier")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSGlyphInfo.characterCollection(): MemorySegment {
    val sel = ObjCRuntime.sel("characterCollection")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSGlyphInfo glyphInfoWithGlyphName:forFont:baseString:]
fun NSGlyphInfo_glyphInfoWithGlyphName_forFont_baseString(glyphName: MemorySegment, font: MemorySegment, string: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("glyphInfoWithGlyphName:forFont:baseString:")
    val cls = ObjCRuntime.getClass("NSGlyphInfo")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, glyphName, font, string) as MemorySegment
}

// Class method: +[NSGlyphInfo glyphInfoWithGlyph:forFont:baseString:]
fun NSGlyphInfo_glyphInfoWithGlyph_forFont_baseString(glyph: Int, font: MemorySegment, string: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("glyphInfoWithGlyph:forFont:baseString:")
    val cls = ObjCRuntime.getClass("NSGlyphInfo")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, glyph, font, string) as MemorySegment
}

// Class method: +[NSGlyphInfo glyphInfoWithCharacterIdentifier:collection:baseString:]
fun NSGlyphInfo_glyphInfoWithCharacterIdentifier_collection_baseString(cid: Long, characterCollection: MemorySegment, string: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("glyphInfoWithCharacterIdentifier:collection:baseString:")
    val cls = ObjCRuntime.getClass("NSGlyphInfo")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, cid, characterCollection, string) as MemorySegment
}

