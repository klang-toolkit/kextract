package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOrthography
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSOrthography(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOrthography") }
        
    }
    
    open fun initWithDominantScript_languageMap(script: MemorySegment, map: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDominantScript:languageMap:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, script, map) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithDominantScript_languageMap(script: String, map: MemorySegment): MemorySegment = initWithDominantScript_languageMap(ObjCRuntime.newNSString(Arena.global(), script), map)
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    // @property dominantScript
    open fun dominantScript(): MemorySegment {
        val sel = ObjCRuntime.sel("dominantScript")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun dominantScriptAsString(): String = ObjCRuntime.toJavaString(dominantScript())
    
    // @property languageMap
    /** @return NSDictionary<NSString *,NSArray<NSString *> *> * */
    open fun languageMap(): MemorySegment {
        val sel = ObjCRuntime.sel("languageMap")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSOrthographyExtended on NSOrthography ─────────────────────────────────────────

/** @return NSArray<NSString *> * */
fun NSOrthography.languagesForScript(script: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("languagesForScript:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, script) as MemorySegment
}

fun NSOrthography.dominantLanguageForScript(script: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dominantLanguageForScript:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, script) as MemorySegment
}

fun NSOrthography.dominantLanguage(): MemorySegment {
    val sel = ObjCRuntime.sel("dominantLanguage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<NSString *> * */
fun NSOrthography.allScripts(): MemorySegment {
    val sel = ObjCRuntime.sel("allScripts")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<NSString *> * */
fun NSOrthography.allLanguages(): MemorySegment {
    val sel = ObjCRuntime.sel("allLanguages")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSOrthography defaultOrthographyForLanguage:]
fun NSOrthography_defaultOrthographyForLanguage(language: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("defaultOrthographyForLanguage:")
    val cls = ObjCRuntime.getClass("NSOrthography")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, language) as MemorySegment
}

// ── Category: NSOrthographyCreation on NSOrthography ─────────────────────────────────────────

// Class method: +[NSOrthography orthographyWithDominantScript:languageMap:]
fun NSOrthography_orthographyWithDominantScript_languageMap(script: MemorySegment, map: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("orthographyWithDominantScript:languageMap:")
    val cls = ObjCRuntime.getClass("NSOrthography")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, script, map) as MemorySegment
}

