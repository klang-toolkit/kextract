package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUserNotification
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSUserNotification(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUserNotification") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property title
    open fun title(): MemorySegment {
        val sel = ObjCRuntime.sel("title")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun titleAsString(): String = ObjCRuntime.toJavaString(title())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setTitle(value: String) = setTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property subtitle
    open fun subtitle(): MemorySegment {
        val sel = ObjCRuntime.sel("subtitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSubtitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSubtitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun subtitleAsString(): String = ObjCRuntime.toJavaString(subtitle())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setSubtitle(value: String) = setSubtitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property informativeText
    open fun informativeText(): MemorySegment {
        val sel = ObjCRuntime.sel("informativeText")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInformativeText(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInformativeText:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun informativeTextAsString(): String = ObjCRuntime.toJavaString(informativeText())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setInformativeText(value: String) = setInformativeText(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property actionButtonTitle
    open fun actionButtonTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("actionButtonTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setActionButtonTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setActionButtonTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun actionButtonTitleAsString(): String = ObjCRuntime.toJavaString(actionButtonTitle())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setActionButtonTitle(value: String) = setActionButtonTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property userInfo
    /** @return NSDictionary<NSString *,id> * */
    open fun userInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("userInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUserInfo(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUserInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property deliveryDate
    open fun deliveryDate(): MemorySegment {
        val sel = ObjCRuntime.sel("deliveryDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDeliveryDate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDeliveryDate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property deliveryTimeZone
    open fun deliveryTimeZone(): MemorySegment {
        val sel = ObjCRuntime.sel("deliveryTimeZone")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDeliveryTimeZone(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDeliveryTimeZone:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property deliveryRepeatInterval
    open fun deliveryRepeatInterval(): MemorySegment {
        val sel = ObjCRuntime.sel("deliveryRepeatInterval")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDeliveryRepeatInterval(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDeliveryRepeatInterval:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property actualDeliveryDate
    open fun actualDeliveryDate(): MemorySegment {
        val sel = ObjCRuntime.sel("actualDeliveryDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property presented
    open fun isPresented(): Boolean {
        val sel = ObjCRuntime.sel("isPresented")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property remote
    open fun isRemote(): Boolean {
        val sel = ObjCRuntime.sel("isRemote")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property soundName
    open fun soundName(): MemorySegment {
        val sel = ObjCRuntime.sel("soundName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSoundName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSoundName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun soundNameAsString(): String = ObjCRuntime.toJavaString(soundName())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setSoundName(value: String) = setSoundName(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property hasActionButton
    open fun hasActionButton(): Boolean {
        val sel = ObjCRuntime.sel("hasActionButton")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHasActionButton(value: Boolean) {
        val sel = ObjCRuntime.sel("setHasActionButton:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property activationType
    open fun activationType(): MemorySegment {
        val sel = ObjCRuntime.sel("activationType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property otherButtonTitle
    open fun otherButtonTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("otherButtonTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setOtherButtonTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setOtherButtonTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun otherButtonTitleAsString(): String = ObjCRuntime.toJavaString(otherButtonTitle())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setOtherButtonTitle(value: String) = setOtherButtonTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property identifier
    open fun identifier(): MemorySegment {
        val sel = ObjCRuntime.sel("identifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun identifierAsString(): String = ObjCRuntime.toJavaString(identifier())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setIdentifier(value: String) = setIdentifier(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property contentImage
    open fun contentImage(): MemorySegment {
        val sel = ObjCRuntime.sel("contentImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContentImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasReplyButton
    open fun hasReplyButton(): Boolean {
        val sel = ObjCRuntime.sel("hasReplyButton")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHasReplyButton(value: Boolean) {
        val sel = ObjCRuntime.sel("setHasReplyButton:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property responsePlaceholder
    open fun responsePlaceholder(): MemorySegment {
        val sel = ObjCRuntime.sel("responsePlaceholder")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setResponsePlaceholder(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setResponsePlaceholder:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun responsePlaceholderAsString(): String = ObjCRuntime.toJavaString(responsePlaceholder())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setResponsePlaceholder(value: String) = setResponsePlaceholder(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property response
    open fun response(): MemorySegment {
        val sel = ObjCRuntime.sel("response")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property additionalActions
    /** @return NSArray<NSUserNotificationAction *> * */
    open fun additionalActions(): MemorySegment {
        val sel = ObjCRuntime.sel("additionalActions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAdditionalActions(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAdditionalActions:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property additionalActivationAction
    open fun additionalActivationAction(): MemorySegment {
        val sel = ObjCRuntime.sel("additionalActivationAction")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _internal: MemorySegment
}

