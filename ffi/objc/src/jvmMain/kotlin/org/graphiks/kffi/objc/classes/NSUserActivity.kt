package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUserActivity
 * Superclass: NSObject
 */
open class NSUserActivity(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUserActivity") }
        
        fun deleteSavedUserActivitiesWithPersistentIdentifiers_completionHandler(persistentIdentifiers: MemorySegment, handler: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("deleteSavedUserActivitiesWithPersistentIdentifiers:completionHandler:")
            ObjCRuntime.msgSend(null, _class, sel, persistentIdentifiers, handler)
        }
        
        fun deleteAllSavedUserActivitiesWithCompletionHandler(handler: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("deleteAllSavedUserActivitiesWithCompletionHandler:")
            ObjCRuntime.msgSend(null, _class, sel, handler)
        }
        
    }
    
    open fun initWithActivityType(activityType: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithActivityType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, activityType) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithActivityType(activityType: String): MemorySegment = initWithActivityType(ObjCRuntime.newNSString(Arena.global(), activityType))
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun addUserInfoEntriesFromDictionary(otherDictionary: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addUserInfoEntriesFromDictionary:")
        ObjCRuntime.msgSend(null, ptr, sel, otherDictionary)
    }
    
    open fun becomeCurrent(): Unit {
        val sel = ObjCRuntime.sel("becomeCurrent")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resignCurrent(): Unit {
        val sel = ObjCRuntime.sel("resignCurrent")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun invalidate(): Unit {
        val sel = ObjCRuntime.sel("invalidate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun getContinuationStreamsWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getContinuationStreamsWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    // @property activityType
    open fun activityType(): MemorySegment {
        val sel = ObjCRuntime.sel("activityType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun activityTypeAsString(): String = ObjCRuntime.toJavaString(activityType())
    
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
    
    // @property userInfo
    open fun userInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("userInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUserInfo(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUserInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property requiredUserInfoKeys
    /** @return NSSet<NSString *> * */
    open fun requiredUserInfoKeys(): MemorySegment {
        val sel = ObjCRuntime.sel("requiredUserInfoKeys")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRequiredUserInfoKeys(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRequiredUserInfoKeys:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property needsSave
    open fun needsSave(): Boolean {
        val sel = ObjCRuntime.sel("needsSave")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setNeedsSave(value: Boolean) {
        val sel = ObjCRuntime.sel("setNeedsSave:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property webpageURL
    open fun webpageURL(): MemorySegment {
        val sel = ObjCRuntime.sel("webpageURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setWebpageURL(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setWebpageURL:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property referrerURL
    open fun referrerURL(): MemorySegment {
        val sel = ObjCRuntime.sel("referrerURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setReferrerURL(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setReferrerURL:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property expirationDate
    open fun expirationDate(): MemorySegment {
        val sel = ObjCRuntime.sel("expirationDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setExpirationDate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setExpirationDate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property keywords
    /** @return NSSet<NSString *> * */
    open fun keywords(): MemorySegment {
        val sel = ObjCRuntime.sel("keywords")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setKeywords(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setKeywords:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property supportsContinuationStreams
    open fun supportsContinuationStreams(): Boolean {
        val sel = ObjCRuntime.sel("supportsContinuationStreams")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSupportsContinuationStreams(value: Boolean) {
        val sel = ObjCRuntime.sel("setSupportsContinuationStreams:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSUserActivityDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property targetContentIdentifier
    open fun targetContentIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("targetContentIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTargetContentIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTargetContentIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun targetContentIdentifierAsString(): String = ObjCRuntime.toJavaString(targetContentIdentifier())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setTargetContentIdentifier(value: String) = setTargetContentIdentifier(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property eligibleForHandoff
    open fun isEligibleForHandoff(): Boolean {
        val sel = ObjCRuntime.sel("isEligibleForHandoff")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEligibleForHandoff(value: Boolean) {
        val sel = ObjCRuntime.sel("setEligibleForHandoff:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property eligibleForSearch
    open fun isEligibleForSearch(): Boolean {
        val sel = ObjCRuntime.sel("isEligibleForSearch")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEligibleForSearch(value: Boolean) {
        val sel = ObjCRuntime.sel("setEligibleForSearch:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property eligibleForPublicIndexing
    open fun isEligibleForPublicIndexing(): Boolean {
        val sel = ObjCRuntime.sel("isEligibleForPublicIndexing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEligibleForPublicIndexing(value: Boolean) {
        val sel = ObjCRuntime.sel("setEligibleForPublicIndexing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property eligibleForPrediction
    open fun isEligibleForPrediction(): Boolean {
        val sel = ObjCRuntime.sel("isEligibleForPrediction")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEligibleForPrediction(value: Boolean) {
        val sel = ObjCRuntime.sel("setEligibleForPrediction:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property persistentIdentifier
    open fun persistentIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("persistentIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPersistentIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPersistentIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

