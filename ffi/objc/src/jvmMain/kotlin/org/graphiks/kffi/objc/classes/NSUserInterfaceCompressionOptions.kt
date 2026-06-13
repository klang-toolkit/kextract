package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUserInterfaceCompressionOptions
 * Superclass: NSObject
 * Protocols: NSCopying, NSCoding
 */
open class NSUserInterfaceCompressionOptions(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUserInterfaceCompressionOptions") }
        
        fun hideImagesOption(): MemorySegment {
            val sel = ObjCRuntime.sel("hideImagesOption")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun hideTextOption(): MemorySegment {
            val sel = ObjCRuntime.sel("hideTextOption")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun reduceMetricsOption(): MemorySegment {
            val sel = ObjCRuntime.sel("reduceMetricsOption")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun breakEqualWidthsOption(): MemorySegment {
            val sel = ObjCRuntime.sel("breakEqualWidthsOption")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun standardOptions(): MemorySegment {
            val sel = ObjCRuntime.sel("standardOptions")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
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
    
    open fun initWithIdentifier(identifier: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIdentifier:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, identifier) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithIdentifier(identifier: String): MemorySegment = initWithIdentifier(ObjCRuntime.newNSString(Arena.global(), identifier))
    
    open fun initWithCompressionOptions(options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCompressionOptions:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, options) as MemorySegment
    }
    
    open fun containsOptions(options: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("containsOptions:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, options) as Boolean
    }
    
    open fun intersectsOptions(options: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("intersectsOptions:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, options) as Boolean
    }
    
    open fun optionsByAddingOptions(options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("optionsByAddingOptions:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, options) as MemorySegment
    }
    
    open fun optionsByRemovingOptions(options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("optionsByRemovingOptions:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, options) as MemorySegment
    }
    
    // @property empty
    open fun isEmpty(): Boolean {
        val sel = ObjCRuntime.sel("isEmpty")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property hideImagesOption
    open fun hideImagesOption(): MemorySegment {
        val sel = ObjCRuntime.sel("hideImagesOption")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property hideTextOption
    open fun hideTextOption(): MemorySegment {
        val sel = ObjCRuntime.sel("hideTextOption")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property reduceMetricsOption
    open fun reduceMetricsOption(): MemorySegment {
        val sel = ObjCRuntime.sel("reduceMetricsOption")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property breakEqualWidthsOption
    open fun breakEqualWidthsOption(): MemorySegment {
        val sel = ObjCRuntime.sel("breakEqualWidthsOption")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property standardOptions
    open fun standardOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("standardOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

