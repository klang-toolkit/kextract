package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : CGPathCreateCopyBySymmetricDifferenceOfPath typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,typedef CGPathRef = (Declared(CGPath))*,Bool)
 */
private val CGPathCreateCopyBySymmetricDifferenceOfPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGPathCreateCopyBySymmetricDifferenceOfPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyBySymmetricDifferenceOfPath").orElseThrow()
private val CGPathCreateCopyBySymmetricDifferenceOfPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyBySymmetricDifferenceOfPath_ADDR, CGPathCreateCopyBySymmetricDifferenceOfPath_DESC)

fun CGPathCreateCopyBySymmetricDifferenceOfPath(arg0: MemorySegment, arg1: MemorySegment, arg2: Boolean): MemorySegment {
    try {
        return CGPathCreateCopyBySymmetricDifferenceOfPath_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopyOfLineBySubtractingPath typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,typedef CGPathRef = (Declared(CGPath))*,Bool)
 */
private val CGPathCreateCopyOfLineBySubtractingPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGPathCreateCopyOfLineBySubtractingPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyOfLineBySubtractingPath").orElseThrow()
private val CGPathCreateCopyOfLineBySubtractingPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyOfLineBySubtractingPath_ADDR, CGPathCreateCopyOfLineBySubtractingPath_DESC)

fun CGPathCreateCopyOfLineBySubtractingPath(arg0: MemorySegment, arg1: MemorySegment, arg2: Boolean): MemorySegment {
    try {
        return CGPathCreateCopyOfLineBySubtractingPath_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopyOfLineByIntersectingPath typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,typedef CGPathRef = (Declared(CGPath))*,Bool)
 */
private val CGPathCreateCopyOfLineByIntersectingPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGPathCreateCopyOfLineByIntersectingPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyOfLineByIntersectingPath").orElseThrow()
private val CGPathCreateCopyOfLineByIntersectingPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyOfLineByIntersectingPath_ADDR, CGPathCreateCopyOfLineByIntersectingPath_DESC)

fun CGPathCreateCopyOfLineByIntersectingPath(arg0: MemorySegment, arg1: MemorySegment, arg2: Boolean): MemorySegment {
    try {
        return CGPathCreateCopyOfLineByIntersectingPath_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateSeparateComponents typedef CFArrayRef = (Declared(__CFArray))*(typedef CGPathRef = (Declared(CGPath))*,Bool)
 */
private val CGPathCreateSeparateComponents_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGPathCreateSeparateComponents_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateSeparateComponents").orElseThrow()
private val CGPathCreateSeparateComponents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateSeparateComponents_ADDR, CGPathCreateSeparateComponents_DESC)

fun CGPathCreateSeparateComponents(arg0: MemorySegment, arg1: Boolean): MemorySegment {
    try {
        return CGPathCreateSeparateComponents_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopyByFlattening typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,typedef CGFloat = Double)
 */
private val CGPathCreateCopyByFlattening_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGPathCreateCopyByFlattening_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyByFlattening").orElseThrow()
private val CGPathCreateCopyByFlattening_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyByFlattening_ADDR, CGPathCreateCopyByFlattening_DESC)

fun CGPathCreateCopyByFlattening(arg0: MemorySegment, arg1: Double): MemorySegment {
    try {
        return CGPathCreateCopyByFlattening_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathIntersectsPath Bool(typedef CGPathRef = (Declared(CGPath))*,typedef CGPathRef = (Declared(CGPath))*,Bool)
 */
private val CGPathIntersectsPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGPathIntersectsPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathIntersectsPath").orElseThrow()
private val CGPathIntersectsPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathIntersectsPath_ADDR, CGPathIntersectsPath_DESC)

fun CGPathIntersectsPath(arg0: MemorySegment, arg1: MemorySegment, arg2: Boolean): Boolean {
    try {
        return CGPathIntersectsPath_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFStreamGetDictionary typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*(typedef CGPDFStreamRef = (Declared(CGPDFStream))*)
 */
private val CGPDFStreamGetDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFStreamGetDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFStreamGetDictionary").orElseThrow()
private val CGPDFStreamGetDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFStreamGetDictionary_ADDR, CGPDFStreamGetDictionary_DESC)

fun CGPDFStreamGetDictionary(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFStreamGetDictionary_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFStreamCopyData typedef CFDataRef = (Declared(__CFData))*(typedef CGPDFStreamRef = (Declared(CGPDFStream))*,(typedef CGPDFDataFormat = <error: enum CGPDFDataFormat>)*)
 */
private val CGPDFStreamCopyData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFStreamCopyData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFStreamCopyData").orElseThrow()
private val CGPDFStreamCopyData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFStreamCopyData_ADDR, CGPDFStreamCopyData_DESC)

fun CGPDFStreamCopyData(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGPDFStreamCopyData_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFStringGetLength typedef size_t = UNSIGNED = Long(typedef CGPDFStringRef = (Declared(CGPDFString))*)
 */
private val CGPDFStringGetLength_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFStringGetLength_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFStringGetLength").orElseThrow()
private val CGPDFStringGetLength_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFStringGetLength_ADDR, CGPDFStringGetLength_DESC)

fun CGPDFStringGetLength(arg0: MemorySegment): Long {
    try {
        return CGPDFStringGetLength_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFStringGetBytePtr (UNSIGNED = Char)*(typedef CGPDFStringRef = (Declared(CGPDFString))*)
 */
private val CGPDFStringGetBytePtr_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFStringGetBytePtr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFStringGetBytePtr").orElseThrow()
private val CGPDFStringGetBytePtr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFStringGetBytePtr_ADDR, CGPDFStringGetBytePtr_DESC)

fun CGPDFStringGetBytePtr(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFStringGetBytePtr_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFStringCopyTextString typedef CFStringRef = (Declared(__CFString))*(typedef CGPDFStringRef = (Declared(CGPDFString))*)
 */
private val CGPDFStringCopyTextString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFStringCopyTextString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFStringCopyTextString").orElseThrow()
private val CGPDFStringCopyTextString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFStringCopyTextString_ADDR, CGPDFStringCopyTextString_DESC)

fun CGPDFStringCopyTextString(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFStringCopyTextString_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFStringCopyDate typedef CFDateRef = (Declared(__CFDate))*(typedef CGPDFStringRef = (Declared(CGPDFString))*)
 */
private val CGPDFStringCopyDate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFStringCopyDate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFStringCopyDate").orElseThrow()
private val CGPDFStringCopyDate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFStringCopyDate_ADDR, CGPDFStringCopyDate_DESC)

fun CGPDFStringCopyDate(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFStringCopyDate_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetCount typedef size_t = UNSIGNED = Long(typedef CGPDFArrayRef = (Declared(CGPDFArray))*)
 */
private val CGPDFArrayGetCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetCount").orElseThrow()
private val CGPDFArrayGetCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetCount_ADDR, CGPDFArrayGetCount_DESC)

fun CGPDFArrayGetCount(arg0: MemorySegment): Long {
    try {
        return CGPDFArrayGetCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetObject Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long,(typedef CGPDFObjectRef = (Declared(CGPDFObject))*)*)
 */
private val CGPDFArrayGetObject_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetObject_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetObject").orElseThrow()
private val CGPDFArrayGetObject_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetObject_ADDR, CGPDFArrayGetObject_DESC)

fun CGPDFArrayGetObject(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Boolean {
    try {
        return CGPDFArrayGetObject_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetNull Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long)
 */
private val CGPDFArrayGetNull_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGPDFArrayGetNull_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetNull").orElseThrow()
private val CGPDFArrayGetNull_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetNull_ADDR, CGPDFArrayGetNull_DESC)

fun CGPDFArrayGetNull(arg0: MemorySegment, arg1: Long): Boolean {
    try {
        return CGPDFArrayGetNull_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetBoolean Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long,(typedef CGPDFBoolean = UNSIGNED = Char)*)
 */
private val CGPDFArrayGetBoolean_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetBoolean_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetBoolean").orElseThrow()
private val CGPDFArrayGetBoolean_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetBoolean_ADDR, CGPDFArrayGetBoolean_DESC)

fun CGPDFArrayGetBoolean(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Boolean {
    try {
        return CGPDFArrayGetBoolean_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetInteger Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long,(typedef CGPDFInteger = Long)*)
 */
private val CGPDFArrayGetInteger_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetInteger_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetInteger").orElseThrow()
private val CGPDFArrayGetInteger_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetInteger_ADDR, CGPDFArrayGetInteger_DESC)

fun CGPDFArrayGetInteger(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Boolean {
    try {
        return CGPDFArrayGetInteger_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetNumber Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long,(typedef CGPDFReal = Double)*)
 */
private val CGPDFArrayGetNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetNumber").orElseThrow()
private val CGPDFArrayGetNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetNumber_ADDR, CGPDFArrayGetNumber_DESC)

fun CGPDFArrayGetNumber(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Boolean {
    try {
        return CGPDFArrayGetNumber_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetName Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long,((Char)*)*)
 */
private val CGPDFArrayGetName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetName").orElseThrow()
private val CGPDFArrayGetName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetName_ADDR, CGPDFArrayGetName_DESC)

fun CGPDFArrayGetName(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Boolean {
    try {
        return CGPDFArrayGetName_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetString Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long,(typedef CGPDFStringRef = (Declared(CGPDFString))*)*)
 */
private val CGPDFArrayGetString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetString").orElseThrow()
private val CGPDFArrayGetString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetString_ADDR, CGPDFArrayGetString_DESC)

fun CGPDFArrayGetString(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Boolean {
    try {
        return CGPDFArrayGetString_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetArray Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long,(typedef CGPDFArrayRef = (Declared(CGPDFArray))*)*)
 */
private val CGPDFArrayGetArray_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetArray_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetArray").orElseThrow()
private val CGPDFArrayGetArray_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetArray_ADDR, CGPDFArrayGetArray_DESC)

fun CGPDFArrayGetArray(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Boolean {
    try {
        return CGPDFArrayGetArray_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetDictionary Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long,(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*)*)
 */
private val CGPDFArrayGetDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetDictionary").orElseThrow()
private val CGPDFArrayGetDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetDictionary_ADDR, CGPDFArrayGetDictionary_DESC)

fun CGPDFArrayGetDictionary(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Boolean {
    try {
        return CGPDFArrayGetDictionary_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayGetStream Bool(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef size_t = UNSIGNED = Long,(typedef CGPDFStreamRef = (Declared(CGPDFStream))*)*)
 */
private val CGPDFArrayGetStream_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFArrayGetStream_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayGetStream").orElseThrow()
private val CGPDFArrayGetStream_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayGetStream_ADDR, CGPDFArrayGetStream_DESC)

fun CGPDFArrayGetStream(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): Boolean {
    try {
        return CGPDFArrayGetStream_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFArrayApplyBlock Void(typedef CGPDFArrayRef = (Declared(CGPDFArray))*,typedef CGPDFArrayApplierBlock = (Void)*,(Void)*)
 */
private val CGPDFArrayApplyBlock_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFArrayApplyBlock_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFArrayApplyBlock").orElseThrow()
private val CGPDFArrayApplyBlock_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFArrayApplyBlock_ADDR, CGPDFArrayApplyBlock_DESC)

fun CGPDFArrayApplyBlock(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPDFArrayApplyBlock_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetCount typedef size_t = UNSIGNED = Long(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*)
 */
private val CGPDFDictionaryGetCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetCount").orElseThrow()
private val CGPDFDictionaryGetCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetCount_ADDR, CGPDFDictionaryGetCount_DESC)

fun CGPDFDictionaryGetCount(arg0: MemorySegment): Long {
    try {
        return CGPDFDictionaryGetCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetObject Bool(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,(Char)*,(typedef CGPDFObjectRef = (Declared(CGPDFObject))*)*)
 */
private val CGPDFDictionaryGetObject_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetObject_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetObject").orElseThrow()
private val CGPDFDictionaryGetObject_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetObject_ADDR, CGPDFDictionaryGetObject_DESC)

fun CGPDFDictionaryGetObject(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return CGPDFDictionaryGetObject_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetBoolean Bool(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,(Char)*,(typedef CGPDFBoolean = UNSIGNED = Char)*)
 */
private val CGPDFDictionaryGetBoolean_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetBoolean_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetBoolean").orElseThrow()
private val CGPDFDictionaryGetBoolean_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetBoolean_ADDR, CGPDFDictionaryGetBoolean_DESC)

fun CGPDFDictionaryGetBoolean(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return CGPDFDictionaryGetBoolean_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetInteger Bool(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,(Char)*,(typedef CGPDFInteger = Long)*)
 */
private val CGPDFDictionaryGetInteger_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetInteger_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetInteger").orElseThrow()
private val CGPDFDictionaryGetInteger_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetInteger_ADDR, CGPDFDictionaryGetInteger_DESC)

fun CGPDFDictionaryGetInteger(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return CGPDFDictionaryGetInteger_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetNumber Bool(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,(Char)*,(typedef CGPDFReal = Double)*)
 */
private val CGPDFDictionaryGetNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetNumber").orElseThrow()
private val CGPDFDictionaryGetNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetNumber_ADDR, CGPDFDictionaryGetNumber_DESC)

fun CGPDFDictionaryGetNumber(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return CGPDFDictionaryGetNumber_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetName Bool(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,(Char)*,((Char)*)*)
 */
private val CGPDFDictionaryGetName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetName").orElseThrow()
private val CGPDFDictionaryGetName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetName_ADDR, CGPDFDictionaryGetName_DESC)

fun CGPDFDictionaryGetName(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return CGPDFDictionaryGetName_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetString Bool(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,(Char)*,(typedef CGPDFStringRef = (Declared(CGPDFString))*)*)
 */
private val CGPDFDictionaryGetString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetString").orElseThrow()
private val CGPDFDictionaryGetString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetString_ADDR, CGPDFDictionaryGetString_DESC)

fun CGPDFDictionaryGetString(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return CGPDFDictionaryGetString_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetArray Bool(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,(Char)*,(typedef CGPDFArrayRef = (Declared(CGPDFArray))*)*)
 */
private val CGPDFDictionaryGetArray_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetArray_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetArray").orElseThrow()
private val CGPDFDictionaryGetArray_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetArray_ADDR, CGPDFDictionaryGetArray_DESC)

fun CGPDFDictionaryGetArray(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return CGPDFDictionaryGetArray_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetDictionary Bool(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,(Char)*,(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*)*)
 */
private val CGPDFDictionaryGetDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetDictionary").orElseThrow()
private val CGPDFDictionaryGetDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetDictionary_ADDR, CGPDFDictionaryGetDictionary_DESC)

fun CGPDFDictionaryGetDictionary(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return CGPDFDictionaryGetDictionary_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryGetStream Bool(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,(Char)*,(typedef CGPDFStreamRef = (Declared(CGPDFStream))*)*)
 */
private val CGPDFDictionaryGetStream_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryGetStream_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryGetStream").orElseThrow()
private val CGPDFDictionaryGetStream_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryGetStream_ADDR, CGPDFDictionaryGetStream_DESC)

fun CGPDFDictionaryGetStream(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return CGPDFDictionaryGetStream_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryApplyFunction Void(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,typedef CGPDFDictionaryApplierFunction = (Void((Char)*,(Declared(CGPDFObject))*,(Void)*))*,(Void)*)
 */
private val CGPDFDictionaryApplyFunction_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryApplyFunction_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryApplyFunction").orElseThrow()
private val CGPDFDictionaryApplyFunction_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryApplyFunction_ADDR, CGPDFDictionaryApplyFunction_DESC)

fun CGPDFDictionaryApplyFunction(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPDFDictionaryApplyFunction_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDictionaryApplyBlock Void(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,typedef CGPDFDictionaryApplierBlock = (Void)*,(Void)*)
 */
private val CGPDFDictionaryApplyBlock_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDictionaryApplyBlock_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDictionaryApplyBlock").orElseThrow()
private val CGPDFDictionaryApplyBlock_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDictionaryApplyBlock_ADDR, CGPDFDictionaryApplyBlock_DESC)

fun CGPDFDictionaryApplyBlock(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPDFDictionaryApplyBlock_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFPageRetain typedef CGPDFPageRef = (Declared(CGPDFPage))*(typedef CGPDFPageRef = (Declared(CGPDFPage))*)
 */
private val CGPDFPageRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFPageRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFPageRetain").orElseThrow()
private val CGPDFPageRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFPageRetain_ADDR, CGPDFPageRetain_DESC)

fun CGPDFPageRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFPageRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFPageRelease Void(typedef CGPDFPageRef = (Declared(CGPDFPage))*)
 */
private val CGPDFPageRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPDFPageRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFPageRelease").orElseThrow()
private val CGPDFPageRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFPageRelease_ADDR, CGPDFPageRelease_DESC)

fun CGPDFPageRelease(arg0: MemorySegment): Unit {
    try {
        CGPDFPageRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFPageGetDocument typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*(typedef CGPDFPageRef = (Declared(CGPDFPage))*)
 */
private val CGPDFPageGetDocument_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFPageGetDocument_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFPageGetDocument").orElseThrow()
private val CGPDFPageGetDocument_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFPageGetDocument_ADDR, CGPDFPageGetDocument_DESC)

fun CGPDFPageGetDocument(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFPageGetDocument_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFPageGetPageNumber typedef size_t = UNSIGNED = Long(typedef CGPDFPageRef = (Declared(CGPDFPage))*)
 */
private val CGPDFPageGetPageNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFPageGetPageNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFPageGetPageNumber").orElseThrow()
private val CGPDFPageGetPageNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFPageGetPageNumber_ADDR, CGPDFPageGetPageNumber_DESC)

fun CGPDFPageGetPageNumber(arg0: MemorySegment): Long {
    try {
        return CGPDFPageGetPageNumber_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFPageGetRotationAngle Int(typedef CGPDFPageRef = (Declared(CGPDFPage))*)
 */
private val CGPDFPageGetRotationAngle_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGPDFPageGetRotationAngle_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFPageGetRotationAngle").orElseThrow()
private val CGPDFPageGetRotationAngle_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFPageGetRotationAngle_ADDR, CGPDFPageGetRotationAngle_DESC)

fun CGPDFPageGetRotationAngle(arg0: MemorySegment): Int {
    try {
        return CGPDFPageGetRotationAngle_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFPageGetDictionary typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*(typedef CGPDFPageRef = (Declared(CGPDFPage))*)
 */
private val CGPDFPageGetDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFPageGetDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFPageGetDictionary").orElseThrow()
private val CGPDFPageGetDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFPageGetDictionary_ADDR, CGPDFPageGetDictionary_DESC)

fun CGPDFPageGetDictionary(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFPageGetDictionary_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFPageGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGPDFPageGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGPDFPageGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFPageGetTypeID").orElseThrow()
private val CGPDFPageGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFPageGetTypeID_ADDR, CGPDFPageGetTypeID_DESC)

fun CGPDFPageGetTypeID(): Long {
    try {
        return CGPDFPageGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGPDFOutlineTitle typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFOutlineTitle_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFOutlineTitle_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFOutlineTitle").orElseThrow() }
private val kCGPDFOutlineTitle_VH: VarHandle by lazy { kCGPDFOutlineTitle_LAYOUT.varHandle() }

var kCGPDFOutlineTitle: MemorySegment
    get() = kCGPDFOutlineTitle_VH.get(kCGPDFOutlineTitle_SEGMENT) as MemorySegment
    set(value) = kCGPDFOutlineTitle_VH.set(kCGPDFOutlineTitle_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFOutlineChildren typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFOutlineChildren_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFOutlineChildren_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFOutlineChildren").orElseThrow() }
private val kCGPDFOutlineChildren_VH: VarHandle by lazy { kCGPDFOutlineChildren_LAYOUT.varHandle() }

var kCGPDFOutlineChildren: MemorySegment
    get() = kCGPDFOutlineChildren_VH.get(kCGPDFOutlineChildren_SEGMENT) as MemorySegment
    set(value) = kCGPDFOutlineChildren_VH.set(kCGPDFOutlineChildren_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFOutlineDestination typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFOutlineDestination_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFOutlineDestination_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFOutlineDestination").orElseThrow() }
private val kCGPDFOutlineDestination_VH: VarHandle by lazy { kCGPDFOutlineDestination_LAYOUT.varHandle() }

var kCGPDFOutlineDestination: MemorySegment
    get() = kCGPDFOutlineDestination_VH.get(kCGPDFOutlineDestination_SEGMENT) as MemorySegment
    set(value) = kCGPDFOutlineDestination_VH.set(kCGPDFOutlineDestination_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFOutlineDestinationRect typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFOutlineDestinationRect_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFOutlineDestinationRect_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFOutlineDestinationRect").orElseThrow() }
private val kCGPDFOutlineDestinationRect_VH: VarHandle by lazy { kCGPDFOutlineDestinationRect_LAYOUT.varHandle() }

var kCGPDFOutlineDestinationRect: MemorySegment
    get() = kCGPDFOutlineDestinationRect_VH.get(kCGPDFOutlineDestinationRect_SEGMENT) as MemorySegment
    set(value) = kCGPDFOutlineDestinationRect_VH.set(kCGPDFOutlineDestinationRect_SEGMENT, value)

/**
 * {@snippet lang=c : CGPDFDocumentCreateWithProvider typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*(typedef CGDataProviderRef = (Declared(CGDataProvider))*)
 */
private val CGPDFDocumentCreateWithProvider_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDocumentCreateWithProvider_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentCreateWithProvider").orElseThrow()
private val CGPDFDocumentCreateWithProvider_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentCreateWithProvider_ADDR, CGPDFDocumentCreateWithProvider_DESC)

fun CGPDFDocumentCreateWithProvider(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFDocumentCreateWithProvider_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentCreateWithURL typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CGPDFDocumentCreateWithURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDocumentCreateWithURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentCreateWithURL").orElseThrow()
private val CGPDFDocumentCreateWithURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentCreateWithURL_ADDR, CGPDFDocumentCreateWithURL_DESC)

fun CGPDFDocumentCreateWithURL(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFDocumentCreateWithURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentRetain typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDocumentRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentRetain").orElseThrow()
private val CGPDFDocumentRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentRetain_ADDR, CGPDFDocumentRetain_DESC)

fun CGPDFDocumentRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFDocumentRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentRelease Void(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPDFDocumentRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentRelease").orElseThrow()
private val CGPDFDocumentRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentRelease_ADDR, CGPDFDocumentRelease_DESC)

fun CGPDFDocumentRelease(arg0: MemorySegment): Unit {
    try {
        CGPDFDocumentRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetVersion Void(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,(Int)*,(Int)*)
 */
private val CGPDFDocumentGetVersion_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDocumentGetVersion_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetVersion").orElseThrow()
private val CGPDFDocumentGetVersion_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetVersion_ADDR, CGPDFDocumentGetVersion_DESC)

fun CGPDFDocumentGetVersion(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPDFDocumentGetVersion_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentIsEncrypted Bool(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentIsEncrypted_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGPDFDocumentIsEncrypted_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentIsEncrypted").orElseThrow()
private val CGPDFDocumentIsEncrypted_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentIsEncrypted_ADDR, CGPDFDocumentIsEncrypted_DESC)

fun CGPDFDocumentIsEncrypted(arg0: MemorySegment): Boolean {
    try {
        return CGPDFDocumentIsEncrypted_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentUnlockWithPassword Bool(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,(Char)*)
 */
private val CGPDFDocumentUnlockWithPassword_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDocumentUnlockWithPassword_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentUnlockWithPassword").orElseThrow()
private val CGPDFDocumentUnlockWithPassword_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentUnlockWithPassword_ADDR, CGPDFDocumentUnlockWithPassword_DESC)

fun CGPDFDocumentUnlockWithPassword(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFDocumentUnlockWithPassword_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentIsUnlocked Bool(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentIsUnlocked_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGPDFDocumentIsUnlocked_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentIsUnlocked").orElseThrow()
private val CGPDFDocumentIsUnlocked_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentIsUnlocked_ADDR, CGPDFDocumentIsUnlocked_DESC)

fun CGPDFDocumentIsUnlocked(arg0: MemorySegment): Boolean {
    try {
        return CGPDFDocumentIsUnlocked_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentAllowsPrinting Bool(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentAllowsPrinting_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGPDFDocumentAllowsPrinting_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentAllowsPrinting").orElseThrow()
private val CGPDFDocumentAllowsPrinting_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentAllowsPrinting_ADDR, CGPDFDocumentAllowsPrinting_DESC)

fun CGPDFDocumentAllowsPrinting(arg0: MemorySegment): Boolean {
    try {
        return CGPDFDocumentAllowsPrinting_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentAllowsCopying Bool(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentAllowsCopying_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGPDFDocumentAllowsCopying_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentAllowsCopying").orElseThrow()
private val CGPDFDocumentAllowsCopying_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentAllowsCopying_ADDR, CGPDFDocumentAllowsCopying_DESC)

fun CGPDFDocumentAllowsCopying(arg0: MemorySegment): Boolean {
    try {
        return CGPDFDocumentAllowsCopying_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetNumberOfPages typedef size_t = UNSIGNED = Long(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentGetNumberOfPages_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGPDFDocumentGetNumberOfPages_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetNumberOfPages").orElseThrow()
private val CGPDFDocumentGetNumberOfPages_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetNumberOfPages_ADDR, CGPDFDocumentGetNumberOfPages_DESC)

fun CGPDFDocumentGetNumberOfPages(arg0: MemorySegment): Long {
    try {
        return CGPDFDocumentGetNumberOfPages_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetPage typedef CGPDFPageRef = (Declared(CGPDFPage))*(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,typedef size_t = UNSIGNED = Long)
 */
private val CGPDFDocumentGetPage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGPDFDocumentGetPage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetPage").orElseThrow()
private val CGPDFDocumentGetPage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetPage_ADDR, CGPDFDocumentGetPage_DESC)

fun CGPDFDocumentGetPage(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return CGPDFDocumentGetPage_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetCatalog typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentGetCatalog_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDocumentGetCatalog_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetCatalog").orElseThrow()
private val CGPDFDocumentGetCatalog_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetCatalog_ADDR, CGPDFDocumentGetCatalog_DESC)

fun CGPDFDocumentGetCatalog(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFDocumentGetCatalog_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetInfo typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentGetInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDocumentGetInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetInfo").orElseThrow()
private val CGPDFDocumentGetInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetInfo_ADDR, CGPDFDocumentGetInfo_DESC)

fun CGPDFDocumentGetInfo(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFDocumentGetInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetID typedef CGPDFArrayRef = (Declared(CGPDFArray))*(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentGetID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDocumentGetID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetID").orElseThrow()
private val CGPDFDocumentGetID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetID_ADDR, CGPDFDocumentGetID_DESC)

fun CGPDFDocumentGetID(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFDocumentGetID_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGPDFDocumentGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGPDFDocumentGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetTypeID").orElseThrow()
private val CGPDFDocumentGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetTypeID_ADDR, CGPDFDocumentGetTypeID_DESC)

fun CGPDFDocumentGetTypeID(): Long {
    try {
        return CGPDFDocumentGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetOutline typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*)
 */
private val CGPDFDocumentGetOutline_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFDocumentGetOutline_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetOutline").orElseThrow()
private val CGPDFDocumentGetOutline_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetOutline_ADDR, CGPDFDocumentGetOutline_DESC)

fun CGPDFDocumentGetOutline(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFDocumentGetOutline_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetMediaBox typedef CGRect = Declared(CGRect)(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,Int)
 */
private val CGPDFDocumentGetMediaBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGPDFDocumentGetMediaBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetMediaBox").orElseThrow()
private val CGPDFDocumentGetMediaBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetMediaBox_ADDR, CGPDFDocumentGetMediaBox_DESC)

fun CGPDFDocumentGetMediaBox(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return CGPDFDocumentGetMediaBox_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetCropBox typedef CGRect = Declared(CGRect)(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,Int)
 */
private val CGPDFDocumentGetCropBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGPDFDocumentGetCropBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetCropBox").orElseThrow()
private val CGPDFDocumentGetCropBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetCropBox_ADDR, CGPDFDocumentGetCropBox_DESC)

fun CGPDFDocumentGetCropBox(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return CGPDFDocumentGetCropBox_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetBleedBox typedef CGRect = Declared(CGRect)(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,Int)
 */
private val CGPDFDocumentGetBleedBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGPDFDocumentGetBleedBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetBleedBox").orElseThrow()
private val CGPDFDocumentGetBleedBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetBleedBox_ADDR, CGPDFDocumentGetBleedBox_DESC)

fun CGPDFDocumentGetBleedBox(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return CGPDFDocumentGetBleedBox_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetTrimBox typedef CGRect = Declared(CGRect)(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,Int)
 */
private val CGPDFDocumentGetTrimBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGPDFDocumentGetTrimBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetTrimBox").orElseThrow()
private val CGPDFDocumentGetTrimBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetTrimBox_ADDR, CGPDFDocumentGetTrimBox_DESC)

fun CGPDFDocumentGetTrimBox(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return CGPDFDocumentGetTrimBox_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetArtBox typedef CGRect = Declared(CGRect)(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,Int)
 */
private val CGPDFDocumentGetArtBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGPDFDocumentGetArtBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetArtBox").orElseThrow()
private val CGPDFDocumentGetArtBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetArtBox_ADDR, CGPDFDocumentGetArtBox_DESC)

fun CGPDFDocumentGetArtBox(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return CGPDFDocumentGetArtBox_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFDocumentGetRotationAngle Int(typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,Int)
 */
private val CGPDFDocumentGetRotationAngle_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGPDFDocumentGetRotationAngle_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFDocumentGetRotationAngle").orElseThrow()
private val CGPDFDocumentGetRotationAngle_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFDocumentGetRotationAngle_ADDR, CGPDFDocumentGetRotationAngle_DESC)

fun CGPDFDocumentGetRotationAngle(arg0: MemorySegment, arg1: Int): Int {
    try {
        return CGPDFDocumentGetRotationAngle_HANDLE.invokeExact(arg0, arg1) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFunctionGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGFunctionGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGFunctionGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFunctionGetTypeID").orElseThrow()
private val CGFunctionGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFunctionGetTypeID_ADDR, CGFunctionGetTypeID_DESC)

fun CGFunctionGetTypeID(): Long {
    try {
        return CGFunctionGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFunctionCreate typedef CGFunctionRef = (Declared(CGFunction))*((Void)*,typedef size_t = UNSIGNED = Long,(typedef CGFloat = Double)*,typedef size_t = UNSIGNED = Long,(typedef CGFloat = Double)*,(typedef CGFunctionCallbacks = Declared(CGFunctionCallbacks))*)
 */
private val CGFunctionCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFunctionCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFunctionCreate").orElseThrow()
private val CGFunctionCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFunctionCreate_ADDR, CGFunctionCreate_DESC)

fun CGFunctionCreate(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: Long, arg4: MemorySegment, arg5: MemorySegment): MemorySegment {
    try {
        return CGFunctionCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFunctionRetain typedef CGFunctionRef = (Declared(CGFunction))*(typedef CGFunctionRef = (Declared(CGFunction))*)
 */
private val CGFunctionRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFunctionRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFunctionRetain").orElseThrow()
private val CGFunctionRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFunctionRetain_ADDR, CGFunctionRetain_DESC)

fun CGFunctionRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGFunctionRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFunctionRelease Void(typedef CGFunctionRef = (Declared(CGFunction))*)
 */
private val CGFunctionRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGFunctionRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFunctionRelease").orElseThrow()
private val CGFunctionRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFunctionRelease_ADDR, CGFunctionRelease_DESC)

fun CGFunctionRelease(arg0: MemorySegment): Unit {
    try {
        CGFunctionRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShadingGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGShadingGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGShadingGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShadingGetTypeID").orElseThrow()
private val CGShadingGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShadingGetTypeID_ADDR, CGShadingGetTypeID_DESC)

fun CGShadingGetTypeID(): Long {
    try {
        return CGShadingGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShadingCreateAxial typedef CGShadingRef = (Declared(CGShading))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CGPoint = Declared(CGPoint),typedef CGPoint = Declared(CGPoint),typedef CGFunctionRef = (Declared(CGFunction))*,Bool,Bool)
 */
private val CGShadingCreateAxial_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGPoint.layout, CGPoint.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_BOOLEAN)
private val CGShadingCreateAxial_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShadingCreateAxial").orElseThrow()
private val CGShadingCreateAxial_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShadingCreateAxial_ADDR, CGShadingCreateAxial_DESC)

fun CGShadingCreateAxial(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: Boolean, arg5: Boolean): MemorySegment {
    try {
        return CGShadingCreateAxial_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShadingCreateAxialWithContentHeadroom typedef CGShadingRef = (Declared(CGShading))*(Float,typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CGPoint = Declared(CGPoint),typedef CGPoint = Declared(CGPoint),typedef CGFunctionRef = (Declared(CGFunction))*,Bool,Bool)
 */
private val CGShadingCreateAxialWithContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS, CGPoint.layout, CGPoint.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_BOOLEAN)
private val CGShadingCreateAxialWithContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShadingCreateAxialWithContentHeadroom").orElseThrow()
private val CGShadingCreateAxialWithContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShadingCreateAxialWithContentHeadroom_ADDR, CGShadingCreateAxialWithContentHeadroom_DESC)

fun CGShadingCreateAxialWithContentHeadroom(arg0: Float, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: Boolean, arg6: Boolean): MemorySegment {
    try {
        return CGShadingCreateAxialWithContentHeadroom_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShadingCreateRadial typedef CGShadingRef = (Declared(CGShading))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CGPoint = Declared(CGPoint),typedef CGFloat = Double,typedef CGPoint = Declared(CGPoint),typedef CGFloat = Double,typedef CGFunctionRef = (Declared(CGFunction))*,Bool,Bool)
 */
private val CGShadingCreateRadial_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGPoint.layout, ValueLayout.JAVA_DOUBLE, CGPoint.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_BOOLEAN)
private val CGShadingCreateRadial_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShadingCreateRadial").orElseThrow()
private val CGShadingCreateRadial_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShadingCreateRadial_ADDR, CGShadingCreateRadial_DESC)

fun CGShadingCreateRadial(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: MemorySegment, arg4: Double, arg5: MemorySegment, arg6: Boolean, arg7: Boolean): MemorySegment {
    try {
        return CGShadingCreateRadial_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShadingCreateRadialWithContentHeadroom typedef CGShadingRef = (Declared(CGShading))*(Float,typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CGPoint = Declared(CGPoint),typedef CGFloat = Double,typedef CGPoint = Declared(CGPoint),typedef CGFloat = Double,typedef CGFunctionRef = (Declared(CGFunction))*,Bool,Bool)
 */
private val CGShadingCreateRadialWithContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS, CGPoint.layout, ValueLayout.JAVA_DOUBLE, CGPoint.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_BOOLEAN)
private val CGShadingCreateRadialWithContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShadingCreateRadialWithContentHeadroom").orElseThrow()
private val CGShadingCreateRadialWithContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShadingCreateRadialWithContentHeadroom_ADDR, CGShadingCreateRadialWithContentHeadroom_DESC)

fun CGShadingCreateRadialWithContentHeadroom(arg0: Float, arg1: MemorySegment, arg2: MemorySegment, arg3: Double, arg4: MemorySegment, arg5: Double, arg6: MemorySegment, arg7: Boolean, arg8: Boolean): MemorySegment {
    try {
        return CGShadingCreateRadialWithContentHeadroom_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShadingRetain typedef CGShadingRef = (Declared(CGShading))*(typedef CGShadingRef = (Declared(CGShading))*)
 */
private val CGShadingRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGShadingRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShadingRetain").orElseThrow()
private val CGShadingRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShadingRetain_ADDR, CGShadingRetain_DESC)

fun CGShadingRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGShadingRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShadingRelease Void(typedef CGShadingRef = (Declared(CGShading))*)
 */
private val CGShadingRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGShadingRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShadingRelease").orElseThrow()
private val CGShadingRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShadingRelease_ADDR, CGShadingRelease_DESC)

fun CGShadingRelease(arg0: MemorySegment): Unit {
    try {
        CGShadingRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShadingGetContentHeadroom Float(typedef CGShadingRef = (Declared(CGShading))*)
 */
private val CGShadingGetContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS)
private val CGShadingGetContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShadingGetContentHeadroom").orElseThrow()
private val CGShadingGetContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShadingGetContentHeadroom_ADDR, CGShadingGetContentHeadroom_DESC)

fun CGShadingGetContentHeadroom(arg0: MemorySegment): Float {
    try {
        return CGShadingGetContentHeadroom_HANDLE.invokeExact(arg0) as Float
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGEXRToneMappingGammaDefog typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGEXRToneMappingGammaDefog_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGEXRToneMappingGammaDefog_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGEXRToneMappingGammaDefog").orElseThrow() }
private val kCGEXRToneMappingGammaDefog_VH: VarHandle by lazy { kCGEXRToneMappingGammaDefog_LAYOUT.varHandle() }

var kCGEXRToneMappingGammaDefog: MemorySegment
    get() = kCGEXRToneMappingGammaDefog_VH.get(kCGEXRToneMappingGammaDefog_SEGMENT) as MemorySegment
    set(value) = kCGEXRToneMappingGammaDefog_VH.set(kCGEXRToneMappingGammaDefog_SEGMENT, value)

/**
 * {@snippet lang=c : kCGEXRToneMappingGammaExposure typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGEXRToneMappingGammaExposure_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGEXRToneMappingGammaExposure_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGEXRToneMappingGammaExposure").orElseThrow() }
private val kCGEXRToneMappingGammaExposure_VH: VarHandle by lazy { kCGEXRToneMappingGammaExposure_LAYOUT.varHandle() }

var kCGEXRToneMappingGammaExposure: MemorySegment
    get() = kCGEXRToneMappingGammaExposure_VH.get(kCGEXRToneMappingGammaExposure_SEGMENT) as MemorySegment
    set(value) = kCGEXRToneMappingGammaExposure_VH.set(kCGEXRToneMappingGammaExposure_SEGMENT, value)

/**
 * {@snippet lang=c : kCGEXRToneMappingGammaKneeLow typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGEXRToneMappingGammaKneeLow_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGEXRToneMappingGammaKneeLow_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGEXRToneMappingGammaKneeLow").orElseThrow() }
private val kCGEXRToneMappingGammaKneeLow_VH: VarHandle by lazy { kCGEXRToneMappingGammaKneeLow_LAYOUT.varHandle() }

var kCGEXRToneMappingGammaKneeLow: MemorySegment
    get() = kCGEXRToneMappingGammaKneeLow_VH.get(kCGEXRToneMappingGammaKneeLow_SEGMENT) as MemorySegment
    set(value) = kCGEXRToneMappingGammaKneeLow_VH.set(kCGEXRToneMappingGammaKneeLow_SEGMENT, value)

/**
 * {@snippet lang=c : kCGEXRToneMappingGammaKneeHigh typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGEXRToneMappingGammaKneeHigh_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGEXRToneMappingGammaKneeHigh_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGEXRToneMappingGammaKneeHigh").orElseThrow() }
private val kCGEXRToneMappingGammaKneeHigh_VH: VarHandle by lazy { kCGEXRToneMappingGammaKneeHigh_LAYOUT.varHandle() }

var kCGEXRToneMappingGammaKneeHigh: MemorySegment
    get() = kCGEXRToneMappingGammaKneeHigh_VH.get(kCGEXRToneMappingGammaKneeHigh_SEGMENT) as MemorySegment
    set(value) = kCGEXRToneMappingGammaKneeHigh_VH.set(kCGEXRToneMappingGammaKneeHigh_SEGMENT, value)

/**
 * {@snippet lang=c : CGEXRToneMappingGammaGetDefaultOptions typedef CFDictionaryRef = (Declared(__CFDictionary))*()
 */
private val CGEXRToneMappingGammaGetDefaultOptions_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CGEXRToneMappingGammaGetDefaultOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGEXRToneMappingGammaGetDefaultOptions").orElseThrow()
private val CGEXRToneMappingGammaGetDefaultOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGEXRToneMappingGammaGetDefaultOptions_ADDR, CGEXRToneMappingGammaGetDefaultOptions_DESC)

fun CGEXRToneMappingGammaGetDefaultOptions(): MemorySegment {
    try {
        return CGEXRToneMappingGammaGetDefaultOptions_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGUse100nitsHLGOOTF typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGUse100nitsHLGOOTF_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGUse100nitsHLGOOTF_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGUse100nitsHLGOOTF").orElseThrow() }
private val kCGUse100nitsHLGOOTF_VH: VarHandle by lazy { kCGUse100nitsHLGOOTF_LAYOUT.varHandle() }

var kCGUse100nitsHLGOOTF: MemorySegment
    get() = kCGUse100nitsHLGOOTF_VH.get(kCGUse100nitsHLGOOTF_SEGMENT) as MemorySegment
    set(value) = kCGUse100nitsHLGOOTF_VH.set(kCGUse100nitsHLGOOTF_SEGMENT, value)

/**
 * {@snippet lang=c : kCGUseBT1886ForCoreVideoGamma typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGUseBT1886ForCoreVideoGamma_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGUseBT1886ForCoreVideoGamma_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGUseBT1886ForCoreVideoGamma").orElseThrow() }
private val kCGUseBT1886ForCoreVideoGamma_VH: VarHandle by lazy { kCGUseBT1886ForCoreVideoGamma_LAYOUT.varHandle() }

var kCGUseBT1886ForCoreVideoGamma: MemorySegment
    get() = kCGUseBT1886ForCoreVideoGamma_VH.get(kCGUseBT1886ForCoreVideoGamma_SEGMENT) as MemorySegment
    set(value) = kCGUseBT1886ForCoreVideoGamma_VH.set(kCGUseBT1886ForCoreVideoGamma_SEGMENT, value)

/**
 * {@snippet lang=c : kCGSkipBoostToHDR typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGSkipBoostToHDR_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGSkipBoostToHDR_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGSkipBoostToHDR").orElseThrow() }
private val kCGSkipBoostToHDR_VH: VarHandle by lazy { kCGSkipBoostToHDR_LAYOUT.varHandle() }

var kCGSkipBoostToHDR: MemorySegment
    get() = kCGSkipBoostToHDR_VH.get(kCGSkipBoostToHDR_SEGMENT) as MemorySegment
    set(value) = kCGSkipBoostToHDR_VH.set(kCGSkipBoostToHDR_SEGMENT, value)

/**
 * {@snippet lang=c : kCGUseLegacyHDREcosystem typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGUseLegacyHDREcosystem_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGUseLegacyHDREcosystem_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGUseLegacyHDREcosystem").orElseThrow() }
private val kCGUseLegacyHDREcosystem_VH: VarHandle by lazy { kCGUseLegacyHDREcosystem_LAYOUT.varHandle() }

var kCGUseLegacyHDREcosystem: MemorySegment
    get() = kCGUseLegacyHDREcosystem_VH.get(kCGUseLegacyHDREcosystem_SEGMENT) as MemorySegment
    set(value) = kCGUseLegacyHDREcosystem_VH.set(kCGUseLegacyHDREcosystem_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPreferredDynamicRange typedef CFStringRef = (Declared(__CFString))*
 */
private val kCGPreferredDynamicRange_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPreferredDynamicRange_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPreferredDynamicRange").orElseThrow() }
private val kCGPreferredDynamicRange_VH: VarHandle by lazy { kCGPreferredDynamicRange_LAYOUT.varHandle() }

var kCGPreferredDynamicRange: MemorySegment
    get() = kCGPreferredDynamicRange_VH.get(kCGPreferredDynamicRange_SEGMENT) as MemorySegment
    set(value) = kCGPreferredDynamicRange_VH.set(kCGPreferredDynamicRange_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDynamicRangeHigh typedef CFStringRef = (Declared(__CFString))*
 */
private val kCGDynamicRangeHigh_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDynamicRangeHigh_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDynamicRangeHigh").orElseThrow() }
private val kCGDynamicRangeHigh_VH: VarHandle by lazy { kCGDynamicRangeHigh_LAYOUT.varHandle() }

var kCGDynamicRangeHigh: MemorySegment
    get() = kCGDynamicRangeHigh_VH.get(kCGDynamicRangeHigh_SEGMENT) as MemorySegment
    set(value) = kCGDynamicRangeHigh_VH.set(kCGDynamicRangeHigh_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDynamicRangeConstrained typedef CFStringRef = (Declared(__CFString))*
 */
private val kCGDynamicRangeConstrained_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDynamicRangeConstrained_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDynamicRangeConstrained").orElseThrow() }
private val kCGDynamicRangeConstrained_VH: VarHandle by lazy { kCGDynamicRangeConstrained_LAYOUT.varHandle() }

var kCGDynamicRangeConstrained: MemorySegment
    get() = kCGDynamicRangeConstrained_VH.get(kCGDynamicRangeConstrained_SEGMENT) as MemorySegment
    set(value) = kCGDynamicRangeConstrained_VH.set(kCGDynamicRangeConstrained_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDynamicRangeStandard typedef CFStringRef = (Declared(__CFString))*
 */
private val kCGDynamicRangeStandard_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDynamicRangeStandard_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDynamicRangeStandard").orElseThrow() }
private val kCGDynamicRangeStandard_VH: VarHandle by lazy { kCGDynamicRangeStandard_LAYOUT.varHandle() }

var kCGDynamicRangeStandard: MemorySegment
    get() = kCGDynamicRangeStandard_VH.get(kCGDynamicRangeStandard_SEGMENT) as MemorySegment
    set(value) = kCGDynamicRangeStandard_VH.set(kCGDynamicRangeStandard_SEGMENT, value)

/**
 * {@snippet lang=c : kCGContentAverageLightLevel typedef CFStringRef = (Declared(__CFString))*
 */
private val kCGContentAverageLightLevel_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGContentAverageLightLevel_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGContentAverageLightLevel").orElseThrow() }
private val kCGContentAverageLightLevel_VH: VarHandle by lazy { kCGContentAverageLightLevel_LAYOUT.varHandle() }

var kCGContentAverageLightLevel: MemorySegment
    get() = kCGContentAverageLightLevel_VH.get(kCGContentAverageLightLevel_SEGMENT) as MemorySegment
    set(value) = kCGContentAverageLightLevel_VH.set(kCGContentAverageLightLevel_SEGMENT, value)

/**
 * {@snippet lang=c : kCGContentAverageLightLevelNits typedef CFStringRef = (Declared(__CFString))*
 */
private val kCGContentAverageLightLevelNits_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGContentAverageLightLevelNits_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGContentAverageLightLevelNits").orElseThrow() }
private val kCGContentAverageLightLevelNits_VH: VarHandle by lazy { kCGContentAverageLightLevelNits_LAYOUT.varHandle() }

var kCGContentAverageLightLevelNits: MemorySegment
    get() = kCGContentAverageLightLevelNits_VH.get(kCGContentAverageLightLevelNits_SEGMENT) as MemorySegment
    set(value) = kCGContentAverageLightLevelNits_VH.set(kCGContentAverageLightLevelNits_SEGMENT, value)

/**
 * {@snippet lang=c : CGContextGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGContextGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGContextGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextGetTypeID").orElseThrow()
private val CGContextGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextGetTypeID_ADDR, CGContextGetTypeID_DESC)

fun CGContextGetTypeID(): Long {
    try {
        return CGContextGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSaveGState Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextSaveGState_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextSaveGState_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSaveGState").orElseThrow()
private val CGContextSaveGState_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSaveGState_ADDR, CGContextSaveGState_DESC)

fun CGContextSaveGState(arg0: MemorySegment): Unit {
    try {
        CGContextSaveGState_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextRestoreGState Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextRestoreGState_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextRestoreGState_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextRestoreGState").orElseThrow()
private val CGContextRestoreGState_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextRestoreGState_ADDR, CGContextRestoreGState_DESC)

fun CGContextRestoreGState(arg0: MemorySegment): Unit {
    try {
        CGContextRestoreGState_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextScaleCTM Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextScaleCTM_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextScaleCTM_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextScaleCTM").orElseThrow()
private val CGContextScaleCTM_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextScaleCTM_ADDR, CGContextScaleCTM_DESC)

fun CGContextScaleCTM(arg0: MemorySegment, arg1: Double, arg2: Double): Unit {
    try {
        CGContextScaleCTM_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextTranslateCTM Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextTranslateCTM_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextTranslateCTM_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextTranslateCTM").orElseThrow()
private val CGContextTranslateCTM_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextTranslateCTM_ADDR, CGContextTranslateCTM_DESC)

fun CGContextTranslateCTM(arg0: MemorySegment, arg1: Double, arg2: Double): Unit {
    try {
        CGContextTranslateCTM_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextRotateCTM Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double)
 */
private val CGContextRotateCTM_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGContextRotateCTM_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextRotateCTM").orElseThrow()
private val CGContextRotateCTM_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextRotateCTM_ADDR, CGContextRotateCTM_DESC)

fun CGContextRotateCTM(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CGContextRotateCTM_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetLineWidth Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double)
 */
private val CGContextSetLineWidth_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGContextSetLineWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetLineWidth").orElseThrow()
private val CGContextSetLineWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetLineWidth_ADDR, CGContextSetLineWidth_DESC)

fun CGContextSetLineWidth(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CGContextSetLineWidth_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetMiterLimit Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double)
 */
private val CGContextSetMiterLimit_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGContextSetMiterLimit_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetMiterLimit").orElseThrow()
private val CGContextSetMiterLimit_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetMiterLimit_ADDR, CGContextSetMiterLimit_DESC)

fun CGContextSetMiterLimit(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CGContextSetMiterLimit_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetLineDash Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,(typedef CGFloat = Double)*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextSetLineDash_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextSetLineDash_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetLineDash").orElseThrow()
private val CGContextSetLineDash_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetLineDash_ADDR, CGContextSetLineDash_DESC)

fun CGContextSetLineDash(arg0: MemorySegment, arg1: Double, arg2: MemorySegment, arg3: Long): Unit {
    try {
        CGContextSetLineDash_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetFlatness Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double)
 */
private val CGContextSetFlatness_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGContextSetFlatness_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetFlatness").orElseThrow()
private val CGContextSetFlatness_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetFlatness_ADDR, CGContextSetFlatness_DESC)

fun CGContextSetFlatness(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CGContextSetFlatness_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetAlpha Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double)
 */
private val CGContextSetAlpha_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGContextSetAlpha_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetAlpha").orElseThrow()
private val CGContextSetAlpha_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetAlpha_ADDR, CGContextSetAlpha_DESC)

fun CGContextSetAlpha(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CGContextSetAlpha_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextBeginPath Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextBeginPath_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextBeginPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextBeginPath").orElseThrow()
private val CGContextBeginPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextBeginPath_ADDR, CGContextBeginPath_DESC)

fun CGContextBeginPath(arg0: MemorySegment): Unit {
    try {
        CGContextBeginPath_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextMoveToPoint Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextMoveToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextMoveToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextMoveToPoint").orElseThrow()
private val CGContextMoveToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextMoveToPoint_ADDR, CGContextMoveToPoint_DESC)

fun CGContextMoveToPoint(arg0: MemorySegment, arg1: Double, arg2: Double): Unit {
    try {
        CGContextMoveToPoint_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddLineToPoint Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextAddLineToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextAddLineToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddLineToPoint").orElseThrow()
private val CGContextAddLineToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddLineToPoint_ADDR, CGContextAddLineToPoint_DESC)

fun CGContextAddLineToPoint(arg0: MemorySegment, arg1: Double, arg2: Double): Unit {
    try {
        CGContextAddLineToPoint_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddCurveToPoint Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextAddCurveToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextAddCurveToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddCurveToPoint").orElseThrow()
private val CGContextAddCurveToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddCurveToPoint_ADDR, CGContextAddCurveToPoint_DESC)

fun CGContextAddCurveToPoint(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double, arg4: Double, arg5: Double, arg6: Double): Unit {
    try {
        CGContextAddCurveToPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddQuadCurveToPoint Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextAddQuadCurveToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextAddQuadCurveToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddQuadCurveToPoint").orElseThrow()
private val CGContextAddQuadCurveToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddQuadCurveToPoint_ADDR, CGContextAddQuadCurveToPoint_DESC)

fun CGContextAddQuadCurveToPoint(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double, arg4: Double): Unit {
    try {
        CGContextAddQuadCurveToPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextClosePath Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextClosePath_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextClosePath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextClosePath").orElseThrow()
private val CGContextClosePath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextClosePath_ADDR, CGContextClosePath_DESC)

fun CGContextClosePath(arg0: MemorySegment): Unit {
    try {
        CGContextClosePath_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextAddRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout)
private val CGContextAddRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddRect").orElseThrow()
private val CGContextAddRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddRect_ADDR, CGContextAddRect_DESC)

fun CGContextAddRect(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextAddRect_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddRects Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGRect = Declared(CGRect))*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextAddRects_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextAddRects_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddRects").orElseThrow()
private val CGContextAddRects_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddRects_ADDR, CGContextAddRects_DESC)

fun CGContextAddRects(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        CGContextAddRects_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddLines Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGPoint = Declared(CGPoint))*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextAddLines_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextAddLines_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddLines").orElseThrow()
private val CGContextAddLines_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddLines_ADDR, CGContextAddLines_DESC)

fun CGContextAddLines(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        CGContextAddLines_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddEllipseInRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextAddEllipseInRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout)
private val CGContextAddEllipseInRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddEllipseInRect").orElseThrow()
private val CGContextAddEllipseInRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddEllipseInRect_ADDR, CGContextAddEllipseInRect_DESC)

fun CGContextAddEllipseInRect(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextAddEllipseInRect_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddArc Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,Int)
 */
private val CGContextAddArc_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_INT)
private val CGContextAddArc_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddArc").orElseThrow()
private val CGContextAddArc_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddArc_ADDR, CGContextAddArc_DESC)

fun CGContextAddArc(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double, arg4: Double, arg5: Double, arg6: Int): Unit {
    try {
        CGContextAddArc_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddArcToPoint Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextAddArcToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextAddArcToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddArcToPoint").orElseThrow()
private val CGContextAddArcToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddArcToPoint_ADDR, CGContextAddArcToPoint_DESC)

fun CGContextAddArcToPoint(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double, arg4: Double, arg5: Double): Unit {
    try {
        CGContextAddArcToPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextAddPath Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGContextAddPath_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextAddPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextAddPath").orElseThrow()
private val CGContextAddPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextAddPath_ADDR, CGContextAddPath_DESC)

fun CGContextAddPath(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextAddPath_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextReplacePathWithStrokedPath Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextReplacePathWithStrokedPath_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextReplacePathWithStrokedPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextReplacePathWithStrokedPath").orElseThrow()
private val CGContextReplacePathWithStrokedPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextReplacePathWithStrokedPath_ADDR, CGContextReplacePathWithStrokedPath_DESC)

fun CGContextReplacePathWithStrokedPath(arg0: MemorySegment): Unit {
    try {
        CGContextReplacePathWithStrokedPath_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextIsPathEmpty Bool(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextIsPathEmpty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGContextIsPathEmpty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextIsPathEmpty").orElseThrow()
private val CGContextIsPathEmpty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextIsPathEmpty_ADDR, CGContextIsPathEmpty_DESC)

fun CGContextIsPathEmpty(arg0: MemorySegment): Boolean {
    try {
        return CGContextIsPathEmpty_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextGetPathCurrentPoint typedef CGPoint = Declared(CGPoint)(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextGetPathCurrentPoint_DESC: FunctionDescriptor = FunctionDescriptor.of(CGPoint.layout, ValueLayout.ADDRESS)
private val CGContextGetPathCurrentPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextGetPathCurrentPoint").orElseThrow()
private val CGContextGetPathCurrentPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextGetPathCurrentPoint_ADDR, CGContextGetPathCurrentPoint_DESC)

fun CGContextGetPathCurrentPoint(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGContextGetPathCurrentPoint_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextGetPathBoundingBox typedef CGRect = Declared(CGRect)(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextGetPathBoundingBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS)
private val CGContextGetPathBoundingBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextGetPathBoundingBox").orElseThrow()
private val CGContextGetPathBoundingBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextGetPathBoundingBox_ADDR, CGContextGetPathBoundingBox_DESC)

fun CGContextGetPathBoundingBox(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGContextGetPathBoundingBox_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextCopyPath typedef CGPathRef = (Declared(CGPath))*(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextCopyPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextCopyPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextCopyPath").orElseThrow()
private val CGContextCopyPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextCopyPath_ADDR, CGContextCopyPath_DESC)

fun CGContextCopyPath(arg0: MemorySegment): MemorySegment {
    try {
        return CGContextCopyPath_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextFillPath Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextFillPath_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextFillPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextFillPath").orElseThrow()
private val CGContextFillPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextFillPath_ADDR, CGContextFillPath_DESC)

fun CGContextFillPath(arg0: MemorySegment): Unit {
    try {
        CGContextFillPath_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextEOFillPath Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextEOFillPath_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextEOFillPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextEOFillPath").orElseThrow()
private val CGContextEOFillPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextEOFillPath_ADDR, CGContextEOFillPath_DESC)

fun CGContextEOFillPath(arg0: MemorySegment): Unit {
    try {
        CGContextEOFillPath_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextStrokePath Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextStrokePath_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextStrokePath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextStrokePath").orElseThrow()
private val CGContextStrokePath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextStrokePath_ADDR, CGContextStrokePath_DESC)

fun CGContextStrokePath(arg0: MemorySegment): Unit {
    try {
        CGContextStrokePath_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextFillRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextFillRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout)
private val CGContextFillRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextFillRect").orElseThrow()
private val CGContextFillRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextFillRect_ADDR, CGContextFillRect_DESC)

fun CGContextFillRect(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextFillRect_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextFillRects Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGRect = Declared(CGRect))*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextFillRects_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextFillRects_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextFillRects").orElseThrow()
private val CGContextFillRects_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextFillRects_ADDR, CGContextFillRects_DESC)

fun CGContextFillRects(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        CGContextFillRects_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextStrokeRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextStrokeRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout)
private val CGContextStrokeRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextStrokeRect").orElseThrow()
private val CGContextStrokeRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextStrokeRect_ADDR, CGContextStrokeRect_DESC)

fun CGContextStrokeRect(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextStrokeRect_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextStrokeRectWithWidth Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect),typedef CGFloat = Double)
 */
private val CGContextStrokeRectWithWidth_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.JAVA_DOUBLE)
private val CGContextStrokeRectWithWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextStrokeRectWithWidth").orElseThrow()
private val CGContextStrokeRectWithWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextStrokeRectWithWidth_ADDR, CGContextStrokeRectWithWidth_DESC)

fun CGContextStrokeRectWithWidth(arg0: MemorySegment, arg1: MemorySegment, arg2: Double): Unit {
    try {
        CGContextStrokeRectWithWidth_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextClearRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextClearRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout)
private val CGContextClearRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextClearRect").orElseThrow()
private val CGContextClearRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextClearRect_ADDR, CGContextClearRect_DESC)

fun CGContextClearRect(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextClearRect_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextFillEllipseInRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextFillEllipseInRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout)
private val CGContextFillEllipseInRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextFillEllipseInRect").orElseThrow()
private val CGContextFillEllipseInRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextFillEllipseInRect_ADDR, CGContextFillEllipseInRect_DESC)

fun CGContextFillEllipseInRect(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextFillEllipseInRect_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextStrokeEllipseInRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextStrokeEllipseInRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout)
private val CGContextStrokeEllipseInRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextStrokeEllipseInRect").orElseThrow()
private val CGContextStrokeEllipseInRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextStrokeEllipseInRect_ADDR, CGContextStrokeEllipseInRect_DESC)

fun CGContextStrokeEllipseInRect(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextStrokeEllipseInRect_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextStrokeLineSegments Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGPoint = Declared(CGPoint))*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextStrokeLineSegments_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextStrokeLineSegments_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextStrokeLineSegments").orElseThrow()
private val CGContextStrokeLineSegments_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextStrokeLineSegments_ADDR, CGContextStrokeLineSegments_DESC)

fun CGContextStrokeLineSegments(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        CGContextStrokeLineSegments_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextClip Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextClip_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextClip_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextClip").orElseThrow()
private val CGContextClip_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextClip_ADDR, CGContextClip_DESC)

fun CGContextClip(arg0: MemorySegment): Unit {
    try {
        CGContextClip_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextEOClip Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextEOClip_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextEOClip_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextEOClip").orElseThrow()
private val CGContextEOClip_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextEOClip_ADDR, CGContextEOClip_DESC)

fun CGContextEOClip(arg0: MemorySegment): Unit {
    try {
        CGContextEOClip_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextResetClip Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextResetClip_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextResetClip_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextResetClip").orElseThrow()
private val CGContextResetClip_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextResetClip_ADDR, CGContextResetClip_DESC)

fun CGContextResetClip(arg0: MemorySegment): Unit {
    try {
        CGContextResetClip_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextClipToMask Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect),typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGContextClipToMask_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.ADDRESS)
private val CGContextClipToMask_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextClipToMask").orElseThrow()
private val CGContextClipToMask_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextClipToMask_ADDR, CGContextClipToMask_DESC)

fun CGContextClipToMask(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGContextClipToMask_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextGetClipBoundingBox typedef CGRect = Declared(CGRect)(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextGetClipBoundingBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS)
private val CGContextGetClipBoundingBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextGetClipBoundingBox").orElseThrow()
private val CGContextGetClipBoundingBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextGetClipBoundingBox_ADDR, CGContextGetClipBoundingBox_DESC)

fun CGContextGetClipBoundingBox(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGContextGetClipBoundingBox_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextClipToRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextClipToRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout)
private val CGContextClipToRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextClipToRect").orElseThrow()
private val CGContextClipToRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextClipToRect_ADDR, CGContextClipToRect_DESC)

fun CGContextClipToRect(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextClipToRect_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextClipToRects Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGRect = Declared(CGRect))*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextClipToRects_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextClipToRects_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextClipToRects").orElseThrow()
private val CGContextClipToRects_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextClipToRects_ADDR, CGContextClipToRects_DESC)

fun CGContextClipToRects(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        CGContextClipToRects_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetFillColorWithColor Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGContextSetFillColorWithColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextSetFillColorWithColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetFillColorWithColor").orElseThrow()
private val CGContextSetFillColorWithColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetFillColorWithColor_ADDR, CGContextSetFillColorWithColor_DESC)

fun CGContextSetFillColorWithColor(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextSetFillColorWithColor_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetStrokeColorWithColor Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGContextSetStrokeColorWithColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextSetStrokeColorWithColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetStrokeColorWithColor").orElseThrow()
private val CGContextSetStrokeColorWithColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetStrokeColorWithColor_ADDR, CGContextSetStrokeColorWithColor_DESC)

fun CGContextSetStrokeColorWithColor(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextSetStrokeColorWithColor_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetFillColorSpace Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGContextSetFillColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextSetFillColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetFillColorSpace").orElseThrow()
private val CGContextSetFillColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetFillColorSpace_ADDR, CGContextSetFillColorSpace_DESC)

fun CGContextSetFillColorSpace(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextSetFillColorSpace_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetStrokeColorSpace Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGContextSetStrokeColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextSetStrokeColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetStrokeColorSpace").orElseThrow()
private val CGContextSetStrokeColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetStrokeColorSpace_ADDR, CGContextSetStrokeColorSpace_DESC)

fun CGContextSetStrokeColorSpace(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextSetStrokeColorSpace_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetFillColor Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGFloat = Double)*)
 */
private val CGContextSetFillColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextSetFillColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetFillColor").orElseThrow()
private val CGContextSetFillColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetFillColor_ADDR, CGContextSetFillColor_DESC)

fun CGContextSetFillColor(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextSetFillColor_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetStrokeColor Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGFloat = Double)*)
 */
private val CGContextSetStrokeColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextSetStrokeColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetStrokeColor").orElseThrow()
private val CGContextSetStrokeColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetStrokeColor_ADDR, CGContextSetStrokeColor_DESC)

fun CGContextSetStrokeColor(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextSetStrokeColor_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetFillPattern Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGPatternRef = (Declared(CGPattern))*,(typedef CGFloat = Double)*)
 */
private val CGContextSetFillPattern_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextSetFillPattern_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetFillPattern").orElseThrow()
private val CGContextSetFillPattern_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetFillPattern_ADDR, CGContextSetFillPattern_DESC)

fun CGContextSetFillPattern(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGContextSetFillPattern_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetStrokePattern Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGPatternRef = (Declared(CGPattern))*,(typedef CGFloat = Double)*)
 */
private val CGContextSetStrokePattern_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextSetStrokePattern_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetStrokePattern").orElseThrow()
private val CGContextSetStrokePattern_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetStrokePattern_ADDR, CGContextSetStrokePattern_DESC)

fun CGContextSetStrokePattern(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGContextSetStrokePattern_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetPatternPhase Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGSize = Declared(CGSize))
 */
private val CGContextSetPatternPhase_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGSize.layout)
private val CGContextSetPatternPhase_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetPatternPhase").orElseThrow()
private val CGContextSetPatternPhase_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetPatternPhase_ADDR, CGContextSetPatternPhase_DESC)

fun CGContextSetPatternPhase(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextSetPatternPhase_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetGrayFillColor Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextSetGrayFillColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextSetGrayFillColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetGrayFillColor").orElseThrow()
private val CGContextSetGrayFillColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetGrayFillColor_ADDR, CGContextSetGrayFillColor_DESC)

fun CGContextSetGrayFillColor(arg0: MemorySegment, arg1: Double, arg2: Double): Unit {
    try {
        CGContextSetGrayFillColor_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetGrayStrokeColor Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextSetGrayStrokeColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextSetGrayStrokeColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetGrayStrokeColor").orElseThrow()
private val CGContextSetGrayStrokeColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetGrayStrokeColor_ADDR, CGContextSetGrayStrokeColor_DESC)

fun CGContextSetGrayStrokeColor(arg0: MemorySegment, arg1: Double, arg2: Double): Unit {
    try {
        CGContextSetGrayStrokeColor_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetRGBFillColor Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextSetRGBFillColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextSetRGBFillColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetRGBFillColor").orElseThrow()
private val CGContextSetRGBFillColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetRGBFillColor_ADDR, CGContextSetRGBFillColor_DESC)

fun CGContextSetRGBFillColor(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double, arg4: Double): Unit {
    try {
        CGContextSetRGBFillColor_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetRGBStrokeColor Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextSetRGBStrokeColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextSetRGBStrokeColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetRGBStrokeColor").orElseThrow()
private val CGContextSetRGBStrokeColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetRGBStrokeColor_ADDR, CGContextSetRGBStrokeColor_DESC)

fun CGContextSetRGBStrokeColor(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double, arg4: Double): Unit {
    try {
        CGContextSetRGBStrokeColor_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetCMYKFillColor Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextSetCMYKFillColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextSetCMYKFillColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetCMYKFillColor").orElseThrow()
private val CGContextSetCMYKFillColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetCMYKFillColor_ADDR, CGContextSetCMYKFillColor_DESC)

fun CGContextSetCMYKFillColor(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double, arg4: Double, arg5: Double): Unit {
    try {
        CGContextSetCMYKFillColor_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetCMYKStrokeColor Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextSetCMYKStrokeColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextSetCMYKStrokeColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetCMYKStrokeColor").orElseThrow()
private val CGContextSetCMYKStrokeColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetCMYKStrokeColor_ADDR, CGContextSetCMYKStrokeColor_DESC)

fun CGContextSetCMYKStrokeColor(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double, arg4: Double, arg5: Double): Unit {
    try {
        CGContextSetCMYKStrokeColor_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetEDRTargetHeadroom Bool(typedef CGContextRef = (Declared(CGContext))*,Float)
 */
private val CGContextSetEDRTargetHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_FLOAT)
private val CGContextSetEDRTargetHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetEDRTargetHeadroom").orElseThrow()
private val CGContextSetEDRTargetHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetEDRTargetHeadroom_ADDR, CGContextSetEDRTargetHeadroom_DESC)

fun CGContextSetEDRTargetHeadroom(arg0: MemorySegment, arg1: Float): Boolean {
    try {
        return CGContextSetEDRTargetHeadroom_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextGetEDRTargetHeadroom Float(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextGetEDRTargetHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS)
private val CGContextGetEDRTargetHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextGetEDRTargetHeadroom").orElseThrow()
private val CGContextGetEDRTargetHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextGetEDRTargetHeadroom_ADDR, CGContextGetEDRTargetHeadroom_DESC)

fun CGContextGetEDRTargetHeadroom(arg0: MemorySegment): Float {
    try {
        return CGContextGetEDRTargetHeadroom_HANDLE.invokeExact(arg0) as Float
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextDrawImage Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect),typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGContextDrawImage_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.ADDRESS)
private val CGContextDrawImage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextDrawImage").orElseThrow()
private val CGContextDrawImage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextDrawImage_ADDR, CGContextDrawImage_DESC)

fun CGContextDrawImage(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGContextDrawImage_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextDrawTiledImage Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect),typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGContextDrawTiledImage_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.ADDRESS)
private val CGContextDrawTiledImage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextDrawTiledImage").orElseThrow()
private val CGContextDrawTiledImage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextDrawTiledImage_ADDR, CGContextDrawTiledImage_DESC)

fun CGContextDrawTiledImage(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGContextDrawTiledImage_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextGetContentToneMappingInfo typedef CGContentToneMappingInfo = Declared(CGContentToneMappingInfo)(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextGetContentToneMappingInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(CGContentToneMappingInfo.layout, ValueLayout.ADDRESS)
private val CGContextGetContentToneMappingInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextGetContentToneMappingInfo").orElseThrow()
private val CGContextGetContentToneMappingInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextGetContentToneMappingInfo_ADDR, CGContextGetContentToneMappingInfo_DESC)

fun CGContextGetContentToneMappingInfo(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGContextGetContentToneMappingInfo_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetContentToneMappingInfo Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGContentToneMappingInfo = Declared(CGContentToneMappingInfo))
 */
private val CGContextSetContentToneMappingInfo_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGContentToneMappingInfo.layout)
private val CGContextSetContentToneMappingInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetContentToneMappingInfo").orElseThrow()
private val CGContextSetContentToneMappingInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetContentToneMappingInfo_ADDR, CGContextSetContentToneMappingInfo_DESC)

fun CGContextSetContentToneMappingInfo(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextSetContentToneMappingInfo_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetShadowWithColor Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGSize = Declared(CGSize),typedef CGFloat = Double,typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGContextSetShadowWithColor_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGSize.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CGContextSetShadowWithColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetShadowWithColor").orElseThrow()
private val CGContextSetShadowWithColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetShadowWithColor_ADDR, CGContextSetShadowWithColor_DESC)

fun CGContextSetShadowWithColor(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: MemorySegment): Unit {
    try {
        CGContextSetShadowWithColor_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetShadow Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGSize = Declared(CGSize),typedef CGFloat = Double)
 */
private val CGContextSetShadow_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGSize.layout, ValueLayout.JAVA_DOUBLE)
private val CGContextSetShadow_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetShadow").orElseThrow()
private val CGContextSetShadow_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetShadow_ADDR, CGContextSetShadow_DESC)

fun CGContextSetShadow(arg0: MemorySegment, arg1: MemorySegment, arg2: Double): Unit {
    try {
        CGContextSetShadow_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextDrawConicGradient Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGGradientRef = (Declared(CGGradient))*,typedef CGPoint = Declared(CGPoint),typedef CGFloat = Double)
 */
private val CGContextDrawConicGradient_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGPoint.layout, ValueLayout.JAVA_DOUBLE)
private val CGContextDrawConicGradient_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextDrawConicGradient").orElseThrow()
private val CGContextDrawConicGradient_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextDrawConicGradient_ADDR, CGContextDrawConicGradient_DESC)

fun CGContextDrawConicGradient(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Double): Unit {
    try {
        CGContextDrawConicGradient_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextDrawShading Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGShadingRef = (Declared(CGShading))*)
 */
private val CGContextDrawShading_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextDrawShading_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextDrawShading").orElseThrow()
private val CGContextDrawShading_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextDrawShading_ADDR, CGContextDrawShading_DESC)

fun CGContextDrawShading(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextDrawShading_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetCharacterSpacing Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double)
 */
private val CGContextSetCharacterSpacing_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGContextSetCharacterSpacing_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetCharacterSpacing").orElseThrow()
private val CGContextSetCharacterSpacing_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetCharacterSpacing_ADDR, CGContextSetCharacterSpacing_DESC)

fun CGContextSetCharacterSpacing(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CGContextSetCharacterSpacing_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetTextPosition Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGContextSetTextPosition_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGContextSetTextPosition_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetTextPosition").orElseThrow()
private val CGContextSetTextPosition_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetTextPosition_ADDR, CGContextSetTextPosition_DESC)

fun CGContextSetTextPosition(arg0: MemorySegment, arg1: Double, arg2: Double): Unit {
    try {
        CGContextSetTextPosition_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextGetTextPosition typedef CGPoint = Declared(CGPoint)(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextGetTextPosition_DESC: FunctionDescriptor = FunctionDescriptor.of(CGPoint.layout, ValueLayout.ADDRESS)
private val CGContextGetTextPosition_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextGetTextPosition").orElseThrow()
private val CGContextGetTextPosition_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextGetTextPosition_ADDR, CGContextGetTextPosition_DESC)

fun CGContextGetTextPosition(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGContextGetTextPosition_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetFont Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGContextSetFont_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextSetFont_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetFont").orElseThrow()
private val CGContextSetFont_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetFont_ADDR, CGContextSetFont_DESC)

fun CGContextSetFont(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextSetFont_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetFontSize Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double)
 */
private val CGContextSetFontSize_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGContextSetFontSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetFontSize").orElseThrow()
private val CGContextSetFontSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetFontSize_ADDR, CGContextSetFontSize_DESC)

fun CGContextSetFontSize(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CGContextSetFontSize_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextShowGlyphsAtPositions Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGGlyph = UNSIGNED = Short)*,(typedef CGPoint = Declared(CGPoint))*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextShowGlyphsAtPositions_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextShowGlyphsAtPositions_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextShowGlyphsAtPositions").orElseThrow()
private val CGContextShowGlyphsAtPositions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextShowGlyphsAtPositions_ADDR, CGContextShowGlyphsAtPositions_DESC)

fun CGContextShowGlyphsAtPositions(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long): Unit {
    try {
        CGContextShowGlyphsAtPositions_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextDrawPDFPage Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGPDFPageRef = (Declared(CGPDFPage))*)
 */
private val CGContextDrawPDFPage_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextDrawPDFPage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextDrawPDFPage").orElseThrow()
private val CGContextDrawPDFPage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextDrawPDFPage_ADDR, CGContextDrawPDFPage_DESC)

fun CGContextDrawPDFPage(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextDrawPDFPage_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextBeginPage Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGRect = Declared(CGRect))*)
 */
private val CGContextBeginPage_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextBeginPage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextBeginPage").orElseThrow()
private val CGContextBeginPage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextBeginPage_ADDR, CGContextBeginPage_DESC)

fun CGContextBeginPage(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextBeginPage_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextEndPage Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextEndPage_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextEndPage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextEndPage").orElseThrow()
private val CGContextEndPage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextEndPage_ADDR, CGContextEndPage_DESC)

fun CGContextEndPage(arg0: MemorySegment): Unit {
    try {
        CGContextEndPage_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextRetain typedef CGContextRef = (Declared(CGContext))*(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextRetain").orElseThrow()
private val CGContextRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextRetain_ADDR, CGContextRetain_DESC)

fun CGContextRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGContextRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextRelease Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextRelease").orElseThrow()
private val CGContextRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextRelease_ADDR, CGContextRelease_DESC)

fun CGContextRelease(arg0: MemorySegment): Unit {
    try {
        CGContextRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextFlush Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextFlush_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextFlush_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextFlush").orElseThrow()
private val CGContextFlush_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextFlush_ADDR, CGContextFlush_DESC)

fun CGContextFlush(arg0: MemorySegment): Unit {
    try {
        CGContextFlush_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSynchronize Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextSynchronize_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextSynchronize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSynchronize").orElseThrow()
private val CGContextSynchronize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSynchronize_ADDR, CGContextSynchronize_DESC)

fun CGContextSynchronize(arg0: MemorySegment): Unit {
    try {
        CGContextSynchronize_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSynchronizeAttributes Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextSynchronizeAttributes_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextSynchronizeAttributes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSynchronizeAttributes").orElseThrow()
private val CGContextSynchronizeAttributes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSynchronizeAttributes_ADDR, CGContextSynchronizeAttributes_DESC)

fun CGContextSynchronizeAttributes(arg0: MemorySegment): Unit {
    try {
        CGContextSynchronizeAttributes_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetShouldAntialias Void(typedef CGContextRef = (Declared(CGContext))*,Bool)
 */
private val CGContextSetShouldAntialias_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGContextSetShouldAntialias_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetShouldAntialias").orElseThrow()
private val CGContextSetShouldAntialias_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetShouldAntialias_ADDR, CGContextSetShouldAntialias_DESC)

fun CGContextSetShouldAntialias(arg0: MemorySegment, arg1: Boolean): Unit {
    try {
        CGContextSetShouldAntialias_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetAllowsAntialiasing Void(typedef CGContextRef = (Declared(CGContext))*,Bool)
 */
private val CGContextSetAllowsAntialiasing_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGContextSetAllowsAntialiasing_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetAllowsAntialiasing").orElseThrow()
private val CGContextSetAllowsAntialiasing_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetAllowsAntialiasing_ADDR, CGContextSetAllowsAntialiasing_DESC)

fun CGContextSetAllowsAntialiasing(arg0: MemorySegment, arg1: Boolean): Unit {
    try {
        CGContextSetAllowsAntialiasing_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetShouldSmoothFonts Void(typedef CGContextRef = (Declared(CGContext))*,Bool)
 */
private val CGContextSetShouldSmoothFonts_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGContextSetShouldSmoothFonts_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetShouldSmoothFonts").orElseThrow()
private val CGContextSetShouldSmoothFonts_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetShouldSmoothFonts_ADDR, CGContextSetShouldSmoothFonts_DESC)

fun CGContextSetShouldSmoothFonts(arg0: MemorySegment, arg1: Boolean): Unit {
    try {
        CGContextSetShouldSmoothFonts_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetAllowsFontSmoothing Void(typedef CGContextRef = (Declared(CGContext))*,Bool)
 */
private val CGContextSetAllowsFontSmoothing_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGContextSetAllowsFontSmoothing_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetAllowsFontSmoothing").orElseThrow()
private val CGContextSetAllowsFontSmoothing_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetAllowsFontSmoothing_ADDR, CGContextSetAllowsFontSmoothing_DESC)

fun CGContextSetAllowsFontSmoothing(arg0: MemorySegment, arg1: Boolean): Unit {
    try {
        CGContextSetAllowsFontSmoothing_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetShouldSubpixelPositionFonts Void(typedef CGContextRef = (Declared(CGContext))*,Bool)
 */
private val CGContextSetShouldSubpixelPositionFonts_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGContextSetShouldSubpixelPositionFonts_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetShouldSubpixelPositionFonts").orElseThrow()
private val CGContextSetShouldSubpixelPositionFonts_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetShouldSubpixelPositionFonts_ADDR, CGContextSetShouldSubpixelPositionFonts_DESC)

fun CGContextSetShouldSubpixelPositionFonts(arg0: MemorySegment, arg1: Boolean): Unit {
    try {
        CGContextSetShouldSubpixelPositionFonts_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetAllowsFontSubpixelPositioning Void(typedef CGContextRef = (Declared(CGContext))*,Bool)
 */
private val CGContextSetAllowsFontSubpixelPositioning_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGContextSetAllowsFontSubpixelPositioning_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetAllowsFontSubpixelPositioning").orElseThrow()
private val CGContextSetAllowsFontSubpixelPositioning_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetAllowsFontSubpixelPositioning_ADDR, CGContextSetAllowsFontSubpixelPositioning_DESC)

fun CGContextSetAllowsFontSubpixelPositioning(arg0: MemorySegment, arg1: Boolean): Unit {
    try {
        CGContextSetAllowsFontSubpixelPositioning_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetShouldSubpixelQuantizeFonts Void(typedef CGContextRef = (Declared(CGContext))*,Bool)
 */
private val CGContextSetShouldSubpixelQuantizeFonts_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGContextSetShouldSubpixelQuantizeFonts_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetShouldSubpixelQuantizeFonts").orElseThrow()
private val CGContextSetShouldSubpixelQuantizeFonts_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetShouldSubpixelQuantizeFonts_ADDR, CGContextSetShouldSubpixelQuantizeFonts_DESC)

fun CGContextSetShouldSubpixelQuantizeFonts(arg0: MemorySegment, arg1: Boolean): Unit {
    try {
        CGContextSetShouldSubpixelQuantizeFonts_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextSetAllowsFontSubpixelQuantization Void(typedef CGContextRef = (Declared(CGContext))*,Bool)
 */
private val CGContextSetAllowsFontSubpixelQuantization_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGContextSetAllowsFontSubpixelQuantization_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextSetAllowsFontSubpixelQuantization").orElseThrow()
private val CGContextSetAllowsFontSubpixelQuantization_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextSetAllowsFontSubpixelQuantization_ADDR, CGContextSetAllowsFontSubpixelQuantization_DESC)

fun CGContextSetAllowsFontSubpixelQuantization(arg0: MemorySegment, arg1: Boolean): Unit {
    try {
        CGContextSetAllowsFontSubpixelQuantization_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextBeginTransparencyLayer Void(typedef CGContextRef = (Declared(CGContext))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGContextBeginTransparencyLayer_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGContextBeginTransparencyLayer_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextBeginTransparencyLayer").orElseThrow()
private val CGContextBeginTransparencyLayer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextBeginTransparencyLayer_ADDR, CGContextBeginTransparencyLayer_DESC)

fun CGContextBeginTransparencyLayer(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGContextBeginTransparencyLayer_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextBeginTransparencyLayerWithRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect),typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGContextBeginTransparencyLayerWithRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.ADDRESS)
private val CGContextBeginTransparencyLayerWithRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextBeginTransparencyLayerWithRect").orElseThrow()
private val CGContextBeginTransparencyLayerWithRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextBeginTransparencyLayerWithRect_ADDR, CGContextBeginTransparencyLayerWithRect_DESC)

fun CGContextBeginTransparencyLayerWithRect(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGContextBeginTransparencyLayerWithRect_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextEndTransparencyLayer Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGContextEndTransparencyLayer_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGContextEndTransparencyLayer_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextEndTransparencyLayer").orElseThrow()
private val CGContextEndTransparencyLayer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextEndTransparencyLayer_ADDR, CGContextEndTransparencyLayer_DESC)

fun CGContextEndTransparencyLayer(arg0: MemorySegment): Unit {
    try {
        CGContextEndTransparencyLayer_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextConvertPointToDeviceSpace typedef CGPoint = Declared(CGPoint)(typedef CGContextRef = (Declared(CGContext))*,typedef CGPoint = Declared(CGPoint))
 */
private val CGContextConvertPointToDeviceSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(CGPoint.layout, ValueLayout.ADDRESS, CGPoint.layout)
private val CGContextConvertPointToDeviceSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextConvertPointToDeviceSpace").orElseThrow()
private val CGContextConvertPointToDeviceSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextConvertPointToDeviceSpace_ADDR, CGContextConvertPointToDeviceSpace_DESC)

fun CGContextConvertPointToDeviceSpace(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGContextConvertPointToDeviceSpace_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextConvertPointToUserSpace typedef CGPoint = Declared(CGPoint)(typedef CGContextRef = (Declared(CGContext))*,typedef CGPoint = Declared(CGPoint))
 */
private val CGContextConvertPointToUserSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(CGPoint.layout, ValueLayout.ADDRESS, CGPoint.layout)
private val CGContextConvertPointToUserSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextConvertPointToUserSpace").orElseThrow()
private val CGContextConvertPointToUserSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextConvertPointToUserSpace_ADDR, CGContextConvertPointToUserSpace_DESC)

fun CGContextConvertPointToUserSpace(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGContextConvertPointToUserSpace_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextConvertSizeToDeviceSpace typedef CGSize = Declared(CGSize)(typedef CGContextRef = (Declared(CGContext))*,typedef CGSize = Declared(CGSize))
 */
private val CGContextConvertSizeToDeviceSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(CGSize.layout, ValueLayout.ADDRESS, CGSize.layout)
private val CGContextConvertSizeToDeviceSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextConvertSizeToDeviceSpace").orElseThrow()
private val CGContextConvertSizeToDeviceSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextConvertSizeToDeviceSpace_ADDR, CGContextConvertSizeToDeviceSpace_DESC)

fun CGContextConvertSizeToDeviceSpace(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGContextConvertSizeToDeviceSpace_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextConvertSizeToUserSpace typedef CGSize = Declared(CGSize)(typedef CGContextRef = (Declared(CGContext))*,typedef CGSize = Declared(CGSize))
 */
private val CGContextConvertSizeToUserSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(CGSize.layout, ValueLayout.ADDRESS, CGSize.layout)
private val CGContextConvertSizeToUserSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextConvertSizeToUserSpace").orElseThrow()
private val CGContextConvertSizeToUserSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextConvertSizeToUserSpace_ADDR, CGContextConvertSizeToUserSpace_DESC)

fun CGContextConvertSizeToUserSpace(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGContextConvertSizeToUserSpace_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextConvertRectToDeviceSpace typedef CGRect = Declared(CGRect)(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextConvertRectToDeviceSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS, CGRect.layout)
private val CGContextConvertRectToDeviceSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextConvertRectToDeviceSpace").orElseThrow()
private val CGContextConvertRectToDeviceSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextConvertRectToDeviceSpace_ADDR, CGContextConvertRectToDeviceSpace_DESC)

fun CGContextConvertRectToDeviceSpace(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGContextConvertRectToDeviceSpace_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextConvertRectToUserSpace typedef CGRect = Declared(CGRect)(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect))
 */
private val CGContextConvertRectToUserSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS, CGRect.layout)
private val CGContextConvertRectToUserSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextConvertRectToUserSpace").orElseThrow()
private val CGContextConvertRectToUserSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextConvertRectToUserSpace_ADDR, CGContextConvertRectToUserSpace_DESC)

fun CGContextConvertRectToUserSpace(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGContextConvertRectToUserSpace_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextShowText Void(typedef CGContextRef = (Declared(CGContext))*,(Char)*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextShowText_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextShowText_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextShowText").orElseThrow()
private val CGContextShowText_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextShowText_ADDR, CGContextShowText_DESC)

fun CGContextShowText(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        CGContextShowText_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextShowTextAtPoint Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,(Char)*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextShowTextAtPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextShowTextAtPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextShowTextAtPoint").orElseThrow()
private val CGContextShowTextAtPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextShowTextAtPoint_ADDR, CGContextShowTextAtPoint_DESC)

fun CGContextShowTextAtPoint(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: MemorySegment, arg4: Long): Unit {
    try {
        CGContextShowTextAtPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextShowGlyphs Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGGlyph = UNSIGNED = Short)*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextShowGlyphs_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextShowGlyphs_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextShowGlyphs").orElseThrow()
private val CGContextShowGlyphs_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextShowGlyphs_ADDR, CGContextShowGlyphs_DESC)

fun CGContextShowGlyphs(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        CGContextShowGlyphs_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextShowGlyphsAtPoint Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGFloat = Double,typedef CGFloat = Double,(typedef CGGlyph = UNSIGNED = Short)*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextShowGlyphsAtPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextShowGlyphsAtPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextShowGlyphsAtPoint").orElseThrow()
private val CGContextShowGlyphsAtPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextShowGlyphsAtPoint_ADDR, CGContextShowGlyphsAtPoint_DESC)

fun CGContextShowGlyphsAtPoint(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: MemorySegment, arg4: Long): Unit {
    try {
        CGContextShowGlyphsAtPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextShowGlyphsWithAdvances Void(typedef CGContextRef = (Declared(CGContext))*,(typedef CGGlyph = UNSIGNED = Short)*,(typedef CGSize = Declared(CGSize))*,typedef size_t = UNSIGNED = Long)
 */
private val CGContextShowGlyphsWithAdvances_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGContextShowGlyphsWithAdvances_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextShowGlyphsWithAdvances").orElseThrow()
private val CGContextShowGlyphsWithAdvances_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextShowGlyphsWithAdvances_ADDR, CGContextShowGlyphsWithAdvances_DESC)

fun CGContextShowGlyphsWithAdvances(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long): Unit {
    try {
        CGContextShowGlyphsWithAdvances_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextDrawPDFDocument Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect),typedef CGPDFDocumentRef = (Declared(CGPDFDocument))*,Int)
 */
private val CGContextDrawPDFDocument_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGContextDrawPDFDocument_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextDrawPDFDocument").orElseThrow()
private val CGContextDrawPDFDocument_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextDrawPDFDocument_ADDR, CGContextDrawPDFDocument_DESC)

fun CGContextDrawPDFDocument(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Int): Unit {
    try {
        CGContextDrawPDFDocument_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSGraphicsContextDestinationAttributeName typedef NSGraphicsContextAttributeKey = typedef NSString = (Void)*
 */
private val NSGraphicsContextDestinationAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGraphicsContextDestinationAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGraphicsContextDestinationAttributeName").orElseThrow() }
private val NSGraphicsContextDestinationAttributeName_VH: VarHandle by lazy { NSGraphicsContextDestinationAttributeName_LAYOUT.varHandle() }

var NSGraphicsContextDestinationAttributeName: MemorySegment
    get() = NSGraphicsContextDestinationAttributeName_VH.get(NSGraphicsContextDestinationAttributeName_SEGMENT) as MemorySegment
    set(value) = NSGraphicsContextDestinationAttributeName_VH.set(NSGraphicsContextDestinationAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSGraphicsContextRepresentationFormatAttributeName typedef NSGraphicsContextAttributeKey = typedef NSString = (Void)*
 */
private val NSGraphicsContextRepresentationFormatAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGraphicsContextRepresentationFormatAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGraphicsContextRepresentationFormatAttributeName").orElseThrow() }
private val NSGraphicsContextRepresentationFormatAttributeName_VH: VarHandle by lazy { NSGraphicsContextRepresentationFormatAttributeName_LAYOUT.varHandle() }

var NSGraphicsContextRepresentationFormatAttributeName: MemorySegment
    get() = NSGraphicsContextRepresentationFormatAttributeName_VH.get(NSGraphicsContextRepresentationFormatAttributeName_SEGMENT) as MemorySegment
    set(value) = NSGraphicsContextRepresentationFormatAttributeName_VH.set(NSGraphicsContextRepresentationFormatAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSGraphicsContextPSFormat typedef NSGraphicsContextRepresentationFormatName = typedef NSString = (Void)*
 */
private val NSGraphicsContextPSFormat_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGraphicsContextPSFormat_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGraphicsContextPSFormat").orElseThrow() }
private val NSGraphicsContextPSFormat_VH: VarHandle by lazy { NSGraphicsContextPSFormat_LAYOUT.varHandle() }

var NSGraphicsContextPSFormat: MemorySegment
    get() = NSGraphicsContextPSFormat_VH.get(NSGraphicsContextPSFormat_SEGMENT) as MemorySegment
    set(value) = NSGraphicsContextPSFormat_VH.set(NSGraphicsContextPSFormat_SEGMENT, value)

/**
 * {@snippet lang=c : NSGraphicsContextPDFFormat typedef NSGraphicsContextRepresentationFormatName = typedef NSString = (Void)*
 */
private val NSGraphicsContextPDFFormat_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGraphicsContextPDFFormat_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGraphicsContextPDFFormat").orElseThrow() }
private val NSGraphicsContextPDFFormat_VH: VarHandle by lazy { NSGraphicsContextPDFFormat_LAYOUT.varHandle() }

var NSGraphicsContextPDFFormat: MemorySegment
    get() = NSGraphicsContextPDFFormat_VH.get(NSGraphicsContextPDFFormat_SEGMENT) as MemorySegment
    set(value) = NSGraphicsContextPDFFormat_VH.set(NSGraphicsContextPDFFormat_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextLineTooLongException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSTextLineTooLongException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextLineTooLongException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextLineTooLongException").orElseThrow() }
private val NSTextLineTooLongException_VH: VarHandle by lazy { NSTextLineTooLongException_LAYOUT.varHandle() }

var NSTextLineTooLongException: MemorySegment
    get() = NSTextLineTooLongException_VH.get(NSTextLineTooLongException_SEGMENT) as MemorySegment
    set(value) = NSTextLineTooLongException_VH.set(NSTextLineTooLongException_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextNoSelectionException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSTextNoSelectionException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextNoSelectionException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextNoSelectionException").orElseThrow() }
private val NSTextNoSelectionException_VH: VarHandle by lazy { NSTextNoSelectionException_LAYOUT.varHandle() }

var NSTextNoSelectionException: MemorySegment
    get() = NSTextNoSelectionException_VH.get(NSTextNoSelectionException_SEGMENT) as MemorySegment
    set(value) = NSTextNoSelectionException_VH.set(NSTextNoSelectionException_SEGMENT, value)

/**
 * {@snippet lang=c : NSWordTablesWriteException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSWordTablesWriteException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWordTablesWriteException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWordTablesWriteException").orElseThrow() }
private val NSWordTablesWriteException_VH: VarHandle by lazy { NSWordTablesWriteException_LAYOUT.varHandle() }

var NSWordTablesWriteException: MemorySegment
    get() = NSWordTablesWriteException_VH.get(NSWordTablesWriteException_SEGMENT) as MemorySegment
    set(value) = NSWordTablesWriteException_VH.set(NSWordTablesWriteException_SEGMENT, value)

/**
 * {@snippet lang=c : NSWordTablesReadException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSWordTablesReadException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWordTablesReadException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWordTablesReadException").orElseThrow() }
private val NSWordTablesReadException_VH: VarHandle by lazy { NSWordTablesReadException_LAYOUT.varHandle() }

var NSWordTablesReadException: MemorySegment
    get() = NSWordTablesReadException_VH.get(NSWordTablesReadException_SEGMENT) as MemorySegment
    set(value) = NSWordTablesReadException_VH.set(NSWordTablesReadException_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextReadException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSTextReadException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextReadException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextReadException").orElseThrow() }
private val NSTextReadException_VH: VarHandle by lazy { NSTextReadException_LAYOUT.varHandle() }

var NSTextReadException: MemorySegment
    get() = NSTextReadException_VH.get(NSTextReadException_SEGMENT) as MemorySegment
    set(value) = NSTextReadException_VH.set(NSTextReadException_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextWriteException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSTextWriteException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextWriteException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextWriteException").orElseThrow() }
private val NSTextWriteException_VH: VarHandle by lazy { NSTextWriteException_LAYOUT.varHandle() }

var NSTextWriteException: MemorySegment
    get() = NSTextWriteException_VH.get(NSTextWriteException_SEGMENT) as MemorySegment
    set(value) = NSTextWriteException_VH.set(NSTextWriteException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPasteboardCommunicationException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSPasteboardCommunicationException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPasteboardCommunicationException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPasteboardCommunicationException").orElseThrow() }
private val NSPasteboardCommunicationException_VH: VarHandle by lazy { NSPasteboardCommunicationException_LAYOUT.varHandle() }

var NSPasteboardCommunicationException: MemorySegment
    get() = NSPasteboardCommunicationException_VH.get(NSPasteboardCommunicationException_SEGMENT) as MemorySegment
    set(value) = NSPasteboardCommunicationException_VH.set(NSPasteboardCommunicationException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintingCommunicationException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSPrintingCommunicationException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintingCommunicationException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintingCommunicationException").orElseThrow() }
private val NSPrintingCommunicationException_VH: VarHandle by lazy { NSPrintingCommunicationException_LAYOUT.varHandle() }

var NSPrintingCommunicationException: MemorySegment
    get() = NSPrintingCommunicationException_VH.get(NSPrintingCommunicationException_SEGMENT) as MemorySegment
    set(value) = NSPrintingCommunicationException_VH.set(NSPrintingCommunicationException_SEGMENT, value)

/**
 * {@snippet lang=c : NSAbortModalException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSAbortModalException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAbortModalException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAbortModalException").orElseThrow() }
private val NSAbortModalException_VH: VarHandle by lazy { NSAbortModalException_LAYOUT.varHandle() }

var NSAbortModalException: MemorySegment
    get() = NSAbortModalException_VH.get(NSAbortModalException_SEGMENT) as MemorySegment
    set(value) = NSAbortModalException_VH.set(NSAbortModalException_SEGMENT, value)

/**
 * {@snippet lang=c : NSAbortPrintingException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSAbortPrintingException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAbortPrintingException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAbortPrintingException").orElseThrow() }
private val NSAbortPrintingException_VH: VarHandle by lazy { NSAbortPrintingException_LAYOUT.varHandle() }

var NSAbortPrintingException: MemorySegment
    get() = NSAbortPrintingException_VH.get(NSAbortPrintingException_SEGMENT) as MemorySegment
    set(value) = NSAbortPrintingException_VH.set(NSAbortPrintingException_SEGMENT, value)

/**
 * {@snippet lang=c : NSIllegalSelectorException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSIllegalSelectorException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSIllegalSelectorException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIllegalSelectorException").orElseThrow() }
private val NSIllegalSelectorException_VH: VarHandle by lazy { NSIllegalSelectorException_LAYOUT.varHandle() }

var NSIllegalSelectorException: MemorySegment
    get() = NSIllegalSelectorException_VH.get(NSIllegalSelectorException_SEGMENT) as MemorySegment
    set(value) = NSIllegalSelectorException_VH.set(NSIllegalSelectorException_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppKitVirtualMemoryException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSAppKitVirtualMemoryException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppKitVirtualMemoryException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppKitVirtualMemoryException").orElseThrow() }
private val NSAppKitVirtualMemoryException_VH: VarHandle by lazy { NSAppKitVirtualMemoryException_LAYOUT.varHandle() }

var NSAppKitVirtualMemoryException: MemorySegment
    get() = NSAppKitVirtualMemoryException_VH.get(NSAppKitVirtualMemoryException_SEGMENT) as MemorySegment
    set(value) = NSAppKitVirtualMemoryException_VH.set(NSAppKitVirtualMemoryException_SEGMENT, value)

/**
 * {@snippet lang=c : NSBadRTFDirectiveException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSBadRTFDirectiveException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBadRTFDirectiveException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBadRTFDirectiveException").orElseThrow() }
private val NSBadRTFDirectiveException_VH: VarHandle by lazy { NSBadRTFDirectiveException_LAYOUT.varHandle() }

var NSBadRTFDirectiveException: MemorySegment
    get() = NSBadRTFDirectiveException_VH.get(NSBadRTFDirectiveException_SEGMENT) as MemorySegment
    set(value) = NSBadRTFDirectiveException_VH.set(NSBadRTFDirectiveException_SEGMENT, value)

/**
 * {@snippet lang=c : NSBadRTFFontTableException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSBadRTFFontTableException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBadRTFFontTableException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBadRTFFontTableException").orElseThrow() }
private val NSBadRTFFontTableException_VH: VarHandle by lazy { NSBadRTFFontTableException_LAYOUT.varHandle() }

var NSBadRTFFontTableException: MemorySegment
    get() = NSBadRTFFontTableException_VH.get(NSBadRTFFontTableException_SEGMENT) as MemorySegment
    set(value) = NSBadRTFFontTableException_VH.set(NSBadRTFFontTableException_SEGMENT, value)

/**
 * {@snippet lang=c : NSBadRTFStyleSheetException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSBadRTFStyleSheetException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBadRTFStyleSheetException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBadRTFStyleSheetException").orElseThrow() }
private val NSBadRTFStyleSheetException_VH: VarHandle by lazy { NSBadRTFStyleSheetException_LAYOUT.varHandle() }

var NSBadRTFStyleSheetException: MemorySegment
    get() = NSBadRTFStyleSheetException_VH.get(NSBadRTFStyleSheetException_SEGMENT) as MemorySegment
    set(value) = NSBadRTFStyleSheetException_VH.set(NSBadRTFStyleSheetException_SEGMENT, value)

/**
 * {@snippet lang=c : NSTypedStreamVersionException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSTypedStreamVersionException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTypedStreamVersionException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTypedStreamVersionException").orElseThrow() }
private val NSTypedStreamVersionException_VH: VarHandle by lazy { NSTypedStreamVersionException_LAYOUT.varHandle() }

var NSTypedStreamVersionException: MemorySegment
    get() = NSTypedStreamVersionException_VH.get(NSTypedStreamVersionException_SEGMENT) as MemorySegment
    set(value) = NSTypedStreamVersionException_VH.set(NSTypedStreamVersionException_SEGMENT, value)

/**
 * {@snippet lang=c : NSTIFFException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSTIFFException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTIFFException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTIFFException").orElseThrow() }
private val NSTIFFException_VH: VarHandle by lazy { NSTIFFException_LAYOUT.varHandle() }

var NSTIFFException: MemorySegment
    get() = NSTIFFException_VH.get(NSTIFFException_SEGMENT) as MemorySegment
    set(value) = NSTIFFException_VH.set(NSTIFFException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPackageException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSPrintPackageException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPackageException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPackageException").orElseThrow() }
private val NSPrintPackageException_VH: VarHandle by lazy { NSPrintPackageException_LAYOUT.varHandle() }

var NSPrintPackageException: MemorySegment
    get() = NSPrintPackageException_VH.get(NSPrintPackageException_SEGMENT) as MemorySegment
    set(value) = NSPrintPackageException_VH.set(NSPrintPackageException_SEGMENT, value)

/**
 * {@snippet lang=c : NSBadRTFColorTableException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSBadRTFColorTableException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBadRTFColorTableException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBadRTFColorTableException").orElseThrow() }
private val NSBadRTFColorTableException_VH: VarHandle by lazy { NSBadRTFColorTableException_LAYOUT.varHandle() }

var NSBadRTFColorTableException: MemorySegment
    get() = NSBadRTFColorTableException_VH.get(NSBadRTFColorTableException_SEGMENT) as MemorySegment
    set(value) = NSBadRTFColorTableException_VH.set(NSBadRTFColorTableException_SEGMENT, value)

/**
 * {@snippet lang=c : NSDraggingException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSDraggingException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDraggingException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDraggingException").orElseThrow() }
private val NSDraggingException_VH: VarHandle by lazy { NSDraggingException_LAYOUT.varHandle() }

var NSDraggingException: MemorySegment
    get() = NSDraggingException_VH.get(NSDraggingException_SEGMENT) as MemorySegment
    set(value) = NSDraggingException_VH.set(NSDraggingException_SEGMENT, value)

/**
 * {@snippet lang=c : NSColorListIOException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSColorListIOException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSColorListIOException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSColorListIOException").orElseThrow() }
private val NSColorListIOException_VH: VarHandle by lazy { NSColorListIOException_LAYOUT.varHandle() }

var NSColorListIOException: MemorySegment
    get() = NSColorListIOException_VH.get(NSColorListIOException_SEGMENT) as MemorySegment
    set(value) = NSColorListIOException_VH.set(NSColorListIOException_SEGMENT, value)

/**
 * {@snippet lang=c : NSColorListNotEditableException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSColorListNotEditableException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSColorListNotEditableException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSColorListNotEditableException").orElseThrow() }
private val NSColorListNotEditableException_VH: VarHandle by lazy { NSColorListNotEditableException_LAYOUT.varHandle() }

var NSColorListNotEditableException: MemorySegment
    get() = NSColorListNotEditableException_VH.get(NSColorListNotEditableException_SEGMENT) as MemorySegment
    set(value) = NSColorListNotEditableException_VH.set(NSColorListNotEditableException_SEGMENT, value)

/**
 * {@snippet lang=c : NSBadBitmapParametersException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSBadBitmapParametersException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBadBitmapParametersException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBadBitmapParametersException").orElseThrow() }
private val NSBadBitmapParametersException_VH: VarHandle by lazy { NSBadBitmapParametersException_LAYOUT.varHandle() }

var NSBadBitmapParametersException: MemorySegment
    get() = NSBadBitmapParametersException_VH.get(NSBadBitmapParametersException_SEGMENT) as MemorySegment
    set(value) = NSBadBitmapParametersException_VH.set(NSBadBitmapParametersException_SEGMENT, value)

/**
 * {@snippet lang=c : NSWindowServerCommunicationException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSWindowServerCommunicationException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWindowServerCommunicationException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWindowServerCommunicationException").orElseThrow() }
private val NSWindowServerCommunicationException_VH: VarHandle by lazy { NSWindowServerCommunicationException_LAYOUT.varHandle() }

var NSWindowServerCommunicationException: MemorySegment
    get() = NSWindowServerCommunicationException_VH.get(NSWindowServerCommunicationException_SEGMENT) as MemorySegment
    set(value) = NSWindowServerCommunicationException_VH.set(NSWindowServerCommunicationException_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontUnavailableException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSFontUnavailableException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontUnavailableException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontUnavailableException").orElseThrow() }
private val NSFontUnavailableException_VH: VarHandle by lazy { NSFontUnavailableException_LAYOUT.varHandle() }

var NSFontUnavailableException: MemorySegment
    get() = NSFontUnavailableException_VH.get(NSFontUnavailableException_SEGMENT) as MemorySegment
    set(value) = NSFontUnavailableException_VH.set(NSFontUnavailableException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPPDIncludeNotFoundException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSPPDIncludeNotFoundException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPPDIncludeNotFoundException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPPDIncludeNotFoundException").orElseThrow() }
private val NSPPDIncludeNotFoundException_VH: VarHandle by lazy { NSPPDIncludeNotFoundException_LAYOUT.varHandle() }

var NSPPDIncludeNotFoundException: MemorySegment
    get() = NSPPDIncludeNotFoundException_VH.get(NSPPDIncludeNotFoundException_SEGMENT) as MemorySegment
    set(value) = NSPPDIncludeNotFoundException_VH.set(NSPPDIncludeNotFoundException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPPDParseException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSPPDParseException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPPDParseException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPPDParseException").orElseThrow() }
private val NSPPDParseException_VH: VarHandle by lazy { NSPPDParseException_LAYOUT.varHandle() }

var NSPPDParseException: MemorySegment
    get() = NSPPDParseException_VH.get(NSPPDParseException_SEGMENT) as MemorySegment
    set(value) = NSPPDParseException_VH.set(NSPPDParseException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPPDIncludeStackOverflowException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSPPDIncludeStackOverflowException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPPDIncludeStackOverflowException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPPDIncludeStackOverflowException").orElseThrow() }
private val NSPPDIncludeStackOverflowException_VH: VarHandle by lazy { NSPPDIncludeStackOverflowException_LAYOUT.varHandle() }

var NSPPDIncludeStackOverflowException: MemorySegment
    get() = NSPPDIncludeStackOverflowException_VH.get(NSPPDIncludeStackOverflowException_SEGMENT) as MemorySegment
    set(value) = NSPPDIncludeStackOverflowException_VH.set(NSPPDIncludeStackOverflowException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPPDIncludeStackUnderflowException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSPPDIncludeStackUnderflowException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPPDIncludeStackUnderflowException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPPDIncludeStackUnderflowException").orElseThrow() }
private val NSPPDIncludeStackUnderflowException_VH: VarHandle by lazy { NSPPDIncludeStackUnderflowException_LAYOUT.varHandle() }

var NSPPDIncludeStackUnderflowException: MemorySegment
    get() = NSPPDIncludeStackUnderflowException_VH.get(NSPPDIncludeStackUnderflowException_SEGMENT) as MemorySegment
    set(value) = NSPPDIncludeStackUnderflowException_VH.set(NSPPDIncludeStackUnderflowException_SEGMENT, value)

/**
 * {@snippet lang=c : NSRTFPropertyStackOverflowException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSRTFPropertyStackOverflowException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRTFPropertyStackOverflowException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRTFPropertyStackOverflowException").orElseThrow() }
private val NSRTFPropertyStackOverflowException_VH: VarHandle by lazy { NSRTFPropertyStackOverflowException_LAYOUT.varHandle() }

var NSRTFPropertyStackOverflowException: MemorySegment
    get() = NSRTFPropertyStackOverflowException_VH.get(NSRTFPropertyStackOverflowException_SEGMENT) as MemorySegment
    set(value) = NSRTFPropertyStackOverflowException_VH.set(NSRTFPropertyStackOverflowException_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppKitIgnoredException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSAppKitIgnoredException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppKitIgnoredException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppKitIgnoredException").orElseThrow() }
private val NSAppKitIgnoredException_VH: VarHandle by lazy { NSAppKitIgnoredException_LAYOUT.varHandle() }

var NSAppKitIgnoredException: MemorySegment
    get() = NSAppKitIgnoredException_VH.get(NSAppKitIgnoredException_SEGMENT) as MemorySegment
    set(value) = NSAppKitIgnoredException_VH.set(NSAppKitIgnoredException_SEGMENT, value)

/**
 * {@snippet lang=c : NSBadComparisonException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSBadComparisonException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBadComparisonException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBadComparisonException").orElseThrow() }
private val NSBadComparisonException_VH: VarHandle by lazy { NSBadComparisonException_LAYOUT.varHandle() }

var NSBadComparisonException: MemorySegment
    get() = NSBadComparisonException_VH.get(NSBadComparisonException_SEGMENT) as MemorySegment
    set(value) = NSBadComparisonException_VH.set(NSBadComparisonException_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageCacheException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSImageCacheException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageCacheException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageCacheException").orElseThrow() }
private val NSImageCacheException_VH: VarHandle by lazy { NSImageCacheException_LAYOUT.varHandle() }

var NSImageCacheException: MemorySegment
    get() = NSImageCacheException_VH.get(NSImageCacheException_SEGMENT) as MemorySegment
    set(value) = NSImageCacheException_VH.set(NSImageCacheException_SEGMENT, value)

/**
 * {@snippet lang=c : NSNibLoadingException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSNibLoadingException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNibLoadingException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNibLoadingException").orElseThrow() }
private val NSNibLoadingException_VH: VarHandle by lazy { NSNibLoadingException_LAYOUT.varHandle() }

var NSNibLoadingException: MemorySegment
    get() = NSNibLoadingException_VH.get(NSNibLoadingException_SEGMENT) as MemorySegment
    set(value) = NSNibLoadingException_VH.set(NSNibLoadingException_SEGMENT, value)

/**
 * {@snippet lang=c : NSBrowserIllegalDelegateException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSBrowserIllegalDelegateException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBrowserIllegalDelegateException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBrowserIllegalDelegateException").orElseThrow() }
private val NSBrowserIllegalDelegateException_VH: VarHandle by lazy { NSBrowserIllegalDelegateException_LAYOUT.varHandle() }

var NSBrowserIllegalDelegateException: MemorySegment
    get() = NSBrowserIllegalDelegateException_VH.get(NSBrowserIllegalDelegateException_SEGMENT) as MemorySegment
    set(value) = NSBrowserIllegalDelegateException_VH.set(NSBrowserIllegalDelegateException_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSAccessibilityException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityException").orElseThrow() }
private val NSAccessibilityException_VH: VarHandle by lazy { NSAccessibilityException_LAYOUT.varHandle() }

var NSAccessibilityException: MemorySegment
    get() = NSAccessibilityException_VH.get(NSAccessibilityException_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityException_VH.set(NSAccessibilityException_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityErrorCodeExceptionInfo (Void)*
 */
private val NSAccessibilityErrorCodeExceptionInfo_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityErrorCodeExceptionInfo_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityErrorCodeExceptionInfo").orElseThrow() }
private val NSAccessibilityErrorCodeExceptionInfo_VH: VarHandle by lazy { NSAccessibilityErrorCodeExceptionInfo_LAYOUT.varHandle() }

var NSAccessibilityErrorCodeExceptionInfo: MemorySegment
    get() = NSAccessibilityErrorCodeExceptionInfo_VH.get(NSAccessibilityErrorCodeExceptionInfo_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityErrorCodeExceptionInfo_VH.set(NSAccessibilityErrorCodeExceptionInfo_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRoleAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityRoleAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRoleAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRoleAttribute").orElseThrow() }
private val NSAccessibilityRoleAttribute_VH: VarHandle by lazy { NSAccessibilityRoleAttribute_LAYOUT.varHandle() }

var NSAccessibilityRoleAttribute: MemorySegment
    get() = NSAccessibilityRoleAttribute_VH.get(NSAccessibilityRoleAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRoleAttribute_VH.set(NSAccessibilityRoleAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRoleDescriptionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityRoleDescriptionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRoleDescriptionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRoleDescriptionAttribute").orElseThrow() }
private val NSAccessibilityRoleDescriptionAttribute_VH: VarHandle by lazy { NSAccessibilityRoleDescriptionAttribute_LAYOUT.varHandle() }

var NSAccessibilityRoleDescriptionAttribute: MemorySegment
    get() = NSAccessibilityRoleDescriptionAttribute_VH.get(NSAccessibilityRoleDescriptionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRoleDescriptionAttribute_VH.set(NSAccessibilityRoleDescriptionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySubroleAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySubroleAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySubroleAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySubroleAttribute").orElseThrow() }
private val NSAccessibilitySubroleAttribute_VH: VarHandle by lazy { NSAccessibilitySubroleAttribute_LAYOUT.varHandle() }

var NSAccessibilitySubroleAttribute: MemorySegment
    get() = NSAccessibilitySubroleAttribute_VH.get(NSAccessibilitySubroleAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySubroleAttribute_VH.set(NSAccessibilitySubroleAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHelpAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityHelpAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHelpAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHelpAttribute").orElseThrow() }
private val NSAccessibilityHelpAttribute_VH: VarHandle by lazy { NSAccessibilityHelpAttribute_LAYOUT.varHandle() }

var NSAccessibilityHelpAttribute: MemorySegment
    get() = NSAccessibilityHelpAttribute_VH.get(NSAccessibilityHelpAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHelpAttribute_VH.set(NSAccessibilityHelpAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityValueAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityValueAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityValueAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityValueAttribute").orElseThrow() }
private val NSAccessibilityValueAttribute_VH: VarHandle by lazy { NSAccessibilityValueAttribute_LAYOUT.varHandle() }

var NSAccessibilityValueAttribute: MemorySegment
    get() = NSAccessibilityValueAttribute_VH.get(NSAccessibilityValueAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityValueAttribute_VH.set(NSAccessibilityValueAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMinValueAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMinValueAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMinValueAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMinValueAttribute").orElseThrow() }
private val NSAccessibilityMinValueAttribute_VH: VarHandle by lazy { NSAccessibilityMinValueAttribute_LAYOUT.varHandle() }

var NSAccessibilityMinValueAttribute: MemorySegment
    get() = NSAccessibilityMinValueAttribute_VH.get(NSAccessibilityMinValueAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMinValueAttribute_VH.set(NSAccessibilityMinValueAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMaxValueAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMaxValueAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMaxValueAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMaxValueAttribute").orElseThrow() }
private val NSAccessibilityMaxValueAttribute_VH: VarHandle by lazy { NSAccessibilityMaxValueAttribute_LAYOUT.varHandle() }

var NSAccessibilityMaxValueAttribute: MemorySegment
    get() = NSAccessibilityMaxValueAttribute_VH.get(NSAccessibilityMaxValueAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMaxValueAttribute_VH.set(NSAccessibilityMaxValueAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityEnabledAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityEnabledAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityEnabledAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityEnabledAttribute").orElseThrow() }
private val NSAccessibilityEnabledAttribute_VH: VarHandle by lazy { NSAccessibilityEnabledAttribute_LAYOUT.varHandle() }

var NSAccessibilityEnabledAttribute: MemorySegment
    get() = NSAccessibilityEnabledAttribute_VH.get(NSAccessibilityEnabledAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityEnabledAttribute_VH.set(NSAccessibilityEnabledAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFocusedAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityFocusedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFocusedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFocusedAttribute").orElseThrow() }
private val NSAccessibilityFocusedAttribute_VH: VarHandle by lazy { NSAccessibilityFocusedAttribute_LAYOUT.varHandle() }

var NSAccessibilityFocusedAttribute: MemorySegment
    get() = NSAccessibilityFocusedAttribute_VH.get(NSAccessibilityFocusedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFocusedAttribute_VH.set(NSAccessibilityFocusedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityParentAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityParentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityParentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityParentAttribute").orElseThrow() }
private val NSAccessibilityParentAttribute_VH: VarHandle by lazy { NSAccessibilityParentAttribute_LAYOUT.varHandle() }

var NSAccessibilityParentAttribute: MemorySegment
    get() = NSAccessibilityParentAttribute_VH.get(NSAccessibilityParentAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityParentAttribute_VH.set(NSAccessibilityParentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityChildrenAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityChildrenAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityChildrenAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityChildrenAttribute").orElseThrow() }
private val NSAccessibilityChildrenAttribute_VH: VarHandle by lazy { NSAccessibilityChildrenAttribute_LAYOUT.varHandle() }

var NSAccessibilityChildrenAttribute: MemorySegment
    get() = NSAccessibilityChildrenAttribute_VH.get(NSAccessibilityChildrenAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityChildrenAttribute_VH.set(NSAccessibilityChildrenAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWindowAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityWindowAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWindowAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWindowAttribute").orElseThrow() }
private val NSAccessibilityWindowAttribute_VH: VarHandle by lazy { NSAccessibilityWindowAttribute_LAYOUT.varHandle() }

var NSAccessibilityWindowAttribute: MemorySegment
    get() = NSAccessibilityWindowAttribute_VH.get(NSAccessibilityWindowAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWindowAttribute_VH.set(NSAccessibilityWindowAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTopLevelUIElementAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityTopLevelUIElementAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTopLevelUIElementAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTopLevelUIElementAttribute").orElseThrow() }
private val NSAccessibilityTopLevelUIElementAttribute_VH: VarHandle by lazy { NSAccessibilityTopLevelUIElementAttribute_LAYOUT.varHandle() }

var NSAccessibilityTopLevelUIElementAttribute: MemorySegment
    get() = NSAccessibilityTopLevelUIElementAttribute_VH.get(NSAccessibilityTopLevelUIElementAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTopLevelUIElementAttribute_VH.set(NSAccessibilityTopLevelUIElementAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedChildrenAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySelectedChildrenAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedChildrenAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedChildrenAttribute").orElseThrow() }
private val NSAccessibilitySelectedChildrenAttribute_VH: VarHandle by lazy { NSAccessibilitySelectedChildrenAttribute_LAYOUT.varHandle() }

var NSAccessibilitySelectedChildrenAttribute: MemorySegment
    get() = NSAccessibilitySelectedChildrenAttribute_VH.get(NSAccessibilitySelectedChildrenAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedChildrenAttribute_VH.set(NSAccessibilitySelectedChildrenAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVisibleChildrenAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityVisibleChildrenAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVisibleChildrenAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVisibleChildrenAttribute").orElseThrow() }
private val NSAccessibilityVisibleChildrenAttribute_VH: VarHandle by lazy { NSAccessibilityVisibleChildrenAttribute_LAYOUT.varHandle() }

var NSAccessibilityVisibleChildrenAttribute: MemorySegment
    get() = NSAccessibilityVisibleChildrenAttribute_VH.get(NSAccessibilityVisibleChildrenAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVisibleChildrenAttribute_VH.set(NSAccessibilityVisibleChildrenAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPositionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityPositionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPositionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPositionAttribute").orElseThrow() }
private val NSAccessibilityPositionAttribute_VH: VarHandle by lazy { NSAccessibilityPositionAttribute_LAYOUT.varHandle() }

var NSAccessibilityPositionAttribute: MemorySegment
    get() = NSAccessibilityPositionAttribute_VH.get(NSAccessibilityPositionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPositionAttribute_VH.set(NSAccessibilityPositionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySizeAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySizeAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySizeAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySizeAttribute").orElseThrow() }
private val NSAccessibilitySizeAttribute_VH: VarHandle by lazy { NSAccessibilitySizeAttribute_LAYOUT.varHandle() }

var NSAccessibilitySizeAttribute: MemorySegment
    get() = NSAccessibilitySizeAttribute_VH.get(NSAccessibilitySizeAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySizeAttribute_VH.set(NSAccessibilitySizeAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityContentsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityContentsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityContentsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityContentsAttribute").orElseThrow() }
private val NSAccessibilityContentsAttribute_VH: VarHandle by lazy { NSAccessibilityContentsAttribute_LAYOUT.varHandle() }

var NSAccessibilityContentsAttribute: MemorySegment
    get() = NSAccessibilityContentsAttribute_VH.get(NSAccessibilityContentsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityContentsAttribute_VH.set(NSAccessibilityContentsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTitleAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityTitleAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTitleAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTitleAttribute").orElseThrow() }
private val NSAccessibilityTitleAttribute_VH: VarHandle by lazy { NSAccessibilityTitleAttribute_LAYOUT.varHandle() }

var NSAccessibilityTitleAttribute: MemorySegment
    get() = NSAccessibilityTitleAttribute_VH.get(NSAccessibilityTitleAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTitleAttribute_VH.set(NSAccessibilityTitleAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDescriptionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityDescriptionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDescriptionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDescriptionAttribute").orElseThrow() }
private val NSAccessibilityDescriptionAttribute_VH: VarHandle by lazy { NSAccessibilityDescriptionAttribute_LAYOUT.varHandle() }

var NSAccessibilityDescriptionAttribute: MemorySegment
    get() = NSAccessibilityDescriptionAttribute_VH.get(NSAccessibilityDescriptionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDescriptionAttribute_VH.set(NSAccessibilityDescriptionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityShownMenuAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityShownMenuAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityShownMenuAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityShownMenuAttribute").orElseThrow() }
private val NSAccessibilityShownMenuAttribute_VH: VarHandle by lazy { NSAccessibilityShownMenuAttribute_LAYOUT.varHandle() }

var NSAccessibilityShownMenuAttribute: MemorySegment
    get() = NSAccessibilityShownMenuAttribute_VH.get(NSAccessibilityShownMenuAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityShownMenuAttribute_VH.set(NSAccessibilityShownMenuAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityValueDescriptionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityValueDescriptionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityValueDescriptionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityValueDescriptionAttribute").orElseThrow() }
private val NSAccessibilityValueDescriptionAttribute_VH: VarHandle by lazy { NSAccessibilityValueDescriptionAttribute_LAYOUT.varHandle() }

var NSAccessibilityValueDescriptionAttribute: MemorySegment
    get() = NSAccessibilityValueDescriptionAttribute_VH.get(NSAccessibilityValueDescriptionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityValueDescriptionAttribute_VH.set(NSAccessibilityValueDescriptionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySharedFocusElementsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySharedFocusElementsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySharedFocusElementsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySharedFocusElementsAttribute").orElseThrow() }
private val NSAccessibilitySharedFocusElementsAttribute_VH: VarHandle by lazy { NSAccessibilitySharedFocusElementsAttribute_LAYOUT.varHandle() }

var NSAccessibilitySharedFocusElementsAttribute: MemorySegment
    get() = NSAccessibilitySharedFocusElementsAttribute_VH.get(NSAccessibilitySharedFocusElementsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySharedFocusElementsAttribute_VH.set(NSAccessibilitySharedFocusElementsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPreviousContentsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityPreviousContentsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPreviousContentsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPreviousContentsAttribute").orElseThrow() }
private val NSAccessibilityPreviousContentsAttribute_VH: VarHandle by lazy { NSAccessibilityPreviousContentsAttribute_LAYOUT.varHandle() }

var NSAccessibilityPreviousContentsAttribute: MemorySegment
    get() = NSAccessibilityPreviousContentsAttribute_VH.get(NSAccessibilityPreviousContentsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPreviousContentsAttribute_VH.set(NSAccessibilityPreviousContentsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityNextContentsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityNextContentsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityNextContentsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityNextContentsAttribute").orElseThrow() }
private val NSAccessibilityNextContentsAttribute_VH: VarHandle by lazy { NSAccessibilityNextContentsAttribute_LAYOUT.varHandle() }

var NSAccessibilityNextContentsAttribute: MemorySegment
    get() = NSAccessibilityNextContentsAttribute_VH.get(NSAccessibilityNextContentsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityNextContentsAttribute_VH.set(NSAccessibilityNextContentsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeaderAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityHeaderAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeaderAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeaderAttribute").orElseThrow() }
private val NSAccessibilityHeaderAttribute_VH: VarHandle by lazy { NSAccessibilityHeaderAttribute_LAYOUT.varHandle() }

var NSAccessibilityHeaderAttribute: MemorySegment
    get() = NSAccessibilityHeaderAttribute_VH.get(NSAccessibilityHeaderAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeaderAttribute_VH.set(NSAccessibilityHeaderAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityEditedAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityEditedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityEditedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityEditedAttribute").orElseThrow() }
private val NSAccessibilityEditedAttribute_VH: VarHandle by lazy { NSAccessibilityEditedAttribute_LAYOUT.varHandle() }

var NSAccessibilityEditedAttribute: MemorySegment
    get() = NSAccessibilityEditedAttribute_VH.get(NSAccessibilityEditedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityEditedAttribute_VH.set(NSAccessibilityEditedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTabsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityTabsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTabsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTabsAttribute").orElseThrow() }
private val NSAccessibilityTabsAttribute_VH: VarHandle by lazy { NSAccessibilityTabsAttribute_LAYOUT.varHandle() }

var NSAccessibilityTabsAttribute: MemorySegment
    get() = NSAccessibilityTabsAttribute_VH.get(NSAccessibilityTabsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTabsAttribute_VH.set(NSAccessibilityTabsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHorizontalScrollBarAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityHorizontalScrollBarAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHorizontalScrollBarAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHorizontalScrollBarAttribute").orElseThrow() }
private val NSAccessibilityHorizontalScrollBarAttribute_VH: VarHandle by lazy { NSAccessibilityHorizontalScrollBarAttribute_LAYOUT.varHandle() }

var NSAccessibilityHorizontalScrollBarAttribute: MemorySegment
    get() = NSAccessibilityHorizontalScrollBarAttribute_VH.get(NSAccessibilityHorizontalScrollBarAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHorizontalScrollBarAttribute_VH.set(NSAccessibilityHorizontalScrollBarAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVerticalScrollBarAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityVerticalScrollBarAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVerticalScrollBarAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVerticalScrollBarAttribute").orElseThrow() }
private val NSAccessibilityVerticalScrollBarAttribute_VH: VarHandle by lazy { NSAccessibilityVerticalScrollBarAttribute_LAYOUT.varHandle() }

var NSAccessibilityVerticalScrollBarAttribute: MemorySegment
    get() = NSAccessibilityVerticalScrollBarAttribute_VH.get(NSAccessibilityVerticalScrollBarAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVerticalScrollBarAttribute_VH.set(NSAccessibilityVerticalScrollBarAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityOverflowButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityOverflowButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityOverflowButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityOverflowButtonAttribute").orElseThrow() }
private val NSAccessibilityOverflowButtonAttribute_VH: VarHandle by lazy { NSAccessibilityOverflowButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityOverflowButtonAttribute: MemorySegment
    get() = NSAccessibilityOverflowButtonAttribute_VH.get(NSAccessibilityOverflowButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityOverflowButtonAttribute_VH.set(NSAccessibilityOverflowButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityIncrementButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityIncrementButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityIncrementButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityIncrementButtonAttribute").orElseThrow() }
private val NSAccessibilityIncrementButtonAttribute_VH: VarHandle by lazy { NSAccessibilityIncrementButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityIncrementButtonAttribute: MemorySegment
    get() = NSAccessibilityIncrementButtonAttribute_VH.get(NSAccessibilityIncrementButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityIncrementButtonAttribute_VH.set(NSAccessibilityIncrementButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDecrementButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityDecrementButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDecrementButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDecrementButtonAttribute").orElseThrow() }
private val NSAccessibilityDecrementButtonAttribute_VH: VarHandle by lazy { NSAccessibilityDecrementButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityDecrementButtonAttribute: MemorySegment
    get() = NSAccessibilityDecrementButtonAttribute_VH.get(NSAccessibilityDecrementButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDecrementButtonAttribute_VH.set(NSAccessibilityDecrementButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFilenameAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityFilenameAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFilenameAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFilenameAttribute").orElseThrow() }
private val NSAccessibilityFilenameAttribute_VH: VarHandle by lazy { NSAccessibilityFilenameAttribute_LAYOUT.varHandle() }

var NSAccessibilityFilenameAttribute: MemorySegment
    get() = NSAccessibilityFilenameAttribute_VH.get(NSAccessibilityFilenameAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFilenameAttribute_VH.set(NSAccessibilityFilenameAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityExpandedAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityExpandedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityExpandedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityExpandedAttribute").orElseThrow() }
private val NSAccessibilityExpandedAttribute_VH: VarHandle by lazy { NSAccessibilityExpandedAttribute_LAYOUT.varHandle() }

var NSAccessibilityExpandedAttribute: MemorySegment
    get() = NSAccessibilityExpandedAttribute_VH.get(NSAccessibilityExpandedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityExpandedAttribute_VH.set(NSAccessibilityExpandedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySelectedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedAttribute").orElseThrow() }
private val NSAccessibilitySelectedAttribute_VH: VarHandle by lazy { NSAccessibilitySelectedAttribute_LAYOUT.varHandle() }

var NSAccessibilitySelectedAttribute: MemorySegment
    get() = NSAccessibilitySelectedAttribute_VH.get(NSAccessibilitySelectedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedAttribute_VH.set(NSAccessibilitySelectedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySplittersAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySplittersAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySplittersAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySplittersAttribute").orElseThrow() }
private val NSAccessibilitySplittersAttribute_VH: VarHandle by lazy { NSAccessibilitySplittersAttribute_LAYOUT.varHandle() }

var NSAccessibilitySplittersAttribute: MemorySegment
    get() = NSAccessibilitySplittersAttribute_VH.get(NSAccessibilitySplittersAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySplittersAttribute_VH.set(NSAccessibilitySplittersAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDocumentAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDocumentAttribute").orElseThrow() }
private val NSAccessibilityDocumentAttribute_VH: VarHandle by lazy { NSAccessibilityDocumentAttribute_LAYOUT.varHandle() }

var NSAccessibilityDocumentAttribute: MemorySegment
    get() = NSAccessibilityDocumentAttribute_VH.get(NSAccessibilityDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDocumentAttribute_VH.set(NSAccessibilityDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityActivationPointAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityActivationPointAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityActivationPointAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityActivationPointAttribute").orElseThrow() }
private val NSAccessibilityActivationPointAttribute_VH: VarHandle by lazy { NSAccessibilityActivationPointAttribute_LAYOUT.varHandle() }

var NSAccessibilityActivationPointAttribute: MemorySegment
    get() = NSAccessibilityActivationPointAttribute_VH.get(NSAccessibilityActivationPointAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityActivationPointAttribute_VH.set(NSAccessibilityActivationPointAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityURLAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityURLAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityURLAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityURLAttribute").orElseThrow() }
private val NSAccessibilityURLAttribute_VH: VarHandle by lazy { NSAccessibilityURLAttribute_LAYOUT.varHandle() }

var NSAccessibilityURLAttribute: MemorySegment
    get() = NSAccessibilityURLAttribute_VH.get(NSAccessibilityURLAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityURLAttribute_VH.set(NSAccessibilityURLAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityIndexAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityIndexAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityIndexAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityIndexAttribute").orElseThrow() }
private val NSAccessibilityIndexAttribute_VH: VarHandle by lazy { NSAccessibilityIndexAttribute_LAYOUT.varHandle() }

var NSAccessibilityIndexAttribute: MemorySegment
    get() = NSAccessibilityIndexAttribute_VH.get(NSAccessibilityIndexAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityIndexAttribute_VH.set(NSAccessibilityIndexAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRowCountAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityRowCountAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRowCountAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRowCountAttribute").orElseThrow() }
private val NSAccessibilityRowCountAttribute_VH: VarHandle by lazy { NSAccessibilityRowCountAttribute_LAYOUT.varHandle() }

var NSAccessibilityRowCountAttribute: MemorySegment
    get() = NSAccessibilityRowCountAttribute_VH.get(NSAccessibilityRowCountAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRowCountAttribute_VH.set(NSAccessibilityRowCountAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityColumnCountAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityColumnCountAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityColumnCountAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityColumnCountAttribute").orElseThrow() }
private val NSAccessibilityColumnCountAttribute_VH: VarHandle by lazy { NSAccessibilityColumnCountAttribute_LAYOUT.varHandle() }

var NSAccessibilityColumnCountAttribute: MemorySegment
    get() = NSAccessibilityColumnCountAttribute_VH.get(NSAccessibilityColumnCountAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityColumnCountAttribute_VH.set(NSAccessibilityColumnCountAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityOrderedByRowAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityOrderedByRowAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityOrderedByRowAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityOrderedByRowAttribute").orElseThrow() }
private val NSAccessibilityOrderedByRowAttribute_VH: VarHandle by lazy { NSAccessibilityOrderedByRowAttribute_LAYOUT.varHandle() }

var NSAccessibilityOrderedByRowAttribute: MemorySegment
    get() = NSAccessibilityOrderedByRowAttribute_VH.get(NSAccessibilityOrderedByRowAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityOrderedByRowAttribute_VH.set(NSAccessibilityOrderedByRowAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWarningValueAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityWarningValueAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWarningValueAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWarningValueAttribute").orElseThrow() }
private val NSAccessibilityWarningValueAttribute_VH: VarHandle by lazy { NSAccessibilityWarningValueAttribute_LAYOUT.varHandle() }

var NSAccessibilityWarningValueAttribute: MemorySegment
    get() = NSAccessibilityWarningValueAttribute_VH.get(NSAccessibilityWarningValueAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWarningValueAttribute_VH.set(NSAccessibilityWarningValueAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCriticalValueAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityCriticalValueAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCriticalValueAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCriticalValueAttribute").orElseThrow() }
private val NSAccessibilityCriticalValueAttribute_VH: VarHandle by lazy { NSAccessibilityCriticalValueAttribute_LAYOUT.varHandle() }

var NSAccessibilityCriticalValueAttribute: MemorySegment
    get() = NSAccessibilityCriticalValueAttribute_VH.get(NSAccessibilityCriticalValueAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCriticalValueAttribute_VH.set(NSAccessibilityCriticalValueAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPlaceholderValueAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityPlaceholderValueAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPlaceholderValueAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPlaceholderValueAttribute").orElseThrow() }
private val NSAccessibilityPlaceholderValueAttribute_VH: VarHandle by lazy { NSAccessibilityPlaceholderValueAttribute_LAYOUT.varHandle() }

var NSAccessibilityPlaceholderValueAttribute: MemorySegment
    get() = NSAccessibilityPlaceholderValueAttribute_VH.get(NSAccessibilityPlaceholderValueAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPlaceholderValueAttribute_VH.set(NSAccessibilityPlaceholderValueAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityContainsProtectedContentAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityContainsProtectedContentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityContainsProtectedContentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityContainsProtectedContentAttribute").orElseThrow() }
private val NSAccessibilityContainsProtectedContentAttribute_VH: VarHandle by lazy { NSAccessibilityContainsProtectedContentAttribute_LAYOUT.varHandle() }

var NSAccessibilityContainsProtectedContentAttribute: MemorySegment
    get() = NSAccessibilityContainsProtectedContentAttribute_VH.get(NSAccessibilityContainsProtectedContentAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityContainsProtectedContentAttribute_VH.set(NSAccessibilityContainsProtectedContentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAlternateUIVisibleAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityAlternateUIVisibleAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAlternateUIVisibleAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAlternateUIVisibleAttribute").orElseThrow() }
private val NSAccessibilityAlternateUIVisibleAttribute_VH: VarHandle by lazy { NSAccessibilityAlternateUIVisibleAttribute_LAYOUT.varHandle() }

var NSAccessibilityAlternateUIVisibleAttribute: MemorySegment
    get() = NSAccessibilityAlternateUIVisibleAttribute_VH.get(NSAccessibilityAlternateUIVisibleAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAlternateUIVisibleAttribute_VH.set(NSAccessibilityAlternateUIVisibleAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRequiredAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityRequiredAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRequiredAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRequiredAttribute").orElseThrow() }
private val NSAccessibilityRequiredAttribute_VH: VarHandle by lazy { NSAccessibilityRequiredAttribute_LAYOUT.varHandle() }

var NSAccessibilityRequiredAttribute: MemorySegment
    get() = NSAccessibilityRequiredAttribute_VH.get(NSAccessibilityRequiredAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRequiredAttribute_VH.set(NSAccessibilityRequiredAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAutoInteractableAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityAutoInteractableAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAutoInteractableAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAutoInteractableAttribute").orElseThrow() }
private val NSAccessibilityAutoInteractableAttribute_VH: VarHandle by lazy { NSAccessibilityAutoInteractableAttribute_LAYOUT.varHandle() }

var NSAccessibilityAutoInteractableAttribute: MemorySegment
    get() = NSAccessibilityAutoInteractableAttribute_VH.get(NSAccessibilityAutoInteractableAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAutoInteractableAttribute_VH.set(NSAccessibilityAutoInteractableAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDateTimeComponentsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityDateTimeComponentsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDateTimeComponentsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDateTimeComponentsAttribute").orElseThrow() }
private val NSAccessibilityDateTimeComponentsAttribute_VH: VarHandle by lazy { NSAccessibilityDateTimeComponentsAttribute_LAYOUT.varHandle() }

var NSAccessibilityDateTimeComponentsAttribute: MemorySegment
    get() = NSAccessibilityDateTimeComponentsAttribute_VH.get(NSAccessibilityDateTimeComponentsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDateTimeComponentsAttribute_VH.set(NSAccessibilityDateTimeComponentsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityEmbeddedImageDescriptionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityEmbeddedImageDescriptionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityEmbeddedImageDescriptionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityEmbeddedImageDescriptionAttribute").orElseThrow() }
private val NSAccessibilityEmbeddedImageDescriptionAttribute_VH: VarHandle by lazy { NSAccessibilityEmbeddedImageDescriptionAttribute_LAYOUT.varHandle() }

var NSAccessibilityEmbeddedImageDescriptionAttribute: MemorySegment
    get() = NSAccessibilityEmbeddedImageDescriptionAttribute_VH.get(NSAccessibilityEmbeddedImageDescriptionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityEmbeddedImageDescriptionAttribute_VH.set(NSAccessibilityEmbeddedImageDescriptionAttribute_SEGMENT, value)

