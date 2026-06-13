package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : kCFURLTypeIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLTypeIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLTypeIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLTypeIdentifierKey").orElseThrow() }
private val kCFURLTypeIdentifierKey_VH: VarHandle by lazy { kCFURLTypeIdentifierKey_LAYOUT.varHandle() }

var kCFURLTypeIdentifierKey: MemorySegment
    get() = kCFURLTypeIdentifierKey_VH.get(kCFURLTypeIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFURLTypeIdentifierKey_VH.set(kCFURLTypeIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLLocalizedTypeDescriptionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLLocalizedTypeDescriptionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLLocalizedTypeDescriptionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLLocalizedTypeDescriptionKey").orElseThrow() }
private val kCFURLLocalizedTypeDescriptionKey_VH: VarHandle by lazy { kCFURLLocalizedTypeDescriptionKey_LAYOUT.varHandle() }

var kCFURLLocalizedTypeDescriptionKey: MemorySegment
    get() = kCFURLLocalizedTypeDescriptionKey_VH.get(kCFURLLocalizedTypeDescriptionKey_SEGMENT) as MemorySegment
    set(value) = kCFURLLocalizedTypeDescriptionKey_VH.set(kCFURLLocalizedTypeDescriptionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLLabelNumberKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLLabelNumberKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLLabelNumberKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLLabelNumberKey").orElseThrow() }
private val kCFURLLabelNumberKey_VH: VarHandle by lazy { kCFURLLabelNumberKey_LAYOUT.varHandle() }

var kCFURLLabelNumberKey: MemorySegment
    get() = kCFURLLabelNumberKey_VH.get(kCFURLLabelNumberKey_SEGMENT) as MemorySegment
    set(value) = kCFURLLabelNumberKey_VH.set(kCFURLLabelNumberKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLLabelColorKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLLabelColorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLLabelColorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLLabelColorKey").orElseThrow() }
private val kCFURLLabelColorKey_VH: VarHandle by lazy { kCFURLLabelColorKey_LAYOUT.varHandle() }

var kCFURLLabelColorKey: MemorySegment
    get() = kCFURLLabelColorKey_VH.get(kCFURLLabelColorKey_SEGMENT) as MemorySegment
    set(value) = kCFURLLabelColorKey_VH.set(kCFURLLabelColorKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLLocalizedLabelKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLLocalizedLabelKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLLocalizedLabelKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLLocalizedLabelKey").orElseThrow() }
private val kCFURLLocalizedLabelKey_VH: VarHandle by lazy { kCFURLLocalizedLabelKey_LAYOUT.varHandle() }

var kCFURLLocalizedLabelKey: MemorySegment
    get() = kCFURLLocalizedLabelKey_VH.get(kCFURLLocalizedLabelKey_SEGMENT) as MemorySegment
    set(value) = kCFURLLocalizedLabelKey_VH.set(kCFURLLocalizedLabelKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLEffectiveIconKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLEffectiveIconKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLEffectiveIconKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLEffectiveIconKey").orElseThrow() }
private val kCFURLEffectiveIconKey_VH: VarHandle by lazy { kCFURLEffectiveIconKey_LAYOUT.varHandle() }

var kCFURLEffectiveIconKey: MemorySegment
    get() = kCFURLEffectiveIconKey_VH.get(kCFURLEffectiveIconKey_SEGMENT) as MemorySegment
    set(value) = kCFURLEffectiveIconKey_VH.set(kCFURLEffectiveIconKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLCustomIconKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLCustomIconKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLCustomIconKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLCustomIconKey").orElseThrow() }
private val kCFURLCustomIconKey_VH: VarHandle by lazy { kCFURLCustomIconKey_LAYOUT.varHandle() }

var kCFURLCustomIconKey: MemorySegment
    get() = kCFURLCustomIconKey_VH.get(kCFURLCustomIconKey_SEGMENT) as MemorySegment
    set(value) = kCFURLCustomIconKey_VH.set(kCFURLCustomIconKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceIdentifierKey").orElseThrow() }
private val kCFURLFileResourceIdentifierKey_VH: VarHandle by lazy { kCFURLFileResourceIdentifierKey_LAYOUT.varHandle() }

var kCFURLFileResourceIdentifierKey: MemorySegment
    get() = kCFURLFileResourceIdentifierKey_VH.get(kCFURLFileResourceIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceIdentifierKey_VH.set(kCFURLFileResourceIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIdentifierKey").orElseThrow() }
private val kCFURLVolumeIdentifierKey_VH: VarHandle by lazy { kCFURLVolumeIdentifierKey_LAYOUT.varHandle() }

var kCFURLVolumeIdentifierKey: MemorySegment
    get() = kCFURLVolumeIdentifierKey_VH.get(kCFURLVolumeIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIdentifierKey_VH.set(kCFURLVolumeIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLPreferredIOBlockSizeKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLPreferredIOBlockSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLPreferredIOBlockSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLPreferredIOBlockSizeKey").orElseThrow() }
private val kCFURLPreferredIOBlockSizeKey_VH: VarHandle by lazy { kCFURLPreferredIOBlockSizeKey_LAYOUT.varHandle() }

var kCFURLPreferredIOBlockSizeKey: MemorySegment
    get() = kCFURLPreferredIOBlockSizeKey_VH.get(kCFURLPreferredIOBlockSizeKey_SEGMENT) as MemorySegment
    set(value) = kCFURLPreferredIOBlockSizeKey_VH.set(kCFURLPreferredIOBlockSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsReadableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsReadableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsReadableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsReadableKey").orElseThrow() }
private val kCFURLIsReadableKey_VH: VarHandle by lazy { kCFURLIsReadableKey_LAYOUT.varHandle() }

var kCFURLIsReadableKey: MemorySegment
    get() = kCFURLIsReadableKey_VH.get(kCFURLIsReadableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsReadableKey_VH.set(kCFURLIsReadableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsWritableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsWritableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsWritableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsWritableKey").orElseThrow() }
private val kCFURLIsWritableKey_VH: VarHandle by lazy { kCFURLIsWritableKey_LAYOUT.varHandle() }

var kCFURLIsWritableKey: MemorySegment
    get() = kCFURLIsWritableKey_VH.get(kCFURLIsWritableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsWritableKey_VH.set(kCFURLIsWritableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsExecutableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsExecutableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsExecutableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsExecutableKey").orElseThrow() }
private val kCFURLIsExecutableKey_VH: VarHandle by lazy { kCFURLIsExecutableKey_LAYOUT.varHandle() }

var kCFURLIsExecutableKey: MemorySegment
    get() = kCFURLIsExecutableKey_VH.get(kCFURLIsExecutableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsExecutableKey_VH.set(kCFURLIsExecutableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileSecurityKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileSecurityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileSecurityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileSecurityKey").orElseThrow() }
private val kCFURLFileSecurityKey_VH: VarHandle by lazy { kCFURLFileSecurityKey_LAYOUT.varHandle() }

var kCFURLFileSecurityKey: MemorySegment
    get() = kCFURLFileSecurityKey_VH.get(kCFURLFileSecurityKey_SEGMENT) as MemorySegment
    set(value) = kCFURLFileSecurityKey_VH.set(kCFURLFileSecurityKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsExcludedFromBackupKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsExcludedFromBackupKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsExcludedFromBackupKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsExcludedFromBackupKey").orElseThrow() }
private val kCFURLIsExcludedFromBackupKey_VH: VarHandle by lazy { kCFURLIsExcludedFromBackupKey_LAYOUT.varHandle() }

var kCFURLIsExcludedFromBackupKey: MemorySegment
    get() = kCFURLIsExcludedFromBackupKey_VH.get(kCFURLIsExcludedFromBackupKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsExcludedFromBackupKey_VH.set(kCFURLIsExcludedFromBackupKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLTagNamesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLTagNamesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLTagNamesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLTagNamesKey").orElseThrow() }
private val kCFURLTagNamesKey_VH: VarHandle by lazy { kCFURLTagNamesKey_LAYOUT.varHandle() }

var kCFURLTagNamesKey: MemorySegment
    get() = kCFURLTagNamesKey_VH.get(kCFURLTagNamesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLTagNamesKey_VH.set(kCFURLTagNamesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLPathKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLPathKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLPathKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLPathKey").orElseThrow() }
private val kCFURLPathKey_VH: VarHandle by lazy { kCFURLPathKey_LAYOUT.varHandle() }

var kCFURLPathKey: MemorySegment
    get() = kCFURLPathKey_VH.get(kCFURLPathKey_SEGMENT) as MemorySegment
    set(value) = kCFURLPathKey_VH.set(kCFURLPathKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLCanonicalPathKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLCanonicalPathKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLCanonicalPathKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLCanonicalPathKey").orElseThrow() }
private val kCFURLCanonicalPathKey_VH: VarHandle by lazy { kCFURLCanonicalPathKey_LAYOUT.varHandle() }

var kCFURLCanonicalPathKey: MemorySegment
    get() = kCFURLCanonicalPathKey_VH.get(kCFURLCanonicalPathKey_SEGMENT) as MemorySegment
    set(value) = kCFURLCanonicalPathKey_VH.set(kCFURLCanonicalPathKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsMountTriggerKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsMountTriggerKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsMountTriggerKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsMountTriggerKey").orElseThrow() }
private val kCFURLIsMountTriggerKey_VH: VarHandle by lazy { kCFURLIsMountTriggerKey_LAYOUT.varHandle() }

var kCFURLIsMountTriggerKey: MemorySegment
    get() = kCFURLIsMountTriggerKey_VH.get(kCFURLIsMountTriggerKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsMountTriggerKey_VH.set(kCFURLIsMountTriggerKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLGenerationIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLGenerationIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLGenerationIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLGenerationIdentifierKey").orElseThrow() }
private val kCFURLGenerationIdentifierKey_VH: VarHandle by lazy { kCFURLGenerationIdentifierKey_LAYOUT.varHandle() }

var kCFURLGenerationIdentifierKey: MemorySegment
    get() = kCFURLGenerationIdentifierKey_VH.get(kCFURLGenerationIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFURLGenerationIdentifierKey_VH.set(kCFURLGenerationIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLDocumentIdentifierKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLDocumentIdentifierKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLDocumentIdentifierKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLDocumentIdentifierKey").orElseThrow() }
private val kCFURLDocumentIdentifierKey_VH: VarHandle by lazy { kCFURLDocumentIdentifierKey_LAYOUT.varHandle() }

var kCFURLDocumentIdentifierKey: MemorySegment
    get() = kCFURLDocumentIdentifierKey_VH.get(kCFURLDocumentIdentifierKey_SEGMENT) as MemorySegment
    set(value) = kCFURLDocumentIdentifierKey_VH.set(kCFURLDocumentIdentifierKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLAddedToDirectoryDateKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLAddedToDirectoryDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLAddedToDirectoryDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLAddedToDirectoryDateKey").orElseThrow() }
private val kCFURLAddedToDirectoryDateKey_VH: VarHandle by lazy { kCFURLAddedToDirectoryDateKey_LAYOUT.varHandle() }

var kCFURLAddedToDirectoryDateKey: MemorySegment
    get() = kCFURLAddedToDirectoryDateKey_VH.get(kCFURLAddedToDirectoryDateKey_SEGMENT) as MemorySegment
    set(value) = kCFURLAddedToDirectoryDateKey_VH.set(kCFURLAddedToDirectoryDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLQuarantinePropertiesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLQuarantinePropertiesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLQuarantinePropertiesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLQuarantinePropertiesKey").orElseThrow() }
private val kCFURLQuarantinePropertiesKey_VH: VarHandle by lazy { kCFURLQuarantinePropertiesKey_LAYOUT.varHandle() }

var kCFURLQuarantinePropertiesKey: MemorySegment
    get() = kCFURLQuarantinePropertiesKey_VH.get(kCFURLQuarantinePropertiesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLQuarantinePropertiesKey_VH.set(kCFURLQuarantinePropertiesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceTypeKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceTypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceTypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceTypeKey").orElseThrow() }
private val kCFURLFileResourceTypeKey_VH: VarHandle by lazy { kCFURLFileResourceTypeKey_LAYOUT.varHandle() }

var kCFURLFileResourceTypeKey: MemorySegment
    get() = kCFURLFileResourceTypeKey_VH.get(kCFURLFileResourceTypeKey_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceTypeKey_VH.set(kCFURLFileResourceTypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceTypeNamedPipe typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceTypeNamedPipe_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceTypeNamedPipe_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceTypeNamedPipe").orElseThrow() }
private val kCFURLFileResourceTypeNamedPipe_VH: VarHandle by lazy { kCFURLFileResourceTypeNamedPipe_LAYOUT.varHandle() }

var kCFURLFileResourceTypeNamedPipe: MemorySegment
    get() = kCFURLFileResourceTypeNamedPipe_VH.get(kCFURLFileResourceTypeNamedPipe_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceTypeNamedPipe_VH.set(kCFURLFileResourceTypeNamedPipe_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceTypeCharacterSpecial typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceTypeCharacterSpecial_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceTypeCharacterSpecial_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceTypeCharacterSpecial").orElseThrow() }
private val kCFURLFileResourceTypeCharacterSpecial_VH: VarHandle by lazy { kCFURLFileResourceTypeCharacterSpecial_LAYOUT.varHandle() }

var kCFURLFileResourceTypeCharacterSpecial: MemorySegment
    get() = kCFURLFileResourceTypeCharacterSpecial_VH.get(kCFURLFileResourceTypeCharacterSpecial_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceTypeCharacterSpecial_VH.set(kCFURLFileResourceTypeCharacterSpecial_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceTypeDirectory typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceTypeDirectory_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceTypeDirectory_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceTypeDirectory").orElseThrow() }
private val kCFURLFileResourceTypeDirectory_VH: VarHandle by lazy { kCFURLFileResourceTypeDirectory_LAYOUT.varHandle() }

var kCFURLFileResourceTypeDirectory: MemorySegment
    get() = kCFURLFileResourceTypeDirectory_VH.get(kCFURLFileResourceTypeDirectory_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceTypeDirectory_VH.set(kCFURLFileResourceTypeDirectory_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceTypeBlockSpecial typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceTypeBlockSpecial_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceTypeBlockSpecial_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceTypeBlockSpecial").orElseThrow() }
private val kCFURLFileResourceTypeBlockSpecial_VH: VarHandle by lazy { kCFURLFileResourceTypeBlockSpecial_LAYOUT.varHandle() }

var kCFURLFileResourceTypeBlockSpecial: MemorySegment
    get() = kCFURLFileResourceTypeBlockSpecial_VH.get(kCFURLFileResourceTypeBlockSpecial_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceTypeBlockSpecial_VH.set(kCFURLFileResourceTypeBlockSpecial_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceTypeRegular typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceTypeRegular_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceTypeRegular_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceTypeRegular").orElseThrow() }
private val kCFURLFileResourceTypeRegular_VH: VarHandle by lazy { kCFURLFileResourceTypeRegular_LAYOUT.varHandle() }

var kCFURLFileResourceTypeRegular: MemorySegment
    get() = kCFURLFileResourceTypeRegular_VH.get(kCFURLFileResourceTypeRegular_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceTypeRegular_VH.set(kCFURLFileResourceTypeRegular_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceTypeSymbolicLink typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceTypeSymbolicLink_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceTypeSymbolicLink_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceTypeSymbolicLink").orElseThrow() }
private val kCFURLFileResourceTypeSymbolicLink_VH: VarHandle by lazy { kCFURLFileResourceTypeSymbolicLink_LAYOUT.varHandle() }

var kCFURLFileResourceTypeSymbolicLink: MemorySegment
    get() = kCFURLFileResourceTypeSymbolicLink_VH.get(kCFURLFileResourceTypeSymbolicLink_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceTypeSymbolicLink_VH.set(kCFURLFileResourceTypeSymbolicLink_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceTypeSocket typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceTypeSocket_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceTypeSocket_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceTypeSocket").orElseThrow() }
private val kCFURLFileResourceTypeSocket_VH: VarHandle by lazy { kCFURLFileResourceTypeSocket_LAYOUT.varHandle() }

var kCFURLFileResourceTypeSocket: MemorySegment
    get() = kCFURLFileResourceTypeSocket_VH.get(kCFURLFileResourceTypeSocket_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceTypeSocket_VH.set(kCFURLFileResourceTypeSocket_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileResourceTypeUnknown typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileResourceTypeUnknown_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileResourceTypeUnknown_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileResourceTypeUnknown").orElseThrow() }
private val kCFURLFileResourceTypeUnknown_VH: VarHandle by lazy { kCFURLFileResourceTypeUnknown_LAYOUT.varHandle() }

var kCFURLFileResourceTypeUnknown: MemorySegment
    get() = kCFURLFileResourceTypeUnknown_VH.get(kCFURLFileResourceTypeUnknown_SEGMENT) as MemorySegment
    set(value) = kCFURLFileResourceTypeUnknown_VH.set(kCFURLFileResourceTypeUnknown_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileSizeKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileSizeKey").orElseThrow() }
private val kCFURLFileSizeKey_VH: VarHandle by lazy { kCFURLFileSizeKey_LAYOUT.varHandle() }

var kCFURLFileSizeKey: MemorySegment
    get() = kCFURLFileSizeKey_VH.get(kCFURLFileSizeKey_SEGMENT) as MemorySegment
    set(value) = kCFURLFileSizeKey_VH.set(kCFURLFileSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileAllocatedSizeKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileAllocatedSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileAllocatedSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileAllocatedSizeKey").orElseThrow() }
private val kCFURLFileAllocatedSizeKey_VH: VarHandle by lazy { kCFURLFileAllocatedSizeKey_LAYOUT.varHandle() }

var kCFURLFileAllocatedSizeKey: MemorySegment
    get() = kCFURLFileAllocatedSizeKey_VH.get(kCFURLFileAllocatedSizeKey_SEGMENT) as MemorySegment
    set(value) = kCFURLFileAllocatedSizeKey_VH.set(kCFURLFileAllocatedSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLTotalFileSizeKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLTotalFileSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLTotalFileSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLTotalFileSizeKey").orElseThrow() }
private val kCFURLTotalFileSizeKey_VH: VarHandle by lazy { kCFURLTotalFileSizeKey_LAYOUT.varHandle() }

var kCFURLTotalFileSizeKey: MemorySegment
    get() = kCFURLTotalFileSizeKey_VH.get(kCFURLTotalFileSizeKey_SEGMENT) as MemorySegment
    set(value) = kCFURLTotalFileSizeKey_VH.set(kCFURLTotalFileSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLTotalFileAllocatedSizeKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLTotalFileAllocatedSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLTotalFileAllocatedSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLTotalFileAllocatedSizeKey").orElseThrow() }
private val kCFURLTotalFileAllocatedSizeKey_VH: VarHandle by lazy { kCFURLTotalFileAllocatedSizeKey_LAYOUT.varHandle() }

var kCFURLTotalFileAllocatedSizeKey: MemorySegment
    get() = kCFURLTotalFileAllocatedSizeKey_VH.get(kCFURLTotalFileAllocatedSizeKey_SEGMENT) as MemorySegment
    set(value) = kCFURLTotalFileAllocatedSizeKey_VH.set(kCFURLTotalFileAllocatedSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsAliasFileKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsAliasFileKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsAliasFileKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsAliasFileKey").orElseThrow() }
private val kCFURLIsAliasFileKey_VH: VarHandle by lazy { kCFURLIsAliasFileKey_LAYOUT.varHandle() }

var kCFURLIsAliasFileKey: MemorySegment
    get() = kCFURLIsAliasFileKey_VH.get(kCFURLIsAliasFileKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsAliasFileKey_VH.set(kCFURLIsAliasFileKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileProtectionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileProtectionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileProtectionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileProtectionKey").orElseThrow() }
private val kCFURLFileProtectionKey_VH: VarHandle by lazy { kCFURLFileProtectionKey_LAYOUT.varHandle() }

var kCFURLFileProtectionKey: MemorySegment
    get() = kCFURLFileProtectionKey_VH.get(kCFURLFileProtectionKey_SEGMENT) as MemorySegment
    set(value) = kCFURLFileProtectionKey_VH.set(kCFURLFileProtectionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileProtectionNone typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileProtectionNone_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileProtectionNone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileProtectionNone").orElseThrow() }
private val kCFURLFileProtectionNone_VH: VarHandle by lazy { kCFURLFileProtectionNone_LAYOUT.varHandle() }

var kCFURLFileProtectionNone: MemorySegment
    get() = kCFURLFileProtectionNone_VH.get(kCFURLFileProtectionNone_SEGMENT) as MemorySegment
    set(value) = kCFURLFileProtectionNone_VH.set(kCFURLFileProtectionNone_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileProtectionComplete typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileProtectionComplete_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileProtectionComplete_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileProtectionComplete").orElseThrow() }
private val kCFURLFileProtectionComplete_VH: VarHandle by lazy { kCFURLFileProtectionComplete_LAYOUT.varHandle() }

var kCFURLFileProtectionComplete: MemorySegment
    get() = kCFURLFileProtectionComplete_VH.get(kCFURLFileProtectionComplete_SEGMENT) as MemorySegment
    set(value) = kCFURLFileProtectionComplete_VH.set(kCFURLFileProtectionComplete_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileProtectionCompleteUnlessOpen typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileProtectionCompleteUnlessOpen_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileProtectionCompleteUnlessOpen_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileProtectionCompleteUnlessOpen").orElseThrow() }
private val kCFURLFileProtectionCompleteUnlessOpen_VH: VarHandle by lazy { kCFURLFileProtectionCompleteUnlessOpen_LAYOUT.varHandle() }

var kCFURLFileProtectionCompleteUnlessOpen: MemorySegment
    get() = kCFURLFileProtectionCompleteUnlessOpen_VH.get(kCFURLFileProtectionCompleteUnlessOpen_SEGMENT) as MemorySegment
    set(value) = kCFURLFileProtectionCompleteUnlessOpen_VH.set(kCFURLFileProtectionCompleteUnlessOpen_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileProtectionCompleteUntilFirstUserAuthentication typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileProtectionCompleteUntilFirstUserAuthentication_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileProtectionCompleteUntilFirstUserAuthentication_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileProtectionCompleteUntilFirstUserAuthentication").orElseThrow() }
private val kCFURLFileProtectionCompleteUntilFirstUserAuthentication_VH: VarHandle by lazy { kCFURLFileProtectionCompleteUntilFirstUserAuthentication_LAYOUT.varHandle() }

var kCFURLFileProtectionCompleteUntilFirstUserAuthentication: MemorySegment
    get() = kCFURLFileProtectionCompleteUntilFirstUserAuthentication_VH.get(kCFURLFileProtectionCompleteUntilFirstUserAuthentication_SEGMENT) as MemorySegment
    set(value) = kCFURLFileProtectionCompleteUntilFirstUserAuthentication_VH.set(kCFURLFileProtectionCompleteUntilFirstUserAuthentication_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileProtectionCompleteWhenUserInactive typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileProtectionCompleteWhenUserInactive_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileProtectionCompleteWhenUserInactive_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileProtectionCompleteWhenUserInactive").orElseThrow() }
private val kCFURLFileProtectionCompleteWhenUserInactive_VH: VarHandle by lazy { kCFURLFileProtectionCompleteWhenUserInactive_LAYOUT.varHandle() }

var kCFURLFileProtectionCompleteWhenUserInactive: MemorySegment
    get() = kCFURLFileProtectionCompleteWhenUserInactive_VH.get(kCFURLFileProtectionCompleteWhenUserInactive_SEGMENT) as MemorySegment
    set(value) = kCFURLFileProtectionCompleteWhenUserInactive_VH.set(kCFURLFileProtectionCompleteWhenUserInactive_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLDirectoryEntryCountKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLDirectoryEntryCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLDirectoryEntryCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLDirectoryEntryCountKey").orElseThrow() }
private val kCFURLDirectoryEntryCountKey_VH: VarHandle by lazy { kCFURLDirectoryEntryCountKey_LAYOUT.varHandle() }

var kCFURLDirectoryEntryCountKey: MemorySegment
    get() = kCFURLDirectoryEntryCountKey_VH.get(kCFURLDirectoryEntryCountKey_SEGMENT) as MemorySegment
    set(value) = kCFURLDirectoryEntryCountKey_VH.set(kCFURLDirectoryEntryCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeLocalizedFormatDescriptionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeLocalizedFormatDescriptionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeLocalizedFormatDescriptionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeLocalizedFormatDescriptionKey").orElseThrow() }
private val kCFURLVolumeLocalizedFormatDescriptionKey_VH: VarHandle by lazy { kCFURLVolumeLocalizedFormatDescriptionKey_LAYOUT.varHandle() }

var kCFURLVolumeLocalizedFormatDescriptionKey: MemorySegment
    get() = kCFURLVolumeLocalizedFormatDescriptionKey_VH.get(kCFURLVolumeLocalizedFormatDescriptionKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeLocalizedFormatDescriptionKey_VH.set(kCFURLVolumeLocalizedFormatDescriptionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeTotalCapacityKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeTotalCapacityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeTotalCapacityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeTotalCapacityKey").orElseThrow() }
private val kCFURLVolumeTotalCapacityKey_VH: VarHandle by lazy { kCFURLVolumeTotalCapacityKey_LAYOUT.varHandle() }

var kCFURLVolumeTotalCapacityKey: MemorySegment
    get() = kCFURLVolumeTotalCapacityKey_VH.get(kCFURLVolumeTotalCapacityKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeTotalCapacityKey_VH.set(kCFURLVolumeTotalCapacityKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeAvailableCapacityKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeAvailableCapacityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeAvailableCapacityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeAvailableCapacityKey").orElseThrow() }
private val kCFURLVolumeAvailableCapacityKey_VH: VarHandle by lazy { kCFURLVolumeAvailableCapacityKey_LAYOUT.varHandle() }

var kCFURLVolumeAvailableCapacityKey: MemorySegment
    get() = kCFURLVolumeAvailableCapacityKey_VH.get(kCFURLVolumeAvailableCapacityKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeAvailableCapacityKey_VH.set(kCFURLVolumeAvailableCapacityKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeAvailableCapacityForImportantUsageKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeAvailableCapacityForImportantUsageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeAvailableCapacityForImportantUsageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeAvailableCapacityForImportantUsageKey").orElseThrow() }
private val kCFURLVolumeAvailableCapacityForImportantUsageKey_VH: VarHandle by lazy { kCFURLVolumeAvailableCapacityForImportantUsageKey_LAYOUT.varHandle() }

var kCFURLVolumeAvailableCapacityForImportantUsageKey: MemorySegment
    get() = kCFURLVolumeAvailableCapacityForImportantUsageKey_VH.get(kCFURLVolumeAvailableCapacityForImportantUsageKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeAvailableCapacityForImportantUsageKey_VH.set(kCFURLVolumeAvailableCapacityForImportantUsageKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeAvailableCapacityForOpportunisticUsageKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeAvailableCapacityForOpportunisticUsageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeAvailableCapacityForOpportunisticUsageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeAvailableCapacityForOpportunisticUsageKey").orElseThrow() }
private val kCFURLVolumeAvailableCapacityForOpportunisticUsageKey_VH: VarHandle by lazy { kCFURLVolumeAvailableCapacityForOpportunisticUsageKey_LAYOUT.varHandle() }

var kCFURLVolumeAvailableCapacityForOpportunisticUsageKey: MemorySegment
    get() = kCFURLVolumeAvailableCapacityForOpportunisticUsageKey_VH.get(kCFURLVolumeAvailableCapacityForOpportunisticUsageKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeAvailableCapacityForOpportunisticUsageKey_VH.set(kCFURLVolumeAvailableCapacityForOpportunisticUsageKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeResourceCountKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeResourceCountKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeResourceCountKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeResourceCountKey").orElseThrow() }
private val kCFURLVolumeResourceCountKey_VH: VarHandle by lazy { kCFURLVolumeResourceCountKey_LAYOUT.varHandle() }

var kCFURLVolumeResourceCountKey: MemorySegment
    get() = kCFURLVolumeResourceCountKey_VH.get(kCFURLVolumeResourceCountKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeResourceCountKey_VH.set(kCFURLVolumeResourceCountKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsPersistentIDsKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsPersistentIDsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsPersistentIDsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsPersistentIDsKey").orElseThrow() }
private val kCFURLVolumeSupportsPersistentIDsKey_VH: VarHandle by lazy { kCFURLVolumeSupportsPersistentIDsKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsPersistentIDsKey: MemorySegment
    get() = kCFURLVolumeSupportsPersistentIDsKey_VH.get(kCFURLVolumeSupportsPersistentIDsKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsPersistentIDsKey_VH.set(kCFURLVolumeSupportsPersistentIDsKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsSymbolicLinksKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsSymbolicLinksKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsSymbolicLinksKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsSymbolicLinksKey").orElseThrow() }
private val kCFURLVolumeSupportsSymbolicLinksKey_VH: VarHandle by lazy { kCFURLVolumeSupportsSymbolicLinksKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsSymbolicLinksKey: MemorySegment
    get() = kCFURLVolumeSupportsSymbolicLinksKey_VH.get(kCFURLVolumeSupportsSymbolicLinksKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsSymbolicLinksKey_VH.set(kCFURLVolumeSupportsSymbolicLinksKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsHardLinksKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsHardLinksKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsHardLinksKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsHardLinksKey").orElseThrow() }
private val kCFURLVolumeSupportsHardLinksKey_VH: VarHandle by lazy { kCFURLVolumeSupportsHardLinksKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsHardLinksKey: MemorySegment
    get() = kCFURLVolumeSupportsHardLinksKey_VH.get(kCFURLVolumeSupportsHardLinksKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsHardLinksKey_VH.set(kCFURLVolumeSupportsHardLinksKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsJournalingKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsJournalingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsJournalingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsJournalingKey").orElseThrow() }
private val kCFURLVolumeSupportsJournalingKey_VH: VarHandle by lazy { kCFURLVolumeSupportsJournalingKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsJournalingKey: MemorySegment
    get() = kCFURLVolumeSupportsJournalingKey_VH.get(kCFURLVolumeSupportsJournalingKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsJournalingKey_VH.set(kCFURLVolumeSupportsJournalingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsJournalingKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsJournalingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsJournalingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsJournalingKey").orElseThrow() }
private val kCFURLVolumeIsJournalingKey_VH: VarHandle by lazy { kCFURLVolumeIsJournalingKey_LAYOUT.varHandle() }

var kCFURLVolumeIsJournalingKey: MemorySegment
    get() = kCFURLVolumeIsJournalingKey_VH.get(kCFURLVolumeIsJournalingKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsJournalingKey_VH.set(kCFURLVolumeIsJournalingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsSparseFilesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsSparseFilesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsSparseFilesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsSparseFilesKey").orElseThrow() }
private val kCFURLVolumeSupportsSparseFilesKey_VH: VarHandle by lazy { kCFURLVolumeSupportsSparseFilesKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsSparseFilesKey: MemorySegment
    get() = kCFURLVolumeSupportsSparseFilesKey_VH.get(kCFURLVolumeSupportsSparseFilesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsSparseFilesKey_VH.set(kCFURLVolumeSupportsSparseFilesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsZeroRunsKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsZeroRunsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsZeroRunsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsZeroRunsKey").orElseThrow() }
private val kCFURLVolumeSupportsZeroRunsKey_VH: VarHandle by lazy { kCFURLVolumeSupportsZeroRunsKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsZeroRunsKey: MemorySegment
    get() = kCFURLVolumeSupportsZeroRunsKey_VH.get(kCFURLVolumeSupportsZeroRunsKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsZeroRunsKey_VH.set(kCFURLVolumeSupportsZeroRunsKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsCaseSensitiveNamesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsCaseSensitiveNamesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsCaseSensitiveNamesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsCaseSensitiveNamesKey").orElseThrow() }
private val kCFURLVolumeSupportsCaseSensitiveNamesKey_VH: VarHandle by lazy { kCFURLVolumeSupportsCaseSensitiveNamesKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsCaseSensitiveNamesKey: MemorySegment
    get() = kCFURLVolumeSupportsCaseSensitiveNamesKey_VH.get(kCFURLVolumeSupportsCaseSensitiveNamesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsCaseSensitiveNamesKey_VH.set(kCFURLVolumeSupportsCaseSensitiveNamesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsCasePreservedNamesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsCasePreservedNamesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsCasePreservedNamesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsCasePreservedNamesKey").orElseThrow() }
private val kCFURLVolumeSupportsCasePreservedNamesKey_VH: VarHandle by lazy { kCFURLVolumeSupportsCasePreservedNamesKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsCasePreservedNamesKey: MemorySegment
    get() = kCFURLVolumeSupportsCasePreservedNamesKey_VH.get(kCFURLVolumeSupportsCasePreservedNamesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsCasePreservedNamesKey_VH.set(kCFURLVolumeSupportsCasePreservedNamesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsRootDirectoryDatesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsRootDirectoryDatesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsRootDirectoryDatesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsRootDirectoryDatesKey").orElseThrow() }
private val kCFURLVolumeSupportsRootDirectoryDatesKey_VH: VarHandle by lazy { kCFURLVolumeSupportsRootDirectoryDatesKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsRootDirectoryDatesKey: MemorySegment
    get() = kCFURLVolumeSupportsRootDirectoryDatesKey_VH.get(kCFURLVolumeSupportsRootDirectoryDatesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsRootDirectoryDatesKey_VH.set(kCFURLVolumeSupportsRootDirectoryDatesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsVolumeSizesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsVolumeSizesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsVolumeSizesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsVolumeSizesKey").orElseThrow() }
private val kCFURLVolumeSupportsVolumeSizesKey_VH: VarHandle by lazy { kCFURLVolumeSupportsVolumeSizesKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsVolumeSizesKey: MemorySegment
    get() = kCFURLVolumeSupportsVolumeSizesKey_VH.get(kCFURLVolumeSupportsVolumeSizesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsVolumeSizesKey_VH.set(kCFURLVolumeSupportsVolumeSizesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsRenamingKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsRenamingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsRenamingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsRenamingKey").orElseThrow() }
private val kCFURLVolumeSupportsRenamingKey_VH: VarHandle by lazy { kCFURLVolumeSupportsRenamingKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsRenamingKey: MemorySegment
    get() = kCFURLVolumeSupportsRenamingKey_VH.get(kCFURLVolumeSupportsRenamingKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsRenamingKey_VH.set(kCFURLVolumeSupportsRenamingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsAdvisoryFileLockingKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsAdvisoryFileLockingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsAdvisoryFileLockingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsAdvisoryFileLockingKey").orElseThrow() }
private val kCFURLVolumeSupportsAdvisoryFileLockingKey_VH: VarHandle by lazy { kCFURLVolumeSupportsAdvisoryFileLockingKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsAdvisoryFileLockingKey: MemorySegment
    get() = kCFURLVolumeSupportsAdvisoryFileLockingKey_VH.get(kCFURLVolumeSupportsAdvisoryFileLockingKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsAdvisoryFileLockingKey_VH.set(kCFURLVolumeSupportsAdvisoryFileLockingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsExtendedSecurityKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsExtendedSecurityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsExtendedSecurityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsExtendedSecurityKey").orElseThrow() }
private val kCFURLVolumeSupportsExtendedSecurityKey_VH: VarHandle by lazy { kCFURLVolumeSupportsExtendedSecurityKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsExtendedSecurityKey: MemorySegment
    get() = kCFURLVolumeSupportsExtendedSecurityKey_VH.get(kCFURLVolumeSupportsExtendedSecurityKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsExtendedSecurityKey_VH.set(kCFURLVolumeSupportsExtendedSecurityKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsBrowsableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsBrowsableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsBrowsableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsBrowsableKey").orElseThrow() }
private val kCFURLVolumeIsBrowsableKey_VH: VarHandle by lazy { kCFURLVolumeIsBrowsableKey_LAYOUT.varHandle() }

var kCFURLVolumeIsBrowsableKey: MemorySegment
    get() = kCFURLVolumeIsBrowsableKey_VH.get(kCFURLVolumeIsBrowsableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsBrowsableKey_VH.set(kCFURLVolumeIsBrowsableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeMaximumFileSizeKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeMaximumFileSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeMaximumFileSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeMaximumFileSizeKey").orElseThrow() }
private val kCFURLVolumeMaximumFileSizeKey_VH: VarHandle by lazy { kCFURLVolumeMaximumFileSizeKey_LAYOUT.varHandle() }

var kCFURLVolumeMaximumFileSizeKey: MemorySegment
    get() = kCFURLVolumeMaximumFileSizeKey_VH.get(kCFURLVolumeMaximumFileSizeKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeMaximumFileSizeKey_VH.set(kCFURLVolumeMaximumFileSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsEjectableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsEjectableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsEjectableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsEjectableKey").orElseThrow() }
private val kCFURLVolumeIsEjectableKey_VH: VarHandle by lazy { kCFURLVolumeIsEjectableKey_LAYOUT.varHandle() }

var kCFURLVolumeIsEjectableKey: MemorySegment
    get() = kCFURLVolumeIsEjectableKey_VH.get(kCFURLVolumeIsEjectableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsEjectableKey_VH.set(kCFURLVolumeIsEjectableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsRemovableKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsRemovableKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsRemovableKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsRemovableKey").orElseThrow() }
private val kCFURLVolumeIsRemovableKey_VH: VarHandle by lazy { kCFURLVolumeIsRemovableKey_LAYOUT.varHandle() }

var kCFURLVolumeIsRemovableKey: MemorySegment
    get() = kCFURLVolumeIsRemovableKey_VH.get(kCFURLVolumeIsRemovableKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsRemovableKey_VH.set(kCFURLVolumeIsRemovableKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsInternalKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsInternalKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsInternalKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsInternalKey").orElseThrow() }
private val kCFURLVolumeIsInternalKey_VH: VarHandle by lazy { kCFURLVolumeIsInternalKey_LAYOUT.varHandle() }

var kCFURLVolumeIsInternalKey: MemorySegment
    get() = kCFURLVolumeIsInternalKey_VH.get(kCFURLVolumeIsInternalKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsInternalKey_VH.set(kCFURLVolumeIsInternalKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsAutomountedKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsAutomountedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsAutomountedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsAutomountedKey").orElseThrow() }
private val kCFURLVolumeIsAutomountedKey_VH: VarHandle by lazy { kCFURLVolumeIsAutomountedKey_LAYOUT.varHandle() }

var kCFURLVolumeIsAutomountedKey: MemorySegment
    get() = kCFURLVolumeIsAutomountedKey_VH.get(kCFURLVolumeIsAutomountedKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsAutomountedKey_VH.set(kCFURLVolumeIsAutomountedKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsLocalKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsLocalKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsLocalKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsLocalKey").orElseThrow() }
private val kCFURLVolumeIsLocalKey_VH: VarHandle by lazy { kCFURLVolumeIsLocalKey_LAYOUT.varHandle() }

var kCFURLVolumeIsLocalKey: MemorySegment
    get() = kCFURLVolumeIsLocalKey_VH.get(kCFURLVolumeIsLocalKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsLocalKey_VH.set(kCFURLVolumeIsLocalKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsReadOnlyKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsReadOnlyKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsReadOnlyKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsReadOnlyKey").orElseThrow() }
private val kCFURLVolumeIsReadOnlyKey_VH: VarHandle by lazy { kCFURLVolumeIsReadOnlyKey_LAYOUT.varHandle() }

var kCFURLVolumeIsReadOnlyKey: MemorySegment
    get() = kCFURLVolumeIsReadOnlyKey_VH.get(kCFURLVolumeIsReadOnlyKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsReadOnlyKey_VH.set(kCFURLVolumeIsReadOnlyKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeCreationDateKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeCreationDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeCreationDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeCreationDateKey").orElseThrow() }
private val kCFURLVolumeCreationDateKey_VH: VarHandle by lazy { kCFURLVolumeCreationDateKey_LAYOUT.varHandle() }

var kCFURLVolumeCreationDateKey: MemorySegment
    get() = kCFURLVolumeCreationDateKey_VH.get(kCFURLVolumeCreationDateKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeCreationDateKey_VH.set(kCFURLVolumeCreationDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeURLForRemountingKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeURLForRemountingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeURLForRemountingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeURLForRemountingKey").orElseThrow() }
private val kCFURLVolumeURLForRemountingKey_VH: VarHandle by lazy { kCFURLVolumeURLForRemountingKey_LAYOUT.varHandle() }

var kCFURLVolumeURLForRemountingKey: MemorySegment
    get() = kCFURLVolumeURLForRemountingKey_VH.get(kCFURLVolumeURLForRemountingKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeURLForRemountingKey_VH.set(kCFURLVolumeURLForRemountingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeUUIDStringKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeUUIDStringKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeUUIDStringKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeUUIDStringKey").orElseThrow() }
private val kCFURLVolumeUUIDStringKey_VH: VarHandle by lazy { kCFURLVolumeUUIDStringKey_LAYOUT.varHandle() }

var kCFURLVolumeUUIDStringKey: MemorySegment
    get() = kCFURLVolumeUUIDStringKey_VH.get(kCFURLVolumeUUIDStringKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeUUIDStringKey_VH.set(kCFURLVolumeUUIDStringKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeNameKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeNameKey").orElseThrow() }
private val kCFURLVolumeNameKey_VH: VarHandle by lazy { kCFURLVolumeNameKey_LAYOUT.varHandle() }

var kCFURLVolumeNameKey: MemorySegment
    get() = kCFURLVolumeNameKey_VH.get(kCFURLVolumeNameKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeNameKey_VH.set(kCFURLVolumeNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeLocalizedNameKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeLocalizedNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeLocalizedNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeLocalizedNameKey").orElseThrow() }
private val kCFURLVolumeLocalizedNameKey_VH: VarHandle by lazy { kCFURLVolumeLocalizedNameKey_LAYOUT.varHandle() }

var kCFURLVolumeLocalizedNameKey: MemorySegment
    get() = kCFURLVolumeLocalizedNameKey_VH.get(kCFURLVolumeLocalizedNameKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeLocalizedNameKey_VH.set(kCFURLVolumeLocalizedNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsEncryptedKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsEncryptedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsEncryptedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsEncryptedKey").orElseThrow() }
private val kCFURLVolumeIsEncryptedKey_VH: VarHandle by lazy { kCFURLVolumeIsEncryptedKey_LAYOUT.varHandle() }

var kCFURLVolumeIsEncryptedKey: MemorySegment
    get() = kCFURLVolumeIsEncryptedKey_VH.get(kCFURLVolumeIsEncryptedKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsEncryptedKey_VH.set(kCFURLVolumeIsEncryptedKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeIsRootFileSystemKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeIsRootFileSystemKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeIsRootFileSystemKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeIsRootFileSystemKey").orElseThrow() }
private val kCFURLVolumeIsRootFileSystemKey_VH: VarHandle by lazy { kCFURLVolumeIsRootFileSystemKey_LAYOUT.varHandle() }

var kCFURLVolumeIsRootFileSystemKey: MemorySegment
    get() = kCFURLVolumeIsRootFileSystemKey_VH.get(kCFURLVolumeIsRootFileSystemKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeIsRootFileSystemKey_VH.set(kCFURLVolumeIsRootFileSystemKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsCompressionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsCompressionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsCompressionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsCompressionKey").orElseThrow() }
private val kCFURLVolumeSupportsCompressionKey_VH: VarHandle by lazy { kCFURLVolumeSupportsCompressionKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsCompressionKey: MemorySegment
    get() = kCFURLVolumeSupportsCompressionKey_VH.get(kCFURLVolumeSupportsCompressionKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsCompressionKey_VH.set(kCFURLVolumeSupportsCompressionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsFileCloningKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsFileCloningKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsFileCloningKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsFileCloningKey").orElseThrow() }
private val kCFURLVolumeSupportsFileCloningKey_VH: VarHandle by lazy { kCFURLVolumeSupportsFileCloningKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsFileCloningKey: MemorySegment
    get() = kCFURLVolumeSupportsFileCloningKey_VH.get(kCFURLVolumeSupportsFileCloningKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsFileCloningKey_VH.set(kCFURLVolumeSupportsFileCloningKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsSwapRenamingKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsSwapRenamingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsSwapRenamingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsSwapRenamingKey").orElseThrow() }
private val kCFURLVolumeSupportsSwapRenamingKey_VH: VarHandle by lazy { kCFURLVolumeSupportsSwapRenamingKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsSwapRenamingKey: MemorySegment
    get() = kCFURLVolumeSupportsSwapRenamingKey_VH.get(kCFURLVolumeSupportsSwapRenamingKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsSwapRenamingKey_VH.set(kCFURLVolumeSupportsSwapRenamingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsExclusiveRenamingKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsExclusiveRenamingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsExclusiveRenamingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsExclusiveRenamingKey").orElseThrow() }
private val kCFURLVolumeSupportsExclusiveRenamingKey_VH: VarHandle by lazy { kCFURLVolumeSupportsExclusiveRenamingKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsExclusiveRenamingKey: MemorySegment
    get() = kCFURLVolumeSupportsExclusiveRenamingKey_VH.get(kCFURLVolumeSupportsExclusiveRenamingKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsExclusiveRenamingKey_VH.set(kCFURLVolumeSupportsExclusiveRenamingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsImmutableFilesKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsImmutableFilesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsImmutableFilesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsImmutableFilesKey").orElseThrow() }
private val kCFURLVolumeSupportsImmutableFilesKey_VH: VarHandle by lazy { kCFURLVolumeSupportsImmutableFilesKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsImmutableFilesKey: MemorySegment
    get() = kCFURLVolumeSupportsImmutableFilesKey_VH.get(kCFURLVolumeSupportsImmutableFilesKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsImmutableFilesKey_VH.set(kCFURLVolumeSupportsImmutableFilesKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsAccessPermissionsKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsAccessPermissionsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsAccessPermissionsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsAccessPermissionsKey").orElseThrow() }
private val kCFURLVolumeSupportsAccessPermissionsKey_VH: VarHandle by lazy { kCFURLVolumeSupportsAccessPermissionsKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsAccessPermissionsKey: MemorySegment
    get() = kCFURLVolumeSupportsAccessPermissionsKey_VH.get(kCFURLVolumeSupportsAccessPermissionsKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsAccessPermissionsKey_VH.set(kCFURLVolumeSupportsAccessPermissionsKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSupportsFileProtectionKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSupportsFileProtectionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSupportsFileProtectionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSupportsFileProtectionKey").orElseThrow() }
private val kCFURLVolumeSupportsFileProtectionKey_VH: VarHandle by lazy { kCFURLVolumeSupportsFileProtectionKey_LAYOUT.varHandle() }

var kCFURLVolumeSupportsFileProtectionKey: MemorySegment
    get() = kCFURLVolumeSupportsFileProtectionKey_VH.get(kCFURLVolumeSupportsFileProtectionKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSupportsFileProtectionKey_VH.set(kCFURLVolumeSupportsFileProtectionKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeTypeNameKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeTypeNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeTypeNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeTypeNameKey").orElseThrow() }
private val kCFURLVolumeTypeNameKey_VH: VarHandle by lazy { kCFURLVolumeTypeNameKey_LAYOUT.varHandle() }

var kCFURLVolumeTypeNameKey: MemorySegment
    get() = kCFURLVolumeTypeNameKey_VH.get(kCFURLVolumeTypeNameKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeTypeNameKey_VH.set(kCFURLVolumeTypeNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeSubtypeKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeSubtypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeSubtypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeSubtypeKey").orElseThrow() }
private val kCFURLVolumeSubtypeKey_VH: VarHandle by lazy { kCFURLVolumeSubtypeKey_LAYOUT.varHandle() }

var kCFURLVolumeSubtypeKey: MemorySegment
    get() = kCFURLVolumeSubtypeKey_VH.get(kCFURLVolumeSubtypeKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeSubtypeKey_VH.set(kCFURLVolumeSubtypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLVolumeMountFromLocationKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLVolumeMountFromLocationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLVolumeMountFromLocationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLVolumeMountFromLocationKey").orElseThrow() }
private val kCFURLVolumeMountFromLocationKey_VH: VarHandle by lazy { kCFURLVolumeMountFromLocationKey_LAYOUT.varHandle() }

var kCFURLVolumeMountFromLocationKey: MemorySegment
    get() = kCFURLVolumeMountFromLocationKey_VH.get(kCFURLVolumeMountFromLocationKey_SEGMENT) as MemorySegment
    set(value) = kCFURLVolumeMountFromLocationKey_VH.set(kCFURLVolumeMountFromLocationKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLIsUbiquitousItemKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLIsUbiquitousItemKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLIsUbiquitousItemKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLIsUbiquitousItemKey").orElseThrow() }
private val kCFURLIsUbiquitousItemKey_VH: VarHandle by lazy { kCFURLIsUbiquitousItemKey_LAYOUT.varHandle() }

var kCFURLIsUbiquitousItemKey: MemorySegment
    get() = kCFURLIsUbiquitousItemKey_VH.get(kCFURLIsUbiquitousItemKey_SEGMENT) as MemorySegment
    set(value) = kCFURLIsUbiquitousItemKey_VH.set(kCFURLIsUbiquitousItemKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemHasUnresolvedConflictsKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemHasUnresolvedConflictsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemHasUnresolvedConflictsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemHasUnresolvedConflictsKey").orElseThrow() }
private val kCFURLUbiquitousItemHasUnresolvedConflictsKey_VH: VarHandle by lazy { kCFURLUbiquitousItemHasUnresolvedConflictsKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemHasUnresolvedConflictsKey: MemorySegment
    get() = kCFURLUbiquitousItemHasUnresolvedConflictsKey_VH.get(kCFURLUbiquitousItemHasUnresolvedConflictsKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemHasUnresolvedConflictsKey_VH.set(kCFURLUbiquitousItemHasUnresolvedConflictsKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemIsDownloadedKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemIsDownloadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemIsDownloadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemIsDownloadedKey").orElseThrow() }
private val kCFURLUbiquitousItemIsDownloadedKey_VH: VarHandle by lazy { kCFURLUbiquitousItemIsDownloadedKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemIsDownloadedKey: MemorySegment
    get() = kCFURLUbiquitousItemIsDownloadedKey_VH.get(kCFURLUbiquitousItemIsDownloadedKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemIsDownloadedKey_VH.set(kCFURLUbiquitousItemIsDownloadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemIsDownloadingKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemIsDownloadingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemIsDownloadingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemIsDownloadingKey").orElseThrow() }
private val kCFURLUbiquitousItemIsDownloadingKey_VH: VarHandle by lazy { kCFURLUbiquitousItemIsDownloadingKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemIsDownloadingKey: MemorySegment
    get() = kCFURLUbiquitousItemIsDownloadingKey_VH.get(kCFURLUbiquitousItemIsDownloadingKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemIsDownloadingKey_VH.set(kCFURLUbiquitousItemIsDownloadingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemIsUploadedKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemIsUploadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemIsUploadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemIsUploadedKey").orElseThrow() }
private val kCFURLUbiquitousItemIsUploadedKey_VH: VarHandle by lazy { kCFURLUbiquitousItemIsUploadedKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemIsUploadedKey: MemorySegment
    get() = kCFURLUbiquitousItemIsUploadedKey_VH.get(kCFURLUbiquitousItemIsUploadedKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemIsUploadedKey_VH.set(kCFURLUbiquitousItemIsUploadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemIsUploadingKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemIsUploadingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemIsUploadingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemIsUploadingKey").orElseThrow() }
private val kCFURLUbiquitousItemIsUploadingKey_VH: VarHandle by lazy { kCFURLUbiquitousItemIsUploadingKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemIsUploadingKey: MemorySegment
    get() = kCFURLUbiquitousItemIsUploadingKey_VH.get(kCFURLUbiquitousItemIsUploadingKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemIsUploadingKey_VH.set(kCFURLUbiquitousItemIsUploadingKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemPercentDownloadedKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemPercentDownloadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemPercentDownloadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemPercentDownloadedKey").orElseThrow() }
private val kCFURLUbiquitousItemPercentDownloadedKey_VH: VarHandle by lazy { kCFURLUbiquitousItemPercentDownloadedKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemPercentDownloadedKey: MemorySegment
    get() = kCFURLUbiquitousItemPercentDownloadedKey_VH.get(kCFURLUbiquitousItemPercentDownloadedKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemPercentDownloadedKey_VH.set(kCFURLUbiquitousItemPercentDownloadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemPercentUploadedKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemPercentUploadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemPercentUploadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemPercentUploadedKey").orElseThrow() }
private val kCFURLUbiquitousItemPercentUploadedKey_VH: VarHandle by lazy { kCFURLUbiquitousItemPercentUploadedKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemPercentUploadedKey: MemorySegment
    get() = kCFURLUbiquitousItemPercentUploadedKey_VH.get(kCFURLUbiquitousItemPercentUploadedKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemPercentUploadedKey_VH.set(kCFURLUbiquitousItemPercentUploadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemDownloadingStatusKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemDownloadingStatusKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemDownloadingStatusKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemDownloadingStatusKey").orElseThrow() }
private val kCFURLUbiquitousItemDownloadingStatusKey_VH: VarHandle by lazy { kCFURLUbiquitousItemDownloadingStatusKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemDownloadingStatusKey: MemorySegment
    get() = kCFURLUbiquitousItemDownloadingStatusKey_VH.get(kCFURLUbiquitousItemDownloadingStatusKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemDownloadingStatusKey_VH.set(kCFURLUbiquitousItemDownloadingStatusKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemDownloadingErrorKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemDownloadingErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemDownloadingErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemDownloadingErrorKey").orElseThrow() }
private val kCFURLUbiquitousItemDownloadingErrorKey_VH: VarHandle by lazy { kCFURLUbiquitousItemDownloadingErrorKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemDownloadingErrorKey: MemorySegment
    get() = kCFURLUbiquitousItemDownloadingErrorKey_VH.get(kCFURLUbiquitousItemDownloadingErrorKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemDownloadingErrorKey_VH.set(kCFURLUbiquitousItemDownloadingErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemUploadingErrorKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemUploadingErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemUploadingErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemUploadingErrorKey").orElseThrow() }
private val kCFURLUbiquitousItemUploadingErrorKey_VH: VarHandle by lazy { kCFURLUbiquitousItemUploadingErrorKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemUploadingErrorKey: MemorySegment
    get() = kCFURLUbiquitousItemUploadingErrorKey_VH.get(kCFURLUbiquitousItemUploadingErrorKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemUploadingErrorKey_VH.set(kCFURLUbiquitousItemUploadingErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemIsExcludedFromSyncKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemIsExcludedFromSyncKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemIsExcludedFromSyncKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemIsExcludedFromSyncKey").orElseThrow() }
private val kCFURLUbiquitousItemIsExcludedFromSyncKey_VH: VarHandle by lazy { kCFURLUbiquitousItemIsExcludedFromSyncKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemIsExcludedFromSyncKey: MemorySegment
    get() = kCFURLUbiquitousItemIsExcludedFromSyncKey_VH.get(kCFURLUbiquitousItemIsExcludedFromSyncKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemIsExcludedFromSyncKey_VH.set(kCFURLUbiquitousItemIsExcludedFromSyncKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemDownloadingStatusNotDownloaded typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemDownloadingStatusNotDownloaded_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemDownloadingStatusNotDownloaded_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemDownloadingStatusNotDownloaded").orElseThrow() }
private val kCFURLUbiquitousItemDownloadingStatusNotDownloaded_VH: VarHandle by lazy { kCFURLUbiquitousItemDownloadingStatusNotDownloaded_LAYOUT.varHandle() }

var kCFURLUbiquitousItemDownloadingStatusNotDownloaded: MemorySegment
    get() = kCFURLUbiquitousItemDownloadingStatusNotDownloaded_VH.get(kCFURLUbiquitousItemDownloadingStatusNotDownloaded_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemDownloadingStatusNotDownloaded_VH.set(kCFURLUbiquitousItemDownloadingStatusNotDownloaded_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemDownloadingStatusDownloaded typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemDownloadingStatusDownloaded_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemDownloadingStatusDownloaded_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemDownloadingStatusDownloaded").orElseThrow() }
private val kCFURLUbiquitousItemDownloadingStatusDownloaded_VH: VarHandle by lazy { kCFURLUbiquitousItemDownloadingStatusDownloaded_LAYOUT.varHandle() }

var kCFURLUbiquitousItemDownloadingStatusDownloaded: MemorySegment
    get() = kCFURLUbiquitousItemDownloadingStatusDownloaded_VH.get(kCFURLUbiquitousItemDownloadingStatusDownloaded_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemDownloadingStatusDownloaded_VH.set(kCFURLUbiquitousItemDownloadingStatusDownloaded_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemDownloadingStatusCurrent typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemDownloadingStatusCurrent_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemDownloadingStatusCurrent_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemDownloadingStatusCurrent").orElseThrow() }
private val kCFURLUbiquitousItemDownloadingStatusCurrent_VH: VarHandle by lazy { kCFURLUbiquitousItemDownloadingStatusCurrent_LAYOUT.varHandle() }

var kCFURLUbiquitousItemDownloadingStatusCurrent: MemorySegment
    get() = kCFURLUbiquitousItemDownloadingStatusCurrent_VH.get(kCFURLUbiquitousItemDownloadingStatusCurrent_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemDownloadingStatusCurrent_VH.set(kCFURLUbiquitousItemDownloadingStatusCurrent_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemSupportedSyncControlsKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemSupportedSyncControlsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemSupportedSyncControlsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemSupportedSyncControlsKey").orElseThrow() }
private val kCFURLUbiquitousItemSupportedSyncControlsKey_VH: VarHandle by lazy { kCFURLUbiquitousItemSupportedSyncControlsKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemSupportedSyncControlsKey: MemorySegment
    get() = kCFURLUbiquitousItemSupportedSyncControlsKey_VH.get(kCFURLUbiquitousItemSupportedSyncControlsKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemSupportedSyncControlsKey_VH.set(kCFURLUbiquitousItemSupportedSyncControlsKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLUbiquitousItemIsSyncPausedKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLUbiquitousItemIsSyncPausedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLUbiquitousItemIsSyncPausedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLUbiquitousItemIsSyncPausedKey").orElseThrow() }
private val kCFURLUbiquitousItemIsSyncPausedKey_VH: VarHandle by lazy { kCFURLUbiquitousItemIsSyncPausedKey_LAYOUT.varHandle() }

var kCFURLUbiquitousItemIsSyncPausedKey: MemorySegment
    get() = kCFURLUbiquitousItemIsSyncPausedKey_VH.get(kCFURLUbiquitousItemIsSyncPausedKey_SEGMENT) as MemorySegment
    set(value) = kCFURLUbiquitousItemIsSyncPausedKey_VH.set(kCFURLUbiquitousItemIsSyncPausedKey_SEGMENT, value)

/**
 * {@snippet lang=c : CFURLCreateResourcePropertiesForKeysFromBookmarkData typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFArrayRef = (Declared(__CFArray))*,typedef CFDataRef = (Declared(__CFData))*)
 */
private val CFURLCreateResourcePropertiesForKeysFromBookmarkData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateResourcePropertiesForKeysFromBookmarkData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateResourcePropertiesForKeysFromBookmarkData").orElseThrow()
private val CFURLCreateResourcePropertiesForKeysFromBookmarkData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateResourcePropertiesForKeysFromBookmarkData_ADDR, CFURLCreateResourcePropertiesForKeysFromBookmarkData_DESC)

fun CFURLCreateResourcePropertiesForKeysFromBookmarkData(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFURLCreateResourcePropertiesForKeysFromBookmarkData_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateResourcePropertyForKeyFromBookmarkData typedef CFTypeRef = (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef CFDataRef = (Declared(__CFData))*)
 */
private val CFURLCreateResourcePropertyForKeyFromBookmarkData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateResourcePropertyForKeyFromBookmarkData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateResourcePropertyForKeyFromBookmarkData").orElseThrow()
private val CFURLCreateResourcePropertyForKeyFromBookmarkData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateResourcePropertyForKeyFromBookmarkData_ADDR, CFURLCreateResourcePropertyForKeyFromBookmarkData_DESC)

fun CFURLCreateResourcePropertyForKeyFromBookmarkData(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFURLCreateResourcePropertyForKeyFromBookmarkData_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateBookmarkDataFromFile typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFURLCreateBookmarkDataFromFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateBookmarkDataFromFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateBookmarkDataFromFile").orElseThrow()
private val CFURLCreateBookmarkDataFromFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateBookmarkDataFromFile_ADDR, CFURLCreateBookmarkDataFromFile_DESC)

fun CFURLCreateBookmarkDataFromFile(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CFURLCreateBookmarkDataFromFile_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLWriteBookmarkDataToFile typedef Boolean = UNSIGNED = Char(typedef CFDataRef = (Declared(__CFData))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFURLBookmarkFileCreationOptions = UNSIGNED = Long,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFURLWriteBookmarkDataToFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFURLWriteBookmarkDataToFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLWriteBookmarkDataToFile").orElseThrow()
private val CFURLWriteBookmarkDataToFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLWriteBookmarkDataToFile_ADDR, CFURLWriteBookmarkDataToFile_DESC)

fun CFURLWriteBookmarkDataToFile(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): Byte {
    try {
        return CFURLWriteBookmarkDataToFile_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateBookmarkDataFromAliasRecord typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*)
 */
private val CFURLCreateBookmarkDataFromAliasRecord_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateBookmarkDataFromAliasRecord_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateBookmarkDataFromAliasRecord").orElseThrow()
private val CFURLCreateBookmarkDataFromAliasRecord_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateBookmarkDataFromAliasRecord_ADDR, CFURLCreateBookmarkDataFromAliasRecord_DESC)

fun CFURLCreateBookmarkDataFromAliasRecord(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFURLCreateBookmarkDataFromAliasRecord_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLStartAccessingSecurityScopedResource typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLStartAccessingSecurityScopedResource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFURLStartAccessingSecurityScopedResource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLStartAccessingSecurityScopedResource").orElseThrow()
private val CFURLStartAccessingSecurityScopedResource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLStartAccessingSecurityScopedResource_ADDR, CFURLStartAccessingSecurityScopedResource_DESC)

fun CFURLStartAccessingSecurityScopedResource(arg0: MemorySegment): Byte {
    try {
        return CFURLStartAccessingSecurityScopedResource_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLStopAccessingSecurityScopedResource Void(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFURLStopAccessingSecurityScopedResource_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFURLStopAccessingSecurityScopedResource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLStopAccessingSecurityScopedResource").orElseThrow()
private val CFURLStopAccessingSecurityScopedResource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLStopAccessingSecurityScopedResource_ADDR, CFURLStopAccessingSecurityScopedResource_DESC)

fun CFURLStopAccessingSecurityScopedResource(arg0: MemorySegment): Unit {
    try {
        CFURLStopAccessingSecurityScopedResource_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFRunLoopDefaultMode typedef const CFRunLoopMode = (Declared(__CFString))*
 */
private val kCFRunLoopDefaultMode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFRunLoopDefaultMode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFRunLoopDefaultMode").orElseThrow() }
private val kCFRunLoopDefaultMode_VH: VarHandle by lazy { kCFRunLoopDefaultMode_LAYOUT.varHandle() }

var kCFRunLoopDefaultMode: MemorySegment
    get() = kCFRunLoopDefaultMode_VH.get(kCFRunLoopDefaultMode_SEGMENT) as MemorySegment
    set(value) = kCFRunLoopDefaultMode_VH.set(kCFRunLoopDefaultMode_SEGMENT, value)

/**
 * {@snippet lang=c : kCFRunLoopCommonModes typedef const CFRunLoopMode = (Declared(__CFString))*
 */
private val kCFRunLoopCommonModes_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFRunLoopCommonModes_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFRunLoopCommonModes").orElseThrow() }
private val kCFRunLoopCommonModes_VH: VarHandle by lazy { kCFRunLoopCommonModes_LAYOUT.varHandle() }

var kCFRunLoopCommonModes: MemorySegment
    get() = kCFRunLoopCommonModes_VH.get(kCFRunLoopCommonModes_SEGMENT) as MemorySegment
    set(value) = kCFRunLoopCommonModes_VH.set(kCFRunLoopCommonModes_SEGMENT, value)

/**
 * {@snippet lang=c : CFRunLoopGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFRunLoopGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFRunLoopGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopGetTypeID").orElseThrow()
private val CFRunLoopGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopGetTypeID_ADDR, CFRunLoopGetTypeID_DESC)

fun CFRunLoopGetTypeID(): Long {
    try {
        return CFRunLoopGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopGetCurrent typedef CFRunLoopRef = (Declared(__CFRunLoop))*()
 */
private val CFRunLoopGetCurrent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFRunLoopGetCurrent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopGetCurrent").orElseThrow()
private val CFRunLoopGetCurrent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopGetCurrent_ADDR, CFRunLoopGetCurrent_DESC)

fun CFRunLoopGetCurrent(): MemorySegment {
    try {
        return CFRunLoopGetCurrent_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopGetMain typedef CFRunLoopRef = (Declared(__CFRunLoop))*()
 */
private val CFRunLoopGetMain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CFRunLoopGetMain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopGetMain").orElseThrow()
private val CFRunLoopGetMain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopGetMain_ADDR, CFRunLoopGetMain_DESC)

fun CFRunLoopGetMain(): MemorySegment {
    try {
        return CFRunLoopGetMain_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopCopyCurrentMode typedef CFRunLoopMode = (Declared(__CFString))*(typedef CFRunLoopRef = (Declared(__CFRunLoop))*)
 */
private val CFRunLoopCopyCurrentMode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopCopyCurrentMode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopCopyCurrentMode").orElseThrow()
private val CFRunLoopCopyCurrentMode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopCopyCurrentMode_ADDR, CFRunLoopCopyCurrentMode_DESC)

fun CFRunLoopCopyCurrentMode(arg0: MemorySegment): MemorySegment {
    try {
        return CFRunLoopCopyCurrentMode_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopCopyAllModes typedef CFArrayRef = (Declared(__CFArray))*(typedef CFRunLoopRef = (Declared(__CFRunLoop))*)
 */
private val CFRunLoopCopyAllModes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopCopyAllModes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopCopyAllModes").orElseThrow()
private val CFRunLoopCopyAllModes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopCopyAllModes_ADDR, CFRunLoopCopyAllModes_DESC)

fun CFRunLoopCopyAllModes(arg0: MemorySegment): MemorySegment {
    try {
        return CFRunLoopCopyAllModes_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopAddCommonMode Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopAddCommonMode_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopAddCommonMode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopAddCommonMode").orElseThrow()
private val CFRunLoopAddCommonMode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopAddCommonMode_ADDR, CFRunLoopAddCommonMode_DESC)

fun CFRunLoopAddCommonMode(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFRunLoopAddCommonMode_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopGetNextTimerFireDate typedef CFAbsoluteTime = Double(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopGetNextTimerFireDate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopGetNextTimerFireDate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopGetNextTimerFireDate").orElseThrow()
private val CFRunLoopGetNextTimerFireDate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopGetNextTimerFireDate_ADDR, CFRunLoopGetNextTimerFireDate_DESC)

fun CFRunLoopGetNextTimerFireDate(arg0: MemorySegment, arg1: MemorySegment): Double {
    try {
        return CFRunLoopGetNextTimerFireDate_HANDLE.invokeExact(arg0, arg1) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopRun Void()
 */
private val CFRunLoopRun_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val CFRunLoopRun_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopRun").orElseThrow()
private val CFRunLoopRun_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopRun_ADDR, CFRunLoopRun_DESC)

fun CFRunLoopRun(): Unit {
    try {
        CFRunLoopRun_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopIsWaiting typedef Boolean = UNSIGNED = Char(typedef CFRunLoopRef = (Declared(__CFRunLoop))*)
 */
private val CFRunLoopIsWaiting_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFRunLoopIsWaiting_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopIsWaiting").orElseThrow()
private val CFRunLoopIsWaiting_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopIsWaiting_ADDR, CFRunLoopIsWaiting_DESC)

fun CFRunLoopIsWaiting(arg0: MemorySegment): Byte {
    try {
        return CFRunLoopIsWaiting_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopWakeUp Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*)
 */
private val CFRunLoopWakeUp_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFRunLoopWakeUp_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopWakeUp").orElseThrow()
private val CFRunLoopWakeUp_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopWakeUp_ADDR, CFRunLoopWakeUp_DESC)

fun CFRunLoopWakeUp(arg0: MemorySegment): Unit {
    try {
        CFRunLoopWakeUp_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopStop Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*)
 */
private val CFRunLoopStop_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFRunLoopStop_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopStop").orElseThrow()
private val CFRunLoopStop_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopStop_ADDR, CFRunLoopStop_DESC)

fun CFRunLoopStop(arg0: MemorySegment): Unit {
    try {
        CFRunLoopStop_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopPerformBlock Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFTypeRef = (Void)*,(Void)*)
 */
private val CFRunLoopPerformBlock_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopPerformBlock_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopPerformBlock").orElseThrow()
private val CFRunLoopPerformBlock_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopPerformBlock_ADDR, CFRunLoopPerformBlock_DESC)

fun CFRunLoopPerformBlock(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFRunLoopPerformBlock_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopContainsSource typedef Boolean = UNSIGNED = Char(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopContainsSource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopContainsSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopContainsSource").orElseThrow()
private val CFRunLoopContainsSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopContainsSource_ADDR, CFRunLoopContainsSource_DESC)

fun CFRunLoopContainsSource(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFRunLoopContainsSource_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopAddSource Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopAddSource_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopAddSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopAddSource").orElseThrow()
private val CFRunLoopAddSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopAddSource_ADDR, CFRunLoopAddSource_DESC)

fun CFRunLoopAddSource(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFRunLoopAddSource_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopRemoveSource Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopRemoveSource_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopRemoveSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopRemoveSource").orElseThrow()
private val CFRunLoopRemoveSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopRemoveSource_ADDR, CFRunLoopRemoveSource_DESC)

fun CFRunLoopRemoveSource(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFRunLoopRemoveSource_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopContainsObserver typedef Boolean = UNSIGNED = Char(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopContainsObserver_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopContainsObserver_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopContainsObserver").orElseThrow()
private val CFRunLoopContainsObserver_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopContainsObserver_ADDR, CFRunLoopContainsObserver_DESC)

fun CFRunLoopContainsObserver(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFRunLoopContainsObserver_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopAddObserver Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopAddObserver_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopAddObserver_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopAddObserver").orElseThrow()
private val CFRunLoopAddObserver_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopAddObserver_ADDR, CFRunLoopAddObserver_DESC)

fun CFRunLoopAddObserver(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFRunLoopAddObserver_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopRemoveObserver Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopRemoveObserver_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopRemoveObserver_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopRemoveObserver").orElseThrow()
private val CFRunLoopRemoveObserver_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopRemoveObserver_ADDR, CFRunLoopRemoveObserver_DESC)

fun CFRunLoopRemoveObserver(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFRunLoopRemoveObserver_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopContainsTimer typedef Boolean = UNSIGNED = Char(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopContainsTimer_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopContainsTimer_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopContainsTimer").orElseThrow()
private val CFRunLoopContainsTimer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopContainsTimer_ADDR, CFRunLoopContainsTimer_DESC)

fun CFRunLoopContainsTimer(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFRunLoopContainsTimer_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopAddTimer Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopAddTimer_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopAddTimer_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopAddTimer").orElseThrow()
private val CFRunLoopAddTimer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopAddTimer_ADDR, CFRunLoopAddTimer_DESC)

fun CFRunLoopAddTimer(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFRunLoopAddTimer_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopRemoveTimer Void(typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFRunLoopRemoveTimer_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopRemoveTimer_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopRemoveTimer").orElseThrow()
private val CFRunLoopRemoveTimer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopRemoveTimer_ADDR, CFRunLoopRemoveTimer_DESC)

fun CFRunLoopRemoveTimer(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFRunLoopRemoveTimer_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopSourceGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFRunLoopSourceGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFRunLoopSourceGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopSourceGetTypeID").orElseThrow()
private val CFRunLoopSourceGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopSourceGetTypeID_ADDR, CFRunLoopSourceGetTypeID_DESC)

fun CFRunLoopSourceGetTypeID(): Long {
    try {
        return CFRunLoopSourceGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopSourceCreate typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,(typedef CFRunLoopSourceContext = Declared(CFRunLoopSourceContext))*)
 */
private val CFRunLoopSourceCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFRunLoopSourceCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopSourceCreate").orElseThrow()
private val CFRunLoopSourceCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopSourceCreate_ADDR, CFRunLoopSourceCreate_DESC)

fun CFRunLoopSourceCreate(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFRunLoopSourceCreate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopSourceGetOrder typedef CFIndex = Long(typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*)
 */
private val CFRunLoopSourceGetOrder_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFRunLoopSourceGetOrder_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopSourceGetOrder").orElseThrow()
private val CFRunLoopSourceGetOrder_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopSourceGetOrder_ADDR, CFRunLoopSourceGetOrder_DESC)

fun CFRunLoopSourceGetOrder(arg0: MemorySegment): Long {
    try {
        return CFRunLoopSourceGetOrder_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopSourceInvalidate Void(typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*)
 */
private val CFRunLoopSourceInvalidate_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFRunLoopSourceInvalidate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopSourceInvalidate").orElseThrow()
private val CFRunLoopSourceInvalidate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopSourceInvalidate_ADDR, CFRunLoopSourceInvalidate_DESC)

fun CFRunLoopSourceInvalidate(arg0: MemorySegment): Unit {
    try {
        CFRunLoopSourceInvalidate_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopSourceIsValid typedef Boolean = UNSIGNED = Char(typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*)
 */
private val CFRunLoopSourceIsValid_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFRunLoopSourceIsValid_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopSourceIsValid").orElseThrow()
private val CFRunLoopSourceIsValid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopSourceIsValid_ADDR, CFRunLoopSourceIsValid_DESC)

fun CFRunLoopSourceIsValid(arg0: MemorySegment): Byte {
    try {
        return CFRunLoopSourceIsValid_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopSourceGetContext Void(typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*,(typedef CFRunLoopSourceContext = Declared(CFRunLoopSourceContext))*)
 */
private val CFRunLoopSourceGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopSourceGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopSourceGetContext").orElseThrow()
private val CFRunLoopSourceGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopSourceGetContext_ADDR, CFRunLoopSourceGetContext_DESC)

fun CFRunLoopSourceGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFRunLoopSourceGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopSourceSignal Void(typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*)
 */
private val CFRunLoopSourceSignal_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFRunLoopSourceSignal_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopSourceSignal").orElseThrow()
private val CFRunLoopSourceSignal_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopSourceSignal_ADDR, CFRunLoopSourceSignal_DESC)

fun CFRunLoopSourceSignal(arg0: MemorySegment): Unit {
    try {
        CFRunLoopSourceSignal_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopObserverGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFRunLoopObserverGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFRunLoopObserverGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopObserverGetTypeID").orElseThrow()
private val CFRunLoopObserverGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopObserverGetTypeID_ADDR, CFRunLoopObserverGetTypeID_DESC)

fun CFRunLoopObserverGetTypeID(): Long {
    try {
        return CFRunLoopObserverGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopObserverCreate typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFOptionFlags = UNSIGNED = Long,typedef Boolean = UNSIGNED = Char,typedef CFIndex = Long,typedef CFRunLoopObserverCallBack = (Void((Declared(__CFRunLoopObserver))*,<error: enum CFRunLoopActivity>,(Void)*))*,(typedef CFRunLoopObserverContext = Declared(CFRunLoopObserverContext))*)
 */
private val CFRunLoopObserverCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopObserverCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopObserverCreate").orElseThrow()
private val CFRunLoopObserverCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopObserverCreate_ADDR, CFRunLoopObserverCreate_DESC)

fun CFRunLoopObserverCreate(arg0: MemorySegment, arg1: Long, arg2: Byte, arg3: Long, arg4: MemorySegment, arg5: MemorySegment): MemorySegment {
    try {
        return CFRunLoopObserverCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopObserverCreateWithHandler typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFOptionFlags = UNSIGNED = Long,typedef Boolean = UNSIGNED = Char,typedef CFIndex = Long,(Void)*)
 */
private val CFRunLoopObserverCreateWithHandler_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFRunLoopObserverCreateWithHandler_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopObserverCreateWithHandler").orElseThrow()
private val CFRunLoopObserverCreateWithHandler_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopObserverCreateWithHandler_ADDR, CFRunLoopObserverCreateWithHandler_DESC)

fun CFRunLoopObserverCreateWithHandler(arg0: MemorySegment, arg1: Long, arg2: Byte, arg3: Long, arg4: MemorySegment): MemorySegment {
    try {
        return CFRunLoopObserverCreateWithHandler_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopObserverGetActivities typedef CFOptionFlags = UNSIGNED = Long(typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*)
 */
private val CFRunLoopObserverGetActivities_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFRunLoopObserverGetActivities_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopObserverGetActivities").orElseThrow()
private val CFRunLoopObserverGetActivities_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopObserverGetActivities_ADDR, CFRunLoopObserverGetActivities_DESC)

fun CFRunLoopObserverGetActivities(arg0: MemorySegment): Long {
    try {
        return CFRunLoopObserverGetActivities_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopObserverDoesRepeat typedef Boolean = UNSIGNED = Char(typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*)
 */
private val CFRunLoopObserverDoesRepeat_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFRunLoopObserverDoesRepeat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopObserverDoesRepeat").orElseThrow()
private val CFRunLoopObserverDoesRepeat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopObserverDoesRepeat_ADDR, CFRunLoopObserverDoesRepeat_DESC)

fun CFRunLoopObserverDoesRepeat(arg0: MemorySegment): Byte {
    try {
        return CFRunLoopObserverDoesRepeat_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopObserverGetOrder typedef CFIndex = Long(typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*)
 */
private val CFRunLoopObserverGetOrder_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFRunLoopObserverGetOrder_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopObserverGetOrder").orElseThrow()
private val CFRunLoopObserverGetOrder_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopObserverGetOrder_ADDR, CFRunLoopObserverGetOrder_DESC)

fun CFRunLoopObserverGetOrder(arg0: MemorySegment): Long {
    try {
        return CFRunLoopObserverGetOrder_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopObserverInvalidate Void(typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*)
 */
private val CFRunLoopObserverInvalidate_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFRunLoopObserverInvalidate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopObserverInvalidate").orElseThrow()
private val CFRunLoopObserverInvalidate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopObserverInvalidate_ADDR, CFRunLoopObserverInvalidate_DESC)

fun CFRunLoopObserverInvalidate(arg0: MemorySegment): Unit {
    try {
        CFRunLoopObserverInvalidate_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopObserverIsValid typedef Boolean = UNSIGNED = Char(typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*)
 */
private val CFRunLoopObserverIsValid_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFRunLoopObserverIsValid_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopObserverIsValid").orElseThrow()
private val CFRunLoopObserverIsValid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopObserverIsValid_ADDR, CFRunLoopObserverIsValid_DESC)

fun CFRunLoopObserverIsValid(arg0: MemorySegment): Byte {
    try {
        return CFRunLoopObserverIsValid_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopObserverGetContext Void(typedef CFRunLoopObserverRef = (Declared(__CFRunLoopObserver))*,(typedef CFRunLoopObserverContext = Declared(CFRunLoopObserverContext))*)
 */
private val CFRunLoopObserverGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopObserverGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopObserverGetContext").orElseThrow()
private val CFRunLoopObserverGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopObserverGetContext_ADDR, CFRunLoopObserverGetContext_DESC)

fun CFRunLoopObserverGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFRunLoopObserverGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFRunLoopTimerGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFRunLoopTimerGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerGetTypeID").orElseThrow()
private val CFRunLoopTimerGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerGetTypeID_ADDR, CFRunLoopTimerGetTypeID_DESC)

fun CFRunLoopTimerGetTypeID(): Long {
    try {
        return CFRunLoopTimerGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerCreate typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFAbsoluteTime = Double,typedef CFTimeInterval = Double,typedef CFOptionFlags = UNSIGNED = Long,typedef CFIndex = Long,typedef CFRunLoopTimerCallBack = (Void((Declared(__CFRunLoopTimer))*,(Void)*))*,(typedef CFRunLoopTimerContext = Declared(CFRunLoopTimerContext))*)
 */
private val CFRunLoopTimerCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopTimerCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerCreate").orElseThrow()
private val CFRunLoopTimerCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerCreate_ADDR, CFRunLoopTimerCreate_DESC)

fun CFRunLoopTimerCreate(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Long, arg4: Long, arg5: MemorySegment, arg6: MemorySegment): MemorySegment {
    try {
        return CFRunLoopTimerCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerCreateWithHandler typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFAbsoluteTime = Double,typedef CFTimeInterval = Double,typedef CFOptionFlags = UNSIGNED = Long,typedef CFIndex = Long,(Void)*)
 */
private val CFRunLoopTimerCreateWithHandler_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFRunLoopTimerCreateWithHandler_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerCreateWithHandler").orElseThrow()
private val CFRunLoopTimerCreateWithHandler_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerCreateWithHandler_ADDR, CFRunLoopTimerCreateWithHandler_DESC)

fun CFRunLoopTimerCreateWithHandler(arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Long, arg4: Long, arg5: MemorySegment): MemorySegment {
    try {
        return CFRunLoopTimerCreateWithHandler_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerGetNextFireDate typedef CFAbsoluteTime = Double(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*)
 */
private val CFRunLoopTimerGetNextFireDate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFRunLoopTimerGetNextFireDate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerGetNextFireDate").orElseThrow()
private val CFRunLoopTimerGetNextFireDate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerGetNextFireDate_ADDR, CFRunLoopTimerGetNextFireDate_DESC)

fun CFRunLoopTimerGetNextFireDate(arg0: MemorySegment): Double {
    try {
        return CFRunLoopTimerGetNextFireDate_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerSetNextFireDate Void(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*,typedef CFAbsoluteTime = Double)
 */
private val CFRunLoopTimerSetNextFireDate_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFRunLoopTimerSetNextFireDate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerSetNextFireDate").orElseThrow()
private val CFRunLoopTimerSetNextFireDate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerSetNextFireDate_ADDR, CFRunLoopTimerSetNextFireDate_DESC)

fun CFRunLoopTimerSetNextFireDate(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CFRunLoopTimerSetNextFireDate_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerGetInterval typedef CFTimeInterval = Double(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*)
 */
private val CFRunLoopTimerGetInterval_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFRunLoopTimerGetInterval_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerGetInterval").orElseThrow()
private val CFRunLoopTimerGetInterval_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerGetInterval_ADDR, CFRunLoopTimerGetInterval_DESC)

fun CFRunLoopTimerGetInterval(arg0: MemorySegment): Double {
    try {
        return CFRunLoopTimerGetInterval_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerDoesRepeat typedef Boolean = UNSIGNED = Char(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*)
 */
private val CFRunLoopTimerDoesRepeat_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFRunLoopTimerDoesRepeat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerDoesRepeat").orElseThrow()
private val CFRunLoopTimerDoesRepeat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerDoesRepeat_ADDR, CFRunLoopTimerDoesRepeat_DESC)

fun CFRunLoopTimerDoesRepeat(arg0: MemorySegment): Byte {
    try {
        return CFRunLoopTimerDoesRepeat_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerGetOrder typedef CFIndex = Long(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*)
 */
private val CFRunLoopTimerGetOrder_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFRunLoopTimerGetOrder_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerGetOrder").orElseThrow()
private val CFRunLoopTimerGetOrder_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerGetOrder_ADDR, CFRunLoopTimerGetOrder_DESC)

fun CFRunLoopTimerGetOrder(arg0: MemorySegment): Long {
    try {
        return CFRunLoopTimerGetOrder_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerInvalidate Void(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*)
 */
private val CFRunLoopTimerInvalidate_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFRunLoopTimerInvalidate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerInvalidate").orElseThrow()
private val CFRunLoopTimerInvalidate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerInvalidate_ADDR, CFRunLoopTimerInvalidate_DESC)

fun CFRunLoopTimerInvalidate(arg0: MemorySegment): Unit {
    try {
        CFRunLoopTimerInvalidate_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerIsValid typedef Boolean = UNSIGNED = Char(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*)
 */
private val CFRunLoopTimerIsValid_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFRunLoopTimerIsValid_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerIsValid").orElseThrow()
private val CFRunLoopTimerIsValid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerIsValid_ADDR, CFRunLoopTimerIsValid_DESC)

fun CFRunLoopTimerIsValid(arg0: MemorySegment): Byte {
    try {
        return CFRunLoopTimerIsValid_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerGetContext Void(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*,(typedef CFRunLoopTimerContext = Declared(CFRunLoopTimerContext))*)
 */
private val CFRunLoopTimerGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFRunLoopTimerGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerGetContext").orElseThrow()
private val CFRunLoopTimerGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerGetContext_ADDR, CFRunLoopTimerGetContext_DESC)

fun CFRunLoopTimerGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFRunLoopTimerGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerGetTolerance typedef CFTimeInterval = Double(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*)
 */
private val CFRunLoopTimerGetTolerance_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CFRunLoopTimerGetTolerance_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerGetTolerance").orElseThrow()
private val CFRunLoopTimerGetTolerance_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerGetTolerance_ADDR, CFRunLoopTimerGetTolerance_DESC)

fun CFRunLoopTimerGetTolerance(arg0: MemorySegment): Double {
    try {
        return CFRunLoopTimerGetTolerance_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFRunLoopTimerSetTolerance Void(typedef CFRunLoopTimerRef = (Declared(__CFRunLoopTimer))*,typedef CFTimeInterval = Double)
 */
private val CFRunLoopTimerSetTolerance_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFRunLoopTimerSetTolerance_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFRunLoopTimerSetTolerance").orElseThrow()
private val CFRunLoopTimerSetTolerance_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFRunLoopTimerSetTolerance_ADDR, CFRunLoopTimerSetTolerance_DESC)

fun CFRunLoopTimerSetTolerance(arg0: MemorySegment, arg1: Double): Unit {
    try {
        CFRunLoopTimerSetTolerance_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFSocketGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFSocketGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketGetTypeID").orElseThrow()
private val CFSocketGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketGetTypeID_ADDR, CFSocketGetTypeID_DESC)

fun CFSocketGetTypeID(): Long {
    try {
        return CFSocketGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketCreate typedef CFSocketRef = (Declared(__CFSocket))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef SInt32 = Int,typedef SInt32 = Int,typedef SInt32 = Int,typedef CFOptionFlags = UNSIGNED = Long,typedef CFSocketCallBack = (Void((Declared(__CFSocket))*,<error: enum CFSocketCallBackType>,(Declared(__CFData))*,(Void)*,(Void)*))*,(typedef CFSocketContext = Declared(CFSocketContext))*)
 */
private val CFSocketCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSocketCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketCreate").orElseThrow()
private val CFSocketCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketCreate_ADDR, CFSocketCreate_DESC)

fun CFSocketCreate(arg0: MemorySegment, arg1: Int, arg2: Int, arg3: Int, arg4: Long, arg5: MemorySegment, arg6: MemorySegment): MemorySegment {
    try {
        return CFSocketCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketCreateWithNative typedef CFSocketRef = (Declared(__CFSocket))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFSocketNativeHandle = Int,typedef CFOptionFlags = UNSIGNED = Long,typedef CFSocketCallBack = (Void((Declared(__CFSocket))*,<error: enum CFSocketCallBackType>,(Declared(__CFData))*,(Void)*,(Void)*))*,(typedef CFSocketContext = Declared(CFSocketContext))*)
 */
private val CFSocketCreateWithNative_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSocketCreateWithNative_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketCreateWithNative").orElseThrow()
private val CFSocketCreateWithNative_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketCreateWithNative_ADDR, CFSocketCreateWithNative_DESC)

fun CFSocketCreateWithNative(arg0: MemorySegment, arg1: Int, arg2: Long, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFSocketCreateWithNative_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketCreateWithSocketSignature typedef CFSocketRef = (Declared(__CFSocket))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef CFSocketSignature = Declared(CFSocketSignature))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFSocketCallBack = (Void((Declared(__CFSocket))*,<error: enum CFSocketCallBackType>,(Declared(__CFData))*,(Void)*,(Void)*))*,(typedef CFSocketContext = Declared(CFSocketContext))*)
 */
private val CFSocketCreateWithSocketSignature_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSocketCreateWithSocketSignature_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketCreateWithSocketSignature").orElseThrow()
private val CFSocketCreateWithSocketSignature_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketCreateWithSocketSignature_ADDR, CFSocketCreateWithSocketSignature_DESC)

fun CFSocketCreateWithSocketSignature(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFSocketCreateWithSocketSignature_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketCreateConnectedToSocketSignature typedef CFSocketRef = (Declared(__CFSocket))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef CFSocketSignature = Declared(CFSocketSignature))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFSocketCallBack = (Void((Declared(__CFSocket))*,<error: enum CFSocketCallBackType>,(Declared(__CFData))*,(Void)*,(Void)*))*,(typedef CFSocketContext = Declared(CFSocketContext))*,typedef CFTimeInterval = Double)
 */
private val CFSocketCreateConnectedToSocketSignature_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_DOUBLE)
private val CFSocketCreateConnectedToSocketSignature_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketCreateConnectedToSocketSignature").orElseThrow()
private val CFSocketCreateConnectedToSocketSignature_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketCreateConnectedToSocketSignature_ADDR, CFSocketCreateConnectedToSocketSignature_DESC)

fun CFSocketCreateConnectedToSocketSignature(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment, arg4: MemorySegment, arg5: Double): MemorySegment {
    try {
        return CFSocketCreateConnectedToSocketSignature_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketInvalidate Void(typedef CFSocketRef = (Declared(__CFSocket))*)
 */
private val CFSocketInvalidate_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFSocketInvalidate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketInvalidate").orElseThrow()
private val CFSocketInvalidate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketInvalidate_ADDR, CFSocketInvalidate_DESC)

fun CFSocketInvalidate(arg0: MemorySegment): Unit {
    try {
        CFSocketInvalidate_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketIsValid typedef Boolean = UNSIGNED = Char(typedef CFSocketRef = (Declared(__CFSocket))*)
 */
private val CFSocketIsValid_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFSocketIsValid_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketIsValid").orElseThrow()
private val CFSocketIsValid_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketIsValid_ADDR, CFSocketIsValid_DESC)

fun CFSocketIsValid(arg0: MemorySegment): Byte {
    try {
        return CFSocketIsValid_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketCopyAddress typedef CFDataRef = (Declared(__CFData))*(typedef CFSocketRef = (Declared(__CFSocket))*)
 */
private val CFSocketCopyAddress_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSocketCopyAddress_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketCopyAddress").orElseThrow()
private val CFSocketCopyAddress_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketCopyAddress_ADDR, CFSocketCopyAddress_DESC)

fun CFSocketCopyAddress(arg0: MemorySegment): MemorySegment {
    try {
        return CFSocketCopyAddress_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketCopyPeerAddress typedef CFDataRef = (Declared(__CFData))*(typedef CFSocketRef = (Declared(__CFSocket))*)
 */
private val CFSocketCopyPeerAddress_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSocketCopyPeerAddress_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketCopyPeerAddress").orElseThrow()
private val CFSocketCopyPeerAddress_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketCopyPeerAddress_ADDR, CFSocketCopyPeerAddress_DESC)

fun CFSocketCopyPeerAddress(arg0: MemorySegment): MemorySegment {
    try {
        return CFSocketCopyPeerAddress_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketGetContext Void(typedef CFSocketRef = (Declared(__CFSocket))*,(typedef CFSocketContext = Declared(CFSocketContext))*)
 */
private val CFSocketGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSocketGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketGetContext").orElseThrow()
private val CFSocketGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketGetContext_ADDR, CFSocketGetContext_DESC)

fun CFSocketGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFSocketGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketGetNative typedef CFSocketNativeHandle = Int(typedef CFSocketRef = (Declared(__CFSocket))*)
 */
private val CFSocketGetNative_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CFSocketGetNative_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketGetNative").orElseThrow()
private val CFSocketGetNative_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketGetNative_ADDR, CFSocketGetNative_DESC)

fun CFSocketGetNative(arg0: MemorySegment): Int {
    try {
        return CFSocketGetNative_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketCreateRunLoopSource typedef CFRunLoopSourceRef = (Declared(__CFRunLoopSource))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFSocketRef = (Declared(__CFSocket))*,typedef CFIndex = Long)
 */
private val CFSocketCreateRunLoopSource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFSocketCreateRunLoopSource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketCreateRunLoopSource").orElseThrow()
private val CFSocketCreateRunLoopSource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketCreateRunLoopSource_ADDR, CFSocketCreateRunLoopSource_DESC)

fun CFSocketCreateRunLoopSource(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFSocketCreateRunLoopSource_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketGetSocketFlags typedef CFOptionFlags = UNSIGNED = Long(typedef CFSocketRef = (Declared(__CFSocket))*)
 */
private val CFSocketGetSocketFlags_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFSocketGetSocketFlags_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketGetSocketFlags").orElseThrow()
private val CFSocketGetSocketFlags_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketGetSocketFlags_ADDR, CFSocketGetSocketFlags_DESC)

fun CFSocketGetSocketFlags(arg0: MemorySegment): Long {
    try {
        return CFSocketGetSocketFlags_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketSetSocketFlags Void(typedef CFSocketRef = (Declared(__CFSocket))*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFSocketSetSocketFlags_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFSocketSetSocketFlags_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketSetSocketFlags").orElseThrow()
private val CFSocketSetSocketFlags_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketSetSocketFlags_ADDR, CFSocketSetSocketFlags_DESC)

fun CFSocketSetSocketFlags(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFSocketSetSocketFlags_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketDisableCallBacks Void(typedef CFSocketRef = (Declared(__CFSocket))*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFSocketDisableCallBacks_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFSocketDisableCallBacks_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketDisableCallBacks").orElseThrow()
private val CFSocketDisableCallBacks_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketDisableCallBacks_ADDR, CFSocketDisableCallBacks_DESC)

fun CFSocketDisableCallBacks(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFSocketDisableCallBacks_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketEnableCallBacks Void(typedef CFSocketRef = (Declared(__CFSocket))*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFSocketEnableCallBacks_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFSocketEnableCallBacks_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketEnableCallBacks").orElseThrow()
private val CFSocketEnableCallBacks_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketEnableCallBacks_ADDR, CFSocketEnableCallBacks_DESC)

fun CFSocketEnableCallBacks(arg0: MemorySegment, arg1: Long): Unit {
    try {
        CFSocketEnableCallBacks_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketSetDefaultNameRegistryPortNumber Void(typedef UInt16 = UNSIGNED = Short)
 */
private val CFSocketSetDefaultNameRegistryPortNumber_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.JAVA_SHORT)
private val CFSocketSetDefaultNameRegistryPortNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketSetDefaultNameRegistryPortNumber").orElseThrow()
private val CFSocketSetDefaultNameRegistryPortNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketSetDefaultNameRegistryPortNumber_ADDR, CFSocketSetDefaultNameRegistryPortNumber_DESC)

fun CFSocketSetDefaultNameRegistryPortNumber(arg0: Short): Unit {
    try {
        CFSocketSetDefaultNameRegistryPortNumber_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSocketGetDefaultNameRegistryPortNumber typedef UInt16 = UNSIGNED = Short()
 */
private val CFSocketGetDefaultNameRegistryPortNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_SHORT)
private val CFSocketGetDefaultNameRegistryPortNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSocketGetDefaultNameRegistryPortNumber").orElseThrow()
private val CFSocketGetDefaultNameRegistryPortNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSocketGetDefaultNameRegistryPortNumber_ADDR, CFSocketGetDefaultNameRegistryPortNumber_DESC)

fun CFSocketGetDefaultNameRegistryPortNumber(): Short {
    try {
        return CFSocketGetDefaultNameRegistryPortNumber_HANDLE.invokeExact() as Short
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFSocketCommandKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFSocketCommandKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFSocketCommandKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFSocketCommandKey").orElseThrow() }
private val kCFSocketCommandKey_VH: VarHandle by lazy { kCFSocketCommandKey_LAYOUT.varHandle() }

var kCFSocketCommandKey: MemorySegment
    get() = kCFSocketCommandKey_VH.get(kCFSocketCommandKey_SEGMENT) as MemorySegment
    set(value) = kCFSocketCommandKey_VH.set(kCFSocketCommandKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFSocketNameKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFSocketNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFSocketNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFSocketNameKey").orElseThrow() }
private val kCFSocketNameKey_VH: VarHandle by lazy { kCFSocketNameKey_LAYOUT.varHandle() }

var kCFSocketNameKey: MemorySegment
    get() = kCFSocketNameKey_VH.get(kCFSocketNameKey_SEGMENT) as MemorySegment
    set(value) = kCFSocketNameKey_VH.set(kCFSocketNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFSocketValueKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFSocketValueKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFSocketValueKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFSocketValueKey").orElseThrow() }
private val kCFSocketValueKey_VH: VarHandle by lazy { kCFSocketValueKey_LAYOUT.varHandle() }

var kCFSocketValueKey: MemorySegment
    get() = kCFSocketValueKey_VH.get(kCFSocketValueKey_SEGMENT) as MemorySegment
    set(value) = kCFSocketValueKey_VH.set(kCFSocketValueKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFSocketResultKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFSocketResultKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFSocketResultKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFSocketResultKey").orElseThrow() }
private val kCFSocketResultKey_VH: VarHandle by lazy { kCFSocketResultKey_LAYOUT.varHandle() }

var kCFSocketResultKey: MemorySegment
    get() = kCFSocketResultKey_VH.get(kCFSocketResultKey_SEGMENT) as MemorySegment
    set(value) = kCFSocketResultKey_VH.set(kCFSocketResultKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFSocketErrorKey typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFSocketErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFSocketErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFSocketErrorKey").orElseThrow() }
private val kCFSocketErrorKey_VH: VarHandle by lazy { kCFSocketErrorKey_LAYOUT.varHandle() }

var kCFSocketErrorKey: MemorySegment
    get() = kCFSocketErrorKey_VH.get(kCFSocketErrorKey_SEGMENT) as MemorySegment
    set(value) = kCFSocketErrorKey_VH.set(kCFSocketErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : kCFSocketRegisterCommand typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFSocketRegisterCommand_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFSocketRegisterCommand_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFSocketRegisterCommand").orElseThrow() }
private val kCFSocketRegisterCommand_VH: VarHandle by lazy { kCFSocketRegisterCommand_LAYOUT.varHandle() }

var kCFSocketRegisterCommand: MemorySegment
    get() = kCFSocketRegisterCommand_VH.get(kCFSocketRegisterCommand_SEGMENT) as MemorySegment
    set(value) = kCFSocketRegisterCommand_VH.set(kCFSocketRegisterCommand_SEGMENT, value)

/**
 * {@snippet lang=c : kCFSocketRetrieveCommand typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFSocketRetrieveCommand_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFSocketRetrieveCommand_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFSocketRetrieveCommand").orElseThrow() }
private val kCFSocketRetrieveCommand_VH: VarHandle by lazy { kCFSocketRetrieveCommand_LAYOUT.varHandle() }

var kCFSocketRetrieveCommand: MemorySegment
    get() = kCFSocketRetrieveCommand_VH.get(kCFSocketRetrieveCommand_SEGMENT) as MemorySegment
    set(value) = kCFSocketRetrieveCommand_VH.set(kCFSocketRetrieveCommand_SEGMENT, value)

/**
 * {@snippet lang=c : CFReadStreamGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFReadStreamGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFReadStreamGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamGetTypeID").orElseThrow()
private val CFReadStreamGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamGetTypeID_ADDR, CFReadStreamGetTypeID_DESC)

fun CFReadStreamGetTypeID(): Long {
    try {
        return CFReadStreamGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFWriteStreamGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFWriteStreamGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamGetTypeID").orElseThrow()
private val CFWriteStreamGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamGetTypeID_ADDR, CFWriteStreamGetTypeID_DESC)

fun CFWriteStreamGetTypeID(): Long {
    try {
        return CFWriteStreamGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFStreamPropertyDataWritten typedef CFStreamPropertyKey = (Declared(__CFString))*
 */
private val kCFStreamPropertyDataWritten_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertyDataWritten_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertyDataWritten").orElseThrow() }
private val kCFStreamPropertyDataWritten_VH: VarHandle by lazy { kCFStreamPropertyDataWritten_LAYOUT.varHandle() }

var kCFStreamPropertyDataWritten: MemorySegment
    get() = kCFStreamPropertyDataWritten_VH.get(kCFStreamPropertyDataWritten_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertyDataWritten_VH.set(kCFStreamPropertyDataWritten_SEGMENT, value)

/**
 * {@snippet lang=c : CFReadStreamCreateWithBytesNoCopy typedef CFReadStreamRef = (Declared(__CFReadStream))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long,typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFReadStreamCreateWithBytesNoCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFReadStreamCreateWithBytesNoCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamCreateWithBytesNoCopy").orElseThrow()
private val CFReadStreamCreateWithBytesNoCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamCreateWithBytesNoCopy_ADDR, CFReadStreamCreateWithBytesNoCopy_DESC)

fun CFReadStreamCreateWithBytesNoCopy(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CFReadStreamCreateWithBytesNoCopy_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamCreateWithBuffer typedef CFWriteStreamRef = (Declared(__CFWriteStream))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long)
 */
private val CFWriteStreamCreateWithBuffer_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFWriteStreamCreateWithBuffer_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamCreateWithBuffer").orElseThrow()
private val CFWriteStreamCreateWithBuffer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamCreateWithBuffer_ADDR, CFWriteStreamCreateWithBuffer_DESC)

fun CFWriteStreamCreateWithBuffer(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFWriteStreamCreateWithBuffer_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamCreateWithAllocatedBuffers typedef CFWriteStreamRef = (Declared(__CFWriteStream))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFAllocatorRef = (Declared(__CFAllocator))*)
 */
private val CFWriteStreamCreateWithAllocatedBuffers_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamCreateWithAllocatedBuffers_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamCreateWithAllocatedBuffers").orElseThrow()
private val CFWriteStreamCreateWithAllocatedBuffers_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamCreateWithAllocatedBuffers_ADDR, CFWriteStreamCreateWithAllocatedBuffers_DESC)

fun CFWriteStreamCreateWithAllocatedBuffers(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFWriteStreamCreateWithAllocatedBuffers_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamCreateWithFile typedef CFReadStreamRef = (Declared(__CFReadStream))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFReadStreamCreateWithFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFReadStreamCreateWithFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamCreateWithFile").orElseThrow()
private val CFReadStreamCreateWithFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamCreateWithFile_ADDR, CFReadStreamCreateWithFile_DESC)

fun CFReadStreamCreateWithFile(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFReadStreamCreateWithFile_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamCreateWithFile typedef CFWriteStreamRef = (Declared(__CFWriteStream))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CFWriteStreamCreateWithFile_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamCreateWithFile_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamCreateWithFile").orElseThrow()
private val CFWriteStreamCreateWithFile_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamCreateWithFile_ADDR, CFWriteStreamCreateWithFile_DESC)

fun CFWriteStreamCreateWithFile(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFWriteStreamCreateWithFile_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStreamCreateBoundPair Void(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef CFReadStreamRef = (Declared(__CFReadStream))*)*,(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)*,typedef CFIndex = Long)
 */
private val CFStreamCreateBoundPair_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFStreamCreateBoundPair_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStreamCreateBoundPair").orElseThrow()
private val CFStreamCreateBoundPair_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStreamCreateBoundPair_ADDR, CFStreamCreateBoundPair_DESC)

fun CFStreamCreateBoundPair(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: Long): Unit {
    try {
        CFStreamCreateBoundPair_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFStreamPropertyAppendToFile typedef CFStreamPropertyKey = (Declared(__CFString))*
 */
private val kCFStreamPropertyAppendToFile_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertyAppendToFile_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertyAppendToFile").orElseThrow() }
private val kCFStreamPropertyAppendToFile_VH: VarHandle by lazy { kCFStreamPropertyAppendToFile_LAYOUT.varHandle() }

var kCFStreamPropertyAppendToFile: MemorySegment
    get() = kCFStreamPropertyAppendToFile_VH.get(kCFStreamPropertyAppendToFile_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertyAppendToFile_VH.set(kCFStreamPropertyAppendToFile_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertyFileCurrentOffset typedef CFStreamPropertyKey = (Declared(__CFString))*
 */
private val kCFStreamPropertyFileCurrentOffset_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertyFileCurrentOffset_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertyFileCurrentOffset").orElseThrow() }
private val kCFStreamPropertyFileCurrentOffset_VH: VarHandle by lazy { kCFStreamPropertyFileCurrentOffset_LAYOUT.varHandle() }

var kCFStreamPropertyFileCurrentOffset: MemorySegment
    get() = kCFStreamPropertyFileCurrentOffset_VH.get(kCFStreamPropertyFileCurrentOffset_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertyFileCurrentOffset_VH.set(kCFStreamPropertyFileCurrentOffset_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySocketNativeHandle typedef CFStreamPropertyKey = (Declared(__CFString))*
 */
private val kCFStreamPropertySocketNativeHandle_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySocketNativeHandle_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySocketNativeHandle").orElseThrow() }
private val kCFStreamPropertySocketNativeHandle_VH: VarHandle by lazy { kCFStreamPropertySocketNativeHandle_LAYOUT.varHandle() }

var kCFStreamPropertySocketNativeHandle: MemorySegment
    get() = kCFStreamPropertySocketNativeHandle_VH.get(kCFStreamPropertySocketNativeHandle_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySocketNativeHandle_VH.set(kCFStreamPropertySocketNativeHandle_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySocketRemoteHostName typedef CFStreamPropertyKey = (Declared(__CFString))*
 */
private val kCFStreamPropertySocketRemoteHostName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySocketRemoteHostName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySocketRemoteHostName").orElseThrow() }
private val kCFStreamPropertySocketRemoteHostName_VH: VarHandle by lazy { kCFStreamPropertySocketRemoteHostName_LAYOUT.varHandle() }

var kCFStreamPropertySocketRemoteHostName: MemorySegment
    get() = kCFStreamPropertySocketRemoteHostName_VH.get(kCFStreamPropertySocketRemoteHostName_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySocketRemoteHostName_VH.set(kCFStreamPropertySocketRemoteHostName_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySocketRemotePortNumber typedef CFStreamPropertyKey = (Declared(__CFString))*
 */
private val kCFStreamPropertySocketRemotePortNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySocketRemotePortNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySocketRemotePortNumber").orElseThrow() }
private val kCFStreamPropertySocketRemotePortNumber_VH: VarHandle by lazy { kCFStreamPropertySocketRemotePortNumber_LAYOUT.varHandle() }

var kCFStreamPropertySocketRemotePortNumber: MemorySegment
    get() = kCFStreamPropertySocketRemotePortNumber_VH.get(kCFStreamPropertySocketRemotePortNumber_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySocketRemotePortNumber_VH.set(kCFStreamPropertySocketRemotePortNumber_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamErrorDomainSOCKS Int
 */
private val kCFStreamErrorDomainSOCKS_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_INT }
private val kCFStreamErrorDomainSOCKS_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamErrorDomainSOCKS").orElseThrow() }
private val kCFStreamErrorDomainSOCKS_VH: VarHandle by lazy { kCFStreamErrorDomainSOCKS_LAYOUT.varHandle() }

var kCFStreamErrorDomainSOCKS: Int
    get() = kCFStreamErrorDomainSOCKS_VH.get(kCFStreamErrorDomainSOCKS_SEGMENT) as Int
    set(value) = kCFStreamErrorDomainSOCKS_VH.set(kCFStreamErrorDomainSOCKS_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySOCKSProxy typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamPropertySOCKSProxy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySOCKSProxy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySOCKSProxy").orElseThrow() }
private val kCFStreamPropertySOCKSProxy_VH: VarHandle by lazy { kCFStreamPropertySOCKSProxy_LAYOUT.varHandle() }

var kCFStreamPropertySOCKSProxy: MemorySegment
    get() = kCFStreamPropertySOCKSProxy_VH.get(kCFStreamPropertySOCKSProxy_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySOCKSProxy_VH.set(kCFStreamPropertySOCKSProxy_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySOCKSProxyHost typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamPropertySOCKSProxyHost_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySOCKSProxyHost_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySOCKSProxyHost").orElseThrow() }
private val kCFStreamPropertySOCKSProxyHost_VH: VarHandle by lazy { kCFStreamPropertySOCKSProxyHost_LAYOUT.varHandle() }

var kCFStreamPropertySOCKSProxyHost: MemorySegment
    get() = kCFStreamPropertySOCKSProxyHost_VH.get(kCFStreamPropertySOCKSProxyHost_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySOCKSProxyHost_VH.set(kCFStreamPropertySOCKSProxyHost_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySOCKSProxyPort typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamPropertySOCKSProxyPort_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySOCKSProxyPort_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySOCKSProxyPort").orElseThrow() }
private val kCFStreamPropertySOCKSProxyPort_VH: VarHandle by lazy { kCFStreamPropertySOCKSProxyPort_LAYOUT.varHandle() }

var kCFStreamPropertySOCKSProxyPort: MemorySegment
    get() = kCFStreamPropertySOCKSProxyPort_VH.get(kCFStreamPropertySOCKSProxyPort_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySOCKSProxyPort_VH.set(kCFStreamPropertySOCKSProxyPort_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySOCKSVersion typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamPropertySOCKSVersion_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySOCKSVersion_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySOCKSVersion").orElseThrow() }
private val kCFStreamPropertySOCKSVersion_VH: VarHandle by lazy { kCFStreamPropertySOCKSVersion_LAYOUT.varHandle() }

var kCFStreamPropertySOCKSVersion: MemorySegment
    get() = kCFStreamPropertySOCKSVersion_VH.get(kCFStreamPropertySOCKSVersion_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySOCKSVersion_VH.set(kCFStreamPropertySOCKSVersion_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamSocketSOCKSVersion4 typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamSocketSOCKSVersion4_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamSocketSOCKSVersion4_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamSocketSOCKSVersion4").orElseThrow() }
private val kCFStreamSocketSOCKSVersion4_VH: VarHandle by lazy { kCFStreamSocketSOCKSVersion4_LAYOUT.varHandle() }

var kCFStreamSocketSOCKSVersion4: MemorySegment
    get() = kCFStreamSocketSOCKSVersion4_VH.get(kCFStreamSocketSOCKSVersion4_SEGMENT) as MemorySegment
    set(value) = kCFStreamSocketSOCKSVersion4_VH.set(kCFStreamSocketSOCKSVersion4_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamSocketSOCKSVersion5 typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamSocketSOCKSVersion5_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamSocketSOCKSVersion5_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamSocketSOCKSVersion5").orElseThrow() }
private val kCFStreamSocketSOCKSVersion5_VH: VarHandle by lazy { kCFStreamSocketSOCKSVersion5_LAYOUT.varHandle() }

var kCFStreamSocketSOCKSVersion5: MemorySegment
    get() = kCFStreamSocketSOCKSVersion5_VH.get(kCFStreamSocketSOCKSVersion5_SEGMENT) as MemorySegment
    set(value) = kCFStreamSocketSOCKSVersion5_VH.set(kCFStreamSocketSOCKSVersion5_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySOCKSUser typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamPropertySOCKSUser_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySOCKSUser_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySOCKSUser").orElseThrow() }
private val kCFStreamPropertySOCKSUser_VH: VarHandle by lazy { kCFStreamPropertySOCKSUser_LAYOUT.varHandle() }

var kCFStreamPropertySOCKSUser: MemorySegment
    get() = kCFStreamPropertySOCKSUser_VH.get(kCFStreamPropertySOCKSUser_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySOCKSUser_VH.set(kCFStreamPropertySOCKSUser_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySOCKSPassword typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamPropertySOCKSPassword_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySOCKSPassword_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySOCKSPassword").orElseThrow() }
private val kCFStreamPropertySOCKSPassword_VH: VarHandle by lazy { kCFStreamPropertySOCKSPassword_LAYOUT.varHandle() }

var kCFStreamPropertySOCKSPassword: MemorySegment
    get() = kCFStreamPropertySOCKSPassword_VH.get(kCFStreamPropertySOCKSPassword_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySOCKSPassword_VH.set(kCFStreamPropertySOCKSPassword_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamErrorDomainSSL Int
 */
private val kCFStreamErrorDomainSSL_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_INT }
private val kCFStreamErrorDomainSSL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamErrorDomainSSL").orElseThrow() }
private val kCFStreamErrorDomainSSL_VH: VarHandle by lazy { kCFStreamErrorDomainSSL_LAYOUT.varHandle() }

var kCFStreamErrorDomainSSL: Int
    get() = kCFStreamErrorDomainSSL_VH.get(kCFStreamErrorDomainSSL_SEGMENT) as Int
    set(value) = kCFStreamErrorDomainSSL_VH.set(kCFStreamErrorDomainSSL_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertySocketSecurityLevel typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamPropertySocketSecurityLevel_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertySocketSecurityLevel_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertySocketSecurityLevel").orElseThrow() }
private val kCFStreamPropertySocketSecurityLevel_VH: VarHandle by lazy { kCFStreamPropertySocketSecurityLevel_LAYOUT.varHandle() }

var kCFStreamPropertySocketSecurityLevel: MemorySegment
    get() = kCFStreamPropertySocketSecurityLevel_VH.get(kCFStreamPropertySocketSecurityLevel_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertySocketSecurityLevel_VH.set(kCFStreamPropertySocketSecurityLevel_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamSocketSecurityLevelNone typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamSocketSecurityLevelNone_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamSocketSecurityLevelNone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamSocketSecurityLevelNone").orElseThrow() }
private val kCFStreamSocketSecurityLevelNone_VH: VarHandle by lazy { kCFStreamSocketSecurityLevelNone_LAYOUT.varHandle() }

var kCFStreamSocketSecurityLevelNone: MemorySegment
    get() = kCFStreamSocketSecurityLevelNone_VH.get(kCFStreamSocketSecurityLevelNone_SEGMENT) as MemorySegment
    set(value) = kCFStreamSocketSecurityLevelNone_VH.set(kCFStreamSocketSecurityLevelNone_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamSocketSecurityLevelSSLv2 typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamSocketSecurityLevelSSLv2_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamSocketSecurityLevelSSLv2_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamSocketSecurityLevelSSLv2").orElseThrow() }
private val kCFStreamSocketSecurityLevelSSLv2_VH: VarHandle by lazy { kCFStreamSocketSecurityLevelSSLv2_LAYOUT.varHandle() }

var kCFStreamSocketSecurityLevelSSLv2: MemorySegment
    get() = kCFStreamSocketSecurityLevelSSLv2_VH.get(kCFStreamSocketSecurityLevelSSLv2_SEGMENT) as MemorySegment
    set(value) = kCFStreamSocketSecurityLevelSSLv2_VH.set(kCFStreamSocketSecurityLevelSSLv2_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamSocketSecurityLevelSSLv3 typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamSocketSecurityLevelSSLv3_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamSocketSecurityLevelSSLv3_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamSocketSecurityLevelSSLv3").orElseThrow() }
private val kCFStreamSocketSecurityLevelSSLv3_VH: VarHandle by lazy { kCFStreamSocketSecurityLevelSSLv3_LAYOUT.varHandle() }

var kCFStreamSocketSecurityLevelSSLv3: MemorySegment
    get() = kCFStreamSocketSecurityLevelSSLv3_VH.get(kCFStreamSocketSecurityLevelSSLv3_SEGMENT) as MemorySegment
    set(value) = kCFStreamSocketSecurityLevelSSLv3_VH.set(kCFStreamSocketSecurityLevelSSLv3_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamSocketSecurityLevelTLSv1 typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamSocketSecurityLevelTLSv1_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamSocketSecurityLevelTLSv1_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamSocketSecurityLevelTLSv1").orElseThrow() }
private val kCFStreamSocketSecurityLevelTLSv1_VH: VarHandle by lazy { kCFStreamSocketSecurityLevelTLSv1_LAYOUT.varHandle() }

var kCFStreamSocketSecurityLevelTLSv1: MemorySegment
    get() = kCFStreamSocketSecurityLevelTLSv1_VH.get(kCFStreamSocketSecurityLevelTLSv1_SEGMENT) as MemorySegment
    set(value) = kCFStreamSocketSecurityLevelTLSv1_VH.set(kCFStreamSocketSecurityLevelTLSv1_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamSocketSecurityLevelNegotiatedSSL typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamSocketSecurityLevelNegotiatedSSL_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamSocketSecurityLevelNegotiatedSSL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamSocketSecurityLevelNegotiatedSSL").orElseThrow() }
private val kCFStreamSocketSecurityLevelNegotiatedSSL_VH: VarHandle by lazy { kCFStreamSocketSecurityLevelNegotiatedSSL_LAYOUT.varHandle() }

var kCFStreamSocketSecurityLevelNegotiatedSSL: MemorySegment
    get() = kCFStreamSocketSecurityLevelNegotiatedSSL_VH.get(kCFStreamSocketSecurityLevelNegotiatedSSL_SEGMENT) as MemorySegment
    set(value) = kCFStreamSocketSecurityLevelNegotiatedSSL_VH.set(kCFStreamSocketSecurityLevelNegotiatedSSL_SEGMENT, value)

/**
 * {@snippet lang=c : kCFStreamPropertyShouldCloseNativeSocket typedef CFStringRef = (Declared(__CFString))*
 */
private val kCFStreamPropertyShouldCloseNativeSocket_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFStreamPropertyShouldCloseNativeSocket_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFStreamPropertyShouldCloseNativeSocket").orElseThrow() }
private val kCFStreamPropertyShouldCloseNativeSocket_VH: VarHandle by lazy { kCFStreamPropertyShouldCloseNativeSocket_LAYOUT.varHandle() }

var kCFStreamPropertyShouldCloseNativeSocket: MemorySegment
    get() = kCFStreamPropertyShouldCloseNativeSocket_VH.get(kCFStreamPropertyShouldCloseNativeSocket_SEGMENT) as MemorySegment
    set(value) = kCFStreamPropertyShouldCloseNativeSocket_VH.set(kCFStreamPropertyShouldCloseNativeSocket_SEGMENT, value)

/**
 * {@snippet lang=c : CFStreamCreatePairWithSocket Void(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFSocketNativeHandle = Int,(typedef CFReadStreamRef = (Declared(__CFReadStream))*)*,(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)*)
 */
private val CFStreamCreatePairWithSocket_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStreamCreatePairWithSocket_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStreamCreatePairWithSocket").orElseThrow()
private val CFStreamCreatePairWithSocket_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStreamCreatePairWithSocket_ADDR, CFStreamCreatePairWithSocket_DESC)

fun CFStreamCreatePairWithSocket(arg0: MemorySegment, arg1: Int, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        CFStreamCreatePairWithSocket_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStreamCreatePairWithSocketToHost Void(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFStringRef = (Declared(__CFString))*,typedef UInt32 = UNSIGNED = Int,(typedef CFReadStreamRef = (Declared(__CFReadStream))*)*,(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)*)
 */
private val CFStreamCreatePairWithSocketToHost_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStreamCreatePairWithSocketToHost_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStreamCreatePairWithSocketToHost").orElseThrow()
private val CFStreamCreatePairWithSocketToHost_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStreamCreatePairWithSocketToHost_ADDR, CFStreamCreatePairWithSocketToHost_DESC)

fun CFStreamCreatePairWithSocketToHost(arg0: MemorySegment, arg1: MemorySegment, arg2: Int, arg3: MemorySegment, arg4: MemorySegment): Unit {
    try {
        CFStreamCreatePairWithSocketToHost_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFStreamCreatePairWithPeerSocketSignature Void(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef CFSocketSignature = Declared(CFSocketSignature))*,(typedef CFReadStreamRef = (Declared(__CFReadStream))*)*,(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)*)
 */
private val CFStreamCreatePairWithPeerSocketSignature_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFStreamCreatePairWithPeerSocketSignature_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFStreamCreatePairWithPeerSocketSignature").orElseThrow()
private val CFStreamCreatePairWithPeerSocketSignature_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFStreamCreatePairWithPeerSocketSignature_ADDR, CFStreamCreatePairWithPeerSocketSignature_DESC)

fun CFStreamCreatePairWithPeerSocketSignature(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Unit {
    try {
        CFStreamCreatePairWithPeerSocketSignature_HANDLE.invokeExact(arg0, arg1, arg2, arg3)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamCopyError typedef CFErrorRef = (Declared(__CFError))*(typedef CFReadStreamRef = (Declared(__CFReadStream))*)
 */
private val CFReadStreamCopyError_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFReadStreamCopyError_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamCopyError").orElseThrow()
private val CFReadStreamCopyError_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamCopyError_ADDR, CFReadStreamCopyError_DESC)

fun CFReadStreamCopyError(arg0: MemorySegment): MemorySegment {
    try {
        return CFReadStreamCopyError_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamCopyError typedef CFErrorRef = (Declared(__CFError))*(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)
 */
private val CFWriteStreamCopyError_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamCopyError_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamCopyError").orElseThrow()
private val CFWriteStreamCopyError_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamCopyError_ADDR, CFWriteStreamCopyError_DESC)

fun CFWriteStreamCopyError(arg0: MemorySegment): MemorySegment {
    try {
        return CFWriteStreamCopyError_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamOpen typedef Boolean = UNSIGNED = Char(typedef CFReadStreamRef = (Declared(__CFReadStream))*)
 */
private val CFReadStreamOpen_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFReadStreamOpen_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamOpen").orElseThrow()
private val CFReadStreamOpen_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamOpen_ADDR, CFReadStreamOpen_DESC)

fun CFReadStreamOpen(arg0: MemorySegment): Byte {
    try {
        return CFReadStreamOpen_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamOpen typedef Boolean = UNSIGNED = Char(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)
 */
private val CFWriteStreamOpen_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFWriteStreamOpen_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamOpen").orElseThrow()
private val CFWriteStreamOpen_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamOpen_ADDR, CFWriteStreamOpen_DESC)

fun CFWriteStreamOpen(arg0: MemorySegment): Byte {
    try {
        return CFWriteStreamOpen_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamClose Void(typedef CFReadStreamRef = (Declared(__CFReadStream))*)
 */
private val CFReadStreamClose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFReadStreamClose_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamClose").orElseThrow()
private val CFReadStreamClose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamClose_ADDR, CFReadStreamClose_DESC)

fun CFReadStreamClose(arg0: MemorySegment): Unit {
    try {
        CFReadStreamClose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamClose Void(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)
 */
private val CFWriteStreamClose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFWriteStreamClose_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamClose").orElseThrow()
private val CFWriteStreamClose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamClose_ADDR, CFWriteStreamClose_DESC)

fun CFWriteStreamClose(arg0: MemorySegment): Unit {
    try {
        CFWriteStreamClose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamHasBytesAvailable typedef Boolean = UNSIGNED = Char(typedef CFReadStreamRef = (Declared(__CFReadStream))*)
 */
private val CFReadStreamHasBytesAvailable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFReadStreamHasBytesAvailable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamHasBytesAvailable").orElseThrow()
private val CFReadStreamHasBytesAvailable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamHasBytesAvailable_ADDR, CFReadStreamHasBytesAvailable_DESC)

fun CFReadStreamHasBytesAvailable(arg0: MemorySegment): Byte {
    try {
        return CFReadStreamHasBytesAvailable_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamRead typedef CFIndex = Long(typedef CFReadStreamRef = (Declared(__CFReadStream))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long)
 */
private val CFReadStreamRead_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFReadStreamRead_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamRead").orElseThrow()
private val CFReadStreamRead_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamRead_ADDR, CFReadStreamRead_DESC)

fun CFReadStreamRead(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Long {
    try {
        return CFReadStreamRead_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamGetBuffer (typedef UInt8 = UNSIGNED = Char)*(typedef CFReadStreamRef = (Declared(__CFReadStream))*,typedef CFIndex = Long,(typedef CFIndex = Long)*)
 */
private val CFReadStreamGetBuffer_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFReadStreamGetBuffer_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamGetBuffer").orElseThrow()
private val CFReadStreamGetBuffer_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamGetBuffer_ADDR, CFReadStreamGetBuffer_DESC)

fun CFReadStreamGetBuffer(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFReadStreamGetBuffer_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamCanAcceptBytes typedef Boolean = UNSIGNED = Char(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)
 */
private val CFWriteStreamCanAcceptBytes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS)
private val CFWriteStreamCanAcceptBytes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamCanAcceptBytes").orElseThrow()
private val CFWriteStreamCanAcceptBytes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamCanAcceptBytes_ADDR, CFWriteStreamCanAcceptBytes_DESC)

fun CFWriteStreamCanAcceptBytes(arg0: MemorySegment): Byte {
    try {
        return CFWriteStreamCanAcceptBytes_HANDLE.invokeExact(arg0) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamWrite typedef CFIndex = Long(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*,(typedef UInt8 = UNSIGNED = Char)*,typedef CFIndex = Long)
 */
private val CFWriteStreamWrite_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFWriteStreamWrite_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamWrite").orElseThrow()
private val CFWriteStreamWrite_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamWrite_ADDR, CFWriteStreamWrite_DESC)

fun CFWriteStreamWrite(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): Long {
    try {
        return CFWriteStreamWrite_HANDLE.invokeExact(arg0, arg1, arg2) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamCopyProperty typedef CFTypeRef = (Void)*(typedef CFReadStreamRef = (Declared(__CFReadStream))*,typedef CFStreamPropertyKey = (Declared(__CFString))*)
 */
private val CFReadStreamCopyProperty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFReadStreamCopyProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamCopyProperty").orElseThrow()
private val CFReadStreamCopyProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamCopyProperty_ADDR, CFReadStreamCopyProperty_DESC)

fun CFReadStreamCopyProperty(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFReadStreamCopyProperty_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamCopyProperty typedef CFTypeRef = (Void)*(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*,typedef CFStreamPropertyKey = (Declared(__CFString))*)
 */
private val CFWriteStreamCopyProperty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamCopyProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamCopyProperty").orElseThrow()
private val CFWriteStreamCopyProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamCopyProperty_ADDR, CFWriteStreamCopyProperty_DESC)

fun CFWriteStreamCopyProperty(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFWriteStreamCopyProperty_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamSetProperty typedef Boolean = UNSIGNED = Char(typedef CFReadStreamRef = (Declared(__CFReadStream))*,typedef CFStreamPropertyKey = (Declared(__CFString))*,typedef CFTypeRef = (Void)*)
 */
private val CFReadStreamSetProperty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFReadStreamSetProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamSetProperty").orElseThrow()
private val CFReadStreamSetProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamSetProperty_ADDR, CFReadStreamSetProperty_DESC)

fun CFReadStreamSetProperty(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFReadStreamSetProperty_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamSetProperty typedef Boolean = UNSIGNED = Char(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*,typedef CFStreamPropertyKey = (Declared(__CFString))*,typedef CFTypeRef = (Void)*)
 */
private val CFWriteStreamSetProperty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamSetProperty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamSetProperty").orElseThrow()
private val CFWriteStreamSetProperty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamSetProperty_ADDR, CFWriteStreamSetProperty_DESC)

fun CFWriteStreamSetProperty(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFWriteStreamSetProperty_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamSetClient typedef Boolean = UNSIGNED = Char(typedef CFReadStreamRef = (Declared(__CFReadStream))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFReadStreamClientCallBack = (Void((Declared(__CFReadStream))*,<error: enum CFStreamEventType>,(Void)*))*,(typedef CFStreamClientContext = Declared(CFStreamClientContext))*)
 */
private val CFReadStreamSetClient_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFReadStreamSetClient_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamSetClient").orElseThrow()
private val CFReadStreamSetClient_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamSetClient_ADDR, CFReadStreamSetClient_DESC)

fun CFReadStreamSetClient(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment): Byte {
    try {
        return CFReadStreamSetClient_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamSetClient typedef Boolean = UNSIGNED = Char(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*,typedef CFOptionFlags = UNSIGNED = Long,typedef CFWriteStreamClientCallBack = (Void((Declared(__CFWriteStream))*,<error: enum CFStreamEventType>,(Void)*))*,(typedef CFStreamClientContext = Declared(CFStreamClientContext))*)
 */
private val CFWriteStreamSetClient_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamSetClient_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamSetClient").orElseThrow()
private val CFWriteStreamSetClient_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamSetClient_ADDR, CFWriteStreamSetClient_DESC)

fun CFWriteStreamSetClient(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment): Byte {
    try {
        return CFWriteStreamSetClient_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamScheduleWithRunLoop Void(typedef CFReadStreamRef = (Declared(__CFReadStream))*,typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFReadStreamScheduleWithRunLoop_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFReadStreamScheduleWithRunLoop_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamScheduleWithRunLoop").orElseThrow()
private val CFReadStreamScheduleWithRunLoop_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamScheduleWithRunLoop_ADDR, CFReadStreamScheduleWithRunLoop_DESC)

fun CFReadStreamScheduleWithRunLoop(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFReadStreamScheduleWithRunLoop_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamScheduleWithRunLoop Void(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*,typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFWriteStreamScheduleWithRunLoop_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamScheduleWithRunLoop_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamScheduleWithRunLoop").orElseThrow()
private val CFWriteStreamScheduleWithRunLoop_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamScheduleWithRunLoop_ADDR, CFWriteStreamScheduleWithRunLoop_DESC)

fun CFWriteStreamScheduleWithRunLoop(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFWriteStreamScheduleWithRunLoop_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamUnscheduleFromRunLoop Void(typedef CFReadStreamRef = (Declared(__CFReadStream))*,typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFReadStreamUnscheduleFromRunLoop_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFReadStreamUnscheduleFromRunLoop_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamUnscheduleFromRunLoop").orElseThrow()
private val CFReadStreamUnscheduleFromRunLoop_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamUnscheduleFromRunLoop_ADDR, CFReadStreamUnscheduleFromRunLoop_DESC)

fun CFReadStreamUnscheduleFromRunLoop(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFReadStreamUnscheduleFromRunLoop_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamUnscheduleFromRunLoop Void(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*,typedef CFRunLoopRef = (Declared(__CFRunLoop))*,typedef CFRunLoopMode = (Declared(__CFString))*)
 */
private val CFWriteStreamUnscheduleFromRunLoop_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamUnscheduleFromRunLoop_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamUnscheduleFromRunLoop").orElseThrow()
private val CFWriteStreamUnscheduleFromRunLoop_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamUnscheduleFromRunLoop_ADDR, CFWriteStreamUnscheduleFromRunLoop_DESC)

fun CFWriteStreamUnscheduleFromRunLoop(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFWriteStreamUnscheduleFromRunLoop_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamSetDispatchQueue Void(typedef CFReadStreamRef = (Declared(__CFReadStream))*,typedef dispatch_queue_t = (Void)*)
 */
private val CFReadStreamSetDispatchQueue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFReadStreamSetDispatchQueue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamSetDispatchQueue").orElseThrow()
private val CFReadStreamSetDispatchQueue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamSetDispatchQueue_ADDR, CFReadStreamSetDispatchQueue_DESC)

fun CFReadStreamSetDispatchQueue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFReadStreamSetDispatchQueue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamSetDispatchQueue Void(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*,typedef dispatch_queue_t = (Void)*)
 */
private val CFWriteStreamSetDispatchQueue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamSetDispatchQueue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamSetDispatchQueue").orElseThrow()
private val CFWriteStreamSetDispatchQueue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamSetDispatchQueue_ADDR, CFWriteStreamSetDispatchQueue_DESC)

fun CFWriteStreamSetDispatchQueue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFWriteStreamSetDispatchQueue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamCopyDispatchQueue typedef dispatch_queue_t = (Void)*(typedef CFReadStreamRef = (Declared(__CFReadStream))*)
 */
private val CFReadStreamCopyDispatchQueue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFReadStreamCopyDispatchQueue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamCopyDispatchQueue").orElseThrow()
private val CFReadStreamCopyDispatchQueue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamCopyDispatchQueue_ADDR, CFReadStreamCopyDispatchQueue_DESC)

fun CFReadStreamCopyDispatchQueue(arg0: MemorySegment): MemorySegment {
    try {
        return CFReadStreamCopyDispatchQueue_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamCopyDispatchQueue typedef dispatch_queue_t = (Void)*(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)
 */
private val CFWriteStreamCopyDispatchQueue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFWriteStreamCopyDispatchQueue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamCopyDispatchQueue").orElseThrow()
private val CFWriteStreamCopyDispatchQueue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamCopyDispatchQueue_ADDR, CFWriteStreamCopyDispatchQueue_DESC)

fun CFWriteStreamCopyDispatchQueue(arg0: MemorySegment): MemorySegment {
    try {
        return CFWriteStreamCopyDispatchQueue_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFReadStreamGetError typedef CFStreamError = Declared(CFStreamError)(typedef CFReadStreamRef = (Declared(__CFReadStream))*)
 */
private val CFReadStreamGetError_DESC: FunctionDescriptor = FunctionDescriptor.of(CFStreamError.layout, ValueLayout.ADDRESS)
private val CFReadStreamGetError_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFReadStreamGetError").orElseThrow()
private val CFReadStreamGetError_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFReadStreamGetError_ADDR, CFReadStreamGetError_DESC)

fun CFReadStreamGetError(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CFReadStreamGetError_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFWriteStreamGetError typedef CFStreamError = Declared(CFStreamError)(typedef CFWriteStreamRef = (Declared(__CFWriteStream))*)
 */
private val CFWriteStreamGetError_DESC: FunctionDescriptor = FunctionDescriptor.of(CFStreamError.layout, ValueLayout.ADDRESS)
private val CFWriteStreamGetError_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFWriteStreamGetError").orElseThrow()
private val CFWriteStreamGetError_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFWriteStreamGetError_ADDR, CFWriteStreamGetError_DESC)

fun CFWriteStreamGetError(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CFWriteStreamGetError_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPropertyListCreateFromXMLData typedef CFPropertyListRef = (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*,typedef CFOptionFlags = UNSIGNED = Long,(typedef CFStringRef = (Declared(__CFString))*)*)
 */
private val CFPropertyListCreateFromXMLData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFPropertyListCreateFromXMLData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPropertyListCreateFromXMLData").orElseThrow()
private val CFPropertyListCreateFromXMLData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPropertyListCreateFromXMLData_ADDR, CFPropertyListCreateFromXMLData_DESC)

fun CFPropertyListCreateFromXMLData(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CFPropertyListCreateFromXMLData_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPropertyListCreateXMLData typedef CFDataRef = (Declared(__CFData))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFPropertyListRef = (Void)*)
 */
private val CFPropertyListCreateXMLData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPropertyListCreateXMLData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPropertyListCreateXMLData").orElseThrow()
private val CFPropertyListCreateXMLData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPropertyListCreateXMLData_ADDR, CFPropertyListCreateXMLData_DESC)

fun CFPropertyListCreateXMLData(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFPropertyListCreateXMLData_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPropertyListCreateDeepCopy typedef CFPropertyListRef = (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFPropertyListRef = (Void)*,typedef CFOptionFlags = UNSIGNED = Long)
 */
private val CFPropertyListCreateDeepCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFPropertyListCreateDeepCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPropertyListCreateDeepCopy").orElseThrow()
private val CFPropertyListCreateDeepCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPropertyListCreateDeepCopy_ADDR, CFPropertyListCreateDeepCopy_DESC)

fun CFPropertyListCreateDeepCopy(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return CFPropertyListCreateDeepCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPropertyListCreateFromStream typedef CFPropertyListRef = (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFReadStreamRef = (Declared(__CFReadStream))*,typedef CFIndex = Long,typedef CFOptionFlags = UNSIGNED = Long,(typedef CFPropertyListFormat = <error: enum CFPropertyListFormat>)*,(typedef CFStringRef = (Declared(__CFString))*)*)
 */
private val CFPropertyListCreateFromStream_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPropertyListCreateFromStream_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPropertyListCreateFromStream").orElseThrow()
private val CFPropertyListCreateFromStream_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPropertyListCreateFromStream_ADDR, CFPropertyListCreateFromStream_DESC)

fun CFPropertyListCreateFromStream(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long, arg4: MemorySegment, arg5: MemorySegment): MemorySegment {
    try {
        return CFPropertyListCreateFromStream_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPropertyListCreateWithData typedef CFPropertyListRef = (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFDataRef = (Declared(__CFData))*,typedef CFOptionFlags = UNSIGNED = Long,(typedef CFPropertyListFormat = <error: enum CFPropertyListFormat>)*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFPropertyListCreateWithData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPropertyListCreateWithData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPropertyListCreateWithData").orElseThrow()
private val CFPropertyListCreateWithData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPropertyListCreateWithData_ADDR, CFPropertyListCreateWithData_DESC)

fun CFPropertyListCreateWithData(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CFPropertyListCreateWithData_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFPropertyListCreateWithStream typedef CFPropertyListRef = (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFReadStreamRef = (Declared(__CFReadStream))*,typedef CFIndex = Long,typedef CFOptionFlags = UNSIGNED = Long,(typedef CFPropertyListFormat = <error: enum CFPropertyListFormat>)*,(typedef CFErrorRef = (Declared(__CFError))*)*)
 */
private val CFPropertyListCreateWithStream_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFPropertyListCreateWithStream_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFPropertyListCreateWithStream").orElseThrow()
private val CFPropertyListCreateWithStream_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFPropertyListCreateWithStream_ADDR, CFPropertyListCreateWithStream_DESC)

fun CFPropertyListCreateWithStream(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: Long, arg4: MemorySegment, arg5: MemorySegment): MemorySegment {
    try {
        return CFPropertyListCreateWithStream_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFTypeSetCallBacks typedef const CFSetCallBacks = Declared(CFSetCallBacks)
 */
private val kCFTypeSetCallBacks_LAYOUT: MemoryLayout by lazy { CFSetCallBacks.layout }
private val kCFTypeSetCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFTypeSetCallBacks").orElseThrow() }
private val kCFTypeSetCallBacks_VH: VarHandle by lazy { kCFTypeSetCallBacks_LAYOUT.varHandle() }

var kCFTypeSetCallBacks: MemorySegment
    get() = kCFTypeSetCallBacks_VH.get(kCFTypeSetCallBacks_SEGMENT) as MemorySegment
    set(value) = kCFTypeSetCallBacks_VH.set(kCFTypeSetCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : kCFCopyStringSetCallBacks typedef const CFSetCallBacks = Declared(CFSetCallBacks)
 */
private val kCFCopyStringSetCallBacks_LAYOUT: MemoryLayout by lazy { CFSetCallBacks.layout }
private val kCFCopyStringSetCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFCopyStringSetCallBacks").orElseThrow() }
private val kCFCopyStringSetCallBacks_VH: VarHandle by lazy { kCFCopyStringSetCallBacks_LAYOUT.varHandle() }

var kCFCopyStringSetCallBacks: MemorySegment
    get() = kCFCopyStringSetCallBacks_VH.get(kCFCopyStringSetCallBacks_SEGMENT) as MemorySegment
    set(value) = kCFCopyStringSetCallBacks_VH.set(kCFCopyStringSetCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : CFSetGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFSetGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFSetGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetGetTypeID").orElseThrow()
private val CFSetGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetGetTypeID_ADDR, CFSetGetTypeID_DESC)

fun CFSetGetTypeID(): Long {
    try {
        return CFSetGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetCreate typedef CFSetRef = (Declared(__CFSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,((Void)*)*,typedef CFIndex = Long,(typedef CFSetCallBacks = Declared(CFSetCallBacks))*)
 */
private val CFSetCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFSetCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetCreate").orElseThrow()
private val CFSetCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetCreate_ADDR, CFSetCreate_DESC)

fun CFSetCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return CFSetCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetCreateCopy typedef CFSetRef = (Declared(__CFSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFSetRef = (Declared(__CFSet))*)
 */
private val CFSetCreateCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetCreateCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetCreateCopy").orElseThrow()
private val CFSetCreateCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetCreateCopy_ADDR, CFSetCreateCopy_DESC)

fun CFSetCreateCopy(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFSetCreateCopy_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetCreateMutable typedef CFMutableSetRef = (Declared(__CFSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,(typedef CFSetCallBacks = Declared(CFSetCallBacks))*)
 */
private val CFSetCreateMutable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFSetCreateMutable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetCreateMutable").orElseThrow()
private val CFSetCreateMutable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetCreateMutable_ADDR, CFSetCreateMutable_DESC)

fun CFSetCreateMutable(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFSetCreateMutable_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetCreateMutableCopy typedef CFMutableSetRef = (Declared(__CFSet))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFIndex = Long,typedef CFSetRef = (Declared(__CFSet))*)
 */
private val CFSetCreateMutableCopy_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFSetCreateMutableCopy_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetCreateMutableCopy").orElseThrow()
private val CFSetCreateMutableCopy_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetCreateMutableCopy_ADDR, CFSetCreateMutableCopy_DESC)

fun CFSetCreateMutableCopy(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return CFSetCreateMutableCopy_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetGetCount typedef CFIndex = Long(typedef CFSetRef = (Declared(__CFSet))*)
 */
private val CFSetGetCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFSetGetCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetGetCount").orElseThrow()
private val CFSetGetCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetGetCount_ADDR, CFSetGetCount_DESC)

fun CFSetGetCount(arg0: MemorySegment): Long {
    try {
        return CFSetGetCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetGetCountOfValue typedef CFIndex = Long(typedef CFSetRef = (Declared(__CFSet))*,(Void)*)
 */
private val CFSetGetCountOfValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetGetCountOfValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetGetCountOfValue").orElseThrow()
private val CFSetGetCountOfValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetGetCountOfValue_ADDR, CFSetGetCountOfValue_DESC)

fun CFSetGetCountOfValue(arg0: MemorySegment, arg1: MemorySegment): Long {
    try {
        return CFSetGetCountOfValue_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetContainsValue typedef Boolean = UNSIGNED = Char(typedef CFSetRef = (Declared(__CFSet))*,(Void)*)
 */
private val CFSetContainsValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetContainsValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetContainsValue").orElseThrow()
private val CFSetContainsValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetContainsValue_ADDR, CFSetContainsValue_DESC)

fun CFSetContainsValue(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFSetContainsValue_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetGetValue (Void)*(typedef CFSetRef = (Declared(__CFSet))*,(Void)*)
 */
private val CFSetGetValue_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetGetValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetGetValue").orElseThrow()
private val CFSetGetValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetGetValue_ADDR, CFSetGetValue_DESC)

fun CFSetGetValue(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFSetGetValue_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetGetValueIfPresent typedef Boolean = UNSIGNED = Char(typedef CFSetRef = (Declared(__CFSet))*,(Void)*,((Void)*)*)
 */
private val CFSetGetValueIfPresent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetGetValueIfPresent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetGetValueIfPresent").orElseThrow()
private val CFSetGetValueIfPresent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetGetValueIfPresent_ADDR, CFSetGetValueIfPresent_DESC)

fun CFSetGetValueIfPresent(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Byte {
    try {
        return CFSetGetValueIfPresent_HANDLE.invokeExact(arg0, arg1, arg2) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetGetValues Void(typedef CFSetRef = (Declared(__CFSet))*,((Void)*)*)
 */
private val CFSetGetValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetGetValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetGetValues").orElseThrow()
private val CFSetGetValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetGetValues_ADDR, CFSetGetValues_DESC)

fun CFSetGetValues(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFSetGetValues_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetApplyFunction Void(typedef CFSetRef = (Declared(__CFSet))*,typedef CFSetApplierFunction = (Void((Void)*,(Void)*))*,(Void)*)
 */
private val CFSetApplyFunction_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetApplyFunction_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetApplyFunction").orElseThrow()
private val CFSetApplyFunction_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetApplyFunction_ADDR, CFSetApplyFunction_DESC)

fun CFSetApplyFunction(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFSetApplyFunction_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetAddValue Void(typedef CFMutableSetRef = (Declared(__CFSet))*,(Void)*)
 */
private val CFSetAddValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetAddValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetAddValue").orElseThrow()
private val CFSetAddValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetAddValue_ADDR, CFSetAddValue_DESC)

fun CFSetAddValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFSetAddValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetReplaceValue Void(typedef CFMutableSetRef = (Declared(__CFSet))*,(Void)*)
 */
private val CFSetReplaceValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetReplaceValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetReplaceValue").orElseThrow()
private val CFSetReplaceValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetReplaceValue_ADDR, CFSetReplaceValue_DESC)

fun CFSetReplaceValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFSetReplaceValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetSetValue Void(typedef CFMutableSetRef = (Declared(__CFSet))*,(Void)*)
 */
private val CFSetSetValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetSetValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetSetValue").orElseThrow()
private val CFSetSetValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetSetValue_ADDR, CFSetSetValue_DESC)

fun CFSetSetValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFSetSetValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetRemoveValue Void(typedef CFMutableSetRef = (Declared(__CFSet))*,(Void)*)
 */
private val CFSetRemoveValue_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFSetRemoveValue_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetRemoveValue").orElseThrow()
private val CFSetRemoveValue_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetRemoveValue_ADDR, CFSetRemoveValue_DESC)

fun CFSetRemoveValue(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFSetRemoveValue_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFSetRemoveAllValues Void(typedef CFMutableSetRef = (Declared(__CFSet))*)
 */
private val CFSetRemoveAllValues_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFSetRemoveAllValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFSetRemoveAllValues").orElseThrow()
private val CFSetRemoveAllValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFSetRemoveAllValues_ADDR, CFSetRemoveAllValues_DESC)

fun CFSetRemoveAllValues(arg0: MemorySegment): Unit {
    try {
        CFSetRemoveAllValues_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CFTreeGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CFTreeGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeGetTypeID").orElseThrow()
private val CFTreeGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeGetTypeID_ADDR, CFTreeGetTypeID_DESC)

fun CFTreeGetTypeID(): Long {
    try {
        return CFTreeGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeCreate typedef CFTreeRef = (Declared(__CFTree))*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,(typedef CFTreeContext = Declared(CFTreeContext))*)
 */
private val CFTreeCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeCreate").orElseThrow()
private val CFTreeCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeCreate_ADDR, CFTreeCreate_DESC)

fun CFTreeCreate(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CFTreeCreate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeGetParent typedef CFTreeRef = (Declared(__CFTree))*(typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreeGetParent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeGetParent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeGetParent").orElseThrow()
private val CFTreeGetParent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeGetParent_ADDR, CFTreeGetParent_DESC)

fun CFTreeGetParent(arg0: MemorySegment): MemorySegment {
    try {
        return CFTreeGetParent_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeGetNextSibling typedef CFTreeRef = (Declared(__CFTree))*(typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreeGetNextSibling_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeGetNextSibling_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeGetNextSibling").orElseThrow()
private val CFTreeGetNextSibling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeGetNextSibling_ADDR, CFTreeGetNextSibling_DESC)

fun CFTreeGetNextSibling(arg0: MemorySegment): MemorySegment {
    try {
        return CFTreeGetNextSibling_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeGetFirstChild typedef CFTreeRef = (Declared(__CFTree))*(typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreeGetFirstChild_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeGetFirstChild_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeGetFirstChild").orElseThrow()
private val CFTreeGetFirstChild_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeGetFirstChild_ADDR, CFTreeGetFirstChild_DESC)

fun CFTreeGetFirstChild(arg0: MemorySegment): MemorySegment {
    try {
        return CFTreeGetFirstChild_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeGetContext Void(typedef CFTreeRef = (Declared(__CFTree))*,(typedef CFTreeContext = Declared(CFTreeContext))*)
 */
private val CFTreeGetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeGetContext").orElseThrow()
private val CFTreeGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeGetContext_ADDR, CFTreeGetContext_DESC)

fun CFTreeGetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFTreeGetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeGetChildCount typedef CFIndex = Long(typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreeGetChildCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CFTreeGetChildCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeGetChildCount").orElseThrow()
private val CFTreeGetChildCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeGetChildCount_ADDR, CFTreeGetChildCount_DESC)

fun CFTreeGetChildCount(arg0: MemorySegment): Long {
    try {
        return CFTreeGetChildCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeGetChildAtIndex typedef CFTreeRef = (Declared(__CFTree))*(typedef CFTreeRef = (Declared(__CFTree))*,typedef CFIndex = Long)
 */
private val CFTreeGetChildAtIndex_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG)
private val CFTreeGetChildAtIndex_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeGetChildAtIndex").orElseThrow()
private val CFTreeGetChildAtIndex_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeGetChildAtIndex_ADDR, CFTreeGetChildAtIndex_DESC)

fun CFTreeGetChildAtIndex(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return CFTreeGetChildAtIndex_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeGetChildren Void(typedef CFTreeRef = (Declared(__CFTree))*,(typedef CFTreeRef = (Declared(__CFTree))*)*)
 */
private val CFTreeGetChildren_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeGetChildren_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeGetChildren").orElseThrow()
private val CFTreeGetChildren_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeGetChildren_ADDR, CFTreeGetChildren_DESC)

fun CFTreeGetChildren(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFTreeGetChildren_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeApplyFunctionToChildren Void(typedef CFTreeRef = (Declared(__CFTree))*,typedef CFTreeApplierFunction = (Void((Void)*,(Void)*))*,(Void)*)
 */
private val CFTreeApplyFunctionToChildren_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeApplyFunctionToChildren_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeApplyFunctionToChildren").orElseThrow()
private val CFTreeApplyFunctionToChildren_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeApplyFunctionToChildren_ADDR, CFTreeApplyFunctionToChildren_DESC)

fun CFTreeApplyFunctionToChildren(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFTreeApplyFunctionToChildren_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeFindRoot typedef CFTreeRef = (Declared(__CFTree))*(typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreeFindRoot_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeFindRoot_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeFindRoot").orElseThrow()
private val CFTreeFindRoot_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeFindRoot_ADDR, CFTreeFindRoot_DESC)

fun CFTreeFindRoot(arg0: MemorySegment): MemorySegment {
    try {
        return CFTreeFindRoot_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeSetContext Void(typedef CFTreeRef = (Declared(__CFTree))*,(typedef CFTreeContext = Declared(CFTreeContext))*)
 */
private val CFTreeSetContext_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeSetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeSetContext").orElseThrow()
private val CFTreeSetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeSetContext_ADDR, CFTreeSetContext_DESC)

fun CFTreeSetContext(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFTreeSetContext_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreePrependChild Void(typedef CFTreeRef = (Declared(__CFTree))*,typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreePrependChild_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreePrependChild_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreePrependChild").orElseThrow()
private val CFTreePrependChild_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreePrependChild_ADDR, CFTreePrependChild_DESC)

fun CFTreePrependChild(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFTreePrependChild_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeAppendChild Void(typedef CFTreeRef = (Declared(__CFTree))*,typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreeAppendChild_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeAppendChild_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeAppendChild").orElseThrow()
private val CFTreeAppendChild_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeAppendChild_ADDR, CFTreeAppendChild_DESC)

fun CFTreeAppendChild(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFTreeAppendChild_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeInsertSibling Void(typedef CFTreeRef = (Declared(__CFTree))*,typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreeInsertSibling_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeInsertSibling_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeInsertSibling").orElseThrow()
private val CFTreeInsertSibling_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeInsertSibling_ADDR, CFTreeInsertSibling_DESC)

fun CFTreeInsertSibling(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CFTreeInsertSibling_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeRemove Void(typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreeRemove_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFTreeRemove_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeRemove").orElseThrow()
private val CFTreeRemove_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeRemove_ADDR, CFTreeRemove_DESC)

fun CFTreeRemove(arg0: MemorySegment): Unit {
    try {
        CFTreeRemove_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeRemoveAllChildren Void(typedef CFTreeRef = (Declared(__CFTree))*)
 */
private val CFTreeRemoveAllChildren_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CFTreeRemoveAllChildren_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeRemoveAllChildren").orElseThrow()
private val CFTreeRemoveAllChildren_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeRemoveAllChildren_ADDR, CFTreeRemoveAllChildren_DESC)

fun CFTreeRemoveAllChildren(arg0: MemorySegment): Unit {
    try {
        CFTreeRemoveAllChildren_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFTreeSortChildren Void(typedef CFTreeRef = (Declared(__CFTree))*,typedef CFComparatorFunction = (<error: enum CFComparisonResult>((Void)*,(Void)*,(Void)*))*,(Void)*)
 */
private val CFTreeSortChildren_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFTreeSortChildren_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFTreeSortChildren").orElseThrow()
private val CFTreeSortChildren_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFTreeSortChildren_ADDR, CFTreeSortChildren_DESC)

fun CFTreeSortChildren(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CFTreeSortChildren_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreateDataAndPropertiesFromResource typedef Boolean = UNSIGNED = Char(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,(typedef CFDataRef = (Declared(__CFData))*)*,(typedef CFDictionaryRef = (Declared(__CFDictionary))*)*,typedef CFArrayRef = (Declared(__CFArray))*,(typedef SInt32 = Int)*)
 */
private val CFURLCreateDataAndPropertiesFromResource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreateDataAndPropertiesFromResource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreateDataAndPropertiesFromResource").orElseThrow()
private val CFURLCreateDataAndPropertiesFromResource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreateDataAndPropertiesFromResource_ADDR, CFURLCreateDataAndPropertiesFromResource_DESC)

fun CFURLCreateDataAndPropertiesFromResource(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment): Byte {
    try {
        return CFURLCreateDataAndPropertiesFromResource_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLWriteDataAndPropertiesToResource typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*,typedef CFDataRef = (Declared(__CFData))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*,(typedef SInt32 = Int)*)
 */
private val CFURLWriteDataAndPropertiesToResource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLWriteDataAndPropertiesToResource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLWriteDataAndPropertiesToResource").orElseThrow()
private val CFURLWriteDataAndPropertiesToResource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLWriteDataAndPropertiesToResource_ADDR, CFURLWriteDataAndPropertiesToResource_DESC)

fun CFURLWriteDataAndPropertiesToResource(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Byte {
    try {
        return CFURLWriteDataAndPropertiesToResource_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLDestroyResource typedef Boolean = UNSIGNED = Char(typedef CFURLRef = (Declared(__CFURL))*,(typedef SInt32 = Int)*)
 */
private val CFURLDestroyResource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLDestroyResource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLDestroyResource").orElseThrow()
private val CFURLDestroyResource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLDestroyResource_ADDR, CFURLDestroyResource_DESC)

fun CFURLDestroyResource(arg0: MemorySegment, arg1: MemorySegment): Byte {
    try {
        return CFURLDestroyResource_HANDLE.invokeExact(arg0, arg1) as Byte
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CFURLCreatePropertyFromResource typedef CFTypeRef = (Void)*(typedef CFAllocatorRef = (Declared(__CFAllocator))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CFStringRef = (Declared(__CFString))*,(typedef SInt32 = Int)*)
 */
private val CFURLCreatePropertyFromResource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CFURLCreatePropertyFromResource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CFURLCreatePropertyFromResource").orElseThrow()
private val CFURLCreatePropertyFromResource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CFURLCreatePropertyFromResource_ADDR, CFURLCreatePropertyFromResource_DESC)

fun CFURLCreatePropertyFromResource(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): MemorySegment {
    try {
        return CFURLCreatePropertyFromResource_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCFURLFileExists typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileExists_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileExists_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileExists").orElseThrow() }
private val kCFURLFileExists_VH: VarHandle by lazy { kCFURLFileExists_LAYOUT.varHandle() }

var kCFURLFileExists: MemorySegment
    get() = kCFURLFileExists_VH.get(kCFURLFileExists_SEGMENT) as MemorySegment
    set(value) = kCFURLFileExists_VH.set(kCFURLFileExists_SEGMENT, value)

/**
 * {@snippet lang=c : kCFURLFileDirectoryContents typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCFURLFileDirectoryContents_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCFURLFileDirectoryContents_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCFURLFileDirectoryContents").orElseThrow() }
private val kCFURLFileDirectoryContents_VH: VarHandle by lazy { kCFURLFileDirectoryContents_LAYOUT.varHandle() }

var kCFURLFileDirectoryContents: MemorySegment
    get() = kCFURLFileDirectoryContents_VH.get(kCFURLFileDirectoryContents_SEGMENT) as MemorySegment
    set(value) = kCFURLFileDirectoryContents_VH.set(kCFURLFileDirectoryContents_SEGMENT, value)

