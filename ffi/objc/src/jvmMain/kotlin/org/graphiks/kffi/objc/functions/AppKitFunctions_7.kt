package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : NSAppleScriptErrorNumber (Void)*
 */
private val NSAppleScriptErrorNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppleScriptErrorNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppleScriptErrorNumber").orElseThrow() }
private val NSAppleScriptErrorNumber_VH: VarHandle by lazy { NSAppleScriptErrorNumber_LAYOUT.varHandle() }

var NSAppleScriptErrorNumber: MemorySegment
    get() = NSAppleScriptErrorNumber_VH.get(NSAppleScriptErrorNumber_SEGMENT) as MemorySegment
    set(value) = NSAppleScriptErrorNumber_VH.set(NSAppleScriptErrorNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppleScriptErrorAppName (Void)*
 */
private val NSAppleScriptErrorAppName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppleScriptErrorAppName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppleScriptErrorAppName").orElseThrow() }
private val NSAppleScriptErrorAppName_VH: VarHandle by lazy { NSAppleScriptErrorAppName_LAYOUT.varHandle() }

var NSAppleScriptErrorAppName: MemorySegment
    get() = NSAppleScriptErrorAppName_VH.get(NSAppleScriptErrorAppName_SEGMENT) as MemorySegment
    set(value) = NSAppleScriptErrorAppName_VH.set(NSAppleScriptErrorAppName_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppleScriptErrorBriefMessage (Void)*
 */
private val NSAppleScriptErrorBriefMessage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppleScriptErrorBriefMessage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppleScriptErrorBriefMessage").orElseThrow() }
private val NSAppleScriptErrorBriefMessage_VH: VarHandle by lazy { NSAppleScriptErrorBriefMessage_LAYOUT.varHandle() }

var NSAppleScriptErrorBriefMessage: MemorySegment
    get() = NSAppleScriptErrorBriefMessage_VH.get(NSAppleScriptErrorBriefMessage_SEGMENT) as MemorySegment
    set(value) = NSAppleScriptErrorBriefMessage_VH.set(NSAppleScriptErrorBriefMessage_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppleScriptErrorRange (Void)*
 */
private val NSAppleScriptErrorRange_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppleScriptErrorRange_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppleScriptErrorRange").orElseThrow() }
private val NSAppleScriptErrorRange_VH: VarHandle by lazy { NSAppleScriptErrorRange_LAYOUT.varHandle() }

var NSAppleScriptErrorRange: MemorySegment
    get() = NSAppleScriptErrorRange_VH.get(NSAppleScriptErrorRange_SEGMENT) as MemorySegment
    set(value) = NSAppleScriptErrorRange_VH.set(NSAppleScriptErrorRange_SEGMENT, value)

/**
 * {@snippet lang=c : NSConnectionReplyMode (Void)*
 */
private val NSConnectionReplyMode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSConnectionReplyMode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSConnectionReplyMode").orElseThrow() }
private val NSConnectionReplyMode_VH: VarHandle by lazy { NSConnectionReplyMode_LAYOUT.varHandle() }

var NSConnectionReplyMode: MemorySegment
    get() = NSConnectionReplyMode_VH.get(NSConnectionReplyMode_SEGMENT) as MemorySegment
    set(value) = NSConnectionReplyMode_VH.set(NSConnectionReplyMode_SEGMENT, value)

/**
 * {@snippet lang=c : NSConnectionDidDieNotification (Void)*
 */
private val NSConnectionDidDieNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSConnectionDidDieNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSConnectionDidDieNotification").orElseThrow() }
private val NSConnectionDidDieNotification_VH: VarHandle by lazy { NSConnectionDidDieNotification_LAYOUT.varHandle() }

var NSConnectionDidDieNotification: MemorySegment
    get() = NSConnectionDidDieNotification_VH.get(NSConnectionDidDieNotification_SEGMENT) as MemorySegment
    set(value) = NSConnectionDidDieNotification_VH.set(NSConnectionDidDieNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSFailedAuthenticationException (Void)*
 */
private val NSFailedAuthenticationException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFailedAuthenticationException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFailedAuthenticationException").orElseThrow() }
private val NSFailedAuthenticationException_VH: VarHandle by lazy { NSFailedAuthenticationException_LAYOUT.varHandle() }

var NSFailedAuthenticationException: MemorySegment
    get() = NSFailedAuthenticationException_VH.get(NSFailedAuthenticationException_SEGMENT) as MemorySegment
    set(value) = NSFailedAuthenticationException_VH.set(NSFailedAuthenticationException_SEGMENT, value)

/**
 * {@snippet lang=c : NSConnectionDidInitializeNotification (Void)*
 */
private val NSConnectionDidInitializeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSConnectionDidInitializeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSConnectionDidInitializeNotification").orElseThrow() }
private val NSConnectionDidInitializeNotification_VH: VarHandle by lazy { NSConnectionDidInitializeNotification_LAYOUT.varHandle() }

var NSConnectionDidInitializeNotification: MemorySegment
    get() = NSConnectionDidInitializeNotification_VH.get(NSConnectionDidInitializeNotification_SEGMENT) as MemorySegment
    set(value) = NSConnectionDidInitializeNotification_VH.set(NSConnectionDidInitializeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocalNotificationCenterType typedef const NSDistributedNotificationCenterType = (Void)*
 */
private val NSLocalNotificationCenterType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocalNotificationCenterType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocalNotificationCenterType").orElseThrow() }
private val NSLocalNotificationCenterType_VH: VarHandle by lazy { NSLocalNotificationCenterType_LAYOUT.varHandle() }

var NSLocalNotificationCenterType: MemorySegment
    get() = NSLocalNotificationCenterType_VH.get(NSLocalNotificationCenterType_SEGMENT) as MemorySegment
    set(value) = NSLocalNotificationCenterType_VH.set(NSLocalNotificationCenterType_SEGMENT, value)

/**
 * {@snippet lang=c : NSTaskDidTerminateNotification typedef const NSNotificationName = (Void)*
 */
private val NSTaskDidTerminateNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTaskDidTerminateNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTaskDidTerminateNotification").orElseThrow() }
private val NSTaskDidTerminateNotification_VH: VarHandle by lazy { NSTaskDidTerminateNotification_LAYOUT.varHandle() }

var NSTaskDidTerminateNotification: MemorySegment
    get() = NSTaskDidTerminateNotification_VH.get(NSTaskDidTerminateNotification_SEGMENT) as MemorySegment
    set(value) = NSTaskDidTerminateNotification_VH.set(NSTaskDidTerminateNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppleEventTimeOutDefault Double
 */
private val NSAppleEventTimeOutDefault_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSAppleEventTimeOutDefault_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppleEventTimeOutDefault").orElseThrow() }
private val NSAppleEventTimeOutDefault_VH: VarHandle by lazy { NSAppleEventTimeOutDefault_LAYOUT.varHandle() }

var NSAppleEventTimeOutDefault: Double
    get() = NSAppleEventTimeOutDefault_VH.get(NSAppleEventTimeOutDefault_SEGMENT) as Double
    set(value) = NSAppleEventTimeOutDefault_VH.set(NSAppleEventTimeOutDefault_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppleEventTimeOutNone Double
 */
private val NSAppleEventTimeOutNone_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSAppleEventTimeOutNone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppleEventTimeOutNone").orElseThrow() }
private val NSAppleEventTimeOutNone_VH: VarHandle by lazy { NSAppleEventTimeOutNone_LAYOUT.varHandle() }

var NSAppleEventTimeOutNone: Double
    get() = NSAppleEventTimeOutNone_VH.get(NSAppleEventTimeOutNone_SEGMENT) as Double
    set(value) = NSAppleEventTimeOutNone_VH.set(NSAppleEventTimeOutNone_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppleEventManagerWillProcessFirstEventNotification typedef const NSNotificationName = (Void)*
 */
private val NSAppleEventManagerWillProcessFirstEventNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppleEventManagerWillProcessFirstEventNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppleEventManagerWillProcessFirstEventNotification").orElseThrow() }
private val NSAppleEventManagerWillProcessFirstEventNotification_VH: VarHandle by lazy { NSAppleEventManagerWillProcessFirstEventNotification_LAYOUT.varHandle() }

var NSAppleEventManagerWillProcessFirstEventNotification: MemorySegment
    get() = NSAppleEventManagerWillProcessFirstEventNotification_VH.get(NSAppleEventManagerWillProcessFirstEventNotification_SEGMENT) as MemorySegment
    set(value) = NSAppleEventManagerWillProcessFirstEventNotification_VH.set(NSAppleEventManagerWillProcessFirstEventNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSClassDescriptionNeededForClassNotification typedef const NSNotificationName = (Void)*
 */
private val NSClassDescriptionNeededForClassNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSClassDescriptionNeededForClassNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSClassDescriptionNeededForClassNotification").orElseThrow() }
private val NSClassDescriptionNeededForClassNotification_VH: VarHandle by lazy { NSClassDescriptionNeededForClassNotification_LAYOUT.varHandle() }

var NSClassDescriptionNeededForClassNotification: MemorySegment
    get() = NSClassDescriptionNeededForClassNotification_VH.get(NSClassDescriptionNeededForClassNotification_SEGMENT) as MemorySegment
    set(value) = NSClassDescriptionNeededForClassNotification_VH.set(NSClassDescriptionNeededForClassNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeForHFSTypeCode typedef NSString = (Void)*(typedef OSType = UNSIGNED = Int)
 */
private val NSFileTypeForHFSTypeCode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val NSFileTypeForHFSTypeCode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSFileTypeForHFSTypeCode").orElseThrow()
private val NSFileTypeForHFSTypeCode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSFileTypeForHFSTypeCode_ADDR, NSFileTypeForHFSTypeCode_DESC)

fun NSFileTypeForHFSTypeCode(arg0: Int): MemorySegment {
    try {
        return NSFileTypeForHFSTypeCode_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHFSTypeCodeFromFileType typedef OSType = UNSIGNED = Int((Void)*)
 */
private val NSHFSTypeCodeFromFileType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val NSHFSTypeCodeFromFileType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHFSTypeCodeFromFileType").orElseThrow()
private val NSHFSTypeCodeFromFileType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHFSTypeCodeFromFileType_ADDR, NSHFSTypeCodeFromFileType_DESC)

fun NSHFSTypeCodeFromFileType(arg0: MemorySegment): Int {
    try {
        return NSHFSTypeCodeFromFileType_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHFSTypeOfFile typedef NSString = (Void)*((Void)*)
 */
private val NSHFSTypeOfFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSHFSTypeOfFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHFSTypeOfFile").orElseThrow()
private val NSHFSTypeOfFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHFSTypeOfFile_ADDR, NSHFSTypeOfFile_DESC)

fun NSHFSTypeOfFile(arg0: MemorySegment): MemorySegment {
    try {
        return NSHFSTypeOfFile_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSOperationNotSupportedForKeyException (Void)*
 */
private val NSOperationNotSupportedForKeyException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOperationNotSupportedForKeyException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOperationNotSupportedForKeyException").orElseThrow() }
private val NSOperationNotSupportedForKeyException_VH: VarHandle by lazy { NSOperationNotSupportedForKeyException_LAYOUT.varHandle() }

var NSOperationNotSupportedForKeyException: MemorySegment
    get() = NSOperationNotSupportedForKeyException_VH.get(NSOperationNotSupportedForKeyException_SEGMENT) as MemorySegment
    set(value) = NSOperationNotSupportedForKeyException_VH.set(NSOperationNotSupportedForKeyException_SEGMENT, value)

/**
 * {@snippet lang=c : NSGrammarRange (Void)*
 */
private val NSGrammarRange_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGrammarRange_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGrammarRange").orElseThrow() }
private val NSGrammarRange_VH: VarHandle by lazy { NSGrammarRange_LAYOUT.varHandle() }

var NSGrammarRange: MemorySegment
    get() = NSGrammarRange_VH.get(NSGrammarRange_SEGMENT) as MemorySegment
    set(value) = NSGrammarRange_VH.set(NSGrammarRange_SEGMENT, value)

/**
 * {@snippet lang=c : NSGrammarUserDescription (Void)*
 */
private val NSGrammarUserDescription_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGrammarUserDescription_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGrammarUserDescription").orElseThrow() }
private val NSGrammarUserDescription_VH: VarHandle by lazy { NSGrammarUserDescription_LAYOUT.varHandle() }

var NSGrammarUserDescription: MemorySegment
    get() = NSGrammarUserDescription_VH.get(NSGrammarUserDescription_SEGMENT) as MemorySegment
    set(value) = NSGrammarUserDescription_VH.set(NSGrammarUserDescription_SEGMENT, value)

/**
 * {@snippet lang=c : NSGrammarCorrections (Void)*
 */
private val NSGrammarCorrections_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGrammarCorrections_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGrammarCorrections").orElseThrow() }
private val NSGrammarCorrections_VH: VarHandle by lazy { NSGrammarCorrections_LAYOUT.varHandle() }

var NSGrammarCorrections: MemorySegment
    get() = NSGrammarCorrections_VH.get(NSGrammarCorrections_SEGMENT) as MemorySegment
    set(value) = NSGrammarCorrections_VH.set(NSGrammarCorrections_SEGMENT, value)

/**
 * {@snippet lang=c : NSUserNotificationDefaultSoundName (Void)*
 */
private val NSUserNotificationDefaultSoundName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUserNotificationDefaultSoundName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUserNotificationDefaultSoundName").orElseThrow() }
private val NSUserNotificationDefaultSoundName_VH: VarHandle by lazy { NSUserNotificationDefaultSoundName_LAYOUT.varHandle() }

var NSUserNotificationDefaultSoundName: MemorySegment
    get() = NSUserNotificationDefaultSoundName_VH.get(NSUserNotificationDefaultSoundName_SEGMENT) as MemorySegment
    set(value) = NSUserNotificationDefaultSoundName_VH.set(NSUserNotificationDefaultSoundName_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalibratedWhiteColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSCalibratedWhiteColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalibratedWhiteColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalibratedWhiteColorSpace").orElseThrow() }
private val NSCalibratedWhiteColorSpace_VH: VarHandle by lazy { NSCalibratedWhiteColorSpace_LAYOUT.varHandle() }

var NSCalibratedWhiteColorSpace: MemorySegment
    get() = NSCalibratedWhiteColorSpace_VH.get(NSCalibratedWhiteColorSpace_SEGMENT) as MemorySegment
    set(value) = NSCalibratedWhiteColorSpace_VH.set(NSCalibratedWhiteColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalibratedRGBColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSCalibratedRGBColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalibratedRGBColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalibratedRGBColorSpace").orElseThrow() }
private val NSCalibratedRGBColorSpace_VH: VarHandle by lazy { NSCalibratedRGBColorSpace_LAYOUT.varHandle() }

var NSCalibratedRGBColorSpace: MemorySegment
    get() = NSCalibratedRGBColorSpace_VH.get(NSCalibratedRGBColorSpace_SEGMENT) as MemorySegment
    set(value) = NSCalibratedRGBColorSpace_VH.set(NSCalibratedRGBColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceWhiteColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSDeviceWhiteColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceWhiteColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceWhiteColorSpace").orElseThrow() }
private val NSDeviceWhiteColorSpace_VH: VarHandle by lazy { NSDeviceWhiteColorSpace_LAYOUT.varHandle() }

var NSDeviceWhiteColorSpace: MemorySegment
    get() = NSDeviceWhiteColorSpace_VH.get(NSDeviceWhiteColorSpace_SEGMENT) as MemorySegment
    set(value) = NSDeviceWhiteColorSpace_VH.set(NSDeviceWhiteColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceRGBColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSDeviceRGBColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceRGBColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceRGBColorSpace").orElseThrow() }
private val NSDeviceRGBColorSpace_VH: VarHandle by lazy { NSDeviceRGBColorSpace_LAYOUT.varHandle() }

var NSDeviceRGBColorSpace: MemorySegment
    get() = NSDeviceRGBColorSpace_VH.get(NSDeviceRGBColorSpace_SEGMENT) as MemorySegment
    set(value) = NSDeviceRGBColorSpace_VH.set(NSDeviceRGBColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceCMYKColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSDeviceCMYKColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceCMYKColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceCMYKColorSpace").orElseThrow() }
private val NSDeviceCMYKColorSpace_VH: VarHandle by lazy { NSDeviceCMYKColorSpace_LAYOUT.varHandle() }

var NSDeviceCMYKColorSpace: MemorySegment
    get() = NSDeviceCMYKColorSpace_VH.get(NSDeviceCMYKColorSpace_SEGMENT) as MemorySegment
    set(value) = NSDeviceCMYKColorSpace_VH.set(NSDeviceCMYKColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSNamedColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSNamedColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSNamedColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNamedColorSpace").orElseThrow() }
private val NSNamedColorSpace_VH: VarHandle by lazy { NSNamedColorSpace_LAYOUT.varHandle() }

var NSNamedColorSpace: MemorySegment
    get() = NSNamedColorSpace_VH.get(NSNamedColorSpace_SEGMENT) as MemorySegment
    set(value) = NSNamedColorSpace_VH.set(NSNamedColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSPatternColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSPatternColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPatternColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPatternColorSpace").orElseThrow() }
private val NSPatternColorSpace_VH: VarHandle by lazy { NSPatternColorSpace_LAYOUT.varHandle() }

var NSPatternColorSpace: MemorySegment
    get() = NSPatternColorSpace_VH.get(NSPatternColorSpace_SEGMENT) as MemorySegment
    set(value) = NSPatternColorSpace_VH.set(NSPatternColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSCustomColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSCustomColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCustomColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCustomColorSpace").orElseThrow() }
private val NSCustomColorSpace_VH: VarHandle by lazy { NSCustomColorSpace_LAYOUT.varHandle() }

var NSCustomColorSpace: MemorySegment
    get() = NSCustomColorSpace_VH.get(NSCustomColorSpace_SEGMENT) as MemorySegment
    set(value) = NSCustomColorSpace_VH.set(NSCustomColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalibratedBlackColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSCalibratedBlackColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalibratedBlackColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalibratedBlackColorSpace").orElseThrow() }
private val NSCalibratedBlackColorSpace_VH: VarHandle by lazy { NSCalibratedBlackColorSpace_LAYOUT.varHandle() }

var NSCalibratedBlackColorSpace: MemorySegment
    get() = NSCalibratedBlackColorSpace_VH.get(NSCalibratedBlackColorSpace_SEGMENT) as MemorySegment
    set(value) = NSCalibratedBlackColorSpace_VH.set(NSCalibratedBlackColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceBlackColorSpace typedef NSColorSpaceName = typedef NSString = (Void)*
 */
private val NSDeviceBlackColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceBlackColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceBlackColorSpace").orElseThrow() }
private val NSDeviceBlackColorSpace_VH: VarHandle by lazy { NSDeviceBlackColorSpace_LAYOUT.varHandle() }

var NSDeviceBlackColorSpace: MemorySegment
    get() = NSDeviceBlackColorSpace_VH.get(NSDeviceBlackColorSpace_SEGMENT) as MemorySegment
    set(value) = NSDeviceBlackColorSpace_VH.set(NSDeviceBlackColorSpace_SEGMENT, value)

/**
 * {@snippet lang=c : NSNumberOfColorComponents typedef NSInteger = Long(typedef NSColorSpaceName = typedef NSString = (Void)*)
 */
private val NSNumberOfColorComponents_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSNumberOfColorComponents_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSNumberOfColorComponents").orElseThrow()
private val NSNumberOfColorComponents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSNumberOfColorComponents_ADDR, NSNumberOfColorComponents_DESC)

fun NSNumberOfColorComponents(arg0: MemorySegment): Long {
    try {
        return NSNumberOfColorComponents_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAvailableWindowDepths (typedef NSWindowDepth = <error: enum NSWindowDepth>)*()
 */
private val NSAvailableWindowDepths_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val NSAvailableWindowDepths_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAvailableWindowDepths").orElseThrow()
private val NSAvailableWindowDepths_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAvailableWindowDepths_ADDR, NSAvailableWindowDepths_DESC)

fun NSAvailableWindowDepths(): MemorySegment {
    try {
        return NSAvailableWindowDepths_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSWhite typedef const CGFloat = Double
 */
private val NSWhite_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSWhite_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWhite").orElseThrow() }
private val NSWhite_VH: VarHandle by lazy { NSWhite_LAYOUT.varHandle() }

var NSWhite: Double
    get() = NSWhite_VH.get(NSWhite_SEGMENT) as Double
    set(value) = NSWhite_VH.set(NSWhite_SEGMENT, value)

/**
 * {@snippet lang=c : NSLightGray typedef const CGFloat = Double
 */
private val NSLightGray_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSLightGray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLightGray").orElseThrow() }
private val NSLightGray_VH: VarHandle by lazy { NSLightGray_LAYOUT.varHandle() }

var NSLightGray: Double
    get() = NSLightGray_VH.get(NSLightGray_SEGMENT) as Double
    set(value) = NSLightGray_VH.set(NSLightGray_SEGMENT, value)

/**
 * {@snippet lang=c : NSDarkGray typedef const CGFloat = Double
 */
private val NSDarkGray_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSDarkGray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDarkGray").orElseThrow() }
private val NSDarkGray_VH: VarHandle by lazy { NSDarkGray_LAYOUT.varHandle() }

var NSDarkGray: Double
    get() = NSDarkGray_VH.get(NSDarkGray_SEGMENT) as Double
    set(value) = NSDarkGray_VH.set(NSDarkGray_SEGMENT, value)

/**
 * {@snippet lang=c : NSBlack typedef const CGFloat = Double
 */
private val NSBlack_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSBlack_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBlack").orElseThrow() }
private val NSBlack_VH: VarHandle by lazy { NSBlack_LAYOUT.varHandle() }

var NSBlack: Double
    get() = NSBlack_VH.get(NSBlack_SEGMENT) as Double
    set(value) = NSBlack_VH.set(NSBlack_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceResolution typedef NSDeviceDescriptionKey = typedef NSString = (Void)*
 */
private val NSDeviceResolution_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceResolution_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceResolution").orElseThrow() }
private val NSDeviceResolution_VH: VarHandle by lazy { NSDeviceResolution_LAYOUT.varHandle() }

var NSDeviceResolution: MemorySegment
    get() = NSDeviceResolution_VH.get(NSDeviceResolution_SEGMENT) as MemorySegment
    set(value) = NSDeviceResolution_VH.set(NSDeviceResolution_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceColorSpaceName typedef NSDeviceDescriptionKey = typedef NSString = (Void)*
 */
private val NSDeviceColorSpaceName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceColorSpaceName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceColorSpaceName").orElseThrow() }
private val NSDeviceColorSpaceName_VH: VarHandle by lazy { NSDeviceColorSpaceName_LAYOUT.varHandle() }

var NSDeviceColorSpaceName: MemorySegment
    get() = NSDeviceColorSpaceName_VH.get(NSDeviceColorSpaceName_SEGMENT) as MemorySegment
    set(value) = NSDeviceColorSpaceName_VH.set(NSDeviceColorSpaceName_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceBitsPerSample typedef NSDeviceDescriptionKey = typedef NSString = (Void)*
 */
private val NSDeviceBitsPerSample_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceBitsPerSample_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceBitsPerSample").orElseThrow() }
private val NSDeviceBitsPerSample_VH: VarHandle by lazy { NSDeviceBitsPerSample_LAYOUT.varHandle() }

var NSDeviceBitsPerSample: MemorySegment
    get() = NSDeviceBitsPerSample_VH.get(NSDeviceBitsPerSample_SEGMENT) as MemorySegment
    set(value) = NSDeviceBitsPerSample_VH.set(NSDeviceBitsPerSample_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceIsScreen typedef NSDeviceDescriptionKey = typedef NSString = (Void)*
 */
private val NSDeviceIsScreen_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceIsScreen_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceIsScreen").orElseThrow() }
private val NSDeviceIsScreen_VH: VarHandle by lazy { NSDeviceIsScreen_LAYOUT.varHandle() }

var NSDeviceIsScreen: MemorySegment
    get() = NSDeviceIsScreen_VH.get(NSDeviceIsScreen_SEGMENT) as MemorySegment
    set(value) = NSDeviceIsScreen_VH.set(NSDeviceIsScreen_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceIsPrinter typedef NSDeviceDescriptionKey = typedef NSString = (Void)*
 */
private val NSDeviceIsPrinter_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceIsPrinter_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceIsPrinter").orElseThrow() }
private val NSDeviceIsPrinter_VH: VarHandle by lazy { NSDeviceIsPrinter_LAYOUT.varHandle() }

var NSDeviceIsPrinter: MemorySegment
    get() = NSDeviceIsPrinter_VH.get(NSDeviceIsPrinter_SEGMENT) as MemorySegment
    set(value) = NSDeviceIsPrinter_VH.set(NSDeviceIsPrinter_SEGMENT, value)

/**
 * {@snippet lang=c : NSDeviceSize typedef NSDeviceDescriptionKey = typedef NSString = (Void)*
 */
private val NSDeviceSize_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDeviceSize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDeviceSize").orElseThrow() }
private val NSDeviceSize_VH: VarHandle by lazy { NSDeviceSize_LAYOUT.varHandle() }

var NSDeviceSize: MemorySegment
    get() = NSDeviceSize_VH.get(NSDeviceSize_SEGMENT) as MemorySegment
    set(value) = NSDeviceSize_VH.set(NSDeviceSize_SEGMENT, value)

/**
 * {@snippet lang=c : NSRectFill Void(typedef NSRect = Declared(CGRect))
 */
private val NSRectFill_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout)
private val NSRectFill_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRectFill").orElseThrow()
private val NSRectFill_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRectFill_ADDR, NSRectFill_DESC)

fun NSRectFill(arg0: MemorySegment): Unit {
    try {
        NSRectFill_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRectFillList Void((typedef NSRect = Declared(CGRect))*,typedef NSInteger = Long)
 */
private val NSRectFillList_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSRectFillList_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRectFillList").orElseThrow()
private val NSRectFillList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRectFillList_ADDR, NSRectFillList_DESC)

fun NSRectFillList(arg0: MemorySegment, arg1: Long): Unit {
    try {
        NSRectFillList_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRectFillListWithGrays Void((typedef NSRect = Declared(CGRect))*,(typedef CGFloat = Double)*,typedef NSInteger = Long)
 */
private val NSRectFillListWithGrays_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSRectFillListWithGrays_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRectFillListWithGrays").orElseThrow()
private val NSRectFillListWithGrays_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRectFillListWithGrays_ADDR, NSRectFillListWithGrays_DESC)

fun NSRectFillListWithGrays(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        NSRectFillListWithGrays_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRectFillListWithColors Void((typedef NSRect = Declared(CGRect))*,((Void)*)*,typedef NSInteger = Long)
 */
private val NSRectFillListWithColors_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSRectFillListWithColors_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRectFillListWithColors").orElseThrow()
private val NSRectFillListWithColors_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRectFillListWithColors_ADDR, NSRectFillListWithColors_DESC)

fun NSRectFillListWithColors(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Unit {
    try {
        NSRectFillListWithColors_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSFrameRect Void(typedef NSRect = Declared(CGRect))
 */
private val NSFrameRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout)
private val NSFrameRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSFrameRect").orElseThrow()
private val NSFrameRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSFrameRect_ADDR, NSFrameRect_DESC)

fun NSFrameRect(arg0: MemorySegment): Unit {
    try {
        NSFrameRect_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSFrameRectWithWidth Void(typedef NSRect = Declared(CGRect),typedef CGFloat = Double)
 */
private val NSFrameRectWithWidth_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout, ValueLayout.JAVA_DOUBLE)
private val NSFrameRectWithWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSFrameRectWithWidth").orElseThrow()
private val NSFrameRectWithWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSFrameRectWithWidth_ADDR, NSFrameRectWithWidth_DESC)

fun NSFrameRectWithWidth(arg0: MemorySegment, arg1: Double): Unit {
    try {
        NSFrameRectWithWidth_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRectClip Void(typedef NSRect = Declared(CGRect))
 */
private val NSRectClip_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout)
private val NSRectClip_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRectClip").orElseThrow()
private val NSRectClip_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRectClip_ADDR, NSRectClip_DESC)

fun NSRectClip(arg0: MemorySegment): Unit {
    try {
        NSRectClip_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRectClipList Void((typedef NSRect = Declared(CGRect))*,typedef NSInteger = Long)
 */
private val NSRectClipList_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSRectClipList_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRectClipList").orElseThrow()
private val NSRectClipList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRectClipList_ADDR, NSRectClipList_DESC)

fun NSRectClipList(arg0: MemorySegment, arg1: Long): Unit {
    try {
        NSRectClipList_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawTiledRects typedef NSRect = Declared(CGRect)(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect),(typedef NSRectEdge = <error: enum NSRectEdge>)*,(typedef CGFloat = Double)*,typedef NSInteger = Long)
 */
private val NSDrawTiledRects_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, CGRect.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSDrawTiledRects_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawTiledRects").orElseThrow()
private val NSDrawTiledRects_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawTiledRects_ADDR, NSDrawTiledRects_DESC)

fun NSDrawTiledRects(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: Long): MemorySegment {
    try {
        return NSDrawTiledRects_HANDLE.invokeExact(allocator, arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawGrayBezel Void(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSDrawGrayBezel_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout, CGRect.layout)
private val NSDrawGrayBezel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawGrayBezel").orElseThrow()
private val NSDrawGrayBezel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawGrayBezel_ADDR, NSDrawGrayBezel_DESC)

fun NSDrawGrayBezel(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSDrawGrayBezel_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawGroove Void(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSDrawGroove_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout, CGRect.layout)
private val NSDrawGroove_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawGroove").orElseThrow()
private val NSDrawGroove_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawGroove_ADDR, NSDrawGroove_DESC)

fun NSDrawGroove(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSDrawGroove_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawWhiteBezel Void(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSDrawWhiteBezel_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout, CGRect.layout)
private val NSDrawWhiteBezel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawWhiteBezel").orElseThrow()
private val NSDrawWhiteBezel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawWhiteBezel_ADDR, NSDrawWhiteBezel_DESC)

fun NSDrawWhiteBezel(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSDrawWhiteBezel_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawButton Void(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSDrawButton_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout, CGRect.layout)
private val NSDrawButton_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawButton").orElseThrow()
private val NSDrawButton_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawButton_ADDR, NSDrawButton_DESC)

fun NSDrawButton(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSDrawButton_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSEraseRect Void(typedef NSRect = Declared(CGRect))
 */
private val NSEraseRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout)
private val NSEraseRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEraseRect").orElseThrow()
private val NSEraseRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEraseRect_ADDR, NSEraseRect_DESC)

fun NSEraseRect(arg0: MemorySegment): Unit {
    try {
        NSEraseRect_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSReadPixel typedef NSColor = (Void)*(typedef NSPoint = Declared(CGPoint))
 */
private val NSReadPixel_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGPoint.layout)
private val NSReadPixel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSReadPixel").orElseThrow()
private val NSReadPixel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSReadPixel_ADDR, NSReadPixel_DESC)

fun NSReadPixel(arg0: MemorySegment): MemorySegment {
    try {
        return NSReadPixel_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawBitmap Void(typedef NSRect = Declared(CGRect),typedef NSInteger = Long,typedef NSInteger = Long,typedef NSInteger = Long,typedef NSInteger = Long,typedef NSInteger = Long,typedef NSInteger = Long,typedef BOOL = Bool,typedef BOOL = Bool,typedef NSColorSpaceName = typedef NSString = (Void)*,((UNSIGNED = Char)*)*)
 */
private val NSDrawBitmap_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSDrawBitmap_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawBitmap").orElseThrow()
private val NSDrawBitmap_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawBitmap_ADDR, NSDrawBitmap_DESC)

fun NSDrawBitmap(arg0: MemorySegment, arg1: Long, arg2: Long, arg3: Long, arg4: Long, arg5: Long, arg6: Long, arg7: Boolean, arg8: Boolean, arg9: MemorySegment, arg10: MemorySegment): Unit {
    try {
        NSDrawBitmap_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHighlightRect Void(typedef NSRect = Declared(CGRect))
 */
private val NSHighlightRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout)
private val NSHighlightRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHighlightRect").orElseThrow()
private val NSHighlightRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHighlightRect_ADDR, NSHighlightRect_DESC)

fun NSHighlightRect(arg0: MemorySegment): Unit {
    try {
        NSHighlightRect_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSBeep Void()
 */
private val NSBeep_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val NSBeep_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSBeep").orElseThrow()
private val NSBeep_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSBeep_ADDR, NSBeep_DESC)

fun NSBeep(): Unit {
    try {
        NSBeep_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSGetWindowServerMemory typedef NSInteger = Long(typedef NSInteger = Long,(typedef NSInteger = Long)*,(typedef NSInteger = Long)*,(typedef NSString = (Void)*)*)
 */
private val NSGetWindowServerMemory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSGetWindowServerMemory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSGetWindowServerMemory").orElseThrow()
private val NSGetWindowServerMemory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSGetWindowServerMemory_ADDR, NSGetWindowServerMemory_DESC)

fun NSGetWindowServerMemory(arg0: Long, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Long {
    try {
        return NSGetWindowServerMemory_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawColorTiledRects typedef NSRect = Declared(CGRect)(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect),(typedef NSRectEdge = <error: enum NSRectEdge>)*,(typedef NSColor = (Void)*)*,typedef NSInteger = Long)
 */
private val NSDrawColorTiledRects_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, CGRect.layout, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val NSDrawColorTiledRects_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawColorTiledRects").orElseThrow()
private val NSDrawColorTiledRects_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawColorTiledRects_ADDR, NSDrawColorTiledRects_DESC)

fun NSDrawColorTiledRects(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: Long): MemorySegment {
    try {
        return NSDrawColorTiledRects_HANDLE.invokeExact(allocator, arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawDarkBezel Void(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSDrawDarkBezel_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout, CGRect.layout)
private val NSDrawDarkBezel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawDarkBezel").orElseThrow()
private val NSDrawDarkBezel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawDarkBezel_ADDR, NSDrawDarkBezel_DESC)

fun NSDrawDarkBezel(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSDrawDarkBezel_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawLightBezel Void(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSDrawLightBezel_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout, CGRect.layout)
private val NSDrawLightBezel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawLightBezel").orElseThrow()
private val NSDrawLightBezel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawLightBezel_ADDR, NSDrawLightBezel_DESC)

fun NSDrawLightBezel(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSDrawLightBezel_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDottedFrameRect Void(typedef NSRect = Declared(CGRect))
 */
private val NSDottedFrameRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout)
private val NSDottedFrameRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDottedFrameRect").orElseThrow()
private val NSDottedFrameRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDottedFrameRect_ADDR, NSDottedFrameRect_DESC)

fun NSDottedFrameRect(arg0: MemorySegment): Unit {
    try {
        NSDottedFrameRect_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDrawWindowBackground Void(typedef NSRect = Declared(CGRect))
 */
private val NSDrawWindowBackground_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(CGRect.layout)
private val NSDrawWindowBackground_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDrawWindowBackground").orElseThrow()
private val NSDrawWindowBackground_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDrawWindowBackground_ADDR, NSDrawWindowBackground_DESC)

fun NSDrawWindowBackground(arg0: MemorySegment): Unit {
    try {
        NSDrawWindowBackground_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDisableScreenUpdates Void()
 */
private val NSDisableScreenUpdates_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val NSDisableScreenUpdates_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDisableScreenUpdates").orElseThrow()
private val NSDisableScreenUpdates_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDisableScreenUpdates_ADDR, NSDisableScreenUpdates_DESC)

fun NSDisableScreenUpdates(): Unit {
    try {
        NSDisableScreenUpdates_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSEnableScreenUpdates Void()
 */
private val NSEnableScreenUpdates_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val NSEnableScreenUpdates_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEnableScreenUpdates").orElseThrow()
private val NSEnableScreenUpdates_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEnableScreenUpdates_ADDR, NSEnableScreenUpdates_DESC)

fun NSEnableScreenUpdates(): Unit {
    try {
        NSEnableScreenUpdates_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCountWindows Void((typedef NSInteger = Long)*)
 */
private val NSCountWindows_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSCountWindows_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCountWindows").orElseThrow()
private val NSCountWindows_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCountWindows_ADDR, NSCountWindows_DESC)

fun NSCountWindows(arg0: MemorySegment): Unit {
    try {
        NSCountWindows_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSWindowList Void(typedef NSInteger = Long,(typedef NSInteger = Long)*)
 */
private val NSWindowList_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSWindowList_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSWindowList").orElseThrow()
private val NSWindowList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSWindowList_ADDR, NSWindowList_DESC)

fun NSWindowList(arg0: Long, arg1: MemorySegment): Unit {
    try {
        NSWindowList_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCountWindowsForContext Void(typedef NSInteger = Long,(typedef NSInteger = Long)*)
 */
private val NSCountWindowsForContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSCountWindowsForContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCountWindowsForContext").orElseThrow()
private val NSCountWindowsForContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCountWindowsForContext_ADDR, NSCountWindowsForContext_DESC)

fun NSCountWindowsForContext(arg0: Long, arg1: MemorySegment): Unit {
    try {
        NSCountWindowsForContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSWindowListForContext Void(typedef NSInteger = Long,typedef NSInteger = Long,(typedef NSInteger = Long)*)
 */
private val NSWindowListForContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSWindowListForContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSWindowListForContext").orElseThrow()
private val NSWindowListForContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSWindowListForContext_ADDR, NSWindowListForContext_DESC)

fun NSWindowListForContext(arg0: Long, arg1: Long, arg2: MemorySegment): Unit {
    try {
        NSWindowListForContext_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCopyBits Void(typedef NSInteger = Long,typedef NSRect = Declared(CGRect),typedef NSPoint = Declared(CGPoint))
 */
private val NSCopyBits_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG, CGRect.layout, CGPoint.layout)
private val NSCopyBits_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCopyBits").orElseThrow()
private val NSCopyBits_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCopyBits_ADDR, NSCopyBits_DESC)

fun NSCopyBits(arg0: Long, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        NSCopyBits_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGDataProviderGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGDataProviderGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderGetTypeID").orElseThrow()
private val CGDataProviderGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderGetTypeID_ADDR, CGDataProviderGetTypeID_DESC)

fun CGDataProviderGetTypeID(): Long {
    try {
        return CGDataProviderGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderCreateSequential typedef CGDataProviderRef = (Declared(CGDataProvider))*((Void)*,(typedef CGDataProviderSequentialCallbacks = Declared(CGDataProviderSequentialCallbacks))*)
 */
private val CGDataProviderCreateSequential_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataProviderCreateSequential_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderCreateSequential").orElseThrow()
private val CGDataProviderCreateSequential_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderCreateSequential_ADDR, CGDataProviderCreateSequential_DESC)

fun CGDataProviderCreateSequential(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGDataProviderCreateSequential_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderCreateDirect typedef CGDataProviderRef = (Declared(CGDataProvider))*((Void)*,typedef off_t = LongLong,(typedef CGDataProviderDirectCallbacks = Declared(CGDataProviderDirectCallbacks))*)
 */
private val CGDataProviderCreateDirect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGDataProviderCreateDirect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderCreateDirect").orElseThrow()
private val CGDataProviderCreateDirect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderCreateDirect_ADDR, CGDataProviderCreateDirect_DESC)

fun CGDataProviderCreateDirect(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CGDataProviderCreateDirect_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderCreateWithData typedef CGDataProviderRef = (Declared(CGDataProvider))*((Void)*,(Void)*,typedef size_t = UNSIGNED = Long,typedef CGDataProviderReleaseDataCallback = (Void((Void)*,(Void)*,UNSIGNED = Long))*)
 */
private val CGDataProviderCreateWithData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGDataProviderCreateWithData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderCreateWithData").orElseThrow()
private val CGDataProviderCreateWithData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderCreateWithData_ADDR, CGDataProviderCreateWithData_DESC)

fun CGDataProviderCreateWithData(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CGDataProviderCreateWithData_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderCreateWithCFData typedef CGDataProviderRef = (Declared(CGDataProvider))*(typedef CFDataRef = (Declared(__CFData))*)
 */
private val CGDataProviderCreateWithCFData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataProviderCreateWithCFData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderCreateWithCFData").orElseThrow()
private val CGDataProviderCreateWithCFData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderCreateWithCFData_ADDR, CGDataProviderCreateWithCFData_DESC)

fun CGDataProviderCreateWithCFData(arg0: MemorySegment): MemorySegment {
    try {
        return CGDataProviderCreateWithCFData_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderCreateWithURL typedef CGDataProviderRef = (Declared(CGDataProvider))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CGDataProviderCreateWithURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataProviderCreateWithURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderCreateWithURL").orElseThrow()
private val CGDataProviderCreateWithURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderCreateWithURL_ADDR, CGDataProviderCreateWithURL_DESC)

fun CGDataProviderCreateWithURL(arg0: MemorySegment): MemorySegment {
    try {
        return CGDataProviderCreateWithURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderCreateWithFilename typedef CGDataProviderRef = (Declared(CGDataProvider))*((Char)*)
 */
private val CGDataProviderCreateWithFilename_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataProviderCreateWithFilename_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderCreateWithFilename").orElseThrow()
private val CGDataProviderCreateWithFilename_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderCreateWithFilename_ADDR, CGDataProviderCreateWithFilename_DESC)

fun CGDataProviderCreateWithFilename(arg0: MemorySegment): MemorySegment {
    try {
        return CGDataProviderCreateWithFilename_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderRetain typedef CGDataProviderRef = (Declared(CGDataProvider))*(typedef CGDataProviderRef = (Declared(CGDataProvider))*)
 */
private val CGDataProviderRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataProviderRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderRetain").orElseThrow()
private val CGDataProviderRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderRetain_ADDR, CGDataProviderRetain_DESC)

fun CGDataProviderRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGDataProviderRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderRelease Void(typedef CGDataProviderRef = (Declared(CGDataProvider))*)
 */
private val CGDataProviderRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGDataProviderRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderRelease").orElseThrow()
private val CGDataProviderRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderRelease_ADDR, CGDataProviderRelease_DESC)

fun CGDataProviderRelease(arg0: MemorySegment): Unit {
    try {
        CGDataProviderRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderCopyData typedef CFDataRef = (Declared(__CFData))*(typedef CGDataProviderRef = (Declared(CGDataProvider))*)
 */
private val CGDataProviderCopyData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataProviderCopyData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderCopyData").orElseThrow()
private val CGDataProviderCopyData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderCopyData_ADDR, CGDataProviderCopyData_DESC)

fun CGDataProviderCopyData(arg0: MemorySegment): MemorySegment {
    try {
        return CGDataProviderCopyData_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataProviderGetInfo (Void)*(typedef CGDataProviderRef = (Declared(CGDataProvider))*)
 */
private val CGDataProviderGetInfo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataProviderGetInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataProviderGetInfo").orElseThrow()
private val CGDataProviderGetInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataProviderGetInfo_ADDR, CGDataProviderGetInfo_DESC)

fun CGDataProviderGetInfo(arg0: MemorySegment): MemorySegment {
    try {
        return CGDataProviderGetInfo_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGColorSpaceGenericGray typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceGenericGray_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceGenericGray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceGenericGray").orElseThrow() }
private val kCGColorSpaceGenericGray_VH: VarHandle by lazy { kCGColorSpaceGenericGray_LAYOUT.varHandle() }

var kCGColorSpaceGenericGray: MemorySegment
    get() = kCGColorSpaceGenericGray_VH.get(kCGColorSpaceGenericGray_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceGenericGray_VH.set(kCGColorSpaceGenericGray_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceGenericRGB typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceGenericRGB_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceGenericRGB_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceGenericRGB").orElseThrow() }
private val kCGColorSpaceGenericRGB_VH: VarHandle by lazy { kCGColorSpaceGenericRGB_LAYOUT.varHandle() }

var kCGColorSpaceGenericRGB: MemorySegment
    get() = kCGColorSpaceGenericRGB_VH.get(kCGColorSpaceGenericRGB_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceGenericRGB_VH.set(kCGColorSpaceGenericRGB_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceGenericCMYK typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceGenericCMYK_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceGenericCMYK_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceGenericCMYK").orElseThrow() }
private val kCGColorSpaceGenericCMYK_VH: VarHandle by lazy { kCGColorSpaceGenericCMYK_LAYOUT.varHandle() }

var kCGColorSpaceGenericCMYK: MemorySegment
    get() = kCGColorSpaceGenericCMYK_VH.get(kCGColorSpaceGenericCMYK_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceGenericCMYK_VH.set(kCGColorSpaceGenericCMYK_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceDisplayP3 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceDisplayP3_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceDisplayP3_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceDisplayP3").orElseThrow() }
private val kCGColorSpaceDisplayP3_VH: VarHandle by lazy { kCGColorSpaceDisplayP3_LAYOUT.varHandle() }

var kCGColorSpaceDisplayP3: MemorySegment
    get() = kCGColorSpaceDisplayP3_VH.get(kCGColorSpaceDisplayP3_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceDisplayP3_VH.set(kCGColorSpaceDisplayP3_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceGenericRGBLinear typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceGenericRGBLinear_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceGenericRGBLinear_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceGenericRGBLinear").orElseThrow() }
private val kCGColorSpaceGenericRGBLinear_VH: VarHandle by lazy { kCGColorSpaceGenericRGBLinear_LAYOUT.varHandle() }

var kCGColorSpaceGenericRGBLinear: MemorySegment
    get() = kCGColorSpaceGenericRGBLinear_VH.get(kCGColorSpaceGenericRGBLinear_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceGenericRGBLinear_VH.set(kCGColorSpaceGenericRGBLinear_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceAdobeRGB1998 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceAdobeRGB1998_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceAdobeRGB1998_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceAdobeRGB1998").orElseThrow() }
private val kCGColorSpaceAdobeRGB1998_VH: VarHandle by lazy { kCGColorSpaceAdobeRGB1998_LAYOUT.varHandle() }

var kCGColorSpaceAdobeRGB1998: MemorySegment
    get() = kCGColorSpaceAdobeRGB1998_VH.get(kCGColorSpaceAdobeRGB1998_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceAdobeRGB1998_VH.set(kCGColorSpaceAdobeRGB1998_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceSRGB typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceSRGB_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceSRGB_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceSRGB").orElseThrow() }
private val kCGColorSpaceSRGB_VH: VarHandle by lazy { kCGColorSpaceSRGB_LAYOUT.varHandle() }

var kCGColorSpaceSRGB: MemorySegment
    get() = kCGColorSpaceSRGB_VH.get(kCGColorSpaceSRGB_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceSRGB_VH.set(kCGColorSpaceSRGB_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceGenericGrayGamma2_2 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceGenericGrayGamma2_2_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceGenericGrayGamma2_2_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceGenericGrayGamma2_2").orElseThrow() }
private val kCGColorSpaceGenericGrayGamma2_2_VH: VarHandle by lazy { kCGColorSpaceGenericGrayGamma2_2_LAYOUT.varHandle() }

var kCGColorSpaceGenericGrayGamma2_2: MemorySegment
    get() = kCGColorSpaceGenericGrayGamma2_2_VH.get(kCGColorSpaceGenericGrayGamma2_2_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceGenericGrayGamma2_2_VH.set(kCGColorSpaceGenericGrayGamma2_2_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceGenericXYZ typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceGenericXYZ_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceGenericXYZ_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceGenericXYZ").orElseThrow() }
private val kCGColorSpaceGenericXYZ_VH: VarHandle by lazy { kCGColorSpaceGenericXYZ_LAYOUT.varHandle() }

var kCGColorSpaceGenericXYZ: MemorySegment
    get() = kCGColorSpaceGenericXYZ_VH.get(kCGColorSpaceGenericXYZ_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceGenericXYZ_VH.set(kCGColorSpaceGenericXYZ_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceGenericLab typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceGenericLab_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceGenericLab_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceGenericLab").orElseThrow() }
private val kCGColorSpaceGenericLab_VH: VarHandle by lazy { kCGColorSpaceGenericLab_LAYOUT.varHandle() }

var kCGColorSpaceGenericLab: MemorySegment
    get() = kCGColorSpaceGenericLab_VH.get(kCGColorSpaceGenericLab_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceGenericLab_VH.set(kCGColorSpaceGenericLab_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceACESCGLinear typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceACESCGLinear_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceACESCGLinear_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceACESCGLinear").orElseThrow() }
private val kCGColorSpaceACESCGLinear_VH: VarHandle by lazy { kCGColorSpaceACESCGLinear_LAYOUT.varHandle() }

var kCGColorSpaceACESCGLinear: MemorySegment
    get() = kCGColorSpaceACESCGLinear_VH.get(kCGColorSpaceACESCGLinear_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceACESCGLinear_VH.set(kCGColorSpaceACESCGLinear_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_709 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_709_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_709_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_709").orElseThrow() }
private val kCGColorSpaceITUR_709_VH: VarHandle by lazy { kCGColorSpaceITUR_709_LAYOUT.varHandle() }

var kCGColorSpaceITUR_709: MemorySegment
    get() = kCGColorSpaceITUR_709_VH.get(kCGColorSpaceITUR_709_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_709_VH.set(kCGColorSpaceITUR_709_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_709_PQ typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_709_PQ_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_709_PQ_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_709_PQ").orElseThrow() }
private val kCGColorSpaceITUR_709_PQ_VH: VarHandle by lazy { kCGColorSpaceITUR_709_PQ_LAYOUT.varHandle() }

var kCGColorSpaceITUR_709_PQ: MemorySegment
    get() = kCGColorSpaceITUR_709_PQ_VH.get(kCGColorSpaceITUR_709_PQ_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_709_PQ_VH.set(kCGColorSpaceITUR_709_PQ_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_709_HLG typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_709_HLG_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_709_HLG_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_709_HLG").orElseThrow() }
private val kCGColorSpaceITUR_709_HLG_VH: VarHandle by lazy { kCGColorSpaceITUR_709_HLG_LAYOUT.varHandle() }

var kCGColorSpaceITUR_709_HLG: MemorySegment
    get() = kCGColorSpaceITUR_709_HLG_VH.get(kCGColorSpaceITUR_709_HLG_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_709_HLG_VH.set(kCGColorSpaceITUR_709_HLG_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_2020 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_2020_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_2020_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_2020").orElseThrow() }
private val kCGColorSpaceITUR_2020_VH: VarHandle by lazy { kCGColorSpaceITUR_2020_LAYOUT.varHandle() }

var kCGColorSpaceITUR_2020: MemorySegment
    get() = kCGColorSpaceITUR_2020_VH.get(kCGColorSpaceITUR_2020_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_2020_VH.set(kCGColorSpaceITUR_2020_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_2020_sRGBGamma typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_2020_sRGBGamma_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_2020_sRGBGamma_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_2020_sRGBGamma").orElseThrow() }
private val kCGColorSpaceITUR_2020_sRGBGamma_VH: VarHandle by lazy { kCGColorSpaceITUR_2020_sRGBGamma_LAYOUT.varHandle() }

var kCGColorSpaceITUR_2020_sRGBGamma: MemorySegment
    get() = kCGColorSpaceITUR_2020_sRGBGamma_VH.get(kCGColorSpaceITUR_2020_sRGBGamma_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_2020_sRGBGamma_VH.set(kCGColorSpaceITUR_2020_sRGBGamma_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceROMMRGB typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceROMMRGB_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceROMMRGB_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceROMMRGB").orElseThrow() }
private val kCGColorSpaceROMMRGB_VH: VarHandle by lazy { kCGColorSpaceROMMRGB_LAYOUT.varHandle() }

var kCGColorSpaceROMMRGB: MemorySegment
    get() = kCGColorSpaceROMMRGB_VH.get(kCGColorSpaceROMMRGB_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceROMMRGB_VH.set(kCGColorSpaceROMMRGB_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceDCIP3 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceDCIP3_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceDCIP3_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceDCIP3").orElseThrow() }
private val kCGColorSpaceDCIP3_VH: VarHandle by lazy { kCGColorSpaceDCIP3_LAYOUT.varHandle() }

var kCGColorSpaceDCIP3: MemorySegment
    get() = kCGColorSpaceDCIP3_VH.get(kCGColorSpaceDCIP3_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceDCIP3_VH.set(kCGColorSpaceDCIP3_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceLinearITUR_2020 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceLinearITUR_2020_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceLinearITUR_2020_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceLinearITUR_2020").orElseThrow() }
private val kCGColorSpaceLinearITUR_2020_VH: VarHandle by lazy { kCGColorSpaceLinearITUR_2020_LAYOUT.varHandle() }

var kCGColorSpaceLinearITUR_2020: MemorySegment
    get() = kCGColorSpaceLinearITUR_2020_VH.get(kCGColorSpaceLinearITUR_2020_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceLinearITUR_2020_VH.set(kCGColorSpaceLinearITUR_2020_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceExtendedITUR_2020 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceExtendedITUR_2020_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceExtendedITUR_2020_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceExtendedITUR_2020").orElseThrow() }
private val kCGColorSpaceExtendedITUR_2020_VH: VarHandle by lazy { kCGColorSpaceExtendedITUR_2020_LAYOUT.varHandle() }

var kCGColorSpaceExtendedITUR_2020: MemorySegment
    get() = kCGColorSpaceExtendedITUR_2020_VH.get(kCGColorSpaceExtendedITUR_2020_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceExtendedITUR_2020_VH.set(kCGColorSpaceExtendedITUR_2020_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceExtendedLinearITUR_2020 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceExtendedLinearITUR_2020_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceExtendedLinearITUR_2020_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceExtendedLinearITUR_2020").orElseThrow() }
private val kCGColorSpaceExtendedLinearITUR_2020_VH: VarHandle by lazy { kCGColorSpaceExtendedLinearITUR_2020_LAYOUT.varHandle() }

var kCGColorSpaceExtendedLinearITUR_2020: MemorySegment
    get() = kCGColorSpaceExtendedLinearITUR_2020_VH.get(kCGColorSpaceExtendedLinearITUR_2020_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceExtendedLinearITUR_2020_VH.set(kCGColorSpaceExtendedLinearITUR_2020_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceLinearDisplayP3 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceLinearDisplayP3_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceLinearDisplayP3_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceLinearDisplayP3").orElseThrow() }
private val kCGColorSpaceLinearDisplayP3_VH: VarHandle by lazy { kCGColorSpaceLinearDisplayP3_LAYOUT.varHandle() }

var kCGColorSpaceLinearDisplayP3: MemorySegment
    get() = kCGColorSpaceLinearDisplayP3_VH.get(kCGColorSpaceLinearDisplayP3_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceLinearDisplayP3_VH.set(kCGColorSpaceLinearDisplayP3_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceExtendedDisplayP3 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceExtendedDisplayP3_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceExtendedDisplayP3_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceExtendedDisplayP3").orElseThrow() }
private val kCGColorSpaceExtendedDisplayP3_VH: VarHandle by lazy { kCGColorSpaceExtendedDisplayP3_LAYOUT.varHandle() }

var kCGColorSpaceExtendedDisplayP3: MemorySegment
    get() = kCGColorSpaceExtendedDisplayP3_VH.get(kCGColorSpaceExtendedDisplayP3_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceExtendedDisplayP3_VH.set(kCGColorSpaceExtendedDisplayP3_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceExtendedLinearDisplayP3 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceExtendedLinearDisplayP3_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceExtendedLinearDisplayP3_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceExtendedLinearDisplayP3").orElseThrow() }
private val kCGColorSpaceExtendedLinearDisplayP3_VH: VarHandle by lazy { kCGColorSpaceExtendedLinearDisplayP3_LAYOUT.varHandle() }

var kCGColorSpaceExtendedLinearDisplayP3: MemorySegment
    get() = kCGColorSpaceExtendedLinearDisplayP3_VH.get(kCGColorSpaceExtendedLinearDisplayP3_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceExtendedLinearDisplayP3_VH.set(kCGColorSpaceExtendedLinearDisplayP3_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_2100_PQ typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_2100_PQ_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_2100_PQ_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_2100_PQ").orElseThrow() }
private val kCGColorSpaceITUR_2100_PQ_VH: VarHandle by lazy { kCGColorSpaceITUR_2100_PQ_LAYOUT.varHandle() }

var kCGColorSpaceITUR_2100_PQ: MemorySegment
    get() = kCGColorSpaceITUR_2100_PQ_VH.get(kCGColorSpaceITUR_2100_PQ_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_2100_PQ_VH.set(kCGColorSpaceITUR_2100_PQ_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_2100_HLG typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_2100_HLG_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_2100_HLG_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_2100_HLG").orElseThrow() }
private val kCGColorSpaceITUR_2100_HLG_VH: VarHandle by lazy { kCGColorSpaceITUR_2100_HLG_LAYOUT.varHandle() }

var kCGColorSpaceITUR_2100_HLG: MemorySegment
    get() = kCGColorSpaceITUR_2100_HLG_VH.get(kCGColorSpaceITUR_2100_HLG_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_2100_HLG_VH.set(kCGColorSpaceITUR_2100_HLG_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceDisplayP3_PQ typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceDisplayP3_PQ_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceDisplayP3_PQ_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceDisplayP3_PQ").orElseThrow() }
private val kCGColorSpaceDisplayP3_PQ_VH: VarHandle by lazy { kCGColorSpaceDisplayP3_PQ_LAYOUT.varHandle() }

var kCGColorSpaceDisplayP3_PQ: MemorySegment
    get() = kCGColorSpaceDisplayP3_PQ_VH.get(kCGColorSpaceDisplayP3_PQ_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceDisplayP3_PQ_VH.set(kCGColorSpaceDisplayP3_PQ_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceDisplayP3_HLG typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceDisplayP3_HLG_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceDisplayP3_HLG_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceDisplayP3_HLG").orElseThrow() }
private val kCGColorSpaceDisplayP3_HLG_VH: VarHandle by lazy { kCGColorSpaceDisplayP3_HLG_LAYOUT.varHandle() }

var kCGColorSpaceDisplayP3_HLG: MemorySegment
    get() = kCGColorSpaceDisplayP3_HLG_VH.get(kCGColorSpaceDisplayP3_HLG_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceDisplayP3_HLG_VH.set(kCGColorSpaceDisplayP3_HLG_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_2020_PQ typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_2020_PQ_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_2020_PQ_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_2020_PQ").orElseThrow() }
private val kCGColorSpaceITUR_2020_PQ_VH: VarHandle by lazy { kCGColorSpaceITUR_2020_PQ_LAYOUT.varHandle() }

var kCGColorSpaceITUR_2020_PQ: MemorySegment
    get() = kCGColorSpaceITUR_2020_PQ_VH.get(kCGColorSpaceITUR_2020_PQ_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_2020_PQ_VH.set(kCGColorSpaceITUR_2020_PQ_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_2020_HLG typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_2020_HLG_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_2020_HLG_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_2020_HLG").orElseThrow() }
private val kCGColorSpaceITUR_2020_HLG_VH: VarHandle by lazy { kCGColorSpaceITUR_2020_HLG_LAYOUT.varHandle() }

var kCGColorSpaceITUR_2020_HLG: MemorySegment
    get() = kCGColorSpaceITUR_2020_HLG_VH.get(kCGColorSpaceITUR_2020_HLG_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_2020_HLG_VH.set(kCGColorSpaceITUR_2020_HLG_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceDisplayP3_PQ_EOTF typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceDisplayP3_PQ_EOTF_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceDisplayP3_PQ_EOTF_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceDisplayP3_PQ_EOTF").orElseThrow() }
private val kCGColorSpaceDisplayP3_PQ_EOTF_VH: VarHandle by lazy { kCGColorSpaceDisplayP3_PQ_EOTF_LAYOUT.varHandle() }

var kCGColorSpaceDisplayP3_PQ_EOTF: MemorySegment
    get() = kCGColorSpaceDisplayP3_PQ_EOTF_VH.get(kCGColorSpaceDisplayP3_PQ_EOTF_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceDisplayP3_PQ_EOTF_VH.set(kCGColorSpaceDisplayP3_PQ_EOTF_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceITUR_2020_PQ_EOTF typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceITUR_2020_PQ_EOTF_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceITUR_2020_PQ_EOTF_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceITUR_2020_PQ_EOTF").orElseThrow() }
private val kCGColorSpaceITUR_2020_PQ_EOTF_VH: VarHandle by lazy { kCGColorSpaceITUR_2020_PQ_EOTF_LAYOUT.varHandle() }

var kCGColorSpaceITUR_2020_PQ_EOTF: MemorySegment
    get() = kCGColorSpaceITUR_2020_PQ_EOTF_VH.get(kCGColorSpaceITUR_2020_PQ_EOTF_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceITUR_2020_PQ_EOTF_VH.set(kCGColorSpaceITUR_2020_PQ_EOTF_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceExtendedSRGB typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceExtendedSRGB_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceExtendedSRGB_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceExtendedSRGB").orElseThrow() }
private val kCGColorSpaceExtendedSRGB_VH: VarHandle by lazy { kCGColorSpaceExtendedSRGB_LAYOUT.varHandle() }

var kCGColorSpaceExtendedSRGB: MemorySegment
    get() = kCGColorSpaceExtendedSRGB_VH.get(kCGColorSpaceExtendedSRGB_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceExtendedSRGB_VH.set(kCGColorSpaceExtendedSRGB_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceLinearSRGB typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceLinearSRGB_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceLinearSRGB_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceLinearSRGB").orElseThrow() }
private val kCGColorSpaceLinearSRGB_VH: VarHandle by lazy { kCGColorSpaceLinearSRGB_LAYOUT.varHandle() }

var kCGColorSpaceLinearSRGB: MemorySegment
    get() = kCGColorSpaceLinearSRGB_VH.get(kCGColorSpaceLinearSRGB_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceLinearSRGB_VH.set(kCGColorSpaceLinearSRGB_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceExtendedLinearSRGB typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceExtendedLinearSRGB_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceExtendedLinearSRGB_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceExtendedLinearSRGB").orElseThrow() }
private val kCGColorSpaceExtendedLinearSRGB_VH: VarHandle by lazy { kCGColorSpaceExtendedLinearSRGB_LAYOUT.varHandle() }

var kCGColorSpaceExtendedLinearSRGB: MemorySegment
    get() = kCGColorSpaceExtendedLinearSRGB_VH.get(kCGColorSpaceExtendedLinearSRGB_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceExtendedLinearSRGB_VH.set(kCGColorSpaceExtendedLinearSRGB_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceExtendedGray typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceExtendedGray_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceExtendedGray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceExtendedGray").orElseThrow() }
private val kCGColorSpaceExtendedGray_VH: VarHandle by lazy { kCGColorSpaceExtendedGray_LAYOUT.varHandle() }

var kCGColorSpaceExtendedGray: MemorySegment
    get() = kCGColorSpaceExtendedGray_VH.get(kCGColorSpaceExtendedGray_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceExtendedGray_VH.set(kCGColorSpaceExtendedGray_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceLinearGray typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceLinearGray_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceLinearGray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceLinearGray").orElseThrow() }
private val kCGColorSpaceLinearGray_VH: VarHandle by lazy { kCGColorSpaceLinearGray_LAYOUT.varHandle() }

var kCGColorSpaceLinearGray: MemorySegment
    get() = kCGColorSpaceLinearGray_VH.get(kCGColorSpaceLinearGray_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceLinearGray_VH.set(kCGColorSpaceLinearGray_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceExtendedLinearGray typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceExtendedLinearGray_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceExtendedLinearGray_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceExtendedLinearGray").orElseThrow() }
private val kCGColorSpaceExtendedLinearGray_VH: VarHandle by lazy { kCGColorSpaceExtendedLinearGray_LAYOUT.varHandle() }

var kCGColorSpaceExtendedLinearGray: MemorySegment
    get() = kCGColorSpaceExtendedLinearGray_VH.get(kCGColorSpaceExtendedLinearGray_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceExtendedLinearGray_VH.set(kCGColorSpaceExtendedLinearGray_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorSpaceCoreMedia709 typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceCoreMedia709_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceCoreMedia709_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceCoreMedia709").orElseThrow() }
private val kCGColorSpaceCoreMedia709_VH: VarHandle by lazy { kCGColorSpaceCoreMedia709_LAYOUT.varHandle() }

var kCGColorSpaceCoreMedia709: MemorySegment
    get() = kCGColorSpaceCoreMedia709_VH.get(kCGColorSpaceCoreMedia709_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceCoreMedia709_VH.set(kCGColorSpaceCoreMedia709_SEGMENT, value)

/**
 * {@snippet lang=c : CGColorSpaceCreateDeviceGray typedef CGColorSpaceRef = (Declared(CGColorSpace))*()
 */
private val CGColorSpaceCreateDeviceGray_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CGColorSpaceCreateDeviceGray_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateDeviceGray").orElseThrow()
private val CGColorSpaceCreateDeviceGray_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateDeviceGray_ADDR, CGColorSpaceCreateDeviceGray_DESC)

fun CGColorSpaceCreateDeviceGray(): MemorySegment {
    try {
        return CGColorSpaceCreateDeviceGray_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateDeviceRGB typedef CGColorSpaceRef = (Declared(CGColorSpace))*()
 */
private val CGColorSpaceCreateDeviceRGB_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CGColorSpaceCreateDeviceRGB_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateDeviceRGB").orElseThrow()
private val CGColorSpaceCreateDeviceRGB_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateDeviceRGB_ADDR, CGColorSpaceCreateDeviceRGB_DESC)

fun CGColorSpaceCreateDeviceRGB(): MemorySegment {
    try {
        return CGColorSpaceCreateDeviceRGB_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateDeviceCMYK typedef CGColorSpaceRef = (Declared(CGColorSpace))*()
 */
private val CGColorSpaceCreateDeviceCMYK_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CGColorSpaceCreateDeviceCMYK_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateDeviceCMYK").orElseThrow()
private val CGColorSpaceCreateDeviceCMYK_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateDeviceCMYK_ADDR, CGColorSpaceCreateDeviceCMYK_DESC)

fun CGColorSpaceCreateDeviceCMYK(): MemorySegment {
    try {
        return CGColorSpaceCreateDeviceCMYK_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateCalibratedGray typedef CGColorSpaceRef = (Declared(CGColorSpace))*((typedef CGFloat = Double)*,(typedef CGFloat = Double)*,typedef CGFloat = Double)
 */
private val CGColorSpaceCreateCalibratedGray_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGColorSpaceCreateCalibratedGray_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateCalibratedGray").orElseThrow()
private val CGColorSpaceCreateCalibratedGray_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateCalibratedGray_ADDR, CGColorSpaceCreateCalibratedGray_DESC)

fun CGColorSpaceCreateCalibratedGray(arg0: MemorySegment, arg1: MemorySegment, arg2: Double): MemorySegment {
    try {
        return CGColorSpaceCreateCalibratedGray_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateCalibratedRGB typedef CGColorSpaceRef = (Declared(CGColorSpace))*((typedef CGFloat = Double)*,(typedef CGFloat = Double)*,(typedef CGFloat = Double)*,(typedef CGFloat = Double)*)
 */
private val CGColorSpaceCreateCalibratedRGB_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateCalibratedRGB_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateCalibratedRGB").orElseThrow()
private val CGColorSpaceCreateCalibratedRGB_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateCalibratedRGB_ADDR, CGColorSpaceCreateCalibratedRGB_DESC)

fun CGColorSpaceCreateCalibratedRGB(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateCalibratedRGB_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateLab typedef CGColorSpaceRef = (Declared(CGColorSpace))*((typedef CGFloat = Double)*,(typedef CGFloat = Double)*,(typedef CGFloat = Double)*)
 */
private val CGColorSpaceCreateLab_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateLab_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateLab").orElseThrow()
private val CGColorSpaceCreateLab_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateLab_ADDR, CGColorSpaceCreateLab_DESC)

fun CGColorSpaceCreateLab(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateLab_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateWithICCData typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CFTypeRef = (Void)*)
 */
private val CGColorSpaceCreateWithICCData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateWithICCData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateWithICCData").orElseThrow()
private val CGColorSpaceCreateWithICCData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateWithICCData_ADDR, CGColorSpaceCreateWithICCData_DESC)

fun CGColorSpaceCreateWithICCData(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateWithICCData_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateICCBased typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef size_t = UNSIGNED = Long,(typedef CGFloat = Double)*,typedef CGDataProviderRef = (Declared(CGDataProvider))*,typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCreateICCBased_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateICCBased_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateICCBased").orElseThrow()
private val CGColorSpaceCreateICCBased_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateICCBased_ADDR, CGColorSpaceCreateICCBased_DESC)

fun CGColorSpaceCreateICCBased(arg0: Long, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateICCBased_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateIndexed typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef size_t = UNSIGNED = Long,(UNSIGNED = Char)*)
 */
private val CGColorSpaceCreateIndexed_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGColorSpaceCreateIndexed_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateIndexed").orElseThrow()
private val CGColorSpaceCreateIndexed_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateIndexed_ADDR, CGColorSpaceCreateIndexed_DESC)

fun CGColorSpaceCreateIndexed(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateIndexed_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreatePattern typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCreatePattern_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreatePattern_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreatePattern").orElseThrow()
private val CGColorSpaceCreatePattern_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreatePattern_ADDR, CGColorSpaceCreatePattern_DESC)

fun CGColorSpaceCreatePattern(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreatePattern_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGColorSpaceExtendedRange typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorSpaceExtendedRange_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorSpaceExtendedRange_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorSpaceExtendedRange").orElseThrow() }
private val kCGColorSpaceExtendedRange_VH: VarHandle by lazy { kCGColorSpaceExtendedRange_LAYOUT.varHandle() }

var kCGColorSpaceExtendedRange: MemorySegment
    get() = kCGColorSpaceExtendedRange_VH.get(kCGColorSpaceExtendedRange_SEGMENT) as MemorySegment
    set(value) = kCGColorSpaceExtendedRange_VH.set(kCGColorSpaceExtendedRange_SEGMENT, value)

/**
 * {@snippet lang=c : CGColorSpaceCreateWithColorSyncProfile typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef ColorSyncProfileRef = (Declared(ColorSyncProfile))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGColorSpaceCreateWithColorSyncProfile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateWithColorSyncProfile_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateWithColorSyncProfile").orElseThrow()
private val CGColorSpaceCreateWithColorSyncProfile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateWithColorSyncProfile_ADDR, CGColorSpaceCreateWithColorSyncProfile_DESC)

fun CGColorSpaceCreateWithColorSyncProfile(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateWithColorSyncProfile_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateWithName typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CGColorSpaceCreateWithName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateWithName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateWithName").orElseThrow()
private val CGColorSpaceCreateWithName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateWithName_ADDR, CGColorSpaceCreateWithName_DESC)

fun CGColorSpaceCreateWithName(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateWithName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceRetain typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceRetain").orElseThrow()
private val CGColorSpaceRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceRetain_ADDR, CGColorSpaceRetain_DESC)

fun CGColorSpaceRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceRelease Void(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGColorSpaceRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceRelease").orElseThrow()
private val CGColorSpaceRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceRelease_ADDR, CGColorSpaceRelease_DESC)

fun CGColorSpaceRelease(arg0: MemorySegment): Unit {
    try {
        CGColorSpaceRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceGetName typedef CFStringRef = (Declared(__CFString))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceGetName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceGetName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceGetName").orElseThrow()
private val CGColorSpaceGetName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceGetName_ADDR, CGColorSpaceGetName_DESC)

fun CGColorSpaceGetName(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceGetName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCopyName typedef CFStringRef = (Declared(__CFString))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCopyName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCopyName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCopyName").orElseThrow()
private val CGColorSpaceCopyName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCopyName_ADDR, CGColorSpaceCopyName_DESC)

fun CGColorSpaceCopyName(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCopyName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGColorSpaceGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGColorSpaceGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceGetTypeID").orElseThrow()
private val CGColorSpaceGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceGetTypeID_ADDR, CGColorSpaceGetTypeID_DESC)

fun CGColorSpaceGetTypeID(): Long {
    try {
        return CGColorSpaceGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceGetNumberOfComponents typedef size_t = UNSIGNED = Long(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceGetNumberOfComponents_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGColorSpaceGetNumberOfComponents_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceGetNumberOfComponents").orElseThrow()
private val CGColorSpaceGetNumberOfComponents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceGetNumberOfComponents_ADDR, CGColorSpaceGetNumberOfComponents_DESC)

fun CGColorSpaceGetNumberOfComponents(arg0: MemorySegment): Long {
    try {
        return CGColorSpaceGetNumberOfComponents_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceGetBaseColorSpace typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceGetBaseColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceGetBaseColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceGetBaseColorSpace").orElseThrow()
private val CGColorSpaceGetBaseColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceGetBaseColorSpace_ADDR, CGColorSpaceGetBaseColorSpace_DESC)

fun CGColorSpaceGetBaseColorSpace(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceGetBaseColorSpace_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCopyBaseColorSpace typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCopyBaseColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCopyBaseColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCopyBaseColorSpace").orElseThrow()
private val CGColorSpaceCopyBaseColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCopyBaseColorSpace_ADDR, CGColorSpaceCopyBaseColorSpace_DESC)

fun CGColorSpaceCopyBaseColorSpace(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCopyBaseColorSpace_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceGetColorTableCount typedef size_t = UNSIGNED = Long(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceGetColorTableCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGColorSpaceGetColorTableCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceGetColorTableCount").orElseThrow()
private val CGColorSpaceGetColorTableCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceGetColorTableCount_ADDR, CGColorSpaceGetColorTableCount_DESC)

fun CGColorSpaceGetColorTableCount(arg0: MemorySegment): Long {
    try {
        return CGColorSpaceGetColorTableCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceGetColorTable Void(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,(typedef uint8_t = UNSIGNED = Char)*)
 */
private val CGColorSpaceGetColorTable_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceGetColorTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceGetColorTable").orElseThrow()
private val CGColorSpaceGetColorTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceGetColorTable_ADDR, CGColorSpaceGetColorTable_DESC)

fun CGColorSpaceGetColorTable(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGColorSpaceGetColorTable_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCopyICCData typedef CFDataRef = (Declared(__CFData))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCopyICCData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCopyICCData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCopyICCData").orElseThrow()
private val CGColorSpaceCopyICCData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCopyICCData_ADDR, CGColorSpaceCopyICCData_DESC)

fun CGColorSpaceCopyICCData(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCopyICCData_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceIsWideGamutRGB Bool(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceIsWideGamutRGB_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGColorSpaceIsWideGamutRGB_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceIsWideGamutRGB").orElseThrow()
private val CGColorSpaceIsWideGamutRGB_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceIsWideGamutRGB_ADDR, CGColorSpaceIsWideGamutRGB_DESC)

fun CGColorSpaceIsWideGamutRGB(arg0: MemorySegment): Boolean {
    try {
        return CGColorSpaceIsWideGamutRGB_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceIsHDR Bool(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceIsHDR_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGColorSpaceIsHDR_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceIsHDR").orElseThrow()
private val CGColorSpaceIsHDR_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceIsHDR_ADDR, CGColorSpaceIsHDR_DESC)

fun CGColorSpaceIsHDR(arg0: MemorySegment): Boolean {
    try {
        return CGColorSpaceIsHDR_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceUsesITUR_2100TF Bool(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceUsesITUR_2100TF_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGColorSpaceUsesITUR_2100TF_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceUsesITUR_2100TF").orElseThrow()
private val CGColorSpaceUsesITUR_2100TF_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceUsesITUR_2100TF_ADDR, CGColorSpaceUsesITUR_2100TF_DESC)

fun CGColorSpaceUsesITUR_2100TF(arg0: MemorySegment): Boolean {
    try {
        return CGColorSpaceUsesITUR_2100TF_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceIsPQBased Bool(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceIsPQBased_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGColorSpaceIsPQBased_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceIsPQBased").orElseThrow()
private val CGColorSpaceIsPQBased_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceIsPQBased_ADDR, CGColorSpaceIsPQBased_DESC)

fun CGColorSpaceIsPQBased(arg0: MemorySegment): Boolean {
    try {
        return CGColorSpaceIsPQBased_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceIsHLGBased Bool(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceIsHLGBased_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGColorSpaceIsHLGBased_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceIsHLGBased").orElseThrow()
private val CGColorSpaceIsHLGBased_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceIsHLGBased_ADDR, CGColorSpaceIsHLGBased_DESC)

fun CGColorSpaceIsHLGBased(arg0: MemorySegment): Boolean {
    try {
        return CGColorSpaceIsHLGBased_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceSupportsOutput Bool(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceSupportsOutput_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGColorSpaceSupportsOutput_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceSupportsOutput").orElseThrow()
private val CGColorSpaceSupportsOutput_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceSupportsOutput_ADDR, CGColorSpaceSupportsOutput_DESC)

fun CGColorSpaceSupportsOutput(arg0: MemorySegment): Boolean {
    try {
        return CGColorSpaceSupportsOutput_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCopyPropertyList typedef CFPropertyListRef = (Void)*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCopyPropertyList_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCopyPropertyList_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCopyPropertyList").orElseThrow()
private val CGColorSpaceCopyPropertyList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCopyPropertyList_ADDR, CGColorSpaceCopyPropertyList_DESC)

fun CGColorSpaceCopyPropertyList(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCopyPropertyList_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateWithPropertyList typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CFPropertyListRef = (Void)*)
 */
private val CGColorSpaceCreateWithPropertyList_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateWithPropertyList_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateWithPropertyList").orElseThrow()
private val CGColorSpaceCreateWithPropertyList_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateWithPropertyList_ADDR, CGColorSpaceCreateWithPropertyList_DESC)

fun CGColorSpaceCreateWithPropertyList(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateWithPropertyList_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceUsesExtendedRange Bool(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceUsesExtendedRange_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGColorSpaceUsesExtendedRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceUsesExtendedRange").orElseThrow()
private val CGColorSpaceUsesExtendedRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceUsesExtendedRange_ADDR, CGColorSpaceUsesExtendedRange_DESC)

fun CGColorSpaceUsesExtendedRange(arg0: MemorySegment): Boolean {
    try {
        return CGColorSpaceUsesExtendedRange_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateLinearized typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCreateLinearized_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateLinearized_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateLinearized").orElseThrow()
private val CGColorSpaceCreateLinearized_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateLinearized_ADDR, CGColorSpaceCreateLinearized_DESC)

fun CGColorSpaceCreateLinearized(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateLinearized_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateExtended typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCreateExtended_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateExtended_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateExtended").orElseThrow()
private val CGColorSpaceCreateExtended_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateExtended_ADDR, CGColorSpaceCreateExtended_DESC)

fun CGColorSpaceCreateExtended(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateExtended_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateExtendedLinearized typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCreateExtendedLinearized_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateExtendedLinearized_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateExtendedLinearized").orElseThrow()
private val CGColorSpaceCreateExtendedLinearized_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateExtendedLinearized_ADDR, CGColorSpaceCreateExtendedLinearized_DESC)

fun CGColorSpaceCreateExtendedLinearized(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateExtendedLinearized_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateCopyWithStandardRange typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCreateCopyWithStandardRange_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateCopyWithStandardRange_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateCopyWithStandardRange").orElseThrow()
private val CGColorSpaceCreateCopyWithStandardRange_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateCopyWithStandardRange_ADDR, CGColorSpaceCreateCopyWithStandardRange_DESC)

fun CGColorSpaceCreateCopyWithStandardRange(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateCopyWithStandardRange_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateWithICCProfile typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CFDataRef = (Declared(__CFData))*)
 */
private val CGColorSpaceCreateWithICCProfile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateWithICCProfile_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateWithICCProfile").orElseThrow()
private val CGColorSpaceCreateWithICCProfile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateWithICCProfile_ADDR, CGColorSpaceCreateWithICCProfile_DESC)

fun CGColorSpaceCreateWithICCProfile(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateWithICCProfile_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCopyICCProfile typedef CFDataRef = (Declared(__CFData))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorSpaceCopyICCProfile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCopyICCProfile_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCopyICCProfile").orElseThrow()
private val CGColorSpaceCopyICCProfile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCopyICCProfile_ADDR, CGColorSpaceCopyICCProfile_DESC)

fun CGColorSpaceCopyICCProfile(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCopyICCProfile_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorSpaceCreateWithPlatformColorSpace typedef CGColorSpaceRef = (Declared(CGColorSpace))*((Void)*)
 */
private val CGColorSpaceCreateWithPlatformColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorSpaceCreateWithPlatformColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorSpaceCreateWithPlatformColorSpace").orElseThrow()
private val CGColorSpaceCreateWithPlatformColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorSpaceCreateWithPlatformColorSpace_ADDR, CGColorSpaceCreateWithPlatformColorSpace_DESC)

fun CGColorSpaceCreateWithPlatformColorSpace(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorSpaceCreateWithPlatformColorSpace_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPatternGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGPatternGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGPatternGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPatternGetTypeID").orElseThrow()
private val CGPatternGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPatternGetTypeID_ADDR, CGPatternGetTypeID_DESC)

fun CGPatternGetTypeID(): Long {
    try {
        return CGPatternGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPatternRetain typedef CGPatternRef = (Declared(CGPattern))*(typedef CGPatternRef = (Declared(CGPattern))*)
 */
private val CGPatternRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPatternRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPatternRetain").orElseThrow()
private val CGPatternRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPatternRetain_ADDR, CGPatternRetain_DESC)

fun CGPatternRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGPatternRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPatternRelease Void(typedef CGPatternRef = (Declared(CGPattern))*)
 */
private val CGPatternRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPatternRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPatternRelease").orElseThrow()
private val CGPatternRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPatternRelease_ADDR, CGPatternRelease_DESC)

fun CGPatternRelease(arg0: MemorySegment): Unit {
    try {
        CGPatternRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreate typedef CGColorRef = (Declared(CGColor))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,(typedef CGFloat = Double)*)
 */
private val CGColorCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreate").orElseThrow()
private val CGColorCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreate_ADDR, CGColorCreate_DESC)

fun CGColorCreate(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGColorCreate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreateGenericGray typedef CGColorRef = (Declared(CGColor))*(typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGColorCreateGenericGray_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGColorCreateGenericGray_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreateGenericGray").orElseThrow()
private val CGColorCreateGenericGray_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreateGenericGray_ADDR, CGColorCreateGenericGray_DESC)

fun CGColorCreateGenericGray(arg0: Double, arg1: Double): MemorySegment {
    try {
        return CGColorCreateGenericGray_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreateGenericRGB typedef CGColorRef = (Declared(CGColor))*(typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGColorCreateGenericRGB_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGColorCreateGenericRGB_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreateGenericRGB").orElseThrow()
private val CGColorCreateGenericRGB_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreateGenericRGB_ADDR, CGColorCreateGenericRGB_DESC)

fun CGColorCreateGenericRGB(arg0: Double, arg1: Double, arg2: Double, arg3: Double): MemorySegment {
    try {
        return CGColorCreateGenericRGB_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreateGenericCMYK typedef CGColorRef = (Declared(CGColor))*(typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGColorCreateGenericCMYK_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGColorCreateGenericCMYK_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreateGenericCMYK").orElseThrow()
private val CGColorCreateGenericCMYK_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreateGenericCMYK_ADDR, CGColorCreateGenericCMYK_DESC)

fun CGColorCreateGenericCMYK(arg0: Double, arg1: Double, arg2: Double, arg3: Double, arg4: Double): MemorySegment {
    try {
        return CGColorCreateGenericCMYK_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreateGenericGrayGamma2_2 typedef CGColorRef = (Declared(CGColor))*(typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGColorCreateGenericGrayGamma2_2_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGColorCreateGenericGrayGamma2_2_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreateGenericGrayGamma2_2").orElseThrow()
private val CGColorCreateGenericGrayGamma2_2_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreateGenericGrayGamma2_2_ADDR, CGColorCreateGenericGrayGamma2_2_DESC)

fun CGColorCreateGenericGrayGamma2_2(arg0: Double, arg1: Double): MemorySegment {
    try {
        return CGColorCreateGenericGrayGamma2_2_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreateSRGB typedef CGColorRef = (Declared(CGColor))*(typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGColorCreateSRGB_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGColorCreateSRGB_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreateSRGB").orElseThrow()
private val CGColorCreateSRGB_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreateSRGB_ADDR, CGColorCreateSRGB_DESC)

fun CGColorCreateSRGB(arg0: Double, arg1: Double, arg2: Double, arg3: Double): MemorySegment {
    try {
        return CGColorCreateSRGB_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreateWithContentHeadroom typedef CGColorRef = (Declared(CGColor))*(Float,typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGColorCreateWithContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGColorCreateWithContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreateWithContentHeadroom").orElseThrow()
private val CGColorCreateWithContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreateWithContentHeadroom_ADDR, CGColorCreateWithContentHeadroom_DESC)

fun CGColorCreateWithContentHeadroom(arg0: Float, arg1: MemorySegment, arg2: Double, arg3: Double, arg4: Double, arg5: Double): MemorySegment {
    try {
        return CGColorCreateWithContentHeadroom_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorGetContentHeadroom Float(typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorGetContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS)
private val CGColorGetContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorGetContentHeadroom").orElseThrow()
private val CGColorGetContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorGetContentHeadroom_ADDR, CGColorGetContentHeadroom_DESC)

fun CGColorGetContentHeadroom(arg0: MemorySegment): Float {
    try {
        return CGColorGetContentHeadroom_HANDLE.invokeExact(arg0) as Float
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorGetConstantColor typedef CGColorRef = (Declared(CGColor))*(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CGColorGetConstantColor_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorGetConstantColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorGetConstantColor").orElseThrow()
private val CGColorGetConstantColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorGetConstantColor_ADDR, CGColorGetConstantColor_DESC)

fun CGColorGetConstantColor(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorGetConstantColor_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreateWithPattern typedef CGColorRef = (Declared(CGColor))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CGPatternRef = (Declared(CGPattern))*,(typedef CGFloat = Double)*)
 */
private val CGColorCreateWithPattern_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorCreateWithPattern_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreateWithPattern").orElseThrow()
private val CGColorCreateWithPattern_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreateWithPattern_ADDR, CGColorCreateWithPattern_DESC)

fun CGColorCreateWithPattern(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGColorCreateWithPattern_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreateCopy typedef CGColorRef = (Declared(CGColor))*(typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreateCopy").orElseThrow()
private val CGColorCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreateCopy_ADDR, CGColorCreateCopy_DESC)

fun CGColorCreateCopy(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorCreateCopy_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorCreateCopyWithAlpha typedef CGColorRef = (Declared(CGColor))*(typedef CGColorRef = (Declared(CGColor))*,typedef CGFloat = Double)
 */
private val CGColorCreateCopyWithAlpha_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CGColorCreateCopyWithAlpha_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorCreateCopyWithAlpha").orElseThrow()
private val CGColorCreateCopyWithAlpha_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorCreateCopyWithAlpha_ADDR, CGColorCreateCopyWithAlpha_DESC)

fun CGColorCreateCopyWithAlpha(arg0: MemorySegment, arg1: Double): MemorySegment {
    try {
        return CGColorCreateCopyWithAlpha_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorRetain typedef CGColorRef = (Declared(CGColor))*(typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorRetain").orElseThrow()
private val CGColorRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorRetain_ADDR, CGColorRetain_DESC)

fun CGColorRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorRelease Void(typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGColorRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorRelease").orElseThrow()
private val CGColorRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorRelease_ADDR, CGColorRelease_DESC)

fun CGColorRelease(arg0: MemorySegment): Unit {
    try {
        CGColorRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorEqualToColor Bool(typedef CGColorRef = (Declared(CGColor))*,typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorEqualToColor_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorEqualToColor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorEqualToColor").orElseThrow()
private val CGColorEqualToColor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorEqualToColor_ADDR, CGColorEqualToColor_DESC)

fun CGColorEqualToColor(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGColorEqualToColor_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorGetNumberOfComponents typedef size_t = UNSIGNED = Long(typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorGetNumberOfComponents_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGColorGetNumberOfComponents_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorGetNumberOfComponents").orElseThrow()
private val CGColorGetNumberOfComponents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorGetNumberOfComponents_ADDR, CGColorGetNumberOfComponents_DESC)

fun CGColorGetNumberOfComponents(arg0: MemorySegment): Long {
    try {
        return CGColorGetNumberOfComponents_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorGetComponents (typedef CGFloat = Double)*(typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorGetComponents_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorGetComponents_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorGetComponents").orElseThrow()
private val CGColorGetComponents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorGetComponents_ADDR, CGColorGetComponents_DESC)

fun CGColorGetComponents(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorGetComponents_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorGetAlpha typedef CGFloat = Double(typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorGetAlpha_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CGColorGetAlpha_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorGetAlpha").orElseThrow()
private val CGColorGetAlpha_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorGetAlpha_ADDR, CGColorGetAlpha_DESC)

fun CGColorGetAlpha(arg0: MemorySegment): Double {
    try {
        return CGColorGetAlpha_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorGetColorSpace typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorGetColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorGetColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorGetColorSpace").orElseThrow()
private val CGColorGetColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorGetColorSpace_ADDR, CGColorGetColorSpace_DESC)

fun CGColorGetColorSpace(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorGetColorSpace_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorGetPattern typedef CGPatternRef = (Declared(CGPattern))*(typedef CGColorRef = (Declared(CGColor))*)
 */
private val CGColorGetPattern_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorGetPattern_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorGetPattern").orElseThrow()
private val CGColorGetPattern_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorGetPattern_ADDR, CGColorGetPattern_DESC)

fun CGColorGetPattern(arg0: MemorySegment): MemorySegment {
    try {
        return CGColorGetPattern_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGColorGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGColorGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorGetTypeID").orElseThrow()
private val CGColorGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorGetTypeID_ADDR, CGColorGetTypeID_DESC)

fun CGColorGetTypeID(): Long {
    try {
        return CGColorGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGColorWhite typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorWhite_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorWhite_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorWhite").orElseThrow() }
private val kCGColorWhite_VH: VarHandle by lazy { kCGColorWhite_LAYOUT.varHandle() }

var kCGColorWhite: MemorySegment
    get() = kCGColorWhite_VH.get(kCGColorWhite_SEGMENT) as MemorySegment
    set(value) = kCGColorWhite_VH.set(kCGColorWhite_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorBlack typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorBlack_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorBlack_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorBlack").orElseThrow() }
private val kCGColorBlack_VH: VarHandle by lazy { kCGColorBlack_LAYOUT.varHandle() }

var kCGColorBlack: MemorySegment
    get() = kCGColorBlack_VH.get(kCGColorBlack_SEGMENT) as MemorySegment
    set(value) = kCGColorBlack_VH.set(kCGColorBlack_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorClear typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorClear_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorClear_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorClear").orElseThrow() }
private val kCGColorClear_VH: VarHandle by lazy { kCGColorClear_LAYOUT.varHandle() }

var kCGColorClear: MemorySegment
    get() = kCGColorClear_VH.get(kCGColorClear_SEGMENT) as MemorySegment
    set(value) = kCGColorClear_VH.set(kCGColorClear_SEGMENT, value)

/**
 * {@snippet lang=c : CGFontGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGFontGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGFontGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetTypeID").orElseThrow()
private val CGFontGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetTypeID_ADDR, CGFontGetTypeID_DESC)

fun CGFontGetTypeID(): Long {
    try {
        return CGFontGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCreateWithPlatformFont typedef CGFontRef = (Declared(CGFont))*((Void)*)
 */
private val CGFontCreateWithPlatformFont_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCreateWithPlatformFont_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCreateWithPlatformFont").orElseThrow()
private val CGFontCreateWithPlatformFont_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCreateWithPlatformFont_ADDR, CGFontCreateWithPlatformFont_DESC)

fun CGFontCreateWithPlatformFont(arg0: MemorySegment): MemorySegment {
    try {
        return CGFontCreateWithPlatformFont_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCreateWithDataProvider typedef CGFontRef = (Declared(CGFont))*(typedef CGDataProviderRef = (Declared(CGDataProvider))*)
 */
private val CGFontCreateWithDataProvider_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCreateWithDataProvider_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCreateWithDataProvider").orElseThrow()
private val CGFontCreateWithDataProvider_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCreateWithDataProvider_ADDR, CGFontCreateWithDataProvider_DESC)

fun CGFontCreateWithDataProvider(arg0: MemorySegment): MemorySegment {
    try {
        return CGFontCreateWithDataProvider_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCreateWithFontName typedef CGFontRef = (Declared(CGFont))*(typedef CFStringRef = (Declared(__CFString))*)
 */
private val CGFontCreateWithFontName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCreateWithFontName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCreateWithFontName").orElseThrow()
private val CGFontCreateWithFontName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCreateWithFontName_ADDR, CGFontCreateWithFontName_DESC)

fun CGFontCreateWithFontName(arg0: MemorySegment): MemorySegment {
    try {
        return CGFontCreateWithFontName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCreateCopyWithVariations typedef CGFontRef = (Declared(CGFont))*(typedef CGFontRef = (Declared(CGFont))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGFontCreateCopyWithVariations_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCreateCopyWithVariations_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCreateCopyWithVariations").orElseThrow()
private val CGFontCreateCopyWithVariations_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCreateCopyWithVariations_ADDR, CGFontCreateCopyWithVariations_DESC)

fun CGFontCreateCopyWithVariations(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGFontCreateCopyWithVariations_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontRetain typedef CGFontRef = (Declared(CGFont))*(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontRetain").orElseThrow()
private val CGFontRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontRetain_ADDR, CGFontRetain_DESC)

fun CGFontRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGFontRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontRelease Void(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGFontRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontRelease").orElseThrow()
private val CGFontRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontRelease_ADDR, CGFontRelease_DESC)

fun CGFontRelease(arg0: MemorySegment): Unit {
    try {
        CGFontRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetNumberOfGlyphs typedef size_t = UNSIGNED = Long(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetNumberOfGlyphs_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGFontGetNumberOfGlyphs_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetNumberOfGlyphs").orElseThrow()
private val CGFontGetNumberOfGlyphs_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetNumberOfGlyphs_ADDR, CGFontGetNumberOfGlyphs_DESC)

fun CGFontGetNumberOfGlyphs(arg0: MemorySegment): Long {
    try {
        return CGFontGetNumberOfGlyphs_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetUnitsPerEm Int(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetUnitsPerEm_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGFontGetUnitsPerEm_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetUnitsPerEm").orElseThrow()
private val CGFontGetUnitsPerEm_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetUnitsPerEm_ADDR, CGFontGetUnitsPerEm_DESC)

fun CGFontGetUnitsPerEm(arg0: MemorySegment): Int {
    try {
        return CGFontGetUnitsPerEm_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCopyPostScriptName typedef CFStringRef = (Declared(__CFString))*(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontCopyPostScriptName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCopyPostScriptName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCopyPostScriptName").orElseThrow()
private val CGFontCopyPostScriptName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCopyPostScriptName_ADDR, CGFontCopyPostScriptName_DESC)

fun CGFontCopyPostScriptName(arg0: MemorySegment): MemorySegment {
    try {
        return CGFontCopyPostScriptName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCopyFullName typedef CFStringRef = (Declared(__CFString))*(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontCopyFullName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCopyFullName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCopyFullName").orElseThrow()
private val CGFontCopyFullName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCopyFullName_ADDR, CGFontCopyFullName_DESC)

fun CGFontCopyFullName(arg0: MemorySegment): MemorySegment {
    try {
        return CGFontCopyFullName_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetAscent Int(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetAscent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGFontGetAscent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetAscent").orElseThrow()
private val CGFontGetAscent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetAscent_ADDR, CGFontGetAscent_DESC)

fun CGFontGetAscent(arg0: MemorySegment): Int {
    try {
        return CGFontGetAscent_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetDescent Int(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetDescent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGFontGetDescent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetDescent").orElseThrow()
private val CGFontGetDescent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetDescent_ADDR, CGFontGetDescent_DESC)

fun CGFontGetDescent(arg0: MemorySegment): Int {
    try {
        return CGFontGetDescent_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetLeading Int(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetLeading_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGFontGetLeading_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetLeading").orElseThrow()
private val CGFontGetLeading_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetLeading_ADDR, CGFontGetLeading_DESC)

fun CGFontGetLeading(arg0: MemorySegment): Int {
    try {
        return CGFontGetLeading_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetCapHeight Int(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetCapHeight_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGFontGetCapHeight_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetCapHeight").orElseThrow()
private val CGFontGetCapHeight_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetCapHeight_ADDR, CGFontGetCapHeight_DESC)

fun CGFontGetCapHeight(arg0: MemorySegment): Int {
    try {
        return CGFontGetCapHeight_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetXHeight Int(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetXHeight_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGFontGetXHeight_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetXHeight").orElseThrow()
private val CGFontGetXHeight_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetXHeight_ADDR, CGFontGetXHeight_DESC)

fun CGFontGetXHeight(arg0: MemorySegment): Int {
    try {
        return CGFontGetXHeight_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetFontBBox typedef CGRect = Declared(CGRect)(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetFontBBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS)
private val CGFontGetFontBBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetFontBBox").orElseThrow()
private val CGFontGetFontBBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetFontBBox_ADDR, CGFontGetFontBBox_DESC)

fun CGFontGetFontBBox(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGFontGetFontBBox_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetItalicAngle typedef CGFloat = Double(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetItalicAngle_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CGFontGetItalicAngle_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetItalicAngle").orElseThrow()
private val CGFontGetItalicAngle_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetItalicAngle_ADDR, CGFontGetItalicAngle_DESC)

fun CGFontGetItalicAngle(arg0: MemorySegment): Double {
    try {
        return CGFontGetItalicAngle_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetStemV typedef CGFloat = Double(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontGetStemV_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CGFontGetStemV_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetStemV").orElseThrow()
private val CGFontGetStemV_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetStemV_ADDR, CGFontGetStemV_DESC)

fun CGFontGetStemV(arg0: MemorySegment): Double {
    try {
        return CGFontGetStemV_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCopyVariationAxes typedef CFArrayRef = (Declared(__CFArray))*(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontCopyVariationAxes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCopyVariationAxes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCopyVariationAxes").orElseThrow()
private val CGFontCopyVariationAxes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCopyVariationAxes_ADDR, CGFontCopyVariationAxes_DESC)

fun CGFontCopyVariationAxes(arg0: MemorySegment): MemorySegment {
    try {
        return CGFontCopyVariationAxes_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCopyVariations typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontCopyVariations_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCopyVariations_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCopyVariations").orElseThrow()
private val CGFontCopyVariations_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCopyVariations_ADDR, CGFontCopyVariations_DESC)

fun CGFontCopyVariations(arg0: MemorySegment): MemorySegment {
    try {
        return CGFontCopyVariations_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetGlyphAdvances Bool(typedef CGFontRef = (Declared(CGFont))*,(typedef CGGlyph = UNSIGNED = Short)*,typedef size_t = UNSIGNED = Long,(Int)*)
 */
private val CGFontGetGlyphAdvances_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGFontGetGlyphAdvances_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetGlyphAdvances").orElseThrow()
private val CGFontGetGlyphAdvances_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetGlyphAdvances_ADDR, CGFontGetGlyphAdvances_DESC)

fun CGFontGetGlyphAdvances(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): Boolean {
    try {
        return CGFontGetGlyphAdvances_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetGlyphBBoxes Bool(typedef CGFontRef = (Declared(CGFont))*,(typedef CGGlyph = UNSIGNED = Short)*,typedef size_t = UNSIGNED = Long,(typedef CGRect = Declared(CGRect))*)
 */
private val CGFontGetGlyphBBoxes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGFontGetGlyphBBoxes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetGlyphBBoxes").orElseThrow()
private val CGFontGetGlyphBBoxes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetGlyphBBoxes_ADDR, CGFontGetGlyphBBoxes_DESC)

fun CGFontGetGlyphBBoxes(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): Boolean {
    try {
        return CGFontGetGlyphBBoxes_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontGetGlyphWithGlyphName typedef CGGlyph = UNSIGNED = Short(typedef CGFontRef = (Declared(CGFont))*,typedef CFStringRef = (Declared(__CFString))*)
 */
private val CGFontGetGlyphWithGlyphName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontGetGlyphWithGlyphName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontGetGlyphWithGlyphName").orElseThrow()
private val CGFontGetGlyphWithGlyphName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontGetGlyphWithGlyphName_ADDR, CGFontGetGlyphWithGlyphName_DESC)

fun CGFontGetGlyphWithGlyphName(arg0: MemorySegment, arg1: MemorySegment): Short {
    try {
        return CGFontGetGlyphWithGlyphName_HANDLE.invokeExact(arg0, arg1) as Short
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCopyGlyphNameForGlyph typedef CFStringRef = (Declared(__CFString))*(typedef CGFontRef = (Declared(CGFont))*,typedef CGGlyph = UNSIGNED = Short)
 */
private val CGFontCopyGlyphNameForGlyph_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_SHORT)
private val CGFontCopyGlyphNameForGlyph_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCopyGlyphNameForGlyph").orElseThrow()
private val CGFontCopyGlyphNameForGlyph_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCopyGlyphNameForGlyph_ADDR, CGFontCopyGlyphNameForGlyph_DESC)

fun CGFontCopyGlyphNameForGlyph(arg0: MemorySegment, arg1: Short): MemorySegment {
    try {
        return CGFontCopyGlyphNameForGlyph_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCreatePostScriptEncoding typedef CFDataRef = (Declared(__CFData))*(typedef CGFontRef = (Declared(CGFont))*,(typedef CGGlyph = UNSIGNED = Short)*)
 */
private val CGFontCreatePostScriptEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCreatePostScriptEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCreatePostScriptEncoding").orElseThrow()
private val CGFontCreatePostScriptEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCreatePostScriptEncoding_ADDR, CGFontCreatePostScriptEncoding_DESC)

fun CGFontCreatePostScriptEncoding(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGFontCreatePostScriptEncoding_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCopyTableTags typedef CFArrayRef = (Declared(__CFArray))*(typedef CGFontRef = (Declared(CGFont))*)
 */
private val CGFontCopyTableTags_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGFontCopyTableTags_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCopyTableTags").orElseThrow()
private val CGFontCopyTableTags_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCopyTableTags_ADDR, CGFontCopyTableTags_DESC)

fun CGFontCopyTableTags(arg0: MemorySegment): MemorySegment {
    try {
        return CGFontCopyTableTags_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGFontCopyTableForTag typedef CFDataRef = (Declared(__CFData))*(typedef CGFontRef = (Declared(CGFont))*,typedef uint32_t = UNSIGNED = Int)
 */
private val CGFontCopyTableForTag_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGFontCopyTableForTag_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGFontCopyTableForTag").orElseThrow()
private val CGFontCopyTableForTag_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGFontCopyTableForTag_ADDR, CGFontCopyTableForTag_DESC)

fun CGFontCopyTableForTag(arg0: MemorySegment, arg1: Int): MemorySegment {
    try {
        return CGFontCopyTableForTag_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGFontVariationAxisName typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGFontVariationAxisName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGFontVariationAxisName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGFontVariationAxisName").orElseThrow() }
private val kCGFontVariationAxisName_VH: VarHandle by lazy { kCGFontVariationAxisName_LAYOUT.varHandle() }

var kCGFontVariationAxisName: MemorySegment
    get() = kCGFontVariationAxisName_VH.get(kCGFontVariationAxisName_SEGMENT) as MemorySegment
    set(value) = kCGFontVariationAxisName_VH.set(kCGFontVariationAxisName_SEGMENT, value)

/**
 * {@snippet lang=c : kCGFontVariationAxisMinValue typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGFontVariationAxisMinValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGFontVariationAxisMinValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGFontVariationAxisMinValue").orElseThrow() }
private val kCGFontVariationAxisMinValue_VH: VarHandle by lazy { kCGFontVariationAxisMinValue_LAYOUT.varHandle() }

var kCGFontVariationAxisMinValue: MemorySegment
    get() = kCGFontVariationAxisMinValue_VH.get(kCGFontVariationAxisMinValue_SEGMENT) as MemorySegment
    set(value) = kCGFontVariationAxisMinValue_VH.set(kCGFontVariationAxisMinValue_SEGMENT, value)

/**
 * {@snippet lang=c : kCGFontVariationAxisMaxValue typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGFontVariationAxisMaxValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGFontVariationAxisMaxValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGFontVariationAxisMaxValue").orElseThrow() }
private val kCGFontVariationAxisMaxValue_VH: VarHandle by lazy { kCGFontVariationAxisMaxValue_LAYOUT.varHandle() }

var kCGFontVariationAxisMaxValue: MemorySegment
    get() = kCGFontVariationAxisMaxValue_VH.get(kCGFontVariationAxisMaxValue_SEGMENT) as MemorySegment
    set(value) = kCGFontVariationAxisMaxValue_VH.set(kCGFontVariationAxisMaxValue_SEGMENT, value)

/**
 * {@snippet lang=c : kCGFontVariationAxisDefaultValue typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGFontVariationAxisDefaultValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGFontVariationAxisDefaultValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGFontVariationAxisDefaultValue").orElseThrow() }
private val kCGFontVariationAxisDefaultValue_VH: VarHandle by lazy { kCGFontVariationAxisDefaultValue_LAYOUT.varHandle() }

var kCGFontVariationAxisDefaultValue: MemorySegment
    get() = kCGFontVariationAxisDefaultValue_VH.get(kCGFontVariationAxisDefaultValue_SEGMENT) as MemorySegment
    set(value) = kCGFontVariationAxisDefaultValue_VH.set(kCGFontVariationAxisDefaultValue_SEGMENT, value)

/**
 * {@snippet lang=c : CGGradientGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGGradientGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGGradientGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGGradientGetTypeID").orElseThrow()
private val CGGradientGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGGradientGetTypeID_ADDR, CGGradientGetTypeID_DESC)

fun CGGradientGetTypeID(): Long {
    try {
        return CGGradientGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGGradientCreateWithColorComponents typedef CGGradientRef = (Declared(CGGradient))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,(typedef CGFloat = Double)*,(typedef CGFloat = Double)*,typedef size_t = UNSIGNED = Long)
 */
private val CGGradientCreateWithColorComponents_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGGradientCreateWithColorComponents_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGGradientCreateWithColorComponents").orElseThrow()
private val CGGradientCreateWithColorComponents_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGGradientCreateWithColorComponents_ADDR, CGGradientCreateWithColorComponents_DESC)

fun CGGradientCreateWithColorComponents(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long): MemorySegment {
    try {
        return CGGradientCreateWithColorComponents_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGGradientCreateWithContentHeadroom typedef CGGradientRef = (Declared(CGGradient))*(Float,typedef CGColorSpaceRef = (Declared(CGColorSpace))*,(typedef CGFloat = Double)*,(typedef CGFloat = Double)*,typedef size_t = UNSIGNED = Long)
 */
private val CGGradientCreateWithContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGGradientCreateWithContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGGradientCreateWithContentHeadroom").orElseThrow()
private val CGGradientCreateWithContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGGradientCreateWithContentHeadroom_ADDR, CGGradientCreateWithContentHeadroom_DESC)

fun CGGradientCreateWithContentHeadroom(arg0: Float, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: Long): MemorySegment {
    try {
        return CGGradientCreateWithContentHeadroom_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGGradientCreateWithColors typedef CGGradientRef = (Declared(CGGradient))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CFArrayRef = (Declared(__CFArray))*,(typedef CGFloat = Double)*)
 */
private val CGGradientCreateWithColors_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGGradientCreateWithColors_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGGradientCreateWithColors").orElseThrow()
private val CGGradientCreateWithColors_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGGradientCreateWithColors_ADDR, CGGradientCreateWithColors_DESC)

fun CGGradientCreateWithColors(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGGradientCreateWithColors_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGGradientRetain typedef CGGradientRef = (Declared(CGGradient))*(typedef CGGradientRef = (Declared(CGGradient))*)
 */
private val CGGradientRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGGradientRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGGradientRetain").orElseThrow()
private val CGGradientRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGGradientRetain_ADDR, CGGradientRetain_DESC)

fun CGGradientRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGGradientRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGGradientRelease Void(typedef CGGradientRef = (Declared(CGGradient))*)
 */
private val CGGradientRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGGradientRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGGradientRelease").orElseThrow()
private val CGGradientRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGGradientRelease_ADDR, CGGradientRelease_DESC)

fun CGGradientRelease(arg0: MemorySegment): Unit {
    try {
        CGGradientRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGGradientGetContentHeadroom Float(typedef CGGradientRef = (Declared(CGGradient))*)
 */
private val CGGradientGetContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS)
private val CGGradientGetContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGGradientGetContentHeadroom").orElseThrow()
private val CGGradientGetContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGGradientGetContentHeadroom_ADDR, CGGradientGetContentHeadroom_DESC)

fun CGGradientGetContentHeadroom(arg0: MemorySegment): Float {
    try {
        return CGGradientGetContentHeadroom_HANDLE.invokeExact(arg0) as Float
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGImageGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGImageGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetTypeID").orElseThrow()
private val CGImageGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetTypeID_ADDR, CGImageGetTypeID_DESC)

fun CGImageGetTypeID(): Long {
    try {
        return CGImageGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageMaskCreate typedef CGImageRef = (Declared(CGImage))*(typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef CGDataProviderRef = (Declared(CGDataProvider))*,(typedef CGFloat = Double)*,Bool)
 */
private val CGImageMaskCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGImageMaskCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageMaskCreate").orElseThrow()
private val CGImageMaskCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageMaskCreate_ADDR, CGImageMaskCreate_DESC)

fun CGImageMaskCreate(arg0: Long, arg1: Long, arg2: Long, arg3: Long, arg4: Long, arg5: MemorySegment, arg6: MemorySegment, arg7: Boolean): MemorySegment {
    try {
        return CGImageMaskCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCreateCopy typedef CGImageRef = (Declared(CGImage))*(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCreateCopy").orElseThrow()
private val CGImageCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCreateCopy_ADDR, CGImageCreateCopy_DESC)

fun CGImageCreateCopy(arg0: MemorySegment): MemorySegment {
    try {
        return CGImageCreateCopy_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCreateWithImageInRect typedef CGImageRef = (Declared(CGImage))*(typedef CGImageRef = (Declared(CGImage))*,typedef CGRect = Declared(CGRect))
 */
private val CGImageCreateWithImageInRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGRect.layout)
private val CGImageCreateWithImageInRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCreateWithImageInRect").orElseThrow()
private val CGImageCreateWithImageInRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCreateWithImageInRect_ADDR, CGImageCreateWithImageInRect_DESC)

fun CGImageCreateWithImageInRect(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGImageCreateWithImageInRect_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCreateWithMask typedef CGImageRef = (Declared(CGImage))*(typedef CGImageRef = (Declared(CGImage))*,typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageCreateWithMask_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageCreateWithMask_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCreateWithMask").orElseThrow()
private val CGImageCreateWithMask_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCreateWithMask_ADDR, CGImageCreateWithMask_DESC)

fun CGImageCreateWithMask(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGImageCreateWithMask_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCreateWithMaskingColors typedef CGImageRef = (Declared(CGImage))*(typedef CGImageRef = (Declared(CGImage))*,(typedef CGFloat = Double)*)
 */
private val CGImageCreateWithMaskingColors_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageCreateWithMaskingColors_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCreateWithMaskingColors").orElseThrow()
private val CGImageCreateWithMaskingColors_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCreateWithMaskingColors_ADDR, CGImageCreateWithMaskingColors_DESC)

fun CGImageCreateWithMaskingColors(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGImageCreateWithMaskingColors_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCreateCopyWithColorSpace typedef CGImageRef = (Declared(CGImage))*(typedef CGImageRef = (Declared(CGImage))*,typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGImageCreateCopyWithColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageCreateCopyWithColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCreateCopyWithColorSpace").orElseThrow()
private val CGImageCreateCopyWithColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCreateCopyWithColorSpace_ADDR, CGImageCreateCopyWithColorSpace_DESC)

fun CGImageCreateCopyWithColorSpace(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGImageCreateCopyWithColorSpace_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCreateCopyWithContentHeadroom typedef CGImageRef = (Declared(CGImage))*(Float,typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageCreateCopyWithContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS)
private val CGImageCreateCopyWithContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCreateCopyWithContentHeadroom").orElseThrow()
private val CGImageCreateCopyWithContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCreateCopyWithContentHeadroom_ADDR, CGImageCreateCopyWithContentHeadroom_DESC)

fun CGImageCreateCopyWithContentHeadroom(arg0: Float, arg1: MemorySegment): MemorySegment {
    try {
        return CGImageCreateCopyWithContentHeadroom_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGDefaultHDRImageContentHeadroom Float
 */
private val kCGDefaultHDRImageContentHeadroom_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_FLOAT }
private val kCGDefaultHDRImageContentHeadroom_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDefaultHDRImageContentHeadroom").orElseThrow() }
private val kCGDefaultHDRImageContentHeadroom_VH: VarHandle by lazy { kCGDefaultHDRImageContentHeadroom_LAYOUT.varHandle() }

var kCGDefaultHDRImageContentHeadroom: Float
    get() = kCGDefaultHDRImageContentHeadroom_VH.get(kCGDefaultHDRImageContentHeadroom_SEGMENT) as Float
    set(value) = kCGDefaultHDRImageContentHeadroom_VH.set(kCGDefaultHDRImageContentHeadroom_SEGMENT, value)

/**
 * {@snippet lang=c : CGImageGetContentHeadroom Float(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS)
private val CGImageGetContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetContentHeadroom").orElseThrow()
private val CGImageGetContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetContentHeadroom_ADDR, CGImageGetContentHeadroom_DESC)

fun CGImageGetContentHeadroom(arg0: MemorySegment): Float {
    try {
        return CGImageGetContentHeadroom_HANDLE.invokeExact(arg0) as Float
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCalculateContentHeadroom Float(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageCalculateContentHeadroom_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS)
private val CGImageCalculateContentHeadroom_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCalculateContentHeadroom").orElseThrow()
private val CGImageCalculateContentHeadroom_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCalculateContentHeadroom_ADDR, CGImageCalculateContentHeadroom_DESC)

fun CGImageCalculateContentHeadroom(arg0: MemorySegment): Float {
    try {
        return CGImageCalculateContentHeadroom_HANDLE.invokeExact(arg0) as Float
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetContentAverageLightLevel Float(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetContentAverageLightLevel_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS)
private val CGImageGetContentAverageLightLevel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetContentAverageLightLevel").orElseThrow()
private val CGImageGetContentAverageLightLevel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetContentAverageLightLevel_ADDR, CGImageGetContentAverageLightLevel_DESC)

fun CGImageGetContentAverageLightLevel(arg0: MemorySegment): Float {
    try {
        return CGImageGetContentAverageLightLevel_HANDLE.invokeExact(arg0) as Float
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCalculateContentAverageLightLevel Float(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageCalculateContentAverageLightLevel_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, ValueLayout.ADDRESS)
private val CGImageCalculateContentAverageLightLevel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCalculateContentAverageLightLevel").orElseThrow()
private val CGImageCalculateContentAverageLightLevel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCalculateContentAverageLightLevel_ADDR, CGImageCalculateContentAverageLightLevel_DESC)

fun CGImageCalculateContentAverageLightLevel(arg0: MemorySegment): Float {
    try {
        return CGImageCalculateContentAverageLightLevel_HANDLE.invokeExact(arg0) as Float
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCreateCopyWithContentAverageLightLevel typedef CGImageRef = (Declared(CGImage))*(typedef CGImageRef = (Declared(CGImage))*,Float)
 */
private val CGImageCreateCopyWithContentAverageLightLevel_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_FLOAT)
private val CGImageCreateCopyWithContentAverageLightLevel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCreateCopyWithContentAverageLightLevel").orElseThrow()
private val CGImageCreateCopyWithContentAverageLightLevel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCreateCopyWithContentAverageLightLevel_ADDR, CGImageCreateCopyWithContentAverageLightLevel_DESC)

fun CGImageCreateCopyWithContentAverageLightLevel(arg0: MemorySegment, arg1: Float): MemorySegment {
    try {
        return CGImageCreateCopyWithContentAverageLightLevel_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageCreateCopyWithCalculatedHDRStats typedef CGImageRef = (Declared(CGImage))*(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageCreateCopyWithCalculatedHDRStats_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageCreateCopyWithCalculatedHDRStats_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageCreateCopyWithCalculatedHDRStats").orElseThrow()
private val CGImageCreateCopyWithCalculatedHDRStats_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageCreateCopyWithCalculatedHDRStats_ADDR, CGImageCreateCopyWithCalculatedHDRStats_DESC)

fun CGImageCreateCopyWithCalculatedHDRStats(arg0: MemorySegment): MemorySegment {
    try {
        return CGImageCreateCopyWithCalculatedHDRStats_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageRetain typedef CGImageRef = (Declared(CGImage))*(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageRetain").orElseThrow()
private val CGImageRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageRetain_ADDR, CGImageRetain_DESC)

fun CGImageRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGImageRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageRelease Void(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGImageRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageRelease").orElseThrow()
private val CGImageRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageRelease_ADDR, CGImageRelease_DESC)

fun CGImageRelease(arg0: MemorySegment): Unit {
    try {
        CGImageRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageIsMask Bool(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageIsMask_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGImageIsMask_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageIsMask").orElseThrow()
private val CGImageIsMask_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageIsMask_ADDR, CGImageIsMask_DESC)

fun CGImageIsMask(arg0: MemorySegment): Boolean {
    try {
        return CGImageIsMask_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetWidth typedef size_t = UNSIGNED = Long(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetWidth_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGImageGetWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetWidth").orElseThrow()
private val CGImageGetWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetWidth_ADDR, CGImageGetWidth_DESC)

fun CGImageGetWidth(arg0: MemorySegment): Long {
    try {
        return CGImageGetWidth_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetHeight typedef size_t = UNSIGNED = Long(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetHeight_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGImageGetHeight_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetHeight").orElseThrow()
private val CGImageGetHeight_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetHeight_ADDR, CGImageGetHeight_DESC)

fun CGImageGetHeight(arg0: MemorySegment): Long {
    try {
        return CGImageGetHeight_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetBitsPerComponent typedef size_t = UNSIGNED = Long(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetBitsPerComponent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGImageGetBitsPerComponent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetBitsPerComponent").orElseThrow()
private val CGImageGetBitsPerComponent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetBitsPerComponent_ADDR, CGImageGetBitsPerComponent_DESC)

fun CGImageGetBitsPerComponent(arg0: MemorySegment): Long {
    try {
        return CGImageGetBitsPerComponent_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetBitsPerPixel typedef size_t = UNSIGNED = Long(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetBitsPerPixel_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGImageGetBitsPerPixel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetBitsPerPixel").orElseThrow()
private val CGImageGetBitsPerPixel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetBitsPerPixel_ADDR, CGImageGetBitsPerPixel_DESC)

fun CGImageGetBitsPerPixel(arg0: MemorySegment): Long {
    try {
        return CGImageGetBitsPerPixel_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetBytesPerRow typedef size_t = UNSIGNED = Long(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetBytesPerRow_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGImageGetBytesPerRow_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetBytesPerRow").orElseThrow()
private val CGImageGetBytesPerRow_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetBytesPerRow_ADDR, CGImageGetBytesPerRow_DESC)

fun CGImageGetBytesPerRow(arg0: MemorySegment): Long {
    try {
        return CGImageGetBytesPerRow_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetColorSpace typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageGetColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetColorSpace").orElseThrow()
private val CGImageGetColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetColorSpace_ADDR, CGImageGetColorSpace_DESC)

fun CGImageGetColorSpace(arg0: MemorySegment): MemorySegment {
    try {
        return CGImageGetColorSpace_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetDataProvider typedef CGDataProviderRef = (Declared(CGDataProvider))*(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetDataProvider_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageGetDataProvider_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetDataProvider").orElseThrow()
private val CGImageGetDataProvider_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetDataProvider_ADDR, CGImageGetDataProvider_DESC)

fun CGImageGetDataProvider(arg0: MemorySegment): MemorySegment {
    try {
        return CGImageGetDataProvider_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetDecode (typedef CGFloat = Double)*(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetDecode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageGetDecode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetDecode").orElseThrow()
private val CGImageGetDecode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetDecode_ADDR, CGImageGetDecode_DESC)

fun CGImageGetDecode(arg0: MemorySegment): MemorySegment {
    try {
        return CGImageGetDecode_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetShouldInterpolate Bool(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetShouldInterpolate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGImageGetShouldInterpolate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetShouldInterpolate").orElseThrow()
private val CGImageGetShouldInterpolate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetShouldInterpolate_ADDR, CGImageGetShouldInterpolate_DESC)

fun CGImageGetShouldInterpolate(arg0: MemorySegment): Boolean {
    try {
        return CGImageGetShouldInterpolate_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageShouldToneMap Bool(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageShouldToneMap_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGImageShouldToneMap_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageShouldToneMap").orElseThrow()
private val CGImageShouldToneMap_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageShouldToneMap_ADDR, CGImageShouldToneMap_DESC)

fun CGImageShouldToneMap(arg0: MemorySegment): Boolean {
    try {
        return CGImageShouldToneMap_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageContainsImageSpecificToneMappingMetadata Bool(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageContainsImageSpecificToneMappingMetadata_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGImageContainsImageSpecificToneMappingMetadata_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageContainsImageSpecificToneMappingMetadata").orElseThrow()
private val CGImageContainsImageSpecificToneMappingMetadata_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageContainsImageSpecificToneMappingMetadata_ADDR, CGImageContainsImageSpecificToneMappingMetadata_DESC)

fun CGImageContainsImageSpecificToneMappingMetadata(arg0: MemorySegment): Boolean {
    try {
        return CGImageContainsImageSpecificToneMappingMetadata_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGImageGetUTType typedef CFStringRef = (Declared(__CFString))*(typedef CGImageRef = (Declared(CGImage))*)
 */
private val CGImageGetUTType_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGImageGetUTType_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGImageGetUTType").orElseThrow()
private val CGImageGetUTType_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGImageGetUTType_ADDR, CGImageGetUTType_DESC)

fun CGImageGetUTType(arg0: MemorySegment): MemorySegment {
    try {
        return CGImageGetUTType_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGPathGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGPathGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathGetTypeID").orElseThrow()
private val CGPathGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathGetTypeID_ADDR, CGPathGetTypeID_DESC)

fun CGPathGetTypeID(): Long {
    try {
        return CGPathGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateMutable typedef CGMutablePathRef = (Declared(CGPath))*()
 */
private val CGPathCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CGPathCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateMutable").orElseThrow()
private val CGPathCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateMutable_ADDR, CGPathCreateMutable_DESC)

fun CGPathCreateMutable(): MemorySegment {
    try {
        return CGPathCreateMutable_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopy typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopy").orElseThrow()
private val CGPathCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopy_ADDR, CGPathCreateCopy_DESC)

fun CGPathCreateCopy(arg0: MemorySegment): MemorySegment {
    try {
        return CGPathCreateCopy_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopyByTransformingPath typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*)
 */
private val CGPathCreateCopyByTransformingPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathCreateCopyByTransformingPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyByTransformingPath").orElseThrow()
private val CGPathCreateCopyByTransformingPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyByTransformingPath_ADDR, CGPathCreateCopyByTransformingPath_DESC)

fun CGPathCreateCopyByTransformingPath(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGPathCreateCopyByTransformingPath_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateMutableCopy typedef CGMutablePathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateMutableCopy").orElseThrow()
private val CGPathCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateMutableCopy_ADDR, CGPathCreateMutableCopy_DESC)

fun CGPathCreateMutableCopy(arg0: MemorySegment): MemorySegment {
    try {
        return CGPathCreateMutableCopy_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateMutableCopyByTransformingPath typedef CGMutablePathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*)
 */
private val CGPathCreateMutableCopyByTransformingPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathCreateMutableCopyByTransformingPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateMutableCopyByTransformingPath").orElseThrow()
private val CGPathCreateMutableCopyByTransformingPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateMutableCopyByTransformingPath_ADDR, CGPathCreateMutableCopyByTransformingPath_DESC)

fun CGPathCreateMutableCopyByTransformingPath(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGPathCreateMutableCopyByTransformingPath_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateWithRect typedef CGPathRef = (Declared(CGPath))*(typedef CGRect = Declared(CGRect),(typedef CGAffineTransform = <error: struct CGAffineTransform>)*)
 */
private val CGPathCreateWithRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.ADDRESS)
private val CGPathCreateWithRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateWithRect").orElseThrow()
private val CGPathCreateWithRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateWithRect_ADDR, CGPathCreateWithRect_DESC)

fun CGPathCreateWithRect(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGPathCreateWithRect_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateWithEllipseInRect typedef CGPathRef = (Declared(CGPath))*(typedef CGRect = Declared(CGRect),(typedef CGAffineTransform = <error: struct CGAffineTransform>)*)
 */
private val CGPathCreateWithEllipseInRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.ADDRESS)
private val CGPathCreateWithEllipseInRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateWithEllipseInRect").orElseThrow()
private val CGPathCreateWithEllipseInRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateWithEllipseInRect_ADDR, CGPathCreateWithEllipseInRect_DESC)

fun CGPathCreateWithEllipseInRect(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGPathCreateWithEllipseInRect_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateWithRoundedRect typedef CGPathRef = (Declared(CGPath))*(typedef CGRect = Declared(CGRect),typedef CGFloat = Double,typedef CGFloat = Double,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*)
 */
private val CGPathCreateWithRoundedRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CGPathCreateWithRoundedRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateWithRoundedRect").orElseThrow()
private val CGPathCreateWithRoundedRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateWithRoundedRect_ADDR, CGPathCreateWithRoundedRect_DESC)

fun CGPathCreateWithRoundedRect(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: MemorySegment): MemorySegment {
    try {
        return CGPathCreateWithRoundedRect_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddRoundedRect Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGRect = Declared(CGRect),typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGPathAddRoundedRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGRect.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGPathAddRoundedRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddRoundedRect").orElseThrow()
private val CGPathAddRoundedRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddRoundedRect_ADDR, CGPathAddRoundedRect_DESC)

fun CGPathAddRoundedRect(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Double, arg4: Double): Unit {
    try {
        CGPathAddRoundedRect_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopyByDashingPath typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGFloat = Double,(typedef CGFloat = Double)*,typedef size_t = UNSIGNED = Long)
 */
private val CGPathCreateCopyByDashingPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGPathCreateCopyByDashingPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyByDashingPath").orElseThrow()
private val CGPathCreateCopyByDashingPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyByDashingPath_ADDR, CGPathCreateCopyByDashingPath_DESC)

fun CGPathCreateCopyByDashingPath(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: MemorySegment, arg4: Long): MemorySegment {
    try {
        return CGPathCreateCopyByDashingPath_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathRetain typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathRetain").orElseThrow()
private val CGPathRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathRetain_ADDR, CGPathRetain_DESC)

fun CGPathRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGPathRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathRelease Void(typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPathRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathRelease").orElseThrow()
private val CGPathRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathRelease_ADDR, CGPathRelease_DESC)

fun CGPathRelease(arg0: MemorySegment): Unit {
    try {
        CGPathRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathEqualToPath Bool(typedef CGPathRef = (Declared(CGPath))*,typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathEqualToPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathEqualToPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathEqualToPath").orElseThrow()
private val CGPathEqualToPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathEqualToPath_ADDR, CGPathEqualToPath_DESC)

fun CGPathEqualToPath(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPathEqualToPath_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathMoveToPoint Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGPathMoveToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGPathMoveToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathMoveToPoint").orElseThrow()
private val CGPathMoveToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathMoveToPoint_ADDR, CGPathMoveToPoint_DESC)

fun CGPathMoveToPoint(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: Double): Unit {
    try {
        CGPathMoveToPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddLineToPoint Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGPathAddLineToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGPathAddLineToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddLineToPoint").orElseThrow()
private val CGPathAddLineToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddLineToPoint_ADDR, CGPathAddLineToPoint_DESC)

fun CGPathAddLineToPoint(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: Double): Unit {
    try {
        CGPathAddLineToPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddQuadCurveToPoint Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGPathAddQuadCurveToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGPathAddQuadCurveToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddQuadCurveToPoint").orElseThrow()
private val CGPathAddQuadCurveToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddQuadCurveToPoint_ADDR, CGPathAddQuadCurveToPoint_DESC)

fun CGPathAddQuadCurveToPoint(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: Double, arg4: Double, arg5: Double): Unit {
    try {
        CGPathAddQuadCurveToPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddCurveToPoint Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGPathAddCurveToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGPathAddCurveToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddCurveToPoint").orElseThrow()
private val CGPathAddCurveToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddCurveToPoint_ADDR, CGPathAddCurveToPoint_DESC)

fun CGPathAddCurveToPoint(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: Double, arg4: Double, arg5: Double, arg6: Double, arg7: Double): Unit {
    try {
        CGPathAddCurveToPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCloseSubpath Void(typedef CGMutablePathRef = (Declared(CGPath))*)
 */
private val CGPathCloseSubpath_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPathCloseSubpath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCloseSubpath").orElseThrow()
private val CGPathCloseSubpath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCloseSubpath_ADDR, CGPathCloseSubpath_DESC)

fun CGPathCloseSubpath(arg0: MemorySegment): Unit {
    try {
        CGPathCloseSubpath_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddRect Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGRect = Declared(CGRect))
 */
private val CGPathAddRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGRect.layout)
private val CGPathAddRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddRect").orElseThrow()
private val CGPathAddRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddRect_ADDR, CGPathAddRect_DESC)

fun CGPathAddRect(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPathAddRect_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddRects Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,(typedef CGRect = Declared(CGRect))*,typedef size_t = UNSIGNED = Long)
 */
private val CGPathAddRects_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGPathAddRects_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddRects").orElseThrow()
private val CGPathAddRects_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddRects_ADDR, CGPathAddRects_DESC)

fun CGPathAddRects(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long): Unit {
    try {
        CGPathAddRects_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddLines Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,(typedef CGPoint = Declared(CGPoint))*,typedef size_t = UNSIGNED = Long)
 */
private val CGPathAddLines_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CGPathAddLines_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddLines").orElseThrow()
private val CGPathAddLines_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddLines_ADDR, CGPathAddLines_DESC)

fun CGPathAddLines(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long): Unit {
    try {
        CGPathAddLines_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddEllipseInRect Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGRect = Declared(CGRect))
 */
private val CGPathAddEllipseInRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGRect.layout)
private val CGPathAddEllipseInRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddEllipseInRect").orElseThrow()
private val CGPathAddEllipseInRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddEllipseInRect_ADDR, CGPathAddEllipseInRect_DESC)

fun CGPathAddEllipseInRect(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPathAddEllipseInRect_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddRelativeArc Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGPathAddRelativeArc_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGPathAddRelativeArc_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddRelativeArc").orElseThrow()
private val CGPathAddRelativeArc_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddRelativeArc_ADDR, CGPathAddRelativeArc_DESC)

fun CGPathAddRelativeArc(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: Double, arg4: Double, arg5: Double, arg6: Double): Unit {
    try {
        CGPathAddRelativeArc_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddArc Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,Bool)
 */
private val CGPathAddArc_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_BOOLEAN)
private val CGPathAddArc_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddArc").orElseThrow()
private val CGPathAddArc_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddArc_ADDR, CGPathAddArc_DESC)

fun CGPathAddArc(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: Double, arg4: Double, arg5: Double, arg6: Double, arg7: Boolean): Unit {
    try {
        CGPathAddArc_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddArcToPoint Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGPathAddArcToPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGPathAddArcToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddArcToPoint").orElseThrow()
private val CGPathAddArcToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddArcToPoint_ADDR, CGPathAddArcToPoint_DESC)

fun CGPathAddArcToPoint(arg0: MemorySegment, arg1: MemorySegment, arg2: Double, arg3: Double, arg4: Double, arg5: Double, arg6: Double): Unit {
    try {
        CGPathAddArcToPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathAddPath Void(typedef CGMutablePathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathAddPath_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathAddPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathAddPath").orElseThrow()
private val CGPathAddPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathAddPath_ADDR, CGPathAddPath_DESC)

fun CGPathAddPath(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPathAddPath_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathIsEmpty Bool(typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathIsEmpty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGPathIsEmpty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathIsEmpty").orElseThrow()
private val CGPathIsEmpty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathIsEmpty_ADDR, CGPathIsEmpty_DESC)

fun CGPathIsEmpty(arg0: MemorySegment): Boolean {
    try {
        return CGPathIsEmpty_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathIsRect Bool(typedef CGPathRef = (Declared(CGPath))*,(typedef CGRect = Declared(CGRect))*)
 */
private val CGPathIsRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathIsRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathIsRect").orElseThrow()
private val CGPathIsRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathIsRect_ADDR, CGPathIsRect_DESC)

fun CGPathIsRect(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPathIsRect_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathGetCurrentPoint typedef CGPoint = Declared(CGPoint)(typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathGetCurrentPoint_DESC: FunctionDescriptor = FunctionDescriptor.of(CGPoint.layout, ValueLayout.ADDRESS)
private val CGPathGetCurrentPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathGetCurrentPoint").orElseThrow()
private val CGPathGetCurrentPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathGetCurrentPoint_ADDR, CGPathGetCurrentPoint_DESC)

fun CGPathGetCurrentPoint(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGPathGetCurrentPoint_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathGetBoundingBox typedef CGRect = Declared(CGRect)(typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathGetBoundingBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS)
private val CGPathGetBoundingBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathGetBoundingBox").orElseThrow()
private val CGPathGetBoundingBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathGetBoundingBox_ADDR, CGPathGetBoundingBox_DESC)

fun CGPathGetBoundingBox(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGPathGetBoundingBox_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathGetPathBoundingBox typedef CGRect = Declared(CGRect)(typedef CGPathRef = (Declared(CGPath))*)
 */
private val CGPathGetPathBoundingBox_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS)
private val CGPathGetPathBoundingBox_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathGetPathBoundingBox").orElseThrow()
private val CGPathGetPathBoundingBox_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathGetPathBoundingBox_ADDR, CGPathGetPathBoundingBox_DESC)

fun CGPathGetPathBoundingBox(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGPathGetPathBoundingBox_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathContainsPoint Bool(typedef CGPathRef = (Declared(CGPath))*,(typedef CGAffineTransform = <error: struct CGAffineTransform>)*,typedef CGPoint = Declared(CGPoint),Bool)
 */
private val CGPathContainsPoint_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGPoint.layout, ValueLayout.JAVA_BOOLEAN)
private val CGPathContainsPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathContainsPoint").orElseThrow()
private val CGPathContainsPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathContainsPoint_ADDR, CGPathContainsPoint_DESC)

fun CGPathContainsPoint(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Boolean): Boolean {
    try {
        return CGPathContainsPoint_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathApply Void(typedef CGPathRef = (Declared(CGPath))*,(Void)*,typedef CGPathApplierFunction = (Void((Void)*,(Declared(CGPathElement))*))*)
 */
private val CGPathApply_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathApply_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathApply").orElseThrow()
private val CGPathApply_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathApply_ADDR, CGPathApply_DESC)

fun CGPathApply(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPathApply_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathApplyWithBlock Void(typedef CGPathRef = (Declared(CGPath))*,typedef CGPathApplyBlock = (Void)*)
 */
private val CGPathApplyWithBlock_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPathApplyWithBlock_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathApplyWithBlock").orElseThrow()
private val CGPathApplyWithBlock_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathApplyWithBlock_ADDR, CGPathApplyWithBlock_DESC)

fun CGPathApplyWithBlock(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGPathApplyWithBlock_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopyByNormalizing typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,Bool)
 */
private val CGPathCreateCopyByNormalizing_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGPathCreateCopyByNormalizing_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyByNormalizing").orElseThrow()
private val CGPathCreateCopyByNormalizing_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyByNormalizing_ADDR, CGPathCreateCopyByNormalizing_DESC)

fun CGPathCreateCopyByNormalizing(arg0: MemorySegment, arg1: Boolean): MemorySegment {
    try {
        return CGPathCreateCopyByNormalizing_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopyByUnioningPath typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,typedef CGPathRef = (Declared(CGPath))*,Bool)
 */
private val CGPathCreateCopyByUnioningPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGPathCreateCopyByUnioningPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyByUnioningPath").orElseThrow()
private val CGPathCreateCopyByUnioningPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyByUnioningPath_ADDR, CGPathCreateCopyByUnioningPath_DESC)

fun CGPathCreateCopyByUnioningPath(arg0: MemorySegment, arg1: MemorySegment, arg2: Boolean): MemorySegment {
    try {
        return CGPathCreateCopyByUnioningPath_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopyByIntersectingPath typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,typedef CGPathRef = (Declared(CGPath))*,Bool)
 */
private val CGPathCreateCopyByIntersectingPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGPathCreateCopyByIntersectingPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyByIntersectingPath").orElseThrow()
private val CGPathCreateCopyByIntersectingPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyByIntersectingPath_ADDR, CGPathCreateCopyByIntersectingPath_DESC)

fun CGPathCreateCopyByIntersectingPath(arg0: MemorySegment, arg1: MemorySegment, arg2: Boolean): MemorySegment {
    try {
        return CGPathCreateCopyByIntersectingPath_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPathCreateCopyBySubtractingPath typedef CGPathRef = (Declared(CGPath))*(typedef CGPathRef = (Declared(CGPath))*,typedef CGPathRef = (Declared(CGPath))*,Bool)
 */
private val CGPathCreateCopyBySubtractingPath_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN)
private val CGPathCreateCopyBySubtractingPath_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPathCreateCopyBySubtractingPath").orElseThrow()
private val CGPathCreateCopyBySubtractingPath_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPathCreateCopyBySubtractingPath_ADDR, CGPathCreateCopyBySubtractingPath_DESC)

fun CGPathCreateCopyBySubtractingPath(arg0: MemorySegment, arg1: MemorySegment, arg2: Boolean): MemorySegment {
    try {
        return CGPathCreateCopyBySubtractingPath_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

