package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFileCoordinator
 * Superclass: NSObject
 */
open class NSFileCoordinator(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFileCoordinator") }
        
        fun addFilePresenter(filePresenter: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("addFilePresenter:")
            ObjCRuntime.msgSend(null, _class, sel, filePresenter)
        }
        
        fun removeFilePresenter(filePresenter: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("removeFilePresenter:")
            ObjCRuntime.msgSend(null, _class, sel, filePresenter)
        }
        
        /** @return NSArray<id<NSFilePresenter>> * */
        fun filePresenters(): MemorySegment {
            val sel = ObjCRuntime.sel("filePresenters")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithFilePresenter(filePresenterOrNil: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFilePresenter:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, filePresenterOrNil) as MemorySegment
    }
    
    open fun coordinateAccessWithIntents_queue_byAccessor(intents: MemorySegment, queue: MemorySegment, accessor: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("coordinateAccessWithIntents:queue:byAccessor:")
        ObjCRuntime.msgSend(null, ptr, sel, intents, queue, accessor)
    }
    
    open fun coordinateReadingItemAtURL_options_error_byAccessor(url: MemorySegment, options: MemorySegment, outError: MemorySegment, reader: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("coordinateReadingItemAtURL:options:error:byAccessor:")
        ObjCRuntime.msgSend(null, ptr, sel, url, options, outError, reader)
    }
    
    open fun coordinateWritingItemAtURL_options_error_byAccessor(url: MemorySegment, options: MemorySegment, outError: MemorySegment, writer: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("coordinateWritingItemAtURL:options:error:byAccessor:")
        ObjCRuntime.msgSend(null, ptr, sel, url, options, outError, writer)
    }
    
    open fun coordinateReadingItemAtURL_options_writingItemAtURL_options_error_byAccessor(readingURL: MemorySegment, readingOptions: MemorySegment, writingURL: MemorySegment, writingOptions: MemorySegment, outError: MemorySegment, readerWriter: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("coordinateReadingItemAtURL:options:writingItemAtURL:options:error:byAccessor:")
        ObjCRuntime.msgSend(null, ptr, sel, readingURL, readingOptions, writingURL, writingOptions, outError, readerWriter)
    }
    
    open fun coordinateWritingItemAtURL_options_writingItemAtURL_options_error_byAccessor(url1: MemorySegment, options1: MemorySegment, url2: MemorySegment, options2: MemorySegment, outError: MemorySegment, writer: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("coordinateWritingItemAtURL:options:writingItemAtURL:options:error:byAccessor:")
        ObjCRuntime.msgSend(null, ptr, sel, url1, options1, url2, options2, outError, writer)
    }
    
    open fun prepareForReadingItemsAtURLs_options_writingItemsAtURLs_options_error_byAccessor(readingURLs: MemorySegment, readingOptions: MemorySegment, writingURLs: MemorySegment, writingOptions: MemorySegment, outError: MemorySegment, batchAccessor: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("prepareForReadingItemsAtURLs:options:writingItemsAtURLs:options:error:byAccessor:")
        ObjCRuntime.msgSend(null, ptr, sel, readingURLs, readingOptions, writingURLs, writingOptions, outError, batchAccessor)
    }
    
    open fun itemAtURL_willMoveToURL(oldURL: MemorySegment, newURL: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("itemAtURL:willMoveToURL:")
        ObjCRuntime.msgSend(null, ptr, sel, oldURL, newURL)
    }
    
    open fun itemAtURL_didMoveToURL(oldURL: MemorySegment, newURL: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("itemAtURL:didMoveToURL:")
        ObjCRuntime.msgSend(null, ptr, sel, oldURL, newURL)
    }
    
    open fun itemAtURL_didChangeUbiquityAttributes(url: MemorySegment, attributes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("itemAtURL:didChangeUbiquityAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, url, attributes)
    }
    
    open fun cancel(): Unit {
        val sel = ObjCRuntime.sel("cancel")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property filePresenters
    /** @return NSArray<id<NSFilePresenter>> * */
    open fun filePresenters(): MemorySegment {
        val sel = ObjCRuntime.sel("filePresenters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property purposeIdentifier
    open fun purposeIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("purposeIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPurposeIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPurposeIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun purposeIdentifierAsString(): String = ObjCRuntime.toJavaString(purposeIdentifier())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setPurposeIdentifier(value: String) = setPurposeIdentifier(ObjCRuntime.newNSString(Arena.global(), value))
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _accessArbiter: MemorySegment
    // ivar: _fileReactor: MemorySegment
    // ivar: _purposeID: MemorySegment
    // ivar: _recentFilePresenterURL: MemorySegment
    // ivar: _accessClaimIDOrIDs: MemorySegment
    // ivar: _movedItems: MemorySegment
    // ivar: _isCancelled: Boolean
}

