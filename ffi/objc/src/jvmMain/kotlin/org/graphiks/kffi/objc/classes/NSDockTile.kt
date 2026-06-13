package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDockTile
 * Superclass: NSObject
 */
open class NSDockTile(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDockTile") }
        
    }
    
    open fun display(): Unit {
        val sel = ObjCRuntime.sel("display")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property size
    open fun size(): MemorySegment {
        val sel = ObjCRuntime.sel("size")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    
    // @property contentView
    open fun contentView(): MemorySegment {
        val sel = ObjCRuntime.sel("contentView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContentView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsApplicationBadge
    open fun showsApplicationBadge(): Boolean {
        val sel = ObjCRuntime.sel("showsApplicationBadge")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsApplicationBadge(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsApplicationBadge:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property badgeLabel
    open fun badgeLabel(): MemorySegment {
        val sel = ObjCRuntime.sel("badgeLabel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBadgeLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBadgeLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun badgeLabelAsString(): String = ObjCRuntime.toJavaString(badgeLabel())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setBadgeLabel(value: String) = setBadgeLabel(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property owner
    open fun owner(): MemorySegment {
        val sel = ObjCRuntime.sel("owner")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

