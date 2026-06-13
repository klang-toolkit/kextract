package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSFilePresenter
 * Inherits protocols: NSObject
 */
interface NSFilePresenter {
    // @optional
    fun relinquishPresentedItemToReader(reader: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'relinquishPresentedItemToReader:' not implemented")
    
    // @optional
    fun relinquishPresentedItemToWriter(writer: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'relinquishPresentedItemToWriter:' not implemented")
    
    // @optional
    fun savePresentedItemChangesWithCompletionHandler(completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'savePresentedItemChangesWithCompletionHandler:' not implemented")
    
    // @optional
    fun accommodatePresentedItemDeletionWithCompletionHandler(completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'accommodatePresentedItemDeletionWithCompletionHandler:' not implemented")
    
    // @optional
    fun accommodatePresentedItemEvictionWithCompletionHandler(completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'accommodatePresentedItemEvictionWithCompletionHandler:' not implemented")
    
    // @optional
    fun presentedItemDidMoveToURL(newURL: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedItemDidMoveToURL:' not implemented")
    
    // @optional
    fun presentedItemDidChange(): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedItemDidChange' not implemented")
    
    // @optional
    fun presentedItemDidChangeUbiquityAttributes(attributes: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedItemDidChangeUbiquityAttributes:' not implemented")
    
    // @optional
    fun presentedItemDidGainVersion(version: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedItemDidGainVersion:' not implemented")
    
    // @optional
    fun presentedItemDidLoseVersion(version: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedItemDidLoseVersion:' not implemented")
    
    // @optional
    fun presentedItemDidResolveConflictVersion(version: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedItemDidResolveConflictVersion:' not implemented")
    
    // @optional
    fun accommodatePresentedSubitemDeletionAtURL_completionHandler(url: MemorySegment, completionHandler: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'accommodatePresentedSubitemDeletionAtURL:completionHandler:' not implemented")
    
    // @optional
    fun presentedSubitemDidAppearAtURL(url: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedSubitemDidAppearAtURL:' not implemented")
    
    // @optional
    fun presentedSubitemAtURL_didMoveToURL(oldURL: MemorySegment, newURL: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedSubitemAtURL:didMoveToURL:' not implemented")
    
    // @optional
    fun presentedSubitemDidChangeAtURL(url: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedSubitemDidChangeAtURL:' not implemented")
    
    // @optional
    fun presentedSubitemAtURL_didGainVersion(url: MemorySegment, version: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedSubitemAtURL:didGainVersion:' not implemented")
    
    // @optional
    fun presentedSubitemAtURL_didLoseVersion(url: MemorySegment, version: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedSubitemAtURL:didLoseVersion:' not implemented")
    
    // @optional
    fun presentedSubitemAtURL_didResolveConflictVersion(url: MemorySegment, version: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'presentedSubitemAtURL:didResolveConflictVersion:' not implemented")
    
    fun presentedItemURL(): MemorySegment
    
    fun presentedItemOperationQueue(): MemorySegment
    
    // @optional
    fun primaryPresentedItemURL(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'primaryPresentedItemURL' not implemented")
    
    /** @return NSSet<NSURLResourceKey> * */
    // @optional
    fun observedPresentedItemUbiquityAttributes(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'observedPresentedItemUbiquityAttributes' not implemented")
    
}

