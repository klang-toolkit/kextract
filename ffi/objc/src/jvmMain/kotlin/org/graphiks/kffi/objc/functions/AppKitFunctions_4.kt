package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : NSStringTransformStripCombiningMarks typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformStripCombiningMarks_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformStripCombiningMarks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformStripCombiningMarks").orElseThrow() }
private val NSStringTransformStripCombiningMarks_VH: VarHandle by lazy { NSStringTransformStripCombiningMarks_LAYOUT.varHandle() }

var NSStringTransformStripCombiningMarks: MemorySegment
    get() = NSStringTransformStripCombiningMarks_VH.get(NSStringTransformStripCombiningMarks_SEGMENT) as MemorySegment
    set(value) = NSStringTransformStripCombiningMarks_VH.set(NSStringTransformStripCombiningMarks_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringTransformStripDiacritics typedef const NSStringTransform = (Void)*
 */
private val NSStringTransformStripDiacritics_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringTransformStripDiacritics_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringTransformStripDiacritics").orElseThrow() }
private val NSStringTransformStripDiacritics_VH: VarHandle by lazy { NSStringTransformStripDiacritics_LAYOUT.varHandle() }

var NSStringTransformStripDiacritics: MemorySegment
    get() = NSStringTransformStripDiacritics_VH.get(NSStringTransformStripDiacritics_SEGMENT) as MemorySegment
    set(value) = NSStringTransformStripDiacritics_VH.set(NSStringTransformStripDiacritics_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringEncodingDetectionSuggestedEncodingsKey typedef const NSStringEncodingDetectionOptionsKey = (Void)*
 */
private val NSStringEncodingDetectionSuggestedEncodingsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringEncodingDetectionSuggestedEncodingsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringEncodingDetectionSuggestedEncodingsKey").orElseThrow() }
private val NSStringEncodingDetectionSuggestedEncodingsKey_VH: VarHandle by lazy { NSStringEncodingDetectionSuggestedEncodingsKey_LAYOUT.varHandle() }

var NSStringEncodingDetectionSuggestedEncodingsKey: MemorySegment
    get() = NSStringEncodingDetectionSuggestedEncodingsKey_VH.get(NSStringEncodingDetectionSuggestedEncodingsKey_SEGMENT) as MemorySegment
    set(value) = NSStringEncodingDetectionSuggestedEncodingsKey_VH.set(NSStringEncodingDetectionSuggestedEncodingsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringEncodingDetectionDisallowedEncodingsKey typedef const NSStringEncodingDetectionOptionsKey = (Void)*
 */
private val NSStringEncodingDetectionDisallowedEncodingsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringEncodingDetectionDisallowedEncodingsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringEncodingDetectionDisallowedEncodingsKey").orElseThrow() }
private val NSStringEncodingDetectionDisallowedEncodingsKey_VH: VarHandle by lazy { NSStringEncodingDetectionDisallowedEncodingsKey_LAYOUT.varHandle() }

var NSStringEncodingDetectionDisallowedEncodingsKey: MemorySegment
    get() = NSStringEncodingDetectionDisallowedEncodingsKey_VH.get(NSStringEncodingDetectionDisallowedEncodingsKey_SEGMENT) as MemorySegment
    set(value) = NSStringEncodingDetectionDisallowedEncodingsKey_VH.set(NSStringEncodingDetectionDisallowedEncodingsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringEncodingDetectionUseOnlySuggestedEncodingsKey typedef const NSStringEncodingDetectionOptionsKey = (Void)*
 */
private val NSStringEncodingDetectionUseOnlySuggestedEncodingsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringEncodingDetectionUseOnlySuggestedEncodingsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringEncodingDetectionUseOnlySuggestedEncodingsKey").orElseThrow() }
private val NSStringEncodingDetectionUseOnlySuggestedEncodingsKey_VH: VarHandle by lazy { NSStringEncodingDetectionUseOnlySuggestedEncodingsKey_LAYOUT.varHandle() }

var NSStringEncodingDetectionUseOnlySuggestedEncodingsKey: MemorySegment
    get() = NSStringEncodingDetectionUseOnlySuggestedEncodingsKey_VH.get(NSStringEncodingDetectionUseOnlySuggestedEncodingsKey_SEGMENT) as MemorySegment
    set(value) = NSStringEncodingDetectionUseOnlySuggestedEncodingsKey_VH.set(NSStringEncodingDetectionUseOnlySuggestedEncodingsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringEncodingDetectionAllowLossyKey typedef const NSStringEncodingDetectionOptionsKey = (Void)*
 */
private val NSStringEncodingDetectionAllowLossyKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringEncodingDetectionAllowLossyKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringEncodingDetectionAllowLossyKey").orElseThrow() }
private val NSStringEncodingDetectionAllowLossyKey_VH: VarHandle by lazy { NSStringEncodingDetectionAllowLossyKey_LAYOUT.varHandle() }

var NSStringEncodingDetectionAllowLossyKey: MemorySegment
    get() = NSStringEncodingDetectionAllowLossyKey_VH.get(NSStringEncodingDetectionAllowLossyKey_SEGMENT) as MemorySegment
    set(value) = NSStringEncodingDetectionAllowLossyKey_VH.set(NSStringEncodingDetectionAllowLossyKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringEncodingDetectionFromWindowsKey typedef const NSStringEncodingDetectionOptionsKey = (Void)*
 */
private val NSStringEncodingDetectionFromWindowsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringEncodingDetectionFromWindowsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringEncodingDetectionFromWindowsKey").orElseThrow() }
private val NSStringEncodingDetectionFromWindowsKey_VH: VarHandle by lazy { NSStringEncodingDetectionFromWindowsKey_LAYOUT.varHandle() }

var NSStringEncodingDetectionFromWindowsKey: MemorySegment
    get() = NSStringEncodingDetectionFromWindowsKey_VH.get(NSStringEncodingDetectionFromWindowsKey_SEGMENT) as MemorySegment
    set(value) = NSStringEncodingDetectionFromWindowsKey_VH.set(NSStringEncodingDetectionFromWindowsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringEncodingDetectionLossySubstitutionKey typedef const NSStringEncodingDetectionOptionsKey = (Void)*
 */
private val NSStringEncodingDetectionLossySubstitutionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringEncodingDetectionLossySubstitutionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringEncodingDetectionLossySubstitutionKey").orElseThrow() }
private val NSStringEncodingDetectionLossySubstitutionKey_VH: VarHandle by lazy { NSStringEncodingDetectionLossySubstitutionKey_LAYOUT.varHandle() }

var NSStringEncodingDetectionLossySubstitutionKey: MemorySegment
    get() = NSStringEncodingDetectionLossySubstitutionKey_VH.get(NSStringEncodingDetectionLossySubstitutionKey_SEGMENT) as MemorySegment
    set(value) = NSStringEncodingDetectionLossySubstitutionKey_VH.set(NSStringEncodingDetectionLossySubstitutionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringEncodingDetectionLikelyLanguageKey typedef const NSStringEncodingDetectionOptionsKey = (Void)*
 */
private val NSStringEncodingDetectionLikelyLanguageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringEncodingDetectionLikelyLanguageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringEncodingDetectionLikelyLanguageKey").orElseThrow() }
private val NSStringEncodingDetectionLikelyLanguageKey_VH: VarHandle by lazy { NSStringEncodingDetectionLikelyLanguageKey_LAYOUT.varHandle() }

var NSStringEncodingDetectionLikelyLanguageKey: MemorySegment
    get() = NSStringEncodingDetectionLikelyLanguageKey_VH.get(NSStringEncodingDetectionLikelyLanguageKey_SEGMENT) as MemorySegment
    set(value) = NSStringEncodingDetectionLikelyLanguageKey_VH.set(NSStringEncodingDetectionLikelyLanguageKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSCharacterConversionException typedef const NSExceptionName = (Void)*
 */
private val NSCharacterConversionException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCharacterConversionException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCharacterConversionException").orElseThrow() }
private val NSCharacterConversionException_VH: VarHandle by lazy { NSCharacterConversionException_LAYOUT.varHandle() }

var NSCharacterConversionException: MemorySegment
    get() = NSCharacterConversionException_VH.get(NSCharacterConversionException_SEGMENT) as MemorySegment
    set(value) = NSCharacterConversionException_VH.set(NSCharacterConversionException_SEGMENT, value)

/**
 * {@snippet lang=c : NSParseErrorException typedef const NSExceptionName = (Void)*
 */
private val NSParseErrorException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSParseErrorException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSParseErrorException").orElseThrow() }
private val NSParseErrorException_VH: VarHandle by lazy { NSParseErrorException_LAYOUT.varHandle() }

var NSParseErrorException: MemorySegment
    get() = NSParseErrorException_VH.get(NSParseErrorException_SEGMENT) as MemorySegment
    set(value) = NSParseErrorException_VH.set(NSParseErrorException_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressEstimatedTimeRemainingKey typedef const NSProgressUserInfoKey = (Void)*
 */
private val NSProgressEstimatedTimeRemainingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressEstimatedTimeRemainingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressEstimatedTimeRemainingKey").orElseThrow() }
private val NSProgressEstimatedTimeRemainingKey_VH: VarHandle by lazy { NSProgressEstimatedTimeRemainingKey_LAYOUT.varHandle() }

var NSProgressEstimatedTimeRemainingKey: MemorySegment
    get() = NSProgressEstimatedTimeRemainingKey_VH.get(NSProgressEstimatedTimeRemainingKey_SEGMENT) as MemorySegment
    set(value) = NSProgressEstimatedTimeRemainingKey_VH.set(NSProgressEstimatedTimeRemainingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressThroughputKey typedef const NSProgressUserInfoKey = (Void)*
 */
private val NSProgressThroughputKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressThroughputKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressThroughputKey").orElseThrow() }
private val NSProgressThroughputKey_VH: VarHandle by lazy { NSProgressThroughputKey_LAYOUT.varHandle() }

var NSProgressThroughputKey: MemorySegment
    get() = NSProgressThroughputKey_VH.get(NSProgressThroughputKey_SEGMENT) as MemorySegment
    set(value) = NSProgressThroughputKey_VH.set(NSProgressThroughputKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressKindFile typedef const NSProgressKind = (Void)*
 */
private val NSProgressKindFile_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressKindFile_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressKindFile").orElseThrow() }
private val NSProgressKindFile_VH: VarHandle by lazy { NSProgressKindFile_LAYOUT.varHandle() }

var NSProgressKindFile: MemorySegment
    get() = NSProgressKindFile_VH.get(NSProgressKindFile_SEGMENT) as MemorySegment
    set(value) = NSProgressKindFile_VH.set(NSProgressKindFile_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileOperationKindKey typedef const NSProgressUserInfoKey = (Void)*
 */
private val NSProgressFileOperationKindKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileOperationKindKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileOperationKindKey").orElseThrow() }
private val NSProgressFileOperationKindKey_VH: VarHandle by lazy { NSProgressFileOperationKindKey_LAYOUT.varHandle() }

var NSProgressFileOperationKindKey: MemorySegment
    get() = NSProgressFileOperationKindKey_VH.get(NSProgressFileOperationKindKey_SEGMENT) as MemorySegment
    set(value) = NSProgressFileOperationKindKey_VH.set(NSProgressFileOperationKindKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileOperationKindDownloading typedef const NSProgressFileOperationKind = (Void)*
 */
private val NSProgressFileOperationKindDownloading_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileOperationKindDownloading_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileOperationKindDownloading").orElseThrow() }
private val NSProgressFileOperationKindDownloading_VH: VarHandle by lazy { NSProgressFileOperationKindDownloading_LAYOUT.varHandle() }

var NSProgressFileOperationKindDownloading: MemorySegment
    get() = NSProgressFileOperationKindDownloading_VH.get(NSProgressFileOperationKindDownloading_SEGMENT) as MemorySegment
    set(value) = NSProgressFileOperationKindDownloading_VH.set(NSProgressFileOperationKindDownloading_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileOperationKindDecompressingAfterDownloading typedef const NSProgressFileOperationKind = (Void)*
 */
private val NSProgressFileOperationKindDecompressingAfterDownloading_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileOperationKindDecompressingAfterDownloading_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileOperationKindDecompressingAfterDownloading").orElseThrow() }
private val NSProgressFileOperationKindDecompressingAfterDownloading_VH: VarHandle by lazy { NSProgressFileOperationKindDecompressingAfterDownloading_LAYOUT.varHandle() }

var NSProgressFileOperationKindDecompressingAfterDownloading: MemorySegment
    get() = NSProgressFileOperationKindDecompressingAfterDownloading_VH.get(NSProgressFileOperationKindDecompressingAfterDownloading_SEGMENT) as MemorySegment
    set(value) = NSProgressFileOperationKindDecompressingAfterDownloading_VH.set(NSProgressFileOperationKindDecompressingAfterDownloading_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileOperationKindReceiving typedef const NSProgressFileOperationKind = (Void)*
 */
private val NSProgressFileOperationKindReceiving_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileOperationKindReceiving_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileOperationKindReceiving").orElseThrow() }
private val NSProgressFileOperationKindReceiving_VH: VarHandle by lazy { NSProgressFileOperationKindReceiving_LAYOUT.varHandle() }

var NSProgressFileOperationKindReceiving: MemorySegment
    get() = NSProgressFileOperationKindReceiving_VH.get(NSProgressFileOperationKindReceiving_SEGMENT) as MemorySegment
    set(value) = NSProgressFileOperationKindReceiving_VH.set(NSProgressFileOperationKindReceiving_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileOperationKindCopying typedef const NSProgressFileOperationKind = (Void)*
 */
private val NSProgressFileOperationKindCopying_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileOperationKindCopying_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileOperationKindCopying").orElseThrow() }
private val NSProgressFileOperationKindCopying_VH: VarHandle by lazy { NSProgressFileOperationKindCopying_LAYOUT.varHandle() }

var NSProgressFileOperationKindCopying: MemorySegment
    get() = NSProgressFileOperationKindCopying_VH.get(NSProgressFileOperationKindCopying_SEGMENT) as MemorySegment
    set(value) = NSProgressFileOperationKindCopying_VH.set(NSProgressFileOperationKindCopying_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileOperationKindUploading typedef const NSProgressFileOperationKind = (Void)*
 */
private val NSProgressFileOperationKindUploading_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileOperationKindUploading_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileOperationKindUploading").orElseThrow() }
private val NSProgressFileOperationKindUploading_VH: VarHandle by lazy { NSProgressFileOperationKindUploading_LAYOUT.varHandle() }

var NSProgressFileOperationKindUploading: MemorySegment
    get() = NSProgressFileOperationKindUploading_VH.get(NSProgressFileOperationKindUploading_SEGMENT) as MemorySegment
    set(value) = NSProgressFileOperationKindUploading_VH.set(NSProgressFileOperationKindUploading_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileOperationKindDuplicating typedef const NSProgressFileOperationKind = (Void)*
 */
private val NSProgressFileOperationKindDuplicating_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileOperationKindDuplicating_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileOperationKindDuplicating").orElseThrow() }
private val NSProgressFileOperationKindDuplicating_VH: VarHandle by lazy { NSProgressFileOperationKindDuplicating_LAYOUT.varHandle() }

var NSProgressFileOperationKindDuplicating: MemorySegment
    get() = NSProgressFileOperationKindDuplicating_VH.get(NSProgressFileOperationKindDuplicating_SEGMENT) as MemorySegment
    set(value) = NSProgressFileOperationKindDuplicating_VH.set(NSProgressFileOperationKindDuplicating_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileURLKey typedef const NSProgressUserInfoKey = (Void)*
 */
private val NSProgressFileURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileURLKey").orElseThrow() }
private val NSProgressFileURLKey_VH: VarHandle by lazy { NSProgressFileURLKey_LAYOUT.varHandle() }

var NSProgressFileURLKey: MemorySegment
    get() = NSProgressFileURLKey_VH.get(NSProgressFileURLKey_SEGMENT) as MemorySegment
    set(value) = NSProgressFileURLKey_VH.set(NSProgressFileURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileTotalCountKey typedef const NSProgressUserInfoKey = (Void)*
 */
private val NSProgressFileTotalCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileTotalCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileTotalCountKey").orElseThrow() }
private val NSProgressFileTotalCountKey_VH: VarHandle by lazy { NSProgressFileTotalCountKey_LAYOUT.varHandle() }

var NSProgressFileTotalCountKey: MemorySegment
    get() = NSProgressFileTotalCountKey_VH.get(NSProgressFileTotalCountKey_SEGMENT) as MemorySegment
    set(value) = NSProgressFileTotalCountKey_VH.set(NSProgressFileTotalCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileCompletedCountKey typedef const NSProgressUserInfoKey = (Void)*
 */
private val NSProgressFileCompletedCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileCompletedCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileCompletedCountKey").orElseThrow() }
private val NSProgressFileCompletedCountKey_VH: VarHandle by lazy { NSProgressFileCompletedCountKey_LAYOUT.varHandle() }

var NSProgressFileCompletedCountKey: MemorySegment
    get() = NSProgressFileCompletedCountKey_VH.get(NSProgressFileCompletedCountKey_SEGMENT) as MemorySegment
    set(value) = NSProgressFileCompletedCountKey_VH.set(NSProgressFileCompletedCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileAnimationImageKey typedef const NSProgressUserInfoKey = (Void)*
 */
private val NSProgressFileAnimationImageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileAnimationImageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileAnimationImageKey").orElseThrow() }
private val NSProgressFileAnimationImageKey_VH: VarHandle by lazy { NSProgressFileAnimationImageKey_LAYOUT.varHandle() }

var NSProgressFileAnimationImageKey: MemorySegment
    get() = NSProgressFileAnimationImageKey_VH.get(NSProgressFileAnimationImageKey_SEGMENT) as MemorySegment
    set(value) = NSProgressFileAnimationImageKey_VH.set(NSProgressFileAnimationImageKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileAnimationImageOriginalRectKey typedef const NSProgressUserInfoKey = (Void)*
 */
private val NSProgressFileAnimationImageOriginalRectKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileAnimationImageOriginalRectKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileAnimationImageOriginalRectKey").orElseThrow() }
private val NSProgressFileAnimationImageOriginalRectKey_VH: VarHandle by lazy { NSProgressFileAnimationImageOriginalRectKey_LAYOUT.varHandle() }

var NSProgressFileAnimationImageOriginalRectKey: MemorySegment
    get() = NSProgressFileAnimationImageOriginalRectKey_VH.get(NSProgressFileAnimationImageOriginalRectKey_SEGMENT) as MemorySegment
    set(value) = NSProgressFileAnimationImageOriginalRectKey_VH.set(NSProgressFileAnimationImageOriginalRectKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSProgressFileIconKey typedef const NSProgressUserInfoKey = (Void)*
 */
private val NSProgressFileIconKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProgressFileIconKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProgressFileIconKey").orElseThrow() }
private val NSProgressFileIconKey_VH: VarHandle by lazy { NSProgressFileIconKey_LAYOUT.varHandle() }

var NSProgressFileIconKey: MemorySegment
    get() = NSProgressFileIconKey_VH.get(NSProgressFileIconKey_SEGMENT) as MemorySegment
    set(value) = NSProgressFileIconKey_VH.set(NSProgressFileIconKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSBundleDidLoadNotification typedef const NSNotificationName = (Void)*
 */
private val NSBundleDidLoadNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBundleDidLoadNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBundleDidLoadNotification").orElseThrow() }
private val NSBundleDidLoadNotification_VH: VarHandle by lazy { NSBundleDidLoadNotification_LAYOUT.varHandle() }

var NSBundleDidLoadNotification: MemorySegment
    get() = NSBundleDidLoadNotification_VH.get(NSBundleDidLoadNotification_SEGMENT) as MemorySegment
    set(value) = NSBundleDidLoadNotification_VH.set(NSBundleDidLoadNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSLoadedClasses (Void)*
 */
private val NSLoadedClasses_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLoadedClasses_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLoadedClasses").orElseThrow() }
private val NSLoadedClasses_VH: VarHandle by lazy { NSLoadedClasses_LAYOUT.varHandle() }

var NSLoadedClasses: MemorySegment
    get() = NSLoadedClasses_VH.get(NSLoadedClasses_SEGMENT) as MemorySegment
    set(value) = NSLoadedClasses_VH.set(NSLoadedClasses_SEGMENT, value)

/**
 * {@snippet lang=c : NSBundleResourceRequestLowDiskSpaceNotification typedef const NSNotificationName = (Void)*
 */
private val NSBundleResourceRequestLowDiskSpaceNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBundleResourceRequestLowDiskSpaceNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBundleResourceRequestLowDiskSpaceNotification").orElseThrow() }
private val NSBundleResourceRequestLowDiskSpaceNotification_VH: VarHandle by lazy { NSBundleResourceRequestLowDiskSpaceNotification_LAYOUT.varHandle() }

var NSBundleResourceRequestLowDiskSpaceNotification: MemorySegment
    get() = NSBundleResourceRequestLowDiskSpaceNotification_VH.get(NSBundleResourceRequestLowDiskSpaceNotification_SEGMENT) as MemorySegment
    set(value) = NSBundleResourceRequestLowDiskSpaceNotification_VH.set(NSBundleResourceRequestLowDiskSpaceNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSBundleResourceRequestLoadingPriorityUrgent Double
 */
private val NSBundleResourceRequestLoadingPriorityUrgent_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSBundleResourceRequestLoadingPriorityUrgent_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBundleResourceRequestLoadingPriorityUrgent").orElseThrow() }
private val NSBundleResourceRequestLoadingPriorityUrgent_VH: VarHandle by lazy { NSBundleResourceRequestLoadingPriorityUrgent_LAYOUT.varHandle() }

var NSBundleResourceRequestLoadingPriorityUrgent: Double
    get() = NSBundleResourceRequestLoadingPriorityUrgent_VH.get(NSBundleResourceRequestLoadingPriorityUrgent_SEGMENT) as Double
    set(value) = NSBundleResourceRequestLoadingPriorityUrgent_VH.set(NSBundleResourceRequestLoadingPriorityUrgent_SEGMENT, value)

/**
 * {@snippet lang=c : NSSystemClockDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSSystemClockDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSystemClockDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSystemClockDidChangeNotification").orElseThrow() }
private val NSSystemClockDidChangeNotification_VH: VarHandle by lazy { NSSystemClockDidChangeNotification_LAYOUT.varHandle() }

var NSSystemClockDidChangeNotification: MemorySegment
    get() = NSSystemClockDidChangeNotification_VH.get(NSSystemClockDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSSystemClockDidChangeNotification_VH.set(NSSystemClockDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierGregorian typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierGregorian_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierGregorian_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierGregorian").orElseThrow() }
private val NSCalendarIdentifierGregorian_VH: VarHandle by lazy { NSCalendarIdentifierGregorian_LAYOUT.varHandle() }

var NSCalendarIdentifierGregorian: MemorySegment
    get() = NSCalendarIdentifierGregorian_VH.get(NSCalendarIdentifierGregorian_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierGregorian_VH.set(NSCalendarIdentifierGregorian_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierBuddhist typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierBuddhist_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierBuddhist_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierBuddhist").orElseThrow() }
private val NSCalendarIdentifierBuddhist_VH: VarHandle by lazy { NSCalendarIdentifierBuddhist_LAYOUT.varHandle() }

var NSCalendarIdentifierBuddhist: MemorySegment
    get() = NSCalendarIdentifierBuddhist_VH.get(NSCalendarIdentifierBuddhist_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierBuddhist_VH.set(NSCalendarIdentifierBuddhist_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierChinese typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierChinese_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierChinese_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierChinese").orElseThrow() }
private val NSCalendarIdentifierChinese_VH: VarHandle by lazy { NSCalendarIdentifierChinese_LAYOUT.varHandle() }

var NSCalendarIdentifierChinese: MemorySegment
    get() = NSCalendarIdentifierChinese_VH.get(NSCalendarIdentifierChinese_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierChinese_VH.set(NSCalendarIdentifierChinese_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierCoptic typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierCoptic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierCoptic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierCoptic").orElseThrow() }
private val NSCalendarIdentifierCoptic_VH: VarHandle by lazy { NSCalendarIdentifierCoptic_LAYOUT.varHandle() }

var NSCalendarIdentifierCoptic: MemorySegment
    get() = NSCalendarIdentifierCoptic_VH.get(NSCalendarIdentifierCoptic_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierCoptic_VH.set(NSCalendarIdentifierCoptic_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierEthiopicAmeteMihret typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierEthiopicAmeteMihret_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierEthiopicAmeteMihret_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierEthiopicAmeteMihret").orElseThrow() }
private val NSCalendarIdentifierEthiopicAmeteMihret_VH: VarHandle by lazy { NSCalendarIdentifierEthiopicAmeteMihret_LAYOUT.varHandle() }

var NSCalendarIdentifierEthiopicAmeteMihret: MemorySegment
    get() = NSCalendarIdentifierEthiopicAmeteMihret_VH.get(NSCalendarIdentifierEthiopicAmeteMihret_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierEthiopicAmeteMihret_VH.set(NSCalendarIdentifierEthiopicAmeteMihret_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierEthiopicAmeteAlem typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierEthiopicAmeteAlem_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierEthiopicAmeteAlem_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierEthiopicAmeteAlem").orElseThrow() }
private val NSCalendarIdentifierEthiopicAmeteAlem_VH: VarHandle by lazy { NSCalendarIdentifierEthiopicAmeteAlem_LAYOUT.varHandle() }

var NSCalendarIdentifierEthiopicAmeteAlem: MemorySegment
    get() = NSCalendarIdentifierEthiopicAmeteAlem_VH.get(NSCalendarIdentifierEthiopicAmeteAlem_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierEthiopicAmeteAlem_VH.set(NSCalendarIdentifierEthiopicAmeteAlem_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierHebrew typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierHebrew_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierHebrew_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierHebrew").orElseThrow() }
private val NSCalendarIdentifierHebrew_VH: VarHandle by lazy { NSCalendarIdentifierHebrew_LAYOUT.varHandle() }

var NSCalendarIdentifierHebrew: MemorySegment
    get() = NSCalendarIdentifierHebrew_VH.get(NSCalendarIdentifierHebrew_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierHebrew_VH.set(NSCalendarIdentifierHebrew_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierISO8601 typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierISO8601_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierISO8601_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierISO8601").orElseThrow() }
private val NSCalendarIdentifierISO8601_VH: VarHandle by lazy { NSCalendarIdentifierISO8601_LAYOUT.varHandle() }

var NSCalendarIdentifierISO8601: MemorySegment
    get() = NSCalendarIdentifierISO8601_VH.get(NSCalendarIdentifierISO8601_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierISO8601_VH.set(NSCalendarIdentifierISO8601_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierIndian typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierIndian_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierIndian_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierIndian").orElseThrow() }
private val NSCalendarIdentifierIndian_VH: VarHandle by lazy { NSCalendarIdentifierIndian_LAYOUT.varHandle() }

var NSCalendarIdentifierIndian: MemorySegment
    get() = NSCalendarIdentifierIndian_VH.get(NSCalendarIdentifierIndian_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierIndian_VH.set(NSCalendarIdentifierIndian_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierIslamic typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierIslamic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierIslamic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierIslamic").orElseThrow() }
private val NSCalendarIdentifierIslamic_VH: VarHandle by lazy { NSCalendarIdentifierIslamic_LAYOUT.varHandle() }

var NSCalendarIdentifierIslamic: MemorySegment
    get() = NSCalendarIdentifierIslamic_VH.get(NSCalendarIdentifierIslamic_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierIslamic_VH.set(NSCalendarIdentifierIslamic_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierIslamicCivil typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierIslamicCivil_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierIslamicCivil_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierIslamicCivil").orElseThrow() }
private val NSCalendarIdentifierIslamicCivil_VH: VarHandle by lazy { NSCalendarIdentifierIslamicCivil_LAYOUT.varHandle() }

var NSCalendarIdentifierIslamicCivil: MemorySegment
    get() = NSCalendarIdentifierIslamicCivil_VH.get(NSCalendarIdentifierIslamicCivil_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierIslamicCivil_VH.set(NSCalendarIdentifierIslamicCivil_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierJapanese typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierJapanese_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierJapanese_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierJapanese").orElseThrow() }
private val NSCalendarIdentifierJapanese_VH: VarHandle by lazy { NSCalendarIdentifierJapanese_LAYOUT.varHandle() }

var NSCalendarIdentifierJapanese: MemorySegment
    get() = NSCalendarIdentifierJapanese_VH.get(NSCalendarIdentifierJapanese_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierJapanese_VH.set(NSCalendarIdentifierJapanese_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierPersian typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierPersian_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierPersian_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierPersian").orElseThrow() }
private val NSCalendarIdentifierPersian_VH: VarHandle by lazy { NSCalendarIdentifierPersian_LAYOUT.varHandle() }

var NSCalendarIdentifierPersian: MemorySegment
    get() = NSCalendarIdentifierPersian_VH.get(NSCalendarIdentifierPersian_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierPersian_VH.set(NSCalendarIdentifierPersian_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierRepublicOfChina typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierRepublicOfChina_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierRepublicOfChina_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierRepublicOfChina").orElseThrow() }
private val NSCalendarIdentifierRepublicOfChina_VH: VarHandle by lazy { NSCalendarIdentifierRepublicOfChina_LAYOUT.varHandle() }

var NSCalendarIdentifierRepublicOfChina: MemorySegment
    get() = NSCalendarIdentifierRepublicOfChina_VH.get(NSCalendarIdentifierRepublicOfChina_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierRepublicOfChina_VH.set(NSCalendarIdentifierRepublicOfChina_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierIslamicTabular typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierIslamicTabular_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierIslamicTabular_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierIslamicTabular").orElseThrow() }
private val NSCalendarIdentifierIslamicTabular_VH: VarHandle by lazy { NSCalendarIdentifierIslamicTabular_LAYOUT.varHandle() }

var NSCalendarIdentifierIslamicTabular: MemorySegment
    get() = NSCalendarIdentifierIslamicTabular_VH.get(NSCalendarIdentifierIslamicTabular_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierIslamicTabular_VH.set(NSCalendarIdentifierIslamicTabular_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierIslamicUmmAlQura typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierIslamicUmmAlQura_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierIslamicUmmAlQura_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierIslamicUmmAlQura").orElseThrow() }
private val NSCalendarIdentifierIslamicUmmAlQura_VH: VarHandle by lazy { NSCalendarIdentifierIslamicUmmAlQura_LAYOUT.varHandle() }

var NSCalendarIdentifierIslamicUmmAlQura: MemorySegment
    get() = NSCalendarIdentifierIslamicUmmAlQura_VH.get(NSCalendarIdentifierIslamicUmmAlQura_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierIslamicUmmAlQura_VH.set(NSCalendarIdentifierIslamicUmmAlQura_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierBangla typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierBangla_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierBangla_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierBangla").orElseThrow() }
private val NSCalendarIdentifierBangla_VH: VarHandle by lazy { NSCalendarIdentifierBangla_LAYOUT.varHandle() }

var NSCalendarIdentifierBangla: MemorySegment
    get() = NSCalendarIdentifierBangla_VH.get(NSCalendarIdentifierBangla_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierBangla_VH.set(NSCalendarIdentifierBangla_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierGujarati typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierGujarati_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierGujarati_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierGujarati").orElseThrow() }
private val NSCalendarIdentifierGujarati_VH: VarHandle by lazy { NSCalendarIdentifierGujarati_LAYOUT.varHandle() }

var NSCalendarIdentifierGujarati: MemorySegment
    get() = NSCalendarIdentifierGujarati_VH.get(NSCalendarIdentifierGujarati_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierGujarati_VH.set(NSCalendarIdentifierGujarati_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierKannada typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierKannada_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierKannada_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierKannada").orElseThrow() }
private val NSCalendarIdentifierKannada_VH: VarHandle by lazy { NSCalendarIdentifierKannada_LAYOUT.varHandle() }

var NSCalendarIdentifierKannada: MemorySegment
    get() = NSCalendarIdentifierKannada_VH.get(NSCalendarIdentifierKannada_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierKannada_VH.set(NSCalendarIdentifierKannada_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierMalayalam typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierMalayalam_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierMalayalam_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierMalayalam").orElseThrow() }
private val NSCalendarIdentifierMalayalam_VH: VarHandle by lazy { NSCalendarIdentifierMalayalam_LAYOUT.varHandle() }

var NSCalendarIdentifierMalayalam: MemorySegment
    get() = NSCalendarIdentifierMalayalam_VH.get(NSCalendarIdentifierMalayalam_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierMalayalam_VH.set(NSCalendarIdentifierMalayalam_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierMarathi typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierMarathi_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierMarathi_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierMarathi").orElseThrow() }
private val NSCalendarIdentifierMarathi_VH: VarHandle by lazy { NSCalendarIdentifierMarathi_LAYOUT.varHandle() }

var NSCalendarIdentifierMarathi: MemorySegment
    get() = NSCalendarIdentifierMarathi_VH.get(NSCalendarIdentifierMarathi_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierMarathi_VH.set(NSCalendarIdentifierMarathi_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierOdia typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierOdia_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierOdia_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierOdia").orElseThrow() }
private val NSCalendarIdentifierOdia_VH: VarHandle by lazy { NSCalendarIdentifierOdia_LAYOUT.varHandle() }

var NSCalendarIdentifierOdia: MemorySegment
    get() = NSCalendarIdentifierOdia_VH.get(NSCalendarIdentifierOdia_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierOdia_VH.set(NSCalendarIdentifierOdia_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierTamil typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierTamil_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierTamil_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierTamil").orElseThrow() }
private val NSCalendarIdentifierTamil_VH: VarHandle by lazy { NSCalendarIdentifierTamil_LAYOUT.varHandle() }

var NSCalendarIdentifierTamil: MemorySegment
    get() = NSCalendarIdentifierTamil_VH.get(NSCalendarIdentifierTamil_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierTamil_VH.set(NSCalendarIdentifierTamil_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierTelugu typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierTelugu_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierTelugu_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierTelugu").orElseThrow() }
private val NSCalendarIdentifierTelugu_VH: VarHandle by lazy { NSCalendarIdentifierTelugu_LAYOUT.varHandle() }

var NSCalendarIdentifierTelugu: MemorySegment
    get() = NSCalendarIdentifierTelugu_VH.get(NSCalendarIdentifierTelugu_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierTelugu_VH.set(NSCalendarIdentifierTelugu_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierVikram typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierVikram_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierVikram_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierVikram").orElseThrow() }
private val NSCalendarIdentifierVikram_VH: VarHandle by lazy { NSCalendarIdentifierVikram_LAYOUT.varHandle() }

var NSCalendarIdentifierVikram: MemorySegment
    get() = NSCalendarIdentifierVikram_VH.get(NSCalendarIdentifierVikram_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierVikram_VH.set(NSCalendarIdentifierVikram_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierDangi typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierDangi_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierDangi_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierDangi").orElseThrow() }
private val NSCalendarIdentifierDangi_VH: VarHandle by lazy { NSCalendarIdentifierDangi_LAYOUT.varHandle() }

var NSCalendarIdentifierDangi: MemorySegment
    get() = NSCalendarIdentifierDangi_VH.get(NSCalendarIdentifierDangi_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierDangi_VH.set(NSCalendarIdentifierDangi_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarIdentifierVietnamese typedef const NSCalendarIdentifier = (Void)*
 */
private val NSCalendarIdentifierVietnamese_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarIdentifierVietnamese_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarIdentifierVietnamese").orElseThrow() }
private val NSCalendarIdentifierVietnamese_VH: VarHandle by lazy { NSCalendarIdentifierVietnamese_LAYOUT.varHandle() }

var NSCalendarIdentifierVietnamese: MemorySegment
    get() = NSCalendarIdentifierVietnamese_VH.get(NSCalendarIdentifierVietnamese_SEGMENT) as MemorySegment
    set(value) = NSCalendarIdentifierVietnamese_VH.set(NSCalendarIdentifierVietnamese_SEGMENT, value)

/**
 * {@snippet lang=c : NSCalendarDayChangedNotification typedef const NSNotificationName = (Void)*
 */
private val NSCalendarDayChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCalendarDayChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCalendarDayChangedNotification").orElseThrow() }
private val NSCalendarDayChangedNotification_VH: VarHandle by lazy { NSCalendarDayChangedNotification_LAYOUT.varHandle() }

var NSCalendarDayChangedNotification: MemorySegment
    get() = NSCalendarDayChangedNotification_VH.get(NSCalendarDayChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSCalendarDayChangedNotification_VH.set(NSCalendarDayChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NXReadNSObjectFromCoder typedef NSObject = (Void)*(typedef NSCoder = (Void)*)
 */
private val NXReadNSObjectFromCoder_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NXReadNSObjectFromCoder_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NXReadNSObjectFromCoder").orElseThrow()
private val NXReadNSObjectFromCoder_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NXReadNSObjectFromCoder_ADDR, NXReadNSObjectFromCoder_DESC)

fun NXReadNSObjectFromCoder(arg0: MemorySegment): MemorySegment {
    try {
        return NXReadNSObjectFromCoder_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSInflectionConceptsKey typedef const NSAttributedStringFormattingContextKey = (Void)*
 */
private val NSInflectionConceptsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInflectionConceptsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInflectionConceptsKey").orElseThrow() }
private val NSInflectionConceptsKey_VH: VarHandle by lazy { NSInflectionConceptsKey_LAYOUT.varHandle() }

var NSInflectionConceptsKey: MemorySegment
    get() = NSInflectionConceptsKey_VH.get(NSInflectionConceptsKey_SEGMENT) as MemorySegment
    set(value) = NSInflectionConceptsKey_VH.set(NSInflectionConceptsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSInlinePresentationIntentAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSInlinePresentationIntentAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInlinePresentationIntentAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInlinePresentationIntentAttributeName").orElseThrow() }
private val NSInlinePresentationIntentAttributeName_VH: VarHandle by lazy { NSInlinePresentationIntentAttributeName_LAYOUT.varHandle() }

var NSInlinePresentationIntentAttributeName: MemorySegment
    get() = NSInlinePresentationIntentAttributeName_VH.get(NSInlinePresentationIntentAttributeName_SEGMENT) as MemorySegment
    set(value) = NSInlinePresentationIntentAttributeName_VH.set(NSInlinePresentationIntentAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSAlternateDescriptionAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSAlternateDescriptionAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAlternateDescriptionAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAlternateDescriptionAttributeName").orElseThrow() }
private val NSAlternateDescriptionAttributeName_VH: VarHandle by lazy { NSAlternateDescriptionAttributeName_LAYOUT.varHandle() }

var NSAlternateDescriptionAttributeName: MemorySegment
    get() = NSAlternateDescriptionAttributeName_VH.get(NSAlternateDescriptionAttributeName_SEGMENT) as MemorySegment
    set(value) = NSAlternateDescriptionAttributeName_VH.set(NSAlternateDescriptionAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageURLAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSImageURLAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageURLAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageURLAttributeName").orElseThrow() }
private val NSImageURLAttributeName_VH: VarHandle by lazy { NSImageURLAttributeName_LAYOUT.varHandle() }

var NSImageURLAttributeName: MemorySegment
    get() = NSImageURLAttributeName_VH.get(NSImageURLAttributeName_SEGMENT) as MemorySegment
    set(value) = NSImageURLAttributeName_VH.set(NSImageURLAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSLanguageIdentifierAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSLanguageIdentifierAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLanguageIdentifierAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLanguageIdentifierAttributeName").orElseThrow() }
private val NSLanguageIdentifierAttributeName_VH: VarHandle by lazy { NSLanguageIdentifierAttributeName_LAYOUT.varHandle() }

var NSLanguageIdentifierAttributeName: MemorySegment
    get() = NSLanguageIdentifierAttributeName_VH.get(NSLanguageIdentifierAttributeName_SEGMENT) as MemorySegment
    set(value) = NSLanguageIdentifierAttributeName_VH.set(NSLanguageIdentifierAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSMarkdownSourcePositionAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSMarkdownSourcePositionAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMarkdownSourcePositionAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMarkdownSourcePositionAttributeName").orElseThrow() }
private val NSMarkdownSourcePositionAttributeName_VH: VarHandle by lazy { NSMarkdownSourcePositionAttributeName_LAYOUT.varHandle() }

var NSMarkdownSourcePositionAttributeName: MemorySegment
    get() = NSMarkdownSourcePositionAttributeName_VH.get(NSMarkdownSourcePositionAttributeName_SEGMENT) as MemorySegment
    set(value) = NSMarkdownSourcePositionAttributeName_VH.set(NSMarkdownSourcePositionAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSReplacementIndexAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSReplacementIndexAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSReplacementIndexAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSReplacementIndexAttributeName").orElseThrow() }
private val NSReplacementIndexAttributeName_VH: VarHandle by lazy { NSReplacementIndexAttributeName_LAYOUT.varHandle() }

var NSReplacementIndexAttributeName: MemorySegment
    get() = NSReplacementIndexAttributeName_VH.get(NSReplacementIndexAttributeName_SEGMENT) as MemorySegment
    set(value) = NSReplacementIndexAttributeName_VH.set(NSReplacementIndexAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSMorphologyAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSMorphologyAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMorphologyAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMorphologyAttributeName").orElseThrow() }
private val NSMorphologyAttributeName_VH: VarHandle by lazy { NSMorphologyAttributeName_LAYOUT.varHandle() }

var NSMorphologyAttributeName: MemorySegment
    get() = NSMorphologyAttributeName_VH.get(NSMorphologyAttributeName_SEGMENT) as MemorySegment
    set(value) = NSMorphologyAttributeName_VH.set(NSMorphologyAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSInflectionRuleAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSInflectionRuleAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInflectionRuleAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInflectionRuleAttributeName").orElseThrow() }
private val NSInflectionRuleAttributeName_VH: VarHandle by lazy { NSInflectionRuleAttributeName_LAYOUT.varHandle() }

var NSInflectionRuleAttributeName: MemorySegment
    get() = NSInflectionRuleAttributeName_VH.get(NSInflectionRuleAttributeName_SEGMENT) as MemorySegment
    set(value) = NSInflectionRuleAttributeName_VH.set(NSInflectionRuleAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSInflectionAgreementArgumentAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSInflectionAgreementArgumentAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInflectionAgreementArgumentAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInflectionAgreementArgumentAttributeName").orElseThrow() }
private val NSInflectionAgreementArgumentAttributeName_VH: VarHandle by lazy { NSInflectionAgreementArgumentAttributeName_LAYOUT.varHandle() }

var NSInflectionAgreementArgumentAttributeName: MemorySegment
    get() = NSInflectionAgreementArgumentAttributeName_VH.get(NSInflectionAgreementArgumentAttributeName_SEGMENT) as MemorySegment
    set(value) = NSInflectionAgreementArgumentAttributeName_VH.set(NSInflectionAgreementArgumentAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSInflectionAgreementConceptAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSInflectionAgreementConceptAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInflectionAgreementConceptAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInflectionAgreementConceptAttributeName").orElseThrow() }
private val NSInflectionAgreementConceptAttributeName_VH: VarHandle by lazy { NSInflectionAgreementConceptAttributeName_LAYOUT.varHandle() }

var NSInflectionAgreementConceptAttributeName: MemorySegment
    get() = NSInflectionAgreementConceptAttributeName_VH.get(NSInflectionAgreementConceptAttributeName_SEGMENT) as MemorySegment
    set(value) = NSInflectionAgreementConceptAttributeName_VH.set(NSInflectionAgreementConceptAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSInflectionReferentConceptAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSInflectionReferentConceptAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInflectionReferentConceptAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInflectionReferentConceptAttributeName").orElseThrow() }
private val NSInflectionReferentConceptAttributeName_VH: VarHandle by lazy { NSInflectionReferentConceptAttributeName_LAYOUT.varHandle() }

var NSInflectionReferentConceptAttributeName: MemorySegment
    get() = NSInflectionReferentConceptAttributeName_VH.get(NSInflectionReferentConceptAttributeName_SEGMENT) as MemorySegment
    set(value) = NSInflectionReferentConceptAttributeName_VH.set(NSInflectionReferentConceptAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSInflectionAlternativeAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSInflectionAlternativeAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInflectionAlternativeAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInflectionAlternativeAttributeName").orElseThrow() }
private val NSInflectionAlternativeAttributeName_VH: VarHandle by lazy { NSInflectionAlternativeAttributeName_LAYOUT.varHandle() }

var NSInflectionAlternativeAttributeName: MemorySegment
    get() = NSInflectionAlternativeAttributeName_VH.get(NSInflectionAlternativeAttributeName_SEGMENT) as MemorySegment
    set(value) = NSInflectionAlternativeAttributeName_VH.set(NSInflectionAlternativeAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocalizedNumberFormatAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSLocalizedNumberFormatAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocalizedNumberFormatAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocalizedNumberFormatAttributeName").orElseThrow() }
private val NSLocalizedNumberFormatAttributeName_VH: VarHandle by lazy { NSLocalizedNumberFormatAttributeName_LAYOUT.varHandle() }

var NSLocalizedNumberFormatAttributeName: MemorySegment
    get() = NSLocalizedNumberFormatAttributeName_VH.get(NSLocalizedNumberFormatAttributeName_SEGMENT) as MemorySegment
    set(value) = NSLocalizedNumberFormatAttributeName_VH.set(NSLocalizedNumberFormatAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSListItemDelimiterAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSListItemDelimiterAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSListItemDelimiterAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSListItemDelimiterAttributeName").orElseThrow() }
private val NSListItemDelimiterAttributeName_VH: VarHandle by lazy { NSListItemDelimiterAttributeName_LAYOUT.varHandle() }

var NSListItemDelimiterAttributeName: MemorySegment
    get() = NSListItemDelimiterAttributeName_VH.get(NSListItemDelimiterAttributeName_SEGMENT) as MemorySegment
    set(value) = NSListItemDelimiterAttributeName_VH.set(NSListItemDelimiterAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSPresentationIntentAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSPresentationIntentAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPresentationIntentAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPresentationIntentAttributeName").orElseThrow() }
private val NSPresentationIntentAttributeName_VH: VarHandle by lazy { NSPresentationIntentAttributeName_LAYOUT.varHandle() }

var NSPresentationIntentAttributeName: MemorySegment
    get() = NSPresentationIntentAttributeName_VH.get(NSPresentationIntentAttributeName_SEGMENT) as MemorySegment
    set(value) = NSPresentationIntentAttributeName_VH.set(NSPresentationIntentAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSCurrentLocaleDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSCurrentLocaleDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCurrentLocaleDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCurrentLocaleDidChangeNotification").orElseThrow() }
private val NSCurrentLocaleDidChangeNotification_VH: VarHandle by lazy { NSCurrentLocaleDidChangeNotification_LAYOUT.varHandle() }

var NSCurrentLocaleDidChangeNotification: MemorySegment
    get() = NSCurrentLocaleDidChangeNotification_VH.get(NSCurrentLocaleDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSCurrentLocaleDidChangeNotification_VH.set(NSCurrentLocaleDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleIdentifier typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleIdentifier").orElseThrow() }
private val NSLocaleIdentifier_VH: VarHandle by lazy { NSLocaleIdentifier_LAYOUT.varHandle() }

var NSLocaleIdentifier: MemorySegment
    get() = NSLocaleIdentifier_VH.get(NSLocaleIdentifier_SEGMENT) as MemorySegment
    set(value) = NSLocaleIdentifier_VH.set(NSLocaleIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleLanguageCode typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleLanguageCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleLanguageCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleLanguageCode").orElseThrow() }
private val NSLocaleLanguageCode_VH: VarHandle by lazy { NSLocaleLanguageCode_LAYOUT.varHandle() }

var NSLocaleLanguageCode: MemorySegment
    get() = NSLocaleLanguageCode_VH.get(NSLocaleLanguageCode_SEGMENT) as MemorySegment
    set(value) = NSLocaleLanguageCode_VH.set(NSLocaleLanguageCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleCountryCode typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleCountryCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleCountryCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleCountryCode").orElseThrow() }
private val NSLocaleCountryCode_VH: VarHandle by lazy { NSLocaleCountryCode_LAYOUT.varHandle() }

var NSLocaleCountryCode: MemorySegment
    get() = NSLocaleCountryCode_VH.get(NSLocaleCountryCode_SEGMENT) as MemorySegment
    set(value) = NSLocaleCountryCode_VH.set(NSLocaleCountryCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleScriptCode typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleScriptCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleScriptCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleScriptCode").orElseThrow() }
private val NSLocaleScriptCode_VH: VarHandle by lazy { NSLocaleScriptCode_LAYOUT.varHandle() }

var NSLocaleScriptCode: MemorySegment
    get() = NSLocaleScriptCode_VH.get(NSLocaleScriptCode_SEGMENT) as MemorySegment
    set(value) = NSLocaleScriptCode_VH.set(NSLocaleScriptCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleVariantCode typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleVariantCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleVariantCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleVariantCode").orElseThrow() }
private val NSLocaleVariantCode_VH: VarHandle by lazy { NSLocaleVariantCode_LAYOUT.varHandle() }

var NSLocaleVariantCode: MemorySegment
    get() = NSLocaleVariantCode_VH.get(NSLocaleVariantCode_SEGMENT) as MemorySegment
    set(value) = NSLocaleVariantCode_VH.set(NSLocaleVariantCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleExemplarCharacterSet typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleExemplarCharacterSet_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleExemplarCharacterSet_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleExemplarCharacterSet").orElseThrow() }
private val NSLocaleExemplarCharacterSet_VH: VarHandle by lazy { NSLocaleExemplarCharacterSet_LAYOUT.varHandle() }

var NSLocaleExemplarCharacterSet: MemorySegment
    get() = NSLocaleExemplarCharacterSet_VH.get(NSLocaleExemplarCharacterSet_SEGMENT) as MemorySegment
    set(value) = NSLocaleExemplarCharacterSet_VH.set(NSLocaleExemplarCharacterSet_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleCalendar typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleCalendar").orElseThrow() }
private val NSLocaleCalendar_VH: VarHandle by lazy { NSLocaleCalendar_LAYOUT.varHandle() }

var NSLocaleCalendar: MemorySegment
    get() = NSLocaleCalendar_VH.get(NSLocaleCalendar_SEGMENT) as MemorySegment
    set(value) = NSLocaleCalendar_VH.set(NSLocaleCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleCollationIdentifier typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleCollationIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleCollationIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleCollationIdentifier").orElseThrow() }
private val NSLocaleCollationIdentifier_VH: VarHandle by lazy { NSLocaleCollationIdentifier_LAYOUT.varHandle() }

var NSLocaleCollationIdentifier: MemorySegment
    get() = NSLocaleCollationIdentifier_VH.get(NSLocaleCollationIdentifier_SEGMENT) as MemorySegment
    set(value) = NSLocaleCollationIdentifier_VH.set(NSLocaleCollationIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleUsesMetricSystem typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleUsesMetricSystem_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleUsesMetricSystem_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleUsesMetricSystem").orElseThrow() }
private val NSLocaleUsesMetricSystem_VH: VarHandle by lazy { NSLocaleUsesMetricSystem_LAYOUT.varHandle() }

var NSLocaleUsesMetricSystem: MemorySegment
    get() = NSLocaleUsesMetricSystem_VH.get(NSLocaleUsesMetricSystem_SEGMENT) as MemorySegment
    set(value) = NSLocaleUsesMetricSystem_VH.set(NSLocaleUsesMetricSystem_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleMeasurementSystem typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleMeasurementSystem_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleMeasurementSystem_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleMeasurementSystem").orElseThrow() }
private val NSLocaleMeasurementSystem_VH: VarHandle by lazy { NSLocaleMeasurementSystem_LAYOUT.varHandle() }

var NSLocaleMeasurementSystem: MemorySegment
    get() = NSLocaleMeasurementSystem_VH.get(NSLocaleMeasurementSystem_SEGMENT) as MemorySegment
    set(value) = NSLocaleMeasurementSystem_VH.set(NSLocaleMeasurementSystem_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleDecimalSeparator typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleDecimalSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleDecimalSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleDecimalSeparator").orElseThrow() }
private val NSLocaleDecimalSeparator_VH: VarHandle by lazy { NSLocaleDecimalSeparator_LAYOUT.varHandle() }

var NSLocaleDecimalSeparator: MemorySegment
    get() = NSLocaleDecimalSeparator_VH.get(NSLocaleDecimalSeparator_SEGMENT) as MemorySegment
    set(value) = NSLocaleDecimalSeparator_VH.set(NSLocaleDecimalSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleGroupingSeparator typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleGroupingSeparator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleGroupingSeparator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleGroupingSeparator").orElseThrow() }
private val NSLocaleGroupingSeparator_VH: VarHandle by lazy { NSLocaleGroupingSeparator_LAYOUT.varHandle() }

var NSLocaleGroupingSeparator: MemorySegment
    get() = NSLocaleGroupingSeparator_VH.get(NSLocaleGroupingSeparator_SEGMENT) as MemorySegment
    set(value) = NSLocaleGroupingSeparator_VH.set(NSLocaleGroupingSeparator_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleCurrencySymbol typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleCurrencySymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleCurrencySymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleCurrencySymbol").orElseThrow() }
private val NSLocaleCurrencySymbol_VH: VarHandle by lazy { NSLocaleCurrencySymbol_LAYOUT.varHandle() }

var NSLocaleCurrencySymbol: MemorySegment
    get() = NSLocaleCurrencySymbol_VH.get(NSLocaleCurrencySymbol_SEGMENT) as MemorySegment
    set(value) = NSLocaleCurrencySymbol_VH.set(NSLocaleCurrencySymbol_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleCurrencyCode typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleCurrencyCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleCurrencyCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleCurrencyCode").orElseThrow() }
private val NSLocaleCurrencyCode_VH: VarHandle by lazy { NSLocaleCurrencyCode_LAYOUT.varHandle() }

var NSLocaleCurrencyCode: MemorySegment
    get() = NSLocaleCurrencyCode_VH.get(NSLocaleCurrencyCode_SEGMENT) as MemorySegment
    set(value) = NSLocaleCurrencyCode_VH.set(NSLocaleCurrencyCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleCollatorIdentifier typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleCollatorIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleCollatorIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleCollatorIdentifier").orElseThrow() }
private val NSLocaleCollatorIdentifier_VH: VarHandle by lazy { NSLocaleCollatorIdentifier_LAYOUT.varHandle() }

var NSLocaleCollatorIdentifier: MemorySegment
    get() = NSLocaleCollatorIdentifier_VH.get(NSLocaleCollatorIdentifier_SEGMENT) as MemorySegment
    set(value) = NSLocaleCollatorIdentifier_VH.set(NSLocaleCollatorIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleQuotationBeginDelimiterKey typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleQuotationBeginDelimiterKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleQuotationBeginDelimiterKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleQuotationBeginDelimiterKey").orElseThrow() }
private val NSLocaleQuotationBeginDelimiterKey_VH: VarHandle by lazy { NSLocaleQuotationBeginDelimiterKey_LAYOUT.varHandle() }

var NSLocaleQuotationBeginDelimiterKey: MemorySegment
    get() = NSLocaleQuotationBeginDelimiterKey_VH.get(NSLocaleQuotationBeginDelimiterKey_SEGMENT) as MemorySegment
    set(value) = NSLocaleQuotationBeginDelimiterKey_VH.set(NSLocaleQuotationBeginDelimiterKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleQuotationEndDelimiterKey typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleQuotationEndDelimiterKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleQuotationEndDelimiterKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleQuotationEndDelimiterKey").orElseThrow() }
private val NSLocaleQuotationEndDelimiterKey_VH: VarHandle by lazy { NSLocaleQuotationEndDelimiterKey_LAYOUT.varHandle() }

var NSLocaleQuotationEndDelimiterKey: MemorySegment
    get() = NSLocaleQuotationEndDelimiterKey_VH.get(NSLocaleQuotationEndDelimiterKey_SEGMENT) as MemorySegment
    set(value) = NSLocaleQuotationEndDelimiterKey_VH.set(NSLocaleQuotationEndDelimiterKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleAlternateQuotationBeginDelimiterKey typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleAlternateQuotationBeginDelimiterKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleAlternateQuotationBeginDelimiterKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleAlternateQuotationBeginDelimiterKey").orElseThrow() }
private val NSLocaleAlternateQuotationBeginDelimiterKey_VH: VarHandle by lazy { NSLocaleAlternateQuotationBeginDelimiterKey_LAYOUT.varHandle() }

var NSLocaleAlternateQuotationBeginDelimiterKey: MemorySegment
    get() = NSLocaleAlternateQuotationBeginDelimiterKey_VH.get(NSLocaleAlternateQuotationBeginDelimiterKey_SEGMENT) as MemorySegment
    set(value) = NSLocaleAlternateQuotationBeginDelimiterKey_VH.set(NSLocaleAlternateQuotationBeginDelimiterKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocaleAlternateQuotationEndDelimiterKey typedef const NSLocaleKey = (Void)*
 */
private val NSLocaleAlternateQuotationEndDelimiterKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocaleAlternateQuotationEndDelimiterKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocaleAlternateQuotationEndDelimiterKey").orElseThrow() }
private val NSLocaleAlternateQuotationEndDelimiterKey_VH: VarHandle by lazy { NSLocaleAlternateQuotationEndDelimiterKey_LAYOUT.varHandle() }

var NSLocaleAlternateQuotationEndDelimiterKey: MemorySegment
    get() = NSLocaleAlternateQuotationEndDelimiterKey_VH.get(NSLocaleAlternateQuotationEndDelimiterKey_SEGMENT) as MemorySegment
    set(value) = NSLocaleAlternateQuotationEndDelimiterKey_VH.set(NSLocaleAlternateQuotationEndDelimiterKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSGregorianCalendar (Void)*
 */
private val NSGregorianCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGregorianCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGregorianCalendar").orElseThrow() }
private val NSGregorianCalendar_VH: VarHandle by lazy { NSGregorianCalendar_LAYOUT.varHandle() }

var NSGregorianCalendar: MemorySegment
    get() = NSGregorianCalendar_VH.get(NSGregorianCalendar_SEGMENT) as MemorySegment
    set(value) = NSGregorianCalendar_VH.set(NSGregorianCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSBuddhistCalendar (Void)*
 */
private val NSBuddhistCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBuddhistCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBuddhistCalendar").orElseThrow() }
private val NSBuddhistCalendar_VH: VarHandle by lazy { NSBuddhistCalendar_LAYOUT.varHandle() }

var NSBuddhistCalendar: MemorySegment
    get() = NSBuddhistCalendar_VH.get(NSBuddhistCalendar_SEGMENT) as MemorySegment
    set(value) = NSBuddhistCalendar_VH.set(NSBuddhistCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSChineseCalendar (Void)*
 */
private val NSChineseCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSChineseCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSChineseCalendar").orElseThrow() }
private val NSChineseCalendar_VH: VarHandle by lazy { NSChineseCalendar_LAYOUT.varHandle() }

var NSChineseCalendar: MemorySegment
    get() = NSChineseCalendar_VH.get(NSChineseCalendar_SEGMENT) as MemorySegment
    set(value) = NSChineseCalendar_VH.set(NSChineseCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSHebrewCalendar (Void)*
 */
private val NSHebrewCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHebrewCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHebrewCalendar").orElseThrow() }
private val NSHebrewCalendar_VH: VarHandle by lazy { NSHebrewCalendar_LAYOUT.varHandle() }

var NSHebrewCalendar: MemorySegment
    get() = NSHebrewCalendar_VH.get(NSHebrewCalendar_SEGMENT) as MemorySegment
    set(value) = NSHebrewCalendar_VH.set(NSHebrewCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSIslamicCalendar (Void)*
 */
private val NSIslamicCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSIslamicCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIslamicCalendar").orElseThrow() }
private val NSIslamicCalendar_VH: VarHandle by lazy { NSIslamicCalendar_LAYOUT.varHandle() }

var NSIslamicCalendar: MemorySegment
    get() = NSIslamicCalendar_VH.get(NSIslamicCalendar_SEGMENT) as MemorySegment
    set(value) = NSIslamicCalendar_VH.set(NSIslamicCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSIslamicCivilCalendar (Void)*
 */
private val NSIslamicCivilCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSIslamicCivilCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIslamicCivilCalendar").orElseThrow() }
private val NSIslamicCivilCalendar_VH: VarHandle by lazy { NSIslamicCivilCalendar_LAYOUT.varHandle() }

var NSIslamicCivilCalendar: MemorySegment
    get() = NSIslamicCivilCalendar_VH.get(NSIslamicCivilCalendar_SEGMENT) as MemorySegment
    set(value) = NSIslamicCivilCalendar_VH.set(NSIslamicCivilCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSJapaneseCalendar (Void)*
 */
private val NSJapaneseCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSJapaneseCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSJapaneseCalendar").orElseThrow() }
private val NSJapaneseCalendar_VH: VarHandle by lazy { NSJapaneseCalendar_LAYOUT.varHandle() }

var NSJapaneseCalendar: MemorySegment
    get() = NSJapaneseCalendar_VH.get(NSJapaneseCalendar_SEGMENT) as MemorySegment
    set(value) = NSJapaneseCalendar_VH.set(NSJapaneseCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSRepublicOfChinaCalendar (Void)*
 */
private val NSRepublicOfChinaCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRepublicOfChinaCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRepublicOfChinaCalendar").orElseThrow() }
private val NSRepublicOfChinaCalendar_VH: VarHandle by lazy { NSRepublicOfChinaCalendar_LAYOUT.varHandle() }

var NSRepublicOfChinaCalendar: MemorySegment
    get() = NSRepublicOfChinaCalendar_VH.get(NSRepublicOfChinaCalendar_SEGMENT) as MemorySegment
    set(value) = NSRepublicOfChinaCalendar_VH.set(NSRepublicOfChinaCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSPersianCalendar (Void)*
 */
private val NSPersianCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPersianCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPersianCalendar").orElseThrow() }
private val NSPersianCalendar_VH: VarHandle by lazy { NSPersianCalendar_LAYOUT.varHandle() }

var NSPersianCalendar: MemorySegment
    get() = NSPersianCalendar_VH.get(NSPersianCalendar_SEGMENT) as MemorySegment
    set(value) = NSPersianCalendar_VH.set(NSPersianCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSIndianCalendar (Void)*
 */
private val NSIndianCalendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSIndianCalendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIndianCalendar").orElseThrow() }
private val NSIndianCalendar_VH: VarHandle by lazy { NSIndianCalendar_LAYOUT.varHandle() }

var NSIndianCalendar: MemorySegment
    get() = NSIndianCalendar_VH.get(NSIndianCalendar_SEGMENT) as MemorySegment
    set(value) = NSIndianCalendar_VH.set(NSIndianCalendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSISO8601Calendar (Void)*
 */
private val NSISO8601Calendar_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSISO8601Calendar_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSISO8601Calendar").orElseThrow() }
private val NSISO8601Calendar_VH: VarHandle by lazy { NSISO8601Calendar_LAYOUT.varHandle() }

var NSISO8601Calendar: MemorySegment
    get() = NSISO8601Calendar_VH.get(NSISO8601Calendar_SEGMENT) as MemorySegment
    set(value) = NSISO8601Calendar_VH.set(NSISO8601Calendar_SEGMENT, value)

/**
 * {@snippet lang=c : NSPersonNameComponentKey (Void)*
 */
private val NSPersonNameComponentKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPersonNameComponentKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPersonNameComponentKey").orElseThrow() }
private val NSPersonNameComponentKey_VH: VarHandle by lazy { NSPersonNameComponentKey_LAYOUT.varHandle() }

var NSPersonNameComponentKey: MemorySegment
    get() = NSPersonNameComponentKey_VH.get(NSPersonNameComponentKey_SEGMENT) as MemorySegment
    set(value) = NSPersonNameComponentKey_VH.set(NSPersonNameComponentKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSPersonNameComponentGivenName (Void)*
 */
private val NSPersonNameComponentGivenName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPersonNameComponentGivenName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPersonNameComponentGivenName").orElseThrow() }
private val NSPersonNameComponentGivenName_VH: VarHandle by lazy { NSPersonNameComponentGivenName_LAYOUT.varHandle() }

var NSPersonNameComponentGivenName: MemorySegment
    get() = NSPersonNameComponentGivenName_VH.get(NSPersonNameComponentGivenName_SEGMENT) as MemorySegment
    set(value) = NSPersonNameComponentGivenName_VH.set(NSPersonNameComponentGivenName_SEGMENT, value)

/**
 * {@snippet lang=c : NSPersonNameComponentFamilyName (Void)*
 */
private val NSPersonNameComponentFamilyName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPersonNameComponentFamilyName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPersonNameComponentFamilyName").orElseThrow() }
private val NSPersonNameComponentFamilyName_VH: VarHandle by lazy { NSPersonNameComponentFamilyName_LAYOUT.varHandle() }

var NSPersonNameComponentFamilyName: MemorySegment
    get() = NSPersonNameComponentFamilyName_VH.get(NSPersonNameComponentFamilyName_SEGMENT) as MemorySegment
    set(value) = NSPersonNameComponentFamilyName_VH.set(NSPersonNameComponentFamilyName_SEGMENT, value)

/**
 * {@snippet lang=c : NSPersonNameComponentMiddleName (Void)*
 */
private val NSPersonNameComponentMiddleName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPersonNameComponentMiddleName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPersonNameComponentMiddleName").orElseThrow() }
private val NSPersonNameComponentMiddleName_VH: VarHandle by lazy { NSPersonNameComponentMiddleName_LAYOUT.varHandle() }

var NSPersonNameComponentMiddleName: MemorySegment
    get() = NSPersonNameComponentMiddleName_VH.get(NSPersonNameComponentMiddleName_SEGMENT) as MemorySegment
    set(value) = NSPersonNameComponentMiddleName_VH.set(NSPersonNameComponentMiddleName_SEGMENT, value)

/**
 * {@snippet lang=c : NSPersonNameComponentPrefix (Void)*
 */
private val NSPersonNameComponentPrefix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPersonNameComponentPrefix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPersonNameComponentPrefix").orElseThrow() }
private val NSPersonNameComponentPrefix_VH: VarHandle by lazy { NSPersonNameComponentPrefix_LAYOUT.varHandle() }

var NSPersonNameComponentPrefix: MemorySegment
    get() = NSPersonNameComponentPrefix_VH.get(NSPersonNameComponentPrefix_SEGMENT) as MemorySegment
    set(value) = NSPersonNameComponentPrefix_VH.set(NSPersonNameComponentPrefix_SEGMENT, value)

/**
 * {@snippet lang=c : NSPersonNameComponentSuffix (Void)*
 */
private val NSPersonNameComponentSuffix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPersonNameComponentSuffix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPersonNameComponentSuffix").orElseThrow() }
private val NSPersonNameComponentSuffix_VH: VarHandle by lazy { NSPersonNameComponentSuffix_LAYOUT.varHandle() }

var NSPersonNameComponentSuffix: MemorySegment
    get() = NSPersonNameComponentSuffix_VH.get(NSPersonNameComponentSuffix_SEGMENT) as MemorySegment
    set(value) = NSPersonNameComponentSuffix_VH.set(NSPersonNameComponentSuffix_SEGMENT, value)

/**
 * {@snippet lang=c : NSPersonNameComponentNickname (Void)*
 */
private val NSPersonNameComponentNickname_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPersonNameComponentNickname_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPersonNameComponentNickname").orElseThrow() }
private val NSPersonNameComponentNickname_VH: VarHandle by lazy { NSPersonNameComponentNickname_LAYOUT.varHandle() }

var NSPersonNameComponentNickname: MemorySegment
    get() = NSPersonNameComponentNickname_VH.get(NSPersonNameComponentNickname_SEGMENT) as MemorySegment
    set(value) = NSPersonNameComponentNickname_VH.set(NSPersonNameComponentNickname_SEGMENT, value)

/**
 * {@snippet lang=c : NSPersonNameComponentDelimiter (Void)*
 */
private val NSPersonNameComponentDelimiter_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPersonNameComponentDelimiter_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPersonNameComponentDelimiter").orElseThrow() }
private val NSPersonNameComponentDelimiter_VH: VarHandle by lazy { NSPersonNameComponentDelimiter_LAYOUT.varHandle() }

var NSPersonNameComponentDelimiter: MemorySegment
    get() = NSPersonNameComponentDelimiter_VH.get(NSPersonNameComponentDelimiter_SEGMENT) as MemorySegment
    set(value) = NSPersonNameComponentDelimiter_VH.set(NSPersonNameComponentDelimiter_SEGMENT, value)

/**
 * {@snippet lang=c : NSDecimalCopy Void((typedef NSDecimal = Declared(NSDecimal))*,(typedef NSDecimal = Declared(NSDecimal))*)
 */
private val NSDecimalCopy_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSDecimalCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDecimalCopy").orElseThrow()
private val NSDecimalCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDecimalCopy_ADDR, NSDecimalCopy_DESC)

fun NSDecimalCopy(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSDecimalCopy_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDecimalCompact Void((typedef NSDecimal = Declared(NSDecimal))*)
 */
private val NSDecimalCompact_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSDecimalCompact_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDecimalCompact").orElseThrow()
private val NSDecimalCompact_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDecimalCompact_ADDR, NSDecimalCompact_DESC)

fun NSDecimalCompact(arg0: MemorySegment): Unit {
    try {
        NSDecimalCompact_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSDecimalString typedef NSString = (Void)*((typedef NSDecimal = Declared(NSDecimal))*,typedef id = (Void)*)
 */
private val NSDecimalString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSDecimalString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSDecimalString").orElseThrow()
private val NSDecimalString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSDecimalString_ADDR, NSDecimalString_DESC)

fun NSDecimalString(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSDecimalString_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSGenericException typedef const NSExceptionName = (Void)*
 */
private val NSGenericException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGenericException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGenericException").orElseThrow() }
private val NSGenericException_VH: VarHandle by lazy { NSGenericException_LAYOUT.varHandle() }

var NSGenericException: MemorySegment
    get() = NSGenericException_VH.get(NSGenericException_SEGMENT) as MemorySegment
    set(value) = NSGenericException_VH.set(NSGenericException_SEGMENT, value)

/**
 * {@snippet lang=c : NSRangeException typedef const NSExceptionName = (Void)*
 */
private val NSRangeException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRangeException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRangeException").orElseThrow() }
private val NSRangeException_VH: VarHandle by lazy { NSRangeException_LAYOUT.varHandle() }

var NSRangeException: MemorySegment
    get() = NSRangeException_VH.get(NSRangeException_SEGMENT) as MemorySegment
    set(value) = NSRangeException_VH.set(NSRangeException_SEGMENT, value)

/**
 * {@snippet lang=c : NSInvalidArgumentException typedef const NSExceptionName = (Void)*
 */
private val NSInvalidArgumentException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInvalidArgumentException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInvalidArgumentException").orElseThrow() }
private val NSInvalidArgumentException_VH: VarHandle by lazy { NSInvalidArgumentException_LAYOUT.varHandle() }

var NSInvalidArgumentException: MemorySegment
    get() = NSInvalidArgumentException_VH.get(NSInvalidArgumentException_SEGMENT) as MemorySegment
    set(value) = NSInvalidArgumentException_VH.set(NSInvalidArgumentException_SEGMENT, value)

/**
 * {@snippet lang=c : NSInternalInconsistencyException typedef const NSExceptionName = (Void)*
 */
private val NSInternalInconsistencyException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInternalInconsistencyException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInternalInconsistencyException").orElseThrow() }
private val NSInternalInconsistencyException_VH: VarHandle by lazy { NSInternalInconsistencyException_LAYOUT.varHandle() }

var NSInternalInconsistencyException: MemorySegment
    get() = NSInternalInconsistencyException_VH.get(NSInternalInconsistencyException_SEGMENT) as MemorySegment
    set(value) = NSInternalInconsistencyException_VH.set(NSInternalInconsistencyException_SEGMENT, value)

/**
 * {@snippet lang=c : NSMallocException typedef const NSExceptionName = (Void)*
 */
private val NSMallocException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMallocException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMallocException").orElseThrow() }
private val NSMallocException_VH: VarHandle by lazy { NSMallocException_LAYOUT.varHandle() }

var NSMallocException: MemorySegment
    get() = NSMallocException_VH.get(NSMallocException_SEGMENT) as MemorySegment
    set(value) = NSMallocException_VH.set(NSMallocException_SEGMENT, value)

/**
 * {@snippet lang=c : NSObjectInaccessibleException typedef const NSExceptionName = (Void)*
 */
private val NSObjectInaccessibleException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSObjectInaccessibleException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSObjectInaccessibleException").orElseThrow() }
private val NSObjectInaccessibleException_VH: VarHandle by lazy { NSObjectInaccessibleException_LAYOUT.varHandle() }

var NSObjectInaccessibleException: MemorySegment
    get() = NSObjectInaccessibleException_VH.get(NSObjectInaccessibleException_SEGMENT) as MemorySegment
    set(value) = NSObjectInaccessibleException_VH.set(NSObjectInaccessibleException_SEGMENT, value)

/**
 * {@snippet lang=c : NSObjectNotAvailableException typedef const NSExceptionName = (Void)*
 */
private val NSObjectNotAvailableException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSObjectNotAvailableException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSObjectNotAvailableException").orElseThrow() }
private val NSObjectNotAvailableException_VH: VarHandle by lazy { NSObjectNotAvailableException_LAYOUT.varHandle() }

var NSObjectNotAvailableException: MemorySegment
    get() = NSObjectNotAvailableException_VH.get(NSObjectNotAvailableException_SEGMENT) as MemorySegment
    set(value) = NSObjectNotAvailableException_VH.set(NSObjectNotAvailableException_SEGMENT, value)

/**
 * {@snippet lang=c : NSDestinationInvalidException typedef const NSExceptionName = (Void)*
 */
private val NSDestinationInvalidException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDestinationInvalidException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDestinationInvalidException").orElseThrow() }
private val NSDestinationInvalidException_VH: VarHandle by lazy { NSDestinationInvalidException_LAYOUT.varHandle() }

var NSDestinationInvalidException: MemorySegment
    get() = NSDestinationInvalidException_VH.get(NSDestinationInvalidException_SEGMENT) as MemorySegment
    set(value) = NSDestinationInvalidException_VH.set(NSDestinationInvalidException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPortTimeoutException typedef const NSExceptionName = (Void)*
 */
private val NSPortTimeoutException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPortTimeoutException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPortTimeoutException").orElseThrow() }
private val NSPortTimeoutException_VH: VarHandle by lazy { NSPortTimeoutException_LAYOUT.varHandle() }

var NSPortTimeoutException: MemorySegment
    get() = NSPortTimeoutException_VH.get(NSPortTimeoutException_SEGMENT) as MemorySegment
    set(value) = NSPortTimeoutException_VH.set(NSPortTimeoutException_SEGMENT, value)

/**
 * {@snippet lang=c : NSInvalidSendPortException typedef const NSExceptionName = (Void)*
 */
private val NSInvalidSendPortException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInvalidSendPortException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInvalidSendPortException").orElseThrow() }
private val NSInvalidSendPortException_VH: VarHandle by lazy { NSInvalidSendPortException_LAYOUT.varHandle() }

var NSInvalidSendPortException: MemorySegment
    get() = NSInvalidSendPortException_VH.get(NSInvalidSendPortException_SEGMENT) as MemorySegment
    set(value) = NSInvalidSendPortException_VH.set(NSInvalidSendPortException_SEGMENT, value)

/**
 * {@snippet lang=c : NSInvalidReceivePortException typedef const NSExceptionName = (Void)*
 */
private val NSInvalidReceivePortException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInvalidReceivePortException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInvalidReceivePortException").orElseThrow() }
private val NSInvalidReceivePortException_VH: VarHandle by lazy { NSInvalidReceivePortException_LAYOUT.varHandle() }

var NSInvalidReceivePortException: MemorySegment
    get() = NSInvalidReceivePortException_VH.get(NSInvalidReceivePortException_SEGMENT) as MemorySegment
    set(value) = NSInvalidReceivePortException_VH.set(NSInvalidReceivePortException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPortSendException typedef const NSExceptionName = (Void)*
 */
private val NSPortSendException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPortSendException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPortSendException").orElseThrow() }
private val NSPortSendException_VH: VarHandle by lazy { NSPortSendException_LAYOUT.varHandle() }

var NSPortSendException: MemorySegment
    get() = NSPortSendException_VH.get(NSPortSendException_SEGMENT) as MemorySegment
    set(value) = NSPortSendException_VH.set(NSPortSendException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPortReceiveException typedef const NSExceptionName = (Void)*
 */
private val NSPortReceiveException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPortReceiveException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPortReceiveException").orElseThrow() }
private val NSPortReceiveException_VH: VarHandle by lazy { NSPortReceiveException_LAYOUT.varHandle() }

var NSPortReceiveException: MemorySegment
    get() = NSPortReceiveException_VH.get(NSPortReceiveException_SEGMENT) as MemorySegment
    set(value) = NSPortReceiveException_VH.set(NSPortReceiveException_SEGMENT, value)

/**
 * {@snippet lang=c : NSOldStyleException typedef const NSExceptionName = (Void)*
 */
private val NSOldStyleException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOldStyleException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOldStyleException").orElseThrow() }
private val NSOldStyleException_VH: VarHandle by lazy { NSOldStyleException_LAYOUT.varHandle() }

var NSOldStyleException: MemorySegment
    get() = NSOldStyleException_VH.get(NSOldStyleException_SEGMENT) as MemorySegment
    set(value) = NSOldStyleException_VH.set(NSOldStyleException_SEGMENT, value)

/**
 * {@snippet lang=c : NSInconsistentArchiveException typedef const NSExceptionName = (Void)*
 */
private val NSInconsistentArchiveException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInconsistentArchiveException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInconsistentArchiveException").orElseThrow() }
private val NSInconsistentArchiveException_VH: VarHandle by lazy { NSInconsistentArchiveException_LAYOUT.varHandle() }

var NSInconsistentArchiveException: MemorySegment
    get() = NSInconsistentArchiveException_VH.get(NSInconsistentArchiveException_SEGMENT) as MemorySegment
    set(value) = NSInconsistentArchiveException_VH.set(NSInconsistentArchiveException_SEGMENT, value)

/**
 * {@snippet lang=c : NSGetUncaughtExceptionHandler (typedef NSUncaughtExceptionHandler = Void(typedef NSException = (Void)*))*()
 */
private val NSGetUncaughtExceptionHandler_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val NSGetUncaughtExceptionHandler_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSGetUncaughtExceptionHandler").orElseThrow()
private val NSGetUncaughtExceptionHandler_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSGetUncaughtExceptionHandler_ADDR, NSGetUncaughtExceptionHandler_DESC)

fun NSGetUncaughtExceptionHandler(): MemorySegment {
    try {
        return NSGetUncaughtExceptionHandler_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSSetUncaughtExceptionHandler Void((typedef NSUncaughtExceptionHandler = Void(typedef NSException = (Void)*))*)
 */
private val NSSetUncaughtExceptionHandler_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSSetUncaughtExceptionHandler_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSSetUncaughtExceptionHandler").orElseThrow()
private val NSSetUncaughtExceptionHandler_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSSetUncaughtExceptionHandler_ADDR, NSSetUncaughtExceptionHandler_DESC)

fun NSSetUncaughtExceptionHandler(arg0: MemorySegment): Unit {
    try {
        NSSetUncaughtExceptionHandler_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAssertionHandlerKey (Void)*
 */
private val NSAssertionHandlerKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAssertionHandlerKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAssertionHandlerKey").orElseThrow() }
private val NSAssertionHandlerKey_VH: VarHandle by lazy { NSAssertionHandlerKey_LAYOUT.varHandle() }

var NSAssertionHandlerKey: MemorySegment
    get() = NSAssertionHandlerKey_VH.get(NSAssertionHandlerKey_SEGMENT) as MemorySegment
    set(value) = NSAssertionHandlerKey_VH.set(NSAssertionHandlerKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSDecimalNumberExactnessException typedef const NSExceptionName = (Void)*
 */
private val NSDecimalNumberExactnessException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDecimalNumberExactnessException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDecimalNumberExactnessException").orElseThrow() }
private val NSDecimalNumberExactnessException_VH: VarHandle by lazy { NSDecimalNumberExactnessException_LAYOUT.varHandle() }

var NSDecimalNumberExactnessException: MemorySegment
    get() = NSDecimalNumberExactnessException_VH.get(NSDecimalNumberExactnessException_SEGMENT) as MemorySegment
    set(value) = NSDecimalNumberExactnessException_VH.set(NSDecimalNumberExactnessException_SEGMENT, value)

/**
 * {@snippet lang=c : NSDecimalNumberOverflowException typedef const NSExceptionName = (Void)*
 */
private val NSDecimalNumberOverflowException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDecimalNumberOverflowException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDecimalNumberOverflowException").orElseThrow() }
private val NSDecimalNumberOverflowException_VH: VarHandle by lazy { NSDecimalNumberOverflowException_LAYOUT.varHandle() }

var NSDecimalNumberOverflowException: MemorySegment
    get() = NSDecimalNumberOverflowException_VH.get(NSDecimalNumberOverflowException_SEGMENT) as MemorySegment
    set(value) = NSDecimalNumberOverflowException_VH.set(NSDecimalNumberOverflowException_SEGMENT, value)

/**
 * {@snippet lang=c : NSDecimalNumberUnderflowException typedef const NSExceptionName = (Void)*
 */
private val NSDecimalNumberUnderflowException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDecimalNumberUnderflowException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDecimalNumberUnderflowException").orElseThrow() }
private val NSDecimalNumberUnderflowException_VH: VarHandle by lazy { NSDecimalNumberUnderflowException_LAYOUT.varHandle() }

var NSDecimalNumberUnderflowException: MemorySegment
    get() = NSDecimalNumberUnderflowException_VH.get(NSDecimalNumberUnderflowException_SEGMENT) as MemorySegment
    set(value) = NSDecimalNumberUnderflowException_VH.set(NSDecimalNumberUnderflowException_SEGMENT, value)

/**
 * {@snippet lang=c : NSDecimalNumberDivideByZeroException typedef const NSExceptionName = (Void)*
 */
private val NSDecimalNumberDivideByZeroException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDecimalNumberDivideByZeroException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDecimalNumberDivideByZeroException").orElseThrow() }
private val NSDecimalNumberDivideByZeroException_VH: VarHandle by lazy { NSDecimalNumberDivideByZeroException_LAYOUT.varHandle() }

var NSDecimalNumberDivideByZeroException: MemorySegment
    get() = NSDecimalNumberDivideByZeroException_VH.get(NSDecimalNumberDivideByZeroException_SEGMENT) as MemorySegment
    set(value) = NSDecimalNumberDivideByZeroException_VH.set(NSDecimalNumberDivideByZeroException_SEGMENT, value)

/**
 * {@snippet lang=c : NSCocoaErrorDomain typedef const NSErrorDomain = (Void)*
 */
private val NSCocoaErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCocoaErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCocoaErrorDomain").orElseThrow() }
private val NSCocoaErrorDomain_VH: VarHandle by lazy { NSCocoaErrorDomain_LAYOUT.varHandle() }

var NSCocoaErrorDomain: MemorySegment
    get() = NSCocoaErrorDomain_VH.get(NSCocoaErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSCocoaErrorDomain_VH.set(NSCocoaErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSPOSIXErrorDomain typedef const NSErrorDomain = (Void)*
 */
private val NSPOSIXErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPOSIXErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPOSIXErrorDomain").orElseThrow() }
private val NSPOSIXErrorDomain_VH: VarHandle by lazy { NSPOSIXErrorDomain_LAYOUT.varHandle() }

var NSPOSIXErrorDomain: MemorySegment
    get() = NSPOSIXErrorDomain_VH.get(NSPOSIXErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSPOSIXErrorDomain_VH.set(NSPOSIXErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSOSStatusErrorDomain typedef const NSErrorDomain = (Void)*
 */
private val NSOSStatusErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOSStatusErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOSStatusErrorDomain").orElseThrow() }
private val NSOSStatusErrorDomain_VH: VarHandle by lazy { NSOSStatusErrorDomain_LAYOUT.varHandle() }

var NSOSStatusErrorDomain: MemorySegment
    get() = NSOSStatusErrorDomain_VH.get(NSOSStatusErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSOSStatusErrorDomain_VH.set(NSOSStatusErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSMachErrorDomain typedef const NSErrorDomain = (Void)*
 */
private val NSMachErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMachErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMachErrorDomain").orElseThrow() }
private val NSMachErrorDomain_VH: VarHandle by lazy { NSMachErrorDomain_LAYOUT.varHandle() }

var NSMachErrorDomain: MemorySegment
    get() = NSMachErrorDomain_VH.get(NSMachErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSMachErrorDomain_VH.set(NSMachErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSUnderlyingErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSUnderlyingErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUnderlyingErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUnderlyingErrorKey").orElseThrow() }
private val NSUnderlyingErrorKey_VH: VarHandle by lazy { NSUnderlyingErrorKey_LAYOUT.varHandle() }

var NSUnderlyingErrorKey: MemorySegment
    get() = NSUnderlyingErrorKey_VH.get(NSUnderlyingErrorKey_SEGMENT) as MemorySegment
    set(value) = NSUnderlyingErrorKey_VH.set(NSUnderlyingErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSMultipleUnderlyingErrorsKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSMultipleUnderlyingErrorsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMultipleUnderlyingErrorsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMultipleUnderlyingErrorsKey").orElseThrow() }
private val NSMultipleUnderlyingErrorsKey_VH: VarHandle by lazy { NSMultipleUnderlyingErrorsKey_LAYOUT.varHandle() }

var NSMultipleUnderlyingErrorsKey: MemorySegment
    get() = NSMultipleUnderlyingErrorsKey_VH.get(NSMultipleUnderlyingErrorsKey_SEGMENT) as MemorySegment
    set(value) = NSMultipleUnderlyingErrorsKey_VH.set(NSMultipleUnderlyingErrorsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocalizedDescriptionKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSLocalizedDescriptionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocalizedDescriptionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocalizedDescriptionKey").orElseThrow() }
private val NSLocalizedDescriptionKey_VH: VarHandle by lazy { NSLocalizedDescriptionKey_LAYOUT.varHandle() }

var NSLocalizedDescriptionKey: MemorySegment
    get() = NSLocalizedDescriptionKey_VH.get(NSLocalizedDescriptionKey_SEGMENT) as MemorySegment
    set(value) = NSLocalizedDescriptionKey_VH.set(NSLocalizedDescriptionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocalizedFailureReasonErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSLocalizedFailureReasonErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocalizedFailureReasonErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocalizedFailureReasonErrorKey").orElseThrow() }
private val NSLocalizedFailureReasonErrorKey_VH: VarHandle by lazy { NSLocalizedFailureReasonErrorKey_LAYOUT.varHandle() }

var NSLocalizedFailureReasonErrorKey: MemorySegment
    get() = NSLocalizedFailureReasonErrorKey_VH.get(NSLocalizedFailureReasonErrorKey_SEGMENT) as MemorySegment
    set(value) = NSLocalizedFailureReasonErrorKey_VH.set(NSLocalizedFailureReasonErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocalizedRecoverySuggestionErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSLocalizedRecoverySuggestionErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocalizedRecoverySuggestionErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocalizedRecoverySuggestionErrorKey").orElseThrow() }
private val NSLocalizedRecoverySuggestionErrorKey_VH: VarHandle by lazy { NSLocalizedRecoverySuggestionErrorKey_LAYOUT.varHandle() }

var NSLocalizedRecoverySuggestionErrorKey: MemorySegment
    get() = NSLocalizedRecoverySuggestionErrorKey_VH.get(NSLocalizedRecoverySuggestionErrorKey_SEGMENT) as MemorySegment
    set(value) = NSLocalizedRecoverySuggestionErrorKey_VH.set(NSLocalizedRecoverySuggestionErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocalizedRecoveryOptionsErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSLocalizedRecoveryOptionsErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocalizedRecoveryOptionsErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocalizedRecoveryOptionsErrorKey").orElseThrow() }
private val NSLocalizedRecoveryOptionsErrorKey_VH: VarHandle by lazy { NSLocalizedRecoveryOptionsErrorKey_LAYOUT.varHandle() }

var NSLocalizedRecoveryOptionsErrorKey: MemorySegment
    get() = NSLocalizedRecoveryOptionsErrorKey_VH.get(NSLocalizedRecoveryOptionsErrorKey_SEGMENT) as MemorySegment
    set(value) = NSLocalizedRecoveryOptionsErrorKey_VH.set(NSLocalizedRecoveryOptionsErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSRecoveryAttempterErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSRecoveryAttempterErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRecoveryAttempterErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRecoveryAttempterErrorKey").orElseThrow() }
private val NSRecoveryAttempterErrorKey_VH: VarHandle by lazy { NSRecoveryAttempterErrorKey_LAYOUT.varHandle() }

var NSRecoveryAttempterErrorKey: MemorySegment
    get() = NSRecoveryAttempterErrorKey_VH.get(NSRecoveryAttempterErrorKey_SEGMENT) as MemorySegment
    set(value) = NSRecoveryAttempterErrorKey_VH.set(NSRecoveryAttempterErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSHelpAnchorErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSHelpAnchorErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHelpAnchorErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHelpAnchorErrorKey").orElseThrow() }
private val NSHelpAnchorErrorKey_VH: VarHandle by lazy { NSHelpAnchorErrorKey_LAYOUT.varHandle() }

var NSHelpAnchorErrorKey: MemorySegment
    get() = NSHelpAnchorErrorKey_VH.get(NSHelpAnchorErrorKey_SEGMENT) as MemorySegment
    set(value) = NSHelpAnchorErrorKey_VH.set(NSHelpAnchorErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSDebugDescriptionErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSDebugDescriptionErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDebugDescriptionErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDebugDescriptionErrorKey").orElseThrow() }
private val NSDebugDescriptionErrorKey_VH: VarHandle by lazy { NSDebugDescriptionErrorKey_LAYOUT.varHandle() }

var NSDebugDescriptionErrorKey: MemorySegment
    get() = NSDebugDescriptionErrorKey_VH.get(NSDebugDescriptionErrorKey_SEGMENT) as MemorySegment
    set(value) = NSDebugDescriptionErrorKey_VH.set(NSDebugDescriptionErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSLocalizedFailureErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSLocalizedFailureErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLocalizedFailureErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLocalizedFailureErrorKey").orElseThrow() }
private val NSLocalizedFailureErrorKey_VH: VarHandle by lazy { NSLocalizedFailureErrorKey_LAYOUT.varHandle() }

var NSLocalizedFailureErrorKey: MemorySegment
    get() = NSLocalizedFailureErrorKey_VH.get(NSLocalizedFailureErrorKey_SEGMENT) as MemorySegment
    set(value) = NSLocalizedFailureErrorKey_VH.set(NSLocalizedFailureErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStringEncodingErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSStringEncodingErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStringEncodingErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStringEncodingErrorKey").orElseThrow() }
private val NSStringEncodingErrorKey_VH: VarHandle by lazy { NSStringEncodingErrorKey_LAYOUT.varHandle() }

var NSStringEncodingErrorKey: MemorySegment
    get() = NSStringEncodingErrorKey_VH.get(NSStringEncodingErrorKey_SEGMENT) as MemorySegment
    set(value) = NSStringEncodingErrorKey_VH.set(NSStringEncodingErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSURLErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLErrorKey").orElseThrow() }
private val NSURLErrorKey_VH: VarHandle by lazy { NSURLErrorKey_LAYOUT.varHandle() }

var NSURLErrorKey: MemorySegment
    get() = NSURLErrorKey_VH.get(NSURLErrorKey_SEGMENT) as MemorySegment
    set(value) = NSURLErrorKey_VH.set(NSURLErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSFilePathErrorKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSFilePathErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFilePathErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFilePathErrorKey").orElseThrow() }
private val NSFilePathErrorKey_VH: VarHandle by lazy { NSFilePathErrorKey_LAYOUT.varHandle() }

var NSFilePathErrorKey: MemorySegment
    get() = NSFilePathErrorKey_VH.get(NSFilePathErrorKey_SEGMENT) as MemorySegment
    set(value) = NSFilePathErrorKey_VH.set(NSFilePathErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSDefaultRunLoopMode typedef const NSRunLoopMode = (Void)*
 */
private val NSDefaultRunLoopMode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDefaultRunLoopMode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDefaultRunLoopMode").orElseThrow() }
private val NSDefaultRunLoopMode_VH: VarHandle by lazy { NSDefaultRunLoopMode_LAYOUT.varHandle() }

var NSDefaultRunLoopMode: MemorySegment
    get() = NSDefaultRunLoopMode_VH.get(NSDefaultRunLoopMode_SEGMENT) as MemorySegment
    set(value) = NSDefaultRunLoopMode_VH.set(NSDefaultRunLoopMode_SEGMENT, value)

/**
 * {@snippet lang=c : NSRunLoopCommonModes typedef const NSRunLoopMode = (Void)*
 */
private val NSRunLoopCommonModes_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRunLoopCommonModes_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRunLoopCommonModes").orElseThrow() }
private val NSRunLoopCommonModes_VH: VarHandle by lazy { NSRunLoopCommonModes_LAYOUT.varHandle() }

var NSRunLoopCommonModes: MemorySegment
    get() = NSRunLoopCommonModes_VH.get(NSRunLoopCommonModes_SEGMENT) as MemorySegment
    set(value) = NSRunLoopCommonModes_VH.set(NSRunLoopCommonModes_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHandleOperationException typedef const NSExceptionName = (Void)*
 */
private val NSFileHandleOperationException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHandleOperationException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHandleOperationException").orElseThrow() }
private val NSFileHandleOperationException_VH: VarHandle by lazy { NSFileHandleOperationException_LAYOUT.varHandle() }

var NSFileHandleOperationException: MemorySegment
    get() = NSFileHandleOperationException_VH.get(NSFileHandleOperationException_SEGMENT) as MemorySegment
    set(value) = NSFileHandleOperationException_VH.set(NSFileHandleOperationException_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHandleReadCompletionNotification typedef const NSNotificationName = (Void)*
 */
private val NSFileHandleReadCompletionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHandleReadCompletionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHandleReadCompletionNotification").orElseThrow() }
private val NSFileHandleReadCompletionNotification_VH: VarHandle by lazy { NSFileHandleReadCompletionNotification_LAYOUT.varHandle() }

var NSFileHandleReadCompletionNotification: MemorySegment
    get() = NSFileHandleReadCompletionNotification_VH.get(NSFileHandleReadCompletionNotification_SEGMENT) as MemorySegment
    set(value) = NSFileHandleReadCompletionNotification_VH.set(NSFileHandleReadCompletionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHandleReadToEndOfFileCompletionNotification typedef const NSNotificationName = (Void)*
 */
private val NSFileHandleReadToEndOfFileCompletionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHandleReadToEndOfFileCompletionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHandleReadToEndOfFileCompletionNotification").orElseThrow() }
private val NSFileHandleReadToEndOfFileCompletionNotification_VH: VarHandle by lazy { NSFileHandleReadToEndOfFileCompletionNotification_LAYOUT.varHandle() }

var NSFileHandleReadToEndOfFileCompletionNotification: MemorySegment
    get() = NSFileHandleReadToEndOfFileCompletionNotification_VH.get(NSFileHandleReadToEndOfFileCompletionNotification_SEGMENT) as MemorySegment
    set(value) = NSFileHandleReadToEndOfFileCompletionNotification_VH.set(NSFileHandleReadToEndOfFileCompletionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHandleConnectionAcceptedNotification typedef const NSNotificationName = (Void)*
 */
private val NSFileHandleConnectionAcceptedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHandleConnectionAcceptedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHandleConnectionAcceptedNotification").orElseThrow() }
private val NSFileHandleConnectionAcceptedNotification_VH: VarHandle by lazy { NSFileHandleConnectionAcceptedNotification_LAYOUT.varHandle() }

var NSFileHandleConnectionAcceptedNotification: MemorySegment
    get() = NSFileHandleConnectionAcceptedNotification_VH.get(NSFileHandleConnectionAcceptedNotification_SEGMENT) as MemorySegment
    set(value) = NSFileHandleConnectionAcceptedNotification_VH.set(NSFileHandleConnectionAcceptedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHandleDataAvailableNotification typedef const NSNotificationName = (Void)*
 */
private val NSFileHandleDataAvailableNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHandleDataAvailableNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHandleDataAvailableNotification").orElseThrow() }
private val NSFileHandleDataAvailableNotification_VH: VarHandle by lazy { NSFileHandleDataAvailableNotification_LAYOUT.varHandle() }

var NSFileHandleDataAvailableNotification: MemorySegment
    get() = NSFileHandleDataAvailableNotification_VH.get(NSFileHandleDataAvailableNotification_SEGMENT) as MemorySegment
    set(value) = NSFileHandleDataAvailableNotification_VH.set(NSFileHandleDataAvailableNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHandleNotificationDataItem (Void)*
 */
private val NSFileHandleNotificationDataItem_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHandleNotificationDataItem_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHandleNotificationDataItem").orElseThrow() }
private val NSFileHandleNotificationDataItem_VH: VarHandle by lazy { NSFileHandleNotificationDataItem_LAYOUT.varHandle() }

var NSFileHandleNotificationDataItem: MemorySegment
    get() = NSFileHandleNotificationDataItem_VH.get(NSFileHandleNotificationDataItem_SEGMENT) as MemorySegment
    set(value) = NSFileHandleNotificationDataItem_VH.set(NSFileHandleNotificationDataItem_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHandleNotificationFileHandleItem (Void)*
 */
private val NSFileHandleNotificationFileHandleItem_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHandleNotificationFileHandleItem_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHandleNotificationFileHandleItem").orElseThrow() }
private val NSFileHandleNotificationFileHandleItem_VH: VarHandle by lazy { NSFileHandleNotificationFileHandleItem_LAYOUT.varHandle() }

var NSFileHandleNotificationFileHandleItem: MemorySegment
    get() = NSFileHandleNotificationFileHandleItem_VH.get(NSFileHandleNotificationFileHandleItem_SEGMENT) as MemorySegment
    set(value) = NSFileHandleNotificationFileHandleItem_VH.set(NSFileHandleNotificationFileHandleItem_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHandleNotificationMonitorModes (Void)*
 */
private val NSFileHandleNotificationMonitorModes_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHandleNotificationMonitorModes_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHandleNotificationMonitorModes").orElseThrow() }
private val NSFileHandleNotificationMonitorModes_VH: VarHandle by lazy { NSFileHandleNotificationMonitorModes_LAYOUT.varHandle() }

var NSFileHandleNotificationMonitorModes: MemorySegment
    get() = NSFileHandleNotificationMonitorModes_VH.get(NSFileHandleNotificationMonitorModes_SEGMENT) as MemorySegment
    set(value) = NSFileHandleNotificationMonitorModes_VH.set(NSFileHandleNotificationMonitorModes_SEGMENT, value)

/**
 * {@snippet lang=c : NSUserName typedef NSString = (Void)*()
 */
private val NSUserName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val NSUserName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSUserName").orElseThrow()
private val NSUserName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSUserName_ADDR, NSUserName_DESC)

fun NSUserName(): MemorySegment {
    try {
        return NSUserName_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSFullUserName typedef NSString = (Void)*()
 */
private val NSFullUserName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val NSFullUserName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSFullUserName").orElseThrow()
private val NSFullUserName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSFullUserName_ADDR, NSFullUserName_DESC)

fun NSFullUserName(): MemorySegment {
    try {
        return NSFullUserName_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHomeDirectory typedef NSString = (Void)*()
 */
private val NSHomeDirectory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val NSHomeDirectory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHomeDirectory").orElseThrow()
private val NSHomeDirectory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHomeDirectory_ADDR, NSHomeDirectory_DESC)

fun NSHomeDirectory(): MemorySegment {
    try {
        return NSHomeDirectory_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHomeDirectoryForUser typedef NSString = (Void)*(typedef NSString = (Void)*)
 */
private val NSHomeDirectoryForUser_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSHomeDirectoryForUser_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHomeDirectoryForUser").orElseThrow()
private val NSHomeDirectoryForUser_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHomeDirectoryForUser_ADDR, NSHomeDirectoryForUser_DESC)

fun NSHomeDirectoryForUser(arg0: MemorySegment): MemorySegment {
    try {
        return NSHomeDirectoryForUser_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSTemporaryDirectory typedef NSString = (Void)*()
 */
private val NSTemporaryDirectory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val NSTemporaryDirectory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSTemporaryDirectory").orElseThrow()
private val NSTemporaryDirectory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSTemporaryDirectory_ADDR, NSTemporaryDirectory_DESC)

fun NSTemporaryDirectory(): MemorySegment {
    try {
        return NSTemporaryDirectory_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSOpenStepRootDirectory typedef NSString = (Void)*()
 */
private val NSOpenStepRootDirectory_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val NSOpenStepRootDirectory_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSOpenStepRootDirectory").orElseThrow()
private val NSOpenStepRootDirectory_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSOpenStepRootDirectory_ADDR, NSOpenStepRootDirectory_DESC)

fun NSOpenStepRootDirectory(): MemorySegment {
    try {
        return NSOpenStepRootDirectory_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHTTPPropertyStatusCodeKey (Void)*
 */
private val NSHTTPPropertyStatusCodeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPPropertyStatusCodeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPPropertyStatusCodeKey").orElseThrow() }
private val NSHTTPPropertyStatusCodeKey_VH: VarHandle by lazy { NSHTTPPropertyStatusCodeKey_LAYOUT.varHandle() }

var NSHTTPPropertyStatusCodeKey: MemorySegment
    get() = NSHTTPPropertyStatusCodeKey_VH.get(NSHTTPPropertyStatusCodeKey_SEGMENT) as MemorySegment
    set(value) = NSHTTPPropertyStatusCodeKey_VH.set(NSHTTPPropertyStatusCodeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPPropertyStatusReasonKey (Void)*
 */
private val NSHTTPPropertyStatusReasonKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPPropertyStatusReasonKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPPropertyStatusReasonKey").orElseThrow() }
private val NSHTTPPropertyStatusReasonKey_VH: VarHandle by lazy { NSHTTPPropertyStatusReasonKey_LAYOUT.varHandle() }

var NSHTTPPropertyStatusReasonKey: MemorySegment
    get() = NSHTTPPropertyStatusReasonKey_VH.get(NSHTTPPropertyStatusReasonKey_SEGMENT) as MemorySegment
    set(value) = NSHTTPPropertyStatusReasonKey_VH.set(NSHTTPPropertyStatusReasonKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPPropertyServerHTTPVersionKey (Void)*
 */
private val NSHTTPPropertyServerHTTPVersionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPPropertyServerHTTPVersionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPPropertyServerHTTPVersionKey").orElseThrow() }
private val NSHTTPPropertyServerHTTPVersionKey_VH: VarHandle by lazy { NSHTTPPropertyServerHTTPVersionKey_LAYOUT.varHandle() }

var NSHTTPPropertyServerHTTPVersionKey: MemorySegment
    get() = NSHTTPPropertyServerHTTPVersionKey_VH.get(NSHTTPPropertyServerHTTPVersionKey_SEGMENT) as MemorySegment
    set(value) = NSHTTPPropertyServerHTTPVersionKey_VH.set(NSHTTPPropertyServerHTTPVersionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPPropertyRedirectionHeadersKey (Void)*
 */
private val NSHTTPPropertyRedirectionHeadersKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPPropertyRedirectionHeadersKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPPropertyRedirectionHeadersKey").orElseThrow() }
private val NSHTTPPropertyRedirectionHeadersKey_VH: VarHandle by lazy { NSHTTPPropertyRedirectionHeadersKey_LAYOUT.varHandle() }

var NSHTTPPropertyRedirectionHeadersKey: MemorySegment
    get() = NSHTTPPropertyRedirectionHeadersKey_VH.get(NSHTTPPropertyRedirectionHeadersKey_SEGMENT) as MemorySegment
    set(value) = NSHTTPPropertyRedirectionHeadersKey_VH.set(NSHTTPPropertyRedirectionHeadersKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPPropertyErrorPageDataKey (Void)*
 */
private val NSHTTPPropertyErrorPageDataKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPPropertyErrorPageDataKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPPropertyErrorPageDataKey").orElseThrow() }
private val NSHTTPPropertyErrorPageDataKey_VH: VarHandle by lazy { NSHTTPPropertyErrorPageDataKey_LAYOUT.varHandle() }

var NSHTTPPropertyErrorPageDataKey: MemorySegment
    get() = NSHTTPPropertyErrorPageDataKey_VH.get(NSHTTPPropertyErrorPageDataKey_SEGMENT) as MemorySegment
    set(value) = NSHTTPPropertyErrorPageDataKey_VH.set(NSHTTPPropertyErrorPageDataKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPPropertyHTTPProxy (Void)*
 */
private val NSHTTPPropertyHTTPProxy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPPropertyHTTPProxy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPPropertyHTTPProxy").orElseThrow() }
private val NSHTTPPropertyHTTPProxy_VH: VarHandle by lazy { NSHTTPPropertyHTTPProxy_LAYOUT.varHandle() }

var NSHTTPPropertyHTTPProxy: MemorySegment
    get() = NSHTTPPropertyHTTPProxy_VH.get(NSHTTPPropertyHTTPProxy_SEGMENT) as MemorySegment
    set(value) = NSHTTPPropertyHTTPProxy_VH.set(NSHTTPPropertyHTTPProxy_SEGMENT, value)

/**
 * {@snippet lang=c : NSFTPPropertyUserLoginKey (Void)*
 */
private val NSFTPPropertyUserLoginKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFTPPropertyUserLoginKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFTPPropertyUserLoginKey").orElseThrow() }
private val NSFTPPropertyUserLoginKey_VH: VarHandle by lazy { NSFTPPropertyUserLoginKey_LAYOUT.varHandle() }

var NSFTPPropertyUserLoginKey: MemorySegment
    get() = NSFTPPropertyUserLoginKey_VH.get(NSFTPPropertyUserLoginKey_SEGMENT) as MemorySegment
    set(value) = NSFTPPropertyUserLoginKey_VH.set(NSFTPPropertyUserLoginKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSFTPPropertyUserPasswordKey (Void)*
 */
private val NSFTPPropertyUserPasswordKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFTPPropertyUserPasswordKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFTPPropertyUserPasswordKey").orElseThrow() }
private val NSFTPPropertyUserPasswordKey_VH: VarHandle by lazy { NSFTPPropertyUserPasswordKey_LAYOUT.varHandle() }

var NSFTPPropertyUserPasswordKey: MemorySegment
    get() = NSFTPPropertyUserPasswordKey_VH.get(NSFTPPropertyUserPasswordKey_SEGMENT) as MemorySegment
    set(value) = NSFTPPropertyUserPasswordKey_VH.set(NSFTPPropertyUserPasswordKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSFTPPropertyActiveTransferModeKey (Void)*
 */
private val NSFTPPropertyActiveTransferModeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFTPPropertyActiveTransferModeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFTPPropertyActiveTransferModeKey").orElseThrow() }
private val NSFTPPropertyActiveTransferModeKey_VH: VarHandle by lazy { NSFTPPropertyActiveTransferModeKey_LAYOUT.varHandle() }

var NSFTPPropertyActiveTransferModeKey: MemorySegment
    get() = NSFTPPropertyActiveTransferModeKey_VH.get(NSFTPPropertyActiveTransferModeKey_SEGMENT) as MemorySegment
    set(value) = NSFTPPropertyActiveTransferModeKey_VH.set(NSFTPPropertyActiveTransferModeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSFTPPropertyFileOffsetKey (Void)*
 */
private val NSFTPPropertyFileOffsetKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFTPPropertyFileOffsetKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFTPPropertyFileOffsetKey").orElseThrow() }
private val NSFTPPropertyFileOffsetKey_VH: VarHandle by lazy { NSFTPPropertyFileOffsetKey_LAYOUT.varHandle() }

var NSFTPPropertyFileOffsetKey: MemorySegment
    get() = NSFTPPropertyFileOffsetKey_VH.get(NSFTPPropertyFileOffsetKey_SEGMENT) as MemorySegment
    set(value) = NSFTPPropertyFileOffsetKey_VH.set(NSFTPPropertyFileOffsetKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSFTPPropertyFTPProxy (Void)*
 */
private val NSFTPPropertyFTPProxy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFTPPropertyFTPProxy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFTPPropertyFTPProxy").orElseThrow() }
private val NSFTPPropertyFTPProxy_VH: VarHandle by lazy { NSFTPPropertyFTPProxy_LAYOUT.varHandle() }

var NSFTPPropertyFTPProxy: MemorySegment
    get() = NSFTPPropertyFTPProxy_VH.get(NSFTPPropertyFTPProxy_SEGMENT) as MemorySegment
    set(value) = NSFTPPropertyFTPProxy_VH.set(NSFTPPropertyFTPProxy_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileScheme (Void)*
 */
private val NSURLFileScheme_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileScheme_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileScheme").orElseThrow() }
private val NSURLFileScheme_VH: VarHandle by lazy { NSURLFileScheme_LAYOUT.varHandle() }

var NSURLFileScheme: MemorySegment
    get() = NSURLFileScheme_VH.get(NSURLFileScheme_SEGMENT) as MemorySegment
    set(value) = NSURLFileScheme_VH.set(NSURLFileScheme_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLKeysOfUnsetValuesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLKeysOfUnsetValuesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLKeysOfUnsetValuesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLKeysOfUnsetValuesKey").orElseThrow() }
private val NSURLKeysOfUnsetValuesKey_VH: VarHandle by lazy { NSURLKeysOfUnsetValuesKey_LAYOUT.varHandle() }

var NSURLKeysOfUnsetValuesKey: MemorySegment
    get() = NSURLKeysOfUnsetValuesKey_VH.get(NSURLKeysOfUnsetValuesKey_SEGMENT) as MemorySegment
    set(value) = NSURLKeysOfUnsetValuesKey_VH.set(NSURLKeysOfUnsetValuesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLNameKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLNameKey").orElseThrow() }
private val NSURLNameKey_VH: VarHandle by lazy { NSURLNameKey_LAYOUT.varHandle() }

var NSURLNameKey: MemorySegment
    get() = NSURLNameKey_VH.get(NSURLNameKey_SEGMENT) as MemorySegment
    set(value) = NSURLNameKey_VH.set(NSURLNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLLocalizedNameKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLLocalizedNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLLocalizedNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLLocalizedNameKey").orElseThrow() }
private val NSURLLocalizedNameKey_VH: VarHandle by lazy { NSURLLocalizedNameKey_LAYOUT.varHandle() }

var NSURLLocalizedNameKey: MemorySegment
    get() = NSURLLocalizedNameKey_VH.get(NSURLLocalizedNameKey_SEGMENT) as MemorySegment
    set(value) = NSURLLocalizedNameKey_VH.set(NSURLLocalizedNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsRegularFileKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsRegularFileKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsRegularFileKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsRegularFileKey").orElseThrow() }
private val NSURLIsRegularFileKey_VH: VarHandle by lazy { NSURLIsRegularFileKey_LAYOUT.varHandle() }

var NSURLIsRegularFileKey: MemorySegment
    get() = NSURLIsRegularFileKey_VH.get(NSURLIsRegularFileKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsRegularFileKey_VH.set(NSURLIsRegularFileKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsDirectoryKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsDirectoryKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsDirectoryKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsDirectoryKey").orElseThrow() }
private val NSURLIsDirectoryKey_VH: VarHandle by lazy { NSURLIsDirectoryKey_LAYOUT.varHandle() }

var NSURLIsDirectoryKey: MemorySegment
    get() = NSURLIsDirectoryKey_VH.get(NSURLIsDirectoryKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsDirectoryKey_VH.set(NSURLIsDirectoryKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsSymbolicLinkKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsSymbolicLinkKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsSymbolicLinkKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsSymbolicLinkKey").orElseThrow() }
private val NSURLIsSymbolicLinkKey_VH: VarHandle by lazy { NSURLIsSymbolicLinkKey_LAYOUT.varHandle() }

var NSURLIsSymbolicLinkKey: MemorySegment
    get() = NSURLIsSymbolicLinkKey_VH.get(NSURLIsSymbolicLinkKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsSymbolicLinkKey_VH.set(NSURLIsSymbolicLinkKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsVolumeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsVolumeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsVolumeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsVolumeKey").orElseThrow() }
private val NSURLIsVolumeKey_VH: VarHandle by lazy { NSURLIsVolumeKey_LAYOUT.varHandle() }

var NSURLIsVolumeKey: MemorySegment
    get() = NSURLIsVolumeKey_VH.get(NSURLIsVolumeKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsVolumeKey_VH.set(NSURLIsVolumeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsPackageKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsPackageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsPackageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsPackageKey").orElseThrow() }
private val NSURLIsPackageKey_VH: VarHandle by lazy { NSURLIsPackageKey_LAYOUT.varHandle() }

var NSURLIsPackageKey: MemorySegment
    get() = NSURLIsPackageKey_VH.get(NSURLIsPackageKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsPackageKey_VH.set(NSURLIsPackageKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsApplicationKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsApplicationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsApplicationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsApplicationKey").orElseThrow() }
private val NSURLIsApplicationKey_VH: VarHandle by lazy { NSURLIsApplicationKey_LAYOUT.varHandle() }

var NSURLIsApplicationKey: MemorySegment
    get() = NSURLIsApplicationKey_VH.get(NSURLIsApplicationKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsApplicationKey_VH.set(NSURLIsApplicationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLApplicationIsScriptableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLApplicationIsScriptableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLApplicationIsScriptableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLApplicationIsScriptableKey").orElseThrow() }
private val NSURLApplicationIsScriptableKey_VH: VarHandle by lazy { NSURLApplicationIsScriptableKey_LAYOUT.varHandle() }

var NSURLApplicationIsScriptableKey: MemorySegment
    get() = NSURLApplicationIsScriptableKey_VH.get(NSURLApplicationIsScriptableKey_SEGMENT) as MemorySegment
    set(value) = NSURLApplicationIsScriptableKey_VH.set(NSURLApplicationIsScriptableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsSystemImmutableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsSystemImmutableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsSystemImmutableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsSystemImmutableKey").orElseThrow() }
private val NSURLIsSystemImmutableKey_VH: VarHandle by lazy { NSURLIsSystemImmutableKey_LAYOUT.varHandle() }

var NSURLIsSystemImmutableKey: MemorySegment
    get() = NSURLIsSystemImmutableKey_VH.get(NSURLIsSystemImmutableKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsSystemImmutableKey_VH.set(NSURLIsSystemImmutableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsUserImmutableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsUserImmutableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsUserImmutableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsUserImmutableKey").orElseThrow() }
private val NSURLIsUserImmutableKey_VH: VarHandle by lazy { NSURLIsUserImmutableKey_LAYOUT.varHandle() }

var NSURLIsUserImmutableKey: MemorySegment
    get() = NSURLIsUserImmutableKey_VH.get(NSURLIsUserImmutableKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsUserImmutableKey_VH.set(NSURLIsUserImmutableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsHiddenKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsHiddenKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsHiddenKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsHiddenKey").orElseThrow() }
private val NSURLIsHiddenKey_VH: VarHandle by lazy { NSURLIsHiddenKey_LAYOUT.varHandle() }

var NSURLIsHiddenKey: MemorySegment
    get() = NSURLIsHiddenKey_VH.get(NSURLIsHiddenKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsHiddenKey_VH.set(NSURLIsHiddenKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLHasHiddenExtensionKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLHasHiddenExtensionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLHasHiddenExtensionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLHasHiddenExtensionKey").orElseThrow() }
private val NSURLHasHiddenExtensionKey_VH: VarHandle by lazy { NSURLHasHiddenExtensionKey_LAYOUT.varHandle() }

var NSURLHasHiddenExtensionKey: MemorySegment
    get() = NSURLHasHiddenExtensionKey_VH.get(NSURLHasHiddenExtensionKey_SEGMENT) as MemorySegment
    set(value) = NSURLHasHiddenExtensionKey_VH.set(NSURLHasHiddenExtensionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLCreationDateKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLCreationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLCreationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLCreationDateKey").orElseThrow() }
private val NSURLCreationDateKey_VH: VarHandle by lazy { NSURLCreationDateKey_LAYOUT.varHandle() }

var NSURLCreationDateKey: MemorySegment
    get() = NSURLCreationDateKey_VH.get(NSURLCreationDateKey_SEGMENT) as MemorySegment
    set(value) = NSURLCreationDateKey_VH.set(NSURLCreationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLContentAccessDateKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLContentAccessDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLContentAccessDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLContentAccessDateKey").orElseThrow() }
private val NSURLContentAccessDateKey_VH: VarHandle by lazy { NSURLContentAccessDateKey_LAYOUT.varHandle() }

var NSURLContentAccessDateKey: MemorySegment
    get() = NSURLContentAccessDateKey_VH.get(NSURLContentAccessDateKey_SEGMENT) as MemorySegment
    set(value) = NSURLContentAccessDateKey_VH.set(NSURLContentAccessDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLContentModificationDateKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLContentModificationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLContentModificationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLContentModificationDateKey").orElseThrow() }
private val NSURLContentModificationDateKey_VH: VarHandle by lazy { NSURLContentModificationDateKey_LAYOUT.varHandle() }

var NSURLContentModificationDateKey: MemorySegment
    get() = NSURLContentModificationDateKey_VH.get(NSURLContentModificationDateKey_SEGMENT) as MemorySegment
    set(value) = NSURLContentModificationDateKey_VH.set(NSURLContentModificationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAttributeModificationDateKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLAttributeModificationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAttributeModificationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAttributeModificationDateKey").orElseThrow() }
private val NSURLAttributeModificationDateKey_VH: VarHandle by lazy { NSURLAttributeModificationDateKey_LAYOUT.varHandle() }

var NSURLAttributeModificationDateKey: MemorySegment
    get() = NSURLAttributeModificationDateKey_VH.get(NSURLAttributeModificationDateKey_SEGMENT) as MemorySegment
    set(value) = NSURLAttributeModificationDateKey_VH.set(NSURLAttributeModificationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLLinkCountKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLLinkCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLLinkCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLLinkCountKey").orElseThrow() }
private val NSURLLinkCountKey_VH: VarHandle by lazy { NSURLLinkCountKey_LAYOUT.varHandle() }

var NSURLLinkCountKey: MemorySegment
    get() = NSURLLinkCountKey_VH.get(NSURLLinkCountKey_SEGMENT) as MemorySegment
    set(value) = NSURLLinkCountKey_VH.set(NSURLLinkCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLParentDirectoryURLKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLParentDirectoryURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLParentDirectoryURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLParentDirectoryURLKey").orElseThrow() }
private val NSURLParentDirectoryURLKey_VH: VarHandle by lazy { NSURLParentDirectoryURLKey_LAYOUT.varHandle() }

var NSURLParentDirectoryURLKey: MemorySegment
    get() = NSURLParentDirectoryURLKey_VH.get(NSURLParentDirectoryURLKey_SEGMENT) as MemorySegment
    set(value) = NSURLParentDirectoryURLKey_VH.set(NSURLParentDirectoryURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeURLKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeURLKey").orElseThrow() }
private val NSURLVolumeURLKey_VH: VarHandle by lazy { NSURLVolumeURLKey_LAYOUT.varHandle() }

var NSURLVolumeURLKey: MemorySegment
    get() = NSURLVolumeURLKey_VH.get(NSURLVolumeURLKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeURLKey_VH.set(NSURLVolumeURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLTypeIdentifierKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLTypeIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLTypeIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLTypeIdentifierKey").orElseThrow() }
private val NSURLTypeIdentifierKey_VH: VarHandle by lazy { NSURLTypeIdentifierKey_LAYOUT.varHandle() }

var NSURLTypeIdentifierKey: MemorySegment
    get() = NSURLTypeIdentifierKey_VH.get(NSURLTypeIdentifierKey_SEGMENT) as MemorySegment
    set(value) = NSURLTypeIdentifierKey_VH.set(NSURLTypeIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLContentTypeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLContentTypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLContentTypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLContentTypeKey").orElseThrow() }
private val NSURLContentTypeKey_VH: VarHandle by lazy { NSURLContentTypeKey_LAYOUT.varHandle() }

var NSURLContentTypeKey: MemorySegment
    get() = NSURLContentTypeKey_VH.get(NSURLContentTypeKey_SEGMENT) as MemorySegment
    set(value) = NSURLContentTypeKey_VH.set(NSURLContentTypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLLocalizedTypeDescriptionKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLLocalizedTypeDescriptionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLLocalizedTypeDescriptionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLLocalizedTypeDescriptionKey").orElseThrow() }
private val NSURLLocalizedTypeDescriptionKey_VH: VarHandle by lazy { NSURLLocalizedTypeDescriptionKey_LAYOUT.varHandle() }

var NSURLLocalizedTypeDescriptionKey: MemorySegment
    get() = NSURLLocalizedTypeDescriptionKey_VH.get(NSURLLocalizedTypeDescriptionKey_SEGMENT) as MemorySegment
    set(value) = NSURLLocalizedTypeDescriptionKey_VH.set(NSURLLocalizedTypeDescriptionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLLabelNumberKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLLabelNumberKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLLabelNumberKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLLabelNumberKey").orElseThrow() }
private val NSURLLabelNumberKey_VH: VarHandle by lazy { NSURLLabelNumberKey_LAYOUT.varHandle() }

var NSURLLabelNumberKey: MemorySegment
    get() = NSURLLabelNumberKey_VH.get(NSURLLabelNumberKey_SEGMENT) as MemorySegment
    set(value) = NSURLLabelNumberKey_VH.set(NSURLLabelNumberKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLLabelColorKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLLabelColorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLLabelColorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLLabelColorKey").orElseThrow() }
private val NSURLLabelColorKey_VH: VarHandle by lazy { NSURLLabelColorKey_LAYOUT.varHandle() }

var NSURLLabelColorKey: MemorySegment
    get() = NSURLLabelColorKey_VH.get(NSURLLabelColorKey_SEGMENT) as MemorySegment
    set(value) = NSURLLabelColorKey_VH.set(NSURLLabelColorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLLocalizedLabelKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLLocalizedLabelKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLLocalizedLabelKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLLocalizedLabelKey").orElseThrow() }
private val NSURLLocalizedLabelKey_VH: VarHandle by lazy { NSURLLocalizedLabelKey_LAYOUT.varHandle() }

var NSURLLocalizedLabelKey: MemorySegment
    get() = NSURLLocalizedLabelKey_VH.get(NSURLLocalizedLabelKey_SEGMENT) as MemorySegment
    set(value) = NSURLLocalizedLabelKey_VH.set(NSURLLocalizedLabelKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLEffectiveIconKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLEffectiveIconKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLEffectiveIconKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLEffectiveIconKey").orElseThrow() }
private val NSURLEffectiveIconKey_VH: VarHandle by lazy { NSURLEffectiveIconKey_LAYOUT.varHandle() }

var NSURLEffectiveIconKey: MemorySegment
    get() = NSURLEffectiveIconKey_VH.get(NSURLEffectiveIconKey_SEGMENT) as MemorySegment
    set(value) = NSURLEffectiveIconKey_VH.set(NSURLEffectiveIconKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLCustomIconKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLCustomIconKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLCustomIconKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLCustomIconKey").orElseThrow() }
private val NSURLCustomIconKey_VH: VarHandle by lazy { NSURLCustomIconKey_LAYOUT.varHandle() }

var NSURLCustomIconKey: MemorySegment
    get() = NSURLCustomIconKey_VH.get(NSURLCustomIconKey_SEGMENT) as MemorySegment
    set(value) = NSURLCustomIconKey_VH.set(NSURLCustomIconKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceIdentifierKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLFileResourceIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceIdentifierKey").orElseThrow() }
private val NSURLFileResourceIdentifierKey_VH: VarHandle by lazy { NSURLFileResourceIdentifierKey_LAYOUT.varHandle() }

var NSURLFileResourceIdentifierKey: MemorySegment
    get() = NSURLFileResourceIdentifierKey_VH.get(NSURLFileResourceIdentifierKey_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceIdentifierKey_VH.set(NSURLFileResourceIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIdentifierKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIdentifierKey").orElseThrow() }
private val NSURLVolumeIdentifierKey_VH: VarHandle by lazy { NSURLVolumeIdentifierKey_LAYOUT.varHandle() }

var NSURLVolumeIdentifierKey: MemorySegment
    get() = NSURLVolumeIdentifierKey_VH.get(NSURLVolumeIdentifierKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIdentifierKey_VH.set(NSURLVolumeIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLPreferredIOBlockSizeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLPreferredIOBlockSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLPreferredIOBlockSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLPreferredIOBlockSizeKey").orElseThrow() }
private val NSURLPreferredIOBlockSizeKey_VH: VarHandle by lazy { NSURLPreferredIOBlockSizeKey_LAYOUT.varHandle() }

var NSURLPreferredIOBlockSizeKey: MemorySegment
    get() = NSURLPreferredIOBlockSizeKey_VH.get(NSURLPreferredIOBlockSizeKey_SEGMENT) as MemorySegment
    set(value) = NSURLPreferredIOBlockSizeKey_VH.set(NSURLPreferredIOBlockSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsReadableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsReadableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsReadableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsReadableKey").orElseThrow() }
private val NSURLIsReadableKey_VH: VarHandle by lazy { NSURLIsReadableKey_LAYOUT.varHandle() }

var NSURLIsReadableKey: MemorySegment
    get() = NSURLIsReadableKey_VH.get(NSURLIsReadableKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsReadableKey_VH.set(NSURLIsReadableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsWritableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsWritableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsWritableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsWritableKey").orElseThrow() }
private val NSURLIsWritableKey_VH: VarHandle by lazy { NSURLIsWritableKey_LAYOUT.varHandle() }

var NSURLIsWritableKey: MemorySegment
    get() = NSURLIsWritableKey_VH.get(NSURLIsWritableKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsWritableKey_VH.set(NSURLIsWritableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsExecutableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsExecutableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsExecutableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsExecutableKey").orElseThrow() }
private val NSURLIsExecutableKey_VH: VarHandle by lazy { NSURLIsExecutableKey_LAYOUT.varHandle() }

var NSURLIsExecutableKey: MemorySegment
    get() = NSURLIsExecutableKey_VH.get(NSURLIsExecutableKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsExecutableKey_VH.set(NSURLIsExecutableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileSecurityKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLFileSecurityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileSecurityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileSecurityKey").orElseThrow() }
private val NSURLFileSecurityKey_VH: VarHandle by lazy { NSURLFileSecurityKey_LAYOUT.varHandle() }

var NSURLFileSecurityKey: MemorySegment
    get() = NSURLFileSecurityKey_VH.get(NSURLFileSecurityKey_SEGMENT) as MemorySegment
    set(value) = NSURLFileSecurityKey_VH.set(NSURLFileSecurityKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsExcludedFromBackupKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsExcludedFromBackupKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsExcludedFromBackupKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsExcludedFromBackupKey").orElseThrow() }
private val NSURLIsExcludedFromBackupKey_VH: VarHandle by lazy { NSURLIsExcludedFromBackupKey_LAYOUT.varHandle() }

var NSURLIsExcludedFromBackupKey: MemorySegment
    get() = NSURLIsExcludedFromBackupKey_VH.get(NSURLIsExcludedFromBackupKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsExcludedFromBackupKey_VH.set(NSURLIsExcludedFromBackupKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLTagNamesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLTagNamesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLTagNamesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLTagNamesKey").orElseThrow() }
private val NSURLTagNamesKey_VH: VarHandle by lazy { NSURLTagNamesKey_LAYOUT.varHandle() }

var NSURLTagNamesKey: MemorySegment
    get() = NSURLTagNamesKey_VH.get(NSURLTagNamesKey_SEGMENT) as MemorySegment
    set(value) = NSURLTagNamesKey_VH.set(NSURLTagNamesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLPathKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLPathKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLPathKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLPathKey").orElseThrow() }
private val NSURLPathKey_VH: VarHandle by lazy { NSURLPathKey_LAYOUT.varHandle() }

var NSURLPathKey: MemorySegment
    get() = NSURLPathKey_VH.get(NSURLPathKey_SEGMENT) as MemorySegment
    set(value) = NSURLPathKey_VH.set(NSURLPathKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLCanonicalPathKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLCanonicalPathKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLCanonicalPathKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLCanonicalPathKey").orElseThrow() }
private val NSURLCanonicalPathKey_VH: VarHandle by lazy { NSURLCanonicalPathKey_LAYOUT.varHandle() }

var NSURLCanonicalPathKey: MemorySegment
    get() = NSURLCanonicalPathKey_VH.get(NSURLCanonicalPathKey_SEGMENT) as MemorySegment
    set(value) = NSURLCanonicalPathKey_VH.set(NSURLCanonicalPathKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsMountTriggerKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsMountTriggerKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsMountTriggerKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsMountTriggerKey").orElseThrow() }
private val NSURLIsMountTriggerKey_VH: VarHandle by lazy { NSURLIsMountTriggerKey_LAYOUT.varHandle() }

var NSURLIsMountTriggerKey: MemorySegment
    get() = NSURLIsMountTriggerKey_VH.get(NSURLIsMountTriggerKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsMountTriggerKey_VH.set(NSURLIsMountTriggerKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLGenerationIdentifierKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLGenerationIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLGenerationIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLGenerationIdentifierKey").orElseThrow() }
private val NSURLGenerationIdentifierKey_VH: VarHandle by lazy { NSURLGenerationIdentifierKey_LAYOUT.varHandle() }

var NSURLGenerationIdentifierKey: MemorySegment
    get() = NSURLGenerationIdentifierKey_VH.get(NSURLGenerationIdentifierKey_SEGMENT) as MemorySegment
    set(value) = NSURLGenerationIdentifierKey_VH.set(NSURLGenerationIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLDocumentIdentifierKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLDocumentIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLDocumentIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLDocumentIdentifierKey").orElseThrow() }
private val NSURLDocumentIdentifierKey_VH: VarHandle by lazy { NSURLDocumentIdentifierKey_LAYOUT.varHandle() }

var NSURLDocumentIdentifierKey: MemorySegment
    get() = NSURLDocumentIdentifierKey_VH.get(NSURLDocumentIdentifierKey_SEGMENT) as MemorySegment
    set(value) = NSURLDocumentIdentifierKey_VH.set(NSURLDocumentIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAddedToDirectoryDateKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLAddedToDirectoryDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAddedToDirectoryDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAddedToDirectoryDateKey").orElseThrow() }
private val NSURLAddedToDirectoryDateKey_VH: VarHandle by lazy { NSURLAddedToDirectoryDateKey_LAYOUT.varHandle() }

var NSURLAddedToDirectoryDateKey: MemorySegment
    get() = NSURLAddedToDirectoryDateKey_VH.get(NSURLAddedToDirectoryDateKey_SEGMENT) as MemorySegment
    set(value) = NSURLAddedToDirectoryDateKey_VH.set(NSURLAddedToDirectoryDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLQuarantinePropertiesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLQuarantinePropertiesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLQuarantinePropertiesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLQuarantinePropertiesKey").orElseThrow() }
private val NSURLQuarantinePropertiesKey_VH: VarHandle by lazy { NSURLQuarantinePropertiesKey_LAYOUT.varHandle() }

var NSURLQuarantinePropertiesKey: MemorySegment
    get() = NSURLQuarantinePropertiesKey_VH.get(NSURLQuarantinePropertiesKey_SEGMENT) as MemorySegment
    set(value) = NSURLQuarantinePropertiesKey_VH.set(NSURLQuarantinePropertiesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceTypeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLFileResourceTypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceTypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceTypeKey").orElseThrow() }
private val NSURLFileResourceTypeKey_VH: VarHandle by lazy { NSURLFileResourceTypeKey_LAYOUT.varHandle() }

var NSURLFileResourceTypeKey: MemorySegment
    get() = NSURLFileResourceTypeKey_VH.get(NSURLFileResourceTypeKey_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceTypeKey_VH.set(NSURLFileResourceTypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileIdentifierKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLFileIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileIdentifierKey").orElseThrow() }
private val NSURLFileIdentifierKey_VH: VarHandle by lazy { NSURLFileIdentifierKey_LAYOUT.varHandle() }

var NSURLFileIdentifierKey: MemorySegment
    get() = NSURLFileIdentifierKey_VH.get(NSURLFileIdentifierKey_SEGMENT) as MemorySegment
    set(value) = NSURLFileIdentifierKey_VH.set(NSURLFileIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileContentIdentifierKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLFileContentIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileContentIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileContentIdentifierKey").orElseThrow() }
private val NSURLFileContentIdentifierKey_VH: VarHandle by lazy { NSURLFileContentIdentifierKey_LAYOUT.varHandle() }

var NSURLFileContentIdentifierKey: MemorySegment
    get() = NSURLFileContentIdentifierKey_VH.get(NSURLFileContentIdentifierKey_SEGMENT) as MemorySegment
    set(value) = NSURLFileContentIdentifierKey_VH.set(NSURLFileContentIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLMayShareFileContentKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLMayShareFileContentKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLMayShareFileContentKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLMayShareFileContentKey").orElseThrow() }
private val NSURLMayShareFileContentKey_VH: VarHandle by lazy { NSURLMayShareFileContentKey_LAYOUT.varHandle() }

var NSURLMayShareFileContentKey: MemorySegment
    get() = NSURLMayShareFileContentKey_VH.get(NSURLMayShareFileContentKey_SEGMENT) as MemorySegment
    set(value) = NSURLMayShareFileContentKey_VH.set(NSURLMayShareFileContentKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLMayHaveExtendedAttributesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLMayHaveExtendedAttributesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLMayHaveExtendedAttributesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLMayHaveExtendedAttributesKey").orElseThrow() }
private val NSURLMayHaveExtendedAttributesKey_VH: VarHandle by lazy { NSURLMayHaveExtendedAttributesKey_LAYOUT.varHandle() }

var NSURLMayHaveExtendedAttributesKey: MemorySegment
    get() = NSURLMayHaveExtendedAttributesKey_VH.get(NSURLMayHaveExtendedAttributesKey_SEGMENT) as MemorySegment
    set(value) = NSURLMayHaveExtendedAttributesKey_VH.set(NSURLMayHaveExtendedAttributesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsPurgeableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsPurgeableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsPurgeableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsPurgeableKey").orElseThrow() }
private val NSURLIsPurgeableKey_VH: VarHandle by lazy { NSURLIsPurgeableKey_LAYOUT.varHandle() }

var NSURLIsPurgeableKey: MemorySegment
    get() = NSURLIsPurgeableKey_VH.get(NSURLIsPurgeableKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsPurgeableKey_VH.set(NSURLIsPurgeableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsSparseKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsSparseKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsSparseKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsSparseKey").orElseThrow() }
private val NSURLIsSparseKey_VH: VarHandle by lazy { NSURLIsSparseKey_LAYOUT.varHandle() }

var NSURLIsSparseKey: MemorySegment
    get() = NSURLIsSparseKey_VH.get(NSURLIsSparseKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsSparseKey_VH.set(NSURLIsSparseKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceTypeNamedPipe typedef const NSURLFileResourceType = (Void)*
 */
private val NSURLFileResourceTypeNamedPipe_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceTypeNamedPipe_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceTypeNamedPipe").orElseThrow() }
private val NSURLFileResourceTypeNamedPipe_VH: VarHandle by lazy { NSURLFileResourceTypeNamedPipe_LAYOUT.varHandle() }

var NSURLFileResourceTypeNamedPipe: MemorySegment
    get() = NSURLFileResourceTypeNamedPipe_VH.get(NSURLFileResourceTypeNamedPipe_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceTypeNamedPipe_VH.set(NSURLFileResourceTypeNamedPipe_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceTypeCharacterSpecial typedef const NSURLFileResourceType = (Void)*
 */
private val NSURLFileResourceTypeCharacterSpecial_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceTypeCharacterSpecial_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceTypeCharacterSpecial").orElseThrow() }
private val NSURLFileResourceTypeCharacterSpecial_VH: VarHandle by lazy { NSURLFileResourceTypeCharacterSpecial_LAYOUT.varHandle() }

var NSURLFileResourceTypeCharacterSpecial: MemorySegment
    get() = NSURLFileResourceTypeCharacterSpecial_VH.get(NSURLFileResourceTypeCharacterSpecial_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceTypeCharacterSpecial_VH.set(NSURLFileResourceTypeCharacterSpecial_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceTypeDirectory typedef const NSURLFileResourceType = (Void)*
 */
private val NSURLFileResourceTypeDirectory_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceTypeDirectory_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceTypeDirectory").orElseThrow() }
private val NSURLFileResourceTypeDirectory_VH: VarHandle by lazy { NSURLFileResourceTypeDirectory_LAYOUT.varHandle() }

var NSURLFileResourceTypeDirectory: MemorySegment
    get() = NSURLFileResourceTypeDirectory_VH.get(NSURLFileResourceTypeDirectory_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceTypeDirectory_VH.set(NSURLFileResourceTypeDirectory_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceTypeBlockSpecial typedef const NSURLFileResourceType = (Void)*
 */
private val NSURLFileResourceTypeBlockSpecial_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceTypeBlockSpecial_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceTypeBlockSpecial").orElseThrow() }
private val NSURLFileResourceTypeBlockSpecial_VH: VarHandle by lazy { NSURLFileResourceTypeBlockSpecial_LAYOUT.varHandle() }

var NSURLFileResourceTypeBlockSpecial: MemorySegment
    get() = NSURLFileResourceTypeBlockSpecial_VH.get(NSURLFileResourceTypeBlockSpecial_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceTypeBlockSpecial_VH.set(NSURLFileResourceTypeBlockSpecial_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceTypeRegular typedef const NSURLFileResourceType = (Void)*
 */
private val NSURLFileResourceTypeRegular_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceTypeRegular_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceTypeRegular").orElseThrow() }
private val NSURLFileResourceTypeRegular_VH: VarHandle by lazy { NSURLFileResourceTypeRegular_LAYOUT.varHandle() }

var NSURLFileResourceTypeRegular: MemorySegment
    get() = NSURLFileResourceTypeRegular_VH.get(NSURLFileResourceTypeRegular_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceTypeRegular_VH.set(NSURLFileResourceTypeRegular_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceTypeSymbolicLink typedef const NSURLFileResourceType = (Void)*
 */
private val NSURLFileResourceTypeSymbolicLink_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceTypeSymbolicLink_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceTypeSymbolicLink").orElseThrow() }
private val NSURLFileResourceTypeSymbolicLink_VH: VarHandle by lazy { NSURLFileResourceTypeSymbolicLink_LAYOUT.varHandle() }

var NSURLFileResourceTypeSymbolicLink: MemorySegment
    get() = NSURLFileResourceTypeSymbolicLink_VH.get(NSURLFileResourceTypeSymbolicLink_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceTypeSymbolicLink_VH.set(NSURLFileResourceTypeSymbolicLink_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceTypeSocket typedef const NSURLFileResourceType = (Void)*
 */
private val NSURLFileResourceTypeSocket_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceTypeSocket_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceTypeSocket").orElseThrow() }
private val NSURLFileResourceTypeSocket_VH: VarHandle by lazy { NSURLFileResourceTypeSocket_LAYOUT.varHandle() }

var NSURLFileResourceTypeSocket: MemorySegment
    get() = NSURLFileResourceTypeSocket_VH.get(NSURLFileResourceTypeSocket_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceTypeSocket_VH.set(NSURLFileResourceTypeSocket_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileResourceTypeUnknown typedef const NSURLFileResourceType = (Void)*
 */
private val NSURLFileResourceTypeUnknown_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileResourceTypeUnknown_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileResourceTypeUnknown").orElseThrow() }
private val NSURLFileResourceTypeUnknown_VH: VarHandle by lazy { NSURLFileResourceTypeUnknown_LAYOUT.varHandle() }

var NSURLFileResourceTypeUnknown: MemorySegment
    get() = NSURLFileResourceTypeUnknown_VH.get(NSURLFileResourceTypeUnknown_SEGMENT) as MemorySegment
    set(value) = NSURLFileResourceTypeUnknown_VH.set(NSURLFileResourceTypeUnknown_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLThumbnailDictionaryKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLThumbnailDictionaryKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLThumbnailDictionaryKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLThumbnailDictionaryKey").orElseThrow() }
private val NSURLThumbnailDictionaryKey_VH: VarHandle by lazy { NSURLThumbnailDictionaryKey_LAYOUT.varHandle() }

var NSURLThumbnailDictionaryKey: MemorySegment
    get() = NSURLThumbnailDictionaryKey_VH.get(NSURLThumbnailDictionaryKey_SEGMENT) as MemorySegment
    set(value) = NSURLThumbnailDictionaryKey_VH.set(NSURLThumbnailDictionaryKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLThumbnailKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLThumbnailKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLThumbnailKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLThumbnailKey").orElseThrow() }
private val NSURLThumbnailKey_VH: VarHandle by lazy { NSURLThumbnailKey_LAYOUT.varHandle() }

var NSURLThumbnailKey: MemorySegment
    get() = NSURLThumbnailKey_VH.get(NSURLThumbnailKey_SEGMENT) as MemorySegment
    set(value) = NSURLThumbnailKey_VH.set(NSURLThumbnailKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSThumbnail1024x1024SizeKey typedef const NSURLThumbnailDictionaryItem = (Void)*
 */
private val NSThumbnail1024x1024SizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSThumbnail1024x1024SizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSThumbnail1024x1024SizeKey").orElseThrow() }
private val NSThumbnail1024x1024SizeKey_VH: VarHandle by lazy { NSThumbnail1024x1024SizeKey_LAYOUT.varHandle() }

var NSThumbnail1024x1024SizeKey: MemorySegment
    get() = NSThumbnail1024x1024SizeKey_VH.get(NSThumbnail1024x1024SizeKey_SEGMENT) as MemorySegment
    set(value) = NSThumbnail1024x1024SizeKey_VH.set(NSThumbnail1024x1024SizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileSizeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLFileSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileSizeKey").orElseThrow() }
private val NSURLFileSizeKey_VH: VarHandle by lazy { NSURLFileSizeKey_LAYOUT.varHandle() }

var NSURLFileSizeKey: MemorySegment
    get() = NSURLFileSizeKey_VH.get(NSURLFileSizeKey_SEGMENT) as MemorySegment
    set(value) = NSURLFileSizeKey_VH.set(NSURLFileSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileAllocatedSizeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLFileAllocatedSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileAllocatedSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileAllocatedSizeKey").orElseThrow() }
private val NSURLFileAllocatedSizeKey_VH: VarHandle by lazy { NSURLFileAllocatedSizeKey_LAYOUT.varHandle() }

var NSURLFileAllocatedSizeKey: MemorySegment
    get() = NSURLFileAllocatedSizeKey_VH.get(NSURLFileAllocatedSizeKey_SEGMENT) as MemorySegment
    set(value) = NSURLFileAllocatedSizeKey_VH.set(NSURLFileAllocatedSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLTotalFileSizeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLTotalFileSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLTotalFileSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLTotalFileSizeKey").orElseThrow() }
private val NSURLTotalFileSizeKey_VH: VarHandle by lazy { NSURLTotalFileSizeKey_LAYOUT.varHandle() }

var NSURLTotalFileSizeKey: MemorySegment
    get() = NSURLTotalFileSizeKey_VH.get(NSURLTotalFileSizeKey_SEGMENT) as MemorySegment
    set(value) = NSURLTotalFileSizeKey_VH.set(NSURLTotalFileSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLTotalFileAllocatedSizeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLTotalFileAllocatedSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLTotalFileAllocatedSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLTotalFileAllocatedSizeKey").orElseThrow() }
private val NSURLTotalFileAllocatedSizeKey_VH: VarHandle by lazy { NSURLTotalFileAllocatedSizeKey_LAYOUT.varHandle() }

var NSURLTotalFileAllocatedSizeKey: MemorySegment
    get() = NSURLTotalFileAllocatedSizeKey_VH.get(NSURLTotalFileAllocatedSizeKey_SEGMENT) as MemorySegment
    set(value) = NSURLTotalFileAllocatedSizeKey_VH.set(NSURLTotalFileAllocatedSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsAliasFileKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsAliasFileKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsAliasFileKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsAliasFileKey").orElseThrow() }
private val NSURLIsAliasFileKey_VH: VarHandle by lazy { NSURLIsAliasFileKey_LAYOUT.varHandle() }

var NSURLIsAliasFileKey: MemorySegment
    get() = NSURLIsAliasFileKey_VH.get(NSURLIsAliasFileKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsAliasFileKey_VH.set(NSURLIsAliasFileKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileProtectionKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLFileProtectionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileProtectionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileProtectionKey").orElseThrow() }
private val NSURLFileProtectionKey_VH: VarHandle by lazy { NSURLFileProtectionKey_LAYOUT.varHandle() }

var NSURLFileProtectionKey: MemorySegment
    get() = NSURLFileProtectionKey_VH.get(NSURLFileProtectionKey_SEGMENT) as MemorySegment
    set(value) = NSURLFileProtectionKey_VH.set(NSURLFileProtectionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileProtectionNone typedef const NSURLFileProtectionType = (Void)*
 */
private val NSURLFileProtectionNone_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileProtectionNone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileProtectionNone").orElseThrow() }
private val NSURLFileProtectionNone_VH: VarHandle by lazy { NSURLFileProtectionNone_LAYOUT.varHandle() }

var NSURLFileProtectionNone: MemorySegment
    get() = NSURLFileProtectionNone_VH.get(NSURLFileProtectionNone_SEGMENT) as MemorySegment
    set(value) = NSURLFileProtectionNone_VH.set(NSURLFileProtectionNone_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileProtectionComplete typedef const NSURLFileProtectionType = (Void)*
 */
private val NSURLFileProtectionComplete_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileProtectionComplete_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileProtectionComplete").orElseThrow() }
private val NSURLFileProtectionComplete_VH: VarHandle by lazy { NSURLFileProtectionComplete_LAYOUT.varHandle() }

var NSURLFileProtectionComplete: MemorySegment
    get() = NSURLFileProtectionComplete_VH.get(NSURLFileProtectionComplete_SEGMENT) as MemorySegment
    set(value) = NSURLFileProtectionComplete_VH.set(NSURLFileProtectionComplete_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileProtectionCompleteUnlessOpen typedef const NSURLFileProtectionType = (Void)*
 */
private val NSURLFileProtectionCompleteUnlessOpen_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileProtectionCompleteUnlessOpen_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileProtectionCompleteUnlessOpen").orElseThrow() }
private val NSURLFileProtectionCompleteUnlessOpen_VH: VarHandle by lazy { NSURLFileProtectionCompleteUnlessOpen_LAYOUT.varHandle() }

var NSURLFileProtectionCompleteUnlessOpen: MemorySegment
    get() = NSURLFileProtectionCompleteUnlessOpen_VH.get(NSURLFileProtectionCompleteUnlessOpen_SEGMENT) as MemorySegment
    set(value) = NSURLFileProtectionCompleteUnlessOpen_VH.set(NSURLFileProtectionCompleteUnlessOpen_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileProtectionCompleteUntilFirstUserAuthentication typedef const NSURLFileProtectionType = (Void)*
 */
private val NSURLFileProtectionCompleteUntilFirstUserAuthentication_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileProtectionCompleteUntilFirstUserAuthentication_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileProtectionCompleteUntilFirstUserAuthentication").orElseThrow() }
private val NSURLFileProtectionCompleteUntilFirstUserAuthentication_VH: VarHandle by lazy { NSURLFileProtectionCompleteUntilFirstUserAuthentication_LAYOUT.varHandle() }

var NSURLFileProtectionCompleteUntilFirstUserAuthentication: MemorySegment
    get() = NSURLFileProtectionCompleteUntilFirstUserAuthentication_VH.get(NSURLFileProtectionCompleteUntilFirstUserAuthentication_SEGMENT) as MemorySegment
    set(value) = NSURLFileProtectionCompleteUntilFirstUserAuthentication_VH.set(NSURLFileProtectionCompleteUntilFirstUserAuthentication_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLFileProtectionCompleteWhenUserInactive typedef const NSURLFileProtectionType = (Void)*
 */
private val NSURLFileProtectionCompleteWhenUserInactive_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLFileProtectionCompleteWhenUserInactive_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLFileProtectionCompleteWhenUserInactive").orElseThrow() }
private val NSURLFileProtectionCompleteWhenUserInactive_VH: VarHandle by lazy { NSURLFileProtectionCompleteWhenUserInactive_LAYOUT.varHandle() }

var NSURLFileProtectionCompleteWhenUserInactive: MemorySegment
    get() = NSURLFileProtectionCompleteWhenUserInactive_VH.get(NSURLFileProtectionCompleteWhenUserInactive_SEGMENT) as MemorySegment
    set(value) = NSURLFileProtectionCompleteWhenUserInactive_VH.set(NSURLFileProtectionCompleteWhenUserInactive_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLDirectoryEntryCountKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLDirectoryEntryCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLDirectoryEntryCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLDirectoryEntryCountKey").orElseThrow() }
private val NSURLDirectoryEntryCountKey_VH: VarHandle by lazy { NSURLDirectoryEntryCountKey_LAYOUT.varHandle() }

var NSURLDirectoryEntryCountKey: MemorySegment
    get() = NSURLDirectoryEntryCountKey_VH.get(NSURLDirectoryEntryCountKey_SEGMENT) as MemorySegment
    set(value) = NSURLDirectoryEntryCountKey_VH.set(NSURLDirectoryEntryCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeLocalizedFormatDescriptionKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeLocalizedFormatDescriptionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeLocalizedFormatDescriptionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeLocalizedFormatDescriptionKey").orElseThrow() }
private val NSURLVolumeLocalizedFormatDescriptionKey_VH: VarHandle by lazy { NSURLVolumeLocalizedFormatDescriptionKey_LAYOUT.varHandle() }

var NSURLVolumeLocalizedFormatDescriptionKey: MemorySegment
    get() = NSURLVolumeLocalizedFormatDescriptionKey_VH.get(NSURLVolumeLocalizedFormatDescriptionKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeLocalizedFormatDescriptionKey_VH.set(NSURLVolumeLocalizedFormatDescriptionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeTotalCapacityKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeTotalCapacityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeTotalCapacityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeTotalCapacityKey").orElseThrow() }
private val NSURLVolumeTotalCapacityKey_VH: VarHandle by lazy { NSURLVolumeTotalCapacityKey_LAYOUT.varHandle() }

var NSURLVolumeTotalCapacityKey: MemorySegment
    get() = NSURLVolumeTotalCapacityKey_VH.get(NSURLVolumeTotalCapacityKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeTotalCapacityKey_VH.set(NSURLVolumeTotalCapacityKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeAvailableCapacityKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeAvailableCapacityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeAvailableCapacityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeAvailableCapacityKey").orElseThrow() }
private val NSURLVolumeAvailableCapacityKey_VH: VarHandle by lazy { NSURLVolumeAvailableCapacityKey_LAYOUT.varHandle() }

var NSURLVolumeAvailableCapacityKey: MemorySegment
    get() = NSURLVolumeAvailableCapacityKey_VH.get(NSURLVolumeAvailableCapacityKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeAvailableCapacityKey_VH.set(NSURLVolumeAvailableCapacityKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeResourceCountKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeResourceCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeResourceCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeResourceCountKey").orElseThrow() }
private val NSURLVolumeResourceCountKey_VH: VarHandle by lazy { NSURLVolumeResourceCountKey_LAYOUT.varHandle() }

var NSURLVolumeResourceCountKey: MemorySegment
    get() = NSURLVolumeResourceCountKey_VH.get(NSURLVolumeResourceCountKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeResourceCountKey_VH.set(NSURLVolumeResourceCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsPersistentIDsKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsPersistentIDsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsPersistentIDsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsPersistentIDsKey").orElseThrow() }
private val NSURLVolumeSupportsPersistentIDsKey_VH: VarHandle by lazy { NSURLVolumeSupportsPersistentIDsKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsPersistentIDsKey: MemorySegment
    get() = NSURLVolumeSupportsPersistentIDsKey_VH.get(NSURLVolumeSupportsPersistentIDsKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsPersistentIDsKey_VH.set(NSURLVolumeSupportsPersistentIDsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsSymbolicLinksKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsSymbolicLinksKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsSymbolicLinksKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsSymbolicLinksKey").orElseThrow() }
private val NSURLVolumeSupportsSymbolicLinksKey_VH: VarHandle by lazy { NSURLVolumeSupportsSymbolicLinksKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsSymbolicLinksKey: MemorySegment
    get() = NSURLVolumeSupportsSymbolicLinksKey_VH.get(NSURLVolumeSupportsSymbolicLinksKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsSymbolicLinksKey_VH.set(NSURLVolumeSupportsSymbolicLinksKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsHardLinksKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsHardLinksKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsHardLinksKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsHardLinksKey").orElseThrow() }
private val NSURLVolumeSupportsHardLinksKey_VH: VarHandle by lazy { NSURLVolumeSupportsHardLinksKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsHardLinksKey: MemorySegment
    get() = NSURLVolumeSupportsHardLinksKey_VH.get(NSURLVolumeSupportsHardLinksKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsHardLinksKey_VH.set(NSURLVolumeSupportsHardLinksKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsJournalingKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsJournalingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsJournalingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsJournalingKey").orElseThrow() }
private val NSURLVolumeSupportsJournalingKey_VH: VarHandle by lazy { NSURLVolumeSupportsJournalingKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsJournalingKey: MemorySegment
    get() = NSURLVolumeSupportsJournalingKey_VH.get(NSURLVolumeSupportsJournalingKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsJournalingKey_VH.set(NSURLVolumeSupportsJournalingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsJournalingKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsJournalingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsJournalingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsJournalingKey").orElseThrow() }
private val NSURLVolumeIsJournalingKey_VH: VarHandle by lazy { NSURLVolumeIsJournalingKey_LAYOUT.varHandle() }

var NSURLVolumeIsJournalingKey: MemorySegment
    get() = NSURLVolumeIsJournalingKey_VH.get(NSURLVolumeIsJournalingKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsJournalingKey_VH.set(NSURLVolumeIsJournalingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsSparseFilesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsSparseFilesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsSparseFilesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsSparseFilesKey").orElseThrow() }
private val NSURLVolumeSupportsSparseFilesKey_VH: VarHandle by lazy { NSURLVolumeSupportsSparseFilesKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsSparseFilesKey: MemorySegment
    get() = NSURLVolumeSupportsSparseFilesKey_VH.get(NSURLVolumeSupportsSparseFilesKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsSparseFilesKey_VH.set(NSURLVolumeSupportsSparseFilesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsZeroRunsKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsZeroRunsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsZeroRunsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsZeroRunsKey").orElseThrow() }
private val NSURLVolumeSupportsZeroRunsKey_VH: VarHandle by lazy { NSURLVolumeSupportsZeroRunsKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsZeroRunsKey: MemorySegment
    get() = NSURLVolumeSupportsZeroRunsKey_VH.get(NSURLVolumeSupportsZeroRunsKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsZeroRunsKey_VH.set(NSURLVolumeSupportsZeroRunsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsCaseSensitiveNamesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsCaseSensitiveNamesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsCaseSensitiveNamesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsCaseSensitiveNamesKey").orElseThrow() }
private val NSURLVolumeSupportsCaseSensitiveNamesKey_VH: VarHandle by lazy { NSURLVolumeSupportsCaseSensitiveNamesKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsCaseSensitiveNamesKey: MemorySegment
    get() = NSURLVolumeSupportsCaseSensitiveNamesKey_VH.get(NSURLVolumeSupportsCaseSensitiveNamesKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsCaseSensitiveNamesKey_VH.set(NSURLVolumeSupportsCaseSensitiveNamesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsCasePreservedNamesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsCasePreservedNamesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsCasePreservedNamesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsCasePreservedNamesKey").orElseThrow() }
private val NSURLVolumeSupportsCasePreservedNamesKey_VH: VarHandle by lazy { NSURLVolumeSupportsCasePreservedNamesKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsCasePreservedNamesKey: MemorySegment
    get() = NSURLVolumeSupportsCasePreservedNamesKey_VH.get(NSURLVolumeSupportsCasePreservedNamesKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsCasePreservedNamesKey_VH.set(NSURLVolumeSupportsCasePreservedNamesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsRootDirectoryDatesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsRootDirectoryDatesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsRootDirectoryDatesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsRootDirectoryDatesKey").orElseThrow() }
private val NSURLVolumeSupportsRootDirectoryDatesKey_VH: VarHandle by lazy { NSURLVolumeSupportsRootDirectoryDatesKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsRootDirectoryDatesKey: MemorySegment
    get() = NSURLVolumeSupportsRootDirectoryDatesKey_VH.get(NSURLVolumeSupportsRootDirectoryDatesKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsRootDirectoryDatesKey_VH.set(NSURLVolumeSupportsRootDirectoryDatesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsVolumeSizesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsVolumeSizesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsVolumeSizesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsVolumeSizesKey").orElseThrow() }
private val NSURLVolumeSupportsVolumeSizesKey_VH: VarHandle by lazy { NSURLVolumeSupportsVolumeSizesKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsVolumeSizesKey: MemorySegment
    get() = NSURLVolumeSupportsVolumeSizesKey_VH.get(NSURLVolumeSupportsVolumeSizesKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsVolumeSizesKey_VH.set(NSURLVolumeSupportsVolumeSizesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsRenamingKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsRenamingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsRenamingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsRenamingKey").orElseThrow() }
private val NSURLVolumeSupportsRenamingKey_VH: VarHandle by lazy { NSURLVolumeSupportsRenamingKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsRenamingKey: MemorySegment
    get() = NSURLVolumeSupportsRenamingKey_VH.get(NSURLVolumeSupportsRenamingKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsRenamingKey_VH.set(NSURLVolumeSupportsRenamingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsAdvisoryFileLockingKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsAdvisoryFileLockingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsAdvisoryFileLockingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsAdvisoryFileLockingKey").orElseThrow() }
private val NSURLVolumeSupportsAdvisoryFileLockingKey_VH: VarHandle by lazy { NSURLVolumeSupportsAdvisoryFileLockingKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsAdvisoryFileLockingKey: MemorySegment
    get() = NSURLVolumeSupportsAdvisoryFileLockingKey_VH.get(NSURLVolumeSupportsAdvisoryFileLockingKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsAdvisoryFileLockingKey_VH.set(NSURLVolumeSupportsAdvisoryFileLockingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsExtendedSecurityKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsExtendedSecurityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsExtendedSecurityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsExtendedSecurityKey").orElseThrow() }
private val NSURLVolumeSupportsExtendedSecurityKey_VH: VarHandle by lazy { NSURLVolumeSupportsExtendedSecurityKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsExtendedSecurityKey: MemorySegment
    get() = NSURLVolumeSupportsExtendedSecurityKey_VH.get(NSURLVolumeSupportsExtendedSecurityKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsExtendedSecurityKey_VH.set(NSURLVolumeSupportsExtendedSecurityKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsBrowsableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsBrowsableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsBrowsableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsBrowsableKey").orElseThrow() }
private val NSURLVolumeIsBrowsableKey_VH: VarHandle by lazy { NSURLVolumeIsBrowsableKey_LAYOUT.varHandle() }

var NSURLVolumeIsBrowsableKey: MemorySegment
    get() = NSURLVolumeIsBrowsableKey_VH.get(NSURLVolumeIsBrowsableKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsBrowsableKey_VH.set(NSURLVolumeIsBrowsableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeMaximumFileSizeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeMaximumFileSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeMaximumFileSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeMaximumFileSizeKey").orElseThrow() }
private val NSURLVolumeMaximumFileSizeKey_VH: VarHandle by lazy { NSURLVolumeMaximumFileSizeKey_LAYOUT.varHandle() }

var NSURLVolumeMaximumFileSizeKey: MemorySegment
    get() = NSURLVolumeMaximumFileSizeKey_VH.get(NSURLVolumeMaximumFileSizeKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeMaximumFileSizeKey_VH.set(NSURLVolumeMaximumFileSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsEjectableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsEjectableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsEjectableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsEjectableKey").orElseThrow() }
private val NSURLVolumeIsEjectableKey_VH: VarHandle by lazy { NSURLVolumeIsEjectableKey_LAYOUT.varHandle() }

var NSURLVolumeIsEjectableKey: MemorySegment
    get() = NSURLVolumeIsEjectableKey_VH.get(NSURLVolumeIsEjectableKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsEjectableKey_VH.set(NSURLVolumeIsEjectableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsRemovableKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsRemovableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsRemovableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsRemovableKey").orElseThrow() }
private val NSURLVolumeIsRemovableKey_VH: VarHandle by lazy { NSURLVolumeIsRemovableKey_LAYOUT.varHandle() }

var NSURLVolumeIsRemovableKey: MemorySegment
    get() = NSURLVolumeIsRemovableKey_VH.get(NSURLVolumeIsRemovableKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsRemovableKey_VH.set(NSURLVolumeIsRemovableKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsInternalKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsInternalKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsInternalKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsInternalKey").orElseThrow() }
private val NSURLVolumeIsInternalKey_VH: VarHandle by lazy { NSURLVolumeIsInternalKey_LAYOUT.varHandle() }

var NSURLVolumeIsInternalKey: MemorySegment
    get() = NSURLVolumeIsInternalKey_VH.get(NSURLVolumeIsInternalKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsInternalKey_VH.set(NSURLVolumeIsInternalKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsAutomountedKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsAutomountedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsAutomountedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsAutomountedKey").orElseThrow() }
private val NSURLVolumeIsAutomountedKey_VH: VarHandle by lazy { NSURLVolumeIsAutomountedKey_LAYOUT.varHandle() }

var NSURLVolumeIsAutomountedKey: MemorySegment
    get() = NSURLVolumeIsAutomountedKey_VH.get(NSURLVolumeIsAutomountedKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsAutomountedKey_VH.set(NSURLVolumeIsAutomountedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsLocalKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsLocalKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsLocalKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsLocalKey").orElseThrow() }
private val NSURLVolumeIsLocalKey_VH: VarHandle by lazy { NSURLVolumeIsLocalKey_LAYOUT.varHandle() }

var NSURLVolumeIsLocalKey: MemorySegment
    get() = NSURLVolumeIsLocalKey_VH.get(NSURLVolumeIsLocalKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsLocalKey_VH.set(NSURLVolumeIsLocalKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsReadOnlyKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsReadOnlyKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsReadOnlyKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsReadOnlyKey").orElseThrow() }
private val NSURLVolumeIsReadOnlyKey_VH: VarHandle by lazy { NSURLVolumeIsReadOnlyKey_LAYOUT.varHandle() }

var NSURLVolumeIsReadOnlyKey: MemorySegment
    get() = NSURLVolumeIsReadOnlyKey_VH.get(NSURLVolumeIsReadOnlyKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsReadOnlyKey_VH.set(NSURLVolumeIsReadOnlyKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeCreationDateKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeCreationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeCreationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeCreationDateKey").orElseThrow() }
private val NSURLVolumeCreationDateKey_VH: VarHandle by lazy { NSURLVolumeCreationDateKey_LAYOUT.varHandle() }

var NSURLVolumeCreationDateKey: MemorySegment
    get() = NSURLVolumeCreationDateKey_VH.get(NSURLVolumeCreationDateKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeCreationDateKey_VH.set(NSURLVolumeCreationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeURLForRemountingKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeURLForRemountingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeURLForRemountingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeURLForRemountingKey").orElseThrow() }
private val NSURLVolumeURLForRemountingKey_VH: VarHandle by lazy { NSURLVolumeURLForRemountingKey_LAYOUT.varHandle() }

var NSURLVolumeURLForRemountingKey: MemorySegment
    get() = NSURLVolumeURLForRemountingKey_VH.get(NSURLVolumeURLForRemountingKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeURLForRemountingKey_VH.set(NSURLVolumeURLForRemountingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeUUIDStringKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeUUIDStringKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeUUIDStringKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeUUIDStringKey").orElseThrow() }
private val NSURLVolumeUUIDStringKey_VH: VarHandle by lazy { NSURLVolumeUUIDStringKey_LAYOUT.varHandle() }

var NSURLVolumeUUIDStringKey: MemorySegment
    get() = NSURLVolumeUUIDStringKey_VH.get(NSURLVolumeUUIDStringKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeUUIDStringKey_VH.set(NSURLVolumeUUIDStringKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeNameKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeNameKey").orElseThrow() }
private val NSURLVolumeNameKey_VH: VarHandle by lazy { NSURLVolumeNameKey_LAYOUT.varHandle() }

var NSURLVolumeNameKey: MemorySegment
    get() = NSURLVolumeNameKey_VH.get(NSURLVolumeNameKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeNameKey_VH.set(NSURLVolumeNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeLocalizedNameKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeLocalizedNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeLocalizedNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeLocalizedNameKey").orElseThrow() }
private val NSURLVolumeLocalizedNameKey_VH: VarHandle by lazy { NSURLVolumeLocalizedNameKey_LAYOUT.varHandle() }

var NSURLVolumeLocalizedNameKey: MemorySegment
    get() = NSURLVolumeLocalizedNameKey_VH.get(NSURLVolumeLocalizedNameKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeLocalizedNameKey_VH.set(NSURLVolumeLocalizedNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsEncryptedKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsEncryptedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsEncryptedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsEncryptedKey").orElseThrow() }
private val NSURLVolumeIsEncryptedKey_VH: VarHandle by lazy { NSURLVolumeIsEncryptedKey_LAYOUT.varHandle() }

var NSURLVolumeIsEncryptedKey: MemorySegment
    get() = NSURLVolumeIsEncryptedKey_VH.get(NSURLVolumeIsEncryptedKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsEncryptedKey_VH.set(NSURLVolumeIsEncryptedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeIsRootFileSystemKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeIsRootFileSystemKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeIsRootFileSystemKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeIsRootFileSystemKey").orElseThrow() }
private val NSURLVolumeIsRootFileSystemKey_VH: VarHandle by lazy { NSURLVolumeIsRootFileSystemKey_LAYOUT.varHandle() }

var NSURLVolumeIsRootFileSystemKey: MemorySegment
    get() = NSURLVolumeIsRootFileSystemKey_VH.get(NSURLVolumeIsRootFileSystemKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeIsRootFileSystemKey_VH.set(NSURLVolumeIsRootFileSystemKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsCompressionKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsCompressionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsCompressionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsCompressionKey").orElseThrow() }
private val NSURLVolumeSupportsCompressionKey_VH: VarHandle by lazy { NSURLVolumeSupportsCompressionKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsCompressionKey: MemorySegment
    get() = NSURLVolumeSupportsCompressionKey_VH.get(NSURLVolumeSupportsCompressionKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsCompressionKey_VH.set(NSURLVolumeSupportsCompressionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsFileCloningKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsFileCloningKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsFileCloningKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsFileCloningKey").orElseThrow() }
private val NSURLVolumeSupportsFileCloningKey_VH: VarHandle by lazy { NSURLVolumeSupportsFileCloningKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsFileCloningKey: MemorySegment
    get() = NSURLVolumeSupportsFileCloningKey_VH.get(NSURLVolumeSupportsFileCloningKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsFileCloningKey_VH.set(NSURLVolumeSupportsFileCloningKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsSwapRenamingKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsSwapRenamingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsSwapRenamingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsSwapRenamingKey").orElseThrow() }
private val NSURLVolumeSupportsSwapRenamingKey_VH: VarHandle by lazy { NSURLVolumeSupportsSwapRenamingKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsSwapRenamingKey: MemorySegment
    get() = NSURLVolumeSupportsSwapRenamingKey_VH.get(NSURLVolumeSupportsSwapRenamingKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsSwapRenamingKey_VH.set(NSURLVolumeSupportsSwapRenamingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsExclusiveRenamingKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsExclusiveRenamingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsExclusiveRenamingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsExclusiveRenamingKey").orElseThrow() }
private val NSURLVolumeSupportsExclusiveRenamingKey_VH: VarHandle by lazy { NSURLVolumeSupportsExclusiveRenamingKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsExclusiveRenamingKey: MemorySegment
    get() = NSURLVolumeSupportsExclusiveRenamingKey_VH.get(NSURLVolumeSupportsExclusiveRenamingKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsExclusiveRenamingKey_VH.set(NSURLVolumeSupportsExclusiveRenamingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsImmutableFilesKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsImmutableFilesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsImmutableFilesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsImmutableFilesKey").orElseThrow() }
private val NSURLVolumeSupportsImmutableFilesKey_VH: VarHandle by lazy { NSURLVolumeSupportsImmutableFilesKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsImmutableFilesKey: MemorySegment
    get() = NSURLVolumeSupportsImmutableFilesKey_VH.get(NSURLVolumeSupportsImmutableFilesKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsImmutableFilesKey_VH.set(NSURLVolumeSupportsImmutableFilesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSupportsAccessPermissionsKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsAccessPermissionsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsAccessPermissionsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsAccessPermissionsKey").orElseThrow() }
private val NSURLVolumeSupportsAccessPermissionsKey_VH: VarHandle by lazy { NSURLVolumeSupportsAccessPermissionsKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsAccessPermissionsKey: MemorySegment
    get() = NSURLVolumeSupportsAccessPermissionsKey_VH.get(NSURLVolumeSupportsAccessPermissionsKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsAccessPermissionsKey_VH.set(NSURLVolumeSupportsAccessPermissionsKey_SEGMENT, value)

