package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMorphology
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSMorphology(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMorphology") }
        
    }
    
    // @property grammaticalGender
    open fun grammaticalGender(): MemorySegment {
        val sel = ObjCRuntime.sel("grammaticalGender")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setGrammaticalGender(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setGrammaticalGender:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property partOfSpeech
    open fun partOfSpeech(): MemorySegment {
        val sel = ObjCRuntime.sel("partOfSpeech")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPartOfSpeech(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPartOfSpeech:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property number
    open fun number(): MemorySegment {
        val sel = ObjCRuntime.sel("number")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setNumber(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setNumber:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property grammaticalCase
    open fun grammaticalCase(): MemorySegment {
        val sel = ObjCRuntime.sel("grammaticalCase")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setGrammaticalCase(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setGrammaticalCase:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property determination
    open fun determination(): MemorySegment {
        val sel = ObjCRuntime.sel("determination")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDetermination(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDetermination:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property grammaticalPerson
    open fun grammaticalPerson(): MemorySegment {
        val sel = ObjCRuntime.sel("grammaticalPerson")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setGrammaticalPerson(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setGrammaticalPerson:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pronounType
    open fun pronounType(): MemorySegment {
        val sel = ObjCRuntime.sel("pronounType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPronounType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPronounType:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property definiteness
    open fun definiteness(): MemorySegment {
        val sel = ObjCRuntime.sel("definiteness")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDefiniteness(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDefiniteness:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSCustomPronouns on NSMorphology ─────────────────────────────────────────

fun NSMorphology.customPronounForLanguage(language: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("customPronounForLanguage:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, language) as MemorySegment
}

fun NSMorphology.setCustomPronoun_forLanguage_error(features: MemorySegment, language: MemorySegment, error: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("setCustomPronoun:forLanguage:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, features, language, error) as Boolean
}

// ── Category: NSMorphologyUserSettings on NSMorphology ─────────────────────────────────────────

fun NSMorphology.isUnspecified(): Boolean {
    val sel = ObjCRuntime.sel("isUnspecified")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// Class method: +[NSMorphology userMorphology]
fun NSMorphology_userMorphology(): MemorySegment {
    val sel = ObjCRuntime.sel("userMorphology")
    val cls = ObjCRuntime.getClass("NSMorphology")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// @property userMorphology
fun NSMorphology.userMorphology(): MemorySegment {
    val sel = ObjCRuntime.sel("userMorphology")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

