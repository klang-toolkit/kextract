package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : NSAccessibilitySearchDirectionKey (Void)*
 */
private val NSAccessibilitySearchDirectionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchDirectionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchDirectionKey").orElseThrow() }
private val NSAccessibilitySearchDirectionKey_VH: VarHandle by lazy { NSAccessibilitySearchDirectionKey_LAYOUT.varHandle() }

var NSAccessibilitySearchDirectionKey: MemorySegment
    get() = NSAccessibilitySearchDirectionKey_VH.get(NSAccessibilitySearchDirectionKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchDirectionKey_VH.set(NSAccessibilitySearchDirectionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchResultsLimitKey (Void)*
 */
private val NSAccessibilitySearchResultsLimitKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchResultsLimitKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchResultsLimitKey").orElseThrow() }
private val NSAccessibilitySearchResultsLimitKey_VH: VarHandle by lazy { NSAccessibilitySearchResultsLimitKey_LAYOUT.varHandle() }

var NSAccessibilitySearchResultsLimitKey: MemorySegment
    get() = NSAccessibilitySearchResultsLimitKey_VH.get(NSAccessibilitySearchResultsLimitKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchResultsLimitKey_VH.set(NSAccessibilitySearchResultsLimitKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchTextKey (Void)*
 */
private val NSAccessibilitySearchTextKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchTextKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchTextKey").orElseThrow() }
private val NSAccessibilitySearchTextKey_VH: VarHandle by lazy { NSAccessibilitySearchTextKey_LAYOUT.varHandle() }

var NSAccessibilitySearchTextKey: MemorySegment
    get() = NSAccessibilitySearchTextKey_VH.get(NSAccessibilitySearchTextKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchTextKey_VH.set(NSAccessibilitySearchTextKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchDirectionNext (Void)*
 */
private val NSAccessibilitySearchDirectionNext_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchDirectionNext_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchDirectionNext").orElseThrow() }
private val NSAccessibilitySearchDirectionNext_VH: VarHandle by lazy { NSAccessibilitySearchDirectionNext_LAYOUT.varHandle() }

var NSAccessibilitySearchDirectionNext: MemorySegment
    get() = NSAccessibilitySearchDirectionNext_VH.get(NSAccessibilitySearchDirectionNext_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchDirectionNext_VH.set(NSAccessibilitySearchDirectionNext_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchDirectionPrevious (Void)*
 */
private val NSAccessibilitySearchDirectionPrevious_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchDirectionPrevious_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchDirectionPrevious").orElseThrow() }
private val NSAccessibilitySearchDirectionPrevious_VH: VarHandle by lazy { NSAccessibilitySearchDirectionPrevious_LAYOUT.varHandle() }

var NSAccessibilitySearchDirectionPrevious: MemorySegment
    get() = NSAccessibilitySearchDirectionPrevious_VH.get(NSAccessibilitySearchDirectionPrevious_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchDirectionPrevious_VH.set(NSAccessibilitySearchDirectionPrevious_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchResultElementKey (Void)*
 */
private val NSAccessibilitySearchResultElementKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchResultElementKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchResultElementKey").orElseThrow() }
private val NSAccessibilitySearchResultElementKey_VH: VarHandle by lazy { NSAccessibilitySearchResultElementKey_LAYOUT.varHandle() }

var NSAccessibilitySearchResultElementKey: MemorySegment
    get() = NSAccessibilitySearchResultElementKey_VH.get(NSAccessibilitySearchResultElementKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchResultElementKey_VH.set(NSAccessibilitySearchResultElementKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchResultRangeKey (Void)*
 */
private val NSAccessibilitySearchResultRangeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchResultRangeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchResultRangeKey").orElseThrow() }
private val NSAccessibilitySearchResultRangeKey_VH: VarHandle by lazy { NSAccessibilitySearchResultRangeKey_LAYOUT.varHandle() }

var NSAccessibilitySearchResultRangeKey: MemorySegment
    get() = NSAccessibilitySearchResultRangeKey_VH.get(NSAccessibilitySearchResultRangeKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchResultRangeKey_VH.set(NSAccessibilitySearchResultRangeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchResultDescriptionOverrideKey (Void)*
 */
private val NSAccessibilitySearchResultDescriptionOverrideKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchResultDescriptionOverrideKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchResultDescriptionOverrideKey").orElseThrow() }
private val NSAccessibilitySearchResultDescriptionOverrideKey_VH: VarHandle by lazy { NSAccessibilitySearchResultDescriptionOverrideKey_LAYOUT.varHandle() }

var NSAccessibilitySearchResultDescriptionOverrideKey: MemorySegment
    get() = NSAccessibilitySearchResultDescriptionOverrideKey_VH.get(NSAccessibilitySearchResultDescriptionOverrideKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchResultDescriptionOverrideKey_VH.set(NSAccessibilitySearchResultDescriptionOverrideKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySearchResultLoaderKey (Void)*
 */
private val NSAccessibilitySearchResultLoaderKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySearchResultLoaderKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySearchResultLoaderKey").orElseThrow() }
private val NSAccessibilitySearchResultLoaderKey_VH: VarHandle by lazy { NSAccessibilitySearchResultLoaderKey_LAYOUT.varHandle() }

var NSAccessibilitySearchResultLoaderKey: MemorySegment
    get() = NSAccessibilitySearchResultLoaderKey_VH.get(NSAccessibilitySearchResultLoaderKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySearchResultLoaderKey_VH.set(NSAccessibilitySearchResultLoaderKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityAnyTypeSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityAnyTypeSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityAnyTypeSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityAnyTypeSearchKey").orElseThrow() }
private val NSAccessibilityAnyTypeSearchKey_VH: VarHandle by lazy { NSAccessibilityAnyTypeSearchKey_LAYOUT.varHandle() }

var NSAccessibilityAnyTypeSearchKey: MemorySegment
    get() = NSAccessibilityAnyTypeSearchKey_VH.get(NSAccessibilityAnyTypeSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityAnyTypeSearchKey_VH.set(NSAccessibilityAnyTypeSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityArticleSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityArticleSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityArticleSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityArticleSearchKey").orElseThrow() }
private val NSAccessibilityArticleSearchKey_VH: VarHandle by lazy { NSAccessibilityArticleSearchKey_LAYOUT.varHandle() }

var NSAccessibilityArticleSearchKey: MemorySegment
    get() = NSAccessibilityArticleSearchKey_VH.get(NSAccessibilityArticleSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityArticleSearchKey_VH.set(NSAccessibilityArticleSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityBlockquoteSameLevelSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityBlockquoteSameLevelSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityBlockquoteSameLevelSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityBlockquoteSameLevelSearchKey").orElseThrow() }
private val NSAccessibilityBlockquoteSameLevelSearchKey_VH: VarHandle by lazy { NSAccessibilityBlockquoteSameLevelSearchKey_LAYOUT.varHandle() }

var NSAccessibilityBlockquoteSameLevelSearchKey: MemorySegment
    get() = NSAccessibilityBlockquoteSameLevelSearchKey_VH.get(NSAccessibilityBlockquoteSameLevelSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityBlockquoteSameLevelSearchKey_VH.set(NSAccessibilityBlockquoteSameLevelSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityBlockquoteSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityBlockquoteSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityBlockquoteSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityBlockquoteSearchKey").orElseThrow() }
private val NSAccessibilityBlockquoteSearchKey_VH: VarHandle by lazy { NSAccessibilityBlockquoteSearchKey_LAYOUT.varHandle() }

var NSAccessibilityBlockquoteSearchKey: MemorySegment
    get() = NSAccessibilityBlockquoteSearchKey_VH.get(NSAccessibilityBlockquoteSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityBlockquoteSearchKey_VH.set(NSAccessibilityBlockquoteSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityBoldFontSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityBoldFontSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityBoldFontSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityBoldFontSearchKey").orElseThrow() }
private val NSAccessibilityBoldFontSearchKey_VH: VarHandle by lazy { NSAccessibilityBoldFontSearchKey_LAYOUT.varHandle() }

var NSAccessibilityBoldFontSearchKey: MemorySegment
    get() = NSAccessibilityBoldFontSearchKey_VH.get(NSAccessibilityBoldFontSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityBoldFontSearchKey_VH.set(NSAccessibilityBoldFontSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityButtonSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityButtonSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityButtonSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityButtonSearchKey").orElseThrow() }
private val NSAccessibilityButtonSearchKey_VH: VarHandle by lazy { NSAccessibilityButtonSearchKey_LAYOUT.varHandle() }

var NSAccessibilityButtonSearchKey: MemorySegment
    get() = NSAccessibilityButtonSearchKey_VH.get(NSAccessibilityButtonSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityButtonSearchKey_VH.set(NSAccessibilityButtonSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityCheckBoxSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityCheckBoxSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityCheckBoxSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityCheckBoxSearchKey").orElseThrow() }
private val NSAccessibilityCheckBoxSearchKey_VH: VarHandle by lazy { NSAccessibilityCheckBoxSearchKey_LAYOUT.varHandle() }

var NSAccessibilityCheckBoxSearchKey: MemorySegment
    get() = NSAccessibilityCheckBoxSearchKey_VH.get(NSAccessibilityCheckBoxSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityCheckBoxSearchKey_VH.set(NSAccessibilityCheckBoxSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityControlSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityControlSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityControlSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityControlSearchKey").orElseThrow() }
private val NSAccessibilityControlSearchKey_VH: VarHandle by lazy { NSAccessibilityControlSearchKey_LAYOUT.varHandle() }

var NSAccessibilityControlSearchKey: MemorySegment
    get() = NSAccessibilityControlSearchKey_VH.get(NSAccessibilityControlSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityControlSearchKey_VH.set(NSAccessibilityControlSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityDifferentTypeSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityDifferentTypeSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityDifferentTypeSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityDifferentTypeSearchKey").orElseThrow() }
private val NSAccessibilityDifferentTypeSearchKey_VH: VarHandle by lazy { NSAccessibilityDifferentTypeSearchKey_LAYOUT.varHandle() }

var NSAccessibilityDifferentTypeSearchKey: MemorySegment
    get() = NSAccessibilityDifferentTypeSearchKey_VH.get(NSAccessibilityDifferentTypeSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityDifferentTypeSearchKey_VH.set(NSAccessibilityDifferentTypeSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFontChangeSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityFontChangeSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFontChangeSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFontChangeSearchKey").orElseThrow() }
private val NSAccessibilityFontChangeSearchKey_VH: VarHandle by lazy { NSAccessibilityFontChangeSearchKey_LAYOUT.varHandle() }

var NSAccessibilityFontChangeSearchKey: MemorySegment
    get() = NSAccessibilityFontChangeSearchKey_VH.get(NSAccessibilityFontChangeSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFontChangeSearchKey_VH.set(NSAccessibilityFontChangeSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFontColorChangeSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityFontColorChangeSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFontColorChangeSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFontColorChangeSearchKey").orElseThrow() }
private val NSAccessibilityFontColorChangeSearchKey_VH: VarHandle by lazy { NSAccessibilityFontColorChangeSearchKey_LAYOUT.varHandle() }

var NSAccessibilityFontColorChangeSearchKey: MemorySegment
    get() = NSAccessibilityFontColorChangeSearchKey_VH.get(NSAccessibilityFontColorChangeSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFontColorChangeSearchKey_VH.set(NSAccessibilityFontColorChangeSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFrameSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityFrameSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityFrameSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityFrameSearchKey").orElseThrow() }
private val NSAccessibilityFrameSearchKey_VH: VarHandle by lazy { NSAccessibilityFrameSearchKey_LAYOUT.varHandle() }

var NSAccessibilityFrameSearchKey: MemorySegment
    get() = NSAccessibilityFrameSearchKey_VH.get(NSAccessibilityFrameSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityFrameSearchKey_VH.set(NSAccessibilityFrameSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityGraphicSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityGraphicSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityGraphicSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityGraphicSearchKey").orElseThrow() }
private val NSAccessibilityGraphicSearchKey_VH: VarHandle by lazy { NSAccessibilityGraphicSearchKey_LAYOUT.varHandle() }

var NSAccessibilityGraphicSearchKey: MemorySegment
    get() = NSAccessibilityGraphicSearchKey_VH.get(NSAccessibilityGraphicSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityGraphicSearchKey_VH.set(NSAccessibilityGraphicSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingLevel1SearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityHeadingLevel1SearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingLevel1SearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingLevel1SearchKey").orElseThrow() }
private val NSAccessibilityHeadingLevel1SearchKey_VH: VarHandle by lazy { NSAccessibilityHeadingLevel1SearchKey_LAYOUT.varHandle() }

var NSAccessibilityHeadingLevel1SearchKey: MemorySegment
    get() = NSAccessibilityHeadingLevel1SearchKey_VH.get(NSAccessibilityHeadingLevel1SearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingLevel1SearchKey_VH.set(NSAccessibilityHeadingLevel1SearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingLevel2SearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityHeadingLevel2SearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingLevel2SearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingLevel2SearchKey").orElseThrow() }
private val NSAccessibilityHeadingLevel2SearchKey_VH: VarHandle by lazy { NSAccessibilityHeadingLevel2SearchKey_LAYOUT.varHandle() }

var NSAccessibilityHeadingLevel2SearchKey: MemorySegment
    get() = NSAccessibilityHeadingLevel2SearchKey_VH.get(NSAccessibilityHeadingLevel2SearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingLevel2SearchKey_VH.set(NSAccessibilityHeadingLevel2SearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingLevel3SearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityHeadingLevel3SearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingLevel3SearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingLevel3SearchKey").orElseThrow() }
private val NSAccessibilityHeadingLevel3SearchKey_VH: VarHandle by lazy { NSAccessibilityHeadingLevel3SearchKey_LAYOUT.varHandle() }

var NSAccessibilityHeadingLevel3SearchKey: MemorySegment
    get() = NSAccessibilityHeadingLevel3SearchKey_VH.get(NSAccessibilityHeadingLevel3SearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingLevel3SearchKey_VH.set(NSAccessibilityHeadingLevel3SearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingLevel4SearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityHeadingLevel4SearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingLevel4SearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingLevel4SearchKey").orElseThrow() }
private val NSAccessibilityHeadingLevel4SearchKey_VH: VarHandle by lazy { NSAccessibilityHeadingLevel4SearchKey_LAYOUT.varHandle() }

var NSAccessibilityHeadingLevel4SearchKey: MemorySegment
    get() = NSAccessibilityHeadingLevel4SearchKey_VH.get(NSAccessibilityHeadingLevel4SearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingLevel4SearchKey_VH.set(NSAccessibilityHeadingLevel4SearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingLevel5SearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityHeadingLevel5SearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingLevel5SearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingLevel5SearchKey").orElseThrow() }
private val NSAccessibilityHeadingLevel5SearchKey_VH: VarHandle by lazy { NSAccessibilityHeadingLevel5SearchKey_LAYOUT.varHandle() }

var NSAccessibilityHeadingLevel5SearchKey: MemorySegment
    get() = NSAccessibilityHeadingLevel5SearchKey_VH.get(NSAccessibilityHeadingLevel5SearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingLevel5SearchKey_VH.set(NSAccessibilityHeadingLevel5SearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingLevel6SearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityHeadingLevel6SearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingLevel6SearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingLevel6SearchKey").orElseThrow() }
private val NSAccessibilityHeadingLevel6SearchKey_VH: VarHandle by lazy { NSAccessibilityHeadingLevel6SearchKey_LAYOUT.varHandle() }

var NSAccessibilityHeadingLevel6SearchKey: MemorySegment
    get() = NSAccessibilityHeadingLevel6SearchKey_VH.get(NSAccessibilityHeadingLevel6SearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingLevel6SearchKey_VH.set(NSAccessibilityHeadingLevel6SearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingSameLevelSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityHeadingSameLevelSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingSameLevelSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingSameLevelSearchKey").orElseThrow() }
private val NSAccessibilityHeadingSameLevelSearchKey_VH: VarHandle by lazy { NSAccessibilityHeadingSameLevelSearchKey_LAYOUT.varHandle() }

var NSAccessibilityHeadingSameLevelSearchKey: MemorySegment
    get() = NSAccessibilityHeadingSameLevelSearchKey_VH.get(NSAccessibilityHeadingSameLevelSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingSameLevelSearchKey_VH.set(NSAccessibilityHeadingSameLevelSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityHeadingSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityHeadingSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityHeadingSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityHeadingSearchKey").orElseThrow() }
private val NSAccessibilityHeadingSearchKey_VH: VarHandle by lazy { NSAccessibilityHeadingSearchKey_LAYOUT.varHandle() }

var NSAccessibilityHeadingSearchKey: MemorySegment
    get() = NSAccessibilityHeadingSearchKey_VH.get(NSAccessibilityHeadingSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityHeadingSearchKey_VH.set(NSAccessibilityHeadingSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityItalicFontSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityItalicFontSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityItalicFontSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityItalicFontSearchKey").orElseThrow() }
private val NSAccessibilityItalicFontSearchKey_VH: VarHandle by lazy { NSAccessibilityItalicFontSearchKey_LAYOUT.varHandle() }

var NSAccessibilityItalicFontSearchKey: MemorySegment
    get() = NSAccessibilityItalicFontSearchKey_VH.get(NSAccessibilityItalicFontSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityItalicFontSearchKey_VH.set(NSAccessibilityItalicFontSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityKeyboardFocusableSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityKeyboardFocusableSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityKeyboardFocusableSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityKeyboardFocusableSearchKey").orElseThrow() }
private val NSAccessibilityKeyboardFocusableSearchKey_VH: VarHandle by lazy { NSAccessibilityKeyboardFocusableSearchKey_LAYOUT.varHandle() }

var NSAccessibilityKeyboardFocusableSearchKey: MemorySegment
    get() = NSAccessibilityKeyboardFocusableSearchKey_VH.get(NSAccessibilityKeyboardFocusableSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityKeyboardFocusableSearchKey_VH.set(NSAccessibilityKeyboardFocusableSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLandmarkSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityLandmarkSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLandmarkSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLandmarkSearchKey").orElseThrow() }
private val NSAccessibilityLandmarkSearchKey_VH: VarHandle by lazy { NSAccessibilityLandmarkSearchKey_LAYOUT.varHandle() }

var NSAccessibilityLandmarkSearchKey: MemorySegment
    get() = NSAccessibilityLandmarkSearchKey_VH.get(NSAccessibilityLandmarkSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLandmarkSearchKey_VH.set(NSAccessibilityLandmarkSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLinkSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityLinkSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLinkSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLinkSearchKey").orElseThrow() }
private val NSAccessibilityLinkSearchKey_VH: VarHandle by lazy { NSAccessibilityLinkSearchKey_LAYOUT.varHandle() }

var NSAccessibilityLinkSearchKey: MemorySegment
    get() = NSAccessibilityLinkSearchKey_VH.get(NSAccessibilityLinkSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLinkSearchKey_VH.set(NSAccessibilityLinkSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityListSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityListSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityListSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityListSearchKey").orElseThrow() }
private val NSAccessibilityListSearchKey_VH: VarHandle by lazy { NSAccessibilityListSearchKey_LAYOUT.varHandle() }

var NSAccessibilityListSearchKey: MemorySegment
    get() = NSAccessibilityListSearchKey_VH.get(NSAccessibilityListSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityListSearchKey_VH.set(NSAccessibilityListSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityLiveRegionSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityLiveRegionSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityLiveRegionSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityLiveRegionSearchKey").orElseThrow() }
private val NSAccessibilityLiveRegionSearchKey_VH: VarHandle by lazy { NSAccessibilityLiveRegionSearchKey_LAYOUT.varHandle() }

var NSAccessibilityLiveRegionSearchKey: MemorySegment
    get() = NSAccessibilityLiveRegionSearchKey_VH.get(NSAccessibilityLiveRegionSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityLiveRegionSearchKey_VH.set(NSAccessibilityLiveRegionSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityMisspelledWordSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityMisspelledWordSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityMisspelledWordSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityMisspelledWordSearchKey").orElseThrow() }
private val NSAccessibilityMisspelledWordSearchKey_VH: VarHandle by lazy { NSAccessibilityMisspelledWordSearchKey_LAYOUT.varHandle() }

var NSAccessibilityMisspelledWordSearchKey: MemorySegment
    get() = NSAccessibilityMisspelledWordSearchKey_VH.get(NSAccessibilityMisspelledWordSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityMisspelledWordSearchKey_VH.set(NSAccessibilityMisspelledWordSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityOutlineSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityOutlineSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityOutlineSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityOutlineSearchKey").orElseThrow() }
private val NSAccessibilityOutlineSearchKey_VH: VarHandle by lazy { NSAccessibilityOutlineSearchKey_LAYOUT.varHandle() }

var NSAccessibilityOutlineSearchKey: MemorySegment
    get() = NSAccessibilityOutlineSearchKey_VH.get(NSAccessibilityOutlineSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityOutlineSearchKey_VH.set(NSAccessibilityOutlineSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityPlainTextSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityPlainTextSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityPlainTextSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityPlainTextSearchKey").orElseThrow() }
private val NSAccessibilityPlainTextSearchKey_VH: VarHandle by lazy { NSAccessibilityPlainTextSearchKey_LAYOUT.varHandle() }

var NSAccessibilityPlainTextSearchKey: MemorySegment
    get() = NSAccessibilityPlainTextSearchKey_VH.get(NSAccessibilityPlainTextSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityPlainTextSearchKey_VH.set(NSAccessibilityPlainTextSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityRadioGroupSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityRadioGroupSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityRadioGroupSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityRadioGroupSearchKey").orElseThrow() }
private val NSAccessibilityRadioGroupSearchKey_VH: VarHandle by lazy { NSAccessibilityRadioGroupSearchKey_LAYOUT.varHandle() }

var NSAccessibilityRadioGroupSearchKey: MemorySegment
    get() = NSAccessibilityRadioGroupSearchKey_VH.get(NSAccessibilityRadioGroupSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityRadioGroupSearchKey_VH.set(NSAccessibilityRadioGroupSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySameTypeSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilitySameTypeSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySameTypeSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySameTypeSearchKey").orElseThrow() }
private val NSAccessibilitySameTypeSearchKey_VH: VarHandle by lazy { NSAccessibilitySameTypeSearchKey_LAYOUT.varHandle() }

var NSAccessibilitySameTypeSearchKey: MemorySegment
    get() = NSAccessibilitySameTypeSearchKey_VH.get(NSAccessibilitySameTypeSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySameTypeSearchKey_VH.set(NSAccessibilitySameTypeSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityStaticTextSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityStaticTextSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityStaticTextSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityStaticTextSearchKey").orElseThrow() }
private val NSAccessibilityStaticTextSearchKey_VH: VarHandle by lazy { NSAccessibilityStaticTextSearchKey_LAYOUT.varHandle() }

var NSAccessibilityStaticTextSearchKey: MemorySegment
    get() = NSAccessibilityStaticTextSearchKey_VH.get(NSAccessibilityStaticTextSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityStaticTextSearchKey_VH.set(NSAccessibilityStaticTextSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityStyleChangeSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityStyleChangeSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityStyleChangeSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityStyleChangeSearchKey").orElseThrow() }
private val NSAccessibilityStyleChangeSearchKey_VH: VarHandle by lazy { NSAccessibilityStyleChangeSearchKey_LAYOUT.varHandle() }

var NSAccessibilityStyleChangeSearchKey: MemorySegment
    get() = NSAccessibilityStyleChangeSearchKey_VH.get(NSAccessibilityStyleChangeSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityStyleChangeSearchKey_VH.set(NSAccessibilityStyleChangeSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTableSameLevelSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityTableSameLevelSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTableSameLevelSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTableSameLevelSearchKey").orElseThrow() }
private val NSAccessibilityTableSameLevelSearchKey_VH: VarHandle by lazy { NSAccessibilityTableSameLevelSearchKey_LAYOUT.varHandle() }

var NSAccessibilityTableSameLevelSearchKey: MemorySegment
    get() = NSAccessibilityTableSameLevelSearchKey_VH.get(NSAccessibilityTableSameLevelSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTableSameLevelSearchKey_VH.set(NSAccessibilityTableSameLevelSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTableSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityTableSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTableSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTableSearchKey").orElseThrow() }
private val NSAccessibilityTableSearchKey_VH: VarHandle by lazy { NSAccessibilityTableSearchKey_LAYOUT.varHandle() }

var NSAccessibilityTableSearchKey: MemorySegment
    get() = NSAccessibilityTableSearchKey_VH.get(NSAccessibilityTableSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTableSearchKey_VH.set(NSAccessibilityTableSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextFieldSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityTextFieldSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextFieldSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextFieldSearchKey").orElseThrow() }
private val NSAccessibilityTextFieldSearchKey_VH: VarHandle by lazy { NSAccessibilityTextFieldSearchKey_LAYOUT.varHandle() }

var NSAccessibilityTextFieldSearchKey: MemorySegment
    get() = NSAccessibilityTextFieldSearchKey_VH.get(NSAccessibilityTextFieldSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextFieldSearchKey_VH.set(NSAccessibilityTextFieldSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextStateChangeTypeKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityTextStateChangeTypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextStateChangeTypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextStateChangeTypeKey").orElseThrow() }
private val NSAccessibilityTextStateChangeTypeKey_VH: VarHandle by lazy { NSAccessibilityTextStateChangeTypeKey_LAYOUT.varHandle() }

var NSAccessibilityTextStateChangeTypeKey: MemorySegment
    get() = NSAccessibilityTextStateChangeTypeKey_VH.get(NSAccessibilityTextStateChangeTypeKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextStateChangeTypeKey_VH.set(NSAccessibilityTextStateChangeTypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityTextStateSyncKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityTextStateSyncKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityTextStateSyncKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityTextStateSyncKey").orElseThrow() }
private val NSAccessibilityTextStateSyncKey_VH: VarHandle by lazy { NSAccessibilityTextStateSyncKey_LAYOUT.varHandle() }

var NSAccessibilityTextStateSyncKey: MemorySegment
    get() = NSAccessibilityTextStateSyncKey_VH.get(NSAccessibilityTextStateSyncKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityTextStateSyncKey_VH.set(NSAccessibilityTextStateSyncKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnderlineSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityUnderlineSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnderlineSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnderlineSearchKey").orElseThrow() }
private val NSAccessibilityUnderlineSearchKey_VH: VarHandle by lazy { NSAccessibilityUnderlineSearchKey_LAYOUT.varHandle() }

var NSAccessibilityUnderlineSearchKey: MemorySegment
    get() = NSAccessibilityUnderlineSearchKey_VH.get(NSAccessibilityUnderlineSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnderlineSearchKey_VH.set(NSAccessibilityUnderlineSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityUnvisitedLinkSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityUnvisitedLinkSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityUnvisitedLinkSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityUnvisitedLinkSearchKey").orElseThrow() }
private val NSAccessibilityUnvisitedLinkSearchKey_VH: VarHandle by lazy { NSAccessibilityUnvisitedLinkSearchKey_LAYOUT.varHandle() }

var NSAccessibilityUnvisitedLinkSearchKey: MemorySegment
    get() = NSAccessibilityUnvisitedLinkSearchKey_VH.get(NSAccessibilityUnvisitedLinkSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityUnvisitedLinkSearchKey_VH.set(NSAccessibilityUnvisitedLinkSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityVisitedLinkSearchKey typedef const NSAccessibilitySearchKey = (Void)*
 */
private val NSAccessibilityVisitedLinkSearchKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilityVisitedLinkSearchKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilityVisitedLinkSearchKey").orElseThrow() }
private val NSAccessibilityVisitedLinkSearchKey_VH: VarHandle by lazy { NSAccessibilityVisitedLinkSearchKey_LAYOUT.varHandle() }

var NSAccessibilityVisitedLinkSearchKey: MemorySegment
    get() = NSAccessibilityVisitedLinkSearchKey_VH.get(NSAccessibilityVisitedLinkSearchKey_SEGMENT) as MemorySegment
    set(value) = NSAccessibilityVisitedLinkSearchKey_VH.set(NSAccessibilityVisitedLinkSearchKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilitySortButtonRole typedef const NSAccessibilityRole = (Void)*
 */
private val NSAccessibilitySortButtonRole_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAccessibilitySortButtonRole_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAccessibilitySortButtonRole").orElseThrow() }
private val NSAccessibilitySortButtonRole_VH: VarHandle by lazy { NSAccessibilitySortButtonRole_LAYOUT.varHandle() }

var NSAccessibilitySortButtonRole: MemorySegment
    get() = NSAccessibilitySortButtonRole_VH.get(NSAccessibilitySortButtonRole_SEGMENT) as MemorySegment
    set(value) = NSAccessibilitySortButtonRole_VH.set(NSAccessibilitySortButtonRole_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDesktopImageScalingKey typedef const NSWorkspaceDesktopImageOptionKey = (Void)*
 */
private val NSWorkspaceDesktopImageScalingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDesktopImageScalingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDesktopImageScalingKey").orElseThrow() }
private val NSWorkspaceDesktopImageScalingKey_VH: VarHandle by lazy { NSWorkspaceDesktopImageScalingKey_LAYOUT.varHandle() }

var NSWorkspaceDesktopImageScalingKey: MemorySegment
    get() = NSWorkspaceDesktopImageScalingKey_VH.get(NSWorkspaceDesktopImageScalingKey_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDesktopImageScalingKey_VH.set(NSWorkspaceDesktopImageScalingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDesktopImageAllowClippingKey typedef const NSWorkspaceDesktopImageOptionKey = (Void)*
 */
private val NSWorkspaceDesktopImageAllowClippingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDesktopImageAllowClippingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDesktopImageAllowClippingKey").orElseThrow() }
private val NSWorkspaceDesktopImageAllowClippingKey_VH: VarHandle by lazy { NSWorkspaceDesktopImageAllowClippingKey_LAYOUT.varHandle() }

var NSWorkspaceDesktopImageAllowClippingKey: MemorySegment
    get() = NSWorkspaceDesktopImageAllowClippingKey_VH.get(NSWorkspaceDesktopImageAllowClippingKey_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDesktopImageAllowClippingKey_VH.set(NSWorkspaceDesktopImageAllowClippingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDesktopImageFillColorKey typedef const NSWorkspaceDesktopImageOptionKey = (Void)*
 */
private val NSWorkspaceDesktopImageFillColorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDesktopImageFillColorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDesktopImageFillColorKey").orElseThrow() }
private val NSWorkspaceDesktopImageFillColorKey_VH: VarHandle by lazy { NSWorkspaceDesktopImageFillColorKey_LAYOUT.varHandle() }

var NSWorkspaceDesktopImageFillColorKey: MemorySegment
    get() = NSWorkspaceDesktopImageFillColorKey_VH.get(NSWorkspaceDesktopImageFillColorKey_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDesktopImageFillColorKey_VH.set(NSWorkspaceDesktopImageFillColorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceApplicationKey (Void)*
 */
private val NSWorkspaceApplicationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceApplicationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceApplicationKey").orElseThrow() }
private val NSWorkspaceApplicationKey_VH: VarHandle by lazy { NSWorkspaceApplicationKey_LAYOUT.varHandle() }

var NSWorkspaceApplicationKey: MemorySegment
    get() = NSWorkspaceApplicationKey_VH.get(NSWorkspaceApplicationKey_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceApplicationKey_VH.set(NSWorkspaceApplicationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceWillLaunchApplicationNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceWillLaunchApplicationNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceWillLaunchApplicationNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceWillLaunchApplicationNotification").orElseThrow() }
private val NSWorkspaceWillLaunchApplicationNotification_VH: VarHandle by lazy { NSWorkspaceWillLaunchApplicationNotification_LAYOUT.varHandle() }

var NSWorkspaceWillLaunchApplicationNotification: MemorySegment
    get() = NSWorkspaceWillLaunchApplicationNotification_VH.get(NSWorkspaceWillLaunchApplicationNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceWillLaunchApplicationNotification_VH.set(NSWorkspaceWillLaunchApplicationNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidLaunchApplicationNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDidLaunchApplicationNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidLaunchApplicationNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidLaunchApplicationNotification").orElseThrow() }
private val NSWorkspaceDidLaunchApplicationNotification_VH: VarHandle by lazy { NSWorkspaceDidLaunchApplicationNotification_LAYOUT.varHandle() }

var NSWorkspaceDidLaunchApplicationNotification: MemorySegment
    get() = NSWorkspaceDidLaunchApplicationNotification_VH.get(NSWorkspaceDidLaunchApplicationNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidLaunchApplicationNotification_VH.set(NSWorkspaceDidLaunchApplicationNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidTerminateApplicationNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDidTerminateApplicationNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidTerminateApplicationNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidTerminateApplicationNotification").orElseThrow() }
private val NSWorkspaceDidTerminateApplicationNotification_VH: VarHandle by lazy { NSWorkspaceDidTerminateApplicationNotification_LAYOUT.varHandle() }

var NSWorkspaceDidTerminateApplicationNotification: MemorySegment
    get() = NSWorkspaceDidTerminateApplicationNotification_VH.get(NSWorkspaceDidTerminateApplicationNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidTerminateApplicationNotification_VH.set(NSWorkspaceDidTerminateApplicationNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidHideApplicationNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceDidHideApplicationNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidHideApplicationNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidHideApplicationNotification").orElseThrow() }
private val NSWorkspaceDidHideApplicationNotification_VH: VarHandle by lazy { NSWorkspaceDidHideApplicationNotification_LAYOUT.varHandle() }

var NSWorkspaceDidHideApplicationNotification: MemorySegment
    get() = NSWorkspaceDidHideApplicationNotification_VH.get(NSWorkspaceDidHideApplicationNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidHideApplicationNotification_VH.set(NSWorkspaceDidHideApplicationNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidUnhideApplicationNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceDidUnhideApplicationNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidUnhideApplicationNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidUnhideApplicationNotification").orElseThrow() }
private val NSWorkspaceDidUnhideApplicationNotification_VH: VarHandle by lazy { NSWorkspaceDidUnhideApplicationNotification_LAYOUT.varHandle() }

var NSWorkspaceDidUnhideApplicationNotification: MemorySegment
    get() = NSWorkspaceDidUnhideApplicationNotification_VH.get(NSWorkspaceDidUnhideApplicationNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidUnhideApplicationNotification_VH.set(NSWorkspaceDidUnhideApplicationNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidActivateApplicationNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceDidActivateApplicationNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidActivateApplicationNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidActivateApplicationNotification").orElseThrow() }
private val NSWorkspaceDidActivateApplicationNotification_VH: VarHandle by lazy { NSWorkspaceDidActivateApplicationNotification_LAYOUT.varHandle() }

var NSWorkspaceDidActivateApplicationNotification: MemorySegment
    get() = NSWorkspaceDidActivateApplicationNotification_VH.get(NSWorkspaceDidActivateApplicationNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidActivateApplicationNotification_VH.set(NSWorkspaceDidActivateApplicationNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidDeactivateApplicationNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceDidDeactivateApplicationNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidDeactivateApplicationNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidDeactivateApplicationNotification").orElseThrow() }
private val NSWorkspaceDidDeactivateApplicationNotification_VH: VarHandle by lazy { NSWorkspaceDidDeactivateApplicationNotification_LAYOUT.varHandle() }

var NSWorkspaceDidDeactivateApplicationNotification: MemorySegment
    get() = NSWorkspaceDidDeactivateApplicationNotification_VH.get(NSWorkspaceDidDeactivateApplicationNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidDeactivateApplicationNotification_VH.set(NSWorkspaceDidDeactivateApplicationNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceVolumeLocalizedNameKey (Void)*
 */
private val NSWorkspaceVolumeLocalizedNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceVolumeLocalizedNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceVolumeLocalizedNameKey").orElseThrow() }
private val NSWorkspaceVolumeLocalizedNameKey_VH: VarHandle by lazy { NSWorkspaceVolumeLocalizedNameKey_LAYOUT.varHandle() }

var NSWorkspaceVolumeLocalizedNameKey: MemorySegment
    get() = NSWorkspaceVolumeLocalizedNameKey_VH.get(NSWorkspaceVolumeLocalizedNameKey_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceVolumeLocalizedNameKey_VH.set(NSWorkspaceVolumeLocalizedNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceVolumeURLKey (Void)*
 */
private val NSWorkspaceVolumeURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceVolumeURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceVolumeURLKey").orElseThrow() }
private val NSWorkspaceVolumeURLKey_VH: VarHandle by lazy { NSWorkspaceVolumeURLKey_LAYOUT.varHandle() }

var NSWorkspaceVolumeURLKey: MemorySegment
    get() = NSWorkspaceVolumeURLKey_VH.get(NSWorkspaceVolumeURLKey_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceVolumeURLKey_VH.set(NSWorkspaceVolumeURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceVolumeOldLocalizedNameKey (Void)*
 */
private val NSWorkspaceVolumeOldLocalizedNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceVolumeOldLocalizedNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceVolumeOldLocalizedNameKey").orElseThrow() }
private val NSWorkspaceVolumeOldLocalizedNameKey_VH: VarHandle by lazy { NSWorkspaceVolumeOldLocalizedNameKey_LAYOUT.varHandle() }

var NSWorkspaceVolumeOldLocalizedNameKey: MemorySegment
    get() = NSWorkspaceVolumeOldLocalizedNameKey_VH.get(NSWorkspaceVolumeOldLocalizedNameKey_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceVolumeOldLocalizedNameKey_VH.set(NSWorkspaceVolumeOldLocalizedNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceVolumeOldURLKey (Void)*
 */
private val NSWorkspaceVolumeOldURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceVolumeOldURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceVolumeOldURLKey").orElseThrow() }
private val NSWorkspaceVolumeOldURLKey_VH: VarHandle by lazy { NSWorkspaceVolumeOldURLKey_LAYOUT.varHandle() }

var NSWorkspaceVolumeOldURLKey: MemorySegment
    get() = NSWorkspaceVolumeOldURLKey_VH.get(NSWorkspaceVolumeOldURLKey_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceVolumeOldURLKey_VH.set(NSWorkspaceVolumeOldURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidMountNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDidMountNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidMountNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidMountNotification").orElseThrow() }
private val NSWorkspaceDidMountNotification_VH: VarHandle by lazy { NSWorkspaceDidMountNotification_LAYOUT.varHandle() }

var NSWorkspaceDidMountNotification: MemorySegment
    get() = NSWorkspaceDidMountNotification_VH.get(NSWorkspaceDidMountNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidMountNotification_VH.set(NSWorkspaceDidMountNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidUnmountNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDidUnmountNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidUnmountNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidUnmountNotification").orElseThrow() }
private val NSWorkspaceDidUnmountNotification_VH: VarHandle by lazy { NSWorkspaceDidUnmountNotification_LAYOUT.varHandle() }

var NSWorkspaceDidUnmountNotification: MemorySegment
    get() = NSWorkspaceDidUnmountNotification_VH.get(NSWorkspaceDidUnmountNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidUnmountNotification_VH.set(NSWorkspaceDidUnmountNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceWillUnmountNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceWillUnmountNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceWillUnmountNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceWillUnmountNotification").orElseThrow() }
private val NSWorkspaceWillUnmountNotification_VH: VarHandle by lazy { NSWorkspaceWillUnmountNotification_LAYOUT.varHandle() }

var NSWorkspaceWillUnmountNotification: MemorySegment
    get() = NSWorkspaceWillUnmountNotification_VH.get(NSWorkspaceWillUnmountNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceWillUnmountNotification_VH.set(NSWorkspaceWillUnmountNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidRenameVolumeNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceDidRenameVolumeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidRenameVolumeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidRenameVolumeNotification").orElseThrow() }
private val NSWorkspaceDidRenameVolumeNotification_VH: VarHandle by lazy { NSWorkspaceDidRenameVolumeNotification_LAYOUT.varHandle() }

var NSWorkspaceDidRenameVolumeNotification: MemorySegment
    get() = NSWorkspaceDidRenameVolumeNotification_VH.get(NSWorkspaceDidRenameVolumeNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidRenameVolumeNotification_VH.set(NSWorkspaceDidRenameVolumeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceWillPowerOffNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceWillPowerOffNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceWillPowerOffNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceWillPowerOffNotification").orElseThrow() }
private val NSWorkspaceWillPowerOffNotification_VH: VarHandle by lazy { NSWorkspaceWillPowerOffNotification_LAYOUT.varHandle() }

var NSWorkspaceWillPowerOffNotification: MemorySegment
    get() = NSWorkspaceWillPowerOffNotification_VH.get(NSWorkspaceWillPowerOffNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceWillPowerOffNotification_VH.set(NSWorkspaceWillPowerOffNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceWillSleepNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceWillSleepNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceWillSleepNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceWillSleepNotification").orElseThrow() }
private val NSWorkspaceWillSleepNotification_VH: VarHandle by lazy { NSWorkspaceWillSleepNotification_LAYOUT.varHandle() }

var NSWorkspaceWillSleepNotification: MemorySegment
    get() = NSWorkspaceWillSleepNotification_VH.get(NSWorkspaceWillSleepNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceWillSleepNotification_VH.set(NSWorkspaceWillSleepNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidWakeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDidWakeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidWakeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidWakeNotification").orElseThrow() }
private val NSWorkspaceDidWakeNotification_VH: VarHandle by lazy { NSWorkspaceDidWakeNotification_LAYOUT.varHandle() }

var NSWorkspaceDidWakeNotification: MemorySegment
    get() = NSWorkspaceDidWakeNotification_VH.get(NSWorkspaceDidWakeNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidWakeNotification_VH.set(NSWorkspaceDidWakeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceScreensDidSleepNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceScreensDidSleepNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceScreensDidSleepNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceScreensDidSleepNotification").orElseThrow() }
private val NSWorkspaceScreensDidSleepNotification_VH: VarHandle by lazy { NSWorkspaceScreensDidSleepNotification_LAYOUT.varHandle() }

var NSWorkspaceScreensDidSleepNotification: MemorySegment
    get() = NSWorkspaceScreensDidSleepNotification_VH.get(NSWorkspaceScreensDidSleepNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceScreensDidSleepNotification_VH.set(NSWorkspaceScreensDidSleepNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceScreensDidWakeNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceScreensDidWakeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceScreensDidWakeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceScreensDidWakeNotification").orElseThrow() }
private val NSWorkspaceScreensDidWakeNotification_VH: VarHandle by lazy { NSWorkspaceScreensDidWakeNotification_LAYOUT.varHandle() }

var NSWorkspaceScreensDidWakeNotification: MemorySegment
    get() = NSWorkspaceScreensDidWakeNotification_VH.get(NSWorkspaceScreensDidWakeNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceScreensDidWakeNotification_VH.set(NSWorkspaceScreensDidWakeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceSessionDidBecomeActiveNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceSessionDidBecomeActiveNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceSessionDidBecomeActiveNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceSessionDidBecomeActiveNotification").orElseThrow() }
private val NSWorkspaceSessionDidBecomeActiveNotification_VH: VarHandle by lazy { NSWorkspaceSessionDidBecomeActiveNotification_LAYOUT.varHandle() }

var NSWorkspaceSessionDidBecomeActiveNotification: MemorySegment
    get() = NSWorkspaceSessionDidBecomeActiveNotification_VH.get(NSWorkspaceSessionDidBecomeActiveNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceSessionDidBecomeActiveNotification_VH.set(NSWorkspaceSessionDidBecomeActiveNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceSessionDidResignActiveNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceSessionDidResignActiveNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceSessionDidResignActiveNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceSessionDidResignActiveNotification").orElseThrow() }
private val NSWorkspaceSessionDidResignActiveNotification_VH: VarHandle by lazy { NSWorkspaceSessionDidResignActiveNotification_LAYOUT.varHandle() }

var NSWorkspaceSessionDidResignActiveNotification: MemorySegment
    get() = NSWorkspaceSessionDidResignActiveNotification_VH.get(NSWorkspaceSessionDidResignActiveNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceSessionDidResignActiveNotification_VH.set(NSWorkspaceSessionDidResignActiveNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidChangeFileLabelsNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceDidChangeFileLabelsNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidChangeFileLabelsNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidChangeFileLabelsNotification").orElseThrow() }
private val NSWorkspaceDidChangeFileLabelsNotification_VH: VarHandle by lazy { NSWorkspaceDidChangeFileLabelsNotification_LAYOUT.varHandle() }

var NSWorkspaceDidChangeFileLabelsNotification: MemorySegment
    get() = NSWorkspaceDidChangeFileLabelsNotification_VH.get(NSWorkspaceDidChangeFileLabelsNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidChangeFileLabelsNotification_VH.set(NSWorkspaceDidChangeFileLabelsNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceActiveSpaceDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceActiveSpaceDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceActiveSpaceDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceActiveSpaceDidChangeNotification").orElseThrow() }
private val NSWorkspaceActiveSpaceDidChangeNotification_VH: VarHandle by lazy { NSWorkspaceActiveSpaceDidChangeNotification_LAYOUT.varHandle() }

var NSWorkspaceActiveSpaceDidChangeNotification: MemorySegment
    get() = NSWorkspaceActiveSpaceDidChangeNotification_VH.get(NSWorkspaceActiveSpaceDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceActiveSpaceDidChangeNotification_VH.set(NSWorkspaceActiveSpaceDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceLaunchConfigurationAppleEvent typedef const NSWorkspaceLaunchConfigurationKey = (Void)*
 */
private val NSWorkspaceLaunchConfigurationAppleEvent_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceLaunchConfigurationAppleEvent_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceLaunchConfigurationAppleEvent").orElseThrow() }
private val NSWorkspaceLaunchConfigurationAppleEvent_VH: VarHandle by lazy { NSWorkspaceLaunchConfigurationAppleEvent_LAYOUT.varHandle() }

var NSWorkspaceLaunchConfigurationAppleEvent: MemorySegment
    get() = NSWorkspaceLaunchConfigurationAppleEvent_VH.get(NSWorkspaceLaunchConfigurationAppleEvent_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceLaunchConfigurationAppleEvent_VH.set(NSWorkspaceLaunchConfigurationAppleEvent_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceLaunchConfigurationArguments typedef const NSWorkspaceLaunchConfigurationKey = (Void)*
 */
private val NSWorkspaceLaunchConfigurationArguments_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceLaunchConfigurationArguments_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceLaunchConfigurationArguments").orElseThrow() }
private val NSWorkspaceLaunchConfigurationArguments_VH: VarHandle by lazy { NSWorkspaceLaunchConfigurationArguments_LAYOUT.varHandle() }

var NSWorkspaceLaunchConfigurationArguments: MemorySegment
    get() = NSWorkspaceLaunchConfigurationArguments_VH.get(NSWorkspaceLaunchConfigurationArguments_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceLaunchConfigurationArguments_VH.set(NSWorkspaceLaunchConfigurationArguments_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceLaunchConfigurationEnvironment typedef const NSWorkspaceLaunchConfigurationKey = (Void)*
 */
private val NSWorkspaceLaunchConfigurationEnvironment_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceLaunchConfigurationEnvironment_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceLaunchConfigurationEnvironment").orElseThrow() }
private val NSWorkspaceLaunchConfigurationEnvironment_VH: VarHandle by lazy { NSWorkspaceLaunchConfigurationEnvironment_LAYOUT.varHandle() }

var NSWorkspaceLaunchConfigurationEnvironment: MemorySegment
    get() = NSWorkspaceLaunchConfigurationEnvironment_VH.get(NSWorkspaceLaunchConfigurationEnvironment_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceLaunchConfigurationEnvironment_VH.set(NSWorkspaceLaunchConfigurationEnvironment_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceLaunchConfigurationArchitecture typedef const NSWorkspaceLaunchConfigurationKey = (Void)*
 */
private val NSWorkspaceLaunchConfigurationArchitecture_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceLaunchConfigurationArchitecture_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceLaunchConfigurationArchitecture").orElseThrow() }
private val NSWorkspaceLaunchConfigurationArchitecture_VH: VarHandle by lazy { NSWorkspaceLaunchConfigurationArchitecture_LAYOUT.varHandle() }

var NSWorkspaceLaunchConfigurationArchitecture: MemorySegment
    get() = NSWorkspaceLaunchConfigurationArchitecture_VH.get(NSWorkspaceLaunchConfigurationArchitecture_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceLaunchConfigurationArchitecture_VH.set(NSWorkspaceLaunchConfigurationArchitecture_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceMoveOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceMoveOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceMoveOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceMoveOperation").orElseThrow() }
private val NSWorkspaceMoveOperation_VH: VarHandle by lazy { NSWorkspaceMoveOperation_LAYOUT.varHandle() }

var NSWorkspaceMoveOperation: MemorySegment
    get() = NSWorkspaceMoveOperation_VH.get(NSWorkspaceMoveOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceMoveOperation_VH.set(NSWorkspaceMoveOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceCopyOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceCopyOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceCopyOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceCopyOperation").orElseThrow() }
private val NSWorkspaceCopyOperation_VH: VarHandle by lazy { NSWorkspaceCopyOperation_LAYOUT.varHandle() }

var NSWorkspaceCopyOperation: MemorySegment
    get() = NSWorkspaceCopyOperation_VH.get(NSWorkspaceCopyOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceCopyOperation_VH.set(NSWorkspaceCopyOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceLinkOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceLinkOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceLinkOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceLinkOperation").orElseThrow() }
private val NSWorkspaceLinkOperation_VH: VarHandle by lazy { NSWorkspaceLinkOperation_LAYOUT.varHandle() }

var NSWorkspaceLinkOperation: MemorySegment
    get() = NSWorkspaceLinkOperation_VH.get(NSWorkspaceLinkOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceLinkOperation_VH.set(NSWorkspaceLinkOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceCompressOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceCompressOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceCompressOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceCompressOperation").orElseThrow() }
private val NSWorkspaceCompressOperation_VH: VarHandle by lazy { NSWorkspaceCompressOperation_LAYOUT.varHandle() }

var NSWorkspaceCompressOperation: MemorySegment
    get() = NSWorkspaceCompressOperation_VH.get(NSWorkspaceCompressOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceCompressOperation_VH.set(NSWorkspaceCompressOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDecompressOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDecompressOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDecompressOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDecompressOperation").orElseThrow() }
private val NSWorkspaceDecompressOperation_VH: VarHandle by lazy { NSWorkspaceDecompressOperation_LAYOUT.varHandle() }

var NSWorkspaceDecompressOperation: MemorySegment
    get() = NSWorkspaceDecompressOperation_VH.get(NSWorkspaceDecompressOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDecompressOperation_VH.set(NSWorkspaceDecompressOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceEncryptOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceEncryptOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceEncryptOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceEncryptOperation").orElseThrow() }
private val NSWorkspaceEncryptOperation_VH: VarHandle by lazy { NSWorkspaceEncryptOperation_LAYOUT.varHandle() }

var NSWorkspaceEncryptOperation: MemorySegment
    get() = NSWorkspaceEncryptOperation_VH.get(NSWorkspaceEncryptOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceEncryptOperation_VH.set(NSWorkspaceEncryptOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDecryptOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDecryptOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDecryptOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDecryptOperation").orElseThrow() }
private val NSWorkspaceDecryptOperation_VH: VarHandle by lazy { NSWorkspaceDecryptOperation_LAYOUT.varHandle() }

var NSWorkspaceDecryptOperation: MemorySegment
    get() = NSWorkspaceDecryptOperation_VH.get(NSWorkspaceDecryptOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDecryptOperation_VH.set(NSWorkspaceDecryptOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDestroyOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDestroyOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDestroyOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDestroyOperation").orElseThrow() }
private val NSWorkspaceDestroyOperation_VH: VarHandle by lazy { NSWorkspaceDestroyOperation_LAYOUT.varHandle() }

var NSWorkspaceDestroyOperation: MemorySegment
    get() = NSWorkspaceDestroyOperation_VH.get(NSWorkspaceDestroyOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDestroyOperation_VH.set(NSWorkspaceDestroyOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceRecycleOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceRecycleOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceRecycleOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceRecycleOperation").orElseThrow() }
private val NSWorkspaceRecycleOperation_VH: VarHandle by lazy { NSWorkspaceRecycleOperation_LAYOUT.varHandle() }

var NSWorkspaceRecycleOperation: MemorySegment
    get() = NSWorkspaceRecycleOperation_VH.get(NSWorkspaceRecycleOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceRecycleOperation_VH.set(NSWorkspaceRecycleOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDuplicateOperation typedef NSWorkspaceFileOperationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDuplicateOperation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDuplicateOperation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDuplicateOperation").orElseThrow() }
private val NSWorkspaceDuplicateOperation_VH: VarHandle by lazy { NSWorkspaceDuplicateOperation_LAYOUT.varHandle() }

var NSWorkspaceDuplicateOperation: MemorySegment
    get() = NSWorkspaceDuplicateOperation_VH.get(NSWorkspaceDuplicateOperation_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDuplicateOperation_VH.set(NSWorkspaceDuplicateOperation_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceDidPerformFileOperationNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSWorkspaceDidPerformFileOperationNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceDidPerformFileOperationNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceDidPerformFileOperationNotification").orElseThrow() }
private val NSWorkspaceDidPerformFileOperationNotification_VH: VarHandle by lazy { NSWorkspaceDidPerformFileOperationNotification_LAYOUT.varHandle() }

var NSWorkspaceDidPerformFileOperationNotification: MemorySegment
    get() = NSWorkspaceDidPerformFileOperationNotification_VH.get(NSWorkspaceDidPerformFileOperationNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceDidPerformFileOperationNotification_VH.set(NSWorkspaceDidPerformFileOperationNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSPlainFileType typedef NSString = (Void)*
 */
private val NSPlainFileType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPlainFileType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPlainFileType").orElseThrow() }
private val NSPlainFileType_VH: VarHandle by lazy { NSPlainFileType_LAYOUT.varHandle() }

var NSPlainFileType: MemorySegment
    get() = NSPlainFileType_VH.get(NSPlainFileType_SEGMENT) as MemorySegment
    set(value) = NSPlainFileType_VH.set(NSPlainFileType_SEGMENT, value)

/**
 * {@snippet lang=c : NSDirectoryFileType typedef NSString = (Void)*
 */
private val NSDirectoryFileType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDirectoryFileType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDirectoryFileType").orElseThrow() }
private val NSDirectoryFileType_VH: VarHandle by lazy { NSDirectoryFileType_LAYOUT.varHandle() }

var NSDirectoryFileType: MemorySegment
    get() = NSDirectoryFileType_VH.get(NSDirectoryFileType_SEGMENT) as MemorySegment
    set(value) = NSDirectoryFileType_VH.set(NSDirectoryFileType_SEGMENT, value)

/**
 * {@snippet lang=c : NSApplicationFileType typedef NSString = (Void)*
 */
private val NSApplicationFileType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSApplicationFileType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSApplicationFileType").orElseThrow() }
private val NSApplicationFileType_VH: VarHandle by lazy { NSApplicationFileType_LAYOUT.varHandle() }

var NSApplicationFileType: MemorySegment
    get() = NSApplicationFileType_VH.get(NSApplicationFileType_SEGMENT) as MemorySegment
    set(value) = NSApplicationFileType_VH.set(NSApplicationFileType_SEGMENT, value)

/**
 * {@snippet lang=c : NSFilesystemFileType typedef NSString = (Void)*
 */
private val NSFilesystemFileType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFilesystemFileType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFilesystemFileType").orElseThrow() }
private val NSFilesystemFileType_VH: VarHandle by lazy { NSFilesystemFileType_LAYOUT.varHandle() }

var NSFilesystemFileType: MemorySegment
    get() = NSFilesystemFileType_VH.get(NSFilesystemFileType_SEGMENT) as MemorySegment
    set(value) = NSFilesystemFileType_VH.set(NSFilesystemFileType_SEGMENT, value)

/**
 * {@snippet lang=c : NSShellCommandFileType typedef NSString = (Void)*
 */
private val NSShellCommandFileType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSShellCommandFileType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSShellCommandFileType").orElseThrow() }
private val NSShellCommandFileType_VH: VarHandle by lazy { NSShellCommandFileType_LAYOUT.varHandle() }

var NSShellCommandFileType: MemorySegment
    get() = NSShellCommandFileType_VH.get(NSShellCommandFileType_SEGMENT) as MemorySegment
    set(value) = NSShellCommandFileType_VH.set(NSShellCommandFileType_SEGMENT, value)

/**
 * {@snippet lang=c : NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification").orElseThrow() }
private val NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification_VH: VarHandle by lazy { NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification_LAYOUT.varHandle() }

var NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification: MemorySegment
    get() = NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification_VH.get(NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification_VH.set(NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAccessibilityFrameInView typedef NSRect = Declared(CGRect)(typedef NSView = (Void)*,typedef NSRect = Declared(CGRect))
 */
private val NSAccessibilityFrameInView_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS, CGRect.layout)
private val NSAccessibilityFrameInView_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityFrameInView").orElseThrow()
private val NSAccessibilityFrameInView_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityFrameInView_ADDR, NSAccessibilityFrameInView_DESC)

fun NSAccessibilityFrameInView(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSAccessibilityFrameInView_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityPointInView typedef NSPoint = Declared(CGPoint)(typedef NSView = (Void)*,typedef NSPoint = Declared(CGPoint))
 */
private val NSAccessibilityPointInView_DESC: FunctionDescriptor = FunctionDescriptor.of(CGPoint.layout, ValueLayout.ADDRESS, CGPoint.layout)
private val NSAccessibilityPointInView_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityPointInView").orElseThrow()
private val NSAccessibilityPointInView_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityPointInView_ADDR, NSAccessibilityPointInView_DESC)

fun NSAccessibilityPointInView(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSAccessibilityPointInView_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilitySetMayContainProtectedContent typedef BOOL = Bool(typedef BOOL = Bool)
 */
private val NSAccessibilitySetMayContainProtectedContent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_BOOLEAN)
private val NSAccessibilitySetMayContainProtectedContent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilitySetMayContainProtectedContent").orElseThrow()
private val NSAccessibilitySetMayContainProtectedContent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilitySetMayContainProtectedContent_ADDR, NSAccessibilitySetMayContainProtectedContent_DESC)

fun NSAccessibilitySetMayContainProtectedContent(arg0: Boolean): Boolean {
    try {
        return NSAccessibilitySetMayContainProtectedContent_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityRoleDescription typedef NSString = (Void)*(typedef NSAccessibilityRole = typedef NSString = (Void)*,typedef NSAccessibilitySubrole = typedef NSString = (Void)*)
 */
private val NSAccessibilityRoleDescription_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityRoleDescription_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityRoleDescription").orElseThrow()
private val NSAccessibilityRoleDescription_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityRoleDescription_ADDR, NSAccessibilityRoleDescription_DESC)

fun NSAccessibilityRoleDescription(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSAccessibilityRoleDescription_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityRoleDescriptionForUIElement typedef NSString = (Void)*(typedef id = (Void)*)
 */
private val NSAccessibilityRoleDescriptionForUIElement_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityRoleDescriptionForUIElement_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityRoleDescriptionForUIElement").orElseThrow()
private val NSAccessibilityRoleDescriptionForUIElement_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityRoleDescriptionForUIElement_ADDR, NSAccessibilityRoleDescriptionForUIElement_DESC)

fun NSAccessibilityRoleDescriptionForUIElement(arg0: MemorySegment): MemorySegment {
    try {
        return NSAccessibilityRoleDescriptionForUIElement_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityActionDescription typedef NSString = (Void)*(typedef NSAccessibilityActionName = typedef NSString = (Void)*)
 */
private val NSAccessibilityActionDescription_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityActionDescription_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityActionDescription").orElseThrow()
private val NSAccessibilityActionDescription_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityActionDescription_ADDR, NSAccessibilityActionDescription_DESC)

fun NSAccessibilityActionDescription(arg0: MemorySegment): MemorySegment {
    try {
        return NSAccessibilityActionDescription_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityRaiseBadArgumentException Void(typedef id = (Void)*,typedef NSAccessibilityAttributeName = typedef NSString = (Void)*,typedef id = (Void)*)
 */
private val NSAccessibilityRaiseBadArgumentException_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityRaiseBadArgumentException_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityRaiseBadArgumentException").orElseThrow()
private val NSAccessibilityRaiseBadArgumentException_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityRaiseBadArgumentException_ADDR, NSAccessibilityRaiseBadArgumentException_DESC)

fun NSAccessibilityRaiseBadArgumentException(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        NSAccessibilityRaiseBadArgumentException_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityUnignoredAncestor typedef id = (Void)*(typedef id = (Void)*)
 */
private val NSAccessibilityUnignoredAncestor_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityUnignoredAncestor_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityUnignoredAncestor").orElseThrow()
private val NSAccessibilityUnignoredAncestor_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityUnignoredAncestor_ADDR, NSAccessibilityUnignoredAncestor_DESC)

fun NSAccessibilityUnignoredAncestor(arg0: MemorySegment): MemorySegment {
    try {
        return NSAccessibilityUnignoredAncestor_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityUnignoredDescendant typedef id = (Void)*(typedef id = (Void)*)
 */
private val NSAccessibilityUnignoredDescendant_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityUnignoredDescendant_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityUnignoredDescendant").orElseThrow()
private val NSAccessibilityUnignoredDescendant_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityUnignoredDescendant_ADDR, NSAccessibilityUnignoredDescendant_DESC)

fun NSAccessibilityUnignoredDescendant(arg0: MemorySegment): MemorySegment {
    try {
        return NSAccessibilityUnignoredDescendant_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityUnignoredChildren typedef NSArray = (Void)*(typedef NSArray = (Void)*)
 */
private val NSAccessibilityUnignoredChildren_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityUnignoredChildren_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityUnignoredChildren").orElseThrow()
private val NSAccessibilityUnignoredChildren_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityUnignoredChildren_ADDR, NSAccessibilityUnignoredChildren_DESC)

fun NSAccessibilityUnignoredChildren(arg0: MemorySegment): MemorySegment {
    try {
        return NSAccessibilityUnignoredChildren_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityUnignoredChildrenForOnlyChild typedef NSArray = (Void)*(typedef id = (Void)*)
 */
private val NSAccessibilityUnignoredChildrenForOnlyChild_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityUnignoredChildrenForOnlyChild_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityUnignoredChildrenForOnlyChild").orElseThrow()
private val NSAccessibilityUnignoredChildrenForOnlyChild_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityUnignoredChildrenForOnlyChild_ADDR, NSAccessibilityUnignoredChildrenForOnlyChild_DESC)

fun NSAccessibilityUnignoredChildrenForOnlyChild(arg0: MemorySegment): MemorySegment {
    try {
        return NSAccessibilityUnignoredChildrenForOnlyChild_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAccessibilityPostNotification Void(typedef id = (Void)*,typedef NSAccessibilityNotificationName = typedef NSString = (Void)*)
 */
private val NSAccessibilityPostNotification_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAccessibilityPostNotification_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAccessibilityPostNotification").orElseThrow()
private val NSAccessibilityPostNotification_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAccessibilityPostNotification_ADDR, NSAccessibilityPostNotification_DESC)

fun NSAccessibilityPostNotification(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSAccessibilityPostNotification_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRenderingBufferProviderCreate typedef CGRenderingBufferProviderRef = (Declared(CGRenderingBufferProvider))*((Void)*,typedef size_t = UNSIGNED = Long,(Void)*,(Void)*,(Void)*)
 */
private val CGRenderingBufferProviderCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGRenderingBufferProviderCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRenderingBufferProviderCreate").orElseThrow()
private val CGRenderingBufferProviderCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRenderingBufferProviderCreate_ADDR, CGRenderingBufferProviderCreate_DESC)

fun CGRenderingBufferProviderCreate(arg0: MemorySegment, arg1: Long, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment): MemorySegment {
    try {
        return CGRenderingBufferProviderCreate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRenderingBufferProviderCreateWithCFData typedef CGRenderingBufferProviderRef = (Declared(CGRenderingBufferProvider))*(typedef CFMutableDataRef = (Declared(__CFData))*)
 */
private val CGRenderingBufferProviderCreateWithCFData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGRenderingBufferProviderCreateWithCFData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRenderingBufferProviderCreateWithCFData").orElseThrow()
private val CGRenderingBufferProviderCreateWithCFData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRenderingBufferProviderCreateWithCFData_ADDR, CGRenderingBufferProviderCreateWithCFData_DESC)

fun CGRenderingBufferProviderCreateWithCFData(arg0: MemorySegment): MemorySegment {
    try {
        return CGRenderingBufferProviderCreateWithCFData_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRenderingBufferProviderGetSize typedef size_t = UNSIGNED = Long(typedef CGRenderingBufferProviderRef = (Declared(CGRenderingBufferProvider))*)
 */
private val CGRenderingBufferProviderGetSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGRenderingBufferProviderGetSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRenderingBufferProviderGetSize").orElseThrow()
private val CGRenderingBufferProviderGetSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRenderingBufferProviderGetSize_ADDR, CGRenderingBufferProviderGetSize_DESC)

fun CGRenderingBufferProviderGetSize(arg0: MemorySegment): Long {
    try {
        return CGRenderingBufferProviderGetSize_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRenderingBufferLockBytePtr (Void)*(typedef CGRenderingBufferProviderRef = (Declared(CGRenderingBufferProvider))*)
 */
private val CGRenderingBufferLockBytePtr_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGRenderingBufferLockBytePtr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRenderingBufferLockBytePtr").orElseThrow()
private val CGRenderingBufferLockBytePtr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRenderingBufferLockBytePtr_ADDR, CGRenderingBufferLockBytePtr_DESC)

fun CGRenderingBufferLockBytePtr(arg0: MemorySegment): MemorySegment {
    try {
        return CGRenderingBufferLockBytePtr_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRenderingBufferUnlockBytePtr Void(typedef CGRenderingBufferProviderRef = (Declared(CGRenderingBufferProvider))*)
 */
private val CGRenderingBufferUnlockBytePtr_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGRenderingBufferUnlockBytePtr_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRenderingBufferUnlockBytePtr").orElseThrow()
private val CGRenderingBufferUnlockBytePtr_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRenderingBufferUnlockBytePtr_ADDR, CGRenderingBufferUnlockBytePtr_DESC)

fun CGRenderingBufferUnlockBytePtr(arg0: MemorySegment): Unit {
    try {
        CGRenderingBufferUnlockBytePtr_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRenderingBufferProviderGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGRenderingBufferProviderGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGRenderingBufferProviderGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRenderingBufferProviderGetTypeID").orElseThrow()
private val CGRenderingBufferProviderGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRenderingBufferProviderGetTypeID_ADDR, CGRenderingBufferProviderGetTypeID_DESC)

fun CGRenderingBufferProviderGetTypeID(): Long {
    try {
        return CGRenderingBufferProviderGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGBitmapContextCreateAdaptive typedef CGContextRef = (Declared(CGContext))*(typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef CFDictionaryRef = (Declared(__CFDictionary))*,(Void)*,(Void)*,(Void)*,(Void)*)
 */
private val CGBitmapContextCreateAdaptive_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGBitmapContextCreateAdaptive_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGBitmapContextCreateAdaptive").orElseThrow()
private val CGBitmapContextCreateAdaptive_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGBitmapContextCreateAdaptive_ADDR, CGBitmapContextCreateAdaptive_DESC)

fun CGBitmapContextCreateAdaptive(arg0: Long, arg1: Long, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment, arg6: MemorySegment): MemorySegment {
    try {
        return CGBitmapContextCreateAdaptive_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGAdaptiveMaximumBitDepth typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGAdaptiveMaximumBitDepth_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGAdaptiveMaximumBitDepth_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGAdaptiveMaximumBitDepth").orElseThrow() }
private val kCGAdaptiveMaximumBitDepth_VH: VarHandle by lazy { kCGAdaptiveMaximumBitDepth_LAYOUT.varHandle() }

var kCGAdaptiveMaximumBitDepth: MemorySegment
    get() = kCGAdaptiveMaximumBitDepth_VH.get(kCGAdaptiveMaximumBitDepth_SEGMENT) as MemorySegment
    set(value) = kCGAdaptiveMaximumBitDepth_VH.set(kCGAdaptiveMaximumBitDepth_SEGMENT, value)

/**
 * {@snippet lang=c : CGBitmapContextGetData (Void)*(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGBitmapContextGetData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGBitmapContextGetData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGBitmapContextGetData").orElseThrow()
private val CGBitmapContextGetData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGBitmapContextGetData_ADDR, CGBitmapContextGetData_DESC)

fun CGBitmapContextGetData(arg0: MemorySegment): MemorySegment {
    try {
        return CGBitmapContextGetData_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGBitmapContextGetWidth typedef size_t = UNSIGNED = Long(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGBitmapContextGetWidth_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGBitmapContextGetWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGBitmapContextGetWidth").orElseThrow()
private val CGBitmapContextGetWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGBitmapContextGetWidth_ADDR, CGBitmapContextGetWidth_DESC)

fun CGBitmapContextGetWidth(arg0: MemorySegment): Long {
    try {
        return CGBitmapContextGetWidth_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGBitmapContextGetHeight typedef size_t = UNSIGNED = Long(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGBitmapContextGetHeight_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGBitmapContextGetHeight_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGBitmapContextGetHeight").orElseThrow()
private val CGBitmapContextGetHeight_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGBitmapContextGetHeight_ADDR, CGBitmapContextGetHeight_DESC)

fun CGBitmapContextGetHeight(arg0: MemorySegment): Long {
    try {
        return CGBitmapContextGetHeight_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGBitmapContextGetBitsPerComponent typedef size_t = UNSIGNED = Long(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGBitmapContextGetBitsPerComponent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGBitmapContextGetBitsPerComponent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGBitmapContextGetBitsPerComponent").orElseThrow()
private val CGBitmapContextGetBitsPerComponent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGBitmapContextGetBitsPerComponent_ADDR, CGBitmapContextGetBitsPerComponent_DESC)

fun CGBitmapContextGetBitsPerComponent(arg0: MemorySegment): Long {
    try {
        return CGBitmapContextGetBitsPerComponent_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGBitmapContextGetBitsPerPixel typedef size_t = UNSIGNED = Long(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGBitmapContextGetBitsPerPixel_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGBitmapContextGetBitsPerPixel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGBitmapContextGetBitsPerPixel").orElseThrow()
private val CGBitmapContextGetBitsPerPixel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGBitmapContextGetBitsPerPixel_ADDR, CGBitmapContextGetBitsPerPixel_DESC)

fun CGBitmapContextGetBitsPerPixel(arg0: MemorySegment): Long {
    try {
        return CGBitmapContextGetBitsPerPixel_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGBitmapContextGetBytesPerRow typedef size_t = UNSIGNED = Long(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGBitmapContextGetBytesPerRow_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGBitmapContextGetBytesPerRow_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGBitmapContextGetBytesPerRow").orElseThrow()
private val CGBitmapContextGetBytesPerRow_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGBitmapContextGetBytesPerRow_ADDR, CGBitmapContextGetBytesPerRow_DESC)

fun CGBitmapContextGetBytesPerRow(arg0: MemorySegment): Long {
    try {
        return CGBitmapContextGetBytesPerRow_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGBitmapContextGetColorSpace typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGBitmapContextGetColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGBitmapContextGetColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGBitmapContextGetColorSpace").orElseThrow()
private val CGBitmapContextGetColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGBitmapContextGetColorSpace_ADDR, CGBitmapContextGetColorSpace_DESC)

fun CGBitmapContextGetColorSpace(arg0: MemorySegment): MemorySegment {
    try {
        return CGBitmapContextGetColorSpace_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGBitmapContextCreateImage typedef CGImageRef = (Declared(CGImage))*(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGBitmapContextCreateImage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGBitmapContextCreateImage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGBitmapContextCreateImage").orElseThrow()
private val CGBitmapContextCreateImage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGBitmapContextCreateImage_ADDR, CGBitmapContextCreateImage_DESC)

fun CGBitmapContextCreateImage(arg0: MemorySegment): MemorySegment {
    try {
        return CGBitmapContextCreateImage_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorConversionInfoGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGColorConversionInfoGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGColorConversionInfoGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorConversionInfoGetTypeID").orElseThrow()
private val CGColorConversionInfoGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorConversionInfoGetTypeID_ADDR, CGColorConversionInfoGetTypeID_DESC)

fun CGColorConversionInfoGetTypeID(): Long {
    try {
        return CGColorConversionInfoGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorConversionInfoCreate typedef CGColorConversionInfoRef = (Declared(CGColorConversionInfo))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CGColorSpaceRef = (Declared(CGColorSpace))*)
 */
private val CGColorConversionInfoCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorConversionInfoCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorConversionInfoCreate").orElseThrow()
private val CGColorConversionInfoCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorConversionInfoCreate_ADDR, CGColorConversionInfoCreate_DESC)

fun CGColorConversionInfoCreate(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGColorConversionInfoCreate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorConversionInfoCreateWithOptions typedef CGColorConversionInfoRef = (Declared(CGColorConversionInfo))*(typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CGColorSpaceRef = (Declared(CGColorSpace))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGColorConversionInfoCreateWithOptions_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGColorConversionInfoCreateWithOptions_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorConversionInfoCreateWithOptions").orElseThrow()
private val CGColorConversionInfoCreateWithOptions_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorConversionInfoCreateWithOptions_ADDR, CGColorConversionInfoCreateWithOptions_DESC)

fun CGColorConversionInfoCreateWithOptions(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGColorConversionInfoCreateWithOptions_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGColorConversionInfoConvertData Bool(typedef CGColorConversionInfoRef = (Declared(CGColorConversionInfo))*,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,(Void)*,typedef CGColorBufferFormat = Declared(CGColorBufferFormat),(Void)*,typedef CGColorBufferFormat = Declared(CGColorBufferFormat),typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGColorConversionInfoConvertData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CGColorBufferFormat.layout, ValueLayout.ADDRESS, CGColorBufferFormat.layout, ValueLayout.ADDRESS)
private val CGColorConversionInfoConvertData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGColorConversionInfoConvertData").orElseThrow()
private val CGColorConversionInfoConvertData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGColorConversionInfoConvertData_ADDR, CGColorConversionInfoConvertData_DESC)

fun CGColorConversionInfoConvertData(arg0: MemorySegment, arg1: Long, arg2: Long, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment, arg6: MemorySegment, arg7: MemorySegment): Boolean {
    try {
        return CGColorConversionInfoConvertData_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGColorConversionBlackPointCompensation typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorConversionBlackPointCompensation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorConversionBlackPointCompensation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorConversionBlackPointCompensation").orElseThrow() }
private val kCGColorConversionBlackPointCompensation_VH: VarHandle by lazy { kCGColorConversionBlackPointCompensation_LAYOUT.varHandle() }

var kCGColorConversionBlackPointCompensation: MemorySegment
    get() = kCGColorConversionBlackPointCompensation_VH.get(kCGColorConversionBlackPointCompensation_SEGMENT) as MemorySegment
    set(value) = kCGColorConversionBlackPointCompensation_VH.set(kCGColorConversionBlackPointCompensation_SEGMENT, value)

/**
 * {@snippet lang=c : kCGColorConversionTRCSize typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGColorConversionTRCSize_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGColorConversionTRCSize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGColorConversionTRCSize").orElseThrow() }
private val kCGColorConversionTRCSize_VH: VarHandle by lazy { kCGColorConversionTRCSize_LAYOUT.varHandle() }

var kCGColorConversionTRCSize: MemorySegment
    get() = kCGColorConversionTRCSize_VH.get(kCGColorConversionTRCSize_SEGMENT) as MemorySegment
    set(value) = kCGColorConversionTRCSize_VH.set(kCGColorConversionTRCSize_SEGMENT, value)

/**
 * {@snippet lang=c : CGConvertColorDataWithFormat Bool(typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,(Void)*,typedef CGColorDataFormat = Declared(CGColorDataFormat),(Void)*,typedef CGColorDataFormat = Declared(CGColorDataFormat),typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGConvertColorDataWithFormat_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, CGColorDataFormat.layout, ValueLayout.ADDRESS, CGColorDataFormat.layout, ValueLayout.ADDRESS)
private val CGConvertColorDataWithFormat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGConvertColorDataWithFormat").orElseThrow()
private val CGConvertColorDataWithFormat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGConvertColorDataWithFormat_ADDR, CGConvertColorDataWithFormat_DESC)

fun CGConvertColorDataWithFormat(arg0: Long, arg1: Long, arg2: MemorySegment, arg3: MemorySegment, arg4: MemorySegment, arg5: MemorySegment, arg6: MemorySegment): Boolean {
    try {
        return CGConvertColorDataWithFormat_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5, arg6) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataConsumerGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGDataConsumerGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGDataConsumerGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataConsumerGetTypeID").orElseThrow()
private val CGDataConsumerGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataConsumerGetTypeID_ADDR, CGDataConsumerGetTypeID_DESC)

fun CGDataConsumerGetTypeID(): Long {
    try {
        return CGDataConsumerGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataConsumerCreate typedef CGDataConsumerRef = (Declared(CGDataConsumer))*((Void)*,(typedef CGDataConsumerCallbacks = Declared(CGDataConsumerCallbacks))*)
 */
private val CGDataConsumerCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataConsumerCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataConsumerCreate").orElseThrow()
private val CGDataConsumerCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataConsumerCreate_ADDR, CGDataConsumerCreate_DESC)

fun CGDataConsumerCreate(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGDataConsumerCreate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataConsumerCreateWithURL typedef CGDataConsumerRef = (Declared(CGDataConsumer))*(typedef CFURLRef = (Declared(__CFURL))*)
 */
private val CGDataConsumerCreateWithURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataConsumerCreateWithURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataConsumerCreateWithURL").orElseThrow()
private val CGDataConsumerCreateWithURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataConsumerCreateWithURL_ADDR, CGDataConsumerCreateWithURL_DESC)

fun CGDataConsumerCreateWithURL(arg0: MemorySegment): MemorySegment {
    try {
        return CGDataConsumerCreateWithURL_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataConsumerCreateWithCFData typedef CGDataConsumerRef = (Declared(CGDataConsumer))*(typedef CFMutableDataRef = (Declared(__CFData))*)
 */
private val CGDataConsumerCreateWithCFData_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataConsumerCreateWithCFData_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataConsumerCreateWithCFData").orElseThrow()
private val CGDataConsumerCreateWithCFData_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataConsumerCreateWithCFData_ADDR, CGDataConsumerCreateWithCFData_DESC)

fun CGDataConsumerCreateWithCFData(arg0: MemorySegment): MemorySegment {
    try {
        return CGDataConsumerCreateWithCFData_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataConsumerRetain typedef CGDataConsumerRef = (Declared(CGDataConsumer))*(typedef CGDataConsumerRef = (Declared(CGDataConsumer))*)
 */
private val CGDataConsumerRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDataConsumerRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataConsumerRetain").orElseThrow()
private val CGDataConsumerRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataConsumerRetain_ADDR, CGDataConsumerRetain_DESC)

fun CGDataConsumerRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGDataConsumerRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDataConsumerRelease Void(typedef CGDataConsumerRef = (Declared(CGDataConsumer))*)
 */
private val CGDataConsumerRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGDataConsumerRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDataConsumerRelease").orElseThrow()
private val CGDataConsumerRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDataConsumerRelease_ADDR, CGDataConsumerRelease_DESC)

fun CGDataConsumerRelease(arg0: MemorySegment): Unit {
    try {
        CGDataConsumerRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGErrorSetCallback Void(typedef CGErrorCallback = (Void())*)
 */
private val CGErrorSetCallback_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGErrorSetCallback_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGErrorSetCallback").orElseThrow()
private val CGErrorSetCallback_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGErrorSetCallback_ADDR, CGErrorSetCallback_DESC)

fun CGErrorSetCallback(arg0: MemorySegment): Unit {
    try {
        CGErrorSetCallback_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGLayerCreateWithContext typedef CGLayerRef = (Declared(CGLayer))*(typedef CGContextRef = (Declared(CGContext))*,typedef CGSize = Declared(CGSize),typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGLayerCreateWithContext_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGSize.layout, ValueLayout.ADDRESS)
private val CGLayerCreateWithContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGLayerCreateWithContext").orElseThrow()
private val CGLayerCreateWithContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGLayerCreateWithContext_ADDR, CGLayerCreateWithContext_DESC)

fun CGLayerCreateWithContext(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGLayerCreateWithContext_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGLayerRetain typedef CGLayerRef = (Declared(CGLayer))*(typedef CGLayerRef = (Declared(CGLayer))*)
 */
private val CGLayerRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGLayerRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGLayerRetain").orElseThrow()
private val CGLayerRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGLayerRetain_ADDR, CGLayerRetain_DESC)

fun CGLayerRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGLayerRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGLayerRelease Void(typedef CGLayerRef = (Declared(CGLayer))*)
 */
private val CGLayerRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGLayerRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGLayerRelease").orElseThrow()
private val CGLayerRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGLayerRelease_ADDR, CGLayerRelease_DESC)

fun CGLayerRelease(arg0: MemorySegment): Unit {
    try {
        CGLayerRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGLayerGetSize typedef CGSize = Declared(CGSize)(typedef CGLayerRef = (Declared(CGLayer))*)
 */
private val CGLayerGetSize_DESC: FunctionDescriptor = FunctionDescriptor.of(CGSize.layout, ValueLayout.ADDRESS)
private val CGLayerGetSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGLayerGetSize").orElseThrow()
private val CGLayerGetSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGLayerGetSize_ADDR, CGLayerGetSize_DESC)

fun CGLayerGetSize(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGLayerGetSize_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGLayerGetContext typedef CGContextRef = (Declared(CGContext))*(typedef CGLayerRef = (Declared(CGLayer))*)
 */
private val CGLayerGetContext_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGLayerGetContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGLayerGetContext").orElseThrow()
private val CGLayerGetContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGLayerGetContext_ADDR, CGLayerGetContext_DESC)

fun CGLayerGetContext(arg0: MemorySegment): MemorySegment {
    try {
        return CGLayerGetContext_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextDrawLayerInRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGRect = Declared(CGRect),typedef CGLayerRef = (Declared(CGLayer))*)
 */
private val CGContextDrawLayerInRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGRect.layout, ValueLayout.ADDRESS)
private val CGContextDrawLayerInRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextDrawLayerInRect").orElseThrow()
private val CGContextDrawLayerInRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextDrawLayerInRect_ADDR, CGContextDrawLayerInRect_DESC)

fun CGContextDrawLayerInRect(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGContextDrawLayerInRect_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGContextDrawLayerAtPoint Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGPoint = Declared(CGPoint),typedef CGLayerRef = (Declared(CGLayer))*)
 */
private val CGContextDrawLayerAtPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, CGPoint.layout, ValueLayout.ADDRESS)
private val CGContextDrawLayerAtPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGContextDrawLayerAtPoint").orElseThrow()
private val CGContextDrawLayerAtPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGContextDrawLayerAtPoint_ADDR, CGContextDrawLayerAtPoint_DESC)

fun CGContextDrawLayerAtPoint(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGContextDrawLayerAtPoint_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGLayerGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGLayerGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGLayerGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGLayerGetTypeID").orElseThrow()
private val CGLayerGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGLayerGetTypeID_ADDR, CGLayerGetTypeID_DESC)

fun CGLayerGetTypeID(): Long {
    try {
        return CGLayerGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContentStreamCreateWithPage typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*(typedef CGPDFPageRef = (Declared(CGPDFPage))*)
 */
private val CGPDFContentStreamCreateWithPage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContentStreamCreateWithPage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContentStreamCreateWithPage").orElseThrow()
private val CGPDFContentStreamCreateWithPage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContentStreamCreateWithPage_ADDR, CGPDFContentStreamCreateWithPage_DESC)

fun CGPDFContentStreamCreateWithPage(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFContentStreamCreateWithPage_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContentStreamCreateWithStream typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*(typedef CGPDFStreamRef = (Declared(CGPDFStream))*,typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*,typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*)
 */
private val CGPDFContentStreamCreateWithStream_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContentStreamCreateWithStream_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContentStreamCreateWithStream").orElseThrow()
private val CGPDFContentStreamCreateWithStream_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContentStreamCreateWithStream_ADDR, CGPDFContentStreamCreateWithStream_DESC)

fun CGPDFContentStreamCreateWithStream(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGPDFContentStreamCreateWithStream_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContentStreamRetain typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*(typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*)
 */
private val CGPDFContentStreamRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContentStreamRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContentStreamRetain").orElseThrow()
private val CGPDFContentStreamRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContentStreamRetain_ADDR, CGPDFContentStreamRetain_DESC)

fun CGPDFContentStreamRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFContentStreamRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContentStreamRelease Void(typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*)
 */
private val CGPDFContentStreamRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPDFContentStreamRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContentStreamRelease").orElseThrow()
private val CGPDFContentStreamRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContentStreamRelease_ADDR, CGPDFContentStreamRelease_DESC)

fun CGPDFContentStreamRelease(arg0: MemorySegment): Unit {
    try {
        CGPDFContentStreamRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContentStreamGetStreams typedef CFArrayRef = (Declared(__CFArray))*(typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*)
 */
private val CGPDFContentStreamGetStreams_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContentStreamGetStreams_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContentStreamGetStreams").orElseThrow()
private val CGPDFContentStreamGetStreams_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContentStreamGetStreams_ADDR, CGPDFContentStreamGetStreams_DESC)

fun CGPDFContentStreamGetStreams(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFContentStreamGetStreams_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContentStreamGetResource typedef CGPDFObjectRef = (Declared(CGPDFObject))*(typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*,(Char)*,(Char)*)
 */
private val CGPDFContentStreamGetResource_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContentStreamGetResource_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContentStreamGetResource").orElseThrow()
private val CGPDFContentStreamGetResource_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContentStreamGetResource_ADDR, CGPDFContentStreamGetResource_DESC)

fun CGPDFContentStreamGetResource(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGPDFContentStreamGetResource_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextCreate typedef CGContextRef = (Declared(CGContext))*(typedef CGDataConsumerRef = (Declared(CGDataConsumer))*,(typedef CGRect = Declared(CGRect))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGPDFContextCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContextCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextCreate").orElseThrow()
private val CGPDFContextCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextCreate_ADDR, CGPDFContextCreate_DESC)

fun CGPDFContextCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGPDFContextCreate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextCreateWithURL typedef CGContextRef = (Declared(CGContext))*(typedef CFURLRef = (Declared(__CFURL))*,(typedef CGRect = Declared(CGRect))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGPDFContextCreateWithURL_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContextCreateWithURL_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextCreateWithURL").orElseThrow()
private val CGPDFContextCreateWithURL_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextCreateWithURL_ADDR, CGPDFContextCreateWithURL_DESC)

fun CGPDFContextCreateWithURL(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGPDFContextCreateWithURL_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextClose Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGPDFContextClose_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPDFContextClose_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextClose").orElseThrow()
private val CGPDFContextClose_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextClose_ADDR, CGPDFContextClose_DESC)

fun CGPDFContextClose(arg0: MemorySegment): Unit {
    try {
        CGPDFContextClose_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextBeginPage Void(typedef CGContextRef = (Declared(CGContext))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGPDFContextBeginPage_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContextBeginPage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextBeginPage").orElseThrow()
private val CGPDFContextBeginPage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextBeginPage_ADDR, CGPDFContextBeginPage_DESC)

fun CGPDFContextBeginPage(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGPDFContextBeginPage_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextEndPage Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGPDFContextEndPage_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPDFContextEndPage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextEndPage").orElseThrow()
private val CGPDFContextEndPage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextEndPage_ADDR, CGPDFContextEndPage_DESC)

fun CGPDFContextEndPage(arg0: MemorySegment): Unit {
    try {
        CGPDFContextEndPage_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextAddDocumentMetadata Void(typedef CGContextRef = (Declared(CGContext))*,typedef CFDataRef = (Declared(__CFData))*)
 */
private val CGPDFContextAddDocumentMetadata_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContextAddDocumentMetadata_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextAddDocumentMetadata").orElseThrow()
private val CGPDFContextAddDocumentMetadata_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextAddDocumentMetadata_ADDR, CGPDFContextAddDocumentMetadata_DESC)

fun CGPDFContextAddDocumentMetadata(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGPDFContextAddDocumentMetadata_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextSetParentTree Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*)
 */
private val CGPDFContextSetParentTree_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContextSetParentTree_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextSetParentTree").orElseThrow()
private val CGPDFContextSetParentTree_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextSetParentTree_ADDR, CGPDFContextSetParentTree_DESC)

fun CGPDFContextSetParentTree(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGPDFContextSetParentTree_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextSetIDTree Void(typedef CGContextRef = (Declared(CGContext))*,typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*)
 */
private val CGPDFContextSetIDTree_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContextSetIDTree_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextSetIDTree").orElseThrow()
private val CGPDFContextSetIDTree_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextSetIDTree_ADDR, CGPDFContextSetIDTree_DESC)

fun CGPDFContextSetIDTree(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGPDFContextSetIDTree_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextSetPageTagStructureTree Void(typedef CGContextRef = (Declared(CGContext))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGPDFContextSetPageTagStructureTree_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContextSetPageTagStructureTree_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextSetPageTagStructureTree").orElseThrow()
private val CGPDFContextSetPageTagStructureTree_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextSetPageTagStructureTree_ADDR, CGPDFContextSetPageTagStructureTree_DESC)

fun CGPDFContextSetPageTagStructureTree(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGPDFContextSetPageTagStructureTree_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextSetURLForRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CFURLRef = (Declared(__CFURL))*,typedef CGRect = Declared(CGRect))
 */
private val CGPDFContextSetURLForRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGRect.layout)
private val CGPDFContextSetURLForRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextSetURLForRect").orElseThrow()
private val CGPDFContextSetURLForRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextSetURLForRect_ADDR, CGPDFContextSetURLForRect_DESC)

fun CGPDFContextSetURLForRect(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPDFContextSetURLForRect_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextAddDestinationAtPoint Void(typedef CGContextRef = (Declared(CGContext))*,typedef CFStringRef = (Declared(__CFString))*,typedef CGPoint = Declared(CGPoint))
 */
private val CGPDFContextAddDestinationAtPoint_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGPoint.layout)
private val CGPDFContextAddDestinationAtPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextAddDestinationAtPoint").orElseThrow()
private val CGPDFContextAddDestinationAtPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextAddDestinationAtPoint_ADDR, CGPDFContextAddDestinationAtPoint_DESC)

fun CGPDFContextAddDestinationAtPoint(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPDFContextAddDestinationAtPoint_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFContextSetDestinationForRect Void(typedef CGContextRef = (Declared(CGContext))*,typedef CFStringRef = (Declared(__CFString))*,typedef CGRect = Declared(CGRect))
 */
private val CGPDFContextSetDestinationForRect_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, CGRect.layout)
private val CGPDFContextSetDestinationForRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextSetDestinationForRect").orElseThrow()
private val CGPDFContextSetDestinationForRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextSetDestinationForRect_ADDR, CGPDFContextSetDestinationForRect_DESC)

fun CGPDFContextSetDestinationForRect(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPDFContextSetDestinationForRect_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGPDFContextMediaBox typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextMediaBox_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextMediaBox_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextMediaBox").orElseThrow() }
private val kCGPDFContextMediaBox_VH: VarHandle by lazy { kCGPDFContextMediaBox_LAYOUT.varHandle() }

var kCGPDFContextMediaBox: MemorySegment
    get() = kCGPDFContextMediaBox_VH.get(kCGPDFContextMediaBox_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextMediaBox_VH.set(kCGPDFContextMediaBox_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextCropBox typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextCropBox_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextCropBox_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextCropBox").orElseThrow() }
private val kCGPDFContextCropBox_VH: VarHandle by lazy { kCGPDFContextCropBox_LAYOUT.varHandle() }

var kCGPDFContextCropBox: MemorySegment
    get() = kCGPDFContextCropBox_VH.get(kCGPDFContextCropBox_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextCropBox_VH.set(kCGPDFContextCropBox_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextBleedBox typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextBleedBox_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextBleedBox_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextBleedBox").orElseThrow() }
private val kCGPDFContextBleedBox_VH: VarHandle by lazy { kCGPDFContextBleedBox_LAYOUT.varHandle() }

var kCGPDFContextBleedBox: MemorySegment
    get() = kCGPDFContextBleedBox_VH.get(kCGPDFContextBleedBox_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextBleedBox_VH.set(kCGPDFContextBleedBox_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextTrimBox typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextTrimBox_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextTrimBox_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextTrimBox").orElseThrow() }
private val kCGPDFContextTrimBox_VH: VarHandle by lazy { kCGPDFContextTrimBox_LAYOUT.varHandle() }

var kCGPDFContextTrimBox: MemorySegment
    get() = kCGPDFContextTrimBox_VH.get(kCGPDFContextTrimBox_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextTrimBox_VH.set(kCGPDFContextTrimBox_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextArtBox typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextArtBox_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextArtBox_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextArtBox").orElseThrow() }
private val kCGPDFContextArtBox_VH: VarHandle by lazy { kCGPDFContextArtBox_LAYOUT.varHandle() }

var kCGPDFContextArtBox: MemorySegment
    get() = kCGPDFContextArtBox_VH.get(kCGPDFContextArtBox_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextArtBox_VH.set(kCGPDFContextArtBox_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextTitle typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextTitle_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextTitle_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextTitle").orElseThrow() }
private val kCGPDFContextTitle_VH: VarHandle by lazy { kCGPDFContextTitle_LAYOUT.varHandle() }

var kCGPDFContextTitle: MemorySegment
    get() = kCGPDFContextTitle_VH.get(kCGPDFContextTitle_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextTitle_VH.set(kCGPDFContextTitle_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextAuthor typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextAuthor_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextAuthor_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextAuthor").orElseThrow() }
private val kCGPDFContextAuthor_VH: VarHandle by lazy { kCGPDFContextAuthor_LAYOUT.varHandle() }

var kCGPDFContextAuthor: MemorySegment
    get() = kCGPDFContextAuthor_VH.get(kCGPDFContextAuthor_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextAuthor_VH.set(kCGPDFContextAuthor_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextSubject typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextSubject_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextSubject_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextSubject").orElseThrow() }
private val kCGPDFContextSubject_VH: VarHandle by lazy { kCGPDFContextSubject_LAYOUT.varHandle() }

var kCGPDFContextSubject: MemorySegment
    get() = kCGPDFContextSubject_VH.get(kCGPDFContextSubject_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextSubject_VH.set(kCGPDFContextSubject_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextKeywords typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextKeywords_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextKeywords_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextKeywords").orElseThrow() }
private val kCGPDFContextKeywords_VH: VarHandle by lazy { kCGPDFContextKeywords_LAYOUT.varHandle() }

var kCGPDFContextKeywords: MemorySegment
    get() = kCGPDFContextKeywords_VH.get(kCGPDFContextKeywords_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextKeywords_VH.set(kCGPDFContextKeywords_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextCreator typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextCreator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextCreator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextCreator").orElseThrow() }
private val kCGPDFContextCreator_VH: VarHandle by lazy { kCGPDFContextCreator_LAYOUT.varHandle() }

var kCGPDFContextCreator: MemorySegment
    get() = kCGPDFContextCreator_VH.get(kCGPDFContextCreator_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextCreator_VH.set(kCGPDFContextCreator_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextOwnerPassword typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextOwnerPassword_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextOwnerPassword_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextOwnerPassword").orElseThrow() }
private val kCGPDFContextOwnerPassword_VH: VarHandle by lazy { kCGPDFContextOwnerPassword_LAYOUT.varHandle() }

var kCGPDFContextOwnerPassword: MemorySegment
    get() = kCGPDFContextOwnerPassword_VH.get(kCGPDFContextOwnerPassword_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextOwnerPassword_VH.set(kCGPDFContextOwnerPassword_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextUserPassword typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextUserPassword_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextUserPassword_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextUserPassword").orElseThrow() }
private val kCGPDFContextUserPassword_VH: VarHandle by lazy { kCGPDFContextUserPassword_LAYOUT.varHandle() }

var kCGPDFContextUserPassword: MemorySegment
    get() = kCGPDFContextUserPassword_VH.get(kCGPDFContextUserPassword_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextUserPassword_VH.set(kCGPDFContextUserPassword_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextEncryptionKeyLength typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextEncryptionKeyLength_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextEncryptionKeyLength_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextEncryptionKeyLength").orElseThrow() }
private val kCGPDFContextEncryptionKeyLength_VH: VarHandle by lazy { kCGPDFContextEncryptionKeyLength_LAYOUT.varHandle() }

var kCGPDFContextEncryptionKeyLength: MemorySegment
    get() = kCGPDFContextEncryptionKeyLength_VH.get(kCGPDFContextEncryptionKeyLength_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextEncryptionKeyLength_VH.set(kCGPDFContextEncryptionKeyLength_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextAllowsPrinting typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextAllowsPrinting_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextAllowsPrinting_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextAllowsPrinting").orElseThrow() }
private val kCGPDFContextAllowsPrinting_VH: VarHandle by lazy { kCGPDFContextAllowsPrinting_LAYOUT.varHandle() }

var kCGPDFContextAllowsPrinting: MemorySegment
    get() = kCGPDFContextAllowsPrinting_VH.get(kCGPDFContextAllowsPrinting_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextAllowsPrinting_VH.set(kCGPDFContextAllowsPrinting_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextAllowsCopying typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextAllowsCopying_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextAllowsCopying_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextAllowsCopying").orElseThrow() }
private val kCGPDFContextAllowsCopying_VH: VarHandle by lazy { kCGPDFContextAllowsCopying_LAYOUT.varHandle() }

var kCGPDFContextAllowsCopying: MemorySegment
    get() = kCGPDFContextAllowsCopying_VH.get(kCGPDFContextAllowsCopying_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextAllowsCopying_VH.set(kCGPDFContextAllowsCopying_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextOutputIntent typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextOutputIntent_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextOutputIntent_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextOutputIntent").orElseThrow() }
private val kCGPDFContextOutputIntent_VH: VarHandle by lazy { kCGPDFContextOutputIntent_LAYOUT.varHandle() }

var kCGPDFContextOutputIntent: MemorySegment
    get() = kCGPDFContextOutputIntent_VH.get(kCGPDFContextOutputIntent_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextOutputIntent_VH.set(kCGPDFContextOutputIntent_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFXOutputIntentSubtype typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFXOutputIntentSubtype_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFXOutputIntentSubtype_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFXOutputIntentSubtype").orElseThrow() }
private val kCGPDFXOutputIntentSubtype_VH: VarHandle by lazy { kCGPDFXOutputIntentSubtype_LAYOUT.varHandle() }

var kCGPDFXOutputIntentSubtype: MemorySegment
    get() = kCGPDFXOutputIntentSubtype_VH.get(kCGPDFXOutputIntentSubtype_SEGMENT) as MemorySegment
    set(value) = kCGPDFXOutputIntentSubtype_VH.set(kCGPDFXOutputIntentSubtype_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFXOutputConditionIdentifier typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFXOutputConditionIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFXOutputConditionIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFXOutputConditionIdentifier").orElseThrow() }
private val kCGPDFXOutputConditionIdentifier_VH: VarHandle by lazy { kCGPDFXOutputConditionIdentifier_LAYOUT.varHandle() }

var kCGPDFXOutputConditionIdentifier: MemorySegment
    get() = kCGPDFXOutputConditionIdentifier_VH.get(kCGPDFXOutputConditionIdentifier_SEGMENT) as MemorySegment
    set(value) = kCGPDFXOutputConditionIdentifier_VH.set(kCGPDFXOutputConditionIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFXOutputCondition typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFXOutputCondition_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFXOutputCondition_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFXOutputCondition").orElseThrow() }
private val kCGPDFXOutputCondition_VH: VarHandle by lazy { kCGPDFXOutputCondition_LAYOUT.varHandle() }

var kCGPDFXOutputCondition: MemorySegment
    get() = kCGPDFXOutputCondition_VH.get(kCGPDFXOutputCondition_SEGMENT) as MemorySegment
    set(value) = kCGPDFXOutputCondition_VH.set(kCGPDFXOutputCondition_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFXRegistryName typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFXRegistryName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFXRegistryName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFXRegistryName").orElseThrow() }
private val kCGPDFXRegistryName_VH: VarHandle by lazy { kCGPDFXRegistryName_LAYOUT.varHandle() }

var kCGPDFXRegistryName: MemorySegment
    get() = kCGPDFXRegistryName_VH.get(kCGPDFXRegistryName_SEGMENT) as MemorySegment
    set(value) = kCGPDFXRegistryName_VH.set(kCGPDFXRegistryName_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFXInfo typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFXInfo_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFXInfo_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFXInfo").orElseThrow() }
private val kCGPDFXInfo_VH: VarHandle by lazy { kCGPDFXInfo_LAYOUT.varHandle() }

var kCGPDFXInfo: MemorySegment
    get() = kCGPDFXInfo_VH.get(kCGPDFXInfo_SEGMENT) as MemorySegment
    set(value) = kCGPDFXInfo_VH.set(kCGPDFXInfo_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFXDestinationOutputProfile typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFXDestinationOutputProfile_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFXDestinationOutputProfile_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFXDestinationOutputProfile").orElseThrow() }
private val kCGPDFXDestinationOutputProfile_VH: VarHandle by lazy { kCGPDFXDestinationOutputProfile_LAYOUT.varHandle() }

var kCGPDFXDestinationOutputProfile: MemorySegment
    get() = kCGPDFXDestinationOutputProfile_VH.get(kCGPDFXDestinationOutputProfile_SEGMENT) as MemorySegment
    set(value) = kCGPDFXDestinationOutputProfile_VH.set(kCGPDFXDestinationOutputProfile_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextOutputIntents typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextOutputIntents_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextOutputIntents_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextOutputIntents").orElseThrow() }
private val kCGPDFContextOutputIntents_VH: VarHandle by lazy { kCGPDFContextOutputIntents_LAYOUT.varHandle() }

var kCGPDFContextOutputIntents: MemorySegment
    get() = kCGPDFContextOutputIntents_VH.get(kCGPDFContextOutputIntents_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextOutputIntents_VH.set(kCGPDFContextOutputIntents_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextAccessPermissions typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextAccessPermissions_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextAccessPermissions_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextAccessPermissions").orElseThrow() }
private val kCGPDFContextAccessPermissions_VH: VarHandle by lazy { kCGPDFContextAccessPermissions_LAYOUT.varHandle() }

var kCGPDFContextAccessPermissions: MemorySegment
    get() = kCGPDFContextAccessPermissions_VH.get(kCGPDFContextAccessPermissions_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextAccessPermissions_VH.set(kCGPDFContextAccessPermissions_SEGMENT, value)

/**
 * {@snippet lang=c : CGPDFContextSetOutline Void(typedef CGContextRef = (Declared(CGContext))*,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGPDFContextSetOutline_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFContextSetOutline_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextSetOutline").orElseThrow()
private val CGPDFContextSetOutline_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextSetOutline_ADDR, CGPDFContextSetOutline_DESC)

fun CGPDFContextSetOutline(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGPDFContextSetOutline_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGPDFContextCreateLinearizedPDF typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextCreateLinearizedPDF_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextCreateLinearizedPDF_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextCreateLinearizedPDF").orElseThrow() }
private val kCGPDFContextCreateLinearizedPDF_VH: VarHandle by lazy { kCGPDFContextCreateLinearizedPDF_LAYOUT.varHandle() }

var kCGPDFContextCreateLinearizedPDF: MemorySegment
    get() = kCGPDFContextCreateLinearizedPDF_VH.get(kCGPDFContextCreateLinearizedPDF_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextCreateLinearizedPDF_VH.set(kCGPDFContextCreateLinearizedPDF_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFContextCreatePDFA typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGPDFContextCreatePDFA_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFContextCreatePDFA_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFContextCreatePDFA").orElseThrow() }
private val kCGPDFContextCreatePDFA_VH: VarHandle by lazy { kCGPDFContextCreatePDFA_LAYOUT.varHandle() }

var kCGPDFContextCreatePDFA: MemorySegment
    get() = kCGPDFContextCreatePDFA_VH.get(kCGPDFContextCreatePDFA_SEGMENT) as MemorySegment
    set(value) = kCGPDFContextCreatePDFA_VH.set(kCGPDFContextCreatePDFA_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFTagPropertyActualText typedef CGPDFTagProperty = (Declared(__CFString))*
 */
private val kCGPDFTagPropertyActualText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFTagPropertyActualText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFTagPropertyActualText").orElseThrow() }
private val kCGPDFTagPropertyActualText_VH: VarHandle by lazy { kCGPDFTagPropertyActualText_LAYOUT.varHandle() }

var kCGPDFTagPropertyActualText: MemorySegment
    get() = kCGPDFTagPropertyActualText_VH.get(kCGPDFTagPropertyActualText_SEGMENT) as MemorySegment
    set(value) = kCGPDFTagPropertyActualText_VH.set(kCGPDFTagPropertyActualText_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFTagPropertyAlternativeText typedef CGPDFTagProperty = (Declared(__CFString))*
 */
private val kCGPDFTagPropertyAlternativeText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFTagPropertyAlternativeText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFTagPropertyAlternativeText").orElseThrow() }
private val kCGPDFTagPropertyAlternativeText_VH: VarHandle by lazy { kCGPDFTagPropertyAlternativeText_LAYOUT.varHandle() }

var kCGPDFTagPropertyAlternativeText: MemorySegment
    get() = kCGPDFTagPropertyAlternativeText_VH.get(kCGPDFTagPropertyAlternativeText_SEGMENT) as MemorySegment
    set(value) = kCGPDFTagPropertyAlternativeText_VH.set(kCGPDFTagPropertyAlternativeText_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFTagPropertyTitleText typedef CGPDFTagProperty = (Declared(__CFString))*
 */
private val kCGPDFTagPropertyTitleText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFTagPropertyTitleText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFTagPropertyTitleText").orElseThrow() }
private val kCGPDFTagPropertyTitleText_VH: VarHandle by lazy { kCGPDFTagPropertyTitleText_LAYOUT.varHandle() }

var kCGPDFTagPropertyTitleText: MemorySegment
    get() = kCGPDFTagPropertyTitleText_VH.get(kCGPDFTagPropertyTitleText_SEGMENT) as MemorySegment
    set(value) = kCGPDFTagPropertyTitleText_VH.set(kCGPDFTagPropertyTitleText_SEGMENT, value)

/**
 * {@snippet lang=c : kCGPDFTagPropertyLanguageText typedef CGPDFTagProperty = (Declared(__CFString))*
 */
private val kCGPDFTagPropertyLanguageText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGPDFTagPropertyLanguageText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGPDFTagPropertyLanguageText").orElseThrow() }
private val kCGPDFTagPropertyLanguageText_VH: VarHandle by lazy { kCGPDFTagPropertyLanguageText_LAYOUT.varHandle() }

var kCGPDFTagPropertyLanguageText: MemorySegment
    get() = kCGPDFTagPropertyLanguageText_VH.get(kCGPDFTagPropertyLanguageText_SEGMENT) as MemorySegment
    set(value) = kCGPDFTagPropertyLanguageText_VH.set(kCGPDFTagPropertyLanguageText_SEGMENT, value)

/**
 * {@snippet lang=c : CGPDFContextEndTag Void(typedef CGContextRef = (Declared(CGContext))*)
 */
private val CGPDFContextEndTag_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPDFContextEndTag_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFContextEndTag").orElseThrow()
private val CGPDFContextEndTag_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFContextEndTag_ADDR, CGPDFContextEndTag_DESC)

fun CGPDFContextEndTag(arg0: MemorySegment): Unit {
    try {
        CGPDFContextEndTag_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerCreate typedef CGPDFScannerRef = (Declared(CGPDFScanner))*(typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*,typedef CGPDFOperatorTableRef = (Declared(CGPDFOperatorTable))*,(Void)*)
 */
private val CGPDFScannerCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerCreate").orElseThrow()
private val CGPDFScannerCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerCreate_ADDR, CGPDFScannerCreate_DESC)

fun CGPDFScannerCreate(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return CGPDFScannerCreate_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerRetain typedef CGPDFScannerRef = (Declared(CGPDFScanner))*(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*)
 */
private val CGPDFScannerRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerRetain").orElseThrow()
private val CGPDFScannerRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerRetain_ADDR, CGPDFScannerRetain_DESC)

fun CGPDFScannerRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFScannerRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerRelease Void(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*)
 */
private val CGPDFScannerRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPDFScannerRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerRelease").orElseThrow()
private val CGPDFScannerRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerRelease_ADDR, CGPDFScannerRelease_DESC)

fun CGPDFScannerRelease(arg0: MemorySegment): Unit {
    try {
        CGPDFScannerRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerScan Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*)
 */
private val CGPDFScannerScan_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGPDFScannerScan_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerScan").orElseThrow()
private val CGPDFScannerScan_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerScan_ADDR, CGPDFScannerScan_DESC)

fun CGPDFScannerScan(arg0: MemorySegment): Boolean {
    try {
        return CGPDFScannerScan_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerGetContentStream typedef CGPDFContentStreamRef = (Declared(CGPDFContentStream))*(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*)
 */
private val CGPDFScannerGetContentStream_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerGetContentStream_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerGetContentStream").orElseThrow()
private val CGPDFScannerGetContentStream_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerGetContentStream_ADDR, CGPDFScannerGetContentStream_DESC)

fun CGPDFScannerGetContentStream(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFScannerGetContentStream_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerPopObject Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*,(typedef CGPDFObjectRef = (Declared(CGPDFObject))*)*)
 */
private val CGPDFScannerPopObject_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerPopObject_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerPopObject").orElseThrow()
private val CGPDFScannerPopObject_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerPopObject_ADDR, CGPDFScannerPopObject_DESC)

fun CGPDFScannerPopObject(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFScannerPopObject_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerPopBoolean Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*,(typedef CGPDFBoolean = UNSIGNED = Char)*)
 */
private val CGPDFScannerPopBoolean_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerPopBoolean_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerPopBoolean").orElseThrow()
private val CGPDFScannerPopBoolean_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerPopBoolean_ADDR, CGPDFScannerPopBoolean_DESC)

fun CGPDFScannerPopBoolean(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFScannerPopBoolean_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerPopInteger Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*,(typedef CGPDFInteger = Long)*)
 */
private val CGPDFScannerPopInteger_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerPopInteger_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerPopInteger").orElseThrow()
private val CGPDFScannerPopInteger_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerPopInteger_ADDR, CGPDFScannerPopInteger_DESC)

fun CGPDFScannerPopInteger(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFScannerPopInteger_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerPopNumber Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*,(typedef CGPDFReal = Double)*)
 */
private val CGPDFScannerPopNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerPopNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerPopNumber").orElseThrow()
private val CGPDFScannerPopNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerPopNumber_ADDR, CGPDFScannerPopNumber_DESC)

fun CGPDFScannerPopNumber(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFScannerPopNumber_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerPopName Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*,((Char)*)*)
 */
private val CGPDFScannerPopName_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerPopName_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerPopName").orElseThrow()
private val CGPDFScannerPopName_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerPopName_ADDR, CGPDFScannerPopName_DESC)

fun CGPDFScannerPopName(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFScannerPopName_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerPopString Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*,(typedef CGPDFStringRef = (Declared(CGPDFString))*)*)
 */
private val CGPDFScannerPopString_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerPopString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerPopString").orElseThrow()
private val CGPDFScannerPopString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerPopString_ADDR, CGPDFScannerPopString_DESC)

fun CGPDFScannerPopString(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFScannerPopString_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerPopArray Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*,(typedef CGPDFArrayRef = (Declared(CGPDFArray))*)*)
 */
private val CGPDFScannerPopArray_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerPopArray_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerPopArray").orElseThrow()
private val CGPDFScannerPopArray_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerPopArray_ADDR, CGPDFScannerPopArray_DESC)

fun CGPDFScannerPopArray(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFScannerPopArray_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerPopDictionary Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*,(typedef CGPDFDictionaryRef = (Declared(CGPDFDictionary))*)*)
 */
private val CGPDFScannerPopDictionary_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerPopDictionary_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerPopDictionary").orElseThrow()
private val CGPDFScannerPopDictionary_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerPopDictionary_ADDR, CGPDFScannerPopDictionary_DESC)

fun CGPDFScannerPopDictionary(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFScannerPopDictionary_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerPopStream Bool(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*,(typedef CGPDFStreamRef = (Declared(CGPDFStream))*)*)
 */
private val CGPDFScannerPopStream_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFScannerPopStream_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerPopStream").orElseThrow()
private val CGPDFScannerPopStream_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerPopStream_ADDR, CGPDFScannerPopStream_DESC)

fun CGPDFScannerPopStream(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPDFScannerPopStream_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFScannerStop Void(typedef CGPDFScannerRef = (Declared(CGPDFScanner))*)
 */
private val CGPDFScannerStop_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPDFScannerStop_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFScannerStop").orElseThrow()
private val CGPDFScannerStop_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFScannerStop_ADDR, CGPDFScannerStop_DESC)

fun CGPDFScannerStop(arg0: MemorySegment): Unit {
    try {
        CGPDFScannerStop_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFOperatorTableCreate typedef CGPDFOperatorTableRef = (Declared(CGPDFOperatorTable))*()
 */
private val CGPDFOperatorTableCreate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS)
private val CGPDFOperatorTableCreate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFOperatorTableCreate").orElseThrow()
private val CGPDFOperatorTableCreate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFOperatorTableCreate_ADDR, CGPDFOperatorTableCreate_DESC)

fun CGPDFOperatorTableCreate(): MemorySegment {
    try {
        return CGPDFOperatorTableCreate_HANDLE.invokeExact() as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFOperatorTableRetain typedef CGPDFOperatorTableRef = (Declared(CGPDFOperatorTable))*(typedef CGPDFOperatorTableRef = (Declared(CGPDFOperatorTable))*)
 */
private val CGPDFOperatorTableRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFOperatorTableRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFOperatorTableRetain").orElseThrow()
private val CGPDFOperatorTableRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFOperatorTableRetain_ADDR, CGPDFOperatorTableRetain_DESC)

fun CGPDFOperatorTableRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGPDFOperatorTableRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFOperatorTableRelease Void(typedef CGPDFOperatorTableRef = (Declared(CGPDFOperatorTable))*)
 */
private val CGPDFOperatorTableRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGPDFOperatorTableRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFOperatorTableRelease").orElseThrow()
private val CGPDFOperatorTableRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFOperatorTableRelease_ADDR, CGPDFOperatorTableRelease_DESC)

fun CGPDFOperatorTableRelease(arg0: MemorySegment): Unit {
    try {
        CGPDFOperatorTableRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPDFOperatorTableSetCallback Void(typedef CGPDFOperatorTableRef = (Declared(CGPDFOperatorTable))*,(Char)*,typedef CGPDFOperatorCallback = (Void((Declared(CGPDFScanner))*,(Void)*))*)
 */
private val CGPDFOperatorTableSetCallback_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPDFOperatorTableSetCallback_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPDFOperatorTableSetCallback").orElseThrow()
private val CGPDFOperatorTableSetCallback_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPDFOperatorTableSetCallback_ADDR, CGPDFOperatorTableSetCallback_DESC)

fun CGPDFOperatorTableSetCallback(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGPDFOperatorTableSetCallback_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGWindowNumber typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowNumber").orElseThrow() }
private val kCGWindowNumber_VH: VarHandle by lazy { kCGWindowNumber_LAYOUT.varHandle() }

var kCGWindowNumber: MemorySegment
    get() = kCGWindowNumber_VH.get(kCGWindowNumber_SEGMENT) as MemorySegment
    set(value) = kCGWindowNumber_VH.set(kCGWindowNumber_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowStoreType typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowStoreType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowStoreType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowStoreType").orElseThrow() }
private val kCGWindowStoreType_VH: VarHandle by lazy { kCGWindowStoreType_LAYOUT.varHandle() }

var kCGWindowStoreType: MemorySegment
    get() = kCGWindowStoreType_VH.get(kCGWindowStoreType_SEGMENT) as MemorySegment
    set(value) = kCGWindowStoreType_VH.set(kCGWindowStoreType_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowLayer typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowLayer_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowLayer_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowLayer").orElseThrow() }
private val kCGWindowLayer_VH: VarHandle by lazy { kCGWindowLayer_LAYOUT.varHandle() }

var kCGWindowLayer: MemorySegment
    get() = kCGWindowLayer_VH.get(kCGWindowLayer_SEGMENT) as MemorySegment
    set(value) = kCGWindowLayer_VH.set(kCGWindowLayer_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowBounds typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowBounds_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowBounds_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowBounds").orElseThrow() }
private val kCGWindowBounds_VH: VarHandle by lazy { kCGWindowBounds_LAYOUT.varHandle() }

var kCGWindowBounds: MemorySegment
    get() = kCGWindowBounds_VH.get(kCGWindowBounds_SEGMENT) as MemorySegment
    set(value) = kCGWindowBounds_VH.set(kCGWindowBounds_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowSharingState typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowSharingState_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowSharingState_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowSharingState").orElseThrow() }
private val kCGWindowSharingState_VH: VarHandle by lazy { kCGWindowSharingState_LAYOUT.varHandle() }

var kCGWindowSharingState: MemorySegment
    get() = kCGWindowSharingState_VH.get(kCGWindowSharingState_SEGMENT) as MemorySegment
    set(value) = kCGWindowSharingState_VH.set(kCGWindowSharingState_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowAlpha typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowAlpha_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowAlpha_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowAlpha").orElseThrow() }
private val kCGWindowAlpha_VH: VarHandle by lazy { kCGWindowAlpha_LAYOUT.varHandle() }

var kCGWindowAlpha: MemorySegment
    get() = kCGWindowAlpha_VH.get(kCGWindowAlpha_SEGMENT) as MemorySegment
    set(value) = kCGWindowAlpha_VH.set(kCGWindowAlpha_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowOwnerPID typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowOwnerPID_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowOwnerPID_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowOwnerPID").orElseThrow() }
private val kCGWindowOwnerPID_VH: VarHandle by lazy { kCGWindowOwnerPID_LAYOUT.varHandle() }

var kCGWindowOwnerPID: MemorySegment
    get() = kCGWindowOwnerPID_VH.get(kCGWindowOwnerPID_SEGMENT) as MemorySegment
    set(value) = kCGWindowOwnerPID_VH.set(kCGWindowOwnerPID_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowMemoryUsage typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowMemoryUsage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowMemoryUsage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowMemoryUsage").orElseThrow() }
private val kCGWindowMemoryUsage_VH: VarHandle by lazy { kCGWindowMemoryUsage_LAYOUT.varHandle() }

var kCGWindowMemoryUsage: MemorySegment
    get() = kCGWindowMemoryUsage_VH.get(kCGWindowMemoryUsage_SEGMENT) as MemorySegment
    set(value) = kCGWindowMemoryUsage_VH.set(kCGWindowMemoryUsage_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowWorkspace typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowWorkspace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowWorkspace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowWorkspace").orElseThrow() }
private val kCGWindowWorkspace_VH: VarHandle by lazy { kCGWindowWorkspace_LAYOUT.varHandle() }

var kCGWindowWorkspace: MemorySegment
    get() = kCGWindowWorkspace_VH.get(kCGWindowWorkspace_SEGMENT) as MemorySegment
    set(value) = kCGWindowWorkspace_VH.set(kCGWindowWorkspace_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowOwnerName typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowOwnerName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowOwnerName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowOwnerName").orElseThrow() }
private val kCGWindowOwnerName_VH: VarHandle by lazy { kCGWindowOwnerName_LAYOUT.varHandle() }

var kCGWindowOwnerName: MemorySegment
    get() = kCGWindowOwnerName_VH.get(kCGWindowOwnerName_SEGMENT) as MemorySegment
    set(value) = kCGWindowOwnerName_VH.set(kCGWindowOwnerName_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowName typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowName").orElseThrow() }
private val kCGWindowName_VH: VarHandle by lazy { kCGWindowName_LAYOUT.varHandle() }

var kCGWindowName: MemorySegment
    get() = kCGWindowName_VH.get(kCGWindowName_SEGMENT) as MemorySegment
    set(value) = kCGWindowName_VH.set(kCGWindowName_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowIsOnscreen typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowIsOnscreen_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowIsOnscreen_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowIsOnscreen").orElseThrow() }
private val kCGWindowIsOnscreen_VH: VarHandle by lazy { kCGWindowIsOnscreen_LAYOUT.varHandle() }

var kCGWindowIsOnscreen: MemorySegment
    get() = kCGWindowIsOnscreen_VH.get(kCGWindowIsOnscreen_SEGMENT) as MemorySegment
    set(value) = kCGWindowIsOnscreen_VH.set(kCGWindowIsOnscreen_SEGMENT, value)

/**
 * {@snippet lang=c : kCGWindowBackingLocationVideoMemory typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGWindowBackingLocationVideoMemory_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGWindowBackingLocationVideoMemory_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGWindowBackingLocationVideoMemory").orElseThrow() }
private val kCGWindowBackingLocationVideoMemory_VH: VarHandle by lazy { kCGWindowBackingLocationVideoMemory_LAYOUT.varHandle() }

var kCGWindowBackingLocationVideoMemory: MemorySegment
    get() = kCGWindowBackingLocationVideoMemory_VH.get(kCGWindowBackingLocationVideoMemory_SEGMENT) as MemorySegment
    set(value) = kCGWindowBackingLocationVideoMemory_VH.set(kCGWindowBackingLocationVideoMemory_SEGMENT, value)

/**
 * {@snippet lang=c : CGWindowListCreateDescriptionFromArray typedef CFArrayRef = (Declared(__CFArray))*(typedef CFArrayRef = (Declared(__CFArray))*)
 */
private val CGWindowListCreateDescriptionFromArray_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGWindowListCreateDescriptionFromArray_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGWindowListCreateDescriptionFromArray").orElseThrow()
private val CGWindowListCreateDescriptionFromArray_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGWindowListCreateDescriptionFromArray_ADDR, CGWindowListCreateDescriptionFromArray_DESC)

fun CGWindowListCreateDescriptionFromArray(arg0: MemorySegment): MemorySegment {
    try {
        return CGWindowListCreateDescriptionFromArray_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPreflightScreenCaptureAccess Bool()
 */
private val CGPreflightScreenCaptureAccess_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
private val CGPreflightScreenCaptureAccess_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPreflightScreenCaptureAccess").orElseThrow()
private val CGPreflightScreenCaptureAccess_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPreflightScreenCaptureAccess_ADDR, CGPreflightScreenCaptureAccess_DESC)

fun CGPreflightScreenCaptureAccess(): Boolean {
    try {
        return CGPreflightScreenCaptureAccess_HANDLE.invokeExact() as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRequestScreenCaptureAccess Bool()
 */
private val CGRequestScreenCaptureAccess_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
private val CGRequestScreenCaptureAccess_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRequestScreenCaptureAccess").orElseThrow()
private val CGRequestScreenCaptureAccess_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRequestScreenCaptureAccess_ADDR, CGRequestScreenCaptureAccess_DESC)

fun CGRequestScreenCaptureAccess(): Boolean {
    try {
        return CGRequestScreenCaptureAccess_HANDLE.invokeExact() as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGMainDisplayID typedef CGDirectDisplayID = UNSIGNED = Int()
 */
private val CGMainDisplayID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT)
private val CGMainDisplayID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGMainDisplayID").orElseThrow()
private val CGMainDisplayID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGMainDisplayID_ADDR, CGMainDisplayID_DESC)

fun CGMainDisplayID(): Int {
    try {
        return CGMainDisplayID_HANDLE.invokeExact() as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIDToOpenGLDisplayMask typedef CGOpenGLDisplayMask = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIDToOpenGLDisplayMask_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIDToOpenGLDisplayMask_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIDToOpenGLDisplayMask").orElseThrow()
private val CGDisplayIDToOpenGLDisplayMask_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIDToOpenGLDisplayMask_ADDR, CGDisplayIDToOpenGLDisplayMask_DESC)

fun CGDisplayIDToOpenGLDisplayMask(arg0: Int): Int {
    try {
        return CGDisplayIDToOpenGLDisplayMask_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGOpenGLDisplayMaskToDisplayID typedef CGDirectDisplayID = UNSIGNED = Int(typedef CGOpenGLDisplayMask = UNSIGNED = Int)
 */
private val CGOpenGLDisplayMaskToDisplayID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGOpenGLDisplayMaskToDisplayID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGOpenGLDisplayMaskToDisplayID").orElseThrow()
private val CGOpenGLDisplayMaskToDisplayID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGOpenGLDisplayMaskToDisplayID_ADDR, CGOpenGLDisplayMaskToDisplayID_DESC)

fun CGOpenGLDisplayMaskToDisplayID(arg0: Int): Int {
    try {
        return CGOpenGLDisplayMaskToDisplayID_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayBounds typedef CGRect = Declared(CGRect)(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayBounds_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.JAVA_INT)
private val CGDisplayBounds_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayBounds").orElseThrow()
private val CGDisplayBounds_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayBounds_ADDR, CGDisplayBounds_DESC)

fun CGDisplayBounds(allocator: SegmentAllocator, arg0: Int): MemorySegment {
    try {
        return CGDisplayBounds_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayPixelsWide typedef size_t = UNSIGNED = Long(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayPixelsWide_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT)
private val CGDisplayPixelsWide_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayPixelsWide").orElseThrow()
private val CGDisplayPixelsWide_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayPixelsWide_ADDR, CGDisplayPixelsWide_DESC)

fun CGDisplayPixelsWide(arg0: Int): Long {
    try {
        return CGDisplayPixelsWide_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayPixelsHigh typedef size_t = UNSIGNED = Long(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayPixelsHigh_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.JAVA_INT)
private val CGDisplayPixelsHigh_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayPixelsHigh").orElseThrow()
private val CGDisplayPixelsHigh_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayPixelsHigh_ADDR, CGDisplayPixelsHigh_DESC)

fun CGDisplayPixelsHigh(arg0: Int): Long {
    try {
        return CGDisplayPixelsHigh_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayCopyAllDisplayModes typedef CFArrayRef = (Declared(__CFArray))*(typedef CGDirectDisplayID = UNSIGNED = Int,typedef CFDictionaryRef = (Declared(__CFDictionary))*)
 */
private val CGDisplayCopyAllDisplayModes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGDisplayCopyAllDisplayModes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayCopyAllDisplayModes").orElseThrow()
private val CGDisplayCopyAllDisplayModes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayCopyAllDisplayModes_ADDR, CGDisplayCopyAllDisplayModes_DESC)

fun CGDisplayCopyAllDisplayModes(arg0: Int, arg1: MemorySegment): MemorySegment {
    try {
        return CGDisplayCopyAllDisplayModes_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGDisplayShowDuplicateLowResolutionModes typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayShowDuplicateLowResolutionModes_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayShowDuplicateLowResolutionModes_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayShowDuplicateLowResolutionModes").orElseThrow() }
private val kCGDisplayShowDuplicateLowResolutionModes_VH: VarHandle by lazy { kCGDisplayShowDuplicateLowResolutionModes_LAYOUT.varHandle() }

var kCGDisplayShowDuplicateLowResolutionModes: MemorySegment
    get() = kCGDisplayShowDuplicateLowResolutionModes_VH.get(kCGDisplayShowDuplicateLowResolutionModes_SEGMENT) as MemorySegment
    set(value) = kCGDisplayShowDuplicateLowResolutionModes_VH.set(kCGDisplayShowDuplicateLowResolutionModes_SEGMENT, value)

/**
 * {@snippet lang=c : CGDisplayCopyDisplayMode typedef CGDisplayModeRef = (Declared(CGDisplayMode))*(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayCopyDisplayMode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGDisplayCopyDisplayMode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayCopyDisplayMode").orElseThrow()
private val CGDisplayCopyDisplayMode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayCopyDisplayMode_ADDR, CGDisplayCopyDisplayMode_DESC)

fun CGDisplayCopyDisplayMode(arg0: Int): MemorySegment {
    try {
        return CGDisplayCopyDisplayMode_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeGetWidth typedef size_t = UNSIGNED = Long(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeGetWidth_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGDisplayModeGetWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeGetWidth").orElseThrow()
private val CGDisplayModeGetWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeGetWidth_ADDR, CGDisplayModeGetWidth_DESC)

fun CGDisplayModeGetWidth(arg0: MemorySegment): Long {
    try {
        return CGDisplayModeGetWidth_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeGetHeight typedef size_t = UNSIGNED = Long(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeGetHeight_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGDisplayModeGetHeight_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeGetHeight").orElseThrow()
private val CGDisplayModeGetHeight_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeGetHeight_ADDR, CGDisplayModeGetHeight_DESC)

fun CGDisplayModeGetHeight(arg0: MemorySegment): Long {
    try {
        return CGDisplayModeGetHeight_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeCopyPixelEncoding typedef CFStringRef = (Declared(__CFString))*(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeCopyPixelEncoding_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDisplayModeCopyPixelEncoding_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeCopyPixelEncoding").orElseThrow()
private val CGDisplayModeCopyPixelEncoding_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeCopyPixelEncoding_ADDR, CGDisplayModeCopyPixelEncoding_DESC)

fun CGDisplayModeCopyPixelEncoding(arg0: MemorySegment): MemorySegment {
    try {
        return CGDisplayModeCopyPixelEncoding_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeGetRefreshRate Double(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeGetRefreshRate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CGDisplayModeGetRefreshRate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeGetRefreshRate").orElseThrow()
private val CGDisplayModeGetRefreshRate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeGetRefreshRate_ADDR, CGDisplayModeGetRefreshRate_DESC)

fun CGDisplayModeGetRefreshRate(arg0: MemorySegment): Double {
    try {
        return CGDisplayModeGetRefreshRate_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeGetIOFlags typedef uint32_t = UNSIGNED = Int(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeGetIOFlags_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGDisplayModeGetIOFlags_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeGetIOFlags").orElseThrow()
private val CGDisplayModeGetIOFlags_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeGetIOFlags_ADDR, CGDisplayModeGetIOFlags_DESC)

fun CGDisplayModeGetIOFlags(arg0: MemorySegment): Int {
    try {
        return CGDisplayModeGetIOFlags_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeGetIODisplayModeID typedef int32_t = Int(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeGetIODisplayModeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
private val CGDisplayModeGetIODisplayModeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeGetIODisplayModeID").orElseThrow()
private val CGDisplayModeGetIODisplayModeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeGetIODisplayModeID_ADDR, CGDisplayModeGetIODisplayModeID_DESC)

fun CGDisplayModeGetIODisplayModeID(arg0: MemorySegment): Int {
    try {
        return CGDisplayModeGetIODisplayModeID_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeIsUsableForDesktopGUI Bool(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeIsUsableForDesktopGUI_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS)
private val CGDisplayModeIsUsableForDesktopGUI_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeIsUsableForDesktopGUI").orElseThrow()
private val CGDisplayModeIsUsableForDesktopGUI_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeIsUsableForDesktopGUI_ADDR, CGDisplayModeIsUsableForDesktopGUI_DESC)

fun CGDisplayModeIsUsableForDesktopGUI(arg0: MemorySegment): Boolean {
    try {
        return CGDisplayModeIsUsableForDesktopGUI_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGDisplayModeGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGDisplayModeGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeGetTypeID").orElseThrow()
private val CGDisplayModeGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeGetTypeID_ADDR, CGDisplayModeGetTypeID_DESC)

fun CGDisplayModeGetTypeID(): Long {
    try {
        return CGDisplayModeGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeRetain typedef CGDisplayModeRef = (Declared(CGDisplayMode))*(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeRetain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDisplayModeRetain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeRetain").orElseThrow()
private val CGDisplayModeRetain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeRetain_ADDR, CGDisplayModeRetain_DESC)

fun CGDisplayModeRetain(arg0: MemorySegment): MemorySegment {
    try {
        return CGDisplayModeRetain_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeRelease Void(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeRelease_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val CGDisplayModeRelease_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeRelease").orElseThrow()
private val CGDisplayModeRelease_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeRelease_ADDR, CGDisplayModeRelease_DESC)

fun CGDisplayModeRelease(arg0: MemorySegment): Unit {
    try {
        CGDisplayModeRelease_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeGetPixelWidth typedef size_t = UNSIGNED = Long(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeGetPixelWidth_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGDisplayModeGetPixelWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeGetPixelWidth").orElseThrow()
private val CGDisplayModeGetPixelWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeGetPixelWidth_ADDR, CGDisplayModeGetPixelWidth_DESC)

fun CGDisplayModeGetPixelWidth(arg0: MemorySegment): Long {
    try {
        return CGDisplayModeGetPixelWidth_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModeGetPixelHeight typedef size_t = UNSIGNED = Long(typedef CGDisplayModeRef = (Declared(CGDisplayMode))*)
 */
private val CGDisplayModeGetPixelHeight_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGDisplayModeGetPixelHeight_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModeGetPixelHeight").orElseThrow()
private val CGDisplayModeGetPixelHeight_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModeGetPixelHeight_ADDR, CGDisplayModeGetPixelHeight_DESC)

fun CGDisplayModeGetPixelHeight(arg0: MemorySegment): Long {
    try {
        return CGDisplayModeGetPixelHeight_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayGammaTableCapacity typedef uint32_t = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayGammaTableCapacity_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayGammaTableCapacity_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayGammaTableCapacity").orElseThrow()
private val CGDisplayGammaTableCapacity_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayGammaTableCapacity_ADDR, CGDisplayGammaTableCapacity_DESC)

fun CGDisplayGammaTableCapacity(arg0: Int): Int {
    try {
        return CGDisplayGammaTableCapacity_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayRestoreColorSyncSettings Void()
 */
private val CGDisplayRestoreColorSyncSettings_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val CGDisplayRestoreColorSyncSettings_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayRestoreColorSyncSettings").orElseThrow()
private val CGDisplayRestoreColorSyncSettings_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayRestoreColorSyncSettings_ADDR, CGDisplayRestoreColorSyncSettings_DESC)

fun CGDisplayRestoreColorSyncSettings(): Unit {
    try {
        CGDisplayRestoreColorSyncSettings_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsCaptured typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsCaptured_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsCaptured_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsCaptured").orElseThrow()
private val CGDisplayIsCaptured_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsCaptured_ADDR, CGDisplayIsCaptured_DESC)

fun CGDisplayIsCaptured(arg0: Int): Int {
    try {
        return CGDisplayIsCaptured_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShieldingWindowID typedef CGWindowID = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGShieldingWindowID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGShieldingWindowID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShieldingWindowID").orElseThrow()
private val CGShieldingWindowID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShieldingWindowID_ADDR, CGShieldingWindowID_DESC)

fun CGShieldingWindowID(arg0: Int): Int {
    try {
        return CGShieldingWindowID_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGShieldingWindowLevel typedef CGWindowLevel = Int()
 */
private val CGShieldingWindowLevel_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT)
private val CGShieldingWindowLevel_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGShieldingWindowLevel").orElseThrow()
private val CGShieldingWindowLevel_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGShieldingWindowLevel_ADDR, CGShieldingWindowLevel_DESC)

fun CGShieldingWindowLevel(): Int {
    try {
        return CGShieldingWindowLevel_HANDLE.invokeExact() as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayCreateImage typedef CGImageRef = (Declared(CGImage))*(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayCreateImage_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGDisplayCreateImage_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayCreateImage").orElseThrow()
private val CGDisplayCreateImage_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayCreateImage_ADDR, CGDisplayCreateImage_DESC)

fun CGDisplayCreateImage(arg0: Int): MemorySegment {
    try {
        return CGDisplayCreateImage_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayCreateImageForRect typedef CGImageRef = (Declared(CGImage))*(typedef CGDirectDisplayID = UNSIGNED = Int,typedef CGRect = Declared(CGRect))
 */
private val CGDisplayCreateImageForRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, CGRect.layout)
private val CGDisplayCreateImageForRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayCreateImageForRect").orElseThrow()
private val CGDisplayCreateImageForRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayCreateImageForRect_ADDR, CGDisplayCreateImageForRect_DESC)

fun CGDisplayCreateImageForRect(arg0: Int, arg1: MemorySegment): MemorySegment {
    try {
        return CGDisplayCreateImageForRect_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGGetLastMouseDelta Void((typedef int32_t = Int)*,(typedef int32_t = Int)*)
 */
private val CGGetLastMouseDelta_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGGetLastMouseDelta_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGGetLastMouseDelta").orElseThrow()
private val CGGetLastMouseDelta_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGGetLastMouseDelta_ADDR, CGGetLastMouseDelta_DESC)

fun CGGetLastMouseDelta(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        CGGetLastMouseDelta_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayGetDrawingContext typedef CGContextRef = (Declared(CGContext))*(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayGetDrawingContext_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGDisplayGetDrawingContext_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayGetDrawingContext").orElseThrow()
private val CGDisplayGetDrawingContext_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayGetDrawingContext_ADDR, CGDisplayGetDrawingContext_DESC)

fun CGDisplayGetDrawingContext(arg0: Int): MemorySegment {
    try {
        return CGDisplayGetDrawingContext_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayAvailableModes typedef CFArrayRef = (Declared(__CFArray))*(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayAvailableModes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGDisplayAvailableModes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayAvailableModes").orElseThrow()
private val CGDisplayAvailableModes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayAvailableModes_ADDR, CGDisplayAvailableModes_DESC)

fun CGDisplayAvailableModes(arg0: Int): MemorySegment {
    try {
        return CGDisplayAvailableModes_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayBestModeForParameters typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CGDirectDisplayID = UNSIGNED = Int,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,(typedef boolean_t = Int)*)
 */
private val CGDisplayBestModeForParameters_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGDisplayBestModeForParameters_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayBestModeForParameters").orElseThrow()
private val CGDisplayBestModeForParameters_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayBestModeForParameters_ADDR, CGDisplayBestModeForParameters_DESC)

fun CGDisplayBestModeForParameters(arg0: Int, arg1: Long, arg2: Long, arg3: Long, arg4: MemorySegment): MemorySegment {
    try {
        return CGDisplayBestModeForParameters_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayBestModeForParametersAndRefreshRate typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CGDirectDisplayID = UNSIGNED = Int,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef size_t = UNSIGNED = Long,typedef CGRefreshRate = Double,(typedef boolean_t = Int)*)
 */
private val CGDisplayBestModeForParametersAndRefreshRate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.JAVA_DOUBLE, ValueLayout.ADDRESS)
private val CGDisplayBestModeForParametersAndRefreshRate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayBestModeForParametersAndRefreshRate").orElseThrow()
private val CGDisplayBestModeForParametersAndRefreshRate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayBestModeForParametersAndRefreshRate_ADDR, CGDisplayBestModeForParametersAndRefreshRate_DESC)

fun CGDisplayBestModeForParametersAndRefreshRate(arg0: Int, arg1: Long, arg2: Long, arg3: Long, arg4: Double, arg5: MemorySegment): MemorySegment {
    try {
        return CGDisplayBestModeForParametersAndRefreshRate_HANDLE.invokeExact(arg0, arg1, arg2, arg3, arg4, arg5) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayCurrentMode typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayCurrentMode_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGDisplayCurrentMode_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayCurrentMode").orElseThrow()
private val CGDisplayCurrentMode_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayCurrentMode_ADDR, CGDisplayCurrentMode_DESC)

fun CGDisplayCurrentMode(arg0: Int): MemorySegment {
    try {
        return CGDisplayCurrentMode_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRestorePermanentDisplayConfiguration Void()
 */
private val CGRestorePermanentDisplayConfiguration_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid()
private val CGRestorePermanentDisplayConfiguration_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRestorePermanentDisplayConfiguration").orElseThrow()
private val CGRestorePermanentDisplayConfiguration_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRestorePermanentDisplayConfiguration_ADDR, CGRestorePermanentDisplayConfiguration_DESC)

fun CGRestorePermanentDisplayConfiguration(): Unit {
    try {
        CGRestorePermanentDisplayConfiguration_HANDLE.invokeExact()
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsActive typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsActive_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsActive_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsActive").orElseThrow()
private val CGDisplayIsActive_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsActive_ADDR, CGDisplayIsActive_DESC)

fun CGDisplayIsActive(arg0: Int): Int {
    try {
        return CGDisplayIsActive_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsAsleep typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsAsleep_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsAsleep_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsAsleep").orElseThrow()
private val CGDisplayIsAsleep_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsAsleep_ADDR, CGDisplayIsAsleep_DESC)

fun CGDisplayIsAsleep(arg0: Int): Int {
    try {
        return CGDisplayIsAsleep_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsOnline typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsOnline_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsOnline_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsOnline").orElseThrow()
private val CGDisplayIsOnline_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsOnline_ADDR, CGDisplayIsOnline_DESC)

fun CGDisplayIsOnline(arg0: Int): Int {
    try {
        return CGDisplayIsOnline_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsMain typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsMain_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsMain_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsMain").orElseThrow()
private val CGDisplayIsMain_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsMain_ADDR, CGDisplayIsMain_DESC)

fun CGDisplayIsMain(arg0: Int): Int {
    try {
        return CGDisplayIsMain_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsBuiltin typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsBuiltin_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsBuiltin_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsBuiltin").orElseThrow()
private val CGDisplayIsBuiltin_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsBuiltin_ADDR, CGDisplayIsBuiltin_DESC)

fun CGDisplayIsBuiltin(arg0: Int): Int {
    try {
        return CGDisplayIsBuiltin_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsInMirrorSet typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsInMirrorSet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsInMirrorSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsInMirrorSet").orElseThrow()
private val CGDisplayIsInMirrorSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsInMirrorSet_ADDR, CGDisplayIsInMirrorSet_DESC)

fun CGDisplayIsInMirrorSet(arg0: Int): Int {
    try {
        return CGDisplayIsInMirrorSet_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsAlwaysInMirrorSet typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsAlwaysInMirrorSet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsAlwaysInMirrorSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsAlwaysInMirrorSet").orElseThrow()
private val CGDisplayIsAlwaysInMirrorSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsAlwaysInMirrorSet_ADDR, CGDisplayIsAlwaysInMirrorSet_DESC)

fun CGDisplayIsAlwaysInMirrorSet(arg0: Int): Int {
    try {
        return CGDisplayIsAlwaysInMirrorSet_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsInHWMirrorSet typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsInHWMirrorSet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsInHWMirrorSet_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsInHWMirrorSet").orElseThrow()
private val CGDisplayIsInHWMirrorSet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsInHWMirrorSet_ADDR, CGDisplayIsInHWMirrorSet_DESC)

fun CGDisplayIsInHWMirrorSet(arg0: Int): Int {
    try {
        return CGDisplayIsInHWMirrorSet_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayMirrorsDisplay typedef CGDirectDisplayID = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayMirrorsDisplay_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayMirrorsDisplay_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayMirrorsDisplay").orElseThrow()
private val CGDisplayMirrorsDisplay_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayMirrorsDisplay_ADDR, CGDisplayMirrorsDisplay_DESC)

fun CGDisplayMirrorsDisplay(arg0: Int): Int {
    try {
        return CGDisplayMirrorsDisplay_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayUsesOpenGLAcceleration typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayUsesOpenGLAcceleration_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayUsesOpenGLAcceleration_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayUsesOpenGLAcceleration").orElseThrow()
private val CGDisplayUsesOpenGLAcceleration_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayUsesOpenGLAcceleration_ADDR, CGDisplayUsesOpenGLAcceleration_DESC)

fun CGDisplayUsesOpenGLAcceleration(arg0: Int): Int {
    try {
        return CGDisplayUsesOpenGLAcceleration_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIsStereo typedef boolean_t = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIsStereo_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIsStereo_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIsStereo").orElseThrow()
private val CGDisplayIsStereo_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIsStereo_ADDR, CGDisplayIsStereo_DESC)

fun CGDisplayIsStereo(arg0: Int): Int {
    try {
        return CGDisplayIsStereo_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayPrimaryDisplay typedef CGDirectDisplayID = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayPrimaryDisplay_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayPrimaryDisplay_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayPrimaryDisplay").orElseThrow()
private val CGDisplayPrimaryDisplay_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayPrimaryDisplay_ADDR, CGDisplayPrimaryDisplay_DESC)

fun CGDisplayPrimaryDisplay(arg0: Int): Int {
    try {
        return CGDisplayPrimaryDisplay_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayUnitNumber typedef uint32_t = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayUnitNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayUnitNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayUnitNumber").orElseThrow()
private val CGDisplayUnitNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayUnitNumber_ADDR, CGDisplayUnitNumber_DESC)

fun CGDisplayUnitNumber(arg0: Int): Int {
    try {
        return CGDisplayUnitNumber_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayVendorNumber typedef uint32_t = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayVendorNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayVendorNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayVendorNumber").orElseThrow()
private val CGDisplayVendorNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayVendorNumber_ADDR, CGDisplayVendorNumber_DESC)

fun CGDisplayVendorNumber(arg0: Int): Int {
    try {
        return CGDisplayVendorNumber_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayModelNumber typedef uint32_t = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayModelNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayModelNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayModelNumber").orElseThrow()
private val CGDisplayModelNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayModelNumber_ADDR, CGDisplayModelNumber_DESC)

fun CGDisplayModelNumber(arg0: Int): Int {
    try {
        return CGDisplayModelNumber_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplaySerialNumber typedef uint32_t = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplaySerialNumber_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplaySerialNumber_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplaySerialNumber").orElseThrow()
private val CGDisplaySerialNumber_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplaySerialNumber_ADDR, CGDisplaySerialNumber_DESC)

fun CGDisplaySerialNumber(arg0: Int): Int {
    try {
        return CGDisplaySerialNumber_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayIOServicePort typedef io_service_t = UNSIGNED = Int(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayIOServicePort_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
private val CGDisplayIOServicePort_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayIOServicePort").orElseThrow()
private val CGDisplayIOServicePort_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayIOServicePort_ADDR, CGDisplayIOServicePort_DESC)

fun CGDisplayIOServicePort(arg0: Int): Int {
    try {
        return CGDisplayIOServicePort_HANDLE.invokeExact(arg0) as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayScreenSize typedef CGSize = Declared(CGSize)(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayScreenSize_DESC: FunctionDescriptor = FunctionDescriptor.of(CGSize.layout, ValueLayout.JAVA_INT)
private val CGDisplayScreenSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayScreenSize").orElseThrow()
private val CGDisplayScreenSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayScreenSize_ADDR, CGDisplayScreenSize_DESC)

fun CGDisplayScreenSize(allocator: SegmentAllocator, arg0: Int): MemorySegment {
    try {
        return CGDisplayScreenSize_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayRotation Double(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayRotation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_INT)
private val CGDisplayRotation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayRotation").orElseThrow()
private val CGDisplayRotation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayRotation_ADDR, CGDisplayRotation_DESC)

fun CGDisplayRotation(arg0: Int): Double {
    try {
        return CGDisplayRotation_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayCopyColorSpace typedef CGColorSpaceRef = (Declared(CGColorSpace))*(typedef CGDirectDisplayID = UNSIGNED = Int)
 */
private val CGDisplayCopyColorSpace_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
private val CGDisplayCopyColorSpace_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayCopyColorSpace").orElseThrow()
private val CGDisplayCopyColorSpace_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayCopyColorSpace_ADDR, CGDisplayCopyColorSpace_DESC)

fun CGDisplayCopyColorSpace(arg0: Int): MemorySegment {
    try {
        return CGDisplayCopyColorSpace_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayFadeOperationInProgress typedef boolean_t = Int()
 */
private val CGDisplayFadeOperationInProgress_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT)
private val CGDisplayFadeOperationInProgress_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayFadeOperationInProgress").orElseThrow()
private val CGDisplayFadeOperationInProgress_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayFadeOperationInProgress_ADDR, CGDisplayFadeOperationInProgress_DESC)

fun CGDisplayFadeOperationInProgress(): Int {
    try {
        return CGDisplayFadeOperationInProgress_HANDLE.invokeExact() as Int
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayStreamUpdateGetTypeID typedef CFTypeID = UNSIGNED = Long()
 */
private val CGDisplayStreamUpdateGetTypeID_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG)
private val CGDisplayStreamUpdateGetTypeID_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayStreamUpdateGetTypeID").orElseThrow()
private val CGDisplayStreamUpdateGetTypeID_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayStreamUpdateGetTypeID_ADDR, CGDisplayStreamUpdateGetTypeID_DESC)

fun CGDisplayStreamUpdateGetTypeID(): Long {
    try {
        return CGDisplayStreamUpdateGetTypeID_HANDLE.invokeExact() as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayStreamUpdateCreateMergedUpdate typedef CGDisplayStreamUpdateRef = (Declared(CGDisplayStreamUpdate))*(typedef CGDisplayStreamUpdateRef = (Declared(CGDisplayStreamUpdate))*,typedef CGDisplayStreamUpdateRef = (Declared(CGDisplayStreamUpdate))*)
 */
private val CGDisplayStreamUpdateCreateMergedUpdate_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDisplayStreamUpdateCreateMergedUpdate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayStreamUpdateCreateMergedUpdate").orElseThrow()
private val CGDisplayStreamUpdateCreateMergedUpdate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayStreamUpdateCreateMergedUpdate_ADDR, CGDisplayStreamUpdateCreateMergedUpdate_DESC)

fun CGDisplayStreamUpdateCreateMergedUpdate(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGDisplayStreamUpdateCreateMergedUpdate_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayStreamUpdateGetMovedRectsDelta Void(typedef CGDisplayStreamUpdateRef = (Declared(CGDisplayStreamUpdate))*,(typedef CGFloat = Double)*,(typedef CGFloat = Double)*)
 */
private val CGDisplayStreamUpdateGetMovedRectsDelta_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGDisplayStreamUpdateGetMovedRectsDelta_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayStreamUpdateGetMovedRectsDelta").orElseThrow()
private val CGDisplayStreamUpdateGetMovedRectsDelta_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayStreamUpdateGetMovedRectsDelta_ADDR, CGDisplayStreamUpdateGetMovedRectsDelta_DESC)

fun CGDisplayStreamUpdateGetMovedRectsDelta(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        CGDisplayStreamUpdateGetMovedRectsDelta_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGDisplayStreamUpdateGetDropCount typedef size_t = UNSIGNED = Long(typedef CGDisplayStreamUpdateRef = (Declared(CGDisplayStreamUpdate))*)
 */
private val CGDisplayStreamUpdateGetDropCount_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val CGDisplayStreamUpdateGetDropCount_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGDisplayStreamUpdateGetDropCount").orElseThrow()
private val CGDisplayStreamUpdateGetDropCount_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGDisplayStreamUpdateGetDropCount_ADDR, CGDisplayStreamUpdateGetDropCount_DESC)

fun CGDisplayStreamUpdateGetDropCount(arg0: MemorySegment): Long {
    try {
        return CGDisplayStreamUpdateGetDropCount_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCGDisplayStreamSourceRect typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamSourceRect_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamSourceRect_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamSourceRect").orElseThrow() }
private val kCGDisplayStreamSourceRect_VH: VarHandle by lazy { kCGDisplayStreamSourceRect_LAYOUT.varHandle() }

var kCGDisplayStreamSourceRect: MemorySegment
    get() = kCGDisplayStreamSourceRect_VH.get(kCGDisplayStreamSourceRect_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamSourceRect_VH.set(kCGDisplayStreamSourceRect_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDisplayStreamDestinationRect typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamDestinationRect_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamDestinationRect_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamDestinationRect").orElseThrow() }
private val kCGDisplayStreamDestinationRect_VH: VarHandle by lazy { kCGDisplayStreamDestinationRect_LAYOUT.varHandle() }

var kCGDisplayStreamDestinationRect: MemorySegment
    get() = kCGDisplayStreamDestinationRect_VH.get(kCGDisplayStreamDestinationRect_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamDestinationRect_VH.set(kCGDisplayStreamDestinationRect_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDisplayStreamPreserveAspectRatio typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamPreserveAspectRatio_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamPreserveAspectRatio_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamPreserveAspectRatio").orElseThrow() }
private val kCGDisplayStreamPreserveAspectRatio_VH: VarHandle by lazy { kCGDisplayStreamPreserveAspectRatio_LAYOUT.varHandle() }

var kCGDisplayStreamPreserveAspectRatio: MemorySegment
    get() = kCGDisplayStreamPreserveAspectRatio_VH.get(kCGDisplayStreamPreserveAspectRatio_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamPreserveAspectRatio_VH.set(kCGDisplayStreamPreserveAspectRatio_SEGMENT, value)

/**
 * {@snippet lang=c : kCGDisplayStreamColorSpace typedef const CFStringRef = (Declared(__CFString))*
 */
private val kCGDisplayStreamColorSpace_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCGDisplayStreamColorSpace_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCGDisplayStreamColorSpace").orElseThrow() }
private val kCGDisplayStreamColorSpace_VH: VarHandle by lazy { kCGDisplayStreamColorSpace_LAYOUT.varHandle() }

var kCGDisplayStreamColorSpace: MemorySegment
    get() = kCGDisplayStreamColorSpace_VH.get(kCGDisplayStreamColorSpace_SEGMENT) as MemorySegment
    set(value) = kCGDisplayStreamColorSpace_VH.set(kCGDisplayStreamColorSpace_SEGMENT, value)

