package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : kCGDisplayStreamMinimumFrameTime typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamMinimumFrameTime_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamMinimumFrameTime_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamMinimumFrameTime").orElseThrow() }
private val kCGDisplayStreamMinimumFrameTime_VH: VarHandle by lazy { kCGDisplayStreamMinimumFrameTime_LAYOUT.varHandle() }

var kCGDisplayStreamMinimumFrameTime: MemorySegment
    get() = kCGDisplayStreamMinimumFrameTime_VH.get(kCGDisplayStreamMinimumFrameTime_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamMinimumFrameTime_VH.set(kCGDisplayStreamMinimumFrameTime_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDisplayStreamShowCursor typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamShowCursor_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamShowCursor_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamShowCursor").orElseThrow() }
private val kCGDisplayStreamShowCursor_VH: VarHandle by lazy { kCGDisplayStreamShowCursor_LAYOUT.varHandle() }

var kCGDisplayStreamShowCursor: MemorySegment
    get() = kCGDisplayStreamShowCursor_VH.get(kCGDisplayStreamShowCursor_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamShowCursor_VH.set(kCGDisplayStreamShowCursor_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDisplayStreamQueueDepth typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamQueueDepth_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamQueueDepth_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamQueueDepth").orElseThrow() }
private val kCGDisplayStreamQueueDepth_VH: VarHandle by lazy { kCGDisplayStreamQueueDepth_LAYOUT.varHandle() }

var kCGDisplayStreamQueueDepth: MemorySegment
    get() = kCGDisplayStreamQueueDepth_VH.get(kCGDisplayStreamQueueDepth_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamQueueDepth_VH.set(kCGDisplayStreamQueueDepth_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDisplayStreamYCbCrMatrix typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamYCbCrMatrix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamYCbCrMatrix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamYCbCrMatrix").orElseThrow() }
private val kCGDisplayStreamYCbCrMatrix_VH: VarHandle by lazy { kCGDisplayStreamYCbCrMatrix_LAYOUT.varHandle() }

var kCGDisplayStreamYCbCrMatrix: MemorySegment
    get() = kCGDisplayStreamYCbCrMatrix_VH.get(kCGDisplayStreamYCbCrMatrix_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamYCbCrMatrix_VH.set(kCGDisplayStreamYCbCrMatrix_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDisplayStreamYCbCrMatrix_ITU_R_709_2 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamYCbCrMatrix_ITU_R_709_2_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamYCbCrMatrix_ITU_R_709_2_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamYCbCrMatrix_ITU_R_709_2").orElseThrow() }
private val kCGDisplayStreamYCbCrMatrix_ITU_R_709_2_VH: VarHandle by lazy { kCGDisplayStreamYCbCrMatrix_ITU_R_709_2_LAYOUT.varHandle() }

var kCGDisplayStreamYCbCrMatrix_ITU_R_709_2: MemorySegment
    get() = kCGDisplayStreamYCbCrMatrix_ITU_R_709_2_VH.get(kCGDisplayStreamYCbCrMatrix_ITU_R_709_2_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamYCbCrMatrix_ITU_R_709_2_VH.set(kCGDisplayStreamYCbCrMatrix_ITU_R_709_2_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDisplayStreamYCbCrMatrix_ITU_R_601_4 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamYCbCrMatrix_ITU_R_601_4_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamYCbCrMatrix_ITU_R_601_4_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamYCbCrMatrix_ITU_R_601_4").orElseThrow() }
private val kCGDisplayStreamYCbCrMatrix_ITU_R_601_4_VH: VarHandle by lazy { kCGDisplayStreamYCbCrMatrix_ITU_R_601_4_LAYOUT.varHandle() }

var kCGDisplayStreamYCbCrMatrix_ITU_R_601_4: MemorySegment
    get() = kCGDisplayStreamYCbCrMatrix_ITU_R_601_4_VH.get(kCGDisplayStreamYCbCrMatrix_ITU_R_601_4_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamYCbCrMatrix_ITU_R_601_4_VH.set(kCGDisplayStreamYCbCrMatrix_ITU_R_601_4_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995").orElseThrow() }
private val kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995_VH: VarHandle by lazy { kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995_LAYOUT.varHandle() }

var kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995: MemorySegment
    get() = kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995_VH.get(kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995_VH.set(kCGDisplayStreamYCbCrMatrix_SMPTE_240M_1995_SEGMENT, value)

/**
 * {@snippet lang=c : CGDisplayStreamGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGDisplayStreamGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGDisplayStreamGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayStreamGetTypeID").orElseThrow()
private val CGDisplayStreamGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayStreamGetTypeID_ADDR, CGDisplayStreamGetTypeID_DESC)

fun CGDisplayStreamGetTypeID(): Long {
    try {
        return CGDisplayStreamGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayStreamCreate typedef CGDisplayStreamRef = (Declared(CGDisplayStream))*(typedef CGDirectDisplayID = UNSIGNED = Int,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef int32_t = Int,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CGDisplayStreamFrameAvailableHandler = (Void)*)
 */
private val CGDisplayStreamCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDisplayStreamCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayStreamCreate").orElseThrow()
private val CGDisplayStreamCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayStreamCreate_ADDR, CGDisplayStreamCreate_DESC)

fun CGDisplayStreamCreate(arg0: Int, arg1: Long, arg2: Long, arg3: Int, arg4: MemorySegment, arg5: MemorySegment): MemorySegment {
    try {
        return CGDisplayStreamCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayStreamCreateWithDispatchQueue typedef CGDisplayStreamRef = (Declared(CGDisplayStream))*(typedef CGDirectDisplayID = UNSIGNED = Int,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef int32_t = Int,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef dispatch_queue_t = (Void)*,typedef CGDisplayStreamFrameAvailableHandler = (Void)*)
 */
private val CGDisplayStreamCreateWithDispatchQueue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDisplayStreamCreateWithDispatchQueue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayStreamCreateWithDispatchQueue").orElseThrow()
private val CGDisplayStreamCreateWithDispatchQueue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayStreamCreateWithDispatchQueue_ADDR, CGDisplayStreamCreateWithDispatchQueue_DESC)

fun CGDisplayStreamCreateWithDispatchQueue(arg0: Int, arg1: Long, arg2: Long, arg3: Int, arg4: MemorySegment, arg5: MemorySegment, arg6: MemorySegment): MemorySegment {
    try {
        return CGDisplayStreamCreateWithDispatchQueue_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayStreamGetRunLoopSource typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*(typedef CGDisplayStreamRef = (Declared(CGDisplayStream))*)
 */
private val CGDisplayStreamGetRunLoopSource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDisplayStreamGetRunLoopSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayStreamGetRunLoopSource").orElseThrow()
private val CGDisplayStreamGetRunLoopSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayStreamGetRunLoopSource_ADDR, CGDisplayStreamGetRunLoopSource_DESC)

fun CGDisplayStreamGetRunLoopSource(arg0: MemorySegment): MemorySegment {
    try {
        return CGDisplayStreamGetRunLoopSource_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGUnregisterScreenRefreshCallback Void(typedef CGScreenRefreshCallback = (Void(UNSIGNED = Int,(Declared(CGRect))*,(Void)*))*,(Void)*)
 */
private val CGUnregisterScreenRefreshCallback_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGUnregisterScreenRefreshCallback_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGUnregisterScreenRefreshCallback").orElseThrow()
private val CGUnregisterScreenRefreshCallback_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGUnregisterScreenRefreshCallback_ADDR, CGUnregisterScreenRefreshCallback_DESC)

fun CGUnregisterScreenRefreshCallback(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGUnregisterScreenRefreshCallback_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGScreenUnregisterMoveCallback Void(typedef CGScreenUpdateMoveCallback = (Void(Declared(CGScreenUpdateMoveDelta),UNSIGNED = Long,(Declared(CGRect))*,(Void)*))*,(Void)*)
 */
private val CGScreenUnregisterMoveCallback_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGScreenUnregisterMoveCallback_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGScreenUnregisterMoveCallback").orElseThrow()
private val CGScreenUnregisterMoveCallback_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGScreenUnregisterMoveCallback_ADDR, CGScreenUnregisterMoveCallback_DESC)

fun CGScreenUnregisterMoveCallback(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGScreenUnregisterMoveCallback_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGReleaseScreenRefreshRects Void((typedef CGRect = Declared(CGRect))*)
 */
private val CGReleaseScreenRefreshRects_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGReleaseScreenRefreshRects_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGReleaseScreenRefreshRects").orElseThrow()
private val CGReleaseScreenRefreshRects_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGReleaseScreenRefreshRects_ADDR, CGReleaseScreenRefreshRects_DESC)

fun CGReleaseScreenRefreshRects(arg0: MemorySegment): Unit {
    try {
        CGReleaseScreenRefreshRects_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGCursorIsVisible typedef boolean_t = Int()
 */
private val CGCursorIsVisible_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT)
private val CGCursorIsVisible_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGCursorIsVisible").orElseThrow()
private val CGCursorIsVisible_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGCursorIsVisible_ADDR, CGCursorIsVisible_DESC)

fun CGCursorIsVisible(): Int {
    try {
        return CGCursorIsVisible_HANDLE.invokeExact() as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGCursorIsDrawnInFramebuffer typedef boolean_t = Int()
 */
private val CGCursorIsDrawnInFramebuffer_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT)
private val CGCursorIsDrawnInFramebuffer_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGCursorIsDrawnInFramebuffer").orElseThrow()
private val CGCursorIsDrawnInFramebuffer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGCursorIsDrawnInFramebuffer_ADDR, CGCursorIsDrawnInFramebuffer_DESC)

fun CGCursorIsDrawnInFramebuffer(): Int {
    try {
        return CGCursorIsDrawnInFramebuffer_HANDLE.invokeExact() as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGWindowServerCreateServerPort typedef CFMachPortRef = (Declared(__CFMachPort))*()
 */
private val CGWindowServerCreateServerPort_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CGWindowServerCreateServerPort_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGWindowServerCreateServerPort").orElseThrow()
private val CGWindowServerCreateServerPort_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGWindowServerCreateServerPort_ADDR, CGWindowServerCreateServerPort_DESC)

fun CGWindowServerCreateServerPort(): MemorySegment {
    try {
        return CGWindowServerCreateServerPort_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGWindowServerCFMachPort typedef CFMachPortRef = (Declared(__CFMachPort))*()
 */
private val CGWindowServerCFMachPort_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CGWindowServerCFMachPort_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGWindowServerCFMachPort").orElseThrow()
private val CGWindowServerCFMachPort_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGWindowServerCFMachPort_ADDR, CGWindowServerCFMachPort_DESC)

fun CGWindowServerCFMachPort(): MemorySegment {
    try {
        return CGWindowServerCFMachPort_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGEventGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGEventGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventGetTypeID").orElseThrow()
private val CGEventGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventGetTypeID_ADDR, CGEventGetTypeID_DESC)

fun CGEventGetTypeID(): Long {
    try {
        return CGEventGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventCreate typedef CGEventRef = (Declared(__CGEvent))*(typedef CGEventSourceRef = (Declared(__CGEventSource))*)
 */
private val CGEventCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGEventCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventCreate").orElseThrow()
private val CGEventCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventCreate_ADDR, CGEventCreate_DESC)

fun CGEventCreate(arg0: MemorySegment): MemorySegment {
    try {
        return CGEventCreate_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventCreateData typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CGEventRef = (Declared(__CGEvent))*)
 */
private val CGEventCreateData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGEventCreateData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventCreateData").orElseThrow()
private val CGEventCreateData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventCreateData_ADDR, CGEventCreateData_DESC)

fun CGEventCreateData(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGEventCreateData_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventCreateFromData typedef CGEventRef = (Declared(__CGEvent))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*)
 */
private val CGEventCreateFromData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGEventCreateFromData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventCreateFromData").orElseThrow()
private val CGEventCreateFromData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventCreateFromData_ADDR, CGEventCreateFromData_DESC)

fun CGEventCreateFromData(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGEventCreateFromData_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventCreateKeyboardEvent typedef CGEventRef = (Declared(__CGEvent))*(typedef CGEventSourceRef = (Declared(__CGEventSource))*,typedef CGKeyCode = UNSIGNED = Short,Bool)
 */
private val CGEventCreateKeyboardEvent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_SHORT, ValueLayout.JAVA_BOOLEAN)
private val CGEventCreateKeyboardEvent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventCreateKeyboardEvent").orElseThrow()
private val CGEventCreateKeyboardEvent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventCreateKeyboardEvent_ADDR, CGEventCreateKeyboardEvent_DESC)

fun CGEventCreateKeyboardEvent(arg0: MemorySegment, arg1: Short, arg2: Boolean): MemorySegment {
    try {
        return CGEventCreateKeyboardEvent_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventCreateCopy typedef CGEventRef = (Declared(__CGEvent))*(typedef CGEventRef = (Declared(__CGEvent))*)
 */
private val CGEventCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGEventCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventCreateCopy").orElseThrow()
private val CGEventCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventCreateCopy_ADDR, CGEventCreateCopy_DESC)

fun CGEventCreateCopy(arg0: MemorySegment): MemorySegment {
    try {
        return CGEventCreateCopy_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventCreateSourceFromEvent typedef CGEventSourceRef = (Declared(__CGEventSource))*(typedef CGEventRef = (Declared(__CGEvent))*)
 */
private val CGEventCreateSourceFromEvent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGEventCreateSourceFromEvent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventCreateSourceFromEvent").orElseThrow()
private val CGEventCreateSourceFromEvent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventCreateSourceFromEvent_ADDR, CGEventCreateSourceFromEvent_DESC)

fun CGEventCreateSourceFromEvent(arg0: MemorySegment): MemorySegment {
    try {
        return CGEventCreateSourceFromEvent_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSetSource Void(typedef CGEventRef = (Declared(__CGEvent))*,typedef CGEventSourceRef = (Declared(__CGEventSource))*)
 */
private val CGEventSetSource_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGEventSetSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSetSource").orElseThrow()
private val CGEventSetSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSetSource_ADDR, CGEventSetSource_DESC)

fun CGEventSetSource(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGEventSetSource_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventGetTimestamp typedef CGEventTimestamp = UNSIGNED = LongLong(typedef CGEventRef = (Declared(__CGEvent))*)
 */
private val CGEventGetTimestamp_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGEventGetTimestamp_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventGetTimestamp").orElseThrow()
private val CGEventGetTimestamp_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventGetTimestamp_ADDR, CGEventGetTimestamp_DESC)

fun CGEventGetTimestamp(arg0: MemorySegment): Long {
    try {
        return CGEventGetTimestamp_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSetTimestamp Void(typedef CGEventRef = (Declared(__CGEvent))*,typedef CGEventTimestamp = UNSIGNED = LongLong)
 */
private val CGEventSetTimestamp_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGEventSetTimestamp_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSetTimestamp").orElseThrow()
private val CGEventSetTimestamp_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSetTimestamp_ADDR, CGEventSetTimestamp_DESC)

fun CGEventSetTimestamp(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CGEventSetTimestamp_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventGetLocation typedef CGPoint = Declared(CGPoint)(typedef CGEventRef = (Declared(__CGEvent))*)
 */
private val CGEventGetLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(CGPoint.layout, ValueLayout.ADDRESS)
private val CGEventGetLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventGetLocation").orElseThrow()
private val CGEventGetLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventGetLocation_ADDR, CGEventGetLocation_DESC)

fun CGEventGetLocation(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGEventGetLocation_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventGetUnflippedLocation typedef CGPoint = Declared(CGPoint)(typedef CGEventRef = (Declared(__CGEvent))*)
 */
private val CGEventGetUnflippedLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(CGPoint.layout, ValueLayout.ADDRESS)
private val CGEventGetUnflippedLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventGetUnflippedLocation").orElseThrow()
private val CGEventGetUnflippedLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventGetUnflippedLocation_ADDR, CGEventGetUnflippedLocation_DESC)

fun CGEventGetUnflippedLocation(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGEventGetUnflippedLocation_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSetLocation Void(typedef CGEventRef = (Declared(__CGEvent))*,typedef CGPoint = Declared(CGPoint))
 */
private val CGEventSetLocation_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGPoint.layout)
private val CGEventSetLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSetLocation").orElseThrow()
private val CGEventSetLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSetLocation_ADDR, CGEventSetLocation_DESC)

fun CGEventSetLocation(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGEventSetLocation_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventKeyboardGetUnicodeString Void(typedef CGEventRef = (Declared(__CGEvent))*,typedef UniCharCount = UNSIGNED = Long,(typedef UniCharCount = UNSIGNED = Long)*,(typedef UniChar = UNSIGNED = Short)*)
 */
private val CGEventKeyboardGetUnicodeString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGEventKeyboardGetUnicodeString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventKeyboardGetUnicodeString").orElseThrow()
private val CGEventKeyboardGetUnicodeString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventKeyboardGetUnicodeString_ADDR, CGEventKeyboardGetUnicodeString_DESC)

fun CGEventKeyboardGetUnicodeString(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        CGEventKeyboardGetUnicodeString_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventKeyboardSetUnicodeString Void(typedef CGEventRef = (Declared(__CGEvent))*,typedef UniCharCount = UNSIGNED = Long,(typedef UniChar = UNSIGNED = Short)*)
 */
private val CGEventKeyboardSetUnicodeString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGEventKeyboardSetUnicodeString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventKeyboardSetUnicodeString").orElseThrow()
private val CGEventKeyboardSetUnicodeString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventKeyboardSetUnicodeString_ADDR, CGEventKeyboardSetUnicodeString_DESC)

fun CGEventKeyboardSetUnicodeString(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Unit {
    try {
        CGEventKeyboardSetUnicodeString_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventTapEnable Void(typedef CFMachPortRef = (Declared(__CFMachPort))*,Bool)
 */
private val CGEventTapEnable_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGEventTapEnable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventTapEnable").orElseThrow()
private val CGEventTapEnable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventTapEnable_ADDR, CGEventTapEnable_DESC)

fun CGEventTapEnable(arg0: MemorySegment, arg1: Boolean): Unit {
    try {
        CGEventTapEnable_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventTapIsEnabled Bool(typedef CFMachPortRef = (Declared(__CFMachPort))*)
 */
private val CGEventTapIsEnabled_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGEventTapIsEnabled_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventTapIsEnabled").orElseThrow()
private val CGEventTapIsEnabled_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventTapIsEnabled_ADDR, CGEventTapIsEnabled_DESC)

fun CGEventTapIsEnabled(arg0: MemorySegment): Boolean {
    try {
        return CGEventTapIsEnabled_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventTapPostEvent Void(typedef CGEventTapProxy = (Declared(__CGEventTapProxy))*,typedef CGEventRef = (Declared(__CGEvent))*)
 */
private val CGEventTapPostEvent_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGEventTapPostEvent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventTapPostEvent").orElseThrow()
private val CGEventTapPostEvent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventTapPostEvent_ADDR, CGEventTapPostEvent_DESC)

fun CGEventTapPostEvent(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGEventTapPostEvent_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventPostToPSN Void((Void)*,typedef CGEventRef = (Declared(__CGEvent))*)
 */
private val CGEventPostToPSN_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGEventPostToPSN_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventPostToPSN").orElseThrow()
private val CGEventPostToPSN_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventPostToPSN_ADDR, CGEventPostToPSN_DESC)

fun CGEventPostToPSN(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGEventPostToPSN_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventPostToPid Void(typedef pid_t = Int,typedef CGEventRef = (Declared(__CGEvent))*)
 */
private val CGEventPostToPid_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGEventPostToPid_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventPostToPid").orElseThrow()
private val CGEventPostToPid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventPostToPid_ADDR, CGEventPostToPid_DESC)

fun CGEventPostToPid(arg0: Int, arg1: MemorySegment): Unit {
    try {
        CGEventPostToPid_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPreflightListenEventAccess Bool()
 */
private val CGPreflightListenEventAccess_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
private val CGPreflightListenEventAccess_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPreflightListenEventAccess").orElseThrow()
private val CGPreflightListenEventAccess_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPreflightListenEventAccess_ADDR, CGPreflightListenEventAccess_DESC)

fun CGPreflightListenEventAccess(): Boolean {
    try {
        return CGPreflightListenEventAccess_HANDLE.invokeExact() as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRequestListenEventAccess Bool()
 */
private val CGRequestListenEventAccess_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
private val CGRequestListenEventAccess_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRequestListenEventAccess").orElseThrow()
private val CGRequestListenEventAccess_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRequestListenEventAccess_ADDR, CGRequestListenEventAccess_DESC)

fun CGRequestListenEventAccess(): Boolean {
    try {
        return CGRequestListenEventAccess_HANDLE.invokeExact() as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPreflightPostEventAccess Bool()
 */
private val CGPreflightPostEventAccess_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
private val CGPreflightPostEventAccess_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPreflightPostEventAccess").orElseThrow()
private val CGPreflightPostEventAccess_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPreflightPostEventAccess_ADDR, CGPreflightPostEventAccess_DESC)

fun CGPreflightPostEventAccess(): Boolean {
    try {
        return CGPreflightPostEventAccess_HANDLE.invokeExact() as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRequestPostEventAccess Bool()
 */
private val CGRequestPostEventAccess_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
private val CGRequestPostEventAccess_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRequestPostEventAccess").orElseThrow()
private val CGRequestPostEventAccess_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRequestPostEventAccess_ADDR, CGRequestPostEventAccess_DESC)

fun CGRequestPostEventAccess(): Boolean {
    try {
        return CGRequestPostEventAccess_HANDLE.invokeExact() as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSourceGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGEventSourceGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGEventSourceGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSourceGetTypeID").orElseThrow()
private val CGEventSourceGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSourceGetTypeID_ADDR, CGEventSourceGetTypeID_DESC)

fun CGEventSourceGetTypeID(): Long {
    try {
        return CGEventSourceGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSourceGetKeyboardType typedef CGEventSourceKeyboardType = UNSIGNED = Int(typedef CGEventSourceRef = (Declared(__CGEventSource))*)
 */
private val CGEventSourceGetKeyboardType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGEventSourceGetKeyboardType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSourceGetKeyboardType").orElseThrow()
private val CGEventSourceGetKeyboardType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSourceGetKeyboardType_ADDR, CGEventSourceGetKeyboardType_DESC)

fun CGEventSourceGetKeyboardType(arg0: MemorySegment): Int {
    try {
        return CGEventSourceGetKeyboardType_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSourceSetKeyboardType Void(typedef CGEventSourceRef = (Declared(__CGEventSource))*,typedef CGEventSourceKeyboardType = UNSIGNED = Int)
 */
private val CGEventSourceSetKeyboardType_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGEventSourceSetKeyboardType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSourceSetKeyboardType").orElseThrow()
private val CGEventSourceSetKeyboardType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSourceSetKeyboardType_ADDR, CGEventSourceSetKeyboardType_DESC)

fun CGEventSourceSetKeyboardType(arg0: MemorySegment, arg1: Int): Unit {
    try {
        CGEventSourceSetKeyboardType_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSourceGetPixelsPerLine Double(typedef CGEventSourceRef = (Declared(__CGEventSource))*)
 */
private val CGEventSourceGetPixelsPerLine_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CGEventSourceGetPixelsPerLine_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSourceGetPixelsPerLine").orElseThrow()
private val CGEventSourceGetPixelsPerLine_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSourceGetPixelsPerLine_ADDR, CGEventSourceGetPixelsPerLine_DESC)

fun CGEventSourceGetPixelsPerLine(arg0: MemorySegment): Double {
    try {
        return CGEventSourceGetPixelsPerLine_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSourceSetPixelsPerLine Void(typedef CGEventSourceRef = (Declared(__CGEventSource))*,Double)
 */
private val CGEventSourceSetPixelsPerLine_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGEventSourceSetPixelsPerLine_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSourceSetPixelsPerLine").orElseThrow()
private val CGEventSourceSetPixelsPerLine_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSourceSetPixelsPerLine_ADDR, CGEventSourceSetPixelsPerLine_DESC)

fun CGEventSourceSetPixelsPerLine(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CGEventSourceSetPixelsPerLine_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSourceSetUserData Void(typedef CGEventSourceRef = (Declared(__CGEventSource))*,typedef int64_t = LongLong)
 */
private val CGEventSourceSetUserData_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGEventSourceSetUserData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSourceSetUserData").orElseThrow()
private val CGEventSourceSetUserData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSourceSetUserData_ADDR, CGEventSourceSetUserData_DESC)

fun CGEventSourceSetUserData(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CGEventSourceSetUserData_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSourceGetUserData typedef int64_t = LongLong(typedef CGEventSourceRef = (Declared(__CGEventSource))*)
 */
private val CGEventSourceGetUserData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGEventSourceGetUserData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSourceGetUserData").orElseThrow()
private val CGEventSourceGetUserData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSourceGetUserData_ADDR, CGEventSourceGetUserData_DESC)

fun CGEventSourceGetUserData(arg0: MemorySegment): Long {
    try {
        return CGEventSourceGetUserData_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSourceSetLocalEventsSuppressionInterval Void(typedef CGEventSourceRef = (Declared(__CGEventSource))*,typedef CFTimeInterval = Double)
 */
private val CGEventSourceSetLocalEventsSuppressionInterval_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGEventSourceSetLocalEventsSuppressionInterval_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSourceSetLocalEventsSuppressionInterval").orElseThrow()
private val CGEventSourceSetLocalEventsSuppressionInterval_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSourceSetLocalEventsSuppressionInterval_ADDR, CGEventSourceSetLocalEventsSuppressionInterval_DESC)

fun CGEventSourceSetLocalEventsSuppressionInterval(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CGEventSourceSetLocalEventsSuppressionInterval_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGEventSourceGetLocalEventsSuppressionInterval typedef CFTimeInterval = Double(typedef CGEventSourceRef = (Declared(__CGEventSource))*)
 */
private val CGEventSourceGetLocalEventsSuppressionInterval_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CGEventSourceGetLocalEventsSuppressionInterval_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEventSourceGetLocalEventsSuppressionInterval").orElseThrow()
private val CGEventSourceGetLocalEventsSuppressionInterval_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEventSourceGetLocalEventsSuppressionInterval_ADDR, CGEventSourceGetLocalEventsSuppressionInterval_DESC)

fun CGEventSourceGetLocalEventsSuppressionInterval(arg0: MemorySegment): Double {
    try {
        return CGEventSourceGetLocalEventsSuppressionInterval_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPSConverterCreate typedef CGPSConverterRef = (Declared(CGPSConverter))*((Void)*,(typedef CGPSConverterCallbacks = Declared(CGPSConverterCallbacks))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGPSConverterCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPSConverterCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPSConverterCreate").orElseThrow()
private val CGPSConverterCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPSConverterCreate_ADDR, CGPSConverterCreate_DESC)

fun CGPSConverterCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGPSConverterCreate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPSConverterConvert Bool(typedef CGPSConverterRef = (Declared(CGPSConverter))*,typedef CGDataProviderRef = (Declared(CGDataProvider))*,typedef CGDataConsumerRef = (Declared(CGDataConsumer))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGPSConverterConvert_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPSConverterConvert_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPSConverterConvert").orElseThrow()
private val CGPSConverterConvert_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPSConverterConvert_ADDR, CGPSConverterConvert_DESC)

fun CGPSConverterConvert(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Boolean {
    try {
        return CGPSConverterConvert_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPSConverterAbort Bool(typedef CGPSConverterRef = (Declared(CGPSConverter))*)
 */
private val CGPSConverterAbort_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGPSConverterAbort_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPSConverterAbort").orElseThrow()
private val CGPSConverterAbort_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPSConverterAbort_ADDR, CGPSConverterAbort_DESC)

fun CGPSConverterAbort(arg0: MemorySegment): Boolean {
    try {
        return CGPSConverterAbort_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPSConverterIsConverting Bool(typedef CGPSConverterRef = (Declared(CGPSConverter))*)
 */
private val CGPSConverterIsConverting_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGPSConverterIsConverting_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPSConverterIsConverting").orElseThrow()
private val CGPSConverterIsConverting_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPSConverterIsConverting_ADDR, CGPSConverterIsConverting_DESC)

fun CGPSConverterIsConverting(arg0: MemorySegment): Boolean {
    try {
        return CGPSConverterIsConverting_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPSConverterGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGPSConverterGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGPSConverterGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPSConverterGetTypeID").orElseThrow()
private val CGPSConverterGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPSConverterGetTypeID_ADDR, CGPSConverterGetTypeID_DESC)

fun CGPSConverterGetTypeID(): Long {
    try {
        return CGPSConverterGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGSessionCopyCurrentDictionary typedef CFDictionaryRef = (Declared(__CFDictionary))*()
 */
private val CGSessionCopyCurrentDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CGSessionCopyCurrentDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGSessionCopyCurrentDictionary").orElseThrow()
private val CGSessionCopyCurrentDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGSessionCopyCurrentDictionary_ADDR, CGSessionCopyCurrentDictionary_DESC)

fun CGSessionCopyCurrentDictionary(): MemorySegment {
    try {
        return CGSessionCopyCurrentDictionary_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDirectDisplayCopyCurrentMetalDevice (Void)*(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDirectDisplayCopyCurrentMetalDevice_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGDirectDisplayCopyCurrentMetalDevice_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDirectDisplayCopyCurrentMetalDevice").orElseThrow()
private val CGDirectDisplayCopyCurrentMetalDevice_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDirectDisplayCopyCurrentMetalDevice_ADDR, CGDirectDisplayCopyCurrentMetalDevice_DESC)

fun CGDirectDisplayCopyCurrentMetalDevice(arg0: Int): MemorySegment {
    try {
        return CGDirectDisplayCopyCurrentMetalDevice_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSPasteboardTypeString typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeString_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeString_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeString").orElseThrow() }
private val NSPasteboardTypeString_VH: VarHandle by lazy { NSPasteboardTypeString_LAYOUT.varHandle() }

var NSPasteboardTypeString: MemorySegment
    get() = NSPasteboardTypeString_VH.get(NSPasteboardTypeString_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeString_VH.set(NSPasteboardTypeString_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypePDF typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypePDF_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypePDF_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypePDF").orElseThrow() }
private val NSPasteboardTypePDF_VH: VarHandle by lazy { NSPasteboardTypePDF_LAYOUT.varHandle() }

var NSPasteboardTypePDF: MemorySegment
    get() = NSPasteboardTypePDF_VH.get(NSPasteboardTypePDF_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypePDF_VH.set(NSPasteboardTypePDF_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeTIFF typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeTIFF_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeTIFF_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeTIFF").orElseThrow() }
private val NSPasteboardTypeTIFF_VH: VarHandle by lazy { NSPasteboardTypeTIFF_LAYOUT.varHandle() }

var NSPasteboardTypeTIFF: MemorySegment
    get() = NSPasteboardTypeTIFF_VH.get(NSPasteboardTypeTIFF_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeTIFF_VH.set(NSPasteboardTypeTIFF_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypePNG typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypePNG_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypePNG_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypePNG").orElseThrow() }
private val NSPasteboardTypePNG_VH: VarHandle by lazy { NSPasteboardTypePNG_LAYOUT.varHandle() }

var NSPasteboardTypePNG: MemorySegment
    get() = NSPasteboardTypePNG_VH.get(NSPasteboardTypePNG_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypePNG_VH.set(NSPasteboardTypePNG_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeRTF typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeRTF_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeRTF_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeRTF").orElseThrow() }
private val NSPasteboardTypeRTF_VH: VarHandle by lazy { NSPasteboardTypeRTF_LAYOUT.varHandle() }

var NSPasteboardTypeRTF: MemorySegment
    get() = NSPasteboardTypeRTF_VH.get(NSPasteboardTypeRTF_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeRTF_VH.set(NSPasteboardTypeRTF_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeRTFD typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeRTFD_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeRTFD_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeRTFD").orElseThrow() }
private val NSPasteboardTypeRTFD_VH: VarHandle by lazy { NSPasteboardTypeRTFD_LAYOUT.varHandle() }

var NSPasteboardTypeRTFD: MemorySegment
    get() = NSPasteboardTypeRTFD_VH.get(NSPasteboardTypeRTFD_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeRTFD_VH.set(NSPasteboardTypeRTFD_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeHTML typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeHTML_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeHTML_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeHTML").orElseThrow() }
private val NSPasteboardTypeHTML_VH: VarHandle by lazy { NSPasteboardTypeHTML_LAYOUT.varHandle() }

var NSPasteboardTypeHTML: MemorySegment
    get() = NSPasteboardTypeHTML_VH.get(NSPasteboardTypeHTML_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeHTML_VH.set(NSPasteboardTypeHTML_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeTabularText typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeTabularText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeTabularText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeTabularText").orElseThrow() }
private val NSPasteboardTypeTabularText_VH: VarHandle by lazy { NSPasteboardTypeTabularText_LAYOUT.varHandle() }

var NSPasteboardTypeTabularText: MemorySegment
    get() = NSPasteboardTypeTabularText_VH.get(NSPasteboardTypeTabularText_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeTabularText_VH.set(NSPasteboardTypeTabularText_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeFont typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeFont_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeFont_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeFont").orElseThrow() }
private val NSPasteboardTypeFont_VH: VarHandle by lazy { NSPasteboardTypeFont_LAYOUT.varHandle() }

var NSPasteboardTypeFont: MemorySegment
    get() = NSPasteboardTypeFont_VH.get(NSPasteboardTypeFont_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeFont_VH.set(NSPasteboardTypeFont_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeRuler typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeRuler_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeRuler_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeRuler").orElseThrow() }
private val NSPasteboardTypeRuler_VH: VarHandle by lazy { NSPasteboardTypeRuler_LAYOUT.varHandle() }

var NSPasteboardTypeRuler: MemorySegment
    get() = NSPasteboardTypeRuler_VH.get(NSPasteboardTypeRuler_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeRuler_VH.set(NSPasteboardTypeRuler_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeColor typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeColor_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeColor_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeColor").orElseThrow() }
private val NSPasteboardTypeColor_VH: VarHandle by lazy { NSPasteboardTypeColor_LAYOUT.varHandle() }

var NSPasteboardTypeColor: MemorySegment
    get() = NSPasteboardTypeColor_VH.get(NSPasteboardTypeColor_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeColor_VH.set(NSPasteboardTypeColor_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeSound typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeSound_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeSound_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeSound").orElseThrow() }
private val NSPasteboardTypeSound_VH: VarHandle by lazy { NSPasteboardTypeSound_LAYOUT.varHandle() }

var NSPasteboardTypeSound: MemorySegment
    get() = NSPasteboardTypeSound_VH.get(NSPasteboardTypeSound_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeSound_VH.set(NSPasteboardTypeSound_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeMultipleTextSelection typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeMultipleTextSelection_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeMultipleTextSelection_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeMultipleTextSelection").orElseThrow() }
private val NSPasteboardTypeMultipleTextSelection_VH: VarHandle by lazy { NSPasteboardTypeMultipleTextSelection_LAYOUT.varHandle() }

var NSPasteboardTypeMultipleTextSelection: MemorySegment
    get() = NSPasteboardTypeMultipleTextSelection_VH.get(NSPasteboardTypeMultipleTextSelection_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeMultipleTextSelection_VH.set(NSPasteboardTypeMultipleTextSelection_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeTextFinderOptions typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeTextFinderOptions_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeTextFinderOptions_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeTextFinderOptions").orElseThrow() }
private val NSPasteboardTypeTextFinderOptions_VH: VarHandle by lazy { NSPasteboardTypeTextFinderOptions_LAYOUT.varHandle() }

var NSPasteboardTypeTextFinderOptions: MemorySegment
    get() = NSPasteboardTypeTextFinderOptions_VH.get(NSPasteboardTypeTextFinderOptions_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeTextFinderOptions_VH.set(NSPasteboardTypeTextFinderOptions_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeURL typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeURL_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeURL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeURL").orElseThrow() }
private val NSPasteboardTypeURL_VH: VarHandle by lazy { NSPasteboardTypeURL_LAYOUT.varHandle() }

var NSPasteboardTypeURL: MemorySegment
    get() = NSPasteboardTypeURL_VH.get(NSPasteboardTypeURL_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeURL_VH.set(NSPasteboardTypeURL_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeFileURL typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeFileURL_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeFileURL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeFileURL").orElseThrow() }
private val NSPasteboardTypeFileURL_VH: VarHandle by lazy { NSPasteboardTypeFileURL_LAYOUT.varHandle() }

var NSPasteboardTypeFileURL: MemorySegment
    get() = NSPasteboardTypeFileURL_VH.get(NSPasteboardTypeFileURL_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeFileURL_VH.set(NSPasteboardTypeFileURL_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardNameGeneral typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSPasteboardNameGeneral_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardNameGeneral_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardNameGeneral").orElseThrow() }
private val NSPasteboardNameGeneral_VH: VarHandle by lazy { NSPasteboardNameGeneral_LAYOUT.varHandle() }

var NSPasteboardNameGeneral: MemorySegment
    get() = NSPasteboardNameGeneral_VH.get(NSPasteboardNameGeneral_SEGMENT) as MemorySegment
    set(value) = NSPasteboardNameGeneral_VH.set(NSPasteboardNameGeneral_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardNameFont typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSPasteboardNameFont_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardNameFont_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardNameFont").orElseThrow() }
private val NSPasteboardNameFont_VH: VarHandle by lazy { NSPasteboardNameFont_LAYOUT.varHandle() }

var NSPasteboardNameFont: MemorySegment
    get() = NSPasteboardNameFont_VH.get(NSPasteboardNameFont_SEGMENT) as MemorySegment
    set(value) = NSPasteboardNameFont_VH.set(NSPasteboardNameFont_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardNameRuler typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSPasteboardNameRuler_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardNameRuler_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardNameRuler").orElseThrow() }
private val NSPasteboardNameRuler_VH: VarHandle by lazy { NSPasteboardNameRuler_LAYOUT.varHandle() }

var NSPasteboardNameRuler: MemorySegment
    get() = NSPasteboardNameRuler_VH.get(NSPasteboardNameRuler_SEGMENT) as MemorySegment
    set(value) = NSPasteboardNameRuler_VH.set(NSPasteboardNameRuler_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardNameFind typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSPasteboardNameFind_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardNameFind_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardNameFind").orElseThrow() }
private val NSPasteboardNameFind_VH: VarHandle by lazy { NSPasteboardNameFind_LAYOUT.varHandle() }

var NSPasteboardNameFind: MemorySegment
    get() = NSPasteboardNameFind_VH.get(NSPasteboardNameFind_SEGMENT) as MemorySegment
    set(value) = NSPasteboardNameFind_VH.set(NSPasteboardNameFind_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardNameDrag typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSPasteboardNameDrag_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardNameDrag_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardNameDrag").orElseThrow() }
private val NSPasteboardNameDrag_VH: VarHandle by lazy { NSPasteboardNameDrag_LAYOUT.varHandle() }

var NSPasteboardNameDrag: MemorySegment
    get() = NSPasteboardNameDrag_VH.get(NSPasteboardNameDrag_SEGMENT) as MemorySegment
    set(value) = NSPasteboardNameDrag_VH.set(NSPasteboardNameDrag_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternProbableWebURL typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternProbableWebURL_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternProbableWebURL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternProbableWebURL").orElseThrow() }
private val NSPasteboardDetectionPatternProbableWebURL_VH: VarHandle by lazy { NSPasteboardDetectionPatternProbableWebURL_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternProbableWebURL: MemorySegment
    get() = NSPasteboardDetectionPatternProbableWebURL_VH.get(NSPasteboardDetectionPatternProbableWebURL_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternProbableWebURL_VH.set(NSPasteboardDetectionPatternProbableWebURL_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternProbableWebSearch typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternProbableWebSearch_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternProbableWebSearch_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternProbableWebSearch").orElseThrow() }
private val NSPasteboardDetectionPatternProbableWebSearch_VH: VarHandle by lazy { NSPasteboardDetectionPatternProbableWebSearch_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternProbableWebSearch: MemorySegment
    get() = NSPasteboardDetectionPatternProbableWebSearch_VH.get(NSPasteboardDetectionPatternProbableWebSearch_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternProbableWebSearch_VH.set(NSPasteboardDetectionPatternProbableWebSearch_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternNumber typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternNumber").orElseThrow() }
private val NSPasteboardDetectionPatternNumber_VH: VarHandle by lazy { NSPasteboardDetectionPatternNumber_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternNumber: MemorySegment
    get() = NSPasteboardDetectionPatternNumber_VH.get(NSPasteboardDetectionPatternNumber_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternNumber_VH.set(NSPasteboardDetectionPatternNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternLink typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternLink_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternLink_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternLink").orElseThrow() }
private val NSPasteboardDetectionPatternLink_VH: VarHandle by lazy { NSPasteboardDetectionPatternLink_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternLink: MemorySegment
    get() = NSPasteboardDetectionPatternLink_VH.get(NSPasteboardDetectionPatternLink_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternLink_VH.set(NSPasteboardDetectionPatternLink_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternPhoneNumber typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternPhoneNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternPhoneNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternPhoneNumber").orElseThrow() }
private val NSPasteboardDetectionPatternPhoneNumber_VH: VarHandle by lazy { NSPasteboardDetectionPatternPhoneNumber_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternPhoneNumber: MemorySegment
    get() = NSPasteboardDetectionPatternPhoneNumber_VH.get(NSPasteboardDetectionPatternPhoneNumber_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternPhoneNumber_VH.set(NSPasteboardDetectionPatternPhoneNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternEmailAddress typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternEmailAddress_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternEmailAddress_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternEmailAddress").orElseThrow() }
private val NSPasteboardDetectionPatternEmailAddress_VH: VarHandle by lazy { NSPasteboardDetectionPatternEmailAddress_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternEmailAddress: MemorySegment
    get() = NSPasteboardDetectionPatternEmailAddress_VH.get(NSPasteboardDetectionPatternEmailAddress_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternEmailAddress_VH.set(NSPasteboardDetectionPatternEmailAddress_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternPostalAddress typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternPostalAddress_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternPostalAddress_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternPostalAddress").orElseThrow() }
private val NSPasteboardDetectionPatternPostalAddress_VH: VarHandle by lazy { NSPasteboardDetectionPatternPostalAddress_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternPostalAddress: MemorySegment
    get() = NSPasteboardDetectionPatternPostalAddress_VH.get(NSPasteboardDetectionPatternPostalAddress_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternPostalAddress_VH.set(NSPasteboardDetectionPatternPostalAddress_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternCalendarEvent typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternCalendarEvent_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternCalendarEvent_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternCalendarEvent").orElseThrow() }
private val NSPasteboardDetectionPatternCalendarEvent_VH: VarHandle by lazy { NSPasteboardDetectionPatternCalendarEvent_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternCalendarEvent: MemorySegment
    get() = NSPasteboardDetectionPatternCalendarEvent_VH.get(NSPasteboardDetectionPatternCalendarEvent_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternCalendarEvent_VH.set(NSPasteboardDetectionPatternCalendarEvent_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternShipmentTrackingNumber typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternShipmentTrackingNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternShipmentTrackingNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternShipmentTrackingNumber").orElseThrow() }
private val NSPasteboardDetectionPatternShipmentTrackingNumber_VH: VarHandle by lazy { NSPasteboardDetectionPatternShipmentTrackingNumber_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternShipmentTrackingNumber: MemorySegment
    get() = NSPasteboardDetectionPatternShipmentTrackingNumber_VH.get(NSPasteboardDetectionPatternShipmentTrackingNumber_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternShipmentTrackingNumber_VH.set(NSPasteboardDetectionPatternShipmentTrackingNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternFlightNumber typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternFlightNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternFlightNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternFlightNumber").orElseThrow() }
private val NSPasteboardDetectionPatternFlightNumber_VH: VarHandle by lazy { NSPasteboardDetectionPatternFlightNumber_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternFlightNumber: MemorySegment
    get() = NSPasteboardDetectionPatternFlightNumber_VH.get(NSPasteboardDetectionPatternFlightNumber_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternFlightNumber_VH.set(NSPasteboardDetectionPatternFlightNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardDetectionPatternMoneyAmount typedef const NSPasteboardDetectionPattern = (Void)*
 */
private val NSPasteboardDetectionPatternMoneyAmount_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardDetectionPatternMoneyAmount_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardDetectionPatternMoneyAmount").orElseThrow() }
private val NSPasteboardDetectionPatternMoneyAmount_VH: VarHandle by lazy { NSPasteboardDetectionPatternMoneyAmount_LAYOUT.varHandle() }

var NSPasteboardDetectionPatternMoneyAmount: MemorySegment
    get() = NSPasteboardDetectionPatternMoneyAmount_VH.get(NSPasteboardDetectionPatternMoneyAmount_SEGMENT) as MemorySegment
    set(value) = NSPasteboardDetectionPatternMoneyAmount_VH.set(NSPasteboardDetectionPatternMoneyAmount_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardMetadataTypeContentType typedef const NSPasteboardMetadataType = (Void)*
 */
private val NSPasteboardMetadataTypeContentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardMetadataTypeContentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardMetadataTypeContentType").orElseThrow() }
private val NSPasteboardMetadataTypeContentType_VH: VarHandle by lazy { NSPasteboardMetadataTypeContentType_LAYOUT.varHandle() }

var NSPasteboardMetadataTypeContentType: MemorySegment
    get() = NSPasteboardMetadataTypeContentType_VH.get(NSPasteboardMetadataTypeContentType_SEGMENT) as MemorySegment
    set(value) = NSPasteboardMetadataTypeContentType_VH.set(NSPasteboardMetadataTypeContentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardURLReadingFileURLsOnlyKey typedef const NSPasteboardReadingOptionKey = (Void)*
 */
private val NSPasteboardURLReadingFileURLsOnlyKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardURLReadingFileURLsOnlyKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardURLReadingFileURLsOnlyKey").orElseThrow() }
private val NSPasteboardURLReadingFileURLsOnlyKey_VH: VarHandle by lazy { NSPasteboardURLReadingFileURLsOnlyKey_LAYOUT.varHandle() }

var NSPasteboardURLReadingFileURLsOnlyKey: MemorySegment
    get() = NSPasteboardURLReadingFileURLsOnlyKey_VH.get(NSPasteboardURLReadingFileURLsOnlyKey_SEGMENT) as MemorySegment
    set(value) = NSPasteboardURLReadingFileURLsOnlyKey_VH.set(NSPasteboardURLReadingFileURLsOnlyKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardURLReadingContentsConformToTypesKey typedef const NSPasteboardReadingOptionKey = (Void)*
 */
private val NSPasteboardURLReadingContentsConformToTypesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardURLReadingContentsConformToTypesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardURLReadingContentsConformToTypesKey").orElseThrow() }
private val NSPasteboardURLReadingContentsConformToTypesKey_VH: VarHandle by lazy { NSPasteboardURLReadingContentsConformToTypesKey_LAYOUT.varHandle() }

var NSPasteboardURLReadingContentsConformToTypesKey: MemorySegment
    get() = NSPasteboardURLReadingContentsConformToTypesKey_VH.get(NSPasteboardURLReadingContentsConformToTypesKey_SEGMENT) as MemorySegment
    set(value) = NSPasteboardURLReadingContentsConformToTypesKey_VH.set(NSPasteboardURLReadingContentsConformToTypesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileContentsPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSFileContentsPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileContentsPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileContentsPboardType").orElseThrow() }
private val NSFileContentsPboardType_VH: VarHandle by lazy { NSFileContentsPboardType_LAYOUT.varHandle() }

var NSFileContentsPboardType: MemorySegment
    get() = NSFileContentsPboardType_VH.get(NSFileContentsPboardType_SEGMENT) as MemorySegment
    set(value) = NSFileContentsPboardType_VH.set(NSFileContentsPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSCreateFilenamePboardType typedef NSPasteboardType = typedef NSString = (Void)*(typedef NSString = (Void)*)
 */
private val NSCreateFilenamePboardType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSCreateFilenamePboardType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCreateFilenamePboardType").orElseThrow()
private val NSCreateFilenamePboardType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCreateFilenamePboardType_ADDR, NSCreateFilenamePboardType_DESC)

fun NSCreateFilenamePboardType(arg0: MemorySegment): MemorySegment {
    try {
        return NSCreateFilenamePboardType_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCreateFileContentsPboardType typedef NSPasteboardType = typedef NSString = (Void)*(typedef NSString = (Void)*)
 */
private val NSCreateFileContentsPboardType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSCreateFileContentsPboardType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCreateFileContentsPboardType").orElseThrow()
private val NSCreateFileContentsPboardType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCreateFileContentsPboardType_ADDR, NSCreateFileContentsPboardType_DESC)

fun NSCreateFileContentsPboardType(arg0: MemorySegment): MemorySegment {
    try {
        return NSCreateFileContentsPboardType_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSGetFileType typedef NSString = (Void)*(typedef NSPasteboardType = typedef NSString = (Void)*)
 */
private val NSGetFileType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSGetFileType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSGetFileType").orElseThrow()
private val NSGetFileType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSGetFileType_ADDR, NSGetFileType_DESC)

fun NSGetFileType(arg0: MemorySegment): MemorySegment {
    try {
        return NSGetFileType_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSGetFileTypes (Void)*((Void)*)
 */
private val NSGetFileTypes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSGetFileTypes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSGetFileTypes").orElseThrow()
private val NSGetFileTypes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSGetFileTypes_ADDR, NSGetFileTypes_DESC)

fun NSGetFileTypes(arg0: MemorySegment): MemorySegment {
    try {
        return NSGetFileTypes_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSStringPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSStringPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringPboardType").orElseThrow() }
private val NSStringPboardType_VH: VarHandle by lazy { NSStringPboardType_LAYOUT.varHandle() }

var NSStringPboardType: MemorySegment
    get() = NSStringPboardType_VH.get(NSStringPboardType_SEGMENT) as MemorySegment
    set(value) = NSStringPboardType_VH.set(NSStringPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSFilenamesPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSFilenamesPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFilenamesPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFilenamesPboardType").orElseThrow() }
private val NSFilenamesPboardType_VH: VarHandle by lazy { NSFilenamesPboardType_LAYOUT.varHandle() }

var NSFilenamesPboardType: MemorySegment
    get() = NSFilenamesPboardType_VH.get(NSFilenamesPboardType_SEGMENT) as MemorySegment
    set(value) = NSFilenamesPboardType_VH.set(NSFilenamesPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSTIFFPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSTIFFPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTIFFPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTIFFPboardType").orElseThrow() }
private val NSTIFFPboardType_VH: VarHandle by lazy { NSTIFFPboardType_LAYOUT.varHandle() }

var NSTIFFPboardType: MemorySegment
    get() = NSTIFFPboardType_VH.get(NSTIFFPboardType_SEGMENT) as MemorySegment
    set(value) = NSTIFFPboardType_VH.set(NSTIFFPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSRTFPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSRTFPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRTFPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRTFPboardType").orElseThrow() }
private val NSRTFPboardType_VH: VarHandle by lazy { NSRTFPboardType_LAYOUT.varHandle() }

var NSRTFPboardType: MemorySegment
    get() = NSRTFPboardType_VH.get(NSRTFPboardType_SEGMENT) as MemorySegment
    set(value) = NSRTFPboardType_VH.set(NSRTFPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSTabularTextPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSTabularTextPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTabularTextPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTabularTextPboardType").orElseThrow() }
private val NSTabularTextPboardType_VH: VarHandle by lazy { NSTabularTextPboardType_LAYOUT.varHandle() }

var NSTabularTextPboardType: MemorySegment
    get() = NSTabularTextPboardType_VH.get(NSTabularTextPboardType_SEGMENT) as MemorySegment
    set(value) = NSTabularTextPboardType_VH.set(NSTabularTextPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSFontPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontPboardType").orElseThrow() }
private val NSFontPboardType_VH: VarHandle by lazy { NSFontPboardType_LAYOUT.varHandle() }

var NSFontPboardType: MemorySegment
    get() = NSFontPboardType_VH.get(NSFontPboardType_SEGMENT) as MemorySegment
    set(value) = NSFontPboardType_VH.set(NSFontPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSRulerPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSRulerPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRulerPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRulerPboardType").orElseThrow() }
private val NSRulerPboardType_VH: VarHandle by lazy { NSRulerPboardType_LAYOUT.varHandle() }

var NSRulerPboardType: MemorySegment
    get() = NSRulerPboardType_VH.get(NSRulerPboardType_SEGMENT) as MemorySegment
    set(value) = NSRulerPboardType_VH.set(NSRulerPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSColorPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSColorPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSColorPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSColorPboardType").orElseThrow() }
private val NSColorPboardType_VH: VarHandle by lazy { NSColorPboardType_LAYOUT.varHandle() }

var NSColorPboardType: MemorySegment
    get() = NSColorPboardType_VH.get(NSColorPboardType_SEGMENT) as MemorySegment
    set(value) = NSColorPboardType_VH.set(NSColorPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSRTFDPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSRTFDPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRTFDPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRTFDPboardType").orElseThrow() }
private val NSRTFDPboardType_VH: VarHandle by lazy { NSRTFDPboardType_LAYOUT.varHandle() }

var NSRTFDPboardType: MemorySegment
    get() = NSRTFDPboardType_VH.get(NSRTFDPboardType_SEGMENT) as MemorySegment
    set(value) = NSRTFDPboardType_VH.set(NSRTFDPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTMLPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSHTMLPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTMLPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTMLPboardType").orElseThrow() }
private val NSHTMLPboardType_VH: VarHandle by lazy { NSHTMLPboardType_LAYOUT.varHandle() }

var NSHTMLPboardType: MemorySegment
    get() = NSHTMLPboardType_VH.get(NSHTMLPboardType_SEGMENT) as MemorySegment
    set(value) = NSHTMLPboardType_VH.set(NSHTMLPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSURLPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLPboardType").orElseThrow() }
private val NSURLPboardType_VH: VarHandle by lazy { NSURLPboardType_LAYOUT.varHandle() }

var NSURLPboardType: MemorySegment
    get() = NSURLPboardType_VH.get(NSURLPboardType_SEGMENT) as MemorySegment
    set(value) = NSURLPboardType_VH.set(NSURLPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSPDFPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSPDFPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPDFPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPDFPboardType").orElseThrow() }
private val NSPDFPboardType_VH: VarHandle by lazy { NSPDFPboardType_LAYOUT.varHandle() }

var NSPDFPboardType: MemorySegment
    get() = NSPDFPboardType_VH.get(NSPDFPboardType_SEGMENT) as MemorySegment
    set(value) = NSPDFPboardType_VH.set(NSPDFPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSMultipleTextSelectionPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSMultipleTextSelectionPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMultipleTextSelectionPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMultipleTextSelectionPboardType").orElseThrow() }
private val NSMultipleTextSelectionPboardType_VH: VarHandle by lazy { NSMultipleTextSelectionPboardType_LAYOUT.varHandle() }

var NSMultipleTextSelectionPboardType: MemorySegment
    get() = NSMultipleTextSelectionPboardType_VH.get(NSMultipleTextSelectionPboardType_SEGMENT) as MemorySegment
    set(value) = NSMultipleTextSelectionPboardType_VH.set(NSMultipleTextSelectionPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSPostScriptPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSPostScriptPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPostScriptPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPostScriptPboardType").orElseThrow() }
private val NSPostScriptPboardType_VH: VarHandle by lazy { NSPostScriptPboardType_LAYOUT.varHandle() }

var NSPostScriptPboardType: MemorySegment
    get() = NSPostScriptPboardType_VH.get(NSPostScriptPboardType_SEGMENT) as MemorySegment
    set(value) = NSPostScriptPboardType_VH.set(NSPostScriptPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSVCardPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSVCardPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVCardPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVCardPboardType").orElseThrow() }
private val NSVCardPboardType_VH: VarHandle by lazy { NSVCardPboardType_LAYOUT.varHandle() }

var NSVCardPboardType: MemorySegment
    get() = NSVCardPboardType_VH.get(NSVCardPboardType_SEGMENT) as MemorySegment
    set(value) = NSVCardPboardType_VH.set(NSVCardPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSInkTextPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSInkTextPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInkTextPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInkTextPboardType").orElseThrow() }
private val NSInkTextPboardType_VH: VarHandle by lazy { NSInkTextPboardType_LAYOUT.varHandle() }

var NSInkTextPboardType: MemorySegment
    get() = NSInkTextPboardType_VH.get(NSInkTextPboardType_SEGMENT) as MemorySegment
    set(value) = NSInkTextPboardType_VH.set(NSInkTextPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSFilesPromisePboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSFilesPromisePboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFilesPromisePboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFilesPromisePboardType").orElseThrow() }
private val NSFilesPromisePboardType_VH: VarHandle by lazy { NSFilesPromisePboardType_LAYOUT.varHandle() }

var NSFilesPromisePboardType: MemorySegment
    get() = NSFilesPromisePboardType_VH.get(NSFilesPromisePboardType_SEGMENT) as MemorySegment
    set(value) = NSFilesPromisePboardType_VH.set(NSFilesPromisePboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardTypeFindPanelSearchOptions typedef const NSPasteboardType = (Void)*
 */
private val NSPasteboardTypeFindPanelSearchOptions_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardTypeFindPanelSearchOptions_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardTypeFindPanelSearchOptions").orElseThrow() }
private val NSPasteboardTypeFindPanelSearchOptions_VH: VarHandle by lazy { NSPasteboardTypeFindPanelSearchOptions_LAYOUT.varHandle() }

var NSPasteboardTypeFindPanelSearchOptions: MemorySegment
    get() = NSPasteboardTypeFindPanelSearchOptions_VH.get(NSPasteboardTypeFindPanelSearchOptions_SEGMENT) as MemorySegment
    set(value) = NSPasteboardTypeFindPanelSearchOptions_VH.set(NSPasteboardTypeFindPanelSearchOptions_SEGMENT, value)

/**
 * {@snippet lang=c : NSGeneralPboard typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSGeneralPboard_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGeneralPboard_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGeneralPboard").orElseThrow() }
private val NSGeneralPboard_VH: VarHandle by lazy { NSGeneralPboard_LAYOUT.varHandle() }

var NSGeneralPboard: MemorySegment
    get() = NSGeneralPboard_VH.get(NSGeneralPboard_SEGMENT) as MemorySegment
    set(value) = NSGeneralPboard_VH.set(NSGeneralPboard_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontPboard typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSFontPboard_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontPboard_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontPboard").orElseThrow() }
private val NSFontPboard_VH: VarHandle by lazy { NSFontPboard_LAYOUT.varHandle() }

var NSFontPboard: MemorySegment
    get() = NSFontPboard_VH.get(NSFontPboard_SEGMENT) as MemorySegment
    set(value) = NSFontPboard_VH.set(NSFontPboard_SEGMENT, value)

/**
 * {@snippet lang=c : NSRulerPboard typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSRulerPboard_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRulerPboard_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRulerPboard").orElseThrow() }
private val NSRulerPboard_VH: VarHandle by lazy { NSRulerPboard_LAYOUT.varHandle() }

var NSRulerPboard: MemorySegment
    get() = NSRulerPboard_VH.get(NSRulerPboard_SEGMENT) as MemorySegment
    set(value) = NSRulerPboard_VH.set(NSRulerPboard_SEGMENT, value)

/**
 * {@snippet lang=c : NSFindPboard typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSFindPboard_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFindPboard_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFindPboard").orElseThrow() }
private val NSFindPboard_VH: VarHandle by lazy { NSFindPboard_LAYOUT.varHandle() }

var NSFindPboard: MemorySegment
    get() = NSFindPboard_VH.get(NSFindPboard_SEGMENT) as MemorySegment
    set(value) = NSFindPboard_VH.set(NSFindPboard_SEGMENT, value)

/**
 * {@snippet lang=c : NSDragPboard typedef NSPasteboardName = typedef NSString = (Void)*
 */
private val NSDragPboard_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDragPboard_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDragPboard").orElseThrow() }
private val NSDragPboard_VH: VarHandle by lazy { NSDragPboard_LAYOUT.varHandle() }

var NSDragPboard: MemorySegment
    get() = NSDragPboard_VH.get(NSDragPboard_SEGMENT) as MemorySegment
    set(value) = NSDragPboard_VH.set(NSDragPboard_SEGMENT, value)

/**
 * {@snippet lang=c : NSPICTPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSPICTPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPICTPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPICTPboardType").orElseThrow() }
private val NSPICTPboardType_VH: VarHandle by lazy { NSPICTPboardType_LAYOUT.varHandle() }

var NSPICTPboardType: MemorySegment
    get() = NSPICTPboardType_VH.get(NSPICTPboardType_SEGMENT) as MemorySegment
    set(value) = NSPICTPboardType_VH.set(NSPICTPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSNibOwner typedef NSString = (Void)*
 */
private val NSNibOwner_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNibOwner_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNibOwner").orElseThrow() }
private val NSNibOwner_VH: VarHandle by lazy { NSNibOwner_LAYOUT.varHandle() }

var NSNibOwner: MemorySegment
    get() = NSNibOwner_VH.get(NSNibOwner_SEGMENT) as MemorySegment
    set(value) = NSNibOwner_VH.set(NSNibOwner_SEGMENT, value)

/**
 * {@snippet lang=c : NSNibTopLevelObjects typedef NSString = (Void)*
 */
private val NSNibTopLevelObjects_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNibTopLevelObjects_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNibTopLevelObjects").orElseThrow() }
private val NSNibTopLevelObjects_VH: VarHandle by lazy { NSNibTopLevelObjects_LAYOUT.varHandle() }

var NSNibTopLevelObjects: MemorySegment
    get() = NSNibTopLevelObjects_VH.get(NSNibTopLevelObjects_SEGMENT) as MemorySegment
    set(value) = NSNibTopLevelObjects_VH.set(NSNibTopLevelObjects_SEGMENT, value)

/**
 * {@snippet lang=c : NSAnimationProgressMarkNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSAnimationProgressMarkNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAnimationProgressMarkNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAnimationProgressMarkNotification").orElseThrow() }
private val NSAnimationProgressMarkNotification_VH: VarHandle by lazy { NSAnimationProgressMarkNotification_LAYOUT.varHandle() }

var NSAnimationProgressMarkNotification: MemorySegment
    get() = NSAnimationProgressMarkNotification_VH.get(NSAnimationProgressMarkNotification_SEGMENT) as MemorySegment
    set(value) = NSAnimationProgressMarkNotification_VH.set(NSAnimationProgressMarkNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAnimationProgressMark typedef NSString = (Void)*
 */
private val NSAnimationProgressMark_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAnimationProgressMark_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAnimationProgressMark").orElseThrow() }
private val NSAnimationProgressMark_VH: VarHandle by lazy { NSAnimationProgressMark_LAYOUT.varHandle() }

var NSAnimationProgressMark: MemorySegment
    get() = NSAnimationProgressMark_VH.get(NSAnimationProgressMark_SEGMENT) as MemorySegment
    set(value) = NSAnimationProgressMark_VH.set(NSAnimationProgressMark_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewAnimationTargetKey typedef NSViewAnimationKey = typedef NSString = (Void)*
 */
private val NSViewAnimationTargetKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewAnimationTargetKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewAnimationTargetKey").orElseThrow() }
private val NSViewAnimationTargetKey_VH: VarHandle by lazy { NSViewAnimationTargetKey_LAYOUT.varHandle() }

var NSViewAnimationTargetKey: MemorySegment
    get() = NSViewAnimationTargetKey_VH.get(NSViewAnimationTargetKey_SEGMENT) as MemorySegment
    set(value) = NSViewAnimationTargetKey_VH.set(NSViewAnimationTargetKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewAnimationStartFrameKey typedef NSViewAnimationKey = typedef NSString = (Void)*
 */
private val NSViewAnimationStartFrameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewAnimationStartFrameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewAnimationStartFrameKey").orElseThrow() }
private val NSViewAnimationStartFrameKey_VH: VarHandle by lazy { NSViewAnimationStartFrameKey_LAYOUT.varHandle() }

var NSViewAnimationStartFrameKey: MemorySegment
    get() = NSViewAnimationStartFrameKey_VH.get(NSViewAnimationStartFrameKey_SEGMENT) as MemorySegment
    set(value) = NSViewAnimationStartFrameKey_VH.set(NSViewAnimationStartFrameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewAnimationEndFrameKey typedef NSViewAnimationKey = typedef NSString = (Void)*
 */
private val NSViewAnimationEndFrameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewAnimationEndFrameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewAnimationEndFrameKey").orElseThrow() }
private val NSViewAnimationEndFrameKey_VH: VarHandle by lazy { NSViewAnimationEndFrameKey_LAYOUT.varHandle() }

var NSViewAnimationEndFrameKey: MemorySegment
    get() = NSViewAnimationEndFrameKey_VH.get(NSViewAnimationEndFrameKey_SEGMENT) as MemorySegment
    set(value) = NSViewAnimationEndFrameKey_VH.set(NSViewAnimationEndFrameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewAnimationEffectKey typedef NSViewAnimationKey = typedef NSString = (Void)*
 */
private val NSViewAnimationEffectKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewAnimationEffectKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewAnimationEffectKey").orElseThrow() }
private val NSViewAnimationEffectKey_VH: VarHandle by lazy { NSViewAnimationEffectKey_LAYOUT.varHandle() }

var NSViewAnimationEffectKey: MemorySegment
    get() = NSViewAnimationEffectKey_VH.get(NSViewAnimationEffectKey_SEGMENT) as MemorySegment
    set(value) = NSViewAnimationEffectKey_VH.set(NSViewAnimationEffectKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewAnimationFadeInEffect typedef NSViewAnimationEffectName = typedef NSString = (Void)*
 */
private val NSViewAnimationFadeInEffect_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewAnimationFadeInEffect_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewAnimationFadeInEffect").orElseThrow() }
private val NSViewAnimationFadeInEffect_VH: VarHandle by lazy { NSViewAnimationFadeInEffect_LAYOUT.varHandle() }

var NSViewAnimationFadeInEffect: MemorySegment
    get() = NSViewAnimationFadeInEffect_VH.get(NSViewAnimationFadeInEffect_SEGMENT) as MemorySegment
    set(value) = NSViewAnimationFadeInEffect_VH.set(NSViewAnimationFadeInEffect_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewAnimationFadeOutEffect typedef NSViewAnimationEffectName = typedef NSString = (Void)*
 */
private val NSViewAnimationFadeOutEffect_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewAnimationFadeOutEffect_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewAnimationFadeOutEffect").orElseThrow() }
private val NSViewAnimationFadeOutEffect_VH: VarHandle by lazy { NSViewAnimationFadeOutEffect_LAYOUT.varHandle() }

var NSViewAnimationFadeOutEffect: MemorySegment
    get() = NSViewAnimationFadeOutEffect_VH.get(NSViewAnimationFadeOutEffect_SEGMENT) as MemorySegment
    set(value) = NSViewAnimationFadeOutEffect_VH.set(NSViewAnimationFadeOutEffect_SEGMENT, value)

/**
 * {@snippet lang=c : NSAnimationTriggerOrderIn typedef NSAnimatablePropertyKey = typedef NSString = (Void)*
 */
private val NSAnimationTriggerOrderIn_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAnimationTriggerOrderIn_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAnimationTriggerOrderIn").orElseThrow() }
private val NSAnimationTriggerOrderIn_VH: VarHandle by lazy { NSAnimationTriggerOrderIn_LAYOUT.varHandle() }

var NSAnimationTriggerOrderIn: MemorySegment
    get() = NSAnimationTriggerOrderIn_VH.get(NSAnimationTriggerOrderIn_SEGMENT) as MemorySegment
    set(value) = NSAnimationTriggerOrderIn_VH.set(NSAnimationTriggerOrderIn_SEGMENT, value)

/**
 * {@snippet lang=c : NSAnimationTriggerOrderOut typedef NSAnimatablePropertyKey = typedef NSString = (Void)*
 */
private val NSAnimationTriggerOrderOut_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAnimationTriggerOrderOut_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAnimationTriggerOrderOut").orElseThrow() }
private val NSAnimationTriggerOrderOut_VH: VarHandle by lazy { NSAnimationTriggerOrderOut_LAYOUT.varHandle() }

var NSAnimationTriggerOrderOut: MemorySegment
    get() = NSAnimationTriggerOrderOut_VH.get(NSAnimationTriggerOrderOut_SEGMENT) as MemorySegment
    set(value) = NSAnimationTriggerOrderOut_VH.set(NSAnimationTriggerOrderOut_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceNameAqua typedef const NSAppearanceName = (Void)*
 */
private val NSAppearanceNameAqua_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceNameAqua_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceNameAqua").orElseThrow() }
private val NSAppearanceNameAqua_VH: VarHandle by lazy { NSAppearanceNameAqua_LAYOUT.varHandle() }

var NSAppearanceNameAqua: MemorySegment
    get() = NSAppearanceNameAqua_VH.get(NSAppearanceNameAqua_SEGMENT) as MemorySegment
    set(value) = NSAppearanceNameAqua_VH.set(NSAppearanceNameAqua_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceNameDarkAqua typedef const NSAppearanceName = (Void)*
 */
private val NSAppearanceNameDarkAqua_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceNameDarkAqua_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceNameDarkAqua").orElseThrow() }
private val NSAppearanceNameDarkAqua_VH: VarHandle by lazy { NSAppearanceNameDarkAqua_LAYOUT.varHandle() }

var NSAppearanceNameDarkAqua: MemorySegment
    get() = NSAppearanceNameDarkAqua_VH.get(NSAppearanceNameDarkAqua_SEGMENT) as MemorySegment
    set(value) = NSAppearanceNameDarkAqua_VH.set(NSAppearanceNameDarkAqua_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceNameLightContent typedef const NSAppearanceName = (Void)*
 */
private val NSAppearanceNameLightContent_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceNameLightContent_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceNameLightContent").orElseThrow() }
private val NSAppearanceNameLightContent_VH: VarHandle by lazy { NSAppearanceNameLightContent_LAYOUT.varHandle() }

var NSAppearanceNameLightContent: MemorySegment
    get() = NSAppearanceNameLightContent_VH.get(NSAppearanceNameLightContent_SEGMENT) as MemorySegment
    set(value) = NSAppearanceNameLightContent_VH.set(NSAppearanceNameLightContent_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceNameVibrantDark typedef const NSAppearanceName = (Void)*
 */
private val NSAppearanceNameVibrantDark_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceNameVibrantDark_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceNameVibrantDark").orElseThrow() }
private val NSAppearanceNameVibrantDark_VH: VarHandle by lazy { NSAppearanceNameVibrantDark_LAYOUT.varHandle() }

var NSAppearanceNameVibrantDark: MemorySegment
    get() = NSAppearanceNameVibrantDark_VH.get(NSAppearanceNameVibrantDark_SEGMENT) as MemorySegment
    set(value) = NSAppearanceNameVibrantDark_VH.set(NSAppearanceNameVibrantDark_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceNameVibrantLight typedef const NSAppearanceName = (Void)*
 */
private val NSAppearanceNameVibrantLight_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceNameVibrantLight_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceNameVibrantLight").orElseThrow() }
private val NSAppearanceNameVibrantLight_VH: VarHandle by lazy { NSAppearanceNameVibrantLight_LAYOUT.varHandle() }

var NSAppearanceNameVibrantLight: MemorySegment
    get() = NSAppearanceNameVibrantLight_VH.get(NSAppearanceNameVibrantLight_SEGMENT) as MemorySegment
    set(value) = NSAppearanceNameVibrantLight_VH.set(NSAppearanceNameVibrantLight_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceNameAccessibilityHighContrastAqua typedef const NSAppearanceName = (Void)*
 */
private val NSAppearanceNameAccessibilityHighContrastAqua_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceNameAccessibilityHighContrastAqua_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceNameAccessibilityHighContrastAqua").orElseThrow() }
private val NSAppearanceNameAccessibilityHighContrastAqua_VH: VarHandle by lazy { NSAppearanceNameAccessibilityHighContrastAqua_LAYOUT.varHandle() }

var NSAppearanceNameAccessibilityHighContrastAqua: MemorySegment
    get() = NSAppearanceNameAccessibilityHighContrastAqua_VH.get(NSAppearanceNameAccessibilityHighContrastAqua_SEGMENT) as MemorySegment
    set(value) = NSAppearanceNameAccessibilityHighContrastAqua_VH.set(NSAppearanceNameAccessibilityHighContrastAqua_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceNameAccessibilityHighContrastDarkAqua typedef const NSAppearanceName = (Void)*
 */
private val NSAppearanceNameAccessibilityHighContrastDarkAqua_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceNameAccessibilityHighContrastDarkAqua_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceNameAccessibilityHighContrastDarkAqua").orElseThrow() }
private val NSAppearanceNameAccessibilityHighContrastDarkAqua_VH: VarHandle by lazy { NSAppearanceNameAccessibilityHighContrastDarkAqua_LAYOUT.varHandle() }

var NSAppearanceNameAccessibilityHighContrastDarkAqua: MemorySegment
    get() = NSAppearanceNameAccessibilityHighContrastDarkAqua_VH.get(NSAppearanceNameAccessibilityHighContrastDarkAqua_SEGMENT) as MemorySegment
    set(value) = NSAppearanceNameAccessibilityHighContrastDarkAqua_VH.set(NSAppearanceNameAccessibilityHighContrastDarkAqua_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceNameAccessibilityHighContrastVibrantLight typedef const NSAppearanceName = (Void)*
 */
private val NSAppearanceNameAccessibilityHighContrastVibrantLight_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceNameAccessibilityHighContrastVibrantLight_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceNameAccessibilityHighContrastVibrantLight").orElseThrow() }
private val NSAppearanceNameAccessibilityHighContrastVibrantLight_VH: VarHandle by lazy { NSAppearanceNameAccessibilityHighContrastVibrantLight_LAYOUT.varHandle() }

var NSAppearanceNameAccessibilityHighContrastVibrantLight: MemorySegment
    get() = NSAppearanceNameAccessibilityHighContrastVibrantLight_VH.get(NSAppearanceNameAccessibilityHighContrastVibrantLight_SEGMENT) as MemorySegment
    set(value) = NSAppearanceNameAccessibilityHighContrastVibrantLight_VH.set(NSAppearanceNameAccessibilityHighContrastVibrantLight_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceNameAccessibilityHighContrastVibrantDark typedef const NSAppearanceName = (Void)*
 */
private val NSAppearanceNameAccessibilityHighContrastVibrantDark_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceNameAccessibilityHighContrastVibrantDark_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceNameAccessibilityHighContrastVibrantDark").orElseThrow() }
private val NSAppearanceNameAccessibilityHighContrastVibrantDark_VH: VarHandle by lazy { NSAppearanceNameAccessibilityHighContrastVibrantDark_LAYOUT.varHandle() }

var NSAppearanceNameAccessibilityHighContrastVibrantDark: MemorySegment
    get() = NSAppearanceNameAccessibilityHighContrastVibrantDark_VH.get(NSAppearanceNameAccessibilityHighContrastVibrantDark_SEGMENT) as MemorySegment
    set(value) = NSAppearanceNameAccessibilityHighContrastVibrantDark_VH.set(NSAppearanceNameAccessibilityHighContrastVibrantDark_SEGMENT, value)

/**
 * {@snippet lang=c : NSFullScreenModeAllScreens typedef const NSViewFullScreenModeOptionKey = (Void)*
 */
private val NSFullScreenModeAllScreens_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFullScreenModeAllScreens_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFullScreenModeAllScreens").orElseThrow() }
private val NSFullScreenModeAllScreens_VH: VarHandle by lazy { NSFullScreenModeAllScreens_LAYOUT.varHandle() }

var NSFullScreenModeAllScreens: MemorySegment
    get() = NSFullScreenModeAllScreens_VH.get(NSFullScreenModeAllScreens_SEGMENT) as MemorySegment
    set(value) = NSFullScreenModeAllScreens_VH.set(NSFullScreenModeAllScreens_SEGMENT, value)

/**
 * {@snippet lang=c : NSFullScreenModeSetting typedef const NSViewFullScreenModeOptionKey = (Void)*
 */
private val NSFullScreenModeSetting_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFullScreenModeSetting_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFullScreenModeSetting").orElseThrow() }
private val NSFullScreenModeSetting_VH: VarHandle by lazy { NSFullScreenModeSetting_LAYOUT.varHandle() }

var NSFullScreenModeSetting: MemorySegment
    get() = NSFullScreenModeSetting_VH.get(NSFullScreenModeSetting_SEGMENT) as MemorySegment
    set(value) = NSFullScreenModeSetting_VH.set(NSFullScreenModeSetting_SEGMENT, value)

/**
 * {@snippet lang=c : NSFullScreenModeWindowLevel typedef const NSViewFullScreenModeOptionKey = (Void)*
 */
private val NSFullScreenModeWindowLevel_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFullScreenModeWindowLevel_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFullScreenModeWindowLevel").orElseThrow() }
private val NSFullScreenModeWindowLevel_VH: VarHandle by lazy { NSFullScreenModeWindowLevel_LAYOUT.varHandle() }

var NSFullScreenModeWindowLevel: MemorySegment
    get() = NSFullScreenModeWindowLevel_VH.get(NSFullScreenModeWindowLevel_SEGMENT) as MemorySegment
    set(value) = NSFullScreenModeWindowLevel_VH.set(NSFullScreenModeWindowLevel_SEGMENT, value)

/**
 * {@snippet lang=c : NSFullScreenModeApplicationPresentationOptions typedef const NSViewFullScreenModeOptionKey = (Void)*
 */
private val NSFullScreenModeApplicationPresentationOptions_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFullScreenModeApplicationPresentationOptions_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFullScreenModeApplicationPresentationOptions").orElseThrow() }
private val NSFullScreenModeApplicationPresentationOptions_VH: VarHandle by lazy { NSFullScreenModeApplicationPresentationOptions_LAYOUT.varHandle() }

var NSFullScreenModeApplicationPresentationOptions: MemorySegment
    get() = NSFullScreenModeApplicationPresentationOptions_VH.get(NSFullScreenModeApplicationPresentationOptions_SEGMENT) as MemorySegment
    set(value) = NSFullScreenModeApplicationPresentationOptions_VH.set(NSFullScreenModeApplicationPresentationOptions_SEGMENT, value)

/**
 * {@snippet lang=c : NSDefinitionPresentationTypeKey typedef const NSDefinitionOptionKey = (Void)*
 */
private val NSDefinitionPresentationTypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDefinitionPresentationTypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDefinitionPresentationTypeKey").orElseThrow() }
private val NSDefinitionPresentationTypeKey_VH: VarHandle by lazy { NSDefinitionPresentationTypeKey_LAYOUT.varHandle() }

var NSDefinitionPresentationTypeKey: MemorySegment
    get() = NSDefinitionPresentationTypeKey_VH.get(NSDefinitionPresentationTypeKey_SEGMENT) as MemorySegment
    set(value) = NSDefinitionPresentationTypeKey_VH.set(NSDefinitionPresentationTypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSDefinitionPresentationTypeOverlay typedef const NSDefinitionPresentationType = (Void)*
 */
private val NSDefinitionPresentationTypeOverlay_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDefinitionPresentationTypeOverlay_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDefinitionPresentationTypeOverlay").orElseThrow() }
private val NSDefinitionPresentationTypeOverlay_VH: VarHandle by lazy { NSDefinitionPresentationTypeOverlay_LAYOUT.varHandle() }

var NSDefinitionPresentationTypeOverlay: MemorySegment
    get() = NSDefinitionPresentationTypeOverlay_VH.get(NSDefinitionPresentationTypeOverlay_SEGMENT) as MemorySegment
    set(value) = NSDefinitionPresentationTypeOverlay_VH.set(NSDefinitionPresentationTypeOverlay_SEGMENT, value)

/**
 * {@snippet lang=c : NSDefinitionPresentationTypeDictionaryApplication typedef const NSDefinitionPresentationType = (Void)*
 */
private val NSDefinitionPresentationTypeDictionaryApplication_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDefinitionPresentationTypeDictionaryApplication_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDefinitionPresentationTypeDictionaryApplication").orElseThrow() }
private val NSDefinitionPresentationTypeDictionaryApplication_VH: VarHandle by lazy { NSDefinitionPresentationTypeDictionaryApplication_LAYOUT.varHandle() }

var NSDefinitionPresentationTypeDictionaryApplication: MemorySegment
    get() = NSDefinitionPresentationTypeDictionaryApplication_VH.get(NSDefinitionPresentationTypeDictionaryApplication_SEGMENT) as MemorySegment
    set(value) = NSDefinitionPresentationTypeDictionaryApplication_VH.set(NSDefinitionPresentationTypeDictionaryApplication_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewFrameDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSViewFrameDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewFrameDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewFrameDidChangeNotification").orElseThrow() }
private val NSViewFrameDidChangeNotification_VH: VarHandle by lazy { NSViewFrameDidChangeNotification_LAYOUT.varHandle() }

var NSViewFrameDidChangeNotification: MemorySegment
    get() = NSViewFrameDidChangeNotification_VH.get(NSViewFrameDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSViewFrameDidChangeNotification_VH.set(NSViewFrameDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewFocusDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSViewFocusDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewFocusDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewFocusDidChangeNotification").orElseThrow() }
private val NSViewFocusDidChangeNotification_VH: VarHandle by lazy { NSViewFocusDidChangeNotification_LAYOUT.varHandle() }

var NSViewFocusDidChangeNotification: MemorySegment
    get() = NSViewFocusDidChangeNotification_VH.get(NSViewFocusDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSViewFocusDidChangeNotification_VH.set(NSViewFocusDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewBoundsDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSViewBoundsDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewBoundsDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewBoundsDidChangeNotification").orElseThrow() }
private val NSViewBoundsDidChangeNotification_VH: VarHandle by lazy { NSViewBoundsDidChangeNotification_LAYOUT.varHandle() }

var NSViewBoundsDidChangeNotification: MemorySegment
    get() = NSViewBoundsDidChangeNotification_VH.get(NSViewBoundsDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSViewBoundsDidChangeNotification_VH.set(NSViewBoundsDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewGlobalFrameDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSViewGlobalFrameDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewGlobalFrameDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewGlobalFrameDidChangeNotification").orElseThrow() }
private val NSViewGlobalFrameDidChangeNotification_VH: VarHandle by lazy { NSViewGlobalFrameDidChangeNotification_LAYOUT.varHandle() }

var NSViewGlobalFrameDidChangeNotification: MemorySegment
    get() = NSViewGlobalFrameDidChangeNotification_VH.get(NSViewGlobalFrameDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSViewGlobalFrameDidChangeNotification_VH.set(NSViewGlobalFrameDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewDidUpdateTrackingAreasNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSViewDidUpdateTrackingAreasNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewDidUpdateTrackingAreasNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewDidUpdateTrackingAreasNotification").orElseThrow() }
private val NSViewDidUpdateTrackingAreasNotification_VH: VarHandle by lazy { NSViewDidUpdateTrackingAreasNotification_LAYOUT.varHandle() }

var NSViewDidUpdateTrackingAreasNotification: MemorySegment
    get() = NSViewDidUpdateTrackingAreasNotification_VH.get(NSViewDidUpdateTrackingAreasNotification_SEGMENT) as MemorySegment
    set(value) = NSViewDidUpdateTrackingAreasNotification_VH.set(NSViewDidUpdateTrackingAreasNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextDidBeginEditingNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextDidBeginEditingNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextDidBeginEditingNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextDidBeginEditingNotification").orElseThrow() }
private val NSTextDidBeginEditingNotification_VH: VarHandle by lazy { NSTextDidBeginEditingNotification_LAYOUT.varHandle() }

var NSTextDidBeginEditingNotification: MemorySegment
    get() = NSTextDidBeginEditingNotification_VH.get(NSTextDidBeginEditingNotification_SEGMENT) as MemorySegment
    set(value) = NSTextDidBeginEditingNotification_VH.set(NSTextDidBeginEditingNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextDidEndEditingNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextDidEndEditingNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextDidEndEditingNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextDidEndEditingNotification").orElseThrow() }
private val NSTextDidEndEditingNotification_VH: VarHandle by lazy { NSTextDidEndEditingNotification_LAYOUT.varHandle() }

var NSTextDidEndEditingNotification: MemorySegment
    get() = NSTextDidEndEditingNotification_VH.get(NSTextDidEndEditingNotification_SEGMENT) as MemorySegment
    set(value) = NSTextDidEndEditingNotification_VH.set(NSTextDidEndEditingNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextDidChangeNotification").orElseThrow() }
private val NSTextDidChangeNotification_VH: VarHandle by lazy { NSTextDidChangeNotification_LAYOUT.varHandle() }

var NSTextDidChangeNotification: MemorySegment
    get() = NSTextDidChangeNotification_VH.get(NSTextDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSTextDidChangeNotification_VH.set(NSTextDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextMovementUserInfoKey (Void)*
 */
private val NSTextMovementUserInfoKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextMovementUserInfoKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextMovementUserInfoKey").orElseThrow() }
private val NSTextMovementUserInfoKey_VH: VarHandle by lazy { NSTextMovementUserInfoKey_LAYOUT.varHandle() }

var NSTextMovementUserInfoKey: MemorySegment
    get() = NSTextMovementUserInfoKey_VH.get(NSTextMovementUserInfoKey_SEGMENT) as MemorySegment
    set(value) = NSTextMovementUserInfoKey_VH.set(NSTextMovementUserInfoKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTabColumnTerminatorsAttributeName typedef NSTextTabOptionKey = typedef NSString = (Void)*
 */
private val NSTabColumnTerminatorsAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTabColumnTerminatorsAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTabColumnTerminatorsAttributeName").orElseThrow() }
private val NSTabColumnTerminatorsAttributeName_VH: VarHandle by lazy { NSTabColumnTerminatorsAttributeName_LAYOUT.varHandle() }

var NSTabColumnTerminatorsAttributeName: MemorySegment
    get() = NSTabColumnTerminatorsAttributeName_VH.get(NSTabColumnTerminatorsAttributeName_SEGMENT) as MemorySegment
    set(value) = NSTabColumnTerminatorsAttributeName_VH.set(NSTabColumnTerminatorsAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSControlTintDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSControlTintDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSControlTintDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSControlTintDidChangeNotification").orElseThrow() }
private val NSControlTintDidChangeNotification_VH: VarHandle by lazy { NSControlTintDidChangeNotification_LAYOUT.varHandle() }

var NSControlTintDidChangeNotification: MemorySegment
    get() = NSControlTintDidChangeNotification_VH.get(NSControlTintDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSControlTintDidChangeNotification_VH.set(NSControlTintDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMenuItemImportFromDeviceIdentifier typedef const NSUserInterfaceItemIdentifier = (Void)*
 */
private val NSMenuItemImportFromDeviceIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMenuItemImportFromDeviceIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMenuItemImportFromDeviceIdentifier").orElseThrow() }
private val NSMenuItemImportFromDeviceIdentifier_VH: VarHandle by lazy { NSMenuItemImportFromDeviceIdentifier_LAYOUT.varHandle() }

var NSMenuItemImportFromDeviceIdentifier: MemorySegment
    get() = NSMenuItemImportFromDeviceIdentifier_VH.get(NSMenuItemImportFromDeviceIdentifier_SEGMENT) as MemorySegment
    set(value) = NSMenuItemImportFromDeviceIdentifier_VH.set(NSMenuItemImportFromDeviceIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSMenuWillSendActionNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSMenuWillSendActionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMenuWillSendActionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMenuWillSendActionNotification").orElseThrow() }
private val NSMenuWillSendActionNotification_VH: VarHandle by lazy { NSMenuWillSendActionNotification_LAYOUT.varHandle() }

var NSMenuWillSendActionNotification: MemorySegment
    get() = NSMenuWillSendActionNotification_VH.get(NSMenuWillSendActionNotification_SEGMENT) as MemorySegment
    set(value) = NSMenuWillSendActionNotification_VH.set(NSMenuWillSendActionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMenuDidSendActionNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSMenuDidSendActionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMenuDidSendActionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMenuDidSendActionNotification").orElseThrow() }
private val NSMenuDidSendActionNotification_VH: VarHandle by lazy { NSMenuDidSendActionNotification_LAYOUT.varHandle() }

var NSMenuDidSendActionNotification: MemorySegment
    get() = NSMenuDidSendActionNotification_VH.get(NSMenuDidSendActionNotification_SEGMENT) as MemorySegment
    set(value) = NSMenuDidSendActionNotification_VH.set(NSMenuDidSendActionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMenuDidAddItemNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSMenuDidAddItemNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMenuDidAddItemNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMenuDidAddItemNotification").orElseThrow() }
private val NSMenuDidAddItemNotification_VH: VarHandle by lazy { NSMenuDidAddItemNotification_LAYOUT.varHandle() }

var NSMenuDidAddItemNotification: MemorySegment
    get() = NSMenuDidAddItemNotification_VH.get(NSMenuDidAddItemNotification_SEGMENT) as MemorySegment
    set(value) = NSMenuDidAddItemNotification_VH.set(NSMenuDidAddItemNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMenuDidRemoveItemNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSMenuDidRemoveItemNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMenuDidRemoveItemNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMenuDidRemoveItemNotification").orElseThrow() }
private val NSMenuDidRemoveItemNotification_VH: VarHandle by lazy { NSMenuDidRemoveItemNotification_LAYOUT.varHandle() }

var NSMenuDidRemoveItemNotification: MemorySegment
    get() = NSMenuDidRemoveItemNotification_VH.get(NSMenuDidRemoveItemNotification_SEGMENT) as MemorySegment
    set(value) = NSMenuDidRemoveItemNotification_VH.set(NSMenuDidRemoveItemNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMenuDidChangeItemNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSMenuDidChangeItemNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMenuDidChangeItemNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMenuDidChangeItemNotification").orElseThrow() }
private val NSMenuDidChangeItemNotification_VH: VarHandle by lazy { NSMenuDidChangeItemNotification_LAYOUT.varHandle() }

var NSMenuDidChangeItemNotification: MemorySegment
    get() = NSMenuDidChangeItemNotification_VH.get(NSMenuDidChangeItemNotification_SEGMENT) as MemorySegment
    set(value) = NSMenuDidChangeItemNotification_VH.set(NSMenuDidChangeItemNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMenuDidBeginTrackingNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSMenuDidBeginTrackingNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMenuDidBeginTrackingNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMenuDidBeginTrackingNotification").orElseThrow() }
private val NSMenuDidBeginTrackingNotification_VH: VarHandle by lazy { NSMenuDidBeginTrackingNotification_LAYOUT.varHandle() }

var NSMenuDidBeginTrackingNotification: MemorySegment
    get() = NSMenuDidBeginTrackingNotification_VH.get(NSMenuDidBeginTrackingNotification_SEGMENT) as MemorySegment
    set(value) = NSMenuDidBeginTrackingNotification_VH.set(NSMenuDidBeginTrackingNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSMenuDidEndTrackingNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSMenuDidEndTrackingNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMenuDidEndTrackingNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMenuDidEndTrackingNotification").orElseThrow() }
private val NSMenuDidEndTrackingNotification_VH: VarHandle by lazy { NSMenuDidEndTrackingNotification_LAYOUT.varHandle() }

var NSMenuDidEndTrackingNotification: MemorySegment
    get() = NSMenuDidEndTrackingNotification_VH.get(NSMenuDidEndTrackingNotification_SEGMENT) as MemorySegment
    set(value) = NSMenuDidEndTrackingNotification_VH.set(NSMenuDidEndTrackingNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPaperName typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintPaperName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPaperName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPaperName").orElseThrow() }
private val NSPrintPaperName_VH: VarHandle by lazy { NSPrintPaperName_LAYOUT.varHandle() }

var NSPrintPaperName: MemorySegment
    get() = NSPrintPaperName_VH.get(NSPrintPaperName_SEGMENT) as MemorySegment
    set(value) = NSPrintPaperName_VH.set(NSPrintPaperName_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPaperSize typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintPaperSize_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPaperSize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPaperSize").orElseThrow() }
private val NSPrintPaperSize_VH: VarHandle by lazy { NSPrintPaperSize_LAYOUT.varHandle() }

var NSPrintPaperSize: MemorySegment
    get() = NSPrintPaperSize_VH.get(NSPrintPaperSize_SEGMENT) as MemorySegment
    set(value) = NSPrintPaperSize_VH.set(NSPrintPaperSize_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintOrientation typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintOrientation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintOrientation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintOrientation").orElseThrow() }
private val NSPrintOrientation_VH: VarHandle by lazy { NSPrintOrientation_LAYOUT.varHandle() }

var NSPrintOrientation: MemorySegment
    get() = NSPrintOrientation_VH.get(NSPrintOrientation_SEGMENT) as MemorySegment
    set(value) = NSPrintOrientation_VH.set(NSPrintOrientation_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintScalingFactor typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintScalingFactor_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintScalingFactor_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintScalingFactor").orElseThrow() }
private val NSPrintScalingFactor_VH: VarHandle by lazy { NSPrintScalingFactor_LAYOUT.varHandle() }

var NSPrintScalingFactor: MemorySegment
    get() = NSPrintScalingFactor_VH.get(NSPrintScalingFactor_SEGMENT) as MemorySegment
    set(value) = NSPrintScalingFactor_VH.set(NSPrintScalingFactor_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintLeftMargin typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintLeftMargin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintLeftMargin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintLeftMargin").orElseThrow() }
private val NSPrintLeftMargin_VH: VarHandle by lazy { NSPrintLeftMargin_LAYOUT.varHandle() }

var NSPrintLeftMargin: MemorySegment
    get() = NSPrintLeftMargin_VH.get(NSPrintLeftMargin_SEGMENT) as MemorySegment
    set(value) = NSPrintLeftMargin_VH.set(NSPrintLeftMargin_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintRightMargin typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintRightMargin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintRightMargin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintRightMargin").orElseThrow() }
private val NSPrintRightMargin_VH: VarHandle by lazy { NSPrintRightMargin_LAYOUT.varHandle() }

var NSPrintRightMargin: MemorySegment
    get() = NSPrintRightMargin_VH.get(NSPrintRightMargin_SEGMENT) as MemorySegment
    set(value) = NSPrintRightMargin_VH.set(NSPrintRightMargin_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintTopMargin typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintTopMargin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintTopMargin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintTopMargin").orElseThrow() }
private val NSPrintTopMargin_VH: VarHandle by lazy { NSPrintTopMargin_LAYOUT.varHandle() }

var NSPrintTopMargin: MemorySegment
    get() = NSPrintTopMargin_VH.get(NSPrintTopMargin_SEGMENT) as MemorySegment
    set(value) = NSPrintTopMargin_VH.set(NSPrintTopMargin_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintBottomMargin typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintBottomMargin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintBottomMargin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintBottomMargin").orElseThrow() }
private val NSPrintBottomMargin_VH: VarHandle by lazy { NSPrintBottomMargin_LAYOUT.varHandle() }

var NSPrintBottomMargin: MemorySegment
    get() = NSPrintBottomMargin_VH.get(NSPrintBottomMargin_SEGMENT) as MemorySegment
    set(value) = NSPrintBottomMargin_VH.set(NSPrintBottomMargin_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintHorizontallyCentered typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintHorizontallyCentered_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintHorizontallyCentered_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintHorizontallyCentered").orElseThrow() }
private val NSPrintHorizontallyCentered_VH: VarHandle by lazy { NSPrintHorizontallyCentered_LAYOUT.varHandle() }

var NSPrintHorizontallyCentered: MemorySegment
    get() = NSPrintHorizontallyCentered_VH.get(NSPrintHorizontallyCentered_SEGMENT) as MemorySegment
    set(value) = NSPrintHorizontallyCentered_VH.set(NSPrintHorizontallyCentered_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintVerticallyCentered typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintVerticallyCentered_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintVerticallyCentered_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintVerticallyCentered").orElseThrow() }
private val NSPrintVerticallyCentered_VH: VarHandle by lazy { NSPrintVerticallyCentered_LAYOUT.varHandle() }

var NSPrintVerticallyCentered: MemorySegment
    get() = NSPrintVerticallyCentered_VH.get(NSPrintVerticallyCentered_SEGMENT) as MemorySegment
    set(value) = NSPrintVerticallyCentered_VH.set(NSPrintVerticallyCentered_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintHorizontalPagination typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintHorizontalPagination_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintHorizontalPagination_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintHorizontalPagination").orElseThrow() }
private val NSPrintHorizontalPagination_VH: VarHandle by lazy { NSPrintHorizontalPagination_LAYOUT.varHandle() }

var NSPrintHorizontalPagination: MemorySegment
    get() = NSPrintHorizontalPagination_VH.get(NSPrintHorizontalPagination_SEGMENT) as MemorySegment
    set(value) = NSPrintHorizontalPagination_VH.set(NSPrintHorizontalPagination_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintVerticalPagination typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintVerticalPagination_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintVerticalPagination_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintVerticalPagination").orElseThrow() }
private val NSPrintVerticalPagination_VH: VarHandle by lazy { NSPrintVerticalPagination_LAYOUT.varHandle() }

var NSPrintVerticalPagination: MemorySegment
    get() = NSPrintVerticalPagination_VH.get(NSPrintVerticalPagination_SEGMENT) as MemorySegment
    set(value) = NSPrintVerticalPagination_VH.set(NSPrintVerticalPagination_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPrinter typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintPrinter_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPrinter_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPrinter").orElseThrow() }
private val NSPrintPrinter_VH: VarHandle by lazy { NSPrintPrinter_LAYOUT.varHandle() }

var NSPrintPrinter: MemorySegment
    get() = NSPrintPrinter_VH.get(NSPrintPrinter_SEGMENT) as MemorySegment
    set(value) = NSPrintPrinter_VH.set(NSPrintPrinter_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintCopies typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintCopies_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintCopies_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintCopies").orElseThrow() }
private val NSPrintCopies_VH: VarHandle by lazy { NSPrintCopies_LAYOUT.varHandle() }

var NSPrintCopies: MemorySegment
    get() = NSPrintCopies_VH.get(NSPrintCopies_SEGMENT) as MemorySegment
    set(value) = NSPrintCopies_VH.set(NSPrintCopies_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintAllPages typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintAllPages_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintAllPages_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintAllPages").orElseThrow() }
private val NSPrintAllPages_VH: VarHandle by lazy { NSPrintAllPages_LAYOUT.varHandle() }

var NSPrintAllPages: MemorySegment
    get() = NSPrintAllPages_VH.get(NSPrintAllPages_SEGMENT) as MemorySegment
    set(value) = NSPrintAllPages_VH.set(NSPrintAllPages_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintFirstPage typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintFirstPage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintFirstPage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintFirstPage").orElseThrow() }
private val NSPrintFirstPage_VH: VarHandle by lazy { NSPrintFirstPage_LAYOUT.varHandle() }

var NSPrintFirstPage: MemorySegment
    get() = NSPrintFirstPage_VH.get(NSPrintFirstPage_SEGMENT) as MemorySegment
    set(value) = NSPrintFirstPage_VH.set(NSPrintFirstPage_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintLastPage typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintLastPage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintLastPage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintLastPage").orElseThrow() }
private val NSPrintLastPage_VH: VarHandle by lazy { NSPrintLastPage_LAYOUT.varHandle() }

var NSPrintLastPage: MemorySegment
    get() = NSPrintLastPage_VH.get(NSPrintLastPage_SEGMENT) as MemorySegment
    set(value) = NSPrintLastPage_VH.set(NSPrintLastPage_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintMustCollate typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintMustCollate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintMustCollate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintMustCollate").orElseThrow() }
private val NSPrintMustCollate_VH: VarHandle by lazy { NSPrintMustCollate_LAYOUT.varHandle() }

var NSPrintMustCollate: MemorySegment
    get() = NSPrintMustCollate_VH.get(NSPrintMustCollate_SEGMENT) as MemorySegment
    set(value) = NSPrintMustCollate_VH.set(NSPrintMustCollate_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintReversePageOrder typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintReversePageOrder_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintReversePageOrder_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintReversePageOrder").orElseThrow() }
private val NSPrintReversePageOrder_VH: VarHandle by lazy { NSPrintReversePageOrder_LAYOUT.varHandle() }

var NSPrintReversePageOrder: MemorySegment
    get() = NSPrintReversePageOrder_VH.get(NSPrintReversePageOrder_SEGMENT) as MemorySegment
    set(value) = NSPrintReversePageOrder_VH.set(NSPrintReversePageOrder_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintJobDisposition typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintJobDisposition_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintJobDisposition_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintJobDisposition").orElseThrow() }
private val NSPrintJobDisposition_VH: VarHandle by lazy { NSPrintJobDisposition_LAYOUT.varHandle() }

var NSPrintJobDisposition: MemorySegment
    get() = NSPrintJobDisposition_VH.get(NSPrintJobDisposition_SEGMENT) as MemorySegment
    set(value) = NSPrintJobDisposition_VH.set(NSPrintJobDisposition_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPagesAcross typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintPagesAcross_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPagesAcross_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPagesAcross").orElseThrow() }
private val NSPrintPagesAcross_VH: VarHandle by lazy { NSPrintPagesAcross_LAYOUT.varHandle() }

var NSPrintPagesAcross: MemorySegment
    get() = NSPrintPagesAcross_VH.get(NSPrintPagesAcross_SEGMENT) as MemorySegment
    set(value) = NSPrintPagesAcross_VH.set(NSPrintPagesAcross_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPagesDown typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintPagesDown_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPagesDown_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPagesDown").orElseThrow() }
private val NSPrintPagesDown_VH: VarHandle by lazy { NSPrintPagesDown_LAYOUT.varHandle() }

var NSPrintPagesDown: MemorySegment
    get() = NSPrintPagesDown_VH.get(NSPrintPagesDown_SEGMENT) as MemorySegment
    set(value) = NSPrintPagesDown_VH.set(NSPrintPagesDown_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintTime typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintTime_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintTime_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintTime").orElseThrow() }
private val NSPrintTime_VH: VarHandle by lazy { NSPrintTime_LAYOUT.varHandle() }

var NSPrintTime: MemorySegment
    get() = NSPrintTime_VH.get(NSPrintTime_SEGMENT) as MemorySegment
    set(value) = NSPrintTime_VH.set(NSPrintTime_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintDetailedErrorReporting typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintDetailedErrorReporting_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintDetailedErrorReporting_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintDetailedErrorReporting").orElseThrow() }
private val NSPrintDetailedErrorReporting_VH: VarHandle by lazy { NSPrintDetailedErrorReporting_LAYOUT.varHandle() }

var NSPrintDetailedErrorReporting: MemorySegment
    get() = NSPrintDetailedErrorReporting_VH.get(NSPrintDetailedErrorReporting_SEGMENT) as MemorySegment
    set(value) = NSPrintDetailedErrorReporting_VH.set(NSPrintDetailedErrorReporting_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintFaxNumber typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintFaxNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintFaxNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintFaxNumber").orElseThrow() }
private val NSPrintFaxNumber_VH: VarHandle by lazy { NSPrintFaxNumber_LAYOUT.varHandle() }

var NSPrintFaxNumber: MemorySegment
    get() = NSPrintFaxNumber_VH.get(NSPrintFaxNumber_SEGMENT) as MemorySegment
    set(value) = NSPrintFaxNumber_VH.set(NSPrintFaxNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPrinterName typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintPrinterName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPrinterName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPrinterName").orElseThrow() }
private val NSPrintPrinterName_VH: VarHandle by lazy { NSPrintPrinterName_LAYOUT.varHandle() }

var NSPrintPrinterName: MemorySegment
    get() = NSPrintPrinterName_VH.get(NSPrintPrinterName_SEGMENT) as MemorySegment
    set(value) = NSPrintPrinterName_VH.set(NSPrintPrinterName_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintSelectionOnly typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintSelectionOnly_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintSelectionOnly_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintSelectionOnly").orElseThrow() }
private val NSPrintSelectionOnly_VH: VarHandle by lazy { NSPrintSelectionOnly_LAYOUT.varHandle() }

var NSPrintSelectionOnly: MemorySegment
    get() = NSPrintSelectionOnly_VH.get(NSPrintSelectionOnly_SEGMENT) as MemorySegment
    set(value) = NSPrintSelectionOnly_VH.set(NSPrintSelectionOnly_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintJobSavingURL typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintJobSavingURL_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintJobSavingURL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintJobSavingURL").orElseThrow() }
private val NSPrintJobSavingURL_VH: VarHandle by lazy { NSPrintJobSavingURL_LAYOUT.varHandle() }

var NSPrintJobSavingURL: MemorySegment
    get() = NSPrintJobSavingURL_VH.get(NSPrintJobSavingURL_SEGMENT) as MemorySegment
    set(value) = NSPrintJobSavingURL_VH.set(NSPrintJobSavingURL_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintJobSavingFileNameExtensionHidden typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintJobSavingFileNameExtensionHidden_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintJobSavingFileNameExtensionHidden_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintJobSavingFileNameExtensionHidden").orElseThrow() }
private val NSPrintJobSavingFileNameExtensionHidden_VH: VarHandle by lazy { NSPrintJobSavingFileNameExtensionHidden_LAYOUT.varHandle() }

var NSPrintJobSavingFileNameExtensionHidden: MemorySegment
    get() = NSPrintJobSavingFileNameExtensionHidden_VH.get(NSPrintJobSavingFileNameExtensionHidden_SEGMENT) as MemorySegment
    set(value) = NSPrintJobSavingFileNameExtensionHidden_VH.set(NSPrintJobSavingFileNameExtensionHidden_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintHeaderAndFooter typedef const NSPrintInfoAttributeKey = (Void)*
 */
private val NSPrintHeaderAndFooter_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintHeaderAndFooter_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintHeaderAndFooter").orElseThrow() }
private val NSPrintHeaderAndFooter_VH: VarHandle by lazy { NSPrintHeaderAndFooter_LAYOUT.varHandle() }

var NSPrintHeaderAndFooter: MemorySegment
    get() = NSPrintHeaderAndFooter_VH.get(NSPrintHeaderAndFooter_SEGMENT) as MemorySegment
    set(value) = NSPrintHeaderAndFooter_VH.set(NSPrintHeaderAndFooter_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintSpoolJob typedef const NSPrintJobDispositionValue = (Void)*
 */
private val NSPrintSpoolJob_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintSpoolJob_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintSpoolJob").orElseThrow() }
private val NSPrintSpoolJob_VH: VarHandle by lazy { NSPrintSpoolJob_LAYOUT.varHandle() }

var NSPrintSpoolJob: MemorySegment
    get() = NSPrintSpoolJob_VH.get(NSPrintSpoolJob_SEGMENT) as MemorySegment
    set(value) = NSPrintSpoolJob_VH.set(NSPrintSpoolJob_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPreviewJob typedef const NSPrintJobDispositionValue = (Void)*
 */
private val NSPrintPreviewJob_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPreviewJob_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPreviewJob").orElseThrow() }
private val NSPrintPreviewJob_VH: VarHandle by lazy { NSPrintPreviewJob_LAYOUT.varHandle() }

var NSPrintPreviewJob: MemorySegment
    get() = NSPrintPreviewJob_VH.get(NSPrintPreviewJob_SEGMENT) as MemorySegment
    set(value) = NSPrintPreviewJob_VH.set(NSPrintPreviewJob_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintSaveJob typedef const NSPrintJobDispositionValue = (Void)*
 */
private val NSPrintSaveJob_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintSaveJob_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintSaveJob").orElseThrow() }
private val NSPrintSaveJob_VH: VarHandle by lazy { NSPrintSaveJob_LAYOUT.varHandle() }

var NSPrintSaveJob: MemorySegment
    get() = NSPrintSaveJob_VH.get(NSPrintSaveJob_SEGMENT) as MemorySegment
    set(value) = NSPrintSaveJob_VH.set(NSPrintSaveJob_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintCancelJob typedef const NSPrintJobDispositionValue = (Void)*
 */
private val NSPrintCancelJob_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintCancelJob_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintCancelJob").orElseThrow() }
private val NSPrintCancelJob_VH: VarHandle by lazy { NSPrintCancelJob_LAYOUT.varHandle() }

var NSPrintCancelJob: MemorySegment
    get() = NSPrintCancelJob_VH.get(NSPrintCancelJob_SEGMENT) as MemorySegment
    set(value) = NSPrintCancelJob_VH.set(NSPrintCancelJob_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintFormName (Void)*
 */
private val NSPrintFormName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintFormName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintFormName").orElseThrow() }
private val NSPrintFormName_VH: VarHandle by lazy { NSPrintFormName_LAYOUT.varHandle() }

var NSPrintFormName: MemorySegment
    get() = NSPrintFormName_VH.get(NSPrintFormName_SEGMENT) as MemorySegment
    set(value) = NSPrintFormName_VH.set(NSPrintFormName_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintJobFeatures (Void)*
 */
private val NSPrintJobFeatures_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintJobFeatures_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintJobFeatures").orElseThrow() }
private val NSPrintJobFeatures_VH: VarHandle by lazy { NSPrintJobFeatures_LAYOUT.varHandle() }

var NSPrintJobFeatures: MemorySegment
    get() = NSPrintJobFeatures_VH.get(NSPrintJobFeatures_SEGMENT) as MemorySegment
    set(value) = NSPrintJobFeatures_VH.set(NSPrintJobFeatures_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintManualFeed (Void)*
 */
private val NSPrintManualFeed_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintManualFeed_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintManualFeed").orElseThrow() }
private val NSPrintManualFeed_VH: VarHandle by lazy { NSPrintManualFeed_LAYOUT.varHandle() }

var NSPrintManualFeed: MemorySegment
    get() = NSPrintManualFeed_VH.get(NSPrintManualFeed_SEGMENT) as MemorySegment
    set(value) = NSPrintManualFeed_VH.set(NSPrintManualFeed_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPagesPerSheet (Void)*
 */
private val NSPrintPagesPerSheet_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPagesPerSheet_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPagesPerSheet").orElseThrow() }
private val NSPrintPagesPerSheet_VH: VarHandle by lazy { NSPrintPagesPerSheet_LAYOUT.varHandle() }

var NSPrintPagesPerSheet: MemorySegment
    get() = NSPrintPagesPerSheet_VH.get(NSPrintPagesPerSheet_SEGMENT) as MemorySegment
    set(value) = NSPrintPagesPerSheet_VH.set(NSPrintPagesPerSheet_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPaperFeed (Void)*
 */
private val NSPrintPaperFeed_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPaperFeed_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPaperFeed").orElseThrow() }
private val NSPrintPaperFeed_VH: VarHandle by lazy { NSPrintPaperFeed_LAYOUT.varHandle() }

var NSPrintPaperFeed: MemorySegment
    get() = NSPrintPaperFeed_VH.get(NSPrintPaperFeed_SEGMENT) as MemorySegment
    set(value) = NSPrintPaperFeed_VH.set(NSPrintPaperFeed_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintSavePath (Void)*
 */
private val NSPrintSavePath_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintSavePath_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintSavePath").orElseThrow() }
private val NSPrintSavePath_VH: VarHandle by lazy { NSPrintSavePath_LAYOUT.varHandle() }

var NSPrintSavePath: MemorySegment
    get() = NSPrintSavePath_VH.get(NSPrintSavePath_SEGMENT) as MemorySegment
    set(value) = NSPrintSavePath_VH.set(NSPrintSavePath_SEGMENT, value)

/**
 * {@snippet lang=c : NSMultipleValuesMarker typedef id = (Void)*
 */
private val NSMultipleValuesMarker_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMultipleValuesMarker_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMultipleValuesMarker").orElseThrow() }
private val NSMultipleValuesMarker_VH: VarHandle by lazy { NSMultipleValuesMarker_LAYOUT.varHandle() }

var NSMultipleValuesMarker: MemorySegment
    get() = NSMultipleValuesMarker_VH.get(NSMultipleValuesMarker_SEGMENT) as MemorySegment
    set(value) = NSMultipleValuesMarker_VH.set(NSMultipleValuesMarker_SEGMENT, value)

/**
 * {@snippet lang=c : NSNoSelectionMarker typedef id = (Void)*
 */
private val NSNoSelectionMarker_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNoSelectionMarker_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNoSelectionMarker").orElseThrow() }
private val NSNoSelectionMarker_VH: VarHandle by lazy { NSNoSelectionMarker_LAYOUT.varHandle() }

var NSNoSelectionMarker: MemorySegment
    get() = NSNoSelectionMarker_VH.get(NSNoSelectionMarker_SEGMENT) as MemorySegment
    set(value) = NSNoSelectionMarker_VH.set(NSNoSelectionMarker_SEGMENT, value)

/**
 * {@snippet lang=c : NSNotApplicableMarker typedef id = (Void)*
 */
private val NSNotApplicableMarker_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNotApplicableMarker_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNotApplicableMarker").orElseThrow() }
private val NSNotApplicableMarker_VH: VarHandle by lazy { NSNotApplicableMarker_LAYOUT.varHandle() }

var NSNotApplicableMarker: MemorySegment
    get() = NSNotApplicableMarker_VH.get(NSNotApplicableMarker_SEGMENT) as MemorySegment
    set(value) = NSNotApplicableMarker_VH.set(NSNotApplicableMarker_SEGMENT, value)

/**
 * {@snippet lang=c : NSIsControllerMarker typedef BOOL = Bool(typedef id = (Void)*)
 */
private val NSIsControllerMarker_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val NSIsControllerMarker_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSIsControllerMarker").orElseThrow()
private val NSIsControllerMarker_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSIsControllerMarker_ADDR, NSIsControllerMarker_DESC)

fun NSIsControllerMarker(arg0: MemorySegment): Boolean {
    try {
        return NSIsControllerMarker_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSObservedObjectKey typedef NSBindingInfoKey = typedef NSString = (Void)*
 */
private val NSObservedObjectKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSObservedObjectKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSObservedObjectKey").orElseThrow() }
private val NSObservedObjectKey_VH: VarHandle by lazy { NSObservedObjectKey_LAYOUT.varHandle() }

var NSObservedObjectKey: MemorySegment
    get() = NSObservedObjectKey_VH.get(NSObservedObjectKey_SEGMENT) as MemorySegment
    set(value) = NSObservedObjectKey_VH.set(NSObservedObjectKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSObservedKeyPathKey typedef NSBindingInfoKey = typedef NSString = (Void)*
 */
private val NSObservedKeyPathKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSObservedKeyPathKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSObservedKeyPathKey").orElseThrow() }
private val NSObservedKeyPathKey_VH: VarHandle by lazy { NSObservedKeyPathKey_LAYOUT.varHandle() }

var NSObservedKeyPathKey: MemorySegment
    get() = NSObservedKeyPathKey_VH.get(NSObservedKeyPathKey_SEGMENT) as MemorySegment
    set(value) = NSObservedKeyPathKey_VH.set(NSObservedKeyPathKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSOptionsKey typedef NSBindingInfoKey = typedef NSString = (Void)*
 */
private val NSOptionsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOptionsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOptionsKey").orElseThrow() }
private val NSOptionsKey_VH: VarHandle by lazy { NSOptionsKey_LAYOUT.varHandle() }

var NSOptionsKey: MemorySegment
    get() = NSOptionsKey_VH.get(NSOptionsKey_SEGMENT) as MemorySegment
    set(value) = NSOptionsKey_VH.set(NSOptionsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAlignmentBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSAlignmentBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAlignmentBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAlignmentBinding").orElseThrow() }
private val NSAlignmentBinding_VH: VarHandle by lazy { NSAlignmentBinding_LAYOUT.varHandle() }

var NSAlignmentBinding: MemorySegment
    get() = NSAlignmentBinding_VH.get(NSAlignmentBinding_SEGMENT) as MemorySegment
    set(value) = NSAlignmentBinding_VH.set(NSAlignmentBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSAlternateImageBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSAlternateImageBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAlternateImageBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAlternateImageBinding").orElseThrow() }
private val NSAlternateImageBinding_VH: VarHandle by lazy { NSAlternateImageBinding_LAYOUT.varHandle() }

var NSAlternateImageBinding: MemorySegment
    get() = NSAlternateImageBinding_VH.get(NSAlternateImageBinding_SEGMENT) as MemorySegment
    set(value) = NSAlternateImageBinding_VH.set(NSAlternateImageBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSAlternateTitleBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSAlternateTitleBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAlternateTitleBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAlternateTitleBinding").orElseThrow() }
private val NSAlternateTitleBinding_VH: VarHandle by lazy { NSAlternateTitleBinding_LAYOUT.varHandle() }

var NSAlternateTitleBinding: MemorySegment
    get() = NSAlternateTitleBinding_VH.get(NSAlternateTitleBinding_SEGMENT) as MemorySegment
    set(value) = NSAlternateTitleBinding_VH.set(NSAlternateTitleBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSAnimateBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSAnimateBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAnimateBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAnimateBinding").orElseThrow() }
private val NSAnimateBinding_VH: VarHandle by lazy { NSAnimateBinding_LAYOUT.varHandle() }

var NSAnimateBinding: MemorySegment
    get() = NSAnimateBinding_VH.get(NSAnimateBinding_SEGMENT) as MemorySegment
    set(value) = NSAnimateBinding_VH.set(NSAnimateBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSAnimationDelayBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSAnimationDelayBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAnimationDelayBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAnimationDelayBinding").orElseThrow() }
private val NSAnimationDelayBinding_VH: VarHandle by lazy { NSAnimationDelayBinding_LAYOUT.varHandle() }

var NSAnimationDelayBinding: MemorySegment
    get() = NSAnimationDelayBinding_VH.get(NSAnimationDelayBinding_SEGMENT) as MemorySegment
    set(value) = NSAnimationDelayBinding_VH.set(NSAnimationDelayBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSArgumentBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSArgumentBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSArgumentBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSArgumentBinding").orElseThrow() }
private val NSArgumentBinding_VH: VarHandle by lazy { NSArgumentBinding_LAYOUT.varHandle() }

var NSArgumentBinding: MemorySegment
    get() = NSArgumentBinding_VH.get(NSArgumentBinding_SEGMENT) as MemorySegment
    set(value) = NSArgumentBinding_VH.set(NSArgumentBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSAttributedStringBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSAttributedStringBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAttributedStringBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAttributedStringBinding").orElseThrow() }
private val NSAttributedStringBinding_VH: VarHandle by lazy { NSAttributedStringBinding_LAYOUT.varHandle() }

var NSAttributedStringBinding: MemorySegment
    get() = NSAttributedStringBinding_VH.get(NSAttributedStringBinding_SEGMENT) as MemorySegment
    set(value) = NSAttributedStringBinding_VH.set(NSAttributedStringBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentArrayBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentArrayBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentArrayBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentArrayBinding").orElseThrow() }
private val NSContentArrayBinding_VH: VarHandle by lazy { NSContentArrayBinding_LAYOUT.varHandle() }

var NSContentArrayBinding: MemorySegment
    get() = NSContentArrayBinding_VH.get(NSContentArrayBinding_SEGMENT) as MemorySegment
    set(value) = NSContentArrayBinding_VH.set(NSContentArrayBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentArrayForMultipleSelectionBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentArrayForMultipleSelectionBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentArrayForMultipleSelectionBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentArrayForMultipleSelectionBinding").orElseThrow() }
private val NSContentArrayForMultipleSelectionBinding_VH: VarHandle by lazy { NSContentArrayForMultipleSelectionBinding_LAYOUT.varHandle() }

var NSContentArrayForMultipleSelectionBinding: MemorySegment
    get() = NSContentArrayForMultipleSelectionBinding_VH.get(NSContentArrayForMultipleSelectionBinding_SEGMENT) as MemorySegment
    set(value) = NSContentArrayForMultipleSelectionBinding_VH.set(NSContentArrayForMultipleSelectionBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentBinding").orElseThrow() }
private val NSContentBinding_VH: VarHandle by lazy { NSContentBinding_LAYOUT.varHandle() }

var NSContentBinding: MemorySegment
    get() = NSContentBinding_VH.get(NSContentBinding_SEGMENT) as MemorySegment
    set(value) = NSContentBinding_VH.set(NSContentBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentDictionaryBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentDictionaryBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentDictionaryBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentDictionaryBinding").orElseThrow() }
private val NSContentDictionaryBinding_VH: VarHandle by lazy { NSContentDictionaryBinding_LAYOUT.varHandle() }

var NSContentDictionaryBinding: MemorySegment
    get() = NSContentDictionaryBinding_VH.get(NSContentDictionaryBinding_SEGMENT) as MemorySegment
    set(value) = NSContentDictionaryBinding_VH.set(NSContentDictionaryBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentHeightBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentHeightBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentHeightBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentHeightBinding").orElseThrow() }
private val NSContentHeightBinding_VH: VarHandle by lazy { NSContentHeightBinding_LAYOUT.varHandle() }

var NSContentHeightBinding: MemorySegment
    get() = NSContentHeightBinding_VH.get(NSContentHeightBinding_SEGMENT) as MemorySegment
    set(value) = NSContentHeightBinding_VH.set(NSContentHeightBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentObjectBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentObjectBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentObjectBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentObjectBinding").orElseThrow() }
private val NSContentObjectBinding_VH: VarHandle by lazy { NSContentObjectBinding_LAYOUT.varHandle() }

var NSContentObjectBinding: MemorySegment
    get() = NSContentObjectBinding_VH.get(NSContentObjectBinding_SEGMENT) as MemorySegment
    set(value) = NSContentObjectBinding_VH.set(NSContentObjectBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentObjectsBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentObjectsBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentObjectsBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentObjectsBinding").orElseThrow() }
private val NSContentObjectsBinding_VH: VarHandle by lazy { NSContentObjectsBinding_LAYOUT.varHandle() }

var NSContentObjectsBinding: MemorySegment
    get() = NSContentObjectsBinding_VH.get(NSContentObjectsBinding_SEGMENT) as MemorySegment
    set(value) = NSContentObjectsBinding_VH.set(NSContentObjectsBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentSetBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentSetBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentSetBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentSetBinding").orElseThrow() }
private val NSContentSetBinding_VH: VarHandle by lazy { NSContentSetBinding_LAYOUT.varHandle() }

var NSContentSetBinding: MemorySegment
    get() = NSContentSetBinding_VH.get(NSContentSetBinding_SEGMENT) as MemorySegment
    set(value) = NSContentSetBinding_VH.set(NSContentSetBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentValuesBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentValuesBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentValuesBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentValuesBinding").orElseThrow() }
private val NSContentValuesBinding_VH: VarHandle by lazy { NSContentValuesBinding_LAYOUT.varHandle() }

var NSContentValuesBinding: MemorySegment
    get() = NSContentValuesBinding_VH.get(NSContentValuesBinding_SEGMENT) as MemorySegment
    set(value) = NSContentValuesBinding_VH.set(NSContentValuesBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSContentWidthBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSContentWidthBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSContentWidthBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSContentWidthBinding").orElseThrow() }
private val NSContentWidthBinding_VH: VarHandle by lazy { NSContentWidthBinding_LAYOUT.varHandle() }

var NSContentWidthBinding: MemorySegment
    get() = NSContentWidthBinding_VH.get(NSContentWidthBinding_SEGMENT) as MemorySegment
    set(value) = NSContentWidthBinding_VH.set(NSContentWidthBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSCriticalValueBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSCriticalValueBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCriticalValueBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCriticalValueBinding").orElseThrow() }
private val NSCriticalValueBinding_VH: VarHandle by lazy { NSCriticalValueBinding_LAYOUT.varHandle() }

var NSCriticalValueBinding: MemorySegment
    get() = NSCriticalValueBinding_VH.get(NSCriticalValueBinding_SEGMENT) as MemorySegment
    set(value) = NSCriticalValueBinding_VH.set(NSCriticalValueBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSDataBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSDataBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDataBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDataBinding").orElseThrow() }
private val NSDataBinding_VH: VarHandle by lazy { NSDataBinding_LAYOUT.varHandle() }

var NSDataBinding: MemorySegment
    get() = NSDataBinding_VH.get(NSDataBinding_SEGMENT) as MemorySegment
    set(value) = NSDataBinding_VH.set(NSDataBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSDisplayPatternTitleBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSDisplayPatternTitleBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDisplayPatternTitleBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDisplayPatternTitleBinding").orElseThrow() }
private val NSDisplayPatternTitleBinding_VH: VarHandle by lazy { NSDisplayPatternTitleBinding_LAYOUT.varHandle() }

var NSDisplayPatternTitleBinding: MemorySegment
    get() = NSDisplayPatternTitleBinding_VH.get(NSDisplayPatternTitleBinding_SEGMENT) as MemorySegment
    set(value) = NSDisplayPatternTitleBinding_VH.set(NSDisplayPatternTitleBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSDisplayPatternValueBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSDisplayPatternValueBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDisplayPatternValueBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDisplayPatternValueBinding").orElseThrow() }
private val NSDisplayPatternValueBinding_VH: VarHandle by lazy { NSDisplayPatternValueBinding_LAYOUT.varHandle() }

var NSDisplayPatternValueBinding: MemorySegment
    get() = NSDisplayPatternValueBinding_VH.get(NSDisplayPatternValueBinding_SEGMENT) as MemorySegment
    set(value) = NSDisplayPatternValueBinding_VH.set(NSDisplayPatternValueBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSDocumentEditedBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSDocumentEditedBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDocumentEditedBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDocumentEditedBinding").orElseThrow() }
private val NSDocumentEditedBinding_VH: VarHandle by lazy { NSDocumentEditedBinding_LAYOUT.varHandle() }

var NSDocumentEditedBinding: MemorySegment
    get() = NSDocumentEditedBinding_VH.get(NSDocumentEditedBinding_SEGMENT) as MemorySegment
    set(value) = NSDocumentEditedBinding_VH.set(NSDocumentEditedBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSDoubleClickArgumentBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSDoubleClickArgumentBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDoubleClickArgumentBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDoubleClickArgumentBinding").orElseThrow() }
private val NSDoubleClickArgumentBinding_VH: VarHandle by lazy { NSDoubleClickArgumentBinding_LAYOUT.varHandle() }

var NSDoubleClickArgumentBinding: MemorySegment
    get() = NSDoubleClickArgumentBinding_VH.get(NSDoubleClickArgumentBinding_SEGMENT) as MemorySegment
    set(value) = NSDoubleClickArgumentBinding_VH.set(NSDoubleClickArgumentBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSDoubleClickTargetBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSDoubleClickTargetBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDoubleClickTargetBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDoubleClickTargetBinding").orElseThrow() }
private val NSDoubleClickTargetBinding_VH: VarHandle by lazy { NSDoubleClickTargetBinding_LAYOUT.varHandle() }

var NSDoubleClickTargetBinding: MemorySegment
    get() = NSDoubleClickTargetBinding_VH.get(NSDoubleClickTargetBinding_SEGMENT) as MemorySegment
    set(value) = NSDoubleClickTargetBinding_VH.set(NSDoubleClickTargetBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSEditableBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSEditableBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSEditableBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSEditableBinding").orElseThrow() }
private val NSEditableBinding_VH: VarHandle by lazy { NSEditableBinding_LAYOUT.varHandle() }

var NSEditableBinding: MemorySegment
    get() = NSEditableBinding_VH.get(NSEditableBinding_SEGMENT) as MemorySegment
    set(value) = NSEditableBinding_VH.set(NSEditableBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSEnabledBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSEnabledBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSEnabledBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSEnabledBinding").orElseThrow() }
private val NSEnabledBinding_VH: VarHandle by lazy { NSEnabledBinding_LAYOUT.varHandle() }

var NSEnabledBinding: MemorySegment
    get() = NSEnabledBinding_VH.get(NSEnabledBinding_SEGMENT) as MemorySegment
    set(value) = NSEnabledBinding_VH.set(NSEnabledBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSExcludedKeysBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSExcludedKeysBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExcludedKeysBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExcludedKeysBinding").orElseThrow() }
private val NSExcludedKeysBinding_VH: VarHandle by lazy { NSExcludedKeysBinding_LAYOUT.varHandle() }

var NSExcludedKeysBinding: MemorySegment
    get() = NSExcludedKeysBinding_VH.get(NSExcludedKeysBinding_SEGMENT) as MemorySegment
    set(value) = NSExcludedKeysBinding_VH.set(NSExcludedKeysBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSFilterPredicateBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSFilterPredicateBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFilterPredicateBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFilterPredicateBinding").orElseThrow() }
private val NSFilterPredicateBinding_VH: VarHandle by lazy { NSFilterPredicateBinding_LAYOUT.varHandle() }

var NSFilterPredicateBinding: MemorySegment
    get() = NSFilterPredicateBinding_VH.get(NSFilterPredicateBinding_SEGMENT) as MemorySegment
    set(value) = NSFilterPredicateBinding_VH.set(NSFilterPredicateBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSFontBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontBinding").orElseThrow() }
private val NSFontBinding_VH: VarHandle by lazy { NSFontBinding_LAYOUT.varHandle() }

var NSFontBinding: MemorySegment
    get() = NSFontBinding_VH.get(NSFontBinding_SEGMENT) as MemorySegment
    set(value) = NSFontBinding_VH.set(NSFontBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontBoldBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSFontBoldBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontBoldBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontBoldBinding").orElseThrow() }
private val NSFontBoldBinding_VH: VarHandle by lazy { NSFontBoldBinding_LAYOUT.varHandle() }

var NSFontBoldBinding: MemorySegment
    get() = NSFontBoldBinding_VH.get(NSFontBoldBinding_SEGMENT) as MemorySegment
    set(value) = NSFontBoldBinding_VH.set(NSFontBoldBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontFamilyNameBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSFontFamilyNameBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontFamilyNameBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontFamilyNameBinding").orElseThrow() }
private val NSFontFamilyNameBinding_VH: VarHandle by lazy { NSFontFamilyNameBinding_LAYOUT.varHandle() }

var NSFontFamilyNameBinding: MemorySegment
    get() = NSFontFamilyNameBinding_VH.get(NSFontFamilyNameBinding_SEGMENT) as MemorySegment
    set(value) = NSFontFamilyNameBinding_VH.set(NSFontFamilyNameBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontItalicBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSFontItalicBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontItalicBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontItalicBinding").orElseThrow() }
private val NSFontItalicBinding_VH: VarHandle by lazy { NSFontItalicBinding_LAYOUT.varHandle() }

var NSFontItalicBinding: MemorySegment
    get() = NSFontItalicBinding_VH.get(NSFontItalicBinding_SEGMENT) as MemorySegment
    set(value) = NSFontItalicBinding_VH.set(NSFontItalicBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontNameBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSFontNameBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontNameBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontNameBinding").orElseThrow() }
private val NSFontNameBinding_VH: VarHandle by lazy { NSFontNameBinding_LAYOUT.varHandle() }

var NSFontNameBinding: MemorySegment
    get() = NSFontNameBinding_VH.get(NSFontNameBinding_SEGMENT) as MemorySegment
    set(value) = NSFontNameBinding_VH.set(NSFontNameBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontSizeBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSFontSizeBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontSizeBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontSizeBinding").orElseThrow() }
private val NSFontSizeBinding_VH: VarHandle by lazy { NSFontSizeBinding_LAYOUT.varHandle() }

var NSFontSizeBinding: MemorySegment
    get() = NSFontSizeBinding_VH.get(NSFontSizeBinding_SEGMENT) as MemorySegment
    set(value) = NSFontSizeBinding_VH.set(NSFontSizeBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSHeaderTitleBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSHeaderTitleBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHeaderTitleBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHeaderTitleBinding").orElseThrow() }
private val NSHeaderTitleBinding_VH: VarHandle by lazy { NSHeaderTitleBinding_LAYOUT.varHandle() }

var NSHeaderTitleBinding: MemorySegment
    get() = NSHeaderTitleBinding_VH.get(NSHeaderTitleBinding_SEGMENT) as MemorySegment
    set(value) = NSHeaderTitleBinding_VH.set(NSHeaderTitleBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSHiddenBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSHiddenBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHiddenBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHiddenBinding").orElseThrow() }
private val NSHiddenBinding_VH: VarHandle by lazy { NSHiddenBinding_LAYOUT.varHandle() }

var NSHiddenBinding: MemorySegment
    get() = NSHiddenBinding_VH.get(NSHiddenBinding_SEGMENT) as MemorySegment
    set(value) = NSHiddenBinding_VH.set(NSHiddenBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSImageBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageBinding").orElseThrow() }
private val NSImageBinding_VH: VarHandle by lazy { NSImageBinding_LAYOUT.varHandle() }

var NSImageBinding: MemorySegment
    get() = NSImageBinding_VH.get(NSImageBinding_SEGMENT) as MemorySegment
    set(value) = NSImageBinding_VH.set(NSImageBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSIncludedKeysBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSIncludedKeysBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSIncludedKeysBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIncludedKeysBinding").orElseThrow() }
private val NSIncludedKeysBinding_VH: VarHandle by lazy { NSIncludedKeysBinding_LAYOUT.varHandle() }

var NSIncludedKeysBinding: MemorySegment
    get() = NSIncludedKeysBinding_VH.get(NSIncludedKeysBinding_SEGMENT) as MemorySegment
    set(value) = NSIncludedKeysBinding_VH.set(NSIncludedKeysBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSInitialKeyBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSInitialKeyBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInitialKeyBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInitialKeyBinding").orElseThrow() }
private val NSInitialKeyBinding_VH: VarHandle by lazy { NSInitialKeyBinding_LAYOUT.varHandle() }

var NSInitialKeyBinding: MemorySegment
    get() = NSInitialKeyBinding_VH.get(NSInitialKeyBinding_SEGMENT) as MemorySegment
    set(value) = NSInitialKeyBinding_VH.set(NSInitialKeyBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSInitialValueBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSInitialValueBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInitialValueBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInitialValueBinding").orElseThrow() }
private val NSInitialValueBinding_VH: VarHandle by lazy { NSInitialValueBinding_LAYOUT.varHandle() }

var NSInitialValueBinding: MemorySegment
    get() = NSInitialValueBinding_VH.get(NSInitialValueBinding_SEGMENT) as MemorySegment
    set(value) = NSInitialValueBinding_VH.set(NSInitialValueBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSIsIndeterminateBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSIsIndeterminateBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSIsIndeterminateBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIsIndeterminateBinding").orElseThrow() }
private val NSIsIndeterminateBinding_VH: VarHandle by lazy { NSIsIndeterminateBinding_LAYOUT.varHandle() }

var NSIsIndeterminateBinding: MemorySegment
    get() = NSIsIndeterminateBinding_VH.get(NSIsIndeterminateBinding_SEGMENT) as MemorySegment
    set(value) = NSIsIndeterminateBinding_VH.set(NSIsIndeterminateBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSLabelBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSLabelBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLabelBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLabelBinding").orElseThrow() }
private val NSLabelBinding_VH: VarHandle by lazy { NSLabelBinding_LAYOUT.varHandle() }

var NSLabelBinding: MemorySegment
    get() = NSLabelBinding_VH.get(NSLabelBinding_SEGMENT) as MemorySegment
    set(value) = NSLabelBinding_VH.set(NSLabelBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocalizedKeyDictionaryBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSLocalizedKeyDictionaryBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocalizedKeyDictionaryBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocalizedKeyDictionaryBinding").orElseThrow() }
private val NSLocalizedKeyDictionaryBinding_VH: VarHandle by lazy { NSLocalizedKeyDictionaryBinding_LAYOUT.varHandle() }

var NSLocalizedKeyDictionaryBinding: MemorySegment
    get() = NSLocalizedKeyDictionaryBinding_VH.get(NSLocalizedKeyDictionaryBinding_SEGMENT) as MemorySegment
    set(value) = NSLocalizedKeyDictionaryBinding_VH.set(NSLocalizedKeyDictionaryBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSManagedObjectContextBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSManagedObjectContextBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSManagedObjectContextBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSManagedObjectContextBinding").orElseThrow() }
private val NSManagedObjectContextBinding_VH: VarHandle by lazy { NSManagedObjectContextBinding_LAYOUT.varHandle() }

var NSManagedObjectContextBinding: MemorySegment
    get() = NSManagedObjectContextBinding_VH.get(NSManagedObjectContextBinding_SEGMENT) as MemorySegment
    set(value) = NSManagedObjectContextBinding_VH.set(NSManagedObjectContextBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSMaximumRecentsBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSMaximumRecentsBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMaximumRecentsBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMaximumRecentsBinding").orElseThrow() }
private val NSMaximumRecentsBinding_VH: VarHandle by lazy { NSMaximumRecentsBinding_LAYOUT.varHandle() }

var NSMaximumRecentsBinding: MemorySegment
    get() = NSMaximumRecentsBinding_VH.get(NSMaximumRecentsBinding_SEGMENT) as MemorySegment
    set(value) = NSMaximumRecentsBinding_VH.set(NSMaximumRecentsBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSMaxValueBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSMaxValueBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMaxValueBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMaxValueBinding").orElseThrow() }
private val NSMaxValueBinding_VH: VarHandle by lazy { NSMaxValueBinding_LAYOUT.varHandle() }

var NSMaxValueBinding: MemorySegment
    get() = NSMaxValueBinding_VH.get(NSMaxValueBinding_SEGMENT) as MemorySegment
    set(value) = NSMaxValueBinding_VH.set(NSMaxValueBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSMaxWidthBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSMaxWidthBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMaxWidthBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMaxWidthBinding").orElseThrow() }
private val NSMaxWidthBinding_VH: VarHandle by lazy { NSMaxWidthBinding_LAYOUT.varHandle() }

var NSMaxWidthBinding: MemorySegment
    get() = NSMaxWidthBinding_VH.get(NSMaxWidthBinding_SEGMENT) as MemorySegment
    set(value) = NSMaxWidthBinding_VH.set(NSMaxWidthBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSMinValueBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSMinValueBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMinValueBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMinValueBinding").orElseThrow() }
private val NSMinValueBinding_VH: VarHandle by lazy { NSMinValueBinding_LAYOUT.varHandle() }

var NSMinValueBinding: MemorySegment
    get() = NSMinValueBinding_VH.get(NSMinValueBinding_SEGMENT) as MemorySegment
    set(value) = NSMinValueBinding_VH.set(NSMinValueBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSMinWidthBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSMinWidthBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMinWidthBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMinWidthBinding").orElseThrow() }
private val NSMinWidthBinding_VH: VarHandle by lazy { NSMinWidthBinding_LAYOUT.varHandle() }

var NSMinWidthBinding: MemorySegment
    get() = NSMinWidthBinding_VH.get(NSMinWidthBinding_SEGMENT) as MemorySegment
    set(value) = NSMinWidthBinding_VH.set(NSMinWidthBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSMixedStateImageBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSMixedStateImageBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMixedStateImageBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMixedStateImageBinding").orElseThrow() }
private val NSMixedStateImageBinding_VH: VarHandle by lazy { NSMixedStateImageBinding_LAYOUT.varHandle() }

var NSMixedStateImageBinding: MemorySegment
    get() = NSMixedStateImageBinding_VH.get(NSMixedStateImageBinding_SEGMENT) as MemorySegment
    set(value) = NSMixedStateImageBinding_VH.set(NSMixedStateImageBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSOffStateImageBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSOffStateImageBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOffStateImageBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOffStateImageBinding").orElseThrow() }
private val NSOffStateImageBinding_VH: VarHandle by lazy { NSOffStateImageBinding_LAYOUT.varHandle() }

var NSOffStateImageBinding: MemorySegment
    get() = NSOffStateImageBinding_VH.get(NSOffStateImageBinding_SEGMENT) as MemorySegment
    set(value) = NSOffStateImageBinding_VH.set(NSOffStateImageBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSOnStateImageBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSOnStateImageBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOnStateImageBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOnStateImageBinding").orElseThrow() }
private val NSOnStateImageBinding_VH: VarHandle by lazy { NSOnStateImageBinding_LAYOUT.varHandle() }

var NSOnStateImageBinding: MemorySegment
    get() = NSOnStateImageBinding_VH.get(NSOnStateImageBinding_SEGMENT) as MemorySegment
    set(value) = NSOnStateImageBinding_VH.set(NSOnStateImageBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSPositioningRectBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSPositioningRectBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPositioningRectBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPositioningRectBinding").orElseThrow() }
private val NSPositioningRectBinding_VH: VarHandle by lazy { NSPositioningRectBinding_LAYOUT.varHandle() }

var NSPositioningRectBinding: MemorySegment
    get() = NSPositioningRectBinding_VH.get(NSPositioningRectBinding_SEGMENT) as MemorySegment
    set(value) = NSPositioningRectBinding_VH.set(NSPositioningRectBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSPredicateBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSPredicateBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPredicateBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPredicateBinding").orElseThrow() }
private val NSPredicateBinding_VH: VarHandle by lazy { NSPredicateBinding_LAYOUT.varHandle() }

var NSPredicateBinding: MemorySegment
    get() = NSPredicateBinding_VH.get(NSPredicateBinding_SEGMENT) as MemorySegment
    set(value) = NSPredicateBinding_VH.set(NSPredicateBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSRecentSearchesBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSRecentSearchesBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRecentSearchesBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRecentSearchesBinding").orElseThrow() }
private val NSRecentSearchesBinding_VH: VarHandle by lazy { NSRecentSearchesBinding_LAYOUT.varHandle() }

var NSRecentSearchesBinding: MemorySegment
    get() = NSRecentSearchesBinding_VH.get(NSRecentSearchesBinding_SEGMENT) as MemorySegment
    set(value) = NSRecentSearchesBinding_VH.set(NSRecentSearchesBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSRepresentedFilenameBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSRepresentedFilenameBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRepresentedFilenameBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRepresentedFilenameBinding").orElseThrow() }
private val NSRepresentedFilenameBinding_VH: VarHandle by lazy { NSRepresentedFilenameBinding_LAYOUT.varHandle() }

var NSRepresentedFilenameBinding: MemorySegment
    get() = NSRepresentedFilenameBinding_VH.get(NSRepresentedFilenameBinding_SEGMENT) as MemorySegment
    set(value) = NSRepresentedFilenameBinding_VH.set(NSRepresentedFilenameBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSRowHeightBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSRowHeightBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRowHeightBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRowHeightBinding").orElseThrow() }
private val NSRowHeightBinding_VH: VarHandle by lazy { NSRowHeightBinding_LAYOUT.varHandle() }

var NSRowHeightBinding: MemorySegment
    get() = NSRowHeightBinding_VH.get(NSRowHeightBinding_SEGMENT) as MemorySegment
    set(value) = NSRowHeightBinding_VH.set(NSRowHeightBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectedIdentifierBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectedIdentifierBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectedIdentifierBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectedIdentifierBinding").orElseThrow() }
private val NSSelectedIdentifierBinding_VH: VarHandle by lazy { NSSelectedIdentifierBinding_LAYOUT.varHandle() }

var NSSelectedIdentifierBinding: MemorySegment
    get() = NSSelectedIdentifierBinding_VH.get(NSSelectedIdentifierBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectedIdentifierBinding_VH.set(NSSelectedIdentifierBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectedIndexBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectedIndexBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectedIndexBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectedIndexBinding").orElseThrow() }
private val NSSelectedIndexBinding_VH: VarHandle by lazy { NSSelectedIndexBinding_LAYOUT.varHandle() }

var NSSelectedIndexBinding: MemorySegment
    get() = NSSelectedIndexBinding_VH.get(NSSelectedIndexBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectedIndexBinding_VH.set(NSSelectedIndexBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectedLabelBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectedLabelBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectedLabelBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectedLabelBinding").orElseThrow() }
private val NSSelectedLabelBinding_VH: VarHandle by lazy { NSSelectedLabelBinding_LAYOUT.varHandle() }

var NSSelectedLabelBinding: MemorySegment
    get() = NSSelectedLabelBinding_VH.get(NSSelectedLabelBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectedLabelBinding_VH.set(NSSelectedLabelBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectedObjectBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectedObjectBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectedObjectBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectedObjectBinding").orElseThrow() }
private val NSSelectedObjectBinding_VH: VarHandle by lazy { NSSelectedObjectBinding_LAYOUT.varHandle() }

var NSSelectedObjectBinding: MemorySegment
    get() = NSSelectedObjectBinding_VH.get(NSSelectedObjectBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectedObjectBinding_VH.set(NSSelectedObjectBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectedObjectsBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectedObjectsBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectedObjectsBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectedObjectsBinding").orElseThrow() }
private val NSSelectedObjectsBinding_VH: VarHandle by lazy { NSSelectedObjectsBinding_LAYOUT.varHandle() }

var NSSelectedObjectsBinding: MemorySegment
    get() = NSSelectedObjectsBinding_VH.get(NSSelectedObjectsBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectedObjectsBinding_VH.set(NSSelectedObjectsBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectedTagBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectedTagBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectedTagBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectedTagBinding").orElseThrow() }
private val NSSelectedTagBinding_VH: VarHandle by lazy { NSSelectedTagBinding_LAYOUT.varHandle() }

var NSSelectedTagBinding: MemorySegment
    get() = NSSelectedTagBinding_VH.get(NSSelectedTagBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectedTagBinding_VH.set(NSSelectedTagBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectedValueBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectedValueBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectedValueBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectedValueBinding").orElseThrow() }
private val NSSelectedValueBinding_VH: VarHandle by lazy { NSSelectedValueBinding_LAYOUT.varHandle() }

var NSSelectedValueBinding: MemorySegment
    get() = NSSelectedValueBinding_VH.get(NSSelectedValueBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectedValueBinding_VH.set(NSSelectedValueBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectedValuesBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectedValuesBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectedValuesBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectedValuesBinding").orElseThrow() }
private val NSSelectedValuesBinding_VH: VarHandle by lazy { NSSelectedValuesBinding_LAYOUT.varHandle() }

var NSSelectedValuesBinding: MemorySegment
    get() = NSSelectedValuesBinding_VH.get(NSSelectedValuesBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectedValuesBinding_VH.set(NSSelectedValuesBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectionIndexesBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectionIndexesBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectionIndexesBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectionIndexesBinding").orElseThrow() }
private val NSSelectionIndexesBinding_VH: VarHandle by lazy { NSSelectionIndexesBinding_LAYOUT.varHandle() }

var NSSelectionIndexesBinding: MemorySegment
    get() = NSSelectionIndexesBinding_VH.get(NSSelectionIndexesBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectionIndexesBinding_VH.set(NSSelectionIndexesBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSelectionIndexPathsBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSelectionIndexPathsBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSelectionIndexPathsBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSelectionIndexPathsBinding").orElseThrow() }
private val NSSelectionIndexPathsBinding_VH: VarHandle by lazy { NSSelectionIndexPathsBinding_LAYOUT.varHandle() }

var NSSelectionIndexPathsBinding: MemorySegment
    get() = NSSelectionIndexPathsBinding_VH.get(NSSelectionIndexPathsBinding_SEGMENT) as MemorySegment
    set(value) = NSSelectionIndexPathsBinding_VH.set(NSSelectionIndexPathsBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSSortDescriptorsBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSSortDescriptorsBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSortDescriptorsBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSortDescriptorsBinding").orElseThrow() }
private val NSSortDescriptorsBinding_VH: VarHandle by lazy { NSSortDescriptorsBinding_LAYOUT.varHandle() }

var NSSortDescriptorsBinding: MemorySegment
    get() = NSSortDescriptorsBinding_VH.get(NSSortDescriptorsBinding_SEGMENT) as MemorySegment
    set(value) = NSSortDescriptorsBinding_VH.set(NSSortDescriptorsBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSTargetBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSTargetBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTargetBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTargetBinding").orElseThrow() }
private val NSTargetBinding_VH: VarHandle by lazy { NSTargetBinding_LAYOUT.varHandle() }

var NSTargetBinding: MemorySegment
    get() = NSTargetBinding_VH.get(NSTargetBinding_SEGMENT) as MemorySegment
    set(value) = NSTargetBinding_VH.set(NSTargetBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextColorBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSTextColorBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextColorBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextColorBinding").orElseThrow() }
private val NSTextColorBinding_VH: VarHandle by lazy { NSTextColorBinding_LAYOUT.varHandle() }

var NSTextColorBinding: MemorySegment
    get() = NSTextColorBinding_VH.get(NSTextColorBinding_SEGMENT) as MemorySegment
    set(value) = NSTextColorBinding_VH.set(NSTextColorBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSTitleBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSTitleBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTitleBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTitleBinding").orElseThrow() }
private val NSTitleBinding_VH: VarHandle by lazy { NSTitleBinding_LAYOUT.varHandle() }

var NSTitleBinding: MemorySegment
    get() = NSTitleBinding_VH.get(NSTitleBinding_SEGMENT) as MemorySegment
    set(value) = NSTitleBinding_VH.set(NSTitleBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolTipBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSToolTipBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolTipBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolTipBinding").orElseThrow() }
private val NSToolTipBinding_VH: VarHandle by lazy { NSToolTipBinding_LAYOUT.varHandle() }

var NSToolTipBinding: MemorySegment
    get() = NSToolTipBinding_VH.get(NSToolTipBinding_SEGMENT) as MemorySegment
    set(value) = NSToolTipBinding_VH.set(NSToolTipBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSTransparentBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSTransparentBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTransparentBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTransparentBinding").orElseThrow() }
private val NSTransparentBinding_VH: VarHandle by lazy { NSTransparentBinding_LAYOUT.varHandle() }

var NSTransparentBinding: MemorySegment
    get() = NSTransparentBinding_VH.get(NSTransparentBinding_SEGMENT) as MemorySegment
    set(value) = NSTransparentBinding_VH.set(NSTransparentBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSValueBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSValueBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSValueBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSValueBinding").orElseThrow() }
private val NSValueBinding_VH: VarHandle by lazy { NSValueBinding_LAYOUT.varHandle() }

var NSValueBinding: MemorySegment
    get() = NSValueBinding_VH.get(NSValueBinding_SEGMENT) as MemorySegment
    set(value) = NSValueBinding_VH.set(NSValueBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSValuePathBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSValuePathBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSValuePathBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSValuePathBinding").orElseThrow() }
private val NSValuePathBinding_VH: VarHandle by lazy { NSValuePathBinding_LAYOUT.varHandle() }

var NSValuePathBinding: MemorySegment
    get() = NSValuePathBinding_VH.get(NSValuePathBinding_SEGMENT) as MemorySegment
    set(value) = NSValuePathBinding_VH.set(NSValuePathBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSValueURLBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSValueURLBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSValueURLBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSValueURLBinding").orElseThrow() }
private val NSValueURLBinding_VH: VarHandle by lazy { NSValueURLBinding_LAYOUT.varHandle() }

var NSValueURLBinding: MemorySegment
    get() = NSValueURLBinding_VH.get(NSValueURLBinding_SEGMENT) as MemorySegment
    set(value) = NSValueURLBinding_VH.set(NSValueURLBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSVisibleBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSVisibleBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVisibleBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVisibleBinding").orElseThrow() }
private val NSVisibleBinding_VH: VarHandle by lazy { NSVisibleBinding_LAYOUT.varHandle() }

var NSVisibleBinding: MemorySegment
    get() = NSVisibleBinding_VH.get(NSVisibleBinding_SEGMENT) as MemorySegment
    set(value) = NSVisibleBinding_VH.set(NSVisibleBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSWarningValueBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSWarningValueBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWarningValueBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWarningValueBinding").orElseThrow() }
private val NSWarningValueBinding_VH: VarHandle by lazy { NSWarningValueBinding_LAYOUT.varHandle() }

var NSWarningValueBinding: MemorySegment
    get() = NSWarningValueBinding_VH.get(NSWarningValueBinding_SEGMENT) as MemorySegment
    set(value) = NSWarningValueBinding_VH.set(NSWarningValueBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSWidthBinding typedef NSBindingName = typedef NSString = (Void)*
 */
private val NSWidthBinding_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWidthBinding_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWidthBinding").orElseThrow() }
private val NSWidthBinding_VH: VarHandle by lazy { NSWidthBinding_LAYOUT.varHandle() }

var NSWidthBinding: MemorySegment
    get() = NSWidthBinding_VH.get(NSWidthBinding_SEGMENT) as MemorySegment
    set(value) = NSWidthBinding_VH.set(NSWidthBinding_SEGMENT, value)

/**
 * {@snippet lang=c : NSAllowsEditingMultipleValuesSelectionBindingOption typedef NSBindingOption = typedef NSString = (Void)*
 */
private val NSAllowsEditingMultipleValuesSelectionBindingOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAllowsEditingMultipleValuesSelectionBindingOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAllowsEditingMultipleValuesSelectionBindingOption").orElseThrow() }
private val NSAllowsEditingMultipleValuesSelectionBindingOption_VH: VarHandle by lazy { NSAllowsEditingMultipleValuesSelectionBindingOption_LAYOUT.varHandle() }

var NSAllowsEditingMultipleValuesSelectionBindingOption: MemorySegment
    get() = NSAllowsEditingMultipleValuesSelectionBindingOption_VH.get(NSAllowsEditingMultipleValuesSelectionBindingOption_SEGMENT) as MemorySegment
    set(value) = NSAllowsEditingMultipleValuesSelectionBindingOption_VH.set(NSAllowsEditingMultipleValuesSelectionBindingOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSAllowsNullArgumentBindingOption typedef NSBindingOption = typedef NSString = (Void)*
 */
private val NSAllowsNullArgumentBindingOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAllowsNullArgumentBindingOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAllowsNullArgumentBindingOption").orElseThrow() }
private val NSAllowsNullArgumentBindingOption_VH: VarHandle by lazy { NSAllowsNullArgumentBindingOption_LAYOUT.varHandle() }

var NSAllowsNullArgumentBindingOption: MemorySegment
    get() = NSAllowsNullArgumentBindingOption_VH.get(NSAllowsNullArgumentBindingOption_SEGMENT) as MemorySegment
    set(value) = NSAllowsNullArgumentBindingOption_VH.set(NSAllowsNullArgumentBindingOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSAlwaysPresentsApplicationModalAlertsBindingOption typedef NSBindingOption = typedef NSString = (Void)*
 */
private val NSAlwaysPresentsApplicationModalAlertsBindingOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAlwaysPresentsApplicationModalAlertsBindingOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAlwaysPresentsApplicationModalAlertsBindingOption").orElseThrow() }
private val NSAlwaysPresentsApplicationModalAlertsBindingOption_VH: VarHandle by lazy { NSAlwaysPresentsApplicationModalAlertsBindingOption_LAYOUT.varHandle() }

var NSAlwaysPresentsApplicationModalAlertsBindingOption: MemorySegment
    get() = NSAlwaysPresentsApplicationModalAlertsBindingOption_VH.get(NSAlwaysPresentsApplicationModalAlertsBindingOption_SEGMENT) as MemorySegment
    set(value) = NSAlwaysPresentsApplicationModalAlertsBindingOption_VH.set(NSAlwaysPresentsApplicationModalAlertsBindingOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSConditionallySetsEditableBindingOption typedef NSBindingOption = typedef NSString = (Void)*
 */
private val NSConditionallySetsEditableBindingOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSConditionallySetsEditableBindingOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSConditionallySetsEditableBindingOption").orElseThrow() }
private val NSConditionallySetsEditableBindingOption_VH: VarHandle by lazy { NSConditionallySetsEditableBindingOption_LAYOUT.varHandle() }

var NSConditionallySetsEditableBindingOption: MemorySegment
    get() = NSConditionallySetsEditableBindingOption_VH.get(NSConditionallySetsEditableBindingOption_SEGMENT) as MemorySegment
    set(value) = NSConditionallySetsEditableBindingOption_VH.set(NSConditionallySetsEditableBindingOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSConditionallySetsEnabledBindingOption typedef NSBindingOption = typedef NSString = (Void)*
 */
private val NSConditionallySetsEnabledBindingOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSConditionallySetsEnabledBindingOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSConditionallySetsEnabledBindingOption").orElseThrow() }
private val NSConditionallySetsEnabledBindingOption_VH: VarHandle by lazy { NSConditionallySetsEnabledBindingOption_LAYOUT.varHandle() }

var NSConditionallySetsEnabledBindingOption: MemorySegment
    get() = NSConditionallySetsEnabledBindingOption_VH.get(NSConditionallySetsEnabledBindingOption_SEGMENT) as MemorySegment
    set(value) = NSConditionallySetsEnabledBindingOption_VH.set(NSConditionallySetsEnabledBindingOption_SEGMENT, value)

