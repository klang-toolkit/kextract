package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : kCFCoreFoundationVersionNumber Double
 */
private val kCFCoreFoundationVersionNumber_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val kCFCoreFoundationVersionNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFCoreFoundationVersionNumber").orElseThrow() }
private val kCFCoreFoundationVersionNumber_VH: VarHandle by lazy { kCFCoreFoundationVersionNumber_LAYOUT.varHandle() }

var kCFCoreFoundationVersionNumber: Double
    get() = kCFCoreFoundationVersionNumber_VH.get(kCFCoreFoundationVersionNumber_SEGMENT) as Double
    set(value) = kCFCoreFoundationVersionNumber_VH.set(kCFCoreFoundationVersionNumber_SEGMENT, value)

/**
 * {@snippet lang=c : __CFRangeMake typedef CFRange = Declared(CFRange)(typedef CFIndex = Long,typedef CFIndex = Long)
 */
private val _CFRangeMake_DESC: FunctionDescriptor = FunctionDescriptor.of(CFRange.layout, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val _CFRangeMake_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("__CFRangeMake").orElseThrow()
private val _CFRangeMake_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(_CFRangeMake_ADDR, _CFRangeMake_DESC)

fun _CFRangeMake(allocator: SegmentAllocator, arg0: Long, arg1: Long): MemorySegment {
    try {
        return _CFRangeMake_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNullGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFNullGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFNullGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNullGetTypeID").orElseThrow()
private val CFNullGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNullGetTypeID_ADDR, CFNullGetTypeID_DESC)

fun CFNullGetTypeID(): Long {
    try {
        return CFNullGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFNull typedef const CFNullRef = (Declared(__CFNull))*
 */
private val kCFNull_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNull_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNull").orElseThrow() }
private val kCFNull_VH: VarHandle by lazy { kCFNull_LAYOUT.varHandle() }

var kCFNull: MemorySegment
    get() = kCFNull_VH.get(kCFNull_SEGMENT) as MemorySegment
    set(value) = kCFNull_VH.set(kCFNull_SEGMENT, value)

/**
 * {@snippet lang=c : kCFAllocatorDefault typedef const CFAllocatorRef = (Declared(__CFAllocator))*
 */
private val kCFAllocatorDefault_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFAllocatorDefault_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFAllocatorDefault").orElseThrow() }
private val kCFAllocatorDefault_VH: VarHandle by lazy { kCFAllocatorDefault_LAYOUT.varHandle() }

var kCFAllocatorDefault: MemorySegment
    get() = kCFAllocatorDefault_VH.get(kCFAllocatorDefault_SEGMENT) as MemorySegment
    set(value) = kCFAllocatorDefault_VH.set(kCFAllocatorDefault_SEGMENT, value)

/**
 * {@snippet lang=c : kCFAllocatorSystemDefault typedef const CFAllocatorRef = (Declared(__CFAllocator))*
 */
private val kCFAllocatorSystemDefault_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFAllocatorSystemDefault_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFAllocatorSystemDefault").orElseThrow() }
private val kCFAllocatorSystemDefault_VH: VarHandle by lazy { kCFAllocatorSystemDefault_LAYOUT.varHandle() }

var kCFAllocatorSystemDefault: MemorySegment
    get() = kCFAllocatorSystemDefault_VH.get(kCFAllocatorSystemDefault_SEGMENT) as MemorySegment
    set(value) = kCFAllocatorSystemDefault_VH.set(kCFAllocatorSystemDefault_SEGMENT, value)

/**
 * {@snippet lang=c : kCFAllocatorMalloc typedef const CFAllocatorRef = (Declared(__CFAllocator))*
 */
private val kCFAllocatorMalloc_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFAllocatorMalloc_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFAllocatorMalloc").orElseThrow() }
private val kCFAllocatorMalloc_VH: VarHandle by lazy { kCFAllocatorMalloc_LAYOUT.varHandle() }

var kCFAllocatorMalloc: MemorySegment
    get() = kCFAllocatorMalloc_VH.get(kCFAllocatorMalloc_SEGMENT) as MemorySegment
    set(value) = kCFAllocatorMalloc_VH.set(kCFAllocatorMalloc_SEGMENT, value)

/**
 * {@snippet lang=c : kCFAllocatorMallocZone typedef const CFAllocatorRef = (Declared(__CFAllocator))*
 */
private val kCFAllocatorMallocZone_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFAllocatorMallocZone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFAllocatorMallocZone").orElseThrow() }
private val kCFAllocatorMallocZone_VH: VarHandle by lazy { kCFAllocatorMallocZone_LAYOUT.varHandle() }

var kCFAllocatorMallocZone: MemorySegment
    get() = kCFAllocatorMallocZone_VH.get(kCFAllocatorMallocZone_SEGMENT) as MemorySegment
    set(value) = kCFAllocatorMallocZone_VH.set(kCFAllocatorMallocZone_SEGMENT, value)

/**
 * {@snippet lang=c : kCFAllocatorNull typedef const CFAllocatorRef = (Declared(__CFAllocator))*
 */
private val kCFAllocatorNull_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFAllocatorNull_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFAllocatorNull").orElseThrow() }
private val kCFAllocatorNull_VH: VarHandle by lazy { kCFAllocatorNull_LAYOUT.varHandle() }

var kCFAllocatorNull: MemorySegment
    get() = kCFAllocatorNull_VH.get(kCFAllocatorNull_SEGMENT) as MemorySegment
    set(value) = kCFAllocatorNull_VH.set(kCFAllocatorNull_SEGMENT, value)

/**
 * {@snippet lang=c : kCFAllocatorUseContext typedef const CFAllocatorRef = (Declared(__CFAllocator))*
 */
private val kCFAllocatorUseContext_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFAllocatorUseContext_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFAllocatorUseContext").orElseThrow() }
private val kCFAllocatorUseContext_VH: VarHandle by lazy { kCFAllocatorUseContext_LAYOUT.varHandle() }

var kCFAllocatorUseContext: MemorySegment
    get() = kCFAllocatorUseContext_VH.get(kCFAllocatorUseContext_SEGMENT) as MemorySegment
    set(value) = kCFAllocatorUseContext_VH.set(kCFAllocatorUseContext_SEGMENT, value)

/**
 * {@snippet lang=c : CFAllocatorGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFAllocatorGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFAllocatorGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorGetTypeID").orElseThrow()
private val CFAllocatorGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorGetTypeID_ADDR, CFAllocatorGetTypeID_DESC)

fun CFAllocatorGetTypeID(): Long {
    try {
        return CFAllocatorGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorSetDefault Void(typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFAllocatorSetDefault_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFAllocatorSetDefault_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorSetDefault").orElseThrow()
private val CFAllocatorSetDefault_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorSetDefault_ADDR, CFAllocatorSetDefault_DESC)

fun CFAllocatorSetDefault(arg0: MemorySegment): Unit {
    try {
        CFAllocatorSetDefault_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorGetDefault typedef CFAllocatorRef = (Declared(__CFAllocator))*()
 */
private val CFAllocatorGetDefault_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFAllocatorGetDefault_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorGetDefault").orElseThrow()
private val CFAllocatorGetDefault_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorGetDefault_ADDR, CFAllocatorGetDefault_DESC)

fun CFAllocatorGetDefault(): MemorySegment {
    try {
        return CFAllocatorGetDefault_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorCreate typedef CFAllocatorRef = (Declared(__CFAllocator))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef CFAllocatorContext = Declared(CFAllocatorContext))*)
 */
private val CFAllocatorCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAllocatorCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorCreate").orElseThrow()
private val CFAllocatorCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorCreate_ADDR, CFAllocatorCreate_DESC)

fun CFAllocatorCreate(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFAllocatorCreate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorCreateWithZone typedef CFAllocatorRef = (Declared(__CFAllocator))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(Declared(_malloc_zone_t))*)
 */
private val CFAllocatorCreateWithZone_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAllocatorCreateWithZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorCreateWithZone").orElseThrow()
private val CFAllocatorCreateWithZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorCreateWithZone_ADDR, CFAllocatorCreateWithZone_DESC)

fun CFAllocatorCreateWithZone(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFAllocatorCreateWithZone_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorAllocateTyped (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFAllocatorTypeID = UNSIGNED = LongLong,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFAllocatorAllocateTyped_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFAllocatorAllocateTyped_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorAllocateTyped").orElseThrow()
private val CFAllocatorAllocateTyped_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorAllocateTyped_ADDR, CFAllocatorAllocateTyped_DESC)

fun CFAllocatorAllocateTyped(arg0: MemorySegment, arg1: Long, arg2: Long, arg3: Long): MemorySegment {
    try {
        return CFAllocatorAllocateTyped_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorReallocateTyped (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(Void)*,typedef CFIndex = Long,typedef CFAllocatorTypeID = UNSIGNED = LongLong,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFAllocatorReallocateTyped_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFAllocatorReallocateTyped_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorReallocateTyped").orElseThrow()
private val CFAllocatorReallocateTyped_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorReallocateTyped_ADDR, CFAllocatorReallocateTyped_DESC)

fun CFAllocatorReallocateTyped(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long, arg4: Long): MemorySegment {
    try {
        return CFAllocatorReallocateTyped_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorAllocateBytes (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFAllocatorAllocateBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFAllocatorAllocateBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorAllocateBytes").orElseThrow()
private val CFAllocatorAllocateBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorAllocateBytes_ADDR, CFAllocatorAllocateBytes_DESC)

fun CFAllocatorAllocateBytes(arg0: MemorySegment, arg1: Long, arg2: Long): MemorySegment {
    try {
        return CFAllocatorAllocateBytes_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorReallocateBytes (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(Void)*,typedef CFIndex = Long,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFAllocatorReallocateBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFAllocatorReallocateBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorReallocateBytes").orElseThrow()
private val CFAllocatorReallocateBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorReallocateBytes_ADDR, CFAllocatorReallocateBytes_DESC)

fun CFAllocatorReallocateBytes(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long): MemorySegment {
    try {
        return CFAllocatorReallocateBytes_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorAllocate (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFAllocatorAllocate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFAllocatorAllocate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorAllocate").orElseThrow()
private val CFAllocatorAllocate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorAllocate_ADDR, CFAllocatorAllocate_DESC)

fun CFAllocatorAllocate(arg0: MemorySegment, arg1: Long, arg2: Long): MemorySegment {
    try {
        return CFAllocatorAllocate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorReallocate (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(Void)*,typedef CFIndex = Long,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFAllocatorReallocate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFAllocatorReallocate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorReallocate").orElseThrow()
private val CFAllocatorReallocate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorReallocate_ADDR, CFAllocatorReallocate_DESC)

fun CFAllocatorReallocate(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long): MemorySegment {
    try {
        return CFAllocatorReallocate_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorDeallocate Void(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(Void)*)
 */
private val CFAllocatorDeallocate_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAllocatorDeallocate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorDeallocate").orElseThrow()
private val CFAllocatorDeallocate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorDeallocate_ADDR, CFAllocatorDeallocate_DESC)

fun CFAllocatorDeallocate(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFAllocatorDeallocate_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorGetPreferredSizeForSize typedef CFIndex = Long(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFAllocatorGetPreferredSizeForSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFAllocatorGetPreferredSizeForSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorGetPreferredSizeForSize").orElseThrow()
private val CFAllocatorGetPreferredSizeForSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorGetPreferredSizeForSize_ADDR, CFAllocatorGetPreferredSizeForSize_DESC)

fun CFAllocatorGetPreferredSizeForSize(arg0: MemorySegment, arg1: Long, arg2: Long): Long {
    try {
        return CFAllocatorGetPreferredSizeForSize_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAllocatorGetContext Void(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef CFAllocatorContext = Declared(CFAllocatorContext))*)
 */
private val CFAllocatorGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAllocatorGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAllocatorGetContext").orElseThrow()
private val CFAllocatorGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAllocatorGetContext_ADDR, CFAllocatorGetContext_DESC)

fun CFAllocatorGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFAllocatorGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFGetTypeID typedef CFTypeID = UNSIGNED = Long(typedef CFTypeRef = (Void)*)
 */
private val CFGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFGetTypeID").orElseThrow()
private val CFGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFGetTypeID_ADDR, CFGetTypeID_DESC)

fun CFGetTypeID(arg0: MemorySegment): Long {
    try {
        return CFGetTypeID_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCopyTypeIDDescription typedef CFStringRef = (Declared(__CFString))*(typedef CFTypeID = UNSIGNED = Long)
 */
private val CFCopyTypeIDDescription_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFCopyTypeIDDescription_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCopyTypeIDDescription").orElseThrow()
private val CFCopyTypeIDDescription_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCopyTypeIDDescription_ADDR, CFCopyTypeIDDescription_DESC)

fun CFCopyTypeIDDescription(arg0: Long): MemorySegment {
    try {
        return CFCopyTypeIDDescription_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRetain typedef CFTypeRef = (Void)*(typedef CFTypeRef = (Void)*)
 */
private val CFRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRetain").orElseThrow()
private val CFRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRetain_ADDR, CFRetain_DESC)

fun CFRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CFRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRelease Void(typedef CFTypeRef = (Void)*)
 */
private val CFRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRelease").orElseThrow()
private val CFRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRelease_ADDR, CFRelease_DESC)

fun CFRelease(arg0: MemorySegment): Unit {
    try {
        CFRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAutorelease typedef CFTypeRef = (Void)*(typedef CFTypeRef = (Void)*)
 */
private val CFAutorelease_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAutorelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAutorelease").orElseThrow()
private val CFAutorelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAutorelease_ADDR, CFAutorelease_DESC)

fun CFAutorelease(arg0: MemorySegment): MemorySegment {
    try {
        return CFAutorelease_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFGetRetainCount typedef CFIndex = Long(typedef CFTypeRef = (Void)*)
 */
private val CFGetRetainCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFGetRetainCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFGetRetainCount").orElseThrow()
private val CFGetRetainCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFGetRetainCount_ADDR, CFGetRetainCount_DESC)

fun CFGetRetainCount(arg0: MemorySegment): Long {
    try {
        return CFGetRetainCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFEqual typedef Boolean = UNSIGNED = Char(typedef CFTypeRef = (Void)*,typedef CFTypeRef = (Void)*)
 */
private val CFEqual_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFEqual_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFEqual").orElseThrow()
private val CFEqual_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFEqual_ADDR, CFEqual_DESC)

fun CFEqual(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFEqual_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFHash typedef CFHashCode = UNSIGNED = Long(typedef CFTypeRef = (Void)*)
 */
private val CFHash_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFHash_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFHash").orElseThrow()
private val CFHash_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFHash_ADDR, CFHash_DESC)

fun CFHash(arg0: MemorySegment): Long {
    try {
        return CFHash_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCopyDescription typedef CFStringRef = (Declared(__CFString))*(typedef CFTypeRef = (Void)*)
 */
private val CFCopyDescription_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCopyDescription_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCopyDescription").orElseThrow()
private val CFCopyDescription_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCopyDescription_ADDR, CFCopyDescription_DESC)

fun CFCopyDescription(arg0: MemorySegment): MemorySegment {
    try {
        return CFCopyDescription_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFGetAllocator typedef CFAllocatorRef = (Declared(__CFAllocator))*(typedef CFTypeRef = (Void)*)
 */
private val CFGetAllocator_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFGetAllocator_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFGetAllocator").orElseThrow()
private val CFGetAllocator_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFGetAllocator_ADDR, CFGetAllocator_DESC)

fun CFGetAllocator(arg0: MemorySegment): MemorySegment {
    try {
        return CFGetAllocator_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMakeCollectable typedef CFTypeRef = (Void)*(typedef CFTypeRef = (Void)*)
 */
private val CFMakeCollectable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMakeCollectable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMakeCollectable").orElseThrow()
private val CFMakeCollectable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMakeCollectable_ADDR, CFMakeCollectable_DESC)

fun CFMakeCollectable(arg0: MemorySegment): MemorySegment {
    try {
        return CFMakeCollectable_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFTypeArrayCallBacks typedef const CFArrayCallBacks = Declared(CFArrayCallBacks)
 */
private val kCFTypeArrayCallBacks_LAYOUT: MemoryLayout by lazy { CFArrayCallBacks.layout }
private val kCFTypeArrayCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFTypeArrayCallBacks").orElseThrow() }
private val kCFTypeArrayCallBacks_VH: VarHandle by lazy { kCFTypeArrayCallBacks_LAYOUT.varHandle() }

var kCFTypeArrayCallBacks: MemorySegment
    get() = kCFTypeArrayCallBacks_VH.get(kCFTypeArrayCallBacks_SEGMENT) as MemorySegment
    set(value) = kCFTypeArrayCallBacks_VH.set(kCFTypeArrayCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : CFArrayGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFArrayGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFArrayGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayGetTypeID").orElseThrow()
private val CFArrayGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayGetTypeID_ADDR, CFArrayGetTypeID_DESC)

fun CFArrayGetTypeID(): Long {
    try {
        return CFArrayGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayCreate typedef CFArrayRef = (Declared(__CFArray))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,((Void)*)*,typedef CFIndex = Long,(typedef CFArrayCallBacks = Declared(CFArrayCallBacks))*)
 */
private val CFArrayCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFArrayCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayCreate").orElseThrow()
private val CFArrayCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayCreate_ADDR, CFArrayCreate_DESC)

fun CFArrayCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CFArrayCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayCreateCopy typedef CFArrayRef = (Declared(__CFArray))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFArrayRef = (Declared(__CFArray))*)
 */
private val CFArrayCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFArrayCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayCreateCopy").orElseThrow()
private val CFArrayCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayCreateCopy_ADDR, CFArrayCreateCopy_DESC)

fun CFArrayCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFArrayCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayCreateMutable typedef CFMutableArrayRef = (Declared(__CFArray))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,(typedef CFArrayCallBacks = Declared(CFArrayCallBacks))*)
 */
private val CFArrayCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFArrayCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayCreateMutable").orElseThrow()
private val CFArrayCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayCreateMutable_ADDR, CFArrayCreateMutable_DESC)

fun CFArrayCreateMutable(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFArrayCreateMutable_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayCreateMutableCopy typedef CFMutableArrayRef = (Declared(__CFArray))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFArrayRef = (Declared(__CFArray))*)
 */
private val CFArrayCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFArrayCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayCreateMutableCopy").orElseThrow()
private val CFArrayCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayCreateMutableCopy_ADDR, CFArrayCreateMutableCopy_DESC)

fun CFArrayCreateMutableCopy(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFArrayCreateMutableCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayGetCount typedef CFIndex = Long(typedef CFArrayRef = (Declared(__CFArray))*)
 */
private val CFArrayGetCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFArrayGetCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayGetCount").orElseThrow()
private val CFArrayGetCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayGetCount_ADDR, CFArrayGetCount_DESC)

fun CFArrayGetCount(arg0: MemorySegment): Long {
    try {
        return CFArrayGetCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayGetCountOfValue typedef CFIndex = Long(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange),(Void)*)
 */
private val CFArrayGetCountOfValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFArrayGetCountOfValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayGetCountOfValue").orElseThrow()
private val CFArrayGetCountOfValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayGetCountOfValue_ADDR, CFArrayGetCountOfValue_DESC)

fun CFArrayGetCountOfValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Long {
    try {
        return CFArrayGetCountOfValue_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayContainsValue typedef Boolean = UNSIGNED = Char(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange),(Void)*)
 */
private val CFArrayContainsValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFArrayContainsValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayContainsValue").orElseThrow()
private val CFArrayContainsValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayContainsValue_ADDR, CFArrayContainsValue_DESC)

fun CFArrayContainsValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFArrayContainsValue_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayGetValueAtIndex (Void)*(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFIndex = Long)
 */
private val CFArrayGetValueAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFArrayGetValueAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayGetValueAtIndex").orElseThrow()
private val CFArrayGetValueAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayGetValueAtIndex_ADDR, CFArrayGetValueAtIndex_DESC)

fun CFArrayGetValueAtIndex(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return CFArrayGetValueAtIndex_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayGetValues Void(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange),((Void)*)*)
 */
private val CFArrayGetValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFArrayGetValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayGetValues").orElseThrow()
private val CFArrayGetValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayGetValues_ADDR, CFArrayGetValues_DESC)

fun CFArrayGetValues(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFArrayGetValues_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayApplyFunction Void(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange),typedef CFArrayApplierFunction = (Void((Void)*,(Void)*))*,(Void)*)
 */
private val CFArrayApplyFunction_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFArrayApplyFunction_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayApplyFunction").orElseThrow()
private val CFArrayApplyFunction_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayApplyFunction_ADDR, CFArrayApplyFunction_DESC)

fun CFArrayApplyFunction(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        CFArrayApplyFunction_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayGetFirstIndexOfValue typedef CFIndex = Long(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange),(Void)*)
 */
private val CFArrayGetFirstIndexOfValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFArrayGetFirstIndexOfValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayGetFirstIndexOfValue").orElseThrow()
private val CFArrayGetFirstIndexOfValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayGetFirstIndexOfValue_ADDR, CFArrayGetFirstIndexOfValue_DESC)

fun CFArrayGetFirstIndexOfValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Long {
    try {
        return CFArrayGetFirstIndexOfValue_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayGetLastIndexOfValue typedef CFIndex = Long(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange),(Void)*)
 */
private val CFArrayGetLastIndexOfValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFArrayGetLastIndexOfValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayGetLastIndexOfValue").orElseThrow()
private val CFArrayGetLastIndexOfValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayGetLastIndexOfValue_ADDR, CFArrayGetLastIndexOfValue_DESC)

fun CFArrayGetLastIndexOfValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Long {
    try {
        return CFArrayGetLastIndexOfValue_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayBSearchValues typedef CFIndex = Long(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange),(Void)*,typedef CFComparatorFunction = (<error: enum CFComparisonResult>((Void)*,(Void)*,(Void)*))*,(Void)*)
 */
private val CFArrayBSearchValues_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFArrayBSearchValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayBSearchValues").orElseThrow()
private val CFArrayBSearchValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayBSearchValues_ADDR, CFArrayBSearchValues_DESC)

fun CFArrayBSearchValues(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): Long {
    try {
        return CFArrayBSearchValues_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayAppendValue Void(typedef CFMutableArrayRef = (Declared(__CFArray))*,(Void)*)
 */
private val CFArrayAppendValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFArrayAppendValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayAppendValue").orElseThrow()
private val CFArrayAppendValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayAppendValue_ADDR, CFArrayAppendValue_DESC)

fun CFArrayAppendValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFArrayAppendValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayInsertValueAtIndex Void(typedef CFMutableArrayRef = (Declared(__CFArray))*,typedef CFIndex = Long,(Void)*)
 */
private val CFArrayInsertValueAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFArrayInsertValueAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayInsertValueAtIndex").orElseThrow()
private val CFArrayInsertValueAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayInsertValueAtIndex_ADDR, CFArrayInsertValueAtIndex_DESC)

fun CFArrayInsertValueAtIndex(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Unit {
    try {
        CFArrayInsertValueAtIndex_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArraySetValueAtIndex Void(typedef CFMutableArrayRef = (Declared(__CFArray))*,typedef CFIndex = Long,(Void)*)
 */
private val CFArraySetValueAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFArraySetValueAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArraySetValueAtIndex").orElseThrow()
private val CFArraySetValueAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArraySetValueAtIndex_ADDR, CFArraySetValueAtIndex_DESC)

fun CFArraySetValueAtIndex(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Unit {
    try {
        CFArraySetValueAtIndex_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayRemoveValueAtIndex Void(typedef CFMutableArrayRef = (Declared(__CFArray))*,typedef CFIndex = Long)
 */
private val CFArrayRemoveValueAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFArrayRemoveValueAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayRemoveValueAtIndex").orElseThrow()
private val CFArrayRemoveValueAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayRemoveValueAtIndex_ADDR, CFArrayRemoveValueAtIndex_DESC)

fun CFArrayRemoveValueAtIndex(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFArrayRemoveValueAtIndex_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayRemoveAllValues Void(typedef CFMutableArrayRef = (Declared(__CFArray))*)
 */
private val CFArrayRemoveAllValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFArrayRemoveAllValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayRemoveAllValues").orElseThrow()
private val CFArrayRemoveAllValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayRemoveAllValues_ADDR, CFArrayRemoveAllValues_DESC)

fun CFArrayRemoveAllValues(arg0: MemorySegment): Unit {
    try {
        CFArrayRemoveAllValues_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayReplaceValues Void(typedef CFMutableArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange),((Void)*)*,typedef CFIndex = Long)
 */
private val CFArrayReplaceValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFArrayReplaceValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayReplaceValues").orElseThrow()
private val CFArrayReplaceValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayReplaceValues_ADDR, CFArrayReplaceValues_DESC)

fun CFArrayReplaceValues(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long): Unit {
    try {
        CFArrayReplaceValues_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayExchangeValuesAtIndices Void(typedef CFMutableArrayRef = (Declared(__CFArray))*,typedef CFIndex = Long,typedef CFIndex = Long)
 */
private val CFArrayExchangeValuesAtIndices_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFArrayExchangeValuesAtIndices_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayExchangeValuesAtIndices").orElseThrow()
private val CFArrayExchangeValuesAtIndices_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayExchangeValuesAtIndices_ADDR, CFArrayExchangeValuesAtIndices_DESC)

fun CFArrayExchangeValuesAtIndices(arg0: MemorySegment, arg1: Long, arg2: Long): Unit {
    try {
        CFArrayExchangeValuesAtIndices_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArraySortValues Void(typedef CFMutableArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange),typedef CFComparatorFunction = (<error: enum CFComparisonResult>((Void)*,(Void)*,(Void)*))*,(Void)*)
 */
private val CFArraySortValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFArraySortValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArraySortValues").orElseThrow()
private val CFArraySortValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArraySortValues_ADDR, CFArraySortValues_DESC)

fun CFArraySortValues(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        CFArraySortValues_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFArrayAppendArray Void(typedef CFMutableArrayRef = (Declared(__CFArray))*,typedef CFArrayRef = (Declared(__CFArray))*,typedef CFRange = Declared(CFRange))
 */
private val CFArrayAppendArray_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CFRange.layout)
private val CFArrayAppendArray_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFArrayAppendArray").orElseThrow()
private val CFArrayAppendArray_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFArrayAppendArray_ADDR, CFArrayAppendArray_DESC)

fun CFArrayAppendArray(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFArrayAppendArray_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFTypeBagCallBacks typedef const CFBagCallBacks = Declared(CFBagCallBacks)
 */
private val kCFTypeBagCallBacks_LAYOUT: MemoryLayout by lazy { CFBagCallBacks.layout }
private val kCFTypeBagCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFTypeBagCallBacks").orElseThrow() }
private val kCFTypeBagCallBacks_VH: VarHandle by lazy { kCFTypeBagCallBacks_LAYOUT.varHandle() }

var kCFTypeBagCallBacks: MemorySegment
    get() = kCFTypeBagCallBacks_VH.get(kCFTypeBagCallBacks_SEGMENT) as MemorySegment
    set(value) = kCFTypeBagCallBacks_VH.set(kCFTypeBagCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : kCFCopyStringBagCallBacks typedef const CFBagCallBacks = Declared(CFBagCallBacks)
 */
private val kCFCopyStringBagCallBacks_LAYOUT: MemoryLayout by lazy { CFBagCallBacks.layout }
private val kCFCopyStringBagCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFCopyStringBagCallBacks").orElseThrow() }
private val kCFCopyStringBagCallBacks_VH: VarHandle by lazy { kCFCopyStringBagCallBacks_LAYOUT.varHandle() }

var kCFCopyStringBagCallBacks: MemorySegment
    get() = kCFCopyStringBagCallBacks_VH.get(kCFCopyStringBagCallBacks_SEGMENT) as MemorySegment
    set(value) = kCFCopyStringBagCallBacks_VH.set(kCFCopyStringBagCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : CFBagGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFBagGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFBagGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagGetTypeID").orElseThrow()
private val CFBagGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagGetTypeID_ADDR, CFBagGetTypeID_DESC)

fun CFBagGetTypeID(): Long {
    try {
        return CFBagGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagCreate typedef CFBagRef = (Declared(__CFBag))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,((Void)*)*,typedef CFIndex = Long,(typedef CFBagCallBacks = Declared(CFBagCallBacks))*)
 */
private val CFBagCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFBagCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagCreate").orElseThrow()
private val CFBagCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagCreate_ADDR, CFBagCreate_DESC)

fun CFBagCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CFBagCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagCreateCopy typedef CFBagRef = (Declared(__CFBag))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFBagRef = (Declared(__CFBag))*)
 */
private val CFBagCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagCreateCopy").orElseThrow()
private val CFBagCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagCreateCopy_ADDR, CFBagCreateCopy_DESC)

fun CFBagCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFBagCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagCreateMutable typedef CFMutableBagRef = (Declared(__CFBag))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,(typedef CFBagCallBacks = Declared(CFBagCallBacks))*)
 */
private val CFBagCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFBagCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagCreateMutable").orElseThrow()
private val CFBagCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagCreateMutable_ADDR, CFBagCreateMutable_DESC)

fun CFBagCreateMutable(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFBagCreateMutable_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagCreateMutableCopy typedef CFMutableBagRef = (Declared(__CFBag))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFBagRef = (Declared(__CFBag))*)
 */
private val CFBagCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFBagCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagCreateMutableCopy").orElseThrow()
private val CFBagCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagCreateMutableCopy_ADDR, CFBagCreateMutableCopy_DESC)

fun CFBagCreateMutableCopy(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFBagCreateMutableCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagGetCount typedef CFIndex = Long(typedef CFBagRef = (Declared(__CFBag))*)
 */
private val CFBagGetCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFBagGetCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagGetCount").orElseThrow()
private val CFBagGetCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagGetCount_ADDR, CFBagGetCount_DESC)

fun CFBagGetCount(arg0: MemorySegment): Long {
    try {
        return CFBagGetCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagGetCountOfValue typedef CFIndex = Long(typedef CFBagRef = (Declared(__CFBag))*,(Void)*)
 */
private val CFBagGetCountOfValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagGetCountOfValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagGetCountOfValue").orElseThrow()
private val CFBagGetCountOfValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagGetCountOfValue_ADDR, CFBagGetCountOfValue_DESC)

fun CFBagGetCountOfValue(arg0: MemorySegment, arg1: MemorySegment): Long {
    try {
        return CFBagGetCountOfValue_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagContainsValue typedef Boolean = UNSIGNED = Char(typedef CFBagRef = (Declared(__CFBag))*,(Void)*)
 */
private val CFBagContainsValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagContainsValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagContainsValue").orElseThrow()
private val CFBagContainsValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagContainsValue_ADDR, CFBagContainsValue_DESC)

fun CFBagContainsValue(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFBagContainsValue_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagGetValue (Void)*(typedef CFBagRef = (Declared(__CFBag))*,(Void)*)
 */
private val CFBagGetValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagGetValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagGetValue").orElseThrow()
private val CFBagGetValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagGetValue_ADDR, CFBagGetValue_DESC)

fun CFBagGetValue(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFBagGetValue_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagGetValueIfPresent typedef Boolean = UNSIGNED = Char(typedef CFBagRef = (Declared(__CFBag))*,(Void)*,((Void)*)*)
 */
private val CFBagGetValueIfPresent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagGetValueIfPresent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagGetValueIfPresent").orElseThrow()
private val CFBagGetValueIfPresent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagGetValueIfPresent_ADDR, CFBagGetValueIfPresent_DESC)

fun CFBagGetValueIfPresent(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFBagGetValueIfPresent_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagGetValues Void(typedef CFBagRef = (Declared(__CFBag))*,((Void)*)*)
 */
private val CFBagGetValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagGetValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagGetValues").orElseThrow()
private val CFBagGetValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagGetValues_ADDR, CFBagGetValues_DESC)

fun CFBagGetValues(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFBagGetValues_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagApplyFunction Void(typedef CFBagRef = (Declared(__CFBag))*,typedef CFBagApplierFunction = (Void((Void)*,(Void)*))*,(Void)*)
 */
private val CFBagApplyFunction_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagApplyFunction_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagApplyFunction").orElseThrow()
private val CFBagApplyFunction_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagApplyFunction_ADDR, CFBagApplyFunction_DESC)

fun CFBagApplyFunction(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFBagApplyFunction_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagAddValue Void(typedef CFMutableBagRef = (Declared(__CFBag))*,(Void)*)
 */
private val CFBagAddValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagAddValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagAddValue").orElseThrow()
private val CFBagAddValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagAddValue_ADDR, CFBagAddValue_DESC)

fun CFBagAddValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFBagAddValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagReplaceValue Void(typedef CFMutableBagRef = (Declared(__CFBag))*,(Void)*)
 */
private val CFBagReplaceValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagReplaceValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagReplaceValue").orElseThrow()
private val CFBagReplaceValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagReplaceValue_ADDR, CFBagReplaceValue_DESC)

fun CFBagReplaceValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFBagReplaceValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagSetValue Void(typedef CFMutableBagRef = (Declared(__CFBag))*,(Void)*)
 */
private val CFBagSetValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagSetValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagSetValue").orElseThrow()
private val CFBagSetValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagSetValue_ADDR, CFBagSetValue_DESC)

fun CFBagSetValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFBagSetValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagRemoveValue Void(typedef CFMutableBagRef = (Declared(__CFBag))*,(Void)*)
 */
private val CFBagRemoveValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBagRemoveValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagRemoveValue").orElseThrow()
private val CFBagRemoveValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagRemoveValue_ADDR, CFBagRemoveValue_DESC)

fun CFBagRemoveValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFBagRemoveValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBagRemoveAllValues Void(typedef CFMutableBagRef = (Declared(__CFBag))*)
 */
private val CFBagRemoveAllValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFBagRemoveAllValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBagRemoveAllValues").orElseThrow()
private val CFBagRemoveAllValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBagRemoveAllValues_ADDR, CFBagRemoveAllValues_DESC)

fun CFBagRemoveAllValues(arg0: MemorySegment): Unit {
    try {
        CFBagRemoveAllValues_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFStringBinaryHeapCallBacks typedef const CFBinaryHeapCallBacks = Declared(CFBinaryHeapCallBacks)
 */
private val kCFStringBinaryHeapCallBacks_LAYOUT: MemoryLayout by lazy { CFBinaryHeapCallBacks.layout }
private val kCFStringBinaryHeapCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringBinaryHeapCallBacks").orElseThrow() }
private val kCFStringBinaryHeapCallBacks_VH: VarHandle by lazy { kCFStringBinaryHeapCallBacks_LAYOUT.varHandle() }

var kCFStringBinaryHeapCallBacks: MemorySegment
    get() = kCFStringBinaryHeapCallBacks_VH.get(kCFStringBinaryHeapCallBacks_SEGMENT) as MemorySegment
    set(value) = kCFStringBinaryHeapCallBacks_VH.set(kCFStringBinaryHeapCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : CFBinaryHeapGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFBinaryHeapGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFBinaryHeapGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapGetTypeID").orElseThrow()
private val CFBinaryHeapGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapGetTypeID_ADDR, CFBinaryHeapGetTypeID_DESC)

fun CFBinaryHeapGetTypeID(): Long {
    try {
        return CFBinaryHeapGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapCreate typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,(typedef CFBinaryHeapCallBacks = Declared(CFBinaryHeapCallBacks))*,(typedef CFBinaryHeapCompareContext = Declared(CFBinaryHeapCompareContext))*)
 */
private val CFBinaryHeapCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBinaryHeapCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapCreate").orElseThrow()
private val CFBinaryHeapCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapCreate_ADDR, CFBinaryHeapCreate_DESC)

fun CFBinaryHeapCreate(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFBinaryHeapCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapCreateCopy typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*)
 */
private val CFBinaryHeapCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFBinaryHeapCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapCreateCopy").orElseThrow()
private val CFBinaryHeapCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapCreateCopy_ADDR, CFBinaryHeapCreateCopy_DESC)

fun CFBinaryHeapCreateCopy(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFBinaryHeapCreateCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapGetCount typedef CFIndex = Long(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*)
 */
private val CFBinaryHeapGetCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFBinaryHeapGetCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapGetCount").orElseThrow()
private val CFBinaryHeapGetCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapGetCount_ADDR, CFBinaryHeapGetCount_DESC)

fun CFBinaryHeapGetCount(arg0: MemorySegment): Long {
    try {
        return CFBinaryHeapGetCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapGetCountOfValue typedef CFIndex = Long(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*,(Void)*)
 */
private val CFBinaryHeapGetCountOfValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBinaryHeapGetCountOfValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapGetCountOfValue").orElseThrow()
private val CFBinaryHeapGetCountOfValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapGetCountOfValue_ADDR, CFBinaryHeapGetCountOfValue_DESC)

fun CFBinaryHeapGetCountOfValue(arg0: MemorySegment, arg1: MemorySegment): Long {
    try {
        return CFBinaryHeapGetCountOfValue_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapContainsValue typedef Boolean = UNSIGNED = Char(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*,(Void)*)
 */
private val CFBinaryHeapContainsValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBinaryHeapContainsValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapContainsValue").orElseThrow()
private val CFBinaryHeapContainsValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapContainsValue_ADDR, CFBinaryHeapContainsValue_DESC)

fun CFBinaryHeapContainsValue(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFBinaryHeapContainsValue_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapGetMinimum (Void)*(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*)
 */
private val CFBinaryHeapGetMinimum_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBinaryHeapGetMinimum_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapGetMinimum").orElseThrow()
private val CFBinaryHeapGetMinimum_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapGetMinimum_ADDR, CFBinaryHeapGetMinimum_DESC)

fun CFBinaryHeapGetMinimum(arg0: MemorySegment): MemorySegment {
    try {
        return CFBinaryHeapGetMinimum_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapGetMinimumIfPresent typedef Boolean = UNSIGNED = Char(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*,((Void)*)*)
 */
private val CFBinaryHeapGetMinimumIfPresent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBinaryHeapGetMinimumIfPresent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapGetMinimumIfPresent").orElseThrow()
private val CFBinaryHeapGetMinimumIfPresent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapGetMinimumIfPresent_ADDR, CFBinaryHeapGetMinimumIfPresent_DESC)

fun CFBinaryHeapGetMinimumIfPresent(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFBinaryHeapGetMinimumIfPresent_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapGetValues Void(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*,((Void)*)*)
 */
private val CFBinaryHeapGetValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBinaryHeapGetValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapGetValues").orElseThrow()
private val CFBinaryHeapGetValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapGetValues_ADDR, CFBinaryHeapGetValues_DESC)

fun CFBinaryHeapGetValues(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFBinaryHeapGetValues_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapApplyFunction Void(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*,typedef CFBinaryHeapApplierFunction = (Void((Void)*,(Void)*))*,(Void)*)
 */
private val CFBinaryHeapApplyFunction_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBinaryHeapApplyFunction_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapApplyFunction").orElseThrow()
private val CFBinaryHeapApplyFunction_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapApplyFunction_ADDR, CFBinaryHeapApplyFunction_DESC)

fun CFBinaryHeapApplyFunction(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFBinaryHeapApplyFunction_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapAddValue Void(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*,(Void)*)
 */
private val CFBinaryHeapAddValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBinaryHeapAddValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapAddValue").orElseThrow()
private val CFBinaryHeapAddValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapAddValue_ADDR, CFBinaryHeapAddValue_DESC)

fun CFBinaryHeapAddValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFBinaryHeapAddValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapRemoveMinimumValue Void(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*)
 */
private val CFBinaryHeapRemoveMinimumValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFBinaryHeapRemoveMinimumValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapRemoveMinimumValue").orElseThrow()
private val CFBinaryHeapRemoveMinimumValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapRemoveMinimumValue_ADDR, CFBinaryHeapRemoveMinimumValue_DESC)

fun CFBinaryHeapRemoveMinimumValue(arg0: MemorySegment): Unit {
    try {
        CFBinaryHeapRemoveMinimumValue_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBinaryHeapRemoveAllValues Void(typedef CFBinaryHeapRef = (Declared(__CFBinaryHeap))*)
 */
private val CFBinaryHeapRemoveAllValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFBinaryHeapRemoveAllValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBinaryHeapRemoveAllValues").orElseThrow()
private val CFBinaryHeapRemoveAllValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBinaryHeapRemoveAllValues_ADDR, CFBinaryHeapRemoveAllValues_DESC)

fun CFBinaryHeapRemoveAllValues(arg0: MemorySegment): Unit {
    try {
        CFBinaryHeapRemoveAllValues_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFBitVectorGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFBitVectorGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorGetTypeID").orElseThrow()
private val CFBitVectorGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorGetTypeID_ADDR, CFBitVectorGetTypeID_DESC)

fun CFBitVectorGetTypeID(): Long {
    try {
        return CFBitVectorGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorCreate typedef CFBitVectorRef = (Declared(__CFBitVector))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long)
 */
private val CFBitVectorCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFBitVectorCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorCreate").orElseThrow()
private val CFBitVectorCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorCreate_ADDR, CFBitVectorCreate_DESC)

fun CFBitVectorCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFBitVectorCreate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorCreateCopy typedef CFBitVectorRef = (Declared(__CFBitVector))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFBitVectorRef = (Declared(__CFBitVector))*)
 */
private val CFBitVectorCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBitVectorCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorCreateCopy").orElseThrow()
private val CFBitVectorCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorCreateCopy_ADDR, CFBitVectorCreateCopy_DESC)

fun CFBitVectorCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFBitVectorCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorCreateMutable typedef CFMutableBitVectorRef = (Declared(__CFBitVector))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long)
 */
private val CFBitVectorCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFBitVectorCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorCreateMutable").orElseThrow()
private val CFBitVectorCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorCreateMutable_ADDR, CFBitVectorCreateMutable_DESC)

fun CFBitVectorCreateMutable(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return CFBitVectorCreateMutable_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorCreateMutableCopy typedef CFMutableBitVectorRef = (Declared(__CFBitVector))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFBitVectorRef = (Declared(__CFBitVector))*)
 */
private val CFBitVectorCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFBitVectorCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorCreateMutableCopy").orElseThrow()
private val CFBitVectorCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorCreateMutableCopy_ADDR, CFBitVectorCreateMutableCopy_DESC)

fun CFBitVectorCreateMutableCopy(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFBitVectorCreateMutableCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorGetCount typedef CFIndex = Long(typedef CFBitVectorRef = (Declared(__CFBitVector))*)
 */
private val CFBitVectorGetCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFBitVectorGetCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorGetCount").orElseThrow()
private val CFBitVectorGetCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorGetCount_ADDR, CFBitVectorGetCount_DESC)

fun CFBitVectorGetCount(arg0: MemorySegment): Long {
    try {
        return CFBitVectorGetCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorGetCountOfBit typedef CFIndex = Long(typedef CFBitVectorRef = (Declared(__CFBitVector))*,typedef CFRange = Declared(CFRange),typedef CFBit = UNSIGNED = Int)
 */
private val CFBitVectorGetCountOfBit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.JAVA_INT)
private val CFBitVectorGetCountOfBit_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorGetCountOfBit").orElseThrow()
private val CFBitVectorGetCountOfBit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorGetCountOfBit_ADDR, CFBitVectorGetCountOfBit_DESC)

fun CFBitVectorGetCountOfBit(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Long {
    try {
        return CFBitVectorGetCountOfBit_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorContainsBit typedef Boolean = UNSIGNED = Char(typedef CFBitVectorRef = (Declared(__CFBitVector))*,typedef CFRange = Declared(CFRange),typedef CFBit = UNSIGNED = Int)
 */
private val CFBitVectorContainsBit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.JAVA_INT)
private val CFBitVectorContainsBit_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorContainsBit").orElseThrow()
private val CFBitVectorContainsBit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorContainsBit_ADDR, CFBitVectorContainsBit_DESC)

fun CFBitVectorContainsBit(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Byte {
    try {
        return CFBitVectorContainsBit_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorGetBitAtIndex typedef CFBit = UNSIGNED = Int(typedef CFBitVectorRef = (Declared(__CFBitVector))*,typedef CFIndex = Long)
 */
private val CFBitVectorGetBitAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFBitVectorGetBitAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorGetBitAtIndex").orElseThrow()
private val CFBitVectorGetBitAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorGetBitAtIndex_ADDR, CFBitVectorGetBitAtIndex_DESC)

fun CFBitVectorGetBitAtIndex(arg0: MemorySegment, arg1: Long): Int {
    try {
        return CFBitVectorGetBitAtIndex_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorGetBits Void(typedef CFBitVectorRef = (Declared(__CFBitVector))*,typedef CFRange = Declared(CFRange),(typedef UInt8 = UNSIGNED = Char)*)
 */
private val CFBitVectorGetBits_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFBitVectorGetBits_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorGetBits").orElseThrow()
private val CFBitVectorGetBits_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorGetBits_ADDR, CFBitVectorGetBits_DESC)

fun CFBitVectorGetBits(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFBitVectorGetBits_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorGetFirstIndexOfBit typedef CFIndex = Long(typedef CFBitVectorRef = (Declared(__CFBitVector))*,typedef CFRange = Declared(CFRange),typedef CFBit = UNSIGNED = Int)
 */
private val CFBitVectorGetFirstIndexOfBit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.JAVA_INT)
private val CFBitVectorGetFirstIndexOfBit_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorGetFirstIndexOfBit").orElseThrow()
private val CFBitVectorGetFirstIndexOfBit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorGetFirstIndexOfBit_ADDR, CFBitVectorGetFirstIndexOfBit_DESC)

fun CFBitVectorGetFirstIndexOfBit(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Long {
    try {
        return CFBitVectorGetFirstIndexOfBit_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorGetLastIndexOfBit typedef CFIndex = Long(typedef CFBitVectorRef = (Declared(__CFBitVector))*,typedef CFRange = Declared(CFRange),typedef CFBit = UNSIGNED = Int)
 */
private val CFBitVectorGetLastIndexOfBit_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.JAVA_INT)
private val CFBitVectorGetLastIndexOfBit_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorGetLastIndexOfBit").orElseThrow()
private val CFBitVectorGetLastIndexOfBit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorGetLastIndexOfBit_ADDR, CFBitVectorGetLastIndexOfBit_DESC)

fun CFBitVectorGetLastIndexOfBit(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Long {
    try {
        return CFBitVectorGetLastIndexOfBit_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorSetCount Void(typedef CFMutableBitVectorRef = (Declared(__CFBitVector))*,typedef CFIndex = Long)
 */
private val CFBitVectorSetCount_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFBitVectorSetCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorSetCount").orElseThrow()
private val CFBitVectorSetCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorSetCount_ADDR, CFBitVectorSetCount_DESC)

fun CFBitVectorSetCount(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFBitVectorSetCount_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorFlipBitAtIndex Void(typedef CFMutableBitVectorRef = (Declared(__CFBitVector))*,typedef CFIndex = Long)
 */
private val CFBitVectorFlipBitAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFBitVectorFlipBitAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorFlipBitAtIndex").orElseThrow()
private val CFBitVectorFlipBitAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorFlipBitAtIndex_ADDR, CFBitVectorFlipBitAtIndex_DESC)

fun CFBitVectorFlipBitAtIndex(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFBitVectorFlipBitAtIndex_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorFlipBits Void(typedef CFMutableBitVectorRef = (Declared(__CFBitVector))*,typedef CFRange = Declared(CFRange))
 */
private val CFBitVectorFlipBits_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout)
private val CFBitVectorFlipBits_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorFlipBits").orElseThrow()
private val CFBitVectorFlipBits_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorFlipBits_ADDR, CFBitVectorFlipBits_DESC)

fun CFBitVectorFlipBits(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFBitVectorFlipBits_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorSetBitAtIndex Void(typedef CFMutableBitVectorRef = (Declared(__CFBitVector))*,typedef CFIndex = Long,typedef CFBit = UNSIGNED = Int)
 */
private val CFBitVectorSetBitAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT)
private val CFBitVectorSetBitAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorSetBitAtIndex").orElseThrow()
private val CFBitVectorSetBitAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorSetBitAtIndex_ADDR, CFBitVectorSetBitAtIndex_DESC)

fun CFBitVectorSetBitAtIndex(arg0: MemorySegment, arg1: Long, arg2: Int): Unit {
    try {
        CFBitVectorSetBitAtIndex_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorSetBits Void(typedef CFMutableBitVectorRef = (Declared(__CFBitVector))*,typedef CFRange = Declared(CFRange),typedef CFBit = UNSIGNED = Int)
 */
private val CFBitVectorSetBits_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.JAVA_INT)
private val CFBitVectorSetBits_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorSetBits").orElseThrow()
private val CFBitVectorSetBits_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorSetBits_ADDR, CFBitVectorSetBits_DESC)

fun CFBitVectorSetBits(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Unit {
    try {
        CFBitVectorSetBits_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBitVectorSetAllBits Void(typedef CFMutableBitVectorRef = (Declared(__CFBitVector))*,typedef CFBit = UNSIGNED = Int)
 */
private val CFBitVectorSetAllBits_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFBitVectorSetAllBits_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBitVectorSetAllBits").orElseThrow()
private val CFBitVectorSetAllBits_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBitVectorSetAllBits_ADDR, CFBitVectorSetAllBits_DESC)

fun CFBitVectorSetAllBits(arg0: MemorySegment, arg1: Int): Unit {
    try {
        CFBitVectorSetAllBits_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFTypeDictionaryKeyCallBacks typedef const CFDictionaryKeyCallBacks = Declared(CFDictionaryKeyCallBacks)
 */
private val kCFTypeDictionaryKeyCallBacks_LAYOUT: MemoryLayout by lazy { CFDictionaryKeyCallBacks.layout }
private val kCFTypeDictionaryKeyCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFTypeDictionaryKeyCallBacks").orElseThrow() }
private val kCFTypeDictionaryKeyCallBacks_VH: VarHandle by lazy { kCFTypeDictionaryKeyCallBacks_LAYOUT.varHandle() }

var kCFTypeDictionaryKeyCallBacks: MemorySegment
    get() = kCFTypeDictionaryKeyCallBacks_VH.get(kCFTypeDictionaryKeyCallBacks_SEGMENT) as MemorySegment
    set(value) = kCFTypeDictionaryKeyCallBacks_VH.set(kCFTypeDictionaryKeyCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : kCFCopyStringDictionaryKeyCallBacks typedef const CFDictionaryKeyCallBacks = Declared(CFDictionaryKeyCallBacks)
 */
private val kCFCopyStringDictionaryKeyCallBacks_LAYOUT: MemoryLayout by lazy { CFDictionaryKeyCallBacks.layout }
private val kCFCopyStringDictionaryKeyCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFCopyStringDictionaryKeyCallBacks").orElseThrow() }
private val kCFCopyStringDictionaryKeyCallBacks_VH: VarHandle by lazy { kCFCopyStringDictionaryKeyCallBacks_LAYOUT.varHandle() }

var kCFCopyStringDictionaryKeyCallBacks: MemorySegment
    get() = kCFCopyStringDictionaryKeyCallBacks_VH.get(kCFCopyStringDictionaryKeyCallBacks_SEGMENT) as MemorySegment
    set(value) = kCFCopyStringDictionaryKeyCallBacks_VH.set(kCFCopyStringDictionaryKeyCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : kCFTypeDictionaryValueCallBacks typedef const CFDictionaryValueCallBacks = Declared(CFDictionaryValueCallBacks)
 */
private val kCFTypeDictionaryValueCallBacks_LAYOUT: MemoryLayout by lazy { CFDictionaryValueCallBacks.layout }
private val kCFTypeDictionaryValueCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFTypeDictionaryValueCallBacks").orElseThrow() }
private val kCFTypeDictionaryValueCallBacks_VH: VarHandle by lazy { kCFTypeDictionaryValueCallBacks_LAYOUT.varHandle() }

var kCFTypeDictionaryValueCallBacks: MemorySegment
    get() = kCFTypeDictionaryValueCallBacks_VH.get(kCFTypeDictionaryValueCallBacks_SEGMENT) as MemorySegment
    set(value) = kCFTypeDictionaryValueCallBacks_VH.set(kCFTypeDictionaryValueCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : CFDictionaryGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFDictionaryGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFDictionaryGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryGetTypeID").orElseThrow()
private val CFDictionaryGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryGetTypeID_ADDR, CFDictionaryGetTypeID_DESC)

fun CFDictionaryGetTypeID(): Long {
    try {
        return CFDictionaryGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryCreate typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,((Void)*)*,((Void)*)*,typedef CFIndex = Long,(typedef CFDictionaryKeyCallBacks = Declared(CFDictionaryKeyCallBacks))*,(typedef CFDictionaryValueCallBacks = Declared(CFDictionaryValueCallBacks))*)
 */
private val CFDictionaryCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryCreate").orElseThrow()
private val CFDictionaryCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryCreate_ADDR, CFDictionaryCreate_DESC)

fun CFDictionaryCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long, arg4: MemorySegment, arg5: MemorySegment): MemorySegment {
    try {
        return CFDictionaryCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryCreateCopy typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFDictionaryCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryCreateCopy").orElseThrow()
private val CFDictionaryCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryCreateCopy_ADDR, CFDictionaryCreateCopy_DESC)

fun CFDictionaryCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFDictionaryCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryCreateMutable typedef CFMutableDictionaryRef = (Declared(__CFDictionary))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,(typedef CFDictionaryKeyCallBacks = Declared(CFDictionaryKeyCallBacks))*,(typedef CFDictionaryValueCallBacks = Declared(CFDictionaryValueCallBacks))*)
 */
private val CFDictionaryCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryCreateMutable").orElseThrow()
private val CFDictionaryCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryCreateMutable_ADDR, CFDictionaryCreateMutable_DESC)

fun CFDictionaryCreateMutable(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFDictionaryCreateMutable_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryCreateMutableCopy typedef CFMutableDictionaryRef = (Declared(__CFDictionary))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFDictionaryCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFDictionaryCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryCreateMutableCopy").orElseThrow()
private val CFDictionaryCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryCreateMutableCopy_ADDR, CFDictionaryCreateMutableCopy_DESC)

fun CFDictionaryCreateMutableCopy(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFDictionaryCreateMutableCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryGetCount typedef CFIndex = Long(typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFDictionaryGetCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFDictionaryGetCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryGetCount").orElseThrow()
private val CFDictionaryGetCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryGetCount_ADDR, CFDictionaryGetCount_DESC)

fun CFDictionaryGetCount(arg0: MemorySegment): Long {
    try {
        return CFDictionaryGetCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryGetCountOfKey typedef CFIndex = Long(typedef CFDictionaryRef = (Declared(__CFDictionary))*,(Void)*)
 */
private val CFDictionaryGetCountOfKey_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryGetCountOfKey_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryGetCountOfKey").orElseThrow()
private val CFDictionaryGetCountOfKey_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryGetCountOfKey_ADDR, CFDictionaryGetCountOfKey_DESC)

fun CFDictionaryGetCountOfKey(arg0: MemorySegment, arg1: MemorySegment): Long {
    try {
        return CFDictionaryGetCountOfKey_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryGetCountOfValue typedef CFIndex = Long(typedef CFDictionaryRef = (Declared(__CFDictionary))*,(Void)*)
 */
private val CFDictionaryGetCountOfValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryGetCountOfValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryGetCountOfValue").orElseThrow()
private val CFDictionaryGetCountOfValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryGetCountOfValue_ADDR, CFDictionaryGetCountOfValue_DESC)

fun CFDictionaryGetCountOfValue(arg0: MemorySegment, arg1: MemorySegment): Long {
    try {
        return CFDictionaryGetCountOfValue_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryContainsKey typedef Boolean = UNSIGNED = Char(typedef CFDictionaryRef = (Declared(__CFDictionary))*,(Void)*)
 */
private val CFDictionaryContainsKey_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryContainsKey_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryContainsKey").orElseThrow()
private val CFDictionaryContainsKey_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryContainsKey_ADDR, CFDictionaryContainsKey_DESC)

fun CFDictionaryContainsKey(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFDictionaryContainsKey_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryContainsValue typedef Boolean = UNSIGNED = Char(typedef CFDictionaryRef = (Declared(__CFDictionary))*,(Void)*)
 */
private val CFDictionaryContainsValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryContainsValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryContainsValue").orElseThrow()
private val CFDictionaryContainsValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryContainsValue_ADDR, CFDictionaryContainsValue_DESC)

fun CFDictionaryContainsValue(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFDictionaryContainsValue_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryGetValue (Void)*(typedef CFDictionaryRef = (Declared(__CFDictionary))*,(Void)*)
 */
private val CFDictionaryGetValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryGetValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryGetValue").orElseThrow()
private val CFDictionaryGetValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryGetValue_ADDR, CFDictionaryGetValue_DESC)

fun CFDictionaryGetValue(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFDictionaryGetValue_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryGetValueIfPresent typedef Boolean = UNSIGNED = Char(typedef CFDictionaryRef = (Declared(__CFDictionary))*,(Void)*,((Void)*)*)
 */
private val CFDictionaryGetValueIfPresent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryGetValueIfPresent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryGetValueIfPresent").orElseThrow()
private val CFDictionaryGetValueIfPresent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryGetValueIfPresent_ADDR, CFDictionaryGetValueIfPresent_DESC)

fun CFDictionaryGetValueIfPresent(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFDictionaryGetValueIfPresent_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryGetKeysAndValues Void(typedef CFDictionaryRef = (Declared(__CFDictionary))*,((Void)*)*,((Void)*)*)
 */
private val CFDictionaryGetKeysAndValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryGetKeysAndValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryGetKeysAndValues").orElseThrow()
private val CFDictionaryGetKeysAndValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryGetKeysAndValues_ADDR, CFDictionaryGetKeysAndValues_DESC)

fun CFDictionaryGetKeysAndValues(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFDictionaryGetKeysAndValues_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryApplyFunction Void(typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CFDictionaryApplierFunction = (Void((Void)*,(Void)*,(Void)*))*,(Void)*)
 */
private val CFDictionaryApplyFunction_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryApplyFunction_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryApplyFunction").orElseThrow()
private val CFDictionaryApplyFunction_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryApplyFunction_ADDR, CFDictionaryApplyFunction_DESC)

fun CFDictionaryApplyFunction(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFDictionaryApplyFunction_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryAddValue Void(typedef CFMutableDictionaryRef = (Declared(__CFDictionary))*,(Void)*,(Void)*)
 */
private val CFDictionaryAddValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryAddValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryAddValue").orElseThrow()
private val CFDictionaryAddValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryAddValue_ADDR, CFDictionaryAddValue_DESC)

fun CFDictionaryAddValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFDictionaryAddValue_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionarySetValue Void(typedef CFMutableDictionaryRef = (Declared(__CFDictionary))*,(Void)*,(Void)*)
 */
private val CFDictionarySetValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionarySetValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionarySetValue").orElseThrow()
private val CFDictionarySetValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionarySetValue_ADDR, CFDictionarySetValue_DESC)

fun CFDictionarySetValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFDictionarySetValue_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryReplaceValue Void(typedef CFMutableDictionaryRef = (Declared(__CFDictionary))*,(Void)*,(Void)*)
 */
private val CFDictionaryReplaceValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryReplaceValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryReplaceValue").orElseThrow()
private val CFDictionaryReplaceValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryReplaceValue_ADDR, CFDictionaryReplaceValue_DESC)

fun CFDictionaryReplaceValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFDictionaryReplaceValue_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryRemoveValue Void(typedef CFMutableDictionaryRef = (Declared(__CFDictionary))*,(Void)*)
 */
private val CFDictionaryRemoveValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDictionaryRemoveValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryRemoveValue").orElseThrow()
private val CFDictionaryRemoveValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryRemoveValue_ADDR, CFDictionaryRemoveValue_DESC)

fun CFDictionaryRemoveValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFDictionaryRemoveValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDictionaryRemoveAllValues Void(typedef CFMutableDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFDictionaryRemoveAllValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFDictionaryRemoveAllValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDictionaryRemoveAllValues").orElseThrow()
private val CFDictionaryRemoveAllValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDictionaryRemoveAllValues_ADDR, CFDictionaryRemoveAllValues_DESC)

fun CFDictionaryRemoveAllValues(arg0: MemorySegment): Unit {
    try {
        CFDictionaryRemoveAllValues_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNotificationCenterGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFNotificationCenterGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFNotificationCenterGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNotificationCenterGetTypeID").orElseThrow()
private val CFNotificationCenterGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNotificationCenterGetTypeID_ADDR, CFNotificationCenterGetTypeID_DESC)

fun CFNotificationCenterGetTypeID(): Long {
    try {
        return CFNotificationCenterGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNotificationCenterGetLocalCenter typedef CFNotificationCenterRef = (Declared(__CFNotificationCenter))*()
 */
private val CFNotificationCenterGetLocalCenter_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFNotificationCenterGetLocalCenter_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNotificationCenterGetLocalCenter").orElseThrow()
private val CFNotificationCenterGetLocalCenter_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNotificationCenterGetLocalCenter_ADDR, CFNotificationCenterGetLocalCenter_DESC)

fun CFNotificationCenterGetLocalCenter(): MemorySegment {
    try {
        return CFNotificationCenterGetLocalCenter_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNotificationCenterGetDistributedCenter typedef CFNotificationCenterRef = (Declared(__CFNotificationCenter))*()
 */
private val CFNotificationCenterGetDistributedCenter_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFNotificationCenterGetDistributedCenter_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNotificationCenterGetDistributedCenter").orElseThrow()
private val CFNotificationCenterGetDistributedCenter_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNotificationCenterGetDistributedCenter_ADDR, CFNotificationCenterGetDistributedCenter_DESC)

fun CFNotificationCenterGetDistributedCenter(): MemorySegment {
    try {
        return CFNotificationCenterGetDistributedCenter_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNotificationCenterGetDarwinNotifyCenter typedef CFNotificationCenterRef = (Declared(__CFNotificationCenter))*()
 */
private val CFNotificationCenterGetDarwinNotifyCenter_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFNotificationCenterGetDarwinNotifyCenter_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNotificationCenterGetDarwinNotifyCenter").orElseThrow()
private val CFNotificationCenterGetDarwinNotifyCenter_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNotificationCenterGetDarwinNotifyCenter_ADDR, CFNotificationCenterGetDarwinNotifyCenter_DESC)

fun CFNotificationCenterGetDarwinNotifyCenter(): MemorySegment {
    try {
        return CFNotificationCenterGetDarwinNotifyCenter_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNotificationCenterRemoveObserver Void(typedef CFNotificationCenterRef = (Declared(__CFNotificationCenter))*,(Void)*,typedef CFNotificationName = (Declared(__CFString))*,(Void)*)
 */
private val CFNotificationCenterRemoveObserver_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFNotificationCenterRemoveObserver_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNotificationCenterRemoveObserver").orElseThrow()
private val CFNotificationCenterRemoveObserver_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNotificationCenterRemoveObserver_ADDR, CFNotificationCenterRemoveObserver_DESC)

fun CFNotificationCenterRemoveObserver(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        CFNotificationCenterRemoveObserver_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNotificationCenterRemoveEveryObserver Void(typedef CFNotificationCenterRef = (Declared(__CFNotificationCenter))*,(Void)*)
 */
private val CFNotificationCenterRemoveEveryObserver_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFNotificationCenterRemoveEveryObserver_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNotificationCenterRemoveEveryObserver").orElseThrow()
private val CFNotificationCenterRemoveEveryObserver_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNotificationCenterRemoveEveryObserver_ADDR, CFNotificationCenterRemoveEveryObserver_DESC)

fun CFNotificationCenterRemoveEveryObserver(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFNotificationCenterRemoveEveryObserver_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNotificationCenterPostNotification Void(typedef CFNotificationCenterRef = (Declared(__CFNotificationCenter))*,typedef CFNotificationName = (Declared(__CFString))*,(Void)*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef Boolean = UNSIGNED = Char)
 */
private val CFNotificationCenterPostNotification_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE)
private val CFNotificationCenterPostNotification_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNotificationCenterPostNotification").orElseThrow()
private val CFNotificationCenterPostNotification_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNotificationCenterPostNotification_ADDR, CFNotificationCenterPostNotification_DESC)

fun CFNotificationCenterPostNotification(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: Byte): Unit {
    try {
        CFNotificationCenterPostNotification_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNotificationCenterPostNotificationWithOptions Void(typedef CFNotificationCenterRef = (Declared(__CFNotificationCenter))*,typedef CFNotificationName = (Declared(__CFString))*,(Void)*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFNotificationCenterPostNotificationWithOptions_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFNotificationCenterPostNotificationWithOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNotificationCenterPostNotificationWithOptions").orElseThrow()
private val CFNotificationCenterPostNotificationWithOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNotificationCenterPostNotificationWithOptions_ADDR, CFNotificationCenterPostNotificationWithOptions_DESC)

fun CFNotificationCenterPostNotificationWithOptions(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: Long): Unit {
    try {
        CFNotificationCenterPostNotificationWithOptions_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFLocaleGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFLocaleGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleGetTypeID").orElseThrow()
private val CFLocaleGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleGetTypeID_ADDR, CFLocaleGetTypeID_DESC)

fun CFLocaleGetTypeID(): Long {
    try {
        return CFLocaleGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleGetSystem typedef CFLocaleRef = (Declared(__CFLocale))*()
 */
private val CFLocaleGetSystem_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFLocaleGetSystem_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleGetSystem").orElseThrow()
private val CFLocaleGetSystem_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleGetSystem_ADDR, CFLocaleGetSystem_DESC)

fun CFLocaleGetSystem(): MemorySegment {
    try {
        return CFLocaleGetSystem_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCopyCurrent typedef CFLocaleRef = (Declared(__CFLocale))*()
 */
private val CFLocaleCopyCurrent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFLocaleCopyCurrent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCopyCurrent").orElseThrow()
private val CFLocaleCopyCurrent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCopyCurrent_ADDR, CFLocaleCopyCurrent_DESC)

fun CFLocaleCopyCurrent(): MemorySegment {
    try {
        return CFLocaleCopyCurrent_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCopyAvailableLocaleIdentifiers typedef CFArrayRef = (Declared(__CFArray))*()
 */
private val CFLocaleCopyAvailableLocaleIdentifiers_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFLocaleCopyAvailableLocaleIdentifiers_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCopyAvailableLocaleIdentifiers").orElseThrow()
private val CFLocaleCopyAvailableLocaleIdentifiers_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCopyAvailableLocaleIdentifiers_ADDR, CFLocaleCopyAvailableLocaleIdentifiers_DESC)

fun CFLocaleCopyAvailableLocaleIdentifiers(): MemorySegment {
    try {
        return CFLocaleCopyAvailableLocaleIdentifiers_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCopyISOLanguageCodes typedef CFArrayRef = (Declared(__CFArray))*()
 */
private val CFLocaleCopyISOLanguageCodes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFLocaleCopyISOLanguageCodes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCopyISOLanguageCodes").orElseThrow()
private val CFLocaleCopyISOLanguageCodes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCopyISOLanguageCodes_ADDR, CFLocaleCopyISOLanguageCodes_DESC)

fun CFLocaleCopyISOLanguageCodes(): MemorySegment {
    try {
        return CFLocaleCopyISOLanguageCodes_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCopyISOCountryCodes typedef CFArrayRef = (Declared(__CFArray))*()
 */
private val CFLocaleCopyISOCountryCodes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFLocaleCopyISOCountryCodes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCopyISOCountryCodes").orElseThrow()
private val CFLocaleCopyISOCountryCodes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCopyISOCountryCodes_ADDR, CFLocaleCopyISOCountryCodes_DESC)

fun CFLocaleCopyISOCountryCodes(): MemorySegment {
    try {
        return CFLocaleCopyISOCountryCodes_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCopyISOCurrencyCodes typedef CFArrayRef = (Declared(__CFArray))*()
 */
private val CFLocaleCopyISOCurrencyCodes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFLocaleCopyISOCurrencyCodes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCopyISOCurrencyCodes").orElseThrow()
private val CFLocaleCopyISOCurrencyCodes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCopyISOCurrencyCodes_ADDR, CFLocaleCopyISOCurrencyCodes_DESC)

fun CFLocaleCopyISOCurrencyCodes(): MemorySegment {
    try {
        return CFLocaleCopyISOCurrencyCodes_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCopyCommonISOCurrencyCodes typedef CFArrayRef = (Declared(__CFArray))*()
 */
private val CFLocaleCopyCommonISOCurrencyCodes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFLocaleCopyCommonISOCurrencyCodes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCopyCommonISOCurrencyCodes").orElseThrow()
private val CFLocaleCopyCommonISOCurrencyCodes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCopyCommonISOCurrencyCodes_ADDR, CFLocaleCopyCommonISOCurrencyCodes_DESC)

fun CFLocaleCopyCommonISOCurrencyCodes(): MemorySegment {
    try {
        return CFLocaleCopyCommonISOCurrencyCodes_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCopyPreferredLanguages typedef CFArrayRef = (Declared(__CFArray))*()
 */
private val CFLocaleCopyPreferredLanguages_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFLocaleCopyPreferredLanguages_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCopyPreferredLanguages").orElseThrow()
private val CFLocaleCopyPreferredLanguages_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCopyPreferredLanguages_ADDR, CFLocaleCopyPreferredLanguages_DESC)

fun CFLocaleCopyPreferredLanguages(): MemorySegment {
    try {
        return CFLocaleCopyPreferredLanguages_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCreateCanonicalLanguageIdentifierFromString typedef CFLocaleIdentifier = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFLocaleCreateCanonicalLanguageIdentifierFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFLocaleCreateCanonicalLanguageIdentifierFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCreateCanonicalLanguageIdentifierFromString").orElseThrow()
private val CFLocaleCreateCanonicalLanguageIdentifierFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCreateCanonicalLanguageIdentifierFromString_ADDR, CFLocaleCreateCanonicalLanguageIdentifierFromString_DESC)

fun CFLocaleCreateCanonicalLanguageIdentifierFromString(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFLocaleCreateCanonicalLanguageIdentifierFromString_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCreateCanonicalLocaleIdentifierFromString typedef CFLocaleIdentifier = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFLocaleCreateCanonicalLocaleIdentifierFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFLocaleCreateCanonicalLocaleIdentifierFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCreateCanonicalLocaleIdentifierFromString").orElseThrow()
private val CFLocaleCreateCanonicalLocaleIdentifierFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCreateCanonicalLocaleIdentifierFromString_ADDR, CFLocaleCreateCanonicalLocaleIdentifierFromString_DESC)

fun CFLocaleCreateCanonicalLocaleIdentifierFromString(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFLocaleCreateCanonicalLocaleIdentifierFromString_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes typedef CFLocaleIdentifier = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef LangCode = Short,typedef RegionCode = Short)
 */
private val CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_SHORT, ValueLayout.JAVA_SHORT)
private val CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes").orElseThrow()
private val CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes_ADDR, CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes_DESC)

fun CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes(arg0: MemorySegment, arg1: Short, arg2: Short): MemorySegment {
    try {
        return CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode typedef CFLocaleIdentifier = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef uint32_t = UNSIGNED = Int)
 */
private val CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode").orElseThrow()
private val CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode_ADDR, CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode_DESC)

fun CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode(arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier typedef uint32_t = UNSIGNED = Int(typedef CFLocaleIdentifier = (Declared(__CFString))*)
 */
private val CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier").orElseThrow()
private val CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier_ADDR, CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier_DESC)

fun CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier(arg0: MemorySegment): Int {
    try {
        return CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCreateComponentsFromLocaleIdentifier typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFLocaleIdentifier = (Declared(__CFString))*)
 */
private val CFLocaleCreateComponentsFromLocaleIdentifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFLocaleCreateComponentsFromLocaleIdentifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCreateComponentsFromLocaleIdentifier").orElseThrow()
private val CFLocaleCreateComponentsFromLocaleIdentifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCreateComponentsFromLocaleIdentifier_ADDR, CFLocaleCreateComponentsFromLocaleIdentifier_DESC)

fun CFLocaleCreateComponentsFromLocaleIdentifier(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFLocaleCreateComponentsFromLocaleIdentifier_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCreateLocaleIdentifierFromComponents typedef CFLocaleIdentifier = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFLocaleCreateLocaleIdentifierFromComponents_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFLocaleCreateLocaleIdentifierFromComponents_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCreateLocaleIdentifierFromComponents").orElseThrow()
private val CFLocaleCreateLocaleIdentifierFromComponents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCreateLocaleIdentifierFromComponents_ADDR, CFLocaleCreateLocaleIdentifierFromComponents_DESC)

fun CFLocaleCreateLocaleIdentifierFromComponents(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFLocaleCreateLocaleIdentifierFromComponents_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCreate typedef CFLocaleRef = (Declared(__CFLocale))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFLocaleIdentifier = (Declared(__CFString))*)
 */
private val CFLocaleCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFLocaleCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCreate").orElseThrow()
private val CFLocaleCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCreate_ADDR, CFLocaleCreate_DESC)

fun CFLocaleCreate(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFLocaleCreate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCreateCopy typedef CFLocaleRef = (Declared(__CFLocale))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFLocaleRef = (Declared(__CFLocale))*)
 */
private val CFLocaleCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFLocaleCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCreateCopy").orElseThrow()
private val CFLocaleCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCreateCopy_ADDR, CFLocaleCreateCopy_DESC)

fun CFLocaleCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFLocaleCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleGetIdentifier typedef CFLocaleIdentifier = (Declared(__CFString))*(typedef CFLocaleRef = (Declared(__CFLocale))*)
 */
private val CFLocaleGetIdentifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFLocaleGetIdentifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleGetIdentifier").orElseThrow()
private val CFLocaleGetIdentifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleGetIdentifier_ADDR, CFLocaleGetIdentifier_DESC)

fun CFLocaleGetIdentifier(arg0: MemorySegment): MemorySegment {
    try {
        return CFLocaleGetIdentifier_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleGetValue typedef CFTypeRef = (Void)*(typedef CFLocaleRef = (Declared(__CFLocale))*,typedef CFLocaleKey = (Declared(__CFString))*)
 */
private val CFLocaleGetValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFLocaleGetValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleGetValue").orElseThrow()
private val CFLocaleGetValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleGetValue_ADDR, CFLocaleGetValue_DESC)

fun CFLocaleGetValue(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFLocaleGetValue_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFLocaleCopyDisplayNameForPropertyValue typedef CFStringRef = (Declared(__CFString))*(typedef CFLocaleRef = (Declared(__CFLocale))*,typedef CFLocaleKey = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFLocaleCopyDisplayNameForPropertyValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFLocaleCopyDisplayNameForPropertyValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFLocaleCopyDisplayNameForPropertyValue").orElseThrow()
private val CFLocaleCopyDisplayNameForPropertyValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFLocaleCopyDisplayNameForPropertyValue_ADDR, CFLocaleCopyDisplayNameForPropertyValue_DESC)

fun CFLocaleCopyDisplayNameForPropertyValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFLocaleCopyDisplayNameForPropertyValue_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFLocaleCurrentLocaleDidChangeNotification typedef const CFNotificationName = (Declared(__CFString))*
 */
private val kCFLocaleCurrentLocaleDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleCurrentLocaleDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleCurrentLocaleDidChangeNotification").orElseThrow() }
private val kCFLocaleCurrentLocaleDidChangeNotification_VH: VarHandle by lazy { kCFLocaleCurrentLocaleDidChangeNotification_LAYOUT.varHandle() }

var kCFLocaleCurrentLocaleDidChangeNotification: MemorySegment
    get() = kCFLocaleCurrentLocaleDidChangeNotification_VH.get(kCFLocaleCurrentLocaleDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = kCFLocaleCurrentLocaleDidChangeNotification_VH.set(kCFLocaleCurrentLocaleDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleIdentifier typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleIdentifier").orElseThrow() }
private val kCFLocaleIdentifier_VH: VarHandle by lazy { kCFLocaleIdentifier_LAYOUT.varHandle() }

var kCFLocaleIdentifier: MemorySegment
    get() = kCFLocaleIdentifier_VH.get(kCFLocaleIdentifier_SEGMENT) as MemorySegment
    set(value) = kCFLocaleIdentifier_VH.set(kCFLocaleIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleLanguageCode typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleLanguageCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleLanguageCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleLanguageCode").orElseThrow() }
private val kCFLocaleLanguageCode_VH: VarHandle by lazy { kCFLocaleLanguageCode_LAYOUT.varHandle() }

var kCFLocaleLanguageCode: MemorySegment
    get() = kCFLocaleLanguageCode_VH.get(kCFLocaleLanguageCode_SEGMENT) as MemorySegment
    set(value) = kCFLocaleLanguageCode_VH.set(kCFLocaleLanguageCode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleCountryCode typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleCountryCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleCountryCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleCountryCode").orElseThrow() }
private val kCFLocaleCountryCode_VH: VarHandle by lazy { kCFLocaleCountryCode_LAYOUT.varHandle() }

var kCFLocaleCountryCode: MemorySegment
    get() = kCFLocaleCountryCode_VH.get(kCFLocaleCountryCode_SEGMENT) as MemorySegment
    set(value) = kCFLocaleCountryCode_VH.set(kCFLocaleCountryCode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleScriptCode typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleScriptCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleScriptCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleScriptCode").orElseThrow() }
private val kCFLocaleScriptCode_VH: VarHandle by lazy { kCFLocaleScriptCode_LAYOUT.varHandle() }

var kCFLocaleScriptCode: MemorySegment
    get() = kCFLocaleScriptCode_VH.get(kCFLocaleScriptCode_SEGMENT) as MemorySegment
    set(value) = kCFLocaleScriptCode_VH.set(kCFLocaleScriptCode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleVariantCode typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleVariantCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleVariantCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleVariantCode").orElseThrow() }
private val kCFLocaleVariantCode_VH: VarHandle by lazy { kCFLocaleVariantCode_LAYOUT.varHandle() }

var kCFLocaleVariantCode: MemorySegment
    get() = kCFLocaleVariantCode_VH.get(kCFLocaleVariantCode_SEGMENT) as MemorySegment
    set(value) = kCFLocaleVariantCode_VH.set(kCFLocaleVariantCode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleExemplarCharacterSet typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleExemplarCharacterSet_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleExemplarCharacterSet_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleExemplarCharacterSet").orElseThrow() }
private val kCFLocaleExemplarCharacterSet_VH: VarHandle by lazy { kCFLocaleExemplarCharacterSet_LAYOUT.varHandle() }

var kCFLocaleExemplarCharacterSet: MemorySegment
    get() = kCFLocaleExemplarCharacterSet_VH.get(kCFLocaleExemplarCharacterSet_SEGMENT) as MemorySegment
    set(value) = kCFLocaleExemplarCharacterSet_VH.set(kCFLocaleExemplarCharacterSet_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleCalendarIdentifier typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleCalendarIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleCalendarIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleCalendarIdentifier").orElseThrow() }
private val kCFLocaleCalendarIdentifier_VH: VarHandle by lazy { kCFLocaleCalendarIdentifier_LAYOUT.varHandle() }

var kCFLocaleCalendarIdentifier: MemorySegment
    get() = kCFLocaleCalendarIdentifier_VH.get(kCFLocaleCalendarIdentifier_SEGMENT) as MemorySegment
    set(value) = kCFLocaleCalendarIdentifier_VH.set(kCFLocaleCalendarIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleCalendar typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleCalendar").orElseThrow() }
private val kCFLocaleCalendar_VH: VarHandle by lazy { kCFLocaleCalendar_LAYOUT.varHandle() }

var kCFLocaleCalendar: MemorySegment
    get() = kCFLocaleCalendar_VH.get(kCFLocaleCalendar_SEGMENT) as MemorySegment
    set(value) = kCFLocaleCalendar_VH.set(kCFLocaleCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleCollationIdentifier typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleCollationIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleCollationIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleCollationIdentifier").orElseThrow() }
private val kCFLocaleCollationIdentifier_VH: VarHandle by lazy { kCFLocaleCollationIdentifier_LAYOUT.varHandle() }

var kCFLocaleCollationIdentifier: MemorySegment
    get() = kCFLocaleCollationIdentifier_VH.get(kCFLocaleCollationIdentifier_SEGMENT) as MemorySegment
    set(value) = kCFLocaleCollationIdentifier_VH.set(kCFLocaleCollationIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleUsesMetricSystem typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleUsesMetricSystem_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleUsesMetricSystem_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleUsesMetricSystem").orElseThrow() }
private val kCFLocaleUsesMetricSystem_VH: VarHandle by lazy { kCFLocaleUsesMetricSystem_LAYOUT.varHandle() }

var kCFLocaleUsesMetricSystem: MemorySegment
    get() = kCFLocaleUsesMetricSystem_VH.get(kCFLocaleUsesMetricSystem_SEGMENT) as MemorySegment
    set(value) = kCFLocaleUsesMetricSystem_VH.set(kCFLocaleUsesMetricSystem_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleMeasurementSystem typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleMeasurementSystem_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleMeasurementSystem_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleMeasurementSystem").orElseThrow() }
private val kCFLocaleMeasurementSystem_VH: VarHandle by lazy { kCFLocaleMeasurementSystem_LAYOUT.varHandle() }

var kCFLocaleMeasurementSystem: MemorySegment
    get() = kCFLocaleMeasurementSystem_VH.get(kCFLocaleMeasurementSystem_SEGMENT) as MemorySegment
    set(value) = kCFLocaleMeasurementSystem_VH.set(kCFLocaleMeasurementSystem_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleDecimalSeparator typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleDecimalSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleDecimalSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleDecimalSeparator").orElseThrow() }
private val kCFLocaleDecimalSeparator_VH: VarHandle by lazy { kCFLocaleDecimalSeparator_LAYOUT.varHandle() }

var kCFLocaleDecimalSeparator: MemorySegment
    get() = kCFLocaleDecimalSeparator_VH.get(kCFLocaleDecimalSeparator_SEGMENT) as MemorySegment
    set(value) = kCFLocaleDecimalSeparator_VH.set(kCFLocaleDecimalSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleGroupingSeparator typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleGroupingSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleGroupingSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleGroupingSeparator").orElseThrow() }
private val kCFLocaleGroupingSeparator_VH: VarHandle by lazy { kCFLocaleGroupingSeparator_LAYOUT.varHandle() }

var kCFLocaleGroupingSeparator: MemorySegment
    get() = kCFLocaleGroupingSeparator_VH.get(kCFLocaleGroupingSeparator_SEGMENT) as MemorySegment
    set(value) = kCFLocaleGroupingSeparator_VH.set(kCFLocaleGroupingSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleCurrencySymbol typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleCurrencySymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleCurrencySymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleCurrencySymbol").orElseThrow() }
private val kCFLocaleCurrencySymbol_VH: VarHandle by lazy { kCFLocaleCurrencySymbol_LAYOUT.varHandle() }

var kCFLocaleCurrencySymbol: MemorySegment
    get() = kCFLocaleCurrencySymbol_VH.get(kCFLocaleCurrencySymbol_SEGMENT) as MemorySegment
    set(value) = kCFLocaleCurrencySymbol_VH.set(kCFLocaleCurrencySymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleCurrencyCode typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleCurrencyCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleCurrencyCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleCurrencyCode").orElseThrow() }
private val kCFLocaleCurrencyCode_VH: VarHandle by lazy { kCFLocaleCurrencyCode_LAYOUT.varHandle() }

var kCFLocaleCurrencyCode: MemorySegment
    get() = kCFLocaleCurrencyCode_VH.get(kCFLocaleCurrencyCode_SEGMENT) as MemorySegment
    set(value) = kCFLocaleCurrencyCode_VH.set(kCFLocaleCurrencyCode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleCollatorIdentifier typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleCollatorIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleCollatorIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleCollatorIdentifier").orElseThrow() }
private val kCFLocaleCollatorIdentifier_VH: VarHandle by lazy { kCFLocaleCollatorIdentifier_LAYOUT.varHandle() }

var kCFLocaleCollatorIdentifier: MemorySegment
    get() = kCFLocaleCollatorIdentifier_VH.get(kCFLocaleCollatorIdentifier_SEGMENT) as MemorySegment
    set(value) = kCFLocaleCollatorIdentifier_VH.set(kCFLocaleCollatorIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleQuotationBeginDelimiterKey typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleQuotationBeginDelimiterKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleQuotationBeginDelimiterKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleQuotationBeginDelimiterKey").orElseThrow() }
private val kCFLocaleQuotationBeginDelimiterKey_VH: VarHandle by lazy { kCFLocaleQuotationBeginDelimiterKey_LAYOUT.varHandle() }

var kCFLocaleQuotationBeginDelimiterKey: MemorySegment
    get() = kCFLocaleQuotationBeginDelimiterKey_VH.get(kCFLocaleQuotationBeginDelimiterKey_SEGMENT) as MemorySegment
    set(value) = kCFLocaleQuotationBeginDelimiterKey_VH.set(kCFLocaleQuotationBeginDelimiterKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleQuotationEndDelimiterKey typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleQuotationEndDelimiterKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleQuotationEndDelimiterKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleQuotationEndDelimiterKey").orElseThrow() }
private val kCFLocaleQuotationEndDelimiterKey_VH: VarHandle by lazy { kCFLocaleQuotationEndDelimiterKey_LAYOUT.varHandle() }

var kCFLocaleQuotationEndDelimiterKey: MemorySegment
    get() = kCFLocaleQuotationEndDelimiterKey_VH.get(kCFLocaleQuotationEndDelimiterKey_SEGMENT) as MemorySegment
    set(value) = kCFLocaleQuotationEndDelimiterKey_VH.set(kCFLocaleQuotationEndDelimiterKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleAlternateQuotationBeginDelimiterKey typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleAlternateQuotationBeginDelimiterKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleAlternateQuotationBeginDelimiterKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleAlternateQuotationBeginDelimiterKey").orElseThrow() }
private val kCFLocaleAlternateQuotationBeginDelimiterKey_VH: VarHandle by lazy { kCFLocaleAlternateQuotationBeginDelimiterKey_LAYOUT.varHandle() }

var kCFLocaleAlternateQuotationBeginDelimiterKey: MemorySegment
    get() = kCFLocaleAlternateQuotationBeginDelimiterKey_VH.get(kCFLocaleAlternateQuotationBeginDelimiterKey_SEGMENT) as MemorySegment
    set(value) = kCFLocaleAlternateQuotationBeginDelimiterKey_VH.set(kCFLocaleAlternateQuotationBeginDelimiterKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFLocaleAlternateQuotationEndDelimiterKey typedef const CFLocaleKey = (Declared(__CFString))*
 */
private val kCFLocaleAlternateQuotationEndDelimiterKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFLocaleAlternateQuotationEndDelimiterKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFLocaleAlternateQuotationEndDelimiterKey").orElseThrow() }
private val kCFLocaleAlternateQuotationEndDelimiterKey_VH: VarHandle by lazy { kCFLocaleAlternateQuotationEndDelimiterKey_LAYOUT.varHandle() }

var kCFLocaleAlternateQuotationEndDelimiterKey: MemorySegment
    get() = kCFLocaleAlternateQuotationEndDelimiterKey_VH.get(kCFLocaleAlternateQuotationEndDelimiterKey_SEGMENT) as MemorySegment
    set(value) = kCFLocaleAlternateQuotationEndDelimiterKey_VH.set(kCFLocaleAlternateQuotationEndDelimiterKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFGregorianCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFGregorianCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFGregorianCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFGregorianCalendar").orElseThrow() }
private val kCFGregorianCalendar_VH: VarHandle by lazy { kCFGregorianCalendar_LAYOUT.varHandle() }

var kCFGregorianCalendar: MemorySegment
    get() = kCFGregorianCalendar_VH.get(kCFGregorianCalendar_SEGMENT) as MemorySegment
    set(value) = kCFGregorianCalendar_VH.set(kCFGregorianCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBuddhistCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFBuddhistCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBuddhistCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBuddhistCalendar").orElseThrow() }
private val kCFBuddhistCalendar_VH: VarHandle by lazy { kCFBuddhistCalendar_LAYOUT.varHandle() }

var kCFBuddhistCalendar: MemorySegment
    get() = kCFBuddhistCalendar_VH.get(kCFBuddhistCalendar_SEGMENT) as MemorySegment
    set(value) = kCFBuddhistCalendar_VH.set(kCFBuddhistCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFChineseCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFChineseCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFChineseCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFChineseCalendar").orElseThrow() }
private val kCFChineseCalendar_VH: VarHandle by lazy { kCFChineseCalendar_LAYOUT.varHandle() }

var kCFChineseCalendar: MemorySegment
    get() = kCFChineseCalendar_VH.get(kCFChineseCalendar_SEGMENT) as MemorySegment
    set(value) = kCFChineseCalendar_VH.set(kCFChineseCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFHebrewCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFHebrewCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFHebrewCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFHebrewCalendar").orElseThrow() }
private val kCFHebrewCalendar_VH: VarHandle by lazy { kCFHebrewCalendar_LAYOUT.varHandle() }

var kCFHebrewCalendar: MemorySegment
    get() = kCFHebrewCalendar_VH.get(kCFHebrewCalendar_SEGMENT) as MemorySegment
    set(value) = kCFHebrewCalendar_VH.set(kCFHebrewCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFIslamicCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFIslamicCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFIslamicCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFIslamicCalendar").orElseThrow() }
private val kCFIslamicCalendar_VH: VarHandle by lazy { kCFIslamicCalendar_LAYOUT.varHandle() }

var kCFIslamicCalendar: MemorySegment
    get() = kCFIslamicCalendar_VH.get(kCFIslamicCalendar_SEGMENT) as MemorySegment
    set(value) = kCFIslamicCalendar_VH.set(kCFIslamicCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFIslamicCivilCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFIslamicCivilCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFIslamicCivilCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFIslamicCivilCalendar").orElseThrow() }
private val kCFIslamicCivilCalendar_VH: VarHandle by lazy { kCFIslamicCivilCalendar_LAYOUT.varHandle() }

var kCFIslamicCivilCalendar: MemorySegment
    get() = kCFIslamicCivilCalendar_VH.get(kCFIslamicCivilCalendar_SEGMENT) as MemorySegment
    set(value) = kCFIslamicCivilCalendar_VH.set(kCFIslamicCivilCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFJapaneseCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFJapaneseCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFJapaneseCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFJapaneseCalendar").orElseThrow() }
private val kCFJapaneseCalendar_VH: VarHandle by lazy { kCFJapaneseCalendar_LAYOUT.varHandle() }

var kCFJapaneseCalendar: MemorySegment
    get() = kCFJapaneseCalendar_VH.get(kCFJapaneseCalendar_SEGMENT) as MemorySegment
    set(value) = kCFJapaneseCalendar_VH.set(kCFJapaneseCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFRepublicOfChinaCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFRepublicOfChinaCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFRepublicOfChinaCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFRepublicOfChinaCalendar").orElseThrow() }
private val kCFRepublicOfChinaCalendar_VH: VarHandle by lazy { kCFRepublicOfChinaCalendar_LAYOUT.varHandle() }

var kCFRepublicOfChinaCalendar: MemorySegment
    get() = kCFRepublicOfChinaCalendar_VH.get(kCFRepublicOfChinaCalendar_SEGMENT) as MemorySegment
    set(value) = kCFRepublicOfChinaCalendar_VH.set(kCFRepublicOfChinaCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPersianCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFPersianCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPersianCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPersianCalendar").orElseThrow() }
private val kCFPersianCalendar_VH: VarHandle by lazy { kCFPersianCalendar_LAYOUT.varHandle() }

var kCFPersianCalendar: MemorySegment
    get() = kCFPersianCalendar_VH.get(kCFPersianCalendar_SEGMENT) as MemorySegment
    set(value) = kCFPersianCalendar_VH.set(kCFPersianCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFIndianCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFIndianCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFIndianCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFIndianCalendar").orElseThrow() }
private val kCFIndianCalendar_VH: VarHandle by lazy { kCFIndianCalendar_LAYOUT.varHandle() }

var kCFIndianCalendar: MemorySegment
    get() = kCFIndianCalendar_VH.get(kCFIndianCalendar_SEGMENT) as MemorySegment
    set(value) = kCFIndianCalendar_VH.set(kCFIndianCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFISO8601Calendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFISO8601Calendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFISO8601Calendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFISO8601Calendar").orElseThrow() }
private val kCFISO8601Calendar_VH: VarHandle by lazy { kCFISO8601Calendar_LAYOUT.varHandle() }

var kCFISO8601Calendar: MemorySegment
    get() = kCFISO8601Calendar_VH.get(kCFISO8601Calendar_SEGMENT) as MemorySegment
    set(value) = kCFISO8601Calendar_VH.set(kCFISO8601Calendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFIslamicTabularCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFIslamicTabularCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFIslamicTabularCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFIslamicTabularCalendar").orElseThrow() }
private val kCFIslamicTabularCalendar_VH: VarHandle by lazy { kCFIslamicTabularCalendar_LAYOUT.varHandle() }

var kCFIslamicTabularCalendar: MemorySegment
    get() = kCFIslamicTabularCalendar_VH.get(kCFIslamicTabularCalendar_SEGMENT) as MemorySegment
    set(value) = kCFIslamicTabularCalendar_VH.set(kCFIslamicTabularCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFIslamicUmmAlQuraCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFIslamicUmmAlQuraCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFIslamicUmmAlQuraCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFIslamicUmmAlQuraCalendar").orElseThrow() }
private val kCFIslamicUmmAlQuraCalendar_VH: VarHandle by lazy { kCFIslamicUmmAlQuraCalendar_LAYOUT.varHandle() }

var kCFIslamicUmmAlQuraCalendar: MemorySegment
    get() = kCFIslamicUmmAlQuraCalendar_VH.get(kCFIslamicUmmAlQuraCalendar_SEGMENT) as MemorySegment
    set(value) = kCFIslamicUmmAlQuraCalendar_VH.set(kCFIslamicUmmAlQuraCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBanglaCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFBanglaCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBanglaCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBanglaCalendar").orElseThrow() }
private val kCFBanglaCalendar_VH: VarHandle by lazy { kCFBanglaCalendar_LAYOUT.varHandle() }

var kCFBanglaCalendar: MemorySegment
    get() = kCFBanglaCalendar_VH.get(kCFBanglaCalendar_SEGMENT) as MemorySegment
    set(value) = kCFBanglaCalendar_VH.set(kCFBanglaCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFGujaratiCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFGujaratiCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFGujaratiCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFGujaratiCalendar").orElseThrow() }
private val kCFGujaratiCalendar_VH: VarHandle by lazy { kCFGujaratiCalendar_LAYOUT.varHandle() }

var kCFGujaratiCalendar: MemorySegment
    get() = kCFGujaratiCalendar_VH.get(kCFGujaratiCalendar_SEGMENT) as MemorySegment
    set(value) = kCFGujaratiCalendar_VH.set(kCFGujaratiCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFKannadaCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFKannadaCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFKannadaCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFKannadaCalendar").orElseThrow() }
private val kCFKannadaCalendar_VH: VarHandle by lazy { kCFKannadaCalendar_LAYOUT.varHandle() }

var kCFKannadaCalendar: MemorySegment
    get() = kCFKannadaCalendar_VH.get(kCFKannadaCalendar_SEGMENT) as MemorySegment
    set(value) = kCFKannadaCalendar_VH.set(kCFKannadaCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFMalayalamCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFMalayalamCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFMalayalamCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFMalayalamCalendar").orElseThrow() }
private val kCFMalayalamCalendar_VH: VarHandle by lazy { kCFMalayalamCalendar_LAYOUT.varHandle() }

var kCFMalayalamCalendar: MemorySegment
    get() = kCFMalayalamCalendar_VH.get(kCFMalayalamCalendar_SEGMENT) as MemorySegment
    set(value) = kCFMalayalamCalendar_VH.set(kCFMalayalamCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFMarathiCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFMarathiCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFMarathiCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFMarathiCalendar").orElseThrow() }
private val kCFMarathiCalendar_VH: VarHandle by lazy { kCFMarathiCalendar_LAYOUT.varHandle() }

var kCFMarathiCalendar: MemorySegment
    get() = kCFMarathiCalendar_VH.get(kCFMarathiCalendar_SEGMENT) as MemorySegment
    set(value) = kCFMarathiCalendar_VH.set(kCFMarathiCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFOdiaCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFOdiaCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFOdiaCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFOdiaCalendar").orElseThrow() }
private val kCFOdiaCalendar_VH: VarHandle by lazy { kCFOdiaCalendar_LAYOUT.varHandle() }

var kCFOdiaCalendar: MemorySegment
    get() = kCFOdiaCalendar_VH.get(kCFOdiaCalendar_SEGMENT) as MemorySegment
    set(value) = kCFOdiaCalendar_VH.set(kCFOdiaCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFTamilCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFTamilCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFTamilCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFTamilCalendar").orElseThrow() }
private val kCFTamilCalendar_VH: VarHandle by lazy { kCFTamilCalendar_LAYOUT.varHandle() }

var kCFTamilCalendar: MemorySegment
    get() = kCFTamilCalendar_VH.get(kCFTamilCalendar_SEGMENT) as MemorySegment
    set(value) = kCFTamilCalendar_VH.set(kCFTamilCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFTeluguCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFTeluguCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFTeluguCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFTeluguCalendar").orElseThrow() }
private val kCFTeluguCalendar_VH: VarHandle by lazy { kCFTeluguCalendar_LAYOUT.varHandle() }

var kCFTeluguCalendar: MemorySegment
    get() = kCFTeluguCalendar_VH.get(kCFTeluguCalendar_SEGMENT) as MemorySegment
    set(value) = kCFTeluguCalendar_VH.set(kCFTeluguCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFVikramCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFVikramCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFVikramCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFVikramCalendar").orElseThrow() }
private val kCFVikramCalendar_VH: VarHandle by lazy { kCFVikramCalendar_LAYOUT.varHandle() }

var kCFVikramCalendar: MemorySegment
    get() = kCFVikramCalendar_VH.get(kCFVikramCalendar_SEGMENT) as MemorySegment
    set(value) = kCFVikramCalendar_VH.set(kCFVikramCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDangiCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFDangiCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDangiCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDangiCalendar").orElseThrow() }
private val kCFDangiCalendar_VH: VarHandle by lazy { kCFDangiCalendar_LAYOUT.varHandle() }

var kCFDangiCalendar: MemorySegment
    get() = kCFDangiCalendar_VH.get(kCFDangiCalendar_SEGMENT) as MemorySegment
    set(value) = kCFDangiCalendar_VH.set(kCFDangiCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFVietnameseCalendar typedef const CFCalendarIdentifier = (Declared(__CFString))*
 */
private val kCFVietnameseCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFVietnameseCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFVietnameseCalendar").orElseThrow() }
private val kCFVietnameseCalendar_VH: VarHandle by lazy { kCFVietnameseCalendar_LAYOUT.varHandle() }

var kCFVietnameseCalendar: MemorySegment
    get() = kCFVietnameseCalendar_VH.get(kCFVietnameseCalendar_SEGMENT) as MemorySegment
    set(value) = kCFVietnameseCalendar_VH.set(kCFVietnameseCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : CFAbsoluteTimeGetCurrent typedef CFAbsoluteTime = Double()
 */
private val CFAbsoluteTimeGetCurrent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE)
private val CFAbsoluteTimeGetCurrent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAbsoluteTimeGetCurrent").orElseThrow()
private val CFAbsoluteTimeGetCurrent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAbsoluteTimeGetCurrent_ADDR, CFAbsoluteTimeGetCurrent_DESC)

fun CFAbsoluteTimeGetCurrent(): Double {
    try {
        return CFAbsoluteTimeGetCurrent_HANDLE.invokeExact() as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFAbsoluteTimeIntervalSince1970 typedef const CFTimeInterval = Double
 */
private val kCFAbsoluteTimeIntervalSince1970_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val kCFAbsoluteTimeIntervalSince1970_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFAbsoluteTimeIntervalSince1970").orElseThrow() }
private val kCFAbsoluteTimeIntervalSince1970_VH: VarHandle by lazy { kCFAbsoluteTimeIntervalSince1970_LAYOUT.varHandle() }

var kCFAbsoluteTimeIntervalSince1970: Double
    get() = kCFAbsoluteTimeIntervalSince1970_VH.get(kCFAbsoluteTimeIntervalSince1970_SEGMENT) as Double
    set(value) = kCFAbsoluteTimeIntervalSince1970_VH.set(kCFAbsoluteTimeIntervalSince1970_SEGMENT, value)

/**
 * {@snippet lang=c : kCFAbsoluteTimeIntervalSince1904 typedef const CFTimeInterval = Double
 */
private val kCFAbsoluteTimeIntervalSince1904_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val kCFAbsoluteTimeIntervalSince1904_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFAbsoluteTimeIntervalSince1904").orElseThrow() }
private val kCFAbsoluteTimeIntervalSince1904_VH: VarHandle by lazy { kCFAbsoluteTimeIntervalSince1904_LAYOUT.varHandle() }

var kCFAbsoluteTimeIntervalSince1904: Double
    get() = kCFAbsoluteTimeIntervalSince1904_VH.get(kCFAbsoluteTimeIntervalSince1904_SEGMENT) as Double
    set(value) = kCFAbsoluteTimeIntervalSince1904_VH.set(kCFAbsoluteTimeIntervalSince1904_SEGMENT, value)

/**
 * {@snippet lang=c : CFDateGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFDateGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFDateGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateGetTypeID").orElseThrow()
private val CFDateGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateGetTypeID_ADDR, CFDateGetTypeID_DESC)

fun CFDateGetTypeID(): Long {
    try {
        return CFDateGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateCreate typedef CFDateRef = (Declared(__CFDate))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFAbsoluteTime = Double)
 */
private val CFDateCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFDateCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateCreate").orElseThrow()
private val CFDateCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateCreate_ADDR, CFDateCreate_DESC)

fun CFDateCreate(arg0: MemorySegment, arg1: Double): MemorySegment {
    try {
        return CFDateCreate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateGetAbsoluteTime typedef CFAbsoluteTime = Double(typedef CFDateRef = (Declared(__CFDate))*)
 */
private val CFDateGetAbsoluteTime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFDateGetAbsoluteTime_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateGetAbsoluteTime").orElseThrow()
private val CFDateGetAbsoluteTime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateGetAbsoluteTime_ADDR, CFDateGetAbsoluteTime_DESC)

fun CFDateGetAbsoluteTime(arg0: MemorySegment): Double {
    try {
        return CFDateGetAbsoluteTime_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateGetTimeIntervalSinceDate typedef CFTimeInterval = Double(typedef CFDateRef = (Declared(__CFDate))*,typedef CFDateRef = (Declared(__CFDate))*)
 */
private val CFDateGetTimeIntervalSinceDate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDateGetTimeIntervalSinceDate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateGetTimeIntervalSinceDate").orElseThrow()
private val CFDateGetTimeIntervalSinceDate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateGetTimeIntervalSinceDate_ADDR, CFDateGetTimeIntervalSinceDate_DESC)

fun CFDateGetTimeIntervalSinceDate(arg0: MemorySegment, arg1: MemorySegment): Double {
    try {
        return CFDateGetTimeIntervalSinceDate_HANDLE.invokeExact(arg0, arg1) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFGregorianDateIsValid typedef Boolean = UNSIGNED = Char(typedef CFGregorianDate = Declared(CFGregorianDate),typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFGregorianDateIsValid_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, CFGregorianDate.layout, ValueLayout.JAVA_LONG)
private val CFGregorianDateIsValid_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFGregorianDateIsValid").orElseThrow()
private val CFGregorianDateIsValid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFGregorianDateIsValid_ADDR, CFGregorianDateIsValid_DESC)

fun CFGregorianDateIsValid(arg0: MemorySegment, arg1: Long): Byte {
    try {
        return CFGregorianDateIsValid_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFGregorianDateGetAbsoluteTime typedef CFAbsoluteTime = Double(typedef CFGregorianDate = Declared(CFGregorianDate),typedef CFTimeZoneRef = (Declared(__CFTimeZone))*)
 */
private val CFGregorianDateGetAbsoluteTime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, CFGregorianDate.layout, ValueLayout.ADDRESS)
private val CFGregorianDateGetAbsoluteTime_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFGregorianDateGetAbsoluteTime").orElseThrow()
private val CFGregorianDateGetAbsoluteTime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFGregorianDateGetAbsoluteTime_ADDR, CFGregorianDateGetAbsoluteTime_DESC)

fun CFGregorianDateGetAbsoluteTime(arg0: MemorySegment, arg1: MemorySegment): Double {
    try {
        return CFGregorianDateGetAbsoluteTime_HANDLE.invokeExact(arg0, arg1) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAbsoluteTimeGetGregorianDate typedef CFGregorianDate = Declared(CFGregorianDate)(typedef CFAbsoluteTime = Double,typedef CFTimeZoneRef = (Declared(__CFTimeZone))*)
 */
private val CFAbsoluteTimeGetGregorianDate_DESC: FunctionDescriptor = FunctionDescriptor.of(CFGregorianDate.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFAbsoluteTimeGetGregorianDate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAbsoluteTimeGetGregorianDate").orElseThrow()
private val CFAbsoluteTimeGetGregorianDate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAbsoluteTimeGetGregorianDate_ADDR, CFAbsoluteTimeGetGregorianDate_DESC)

fun CFAbsoluteTimeGetGregorianDate(allocator: SegmentAllocator, arg0: Double, arg1: MemorySegment): MemorySegment {
    try {
        return CFAbsoluteTimeGetGregorianDate_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAbsoluteTimeAddGregorianUnits typedef CFAbsoluteTime = Double(typedef CFAbsoluteTime = Double,typedef CFTimeZoneRef = (Declared(__CFTimeZone))*,typedef CFGregorianUnits = Declared(CFGregorianUnits))
 */
private val CFAbsoluteTimeAddGregorianUnits_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, CFGregorianUnits.layout)
private val CFAbsoluteTimeAddGregorianUnits_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAbsoluteTimeAddGregorianUnits").orElseThrow()
private val CFAbsoluteTimeAddGregorianUnits_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAbsoluteTimeAddGregorianUnits_ADDR, CFAbsoluteTimeAddGregorianUnits_DESC)

fun CFAbsoluteTimeAddGregorianUnits(arg0: Double, arg1: MemorySegment, arg2: MemorySegment): Double {
    try {
        return CFAbsoluteTimeAddGregorianUnits_HANDLE.invokeExact(arg0, arg1, arg2) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAbsoluteTimeGetDifferenceAsGregorianUnits typedef CFGregorianUnits = Declared(CFGregorianUnits)(typedef CFAbsoluteTime = Double,typedef CFAbsoluteTime = Double,typedef CFTimeZoneRef = (Declared(__CFTimeZone))*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFAbsoluteTimeGetDifferenceAsGregorianUnits_DESC: FunctionDescriptor = FunctionDescriptor.of(CFGregorianUnits.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFAbsoluteTimeGetDifferenceAsGregorianUnits_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAbsoluteTimeGetDifferenceAsGregorianUnits").orElseThrow()
private val CFAbsoluteTimeGetDifferenceAsGregorianUnits_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAbsoluteTimeGetDifferenceAsGregorianUnits_ADDR, CFAbsoluteTimeGetDifferenceAsGregorianUnits_DESC)

fun CFAbsoluteTimeGetDifferenceAsGregorianUnits(allocator: SegmentAllocator, arg0: Double, arg1: Double, arg2: MemorySegment, arg3: Long): MemorySegment {
    try {
        return CFAbsoluteTimeGetDifferenceAsGregorianUnits_HANDLE.invokeExact(allocator, arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAbsoluteTimeGetDayOfWeek typedef SInt32 = Int(typedef CFAbsoluteTime = Double,typedef CFTimeZoneRef = (Declared(__CFTimeZone))*)
 */
private val CFAbsoluteTimeGetDayOfWeek_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFAbsoluteTimeGetDayOfWeek_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAbsoluteTimeGetDayOfWeek").orElseThrow()
private val CFAbsoluteTimeGetDayOfWeek_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAbsoluteTimeGetDayOfWeek_ADDR, CFAbsoluteTimeGetDayOfWeek_DESC)

fun CFAbsoluteTimeGetDayOfWeek(arg0: Double, arg1: MemorySegment): Int {
    try {
        return CFAbsoluteTimeGetDayOfWeek_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAbsoluteTimeGetDayOfYear typedef SInt32 = Int(typedef CFAbsoluteTime = Double,typedef CFTimeZoneRef = (Declared(__CFTimeZone))*)
 */
private val CFAbsoluteTimeGetDayOfYear_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFAbsoluteTimeGetDayOfYear_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAbsoluteTimeGetDayOfYear").orElseThrow()
private val CFAbsoluteTimeGetDayOfYear_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAbsoluteTimeGetDayOfYear_ADDR, CFAbsoluteTimeGetDayOfYear_DESC)

fun CFAbsoluteTimeGetDayOfYear(arg0: Double, arg1: MemorySegment): Int {
    try {
        return CFAbsoluteTimeGetDayOfYear_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAbsoluteTimeGetWeekOfYear typedef SInt32 = Int(typedef CFAbsoluteTime = Double,typedef CFTimeZoneRef = (Declared(__CFTimeZone))*)
 */
private val CFAbsoluteTimeGetWeekOfYear_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFAbsoluteTimeGetWeekOfYear_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAbsoluteTimeGetWeekOfYear").orElseThrow()
private val CFAbsoluteTimeGetWeekOfYear_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAbsoluteTimeGetWeekOfYear_ADDR, CFAbsoluteTimeGetWeekOfYear_DESC)

fun CFAbsoluteTimeGetWeekOfYear(arg0: Double, arg1: MemorySegment): Int {
    try {
        return CFAbsoluteTimeGetWeekOfYear_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFDataGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFDataGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataGetTypeID").orElseThrow()
private val CFDataGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataGetTypeID_ADDR, CFDataGetTypeID_DESC)

fun CFDataGetTypeID(): Long {
    try {
        return CFDataGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataCreate typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long)
 */
private val CFDataCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFDataCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataCreate").orElseThrow()
private val CFDataCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataCreate_ADDR, CFDataCreate_DESC)

fun CFDataCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFDataCreate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataCreateWithBytesNoCopy typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long,typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFDataCreateWithBytesNoCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFDataCreateWithBytesNoCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataCreateWithBytesNoCopy").orElseThrow()
private val CFDataCreateWithBytesNoCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataCreateWithBytesNoCopy_ADDR, CFDataCreateWithBytesNoCopy_DESC)

fun CFDataCreateWithBytesNoCopy(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CFDataCreateWithBytesNoCopy_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataCreateCopy typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*)
 */
private val CFDataCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDataCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataCreateCopy").orElseThrow()
private val CFDataCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataCreateCopy_ADDR, CFDataCreateCopy_DESC)

fun CFDataCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFDataCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataCreateMutable typedef CFMutableDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long)
 */
private val CFDataCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFDataCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataCreateMutable").orElseThrow()
private val CFDataCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataCreateMutable_ADDR, CFDataCreateMutable_DESC)

fun CFDataCreateMutable(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return CFDataCreateMutable_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataCreateMutableCopy typedef CFMutableDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFDataRef = (Declared(__CFData))*)
 */
private val CFDataCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFDataCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataCreateMutableCopy").orElseThrow()
private val CFDataCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataCreateMutableCopy_ADDR, CFDataCreateMutableCopy_DESC)

fun CFDataCreateMutableCopy(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFDataCreateMutableCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataGetLength typedef CFIndex = Long(typedef CFDataRef = (Declared(__CFData))*)
 */
private val CFDataGetLength_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFDataGetLength_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataGetLength").orElseThrow()
private val CFDataGetLength_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataGetLength_ADDR, CFDataGetLength_DESC)

fun CFDataGetLength(arg0: MemorySegment): Long {
    try {
        return CFDataGetLength_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataGetBytePtr (typedef UInt8 = UNSIGNED = Char)*(typedef CFDataRef = (Declared(__CFData))*)
 */
private val CFDataGetBytePtr_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDataGetBytePtr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataGetBytePtr").orElseThrow()
private val CFDataGetBytePtr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataGetBytePtr_ADDR, CFDataGetBytePtr_DESC)

fun CFDataGetBytePtr(arg0: MemorySegment): MemorySegment {
    try {
        return CFDataGetBytePtr_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataGetMutableBytePtr (typedef UInt8 = UNSIGNED = Char)*(typedef CFMutableDataRef = (Declared(__CFData))*)
 */
private val CFDataGetMutableBytePtr_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDataGetMutableBytePtr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataGetMutableBytePtr").orElseThrow()
private val CFDataGetMutableBytePtr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataGetMutableBytePtr_ADDR, CFDataGetMutableBytePtr_DESC)

fun CFDataGetMutableBytePtr(arg0: MemorySegment): MemorySegment {
    try {
        return CFDataGetMutableBytePtr_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataGetBytes Void(typedef CFDataRef = (Declared(__CFData))*,typedef CFRange = Declared(CFRange),(typedef UInt8 = UNSIGNED = Char)*)
 */
private val CFDataGetBytes_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFDataGetBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataGetBytes").orElseThrow()
private val CFDataGetBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataGetBytes_ADDR, CFDataGetBytes_DESC)

fun CFDataGetBytes(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFDataGetBytes_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataSetLength Void(typedef CFMutableDataRef = (Declared(__CFData))*,typedef CFIndex = Long)
 */
private val CFDataSetLength_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFDataSetLength_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataSetLength").orElseThrow()
private val CFDataSetLength_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataSetLength_ADDR, CFDataSetLength_DESC)

fun CFDataSetLength(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFDataSetLength_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataIncreaseLength Void(typedef CFMutableDataRef = (Declared(__CFData))*,typedef CFIndex = Long)
 */
private val CFDataIncreaseLength_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFDataIncreaseLength_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataIncreaseLength").orElseThrow()
private val CFDataIncreaseLength_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataIncreaseLength_ADDR, CFDataIncreaseLength_DESC)

fun CFDataIncreaseLength(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFDataIncreaseLength_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataAppendBytes Void(typedef CFMutableDataRef = (Declared(__CFData))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long)
 */
private val CFDataAppendBytes_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFDataAppendBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataAppendBytes").orElseThrow()
private val CFDataAppendBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataAppendBytes_ADDR, CFDataAppendBytes_DESC)

fun CFDataAppendBytes(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        CFDataAppendBytes_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataReplaceBytes Void(typedef CFMutableDataRef = (Declared(__CFData))*,typedef CFRange = Declared(CFRange),(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long)
 */
private val CFDataReplaceBytes_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFDataReplaceBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataReplaceBytes").orElseThrow()
private val CFDataReplaceBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataReplaceBytes_ADDR, CFDataReplaceBytes_DESC)

fun CFDataReplaceBytes(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long): Unit {
    try {
        CFDataReplaceBytes_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDataDeleteBytes Void(typedef CFMutableDataRef = (Declared(__CFData))*,typedef CFRange = Declared(CFRange))
 */
private val CFDataDeleteBytes_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout)
private val CFDataDeleteBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDataDeleteBytes").orElseThrow()
private val CFDataDeleteBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDataDeleteBytes_ADDR, CFDataDeleteBytes_DESC)

fun CFDataDeleteBytes(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFDataDeleteBytes_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFCharacterSetGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFCharacterSetGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetGetTypeID").orElseThrow()
private val CFCharacterSetGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetGetTypeID_ADDR, CFCharacterSetGetTypeID_DESC)

fun CFCharacterSetGetTypeID(): Long {
    try {
        return CFCharacterSetGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetCreateWithCharactersInRange typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFRange = Declared(CFRange))
 */
private val CFCharacterSetCreateWithCharactersInRange_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CFRange.layout)
private val CFCharacterSetCreateWithCharactersInRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetCreateWithCharactersInRange").orElseThrow()
private val CFCharacterSetCreateWithCharactersInRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetCreateWithCharactersInRange_ADDR, CFCharacterSetCreateWithCharactersInRange_DESC)

fun CFCharacterSetCreateWithCharactersInRange(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFCharacterSetCreateWithCharactersInRange_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetCreateWithCharactersInString typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFCharacterSetCreateWithCharactersInString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetCreateWithCharactersInString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetCreateWithCharactersInString").orElseThrow()
private val CFCharacterSetCreateWithCharactersInString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetCreateWithCharactersInString_ADDR, CFCharacterSetCreateWithCharactersInString_DESC)

fun CFCharacterSetCreateWithCharactersInString(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFCharacterSetCreateWithCharactersInString_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetCreateWithBitmapRepresentation typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*)
 */
private val CFCharacterSetCreateWithBitmapRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetCreateWithBitmapRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetCreateWithBitmapRepresentation").orElseThrow()
private val CFCharacterSetCreateWithBitmapRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetCreateWithBitmapRepresentation_ADDR, CFCharacterSetCreateWithBitmapRepresentation_DESC)

fun CFCharacterSetCreateWithBitmapRepresentation(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFCharacterSetCreateWithBitmapRepresentation_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetCreateInvertedSet typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*)
 */
private val CFCharacterSetCreateInvertedSet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetCreateInvertedSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetCreateInvertedSet").orElseThrow()
private val CFCharacterSetCreateInvertedSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetCreateInvertedSet_ADDR, CFCharacterSetCreateInvertedSet_DESC)

fun CFCharacterSetCreateInvertedSet(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFCharacterSetCreateInvertedSet_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetIsSupersetOfSet typedef Boolean = UNSIGNED = Char(typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*,typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*)
 */
private val CFCharacterSetIsSupersetOfSet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetIsSupersetOfSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetIsSupersetOfSet").orElseThrow()
private val CFCharacterSetIsSupersetOfSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetIsSupersetOfSet_ADDR, CFCharacterSetIsSupersetOfSet_DESC)

fun CFCharacterSetIsSupersetOfSet(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFCharacterSetIsSupersetOfSet_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetHasMemberInPlane typedef Boolean = UNSIGNED = Char(typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*,typedef CFIndex = Long)
 */
private val CFCharacterSetHasMemberInPlane_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFCharacterSetHasMemberInPlane_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetHasMemberInPlane").orElseThrow()
private val CFCharacterSetHasMemberInPlane_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetHasMemberInPlane_ADDR, CFCharacterSetHasMemberInPlane_DESC)

fun CFCharacterSetHasMemberInPlane(arg0: MemorySegment, arg1: Long): Byte {
    try {
        return CFCharacterSetHasMemberInPlane_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetCreateMutable typedef CFMutableCharacterSetRef = (Declared(__CFCharacterSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFCharacterSetCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetCreateMutable").orElseThrow()
private val CFCharacterSetCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetCreateMutable_ADDR, CFCharacterSetCreateMutable_DESC)

fun CFCharacterSetCreateMutable(arg0: MemorySegment): MemorySegment {
    try {
        return CFCharacterSetCreateMutable_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetCreateCopy typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*)
 */
private val CFCharacterSetCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetCreateCopy").orElseThrow()
private val CFCharacterSetCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetCreateCopy_ADDR, CFCharacterSetCreateCopy_DESC)

fun CFCharacterSetCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFCharacterSetCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetCreateMutableCopy typedef CFMutableCharacterSetRef = (Declared(__CFCharacterSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*)
 */
private val CFCharacterSetCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetCreateMutableCopy").orElseThrow()
private val CFCharacterSetCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetCreateMutableCopy_ADDR, CFCharacterSetCreateMutableCopy_DESC)

fun CFCharacterSetCreateMutableCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFCharacterSetCreateMutableCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetIsCharacterMember typedef Boolean = UNSIGNED = Char(typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*,typedef UniChar = UNSIGNED = Short)
 */
private val CFCharacterSetIsCharacterMember_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_SHORT)
private val CFCharacterSetIsCharacterMember_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetIsCharacterMember").orElseThrow()
private val CFCharacterSetIsCharacterMember_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetIsCharacterMember_ADDR, CFCharacterSetIsCharacterMember_DESC)

fun CFCharacterSetIsCharacterMember(arg0: MemorySegment, arg1: Short): Byte {
    try {
        return CFCharacterSetIsCharacterMember_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetIsLongCharacterMember typedef Boolean = UNSIGNED = Char(typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*,typedef UTF32Char = UNSIGNED = Int)
 */
private val CFCharacterSetIsLongCharacterMember_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFCharacterSetIsLongCharacterMember_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetIsLongCharacterMember").orElseThrow()
private val CFCharacterSetIsLongCharacterMember_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetIsLongCharacterMember_ADDR, CFCharacterSetIsLongCharacterMember_DESC)

fun CFCharacterSetIsLongCharacterMember(arg0: MemorySegment, arg1: Int): Byte {
    try {
        return CFCharacterSetIsLongCharacterMember_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetCreateBitmapRepresentation typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*)
 */
private val CFCharacterSetCreateBitmapRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetCreateBitmapRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetCreateBitmapRepresentation").orElseThrow()
private val CFCharacterSetCreateBitmapRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetCreateBitmapRepresentation_ADDR, CFCharacterSetCreateBitmapRepresentation_DESC)

fun CFCharacterSetCreateBitmapRepresentation(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFCharacterSetCreateBitmapRepresentation_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetAddCharactersInRange Void(typedef CFMutableCharacterSetRef = (Declared(__CFCharacterSet))*,typedef CFRange = Declared(CFRange))
 */
private val CFCharacterSetAddCharactersInRange_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout)
private val CFCharacterSetAddCharactersInRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetAddCharactersInRange").orElseThrow()
private val CFCharacterSetAddCharactersInRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetAddCharactersInRange_ADDR, CFCharacterSetAddCharactersInRange_DESC)

fun CFCharacterSetAddCharactersInRange(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFCharacterSetAddCharactersInRange_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetRemoveCharactersInRange Void(typedef CFMutableCharacterSetRef = (Declared(__CFCharacterSet))*,typedef CFRange = Declared(CFRange))
 */
private val CFCharacterSetRemoveCharactersInRange_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout)
private val CFCharacterSetRemoveCharactersInRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetRemoveCharactersInRange").orElseThrow()
private val CFCharacterSetRemoveCharactersInRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetRemoveCharactersInRange_ADDR, CFCharacterSetRemoveCharactersInRange_DESC)

fun CFCharacterSetRemoveCharactersInRange(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFCharacterSetRemoveCharactersInRange_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetAddCharactersInString Void(typedef CFMutableCharacterSetRef = (Declared(__CFCharacterSet))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFCharacterSetAddCharactersInString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetAddCharactersInString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetAddCharactersInString").orElseThrow()
private val CFCharacterSetAddCharactersInString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetAddCharactersInString_ADDR, CFCharacterSetAddCharactersInString_DESC)

fun CFCharacterSetAddCharactersInString(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFCharacterSetAddCharactersInString_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetRemoveCharactersInString Void(typedef CFMutableCharacterSetRef = (Declared(__CFCharacterSet))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFCharacterSetRemoveCharactersInString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetRemoveCharactersInString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetRemoveCharactersInString").orElseThrow()
private val CFCharacterSetRemoveCharactersInString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetRemoveCharactersInString_ADDR, CFCharacterSetRemoveCharactersInString_DESC)

fun CFCharacterSetRemoveCharactersInString(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFCharacterSetRemoveCharactersInString_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetUnion Void(typedef CFMutableCharacterSetRef = (Declared(__CFCharacterSet))*,typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*)
 */
private val CFCharacterSetUnion_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetUnion_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetUnion").orElseThrow()
private val CFCharacterSetUnion_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetUnion_ADDR, CFCharacterSetUnion_DESC)

fun CFCharacterSetUnion(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFCharacterSetUnion_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetIntersect Void(typedef CFMutableCharacterSetRef = (Declared(__CFCharacterSet))*,typedef CFCharacterSetRef = (Declared(__CFCharacterSet))*)
 */
private val CFCharacterSetIntersect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCharacterSetIntersect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetIntersect").orElseThrow()
private val CFCharacterSetIntersect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetIntersect_ADDR, CFCharacterSetIntersect_DESC)

fun CFCharacterSetIntersect(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFCharacterSetIntersect_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCharacterSetInvert Void(typedef CFMutableCharacterSetRef = (Declared(__CFCharacterSet))*)
 */
private val CFCharacterSetInvert_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFCharacterSetInvert_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCharacterSetInvert").orElseThrow()
private val CFCharacterSetInvert_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCharacterSetInvert_ADDR, CFCharacterSetInvert_DESC)

fun CFCharacterSetInvert(arg0: MemorySegment): Unit {
    try {
        CFCharacterSetInvert_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFErrorGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFErrorGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFErrorGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFErrorGetTypeID").orElseThrow()
private val CFErrorGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFErrorGetTypeID_ADDR, CFErrorGetTypeID_DESC)

fun CFErrorGetTypeID(): Long {
    try {
        return CFErrorGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFErrorDomainPOSIX typedef const CFErrorDomain = (Declared(__CFString))*
 */
private val kCFErrorDomainPOSIX_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorDomainPOSIX_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorDomainPOSIX").orElseThrow() }
private val kCFErrorDomainPOSIX_VH: VarHandle by lazy { kCFErrorDomainPOSIX_LAYOUT.varHandle() }

var kCFErrorDomainPOSIX: MemorySegment
    get() = kCFErrorDomainPOSIX_VH.get(kCFErrorDomainPOSIX_SEGMENT) as MemorySegment
    set(value) = kCFErrorDomainPOSIX_VH.set(kCFErrorDomainPOSIX_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorDomainOSStatus typedef const CFErrorDomain = (Declared(__CFString))*
 */
private val kCFErrorDomainOSStatus_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorDomainOSStatus_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorDomainOSStatus").orElseThrow() }
private val kCFErrorDomainOSStatus_VH: VarHandle by lazy { kCFErrorDomainOSStatus_LAYOUT.varHandle() }

var kCFErrorDomainOSStatus: MemorySegment
    get() = kCFErrorDomainOSStatus_VH.get(kCFErrorDomainOSStatus_SEGMENT) as MemorySegment
    set(value) = kCFErrorDomainOSStatus_VH.set(kCFErrorDomainOSStatus_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorDomainMach typedef const CFErrorDomain = (Declared(__CFString))*
 */
private val kCFErrorDomainMach_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorDomainMach_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorDomainMach").orElseThrow() }
private val kCFErrorDomainMach_VH: VarHandle by lazy { kCFErrorDomainMach_LAYOUT.varHandle() }

var kCFErrorDomainMach: MemorySegment
    get() = kCFErrorDomainMach_VH.get(kCFErrorDomainMach_SEGMENT) as MemorySegment
    set(value) = kCFErrorDomainMach_VH.set(kCFErrorDomainMach_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorDomainCocoa typedef const CFErrorDomain = (Declared(__CFString))*
 */
private val kCFErrorDomainCocoa_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorDomainCocoa_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorDomainCocoa").orElseThrow() }
private val kCFErrorDomainCocoa_VH: VarHandle by lazy { kCFErrorDomainCocoa_LAYOUT.varHandle() }

var kCFErrorDomainCocoa: MemorySegment
    get() = kCFErrorDomainCocoa_VH.get(kCFErrorDomainCocoa_SEGMENT) as MemorySegment
    set(value) = kCFErrorDomainCocoa_VH.set(kCFErrorDomainCocoa_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorLocalizedDescriptionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFErrorLocalizedDescriptionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorLocalizedDescriptionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorLocalizedDescriptionKey").orElseThrow() }
private val kCFErrorLocalizedDescriptionKey_VH: VarHandle by lazy { kCFErrorLocalizedDescriptionKey_LAYOUT.varHandle() }

var kCFErrorLocalizedDescriptionKey: MemorySegment
    get() = kCFErrorLocalizedDescriptionKey_VH.get(kCFErrorLocalizedDescriptionKey_SEGMENT) as MemorySegment
    set(value) = kCFErrorLocalizedDescriptionKey_VH.set(kCFErrorLocalizedDescriptionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorLocalizedFailureKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFErrorLocalizedFailureKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorLocalizedFailureKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorLocalizedFailureKey").orElseThrow() }
private val kCFErrorLocalizedFailureKey_VH: VarHandle by lazy { kCFErrorLocalizedFailureKey_LAYOUT.varHandle() }

var kCFErrorLocalizedFailureKey: MemorySegment
    get() = kCFErrorLocalizedFailureKey_VH.get(kCFErrorLocalizedFailureKey_SEGMENT) as MemorySegment
    set(value) = kCFErrorLocalizedFailureKey_VH.set(kCFErrorLocalizedFailureKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorLocalizedFailureReasonKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFErrorLocalizedFailureReasonKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorLocalizedFailureReasonKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorLocalizedFailureReasonKey").orElseThrow() }
private val kCFErrorLocalizedFailureReasonKey_VH: VarHandle by lazy { kCFErrorLocalizedFailureReasonKey_LAYOUT.varHandle() }

var kCFErrorLocalizedFailureReasonKey: MemorySegment
    get() = kCFErrorLocalizedFailureReasonKey_VH.get(kCFErrorLocalizedFailureReasonKey_SEGMENT) as MemorySegment
    set(value) = kCFErrorLocalizedFailureReasonKey_VH.set(kCFErrorLocalizedFailureReasonKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorLocalizedRecoverySuggestionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFErrorLocalizedRecoverySuggestionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorLocalizedRecoverySuggestionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorLocalizedRecoverySuggestionKey").orElseThrow() }
private val kCFErrorLocalizedRecoverySuggestionKey_VH: VarHandle by lazy { kCFErrorLocalizedRecoverySuggestionKey_LAYOUT.varHandle() }

var kCFErrorLocalizedRecoverySuggestionKey: MemorySegment
    get() = kCFErrorLocalizedRecoverySuggestionKey_VH.get(kCFErrorLocalizedRecoverySuggestionKey_SEGMENT) as MemorySegment
    set(value) = kCFErrorLocalizedRecoverySuggestionKey_VH.set(kCFErrorLocalizedRecoverySuggestionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorDescriptionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFErrorDescriptionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorDescriptionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorDescriptionKey").orElseThrow() }
private val kCFErrorDescriptionKey_VH: VarHandle by lazy { kCFErrorDescriptionKey_LAYOUT.varHandle() }

var kCFErrorDescriptionKey: MemorySegment
    get() = kCFErrorDescriptionKey_VH.get(kCFErrorDescriptionKey_SEGMENT) as MemorySegment
    set(value) = kCFErrorDescriptionKey_VH.set(kCFErrorDescriptionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorUnderlyingErrorKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFErrorUnderlyingErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorUnderlyingErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorUnderlyingErrorKey").orElseThrow() }
private val kCFErrorUnderlyingErrorKey_VH: VarHandle by lazy { kCFErrorUnderlyingErrorKey_LAYOUT.varHandle() }

var kCFErrorUnderlyingErrorKey: MemorySegment
    get() = kCFErrorUnderlyingErrorKey_VH.get(kCFErrorUnderlyingErrorKey_SEGMENT) as MemorySegment
    set(value) = kCFErrorUnderlyingErrorKey_VH.set(kCFErrorUnderlyingErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorURLKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFErrorURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorURLKey").orElseThrow() }
private val kCFErrorURLKey_VH: VarHandle by lazy { kCFErrorURLKey_LAYOUT.varHandle() }

var kCFErrorURLKey: MemorySegment
    get() = kCFErrorURLKey_VH.get(kCFErrorURLKey_SEGMENT) as MemorySegment
    set(value) = kCFErrorURLKey_VH.set(kCFErrorURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFErrorFilePathKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFErrorFilePathKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFErrorFilePathKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFErrorFilePathKey").orElseThrow() }
private val kCFErrorFilePathKey_VH: VarHandle by lazy { kCFErrorFilePathKey_LAYOUT.varHandle() }

var kCFErrorFilePathKey: MemorySegment
    get() = kCFErrorFilePathKey_VH.get(kCFErrorFilePathKey_SEGMENT) as MemorySegment
    set(value) = kCFErrorFilePathKey_VH.set(kCFErrorFilePathKey_SEGMENT, value)

/**
 * {@snippet lang=c : CFErrorCreate typedef CFErrorRef = (Declared(__CFError))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFErrorDomain = (Declared(__CFString))*,typedef CFIndex = Long,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFErrorCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFErrorCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFErrorCreate").orElseThrow()
private val CFErrorCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFErrorCreate_ADDR, CFErrorCreate_DESC)

fun CFErrorCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CFErrorCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFErrorCreateWithUserInfoKeysAndValues typedef CFErrorRef = (Declared(__CFError))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFErrorDomain = (Declared(__CFString))*,typedef CFIndex = Long,((Void)*)*,((Void)*)*,typedef CFIndex = Long)
 */
private val CFErrorCreateWithUserInfoKeysAndValues_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFErrorCreateWithUserInfoKeysAndValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFErrorCreateWithUserInfoKeysAndValues").orElseThrow()
private val CFErrorCreateWithUserInfoKeysAndValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFErrorCreateWithUserInfoKeysAndValues_ADDR, CFErrorCreateWithUserInfoKeysAndValues_DESC)

fun CFErrorCreateWithUserInfoKeysAndValues(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment, arg4: MemorySegment, arg5: Long): MemorySegment {
    try {
        return CFErrorCreateWithUserInfoKeysAndValues_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFErrorGetDomain typedef CFErrorDomain = (Declared(__CFString))*(typedef CFErrorRef = (Declared(__CFError))*)
 */
private val CFErrorGetDomain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFErrorGetDomain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFErrorGetDomain").orElseThrow()
private val CFErrorGetDomain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFErrorGetDomain_ADDR, CFErrorGetDomain_DESC)

fun CFErrorGetDomain(arg0: MemorySegment): MemorySegment {
    try {
        return CFErrorGetDomain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFErrorGetCode typedef CFIndex = Long(typedef CFErrorRef = (Declared(__CFError))*)
 */
private val CFErrorGetCode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFErrorGetCode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFErrorGetCode").orElseThrow()
private val CFErrorGetCode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFErrorGetCode_ADDR, CFErrorGetCode_DESC)

fun CFErrorGetCode(arg0: MemorySegment): Long {
    try {
        return CFErrorGetCode_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFErrorCopyUserInfo typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFErrorRef = (Declared(__CFError))*)
 */
private val CFErrorCopyUserInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFErrorCopyUserInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFErrorCopyUserInfo").orElseThrow()
private val CFErrorCopyUserInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFErrorCopyUserInfo_ADDR, CFErrorCopyUserInfo_DESC)

fun CFErrorCopyUserInfo(arg0: MemorySegment): MemorySegment {
    try {
        return CFErrorCopyUserInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFErrorCopyDescription typedef CFStringRef = (Declared(__CFString))*(typedef CFErrorRef = (Declared(__CFError))*)
 */
private val CFErrorCopyDescription_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFErrorCopyDescription_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFErrorCopyDescription").orElseThrow()
private val CFErrorCopyDescription_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFErrorCopyDescription_ADDR, CFErrorCopyDescription_DESC)

fun CFErrorCopyDescription(arg0: MemorySegment): MemorySegment {
    try {
        return CFErrorCopyDescription_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFErrorCopyFailureReason typedef CFStringRef = (Declared(__CFString))*(typedef CFErrorRef = (Declared(__CFError))*)
 */
private val CFErrorCopyFailureReason_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFErrorCopyFailureReason_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFErrorCopyFailureReason").orElseThrow()
private val CFErrorCopyFailureReason_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFErrorCopyFailureReason_ADDR, CFErrorCopyFailureReason_DESC)

fun CFErrorCopyFailureReason(arg0: MemorySegment): MemorySegment {
    try {
        return CFErrorCopyFailureReason_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFErrorCopyRecoverySuggestion typedef CFStringRef = (Declared(__CFString))*(typedef CFErrorRef = (Declared(__CFError))*)
 */
private val CFErrorCopyRecoverySuggestion_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFErrorCopyRecoverySuggestion_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFErrorCopyRecoverySuggestion").orElseThrow()
private val CFErrorCopyRecoverySuggestion_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFErrorCopyRecoverySuggestion_ADDR, CFErrorCopyRecoverySuggestion_DESC)

fun CFErrorCopyRecoverySuggestion(arg0: MemorySegment): MemorySegment {
    try {
        return CFErrorCopyRecoverySuggestion_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFStringGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFStringGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetTypeID").orElseThrow()
private val CFStringGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetTypeID_ADDR, CFStringGetTypeID_DESC)

fun CFStringGetTypeID(): Long {
    try {
        return CFStringGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithPascalString typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef ConstStr255Param = (UNSIGNED = Char)*,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringCreateWithPascalString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFStringCreateWithPascalString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithPascalString").orElseThrow()
private val CFStringCreateWithPascalString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithPascalString_ADDR, CFStringCreateWithPascalString_DESC)

fun CFStringCreateWithPascalString(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): MemorySegment {
    try {
        return CFStringCreateWithPascalString_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithCString typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(Char)*,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringCreateWithCString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFStringCreateWithCString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithCString").orElseThrow()
private val CFStringCreateWithCString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithCString_ADDR, CFStringCreateWithCString_DESC)

fun CFStringCreateWithCString(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): MemorySegment {
    try {
        return CFStringCreateWithCString_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithBytes typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long,typedef CFStringEncoding = UNSIGNED = Int,typedef Boolean = UNSIGNED = Char)
 */
private val CFStringCreateWithBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_BYTE)
private val CFStringCreateWithBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithBytes").orElseThrow()
private val CFStringCreateWithBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithBytes_ADDR, CFStringCreateWithBytes_DESC)

fun CFStringCreateWithBytes(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Int, arg4: Byte): MemorySegment {
    try {
        return CFStringCreateWithBytes_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithCharacters typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UniChar = UNSIGNED = Short)*,typedef CFIndex = Long)
 */
private val CFStringCreateWithCharacters_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFStringCreateWithCharacters_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithCharacters").orElseThrow()
private val CFStringCreateWithCharacters_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithCharacters_ADDR, CFStringCreateWithCharacters_DESC)

fun CFStringCreateWithCharacters(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFStringCreateWithCharacters_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithPascalStringNoCopy typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef ConstStr255Param = (UNSIGNED = Char)*,typedef CFStringEncoding = UNSIGNED = Int,typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFStringCreateWithPascalStringNoCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFStringCreateWithPascalStringNoCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithPascalStringNoCopy").orElseThrow()
private val CFStringCreateWithPascalStringNoCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithPascalStringNoCopy_ADDR, CFStringCreateWithPascalStringNoCopy_DESC)

fun CFStringCreateWithPascalStringNoCopy(arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: MemorySegment): MemorySegment {
    try {
        return CFStringCreateWithPascalStringNoCopy_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithCStringNoCopy typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(Char)*,typedef CFStringEncoding = UNSIGNED = Int,typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFStringCreateWithCStringNoCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFStringCreateWithCStringNoCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithCStringNoCopy").orElseThrow()
private val CFStringCreateWithCStringNoCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithCStringNoCopy_ADDR, CFStringCreateWithCStringNoCopy_DESC)

fun CFStringCreateWithCStringNoCopy(arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: MemorySegment): MemorySegment {
    try {
        return CFStringCreateWithCStringNoCopy_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithBytesNoCopy typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long,typedef CFStringEncoding = UNSIGNED = Int,typedef Boolean = UNSIGNED = Char,typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFStringCreateWithBytesNoCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFStringCreateWithBytesNoCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithBytesNoCopy").orElseThrow()
private val CFStringCreateWithBytesNoCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithBytesNoCopy_ADDR, CFStringCreateWithBytesNoCopy_DESC)

fun CFStringCreateWithBytesNoCopy(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Int, arg4: Byte, arg5: MemorySegment): MemorySegment {
    try {
        return CFStringCreateWithBytesNoCopy_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithCharactersNoCopy typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UniChar = UNSIGNED = Short)*,typedef CFIndex = Long,typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFStringCreateWithCharactersNoCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFStringCreateWithCharactersNoCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithCharactersNoCopy").orElseThrow()
private val CFStringCreateWithCharactersNoCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithCharactersNoCopy_ADDR, CFStringCreateWithCharactersNoCopy_DESC)

fun CFStringCreateWithCharactersNoCopy(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CFStringCreateWithCharactersNoCopy_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithSubstring typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange))
 */
private val CFStringCreateWithSubstring_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, CFRange.layout)
private val CFStringCreateWithSubstring_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithSubstring").orElseThrow()
private val CFStringCreateWithSubstring_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithSubstring_ADDR, CFStringCreateWithSubstring_DESC)

fun CFStringCreateWithSubstring(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFStringCreateWithSubstring_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateCopy typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateCopy").orElseThrow()
private val CFStringCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateCopy_ADDR, CFStringCreateCopy_DESC)

fun CFStringCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFStringCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithFormat typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringCreateWithFormat_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringCreateWithFormat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithFormat").orElseThrow()
private val CFStringCreateWithFormat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithFormat_ADDR, CFStringCreateWithFormat_DESC)

fun CFStringCreateWithFormat(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFStringCreateWithFormat_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithFormatAndArguments typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CFStringRef = (Declared(__CFString))*,typedef va_list = (Char)*)
 */
private val CFStringCreateWithFormatAndArguments_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringCreateWithFormatAndArguments_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithFormatAndArguments").orElseThrow()
private val CFStringCreateWithFormatAndArguments_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithFormatAndArguments_ADDR, CFStringCreateWithFormatAndArguments_DESC)

fun CFStringCreateWithFormatAndArguments(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFStringCreateWithFormatAndArguments_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateStringWithValidatedFormat typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFStringCreateStringWithValidatedFormat_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringCreateStringWithValidatedFormat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateStringWithValidatedFormat").orElseThrow()
private val CFStringCreateStringWithValidatedFormat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateStringWithValidatedFormat_ADDR, CFStringCreateStringWithValidatedFormat_DESC)

fun CFStringCreateStringWithValidatedFormat(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFStringCreateStringWithValidatedFormat_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateStringWithValidatedFormatAndArguments typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef va_list = (Char)*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFStringCreateStringWithValidatedFormatAndArguments_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringCreateStringWithValidatedFormatAndArguments_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateStringWithValidatedFormatAndArguments").orElseThrow()
private val CFStringCreateStringWithValidatedFormatAndArguments_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateStringWithValidatedFormatAndArguments_ADDR, CFStringCreateStringWithValidatedFormatAndArguments_DESC)

fun CFStringCreateStringWithValidatedFormatAndArguments(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment): MemorySegment {
    try {
        return CFStringCreateStringWithValidatedFormatAndArguments_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateMutable typedef CFMutableStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long)
 */
private val CFStringCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFStringCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateMutable").orElseThrow()
private val CFStringCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateMutable_ADDR, CFStringCreateMutable_DESC)

fun CFStringCreateMutable(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return CFStringCreateMutable_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateMutableCopy typedef CFMutableStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFStringCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateMutableCopy").orElseThrow()
private val CFStringCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateMutableCopy_ADDR, CFStringCreateMutableCopy_DESC)

fun CFStringCreateMutableCopy(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFStringCreateMutableCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateMutableWithExternalCharactersNoCopy typedef CFMutableStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UniChar = UNSIGNED = Short)*,typedef CFIndex = Long,typedef CFIndex = Long,typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFStringCreateMutableWithExternalCharactersNoCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFStringCreateMutableWithExternalCharactersNoCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateMutableWithExternalCharactersNoCopy").orElseThrow()
private val CFStringCreateMutableWithExternalCharactersNoCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateMutableWithExternalCharactersNoCopy_ADDR, CFStringCreateMutableWithExternalCharactersNoCopy_DESC)

fun CFStringCreateMutableWithExternalCharactersNoCopy(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long, arg4: MemorySegment): MemorySegment {
    try {
        return CFStringCreateMutableWithExternalCharactersNoCopy_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetLength typedef CFIndex = Long(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringGetLength_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFStringGetLength_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetLength").orElseThrow()
private val CFStringGetLength_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetLength_ADDR, CFStringGetLength_DESC)

fun CFStringGetLength(arg0: MemorySegment): Long {
    try {
        return CFStringGetLength_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetCharacterAtIndex typedef UniChar = UNSIGNED = Short(typedef CFStringRef = (Declared(__CFString))*,typedef CFIndex = Long)
 */
private val CFStringGetCharacterAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFStringGetCharacterAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetCharacterAtIndex").orElseThrow()
private val CFStringGetCharacterAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetCharacterAtIndex_ADDR, CFStringGetCharacterAtIndex_DESC)

fun CFStringGetCharacterAtIndex(arg0: MemorySegment, arg1: Long): Short {
    try {
        return CFStringGetCharacterAtIndex_HANDLE.invokeExact(arg0, arg1) as Short
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetCharacters Void(typedef CFStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange),(typedef UniChar = UNSIGNED = Short)*)
 */
private val CFStringGetCharacters_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFStringGetCharacters_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetCharacters").orElseThrow()
private val CFStringGetCharacters_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetCharacters_ADDR, CFStringGetCharacters_DESC)

fun CFStringGetCharacters(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFStringGetCharacters_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetPascalString typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*,typedef StringPtr = (UNSIGNED = Char)*,typedef CFIndex = Long,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringGetPascalString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT)
private val CFStringGetPascalString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetPascalString").orElseThrow()
private val CFStringGetPascalString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetPascalString_ADDR, CFStringGetPascalString_DESC)

fun CFStringGetPascalString(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Int): Byte {
    try {
        return CFStringGetPascalString_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetCString typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*,(Char)*,typedef CFIndex = Long,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringGetCString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT)
private val CFStringGetCString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetCString").orElseThrow()
private val CFStringGetCString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetCString_ADDR, CFStringGetCString_DESC)

fun CFStringGetCString(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Int): Byte {
    try {
        return CFStringGetCString_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

