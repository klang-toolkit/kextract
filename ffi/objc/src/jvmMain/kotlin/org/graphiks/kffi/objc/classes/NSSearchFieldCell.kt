package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSearchFieldCell
 * Superclass: NSTextFieldCell
 */
open class NSSearchFieldCell(override val ptr: MemorySegment) : NSTextFieldCell(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSearchFieldCell") }
        
    }
    
    override fun initTextCell(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initTextCell:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    override fun initImageCell(image: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initImageCell:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, image) as MemorySegment
    }
    
    open fun resetSearchButtonCell(): Unit {
        val sel = ObjCRuntime.sel("resetSearchButtonCell")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resetCancelButtonCell(): Unit {
        val sel = ObjCRuntime.sel("resetCancelButtonCell")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun searchTextRectForBounds(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("searchTextRectForBounds:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun searchButtonRectForBounds(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("searchButtonRectForBounds:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun cancelButtonRectForBounds(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("cancelButtonRectForBounds:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    // @property searchButtonCell
    open fun searchButtonCell(): MemorySegment {
        val sel = ObjCRuntime.sel("searchButtonCell")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSearchButtonCell(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSearchButtonCell:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property cancelButtonCell
    open fun cancelButtonCell(): MemorySegment {
        val sel = ObjCRuntime.sel("cancelButtonCell")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCancelButtonCell(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCancelButtonCell:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property searchMenuTemplate
    open fun searchMenuTemplate(): MemorySegment {
        val sel = ObjCRuntime.sel("searchMenuTemplate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSearchMenuTemplate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSearchMenuTemplate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sendsWholeSearchString
    open fun sendsWholeSearchString(): Boolean {
        val sel = ObjCRuntime.sel("sendsWholeSearchString")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSendsWholeSearchString(value: Boolean) {
        val sel = ObjCRuntime.sel("setSendsWholeSearchString:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maximumRecents
    open fun maximumRecents(): Long {
        val sel = ObjCRuntime.sel("maximumRecents")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setMaximumRecents(value: Long) {
        val sel = ObjCRuntime.sel("setMaximumRecents:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property recentSearches
    /** @return NSArray<NSString *> * */
    open fun recentSearches(): MemorySegment {
        val sel = ObjCRuntime.sel("recentSearches")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRecentSearches(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRecentSearches:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property recentsAutosaveName
    open fun recentsAutosaveName(): MemorySegment {
        val sel = ObjCRuntime.sel("recentsAutosaveName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRecentsAutosaveName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRecentsAutosaveName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sendsSearchStringImmediately
    open fun sendsSearchStringImmediately(): Boolean {
        val sel = ObjCRuntime.sel("sendsSearchStringImmediately")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSendsSearchStringImmediately(value: Boolean) {
        val sel = ObjCRuntime.sel("setSendsSearchStringImmediately:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

