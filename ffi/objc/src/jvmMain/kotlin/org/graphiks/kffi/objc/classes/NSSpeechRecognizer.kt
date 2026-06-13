package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSpeechRecognizer
 * Superclass: NSObject
 */
open class NSSpeechRecognizer(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSpeechRecognizer") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun startListening(): Unit {
        val sel = ObjCRuntime.sel("startListening")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun stopListening(): Unit {
        val sel = ObjCRuntime.sel("stopListening")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property delegate
    /** @return id<NSSpeechRecognizerDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property commands
    /** @return NSArray<NSString *> * */
    open fun commands(): MemorySegment {
        val sel = ObjCRuntime.sel("commands")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCommands(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCommands:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property displayedCommandsTitle
    open fun displayedCommandsTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("displayedCommandsTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDisplayedCommandsTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDisplayedCommandsTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun displayedCommandsTitleAsString(): String = ObjCRuntime.toJavaString(displayedCommandsTitle())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setDisplayedCommandsTitle(value: String) = setDisplayedCommandsTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property listensInForegroundOnly
    open fun listensInForegroundOnly(): Boolean {
        val sel = ObjCRuntime.sel("listensInForegroundOnly")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setListensInForegroundOnly(value: Boolean) {
        val sel = ObjCRuntime.sel("setListensInForegroundOnly:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property blocksOtherRecognizers
    open fun blocksOtherRecognizers(): Boolean {
        val sel = ObjCRuntime.sel("blocksOtherRecognizers")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setBlocksOtherRecognizers(value: Boolean) {
        val sel = ObjCRuntime.sel("setBlocksOtherRecognizers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

