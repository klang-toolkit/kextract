package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSProcessInfo
 * Superclass: NSObject
 */
open class NSProcessInfo(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSProcessInfo") }
        
        fun processInfo(): MemorySegment {
            val sel = ObjCRuntime.sel("processInfo")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun operatingSystem(): Long {
        val sel = ObjCRuntime.sel("operatingSystem")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    open fun operatingSystemName(): MemorySegment {
        val sel = ObjCRuntime.sel("operatingSystemName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun operatingSystemNameAsString(): String = ObjCRuntime.toJavaString(operatingSystemName())
    
    open fun isOperatingSystemAtLeastVersion(version: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isOperatingSystemAtLeastVersion:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(version, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("majorVersion"), ValueLayout.JAVA_LONG.withName("minorVersion"), ValueLayout.JAVA_LONG.withName("patchVersion")).withName("NSOperatingSystemVersion"))) as Boolean
    }
    
    open fun disableSuddenTermination(): Unit {
        val sel = ObjCRuntime.sel("disableSuddenTermination")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun enableSuddenTermination(): Unit {
        val sel = ObjCRuntime.sel("enableSuddenTermination")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun disableAutomaticTermination(reason: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("disableAutomaticTermination:")
        ObjCRuntime.msgSend(null, ptr, sel, reason)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun disableAutomaticTermination(reason: String): Unit = disableAutomaticTermination(ObjCRuntime.newNSString(Arena.global(), reason))
    
    open fun enableAutomaticTermination(reason: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enableAutomaticTermination:")
        ObjCRuntime.msgSend(null, ptr, sel, reason)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun enableAutomaticTermination(reason: String): Unit = enableAutomaticTermination(ObjCRuntime.newNSString(Arena.global(), reason))
    
    // @property processInfo
    open fun processInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("processInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property environment
    /** @return NSDictionary<NSString *,NSString *> * */
    open fun environment(): MemorySegment {
        val sel = ObjCRuntime.sel("environment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property arguments
    /** @return NSArray<NSString *> * */
    open fun arguments(): MemorySegment {
        val sel = ObjCRuntime.sel("arguments")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property hostName
    open fun hostName(): MemorySegment {
        val sel = ObjCRuntime.sel("hostName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun hostNameAsString(): String = ObjCRuntime.toJavaString(hostName())
    
    // @property processName
    open fun processName(): MemorySegment {
        val sel = ObjCRuntime.sel("processName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setProcessName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setProcessName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun processNameAsString(): String = ObjCRuntime.toJavaString(processName())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setProcessName(value: String) = setProcessName(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property processIdentifier
    open fun processIdentifier(): Int {
        val sel = ObjCRuntime.sel("processIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
    // @property globallyUniqueString
    open fun globallyUniqueString(): MemorySegment {
        val sel = ObjCRuntime.sel("globallyUniqueString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun globallyUniqueStringAsString(): String = ObjCRuntime.toJavaString(globallyUniqueString())
    
    // @property operatingSystemVersionString
    open fun operatingSystemVersionString(): MemorySegment {
        val sel = ObjCRuntime.sel("operatingSystemVersionString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun operatingSystemVersionStringAsString(): String = ObjCRuntime.toJavaString(operatingSystemVersionString())
    
    // @property operatingSystemVersion
    open fun operatingSystemVersion(): MemorySegment {
        val sel = ObjCRuntime.sel("operatingSystemVersion")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("majorVersion"), ValueLayout.JAVA_LONG.withName("minorVersion"), ValueLayout.JAVA_LONG.withName("patchVersion")).withName("NSOperatingSystemVersion"), ptr, sel) as MemorySegment
    }
    
    // @property processorCount
    open fun processorCount(): Long {
        val sel = ObjCRuntime.sel("processorCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property activeProcessorCount
    open fun activeProcessorCount(): Long {
        val sel = ObjCRuntime.sel("activeProcessorCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property physicalMemory
    open fun physicalMemory(): Long {
        val sel = ObjCRuntime.sel("physicalMemory")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property systemUptime
    open fun systemUptime(): Double {
        val sel = ObjCRuntime.sel("systemUptime")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property automaticTerminationSupportEnabled
    open fun automaticTerminationSupportEnabled(): Boolean {
        val sel = ObjCRuntime.sel("automaticTerminationSupportEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticTerminationSupportEnabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticTerminationSupportEnabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: environment: MemorySegment
    // ivar: arguments: MemorySegment
    // ivar: hostName: MemorySegment
    // ivar: name: MemorySegment
    // ivar: automaticTerminationOptOutCounter: Long
}

// ── Category: NSProcessInfoActivity on NSProcessInfo ─────────────────────────────────────────

/** @return id<NSObject> */
fun NSProcessInfo.beginActivityWithOptions_reason(options: MemorySegment, reason: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("beginActivityWithOptions:reason:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, options, reason) as MemorySegment
}

fun NSProcessInfo.endActivity(activity: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("endActivity:")
    ObjCRuntime.msgSend(null, this.ptr, sel, activity)
}

fun NSProcessInfo.performActivityWithOptions_reason_usingBlock(options: MemorySegment, reason: MemorySegment, block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("performActivityWithOptions:reason:usingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, options, reason, block)
}

fun NSProcessInfo.performExpiringActivityWithReason_usingBlock(reason: MemorySegment, block: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("performExpiringActivityWithReason:usingBlock:")
    ObjCRuntime.msgSend(null, this.ptr, sel, reason, block)
}

// ── Category: NSUserInformation on NSProcessInfo ─────────────────────────────────────────

fun NSProcessInfo.userName(): MemorySegment {
    val sel = ObjCRuntime.sel("userName")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSProcessInfo.fullUserName(): MemorySegment {
    val sel = ObjCRuntime.sel("fullUserName")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSProcessInfoThermalState on NSProcessInfo ─────────────────────────────────────────

fun NSProcessInfo.thermalState(): MemorySegment {
    val sel = ObjCRuntime.sel("thermalState")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSProcessInfoPowerState on NSProcessInfo ─────────────────────────────────────────

fun NSProcessInfo.isLowPowerModeEnabled(): Boolean {
    val sel = ObjCRuntime.sel("isLowPowerModeEnabled")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSProcessInfoPlatform on NSProcessInfo ─────────────────────────────────────────

fun NSProcessInfo.isMacCatalystApp(): Boolean {
    val sel = ObjCRuntime.sel("isMacCatalystApp")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSProcessInfo.isiOSAppOnMac(): Boolean {
    val sel = ObjCRuntime.sel("isiOSAppOnMac")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSProcessInfo.isiOSAppOnVision(): Boolean {
    val sel = ObjCRuntime.sel("isiOSAppOnVision")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

