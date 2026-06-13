package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : CFStringGetPascalStringPtr typedef ConstStringPtr = (UNSIGNED = Char)*(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringGetPascalStringPtr_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFStringGetPascalStringPtr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetPascalStringPtr").orElseThrow()
private val CFStringGetPascalStringPtr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetPascalStringPtr_ADDR, CFStringGetPascalStringPtr_DESC)

fun CFStringGetPascalStringPtr(arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return CFStringGetPascalStringPtr_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetCStringPtr (Char)*(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringGetCStringPtr_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFStringGetCStringPtr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetCStringPtr").orElseThrow()
private val CFStringGetCStringPtr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetCStringPtr_ADDR, CFStringGetCStringPtr_DESC)

fun CFStringGetCStringPtr(arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return CFStringGetCStringPtr_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetCharactersPtr (typedef UniChar = UNSIGNED = Short)*(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringGetCharactersPtr_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringGetCharactersPtr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetCharactersPtr").orElseThrow()
private val CFStringGetCharactersPtr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetCharactersPtr_ADDR, CFStringGetCharactersPtr_DESC)

fun CFStringGetCharactersPtr(arg0: MemorySegment): MemorySegment {
    try {
        return CFStringGetCharactersPtr_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetBytes typedef CFIndex = Long(typedef CFStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange),typedef CFStringEncoding = UNSIGNED = Int,typedef UInt8 = UNSIGNED = Char,typedef Boolean = UNSIGNED = Char,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long,(typedef CFIndex = Long)*)
 */
private val CFStringGetBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CFRange.layout, ValueLayout.JAVA_INT, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFStringGetBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetBytes").orElseThrow()
private val CFStringGetBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetBytes_ADDR, CFStringGetBytes_DESC)

fun CFStringGetBytes(arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: Byte, arg4: Byte, arg5: MemorySegment, arg6: Long, arg7: MemorySegment): Long {
    try {
        return CFStringGetBytes_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateFromExternalRepresentation typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringCreateFromExternalRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFStringCreateFromExternalRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateFromExternalRepresentation").orElseThrow()
private val CFStringCreateFromExternalRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateFromExternalRepresentation_ADDR, CFStringCreateFromExternalRepresentation_DESC)

fun CFStringCreateFromExternalRepresentation(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): MemorySegment {
    try {
        return CFStringCreateFromExternalRepresentation_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateExternalRepresentation typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringEncoding = UNSIGNED = Int,typedef UInt8 = UNSIGNED = Char)
 */
private val CFStringCreateExternalRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_BYTE)
private val CFStringCreateExternalRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateExternalRepresentation").orElseThrow()
private val CFStringCreateExternalRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateExternalRepresentation_ADDR, CFStringCreateExternalRepresentation_DESC)

fun CFStringCreateExternalRepresentation(arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: Byte): MemorySegment {
    try {
        return CFStringCreateExternalRepresentation_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetSmallestEncoding typedef CFStringEncoding = UNSIGNED = Int(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringGetSmallestEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFStringGetSmallestEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetSmallestEncoding").orElseThrow()
private val CFStringGetSmallestEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetSmallestEncoding_ADDR, CFStringGetSmallestEncoding_DESC)

fun CFStringGetSmallestEncoding(arg0: MemorySegment): Int {
    try {
        return CFStringGetSmallestEncoding_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetFastestEncoding typedef CFStringEncoding = UNSIGNED = Int(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringGetFastestEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFStringGetFastestEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetFastestEncoding").orElseThrow()
private val CFStringGetFastestEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetFastestEncoding_ADDR, CFStringGetFastestEncoding_DESC)

fun CFStringGetFastestEncoding(arg0: MemorySegment): Int {
    try {
        return CFStringGetFastestEncoding_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetSystemEncoding typedef CFStringEncoding = UNSIGNED = Int()
 */
private val CFStringGetSystemEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT)
private val CFStringGetSystemEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetSystemEncoding").orElseThrow()
private val CFStringGetSystemEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetSystemEncoding_ADDR, CFStringGetSystemEncoding_DESC)

fun CFStringGetSystemEncoding(): Int {
    try {
        return CFStringGetSystemEncoding_HANDLE.invokeExact() as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetMaximumSizeForEncoding typedef CFIndex = Long(typedef CFIndex = Long,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringGetMaximumSizeForEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT)
private val CFStringGetMaximumSizeForEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetMaximumSizeForEncoding").orElseThrow()
private val CFStringGetMaximumSizeForEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetMaximumSizeForEncoding_ADDR, CFStringGetMaximumSizeForEncoding_DESC)

fun CFStringGetMaximumSizeForEncoding(arg0: Long, arg1: Int): Long {
    try {
        return CFStringGetMaximumSizeForEncoding_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetFileSystemRepresentation typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*,(Char)*,typedef CFIndex = Long)
 */
private val CFStringGetFileSystemRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFStringGetFileSystemRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetFileSystemRepresentation").orElseThrow()
private val CFStringGetFileSystemRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetFileSystemRepresentation_ADDR, CFStringGetFileSystemRepresentation_DESC)

fun CFStringGetFileSystemRepresentation(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Byte {
    try {
        return CFStringGetFileSystemRepresentation_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetMaximumSizeOfFileSystemRepresentation typedef CFIndex = Long(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringGetMaximumSizeOfFileSystemRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFStringGetMaximumSizeOfFileSystemRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetMaximumSizeOfFileSystemRepresentation").orElseThrow()
private val CFStringGetMaximumSizeOfFileSystemRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetMaximumSizeOfFileSystemRepresentation_ADDR, CFStringGetMaximumSizeOfFileSystemRepresentation_DESC)

fun CFStringGetMaximumSizeOfFileSystemRepresentation(arg0: MemorySegment): Long {
    try {
        return CFStringGetMaximumSizeOfFileSystemRepresentation_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateWithFileSystemRepresentation typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(Char)*)
 */
private val CFStringCreateWithFileSystemRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringCreateWithFileSystemRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateWithFileSystemRepresentation").orElseThrow()
private val CFStringCreateWithFileSystemRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateWithFileSystemRepresentation_ADDR, CFStringCreateWithFileSystemRepresentation_DESC)

fun CFStringCreateWithFileSystemRepresentation(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFStringCreateWithFileSystemRepresentation_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringHasPrefix typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringHasPrefix_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringHasPrefix_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringHasPrefix").orElseThrow()
private val CFStringHasPrefix_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringHasPrefix_ADDR, CFStringHasPrefix_DESC)

fun CFStringHasPrefix(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFStringHasPrefix_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringHasSuffix typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringHasSuffix_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringHasSuffix_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringHasSuffix").orElseThrow()
private val CFStringHasSuffix_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringHasSuffix_ADDR, CFStringHasSuffix_DESC)

fun CFStringHasSuffix(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFStringHasSuffix_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetRangeOfComposedCharactersAtIndex typedef CFRange = Declared(CFRange)(typedef CFStringRef = (Declared(__CFString))*,typedef CFIndex = Long)
 */
private val CFStringGetRangeOfComposedCharactersAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.of(CFRange.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFStringGetRangeOfComposedCharactersAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetRangeOfComposedCharactersAtIndex").orElseThrow()
private val CFStringGetRangeOfComposedCharactersAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetRangeOfComposedCharactersAtIndex_ADDR, CFStringGetRangeOfComposedCharactersAtIndex_DESC)

fun CFStringGetRangeOfComposedCharactersAtIndex(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return CFStringGetRangeOfComposedCharactersAtIndex_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetLineBounds Void(typedef CFStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange),(typedef CFIndex = Long)*,(typedef CFIndex = Long)*,(typedef CFIndex = Long)*)
 */
private val CFStringGetLineBounds_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringGetLineBounds_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetLineBounds").orElseThrow()
private val CFStringGetLineBounds_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetLineBounds_ADDR, CFStringGetLineBounds_DESC)

fun CFStringGetLineBounds(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): Unit {
    try {
        CFStringGetLineBounds_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetParagraphBounds Void(typedef CFStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange),(typedef CFIndex = Long)*,(typedef CFIndex = Long)*,(typedef CFIndex = Long)*)
 */
private val CFStringGetParagraphBounds_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringGetParagraphBounds_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetParagraphBounds").orElseThrow()
private val CFStringGetParagraphBounds_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetParagraphBounds_ADDR, CFStringGetParagraphBounds_DESC)

fun CFStringGetParagraphBounds(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): Unit {
    try {
        CFStringGetParagraphBounds_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetHyphenationLocationBeforeIndex typedef CFIndex = Long(typedef CFStringRef = (Declared(__CFString))*,typedef CFIndex = Long,typedef CFRange = Declared(CFRange),typedef CFOptionFlags = UNSIGNED = Long,typedef CFLocaleRef = (Declared(__CFLocale))*,(typedef UTF32Char = UNSIGNED = Int)*)
 */
private val CFStringGetHyphenationLocationBeforeIndex_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, CFRange.layout, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringGetHyphenationLocationBeforeIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetHyphenationLocationBeforeIndex").orElseThrow()
private val CFStringGetHyphenationLocationBeforeIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetHyphenationLocationBeforeIndex_ADDR, CFStringGetHyphenationLocationBeforeIndex_DESC)

fun CFStringGetHyphenationLocationBeforeIndex(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: Long, arg4: MemorySegment, arg5: MemorySegment): Long {
    try {
        return CFStringGetHyphenationLocationBeforeIndex_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringIsHyphenationAvailableForLocale typedef Boolean = UNSIGNED = Char(typedef CFLocaleRef = (Declared(__CFLocale))*)
 */
private val CFStringIsHyphenationAvailableForLocale_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFStringIsHyphenationAvailableForLocale_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringIsHyphenationAvailableForLocale").orElseThrow()
private val CFStringIsHyphenationAvailableForLocale_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringIsHyphenationAvailableForLocale_ADDR, CFStringIsHyphenationAvailableForLocale_DESC)

fun CFStringIsHyphenationAvailableForLocale(arg0: MemorySegment): Byte {
    try {
        return CFStringIsHyphenationAvailableForLocale_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateByCombiningStrings typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFArrayRef = (Declared(__CFArray))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringCreateByCombiningStrings_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringCreateByCombiningStrings_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateByCombiningStrings").orElseThrow()
private val CFStringCreateByCombiningStrings_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateByCombiningStrings_ADDR, CFStringCreateByCombiningStrings_DESC)

fun CFStringCreateByCombiningStrings(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFStringCreateByCombiningStrings_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCreateArrayBySeparatingStrings typedef CFArrayRef = (Declared(__CFArray))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringCreateArrayBySeparatingStrings_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringCreateArrayBySeparatingStrings_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCreateArrayBySeparatingStrings").orElseThrow()
private val CFStringCreateArrayBySeparatingStrings_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCreateArrayBySeparatingStrings_ADDR, CFStringCreateArrayBySeparatingStrings_DESC)

fun CFStringCreateArrayBySeparatingStrings(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFStringCreateArrayBySeparatingStrings_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetIntValue typedef SInt32 = Int(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringGetIntValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFStringGetIntValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetIntValue").orElseThrow()
private val CFStringGetIntValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetIntValue_ADDR, CFStringGetIntValue_DESC)

fun CFStringGetIntValue(arg0: MemorySegment): Int {
    try {
        return CFStringGetIntValue_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetDoubleValue Double(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringGetDoubleValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFStringGetDoubleValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetDoubleValue").orElseThrow()
private val CFStringGetDoubleValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetDoubleValue_ADDR, CFStringGetDoubleValue_DESC)

fun CFStringGetDoubleValue(arg0: MemorySegment): Double {
    try {
        return CFStringGetDoubleValue_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringAppend Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringAppend_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringAppend_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringAppend").orElseThrow()
private val CFStringAppend_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringAppend_ADDR, CFStringAppend_DESC)

fun CFStringAppend(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFStringAppend_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringAppendCharacters Void(typedef CFMutableStringRef = (Declared(__CFString))*,(typedef UniChar = UNSIGNED = Short)*,typedef CFIndex = Long)
 */
private val CFStringAppendCharacters_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFStringAppendCharacters_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringAppendCharacters").orElseThrow()
private val CFStringAppendCharacters_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringAppendCharacters_ADDR, CFStringAppendCharacters_DESC)

fun CFStringAppendCharacters(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        CFStringAppendCharacters_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringAppendPascalString Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef ConstStr255Param = (UNSIGNED = Char)*,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringAppendPascalString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFStringAppendPascalString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringAppendPascalString").orElseThrow()
private val CFStringAppendPascalString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringAppendPascalString_ADDR, CFStringAppendPascalString_DESC)

fun CFStringAppendPascalString(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Unit {
    try {
        CFStringAppendPascalString_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringAppendCString Void(typedef CFMutableStringRef = (Declared(__CFString))*,(Char)*,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringAppendCString_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFStringAppendCString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringAppendCString").orElseThrow()
private val CFStringAppendCString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringAppendCString_ADDR, CFStringAppendCString_DESC)

fun CFStringAppendCString(arg0: MemorySegment, arg1: MemorySegment, arg2: Int): Unit {
    try {
        CFStringAppendCString_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringAppendFormat Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringAppendFormat_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringAppendFormat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringAppendFormat").orElseThrow()
private val CFStringAppendFormat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringAppendFormat_ADDR, CFStringAppendFormat_DESC)

fun CFStringAppendFormat(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFStringAppendFormat_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringAppendFormatAndArguments Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CFStringRef = (Declared(__CFString))*,typedef va_list = (Char)*)
 */
private val CFStringAppendFormatAndArguments_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringAppendFormatAndArguments_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringAppendFormatAndArguments").orElseThrow()
private val CFStringAppendFormatAndArguments_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringAppendFormatAndArguments_ADDR, CFStringAppendFormatAndArguments_DESC)

fun CFStringAppendFormatAndArguments(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        CFStringAppendFormatAndArguments_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringInsert Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFIndex = Long,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringInsert_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFStringInsert_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringInsert").orElseThrow()
private val CFStringInsert_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringInsert_ADDR, CFStringInsert_DESC)

fun CFStringInsert(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Unit {
    try {
        CFStringInsert_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringDelete Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange))
 */
private val CFStringDelete_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout)
private val CFStringDelete_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringDelete").orElseThrow()
private val CFStringDelete_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringDelete_ADDR, CFStringDelete_DESC)

fun CFStringDelete(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFStringDelete_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringReplace Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFRange = Declared(CFRange),typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringReplace_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CFRange.layout, ValueLayout.ADDRESS)
private val CFStringReplace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringReplace").orElseThrow()
private val CFStringReplace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringReplace_ADDR, CFStringReplace_DESC)

fun CFStringReplace(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFStringReplace_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringReplaceAll Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringReplaceAll_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringReplaceAll_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringReplaceAll").orElseThrow()
private val CFStringReplaceAll_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringReplaceAll_ADDR, CFStringReplaceAll_DESC)

fun CFStringReplaceAll(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFStringReplaceAll_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringSetExternalCharactersNoCopy Void(typedef CFMutableStringRef = (Declared(__CFString))*,(typedef UniChar = UNSIGNED = Short)*,typedef CFIndex = Long,typedef CFIndex = Long)
 */
private val CFStringSetExternalCharactersNoCopy_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFStringSetExternalCharactersNoCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringSetExternalCharactersNoCopy").orElseThrow()
private val CFStringSetExternalCharactersNoCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringSetExternalCharactersNoCopy_ADDR, CFStringSetExternalCharactersNoCopy_DESC)

fun CFStringSetExternalCharactersNoCopy(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long): Unit {
    try {
        CFStringSetExternalCharactersNoCopy_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringPad Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFIndex = Long,typedef CFIndex = Long)
 */
private val CFStringPad_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG)
private val CFStringPad_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringPad").orElseThrow()
private val CFStringPad_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringPad_ADDR, CFStringPad_DESC)

fun CFStringPad(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long): Unit {
    try {
        CFStringPad_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTrim Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringTrim_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringTrim_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTrim").orElseThrow()
private val CFStringTrim_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTrim_ADDR, CFStringTrim_DESC)

fun CFStringTrim(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFStringTrim_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTrimWhitespace Void(typedef CFMutableStringRef = (Declared(__CFString))*)
 */
private val CFStringTrimWhitespace_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFStringTrimWhitespace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTrimWhitespace").orElseThrow()
private val CFStringTrimWhitespace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTrimWhitespace_ADDR, CFStringTrimWhitespace_DESC)

fun CFStringTrimWhitespace(arg0: MemorySegment): Unit {
    try {
        CFStringTrimWhitespace_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringLowercase Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFLocaleRef = (Declared(__CFLocale))*)
 */
private val CFStringLowercase_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringLowercase_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringLowercase").orElseThrow()
private val CFStringLowercase_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringLowercase_ADDR, CFStringLowercase_DESC)

fun CFStringLowercase(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFStringLowercase_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringUppercase Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFLocaleRef = (Declared(__CFLocale))*)
 */
private val CFStringUppercase_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringUppercase_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringUppercase").orElseThrow()
private val CFStringUppercase_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringUppercase_ADDR, CFStringUppercase_DESC)

fun CFStringUppercase(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFStringUppercase_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringCapitalize Void(typedef CFMutableStringRef = (Declared(__CFString))*,typedef CFLocaleRef = (Declared(__CFLocale))*)
 */
private val CFStringCapitalize_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStringCapitalize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringCapitalize").orElseThrow()
private val CFStringCapitalize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringCapitalize_ADDR, CFStringCapitalize_DESC)

fun CFStringCapitalize(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFStringCapitalize_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringTransform typedef Boolean = UNSIGNED = Char(typedef CFMutableStringRef = (Declared(__CFString))*,(typedef CFRange = Declared(CFRange))*,typedef CFStringRef = (Declared(__CFString))*,typedef Boolean = UNSIGNED = Char)
 */
private val CFStringTransform_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE)
private val CFStringTransform_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringTransform").orElseThrow()
private val CFStringTransform_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringTransform_ADDR, CFStringTransform_DESC)

fun CFStringTransform(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Byte): Byte {
    try {
        return CFStringTransform_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFStringTransformStripCombiningMarks typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformStripCombiningMarks_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformStripCombiningMarks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformStripCombiningMarks").orElseThrow() }
private val kCFStringTransformStripCombiningMarks_VH: VarHandle by lazy { kCFStringTransformStripCombiningMarks_LAYOUT.varHandle() }

var kCFStringTransformStripCombiningMarks: MemorySegment
    get() = kCFStringTransformStripCombiningMarks_VH.get(kCFStringTransformStripCombiningMarks_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformStripCombiningMarks_VH.set(kCFStringTransformStripCombiningMarks_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformToLatin typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformToLatin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformToLatin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformToLatin").orElseThrow() }
private val kCFStringTransformToLatin_VH: VarHandle by lazy { kCFStringTransformToLatin_LAYOUT.varHandle() }

var kCFStringTransformToLatin: MemorySegment
    get() = kCFStringTransformToLatin_VH.get(kCFStringTransformToLatin_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformToLatin_VH.set(kCFStringTransformToLatin_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformFullwidthHalfwidth typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformFullwidthHalfwidth_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformFullwidthHalfwidth_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformFullwidthHalfwidth").orElseThrow() }
private val kCFStringTransformFullwidthHalfwidth_VH: VarHandle by lazy { kCFStringTransformFullwidthHalfwidth_LAYOUT.varHandle() }

var kCFStringTransformFullwidthHalfwidth: MemorySegment
    get() = kCFStringTransformFullwidthHalfwidth_VH.get(kCFStringTransformFullwidthHalfwidth_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformFullwidthHalfwidth_VH.set(kCFStringTransformFullwidthHalfwidth_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformLatinKatakana typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformLatinKatakana_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformLatinKatakana_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformLatinKatakana").orElseThrow() }
private val kCFStringTransformLatinKatakana_VH: VarHandle by lazy { kCFStringTransformLatinKatakana_LAYOUT.varHandle() }

var kCFStringTransformLatinKatakana: MemorySegment
    get() = kCFStringTransformLatinKatakana_VH.get(kCFStringTransformLatinKatakana_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformLatinKatakana_VH.set(kCFStringTransformLatinKatakana_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformLatinHiragana typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformLatinHiragana_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformLatinHiragana_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformLatinHiragana").orElseThrow() }
private val kCFStringTransformLatinHiragana_VH: VarHandle by lazy { kCFStringTransformLatinHiragana_LAYOUT.varHandle() }

var kCFStringTransformLatinHiragana: MemorySegment
    get() = kCFStringTransformLatinHiragana_VH.get(kCFStringTransformLatinHiragana_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformLatinHiragana_VH.set(kCFStringTransformLatinHiragana_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformHiraganaKatakana typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformHiraganaKatakana_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformHiraganaKatakana_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformHiraganaKatakana").orElseThrow() }
private val kCFStringTransformHiraganaKatakana_VH: VarHandle by lazy { kCFStringTransformHiraganaKatakana_LAYOUT.varHandle() }

var kCFStringTransformHiraganaKatakana: MemorySegment
    get() = kCFStringTransformHiraganaKatakana_VH.get(kCFStringTransformHiraganaKatakana_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformHiraganaKatakana_VH.set(kCFStringTransformHiraganaKatakana_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformMandarinLatin typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformMandarinLatin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformMandarinLatin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformMandarinLatin").orElseThrow() }
private val kCFStringTransformMandarinLatin_VH: VarHandle by lazy { kCFStringTransformMandarinLatin_LAYOUT.varHandle() }

var kCFStringTransformMandarinLatin: MemorySegment
    get() = kCFStringTransformMandarinLatin_VH.get(kCFStringTransformMandarinLatin_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformMandarinLatin_VH.set(kCFStringTransformMandarinLatin_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformLatinHangul typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformLatinHangul_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformLatinHangul_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformLatinHangul").orElseThrow() }
private val kCFStringTransformLatinHangul_VH: VarHandle by lazy { kCFStringTransformLatinHangul_LAYOUT.varHandle() }

var kCFStringTransformLatinHangul: MemorySegment
    get() = kCFStringTransformLatinHangul_VH.get(kCFStringTransformLatinHangul_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformLatinHangul_VH.set(kCFStringTransformLatinHangul_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformLatinArabic typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformLatinArabic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformLatinArabic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformLatinArabic").orElseThrow() }
private val kCFStringTransformLatinArabic_VH: VarHandle by lazy { kCFStringTransformLatinArabic_LAYOUT.varHandle() }

var kCFStringTransformLatinArabic: MemorySegment
    get() = kCFStringTransformLatinArabic_VH.get(kCFStringTransformLatinArabic_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformLatinArabic_VH.set(kCFStringTransformLatinArabic_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformLatinHebrew typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformLatinHebrew_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformLatinHebrew_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformLatinHebrew").orElseThrow() }
private val kCFStringTransformLatinHebrew_VH: VarHandle by lazy { kCFStringTransformLatinHebrew_LAYOUT.varHandle() }

var kCFStringTransformLatinHebrew: MemorySegment
    get() = kCFStringTransformLatinHebrew_VH.get(kCFStringTransformLatinHebrew_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformLatinHebrew_VH.set(kCFStringTransformLatinHebrew_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformLatinThai typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformLatinThai_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformLatinThai_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformLatinThai").orElseThrow() }
private val kCFStringTransformLatinThai_VH: VarHandle by lazy { kCFStringTransformLatinThai_LAYOUT.varHandle() }

var kCFStringTransformLatinThai: MemorySegment
    get() = kCFStringTransformLatinThai_VH.get(kCFStringTransformLatinThai_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformLatinThai_VH.set(kCFStringTransformLatinThai_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformLatinCyrillic typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformLatinCyrillic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformLatinCyrillic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformLatinCyrillic").orElseThrow() }
private val kCFStringTransformLatinCyrillic_VH: VarHandle by lazy { kCFStringTransformLatinCyrillic_LAYOUT.varHandle() }

var kCFStringTransformLatinCyrillic: MemorySegment
    get() = kCFStringTransformLatinCyrillic_VH.get(kCFStringTransformLatinCyrillic_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformLatinCyrillic_VH.set(kCFStringTransformLatinCyrillic_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformLatinGreek typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformLatinGreek_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformLatinGreek_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformLatinGreek").orElseThrow() }
private val kCFStringTransformLatinGreek_VH: VarHandle by lazy { kCFStringTransformLatinGreek_LAYOUT.varHandle() }

var kCFStringTransformLatinGreek: MemorySegment
    get() = kCFStringTransformLatinGreek_VH.get(kCFStringTransformLatinGreek_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformLatinGreek_VH.set(kCFStringTransformLatinGreek_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformToXMLHex typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformToXMLHex_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformToXMLHex_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformToXMLHex").orElseThrow() }
private val kCFStringTransformToXMLHex_VH: VarHandle by lazy { kCFStringTransformToXMLHex_LAYOUT.varHandle() }

var kCFStringTransformToXMLHex: MemorySegment
    get() = kCFStringTransformToXMLHex_VH.get(kCFStringTransformToXMLHex_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformToXMLHex_VH.set(kCFStringTransformToXMLHex_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformToUnicodeName typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformToUnicodeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformToUnicodeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformToUnicodeName").orElseThrow() }
private val kCFStringTransformToUnicodeName_VH: VarHandle by lazy { kCFStringTransformToUnicodeName_LAYOUT.varHandle() }

var kCFStringTransformToUnicodeName: MemorySegment
    get() = kCFStringTransformToUnicodeName_VH.get(kCFStringTransformToUnicodeName_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformToUnicodeName_VH.set(kCFStringTransformToUnicodeName_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStringTransformStripDiacritics typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFStringTransformStripDiacritics_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStringTransformStripDiacritics_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStringTransformStripDiacritics").orElseThrow() }
private val kCFStringTransformStripDiacritics_VH: VarHandle by lazy { kCFStringTransformStripDiacritics_LAYOUT.varHandle() }

var kCFStringTransformStripDiacritics: MemorySegment
    get() = kCFStringTransformStripDiacritics_VH.get(kCFStringTransformStripDiacritics_SEGMENT) as MemorySegment
    set(value) = kCFStringTransformStripDiacritics_VH.set(kCFStringTransformStripDiacritics_SEGMENT, value)

/**
 * {@snippet lang=c : CFStringIsEncodingAvailable typedef Boolean = UNSIGNED = Char(typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringIsEncodingAvailable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.JAVA_INT)
private val CFStringIsEncodingAvailable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringIsEncodingAvailable").orElseThrow()
private val CFStringIsEncodingAvailable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringIsEncodingAvailable_ADDR, CFStringIsEncodingAvailable_DESC)

fun CFStringIsEncodingAvailable(arg0: Int): Byte {
    try {
        return CFStringIsEncodingAvailable_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetListOfAvailableEncodings (typedef CFStringEncoding = UNSIGNED = Int)*()
 */
private val CFStringGetListOfAvailableEncodings_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFStringGetListOfAvailableEncodings_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetListOfAvailableEncodings").orElseThrow()
private val CFStringGetListOfAvailableEncodings_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetListOfAvailableEncodings_ADDR, CFStringGetListOfAvailableEncodings_DESC)

fun CFStringGetListOfAvailableEncodings(): MemorySegment {
    try {
        return CFStringGetListOfAvailableEncodings_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetNameOfEncoding typedef CFStringRef = (Declared(__CFString))*(typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringGetNameOfEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFStringGetNameOfEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetNameOfEncoding").orElseThrow()
private val CFStringGetNameOfEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetNameOfEncoding_ADDR, CFStringGetNameOfEncoding_DESC)

fun CFStringGetNameOfEncoding(arg0: Int): MemorySegment {
    try {
        return CFStringGetNameOfEncoding_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringConvertEncodingToNSStringEncoding UNSIGNED = Long(typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringConvertEncodingToNSStringEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT)
private val CFStringConvertEncodingToNSStringEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringConvertEncodingToNSStringEncoding").orElseThrow()
private val CFStringConvertEncodingToNSStringEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringConvertEncodingToNSStringEncoding_ADDR, CFStringConvertEncodingToNSStringEncoding_DESC)

fun CFStringConvertEncodingToNSStringEncoding(arg0: Int): Long {
    try {
        return CFStringConvertEncodingToNSStringEncoding_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringConvertNSStringEncodingToEncoding typedef CFStringEncoding = UNSIGNED = Int(UNSIGNED = Long)
 */
private val CFStringConvertNSStringEncodingToEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG)
private val CFStringConvertNSStringEncodingToEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringConvertNSStringEncodingToEncoding").orElseThrow()
private val CFStringConvertNSStringEncodingToEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringConvertNSStringEncodingToEncoding_ADDR, CFStringConvertNSStringEncodingToEncoding_DESC)

fun CFStringConvertNSStringEncodingToEncoding(arg0: Long): Int {
    try {
        return CFStringConvertNSStringEncodingToEncoding_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringConvertEncodingToWindowsCodepage typedef UInt32 = UNSIGNED = Int(typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringConvertEncodingToWindowsCodepage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CFStringConvertEncodingToWindowsCodepage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringConvertEncodingToWindowsCodepage").orElseThrow()
private val CFStringConvertEncodingToWindowsCodepage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringConvertEncodingToWindowsCodepage_ADDR, CFStringConvertEncodingToWindowsCodepage_DESC)

fun CFStringConvertEncodingToWindowsCodepage(arg0: Int): Int {
    try {
        return CFStringConvertEncodingToWindowsCodepage_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringConvertWindowsCodepageToEncoding typedef CFStringEncoding = UNSIGNED = Int(typedef UInt32 = UNSIGNED = Int)
 */
private val CFStringConvertWindowsCodepageToEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CFStringConvertWindowsCodepageToEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringConvertWindowsCodepageToEncoding").orElseThrow()
private val CFStringConvertWindowsCodepageToEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringConvertWindowsCodepageToEncoding_ADDR, CFStringConvertWindowsCodepageToEncoding_DESC)

fun CFStringConvertWindowsCodepageToEncoding(arg0: Int): Int {
    try {
        return CFStringConvertWindowsCodepageToEncoding_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringConvertIANACharSetNameToEncoding typedef CFStringEncoding = UNSIGNED = Int(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFStringConvertIANACharSetNameToEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFStringConvertIANACharSetNameToEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringConvertIANACharSetNameToEncoding").orElseThrow()
private val CFStringConvertIANACharSetNameToEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringConvertIANACharSetNameToEncoding_ADDR, CFStringConvertIANACharSetNameToEncoding_DESC)

fun CFStringConvertIANACharSetNameToEncoding(arg0: MemorySegment): Int {
    try {
        return CFStringConvertIANACharSetNameToEncoding_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringConvertEncodingToIANACharSetName typedef CFStringRef = (Declared(__CFString))*(typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringConvertEncodingToIANACharSetName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFStringConvertEncodingToIANACharSetName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringConvertEncodingToIANACharSetName").orElseThrow()
private val CFStringConvertEncodingToIANACharSetName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringConvertEncodingToIANACharSetName_ADDR, CFStringConvertEncodingToIANACharSetName_DESC)

fun CFStringConvertEncodingToIANACharSetName(arg0: Int): MemorySegment {
    try {
        return CFStringConvertEncodingToIANACharSetName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStringGetMostCompatibleMacStringEncoding typedef CFStringEncoding = UNSIGNED = Int(typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFStringGetMostCompatibleMacStringEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CFStringGetMostCompatibleMacStringEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStringGetMostCompatibleMacStringEncoding").orElseThrow()
private val CFStringGetMostCompatibleMacStringEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStringGetMostCompatibleMacStringEncoding_ADDR, CFStringGetMostCompatibleMacStringEncoding_DESC)

fun CFStringGetMostCompatibleMacStringEncoding(arg0: Int): Int {
    try {
        return CFStringGetMostCompatibleMacStringEncoding_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFShow Void(typedef CFTypeRef = (Void)*)
 */
private val CFShow_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFShow_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFShow").orElseThrow()
private val CFShow_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFShow_ADDR, CFShow_DESC)

fun CFShow(arg0: MemorySegment): Unit {
    try {
        CFShow_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFShowStr Void(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFShowStr_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFShowStr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFShowStr").orElseThrow()
private val CFShowStr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFShowStr_ADDR, CFShowStr_DESC)

fun CFShowStr(arg0: MemorySegment): Unit {
    try {
        CFShowStr_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : __CFStringMakeConstantString typedef CFStringRef = (Declared(__CFString))*((Char)*)
 */
private val _CFStringMakeConstantString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val _CFStringMakeConstantString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("__CFStringMakeConstantString").orElseThrow()
private val _CFStringMakeConstantString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(_CFStringMakeConstantString_ADDR, _CFStringMakeConstantString_DESC)

fun _CFStringMakeConstantString(arg0: MemorySegment): MemorySegment {
    try {
        return _CFStringMakeConstantString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFTimeZoneGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFTimeZoneGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneGetTypeID").orElseThrow()
private val CFTimeZoneGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneGetTypeID_ADDR, CFTimeZoneGetTypeID_DESC)

fun CFTimeZoneGetTypeID(): Long {
    try {
        return CFTimeZoneGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneCopySystem typedef CFTimeZoneRef = (Declared(__CFTimeZone))*()
 */
private val CFTimeZoneCopySystem_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFTimeZoneCopySystem_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneCopySystem").orElseThrow()
private val CFTimeZoneCopySystem_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneCopySystem_ADDR, CFTimeZoneCopySystem_DESC)

fun CFTimeZoneCopySystem(): MemorySegment {
    try {
        return CFTimeZoneCopySystem_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneResetSystem Void()
 */
private val CFTimeZoneResetSystem_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val CFTimeZoneResetSystem_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneResetSystem").orElseThrow()
private val CFTimeZoneResetSystem_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneResetSystem_ADDR, CFTimeZoneResetSystem_DESC)

fun CFTimeZoneResetSystem(): Unit {
    try {
        CFTimeZoneResetSystem_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneCopyDefault typedef CFTimeZoneRef = (Declared(__CFTimeZone))*()
 */
private val CFTimeZoneCopyDefault_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFTimeZoneCopyDefault_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneCopyDefault").orElseThrow()
private val CFTimeZoneCopyDefault_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneCopyDefault_ADDR, CFTimeZoneCopyDefault_DESC)

fun CFTimeZoneCopyDefault(): MemorySegment {
    try {
        return CFTimeZoneCopyDefault_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneSetDefault Void(typedef CFTimeZoneRef = (Declared(__CFTimeZone))*)
 */
private val CFTimeZoneSetDefault_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFTimeZoneSetDefault_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneSetDefault").orElseThrow()
private val CFTimeZoneSetDefault_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneSetDefault_ADDR, CFTimeZoneSetDefault_DESC)

fun CFTimeZoneSetDefault(arg0: MemorySegment): Unit {
    try {
        CFTimeZoneSetDefault_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneCopyKnownNames typedef CFArrayRef = (Declared(__CFArray))*()
 */
private val CFTimeZoneCopyKnownNames_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFTimeZoneCopyKnownNames_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneCopyKnownNames").orElseThrow()
private val CFTimeZoneCopyKnownNames_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneCopyKnownNames_ADDR, CFTimeZoneCopyKnownNames_DESC)

fun CFTimeZoneCopyKnownNames(): MemorySegment {
    try {
        return CFTimeZoneCopyKnownNames_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneCopyAbbreviationDictionary typedef CFDictionaryRef = (Declared(__CFDictionary))*()
 */
private val CFTimeZoneCopyAbbreviationDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFTimeZoneCopyAbbreviationDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneCopyAbbreviationDictionary").orElseThrow()
private val CFTimeZoneCopyAbbreviationDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneCopyAbbreviationDictionary_ADDR, CFTimeZoneCopyAbbreviationDictionary_DESC)

fun CFTimeZoneCopyAbbreviationDictionary(): MemorySegment {
    try {
        return CFTimeZoneCopyAbbreviationDictionary_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneSetAbbreviationDictionary Void(typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CFTimeZoneSetAbbreviationDictionary_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFTimeZoneSetAbbreviationDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneSetAbbreviationDictionary").orElseThrow()
private val CFTimeZoneSetAbbreviationDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneSetAbbreviationDictionary_ADDR, CFTimeZoneSetAbbreviationDictionary_DESC)

fun CFTimeZoneSetAbbreviationDictionary(arg0: MemorySegment): Unit {
    try {
        CFTimeZoneSetAbbreviationDictionary_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneCreate typedef CFTimeZoneRef = (Declared(__CFTimeZone))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFDataRef = (Declared(__CFData))*)
 */
private val CFTimeZoneCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTimeZoneCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneCreate").orElseThrow()
private val CFTimeZoneCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneCreate_ADDR, CFTimeZoneCreate_DESC)

fun CFTimeZoneCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFTimeZoneCreate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneCreateWithTimeIntervalFromGMT typedef CFTimeZoneRef = (Declared(__CFTimeZone))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFTimeInterval = Double)
 */
private val CFTimeZoneCreateWithTimeIntervalFromGMT_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFTimeZoneCreateWithTimeIntervalFromGMT_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneCreateWithTimeIntervalFromGMT").orElseThrow()
private val CFTimeZoneCreateWithTimeIntervalFromGMT_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneCreateWithTimeIntervalFromGMT_ADDR, CFTimeZoneCreateWithTimeIntervalFromGMT_DESC)

fun CFTimeZoneCreateWithTimeIntervalFromGMT(arg0: MemorySegment, arg1: Double): MemorySegment {
    try {
        return CFTimeZoneCreateWithTimeIntervalFromGMT_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneCreateWithName typedef CFTimeZoneRef = (Declared(__CFTimeZone))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef Boolean = UNSIGNED = Char)
 */
private val CFTimeZoneCreateWithName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE)
private val CFTimeZoneCreateWithName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneCreateWithName").orElseThrow()
private val CFTimeZoneCreateWithName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneCreateWithName_ADDR, CFTimeZoneCreateWithName_DESC)

fun CFTimeZoneCreateWithName(arg0: MemorySegment, arg1: MemorySegment, arg2: Byte): MemorySegment {
    try {
        return CFTimeZoneCreateWithName_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneGetName typedef CFStringRef = (Declared(__CFString))*(typedef CFTimeZoneRef = (Declared(__CFTimeZone))*)
 */
private val CFTimeZoneGetName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTimeZoneGetName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneGetName").orElseThrow()
private val CFTimeZoneGetName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneGetName_ADDR, CFTimeZoneGetName_DESC)

fun CFTimeZoneGetName(arg0: MemorySegment): MemorySegment {
    try {
        return CFTimeZoneGetName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneGetData typedef CFDataRef = (Declared(__CFData))*(typedef CFTimeZoneRef = (Declared(__CFTimeZone))*)
 */
private val CFTimeZoneGetData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTimeZoneGetData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneGetData").orElseThrow()
private val CFTimeZoneGetData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneGetData_ADDR, CFTimeZoneGetData_DESC)

fun CFTimeZoneGetData(arg0: MemorySegment): MemorySegment {
    try {
        return CFTimeZoneGetData_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneGetSecondsFromGMT typedef CFTimeInterval = Double(typedef CFTimeZoneRef = (Declared(__CFTimeZone))*,typedef CFAbsoluteTime = Double)
 */
private val CFTimeZoneGetSecondsFromGMT_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFTimeZoneGetSecondsFromGMT_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneGetSecondsFromGMT").orElseThrow()
private val CFTimeZoneGetSecondsFromGMT_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneGetSecondsFromGMT_ADDR, CFTimeZoneGetSecondsFromGMT_DESC)

fun CFTimeZoneGetSecondsFromGMT(arg0: MemorySegment, arg1: Double): Double {
    try {
        return CFTimeZoneGetSecondsFromGMT_HANDLE.invokeExact(arg0, arg1) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneCopyAbbreviation typedef CFStringRef = (Declared(__CFString))*(typedef CFTimeZoneRef = (Declared(__CFTimeZone))*,typedef CFAbsoluteTime = Double)
 */
private val CFTimeZoneCopyAbbreviation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFTimeZoneCopyAbbreviation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneCopyAbbreviation").orElseThrow()
private val CFTimeZoneCopyAbbreviation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneCopyAbbreviation_ADDR, CFTimeZoneCopyAbbreviation_DESC)

fun CFTimeZoneCopyAbbreviation(arg0: MemorySegment, arg1: Double): MemorySegment {
    try {
        return CFTimeZoneCopyAbbreviation_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneIsDaylightSavingTime typedef Boolean = UNSIGNED = Char(typedef CFTimeZoneRef = (Declared(__CFTimeZone))*,typedef CFAbsoluteTime = Double)
 */
private val CFTimeZoneIsDaylightSavingTime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFTimeZoneIsDaylightSavingTime_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneIsDaylightSavingTime").orElseThrow()
private val CFTimeZoneIsDaylightSavingTime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneIsDaylightSavingTime_ADDR, CFTimeZoneIsDaylightSavingTime_DESC)

fun CFTimeZoneIsDaylightSavingTime(arg0: MemorySegment, arg1: Double): Byte {
    try {
        return CFTimeZoneIsDaylightSavingTime_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneGetDaylightSavingTimeOffset typedef CFTimeInterval = Double(typedef CFTimeZoneRef = (Declared(__CFTimeZone))*,typedef CFAbsoluteTime = Double)
 */
private val CFTimeZoneGetDaylightSavingTimeOffset_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFTimeZoneGetDaylightSavingTimeOffset_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneGetDaylightSavingTimeOffset").orElseThrow()
private val CFTimeZoneGetDaylightSavingTimeOffset_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneGetDaylightSavingTimeOffset_ADDR, CFTimeZoneGetDaylightSavingTimeOffset_DESC)

fun CFTimeZoneGetDaylightSavingTimeOffset(arg0: MemorySegment, arg1: Double): Double {
    try {
        return CFTimeZoneGetDaylightSavingTimeOffset_HANDLE.invokeExact(arg0, arg1) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTimeZoneGetNextDaylightSavingTimeTransition typedef CFAbsoluteTime = Double(typedef CFTimeZoneRef = (Declared(__CFTimeZone))*,typedef CFAbsoluteTime = Double)
 */
private val CFTimeZoneGetNextDaylightSavingTimeTransition_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFTimeZoneGetNextDaylightSavingTimeTransition_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTimeZoneGetNextDaylightSavingTimeTransition").orElseThrow()
private val CFTimeZoneGetNextDaylightSavingTimeTransition_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTimeZoneGetNextDaylightSavingTimeTransition_ADDR, CFTimeZoneGetNextDaylightSavingTimeTransition_DESC)

fun CFTimeZoneGetNextDaylightSavingTimeTransition(arg0: MemorySegment, arg1: Double): Double {
    try {
        return CFTimeZoneGetNextDaylightSavingTimeTransition_HANDLE.invokeExact(arg0, arg1) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFTimeZoneSystemTimeZoneDidChangeNotification typedef const CFNotificationName = (Declared(__CFString))*
 */
private val kCFTimeZoneSystemTimeZoneDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFTimeZoneSystemTimeZoneDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFTimeZoneSystemTimeZoneDidChangeNotification").orElseThrow() }
private val kCFTimeZoneSystemTimeZoneDidChangeNotification_VH: VarHandle by lazy { kCFTimeZoneSystemTimeZoneDidChangeNotification_LAYOUT.varHandle() }

var kCFTimeZoneSystemTimeZoneDidChangeNotification: MemorySegment
    get() = kCFTimeZoneSystemTimeZoneDidChangeNotification_VH.get(kCFTimeZoneSystemTimeZoneDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = kCFTimeZoneSystemTimeZoneDidChangeNotification_VH.set(kCFTimeZoneSystemTimeZoneDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : CFCalendarGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFCalendarGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFCalendarGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarGetTypeID").orElseThrow()
private val CFCalendarGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarGetTypeID_ADDR, CFCalendarGetTypeID_DESC)

fun CFCalendarGetTypeID(): Long {
    try {
        return CFCalendarGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarCopyCurrent typedef CFCalendarRef = (Declared(__CFCalendar))*()
 */
private val CFCalendarCopyCurrent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFCalendarCopyCurrent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarCopyCurrent").orElseThrow()
private val CFCalendarCopyCurrent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarCopyCurrent_ADDR, CFCalendarCopyCurrent_DESC)

fun CFCalendarCopyCurrent(): MemorySegment {
    try {
        return CFCalendarCopyCurrent_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarCreateWithIdentifier typedef CFCalendarRef = (Declared(__CFCalendar))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFCalendarIdentifier = (Declared(__CFString))*)
 */
private val CFCalendarCreateWithIdentifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCalendarCreateWithIdentifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarCreateWithIdentifier").orElseThrow()
private val CFCalendarCreateWithIdentifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarCreateWithIdentifier_ADDR, CFCalendarCreateWithIdentifier_DESC)

fun CFCalendarCreateWithIdentifier(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFCalendarCreateWithIdentifier_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarGetIdentifier typedef CFCalendarIdentifier = (Declared(__CFString))*(typedef CFCalendarRef = (Declared(__CFCalendar))*)
 */
private val CFCalendarGetIdentifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCalendarGetIdentifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarGetIdentifier").orElseThrow()
private val CFCalendarGetIdentifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarGetIdentifier_ADDR, CFCalendarGetIdentifier_DESC)

fun CFCalendarGetIdentifier(arg0: MemorySegment): MemorySegment {
    try {
        return CFCalendarGetIdentifier_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarCopyLocale typedef CFLocaleRef = (Declared(__CFLocale))*(typedef CFCalendarRef = (Declared(__CFCalendar))*)
 */
private val CFCalendarCopyLocale_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCalendarCopyLocale_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarCopyLocale").orElseThrow()
private val CFCalendarCopyLocale_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarCopyLocale_ADDR, CFCalendarCopyLocale_DESC)

fun CFCalendarCopyLocale(arg0: MemorySegment): MemorySegment {
    try {
        return CFCalendarCopyLocale_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarSetLocale Void(typedef CFCalendarRef = (Declared(__CFCalendar))*,typedef CFLocaleRef = (Declared(__CFLocale))*)
 */
private val CFCalendarSetLocale_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCalendarSetLocale_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarSetLocale").orElseThrow()
private val CFCalendarSetLocale_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarSetLocale_ADDR, CFCalendarSetLocale_DESC)

fun CFCalendarSetLocale(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFCalendarSetLocale_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarCopyTimeZone typedef CFTimeZoneRef = (Declared(__CFTimeZone))*(typedef CFCalendarRef = (Declared(__CFCalendar))*)
 */
private val CFCalendarCopyTimeZone_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCalendarCopyTimeZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarCopyTimeZone").orElseThrow()
private val CFCalendarCopyTimeZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarCopyTimeZone_ADDR, CFCalendarCopyTimeZone_DESC)

fun CFCalendarCopyTimeZone(arg0: MemorySegment): MemorySegment {
    try {
        return CFCalendarCopyTimeZone_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarSetTimeZone Void(typedef CFCalendarRef = (Declared(__CFCalendar))*,typedef CFTimeZoneRef = (Declared(__CFTimeZone))*)
 */
private val CFCalendarSetTimeZone_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCalendarSetTimeZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarSetTimeZone").orElseThrow()
private val CFCalendarSetTimeZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarSetTimeZone_ADDR, CFCalendarSetTimeZone_DESC)

fun CFCalendarSetTimeZone(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFCalendarSetTimeZone_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarGetFirstWeekday typedef CFIndex = Long(typedef CFCalendarRef = (Declared(__CFCalendar))*)
 */
private val CFCalendarGetFirstWeekday_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFCalendarGetFirstWeekday_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarGetFirstWeekday").orElseThrow()
private val CFCalendarGetFirstWeekday_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarGetFirstWeekday_ADDR, CFCalendarGetFirstWeekday_DESC)

fun CFCalendarGetFirstWeekday(arg0: MemorySegment): Long {
    try {
        return CFCalendarGetFirstWeekday_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarSetFirstWeekday Void(typedef CFCalendarRef = (Declared(__CFCalendar))*,typedef CFIndex = Long)
 */
private val CFCalendarSetFirstWeekday_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFCalendarSetFirstWeekday_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarSetFirstWeekday").orElseThrow()
private val CFCalendarSetFirstWeekday_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarSetFirstWeekday_ADDR, CFCalendarSetFirstWeekday_DESC)

fun CFCalendarSetFirstWeekday(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFCalendarSetFirstWeekday_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarGetMinimumDaysInFirstWeek typedef CFIndex = Long(typedef CFCalendarRef = (Declared(__CFCalendar))*)
 */
private val CFCalendarGetMinimumDaysInFirstWeek_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFCalendarGetMinimumDaysInFirstWeek_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarGetMinimumDaysInFirstWeek").orElseThrow()
private val CFCalendarGetMinimumDaysInFirstWeek_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarGetMinimumDaysInFirstWeek_ADDR, CFCalendarGetMinimumDaysInFirstWeek_DESC)

fun CFCalendarGetMinimumDaysInFirstWeek(arg0: MemorySegment): Long {
    try {
        return CFCalendarGetMinimumDaysInFirstWeek_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarSetMinimumDaysInFirstWeek Void(typedef CFCalendarRef = (Declared(__CFCalendar))*,typedef CFIndex = Long)
 */
private val CFCalendarSetMinimumDaysInFirstWeek_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFCalendarSetMinimumDaysInFirstWeek_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarSetMinimumDaysInFirstWeek").orElseThrow()
private val CFCalendarSetMinimumDaysInFirstWeek_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarSetMinimumDaysInFirstWeek_ADDR, CFCalendarSetMinimumDaysInFirstWeek_DESC)

fun CFCalendarSetMinimumDaysInFirstWeek(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFCalendarSetMinimumDaysInFirstWeek_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarComposeAbsoluteTime typedef Boolean = UNSIGNED = Char(typedef CFCalendarRef = (Declared(__CFCalendar))*,(typedef CFAbsoluteTime = Double)*,(Char)*)
 */
private val CFCalendarComposeAbsoluteTime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFCalendarComposeAbsoluteTime_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarComposeAbsoluteTime").orElseThrow()
private val CFCalendarComposeAbsoluteTime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarComposeAbsoluteTime_ADDR, CFCalendarComposeAbsoluteTime_DESC)

fun CFCalendarComposeAbsoluteTime(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFCalendarComposeAbsoluteTime_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarDecomposeAbsoluteTime typedef Boolean = UNSIGNED = Char(typedef CFCalendarRef = (Declared(__CFCalendar))*,typedef CFAbsoluteTime = Double,(Char)*)
 */
private val CFCalendarDecomposeAbsoluteTime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFCalendarDecomposeAbsoluteTime_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarDecomposeAbsoluteTime").orElseThrow()
private val CFCalendarDecomposeAbsoluteTime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarDecomposeAbsoluteTime_ADDR, CFCalendarDecomposeAbsoluteTime_DESC)

fun CFCalendarDecomposeAbsoluteTime(arg0: MemorySegment, arg1: Double, arg2: MemorySegment): Byte {
    try {
        return CFCalendarDecomposeAbsoluteTime_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarAddComponents typedef Boolean = UNSIGNED = Char(typedef CFCalendarRef = (Declared(__CFCalendar))*,(typedef CFAbsoluteTime = Double)*,typedef CFOptionFlags = UNSIGNED = Long,(Char)*)
 */
private val CFCalendarAddComponents_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFCalendarAddComponents_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarAddComponents").orElseThrow()
private val CFCalendarAddComponents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarAddComponents_ADDR, CFCalendarAddComponents_DESC)

fun CFCalendarAddComponents(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): Byte {
    try {
        return CFCalendarAddComponents_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFCalendarGetComponentDifference typedef Boolean = UNSIGNED = Char(typedef CFCalendarRef = (Declared(__CFCalendar))*,typedef CFAbsoluteTime = Double,typedef CFAbsoluteTime = Double,typedef CFOptionFlags = UNSIGNED = Long,(Char)*)
 */
private val CFCalendarGetComponentDifference_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFCalendarGetComponentDifference_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFCalendarGetComponentDifference").orElseThrow()
private val CFCalendarGetComponentDifference_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFCalendarGetComponentDifference_ADDR, CFCalendarGetComponentDifference_DESC)

fun CFCalendarGetComponentDifference(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Long, arg4: MemorySegment): Byte {
    try {
        return CFCalendarGetComponentDifference_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterCreateDateFormatFromTemplate typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFLocaleRef = (Declared(__CFLocale))*)
 */
private val CFDateFormatterCreateDateFormatFromTemplate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFDateFormatterCreateDateFormatFromTemplate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterCreateDateFormatFromTemplate").orElseThrow()
private val CFDateFormatterCreateDateFormatFromTemplate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterCreateDateFormatFromTemplate_ADDR, CFDateFormatterCreateDateFormatFromTemplate_DESC)

fun CFDateFormatterCreateDateFormatFromTemplate(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CFDateFormatterCreateDateFormatFromTemplate_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFDateFormatterGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFDateFormatterGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterGetTypeID").orElseThrow()
private val CFDateFormatterGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterGetTypeID_ADDR, CFDateFormatterGetTypeID_DESC)

fun CFDateFormatterGetTypeID(): Long {
    try {
        return CFDateFormatterGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterGetLocale typedef CFLocaleRef = (Declared(__CFLocale))*(typedef CFDateFormatterRef = (Declared(__CFDateFormatter))*)
 */
private val CFDateFormatterGetLocale_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDateFormatterGetLocale_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterGetLocale").orElseThrow()
private val CFDateFormatterGetLocale_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterGetLocale_ADDR, CFDateFormatterGetLocale_DESC)

fun CFDateFormatterGetLocale(arg0: MemorySegment): MemorySegment {
    try {
        return CFDateFormatterGetLocale_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterGetFormat typedef CFStringRef = (Declared(__CFString))*(typedef CFDateFormatterRef = (Declared(__CFDateFormatter))*)
 */
private val CFDateFormatterGetFormat_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDateFormatterGetFormat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterGetFormat").orElseThrow()
private val CFDateFormatterGetFormat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterGetFormat_ADDR, CFDateFormatterGetFormat_DESC)

fun CFDateFormatterGetFormat(arg0: MemorySegment): MemorySegment {
    try {
        return CFDateFormatterGetFormat_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterSetFormat Void(typedef CFDateFormatterRef = (Declared(__CFDateFormatter))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFDateFormatterSetFormat_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDateFormatterSetFormat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterSetFormat").orElseThrow()
private val CFDateFormatterSetFormat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterSetFormat_ADDR, CFDateFormatterSetFormat_DESC)

fun CFDateFormatterSetFormat(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFDateFormatterSetFormat_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterCreateStringWithDate typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDateFormatterRef = (Declared(__CFDateFormatter))*,typedef CFDateRef = (Declared(__CFDate))*)
 */
private val CFDateFormatterCreateStringWithDate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDateFormatterCreateStringWithDate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterCreateStringWithDate").orElseThrow()
private val CFDateFormatterCreateStringWithDate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterCreateStringWithDate_ADDR, CFDateFormatterCreateStringWithDate_DESC)

fun CFDateFormatterCreateStringWithDate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFDateFormatterCreateStringWithDate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterCreateStringWithAbsoluteTime typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDateFormatterRef = (Declared(__CFDateFormatter))*,typedef CFAbsoluteTime = Double)
 */
private val CFDateFormatterCreateStringWithAbsoluteTime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFDateFormatterCreateStringWithAbsoluteTime_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterCreateStringWithAbsoluteTime").orElseThrow()
private val CFDateFormatterCreateStringWithAbsoluteTime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterCreateStringWithAbsoluteTime_ADDR, CFDateFormatterCreateStringWithAbsoluteTime_DESC)

fun CFDateFormatterCreateStringWithAbsoluteTime(arg0: MemorySegment, arg1: MemorySegment, arg2: Double): MemorySegment {
    try {
        return CFDateFormatterCreateStringWithAbsoluteTime_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterCreateDateFromString typedef CFDateRef = (Declared(__CFDate))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDateFormatterRef = (Declared(__CFDateFormatter))*,typedef CFStringRef = (Declared(__CFString))*,(typedef CFRange = Declared(CFRange))*)
 */
private val CFDateFormatterCreateDateFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDateFormatterCreateDateFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterCreateDateFromString").orElseThrow()
private val CFDateFormatterCreateDateFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterCreateDateFromString_ADDR, CFDateFormatterCreateDateFromString_DESC)

fun CFDateFormatterCreateDateFromString(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFDateFormatterCreateDateFromString_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterGetAbsoluteTimeFromString typedef Boolean = UNSIGNED = Char(typedef CFDateFormatterRef = (Declared(__CFDateFormatter))*,typedef CFStringRef = (Declared(__CFString))*,(typedef CFRange = Declared(CFRange))*,(typedef CFAbsoluteTime = Double)*)
 */
private val CFDateFormatterGetAbsoluteTimeFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDateFormatterGetAbsoluteTimeFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterGetAbsoluteTimeFromString").orElseThrow()
private val CFDateFormatterGetAbsoluteTimeFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterGetAbsoluteTimeFromString_ADDR, CFDateFormatterGetAbsoluteTimeFromString_DESC)

fun CFDateFormatterGetAbsoluteTimeFromString(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Byte {
    try {
        return CFDateFormatterGetAbsoluteTimeFromString_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterSetProperty Void(typedef CFDateFormatterRef = (Declared(__CFDateFormatter))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFTypeRef = (Void)*)
 */
private val CFDateFormatterSetProperty_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDateFormatterSetProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterSetProperty").orElseThrow()
private val CFDateFormatterSetProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterSetProperty_ADDR, CFDateFormatterSetProperty_DESC)

fun CFDateFormatterSetProperty(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFDateFormatterSetProperty_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFDateFormatterCopyProperty typedef CFTypeRef = (Void)*(typedef CFDateFormatterRef = (Declared(__CFDateFormatter))*,typedef CFDateFormatterKey = (Declared(__CFString))*)
 */
private val CFDateFormatterCopyProperty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFDateFormatterCopyProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFDateFormatterCopyProperty").orElseThrow()
private val CFDateFormatterCopyProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFDateFormatterCopyProperty_ADDR, CFDateFormatterCopyProperty_DESC)

fun CFDateFormatterCopyProperty(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFDateFormatterCopyProperty_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFDateFormatterIsLenient typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterIsLenient_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterIsLenient_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterIsLenient").orElseThrow() }
private val kCFDateFormatterIsLenient_VH: VarHandle by lazy { kCFDateFormatterIsLenient_LAYOUT.varHandle() }

var kCFDateFormatterIsLenient: MemorySegment
    get() = kCFDateFormatterIsLenient_VH.get(kCFDateFormatterIsLenient_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterIsLenient_VH.set(kCFDateFormatterIsLenient_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterTimeZone typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterTimeZone_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterTimeZone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterTimeZone").orElseThrow() }
private val kCFDateFormatterTimeZone_VH: VarHandle by lazy { kCFDateFormatterTimeZone_LAYOUT.varHandle() }

var kCFDateFormatterTimeZone: MemorySegment
    get() = kCFDateFormatterTimeZone_VH.get(kCFDateFormatterTimeZone_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterTimeZone_VH.set(kCFDateFormatterTimeZone_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterCalendarName typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterCalendarName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterCalendarName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterCalendarName").orElseThrow() }
private val kCFDateFormatterCalendarName_VH: VarHandle by lazy { kCFDateFormatterCalendarName_LAYOUT.varHandle() }

var kCFDateFormatterCalendarName: MemorySegment
    get() = kCFDateFormatterCalendarName_VH.get(kCFDateFormatterCalendarName_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterCalendarName_VH.set(kCFDateFormatterCalendarName_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterDefaultFormat typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterDefaultFormat_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterDefaultFormat_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterDefaultFormat").orElseThrow() }
private val kCFDateFormatterDefaultFormat_VH: VarHandle by lazy { kCFDateFormatterDefaultFormat_LAYOUT.varHandle() }

var kCFDateFormatterDefaultFormat: MemorySegment
    get() = kCFDateFormatterDefaultFormat_VH.get(kCFDateFormatterDefaultFormat_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterDefaultFormat_VH.set(kCFDateFormatterDefaultFormat_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterTwoDigitStartDate typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterTwoDigitStartDate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterTwoDigitStartDate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterTwoDigitStartDate").orElseThrow() }
private val kCFDateFormatterTwoDigitStartDate_VH: VarHandle by lazy { kCFDateFormatterTwoDigitStartDate_LAYOUT.varHandle() }

var kCFDateFormatterTwoDigitStartDate: MemorySegment
    get() = kCFDateFormatterTwoDigitStartDate_VH.get(kCFDateFormatterTwoDigitStartDate_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterTwoDigitStartDate_VH.set(kCFDateFormatterTwoDigitStartDate_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterDefaultDate typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterDefaultDate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterDefaultDate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterDefaultDate").orElseThrow() }
private val kCFDateFormatterDefaultDate_VH: VarHandle by lazy { kCFDateFormatterDefaultDate_LAYOUT.varHandle() }

var kCFDateFormatterDefaultDate: MemorySegment
    get() = kCFDateFormatterDefaultDate_VH.get(kCFDateFormatterDefaultDate_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterDefaultDate_VH.set(kCFDateFormatterDefaultDate_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterCalendar typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterCalendar").orElseThrow() }
private val kCFDateFormatterCalendar_VH: VarHandle by lazy { kCFDateFormatterCalendar_LAYOUT.varHandle() }

var kCFDateFormatterCalendar: MemorySegment
    get() = kCFDateFormatterCalendar_VH.get(kCFDateFormatterCalendar_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterCalendar_VH.set(kCFDateFormatterCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterEraSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterEraSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterEraSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterEraSymbols").orElseThrow() }
private val kCFDateFormatterEraSymbols_VH: VarHandle by lazy { kCFDateFormatterEraSymbols_LAYOUT.varHandle() }

var kCFDateFormatterEraSymbols: MemorySegment
    get() = kCFDateFormatterEraSymbols_VH.get(kCFDateFormatterEraSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterEraSymbols_VH.set(kCFDateFormatterEraSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterMonthSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterMonthSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterMonthSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterMonthSymbols").orElseThrow() }
private val kCFDateFormatterMonthSymbols_VH: VarHandle by lazy { kCFDateFormatterMonthSymbols_LAYOUT.varHandle() }

var kCFDateFormatterMonthSymbols: MemorySegment
    get() = kCFDateFormatterMonthSymbols_VH.get(kCFDateFormatterMonthSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterMonthSymbols_VH.set(kCFDateFormatterMonthSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterShortMonthSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterShortMonthSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterShortMonthSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterShortMonthSymbols").orElseThrow() }
private val kCFDateFormatterShortMonthSymbols_VH: VarHandle by lazy { kCFDateFormatterShortMonthSymbols_LAYOUT.varHandle() }

var kCFDateFormatterShortMonthSymbols: MemorySegment
    get() = kCFDateFormatterShortMonthSymbols_VH.get(kCFDateFormatterShortMonthSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterShortMonthSymbols_VH.set(kCFDateFormatterShortMonthSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterWeekdaySymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterWeekdaySymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterWeekdaySymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterWeekdaySymbols").orElseThrow() }
private val kCFDateFormatterWeekdaySymbols_VH: VarHandle by lazy { kCFDateFormatterWeekdaySymbols_LAYOUT.varHandle() }

var kCFDateFormatterWeekdaySymbols: MemorySegment
    get() = kCFDateFormatterWeekdaySymbols_VH.get(kCFDateFormatterWeekdaySymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterWeekdaySymbols_VH.set(kCFDateFormatterWeekdaySymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterShortWeekdaySymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterShortWeekdaySymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterShortWeekdaySymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterShortWeekdaySymbols").orElseThrow() }
private val kCFDateFormatterShortWeekdaySymbols_VH: VarHandle by lazy { kCFDateFormatterShortWeekdaySymbols_LAYOUT.varHandle() }

var kCFDateFormatterShortWeekdaySymbols: MemorySegment
    get() = kCFDateFormatterShortWeekdaySymbols_VH.get(kCFDateFormatterShortWeekdaySymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterShortWeekdaySymbols_VH.set(kCFDateFormatterShortWeekdaySymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterAMSymbol typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterAMSymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterAMSymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterAMSymbol").orElseThrow() }
private val kCFDateFormatterAMSymbol_VH: VarHandle by lazy { kCFDateFormatterAMSymbol_LAYOUT.varHandle() }

var kCFDateFormatterAMSymbol: MemorySegment
    get() = kCFDateFormatterAMSymbol_VH.get(kCFDateFormatterAMSymbol_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterAMSymbol_VH.set(kCFDateFormatterAMSymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterPMSymbol typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterPMSymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterPMSymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterPMSymbol").orElseThrow() }
private val kCFDateFormatterPMSymbol_VH: VarHandle by lazy { kCFDateFormatterPMSymbol_LAYOUT.varHandle() }

var kCFDateFormatterPMSymbol: MemorySegment
    get() = kCFDateFormatterPMSymbol_VH.get(kCFDateFormatterPMSymbol_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterPMSymbol_VH.set(kCFDateFormatterPMSymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterLongEraSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterLongEraSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterLongEraSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterLongEraSymbols").orElseThrow() }
private val kCFDateFormatterLongEraSymbols_VH: VarHandle by lazy { kCFDateFormatterLongEraSymbols_LAYOUT.varHandle() }

var kCFDateFormatterLongEraSymbols: MemorySegment
    get() = kCFDateFormatterLongEraSymbols_VH.get(kCFDateFormatterLongEraSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterLongEraSymbols_VH.set(kCFDateFormatterLongEraSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterVeryShortMonthSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterVeryShortMonthSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterVeryShortMonthSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterVeryShortMonthSymbols").orElseThrow() }
private val kCFDateFormatterVeryShortMonthSymbols_VH: VarHandle by lazy { kCFDateFormatterVeryShortMonthSymbols_LAYOUT.varHandle() }

var kCFDateFormatterVeryShortMonthSymbols: MemorySegment
    get() = kCFDateFormatterVeryShortMonthSymbols_VH.get(kCFDateFormatterVeryShortMonthSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterVeryShortMonthSymbols_VH.set(kCFDateFormatterVeryShortMonthSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterStandaloneMonthSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterStandaloneMonthSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterStandaloneMonthSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterStandaloneMonthSymbols").orElseThrow() }
private val kCFDateFormatterStandaloneMonthSymbols_VH: VarHandle by lazy { kCFDateFormatterStandaloneMonthSymbols_LAYOUT.varHandle() }

var kCFDateFormatterStandaloneMonthSymbols: MemorySegment
    get() = kCFDateFormatterStandaloneMonthSymbols_VH.get(kCFDateFormatterStandaloneMonthSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterStandaloneMonthSymbols_VH.set(kCFDateFormatterStandaloneMonthSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterShortStandaloneMonthSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterShortStandaloneMonthSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterShortStandaloneMonthSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterShortStandaloneMonthSymbols").orElseThrow() }
private val kCFDateFormatterShortStandaloneMonthSymbols_VH: VarHandle by lazy { kCFDateFormatterShortStandaloneMonthSymbols_LAYOUT.varHandle() }

var kCFDateFormatterShortStandaloneMonthSymbols: MemorySegment
    get() = kCFDateFormatterShortStandaloneMonthSymbols_VH.get(kCFDateFormatterShortStandaloneMonthSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterShortStandaloneMonthSymbols_VH.set(kCFDateFormatterShortStandaloneMonthSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterVeryShortStandaloneMonthSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterVeryShortStandaloneMonthSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterVeryShortStandaloneMonthSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterVeryShortStandaloneMonthSymbols").orElseThrow() }
private val kCFDateFormatterVeryShortStandaloneMonthSymbols_VH: VarHandle by lazy { kCFDateFormatterVeryShortStandaloneMonthSymbols_LAYOUT.varHandle() }

var kCFDateFormatterVeryShortStandaloneMonthSymbols: MemorySegment
    get() = kCFDateFormatterVeryShortStandaloneMonthSymbols_VH.get(kCFDateFormatterVeryShortStandaloneMonthSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterVeryShortStandaloneMonthSymbols_VH.set(kCFDateFormatterVeryShortStandaloneMonthSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterVeryShortWeekdaySymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterVeryShortWeekdaySymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterVeryShortWeekdaySymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterVeryShortWeekdaySymbols").orElseThrow() }
private val kCFDateFormatterVeryShortWeekdaySymbols_VH: VarHandle by lazy { kCFDateFormatterVeryShortWeekdaySymbols_LAYOUT.varHandle() }

var kCFDateFormatterVeryShortWeekdaySymbols: MemorySegment
    get() = kCFDateFormatterVeryShortWeekdaySymbols_VH.get(kCFDateFormatterVeryShortWeekdaySymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterVeryShortWeekdaySymbols_VH.set(kCFDateFormatterVeryShortWeekdaySymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterStandaloneWeekdaySymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterStandaloneWeekdaySymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterStandaloneWeekdaySymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterStandaloneWeekdaySymbols").orElseThrow() }
private val kCFDateFormatterStandaloneWeekdaySymbols_VH: VarHandle by lazy { kCFDateFormatterStandaloneWeekdaySymbols_LAYOUT.varHandle() }

var kCFDateFormatterStandaloneWeekdaySymbols: MemorySegment
    get() = kCFDateFormatterStandaloneWeekdaySymbols_VH.get(kCFDateFormatterStandaloneWeekdaySymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterStandaloneWeekdaySymbols_VH.set(kCFDateFormatterStandaloneWeekdaySymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterShortStandaloneWeekdaySymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterShortStandaloneWeekdaySymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterShortStandaloneWeekdaySymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterShortStandaloneWeekdaySymbols").orElseThrow() }
private val kCFDateFormatterShortStandaloneWeekdaySymbols_VH: VarHandle by lazy { kCFDateFormatterShortStandaloneWeekdaySymbols_LAYOUT.varHandle() }

var kCFDateFormatterShortStandaloneWeekdaySymbols: MemorySegment
    get() = kCFDateFormatterShortStandaloneWeekdaySymbols_VH.get(kCFDateFormatterShortStandaloneWeekdaySymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterShortStandaloneWeekdaySymbols_VH.set(kCFDateFormatterShortStandaloneWeekdaySymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterVeryShortStandaloneWeekdaySymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterVeryShortStandaloneWeekdaySymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterVeryShortStandaloneWeekdaySymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterVeryShortStandaloneWeekdaySymbols").orElseThrow() }
private val kCFDateFormatterVeryShortStandaloneWeekdaySymbols_VH: VarHandle by lazy { kCFDateFormatterVeryShortStandaloneWeekdaySymbols_LAYOUT.varHandle() }

var kCFDateFormatterVeryShortStandaloneWeekdaySymbols: MemorySegment
    get() = kCFDateFormatterVeryShortStandaloneWeekdaySymbols_VH.get(kCFDateFormatterVeryShortStandaloneWeekdaySymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterVeryShortStandaloneWeekdaySymbols_VH.set(kCFDateFormatterVeryShortStandaloneWeekdaySymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterQuarterSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterQuarterSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterQuarterSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterQuarterSymbols").orElseThrow() }
private val kCFDateFormatterQuarterSymbols_VH: VarHandle by lazy { kCFDateFormatterQuarterSymbols_LAYOUT.varHandle() }

var kCFDateFormatterQuarterSymbols: MemorySegment
    get() = kCFDateFormatterQuarterSymbols_VH.get(kCFDateFormatterQuarterSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterQuarterSymbols_VH.set(kCFDateFormatterQuarterSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterShortQuarterSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterShortQuarterSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterShortQuarterSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterShortQuarterSymbols").orElseThrow() }
private val kCFDateFormatterShortQuarterSymbols_VH: VarHandle by lazy { kCFDateFormatterShortQuarterSymbols_LAYOUT.varHandle() }

var kCFDateFormatterShortQuarterSymbols: MemorySegment
    get() = kCFDateFormatterShortQuarterSymbols_VH.get(kCFDateFormatterShortQuarterSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterShortQuarterSymbols_VH.set(kCFDateFormatterShortQuarterSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterStandaloneQuarterSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterStandaloneQuarterSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterStandaloneQuarterSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterStandaloneQuarterSymbols").orElseThrow() }
private val kCFDateFormatterStandaloneQuarterSymbols_VH: VarHandle by lazy { kCFDateFormatterStandaloneQuarterSymbols_LAYOUT.varHandle() }

var kCFDateFormatterStandaloneQuarterSymbols: MemorySegment
    get() = kCFDateFormatterStandaloneQuarterSymbols_VH.get(kCFDateFormatterStandaloneQuarterSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterStandaloneQuarterSymbols_VH.set(kCFDateFormatterStandaloneQuarterSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterShortStandaloneQuarterSymbols typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterShortStandaloneQuarterSymbols_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterShortStandaloneQuarterSymbols_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterShortStandaloneQuarterSymbols").orElseThrow() }
private val kCFDateFormatterShortStandaloneQuarterSymbols_VH: VarHandle by lazy { kCFDateFormatterShortStandaloneQuarterSymbols_LAYOUT.varHandle() }

var kCFDateFormatterShortStandaloneQuarterSymbols: MemorySegment
    get() = kCFDateFormatterShortStandaloneQuarterSymbols_VH.get(kCFDateFormatterShortStandaloneQuarterSymbols_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterShortStandaloneQuarterSymbols_VH.set(kCFDateFormatterShortStandaloneQuarterSymbols_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterGregorianStartDate typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterGregorianStartDate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterGregorianStartDate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterGregorianStartDate").orElseThrow() }
private val kCFDateFormatterGregorianStartDate_VH: VarHandle by lazy { kCFDateFormatterGregorianStartDate_LAYOUT.varHandle() }

var kCFDateFormatterGregorianStartDate: MemorySegment
    get() = kCFDateFormatterGregorianStartDate_VH.get(kCFDateFormatterGregorianStartDate_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterGregorianStartDate_VH.set(kCFDateFormatterGregorianStartDate_SEGMENT, value)

/**
 * {@snippet lang=c : kCFDateFormatterDoesRelativeDateFormattingKey typedef const CFDateFormatterKey = (Declared(__CFString))*
 */
private val kCFDateFormatterDoesRelativeDateFormattingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFDateFormatterDoesRelativeDateFormattingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFDateFormatterDoesRelativeDateFormattingKey").orElseThrow() }
private val kCFDateFormatterDoesRelativeDateFormattingKey_VH: VarHandle by lazy { kCFDateFormatterDoesRelativeDateFormattingKey_LAYOUT.varHandle() }

var kCFDateFormatterDoesRelativeDateFormattingKey: MemorySegment
    get() = kCFDateFormatterDoesRelativeDateFormattingKey_VH.get(kCFDateFormatterDoesRelativeDateFormattingKey_SEGMENT) as MemorySegment
    set(value) = kCFDateFormatterDoesRelativeDateFormattingKey_VH.set(kCFDateFormatterDoesRelativeDateFormattingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBooleanTrue typedef const CFBooleanRef = (Declared(__CFBoolean))*
 */
private val kCFBooleanTrue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBooleanTrue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBooleanTrue").orElseThrow() }
private val kCFBooleanTrue_VH: VarHandle by lazy { kCFBooleanTrue_LAYOUT.varHandle() }

var kCFBooleanTrue: MemorySegment
    get() = kCFBooleanTrue_VH.get(kCFBooleanTrue_SEGMENT) as MemorySegment
    set(value) = kCFBooleanTrue_VH.set(kCFBooleanTrue_SEGMENT, value)

/**
 * {@snippet lang=c : kCFBooleanFalse typedef const CFBooleanRef = (Declared(__CFBoolean))*
 */
private val kCFBooleanFalse_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFBooleanFalse_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFBooleanFalse").orElseThrow() }
private val kCFBooleanFalse_VH: VarHandle by lazy { kCFBooleanFalse_LAYOUT.varHandle() }

var kCFBooleanFalse: MemorySegment
    get() = kCFBooleanFalse_VH.get(kCFBooleanFalse_SEGMENT) as MemorySegment
    set(value) = kCFBooleanFalse_VH.set(kCFBooleanFalse_SEGMENT, value)

/**
 * {@snippet lang=c : CFBooleanGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFBooleanGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFBooleanGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBooleanGetTypeID").orElseThrow()
private val CFBooleanGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBooleanGetTypeID_ADDR, CFBooleanGetTypeID_DESC)

fun CFBooleanGetTypeID(): Long {
    try {
        return CFBooleanGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFBooleanGetValue typedef Boolean = UNSIGNED = Char(typedef CFBooleanRef = (Declared(__CFBoolean))*)
 */
private val CFBooleanGetValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFBooleanGetValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFBooleanGetValue").orElseThrow()
private val CFBooleanGetValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFBooleanGetValue_ADDR, CFBooleanGetValue_DESC)

fun CFBooleanGetValue(arg0: MemorySegment): Byte {
    try {
        return CFBooleanGetValue_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFNumberPositiveInfinity typedef const CFNumberRef = (Declared(__CFNumber))*
 */
private val kCFNumberPositiveInfinity_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberPositiveInfinity_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberPositiveInfinity").orElseThrow() }
private val kCFNumberPositiveInfinity_VH: VarHandle by lazy { kCFNumberPositiveInfinity_LAYOUT.varHandle() }

var kCFNumberPositiveInfinity: MemorySegment
    get() = kCFNumberPositiveInfinity_VH.get(kCFNumberPositiveInfinity_SEGMENT) as MemorySegment
    set(value) = kCFNumberPositiveInfinity_VH.set(kCFNumberPositiveInfinity_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberNegativeInfinity typedef const CFNumberRef = (Declared(__CFNumber))*
 */
private val kCFNumberNegativeInfinity_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberNegativeInfinity_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberNegativeInfinity").orElseThrow() }
private val kCFNumberNegativeInfinity_VH: VarHandle by lazy { kCFNumberNegativeInfinity_LAYOUT.varHandle() }

var kCFNumberNegativeInfinity: MemorySegment
    get() = kCFNumberNegativeInfinity_VH.get(kCFNumberNegativeInfinity_SEGMENT) as MemorySegment
    set(value) = kCFNumberNegativeInfinity_VH.set(kCFNumberNegativeInfinity_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberNaN typedef const CFNumberRef = (Declared(__CFNumber))*
 */
private val kCFNumberNaN_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberNaN_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberNaN").orElseThrow() }
private val kCFNumberNaN_VH: VarHandle by lazy { kCFNumberNaN_LAYOUT.varHandle() }

var kCFNumberNaN: MemorySegment
    get() = kCFNumberNaN_VH.get(kCFNumberNaN_SEGMENT) as MemorySegment
    set(value) = kCFNumberNaN_VH.set(kCFNumberNaN_SEGMENT, value)

/**
 * {@snippet lang=c : CFNumberGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFNumberGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFNumberGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberGetTypeID").orElseThrow()
private val CFNumberGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberGetTypeID_ADDR, CFNumberGetTypeID_DESC)

fun CFNumberGetTypeID(): Long {
    try {
        return CFNumberGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberGetByteSize typedef CFIndex = Long(typedef CFNumberRef = (Declared(__CFNumber))*)
 */
private val CFNumberGetByteSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFNumberGetByteSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberGetByteSize").orElseThrow()
private val CFNumberGetByteSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberGetByteSize_ADDR, CFNumberGetByteSize_DESC)

fun CFNumberGetByteSize(arg0: MemorySegment): Long {
    try {
        return CFNumberGetByteSize_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberIsFloatType typedef Boolean = UNSIGNED = Char(typedef CFNumberRef = (Declared(__CFNumber))*)
 */
private val CFNumberIsFloatType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFNumberIsFloatType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberIsFloatType").orElseThrow()
private val CFNumberIsFloatType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberIsFloatType_ADDR, CFNumberIsFloatType_DESC)

fun CFNumberIsFloatType(arg0: MemorySegment): Byte {
    try {
        return CFNumberIsFloatType_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberFormatterGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFNumberFormatterGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFNumberFormatterGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberFormatterGetTypeID").orElseThrow()
private val CFNumberFormatterGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberFormatterGetTypeID_ADDR, CFNumberFormatterGetTypeID_DESC)

fun CFNumberFormatterGetTypeID(): Long {
    try {
        return CFNumberFormatterGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberFormatterGetLocale typedef CFLocaleRef = (Declared(__CFLocale))*(typedef CFNumberFormatterRef = (Declared(__CFNumberFormatter))*)
 */
private val CFNumberFormatterGetLocale_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFNumberFormatterGetLocale_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberFormatterGetLocale").orElseThrow()
private val CFNumberFormatterGetLocale_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberFormatterGetLocale_ADDR, CFNumberFormatterGetLocale_DESC)

fun CFNumberFormatterGetLocale(arg0: MemorySegment): MemorySegment {
    try {
        return CFNumberFormatterGetLocale_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberFormatterGetFormat typedef CFStringRef = (Declared(__CFString))*(typedef CFNumberFormatterRef = (Declared(__CFNumberFormatter))*)
 */
private val CFNumberFormatterGetFormat_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFNumberFormatterGetFormat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberFormatterGetFormat").orElseThrow()
private val CFNumberFormatterGetFormat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberFormatterGetFormat_ADDR, CFNumberFormatterGetFormat_DESC)

fun CFNumberFormatterGetFormat(arg0: MemorySegment): MemorySegment {
    try {
        return CFNumberFormatterGetFormat_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberFormatterSetFormat Void(typedef CFNumberFormatterRef = (Declared(__CFNumberFormatter))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFNumberFormatterSetFormat_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFNumberFormatterSetFormat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberFormatterSetFormat").orElseThrow()
private val CFNumberFormatterSetFormat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberFormatterSetFormat_ADDR, CFNumberFormatterSetFormat_DESC)

fun CFNumberFormatterSetFormat(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFNumberFormatterSetFormat_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberFormatterCreateStringWithNumber typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFNumberFormatterRef = (Declared(__CFNumberFormatter))*,typedef CFNumberRef = (Declared(__CFNumber))*)
 */
private val CFNumberFormatterCreateStringWithNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFNumberFormatterCreateStringWithNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberFormatterCreateStringWithNumber").orElseThrow()
private val CFNumberFormatterCreateStringWithNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberFormatterCreateStringWithNumber_ADDR, CFNumberFormatterCreateStringWithNumber_DESC)

fun CFNumberFormatterCreateStringWithNumber(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFNumberFormatterCreateStringWithNumber_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberFormatterCreateNumberFromString typedef CFNumberRef = (Declared(__CFNumber))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFNumberFormatterRef = (Declared(__CFNumberFormatter))*,typedef CFStringRef = (Declared(__CFString))*,(typedef CFRange = Declared(CFRange))*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFNumberFormatterCreateNumberFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFNumberFormatterCreateNumberFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberFormatterCreateNumberFromString").orElseThrow()
private val CFNumberFormatterCreateNumberFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberFormatterCreateNumberFromString_ADDR, CFNumberFormatterCreateNumberFromString_DESC)

fun CFNumberFormatterCreateNumberFromString(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: Long): MemorySegment {
    try {
        return CFNumberFormatterCreateNumberFromString_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberFormatterSetProperty Void(typedef CFNumberFormatterRef = (Declared(__CFNumberFormatter))*,typedef CFNumberFormatterKey = (Declared(__CFString))*,typedef CFTypeRef = (Void)*)
 */
private val CFNumberFormatterSetProperty_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFNumberFormatterSetProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberFormatterSetProperty").orElseThrow()
private val CFNumberFormatterSetProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberFormatterSetProperty_ADDR, CFNumberFormatterSetProperty_DESC)

fun CFNumberFormatterSetProperty(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFNumberFormatterSetProperty_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFNumberFormatterCopyProperty typedef CFTypeRef = (Void)*(typedef CFNumberFormatterRef = (Declared(__CFNumberFormatter))*,typedef CFNumberFormatterKey = (Declared(__CFString))*)
 */
private val CFNumberFormatterCopyProperty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFNumberFormatterCopyProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberFormatterCopyProperty").orElseThrow()
private val CFNumberFormatterCopyProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberFormatterCopyProperty_ADDR, CFNumberFormatterCopyProperty_DESC)

fun CFNumberFormatterCopyProperty(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFNumberFormatterCopyProperty_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFNumberFormatterCurrencyCode typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterCurrencyCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterCurrencyCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterCurrencyCode").orElseThrow() }
private val kCFNumberFormatterCurrencyCode_VH: VarHandle by lazy { kCFNumberFormatterCurrencyCode_LAYOUT.varHandle() }

var kCFNumberFormatterCurrencyCode: MemorySegment
    get() = kCFNumberFormatterCurrencyCode_VH.get(kCFNumberFormatterCurrencyCode_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterCurrencyCode_VH.set(kCFNumberFormatterCurrencyCode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterDecimalSeparator typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterDecimalSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterDecimalSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterDecimalSeparator").orElseThrow() }
private val kCFNumberFormatterDecimalSeparator_VH: VarHandle by lazy { kCFNumberFormatterDecimalSeparator_LAYOUT.varHandle() }

var kCFNumberFormatterDecimalSeparator: MemorySegment
    get() = kCFNumberFormatterDecimalSeparator_VH.get(kCFNumberFormatterDecimalSeparator_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterDecimalSeparator_VH.set(kCFNumberFormatterDecimalSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterCurrencyDecimalSeparator typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterCurrencyDecimalSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterCurrencyDecimalSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterCurrencyDecimalSeparator").orElseThrow() }
private val kCFNumberFormatterCurrencyDecimalSeparator_VH: VarHandle by lazy { kCFNumberFormatterCurrencyDecimalSeparator_LAYOUT.varHandle() }

var kCFNumberFormatterCurrencyDecimalSeparator: MemorySegment
    get() = kCFNumberFormatterCurrencyDecimalSeparator_VH.get(kCFNumberFormatterCurrencyDecimalSeparator_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterCurrencyDecimalSeparator_VH.set(kCFNumberFormatterCurrencyDecimalSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterAlwaysShowDecimalSeparator typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterAlwaysShowDecimalSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterAlwaysShowDecimalSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterAlwaysShowDecimalSeparator").orElseThrow() }
private val kCFNumberFormatterAlwaysShowDecimalSeparator_VH: VarHandle by lazy { kCFNumberFormatterAlwaysShowDecimalSeparator_LAYOUT.varHandle() }

var kCFNumberFormatterAlwaysShowDecimalSeparator: MemorySegment
    get() = kCFNumberFormatterAlwaysShowDecimalSeparator_VH.get(kCFNumberFormatterAlwaysShowDecimalSeparator_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterAlwaysShowDecimalSeparator_VH.set(kCFNumberFormatterAlwaysShowDecimalSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterGroupingSeparator typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterGroupingSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterGroupingSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterGroupingSeparator").orElseThrow() }
private val kCFNumberFormatterGroupingSeparator_VH: VarHandle by lazy { kCFNumberFormatterGroupingSeparator_LAYOUT.varHandle() }

var kCFNumberFormatterGroupingSeparator: MemorySegment
    get() = kCFNumberFormatterGroupingSeparator_VH.get(kCFNumberFormatterGroupingSeparator_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterGroupingSeparator_VH.set(kCFNumberFormatterGroupingSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterUseGroupingSeparator typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterUseGroupingSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterUseGroupingSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterUseGroupingSeparator").orElseThrow() }
private val kCFNumberFormatterUseGroupingSeparator_VH: VarHandle by lazy { kCFNumberFormatterUseGroupingSeparator_LAYOUT.varHandle() }

var kCFNumberFormatterUseGroupingSeparator: MemorySegment
    get() = kCFNumberFormatterUseGroupingSeparator_VH.get(kCFNumberFormatterUseGroupingSeparator_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterUseGroupingSeparator_VH.set(kCFNumberFormatterUseGroupingSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterPercentSymbol typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterPercentSymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterPercentSymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterPercentSymbol").orElseThrow() }
private val kCFNumberFormatterPercentSymbol_VH: VarHandle by lazy { kCFNumberFormatterPercentSymbol_LAYOUT.varHandle() }

var kCFNumberFormatterPercentSymbol: MemorySegment
    get() = kCFNumberFormatterPercentSymbol_VH.get(kCFNumberFormatterPercentSymbol_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterPercentSymbol_VH.set(kCFNumberFormatterPercentSymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterZeroSymbol typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterZeroSymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterZeroSymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterZeroSymbol").orElseThrow() }
private val kCFNumberFormatterZeroSymbol_VH: VarHandle by lazy { kCFNumberFormatterZeroSymbol_LAYOUT.varHandle() }

var kCFNumberFormatterZeroSymbol: MemorySegment
    get() = kCFNumberFormatterZeroSymbol_VH.get(kCFNumberFormatterZeroSymbol_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterZeroSymbol_VH.set(kCFNumberFormatterZeroSymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterNaNSymbol typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterNaNSymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterNaNSymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterNaNSymbol").orElseThrow() }
private val kCFNumberFormatterNaNSymbol_VH: VarHandle by lazy { kCFNumberFormatterNaNSymbol_LAYOUT.varHandle() }

var kCFNumberFormatterNaNSymbol: MemorySegment
    get() = kCFNumberFormatterNaNSymbol_VH.get(kCFNumberFormatterNaNSymbol_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterNaNSymbol_VH.set(kCFNumberFormatterNaNSymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterInfinitySymbol typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterInfinitySymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterInfinitySymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterInfinitySymbol").orElseThrow() }
private val kCFNumberFormatterInfinitySymbol_VH: VarHandle by lazy { kCFNumberFormatterInfinitySymbol_LAYOUT.varHandle() }

var kCFNumberFormatterInfinitySymbol: MemorySegment
    get() = kCFNumberFormatterInfinitySymbol_VH.get(kCFNumberFormatterInfinitySymbol_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterInfinitySymbol_VH.set(kCFNumberFormatterInfinitySymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterMinusSign typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterMinusSign_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterMinusSign_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterMinusSign").orElseThrow() }
private val kCFNumberFormatterMinusSign_VH: VarHandle by lazy { kCFNumberFormatterMinusSign_LAYOUT.varHandle() }

var kCFNumberFormatterMinusSign: MemorySegment
    get() = kCFNumberFormatterMinusSign_VH.get(kCFNumberFormatterMinusSign_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterMinusSign_VH.set(kCFNumberFormatterMinusSign_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterPlusSign typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterPlusSign_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterPlusSign_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterPlusSign").orElseThrow() }
private val kCFNumberFormatterPlusSign_VH: VarHandle by lazy { kCFNumberFormatterPlusSign_LAYOUT.varHandle() }

var kCFNumberFormatterPlusSign: MemorySegment
    get() = kCFNumberFormatterPlusSign_VH.get(kCFNumberFormatterPlusSign_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterPlusSign_VH.set(kCFNumberFormatterPlusSign_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterCurrencySymbol typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterCurrencySymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterCurrencySymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterCurrencySymbol").orElseThrow() }
private val kCFNumberFormatterCurrencySymbol_VH: VarHandle by lazy { kCFNumberFormatterCurrencySymbol_LAYOUT.varHandle() }

var kCFNumberFormatterCurrencySymbol: MemorySegment
    get() = kCFNumberFormatterCurrencySymbol_VH.get(kCFNumberFormatterCurrencySymbol_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterCurrencySymbol_VH.set(kCFNumberFormatterCurrencySymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterExponentSymbol typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterExponentSymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterExponentSymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterExponentSymbol").orElseThrow() }
private val kCFNumberFormatterExponentSymbol_VH: VarHandle by lazy { kCFNumberFormatterExponentSymbol_LAYOUT.varHandle() }

var kCFNumberFormatterExponentSymbol: MemorySegment
    get() = kCFNumberFormatterExponentSymbol_VH.get(kCFNumberFormatterExponentSymbol_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterExponentSymbol_VH.set(kCFNumberFormatterExponentSymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterMinIntegerDigits typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterMinIntegerDigits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterMinIntegerDigits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterMinIntegerDigits").orElseThrow() }
private val kCFNumberFormatterMinIntegerDigits_VH: VarHandle by lazy { kCFNumberFormatterMinIntegerDigits_LAYOUT.varHandle() }

var kCFNumberFormatterMinIntegerDigits: MemorySegment
    get() = kCFNumberFormatterMinIntegerDigits_VH.get(kCFNumberFormatterMinIntegerDigits_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterMinIntegerDigits_VH.set(kCFNumberFormatterMinIntegerDigits_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterMaxIntegerDigits typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterMaxIntegerDigits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterMaxIntegerDigits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterMaxIntegerDigits").orElseThrow() }
private val kCFNumberFormatterMaxIntegerDigits_VH: VarHandle by lazy { kCFNumberFormatterMaxIntegerDigits_LAYOUT.varHandle() }

var kCFNumberFormatterMaxIntegerDigits: MemorySegment
    get() = kCFNumberFormatterMaxIntegerDigits_VH.get(kCFNumberFormatterMaxIntegerDigits_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterMaxIntegerDigits_VH.set(kCFNumberFormatterMaxIntegerDigits_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterMinFractionDigits typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterMinFractionDigits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterMinFractionDigits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterMinFractionDigits").orElseThrow() }
private val kCFNumberFormatterMinFractionDigits_VH: VarHandle by lazy { kCFNumberFormatterMinFractionDigits_LAYOUT.varHandle() }

var kCFNumberFormatterMinFractionDigits: MemorySegment
    get() = kCFNumberFormatterMinFractionDigits_VH.get(kCFNumberFormatterMinFractionDigits_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterMinFractionDigits_VH.set(kCFNumberFormatterMinFractionDigits_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterMaxFractionDigits typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterMaxFractionDigits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterMaxFractionDigits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterMaxFractionDigits").orElseThrow() }
private val kCFNumberFormatterMaxFractionDigits_VH: VarHandle by lazy { kCFNumberFormatterMaxFractionDigits_LAYOUT.varHandle() }

var kCFNumberFormatterMaxFractionDigits: MemorySegment
    get() = kCFNumberFormatterMaxFractionDigits_VH.get(kCFNumberFormatterMaxFractionDigits_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterMaxFractionDigits_VH.set(kCFNumberFormatterMaxFractionDigits_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterGroupingSize typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterGroupingSize_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterGroupingSize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterGroupingSize").orElseThrow() }
private val kCFNumberFormatterGroupingSize_VH: VarHandle by lazy { kCFNumberFormatterGroupingSize_LAYOUT.varHandle() }

var kCFNumberFormatterGroupingSize: MemorySegment
    get() = kCFNumberFormatterGroupingSize_VH.get(kCFNumberFormatterGroupingSize_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterGroupingSize_VH.set(kCFNumberFormatterGroupingSize_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterSecondaryGroupingSize typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterSecondaryGroupingSize_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterSecondaryGroupingSize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterSecondaryGroupingSize").orElseThrow() }
private val kCFNumberFormatterSecondaryGroupingSize_VH: VarHandle by lazy { kCFNumberFormatterSecondaryGroupingSize_LAYOUT.varHandle() }

var kCFNumberFormatterSecondaryGroupingSize: MemorySegment
    get() = kCFNumberFormatterSecondaryGroupingSize_VH.get(kCFNumberFormatterSecondaryGroupingSize_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterSecondaryGroupingSize_VH.set(kCFNumberFormatterSecondaryGroupingSize_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterRoundingMode typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterRoundingMode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterRoundingMode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterRoundingMode").orElseThrow() }
private val kCFNumberFormatterRoundingMode_VH: VarHandle by lazy { kCFNumberFormatterRoundingMode_LAYOUT.varHandle() }

var kCFNumberFormatterRoundingMode: MemorySegment
    get() = kCFNumberFormatterRoundingMode_VH.get(kCFNumberFormatterRoundingMode_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterRoundingMode_VH.set(kCFNumberFormatterRoundingMode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterRoundingIncrement typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterRoundingIncrement_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterRoundingIncrement_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterRoundingIncrement").orElseThrow() }
private val kCFNumberFormatterRoundingIncrement_VH: VarHandle by lazy { kCFNumberFormatterRoundingIncrement_LAYOUT.varHandle() }

var kCFNumberFormatterRoundingIncrement: MemorySegment
    get() = kCFNumberFormatterRoundingIncrement_VH.get(kCFNumberFormatterRoundingIncrement_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterRoundingIncrement_VH.set(kCFNumberFormatterRoundingIncrement_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterFormatWidth typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterFormatWidth_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterFormatWidth_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterFormatWidth").orElseThrow() }
private val kCFNumberFormatterFormatWidth_VH: VarHandle by lazy { kCFNumberFormatterFormatWidth_LAYOUT.varHandle() }

var kCFNumberFormatterFormatWidth: MemorySegment
    get() = kCFNumberFormatterFormatWidth_VH.get(kCFNumberFormatterFormatWidth_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterFormatWidth_VH.set(kCFNumberFormatterFormatWidth_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterPaddingPosition typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterPaddingPosition_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterPaddingPosition_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterPaddingPosition").orElseThrow() }
private val kCFNumberFormatterPaddingPosition_VH: VarHandle by lazy { kCFNumberFormatterPaddingPosition_LAYOUT.varHandle() }

var kCFNumberFormatterPaddingPosition: MemorySegment
    get() = kCFNumberFormatterPaddingPosition_VH.get(kCFNumberFormatterPaddingPosition_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterPaddingPosition_VH.set(kCFNumberFormatterPaddingPosition_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterPaddingCharacter typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterPaddingCharacter_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterPaddingCharacter_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterPaddingCharacter").orElseThrow() }
private val kCFNumberFormatterPaddingCharacter_VH: VarHandle by lazy { kCFNumberFormatterPaddingCharacter_LAYOUT.varHandle() }

var kCFNumberFormatterPaddingCharacter: MemorySegment
    get() = kCFNumberFormatterPaddingCharacter_VH.get(kCFNumberFormatterPaddingCharacter_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterPaddingCharacter_VH.set(kCFNumberFormatterPaddingCharacter_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterDefaultFormat typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterDefaultFormat_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterDefaultFormat_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterDefaultFormat").orElseThrow() }
private val kCFNumberFormatterDefaultFormat_VH: VarHandle by lazy { kCFNumberFormatterDefaultFormat_LAYOUT.varHandle() }

var kCFNumberFormatterDefaultFormat: MemorySegment
    get() = kCFNumberFormatterDefaultFormat_VH.get(kCFNumberFormatterDefaultFormat_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterDefaultFormat_VH.set(kCFNumberFormatterDefaultFormat_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterMultiplier typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterMultiplier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterMultiplier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterMultiplier").orElseThrow() }
private val kCFNumberFormatterMultiplier_VH: VarHandle by lazy { kCFNumberFormatterMultiplier_LAYOUT.varHandle() }

var kCFNumberFormatterMultiplier: MemorySegment
    get() = kCFNumberFormatterMultiplier_VH.get(kCFNumberFormatterMultiplier_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterMultiplier_VH.set(kCFNumberFormatterMultiplier_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterPositivePrefix typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterPositivePrefix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterPositivePrefix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterPositivePrefix").orElseThrow() }
private val kCFNumberFormatterPositivePrefix_VH: VarHandle by lazy { kCFNumberFormatterPositivePrefix_LAYOUT.varHandle() }

var kCFNumberFormatterPositivePrefix: MemorySegment
    get() = kCFNumberFormatterPositivePrefix_VH.get(kCFNumberFormatterPositivePrefix_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterPositivePrefix_VH.set(kCFNumberFormatterPositivePrefix_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterPositiveSuffix typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterPositiveSuffix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterPositiveSuffix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterPositiveSuffix").orElseThrow() }
private val kCFNumberFormatterPositiveSuffix_VH: VarHandle by lazy { kCFNumberFormatterPositiveSuffix_LAYOUT.varHandle() }

var kCFNumberFormatterPositiveSuffix: MemorySegment
    get() = kCFNumberFormatterPositiveSuffix_VH.get(kCFNumberFormatterPositiveSuffix_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterPositiveSuffix_VH.set(kCFNumberFormatterPositiveSuffix_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterNegativePrefix typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterNegativePrefix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterNegativePrefix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterNegativePrefix").orElseThrow() }
private val kCFNumberFormatterNegativePrefix_VH: VarHandle by lazy { kCFNumberFormatterNegativePrefix_LAYOUT.varHandle() }

var kCFNumberFormatterNegativePrefix: MemorySegment
    get() = kCFNumberFormatterNegativePrefix_VH.get(kCFNumberFormatterNegativePrefix_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterNegativePrefix_VH.set(kCFNumberFormatterNegativePrefix_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterNegativeSuffix typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterNegativeSuffix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterNegativeSuffix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterNegativeSuffix").orElseThrow() }
private val kCFNumberFormatterNegativeSuffix_VH: VarHandle by lazy { kCFNumberFormatterNegativeSuffix_LAYOUT.varHandle() }

var kCFNumberFormatterNegativeSuffix: MemorySegment
    get() = kCFNumberFormatterNegativeSuffix_VH.get(kCFNumberFormatterNegativeSuffix_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterNegativeSuffix_VH.set(kCFNumberFormatterNegativeSuffix_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterPerMillSymbol typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterPerMillSymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterPerMillSymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterPerMillSymbol").orElseThrow() }
private val kCFNumberFormatterPerMillSymbol_VH: VarHandle by lazy { kCFNumberFormatterPerMillSymbol_LAYOUT.varHandle() }

var kCFNumberFormatterPerMillSymbol: MemorySegment
    get() = kCFNumberFormatterPerMillSymbol_VH.get(kCFNumberFormatterPerMillSymbol_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterPerMillSymbol_VH.set(kCFNumberFormatterPerMillSymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterInternationalCurrencySymbol typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterInternationalCurrencySymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterInternationalCurrencySymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterInternationalCurrencySymbol").orElseThrow() }
private val kCFNumberFormatterInternationalCurrencySymbol_VH: VarHandle by lazy { kCFNumberFormatterInternationalCurrencySymbol_LAYOUT.varHandle() }

var kCFNumberFormatterInternationalCurrencySymbol: MemorySegment
    get() = kCFNumberFormatterInternationalCurrencySymbol_VH.get(kCFNumberFormatterInternationalCurrencySymbol_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterInternationalCurrencySymbol_VH.set(kCFNumberFormatterInternationalCurrencySymbol_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterCurrencyGroupingSeparator typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterCurrencyGroupingSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterCurrencyGroupingSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterCurrencyGroupingSeparator").orElseThrow() }
private val kCFNumberFormatterCurrencyGroupingSeparator_VH: VarHandle by lazy { kCFNumberFormatterCurrencyGroupingSeparator_LAYOUT.varHandle() }

var kCFNumberFormatterCurrencyGroupingSeparator: MemorySegment
    get() = kCFNumberFormatterCurrencyGroupingSeparator_VH.get(kCFNumberFormatterCurrencyGroupingSeparator_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterCurrencyGroupingSeparator_VH.set(kCFNumberFormatterCurrencyGroupingSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterIsLenient typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterIsLenient_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterIsLenient_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterIsLenient").orElseThrow() }
private val kCFNumberFormatterIsLenient_VH: VarHandle by lazy { kCFNumberFormatterIsLenient_LAYOUT.varHandle() }

var kCFNumberFormatterIsLenient: MemorySegment
    get() = kCFNumberFormatterIsLenient_VH.get(kCFNumberFormatterIsLenient_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterIsLenient_VH.set(kCFNumberFormatterIsLenient_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterUseSignificantDigits typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterUseSignificantDigits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterUseSignificantDigits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterUseSignificantDigits").orElseThrow() }
private val kCFNumberFormatterUseSignificantDigits_VH: VarHandle by lazy { kCFNumberFormatterUseSignificantDigits_LAYOUT.varHandle() }

var kCFNumberFormatterUseSignificantDigits: MemorySegment
    get() = kCFNumberFormatterUseSignificantDigits_VH.get(kCFNumberFormatterUseSignificantDigits_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterUseSignificantDigits_VH.set(kCFNumberFormatterUseSignificantDigits_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterMinSignificantDigits typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterMinSignificantDigits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterMinSignificantDigits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterMinSignificantDigits").orElseThrow() }
private val kCFNumberFormatterMinSignificantDigits_VH: VarHandle by lazy { kCFNumberFormatterMinSignificantDigits_LAYOUT.varHandle() }

var kCFNumberFormatterMinSignificantDigits: MemorySegment
    get() = kCFNumberFormatterMinSignificantDigits_VH.get(kCFNumberFormatterMinSignificantDigits_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterMinSignificantDigits_VH.set(kCFNumberFormatterMinSignificantDigits_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterMaxSignificantDigits typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterMaxSignificantDigits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterMaxSignificantDigits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterMaxSignificantDigits").orElseThrow() }
private val kCFNumberFormatterMaxSignificantDigits_VH: VarHandle by lazy { kCFNumberFormatterMaxSignificantDigits_LAYOUT.varHandle() }

var kCFNumberFormatterMaxSignificantDigits: MemorySegment
    get() = kCFNumberFormatterMaxSignificantDigits_VH.get(kCFNumberFormatterMaxSignificantDigits_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterMaxSignificantDigits_VH.set(kCFNumberFormatterMaxSignificantDigits_SEGMENT, value)

/**
 * {@snippet lang=c : kCFNumberFormatterMinGroupingDigits typedef const CFNumberFormatterKey = (Declared(__CFString))*
 */
private val kCFNumberFormatterMinGroupingDigits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFNumberFormatterMinGroupingDigits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFNumberFormatterMinGroupingDigits").orElseThrow() }
private val kCFNumberFormatterMinGroupingDigits_VH: VarHandle by lazy { kCFNumberFormatterMinGroupingDigits_LAYOUT.varHandle() }

var kCFNumberFormatterMinGroupingDigits: MemorySegment
    get() = kCFNumberFormatterMinGroupingDigits_VH.get(kCFNumberFormatterMinGroupingDigits_SEGMENT) as MemorySegment
    set(value) = kCFNumberFormatterMinGroupingDigits_VH.set(kCFNumberFormatterMinGroupingDigits_SEGMENT, value)

/**
 * {@snippet lang=c : CFNumberFormatterGetDecimalInfoForCurrencyCode typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*,(typedef int32_t = Int)*,(Double)*)
 */
private val CFNumberFormatterGetDecimalInfoForCurrencyCode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFNumberFormatterGetDecimalInfoForCurrencyCode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFNumberFormatterGetDecimalInfoForCurrencyCode").orElseThrow()
private val CFNumberFormatterGetDecimalInfoForCurrencyCode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFNumberFormatterGetDecimalInfoForCurrencyCode_ADDR, CFNumberFormatterGetDecimalInfoForCurrencyCode_DESC)

fun CFNumberFormatterGetDecimalInfoForCurrencyCode(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFNumberFormatterGetDecimalInfoForCurrencyCode_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFPreferencesAnyApplication typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPreferencesAnyApplication_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPreferencesAnyApplication_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPreferencesAnyApplication").orElseThrow() }
private val kCFPreferencesAnyApplication_VH: VarHandle by lazy { kCFPreferencesAnyApplication_LAYOUT.varHandle() }

var kCFPreferencesAnyApplication: MemorySegment
    get() = kCFPreferencesAnyApplication_VH.get(kCFPreferencesAnyApplication_SEGMENT) as MemorySegment
    set(value) = kCFPreferencesAnyApplication_VH.set(kCFPreferencesAnyApplication_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPreferencesCurrentApplication typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPreferencesCurrentApplication_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPreferencesCurrentApplication_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPreferencesCurrentApplication").orElseThrow() }
private val kCFPreferencesCurrentApplication_VH: VarHandle by lazy { kCFPreferencesCurrentApplication_LAYOUT.varHandle() }

var kCFPreferencesCurrentApplication: MemorySegment
    get() = kCFPreferencesCurrentApplication_VH.get(kCFPreferencesCurrentApplication_SEGMENT) as MemorySegment
    set(value) = kCFPreferencesCurrentApplication_VH.set(kCFPreferencesCurrentApplication_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPreferencesAnyHost typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPreferencesAnyHost_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPreferencesAnyHost_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPreferencesAnyHost").orElseThrow() }
private val kCFPreferencesAnyHost_VH: VarHandle by lazy { kCFPreferencesAnyHost_LAYOUT.varHandle() }

var kCFPreferencesAnyHost: MemorySegment
    get() = kCFPreferencesAnyHost_VH.get(kCFPreferencesAnyHost_SEGMENT) as MemorySegment
    set(value) = kCFPreferencesAnyHost_VH.set(kCFPreferencesAnyHost_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPreferencesCurrentHost typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPreferencesCurrentHost_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPreferencesCurrentHost_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPreferencesCurrentHost").orElseThrow() }
private val kCFPreferencesCurrentHost_VH: VarHandle by lazy { kCFPreferencesCurrentHost_LAYOUT.varHandle() }

var kCFPreferencesCurrentHost: MemorySegment
    get() = kCFPreferencesCurrentHost_VH.get(kCFPreferencesCurrentHost_SEGMENT) as MemorySegment
    set(value) = kCFPreferencesCurrentHost_VH.set(kCFPreferencesCurrentHost_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPreferencesAnyUser typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPreferencesAnyUser_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPreferencesAnyUser_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPreferencesAnyUser").orElseThrow() }
private val kCFPreferencesAnyUser_VH: VarHandle by lazy { kCFPreferencesAnyUser_LAYOUT.varHandle() }

var kCFPreferencesAnyUser: MemorySegment
    get() = kCFPreferencesAnyUser_VH.get(kCFPreferencesAnyUser_SEGMENT) as MemorySegment
    set(value) = kCFPreferencesAnyUser_VH.set(kCFPreferencesAnyUser_SEGMENT, value)

/**
 * {@snippet lang=c : kCFPreferencesCurrentUser typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFPreferencesCurrentUser_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFPreferencesCurrentUser_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFPreferencesCurrentUser").orElseThrow() }
private val kCFPreferencesCurrentUser_VH: VarHandle by lazy { kCFPreferencesCurrentUser_LAYOUT.varHandle() }

var kCFPreferencesCurrentUser: MemorySegment
    get() = kCFPreferencesCurrentUser_VH.get(kCFPreferencesCurrentUser_SEGMENT) as MemorySegment
    set(value) = kCFPreferencesCurrentUser_VH.set(kCFPreferencesCurrentUser_SEGMENT, value)

/**
 * {@snippet lang=c : CFPreferencesCopyAppValue typedef CFPropertyListRef = (Void)*(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesCopyAppValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesCopyAppValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesCopyAppValue").orElseThrow()
private val CFPreferencesCopyAppValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesCopyAppValue_ADDR, CFPreferencesCopyAppValue_DESC)

fun CFPreferencesCopyAppValue(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFPreferencesCopyAppValue_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesGetAppBooleanValue typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,(typedef Boolean = UNSIGNED = Char)*)
 */
private val CFPreferencesGetAppBooleanValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesGetAppBooleanValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesGetAppBooleanValue").orElseThrow()
private val CFPreferencesGetAppBooleanValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesGetAppBooleanValue_ADDR, CFPreferencesGetAppBooleanValue_DESC)

fun CFPreferencesGetAppBooleanValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFPreferencesGetAppBooleanValue_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesGetAppIntegerValue typedef CFIndex = Long(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,(typedef Boolean = UNSIGNED = Char)*)
 */
private val CFPreferencesGetAppIntegerValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesGetAppIntegerValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesGetAppIntegerValue").orElseThrow()
private val CFPreferencesGetAppIntegerValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesGetAppIntegerValue_ADDR, CFPreferencesGetAppIntegerValue_DESC)

fun CFPreferencesGetAppIntegerValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Long {
    try {
        return CFPreferencesGetAppIntegerValue_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesSetAppValue Void(typedef CFStringRef = (Declared(__CFString))*,typedef CFPropertyListRef = (Void)*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesSetAppValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesSetAppValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesSetAppValue").orElseThrow()
private val CFPreferencesSetAppValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesSetAppValue_ADDR, CFPreferencesSetAppValue_DESC)

fun CFPreferencesSetAppValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFPreferencesSetAppValue_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesAddSuitePreferencesToApp Void(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesAddSuitePreferencesToApp_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesAddSuitePreferencesToApp_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesAddSuitePreferencesToApp").orElseThrow()
private val CFPreferencesAddSuitePreferencesToApp_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesAddSuitePreferencesToApp_ADDR, CFPreferencesAddSuitePreferencesToApp_DESC)

fun CFPreferencesAddSuitePreferencesToApp(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFPreferencesAddSuitePreferencesToApp_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesRemoveSuitePreferencesFromApp Void(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesRemoveSuitePreferencesFromApp_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesRemoveSuitePreferencesFromApp_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesRemoveSuitePreferencesFromApp").orElseThrow()
private val CFPreferencesRemoveSuitePreferencesFromApp_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesRemoveSuitePreferencesFromApp_ADDR, CFPreferencesRemoveSuitePreferencesFromApp_DESC)

fun CFPreferencesRemoveSuitePreferencesFromApp(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFPreferencesRemoveSuitePreferencesFromApp_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesAppSynchronize typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesAppSynchronize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFPreferencesAppSynchronize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesAppSynchronize").orElseThrow()
private val CFPreferencesAppSynchronize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesAppSynchronize_ADDR, CFPreferencesAppSynchronize_DESC)

fun CFPreferencesAppSynchronize(arg0: MemorySegment): Byte {
    try {
        return CFPreferencesAppSynchronize_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesCopyValue typedef CFPropertyListRef = (Void)*(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesCopyValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesCopyValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesCopyValue").orElseThrow()
private val CFPreferencesCopyValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesCopyValue_ADDR, CFPreferencesCopyValue_DESC)

fun CFPreferencesCopyValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFPreferencesCopyValue_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesCopyMultiple typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFArrayRef = (Declared(__CFArray))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesCopyMultiple_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesCopyMultiple_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesCopyMultiple").orElseThrow()
private val CFPreferencesCopyMultiple_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesCopyMultiple_ADDR, CFPreferencesCopyMultiple_DESC)

fun CFPreferencesCopyMultiple(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFPreferencesCopyMultiple_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesSetValue Void(typedef CFStringRef = (Declared(__CFString))*,typedef CFPropertyListRef = (Void)*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesSetValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesSetValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesSetValue").orElseThrow()
private val CFPreferencesSetValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesSetValue_ADDR, CFPreferencesSetValue_DESC)

fun CFPreferencesSetValue(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): Unit {
    try {
        CFPreferencesSetValue_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesSetMultiple Void(typedef CFDictionaryRef = (Declared(__CFDictionary))*,typedef CFArrayRef = (Declared(__CFArray))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesSetMultiple_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesSetMultiple_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesSetMultiple").orElseThrow()
private val CFPreferencesSetMultiple_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesSetMultiple_ADDR, CFPreferencesSetMultiple_DESC)

fun CFPreferencesSetMultiple(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): Unit {
    try {
        CFPreferencesSetMultiple_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesSynchronize typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesSynchronize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesSynchronize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesSynchronize").orElseThrow()
private val CFPreferencesSynchronize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesSynchronize_ADDR, CFPreferencesSynchronize_DESC)

fun CFPreferencesSynchronize(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFPreferencesSynchronize_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesCopyApplicationList typedef CFArrayRef = (Declared(__CFArray))*(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesCopyApplicationList_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesCopyApplicationList_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesCopyApplicationList").orElseThrow()
private val CFPreferencesCopyApplicationList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesCopyApplicationList_ADDR, CFPreferencesCopyApplicationList_DESC)

fun CFPreferencesCopyApplicationList(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFPreferencesCopyApplicationList_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesCopyKeyList typedef CFArrayRef = (Declared(__CFArray))*(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesCopyKeyList_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesCopyKeyList_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesCopyKeyList").orElseThrow()
private val CFPreferencesCopyKeyList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesCopyKeyList_ADDR, CFPreferencesCopyKeyList_DESC)

fun CFPreferencesCopyKeyList(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFPreferencesCopyKeyList_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPreferencesAppValueIsForced typedef Boolean = UNSIGNED = Char(typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFPreferencesAppValueIsForced_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPreferencesAppValueIsForced_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPreferencesAppValueIsForced").orElseThrow()
private val CFPreferencesAppValueIsForced_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPreferencesAppValueIsForced_ADDR, CFPreferencesAppValueIsForced_DESC)

fun CFPreferencesAppValueIsForced(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFPreferencesAppValueIsForced_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFURLGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFURLGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLGetTypeID").orElseThrow()
private val CFURLGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLGetTypeID_ADDR, CFURLGetTypeID_DESC)

fun CFURLGetTypeID(): Long {
    try {
        return CFURLGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateWithBytes typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long,typedef CFStringEncoding = UNSIGNED = Int,typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCreateWithBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFURLCreateWithBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateWithBytes").orElseThrow()
private val CFURLCreateWithBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateWithBytes_ADDR, CFURLCreateWithBytes_DESC)

fun CFURLCreateWithBytes(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Int, arg4: MemorySegment): MemorySegment {
    try {
        return CFURLCreateWithBytes_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateData typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringEncoding = UNSIGNED = Int,typedef Boolean = UNSIGNED = Char)
 */
private val CFURLCreateData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_BYTE)
private val CFURLCreateData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateData").orElseThrow()
private val CFURLCreateData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateData_ADDR, CFURLCreateData_DESC)

fun CFURLCreateData(arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: Byte): MemorySegment {
    try {
        return CFURLCreateData_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateWithString typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCreateWithString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateWithString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateWithString").orElseThrow()
private val CFURLCreateWithString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateWithString_ADDR, CFURLCreateWithString_DESC)

fun CFURLCreateWithString(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFURLCreateWithString_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateAbsoluteURLWithBytes typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long,typedef CFStringEncoding = UNSIGNED = Int,typedef CFURLRef = (Declared(__CFURL))*,typedef Boolean = UNSIGNED = Char)
 */
private val CFURLCreateAbsoluteURLWithBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE)
private val CFURLCreateAbsoluteURLWithBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateAbsoluteURLWithBytes").orElseThrow()
private val CFURLCreateAbsoluteURLWithBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateAbsoluteURLWithBytes_ADDR, CFURLCreateAbsoluteURLWithBytes_DESC)

fun CFURLCreateAbsoluteURLWithBytes(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Int, arg4: MemorySegment, arg5: Byte): MemorySegment {
    try {
        return CFURLCreateAbsoluteURLWithBytes_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateFromFileSystemRepresentation typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long,typedef Boolean = UNSIGNED = Char)
 */
private val CFURLCreateFromFileSystemRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_BYTE)
private val CFURLCreateFromFileSystemRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateFromFileSystemRepresentation").orElseThrow()
private val CFURLCreateFromFileSystemRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateFromFileSystemRepresentation_ADDR, CFURLCreateFromFileSystemRepresentation_DESC)

fun CFURLCreateFromFileSystemRepresentation(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Byte): MemorySegment {
    try {
        return CFURLCreateFromFileSystemRepresentation_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateFromFileSystemRepresentationRelativeToBase typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long,typedef Boolean = UNSIGNED = Char,typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCreateFromFileSystemRepresentationRelativeToBase_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFURLCreateFromFileSystemRepresentationRelativeToBase_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateFromFileSystemRepresentationRelativeToBase").orElseThrow()
private val CFURLCreateFromFileSystemRepresentationRelativeToBase_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateFromFileSystemRepresentationRelativeToBase_ADDR, CFURLCreateFromFileSystemRepresentationRelativeToBase_DESC)

fun CFURLCreateFromFileSystemRepresentationRelativeToBase(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Byte, arg4: MemorySegment): MemorySegment {
    try {
        return CFURLCreateFromFileSystemRepresentationRelativeToBase_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLGetFileSystemRepresentation typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*,typedef Boolean = UNSIGNED = Char,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long)
 */
private val CFURLGetFileSystemRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFURLGetFileSystemRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLGetFileSystemRepresentation").orElseThrow()
private val CFURLGetFileSystemRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLGetFileSystemRepresentation_ADDR, CFURLGetFileSystemRepresentation_DESC)

fun CFURLGetFileSystemRepresentation(arg0: MemorySegment, arg1: Byte, arg2: MemorySegment, arg3: Long): Byte {
    try {
        return CFURLGetFileSystemRepresentation_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyAbsoluteURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyAbsoluteURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyAbsoluteURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyAbsoluteURL").orElseThrow()
private val CFURLCopyAbsoluteURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyAbsoluteURL_ADDR, CFURLCopyAbsoluteURL_DESC)

fun CFURLCopyAbsoluteURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyAbsoluteURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLGetString typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLGetString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLGetString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLGetString").orElseThrow()
private val CFURLGetString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLGetString_ADDR, CFURLGetString_DESC)

fun CFURLGetString(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLGetString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLGetBaseURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLGetBaseURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLGetBaseURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLGetBaseURL").orElseThrow()
private val CFURLGetBaseURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLGetBaseURL_ADDR, CFURLGetBaseURL_DESC)

fun CFURLGetBaseURL(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLGetBaseURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCanBeDecomposed typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCanBeDecomposed_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFURLCanBeDecomposed_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCanBeDecomposed").orElseThrow()
private val CFURLCanBeDecomposed_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCanBeDecomposed_ADDR, CFURLCanBeDecomposed_DESC)

fun CFURLCanBeDecomposed(arg0: MemorySegment): Byte {
    try {
        return CFURLCanBeDecomposed_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyScheme typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyScheme_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyScheme_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyScheme").orElseThrow()
private val CFURLCopyScheme_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyScheme_ADDR, CFURLCopyScheme_DESC)

fun CFURLCopyScheme(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyScheme_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyNetLocation typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyNetLocation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyNetLocation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyNetLocation").orElseThrow()
private val CFURLCopyNetLocation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyNetLocation_ADDR, CFURLCopyNetLocation_DESC)

fun CFURLCopyNetLocation(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyNetLocation_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyPath typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyPath").orElseThrow()
private val CFURLCopyPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyPath_ADDR, CFURLCopyPath_DESC)

fun CFURLCopyPath(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyPath_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyStrictPath typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*,(typedef Boolean = UNSIGNED = Char)*)
 */
private val CFURLCopyStrictPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyStrictPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyStrictPath").orElseThrow()
private val CFURLCopyStrictPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyStrictPath_ADDR, CFURLCopyStrictPath_DESC)

fun CFURLCopyStrictPath(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFURLCopyStrictPath_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLHasDirectoryPath typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLHasDirectoryPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFURLHasDirectoryPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLHasDirectoryPath").orElseThrow()
private val CFURLHasDirectoryPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLHasDirectoryPath_ADDR, CFURLHasDirectoryPath_DESC)

fun CFURLHasDirectoryPath(arg0: MemorySegment): Byte {
    try {
        return CFURLHasDirectoryPath_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyResourceSpecifier typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyResourceSpecifier_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyResourceSpecifier_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyResourceSpecifier").orElseThrow()
private val CFURLCopyResourceSpecifier_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyResourceSpecifier_ADDR, CFURLCopyResourceSpecifier_DESC)

fun CFURLCopyResourceSpecifier(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyResourceSpecifier_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyHostName typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyHostName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyHostName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyHostName").orElseThrow()
private val CFURLCopyHostName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyHostName_ADDR, CFURLCopyHostName_DESC)

fun CFURLCopyHostName(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyHostName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLGetPortNumber typedef SInt32 = Int(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLGetPortNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFURLGetPortNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLGetPortNumber").orElseThrow()
private val CFURLGetPortNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLGetPortNumber_ADDR, CFURLGetPortNumber_DESC)

fun CFURLGetPortNumber(arg0: MemorySegment): Int {
    try {
        return CFURLGetPortNumber_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyUserName typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyUserName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyUserName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyUserName").orElseThrow()
private val CFURLCopyUserName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyUserName_ADDR, CFURLCopyUserName_DESC)

fun CFURLCopyUserName(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyUserName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyPassword typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyPassword_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyPassword_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyPassword").orElseThrow()
private val CFURLCopyPassword_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyPassword_ADDR, CFURLCopyPassword_DESC)

fun CFURLCopyPassword(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyPassword_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyParameterString typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFURLCopyParameterString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyParameterString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyParameterString").orElseThrow()
private val CFURLCopyParameterString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyParameterString_ADDR, CFURLCopyParameterString_DESC)

fun CFURLCopyParameterString(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFURLCopyParameterString_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyQueryString typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFURLCopyQueryString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyQueryString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyQueryString").orElseThrow()
private val CFURLCopyQueryString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyQueryString_ADDR, CFURLCopyQueryString_DESC)

fun CFURLCopyQueryString(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFURLCopyQueryString_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyFragment typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFURLCopyFragment_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyFragment_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyFragment").orElseThrow()
private val CFURLCopyFragment_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyFragment_ADDR, CFURLCopyFragment_DESC)

fun CFURLCopyFragment(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFURLCopyFragment_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyLastPathComponent typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyLastPathComponent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyLastPathComponent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyLastPathComponent").orElseThrow()
private val CFURLCopyLastPathComponent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyLastPathComponent_ADDR, CFURLCopyLastPathComponent_DESC)

fun CFURLCopyLastPathComponent(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyLastPathComponent_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyPathExtension typedef CFStringRef = (Declared(__CFString))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCopyPathExtension_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyPathExtension_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyPathExtension").orElseThrow()
private val CFURLCopyPathExtension_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyPathExtension_ADDR, CFURLCopyPathExtension_DESC)

fun CFURLCopyPathExtension(arg0: MemorySegment): MemorySegment {
    try {
        return CFURLCopyPathExtension_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateCopyAppendingPathComponent typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*,typedef Boolean = UNSIGNED = Char)
 */
private val CFURLCreateCopyAppendingPathComponent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BYTE)
private val CFURLCreateCopyAppendingPathComponent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateCopyAppendingPathComponent").orElseThrow()
private val CFURLCreateCopyAppendingPathComponent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateCopyAppendingPathComponent_ADDR, CFURLCreateCopyAppendingPathComponent_DESC)

fun CFURLCreateCopyAppendingPathComponent(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Byte): MemorySegment {
    try {
        return CFURLCreateCopyAppendingPathComponent_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateCopyDeletingLastPathComponent typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCreateCopyDeletingLastPathComponent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateCopyDeletingLastPathComponent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateCopyDeletingLastPathComponent").orElseThrow()
private val CFURLCreateCopyDeletingLastPathComponent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateCopyDeletingLastPathComponent_ADDR, CFURLCreateCopyDeletingLastPathComponent_DESC)

fun CFURLCreateCopyDeletingLastPathComponent(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFURLCreateCopyDeletingLastPathComponent_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateCopyAppendingPathExtension typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFURLCreateCopyAppendingPathExtension_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateCopyAppendingPathExtension_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateCopyAppendingPathExtension").orElseThrow()
private val CFURLCreateCopyAppendingPathExtension_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateCopyAppendingPathExtension_ADDR, CFURLCreateCopyAppendingPathExtension_DESC)

fun CFURLCreateCopyAppendingPathExtension(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFURLCreateCopyAppendingPathExtension_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateCopyDeletingPathExtension typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLCreateCopyDeletingPathExtension_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateCopyDeletingPathExtension_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateCopyDeletingPathExtension").orElseThrow()
private val CFURLCreateCopyDeletingPathExtension_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateCopyDeletingPathExtension_ADDR, CFURLCreateCopyDeletingPathExtension_DESC)

fun CFURLCreateCopyDeletingPathExtension(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFURLCreateCopyDeletingPathExtension_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLGetBytes typedef CFIndex = Long(typedef CFURLRef = (Declared(__CFURL))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long)
 */
private val CFURLGetBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFURLGetBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLGetBytes").orElseThrow()
private val CFURLGetBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLGetBytes_ADDR, CFURLGetBytes_DESC)

fun CFURLGetBytes(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Long {
    try {
        return CFURLGetBytes_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateStringByReplacingPercentEscapes typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFURLCreateStringByReplacingPercentEscapes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateStringByReplacingPercentEscapes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateStringByReplacingPercentEscapes").orElseThrow()
private val CFURLCreateStringByReplacingPercentEscapes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateStringByReplacingPercentEscapes_ADDR, CFURLCreateStringByReplacingPercentEscapes_DESC)

fun CFURLCreateStringByReplacingPercentEscapes(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFURLCreateStringByReplacingPercentEscapes_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateStringByReplacingPercentEscapesUsingEncoding typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFURLCreateStringByReplacingPercentEscapesUsingEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFURLCreateStringByReplacingPercentEscapesUsingEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateStringByReplacingPercentEscapesUsingEncoding").orElseThrow()
private val CFURLCreateStringByReplacingPercentEscapesUsingEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateStringByReplacingPercentEscapesUsingEncoding_ADDR, CFURLCreateStringByReplacingPercentEscapesUsingEncoding_DESC)

fun CFURLCreateStringByReplacingPercentEscapesUsingEncoding(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Int): MemorySegment {
    try {
        return CFURLCreateStringByReplacingPercentEscapesUsingEncoding_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateStringByAddingPercentEscapes typedef CFStringRef = (Declared(__CFString))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFStringEncoding = UNSIGNED = Int)
 */
private val CFURLCreateStringByAddingPercentEscapes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CFURLCreateStringByAddingPercentEscapes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateStringByAddingPercentEscapes").orElseThrow()
private val CFURLCreateStringByAddingPercentEscapes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateStringByAddingPercentEscapes_ADDR, CFURLCreateStringByAddingPercentEscapes_DESC)

fun CFURLCreateStringByAddingPercentEscapes(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: Int): MemorySegment {
    try {
        return CFURLCreateStringByAddingPercentEscapes_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLIsFileReferenceURL typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLIsFileReferenceURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFURLIsFileReferenceURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLIsFileReferenceURL").orElseThrow()
private val CFURLIsFileReferenceURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLIsFileReferenceURL_ADDR, CFURLIsFileReferenceURL_DESC)

fun CFURLIsFileReferenceURL(arg0: MemorySegment): Byte {
    try {
        return CFURLIsFileReferenceURL_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateFileReferenceURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFURLCreateFileReferenceURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateFileReferenceURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateFileReferenceURL").orElseThrow()
private val CFURLCreateFileReferenceURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateFileReferenceURL_ADDR, CFURLCreateFileReferenceURL_DESC)

fun CFURLCreateFileReferenceURL(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFURLCreateFileReferenceURL_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateFilePathURL typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFURLCreateFilePathURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateFilePathURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateFilePathURL").orElseThrow()
private val CFURLCreateFilePathURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateFilePathURL_ADDR, CFURLCreateFilePathURL_DESC)

fun CFURLCreateFilePathURL(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFURLCreateFilePathURL_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateFromFSRef typedef CFURLRef = (Declared(__CFURL))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(Void)*)
 */
private val CFURLCreateFromFSRef_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateFromFSRef_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateFromFSRef").orElseThrow()
private val CFURLCreateFromFSRef_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateFromFSRef_ADDR, CFURLCreateFromFSRef_DESC)

fun CFURLCreateFromFSRef(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFURLCreateFromFSRef_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLGetFSRef typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*,(Void)*)
 */
private val CFURLGetFSRef_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLGetFSRef_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLGetFSRef").orElseThrow()
private val CFURLGetFSRef_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLGetFSRef_ADDR, CFURLGetFSRef_DESC)

fun CFURLGetFSRef(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFURLGetFSRef_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyResourcePropertyForKey typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*,(Void)*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFURLCopyResourcePropertyForKey_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyResourcePropertyForKey_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyResourcePropertyForKey").orElseThrow()
private val CFURLCopyResourcePropertyForKey_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyResourcePropertyForKey_ADDR, CFURLCopyResourcePropertyForKey_DESC)

fun CFURLCopyResourcePropertyForKey(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Byte {
    try {
        return CFURLCopyResourcePropertyForKey_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCopyResourcePropertiesForKeys typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFURLRef = (Declared(__CFURL))*,typedef CFArrayRef = (Declared(__CFArray))*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFURLCopyResourcePropertiesForKeys_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCopyResourcePropertiesForKeys_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCopyResourcePropertiesForKeys").orElseThrow()
private val CFURLCopyResourcePropertiesForKeys_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCopyResourcePropertiesForKeys_ADDR, CFURLCopyResourcePropertiesForKeys_DESC)

fun CFURLCopyResourcePropertiesForKeys(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFURLCopyResourcePropertiesForKeys_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLSetResourcePropertyForKey typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFTypeRef = (Void)*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFURLSetResourcePropertyForKey_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLSetResourcePropertyForKey_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLSetResourcePropertyForKey").orElseThrow()
private val CFURLSetResourcePropertyForKey_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLSetResourcePropertyForKey_ADDR, CFURLSetResourcePropertyForKey_DESC)

fun CFURLSetResourcePropertyForKey(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Byte {
    try {
        return CFURLSetResourcePropertyForKey_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLSetResourcePropertiesForKeys typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFURLSetResourcePropertiesForKeys_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLSetResourcePropertiesForKeys_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLSetResourcePropertiesForKeys").orElseThrow()
private val CFURLSetResourcePropertiesForKeys_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLSetResourcePropertiesForKeys_ADDR, CFURLSetResourcePropertiesForKeys_DESC)

fun CFURLSetResourcePropertiesForKeys(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFURLSetResourcePropertiesForKeys_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFURLKeysOfUnsetValuesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLKeysOfUnsetValuesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLKeysOfUnsetValuesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLKeysOfUnsetValuesKey").orElseThrow() }
private val kCFURLKeysOfUnsetValuesKey_VH: VarHandle by lazy { kCFURLKeysOfUnsetValuesKey_LAYOUT.varHandle() }

var kCFURLKeysOfUnsetValuesKey: MemorySegment
    get() = kCFURLKeysOfUnsetValuesKey_VH.get(kCFURLKeysOfUnsetValuesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLKeysOfUnsetValuesKey_VH.set(kCFURLKeysOfUnsetValuesKey_SEGMENT, value)

/**
 * {@snippet lang=c : CFURLClearResourcePropertyCacheForKey Void(typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CFURLClearResourcePropertyCacheForKey_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLClearResourcePropertyCacheForKey_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLClearResourcePropertyCacheForKey").orElseThrow()
private val CFURLClearResourcePropertyCacheForKey_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLClearResourcePropertyCacheForKey_ADDR, CFURLClearResourcePropertyCacheForKey_DESC)

fun CFURLClearResourcePropertyCacheForKey(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFURLClearResourcePropertyCacheForKey_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLClearResourcePropertyCache Void(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLClearResourcePropertyCache_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFURLClearResourcePropertyCache_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLClearResourcePropertyCache").orElseThrow()
private val CFURLClearResourcePropertyCache_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLClearResourcePropertyCache_ADDR, CFURLClearResourcePropertyCache_DESC)

fun CFURLClearResourcePropertyCache(arg0: MemorySegment): Unit {
    try {
        CFURLClearResourcePropertyCache_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLSetTemporaryResourcePropertyForKey Void(typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFTypeRef = (Void)*)
 */
private val CFURLSetTemporaryResourcePropertyForKey_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLSetTemporaryResourcePropertyForKey_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLSetTemporaryResourcePropertyForKey").orElseThrow()
private val CFURLSetTemporaryResourcePropertyForKey_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLSetTemporaryResourcePropertyForKey_ADDR, CFURLSetTemporaryResourcePropertyForKey_DESC)

fun CFURLSetTemporaryResourcePropertyForKey(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFURLSetTemporaryResourcePropertyForKey_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLResourceIsReachable typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFURLResourceIsReachable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLResourceIsReachable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLResourceIsReachable").orElseThrow()
private val CFURLResourceIsReachable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLResourceIsReachable_ADDR, CFURLResourceIsReachable_DESC)

fun CFURLResourceIsReachable(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFURLResourceIsReachable_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFURLNameKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLNameKey").orElseThrow() }
private val kCFURLNameKey_VH: VarHandle by lazy { kCFURLNameKey_LAYOUT.varHandle() }

var kCFURLNameKey: MemorySegment
    get() = kCFURLNameKey_VH.get(kCFURLNameKey_SEGMENT) as MemorySegment
    set(value) = kCFURLNameKey_VH.set(kCFURLNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLLocalizedNameKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLLocalizedNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLLocalizedNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLLocalizedNameKey").orElseThrow() }
private val kCFURLLocalizedNameKey_VH: VarHandle by lazy { kCFURLLocalizedNameKey_LAYOUT.varHandle() }

var kCFURLLocalizedNameKey: MemorySegment
    get() = kCFURLLocalizedNameKey_VH.get(kCFURLLocalizedNameKey_SEGMENT) as MemorySegment
    set(value) = kCFURLLocalizedNameKey_VH.set(kCFURLLocalizedNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsRegularFileKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsRegularFileKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsRegularFileKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsRegularFileKey").orElseThrow() }
private val kCFURLIsRegularFileKey_VH: VarHandle by lazy { kCFURLIsRegularFileKey_LAYOUT.varHandle() }

var kCFURLIsRegularFileKey: MemorySegment
    get() = kCFURLIsRegularFileKey_VH.get(kCFURLIsRegularFileKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsRegularFileKey_VH.set(kCFURLIsRegularFileKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsDirectoryKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsDirectoryKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsDirectoryKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsDirectoryKey").orElseThrow() }
private val kCFURLIsDirectoryKey_VH: VarHandle by lazy { kCFURLIsDirectoryKey_LAYOUT.varHandle() }

var kCFURLIsDirectoryKey: MemorySegment
    get() = kCFURLIsDirectoryKey_VH.get(kCFURLIsDirectoryKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsDirectoryKey_VH.set(kCFURLIsDirectoryKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsSymbolicLinkKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsSymbolicLinkKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsSymbolicLinkKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsSymbolicLinkKey").orElseThrow() }
private val kCFURLIsSymbolicLinkKey_VH: VarHandle by lazy { kCFURLIsSymbolicLinkKey_LAYOUT.varHandle() }

var kCFURLIsSymbolicLinkKey: MemorySegment
    get() = kCFURLIsSymbolicLinkKey_VH.get(kCFURLIsSymbolicLinkKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsSymbolicLinkKey_VH.set(kCFURLIsSymbolicLinkKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsVolumeKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsVolumeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsVolumeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsVolumeKey").orElseThrow() }
private val kCFURLIsVolumeKey_VH: VarHandle by lazy { kCFURLIsVolumeKey_LAYOUT.varHandle() }

var kCFURLIsVolumeKey: MemorySegment
    get() = kCFURLIsVolumeKey_VH.get(kCFURLIsVolumeKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsVolumeKey_VH.set(kCFURLIsVolumeKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsPackageKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsPackageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsPackageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsPackageKey").orElseThrow() }
private val kCFURLIsPackageKey_VH: VarHandle by lazy { kCFURLIsPackageKey_LAYOUT.varHandle() }

var kCFURLIsPackageKey: MemorySegment
    get() = kCFURLIsPackageKey_VH.get(kCFURLIsPackageKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsPackageKey_VH.set(kCFURLIsPackageKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsApplicationKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsApplicationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsApplicationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsApplicationKey").orElseThrow() }
private val kCFURLIsApplicationKey_VH: VarHandle by lazy { kCFURLIsApplicationKey_LAYOUT.varHandle() }

var kCFURLIsApplicationKey: MemorySegment
    get() = kCFURLIsApplicationKey_VH.get(kCFURLIsApplicationKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsApplicationKey_VH.set(kCFURLIsApplicationKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLApplicationIsScriptableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLApplicationIsScriptableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLApplicationIsScriptableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLApplicationIsScriptableKey").orElseThrow() }
private val kCFURLApplicationIsScriptableKey_VH: VarHandle by lazy { kCFURLApplicationIsScriptableKey_LAYOUT.varHandle() }

var kCFURLApplicationIsScriptableKey: MemorySegment
    get() = kCFURLApplicationIsScriptableKey_VH.get(kCFURLApplicationIsScriptableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLApplicationIsScriptableKey_VH.set(kCFURLApplicationIsScriptableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsSystemImmutableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsSystemImmutableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsSystemImmutableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsSystemImmutableKey").orElseThrow() }
private val kCFURLIsSystemImmutableKey_VH: VarHandle by lazy { kCFURLIsSystemImmutableKey_LAYOUT.varHandle() }

var kCFURLIsSystemImmutableKey: MemorySegment
    get() = kCFURLIsSystemImmutableKey_VH.get(kCFURLIsSystemImmutableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsSystemImmutableKey_VH.set(kCFURLIsSystemImmutableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsUserImmutableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsUserImmutableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsUserImmutableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsUserImmutableKey").orElseThrow() }
private val kCFURLIsUserImmutableKey_VH: VarHandle by lazy { kCFURLIsUserImmutableKey_LAYOUT.varHandle() }

var kCFURLIsUserImmutableKey: MemorySegment
    get() = kCFURLIsUserImmutableKey_VH.get(kCFURLIsUserImmutableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsUserImmutableKey_VH.set(kCFURLIsUserImmutableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsHiddenKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsHiddenKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsHiddenKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsHiddenKey").orElseThrow() }
private val kCFURLIsHiddenKey_VH: VarHandle by lazy { kCFURLIsHiddenKey_LAYOUT.varHandle() }

var kCFURLIsHiddenKey: MemorySegment
    get() = kCFURLIsHiddenKey_VH.get(kCFURLIsHiddenKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsHiddenKey_VH.set(kCFURLIsHiddenKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLHasHiddenExtensionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLHasHiddenExtensionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLHasHiddenExtensionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLHasHiddenExtensionKey").orElseThrow() }
private val kCFURLHasHiddenExtensionKey_VH: VarHandle by lazy { kCFURLHasHiddenExtensionKey_LAYOUT.varHandle() }

var kCFURLHasHiddenExtensionKey: MemorySegment
    get() = kCFURLHasHiddenExtensionKey_VH.get(kCFURLHasHiddenExtensionKey_SEGMENT) as MemorySegment
    set(value) = kCFURLHasHiddenExtensionKey_VH.set(kCFURLHasHiddenExtensionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLCreationDateKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLCreationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLCreationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLCreationDateKey").orElseThrow() }
private val kCFURLCreationDateKey_VH: VarHandle by lazy { kCFURLCreationDateKey_LAYOUT.varHandle() }

var kCFURLCreationDateKey: MemorySegment
    get() = kCFURLCreationDateKey_VH.get(kCFURLCreationDateKey_SEGMENT) as MemorySegment
    set(value) = kCFURLCreationDateKey_VH.set(kCFURLCreationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLContentAccessDateKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLContentAccessDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLContentAccessDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLContentAccessDateKey").orElseThrow() }
private val kCFURLContentAccessDateKey_VH: VarHandle by lazy { kCFURLContentAccessDateKey_LAYOUT.varHandle() }

var kCFURLContentAccessDateKey: MemorySegment
    get() = kCFURLContentAccessDateKey_VH.get(kCFURLContentAccessDateKey_SEGMENT) as MemorySegment
    set(value) = kCFURLContentAccessDateKey_VH.set(kCFURLContentAccessDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLContentModificationDateKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLContentModificationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLContentModificationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLContentModificationDateKey").orElseThrow() }
private val kCFURLContentModificationDateKey_VH: VarHandle by lazy { kCFURLContentModificationDateKey_LAYOUT.varHandle() }

var kCFURLContentModificationDateKey: MemorySegment
    get() = kCFURLContentModificationDateKey_VH.get(kCFURLContentModificationDateKey_SEGMENT) as MemorySegment
    set(value) = kCFURLContentModificationDateKey_VH.set(kCFURLContentModificationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLAttributeModificationDateKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLAttributeModificationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLAttributeModificationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLAttributeModificationDateKey").orElseThrow() }
private val kCFURLAttributeModificationDateKey_VH: VarHandle by lazy { kCFURLAttributeModificationDateKey_LAYOUT.varHandle() }

var kCFURLAttributeModificationDateKey: MemorySegment
    get() = kCFURLAttributeModificationDateKey_VH.get(kCFURLAttributeModificationDateKey_SEGMENT) as MemorySegment
    set(value) = kCFURLAttributeModificationDateKey_VH.set(kCFURLAttributeModificationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileIdentifierKey").orElseThrow() }
private val kCFURLFileIdentifierKey_VH: VarHandle by lazy { kCFURLFileIdentifierKey_LAYOUT.varHandle() }

var kCFURLFileIdentifierKey: MemorySegment
    get() = kCFURLFileIdentifierKey_VH.get(kCFURLFileIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFURLFileIdentifierKey_VH.set(kCFURLFileIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileContentIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileContentIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileContentIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileContentIdentifierKey").orElseThrow() }
private val kCFURLFileContentIdentifierKey_VH: VarHandle by lazy { kCFURLFileContentIdentifierKey_LAYOUT.varHandle() }

var kCFURLFileContentIdentifierKey: MemorySegment
    get() = kCFURLFileContentIdentifierKey_VH.get(kCFURLFileContentIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFURLFileContentIdentifierKey_VH.set(kCFURLFileContentIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLMayShareFileContentKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLMayShareFileContentKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLMayShareFileContentKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLMayShareFileContentKey").orElseThrow() }
private val kCFURLMayShareFileContentKey_VH: VarHandle by lazy { kCFURLMayShareFileContentKey_LAYOUT.varHandle() }

var kCFURLMayShareFileContentKey: MemorySegment
    get() = kCFURLMayShareFileContentKey_VH.get(kCFURLMayShareFileContentKey_SEGMENT) as MemorySegment
    set(value) = kCFURLMayShareFileContentKey_VH.set(kCFURLMayShareFileContentKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLMayHaveExtendedAttributesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLMayHaveExtendedAttributesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLMayHaveExtendedAttributesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLMayHaveExtendedAttributesKey").orElseThrow() }
private val kCFURLMayHaveExtendedAttributesKey_VH: VarHandle by lazy { kCFURLMayHaveExtendedAttributesKey_LAYOUT.varHandle() }

var kCFURLMayHaveExtendedAttributesKey: MemorySegment
    get() = kCFURLMayHaveExtendedAttributesKey_VH.get(kCFURLMayHaveExtendedAttributesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLMayHaveExtendedAttributesKey_VH.set(kCFURLMayHaveExtendedAttributesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsPurgeableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsPurgeableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsPurgeableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsPurgeableKey").orElseThrow() }
private val kCFURLIsPurgeableKey_VH: VarHandle by lazy { kCFURLIsPurgeableKey_LAYOUT.varHandle() }

var kCFURLIsPurgeableKey: MemorySegment
    get() = kCFURLIsPurgeableKey_VH.get(kCFURLIsPurgeableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsPurgeableKey_VH.set(kCFURLIsPurgeableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsSparseKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsSparseKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsSparseKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsSparseKey").orElseThrow() }
private val kCFURLIsSparseKey_VH: VarHandle by lazy { kCFURLIsSparseKey_LAYOUT.varHandle() }

var kCFURLIsSparseKey: MemorySegment
    get() = kCFURLIsSparseKey_VH.get(kCFURLIsSparseKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsSparseKey_VH.set(kCFURLIsSparseKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLLinkCountKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLLinkCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLLinkCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLLinkCountKey").orElseThrow() }
private val kCFURLLinkCountKey_VH: VarHandle by lazy { kCFURLLinkCountKey_LAYOUT.varHandle() }

var kCFURLLinkCountKey: MemorySegment
    get() = kCFURLLinkCountKey_VH.get(kCFURLLinkCountKey_SEGMENT) as MemorySegment
    set(value) = kCFURLLinkCountKey_VH.set(kCFURLLinkCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLParentDirectoryURLKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLParentDirectoryURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLParentDirectoryURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLParentDirectoryURLKey").orElseThrow() }
private val kCFURLParentDirectoryURLKey_VH: VarHandle by lazy { kCFURLParentDirectoryURLKey_LAYOUT.varHandle() }

var kCFURLParentDirectoryURLKey: MemorySegment
    get() = kCFURLParentDirectoryURLKey_VH.get(kCFURLParentDirectoryURLKey_SEGMENT) as MemorySegment
    set(value) = kCFURLParentDirectoryURLKey_VH.set(kCFURLParentDirectoryURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeURLKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeURLKey").orElseThrow() }
private val kCFURLVolumeURLKey_VH: VarHandle by lazy { kCFURLVolumeURLKey_LAYOUT.varHandle() }

var kCFURLVolumeURLKey: MemorySegment
    get() = kCFURLVolumeURLKey_VH.get(kCFURLVolumeURLKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeURLKey_VH.set(kCFURLVolumeURLKey_SEGMENT, value)

