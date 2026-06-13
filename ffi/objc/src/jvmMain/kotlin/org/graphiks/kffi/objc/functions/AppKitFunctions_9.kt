package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : NSAccessibilityPathAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityPathAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPathAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPathAttribute").orElseThrow() }
private val NSAccessibilityPathAttribute_VH: VarHandle by lazy { NSAccessibilityPathAttribute_LAYOUT.varHandle() }

var NSAccessibilityPathAttribute: MemorySegment
    get() = NSAccessibilityPathAttribute_VH.get(NSAccessibilityPathAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPathAttribute_VH.set(NSAccessibilityPathAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextInputMarkedRangeAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityTextInputMarkedRangeAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextInputMarkedRangeAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextInputMarkedRangeAttribute").orElseThrow() }
private val NSAccessibilityTextInputMarkedRangeAttribute_VH: VarHandle by lazy { NSAccessibilityTextInputMarkedRangeAttribute_LAYOUT.varHandle() }

var NSAccessibilityTextInputMarkedRangeAttribute: MemorySegment
    get() = NSAccessibilityTextInputMarkedRangeAttribute_VH.get(NSAccessibilityTextInputMarkedRangeAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextInputMarkedRangeAttribute_VH.set(NSAccessibilityTextInputMarkedRangeAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityBlockQuoteLevelAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityBlockQuoteLevelAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityBlockQuoteLevelAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityBlockQuoteLevelAttribute").orElseThrow() }
private val NSAccessibilityBlockQuoteLevelAttribute_VH: VarHandle by lazy { NSAccessibilityBlockQuoteLevelAttribute_LAYOUT.varHandle() }

var NSAccessibilityBlockQuoteLevelAttribute: MemorySegment
    get() = NSAccessibilityBlockQuoteLevelAttribute_VH.get(NSAccessibilityBlockQuoteLevelAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityBlockQuoteLevelAttribute_VH.set(NSAccessibilityBlockQuoteLevelAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingLevelAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityHeadingLevelAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingLevelAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingLevelAttribute").orElseThrow() }
private val NSAccessibilityHeadingLevelAttribute_VH: VarHandle by lazy { NSAccessibilityHeadingLevelAttribute_LAYOUT.varHandle() }

var NSAccessibilityHeadingLevelAttribute: MemorySegment
    get() = NSAccessibilityHeadingLevelAttribute_VH.get(NSAccessibilityHeadingLevelAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingLevelAttribute_VH.set(NSAccessibilityHeadingLevelAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLanguageAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityLanguageAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLanguageAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLanguageAttribute").orElseThrow() }
private val NSAccessibilityLanguageAttribute_VH: VarHandle by lazy { NSAccessibilityLanguageAttribute_LAYOUT.varHandle() }

var NSAccessibilityLanguageAttribute: MemorySegment
    get() = NSAccessibilityLanguageAttribute_VH.get(NSAccessibilityLanguageAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLanguageAttribute_VH.set(NSAccessibilityLanguageAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVisitedAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityVisitedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVisitedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVisitedAttribute").orElseThrow() }
private val NSAccessibilityVisitedAttribute_VH: VarHandle by lazy { NSAccessibilityVisitedAttribute_LAYOUT.varHandle() }

var NSAccessibilityVisitedAttribute: MemorySegment
    get() = NSAccessibilityVisitedAttribute_VH.get(NSAccessibilityVisitedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVisitedAttribute_VH.set(NSAccessibilityVisitedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTitleUIElementAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityTitleUIElementAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTitleUIElementAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTitleUIElementAttribute").orElseThrow() }
private val NSAccessibilityTitleUIElementAttribute_VH: VarHandle by lazy { NSAccessibilityTitleUIElementAttribute_LAYOUT.varHandle() }

var NSAccessibilityTitleUIElementAttribute: MemorySegment
    get() = NSAccessibilityTitleUIElementAttribute_VH.get(NSAccessibilityTitleUIElementAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTitleUIElementAttribute_VH.set(NSAccessibilityTitleUIElementAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityServesAsTitleForUIElementsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityServesAsTitleForUIElementsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityServesAsTitleForUIElementsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityServesAsTitleForUIElementsAttribute").orElseThrow() }
private val NSAccessibilityServesAsTitleForUIElementsAttribute_VH: VarHandle by lazy { NSAccessibilityServesAsTitleForUIElementsAttribute_LAYOUT.varHandle() }

var NSAccessibilityServesAsTitleForUIElementsAttribute: MemorySegment
    get() = NSAccessibilityServesAsTitleForUIElementsAttribute_VH.get(NSAccessibilityServesAsTitleForUIElementsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityServesAsTitleForUIElementsAttribute_VH.set(NSAccessibilityServesAsTitleForUIElementsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLinkedUIElementsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityLinkedUIElementsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLinkedUIElementsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLinkedUIElementsAttribute").orElseThrow() }
private val NSAccessibilityLinkedUIElementsAttribute_VH: VarHandle by lazy { NSAccessibilityLinkedUIElementsAttribute_LAYOUT.varHandle() }

var NSAccessibilityLinkedUIElementsAttribute: MemorySegment
    get() = NSAccessibilityLinkedUIElementsAttribute_VH.get(NSAccessibilityLinkedUIElementsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLinkedUIElementsAttribute_VH.set(NSAccessibilityLinkedUIElementsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedTextAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySelectedTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedTextAttribute").orElseThrow() }
private val NSAccessibilitySelectedTextAttribute_VH: VarHandle by lazy { NSAccessibilitySelectedTextAttribute_LAYOUT.varHandle() }

var NSAccessibilitySelectedTextAttribute: MemorySegment
    get() = NSAccessibilitySelectedTextAttribute_VH.get(NSAccessibilitySelectedTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedTextAttribute_VH.set(NSAccessibilitySelectedTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedTextRangeAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySelectedTextRangeAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedTextRangeAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedTextRangeAttribute").orElseThrow() }
private val NSAccessibilitySelectedTextRangeAttribute_VH: VarHandle by lazy { NSAccessibilitySelectedTextRangeAttribute_LAYOUT.varHandle() }

var NSAccessibilitySelectedTextRangeAttribute: MemorySegment
    get() = NSAccessibilitySelectedTextRangeAttribute_VH.get(NSAccessibilitySelectedTextRangeAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedTextRangeAttribute_VH.set(NSAccessibilitySelectedTextRangeAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityNumberOfCharactersAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityNumberOfCharactersAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityNumberOfCharactersAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityNumberOfCharactersAttribute").orElseThrow() }
private val NSAccessibilityNumberOfCharactersAttribute_VH: VarHandle by lazy { NSAccessibilityNumberOfCharactersAttribute_LAYOUT.varHandle() }

var NSAccessibilityNumberOfCharactersAttribute: MemorySegment
    get() = NSAccessibilityNumberOfCharactersAttribute_VH.get(NSAccessibilityNumberOfCharactersAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityNumberOfCharactersAttribute_VH.set(NSAccessibilityNumberOfCharactersAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVisibleCharacterRangeAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityVisibleCharacterRangeAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVisibleCharacterRangeAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVisibleCharacterRangeAttribute").orElseThrow() }
private val NSAccessibilityVisibleCharacterRangeAttribute_VH: VarHandle by lazy { NSAccessibilityVisibleCharacterRangeAttribute_LAYOUT.varHandle() }

var NSAccessibilityVisibleCharacterRangeAttribute: MemorySegment
    get() = NSAccessibilityVisibleCharacterRangeAttribute_VH.get(NSAccessibilityVisibleCharacterRangeAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVisibleCharacterRangeAttribute_VH.set(NSAccessibilityVisibleCharacterRangeAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySharedTextUIElementsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySharedTextUIElementsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySharedTextUIElementsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySharedTextUIElementsAttribute").orElseThrow() }
private val NSAccessibilitySharedTextUIElementsAttribute_VH: VarHandle by lazy { NSAccessibilitySharedTextUIElementsAttribute_LAYOUT.varHandle() }

var NSAccessibilitySharedTextUIElementsAttribute: MemorySegment
    get() = NSAccessibilitySharedTextUIElementsAttribute_VH.get(NSAccessibilitySharedTextUIElementsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySharedTextUIElementsAttribute_VH.set(NSAccessibilitySharedTextUIElementsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySharedCharacterRangeAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySharedCharacterRangeAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySharedCharacterRangeAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySharedCharacterRangeAttribute").orElseThrow() }
private val NSAccessibilitySharedCharacterRangeAttribute_VH: VarHandle by lazy { NSAccessibilitySharedCharacterRangeAttribute_LAYOUT.varHandle() }

var NSAccessibilitySharedCharacterRangeAttribute: MemorySegment
    get() = NSAccessibilitySharedCharacterRangeAttribute_VH.get(NSAccessibilitySharedCharacterRangeAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySharedCharacterRangeAttribute_VH.set(NSAccessibilitySharedCharacterRangeAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityInsertionPointLineNumberAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityInsertionPointLineNumberAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityInsertionPointLineNumberAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityInsertionPointLineNumberAttribute").orElseThrow() }
private val NSAccessibilityInsertionPointLineNumberAttribute_VH: VarHandle by lazy { NSAccessibilityInsertionPointLineNumberAttribute_LAYOUT.varHandle() }

var NSAccessibilityInsertionPointLineNumberAttribute: MemorySegment
    get() = NSAccessibilityInsertionPointLineNumberAttribute_VH.get(NSAccessibilityInsertionPointLineNumberAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityInsertionPointLineNumberAttribute_VH.set(NSAccessibilityInsertionPointLineNumberAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedTextRangesAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySelectedTextRangesAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedTextRangesAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedTextRangesAttribute").orElseThrow() }
private val NSAccessibilitySelectedTextRangesAttribute_VH: VarHandle by lazy { NSAccessibilitySelectedTextRangesAttribute_LAYOUT.varHandle() }

var NSAccessibilitySelectedTextRangesAttribute: MemorySegment
    get() = NSAccessibilitySelectedTextRangesAttribute_VH.get(NSAccessibilitySelectedTextRangesAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedTextRangesAttribute_VH.set(NSAccessibilitySelectedTextRangesAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLineForIndexParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityLineForIndexParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLineForIndexParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLineForIndexParameterizedAttribute").orElseThrow() }
private val NSAccessibilityLineForIndexParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityLineForIndexParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityLineForIndexParameterizedAttribute: MemorySegment
    get() = NSAccessibilityLineForIndexParameterizedAttribute_VH.get(NSAccessibilityLineForIndexParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLineForIndexParameterizedAttribute_VH.set(NSAccessibilityLineForIndexParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRangeForLineParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityRangeForLineParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRangeForLineParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRangeForLineParameterizedAttribute").orElseThrow() }
private val NSAccessibilityRangeForLineParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityRangeForLineParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityRangeForLineParameterizedAttribute: MemorySegment
    get() = NSAccessibilityRangeForLineParameterizedAttribute_VH.get(NSAccessibilityRangeForLineParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRangeForLineParameterizedAttribute_VH.set(NSAccessibilityRangeForLineParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityStringForRangeParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityStringForRangeParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityStringForRangeParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityStringForRangeParameterizedAttribute").orElseThrow() }
private val NSAccessibilityStringForRangeParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityStringForRangeParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityStringForRangeParameterizedAttribute: MemorySegment
    get() = NSAccessibilityStringForRangeParameterizedAttribute_VH.get(NSAccessibilityStringForRangeParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityStringForRangeParameterizedAttribute_VH.set(NSAccessibilityStringForRangeParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRangeForPositionParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityRangeForPositionParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRangeForPositionParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRangeForPositionParameterizedAttribute").orElseThrow() }
private val NSAccessibilityRangeForPositionParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityRangeForPositionParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityRangeForPositionParameterizedAttribute: MemorySegment
    get() = NSAccessibilityRangeForPositionParameterizedAttribute_VH.get(NSAccessibilityRangeForPositionParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRangeForPositionParameterizedAttribute_VH.set(NSAccessibilityRangeForPositionParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRangeForIndexParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityRangeForIndexParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRangeForIndexParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRangeForIndexParameterizedAttribute").orElseThrow() }
private val NSAccessibilityRangeForIndexParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityRangeForIndexParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityRangeForIndexParameterizedAttribute: MemorySegment
    get() = NSAccessibilityRangeForIndexParameterizedAttribute_VH.get(NSAccessibilityRangeForIndexParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRangeForIndexParameterizedAttribute_VH.set(NSAccessibilityRangeForIndexParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityBoundsForRangeParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityBoundsForRangeParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityBoundsForRangeParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityBoundsForRangeParameterizedAttribute").orElseThrow() }
private val NSAccessibilityBoundsForRangeParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityBoundsForRangeParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityBoundsForRangeParameterizedAttribute: MemorySegment
    get() = NSAccessibilityBoundsForRangeParameterizedAttribute_VH.get(NSAccessibilityBoundsForRangeParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityBoundsForRangeParameterizedAttribute_VH.set(NSAccessibilityBoundsForRangeParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRTFForRangeParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityRTFForRangeParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRTFForRangeParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRTFForRangeParameterizedAttribute").orElseThrow() }
private val NSAccessibilityRTFForRangeParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityRTFForRangeParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityRTFForRangeParameterizedAttribute: MemorySegment
    get() = NSAccessibilityRTFForRangeParameterizedAttribute_VH.get(NSAccessibilityRTFForRangeParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRTFForRangeParameterizedAttribute_VH.set(NSAccessibilityRTFForRangeParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityStyleRangeForIndexParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityStyleRangeForIndexParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityStyleRangeForIndexParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityStyleRangeForIndexParameterizedAttribute").orElseThrow() }
private val NSAccessibilityStyleRangeForIndexParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityStyleRangeForIndexParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityStyleRangeForIndexParameterizedAttribute: MemorySegment
    get() = NSAccessibilityStyleRangeForIndexParameterizedAttribute_VH.get(NSAccessibilityStyleRangeForIndexParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityStyleRangeForIndexParameterizedAttribute_VH.set(NSAccessibilityStyleRangeForIndexParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAttributedStringForRangeParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityAttributedStringForRangeParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAttributedStringForRangeParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAttributedStringForRangeParameterizedAttribute").orElseThrow() }
private val NSAccessibilityAttributedStringForRangeParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityAttributedStringForRangeParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityAttributedStringForRangeParameterizedAttribute: MemorySegment
    get() = NSAccessibilityAttributedStringForRangeParameterizedAttribute_VH.get(NSAccessibilityAttributedStringForRangeParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAttributedStringForRangeParameterizedAttribute_VH.set(NSAccessibilityAttributedStringForRangeParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFontTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityFontTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFontTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFontTextAttribute").orElseThrow() }
private val NSAccessibilityFontTextAttribute_VH: VarHandle by lazy { NSAccessibilityFontTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityFontTextAttribute: MemorySegment
    get() = NSAccessibilityFontTextAttribute_VH.get(NSAccessibilityFontTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFontTextAttribute_VH.set(NSAccessibilityFontTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityForegroundColorTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityForegroundColorTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityForegroundColorTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityForegroundColorTextAttribute").orElseThrow() }
private val NSAccessibilityForegroundColorTextAttribute_VH: VarHandle by lazy { NSAccessibilityForegroundColorTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityForegroundColorTextAttribute: MemorySegment
    get() = NSAccessibilityForegroundColorTextAttribute_VH.get(NSAccessibilityForegroundColorTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityForegroundColorTextAttribute_VH.set(NSAccessibilityForegroundColorTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityBackgroundColorTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityBackgroundColorTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityBackgroundColorTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityBackgroundColorTextAttribute").orElseThrow() }
private val NSAccessibilityBackgroundColorTextAttribute_VH: VarHandle by lazy { NSAccessibilityBackgroundColorTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityBackgroundColorTextAttribute: MemorySegment
    get() = NSAccessibilityBackgroundColorTextAttribute_VH.get(NSAccessibilityBackgroundColorTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityBackgroundColorTextAttribute_VH.set(NSAccessibilityBackgroundColorTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnderlineColorTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityUnderlineColorTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnderlineColorTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnderlineColorTextAttribute").orElseThrow() }
private val NSAccessibilityUnderlineColorTextAttribute_VH: VarHandle by lazy { NSAccessibilityUnderlineColorTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityUnderlineColorTextAttribute: MemorySegment
    get() = NSAccessibilityUnderlineColorTextAttribute_VH.get(NSAccessibilityUnderlineColorTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnderlineColorTextAttribute_VH.set(NSAccessibilityUnderlineColorTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityStrikethroughColorTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityStrikethroughColorTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityStrikethroughColorTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityStrikethroughColorTextAttribute").orElseThrow() }
private val NSAccessibilityStrikethroughColorTextAttribute_VH: VarHandle by lazy { NSAccessibilityStrikethroughColorTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityStrikethroughColorTextAttribute: MemorySegment
    get() = NSAccessibilityStrikethroughColorTextAttribute_VH.get(NSAccessibilityStrikethroughColorTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityStrikethroughColorTextAttribute_VH.set(NSAccessibilityStrikethroughColorTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnderlineTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityUnderlineTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnderlineTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnderlineTextAttribute").orElseThrow() }
private val NSAccessibilityUnderlineTextAttribute_VH: VarHandle by lazy { NSAccessibilityUnderlineTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityUnderlineTextAttribute: MemorySegment
    get() = NSAccessibilityUnderlineTextAttribute_VH.get(NSAccessibilityUnderlineTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnderlineTextAttribute_VH.set(NSAccessibilityUnderlineTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySuperscriptTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilitySuperscriptTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySuperscriptTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySuperscriptTextAttribute").orElseThrow() }
private val NSAccessibilitySuperscriptTextAttribute_VH: VarHandle by lazy { NSAccessibilitySuperscriptTextAttribute_LAYOUT.varHandle() }

var NSAccessibilitySuperscriptTextAttribute: MemorySegment
    get() = NSAccessibilitySuperscriptTextAttribute_VH.get(NSAccessibilitySuperscriptTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySuperscriptTextAttribute_VH.set(NSAccessibilitySuperscriptTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityStrikethroughTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityStrikethroughTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityStrikethroughTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityStrikethroughTextAttribute").orElseThrow() }
private val NSAccessibilityStrikethroughTextAttribute_VH: VarHandle by lazy { NSAccessibilityStrikethroughTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityStrikethroughTextAttribute: MemorySegment
    get() = NSAccessibilityStrikethroughTextAttribute_VH.get(NSAccessibilityStrikethroughTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityStrikethroughTextAttribute_VH.set(NSAccessibilityStrikethroughTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityShadowTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityShadowTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityShadowTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityShadowTextAttribute").orElseThrow() }
private val NSAccessibilityShadowTextAttribute_VH: VarHandle by lazy { NSAccessibilityShadowTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityShadowTextAttribute: MemorySegment
    get() = NSAccessibilityShadowTextAttribute_VH.get(NSAccessibilityShadowTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityShadowTextAttribute_VH.set(NSAccessibilityShadowTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAttachmentTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityAttachmentTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAttachmentTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAttachmentTextAttribute").orElseThrow() }
private val NSAccessibilityAttachmentTextAttribute_VH: VarHandle by lazy { NSAccessibilityAttachmentTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityAttachmentTextAttribute: MemorySegment
    get() = NSAccessibilityAttachmentTextAttribute_VH.get(NSAccessibilityAttachmentTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAttachmentTextAttribute_VH.set(NSAccessibilityAttachmentTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLinkTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityLinkTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLinkTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLinkTextAttribute").orElseThrow() }
private val NSAccessibilityLinkTextAttribute_VH: VarHandle by lazy { NSAccessibilityLinkTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityLinkTextAttribute: MemorySegment
    get() = NSAccessibilityLinkTextAttribute_VH.get(NSAccessibilityLinkTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLinkTextAttribute_VH.set(NSAccessibilityLinkTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAutocorrectedTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityAutocorrectedTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAutocorrectedTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAutocorrectedTextAttribute").orElseThrow() }
private val NSAccessibilityAutocorrectedTextAttribute_VH: VarHandle by lazy { NSAccessibilityAutocorrectedTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityAutocorrectedTextAttribute: MemorySegment
    get() = NSAccessibilityAutocorrectedTextAttribute_VH.get(NSAccessibilityAutocorrectedTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAutocorrectedTextAttribute_VH.set(NSAccessibilityAutocorrectedTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextAlignmentAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityTextAlignmentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextAlignmentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextAlignmentAttribute").orElseThrow() }
private val NSAccessibilityTextAlignmentAttribute_VH: VarHandle by lazy { NSAccessibilityTextAlignmentAttribute_LAYOUT.varHandle() }

var NSAccessibilityTextAlignmentAttribute: MemorySegment
    get() = NSAccessibilityTextAlignmentAttribute_VH.get(NSAccessibilityTextAlignmentAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextAlignmentAttribute_VH.set(NSAccessibilityTextAlignmentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFontBoldAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityFontBoldAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFontBoldAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFontBoldAttribute").orElseThrow() }
private val NSAccessibilityFontBoldAttribute_VH: VarHandle by lazy { NSAccessibilityFontBoldAttribute_LAYOUT.varHandle() }

var NSAccessibilityFontBoldAttribute: MemorySegment
    get() = NSAccessibilityFontBoldAttribute_VH.get(NSAccessibilityFontBoldAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFontBoldAttribute_VH.set(NSAccessibilityFontBoldAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFontItalicAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityFontItalicAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFontItalicAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFontItalicAttribute").orElseThrow() }
private val NSAccessibilityFontItalicAttribute_VH: VarHandle by lazy { NSAccessibilityFontItalicAttribute_LAYOUT.varHandle() }

var NSAccessibilityFontItalicAttribute: MemorySegment
    get() = NSAccessibilityFontItalicAttribute_VH.get(NSAccessibilityFontItalicAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFontItalicAttribute_VH.set(NSAccessibilityFontItalicAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityChildrenInNavigationOrderAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityChildrenInNavigationOrderAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityChildrenInNavigationOrderAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityChildrenInNavigationOrderAttribute").orElseThrow() }
private val NSAccessibilityChildrenInNavigationOrderAttribute_VH: VarHandle by lazy { NSAccessibilityChildrenInNavigationOrderAttribute_LAYOUT.varHandle() }

var NSAccessibilityChildrenInNavigationOrderAttribute: MemorySegment
    get() = NSAccessibilityChildrenInNavigationOrderAttribute_VH.get(NSAccessibilityChildrenInNavigationOrderAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityChildrenInNavigationOrderAttribute_VH.set(NSAccessibilityChildrenInNavigationOrderAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityIndexForChildUIElementAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityIndexForChildUIElementAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityIndexForChildUIElementAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityIndexForChildUIElementAttribute").orElseThrow() }
private val NSAccessibilityIndexForChildUIElementAttribute_VH: VarHandle by lazy { NSAccessibilityIndexForChildUIElementAttribute_LAYOUT.varHandle() }

var NSAccessibilityIndexForChildUIElementAttribute: MemorySegment
    get() = NSAccessibilityIndexForChildUIElementAttribute_VH.get(NSAccessibilityIndexForChildUIElementAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityIndexForChildUIElementAttribute_VH.set(NSAccessibilityIndexForChildUIElementAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute").orElseThrow() }
private val NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute_VH: VarHandle by lazy { NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute_LAYOUT.varHandle() }

var NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute: MemorySegment
    get() = NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute_VH.get(NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute_VH.set(NSAccessibilityIndexForChildUIElementInNavigationOrderAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityListItemPrefixTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityListItemPrefixTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityListItemPrefixTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityListItemPrefixTextAttribute").orElseThrow() }
private val NSAccessibilityListItemPrefixTextAttribute_VH: VarHandle by lazy { NSAccessibilityListItemPrefixTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityListItemPrefixTextAttribute: MemorySegment
    get() = NSAccessibilityListItemPrefixTextAttribute_VH.get(NSAccessibilityListItemPrefixTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityListItemPrefixTextAttribute_VH.set(NSAccessibilityListItemPrefixTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityListItemIndexTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityListItemIndexTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityListItemIndexTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityListItemIndexTextAttribute").orElseThrow() }
private val NSAccessibilityListItemIndexTextAttribute_VH: VarHandle by lazy { NSAccessibilityListItemIndexTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityListItemIndexTextAttribute: MemorySegment
    get() = NSAccessibilityListItemIndexTextAttribute_VH.get(NSAccessibilityListItemIndexTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityListItemIndexTextAttribute_VH.set(NSAccessibilityListItemIndexTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityListItemLevelTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityListItemLevelTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityListItemLevelTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityListItemLevelTextAttribute").orElseThrow() }
private val NSAccessibilityListItemLevelTextAttribute_VH: VarHandle by lazy { NSAccessibilityListItemLevelTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityListItemLevelTextAttribute: MemorySegment
    get() = NSAccessibilityListItemLevelTextAttribute_VH.get(NSAccessibilityListItemLevelTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityListItemLevelTextAttribute_VH.set(NSAccessibilityListItemLevelTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMisspelledTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityMisspelledTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMisspelledTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMisspelledTextAttribute").orElseThrow() }
private val NSAccessibilityMisspelledTextAttribute_VH: VarHandle by lazy { NSAccessibilityMisspelledTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityMisspelledTextAttribute: MemorySegment
    get() = NSAccessibilityMisspelledTextAttribute_VH.get(NSAccessibilityMisspelledTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMisspelledTextAttribute_VH.set(NSAccessibilityMisspelledTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMarkedMisspelledTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityMarkedMisspelledTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMarkedMisspelledTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMarkedMisspelledTextAttribute").orElseThrow() }
private val NSAccessibilityMarkedMisspelledTextAttribute_VH: VarHandle by lazy { NSAccessibilityMarkedMisspelledTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityMarkedMisspelledTextAttribute: MemorySegment
    get() = NSAccessibilityMarkedMisspelledTextAttribute_VH.get(NSAccessibilityMarkedMisspelledTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMarkedMisspelledTextAttribute_VH.set(NSAccessibilityMarkedMisspelledTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLanguageTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityLanguageTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLanguageTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLanguageTextAttribute").orElseThrow() }
private val NSAccessibilityLanguageTextAttribute_VH: VarHandle by lazy { NSAccessibilityLanguageTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityLanguageTextAttribute: MemorySegment
    get() = NSAccessibilityLanguageTextAttribute_VH.get(NSAccessibilityLanguageTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLanguageTextAttribute_VH.set(NSAccessibilityLanguageTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCustomTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityCustomTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCustomTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCustomTextAttribute").orElseThrow() }
private val NSAccessibilityCustomTextAttribute_VH: VarHandle by lazy { NSAccessibilityCustomTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityCustomTextAttribute: MemorySegment
    get() = NSAccessibilityCustomTextAttribute_VH.get(NSAccessibilityCustomTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCustomTextAttribute_VH.set(NSAccessibilityCustomTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAnnotationTextAttribute typedef const NSAttributedStringKey = (Void)*
 */
private val NSAccessibilityAnnotationTextAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAnnotationTextAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAnnotationTextAttribute").orElseThrow() }
private val NSAccessibilityAnnotationTextAttribute_VH: VarHandle by lazy { NSAccessibilityAnnotationTextAttribute_LAYOUT.varHandle() }

var NSAccessibilityAnnotationTextAttribute: MemorySegment
    get() = NSAccessibilityAnnotationTextAttribute_VH.get(NSAccessibilityAnnotationTextAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAnnotationTextAttribute_VH.set(NSAccessibilityAnnotationTextAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextCompletionAttribute (Void)*
 */
private val NSAccessibilityTextCompletionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextCompletionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextCompletionAttribute").orElseThrow() }
private val NSAccessibilityTextCompletionAttribute_VH: VarHandle by lazy { NSAccessibilityTextCompletionAttribute_LAYOUT.varHandle() }

var NSAccessibilityTextCompletionAttribute: MemorySegment
    get() = NSAccessibilityTextCompletionAttribute_VH.get(NSAccessibilityTextCompletionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextCompletionAttribute_VH.set(NSAccessibilityTextCompletionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAnnotationLabel typedef const NSAccessibilityAnnotationAttributeKey = (Void)*
 */
private val NSAccessibilityAnnotationLabel_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAnnotationLabel_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAnnotationLabel").orElseThrow() }
private val NSAccessibilityAnnotationLabel_VH: VarHandle by lazy { NSAccessibilityAnnotationLabel_LAYOUT.varHandle() }

var NSAccessibilityAnnotationLabel: MemorySegment
    get() = NSAccessibilityAnnotationLabel_VH.get(NSAccessibilityAnnotationLabel_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAnnotationLabel_VH.set(NSAccessibilityAnnotationLabel_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAnnotationElement typedef const NSAccessibilityAnnotationAttributeKey = (Void)*
 */
private val NSAccessibilityAnnotationElement_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAnnotationElement_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAnnotationElement").orElseThrow() }
private val NSAccessibilityAnnotationElement_VH: VarHandle by lazy { NSAccessibilityAnnotationElement_LAYOUT.varHandle() }

var NSAccessibilityAnnotationElement: MemorySegment
    get() = NSAccessibilityAnnotationElement_VH.get(NSAccessibilityAnnotationElement_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAnnotationElement_VH.set(NSAccessibilityAnnotationElement_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAnnotationLocation typedef const NSAccessibilityAnnotationAttributeKey = (Void)*
 */
private val NSAccessibilityAnnotationLocation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAnnotationLocation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAnnotationLocation").orElseThrow() }
private val NSAccessibilityAnnotationLocation_VH: VarHandle by lazy { NSAccessibilityAnnotationLocation_LAYOUT.varHandle() }

var NSAccessibilityAnnotationLocation: MemorySegment
    get() = NSAccessibilityAnnotationLocation_VH.get(NSAccessibilityAnnotationLocation_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAnnotationLocation_VH.set(NSAccessibilityAnnotationLocation_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFontNameKey typedef const NSAccessibilityFontAttributeKey = (Void)*
 */
private val NSAccessibilityFontNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFontNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFontNameKey").orElseThrow() }
private val NSAccessibilityFontNameKey_VH: VarHandle by lazy { NSAccessibilityFontNameKey_LAYOUT.varHandle() }

var NSAccessibilityFontNameKey: MemorySegment
    get() = NSAccessibilityFontNameKey_VH.get(NSAccessibilityFontNameKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFontNameKey_VH.set(NSAccessibilityFontNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFontFamilyKey typedef const NSAccessibilityFontAttributeKey = (Void)*
 */
private val NSAccessibilityFontFamilyKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFontFamilyKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFontFamilyKey").orElseThrow() }
private val NSAccessibilityFontFamilyKey_VH: VarHandle by lazy { NSAccessibilityFontFamilyKey_LAYOUT.varHandle() }

var NSAccessibilityFontFamilyKey: MemorySegment
    get() = NSAccessibilityFontFamilyKey_VH.get(NSAccessibilityFontFamilyKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFontFamilyKey_VH.set(NSAccessibilityFontFamilyKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVisibleNameKey typedef const NSAccessibilityFontAttributeKey = (Void)*
 */
private val NSAccessibilityVisibleNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVisibleNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVisibleNameKey").orElseThrow() }
private val NSAccessibilityVisibleNameKey_VH: VarHandle by lazy { NSAccessibilityVisibleNameKey_LAYOUT.varHandle() }

var NSAccessibilityVisibleNameKey: MemorySegment
    get() = NSAccessibilityVisibleNameKey_VH.get(NSAccessibilityVisibleNameKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVisibleNameKey_VH.set(NSAccessibilityVisibleNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFontSizeKey typedef const NSAccessibilityFontAttributeKey = (Void)*
 */
private val NSAccessibilityFontSizeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFontSizeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFontSizeKey").orElseThrow() }
private val NSAccessibilityFontSizeKey_VH: VarHandle by lazy { NSAccessibilityFontSizeKey_LAYOUT.varHandle() }

var NSAccessibilityFontSizeKey: MemorySegment
    get() = NSAccessibilityFontSizeKey_VH.get(NSAccessibilityFontSizeKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFontSizeKey_VH.set(NSAccessibilityFontSizeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMainAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMainAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMainAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMainAttribute").orElseThrow() }
private val NSAccessibilityMainAttribute_VH: VarHandle by lazy { NSAccessibilityMainAttribute_LAYOUT.varHandle() }

var NSAccessibilityMainAttribute: MemorySegment
    get() = NSAccessibilityMainAttribute_VH.get(NSAccessibilityMainAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMainAttribute_VH.set(NSAccessibilityMainAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMinimizedAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMinimizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMinimizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMinimizedAttribute").orElseThrow() }
private val NSAccessibilityMinimizedAttribute_VH: VarHandle by lazy { NSAccessibilityMinimizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityMinimizedAttribute: MemorySegment
    get() = NSAccessibilityMinimizedAttribute_VH.get(NSAccessibilityMinimizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMinimizedAttribute_VH.set(NSAccessibilityMinimizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCloseButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityCloseButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCloseButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCloseButtonAttribute").orElseThrow() }
private val NSAccessibilityCloseButtonAttribute_VH: VarHandle by lazy { NSAccessibilityCloseButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityCloseButtonAttribute: MemorySegment
    get() = NSAccessibilityCloseButtonAttribute_VH.get(NSAccessibilityCloseButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCloseButtonAttribute_VH.set(NSAccessibilityCloseButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityZoomButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityZoomButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityZoomButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityZoomButtonAttribute").orElseThrow() }
private val NSAccessibilityZoomButtonAttribute_VH: VarHandle by lazy { NSAccessibilityZoomButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityZoomButtonAttribute: MemorySegment
    get() = NSAccessibilityZoomButtonAttribute_VH.get(NSAccessibilityZoomButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityZoomButtonAttribute_VH.set(NSAccessibilityZoomButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMinimizeButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMinimizeButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMinimizeButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMinimizeButtonAttribute").orElseThrow() }
private val NSAccessibilityMinimizeButtonAttribute_VH: VarHandle by lazy { NSAccessibilityMinimizeButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityMinimizeButtonAttribute: MemorySegment
    get() = NSAccessibilityMinimizeButtonAttribute_VH.get(NSAccessibilityMinimizeButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMinimizeButtonAttribute_VH.set(NSAccessibilityMinimizeButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityToolbarButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityToolbarButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityToolbarButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityToolbarButtonAttribute").orElseThrow() }
private val NSAccessibilityToolbarButtonAttribute_VH: VarHandle by lazy { NSAccessibilityToolbarButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityToolbarButtonAttribute: MemorySegment
    get() = NSAccessibilityToolbarButtonAttribute_VH.get(NSAccessibilityToolbarButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityToolbarButtonAttribute_VH.set(NSAccessibilityToolbarButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityProxyAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityProxyAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityProxyAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityProxyAttribute").orElseThrow() }
private val NSAccessibilityProxyAttribute_VH: VarHandle by lazy { NSAccessibilityProxyAttribute_LAYOUT.varHandle() }

var NSAccessibilityProxyAttribute: MemorySegment
    get() = NSAccessibilityProxyAttribute_VH.get(NSAccessibilityProxyAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityProxyAttribute_VH.set(NSAccessibilityProxyAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityGrowAreaAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityGrowAreaAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityGrowAreaAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityGrowAreaAttribute").orElseThrow() }
private val NSAccessibilityGrowAreaAttribute_VH: VarHandle by lazy { NSAccessibilityGrowAreaAttribute_LAYOUT.varHandle() }

var NSAccessibilityGrowAreaAttribute: MemorySegment
    get() = NSAccessibilityGrowAreaAttribute_VH.get(NSAccessibilityGrowAreaAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityGrowAreaAttribute_VH.set(NSAccessibilityGrowAreaAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityModalAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityModalAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityModalAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityModalAttribute").orElseThrow() }
private val NSAccessibilityModalAttribute_VH: VarHandle by lazy { NSAccessibilityModalAttribute_LAYOUT.varHandle() }

var NSAccessibilityModalAttribute: MemorySegment
    get() = NSAccessibilityModalAttribute_VH.get(NSAccessibilityModalAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityModalAttribute_VH.set(NSAccessibilityModalAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDefaultButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityDefaultButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDefaultButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDefaultButtonAttribute").orElseThrow() }
private val NSAccessibilityDefaultButtonAttribute_VH: VarHandle by lazy { NSAccessibilityDefaultButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityDefaultButtonAttribute: MemorySegment
    get() = NSAccessibilityDefaultButtonAttribute_VH.get(NSAccessibilityDefaultButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDefaultButtonAttribute_VH.set(NSAccessibilityDefaultButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCancelButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityCancelButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCancelButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCancelButtonAttribute").orElseThrow() }
private val NSAccessibilityCancelButtonAttribute_VH: VarHandle by lazy { NSAccessibilityCancelButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityCancelButtonAttribute: MemorySegment
    get() = NSAccessibilityCancelButtonAttribute_VH.get(NSAccessibilityCancelButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCancelButtonAttribute_VH.set(NSAccessibilityCancelButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFullScreenButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityFullScreenButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFullScreenButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFullScreenButtonAttribute").orElseThrow() }
private val NSAccessibilityFullScreenButtonAttribute_VH: VarHandle by lazy { NSAccessibilityFullScreenButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityFullScreenButtonAttribute: MemorySegment
    get() = NSAccessibilityFullScreenButtonAttribute_VH.get(NSAccessibilityFullScreenButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFullScreenButtonAttribute_VH.set(NSAccessibilityFullScreenButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMenuBarAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMenuBarAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMenuBarAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMenuBarAttribute").orElseThrow() }
private val NSAccessibilityMenuBarAttribute_VH: VarHandle by lazy { NSAccessibilityMenuBarAttribute_LAYOUT.varHandle() }

var NSAccessibilityMenuBarAttribute: MemorySegment
    get() = NSAccessibilityMenuBarAttribute_VH.get(NSAccessibilityMenuBarAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMenuBarAttribute_VH.set(NSAccessibilityMenuBarAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWindowsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityWindowsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWindowsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWindowsAttribute").orElseThrow() }
private val NSAccessibilityWindowsAttribute_VH: VarHandle by lazy { NSAccessibilityWindowsAttribute_LAYOUT.varHandle() }

var NSAccessibilityWindowsAttribute: MemorySegment
    get() = NSAccessibilityWindowsAttribute_VH.get(NSAccessibilityWindowsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWindowsAttribute_VH.set(NSAccessibilityWindowsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFrontmostAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityFrontmostAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFrontmostAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFrontmostAttribute").orElseThrow() }
private val NSAccessibilityFrontmostAttribute_VH: VarHandle by lazy { NSAccessibilityFrontmostAttribute_LAYOUT.varHandle() }

var NSAccessibilityFrontmostAttribute: MemorySegment
    get() = NSAccessibilityFrontmostAttribute_VH.get(NSAccessibilityFrontmostAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFrontmostAttribute_VH.set(NSAccessibilityFrontmostAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHiddenAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityHiddenAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHiddenAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHiddenAttribute").orElseThrow() }
private val NSAccessibilityHiddenAttribute_VH: VarHandle by lazy { NSAccessibilityHiddenAttribute_LAYOUT.varHandle() }

var NSAccessibilityHiddenAttribute: MemorySegment
    get() = NSAccessibilityHiddenAttribute_VH.get(NSAccessibilityHiddenAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHiddenAttribute_VH.set(NSAccessibilityHiddenAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMainWindowAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMainWindowAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMainWindowAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMainWindowAttribute").orElseThrow() }
private val NSAccessibilityMainWindowAttribute_VH: VarHandle by lazy { NSAccessibilityMainWindowAttribute_LAYOUT.varHandle() }

var NSAccessibilityMainWindowAttribute: MemorySegment
    get() = NSAccessibilityMainWindowAttribute_VH.get(NSAccessibilityMainWindowAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMainWindowAttribute_VH.set(NSAccessibilityMainWindowAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFocusedWindowAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityFocusedWindowAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFocusedWindowAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFocusedWindowAttribute").orElseThrow() }
private val NSAccessibilityFocusedWindowAttribute_VH: VarHandle by lazy { NSAccessibilityFocusedWindowAttribute_LAYOUT.varHandle() }

var NSAccessibilityFocusedWindowAttribute: MemorySegment
    get() = NSAccessibilityFocusedWindowAttribute_VH.get(NSAccessibilityFocusedWindowAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFocusedWindowAttribute_VH.set(NSAccessibilityFocusedWindowAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFocusedUIElementAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityFocusedUIElementAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFocusedUIElementAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFocusedUIElementAttribute").orElseThrow() }
private val NSAccessibilityFocusedUIElementAttribute_VH: VarHandle by lazy { NSAccessibilityFocusedUIElementAttribute_LAYOUT.varHandle() }

var NSAccessibilityFocusedUIElementAttribute: MemorySegment
    get() = NSAccessibilityFocusedUIElementAttribute_VH.get(NSAccessibilityFocusedUIElementAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFocusedUIElementAttribute_VH.set(NSAccessibilityFocusedUIElementAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityExtrasMenuBarAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityExtrasMenuBarAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityExtrasMenuBarAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityExtrasMenuBarAttribute").orElseThrow() }
private val NSAccessibilityExtrasMenuBarAttribute_VH: VarHandle by lazy { NSAccessibilityExtrasMenuBarAttribute_LAYOUT.varHandle() }

var NSAccessibilityExtrasMenuBarAttribute: MemorySegment
    get() = NSAccessibilityExtrasMenuBarAttribute_VH.get(NSAccessibilityExtrasMenuBarAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityExtrasMenuBarAttribute_VH.set(NSAccessibilityExtrasMenuBarAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityOrientationAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityOrientationAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityOrientationAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityOrientationAttribute").orElseThrow() }
private val NSAccessibilityOrientationAttribute_VH: VarHandle by lazy { NSAccessibilityOrientationAttribute_LAYOUT.varHandle() }

var NSAccessibilityOrientationAttribute: MemorySegment
    get() = NSAccessibilityOrientationAttribute_VH.get(NSAccessibilityOrientationAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityOrientationAttribute_VH.set(NSAccessibilityOrientationAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVerticalOrientationValue typedef const NSAccessibilityOrientationValue = (Void)*
 */
private val NSAccessibilityVerticalOrientationValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVerticalOrientationValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVerticalOrientationValue").orElseThrow() }
private val NSAccessibilityVerticalOrientationValue_VH: VarHandle by lazy { NSAccessibilityVerticalOrientationValue_LAYOUT.varHandle() }

var NSAccessibilityVerticalOrientationValue: MemorySegment
    get() = NSAccessibilityVerticalOrientationValue_VH.get(NSAccessibilityVerticalOrientationValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVerticalOrientationValue_VH.set(NSAccessibilityVerticalOrientationValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHorizontalOrientationValue typedef const NSAccessibilityOrientationValue = (Void)*
 */
private val NSAccessibilityHorizontalOrientationValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHorizontalOrientationValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHorizontalOrientationValue").orElseThrow() }
private val NSAccessibilityHorizontalOrientationValue_VH: VarHandle by lazy { NSAccessibilityHorizontalOrientationValue_LAYOUT.varHandle() }

var NSAccessibilityHorizontalOrientationValue: MemorySegment
    get() = NSAccessibilityHorizontalOrientationValue_VH.get(NSAccessibilityHorizontalOrientationValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHorizontalOrientationValue_VH.set(NSAccessibilityHorizontalOrientationValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnknownOrientationValue typedef const NSAccessibilityOrientationValue = (Void)*
 */
private val NSAccessibilityUnknownOrientationValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnknownOrientationValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnknownOrientationValue").orElseThrow() }
private val NSAccessibilityUnknownOrientationValue_VH: VarHandle by lazy { NSAccessibilityUnknownOrientationValue_LAYOUT.varHandle() }

var NSAccessibilityUnknownOrientationValue: MemorySegment
    get() = NSAccessibilityUnknownOrientationValue_VH.get(NSAccessibilityUnknownOrientationValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnknownOrientationValue_VH.set(NSAccessibilityUnknownOrientationValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityColumnTitlesAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityColumnTitlesAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityColumnTitlesAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityColumnTitlesAttribute").orElseThrow() }
private val NSAccessibilityColumnTitlesAttribute_VH: VarHandle by lazy { NSAccessibilityColumnTitlesAttribute_LAYOUT.varHandle() }

var NSAccessibilityColumnTitlesAttribute: MemorySegment
    get() = NSAccessibilityColumnTitlesAttribute_VH.get(NSAccessibilityColumnTitlesAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityColumnTitlesAttribute_VH.set(NSAccessibilityColumnTitlesAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySearchButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchButtonAttribute").orElseThrow() }
private val NSAccessibilitySearchButtonAttribute_VH: VarHandle by lazy { NSAccessibilitySearchButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilitySearchButtonAttribute: MemorySegment
    get() = NSAccessibilitySearchButtonAttribute_VH.get(NSAccessibilitySearchButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchButtonAttribute_VH.set(NSAccessibilitySearchButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchMenuAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySearchMenuAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchMenuAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchMenuAttribute").orElseThrow() }
private val NSAccessibilitySearchMenuAttribute_VH: VarHandle by lazy { NSAccessibilitySearchMenuAttribute_LAYOUT.varHandle() }

var NSAccessibilitySearchMenuAttribute: MemorySegment
    get() = NSAccessibilitySearchMenuAttribute_VH.get(NSAccessibilitySearchMenuAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchMenuAttribute_VH.set(NSAccessibilitySearchMenuAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityClearButtonAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityClearButtonAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityClearButtonAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityClearButtonAttribute").orElseThrow() }
private val NSAccessibilityClearButtonAttribute_VH: VarHandle by lazy { NSAccessibilityClearButtonAttribute_LAYOUT.varHandle() }

var NSAccessibilityClearButtonAttribute: MemorySegment
    get() = NSAccessibilityClearButtonAttribute_VH.get(NSAccessibilityClearButtonAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityClearButtonAttribute_VH.set(NSAccessibilityClearButtonAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRowsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityRowsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRowsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRowsAttribute").orElseThrow() }
private val NSAccessibilityRowsAttribute_VH: VarHandle by lazy { NSAccessibilityRowsAttribute_LAYOUT.varHandle() }

var NSAccessibilityRowsAttribute: MemorySegment
    get() = NSAccessibilityRowsAttribute_VH.get(NSAccessibilityRowsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRowsAttribute_VH.set(NSAccessibilityRowsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVisibleRowsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityVisibleRowsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVisibleRowsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVisibleRowsAttribute").orElseThrow() }
private val NSAccessibilityVisibleRowsAttribute_VH: VarHandle by lazy { NSAccessibilityVisibleRowsAttribute_LAYOUT.varHandle() }

var NSAccessibilityVisibleRowsAttribute: MemorySegment
    get() = NSAccessibilityVisibleRowsAttribute_VH.get(NSAccessibilityVisibleRowsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVisibleRowsAttribute_VH.set(NSAccessibilityVisibleRowsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedRowsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySelectedRowsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedRowsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedRowsAttribute").orElseThrow() }
private val NSAccessibilitySelectedRowsAttribute_VH: VarHandle by lazy { NSAccessibilitySelectedRowsAttribute_LAYOUT.varHandle() }

var NSAccessibilitySelectedRowsAttribute: MemorySegment
    get() = NSAccessibilitySelectedRowsAttribute_VH.get(NSAccessibilitySelectedRowsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedRowsAttribute_VH.set(NSAccessibilitySelectedRowsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityColumnsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityColumnsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityColumnsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityColumnsAttribute").orElseThrow() }
private val NSAccessibilityColumnsAttribute_VH: VarHandle by lazy { NSAccessibilityColumnsAttribute_LAYOUT.varHandle() }

var NSAccessibilityColumnsAttribute: MemorySegment
    get() = NSAccessibilityColumnsAttribute_VH.get(NSAccessibilityColumnsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityColumnsAttribute_VH.set(NSAccessibilityColumnsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVisibleColumnsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityVisibleColumnsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVisibleColumnsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVisibleColumnsAttribute").orElseThrow() }
private val NSAccessibilityVisibleColumnsAttribute_VH: VarHandle by lazy { NSAccessibilityVisibleColumnsAttribute_LAYOUT.varHandle() }

var NSAccessibilityVisibleColumnsAttribute: MemorySegment
    get() = NSAccessibilityVisibleColumnsAttribute_VH.get(NSAccessibilityVisibleColumnsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVisibleColumnsAttribute_VH.set(NSAccessibilityVisibleColumnsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedColumnsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySelectedColumnsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedColumnsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedColumnsAttribute").orElseThrow() }
private val NSAccessibilitySelectedColumnsAttribute_VH: VarHandle by lazy { NSAccessibilitySelectedColumnsAttribute_LAYOUT.varHandle() }

var NSAccessibilitySelectedColumnsAttribute: MemorySegment
    get() = NSAccessibilitySelectedColumnsAttribute_VH.get(NSAccessibilitySelectedColumnsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedColumnsAttribute_VH.set(NSAccessibilitySelectedColumnsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySortDirectionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySortDirectionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySortDirectionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySortDirectionAttribute").orElseThrow() }
private val NSAccessibilitySortDirectionAttribute_VH: VarHandle by lazy { NSAccessibilitySortDirectionAttribute_LAYOUT.varHandle() }

var NSAccessibilitySortDirectionAttribute: MemorySegment
    get() = NSAccessibilitySortDirectionAttribute_VH.get(NSAccessibilitySortDirectionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySortDirectionAttribute_VH.set(NSAccessibilitySortDirectionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedCellsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilitySelectedCellsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedCellsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedCellsAttribute").orElseThrow() }
private val NSAccessibilitySelectedCellsAttribute_VH: VarHandle by lazy { NSAccessibilitySelectedCellsAttribute_LAYOUT.varHandle() }

var NSAccessibilitySelectedCellsAttribute: MemorySegment
    get() = NSAccessibilitySelectedCellsAttribute_VH.get(NSAccessibilitySelectedCellsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedCellsAttribute_VH.set(NSAccessibilitySelectedCellsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVisibleCellsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityVisibleCellsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVisibleCellsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVisibleCellsAttribute").orElseThrow() }
private val NSAccessibilityVisibleCellsAttribute_VH: VarHandle by lazy { NSAccessibilityVisibleCellsAttribute_LAYOUT.varHandle() }

var NSAccessibilityVisibleCellsAttribute: MemorySegment
    get() = NSAccessibilityVisibleCellsAttribute_VH.get(NSAccessibilityVisibleCellsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVisibleCellsAttribute_VH.set(NSAccessibilityVisibleCellsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRowHeaderUIElementsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityRowHeaderUIElementsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRowHeaderUIElementsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRowHeaderUIElementsAttribute").orElseThrow() }
private val NSAccessibilityRowHeaderUIElementsAttribute_VH: VarHandle by lazy { NSAccessibilityRowHeaderUIElementsAttribute_LAYOUT.varHandle() }

var NSAccessibilityRowHeaderUIElementsAttribute: MemorySegment
    get() = NSAccessibilityRowHeaderUIElementsAttribute_VH.get(NSAccessibilityRowHeaderUIElementsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRowHeaderUIElementsAttribute_VH.set(NSAccessibilityRowHeaderUIElementsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityColumnHeaderUIElementsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityColumnHeaderUIElementsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityColumnHeaderUIElementsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityColumnHeaderUIElementsAttribute").orElseThrow() }
private val NSAccessibilityColumnHeaderUIElementsAttribute_VH: VarHandle by lazy { NSAccessibilityColumnHeaderUIElementsAttribute_LAYOUT.varHandle() }

var NSAccessibilityColumnHeaderUIElementsAttribute: MemorySegment
    get() = NSAccessibilityColumnHeaderUIElementsAttribute_VH.get(NSAccessibilityColumnHeaderUIElementsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityColumnHeaderUIElementsAttribute_VH.set(NSAccessibilityColumnHeaderUIElementsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCellForColumnAndRowParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityCellForColumnAndRowParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCellForColumnAndRowParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCellForColumnAndRowParameterizedAttribute").orElseThrow() }
private val NSAccessibilityCellForColumnAndRowParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityCellForColumnAndRowParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityCellForColumnAndRowParameterizedAttribute: MemorySegment
    get() = NSAccessibilityCellForColumnAndRowParameterizedAttribute_VH.get(NSAccessibilityCellForColumnAndRowParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCellForColumnAndRowParameterizedAttribute_VH.set(NSAccessibilityCellForColumnAndRowParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRowIndexRangeAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityRowIndexRangeAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRowIndexRangeAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRowIndexRangeAttribute").orElseThrow() }
private val NSAccessibilityRowIndexRangeAttribute_VH: VarHandle by lazy { NSAccessibilityRowIndexRangeAttribute_LAYOUT.varHandle() }

var NSAccessibilityRowIndexRangeAttribute: MemorySegment
    get() = NSAccessibilityRowIndexRangeAttribute_VH.get(NSAccessibilityRowIndexRangeAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRowIndexRangeAttribute_VH.set(NSAccessibilityRowIndexRangeAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityColumnIndexRangeAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityColumnIndexRangeAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityColumnIndexRangeAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityColumnIndexRangeAttribute").orElseThrow() }
private val NSAccessibilityColumnIndexRangeAttribute_VH: VarHandle by lazy { NSAccessibilityColumnIndexRangeAttribute_LAYOUT.varHandle() }

var NSAccessibilityColumnIndexRangeAttribute: MemorySegment
    get() = NSAccessibilityColumnIndexRangeAttribute_VH.get(NSAccessibilityColumnIndexRangeAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityColumnIndexRangeAttribute_VH.set(NSAccessibilityColumnIndexRangeAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHorizontalUnitsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityHorizontalUnitsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHorizontalUnitsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHorizontalUnitsAttribute").orElseThrow() }
private val NSAccessibilityHorizontalUnitsAttribute_VH: VarHandle by lazy { NSAccessibilityHorizontalUnitsAttribute_LAYOUT.varHandle() }

var NSAccessibilityHorizontalUnitsAttribute: MemorySegment
    get() = NSAccessibilityHorizontalUnitsAttribute_VH.get(NSAccessibilityHorizontalUnitsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHorizontalUnitsAttribute_VH.set(NSAccessibilityHorizontalUnitsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVerticalUnitsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityVerticalUnitsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVerticalUnitsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVerticalUnitsAttribute").orElseThrow() }
private val NSAccessibilityVerticalUnitsAttribute_VH: VarHandle by lazy { NSAccessibilityVerticalUnitsAttribute_LAYOUT.varHandle() }

var NSAccessibilityVerticalUnitsAttribute: MemorySegment
    get() = NSAccessibilityVerticalUnitsAttribute_VH.get(NSAccessibilityVerticalUnitsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVerticalUnitsAttribute_VH.set(NSAccessibilityVerticalUnitsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHorizontalUnitDescriptionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityHorizontalUnitDescriptionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHorizontalUnitDescriptionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHorizontalUnitDescriptionAttribute").orElseThrow() }
private val NSAccessibilityHorizontalUnitDescriptionAttribute_VH: VarHandle by lazy { NSAccessibilityHorizontalUnitDescriptionAttribute_LAYOUT.varHandle() }

var NSAccessibilityHorizontalUnitDescriptionAttribute: MemorySegment
    get() = NSAccessibilityHorizontalUnitDescriptionAttribute_VH.get(NSAccessibilityHorizontalUnitDescriptionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHorizontalUnitDescriptionAttribute_VH.set(NSAccessibilityHorizontalUnitDescriptionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVerticalUnitDescriptionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityVerticalUnitDescriptionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVerticalUnitDescriptionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVerticalUnitDescriptionAttribute").orElseThrow() }
private val NSAccessibilityVerticalUnitDescriptionAttribute_VH: VarHandle by lazy { NSAccessibilityVerticalUnitDescriptionAttribute_LAYOUT.varHandle() }

var NSAccessibilityVerticalUnitDescriptionAttribute: MemorySegment
    get() = NSAccessibilityVerticalUnitDescriptionAttribute_VH.get(NSAccessibilityVerticalUnitDescriptionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVerticalUnitDescriptionAttribute_VH.set(NSAccessibilityVerticalUnitDescriptionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLayoutPointForScreenPointParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityLayoutPointForScreenPointParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLayoutPointForScreenPointParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLayoutPointForScreenPointParameterizedAttribute").orElseThrow() }
private val NSAccessibilityLayoutPointForScreenPointParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityLayoutPointForScreenPointParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityLayoutPointForScreenPointParameterizedAttribute: MemorySegment
    get() = NSAccessibilityLayoutPointForScreenPointParameterizedAttribute_VH.get(NSAccessibilityLayoutPointForScreenPointParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLayoutPointForScreenPointParameterizedAttribute_VH.set(NSAccessibilityLayoutPointForScreenPointParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute").orElseThrow() }
private val NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute: MemorySegment
    get() = NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute_VH.get(NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute_VH.set(NSAccessibilityLayoutSizeForScreenSizeParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityScreenPointForLayoutPointParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityScreenPointForLayoutPointParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityScreenPointForLayoutPointParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityScreenPointForLayoutPointParameterizedAttribute").orElseThrow() }
private val NSAccessibilityScreenPointForLayoutPointParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityScreenPointForLayoutPointParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityScreenPointForLayoutPointParameterizedAttribute: MemorySegment
    get() = NSAccessibilityScreenPointForLayoutPointParameterizedAttribute_VH.get(NSAccessibilityScreenPointForLayoutPointParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityScreenPointForLayoutPointParameterizedAttribute_VH.set(NSAccessibilityScreenPointForLayoutPointParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute").orElseThrow() }
private val NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute: MemorySegment
    get() = NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute_VH.get(NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute_VH.set(NSAccessibilityScreenSizeForLayoutSizeParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHandlesAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityHandlesAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHandlesAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHandlesAttribute").orElseThrow() }
private val NSAccessibilityHandlesAttribute_VH: VarHandle by lazy { NSAccessibilityHandlesAttribute_LAYOUT.varHandle() }

var NSAccessibilityHandlesAttribute: MemorySegment
    get() = NSAccessibilityHandlesAttribute_VH.get(NSAccessibilityHandlesAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHandlesAttribute_VH.set(NSAccessibilityHandlesAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAscendingSortDirectionValue typedef const NSAccessibilitySortDirectionValue = (Void)*
 */
private val NSAccessibilityAscendingSortDirectionValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAscendingSortDirectionValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAscendingSortDirectionValue").orElseThrow() }
private val NSAccessibilityAscendingSortDirectionValue_VH: VarHandle by lazy { NSAccessibilityAscendingSortDirectionValue_LAYOUT.varHandle() }

var NSAccessibilityAscendingSortDirectionValue: MemorySegment
    get() = NSAccessibilityAscendingSortDirectionValue_VH.get(NSAccessibilityAscendingSortDirectionValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAscendingSortDirectionValue_VH.set(NSAccessibilityAscendingSortDirectionValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDescendingSortDirectionValue typedef const NSAccessibilitySortDirectionValue = (Void)*
 */
private val NSAccessibilityDescendingSortDirectionValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDescendingSortDirectionValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDescendingSortDirectionValue").orElseThrow() }
private val NSAccessibilityDescendingSortDirectionValue_VH: VarHandle by lazy { NSAccessibilityDescendingSortDirectionValue_LAYOUT.varHandle() }

var NSAccessibilityDescendingSortDirectionValue: MemorySegment
    get() = NSAccessibilityDescendingSortDirectionValue_VH.get(NSAccessibilityDescendingSortDirectionValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDescendingSortDirectionValue_VH.set(NSAccessibilityDescendingSortDirectionValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnknownSortDirectionValue typedef const NSAccessibilitySortDirectionValue = (Void)*
 */
private val NSAccessibilityUnknownSortDirectionValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnknownSortDirectionValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnknownSortDirectionValue").orElseThrow() }
private val NSAccessibilityUnknownSortDirectionValue_VH: VarHandle by lazy { NSAccessibilityUnknownSortDirectionValue_LAYOUT.varHandle() }

var NSAccessibilityUnknownSortDirectionValue: MemorySegment
    get() = NSAccessibilityUnknownSortDirectionValue_VH.get(NSAccessibilityUnknownSortDirectionValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnknownSortDirectionValue_VH.set(NSAccessibilityUnknownSortDirectionValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDisclosingAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityDisclosingAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDisclosingAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDisclosingAttribute").orElseThrow() }
private val NSAccessibilityDisclosingAttribute_VH: VarHandle by lazy { NSAccessibilityDisclosingAttribute_LAYOUT.varHandle() }

var NSAccessibilityDisclosingAttribute: MemorySegment
    get() = NSAccessibilityDisclosingAttribute_VH.get(NSAccessibilityDisclosingAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDisclosingAttribute_VH.set(NSAccessibilityDisclosingAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDisclosedRowsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityDisclosedRowsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDisclosedRowsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDisclosedRowsAttribute").orElseThrow() }
private val NSAccessibilityDisclosedRowsAttribute_VH: VarHandle by lazy { NSAccessibilityDisclosedRowsAttribute_LAYOUT.varHandle() }

var NSAccessibilityDisclosedRowsAttribute: MemorySegment
    get() = NSAccessibilityDisclosedRowsAttribute_VH.get(NSAccessibilityDisclosedRowsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDisclosedRowsAttribute_VH.set(NSAccessibilityDisclosedRowsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDisclosedByRowAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityDisclosedByRowAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDisclosedByRowAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDisclosedByRowAttribute").orElseThrow() }
private val NSAccessibilityDisclosedByRowAttribute_VH: VarHandle by lazy { NSAccessibilityDisclosedByRowAttribute_LAYOUT.varHandle() }

var NSAccessibilityDisclosedByRowAttribute: MemorySegment
    get() = NSAccessibilityDisclosedByRowAttribute_VH.get(NSAccessibilityDisclosedByRowAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDisclosedByRowAttribute_VH.set(NSAccessibilityDisclosedByRowAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDisclosureLevelAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityDisclosureLevelAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDisclosureLevelAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDisclosureLevelAttribute").orElseThrow() }
private val NSAccessibilityDisclosureLevelAttribute_VH: VarHandle by lazy { NSAccessibilityDisclosureLevelAttribute_LAYOUT.varHandle() }

var NSAccessibilityDisclosureLevelAttribute: MemorySegment
    get() = NSAccessibilityDisclosureLevelAttribute_VH.get(NSAccessibilityDisclosureLevelAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDisclosureLevelAttribute_VH.set(NSAccessibilityDisclosureLevelAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAllowedValuesAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityAllowedValuesAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAllowedValuesAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAllowedValuesAttribute").orElseThrow() }
private val NSAccessibilityAllowedValuesAttribute_VH: VarHandle by lazy { NSAccessibilityAllowedValuesAttribute_LAYOUT.varHandle() }

var NSAccessibilityAllowedValuesAttribute: MemorySegment
    get() = NSAccessibilityAllowedValuesAttribute_VH.get(NSAccessibilityAllowedValuesAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAllowedValuesAttribute_VH.set(NSAccessibilityAllowedValuesAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLabelUIElementsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityLabelUIElementsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLabelUIElementsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLabelUIElementsAttribute").orElseThrow() }
private val NSAccessibilityLabelUIElementsAttribute_VH: VarHandle by lazy { NSAccessibilityLabelUIElementsAttribute_LAYOUT.varHandle() }

var NSAccessibilityLabelUIElementsAttribute: MemorySegment
    get() = NSAccessibilityLabelUIElementsAttribute_VH.get(NSAccessibilityLabelUIElementsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLabelUIElementsAttribute_VH.set(NSAccessibilityLabelUIElementsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLabelValueAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityLabelValueAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLabelValueAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLabelValueAttribute").orElseThrow() }
private val NSAccessibilityLabelValueAttribute_VH: VarHandle by lazy { NSAccessibilityLabelValueAttribute_LAYOUT.varHandle() }

var NSAccessibilityLabelValueAttribute: MemorySegment
    get() = NSAccessibilityLabelValueAttribute_VH.get(NSAccessibilityLabelValueAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLabelValueAttribute_VH.set(NSAccessibilityLabelValueAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMatteHoleAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMatteHoleAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMatteHoleAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMatteHoleAttribute").orElseThrow() }
private val NSAccessibilityMatteHoleAttribute_VH: VarHandle by lazy { NSAccessibilityMatteHoleAttribute_LAYOUT.varHandle() }

var NSAccessibilityMatteHoleAttribute: MemorySegment
    get() = NSAccessibilityMatteHoleAttribute_VH.get(NSAccessibilityMatteHoleAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMatteHoleAttribute_VH.set(NSAccessibilityMatteHoleAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMatteContentUIElementAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMatteContentUIElementAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMatteContentUIElementAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMatteContentUIElementAttribute").orElseThrow() }
private val NSAccessibilityMatteContentUIElementAttribute_VH: VarHandle by lazy { NSAccessibilityMatteContentUIElementAttribute_LAYOUT.varHandle() }

var NSAccessibilityMatteContentUIElementAttribute: MemorySegment
    get() = NSAccessibilityMatteContentUIElementAttribute_VH.get(NSAccessibilityMatteContentUIElementAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMatteContentUIElementAttribute_VH.set(NSAccessibilityMatteContentUIElementAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMarkerUIElementsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMarkerUIElementsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMarkerUIElementsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMarkerUIElementsAttribute").orElseThrow() }
private val NSAccessibilityMarkerUIElementsAttribute_VH: VarHandle by lazy { NSAccessibilityMarkerUIElementsAttribute_LAYOUT.varHandle() }

var NSAccessibilityMarkerUIElementsAttribute: MemorySegment
    get() = NSAccessibilityMarkerUIElementsAttribute_VH.get(NSAccessibilityMarkerUIElementsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMarkerUIElementsAttribute_VH.set(NSAccessibilityMarkerUIElementsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMarkerValuesAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMarkerValuesAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMarkerValuesAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMarkerValuesAttribute").orElseThrow() }
private val NSAccessibilityMarkerValuesAttribute_VH: VarHandle by lazy { NSAccessibilityMarkerValuesAttribute_LAYOUT.varHandle() }

var NSAccessibilityMarkerValuesAttribute: MemorySegment
    get() = NSAccessibilityMarkerValuesAttribute_VH.get(NSAccessibilityMarkerValuesAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMarkerValuesAttribute_VH.set(NSAccessibilityMarkerValuesAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMarkerGroupUIElementAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMarkerGroupUIElementAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMarkerGroupUIElementAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMarkerGroupUIElementAttribute").orElseThrow() }
private val NSAccessibilityMarkerGroupUIElementAttribute_VH: VarHandle by lazy { NSAccessibilityMarkerGroupUIElementAttribute_LAYOUT.varHandle() }

var NSAccessibilityMarkerGroupUIElementAttribute: MemorySegment
    get() = NSAccessibilityMarkerGroupUIElementAttribute_VH.get(NSAccessibilityMarkerGroupUIElementAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMarkerGroupUIElementAttribute_VH.set(NSAccessibilityMarkerGroupUIElementAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnitsAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityUnitsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnitsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnitsAttribute").orElseThrow() }
private val NSAccessibilityUnitsAttribute_VH: VarHandle by lazy { NSAccessibilityUnitsAttribute_LAYOUT.varHandle() }

var NSAccessibilityUnitsAttribute: MemorySegment
    get() = NSAccessibilityUnitsAttribute_VH.get(NSAccessibilityUnitsAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnitsAttribute_VH.set(NSAccessibilityUnitsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnitDescriptionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityUnitDescriptionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnitDescriptionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnitDescriptionAttribute").orElseThrow() }
private val NSAccessibilityUnitDescriptionAttribute_VH: VarHandle by lazy { NSAccessibilityUnitDescriptionAttribute_LAYOUT.varHandle() }

var NSAccessibilityUnitDescriptionAttribute: MemorySegment
    get() = NSAccessibilityUnitDescriptionAttribute_VH.get(NSAccessibilityUnitDescriptionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnitDescriptionAttribute_VH.set(NSAccessibilityUnitDescriptionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMarkerTypeAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMarkerTypeAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMarkerTypeAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMarkerTypeAttribute").orElseThrow() }
private val NSAccessibilityMarkerTypeAttribute_VH: VarHandle by lazy { NSAccessibilityMarkerTypeAttribute_LAYOUT.varHandle() }

var NSAccessibilityMarkerTypeAttribute: MemorySegment
    get() = NSAccessibilityMarkerTypeAttribute_VH.get(NSAccessibilityMarkerTypeAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMarkerTypeAttribute_VH.set(NSAccessibilityMarkerTypeAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMarkerTypeDescriptionAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityMarkerTypeDescriptionAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMarkerTypeDescriptionAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMarkerTypeDescriptionAttribute").orElseThrow() }
private val NSAccessibilityMarkerTypeDescriptionAttribute_VH: VarHandle by lazy { NSAccessibilityMarkerTypeDescriptionAttribute_LAYOUT.varHandle() }

var NSAccessibilityMarkerTypeDescriptionAttribute: MemorySegment
    get() = NSAccessibilityMarkerTypeDescriptionAttribute_VH.get(NSAccessibilityMarkerTypeDescriptionAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMarkerTypeDescriptionAttribute_VH.set(NSAccessibilityMarkerTypeDescriptionAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityIdentifierAttribute typedef const NSAccessibilityAttributeName = (Void)*
 */
private val NSAccessibilityIdentifierAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityIdentifierAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityIdentifierAttribute").orElseThrow() }
private val NSAccessibilityIdentifierAttribute_VH: VarHandle by lazy { NSAccessibilityIdentifierAttribute_LAYOUT.varHandle() }

var NSAccessibilityIdentifierAttribute: MemorySegment
    get() = NSAccessibilityIdentifierAttribute_VH.get(NSAccessibilityIdentifierAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityIdentifierAttribute_VH.set(NSAccessibilityIdentifierAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLeftTabStopMarkerTypeValue typedef const NSAccessibilityRulerMarkerTypeValue = (Void)*
 */
private val NSAccessibilityLeftTabStopMarkerTypeValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLeftTabStopMarkerTypeValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLeftTabStopMarkerTypeValue").orElseThrow() }
private val NSAccessibilityLeftTabStopMarkerTypeValue_VH: VarHandle by lazy { NSAccessibilityLeftTabStopMarkerTypeValue_LAYOUT.varHandle() }

var NSAccessibilityLeftTabStopMarkerTypeValue: MemorySegment
    get() = NSAccessibilityLeftTabStopMarkerTypeValue_VH.get(NSAccessibilityLeftTabStopMarkerTypeValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLeftTabStopMarkerTypeValue_VH.set(NSAccessibilityLeftTabStopMarkerTypeValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRightTabStopMarkerTypeValue typedef const NSAccessibilityRulerMarkerTypeValue = (Void)*
 */
private val NSAccessibilityRightTabStopMarkerTypeValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRightTabStopMarkerTypeValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRightTabStopMarkerTypeValue").orElseThrow() }
private val NSAccessibilityRightTabStopMarkerTypeValue_VH: VarHandle by lazy { NSAccessibilityRightTabStopMarkerTypeValue_LAYOUT.varHandle() }

var NSAccessibilityRightTabStopMarkerTypeValue: MemorySegment
    get() = NSAccessibilityRightTabStopMarkerTypeValue_VH.get(NSAccessibilityRightTabStopMarkerTypeValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRightTabStopMarkerTypeValue_VH.set(NSAccessibilityRightTabStopMarkerTypeValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCenterTabStopMarkerTypeValue typedef const NSAccessibilityRulerMarkerTypeValue = (Void)*
 */
private val NSAccessibilityCenterTabStopMarkerTypeValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCenterTabStopMarkerTypeValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCenterTabStopMarkerTypeValue").orElseThrow() }
private val NSAccessibilityCenterTabStopMarkerTypeValue_VH: VarHandle by lazy { NSAccessibilityCenterTabStopMarkerTypeValue_LAYOUT.varHandle() }

var NSAccessibilityCenterTabStopMarkerTypeValue: MemorySegment
    get() = NSAccessibilityCenterTabStopMarkerTypeValue_VH.get(NSAccessibilityCenterTabStopMarkerTypeValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCenterTabStopMarkerTypeValue_VH.set(NSAccessibilityCenterTabStopMarkerTypeValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDecimalTabStopMarkerTypeValue typedef const NSAccessibilityRulerMarkerTypeValue = (Void)*
 */
private val NSAccessibilityDecimalTabStopMarkerTypeValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDecimalTabStopMarkerTypeValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDecimalTabStopMarkerTypeValue").orElseThrow() }
private val NSAccessibilityDecimalTabStopMarkerTypeValue_VH: VarHandle by lazy { NSAccessibilityDecimalTabStopMarkerTypeValue_LAYOUT.varHandle() }

var NSAccessibilityDecimalTabStopMarkerTypeValue: MemorySegment
    get() = NSAccessibilityDecimalTabStopMarkerTypeValue_VH.get(NSAccessibilityDecimalTabStopMarkerTypeValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDecimalTabStopMarkerTypeValue_VH.set(NSAccessibilityDecimalTabStopMarkerTypeValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadIndentMarkerTypeValue typedef const NSAccessibilityRulerMarkerTypeValue = (Void)*
 */
private val NSAccessibilityHeadIndentMarkerTypeValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadIndentMarkerTypeValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadIndentMarkerTypeValue").orElseThrow() }
private val NSAccessibilityHeadIndentMarkerTypeValue_VH: VarHandle by lazy { NSAccessibilityHeadIndentMarkerTypeValue_LAYOUT.varHandle() }

var NSAccessibilityHeadIndentMarkerTypeValue: MemorySegment
    get() = NSAccessibilityHeadIndentMarkerTypeValue_VH.get(NSAccessibilityHeadIndentMarkerTypeValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadIndentMarkerTypeValue_VH.set(NSAccessibilityHeadIndentMarkerTypeValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTailIndentMarkerTypeValue typedef const NSAccessibilityRulerMarkerTypeValue = (Void)*
 */
private val NSAccessibilityTailIndentMarkerTypeValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTailIndentMarkerTypeValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTailIndentMarkerTypeValue").orElseThrow() }
private val NSAccessibilityTailIndentMarkerTypeValue_VH: VarHandle by lazy { NSAccessibilityTailIndentMarkerTypeValue_LAYOUT.varHandle() }

var NSAccessibilityTailIndentMarkerTypeValue: MemorySegment
    get() = NSAccessibilityTailIndentMarkerTypeValue_VH.get(NSAccessibilityTailIndentMarkerTypeValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTailIndentMarkerTypeValue_VH.set(NSAccessibilityTailIndentMarkerTypeValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFirstLineIndentMarkerTypeValue typedef const NSAccessibilityRulerMarkerTypeValue = (Void)*
 */
private val NSAccessibilityFirstLineIndentMarkerTypeValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFirstLineIndentMarkerTypeValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFirstLineIndentMarkerTypeValue").orElseThrow() }
private val NSAccessibilityFirstLineIndentMarkerTypeValue_VH: VarHandle by lazy { NSAccessibilityFirstLineIndentMarkerTypeValue_LAYOUT.varHandle() }

var NSAccessibilityFirstLineIndentMarkerTypeValue: MemorySegment
    get() = NSAccessibilityFirstLineIndentMarkerTypeValue_VH.get(NSAccessibilityFirstLineIndentMarkerTypeValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFirstLineIndentMarkerTypeValue_VH.set(NSAccessibilityFirstLineIndentMarkerTypeValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnknownMarkerTypeValue typedef const NSAccessibilityRulerMarkerTypeValue = (Void)*
 */
private val NSAccessibilityUnknownMarkerTypeValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnknownMarkerTypeValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnknownMarkerTypeValue").orElseThrow() }
private val NSAccessibilityUnknownMarkerTypeValue_VH: VarHandle by lazy { NSAccessibilityUnknownMarkerTypeValue_LAYOUT.varHandle() }

var NSAccessibilityUnknownMarkerTypeValue: MemorySegment
    get() = NSAccessibilityUnknownMarkerTypeValue_VH.get(NSAccessibilityUnknownMarkerTypeValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnknownMarkerTypeValue_VH.set(NSAccessibilityUnknownMarkerTypeValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityInchesUnitValue typedef const NSAccessibilityRulerUnitValue = (Void)*
 */
private val NSAccessibilityInchesUnitValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityInchesUnitValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityInchesUnitValue").orElseThrow() }
private val NSAccessibilityInchesUnitValue_VH: VarHandle by lazy { NSAccessibilityInchesUnitValue_LAYOUT.varHandle() }

var NSAccessibilityInchesUnitValue: MemorySegment
    get() = NSAccessibilityInchesUnitValue_VH.get(NSAccessibilityInchesUnitValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityInchesUnitValue_VH.set(NSAccessibilityInchesUnitValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCentimetersUnitValue typedef const NSAccessibilityRulerUnitValue = (Void)*
 */
private val NSAccessibilityCentimetersUnitValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCentimetersUnitValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCentimetersUnitValue").orElseThrow() }
private val NSAccessibilityCentimetersUnitValue_VH: VarHandle by lazy { NSAccessibilityCentimetersUnitValue_LAYOUT.varHandle() }

var NSAccessibilityCentimetersUnitValue: MemorySegment
    get() = NSAccessibilityCentimetersUnitValue_VH.get(NSAccessibilityCentimetersUnitValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCentimetersUnitValue_VH.set(NSAccessibilityCentimetersUnitValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPointsUnitValue typedef const NSAccessibilityRulerUnitValue = (Void)*
 */
private val NSAccessibilityPointsUnitValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPointsUnitValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPointsUnitValue").orElseThrow() }
private val NSAccessibilityPointsUnitValue_VH: VarHandle by lazy { NSAccessibilityPointsUnitValue_LAYOUT.varHandle() }

var NSAccessibilityPointsUnitValue: MemorySegment
    get() = NSAccessibilityPointsUnitValue_VH.get(NSAccessibilityPointsUnitValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPointsUnitValue_VH.set(NSAccessibilityPointsUnitValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPicasUnitValue typedef const NSAccessibilityRulerUnitValue = (Void)*
 */
private val NSAccessibilityPicasUnitValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPicasUnitValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPicasUnitValue").orElseThrow() }
private val NSAccessibilityPicasUnitValue_VH: VarHandle by lazy { NSAccessibilityPicasUnitValue_LAYOUT.varHandle() }

var NSAccessibilityPicasUnitValue: MemorySegment
    get() = NSAccessibilityPicasUnitValue_VH.get(NSAccessibilityPicasUnitValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPicasUnitValue_VH.set(NSAccessibilityPicasUnitValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnknownUnitValue typedef const NSAccessibilityRulerUnitValue = (Void)*
 */
private val NSAccessibilityUnknownUnitValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnknownUnitValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnknownUnitValue").orElseThrow() }
private val NSAccessibilityUnknownUnitValue_VH: VarHandle by lazy { NSAccessibilityUnknownUnitValue_LAYOUT.varHandle() }

var NSAccessibilityUnknownUnitValue: MemorySegment
    get() = NSAccessibilityUnknownUnitValue_VH.get(NSAccessibilityUnknownUnitValue_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnknownUnitValue_VH.set(NSAccessibilityUnknownUnitValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPressAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityPressAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPressAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPressAction").orElseThrow() }
private val NSAccessibilityPressAction_VH: VarHandle by lazy { NSAccessibilityPressAction_LAYOUT.varHandle() }

var NSAccessibilityPressAction: MemorySegment
    get() = NSAccessibilityPressAction_VH.get(NSAccessibilityPressAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPressAction_VH.set(NSAccessibilityPressAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityIncrementAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityIncrementAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityIncrementAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityIncrementAction").orElseThrow() }
private val NSAccessibilityIncrementAction_VH: VarHandle by lazy { NSAccessibilityIncrementAction_LAYOUT.varHandle() }

var NSAccessibilityIncrementAction: MemorySegment
    get() = NSAccessibilityIncrementAction_VH.get(NSAccessibilityIncrementAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityIncrementAction_VH.set(NSAccessibilityIncrementAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDecrementAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityDecrementAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDecrementAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDecrementAction").orElseThrow() }
private val NSAccessibilityDecrementAction_VH: VarHandle by lazy { NSAccessibilityDecrementAction_LAYOUT.varHandle() }

var NSAccessibilityDecrementAction: MemorySegment
    get() = NSAccessibilityDecrementAction_VH.get(NSAccessibilityDecrementAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDecrementAction_VH.set(NSAccessibilityDecrementAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityConfirmAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityConfirmAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityConfirmAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityConfirmAction").orElseThrow() }
private val NSAccessibilityConfirmAction_VH: VarHandle by lazy { NSAccessibilityConfirmAction_LAYOUT.varHandle() }

var NSAccessibilityConfirmAction: MemorySegment
    get() = NSAccessibilityConfirmAction_VH.get(NSAccessibilityConfirmAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityConfirmAction_VH.set(NSAccessibilityConfirmAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPickAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityPickAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPickAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPickAction").orElseThrow() }
private val NSAccessibilityPickAction_VH: VarHandle by lazy { NSAccessibilityPickAction_LAYOUT.varHandle() }

var NSAccessibilityPickAction: MemorySegment
    get() = NSAccessibilityPickAction_VH.get(NSAccessibilityPickAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPickAction_VH.set(NSAccessibilityPickAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCancelAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityCancelAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCancelAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCancelAction").orElseThrow() }
private val NSAccessibilityCancelAction_VH: VarHandle by lazy { NSAccessibilityCancelAction_LAYOUT.varHandle() }

var NSAccessibilityCancelAction: MemorySegment
    get() = NSAccessibilityCancelAction_VH.get(NSAccessibilityCancelAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCancelAction_VH.set(NSAccessibilityCancelAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRaiseAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityRaiseAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRaiseAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRaiseAction").orElseThrow() }
private val NSAccessibilityRaiseAction_VH: VarHandle by lazy { NSAccessibilityRaiseAction_LAYOUT.varHandle() }

var NSAccessibilityRaiseAction: MemorySegment
    get() = NSAccessibilityRaiseAction_VH.get(NSAccessibilityRaiseAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRaiseAction_VH.set(NSAccessibilityRaiseAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityShowMenuAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityShowMenuAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityShowMenuAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityShowMenuAction").orElseThrow() }
private val NSAccessibilityShowMenuAction_VH: VarHandle by lazy { NSAccessibilityShowMenuAction_LAYOUT.varHandle() }

var NSAccessibilityShowMenuAction: MemorySegment
    get() = NSAccessibilityShowMenuAction_VH.get(NSAccessibilityShowMenuAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityShowMenuAction_VH.set(NSAccessibilityShowMenuAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDeleteAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityDeleteAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDeleteAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDeleteAction").orElseThrow() }
private val NSAccessibilityDeleteAction_VH: VarHandle by lazy { NSAccessibilityDeleteAction_LAYOUT.varHandle() }

var NSAccessibilityDeleteAction: MemorySegment
    get() = NSAccessibilityDeleteAction_VH.get(NSAccessibilityDeleteAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDeleteAction_VH.set(NSAccessibilityDeleteAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityScrollToVisibleAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityScrollToVisibleAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityScrollToVisibleAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityScrollToVisibleAction").orElseThrow() }
private val NSAccessibilityScrollToVisibleAction_VH: VarHandle by lazy { NSAccessibilityScrollToVisibleAction_LAYOUT.varHandle() }

var NSAccessibilityScrollToVisibleAction: MemorySegment
    get() = NSAccessibilityScrollToVisibleAction_VH.get(NSAccessibilityScrollToVisibleAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityScrollToVisibleAction_VH.set(NSAccessibilityScrollToVisibleAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityShowAlternateUIAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityShowAlternateUIAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityShowAlternateUIAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityShowAlternateUIAction").orElseThrow() }
private val NSAccessibilityShowAlternateUIAction_VH: VarHandle by lazy { NSAccessibilityShowAlternateUIAction_LAYOUT.varHandle() }

var NSAccessibilityShowAlternateUIAction: MemorySegment
    get() = NSAccessibilityShowAlternateUIAction_VH.get(NSAccessibilityShowAlternateUIAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityShowAlternateUIAction_VH.set(NSAccessibilityShowAlternateUIAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityShowDefaultUIAction typedef const NSAccessibilityActionName = (Void)*
 */
private val NSAccessibilityShowDefaultUIAction_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityShowDefaultUIAction_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityShowDefaultUIAction").orElseThrow() }
private val NSAccessibilityShowDefaultUIAction_VH: VarHandle by lazy { NSAccessibilityShowDefaultUIAction_LAYOUT.varHandle() }

var NSAccessibilityShowDefaultUIAction: MemorySegment
    get() = NSAccessibilityShowDefaultUIAction_VH.get(NSAccessibilityShowDefaultUIAction_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityShowDefaultUIAction_VH.set(NSAccessibilityShowDefaultUIAction_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMainWindowChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityMainWindowChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMainWindowChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMainWindowChangedNotification").orElseThrow() }
private val NSAccessibilityMainWindowChangedNotification_VH: VarHandle by lazy { NSAccessibilityMainWindowChangedNotification_LAYOUT.varHandle() }

var NSAccessibilityMainWindowChangedNotification: MemorySegment
    get() = NSAccessibilityMainWindowChangedNotification_VH.get(NSAccessibilityMainWindowChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMainWindowChangedNotification_VH.set(NSAccessibilityMainWindowChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFocusedWindowChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityFocusedWindowChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFocusedWindowChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFocusedWindowChangedNotification").orElseThrow() }
private val NSAccessibilityFocusedWindowChangedNotification_VH: VarHandle by lazy { NSAccessibilityFocusedWindowChangedNotification_LAYOUT.varHandle() }

var NSAccessibilityFocusedWindowChangedNotification: MemorySegment
    get() = NSAccessibilityFocusedWindowChangedNotification_VH.get(NSAccessibilityFocusedWindowChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFocusedWindowChangedNotification_VH.set(NSAccessibilityFocusedWindowChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFocusedUIElementChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityFocusedUIElementChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFocusedUIElementChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFocusedUIElementChangedNotification").orElseThrow() }
private val NSAccessibilityFocusedUIElementChangedNotification_VH: VarHandle by lazy { NSAccessibilityFocusedUIElementChangedNotification_LAYOUT.varHandle() }

var NSAccessibilityFocusedUIElementChangedNotification: MemorySegment
    get() = NSAccessibilityFocusedUIElementChangedNotification_VH.get(NSAccessibilityFocusedUIElementChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFocusedUIElementChangedNotification_VH.set(NSAccessibilityFocusedUIElementChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityApplicationActivatedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityApplicationActivatedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityApplicationActivatedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityApplicationActivatedNotification").orElseThrow() }
private val NSAccessibilityApplicationActivatedNotification_VH: VarHandle by lazy { NSAccessibilityApplicationActivatedNotification_LAYOUT.varHandle() }

var NSAccessibilityApplicationActivatedNotification: MemorySegment
    get() = NSAccessibilityApplicationActivatedNotification_VH.get(NSAccessibilityApplicationActivatedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityApplicationActivatedNotification_VH.set(NSAccessibilityApplicationActivatedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityApplicationDeactivatedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityApplicationDeactivatedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityApplicationDeactivatedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityApplicationDeactivatedNotification").orElseThrow() }
private val NSAccessibilityApplicationDeactivatedNotification_VH: VarHandle by lazy { NSAccessibilityApplicationDeactivatedNotification_LAYOUT.varHandle() }

var NSAccessibilityApplicationDeactivatedNotification: MemorySegment
    get() = NSAccessibilityApplicationDeactivatedNotification_VH.get(NSAccessibilityApplicationDeactivatedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityApplicationDeactivatedNotification_VH.set(NSAccessibilityApplicationDeactivatedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityApplicationHiddenNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityApplicationHiddenNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityApplicationHiddenNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityApplicationHiddenNotification").orElseThrow() }
private val NSAccessibilityApplicationHiddenNotification_VH: VarHandle by lazy { NSAccessibilityApplicationHiddenNotification_LAYOUT.varHandle() }

var NSAccessibilityApplicationHiddenNotification: MemorySegment
    get() = NSAccessibilityApplicationHiddenNotification_VH.get(NSAccessibilityApplicationHiddenNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityApplicationHiddenNotification_VH.set(NSAccessibilityApplicationHiddenNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityApplicationShownNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityApplicationShownNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityApplicationShownNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityApplicationShownNotification").orElseThrow() }
private val NSAccessibilityApplicationShownNotification_VH: VarHandle by lazy { NSAccessibilityApplicationShownNotification_LAYOUT.varHandle() }

var NSAccessibilityApplicationShownNotification: MemorySegment
    get() = NSAccessibilityApplicationShownNotification_VH.get(NSAccessibilityApplicationShownNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityApplicationShownNotification_VH.set(NSAccessibilityApplicationShownNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWindowCreatedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityWindowCreatedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWindowCreatedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWindowCreatedNotification").orElseThrow() }
private val NSAccessibilityWindowCreatedNotification_VH: VarHandle by lazy { NSAccessibilityWindowCreatedNotification_LAYOUT.varHandle() }

var NSAccessibilityWindowCreatedNotification: MemorySegment
    get() = NSAccessibilityWindowCreatedNotification_VH.get(NSAccessibilityWindowCreatedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWindowCreatedNotification_VH.set(NSAccessibilityWindowCreatedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWindowMovedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityWindowMovedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWindowMovedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWindowMovedNotification").orElseThrow() }
private val NSAccessibilityWindowMovedNotification_VH: VarHandle by lazy { NSAccessibilityWindowMovedNotification_LAYOUT.varHandle() }

var NSAccessibilityWindowMovedNotification: MemorySegment
    get() = NSAccessibilityWindowMovedNotification_VH.get(NSAccessibilityWindowMovedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWindowMovedNotification_VH.set(NSAccessibilityWindowMovedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWindowResizedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityWindowResizedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWindowResizedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWindowResizedNotification").orElseThrow() }
private val NSAccessibilityWindowResizedNotification_VH: VarHandle by lazy { NSAccessibilityWindowResizedNotification_LAYOUT.varHandle() }

var NSAccessibilityWindowResizedNotification: MemorySegment
    get() = NSAccessibilityWindowResizedNotification_VH.get(NSAccessibilityWindowResizedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWindowResizedNotification_VH.set(NSAccessibilityWindowResizedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWindowMiniaturizedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityWindowMiniaturizedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWindowMiniaturizedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWindowMiniaturizedNotification").orElseThrow() }
private val NSAccessibilityWindowMiniaturizedNotification_VH: VarHandle by lazy { NSAccessibilityWindowMiniaturizedNotification_LAYOUT.varHandle() }

var NSAccessibilityWindowMiniaturizedNotification: MemorySegment
    get() = NSAccessibilityWindowMiniaturizedNotification_VH.get(NSAccessibilityWindowMiniaturizedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWindowMiniaturizedNotification_VH.set(NSAccessibilityWindowMiniaturizedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWindowDeminiaturizedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityWindowDeminiaturizedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWindowDeminiaturizedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWindowDeminiaturizedNotification").orElseThrow() }
private val NSAccessibilityWindowDeminiaturizedNotification_VH: VarHandle by lazy { NSAccessibilityWindowDeminiaturizedNotification_LAYOUT.varHandle() }

var NSAccessibilityWindowDeminiaturizedNotification: MemorySegment
    get() = NSAccessibilityWindowDeminiaturizedNotification_VH.get(NSAccessibilityWindowDeminiaturizedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWindowDeminiaturizedNotification_VH.set(NSAccessibilityWindowDeminiaturizedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDrawerCreatedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityDrawerCreatedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDrawerCreatedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDrawerCreatedNotification").orElseThrow() }
private val NSAccessibilityDrawerCreatedNotification_VH: VarHandle by lazy { NSAccessibilityDrawerCreatedNotification_LAYOUT.varHandle() }

var NSAccessibilityDrawerCreatedNotification: MemorySegment
    get() = NSAccessibilityDrawerCreatedNotification_VH.get(NSAccessibilityDrawerCreatedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDrawerCreatedNotification_VH.set(NSAccessibilityDrawerCreatedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySheetCreatedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilitySheetCreatedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySheetCreatedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySheetCreatedNotification").orElseThrow() }
private val NSAccessibilitySheetCreatedNotification_VH: VarHandle by lazy { NSAccessibilitySheetCreatedNotification_LAYOUT.varHandle() }

var NSAccessibilitySheetCreatedNotification: MemorySegment
    get() = NSAccessibilitySheetCreatedNotification_VH.get(NSAccessibilitySheetCreatedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySheetCreatedNotification_VH.set(NSAccessibilitySheetCreatedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUIElementDestroyedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityUIElementDestroyedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUIElementDestroyedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUIElementDestroyedNotification").orElseThrow() }
private val NSAccessibilityUIElementDestroyedNotification_VH: VarHandle by lazy { NSAccessibilityUIElementDestroyedNotification_LAYOUT.varHandle() }

var NSAccessibilityUIElementDestroyedNotification: MemorySegment
    get() = NSAccessibilityUIElementDestroyedNotification_VH.get(NSAccessibilityUIElementDestroyedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUIElementDestroyedNotification_VH.set(NSAccessibilityUIElementDestroyedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityValueChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityValueChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityValueChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityValueChangedNotification").orElseThrow() }
private val NSAccessibilityValueChangedNotification_VH: VarHandle by lazy { NSAccessibilityValueChangedNotification_LAYOUT.varHandle() }

var NSAccessibilityValueChangedNotification: MemorySegment
    get() = NSAccessibilityValueChangedNotification_VH.get(NSAccessibilityValueChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityValueChangedNotification_VH.set(NSAccessibilityValueChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTitleChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityTitleChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTitleChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTitleChangedNotification").orElseThrow() }
private val NSAccessibilityTitleChangedNotification_VH: VarHandle by lazy { NSAccessibilityTitleChangedNotification_LAYOUT.varHandle() }

var NSAccessibilityTitleChangedNotification: MemorySegment
    get() = NSAccessibilityTitleChangedNotification_VH.get(NSAccessibilityTitleChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTitleChangedNotification_VH.set(NSAccessibilityTitleChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityResizedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityResizedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityResizedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityResizedNotification").orElseThrow() }
private val NSAccessibilityResizedNotification_VH: VarHandle by lazy { NSAccessibilityResizedNotification_LAYOUT.varHandle() }

var NSAccessibilityResizedNotification: MemorySegment
    get() = NSAccessibilityResizedNotification_VH.get(NSAccessibilityResizedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityResizedNotification_VH.set(NSAccessibilityResizedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMovedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityMovedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMovedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMovedNotification").orElseThrow() }
private val NSAccessibilityMovedNotification_VH: VarHandle by lazy { NSAccessibilityMovedNotification_LAYOUT.varHandle() }

var NSAccessibilityMovedNotification: MemorySegment
    get() = NSAccessibilityMovedNotification_VH.get(NSAccessibilityMovedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMovedNotification_VH.set(NSAccessibilityMovedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCreatedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityCreatedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCreatedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCreatedNotification").orElseThrow() }
private val NSAccessibilityCreatedNotification_VH: VarHandle by lazy { NSAccessibilityCreatedNotification_LAYOUT.varHandle() }

var NSAccessibilityCreatedNotification: MemorySegment
    get() = NSAccessibilityCreatedNotification_VH.get(NSAccessibilityCreatedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCreatedNotification_VH.set(NSAccessibilityCreatedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLayoutChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityLayoutChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLayoutChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLayoutChangedNotification").orElseThrow() }
private val NSAccessibilityLayoutChangedNotification_VH: VarHandle by lazy { NSAccessibilityLayoutChangedNotification_LAYOUT.varHandle() }

var NSAccessibilityLayoutChangedNotification: MemorySegment
    get() = NSAccessibilityLayoutChangedNotification_VH.get(NSAccessibilityLayoutChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLayoutChangedNotification_VH.set(NSAccessibilityLayoutChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHelpTagCreatedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityHelpTagCreatedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHelpTagCreatedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHelpTagCreatedNotification").orElseThrow() }
private val NSAccessibilityHelpTagCreatedNotification_VH: VarHandle by lazy { NSAccessibilityHelpTagCreatedNotification_LAYOUT.varHandle() }

var NSAccessibilityHelpTagCreatedNotification: MemorySegment
    get() = NSAccessibilityHelpTagCreatedNotification_VH.get(NSAccessibilityHelpTagCreatedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHelpTagCreatedNotification_VH.set(NSAccessibilityHelpTagCreatedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedTextChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilitySelectedTextChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedTextChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedTextChangedNotification").orElseThrow() }
private val NSAccessibilitySelectedTextChangedNotification_VH: VarHandle by lazy { NSAccessibilitySelectedTextChangedNotification_LAYOUT.varHandle() }

var NSAccessibilitySelectedTextChangedNotification: MemorySegment
    get() = NSAccessibilitySelectedTextChangedNotification_VH.get(NSAccessibilitySelectedTextChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedTextChangedNotification_VH.set(NSAccessibilitySelectedTextChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRowCountChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityRowCountChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRowCountChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRowCountChangedNotification").orElseThrow() }
private val NSAccessibilityRowCountChangedNotification_VH: VarHandle by lazy { NSAccessibilityRowCountChangedNotification_LAYOUT.varHandle() }

var NSAccessibilityRowCountChangedNotification: MemorySegment
    get() = NSAccessibilityRowCountChangedNotification_VH.get(NSAccessibilityRowCountChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRowCountChangedNotification_VH.set(NSAccessibilityRowCountChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedChildrenChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilitySelectedChildrenChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedChildrenChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedChildrenChangedNotification").orElseThrow() }
private val NSAccessibilitySelectedChildrenChangedNotification_VH: VarHandle by lazy { NSAccessibilitySelectedChildrenChangedNotification_LAYOUT.varHandle() }

var NSAccessibilitySelectedChildrenChangedNotification: MemorySegment
    get() = NSAccessibilitySelectedChildrenChangedNotification_VH.get(NSAccessibilitySelectedChildrenChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedChildrenChangedNotification_VH.set(NSAccessibilitySelectedChildrenChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedRowsChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilitySelectedRowsChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedRowsChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedRowsChangedNotification").orElseThrow() }
private val NSAccessibilitySelectedRowsChangedNotification_VH: VarHandle by lazy { NSAccessibilitySelectedRowsChangedNotification_LAYOUT.varHandle() }

var NSAccessibilitySelectedRowsChangedNotification: MemorySegment
    get() = NSAccessibilitySelectedRowsChangedNotification_VH.get(NSAccessibilitySelectedRowsChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedRowsChangedNotification_VH.set(NSAccessibilitySelectedRowsChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedColumnsChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilitySelectedColumnsChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedColumnsChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedColumnsChangedNotification").orElseThrow() }
private val NSAccessibilitySelectedColumnsChangedNotification_VH: VarHandle by lazy { NSAccessibilitySelectedColumnsChangedNotification_LAYOUT.varHandle() }

var NSAccessibilitySelectedColumnsChangedNotification: MemorySegment
    get() = NSAccessibilitySelectedColumnsChangedNotification_VH.get(NSAccessibilitySelectedColumnsChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedColumnsChangedNotification_VH.set(NSAccessibilitySelectedColumnsChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRowExpandedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityRowExpandedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRowExpandedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRowExpandedNotification").orElseThrow() }
private val NSAccessibilityRowExpandedNotification_VH: VarHandle by lazy { NSAccessibilityRowExpandedNotification_LAYOUT.varHandle() }

var NSAccessibilityRowExpandedNotification: MemorySegment
    get() = NSAccessibilityRowExpandedNotification_VH.get(NSAccessibilityRowExpandedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRowExpandedNotification_VH.set(NSAccessibilityRowExpandedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRowCollapsedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityRowCollapsedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRowCollapsedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRowCollapsedNotification").orElseThrow() }
private val NSAccessibilityRowCollapsedNotification_VH: VarHandle by lazy { NSAccessibilityRowCollapsedNotification_LAYOUT.varHandle() }

var NSAccessibilityRowCollapsedNotification: MemorySegment
    get() = NSAccessibilityRowCollapsedNotification_VH.get(NSAccessibilityRowCollapsedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRowCollapsedNotification_VH.set(NSAccessibilityRowCollapsedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAutocorrectionOccurredNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityAutocorrectionOccurredNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAutocorrectionOccurredNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAutocorrectionOccurredNotification").orElseThrow() }
private val NSAccessibilityAutocorrectionOccurredNotification_VH: VarHandle by lazy { NSAccessibilityAutocorrectionOccurredNotification_LAYOUT.varHandle() }

var NSAccessibilityAutocorrectionOccurredNotification: MemorySegment
    get() = NSAccessibilityAutocorrectionOccurredNotification_VH.get(NSAccessibilityAutocorrectionOccurredNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAutocorrectionOccurredNotification_VH.set(NSAccessibilityAutocorrectionOccurredNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextInputMarkingSessionBeganNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityTextInputMarkingSessionBeganNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextInputMarkingSessionBeganNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextInputMarkingSessionBeganNotification").orElseThrow() }
private val NSAccessibilityTextInputMarkingSessionBeganNotification_VH: VarHandle by lazy { NSAccessibilityTextInputMarkingSessionBeganNotification_LAYOUT.varHandle() }

var NSAccessibilityTextInputMarkingSessionBeganNotification: MemorySegment
    get() = NSAccessibilityTextInputMarkingSessionBeganNotification_VH.get(NSAccessibilityTextInputMarkingSessionBeganNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextInputMarkingSessionBeganNotification_VH.set(NSAccessibilityTextInputMarkingSessionBeganNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextInputMarkingSessionEndedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityTextInputMarkingSessionEndedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextInputMarkingSessionEndedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextInputMarkingSessionEndedNotification").orElseThrow() }
private val NSAccessibilityTextInputMarkingSessionEndedNotification_VH: VarHandle by lazy { NSAccessibilityTextInputMarkingSessionEndedNotification_LAYOUT.varHandle() }

var NSAccessibilityTextInputMarkingSessionEndedNotification: MemorySegment
    get() = NSAccessibilityTextInputMarkingSessionEndedNotification_VH.get(NSAccessibilityTextInputMarkingSessionEndedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextInputMarkingSessionEndedNotification_VH.set(NSAccessibilityTextInputMarkingSessionEndedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDraggingSourceDragBeganNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityDraggingSourceDragBeganNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDraggingSourceDragBeganNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDraggingSourceDragBeganNotification").orElseThrow() }
private val NSAccessibilityDraggingSourceDragBeganNotification_VH: VarHandle by lazy { NSAccessibilityDraggingSourceDragBeganNotification_LAYOUT.varHandle() }

var NSAccessibilityDraggingSourceDragBeganNotification: MemorySegment
    get() = NSAccessibilityDraggingSourceDragBeganNotification_VH.get(NSAccessibilityDraggingSourceDragBeganNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDraggingSourceDragBeganNotification_VH.set(NSAccessibilityDraggingSourceDragBeganNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDraggingSourceDragEndedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityDraggingSourceDragEndedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDraggingSourceDragEndedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDraggingSourceDragEndedNotification").orElseThrow() }
private val NSAccessibilityDraggingSourceDragEndedNotification_VH: VarHandle by lazy { NSAccessibilityDraggingSourceDragEndedNotification_LAYOUT.varHandle() }

var NSAccessibilityDraggingSourceDragEndedNotification: MemorySegment
    get() = NSAccessibilityDraggingSourceDragEndedNotification_VH.get(NSAccessibilityDraggingSourceDragEndedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDraggingSourceDragEndedNotification_VH.set(NSAccessibilityDraggingSourceDragEndedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDraggingDestinationDropAllowedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityDraggingDestinationDropAllowedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDraggingDestinationDropAllowedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDraggingDestinationDropAllowedNotification").orElseThrow() }
private val NSAccessibilityDraggingDestinationDropAllowedNotification_VH: VarHandle by lazy { NSAccessibilityDraggingDestinationDropAllowedNotification_LAYOUT.varHandle() }

var NSAccessibilityDraggingDestinationDropAllowedNotification: MemorySegment
    get() = NSAccessibilityDraggingDestinationDropAllowedNotification_VH.get(NSAccessibilityDraggingDestinationDropAllowedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDraggingDestinationDropAllowedNotification_VH.set(NSAccessibilityDraggingDestinationDropAllowedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDraggingDestinationDropNotAllowedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityDraggingDestinationDropNotAllowedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDraggingDestinationDropNotAllowedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDraggingDestinationDropNotAllowedNotification").orElseThrow() }
private val NSAccessibilityDraggingDestinationDropNotAllowedNotification_VH: VarHandle by lazy { NSAccessibilityDraggingDestinationDropNotAllowedNotification_LAYOUT.varHandle() }

var NSAccessibilityDraggingDestinationDropNotAllowedNotification: MemorySegment
    get() = NSAccessibilityDraggingDestinationDropNotAllowedNotification_VH.get(NSAccessibilityDraggingDestinationDropNotAllowedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDraggingDestinationDropNotAllowedNotification_VH.set(NSAccessibilityDraggingDestinationDropNotAllowedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDraggingDestinationDragAcceptedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityDraggingDestinationDragAcceptedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDraggingDestinationDragAcceptedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDraggingDestinationDragAcceptedNotification").orElseThrow() }
private val NSAccessibilityDraggingDestinationDragAcceptedNotification_VH: VarHandle by lazy { NSAccessibilityDraggingDestinationDragAcceptedNotification_LAYOUT.varHandle() }

var NSAccessibilityDraggingDestinationDragAcceptedNotification: MemorySegment
    get() = NSAccessibilityDraggingDestinationDragAcceptedNotification_VH.get(NSAccessibilityDraggingDestinationDragAcceptedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDraggingDestinationDragAcceptedNotification_VH.set(NSAccessibilityDraggingDestinationDragAcceptedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDraggingDestinationDragNotAcceptedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityDraggingDestinationDragNotAcceptedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDraggingDestinationDragNotAcceptedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDraggingDestinationDragNotAcceptedNotification").orElseThrow() }
private val NSAccessibilityDraggingDestinationDragNotAcceptedNotification_VH: VarHandle by lazy { NSAccessibilityDraggingDestinationDragNotAcceptedNotification_LAYOUT.varHandle() }

var NSAccessibilityDraggingDestinationDragNotAcceptedNotification: MemorySegment
    get() = NSAccessibilityDraggingDestinationDragNotAcceptedNotification_VH.get(NSAccessibilityDraggingDestinationDragNotAcceptedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDraggingDestinationDragNotAcceptedNotification_VH.set(NSAccessibilityDraggingDestinationDragNotAcceptedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedCellsChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilitySelectedCellsChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedCellsChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedCellsChangedNotification").orElseThrow() }
private val NSAccessibilitySelectedCellsChangedNotification_VH: VarHandle by lazy { NSAccessibilitySelectedCellsChangedNotification_LAYOUT.varHandle() }

var NSAccessibilitySelectedCellsChangedNotification: MemorySegment
    get() = NSAccessibilitySelectedCellsChangedNotification_VH.get(NSAccessibilitySelectedCellsChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedCellsChangedNotification_VH.set(NSAccessibilitySelectedCellsChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnitsChangedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityUnitsChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnitsChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnitsChangedNotification").orElseThrow() }
private val NSAccessibilityUnitsChangedNotification_VH: VarHandle by lazy { NSAccessibilityUnitsChangedNotification_LAYOUT.varHandle() }

var NSAccessibilityUnitsChangedNotification: MemorySegment
    get() = NSAccessibilityUnitsChangedNotification_VH.get(NSAccessibilityUnitsChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnitsChangedNotification_VH.set(NSAccessibilityUnitsChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySelectedChildrenMovedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilitySelectedChildrenMovedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySelectedChildrenMovedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySelectedChildrenMovedNotification").orElseThrow() }
private val NSAccessibilitySelectedChildrenMovedNotification_VH: VarHandle by lazy { NSAccessibilitySelectedChildrenMovedNotification_LAYOUT.varHandle() }

var NSAccessibilitySelectedChildrenMovedNotification: MemorySegment
    get() = NSAccessibilitySelectedChildrenMovedNotification_VH.get(NSAccessibilitySelectedChildrenMovedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySelectedChildrenMovedNotification_VH.set(NSAccessibilitySelectedChildrenMovedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAnnouncementRequestedNotification typedef const NSAccessibilityNotificationName = (Void)*
 */
private val NSAccessibilityAnnouncementRequestedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAnnouncementRequestedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAnnouncementRequestedNotification").orElseThrow() }
private val NSAccessibilityAnnouncementRequestedNotification_VH: VarHandle by lazy { NSAccessibilityAnnouncementRequestedNotification_LAYOUT.varHandle() }

var NSAccessibilityAnnouncementRequestedNotification: MemorySegment
    get() = NSAccessibilityAnnouncementRequestedNotification_VH.get(NSAccessibilityAnnouncementRequestedNotification_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAnnouncementRequestedNotification_VH.set(NSAccessibilityAnnouncementRequestedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnknownRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityUnknownRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnknownRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnknownRole").orElseThrow() }
private val NSAccessibilityUnknownRole_VH: VarHandle by lazy { NSAccessibilityUnknownRole_LAYOUT.varHandle() }

var NSAccessibilityUnknownRole: MemorySegment
    get() = NSAccessibilityUnknownRole_VH.get(NSAccessibilityUnknownRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnknownRole_VH.set(NSAccessibilityUnknownRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityButtonRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityButtonRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityButtonRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityButtonRole").orElseThrow() }
private val NSAccessibilityButtonRole_VH: VarHandle by lazy { NSAccessibilityButtonRole_LAYOUT.varHandle() }

var NSAccessibilityButtonRole: MemorySegment
    get() = NSAccessibilityButtonRole_VH.get(NSAccessibilityButtonRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityButtonRole_VH.set(NSAccessibilityButtonRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRadioButtonRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityRadioButtonRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRadioButtonRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRadioButtonRole").orElseThrow() }
private val NSAccessibilityRadioButtonRole_VH: VarHandle by lazy { NSAccessibilityRadioButtonRole_LAYOUT.varHandle() }

var NSAccessibilityRadioButtonRole: MemorySegment
    get() = NSAccessibilityRadioButtonRole_VH.get(NSAccessibilityRadioButtonRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRadioButtonRole_VH.set(NSAccessibilityRadioButtonRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCheckBoxRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityCheckBoxRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCheckBoxRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCheckBoxRole").orElseThrow() }
private val NSAccessibilityCheckBoxRole_VH: VarHandle by lazy { NSAccessibilityCheckBoxRole_LAYOUT.varHandle() }

var NSAccessibilityCheckBoxRole: MemorySegment
    get() = NSAccessibilityCheckBoxRole_VH.get(NSAccessibilityCheckBoxRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCheckBoxRole_VH.set(NSAccessibilityCheckBoxRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySliderRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilitySliderRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySliderRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySliderRole").orElseThrow() }
private val NSAccessibilitySliderRole_VH: VarHandle by lazy { NSAccessibilitySliderRole_LAYOUT.varHandle() }

var NSAccessibilitySliderRole: MemorySegment
    get() = NSAccessibilitySliderRole_VH.get(NSAccessibilitySliderRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySliderRole_VH.set(NSAccessibilitySliderRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTabGroupRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityTabGroupRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTabGroupRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTabGroupRole").orElseThrow() }
private val NSAccessibilityTabGroupRole_VH: VarHandle by lazy { NSAccessibilityTabGroupRole_LAYOUT.varHandle() }

var NSAccessibilityTabGroupRole: MemorySegment
    get() = NSAccessibilityTabGroupRole_VH.get(NSAccessibilityTabGroupRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTabGroupRole_VH.set(NSAccessibilityTabGroupRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextFieldRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityTextFieldRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextFieldRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextFieldRole").orElseThrow() }
private val NSAccessibilityTextFieldRole_VH: VarHandle by lazy { NSAccessibilityTextFieldRole_LAYOUT.varHandle() }

var NSAccessibilityTextFieldRole: MemorySegment
    get() = NSAccessibilityTextFieldRole_VH.get(NSAccessibilityTextFieldRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextFieldRole_VH.set(NSAccessibilityTextFieldRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityStaticTextRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityStaticTextRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityStaticTextRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityStaticTextRole").orElseThrow() }
private val NSAccessibilityStaticTextRole_VH: VarHandle by lazy { NSAccessibilityStaticTextRole_LAYOUT.varHandle() }

var NSAccessibilityStaticTextRole: MemorySegment
    get() = NSAccessibilityStaticTextRole_VH.get(NSAccessibilityStaticTextRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityStaticTextRole_VH.set(NSAccessibilityStaticTextRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextAreaRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityTextAreaRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextAreaRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextAreaRole").orElseThrow() }
private val NSAccessibilityTextAreaRole_VH: VarHandle by lazy { NSAccessibilityTextAreaRole_LAYOUT.varHandle() }

var NSAccessibilityTextAreaRole: MemorySegment
    get() = NSAccessibilityTextAreaRole_VH.get(NSAccessibilityTextAreaRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextAreaRole_VH.set(NSAccessibilityTextAreaRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityScrollAreaRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityScrollAreaRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityScrollAreaRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityScrollAreaRole").orElseThrow() }
private val NSAccessibilityScrollAreaRole_VH: VarHandle by lazy { NSAccessibilityScrollAreaRole_LAYOUT.varHandle() }

var NSAccessibilityScrollAreaRole: MemorySegment
    get() = NSAccessibilityScrollAreaRole_VH.get(NSAccessibilityScrollAreaRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityScrollAreaRole_VH.set(NSAccessibilityScrollAreaRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPopUpButtonRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityPopUpButtonRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPopUpButtonRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPopUpButtonRole").orElseThrow() }
private val NSAccessibilityPopUpButtonRole_VH: VarHandle by lazy { NSAccessibilityPopUpButtonRole_LAYOUT.varHandle() }

var NSAccessibilityPopUpButtonRole: MemorySegment
    get() = NSAccessibilityPopUpButtonRole_VH.get(NSAccessibilityPopUpButtonRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPopUpButtonRole_VH.set(NSAccessibilityPopUpButtonRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMenuButtonRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityMenuButtonRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMenuButtonRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMenuButtonRole").orElseThrow() }
private val NSAccessibilityMenuButtonRole_VH: VarHandle by lazy { NSAccessibilityMenuButtonRole_LAYOUT.varHandle() }

var NSAccessibilityMenuButtonRole: MemorySegment
    get() = NSAccessibilityMenuButtonRole_VH.get(NSAccessibilityMenuButtonRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMenuButtonRole_VH.set(NSAccessibilityMenuButtonRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTableRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityTableRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTableRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTableRole").orElseThrow() }
private val NSAccessibilityTableRole_VH: VarHandle by lazy { NSAccessibilityTableRole_LAYOUT.varHandle() }

var NSAccessibilityTableRole: MemorySegment
    get() = NSAccessibilityTableRole_VH.get(NSAccessibilityTableRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTableRole_VH.set(NSAccessibilityTableRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityApplicationRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityApplicationRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityApplicationRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityApplicationRole").orElseThrow() }
private val NSAccessibilityApplicationRole_VH: VarHandle by lazy { NSAccessibilityApplicationRole_LAYOUT.varHandle() }

var NSAccessibilityApplicationRole: MemorySegment
    get() = NSAccessibilityApplicationRole_VH.get(NSAccessibilityApplicationRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityApplicationRole_VH.set(NSAccessibilityApplicationRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityGroupRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityGroupRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityGroupRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityGroupRole").orElseThrow() }
private val NSAccessibilityGroupRole_VH: VarHandle by lazy { NSAccessibilityGroupRole_LAYOUT.varHandle() }

var NSAccessibilityGroupRole: MemorySegment
    get() = NSAccessibilityGroupRole_VH.get(NSAccessibilityGroupRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityGroupRole_VH.set(NSAccessibilityGroupRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRadioGroupRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityRadioGroupRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRadioGroupRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRadioGroupRole").orElseThrow() }
private val NSAccessibilityRadioGroupRole_VH: VarHandle by lazy { NSAccessibilityRadioGroupRole_LAYOUT.varHandle() }

var NSAccessibilityRadioGroupRole: MemorySegment
    get() = NSAccessibilityRadioGroupRole_VH.get(NSAccessibilityRadioGroupRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRadioGroupRole_VH.set(NSAccessibilityRadioGroupRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityListRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityListRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityListRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityListRole").orElseThrow() }
private val NSAccessibilityListRole_VH: VarHandle by lazy { NSAccessibilityListRole_LAYOUT.varHandle() }

var NSAccessibilityListRole: MemorySegment
    get() = NSAccessibilityListRole_VH.get(NSAccessibilityListRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityListRole_VH.set(NSAccessibilityListRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityScrollBarRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityScrollBarRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityScrollBarRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityScrollBarRole").orElseThrow() }
private val NSAccessibilityScrollBarRole_VH: VarHandle by lazy { NSAccessibilityScrollBarRole_LAYOUT.varHandle() }

var NSAccessibilityScrollBarRole: MemorySegment
    get() = NSAccessibilityScrollBarRole_VH.get(NSAccessibilityScrollBarRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityScrollBarRole_VH.set(NSAccessibilityScrollBarRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityValueIndicatorRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityValueIndicatorRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityValueIndicatorRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityValueIndicatorRole").orElseThrow() }
private val NSAccessibilityValueIndicatorRole_VH: VarHandle by lazy { NSAccessibilityValueIndicatorRole_LAYOUT.varHandle() }

var NSAccessibilityValueIndicatorRole: MemorySegment
    get() = NSAccessibilityValueIndicatorRole_VH.get(NSAccessibilityValueIndicatorRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityValueIndicatorRole_VH.set(NSAccessibilityValueIndicatorRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityImageRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityImageRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityImageRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityImageRole").orElseThrow() }
private val NSAccessibilityImageRole_VH: VarHandle by lazy { NSAccessibilityImageRole_LAYOUT.varHandle() }

var NSAccessibilityImageRole: MemorySegment
    get() = NSAccessibilityImageRole_VH.get(NSAccessibilityImageRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityImageRole_VH.set(NSAccessibilityImageRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMenuBarRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityMenuBarRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMenuBarRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMenuBarRole").orElseThrow() }
private val NSAccessibilityMenuBarRole_VH: VarHandle by lazy { NSAccessibilityMenuBarRole_LAYOUT.varHandle() }

var NSAccessibilityMenuBarRole: MemorySegment
    get() = NSAccessibilityMenuBarRole_VH.get(NSAccessibilityMenuBarRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMenuBarRole_VH.set(NSAccessibilityMenuBarRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMenuBarItemRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityMenuBarItemRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMenuBarItemRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMenuBarItemRole").orElseThrow() }
private val NSAccessibilityMenuBarItemRole_VH: VarHandle by lazy { NSAccessibilityMenuBarItemRole_LAYOUT.varHandle() }

var NSAccessibilityMenuBarItemRole: MemorySegment
    get() = NSAccessibilityMenuBarItemRole_VH.get(NSAccessibilityMenuBarItemRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMenuBarItemRole_VH.set(NSAccessibilityMenuBarItemRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMenuRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityMenuRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMenuRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMenuRole").orElseThrow() }
private val NSAccessibilityMenuRole_VH: VarHandle by lazy { NSAccessibilityMenuRole_LAYOUT.varHandle() }

var NSAccessibilityMenuRole: MemorySegment
    get() = NSAccessibilityMenuRole_VH.get(NSAccessibilityMenuRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMenuRole_VH.set(NSAccessibilityMenuRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMenuItemRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityMenuItemRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMenuItemRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMenuItemRole").orElseThrow() }
private val NSAccessibilityMenuItemRole_VH: VarHandle by lazy { NSAccessibilityMenuItemRole_LAYOUT.varHandle() }

var NSAccessibilityMenuItemRole: MemorySegment
    get() = NSAccessibilityMenuItemRole_VH.get(NSAccessibilityMenuItemRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMenuItemRole_VH.set(NSAccessibilityMenuItemRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityColumnRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityColumnRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityColumnRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityColumnRole").orElseThrow() }
private val NSAccessibilityColumnRole_VH: VarHandle by lazy { NSAccessibilityColumnRole_LAYOUT.varHandle() }

var NSAccessibilityColumnRole: MemorySegment
    get() = NSAccessibilityColumnRole_VH.get(NSAccessibilityColumnRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityColumnRole_VH.set(NSAccessibilityColumnRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRowRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityRowRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRowRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRowRole").orElseThrow() }
private val NSAccessibilityRowRole_VH: VarHandle by lazy { NSAccessibilityRowRole_LAYOUT.varHandle() }

var NSAccessibilityRowRole: MemorySegment
    get() = NSAccessibilityRowRole_VH.get(NSAccessibilityRowRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRowRole_VH.set(NSAccessibilityRowRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityToolbarRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityToolbarRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityToolbarRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityToolbarRole").orElseThrow() }
private val NSAccessibilityToolbarRole_VH: VarHandle by lazy { NSAccessibilityToolbarRole_LAYOUT.varHandle() }

var NSAccessibilityToolbarRole: MemorySegment
    get() = NSAccessibilityToolbarRole_VH.get(NSAccessibilityToolbarRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityToolbarRole_VH.set(NSAccessibilityToolbarRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityBusyIndicatorRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityBusyIndicatorRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityBusyIndicatorRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityBusyIndicatorRole").orElseThrow() }
private val NSAccessibilityBusyIndicatorRole_VH: VarHandle by lazy { NSAccessibilityBusyIndicatorRole_LAYOUT.varHandle() }

var NSAccessibilityBusyIndicatorRole: MemorySegment
    get() = NSAccessibilityBusyIndicatorRole_VH.get(NSAccessibilityBusyIndicatorRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityBusyIndicatorRole_VH.set(NSAccessibilityBusyIndicatorRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityProgressIndicatorRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityProgressIndicatorRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityProgressIndicatorRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityProgressIndicatorRole").orElseThrow() }
private val NSAccessibilityProgressIndicatorRole_VH: VarHandle by lazy { NSAccessibilityProgressIndicatorRole_LAYOUT.varHandle() }

var NSAccessibilityProgressIndicatorRole: MemorySegment
    get() = NSAccessibilityProgressIndicatorRole_VH.get(NSAccessibilityProgressIndicatorRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityProgressIndicatorRole_VH.set(NSAccessibilityProgressIndicatorRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWindowRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityWindowRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWindowRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWindowRole").orElseThrow() }
private val NSAccessibilityWindowRole_VH: VarHandle by lazy { NSAccessibilityWindowRole_LAYOUT.varHandle() }

var NSAccessibilityWindowRole: MemorySegment
    get() = NSAccessibilityWindowRole_VH.get(NSAccessibilityWindowRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWindowRole_VH.set(NSAccessibilityWindowRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDrawerRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityDrawerRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDrawerRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDrawerRole").orElseThrow() }
private val NSAccessibilityDrawerRole_VH: VarHandle by lazy { NSAccessibilityDrawerRole_LAYOUT.varHandle() }

var NSAccessibilityDrawerRole: MemorySegment
    get() = NSAccessibilityDrawerRole_VH.get(NSAccessibilityDrawerRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDrawerRole_VH.set(NSAccessibilityDrawerRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySystemWideRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilitySystemWideRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySystemWideRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySystemWideRole").orElseThrow() }
private val NSAccessibilitySystemWideRole_VH: VarHandle by lazy { NSAccessibilitySystemWideRole_LAYOUT.varHandle() }

var NSAccessibilitySystemWideRole: MemorySegment
    get() = NSAccessibilitySystemWideRole_VH.get(NSAccessibilitySystemWideRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySystemWideRole_VH.set(NSAccessibilitySystemWideRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityOutlineRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityOutlineRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityOutlineRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityOutlineRole").orElseThrow() }
private val NSAccessibilityOutlineRole_VH: VarHandle by lazy { NSAccessibilityOutlineRole_LAYOUT.varHandle() }

var NSAccessibilityOutlineRole: MemorySegment
    get() = NSAccessibilityOutlineRole_VH.get(NSAccessibilityOutlineRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityOutlineRole_VH.set(NSAccessibilityOutlineRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityIncrementorRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityIncrementorRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityIncrementorRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityIncrementorRole").orElseThrow() }
private val NSAccessibilityIncrementorRole_VH: VarHandle by lazy { NSAccessibilityIncrementorRole_LAYOUT.varHandle() }

var NSAccessibilityIncrementorRole: MemorySegment
    get() = NSAccessibilityIncrementorRole_VH.get(NSAccessibilityIncrementorRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityIncrementorRole_VH.set(NSAccessibilityIncrementorRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityBrowserRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityBrowserRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityBrowserRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityBrowserRole").orElseThrow() }
private val NSAccessibilityBrowserRole_VH: VarHandle by lazy { NSAccessibilityBrowserRole_LAYOUT.varHandle() }

var NSAccessibilityBrowserRole: MemorySegment
    get() = NSAccessibilityBrowserRole_VH.get(NSAccessibilityBrowserRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityBrowserRole_VH.set(NSAccessibilityBrowserRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityComboBoxRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityComboBoxRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityComboBoxRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityComboBoxRole").orElseThrow() }
private val NSAccessibilityComboBoxRole_VH: VarHandle by lazy { NSAccessibilityComboBoxRole_LAYOUT.varHandle() }

var NSAccessibilityComboBoxRole: MemorySegment
    get() = NSAccessibilityComboBoxRole_VH.get(NSAccessibilityComboBoxRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityComboBoxRole_VH.set(NSAccessibilityComboBoxRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySplitGroupRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilitySplitGroupRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySplitGroupRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySplitGroupRole").orElseThrow() }
private val NSAccessibilitySplitGroupRole_VH: VarHandle by lazy { NSAccessibilitySplitGroupRole_LAYOUT.varHandle() }

var NSAccessibilitySplitGroupRole: MemorySegment
    get() = NSAccessibilitySplitGroupRole_VH.get(NSAccessibilitySplitGroupRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySplitGroupRole_VH.set(NSAccessibilitySplitGroupRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySplitterRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilitySplitterRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySplitterRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySplitterRole").orElseThrow() }
private val NSAccessibilitySplitterRole_VH: VarHandle by lazy { NSAccessibilitySplitterRole_LAYOUT.varHandle() }

var NSAccessibilitySplitterRole: MemorySegment
    get() = NSAccessibilitySplitterRole_VH.get(NSAccessibilitySplitterRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySplitterRole_VH.set(NSAccessibilitySplitterRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityColorWellRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityColorWellRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityColorWellRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityColorWellRole").orElseThrow() }
private val NSAccessibilityColorWellRole_VH: VarHandle by lazy { NSAccessibilityColorWellRole_LAYOUT.varHandle() }

var NSAccessibilityColorWellRole: MemorySegment
    get() = NSAccessibilityColorWellRole_VH.get(NSAccessibilityColorWellRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityColorWellRole_VH.set(NSAccessibilityColorWellRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityGrowAreaRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityGrowAreaRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityGrowAreaRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityGrowAreaRole").orElseThrow() }
private val NSAccessibilityGrowAreaRole_VH: VarHandle by lazy { NSAccessibilityGrowAreaRole_LAYOUT.varHandle() }

var NSAccessibilityGrowAreaRole: MemorySegment
    get() = NSAccessibilityGrowAreaRole_VH.get(NSAccessibilityGrowAreaRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityGrowAreaRole_VH.set(NSAccessibilityGrowAreaRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySheetRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilitySheetRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySheetRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySheetRole").orElseThrow() }
private val NSAccessibilitySheetRole_VH: VarHandle by lazy { NSAccessibilitySheetRole_LAYOUT.varHandle() }

var NSAccessibilitySheetRole: MemorySegment
    get() = NSAccessibilitySheetRole_VH.get(NSAccessibilitySheetRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySheetRole_VH.set(NSAccessibilitySheetRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHelpTagRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityHelpTagRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHelpTagRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHelpTagRole").orElseThrow() }
private val NSAccessibilityHelpTagRole_VH: VarHandle by lazy { NSAccessibilityHelpTagRole_LAYOUT.varHandle() }

var NSAccessibilityHelpTagRole: MemorySegment
    get() = NSAccessibilityHelpTagRole_VH.get(NSAccessibilityHelpTagRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHelpTagRole_VH.set(NSAccessibilityHelpTagRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMatteRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityMatteRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMatteRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMatteRole").orElseThrow() }
private val NSAccessibilityMatteRole_VH: VarHandle by lazy { NSAccessibilityMatteRole_LAYOUT.varHandle() }

var NSAccessibilityMatteRole: MemorySegment
    get() = NSAccessibilityMatteRole_VH.get(NSAccessibilityMatteRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMatteRole_VH.set(NSAccessibilityMatteRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRulerRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityRulerRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRulerRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRulerRole").orElseThrow() }
private val NSAccessibilityRulerRole_VH: VarHandle by lazy { NSAccessibilityRulerRole_LAYOUT.varHandle() }

var NSAccessibilityRulerRole: MemorySegment
    get() = NSAccessibilityRulerRole_VH.get(NSAccessibilityRulerRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRulerRole_VH.set(NSAccessibilityRulerRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRulerMarkerRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityRulerMarkerRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRulerMarkerRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRulerMarkerRole").orElseThrow() }
private val NSAccessibilityRulerMarkerRole_VH: VarHandle by lazy { NSAccessibilityRulerMarkerRole_LAYOUT.varHandle() }

var NSAccessibilityRulerMarkerRole: MemorySegment
    get() = NSAccessibilityRulerMarkerRole_VH.get(NSAccessibilityRulerMarkerRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRulerMarkerRole_VH.set(NSAccessibilityRulerMarkerRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLinkRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityLinkRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLinkRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLinkRole").orElseThrow() }
private val NSAccessibilityLinkRole_VH: VarHandle by lazy { NSAccessibilityLinkRole_LAYOUT.varHandle() }

var NSAccessibilityLinkRole: MemorySegment
    get() = NSAccessibilityLinkRole_VH.get(NSAccessibilityLinkRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLinkRole_VH.set(NSAccessibilityLinkRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDisclosureTriangleRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityDisclosureTriangleRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDisclosureTriangleRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDisclosureTriangleRole").orElseThrow() }
private val NSAccessibilityDisclosureTriangleRole_VH: VarHandle by lazy { NSAccessibilityDisclosureTriangleRole_LAYOUT.varHandle() }

var NSAccessibilityDisclosureTriangleRole: MemorySegment
    get() = NSAccessibilityDisclosureTriangleRole_VH.get(NSAccessibilityDisclosureTriangleRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDisclosureTriangleRole_VH.set(NSAccessibilityDisclosureTriangleRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityGridRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityGridRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityGridRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityGridRole").orElseThrow() }
private val NSAccessibilityGridRole_VH: VarHandle by lazy { NSAccessibilityGridRole_LAYOUT.varHandle() }

var NSAccessibilityGridRole: MemorySegment
    get() = NSAccessibilityGridRole_VH.get(NSAccessibilityGridRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityGridRole_VH.set(NSAccessibilityGridRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRelevanceIndicatorRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityRelevanceIndicatorRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRelevanceIndicatorRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRelevanceIndicatorRole").orElseThrow() }
private val NSAccessibilityRelevanceIndicatorRole_VH: VarHandle by lazy { NSAccessibilityRelevanceIndicatorRole_LAYOUT.varHandle() }

var NSAccessibilityRelevanceIndicatorRole: MemorySegment
    get() = NSAccessibilityRelevanceIndicatorRole_VH.get(NSAccessibilityRelevanceIndicatorRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRelevanceIndicatorRole_VH.set(NSAccessibilityRelevanceIndicatorRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDateTimeAreaRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityDateTimeAreaRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDateTimeAreaRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDateTimeAreaRole").orElseThrow() }
private val NSAccessibilityDateTimeAreaRole_VH: VarHandle by lazy { NSAccessibilityDateTimeAreaRole_LAYOUT.varHandle() }

var NSAccessibilityDateTimeAreaRole: MemorySegment
    get() = NSAccessibilityDateTimeAreaRole_VH.get(NSAccessibilityDateTimeAreaRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDateTimeAreaRole_VH.set(NSAccessibilityDateTimeAreaRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLevelIndicatorRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityLevelIndicatorRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLevelIndicatorRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLevelIndicatorRole").orElseThrow() }
private val NSAccessibilityLevelIndicatorRole_VH: VarHandle by lazy { NSAccessibilityLevelIndicatorRole_LAYOUT.varHandle() }

var NSAccessibilityLevelIndicatorRole: MemorySegment
    get() = NSAccessibilityLevelIndicatorRole_VH.get(NSAccessibilityLevelIndicatorRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLevelIndicatorRole_VH.set(NSAccessibilityLevelIndicatorRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCellRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityCellRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCellRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCellRole").orElseThrow() }
private val NSAccessibilityCellRole_VH: VarHandle by lazy { NSAccessibilityCellRole_LAYOUT.varHandle() }

var NSAccessibilityCellRole: MemorySegment
    get() = NSAccessibilityCellRole_VH.get(NSAccessibilityCellRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCellRole_VH.set(NSAccessibilityCellRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPopoverRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityPopoverRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPopoverRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPopoverRole").orElseThrow() }
private val NSAccessibilityPopoverRole_VH: VarHandle by lazy { NSAccessibilityPopoverRole_LAYOUT.varHandle() }

var NSAccessibilityPopoverRole: MemorySegment
    get() = NSAccessibilityPopoverRole_VH.get(NSAccessibilityPopoverRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPopoverRole_VH.set(NSAccessibilityPopoverRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPageRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityPageRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPageRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPageRole").orElseThrow() }
private val NSAccessibilityPageRole_VH: VarHandle by lazy { NSAccessibilityPageRole_LAYOUT.varHandle() }

var NSAccessibilityPageRole: MemorySegment
    get() = NSAccessibilityPageRole_VH.get(NSAccessibilityPageRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPageRole_VH.set(NSAccessibilityPageRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityHeadingRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingRole").orElseThrow() }
private val NSAccessibilityHeadingRole_VH: VarHandle by lazy { NSAccessibilityHeadingRole_LAYOUT.varHandle() }

var NSAccessibilityHeadingRole: MemorySegment
    get() = NSAccessibilityHeadingRole_VH.get(NSAccessibilityHeadingRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingRole_VH.set(NSAccessibilityHeadingRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityListMarkerRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityListMarkerRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityListMarkerRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityListMarkerRole").orElseThrow() }
private val NSAccessibilityListMarkerRole_VH: VarHandle by lazy { NSAccessibilityListMarkerRole_LAYOUT.varHandle() }

var NSAccessibilityListMarkerRole: MemorySegment
    get() = NSAccessibilityListMarkerRole_VH.get(NSAccessibilityListMarkerRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityListMarkerRole_VH.set(NSAccessibilityListMarkerRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityWebAreaRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityWebAreaRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityWebAreaRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityWebAreaRole").orElseThrow() }
private val NSAccessibilityWebAreaRole_VH: VarHandle by lazy { NSAccessibilityWebAreaRole_LAYOUT.varHandle() }

var NSAccessibilityWebAreaRole: MemorySegment
    get() = NSAccessibilityWebAreaRole_VH.get(NSAccessibilityWebAreaRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityWebAreaRole_VH.set(NSAccessibilityWebAreaRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLayoutAreaRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityLayoutAreaRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLayoutAreaRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLayoutAreaRole").orElseThrow() }
private val NSAccessibilityLayoutAreaRole_VH: VarHandle by lazy { NSAccessibilityLayoutAreaRole_LAYOUT.varHandle() }

var NSAccessibilityLayoutAreaRole: MemorySegment
    get() = NSAccessibilityLayoutAreaRole_VH.get(NSAccessibilityLayoutAreaRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLayoutAreaRole_VH.set(NSAccessibilityLayoutAreaRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLayoutItemRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityLayoutItemRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLayoutItemRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLayoutItemRole").orElseThrow() }
private val NSAccessibilityLayoutItemRole_VH: VarHandle by lazy { NSAccessibilityLayoutItemRole_LAYOUT.varHandle() }

var NSAccessibilityLayoutItemRole: MemorySegment
    get() = NSAccessibilityLayoutItemRole_VH.get(NSAccessibilityLayoutItemRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLayoutItemRole_VH.set(NSAccessibilityLayoutItemRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHandleRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilityHandleRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHandleRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHandleRole").orElseThrow() }
private val NSAccessibilityHandleRole_VH: VarHandle by lazy { NSAccessibilityHandleRole_LAYOUT.varHandle() }

var NSAccessibilityHandleRole: MemorySegment
    get() = NSAccessibilityHandleRole_VH.get(NSAccessibilityHandleRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHandleRole_VH.set(NSAccessibilityHandleRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnknownSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityUnknownSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnknownSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnknownSubrole").orElseThrow() }
private val NSAccessibilityUnknownSubrole_VH: VarHandle by lazy { NSAccessibilityUnknownSubrole_LAYOUT.varHandle() }

var NSAccessibilityUnknownSubrole: MemorySegment
    get() = NSAccessibilityUnknownSubrole_VH.get(NSAccessibilityUnknownSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnknownSubrole_VH.set(NSAccessibilityUnknownSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCloseButtonSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityCloseButtonSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCloseButtonSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCloseButtonSubrole").orElseThrow() }
private val NSAccessibilityCloseButtonSubrole_VH: VarHandle by lazy { NSAccessibilityCloseButtonSubrole_LAYOUT.varHandle() }

var NSAccessibilityCloseButtonSubrole: MemorySegment
    get() = NSAccessibilityCloseButtonSubrole_VH.get(NSAccessibilityCloseButtonSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCloseButtonSubrole_VH.set(NSAccessibilityCloseButtonSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityZoomButtonSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityZoomButtonSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityZoomButtonSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityZoomButtonSubrole").orElseThrow() }
private val NSAccessibilityZoomButtonSubrole_VH: VarHandle by lazy { NSAccessibilityZoomButtonSubrole_LAYOUT.varHandle() }

var NSAccessibilityZoomButtonSubrole: MemorySegment
    get() = NSAccessibilityZoomButtonSubrole_VH.get(NSAccessibilityZoomButtonSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityZoomButtonSubrole_VH.set(NSAccessibilityZoomButtonSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMinimizeButtonSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityMinimizeButtonSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMinimizeButtonSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMinimizeButtonSubrole").orElseThrow() }
private val NSAccessibilityMinimizeButtonSubrole_VH: VarHandle by lazy { NSAccessibilityMinimizeButtonSubrole_LAYOUT.varHandle() }

var NSAccessibilityMinimizeButtonSubrole: MemorySegment
    get() = NSAccessibilityMinimizeButtonSubrole_VH.get(NSAccessibilityMinimizeButtonSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMinimizeButtonSubrole_VH.set(NSAccessibilityMinimizeButtonSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityToolbarButtonSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityToolbarButtonSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityToolbarButtonSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityToolbarButtonSubrole").orElseThrow() }
private val NSAccessibilityToolbarButtonSubrole_VH: VarHandle by lazy { NSAccessibilityToolbarButtonSubrole_LAYOUT.varHandle() }

var NSAccessibilityToolbarButtonSubrole: MemorySegment
    get() = NSAccessibilityToolbarButtonSubrole_VH.get(NSAccessibilityToolbarButtonSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityToolbarButtonSubrole_VH.set(NSAccessibilityToolbarButtonSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTableRowSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityTableRowSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTableRowSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTableRowSubrole").orElseThrow() }
private val NSAccessibilityTableRowSubrole_VH: VarHandle by lazy { NSAccessibilityTableRowSubrole_LAYOUT.varHandle() }

var NSAccessibilityTableRowSubrole: MemorySegment
    get() = NSAccessibilityTableRowSubrole_VH.get(NSAccessibilityTableRowSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTableRowSubrole_VH.set(NSAccessibilityTableRowSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityOutlineRowSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityOutlineRowSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityOutlineRowSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityOutlineRowSubrole").orElseThrow() }
private val NSAccessibilityOutlineRowSubrole_VH: VarHandle by lazy { NSAccessibilityOutlineRowSubrole_LAYOUT.varHandle() }

var NSAccessibilityOutlineRowSubrole: MemorySegment
    get() = NSAccessibilityOutlineRowSubrole_VH.get(NSAccessibilityOutlineRowSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityOutlineRowSubrole_VH.set(NSAccessibilityOutlineRowSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySecureTextFieldSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilitySecureTextFieldSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySecureTextFieldSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySecureTextFieldSubrole").orElseThrow() }
private val NSAccessibilitySecureTextFieldSubrole_VH: VarHandle by lazy { NSAccessibilitySecureTextFieldSubrole_LAYOUT.varHandle() }

var NSAccessibilitySecureTextFieldSubrole: MemorySegment
    get() = NSAccessibilitySecureTextFieldSubrole_VH.get(NSAccessibilitySecureTextFieldSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySecureTextFieldSubrole_VH.set(NSAccessibilitySecureTextFieldSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityStandardWindowSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityStandardWindowSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityStandardWindowSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityStandardWindowSubrole").orElseThrow() }
private val NSAccessibilityStandardWindowSubrole_VH: VarHandle by lazy { NSAccessibilityStandardWindowSubrole_LAYOUT.varHandle() }

var NSAccessibilityStandardWindowSubrole: MemorySegment
    get() = NSAccessibilityStandardWindowSubrole_VH.get(NSAccessibilityStandardWindowSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityStandardWindowSubrole_VH.set(NSAccessibilityStandardWindowSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDialogSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityDialogSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDialogSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDialogSubrole").orElseThrow() }
private val NSAccessibilityDialogSubrole_VH: VarHandle by lazy { NSAccessibilityDialogSubrole_LAYOUT.varHandle() }

var NSAccessibilityDialogSubrole: MemorySegment
    get() = NSAccessibilityDialogSubrole_VH.get(NSAccessibilityDialogSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDialogSubrole_VH.set(NSAccessibilityDialogSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySystemDialogSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilitySystemDialogSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySystemDialogSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySystemDialogSubrole").orElseThrow() }
private val NSAccessibilitySystemDialogSubrole_VH: VarHandle by lazy { NSAccessibilitySystemDialogSubrole_LAYOUT.varHandle() }

var NSAccessibilitySystemDialogSubrole: MemorySegment
    get() = NSAccessibilitySystemDialogSubrole_VH.get(NSAccessibilitySystemDialogSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySystemDialogSubrole_VH.set(NSAccessibilitySystemDialogSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFloatingWindowSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityFloatingWindowSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFloatingWindowSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFloatingWindowSubrole").orElseThrow() }
private val NSAccessibilityFloatingWindowSubrole_VH: VarHandle by lazy { NSAccessibilityFloatingWindowSubrole_LAYOUT.varHandle() }

var NSAccessibilityFloatingWindowSubrole: MemorySegment
    get() = NSAccessibilityFloatingWindowSubrole_VH.get(NSAccessibilityFloatingWindowSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFloatingWindowSubrole_VH.set(NSAccessibilityFloatingWindowSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySystemFloatingWindowSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilitySystemFloatingWindowSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySystemFloatingWindowSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySystemFloatingWindowSubrole").orElseThrow() }
private val NSAccessibilitySystemFloatingWindowSubrole_VH: VarHandle by lazy { NSAccessibilitySystemFloatingWindowSubrole_LAYOUT.varHandle() }

var NSAccessibilitySystemFloatingWindowSubrole: MemorySegment
    get() = NSAccessibilitySystemFloatingWindowSubrole_VH.get(NSAccessibilitySystemFloatingWindowSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySystemFloatingWindowSubrole_VH.set(NSAccessibilitySystemFloatingWindowSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityIncrementArrowSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityIncrementArrowSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityIncrementArrowSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityIncrementArrowSubrole").orElseThrow() }
private val NSAccessibilityIncrementArrowSubrole_VH: VarHandle by lazy { NSAccessibilityIncrementArrowSubrole_LAYOUT.varHandle() }

var NSAccessibilityIncrementArrowSubrole: MemorySegment
    get() = NSAccessibilityIncrementArrowSubrole_VH.get(NSAccessibilityIncrementArrowSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityIncrementArrowSubrole_VH.set(NSAccessibilityIncrementArrowSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDecrementArrowSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityDecrementArrowSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDecrementArrowSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDecrementArrowSubrole").orElseThrow() }
private val NSAccessibilityDecrementArrowSubrole_VH: VarHandle by lazy { NSAccessibilityDecrementArrowSubrole_LAYOUT.varHandle() }

var NSAccessibilityDecrementArrowSubrole: MemorySegment
    get() = NSAccessibilityDecrementArrowSubrole_VH.get(NSAccessibilityDecrementArrowSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDecrementArrowSubrole_VH.set(NSAccessibilityDecrementArrowSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityIncrementPageSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityIncrementPageSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityIncrementPageSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityIncrementPageSubrole").orElseThrow() }
private val NSAccessibilityIncrementPageSubrole_VH: VarHandle by lazy { NSAccessibilityIncrementPageSubrole_LAYOUT.varHandle() }

var NSAccessibilityIncrementPageSubrole: MemorySegment
    get() = NSAccessibilityIncrementPageSubrole_VH.get(NSAccessibilityIncrementPageSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityIncrementPageSubrole_VH.set(NSAccessibilityIncrementPageSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDecrementPageSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityDecrementPageSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDecrementPageSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDecrementPageSubrole").orElseThrow() }
private val NSAccessibilityDecrementPageSubrole_VH: VarHandle by lazy { NSAccessibilityDecrementPageSubrole_LAYOUT.varHandle() }

var NSAccessibilityDecrementPageSubrole: MemorySegment
    get() = NSAccessibilityDecrementPageSubrole_VH.get(NSAccessibilityDecrementPageSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDecrementPageSubrole_VH.set(NSAccessibilityDecrementPageSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchFieldSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilitySearchFieldSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchFieldSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchFieldSubrole").orElseThrow() }
private val NSAccessibilitySearchFieldSubrole_VH: VarHandle by lazy { NSAccessibilitySearchFieldSubrole_LAYOUT.varHandle() }

var NSAccessibilitySearchFieldSubrole: MemorySegment
    get() = NSAccessibilitySearchFieldSubrole_VH.get(NSAccessibilitySearchFieldSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchFieldSubrole_VH.set(NSAccessibilitySearchFieldSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextAttachmentSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityTextAttachmentSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextAttachmentSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextAttachmentSubrole").orElseThrow() }
private val NSAccessibilityTextAttachmentSubrole_VH: VarHandle by lazy { NSAccessibilityTextAttachmentSubrole_LAYOUT.varHandle() }

var NSAccessibilityTextAttachmentSubrole: MemorySegment
    get() = NSAccessibilityTextAttachmentSubrole_VH.get(NSAccessibilityTextAttachmentSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextAttachmentSubrole_VH.set(NSAccessibilityTextAttachmentSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextLinkSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityTextLinkSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextLinkSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextLinkSubrole").orElseThrow() }
private val NSAccessibilityTextLinkSubrole_VH: VarHandle by lazy { NSAccessibilityTextLinkSubrole_LAYOUT.varHandle() }

var NSAccessibilityTextLinkSubrole: MemorySegment
    get() = NSAccessibilityTextLinkSubrole_VH.get(NSAccessibilityTextLinkSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextLinkSubrole_VH.set(NSAccessibilityTextLinkSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTimelineSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityTimelineSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTimelineSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTimelineSubrole").orElseThrow() }
private val NSAccessibilityTimelineSubrole_VH: VarHandle by lazy { NSAccessibilityTimelineSubrole_LAYOUT.varHandle() }

var NSAccessibilityTimelineSubrole: MemorySegment
    get() = NSAccessibilityTimelineSubrole_VH.get(NSAccessibilityTimelineSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTimelineSubrole_VH.set(NSAccessibilityTimelineSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySortButtonSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilitySortButtonSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySortButtonSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySortButtonSubrole").orElseThrow() }
private val NSAccessibilitySortButtonSubrole_VH: VarHandle by lazy { NSAccessibilitySortButtonSubrole_LAYOUT.varHandle() }

var NSAccessibilitySortButtonSubrole: MemorySegment
    get() = NSAccessibilitySortButtonSubrole_VH.get(NSAccessibilitySortButtonSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySortButtonSubrole_VH.set(NSAccessibilitySortButtonSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRatingIndicatorSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityRatingIndicatorSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRatingIndicatorSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRatingIndicatorSubrole").orElseThrow() }
private val NSAccessibilityRatingIndicatorSubrole_VH: VarHandle by lazy { NSAccessibilityRatingIndicatorSubrole_LAYOUT.varHandle() }

var NSAccessibilityRatingIndicatorSubrole: MemorySegment
    get() = NSAccessibilityRatingIndicatorSubrole_VH.get(NSAccessibilityRatingIndicatorSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRatingIndicatorSubrole_VH.set(NSAccessibilityRatingIndicatorSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityContentListSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityContentListSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityContentListSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityContentListSubrole").orElseThrow() }
private val NSAccessibilityContentListSubrole_VH: VarHandle by lazy { NSAccessibilityContentListSubrole_LAYOUT.varHandle() }

var NSAccessibilityContentListSubrole: MemorySegment
    get() = NSAccessibilityContentListSubrole_VH.get(NSAccessibilityContentListSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityContentListSubrole_VH.set(NSAccessibilityContentListSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDefinitionListSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityDefinitionListSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDefinitionListSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDefinitionListSubrole").orElseThrow() }
private val NSAccessibilityDefinitionListSubrole_VH: VarHandle by lazy { NSAccessibilityDefinitionListSubrole_LAYOUT.varHandle() }

var NSAccessibilityDefinitionListSubrole: MemorySegment
    get() = NSAccessibilityDefinitionListSubrole_VH.get(NSAccessibilityDefinitionListSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDefinitionListSubrole_VH.set(NSAccessibilityDefinitionListSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFullScreenButtonSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityFullScreenButtonSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFullScreenButtonSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFullScreenButtonSubrole").orElseThrow() }
private val NSAccessibilityFullScreenButtonSubrole_VH: VarHandle by lazy { NSAccessibilityFullScreenButtonSubrole_LAYOUT.varHandle() }

var NSAccessibilityFullScreenButtonSubrole: MemorySegment
    get() = NSAccessibilityFullScreenButtonSubrole_VH.get(NSAccessibilityFullScreenButtonSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFullScreenButtonSubrole_VH.set(NSAccessibilityFullScreenButtonSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityToggleSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityToggleSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityToggleSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityToggleSubrole").orElseThrow() }
private val NSAccessibilityToggleSubrole_VH: VarHandle by lazy { NSAccessibilityToggleSubrole_LAYOUT.varHandle() }

var NSAccessibilityToggleSubrole: MemorySegment
    get() = NSAccessibilityToggleSubrole_VH.get(NSAccessibilityToggleSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityToggleSubrole_VH.set(NSAccessibilityToggleSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySwitchSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilitySwitchSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySwitchSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySwitchSubrole").orElseThrow() }
private val NSAccessibilitySwitchSubrole_VH: VarHandle by lazy { NSAccessibilitySwitchSubrole_LAYOUT.varHandle() }

var NSAccessibilitySwitchSubrole: MemorySegment
    get() = NSAccessibilitySwitchSubrole_VH.get(NSAccessibilitySwitchSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySwitchSubrole_VH.set(NSAccessibilitySwitchSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDescriptionListSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityDescriptionListSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDescriptionListSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDescriptionListSubrole").orElseThrow() }
private val NSAccessibilityDescriptionListSubrole_VH: VarHandle by lazy { NSAccessibilityDescriptionListSubrole_LAYOUT.varHandle() }

var NSAccessibilityDescriptionListSubrole: MemorySegment
    get() = NSAccessibilityDescriptionListSubrole_VH.get(NSAccessibilityDescriptionListSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDescriptionListSubrole_VH.set(NSAccessibilityDescriptionListSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTabButtonSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityTabButtonSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTabButtonSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTabButtonSubrole").orElseThrow() }
private val NSAccessibilityTabButtonSubrole_VH: VarHandle by lazy { NSAccessibilityTabButtonSubrole_LAYOUT.varHandle() }

var NSAccessibilityTabButtonSubrole: MemorySegment
    get() = NSAccessibilityTabButtonSubrole_VH.get(NSAccessibilityTabButtonSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTabButtonSubrole_VH.set(NSAccessibilityTabButtonSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCollectionListSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilityCollectionListSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCollectionListSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCollectionListSubrole").orElseThrow() }
private val NSAccessibilityCollectionListSubrole_VH: VarHandle by lazy { NSAccessibilityCollectionListSubrole_LAYOUT.varHandle() }

var NSAccessibilityCollectionListSubrole: MemorySegment
    get() = NSAccessibilityCollectionListSubrole_VH.get(NSAccessibilityCollectionListSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCollectionListSubrole_VH.set(NSAccessibilityCollectionListSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySectionListSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilitySectionListSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySectionListSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySectionListSubrole").orElseThrow() }
private val NSAccessibilitySectionListSubrole_VH: VarHandle by lazy { NSAccessibilitySectionListSubrole_LAYOUT.varHandle() }

var NSAccessibilitySectionListSubrole: MemorySegment
    get() = NSAccessibilitySectionListSubrole_VH.get(NSAccessibilitySectionListSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySectionListSubrole_VH.set(NSAccessibilitySectionListSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySuggestionSubrole typedef const NSAccessibilitySubrole = (Void)*
 */
private val NSAccessibilitySuggestionSubrole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySuggestionSubrole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySuggestionSubrole").orElseThrow() }
private val NSAccessibilitySuggestionSubrole_VH: VarHandle by lazy { NSAccessibilitySuggestionSubrole_LAYOUT.varHandle() }

var NSAccessibilitySuggestionSubrole: MemorySegment
    get() = NSAccessibilitySuggestionSubrole_VH.get(NSAccessibilitySuggestionSubrole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySuggestionSubrole_VH.set(NSAccessibilitySuggestionSubrole_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUIElementsKey typedef const NSAccessibilityNotificationUserInfoKey = (Void)*
 */
private val NSAccessibilityUIElementsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUIElementsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUIElementsKey").orElseThrow() }
private val NSAccessibilityUIElementsKey_VH: VarHandle by lazy { NSAccessibilityUIElementsKey_LAYOUT.varHandle() }

var NSAccessibilityUIElementsKey: MemorySegment
    get() = NSAccessibilityUIElementsKey_VH.get(NSAccessibilityUIElementsKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUIElementsKey_VH.set(NSAccessibilityUIElementsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPriorityKey typedef const NSAccessibilityNotificationUserInfoKey = (Void)*
 */
private val NSAccessibilityPriorityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPriorityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPriorityKey").orElseThrow() }
private val NSAccessibilityPriorityKey_VH: VarHandle by lazy { NSAccessibilityPriorityKey_LAYOUT.varHandle() }

var NSAccessibilityPriorityKey: MemorySegment
    get() = NSAccessibilityPriorityKey_VH.get(NSAccessibilityPriorityKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPriorityKey_VH.set(NSAccessibilityPriorityKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAnnouncementKey typedef const NSAccessibilityNotificationUserInfoKey = (Void)*
 */
private val NSAccessibilityAnnouncementKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAnnouncementKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAnnouncementKey").orElseThrow() }
private val NSAccessibilityAnnouncementKey_VH: VarHandle by lazy { NSAccessibilityAnnouncementKey_LAYOUT.varHandle() }

var NSAccessibilityAnnouncementKey: MemorySegment
    get() = NSAccessibilityAnnouncementKey_VH.get(NSAccessibilityAnnouncementKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAnnouncementKey_VH.set(NSAccessibilityAnnouncementKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPostNotificationWithUserInfo Void(typedef id = (Void)*,typedef NSAccessibilityNotificationName = typedef NSString = (Void)*,(Void)*)
 */
private val NSAccessibilityPostNotificationWithUserInfo_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityPostNotificationWithUserInfo_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityPostNotificationWithUserInfo").orElseThrow()
private val NSAccessibilityPostNotificationWithUserInfo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityPostNotificationWithUserInfo_ADDR, NSAccessibilityPostNotificationWithUserInfo_DESC)

fun NSAccessibilityPostNotificationWithUserInfo(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        NSAccessibilityPostNotificationWithUserInfo_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute").orElseThrow() }
private val NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute: MemorySegment
    get() = NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute_VH.get(NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute_VH.set(NSAccessibilityUIElementsForSearchPredicateParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityResultsForSearchPredicateParameterizedAttribute typedef const NSAccessibilityParameterizedAttributeName = (Void)*
 */
private val NSAccessibilityResultsForSearchPredicateParameterizedAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityResultsForSearchPredicateParameterizedAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityResultsForSearchPredicateParameterizedAttribute").orElseThrow() }
private val NSAccessibilityResultsForSearchPredicateParameterizedAttribute_VH: VarHandle by lazy { NSAccessibilityResultsForSearchPredicateParameterizedAttribute_LAYOUT.varHandle() }

var NSAccessibilityResultsForSearchPredicateParameterizedAttribute: MemorySegment
    get() = NSAccessibilityResultsForSearchPredicateParameterizedAttribute_VH.get(NSAccessibilityResultsForSearchPredicateParameterizedAttribute_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityResultsForSearchPredicateParameterizedAttribute_VH.set(NSAccessibilityResultsForSearchPredicateParameterizedAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchIdentifiersKey (Void)*
 */
private val NSAccessibilitySearchIdentifiersKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchIdentifiersKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchIdentifiersKey").orElseThrow() }
private val NSAccessibilitySearchIdentifiersKey_VH: VarHandle by lazy { NSAccessibilitySearchIdentifiersKey_LAYOUT.varHandle() }

var NSAccessibilitySearchIdentifiersKey: MemorySegment
    get() = NSAccessibilitySearchIdentifiersKey_VH.get(NSAccessibilitySearchIdentifiersKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchIdentifiersKey_VH.set(NSAccessibilitySearchIdentifiersKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchCurrentElementKey (Void)*
 */
private val NSAccessibilitySearchCurrentElementKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchCurrentElementKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchCurrentElementKey").orElseThrow() }
private val NSAccessibilitySearchCurrentElementKey_VH: VarHandle by lazy { NSAccessibilitySearchCurrentElementKey_LAYOUT.varHandle() }

var NSAccessibilitySearchCurrentElementKey: MemorySegment
    get() = NSAccessibilitySearchCurrentElementKey_VH.get(NSAccessibilitySearchCurrentElementKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchCurrentElementKey_VH.set(NSAccessibilitySearchCurrentElementKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchCurrentRangeKey (Void)*
 */
private val NSAccessibilitySearchCurrentRangeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchCurrentRangeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchCurrentRangeKey").orElseThrow() }
private val NSAccessibilitySearchCurrentRangeKey_VH: VarHandle by lazy { NSAccessibilitySearchCurrentRangeKey_LAYOUT.varHandle() }

var NSAccessibilitySearchCurrentRangeKey: MemorySegment
    get() = NSAccessibilitySearchCurrentRangeKey_VH.get(NSAccessibilitySearchCurrentRangeKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchCurrentRangeKey_VH.set(NSAccessibilitySearchCurrentRangeKey_SEGMENT, value)

