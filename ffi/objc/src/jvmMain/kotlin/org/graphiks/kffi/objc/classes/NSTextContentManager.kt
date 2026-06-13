package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextContentManager
 * Superclass: NSObject
 * Protocols: NSTextElementProvider, NSSecureCoding
 */
open class NSTextContentManager(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextContentManager") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun addTextLayoutManager(textLayoutManager: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addTextLayoutManager:")
        ObjCRuntime.msgSend(null, ptr, sel, textLayoutManager)
    }
    
    open fun removeTextLayoutManager(textLayoutManager: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeTextLayoutManager:")
        ObjCRuntime.msgSend(null, ptr, sel, textLayoutManager)
    }
    
    open fun synchronizeTextLayoutManagers(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("synchronizeTextLayoutManagers:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    /** @return NSArray<NSTextElement *> * */
    open fun textElementsForRange(range: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("textElementsForRange:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, range) as MemorySegment
    }
    
    open fun performEditingTransactionUsingBlock(transaction: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performEditingTransactionUsingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, transaction)
    }
    
    open fun recordEditActionInRange_newTextRange(originalTextRange: MemorySegment, newTextRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("recordEditActionInRange:newTextRange:")
        ObjCRuntime.msgSend(null, ptr, sel, originalTextRange, newTextRange)
    }
    
    // @property delegate
    /** @return id<NSTextContentManagerDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property textLayoutManagers
    /** @return NSArray<NSTextLayoutManager *> * */
    open fun textLayoutManagers(): MemorySegment {
        val sel = ObjCRuntime.sel("textLayoutManagers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property primaryTextLayoutManager
    open fun primaryTextLayoutManager(): MemorySegment {
        val sel = ObjCRuntime.sel("primaryTextLayoutManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPrimaryTextLayoutManager(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPrimaryTextLayoutManager:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasEditingTransaction
    open fun hasEditingTransaction(): Boolean {
        val sel = ObjCRuntime.sel("hasEditingTransaction")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property automaticallySynchronizesTextLayoutManagers
    open fun automaticallySynchronizesTextLayoutManagers(): Boolean {
        val sel = ObjCRuntime.sel("automaticallySynchronizesTextLayoutManagers")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticallySynchronizesTextLayoutManagers(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticallySynchronizesTextLayoutManagers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property automaticallySynchronizesToBackingStore
    open fun automaticallySynchronizesToBackingStore(): Boolean {
        val sel = ObjCRuntime.sel("automaticallySynchronizesToBackingStore")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticallySynchronizesToBackingStore(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticallySynchronizesToBackingStore:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

