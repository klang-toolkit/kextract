package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSToolbar
 * Superclass: NSObject
 */
open class NSToolbar(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSToolbar") }
        
    }
    
    open fun initWithIdentifier(identifier: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, identifier) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun insertItemWithItemIdentifier_atIndex(itemIdentifier: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertItemWithItemIdentifier:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, itemIdentifier, index)
    }
    
    open fun removeItemAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeItemAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun removeItemWithItemIdentifier(itemIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeItemWithItemIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, itemIdentifier)
    }
    
    open fun runCustomizationPalette(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("runCustomizationPalette:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun validateVisibleItems(): Unit {
        val sel = ObjCRuntime.sel("validateVisibleItems")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property delegate
    /** @return id<NSToolbarDelegate> */
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
    open fun setVisible(value: Boolean) {
        val sel = ObjCRuntime.sel("setVisible:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property customizationPaletteIsRunning
    open fun customizationPaletteIsRunning(): Boolean {
        val sel = ObjCRuntime.sel("customizationPaletteIsRunning")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property displayMode
    open fun displayMode(): MemorySegment {
        val sel = ObjCRuntime.sel("displayMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDisplayMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDisplayMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectedItemIdentifier
    open fun selectedItemIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedItemIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectedItemIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectedItemIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsUserCustomization
    open fun allowsUserCustomization(): Boolean {
        val sel = ObjCRuntime.sel("allowsUserCustomization")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsUserCustomization(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsUserCustomization:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsDisplayModeCustomization
    open fun allowsDisplayModeCustomization(): Boolean {
        val sel = ObjCRuntime.sel("allowsDisplayModeCustomization")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsDisplayModeCustomization(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsDisplayModeCustomization:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property identifier
    open fun identifier(): MemorySegment {
        val sel = ObjCRuntime.sel("identifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property items
    /** @return NSArray<__kindof NSToolbarItem *> * */
    open fun items(): MemorySegment {
        val sel = ObjCRuntime.sel("items")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property visibleItems
    /** @return NSArray<__kindof NSToolbarItem *> * */
    open fun visibleItems(): MemorySegment {
        val sel = ObjCRuntime.sel("visibleItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property itemIdentifiers
    /** @return NSArray<NSToolbarItemIdentifier> * */
    open fun itemIdentifiers(): MemorySegment {
        val sel = ObjCRuntime.sel("itemIdentifiers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setItemIdentifiers(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setItemIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property centeredItemIdentifiers
    /** @return NSSet<NSToolbarItemIdentifier> * */
    open fun centeredItemIdentifiers(): MemorySegment {
        val sel = ObjCRuntime.sel("centeredItemIdentifiers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCenteredItemIdentifiers(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCenteredItemIdentifiers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autosavesConfiguration
    open fun autosavesConfiguration(): Boolean {
        val sel = ObjCRuntime.sel("autosavesConfiguration")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutosavesConfiguration(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutosavesConfiguration:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsExtensionItems
    open fun allowsExtensionItems(): Boolean {
        val sel = ObjCRuntime.sel("allowsExtensionItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsExtensionItems(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsExtensionItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSDeprecated on NSToolbar ─────────────────────────────────────────

fun NSToolbar.setConfigurationFromDictionary(configDict: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setConfigurationFromDictionary:")
    ObjCRuntime.msgSend(null, this.ptr, sel, configDict)
}

fun NSToolbar.sizeMode(): MemorySegment {
    val sel = ObjCRuntime.sel("sizeMode")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSToolbar.setSizeMode(sizeMode: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setSizeMode:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sizeMode)
}

fun NSToolbar.centeredItemIdentifier(): MemorySegment {
    val sel = ObjCRuntime.sel("centeredItemIdentifier")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSToolbar.setCenteredItemIdentifier(centeredItemIdentifier: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setCenteredItemIdentifier:")
    ObjCRuntime.msgSend(null, this.ptr, sel, centeredItemIdentifier)
}

fun NSToolbar.fullScreenAccessoryView(): MemorySegment {
    val sel = ObjCRuntime.sel("fullScreenAccessoryView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSToolbar.setFullScreenAccessoryView(fullScreenAccessoryView: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setFullScreenAccessoryView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fullScreenAccessoryView)
}

fun NSToolbar.fullScreenAccessoryViewMinHeight(): Double {
    val sel = ObjCRuntime.sel("fullScreenAccessoryViewMinHeight")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSToolbar.setFullScreenAccessoryViewMinHeight(fullScreenAccessoryViewMinHeight: Double): Unit {
    val sel = ObjCRuntime.sel("setFullScreenAccessoryViewMinHeight:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fullScreenAccessoryViewMinHeight)
}

fun NSToolbar.fullScreenAccessoryViewMaxHeight(): Double {
    val sel = ObjCRuntime.sel("fullScreenAccessoryViewMaxHeight")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, this.ptr, sel) as Double
}

fun NSToolbar.setFullScreenAccessoryViewMaxHeight(fullScreenAccessoryViewMaxHeight: Double): Unit {
    val sel = ObjCRuntime.sel("setFullScreenAccessoryViewMaxHeight:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fullScreenAccessoryViewMaxHeight)
}

fun NSToolbar.showsBaselineSeparator(): Boolean {
    val sel = ObjCRuntime.sel("showsBaselineSeparator")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSToolbar.setShowsBaselineSeparator(showsBaselineSeparator: Boolean): Unit {
    val sel = ObjCRuntime.sel("setShowsBaselineSeparator:")
    ObjCRuntime.msgSend(null, this.ptr, sel, showsBaselineSeparator)
}

/** @return NSDictionary<NSString *,id> * */
fun NSToolbar.configurationDictionary(): MemorySegment {
    val sel = ObjCRuntime.sel("configurationDictionary")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

