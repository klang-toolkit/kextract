package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMetadataQuery
 * Superclass: NSObject
 */
open class NSMetadataQuery(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMetadataQuery") }
        
    }
    
    open fun startQuery(): Boolean {
        val sel = ObjCRuntime.sel("startQuery")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun stopQuery(): Unit {
        val sel = ObjCRuntime.sel("stopQuery")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun disableUpdates(): Unit {
        val sel = ObjCRuntime.sel("disableUpdates")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun enableUpdates(): Unit {
        val sel = ObjCRuntime.sel("enableUpdates")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resultAtIndex(idx: Long): MemorySegment {
        val sel = ObjCRuntime.sel("resultAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, idx) as MemorySegment
    }
    
    open fun enumerateResultsUsingBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateResultsUsingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun enumerateResultsWithOptions_usingBlock(opts: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateResultsWithOptions:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, opts, block)
    }
    
    open fun indexOfResult(result: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfResult:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, result) as Long
    }
    
    open fun valueOfAttribute_forResultAtIndex(attrName: MemorySegment, idx: Long): MemorySegment {
        val sel = ObjCRuntime.sel("valueOfAttribute:forResultAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, attrName, idx) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun valueOfAttribute_forResultAtIndex(attrName: String, idx: Long): MemorySegment = valueOfAttribute_forResultAtIndex(ObjCRuntime.newNSString(Arena.global(), attrName), idx)
    
    // @property delegate
    /** @return id<NSMetadataQueryDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property predicate
    open fun predicate(): MemorySegment {
        val sel = ObjCRuntime.sel("predicate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPredicate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPredicate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sortDescriptors
    /** @return NSArray<NSSortDescriptor *> * */
    open fun sortDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("sortDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSortDescriptors(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSortDescriptors:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property valueListAttributes
    /** @return NSArray<NSString *> * */
    open fun valueListAttributes(): MemorySegment {
        val sel = ObjCRuntime.sel("valueListAttributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setValueListAttributes(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setValueListAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property groupingAttributes
    /** @return NSArray<NSString *> * */
    open fun groupingAttributes(): MemorySegment {
        val sel = ObjCRuntime.sel("groupingAttributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setGroupingAttributes(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setGroupingAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property notificationBatchingInterval
    open fun notificationBatchingInterval(): Double {
        val sel = ObjCRuntime.sel("notificationBatchingInterval")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setNotificationBatchingInterval(value: Double) {
        val sel = ObjCRuntime.sel("setNotificationBatchingInterval:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property searchScopes
    open fun searchScopes(): MemorySegment {
        val sel = ObjCRuntime.sel("searchScopes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSearchScopes(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSearchScopes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property searchItems
    open fun searchItems(): MemorySegment {
        val sel = ObjCRuntime.sel("searchItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSearchItems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSearchItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property operationQueue
    open fun operationQueue(): MemorySegment {
        val sel = ObjCRuntime.sel("operationQueue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setOperationQueue(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setOperationQueue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property started
    open fun isStarted(): Boolean {
        val sel = ObjCRuntime.sel("isStarted")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property gathering
    open fun isGathering(): Boolean {
        val sel = ObjCRuntime.sel("isGathering")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property stopped
    open fun isStopped(): Boolean {
        val sel = ObjCRuntime.sel("isStopped")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property resultCount
    open fun resultCount(): Long {
        val sel = ObjCRuntime.sel("resultCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property results
    open fun results(): MemorySegment {
        val sel = ObjCRuntime.sel("results")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property valueLists
    /** @return NSDictionary<NSString *,NSArray<NSMetadataQueryAttributeValueTuple *> *> * */
    open fun valueLists(): MemorySegment {
        val sel = ObjCRuntime.sel("valueLists")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property groupedResults
    /** @return NSArray<NSMetadataQueryResultGroup *> * */
    open fun groupedResults(): MemorySegment {
        val sel = ObjCRuntime.sel("groupedResults")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _flags: Long
    // ivar: _interval: Double
    // ivar: _private: MemorySegment
    // ivar: _reserved: MemorySegment
}

