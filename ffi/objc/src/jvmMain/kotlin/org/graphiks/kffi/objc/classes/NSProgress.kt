package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSProgress
 * Superclass: NSObject
 */
open class NSProgress(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSProgress") }
        
        fun currentProgress(): MemorySegment {
            val sel = ObjCRuntime.sel("currentProgress")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun progressWithTotalUnitCount(unitCount: Long): MemorySegment {
            val sel = ObjCRuntime.sel("progressWithTotalUnitCount:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, unitCount) as MemorySegment
        }
        
        fun discreteProgressWithTotalUnitCount(unitCount: Long): MemorySegment {
            val sel = ObjCRuntime.sel("discreteProgressWithTotalUnitCount:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, unitCount) as MemorySegment
        }
        
        fun progressWithTotalUnitCount_parent_pendingUnitCount(unitCount: Long, parent: MemorySegment, portionOfParentTotalUnitCount: Long): MemorySegment {
            val sel = ObjCRuntime.sel("progressWithTotalUnitCount:parent:pendingUnitCount:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, unitCount, parent, portionOfParentTotalUnitCount) as MemorySegment
        }
        
        fun addSubscriberForFileURL_withPublishingHandler(url: MemorySegment, publishingHandler: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("addSubscriberForFileURL:withPublishingHandler:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url, publishingHandler) as MemorySegment
        }
        
        fun removeSubscriber(subscriber: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("removeSubscriber:")
            ObjCRuntime.msgSend(null, _class, sel, subscriber)
        }
        
    }
    
    open fun initWithParent_userInfo(parentProgressOrNil: MemorySegment, userInfoOrNil: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithParent:userInfo:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, parentProgressOrNil, userInfoOrNil) as MemorySegment
    }
    
    open fun becomeCurrentWithPendingUnitCount(unitCount: Long): Unit {
        val sel = ObjCRuntime.sel("becomeCurrentWithPendingUnitCount:")
        ObjCRuntime.msgSend(null, ptr, sel, unitCount)
    }
    
    open fun performAsCurrentWithPendingUnitCount_usingBlock(unitCount: Long, work: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performAsCurrentWithPendingUnitCount:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, unitCount, work)
    }
    
    open fun resignCurrent(): Unit {
        val sel = ObjCRuntime.sel("resignCurrent")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun addChild_withPendingUnitCount(child: MemorySegment, inUnitCount: Long): Unit {
        val sel = ObjCRuntime.sel("addChild:withPendingUnitCount:")
        ObjCRuntime.msgSend(null, ptr, sel, child, inUnitCount)
    }
    
    open fun setUserInfoObject_forKey(objectOrNil: MemorySegment, key: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setUserInfoObject:forKey:")
        ObjCRuntime.msgSend(null, ptr, sel, objectOrNil, key)
    }
    
    open fun cancel(): Unit {
        val sel = ObjCRuntime.sel("cancel")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun pause(): Unit {
        val sel = ObjCRuntime.sel("pause")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resume(): Unit {
        val sel = ObjCRuntime.sel("resume")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun publish(): Unit {
        val sel = ObjCRuntime.sel("publish")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun unpublish(): Unit {
        val sel = ObjCRuntime.sel("unpublish")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property totalUnitCount
    open fun totalUnitCount(): Long {
        val sel = ObjCRuntime.sel("totalUnitCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setTotalUnitCount(value: Long) {
        val sel = ObjCRuntime.sel("setTotalUnitCount:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property completedUnitCount
    open fun completedUnitCount(): Long {
        val sel = ObjCRuntime.sel("completedUnitCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setCompletedUnitCount(value: Long) {
        val sel = ObjCRuntime.sel("setCompletedUnitCount:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property localizedDescription
    open fun localizedDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLocalizedDescription(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLocalizedDescription:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun localizedDescriptionAsString(): String = ObjCRuntime.toJavaString(localizedDescription())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setLocalizedDescription(value: String) = setLocalizedDescription(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property localizedAdditionalDescription
    open fun localizedAdditionalDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedAdditionalDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLocalizedAdditionalDescription(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLocalizedAdditionalDescription:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun localizedAdditionalDescriptionAsString(): String = ObjCRuntime.toJavaString(localizedAdditionalDescription())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setLocalizedAdditionalDescription(value: String) = setLocalizedAdditionalDescription(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property cancellable
    open fun isCancellable(): Boolean {
        val sel = ObjCRuntime.sel("isCancellable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCancellable(value: Boolean) {
        val sel = ObjCRuntime.sel("setCancellable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pausable
    open fun isPausable(): Boolean {
        val sel = ObjCRuntime.sel("isPausable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPausable(value: Boolean) {
        val sel = ObjCRuntime.sel("setPausable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property cancelled
    open fun isCancelled(): Boolean {
        val sel = ObjCRuntime.sel("isCancelled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property paused
    open fun isPaused(): Boolean {
        val sel = ObjCRuntime.sel("isPaused")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property cancellationHandler
    open fun cancellationHandler(): MemorySegment {
        val sel = ObjCRuntime.sel("cancellationHandler")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCancellationHandler(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCancellationHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pausingHandler
    open fun pausingHandler(): MemorySegment {
        val sel = ObjCRuntime.sel("pausingHandler")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPausingHandler(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPausingHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property resumingHandler
    open fun resumingHandler(): MemorySegment {
        val sel = ObjCRuntime.sel("resumingHandler")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setResumingHandler(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setResumingHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property indeterminate
    open fun isIndeterminate(): Boolean {
        val sel = ObjCRuntime.sel("isIndeterminate")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property fractionCompleted
    open fun fractionCompleted(): Double {
        val sel = ObjCRuntime.sel("fractionCompleted")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property finished
    open fun isFinished(): Boolean {
        val sel = ObjCRuntime.sel("isFinished")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property userInfo
    /** @return NSDictionary<NSProgressUserInfoKey,id> * */
    open fun userInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("userInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property kind
    open fun kind(): MemorySegment {
        val sel = ObjCRuntime.sel("kind")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setKind(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setKind:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property estimatedTimeRemaining
    open fun estimatedTimeRemaining(): MemorySegment {
        val sel = ObjCRuntime.sel("estimatedTimeRemaining")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setEstimatedTimeRemaining(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEstimatedTimeRemaining:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property throughput
    open fun throughput(): MemorySegment {
        val sel = ObjCRuntime.sel("throughput")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setThroughput(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setThroughput:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property fileOperationKind
    open fun fileOperationKind(): MemorySegment {
        val sel = ObjCRuntime.sel("fileOperationKind")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFileOperationKind(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFileOperationKind:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property fileURL
    open fun fileURL(): MemorySegment {
        val sel = ObjCRuntime.sel("fileURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFileURL(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFileURL:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property fileTotalCount
    open fun fileTotalCount(): MemorySegment {
        val sel = ObjCRuntime.sel("fileTotalCount")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFileTotalCount(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFileTotalCount:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property fileCompletedCount
    open fun fileCompletedCount(): MemorySegment {
        val sel = ObjCRuntime.sel("fileCompletedCount")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFileCompletedCount(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFileCompletedCount:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property old
    open fun isOld(): Boolean {
        val sel = ObjCRuntime.sel("isOld")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

