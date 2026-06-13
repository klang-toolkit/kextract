package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFont
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSFont(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFont") }
        
        fun fontWithName_size(fontName: MemorySegment, fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("fontWithName:size:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontName, fontSize) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun fontWithName_size(fontName: String, fontSize: Double): MemorySegment = fontWithName_size(ObjCRuntime.newNSString(Arena.global(), fontName), fontSize)
        
        fun fontWithName_matrix(fontName: MemorySegment, fontMatrix: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontWithName:matrix:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontName, fontMatrix) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun fontWithName_matrix(fontName: String, fontMatrix: MemorySegment): MemorySegment = fontWithName_matrix(ObjCRuntime.newNSString(Arena.global(), fontName), fontMatrix)
        
        fun fontWithDescriptor_size(fontDescriptor: MemorySegment, fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("fontWithDescriptor:size:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontDescriptor, fontSize) as MemorySegment
        }
        
        fun fontWithDescriptor_textTransform(fontDescriptor: MemorySegment, textTransform: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontWithDescriptor:textTransform:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontDescriptor, textTransform) as MemorySegment
        }
        
        fun userFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("userFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun userFixedPitchFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("userFixedPitchFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun setUserFont(font: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setUserFont:")
            ObjCRuntime.msgSend(null, _class, sel, font)
        }
        
        fun setUserFixedPitchFont(font: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setUserFixedPitchFont:")
            ObjCRuntime.msgSend(null, _class, sel, font)
        }
        
        fun systemFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("systemFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun boldSystemFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("boldSystemFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun labelFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("labelFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun titleBarFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("titleBarFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun menuFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("menuFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun menuBarFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("menuBarFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun messageFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("messageFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun paletteFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("paletteFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun toolTipsFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("toolTipsFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun controlContentFontOfSize(fontSize: Double): MemorySegment {
            val sel = ObjCRuntime.sel("controlContentFontOfSize:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize) as MemorySegment
        }
        
        fun systemFontOfSize_weight(fontSize: Double, weight: Double): MemorySegment {
            val sel = ObjCRuntime.sel("systemFontOfSize:weight:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize, weight) as MemorySegment
        }
        
        fun monospacedDigitSystemFontOfSize_weight(fontSize: Double, weight: Double): MemorySegment {
            val sel = ObjCRuntime.sel("monospacedDigitSystemFontOfSize:weight:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize, weight) as MemorySegment
        }
        
        fun systemFontOfSize_weight_width(fontSize: Double, weight: Double, width: Double): MemorySegment {
            val sel = ObjCRuntime.sel("systemFontOfSize:weight:width:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize, weight, width) as MemorySegment
        }
        
        fun monospacedSystemFontOfSize_weight(fontSize: Double, weight: Double): MemorySegment {
            val sel = ObjCRuntime.sel("monospacedSystemFontOfSize:weight:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fontSize, weight) as MemorySegment
        }
        
        fun systemFontSizeForControlSize(controlSize: MemorySegment): Double {
            val sel = ObjCRuntime.sel("systemFontSizeForControlSize:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel, controlSize) as Double
        }
        
        fun systemFontSize(): Double {
            val sel = ObjCRuntime.sel("systemFontSize")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel) as Double
        }
        
        fun smallSystemFontSize(): Double {
            val sel = ObjCRuntime.sel("smallSystemFontSize")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel) as Double
        }
        
        fun labelFontSize(): Double {
            val sel = ObjCRuntime.sel("labelFontSize")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel) as Double
        }
        
    }
    
    open fun fontWithSize(fontSize: Double): MemorySegment {
        val sel = ObjCRuntime.sel("fontWithSize:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, fontSize) as MemorySegment
    }
    
    open fun boundingRectForCGGlyph(glyph: Short): MemorySegment {
        val sel = ObjCRuntime.sel("boundingRectForCGGlyph:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, glyph) as MemorySegment
    }
    
    open fun advancementForCGGlyph(glyph: Short): MemorySegment {
        val sel = ObjCRuntime.sel("advancementForCGGlyph:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel, glyph) as MemorySegment
    }
    
    open fun getBoundingRects_forCGGlyphs_count(bounds: MemorySegment, glyphs: MemorySegment, glyphCount: Long): Unit {
        val sel = ObjCRuntime.sel("getBoundingRects:forCGGlyphs:count:")
        ObjCRuntime.msgSend(null, ptr, sel, bounds, glyphs, glyphCount)
    }
    
    open fun getAdvancements_forCGGlyphs_count(advancements: MemorySegment, glyphs: MemorySegment, glyphCount: Long): Unit {
        val sel = ObjCRuntime.sel("getAdvancements:forCGGlyphs:count:")
        ObjCRuntime.msgSend(null, ptr, sel, advancements, glyphs, glyphCount)
    }
    
    open fun `set`(): Unit {
        val sel = ObjCRuntime.sel("set")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setInContext(graphicsContext: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setInContext:")
        ObjCRuntime.msgSend(null, ptr, sel, graphicsContext)
    }
    
    // @property systemFontSize
    open fun systemFontSize(): Double {
        val sel = ObjCRuntime.sel("systemFontSize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property smallSystemFontSize
    open fun smallSystemFontSize(): Double {
        val sel = ObjCRuntime.sel("smallSystemFontSize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property labelFontSize
    open fun labelFontSize(): Double {
        val sel = ObjCRuntime.sel("labelFontSize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property fontName
    open fun fontName(): MemorySegment {
        val sel = ObjCRuntime.sel("fontName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun fontNameAsString(): String = ObjCRuntime.toJavaString(fontName())
    
    // @property pointSize
    open fun pointSize(): Double {
        val sel = ObjCRuntime.sel("pointSize")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property matrix
    open fun matrix(): MemorySegment {
        val sel = ObjCRuntime.sel("matrix")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property familyName
    open fun familyName(): MemorySegment {
        val sel = ObjCRuntime.sel("familyName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun familyNameAsString(): String = ObjCRuntime.toJavaString(familyName())
    
    // @property displayName
    open fun displayName(): MemorySegment {
        val sel = ObjCRuntime.sel("displayName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun displayNameAsString(): String = ObjCRuntime.toJavaString(displayName())
    
    // @property fontDescriptor
    open fun fontDescriptor(): MemorySegment {
        val sel = ObjCRuntime.sel("fontDescriptor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property textTransform
    open fun textTransform(): MemorySegment {
        val sel = ObjCRuntime.sel("textTransform")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property numberOfGlyphs
    open fun numberOfGlyphs(): Long {
        val sel = ObjCRuntime.sel("numberOfGlyphs")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property mostCompatibleStringEncoding
    open fun mostCompatibleStringEncoding(): Long {
        val sel = ObjCRuntime.sel("mostCompatibleStringEncoding")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property coveredCharacterSet
    open fun coveredCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("coveredCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property boundingRectForFont
    open fun boundingRectForFont(): MemorySegment {
        val sel = ObjCRuntime.sel("boundingRectForFont")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property maximumAdvancement
    open fun maximumAdvancement(): MemorySegment {
        val sel = ObjCRuntime.sel("maximumAdvancement")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    
    // @property ascender
    open fun ascender(): Double {
        val sel = ObjCRuntime.sel("ascender")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property descender
    open fun descender(): Double {
        val sel = ObjCRuntime.sel("descender")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property leading
    open fun leading(): Double {
        val sel = ObjCRuntime.sel("leading")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property underlinePosition
    open fun underlinePosition(): Double {
        val sel = ObjCRuntime.sel("underlinePosition")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property underlineThickness
    open fun underlineThickness(): Double {
        val sel = ObjCRuntime.sel("underlineThickness")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property italicAngle
    open fun italicAngle(): Double {
        val sel = ObjCRuntime.sel("italicAngle")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property capHeight
    open fun capHeight(): Double {
        val sel = ObjCRuntime.sel("capHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property xHeight
    open fun xHeight(): Double {
        val sel = ObjCRuntime.sel("xHeight")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property fixedPitch
    open fun isFixedPitch(): Boolean {
        val sel = ObjCRuntime.sel("isFixedPitch")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property verticalFont
    open fun verticalFont(): MemorySegment {
        val sel = ObjCRuntime.sel("verticalFont")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property vertical
    open fun isVertical(): Boolean {
        val sel = ObjCRuntime.sel("isVertical")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSFont_Deprecated on NSFont ─────────────────────────────────────────

fun NSFont.glyphWithName(name: MemorySegment): Int {
    val sel = ObjCRuntime.sel("glyphWithName:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, this.ptr, sel, name) as Int
}

fun NSFont.boundingRectForGlyph(glyph: Int): MemorySegment {
    val sel = ObjCRuntime.sel("boundingRectForGlyph:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel, glyph) as MemorySegment
}

fun NSFont.advancementForGlyph(glyph: Int): MemorySegment {
    val sel = ObjCRuntime.sel("advancementForGlyph:")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel, glyph) as MemorySegment
}

fun NSFont.getBoundingRects_forGlyphs_count(bounds: MemorySegment, glyphs: MemorySegment, glyphCount: Long): Unit {
    val sel = ObjCRuntime.sel("getBoundingRects:forGlyphs:count:")
    ObjCRuntime.msgSend(null, this.ptr, sel, bounds, glyphs, glyphCount)
}

fun NSFont.getAdvancements_forGlyphs_count(advancements: MemorySegment, glyphs: MemorySegment, glyphCount: Long): Unit {
    val sel = ObjCRuntime.sel("getAdvancements:forGlyphs:count:")
    ObjCRuntime.msgSend(null, this.ptr, sel, advancements, glyphs, glyphCount)
}

fun NSFont.getAdvancements_forPackedGlyphs_length(advancements: MemorySegment, packedGlyphs: MemorySegment, length: Long): Unit {
    val sel = ObjCRuntime.sel("getAdvancements:forPackedGlyphs:length:")
    ObjCRuntime.msgSend(null, this.ptr, sel, advancements, packedGlyphs, length)
}

fun NSFont.screenFontWithRenderingMode(renderingMode: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("screenFontWithRenderingMode:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, renderingMode) as MemorySegment
}

fun NSFont.printerFont(): MemorySegment {
    val sel = ObjCRuntime.sel("printerFont")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSFont.screenFont(): MemorySegment {
    val sel = ObjCRuntime.sel("screenFont")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSFont.renderingMode(): MemorySegment {
    val sel = ObjCRuntime.sel("renderingMode")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSFont_TextStyles on NSFont ─────────────────────────────────────────

// Class method: +[NSFont preferredFontForTextStyle:options:]
fun NSFont_preferredFontForTextStyle_options(style: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("preferredFontForTextStyle:options:")
    val cls = ObjCRuntime.getClass("NSFont")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, style, options) as MemorySegment
}

