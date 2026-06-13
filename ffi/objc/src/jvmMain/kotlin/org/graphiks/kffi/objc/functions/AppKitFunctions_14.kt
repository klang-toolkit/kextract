package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : NSUnderlineColorAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSUnderlineColorAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUnderlineColorAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUnderlineColorAttributeName").orElseThrow() }
private val NSUnderlineColorAttributeName_VH: VarHandle by lazy { NSUnderlineColorAttributeName_LAYOUT.varHandle() }

var NSUnderlineColorAttributeName: MemorySegment
    get() = NSUnderlineColorAttributeName_VH.get(NSUnderlineColorAttributeName_SEGMENT) as MemorySegment
    set(value) = NSUnderlineColorAttributeName_VH.set(NSUnderlineColorAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSStrikethroughColorAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSStrikethroughColorAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStrikethroughColorAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStrikethroughColorAttributeName").orElseThrow() }
private val NSStrikethroughColorAttributeName_VH: VarHandle by lazy { NSStrikethroughColorAttributeName_LAYOUT.varHandle() }

var NSStrikethroughColorAttributeName: MemorySegment
    get() = NSStrikethroughColorAttributeName_VH.get(NSStrikethroughColorAttributeName_SEGMENT) as MemorySegment
    set(value) = NSStrikethroughColorAttributeName_VH.set(NSStrikethroughColorAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSWritingDirectionAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSWritingDirectionAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWritingDirectionAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWritingDirectionAttributeName").orElseThrow() }
private val NSWritingDirectionAttributeName_VH: VarHandle by lazy { NSWritingDirectionAttributeName_LAYOUT.varHandle() }

var NSWritingDirectionAttributeName: MemorySegment
    get() = NSWritingDirectionAttributeName_VH.get(NSWritingDirectionAttributeName_SEGMENT) as MemorySegment
    set(value) = NSWritingDirectionAttributeName_VH.set(NSWritingDirectionAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextHighlightStyleAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSTextHighlightStyleAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextHighlightStyleAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextHighlightStyleAttributeName").orElseThrow() }
private val NSTextHighlightStyleAttributeName_VH: VarHandle by lazy { NSTextHighlightStyleAttributeName_LAYOUT.varHandle() }

var NSTextHighlightStyleAttributeName: MemorySegment
    get() = NSTextHighlightStyleAttributeName_VH.get(NSTextHighlightStyleAttributeName_SEGMENT) as MemorySegment
    set(value) = NSTextHighlightStyleAttributeName_VH.set(NSTextHighlightStyleAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextHighlightColorSchemeAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSTextHighlightColorSchemeAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextHighlightColorSchemeAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextHighlightColorSchemeAttributeName").orElseThrow() }
private val NSTextHighlightColorSchemeAttributeName_VH: VarHandle by lazy { NSTextHighlightColorSchemeAttributeName_LAYOUT.varHandle() }

var NSTextHighlightColorSchemeAttributeName: MemorySegment
    get() = NSTextHighlightColorSchemeAttributeName_VH.get(NSTextHighlightColorSchemeAttributeName_SEGMENT) as MemorySegment
    set(value) = NSTextHighlightColorSchemeAttributeName_VH.set(NSTextHighlightColorSchemeAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSAdaptiveImageGlyphAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSAdaptiveImageGlyphAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAdaptiveImageGlyphAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAdaptiveImageGlyphAttributeName").orElseThrow() }
private val NSAdaptiveImageGlyphAttributeName_VH: VarHandle by lazy { NSAdaptiveImageGlyphAttributeName_LAYOUT.varHandle() }

var NSAdaptiveImageGlyphAttributeName: MemorySegment
    get() = NSAdaptiveImageGlyphAttributeName_VH.get(NSAdaptiveImageGlyphAttributeName_SEGMENT) as MemorySegment
    set(value) = NSAdaptiveImageGlyphAttributeName_VH.set(NSAdaptiveImageGlyphAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSWritingToolsExclusionAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSWritingToolsExclusionAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWritingToolsExclusionAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWritingToolsExclusionAttributeName").orElseThrow() }
private val NSWritingToolsExclusionAttributeName_VH: VarHandle by lazy { NSWritingToolsExclusionAttributeName_LAYOUT.varHandle() }

var NSWritingToolsExclusionAttributeName: MemorySegment
    get() = NSWritingToolsExclusionAttributeName_VH.get(NSWritingToolsExclusionAttributeName_SEGMENT) as MemorySegment
    set(value) = NSWritingToolsExclusionAttributeName_VH.set(NSWritingToolsExclusionAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextEffectLetterpressStyle typedef const NSTextEffectStyle = (Void)*
 */
private val NSTextEffectLetterpressStyle_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextEffectLetterpressStyle_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextEffectLetterpressStyle").orElseThrow() }
private val NSTextEffectLetterpressStyle_VH: VarHandle by lazy { NSTextEffectLetterpressStyle_LAYOUT.varHandle() }

var NSTextEffectLetterpressStyle: MemorySegment
    get() = NSTextEffectLetterpressStyle_VH.get(NSTextEffectLetterpressStyle_SEGMENT) as MemorySegment
    set(value) = NSTextEffectLetterpressStyle_VH.set(NSTextEffectLetterpressStyle_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextHighlightStyleDefault typedef const NSTextHighlightStyle = (Void)*
 */
private val NSTextHighlightStyleDefault_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextHighlightStyleDefault_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextHighlightStyleDefault").orElseThrow() }
private val NSTextHighlightStyleDefault_VH: VarHandle by lazy { NSTextHighlightStyleDefault_LAYOUT.varHandle() }

var NSTextHighlightStyleDefault: MemorySegment
    get() = NSTextHighlightStyleDefault_VH.get(NSTextHighlightStyleDefault_SEGMENT) as MemorySegment
    set(value) = NSTextHighlightStyleDefault_VH.set(NSTextHighlightStyleDefault_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextHighlightColorSchemeDefault typedef const NSTextHighlightColorScheme = (Void)*
 */
private val NSTextHighlightColorSchemeDefault_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextHighlightColorSchemeDefault_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextHighlightColorSchemeDefault").orElseThrow() }
private val NSTextHighlightColorSchemeDefault_VH: VarHandle by lazy { NSTextHighlightColorSchemeDefault_LAYOUT.varHandle() }

var NSTextHighlightColorSchemeDefault: MemorySegment
    get() = NSTextHighlightColorSchemeDefault_VH.get(NSTextHighlightColorSchemeDefault_SEGMENT) as MemorySegment
    set(value) = NSTextHighlightColorSchemeDefault_VH.set(NSTextHighlightColorSchemeDefault_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextHighlightColorSchemePurple typedef const NSTextHighlightColorScheme = (Void)*
 */
private val NSTextHighlightColorSchemePurple_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextHighlightColorSchemePurple_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextHighlightColorSchemePurple").orElseThrow() }
private val NSTextHighlightColorSchemePurple_VH: VarHandle by lazy { NSTextHighlightColorSchemePurple_LAYOUT.varHandle() }

var NSTextHighlightColorSchemePurple: MemorySegment
    get() = NSTextHighlightColorSchemePurple_VH.get(NSTextHighlightColorSchemePurple_SEGMENT) as MemorySegment
    set(value) = NSTextHighlightColorSchemePurple_VH.set(NSTextHighlightColorSchemePurple_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextHighlightColorSchemePink typedef const NSTextHighlightColorScheme = (Void)*
 */
private val NSTextHighlightColorSchemePink_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextHighlightColorSchemePink_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextHighlightColorSchemePink").orElseThrow() }
private val NSTextHighlightColorSchemePink_VH: VarHandle by lazy { NSTextHighlightColorSchemePink_LAYOUT.varHandle() }

var NSTextHighlightColorSchemePink: MemorySegment
    get() = NSTextHighlightColorSchemePink_VH.get(NSTextHighlightColorSchemePink_SEGMENT) as MemorySegment
    set(value) = NSTextHighlightColorSchemePink_VH.set(NSTextHighlightColorSchemePink_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextHighlightColorSchemeOrange typedef const NSTextHighlightColorScheme = (Void)*
 */
private val NSTextHighlightColorSchemeOrange_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextHighlightColorSchemeOrange_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextHighlightColorSchemeOrange").orElseThrow() }
private val NSTextHighlightColorSchemeOrange_VH: VarHandle by lazy { NSTextHighlightColorSchemeOrange_LAYOUT.varHandle() }

var NSTextHighlightColorSchemeOrange: MemorySegment
    get() = NSTextHighlightColorSchemeOrange_VH.get(NSTextHighlightColorSchemeOrange_SEGMENT) as MemorySegment
    set(value) = NSTextHighlightColorSchemeOrange_VH.set(NSTextHighlightColorSchemeOrange_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextHighlightColorSchemeMint typedef const NSTextHighlightColorScheme = (Void)*
 */
private val NSTextHighlightColorSchemeMint_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextHighlightColorSchemeMint_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextHighlightColorSchemeMint").orElseThrow() }
private val NSTextHighlightColorSchemeMint_VH: VarHandle by lazy { NSTextHighlightColorSchemeMint_LAYOUT.varHandle() }

var NSTextHighlightColorSchemeMint: MemorySegment
    get() = NSTextHighlightColorSchemeMint_VH.get(NSTextHighlightColorSchemeMint_SEGMENT) as MemorySegment
    set(value) = NSTextHighlightColorSchemeMint_VH.set(NSTextHighlightColorSchemeMint_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextHighlightColorSchemeBlue typedef const NSTextHighlightColorScheme = (Void)*
 */
private val NSTextHighlightColorSchemeBlue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextHighlightColorSchemeBlue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextHighlightColorSchemeBlue").orElseThrow() }
private val NSTextHighlightColorSchemeBlue_VH: VarHandle by lazy { NSTextHighlightColorSchemeBlue_LAYOUT.varHandle() }

var NSTextHighlightColorSchemeBlue: MemorySegment
    get() = NSTextHighlightColorSchemeBlue_VH.get(NSTextHighlightColorSchemeBlue_SEGMENT) as MemorySegment
    set(value) = NSTextHighlightColorSchemeBlue_VH.set(NSTextHighlightColorSchemeBlue_SEGMENT, value)

/**
 * {@snippet lang=c : NSPlainTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSPlainTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPlainTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPlainTextDocumentType").orElseThrow() }
private val NSPlainTextDocumentType_VH: VarHandle by lazy { NSPlainTextDocumentType_LAYOUT.varHandle() }

var NSPlainTextDocumentType: MemorySegment
    get() = NSPlainTextDocumentType_VH.get(NSPlainTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSPlainTextDocumentType_VH.set(NSPlainTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSRTFTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSRTFTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRTFTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRTFTextDocumentType").orElseThrow() }
private val NSRTFTextDocumentType_VH: VarHandle by lazy { NSRTFTextDocumentType_LAYOUT.varHandle() }

var NSRTFTextDocumentType: MemorySegment
    get() = NSRTFTextDocumentType_VH.get(NSRTFTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSRTFTextDocumentType_VH.set(NSRTFTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSRTFDTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSRTFDTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRTFDTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRTFDTextDocumentType").orElseThrow() }
private val NSRTFDTextDocumentType_VH: VarHandle by lazy { NSRTFDTextDocumentType_LAYOUT.varHandle() }

var NSRTFDTextDocumentType: MemorySegment
    get() = NSRTFDTextDocumentType_VH.get(NSRTFDTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSRTFDTextDocumentType_VH.set(NSRTFDTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTMLTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSHTMLTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTMLTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTMLTextDocumentType").orElseThrow() }
private val NSHTMLTextDocumentType_VH: VarHandle by lazy { NSHTMLTextDocumentType_LAYOUT.varHandle() }

var NSHTMLTextDocumentType: MemorySegment
    get() = NSHTMLTextDocumentType_VH.get(NSHTMLTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSHTMLTextDocumentType_VH.set(NSHTMLTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextLayoutSectionOrientation typedef NSTextLayoutSectionKey = typedef NSString = (Void)*
 */
private val NSTextLayoutSectionOrientation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextLayoutSectionOrientation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextLayoutSectionOrientation").orElseThrow() }
private val NSTextLayoutSectionOrientation_VH: VarHandle by lazy { NSTextLayoutSectionOrientation_LAYOUT.varHandle() }

var NSTextLayoutSectionOrientation: MemorySegment
    get() = NSTextLayoutSectionOrientation_VH.get(NSTextLayoutSectionOrientation_SEGMENT) as MemorySegment
    set(value) = NSTextLayoutSectionOrientation_VH.set(NSTextLayoutSectionOrientation_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextLayoutSectionRange typedef NSTextLayoutSectionKey = typedef NSString = (Void)*
 */
private val NSTextLayoutSectionRange_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextLayoutSectionRange_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextLayoutSectionRange").orElseThrow() }
private val NSTextLayoutSectionRange_VH: VarHandle by lazy { NSTextLayoutSectionRange_LAYOUT.varHandle() }

var NSTextLayoutSectionRange: MemorySegment
    get() = NSTextLayoutSectionRange_VH.get(NSTextLayoutSectionRange_SEGMENT) as MemorySegment
    set(value) = NSTextLayoutSectionRange_VH.set(NSTextLayoutSectionRange_SEGMENT, value)

/**
 * {@snippet lang=c : NSDocumentTypeDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSDocumentTypeDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDocumentTypeDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDocumentTypeDocumentAttribute").orElseThrow() }
private val NSDocumentTypeDocumentAttribute_VH: VarHandle by lazy { NSDocumentTypeDocumentAttribute_LAYOUT.varHandle() }

var NSDocumentTypeDocumentAttribute: MemorySegment
    get() = NSDocumentTypeDocumentAttribute_VH.get(NSDocumentTypeDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSDocumentTypeDocumentAttribute_VH.set(NSDocumentTypeDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSCharacterEncodingDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSCharacterEncodingDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCharacterEncodingDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCharacterEncodingDocumentAttribute").orElseThrow() }
private val NSCharacterEncodingDocumentAttribute_VH: VarHandle by lazy { NSCharacterEncodingDocumentAttribute_LAYOUT.varHandle() }

var NSCharacterEncodingDocumentAttribute: MemorySegment
    get() = NSCharacterEncodingDocumentAttribute_VH.get(NSCharacterEncodingDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSCharacterEncodingDocumentAttribute_VH.set(NSCharacterEncodingDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSDefaultAttributesDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSDefaultAttributesDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDefaultAttributesDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDefaultAttributesDocumentAttribute").orElseThrow() }
private val NSDefaultAttributesDocumentAttribute_VH: VarHandle by lazy { NSDefaultAttributesDocumentAttribute_LAYOUT.varHandle() }

var NSDefaultAttributesDocumentAttribute: MemorySegment
    get() = NSDefaultAttributesDocumentAttribute_VH.get(NSDefaultAttributesDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSDefaultAttributesDocumentAttribute_VH.set(NSDefaultAttributesDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSPaperSizeDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSPaperSizeDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPaperSizeDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPaperSizeDocumentAttribute").orElseThrow() }
private val NSPaperSizeDocumentAttribute_VH: VarHandle by lazy { NSPaperSizeDocumentAttribute_LAYOUT.varHandle() }

var NSPaperSizeDocumentAttribute: MemorySegment
    get() = NSPaperSizeDocumentAttribute_VH.get(NSPaperSizeDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSPaperSizeDocumentAttribute_VH.set(NSPaperSizeDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewSizeDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSViewSizeDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewSizeDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewSizeDocumentAttribute").orElseThrow() }
private val NSViewSizeDocumentAttribute_VH: VarHandle by lazy { NSViewSizeDocumentAttribute_LAYOUT.varHandle() }

var NSViewSizeDocumentAttribute: MemorySegment
    get() = NSViewSizeDocumentAttribute_VH.get(NSViewSizeDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSViewSizeDocumentAttribute_VH.set(NSViewSizeDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewZoomDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSViewZoomDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewZoomDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewZoomDocumentAttribute").orElseThrow() }
private val NSViewZoomDocumentAttribute_VH: VarHandle by lazy { NSViewZoomDocumentAttribute_LAYOUT.varHandle() }

var NSViewZoomDocumentAttribute: MemorySegment
    get() = NSViewZoomDocumentAttribute_VH.get(NSViewZoomDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSViewZoomDocumentAttribute_VH.set(NSViewZoomDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSViewModeDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSViewModeDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSViewModeDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSViewModeDocumentAttribute").orElseThrow() }
private val NSViewModeDocumentAttribute_VH: VarHandle by lazy { NSViewModeDocumentAttribute_LAYOUT.varHandle() }

var NSViewModeDocumentAttribute: MemorySegment
    get() = NSViewModeDocumentAttribute_VH.get(NSViewModeDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSViewModeDocumentAttribute_VH.set(NSViewModeDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSDefaultFontExcludedDocumentAttribute typedef const NSAttributedStringDocumentAttributeKey = (Void)*
 */
private val NSDefaultFontExcludedDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDefaultFontExcludedDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDefaultFontExcludedDocumentAttribute").orElseThrow() }
private val NSDefaultFontExcludedDocumentAttribute_VH: VarHandle by lazy { NSDefaultFontExcludedDocumentAttribute_LAYOUT.varHandle() }

var NSDefaultFontExcludedDocumentAttribute: MemorySegment
    get() = NSDefaultFontExcludedDocumentAttribute_VH.get(NSDefaultFontExcludedDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSDefaultFontExcludedDocumentAttribute_VH.set(NSDefaultFontExcludedDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSReadOnlyDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSReadOnlyDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSReadOnlyDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSReadOnlyDocumentAttribute").orElseThrow() }
private val NSReadOnlyDocumentAttribute_VH: VarHandle by lazy { NSReadOnlyDocumentAttribute_LAYOUT.varHandle() }

var NSReadOnlyDocumentAttribute: MemorySegment
    get() = NSReadOnlyDocumentAttribute_VH.get(NSReadOnlyDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSReadOnlyDocumentAttribute_VH.set(NSReadOnlyDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSBackgroundColorDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSBackgroundColorDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBackgroundColorDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBackgroundColorDocumentAttribute").orElseThrow() }
private val NSBackgroundColorDocumentAttribute_VH: VarHandle by lazy { NSBackgroundColorDocumentAttribute_LAYOUT.varHandle() }

var NSBackgroundColorDocumentAttribute: MemorySegment
    get() = NSBackgroundColorDocumentAttribute_VH.get(NSBackgroundColorDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSBackgroundColorDocumentAttribute_VH.set(NSBackgroundColorDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSHyphenationFactorDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSHyphenationFactorDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHyphenationFactorDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHyphenationFactorDocumentAttribute").orElseThrow() }
private val NSHyphenationFactorDocumentAttribute_VH: VarHandle by lazy { NSHyphenationFactorDocumentAttribute_LAYOUT.varHandle() }

var NSHyphenationFactorDocumentAttribute: MemorySegment
    get() = NSHyphenationFactorDocumentAttribute_VH.get(NSHyphenationFactorDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSHyphenationFactorDocumentAttribute_VH.set(NSHyphenationFactorDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSDefaultTabIntervalDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSDefaultTabIntervalDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDefaultTabIntervalDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDefaultTabIntervalDocumentAttribute").orElseThrow() }
private val NSDefaultTabIntervalDocumentAttribute_VH: VarHandle by lazy { NSDefaultTabIntervalDocumentAttribute_LAYOUT.varHandle() }

var NSDefaultTabIntervalDocumentAttribute: MemorySegment
    get() = NSDefaultTabIntervalDocumentAttribute_VH.get(NSDefaultTabIntervalDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSDefaultTabIntervalDocumentAttribute_VH.set(NSDefaultTabIntervalDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextLayoutSectionsAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSTextLayoutSectionsAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextLayoutSectionsAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextLayoutSectionsAttribute").orElseThrow() }
private val NSTextLayoutSectionsAttribute_VH: VarHandle by lazy { NSTextLayoutSectionsAttribute_LAYOUT.varHandle() }

var NSTextLayoutSectionsAttribute: MemorySegment
    get() = NSTextLayoutSectionsAttribute_VH.get(NSTextLayoutSectionsAttribute_SEGMENT) as MemorySegment
    set(value) = NSTextLayoutSectionsAttribute_VH.set(NSTextLayoutSectionsAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextScalingDocumentAttribute typedef const NSAttributedStringDocumentAttributeKey = (Void)*
 */
private val NSTextScalingDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextScalingDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextScalingDocumentAttribute").orElseThrow() }
private val NSTextScalingDocumentAttribute_VH: VarHandle by lazy { NSTextScalingDocumentAttribute_LAYOUT.varHandle() }

var NSTextScalingDocumentAttribute: MemorySegment
    get() = NSTextScalingDocumentAttribute_VH.get(NSTextScalingDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSTextScalingDocumentAttribute_VH.set(NSTextScalingDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSSourceTextScalingDocumentAttribute typedef const NSAttributedStringDocumentAttributeKey = (Void)*
 */
private val NSSourceTextScalingDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSourceTextScalingDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSourceTextScalingDocumentAttribute").orElseThrow() }
private val NSSourceTextScalingDocumentAttribute_VH: VarHandle by lazy { NSSourceTextScalingDocumentAttribute_LAYOUT.varHandle() }

var NSSourceTextScalingDocumentAttribute: MemorySegment
    get() = NSSourceTextScalingDocumentAttribute_VH.get(NSSourceTextScalingDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSSourceTextScalingDocumentAttribute_VH.set(NSSourceTextScalingDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSCocoaVersionDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSCocoaVersionDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCocoaVersionDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCocoaVersionDocumentAttribute").orElseThrow() }
private val NSCocoaVersionDocumentAttribute_VH: VarHandle by lazy { NSCocoaVersionDocumentAttribute_LAYOUT.varHandle() }

var NSCocoaVersionDocumentAttribute: MemorySegment
    get() = NSCocoaVersionDocumentAttribute_VH.get(NSCocoaVersionDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSCocoaVersionDocumentAttribute_VH.set(NSCocoaVersionDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSDocumentTypeDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSDocumentTypeDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDocumentTypeDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDocumentTypeDocumentOption").orElseThrow() }
private val NSDocumentTypeDocumentOption_VH: VarHandle by lazy { NSDocumentTypeDocumentOption_LAYOUT.varHandle() }

var NSDocumentTypeDocumentOption: MemorySegment
    get() = NSDocumentTypeDocumentOption_VH.get(NSDocumentTypeDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSDocumentTypeDocumentOption_VH.set(NSDocumentTypeDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSDefaultAttributesDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSDefaultAttributesDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDefaultAttributesDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDefaultAttributesDocumentOption").orElseThrow() }
private val NSDefaultAttributesDocumentOption_VH: VarHandle by lazy { NSDefaultAttributesDocumentOption_LAYOUT.varHandle() }

var NSDefaultAttributesDocumentOption: MemorySegment
    get() = NSDefaultAttributesDocumentOption_VH.get(NSDefaultAttributesDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSDefaultAttributesDocumentOption_VH.set(NSDefaultAttributesDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSCharacterEncodingDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSCharacterEncodingDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCharacterEncodingDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCharacterEncodingDocumentOption").orElseThrow() }
private val NSCharacterEncodingDocumentOption_VH: VarHandle by lazy { NSCharacterEncodingDocumentOption_LAYOUT.varHandle() }

var NSCharacterEncodingDocumentOption: MemorySegment
    get() = NSCharacterEncodingDocumentOption_VH.get(NSCharacterEncodingDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSCharacterEncodingDocumentOption_VH.set(NSCharacterEncodingDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSTargetTextScalingDocumentOption typedef const NSAttributedStringDocumentReadingOptionKey = (Void)*
 */
private val NSTargetTextScalingDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTargetTextScalingDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTargetTextScalingDocumentOption").orElseThrow() }
private val NSTargetTextScalingDocumentOption_VH: VarHandle by lazy { NSTargetTextScalingDocumentOption_LAYOUT.varHandle() }

var NSTargetTextScalingDocumentOption: MemorySegment
    get() = NSTargetTextScalingDocumentOption_VH.get(NSTargetTextScalingDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSTargetTextScalingDocumentOption_VH.set(NSTargetTextScalingDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSSourceTextScalingDocumentOption typedef const NSAttributedStringDocumentReadingOptionKey = (Void)*
 */
private val NSSourceTextScalingDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSourceTextScalingDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSourceTextScalingDocumentOption").orElseThrow() }
private val NSSourceTextScalingDocumentOption_VH: VarHandle by lazy { NSSourceTextScalingDocumentOption_LAYOUT.varHandle() }

var NSSourceTextScalingDocumentOption: MemorySegment
    get() = NSSourceTextScalingDocumentOption_VH.get(NSSourceTextScalingDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSSourceTextScalingDocumentOption_VH.set(NSSourceTextScalingDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextKit1ListMarkerFormatDocumentOption typedef const NSAttributedStringDocumentReadingOptionKey = (Void)*
 */
private val NSTextKit1ListMarkerFormatDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextKit1ListMarkerFormatDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextKit1ListMarkerFormatDocumentOption").orElseThrow() }
private val NSTextKit1ListMarkerFormatDocumentOption_VH: VarHandle by lazy { NSTextKit1ListMarkerFormatDocumentOption_LAYOUT.varHandle() }

var NSTextKit1ListMarkerFormatDocumentOption: MemorySegment
    get() = NSTextKit1ListMarkerFormatDocumentOption_VH.get(NSTextKit1ListMarkerFormatDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSTextKit1ListMarkerFormatDocumentOption_VH.set(NSTextKit1ListMarkerFormatDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSCursorAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSCursorAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCursorAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCursorAttributeName").orElseThrow() }
private val NSCursorAttributeName_VH: VarHandle by lazy { NSCursorAttributeName_LAYOUT.varHandle() }

var NSCursorAttributeName: MemorySegment
    get() = NSCursorAttributeName_VH.get(NSCursorAttributeName_SEGMENT) as MemorySegment
    set(value) = NSCursorAttributeName_VH.set(NSCursorAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolTipAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSToolTipAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolTipAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolTipAttributeName").orElseThrow() }
private val NSToolTipAttributeName_VH: VarHandle by lazy { NSToolTipAttributeName_LAYOUT.varHandle() }

var NSToolTipAttributeName: MemorySegment
    get() = NSToolTipAttributeName_VH.get(NSToolTipAttributeName_SEGMENT) as MemorySegment
    set(value) = NSToolTipAttributeName_VH.set(NSToolTipAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSMarkedClauseSegmentAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSMarkedClauseSegmentAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMarkedClauseSegmentAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMarkedClauseSegmentAttributeName").orElseThrow() }
private val NSMarkedClauseSegmentAttributeName_VH: VarHandle by lazy { NSMarkedClauseSegmentAttributeName_LAYOUT.varHandle() }

var NSMarkedClauseSegmentAttributeName: MemorySegment
    get() = NSMarkedClauseSegmentAttributeName_VH.get(NSMarkedClauseSegmentAttributeName_SEGMENT) as MemorySegment
    set(value) = NSMarkedClauseSegmentAttributeName_VH.set(NSMarkedClauseSegmentAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextAlternativesAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSTextAlternativesAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextAlternativesAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextAlternativesAttributeName").orElseThrow() }
private val NSTextAlternativesAttributeName_VH: VarHandle by lazy { NSTextAlternativesAttributeName_LAYOUT.varHandle() }

var NSTextAlternativesAttributeName: MemorySegment
    get() = NSTextAlternativesAttributeName_VH.get(NSTextAlternativesAttributeName_SEGMENT) as MemorySegment
    set(value) = NSTextAlternativesAttributeName_VH.set(NSTextAlternativesAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpellingStateAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSSpellingStateAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpellingStateAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpellingStateAttributeName").orElseThrow() }
private val NSSpellingStateAttributeName_VH: VarHandle by lazy { NSSpellingStateAttributeName_LAYOUT.varHandle() }

var NSSpellingStateAttributeName: MemorySegment
    get() = NSSpellingStateAttributeName_VH.get(NSSpellingStateAttributeName_SEGMENT) as MemorySegment
    set(value) = NSSpellingStateAttributeName_VH.set(NSSpellingStateAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSSuperscriptAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSSuperscriptAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSuperscriptAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSuperscriptAttributeName").orElseThrow() }
private val NSSuperscriptAttributeName_VH: VarHandle by lazy { NSSuperscriptAttributeName_LAYOUT.varHandle() }

var NSSuperscriptAttributeName: MemorySegment
    get() = NSSuperscriptAttributeName_VH.get(NSSuperscriptAttributeName_SEGMENT) as MemorySegment
    set(value) = NSSuperscriptAttributeName_VH.set(NSSuperscriptAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSGlyphInfoAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSGlyphInfoAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGlyphInfoAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGlyphInfoAttributeName").orElseThrow() }
private val NSGlyphInfoAttributeName_VH: VarHandle by lazy { NSGlyphInfoAttributeName_LAYOUT.varHandle() }

var NSGlyphInfoAttributeName: MemorySegment
    get() = NSGlyphInfoAttributeName_VH.get(NSGlyphInfoAttributeName_SEGMENT) as MemorySegment
    set(value) = NSGlyphInfoAttributeName_VH.set(NSGlyphInfoAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSMacSimpleTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSMacSimpleTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMacSimpleTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMacSimpleTextDocumentType").orElseThrow() }
private val NSMacSimpleTextDocumentType_VH: VarHandle by lazy { NSMacSimpleTextDocumentType_LAYOUT.varHandle() }

var NSMacSimpleTextDocumentType: MemorySegment
    get() = NSMacSimpleTextDocumentType_VH.get(NSMacSimpleTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSMacSimpleTextDocumentType_VH.set(NSMacSimpleTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSDocFormatTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSDocFormatTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDocFormatTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDocFormatTextDocumentType").orElseThrow() }
private val NSDocFormatTextDocumentType_VH: VarHandle by lazy { NSDocFormatTextDocumentType_LAYOUT.varHandle() }

var NSDocFormatTextDocumentType: MemorySegment
    get() = NSDocFormatTextDocumentType_VH.get(NSDocFormatTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSDocFormatTextDocumentType_VH.set(NSDocFormatTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSWordMLTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSWordMLTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWordMLTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWordMLTextDocumentType").orElseThrow() }
private val NSWordMLTextDocumentType_VH: VarHandle by lazy { NSWordMLTextDocumentType_LAYOUT.varHandle() }

var NSWordMLTextDocumentType: MemorySegment
    get() = NSWordMLTextDocumentType_VH.get(NSWordMLTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSWordMLTextDocumentType_VH.set(NSWordMLTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSWebArchiveTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSWebArchiveTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWebArchiveTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWebArchiveTextDocumentType").orElseThrow() }
private val NSWebArchiveTextDocumentType_VH: VarHandle by lazy { NSWebArchiveTextDocumentType_LAYOUT.varHandle() }

var NSWebArchiveTextDocumentType: MemorySegment
    get() = NSWebArchiveTextDocumentType_VH.get(NSWebArchiveTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSWebArchiveTextDocumentType_VH.set(NSWebArchiveTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSOfficeOpenXMLTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSOfficeOpenXMLTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOfficeOpenXMLTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOfficeOpenXMLTextDocumentType").orElseThrow() }
private val NSOfficeOpenXMLTextDocumentType_VH: VarHandle by lazy { NSOfficeOpenXMLTextDocumentType_LAYOUT.varHandle() }

var NSOfficeOpenXMLTextDocumentType: MemorySegment
    get() = NSOfficeOpenXMLTextDocumentType_VH.get(NSOfficeOpenXMLTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSOfficeOpenXMLTextDocumentType_VH.set(NSOfficeOpenXMLTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSOpenDocumentTextDocumentType typedef NSAttributedStringDocumentType = typedef NSString = (Void)*
 */
private val NSOpenDocumentTextDocumentType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOpenDocumentTextDocumentType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOpenDocumentTextDocumentType").orElseThrow() }
private val NSOpenDocumentTextDocumentType_VH: VarHandle by lazy { NSOpenDocumentTextDocumentType_LAYOUT.varHandle() }

var NSOpenDocumentTextDocumentType: MemorySegment
    get() = NSOpenDocumentTextDocumentType_VH.get(NSOpenDocumentTextDocumentType_SEGMENT) as MemorySegment
    set(value) = NSOpenDocumentTextDocumentType_VH.set(NSOpenDocumentTextDocumentType_SEGMENT, value)

/**
 * {@snippet lang=c : NSConvertedDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSConvertedDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSConvertedDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSConvertedDocumentAttribute").orElseThrow() }
private val NSConvertedDocumentAttribute_VH: VarHandle by lazy { NSConvertedDocumentAttribute_LAYOUT.varHandle() }

var NSConvertedDocumentAttribute: MemorySegment
    get() = NSConvertedDocumentAttribute_VH.get(NSConvertedDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSConvertedDocumentAttribute_VH.set(NSConvertedDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSFileTypeDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileTypeDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileTypeDocumentAttribute").orElseThrow() }
private val NSFileTypeDocumentAttribute_VH: VarHandle by lazy { NSFileTypeDocumentAttribute_LAYOUT.varHandle() }

var NSFileTypeDocumentAttribute: MemorySegment
    get() = NSFileTypeDocumentAttribute_VH.get(NSFileTypeDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSFileTypeDocumentAttribute_VH.set(NSFileTypeDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSTitleDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSTitleDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTitleDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTitleDocumentAttribute").orElseThrow() }
private val NSTitleDocumentAttribute_VH: VarHandle by lazy { NSTitleDocumentAttribute_LAYOUT.varHandle() }

var NSTitleDocumentAttribute: MemorySegment
    get() = NSTitleDocumentAttribute_VH.get(NSTitleDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSTitleDocumentAttribute_VH.set(NSTitleDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSCompanyDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSCompanyDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCompanyDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCompanyDocumentAttribute").orElseThrow() }
private val NSCompanyDocumentAttribute_VH: VarHandle by lazy { NSCompanyDocumentAttribute_LAYOUT.varHandle() }

var NSCompanyDocumentAttribute: MemorySegment
    get() = NSCompanyDocumentAttribute_VH.get(NSCompanyDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSCompanyDocumentAttribute_VH.set(NSCompanyDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSCopyrightDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSCopyrightDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCopyrightDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCopyrightDocumentAttribute").orElseThrow() }
private val NSCopyrightDocumentAttribute_VH: VarHandle by lazy { NSCopyrightDocumentAttribute_LAYOUT.varHandle() }

var NSCopyrightDocumentAttribute: MemorySegment
    get() = NSCopyrightDocumentAttribute_VH.get(NSCopyrightDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSCopyrightDocumentAttribute_VH.set(NSCopyrightDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSSubjectDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSSubjectDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSubjectDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSubjectDocumentAttribute").orElseThrow() }
private val NSSubjectDocumentAttribute_VH: VarHandle by lazy { NSSubjectDocumentAttribute_LAYOUT.varHandle() }

var NSSubjectDocumentAttribute: MemorySegment
    get() = NSSubjectDocumentAttribute_VH.get(NSSubjectDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSSubjectDocumentAttribute_VH.set(NSSubjectDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAuthorDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSAuthorDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAuthorDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAuthorDocumentAttribute").orElseThrow() }
private val NSAuthorDocumentAttribute_VH: VarHandle by lazy { NSAuthorDocumentAttribute_LAYOUT.varHandle() }

var NSAuthorDocumentAttribute: MemorySegment
    get() = NSAuthorDocumentAttribute_VH.get(NSAuthorDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSAuthorDocumentAttribute_VH.set(NSAuthorDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSKeywordsDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSKeywordsDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSKeywordsDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSKeywordsDocumentAttribute").orElseThrow() }
private val NSKeywordsDocumentAttribute_VH: VarHandle by lazy { NSKeywordsDocumentAttribute_LAYOUT.varHandle() }

var NSKeywordsDocumentAttribute: MemorySegment
    get() = NSKeywordsDocumentAttribute_VH.get(NSKeywordsDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSKeywordsDocumentAttribute_VH.set(NSKeywordsDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSCommentDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSCommentDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCommentDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCommentDocumentAttribute").orElseThrow() }
private val NSCommentDocumentAttribute_VH: VarHandle by lazy { NSCommentDocumentAttribute_LAYOUT.varHandle() }

var NSCommentDocumentAttribute: MemorySegment
    get() = NSCommentDocumentAttribute_VH.get(NSCommentDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSCommentDocumentAttribute_VH.set(NSCommentDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSEditorDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSEditorDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSEditorDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSEditorDocumentAttribute").orElseThrow() }
private val NSEditorDocumentAttribute_VH: VarHandle by lazy { NSEditorDocumentAttribute_LAYOUT.varHandle() }

var NSEditorDocumentAttribute: MemorySegment
    get() = NSEditorDocumentAttribute_VH.get(NSEditorDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSEditorDocumentAttribute_VH.set(NSEditorDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSCreationTimeDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSCreationTimeDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCreationTimeDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCreationTimeDocumentAttribute").orElseThrow() }
private val NSCreationTimeDocumentAttribute_VH: VarHandle by lazy { NSCreationTimeDocumentAttribute_LAYOUT.varHandle() }

var NSCreationTimeDocumentAttribute: MemorySegment
    get() = NSCreationTimeDocumentAttribute_VH.get(NSCreationTimeDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSCreationTimeDocumentAttribute_VH.set(NSCreationTimeDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSModificationTimeDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSModificationTimeDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSModificationTimeDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSModificationTimeDocumentAttribute").orElseThrow() }
private val NSModificationTimeDocumentAttribute_VH: VarHandle by lazy { NSModificationTimeDocumentAttribute_LAYOUT.varHandle() }

var NSModificationTimeDocumentAttribute: MemorySegment
    get() = NSModificationTimeDocumentAttribute_VH.get(NSModificationTimeDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSModificationTimeDocumentAttribute_VH.set(NSModificationTimeDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSManagerDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSManagerDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSManagerDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSManagerDocumentAttribute").orElseThrow() }
private val NSManagerDocumentAttribute_VH: VarHandle by lazy { NSManagerDocumentAttribute_LAYOUT.varHandle() }

var NSManagerDocumentAttribute: MemorySegment
    get() = NSManagerDocumentAttribute_VH.get(NSManagerDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSManagerDocumentAttribute_VH.set(NSManagerDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSCategoryDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSCategoryDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCategoryDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCategoryDocumentAttribute").orElseThrow() }
private val NSCategoryDocumentAttribute_VH: VarHandle by lazy { NSCategoryDocumentAttribute_LAYOUT.varHandle() }

var NSCategoryDocumentAttribute: MemorySegment
    get() = NSCategoryDocumentAttribute_VH.get(NSCategoryDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSCategoryDocumentAttribute_VH.set(NSCategoryDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSAppearanceDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSAppearanceDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAppearanceDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAppearanceDocumentAttribute").orElseThrow() }
private val NSAppearanceDocumentAttribute_VH: VarHandle by lazy { NSAppearanceDocumentAttribute_LAYOUT.varHandle() }

var NSAppearanceDocumentAttribute: MemorySegment
    get() = NSAppearanceDocumentAttribute_VH.get(NSAppearanceDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSAppearanceDocumentAttribute_VH.set(NSAppearanceDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSExcludedElementsDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSExcludedElementsDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExcludedElementsDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExcludedElementsDocumentAttribute").orElseThrow() }
private val NSExcludedElementsDocumentAttribute_VH: VarHandle by lazy { NSExcludedElementsDocumentAttribute_LAYOUT.varHandle() }

var NSExcludedElementsDocumentAttribute: MemorySegment
    get() = NSExcludedElementsDocumentAttribute_VH.get(NSExcludedElementsDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSExcludedElementsDocumentAttribute_VH.set(NSExcludedElementsDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextEncodingNameDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSTextEncodingNameDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextEncodingNameDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextEncodingNameDocumentAttribute").orElseThrow() }
private val NSTextEncodingNameDocumentAttribute_VH: VarHandle by lazy { NSTextEncodingNameDocumentAttribute_LAYOUT.varHandle() }

var NSTextEncodingNameDocumentAttribute: MemorySegment
    get() = NSTextEncodingNameDocumentAttribute_VH.get(NSTextEncodingNameDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSTextEncodingNameDocumentAttribute_VH.set(NSTextEncodingNameDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrefixSpacesDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSPrefixSpacesDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrefixSpacesDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrefixSpacesDocumentAttribute").orElseThrow() }
private val NSPrefixSpacesDocumentAttribute_VH: VarHandle by lazy { NSPrefixSpacesDocumentAttribute_LAYOUT.varHandle() }

var NSPrefixSpacesDocumentAttribute: MemorySegment
    get() = NSPrefixSpacesDocumentAttribute_VH.get(NSPrefixSpacesDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSPrefixSpacesDocumentAttribute_VH.set(NSPrefixSpacesDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSLeftMarginDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSLeftMarginDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLeftMarginDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLeftMarginDocumentAttribute").orElseThrow() }
private val NSLeftMarginDocumentAttribute_VH: VarHandle by lazy { NSLeftMarginDocumentAttribute_LAYOUT.varHandle() }

var NSLeftMarginDocumentAttribute: MemorySegment
    get() = NSLeftMarginDocumentAttribute_VH.get(NSLeftMarginDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSLeftMarginDocumentAttribute_VH.set(NSLeftMarginDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSRightMarginDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSRightMarginDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRightMarginDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRightMarginDocumentAttribute").orElseThrow() }
private val NSRightMarginDocumentAttribute_VH: VarHandle by lazy { NSRightMarginDocumentAttribute_LAYOUT.varHandle() }

var NSRightMarginDocumentAttribute: MemorySegment
    get() = NSRightMarginDocumentAttribute_VH.get(NSRightMarginDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSRightMarginDocumentAttribute_VH.set(NSRightMarginDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSTopMarginDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSTopMarginDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTopMarginDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTopMarginDocumentAttribute").orElseThrow() }
private val NSTopMarginDocumentAttribute_VH: VarHandle by lazy { NSTopMarginDocumentAttribute_LAYOUT.varHandle() }

var NSTopMarginDocumentAttribute: MemorySegment
    get() = NSTopMarginDocumentAttribute_VH.get(NSTopMarginDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSTopMarginDocumentAttribute_VH.set(NSTopMarginDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSBottomMarginDocumentAttribute typedef NSAttributedStringDocumentAttributeKey = typedef NSString = (Void)*
 */
private val NSBottomMarginDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBottomMarginDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBottomMarginDocumentAttribute").orElseThrow() }
private val NSBottomMarginDocumentAttribute_VH: VarHandle by lazy { NSBottomMarginDocumentAttribute_LAYOUT.varHandle() }

var NSBottomMarginDocumentAttribute: MemorySegment
    get() = NSBottomMarginDocumentAttribute_VH.get(NSBottomMarginDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSBottomMarginDocumentAttribute_VH.set(NSBottomMarginDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextEncodingNameDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSTextEncodingNameDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextEncodingNameDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextEncodingNameDocumentOption").orElseThrow() }
private val NSTextEncodingNameDocumentOption_VH: VarHandle by lazy { NSTextEncodingNameDocumentOption_LAYOUT.varHandle() }

var NSTextEncodingNameDocumentOption: MemorySegment
    get() = NSTextEncodingNameDocumentOption_VH.get(NSTextEncodingNameDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSTextEncodingNameDocumentOption_VH.set(NSTextEncodingNameDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSBaseURLDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSBaseURLDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBaseURLDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBaseURLDocumentOption").orElseThrow() }
private val NSBaseURLDocumentOption_VH: VarHandle by lazy { NSBaseURLDocumentOption_LAYOUT.varHandle() }

var NSBaseURLDocumentOption: MemorySegment
    get() = NSBaseURLDocumentOption_VH.get(NSBaseURLDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSBaseURLDocumentOption_VH.set(NSBaseURLDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSTimeoutDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSTimeoutDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTimeoutDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTimeoutDocumentOption").orElseThrow() }
private val NSTimeoutDocumentOption_VH: VarHandle by lazy { NSTimeoutDocumentOption_LAYOUT.varHandle() }

var NSTimeoutDocumentOption: MemorySegment
    get() = NSTimeoutDocumentOption_VH.get(NSTimeoutDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSTimeoutDocumentOption_VH.set(NSTimeoutDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSWebPreferencesDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSWebPreferencesDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWebPreferencesDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWebPreferencesDocumentOption").orElseThrow() }
private val NSWebPreferencesDocumentOption_VH: VarHandle by lazy { NSWebPreferencesDocumentOption_LAYOUT.varHandle() }

var NSWebPreferencesDocumentOption: MemorySegment
    get() = NSWebPreferencesDocumentOption_VH.get(NSWebPreferencesDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSWebPreferencesDocumentOption_VH.set(NSWebPreferencesDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSWebResourceLoadDelegateDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSWebResourceLoadDelegateDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWebResourceLoadDelegateDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWebResourceLoadDelegateDocumentOption").orElseThrow() }
private val NSWebResourceLoadDelegateDocumentOption_VH: VarHandle by lazy { NSWebResourceLoadDelegateDocumentOption_LAYOUT.varHandle() }

var NSWebResourceLoadDelegateDocumentOption: MemorySegment
    get() = NSWebResourceLoadDelegateDocumentOption_VH.get(NSWebResourceLoadDelegateDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSWebResourceLoadDelegateDocumentOption_VH.set(NSWebResourceLoadDelegateDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextSizeMultiplierDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSTextSizeMultiplierDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextSizeMultiplierDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextSizeMultiplierDocumentOption").orElseThrow() }
private val NSTextSizeMultiplierDocumentOption_VH: VarHandle by lazy { NSTextSizeMultiplierDocumentOption_LAYOUT.varHandle() }

var NSTextSizeMultiplierDocumentOption: MemorySegment
    get() = NSTextSizeMultiplierDocumentOption_VH.get(NSTextSizeMultiplierDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSTextSizeMultiplierDocumentOption_VH.set(NSTextSizeMultiplierDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeDocumentOption typedef NSAttributedStringDocumentReadingOptionKey = typedef NSString = (Void)*
 */
private val NSFileTypeDocumentOption_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileTypeDocumentOption_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileTypeDocumentOption").orElseThrow() }
private val NSFileTypeDocumentOption_VH: VarHandle by lazy { NSFileTypeDocumentOption_LAYOUT.varHandle() }

var NSFileTypeDocumentOption: MemorySegment
    get() = NSFileTypeDocumentOption_VH.get(NSFileTypeDocumentOption_SEGMENT) as MemorySegment
    set(value) = NSFileTypeDocumentOption_VH.set(NSFileTypeDocumentOption_SEGMENT, value)

/**
 * {@snippet lang=c : NSCharacterShapeAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSCharacterShapeAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCharacterShapeAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCharacterShapeAttributeName").orElseThrow() }
private val NSCharacterShapeAttributeName_VH: VarHandle by lazy { NSCharacterShapeAttributeName_LAYOUT.varHandle() }

var NSCharacterShapeAttributeName: MemorySegment
    get() = NSCharacterShapeAttributeName_VH.get(NSCharacterShapeAttributeName_SEGMENT) as MemorySegment
    set(value) = NSCharacterShapeAttributeName_VH.set(NSCharacterShapeAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSUsesScreenFontsDocumentAttribute typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSUsesScreenFontsDocumentAttribute_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUsesScreenFontsDocumentAttribute_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUsesScreenFontsDocumentAttribute").orElseThrow() }
private val NSUsesScreenFontsDocumentAttribute_VH: VarHandle by lazy { NSUsesScreenFontsDocumentAttribute_LAYOUT.varHandle() }

var NSUsesScreenFontsDocumentAttribute: MemorySegment
    get() = NSUsesScreenFontsDocumentAttribute_VH.get(NSUsesScreenFontsDocumentAttribute_SEGMENT) as MemorySegment
    set(value) = NSUsesScreenFontsDocumentAttribute_VH.set(NSUsesScreenFontsDocumentAttribute_SEGMENT, value)

/**
 * {@snippet lang=c : NSObliquenessAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSObliquenessAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSObliquenessAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSObliquenessAttributeName").orElseThrow() }
private val NSObliquenessAttributeName_VH: VarHandle by lazy { NSObliquenessAttributeName_LAYOUT.varHandle() }

var NSObliquenessAttributeName: MemorySegment
    get() = NSObliquenessAttributeName_VH.get(NSObliquenessAttributeName_SEGMENT) as MemorySegment
    set(value) = NSObliquenessAttributeName_VH.set(NSObliquenessAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSExpansionAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSExpansionAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSExpansionAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSExpansionAttributeName").orElseThrow() }
private val NSExpansionAttributeName_VH: VarHandle by lazy { NSExpansionAttributeName_LAYOUT.varHandle() }

var NSExpansionAttributeName: MemorySegment
    get() = NSExpansionAttributeName_VH.get(NSExpansionAttributeName_SEGMENT) as MemorySegment
    set(value) = NSExpansionAttributeName_VH.set(NSExpansionAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSVerticalGlyphFormAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSVerticalGlyphFormAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVerticalGlyphFormAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVerticalGlyphFormAttributeName").orElseThrow() }
private val NSVerticalGlyphFormAttributeName_VH: VarHandle by lazy { NSVerticalGlyphFormAttributeName_LAYOUT.varHandle() }

var NSVerticalGlyphFormAttributeName: MemorySegment
    get() = NSVerticalGlyphFormAttributeName_VH.get(NSVerticalGlyphFormAttributeName_SEGMENT) as MemorySegment
    set(value) = NSVerticalGlyphFormAttributeName_VH.set(NSVerticalGlyphFormAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSUnderlineStrikethroughMask typedef NSUInteger = UNSIGNED = Long
 */
private val NSUnderlineStrikethroughMask_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_LONG }
private val NSUnderlineStrikethroughMask_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUnderlineStrikethroughMask").orElseThrow() }
private val NSUnderlineStrikethroughMask_VH: VarHandle by lazy { NSUnderlineStrikethroughMask_LAYOUT.varHandle() }

var NSUnderlineStrikethroughMask: Long
    get() = NSUnderlineStrikethroughMask_VH.get(NSUnderlineStrikethroughMask_SEGMENT) as Long
    set(value) = NSUnderlineStrikethroughMask_VH.set(NSUnderlineStrikethroughMask_SEGMENT, value)

/**
 * {@snippet lang=c : NSUnderlineByWordMask typedef NSUInteger = UNSIGNED = Long
 */
private val NSUnderlineByWordMask_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_LONG }
private val NSUnderlineByWordMask_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUnderlineByWordMask").orElseThrow() }
private val NSUnderlineByWordMask_VH: VarHandle by lazy { NSUnderlineByWordMask_LAYOUT.varHandle() }

var NSUnderlineByWordMask: Long
    get() = NSUnderlineByWordMask_VH.get(NSUnderlineByWordMask_SEGMENT) as Long
    set(value) = NSUnderlineByWordMask_VH.set(NSUnderlineByWordMask_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextStorageWillProcessEditingNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextStorageWillProcessEditingNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextStorageWillProcessEditingNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextStorageWillProcessEditingNotification").orElseThrow() }
private val NSTextStorageWillProcessEditingNotification_VH: VarHandle by lazy { NSTextStorageWillProcessEditingNotification_LAYOUT.varHandle() }

var NSTextStorageWillProcessEditingNotification: MemorySegment
    get() = NSTextStorageWillProcessEditingNotification_VH.get(NSTextStorageWillProcessEditingNotification_SEGMENT) as MemorySegment
    set(value) = NSTextStorageWillProcessEditingNotification_VH.set(NSTextStorageWillProcessEditingNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextStorageDidProcessEditingNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextStorageDidProcessEditingNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextStorageDidProcessEditingNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextStorageDidProcessEditingNotification").orElseThrow() }
private val NSTextStorageDidProcessEditingNotification_VH: VarHandle by lazy { NSTextStorageDidProcessEditingNotification_LAYOUT.varHandle() }

var NSTextStorageDidProcessEditingNotification: MemorySegment
    get() = NSTextStorageDidProcessEditingNotification_VH.get(NSTextStorageDidProcessEditingNotification_SEGMENT) as MemorySegment
    set(value) = NSTextStorageDidProcessEditingNotification_VH.set(NSTextStorageDidProcessEditingNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarItemKey typedef const NSToolbarUserInfoKey = (Void)*
 */
private val NSToolbarItemKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarItemKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarItemKey").orElseThrow() }
private val NSToolbarItemKey_VH: VarHandle by lazy { NSToolbarItemKey_LAYOUT.varHandle() }

var NSToolbarItemKey: MemorySegment
    get() = NSToolbarItemKey_VH.get(NSToolbarItemKey_SEGMENT) as MemorySegment
    set(value) = NSToolbarItemKey_VH.set(NSToolbarItemKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarNewIndexKey typedef const NSToolbarUserInfoKey = (Void)*
 */
private val NSToolbarNewIndexKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarNewIndexKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarNewIndexKey").orElseThrow() }
private val NSToolbarNewIndexKey_VH: VarHandle by lazy { NSToolbarNewIndexKey_LAYOUT.varHandle() }

var NSToolbarNewIndexKey: MemorySegment
    get() = NSToolbarNewIndexKey_VH.get(NSToolbarNewIndexKey_SEGMENT) as MemorySegment
    set(value) = NSToolbarNewIndexKey_VH.set(NSToolbarNewIndexKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarWillAddItemNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSToolbarWillAddItemNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarWillAddItemNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarWillAddItemNotification").orElseThrow() }
private val NSToolbarWillAddItemNotification_VH: VarHandle by lazy { NSToolbarWillAddItemNotification_LAYOUT.varHandle() }

var NSToolbarWillAddItemNotification: MemorySegment
    get() = NSToolbarWillAddItemNotification_VH.get(NSToolbarWillAddItemNotification_SEGMENT) as MemorySegment
    set(value) = NSToolbarWillAddItemNotification_VH.set(NSToolbarWillAddItemNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarDidRemoveItemNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSToolbarDidRemoveItemNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarDidRemoveItemNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarDidRemoveItemNotification").orElseThrow() }
private val NSToolbarDidRemoveItemNotification_VH: VarHandle by lazy { NSToolbarDidRemoveItemNotification_LAYOUT.varHandle() }

var NSToolbarDidRemoveItemNotification: MemorySegment
    get() = NSToolbarDidRemoveItemNotification_VH.get(NSToolbarDidRemoveItemNotification_SEGMENT) as MemorySegment
    set(value) = NSToolbarDidRemoveItemNotification_VH.set(NSToolbarDidRemoveItemNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarSpaceItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarSpaceItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarSpaceItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarSpaceItemIdentifier").orElseThrow() }
private val NSToolbarSpaceItemIdentifier_VH: VarHandle by lazy { NSToolbarSpaceItemIdentifier_LAYOUT.varHandle() }

var NSToolbarSpaceItemIdentifier: MemorySegment
    get() = NSToolbarSpaceItemIdentifier_VH.get(NSToolbarSpaceItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarSpaceItemIdentifier_VH.set(NSToolbarSpaceItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarFlexibleSpaceItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarFlexibleSpaceItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarFlexibleSpaceItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarFlexibleSpaceItemIdentifier").orElseThrow() }
private val NSToolbarFlexibleSpaceItemIdentifier_VH: VarHandle by lazy { NSToolbarFlexibleSpaceItemIdentifier_LAYOUT.varHandle() }

var NSToolbarFlexibleSpaceItemIdentifier: MemorySegment
    get() = NSToolbarFlexibleSpaceItemIdentifier_VH.get(NSToolbarFlexibleSpaceItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarFlexibleSpaceItemIdentifier_VH.set(NSToolbarFlexibleSpaceItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarShowColorsItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarShowColorsItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarShowColorsItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarShowColorsItemIdentifier").orElseThrow() }
private val NSToolbarShowColorsItemIdentifier_VH: VarHandle by lazy { NSToolbarShowColorsItemIdentifier_LAYOUT.varHandle() }

var NSToolbarShowColorsItemIdentifier: MemorySegment
    get() = NSToolbarShowColorsItemIdentifier_VH.get(NSToolbarShowColorsItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarShowColorsItemIdentifier_VH.set(NSToolbarShowColorsItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarShowFontsItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarShowFontsItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarShowFontsItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarShowFontsItemIdentifier").orElseThrow() }
private val NSToolbarShowFontsItemIdentifier_VH: VarHandle by lazy { NSToolbarShowFontsItemIdentifier_LAYOUT.varHandle() }

var NSToolbarShowFontsItemIdentifier: MemorySegment
    get() = NSToolbarShowFontsItemIdentifier_VH.get(NSToolbarShowFontsItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarShowFontsItemIdentifier_VH.set(NSToolbarShowFontsItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarPrintItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarPrintItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarPrintItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarPrintItemIdentifier").orElseThrow() }
private val NSToolbarPrintItemIdentifier_VH: VarHandle by lazy { NSToolbarPrintItemIdentifier_LAYOUT.varHandle() }

var NSToolbarPrintItemIdentifier: MemorySegment
    get() = NSToolbarPrintItemIdentifier_VH.get(NSToolbarPrintItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarPrintItemIdentifier_VH.set(NSToolbarPrintItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarToggleSidebarItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarToggleSidebarItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarToggleSidebarItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarToggleSidebarItemIdentifier").orElseThrow() }
private val NSToolbarToggleSidebarItemIdentifier_VH: VarHandle by lazy { NSToolbarToggleSidebarItemIdentifier_LAYOUT.varHandle() }

var NSToolbarToggleSidebarItemIdentifier: MemorySegment
    get() = NSToolbarToggleSidebarItemIdentifier_VH.get(NSToolbarToggleSidebarItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarToggleSidebarItemIdentifier_VH.set(NSToolbarToggleSidebarItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarToggleInspectorItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarToggleInspectorItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarToggleInspectorItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarToggleInspectorItemIdentifier").orElseThrow() }
private val NSToolbarToggleInspectorItemIdentifier_VH: VarHandle by lazy { NSToolbarToggleInspectorItemIdentifier_LAYOUT.varHandle() }

var NSToolbarToggleInspectorItemIdentifier: MemorySegment
    get() = NSToolbarToggleInspectorItemIdentifier_VH.get(NSToolbarToggleInspectorItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarToggleInspectorItemIdentifier_VH.set(NSToolbarToggleInspectorItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarCloudSharingItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarCloudSharingItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarCloudSharingItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarCloudSharingItemIdentifier").orElseThrow() }
private val NSToolbarCloudSharingItemIdentifier_VH: VarHandle by lazy { NSToolbarCloudSharingItemIdentifier_LAYOUT.varHandle() }

var NSToolbarCloudSharingItemIdentifier: MemorySegment
    get() = NSToolbarCloudSharingItemIdentifier_VH.get(NSToolbarCloudSharingItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarCloudSharingItemIdentifier_VH.set(NSToolbarCloudSharingItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarWritingToolsItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarWritingToolsItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarWritingToolsItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarWritingToolsItemIdentifier").orElseThrow() }
private val NSToolbarWritingToolsItemIdentifier_VH: VarHandle by lazy { NSToolbarWritingToolsItemIdentifier_LAYOUT.varHandle() }

var NSToolbarWritingToolsItemIdentifier: MemorySegment
    get() = NSToolbarWritingToolsItemIdentifier_VH.get(NSToolbarWritingToolsItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarWritingToolsItemIdentifier_VH.set(NSToolbarWritingToolsItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarSidebarTrackingSeparatorItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarSidebarTrackingSeparatorItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarSidebarTrackingSeparatorItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarSidebarTrackingSeparatorItemIdentifier").orElseThrow() }
private val NSToolbarSidebarTrackingSeparatorItemIdentifier_VH: VarHandle by lazy { NSToolbarSidebarTrackingSeparatorItemIdentifier_LAYOUT.varHandle() }

var NSToolbarSidebarTrackingSeparatorItemIdentifier: MemorySegment
    get() = NSToolbarSidebarTrackingSeparatorItemIdentifier_VH.get(NSToolbarSidebarTrackingSeparatorItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarSidebarTrackingSeparatorItemIdentifier_VH.set(NSToolbarSidebarTrackingSeparatorItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarInspectorTrackingSeparatorItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarInspectorTrackingSeparatorItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarInspectorTrackingSeparatorItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarInspectorTrackingSeparatorItemIdentifier").orElseThrow() }
private val NSToolbarInspectorTrackingSeparatorItemIdentifier_VH: VarHandle by lazy { NSToolbarInspectorTrackingSeparatorItemIdentifier_LAYOUT.varHandle() }

var NSToolbarInspectorTrackingSeparatorItemIdentifier: MemorySegment
    get() = NSToolbarInspectorTrackingSeparatorItemIdentifier_VH.get(NSToolbarInspectorTrackingSeparatorItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarInspectorTrackingSeparatorItemIdentifier_VH.set(NSToolbarInspectorTrackingSeparatorItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarSeparatorItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarSeparatorItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarSeparatorItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarSeparatorItemIdentifier").orElseThrow() }
private val NSToolbarSeparatorItemIdentifier_VH: VarHandle by lazy { NSToolbarSeparatorItemIdentifier_LAYOUT.varHandle() }

var NSToolbarSeparatorItemIdentifier: MemorySegment
    get() = NSToolbarSeparatorItemIdentifier_VH.get(NSToolbarSeparatorItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarSeparatorItemIdentifier_VH.set(NSToolbarSeparatorItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSToolbarCustomizeToolbarItemIdentifier typedef NSToolbarItemIdentifier = typedef NSString = (Void)*
 */
private val NSToolbarCustomizeToolbarItemIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSToolbarCustomizeToolbarItemIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSToolbarCustomizeToolbarItemIdentifier").orElseThrow() }
private val NSToolbarCustomizeToolbarItemIdentifier_VH: VarHandle by lazy { NSToolbarCustomizeToolbarItemIdentifier_LAYOUT.varHandle() }

var NSToolbarCustomizeToolbarItemIdentifier: MemorySegment
    get() = NSToolbarCustomizeToolbarItemIdentifier_VH.get(NSToolbarCustomizeToolbarItemIdentifier_SEGMENT) as MemorySegment
    set(value) = NSToolbarCustomizeToolbarItemIdentifier_VH.set(NSToolbarCustomizeToolbarItemIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSComboBoxWillPopUpNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSComboBoxWillPopUpNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSComboBoxWillPopUpNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSComboBoxWillPopUpNotification").orElseThrow() }
private val NSComboBoxWillPopUpNotification_VH: VarHandle by lazy { NSComboBoxWillPopUpNotification_LAYOUT.varHandle() }

var NSComboBoxWillPopUpNotification: MemorySegment
    get() = NSComboBoxWillPopUpNotification_VH.get(NSComboBoxWillPopUpNotification_SEGMENT) as MemorySegment
    set(value) = NSComboBoxWillPopUpNotification_VH.set(NSComboBoxWillPopUpNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSComboBoxWillDismissNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSComboBoxWillDismissNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSComboBoxWillDismissNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSComboBoxWillDismissNotification").orElseThrow() }
private val NSComboBoxWillDismissNotification_VH: VarHandle by lazy { NSComboBoxWillDismissNotification_LAYOUT.varHandle() }

var NSComboBoxWillDismissNotification: MemorySegment
    get() = NSComboBoxWillDismissNotification_VH.get(NSComboBoxWillDismissNotification_SEGMENT) as MemorySegment
    set(value) = NSComboBoxWillDismissNotification_VH.set(NSComboBoxWillDismissNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSComboBoxSelectionDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSComboBoxSelectionDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSComboBoxSelectionDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSComboBoxSelectionDidChangeNotification").orElseThrow() }
private val NSComboBoxSelectionDidChangeNotification_VH: VarHandle by lazy { NSComboBoxSelectionDidChangeNotification_LAYOUT.varHandle() }

var NSComboBoxSelectionDidChangeNotification: MemorySegment
    get() = NSComboBoxSelectionDidChangeNotification_VH.get(NSComboBoxSelectionDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSComboBoxSelectionDidChangeNotification_VH.set(NSComboBoxSelectionDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSComboBoxSelectionIsChangingNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSComboBoxSelectionIsChangingNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSComboBoxSelectionIsChangingNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSComboBoxSelectionIsChangingNotification").orElseThrow() }
private val NSComboBoxSelectionIsChangingNotification_VH: VarHandle by lazy { NSComboBoxSelectionIsChangingNotification_LAYOUT.varHandle() }

var NSComboBoxSelectionIsChangingNotification: MemorySegment
    get() = NSComboBoxSelectionIsChangingNotification_VH.get(NSComboBoxSelectionIsChangingNotification_SEGMENT) as MemorySegment
    set(value) = NSComboBoxSelectionIsChangingNotification_VH.set(NSComboBoxSelectionIsChangingNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSAllRomanInputSourcesLocaleIdentifier typedef NSString = (Void)*
 */
private val NSAllRomanInputSourcesLocaleIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAllRomanInputSourcesLocaleIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAllRomanInputSourcesLocaleIdentifier").orElseThrow() }
private val NSAllRomanInputSourcesLocaleIdentifier_VH: VarHandle by lazy { NSAllRomanInputSourcesLocaleIdentifier_LAYOUT.varHandle() }

var NSAllRomanInputSourcesLocaleIdentifier: MemorySegment
    get() = NSAllRomanInputSourcesLocaleIdentifier_VH.get(NSAllRomanInputSourcesLocaleIdentifier_SEGMENT) as MemorySegment
    set(value) = NSAllRomanInputSourcesLocaleIdentifier_VH.set(NSAllRomanInputSourcesLocaleIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSTouchBarItemIdentifierCharacterPicker typedef const NSTouchBarItemIdentifier = (Void)*
 */
private val NSTouchBarItemIdentifierCharacterPicker_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTouchBarItemIdentifierCharacterPicker_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTouchBarItemIdentifierCharacterPicker").orElseThrow() }
private val NSTouchBarItemIdentifierCharacterPicker_VH: VarHandle by lazy { NSTouchBarItemIdentifierCharacterPicker_LAYOUT.varHandle() }

var NSTouchBarItemIdentifierCharacterPicker: MemorySegment
    get() = NSTouchBarItemIdentifierCharacterPicker_VH.get(NSTouchBarItemIdentifierCharacterPicker_SEGMENT) as MemorySegment
    set(value) = NSTouchBarItemIdentifierCharacterPicker_VH.set(NSTouchBarItemIdentifierCharacterPicker_SEGMENT, value)

/**
 * {@snippet lang=c : NSTouchBarItemIdentifierTextColorPicker typedef const NSTouchBarItemIdentifier = (Void)*
 */
private val NSTouchBarItemIdentifierTextColorPicker_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTouchBarItemIdentifierTextColorPicker_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTouchBarItemIdentifierTextColorPicker").orElseThrow() }
private val NSTouchBarItemIdentifierTextColorPicker_VH: VarHandle by lazy { NSTouchBarItemIdentifierTextColorPicker_LAYOUT.varHandle() }

var NSTouchBarItemIdentifierTextColorPicker: MemorySegment
    get() = NSTouchBarItemIdentifierTextColorPicker_VH.get(NSTouchBarItemIdentifierTextColorPicker_SEGMENT) as MemorySegment
    set(value) = NSTouchBarItemIdentifierTextColorPicker_VH.set(NSTouchBarItemIdentifierTextColorPicker_SEGMENT, value)

/**
 * {@snippet lang=c : NSTouchBarItemIdentifierTextStyle typedef const NSTouchBarItemIdentifier = (Void)*
 */
private val NSTouchBarItemIdentifierTextStyle_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTouchBarItemIdentifierTextStyle_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTouchBarItemIdentifierTextStyle").orElseThrow() }
private val NSTouchBarItemIdentifierTextStyle_VH: VarHandle by lazy { NSTouchBarItemIdentifierTextStyle_LAYOUT.varHandle() }

var NSTouchBarItemIdentifierTextStyle: MemorySegment
    get() = NSTouchBarItemIdentifierTextStyle_VH.get(NSTouchBarItemIdentifierTextStyle_SEGMENT) as MemorySegment
    set(value) = NSTouchBarItemIdentifierTextStyle_VH.set(NSTouchBarItemIdentifierTextStyle_SEGMENT, value)

/**
 * {@snippet lang=c : NSTouchBarItemIdentifierTextAlignment typedef const NSTouchBarItemIdentifier = (Void)*
 */
private val NSTouchBarItemIdentifierTextAlignment_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTouchBarItemIdentifierTextAlignment_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTouchBarItemIdentifierTextAlignment").orElseThrow() }
private val NSTouchBarItemIdentifierTextAlignment_VH: VarHandle by lazy { NSTouchBarItemIdentifierTextAlignment_LAYOUT.varHandle() }

var NSTouchBarItemIdentifierTextAlignment: MemorySegment
    get() = NSTouchBarItemIdentifierTextAlignment_VH.get(NSTouchBarItemIdentifierTextAlignment_SEGMENT) as MemorySegment
    set(value) = NSTouchBarItemIdentifierTextAlignment_VH.set(NSTouchBarItemIdentifierTextAlignment_SEGMENT, value)

/**
 * {@snippet lang=c : NSTouchBarItemIdentifierTextList typedef const NSTouchBarItemIdentifier = (Void)*
 */
private val NSTouchBarItemIdentifierTextList_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTouchBarItemIdentifierTextList_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTouchBarItemIdentifierTextList").orElseThrow() }
private val NSTouchBarItemIdentifierTextList_VH: VarHandle by lazy { NSTouchBarItemIdentifierTextList_LAYOUT.varHandle() }

var NSTouchBarItemIdentifierTextList: MemorySegment
    get() = NSTouchBarItemIdentifierTextList_VH.get(NSTouchBarItemIdentifierTextList_SEGMENT) as MemorySegment
    set(value) = NSTouchBarItemIdentifierTextList_VH.set(NSTouchBarItemIdentifierTextList_SEGMENT, value)

/**
 * {@snippet lang=c : NSTouchBarItemIdentifierTextFormat typedef const NSTouchBarItemIdentifier = (Void)*
 */
private val NSTouchBarItemIdentifierTextFormat_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTouchBarItemIdentifierTextFormat_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTouchBarItemIdentifierTextFormat").orElseThrow() }
private val NSTouchBarItemIdentifierTextFormat_VH: VarHandle by lazy { NSTouchBarItemIdentifierTextFormat_LAYOUT.varHandle() }

var NSTouchBarItemIdentifierTextFormat: MemorySegment
    get() = NSTouchBarItemIdentifierTextFormat_VH.get(NSTouchBarItemIdentifierTextFormat_SEGMENT) as MemorySegment
    set(value) = NSTouchBarItemIdentifierTextFormat_VH.set(NSTouchBarItemIdentifierTextFormat_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextViewWillChangeNotifyingTextViewNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextViewWillChangeNotifyingTextViewNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextViewWillChangeNotifyingTextViewNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextViewWillChangeNotifyingTextViewNotification").orElseThrow() }
private val NSTextViewWillChangeNotifyingTextViewNotification_VH: VarHandle by lazy { NSTextViewWillChangeNotifyingTextViewNotification_LAYOUT.varHandle() }

var NSTextViewWillChangeNotifyingTextViewNotification: MemorySegment
    get() = NSTextViewWillChangeNotifyingTextViewNotification_VH.get(NSTextViewWillChangeNotifyingTextViewNotification_SEGMENT) as MemorySegment
    set(value) = NSTextViewWillChangeNotifyingTextViewNotification_VH.set(NSTextViewWillChangeNotifyingTextViewNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextViewDidChangeSelectionNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextViewDidChangeSelectionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextViewDidChangeSelectionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextViewDidChangeSelectionNotification").orElseThrow() }
private val NSTextViewDidChangeSelectionNotification_VH: VarHandle by lazy { NSTextViewDidChangeSelectionNotification_LAYOUT.varHandle() }

var NSTextViewDidChangeSelectionNotification: MemorySegment
    get() = NSTextViewDidChangeSelectionNotification_VH.get(NSTextViewDidChangeSelectionNotification_SEGMENT) as MemorySegment
    set(value) = NSTextViewDidChangeSelectionNotification_VH.set(NSTextViewDidChangeSelectionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextViewDidChangeTypingAttributesNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextViewDidChangeTypingAttributesNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextViewDidChangeTypingAttributesNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextViewDidChangeTypingAttributesNotification").orElseThrow() }
private val NSTextViewDidChangeTypingAttributesNotification_VH: VarHandle by lazy { NSTextViewDidChangeTypingAttributesNotification_LAYOUT.varHandle() }

var NSTextViewDidChangeTypingAttributesNotification: MemorySegment
    get() = NSTextViewDidChangeTypingAttributesNotification_VH.get(NSTextViewDidChangeTypingAttributesNotification_SEGMENT) as MemorySegment
    set(value) = NSTextViewDidChangeTypingAttributesNotification_VH.set(NSTextViewDidChangeTypingAttributesNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextViewWillSwitchToNSLayoutManagerNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextViewWillSwitchToNSLayoutManagerNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextViewWillSwitchToNSLayoutManagerNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextViewWillSwitchToNSLayoutManagerNotification").orElseThrow() }
private val NSTextViewWillSwitchToNSLayoutManagerNotification_VH: VarHandle by lazy { NSTextViewWillSwitchToNSLayoutManagerNotification_LAYOUT.varHandle() }

var NSTextViewWillSwitchToNSLayoutManagerNotification: MemorySegment
    get() = NSTextViewWillSwitchToNSLayoutManagerNotification_VH.get(NSTextViewWillSwitchToNSLayoutManagerNotification_SEGMENT) as MemorySegment
    set(value) = NSTextViewWillSwitchToNSLayoutManagerNotification_VH.set(NSTextViewWillSwitchToNSLayoutManagerNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextViewDidSwitchToNSLayoutManagerNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextViewDidSwitchToNSLayoutManagerNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextViewDidSwitchToNSLayoutManagerNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextViewDidSwitchToNSLayoutManagerNotification").orElseThrow() }
private val NSTextViewDidSwitchToNSLayoutManagerNotification_VH: VarHandle by lazy { NSTextViewDidSwitchToNSLayoutManagerNotification_LAYOUT.varHandle() }

var NSTextViewDidSwitchToNSLayoutManagerNotification: MemorySegment
    get() = NSTextViewDidSwitchToNSLayoutManagerNotification_VH.get(NSTextViewDidSwitchToNSLayoutManagerNotification_SEGMENT) as MemorySegment
    set(value) = NSTextViewDidSwitchToNSLayoutManagerNotification_VH.set(NSTextViewDidSwitchToNSLayoutManagerNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSFindPanelSearchOptionsPboardType typedef NSPasteboardType = typedef NSString = (Void)*
 */
private val NSFindPanelSearchOptionsPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFindPanelSearchOptionsPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFindPanelSearchOptionsPboardType").orElseThrow() }
private val NSFindPanelSearchOptionsPboardType_VH: VarHandle by lazy { NSFindPanelSearchOptionsPboardType_LAYOUT.varHandle() }

var NSFindPanelSearchOptionsPboardType: MemorySegment
    get() = NSFindPanelSearchOptionsPboardType_VH.get(NSFindPanelSearchOptionsPboardType_SEGMENT) as MemorySegment
    set(value) = NSFindPanelSearchOptionsPboardType_VH.set(NSFindPanelSearchOptionsPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSFindPanelCaseInsensitiveSearch typedef NSPasteboardTypeFindPanelSearchOptionKey = typedef NSString = (Void)*
 */
private val NSFindPanelCaseInsensitiveSearch_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFindPanelCaseInsensitiveSearch_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFindPanelCaseInsensitiveSearch").orElseThrow() }
private val NSFindPanelCaseInsensitiveSearch_VH: VarHandle by lazy { NSFindPanelCaseInsensitiveSearch_LAYOUT.varHandle() }

var NSFindPanelCaseInsensitiveSearch: MemorySegment
    get() = NSFindPanelCaseInsensitiveSearch_VH.get(NSFindPanelCaseInsensitiveSearch_SEGMENT) as MemorySegment
    set(value) = NSFindPanelCaseInsensitiveSearch_VH.set(NSFindPanelCaseInsensitiveSearch_SEGMENT, value)

/**
 * {@snippet lang=c : NSFindPanelSubstringMatch typedef NSPasteboardTypeFindPanelSearchOptionKey = typedef NSString = (Void)*
 */
private val NSFindPanelSubstringMatch_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFindPanelSubstringMatch_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFindPanelSubstringMatch").orElseThrow() }
private val NSFindPanelSubstringMatch_VH: VarHandle by lazy { NSFindPanelSubstringMatch_LAYOUT.varHandle() }

var NSFindPanelSubstringMatch: MemorySegment
    get() = NSFindPanelSubstringMatch_VH.get(NSFindPanelSubstringMatch_SEGMENT) as MemorySegment
    set(value) = NSFindPanelSubstringMatch_VH.set(NSFindPanelSubstringMatch_SEGMENT, value)

/**
 * {@snippet lang=c : NSTableViewSelectionDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTableViewSelectionDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTableViewSelectionDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTableViewSelectionDidChangeNotification").orElseThrow() }
private val NSTableViewSelectionDidChangeNotification_VH: VarHandle by lazy { NSTableViewSelectionDidChangeNotification_LAYOUT.varHandle() }

var NSTableViewSelectionDidChangeNotification: MemorySegment
    get() = NSTableViewSelectionDidChangeNotification_VH.get(NSTableViewSelectionDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSTableViewSelectionDidChangeNotification_VH.set(NSTableViewSelectionDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTableViewColumnDidMoveNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTableViewColumnDidMoveNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTableViewColumnDidMoveNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTableViewColumnDidMoveNotification").orElseThrow() }
private val NSTableViewColumnDidMoveNotification_VH: VarHandle by lazy { NSTableViewColumnDidMoveNotification_LAYOUT.varHandle() }

var NSTableViewColumnDidMoveNotification: MemorySegment
    get() = NSTableViewColumnDidMoveNotification_VH.get(NSTableViewColumnDidMoveNotification_SEGMENT) as MemorySegment
    set(value) = NSTableViewColumnDidMoveNotification_VH.set(NSTableViewColumnDidMoveNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTableViewColumnDidResizeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTableViewColumnDidResizeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTableViewColumnDidResizeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTableViewColumnDidResizeNotification").orElseThrow() }
private val NSTableViewColumnDidResizeNotification_VH: VarHandle by lazy { NSTableViewColumnDidResizeNotification_LAYOUT.varHandle() }

var NSTableViewColumnDidResizeNotification: MemorySegment
    get() = NSTableViewColumnDidResizeNotification_VH.get(NSTableViewColumnDidResizeNotification_SEGMENT) as MemorySegment
    set(value) = NSTableViewColumnDidResizeNotification_VH.set(NSTableViewColumnDidResizeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTableViewSelectionIsChangingNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTableViewSelectionIsChangingNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTableViewSelectionIsChangingNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTableViewSelectionIsChangingNotification").orElseThrow() }
private val NSTableViewSelectionIsChangingNotification_VH: VarHandle by lazy { NSTableViewSelectionIsChangingNotification_LAYOUT.varHandle() }

var NSTableViewSelectionIsChangingNotification: MemorySegment
    get() = NSTableViewSelectionIsChangingNotification_VH.get(NSTableViewSelectionIsChangingNotification_SEGMENT) as MemorySegment
    set(value) = NSTableViewSelectionIsChangingNotification_VH.set(NSTableViewSelectionIsChangingNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTableViewRowViewKey typedef const NSUserInterfaceItemIdentifier = (Void)*
 */
private val NSTableViewRowViewKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTableViewRowViewKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTableViewRowViewKey").orElseThrow() }
private val NSTableViewRowViewKey_VH: VarHandle by lazy { NSTableViewRowViewKey_LAYOUT.varHandle() }

var NSTableViewRowViewKey: MemorySegment
    get() = NSTableViewRowViewKey_VH.get(NSTableViewRowViewKey_SEGMENT) as MemorySegment
    set(value) = NSTableViewRowViewKey_VH.set(NSTableViewRowViewKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewDisclosureButtonKey typedef const NSUserInterfaceItemIdentifier = (Void)*
 */
private val NSOutlineViewDisclosureButtonKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewDisclosureButtonKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewDisclosureButtonKey").orElseThrow() }
private val NSOutlineViewDisclosureButtonKey_VH: VarHandle by lazy { NSOutlineViewDisclosureButtonKey_LAYOUT.varHandle() }

var NSOutlineViewDisclosureButtonKey: MemorySegment
    get() = NSOutlineViewDisclosureButtonKey_VH.get(NSOutlineViewDisclosureButtonKey_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewDisclosureButtonKey_VH.set(NSOutlineViewDisclosureButtonKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewShowHideButtonKey typedef const NSUserInterfaceItemIdentifier = (Void)*
 */
private val NSOutlineViewShowHideButtonKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewShowHideButtonKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewShowHideButtonKey").orElseThrow() }
private val NSOutlineViewShowHideButtonKey_VH: VarHandle by lazy { NSOutlineViewShowHideButtonKey_LAYOUT.varHandle() }

var NSOutlineViewShowHideButtonKey: MemorySegment
    get() = NSOutlineViewShowHideButtonKey_VH.get(NSOutlineViewShowHideButtonKey_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewShowHideButtonKey_VH.set(NSOutlineViewShowHideButtonKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewSelectionDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSOutlineViewSelectionDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewSelectionDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewSelectionDidChangeNotification").orElseThrow() }
private val NSOutlineViewSelectionDidChangeNotification_VH: VarHandle by lazy { NSOutlineViewSelectionDidChangeNotification_LAYOUT.varHandle() }

var NSOutlineViewSelectionDidChangeNotification: MemorySegment
    get() = NSOutlineViewSelectionDidChangeNotification_VH.get(NSOutlineViewSelectionDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewSelectionDidChangeNotification_VH.set(NSOutlineViewSelectionDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewColumnDidMoveNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSOutlineViewColumnDidMoveNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewColumnDidMoveNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewColumnDidMoveNotification").orElseThrow() }
private val NSOutlineViewColumnDidMoveNotification_VH: VarHandle by lazy { NSOutlineViewColumnDidMoveNotification_LAYOUT.varHandle() }

var NSOutlineViewColumnDidMoveNotification: MemorySegment
    get() = NSOutlineViewColumnDidMoveNotification_VH.get(NSOutlineViewColumnDidMoveNotification_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewColumnDidMoveNotification_VH.set(NSOutlineViewColumnDidMoveNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewColumnDidResizeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSOutlineViewColumnDidResizeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewColumnDidResizeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewColumnDidResizeNotification").orElseThrow() }
private val NSOutlineViewColumnDidResizeNotification_VH: VarHandle by lazy { NSOutlineViewColumnDidResizeNotification_LAYOUT.varHandle() }

var NSOutlineViewColumnDidResizeNotification: MemorySegment
    get() = NSOutlineViewColumnDidResizeNotification_VH.get(NSOutlineViewColumnDidResizeNotification_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewColumnDidResizeNotification_VH.set(NSOutlineViewColumnDidResizeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewSelectionIsChangingNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSOutlineViewSelectionIsChangingNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewSelectionIsChangingNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewSelectionIsChangingNotification").orElseThrow() }
private val NSOutlineViewSelectionIsChangingNotification_VH: VarHandle by lazy { NSOutlineViewSelectionIsChangingNotification_LAYOUT.varHandle() }

var NSOutlineViewSelectionIsChangingNotification: MemorySegment
    get() = NSOutlineViewSelectionIsChangingNotification_VH.get(NSOutlineViewSelectionIsChangingNotification_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewSelectionIsChangingNotification_VH.set(NSOutlineViewSelectionIsChangingNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewItemWillExpandNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSOutlineViewItemWillExpandNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewItemWillExpandNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewItemWillExpandNotification").orElseThrow() }
private val NSOutlineViewItemWillExpandNotification_VH: VarHandle by lazy { NSOutlineViewItemWillExpandNotification_LAYOUT.varHandle() }

var NSOutlineViewItemWillExpandNotification: MemorySegment
    get() = NSOutlineViewItemWillExpandNotification_VH.get(NSOutlineViewItemWillExpandNotification_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewItemWillExpandNotification_VH.set(NSOutlineViewItemWillExpandNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewItemDidExpandNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSOutlineViewItemDidExpandNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewItemDidExpandNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewItemDidExpandNotification").orElseThrow() }
private val NSOutlineViewItemDidExpandNotification_VH: VarHandle by lazy { NSOutlineViewItemDidExpandNotification_LAYOUT.varHandle() }

var NSOutlineViewItemDidExpandNotification: MemorySegment
    get() = NSOutlineViewItemDidExpandNotification_VH.get(NSOutlineViewItemDidExpandNotification_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewItemDidExpandNotification_VH.set(NSOutlineViewItemDidExpandNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewItemWillCollapseNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSOutlineViewItemWillCollapseNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewItemWillCollapseNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewItemWillCollapseNotification").orElseThrow() }
private val NSOutlineViewItemWillCollapseNotification_VH: VarHandle by lazy { NSOutlineViewItemWillCollapseNotification_LAYOUT.varHandle() }

var NSOutlineViewItemWillCollapseNotification: MemorySegment
    get() = NSOutlineViewItemWillCollapseNotification_VH.get(NSOutlineViewItemWillCollapseNotification_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewItemWillCollapseNotification_VH.set(NSOutlineViewItemWillCollapseNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSOutlineViewItemDidCollapseNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSOutlineViewItemDidCollapseNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSOutlineViewItemDidCollapseNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOutlineViewItemDidCollapseNotification").orElseThrow() }
private val NSOutlineViewItemDidCollapseNotification_VH: VarHandle by lazy { NSOutlineViewItemDidCollapseNotification_LAYOUT.varHandle() }

var NSOutlineViewItemDidCollapseNotification: MemorySegment
    get() = NSOutlineViewItemDidCollapseNotification_VH.get(NSOutlineViewItemDidCollapseNotification_SEGMENT) as MemorySegment
    set(value) = NSOutlineViewItemDidCollapseNotification_VH.set(NSOutlineViewItemDidCollapseNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSRulerViewUnitInches typedef const NSRulerViewUnitName = (Void)*
 */
private val NSRulerViewUnitInches_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRulerViewUnitInches_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRulerViewUnitInches").orElseThrow() }
private val NSRulerViewUnitInches_VH: VarHandle by lazy { NSRulerViewUnitInches_LAYOUT.varHandle() }

var NSRulerViewUnitInches: MemorySegment
    get() = NSRulerViewUnitInches_VH.get(NSRulerViewUnitInches_SEGMENT) as MemorySegment
    set(value) = NSRulerViewUnitInches_VH.set(NSRulerViewUnitInches_SEGMENT, value)

/**
 * {@snippet lang=c : NSRulerViewUnitCentimeters typedef const NSRulerViewUnitName = (Void)*
 */
private val NSRulerViewUnitCentimeters_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRulerViewUnitCentimeters_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRulerViewUnitCentimeters").orElseThrow() }
private val NSRulerViewUnitCentimeters_VH: VarHandle by lazy { NSRulerViewUnitCentimeters_LAYOUT.varHandle() }

var NSRulerViewUnitCentimeters: MemorySegment
    get() = NSRulerViewUnitCentimeters_VH.get(NSRulerViewUnitCentimeters_SEGMENT) as MemorySegment
    set(value) = NSRulerViewUnitCentimeters_VH.set(NSRulerViewUnitCentimeters_SEGMENT, value)

/**
 * {@snippet lang=c : NSRulerViewUnitPoints typedef const NSRulerViewUnitName = (Void)*
 */
private val NSRulerViewUnitPoints_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRulerViewUnitPoints_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRulerViewUnitPoints").orElseThrow() }
private val NSRulerViewUnitPoints_VH: VarHandle by lazy { NSRulerViewUnitPoints_LAYOUT.varHandle() }

var NSRulerViewUnitPoints: MemorySegment
    get() = NSRulerViewUnitPoints_VH.get(NSRulerViewUnitPoints_SEGMENT) as MemorySegment
    set(value) = NSRulerViewUnitPoints_VH.set(NSRulerViewUnitPoints_SEGMENT, value)

/**
 * {@snippet lang=c : NSRulerViewUnitPicas typedef const NSRulerViewUnitName = (Void)*
 */
private val NSRulerViewUnitPicas_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRulerViewUnitPicas_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRulerViewUnitPicas").orElseThrow() }
private val NSRulerViewUnitPicas_VH: VarHandle by lazy { NSRulerViewUnitPicas_LAYOUT.varHandle() }

var NSRulerViewUnitPicas: MemorySegment
    get() = NSRulerViewUnitPicas_VH.get(NSRulerViewUnitPicas_SEGMENT) as MemorySegment
    set(value) = NSRulerViewUnitPicas_VH.set(NSRulerViewUnitPicas_SEGMENT, value)

/**
 * {@snippet lang=c : NSInterfaceStyleForKey typedef NSInterfaceStyle = UNSIGNED = Long((Void)*,(Void)*)
 */
private val NSInterfaceStyleForKey_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSInterfaceStyleForKey_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSInterfaceStyleForKey").orElseThrow()
private val NSInterfaceStyleForKey_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSInterfaceStyleForKey_ADDR, NSInterfaceStyleForKey_DESC)

fun NSInterfaceStyleForKey(arg0: MemorySegment, arg1: MemorySegment): Long {
    try {
        return NSInterfaceStyleForKey_HANDLE.invokeExact(arg0, arg1) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSInterfaceStyleDefault (Void)*
 */
private val NSInterfaceStyleDefault_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInterfaceStyleDefault_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInterfaceStyleDefault").orElseThrow() }
private val NSInterfaceStyleDefault_VH: VarHandle by lazy { NSInterfaceStyleDefault_LAYOUT.varHandle() }

var NSInterfaceStyleDefault: MemorySegment
    get() = NSInterfaceStyleDefault_VH.get(NSInterfaceStyleDefault_SEGMENT) as MemorySegment
    set(value) = NSInterfaceStyleDefault_VH.set(NSInterfaceStyleDefault_SEGMENT, value)

/**
 * {@snippet lang=c : NSSoundPboardType typedef const NSPasteboardType = (Void)*
 */
private val NSSoundPboardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSoundPboardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSoundPboardType").orElseThrow() }
private val NSSoundPboardType_VH: VarHandle by lazy { NSSoundPboardType_LAYOUT.varHandle() }

var NSSoundPboardType: MemorySegment
    get() = NSSoundPboardType_VH.get(NSSoundPboardType_SEGMENT) as MemorySegment
    set(value) = NSSoundPboardType_VH.set(NSSoundPboardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSDrawerWillOpenNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSDrawerWillOpenNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDrawerWillOpenNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDrawerWillOpenNotification").orElseThrow() }
private val NSDrawerWillOpenNotification_VH: VarHandle by lazy { NSDrawerWillOpenNotification_LAYOUT.varHandle() }

var NSDrawerWillOpenNotification: MemorySegment
    get() = NSDrawerWillOpenNotification_VH.get(NSDrawerWillOpenNotification_SEGMENT) as MemorySegment
    set(value) = NSDrawerWillOpenNotification_VH.set(NSDrawerWillOpenNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSDrawerDidOpenNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSDrawerDidOpenNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDrawerDidOpenNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDrawerDidOpenNotification").orElseThrow() }
private val NSDrawerDidOpenNotification_VH: VarHandle by lazy { NSDrawerDidOpenNotification_LAYOUT.varHandle() }

var NSDrawerDidOpenNotification: MemorySegment
    get() = NSDrawerDidOpenNotification_VH.get(NSDrawerDidOpenNotification_SEGMENT) as MemorySegment
    set(value) = NSDrawerDidOpenNotification_VH.set(NSDrawerDidOpenNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSDrawerWillCloseNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSDrawerWillCloseNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDrawerWillCloseNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDrawerWillCloseNotification").orElseThrow() }
private val NSDrawerWillCloseNotification_VH: VarHandle by lazy { NSDrawerWillCloseNotification_LAYOUT.varHandle() }

var NSDrawerWillCloseNotification: MemorySegment
    get() = NSDrawerWillCloseNotification_VH.get(NSDrawerWillCloseNotification_SEGMENT) as MemorySegment
    set(value) = NSDrawerWillCloseNotification_VH.set(NSDrawerWillCloseNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSDrawerDidCloseNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSDrawerDidCloseNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDrawerDidCloseNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDrawerDidCloseNotification").orElseThrow() }
private val NSDrawerDidCloseNotification_VH: VarHandle by lazy { NSDrawerDidCloseNotification_LAYOUT.varHandle() }

var NSDrawerDidCloseNotification: MemorySegment
    get() = NSDrawerDidCloseNotification_VH.get(NSDrawerDidCloseNotification_SEGMENT) as MemorySegment
    set(value) = NSDrawerDidCloseNotification_VH.set(NSDrawerDidCloseNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSOpenGLGetVersion Void((typedef GLint = Int)*,(typedef GLint = Int)*)
 */
private val NSOpenGLGetVersion_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSOpenGLGetVersion_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSOpenGLGetVersion").orElseThrow()
private val NSOpenGLGetVersion_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSOpenGLGetVersion_ADDR, NSOpenGLGetVersion_DESC)

fun NSOpenGLGetVersion(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSOpenGLGetVersion_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CACurrentMediaTime typedef CFTimeInterval = Double()
 */
private val CACurrentMediaTime_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE)
private val CACurrentMediaTime_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CACurrentMediaTime").orElseThrow()
private val CACurrentMediaTime_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CACurrentMediaTime_ADDR, CACurrentMediaTime_DESC)

fun CACurrentMediaTime(): Double {
    try {
        return CACurrentMediaTime_HANDLE.invokeExact() as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : kCAFillModeForwards typedef const CAMediaTimingFillMode = (Void)*
 */
private val kCAFillModeForwards_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAFillModeForwards_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAFillModeForwards").orElseThrow() }
private val kCAFillModeForwards_VH: VarHandle by lazy { kCAFillModeForwards_LAYOUT.varHandle() }

var kCAFillModeForwards: MemorySegment
    get() = kCAFillModeForwards_VH.get(kCAFillModeForwards_SEGMENT) as MemorySegment
    set(value) = kCAFillModeForwards_VH.set(kCAFillModeForwards_SEGMENT, value)

/**
 * {@snippet lang=c : kCAFillModeBackwards typedef const CAMediaTimingFillMode = (Void)*
 */
private val kCAFillModeBackwards_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAFillModeBackwards_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAFillModeBackwards").orElseThrow() }
private val kCAFillModeBackwards_VH: VarHandle by lazy { kCAFillModeBackwards_LAYOUT.varHandle() }

var kCAFillModeBackwards: MemorySegment
    get() = kCAFillModeBackwards_VH.get(kCAFillModeBackwards_SEGMENT) as MemorySegment
    set(value) = kCAFillModeBackwards_VH.set(kCAFillModeBackwards_SEGMENT, value)

/**
 * {@snippet lang=c : kCAFillModeBoth typedef const CAMediaTimingFillMode = (Void)*
 */
private val kCAFillModeBoth_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAFillModeBoth_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAFillModeBoth").orElseThrow() }
private val kCAFillModeBoth_VH: VarHandle by lazy { kCAFillModeBoth_LAYOUT.varHandle() }

var kCAFillModeBoth: MemorySegment
    get() = kCAFillModeBoth_VH.get(kCAFillModeBoth_SEGMENT) as MemorySegment
    set(value) = kCAFillModeBoth_VH.set(kCAFillModeBoth_SEGMENT, value)

/**
 * {@snippet lang=c : kCAFillModeRemoved typedef const CAMediaTimingFillMode = (Void)*
 */
private val kCAFillModeRemoved_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAFillModeRemoved_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAFillModeRemoved").orElseThrow() }
private val kCAFillModeRemoved_VH: VarHandle by lazy { kCAFillModeRemoved_LAYOUT.varHandle() }

var kCAFillModeRemoved: MemorySegment
    get() = kCAFillModeRemoved_VH.get(kCAFillModeRemoved_SEGMENT) as MemorySegment
    set(value) = kCAFillModeRemoved_VH.set(kCAFillModeRemoved_SEGMENT, value)

/**
 * {@snippet lang=c : CATransform3DIdentity typedef const CATransform3D = Declared(CATransform3D)
 */
private val CATransform3DIdentity_LAYOUT: MemoryLayout by lazy { CATransform3D.layout }
private val CATransform3DIdentity_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CATransform3DIdentity").orElseThrow() }
private val CATransform3DIdentity_VH: VarHandle by lazy { CATransform3DIdentity_LAYOUT.varHandle() }

var CATransform3DIdentity: MemorySegment
    get() = CATransform3DIdentity_VH.get(CATransform3DIdentity_SEGMENT) as MemorySegment
    set(value) = CATransform3DIdentity_VH.set(CATransform3DIdentity_SEGMENT, value)

/**
 * {@snippet lang=c : CATransform3DIsIdentity Bool(typedef CATransform3D = Declared(CATransform3D))
 */
private val CATransform3DIsIdentity_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CATransform3D.layout)
private val CATransform3DIsIdentity_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DIsIdentity").orElseThrow()
private val CATransform3DIsIdentity_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DIsIdentity_ADDR, CATransform3DIsIdentity_DESC)

fun CATransform3DIsIdentity(arg0: MemorySegment): Boolean {
    try {
        return CATransform3DIsIdentity_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DEqualToTransform Bool(typedef CATransform3D = Declared(CATransform3D),typedef CATransform3D = Declared(CATransform3D))
 */
private val CATransform3DEqualToTransform_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CATransform3D.layout, CATransform3D.layout)
private val CATransform3DEqualToTransform_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DEqualToTransform").orElseThrow()
private val CATransform3DEqualToTransform_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DEqualToTransform_ADDR, CATransform3DEqualToTransform_DESC)

fun CATransform3DEqualToTransform(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CATransform3DEqualToTransform_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DMakeTranslation typedef CATransform3D = Declared(CATransform3D)(typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CATransform3DMakeTranslation_DESC: FunctionDescriptor = FunctionDescriptor.of(CATransform3D.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CATransform3DMakeTranslation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DMakeTranslation").orElseThrow()
private val CATransform3DMakeTranslation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DMakeTranslation_ADDR, CATransform3DMakeTranslation_DESC)

fun CATransform3DMakeTranslation(allocator: SegmentAllocator, arg0: Double, arg1: Double, arg2: Double): MemorySegment {
    try {
        return CATransform3DMakeTranslation_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DMakeScale typedef CATransform3D = Declared(CATransform3D)(typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CATransform3DMakeScale_DESC: FunctionDescriptor = FunctionDescriptor.of(CATransform3D.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CATransform3DMakeScale_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DMakeScale").orElseThrow()
private val CATransform3DMakeScale_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DMakeScale_ADDR, CATransform3DMakeScale_DESC)

fun CATransform3DMakeScale(allocator: SegmentAllocator, arg0: Double, arg1: Double, arg2: Double): MemorySegment {
    try {
        return CATransform3DMakeScale_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DMakeRotation typedef CATransform3D = Declared(CATransform3D)(typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CATransform3DMakeRotation_DESC: FunctionDescriptor = FunctionDescriptor.of(CATransform3D.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CATransform3DMakeRotation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DMakeRotation").orElseThrow()
private val CATransform3DMakeRotation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DMakeRotation_ADDR, CATransform3DMakeRotation_DESC)

fun CATransform3DMakeRotation(allocator: SegmentAllocator, arg0: Double, arg1: Double, arg2: Double, arg3: Double): MemorySegment {
    try {
        return CATransform3DMakeRotation_HANDLE.invokeExact(allocator, arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DTranslate typedef CATransform3D = Declared(CATransform3D)(typedef CATransform3D = Declared(CATransform3D),typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CATransform3DTranslate_DESC: FunctionDescriptor = FunctionDescriptor.of(CATransform3D.layout, CATransform3D.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CATransform3DTranslate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DTranslate").orElseThrow()
private val CATransform3DTranslate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DTranslate_ADDR, CATransform3DTranslate_DESC)

fun CATransform3DTranslate(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double): MemorySegment {
    try {
        return CATransform3DTranslate_HANDLE.invokeExact(allocator, arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DScale typedef CATransform3D = Declared(CATransform3D)(typedef CATransform3D = Declared(CATransform3D),typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CATransform3DScale_DESC: FunctionDescriptor = FunctionDescriptor.of(CATransform3D.layout, CATransform3D.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CATransform3DScale_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DScale").orElseThrow()
private val CATransform3DScale_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DScale_ADDR, CATransform3DScale_DESC)

fun CATransform3DScale(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double): MemorySegment {
    try {
        return CATransform3DScale_HANDLE.invokeExact(allocator, arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DRotate typedef CATransform3D = Declared(CATransform3D)(typedef CATransform3D = Declared(CATransform3D),typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CATransform3DRotate_DESC: FunctionDescriptor = FunctionDescriptor.of(CATransform3D.layout, CATransform3D.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CATransform3DRotate_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DRotate").orElseThrow()
private val CATransform3DRotate_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DRotate_ADDR, CATransform3DRotate_DESC)

fun CATransform3DRotate(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Double, arg2: Double, arg3: Double, arg4: Double): MemorySegment {
    try {
        return CATransform3DRotate_HANDLE.invokeExact(allocator, arg0, arg1, arg2, arg3, arg4) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DConcat typedef CATransform3D = Declared(CATransform3D)(typedef CATransform3D = Declared(CATransform3D),typedef CATransform3D = Declared(CATransform3D))
 */
private val CATransform3DConcat_DESC: FunctionDescriptor = FunctionDescriptor.of(CATransform3D.layout, CATransform3D.layout, CATransform3D.layout)
private val CATransform3DConcat_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DConcat").orElseThrow()
private val CATransform3DConcat_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DConcat_ADDR, CATransform3DConcat_DESC)

fun CATransform3DConcat(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CATransform3DConcat_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DInvert typedef CATransform3D = Declared(CATransform3D)(typedef CATransform3D = Declared(CATransform3D))
 */
private val CATransform3DInvert_DESC: FunctionDescriptor = FunctionDescriptor.of(CATransform3D.layout, CATransform3D.layout)
private val CATransform3DInvert_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DInvert").orElseThrow()
private val CATransform3DInvert_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DInvert_ADDR, CATransform3DInvert_DESC)

fun CATransform3DInvert(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CATransform3DInvert_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CATransform3DIsAffine Bool(typedef CATransform3D = Declared(CATransform3D))
 */
private val CATransform3DIsAffine_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CATransform3D.layout)
private val CATransform3DIsAffine_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CATransform3DIsAffine").orElseThrow()
private val CATransform3DIsAffine_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CATransform3DIsAffine_ADDR, CATransform3DIsAffine_DESC)

fun CATransform3DIsAffine(arg0: MemorySegment): Boolean {
    try {
        return CATransform3DIsAffine_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CAToneMapModeAutomatic typedef const CAToneMapMode = (Void)*
 */
private val CAToneMapModeAutomatic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val CAToneMapModeAutomatic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CAToneMapModeAutomatic").orElseThrow() }
private val CAToneMapModeAutomatic_VH: VarHandle by lazy { CAToneMapModeAutomatic_LAYOUT.varHandle() }

var CAToneMapModeAutomatic: MemorySegment
    get() = CAToneMapModeAutomatic_VH.get(CAToneMapModeAutomatic_SEGMENT) as MemorySegment
    set(value) = CAToneMapModeAutomatic_VH.set(CAToneMapModeAutomatic_SEGMENT, value)

/**
 * {@snippet lang=c : CAToneMapModeNever typedef const CAToneMapMode = (Void)*
 */
private val CAToneMapModeNever_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val CAToneMapModeNever_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CAToneMapModeNever").orElseThrow() }
private val CAToneMapModeNever_VH: VarHandle by lazy { CAToneMapModeNever_LAYOUT.varHandle() }

var CAToneMapModeNever: MemorySegment
    get() = CAToneMapModeNever_VH.get(CAToneMapModeNever_SEGMENT) as MemorySegment
    set(value) = CAToneMapModeNever_VH.set(CAToneMapModeNever_SEGMENT, value)

/**
 * {@snippet lang=c : CAToneMapModeIfSupported typedef const CAToneMapMode = (Void)*
 */
private val CAToneMapModeIfSupported_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val CAToneMapModeIfSupported_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CAToneMapModeIfSupported").orElseThrow() }
private val CAToneMapModeIfSupported_VH: VarHandle by lazy { CAToneMapModeIfSupported_LAYOUT.varHandle() }

var CAToneMapModeIfSupported: MemorySegment
    get() = CAToneMapModeIfSupported_VH.get(CAToneMapModeIfSupported_SEGMENT) as MemorySegment
    set(value) = CAToneMapModeIfSupported_VH.set(CAToneMapModeIfSupported_SEGMENT, value)

/**
 * {@snippet lang=c : CADynamicRangeAutomatic typedef const CADynamicRange = (Void)*
 */
private val CADynamicRangeAutomatic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val CADynamicRangeAutomatic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CADynamicRangeAutomatic").orElseThrow() }
private val CADynamicRangeAutomatic_VH: VarHandle by lazy { CADynamicRangeAutomatic_LAYOUT.varHandle() }

var CADynamicRangeAutomatic: MemorySegment
    get() = CADynamicRangeAutomatic_VH.get(CADynamicRangeAutomatic_SEGMENT) as MemorySegment
    set(value) = CADynamicRangeAutomatic_VH.set(CADynamicRangeAutomatic_SEGMENT, value)

/**
 * {@snippet lang=c : CADynamicRangeStandard typedef const CADynamicRange = (Void)*
 */
private val CADynamicRangeStandard_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val CADynamicRangeStandard_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CADynamicRangeStandard").orElseThrow() }
private val CADynamicRangeStandard_VH: VarHandle by lazy { CADynamicRangeStandard_LAYOUT.varHandle() }

var CADynamicRangeStandard: MemorySegment
    get() = CADynamicRangeStandard_VH.get(CADynamicRangeStandard_SEGMENT) as MemorySegment
    set(value) = CADynamicRangeStandard_VH.set(CADynamicRangeStandard_SEGMENT, value)

/**
 * {@snippet lang=c : CADynamicRangeConstrainedHigh typedef const CADynamicRange = (Void)*
 */
private val CADynamicRangeConstrainedHigh_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val CADynamicRangeConstrainedHigh_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CADynamicRangeConstrainedHigh").orElseThrow() }
private val CADynamicRangeConstrainedHigh_VH: VarHandle by lazy { CADynamicRangeConstrainedHigh_LAYOUT.varHandle() }

var CADynamicRangeConstrainedHigh: MemorySegment
    get() = CADynamicRangeConstrainedHigh_VH.get(CADynamicRangeConstrainedHigh_SEGMENT) as MemorySegment
    set(value) = CADynamicRangeConstrainedHigh_VH.set(CADynamicRangeConstrainedHigh_SEGMENT, value)

/**
 * {@snippet lang=c : CADynamicRangeHigh typedef const CADynamicRange = (Void)*
 */
private val CADynamicRangeHigh_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val CADynamicRangeHigh_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CADynamicRangeHigh").orElseThrow() }
private val CADynamicRangeHigh_VH: VarHandle by lazy { CADynamicRangeHigh_LAYOUT.varHandle() }

var CADynamicRangeHigh: MemorySegment
    get() = CADynamicRangeHigh_VH.get(CADynamicRangeHigh_SEGMENT) as MemorySegment
    set(value) = CADynamicRangeHigh_VH.set(CADynamicRangeHigh_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityCenter typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityCenter_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityCenter_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityCenter").orElseThrow() }
private val kCAGravityCenter_VH: VarHandle by lazy { kCAGravityCenter_LAYOUT.varHandle() }

var kCAGravityCenter: MemorySegment
    get() = kCAGravityCenter_VH.get(kCAGravityCenter_SEGMENT) as MemorySegment
    set(value) = kCAGravityCenter_VH.set(kCAGravityCenter_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityTop typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityTop_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityTop_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityTop").orElseThrow() }
private val kCAGravityTop_VH: VarHandle by lazy { kCAGravityTop_LAYOUT.varHandle() }

var kCAGravityTop: MemorySegment
    get() = kCAGravityTop_VH.get(kCAGravityTop_SEGMENT) as MemorySegment
    set(value) = kCAGravityTop_VH.set(kCAGravityTop_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityBottom typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityBottom_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityBottom_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityBottom").orElseThrow() }
private val kCAGravityBottom_VH: VarHandle by lazy { kCAGravityBottom_LAYOUT.varHandle() }

var kCAGravityBottom: MemorySegment
    get() = kCAGravityBottom_VH.get(kCAGravityBottom_SEGMENT) as MemorySegment
    set(value) = kCAGravityBottom_VH.set(kCAGravityBottom_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityLeft typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityLeft_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityLeft_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityLeft").orElseThrow() }
private val kCAGravityLeft_VH: VarHandle by lazy { kCAGravityLeft_LAYOUT.varHandle() }

var kCAGravityLeft: MemorySegment
    get() = kCAGravityLeft_VH.get(kCAGravityLeft_SEGMENT) as MemorySegment
    set(value) = kCAGravityLeft_VH.set(kCAGravityLeft_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityRight typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityRight_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityRight_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityRight").orElseThrow() }
private val kCAGravityRight_VH: VarHandle by lazy { kCAGravityRight_LAYOUT.varHandle() }

var kCAGravityRight: MemorySegment
    get() = kCAGravityRight_VH.get(kCAGravityRight_SEGMENT) as MemorySegment
    set(value) = kCAGravityRight_VH.set(kCAGravityRight_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityTopLeft typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityTopLeft_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityTopLeft_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityTopLeft").orElseThrow() }
private val kCAGravityTopLeft_VH: VarHandle by lazy { kCAGravityTopLeft_LAYOUT.varHandle() }

var kCAGravityTopLeft: MemorySegment
    get() = kCAGravityTopLeft_VH.get(kCAGravityTopLeft_SEGMENT) as MemorySegment
    set(value) = kCAGravityTopLeft_VH.set(kCAGravityTopLeft_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityTopRight typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityTopRight_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityTopRight_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityTopRight").orElseThrow() }
private val kCAGravityTopRight_VH: VarHandle by lazy { kCAGravityTopRight_LAYOUT.varHandle() }

var kCAGravityTopRight: MemorySegment
    get() = kCAGravityTopRight_VH.get(kCAGravityTopRight_SEGMENT) as MemorySegment
    set(value) = kCAGravityTopRight_VH.set(kCAGravityTopRight_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityBottomLeft typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityBottomLeft_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityBottomLeft_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityBottomLeft").orElseThrow() }
private val kCAGravityBottomLeft_VH: VarHandle by lazy { kCAGravityBottomLeft_LAYOUT.varHandle() }

var kCAGravityBottomLeft: MemorySegment
    get() = kCAGravityBottomLeft_VH.get(kCAGravityBottomLeft_SEGMENT) as MemorySegment
    set(value) = kCAGravityBottomLeft_VH.set(kCAGravityBottomLeft_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityBottomRight typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityBottomRight_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityBottomRight_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityBottomRight").orElseThrow() }
private val kCAGravityBottomRight_VH: VarHandle by lazy { kCAGravityBottomRight_LAYOUT.varHandle() }

var kCAGravityBottomRight: MemorySegment
    get() = kCAGravityBottomRight_VH.get(kCAGravityBottomRight_SEGMENT) as MemorySegment
    set(value) = kCAGravityBottomRight_VH.set(kCAGravityBottomRight_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityResize typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityResize_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityResize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityResize").orElseThrow() }
private val kCAGravityResize_VH: VarHandle by lazy { kCAGravityResize_LAYOUT.varHandle() }

var kCAGravityResize: MemorySegment
    get() = kCAGravityResize_VH.get(kCAGravityResize_SEGMENT) as MemorySegment
    set(value) = kCAGravityResize_VH.set(kCAGravityResize_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityResizeAspect typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityResizeAspect_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityResizeAspect_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityResizeAspect").orElseThrow() }
private val kCAGravityResizeAspect_VH: VarHandle by lazy { kCAGravityResizeAspect_LAYOUT.varHandle() }

var kCAGravityResizeAspect: MemorySegment
    get() = kCAGravityResizeAspect_VH.get(kCAGravityResizeAspect_SEGMENT) as MemorySegment
    set(value) = kCAGravityResizeAspect_VH.set(kCAGravityResizeAspect_SEGMENT, value)

/**
 * {@snippet lang=c : kCAGravityResizeAspectFill typedef const CALayerContentsGravity = (Void)*
 */
private val kCAGravityResizeAspectFill_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAGravityResizeAspectFill_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAGravityResizeAspectFill").orElseThrow() }
private val kCAGravityResizeAspectFill_VH: VarHandle by lazy { kCAGravityResizeAspectFill_LAYOUT.varHandle() }

var kCAGravityResizeAspectFill: MemorySegment
    get() = kCAGravityResizeAspectFill_VH.get(kCAGravityResizeAspectFill_SEGMENT) as MemorySegment
    set(value) = kCAGravityResizeAspectFill_VH.set(kCAGravityResizeAspectFill_SEGMENT, value)

/**
 * {@snippet lang=c : kCAContentsFormatRGBA8Uint typedef const CALayerContentsFormat = (Void)*
 */
private val kCAContentsFormatRGBA8Uint_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAContentsFormatRGBA8Uint_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAContentsFormatRGBA8Uint").orElseThrow() }
private val kCAContentsFormatRGBA8Uint_VH: VarHandle by lazy { kCAContentsFormatRGBA8Uint_LAYOUT.varHandle() }

var kCAContentsFormatRGBA8Uint: MemorySegment
    get() = kCAContentsFormatRGBA8Uint_VH.get(kCAContentsFormatRGBA8Uint_SEGMENT) as MemorySegment
    set(value) = kCAContentsFormatRGBA8Uint_VH.set(kCAContentsFormatRGBA8Uint_SEGMENT, value)

/**
 * {@snippet lang=c : kCAContentsFormatRGBA16Float typedef const CALayerContentsFormat = (Void)*
 */
private val kCAContentsFormatRGBA16Float_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAContentsFormatRGBA16Float_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAContentsFormatRGBA16Float").orElseThrow() }
private val kCAContentsFormatRGBA16Float_VH: VarHandle by lazy { kCAContentsFormatRGBA16Float_LAYOUT.varHandle() }

var kCAContentsFormatRGBA16Float: MemorySegment
    get() = kCAContentsFormatRGBA16Float_VH.get(kCAContentsFormatRGBA16Float_SEGMENT) as MemorySegment
    set(value) = kCAContentsFormatRGBA16Float_VH.set(kCAContentsFormatRGBA16Float_SEGMENT, value)

/**
 * {@snippet lang=c : kCAContentsFormatGray8Uint typedef const CALayerContentsFormat = (Void)*
 */
private val kCAContentsFormatGray8Uint_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAContentsFormatGray8Uint_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAContentsFormatGray8Uint").orElseThrow() }
private val kCAContentsFormatGray8Uint_VH: VarHandle by lazy { kCAContentsFormatGray8Uint_LAYOUT.varHandle() }

var kCAContentsFormatGray8Uint: MemorySegment
    get() = kCAContentsFormatGray8Uint_VH.get(kCAContentsFormatGray8Uint_SEGMENT) as MemorySegment
    set(value) = kCAContentsFormatGray8Uint_VH.set(kCAContentsFormatGray8Uint_SEGMENT, value)

/**
 * {@snippet lang=c : kCAContentsFormatAutomatic typedef const CALayerContentsFormat = (Void)*
 */
private val kCAContentsFormatAutomatic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAContentsFormatAutomatic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAContentsFormatAutomatic").orElseThrow() }
private val kCAContentsFormatAutomatic_VH: VarHandle by lazy { kCAContentsFormatAutomatic_LAYOUT.varHandle() }

var kCAContentsFormatAutomatic: MemorySegment
    get() = kCAContentsFormatAutomatic_VH.get(kCAContentsFormatAutomatic_SEGMENT) as MemorySegment
    set(value) = kCAContentsFormatAutomatic_VH.set(kCAContentsFormatAutomatic_SEGMENT, value)

/**
 * {@snippet lang=c : kCAFilterNearest typedef const CALayerContentsFilter = (Void)*
 */
private val kCAFilterNearest_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAFilterNearest_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAFilterNearest").orElseThrow() }
private val kCAFilterNearest_VH: VarHandle by lazy { kCAFilterNearest_LAYOUT.varHandle() }

var kCAFilterNearest: MemorySegment
    get() = kCAFilterNearest_VH.get(kCAFilterNearest_SEGMENT) as MemorySegment
    set(value) = kCAFilterNearest_VH.set(kCAFilterNearest_SEGMENT, value)

/**
 * {@snippet lang=c : kCAFilterLinear typedef const CALayerContentsFilter = (Void)*
 */
private val kCAFilterLinear_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAFilterLinear_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAFilterLinear").orElseThrow() }
private val kCAFilterLinear_VH: VarHandle by lazy { kCAFilterLinear_LAYOUT.varHandle() }

var kCAFilterLinear: MemorySegment
    get() = kCAFilterLinear_VH.get(kCAFilterLinear_SEGMENT) as MemorySegment
    set(value) = kCAFilterLinear_VH.set(kCAFilterLinear_SEGMENT, value)

/**
 * {@snippet lang=c : kCAFilterTrilinear typedef const CALayerContentsFilter = (Void)*
 */
private val kCAFilterTrilinear_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAFilterTrilinear_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAFilterTrilinear").orElseThrow() }
private val kCAFilterTrilinear_VH: VarHandle by lazy { kCAFilterTrilinear_LAYOUT.varHandle() }

var kCAFilterTrilinear: MemorySegment
    get() = kCAFilterTrilinear_VH.get(kCAFilterTrilinear_SEGMENT) as MemorySegment
    set(value) = kCAFilterTrilinear_VH.set(kCAFilterTrilinear_SEGMENT, value)

/**
 * {@snippet lang=c : kCACornerCurveCircular typedef const CALayerCornerCurve = (Void)*
 */
private val kCACornerCurveCircular_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCACornerCurveCircular_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCACornerCurveCircular").orElseThrow() }
private val kCACornerCurveCircular_VH: VarHandle by lazy { kCACornerCurveCircular_LAYOUT.varHandle() }

var kCACornerCurveCircular: MemorySegment
    get() = kCACornerCurveCircular_VH.get(kCACornerCurveCircular_SEGMENT) as MemorySegment
    set(value) = kCACornerCurveCircular_VH.set(kCACornerCurveCircular_SEGMENT, value)

/**
 * {@snippet lang=c : kCACornerCurveContinuous typedef const CALayerCornerCurve = (Void)*
 */
private val kCACornerCurveContinuous_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCACornerCurveContinuous_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCACornerCurveContinuous").orElseThrow() }
private val kCACornerCurveContinuous_VH: VarHandle by lazy { kCACornerCurveContinuous_LAYOUT.varHandle() }

var kCACornerCurveContinuous: MemorySegment
    get() = kCACornerCurveContinuous_VH.get(kCACornerCurveContinuous_SEGMENT) as MemorySegment
    set(value) = kCACornerCurveContinuous_VH.set(kCACornerCurveContinuous_SEGMENT, value)

/**
 * {@snippet lang=c : kCAOnOrderIn (Void)*
 */
private val kCAOnOrderIn_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAOnOrderIn_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAOnOrderIn").orElseThrow() }
private val kCAOnOrderIn_VH: VarHandle by lazy { kCAOnOrderIn_LAYOUT.varHandle() }

var kCAOnOrderIn: MemorySegment
    get() = kCAOnOrderIn_VH.get(kCAOnOrderIn_SEGMENT) as MemorySegment
    set(value) = kCAOnOrderIn_VH.set(kCAOnOrderIn_SEGMENT, value)

/**
 * {@snippet lang=c : kCAOnOrderOut (Void)*
 */
private val kCAOnOrderOut_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCAOnOrderOut_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCAOnOrderOut").orElseThrow() }
private val kCAOnOrderOut_VH: VarHandle by lazy { kCAOnOrderOut_LAYOUT.varHandle() }

var kCAOnOrderOut: MemorySegment
    get() = kCAOnOrderOut_VH.get(kCAOnOrderOut_SEGMENT) as MemorySegment
    set(value) = kCAOnOrderOut_VH.set(kCAOnOrderOut_SEGMENT, value)

/**
 * {@snippet lang=c : kCATransition (Void)*
 */
private val kCATransition_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val kCATransition_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("kCATransition").orElseThrow() }
private val kCATransition_VH: VarHandle by lazy { kCATransition_LAYOUT.varHandle() }

var kCATransition: MemorySegment
    get() = kCATransition_VH.get(kCATransition_SEGMENT) as MemorySegment
    set(value) = kCATransition_VH.set(kCATransition_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerBox typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerBox_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerBox_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerBox").orElseThrow() }
private val NSTextListMarkerBox_VH: VarHandle by lazy { NSTextListMarkerBox_LAYOUT.varHandle() }

var NSTextListMarkerBox: MemorySegment
    get() = NSTextListMarkerBox_VH.get(NSTextListMarkerBox_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerBox_VH.set(NSTextListMarkerBox_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerCheck typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerCheck_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerCheck_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerCheck").orElseThrow() }
private val NSTextListMarkerCheck_VH: VarHandle by lazy { NSTextListMarkerCheck_LAYOUT.varHandle() }

var NSTextListMarkerCheck: MemorySegment
    get() = NSTextListMarkerCheck_VH.get(NSTextListMarkerCheck_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerCheck_VH.set(NSTextListMarkerCheck_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerCircle typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerCircle_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerCircle_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerCircle").orElseThrow() }
private val NSTextListMarkerCircle_VH: VarHandle by lazy { NSTextListMarkerCircle_LAYOUT.varHandle() }

var NSTextListMarkerCircle: MemorySegment
    get() = NSTextListMarkerCircle_VH.get(NSTextListMarkerCircle_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerCircle_VH.set(NSTextListMarkerCircle_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerDiamond typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerDiamond_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerDiamond_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerDiamond").orElseThrow() }
private val NSTextListMarkerDiamond_VH: VarHandle by lazy { NSTextListMarkerDiamond_LAYOUT.varHandle() }

var NSTextListMarkerDiamond: MemorySegment
    get() = NSTextListMarkerDiamond_VH.get(NSTextListMarkerDiamond_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerDiamond_VH.set(NSTextListMarkerDiamond_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerDisc typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerDisc_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerDisc_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerDisc").orElseThrow() }
private val NSTextListMarkerDisc_VH: VarHandle by lazy { NSTextListMarkerDisc_LAYOUT.varHandle() }

var NSTextListMarkerDisc: MemorySegment
    get() = NSTextListMarkerDisc_VH.get(NSTextListMarkerDisc_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerDisc_VH.set(NSTextListMarkerDisc_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerHyphen typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerHyphen_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerHyphen_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerHyphen").orElseThrow() }
private val NSTextListMarkerHyphen_VH: VarHandle by lazy { NSTextListMarkerHyphen_LAYOUT.varHandle() }

var NSTextListMarkerHyphen: MemorySegment
    get() = NSTextListMarkerHyphen_VH.get(NSTextListMarkerHyphen_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerHyphen_VH.set(NSTextListMarkerHyphen_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerSquare typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerSquare_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerSquare_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerSquare").orElseThrow() }
private val NSTextListMarkerSquare_VH: VarHandle by lazy { NSTextListMarkerSquare_LAYOUT.varHandle() }

var NSTextListMarkerSquare: MemorySegment
    get() = NSTextListMarkerSquare_VH.get(NSTextListMarkerSquare_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerSquare_VH.set(NSTextListMarkerSquare_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerLowercaseHexadecimal typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerLowercaseHexadecimal_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerLowercaseHexadecimal_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerLowercaseHexadecimal").orElseThrow() }
private val NSTextListMarkerLowercaseHexadecimal_VH: VarHandle by lazy { NSTextListMarkerLowercaseHexadecimal_LAYOUT.varHandle() }

var NSTextListMarkerLowercaseHexadecimal: MemorySegment
    get() = NSTextListMarkerLowercaseHexadecimal_VH.get(NSTextListMarkerLowercaseHexadecimal_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerLowercaseHexadecimal_VH.set(NSTextListMarkerLowercaseHexadecimal_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerUppercaseHexadecimal typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerUppercaseHexadecimal_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerUppercaseHexadecimal_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerUppercaseHexadecimal").orElseThrow() }
private val NSTextListMarkerUppercaseHexadecimal_VH: VarHandle by lazy { NSTextListMarkerUppercaseHexadecimal_LAYOUT.varHandle() }

var NSTextListMarkerUppercaseHexadecimal: MemorySegment
    get() = NSTextListMarkerUppercaseHexadecimal_VH.get(NSTextListMarkerUppercaseHexadecimal_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerUppercaseHexadecimal_VH.set(NSTextListMarkerUppercaseHexadecimal_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerOctal typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerOctal_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerOctal_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerOctal").orElseThrow() }
private val NSTextListMarkerOctal_VH: VarHandle by lazy { NSTextListMarkerOctal_LAYOUT.varHandle() }

var NSTextListMarkerOctal: MemorySegment
    get() = NSTextListMarkerOctal_VH.get(NSTextListMarkerOctal_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerOctal_VH.set(NSTextListMarkerOctal_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerLowercaseAlpha typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerLowercaseAlpha_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerLowercaseAlpha_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerLowercaseAlpha").orElseThrow() }
private val NSTextListMarkerLowercaseAlpha_VH: VarHandle by lazy { NSTextListMarkerLowercaseAlpha_LAYOUT.varHandle() }

var NSTextListMarkerLowercaseAlpha: MemorySegment
    get() = NSTextListMarkerLowercaseAlpha_VH.get(NSTextListMarkerLowercaseAlpha_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerLowercaseAlpha_VH.set(NSTextListMarkerLowercaseAlpha_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerUppercaseAlpha typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerUppercaseAlpha_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerUppercaseAlpha_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerUppercaseAlpha").orElseThrow() }
private val NSTextListMarkerUppercaseAlpha_VH: VarHandle by lazy { NSTextListMarkerUppercaseAlpha_LAYOUT.varHandle() }

var NSTextListMarkerUppercaseAlpha: MemorySegment
    get() = NSTextListMarkerUppercaseAlpha_VH.get(NSTextListMarkerUppercaseAlpha_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerUppercaseAlpha_VH.set(NSTextListMarkerUppercaseAlpha_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerLowercaseLatin typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerLowercaseLatin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerLowercaseLatin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerLowercaseLatin").orElseThrow() }
private val NSTextListMarkerLowercaseLatin_VH: VarHandle by lazy { NSTextListMarkerLowercaseLatin_LAYOUT.varHandle() }

var NSTextListMarkerLowercaseLatin: MemorySegment
    get() = NSTextListMarkerLowercaseLatin_VH.get(NSTextListMarkerLowercaseLatin_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerLowercaseLatin_VH.set(NSTextListMarkerLowercaseLatin_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerUppercaseLatin typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerUppercaseLatin_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerUppercaseLatin_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerUppercaseLatin").orElseThrow() }
private val NSTextListMarkerUppercaseLatin_VH: VarHandle by lazy { NSTextListMarkerUppercaseLatin_LAYOUT.varHandle() }

var NSTextListMarkerUppercaseLatin: MemorySegment
    get() = NSTextListMarkerUppercaseLatin_VH.get(NSTextListMarkerUppercaseLatin_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerUppercaseLatin_VH.set(NSTextListMarkerUppercaseLatin_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerLowercaseRoman typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerLowercaseRoman_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerLowercaseRoman_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerLowercaseRoman").orElseThrow() }
private val NSTextListMarkerLowercaseRoman_VH: VarHandle by lazy { NSTextListMarkerLowercaseRoman_LAYOUT.varHandle() }

var NSTextListMarkerLowercaseRoman: MemorySegment
    get() = NSTextListMarkerLowercaseRoman_VH.get(NSTextListMarkerLowercaseRoman_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerLowercaseRoman_VH.set(NSTextListMarkerLowercaseRoman_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerUppercaseRoman typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerUppercaseRoman_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerUppercaseRoman_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerUppercaseRoman").orElseThrow() }
private val NSTextListMarkerUppercaseRoman_VH: VarHandle by lazy { NSTextListMarkerUppercaseRoman_LAYOUT.varHandle() }

var NSTextListMarkerUppercaseRoman: MemorySegment
    get() = NSTextListMarkerUppercaseRoman_VH.get(NSTextListMarkerUppercaseRoman_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerUppercaseRoman_VH.set(NSTextListMarkerUppercaseRoman_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextListMarkerDecimal typedef const NSTextListMarkerFormat = (Void)*
 */
private val NSTextListMarkerDecimal_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextListMarkerDecimal_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextListMarkerDecimal").orElseThrow() }
private val NSTextListMarkerDecimal_VH: VarHandle by lazy { NSTextListMarkerDecimal_LAYOUT.varHandle() }

var NSTextListMarkerDecimal: MemorySegment
    get() = NSTextListMarkerDecimal_VH.get(NSTextListMarkerDecimal_SEGMENT) as MemorySegment
    set(value) = NSTextListMarkerDecimal_VH.set(NSTextListMarkerDecimal_SEGMENT, value)

/**
 * {@snippet lang=c : NSRuleEditorPredicateLeftExpression typedef const NSRuleEditorPredicatePartKey = (Void)*
 */
private val NSRuleEditorPredicateLeftExpression_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRuleEditorPredicateLeftExpression_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRuleEditorPredicateLeftExpression").orElseThrow() }
private val NSRuleEditorPredicateLeftExpression_VH: VarHandle by lazy { NSRuleEditorPredicateLeftExpression_LAYOUT.varHandle() }

var NSRuleEditorPredicateLeftExpression: MemorySegment
    get() = NSRuleEditorPredicateLeftExpression_VH.get(NSRuleEditorPredicateLeftExpression_SEGMENT) as MemorySegment
    set(value) = NSRuleEditorPredicateLeftExpression_VH.set(NSRuleEditorPredicateLeftExpression_SEGMENT, value)

/**
 * {@snippet lang=c : NSRuleEditorPredicateRightExpression typedef const NSRuleEditorPredicatePartKey = (Void)*
 */
private val NSRuleEditorPredicateRightExpression_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRuleEditorPredicateRightExpression_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRuleEditorPredicateRightExpression").orElseThrow() }
private val NSRuleEditorPredicateRightExpression_VH: VarHandle by lazy { NSRuleEditorPredicateRightExpression_LAYOUT.varHandle() }

var NSRuleEditorPredicateRightExpression: MemorySegment
    get() = NSRuleEditorPredicateRightExpression_VH.get(NSRuleEditorPredicateRightExpression_SEGMENT) as MemorySegment
    set(value) = NSRuleEditorPredicateRightExpression_VH.set(NSRuleEditorPredicateRightExpression_SEGMENT, value)

/**
 * {@snippet lang=c : NSRuleEditorPredicateComparisonModifier typedef const NSRuleEditorPredicatePartKey = (Void)*
 */
private val NSRuleEditorPredicateComparisonModifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRuleEditorPredicateComparisonModifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRuleEditorPredicateComparisonModifier").orElseThrow() }
private val NSRuleEditorPredicateComparisonModifier_VH: VarHandle by lazy { NSRuleEditorPredicateComparisonModifier_LAYOUT.varHandle() }

var NSRuleEditorPredicateComparisonModifier: MemorySegment
    get() = NSRuleEditorPredicateComparisonModifier_VH.get(NSRuleEditorPredicateComparisonModifier_SEGMENT) as MemorySegment
    set(value) = NSRuleEditorPredicateComparisonModifier_VH.set(NSRuleEditorPredicateComparisonModifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSRuleEditorPredicateOptions typedef const NSRuleEditorPredicatePartKey = (Void)*
 */
private val NSRuleEditorPredicateOptions_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRuleEditorPredicateOptions_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRuleEditorPredicateOptions").orElseThrow() }
private val NSRuleEditorPredicateOptions_VH: VarHandle by lazy { NSRuleEditorPredicateOptions_LAYOUT.varHandle() }

var NSRuleEditorPredicateOptions: MemorySegment
    get() = NSRuleEditorPredicateOptions_VH.get(NSRuleEditorPredicateOptions_SEGMENT) as MemorySegment
    set(value) = NSRuleEditorPredicateOptions_VH.set(NSRuleEditorPredicateOptions_SEGMENT, value)

/**
 * {@snippet lang=c : NSRuleEditorPredicateOperatorType typedef const NSRuleEditorPredicatePartKey = (Void)*
 */
private val NSRuleEditorPredicateOperatorType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRuleEditorPredicateOperatorType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRuleEditorPredicateOperatorType").orElseThrow() }
private val NSRuleEditorPredicateOperatorType_VH: VarHandle by lazy { NSRuleEditorPredicateOperatorType_LAYOUT.varHandle() }

var NSRuleEditorPredicateOperatorType: MemorySegment
    get() = NSRuleEditorPredicateOperatorType_VH.get(NSRuleEditorPredicateOperatorType_SEGMENT) as MemorySegment
    set(value) = NSRuleEditorPredicateOperatorType_VH.set(NSRuleEditorPredicateOperatorType_SEGMENT, value)

/**
 * {@snippet lang=c : NSRuleEditorPredicateCustomSelector typedef const NSRuleEditorPredicatePartKey = (Void)*
 */
private val NSRuleEditorPredicateCustomSelector_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRuleEditorPredicateCustomSelector_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRuleEditorPredicateCustomSelector").orElseThrow() }
private val NSRuleEditorPredicateCustomSelector_VH: VarHandle by lazy { NSRuleEditorPredicateCustomSelector_LAYOUT.varHandle() }

var NSRuleEditorPredicateCustomSelector: MemorySegment
    get() = NSRuleEditorPredicateCustomSelector_VH.get(NSRuleEditorPredicateCustomSelector_SEGMENT) as MemorySegment
    set(value) = NSRuleEditorPredicateCustomSelector_VH.set(NSRuleEditorPredicateCustomSelector_SEGMENT, value)

/**
 * {@snippet lang=c : NSRuleEditorPredicateCompoundType typedef const NSRuleEditorPredicatePartKey = (Void)*
 */
private val NSRuleEditorPredicateCompoundType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRuleEditorPredicateCompoundType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRuleEditorPredicateCompoundType").orElseThrow() }
private val NSRuleEditorPredicateCompoundType_VH: VarHandle by lazy { NSRuleEditorPredicateCompoundType_LAYOUT.varHandle() }

var NSRuleEditorPredicateCompoundType: MemorySegment
    get() = NSRuleEditorPredicateCompoundType_VH.get(NSRuleEditorPredicateCompoundType_SEGMENT) as MemorySegment
    set(value) = NSRuleEditorPredicateCompoundType_VH.set(NSRuleEditorPredicateCompoundType_SEGMENT, value)

/**
 * {@snippet lang=c : NSRuleEditorRowsDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSRuleEditorRowsDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRuleEditorRowsDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRuleEditorRowsDidChangeNotification").orElseThrow() }
private val NSRuleEditorRowsDidChangeNotification_VH: VarHandle by lazy { NSRuleEditorRowsDidChangeNotification_LAYOUT.varHandle() }

var NSRuleEditorRowsDidChangeNotification: MemorySegment
    get() = NSRuleEditorRowsDidChangeNotification_VH.get(NSRuleEditorRowsDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSRuleEditorRowsDidChangeNotification_VH.set(NSRuleEditorRowsDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextInputContextKeyboardSelectionDidChangeNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextInputContextKeyboardSelectionDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextInputContextKeyboardSelectionDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextInputContextKeyboardSelectionDidChangeNotification").orElseThrow() }
private val NSTextInputContextKeyboardSelectionDidChangeNotification_VH: VarHandle by lazy { NSTextInputContextKeyboardSelectionDidChangeNotification_LAYOUT.varHandle() }

var NSTextInputContextKeyboardSelectionDidChangeNotification: MemorySegment
    get() = NSTextInputContextKeyboardSelectionDidChangeNotification_VH.get(NSTextInputContextKeyboardSelectionDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSTextInputContextKeyboardSelectionDidChangeNotification_VH.set(NSTextInputContextKeyboardSelectionDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSApplicationDidFinishRestoringWindowsNotification typedef const NSNotificationName = (Void)*
 */
private val NSApplicationDidFinishRestoringWindowsNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSApplicationDidFinishRestoringWindowsNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSApplicationDidFinishRestoringWindowsNotification").orElseThrow() }
private val NSApplicationDidFinishRestoringWindowsNotification_VH: VarHandle by lazy { NSApplicationDidFinishRestoringWindowsNotification_LAYOUT.varHandle() }

var NSApplicationDidFinishRestoringWindowsNotification: MemorySegment
    get() = NSApplicationDidFinishRestoringWindowsNotification_VH.get(NSApplicationDidFinishRestoringWindowsNotification_SEGMENT) as MemorySegment
    set(value) = NSApplicationDidFinishRestoringWindowsNotification_VH.set(NSApplicationDidFinishRestoringWindowsNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextAlternativesSelectedAlternativeStringNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextAlternativesSelectedAlternativeStringNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextAlternativesSelectedAlternativeStringNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextAlternativesSelectedAlternativeStringNotification").orElseThrow() }
private val NSTextAlternativesSelectedAlternativeStringNotification_VH: VarHandle by lazy { NSTextAlternativesSelectedAlternativeStringNotification_LAYOUT.varHandle() }

var NSTextAlternativesSelectedAlternativeStringNotification: MemorySegment
    get() = NSTextAlternativesSelectedAlternativeStringNotification_VH.get(NSTextAlternativesSelectedAlternativeStringNotification_SEGMENT) as MemorySegment
    set(value) = NSTextAlternativesSelectedAlternativeStringNotification_VH.set(NSTextAlternativesSelectedAlternativeStringNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTypeIdentifierDateText (Void)*
 */
private val NSTypeIdentifierDateText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTypeIdentifierDateText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTypeIdentifierDateText").orElseThrow() }
private val NSTypeIdentifierDateText_VH: VarHandle by lazy { NSTypeIdentifierDateText_LAYOUT.varHandle() }

var NSTypeIdentifierDateText: MemorySegment
    get() = NSTypeIdentifierDateText_VH.get(NSTypeIdentifierDateText_SEGMENT) as MemorySegment
    set(value) = NSTypeIdentifierDateText_VH.set(NSTypeIdentifierDateText_SEGMENT, value)

/**
 * {@snippet lang=c : NSTypeIdentifierAddressText (Void)*
 */
private val NSTypeIdentifierAddressText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTypeIdentifierAddressText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTypeIdentifierAddressText").orElseThrow() }
private val NSTypeIdentifierAddressText_VH: VarHandle by lazy { NSTypeIdentifierAddressText_LAYOUT.varHandle() }

var NSTypeIdentifierAddressText: MemorySegment
    get() = NSTypeIdentifierAddressText_VH.get(NSTypeIdentifierAddressText_SEGMENT) as MemorySegment
    set(value) = NSTypeIdentifierAddressText_VH.set(NSTypeIdentifierAddressText_SEGMENT, value)

/**
 * {@snippet lang=c : NSTypeIdentifierPhoneNumberText (Void)*
 */
private val NSTypeIdentifierPhoneNumberText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTypeIdentifierPhoneNumberText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTypeIdentifierPhoneNumberText").orElseThrow() }
private val NSTypeIdentifierPhoneNumberText_VH: VarHandle by lazy { NSTypeIdentifierPhoneNumberText_LAYOUT.varHandle() }

var NSTypeIdentifierPhoneNumberText: MemorySegment
    get() = NSTypeIdentifierPhoneNumberText_VH.get(NSTypeIdentifierPhoneNumberText_SEGMENT) as MemorySegment
    set(value) = NSTypeIdentifierPhoneNumberText_VH.set(NSTypeIdentifierPhoneNumberText_SEGMENT, value)

/**
 * {@snippet lang=c : NSTypeIdentifierTransitInformationText (Void)*
 */
private val NSTypeIdentifierTransitInformationText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTypeIdentifierTransitInformationText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTypeIdentifierTransitInformationText").orElseThrow() }
private val NSTypeIdentifierTransitInformationText_VH: VarHandle by lazy { NSTypeIdentifierTransitInformationText_LAYOUT.varHandle() }

var NSTypeIdentifierTransitInformationText: MemorySegment
    get() = NSTypeIdentifierTransitInformationText_VH.get(NSTypeIdentifierTransitInformationText_SEGMENT) as MemorySegment
    set(value) = NSTypeIdentifierTransitInformationText_VH.set(NSTypeIdentifierTransitInformationText_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentStorageUnsupportedAttributeAddedNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSTextContentStorageUnsupportedAttributeAddedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentStorageUnsupportedAttributeAddedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentStorageUnsupportedAttributeAddedNotification").orElseThrow() }
private val NSTextContentStorageUnsupportedAttributeAddedNotification_VH: VarHandle by lazy { NSTextContentStorageUnsupportedAttributeAddedNotification_LAYOUT.varHandle() }

var NSTextContentStorageUnsupportedAttributeAddedNotification: MemorySegment
    get() = NSTextContentStorageUnsupportedAttributeAddedNotification_VH.get(NSTextContentStorageUnsupportedAttributeAddedNotification_SEGMENT) as MemorySegment
    set(value) = NSTextContentStorageUnsupportedAttributeAddedNotification_VH.set(NSTextContentStorageUnsupportedAttributeAddedNotification_SEGMENT, value)

