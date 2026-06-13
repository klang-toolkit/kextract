package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSScriptCommandDescription
 * Superclass: NSObject
 * Protocols: NSCoding
 */
open class NSScriptCommandDescription(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSScriptCommandDescription") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithSuiteName_commandName_dictionary(suiteName: MemorySegment, commandName: MemorySegment, commandDeclaration: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSuiteName:commandName:dictionary:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, suiteName, commandName, commandDeclaration) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithSuiteName_commandName_dictionary(suiteName: String, commandName: String, commandDeclaration: MemorySegment): MemorySegment = initWithSuiteName_commandName_dictionary(ObjCRuntime.newNSString(Arena.global(), suiteName), ObjCRuntime.newNSString(Arena.global(), commandName), commandDeclaration)
    
    open fun initWithCoder(inCoder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inCoder) as MemorySegment
    }
    
    open fun typeForArgumentWithName(argumentName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("typeForArgumentWithName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, argumentName) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun typeForArgumentWithNameAsString(argumentName: MemorySegment): String = ObjCRuntime.toJavaString(typeForArgumentWithName(argumentName))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun typeForArgumentWithName(argumentName: String): MemorySegment = typeForArgumentWithName(ObjCRuntime.newNSString(Arena.global(), argumentName))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun typeForArgumentWithNameAsString(argumentName: String): String = ObjCRuntime.toJavaString(typeForArgumentWithName(ObjCRuntime.newNSString(Arena.global(), argumentName)))
    
    open fun appleEventCodeForArgumentWithName(argumentName: MemorySegment): Int {
        val sel = ObjCRuntime.sel("appleEventCodeForArgumentWithName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel, argumentName) as Int
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun appleEventCodeForArgumentWithName(argumentName: String): Int = appleEventCodeForArgumentWithName(ObjCRuntime.newNSString(Arena.global(), argumentName))
    
    open fun isOptionalArgumentWithName(argumentName: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isOptionalArgumentWithName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, argumentName) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun isOptionalArgumentWithName(argumentName: String): Boolean = isOptionalArgumentWithName(ObjCRuntime.newNSString(Arena.global(), argumentName))
    
    open fun createCommandInstance(): MemorySegment {
        val sel = ObjCRuntime.sel("createCommandInstance")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun createCommandInstanceWithZone(zone: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("createCommandInstanceWithZone:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, zone) as MemorySegment
    }
    
    // @property suiteName
    open fun suiteName(): MemorySegment {
        val sel = ObjCRuntime.sel("suiteName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun suiteNameAsString(): String = ObjCRuntime.toJavaString(suiteName())
    
    // @property commandName
    open fun commandName(): MemorySegment {
        val sel = ObjCRuntime.sel("commandName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun commandNameAsString(): String = ObjCRuntime.toJavaString(commandName())
    
    // @property appleEventClassCode
    open fun appleEventClassCode(): Int {
        val sel = ObjCRuntime.sel("appleEventClassCode")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property appleEventCode
    open fun appleEventCode(): Int {
        val sel = ObjCRuntime.sel("appleEventCode")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property commandClassName
    open fun commandClassName(): MemorySegment {
        val sel = ObjCRuntime.sel("commandClassName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun commandClassNameAsString(): String = ObjCRuntime.toJavaString(commandClassName())
    
    // @property returnType
    open fun returnType(): MemorySegment {
        val sel = ObjCRuntime.sel("returnType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun returnTypeAsString(): String = ObjCRuntime.toJavaString(returnType())
    
    // @property appleEventCodeForReturnType
    open fun appleEventCodeForReturnType(): Int {
        val sel = ObjCRuntime.sel("appleEventCodeForReturnType")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property argumentNames
    /** @return NSArray<NSString *> * */
    open fun argumentNames(): MemorySegment {
        val sel = ObjCRuntime.sel("argumentNames")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _suiteName: MemorySegment
    // ivar: _plistCommandName: MemorySegment
    // ivar: _classAppleEventCode: Int
    // ivar: _idAppleEventCode: Int
    // ivar: _objcClassName: MemorySegment
    // ivar: _resultTypeNameOrDescription: MemorySegment
    // ivar: _plistResultTypeAppleEventCode: Int
    // ivar: _moreVars: MemorySegment
}

