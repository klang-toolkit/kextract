package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionViewCompositionalLayout
 * Superclass: NSCollectionViewLayout
 */
open class NSCollectionViewCompositionalLayout(override val ptr: MemorySegment) : NSCollectionViewLayout(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionViewCompositionalLayout") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithSection(section: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSection:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, section) as MemorySegment
    }
    
    open fun initWithSection_configuration(section: MemorySegment, configuration: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSection:configuration:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, section, configuration) as MemorySegment
    }
    
    open fun initWithSectionProvider(sectionProvider: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSectionProvider:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, sectionProvider) as MemorySegment
    }
    
    open fun initWithSectionProvider_configuration(sectionProvider: MemorySegment, configuration: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSectionProvider:configuration:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, sectionProvider, configuration) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property configuration
    open fun configuration(): MemorySegment {
        val sel = ObjCRuntime.sel("configuration")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setConfiguration(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setConfiguration:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

