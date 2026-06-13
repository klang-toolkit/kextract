package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSScriptCommand
 * Superclass: NSObject
 * Protocols: NSCoding
 */
open class NSScriptCommand(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSScriptCommand") }
        
        fun currentCommand(): MemorySegment {
            val sel = ObjCRuntime.sel("currentCommand")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithCommandDescription(commandDef: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCommandDescription:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, commandDef) as MemorySegment
    }
    
    open fun initWithCoder(inCoder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, inCoder) as MemorySegment
    }
    
    open fun performDefaultImplementation(): MemorySegment {
        val sel = ObjCRuntime.sel("performDefaultImplementation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun executeCommand(): MemorySegment {
        val sel = ObjCRuntime.sel("executeCommand")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun suspendExecution(): Unit {
        val sel = ObjCRuntime.sel("suspendExecution")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resumeExecutionWithResult(result: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("resumeExecutionWithResult:")
        ObjCRuntime.msgSend(null, ptr, sel, result)
    }
    
    // @property commandDescription
    open fun commandDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("commandDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property directParameter
    open fun directParameter(): MemorySegment {
        val sel = ObjCRuntime.sel("directParameter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDirectParameter(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDirectParameter:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property receiversSpecifier
    open fun receiversSpecifier(): MemorySegment {
        val sel = ObjCRuntime.sel("receiversSpecifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setReceiversSpecifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setReceiversSpecifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property evaluatedReceivers
    open fun evaluatedReceivers(): MemorySegment {
        val sel = ObjCRuntime.sel("evaluatedReceivers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property arguments
    /** @return NSDictionary<NSString *,id> * */
    open fun arguments(): MemorySegment {
        val sel = ObjCRuntime.sel("arguments")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setArguments(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setArguments:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property evaluatedArguments
    /** @return NSDictionary<NSString *,id> * */
    open fun evaluatedArguments(): MemorySegment {
        val sel = ObjCRuntime.sel("evaluatedArguments")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property wellFormed
    open fun isWellFormed(): Boolean {
        val sel = ObjCRuntime.sel("isWellFormed")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property scriptErrorNumber
    open fun scriptErrorNumber(): Long {
        val sel = ObjCRuntime.sel("scriptErrorNumber")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setScriptErrorNumber(value: Long) {
        val sel = ObjCRuntime.sel("setScriptErrorNumber:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property scriptErrorOffendingObjectDescriptor
    open fun scriptErrorOffendingObjectDescriptor(): MemorySegment {
        val sel = ObjCRuntime.sel("scriptErrorOffendingObjectDescriptor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScriptErrorOffendingObjectDescriptor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScriptErrorOffendingObjectDescriptor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property scriptErrorExpectedTypeDescriptor
    open fun scriptErrorExpectedTypeDescriptor(): MemorySegment {
        val sel = ObjCRuntime.sel("scriptErrorExpectedTypeDescriptor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScriptErrorExpectedTypeDescriptor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScriptErrorExpectedTypeDescriptor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property scriptErrorString
    open fun scriptErrorString(): MemorySegment {
        val sel = ObjCRuntime.sel("scriptErrorString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScriptErrorString(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScriptErrorString:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun scriptErrorStringAsString(): String = ObjCRuntime.toJavaString(scriptErrorString())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setScriptErrorString(value: String) = setScriptErrorString(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property appleEvent
    open fun appleEvent(): MemorySegment {
        val sel = ObjCRuntime.sel("appleEvent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _commandDescription: MemorySegment
    // ivar: _directParameter: MemorySegment
    // ivar: _receiversSpecifier: MemorySegment
    // ivar: _evaluatedReceivers: MemorySegment
    // ivar: _arguments: MemorySegment
    // ivar: _evaluatedArguments: MemorySegment
    // ivar: _flags: MemorySegment
    // ivar: _moreVars: MemorySegment
    // ivar: _reserved: MemorySegment
}

