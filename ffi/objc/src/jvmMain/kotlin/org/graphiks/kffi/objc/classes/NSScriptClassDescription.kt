package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSScriptClassDescription
 * Superclass: NSClassDescription
 */
open class NSScriptClassDescription(override val ptr: MemorySegment) : NSClassDescription(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSScriptClassDescription") }
        
        fun classDescriptionForClass(aClass: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("classDescriptionForClass:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, aClass) as MemorySegment
        }
        
    }
    
    open fun initWithSuiteName_className_dictionary(suiteName: MemorySegment, className: MemorySegment, classDeclaration: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSuiteName:className:dictionary:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, suiteName, className, classDeclaration) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithSuiteName_className_dictionary(suiteName: String, className: String, classDeclaration: MemorySegment): MemorySegment = initWithSuiteName_className_dictionary(ObjCRuntime.newNSString(Arena.global(), suiteName), ObjCRuntime.newNSString(Arena.global(), className), classDeclaration)
    
    open fun matchesAppleEventCode(appleEventCode: Int): Boolean {
        val sel = ObjCRuntime.sel("matchesAppleEventCode:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, appleEventCode) as Boolean
    }
    
    open fun supportsCommand(commandDescription: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("supportsCommand:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, commandDescription) as Boolean
    }
    
    open fun selectorForCommand(commandDescription: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("selectorForCommand:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, commandDescription) as MemorySegment
    }
    
    open fun typeForKey(key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("typeForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun typeForKeyAsString(key: MemorySegment): String = ObjCRuntime.toJavaString(typeForKey(key))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun typeForKey(key: String): MemorySegment = typeForKey(ObjCRuntime.newNSString(Arena.global(), key))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun typeForKeyAsString(key: String): String = ObjCRuntime.toJavaString(typeForKey(ObjCRuntime.newNSString(Arena.global(), key)))
    
    open fun classDescriptionForKey(key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("classDescriptionForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun classDescriptionForKey(key: String): MemorySegment = classDescriptionForKey(ObjCRuntime.newNSString(Arena.global(), key))
    
    open fun appleEventCodeForKey(key: MemorySegment): Int {
        val sel = ObjCRuntime.sel("appleEventCodeForKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel, key) as Int
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun appleEventCodeForKey(key: String): Int = appleEventCodeForKey(ObjCRuntime.newNSString(Arena.global(), key))
    
    open fun keyWithAppleEventCode(appleEventCode: Int): MemorySegment {
        val sel = ObjCRuntime.sel("keyWithAppleEventCode:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, appleEventCode) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun keyWithAppleEventCodeAsString(appleEventCode: Int): String = ObjCRuntime.toJavaString(keyWithAppleEventCode(appleEventCode))
    
    open fun isLocationRequiredToCreateForKey(toManyRelationshipKey: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isLocationRequiredToCreateForKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, toManyRelationshipKey) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun isLocationRequiredToCreateForKey(toManyRelationshipKey: String): Boolean = isLocationRequiredToCreateForKey(ObjCRuntime.newNSString(Arena.global(), toManyRelationshipKey))
    
    open fun hasPropertyForKey(key: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("hasPropertyForKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, key) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun hasPropertyForKey(key: String): Boolean = hasPropertyForKey(ObjCRuntime.newNSString(Arena.global(), key))
    
    open fun hasOrderedToManyRelationshipForKey(key: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("hasOrderedToManyRelationshipForKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, key) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun hasOrderedToManyRelationshipForKey(key: String): Boolean = hasOrderedToManyRelationshipForKey(ObjCRuntime.newNSString(Arena.global(), key))
    
    open fun hasReadablePropertyForKey(key: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("hasReadablePropertyForKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, key) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun hasReadablePropertyForKey(key: String): Boolean = hasReadablePropertyForKey(ObjCRuntime.newNSString(Arena.global(), key))
    
    open fun hasWritablePropertyForKey(key: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("hasWritablePropertyForKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, key) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun hasWritablePropertyForKey(key: String): Boolean = hasWritablePropertyForKey(ObjCRuntime.newNSString(Arena.global(), key))
    
    // @property suiteName
    open fun suiteName(): MemorySegment {
        val sel = ObjCRuntime.sel("suiteName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun suiteNameAsString(): String = ObjCRuntime.toJavaString(suiteName())
    
    // @property className
    open fun className(): MemorySegment {
        val sel = ObjCRuntime.sel("className")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun classNameAsString(): String = ObjCRuntime.toJavaString(className())
    
    // @property implementationClassName
    open fun implementationClassName(): MemorySegment {
        val sel = ObjCRuntime.sel("implementationClassName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun implementationClassNameAsString(): String = ObjCRuntime.toJavaString(implementationClassName())
    
    // @property superclassDescription
    open fun superclassDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("superclassDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property appleEventCode
    open fun appleEventCode(): Int {
        val sel = ObjCRuntime.sel("appleEventCode")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property defaultSubcontainerAttributeKey
    open fun defaultSubcontainerAttributeKey(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultSubcontainerAttributeKey")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun defaultSubcontainerAttributeKeyAsString(): String = ObjCRuntime.toJavaString(defaultSubcontainerAttributeKey())
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _suiteName: MemorySegment
    // ivar: _objcClassName: MemorySegment
    // ivar: _appleEventCode: Int
    // ivar: _superclassNameOrDescription: MemorySegment
    // ivar: _attributeDescriptions: MemorySegment
    // ivar: _toOneRelationshipDescriptions: MemorySegment
    // ivar: _toManyRelationshipDescriptions: MemorySegment
    // ivar: _commandMethodSelectorsByName: MemorySegment
    // ivar: _moreVars: MemorySegment
}

// ── Category: NSDeprecated on NSScriptClassDescription ─────────────────────────────────────────

fun NSScriptClassDescription.isReadOnlyKey(key: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isReadOnlyKey:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, key) as Boolean
}

