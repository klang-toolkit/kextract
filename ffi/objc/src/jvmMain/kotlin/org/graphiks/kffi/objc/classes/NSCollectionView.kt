package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionView
 * Superclass: NSView
 * Protocols: NSDraggingSource, NSDraggingDestination
 */
open class NSCollectionView(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionView") }
        
    }
    
    open fun reloadData(): Unit {
        val sel = ObjCRuntime.sel("reloadData")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun layoutAttributesForItemAtIndexPath(indexPath: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("layoutAttributesForItemAtIndexPath:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, indexPath) as MemorySegment
    }
    
    open fun layoutAttributesForSupplementaryElementOfKind_atIndexPath(kind: MemorySegment, indexPath: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("layoutAttributesForSupplementaryElementOfKind:atIndexPath:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, kind, indexPath) as MemorySegment
    }
    
    open fun frameForItemAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("frameForItemAtIndex:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, index) as MemorySegment
    }
    
    open fun frameForItemAtIndex_withNumberOfItems(index: Long, numberOfItems: Long): MemorySegment {
        val sel = ObjCRuntime.sel("frameForItemAtIndex:withNumberOfItems:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, index, numberOfItems) as MemorySegment
    }
    
    open fun numberOfItemsInSection(section: Long): Long {
        val sel = ObjCRuntime.sel("numberOfItemsInSection:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, section) as Long
    }
    
    open fun selectItemsAtIndexPaths_scrollPosition(indexPaths: MemorySegment, scrollPosition: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectItemsAtIndexPaths:scrollPosition:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPaths, scrollPosition)
    }
    
    open fun deselectItemsAtIndexPaths(indexPaths: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("deselectItemsAtIndexPaths:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPaths)
    }
    
    open fun selectAll(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("selectAll:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun deselectAll(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("deselectAll:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun registerClass_forItemWithIdentifier(itemClass: MemorySegment, identifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("registerClass:forItemWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, itemClass, identifier)
    }
    
    open fun registerNib_forItemWithIdentifier(nib: MemorySegment, identifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("registerNib:forItemWithIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, nib, identifier)
    }
    
    open fun registerClass_forSupplementaryViewOfKind_withIdentifier(viewClass: MemorySegment, kind: MemorySegment, identifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("registerClass:forSupplementaryViewOfKind:withIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, viewClass, kind, identifier)
    }
    
    open fun registerNib_forSupplementaryViewOfKind_withIdentifier(nib: MemorySegment, kind: MemorySegment, identifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("registerNib:forSupplementaryViewOfKind:withIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, nib, kind, identifier)
    }
    
    open fun makeItemWithIdentifier_forIndexPath(identifier: MemorySegment, indexPath: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("makeItemWithIdentifier:forIndexPath:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, identifier, indexPath) as MemorySegment
    }
    
    open fun makeSupplementaryViewOfKind_withIdentifier_forIndexPath(elementKind: MemorySegment, identifier: MemorySegment, indexPath: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("makeSupplementaryViewOfKind:withIdentifier:forIndexPath:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, elementKind, identifier, indexPath) as MemorySegment
    }
    
    open fun itemAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("itemAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun itemAtIndexPath(indexPath: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("itemAtIndexPath:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, indexPath) as MemorySegment
    }
    
    /** @return NSArray<NSCollectionViewItem *> * */
    open fun visibleItems(): MemorySegment {
        val sel = ObjCRuntime.sel("visibleItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** @return NSSet<NSIndexPath *> * */
    open fun indexPathsForVisibleItems(): MemorySegment {
        val sel = ObjCRuntime.sel("indexPathsForVisibleItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun indexPathForItem(item: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("indexPathForItem:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, item) as MemorySegment
    }
    
    open fun indexPathForItemAtPoint(point: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("indexPathForItemAtPoint:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    /** @return NSView<NSCollectionViewElement> * */
    open fun supplementaryViewForElementKind_atIndexPath(elementKind: MemorySegment, indexPath: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("supplementaryViewForElementKind:atIndexPath:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, elementKind, indexPath) as MemorySegment
    }
    
    /** @return NSArray<NSView<NSCollectionViewElement> *> * */
    open fun visibleSupplementaryViewsOfKind(elementKind: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("visibleSupplementaryViewsOfKind:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, elementKind) as MemorySegment
    }
    
    /** @return NSSet<NSIndexPath *> * */
    open fun indexPathsForVisibleSupplementaryElementsOfKind(elementKind: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("indexPathsForVisibleSupplementaryElementsOfKind:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, elementKind) as MemorySegment
    }
    
    open fun insertSections(sections: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertSections:")
        ObjCRuntime.msgSend(null, ptr, sel, sections)
    }
    
    open fun deleteSections(sections: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("deleteSections:")
        ObjCRuntime.msgSend(null, ptr, sel, sections)
    }
    
    open fun reloadSections(sections: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reloadSections:")
        ObjCRuntime.msgSend(null, ptr, sel, sections)
    }
    
    open fun moveSection_toSection(section: Long, newSection: Long): Unit {
        val sel = ObjCRuntime.sel("moveSection:toSection:")
        ObjCRuntime.msgSend(null, ptr, sel, section, newSection)
    }
    
    open fun insertItemsAtIndexPaths(indexPaths: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertItemsAtIndexPaths:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPaths)
    }
    
    open fun deleteItemsAtIndexPaths(indexPaths: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("deleteItemsAtIndexPaths:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPaths)
    }
    
    open fun reloadItemsAtIndexPaths(indexPaths: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reloadItemsAtIndexPaths:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPaths)
    }
    
    open fun moveItemAtIndexPath_toIndexPath(indexPath: MemorySegment, newIndexPath: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveItemAtIndexPath:toIndexPath:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPath, newIndexPath)
    }
    
    open fun performBatchUpdates_completionHandler(updates: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performBatchUpdates:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, updates, completionHandler)
    }
    
    open fun toggleSectionCollapse(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("toggleSectionCollapse:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun scrollToItemsAtIndexPaths_scrollPosition(indexPaths: MemorySegment, scrollPosition: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scrollToItemsAtIndexPaths:scrollPosition:")
        ObjCRuntime.msgSend(null, ptr, sel, indexPaths, scrollPosition)
    }
    
    open fun setDraggingSourceOperationMask_forLocal(dragOperationMask: MemorySegment, localDestination: Boolean): Unit {
        val sel = ObjCRuntime.sel("setDraggingSourceOperationMask:forLocal:")
        ObjCRuntime.msgSend(null, ptr, sel, dragOperationMask, localDestination)
    }
    
    open fun draggingImageForItemsAtIndexPaths_withEvent_offset(indexPaths: MemorySegment, event: MemorySegment, dragImageOffset: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("draggingImageForItemsAtIndexPaths:withEvent:offset:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, indexPaths, event, dragImageOffset) as MemorySegment
    }
    
    open fun draggingImageForItemsAtIndexes_withEvent_offset(indexes: MemorySegment, event: MemorySegment, dragImageOffset: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("draggingImageForItemsAtIndexes:withEvent:offset:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, indexes, event, dragImageOffset) as MemorySegment
    }
    
    // @property dataSource
    /** @return id<NSCollectionViewDataSource> */
    open fun dataSource(): MemorySegment {
        val sel = ObjCRuntime.sel("dataSource")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDataSource(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDataSource:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property prefetchDataSource
    /** @return id<NSCollectionViewPrefetching> */
    open fun prefetchDataSource(): MemorySegment {
        val sel = ObjCRuntime.sel("prefetchDataSource")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPrefetchDataSource(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPrefetchDataSource:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property content
    /** @return NSArray<id> * */
    open fun content(): MemorySegment {
        val sel = ObjCRuntime.sel("content")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContent(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property delegate
    /** @return id<NSCollectionViewDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backgroundView
    open fun backgroundView(): MemorySegment {
        val sel = ObjCRuntime.sel("backgroundView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackgroundView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackgroundView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backgroundViewScrollsWithContent
    open fun backgroundViewScrollsWithContent(): Boolean {
        val sel = ObjCRuntime.sel("backgroundViewScrollsWithContent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setBackgroundViewScrollsWithContent(value: Boolean) {
        val sel = ObjCRuntime.sel("setBackgroundViewScrollsWithContent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property collectionViewLayout
    open fun collectionViewLayout(): MemorySegment {
        val sel = ObjCRuntime.sel("collectionViewLayout")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCollectionViewLayout(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCollectionViewLayout:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property backgroundColors
    /** @return NSArray<NSColor *> * */
    open fun backgroundColors(): MemorySegment {
        val sel = ObjCRuntime.sel("backgroundColors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackgroundColors(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackgroundColors:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberOfSections
    open fun numberOfSections(): Long {
        val sel = ObjCRuntime.sel("numberOfSections")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property firstResponder
    open fun isFirstResponder(): Boolean {
        val sel = ObjCRuntime.sel("isFirstResponder")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property selectable
    open fun isSelectable(): Boolean {
        val sel = ObjCRuntime.sel("isSelectable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSelectable(value: Boolean) {
        val sel = ObjCRuntime.sel("setSelectable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsEmptySelection
    open fun allowsEmptySelection(): Boolean {
        val sel = ObjCRuntime.sel("allowsEmptySelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsEmptySelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsEmptySelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsMultipleSelection
    open fun allowsMultipleSelection(): Boolean {
        val sel = ObjCRuntime.sel("allowsMultipleSelection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsMultipleSelection(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsMultipleSelection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectionIndexes
    open fun selectionIndexes(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionIndexes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionIndexes(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionIndexes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectionIndexPaths
    /** @return NSSet<NSIndexPath *> * */
    open fun selectionIndexPaths(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionIndexPaths")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionIndexPaths(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionIndexPaths:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSDeprecated on NSCollectionView ─────────────────────────────────────────

fun NSCollectionView.newItemForRepresentedObject(`object`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("newItemForRepresentedObject:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `object`) as MemorySegment
}

fun NSCollectionView.itemPrototype(): MemorySegment {
    val sel = ObjCRuntime.sel("itemPrototype")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSCollectionView.setItemPrototype(itemPrototype: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setItemPrototype:")
    ObjCRuntime.msgSend(null, this.ptr, sel, itemPrototype)
}

fun NSCollectionView.maxNumberOfRows(): Long {
    val sel = ObjCRuntime.sel("maxNumberOfRows")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSCollectionView.setMaxNumberOfRows(maxNumberOfRows: Long): Unit {
    val sel = ObjCRuntime.sel("setMaxNumberOfRows:")
    ObjCRuntime.msgSend(null, this.ptr, sel, maxNumberOfRows)
}

fun NSCollectionView.maxNumberOfColumns(): Long {
    val sel = ObjCRuntime.sel("maxNumberOfColumns")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSCollectionView.setMaxNumberOfColumns(maxNumberOfColumns: Long): Unit {
    val sel = ObjCRuntime.sel("setMaxNumberOfColumns:")
    ObjCRuntime.msgSend(null, this.ptr, sel, maxNumberOfColumns)
}

fun NSCollectionView.minItemSize(): MemorySegment {
    val sel = ObjCRuntime.sel("minItemSize")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel) as MemorySegment
}

fun NSCollectionView.setMinItemSize(minItemSize: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setMinItemSize:")
    ObjCRuntime.msgSend(null, this.ptr, sel, minItemSize)
}

fun NSCollectionView.maxItemSize(): MemorySegment {
    val sel = ObjCRuntime.sel("maxItemSize")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel) as MemorySegment
}

fun NSCollectionView.setMaxItemSize(maxItemSize: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setMaxItemSize:")
    ObjCRuntime.msgSend(null, this.ptr, sel, maxItemSize)
}

