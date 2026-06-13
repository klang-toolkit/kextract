package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPopUpButton
 * Superclass: NSButton
 */
open class NSPopUpButton(override val ptr: MemorySegment) : NSButton(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPopUpButton") }
        
        fun popUpButtonWithMenu_target_action(menu: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("popUpButtonWithMenu:target:action:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, menu, target, action) as MemorySegment
        }
        
        fun pullDownButtonWithTitle_menu(title: MemorySegment, menu: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("pullDownButtonWithTitle:menu:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, title, menu) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun pullDownButtonWithTitle_menu(title: String, menu: MemorySegment): MemorySegment = pullDownButtonWithTitle_menu(ObjCRuntime.newNSString(Arena.global(), title), menu)
        
        fun pullDownButtonWithImage_menu(image: MemorySegment, menu: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("pullDownButtonWithImage:menu:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, image, menu) as MemorySegment
        }
        
        fun pullDownButtonWithTitle_image_menu(title: MemorySegment, image: MemorySegment, menu: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("pullDownButtonWithTitle:image:menu:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, title, image, menu) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun pullDownButtonWithTitle_image_menu(title: String, image: MemorySegment, menu: MemorySegment): MemorySegment = pullDownButtonWithTitle_image_menu(ObjCRuntime.newNSString(Arena.global(), title), image, menu)
        
    }
    
    open fun initWithFrame_pullsDown(buttonFrame: MemorySegment, flag: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:pullsDown:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(buttonFrame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), flag) as MemorySegment
    }
    
    open fun addItemWithTitle(title: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addItemWithTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, title)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun addItemWithTitle(title: String): Unit = addItemWithTitle(ObjCRuntime.newNSString(Arena.global(), title))
    
    open fun addItemsWithTitles(itemTitles: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addItemsWithTitles:")
        ObjCRuntime.msgSend(null, ptr, sel, itemTitles)
    }
    
    open fun insertItemWithTitle_atIndex(title: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertItemWithTitle:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, title, index)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun insertItemWithTitle_atIndex(title: String, index: Long): Unit = insertItemWithTitle_atIndex(ObjCRuntime.newNSString(Arena.global(), title), index)
    
    open fun removeItemWithTitle(title: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeItemWithTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, title)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun removeItemWithTitle(title: String): Unit = removeItemWithTitle(ObjCRuntime.newNSString(Arena.global(), title))
    
    open fun removeItemAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removeItemAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun removeAllItems(): Unit {
        val sel = ObjCRuntime.sel("removeAllItems")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun indexOfItem(item: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, item) as Long
    }
    
    open fun indexOfItemWithTitle(title: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfItemWithTitle:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, title) as Long
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun indexOfItemWithTitle(title: String): Long = indexOfItemWithTitle(ObjCRuntime.newNSString(Arena.global(), title))
    
    open fun indexOfItemWithTag(tag: Long): Long {
        val sel = ObjCRuntime.sel("indexOfItemWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, tag) as Long
    }
    
    open fun indexOfItemWithRepresentedObject(obj: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfItemWithRepresentedObject:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, obj) as Long
    }
    
    open fun indexOfItemWithTarget_andAction(target: MemorySegment, actionSelector: MemorySegment): Long {
        val sel = ObjCRuntime.sel("indexOfItemWithTarget:andAction:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, target, actionSelector) as Long
    }
    
    open fun itemAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("itemAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun itemWithTitle(title: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("itemWithTitle:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, title) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun itemWithTitle(title: String): MemorySegment = itemWithTitle(ObjCRuntime.newNSString(Arena.global(), title))
    
    open fun selectItem(item: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectItem:")
        ObjCRuntime.msgSend(null, ptr, sel, item)
    }
    
    open fun selectItemAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("selectItemAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun selectItemWithTitle(title: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectItemWithTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, title)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun selectItemWithTitle(title: String): Unit = selectItemWithTitle(ObjCRuntime.newNSString(Arena.global(), title))
    
    open fun selectItemWithTag(tag: Long): Boolean {
        val sel = ObjCRuntime.sel("selectItemWithTag:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, tag) as Boolean
    }
    
    override fun setTitle(string: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, string)
    }
    
    open fun synchronizeTitleAndSelectedItem(): Unit {
        val sel = ObjCRuntime.sel("synchronizeTitleAndSelectedItem")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun itemTitleAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("itemTitleAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun itemTitleAtIndexAsString(index: Long): String = ObjCRuntime.toJavaString(itemTitleAtIndex(index))
    
    // @property menu
    override fun menu(): MemorySegment {
        val sel = ObjCRuntime.sel("menu")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setMenu(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMenu:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pullsDown
    open fun pullsDown(): Boolean {
        val sel = ObjCRuntime.sel("pullsDown")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPullsDown(value: Boolean) {
        val sel = ObjCRuntime.sel("setPullsDown:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autoenablesItems
    open fun autoenablesItems(): Boolean {
        val sel = ObjCRuntime.sel("autoenablesItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutoenablesItems(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutoenablesItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property preferredEdge
    open fun preferredEdge(): MemorySegment {
        val sel = ObjCRuntime.sel("preferredEdge")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPreferredEdge(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPreferredEdge:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesItemFromMenu
    open fun usesItemFromMenu(): Boolean {
        val sel = ObjCRuntime.sel("usesItemFromMenu")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesItemFromMenu(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesItemFromMenu:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property altersStateOfSelectedItem
    open fun altersStateOfSelectedItem(): Boolean {
        val sel = ObjCRuntime.sel("altersStateOfSelectedItem")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAltersStateOfSelectedItem(value: Boolean) {
        val sel = ObjCRuntime.sel("setAltersStateOfSelectedItem:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property itemArray
    /** @return NSArray<NSMenuItem *> * */
    open fun itemArray(): MemorySegment {
        val sel = ObjCRuntime.sel("itemArray")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property numberOfItems
    open fun numberOfItems(): Long {
        val sel = ObjCRuntime.sel("numberOfItems")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property lastItem
    open fun lastItem(): MemorySegment {
        val sel = ObjCRuntime.sel("lastItem")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectedItem
    open fun selectedItem(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedItem")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property indexOfSelectedItem
    open fun indexOfSelectedItem(): Long {
        val sel = ObjCRuntime.sel("indexOfSelectedItem")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property selectedTag
    open fun selectedTag(): Long {
        val sel = ObjCRuntime.sel("selectedTag")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property itemTitles
    /** @return NSArray<NSString *> * */
    open fun itemTitles(): MemorySegment {
        val sel = ObjCRuntime.sel("itemTitles")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property titleOfSelectedItem
    open fun titleOfSelectedItem(): MemorySegment {
        val sel = ObjCRuntime.sel("titleOfSelectedItem")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun titleOfSelectedItemAsString(): String = ObjCRuntime.toJavaString(titleOfSelectedItem())
    
}

