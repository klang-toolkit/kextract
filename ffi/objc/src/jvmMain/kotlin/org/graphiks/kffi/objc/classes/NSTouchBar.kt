package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTouchBar
 * Superclass: NSObject
 * Protocols: NSCoding
 */
open class NSTouchBar(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTouchBar") }
        
        fun isAutomaticCustomizeTouchBarMenuItemEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isAutomaticCustomizeTouchBarMenuItemEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun setAutomaticCustomizeTouchBarMenuItemEnabled(automaticCustomizeTouchBarMenuItemEnabled: Boolean): Unit {
            val sel = ObjCRuntime.sel("setAutomaticCustomizeTouchBarMenuItemEnabled:")
            ObjCRuntime.msgSend(null, _class, sel, automaticCustomizeTouchBarMenuItemEnabled)
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun itemForIdentifier(identifier: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("itemForIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, identifier) as MemorySegment
    }
    
    // @property customizationIdentifier
    open fun customizationIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("customizationIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCustomizationIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCustomizationIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property customizationAllowedItemIdentifiers
    /** @return NSArray<NSTouchBarItemIdentifier> * */
    open fun customizationAllowedItemIdentifiers(): MemorySegment {
        val sel = ObjCRuntime.sel("customizationAllowedItemIdentifiers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCustomizationAllowedItemIdentifiers(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCustomizationAllowedItemIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property customizationRequiredItemIdentifiers
    /** @return NSArray<NSTouchBarItemIdentifier> * */
    open fun customizationRequiredItemIdentifiers(): MemorySegment {
        val sel = ObjCRuntime.sel("customizationRequiredItemIdentifiers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCustomizationRequiredItemIdentifiers(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCustomizationRequiredItemIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property defaultItemIdentifiers
    /** @return NSArray<NSTouchBarItemIdentifier> * */
    open fun defaultItemIdentifiers(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultItemIdentifiers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDefaultItemIdentifiers(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDefaultItemIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property itemIdentifiers
    /** @return NSArray<NSTouchBarItemIdentifier> * */
    open fun itemIdentifiers(): MemorySegment {
        val sel = ObjCRuntime.sel("itemIdentifiers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property principalItemIdentifier
    open fun principalItemIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("principalItemIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPrincipalItemIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPrincipalItemIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property escapeKeyReplacementItemIdentifier
    open fun escapeKeyReplacementItemIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("escapeKeyReplacementItemIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setEscapeKeyReplacementItemIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEscapeKeyReplacementItemIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property templateItems
    /** @return NSSet<NSTouchBarItem *> * */
    open fun templateItems(): MemorySegment {
        val sel = ObjCRuntime.sel("templateItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTemplateItems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTemplateItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSTouchBarDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property visible
    open fun isVisible(): Boolean {
        val sel = ObjCRuntime.sel("isVisible")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property automaticCustomizeTouchBarMenuItemEnabled
    open fun isAutomaticCustomizeTouchBarMenuItemEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isAutomaticCustomizeTouchBarMenuItemEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticCustomizeTouchBarMenuItemEnabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticCustomizeTouchBarMenuItemEnabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

