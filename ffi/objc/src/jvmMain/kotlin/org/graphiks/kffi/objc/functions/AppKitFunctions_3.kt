package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : kCFURLFileLength typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileLength_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileLength_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileLength").orElseThrow() }
private val kCFURLFileLength_VH: VarHandle by lazy { kCFURLFileLength_LAYOUT.varHandle() }

var kCFURLFileLength: MemorySegment
    get() = kCFURLFileLength_VH.get(kCFURLFileLength_SEGMENT) as MemorySegment
    set(value) = kCFURLFileLength_VH.set(kCFURLFileLength_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileLastModificationTime typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileLastModificationTime_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileLastModificationTime_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileLastModificationTime").orElseThrow() }
private val kCFURLFileLastModificationTime_VH: VarHandle by lazy { kCFURLFileLastModificationTime_LAYOUT.varHandle() }

var kCFURLFileLastModificationTime: MemorySegment
    get() = kCFURLFileLastModificationTime_VH.get(kCFURLFileLastModificationTime_SEGMENT) as MemorySegment
    set(value) = kCFURLFileLastModificationTime_VH.set(kCFURLFileLastModificationTime_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFilePOSIXMode typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFilePOSIXMode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFilePOSIXMode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFilePOSIXMode").orElseThrow() }
private val kCFURLFilePOSIXMode_VH: VarHandle by lazy { kCFURLFilePOSIXMode_LAYOUT.varHandle() }

var kCFURLFilePOSIXMode: MemorySegment
    get() = kCFURLFilePOSIXMode_VH.get(kCFURLFilePOSIXMode_SEGMENT) as MemorySegment
    set(value) = kCFURLFilePOSIXMode_VH.set(kCFURLFilePOSIXMode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileOwnerID typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileOwnerID_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileOwnerID_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileOwnerID").orElseThrow() }
private val kCFURLFileOwnerID_VH: VarHandle by lazy { kCFURLFileOwnerID_LAYOUT.varHandle() }

var kCFURLFileOwnerID: MemorySegment
    get() = kCFURLFileOwnerID_VH.get(kCFURLFileOwnerID_SEGMENT) as MemorySegment
    set(value) = kCFURLFileOwnerID_VH.set(kCFURLFileOwnerID_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLHTTPStatusCode typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLHTTPStatusCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLHTTPStatusCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLHTTPStatusCode").orElseThrow() }
private val kCFURLHTTPStatusCode_VH: VarHandle by lazy { kCFURLHTTPStatusCode_LAYOUT.varHandle() }

var kCFURLHTTPStatusCode: MemorySegment
    get() = kCFURLHTTPStatusCode_VH.get(kCFURLHTTPStatusCode_SEGMENT) as MemorySegment
    set(value) = kCFURLHTTPStatusCode_VH.set(kCFURLHTTPStatusCode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLHTTPStatusLine typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLHTTPStatusLine_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLHTTPStatusLine_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLHTTPStatusLine").orElseThrow() }
private val kCFURLHTTPStatusLine_VH: VarHandle by lazy { kCFURLHTTPStatusLine_LAYOUT.varHandle() }

var kCFURLHTTPStatusLine: MemorySegment
    get() = kCFURLHTTPStatusLine_VH.get(kCFURLHTTPStatusLine_SEGMENT) as MemorySegment
    set(value) = kCFURLHTTPStatusLine_VH.set(kCFURLHTTPStatusLine_SEGMENT, value)

/**
 * {@snippet lang=c : CFUUIDGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFUUIDGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFUUIDGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUUIDGetTypeID").orElseThrow()
private val CFUUIDGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUUIDGetTypeID_ADDR, CFUUIDGetTypeID_DESC)

fun CFUUIDGetTypeID(): Long {
    try {
        return CFUUIDGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUUIDCreate typedef CFUUIDRef = (Declared(__CFUUID))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFUUIDCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFUUIDCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUUIDCreate").orElseThrow()
private val CFUUIDCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUUIDCreate_ADDR, CFUUIDCreate_DESC)

fun CFUUIDCreate(arg0: MemorySegment): MemorySegment {
    try {
        return CFUUIDCreate_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUUIDCreateWithBytes typedef CFUUIDRef = (Declared(__CFUUID))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char)
 */
private val CFUUIDCreateWithBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE)
private val CFUUIDCreateWithBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUUIDCreateWithBytes").orElseThrow()
private val CFUUIDCreateWithBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUUIDCreateWithBytes_ADDR, CFUUIDCreateWithBytes_DESC)

fun CFUUIDCreateWithBytes(arg0: MemorySegment, arg1: Byte, arg2: Byte, arg3: Byte, arg4: Byte, arg5: Byte, arg6: Byte, arg7: Byte, arg8: Byte, arg9: Byte, arg10: Byte, arg11: Byte, arg12: Byte, arg13: Byte, arg14: Byte, arg15: Byte, arg16: Byte): MemorySegment {
    try {
        return CFUUIDCreateWithBytes_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUUIDCreateFromString typedef CFUUIDRef = (Declared(__CFUUID))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFUUIDCreateFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFUUIDCreateFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUUIDCreateFromString").orElseThrow()
private val CFUUIDCreateFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUUIDCreateFromString_ADDR, CFUUIDCreateFromString_DESC)

fun CFUUIDCreateFromString(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFUUIDCreateFromString_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUUIDCreateString typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFUUIDCreateString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFUUIDCreateString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUUIDCreateString").orElseThrow()
private val CFUUIDCreateString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUUIDCreateString_ADDR, CFUUIDCreateString_DESC)

fun CFUUIDCreateString(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFUUIDCreateString_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUUIDGetConstantUUIDWithBytes typedef CFUUIDRef = (Declared(__CFUUID))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char,typedef UInt8 = UNSIGNED = Char)
 */
private val CFUUIDGetConstantUUIDWithBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE)
private val CFUUIDGetConstantUUIDWithBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUUIDGetConstantUUIDWithBytes").orElseThrow()
private val CFUUIDGetConstantUUIDWithBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUUIDGetConstantUUIDWithBytes_ADDR, CFUUIDGetConstantUUIDWithBytes_DESC)

fun CFUUIDGetConstantUUIDWithBytes(arg0: MemorySegment, arg1: Byte, arg2: Byte, arg3: Byte, arg4: Byte, arg5: Byte, arg6: Byte, arg7: Byte, arg8: Byte, arg9: Byte, arg10: Byte, arg11: Byte, arg12: Byte, arg13: Byte, arg14: Byte, arg15: Byte, arg16: Byte): MemorySegment {
    try {
        return CFUUIDGetConstantUUIDWithBytes_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUUIDGetUUIDBytes typedef CFUUIDBytes = Declared(CFUUIDBytes)(typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFUUIDGetUUIDBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(CFUUIDBytes.layout, ValueLayout.ADDRESS)
private val CFUUIDGetUUIDBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUUIDGetUUIDBytes").orElseThrow()
private val CFUUIDGetUUIDBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUUIDGetUUIDBytes_ADDR, CFUUIDGetUUIDBytes_DESC)

fun CFUUIDGetUUIDBytes(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CFUUIDGetUUIDBytes_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUUIDCreateFromUUIDBytes typedef CFUUIDRef = (Declared(__CFUUID))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFUUIDBytes = Declared(CFUUIDBytes))
 */
private val CFUUIDCreateFromUUIDBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CFUUIDBytes.layout)
private val CFUUIDCreateFromUUIDBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUUIDCreateFromUUIDBytes").orElseThrow()
private val CFUUIDCreateFromUUIDBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUUIDCreateFromUUIDBytes_ADDR, CFUUIDCreateFromUUIDBytes_DESC)

fun CFUUIDCreateFromUUIDBytes(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFUUIDCreateFromUUIDBytes_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCopyHomeDirectoryURL typedef CFURLRef = (Declared(__CFURL))*()
 */
private val CFCopyHomeDirectoryURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFCopyHomeDirectoryURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCopyHomeDirectoryURL").orElseThrow()
private val CFCopyHomeDirectoryURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCopyHomeDirectoryURL_ADDR, CFCopyHomeDirectoryURL_DESC)

fun CFCopyHomeDirectoryURL(): MemorySegment {
    try {
        return CFCopyHomeDirectoryURL_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFBundleInfoDictionaryVersionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFBundleInfoDictionaryVersionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBundleInfoDictionaryVersionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBundleInfoDictionaryVersionKey").orElseThrow() }
private val kCFBundleInfoDictionaryVersionKey_VH: VarHandle by lazy { kCFBundleInfoDictionaryVersionKey_LAYOUT.varHandle() }

var kCFBundleInfoDictionaryVersionKey: MemorySegment
    get() = kCFBundleInfoDictionaryVersionKey_VH.get(kCFBundleInfoDictionaryVersionKey_SEGMENT) as MemorySegment
    set(value) = kCFBundleInfoDictionaryVersionKey_VH.set(kCFBundleInfoDictionaryVersionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBundleExecutableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFBundleExecutableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBundleExecutableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBundleExecutableKey").orElseThrow() }
private val kCFBundleExecutableKey_VH: VarHandle by lazy { kCFBundleExecutableKey_LAYOUT.varHandle() }

var kCFBundleExecutableKey: MemorySegment
    get() = kCFBundleExecutableKey_VH.get(kCFBundleExecutableKey_SEGMENT) as MemorySegment
    set(value) = kCFBundleExecutableKey_VH.set(kCFBundleExecutableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBundleIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFBundleIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBundleIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBundleIdentifierKey").orElseThrow() }
private val kCFBundleIdentifierKey_VH: VarHandle by lazy { kCFBundleIdentifierKey_LAYOUT.varHandle() }

var kCFBundleIdentifierKey: MemorySegment
    get() = kCFBundleIdentifierKey_VH.get(kCFBundleIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFBundleIdentifierKey_VH.set(kCFBundleIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBundleVersionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFBundleVersionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBundleVersionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBundleVersionKey").orElseThrow() }
private val kCFBundleVersionKey_VH: VarHandle by lazy { kCFBundleVersionKey_LAYOUT.varHandle() }

var kCFBundleVersionKey: MemorySegment
    get() = kCFBundleVersionKey_VH.get(kCFBundleVersionKey_SEGMENT) as MemorySegment
    set(value) = kCFBundleVersionKey_VH.set(kCFBundleVersionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBundleDevelopmentRegionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFBundleDevelopmentRegionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBundleDevelopmentRegionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBundleDevelopmentRegionKey").orElseThrow() }
private val kCFBundleDevelopmentRegionKey_VH: VarHandle by lazy { kCFBundleDevelopmentRegionKey_LAYOUT.varHandle() }

var kCFBundleDevelopmentRegionKey: MemorySegment
    get() = kCFBundleDevelopmentRegionKey_VH.get(kCFBundleDevelopmentRegionKey_SEGMENT) as MemorySegment
    set(value) = kCFBundleDevelopmentRegionKey_VH.set(kCFBundleDevelopmentRegionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBundleNameKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFBundleNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBundleNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBundleNameKey").orElseThrow() }
private val kCFBundleNameKey_VH: VarHandle by lazy { kCFBundleNameKey_LAYOUT.varHandle() }

var kCFBundleNameKey: MemorySegment
    get() = kCFBundleNameKey_VH.get(kCFBundleNameKey_SEGMENT) as MemorySegment
    set(value) = kCFBundleNameKey_VH.set(kCFBundleNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBundleLocalizationsKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFBundleLocalizationsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBundleLocalizationsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBundleLocalizationsKey").orElseThrow() }
private val kCFBundleLocalizationsKey_VH: VarHandle by lazy { kCFBundleLocalizationsKey_LAYOUT.varHandle() }

var kCFBundleLocalizationsKey: MemorySegment
    get() = kCFBundleLocalizationsKey_VH.get(kCFBundleLocalizationsKey_SEGMENT) as MemorySegment
    set(value) = kCFBundleLocalizationsKey_VH.set(kCFBundleLocalizationsKey_SEGMENT, value)

/**
 * {@snippet lang=c : CFBundleGetMainBundle typedef CFBundleRef = (Declared(__CFBundle))*()
 */
private val CFBundleGetMainBundle_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFBundleGetMainBundle_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetMainBundle").orElseThrow()
private val CFBundleGetMainBundle_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetMainBundle_ADDR, CFBundleGetMainBundle_DESC)

fun CFBundleGetMainBundle(): MemorySegment {
    try {
        return CFBundleGetMainBundle_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetBundleWithIdentifier typedef CFBundleRef = (Declared(__CFBundle))*(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleGetBundleWithIdentifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetBundleWithIdentifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetBundleWithIdentifier").orElseThrow()
private val CFBundleGetBundleWithIdentifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetBundleWithIdentifier_ADDR, CFBundleGetBundleWithIdentifier_DESC)

fun CFBundleGetBundleWithIdentifier(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleGetBundleWithIdentifier_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetAllBundles typedef CFArrayRef = (Declared(__CFArray))*()
 */
private val CFBundleGetAllBundles_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFBundleGetAllBundles_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetAllBundles").orElseThrow()
private val CFBundleGetAllBundles_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetAllBundles_ADDR, CFBundleGetAllBundles_DESC)

fun CFBundleGetAllBundles(): MemorySegment {
    try {
        return CFBundleGetAllBundles_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFBundleGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFBundleGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetTypeID").orElseThrow()
private val CFBundleGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetTypeID_ADDR, CFBundleGetTypeID_DESC)

fun CFBundleGetTypeID(): Long {
    try {
        return CFBundleGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCreate typedef CFBundleRef = (Declared(__CFBundle))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFBundleCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCreate").orElseThrow()
private val CFBundleCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCreate_ADDR, CFBundleCreate_DESC)

fun CFBundleCreate(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFBundleCreate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCreateBundlesFromDirectory typedef CFArrayRef = (Declared(__CFArray))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleCreateBundlesFromDirectory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCreateBundlesFromDirectory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCreateBundlesFromDirectory").orElseThrow()
private val CFBundleCreateBundlesFromDirectory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCreateBundlesFromDirectory_ADDR, CFBundleCreateBundlesFromDirectory_DESC)

fun CFBundleCreateBundlesFromDirectory(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFBundleCreateBundlesFromDirectory_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyBundleURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopyBundleURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyBundleURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyBundleURL").orElseThrow()
private val CFBundleCopyBundleURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyBundleURL_ADDR, CFBundleCopyBundleURL_DESC)

fun CFBundleCopyBundleURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyBundleURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetValueForInfoDictionaryKey typedef CFTypeRef = (Void)*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleGetValueForInfoDictionaryKey_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetValueForInfoDictionaryKey_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetValueForInfoDictionaryKey").orElseThrow()
private val CFBundleGetValueForInfoDictionaryKey_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetValueForInfoDictionaryKey_ADDR, CFBundleGetValueForInfoDictionaryKey_DESC)

fun CFBundleGetValueForInfoDictionaryKey(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFBundleGetValueForInfoDictionaryKey_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetInfoDictionary typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleGetInfoDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetInfoDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetInfoDictionary").orElseThrow()
private val CFBundleGetInfoDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetInfoDictionary_ADDR, CFBundleGetInfoDictionary_DESC)

fun CFBundleGetInfoDictionary(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleGetInfoDictionary_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetLocalInfoDictionary typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleGetLocalInfoDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetLocalInfoDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetLocalInfoDictionary").orElseThrow()
private val CFBundleGetLocalInfoDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetLocalInfoDictionary_ADDR, CFBundleGetLocalInfoDictionary_DESC)

fun CFBundleGetLocalInfoDictionary(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleGetLocalInfoDictionary_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetPackageInfo Void(typedef CFBundleRef = (Declared(__CFBundle))*,(typedef UInt32 = UNSIGNED = Int)*,(typedef UInt32 = UNSIGNED = Int)*)
 */
private val CFBundleGetPackageInfo_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetPackageInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetPackageInfo").orElseThrow()
private val CFBundleGetPackageInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetPackageInfo_ADDR, CFBundleGetPackageInfo_DESC)

fun CFBundleGetPackageInfo(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFBundleGetPackageInfo_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetIdentifier typedef CFStringRef = (Declared(__CFString))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleGetIdentifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetIdentifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetIdentifier").orElseThrow()
private val CFBundleGetIdentifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetIdentifier_ADDR, CFBundleGetIdentifier_DESC)

fun CFBundleGetIdentifier(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleGetIdentifier_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetVersionNumber typedef UInt32 = UNSIGNED = Int(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleGetVersionNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFBundleGetVersionNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetVersionNumber").orElseThrow()
private val CFBundleGetVersionNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetVersionNumber_ADDR, CFBundleGetVersionNumber_DESC)

fun CFBundleGetVersionNumber(arg0: MemorySegment): Int {
    try {
        return CFBundleGetVersionNumber_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetDevelopmentRegion typedef CFStringRef = (Declared(__CFString))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleGetDevelopmentRegion_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetDevelopmentRegion_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetDevelopmentRegion").orElseThrow()
private val CFBundleGetDevelopmentRegion_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetDevelopmentRegion_ADDR, CFBundleGetDevelopmentRegion_DESC)

fun CFBundleGetDevelopmentRegion(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleGetDevelopmentRegion_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopySupportFilesDirectoryURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopySupportFilesDirectoryURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopySupportFilesDirectoryURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopySupportFilesDirectoryURL").orElseThrow()
private val CFBundleCopySupportFilesDirectoryURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopySupportFilesDirectoryURL_ADDR, CFBundleCopySupportFilesDirectoryURL_DESC)

fun CFBundleCopySupportFilesDirectoryURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopySupportFilesDirectoryURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyResourcesDirectoryURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopyResourcesDirectoryURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyResourcesDirectoryURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyResourcesDirectoryURL").orElseThrow()
private val CFBundleCopyResourcesDirectoryURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyResourcesDirectoryURL_ADDR, CFBundleCopyResourcesDirectoryURL_DESC)

fun CFBundleCopyResourcesDirectoryURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyResourcesDirectoryURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyPrivateFrameworksURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopyPrivateFrameworksURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyPrivateFrameworksURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyPrivateFrameworksURL").orElseThrow()
private val CFBundleCopyPrivateFrameworksURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyPrivateFrameworksURL_ADDR, CFBundleCopyPrivateFrameworksURL_DESC)

fun CFBundleCopyPrivateFrameworksURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyPrivateFrameworksURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopySharedFrameworksURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopySharedFrameworksURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopySharedFrameworksURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopySharedFrameworksURL").orElseThrow()
private val CFBundleCopySharedFrameworksURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopySharedFrameworksURL_ADDR, CFBundleCopySharedFrameworksURL_DESC)

fun CFBundleCopySharedFrameworksURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopySharedFrameworksURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopySharedSupportURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopySharedSupportURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopySharedSupportURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopySharedSupportURL").orElseThrow()
private val CFBundleCopySharedSupportURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopySharedSupportURL_ADDR, CFBundleCopySharedSupportURL_DESC)

fun CFBundleCopySharedSupportURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopySharedSupportURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyBuiltInPlugInsURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopyBuiltInPlugInsURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyBuiltInPlugInsURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyBuiltInPlugInsURL").orElseThrow()
private val CFBundleCopyBuiltInPlugInsURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyBuiltInPlugInsURL_ADDR, CFBundleCopyBuiltInPlugInsURL_DESC)

fun CFBundleCopyBuiltInPlugInsURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyBuiltInPlugInsURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyInfoDictionaryInDirectory typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFBundleCopyInfoDictionaryInDirectory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyInfoDictionaryInDirectory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyInfoDictionaryInDirectory").orElseThrow()
private val CFBundleCopyInfoDictionaryInDirectory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyInfoDictionaryInDirectory_ADDR, CFBundleCopyInfoDictionaryInDirectory_DESC)

fun CFBundleCopyInfoDictionaryInDirectory(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyInfoDictionaryInDirectory_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetPackageInfoInDirectory typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*,(typedef UInt32 = UNSIGNED = Int)*,(typedef UInt32 = UNSIGNED = Int)*)
 */
private val CFBundleGetPackageInfoInDirectory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetPackageInfoInDirectory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetPackageInfoInDirectory").orElseThrow()
private val CFBundleGetPackageInfoInDirectory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetPackageInfoInDirectory_ADDR, CFBundleGetPackageInfoInDirectory_DESC)

fun CFBundleGetPackageInfoInDirectory(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFBundleGetPackageInfoInDirectory_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyResourceURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleCopyResourceURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyResourceURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyResourceURL").orElseThrow()
private val CFBundleCopyResourceURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyResourceURL_ADDR, CFBundleCopyResourceURL_DESC)

fun CFBundleCopyResourceURL(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyResourceURL_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyResourceURLsOfType typedef CFArrayRef = (Declared(__CFArray))*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleCopyResourceURLsOfType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyResourceURLsOfType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyResourceURLsOfType").orElseThrow()
private val CFBundleCopyResourceURLsOfType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyResourceURLsOfType_ADDR, CFBundleCopyResourceURLsOfType_DESC)

fun CFBundleCopyResourceURLsOfType(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyResourceURLsOfType_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyLocalizedString typedef CFStringRef = (Declared(__CFString))*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleCopyLocalizedString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyLocalizedString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyLocalizedString").orElseThrow()
private val CFBundleCopyLocalizedString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyLocalizedString_ADDR, CFBundleCopyLocalizedString_DESC)

fun CFBundleCopyLocalizedString(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyLocalizedString_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyLocalizedStringForLocalizations typedef CFStringRef = (Declared(__CFString))*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFArrayRef = (Declared(__CFArray))*)
 */
private val CFBundleCopyLocalizedStringForLocalizations_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyLocalizedStringForLocalizations_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyLocalizedStringForLocalizations").orElseThrow()
private val CFBundleCopyLocalizedStringForLocalizations_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyLocalizedStringForLocalizations_ADDR, CFBundleCopyLocalizedStringForLocalizations_DESC)

fun CFBundleCopyLocalizedStringForLocalizations(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyLocalizedStringForLocalizations_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyResourceURLInDirectory typedef CFURLRef = (Declared(__CFURL))*(typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleCopyResourceURLInDirectory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyResourceURLInDirectory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyResourceURLInDirectory").orElseThrow()
private val CFBundleCopyResourceURLInDirectory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyResourceURLInDirectory_ADDR, CFBundleCopyResourceURLInDirectory_DESC)

fun CFBundleCopyResourceURLInDirectory(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyResourceURLInDirectory_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyResourceURLsOfTypeInDirectory typedef CFArrayRef = (Declared(__CFArray))*(typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleCopyResourceURLsOfTypeInDirectory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyResourceURLsOfTypeInDirectory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyResourceURLsOfTypeInDirectory").orElseThrow()
private val CFBundleCopyResourceURLsOfTypeInDirectory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyResourceURLsOfTypeInDirectory_ADDR, CFBundleCopyResourceURLsOfTypeInDirectory_DESC)

fun CFBundleCopyResourceURLsOfTypeInDirectory(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyResourceURLsOfTypeInDirectory_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyBundleLocalizations typedef CFArrayRef = (Declared(__CFArray))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopyBundleLocalizations_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyBundleLocalizations_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyBundleLocalizations").orElseThrow()
private val CFBundleCopyBundleLocalizations_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyBundleLocalizations_ADDR, CFBundleCopyBundleLocalizations_DESC)

fun CFBundleCopyBundleLocalizations(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyBundleLocalizations_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyPreferredLocalizationsFromArray typedef CFArrayRef = (Declared(__CFArray))*(typedef CFArrayRef = (Declared(__CFArray))*)
 */
private val CFBundleCopyPreferredLocalizationsFromArray_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyPreferredLocalizationsFromArray_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyPreferredLocalizationsFromArray").orElseThrow()
private val CFBundleCopyPreferredLocalizationsFromArray_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyPreferredLocalizationsFromArray_ADDR, CFBundleCopyPreferredLocalizationsFromArray_DESC)

fun CFBundleCopyPreferredLocalizationsFromArray(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyPreferredLocalizationsFromArray_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyLocalizationsForPreferences typedef CFArrayRef = (Declared(__CFArray))*(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFArrayRef = (Declared(__CFArray))*)
 */
private val CFBundleCopyLocalizationsForPreferences_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyLocalizationsForPreferences_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyLocalizationsForPreferences").orElseThrow()
private val CFBundleCopyLocalizationsForPreferences_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyLocalizationsForPreferences_ADDR, CFBundleCopyLocalizationsForPreferences_DESC)

fun CFBundleCopyLocalizationsForPreferences(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyLocalizationsForPreferences_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyResourceURLForLocalization typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleCopyResourceURLForLocalization_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyResourceURLForLocalization_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyResourceURLForLocalization").orElseThrow()
private val CFBundleCopyResourceURLForLocalization_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyResourceURLForLocalization_ADDR, CFBundleCopyResourceURLForLocalization_DESC)

fun CFBundleCopyResourceURLForLocalization(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyResourceURLForLocalization_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyResourceURLsOfTypeForLocalization typedef CFArrayRef = (Declared(__CFArray))*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleCopyResourceURLsOfTypeForLocalization_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyResourceURLsOfTypeForLocalization_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyResourceURLsOfTypeForLocalization").orElseThrow()
private val CFBundleCopyResourceURLsOfTypeForLocalization_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyResourceURLsOfTypeForLocalization_ADDR, CFBundleCopyResourceURLsOfTypeForLocalization_DESC)

fun CFBundleCopyResourceURLsOfTypeForLocalization(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyResourceURLsOfTypeForLocalization_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyInfoDictionaryForURL typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFBundleCopyInfoDictionaryForURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyInfoDictionaryForURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyInfoDictionaryForURL").orElseThrow()
private val CFBundleCopyInfoDictionaryForURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyInfoDictionaryForURL_ADDR, CFBundleCopyInfoDictionaryForURL_DESC)

fun CFBundleCopyInfoDictionaryForURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyInfoDictionaryForURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyLocalizationsForURL typedef CFArrayRef = (Declared(__CFArray))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFBundleCopyLocalizationsForURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyLocalizationsForURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyLocalizationsForURL").orElseThrow()
private val CFBundleCopyLocalizationsForURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyLocalizationsForURL_ADDR, CFBundleCopyLocalizationsForURL_DESC)

fun CFBundleCopyLocalizationsForURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyLocalizationsForURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyExecutableArchitecturesForURL typedef CFArrayRef = (Declared(__CFArray))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFBundleCopyExecutableArchitecturesForURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyExecutableArchitecturesForURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyExecutableArchitecturesForURL").orElseThrow()
private val CFBundleCopyExecutableArchitecturesForURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyExecutableArchitecturesForURL_ADDR, CFBundleCopyExecutableArchitecturesForURL_DESC)

fun CFBundleCopyExecutableArchitecturesForURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyExecutableArchitecturesForURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyExecutableURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopyExecutableURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyExecutableURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyExecutableURL").orElseThrow()
private val CFBundleCopyExecutableURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyExecutableURL_ADDR, CFBundleCopyExecutableURL_DESC)

fun CFBundleCopyExecutableURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyExecutableURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyExecutableArchitectures typedef CFArrayRef = (Declared(__CFArray))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleCopyExecutableArchitectures_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyExecutableArchitectures_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyExecutableArchitectures").orElseThrow()
private val CFBundleCopyExecutableArchitectures_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyExecutableArchitectures_ADDR, CFBundleCopyExecutableArchitectures_DESC)

fun CFBundleCopyExecutableArchitectures(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyExecutableArchitectures_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundlePreflightExecutable typedef Boolean = UNSIGNED = Char(typedef CFBundleRef = (Declared(__CFBundle))*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFBundlePreflightExecutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundlePreflightExecutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundlePreflightExecutable").orElseThrow()
private val CFBundlePreflightExecutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundlePreflightExecutable_ADDR, CFBundlePreflightExecutable_DESC)

fun CFBundlePreflightExecutable(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFBundlePreflightExecutable_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleLoadExecutableAndReturnError typedef Boolean = UNSIGNED = Char(typedef CFBundleRef = (Declared(__CFBundle))*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFBundleLoadExecutableAndReturnError_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleLoadExecutableAndReturnError_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleLoadExecutableAndReturnError").orElseThrow()
private val CFBundleLoadExecutableAndReturnError_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleLoadExecutableAndReturnError_ADDR, CFBundleLoadExecutableAndReturnError_DESC)

fun CFBundleLoadExecutableAndReturnError(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFBundleLoadExecutableAndReturnError_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleLoadExecutable typedef Boolean = UNSIGNED = Char(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleLoadExecutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFBundleLoadExecutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleLoadExecutable").orElseThrow()
private val CFBundleLoadExecutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleLoadExecutable_ADDR, CFBundleLoadExecutable_DESC)

fun CFBundleLoadExecutable(arg0: MemorySegment): Byte {
    try {
        return CFBundleLoadExecutable_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleIsExecutableLoaded typedef Boolean = UNSIGNED = Char(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleIsExecutableLoaded_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFBundleIsExecutableLoaded_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleIsExecutableLoaded").orElseThrow()
private val CFBundleIsExecutableLoaded_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleIsExecutableLoaded_ADDR, CFBundleIsExecutableLoaded_DESC)

fun CFBundleIsExecutableLoaded(arg0: MemorySegment): Byte {
    try {
        return CFBundleIsExecutableLoaded_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleUnloadExecutable Void(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleUnloadExecutable_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFBundleUnloadExecutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleUnloadExecutable").orElseThrow()
private val CFBundleUnloadExecutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleUnloadExecutable_ADDR, CFBundleUnloadExecutable_DESC)

fun CFBundleUnloadExecutable(arg0: MemorySegment): Unit {
    try {
        CFBundleUnloadExecutable_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetFunctionPointerForName (Void)*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleGetFunctionPointerForName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetFunctionPointerForName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetFunctionPointerForName").orElseThrow()
private val CFBundleGetFunctionPointerForName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetFunctionPointerForName_ADDR, CFBundleGetFunctionPointerForName_DESC)

fun CFBundleGetFunctionPointerForName(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFBundleGetFunctionPointerForName_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetFunctionPointersForNames Void(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFArrayRef = (Declared(__CFArray))*,((Void)*)*)
 */
private val CFBundleGetFunctionPointersForNames_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetFunctionPointersForNames_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetFunctionPointersForNames").orElseThrow()
private val CFBundleGetFunctionPointersForNames_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetFunctionPointersForNames_ADDR, CFBundleGetFunctionPointersForNames_DESC)

fun CFBundleGetFunctionPointersForNames(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFBundleGetFunctionPointersForNames_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetDataPointerForName (Void)*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleGetDataPointerForName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetDataPointerForName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetDataPointerForName").orElseThrow()
private val CFBundleGetDataPointerForName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetDataPointerForName_ADDR, CFBundleGetDataPointerForName_DESC)

fun CFBundleGetDataPointerForName(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFBundleGetDataPointerForName_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetDataPointersForNames Void(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFArrayRef = (Declared(__CFArray))*,((Void)*)*)
 */
private val CFBundleGetDataPointersForNames_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetDataPointersForNames_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetDataPointersForNames").orElseThrow()
private val CFBundleGetDataPointersForNames_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetDataPointersForNames_ADDR, CFBundleGetDataPointersForNames_DESC)

fun CFBundleGetDataPointersForNames(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFBundleGetDataPointersForNames_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCopyAuxiliaryExecutableURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFBundleCopyAuxiliaryExecutableURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleCopyAuxiliaryExecutableURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCopyAuxiliaryExecutableURL").orElseThrow()
private val CFBundleCopyAuxiliaryExecutableURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCopyAuxiliaryExecutableURL_ADDR, CFBundleCopyAuxiliaryExecutableURL_DESC)

fun CFBundleCopyAuxiliaryExecutableURL(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFBundleCopyAuxiliaryExecutableURL_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleIsExecutableLoadable typedef Boolean = UNSIGNED = Char(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleIsExecutableLoadable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFBundleIsExecutableLoadable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleIsExecutableLoadable").orElseThrow()
private val CFBundleIsExecutableLoadable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleIsExecutableLoadable_ADDR, CFBundleIsExecutableLoadable_DESC)

fun CFBundleIsExecutableLoadable(arg0: MemorySegment): Byte {
    try {
        return CFBundleIsExecutableLoadable_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleIsExecutableLoadableForURL typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFBundleIsExecutableLoadableForURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFBundleIsExecutableLoadableForURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleIsExecutableLoadableForURL").orElseThrow()
private val CFBundleIsExecutableLoadableForURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleIsExecutableLoadableForURL_ADDR, CFBundleIsExecutableLoadableForURL_DESC)

fun CFBundleIsExecutableLoadableForURL(arg0: MemorySegment): Byte {
    try {
        return CFBundleIsExecutableLoadableForURL_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleIsArchitectureLoadable typedef Boolean = UNSIGNED = Char(typedef cpu_type_t = Int)
 */
private val CFBundleIsArchitectureLoadable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.JAVA_INT)
private val CFBundleIsArchitectureLoadable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleIsArchitectureLoadable").orElseThrow()
private val CFBundleIsArchitectureLoadable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleIsArchitectureLoadable_ADDR, CFBundleIsArchitectureLoadable_DESC)

fun CFBundleIsArchitectureLoadable(arg0: Int): Byte {
    try {
        return CFBundleIsArchitectureLoadable_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleGetPlugIn typedef CFPlugInRef = (Declared(__CFBundle))*(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleGetPlugIn_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleGetPlugIn_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleGetPlugIn").orElseThrow()
private val CFBundleGetPlugIn_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleGetPlugIn_ADDR, CFBundleGetPlugIn_DESC)

fun CFBundleGetPlugIn(arg0: MemorySegment): MemorySegment {
    try {
        return CFBundleGetPlugIn_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleOpenBundleResourceMap typedef CFBundleRefNum = Int(typedef CFBundleRef = (Declared(__CFBundle))*)
 */
private val CFBundleOpenBundleResourceMap_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFBundleOpenBundleResourceMap_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleOpenBundleResourceMap").orElseThrow()
private val CFBundleOpenBundleResourceMap_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleOpenBundleResourceMap_ADDR, CFBundleOpenBundleResourceMap_DESC)

fun CFBundleOpenBundleResourceMap(arg0: MemorySegment): Int {
    try {
        return CFBundleOpenBundleResourceMap_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleOpenBundleResourceFiles typedef SInt32 = Int(typedef CFBundleRef = (Declared(__CFBundle))*,(typedef CFBundleRefNum = Int)*,(typedef CFBundleRefNum = Int)*)
 */
private val CFBundleOpenBundleResourceFiles_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFBundleOpenBundleResourceFiles_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleOpenBundleResourceFiles").orElseThrow()
private val CFBundleOpenBundleResourceFiles_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleOpenBundleResourceFiles_ADDR, CFBundleOpenBundleResourceFiles_DESC)

fun CFBundleOpenBundleResourceFiles(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Int {
    try {
        return CFBundleOpenBundleResourceFiles_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBundleCloseBundleResourceMap Void(typedef CFBundleRef = (Declared(__CFBundle))*,typedef CFBundleRefNum = Int)
 */
private val CFBundleCloseBundleResourceMap_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFBundleCloseBundleResourceMap_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBundleCloseBundleResourceMap").orElseThrow()
private val CFBundleCloseBundleResourceMap_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBundleCloseBundleResourceMap_ADDR, CFBundleCloseBundleResourceMap_DESC)

fun CFBundleCloseBundleResourceMap(arg0: MemorySegment, arg1: Int): Unit {
    try {
        CFBundleCloseBundleResourceMap_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFMessagePortGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFMessagePortGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortGetTypeID").orElseThrow()
private val CFMessagePortGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortGetTypeID_ADDR, CFMessagePortGetTypeID_DESC)

fun CFMessagePortGetTypeID(): Long {
    try {
        return CFMessagePortGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortCreateLocal typedef CFMessagePortRef = (Declared(__CFMessagePort))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFMessagePortCallBack = ((Declared(__CFData))*((Declared(__CFMessagePort))*,Int,(Declared(__CFData))*,(Void)*))*,(typedef CFMessagePortContext = Declared(CFMessagePortContext))*,(typedef Boolean = UNSIGNED = Char)*)
 */
private val CFMessagePortCreateLocal_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMessagePortCreateLocal_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortCreateLocal").orElseThrow()
private val CFMessagePortCreateLocal_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortCreateLocal_ADDR, CFMessagePortCreateLocal_DESC)

fun CFMessagePortCreateLocal(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFMessagePortCreateLocal_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortCreateRemote typedef CFMessagePortRef = (Declared(__CFMessagePort))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFMessagePortCreateRemote_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMessagePortCreateRemote_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortCreateRemote").orElseThrow()
private val CFMessagePortCreateRemote_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortCreateRemote_ADDR, CFMessagePortCreateRemote_DESC)

fun CFMessagePortCreateRemote(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFMessagePortCreateRemote_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortIsRemote typedef Boolean = UNSIGNED = Char(typedef CFMessagePortRef = (Declared(__CFMessagePort))*)
 */
private val CFMessagePortIsRemote_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFMessagePortIsRemote_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortIsRemote").orElseThrow()
private val CFMessagePortIsRemote_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortIsRemote_ADDR, CFMessagePortIsRemote_DESC)

fun CFMessagePortIsRemote(arg0: MemorySegment): Byte {
    try {
        return CFMessagePortIsRemote_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortGetName typedef CFStringRef = (Declared(__CFString))*(typedef CFMessagePortRef = (Declared(__CFMessagePort))*)
 */
private val CFMessagePortGetName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMessagePortGetName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortGetName").orElseThrow()
private val CFMessagePortGetName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortGetName_ADDR, CFMessagePortGetName_DESC)

fun CFMessagePortGetName(arg0: MemorySegment): MemorySegment {
    try {
        return CFMessagePortGetName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortSetName typedef Boolean = UNSIGNED = Char(typedef CFMessagePortRef = (Declared(__CFMessagePort))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFMessagePortSetName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMessagePortSetName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortSetName").orElseThrow()
private val CFMessagePortSetName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortSetName_ADDR, CFMessagePortSetName_DESC)

fun CFMessagePortSetName(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFMessagePortSetName_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortGetContext Void(typedef CFMessagePortRef = (Declared(__CFMessagePort))*,(typedef CFMessagePortContext = Declared(CFMessagePortContext))*)
 */
private val CFMessagePortGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMessagePortGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortGetContext").orElseThrow()
private val CFMessagePortGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortGetContext_ADDR, CFMessagePortGetContext_DESC)

fun CFMessagePortGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFMessagePortGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortInvalidate Void(typedef CFMessagePortRef = (Declared(__CFMessagePort))*)
 */
private val CFMessagePortInvalidate_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFMessagePortInvalidate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortInvalidate").orElseThrow()
private val CFMessagePortInvalidate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortInvalidate_ADDR, CFMessagePortInvalidate_DESC)

fun CFMessagePortInvalidate(arg0: MemorySegment): Unit {
    try {
        CFMessagePortInvalidate_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortIsValid typedef Boolean = UNSIGNED = Char(typedef CFMessagePortRef = (Declared(__CFMessagePort))*)
 */
private val CFMessagePortIsValid_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFMessagePortIsValid_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortIsValid").orElseThrow()
private val CFMessagePortIsValid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortIsValid_ADDR, CFMessagePortIsValid_DESC)

fun CFMessagePortIsValid(arg0: MemorySegment): Byte {
    try {
        return CFMessagePortIsValid_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortGetInvalidationCallBack typedef CFMessagePortInvalidationCallBack = (Void((Declared(__CFMessagePort))*,(Void)*))*(typedef CFMessagePortRef = (Declared(__CFMessagePort))*)
 */
private val CFMessagePortGetInvalidationCallBack_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMessagePortGetInvalidationCallBack_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortGetInvalidationCallBack").orElseThrow()
private val CFMessagePortGetInvalidationCallBack_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortGetInvalidationCallBack_ADDR, CFMessagePortGetInvalidationCallBack_DESC)

fun CFMessagePortGetInvalidationCallBack(arg0: MemorySegment): MemorySegment {
    try {
        return CFMessagePortGetInvalidationCallBack_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortSetInvalidationCallBack Void(typedef CFMessagePortRef = (Declared(__CFMessagePort))*,typedef CFMessagePortInvalidationCallBack = (Void((Declared(__CFMessagePort))*,(Void)*))*)
 */
private val CFMessagePortSetInvalidationCallBack_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMessagePortSetInvalidationCallBack_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortSetInvalidationCallBack").orElseThrow()
private val CFMessagePortSetInvalidationCallBack_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortSetInvalidationCallBack_ADDR, CFMessagePortSetInvalidationCallBack_DESC)

fun CFMessagePortSetInvalidationCallBack(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFMessagePortSetInvalidationCallBack_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortSendRequest typedef SInt32 = Int(typedef CFMessagePortRef = (Declared(__CFMessagePort))*,typedef SInt32 = Int,typedef CFDataRef = (Declared(__CFData))*,typedef CFTimeInterval = Double,typedef CFTimeInterval = Double,typedef CFStringRef = (Declared(__CFString))*,(typedef CFDataRef = (Declared(__CFData))*)*)
 */
private val CFMessagePortSendRequest_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMessagePortSendRequest_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortSendRequest").orElseThrow()
private val CFMessagePortSendRequest_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortSendRequest_ADDR, CFMessagePortSendRequest_DESC)

fun CFMessagePortSendRequest(arg0: MemorySegment, arg1: Int, arg2: MemorySegment, arg3: Double, arg4: Double, arg5: MemorySegment, arg6: MemorySegment): Int {
    try {
        return CFMessagePortSendRequest_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortCreateRunLoopSource typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFMessagePortRef = (Declared(__CFMessagePort))*,typedef CFIndex = Long)
 */
private val CFMessagePortCreateRunLoopSource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFMessagePortCreateRunLoopSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortCreateRunLoopSource").orElseThrow()
private val CFMessagePortCreateRunLoopSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortCreateRunLoopSource_ADDR, CFMessagePortCreateRunLoopSource_DESC)

fun CFMessagePortCreateRunLoopSource(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFMessagePortCreateRunLoopSource_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMessagePortSetDispatchQueue Void(typedef CFMessagePortRef = (Declared(__CFMessagePort))*,typedef __strong dispatch_queue_t = (Void)*)
 */
private val CFMessagePortSetDispatchQueue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMessagePortSetDispatchQueue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMessagePortSetDispatchQueue").orElseThrow()
private val CFMessagePortSetDispatchQueue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMessagePortSetDispatchQueue_ADDR, CFMessagePortSetDispatchQueue_DESC)

fun CFMessagePortSetDispatchQueue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFMessagePortSetDispatchQueue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFPlugInDynamicRegistrationKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPlugInDynamicRegistrationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPlugInDynamicRegistrationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPlugInDynamicRegistrationKey").orElseThrow() }
private val kCFPlugInDynamicRegistrationKey_VH: VarHandle by lazy { kCFPlugInDynamicRegistrationKey_LAYOUT.varHandle() }

var kCFPlugInDynamicRegistrationKey: MemorySegment
    get() = kCFPlugInDynamicRegistrationKey_VH.get(kCFPlugInDynamicRegistrationKey_SEGMENT) as MemorySegment
    set(value) = kCFPlugInDynamicRegistrationKey_VH.set(kCFPlugInDynamicRegistrationKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPlugInDynamicRegisterFunctionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPlugInDynamicRegisterFunctionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPlugInDynamicRegisterFunctionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPlugInDynamicRegisterFunctionKey").orElseThrow() }
private val kCFPlugInDynamicRegisterFunctionKey_VH: VarHandle by lazy { kCFPlugInDynamicRegisterFunctionKey_LAYOUT.varHandle() }

var kCFPlugInDynamicRegisterFunctionKey: MemorySegment
    get() = kCFPlugInDynamicRegisterFunctionKey_VH.get(kCFPlugInDynamicRegisterFunctionKey_SEGMENT) as MemorySegment
    set(value) = kCFPlugInDynamicRegisterFunctionKey_VH.set(kCFPlugInDynamicRegisterFunctionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPlugInUnloadFunctionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPlugInUnloadFunctionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPlugInUnloadFunctionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPlugInUnloadFunctionKey").orElseThrow() }
private val kCFPlugInUnloadFunctionKey_VH: VarHandle by lazy { kCFPlugInUnloadFunctionKey_LAYOUT.varHandle() }

var kCFPlugInUnloadFunctionKey: MemorySegment
    get() = kCFPlugInUnloadFunctionKey_VH.get(kCFPlugInUnloadFunctionKey_SEGMENT) as MemorySegment
    set(value) = kCFPlugInUnloadFunctionKey_VH.set(kCFPlugInUnloadFunctionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPlugInFactoriesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPlugInFactoriesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPlugInFactoriesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPlugInFactoriesKey").orElseThrow() }
private val kCFPlugInFactoriesKey_VH: VarHandle by lazy { kCFPlugInFactoriesKey_LAYOUT.varHandle() }

var kCFPlugInFactoriesKey: MemorySegment
    get() = kCFPlugInFactoriesKey_VH.get(kCFPlugInFactoriesKey_SEGMENT) as MemorySegment
    set(value) = kCFPlugInFactoriesKey_VH.set(kCFPlugInFactoriesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPlugInTypesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPlugInTypesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPlugInTypesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPlugInTypesKey").orElseThrow() }
private val kCFPlugInTypesKey_VH: VarHandle by lazy { kCFPlugInTypesKey_LAYOUT.varHandle() }

var kCFPlugInTypesKey: MemorySegment
    get() = kCFPlugInTypesKey_VH.get(kCFPlugInTypesKey_SEGMENT) as MemorySegment
    set(value) = kCFPlugInTypesKey_VH.set(kCFPlugInTypesKey_SEGMENT, value)

/**
 * {@snippet lang=c : CFPlugInGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFPlugInGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFPlugInGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInGetTypeID").orElseThrow()
private val CFPlugInGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInGetTypeID_ADDR, CFPlugInGetTypeID_DESC)

fun CFPlugInGetTypeID(): Long {
    try {
        return CFPlugInGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInCreate typedef CFPlugInRef = (Declared(__CFBundle))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFPlugInCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInCreate").orElseThrow()
private val CFPlugInCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInCreate_ADDR, CFPlugInCreate_DESC)

fun CFPlugInCreate(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFPlugInCreate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInGetBundle typedef CFBundleRef = (Declared(__CFBundle))*(typedef CFPlugInRef = (Declared(__CFBundle))*)
 */
private val CFPlugInGetBundle_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInGetBundle_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInGetBundle").orElseThrow()
private val CFPlugInGetBundle_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInGetBundle_ADDR, CFPlugInGetBundle_DESC)

fun CFPlugInGetBundle(arg0: MemorySegment): MemorySegment {
    try {
        return CFPlugInGetBundle_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInSetLoadOnDemand Void(typedef CFPlugInRef = (Declared(__CFBundle))*,typedef Boolean = UNSIGNED = Char)
 */
private val CFPlugInSetLoadOnDemand_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE)
private val CFPlugInSetLoadOnDemand_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInSetLoadOnDemand").orElseThrow()
private val CFPlugInSetLoadOnDemand_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInSetLoadOnDemand_ADDR, CFPlugInSetLoadOnDemand_DESC)

fun CFPlugInSetLoadOnDemand(arg0: MemorySegment, arg1: Byte): Unit {
    try {
        CFPlugInSetLoadOnDemand_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInIsLoadOnDemand typedef Boolean = UNSIGNED = Char(typedef CFPlugInRef = (Declared(__CFBundle))*)
 */
private val CFPlugInIsLoadOnDemand_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFPlugInIsLoadOnDemand_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInIsLoadOnDemand").orElseThrow()
private val CFPlugInIsLoadOnDemand_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInIsLoadOnDemand_ADDR, CFPlugInIsLoadOnDemand_DESC)

fun CFPlugInIsLoadOnDemand(arg0: MemorySegment): Byte {
    try {
        return CFPlugInIsLoadOnDemand_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInFindFactoriesForPlugInType typedef CFArrayRef = (Declared(__CFArray))*(typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFPlugInFindFactoriesForPlugInType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInFindFactoriesForPlugInType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInFindFactoriesForPlugInType").orElseThrow()
private val CFPlugInFindFactoriesForPlugInType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInFindFactoriesForPlugInType_ADDR, CFPlugInFindFactoriesForPlugInType_DESC)

fun CFPlugInFindFactoriesForPlugInType(arg0: MemorySegment): MemorySegment {
    try {
        return CFPlugInFindFactoriesForPlugInType_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInFindFactoriesForPlugInTypeInPlugIn typedef CFArrayRef = (Declared(__CFArray))*(typedef CFUUIDRef = (Declared(__CFUUID))*,typedef CFPlugInRef = (Declared(__CFBundle))*)
 */
private val CFPlugInFindFactoriesForPlugInTypeInPlugIn_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInFindFactoriesForPlugInTypeInPlugIn_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInFindFactoriesForPlugInTypeInPlugIn").orElseThrow()
private val CFPlugInFindFactoriesForPlugInTypeInPlugIn_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInFindFactoriesForPlugInTypeInPlugIn_ADDR, CFPlugInFindFactoriesForPlugInTypeInPlugIn_DESC)

fun CFPlugInFindFactoriesForPlugInTypeInPlugIn(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFPlugInFindFactoriesForPlugInTypeInPlugIn_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInInstanceCreate (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFUUIDRef = (Declared(__CFUUID))*,typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFPlugInInstanceCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInInstanceCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInInstanceCreate").orElseThrow()
private val CFPlugInInstanceCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInInstanceCreate_ADDR, CFPlugInInstanceCreate_DESC)

fun CFPlugInInstanceCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFPlugInInstanceCreate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInRegisterFactoryFunction typedef Boolean = UNSIGNED = Char(typedef CFUUIDRef = (Declared(__CFUUID))*,typedef CFPlugInFactoryFunction = ((Void)*((Declared(__CFAllocator))*,(Declared(__CFUUID))*))*)
 */
private val CFPlugInRegisterFactoryFunction_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInRegisterFactoryFunction_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInRegisterFactoryFunction").orElseThrow()
private val CFPlugInRegisterFactoryFunction_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInRegisterFactoryFunction_ADDR, CFPlugInRegisterFactoryFunction_DESC)

fun CFPlugInRegisterFactoryFunction(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFPlugInRegisterFactoryFunction_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInRegisterFactoryFunctionByName typedef Boolean = UNSIGNED = Char(typedef CFUUIDRef = (Declared(__CFUUID))*,typedef CFPlugInRef = (Declared(__CFBundle))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPlugInRegisterFactoryFunctionByName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInRegisterFactoryFunctionByName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInRegisterFactoryFunctionByName").orElseThrow()
private val CFPlugInRegisterFactoryFunctionByName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInRegisterFactoryFunctionByName_ADDR, CFPlugInRegisterFactoryFunctionByName_DESC)

fun CFPlugInRegisterFactoryFunctionByName(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFPlugInRegisterFactoryFunctionByName_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInUnregisterFactory typedef Boolean = UNSIGNED = Char(typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFPlugInUnregisterFactory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFPlugInUnregisterFactory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInUnregisterFactory").orElseThrow()
private val CFPlugInUnregisterFactory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInUnregisterFactory_ADDR, CFPlugInUnregisterFactory_DESC)

fun CFPlugInUnregisterFactory(arg0: MemorySegment): Byte {
    try {
        return CFPlugInUnregisterFactory_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInRegisterPlugInType typedef Boolean = UNSIGNED = Char(typedef CFUUIDRef = (Declared(__CFUUID))*,typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFPlugInRegisterPlugInType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInRegisterPlugInType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInRegisterPlugInType").orElseThrow()
private val CFPlugInRegisterPlugInType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInRegisterPlugInType_ADDR, CFPlugInRegisterPlugInType_DESC)

fun CFPlugInRegisterPlugInType(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFPlugInRegisterPlugInType_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInUnregisterPlugInType typedef Boolean = UNSIGNED = Char(typedef CFUUIDRef = (Declared(__CFUUID))*,typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFPlugInUnregisterPlugInType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInUnregisterPlugInType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInUnregisterPlugInType").orElseThrow()
private val CFPlugInUnregisterPlugInType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInUnregisterPlugInType_ADDR, CFPlugInUnregisterPlugInType_DESC)

fun CFPlugInUnregisterPlugInType(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFPlugInUnregisterPlugInType_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInAddInstanceForFactory Void(typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFPlugInAddInstanceForFactory_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFPlugInAddInstanceForFactory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInAddInstanceForFactory").orElseThrow()
private val CFPlugInAddInstanceForFactory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInAddInstanceForFactory_ADDR, CFPlugInAddInstanceForFactory_DESC)

fun CFPlugInAddInstanceForFactory(arg0: MemorySegment): Unit {
    try {
        CFPlugInAddInstanceForFactory_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInRemoveInstanceForFactory Void(typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFPlugInRemoveInstanceForFactory_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFPlugInRemoveInstanceForFactory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInRemoveInstanceForFactory").orElseThrow()
private val CFPlugInRemoveInstanceForFactory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInRemoveInstanceForFactory_ADDR, CFPlugInRemoveInstanceForFactory_DESC)

fun CFPlugInRemoveInstanceForFactory(arg0: MemorySegment): Unit {
    try {
        CFPlugInRemoveInstanceForFactory_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInInstanceGetInterfaceFunctionTable typedef Boolean = UNSIGNED = Char(typedef CFPlugInInstanceRef = (Declared(__CFPlugInInstance))*,typedef CFStringRef = (Declared(__CFString))*,((Void)*)*)
 */
private val CFPlugInInstanceGetInterfaceFunctionTable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInInstanceGetInterfaceFunctionTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInInstanceGetInterfaceFunctionTable").orElseThrow()
private val CFPlugInInstanceGetInterfaceFunctionTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInInstanceGetInterfaceFunctionTable_ADDR, CFPlugInInstanceGetInterfaceFunctionTable_DESC)

fun CFPlugInInstanceGetInterfaceFunctionTable(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFPlugInInstanceGetInterfaceFunctionTable_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInInstanceGetFactoryName typedef CFStringRef = (Declared(__CFString))*(typedef CFPlugInInstanceRef = (Declared(__CFPlugInInstance))*)
 */
private val CFPlugInInstanceGetFactoryName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInInstanceGetFactoryName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInInstanceGetFactoryName").orElseThrow()
private val CFPlugInInstanceGetFactoryName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInInstanceGetFactoryName_ADDR, CFPlugInInstanceGetFactoryName_DESC)

fun CFPlugInInstanceGetFactoryName(arg0: MemorySegment): MemorySegment {
    try {
        return CFPlugInInstanceGetFactoryName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInInstanceGetInstanceData (Void)*(typedef CFPlugInInstanceRef = (Declared(__CFPlugInInstance))*)
 */
private val CFPlugInInstanceGetInstanceData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInInstanceGetInstanceData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInInstanceGetInstanceData").orElseThrow()
private val CFPlugInInstanceGetInstanceData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInInstanceGetInstanceData_ADDR, CFPlugInInstanceGetInstanceData_DESC)

fun CFPlugInInstanceGetInstanceData(arg0: MemorySegment): MemorySegment {
    try {
        return CFPlugInInstanceGetInstanceData_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInInstanceGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFPlugInInstanceGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFPlugInInstanceGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInInstanceGetTypeID").orElseThrow()
private val CFPlugInInstanceGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInInstanceGetTypeID_ADDR, CFPlugInInstanceGetTypeID_DESC)

fun CFPlugInInstanceGetTypeID(): Long {
    try {
        return CFPlugInInstanceGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPlugInInstanceCreateWithInstanceDataSize typedef CFPlugInInstanceRef = (Declared(__CFPlugInInstance))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFPlugInInstanceDeallocateInstanceDataFunction = (Void((Void)*))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFPlugInInstanceGetInterfaceFunction = (UNSIGNED = Char((Declared(__CFPlugInInstance))*,(Declared(__CFString))*,((Void)*)*))*)
 */
private val CFPlugInInstanceCreateWithInstanceDataSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPlugInInstanceCreateWithInstanceDataSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPlugInInstanceCreateWithInstanceDataSize").orElseThrow()
private val CFPlugInInstanceCreateWithInstanceDataSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPlugInInstanceCreateWithInstanceDataSize_ADDR, CFPlugInInstanceCreateWithInstanceDataSize_DESC)

fun CFPlugInInstanceCreateWithInstanceDataSize(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFPlugInInstanceCreateWithInstanceDataSize_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFMachPortGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFMachPortGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortGetTypeID").orElseThrow()
private val CFMachPortGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortGetTypeID_ADDR, CFMachPortGetTypeID_DESC)

fun CFMachPortGetTypeID(): Long {
    try {
        return CFMachPortGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortCreate typedef CFMachPortRef = (Declared(__CFMachPort))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFMachPortCallBack = (Void((Declared(__CFMachPort))*,(Void)*,Long,(Void)*))*,(typedef CFMachPortContext = Declared(CFMachPortContext))*,(typedef Boolean = UNSIGNED = Char)*)
 */
private val CFMachPortCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMachPortCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortCreate").orElseThrow()
private val CFMachPortCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortCreate_ADDR, CFMachPortCreate_DESC)

fun CFMachPortCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFMachPortCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortCreateWithPort typedef CFMachPortRef = (Declared(__CFMachPort))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef mach_port_t = UNSIGNED = Int,typedef CFMachPortCallBack = (Void((Declared(__CFMachPort))*,(Void)*,Long,(Void)*))*,(typedef CFMachPortContext = Declared(CFMachPortContext))*,(typedef Boolean = UNSIGNED = Char)*)
 */
private val CFMachPortCreateWithPort_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMachPortCreateWithPort_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortCreateWithPort").orElseThrow()
private val CFMachPortCreateWithPort_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortCreateWithPort_ADDR, CFMachPortCreateWithPort_DESC)

fun CFMachPortCreateWithPort(arg0: MemorySegment, arg1: Int, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFMachPortCreateWithPort_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortGetPort typedef mach_port_t = UNSIGNED = Int(typedef CFMachPortRef = (Declared(__CFMachPort))*)
 */
private val CFMachPortGetPort_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFMachPortGetPort_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortGetPort").orElseThrow()
private val CFMachPortGetPort_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortGetPort_ADDR, CFMachPortGetPort_DESC)

fun CFMachPortGetPort(arg0: MemorySegment): Int {
    try {
        return CFMachPortGetPort_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortGetContext Void(typedef CFMachPortRef = (Declared(__CFMachPort))*,(typedef CFMachPortContext = Declared(CFMachPortContext))*)
 */
private val CFMachPortGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMachPortGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortGetContext").orElseThrow()
private val CFMachPortGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortGetContext_ADDR, CFMachPortGetContext_DESC)

fun CFMachPortGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFMachPortGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortInvalidate Void(typedef CFMachPortRef = (Declared(__CFMachPort))*)
 */
private val CFMachPortInvalidate_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFMachPortInvalidate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortInvalidate").orElseThrow()
private val CFMachPortInvalidate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortInvalidate_ADDR, CFMachPortInvalidate_DESC)

fun CFMachPortInvalidate(arg0: MemorySegment): Unit {
    try {
        CFMachPortInvalidate_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortIsValid typedef Boolean = UNSIGNED = Char(typedef CFMachPortRef = (Declared(__CFMachPort))*)
 */
private val CFMachPortIsValid_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFMachPortIsValid_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortIsValid").orElseThrow()
private val CFMachPortIsValid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortIsValid_ADDR, CFMachPortIsValid_DESC)

fun CFMachPortIsValid(arg0: MemorySegment): Byte {
    try {
        return CFMachPortIsValid_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortGetInvalidationCallBack typedef CFMachPortInvalidationCallBack = (Void((Declared(__CFMachPort))*,(Void)*))*(typedef CFMachPortRef = (Declared(__CFMachPort))*)
 */
private val CFMachPortGetInvalidationCallBack_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMachPortGetInvalidationCallBack_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortGetInvalidationCallBack").orElseThrow()
private val CFMachPortGetInvalidationCallBack_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortGetInvalidationCallBack_ADDR, CFMachPortGetInvalidationCallBack_DESC)

fun CFMachPortGetInvalidationCallBack(arg0: MemorySegment): MemorySegment {
    try {
        return CFMachPortGetInvalidationCallBack_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortSetInvalidationCallBack Void(typedef CFMachPortRef = (Declared(__CFMachPort))*,typedef CFMachPortInvalidationCallBack = (Void((Declared(__CFMachPort))*,(Void)*))*)
 */
private val CFMachPortSetInvalidationCallBack_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFMachPortSetInvalidationCallBack_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortSetInvalidationCallBack").orElseThrow()
private val CFMachPortSetInvalidationCallBack_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortSetInvalidationCallBack_ADDR, CFMachPortSetInvalidationCallBack_DESC)

fun CFMachPortSetInvalidationCallBack(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFMachPortSetInvalidationCallBack_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFMachPortCreateRunLoopSource typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFMachPortRef = (Declared(__CFMachPort))*,typedef CFIndex = Long)
 */
private val CFMachPortCreateRunLoopSource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFMachPortCreateRunLoopSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFMachPortCreateRunLoopSource").orElseThrow()
private val CFMachPortCreateRunLoopSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFMachPortCreateRunLoopSource_ADDR, CFMachPortCreateRunLoopSource_DESC)

fun CFMachPortCreateRunLoopSource(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFMachPortCreateRunLoopSource_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFAttributedStringGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFAttributedStringGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetTypeID").orElseThrow()
private val CFAttributedStringGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetTypeID_ADDR, CFAttributedStringGetTypeID_DESC)

fun CFAttributedStringGetTypeID(): Long {
    try {
        return CFAttributedStringGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringCreate typedef CFAttributedStringRef = (Declared(__CFAttributedString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFAttributedStringCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAttributedStringCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringCreate").orElseThrow()
private val CFAttributedStringCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringCreate_ADDR, CFAttributedStringCreate_DESC)

fun CFAttributedStringCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringCreate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringCreateWithSubstring typedef CFAttributedStringRef = (Declared(__CFAttributedString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFRange = Declared(CFRange))
 */
private val CFAttributedStringCreateWithSubstring_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, CFRange.layout)
private val CFAttributedStringCreateWithSubstring_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringCreateWithSubstring").orElseThrow()
private val CFAttributedStringCreateWithSubstring_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringCreateWithSubstring_ADDR, CFAttributedStringCreateWithSubstring_DESC)

fun CFAttributedStringCreateWithSubstring(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringCreateWithSubstring_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringCreateCopy typedef CFAttributedStringRef = (Declared(__CFAttributedString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFAttributedStringRef = (Declared(__CFAttributedString))*)
 */
private val CFAttributedStringCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAttributedStringCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringCreateCopy").orElseThrow()
private val CFAttributedStringCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringCreateCopy_ADDR, CFAttributedStringCreateCopy_DESC)

fun CFAttributedStringCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetString typedef CFStringRef = (Declared(__CFString))*(typedef CFAttributedStringRef = (Declared(__CFAttributedString))*)
 */
private val CFAttributedStringGetString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAttributedStringGetString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetString").orElseThrow()
private val CFAttributedStringGetString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetString_ADDR, CFAttributedStringGetString_DESC)

fun CFAttributedStringGetString(arg0: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringGetString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetLength typedef CFIndex = Long(typedef CFAttributedStringRef = (Declared(__CFAttributedString))*)
 */
private val CFAttributedStringGetLength_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFAttributedStringGetLength_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetLength").orElseThrow()
private val CFAttributedStringGetLength_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetLength_ADDR, CFAttributedStringGetLength_DESC)

fun CFAttributedStringGetLength(arg0: MemorySegment): Long {
    try {
        return CFAttributedStringGetLength_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetAttributes typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFIndex = Long,(typedef CFRange = Declared(CFRange))*)
 */
private val CFAttributedStringGetAttributes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFAttributedStringGetAttributes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetAttributes").orElseThrow()
private val CFAttributedStringGetAttributes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetAttributes_ADDR, CFAttributedStringGetAttributes_DESC)

fun CFAttributedStringGetAttributes(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringGetAttributes_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetAttribute typedef CFTypeRef = (Void)*(typedef CFAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFIndex = Long,typedef CFStringRef = (Declared(__CFString))*,(typedef CFRange = Declared(CFRange))*)
 */
private val CFAttributedStringGetAttribute_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAttributedStringGetAttribute_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetAttribute").orElseThrow()
private val CFAttributedStringGetAttribute_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetAttribute_ADDR, CFAttributedStringGetAttribute_DESC)

fun CFAttributedStringGetAttribute(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringGetAttribute_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetAttributesAndLongestEffectiveRange typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFIndex = Long,typedef CFRange = Declared(CFRange),(typedef CFRange = Declared(CFRange))*)
 */
private val CFAttributedStringGetAttributesAndLongestEffectiveRange_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, CFRange.layout, ValueLayout.ADDRESS)
private val CFAttributedStringGetAttributesAndLongestEffectiveRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetAttributesAndLongestEffectiveRange").orElseThrow()
private val CFAttributedStringGetAttributesAndLongestEffectiveRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetAttributesAndLongestEffectiveRange_ADDR, CFAttributedStringGetAttributesAndLongestEffectiveRange_DESC)

fun CFAttributedStringGetAttributesAndLongestEffectiveRange(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringGetAttributesAndLongestEffectiveRange_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetAttributeAndLongestEffectiveRange typedef CFTypeRef = (Void)*(typedef CFAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFIndex = Long,typedef CFStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange),(typedef CFRange = Declared(CFRange))*)
 */
private val CFAttributedStringGetAttributeAndLongestEffectiveRange_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFAttributedStringGetAttributeAndLongestEffectiveRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetAttributeAndLongestEffectiveRange").orElseThrow()
private val CFAttributedStringGetAttributeAndLongestEffectiveRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetAttributeAndLongestEffectiveRange_ADDR, CFAttributedStringGetAttributeAndLongestEffectiveRange_DESC)

fun CFAttributedStringGetAttributeAndLongestEffectiveRange(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringGetAttributeAndLongestEffectiveRange_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringCreateMutableCopy typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFAttributedStringRef = (Declared(__CFAttributedString))*)
 */
private val CFAttributedStringCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFAttributedStringCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringCreateMutableCopy").orElseThrow()
private val CFAttributedStringCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringCreateMutableCopy_ADDR, CFAttributedStringCreateMutableCopy_DESC)

fun CFAttributedStringCreateMutableCopy(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringCreateMutableCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringCreateMutable typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long)
 */
private val CFAttributedStringCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFAttributedStringCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringCreateMutable").orElseThrow()
private val CFAttributedStringCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringCreateMutable_ADDR, CFAttributedStringCreateMutable_DESC)

fun CFAttributedStringCreateMutable(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return CFAttributedStringCreateMutable_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringReplaceString Void(typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFRange = Declared(CFRange),typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFAttributedStringReplaceString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFAttributedStringReplaceString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringReplaceString").orElseThrow()
private val CFAttributedStringReplaceString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringReplaceString_ADDR, CFAttributedStringReplaceString_DESC)

fun CFAttributedStringReplaceString(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFAttributedStringReplaceString_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetMutableString typedef CFMutableStringRef = (Declared(__CFString))*(typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*)
 */
private val CFAttributedStringGetMutableString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAttributedStringGetMutableString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetMutableString").orElseThrow()
private val CFAttributedStringGetMutableString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetMutableString_ADDR, CFAttributedStringGetMutableString_DESC)

fun CFAttributedStringGetMutableString(arg0: MemorySegment): MemorySegment {
    try {
        return CFAttributedStringGetMutableString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringSetAttributes Void(typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFRange = Declared(CFRange),typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef Boolean = UNSIGNED = Char)
 */
private val CFAttributedStringSetAttributes_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE)
private val CFAttributedStringSetAttributes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringSetAttributes").orElseThrow()
private val CFAttributedStringSetAttributes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringSetAttributes_ADDR, CFAttributedStringSetAttributes_DESC)

fun CFAttributedStringSetAttributes(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Byte): Unit {
    try {
        CFAttributedStringSetAttributes_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringSetAttribute Void(typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFRange = Declared(CFRange),typedef CFStringRef = (Declared(__CFString))*,typedef CFTypeRef = (Void)*)
 */
private val CFAttributedStringSetAttribute_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAttributedStringSetAttribute_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringSetAttribute").orElseThrow()
private val CFAttributedStringSetAttribute_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringSetAttribute_ADDR, CFAttributedStringSetAttribute_DESC)

fun CFAttributedStringSetAttribute(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        CFAttributedStringSetAttribute_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringRemoveAttribute Void(typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFRange = Declared(CFRange),typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFAttributedStringRemoveAttribute_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFAttributedStringRemoveAttribute_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringRemoveAttribute").orElseThrow()
private val CFAttributedStringRemoveAttribute_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringRemoveAttribute_ADDR, CFAttributedStringRemoveAttribute_DESC)

fun CFAttributedStringRemoveAttribute(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFAttributedStringRemoveAttribute_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringReplaceAttributedString Void(typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFRange = Declared(CFRange),typedef CFAttributedStringRef = (Declared(__CFAttributedString))*)
 */
private val CFAttributedStringReplaceAttributedString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFAttributedStringReplaceAttributedString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringReplaceAttributedString").orElseThrow()
private val CFAttributedStringReplaceAttributedString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringReplaceAttributedString_ADDR, CFAttributedStringReplaceAttributedString_DESC)

fun CFAttributedStringReplaceAttributedString(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFAttributedStringReplaceAttributedString_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringBeginEditing Void(typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*)
 */
private val CFAttributedStringBeginEditing_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFAttributedStringBeginEditing_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringBeginEditing").orElseThrow()
private val CFAttributedStringBeginEditing_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringBeginEditing_ADDR, CFAttributedStringBeginEditing_DESC)

fun CFAttributedStringBeginEditing(arg0: MemorySegment): Unit {
    try {
        CFAttributedStringBeginEditing_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringEndEditing Void(typedef CFMutableAttributedStringRef = (Declared(__CFAttributedString))*)
 */
private val CFAttributedStringEndEditing_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFAttributedStringEndEditing_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringEndEditing").orElseThrow()
private val CFAttributedStringEndEditing_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringEndEditing_ADDR, CFAttributedStringEndEditing_DESC)

fun CFAttributedStringEndEditing(arg0: MemorySegment): Unit {
    try {
        CFAttributedStringEndEditing_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetBidiLevelsAndResolvedDirections Bool(typedef CFAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFRange = Declared(CFRange),typedef int8_t = SIGNED = Char,(typedef uint8_t = UNSIGNED = Char)*,(typedef uint8_t = UNSIGNED = Char)*)
 */
private val CFAttributedStringGetBidiLevelsAndResolvedDirections_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAttributedStringGetBidiLevelsAndResolvedDirections_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetBidiLevelsAndResolvedDirections").orElseThrow()
private val CFAttributedStringGetBidiLevelsAndResolvedDirections_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetBidiLevelsAndResolvedDirections_ADDR, CFAttributedStringGetBidiLevelsAndResolvedDirections_DESC)

fun CFAttributedStringGetBidiLevelsAndResolvedDirections(arg0: MemorySegment, arg1: MemorySegment, arg2: Byte, arg3: MemorySegment, arg4: MemorySegment): Boolean {
    try {
        return CFAttributedStringGetBidiLevelsAndResolvedDirections_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFAttributedStringGetStatisticalWritingDirections Bool(typedef CFAttributedStringRef = (Declared(__CFAttributedString))*,typedef CFRange = Declared(CFRange),typedef int8_t = SIGNED = Char,(typedef uint8_t = UNSIGNED = Char)*,(typedef uint8_t = UNSIGNED = Char)*)
 */
private val CFAttributedStringGetStatisticalWritingDirections_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFAttributedStringGetStatisticalWritingDirections_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFAttributedStringGetStatisticalWritingDirections").orElseThrow()
private val CFAttributedStringGetStatisticalWritingDirections_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFAttributedStringGetStatisticalWritingDirections_ADDR, CFAttributedStringGetStatisticalWritingDirections_DESC)

fun CFAttributedStringGetStatisticalWritingDirections(arg0: MemorySegment, arg1: MemorySegment, arg2: Byte, arg3: MemorySegment, arg4: MemorySegment): Boolean {
    try {
        return CFAttributedStringGetStatisticalWritingDirections_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLEnumeratorGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFURLEnumeratorGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFURLEnumeratorGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLEnumeratorGetTypeID").orElseThrow()
private val CFURLEnumeratorGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLEnumeratorGetTypeID_ADDR, CFURLEnumeratorGetTypeID_DESC)

fun CFURLEnumeratorGetTypeID(): Long {
    try {
        return CFURLEnumeratorGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLEnumeratorSkipDescendents Void(typedef CFURLEnumeratorRef = (Declared(__CFURLEnumerator))*)
 */
private val CFURLEnumeratorSkipDescendents_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFURLEnumeratorSkipDescendents_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLEnumeratorSkipDescendents").orElseThrow()
private val CFURLEnumeratorSkipDescendents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLEnumeratorSkipDescendents_ADDR, CFURLEnumeratorSkipDescendents_DESC)

fun CFURLEnumeratorSkipDescendents(arg0: MemorySegment): Unit {
    try {
        CFURLEnumeratorSkipDescendents_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLEnumeratorGetDescendentLevel typedef CFIndex = Long(typedef CFURLEnumeratorRef = (Declared(__CFURLEnumerator))*)
 */
private val CFURLEnumeratorGetDescendentLevel_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFURLEnumeratorGetDescendentLevel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLEnumeratorGetDescendentLevel").orElseThrow()
private val CFURLEnumeratorGetDescendentLevel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLEnumeratorGetDescendentLevel_ADDR, CFURLEnumeratorGetDescendentLevel_DESC)

fun CFURLEnumeratorGetDescendentLevel(arg0: MemorySegment): Long {
    try {
        return CFURLEnumeratorGetDescendentLevel_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLEnumeratorGetSourceDidChange typedef Boolean = UNSIGNED = Char(typedef CFURLEnumeratorRef = (Declared(__CFURLEnumerator))*)
 */
private val CFURLEnumeratorGetSourceDidChange_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFURLEnumeratorGetSourceDidChange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLEnumeratorGetSourceDidChange").orElseThrow()
private val CFURLEnumeratorGetSourceDidChange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLEnumeratorGetSourceDidChange_ADDR, CFURLEnumeratorGetSourceDidChange_DESC)

fun CFURLEnumeratorGetSourceDidChange(arg0: MemorySegment): Byte {
    try {
        return CFURLEnumeratorGetSourceDidChange_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecurityGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFFileSecurityGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFFileSecurityGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecurityGetTypeID").orElseThrow()
private val CFFileSecurityGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecurityGetTypeID_ADDR, CFFileSecurityGetTypeID_DESC)

fun CFFileSecurityGetTypeID(): Long {
    try {
        return CFFileSecurityGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecurityCreate typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFFileSecurityCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecurityCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecurityCreate").orElseThrow()
private val CFFileSecurityCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecurityCreate_ADDR, CFFileSecurityCreate_DESC)

fun CFFileSecurityCreate(arg0: MemorySegment): MemorySegment {
    try {
        return CFFileSecurityCreate_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecurityCreateCopy typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*)
 */
private val CFFileSecurityCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecurityCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecurityCreateCopy").orElseThrow()
private val CFFileSecurityCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecurityCreateCopy_ADDR, CFFileSecurityCreateCopy_DESC)

fun CFFileSecurityCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFFileSecurityCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecurityCopyOwnerUUID typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,(typedef CFUUIDRef = (Declared(__CFUUID))*)*)
 */
private val CFFileSecurityCopyOwnerUUID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecurityCopyOwnerUUID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecurityCopyOwnerUUID").orElseThrow()
private val CFFileSecurityCopyOwnerUUID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecurityCopyOwnerUUID_ADDR, CFFileSecurityCopyOwnerUUID_DESC)

fun CFFileSecurityCopyOwnerUUID(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFFileSecurityCopyOwnerUUID_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecuritySetOwnerUUID typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFFileSecuritySetOwnerUUID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecuritySetOwnerUUID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecuritySetOwnerUUID").orElseThrow()
private val CFFileSecuritySetOwnerUUID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecuritySetOwnerUUID_ADDR, CFFileSecuritySetOwnerUUID_DESC)

fun CFFileSecuritySetOwnerUUID(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFFileSecuritySetOwnerUUID_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecurityCopyGroupUUID typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,(typedef CFUUIDRef = (Declared(__CFUUID))*)*)
 */
private val CFFileSecurityCopyGroupUUID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecurityCopyGroupUUID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecurityCopyGroupUUID").orElseThrow()
private val CFFileSecurityCopyGroupUUID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecurityCopyGroupUUID_ADDR, CFFileSecurityCopyGroupUUID_DESC)

fun CFFileSecurityCopyGroupUUID(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFFileSecurityCopyGroupUUID_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecuritySetGroupUUID typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,typedef CFUUIDRef = (Declared(__CFUUID))*)
 */
private val CFFileSecuritySetGroupUUID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecuritySetGroupUUID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecuritySetGroupUUID").orElseThrow()
private val CFFileSecuritySetGroupUUID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecuritySetGroupUUID_ADDR, CFFileSecuritySetGroupUUID_DESC)

fun CFFileSecuritySetGroupUUID(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFFileSecuritySetGroupUUID_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecurityCopyAccessControlList typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,(typedef acl_t = (Declared(_acl))*)*)
 */
private val CFFileSecurityCopyAccessControlList_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecurityCopyAccessControlList_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecurityCopyAccessControlList").orElseThrow()
private val CFFileSecurityCopyAccessControlList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecurityCopyAccessControlList_ADDR, CFFileSecurityCopyAccessControlList_DESC)

fun CFFileSecurityCopyAccessControlList(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFFileSecurityCopyAccessControlList_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecuritySetAccessControlList typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,typedef acl_t = (Declared(_acl))*)
 */
private val CFFileSecuritySetAccessControlList_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecuritySetAccessControlList_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecuritySetAccessControlList").orElseThrow()
private val CFFileSecuritySetAccessControlList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecuritySetAccessControlList_ADDR, CFFileSecuritySetAccessControlList_DESC)

fun CFFileSecuritySetAccessControlList(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFFileSecuritySetAccessControlList_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecurityGetOwner typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,(typedef uid_t = UNSIGNED = Int)*)
 */
private val CFFileSecurityGetOwner_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecurityGetOwner_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecurityGetOwner").orElseThrow()
private val CFFileSecurityGetOwner_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecurityGetOwner_ADDR, CFFileSecurityGetOwner_DESC)

fun CFFileSecurityGetOwner(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFFileSecurityGetOwner_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecuritySetOwner typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,typedef uid_t = UNSIGNED = Int)
 */
private val CFFileSecuritySetOwner_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFFileSecuritySetOwner_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecuritySetOwner").orElseThrow()
private val CFFileSecuritySetOwner_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecuritySetOwner_ADDR, CFFileSecuritySetOwner_DESC)

fun CFFileSecuritySetOwner(arg0: MemorySegment, arg1: Int): Byte {
    try {
        return CFFileSecuritySetOwner_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecurityGetGroup typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,(typedef gid_t = UNSIGNED = Int)*)
 */
private val CFFileSecurityGetGroup_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecurityGetGroup_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecurityGetGroup").orElseThrow()
private val CFFileSecurityGetGroup_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecurityGetGroup_ADDR, CFFileSecurityGetGroup_DESC)

fun CFFileSecurityGetGroup(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFFileSecurityGetGroup_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecuritySetGroup typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,typedef gid_t = UNSIGNED = Int)
 */
private val CFFileSecuritySetGroup_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFFileSecuritySetGroup_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecuritySetGroup").orElseThrow()
private val CFFileSecuritySetGroup_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecuritySetGroup_ADDR, CFFileSecuritySetGroup_DESC)

fun CFFileSecuritySetGroup(arg0: MemorySegment, arg1: Int): Byte {
    try {
        return CFFileSecuritySetGroup_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecurityGetMode typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,(typedef mode_t = UNSIGNED = Short)*)
 */
private val CFFileSecurityGetMode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileSecurityGetMode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecurityGetMode").orElseThrow()
private val CFFileSecurityGetMode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecurityGetMode_ADDR, CFFileSecurityGetMode_DESC)

fun CFFileSecurityGetMode(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFFileSecurityGetMode_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileSecuritySetMode typedef Boolean = UNSIGNED = Char(typedef CFFileSecurityRef = (Declared(__CFFileSecurity))*,typedef mode_t = UNSIGNED = Short)
 */
private val CFFileSecuritySetMode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_SHORT)
private val CFFileSecuritySetMode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileSecuritySetMode").orElseThrow()
private val CFFileSecuritySetMode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileSecuritySetMode_ADDR, CFFileSecuritySetMode_DESC)

fun CFFileSecuritySetMode(arg0: MemorySegment, arg1: Short): Byte {
    try {
        return CFFileSecuritySetMode_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTokenizerCopyBestStringLanguage typedef CFStringRef = (Declared(__CFString))*(typedef CFStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange))
 */
private val CFStringTokenizerCopyBestStringLanguage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CFRange.layout)
private val CFStringTokenizerCopyBestStringLanguage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTokenizerCopyBestStringLanguage").orElseThrow()
private val CFStringTokenizerCopyBestStringLanguage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTokenizerCopyBestStringLanguage_ADDR, CFStringTokenizerCopyBestStringLanguage_DESC)

fun CFStringTokenizerCopyBestStringLanguage(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFStringTokenizerCopyBestStringLanguage_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTokenizerGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFStringTokenizerGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFStringTokenizerGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTokenizerGetTypeID").orElseThrow()
private val CFStringTokenizerGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTokenizerGetTypeID_ADDR, CFStringTokenizerGetTypeID_DESC)

fun CFStringTokenizerGetTypeID(): Long {
    try {
        return CFStringTokenizerGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTokenizerCreate typedef CFStringTokenizerRef = (Declared(__CFStringTokenizer))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange),typedef CFOptionFlags = UNSIGNED = Long,typedef CFLocaleRef = (Declared(__CFLocale))*)
 */
private val CFStringTokenizerCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFStringTokenizerCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTokenizerCreate").orElseThrow()
private val CFStringTokenizerCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTokenizerCreate_ADDR, CFStringTokenizerCreate_DESC)

fun CFStringTokenizerCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long, arg4: MemorySegment): MemorySegment {
    try {
        return CFStringTokenizerCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTokenizerSetString Void(typedef CFStringTokenizerRef = (Declared(__CFStringTokenizer))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange))
 */
private val CFStringTokenizerSetString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CFRange.layout)
private val CFStringTokenizerSetString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTokenizerSetString").orElseThrow()
private val CFStringTokenizerSetString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTokenizerSetString_ADDR, CFStringTokenizerSetString_DESC)

fun CFStringTokenizerSetString(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFStringTokenizerSetString_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTokenizerGetCurrentTokenRange typedef CFRange = Declared(CFRange)(typedef CFStringTokenizerRef = (Declared(__CFStringTokenizer))*)
 */
private val CFStringTokenizerGetCurrentTokenRange_DESC: FunctionDescriptor = FunctionDescriptor.of(CFRange.layout, ValueLayout.ADDRESS)
private val CFStringTokenizerGetCurrentTokenRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTokenizerGetCurrentTokenRange").orElseThrow()
private val CFStringTokenizerGetCurrentTokenRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTokenizerGetCurrentTokenRange_ADDR, CFStringTokenizerGetCurrentTokenRange_DESC)

fun CFStringTokenizerGetCurrentTokenRange(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CFStringTokenizerGetCurrentTokenRange_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTokenizerCopyCurrentTokenAttribute typedef CFTypeRef = (Void)*(typedef CFStringTokenizerRef = (Declared(__CFStringTokenizer))*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFStringTokenizerCopyCurrentTokenAttribute_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFStringTokenizerCopyCurrentTokenAttribute_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTokenizerCopyCurrentTokenAttribute").orElseThrow()
private val CFStringTokenizerCopyCurrentTokenAttribute_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTokenizerCopyCurrentTokenAttribute_ADDR, CFStringTokenizerCopyCurrentTokenAttribute_DESC)

fun CFStringTokenizerCopyCurrentTokenAttribute(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return CFStringTokenizerCopyCurrentTokenAttribute_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTokenizerGetCurrentSubTokens typedef CFIndex = Long(typedef CFStringTokenizerRef = (Declared(__CFStringTokenizer))*,(typedef CFRange = Declared(CFRange))*,typedef CFIndex = Long,typedef CFMutableArrayRef = (Declared(__CFArray))*)
 */
private val CFStringTokenizerGetCurrentSubTokens_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFStringTokenizerGetCurrentSubTokens_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTokenizerGetCurrentSubTokens").orElseThrow()
private val CFStringTokenizerGetCurrentSubTokens_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTokenizerGetCurrentSubTokens_ADDR, CFStringTokenizerGetCurrentSubTokens_DESC)

fun CFStringTokenizerGetCurrentSubTokens(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): Long {
    try {
        return CFStringTokenizerGetCurrentSubTokens_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileDescriptorGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFFileDescriptorGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFFileDescriptorGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileDescriptorGetTypeID").orElseThrow()
private val CFFileDescriptorGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileDescriptorGetTypeID_ADDR, CFFileDescriptorGetTypeID_DESC)

fun CFFileDescriptorGetTypeID(): Long {
    try {
        return CFFileDescriptorGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileDescriptorCreate typedef CFFileDescriptorRef = (Declared(__CFFileDescriptor))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFFileDescriptorNativeDescriptor = Int,typedef Boolean = UNSIGNED = Char,typedef CFFileDescriptorCallBack = (Void((Declared(__CFFileDescriptor))*,UNSIGNED = Long,(Void)*))*,(typedef CFFileDescriptorContext = Declared(CFFileDescriptorContext))*)
 */
private val CFFileDescriptorCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileDescriptorCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileDescriptorCreate").orElseThrow()
private val CFFileDescriptorCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileDescriptorCreate_ADDR, CFFileDescriptorCreate_DESC)

fun CFFileDescriptorCreate(arg0: MemorySegment, arg1: Int, arg2: Byte, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFFileDescriptorCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileDescriptorGetNativeDescriptor typedef CFFileDescriptorNativeDescriptor = Int(typedef CFFileDescriptorRef = (Declared(__CFFileDescriptor))*)
 */
private val CFFileDescriptorGetNativeDescriptor_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFFileDescriptorGetNativeDescriptor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileDescriptorGetNativeDescriptor").orElseThrow()
private val CFFileDescriptorGetNativeDescriptor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileDescriptorGetNativeDescriptor_ADDR, CFFileDescriptorGetNativeDescriptor_DESC)

fun CFFileDescriptorGetNativeDescriptor(arg0: MemorySegment): Int {
    try {
        return CFFileDescriptorGetNativeDescriptor_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileDescriptorGetContext Void(typedef CFFileDescriptorRef = (Declared(__CFFileDescriptor))*,(typedef CFFileDescriptorContext = Declared(CFFileDescriptorContext))*)
 */
private val CFFileDescriptorGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFFileDescriptorGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileDescriptorGetContext").orElseThrow()
private val CFFileDescriptorGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileDescriptorGetContext_ADDR, CFFileDescriptorGetContext_DESC)

fun CFFileDescriptorGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFFileDescriptorGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileDescriptorEnableCallBacks Void(typedef CFFileDescriptorRef = (Declared(__CFFileDescriptor))*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFFileDescriptorEnableCallBacks_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFFileDescriptorEnableCallBacks_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileDescriptorEnableCallBacks").orElseThrow()
private val CFFileDescriptorEnableCallBacks_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileDescriptorEnableCallBacks_ADDR, CFFileDescriptorEnableCallBacks_DESC)

fun CFFileDescriptorEnableCallBacks(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFFileDescriptorEnableCallBacks_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileDescriptorDisableCallBacks Void(typedef CFFileDescriptorRef = (Declared(__CFFileDescriptor))*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFFileDescriptorDisableCallBacks_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFFileDescriptorDisableCallBacks_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileDescriptorDisableCallBacks").orElseThrow()
private val CFFileDescriptorDisableCallBacks_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileDescriptorDisableCallBacks_ADDR, CFFileDescriptorDisableCallBacks_DESC)

fun CFFileDescriptorDisableCallBacks(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFFileDescriptorDisableCallBacks_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileDescriptorInvalidate Void(typedef CFFileDescriptorRef = (Declared(__CFFileDescriptor))*)
 */
private val CFFileDescriptorInvalidate_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFFileDescriptorInvalidate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileDescriptorInvalidate").orElseThrow()
private val CFFileDescriptorInvalidate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileDescriptorInvalidate_ADDR, CFFileDescriptorInvalidate_DESC)

fun CFFileDescriptorInvalidate(arg0: MemorySegment): Unit {
    try {
        CFFileDescriptorInvalidate_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileDescriptorIsValid typedef Boolean = UNSIGNED = Char(typedef CFFileDescriptorRef = (Declared(__CFFileDescriptor))*)
 */
private val CFFileDescriptorIsValid_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFFileDescriptorIsValid_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileDescriptorIsValid").orElseThrow()
private val CFFileDescriptorIsValid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileDescriptorIsValid_ADDR, CFFileDescriptorIsValid_DESC)

fun CFFileDescriptorIsValid(arg0: MemorySegment): Byte {
    try {
        return CFFileDescriptorIsValid_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFFileDescriptorCreateRunLoopSource typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFFileDescriptorRef = (Declared(__CFFileDescriptor))*,typedef CFIndex = Long)
 */
private val CFFileDescriptorCreateRunLoopSource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFFileDescriptorCreateRunLoopSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFFileDescriptorCreateRunLoopSource").orElseThrow()
private val CFFileDescriptorCreateRunLoopSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFFileDescriptorCreateRunLoopSource_ADDR, CFFileDescriptorCreateRunLoopSource_DESC)

fun CFFileDescriptorCreateRunLoopSource(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFFileDescriptorCreateRunLoopSource_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFUserNotificationGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFUserNotificationGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationGetTypeID").orElseThrow()
private val CFUserNotificationGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationGetTypeID_ADDR, CFUserNotificationGetTypeID_DESC)

fun CFUserNotificationGetTypeID(): Long {
    try {
        return CFUserNotificationGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationCreate typedef CFUserNotificationRef = (Declared(__CFUserNotification))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFTimeInterval = Double,typedef CFOptionFlags = UNSIGNED = Long,(typedef SInt32 = Int)*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFUserNotificationCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFUserNotificationCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationCreate").orElseThrow()
private val CFUserNotificationCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationCreate_ADDR, CFUserNotificationCreate_DESC)

fun CFUserNotificationCreate(arg0: MemorySegment, arg1: Double, arg2: Long, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFUserNotificationCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationReceiveResponse typedef SInt32 = Int(typedef CFUserNotificationRef = (Declared(__CFUserNotification))*,typedef CFTimeInterval = Double,(typedef CFOptionFlags = UNSIGNED = Long)*)
 */
private val CFUserNotificationReceiveResponse_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFUserNotificationReceiveResponse_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationReceiveResponse").orElseThrow()
private val CFUserNotificationReceiveResponse_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationReceiveResponse_ADDR, CFUserNotificationReceiveResponse_DESC)

fun CFUserNotificationReceiveResponse(arg0: MemorySegment, arg1: Double, arg2: MemorySegment): Int {
    try {
        return CFUserNotificationReceiveResponse_HANDLE.invokeExact(arg0, arg1, arg2) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationGetResponseValue typedef CFStringRef = (Declared(__CFString))*(typedef CFUserNotificationRef = (Declared(__CFUserNotification))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFIndex = Long)
 */
private val CFUserNotificationGetResponseValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFUserNotificationGetResponseValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationGetResponseValue").orElseThrow()
private val CFUserNotificationGetResponseValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationGetResponseValue_ADDR, CFUserNotificationGetResponseValue_DESC)

fun CFUserNotificationGetResponseValue(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFUserNotificationGetResponseValue_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationGetResponseDictionary typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFUserNotificationRef = (Declared(__CFUserNotification))*)
 */
private val CFUserNotificationGetResponseDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFUserNotificationGetResponseDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationGetResponseDictionary").orElseThrow()
private val CFUserNotificationGetResponseDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationGetResponseDictionary_ADDR, CFUserNotificationGetResponseDictionary_DESC)

fun CFUserNotificationGetResponseDictionary(arg0: MemorySegment): MemorySegment {
    try {
        return CFUserNotificationGetResponseDictionary_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationUpdate typedef SInt32 = Int(typedef CFUserNotificationRef = (Declared(__CFUserNotification))*,typedef CFTimeInterval = Double,typedef CFOptionFlags = UNSIGNED = Long,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFUserNotificationUpdate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFUserNotificationUpdate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationUpdate").orElseThrow()
private val CFUserNotificationUpdate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationUpdate_ADDR, CFUserNotificationUpdate_DESC)

fun CFUserNotificationUpdate(arg0: MemorySegment, arg1: Double, arg2: Long, arg3: MemorySegment): Int {
    try {
        return CFUserNotificationUpdate_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationCancel typedef SInt32 = Int(typedef CFUserNotificationRef = (Declared(__CFUserNotification))*)
 */
private val CFUserNotificationCancel_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFUserNotificationCancel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationCancel").orElseThrow()
private val CFUserNotificationCancel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationCancel_ADDR, CFUserNotificationCancel_DESC)

fun CFUserNotificationCancel(arg0: MemorySegment): Int {
    try {
        return CFUserNotificationCancel_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationCreateRunLoopSource typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFUserNotificationRef = (Declared(__CFUserNotification))*,typedef CFUserNotificationCallBack = (Void((Declared(__CFUserNotification))*,UNSIGNED = Long))*,typedef CFIndex = Long)
 */
private val CFUserNotificationCreateRunLoopSource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFUserNotificationCreateRunLoopSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationCreateRunLoopSource").orElseThrow()
private val CFUserNotificationCreateRunLoopSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationCreateRunLoopSource_ADDR, CFUserNotificationCreateRunLoopSource_DESC)

fun CFUserNotificationCreateRunLoopSource(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long): MemorySegment {
    try {
        return CFUserNotificationCreateRunLoopSource_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationDisplayNotice typedef SInt32 = Int(typedef CFTimeInterval = Double,typedef CFOptionFlags = UNSIGNED = Long,typedef CFURLRef = (Declared(__CFURL))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFUserNotificationDisplayNotice_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFUserNotificationDisplayNotice_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationDisplayNotice").orElseThrow()
private val CFUserNotificationDisplayNotice_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationDisplayNotice_ADDR, CFUserNotificationDisplayNotice_DESC)

fun CFUserNotificationDisplayNotice(arg0: Double, arg1: Long, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment, arg6: MemorySegment, arg7: MemorySegment): Int {
    try {
        return CFUserNotificationDisplayNotice_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFUserNotificationDisplayAlert typedef SInt32 = Int(typedef CFTimeInterval = Double,typedef CFOptionFlags = UNSIGNED = Long,typedef CFURLRef = (Declared(__CFURL))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,(typedef CFOptionFlags = UNSIGNED = Long)*)
 */
private val CFUserNotificationDisplayAlert_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFUserNotificationDisplayAlert_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFUserNotificationDisplayAlert").orElseThrow()
private val CFUserNotificationDisplayAlert_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFUserNotificationDisplayAlert_ADDR, CFUserNotificationDisplayAlert_DESC)

fun CFUserNotificationDisplayAlert(arg0: Double, arg1: Long, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment, arg6: MemorySegment, arg7: MemorySegment, arg8: MemorySegment, arg9: MemorySegment, arg10: MemorySegment): Int {
    try {
        return CFUserNotificationDisplayAlert_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFUserNotificationIconURLKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationIconURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationIconURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationIconURLKey").orElseThrow() }
private val kCFUserNotificationIconURLKey_VH: VarHandle by lazy { kCFUserNotificationIconURLKey_LAYOUT.varHandle() }

var kCFUserNotificationIconURLKey: MemorySegment
    get() = kCFUserNotificationIconURLKey_VH.get(kCFUserNotificationIconURLKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationIconURLKey_VH.set(kCFUserNotificationIconURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationSoundURLKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationSoundURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationSoundURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationSoundURLKey").orElseThrow() }
private val kCFUserNotificationSoundURLKey_VH: VarHandle by lazy { kCFUserNotificationSoundURLKey_LAYOUT.varHandle() }

var kCFUserNotificationSoundURLKey: MemorySegment
    get() = kCFUserNotificationSoundURLKey_VH.get(kCFUserNotificationSoundURLKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationSoundURLKey_VH.set(kCFUserNotificationSoundURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationLocalizationURLKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationLocalizationURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationLocalizationURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationLocalizationURLKey").orElseThrow() }
private val kCFUserNotificationLocalizationURLKey_VH: VarHandle by lazy { kCFUserNotificationLocalizationURLKey_LAYOUT.varHandle() }

var kCFUserNotificationLocalizationURLKey: MemorySegment
    get() = kCFUserNotificationLocalizationURLKey_VH.get(kCFUserNotificationLocalizationURLKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationLocalizationURLKey_VH.set(kCFUserNotificationLocalizationURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationAlertHeaderKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationAlertHeaderKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationAlertHeaderKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationAlertHeaderKey").orElseThrow() }
private val kCFUserNotificationAlertHeaderKey_VH: VarHandle by lazy { kCFUserNotificationAlertHeaderKey_LAYOUT.varHandle() }

var kCFUserNotificationAlertHeaderKey: MemorySegment
    get() = kCFUserNotificationAlertHeaderKey_VH.get(kCFUserNotificationAlertHeaderKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationAlertHeaderKey_VH.set(kCFUserNotificationAlertHeaderKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationAlertMessageKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationAlertMessageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationAlertMessageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationAlertMessageKey").orElseThrow() }
private val kCFUserNotificationAlertMessageKey_VH: VarHandle by lazy { kCFUserNotificationAlertMessageKey_LAYOUT.varHandle() }

var kCFUserNotificationAlertMessageKey: MemorySegment
    get() = kCFUserNotificationAlertMessageKey_VH.get(kCFUserNotificationAlertMessageKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationAlertMessageKey_VH.set(kCFUserNotificationAlertMessageKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationDefaultButtonTitleKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationDefaultButtonTitleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationDefaultButtonTitleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationDefaultButtonTitleKey").orElseThrow() }
private val kCFUserNotificationDefaultButtonTitleKey_VH: VarHandle by lazy { kCFUserNotificationDefaultButtonTitleKey_LAYOUT.varHandle() }

var kCFUserNotificationDefaultButtonTitleKey: MemorySegment
    get() = kCFUserNotificationDefaultButtonTitleKey_VH.get(kCFUserNotificationDefaultButtonTitleKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationDefaultButtonTitleKey_VH.set(kCFUserNotificationDefaultButtonTitleKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationAlternateButtonTitleKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationAlternateButtonTitleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationAlternateButtonTitleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationAlternateButtonTitleKey").orElseThrow() }
private val kCFUserNotificationAlternateButtonTitleKey_VH: VarHandle by lazy { kCFUserNotificationAlternateButtonTitleKey_LAYOUT.varHandle() }

var kCFUserNotificationAlternateButtonTitleKey: MemorySegment
    get() = kCFUserNotificationAlternateButtonTitleKey_VH.get(kCFUserNotificationAlternateButtonTitleKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationAlternateButtonTitleKey_VH.set(kCFUserNotificationAlternateButtonTitleKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationOtherButtonTitleKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationOtherButtonTitleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationOtherButtonTitleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationOtherButtonTitleKey").orElseThrow() }
private val kCFUserNotificationOtherButtonTitleKey_VH: VarHandle by lazy { kCFUserNotificationOtherButtonTitleKey_LAYOUT.varHandle() }

var kCFUserNotificationOtherButtonTitleKey: MemorySegment
    get() = kCFUserNotificationOtherButtonTitleKey_VH.get(kCFUserNotificationOtherButtonTitleKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationOtherButtonTitleKey_VH.set(kCFUserNotificationOtherButtonTitleKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationProgressIndicatorValueKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationProgressIndicatorValueKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationProgressIndicatorValueKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationProgressIndicatorValueKey").orElseThrow() }
private val kCFUserNotificationProgressIndicatorValueKey_VH: VarHandle by lazy { kCFUserNotificationProgressIndicatorValueKey_LAYOUT.varHandle() }

var kCFUserNotificationProgressIndicatorValueKey: MemorySegment
    get() = kCFUserNotificationProgressIndicatorValueKey_VH.get(kCFUserNotificationProgressIndicatorValueKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationProgressIndicatorValueKey_VH.set(kCFUserNotificationProgressIndicatorValueKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationPopUpTitlesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationPopUpTitlesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationPopUpTitlesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationPopUpTitlesKey").orElseThrow() }
private val kCFUserNotificationPopUpTitlesKey_VH: VarHandle by lazy { kCFUserNotificationPopUpTitlesKey_LAYOUT.varHandle() }

var kCFUserNotificationPopUpTitlesKey: MemorySegment
    get() = kCFUserNotificationPopUpTitlesKey_VH.get(kCFUserNotificationPopUpTitlesKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationPopUpTitlesKey_VH.set(kCFUserNotificationPopUpTitlesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationTextFieldTitlesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationTextFieldTitlesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationTextFieldTitlesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationTextFieldTitlesKey").orElseThrow() }
private val kCFUserNotificationTextFieldTitlesKey_VH: VarHandle by lazy { kCFUserNotificationTextFieldTitlesKey_LAYOUT.varHandle() }

var kCFUserNotificationTextFieldTitlesKey: MemorySegment
    get() = kCFUserNotificationTextFieldTitlesKey_VH.get(kCFUserNotificationTextFieldTitlesKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationTextFieldTitlesKey_VH.set(kCFUserNotificationTextFieldTitlesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationCheckBoxTitlesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationCheckBoxTitlesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationCheckBoxTitlesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationCheckBoxTitlesKey").orElseThrow() }
private val kCFUserNotificationCheckBoxTitlesKey_VH: VarHandle by lazy { kCFUserNotificationCheckBoxTitlesKey_LAYOUT.varHandle() }

var kCFUserNotificationCheckBoxTitlesKey: MemorySegment
    get() = kCFUserNotificationCheckBoxTitlesKey_VH.get(kCFUserNotificationCheckBoxTitlesKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationCheckBoxTitlesKey_VH.set(kCFUserNotificationCheckBoxTitlesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationTextFieldValuesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationTextFieldValuesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationTextFieldValuesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationTextFieldValuesKey").orElseThrow() }
private val kCFUserNotificationTextFieldValuesKey_VH: VarHandle by lazy { kCFUserNotificationTextFieldValuesKey_LAYOUT.varHandle() }

var kCFUserNotificationTextFieldValuesKey: MemorySegment
    get() = kCFUserNotificationTextFieldValuesKey_VH.get(kCFUserNotificationTextFieldValuesKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationTextFieldValuesKey_VH.set(kCFUserNotificationTextFieldValuesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationPopUpSelectionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationPopUpSelectionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationPopUpSelectionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationPopUpSelectionKey").orElseThrow() }
private val kCFUserNotificationPopUpSelectionKey_VH: VarHandle by lazy { kCFUserNotificationPopUpSelectionKey_LAYOUT.varHandle() }

var kCFUserNotificationPopUpSelectionKey: MemorySegment
    get() = kCFUserNotificationPopUpSelectionKey_VH.get(kCFUserNotificationPopUpSelectionKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationPopUpSelectionKey_VH.set(kCFUserNotificationPopUpSelectionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationAlertTopMostKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationAlertTopMostKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationAlertTopMostKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationAlertTopMostKey").orElseThrow() }
private val kCFUserNotificationAlertTopMostKey_VH: VarHandle by lazy { kCFUserNotificationAlertTopMostKey_LAYOUT.varHandle() }

var kCFUserNotificationAlertTopMostKey: MemorySegment
    get() = kCFUserNotificationAlertTopMostKey_VH.get(kCFUserNotificationAlertTopMostKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationAlertTopMostKey_VH.set(kCFUserNotificationAlertTopMostKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationKeyboardTypesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationKeyboardTypesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationKeyboardTypesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationKeyboardTypesKey").orElseThrow() }
private val kCFUserNotificationKeyboardTypesKey_VH: VarHandle by lazy { kCFUserNotificationKeyboardTypesKey_LAYOUT.varHandle() }

var kCFUserNotificationKeyboardTypesKey: MemorySegment
    get() = kCFUserNotificationKeyboardTypesKey_VH.get(kCFUserNotificationKeyboardTypesKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationKeyboardTypesKey_VH.set(kCFUserNotificationKeyboardTypesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationAlertAccessibilityIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationAlertAccessibilityIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationAlertAccessibilityIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationAlertAccessibilityIdentifierKey").orElseThrow() }
private val kCFUserNotificationAlertAccessibilityIdentifierKey_VH: VarHandle by lazy { kCFUserNotificationAlertAccessibilityIdentifierKey_LAYOUT.varHandle() }

var kCFUserNotificationAlertAccessibilityIdentifierKey: MemorySegment
    get() = kCFUserNotificationAlertAccessibilityIdentifierKey_VH.get(kCFUserNotificationAlertAccessibilityIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationAlertAccessibilityIdentifierKey_VH.set(kCFUserNotificationAlertAccessibilityIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationDefaultButtonAccessibilityIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationDefaultButtonAccessibilityIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationDefaultButtonAccessibilityIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationDefaultButtonAccessibilityIdentifierKey").orElseThrow() }
private val kCFUserNotificationDefaultButtonAccessibilityIdentifierKey_VH: VarHandle by lazy { kCFUserNotificationDefaultButtonAccessibilityIdentifierKey_LAYOUT.varHandle() }

var kCFUserNotificationDefaultButtonAccessibilityIdentifierKey: MemorySegment
    get() = kCFUserNotificationDefaultButtonAccessibilityIdentifierKey_VH.get(kCFUserNotificationDefaultButtonAccessibilityIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationDefaultButtonAccessibilityIdentifierKey_VH.set(kCFUserNotificationDefaultButtonAccessibilityIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationAlternateButtonAccessibilityIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationAlternateButtonAccessibilityIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationAlternateButtonAccessibilityIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationAlternateButtonAccessibilityIdentifierKey").orElseThrow() }
private val kCFUserNotificationAlternateButtonAccessibilityIdentifierKey_VH: VarHandle by lazy { kCFUserNotificationAlternateButtonAccessibilityIdentifierKey_LAYOUT.varHandle() }

var kCFUserNotificationAlternateButtonAccessibilityIdentifierKey: MemorySegment
    get() = kCFUserNotificationAlternateButtonAccessibilityIdentifierKey_VH.get(kCFUserNotificationAlternateButtonAccessibilityIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationAlternateButtonAccessibilityIdentifierKey_VH.set(kCFUserNotificationAlternateButtonAccessibilityIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFUserNotificationOtherButtonAccessibilityIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFUserNotificationOtherButtonAccessibilityIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFUserNotificationOtherButtonAccessibilityIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFUserNotificationOtherButtonAccessibilityIdentifierKey").orElseThrow() }
private val kCFUserNotificationOtherButtonAccessibilityIdentifierKey_VH: VarHandle by lazy { kCFUserNotificationOtherButtonAccessibilityIdentifierKey_LAYOUT.varHandle() }

var kCFUserNotificationOtherButtonAccessibilityIdentifierKey: MemorySegment
    get() = kCFUserNotificationOtherButtonAccessibilityIdentifierKey_VH.get(kCFUserNotificationOtherButtonAccessibilityIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFUserNotificationOtherButtonAccessibilityIdentifierKey_VH.set(kCFUserNotificationOtherButtonAccessibilityIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : CFXMLNodeGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFXMLNodeGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFXMLNodeGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLNodeGetTypeID").orElseThrow()
private val CFXMLNodeGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLNodeGetTypeID_ADDR, CFXMLNodeGetTypeID_DESC)

fun CFXMLNodeGetTypeID(): Long {
    try {
        return CFXMLNodeGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLNodeCreateCopy typedef CFXMLNodeRef = (Declared(__CFXMLNode))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFXMLNodeRef = (Declared(__CFXMLNode))*)
 */
private val CFXMLNodeCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLNodeCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLNodeCreateCopy").orElseThrow()
private val CFXMLNodeCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLNodeCreateCopy_ADDR, CFXMLNodeCreateCopy_DESC)

fun CFXMLNodeCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFXMLNodeCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLNodeGetString typedef CFStringRef = (Declared(__CFString))*(typedef CFXMLNodeRef = (Declared(__CFXMLNode))*)
 */
private val CFXMLNodeGetString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLNodeGetString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLNodeGetString").orElseThrow()
private val CFXMLNodeGetString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLNodeGetString_ADDR, CFXMLNodeGetString_DESC)

fun CFXMLNodeGetString(arg0: MemorySegment): MemorySegment {
    try {
        return CFXMLNodeGetString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLNodeGetInfoPtr (Void)*(typedef CFXMLNodeRef = (Declared(__CFXMLNode))*)
 */
private val CFXMLNodeGetInfoPtr_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLNodeGetInfoPtr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLNodeGetInfoPtr").orElseThrow()
private val CFXMLNodeGetInfoPtr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLNodeGetInfoPtr_ADDR, CFXMLNodeGetInfoPtr_DESC)

fun CFXMLNodeGetInfoPtr(arg0: MemorySegment): MemorySegment {
    try {
        return CFXMLNodeGetInfoPtr_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLNodeGetVersion typedef CFIndex = Long(typedef CFXMLNodeRef = (Declared(__CFXMLNode))*)
 */
private val CFXMLNodeGetVersion_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFXMLNodeGetVersion_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLNodeGetVersion").orElseThrow()
private val CFXMLNodeGetVersion_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLNodeGetVersion_ADDR, CFXMLNodeGetVersion_DESC)

fun CFXMLNodeGetVersion(arg0: MemorySegment): Long {
    try {
        return CFXMLNodeGetVersion_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLTreeCreateWithNode typedef CFXMLTreeRef = (Declared(__CFTree))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFXMLNodeRef = (Declared(__CFXMLNode))*)
 */
private val CFXMLTreeCreateWithNode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLTreeCreateWithNode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLTreeCreateWithNode").orElseThrow()
private val CFXMLTreeCreateWithNode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLTreeCreateWithNode_ADDR, CFXMLTreeCreateWithNode_DESC)

fun CFXMLTreeCreateWithNode(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFXMLTreeCreateWithNode_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLTreeGetNode typedef CFXMLNodeRef = (Declared(__CFXMLNode))*(typedef CFXMLTreeRef = (Declared(__CFTree))*)
 */
private val CFXMLTreeGetNode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLTreeGetNode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLTreeGetNode").orElseThrow()
private val CFXMLTreeGetNode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLTreeGetNode_ADDR, CFXMLTreeGetNode_DESC)

fun CFXMLTreeGetNode(arg0: MemorySegment): MemorySegment {
    try {
        return CFXMLTreeGetNode_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFXMLParserGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFXMLParserGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserGetTypeID").orElseThrow()
private val CFXMLParserGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserGetTypeID_ADDR, CFXMLParserGetTypeID_DESC)

fun CFXMLParserGetTypeID(): Long {
    try {
        return CFXMLParserGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserCreate typedef CFXMLParserRef = (Declared(__CFXMLParser))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFIndex = Long,(typedef CFXMLParserCallBacks = Declared(CFXMLParserCallBacks))*,(typedef CFXMLParserContext = Declared(CFXMLParserContext))*)
 */
private val CFXMLParserCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLParserCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserCreate").orElseThrow()
private val CFXMLParserCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserCreate_ADDR, CFXMLParserCreate_DESC)

fun CFXMLParserCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long, arg4: Long, arg5: MemorySegment, arg6: MemorySegment): MemorySegment {
    try {
        return CFXMLParserCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserCreateWithDataFromURL typedef CFXMLParserRef = (Declared(__CFXMLParser))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFIndex = Long,(typedef CFXMLParserCallBacks = Declared(CFXMLParserCallBacks))*,(typedef CFXMLParserContext = Declared(CFXMLParserContext))*)
 */
private val CFXMLParserCreateWithDataFromURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLParserCreateWithDataFromURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserCreateWithDataFromURL").orElseThrow()
private val CFXMLParserCreateWithDataFromURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserCreateWithDataFromURL_ADDR, CFXMLParserCreateWithDataFromURL_DESC)

fun CFXMLParserCreateWithDataFromURL(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long, arg4: MemorySegment, arg5: MemorySegment): MemorySegment {
    try {
        return CFXMLParserCreateWithDataFromURL_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserGetContext Void(typedef CFXMLParserRef = (Declared(__CFXMLParser))*,(typedef CFXMLParserContext = Declared(CFXMLParserContext))*)
 */
private val CFXMLParserGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLParserGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserGetContext").orElseThrow()
private val CFXMLParserGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserGetContext_ADDR, CFXMLParserGetContext_DESC)

fun CFXMLParserGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFXMLParserGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserGetCallBacks Void(typedef CFXMLParserRef = (Declared(__CFXMLParser))*,(typedef CFXMLParserCallBacks = Declared(CFXMLParserCallBacks))*)
 */
private val CFXMLParserGetCallBacks_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLParserGetCallBacks_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserGetCallBacks").orElseThrow()
private val CFXMLParserGetCallBacks_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserGetCallBacks_ADDR, CFXMLParserGetCallBacks_DESC)

fun CFXMLParserGetCallBacks(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFXMLParserGetCallBacks_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserGetSourceURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFXMLParserRef = (Declared(__CFXMLParser))*)
 */
private val CFXMLParserGetSourceURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLParserGetSourceURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserGetSourceURL").orElseThrow()
private val CFXMLParserGetSourceURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserGetSourceURL_ADDR, CFXMLParserGetSourceURL_DESC)

fun CFXMLParserGetSourceURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFXMLParserGetSourceURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserGetLocation typedef CFIndex = Long(typedef CFXMLParserRef = (Declared(__CFXMLParser))*)
 */
private val CFXMLParserGetLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFXMLParserGetLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserGetLocation").orElseThrow()
private val CFXMLParserGetLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserGetLocation_ADDR, CFXMLParserGetLocation_DESC)

fun CFXMLParserGetLocation(arg0: MemorySegment): Long {
    try {
        return CFXMLParserGetLocation_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserGetLineNumber typedef CFIndex = Long(typedef CFXMLParserRef = (Declared(__CFXMLParser))*)
 */
private val CFXMLParserGetLineNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFXMLParserGetLineNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserGetLineNumber").orElseThrow()
private val CFXMLParserGetLineNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserGetLineNumber_ADDR, CFXMLParserGetLineNumber_DESC)

fun CFXMLParserGetLineNumber(arg0: MemorySegment): Long {
    try {
        return CFXMLParserGetLineNumber_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserGetDocument (Void)*(typedef CFXMLParserRef = (Declared(__CFXMLParser))*)
 */
private val CFXMLParserGetDocument_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLParserGetDocument_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserGetDocument").orElseThrow()
private val CFXMLParserGetDocument_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserGetDocument_ADDR, CFXMLParserGetDocument_DESC)

fun CFXMLParserGetDocument(arg0: MemorySegment): MemorySegment {
    try {
        return CFXMLParserGetDocument_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserCopyErrorDescription typedef CFStringRef = (Declared(__CFString))*(typedef CFXMLParserRef = (Declared(__CFXMLParser))*)
 */
private val CFXMLParserCopyErrorDescription_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLParserCopyErrorDescription_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserCopyErrorDescription").orElseThrow()
private val CFXMLParserCopyErrorDescription_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserCopyErrorDescription_ADDR, CFXMLParserCopyErrorDescription_DESC)

fun CFXMLParserCopyErrorDescription(arg0: MemorySegment): MemorySegment {
    try {
        return CFXMLParserCopyErrorDescription_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLParserParse typedef Boolean = UNSIGNED = Char(typedef CFXMLParserRef = (Declared(__CFXMLParser))*)
 */
private val CFXMLParserParse_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFXMLParserParse_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLParserParse").orElseThrow()
private val CFXMLParserParse_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLParserParse_ADDR, CFXMLParserParse_DESC)

fun CFXMLParserParse(arg0: MemorySegment): Byte {
    try {
        return CFXMLParserParse_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLTreeCreateFromData typedef CFXMLTreeRef = (Declared(__CFTree))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFIndex = Long)
 */
private val CFXMLTreeCreateFromData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFXMLTreeCreateFromData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLTreeCreateFromData").orElseThrow()
private val CFXMLTreeCreateFromData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLTreeCreateFromData_ADDR, CFXMLTreeCreateFromData_DESC)

fun CFXMLTreeCreateFromData(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long, arg4: Long): MemorySegment {
    try {
        return CFXMLTreeCreateFromData_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLTreeCreateFromDataWithError typedef CFXMLTreeRef = (Declared(__CFTree))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFIndex = Long,(typedef CFDictionaryRef = (Declared(__CFDictionary))*)*)
 */
private val CFXMLTreeCreateFromDataWithError_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFXMLTreeCreateFromDataWithError_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLTreeCreateFromDataWithError").orElseThrow()
private val CFXMLTreeCreateFromDataWithError_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLTreeCreateFromDataWithError_ADDR, CFXMLTreeCreateFromDataWithError_DESC)

fun CFXMLTreeCreateFromDataWithError(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long, arg4: Long, arg5: MemorySegment): MemorySegment {
    try {
        return CFXMLTreeCreateFromDataWithError_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLTreeCreateWithDataFromURL typedef CFXMLTreeRef = (Declared(__CFTree))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFIndex = Long)
 */
private val CFXMLTreeCreateWithDataFromURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFXMLTreeCreateWithDataFromURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLTreeCreateWithDataFromURL").orElseThrow()
private val CFXMLTreeCreateWithDataFromURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLTreeCreateWithDataFromURL_ADDR, CFXMLTreeCreateWithDataFromURL_DESC)

fun CFXMLTreeCreateWithDataFromURL(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long): MemorySegment {
    try {
        return CFXMLTreeCreateWithDataFromURL_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLTreeCreateXMLData typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFXMLTreeRef = (Declared(__CFTree))*)
 */
private val CFXMLTreeCreateXMLData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLTreeCreateXMLData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLTreeCreateXMLData").orElseThrow()
private val CFXMLTreeCreateXMLData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLTreeCreateXMLData_ADDR, CFXMLTreeCreateXMLData_DESC)

fun CFXMLTreeCreateXMLData(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFXMLTreeCreateXMLData_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLCreateStringByEscapingEntities typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFXMLCreateStringByEscapingEntities_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLCreateStringByEscapingEntities_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLCreateStringByEscapingEntities").orElseThrow()
private val CFXMLCreateStringByEscapingEntities_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLCreateStringByEscapingEntities_ADDR, CFXMLCreateStringByEscapingEntities_DESC)

fun CFXMLCreateStringByEscapingEntities(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFXMLCreateStringByEscapingEntities_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFXMLCreateStringByUnescapingEntities typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFXMLCreateStringByUnescapingEntities_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFXMLCreateStringByUnescapingEntities_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFXMLCreateStringByUnescapingEntities").orElseThrow()
private val CFXMLCreateStringByUnescapingEntities_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFXMLCreateStringByUnescapingEntities_ADDR, CFXMLCreateStringByUnescapingEntities_DESC)

fun CFXMLCreateStringByUnescapingEntities(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFXMLCreateStringByUnescapingEntities_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFXMLTreeErrorDescription typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFXMLTreeErrorDescription_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFXMLTreeErrorDescription_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFXMLTreeErrorDescription").orElseThrow() }
private val kCFXMLTreeErrorDescription_VH: VarHandle by lazy { kCFXMLTreeErrorDescription_LAYOUT.varHandle() }

var kCFXMLTreeErrorDescription: MemorySegment
    get() = kCFXMLTreeErrorDescription_VH.get(kCFXMLTreeErrorDescription_SEGMENT) as MemorySegment
    set(value) = kCFXMLTreeErrorDescription_VH.set(kCFXMLTreeErrorDescription_SEGMENT, value)

/**
 * {@snippet lang=c : kCFXMLTreeErrorLineNumber typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFXMLTreeErrorLineNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFXMLTreeErrorLineNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFXMLTreeErrorLineNumber").orElseThrow() }
private val kCFXMLTreeErrorLineNumber_VH: VarHandle by lazy { kCFXMLTreeErrorLineNumber_LAYOUT.varHandle() }

var kCFXMLTreeErrorLineNumber: MemorySegment
    get() = kCFXMLTreeErrorLineNumber_VH.get(kCFXMLTreeErrorLineNumber_SEGMENT) as MemorySegment
    set(value) = kCFXMLTreeErrorLineNumber_VH.set(kCFXMLTreeErrorLineNumber_SEGMENT, value)

/**
 * {@snippet lang=c : kCFXMLTreeErrorLocation typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFXMLTreeErrorLocation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFXMLTreeErrorLocation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFXMLTreeErrorLocation").orElseThrow() }
private val kCFXMLTreeErrorLocation_VH: VarHandle by lazy { kCFXMLTreeErrorLocation_LAYOUT.varHandle() }

var kCFXMLTreeErrorLocation: MemorySegment
    get() = kCFXMLTreeErrorLocation_VH.get(kCFXMLTreeErrorLocation_SEGMENT) as MemorySegment
    set(value) = kCFXMLTreeErrorLocation_VH.set(kCFXMLTreeErrorLocation_SEGMENT, value)

/**
 * {@snippet lang=c : kCFXMLTreeErrorStatusCode typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFXMLTreeErrorStatusCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFXMLTreeErrorStatusCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFXMLTreeErrorStatusCode").orElseThrow() }
private val kCFXMLTreeErrorStatusCode_VH: VarHandle by lazy { kCFXMLTreeErrorStatusCode_LAYOUT.varHandle() }

var kCFXMLTreeErrorStatusCode: MemorySegment
    get() = kCFXMLTreeErrorStatusCode_VH.get(kCFXMLTreeErrorStatusCode_SEGMENT) as MemorySegment
    set(value) = kCFXMLTreeErrorStatusCode_VH.set(kCFXMLTreeErrorStatusCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSFoundationVersionNumber Double
 */
private val NSFoundationVersionNumber_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSFoundationVersionNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFoundationVersionNumber").orElseThrow() }
private val NSFoundationVersionNumber_VH: VarHandle by lazy { NSFoundationVersionNumber_LAYOUT.varHandle() }

var NSFoundationVersionNumber: Double
    get() = NSFoundationVersionNumber_VH.get(NSFoundationVersionNumber_SEGMENT) as Double
    set(value) = NSFoundationVersionNumber_VH.set(NSFoundationVersionNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringFromSelector typedef NSString = (Void)*(typedef SEL = ((Void)*)*)
 */
private val NSStringFromSelector_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSStringFromSelector_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSStringFromSelector").orElseThrow()
private val NSStringFromSelector_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSStringFromSelector_ADDR, NSStringFromSelector_DESC)

fun NSStringFromSelector(arg0: MemorySegment): MemorySegment {
    try {
        return NSStringFromSelector_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSSelectorFromString typedef SEL = ((Void)*)*(typedef NSString = (Void)*)
 */
private val NSSelectorFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSSelectorFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSSelectorFromString").orElseThrow()
private val NSSelectorFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSSelectorFromString_ADDR, NSSelectorFromString_DESC)

fun NSSelectorFromString(arg0: MemorySegment): MemorySegment {
    try {
        return NSSelectorFromString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSStringFromClass typedef NSString = (Void)*(typedef Class = typedef Class = (Void)*)
 */
private val NSStringFromClass_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSStringFromClass_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSStringFromClass").orElseThrow()
private val NSStringFromClass_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSStringFromClass_ADDR, NSStringFromClass_DESC)

fun NSStringFromClass(arg0: MemorySegment): MemorySegment {
    try {
        return NSStringFromClass_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSClassFromString typedef Class = typedef Class = (Void)*(typedef NSString = (Void)*)
 */
private val NSClassFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSClassFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSClassFromString").orElseThrow()
private val NSClassFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSClassFromString_ADDR, NSClassFromString_DESC)

fun NSClassFromString(arg0: MemorySegment): MemorySegment {
    try {
        return NSClassFromString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSStringFromProtocol typedef NSString = (Void)*(typedef Protocol = (Void)*)
 */
private val NSStringFromProtocol_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSStringFromProtocol_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSStringFromProtocol").orElseThrow()
private val NSStringFromProtocol_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSStringFromProtocol_ADDR, NSStringFromProtocol_DESC)

fun NSStringFromProtocol(arg0: MemorySegment): MemorySegment {
    try {
        return NSStringFromProtocol_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSProtocolFromString typedef Protocol = (Void)*(typedef NSString = (Void)*)
 */
private val NSProtocolFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSProtocolFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSProtocolFromString").orElseThrow()
private val NSProtocolFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSProtocolFromString_ADDR, NSProtocolFromString_DESC)

fun NSProtocolFromString(arg0: MemorySegment): MemorySegment {
    try {
        return NSProtocolFromString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSGetSizeAndAlignment (Char)*((Char)*,(typedef NSUInteger = UNSIGNED = Long)*,(typedef NSUInteger = UNSIGNED = Long)*)
 */
private val NSGetSizeAndAlignment_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSGetSizeAndAlignment_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSGetSizeAndAlignment").orElseThrow()
private val NSGetSizeAndAlignment_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSGetSizeAndAlignment_ADDR, NSGetSizeAndAlignment_DESC)

fun NSGetSizeAndAlignment(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return NSGetSizeAndAlignment_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSLog Void((Void)*)
 */
private val NSLog_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSLog_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSLog").orElseThrow()
private val NSLog_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSLog_ADDR, NSLog_DESC)

fun NSLog(arg0: MemorySegment): Unit {
    try {
        NSLog_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSLogv Void((Void)*,typedef __builtin_va_list = (Char)*)
 */
private val NSLogv_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSLogv_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSLogv").orElseThrow()
private val NSLogv_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSLogv_ADDR, NSLogv_DESC)

fun NSLogv(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSLogv_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDefaultMallocZone (typedef NSZone = Declared(_NSZone))*()
 */
private val NSDefaultMallocZone_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val NSDefaultMallocZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDefaultMallocZone").orElseThrow()
private val NSDefaultMallocZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDefaultMallocZone_ADDR, NSDefaultMallocZone_DESC)

fun NSDefaultMallocZone(): MemorySegment {
    try {
        return NSDefaultMallocZone_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCreateZone (typedef NSZone = Declared(_NSZone))*(typedef NSUInteger = UNSIGNED = Long,typedef NSUInteger = UNSIGNED = Long,typedef BOOL = Bool)
 */
private val NSCreateZone_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_BOOLEAN)
private val NSCreateZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCreateZone").orElseThrow()
private val NSCreateZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCreateZone_ADDR, NSCreateZone_DESC)

fun NSCreateZone(arg0: Long, arg1: Long, arg2: Boolean): MemorySegment {
    try {
        return NSCreateZone_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRecycleZone Void((typedef NSZone = Declared(_NSZone))*)
 */
private val NSRecycleZone_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSRecycleZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRecycleZone").orElseThrow()
private val NSRecycleZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRecycleZone_ADDR, NSRecycleZone_DESC)

fun NSRecycleZone(arg0: MemorySegment): Unit {
    try {
        NSRecycleZone_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSSetZoneName Void((typedef NSZone = Declared(_NSZone))*,typedef NSString = (Void)*)
 */
private val NSSetZoneName_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSSetZoneName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSSetZoneName").orElseThrow()
private val NSSetZoneName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSSetZoneName_ADDR, NSSetZoneName_DESC)

fun NSSetZoneName(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSSetZoneName_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSZoneName typedef NSString = (Void)*((typedef NSZone = Declared(_NSZone))*)
 */
private val NSZoneName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSZoneName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSZoneName").orElseThrow()
private val NSZoneName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSZoneName_ADDR, NSZoneName_DESC)

fun NSZoneName(arg0: MemorySegment): MemorySegment {
    try {
        return NSZoneName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSZoneFromPointer (typedef NSZone = Declared(_NSZone))*((Void)*)
 */
private val NSZoneFromPointer_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSZoneFromPointer_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSZoneFromPointer").orElseThrow()
private val NSZoneFromPointer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSZoneFromPointer_ADDR, NSZoneFromPointer_DESC)

fun NSZoneFromPointer(arg0: MemorySegment): MemorySegment {
    try {
        return NSZoneFromPointer_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSZoneMalloc (Void)*((typedef NSZone = Declared(_NSZone))*,typedef NSUInteger = UNSIGNED = Long)
 */
private val NSZoneMalloc_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSZoneMalloc_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSZoneMalloc").orElseThrow()
private val NSZoneMalloc_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSZoneMalloc_ADDR, NSZoneMalloc_DESC)

fun NSZoneMalloc(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return NSZoneMalloc_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSZoneCalloc (Void)*((typedef NSZone = Declared(_NSZone))*,typedef NSUInteger = UNSIGNED = Long,typedef NSUInteger = UNSIGNED = Long)
 */
private val NSZoneCalloc_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val NSZoneCalloc_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSZoneCalloc").orElseThrow()
private val NSZoneCalloc_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSZoneCalloc_ADDR, NSZoneCalloc_DESC)

fun NSZoneCalloc(arg0: MemorySegment, arg1: Long, arg2: Long): MemorySegment {
    try {
        return NSZoneCalloc_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSZoneRealloc (Void)*((typedef NSZone = Declared(_NSZone))*,(Void)*,typedef NSUInteger = UNSIGNED = Long)
 */
private val NSZoneRealloc_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSZoneRealloc_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSZoneRealloc").orElseThrow()
private val NSZoneRealloc_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSZoneRealloc_ADDR, NSZoneRealloc_DESC)

fun NSZoneRealloc(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return NSZoneRealloc_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSZoneFree Void((typedef NSZone = Declared(_NSZone))*,(Void)*)
 */
private val NSZoneFree_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSZoneFree_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSZoneFree").orElseThrow()
private val NSZoneFree_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSZoneFree_ADDR, NSZoneFree_DESC)

fun NSZoneFree(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSZoneFree_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAllocateCollectable (Void)*(typedef NSUInteger = UNSIGNED = Long,typedef NSUInteger = UNSIGNED = Long)
 */
private val NSAllocateCollectable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val NSAllocateCollectable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAllocateCollectable").orElseThrow()
private val NSAllocateCollectable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAllocateCollectable_ADDR, NSAllocateCollectable_DESC)

fun NSAllocateCollectable(arg0: Long, arg1: Long): MemorySegment {
    try {
        return NSAllocateCollectable_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSReallocateCollectable (Void)*((Void)*,typedef NSUInteger = UNSIGNED = Long,typedef NSUInteger = UNSIGNED = Long)
 */
private val NSReallocateCollectable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val NSReallocateCollectable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSReallocateCollectable").orElseThrow()
private val NSReallocateCollectable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSReallocateCollectable_ADDR, NSReallocateCollectable_DESC)

fun NSReallocateCollectable(arg0: MemorySegment, arg1: Long, arg2: Long): MemorySegment {
    try {
        return NSReallocateCollectable_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSPageSize typedef NSUInteger = UNSIGNED = Long()
 */
private val NSPageSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val NSPageSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSPageSize").orElseThrow()
private val NSPageSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSPageSize_ADDR, NSPageSize_DESC)

fun NSPageSize(): Long {
    try {
        return NSPageSize_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSLogPageSize typedef NSUInteger = UNSIGNED = Long()
 */
private val NSLogPageSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val NSLogPageSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSLogPageSize").orElseThrow()
private val NSLogPageSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSLogPageSize_ADDR, NSLogPageSize_DESC)

fun NSLogPageSize(): Long {
    try {
        return NSLogPageSize_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRoundUpToMultipleOfPageSize typedef NSUInteger = UNSIGNED = Long(typedef NSUInteger = UNSIGNED = Long)
 */
private val NSRoundUpToMultipleOfPageSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val NSRoundUpToMultipleOfPageSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRoundUpToMultipleOfPageSize").orElseThrow()
private val NSRoundUpToMultipleOfPageSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRoundUpToMultipleOfPageSize_ADDR, NSRoundUpToMultipleOfPageSize_DESC)

fun NSRoundUpToMultipleOfPageSize(arg0: Long): Long {
    try {
        return NSRoundUpToMultipleOfPageSize_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRoundDownToMultipleOfPageSize typedef NSUInteger = UNSIGNED = Long(typedef NSUInteger = UNSIGNED = Long)
 */
private val NSRoundDownToMultipleOfPageSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val NSRoundDownToMultipleOfPageSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRoundDownToMultipleOfPageSize").orElseThrow()
private val NSRoundDownToMultipleOfPageSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRoundDownToMultipleOfPageSize_ADDR, NSRoundDownToMultipleOfPageSize_DESC)

fun NSRoundDownToMultipleOfPageSize(arg0: Long): Long {
    try {
        return NSRoundDownToMultipleOfPageSize_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAllocateMemoryPages (Void)*(typedef NSUInteger = UNSIGNED = Long)
 */
private val NSAllocateMemoryPages_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSAllocateMemoryPages_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAllocateMemoryPages").orElseThrow()
private val NSAllocateMemoryPages_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAllocateMemoryPages_ADDR, NSAllocateMemoryPages_DESC)

fun NSAllocateMemoryPages(arg0: Long): MemorySegment {
    try {
        return NSAllocateMemoryPages_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDeallocateMemoryPages Void((Void)*,typedef NSUInteger = UNSIGNED = Long)
 */
private val NSDeallocateMemoryPages_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSDeallocateMemoryPages_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDeallocateMemoryPages").orElseThrow()
private val NSDeallocateMemoryPages_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDeallocateMemoryPages_ADDR, NSDeallocateMemoryPages_DESC)

fun NSDeallocateMemoryPages(arg0: MemorySegment, arg1: Long): Unit {
    try {
        NSDeallocateMemoryPages_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCopyMemoryPages Void((Void)*,(Void)*,typedef NSUInteger = UNSIGNED = Long)
 */
private val NSCopyMemoryPages_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSCopyMemoryPages_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCopyMemoryPages").orElseThrow()
private val NSCopyMemoryPages_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCopyMemoryPages_ADDR, NSCopyMemoryPages_DESC)

fun NSCopyMemoryPages(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        NSCopyMemoryPages_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRealMemoryAvailable typedef NSUInteger = UNSIGNED = Long()
 */
private val NSRealMemoryAvailable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val NSRealMemoryAvailable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRealMemoryAvailable").orElseThrow()
private val NSRealMemoryAvailable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRealMemoryAvailable_ADDR, NSRealMemoryAvailable_DESC)

fun NSRealMemoryAvailable(): Long {
    try {
        return NSRealMemoryAvailable_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAllocateObject typedef id = (Void)*(typedef Class = typedef Class = (Void)*,typedef NSUInteger = UNSIGNED = Long,(typedef NSZone = Declared(_NSZone))*)
 */
private val NSAllocateObject_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSAllocateObject_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAllocateObject").orElseThrow()
private val NSAllocateObject_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAllocateObject_ADDR, NSAllocateObject_DESC)

fun NSAllocateObject(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return NSAllocateObject_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDeallocateObject Void(typedef id = (Void)*)
 */
private val NSDeallocateObject_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSDeallocateObject_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDeallocateObject").orElseThrow()
private val NSDeallocateObject_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDeallocateObject_ADDR, NSDeallocateObject_DESC)

fun NSDeallocateObject(arg0: MemorySegment): Unit {
    try {
        NSDeallocateObject_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCopyObject typedef id = (Void)*(typedef id = (Void)*,typedef NSUInteger = UNSIGNED = Long,(typedef NSZone = Declared(_NSZone))*)
 */
private val NSCopyObject_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSCopyObject_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCopyObject").orElseThrow()
private val NSCopyObject_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCopyObject_ADDR, NSCopyObject_DESC)

fun NSCopyObject(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return NSCopyObject_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSShouldRetainWithZone typedef BOOL = Bool(typedef id = (Void)*,(typedef NSZone = Declared(_NSZone))*)
 */
private val NSShouldRetainWithZone_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSShouldRetainWithZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSShouldRetainWithZone").orElseThrow()
private val NSShouldRetainWithZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSShouldRetainWithZone_ADDR, NSShouldRetainWithZone_DESC)

fun NSShouldRetainWithZone(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSShouldRetainWithZone_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSIncrementExtraRefCount Void(typedef id = (Void)*)
 */
private val NSIncrementExtraRefCount_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSIncrementExtraRefCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSIncrementExtraRefCount").orElseThrow()
private val NSIncrementExtraRefCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSIncrementExtraRefCount_ADDR, NSIncrementExtraRefCount_DESC)

fun NSIncrementExtraRefCount(arg0: MemorySegment): Unit {
    try {
        NSIncrementExtraRefCount_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDecrementExtraRefCountWasZero typedef BOOL = Bool(typedef id = (Void)*)
 */
private val NSDecrementExtraRefCountWasZero_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val NSDecrementExtraRefCountWasZero_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDecrementExtraRefCountWasZero").orElseThrow()
private val NSDecrementExtraRefCountWasZero_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDecrementExtraRefCountWasZero_ADDR, NSDecrementExtraRefCountWasZero_DESC)

fun NSDecrementExtraRefCountWasZero(arg0: MemorySegment): Boolean {
    try {
        return NSDecrementExtraRefCountWasZero_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSExtraRefCount typedef NSUInteger = UNSIGNED = Long(typedef id = (Void)*)
 */
private val NSExtraRefCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSExtraRefCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSExtraRefCount").orElseThrow()
private val NSExtraRefCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSExtraRefCount_ADDR, NSExtraRefCount_DESC)

fun NSExtraRefCount(arg0: MemorySegment): Long {
    try {
        return NSExtraRefCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSUnionRange typedef NSRange = Declared(_NSRange)(typedef NSRange = Declared(_NSRange),typedef NSRange = Declared(_NSRange))
 */
private val NSUnionRange_DESC: FunctionDescriptor = FunctionDescriptor.of(_NSRange.layout, _NSRange.layout, _NSRange.layout)
private val NSUnionRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSUnionRange").orElseThrow()
private val NSUnionRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSUnionRange_ADDR, NSUnionRange_DESC)

fun NSUnionRange(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSUnionRange_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSIntersectionRange typedef NSRange = Declared(_NSRange)(typedef NSRange = Declared(_NSRange),typedef NSRange = Declared(_NSRange))
 */
private val NSIntersectionRange_DESC: FunctionDescriptor = FunctionDescriptor.of(_NSRange.layout, _NSRange.layout, _NSRange.layout)
private val NSIntersectionRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSIntersectionRange").orElseThrow()
private val NSIntersectionRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSIntersectionRange_ADDR, NSIntersectionRange_DESC)

fun NSIntersectionRange(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSIntersectionRange_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSStringFromRange typedef NSString = (Void)*(typedef NSRange = Declared(_NSRange))
 */
private val NSStringFromRange_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, _NSRange.layout)
private val NSStringFromRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSStringFromRange").orElseThrow()
private val NSStringFromRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSStringFromRange_ADDR, NSStringFromRange_DESC)

fun NSStringFromRange(arg0: MemorySegment): MemorySegment {
    try {
        return NSStringFromRange_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRangeFromString typedef NSRange = Declared(_NSRange)(typedef NSString = (Void)*)
 */
private val NSRangeFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(_NSRange.layout, ValueLayout.ADDRESS)
private val NSRangeFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRangeFromString").orElseThrow()
private val NSRangeFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRangeFromString_ADDR, NSRangeFromString_DESC)

fun NSRangeFromString(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return NSRangeFromString_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSItemProviderPreferredImageSizeKey (Void)*
 */
private val NSItemProviderPreferredImageSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSItemProviderPreferredImageSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSItemProviderPreferredImageSizeKey").orElseThrow() }
private val NSItemProviderPreferredImageSizeKey_VH: VarHandle by lazy { NSItemProviderPreferredImageSizeKey_LAYOUT.varHandle() }

var NSItemProviderPreferredImageSizeKey: MemorySegment
    get() = NSItemProviderPreferredImageSizeKey_VH.get(NSItemProviderPreferredImageSizeKey_SEGMENT) as MemorySegment
    set(value) = NSItemProviderPreferredImageSizeKey_VH.set(NSItemProviderPreferredImageSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionJavaScriptPreprocessingResultsKey (Void)*
 */
private val NSExtensionJavaScriptPreprocessingResultsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionJavaScriptPreprocessingResultsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionJavaScriptPreprocessingResultsKey").orElseThrow() }
private val NSExtensionJavaScriptPreprocessingResultsKey_VH: VarHandle by lazy { NSExtensionJavaScriptPreprocessingResultsKey_LAYOUT.varHandle() }

var NSExtensionJavaScriptPreprocessingResultsKey: MemorySegment
    get() = NSExtensionJavaScriptPreprocessingResultsKey_VH.get(NSExtensionJavaScriptPreprocessingResultsKey_SEGMENT) as MemorySegment
    set(value) = NSExtensionJavaScriptPreprocessingResultsKey_VH.set(NSExtensionJavaScriptPreprocessingResultsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSExtensionJavaScriptFinalizeArgumentKey (Void)*
 */
private val NSExtensionJavaScriptFinalizeArgumentKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExtensionJavaScriptFinalizeArgumentKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExtensionJavaScriptFinalizeArgumentKey").orElseThrow() }
private val NSExtensionJavaScriptFinalizeArgumentKey_VH: VarHandle by lazy { NSExtensionJavaScriptFinalizeArgumentKey_LAYOUT.varHandle() }

var NSExtensionJavaScriptFinalizeArgumentKey: MemorySegment
    get() = NSExtensionJavaScriptFinalizeArgumentKey_VH.get(NSExtensionJavaScriptFinalizeArgumentKey_SEGMENT) as MemorySegment
    set(value) = NSExtensionJavaScriptFinalizeArgumentKey_VH.set(NSExtensionJavaScriptFinalizeArgumentKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSItemProviderErrorDomain (Void)*
 */
private val NSItemProviderErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSItemProviderErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSItemProviderErrorDomain").orElseThrow() }
private val NSItemProviderErrorDomain_VH: VarHandle by lazy { NSItemProviderErrorDomain_LAYOUT.varHandle() }

var NSItemProviderErrorDomain: MemorySegment
    get() = NSItemProviderErrorDomain_VH.get(NSItemProviderErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSItemProviderErrorDomain_VH.set(NSItemProviderErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformLatinToKatakana typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformLatinToKatakana_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformLatinToKatakana_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformLatinToKatakana").orElseThrow() }
private val NSStringTransformLatinToKatakana_VH: VarHandle by lazy { NSStringTransformLatinToKatakana_LAYOUT.varHandle() }

var NSStringTransformLatinToKatakana: MemorySegment
    get() = NSStringTransformLatinToKatakana_VH.get(NSStringTransformLatinToKatakana_SEGMENT) as MemorySegment
    set(value) = NSStringTransformLatinToKatakana_VH.set(NSStringTransformLatinToKatakana_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformLatinToHiragana typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformLatinToHiragana_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformLatinToHiragana_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformLatinToHiragana").orElseThrow() }
private val NSStringTransformLatinToHiragana_VH: VarHandle by lazy { NSStringTransformLatinToHiragana_LAYOUT.varHandle() }

var NSStringTransformLatinToHiragana: MemorySegment
    get() = NSStringTransformLatinToHiragana_VH.get(NSStringTransformLatinToHiragana_SEGMENT) as MemorySegment
    set(value) = NSStringTransformLatinToHiragana_VH.set(NSStringTransformLatinToHiragana_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformLatinToHangul typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformLatinToHangul_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformLatinToHangul_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformLatinToHangul").orElseThrow() }
private val NSStringTransformLatinToHangul_VH: VarHandle by lazy { NSStringTransformLatinToHangul_LAYOUT.varHandle() }

var NSStringTransformLatinToHangul: MemorySegment
    get() = NSStringTransformLatinToHangul_VH.get(NSStringTransformLatinToHangul_SEGMENT) as MemorySegment
    set(value) = NSStringTransformLatinToHangul_VH.set(NSStringTransformLatinToHangul_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformLatinToArabic typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformLatinToArabic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformLatinToArabic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformLatinToArabic").orElseThrow() }
private val NSStringTransformLatinToArabic_VH: VarHandle by lazy { NSStringTransformLatinToArabic_LAYOUT.varHandle() }

var NSStringTransformLatinToArabic: MemorySegment
    get() = NSStringTransformLatinToArabic_VH.get(NSStringTransformLatinToArabic_SEGMENT) as MemorySegment
    set(value) = NSStringTransformLatinToArabic_VH.set(NSStringTransformLatinToArabic_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformLatinToHebrew typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformLatinToHebrew_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformLatinToHebrew_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformLatinToHebrew").orElseThrow() }
private val NSStringTransformLatinToHebrew_VH: VarHandle by lazy { NSStringTransformLatinToHebrew_LAYOUT.varHandle() }

var NSStringTransformLatinToHebrew: MemorySegment
    get() = NSStringTransformLatinToHebrew_VH.get(NSStringTransformLatinToHebrew_SEGMENT) as MemorySegment
    set(value) = NSStringTransformLatinToHebrew_VH.set(NSStringTransformLatinToHebrew_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformLatinToThai typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformLatinToThai_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformLatinToThai_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformLatinToThai").orElseThrow() }
private val NSStringTransformLatinToThai_VH: VarHandle by lazy { NSStringTransformLatinToThai_LAYOUT.varHandle() }

var NSStringTransformLatinToThai: MemorySegment
    get() = NSStringTransformLatinToThai_VH.get(NSStringTransformLatinToThai_SEGMENT) as MemorySegment
    set(value) = NSStringTransformLatinToThai_VH.set(NSStringTransformLatinToThai_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformLatinToCyrillic typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformLatinToCyrillic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformLatinToCyrillic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformLatinToCyrillic").orElseThrow() }
private val NSStringTransformLatinToCyrillic_VH: VarHandle by lazy { NSStringTransformLatinToCyrillic_LAYOUT.varHandle() }

var NSStringTransformLatinToCyrillic: MemorySegment
    get() = NSStringTransformLatinToCyrillic_VH.get(NSStringTransformLatinToCyrillic_SEGMENT) as MemorySegment
    set(value) = NSStringTransformLatinToCyrillic_VH.set(NSStringTransformLatinToCyrillic_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformLatinToGreek typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformLatinToGreek_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformLatinToGreek_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformLatinToGreek").orElseThrow() }
private val NSStringTransformLatinToGreek_VH: VarHandle by lazy { NSStringTransformLatinToGreek_LAYOUT.varHandle() }

var NSStringTransformLatinToGreek: MemorySegment
    get() = NSStringTransformLatinToGreek_VH.get(NSStringTransformLatinToGreek_SEGMENT) as MemorySegment
    set(value) = NSStringTransformLatinToGreek_VH.set(NSStringTransformLatinToGreek_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformToLatin typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformToLatin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformToLatin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformToLatin").orElseThrow() }
private val NSStringTransformToLatin_VH: VarHandle by lazy { NSStringTransformToLatin_LAYOUT.varHandle() }

var NSStringTransformToLatin: MemorySegment
    get() = NSStringTransformToLatin_VH.get(NSStringTransformToLatin_SEGMENT) as MemorySegment
    set(value) = NSStringTransformToLatin_VH.set(NSStringTransformToLatin_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformMandarinToLatin typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformMandarinToLatin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformMandarinToLatin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformMandarinToLatin").orElseThrow() }
private val NSStringTransformMandarinToLatin_VH: VarHandle by lazy { NSStringTransformMandarinToLatin_LAYOUT.varHandle() }

var NSStringTransformMandarinToLatin: MemorySegment
    get() = NSStringTransformMandarinToLatin_VH.get(NSStringTransformMandarinToLatin_SEGMENT) as MemorySegment
    set(value) = NSStringTransformMandarinToLatin_VH.set(NSStringTransformMandarinToLatin_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformHiraganaToKatakana typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformHiraganaToKatakana_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformHiraganaToKatakana_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformHiraganaToKatakana").orElseThrow() }
private val NSStringTransformHiraganaToKatakana_VH: VarHandle by lazy { NSStringTransformHiraganaToKatakana_LAYOUT.varHandle() }

var NSStringTransformHiraganaToKatakana: MemorySegment
    get() = NSStringTransformHiraganaToKatakana_VH.get(NSStringTransformHiraganaToKatakana_SEGMENT) as MemorySegment
    set(value) = NSStringTransformHiraganaToKatakana_VH.set(NSStringTransformHiraganaToKatakana_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformFullwidthToHalfwidth typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformFullwidthToHalfwidth_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformFullwidthToHalfwidth_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformFullwidthToHalfwidth").orElseThrow() }
private val NSStringTransformFullwidthToHalfwidth_VH: VarHandle by lazy { NSStringTransformFullwidthToHalfwidth_LAYOUT.varHandle() }

var NSStringTransformFullwidthToHalfwidth: MemorySegment
    get() = NSStringTransformFullwidthToHalfwidth_VH.get(NSStringTransformFullwidthToHalfwidth_SEGMENT) as MemorySegment
    set(value) = NSStringTransformFullwidthToHalfwidth_VH.set(NSStringTransformFullwidthToHalfwidth_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformToXMLHex typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformToXMLHex_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformToXMLHex_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformToXMLHex").orElseThrow() }
private val NSStringTransformToXMLHex_VH: VarHandle by lazy { NSStringTransformToXMLHex_LAYOUT.varHandle() }

var NSStringTransformToXMLHex: MemorySegment
    get() = NSStringTransformToXMLHex_VH.get(NSStringTransformToXMLHex_SEGMENT) as MemorySegment
    set(value) = NSStringTransformToXMLHex_VH.set(NSStringTransformToXMLHex_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformToUnicodeName typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformToUnicodeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformToUnicodeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformToUnicodeName").orElseThrow() }
private val NSStringTransformToUnicodeName_VH: VarHandle by lazy { NSStringTransformToUnicodeName_LAYOUT.varHandle() }

var NSStringTransformToUnicodeName: MemorySegment
    get() = NSStringTransformToUnicodeName_VH.get(NSStringTransformToUnicodeName_SEGMENT) as MemorySegment
    set(value) = NSStringTransformToUnicodeName_VH.set(NSStringTransformToUnicodeName_SEGMENT, value)

