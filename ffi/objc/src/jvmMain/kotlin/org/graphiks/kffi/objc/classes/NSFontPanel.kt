package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFontPanel
 * Superclass: NSPanel
 */
open class NSFontPanel(override val ptr: MemorySegment) : NSPanel(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFontPanel") }
        
        fun sharedFontPanel(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedFontPanel")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun sharedFontPanelExists(): Boolean {
            val sel = ObjCRuntime.sel("sharedFontPanelExists")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
    }
    
    open fun setPanelFont_isMultiple(fontObj: MemorySegment, flag: Boolean): Unit {
        val sel = ObjCRuntime.sel("setPanelFont:isMultiple:")
        ObjCRuntime.msgSend(null, ptr, sel, fontObj, flag)
    }
    
    open fun panelConvertFont(fontObj: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("panelConvertFont:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, fontObj) as MemorySegment
    }
    
    open fun reloadDefaultFontFamilies(): Unit {
        val sel = ObjCRuntime.sel("reloadDefaultFontFamilies")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property sharedFontPanel
    open fun sharedFontPanel(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedFontPanel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sharedFontPanelExists
    open fun sharedFontPanelExists(): Boolean {
        val sel = ObjCRuntime.sel("sharedFontPanelExists")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property accessoryView
    open fun accessoryView(): MemorySegment {
        val sel = ObjCRuntime.sel("accessoryView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAccessoryView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAccessoryView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property worksWhenModal
    override fun worksWhenModal(): Boolean {
        val sel = ObjCRuntime.sel("worksWhenModal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    override fun setWorksWhenModal(value: Boolean) {
        val sel = ObjCRuntime.sel("setWorksWhenModal:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property enabled
    open fun isEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEnabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setEnabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

