package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : NSImageNameQuickLookTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameQuickLookTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameQuickLookTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameQuickLookTemplate").orElseThrow() }
private val NSImageNameQuickLookTemplate_VH: VarHandle by lazy { NSImageNameQuickLookTemplate_LAYOUT.varHandle() }

var NSImageNameQuickLookTemplate: MemorySegment
    get() = NSImageNameQuickLookTemplate_VH.get(NSImageNameQuickLookTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameQuickLookTemplate_VH.set(NSImageNameQuickLookTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameRefreshFreestandingTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameRefreshFreestandingTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameRefreshFreestandingTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameRefreshFreestandingTemplate").orElseThrow() }
private val NSImageNameRefreshFreestandingTemplate_VH: VarHandle by lazy { NSImageNameRefreshFreestandingTemplate_LAYOUT.varHandle() }

var NSImageNameRefreshFreestandingTemplate: MemorySegment
    get() = NSImageNameRefreshFreestandingTemplate_VH.get(NSImageNameRefreshFreestandingTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameRefreshFreestandingTemplate_VH.set(NSImageNameRefreshFreestandingTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameRefreshTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameRefreshTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameRefreshTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameRefreshTemplate").orElseThrow() }
private val NSImageNameRefreshTemplate_VH: VarHandle by lazy { NSImageNameRefreshTemplate_LAYOUT.varHandle() }

var NSImageNameRefreshTemplate: MemorySegment
    get() = NSImageNameRefreshTemplate_VH.get(NSImageNameRefreshTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameRefreshTemplate_VH.set(NSImageNameRefreshTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameRemoveTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameRemoveTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameRemoveTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameRemoveTemplate").orElseThrow() }
private val NSImageNameRemoveTemplate_VH: VarHandle by lazy { NSImageNameRemoveTemplate_LAYOUT.varHandle() }

var NSImageNameRemoveTemplate: MemorySegment
    get() = NSImageNameRemoveTemplate_VH.get(NSImageNameRemoveTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameRemoveTemplate_VH.set(NSImageNameRemoveTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameRevealFreestandingTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameRevealFreestandingTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameRevealFreestandingTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameRevealFreestandingTemplate").orElseThrow() }
private val NSImageNameRevealFreestandingTemplate_VH: VarHandle by lazy { NSImageNameRevealFreestandingTemplate_LAYOUT.varHandle() }

var NSImageNameRevealFreestandingTemplate: MemorySegment
    get() = NSImageNameRevealFreestandingTemplate_VH.get(NSImageNameRevealFreestandingTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameRevealFreestandingTemplate_VH.set(NSImageNameRevealFreestandingTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameShareTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameShareTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameShareTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameShareTemplate").orElseThrow() }
private val NSImageNameShareTemplate_VH: VarHandle by lazy { NSImageNameShareTemplate_LAYOUT.varHandle() }

var NSImageNameShareTemplate: MemorySegment
    get() = NSImageNameShareTemplate_VH.get(NSImageNameShareTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameShareTemplate_VH.set(NSImageNameShareTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameSlideshowTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameSlideshowTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameSlideshowTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameSlideshowTemplate").orElseThrow() }
private val NSImageNameSlideshowTemplate_VH: VarHandle by lazy { NSImageNameSlideshowTemplate_LAYOUT.varHandle() }

var NSImageNameSlideshowTemplate: MemorySegment
    get() = NSImageNameSlideshowTemplate_VH.get(NSImageNameSlideshowTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameSlideshowTemplate_VH.set(NSImageNameSlideshowTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameStatusAvailable typedef const NSImageName = (Void)*
 */
private val NSImageNameStatusAvailable_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameStatusAvailable_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameStatusAvailable").orElseThrow() }
private val NSImageNameStatusAvailable_VH: VarHandle by lazy { NSImageNameStatusAvailable_LAYOUT.varHandle() }

var NSImageNameStatusAvailable: MemorySegment
    get() = NSImageNameStatusAvailable_VH.get(NSImageNameStatusAvailable_SEGMENT) as MemorySegment
    set(value) = NSImageNameStatusAvailable_VH.set(NSImageNameStatusAvailable_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameStatusNone typedef const NSImageName = (Void)*
 */
private val NSImageNameStatusNone_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameStatusNone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameStatusNone").orElseThrow() }
private val NSImageNameStatusNone_VH: VarHandle by lazy { NSImageNameStatusNone_LAYOUT.varHandle() }

var NSImageNameStatusNone: MemorySegment
    get() = NSImageNameStatusNone_VH.get(NSImageNameStatusNone_SEGMENT) as MemorySegment
    set(value) = NSImageNameStatusNone_VH.set(NSImageNameStatusNone_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameStatusPartiallyAvailable typedef const NSImageName = (Void)*
 */
private val NSImageNameStatusPartiallyAvailable_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameStatusPartiallyAvailable_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameStatusPartiallyAvailable").orElseThrow() }
private val NSImageNameStatusPartiallyAvailable_VH: VarHandle by lazy { NSImageNameStatusPartiallyAvailable_LAYOUT.varHandle() }

var NSImageNameStatusPartiallyAvailable: MemorySegment
    get() = NSImageNameStatusPartiallyAvailable_VH.get(NSImageNameStatusPartiallyAvailable_SEGMENT) as MemorySegment
    set(value) = NSImageNameStatusPartiallyAvailable_VH.set(NSImageNameStatusPartiallyAvailable_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameStatusUnavailable typedef const NSImageName = (Void)*
 */
private val NSImageNameStatusUnavailable_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameStatusUnavailable_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameStatusUnavailable").orElseThrow() }
private val NSImageNameStatusUnavailable_VH: VarHandle by lazy { NSImageNameStatusUnavailable_LAYOUT.varHandle() }

var NSImageNameStatusUnavailable: MemorySegment
    get() = NSImageNameStatusUnavailable_VH.get(NSImageNameStatusUnavailable_SEGMENT) as MemorySegment
    set(value) = NSImageNameStatusUnavailable_VH.set(NSImageNameStatusUnavailable_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameStopProgressFreestandingTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameStopProgressFreestandingTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameStopProgressFreestandingTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameStopProgressFreestandingTemplate").orElseThrow() }
private val NSImageNameStopProgressFreestandingTemplate_VH: VarHandle by lazy { NSImageNameStopProgressFreestandingTemplate_LAYOUT.varHandle() }

var NSImageNameStopProgressFreestandingTemplate: MemorySegment
    get() = NSImageNameStopProgressFreestandingTemplate_VH.get(NSImageNameStopProgressFreestandingTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameStopProgressFreestandingTemplate_VH.set(NSImageNameStopProgressFreestandingTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameStopProgressTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameStopProgressTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameStopProgressTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameStopProgressTemplate").orElseThrow() }
private val NSImageNameStopProgressTemplate_VH: VarHandle by lazy { NSImageNameStopProgressTemplate_LAYOUT.varHandle() }

var NSImageNameStopProgressTemplate: MemorySegment
    get() = NSImageNameStopProgressTemplate_VH.get(NSImageNameStopProgressTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameStopProgressTemplate_VH.set(NSImageNameStopProgressTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTrashEmpty typedef const NSImageName = (Void)*
 */
private val NSImageNameTrashEmpty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTrashEmpty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTrashEmpty").orElseThrow() }
private val NSImageNameTrashEmpty_VH: VarHandle by lazy { NSImageNameTrashEmpty_LAYOUT.varHandle() }

var NSImageNameTrashEmpty: MemorySegment
    get() = NSImageNameTrashEmpty_VH.get(NSImageNameTrashEmpty_SEGMENT) as MemorySegment
    set(value) = NSImageNameTrashEmpty_VH.set(NSImageNameTrashEmpty_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTrashFull typedef const NSImageName = (Void)*
 */
private val NSImageNameTrashFull_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTrashFull_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTrashFull").orElseThrow() }
private val NSImageNameTrashFull_VH: VarHandle by lazy { NSImageNameTrashFull_LAYOUT.varHandle() }

var NSImageNameTrashFull: MemorySegment
    get() = NSImageNameTrashFull_VH.get(NSImageNameTrashFull_SEGMENT) as MemorySegment
    set(value) = NSImageNameTrashFull_VH.set(NSImageNameTrashFull_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameActionTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameActionTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameActionTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameActionTemplate").orElseThrow() }
private val NSImageNameActionTemplate_VH: VarHandle by lazy { NSImageNameActionTemplate_LAYOUT.varHandle() }

var NSImageNameActionTemplate: MemorySegment
    get() = NSImageNameActionTemplate_VH.get(NSImageNameActionTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameActionTemplate_VH.set(NSImageNameActionTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameSmartBadgeTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameSmartBadgeTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameSmartBadgeTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameSmartBadgeTemplate").orElseThrow() }
private val NSImageNameSmartBadgeTemplate_VH: VarHandle by lazy { NSImageNameSmartBadgeTemplate_LAYOUT.varHandle() }

var NSImageNameSmartBadgeTemplate: MemorySegment
    get() = NSImageNameSmartBadgeTemplate_VH.get(NSImageNameSmartBadgeTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameSmartBadgeTemplate_VH.set(NSImageNameSmartBadgeTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameIconViewTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameIconViewTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameIconViewTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameIconViewTemplate").orElseThrow() }
private val NSImageNameIconViewTemplate_VH: VarHandle by lazy { NSImageNameIconViewTemplate_LAYOUT.varHandle() }

var NSImageNameIconViewTemplate: MemorySegment
    get() = NSImageNameIconViewTemplate_VH.get(NSImageNameIconViewTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameIconViewTemplate_VH.set(NSImageNameIconViewTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameListViewTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameListViewTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameListViewTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameListViewTemplate").orElseThrow() }
private val NSImageNameListViewTemplate_VH: VarHandle by lazy { NSImageNameListViewTemplate_LAYOUT.varHandle() }

var NSImageNameListViewTemplate: MemorySegment
    get() = NSImageNameListViewTemplate_VH.get(NSImageNameListViewTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameListViewTemplate_VH.set(NSImageNameListViewTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameColumnViewTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameColumnViewTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameColumnViewTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameColumnViewTemplate").orElseThrow() }
private val NSImageNameColumnViewTemplate_VH: VarHandle by lazy { NSImageNameColumnViewTemplate_LAYOUT.varHandle() }

var NSImageNameColumnViewTemplate: MemorySegment
    get() = NSImageNameColumnViewTemplate_VH.get(NSImageNameColumnViewTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameColumnViewTemplate_VH.set(NSImageNameColumnViewTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameFlowViewTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameFlowViewTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameFlowViewTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameFlowViewTemplate").orElseThrow() }
private val NSImageNameFlowViewTemplate_VH: VarHandle by lazy { NSImageNameFlowViewTemplate_LAYOUT.varHandle() }

var NSImageNameFlowViewTemplate: MemorySegment
    get() = NSImageNameFlowViewTemplate_VH.get(NSImageNameFlowViewTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameFlowViewTemplate_VH.set(NSImageNameFlowViewTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameInvalidDataFreestandingTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameInvalidDataFreestandingTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameInvalidDataFreestandingTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameInvalidDataFreestandingTemplate").orElseThrow() }
private val NSImageNameInvalidDataFreestandingTemplate_VH: VarHandle by lazy { NSImageNameInvalidDataFreestandingTemplate_LAYOUT.varHandle() }

var NSImageNameInvalidDataFreestandingTemplate: MemorySegment
    get() = NSImageNameInvalidDataFreestandingTemplate_VH.get(NSImageNameInvalidDataFreestandingTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameInvalidDataFreestandingTemplate_VH.set(NSImageNameInvalidDataFreestandingTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameGoForwardTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameGoForwardTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameGoForwardTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameGoForwardTemplate").orElseThrow() }
private val NSImageNameGoForwardTemplate_VH: VarHandle by lazy { NSImageNameGoForwardTemplate_LAYOUT.varHandle() }

var NSImageNameGoForwardTemplate: MemorySegment
    get() = NSImageNameGoForwardTemplate_VH.get(NSImageNameGoForwardTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameGoForwardTemplate_VH.set(NSImageNameGoForwardTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameGoBackTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameGoBackTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameGoBackTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameGoBackTemplate").orElseThrow() }
private val NSImageNameGoBackTemplate_VH: VarHandle by lazy { NSImageNameGoBackTemplate_LAYOUT.varHandle() }

var NSImageNameGoBackTemplate: MemorySegment
    get() = NSImageNameGoBackTemplate_VH.get(NSImageNameGoBackTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameGoBackTemplate_VH.set(NSImageNameGoBackTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameGoRightTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameGoRightTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameGoRightTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameGoRightTemplate").orElseThrow() }
private val NSImageNameGoRightTemplate_VH: VarHandle by lazy { NSImageNameGoRightTemplate_LAYOUT.varHandle() }

var NSImageNameGoRightTemplate: MemorySegment
    get() = NSImageNameGoRightTemplate_VH.get(NSImageNameGoRightTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameGoRightTemplate_VH.set(NSImageNameGoRightTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameGoLeftTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameGoLeftTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameGoLeftTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameGoLeftTemplate").orElseThrow() }
private val NSImageNameGoLeftTemplate_VH: VarHandle by lazy { NSImageNameGoLeftTemplate_LAYOUT.varHandle() }

var NSImageNameGoLeftTemplate: MemorySegment
    get() = NSImageNameGoLeftTemplate_VH.get(NSImageNameGoLeftTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameGoLeftTemplate_VH.set(NSImageNameGoLeftTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameRightFacingTriangleTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameRightFacingTriangleTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameRightFacingTriangleTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameRightFacingTriangleTemplate").orElseThrow() }
private val NSImageNameRightFacingTriangleTemplate_VH: VarHandle by lazy { NSImageNameRightFacingTriangleTemplate_LAYOUT.varHandle() }

var NSImageNameRightFacingTriangleTemplate: MemorySegment
    get() = NSImageNameRightFacingTriangleTemplate_VH.get(NSImageNameRightFacingTriangleTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameRightFacingTriangleTemplate_VH.set(NSImageNameRightFacingTriangleTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameLeftFacingTriangleTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameLeftFacingTriangleTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameLeftFacingTriangleTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameLeftFacingTriangleTemplate").orElseThrow() }
private val NSImageNameLeftFacingTriangleTemplate_VH: VarHandle by lazy { NSImageNameLeftFacingTriangleTemplate_LAYOUT.varHandle() }

var NSImageNameLeftFacingTriangleTemplate: MemorySegment
    get() = NSImageNameLeftFacingTriangleTemplate_VH.get(NSImageNameLeftFacingTriangleTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameLeftFacingTriangleTemplate_VH.set(NSImageNameLeftFacingTriangleTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameDotMac typedef const NSImageName = (Void)*
 */
private val NSImageNameDotMac_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameDotMac_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameDotMac").orElseThrow() }
private val NSImageNameDotMac_VH: VarHandle by lazy { NSImageNameDotMac_LAYOUT.varHandle() }

var NSImageNameDotMac: MemorySegment
    get() = NSImageNameDotMac_VH.get(NSImageNameDotMac_SEGMENT) as MemorySegment
    set(value) = NSImageNameDotMac_VH.set(NSImageNameDotMac_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameMobileMe typedef const NSImageName = (Void)*
 */
private val NSImageNameMobileMe_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameMobileMe_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameMobileMe").orElseThrow() }
private val NSImageNameMobileMe_VH: VarHandle by lazy { NSImageNameMobileMe_LAYOUT.varHandle() }

var NSImageNameMobileMe: MemorySegment
    get() = NSImageNameMobileMe_VH.get(NSImageNameMobileMe_SEGMENT) as MemorySegment
    set(value) = NSImageNameMobileMe_VH.set(NSImageNameMobileMe_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameMultipleDocuments typedef const NSImageName = (Void)*
 */
private val NSImageNameMultipleDocuments_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameMultipleDocuments_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameMultipleDocuments").orElseThrow() }
private val NSImageNameMultipleDocuments_VH: VarHandle by lazy { NSImageNameMultipleDocuments_LAYOUT.varHandle() }

var NSImageNameMultipleDocuments: MemorySegment
    get() = NSImageNameMultipleDocuments_VH.get(NSImageNameMultipleDocuments_SEGMENT) as MemorySegment
    set(value) = NSImageNameMultipleDocuments_VH.set(NSImageNameMultipleDocuments_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameUserAccounts typedef const NSImageName = (Void)*
 */
private val NSImageNameUserAccounts_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameUserAccounts_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameUserAccounts").orElseThrow() }
private val NSImageNameUserAccounts_VH: VarHandle by lazy { NSImageNameUserAccounts_LAYOUT.varHandle() }

var NSImageNameUserAccounts: MemorySegment
    get() = NSImageNameUserAccounts_VH.get(NSImageNameUserAccounts_SEGMENT) as MemorySegment
    set(value) = NSImageNameUserAccounts_VH.set(NSImageNameUserAccounts_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNamePreferencesGeneral typedef const NSImageName = (Void)*
 */
private val NSImageNamePreferencesGeneral_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNamePreferencesGeneral_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNamePreferencesGeneral").orElseThrow() }
private val NSImageNamePreferencesGeneral_VH: VarHandle by lazy { NSImageNamePreferencesGeneral_LAYOUT.varHandle() }

var NSImageNamePreferencesGeneral: MemorySegment
    get() = NSImageNamePreferencesGeneral_VH.get(NSImageNamePreferencesGeneral_SEGMENT) as MemorySegment
    set(value) = NSImageNamePreferencesGeneral_VH.set(NSImageNamePreferencesGeneral_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameAdvanced typedef const NSImageName = (Void)*
 */
private val NSImageNameAdvanced_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameAdvanced_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameAdvanced").orElseThrow() }
private val NSImageNameAdvanced_VH: VarHandle by lazy { NSImageNameAdvanced_LAYOUT.varHandle() }

var NSImageNameAdvanced: MemorySegment
    get() = NSImageNameAdvanced_VH.get(NSImageNameAdvanced_SEGMENT) as MemorySegment
    set(value) = NSImageNameAdvanced_VH.set(NSImageNameAdvanced_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameInfo typedef const NSImageName = (Void)*
 */
private val NSImageNameInfo_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameInfo_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameInfo").orElseThrow() }
private val NSImageNameInfo_VH: VarHandle by lazy { NSImageNameInfo_LAYOUT.varHandle() }

var NSImageNameInfo: MemorySegment
    get() = NSImageNameInfo_VH.get(NSImageNameInfo_SEGMENT) as MemorySegment
    set(value) = NSImageNameInfo_VH.set(NSImageNameInfo_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameFontPanel typedef const NSImageName = (Void)*
 */
private val NSImageNameFontPanel_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameFontPanel_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameFontPanel").orElseThrow() }
private val NSImageNameFontPanel_VH: VarHandle by lazy { NSImageNameFontPanel_LAYOUT.varHandle() }

var NSImageNameFontPanel: MemorySegment
    get() = NSImageNameFontPanel_VH.get(NSImageNameFontPanel_SEGMENT) as MemorySegment
    set(value) = NSImageNameFontPanel_VH.set(NSImageNameFontPanel_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameColorPanel typedef const NSImageName = (Void)*
 */
private val NSImageNameColorPanel_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameColorPanel_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameColorPanel").orElseThrow() }
private val NSImageNameColorPanel_VH: VarHandle by lazy { NSImageNameColorPanel_LAYOUT.varHandle() }

var NSImageNameColorPanel: MemorySegment
    get() = NSImageNameColorPanel_VH.get(NSImageNameColorPanel_SEGMENT) as MemorySegment
    set(value) = NSImageNameColorPanel_VH.set(NSImageNameColorPanel_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameUser typedef const NSImageName = (Void)*
 */
private val NSImageNameUser_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameUser_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameUser").orElseThrow() }
private val NSImageNameUser_VH: VarHandle by lazy { NSImageNameUser_LAYOUT.varHandle() }

var NSImageNameUser: MemorySegment
    get() = NSImageNameUser_VH.get(NSImageNameUser_SEGMENT) as MemorySegment
    set(value) = NSImageNameUser_VH.set(NSImageNameUser_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameUserGroup typedef const NSImageName = (Void)*
 */
private val NSImageNameUserGroup_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameUserGroup_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameUserGroup").orElseThrow() }
private val NSImageNameUserGroup_VH: VarHandle by lazy { NSImageNameUserGroup_LAYOUT.varHandle() }

var NSImageNameUserGroup: MemorySegment
    get() = NSImageNameUserGroup_VH.get(NSImageNameUserGroup_SEGMENT) as MemorySegment
    set(value) = NSImageNameUserGroup_VH.set(NSImageNameUserGroup_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameEveryone typedef const NSImageName = (Void)*
 */
private val NSImageNameEveryone_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameEveryone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameEveryone").orElseThrow() }
private val NSImageNameEveryone_VH: VarHandle by lazy { NSImageNameEveryone_LAYOUT.varHandle() }

var NSImageNameEveryone: MemorySegment
    get() = NSImageNameEveryone_VH.get(NSImageNameEveryone_SEGMENT) as MemorySegment
    set(value) = NSImageNameEveryone_VH.set(NSImageNameEveryone_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameUserGuest typedef const NSImageName = (Void)*
 */
private val NSImageNameUserGuest_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameUserGuest_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameUserGuest").orElseThrow() }
private val NSImageNameUserGuest_VH: VarHandle by lazy { NSImageNameUserGuest_LAYOUT.varHandle() }

var NSImageNameUserGuest: MemorySegment
    get() = NSImageNameUserGuest_VH.get(NSImageNameUserGuest_SEGMENT) as MemorySegment
    set(value) = NSImageNameUserGuest_VH.set(NSImageNameUserGuest_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameMenuOnStateTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameMenuOnStateTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameMenuOnStateTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameMenuOnStateTemplate").orElseThrow() }
private val NSImageNameMenuOnStateTemplate_VH: VarHandle by lazy { NSImageNameMenuOnStateTemplate_LAYOUT.varHandle() }

var NSImageNameMenuOnStateTemplate: MemorySegment
    get() = NSImageNameMenuOnStateTemplate_VH.get(NSImageNameMenuOnStateTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameMenuOnStateTemplate_VH.set(NSImageNameMenuOnStateTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameMenuMixedStateTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameMenuMixedStateTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameMenuMixedStateTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameMenuMixedStateTemplate").orElseThrow() }
private val NSImageNameMenuMixedStateTemplate_VH: VarHandle by lazy { NSImageNameMenuMixedStateTemplate_LAYOUT.varHandle() }

var NSImageNameMenuMixedStateTemplate: MemorySegment
    get() = NSImageNameMenuMixedStateTemplate_VH.get(NSImageNameMenuMixedStateTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameMenuMixedStateTemplate_VH.set(NSImageNameMenuMixedStateTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameApplicationIcon typedef const NSImageName = (Void)*
 */
private val NSImageNameApplicationIcon_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameApplicationIcon_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameApplicationIcon").orElseThrow() }
private val NSImageNameApplicationIcon_VH: VarHandle by lazy { NSImageNameApplicationIcon_LAYOUT.varHandle() }

var NSImageNameApplicationIcon: MemorySegment
    get() = NSImageNameApplicationIcon_VH.get(NSImageNameApplicationIcon_SEGMENT) as MemorySegment
    set(value) = NSImageNameApplicationIcon_VH.set(NSImageNameApplicationIcon_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAddDetailTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAddDetailTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAddDetailTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAddDetailTemplate").orElseThrow() }
private val NSImageNameTouchBarAddDetailTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAddDetailTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAddDetailTemplate: MemorySegment
    get() = NSImageNameTouchBarAddDetailTemplate_VH.get(NSImageNameTouchBarAddDetailTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAddDetailTemplate_VH.set(NSImageNameTouchBarAddDetailTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAddTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAddTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAddTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAddTemplate").orElseThrow() }
private val NSImageNameTouchBarAddTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAddTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAddTemplate: MemorySegment
    get() = NSImageNameTouchBarAddTemplate_VH.get(NSImageNameTouchBarAddTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAddTemplate_VH.set(NSImageNameTouchBarAddTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAlarmTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAlarmTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAlarmTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAlarmTemplate").orElseThrow() }
private val NSImageNameTouchBarAlarmTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAlarmTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAlarmTemplate: MemorySegment
    get() = NSImageNameTouchBarAlarmTemplate_VH.get(NSImageNameTouchBarAlarmTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAlarmTemplate_VH.set(NSImageNameTouchBarAlarmTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAudioInputMuteTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAudioInputMuteTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAudioInputMuteTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAudioInputMuteTemplate").orElseThrow() }
private val NSImageNameTouchBarAudioInputMuteTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAudioInputMuteTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAudioInputMuteTemplate: MemorySegment
    get() = NSImageNameTouchBarAudioInputMuteTemplate_VH.get(NSImageNameTouchBarAudioInputMuteTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAudioInputMuteTemplate_VH.set(NSImageNameTouchBarAudioInputMuteTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAudioInputTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAudioInputTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAudioInputTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAudioInputTemplate").orElseThrow() }
private val NSImageNameTouchBarAudioInputTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAudioInputTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAudioInputTemplate: MemorySegment
    get() = NSImageNameTouchBarAudioInputTemplate_VH.get(NSImageNameTouchBarAudioInputTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAudioInputTemplate_VH.set(NSImageNameTouchBarAudioInputTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAudioOutputMuteTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAudioOutputMuteTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAudioOutputMuteTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAudioOutputMuteTemplate").orElseThrow() }
private val NSImageNameTouchBarAudioOutputMuteTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAudioOutputMuteTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAudioOutputMuteTemplate: MemorySegment
    get() = NSImageNameTouchBarAudioOutputMuteTemplate_VH.get(NSImageNameTouchBarAudioOutputMuteTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAudioOutputMuteTemplate_VH.set(NSImageNameTouchBarAudioOutputMuteTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAudioOutputVolumeHighTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAudioOutputVolumeHighTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAudioOutputVolumeHighTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAudioOutputVolumeHighTemplate").orElseThrow() }
private val NSImageNameTouchBarAudioOutputVolumeHighTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAudioOutputVolumeHighTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAudioOutputVolumeHighTemplate: MemorySegment
    get() = NSImageNameTouchBarAudioOutputVolumeHighTemplate_VH.get(NSImageNameTouchBarAudioOutputVolumeHighTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAudioOutputVolumeHighTemplate_VH.set(NSImageNameTouchBarAudioOutputVolumeHighTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAudioOutputVolumeLowTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAudioOutputVolumeLowTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAudioOutputVolumeLowTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAudioOutputVolumeLowTemplate").orElseThrow() }
private val NSImageNameTouchBarAudioOutputVolumeLowTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAudioOutputVolumeLowTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAudioOutputVolumeLowTemplate: MemorySegment
    get() = NSImageNameTouchBarAudioOutputVolumeLowTemplate_VH.get(NSImageNameTouchBarAudioOutputVolumeLowTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAudioOutputVolumeLowTemplate_VH.set(NSImageNameTouchBarAudioOutputVolumeLowTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAudioOutputVolumeMediumTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAudioOutputVolumeMediumTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAudioOutputVolumeMediumTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAudioOutputVolumeMediumTemplate").orElseThrow() }
private val NSImageNameTouchBarAudioOutputVolumeMediumTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAudioOutputVolumeMediumTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAudioOutputVolumeMediumTemplate: MemorySegment
    get() = NSImageNameTouchBarAudioOutputVolumeMediumTemplate_VH.get(NSImageNameTouchBarAudioOutputVolumeMediumTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAudioOutputVolumeMediumTemplate_VH.set(NSImageNameTouchBarAudioOutputVolumeMediumTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarAudioOutputVolumeOffTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarAudioOutputVolumeOffTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarAudioOutputVolumeOffTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarAudioOutputVolumeOffTemplate").orElseThrow() }
private val NSImageNameTouchBarAudioOutputVolumeOffTemplate_VH: VarHandle by lazy { NSImageNameTouchBarAudioOutputVolumeOffTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarAudioOutputVolumeOffTemplate: MemorySegment
    get() = NSImageNameTouchBarAudioOutputVolumeOffTemplate_VH.get(NSImageNameTouchBarAudioOutputVolumeOffTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarAudioOutputVolumeOffTemplate_VH.set(NSImageNameTouchBarAudioOutputVolumeOffTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarBookmarksTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarBookmarksTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarBookmarksTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarBookmarksTemplate").orElseThrow() }
private val NSImageNameTouchBarBookmarksTemplate_VH: VarHandle by lazy { NSImageNameTouchBarBookmarksTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarBookmarksTemplate: MemorySegment
    get() = NSImageNameTouchBarBookmarksTemplate_VH.get(NSImageNameTouchBarBookmarksTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarBookmarksTemplate_VH.set(NSImageNameTouchBarBookmarksTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarColorPickerFill typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarColorPickerFill_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarColorPickerFill_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarColorPickerFill").orElseThrow() }
private val NSImageNameTouchBarColorPickerFill_VH: VarHandle by lazy { NSImageNameTouchBarColorPickerFill_LAYOUT.varHandle() }

var NSImageNameTouchBarColorPickerFill: MemorySegment
    get() = NSImageNameTouchBarColorPickerFill_VH.get(NSImageNameTouchBarColorPickerFill_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarColorPickerFill_VH.set(NSImageNameTouchBarColorPickerFill_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarColorPickerFont typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarColorPickerFont_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarColorPickerFont_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarColorPickerFont").orElseThrow() }
private val NSImageNameTouchBarColorPickerFont_VH: VarHandle by lazy { NSImageNameTouchBarColorPickerFont_LAYOUT.varHandle() }

var NSImageNameTouchBarColorPickerFont: MemorySegment
    get() = NSImageNameTouchBarColorPickerFont_VH.get(NSImageNameTouchBarColorPickerFont_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarColorPickerFont_VH.set(NSImageNameTouchBarColorPickerFont_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarColorPickerStroke typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarColorPickerStroke_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarColorPickerStroke_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarColorPickerStroke").orElseThrow() }
private val NSImageNameTouchBarColorPickerStroke_VH: VarHandle by lazy { NSImageNameTouchBarColorPickerStroke_LAYOUT.varHandle() }

var NSImageNameTouchBarColorPickerStroke: MemorySegment
    get() = NSImageNameTouchBarColorPickerStroke_VH.get(NSImageNameTouchBarColorPickerStroke_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarColorPickerStroke_VH.set(NSImageNameTouchBarColorPickerStroke_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarCommunicationAudioTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarCommunicationAudioTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarCommunicationAudioTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarCommunicationAudioTemplate").orElseThrow() }
private val NSImageNameTouchBarCommunicationAudioTemplate_VH: VarHandle by lazy { NSImageNameTouchBarCommunicationAudioTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarCommunicationAudioTemplate: MemorySegment
    get() = NSImageNameTouchBarCommunicationAudioTemplate_VH.get(NSImageNameTouchBarCommunicationAudioTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarCommunicationAudioTemplate_VH.set(NSImageNameTouchBarCommunicationAudioTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarCommunicationVideoTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarCommunicationVideoTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarCommunicationVideoTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarCommunicationVideoTemplate").orElseThrow() }
private val NSImageNameTouchBarCommunicationVideoTemplate_VH: VarHandle by lazy { NSImageNameTouchBarCommunicationVideoTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarCommunicationVideoTemplate: MemorySegment
    get() = NSImageNameTouchBarCommunicationVideoTemplate_VH.get(NSImageNameTouchBarCommunicationVideoTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarCommunicationVideoTemplate_VH.set(NSImageNameTouchBarCommunicationVideoTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarComposeTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarComposeTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarComposeTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarComposeTemplate").orElseThrow() }
private val NSImageNameTouchBarComposeTemplate_VH: VarHandle by lazy { NSImageNameTouchBarComposeTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarComposeTemplate: MemorySegment
    get() = NSImageNameTouchBarComposeTemplate_VH.get(NSImageNameTouchBarComposeTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarComposeTemplate_VH.set(NSImageNameTouchBarComposeTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarDeleteTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarDeleteTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarDeleteTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarDeleteTemplate").orElseThrow() }
private val NSImageNameTouchBarDeleteTemplate_VH: VarHandle by lazy { NSImageNameTouchBarDeleteTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarDeleteTemplate: MemorySegment
    get() = NSImageNameTouchBarDeleteTemplate_VH.get(NSImageNameTouchBarDeleteTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarDeleteTemplate_VH.set(NSImageNameTouchBarDeleteTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarDownloadTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarDownloadTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarDownloadTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarDownloadTemplate").orElseThrow() }
private val NSImageNameTouchBarDownloadTemplate_VH: VarHandle by lazy { NSImageNameTouchBarDownloadTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarDownloadTemplate: MemorySegment
    get() = NSImageNameTouchBarDownloadTemplate_VH.get(NSImageNameTouchBarDownloadTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarDownloadTemplate_VH.set(NSImageNameTouchBarDownloadTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarEnterFullScreenTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarEnterFullScreenTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarEnterFullScreenTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarEnterFullScreenTemplate").orElseThrow() }
private val NSImageNameTouchBarEnterFullScreenTemplate_VH: VarHandle by lazy { NSImageNameTouchBarEnterFullScreenTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarEnterFullScreenTemplate: MemorySegment
    get() = NSImageNameTouchBarEnterFullScreenTemplate_VH.get(NSImageNameTouchBarEnterFullScreenTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarEnterFullScreenTemplate_VH.set(NSImageNameTouchBarEnterFullScreenTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarExitFullScreenTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarExitFullScreenTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarExitFullScreenTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarExitFullScreenTemplate").orElseThrow() }
private val NSImageNameTouchBarExitFullScreenTemplate_VH: VarHandle by lazy { NSImageNameTouchBarExitFullScreenTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarExitFullScreenTemplate: MemorySegment
    get() = NSImageNameTouchBarExitFullScreenTemplate_VH.get(NSImageNameTouchBarExitFullScreenTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarExitFullScreenTemplate_VH.set(NSImageNameTouchBarExitFullScreenTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarFastForwardTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarFastForwardTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarFastForwardTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarFastForwardTemplate").orElseThrow() }
private val NSImageNameTouchBarFastForwardTemplate_VH: VarHandle by lazy { NSImageNameTouchBarFastForwardTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarFastForwardTemplate: MemorySegment
    get() = NSImageNameTouchBarFastForwardTemplate_VH.get(NSImageNameTouchBarFastForwardTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarFastForwardTemplate_VH.set(NSImageNameTouchBarFastForwardTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarFolderCopyToTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarFolderCopyToTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarFolderCopyToTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarFolderCopyToTemplate").orElseThrow() }
private val NSImageNameTouchBarFolderCopyToTemplate_VH: VarHandle by lazy { NSImageNameTouchBarFolderCopyToTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarFolderCopyToTemplate: MemorySegment
    get() = NSImageNameTouchBarFolderCopyToTemplate_VH.get(NSImageNameTouchBarFolderCopyToTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarFolderCopyToTemplate_VH.set(NSImageNameTouchBarFolderCopyToTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarFolderMoveToTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarFolderMoveToTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarFolderMoveToTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarFolderMoveToTemplate").orElseThrow() }
private val NSImageNameTouchBarFolderMoveToTemplate_VH: VarHandle by lazy { NSImageNameTouchBarFolderMoveToTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarFolderMoveToTemplate: MemorySegment
    get() = NSImageNameTouchBarFolderMoveToTemplate_VH.get(NSImageNameTouchBarFolderMoveToTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarFolderMoveToTemplate_VH.set(NSImageNameTouchBarFolderMoveToTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarFolderTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarFolderTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarFolderTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarFolderTemplate").orElseThrow() }
private val NSImageNameTouchBarFolderTemplate_VH: VarHandle by lazy { NSImageNameTouchBarFolderTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarFolderTemplate: MemorySegment
    get() = NSImageNameTouchBarFolderTemplate_VH.get(NSImageNameTouchBarFolderTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarFolderTemplate_VH.set(NSImageNameTouchBarFolderTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarGetInfoTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarGetInfoTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarGetInfoTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarGetInfoTemplate").orElseThrow() }
private val NSImageNameTouchBarGetInfoTemplate_VH: VarHandle by lazy { NSImageNameTouchBarGetInfoTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarGetInfoTemplate: MemorySegment
    get() = NSImageNameTouchBarGetInfoTemplate_VH.get(NSImageNameTouchBarGetInfoTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarGetInfoTemplate_VH.set(NSImageNameTouchBarGetInfoTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarGoBackTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarGoBackTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarGoBackTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarGoBackTemplate").orElseThrow() }
private val NSImageNameTouchBarGoBackTemplate_VH: VarHandle by lazy { NSImageNameTouchBarGoBackTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarGoBackTemplate: MemorySegment
    get() = NSImageNameTouchBarGoBackTemplate_VH.get(NSImageNameTouchBarGoBackTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarGoBackTemplate_VH.set(NSImageNameTouchBarGoBackTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarGoDownTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarGoDownTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarGoDownTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarGoDownTemplate").orElseThrow() }
private val NSImageNameTouchBarGoDownTemplate_VH: VarHandle by lazy { NSImageNameTouchBarGoDownTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarGoDownTemplate: MemorySegment
    get() = NSImageNameTouchBarGoDownTemplate_VH.get(NSImageNameTouchBarGoDownTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarGoDownTemplate_VH.set(NSImageNameTouchBarGoDownTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarGoForwardTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarGoForwardTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarGoForwardTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarGoForwardTemplate").orElseThrow() }
private val NSImageNameTouchBarGoForwardTemplate_VH: VarHandle by lazy { NSImageNameTouchBarGoForwardTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarGoForwardTemplate: MemorySegment
    get() = NSImageNameTouchBarGoForwardTemplate_VH.get(NSImageNameTouchBarGoForwardTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarGoForwardTemplate_VH.set(NSImageNameTouchBarGoForwardTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarGoUpTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarGoUpTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarGoUpTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarGoUpTemplate").orElseThrow() }
private val NSImageNameTouchBarGoUpTemplate_VH: VarHandle by lazy { NSImageNameTouchBarGoUpTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarGoUpTemplate: MemorySegment
    get() = NSImageNameTouchBarGoUpTemplate_VH.get(NSImageNameTouchBarGoUpTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarGoUpTemplate_VH.set(NSImageNameTouchBarGoUpTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarHistoryTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarHistoryTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarHistoryTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarHistoryTemplate").orElseThrow() }
private val NSImageNameTouchBarHistoryTemplate_VH: VarHandle by lazy { NSImageNameTouchBarHistoryTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarHistoryTemplate: MemorySegment
    get() = NSImageNameTouchBarHistoryTemplate_VH.get(NSImageNameTouchBarHistoryTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarHistoryTemplate_VH.set(NSImageNameTouchBarHistoryTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarIconViewTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarIconViewTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarIconViewTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarIconViewTemplate").orElseThrow() }
private val NSImageNameTouchBarIconViewTemplate_VH: VarHandle by lazy { NSImageNameTouchBarIconViewTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarIconViewTemplate: MemorySegment
    get() = NSImageNameTouchBarIconViewTemplate_VH.get(NSImageNameTouchBarIconViewTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarIconViewTemplate_VH.set(NSImageNameTouchBarIconViewTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarListViewTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarListViewTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarListViewTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarListViewTemplate").orElseThrow() }
private val NSImageNameTouchBarListViewTemplate_VH: VarHandle by lazy { NSImageNameTouchBarListViewTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarListViewTemplate: MemorySegment
    get() = NSImageNameTouchBarListViewTemplate_VH.get(NSImageNameTouchBarListViewTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarListViewTemplate_VH.set(NSImageNameTouchBarListViewTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarMailTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarMailTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarMailTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarMailTemplate").orElseThrow() }
private val NSImageNameTouchBarMailTemplate_VH: VarHandle by lazy { NSImageNameTouchBarMailTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarMailTemplate: MemorySegment
    get() = NSImageNameTouchBarMailTemplate_VH.get(NSImageNameTouchBarMailTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarMailTemplate_VH.set(NSImageNameTouchBarMailTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarNewFolderTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarNewFolderTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarNewFolderTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarNewFolderTemplate").orElseThrow() }
private val NSImageNameTouchBarNewFolderTemplate_VH: VarHandle by lazy { NSImageNameTouchBarNewFolderTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarNewFolderTemplate: MemorySegment
    get() = NSImageNameTouchBarNewFolderTemplate_VH.get(NSImageNameTouchBarNewFolderTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarNewFolderTemplate_VH.set(NSImageNameTouchBarNewFolderTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarNewMessageTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarNewMessageTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarNewMessageTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarNewMessageTemplate").orElseThrow() }
private val NSImageNameTouchBarNewMessageTemplate_VH: VarHandle by lazy { NSImageNameTouchBarNewMessageTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarNewMessageTemplate: MemorySegment
    get() = NSImageNameTouchBarNewMessageTemplate_VH.get(NSImageNameTouchBarNewMessageTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarNewMessageTemplate_VH.set(NSImageNameTouchBarNewMessageTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarOpenInBrowserTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarOpenInBrowserTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarOpenInBrowserTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarOpenInBrowserTemplate").orElseThrow() }
private val NSImageNameTouchBarOpenInBrowserTemplate_VH: VarHandle by lazy { NSImageNameTouchBarOpenInBrowserTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarOpenInBrowserTemplate: MemorySegment
    get() = NSImageNameTouchBarOpenInBrowserTemplate_VH.get(NSImageNameTouchBarOpenInBrowserTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarOpenInBrowserTemplate_VH.set(NSImageNameTouchBarOpenInBrowserTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarPauseTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarPauseTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarPauseTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarPauseTemplate").orElseThrow() }
private val NSImageNameTouchBarPauseTemplate_VH: VarHandle by lazy { NSImageNameTouchBarPauseTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarPauseTemplate: MemorySegment
    get() = NSImageNameTouchBarPauseTemplate_VH.get(NSImageNameTouchBarPauseTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarPauseTemplate_VH.set(NSImageNameTouchBarPauseTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarPlayPauseTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarPlayPauseTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarPlayPauseTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarPlayPauseTemplate").orElseThrow() }
private val NSImageNameTouchBarPlayPauseTemplate_VH: VarHandle by lazy { NSImageNameTouchBarPlayPauseTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarPlayPauseTemplate: MemorySegment
    get() = NSImageNameTouchBarPlayPauseTemplate_VH.get(NSImageNameTouchBarPlayPauseTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarPlayPauseTemplate_VH.set(NSImageNameTouchBarPlayPauseTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarPlayTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarPlayTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarPlayTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarPlayTemplate").orElseThrow() }
private val NSImageNameTouchBarPlayTemplate_VH: VarHandle by lazy { NSImageNameTouchBarPlayTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarPlayTemplate: MemorySegment
    get() = NSImageNameTouchBarPlayTemplate_VH.get(NSImageNameTouchBarPlayTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarPlayTemplate_VH.set(NSImageNameTouchBarPlayTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarQuickLookTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarQuickLookTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarQuickLookTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarQuickLookTemplate").orElseThrow() }
private val NSImageNameTouchBarQuickLookTemplate_VH: VarHandle by lazy { NSImageNameTouchBarQuickLookTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarQuickLookTemplate: MemorySegment
    get() = NSImageNameTouchBarQuickLookTemplate_VH.get(NSImageNameTouchBarQuickLookTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarQuickLookTemplate_VH.set(NSImageNameTouchBarQuickLookTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarRecordStartTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarRecordStartTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarRecordStartTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarRecordStartTemplate").orElseThrow() }
private val NSImageNameTouchBarRecordStartTemplate_VH: VarHandle by lazy { NSImageNameTouchBarRecordStartTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarRecordStartTemplate: MemorySegment
    get() = NSImageNameTouchBarRecordStartTemplate_VH.get(NSImageNameTouchBarRecordStartTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarRecordStartTemplate_VH.set(NSImageNameTouchBarRecordStartTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarRecordStopTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarRecordStopTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarRecordStopTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarRecordStopTemplate").orElseThrow() }
private val NSImageNameTouchBarRecordStopTemplate_VH: VarHandle by lazy { NSImageNameTouchBarRecordStopTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarRecordStopTemplate: MemorySegment
    get() = NSImageNameTouchBarRecordStopTemplate_VH.get(NSImageNameTouchBarRecordStopTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarRecordStopTemplate_VH.set(NSImageNameTouchBarRecordStopTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarRefreshTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarRefreshTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarRefreshTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarRefreshTemplate").orElseThrow() }
private val NSImageNameTouchBarRefreshTemplate_VH: VarHandle by lazy { NSImageNameTouchBarRefreshTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarRefreshTemplate: MemorySegment
    get() = NSImageNameTouchBarRefreshTemplate_VH.get(NSImageNameTouchBarRefreshTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarRefreshTemplate_VH.set(NSImageNameTouchBarRefreshTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarRemoveTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarRemoveTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarRemoveTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarRemoveTemplate").orElseThrow() }
private val NSImageNameTouchBarRemoveTemplate_VH: VarHandle by lazy { NSImageNameTouchBarRemoveTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarRemoveTemplate: MemorySegment
    get() = NSImageNameTouchBarRemoveTemplate_VH.get(NSImageNameTouchBarRemoveTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarRemoveTemplate_VH.set(NSImageNameTouchBarRemoveTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarRewindTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarRewindTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarRewindTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarRewindTemplate").orElseThrow() }
private val NSImageNameTouchBarRewindTemplate_VH: VarHandle by lazy { NSImageNameTouchBarRewindTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarRewindTemplate: MemorySegment
    get() = NSImageNameTouchBarRewindTemplate_VH.get(NSImageNameTouchBarRewindTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarRewindTemplate_VH.set(NSImageNameTouchBarRewindTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarRotateLeftTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarRotateLeftTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarRotateLeftTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarRotateLeftTemplate").orElseThrow() }
private val NSImageNameTouchBarRotateLeftTemplate_VH: VarHandle by lazy { NSImageNameTouchBarRotateLeftTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarRotateLeftTemplate: MemorySegment
    get() = NSImageNameTouchBarRotateLeftTemplate_VH.get(NSImageNameTouchBarRotateLeftTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarRotateLeftTemplate_VH.set(NSImageNameTouchBarRotateLeftTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarRotateRightTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarRotateRightTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarRotateRightTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarRotateRightTemplate").orElseThrow() }
private val NSImageNameTouchBarRotateRightTemplate_VH: VarHandle by lazy { NSImageNameTouchBarRotateRightTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarRotateRightTemplate: MemorySegment
    get() = NSImageNameTouchBarRotateRightTemplate_VH.get(NSImageNameTouchBarRotateRightTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarRotateRightTemplate_VH.set(NSImageNameTouchBarRotateRightTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSearchTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSearchTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSearchTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSearchTemplate").orElseThrow() }
private val NSImageNameTouchBarSearchTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSearchTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSearchTemplate: MemorySegment
    get() = NSImageNameTouchBarSearchTemplate_VH.get(NSImageNameTouchBarSearchTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSearchTemplate_VH.set(NSImageNameTouchBarSearchTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarShareTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarShareTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarShareTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarShareTemplate").orElseThrow() }
private val NSImageNameTouchBarShareTemplate_VH: VarHandle by lazy { NSImageNameTouchBarShareTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarShareTemplate: MemorySegment
    get() = NSImageNameTouchBarShareTemplate_VH.get(NSImageNameTouchBarShareTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarShareTemplate_VH.set(NSImageNameTouchBarShareTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSidebarTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSidebarTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSidebarTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSidebarTemplate").orElseThrow() }
private val NSImageNameTouchBarSidebarTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSidebarTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSidebarTemplate: MemorySegment
    get() = NSImageNameTouchBarSidebarTemplate_VH.get(NSImageNameTouchBarSidebarTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSidebarTemplate_VH.set(NSImageNameTouchBarSidebarTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSkipAhead15SecondsTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSkipAhead15SecondsTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSkipAhead15SecondsTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSkipAhead15SecondsTemplate").orElseThrow() }
private val NSImageNameTouchBarSkipAhead15SecondsTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSkipAhead15SecondsTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSkipAhead15SecondsTemplate: MemorySegment
    get() = NSImageNameTouchBarSkipAhead15SecondsTemplate_VH.get(NSImageNameTouchBarSkipAhead15SecondsTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSkipAhead15SecondsTemplate_VH.set(NSImageNameTouchBarSkipAhead15SecondsTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSkipAhead30SecondsTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSkipAhead30SecondsTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSkipAhead30SecondsTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSkipAhead30SecondsTemplate").orElseThrow() }
private val NSImageNameTouchBarSkipAhead30SecondsTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSkipAhead30SecondsTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSkipAhead30SecondsTemplate: MemorySegment
    get() = NSImageNameTouchBarSkipAhead30SecondsTemplate_VH.get(NSImageNameTouchBarSkipAhead30SecondsTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSkipAhead30SecondsTemplate_VH.set(NSImageNameTouchBarSkipAhead30SecondsTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSkipAheadTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSkipAheadTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSkipAheadTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSkipAheadTemplate").orElseThrow() }
private val NSImageNameTouchBarSkipAheadTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSkipAheadTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSkipAheadTemplate: MemorySegment
    get() = NSImageNameTouchBarSkipAheadTemplate_VH.get(NSImageNameTouchBarSkipAheadTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSkipAheadTemplate_VH.set(NSImageNameTouchBarSkipAheadTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSkipBack15SecondsTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSkipBack15SecondsTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSkipBack15SecondsTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSkipBack15SecondsTemplate").orElseThrow() }
private val NSImageNameTouchBarSkipBack15SecondsTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSkipBack15SecondsTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSkipBack15SecondsTemplate: MemorySegment
    get() = NSImageNameTouchBarSkipBack15SecondsTemplate_VH.get(NSImageNameTouchBarSkipBack15SecondsTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSkipBack15SecondsTemplate_VH.set(NSImageNameTouchBarSkipBack15SecondsTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSkipBack30SecondsTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSkipBack30SecondsTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSkipBack30SecondsTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSkipBack30SecondsTemplate").orElseThrow() }
private val NSImageNameTouchBarSkipBack30SecondsTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSkipBack30SecondsTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSkipBack30SecondsTemplate: MemorySegment
    get() = NSImageNameTouchBarSkipBack30SecondsTemplate_VH.get(NSImageNameTouchBarSkipBack30SecondsTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSkipBack30SecondsTemplate_VH.set(NSImageNameTouchBarSkipBack30SecondsTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSkipBackTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSkipBackTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSkipBackTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSkipBackTemplate").orElseThrow() }
private val NSImageNameTouchBarSkipBackTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSkipBackTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSkipBackTemplate: MemorySegment
    get() = NSImageNameTouchBarSkipBackTemplate_VH.get(NSImageNameTouchBarSkipBackTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSkipBackTemplate_VH.set(NSImageNameTouchBarSkipBackTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSkipToEndTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSkipToEndTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSkipToEndTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSkipToEndTemplate").orElseThrow() }
private val NSImageNameTouchBarSkipToEndTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSkipToEndTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSkipToEndTemplate: MemorySegment
    get() = NSImageNameTouchBarSkipToEndTemplate_VH.get(NSImageNameTouchBarSkipToEndTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSkipToEndTemplate_VH.set(NSImageNameTouchBarSkipToEndTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSkipToStartTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSkipToStartTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSkipToStartTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSkipToStartTemplate").orElseThrow() }
private val NSImageNameTouchBarSkipToStartTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSkipToStartTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSkipToStartTemplate: MemorySegment
    get() = NSImageNameTouchBarSkipToStartTemplate_VH.get(NSImageNameTouchBarSkipToStartTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSkipToStartTemplate_VH.set(NSImageNameTouchBarSkipToStartTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarSlideshowTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarSlideshowTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarSlideshowTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarSlideshowTemplate").orElseThrow() }
private val NSImageNameTouchBarSlideshowTemplate_VH: VarHandle by lazy { NSImageNameTouchBarSlideshowTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarSlideshowTemplate: MemorySegment
    get() = NSImageNameTouchBarSlideshowTemplate_VH.get(NSImageNameTouchBarSlideshowTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarSlideshowTemplate_VH.set(NSImageNameTouchBarSlideshowTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTagIconTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTagIconTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTagIconTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTagIconTemplate").orElseThrow() }
private val NSImageNameTouchBarTagIconTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTagIconTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTagIconTemplate: MemorySegment
    get() = NSImageNameTouchBarTagIconTemplate_VH.get(NSImageNameTouchBarTagIconTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTagIconTemplate_VH.set(NSImageNameTouchBarTagIconTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextBoldTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextBoldTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextBoldTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextBoldTemplate").orElseThrow() }
private val NSImageNameTouchBarTextBoldTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextBoldTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextBoldTemplate: MemorySegment
    get() = NSImageNameTouchBarTextBoldTemplate_VH.get(NSImageNameTouchBarTextBoldTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextBoldTemplate_VH.set(NSImageNameTouchBarTextBoldTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextBoxTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextBoxTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextBoxTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextBoxTemplate").orElseThrow() }
private val NSImageNameTouchBarTextBoxTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextBoxTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextBoxTemplate: MemorySegment
    get() = NSImageNameTouchBarTextBoxTemplate_VH.get(NSImageNameTouchBarTextBoxTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextBoxTemplate_VH.set(NSImageNameTouchBarTextBoxTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextCenterAlignTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextCenterAlignTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextCenterAlignTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextCenterAlignTemplate").orElseThrow() }
private val NSImageNameTouchBarTextCenterAlignTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextCenterAlignTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextCenterAlignTemplate: MemorySegment
    get() = NSImageNameTouchBarTextCenterAlignTemplate_VH.get(NSImageNameTouchBarTextCenterAlignTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextCenterAlignTemplate_VH.set(NSImageNameTouchBarTextCenterAlignTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextItalicTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextItalicTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextItalicTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextItalicTemplate").orElseThrow() }
private val NSImageNameTouchBarTextItalicTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextItalicTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextItalicTemplate: MemorySegment
    get() = NSImageNameTouchBarTextItalicTemplate_VH.get(NSImageNameTouchBarTextItalicTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextItalicTemplate_VH.set(NSImageNameTouchBarTextItalicTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextJustifiedAlignTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextJustifiedAlignTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextJustifiedAlignTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextJustifiedAlignTemplate").orElseThrow() }
private val NSImageNameTouchBarTextJustifiedAlignTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextJustifiedAlignTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextJustifiedAlignTemplate: MemorySegment
    get() = NSImageNameTouchBarTextJustifiedAlignTemplate_VH.get(NSImageNameTouchBarTextJustifiedAlignTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextJustifiedAlignTemplate_VH.set(NSImageNameTouchBarTextJustifiedAlignTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextLeftAlignTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextLeftAlignTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextLeftAlignTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextLeftAlignTemplate").orElseThrow() }
private val NSImageNameTouchBarTextLeftAlignTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextLeftAlignTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextLeftAlignTemplate: MemorySegment
    get() = NSImageNameTouchBarTextLeftAlignTemplate_VH.get(NSImageNameTouchBarTextLeftAlignTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextLeftAlignTemplate_VH.set(NSImageNameTouchBarTextLeftAlignTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextListTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextListTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextListTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextListTemplate").orElseThrow() }
private val NSImageNameTouchBarTextListTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextListTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextListTemplate: MemorySegment
    get() = NSImageNameTouchBarTextListTemplate_VH.get(NSImageNameTouchBarTextListTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextListTemplate_VH.set(NSImageNameTouchBarTextListTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextRightAlignTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextRightAlignTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextRightAlignTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextRightAlignTemplate").orElseThrow() }
private val NSImageNameTouchBarTextRightAlignTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextRightAlignTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextRightAlignTemplate: MemorySegment
    get() = NSImageNameTouchBarTextRightAlignTemplate_VH.get(NSImageNameTouchBarTextRightAlignTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextRightAlignTemplate_VH.set(NSImageNameTouchBarTextRightAlignTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextStrikethroughTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextStrikethroughTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextStrikethroughTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextStrikethroughTemplate").orElseThrow() }
private val NSImageNameTouchBarTextStrikethroughTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextStrikethroughTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextStrikethroughTemplate: MemorySegment
    get() = NSImageNameTouchBarTextStrikethroughTemplate_VH.get(NSImageNameTouchBarTextStrikethroughTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextStrikethroughTemplate_VH.set(NSImageNameTouchBarTextStrikethroughTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarTextUnderlineTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarTextUnderlineTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarTextUnderlineTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarTextUnderlineTemplate").orElseThrow() }
private val NSImageNameTouchBarTextUnderlineTemplate_VH: VarHandle by lazy { NSImageNameTouchBarTextUnderlineTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarTextUnderlineTemplate: MemorySegment
    get() = NSImageNameTouchBarTextUnderlineTemplate_VH.get(NSImageNameTouchBarTextUnderlineTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarTextUnderlineTemplate_VH.set(NSImageNameTouchBarTextUnderlineTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarUserAddTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarUserAddTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarUserAddTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarUserAddTemplate").orElseThrow() }
private val NSImageNameTouchBarUserAddTemplate_VH: VarHandle by lazy { NSImageNameTouchBarUserAddTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarUserAddTemplate: MemorySegment
    get() = NSImageNameTouchBarUserAddTemplate_VH.get(NSImageNameTouchBarUserAddTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarUserAddTemplate_VH.set(NSImageNameTouchBarUserAddTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarUserGroupTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarUserGroupTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarUserGroupTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarUserGroupTemplate").orElseThrow() }
private val NSImageNameTouchBarUserGroupTemplate_VH: VarHandle by lazy { NSImageNameTouchBarUserGroupTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarUserGroupTemplate: MemorySegment
    get() = NSImageNameTouchBarUserGroupTemplate_VH.get(NSImageNameTouchBarUserGroupTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarUserGroupTemplate_VH.set(NSImageNameTouchBarUserGroupTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarUserTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarUserTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarUserTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarUserTemplate").orElseThrow() }
private val NSImageNameTouchBarUserTemplate_VH: VarHandle by lazy { NSImageNameTouchBarUserTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarUserTemplate: MemorySegment
    get() = NSImageNameTouchBarUserTemplate_VH.get(NSImageNameTouchBarUserTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarUserTemplate_VH.set(NSImageNameTouchBarUserTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarVolumeDownTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarVolumeDownTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarVolumeDownTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarVolumeDownTemplate").orElseThrow() }
private val NSImageNameTouchBarVolumeDownTemplate_VH: VarHandle by lazy { NSImageNameTouchBarVolumeDownTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarVolumeDownTemplate: MemorySegment
    get() = NSImageNameTouchBarVolumeDownTemplate_VH.get(NSImageNameTouchBarVolumeDownTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarVolumeDownTemplate_VH.set(NSImageNameTouchBarVolumeDownTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarVolumeUpTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarVolumeUpTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarVolumeUpTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarVolumeUpTemplate").orElseThrow() }
private val NSImageNameTouchBarVolumeUpTemplate_VH: VarHandle by lazy { NSImageNameTouchBarVolumeUpTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarVolumeUpTemplate: MemorySegment
    get() = NSImageNameTouchBarVolumeUpTemplate_VH.get(NSImageNameTouchBarVolumeUpTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarVolumeUpTemplate_VH.set(NSImageNameTouchBarVolumeUpTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSImageNameTouchBarPlayheadTemplate typedef const NSImageName = (Void)*
 */
private val NSImageNameTouchBarPlayheadTemplate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSImageNameTouchBarPlayheadTemplate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSImageNameTouchBarPlayheadTemplate").orElseThrow() }
private val NSImageNameTouchBarPlayheadTemplate_VH: VarHandle by lazy { NSImageNameTouchBarPlayheadTemplate_LAYOUT.varHandle() }

var NSImageNameTouchBarPlayheadTemplate: MemorySegment
    get() = NSImageNameTouchBarPlayheadTemplate_VH.get(NSImageNameTouchBarPlayheadTemplate_SEGMENT) as MemorySegment
    set(value) = NSImageNameTouchBarPlayheadTemplate_VH.set(NSImageNameTouchBarPlayheadTemplate_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameComposeEmail typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameComposeEmail_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameComposeEmail_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameComposeEmail").orElseThrow() }
private val NSSharingServiceNameComposeEmail_VH: VarHandle by lazy { NSSharingServiceNameComposeEmail_LAYOUT.varHandle() }

var NSSharingServiceNameComposeEmail: MemorySegment
    get() = NSSharingServiceNameComposeEmail_VH.get(NSSharingServiceNameComposeEmail_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameComposeEmail_VH.set(NSSharingServiceNameComposeEmail_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameComposeMessage typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameComposeMessage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameComposeMessage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameComposeMessage").orElseThrow() }
private val NSSharingServiceNameComposeMessage_VH: VarHandle by lazy { NSSharingServiceNameComposeMessage_LAYOUT.varHandle() }

var NSSharingServiceNameComposeMessage: MemorySegment
    get() = NSSharingServiceNameComposeMessage_VH.get(NSSharingServiceNameComposeMessage_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameComposeMessage_VH.set(NSSharingServiceNameComposeMessage_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameSendViaAirDrop typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameSendViaAirDrop_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameSendViaAirDrop_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameSendViaAirDrop").orElseThrow() }
private val NSSharingServiceNameSendViaAirDrop_VH: VarHandle by lazy { NSSharingServiceNameSendViaAirDrop_LAYOUT.varHandle() }

var NSSharingServiceNameSendViaAirDrop: MemorySegment
    get() = NSSharingServiceNameSendViaAirDrop_VH.get(NSSharingServiceNameSendViaAirDrop_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameSendViaAirDrop_VH.set(NSSharingServiceNameSendViaAirDrop_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameAddToSafariReadingList typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameAddToSafariReadingList_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameAddToSafariReadingList_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameAddToSafariReadingList").orElseThrow() }
private val NSSharingServiceNameAddToSafariReadingList_VH: VarHandle by lazy { NSSharingServiceNameAddToSafariReadingList_LAYOUT.varHandle() }

var NSSharingServiceNameAddToSafariReadingList: MemorySegment
    get() = NSSharingServiceNameAddToSafariReadingList_VH.get(NSSharingServiceNameAddToSafariReadingList_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameAddToSafariReadingList_VH.set(NSSharingServiceNameAddToSafariReadingList_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameAddToIPhoto typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameAddToIPhoto_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameAddToIPhoto_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameAddToIPhoto").orElseThrow() }
private val NSSharingServiceNameAddToIPhoto_VH: VarHandle by lazy { NSSharingServiceNameAddToIPhoto_LAYOUT.varHandle() }

var NSSharingServiceNameAddToIPhoto: MemorySegment
    get() = NSSharingServiceNameAddToIPhoto_VH.get(NSSharingServiceNameAddToIPhoto_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameAddToIPhoto_VH.set(NSSharingServiceNameAddToIPhoto_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameAddToAperture typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameAddToAperture_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameAddToAperture_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameAddToAperture").orElseThrow() }
private val NSSharingServiceNameAddToAperture_VH: VarHandle by lazy { NSSharingServiceNameAddToAperture_LAYOUT.varHandle() }

var NSSharingServiceNameAddToAperture: MemorySegment
    get() = NSSharingServiceNameAddToAperture_VH.get(NSSharingServiceNameAddToAperture_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameAddToAperture_VH.set(NSSharingServiceNameAddToAperture_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameUseAsDesktopPicture typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameUseAsDesktopPicture_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameUseAsDesktopPicture_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameUseAsDesktopPicture").orElseThrow() }
private val NSSharingServiceNameUseAsDesktopPicture_VH: VarHandle by lazy { NSSharingServiceNameUseAsDesktopPicture_LAYOUT.varHandle() }

var NSSharingServiceNameUseAsDesktopPicture: MemorySegment
    get() = NSSharingServiceNameUseAsDesktopPicture_VH.get(NSSharingServiceNameUseAsDesktopPicture_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameUseAsDesktopPicture_VH.set(NSSharingServiceNameUseAsDesktopPicture_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNamePostOnFacebook typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNamePostOnFacebook_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNamePostOnFacebook_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNamePostOnFacebook").orElseThrow() }
private val NSSharingServiceNamePostOnFacebook_VH: VarHandle by lazy { NSSharingServiceNamePostOnFacebook_LAYOUT.varHandle() }

var NSSharingServiceNamePostOnFacebook: MemorySegment
    get() = NSSharingServiceNamePostOnFacebook_VH.get(NSSharingServiceNamePostOnFacebook_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNamePostOnFacebook_VH.set(NSSharingServiceNamePostOnFacebook_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNamePostOnTwitter typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNamePostOnTwitter_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNamePostOnTwitter_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNamePostOnTwitter").orElseThrow() }
private val NSSharingServiceNamePostOnTwitter_VH: VarHandle by lazy { NSSharingServiceNamePostOnTwitter_LAYOUT.varHandle() }

var NSSharingServiceNamePostOnTwitter: MemorySegment
    get() = NSSharingServiceNamePostOnTwitter_VH.get(NSSharingServiceNamePostOnTwitter_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNamePostOnTwitter_VH.set(NSSharingServiceNamePostOnTwitter_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNamePostOnSinaWeibo typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNamePostOnSinaWeibo_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNamePostOnSinaWeibo_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNamePostOnSinaWeibo").orElseThrow() }
private val NSSharingServiceNamePostOnSinaWeibo_VH: VarHandle by lazy { NSSharingServiceNamePostOnSinaWeibo_LAYOUT.varHandle() }

var NSSharingServiceNamePostOnSinaWeibo: MemorySegment
    get() = NSSharingServiceNamePostOnSinaWeibo_VH.get(NSSharingServiceNamePostOnSinaWeibo_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNamePostOnSinaWeibo_VH.set(NSSharingServiceNamePostOnSinaWeibo_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNamePostOnTencentWeibo typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNamePostOnTencentWeibo_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNamePostOnTencentWeibo_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNamePostOnTencentWeibo").orElseThrow() }
private val NSSharingServiceNamePostOnTencentWeibo_VH: VarHandle by lazy { NSSharingServiceNamePostOnTencentWeibo_LAYOUT.varHandle() }

var NSSharingServiceNamePostOnTencentWeibo: MemorySegment
    get() = NSSharingServiceNamePostOnTencentWeibo_VH.get(NSSharingServiceNamePostOnTencentWeibo_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNamePostOnTencentWeibo_VH.set(NSSharingServiceNamePostOnTencentWeibo_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNamePostOnLinkedIn typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNamePostOnLinkedIn_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNamePostOnLinkedIn_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNamePostOnLinkedIn").orElseThrow() }
private val NSSharingServiceNamePostOnLinkedIn_VH: VarHandle by lazy { NSSharingServiceNamePostOnLinkedIn_LAYOUT.varHandle() }

var NSSharingServiceNamePostOnLinkedIn: MemorySegment
    get() = NSSharingServiceNamePostOnLinkedIn_VH.get(NSSharingServiceNamePostOnLinkedIn_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNamePostOnLinkedIn_VH.set(NSSharingServiceNamePostOnLinkedIn_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameUseAsTwitterProfileImage typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameUseAsTwitterProfileImage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameUseAsTwitterProfileImage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameUseAsTwitterProfileImage").orElseThrow() }
private val NSSharingServiceNameUseAsTwitterProfileImage_VH: VarHandle by lazy { NSSharingServiceNameUseAsTwitterProfileImage_LAYOUT.varHandle() }

var NSSharingServiceNameUseAsTwitterProfileImage: MemorySegment
    get() = NSSharingServiceNameUseAsTwitterProfileImage_VH.get(NSSharingServiceNameUseAsTwitterProfileImage_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameUseAsTwitterProfileImage_VH.set(NSSharingServiceNameUseAsTwitterProfileImage_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameUseAsFacebookProfileImage typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameUseAsFacebookProfileImage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameUseAsFacebookProfileImage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameUseAsFacebookProfileImage").orElseThrow() }
private val NSSharingServiceNameUseAsFacebookProfileImage_VH: VarHandle by lazy { NSSharingServiceNameUseAsFacebookProfileImage_LAYOUT.varHandle() }

var NSSharingServiceNameUseAsFacebookProfileImage: MemorySegment
    get() = NSSharingServiceNameUseAsFacebookProfileImage_VH.get(NSSharingServiceNameUseAsFacebookProfileImage_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameUseAsFacebookProfileImage_VH.set(NSSharingServiceNameUseAsFacebookProfileImage_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameUseAsLinkedInProfileImage typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameUseAsLinkedInProfileImage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameUseAsLinkedInProfileImage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameUseAsLinkedInProfileImage").orElseThrow() }
private val NSSharingServiceNameUseAsLinkedInProfileImage_VH: VarHandle by lazy { NSSharingServiceNameUseAsLinkedInProfileImage_LAYOUT.varHandle() }

var NSSharingServiceNameUseAsLinkedInProfileImage: MemorySegment
    get() = NSSharingServiceNameUseAsLinkedInProfileImage_VH.get(NSSharingServiceNameUseAsLinkedInProfileImage_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameUseAsLinkedInProfileImage_VH.set(NSSharingServiceNameUseAsLinkedInProfileImage_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNamePostImageOnFlickr typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNamePostImageOnFlickr_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNamePostImageOnFlickr_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNamePostImageOnFlickr").orElseThrow() }
private val NSSharingServiceNamePostImageOnFlickr_VH: VarHandle by lazy { NSSharingServiceNamePostImageOnFlickr_LAYOUT.varHandle() }

var NSSharingServiceNamePostImageOnFlickr: MemorySegment
    get() = NSSharingServiceNamePostImageOnFlickr_VH.get(NSSharingServiceNamePostImageOnFlickr_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNamePostImageOnFlickr_VH.set(NSSharingServiceNamePostImageOnFlickr_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNamePostVideoOnVimeo typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNamePostVideoOnVimeo_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNamePostVideoOnVimeo_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNamePostVideoOnVimeo").orElseThrow() }
private val NSSharingServiceNamePostVideoOnVimeo_VH: VarHandle by lazy { NSSharingServiceNamePostVideoOnVimeo_LAYOUT.varHandle() }

var NSSharingServiceNamePostVideoOnVimeo: MemorySegment
    get() = NSSharingServiceNamePostVideoOnVimeo_VH.get(NSSharingServiceNamePostVideoOnVimeo_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNamePostVideoOnVimeo_VH.set(NSSharingServiceNamePostVideoOnVimeo_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNamePostVideoOnYouku typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNamePostVideoOnYouku_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNamePostVideoOnYouku_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNamePostVideoOnYouku").orElseThrow() }
private val NSSharingServiceNamePostVideoOnYouku_VH: VarHandle by lazy { NSSharingServiceNamePostVideoOnYouku_LAYOUT.varHandle() }

var NSSharingServiceNamePostVideoOnYouku: MemorySegment
    get() = NSSharingServiceNamePostVideoOnYouku_VH.get(NSSharingServiceNamePostVideoOnYouku_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNamePostVideoOnYouku_VH.set(NSSharingServiceNamePostVideoOnYouku_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNamePostVideoOnTudou typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNamePostVideoOnTudou_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNamePostVideoOnTudou_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNamePostVideoOnTudou").orElseThrow() }
private val NSSharingServiceNamePostVideoOnTudou_VH: VarHandle by lazy { NSSharingServiceNamePostVideoOnTudou_LAYOUT.varHandle() }

var NSSharingServiceNamePostVideoOnTudou: MemorySegment
    get() = NSSharingServiceNamePostVideoOnTudou_VH.get(NSSharingServiceNamePostVideoOnTudou_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNamePostVideoOnTudou_VH.set(NSSharingServiceNamePostVideoOnTudou_SEGMENT, value)

/**
 * {@snippet lang=c : NSSharingServiceNameCloudSharing typedef const NSSharingServiceName = (Void)*
 */
private val NSSharingServiceNameCloudSharing_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSharingServiceNameCloudSharing_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSharingServiceNameCloudSharing").orElseThrow() }
private val NSSharingServiceNameCloudSharing_VH: VarHandle by lazy { NSSharingServiceNameCloudSharing_LAYOUT.varHandle() }

var NSSharingServiceNameCloudSharing: MemorySegment
    get() = NSSharingServiceNameCloudSharing_VH.get(NSSharingServiceNameCloudSharing_SEGMENT) as MemorySegment
    set(value) = NSSharingServiceNameCloudSharing_VH.set(NSSharingServiceNameCloudSharing_SEGMENT, value)

/**
 * {@snippet lang=c : NSSliderAccessoryWidthDefault typedef const NSSliderAccessoryWidth = Double
 */
private val NSSliderAccessoryWidthDefault_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSSliderAccessoryWidthDefault_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSliderAccessoryWidthDefault").orElseThrow() }
private val NSSliderAccessoryWidthDefault_VH: VarHandle by lazy { NSSliderAccessoryWidthDefault_LAYOUT.varHandle() }

var NSSliderAccessoryWidthDefault: Double
    get() = NSSliderAccessoryWidthDefault_VH.get(NSSliderAccessoryWidthDefault_SEGMENT) as Double
    set(value) = NSSliderAccessoryWidthDefault_VH.set(NSSliderAccessoryWidthDefault_SEGMENT, value)

/**
 * {@snippet lang=c : NSSliderAccessoryWidthWide typedef const NSSliderAccessoryWidth = Double
 */
private val NSSliderAccessoryWidthWide_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSSliderAccessoryWidthWide_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSliderAccessoryWidthWide").orElseThrow() }
private val NSSliderAccessoryWidthWide_VH: VarHandle by lazy { NSSliderAccessoryWidthWide_LAYOUT.varHandle() }

var NSSliderAccessoryWidthWide: Double
    get() = NSSliderAccessoryWidthWide_VH.get(NSSliderAccessoryWidthWide_SEGMENT) as Double
    set(value) = NSSliderAccessoryWidthWide_VH.set(NSSliderAccessoryWidthWide_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceName typedef const NSVoiceAttributeKey = (Void)*
 */
private val NSVoiceName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceName").orElseThrow() }
private val NSVoiceName_VH: VarHandle by lazy { NSVoiceName_LAYOUT.varHandle() }

var NSVoiceName: MemorySegment
    get() = NSVoiceName_VH.get(NSVoiceName_SEGMENT) as MemorySegment
    set(value) = NSVoiceName_VH.set(NSVoiceName_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceIdentifier typedef const NSVoiceAttributeKey = (Void)*
 */
private val NSVoiceIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceIdentifier").orElseThrow() }
private val NSVoiceIdentifier_VH: VarHandle by lazy { NSVoiceIdentifier_LAYOUT.varHandle() }

var NSVoiceIdentifier: MemorySegment
    get() = NSVoiceIdentifier_VH.get(NSVoiceIdentifier_SEGMENT) as MemorySegment
    set(value) = NSVoiceIdentifier_VH.set(NSVoiceIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceAge typedef const NSVoiceAttributeKey = (Void)*
 */
private val NSVoiceAge_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceAge_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceAge").orElseThrow() }
private val NSVoiceAge_VH: VarHandle by lazy { NSVoiceAge_LAYOUT.varHandle() }

var NSVoiceAge: MemorySegment
    get() = NSVoiceAge_VH.get(NSVoiceAge_SEGMENT) as MemorySegment
    set(value) = NSVoiceAge_VH.set(NSVoiceAge_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceGender typedef const NSVoiceAttributeKey = (Void)*
 */
private val NSVoiceGender_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceGender_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceGender").orElseThrow() }
private val NSVoiceGender_VH: VarHandle by lazy { NSVoiceGender_LAYOUT.varHandle() }

var NSVoiceGender: MemorySegment
    get() = NSVoiceGender_VH.get(NSVoiceGender_SEGMENT) as MemorySegment
    set(value) = NSVoiceGender_VH.set(NSVoiceGender_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceDemoText typedef const NSVoiceAttributeKey = (Void)*
 */
private val NSVoiceDemoText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceDemoText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceDemoText").orElseThrow() }
private val NSVoiceDemoText_VH: VarHandle by lazy { NSVoiceDemoText_LAYOUT.varHandle() }

var NSVoiceDemoText: MemorySegment
    get() = NSVoiceDemoText_VH.get(NSVoiceDemoText_SEGMENT) as MemorySegment
    set(value) = NSVoiceDemoText_VH.set(NSVoiceDemoText_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceLocaleIdentifier typedef const NSVoiceAttributeKey = (Void)*
 */
private val NSVoiceLocaleIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceLocaleIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceLocaleIdentifier").orElseThrow() }
private val NSVoiceLocaleIdentifier_VH: VarHandle by lazy { NSVoiceLocaleIdentifier_LAYOUT.varHandle() }

var NSVoiceLocaleIdentifier: MemorySegment
    get() = NSVoiceLocaleIdentifier_VH.get(NSVoiceLocaleIdentifier_SEGMENT) as MemorySegment
    set(value) = NSVoiceLocaleIdentifier_VH.set(NSVoiceLocaleIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceSupportedCharacters typedef const NSVoiceAttributeKey = (Void)*
 */
private val NSVoiceSupportedCharacters_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceSupportedCharacters_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceSupportedCharacters").orElseThrow() }
private val NSVoiceSupportedCharacters_VH: VarHandle by lazy { NSVoiceSupportedCharacters_LAYOUT.varHandle() }

var NSVoiceSupportedCharacters: MemorySegment
    get() = NSVoiceSupportedCharacters_VH.get(NSVoiceSupportedCharacters_SEGMENT) as MemorySegment
    set(value) = NSVoiceSupportedCharacters_VH.set(NSVoiceSupportedCharacters_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceIndividuallySpokenCharacters typedef const NSVoiceAttributeKey = (Void)*
 */
private val NSVoiceIndividuallySpokenCharacters_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceIndividuallySpokenCharacters_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceIndividuallySpokenCharacters").orElseThrow() }
private val NSVoiceIndividuallySpokenCharacters_VH: VarHandle by lazy { NSVoiceIndividuallySpokenCharacters_LAYOUT.varHandle() }

var NSVoiceIndividuallySpokenCharacters: MemorySegment
    get() = NSVoiceIndividuallySpokenCharacters_VH.get(NSVoiceIndividuallySpokenCharacters_SEGMENT) as MemorySegment
    set(value) = NSVoiceIndividuallySpokenCharacters_VH.set(NSVoiceIndividuallySpokenCharacters_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechDictionaryLocaleIdentifier typedef const NSSpeechDictionaryKey = (Void)*
 */
private val NSSpeechDictionaryLocaleIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechDictionaryLocaleIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechDictionaryLocaleIdentifier").orElseThrow() }
private val NSSpeechDictionaryLocaleIdentifier_VH: VarHandle by lazy { NSSpeechDictionaryLocaleIdentifier_LAYOUT.varHandle() }

var NSSpeechDictionaryLocaleIdentifier: MemorySegment
    get() = NSSpeechDictionaryLocaleIdentifier_VH.get(NSSpeechDictionaryLocaleIdentifier_SEGMENT) as MemorySegment
    set(value) = NSSpeechDictionaryLocaleIdentifier_VH.set(NSSpeechDictionaryLocaleIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechDictionaryModificationDate typedef const NSSpeechDictionaryKey = (Void)*
 */
private val NSSpeechDictionaryModificationDate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechDictionaryModificationDate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechDictionaryModificationDate").orElseThrow() }
private val NSSpeechDictionaryModificationDate_VH: VarHandle by lazy { NSSpeechDictionaryModificationDate_LAYOUT.varHandle() }

var NSSpeechDictionaryModificationDate: MemorySegment
    get() = NSSpeechDictionaryModificationDate_VH.get(NSSpeechDictionaryModificationDate_SEGMENT) as MemorySegment
    set(value) = NSSpeechDictionaryModificationDate_VH.set(NSSpeechDictionaryModificationDate_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechDictionaryPronunciations typedef const NSSpeechDictionaryKey = (Void)*
 */
private val NSSpeechDictionaryPronunciations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechDictionaryPronunciations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechDictionaryPronunciations").orElseThrow() }
private val NSSpeechDictionaryPronunciations_VH: VarHandle by lazy { NSSpeechDictionaryPronunciations_LAYOUT.varHandle() }

var NSSpeechDictionaryPronunciations: MemorySegment
    get() = NSSpeechDictionaryPronunciations_VH.get(NSSpeechDictionaryPronunciations_SEGMENT) as MemorySegment
    set(value) = NSSpeechDictionaryPronunciations_VH.set(NSSpeechDictionaryPronunciations_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechDictionaryAbbreviations typedef const NSSpeechDictionaryKey = (Void)*
 */
private val NSSpeechDictionaryAbbreviations_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechDictionaryAbbreviations_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechDictionaryAbbreviations").orElseThrow() }
private val NSSpeechDictionaryAbbreviations_VH: VarHandle by lazy { NSSpeechDictionaryAbbreviations_LAYOUT.varHandle() }

var NSSpeechDictionaryAbbreviations: MemorySegment
    get() = NSSpeechDictionaryAbbreviations_VH.get(NSSpeechDictionaryAbbreviations_SEGMENT) as MemorySegment
    set(value) = NSSpeechDictionaryAbbreviations_VH.set(NSSpeechDictionaryAbbreviations_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechDictionaryEntrySpelling typedef const NSSpeechDictionaryKey = (Void)*
 */
private val NSSpeechDictionaryEntrySpelling_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechDictionaryEntrySpelling_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechDictionaryEntrySpelling").orElseThrow() }
private val NSSpeechDictionaryEntrySpelling_VH: VarHandle by lazy { NSSpeechDictionaryEntrySpelling_LAYOUT.varHandle() }

var NSSpeechDictionaryEntrySpelling: MemorySegment
    get() = NSSpeechDictionaryEntrySpelling_VH.get(NSSpeechDictionaryEntrySpelling_SEGMENT) as MemorySegment
    set(value) = NSSpeechDictionaryEntrySpelling_VH.set(NSSpeechDictionaryEntrySpelling_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechDictionaryEntryPhonemes typedef const NSSpeechDictionaryKey = (Void)*
 */
private val NSSpeechDictionaryEntryPhonemes_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechDictionaryEntryPhonemes_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechDictionaryEntryPhonemes").orElseThrow() }
private val NSSpeechDictionaryEntryPhonemes_VH: VarHandle by lazy { NSSpeechDictionaryEntryPhonemes_LAYOUT.varHandle() }

var NSSpeechDictionaryEntryPhonemes: MemorySegment
    get() = NSSpeechDictionaryEntryPhonemes_VH.get(NSSpeechDictionaryEntryPhonemes_SEGMENT) as MemorySegment
    set(value) = NSSpeechDictionaryEntryPhonemes_VH.set(NSSpeechDictionaryEntryPhonemes_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceGenderNeuter typedef const NSVoiceGenderName = (Void)*
 */
private val NSVoiceGenderNeuter_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceGenderNeuter_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceGenderNeuter").orElseThrow() }
private val NSVoiceGenderNeuter_VH: VarHandle by lazy { NSVoiceGenderNeuter_LAYOUT.varHandle() }

var NSVoiceGenderNeuter: MemorySegment
    get() = NSVoiceGenderNeuter_VH.get(NSVoiceGenderNeuter_SEGMENT) as MemorySegment
    set(value) = NSVoiceGenderNeuter_VH.set(NSVoiceGenderNeuter_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceGenderMale typedef const NSVoiceGenderName = (Void)*
 */
private val NSVoiceGenderMale_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceGenderMale_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceGenderMale").orElseThrow() }
private val NSVoiceGenderMale_VH: VarHandle by lazy { NSVoiceGenderMale_LAYOUT.varHandle() }

var NSVoiceGenderMale: MemorySegment
    get() = NSVoiceGenderMale_VH.get(NSVoiceGenderMale_SEGMENT) as MemorySegment
    set(value) = NSVoiceGenderMale_VH.set(NSVoiceGenderMale_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceGenderFemale typedef const NSVoiceGenderName = (Void)*
 */
private val NSVoiceGenderFemale_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceGenderFemale_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceGenderFemale").orElseThrow() }
private val NSVoiceGenderFemale_VH: VarHandle by lazy { NSVoiceGenderFemale_LAYOUT.varHandle() }

var NSVoiceGenderFemale: MemorySegment
    get() = NSVoiceGenderFemale_VH.get(NSVoiceGenderFemale_SEGMENT) as MemorySegment
    set(value) = NSVoiceGenderFemale_VH.set(NSVoiceGenderFemale_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceGenderNeutral typedef const NSVoiceGenderName = (Void)*
 */
private val NSVoiceGenderNeutral_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceGenderNeutral_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceGenderNeutral").orElseThrow() }
private val NSVoiceGenderNeutral_VH: VarHandle by lazy { NSVoiceGenderNeutral_LAYOUT.varHandle() }

var NSVoiceGenderNeutral: MemorySegment
    get() = NSVoiceGenderNeutral_VH.get(NSVoiceGenderNeutral_SEGMENT) as MemorySegment
    set(value) = NSVoiceGenderNeutral_VH.set(NSVoiceGenderNeutral_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechStatusProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechStatusProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechStatusProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechStatusProperty").orElseThrow() }
private val NSSpeechStatusProperty_VH: VarHandle by lazy { NSSpeechStatusProperty_LAYOUT.varHandle() }

var NSSpeechStatusProperty: MemorySegment
    get() = NSSpeechStatusProperty_VH.get(NSSpeechStatusProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechStatusProperty_VH.set(NSSpeechStatusProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechErrorsProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechErrorsProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechErrorsProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechErrorsProperty").orElseThrow() }
private val NSSpeechErrorsProperty_VH: VarHandle by lazy { NSSpeechErrorsProperty_LAYOUT.varHandle() }

var NSSpeechErrorsProperty: MemorySegment
    get() = NSSpeechErrorsProperty_VH.get(NSSpeechErrorsProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechErrorsProperty_VH.set(NSSpeechErrorsProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechInputModeProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechInputModeProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechInputModeProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechInputModeProperty").orElseThrow() }
private val NSSpeechInputModeProperty_VH: VarHandle by lazy { NSSpeechInputModeProperty_LAYOUT.varHandle() }

var NSSpeechInputModeProperty: MemorySegment
    get() = NSSpeechInputModeProperty_VH.get(NSSpeechInputModeProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechInputModeProperty_VH.set(NSSpeechInputModeProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechCharacterModeProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechCharacterModeProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechCharacterModeProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechCharacterModeProperty").orElseThrow() }
private val NSSpeechCharacterModeProperty_VH: VarHandle by lazy { NSSpeechCharacterModeProperty_LAYOUT.varHandle() }

var NSSpeechCharacterModeProperty: MemorySegment
    get() = NSSpeechCharacterModeProperty_VH.get(NSSpeechCharacterModeProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechCharacterModeProperty_VH.set(NSSpeechCharacterModeProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechNumberModeProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechNumberModeProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechNumberModeProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechNumberModeProperty").orElseThrow() }
private val NSSpeechNumberModeProperty_VH: VarHandle by lazy { NSSpeechNumberModeProperty_LAYOUT.varHandle() }

var NSSpeechNumberModeProperty: MemorySegment
    get() = NSSpeechNumberModeProperty_VH.get(NSSpeechNumberModeProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechNumberModeProperty_VH.set(NSSpeechNumberModeProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechRateProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechRateProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechRateProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechRateProperty").orElseThrow() }
private val NSSpeechRateProperty_VH: VarHandle by lazy { NSSpeechRateProperty_LAYOUT.varHandle() }

var NSSpeechRateProperty: MemorySegment
    get() = NSSpeechRateProperty_VH.get(NSSpeechRateProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechRateProperty_VH.set(NSSpeechRateProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechPitchBaseProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechPitchBaseProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechPitchBaseProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechPitchBaseProperty").orElseThrow() }
private val NSSpeechPitchBaseProperty_VH: VarHandle by lazy { NSSpeechPitchBaseProperty_LAYOUT.varHandle() }

var NSSpeechPitchBaseProperty: MemorySegment
    get() = NSSpeechPitchBaseProperty_VH.get(NSSpeechPitchBaseProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechPitchBaseProperty_VH.set(NSSpeechPitchBaseProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechPitchModProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechPitchModProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechPitchModProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechPitchModProperty").orElseThrow() }
private val NSSpeechPitchModProperty_VH: VarHandle by lazy { NSSpeechPitchModProperty_LAYOUT.varHandle() }

var NSSpeechPitchModProperty: MemorySegment
    get() = NSSpeechPitchModProperty_VH.get(NSSpeechPitchModProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechPitchModProperty_VH.set(NSSpeechPitchModProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechVolumeProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechVolumeProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechVolumeProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechVolumeProperty").orElseThrow() }
private val NSSpeechVolumeProperty_VH: VarHandle by lazy { NSSpeechVolumeProperty_LAYOUT.varHandle() }

var NSSpeechVolumeProperty: MemorySegment
    get() = NSSpeechVolumeProperty_VH.get(NSSpeechVolumeProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechVolumeProperty_VH.set(NSSpeechVolumeProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechSynthesizerInfoProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechSynthesizerInfoProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechSynthesizerInfoProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechSynthesizerInfoProperty").orElseThrow() }
private val NSSpeechSynthesizerInfoProperty_VH: VarHandle by lazy { NSSpeechSynthesizerInfoProperty_LAYOUT.varHandle() }

var NSSpeechSynthesizerInfoProperty: MemorySegment
    get() = NSSpeechSynthesizerInfoProperty_VH.get(NSSpeechSynthesizerInfoProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechSynthesizerInfoProperty_VH.set(NSSpeechSynthesizerInfoProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechRecentSyncProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechRecentSyncProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechRecentSyncProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechRecentSyncProperty").orElseThrow() }
private val NSSpeechRecentSyncProperty_VH: VarHandle by lazy { NSSpeechRecentSyncProperty_LAYOUT.varHandle() }

var NSSpeechRecentSyncProperty: MemorySegment
    get() = NSSpeechRecentSyncProperty_VH.get(NSSpeechRecentSyncProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechRecentSyncProperty_VH.set(NSSpeechRecentSyncProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechPhonemeSymbolsProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechPhonemeSymbolsProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechPhonemeSymbolsProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechPhonemeSymbolsProperty").orElseThrow() }
private val NSSpeechPhonemeSymbolsProperty_VH: VarHandle by lazy { NSSpeechPhonemeSymbolsProperty_LAYOUT.varHandle() }

var NSSpeechPhonemeSymbolsProperty: MemorySegment
    get() = NSSpeechPhonemeSymbolsProperty_VH.get(NSSpeechPhonemeSymbolsProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechPhonemeSymbolsProperty_VH.set(NSSpeechPhonemeSymbolsProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechCurrentVoiceProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechCurrentVoiceProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechCurrentVoiceProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechCurrentVoiceProperty").orElseThrow() }
private val NSSpeechCurrentVoiceProperty_VH: VarHandle by lazy { NSSpeechCurrentVoiceProperty_LAYOUT.varHandle() }

var NSSpeechCurrentVoiceProperty: MemorySegment
    get() = NSSpeechCurrentVoiceProperty_VH.get(NSSpeechCurrentVoiceProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechCurrentVoiceProperty_VH.set(NSSpeechCurrentVoiceProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechCommandDelimiterProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechCommandDelimiterProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechCommandDelimiterProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechCommandDelimiterProperty").orElseThrow() }
private val NSSpeechCommandDelimiterProperty_VH: VarHandle by lazy { NSSpeechCommandDelimiterProperty_LAYOUT.varHandle() }

var NSSpeechCommandDelimiterProperty: MemorySegment
    get() = NSSpeechCommandDelimiterProperty_VH.get(NSSpeechCommandDelimiterProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechCommandDelimiterProperty_VH.set(NSSpeechCommandDelimiterProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechResetProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechResetProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechResetProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechResetProperty").orElseThrow() }
private val NSSpeechResetProperty_VH: VarHandle by lazy { NSSpeechResetProperty_LAYOUT.varHandle() }

var NSSpeechResetProperty: MemorySegment
    get() = NSSpeechResetProperty_VH.get(NSSpeechResetProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechResetProperty_VH.set(NSSpeechResetProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechOutputToFileURLProperty typedef const NSSpeechPropertyKey = (Void)*
 */
private val NSSpeechOutputToFileURLProperty_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechOutputToFileURLProperty_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechOutputToFileURLProperty").orElseThrow() }
private val NSSpeechOutputToFileURLProperty_VH: VarHandle by lazy { NSSpeechOutputToFileURLProperty_LAYOUT.varHandle() }

var NSSpeechOutputToFileURLProperty: MemorySegment
    get() = NSSpeechOutputToFileURLProperty_VH.get(NSSpeechOutputToFileURLProperty_SEGMENT) as MemorySegment
    set(value) = NSSpeechOutputToFileURLProperty_VH.set(NSSpeechOutputToFileURLProperty_SEGMENT, value)

/**
 * {@snippet lang=c : NSVoiceLanguage typedef const NSVoiceAttributeKey = (Void)*
 */
private val NSVoiceLanguage_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSVoiceLanguage_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSVoiceLanguage").orElseThrow() }
private val NSVoiceLanguage_VH: VarHandle by lazy { NSVoiceLanguage_LAYOUT.varHandle() }

var NSVoiceLanguage: MemorySegment
    get() = NSVoiceLanguage_VH.get(NSVoiceLanguage_SEGMENT) as MemorySegment
    set(value) = NSVoiceLanguage_VH.set(NSVoiceLanguage_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechModeText typedef const NSSpeechMode = (Void)*
 */
private val NSSpeechModeText_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechModeText_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechModeText").orElseThrow() }
private val NSSpeechModeText_VH: VarHandle by lazy { NSSpeechModeText_LAYOUT.varHandle() }

var NSSpeechModeText: MemorySegment
    get() = NSSpeechModeText_VH.get(NSSpeechModeText_SEGMENT) as MemorySegment
    set(value) = NSSpeechModeText_VH.set(NSSpeechModeText_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechModePhoneme typedef const NSSpeechMode = (Void)*
 */
private val NSSpeechModePhoneme_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechModePhoneme_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechModePhoneme").orElseThrow() }
private val NSSpeechModePhoneme_VH: VarHandle by lazy { NSSpeechModePhoneme_LAYOUT.varHandle() }

var NSSpeechModePhoneme: MemorySegment
    get() = NSSpeechModePhoneme_VH.get(NSSpeechModePhoneme_SEGMENT) as MemorySegment
    set(value) = NSSpeechModePhoneme_VH.set(NSSpeechModePhoneme_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechModeNormal typedef const NSSpeechMode = (Void)*
 */
private val NSSpeechModeNormal_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechModeNormal_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechModeNormal").orElseThrow() }
private val NSSpeechModeNormal_VH: VarHandle by lazy { NSSpeechModeNormal_LAYOUT.varHandle() }

var NSSpeechModeNormal: MemorySegment
    get() = NSSpeechModeNormal_VH.get(NSSpeechModeNormal_SEGMENT) as MemorySegment
    set(value) = NSSpeechModeNormal_VH.set(NSSpeechModeNormal_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechModeLiteral typedef const NSSpeechMode = (Void)*
 */
private val NSSpeechModeLiteral_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechModeLiteral_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechModeLiteral").orElseThrow() }
private val NSSpeechModeLiteral_VH: VarHandle by lazy { NSSpeechModeLiteral_LAYOUT.varHandle() }

var NSSpeechModeLiteral: MemorySegment
    get() = NSSpeechModeLiteral_VH.get(NSSpeechModeLiteral_SEGMENT) as MemorySegment
    set(value) = NSSpeechModeLiteral_VH.set(NSSpeechModeLiteral_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechStatusOutputBusy typedef const NSSpeechStatusKey = (Void)*
 */
private val NSSpeechStatusOutputBusy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechStatusOutputBusy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechStatusOutputBusy").orElseThrow() }
private val NSSpeechStatusOutputBusy_VH: VarHandle by lazy { NSSpeechStatusOutputBusy_LAYOUT.varHandle() }

var NSSpeechStatusOutputBusy: MemorySegment
    get() = NSSpeechStatusOutputBusy_VH.get(NSSpeechStatusOutputBusy_SEGMENT) as MemorySegment
    set(value) = NSSpeechStatusOutputBusy_VH.set(NSSpeechStatusOutputBusy_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechStatusOutputPaused typedef const NSSpeechStatusKey = (Void)*
 */
private val NSSpeechStatusOutputPaused_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechStatusOutputPaused_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechStatusOutputPaused").orElseThrow() }
private val NSSpeechStatusOutputPaused_VH: VarHandle by lazy { NSSpeechStatusOutputPaused_LAYOUT.varHandle() }

var NSSpeechStatusOutputPaused: MemorySegment
    get() = NSSpeechStatusOutputPaused_VH.get(NSSpeechStatusOutputPaused_SEGMENT) as MemorySegment
    set(value) = NSSpeechStatusOutputPaused_VH.set(NSSpeechStatusOutputPaused_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechStatusNumberOfCharactersLeft typedef const NSSpeechStatusKey = (Void)*
 */
private val NSSpeechStatusNumberOfCharactersLeft_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechStatusNumberOfCharactersLeft_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechStatusNumberOfCharactersLeft").orElseThrow() }
private val NSSpeechStatusNumberOfCharactersLeft_VH: VarHandle by lazy { NSSpeechStatusNumberOfCharactersLeft_LAYOUT.varHandle() }

var NSSpeechStatusNumberOfCharactersLeft: MemorySegment
    get() = NSSpeechStatusNumberOfCharactersLeft_VH.get(NSSpeechStatusNumberOfCharactersLeft_SEGMENT) as MemorySegment
    set(value) = NSSpeechStatusNumberOfCharactersLeft_VH.set(NSSpeechStatusNumberOfCharactersLeft_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechStatusPhonemeCode typedef const NSSpeechStatusKey = (Void)*
 */
private val NSSpeechStatusPhonemeCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechStatusPhonemeCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechStatusPhonemeCode").orElseThrow() }
private val NSSpeechStatusPhonemeCode_VH: VarHandle by lazy { NSSpeechStatusPhonemeCode_LAYOUT.varHandle() }

var NSSpeechStatusPhonemeCode: MemorySegment
    get() = NSSpeechStatusPhonemeCode_VH.get(NSSpeechStatusPhonemeCode_SEGMENT) as MemorySegment
    set(value) = NSSpeechStatusPhonemeCode_VH.set(NSSpeechStatusPhonemeCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechErrorCount typedef const NSSpeechErrorKey = (Void)*
 */
private val NSSpeechErrorCount_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechErrorCount_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechErrorCount").orElseThrow() }
private val NSSpeechErrorCount_VH: VarHandle by lazy { NSSpeechErrorCount_LAYOUT.varHandle() }

var NSSpeechErrorCount: MemorySegment
    get() = NSSpeechErrorCount_VH.get(NSSpeechErrorCount_SEGMENT) as MemorySegment
    set(value) = NSSpeechErrorCount_VH.set(NSSpeechErrorCount_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechErrorOldestCode typedef const NSSpeechErrorKey = (Void)*
 */
private val NSSpeechErrorOldestCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechErrorOldestCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechErrorOldestCode").orElseThrow() }
private val NSSpeechErrorOldestCode_VH: VarHandle by lazy { NSSpeechErrorOldestCode_LAYOUT.varHandle() }

var NSSpeechErrorOldestCode: MemorySegment
    get() = NSSpeechErrorOldestCode_VH.get(NSSpeechErrorOldestCode_SEGMENT) as MemorySegment
    set(value) = NSSpeechErrorOldestCode_VH.set(NSSpeechErrorOldestCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechErrorOldestCharacterOffset typedef const NSSpeechErrorKey = (Void)*
 */
private val NSSpeechErrorOldestCharacterOffset_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechErrorOldestCharacterOffset_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechErrorOldestCharacterOffset").orElseThrow() }
private val NSSpeechErrorOldestCharacterOffset_VH: VarHandle by lazy { NSSpeechErrorOldestCharacterOffset_LAYOUT.varHandle() }

var NSSpeechErrorOldestCharacterOffset: MemorySegment
    get() = NSSpeechErrorOldestCharacterOffset_VH.get(NSSpeechErrorOldestCharacterOffset_SEGMENT) as MemorySegment
    set(value) = NSSpeechErrorOldestCharacterOffset_VH.set(NSSpeechErrorOldestCharacterOffset_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechErrorNewestCode typedef const NSSpeechErrorKey = (Void)*
 */
private val NSSpeechErrorNewestCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechErrorNewestCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechErrorNewestCode").orElseThrow() }
private val NSSpeechErrorNewestCode_VH: VarHandle by lazy { NSSpeechErrorNewestCode_LAYOUT.varHandle() }

var NSSpeechErrorNewestCode: MemorySegment
    get() = NSSpeechErrorNewestCode_VH.get(NSSpeechErrorNewestCode_SEGMENT) as MemorySegment
    set(value) = NSSpeechErrorNewestCode_VH.set(NSSpeechErrorNewestCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechErrorNewestCharacterOffset typedef const NSSpeechErrorKey = (Void)*
 */
private val NSSpeechErrorNewestCharacterOffset_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechErrorNewestCharacterOffset_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechErrorNewestCharacterOffset").orElseThrow() }
private val NSSpeechErrorNewestCharacterOffset_VH: VarHandle by lazy { NSSpeechErrorNewestCharacterOffset_LAYOUT.varHandle() }

var NSSpeechErrorNewestCharacterOffset: MemorySegment
    get() = NSSpeechErrorNewestCharacterOffset_VH.get(NSSpeechErrorNewestCharacterOffset_SEGMENT) as MemorySegment
    set(value) = NSSpeechErrorNewestCharacterOffset_VH.set(NSSpeechErrorNewestCharacterOffset_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechSynthesizerInfoIdentifier typedef const NSSpeechSynthesizerInfoKey = (Void)*
 */
private val NSSpeechSynthesizerInfoIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechSynthesizerInfoIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechSynthesizerInfoIdentifier").orElseThrow() }
private val NSSpeechSynthesizerInfoIdentifier_VH: VarHandle by lazy { NSSpeechSynthesizerInfoIdentifier_LAYOUT.varHandle() }

var NSSpeechSynthesizerInfoIdentifier: MemorySegment
    get() = NSSpeechSynthesizerInfoIdentifier_VH.get(NSSpeechSynthesizerInfoIdentifier_SEGMENT) as MemorySegment
    set(value) = NSSpeechSynthesizerInfoIdentifier_VH.set(NSSpeechSynthesizerInfoIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechSynthesizerInfoVersion typedef const NSSpeechSynthesizerInfoKey = (Void)*
 */
private val NSSpeechSynthesizerInfoVersion_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechSynthesizerInfoVersion_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechSynthesizerInfoVersion").orElseThrow() }
private val NSSpeechSynthesizerInfoVersion_VH: VarHandle by lazy { NSSpeechSynthesizerInfoVersion_LAYOUT.varHandle() }

var NSSpeechSynthesizerInfoVersion: MemorySegment
    get() = NSSpeechSynthesizerInfoVersion_VH.get(NSSpeechSynthesizerInfoVersion_SEGMENT) as MemorySegment
    set(value) = NSSpeechSynthesizerInfoVersion_VH.set(NSSpeechSynthesizerInfoVersion_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechPhonemeInfoOpcode typedef const NSSpeechPhonemeInfoKey = (Void)*
 */
private val NSSpeechPhonemeInfoOpcode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechPhonemeInfoOpcode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechPhonemeInfoOpcode").orElseThrow() }
private val NSSpeechPhonemeInfoOpcode_VH: VarHandle by lazy { NSSpeechPhonemeInfoOpcode_LAYOUT.varHandle() }

var NSSpeechPhonemeInfoOpcode: MemorySegment
    get() = NSSpeechPhonemeInfoOpcode_VH.get(NSSpeechPhonemeInfoOpcode_SEGMENT) as MemorySegment
    set(value) = NSSpeechPhonemeInfoOpcode_VH.set(NSSpeechPhonemeInfoOpcode_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechPhonemeInfoSymbol typedef const NSSpeechPhonemeInfoKey = (Void)*
 */
private val NSSpeechPhonemeInfoSymbol_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechPhonemeInfoSymbol_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechPhonemeInfoSymbol").orElseThrow() }
private val NSSpeechPhonemeInfoSymbol_VH: VarHandle by lazy { NSSpeechPhonemeInfoSymbol_LAYOUT.varHandle() }

var NSSpeechPhonemeInfoSymbol: MemorySegment
    get() = NSSpeechPhonemeInfoSymbol_VH.get(NSSpeechPhonemeInfoSymbol_SEGMENT) as MemorySegment
    set(value) = NSSpeechPhonemeInfoSymbol_VH.set(NSSpeechPhonemeInfoSymbol_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechPhonemeInfoExample typedef const NSSpeechPhonemeInfoKey = (Void)*
 */
private val NSSpeechPhonemeInfoExample_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechPhonemeInfoExample_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechPhonemeInfoExample").orElseThrow() }
private val NSSpeechPhonemeInfoExample_VH: VarHandle by lazy { NSSpeechPhonemeInfoExample_LAYOUT.varHandle() }

var NSSpeechPhonemeInfoExample: MemorySegment
    get() = NSSpeechPhonemeInfoExample_VH.get(NSSpeechPhonemeInfoExample_SEGMENT) as MemorySegment
    set(value) = NSSpeechPhonemeInfoExample_VH.set(NSSpeechPhonemeInfoExample_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechPhonemeInfoHiliteStart typedef const NSSpeechPhonemeInfoKey = (Void)*
 */
private val NSSpeechPhonemeInfoHiliteStart_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechPhonemeInfoHiliteStart_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechPhonemeInfoHiliteStart").orElseThrow() }
private val NSSpeechPhonemeInfoHiliteStart_VH: VarHandle by lazy { NSSpeechPhonemeInfoHiliteStart_LAYOUT.varHandle() }

var NSSpeechPhonemeInfoHiliteStart: MemorySegment
    get() = NSSpeechPhonemeInfoHiliteStart_VH.get(NSSpeechPhonemeInfoHiliteStart_SEGMENT) as MemorySegment
    set(value) = NSSpeechPhonemeInfoHiliteStart_VH.set(NSSpeechPhonemeInfoHiliteStart_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechPhonemeInfoHiliteEnd typedef const NSSpeechPhonemeInfoKey = (Void)*
 */
private val NSSpeechPhonemeInfoHiliteEnd_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechPhonemeInfoHiliteEnd_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechPhonemeInfoHiliteEnd").orElseThrow() }
private val NSSpeechPhonemeInfoHiliteEnd_VH: VarHandle by lazy { NSSpeechPhonemeInfoHiliteEnd_LAYOUT.varHandle() }

var NSSpeechPhonemeInfoHiliteEnd: MemorySegment
    get() = NSSpeechPhonemeInfoHiliteEnd_VH.get(NSSpeechPhonemeInfoHiliteEnd_SEGMENT) as MemorySegment
    set(value) = NSSpeechPhonemeInfoHiliteEnd_VH.set(NSSpeechPhonemeInfoHiliteEnd_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechCommandPrefix typedef const NSSpeechCommandDelimiterKey = (Void)*
 */
private val NSSpeechCommandPrefix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechCommandPrefix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechCommandPrefix").orElseThrow() }
private val NSSpeechCommandPrefix_VH: VarHandle by lazy { NSSpeechCommandPrefix_LAYOUT.varHandle() }

var NSSpeechCommandPrefix: MemorySegment
    get() = NSSpeechCommandPrefix_VH.get(NSSpeechCommandPrefix_SEGMENT) as MemorySegment
    set(value) = NSSpeechCommandPrefix_VH.set(NSSpeechCommandPrefix_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpeechCommandSuffix typedef const NSSpeechCommandDelimiterKey = (Void)*
 */
private val NSSpeechCommandSuffix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpeechCommandSuffix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpeechCommandSuffix").orElseThrow() }
private val NSSpeechCommandSuffix_VH: VarHandle by lazy { NSSpeechCommandSuffix_LAYOUT.varHandle() }

var NSSpeechCommandSuffix: MemorySegment
    get() = NSSpeechCommandSuffix_VH.get(NSSpeechCommandSuffix_SEGMENT) as MemorySegment
    set(value) = NSSpeechCommandSuffix_VH.set(NSSpeechCommandSuffix_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingOrthographyKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingOrthographyKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingOrthographyKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingOrthographyKey").orElseThrow() }
private val NSTextCheckingOrthographyKey_VH: VarHandle by lazy { NSTextCheckingOrthographyKey_LAYOUT.varHandle() }

var NSTextCheckingOrthographyKey: MemorySegment
    get() = NSTextCheckingOrthographyKey_VH.get(NSTextCheckingOrthographyKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingOrthographyKey_VH.set(NSTextCheckingOrthographyKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingQuotesKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingQuotesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingQuotesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingQuotesKey").orElseThrow() }
private val NSTextCheckingQuotesKey_VH: VarHandle by lazy { NSTextCheckingQuotesKey_LAYOUT.varHandle() }

var NSTextCheckingQuotesKey: MemorySegment
    get() = NSTextCheckingQuotesKey_VH.get(NSTextCheckingQuotesKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingQuotesKey_VH.set(NSTextCheckingQuotesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingReplacementsKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingReplacementsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingReplacementsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingReplacementsKey").orElseThrow() }
private val NSTextCheckingReplacementsKey_VH: VarHandle by lazy { NSTextCheckingReplacementsKey_LAYOUT.varHandle() }

var NSTextCheckingReplacementsKey: MemorySegment
    get() = NSTextCheckingReplacementsKey_VH.get(NSTextCheckingReplacementsKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingReplacementsKey_VH.set(NSTextCheckingReplacementsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingReferenceDateKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingReferenceDateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingReferenceDateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingReferenceDateKey").orElseThrow() }
private val NSTextCheckingReferenceDateKey_VH: VarHandle by lazy { NSTextCheckingReferenceDateKey_LAYOUT.varHandle() }

var NSTextCheckingReferenceDateKey: MemorySegment
    get() = NSTextCheckingReferenceDateKey_VH.get(NSTextCheckingReferenceDateKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingReferenceDateKey_VH.set(NSTextCheckingReferenceDateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingReferenceTimeZoneKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingReferenceTimeZoneKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingReferenceTimeZoneKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingReferenceTimeZoneKey").orElseThrow() }
private val NSTextCheckingReferenceTimeZoneKey_VH: VarHandle by lazy { NSTextCheckingReferenceTimeZoneKey_LAYOUT.varHandle() }

var NSTextCheckingReferenceTimeZoneKey: MemorySegment
    get() = NSTextCheckingReferenceTimeZoneKey_VH.get(NSTextCheckingReferenceTimeZoneKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingReferenceTimeZoneKey_VH.set(NSTextCheckingReferenceTimeZoneKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingDocumentURLKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingDocumentURLKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingDocumentURLKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingDocumentURLKey").orElseThrow() }
private val NSTextCheckingDocumentURLKey_VH: VarHandle by lazy { NSTextCheckingDocumentURLKey_LAYOUT.varHandle() }

var NSTextCheckingDocumentURLKey: MemorySegment
    get() = NSTextCheckingDocumentURLKey_VH.get(NSTextCheckingDocumentURLKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingDocumentURLKey_VH.set(NSTextCheckingDocumentURLKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingDocumentTitleKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingDocumentTitleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingDocumentTitleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingDocumentTitleKey").orElseThrow() }
private val NSTextCheckingDocumentTitleKey_VH: VarHandle by lazy { NSTextCheckingDocumentTitleKey_LAYOUT.varHandle() }

var NSTextCheckingDocumentTitleKey: MemorySegment
    get() = NSTextCheckingDocumentTitleKey_VH.get(NSTextCheckingDocumentTitleKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingDocumentTitleKey_VH.set(NSTextCheckingDocumentTitleKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingDocumentAuthorKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingDocumentAuthorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingDocumentAuthorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingDocumentAuthorKey").orElseThrow() }
private val NSTextCheckingDocumentAuthorKey_VH: VarHandle by lazy { NSTextCheckingDocumentAuthorKey_LAYOUT.varHandle() }

var NSTextCheckingDocumentAuthorKey: MemorySegment
    get() = NSTextCheckingDocumentAuthorKey_VH.get(NSTextCheckingDocumentAuthorKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingDocumentAuthorKey_VH.set(NSTextCheckingDocumentAuthorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingRegularExpressionsKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingRegularExpressionsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingRegularExpressionsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingRegularExpressionsKey").orElseThrow() }
private val NSTextCheckingRegularExpressionsKey_VH: VarHandle by lazy { NSTextCheckingRegularExpressionsKey_LAYOUT.varHandle() }

var NSTextCheckingRegularExpressionsKey: MemorySegment
    get() = NSTextCheckingRegularExpressionsKey_VH.get(NSTextCheckingRegularExpressionsKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingRegularExpressionsKey_VH.set(NSTextCheckingRegularExpressionsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingSelectedRangeKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingSelectedRangeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingSelectedRangeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingSelectedRangeKey").orElseThrow() }
private val NSTextCheckingSelectedRangeKey_VH: VarHandle by lazy { NSTextCheckingSelectedRangeKey_LAYOUT.varHandle() }

var NSTextCheckingSelectedRangeKey: MemorySegment
    get() = NSTextCheckingSelectedRangeKey_VH.get(NSTextCheckingSelectedRangeKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingSelectedRangeKey_VH.set(NSTextCheckingSelectedRangeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingGenerateInlinePredictionsKey typedef NSTextCheckingOptionKey = typedef NSString = (Void)*
 */
private val NSTextCheckingGenerateInlinePredictionsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingGenerateInlinePredictionsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingGenerateInlinePredictionsKey").orElseThrow() }
private val NSTextCheckingGenerateInlinePredictionsKey_VH: VarHandle by lazy { NSTextCheckingGenerateInlinePredictionsKey_LAYOUT.varHandle() }

var NSTextCheckingGenerateInlinePredictionsKey: MemorySegment
    get() = NSTextCheckingGenerateInlinePredictionsKey_VH.get(NSTextCheckingGenerateInlinePredictionsKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingGenerateInlinePredictionsKey_VH.set(NSTextCheckingGenerateInlinePredictionsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification typedef const NSNotificationName = (Void)*
 */
private val NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification").orElseThrow() }
private val NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification_VH: VarHandle by lazy { NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification_LAYOUT.varHandle() }

var NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification: MemorySegment
    get() = NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification_VH.get(NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification_SEGMENT) as MemorySegment
    set(value) = NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification_VH.set(NSSpellCheckerDidChangeAutomaticSpellingCorrectionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpellCheckerDidChangeAutomaticTextReplacementNotification typedef const NSNotificationName = (Void)*
 */
private val NSSpellCheckerDidChangeAutomaticTextReplacementNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpellCheckerDidChangeAutomaticTextReplacementNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpellCheckerDidChangeAutomaticTextReplacementNotification").orElseThrow() }
private val NSSpellCheckerDidChangeAutomaticTextReplacementNotification_VH: VarHandle by lazy { NSSpellCheckerDidChangeAutomaticTextReplacementNotification_LAYOUT.varHandle() }

var NSSpellCheckerDidChangeAutomaticTextReplacementNotification: MemorySegment
    get() = NSSpellCheckerDidChangeAutomaticTextReplacementNotification_VH.get(NSSpellCheckerDidChangeAutomaticTextReplacementNotification_SEGMENT) as MemorySegment
    set(value) = NSSpellCheckerDidChangeAutomaticTextReplacementNotification_VH.set(NSSpellCheckerDidChangeAutomaticTextReplacementNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification typedef const NSNotificationName = (Void)*
 */
private val NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification").orElseThrow() }
private val NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification_VH: VarHandle by lazy { NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification_LAYOUT.varHandle() }

var NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification: MemorySegment
    get() = NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification_VH.get(NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification_SEGMENT) as MemorySegment
    set(value) = NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification_VH.set(NSSpellCheckerDidChangeAutomaticQuoteSubstitutionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification typedef const NSNotificationName = (Void)*
 */
private val NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification").orElseThrow() }
private val NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification_VH: VarHandle by lazy { NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification_LAYOUT.varHandle() }

var NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification: MemorySegment
    get() = NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification_VH.get(NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification_SEGMENT) as MemorySegment
    set(value) = NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification_VH.set(NSSpellCheckerDidChangeAutomaticDashSubstitutionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpellCheckerDidChangeAutomaticCapitalizationNotification typedef const NSNotificationName = (Void)*
 */
private val NSSpellCheckerDidChangeAutomaticCapitalizationNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpellCheckerDidChangeAutomaticCapitalizationNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpellCheckerDidChangeAutomaticCapitalizationNotification").orElseThrow() }
private val NSSpellCheckerDidChangeAutomaticCapitalizationNotification_VH: VarHandle by lazy { NSSpellCheckerDidChangeAutomaticCapitalizationNotification_LAYOUT.varHandle() }

var NSSpellCheckerDidChangeAutomaticCapitalizationNotification: MemorySegment
    get() = NSSpellCheckerDidChangeAutomaticCapitalizationNotification_VH.get(NSSpellCheckerDidChangeAutomaticCapitalizationNotification_SEGMENT) as MemorySegment
    set(value) = NSSpellCheckerDidChangeAutomaticCapitalizationNotification_VH.set(NSSpellCheckerDidChangeAutomaticCapitalizationNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification typedef const NSNotificationName = (Void)*
 */
private val NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification").orElseThrow() }
private val NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification_VH: VarHandle by lazy { NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification_LAYOUT.varHandle() }

var NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification: MemorySegment
    get() = NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification_VH.get(NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification_SEGMENT) as MemorySegment
    set(value) = NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification_VH.set(NSSpellCheckerDidChangeAutomaticPeriodSubstitutionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpellCheckerDidChangeAutomaticTextCompletionNotification typedef const NSNotificationName = (Void)*
 */
private val NSSpellCheckerDidChangeAutomaticTextCompletionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpellCheckerDidChangeAutomaticTextCompletionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpellCheckerDidChangeAutomaticTextCompletionNotification").orElseThrow() }
private val NSSpellCheckerDidChangeAutomaticTextCompletionNotification_VH: VarHandle by lazy { NSSpellCheckerDidChangeAutomaticTextCompletionNotification_LAYOUT.varHandle() }

var NSSpellCheckerDidChangeAutomaticTextCompletionNotification: MemorySegment
    get() = NSSpellCheckerDidChangeAutomaticTextCompletionNotification_VH.get(NSSpellCheckerDidChangeAutomaticTextCompletionNotification_SEGMENT) as MemorySegment
    set(value) = NSSpellCheckerDidChangeAutomaticTextCompletionNotification_VH.set(NSSpellCheckerDidChangeAutomaticTextCompletionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSpellCheckerDidChangeAutomaticInlinePredictionNotification typedef const NSNotificationName = (Void)*
 */
private val NSSpellCheckerDidChangeAutomaticInlinePredictionNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSpellCheckerDidChangeAutomaticInlinePredictionNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSpellCheckerDidChangeAutomaticInlinePredictionNotification").orElseThrow() }
private val NSSpellCheckerDidChangeAutomaticInlinePredictionNotification_VH: VarHandle by lazy { NSSpellCheckerDidChangeAutomaticInlinePredictionNotification_LAYOUT.varHandle() }

var NSSpellCheckerDidChangeAutomaticInlinePredictionNotification: MemorySegment
    get() = NSSpellCheckerDidChangeAutomaticInlinePredictionNotification_VH.get(NSSpellCheckerDidChangeAutomaticInlinePredictionNotification_SEGMENT) as MemorySegment
    set(value) = NSSpellCheckerDidChangeAutomaticInlinePredictionNotification_VH.set(NSSpellCheckerDidChangeAutomaticInlinePredictionNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSplitViewWillResizeSubviewsNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSSplitViewWillResizeSubviewsNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSplitViewWillResizeSubviewsNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSplitViewWillResizeSubviewsNotification").orElseThrow() }
private val NSSplitViewWillResizeSubviewsNotification_VH: VarHandle by lazy { NSSplitViewWillResizeSubviewsNotification_LAYOUT.varHandle() }

var NSSplitViewWillResizeSubviewsNotification: MemorySegment
    get() = NSSplitViewWillResizeSubviewsNotification_VH.get(NSSplitViewWillResizeSubviewsNotification_SEGMENT) as MemorySegment
    set(value) = NSSplitViewWillResizeSubviewsNotification_VH.set(NSSplitViewWillResizeSubviewsNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSplitViewDidResizeSubviewsNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSSplitViewDidResizeSubviewsNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSplitViewDidResizeSubviewsNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSplitViewDidResizeSubviewsNotification").orElseThrow() }
private val NSSplitViewDidResizeSubviewsNotification_VH: VarHandle by lazy { NSSplitViewDidResizeSubviewsNotification_LAYOUT.varHandle() }

var NSSplitViewDidResizeSubviewsNotification: MemorySegment
    get() = NSSplitViewDidResizeSubviewsNotification_VH.get(NSSplitViewDidResizeSubviewsNotification_SEGMENT) as MemorySegment
    set(value) = NSSplitViewDidResizeSubviewsNotification_VH.set(NSSplitViewDidResizeSubviewsNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSplitViewItemUnspecifiedDimension typedef const CGFloat = Double
 */
private val NSSplitViewItemUnspecifiedDimension_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSSplitViewItemUnspecifiedDimension_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSplitViewItemUnspecifiedDimension").orElseThrow() }
private val NSSplitViewItemUnspecifiedDimension_VH: VarHandle by lazy { NSSplitViewItemUnspecifiedDimension_LAYOUT.varHandle() }

var NSSplitViewItemUnspecifiedDimension: Double
    get() = NSSplitViewItemUnspecifiedDimension_VH.get(NSSplitViewItemUnspecifiedDimension_SEGMENT) as Double
    set(value) = NSSplitViewItemUnspecifiedDimension_VH.set(NSSplitViewItemUnspecifiedDimension_SEGMENT, value)

/**
 * {@snippet lang=c : NSSplitViewControllerAutomaticDimension typedef const CGFloat = Double
 */
private val NSSplitViewControllerAutomaticDimension_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSSplitViewControllerAutomaticDimension_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSplitViewControllerAutomaticDimension").orElseThrow() }
private val NSSplitViewControllerAutomaticDimension_VH: VarHandle by lazy { NSSplitViewControllerAutomaticDimension_LAYOUT.varHandle() }

var NSSplitViewControllerAutomaticDimension: Double
    get() = NSSplitViewControllerAutomaticDimension_VH.get(NSSplitViewControllerAutomaticDimension_SEGMENT) as Double
    set(value) = NSSplitViewControllerAutomaticDimension_VH.set(NSSplitViewControllerAutomaticDimension_SEGMENT, value)

/**
 * {@snippet lang=c : NSPopUpButtonCellWillPopUpNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSPopUpButtonCellWillPopUpNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPopUpButtonCellWillPopUpNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPopUpButtonCellWillPopUpNotification").orElseThrow() }
private val NSPopUpButtonCellWillPopUpNotification_VH: VarHandle by lazy { NSPopUpButtonCellWillPopUpNotification_LAYOUT.varHandle() }

var NSPopUpButtonCellWillPopUpNotification: MemorySegment
    get() = NSPopUpButtonCellWillPopUpNotification_VH.get(NSPopUpButtonCellWillPopUpNotification_SEGMENT) as MemorySegment
    set(value) = NSPopUpButtonCellWillPopUpNotification_VH.set(NSPopUpButtonCellWillPopUpNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSPopUpButtonWillPopUpNotification typedef NSNotificationName = typedef NSString = (Void)*
 */
private val NSPopUpButtonWillPopUpNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPopUpButtonWillPopUpNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPopUpButtonWillPopUpNotification").orElseThrow() }
private val NSPopUpButtonWillPopUpNotification_VH: VarHandle by lazy { NSPopUpButtonWillPopUpNotification_LAYOUT.varHandle() }

var NSPopUpButtonWillPopUpNotification: MemorySegment
    get() = NSPopUpButtonWillPopUpNotification_VH.get(NSPopUpButtonWillPopUpNotification_SEGMENT) as MemorySegment
    set(value) = NSPopUpButtonWillPopUpNotification_VH.set(NSPopUpButtonWillPopUpNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintOperationExistsException typedef NSExceptionName = typedef NSString = (Void)*
 */
private val NSPrintOperationExistsException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintOperationExistsException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintOperationExistsException").orElseThrow() }
private val NSPrintOperationExistsException_VH: VarHandle by lazy { NSPrintOperationExistsException_LAYOUT.varHandle() }

var NSPrintOperationExistsException: MemorySegment
    get() = NSPrintOperationExistsException_VH.get(NSPrintOperationExistsException_SEGMENT) as MemorySegment
    set(value) = NSPrintOperationExistsException_VH.set(NSPrintOperationExistsException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPhotoJobStyleHint typedef const NSPrintPanelJobStyleHint = (Void)*
 */
private val NSPrintPhotoJobStyleHint_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPhotoJobStyleHint_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPhotoJobStyleHint").orElseThrow() }
private val NSPrintPhotoJobStyleHint_VH: VarHandle by lazy { NSPrintPhotoJobStyleHint_LAYOUT.varHandle() }

var NSPrintPhotoJobStyleHint: MemorySegment
    get() = NSPrintPhotoJobStyleHint_VH.get(NSPrintPhotoJobStyleHint_SEGMENT) as MemorySegment
    set(value) = NSPrintPhotoJobStyleHint_VH.set(NSPrintPhotoJobStyleHint_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintAllPresetsJobStyleHint typedef const NSPrintPanelJobStyleHint = (Void)*
 */
private val NSPrintAllPresetsJobStyleHint_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintAllPresetsJobStyleHint_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintAllPresetsJobStyleHint").orElseThrow() }
private val NSPrintAllPresetsJobStyleHint_VH: VarHandle by lazy { NSPrintAllPresetsJobStyleHint_LAYOUT.varHandle() }

var NSPrintAllPresetsJobStyleHint: MemorySegment
    get() = NSPrintAllPresetsJobStyleHint_VH.get(NSPrintAllPresetsJobStyleHint_SEGMENT) as MemorySegment
    set(value) = NSPrintAllPresetsJobStyleHint_VH.set(NSPrintAllPresetsJobStyleHint_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintNoPresetsJobStyleHint typedef const NSPrintPanelJobStyleHint = (Void)*
 */
private val NSPrintNoPresetsJobStyleHint_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintNoPresetsJobStyleHint_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintNoPresetsJobStyleHint").orElseThrow() }
private val NSPrintNoPresetsJobStyleHint_VH: VarHandle by lazy { NSPrintNoPresetsJobStyleHint_LAYOUT.varHandle() }

var NSPrintNoPresetsJobStyleHint: MemorySegment
    get() = NSPrintNoPresetsJobStyleHint_VH.get(NSPrintNoPresetsJobStyleHint_SEGMENT) as MemorySegment
    set(value) = NSPrintNoPresetsJobStyleHint_VH.set(NSPrintNoPresetsJobStyleHint_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPanelAccessorySummaryItemNameKey typedef const NSPrintPanelAccessorySummaryKey = (Void)*
 */
private val NSPrintPanelAccessorySummaryItemNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPanelAccessorySummaryItemNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPanelAccessorySummaryItemNameKey").orElseThrow() }
private val NSPrintPanelAccessorySummaryItemNameKey_VH: VarHandle by lazy { NSPrintPanelAccessorySummaryItemNameKey_LAYOUT.varHandle() }

var NSPrintPanelAccessorySummaryItemNameKey: MemorySegment
    get() = NSPrintPanelAccessorySummaryItemNameKey_VH.get(NSPrintPanelAccessorySummaryItemNameKey_SEGMENT) as MemorySegment
    set(value) = NSPrintPanelAccessorySummaryItemNameKey_VH.set(NSPrintPanelAccessorySummaryItemNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSPrintPanelAccessorySummaryItemDescriptionKey typedef const NSPrintPanelAccessorySummaryKey = (Void)*
 */
private val NSPrintPanelAccessorySummaryItemDescriptionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPrintPanelAccessorySummaryItemDescriptionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPrintPanelAccessorySummaryItemDescriptionKey").orElseThrow() }
private val NSPrintPanelAccessorySummaryItemDescriptionKey_VH: VarHandle by lazy { NSPrintPanelAccessorySummaryItemDescriptionKey_LAYOUT.varHandle() }

var NSPrintPanelAccessorySummaryItemDescriptionKey: MemorySegment
    get() = NSPrintPanelAccessorySummaryItemDescriptionKey_VH.get(NSPrintPanelAccessorySummaryItemDescriptionKey_SEGMENT) as MemorySegment
    set(value) = NSPrintPanelAccessorySummaryItemDescriptionKey_VH.set(NSPrintPanelAccessorySummaryItemDescriptionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSScreenColorSpaceDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSScreenColorSpaceDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSScreenColorSpaceDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSScreenColorSpaceDidChangeNotification").orElseThrow() }
private val NSScreenColorSpaceDidChangeNotification_VH: VarHandle by lazy { NSScreenColorSpaceDidChangeNotification_LAYOUT.varHandle() }

var NSScreenColorSpaceDidChangeNotification: MemorySegment
    get() = NSScreenColorSpaceDidChangeNotification_VH.get(NSScreenColorSpaceDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSScreenColorSpaceDidChangeNotification_VH.set(NSScreenColorSpaceDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSPreferredScrollerStyleDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSPreferredScrollerStyleDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPreferredScrollerStyleDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPreferredScrollerStyleDidChangeNotification").orElseThrow() }
private val NSPreferredScrollerStyleDidChangeNotification_VH: VarHandle by lazy { NSPreferredScrollerStyleDidChangeNotification_LAYOUT.varHandle() }

var NSPreferredScrollerStyleDidChangeNotification: MemorySegment
    get() = NSPreferredScrollerStyleDidChangeNotification_VH.get(NSPreferredScrollerStyleDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSPreferredScrollerStyleDidChangeNotification_VH.set(NSPreferredScrollerStyleDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextFinderCaseInsensitiveKey typedef const NSPasteboardTypeTextFinderOptionKey = (Void)*
 */
private val NSTextFinderCaseInsensitiveKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextFinderCaseInsensitiveKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextFinderCaseInsensitiveKey").orElseThrow() }
private val NSTextFinderCaseInsensitiveKey_VH: VarHandle by lazy { NSTextFinderCaseInsensitiveKey_LAYOUT.varHandle() }

var NSTextFinderCaseInsensitiveKey: MemorySegment
    get() = NSTextFinderCaseInsensitiveKey_VH.get(NSTextFinderCaseInsensitiveKey_SEGMENT) as MemorySegment
    set(value) = NSTextFinderCaseInsensitiveKey_VH.set(NSTextFinderCaseInsensitiveKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextFinderMatchingTypeKey typedef const NSPasteboardTypeTextFinderOptionKey = (Void)*
 */
private val NSTextFinderMatchingTypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextFinderMatchingTypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextFinderMatchingTypeKey").orElseThrow() }
private val NSTextFinderMatchingTypeKey_VH: VarHandle by lazy { NSTextFinderMatchingTypeKey_LAYOUT.varHandle() }

var NSTextFinderMatchingTypeKey: MemorySegment
    get() = NSTextFinderMatchingTypeKey_VH.get(NSTextFinderMatchingTypeKey_SEGMENT) as MemorySegment
    set(value) = NSTextFinderMatchingTypeKey_VH.set(NSTextFinderMatchingTypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSScrollViewWillStartLiveMagnifyNotification typedef const NSNotificationName = (Void)*
 */
private val NSScrollViewWillStartLiveMagnifyNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSScrollViewWillStartLiveMagnifyNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSScrollViewWillStartLiveMagnifyNotification").orElseThrow() }
private val NSScrollViewWillStartLiveMagnifyNotification_VH: VarHandle by lazy { NSScrollViewWillStartLiveMagnifyNotification_LAYOUT.varHandle() }

var NSScrollViewWillStartLiveMagnifyNotification: MemorySegment
    get() = NSScrollViewWillStartLiveMagnifyNotification_VH.get(NSScrollViewWillStartLiveMagnifyNotification_SEGMENT) as MemorySegment
    set(value) = NSScrollViewWillStartLiveMagnifyNotification_VH.set(NSScrollViewWillStartLiveMagnifyNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSScrollViewDidEndLiveMagnifyNotification typedef const NSNotificationName = (Void)*
 */
private val NSScrollViewDidEndLiveMagnifyNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSScrollViewDidEndLiveMagnifyNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSScrollViewDidEndLiveMagnifyNotification").orElseThrow() }
private val NSScrollViewDidEndLiveMagnifyNotification_VH: VarHandle by lazy { NSScrollViewDidEndLiveMagnifyNotification_LAYOUT.varHandle() }

var NSScrollViewDidEndLiveMagnifyNotification: MemorySegment
    get() = NSScrollViewDidEndLiveMagnifyNotification_VH.get(NSScrollViewDidEndLiveMagnifyNotification_SEGMENT) as MemorySegment
    set(value) = NSScrollViewDidEndLiveMagnifyNotification_VH.set(NSScrollViewDidEndLiveMagnifyNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSScrollViewWillStartLiveScrollNotification typedef const NSNotificationName = (Void)*
 */
private val NSScrollViewWillStartLiveScrollNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSScrollViewWillStartLiveScrollNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSScrollViewWillStartLiveScrollNotification").orElseThrow() }
private val NSScrollViewWillStartLiveScrollNotification_VH: VarHandle by lazy { NSScrollViewWillStartLiveScrollNotification_LAYOUT.varHandle() }

var NSScrollViewWillStartLiveScrollNotification: MemorySegment
    get() = NSScrollViewWillStartLiveScrollNotification_VH.get(NSScrollViewWillStartLiveScrollNotification_SEGMENT) as MemorySegment
    set(value) = NSScrollViewWillStartLiveScrollNotification_VH.set(NSScrollViewWillStartLiveScrollNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSScrollViewDidLiveScrollNotification typedef const NSNotificationName = (Void)*
 */
private val NSScrollViewDidLiveScrollNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSScrollViewDidLiveScrollNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSScrollViewDidLiveScrollNotification").orElseThrow() }
private val NSScrollViewDidLiveScrollNotification_VH: VarHandle by lazy { NSScrollViewDidLiveScrollNotification_LAYOUT.varHandle() }

var NSScrollViewDidLiveScrollNotification: MemorySegment
    get() = NSScrollViewDidLiveScrollNotification_VH.get(NSScrollViewDidLiveScrollNotification_SEGMENT) as MemorySegment
    set(value) = NSScrollViewDidLiveScrollNotification_VH.set(NSScrollViewDidLiveScrollNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSScrollViewDidEndLiveScrollNotification typedef const NSNotificationName = (Void)*
 */
private val NSScrollViewDidEndLiveScrollNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSScrollViewDidEndLiveScrollNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSScrollViewDidEndLiveScrollNotification").orElseThrow() }
private val NSScrollViewDidEndLiveScrollNotification_VH: VarHandle by lazy { NSScrollViewDidEndLiveScrollNotification_LAYOUT.varHandle() }

var NSScrollViewDidEndLiveScrollNotification: MemorySegment
    get() = NSScrollViewDidEndLiveScrollNotification_VH.get(NSScrollViewDidEndLiveScrollNotification_SEGMENT) as MemorySegment
    set(value) = NSScrollViewDidEndLiveScrollNotification_VH.set(NSScrollViewDidEndLiveScrollNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSGridViewSizeForContent typedef const CGFloat = Double
 */
private val NSGridViewSizeForContent_LAYOUT: ValueLayout by lazy { ValueLayout.JAVA_DOUBLE }
private val NSGridViewSizeForContent_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGridViewSizeForContent").orElseThrow() }
private val NSGridViewSizeForContent_VH: VarHandle by lazy { NSGridViewSizeForContent_LAYOUT.varHandle() }

var NSGridViewSizeForContent: Double
    get() = NSGridViewSizeForContent_VH.get(NSGridViewSizeForContent_SEGMENT) as Double
    set(value) = NSGridViewSizeForContent_VH.set(NSGridViewSizeForContent_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeUsername typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeUsername_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeUsername_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeUsername").orElseThrow() }
private val NSTextContentTypeUsername_VH: VarHandle by lazy { NSTextContentTypeUsername_LAYOUT.varHandle() }

var NSTextContentTypeUsername: MemorySegment
    get() = NSTextContentTypeUsername_VH.get(NSTextContentTypeUsername_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeUsername_VH.set(NSTextContentTypeUsername_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypePassword typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypePassword_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypePassword_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypePassword").orElseThrow() }
private val NSTextContentTypePassword_VH: VarHandle by lazy { NSTextContentTypePassword_LAYOUT.varHandle() }

var NSTextContentTypePassword: MemorySegment
    get() = NSTextContentTypePassword_VH.get(NSTextContentTypePassword_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypePassword_VH.set(NSTextContentTypePassword_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeOneTimeCode typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeOneTimeCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeOneTimeCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeOneTimeCode").orElseThrow() }
private val NSTextContentTypeOneTimeCode_VH: VarHandle by lazy { NSTextContentTypeOneTimeCode_LAYOUT.varHandle() }

var NSTextContentTypeOneTimeCode: MemorySegment
    get() = NSTextContentTypeOneTimeCode_VH.get(NSTextContentTypeOneTimeCode_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeOneTimeCode_VH.set(NSTextContentTypeOneTimeCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeNewPassword typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeNewPassword_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeNewPassword_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeNewPassword").orElseThrow() }
private val NSTextContentTypeNewPassword_VH: VarHandle by lazy { NSTextContentTypeNewPassword_LAYOUT.varHandle() }

var NSTextContentTypeNewPassword: MemorySegment
    get() = NSTextContentTypeNewPassword_VH.get(NSTextContentTypeNewPassword_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeNewPassword_VH.set(NSTextContentTypeNewPassword_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeName").orElseThrow() }
private val NSTextContentTypeName_VH: VarHandle by lazy { NSTextContentTypeName_LAYOUT.varHandle() }

var NSTextContentTypeName: MemorySegment
    get() = NSTextContentTypeName_VH.get(NSTextContentTypeName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeName_VH.set(NSTextContentTypeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeNamePrefix typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeNamePrefix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeNamePrefix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeNamePrefix").orElseThrow() }
private val NSTextContentTypeNamePrefix_VH: VarHandle by lazy { NSTextContentTypeNamePrefix_LAYOUT.varHandle() }

var NSTextContentTypeNamePrefix: MemorySegment
    get() = NSTextContentTypeNamePrefix_VH.get(NSTextContentTypeNamePrefix_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeNamePrefix_VH.set(NSTextContentTypeNamePrefix_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeGivenName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeGivenName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeGivenName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeGivenName").orElseThrow() }
private val NSTextContentTypeGivenName_VH: VarHandle by lazy { NSTextContentTypeGivenName_LAYOUT.varHandle() }

var NSTextContentTypeGivenName: MemorySegment
    get() = NSTextContentTypeGivenName_VH.get(NSTextContentTypeGivenName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeGivenName_VH.set(NSTextContentTypeGivenName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeMiddleName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeMiddleName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeMiddleName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeMiddleName").orElseThrow() }
private val NSTextContentTypeMiddleName_VH: VarHandle by lazy { NSTextContentTypeMiddleName_LAYOUT.varHandle() }

var NSTextContentTypeMiddleName: MemorySegment
    get() = NSTextContentTypeMiddleName_VH.get(NSTextContentTypeMiddleName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeMiddleName_VH.set(NSTextContentTypeMiddleName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeFamilyName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeFamilyName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeFamilyName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeFamilyName").orElseThrow() }
private val NSTextContentTypeFamilyName_VH: VarHandle by lazy { NSTextContentTypeFamilyName_LAYOUT.varHandle() }

var NSTextContentTypeFamilyName: MemorySegment
    get() = NSTextContentTypeFamilyName_VH.get(NSTextContentTypeFamilyName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeFamilyName_VH.set(NSTextContentTypeFamilyName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeNameSuffix typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeNameSuffix_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeNameSuffix_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeNameSuffix").orElseThrow() }
private val NSTextContentTypeNameSuffix_VH: VarHandle by lazy { NSTextContentTypeNameSuffix_LAYOUT.varHandle() }

var NSTextContentTypeNameSuffix: MemorySegment
    get() = NSTextContentTypeNameSuffix_VH.get(NSTextContentTypeNameSuffix_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeNameSuffix_VH.set(NSTextContentTypeNameSuffix_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeNickname typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeNickname_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeNickname_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeNickname").orElseThrow() }
private val NSTextContentTypeNickname_VH: VarHandle by lazy { NSTextContentTypeNickname_LAYOUT.varHandle() }

var NSTextContentTypeNickname: MemorySegment
    get() = NSTextContentTypeNickname_VH.get(NSTextContentTypeNickname_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeNickname_VH.set(NSTextContentTypeNickname_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeJobTitle typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeJobTitle_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeJobTitle_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeJobTitle").orElseThrow() }
private val NSTextContentTypeJobTitle_VH: VarHandle by lazy { NSTextContentTypeJobTitle_LAYOUT.varHandle() }

var NSTextContentTypeJobTitle: MemorySegment
    get() = NSTextContentTypeJobTitle_VH.get(NSTextContentTypeJobTitle_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeJobTitle_VH.set(NSTextContentTypeJobTitle_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeOrganizationName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeOrganizationName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeOrganizationName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeOrganizationName").orElseThrow() }
private val NSTextContentTypeOrganizationName_VH: VarHandle by lazy { NSTextContentTypeOrganizationName_LAYOUT.varHandle() }

var NSTextContentTypeOrganizationName: MemorySegment
    get() = NSTextContentTypeOrganizationName_VH.get(NSTextContentTypeOrganizationName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeOrganizationName_VH.set(NSTextContentTypeOrganizationName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeLocation typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeLocation_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeLocation_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeLocation").orElseThrow() }
private val NSTextContentTypeLocation_VH: VarHandle by lazy { NSTextContentTypeLocation_LAYOUT.varHandle() }

var NSTextContentTypeLocation: MemorySegment
    get() = NSTextContentTypeLocation_VH.get(NSTextContentTypeLocation_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeLocation_VH.set(NSTextContentTypeLocation_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeFullStreetAddress typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeFullStreetAddress_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeFullStreetAddress_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeFullStreetAddress").orElseThrow() }
private val NSTextContentTypeFullStreetAddress_VH: VarHandle by lazy { NSTextContentTypeFullStreetAddress_LAYOUT.varHandle() }

var NSTextContentTypeFullStreetAddress: MemorySegment
    get() = NSTextContentTypeFullStreetAddress_VH.get(NSTextContentTypeFullStreetAddress_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeFullStreetAddress_VH.set(NSTextContentTypeFullStreetAddress_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeStreetAddressLine1 typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeStreetAddressLine1_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeStreetAddressLine1_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeStreetAddressLine1").orElseThrow() }
private val NSTextContentTypeStreetAddressLine1_VH: VarHandle by lazy { NSTextContentTypeStreetAddressLine1_LAYOUT.varHandle() }

var NSTextContentTypeStreetAddressLine1: MemorySegment
    get() = NSTextContentTypeStreetAddressLine1_VH.get(NSTextContentTypeStreetAddressLine1_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeStreetAddressLine1_VH.set(NSTextContentTypeStreetAddressLine1_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeStreetAddressLine2 typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeStreetAddressLine2_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeStreetAddressLine2_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeStreetAddressLine2").orElseThrow() }
private val NSTextContentTypeStreetAddressLine2_VH: VarHandle by lazy { NSTextContentTypeStreetAddressLine2_LAYOUT.varHandle() }

var NSTextContentTypeStreetAddressLine2: MemorySegment
    get() = NSTextContentTypeStreetAddressLine2_VH.get(NSTextContentTypeStreetAddressLine2_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeStreetAddressLine2_VH.set(NSTextContentTypeStreetAddressLine2_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeAddressCity typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeAddressCity_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeAddressCity_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeAddressCity").orElseThrow() }
private val NSTextContentTypeAddressCity_VH: VarHandle by lazy { NSTextContentTypeAddressCity_LAYOUT.varHandle() }

var NSTextContentTypeAddressCity: MemorySegment
    get() = NSTextContentTypeAddressCity_VH.get(NSTextContentTypeAddressCity_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeAddressCity_VH.set(NSTextContentTypeAddressCity_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeAddressState typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeAddressState_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeAddressState_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeAddressState").orElseThrow() }
private val NSTextContentTypeAddressState_VH: VarHandle by lazy { NSTextContentTypeAddressState_LAYOUT.varHandle() }

var NSTextContentTypeAddressState: MemorySegment
    get() = NSTextContentTypeAddressState_VH.get(NSTextContentTypeAddressState_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeAddressState_VH.set(NSTextContentTypeAddressState_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeAddressCityAndState typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeAddressCityAndState_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeAddressCityAndState_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeAddressCityAndState").orElseThrow() }
private val NSTextContentTypeAddressCityAndState_VH: VarHandle by lazy { NSTextContentTypeAddressCityAndState_LAYOUT.varHandle() }

var NSTextContentTypeAddressCityAndState: MemorySegment
    get() = NSTextContentTypeAddressCityAndState_VH.get(NSTextContentTypeAddressCityAndState_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeAddressCityAndState_VH.set(NSTextContentTypeAddressCityAndState_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeSublocality typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeSublocality_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeSublocality_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeSublocality").orElseThrow() }
private val NSTextContentTypeSublocality_VH: VarHandle by lazy { NSTextContentTypeSublocality_LAYOUT.varHandle() }

var NSTextContentTypeSublocality: MemorySegment
    get() = NSTextContentTypeSublocality_VH.get(NSTextContentTypeSublocality_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeSublocality_VH.set(NSTextContentTypeSublocality_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCountryName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCountryName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCountryName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCountryName").orElseThrow() }
private val NSTextContentTypeCountryName_VH: VarHandle by lazy { NSTextContentTypeCountryName_LAYOUT.varHandle() }

var NSTextContentTypeCountryName: MemorySegment
    get() = NSTextContentTypeCountryName_VH.get(NSTextContentTypeCountryName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCountryName_VH.set(NSTextContentTypeCountryName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypePostalCode typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypePostalCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypePostalCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypePostalCode").orElseThrow() }
private val NSTextContentTypePostalCode_VH: VarHandle by lazy { NSTextContentTypePostalCode_LAYOUT.varHandle() }

var NSTextContentTypePostalCode: MemorySegment
    get() = NSTextContentTypePostalCode_VH.get(NSTextContentTypePostalCode_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypePostalCode_VH.set(NSTextContentTypePostalCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeTelephoneNumber typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeTelephoneNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeTelephoneNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeTelephoneNumber").orElseThrow() }
private val NSTextContentTypeTelephoneNumber_VH: VarHandle by lazy { NSTextContentTypeTelephoneNumber_LAYOUT.varHandle() }

var NSTextContentTypeTelephoneNumber: MemorySegment
    get() = NSTextContentTypeTelephoneNumber_VH.get(NSTextContentTypeTelephoneNumber_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeTelephoneNumber_VH.set(NSTextContentTypeTelephoneNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeEmailAddress typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeEmailAddress_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeEmailAddress_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeEmailAddress").orElseThrow() }
private val NSTextContentTypeEmailAddress_VH: VarHandle by lazy { NSTextContentTypeEmailAddress_LAYOUT.varHandle() }

var NSTextContentTypeEmailAddress: MemorySegment
    get() = NSTextContentTypeEmailAddress_VH.get(NSTextContentTypeEmailAddress_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeEmailAddress_VH.set(NSTextContentTypeEmailAddress_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeURL typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeURL_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeURL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeURL").orElseThrow() }
private val NSTextContentTypeURL_VH: VarHandle by lazy { NSTextContentTypeURL_LAYOUT.varHandle() }

var NSTextContentTypeURL: MemorySegment
    get() = NSTextContentTypeURL_VH.get(NSTextContentTypeURL_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeURL_VH.set(NSTextContentTypeURL_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardNumber typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardNumber").orElseThrow() }
private val NSTextContentTypeCreditCardNumber_VH: VarHandle by lazy { NSTextContentTypeCreditCardNumber_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardNumber: MemorySegment
    get() = NSTextContentTypeCreditCardNumber_VH.get(NSTextContentTypeCreditCardNumber_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardNumber_VH.set(NSTextContentTypeCreditCardNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardName").orElseThrow() }
private val NSTextContentTypeCreditCardName_VH: VarHandle by lazy { NSTextContentTypeCreditCardName_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardName: MemorySegment
    get() = NSTextContentTypeCreditCardName_VH.get(NSTextContentTypeCreditCardName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardName_VH.set(NSTextContentTypeCreditCardName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardGivenName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardGivenName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardGivenName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardGivenName").orElseThrow() }
private val NSTextContentTypeCreditCardGivenName_VH: VarHandle by lazy { NSTextContentTypeCreditCardGivenName_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardGivenName: MemorySegment
    get() = NSTextContentTypeCreditCardGivenName_VH.get(NSTextContentTypeCreditCardGivenName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardGivenName_VH.set(NSTextContentTypeCreditCardGivenName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardMiddleName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardMiddleName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardMiddleName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardMiddleName").orElseThrow() }
private val NSTextContentTypeCreditCardMiddleName_VH: VarHandle by lazy { NSTextContentTypeCreditCardMiddleName_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardMiddleName: MemorySegment
    get() = NSTextContentTypeCreditCardMiddleName_VH.get(NSTextContentTypeCreditCardMiddleName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardMiddleName_VH.set(NSTextContentTypeCreditCardMiddleName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardFamilyName typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardFamilyName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardFamilyName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardFamilyName").orElseThrow() }
private val NSTextContentTypeCreditCardFamilyName_VH: VarHandle by lazy { NSTextContentTypeCreditCardFamilyName_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardFamilyName: MemorySegment
    get() = NSTextContentTypeCreditCardFamilyName_VH.get(NSTextContentTypeCreditCardFamilyName_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardFamilyName_VH.set(NSTextContentTypeCreditCardFamilyName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardSecurityCode typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardSecurityCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardSecurityCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardSecurityCode").orElseThrow() }
private val NSTextContentTypeCreditCardSecurityCode_VH: VarHandle by lazy { NSTextContentTypeCreditCardSecurityCode_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardSecurityCode: MemorySegment
    get() = NSTextContentTypeCreditCardSecurityCode_VH.get(NSTextContentTypeCreditCardSecurityCode_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardSecurityCode_VH.set(NSTextContentTypeCreditCardSecurityCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardExpiration typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardExpiration_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardExpiration_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardExpiration").orElseThrow() }
private val NSTextContentTypeCreditCardExpiration_VH: VarHandle by lazy { NSTextContentTypeCreditCardExpiration_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardExpiration: MemorySegment
    get() = NSTextContentTypeCreditCardExpiration_VH.get(NSTextContentTypeCreditCardExpiration_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardExpiration_VH.set(NSTextContentTypeCreditCardExpiration_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardExpirationMonth typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardExpirationMonth_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardExpirationMonth_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardExpirationMonth").orElseThrow() }
private val NSTextContentTypeCreditCardExpirationMonth_VH: VarHandle by lazy { NSTextContentTypeCreditCardExpirationMonth_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardExpirationMonth: MemorySegment
    get() = NSTextContentTypeCreditCardExpirationMonth_VH.get(NSTextContentTypeCreditCardExpirationMonth_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardExpirationMonth_VH.set(NSTextContentTypeCreditCardExpirationMonth_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardExpirationYear typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardExpirationYear_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardExpirationYear_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardExpirationYear").orElseThrow() }
private val NSTextContentTypeCreditCardExpirationYear_VH: VarHandle by lazy { NSTextContentTypeCreditCardExpirationYear_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardExpirationYear: MemorySegment
    get() = NSTextContentTypeCreditCardExpirationYear_VH.get(NSTextContentTypeCreditCardExpirationYear_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardExpirationYear_VH.set(NSTextContentTypeCreditCardExpirationYear_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeCreditCardType typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeCreditCardType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeCreditCardType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeCreditCardType").orElseThrow() }
private val NSTextContentTypeCreditCardType_VH: VarHandle by lazy { NSTextContentTypeCreditCardType_LAYOUT.varHandle() }

var NSTextContentTypeCreditCardType: MemorySegment
    get() = NSTextContentTypeCreditCardType_VH.get(NSTextContentTypeCreditCardType_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeCreditCardType_VH.set(NSTextContentTypeCreditCardType_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeShipmentTrackingNumber typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeShipmentTrackingNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeShipmentTrackingNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeShipmentTrackingNumber").orElseThrow() }
private val NSTextContentTypeShipmentTrackingNumber_VH: VarHandle by lazy { NSTextContentTypeShipmentTrackingNumber_LAYOUT.varHandle() }

var NSTextContentTypeShipmentTrackingNumber: MemorySegment
    get() = NSTextContentTypeShipmentTrackingNumber_VH.get(NSTextContentTypeShipmentTrackingNumber_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeShipmentTrackingNumber_VH.set(NSTextContentTypeShipmentTrackingNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeFlightNumber typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeFlightNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeFlightNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeFlightNumber").orElseThrow() }
private val NSTextContentTypeFlightNumber_VH: VarHandle by lazy { NSTextContentTypeFlightNumber_LAYOUT.varHandle() }

var NSTextContentTypeFlightNumber: MemorySegment
    get() = NSTextContentTypeFlightNumber_VH.get(NSTextContentTypeFlightNumber_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeFlightNumber_VH.set(NSTextContentTypeFlightNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeDateTime typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeDateTime_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeDateTime_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeDateTime").orElseThrow() }
private val NSTextContentTypeDateTime_VH: VarHandle by lazy { NSTextContentTypeDateTime_LAYOUT.varHandle() }

var NSTextContentTypeDateTime: MemorySegment
    get() = NSTextContentTypeDateTime_VH.get(NSTextContentTypeDateTime_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeDateTime_VH.set(NSTextContentTypeDateTime_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeBirthdate typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeBirthdate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeBirthdate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeBirthdate").orElseThrow() }
private val NSTextContentTypeBirthdate_VH: VarHandle by lazy { NSTextContentTypeBirthdate_LAYOUT.varHandle() }

var NSTextContentTypeBirthdate: MemorySegment
    get() = NSTextContentTypeBirthdate_VH.get(NSTextContentTypeBirthdate_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeBirthdate_VH.set(NSTextContentTypeBirthdate_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeBirthdateDay typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeBirthdateDay_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeBirthdateDay_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeBirthdateDay").orElseThrow() }
private val NSTextContentTypeBirthdateDay_VH: VarHandle by lazy { NSTextContentTypeBirthdateDay_LAYOUT.varHandle() }

var NSTextContentTypeBirthdateDay: MemorySegment
    get() = NSTextContentTypeBirthdateDay_VH.get(NSTextContentTypeBirthdateDay_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeBirthdateDay_VH.set(NSTextContentTypeBirthdateDay_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeBirthdateMonth typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeBirthdateMonth_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeBirthdateMonth_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeBirthdateMonth").orElseThrow() }
private val NSTextContentTypeBirthdateMonth_VH: VarHandle by lazy { NSTextContentTypeBirthdateMonth_LAYOUT.varHandle() }

var NSTextContentTypeBirthdateMonth: MemorySegment
    get() = NSTextContentTypeBirthdateMonth_VH.get(NSTextContentTypeBirthdateMonth_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeBirthdateMonth_VH.set(NSTextContentTypeBirthdateMonth_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextContentTypeBirthdateYear typedef const NSTextContentType = (Void)*
 */
private val NSTextContentTypeBirthdateYear_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextContentTypeBirthdateYear_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextContentTypeBirthdateYear").orElseThrow() }
private val NSTextContentTypeBirthdateYear_VH: VarHandle by lazy { NSTextContentTypeBirthdateYear_LAYOUT.varHandle() }

var NSTextContentTypeBirthdateYear: MemorySegment
    get() = NSTextContentTypeBirthdateYear_VH.get(NSTextContentTypeBirthdateYear_SEGMENT) as MemorySegment
    set(value) = NSTextContentTypeBirthdateYear_VH.set(NSTextContentTypeBirthdateYear_SEGMENT, value)

/**
 * {@snippet lang=c : NSFontAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSFontAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFontAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFontAttributeName").orElseThrow() }
private val NSFontAttributeName_VH: VarHandle by lazy { NSFontAttributeName_LAYOUT.varHandle() }

var NSFontAttributeName: MemorySegment
    get() = NSFontAttributeName_VH.get(NSFontAttributeName_SEGMENT) as MemorySegment
    set(value) = NSFontAttributeName_VH.set(NSFontAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSParagraphStyleAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSParagraphStyleAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSParagraphStyleAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSParagraphStyleAttributeName").orElseThrow() }
private val NSParagraphStyleAttributeName_VH: VarHandle by lazy { NSParagraphStyleAttributeName_LAYOUT.varHandle() }

var NSParagraphStyleAttributeName: MemorySegment
    get() = NSParagraphStyleAttributeName_VH.get(NSParagraphStyleAttributeName_SEGMENT) as MemorySegment
    set(value) = NSParagraphStyleAttributeName_VH.set(NSParagraphStyleAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSForegroundColorAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSForegroundColorAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSForegroundColorAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSForegroundColorAttributeName").orElseThrow() }
private val NSForegroundColorAttributeName_VH: VarHandle by lazy { NSForegroundColorAttributeName_LAYOUT.varHandle() }

var NSForegroundColorAttributeName: MemorySegment
    get() = NSForegroundColorAttributeName_VH.get(NSForegroundColorAttributeName_SEGMENT) as MemorySegment
    set(value) = NSForegroundColorAttributeName_VH.set(NSForegroundColorAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSBackgroundColorAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSBackgroundColorAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBackgroundColorAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBackgroundColorAttributeName").orElseThrow() }
private val NSBackgroundColorAttributeName_VH: VarHandle by lazy { NSBackgroundColorAttributeName_LAYOUT.varHandle() }

var NSBackgroundColorAttributeName: MemorySegment
    get() = NSBackgroundColorAttributeName_VH.get(NSBackgroundColorAttributeName_SEGMENT) as MemorySegment
    set(value) = NSBackgroundColorAttributeName_VH.set(NSBackgroundColorAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSLigatureAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSLigatureAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLigatureAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLigatureAttributeName").orElseThrow() }
private val NSLigatureAttributeName_VH: VarHandle by lazy { NSLigatureAttributeName_LAYOUT.varHandle() }

var NSLigatureAttributeName: MemorySegment
    get() = NSLigatureAttributeName_VH.get(NSLigatureAttributeName_SEGMENT) as MemorySegment
    set(value) = NSLigatureAttributeName_VH.set(NSLigatureAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSKernAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSKernAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSKernAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSKernAttributeName").orElseThrow() }
private val NSKernAttributeName_VH: VarHandle by lazy { NSKernAttributeName_LAYOUT.varHandle() }

var NSKernAttributeName: MemorySegment
    get() = NSKernAttributeName_VH.get(NSKernAttributeName_SEGMENT) as MemorySegment
    set(value) = NSKernAttributeName_VH.set(NSKernAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTrackingAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSTrackingAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTrackingAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTrackingAttributeName").orElseThrow() }
private val NSTrackingAttributeName_VH: VarHandle by lazy { NSTrackingAttributeName_LAYOUT.varHandle() }

var NSTrackingAttributeName: MemorySegment
    get() = NSTrackingAttributeName_VH.get(NSTrackingAttributeName_SEGMENT) as MemorySegment
    set(value) = NSTrackingAttributeName_VH.set(NSTrackingAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSStrikethroughStyleAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSStrikethroughStyleAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStrikethroughStyleAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStrikethroughStyleAttributeName").orElseThrow() }
private val NSStrikethroughStyleAttributeName_VH: VarHandle by lazy { NSStrikethroughStyleAttributeName_LAYOUT.varHandle() }

var NSStrikethroughStyleAttributeName: MemorySegment
    get() = NSStrikethroughStyleAttributeName_VH.get(NSStrikethroughStyleAttributeName_SEGMENT) as MemorySegment
    set(value) = NSStrikethroughStyleAttributeName_VH.set(NSStrikethroughStyleAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSUnderlineStyleAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSUnderlineStyleAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUnderlineStyleAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUnderlineStyleAttributeName").orElseThrow() }
private val NSUnderlineStyleAttributeName_VH: VarHandle by lazy { NSUnderlineStyleAttributeName_LAYOUT.varHandle() }

var NSUnderlineStyleAttributeName: MemorySegment
    get() = NSUnderlineStyleAttributeName_VH.get(NSUnderlineStyleAttributeName_SEGMENT) as MemorySegment
    set(value) = NSUnderlineStyleAttributeName_VH.set(NSUnderlineStyleAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSStrokeColorAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSStrokeColorAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStrokeColorAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStrokeColorAttributeName").orElseThrow() }
private val NSStrokeColorAttributeName_VH: VarHandle by lazy { NSStrokeColorAttributeName_LAYOUT.varHandle() }

var NSStrokeColorAttributeName: MemorySegment
    get() = NSStrokeColorAttributeName_VH.get(NSStrokeColorAttributeName_SEGMENT) as MemorySegment
    set(value) = NSStrokeColorAttributeName_VH.set(NSStrokeColorAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSStrokeWidthAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSStrokeWidthAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStrokeWidthAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStrokeWidthAttributeName").orElseThrow() }
private val NSStrokeWidthAttributeName_VH: VarHandle by lazy { NSStrokeWidthAttributeName_LAYOUT.varHandle() }

var NSStrokeWidthAttributeName: MemorySegment
    get() = NSStrokeWidthAttributeName_VH.get(NSStrokeWidthAttributeName_SEGMENT) as MemorySegment
    set(value) = NSStrokeWidthAttributeName_VH.set(NSStrokeWidthAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSShadowAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSShadowAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSShadowAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSShadowAttributeName").orElseThrow() }
private val NSShadowAttributeName_VH: VarHandle by lazy { NSShadowAttributeName_LAYOUT.varHandle() }

var NSShadowAttributeName: MemorySegment
    get() = NSShadowAttributeName_VH.get(NSShadowAttributeName_SEGMENT) as MemorySegment
    set(value) = NSShadowAttributeName_VH.set(NSShadowAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextEffectAttributeName typedef const NSAttributedStringKey = (Void)*
 */
private val NSTextEffectAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextEffectAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextEffectAttributeName").orElseThrow() }
private val NSTextEffectAttributeName_VH: VarHandle by lazy { NSTextEffectAttributeName_LAYOUT.varHandle() }

var NSTextEffectAttributeName: MemorySegment
    get() = NSTextEffectAttributeName_VH.get(NSTextEffectAttributeName_SEGMENT) as MemorySegment
    set(value) = NSTextEffectAttributeName_VH.set(NSTextEffectAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSAttachmentAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSAttachmentAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAttachmentAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAttachmentAttributeName").orElseThrow() }
private val NSAttachmentAttributeName_VH: VarHandle by lazy { NSAttachmentAttributeName_LAYOUT.varHandle() }

var NSAttachmentAttributeName: MemorySegment
    get() = NSAttachmentAttributeName_VH.get(NSAttachmentAttributeName_SEGMENT) as MemorySegment
    set(value) = NSAttachmentAttributeName_VH.set(NSAttachmentAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSLinkAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSLinkAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSLinkAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSLinkAttributeName").orElseThrow() }
private val NSLinkAttributeName_VH: VarHandle by lazy { NSLinkAttributeName_LAYOUT.varHandle() }

var NSLinkAttributeName: MemorySegment
    get() = NSLinkAttributeName_VH.get(NSLinkAttributeName_SEGMENT) as MemorySegment
    set(value) = NSLinkAttributeName_VH.set(NSLinkAttributeName_SEGMENT, value)

/**
 * {@snippet lang=c : NSBaselineOffsetAttributeName typedef NSAttributedStringKey = typedef NSString = (Void)*
 */
private val NSBaselineOffsetAttributeName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSBaselineOffsetAttributeName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSBaselineOffsetAttributeName").orElseThrow() }
private val NSBaselineOffsetAttributeName_VH: VarHandle by lazy { NSBaselineOffsetAttributeName_LAYOUT.varHandle() }

var NSBaselineOffsetAttributeName: MemorySegment
    get() = NSBaselineOffsetAttributeName_VH.get(NSBaselineOffsetAttributeName_SEGMENT) as MemorySegment
    set(value) = NSBaselineOffsetAttributeName_VH.set(NSBaselineOffsetAttributeName_SEGMENT, value)

