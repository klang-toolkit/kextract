package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSWorkspaceOpenConfiguration
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSWorkspaceOpenConfiguration(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSWorkspaceOpenConfiguration") }
        
        fun configuration(): MemorySegment {
            val sel = ObjCRuntime.sel("configuration")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property promptsUserIfNeeded
    open fun promptsUserIfNeeded(): Boolean {
        val sel = ObjCRuntime.sel("promptsUserIfNeeded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPromptsUserIfNeeded(value: Boolean) {
        val sel = ObjCRuntime.sel("setPromptsUserIfNeeded:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property addsToRecentItems
    open fun addsToRecentItems(): Boolean {
        val sel = ObjCRuntime.sel("addsToRecentItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAddsToRecentItems(value: Boolean) {
        val sel = ObjCRuntime.sel("setAddsToRecentItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property activates
    open fun activates(): Boolean {
        val sel = ObjCRuntime.sel("activates")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setActivates(value: Boolean) {
        val sel = ObjCRuntime.sel("setActivates:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hides
    open fun hides(): Boolean {
        val sel = ObjCRuntime.sel("hides")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHides(value: Boolean) {
        val sel = ObjCRuntime.sel("setHides:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hidesOthers
    open fun hidesOthers(): Boolean {
        val sel = ObjCRuntime.sel("hidesOthers")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHidesOthers(value: Boolean) {
        val sel = ObjCRuntime.sel("setHidesOthers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property forPrinting
    open fun isForPrinting(): Boolean {
        val sel = ObjCRuntime.sel("isForPrinting")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setForPrinting(value: Boolean) {
        val sel = ObjCRuntime.sel("setForPrinting:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property createsNewApplicationInstance
    open fun createsNewApplicationInstance(): Boolean {
        val sel = ObjCRuntime.sel("createsNewApplicationInstance")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCreatesNewApplicationInstance(value: Boolean) {
        val sel = ObjCRuntime.sel("setCreatesNewApplicationInstance:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsRunningApplicationSubstitution
    open fun allowsRunningApplicationSubstitution(): Boolean {
        val sel = ObjCRuntime.sel("allowsRunningApplicationSubstitution")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsRunningApplicationSubstitution(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsRunningApplicationSubstitution:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property arguments
    /** @return NSArray<NSString *> * */
    open fun arguments(): MemorySegment {
        val sel = ObjCRuntime.sel("arguments")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setArguments(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setArguments:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property environment
    /** @return NSDictionary<NSString *,NSString *> * */
    open fun environment(): MemorySegment {
        val sel = ObjCRuntime.sel("environment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setEnvironment(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEnvironment:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property appleEvent
    open fun appleEvent(): MemorySegment {
        val sel = ObjCRuntime.sel("appleEvent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAppleEvent(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAppleEvent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property architecture
    open fun architecture(): Int {
        val sel = ObjCRuntime.sel("architecture")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    open fun setArchitecture(value: Int) {
        val sel = ObjCRuntime.sel("setArchitecture:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property requiresUniversalLinks
    open fun requiresUniversalLinks(): Boolean {
        val sel = ObjCRuntime.sel("requiresUniversalLinks")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setRequiresUniversalLinks(value: Boolean) {
        val sel = ObjCRuntime.sel("setRequiresUniversalLinks:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

