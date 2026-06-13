package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibility
 * Inherits protocols: NSObject
 */
interface NSAccessibility {
    fun accessibilityLayoutPointForScreenPoint(point: MemorySegment): MemorySegment
    
    fun accessibilityLayoutSizeForScreenSize(size: MemorySegment): MemorySegment
    
    fun accessibilityScreenPointForLayoutPoint(point: MemorySegment): MemorySegment
    
    fun accessibilityScreenSizeForLayoutSize(size: MemorySegment): MemorySegment
    
    fun accessibilityCellForColumn_row(column: Long, row: Long): MemorySegment
    
    fun accessibilityAttributedStringForRange(range: MemorySegment): MemorySegment
    
    fun accessibilityRangeForLine(line: Long): MemorySegment
    
    fun accessibilityStringForRange(range: MemorySegment): MemorySegment
    
    fun accessibilityRangeForPosition(point: MemorySegment): MemorySegment
    
    fun accessibilityRangeForIndex(index: Long): MemorySegment
    
    fun accessibilityFrameForRange(range: MemorySegment): MemorySegment
    
    fun accessibilityRTFForRange(range: MemorySegment): MemorySegment
    
    fun accessibilityStyleRangeForIndex(index: Long): MemorySegment
    
    fun accessibilityLineForIndex(index: Long): Long
    
    fun accessibilityPerformCancel(): Boolean
    
    fun accessibilityPerformConfirm(): Boolean
    
    fun accessibilityPerformDecrement(): Boolean
    
    fun accessibilityPerformDelete(): Boolean
    
    fun accessibilityPerformIncrement(): Boolean
    
    fun accessibilityPerformPick(): Boolean
    
    fun accessibilityPerformPress(): Boolean
    
    fun accessibilityPerformRaise(): Boolean
    
    fun accessibilityPerformShowAlternateUI(): Boolean
    
    fun accessibilityPerformShowDefaultUI(): Boolean
    
    fun accessibilityPerformShowMenu(): Boolean
    
    fun isAccessibilitySelectorAllowed(selector: MemorySegment): Boolean
    
    fun isAccessibilityElement(): Boolean
    
    fun setAccessibilityElement(accessibilityElement: Boolean): Unit
    
    fun accessibilityFrame(): MemorySegment
    
    fun setAccessibilityFrame(accessibilityFrame: MemorySegment): Unit
    
    fun isAccessibilityFocused(): Boolean
    
    fun setAccessibilityFocused(accessibilityFocused: Boolean): Unit
    
    fun accessibilityActivationPoint(): MemorySegment
    
    fun setAccessibilityActivationPoint(accessibilityActivationPoint: MemorySegment): Unit
    
    fun accessibilityTopLevelUIElement(): MemorySegment
    
    fun setAccessibilityTopLevelUIElement(accessibilityTopLevelUIElement: MemorySegment): Unit
    
    fun accessibilityURL(): MemorySegment
    
    fun setAccessibilityURL(accessibilityURL: MemorySegment): Unit
    
    fun accessibilityValue(): MemorySegment
    
    fun setAccessibilityValue(accessibilityValue: MemorySegment): Unit
    
    fun accessibilityValueDescription(): MemorySegment
    
    fun setAccessibilityValueDescription(accessibilityValueDescription: MemorySegment): Unit
    
    fun accessibilityVisibleChildren(): MemorySegment
    
    fun setAccessibilityVisibleChildren(accessibilityVisibleChildren: MemorySegment): Unit
    
    fun accessibilitySubrole(): MemorySegment
    
    fun setAccessibilitySubrole(accessibilitySubrole: MemorySegment): Unit
    
    fun accessibilityTitle(): MemorySegment
    
    fun setAccessibilityTitle(accessibilityTitle: MemorySegment): Unit
    
    fun accessibilityTitleUIElement(): MemorySegment
    
    fun setAccessibilityTitleUIElement(accessibilityTitleUIElement: MemorySegment): Unit
    
    fun accessibilityNextContents(): MemorySegment
    
    fun setAccessibilityNextContents(accessibilityNextContents: MemorySegment): Unit
    
    fun accessibilityOrientation(): MemorySegment
    
    fun setAccessibilityOrientation(accessibilityOrientation: MemorySegment): Unit
    
    fun accessibilityOverflowButton(): MemorySegment
    
    fun setAccessibilityOverflowButton(accessibilityOverflowButton: MemorySegment): Unit
    
    fun accessibilityParent(): MemorySegment
    
    fun setAccessibilityParent(accessibilityParent: MemorySegment): Unit
    
    fun accessibilityPlaceholderValue(): MemorySegment
    
    fun setAccessibilityPlaceholderValue(accessibilityPlaceholderValue: MemorySegment): Unit
    
    fun accessibilityPreviousContents(): MemorySegment
    
    fun setAccessibilityPreviousContents(accessibilityPreviousContents: MemorySegment): Unit
    
    fun accessibilityRole(): MemorySegment
    
    fun setAccessibilityRole(accessibilityRole: MemorySegment): Unit
    
    fun accessibilityRoleDescription(): MemorySegment
    
    fun setAccessibilityRoleDescription(accessibilityRoleDescription: MemorySegment): Unit
    
    fun accessibilitySearchButton(): MemorySegment
    
    fun setAccessibilitySearchButton(accessibilitySearchButton: MemorySegment): Unit
    
    fun accessibilitySearchMenu(): MemorySegment
    
    fun setAccessibilitySearchMenu(accessibilitySearchMenu: MemorySegment): Unit
    
    fun isAccessibilitySelected(): Boolean
    
    fun setAccessibilitySelected(accessibilitySelected: Boolean): Unit
    
    fun accessibilitySelectedChildren(): MemorySegment
    
    fun setAccessibilitySelectedChildren(accessibilitySelectedChildren: MemorySegment): Unit
    
    fun accessibilityServesAsTitleForUIElements(): MemorySegment
    
    fun setAccessibilityServesAsTitleForUIElements(accessibilityServesAsTitleForUIElements: MemorySegment): Unit
    
    fun accessibilityShownMenu(): MemorySegment
    
    fun setAccessibilityShownMenu(accessibilityShownMenu: MemorySegment): Unit
    
    fun accessibilityMinValue(): MemorySegment
    
    fun setAccessibilityMinValue(accessibilityMinValue: MemorySegment): Unit
    
    fun accessibilityMaxValue(): MemorySegment
    
    fun setAccessibilityMaxValue(accessibilityMaxValue: MemorySegment): Unit
    
    fun accessibilityLinkedUIElements(): MemorySegment
    
    fun setAccessibilityLinkedUIElements(accessibilityLinkedUIElements: MemorySegment): Unit
    
    fun accessibilityWindow(): MemorySegment
    
    fun setAccessibilityWindow(accessibilityWindow: MemorySegment): Unit
    
    fun accessibilityIdentifier(): MemorySegment
    
    fun setAccessibilityIdentifier(accessibilityIdentifier: MemorySegment): Unit
    
    fun accessibilityHelp(): MemorySegment
    
    fun setAccessibilityHelp(accessibilityHelp: MemorySegment): Unit
    
    fun accessibilityFilename(): MemorySegment
    
    fun setAccessibilityFilename(accessibilityFilename: MemorySegment): Unit
    
    fun isAccessibilityExpanded(): Boolean
    
    fun setAccessibilityExpanded(accessibilityExpanded: Boolean): Unit
    
    fun isAccessibilityEdited(): Boolean
    
    fun setAccessibilityEdited(accessibilityEdited: Boolean): Unit
    
    fun isAccessibilityEnabled(): Boolean
    
    fun setAccessibilityEnabled(accessibilityEnabled: Boolean): Unit
    
    fun accessibilityChildren(): MemorySegment
    
    fun setAccessibilityChildren(accessibilityChildren: MemorySegment): Unit
    
    /** @return NSArray<id<NSAccessibilityElement>> * */
    fun accessibilityChildrenInNavigationOrder(): MemorySegment
    
    fun setAccessibilityChildrenInNavigationOrder(accessibilityChildrenInNavigationOrder: MemorySegment): Unit
    
    fun accessibilityClearButton(): MemorySegment
    
    fun setAccessibilityClearButton(accessibilityClearButton: MemorySegment): Unit
    
    fun accessibilityCancelButton(): MemorySegment
    
    fun setAccessibilityCancelButton(accessibilityCancelButton: MemorySegment): Unit
    
    fun isAccessibilityProtectedContent(): Boolean
    
    fun setAccessibilityProtectedContent(accessibilityProtectedContent: Boolean): Unit
    
    fun accessibilityContents(): MemorySegment
    
    fun setAccessibilityContents(accessibilityContents: MemorySegment): Unit
    
    fun accessibilityLabel(): MemorySegment
    
    fun setAccessibilityLabel(accessibilityLabel: MemorySegment): Unit
    
    fun isAccessibilityAlternateUIVisible(): Boolean
    
    fun setAccessibilityAlternateUIVisible(accessibilityAlternateUIVisible: Boolean): Unit
    
    fun accessibilitySharedFocusElements(): MemorySegment
    
    fun setAccessibilitySharedFocusElements(accessibilitySharedFocusElements: MemorySegment): Unit
    
    fun isAccessibilityRequired(): Boolean
    
    fun setAccessibilityRequired(accessibilityRequired: Boolean): Unit
    
    /** @return NSArray<NSAccessibilityCustomRotor *> * */
    fun accessibilityCustomRotors(): MemorySegment
    
    fun setAccessibilityCustomRotors(accessibilityCustomRotors: MemorySegment): Unit
    
    /** @return NSArray<NSString *> * */
    fun accessibilityUserInputLabels(): MemorySegment
    
    fun setAccessibilityUserInputLabels(accessibilityUserInputLabels: MemorySegment): Unit
    
    /** @return NSArray<NSAttributedString *> * */
    fun accessibilityAttributedUserInputLabels(): MemorySegment
    
    fun setAccessibilityAttributedUserInputLabels(accessibilityAttributedUserInputLabels: MemorySegment): Unit
    
    fun accessibilityApplicationFocusedUIElement(): MemorySegment
    
    fun setAccessibilityApplicationFocusedUIElement(accessibilityApplicationFocusedUIElement: MemorySegment): Unit
    
    fun accessibilityMainWindow(): MemorySegment
    
    fun setAccessibilityMainWindow(accessibilityMainWindow: MemorySegment): Unit
    
    fun isAccessibilityHidden(): Boolean
    
    fun setAccessibilityHidden(accessibilityHidden: Boolean): Unit
    
    fun isAccessibilityFrontmost(): Boolean
    
    fun setAccessibilityFrontmost(accessibilityFrontmost: Boolean): Unit
    
    fun accessibilityFocusedWindow(): MemorySegment
    
    fun setAccessibilityFocusedWindow(accessibilityFocusedWindow: MemorySegment): Unit
    
    fun accessibilityWindows(): MemorySegment
    
    fun setAccessibilityWindows(accessibilityWindows: MemorySegment): Unit
    
    fun accessibilityExtrasMenuBar(): MemorySegment
    
    fun setAccessibilityExtrasMenuBar(accessibilityExtrasMenuBar: MemorySegment): Unit
    
    fun accessibilityMenuBar(): MemorySegment
    
    fun setAccessibilityMenuBar(accessibilityMenuBar: MemorySegment): Unit
    
    fun accessibilityColumnTitles(): MemorySegment
    
    fun setAccessibilityColumnTitles(accessibilityColumnTitles: MemorySegment): Unit
    
    fun isAccessibilityOrderedByRow(): Boolean
    
    fun setAccessibilityOrderedByRow(accessibilityOrderedByRow: Boolean): Unit
    
    fun accessibilityHorizontalUnits(): MemorySegment
    
    fun setAccessibilityHorizontalUnits(accessibilityHorizontalUnits: MemorySegment): Unit
    
    fun accessibilityVerticalUnits(): MemorySegment
    
    fun setAccessibilityVerticalUnits(accessibilityVerticalUnits: MemorySegment): Unit
    
    fun accessibilityHorizontalUnitDescription(): MemorySegment
    
    fun setAccessibilityHorizontalUnitDescription(accessibilityHorizontalUnitDescription: MemorySegment): Unit
    
    fun accessibilityVerticalUnitDescription(): MemorySegment
    
    fun setAccessibilityVerticalUnitDescription(accessibilityVerticalUnitDescription: MemorySegment): Unit
    
    fun accessibilityHandles(): MemorySegment
    
    fun setAccessibilityHandles(accessibilityHandles: MemorySegment): Unit
    
    fun accessibilityWarningValue(): MemorySegment
    
    fun setAccessibilityWarningValue(accessibilityWarningValue: MemorySegment): Unit
    
    fun accessibilityCriticalValue(): MemorySegment
    
    fun setAccessibilityCriticalValue(accessibilityCriticalValue: MemorySegment): Unit
    
    fun isAccessibilityDisclosed(): Boolean
    
    fun setAccessibilityDisclosed(accessibilityDisclosed: Boolean): Unit
    
    fun accessibilityDisclosedByRow(): MemorySegment
    
    fun setAccessibilityDisclosedByRow(accessibilityDisclosedByRow: MemorySegment): Unit
    
    fun accessibilityDisclosedRows(): MemorySegment
    
    fun setAccessibilityDisclosedRows(accessibilityDisclosedRows: MemorySegment): Unit
    
    fun accessibilityDisclosureLevel(): Long
    
    fun setAccessibilityDisclosureLevel(accessibilityDisclosureLevel: Long): Unit
    
    fun accessibilityMarkerUIElements(): MemorySegment
    
    fun setAccessibilityMarkerUIElements(accessibilityMarkerUIElements: MemorySegment): Unit
    
    fun accessibilityMarkerValues(): MemorySegment
    
    fun setAccessibilityMarkerValues(accessibilityMarkerValues: MemorySegment): Unit
    
    fun accessibilityMarkerGroupUIElement(): MemorySegment
    
    fun setAccessibilityMarkerGroupUIElement(accessibilityMarkerGroupUIElement: MemorySegment): Unit
    
    fun accessibilityUnits(): MemorySegment
    
    fun setAccessibilityUnits(accessibilityUnits: MemorySegment): Unit
    
    fun accessibilityUnitDescription(): MemorySegment
    
    fun setAccessibilityUnitDescription(accessibilityUnitDescription: MemorySegment): Unit
    
    fun accessibilityRulerMarkerType(): MemorySegment
    
    fun setAccessibilityRulerMarkerType(accessibilityRulerMarkerType: MemorySegment): Unit
    
    fun accessibilityMarkerTypeDescription(): MemorySegment
    
    fun setAccessibilityMarkerTypeDescription(accessibilityMarkerTypeDescription: MemorySegment): Unit
    
    fun accessibilityHorizontalScrollBar(): MemorySegment
    
    fun setAccessibilityHorizontalScrollBar(accessibilityHorizontalScrollBar: MemorySegment): Unit
    
    fun accessibilityVerticalScrollBar(): MemorySegment
    
    fun setAccessibilityVerticalScrollBar(accessibilityVerticalScrollBar: MemorySegment): Unit
    
    /** @return NSArray<NSNumber *> * */
    fun accessibilityAllowedValues(): MemorySegment
    
    fun setAccessibilityAllowedValues(accessibilityAllowedValues: MemorySegment): Unit
    
    fun accessibilityLabelUIElements(): MemorySegment
    
    fun setAccessibilityLabelUIElements(accessibilityLabelUIElements: MemorySegment): Unit
    
    fun accessibilityLabelValue(): Float
    
    fun setAccessibilityLabelValue(accessibilityLabelValue: Float): Unit
    
    fun accessibilitySplitters(): MemorySegment
    
    fun setAccessibilitySplitters(accessibilitySplitters: MemorySegment): Unit
    
    fun accessibilityDecrementButton(): MemorySegment
    
    fun setAccessibilityDecrementButton(accessibilityDecrementButton: MemorySegment): Unit
    
    fun accessibilityIncrementButton(): MemorySegment
    
    fun setAccessibilityIncrementButton(accessibilityIncrementButton: MemorySegment): Unit
    
    fun accessibilityTabs(): MemorySegment
    
    fun setAccessibilityTabs(accessibilityTabs: MemorySegment): Unit
    
    fun accessibilityHeader(): MemorySegment
    
    fun setAccessibilityHeader(accessibilityHeader: MemorySegment): Unit
    
    fun accessibilityColumnCount(): Long
    
    fun setAccessibilityColumnCount(accessibilityColumnCount: Long): Unit
    
    fun accessibilityRowCount(): Long
    
    fun setAccessibilityRowCount(accessibilityRowCount: Long): Unit
    
    fun accessibilityIndex(): Long
    
    fun setAccessibilityIndex(accessibilityIndex: Long): Unit
    
    fun accessibilityColumns(): MemorySegment
    
    fun setAccessibilityColumns(accessibilityColumns: MemorySegment): Unit
    
    fun accessibilityRows(): MemorySegment
    
    fun setAccessibilityRows(accessibilityRows: MemorySegment): Unit
    
    fun accessibilityVisibleRows(): MemorySegment
    
    fun setAccessibilityVisibleRows(accessibilityVisibleRows: MemorySegment): Unit
    
    fun accessibilitySelectedRows(): MemorySegment
    
    fun setAccessibilitySelectedRows(accessibilitySelectedRows: MemorySegment): Unit
    
    fun accessibilityVisibleColumns(): MemorySegment
    
    fun setAccessibilityVisibleColumns(accessibilityVisibleColumns: MemorySegment): Unit
    
    fun accessibilitySelectedColumns(): MemorySegment
    
    fun setAccessibilitySelectedColumns(accessibilitySelectedColumns: MemorySegment): Unit
    
    fun accessibilitySortDirection(): MemorySegment
    
    fun setAccessibilitySortDirection(accessibilitySortDirection: MemorySegment): Unit
    
    fun accessibilityRowHeaderUIElements(): MemorySegment
    
    fun setAccessibilityRowHeaderUIElements(accessibilityRowHeaderUIElements: MemorySegment): Unit
    
    fun accessibilitySelectedCells(): MemorySegment
    
    fun setAccessibilitySelectedCells(accessibilitySelectedCells: MemorySegment): Unit
    
    fun accessibilityVisibleCells(): MemorySegment
    
    fun setAccessibilityVisibleCells(accessibilityVisibleCells: MemorySegment): Unit
    
    fun accessibilityColumnHeaderUIElements(): MemorySegment
    
    fun setAccessibilityColumnHeaderUIElements(accessibilityColumnHeaderUIElements: MemorySegment): Unit
    
    fun accessibilityRowIndexRange(): MemorySegment
    
    fun setAccessibilityRowIndexRange(accessibilityRowIndexRange: MemorySegment): Unit
    
    fun accessibilityColumnIndexRange(): MemorySegment
    
    fun setAccessibilityColumnIndexRange(accessibilityColumnIndexRange: MemorySegment): Unit
    
    fun accessibilityInsertionPointLineNumber(): Long
    
    fun setAccessibilityInsertionPointLineNumber(accessibilityInsertionPointLineNumber: Long): Unit
    
    fun accessibilitySharedCharacterRange(): MemorySegment
    
    fun setAccessibilitySharedCharacterRange(accessibilitySharedCharacterRange: MemorySegment): Unit
    
    fun accessibilitySharedTextUIElements(): MemorySegment
    
    fun setAccessibilitySharedTextUIElements(accessibilitySharedTextUIElements: MemorySegment): Unit
    
    fun accessibilityVisibleCharacterRange(): MemorySegment
    
    fun setAccessibilityVisibleCharacterRange(accessibilityVisibleCharacterRange: MemorySegment): Unit
    
    fun accessibilityNumberOfCharacters(): Long
    
    fun setAccessibilityNumberOfCharacters(accessibilityNumberOfCharacters: Long): Unit
    
    fun accessibilitySelectedText(): MemorySegment
    
    fun setAccessibilitySelectedText(accessibilitySelectedText: MemorySegment): Unit
    
    fun accessibilitySelectedTextRange(): MemorySegment
    
    fun setAccessibilitySelectedTextRange(accessibilitySelectedTextRange: MemorySegment): Unit
    
    /** @return NSArray<NSValue *> * */
    fun accessibilitySelectedTextRanges(): MemorySegment
    
    fun setAccessibilitySelectedTextRanges(accessibilitySelectedTextRanges: MemorySegment): Unit
    
    fun accessibilityToolbarButton(): MemorySegment
    
    fun setAccessibilityToolbarButton(accessibilityToolbarButton: MemorySegment): Unit
    
    fun isAccessibilityModal(): Boolean
    
    fun setAccessibilityModal(accessibilityModal: Boolean): Unit
    
    fun accessibilityProxy(): MemorySegment
    
    fun setAccessibilityProxy(accessibilityProxy: MemorySegment): Unit
    
    fun isAccessibilityMain(): Boolean
    
    fun setAccessibilityMain(accessibilityMain: Boolean): Unit
    
    fun accessibilityFullScreenButton(): MemorySegment
    
    fun setAccessibilityFullScreenButton(accessibilityFullScreenButton: MemorySegment): Unit
    
    fun accessibilityGrowArea(): MemorySegment
    
    fun setAccessibilityGrowArea(accessibilityGrowArea: MemorySegment): Unit
    
    fun accessibilityDocument(): MemorySegment
    
    fun setAccessibilityDocument(accessibilityDocument: MemorySegment): Unit
    
    fun accessibilityDefaultButton(): MemorySegment
    
    fun setAccessibilityDefaultButton(accessibilityDefaultButton: MemorySegment): Unit
    
    fun accessibilityCloseButton(): MemorySegment
    
    fun setAccessibilityCloseButton(accessibilityCloseButton: MemorySegment): Unit
    
    fun accessibilityZoomButton(): MemorySegment
    
    fun setAccessibilityZoomButton(accessibilityZoomButton: MemorySegment): Unit
    
    fun accessibilityMinimizeButton(): MemorySegment
    
    fun setAccessibilityMinimizeButton(accessibilityMinimizeButton: MemorySegment): Unit
    
    fun isAccessibilityMinimized(): Boolean
    
    fun setAccessibilityMinimized(accessibilityMinimized: Boolean): Unit
    
    /** @return NSArray<NSAccessibilityCustomAction *> * */
    fun accessibilityCustomActions(): MemorySegment
    
    fun setAccessibilityCustomActions(accessibilityCustomActions: MemorySegment): Unit
    
}

