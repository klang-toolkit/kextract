package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCandidateListTouchBarItem
 * Superclass: NSTouchBarItem
 */
open class NSCandidateListTouchBarItem(override val ptr: MemorySegment) : NSTouchBarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCandidateListTouchBarItem") }
        
    }
    
    open fun updateWithInsertionPointVisibility(isVisible: Boolean): Unit {
        val sel = ObjCRuntime.sel("updateWithInsertionPointVisibility:")
        ObjCRuntime.msgSend(null, ptr, sel, isVisible)
    }
    
    open fun setCandidates_forSelectedRange_inString(candidates: MemorySegment, selectedRange: MemorySegment, originalString: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setCandidates:forSelectedRange:inString:")
        ObjCRuntime.msgSend(null, ptr, sel, candidates, ObjCRuntime.ObjCStructArg(selectedRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), originalString)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setCandidates_forSelectedRange_inString(candidates: MemorySegment, selectedRange: MemorySegment, originalString: String): Unit = setCandidates_forSelectedRange_inString(candidates, selectedRange, ObjCRuntime.newNSString(Arena.global(), originalString))
    
    // @property client
    /** @return NSView<NSTextInputClient> * */
    open fun client(): MemorySegment {
        val sel = ObjCRuntime.sel("client")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setClient(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setClient:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSCandidateListTouchBarItemDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property collapsed
    open fun isCollapsed(): Boolean {
        val sel = ObjCRuntime.sel("isCollapsed")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCollapsed(value: Boolean) {
        val sel = ObjCRuntime.sel("setCollapsed:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsCollapsing
    open fun allowsCollapsing(): Boolean {
        val sel = ObjCRuntime.sel("allowsCollapsing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsCollapsing(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsCollapsing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property candidateListVisible
    open fun isCandidateListVisible(): Boolean {
        val sel = ObjCRuntime.sel("isCandidateListVisible")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property allowsTextInputContextCandidates
    open fun allowsTextInputContextCandidates(): Boolean {
        val sel = ObjCRuntime.sel("allowsTextInputContextCandidates")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsTextInputContextCandidates(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsTextInputContextCandidates:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property attributedStringForCandidate
    open fun attributedStringForCandidate(): MemorySegment {
        val sel = ObjCRuntime.sel("attributedStringForCandidate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttributedStringForCandidate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttributedStringForCandidate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property candidates
    /** @return NSArray<CandidateType> * */
    open fun candidates(): MemorySegment {
        val sel = ObjCRuntime.sel("candidates")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property customizationLabel
    override fun customizationLabel(): MemorySegment {
        val sel = ObjCRuntime.sel("customizationLabel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCustomizationLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCustomizationLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

