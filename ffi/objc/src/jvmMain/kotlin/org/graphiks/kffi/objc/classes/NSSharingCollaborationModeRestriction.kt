package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSharingCollaborationModeRestriction
 * Superclass: NSObject
 * Protocols: NSSecureCoding, NSCopying
 */
open class NSSharingCollaborationModeRestriction(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSharingCollaborationModeRestriction") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithDisabledMode(disabledMode: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDisabledMode:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, disabledMode) as MemorySegment
    }
    
    open fun initWithDisabledMode_alertTitle_alertMessage(disabledMode: MemorySegment, alertTitle: MemorySegment, alertMessage: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDisabledMode:alertTitle:alertMessage:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, disabledMode, alertTitle, alertMessage) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithDisabledMode_alertTitle_alertMessage(disabledMode: MemorySegment, alertTitle: String, alertMessage: String): MemorySegment = initWithDisabledMode_alertTitle_alertMessage(disabledMode, ObjCRuntime.newNSString(Arena.global(), alertTitle), ObjCRuntime.newNSString(Arena.global(), alertMessage))
    
    open fun initWithDisabledMode_alertTitle_alertMessage_alertDismissButtonTitle(disabledMode: MemorySegment, alertTitle: MemorySegment, alertMessage: MemorySegment, alertDismissButtonTitle: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDisabledMode:alertTitle:alertMessage:alertDismissButtonTitle:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, disabledMode, alertTitle, alertMessage, alertDismissButtonTitle) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithDisabledMode_alertTitle_alertMessage_alertDismissButtonTitle(disabledMode: MemorySegment, alertTitle: String, alertMessage: String, alertDismissButtonTitle: String): MemorySegment = initWithDisabledMode_alertTitle_alertMessage_alertDismissButtonTitle(disabledMode, ObjCRuntime.newNSString(Arena.global(), alertTitle), ObjCRuntime.newNSString(Arena.global(), alertMessage), ObjCRuntime.newNSString(Arena.global(), alertDismissButtonTitle))
    
    open fun initWithDisabledMode_alertTitle_alertMessage_alertDismissButtonTitle_alertRecoverySuggestionButtonTitle_alertRecoverySuggestionButtonLaunchURL(disabledMode: MemorySegment, alertTitle: MemorySegment, alertMessage: MemorySegment, alertDismissButtonTitle: MemorySegment, alertRecoverySuggestionButtonTitle: MemorySegment, alertRecoverySuggestionButtonLaunchURL: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDisabledMode:alertTitle:alertMessage:alertDismissButtonTitle:alertRecoverySuggestionButtonTitle:alertRecoverySuggestionButtonLaunchURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, disabledMode, alertTitle, alertMessage, alertDismissButtonTitle, alertRecoverySuggestionButtonTitle, alertRecoverySuggestionButtonLaunchURL) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithDisabledMode_alertTitle_alertMessage_alertDismissButtonTitle_alertRecoverySuggestionButtonTitle_alertRecoverySuggestionButtonLaunchURL(disabledMode: MemorySegment, alertTitle: String, alertMessage: String, alertDismissButtonTitle: String, alertRecoverySuggestionButtonTitle: String, alertRecoverySuggestionButtonLaunchURL: MemorySegment): MemorySegment = initWithDisabledMode_alertTitle_alertMessage_alertDismissButtonTitle_alertRecoverySuggestionButtonTitle_alertRecoverySuggestionButtonLaunchURL(disabledMode, ObjCRuntime.newNSString(Arena.global(), alertTitle), ObjCRuntime.newNSString(Arena.global(), alertMessage), ObjCRuntime.newNSString(Arena.global(), alertDismissButtonTitle), ObjCRuntime.newNSString(Arena.global(), alertRecoverySuggestionButtonTitle), alertRecoverySuggestionButtonLaunchURL)
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property disabledMode
    open fun disabledMode(): MemorySegment {
        val sel = ObjCRuntime.sel("disabledMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property alertTitle
    open fun alertTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("alertTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun alertTitleAsString(): String = ObjCRuntime.toJavaString(alertTitle())
    
    // @property alertMessage
    open fun alertMessage(): MemorySegment {
        val sel = ObjCRuntime.sel("alertMessage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun alertMessageAsString(): String = ObjCRuntime.toJavaString(alertMessage())
    
    // @property alertDismissButtonTitle
    open fun alertDismissButtonTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("alertDismissButtonTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun alertDismissButtonTitleAsString(): String = ObjCRuntime.toJavaString(alertDismissButtonTitle())
    
    // @property alertRecoverySuggestionButtonTitle
    open fun alertRecoverySuggestionButtonTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("alertRecoverySuggestionButtonTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun alertRecoverySuggestionButtonTitleAsString(): String = ObjCRuntime.toJavaString(alertRecoverySuggestionButtonTitle())
    
    // @property alertRecoverySuggestionButtonLaunchURL
    open fun alertRecoverySuggestionButtonLaunchURL(): MemorySegment {
        val sel = ObjCRuntime.sel("alertRecoverySuggestionButtonLaunchURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

