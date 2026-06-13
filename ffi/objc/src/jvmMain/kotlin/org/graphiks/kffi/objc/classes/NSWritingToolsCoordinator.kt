package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSWritingToolsCoordinator
 * Superclass: NSObject
 */
open class NSWritingToolsCoordinator(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSWritingToolsCoordinator") }
        
        fun isWritingToolsAvailable(): Boolean {
            val sel = ObjCRuntime.sel("isWritingToolsAvailable")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
    }
    
    open fun initWithDelegate(delegate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDelegate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, delegate) as MemorySegment
    }
    
    open fun stopWritingTools(): Unit {
        val sel = ObjCRuntime.sel("stopWritingTools")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun updateRange_withText_reason_forContextWithIdentifier(range: MemorySegment, replacementText: MemorySegment, reason: MemorySegment, contextID: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("updateRange:withText:reason:forContextWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(range, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), replacementText, reason, contextID)
    }
    
    open fun updateForReflowedTextInContextWithIdentifier(contextID: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("updateForReflowedTextInContextWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, contextID)
    }
    
    // @property isWritingToolsAvailable
    open fun isWritingToolsAvailable(): Boolean {
        val sel = ObjCRuntime.sel("isWritingToolsAvailable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property delegate
    /** @return id<NSWritingToolsCoordinatorDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property view
    open fun view(): MemorySegment {
        val sel = ObjCRuntime.sel("view")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property effectContainerView
    open fun effectContainerView(): MemorySegment {
        val sel = ObjCRuntime.sel("effectContainerView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setEffectContainerView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEffectContainerView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property decorationContainerView
    open fun decorationContainerView(): MemorySegment {
        val sel = ObjCRuntime.sel("decorationContainerView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDecorationContainerView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDecorationContainerView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property state
    open fun state(): MemorySegment {
        val sel = ObjCRuntime.sel("state")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property preferredBehavior
    open fun preferredBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("preferredBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPreferredBehavior(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPreferredBehavior:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property behavior
    open fun behavior(): MemorySegment {
        val sel = ObjCRuntime.sel("behavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property preferredResultOptions
    open fun preferredResultOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("preferredResultOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPreferredResultOptions(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPreferredResultOptions:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property resultOptions
    open fun resultOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("resultOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property includesTextListMarkers
    open fun includesTextListMarkers(): Boolean {
        val sel = ObjCRuntime.sel("includesTextListMarkers")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setIncludesTextListMarkers(value: Boolean) {
        val sel = ObjCRuntime.sel("setIncludesTextListMarkers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

