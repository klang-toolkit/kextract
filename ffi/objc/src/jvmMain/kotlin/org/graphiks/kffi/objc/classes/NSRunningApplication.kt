package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSRunningApplication
 * Superclass: NSObject
 */
open class NSRunningApplication(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSRunningApplication") }
        
        /** @return NSArray<NSRunningApplication *> * */
        fun runningApplicationsWithBundleIdentifier(bundleIdentifier: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("runningApplicationsWithBundleIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, bundleIdentifier) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun runningApplicationsWithBundleIdentifier(bundleIdentifier: String): MemorySegment = runningApplicationsWithBundleIdentifier(ObjCRuntime.newNSString(Arena.global(), bundleIdentifier))
        
        fun runningApplicationWithProcessIdentifier(pid: Int): MemorySegment {
            val sel = ObjCRuntime.sel("runningApplicationWithProcessIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, pid) as MemorySegment
        }
        
        fun terminateAutomaticallyTerminableApplications(): Unit {
            val sel = ObjCRuntime.sel("terminateAutomaticallyTerminableApplications")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun currentApplication(): MemorySegment {
            val sel = ObjCRuntime.sel("currentApplication")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun hide(): Boolean {
        val sel = ObjCRuntime.sel("hide")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun unhide(): Boolean {
        val sel = ObjCRuntime.sel("unhide")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun activateFromApplication_options(application: MemorySegment, options: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("activateFromApplication:options:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, application, options) as Boolean
    }
    
    open fun activateWithOptions(options: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("activateWithOptions:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, options) as Boolean
    }
    
    open fun terminate(): Boolean {
        val sel = ObjCRuntime.sel("terminate")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun forceTerminate(): Boolean {
        val sel = ObjCRuntime.sel("forceTerminate")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property terminated
    open fun isTerminated(): Boolean {
        val sel = ObjCRuntime.sel("isTerminated")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property finishedLaunching
    open fun isFinishedLaunching(): Boolean {
        val sel = ObjCRuntime.sel("isFinishedLaunching")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property hidden
    open fun isHidden(): Boolean {
        val sel = ObjCRuntime.sel("isHidden")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property active
    open fun isActive(): Boolean {
        val sel = ObjCRuntime.sel("isActive")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property ownsMenuBar
    open fun ownsMenuBar(): Boolean {
        val sel = ObjCRuntime.sel("ownsMenuBar")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property activationPolicy
    open fun activationPolicy(): MemorySegment {
        val sel = ObjCRuntime.sel("activationPolicy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property localizedName
    open fun localizedName(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun localizedNameAsString(): String = ObjCRuntime.toJavaString(localizedName())
    
    // @property bundleIdentifier
    open fun bundleIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("bundleIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun bundleIdentifierAsString(): String = ObjCRuntime.toJavaString(bundleIdentifier())
    
    // @property bundleURL
    open fun bundleURL(): MemorySegment {
        val sel = ObjCRuntime.sel("bundleURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property executableURL
    open fun executableURL(): MemorySegment {
        val sel = ObjCRuntime.sel("executableURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property processIdentifier
    open fun processIdentifier(): Int {
        val sel = ObjCRuntime.sel("processIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property launchDate
    open fun launchDate(): MemorySegment {
        val sel = ObjCRuntime.sel("launchDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property icon
    open fun icon(): MemorySegment {
        val sel = ObjCRuntime.sel("icon")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property executableArchitecture
    open fun executableArchitecture(): Long {
        val sel = ObjCRuntime.sel("executableArchitecture")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property currentApplication
    open fun currentApplication(): MemorySegment {
        val sel = ObjCRuntime.sel("currentApplication")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

