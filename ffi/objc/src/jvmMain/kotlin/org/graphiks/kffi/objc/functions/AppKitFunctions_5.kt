package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * {@snippet lang=c : NSURLVolumeSupportsFileProtectionKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSupportsFileProtectionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSupportsFileProtectionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSupportsFileProtectionKey").orElseThrow() }
private val NSURLVolumeSupportsFileProtectionKey_VH: VarHandle by lazy { NSURLVolumeSupportsFileProtectionKey_LAYOUT.varHandle() }

var NSURLVolumeSupportsFileProtectionKey: MemorySegment
    get() = NSURLVolumeSupportsFileProtectionKey_VH.get(NSURLVolumeSupportsFileProtectionKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSupportsFileProtectionKey_VH.set(NSURLVolumeSupportsFileProtectionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeAvailableCapacityForImportantUsageKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeAvailableCapacityForImportantUsageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeAvailableCapacityForImportantUsageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeAvailableCapacityForImportantUsageKey").orElseThrow() }
private val NSURLVolumeAvailableCapacityForImportantUsageKey_VH: VarHandle by lazy { NSURLVolumeAvailableCapacityForImportantUsageKey_LAYOUT.varHandle() }

var NSURLVolumeAvailableCapacityForImportantUsageKey: MemorySegment
    get() = NSURLVolumeAvailableCapacityForImportantUsageKey_VH.get(NSURLVolumeAvailableCapacityForImportantUsageKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeAvailableCapacityForImportantUsageKey_VH.set(NSURLVolumeAvailableCapacityForImportantUsageKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeAvailableCapacityForOpportunisticUsageKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeAvailableCapacityForOpportunisticUsageKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeAvailableCapacityForOpportunisticUsageKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeAvailableCapacityForOpportunisticUsageKey").orElseThrow() }
private val NSURLVolumeAvailableCapacityForOpportunisticUsageKey_VH: VarHandle by lazy { NSURLVolumeAvailableCapacityForOpportunisticUsageKey_LAYOUT.varHandle() }

var NSURLVolumeAvailableCapacityForOpportunisticUsageKey: MemorySegment
    get() = NSURLVolumeAvailableCapacityForOpportunisticUsageKey_VH.get(NSURLVolumeAvailableCapacityForOpportunisticUsageKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeAvailableCapacityForOpportunisticUsageKey_VH.set(NSURLVolumeAvailableCapacityForOpportunisticUsageKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeTypeNameKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeTypeNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeTypeNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeTypeNameKey").orElseThrow() }
private val NSURLVolumeTypeNameKey_VH: VarHandle by lazy { NSURLVolumeTypeNameKey_LAYOUT.varHandle() }

var NSURLVolumeTypeNameKey: MemorySegment
    get() = NSURLVolumeTypeNameKey_VH.get(NSURLVolumeTypeNameKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeTypeNameKey_VH.set(NSURLVolumeTypeNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeSubtypeKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeSubtypeKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeSubtypeKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeSubtypeKey").orElseThrow() }
private val NSURLVolumeSubtypeKey_VH: VarHandle by lazy { NSURLVolumeSubtypeKey_LAYOUT.varHandle() }

var NSURLVolumeSubtypeKey: MemorySegment
    get() = NSURLVolumeSubtypeKey_VH.get(NSURLVolumeSubtypeKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeSubtypeKey_VH.set(NSURLVolumeSubtypeKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLVolumeMountFromLocationKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLVolumeMountFromLocationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLVolumeMountFromLocationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLVolumeMountFromLocationKey").orElseThrow() }
private val NSURLVolumeMountFromLocationKey_VH: VarHandle by lazy { NSURLVolumeMountFromLocationKey_LAYOUT.varHandle() }

var NSURLVolumeMountFromLocationKey: MemorySegment
    get() = NSURLVolumeMountFromLocationKey_VH.get(NSURLVolumeMountFromLocationKey_SEGMENT) as MemorySegment
    set(value) = NSURLVolumeMountFromLocationKey_VH.set(NSURLVolumeMountFromLocationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLIsUbiquitousItemKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLIsUbiquitousItemKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLIsUbiquitousItemKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLIsUbiquitousItemKey").orElseThrow() }
private val NSURLIsUbiquitousItemKey_VH: VarHandle by lazy { NSURLIsUbiquitousItemKey_LAYOUT.varHandle() }

var NSURLIsUbiquitousItemKey: MemorySegment
    get() = NSURLIsUbiquitousItemKey_VH.get(NSURLIsUbiquitousItemKey_SEGMENT) as MemorySegment
    set(value) = NSURLIsUbiquitousItemKey_VH.set(NSURLIsUbiquitousItemKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemHasUnresolvedConflictsKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemHasUnresolvedConflictsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemHasUnresolvedConflictsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemHasUnresolvedConflictsKey").orElseThrow() }
private val NSURLUbiquitousItemHasUnresolvedConflictsKey_VH: VarHandle by lazy { NSURLUbiquitousItemHasUnresolvedConflictsKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemHasUnresolvedConflictsKey: MemorySegment
    get() = NSURLUbiquitousItemHasUnresolvedConflictsKey_VH.get(NSURLUbiquitousItemHasUnresolvedConflictsKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemHasUnresolvedConflictsKey_VH.set(NSURLUbiquitousItemHasUnresolvedConflictsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemIsDownloadedKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemIsDownloadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemIsDownloadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemIsDownloadedKey").orElseThrow() }
private val NSURLUbiquitousItemIsDownloadedKey_VH: VarHandle by lazy { NSURLUbiquitousItemIsDownloadedKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemIsDownloadedKey: MemorySegment
    get() = NSURLUbiquitousItemIsDownloadedKey_VH.get(NSURLUbiquitousItemIsDownloadedKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemIsDownloadedKey_VH.set(NSURLUbiquitousItemIsDownloadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemIsDownloadingKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemIsDownloadingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemIsDownloadingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemIsDownloadingKey").orElseThrow() }
private val NSURLUbiquitousItemIsDownloadingKey_VH: VarHandle by lazy { NSURLUbiquitousItemIsDownloadingKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemIsDownloadingKey: MemorySegment
    get() = NSURLUbiquitousItemIsDownloadingKey_VH.get(NSURLUbiquitousItemIsDownloadingKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemIsDownloadingKey_VH.set(NSURLUbiquitousItemIsDownloadingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemIsUploadedKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemIsUploadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemIsUploadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemIsUploadedKey").orElseThrow() }
private val NSURLUbiquitousItemIsUploadedKey_VH: VarHandle by lazy { NSURLUbiquitousItemIsUploadedKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemIsUploadedKey: MemorySegment
    get() = NSURLUbiquitousItemIsUploadedKey_VH.get(NSURLUbiquitousItemIsUploadedKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemIsUploadedKey_VH.set(NSURLUbiquitousItemIsUploadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemIsUploadingKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemIsUploadingKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemIsUploadingKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemIsUploadingKey").orElseThrow() }
private val NSURLUbiquitousItemIsUploadingKey_VH: VarHandle by lazy { NSURLUbiquitousItemIsUploadingKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemIsUploadingKey: MemorySegment
    get() = NSURLUbiquitousItemIsUploadingKey_VH.get(NSURLUbiquitousItemIsUploadingKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemIsUploadingKey_VH.set(NSURLUbiquitousItemIsUploadingKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemPercentDownloadedKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemPercentDownloadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemPercentDownloadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemPercentDownloadedKey").orElseThrow() }
private val NSURLUbiquitousItemPercentDownloadedKey_VH: VarHandle by lazy { NSURLUbiquitousItemPercentDownloadedKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemPercentDownloadedKey: MemorySegment
    get() = NSURLUbiquitousItemPercentDownloadedKey_VH.get(NSURLUbiquitousItemPercentDownloadedKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemPercentDownloadedKey_VH.set(NSURLUbiquitousItemPercentDownloadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemPercentUploadedKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemPercentUploadedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemPercentUploadedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemPercentUploadedKey").orElseThrow() }
private val NSURLUbiquitousItemPercentUploadedKey_VH: VarHandle by lazy { NSURLUbiquitousItemPercentUploadedKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemPercentUploadedKey: MemorySegment
    get() = NSURLUbiquitousItemPercentUploadedKey_VH.get(NSURLUbiquitousItemPercentUploadedKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemPercentUploadedKey_VH.set(NSURLUbiquitousItemPercentUploadedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemDownloadingStatusKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemDownloadingStatusKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemDownloadingStatusKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemDownloadingStatusKey").orElseThrow() }
private val NSURLUbiquitousItemDownloadingStatusKey_VH: VarHandle by lazy { NSURLUbiquitousItemDownloadingStatusKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemDownloadingStatusKey: MemorySegment
    get() = NSURLUbiquitousItemDownloadingStatusKey_VH.get(NSURLUbiquitousItemDownloadingStatusKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemDownloadingStatusKey_VH.set(NSURLUbiquitousItemDownloadingStatusKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemDownloadingErrorKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemDownloadingErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemDownloadingErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemDownloadingErrorKey").orElseThrow() }
private val NSURLUbiquitousItemDownloadingErrorKey_VH: VarHandle by lazy { NSURLUbiquitousItemDownloadingErrorKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemDownloadingErrorKey: MemorySegment
    get() = NSURLUbiquitousItemDownloadingErrorKey_VH.get(NSURLUbiquitousItemDownloadingErrorKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemDownloadingErrorKey_VH.set(NSURLUbiquitousItemDownloadingErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemUploadingErrorKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemUploadingErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemUploadingErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemUploadingErrorKey").orElseThrow() }
private val NSURLUbiquitousItemUploadingErrorKey_VH: VarHandle by lazy { NSURLUbiquitousItemUploadingErrorKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemUploadingErrorKey: MemorySegment
    get() = NSURLUbiquitousItemUploadingErrorKey_VH.get(NSURLUbiquitousItemUploadingErrorKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemUploadingErrorKey_VH.set(NSURLUbiquitousItemUploadingErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemDownloadRequestedKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemDownloadRequestedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemDownloadRequestedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemDownloadRequestedKey").orElseThrow() }
private val NSURLUbiquitousItemDownloadRequestedKey_VH: VarHandle by lazy { NSURLUbiquitousItemDownloadRequestedKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemDownloadRequestedKey: MemorySegment
    get() = NSURLUbiquitousItemDownloadRequestedKey_VH.get(NSURLUbiquitousItemDownloadRequestedKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemDownloadRequestedKey_VH.set(NSURLUbiquitousItemDownloadRequestedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemContainerDisplayNameKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemContainerDisplayNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemContainerDisplayNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemContainerDisplayNameKey").orElseThrow() }
private val NSURLUbiquitousItemContainerDisplayNameKey_VH: VarHandle by lazy { NSURLUbiquitousItemContainerDisplayNameKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemContainerDisplayNameKey: MemorySegment
    get() = NSURLUbiquitousItemContainerDisplayNameKey_VH.get(NSURLUbiquitousItemContainerDisplayNameKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemContainerDisplayNameKey_VH.set(NSURLUbiquitousItemContainerDisplayNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemIsExcludedFromSyncKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemIsExcludedFromSyncKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemIsExcludedFromSyncKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemIsExcludedFromSyncKey").orElseThrow() }
private val NSURLUbiquitousItemIsExcludedFromSyncKey_VH: VarHandle by lazy { NSURLUbiquitousItemIsExcludedFromSyncKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemIsExcludedFromSyncKey: MemorySegment
    get() = NSURLUbiquitousItemIsExcludedFromSyncKey_VH.get(NSURLUbiquitousItemIsExcludedFromSyncKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemIsExcludedFromSyncKey_VH.set(NSURLUbiquitousItemIsExcludedFromSyncKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemIsSharedKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemIsSharedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemIsSharedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemIsSharedKey").orElseThrow() }
private val NSURLUbiquitousItemIsSharedKey_VH: VarHandle by lazy { NSURLUbiquitousItemIsSharedKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemIsSharedKey: MemorySegment
    get() = NSURLUbiquitousItemIsSharedKey_VH.get(NSURLUbiquitousItemIsSharedKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemIsSharedKey_VH.set(NSURLUbiquitousItemIsSharedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousSharedItemCurrentUserRoleKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousSharedItemCurrentUserRoleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousSharedItemCurrentUserRoleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousSharedItemCurrentUserRoleKey").orElseThrow() }
private val NSURLUbiquitousSharedItemCurrentUserRoleKey_VH: VarHandle by lazy { NSURLUbiquitousSharedItemCurrentUserRoleKey_LAYOUT.varHandle() }

var NSURLUbiquitousSharedItemCurrentUserRoleKey: MemorySegment
    get() = NSURLUbiquitousSharedItemCurrentUserRoleKey_VH.get(NSURLUbiquitousSharedItemCurrentUserRoleKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousSharedItemCurrentUserRoleKey_VH.set(NSURLUbiquitousSharedItemCurrentUserRoleKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousSharedItemCurrentUserPermissionsKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousSharedItemCurrentUserPermissionsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousSharedItemCurrentUserPermissionsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousSharedItemCurrentUserPermissionsKey").orElseThrow() }
private val NSURLUbiquitousSharedItemCurrentUserPermissionsKey_VH: VarHandle by lazy { NSURLUbiquitousSharedItemCurrentUserPermissionsKey_LAYOUT.varHandle() }

var NSURLUbiquitousSharedItemCurrentUserPermissionsKey: MemorySegment
    get() = NSURLUbiquitousSharedItemCurrentUserPermissionsKey_VH.get(NSURLUbiquitousSharedItemCurrentUserPermissionsKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousSharedItemCurrentUserPermissionsKey_VH.set(NSURLUbiquitousSharedItemCurrentUserPermissionsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousSharedItemOwnerNameComponentsKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousSharedItemOwnerNameComponentsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousSharedItemOwnerNameComponentsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousSharedItemOwnerNameComponentsKey").orElseThrow() }
private val NSURLUbiquitousSharedItemOwnerNameComponentsKey_VH: VarHandle by lazy { NSURLUbiquitousSharedItemOwnerNameComponentsKey_LAYOUT.varHandle() }

var NSURLUbiquitousSharedItemOwnerNameComponentsKey: MemorySegment
    get() = NSURLUbiquitousSharedItemOwnerNameComponentsKey_VH.get(NSURLUbiquitousSharedItemOwnerNameComponentsKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousSharedItemOwnerNameComponentsKey_VH.set(NSURLUbiquitousSharedItemOwnerNameComponentsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey").orElseThrow() }
private val NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey_VH: VarHandle by lazy { NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey_LAYOUT.varHandle() }

var NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey: MemorySegment
    get() = NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey_VH.get(NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey_VH.set(NSURLUbiquitousSharedItemMostRecentEditorNameComponentsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemDownloadingStatusNotDownloaded typedef const NSURLUbiquitousItemDownloadingStatus = (Void)*
 */
private val NSURLUbiquitousItemDownloadingStatusNotDownloaded_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemDownloadingStatusNotDownloaded_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemDownloadingStatusNotDownloaded").orElseThrow() }
private val NSURLUbiquitousItemDownloadingStatusNotDownloaded_VH: VarHandle by lazy { NSURLUbiquitousItemDownloadingStatusNotDownloaded_LAYOUT.varHandle() }

var NSURLUbiquitousItemDownloadingStatusNotDownloaded: MemorySegment
    get() = NSURLUbiquitousItemDownloadingStatusNotDownloaded_VH.get(NSURLUbiquitousItemDownloadingStatusNotDownloaded_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemDownloadingStatusNotDownloaded_VH.set(NSURLUbiquitousItemDownloadingStatusNotDownloaded_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemDownloadingStatusDownloaded typedef const NSURLUbiquitousItemDownloadingStatus = (Void)*
 */
private val NSURLUbiquitousItemDownloadingStatusDownloaded_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemDownloadingStatusDownloaded_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemDownloadingStatusDownloaded").orElseThrow() }
private val NSURLUbiquitousItemDownloadingStatusDownloaded_VH: VarHandle by lazy { NSURLUbiquitousItemDownloadingStatusDownloaded_LAYOUT.varHandle() }

var NSURLUbiquitousItemDownloadingStatusDownloaded: MemorySegment
    get() = NSURLUbiquitousItemDownloadingStatusDownloaded_VH.get(NSURLUbiquitousItemDownloadingStatusDownloaded_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemDownloadingStatusDownloaded_VH.set(NSURLUbiquitousItemDownloadingStatusDownloaded_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemDownloadingStatusCurrent typedef const NSURLUbiquitousItemDownloadingStatus = (Void)*
 */
private val NSURLUbiquitousItemDownloadingStatusCurrent_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemDownloadingStatusCurrent_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemDownloadingStatusCurrent").orElseThrow() }
private val NSURLUbiquitousItemDownloadingStatusCurrent_VH: VarHandle by lazy { NSURLUbiquitousItemDownloadingStatusCurrent_LAYOUT.varHandle() }

var NSURLUbiquitousItemDownloadingStatusCurrent: MemorySegment
    get() = NSURLUbiquitousItemDownloadingStatusCurrent_VH.get(NSURLUbiquitousItemDownloadingStatusCurrent_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemDownloadingStatusCurrent_VH.set(NSURLUbiquitousItemDownloadingStatusCurrent_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousSharedItemRoleOwner typedef const NSURLUbiquitousSharedItemRole = (Void)*
 */
private val NSURLUbiquitousSharedItemRoleOwner_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousSharedItemRoleOwner_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousSharedItemRoleOwner").orElseThrow() }
private val NSURLUbiquitousSharedItemRoleOwner_VH: VarHandle by lazy { NSURLUbiquitousSharedItemRoleOwner_LAYOUT.varHandle() }

var NSURLUbiquitousSharedItemRoleOwner: MemorySegment
    get() = NSURLUbiquitousSharedItemRoleOwner_VH.get(NSURLUbiquitousSharedItemRoleOwner_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousSharedItemRoleOwner_VH.set(NSURLUbiquitousSharedItemRoleOwner_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousSharedItemRoleParticipant typedef const NSURLUbiquitousSharedItemRole = (Void)*
 */
private val NSURLUbiquitousSharedItemRoleParticipant_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousSharedItemRoleParticipant_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousSharedItemRoleParticipant").orElseThrow() }
private val NSURLUbiquitousSharedItemRoleParticipant_VH: VarHandle by lazy { NSURLUbiquitousSharedItemRoleParticipant_LAYOUT.varHandle() }

var NSURLUbiquitousSharedItemRoleParticipant: MemorySegment
    get() = NSURLUbiquitousSharedItemRoleParticipant_VH.get(NSURLUbiquitousSharedItemRoleParticipant_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousSharedItemRoleParticipant_VH.set(NSURLUbiquitousSharedItemRoleParticipant_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousSharedItemPermissionsReadOnly typedef const NSURLUbiquitousSharedItemPermissions = (Void)*
 */
private val NSURLUbiquitousSharedItemPermissionsReadOnly_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousSharedItemPermissionsReadOnly_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousSharedItemPermissionsReadOnly").orElseThrow() }
private val NSURLUbiquitousSharedItemPermissionsReadOnly_VH: VarHandle by lazy { NSURLUbiquitousSharedItemPermissionsReadOnly_LAYOUT.varHandle() }

var NSURLUbiquitousSharedItemPermissionsReadOnly: MemorySegment
    get() = NSURLUbiquitousSharedItemPermissionsReadOnly_VH.get(NSURLUbiquitousSharedItemPermissionsReadOnly_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousSharedItemPermissionsReadOnly_VH.set(NSURLUbiquitousSharedItemPermissionsReadOnly_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousSharedItemPermissionsReadWrite typedef const NSURLUbiquitousSharedItemPermissions = (Void)*
 */
private val NSURLUbiquitousSharedItemPermissionsReadWrite_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousSharedItemPermissionsReadWrite_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousSharedItemPermissionsReadWrite").orElseThrow() }
private val NSURLUbiquitousSharedItemPermissionsReadWrite_VH: VarHandle by lazy { NSURLUbiquitousSharedItemPermissionsReadWrite_LAYOUT.varHandle() }

var NSURLUbiquitousSharedItemPermissionsReadWrite: MemorySegment
    get() = NSURLUbiquitousSharedItemPermissionsReadWrite_VH.get(NSURLUbiquitousSharedItemPermissionsReadWrite_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousSharedItemPermissionsReadWrite_VH.set(NSURLUbiquitousSharedItemPermissionsReadWrite_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemSupportedSyncControlsKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemSupportedSyncControlsKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemSupportedSyncControlsKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemSupportedSyncControlsKey").orElseThrow() }
private val NSURLUbiquitousItemSupportedSyncControlsKey_VH: VarHandle by lazy { NSURLUbiquitousItemSupportedSyncControlsKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemSupportedSyncControlsKey: MemorySegment
    get() = NSURLUbiquitousItemSupportedSyncControlsKey_VH.get(NSURLUbiquitousItemSupportedSyncControlsKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemSupportedSyncControlsKey_VH.set(NSURLUbiquitousItemSupportedSyncControlsKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLUbiquitousItemIsSyncPausedKey typedef const NSURLResourceKey = (Void)*
 */
private val NSURLUbiquitousItemIsSyncPausedKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLUbiquitousItemIsSyncPausedKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLUbiquitousItemIsSyncPausedKey").orElseThrow() }
private val NSURLUbiquitousItemIsSyncPausedKey_VH: VarHandle by lazy { NSURLUbiquitousItemIsSyncPausedKey_LAYOUT.varHandle() }

var NSURLUbiquitousItemIsSyncPausedKey: MemorySegment
    get() = NSURLUbiquitousItemIsSyncPausedKey_VH.get(NSURLUbiquitousItemIsSyncPausedKey_SEGMENT) as MemorySegment
    set(value) = NSURLUbiquitousItemIsSyncPausedKey_VH.set(NSURLUbiquitousItemIsSyncPausedKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileManagerUnmountDissentingProcessIdentifierErrorKey (Void)*
 */
private val NSFileManagerUnmountDissentingProcessIdentifierErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileManagerUnmountDissentingProcessIdentifierErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileManagerUnmountDissentingProcessIdentifierErrorKey").orElseThrow() }
private val NSFileManagerUnmountDissentingProcessIdentifierErrorKey_VH: VarHandle by lazy { NSFileManagerUnmountDissentingProcessIdentifierErrorKey_LAYOUT.varHandle() }

var NSFileManagerUnmountDissentingProcessIdentifierErrorKey: MemorySegment
    get() = NSFileManagerUnmountDissentingProcessIdentifierErrorKey_VH.get(NSFileManagerUnmountDissentingProcessIdentifierErrorKey_SEGMENT) as MemorySegment
    set(value) = NSFileManagerUnmountDissentingProcessIdentifierErrorKey_VH.set(NSFileManagerUnmountDissentingProcessIdentifierErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSUbiquityIdentityDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSUbiquityIdentityDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUbiquityIdentityDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUbiquityIdentityDidChangeNotification").orElseThrow() }
private val NSUbiquityIdentityDidChangeNotification_VH: VarHandle by lazy { NSUbiquityIdentityDidChangeNotification_LAYOUT.varHandle() }

var NSUbiquityIdentityDidChangeNotification: MemorySegment
    get() = NSUbiquityIdentityDidChangeNotification_VH.get(NSUbiquityIdentityDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSUbiquityIdentityDidChangeNotification_VH.set(NSUbiquityIdentityDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileType typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileType").orElseThrow() }
private val NSFileType_VH: VarHandle by lazy { NSFileType_LAYOUT.varHandle() }

var NSFileType: MemorySegment
    get() = NSFileType_VH.get(NSFileType_SEGMENT) as MemorySegment
    set(value) = NSFileType_VH.set(NSFileType_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeDirectory typedef const NSFileAttributeType = (Void)*
 */
private val NSFileTypeDirectory_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileTypeDirectory_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileTypeDirectory").orElseThrow() }
private val NSFileTypeDirectory_VH: VarHandle by lazy { NSFileTypeDirectory_LAYOUT.varHandle() }

var NSFileTypeDirectory: MemorySegment
    get() = NSFileTypeDirectory_VH.get(NSFileTypeDirectory_SEGMENT) as MemorySegment
    set(value) = NSFileTypeDirectory_VH.set(NSFileTypeDirectory_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeRegular typedef const NSFileAttributeType = (Void)*
 */
private val NSFileTypeRegular_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileTypeRegular_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileTypeRegular").orElseThrow() }
private val NSFileTypeRegular_VH: VarHandle by lazy { NSFileTypeRegular_LAYOUT.varHandle() }

var NSFileTypeRegular: MemorySegment
    get() = NSFileTypeRegular_VH.get(NSFileTypeRegular_SEGMENT) as MemorySegment
    set(value) = NSFileTypeRegular_VH.set(NSFileTypeRegular_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeSymbolicLink typedef const NSFileAttributeType = (Void)*
 */
private val NSFileTypeSymbolicLink_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileTypeSymbolicLink_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileTypeSymbolicLink").orElseThrow() }
private val NSFileTypeSymbolicLink_VH: VarHandle by lazy { NSFileTypeSymbolicLink_LAYOUT.varHandle() }

var NSFileTypeSymbolicLink: MemorySegment
    get() = NSFileTypeSymbolicLink_VH.get(NSFileTypeSymbolicLink_SEGMENT) as MemorySegment
    set(value) = NSFileTypeSymbolicLink_VH.set(NSFileTypeSymbolicLink_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeSocket typedef const NSFileAttributeType = (Void)*
 */
private val NSFileTypeSocket_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileTypeSocket_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileTypeSocket").orElseThrow() }
private val NSFileTypeSocket_VH: VarHandle by lazy { NSFileTypeSocket_LAYOUT.varHandle() }

var NSFileTypeSocket: MemorySegment
    get() = NSFileTypeSocket_VH.get(NSFileTypeSocket_SEGMENT) as MemorySegment
    set(value) = NSFileTypeSocket_VH.set(NSFileTypeSocket_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeCharacterSpecial typedef const NSFileAttributeType = (Void)*
 */
private val NSFileTypeCharacterSpecial_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileTypeCharacterSpecial_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileTypeCharacterSpecial").orElseThrow() }
private val NSFileTypeCharacterSpecial_VH: VarHandle by lazy { NSFileTypeCharacterSpecial_LAYOUT.varHandle() }

var NSFileTypeCharacterSpecial: MemorySegment
    get() = NSFileTypeCharacterSpecial_VH.get(NSFileTypeCharacterSpecial_SEGMENT) as MemorySegment
    set(value) = NSFileTypeCharacterSpecial_VH.set(NSFileTypeCharacterSpecial_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeBlockSpecial typedef const NSFileAttributeType = (Void)*
 */
private val NSFileTypeBlockSpecial_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileTypeBlockSpecial_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileTypeBlockSpecial").orElseThrow() }
private val NSFileTypeBlockSpecial_VH: VarHandle by lazy { NSFileTypeBlockSpecial_LAYOUT.varHandle() }

var NSFileTypeBlockSpecial: MemorySegment
    get() = NSFileTypeBlockSpecial_VH.get(NSFileTypeBlockSpecial_SEGMENT) as MemorySegment
    set(value) = NSFileTypeBlockSpecial_VH.set(NSFileTypeBlockSpecial_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileTypeUnknown typedef const NSFileAttributeType = (Void)*
 */
private val NSFileTypeUnknown_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileTypeUnknown_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileTypeUnknown").orElseThrow() }
private val NSFileTypeUnknown_VH: VarHandle by lazy { NSFileTypeUnknown_LAYOUT.varHandle() }

var NSFileTypeUnknown: MemorySegment
    get() = NSFileTypeUnknown_VH.get(NSFileTypeUnknown_SEGMENT) as MemorySegment
    set(value) = NSFileTypeUnknown_VH.set(NSFileTypeUnknown_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileSize typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileSize_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileSize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileSize").orElseThrow() }
private val NSFileSize_VH: VarHandle by lazy { NSFileSize_LAYOUT.varHandle() }

var NSFileSize: MemorySegment
    get() = NSFileSize_VH.get(NSFileSize_SEGMENT) as MemorySegment
    set(value) = NSFileSize_VH.set(NSFileSize_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileModificationDate typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileModificationDate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileModificationDate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileModificationDate").orElseThrow() }
private val NSFileModificationDate_VH: VarHandle by lazy { NSFileModificationDate_LAYOUT.varHandle() }

var NSFileModificationDate: MemorySegment
    get() = NSFileModificationDate_VH.get(NSFileModificationDate_SEGMENT) as MemorySegment
    set(value) = NSFileModificationDate_VH.set(NSFileModificationDate_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileReferenceCount typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileReferenceCount_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileReferenceCount_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileReferenceCount").orElseThrow() }
private val NSFileReferenceCount_VH: VarHandle by lazy { NSFileReferenceCount_LAYOUT.varHandle() }

var NSFileReferenceCount: MemorySegment
    get() = NSFileReferenceCount_VH.get(NSFileReferenceCount_SEGMENT) as MemorySegment
    set(value) = NSFileReferenceCount_VH.set(NSFileReferenceCount_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileDeviceIdentifier typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileDeviceIdentifier_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileDeviceIdentifier_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileDeviceIdentifier").orElseThrow() }
private val NSFileDeviceIdentifier_VH: VarHandle by lazy { NSFileDeviceIdentifier_LAYOUT.varHandle() }

var NSFileDeviceIdentifier: MemorySegment
    get() = NSFileDeviceIdentifier_VH.get(NSFileDeviceIdentifier_SEGMENT) as MemorySegment
    set(value) = NSFileDeviceIdentifier_VH.set(NSFileDeviceIdentifier_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileOwnerAccountName typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileOwnerAccountName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileOwnerAccountName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileOwnerAccountName").orElseThrow() }
private val NSFileOwnerAccountName_VH: VarHandle by lazy { NSFileOwnerAccountName_LAYOUT.varHandle() }

var NSFileOwnerAccountName: MemorySegment
    get() = NSFileOwnerAccountName_VH.get(NSFileOwnerAccountName_SEGMENT) as MemorySegment
    set(value) = NSFileOwnerAccountName_VH.set(NSFileOwnerAccountName_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileGroupOwnerAccountName typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileGroupOwnerAccountName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileGroupOwnerAccountName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileGroupOwnerAccountName").orElseThrow() }
private val NSFileGroupOwnerAccountName_VH: VarHandle by lazy { NSFileGroupOwnerAccountName_LAYOUT.varHandle() }

var NSFileGroupOwnerAccountName: MemorySegment
    get() = NSFileGroupOwnerAccountName_VH.get(NSFileGroupOwnerAccountName_SEGMENT) as MemorySegment
    set(value) = NSFileGroupOwnerAccountName_VH.set(NSFileGroupOwnerAccountName_SEGMENT, value)

/**
 * {@snippet lang=c : NSFilePosixPermissions typedef const NSFileAttributeKey = (Void)*
 */
private val NSFilePosixPermissions_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFilePosixPermissions_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFilePosixPermissions").orElseThrow() }
private val NSFilePosixPermissions_VH: VarHandle by lazy { NSFilePosixPermissions_LAYOUT.varHandle() }

var NSFilePosixPermissions: MemorySegment
    get() = NSFilePosixPermissions_VH.get(NSFilePosixPermissions_SEGMENT) as MemorySegment
    set(value) = NSFilePosixPermissions_VH.set(NSFilePosixPermissions_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileSystemNumber typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileSystemNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileSystemNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileSystemNumber").orElseThrow() }
private val NSFileSystemNumber_VH: VarHandle by lazy { NSFileSystemNumber_LAYOUT.varHandle() }

var NSFileSystemNumber: MemorySegment
    get() = NSFileSystemNumber_VH.get(NSFileSystemNumber_SEGMENT) as MemorySegment
    set(value) = NSFileSystemNumber_VH.set(NSFileSystemNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileSystemFileNumber typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileSystemFileNumber_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileSystemFileNumber_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileSystemFileNumber").orElseThrow() }
private val NSFileSystemFileNumber_VH: VarHandle by lazy { NSFileSystemFileNumber_LAYOUT.varHandle() }

var NSFileSystemFileNumber: MemorySegment
    get() = NSFileSystemFileNumber_VH.get(NSFileSystemFileNumber_SEGMENT) as MemorySegment
    set(value) = NSFileSystemFileNumber_VH.set(NSFileSystemFileNumber_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileExtensionHidden typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileExtensionHidden_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileExtensionHidden_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileExtensionHidden").orElseThrow() }
private val NSFileExtensionHidden_VH: VarHandle by lazy { NSFileExtensionHidden_LAYOUT.varHandle() }

var NSFileExtensionHidden: MemorySegment
    get() = NSFileExtensionHidden_VH.get(NSFileExtensionHidden_SEGMENT) as MemorySegment
    set(value) = NSFileExtensionHidden_VH.set(NSFileExtensionHidden_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHFSCreatorCode typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileHFSCreatorCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHFSCreatorCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHFSCreatorCode").orElseThrow() }
private val NSFileHFSCreatorCode_VH: VarHandle by lazy { NSFileHFSCreatorCode_LAYOUT.varHandle() }

var NSFileHFSCreatorCode: MemorySegment
    get() = NSFileHFSCreatorCode_VH.get(NSFileHFSCreatorCode_SEGMENT) as MemorySegment
    set(value) = NSFileHFSCreatorCode_VH.set(NSFileHFSCreatorCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileHFSTypeCode typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileHFSTypeCode_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileHFSTypeCode_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileHFSTypeCode").orElseThrow() }
private val NSFileHFSTypeCode_VH: VarHandle by lazy { NSFileHFSTypeCode_LAYOUT.varHandle() }

var NSFileHFSTypeCode: MemorySegment
    get() = NSFileHFSTypeCode_VH.get(NSFileHFSTypeCode_SEGMENT) as MemorySegment
    set(value) = NSFileHFSTypeCode_VH.set(NSFileHFSTypeCode_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileImmutable typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileImmutable_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileImmutable_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileImmutable").orElseThrow() }
private val NSFileImmutable_VH: VarHandle by lazy { NSFileImmutable_LAYOUT.varHandle() }

var NSFileImmutable: MemorySegment
    get() = NSFileImmutable_VH.get(NSFileImmutable_SEGMENT) as MemorySegment
    set(value) = NSFileImmutable_VH.set(NSFileImmutable_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileAppendOnly typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileAppendOnly_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileAppendOnly_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileAppendOnly").orElseThrow() }
private val NSFileAppendOnly_VH: VarHandle by lazy { NSFileAppendOnly_LAYOUT.varHandle() }

var NSFileAppendOnly: MemorySegment
    get() = NSFileAppendOnly_VH.get(NSFileAppendOnly_SEGMENT) as MemorySegment
    set(value) = NSFileAppendOnly_VH.set(NSFileAppendOnly_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileCreationDate typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileCreationDate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileCreationDate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileCreationDate").orElseThrow() }
private val NSFileCreationDate_VH: VarHandle by lazy { NSFileCreationDate_LAYOUT.varHandle() }

var NSFileCreationDate: MemorySegment
    get() = NSFileCreationDate_VH.get(NSFileCreationDate_SEGMENT) as MemorySegment
    set(value) = NSFileCreationDate_VH.set(NSFileCreationDate_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileOwnerAccountID typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileOwnerAccountID_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileOwnerAccountID_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileOwnerAccountID").orElseThrow() }
private val NSFileOwnerAccountID_VH: VarHandle by lazy { NSFileOwnerAccountID_LAYOUT.varHandle() }

var NSFileOwnerAccountID: MemorySegment
    get() = NSFileOwnerAccountID_VH.get(NSFileOwnerAccountID_SEGMENT) as MemorySegment
    set(value) = NSFileOwnerAccountID_VH.set(NSFileOwnerAccountID_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileGroupOwnerAccountID typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileGroupOwnerAccountID_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileGroupOwnerAccountID_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileGroupOwnerAccountID").orElseThrow() }
private val NSFileGroupOwnerAccountID_VH: VarHandle by lazy { NSFileGroupOwnerAccountID_LAYOUT.varHandle() }

var NSFileGroupOwnerAccountID: MemorySegment
    get() = NSFileGroupOwnerAccountID_VH.get(NSFileGroupOwnerAccountID_SEGMENT) as MemorySegment
    set(value) = NSFileGroupOwnerAccountID_VH.set(NSFileGroupOwnerAccountID_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileBusy typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileBusy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileBusy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileBusy").orElseThrow() }
private val NSFileBusy_VH: VarHandle by lazy { NSFileBusy_LAYOUT.varHandle() }

var NSFileBusy: MemorySegment
    get() = NSFileBusy_VH.get(NSFileBusy_SEGMENT) as MemorySegment
    set(value) = NSFileBusy_VH.set(NSFileBusy_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileProtectionKey typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileProtectionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileProtectionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileProtectionKey").orElseThrow() }
private val NSFileProtectionKey_VH: VarHandle by lazy { NSFileProtectionKey_LAYOUT.varHandle() }

var NSFileProtectionKey: MemorySegment
    get() = NSFileProtectionKey_VH.get(NSFileProtectionKey_SEGMENT) as MemorySegment
    set(value) = NSFileProtectionKey_VH.set(NSFileProtectionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileProtectionNone typedef const NSFileProtectionType = (Void)*
 */
private val NSFileProtectionNone_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileProtectionNone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileProtectionNone").orElseThrow() }
private val NSFileProtectionNone_VH: VarHandle by lazy { NSFileProtectionNone_LAYOUT.varHandle() }

var NSFileProtectionNone: MemorySegment
    get() = NSFileProtectionNone_VH.get(NSFileProtectionNone_SEGMENT) as MemorySegment
    set(value) = NSFileProtectionNone_VH.set(NSFileProtectionNone_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileProtectionComplete typedef const NSFileProtectionType = (Void)*
 */
private val NSFileProtectionComplete_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileProtectionComplete_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileProtectionComplete").orElseThrow() }
private val NSFileProtectionComplete_VH: VarHandle by lazy { NSFileProtectionComplete_LAYOUT.varHandle() }

var NSFileProtectionComplete: MemorySegment
    get() = NSFileProtectionComplete_VH.get(NSFileProtectionComplete_SEGMENT) as MemorySegment
    set(value) = NSFileProtectionComplete_VH.set(NSFileProtectionComplete_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileProtectionCompleteUnlessOpen typedef const NSFileProtectionType = (Void)*
 */
private val NSFileProtectionCompleteUnlessOpen_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileProtectionCompleteUnlessOpen_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileProtectionCompleteUnlessOpen").orElseThrow() }
private val NSFileProtectionCompleteUnlessOpen_VH: VarHandle by lazy { NSFileProtectionCompleteUnlessOpen_LAYOUT.varHandle() }

var NSFileProtectionCompleteUnlessOpen: MemorySegment
    get() = NSFileProtectionCompleteUnlessOpen_VH.get(NSFileProtectionCompleteUnlessOpen_SEGMENT) as MemorySegment
    set(value) = NSFileProtectionCompleteUnlessOpen_VH.set(NSFileProtectionCompleteUnlessOpen_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileProtectionCompleteUntilFirstUserAuthentication typedef const NSFileProtectionType = (Void)*
 */
private val NSFileProtectionCompleteUntilFirstUserAuthentication_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileProtectionCompleteUntilFirstUserAuthentication_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileProtectionCompleteUntilFirstUserAuthentication").orElseThrow() }
private val NSFileProtectionCompleteUntilFirstUserAuthentication_VH: VarHandle by lazy { NSFileProtectionCompleteUntilFirstUserAuthentication_LAYOUT.varHandle() }

var NSFileProtectionCompleteUntilFirstUserAuthentication: MemorySegment
    get() = NSFileProtectionCompleteUntilFirstUserAuthentication_VH.get(NSFileProtectionCompleteUntilFirstUserAuthentication_SEGMENT) as MemorySegment
    set(value) = NSFileProtectionCompleteUntilFirstUserAuthentication_VH.set(NSFileProtectionCompleteUntilFirstUserAuthentication_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileProtectionCompleteWhenUserInactive typedef const NSFileProtectionType = (Void)*
 */
private val NSFileProtectionCompleteWhenUserInactive_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileProtectionCompleteWhenUserInactive_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileProtectionCompleteWhenUserInactive").orElseThrow() }
private val NSFileProtectionCompleteWhenUserInactive_VH: VarHandle by lazy { NSFileProtectionCompleteWhenUserInactive_LAYOUT.varHandle() }

var NSFileProtectionCompleteWhenUserInactive: MemorySegment
    get() = NSFileProtectionCompleteWhenUserInactive_VH.get(NSFileProtectionCompleteWhenUserInactive_SEGMENT) as MemorySegment
    set(value) = NSFileProtectionCompleteWhenUserInactive_VH.set(NSFileProtectionCompleteWhenUserInactive_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileSystemSize typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileSystemSize_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileSystemSize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileSystemSize").orElseThrow() }
private val NSFileSystemSize_VH: VarHandle by lazy { NSFileSystemSize_LAYOUT.varHandle() }

var NSFileSystemSize: MemorySegment
    get() = NSFileSystemSize_VH.get(NSFileSystemSize_SEGMENT) as MemorySegment
    set(value) = NSFileSystemSize_VH.set(NSFileSystemSize_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileSystemFreeSize typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileSystemFreeSize_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileSystemFreeSize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileSystemFreeSize").orElseThrow() }
private val NSFileSystemFreeSize_VH: VarHandle by lazy { NSFileSystemFreeSize_LAYOUT.varHandle() }

var NSFileSystemFreeSize: MemorySegment
    get() = NSFileSystemFreeSize_VH.get(NSFileSystemFreeSize_SEGMENT) as MemorySegment
    set(value) = NSFileSystemFreeSize_VH.set(NSFileSystemFreeSize_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileSystemNodes typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileSystemNodes_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileSystemNodes_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileSystemNodes").orElseThrow() }
private val NSFileSystemNodes_VH: VarHandle by lazy { NSFileSystemNodes_LAYOUT.varHandle() }

var NSFileSystemNodes: MemorySegment
    get() = NSFileSystemNodes_VH.get(NSFileSystemNodes_SEGMENT) as MemorySegment
    set(value) = NSFileSystemNodes_VH.set(NSFileSystemNodes_SEGMENT, value)

/**
 * {@snippet lang=c : NSFileSystemFreeNodes typedef const NSFileAttributeKey = (Void)*
 */
private val NSFileSystemFreeNodes_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSFileSystemFreeNodes_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSFileSystemFreeNodes").orElseThrow() }
private val NSFileSystemFreeNodes_VH: VarHandle by lazy { NSFileSystemFreeNodes_LAYOUT.varHandle() }

var NSFileSystemFreeNodes: MemorySegment
    get() = NSFileSystemFreeNodes_VH.get(NSFileSystemFreeNodes_SEGMENT) as MemorySegment
    set(value) = NSFileSystemFreeNodes_VH.set(NSFileSystemFreeNodes_SEGMENT, value)

/**
 * {@snippet lang=c : NSFreeHashTable Void(typedef NSHashTable = (Void)*)
 */
private val NSFreeHashTable_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSFreeHashTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSFreeHashTable").orElseThrow()
private val NSFreeHashTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSFreeHashTable_ADDR, NSFreeHashTable_DESC)

fun NSFreeHashTable(arg0: MemorySegment): Unit {
    try {
        NSFreeHashTable_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSResetHashTable Void(typedef NSHashTable = (Void)*)
 */
private val NSResetHashTable_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSResetHashTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSResetHashTable").orElseThrow()
private val NSResetHashTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSResetHashTable_ADDR, NSResetHashTable_DESC)

fun NSResetHashTable(arg0: MemorySegment): Unit {
    try {
        NSResetHashTable_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCompareHashTables typedef BOOL = Bool(typedef NSHashTable = (Void)*,typedef NSHashTable = (Void)*)
 */
private val NSCompareHashTables_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSCompareHashTables_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCompareHashTables").orElseThrow()
private val NSCompareHashTables_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCompareHashTables_ADDR, NSCompareHashTables_DESC)

fun NSCompareHashTables(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSCompareHashTables_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCopyHashTableWithZone typedef NSHashTable = (Void)*(typedef NSHashTable = (Void)*,(typedef NSZone = Declared(_NSZone))*)
 */
private val NSCopyHashTableWithZone_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSCopyHashTableWithZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCopyHashTableWithZone").orElseThrow()
private val NSCopyHashTableWithZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCopyHashTableWithZone_ADDR, NSCopyHashTableWithZone_DESC)

fun NSCopyHashTableWithZone(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSCopyHashTableWithZone_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHashGet (Void)*(typedef NSHashTable = (Void)*,(Void)*)
 */
private val NSHashGet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSHashGet_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHashGet").orElseThrow()
private val NSHashGet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHashGet_ADDR, NSHashGet_DESC)

fun NSHashGet(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSHashGet_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHashInsert Void(typedef NSHashTable = (Void)*,(Void)*)
 */
private val NSHashInsert_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSHashInsert_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHashInsert").orElseThrow()
private val NSHashInsert_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHashInsert_ADDR, NSHashInsert_DESC)

fun NSHashInsert(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSHashInsert_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHashInsertKnownAbsent Void(typedef NSHashTable = (Void)*,(Void)*)
 */
private val NSHashInsertKnownAbsent_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSHashInsertKnownAbsent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHashInsertKnownAbsent").orElseThrow()
private val NSHashInsertKnownAbsent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHashInsertKnownAbsent_ADDR, NSHashInsertKnownAbsent_DESC)

fun NSHashInsertKnownAbsent(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSHashInsertKnownAbsent_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHashInsertIfAbsent (Void)*(typedef NSHashTable = (Void)*,(Void)*)
 */
private val NSHashInsertIfAbsent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSHashInsertIfAbsent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHashInsertIfAbsent").orElseThrow()
private val NSHashInsertIfAbsent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHashInsertIfAbsent_ADDR, NSHashInsertIfAbsent_DESC)

fun NSHashInsertIfAbsent(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSHashInsertIfAbsent_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSHashRemove Void(typedef NSHashTable = (Void)*,(Void)*)
 */
private val NSHashRemove_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSHashRemove_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSHashRemove").orElseThrow()
private val NSHashRemove_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSHashRemove_ADDR, NSHashRemove_DESC)

fun NSHashRemove(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSHashRemove_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSEnumerateHashTable typedef NSHashEnumerator = Declared(NSHashEnumerator)(typedef NSHashTable = (Void)*)
 */
private val NSEnumerateHashTable_DESC: FunctionDescriptor = FunctionDescriptor.of(NSHashEnumerator.layout, ValueLayout.ADDRESS)
private val NSEnumerateHashTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEnumerateHashTable").orElseThrow()
private val NSEnumerateHashTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEnumerateHashTable_ADDR, NSEnumerateHashTable_DESC)

fun NSEnumerateHashTable(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return NSEnumerateHashTable_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSNextHashEnumeratorItem (Void)*((typedef NSHashEnumerator = Declared(NSHashEnumerator))*)
 */
private val NSNextHashEnumeratorItem_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSNextHashEnumeratorItem_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSNextHashEnumeratorItem").orElseThrow()
private val NSNextHashEnumeratorItem_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSNextHashEnumeratorItem_ADDR, NSNextHashEnumeratorItem_DESC)

fun NSNextHashEnumeratorItem(arg0: MemorySegment): MemorySegment {
    try {
        return NSNextHashEnumeratorItem_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSEndHashTableEnumeration Void((typedef NSHashEnumerator = Declared(NSHashEnumerator))*)
 */
private val NSEndHashTableEnumeration_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSEndHashTableEnumeration_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEndHashTableEnumeration").orElseThrow()
private val NSEndHashTableEnumeration_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEndHashTableEnumeration_ADDR, NSEndHashTableEnumeration_DESC)

fun NSEndHashTableEnumeration(arg0: MemorySegment): Unit {
    try {
        NSEndHashTableEnumeration_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCountHashTable typedef NSUInteger = UNSIGNED = Long(typedef NSHashTable = (Void)*)
 */
private val NSCountHashTable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSCountHashTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCountHashTable").orElseThrow()
private val NSCountHashTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCountHashTable_ADDR, NSCountHashTable_DESC)

fun NSCountHashTable(arg0: MemorySegment): Long {
    try {
        return NSCountHashTable_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSStringFromHashTable typedef NSString = (Void)*(typedef NSHashTable = (Void)*)
 */
private val NSStringFromHashTable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSStringFromHashTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSStringFromHashTable").orElseThrow()
private val NSStringFromHashTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSStringFromHashTable_ADDR, NSStringFromHashTable_DESC)

fun NSStringFromHashTable(arg0: MemorySegment): MemorySegment {
    try {
        return NSStringFromHashTable_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAllHashTableObjects typedef NSArray = (Void)*(typedef NSHashTable = (Void)*)
 */
private val NSAllHashTableObjects_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAllHashTableObjects_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAllHashTableObjects").orElseThrow()
private val NSAllHashTableObjects_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAllHashTableObjects_ADDR, NSAllHashTableObjects_DESC)

fun NSAllHashTableObjects(arg0: MemorySegment): MemorySegment {
    try {
        return NSAllHashTableObjects_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCreateHashTableWithZone typedef NSHashTable = (Void)*(typedef NSHashTableCallBacks = Declared(NSHashTableCallBacks),typedef NSUInteger = UNSIGNED = Long,(typedef NSZone = Declared(_NSZone))*)
 */
private val NSCreateHashTableWithZone_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, NSHashTableCallBacks.layout, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSCreateHashTableWithZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCreateHashTableWithZone").orElseThrow()
private val NSCreateHashTableWithZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCreateHashTableWithZone_ADDR, NSCreateHashTableWithZone_DESC)

fun NSCreateHashTableWithZone(arg0: MemorySegment, arg1: Long, arg2: MemorySegment): MemorySegment {
    try {
        return NSCreateHashTableWithZone_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCreateHashTable typedef NSHashTable = (Void)*(typedef NSHashTableCallBacks = Declared(NSHashTableCallBacks),typedef NSUInteger = UNSIGNED = Long)
 */
private val NSCreateHashTable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, NSHashTableCallBacks.layout, ValueLayout.JAVA_LONG)
private val NSCreateHashTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCreateHashTable").orElseThrow()
private val NSCreateHashTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCreateHashTable_ADDR, NSCreateHashTable_DESC)

fun NSCreateHashTable(arg0: MemorySegment, arg1: Long): MemorySegment {
    try {
        return NSCreateHashTable_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSIntegerHashCallBacks typedef const NSHashTableCallBacks = Declared(NSHashTableCallBacks)
 */
private val NSIntegerHashCallBacks_LAYOUT: MemoryLayout by lazy { NSHashTableCallBacks.layout }
private val NSIntegerHashCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIntegerHashCallBacks").orElseThrow() }
private val NSIntegerHashCallBacks_VH: VarHandle by lazy { NSIntegerHashCallBacks_LAYOUT.varHandle() }

var NSIntegerHashCallBacks: MemorySegment
    get() = NSIntegerHashCallBacks_VH.get(NSIntegerHashCallBacks_SEGMENT) as MemorySegment
    set(value) = NSIntegerHashCallBacks_VH.set(NSIntegerHashCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSNonOwnedPointerHashCallBacks typedef const NSHashTableCallBacks = Declared(NSHashTableCallBacks)
 */
private val NSNonOwnedPointerHashCallBacks_LAYOUT: MemoryLayout by lazy { NSHashTableCallBacks.layout }
private val NSNonOwnedPointerHashCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNonOwnedPointerHashCallBacks").orElseThrow() }
private val NSNonOwnedPointerHashCallBacks_VH: VarHandle by lazy { NSNonOwnedPointerHashCallBacks_LAYOUT.varHandle() }

var NSNonOwnedPointerHashCallBacks: MemorySegment
    get() = NSNonOwnedPointerHashCallBacks_VH.get(NSNonOwnedPointerHashCallBacks_SEGMENT) as MemorySegment
    set(value) = NSNonOwnedPointerHashCallBacks_VH.set(NSNonOwnedPointerHashCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSNonRetainedObjectHashCallBacks typedef const NSHashTableCallBacks = Declared(NSHashTableCallBacks)
 */
private val NSNonRetainedObjectHashCallBacks_LAYOUT: MemoryLayout by lazy { NSHashTableCallBacks.layout }
private val NSNonRetainedObjectHashCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNonRetainedObjectHashCallBacks").orElseThrow() }
private val NSNonRetainedObjectHashCallBacks_VH: VarHandle by lazy { NSNonRetainedObjectHashCallBacks_LAYOUT.varHandle() }

var NSNonRetainedObjectHashCallBacks: MemorySegment
    get() = NSNonRetainedObjectHashCallBacks_VH.get(NSNonRetainedObjectHashCallBacks_SEGMENT) as MemorySegment
    set(value) = NSNonRetainedObjectHashCallBacks_VH.set(NSNonRetainedObjectHashCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSObjectHashCallBacks typedef const NSHashTableCallBacks = Declared(NSHashTableCallBacks)
 */
private val NSObjectHashCallBacks_LAYOUT: MemoryLayout by lazy { NSHashTableCallBacks.layout }
private val NSObjectHashCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSObjectHashCallBacks").orElseThrow() }
private val NSObjectHashCallBacks_VH: VarHandle by lazy { NSObjectHashCallBacks_LAYOUT.varHandle() }

var NSObjectHashCallBacks: MemorySegment
    get() = NSObjectHashCallBacks_VH.get(NSObjectHashCallBacks_SEGMENT) as MemorySegment
    set(value) = NSObjectHashCallBacks_VH.set(NSObjectHashCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSOwnedObjectIdentityHashCallBacks typedef const NSHashTableCallBacks = Declared(NSHashTableCallBacks)
 */
private val NSOwnedObjectIdentityHashCallBacks_LAYOUT: MemoryLayout by lazy { NSHashTableCallBacks.layout }
private val NSOwnedObjectIdentityHashCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOwnedObjectIdentityHashCallBacks").orElseThrow() }
private val NSOwnedObjectIdentityHashCallBacks_VH: VarHandle by lazy { NSOwnedObjectIdentityHashCallBacks_LAYOUT.varHandle() }

var NSOwnedObjectIdentityHashCallBacks: MemorySegment
    get() = NSOwnedObjectIdentityHashCallBacks_VH.get(NSOwnedObjectIdentityHashCallBacks_SEGMENT) as MemorySegment
    set(value) = NSOwnedObjectIdentityHashCallBacks_VH.set(NSOwnedObjectIdentityHashCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSOwnedPointerHashCallBacks typedef const NSHashTableCallBacks = Declared(NSHashTableCallBacks)
 */
private val NSOwnedPointerHashCallBacks_LAYOUT: MemoryLayout by lazy { NSHashTableCallBacks.layout }
private val NSOwnedPointerHashCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOwnedPointerHashCallBacks").orElseThrow() }
private val NSOwnedPointerHashCallBacks_VH: VarHandle by lazy { NSOwnedPointerHashCallBacks_LAYOUT.varHandle() }

var NSOwnedPointerHashCallBacks: MemorySegment
    get() = NSOwnedPointerHashCallBacks_VH.get(NSOwnedPointerHashCallBacks_SEGMENT) as MemorySegment
    set(value) = NSOwnedPointerHashCallBacks_VH.set(NSOwnedPointerHashCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSPointerToStructHashCallBacks typedef const NSHashTableCallBacks = Declared(NSHashTableCallBacks)
 */
private val NSPointerToStructHashCallBacks_LAYOUT: MemoryLayout by lazy { NSHashTableCallBacks.layout }
private val NSPointerToStructHashCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPointerToStructHashCallBacks").orElseThrow() }
private val NSPointerToStructHashCallBacks_VH: VarHandle by lazy { NSPointerToStructHashCallBacks_LAYOUT.varHandle() }

var NSPointerToStructHashCallBacks: MemorySegment
    get() = NSPointerToStructHashCallBacks_VH.get(NSPointerToStructHashCallBacks_SEGMENT) as MemorySegment
    set(value) = NSPointerToStructHashCallBacks_VH.set(NSPointerToStructHashCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSIntHashCallBacks typedef const NSHashTableCallBacks = Declared(NSHashTableCallBacks)
 */
private val NSIntHashCallBacks_LAYOUT: MemoryLayout by lazy { NSHashTableCallBacks.layout }
private val NSIntHashCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIntHashCallBacks").orElseThrow() }
private val NSIntHashCallBacks_VH: VarHandle by lazy { NSIntHashCallBacks_LAYOUT.varHandle() }

var NSIntHashCallBacks: MemorySegment
    get() = NSIntHashCallBacks_VH.get(NSIntHashCallBacks_SEGMENT) as MemorySegment
    set(value) = NSIntHashCallBacks_VH.set(NSIntHashCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieName typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieName_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieName_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieName").orElseThrow() }
private val NSHTTPCookieName_VH: VarHandle by lazy { NSHTTPCookieName_LAYOUT.varHandle() }

var NSHTTPCookieName: MemorySegment
    get() = NSHTTPCookieName_VH.get(NSHTTPCookieName_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieName_VH.set(NSHTTPCookieName_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieValue typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieValue_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieValue_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieValue").orElseThrow() }
private val NSHTTPCookieValue_VH: VarHandle by lazy { NSHTTPCookieValue_LAYOUT.varHandle() }

var NSHTTPCookieValue: MemorySegment
    get() = NSHTTPCookieValue_VH.get(NSHTTPCookieValue_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieValue_VH.set(NSHTTPCookieValue_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieOriginURL typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieOriginURL_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieOriginURL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieOriginURL").orElseThrow() }
private val NSHTTPCookieOriginURL_VH: VarHandle by lazy { NSHTTPCookieOriginURL_LAYOUT.varHandle() }

var NSHTTPCookieOriginURL: MemorySegment
    get() = NSHTTPCookieOriginURL_VH.get(NSHTTPCookieOriginURL_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieOriginURL_VH.set(NSHTTPCookieOriginURL_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieVersion typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieVersion_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieVersion_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieVersion").orElseThrow() }
private val NSHTTPCookieVersion_VH: VarHandle by lazy { NSHTTPCookieVersion_LAYOUT.varHandle() }

var NSHTTPCookieVersion: MemorySegment
    get() = NSHTTPCookieVersion_VH.get(NSHTTPCookieVersion_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieVersion_VH.set(NSHTTPCookieVersion_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieDomain typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieDomain").orElseThrow() }
private val NSHTTPCookieDomain_VH: VarHandle by lazy { NSHTTPCookieDomain_LAYOUT.varHandle() }

var NSHTTPCookieDomain: MemorySegment
    get() = NSHTTPCookieDomain_VH.get(NSHTTPCookieDomain_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieDomain_VH.set(NSHTTPCookieDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookiePath typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookiePath_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookiePath_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookiePath").orElseThrow() }
private val NSHTTPCookiePath_VH: VarHandle by lazy { NSHTTPCookiePath_LAYOUT.varHandle() }

var NSHTTPCookiePath: MemorySegment
    get() = NSHTTPCookiePath_VH.get(NSHTTPCookiePath_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookiePath_VH.set(NSHTTPCookiePath_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieSecure typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieSecure_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieSecure_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieSecure").orElseThrow() }
private val NSHTTPCookieSecure_VH: VarHandle by lazy { NSHTTPCookieSecure_LAYOUT.varHandle() }

var NSHTTPCookieSecure: MemorySegment
    get() = NSHTTPCookieSecure_VH.get(NSHTTPCookieSecure_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieSecure_VH.set(NSHTTPCookieSecure_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieExpires typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieExpires_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieExpires_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieExpires").orElseThrow() }
private val NSHTTPCookieExpires_VH: VarHandle by lazy { NSHTTPCookieExpires_LAYOUT.varHandle() }

var NSHTTPCookieExpires: MemorySegment
    get() = NSHTTPCookieExpires_VH.get(NSHTTPCookieExpires_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieExpires_VH.set(NSHTTPCookieExpires_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieComment typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieComment_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieComment_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieComment").orElseThrow() }
private val NSHTTPCookieComment_VH: VarHandle by lazy { NSHTTPCookieComment_LAYOUT.varHandle() }

var NSHTTPCookieComment: MemorySegment
    get() = NSHTTPCookieComment_VH.get(NSHTTPCookieComment_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieComment_VH.set(NSHTTPCookieComment_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieCommentURL typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieCommentURL_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieCommentURL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieCommentURL").orElseThrow() }
private val NSHTTPCookieCommentURL_VH: VarHandle by lazy { NSHTTPCookieCommentURL_LAYOUT.varHandle() }

var NSHTTPCookieCommentURL: MemorySegment
    get() = NSHTTPCookieCommentURL_VH.get(NSHTTPCookieCommentURL_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieCommentURL_VH.set(NSHTTPCookieCommentURL_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieDiscard typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieDiscard_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieDiscard_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieDiscard").orElseThrow() }
private val NSHTTPCookieDiscard_VH: VarHandle by lazy { NSHTTPCookieDiscard_LAYOUT.varHandle() }

var NSHTTPCookieDiscard: MemorySegment
    get() = NSHTTPCookieDiscard_VH.get(NSHTTPCookieDiscard_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieDiscard_VH.set(NSHTTPCookieDiscard_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieMaximumAge typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieMaximumAge_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieMaximumAge_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieMaximumAge").orElseThrow() }
private val NSHTTPCookieMaximumAge_VH: VarHandle by lazy { NSHTTPCookieMaximumAge_LAYOUT.varHandle() }

var NSHTTPCookieMaximumAge: MemorySegment
    get() = NSHTTPCookieMaximumAge_VH.get(NSHTTPCookieMaximumAge_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieMaximumAge_VH.set(NSHTTPCookieMaximumAge_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookiePort typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookiePort_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookiePort_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookiePort").orElseThrow() }
private val NSHTTPCookiePort_VH: VarHandle by lazy { NSHTTPCookiePort_LAYOUT.varHandle() }

var NSHTTPCookiePort: MemorySegment
    get() = NSHTTPCookiePort_VH.get(NSHTTPCookiePort_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookiePort_VH.set(NSHTTPCookiePort_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieSetByJavaScript typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieSetByJavaScript_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieSetByJavaScript_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieSetByJavaScript").orElseThrow() }
private val NSHTTPCookieSetByJavaScript_VH: VarHandle by lazy { NSHTTPCookieSetByJavaScript_LAYOUT.varHandle() }

var NSHTTPCookieSetByJavaScript: MemorySegment
    get() = NSHTTPCookieSetByJavaScript_VH.get(NSHTTPCookieSetByJavaScript_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieSetByJavaScript_VH.set(NSHTTPCookieSetByJavaScript_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieSameSitePolicy typedef const NSHTTPCookiePropertyKey = (Void)*
 */
private val NSHTTPCookieSameSitePolicy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieSameSitePolicy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieSameSitePolicy").orElseThrow() }
private val NSHTTPCookieSameSitePolicy_VH: VarHandle by lazy { NSHTTPCookieSameSitePolicy_LAYOUT.varHandle() }

var NSHTTPCookieSameSitePolicy: MemorySegment
    get() = NSHTTPCookieSameSitePolicy_VH.get(NSHTTPCookieSameSitePolicy_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieSameSitePolicy_VH.set(NSHTTPCookieSameSitePolicy_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieSameSiteLax typedef const NSHTTPCookieStringPolicy = (Void)*
 */
private val NSHTTPCookieSameSiteLax_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieSameSiteLax_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieSameSiteLax").orElseThrow() }
private val NSHTTPCookieSameSiteLax_VH: VarHandle by lazy { NSHTTPCookieSameSiteLax_LAYOUT.varHandle() }

var NSHTTPCookieSameSiteLax: MemorySegment
    get() = NSHTTPCookieSameSiteLax_VH.get(NSHTTPCookieSameSiteLax_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieSameSiteLax_VH.set(NSHTTPCookieSameSiteLax_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieSameSiteStrict typedef const NSHTTPCookieStringPolicy = (Void)*
 */
private val NSHTTPCookieSameSiteStrict_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieSameSiteStrict_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieSameSiteStrict").orElseThrow() }
private val NSHTTPCookieSameSiteStrict_VH: VarHandle by lazy { NSHTTPCookieSameSiteStrict_LAYOUT.varHandle() }

var NSHTTPCookieSameSiteStrict: MemorySegment
    get() = NSHTTPCookieSameSiteStrict_VH.get(NSHTTPCookieSameSiteStrict_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieSameSiteStrict_VH.set(NSHTTPCookieSameSiteStrict_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieManagerAcceptPolicyChangedNotification typedef const NSNotificationName = (Void)*
 */
private val NSHTTPCookieManagerAcceptPolicyChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieManagerAcceptPolicyChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieManagerAcceptPolicyChangedNotification").orElseThrow() }
private val NSHTTPCookieManagerAcceptPolicyChangedNotification_VH: VarHandle by lazy { NSHTTPCookieManagerAcceptPolicyChangedNotification_LAYOUT.varHandle() }

var NSHTTPCookieManagerAcceptPolicyChangedNotification: MemorySegment
    get() = NSHTTPCookieManagerAcceptPolicyChangedNotification_VH.get(NSHTTPCookieManagerAcceptPolicyChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieManagerAcceptPolicyChangedNotification_VH.set(NSHTTPCookieManagerAcceptPolicyChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSHTTPCookieManagerCookiesChangedNotification typedef const NSNotificationName = (Void)*
 */
private val NSHTTPCookieManagerCookiesChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSHTTPCookieManagerCookiesChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSHTTPCookieManagerCookiesChangedNotification").orElseThrow() }
private val NSHTTPCookieManagerCookiesChangedNotification_VH: VarHandle by lazy { NSHTTPCookieManagerCookiesChangedNotification_LAYOUT.varHandle() }

var NSHTTPCookieManagerCookiesChangedNotification: MemorySegment
    get() = NSHTTPCookieManagerCookiesChangedNotification_VH.get(NSHTTPCookieManagerCookiesChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSHTTPCookieManagerCookiesChangedNotification_VH.set(NSHTTPCookieManagerCookiesChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUndefinedKeyException typedef const NSExceptionName = (Void)*
 */
private val NSUndefinedKeyException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUndefinedKeyException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUndefinedKeyException").orElseThrow() }
private val NSUndefinedKeyException_VH: VarHandle by lazy { NSUndefinedKeyException_LAYOUT.varHandle() }

var NSUndefinedKeyException: MemorySegment
    get() = NSUndefinedKeyException_VH.get(NSUndefinedKeyException_SEGMENT) as MemorySegment
    set(value) = NSUndefinedKeyException_VH.set(NSUndefinedKeyException_SEGMENT, value)

/**
 * {@snippet lang=c : NSAverageKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSAverageKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSAverageKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSAverageKeyValueOperator").orElseThrow() }
private val NSAverageKeyValueOperator_VH: VarHandle by lazy { NSAverageKeyValueOperator_LAYOUT.varHandle() }

var NSAverageKeyValueOperator: MemorySegment
    get() = NSAverageKeyValueOperator_VH.get(NSAverageKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSAverageKeyValueOperator_VH.set(NSAverageKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSCountKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSCountKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSCountKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSCountKeyValueOperator").orElseThrow() }
private val NSCountKeyValueOperator_VH: VarHandle by lazy { NSCountKeyValueOperator_LAYOUT.varHandle() }

var NSCountKeyValueOperator: MemorySegment
    get() = NSCountKeyValueOperator_VH.get(NSCountKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSCountKeyValueOperator_VH.set(NSCountKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSDistinctUnionOfArraysKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSDistinctUnionOfArraysKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDistinctUnionOfArraysKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDistinctUnionOfArraysKeyValueOperator").orElseThrow() }
private val NSDistinctUnionOfArraysKeyValueOperator_VH: VarHandle by lazy { NSDistinctUnionOfArraysKeyValueOperator_LAYOUT.varHandle() }

var NSDistinctUnionOfArraysKeyValueOperator: MemorySegment
    get() = NSDistinctUnionOfArraysKeyValueOperator_VH.get(NSDistinctUnionOfArraysKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSDistinctUnionOfArraysKeyValueOperator_VH.set(NSDistinctUnionOfArraysKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSDistinctUnionOfObjectsKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSDistinctUnionOfObjectsKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDistinctUnionOfObjectsKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDistinctUnionOfObjectsKeyValueOperator").orElseThrow() }
private val NSDistinctUnionOfObjectsKeyValueOperator_VH: VarHandle by lazy { NSDistinctUnionOfObjectsKeyValueOperator_LAYOUT.varHandle() }

var NSDistinctUnionOfObjectsKeyValueOperator: MemorySegment
    get() = NSDistinctUnionOfObjectsKeyValueOperator_VH.get(NSDistinctUnionOfObjectsKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSDistinctUnionOfObjectsKeyValueOperator_VH.set(NSDistinctUnionOfObjectsKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSDistinctUnionOfSetsKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSDistinctUnionOfSetsKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDistinctUnionOfSetsKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDistinctUnionOfSetsKeyValueOperator").orElseThrow() }
private val NSDistinctUnionOfSetsKeyValueOperator_VH: VarHandle by lazy { NSDistinctUnionOfSetsKeyValueOperator_LAYOUT.varHandle() }

var NSDistinctUnionOfSetsKeyValueOperator: MemorySegment
    get() = NSDistinctUnionOfSetsKeyValueOperator_VH.get(NSDistinctUnionOfSetsKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSDistinctUnionOfSetsKeyValueOperator_VH.set(NSDistinctUnionOfSetsKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSMaximumKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSMaximumKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMaximumKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMaximumKeyValueOperator").orElseThrow() }
private val NSMaximumKeyValueOperator_VH: VarHandle by lazy { NSMaximumKeyValueOperator_LAYOUT.varHandle() }

var NSMaximumKeyValueOperator: MemorySegment
    get() = NSMaximumKeyValueOperator_VH.get(NSMaximumKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSMaximumKeyValueOperator_VH.set(NSMaximumKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSMinimumKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSMinimumKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSMinimumKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSMinimumKeyValueOperator").orElseThrow() }
private val NSMinimumKeyValueOperator_VH: VarHandle by lazy { NSMinimumKeyValueOperator_LAYOUT.varHandle() }

var NSMinimumKeyValueOperator: MemorySegment
    get() = NSMinimumKeyValueOperator_VH.get(NSMinimumKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSMinimumKeyValueOperator_VH.set(NSMinimumKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSSumKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSSumKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSumKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSumKeyValueOperator").orElseThrow() }
private val NSSumKeyValueOperator_VH: VarHandle by lazy { NSSumKeyValueOperator_LAYOUT.varHandle() }

var NSSumKeyValueOperator: MemorySegment
    get() = NSSumKeyValueOperator_VH.get(NSSumKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSSumKeyValueOperator_VH.set(NSSumKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSUnionOfArraysKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSUnionOfArraysKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUnionOfArraysKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUnionOfArraysKeyValueOperator").orElseThrow() }
private val NSUnionOfArraysKeyValueOperator_VH: VarHandle by lazy { NSUnionOfArraysKeyValueOperator_LAYOUT.varHandle() }

var NSUnionOfArraysKeyValueOperator: MemorySegment
    get() = NSUnionOfArraysKeyValueOperator_VH.get(NSUnionOfArraysKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSUnionOfArraysKeyValueOperator_VH.set(NSUnionOfArraysKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSUnionOfObjectsKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSUnionOfObjectsKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUnionOfObjectsKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUnionOfObjectsKeyValueOperator").orElseThrow() }
private val NSUnionOfObjectsKeyValueOperator_VH: VarHandle by lazy { NSUnionOfObjectsKeyValueOperator_LAYOUT.varHandle() }

var NSUnionOfObjectsKeyValueOperator: MemorySegment
    get() = NSUnionOfObjectsKeyValueOperator_VH.get(NSUnionOfObjectsKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSUnionOfObjectsKeyValueOperator_VH.set(NSUnionOfObjectsKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSUnionOfSetsKeyValueOperator typedef const NSKeyValueOperator = (Void)*
 */
private val NSUnionOfSetsKeyValueOperator_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUnionOfSetsKeyValueOperator_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUnionOfSetsKeyValueOperator").orElseThrow() }
private val NSUnionOfSetsKeyValueOperator_VH: VarHandle by lazy { NSUnionOfSetsKeyValueOperator_LAYOUT.varHandle() }

var NSUnionOfSetsKeyValueOperator: MemorySegment
    get() = NSUnionOfSetsKeyValueOperator_VH.get(NSUnionOfSetsKeyValueOperator_SEGMENT) as MemorySegment
    set(value) = NSUnionOfSetsKeyValueOperator_VH.set(NSUnionOfSetsKeyValueOperator_SEGMENT, value)

/**
 * {@snippet lang=c : NSKeyValueChangeKindKey typedef const NSKeyValueChangeKey = (Void)*
 */
private val NSKeyValueChangeKindKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSKeyValueChangeKindKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSKeyValueChangeKindKey").orElseThrow() }
private val NSKeyValueChangeKindKey_VH: VarHandle by lazy { NSKeyValueChangeKindKey_LAYOUT.varHandle() }

var NSKeyValueChangeKindKey: MemorySegment
    get() = NSKeyValueChangeKindKey_VH.get(NSKeyValueChangeKindKey_SEGMENT) as MemorySegment
    set(value) = NSKeyValueChangeKindKey_VH.set(NSKeyValueChangeKindKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSKeyValueChangeNewKey typedef const NSKeyValueChangeKey = (Void)*
 */
private val NSKeyValueChangeNewKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSKeyValueChangeNewKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSKeyValueChangeNewKey").orElseThrow() }
private val NSKeyValueChangeNewKey_VH: VarHandle by lazy { NSKeyValueChangeNewKey_LAYOUT.varHandle() }

var NSKeyValueChangeNewKey: MemorySegment
    get() = NSKeyValueChangeNewKey_VH.get(NSKeyValueChangeNewKey_SEGMENT) as MemorySegment
    set(value) = NSKeyValueChangeNewKey_VH.set(NSKeyValueChangeNewKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSKeyValueChangeOldKey typedef const NSKeyValueChangeKey = (Void)*
 */
private val NSKeyValueChangeOldKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSKeyValueChangeOldKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSKeyValueChangeOldKey").orElseThrow() }
private val NSKeyValueChangeOldKey_VH: VarHandle by lazy { NSKeyValueChangeOldKey_LAYOUT.varHandle() }

var NSKeyValueChangeOldKey: MemorySegment
    get() = NSKeyValueChangeOldKey_VH.get(NSKeyValueChangeOldKey_SEGMENT) as MemorySegment
    set(value) = NSKeyValueChangeOldKey_VH.set(NSKeyValueChangeOldKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSKeyValueChangeIndexesKey typedef const NSKeyValueChangeKey = (Void)*
 */
private val NSKeyValueChangeIndexesKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSKeyValueChangeIndexesKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSKeyValueChangeIndexesKey").orElseThrow() }
private val NSKeyValueChangeIndexesKey_VH: VarHandle by lazy { NSKeyValueChangeIndexesKey_LAYOUT.varHandle() }

var NSKeyValueChangeIndexesKey: MemorySegment
    get() = NSKeyValueChangeIndexesKey_VH.get(NSKeyValueChangeIndexesKey_SEGMENT) as MemorySegment
    set(value) = NSKeyValueChangeIndexesKey_VH.set(NSKeyValueChangeIndexesKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSKeyValueChangeNotificationIsPriorKey typedef const NSKeyValueChangeKey = (Void)*
 */
private val NSKeyValueChangeNotificationIsPriorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSKeyValueChangeNotificationIsPriorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSKeyValueChangeNotificationIsPriorKey").orElseThrow() }
private val NSKeyValueChangeNotificationIsPriorKey_VH: VarHandle by lazy { NSKeyValueChangeNotificationIsPriorKey_LAYOUT.varHandle() }

var NSKeyValueChangeNotificationIsPriorKey: MemorySegment
    get() = NSKeyValueChangeNotificationIsPriorKey_VH.get(NSKeyValueChangeNotificationIsPriorKey_SEGMENT) as MemorySegment
    set(value) = NSKeyValueChangeNotificationIsPriorKey_VH.set(NSKeyValueChangeNotificationIsPriorKey_SEGMENT, value)

/**
 * {@snippet lang=c : CGPointZero typedef const CGPoint = Declared(CGPoint)
 */
private val CGPointZero_LAYOUT: MemoryLayout by lazy { CGPoint.layout }
private val CGPointZero_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CGPointZero").orElseThrow() }
private val CGPointZero_VH: VarHandle by lazy { CGPointZero_LAYOUT.varHandle() }

var CGPointZero: MemorySegment
    get() = CGPointZero_VH.get(CGPointZero_SEGMENT) as MemorySegment
    set(value) = CGPointZero_VH.set(CGPointZero_SEGMENT, value)

/**
 * {@snippet lang=c : CGSizeZero typedef const CGSize = Declared(CGSize)
 */
private val CGSizeZero_LAYOUT: MemoryLayout by lazy { CGSize.layout }
private val CGSizeZero_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CGSizeZero").orElseThrow() }
private val CGSizeZero_VH: VarHandle by lazy { CGSizeZero_LAYOUT.varHandle() }

var CGSizeZero: MemorySegment
    get() = CGSizeZero_VH.get(CGSizeZero_SEGMENT) as MemorySegment
    set(value) = CGSizeZero_VH.set(CGSizeZero_SEGMENT, value)

/**
 * {@snippet lang=c : CGRectZero typedef const CGRect = Declared(CGRect)
 */
private val CGRectZero_LAYOUT: MemoryLayout by lazy { CGRect.layout }
private val CGRectZero_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CGRectZero").orElseThrow() }
private val CGRectZero_VH: VarHandle by lazy { CGRectZero_LAYOUT.varHandle() }

var CGRectZero: MemorySegment
    get() = CGRectZero_VH.get(CGRectZero_SEGMENT) as MemorySegment
    set(value) = CGRectZero_VH.set(CGRectZero_SEGMENT, value)

/**
 * {@snippet lang=c : CGRectNull typedef const CGRect = Declared(CGRect)
 */
private val CGRectNull_LAYOUT: MemoryLayout by lazy { CGRect.layout }
private val CGRectNull_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CGRectNull").orElseThrow() }
private val CGRectNull_VH: VarHandle by lazy { CGRectNull_LAYOUT.varHandle() }

var CGRectNull: MemorySegment
    get() = CGRectNull_VH.get(CGRectNull_SEGMENT) as MemorySegment
    set(value) = CGRectNull_VH.set(CGRectNull_SEGMENT, value)

/**
 * {@snippet lang=c : CGRectInfinite typedef const CGRect = Declared(CGRect)
 */
private val CGRectInfinite_LAYOUT: MemoryLayout by lazy { CGRect.layout }
private val CGRectInfinite_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("CGRectInfinite").orElseThrow() }
private val CGRectInfinite_VH: VarHandle by lazy { CGRectInfinite_LAYOUT.varHandle() }

var CGRectInfinite: MemorySegment
    get() = CGRectInfinite_VH.get(CGRectInfinite_SEGMENT) as MemorySegment
    set(value) = CGRectInfinite_VH.set(CGRectInfinite_SEGMENT, value)

/**
 * {@snippet lang=c : CGRectGetMinX typedef CGFloat = Double(typedef CGRect = Declared(CGRect))
 */
private val CGRectGetMinX_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, CGRect.layout)
private val CGRectGetMinX_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectGetMinX").orElseThrow()
private val CGRectGetMinX_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectGetMinX_ADDR, CGRectGetMinX_DESC)

fun CGRectGetMinX(arg0: MemorySegment): Double {
    try {
        return CGRectGetMinX_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectGetMidX typedef CGFloat = Double(typedef CGRect = Declared(CGRect))
 */
private val CGRectGetMidX_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, CGRect.layout)
private val CGRectGetMidX_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectGetMidX").orElseThrow()
private val CGRectGetMidX_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectGetMidX_ADDR, CGRectGetMidX_DESC)

fun CGRectGetMidX(arg0: MemorySegment): Double {
    try {
        return CGRectGetMidX_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectGetMaxX typedef CGFloat = Double(typedef CGRect = Declared(CGRect))
 */
private val CGRectGetMaxX_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, CGRect.layout)
private val CGRectGetMaxX_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectGetMaxX").orElseThrow()
private val CGRectGetMaxX_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectGetMaxX_ADDR, CGRectGetMaxX_DESC)

fun CGRectGetMaxX(arg0: MemorySegment): Double {
    try {
        return CGRectGetMaxX_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectGetMinY typedef CGFloat = Double(typedef CGRect = Declared(CGRect))
 */
private val CGRectGetMinY_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, CGRect.layout)
private val CGRectGetMinY_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectGetMinY").orElseThrow()
private val CGRectGetMinY_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectGetMinY_ADDR, CGRectGetMinY_DESC)

fun CGRectGetMinY(arg0: MemorySegment): Double {
    try {
        return CGRectGetMinY_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectGetMidY typedef CGFloat = Double(typedef CGRect = Declared(CGRect))
 */
private val CGRectGetMidY_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, CGRect.layout)
private val CGRectGetMidY_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectGetMidY").orElseThrow()
private val CGRectGetMidY_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectGetMidY_ADDR, CGRectGetMidY_DESC)

fun CGRectGetMidY(arg0: MemorySegment): Double {
    try {
        return CGRectGetMidY_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectGetMaxY typedef CGFloat = Double(typedef CGRect = Declared(CGRect))
 */
private val CGRectGetMaxY_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, CGRect.layout)
private val CGRectGetMaxY_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectGetMaxY").orElseThrow()
private val CGRectGetMaxY_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectGetMaxY_ADDR, CGRectGetMaxY_DESC)

fun CGRectGetMaxY(arg0: MemorySegment): Double {
    try {
        return CGRectGetMaxY_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectGetWidth typedef CGFloat = Double(typedef CGRect = Declared(CGRect))
 */
private val CGRectGetWidth_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, CGRect.layout)
private val CGRectGetWidth_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectGetWidth").orElseThrow()
private val CGRectGetWidth_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectGetWidth_ADDR, CGRectGetWidth_DESC)

fun CGRectGetWidth(arg0: MemorySegment): Double {
    try {
        return CGRectGetWidth_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectGetHeight typedef CGFloat = Double(typedef CGRect = Declared(CGRect))
 */
private val CGRectGetHeight_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_DOUBLE, CGRect.layout)
private val CGRectGetHeight_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectGetHeight").orElseThrow()
private val CGRectGetHeight_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectGetHeight_ADDR, CGRectGetHeight_DESC)

fun CGRectGetHeight(arg0: MemorySegment): Double {
    try {
        return CGRectGetHeight_HANDLE.invokeExact(arg0) as Double
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPointEqualToPoint Bool(typedef CGPoint = Declared(CGPoint),typedef CGPoint = Declared(CGPoint))
 */
private val CGPointEqualToPoint_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGPoint.layout, CGPoint.layout)
private val CGPointEqualToPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPointEqualToPoint").orElseThrow()
private val CGPointEqualToPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPointEqualToPoint_ADDR, CGPointEqualToPoint_DESC)

fun CGPointEqualToPoint(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPointEqualToPoint_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGSizeEqualToSize Bool(typedef CGSize = Declared(CGSize),typedef CGSize = Declared(CGSize))
 */
private val CGSizeEqualToSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGSize.layout, CGSize.layout)
private val CGSizeEqualToSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGSizeEqualToSize").orElseThrow()
private val CGSizeEqualToSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGSizeEqualToSize_ADDR, CGSizeEqualToSize_DESC)

fun CGSizeEqualToSize(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGSizeEqualToSize_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectEqualToRect Bool(typedef CGRect = Declared(CGRect),typedef CGRect = Declared(CGRect))
 */
private val CGRectEqualToRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout, CGRect.layout)
private val CGRectEqualToRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectEqualToRect").orElseThrow()
private val CGRectEqualToRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectEqualToRect_ADDR, CGRectEqualToRect_DESC)

fun CGRectEqualToRect(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGRectEqualToRect_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectStandardize typedef CGRect = Declared(CGRect)(typedef CGRect = Declared(CGRect))
 */
private val CGRectStandardize_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout)
private val CGRectStandardize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectStandardize").orElseThrow()
private val CGRectStandardize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectStandardize_ADDR, CGRectStandardize_DESC)

fun CGRectStandardize(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGRectStandardize_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectIsEmpty Bool(typedef CGRect = Declared(CGRect))
 */
private val CGRectIsEmpty_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout)
private val CGRectIsEmpty_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectIsEmpty").orElseThrow()
private val CGRectIsEmpty_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectIsEmpty_ADDR, CGRectIsEmpty_DESC)

fun CGRectIsEmpty(arg0: MemorySegment): Boolean {
    try {
        return CGRectIsEmpty_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectIsNull Bool(typedef CGRect = Declared(CGRect))
 */
private val CGRectIsNull_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout)
private val CGRectIsNull_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectIsNull").orElseThrow()
private val CGRectIsNull_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectIsNull_ADDR, CGRectIsNull_DESC)

fun CGRectIsNull(arg0: MemorySegment): Boolean {
    try {
        return CGRectIsNull_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectIsInfinite Bool(typedef CGRect = Declared(CGRect))
 */
private val CGRectIsInfinite_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout)
private val CGRectIsInfinite_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectIsInfinite").orElseThrow()
private val CGRectIsInfinite_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectIsInfinite_ADDR, CGRectIsInfinite_DESC)

fun CGRectIsInfinite(arg0: MemorySegment): Boolean {
    try {
        return CGRectIsInfinite_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectInset typedef CGRect = Declared(CGRect)(typedef CGRect = Declared(CGRect),typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGRectInset_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGRectInset_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectInset").orElseThrow()
private val CGRectInset_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectInset_ADDR, CGRectInset_DESC)

fun CGRectInset(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Double, arg2: Double): MemorySegment {
    try {
        return CGRectInset_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectIntegral typedef CGRect = Declared(CGRect)(typedef CGRect = Declared(CGRect))
 */
private val CGRectIntegral_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout)
private val CGRectIntegral_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectIntegral").orElseThrow()
private val CGRectIntegral_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectIntegral_ADDR, CGRectIntegral_DESC)

fun CGRectIntegral(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return CGRectIntegral_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectUnion typedef CGRect = Declared(CGRect)(typedef CGRect = Declared(CGRect),typedef CGRect = Declared(CGRect))
 */
private val CGRectUnion_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, CGRect.layout)
private val CGRectUnion_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectUnion").orElseThrow()
private val CGRectUnion_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectUnion_ADDR, CGRectUnion_DESC)

fun CGRectUnion(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGRectUnion_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectIntersection typedef CGRect = Declared(CGRect)(typedef CGRect = Declared(CGRect),typedef CGRect = Declared(CGRect))
 */
private val CGRectIntersection_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, CGRect.layout)
private val CGRectIntersection_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectIntersection").orElseThrow()
private val CGRectIntersection_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectIntersection_ADDR, CGRectIntersection_DESC)

fun CGRectIntersection(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return CGRectIntersection_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectOffset typedef CGRect = Declared(CGRect)(typedef CGRect = Declared(CGRect),typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val CGRectOffset_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val CGRectOffset_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectOffset").orElseThrow()
private val CGRectOffset_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectOffset_ADDR, CGRectOffset_DESC)

fun CGRectOffset(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Double, arg2: Double): MemorySegment {
    try {
        return CGRectOffset_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectContainsPoint Bool(typedef CGRect = Declared(CGRect),typedef CGPoint = Declared(CGPoint))
 */
private val CGRectContainsPoint_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout, CGPoint.layout)
private val CGRectContainsPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectContainsPoint").orElseThrow()
private val CGRectContainsPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectContainsPoint_ADDR, CGRectContainsPoint_DESC)

fun CGRectContainsPoint(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGRectContainsPoint_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectContainsRect Bool(typedef CGRect = Declared(CGRect),typedef CGRect = Declared(CGRect))
 */
private val CGRectContainsRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout, CGRect.layout)
private val CGRectContainsRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectContainsRect").orElseThrow()
private val CGRectContainsRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectContainsRect_ADDR, CGRectContainsRect_DESC)

fun CGRectContainsRect(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGRectContainsRect_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectIntersectsRect Bool(typedef CGRect = Declared(CGRect),typedef CGRect = Declared(CGRect))
 */
private val CGRectIntersectsRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout, CGRect.layout)
private val CGRectIntersectsRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectIntersectsRect").orElseThrow()
private val CGRectIntersectsRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectIntersectsRect_ADDR, CGRectIntersectsRect_DESC)

fun CGRectIntersectsRect(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGRectIntersectsRect_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPointCreateDictionaryRepresentation typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CGPoint = Declared(CGPoint))
 */
private val CGPointCreateDictionaryRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGPoint.layout)
private val CGPointCreateDictionaryRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPointCreateDictionaryRepresentation").orElseThrow()
private val CGPointCreateDictionaryRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPointCreateDictionaryRepresentation_ADDR, CGPointCreateDictionaryRepresentation_DESC)

fun CGPointCreateDictionaryRepresentation(arg0: MemorySegment): MemorySegment {
    try {
        return CGPointCreateDictionaryRepresentation_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGPointMakeWithDictionaryRepresentation Bool(typedef CFDictionaryRef = (Declared(__CFDictionary))*,(typedef CGPoint = Declared(CGPoint))*)
 */
private val CGPointMakeWithDictionaryRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGPointMakeWithDictionaryRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGPointMakeWithDictionaryRepresentation").orElseThrow()
private val CGPointMakeWithDictionaryRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGPointMakeWithDictionaryRepresentation_ADDR, CGPointMakeWithDictionaryRepresentation_DESC)

fun CGPointMakeWithDictionaryRepresentation(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGPointMakeWithDictionaryRepresentation_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGSizeCreateDictionaryRepresentation typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CGSize = Declared(CGSize))
 */
private val CGSizeCreateDictionaryRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGSize.layout)
private val CGSizeCreateDictionaryRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGSizeCreateDictionaryRepresentation").orElseThrow()
private val CGSizeCreateDictionaryRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGSizeCreateDictionaryRepresentation_ADDR, CGSizeCreateDictionaryRepresentation_DESC)

fun CGSizeCreateDictionaryRepresentation(arg0: MemorySegment): MemorySegment {
    try {
        return CGSizeCreateDictionaryRepresentation_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGSizeMakeWithDictionaryRepresentation Bool(typedef CFDictionaryRef = (Declared(__CFDictionary))*,(typedef CGSize = Declared(CGSize))*)
 */
private val CGSizeMakeWithDictionaryRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGSizeMakeWithDictionaryRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGSizeMakeWithDictionaryRepresentation").orElseThrow()
private val CGSizeMakeWithDictionaryRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGSizeMakeWithDictionaryRepresentation_ADDR, CGSizeMakeWithDictionaryRepresentation_DESC)

fun CGSizeMakeWithDictionaryRepresentation(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGSizeMakeWithDictionaryRepresentation_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectCreateDictionaryRepresentation typedef CFDictionaryRef = (Declared(__CFDictionary))*(typedef CGRect = Declared(CGRect))
 */
private val CGRectCreateDictionaryRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGRect.layout)
private val CGRectCreateDictionaryRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectCreateDictionaryRepresentation").orElseThrow()
private val CGRectCreateDictionaryRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectCreateDictionaryRepresentation_ADDR, CGRectCreateDictionaryRepresentation_DESC)

fun CGRectCreateDictionaryRepresentation(arg0: MemorySegment): MemorySegment {
    try {
        return CGRectCreateDictionaryRepresentation_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : CGRectMakeWithDictionaryRepresentation Bool(typedef CFDictionaryRef = (Declared(__CFDictionary))*,(typedef CGRect = Declared(CGRect))*)
 */
private val CGRectMakeWithDictionaryRepresentation_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val CGRectMakeWithDictionaryRepresentation_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("CGRectMakeWithDictionaryRepresentation").orElseThrow()
private val CGRectMakeWithDictionaryRepresentation_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(CGRectMakeWithDictionaryRepresentation_ADDR, CGRectMakeWithDictionaryRepresentation_DESC)

fun CGRectMakeWithDictionaryRepresentation(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return CGRectMakeWithDictionaryRepresentation_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSZeroPoint typedef const NSPoint = Declared(CGPoint)
 */
private val NSZeroPoint_LAYOUT: MemoryLayout by lazy { CGPoint.layout }
private val NSZeroPoint_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSZeroPoint").orElseThrow() }
private val NSZeroPoint_VH: VarHandle by lazy { NSZeroPoint_LAYOUT.varHandle() }

var NSZeroPoint: MemorySegment
    get() = NSZeroPoint_VH.get(NSZeroPoint_SEGMENT) as MemorySegment
    set(value) = NSZeroPoint_VH.set(NSZeroPoint_SEGMENT, value)

/**
 * {@snippet lang=c : NSZeroSize typedef const NSSize = Declared(CGSize)
 */
private val NSZeroSize_LAYOUT: MemoryLayout by lazy { CGSize.layout }
private val NSZeroSize_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSZeroSize").orElseThrow() }
private val NSZeroSize_VH: VarHandle by lazy { NSZeroSize_LAYOUT.varHandle() }

var NSZeroSize: MemorySegment
    get() = NSZeroSize_VH.get(NSZeroSize_SEGMENT) as MemorySegment
    set(value) = NSZeroSize_VH.set(NSZeroSize_SEGMENT, value)

/**
 * {@snippet lang=c : NSZeroRect typedef const NSRect = Declared(CGRect)
 */
private val NSZeroRect_LAYOUT: MemoryLayout by lazy { CGRect.layout }
private val NSZeroRect_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSZeroRect").orElseThrow() }
private val NSZeroRect_VH: VarHandle by lazy { NSZeroRect_LAYOUT.varHandle() }

var NSZeroRect: MemorySegment
    get() = NSZeroRect_VH.get(NSZeroRect_SEGMENT) as MemorySegment
    set(value) = NSZeroRect_VH.set(NSZeroRect_SEGMENT, value)

/**
 * {@snippet lang=c : NSEdgeInsetsZero typedef const NSEdgeInsets = Declared(NSEdgeInsets)
 */
private val NSEdgeInsetsZero_LAYOUT: MemoryLayout by lazy { NSEdgeInsets.layout }
private val NSEdgeInsetsZero_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSEdgeInsetsZero").orElseThrow() }
private val NSEdgeInsetsZero_VH: VarHandle by lazy { NSEdgeInsetsZero_LAYOUT.varHandle() }

var NSEdgeInsetsZero: MemorySegment
    get() = NSEdgeInsetsZero_VH.get(NSEdgeInsetsZero_SEGMENT) as MemorySegment
    set(value) = NSEdgeInsetsZero_VH.set(NSEdgeInsetsZero_SEGMENT, value)

/**
 * {@snippet lang=c : NSEqualPoints typedef BOOL = Bool(typedef NSPoint = Declared(CGPoint),typedef NSPoint = Declared(CGPoint))
 */
private val NSEqualPoints_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGPoint.layout, CGPoint.layout)
private val NSEqualPoints_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEqualPoints").orElseThrow()
private val NSEqualPoints_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEqualPoints_ADDR, NSEqualPoints_DESC)

fun NSEqualPoints(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSEqualPoints_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSEqualSizes typedef BOOL = Bool(typedef NSSize = Declared(CGSize),typedef NSSize = Declared(CGSize))
 */
private val NSEqualSizes_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGSize.layout, CGSize.layout)
private val NSEqualSizes_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEqualSizes").orElseThrow()
private val NSEqualSizes_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEqualSizes_ADDR, NSEqualSizes_DESC)

fun NSEqualSizes(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSEqualSizes_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSEqualRects typedef BOOL = Bool(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSEqualRects_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout, CGRect.layout)
private val NSEqualRects_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEqualRects").orElseThrow()
private val NSEqualRects_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEqualRects_ADDR, NSEqualRects_DESC)

fun NSEqualRects(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSEqualRects_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSIsEmptyRect typedef BOOL = Bool(typedef NSRect = Declared(CGRect))
 */
private val NSIsEmptyRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout)
private val NSIsEmptyRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSIsEmptyRect").orElseThrow()
private val NSIsEmptyRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSIsEmptyRect_ADDR, NSIsEmptyRect_DESC)

fun NSIsEmptyRect(arg0: MemorySegment): Boolean {
    try {
        return NSIsEmptyRect_HANDLE.invokeExact(arg0) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSEdgeInsetsEqual typedef BOOL = Bool(typedef NSEdgeInsets = Declared(NSEdgeInsets),typedef NSEdgeInsets = Declared(NSEdgeInsets))
 */
private val NSEdgeInsetsEqual_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, NSEdgeInsets.layout, NSEdgeInsets.layout)
private val NSEdgeInsetsEqual_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEdgeInsetsEqual").orElseThrow()
private val NSEdgeInsetsEqual_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEdgeInsetsEqual_ADDR, NSEdgeInsetsEqual_DESC)

fun NSEdgeInsetsEqual(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSEdgeInsetsEqual_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSInsetRect typedef NSRect = Declared(CGRect)(typedef NSRect = Declared(CGRect),typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val NSInsetRect_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val NSInsetRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSInsetRect").orElseThrow()
private val NSInsetRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSInsetRect_ADDR, NSInsetRect_DESC)

fun NSInsetRect(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Double, arg2: Double): MemorySegment {
    try {
        return NSInsetRect_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSIntegralRect typedef NSRect = Declared(CGRect)(typedef NSRect = Declared(CGRect))
 */
private val NSIntegralRect_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout)
private val NSIntegralRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSIntegralRect").orElseThrow()
private val NSIntegralRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSIntegralRect_ADDR, NSIntegralRect_DESC)

fun NSIntegralRect(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return NSIntegralRect_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSUnionRect typedef NSRect = Declared(CGRect)(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSUnionRect_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, CGRect.layout)
private val NSUnionRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSUnionRect").orElseThrow()
private val NSUnionRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSUnionRect_ADDR, NSUnionRect_DESC)

fun NSUnionRect(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSUnionRect_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSIntersectionRect typedef NSRect = Declared(CGRect)(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSIntersectionRect_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, CGRect.layout)
private val NSIntersectionRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSIntersectionRect").orElseThrow()
private val NSIntersectionRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSIntersectionRect_ADDR, NSIntersectionRect_DESC)

fun NSIntersectionRect(allocator: SegmentAllocator, arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSIntersectionRect_HANDLE.invokeExact(allocator, arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSOffsetRect typedef NSRect = Declared(CGRect)(typedef NSRect = Declared(CGRect),typedef CGFloat = Double,typedef CGFloat = Double)
 */
private val NSOffsetRect_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, CGRect.layout, ValueLayout.JAVA_DOUBLE, ValueLayout.JAVA_DOUBLE)
private val NSOffsetRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSOffsetRect").orElseThrow()
private val NSOffsetRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSOffsetRect_ADDR, NSOffsetRect_DESC)

fun NSOffsetRect(allocator: SegmentAllocator, arg0: MemorySegment, arg1: Double, arg2: Double): MemorySegment {
    try {
        return NSOffsetRect_HANDLE.invokeExact(allocator, arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSPointInRect typedef BOOL = Bool(typedef NSPoint = Declared(CGPoint),typedef NSRect = Declared(CGRect))
 */
private val NSPointInRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGPoint.layout, CGRect.layout)
private val NSPointInRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSPointInRect").orElseThrow()
private val NSPointInRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSPointInRect_ADDR, NSPointInRect_DESC)

fun NSPointInRect(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSPointInRect_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSMouseInRect typedef BOOL = Bool(typedef NSPoint = Declared(CGPoint),typedef NSRect = Declared(CGRect),typedef BOOL = Bool)
 */
private val NSMouseInRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGPoint.layout, CGRect.layout, ValueLayout.JAVA_BOOLEAN)
private val NSMouseInRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSMouseInRect").orElseThrow()
private val NSMouseInRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSMouseInRect_ADDR, NSMouseInRect_DESC)

fun NSMouseInRect(arg0: MemorySegment, arg1: MemorySegment, arg2: Boolean): Boolean {
    try {
        return NSMouseInRect_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSContainsRect typedef BOOL = Bool(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSContainsRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout, CGRect.layout)
private val NSContainsRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSContainsRect").orElseThrow()
private val NSContainsRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSContainsRect_ADDR, NSContainsRect_DESC)

fun NSContainsRect(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSContainsRect_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSIntersectsRect typedef BOOL = Bool(typedef NSRect = Declared(CGRect),typedef NSRect = Declared(CGRect))
 */
private val NSIntersectsRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, CGRect.layout, CGRect.layout)
private val NSIntersectsRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSIntersectsRect").orElseThrow()
private val NSIntersectsRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSIntersectsRect_ADDR, NSIntersectsRect_DESC)

fun NSIntersectsRect(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSIntersectsRect_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSStringFromPoint typedef NSString = (Void)*(typedef NSPoint = Declared(CGPoint))
 */
private val NSStringFromPoint_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGPoint.layout)
private val NSStringFromPoint_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSStringFromPoint").orElseThrow()
private val NSStringFromPoint_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSStringFromPoint_ADDR, NSStringFromPoint_DESC)

fun NSStringFromPoint(arg0: MemorySegment): MemorySegment {
    try {
        return NSStringFromPoint_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSStringFromSize typedef NSString = (Void)*(typedef NSSize = Declared(CGSize))
 */
private val NSStringFromSize_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGSize.layout)
private val NSStringFromSize_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSStringFromSize").orElseThrow()
private val NSStringFromSize_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSStringFromSize_ADDR, NSStringFromSize_DESC)

fun NSStringFromSize(arg0: MemorySegment): MemorySegment {
    try {
        return NSStringFromSize_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSStringFromRect typedef NSString = (Void)*(typedef NSRect = Declared(CGRect))
 */
private val NSStringFromRect_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, CGRect.layout)
private val NSStringFromRect_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSStringFromRect").orElseThrow()
private val NSStringFromRect_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSStringFromRect_ADDR, NSStringFromRect_DESC)

fun NSStringFromRect(arg0: MemorySegment): MemorySegment {
    try {
        return NSStringFromRect_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSPointFromString typedef NSPoint = Declared(CGPoint)(typedef NSString = (Void)*)
 */
private val NSPointFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(CGPoint.layout, ValueLayout.ADDRESS)
private val NSPointFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSPointFromString").orElseThrow()
private val NSPointFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSPointFromString_ADDR, NSPointFromString_DESC)

fun NSPointFromString(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return NSPointFromString_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSSizeFromString typedef NSSize = Declared(CGSize)(typedef NSString = (Void)*)
 */
private val NSSizeFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(CGSize.layout, ValueLayout.ADDRESS)
private val NSSizeFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSSizeFromString").orElseThrow()
private val NSSizeFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSSizeFromString_ADDR, NSSizeFromString_DESC)

fun NSSizeFromString(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return NSSizeFromString_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSRectFromString typedef NSRect = Declared(CGRect)(typedef NSString = (Void)*)
 */
private val NSRectFromString_DESC: FunctionDescriptor = FunctionDescriptor.of(CGRect.layout, ValueLayout.ADDRESS)
private val NSRectFromString_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSRectFromString").orElseThrow()
private val NSRectFromString_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSRectFromString_ADDR, NSRectFromString_DESC)

fun NSRectFromString(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return NSRectFromString_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSInvalidArchiveOperationException typedef const NSExceptionName = (Void)*
 */
private val NSInvalidArchiveOperationException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInvalidArchiveOperationException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInvalidArchiveOperationException").orElseThrow() }
private val NSInvalidArchiveOperationException_VH: VarHandle by lazy { NSInvalidArchiveOperationException_LAYOUT.varHandle() }

var NSInvalidArchiveOperationException: MemorySegment
    get() = NSInvalidArchiveOperationException_VH.get(NSInvalidArchiveOperationException_SEGMENT) as MemorySegment
    set(value) = NSInvalidArchiveOperationException_VH.set(NSInvalidArchiveOperationException_SEGMENT, value)

/**
 * {@snippet lang=c : NSInvalidUnarchiveOperationException typedef const NSExceptionName = (Void)*
 */
private val NSInvalidUnarchiveOperationException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInvalidUnarchiveOperationException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInvalidUnarchiveOperationException").orElseThrow() }
private val NSInvalidUnarchiveOperationException_VH: VarHandle by lazy { NSInvalidUnarchiveOperationException_LAYOUT.varHandle() }

var NSInvalidUnarchiveOperationException: MemorySegment
    get() = NSInvalidUnarchiveOperationException_VH.get(NSInvalidUnarchiveOperationException_SEGMENT) as MemorySegment
    set(value) = NSInvalidUnarchiveOperationException_VH.set(NSInvalidUnarchiveOperationException_SEGMENT, value)

/**
 * {@snippet lang=c : NSKeyedArchiveRootObjectKey (Void)*
 */
private val NSKeyedArchiveRootObjectKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSKeyedArchiveRootObjectKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSKeyedArchiveRootObjectKey").orElseThrow() }
private val NSKeyedArchiveRootObjectKey_VH: VarHandle by lazy { NSKeyedArchiveRootObjectKey_LAYOUT.varHandle() }

var NSKeyedArchiveRootObjectKey: MemorySegment
    get() = NSKeyedArchiveRootObjectKey_VH.get(NSKeyedArchiveRootObjectKey_SEGMENT) as MemorySegment
    set(value) = NSKeyedArchiveRootObjectKey_VH.set(NSKeyedArchiveRootObjectKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSFreeMapTable Void(typedef NSMapTable = (Void)*)
 */
private val NSFreeMapTable_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSFreeMapTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSFreeMapTable").orElseThrow()
private val NSFreeMapTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSFreeMapTable_ADDR, NSFreeMapTable_DESC)

fun NSFreeMapTable(arg0: MemorySegment): Unit {
    try {
        NSFreeMapTable_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSResetMapTable Void(typedef NSMapTable = (Void)*)
 */
private val NSResetMapTable_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSResetMapTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSResetMapTable").orElseThrow()
private val NSResetMapTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSResetMapTable_ADDR, NSResetMapTable_DESC)

fun NSResetMapTable(arg0: MemorySegment): Unit {
    try {
        NSResetMapTable_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCompareMapTables typedef BOOL = Bool(typedef NSMapTable = (Void)*,typedef NSMapTable = (Void)*)
 */
private val NSCompareMapTables_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSCompareMapTables_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCompareMapTables").orElseThrow()
private val NSCompareMapTables_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCompareMapTables_ADDR, NSCompareMapTables_DESC)

fun NSCompareMapTables(arg0: MemorySegment, arg1: MemorySegment): Boolean {
    try {
        return NSCompareMapTables_HANDLE.invokeExact(arg0, arg1) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCopyMapTableWithZone typedef NSMapTable = (Void)*(typedef NSMapTable = (Void)*,(typedef NSZone = Declared(_NSZone))*)
 */
private val NSCopyMapTableWithZone_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSCopyMapTableWithZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCopyMapTableWithZone").orElseThrow()
private val NSCopyMapTableWithZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCopyMapTableWithZone_ADDR, NSCopyMapTableWithZone_DESC)

fun NSCopyMapTableWithZone(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSCopyMapTableWithZone_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSMapMember typedef BOOL = Bool(typedef NSMapTable = (Void)*,(Void)*,((Void)*)*,((Void)*)*)
 */
private val NSMapMember_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSMapMember_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSMapMember").orElseThrow()
private val NSMapMember_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSMapMember_ADDR, NSMapMember_DESC)

fun NSMapMember(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment, arg3: MemorySegment): Boolean {
    try {
        return NSMapMember_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSMapGet (Void)*(typedef NSMapTable = (Void)*,(Void)*)
 */
private val NSMapGet_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSMapGet_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSMapGet").orElseThrow()
private val NSMapGet_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSMapGet_ADDR, NSMapGet_DESC)

fun NSMapGet(arg0: MemorySegment, arg1: MemorySegment): MemorySegment {
    try {
        return NSMapGet_HANDLE.invokeExact(arg0, arg1) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSMapInsert Void(typedef NSMapTable = (Void)*,(Void)*,(Void)*)
 */
private val NSMapInsert_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSMapInsert_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSMapInsert").orElseThrow()
private val NSMapInsert_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSMapInsert_ADDR, NSMapInsert_DESC)

fun NSMapInsert(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        NSMapInsert_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSMapInsertKnownAbsent Void(typedef NSMapTable = (Void)*,(Void)*,(Void)*)
 */
private val NSMapInsertKnownAbsent_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSMapInsertKnownAbsent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSMapInsertKnownAbsent").orElseThrow()
private val NSMapInsertKnownAbsent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSMapInsertKnownAbsent_ADDR, NSMapInsertKnownAbsent_DESC)

fun NSMapInsertKnownAbsent(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Unit {
    try {
        NSMapInsertKnownAbsent_HANDLE.invokeExact(arg0, arg1, arg2)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSMapInsertIfAbsent (Void)*(typedef NSMapTable = (Void)*,(Void)*,(Void)*)
 */
private val NSMapInsertIfAbsent_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSMapInsertIfAbsent_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSMapInsertIfAbsent").orElseThrow()
private val NSMapInsertIfAbsent_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSMapInsertIfAbsent_ADDR, NSMapInsertIfAbsent_DESC)

fun NSMapInsertIfAbsent(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): MemorySegment {
    try {
        return NSMapInsertIfAbsent_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSMapRemove Void(typedef NSMapTable = (Void)*,(Void)*)
 */
private val NSMapRemove_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSMapRemove_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSMapRemove").orElseThrow()
private val NSMapRemove_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSMapRemove_ADDR, NSMapRemove_DESC)

fun NSMapRemove(arg0: MemorySegment, arg1: MemorySegment): Unit {
    try {
        NSMapRemove_HANDLE.invokeExact(arg0, arg1)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSEnumerateMapTable typedef NSMapEnumerator = Declared(NSMapEnumerator)(typedef NSMapTable = (Void)*)
 */
private val NSEnumerateMapTable_DESC: FunctionDescriptor = FunctionDescriptor.of(NSMapEnumerator.layout, ValueLayout.ADDRESS)
private val NSEnumerateMapTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEnumerateMapTable").orElseThrow()
private val NSEnumerateMapTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEnumerateMapTable_ADDR, NSEnumerateMapTable_DESC)

fun NSEnumerateMapTable(allocator: SegmentAllocator, arg0: MemorySegment): MemorySegment {
    try {
        return NSEnumerateMapTable_HANDLE.invokeExact(allocator, arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSNextMapEnumeratorPair typedef BOOL = Bool((typedef NSMapEnumerator = Declared(NSMapEnumerator))*,((Void)*)*,((Void)*)*)
 */
private val NSNextMapEnumeratorPair_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSNextMapEnumeratorPair_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSNextMapEnumeratorPair").orElseThrow()
private val NSNextMapEnumeratorPair_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSNextMapEnumeratorPair_ADDR, NSNextMapEnumeratorPair_DESC)

fun NSNextMapEnumeratorPair(arg0: MemorySegment, arg1: MemorySegment, arg2: MemorySegment): Boolean {
    try {
        return NSNextMapEnumeratorPair_HANDLE.invokeExact(arg0, arg1, arg2) as Boolean
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSEndMapTableEnumeration Void((typedef NSMapEnumerator = Declared(NSMapEnumerator))*)
 */
private val NSEndMapTableEnumeration_DESC: FunctionDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
private val NSEndMapTableEnumeration_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSEndMapTableEnumeration").orElseThrow()
private val NSEndMapTableEnumeration_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSEndMapTableEnumeration_ADDR, NSEndMapTableEnumeration_DESC)

fun NSEndMapTableEnumeration(arg0: MemorySegment): Unit {
    try {
        NSEndMapTableEnumeration_HANDLE.invokeExact(arg0)
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCountMapTable typedef NSUInteger = UNSIGNED = Long(typedef NSMapTable = (Void)*)
 */
private val NSCountMapTable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSCountMapTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCountMapTable").orElseThrow()
private val NSCountMapTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCountMapTable_ADDR, NSCountMapTable_DESC)

fun NSCountMapTable(arg0: MemorySegment): Long {
    try {
        return NSCountMapTable_HANDLE.invokeExact(arg0) as Long
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSStringFromMapTable typedef NSString = (Void)*(typedef NSMapTable = (Void)*)
 */
private val NSStringFromMapTable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSStringFromMapTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSStringFromMapTable").orElseThrow()
private val NSStringFromMapTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSStringFromMapTable_ADDR, NSStringFromMapTable_DESC)

fun NSStringFromMapTable(arg0: MemorySegment): MemorySegment {
    try {
        return NSStringFromMapTable_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAllMapTableKeys typedef NSArray = (Void)*(typedef NSMapTable = (Void)*)
 */
private val NSAllMapTableKeys_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAllMapTableKeys_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAllMapTableKeys").orElseThrow()
private val NSAllMapTableKeys_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAllMapTableKeys_ADDR, NSAllMapTableKeys_DESC)

fun NSAllMapTableKeys(arg0: MemorySegment): MemorySegment {
    try {
        return NSAllMapTableKeys_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSAllMapTableValues typedef NSArray = (Void)*(typedef NSMapTable = (Void)*)
 */
private val NSAllMapTableValues_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
private val NSAllMapTableValues_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSAllMapTableValues").orElseThrow()
private val NSAllMapTableValues_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSAllMapTableValues_ADDR, NSAllMapTableValues_DESC)

fun NSAllMapTableValues(arg0: MemorySegment): MemorySegment {
    try {
        return NSAllMapTableValues_HANDLE.invokeExact(arg0) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCreateMapTableWithZone typedef NSMapTable = (Void)*(typedef NSMapTableKeyCallBacks = Declared(NSMapTableKeyCallBacks),typedef NSMapTableValueCallBacks = Declared(NSMapTableValueCallBacks),typedef NSUInteger = UNSIGNED = Long,(typedef NSZone = Declared(_NSZone))*)
 */
private val NSCreateMapTableWithZone_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, NSMapTableKeyCallBacks.layout, NSMapTableValueCallBacks.layout, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
private val NSCreateMapTableWithZone_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCreateMapTableWithZone").orElseThrow()
private val NSCreateMapTableWithZone_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCreateMapTableWithZone_ADDR, NSCreateMapTableWithZone_DESC)

fun NSCreateMapTableWithZone(arg0: MemorySegment, arg1: MemorySegment, arg2: Long, arg3: MemorySegment): MemorySegment {
    try {
        return NSCreateMapTableWithZone_HANDLE.invokeExact(arg0, arg1, arg2, arg3) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSCreateMapTable typedef NSMapTable = (Void)*(typedef NSMapTableKeyCallBacks = Declared(NSMapTableKeyCallBacks),typedef NSMapTableValueCallBacks = Declared(NSMapTableValueCallBacks),typedef NSUInteger = UNSIGNED = Long)
 */
private val NSCreateMapTable_DESC: FunctionDescriptor = FunctionDescriptor.of(ValueLayout.ADDRESS, NSMapTableKeyCallBacks.layout, NSMapTableValueCallBacks.layout, ValueLayout.JAVA_LONG)
private val NSCreateMapTable_ADDR: MemorySegment = SymbolLookup.loaderLookup().find("NSCreateMapTable").orElseThrow()
private val NSCreateMapTable_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(NSCreateMapTable_ADDR, NSCreateMapTable_DESC)

fun NSCreateMapTable(arg0: MemorySegment, arg1: MemorySegment, arg2: Long): MemorySegment {
    try {
        return NSCreateMapTable_HANDLE.invokeExact(arg0, arg1, arg2) as MemorySegment
    } catch (ex: Error) {
        throw ex
    } catch (ex: RuntimeException) {
        throw ex
    } catch (ex: Throwable) {
        throw AssertionError("should not reach here", ex)
    }
}

/**
 * {@snippet lang=c : NSIntegerMapKeyCallBacks typedef const NSMapTableKeyCallBacks = Declared(NSMapTableKeyCallBacks)
 */
private val NSIntegerMapKeyCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableKeyCallBacks.layout }
private val NSIntegerMapKeyCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIntegerMapKeyCallBacks").orElseThrow() }
private val NSIntegerMapKeyCallBacks_VH: VarHandle by lazy { NSIntegerMapKeyCallBacks_LAYOUT.varHandle() }

var NSIntegerMapKeyCallBacks: MemorySegment
    get() = NSIntegerMapKeyCallBacks_VH.get(NSIntegerMapKeyCallBacks_SEGMENT) as MemorySegment
    set(value) = NSIntegerMapKeyCallBacks_VH.set(NSIntegerMapKeyCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSNonOwnedPointerMapKeyCallBacks typedef const NSMapTableKeyCallBacks = Declared(NSMapTableKeyCallBacks)
 */
private val NSNonOwnedPointerMapKeyCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableKeyCallBacks.layout }
private val NSNonOwnedPointerMapKeyCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNonOwnedPointerMapKeyCallBacks").orElseThrow() }
private val NSNonOwnedPointerMapKeyCallBacks_VH: VarHandle by lazy { NSNonOwnedPointerMapKeyCallBacks_LAYOUT.varHandle() }

var NSNonOwnedPointerMapKeyCallBacks: MemorySegment
    get() = NSNonOwnedPointerMapKeyCallBacks_VH.get(NSNonOwnedPointerMapKeyCallBacks_SEGMENT) as MemorySegment
    set(value) = NSNonOwnedPointerMapKeyCallBacks_VH.set(NSNonOwnedPointerMapKeyCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSNonOwnedPointerOrNullMapKeyCallBacks typedef const NSMapTableKeyCallBacks = Declared(NSMapTableKeyCallBacks)
 */
private val NSNonOwnedPointerOrNullMapKeyCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableKeyCallBacks.layout }
private val NSNonOwnedPointerOrNullMapKeyCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNonOwnedPointerOrNullMapKeyCallBacks").orElseThrow() }
private val NSNonOwnedPointerOrNullMapKeyCallBacks_VH: VarHandle by lazy { NSNonOwnedPointerOrNullMapKeyCallBacks_LAYOUT.varHandle() }

var NSNonOwnedPointerOrNullMapKeyCallBacks: MemorySegment
    get() = NSNonOwnedPointerOrNullMapKeyCallBacks_VH.get(NSNonOwnedPointerOrNullMapKeyCallBacks_SEGMENT) as MemorySegment
    set(value) = NSNonOwnedPointerOrNullMapKeyCallBacks_VH.set(NSNonOwnedPointerOrNullMapKeyCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSNonRetainedObjectMapKeyCallBacks typedef const NSMapTableKeyCallBacks = Declared(NSMapTableKeyCallBacks)
 */
private val NSNonRetainedObjectMapKeyCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableKeyCallBacks.layout }
private val NSNonRetainedObjectMapKeyCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNonRetainedObjectMapKeyCallBacks").orElseThrow() }
private val NSNonRetainedObjectMapKeyCallBacks_VH: VarHandle by lazy { NSNonRetainedObjectMapKeyCallBacks_LAYOUT.varHandle() }

var NSNonRetainedObjectMapKeyCallBacks: MemorySegment
    get() = NSNonRetainedObjectMapKeyCallBacks_VH.get(NSNonRetainedObjectMapKeyCallBacks_SEGMENT) as MemorySegment
    set(value) = NSNonRetainedObjectMapKeyCallBacks_VH.set(NSNonRetainedObjectMapKeyCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSObjectMapKeyCallBacks typedef const NSMapTableKeyCallBacks = Declared(NSMapTableKeyCallBacks)
 */
private val NSObjectMapKeyCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableKeyCallBacks.layout }
private val NSObjectMapKeyCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSObjectMapKeyCallBacks").orElseThrow() }
private val NSObjectMapKeyCallBacks_VH: VarHandle by lazy { NSObjectMapKeyCallBacks_LAYOUT.varHandle() }

var NSObjectMapKeyCallBacks: MemorySegment
    get() = NSObjectMapKeyCallBacks_VH.get(NSObjectMapKeyCallBacks_SEGMENT) as MemorySegment
    set(value) = NSObjectMapKeyCallBacks_VH.set(NSObjectMapKeyCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSOwnedPointerMapKeyCallBacks typedef const NSMapTableKeyCallBacks = Declared(NSMapTableKeyCallBacks)
 */
private val NSOwnedPointerMapKeyCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableKeyCallBacks.layout }
private val NSOwnedPointerMapKeyCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOwnedPointerMapKeyCallBacks").orElseThrow() }
private val NSOwnedPointerMapKeyCallBacks_VH: VarHandle by lazy { NSOwnedPointerMapKeyCallBacks_LAYOUT.varHandle() }

var NSOwnedPointerMapKeyCallBacks: MemorySegment
    get() = NSOwnedPointerMapKeyCallBacks_VH.get(NSOwnedPointerMapKeyCallBacks_SEGMENT) as MemorySegment
    set(value) = NSOwnedPointerMapKeyCallBacks_VH.set(NSOwnedPointerMapKeyCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSIntMapKeyCallBacks typedef const NSMapTableKeyCallBacks = Declared(NSMapTableKeyCallBacks)
 */
private val NSIntMapKeyCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableKeyCallBacks.layout }
private val NSIntMapKeyCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIntMapKeyCallBacks").orElseThrow() }
private val NSIntMapKeyCallBacks_VH: VarHandle by lazy { NSIntMapKeyCallBacks_LAYOUT.varHandle() }

var NSIntMapKeyCallBacks: MemorySegment
    get() = NSIntMapKeyCallBacks_VH.get(NSIntMapKeyCallBacks_SEGMENT) as MemorySegment
    set(value) = NSIntMapKeyCallBacks_VH.set(NSIntMapKeyCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSIntegerMapValueCallBacks typedef const NSMapTableValueCallBacks = Declared(NSMapTableValueCallBacks)
 */
private val NSIntegerMapValueCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableValueCallBacks.layout }
private val NSIntegerMapValueCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIntegerMapValueCallBacks").orElseThrow() }
private val NSIntegerMapValueCallBacks_VH: VarHandle by lazy { NSIntegerMapValueCallBacks_LAYOUT.varHandle() }

var NSIntegerMapValueCallBacks: MemorySegment
    get() = NSIntegerMapValueCallBacks_VH.get(NSIntegerMapValueCallBacks_SEGMENT) as MemorySegment
    set(value) = NSIntegerMapValueCallBacks_VH.set(NSIntegerMapValueCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSNonOwnedPointerMapValueCallBacks typedef const NSMapTableValueCallBacks = Declared(NSMapTableValueCallBacks)
 */
private val NSNonOwnedPointerMapValueCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableValueCallBacks.layout }
private val NSNonOwnedPointerMapValueCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNonOwnedPointerMapValueCallBacks").orElseThrow() }
private val NSNonOwnedPointerMapValueCallBacks_VH: VarHandle by lazy { NSNonOwnedPointerMapValueCallBacks_LAYOUT.varHandle() }

var NSNonOwnedPointerMapValueCallBacks: MemorySegment
    get() = NSNonOwnedPointerMapValueCallBacks_VH.get(NSNonOwnedPointerMapValueCallBacks_SEGMENT) as MemorySegment
    set(value) = NSNonOwnedPointerMapValueCallBacks_VH.set(NSNonOwnedPointerMapValueCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSObjectMapValueCallBacks typedef const NSMapTableValueCallBacks = Declared(NSMapTableValueCallBacks)
 */
private val NSObjectMapValueCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableValueCallBacks.layout }
private val NSObjectMapValueCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSObjectMapValueCallBacks").orElseThrow() }
private val NSObjectMapValueCallBacks_VH: VarHandle by lazy { NSObjectMapValueCallBacks_LAYOUT.varHandle() }

var NSObjectMapValueCallBacks: MemorySegment
    get() = NSObjectMapValueCallBacks_VH.get(NSObjectMapValueCallBacks_SEGMENT) as MemorySegment
    set(value) = NSObjectMapValueCallBacks_VH.set(NSObjectMapValueCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSNonRetainedObjectMapValueCallBacks typedef const NSMapTableValueCallBacks = Declared(NSMapTableValueCallBacks)
 */
private val NSNonRetainedObjectMapValueCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableValueCallBacks.layout }
private val NSNonRetainedObjectMapValueCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSNonRetainedObjectMapValueCallBacks").orElseThrow() }
private val NSNonRetainedObjectMapValueCallBacks_VH: VarHandle by lazy { NSNonRetainedObjectMapValueCallBacks_LAYOUT.varHandle() }

var NSNonRetainedObjectMapValueCallBacks: MemorySegment
    get() = NSNonRetainedObjectMapValueCallBacks_VH.get(NSNonRetainedObjectMapValueCallBacks_SEGMENT) as MemorySegment
    set(value) = NSNonRetainedObjectMapValueCallBacks_VH.set(NSNonRetainedObjectMapValueCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSOwnedPointerMapValueCallBacks typedef const NSMapTableValueCallBacks = Declared(NSMapTableValueCallBacks)
 */
private val NSOwnedPointerMapValueCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableValueCallBacks.layout }
private val NSOwnedPointerMapValueCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSOwnedPointerMapValueCallBacks").orElseThrow() }
private val NSOwnedPointerMapValueCallBacks_VH: VarHandle by lazy { NSOwnedPointerMapValueCallBacks_LAYOUT.varHandle() }

var NSOwnedPointerMapValueCallBacks: MemorySegment
    get() = NSOwnedPointerMapValueCallBacks_VH.get(NSOwnedPointerMapValueCallBacks_SEGMENT) as MemorySegment
    set(value) = NSOwnedPointerMapValueCallBacks_VH.set(NSOwnedPointerMapValueCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSIntMapValueCallBacks typedef const NSMapTableValueCallBacks = Declared(NSMapTableValueCallBacks)
 */
private val NSIntMapValueCallBacks_LAYOUT: MemoryLayout by lazy { NSMapTableValueCallBacks.layout }
private val NSIntMapValueCallBacks_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSIntMapValueCallBacks").orElseThrow() }
private val NSIntMapValueCallBacks_VH: VarHandle by lazy { NSIntMapValueCallBacks_LAYOUT.varHandle() }

var NSIntMapValueCallBacks: MemorySegment
    get() = NSIntMapValueCallBacks_VH.get(NSIntMapValueCallBacks_SEGMENT) as MemorySegment
    set(value) = NSIntMapValueCallBacks_VH.set(NSIntMapValueCallBacks_SEGMENT, value)

/**
 * {@snippet lang=c : NSInvocationOperationVoidResultException typedef const NSExceptionName = (Void)*
 */
private val NSInvocationOperationVoidResultException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInvocationOperationVoidResultException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInvocationOperationVoidResultException").orElseThrow() }
private val NSInvocationOperationVoidResultException_VH: VarHandle by lazy { NSInvocationOperationVoidResultException_LAYOUT.varHandle() }

var NSInvocationOperationVoidResultException: MemorySegment
    get() = NSInvocationOperationVoidResultException_VH.get(NSInvocationOperationVoidResultException_SEGMENT) as MemorySegment
    set(value) = NSInvocationOperationVoidResultException_VH.set(NSInvocationOperationVoidResultException_SEGMENT, value)

/**
 * {@snippet lang=c : NSInvocationOperationCancelledException typedef const NSExceptionName = (Void)*
 */
private val NSInvocationOperationCancelledException_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSInvocationOperationCancelledException_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSInvocationOperationCancelledException").orElseThrow() }
private val NSInvocationOperationCancelledException_VH: VarHandle by lazy { NSInvocationOperationCancelledException_LAYOUT.varHandle() }

var NSInvocationOperationCancelledException: MemorySegment
    get() = NSInvocationOperationCancelledException_VH.get(NSInvocationOperationCancelledException_SEGMENT) as MemorySegment
    set(value) = NSInvocationOperationCancelledException_VH.set(NSInvocationOperationCancelledException_SEGMENT, value)

/**
 * {@snippet lang=c : NSPortDidBecomeInvalidNotification typedef const NSNotificationName = (Void)*
 */
private val NSPortDidBecomeInvalidNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSPortDidBecomeInvalidNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSPortDidBecomeInvalidNotification").orElseThrow() }
private val NSPortDidBecomeInvalidNotification_VH: VarHandle by lazy { NSPortDidBecomeInvalidNotification_LAYOUT.varHandle() }

var NSPortDidBecomeInvalidNotification: MemorySegment
    get() = NSPortDidBecomeInvalidNotification_VH.get(NSPortDidBecomeInvalidNotification_SEGMENT) as MemorySegment
    set(value) = NSPortDidBecomeInvalidNotification_VH.set(NSPortDidBecomeInvalidNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSProcessInfoThermalStateDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSProcessInfoThermalStateDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProcessInfoThermalStateDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProcessInfoThermalStateDidChangeNotification").orElseThrow() }
private val NSProcessInfoThermalStateDidChangeNotification_VH: VarHandle by lazy { NSProcessInfoThermalStateDidChangeNotification_LAYOUT.varHandle() }

var NSProcessInfoThermalStateDidChangeNotification: MemorySegment
    get() = NSProcessInfoThermalStateDidChangeNotification_VH.get(NSProcessInfoThermalStateDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSProcessInfoThermalStateDidChangeNotification_VH.set(NSProcessInfoThermalStateDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSProcessInfoPowerStateDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSProcessInfoPowerStateDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSProcessInfoPowerStateDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSProcessInfoPowerStateDidChangeNotification").orElseThrow() }
private val NSProcessInfoPowerStateDidChangeNotification_VH: VarHandle by lazy { NSProcessInfoPowerStateDidChangeNotification_LAYOUT.varHandle() }

var NSProcessInfoPowerStateDidChangeNotification: MemorySegment
    get() = NSProcessInfoPowerStateDidChangeNotification_VH.get(NSProcessInfoPowerStateDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSProcessInfoPowerStateDidChangeNotification_VH.set(NSProcessInfoPowerStateDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingNameKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingNameKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingNameKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingNameKey").orElseThrow() }
private val NSTextCheckingNameKey_VH: VarHandle by lazy { NSTextCheckingNameKey_LAYOUT.varHandle() }

var NSTextCheckingNameKey: MemorySegment
    get() = NSTextCheckingNameKey_VH.get(NSTextCheckingNameKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingNameKey_VH.set(NSTextCheckingNameKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingJobTitleKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingJobTitleKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingJobTitleKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingJobTitleKey").orElseThrow() }
private val NSTextCheckingJobTitleKey_VH: VarHandle by lazy { NSTextCheckingJobTitleKey_LAYOUT.varHandle() }

var NSTextCheckingJobTitleKey: MemorySegment
    get() = NSTextCheckingJobTitleKey_VH.get(NSTextCheckingJobTitleKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingJobTitleKey_VH.set(NSTextCheckingJobTitleKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingOrganizationKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingOrganizationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingOrganizationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingOrganizationKey").orElseThrow() }
private val NSTextCheckingOrganizationKey_VH: VarHandle by lazy { NSTextCheckingOrganizationKey_LAYOUT.varHandle() }

var NSTextCheckingOrganizationKey: MemorySegment
    get() = NSTextCheckingOrganizationKey_VH.get(NSTextCheckingOrganizationKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingOrganizationKey_VH.set(NSTextCheckingOrganizationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingStreetKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingStreetKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingStreetKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingStreetKey").orElseThrow() }
private val NSTextCheckingStreetKey_VH: VarHandle by lazy { NSTextCheckingStreetKey_LAYOUT.varHandle() }

var NSTextCheckingStreetKey: MemorySegment
    get() = NSTextCheckingStreetKey_VH.get(NSTextCheckingStreetKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingStreetKey_VH.set(NSTextCheckingStreetKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingCityKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingCityKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingCityKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingCityKey").orElseThrow() }
private val NSTextCheckingCityKey_VH: VarHandle by lazy { NSTextCheckingCityKey_LAYOUT.varHandle() }

var NSTextCheckingCityKey: MemorySegment
    get() = NSTextCheckingCityKey_VH.get(NSTextCheckingCityKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingCityKey_VH.set(NSTextCheckingCityKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingStateKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingStateKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingStateKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingStateKey").orElseThrow() }
private val NSTextCheckingStateKey_VH: VarHandle by lazy { NSTextCheckingStateKey_LAYOUT.varHandle() }

var NSTextCheckingStateKey: MemorySegment
    get() = NSTextCheckingStateKey_VH.get(NSTextCheckingStateKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingStateKey_VH.set(NSTextCheckingStateKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingZIPKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingZIPKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingZIPKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingZIPKey").orElseThrow() }
private val NSTextCheckingZIPKey_VH: VarHandle by lazy { NSTextCheckingZIPKey_LAYOUT.varHandle() }

var NSTextCheckingZIPKey: MemorySegment
    get() = NSTextCheckingZIPKey_VH.get(NSTextCheckingZIPKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingZIPKey_VH.set(NSTextCheckingZIPKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingCountryKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingCountryKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingCountryKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingCountryKey").orElseThrow() }
private val NSTextCheckingCountryKey_VH: VarHandle by lazy { NSTextCheckingCountryKey_LAYOUT.varHandle() }

var NSTextCheckingCountryKey: MemorySegment
    get() = NSTextCheckingCountryKey_VH.get(NSTextCheckingCountryKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingCountryKey_VH.set(NSTextCheckingCountryKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingPhoneKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingPhoneKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingPhoneKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingPhoneKey").orElseThrow() }
private val NSTextCheckingPhoneKey_VH: VarHandle by lazy { NSTextCheckingPhoneKey_LAYOUT.varHandle() }

var NSTextCheckingPhoneKey: MemorySegment
    get() = NSTextCheckingPhoneKey_VH.get(NSTextCheckingPhoneKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingPhoneKey_VH.set(NSTextCheckingPhoneKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingAirlineKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingAirlineKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingAirlineKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingAirlineKey").orElseThrow() }
private val NSTextCheckingAirlineKey_VH: VarHandle by lazy { NSTextCheckingAirlineKey_LAYOUT.varHandle() }

var NSTextCheckingAirlineKey: MemorySegment
    get() = NSTextCheckingAirlineKey_VH.get(NSTextCheckingAirlineKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingAirlineKey_VH.set(NSTextCheckingAirlineKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSTextCheckingFlightKey typedef const NSTextCheckingKey = (Void)*
 */
private val NSTextCheckingFlightKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSTextCheckingFlightKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSTextCheckingFlightKey").orElseThrow() }
private val NSTextCheckingFlightKey_VH: VarHandle by lazy { NSTextCheckingFlightKey_LAYOUT.varHandle() }

var NSTextCheckingFlightKey: MemorySegment
    get() = NSTextCheckingFlightKey_VH.get(NSTextCheckingFlightKey_SEGMENT) as MemorySegment
    set(value) = NSTextCheckingFlightKey_VH.set(NSTextCheckingFlightKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSocketSecurityLevelKey typedef const NSStreamPropertyKey = (Void)*
 */
private val NSStreamSocketSecurityLevelKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSocketSecurityLevelKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSocketSecurityLevelKey").orElseThrow() }
private val NSStreamSocketSecurityLevelKey_VH: VarHandle by lazy { NSStreamSocketSecurityLevelKey_LAYOUT.varHandle() }

var NSStreamSocketSecurityLevelKey: MemorySegment
    get() = NSStreamSocketSecurityLevelKey_VH.get(NSStreamSocketSecurityLevelKey_SEGMENT) as MemorySegment
    set(value) = NSStreamSocketSecurityLevelKey_VH.set(NSStreamSocketSecurityLevelKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSocketSecurityLevelNone typedef const NSStreamSocketSecurityLevel = (Void)*
 */
private val NSStreamSocketSecurityLevelNone_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSocketSecurityLevelNone_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSocketSecurityLevelNone").orElseThrow() }
private val NSStreamSocketSecurityLevelNone_VH: VarHandle by lazy { NSStreamSocketSecurityLevelNone_LAYOUT.varHandle() }

var NSStreamSocketSecurityLevelNone: MemorySegment
    get() = NSStreamSocketSecurityLevelNone_VH.get(NSStreamSocketSecurityLevelNone_SEGMENT) as MemorySegment
    set(value) = NSStreamSocketSecurityLevelNone_VH.set(NSStreamSocketSecurityLevelNone_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSocketSecurityLevelSSLv2 typedef const NSStreamSocketSecurityLevel = (Void)*
 */
private val NSStreamSocketSecurityLevelSSLv2_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSocketSecurityLevelSSLv2_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSocketSecurityLevelSSLv2").orElseThrow() }
private val NSStreamSocketSecurityLevelSSLv2_VH: VarHandle by lazy { NSStreamSocketSecurityLevelSSLv2_LAYOUT.varHandle() }

var NSStreamSocketSecurityLevelSSLv2: MemorySegment
    get() = NSStreamSocketSecurityLevelSSLv2_VH.get(NSStreamSocketSecurityLevelSSLv2_SEGMENT) as MemorySegment
    set(value) = NSStreamSocketSecurityLevelSSLv2_VH.set(NSStreamSocketSecurityLevelSSLv2_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSocketSecurityLevelSSLv3 typedef const NSStreamSocketSecurityLevel = (Void)*
 */
private val NSStreamSocketSecurityLevelSSLv3_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSocketSecurityLevelSSLv3_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSocketSecurityLevelSSLv3").orElseThrow() }
private val NSStreamSocketSecurityLevelSSLv3_VH: VarHandle by lazy { NSStreamSocketSecurityLevelSSLv3_LAYOUT.varHandle() }

var NSStreamSocketSecurityLevelSSLv3: MemorySegment
    get() = NSStreamSocketSecurityLevelSSLv3_VH.get(NSStreamSocketSecurityLevelSSLv3_SEGMENT) as MemorySegment
    set(value) = NSStreamSocketSecurityLevelSSLv3_VH.set(NSStreamSocketSecurityLevelSSLv3_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSocketSecurityLevelTLSv1 typedef const NSStreamSocketSecurityLevel = (Void)*
 */
private val NSStreamSocketSecurityLevelTLSv1_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSocketSecurityLevelTLSv1_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSocketSecurityLevelTLSv1").orElseThrow() }
private val NSStreamSocketSecurityLevelTLSv1_VH: VarHandle by lazy { NSStreamSocketSecurityLevelTLSv1_LAYOUT.varHandle() }

var NSStreamSocketSecurityLevelTLSv1: MemorySegment
    get() = NSStreamSocketSecurityLevelTLSv1_VH.get(NSStreamSocketSecurityLevelTLSv1_SEGMENT) as MemorySegment
    set(value) = NSStreamSocketSecurityLevelTLSv1_VH.set(NSStreamSocketSecurityLevelTLSv1_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSocketSecurityLevelNegotiatedSSL typedef const NSStreamSocketSecurityLevel = (Void)*
 */
private val NSStreamSocketSecurityLevelNegotiatedSSL_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSocketSecurityLevelNegotiatedSSL_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSocketSecurityLevelNegotiatedSSL").orElseThrow() }
private val NSStreamSocketSecurityLevelNegotiatedSSL_VH: VarHandle by lazy { NSStreamSocketSecurityLevelNegotiatedSSL_LAYOUT.varHandle() }

var NSStreamSocketSecurityLevelNegotiatedSSL: MemorySegment
    get() = NSStreamSocketSecurityLevelNegotiatedSSL_VH.get(NSStreamSocketSecurityLevelNegotiatedSSL_SEGMENT) as MemorySegment
    set(value) = NSStreamSocketSecurityLevelNegotiatedSSL_VH.set(NSStreamSocketSecurityLevelNegotiatedSSL_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSOCKSProxyConfigurationKey typedef const NSStreamPropertyKey = (Void)*
 */
private val NSStreamSOCKSProxyConfigurationKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSOCKSProxyConfigurationKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSOCKSProxyConfigurationKey").orElseThrow() }
private val NSStreamSOCKSProxyConfigurationKey_VH: VarHandle by lazy { NSStreamSOCKSProxyConfigurationKey_LAYOUT.varHandle() }

var NSStreamSOCKSProxyConfigurationKey: MemorySegment
    get() = NSStreamSOCKSProxyConfigurationKey_VH.get(NSStreamSOCKSProxyConfigurationKey_SEGMENT) as MemorySegment
    set(value) = NSStreamSOCKSProxyConfigurationKey_VH.set(NSStreamSOCKSProxyConfigurationKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSOCKSProxyHostKey typedef const NSStreamSOCKSProxyConfiguration = (Void)*
 */
private val NSStreamSOCKSProxyHostKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSOCKSProxyHostKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSOCKSProxyHostKey").orElseThrow() }
private val NSStreamSOCKSProxyHostKey_VH: VarHandle by lazy { NSStreamSOCKSProxyHostKey_LAYOUT.varHandle() }

var NSStreamSOCKSProxyHostKey: MemorySegment
    get() = NSStreamSOCKSProxyHostKey_VH.get(NSStreamSOCKSProxyHostKey_SEGMENT) as MemorySegment
    set(value) = NSStreamSOCKSProxyHostKey_VH.set(NSStreamSOCKSProxyHostKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSOCKSProxyPortKey typedef const NSStreamSOCKSProxyConfiguration = (Void)*
 */
private val NSStreamSOCKSProxyPortKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSOCKSProxyPortKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSOCKSProxyPortKey").orElseThrow() }
private val NSStreamSOCKSProxyPortKey_VH: VarHandle by lazy { NSStreamSOCKSProxyPortKey_LAYOUT.varHandle() }

var NSStreamSOCKSProxyPortKey: MemorySegment
    get() = NSStreamSOCKSProxyPortKey_VH.get(NSStreamSOCKSProxyPortKey_SEGMENT) as MemorySegment
    set(value) = NSStreamSOCKSProxyPortKey_VH.set(NSStreamSOCKSProxyPortKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSOCKSProxyVersionKey typedef const NSStreamSOCKSProxyConfiguration = (Void)*
 */
private val NSStreamSOCKSProxyVersionKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSOCKSProxyVersionKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSOCKSProxyVersionKey").orElseThrow() }
private val NSStreamSOCKSProxyVersionKey_VH: VarHandle by lazy { NSStreamSOCKSProxyVersionKey_LAYOUT.varHandle() }

var NSStreamSOCKSProxyVersionKey: MemorySegment
    get() = NSStreamSOCKSProxyVersionKey_VH.get(NSStreamSOCKSProxyVersionKey_SEGMENT) as MemorySegment
    set(value) = NSStreamSOCKSProxyVersionKey_VH.set(NSStreamSOCKSProxyVersionKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSOCKSProxyUserKey typedef const NSStreamSOCKSProxyConfiguration = (Void)*
 */
private val NSStreamSOCKSProxyUserKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSOCKSProxyUserKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSOCKSProxyUserKey").orElseThrow() }
private val NSStreamSOCKSProxyUserKey_VH: VarHandle by lazy { NSStreamSOCKSProxyUserKey_LAYOUT.varHandle() }

var NSStreamSOCKSProxyUserKey: MemorySegment
    get() = NSStreamSOCKSProxyUserKey_VH.get(NSStreamSOCKSProxyUserKey_SEGMENT) as MemorySegment
    set(value) = NSStreamSOCKSProxyUserKey_VH.set(NSStreamSOCKSProxyUserKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSOCKSProxyPasswordKey typedef const NSStreamSOCKSProxyConfiguration = (Void)*
 */
private val NSStreamSOCKSProxyPasswordKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSOCKSProxyPasswordKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSOCKSProxyPasswordKey").orElseThrow() }
private val NSStreamSOCKSProxyPasswordKey_VH: VarHandle by lazy { NSStreamSOCKSProxyPasswordKey_LAYOUT.varHandle() }

var NSStreamSOCKSProxyPasswordKey: MemorySegment
    get() = NSStreamSOCKSProxyPasswordKey_VH.get(NSStreamSOCKSProxyPasswordKey_SEGMENT) as MemorySegment
    set(value) = NSStreamSOCKSProxyPasswordKey_VH.set(NSStreamSOCKSProxyPasswordKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSOCKSProxyVersion4 typedef const NSStreamSOCKSProxyVersion = (Void)*
 */
private val NSStreamSOCKSProxyVersion4_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSOCKSProxyVersion4_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSOCKSProxyVersion4").orElseThrow() }
private val NSStreamSOCKSProxyVersion4_VH: VarHandle by lazy { NSStreamSOCKSProxyVersion4_LAYOUT.varHandle() }

var NSStreamSOCKSProxyVersion4: MemorySegment
    get() = NSStreamSOCKSProxyVersion4_VH.get(NSStreamSOCKSProxyVersion4_SEGMENT) as MemorySegment
    set(value) = NSStreamSOCKSProxyVersion4_VH.set(NSStreamSOCKSProxyVersion4_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSOCKSProxyVersion5 typedef const NSStreamSOCKSProxyVersion = (Void)*
 */
private val NSStreamSOCKSProxyVersion5_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSOCKSProxyVersion5_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSOCKSProxyVersion5").orElseThrow() }
private val NSStreamSOCKSProxyVersion5_VH: VarHandle by lazy { NSStreamSOCKSProxyVersion5_LAYOUT.varHandle() }

var NSStreamSOCKSProxyVersion5: MemorySegment
    get() = NSStreamSOCKSProxyVersion5_VH.get(NSStreamSOCKSProxyVersion5_SEGMENT) as MemorySegment
    set(value) = NSStreamSOCKSProxyVersion5_VH.set(NSStreamSOCKSProxyVersion5_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamDataWrittenToMemoryStreamKey typedef const NSStreamPropertyKey = (Void)*
 */
private val NSStreamDataWrittenToMemoryStreamKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamDataWrittenToMemoryStreamKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamDataWrittenToMemoryStreamKey").orElseThrow() }
private val NSStreamDataWrittenToMemoryStreamKey_VH: VarHandle by lazy { NSStreamDataWrittenToMemoryStreamKey_LAYOUT.varHandle() }

var NSStreamDataWrittenToMemoryStreamKey: MemorySegment
    get() = NSStreamDataWrittenToMemoryStreamKey_VH.get(NSStreamDataWrittenToMemoryStreamKey_SEGMENT) as MemorySegment
    set(value) = NSStreamDataWrittenToMemoryStreamKey_VH.set(NSStreamDataWrittenToMemoryStreamKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamFileCurrentOffsetKey typedef const NSStreamPropertyKey = (Void)*
 */
private val NSStreamFileCurrentOffsetKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamFileCurrentOffsetKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamFileCurrentOffsetKey").orElseThrow() }
private val NSStreamFileCurrentOffsetKey_VH: VarHandle by lazy { NSStreamFileCurrentOffsetKey_LAYOUT.varHandle() }

var NSStreamFileCurrentOffsetKey: MemorySegment
    get() = NSStreamFileCurrentOffsetKey_VH.get(NSStreamFileCurrentOffsetKey_SEGMENT) as MemorySegment
    set(value) = NSStreamFileCurrentOffsetKey_VH.set(NSStreamFileCurrentOffsetKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSocketSSLErrorDomain typedef const NSErrorDomain = (Void)*
 */
private val NSStreamSocketSSLErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSocketSSLErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSocketSSLErrorDomain").orElseThrow() }
private val NSStreamSocketSSLErrorDomain_VH: VarHandle by lazy { NSStreamSocketSSLErrorDomain_LAYOUT.varHandle() }

var NSStreamSocketSSLErrorDomain: MemorySegment
    get() = NSStreamSocketSSLErrorDomain_VH.get(NSStreamSocketSSLErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSStreamSocketSSLErrorDomain_VH.set(NSStreamSocketSSLErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamSOCKSErrorDomain typedef const NSErrorDomain = (Void)*
 */
private val NSStreamSOCKSErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamSOCKSErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamSOCKSErrorDomain").orElseThrow() }
private val NSStreamSOCKSErrorDomain_VH: VarHandle by lazy { NSStreamSOCKSErrorDomain_LAYOUT.varHandle() }

var NSStreamSOCKSErrorDomain: MemorySegment
    get() = NSStreamSOCKSErrorDomain_VH.get(NSStreamSOCKSErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSStreamSOCKSErrorDomain_VH.set(NSStreamSOCKSErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamNetworkServiceType typedef const NSStreamPropertyKey = (Void)*
 */
private val NSStreamNetworkServiceType_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamNetworkServiceType_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamNetworkServiceType").orElseThrow() }
private val NSStreamNetworkServiceType_VH: VarHandle by lazy { NSStreamNetworkServiceType_LAYOUT.varHandle() }

var NSStreamNetworkServiceType: MemorySegment
    get() = NSStreamNetworkServiceType_VH.get(NSStreamNetworkServiceType_SEGMENT) as MemorySegment
    set(value) = NSStreamNetworkServiceType_VH.set(NSStreamNetworkServiceType_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamNetworkServiceTypeVoIP typedef const NSStreamNetworkServiceTypeValue = (Void)*
 */
private val NSStreamNetworkServiceTypeVoIP_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamNetworkServiceTypeVoIP_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamNetworkServiceTypeVoIP").orElseThrow() }
private val NSStreamNetworkServiceTypeVoIP_VH: VarHandle by lazy { NSStreamNetworkServiceTypeVoIP_LAYOUT.varHandle() }

var NSStreamNetworkServiceTypeVoIP: MemorySegment
    get() = NSStreamNetworkServiceTypeVoIP_VH.get(NSStreamNetworkServiceTypeVoIP_SEGMENT) as MemorySegment
    set(value) = NSStreamNetworkServiceTypeVoIP_VH.set(NSStreamNetworkServiceTypeVoIP_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamNetworkServiceTypeVideo typedef const NSStreamNetworkServiceTypeValue = (Void)*
 */
private val NSStreamNetworkServiceTypeVideo_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamNetworkServiceTypeVideo_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamNetworkServiceTypeVideo").orElseThrow() }
private val NSStreamNetworkServiceTypeVideo_VH: VarHandle by lazy { NSStreamNetworkServiceTypeVideo_LAYOUT.varHandle() }

var NSStreamNetworkServiceTypeVideo: MemorySegment
    get() = NSStreamNetworkServiceTypeVideo_VH.get(NSStreamNetworkServiceTypeVideo_SEGMENT) as MemorySegment
    set(value) = NSStreamNetworkServiceTypeVideo_VH.set(NSStreamNetworkServiceTypeVideo_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamNetworkServiceTypeBackground typedef const NSStreamNetworkServiceTypeValue = (Void)*
 */
private val NSStreamNetworkServiceTypeBackground_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamNetworkServiceTypeBackground_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamNetworkServiceTypeBackground").orElseThrow() }
private val NSStreamNetworkServiceTypeBackground_VH: VarHandle by lazy { NSStreamNetworkServiceTypeBackground_LAYOUT.varHandle() }

var NSStreamNetworkServiceTypeBackground: MemorySegment
    get() = NSStreamNetworkServiceTypeBackground_VH.get(NSStreamNetworkServiceTypeBackground_SEGMENT) as MemorySegment
    set(value) = NSStreamNetworkServiceTypeBackground_VH.set(NSStreamNetworkServiceTypeBackground_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamNetworkServiceTypeVoice typedef const NSStreamNetworkServiceTypeValue = (Void)*
 */
private val NSStreamNetworkServiceTypeVoice_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamNetworkServiceTypeVoice_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamNetworkServiceTypeVoice").orElseThrow() }
private val NSStreamNetworkServiceTypeVoice_VH: VarHandle by lazy { NSStreamNetworkServiceTypeVoice_LAYOUT.varHandle() }

var NSStreamNetworkServiceTypeVoice: MemorySegment
    get() = NSStreamNetworkServiceTypeVoice_VH.get(NSStreamNetworkServiceTypeVoice_SEGMENT) as MemorySegment
    set(value) = NSStreamNetworkServiceTypeVoice_VH.set(NSStreamNetworkServiceTypeVoice_SEGMENT, value)

/**
 * {@snippet lang=c : NSStreamNetworkServiceTypeCallSignaling typedef const NSStreamNetworkServiceTypeValue = (Void)*
 */
private val NSStreamNetworkServiceTypeCallSignaling_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSStreamNetworkServiceTypeCallSignaling_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSStreamNetworkServiceTypeCallSignaling").orElseThrow() }
private val NSStreamNetworkServiceTypeCallSignaling_VH: VarHandle by lazy { NSStreamNetworkServiceTypeCallSignaling_LAYOUT.varHandle() }

var NSStreamNetworkServiceTypeCallSignaling: MemorySegment
    get() = NSStreamNetworkServiceTypeCallSignaling_VH.get(NSStreamNetworkServiceTypeCallSignaling_SEGMENT) as MemorySegment
    set(value) = NSStreamNetworkServiceTypeCallSignaling_VH.set(NSStreamNetworkServiceTypeCallSignaling_SEGMENT, value)

/**
 * {@snippet lang=c : NSWillBecomeMultiThreadedNotification typedef const NSNotificationName = (Void)*
 */
private val NSWillBecomeMultiThreadedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSWillBecomeMultiThreadedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSWillBecomeMultiThreadedNotification").orElseThrow() }
private val NSWillBecomeMultiThreadedNotification_VH: VarHandle by lazy { NSWillBecomeMultiThreadedNotification_LAYOUT.varHandle() }

var NSWillBecomeMultiThreadedNotification: MemorySegment
    get() = NSWillBecomeMultiThreadedNotification_VH.get(NSWillBecomeMultiThreadedNotification_SEGMENT) as MemorySegment
    set(value) = NSWillBecomeMultiThreadedNotification_VH.set(NSWillBecomeMultiThreadedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSDidBecomeSingleThreadedNotification typedef const NSNotificationName = (Void)*
 */
private val NSDidBecomeSingleThreadedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSDidBecomeSingleThreadedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSDidBecomeSingleThreadedNotification").orElseThrow() }
private val NSDidBecomeSingleThreadedNotification_VH: VarHandle by lazy { NSDidBecomeSingleThreadedNotification_LAYOUT.varHandle() }

var NSDidBecomeSingleThreadedNotification: MemorySegment
    get() = NSDidBecomeSingleThreadedNotification_VH.get(NSDidBecomeSingleThreadedNotification_SEGMENT) as MemorySegment
    set(value) = NSDidBecomeSingleThreadedNotification_VH.set(NSDidBecomeSingleThreadedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSThreadWillExitNotification typedef const NSNotificationName = (Void)*
 */
private val NSThreadWillExitNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSThreadWillExitNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSThreadWillExitNotification").orElseThrow() }
private val NSThreadWillExitNotification_VH: VarHandle by lazy { NSThreadWillExitNotification_LAYOUT.varHandle() }

var NSThreadWillExitNotification: MemorySegment
    get() = NSThreadWillExitNotification_VH.get(NSThreadWillExitNotification_SEGMENT) as MemorySegment
    set(value) = NSThreadWillExitNotification_VH.set(NSThreadWillExitNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSSystemTimeZoneDidChangeNotification typedef const NSNotificationName = (Void)*
 */
private val NSSystemTimeZoneDidChangeNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSSystemTimeZoneDidChangeNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSSystemTimeZoneDidChangeNotification").orElseThrow() }
private val NSSystemTimeZoneDidChangeNotification_VH: VarHandle by lazy { NSSystemTimeZoneDidChangeNotification_LAYOUT.varHandle() }

var NSSystemTimeZoneDidChangeNotification: MemorySegment
    get() = NSSystemTimeZoneDidChangeNotification_VH.get(NSSystemTimeZoneDidChangeNotification_SEGMENT) as MemorySegment
    set(value) = NSSystemTimeZoneDidChangeNotification_VH.set(NSSystemTimeZoneDidChangeNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLProtectionSpaceHTTP (Void)*
 */
private val NSURLProtectionSpaceHTTP_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLProtectionSpaceHTTP_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLProtectionSpaceHTTP").orElseThrow() }
private val NSURLProtectionSpaceHTTP_VH: VarHandle by lazy { NSURLProtectionSpaceHTTP_LAYOUT.varHandle() }

var NSURLProtectionSpaceHTTP: MemorySegment
    get() = NSURLProtectionSpaceHTTP_VH.get(NSURLProtectionSpaceHTTP_SEGMENT) as MemorySegment
    set(value) = NSURLProtectionSpaceHTTP_VH.set(NSURLProtectionSpaceHTTP_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLProtectionSpaceHTTPS (Void)*
 */
private val NSURLProtectionSpaceHTTPS_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLProtectionSpaceHTTPS_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLProtectionSpaceHTTPS").orElseThrow() }
private val NSURLProtectionSpaceHTTPS_VH: VarHandle by lazy { NSURLProtectionSpaceHTTPS_LAYOUT.varHandle() }

var NSURLProtectionSpaceHTTPS: MemorySegment
    get() = NSURLProtectionSpaceHTTPS_VH.get(NSURLProtectionSpaceHTTPS_SEGMENT) as MemorySegment
    set(value) = NSURLProtectionSpaceHTTPS_VH.set(NSURLProtectionSpaceHTTPS_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLProtectionSpaceFTP (Void)*
 */
private val NSURLProtectionSpaceFTP_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLProtectionSpaceFTP_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLProtectionSpaceFTP").orElseThrow() }
private val NSURLProtectionSpaceFTP_VH: VarHandle by lazy { NSURLProtectionSpaceFTP_LAYOUT.varHandle() }

var NSURLProtectionSpaceFTP: MemorySegment
    get() = NSURLProtectionSpaceFTP_VH.get(NSURLProtectionSpaceFTP_SEGMENT) as MemorySegment
    set(value) = NSURLProtectionSpaceFTP_VH.set(NSURLProtectionSpaceFTP_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLProtectionSpaceHTTPProxy (Void)*
 */
private val NSURLProtectionSpaceHTTPProxy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLProtectionSpaceHTTPProxy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLProtectionSpaceHTTPProxy").orElseThrow() }
private val NSURLProtectionSpaceHTTPProxy_VH: VarHandle by lazy { NSURLProtectionSpaceHTTPProxy_LAYOUT.varHandle() }

var NSURLProtectionSpaceHTTPProxy: MemorySegment
    get() = NSURLProtectionSpaceHTTPProxy_VH.get(NSURLProtectionSpaceHTTPProxy_SEGMENT) as MemorySegment
    set(value) = NSURLProtectionSpaceHTTPProxy_VH.set(NSURLProtectionSpaceHTTPProxy_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLProtectionSpaceHTTPSProxy (Void)*
 */
private val NSURLProtectionSpaceHTTPSProxy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLProtectionSpaceHTTPSProxy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLProtectionSpaceHTTPSProxy").orElseThrow() }
private val NSURLProtectionSpaceHTTPSProxy_VH: VarHandle by lazy { NSURLProtectionSpaceHTTPSProxy_LAYOUT.varHandle() }

var NSURLProtectionSpaceHTTPSProxy: MemorySegment
    get() = NSURLProtectionSpaceHTTPSProxy_VH.get(NSURLProtectionSpaceHTTPSProxy_SEGMENT) as MemorySegment
    set(value) = NSURLProtectionSpaceHTTPSProxy_VH.set(NSURLProtectionSpaceHTTPSProxy_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLProtectionSpaceFTPProxy (Void)*
 */
private val NSURLProtectionSpaceFTPProxy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLProtectionSpaceFTPProxy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLProtectionSpaceFTPProxy").orElseThrow() }
private val NSURLProtectionSpaceFTPProxy_VH: VarHandle by lazy { NSURLProtectionSpaceFTPProxy_LAYOUT.varHandle() }

var NSURLProtectionSpaceFTPProxy: MemorySegment
    get() = NSURLProtectionSpaceFTPProxy_VH.get(NSURLProtectionSpaceFTPProxy_SEGMENT) as MemorySegment
    set(value) = NSURLProtectionSpaceFTPProxy_VH.set(NSURLProtectionSpaceFTPProxy_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLProtectionSpaceSOCKSProxy (Void)*
 */
private val NSURLProtectionSpaceSOCKSProxy_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLProtectionSpaceSOCKSProxy_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLProtectionSpaceSOCKSProxy").orElseThrow() }
private val NSURLProtectionSpaceSOCKSProxy_VH: VarHandle by lazy { NSURLProtectionSpaceSOCKSProxy_LAYOUT.varHandle() }

var NSURLProtectionSpaceSOCKSProxy: MemorySegment
    get() = NSURLProtectionSpaceSOCKSProxy_VH.get(NSURLProtectionSpaceSOCKSProxy_SEGMENT) as MemorySegment
    set(value) = NSURLProtectionSpaceSOCKSProxy_VH.set(NSURLProtectionSpaceSOCKSProxy_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAuthenticationMethodDefault (Void)*
 */
private val NSURLAuthenticationMethodDefault_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAuthenticationMethodDefault_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAuthenticationMethodDefault").orElseThrow() }
private val NSURLAuthenticationMethodDefault_VH: VarHandle by lazy { NSURLAuthenticationMethodDefault_LAYOUT.varHandle() }

var NSURLAuthenticationMethodDefault: MemorySegment
    get() = NSURLAuthenticationMethodDefault_VH.get(NSURLAuthenticationMethodDefault_SEGMENT) as MemorySegment
    set(value) = NSURLAuthenticationMethodDefault_VH.set(NSURLAuthenticationMethodDefault_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAuthenticationMethodHTTPBasic (Void)*
 */
private val NSURLAuthenticationMethodHTTPBasic_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAuthenticationMethodHTTPBasic_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAuthenticationMethodHTTPBasic").orElseThrow() }
private val NSURLAuthenticationMethodHTTPBasic_VH: VarHandle by lazy { NSURLAuthenticationMethodHTTPBasic_LAYOUT.varHandle() }

var NSURLAuthenticationMethodHTTPBasic: MemorySegment
    get() = NSURLAuthenticationMethodHTTPBasic_VH.get(NSURLAuthenticationMethodHTTPBasic_SEGMENT) as MemorySegment
    set(value) = NSURLAuthenticationMethodHTTPBasic_VH.set(NSURLAuthenticationMethodHTTPBasic_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAuthenticationMethodHTTPDigest (Void)*
 */
private val NSURLAuthenticationMethodHTTPDigest_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAuthenticationMethodHTTPDigest_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAuthenticationMethodHTTPDigest").orElseThrow() }
private val NSURLAuthenticationMethodHTTPDigest_VH: VarHandle by lazy { NSURLAuthenticationMethodHTTPDigest_LAYOUT.varHandle() }

var NSURLAuthenticationMethodHTTPDigest: MemorySegment
    get() = NSURLAuthenticationMethodHTTPDigest_VH.get(NSURLAuthenticationMethodHTTPDigest_SEGMENT) as MemorySegment
    set(value) = NSURLAuthenticationMethodHTTPDigest_VH.set(NSURLAuthenticationMethodHTTPDigest_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAuthenticationMethodHTMLForm (Void)*
 */
private val NSURLAuthenticationMethodHTMLForm_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAuthenticationMethodHTMLForm_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAuthenticationMethodHTMLForm").orElseThrow() }
private val NSURLAuthenticationMethodHTMLForm_VH: VarHandle by lazy { NSURLAuthenticationMethodHTMLForm_LAYOUT.varHandle() }

var NSURLAuthenticationMethodHTMLForm: MemorySegment
    get() = NSURLAuthenticationMethodHTMLForm_VH.get(NSURLAuthenticationMethodHTMLForm_SEGMENT) as MemorySegment
    set(value) = NSURLAuthenticationMethodHTMLForm_VH.set(NSURLAuthenticationMethodHTMLForm_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAuthenticationMethodNTLM (Void)*
 */
private val NSURLAuthenticationMethodNTLM_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAuthenticationMethodNTLM_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAuthenticationMethodNTLM").orElseThrow() }
private val NSURLAuthenticationMethodNTLM_VH: VarHandle by lazy { NSURLAuthenticationMethodNTLM_LAYOUT.varHandle() }

var NSURLAuthenticationMethodNTLM: MemorySegment
    get() = NSURLAuthenticationMethodNTLM_VH.get(NSURLAuthenticationMethodNTLM_SEGMENT) as MemorySegment
    set(value) = NSURLAuthenticationMethodNTLM_VH.set(NSURLAuthenticationMethodNTLM_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAuthenticationMethodNegotiate (Void)*
 */
private val NSURLAuthenticationMethodNegotiate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAuthenticationMethodNegotiate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAuthenticationMethodNegotiate").orElseThrow() }
private val NSURLAuthenticationMethodNegotiate_VH: VarHandle by lazy { NSURLAuthenticationMethodNegotiate_LAYOUT.varHandle() }

var NSURLAuthenticationMethodNegotiate: MemorySegment
    get() = NSURLAuthenticationMethodNegotiate_VH.get(NSURLAuthenticationMethodNegotiate_SEGMENT) as MemorySegment
    set(value) = NSURLAuthenticationMethodNegotiate_VH.set(NSURLAuthenticationMethodNegotiate_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAuthenticationMethodClientCertificate (Void)*
 */
private val NSURLAuthenticationMethodClientCertificate_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAuthenticationMethodClientCertificate_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAuthenticationMethodClientCertificate").orElseThrow() }
private val NSURLAuthenticationMethodClientCertificate_VH: VarHandle by lazy { NSURLAuthenticationMethodClientCertificate_LAYOUT.varHandle() }

var NSURLAuthenticationMethodClientCertificate: MemorySegment
    get() = NSURLAuthenticationMethodClientCertificate_VH.get(NSURLAuthenticationMethodClientCertificate_SEGMENT) as MemorySegment
    set(value) = NSURLAuthenticationMethodClientCertificate_VH.set(NSURLAuthenticationMethodClientCertificate_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLAuthenticationMethodServerTrust (Void)*
 */
private val NSURLAuthenticationMethodServerTrust_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLAuthenticationMethodServerTrust_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLAuthenticationMethodServerTrust").orElseThrow() }
private val NSURLAuthenticationMethodServerTrust_VH: VarHandle by lazy { NSURLAuthenticationMethodServerTrust_LAYOUT.varHandle() }

var NSURLAuthenticationMethodServerTrust: MemorySegment
    get() = NSURLAuthenticationMethodServerTrust_VH.get(NSURLAuthenticationMethodServerTrust_SEGMENT) as MemorySegment
    set(value) = NSURLAuthenticationMethodServerTrust_VH.set(NSURLAuthenticationMethodServerTrust_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLCredentialStorageChangedNotification typedef const NSNotificationName = (Void)*
 */
private val NSURLCredentialStorageChangedNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLCredentialStorageChangedNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLCredentialStorageChangedNotification").orElseThrow() }
private val NSURLCredentialStorageChangedNotification_VH: VarHandle by lazy { NSURLCredentialStorageChangedNotification_LAYOUT.varHandle() }

var NSURLCredentialStorageChangedNotification: MemorySegment
    get() = NSURLCredentialStorageChangedNotification_VH.get(NSURLCredentialStorageChangedNotification_SEGMENT) as MemorySegment
    set(value) = NSURLCredentialStorageChangedNotification_VH.set(NSURLCredentialStorageChangedNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLCredentialStorageRemoveSynchronizableCredentials (Void)*
 */
private val NSURLCredentialStorageRemoveSynchronizableCredentials_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLCredentialStorageRemoveSynchronizableCredentials_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLCredentialStorageRemoveSynchronizableCredentials").orElseThrow() }
private val NSURLCredentialStorageRemoveSynchronizableCredentials_VH: VarHandle by lazy { NSURLCredentialStorageRemoveSynchronizableCredentials_LAYOUT.varHandle() }

var NSURLCredentialStorageRemoveSynchronizableCredentials: MemorySegment
    get() = NSURLCredentialStorageRemoveSynchronizableCredentials_VH.get(NSURLCredentialStorageRemoveSynchronizableCredentials_SEGMENT) as MemorySegment
    set(value) = NSURLCredentialStorageRemoveSynchronizableCredentials_VH.set(NSURLCredentialStorageRemoveSynchronizableCredentials_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLErrorDomain typedef const NSErrorDomain = (Void)*
 */
private val NSURLErrorDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLErrorDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLErrorDomain").orElseThrow() }
private val NSURLErrorDomain_VH: VarHandle by lazy { NSURLErrorDomain_LAYOUT.varHandle() }

var NSURLErrorDomain: MemorySegment
    get() = NSURLErrorDomain_VH.get(NSURLErrorDomain_SEGMENT) as MemorySegment
    set(value) = NSURLErrorDomain_VH.set(NSURLErrorDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLErrorFailingURLErrorKey (Void)*
 */
private val NSURLErrorFailingURLErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLErrorFailingURLErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLErrorFailingURLErrorKey").orElseThrow() }
private val NSURLErrorFailingURLErrorKey_VH: VarHandle by lazy { NSURLErrorFailingURLErrorKey_LAYOUT.varHandle() }

var NSURLErrorFailingURLErrorKey: MemorySegment
    get() = NSURLErrorFailingURLErrorKey_VH.get(NSURLErrorFailingURLErrorKey_SEGMENT) as MemorySegment
    set(value) = NSURLErrorFailingURLErrorKey_VH.set(NSURLErrorFailingURLErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLErrorFailingURLStringErrorKey (Void)*
 */
private val NSURLErrorFailingURLStringErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLErrorFailingURLStringErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLErrorFailingURLStringErrorKey").orElseThrow() }
private val NSURLErrorFailingURLStringErrorKey_VH: VarHandle by lazy { NSURLErrorFailingURLStringErrorKey_LAYOUT.varHandle() }

var NSURLErrorFailingURLStringErrorKey: MemorySegment
    get() = NSURLErrorFailingURLStringErrorKey_VH.get(NSURLErrorFailingURLStringErrorKey_SEGMENT) as MemorySegment
    set(value) = NSURLErrorFailingURLStringErrorKey_VH.set(NSURLErrorFailingURLStringErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSErrorFailingURLStringKey (Void)*
 */
private val NSErrorFailingURLStringKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSErrorFailingURLStringKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSErrorFailingURLStringKey").orElseThrow() }
private val NSErrorFailingURLStringKey_VH: VarHandle by lazy { NSErrorFailingURLStringKey_LAYOUT.varHandle() }

var NSErrorFailingURLStringKey: MemorySegment
    get() = NSErrorFailingURLStringKey_VH.get(NSErrorFailingURLStringKey_SEGMENT) as MemorySegment
    set(value) = NSErrorFailingURLStringKey_VH.set(NSErrorFailingURLStringKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLErrorFailingURLPeerTrustErrorKey (Void)*
 */
private val NSURLErrorFailingURLPeerTrustErrorKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLErrorFailingURLPeerTrustErrorKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLErrorFailingURLPeerTrustErrorKey").orElseThrow() }
private val NSURLErrorFailingURLPeerTrustErrorKey_VH: VarHandle by lazy { NSURLErrorFailingURLPeerTrustErrorKey_LAYOUT.varHandle() }

var NSURLErrorFailingURLPeerTrustErrorKey: MemorySegment
    get() = NSURLErrorFailingURLPeerTrustErrorKey_VH.get(NSURLErrorFailingURLPeerTrustErrorKey_SEGMENT) as MemorySegment
    set(value) = NSURLErrorFailingURLPeerTrustErrorKey_VH.set(NSURLErrorFailingURLPeerTrustErrorKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLErrorBackgroundTaskCancelledReasonKey (Void)*
 */
private val NSURLErrorBackgroundTaskCancelledReasonKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLErrorBackgroundTaskCancelledReasonKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLErrorBackgroundTaskCancelledReasonKey").orElseThrow() }
private val NSURLErrorBackgroundTaskCancelledReasonKey_VH: VarHandle by lazy { NSURLErrorBackgroundTaskCancelledReasonKey_LAYOUT.varHandle() }

var NSURLErrorBackgroundTaskCancelledReasonKey: MemorySegment
    get() = NSURLErrorBackgroundTaskCancelledReasonKey_VH.get(NSURLErrorBackgroundTaskCancelledReasonKey_SEGMENT) as MemorySegment
    set(value) = NSURLErrorBackgroundTaskCancelledReasonKey_VH.set(NSURLErrorBackgroundTaskCancelledReasonKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSURLErrorNetworkUnavailableReasonKey typedef const NSErrorUserInfoKey = (Void)*
 */
private val NSURLErrorNetworkUnavailableReasonKey_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSURLErrorNetworkUnavailableReasonKey_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSURLErrorNetworkUnavailableReasonKey").orElseThrow() }
private val NSURLErrorNetworkUnavailableReasonKey_VH: VarHandle by lazy { NSURLErrorNetworkUnavailableReasonKey_LAYOUT.varHandle() }

var NSURLErrorNetworkUnavailableReasonKey: MemorySegment
    get() = NSURLErrorNetworkUnavailableReasonKey_VH.get(NSURLErrorNetworkUnavailableReasonKey_SEGMENT) as MemorySegment
    set(value) = NSURLErrorNetworkUnavailableReasonKey_VH.set(NSURLErrorNetworkUnavailableReasonKey_SEGMENT, value)

/**
 * {@snippet lang=c : NSGlobalDomain (Void)*
 */
private val NSGlobalDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSGlobalDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSGlobalDomain").orElseThrow() }
private val NSGlobalDomain_VH: VarHandle by lazy { NSGlobalDomain_LAYOUT.varHandle() }

var NSGlobalDomain: MemorySegment
    get() = NSGlobalDomain_VH.get(NSGlobalDomain_SEGMENT) as MemorySegment
    set(value) = NSGlobalDomain_VH.set(NSGlobalDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSArgumentDomain (Void)*
 */
private val NSArgumentDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSArgumentDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSArgumentDomain").orElseThrow() }
private val NSArgumentDomain_VH: VarHandle by lazy { NSArgumentDomain_LAYOUT.varHandle() }

var NSArgumentDomain: MemorySegment
    get() = NSArgumentDomain_VH.get(NSArgumentDomain_SEGMENT) as MemorySegment
    set(value) = NSArgumentDomain_VH.set(NSArgumentDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSRegistrationDomain (Void)*
 */
private val NSRegistrationDomain_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSRegistrationDomain_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSRegistrationDomain").orElseThrow() }
private val NSRegistrationDomain_VH: VarHandle by lazy { NSRegistrationDomain_LAYOUT.varHandle() }

var NSRegistrationDomain: MemorySegment
    get() = NSRegistrationDomain_VH.get(NSRegistrationDomain_SEGMENT) as MemorySegment
    set(value) = NSRegistrationDomain_VH.set(NSRegistrationDomain_SEGMENT, value)

/**
 * {@snippet lang=c : NSUserDefaultsSizeLimitExceededNotification typedef const NSNotificationName = (Void)*
 */
private val NSUserDefaultsSizeLimitExceededNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUserDefaultsSizeLimitExceededNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUserDefaultsSizeLimitExceededNotification").orElseThrow() }
private val NSUserDefaultsSizeLimitExceededNotification_VH: VarHandle by lazy { NSUserDefaultsSizeLimitExceededNotification_LAYOUT.varHandle() }

var NSUserDefaultsSizeLimitExceededNotification: MemorySegment
    get() = NSUserDefaultsSizeLimitExceededNotification_VH.get(NSUserDefaultsSizeLimitExceededNotification_SEGMENT) as MemorySegment
    set(value) = NSUserDefaultsSizeLimitExceededNotification_VH.set(NSUserDefaultsSizeLimitExceededNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUbiquitousUserDefaultsNoCloudAccountNotification typedef const NSNotificationName = (Void)*
 */
private val NSUbiquitousUserDefaultsNoCloudAccountNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUbiquitousUserDefaultsNoCloudAccountNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUbiquitousUserDefaultsNoCloudAccountNotification").orElseThrow() }
private val NSUbiquitousUserDefaultsNoCloudAccountNotification_VH: VarHandle by lazy { NSUbiquitousUserDefaultsNoCloudAccountNotification_LAYOUT.varHandle() }

var NSUbiquitousUserDefaultsNoCloudAccountNotification: MemorySegment
    get() = NSUbiquitousUserDefaultsNoCloudAccountNotification_VH.get(NSUbiquitousUserDefaultsNoCloudAccountNotification_SEGMENT) as MemorySegment
    set(value) = NSUbiquitousUserDefaultsNoCloudAccountNotification_VH.set(NSUbiquitousUserDefaultsNoCloudAccountNotification_SEGMENT, value)

/**
 * {@snippet lang=c : NSUbiquitousUserDefaultsDidChangeAccountsNotification typedef const NSNotificationName = (Void)*
 */
private val NSUbiquitousUserDefaultsDidChangeAccountsNotification_LAYOUT: ValueLayout by lazy { ValueLayout.ADDRESS }
private val NSUbiquitousUserDefaultsDidChangeAccountsNotification_SEGMENT: MemorySegment by lazy { SymbolLookup.loaderLookup().find("NSUbiquitousUserDefaultsDidChangeAccountsNotification").orElseThrow() }
private val NSUbiquitousUserDefaultsDidChangeAccountsNotification_VH: VarHandle by lazy { NSUbiquitousUserDefaultsDidChangeAccountsNotification_LAYOUT.varHandle() }

var NSUbiquitousUserDefaultsDidChangeAccountsNotification: MemorySegment
    get() = NSUbiquitousUserDefaultsDidChangeAccountsNotification_VH.get(NSUbiquitousUserDefaultsDidChangeAccountsNotification_SEGMENT) as MemorySegment
    set(value) = NSUbiquitousUserDefaultsDidChangeAccountsNotification_VH.set(NSUbiquitousUserDefaultsDidChangeAccountsNotification_SEGMENT, value)

