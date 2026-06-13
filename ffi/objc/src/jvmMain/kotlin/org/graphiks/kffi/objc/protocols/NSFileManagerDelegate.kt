package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSFileManagerDelegate
 * Inherits protocols: NSObject
 */
interface NSFileManagerDelegate {
    // @optional
    fun fileManager_shouldCopyItemAtPath_toPath(fileManager: MemorySegment, srcPath: MemorySegment, dstPath: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldCopyItemAtPath:toPath:' not implemented")
    
    // @optional
    fun fileManager_shouldCopyItemAtURL_toURL(fileManager: MemorySegment, srcURL: MemorySegment, dstURL: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldCopyItemAtURL:toURL:' not implemented")
    
    // @optional
    fun fileManager_shouldProceedAfterError_copyingItemAtPath_toPath(fileManager: MemorySegment, error: MemorySegment, srcPath: MemorySegment, dstPath: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldProceedAfterError:copyingItemAtPath:toPath:' not implemented")
    
    // @optional
    fun fileManager_shouldProceedAfterError_copyingItemAtURL_toURL(fileManager: MemorySegment, error: MemorySegment, srcURL: MemorySegment, dstURL: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldProceedAfterError:copyingItemAtURL:toURL:' not implemented")
    
    // @optional
    fun fileManager_shouldMoveItemAtPath_toPath(fileManager: MemorySegment, srcPath: MemorySegment, dstPath: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldMoveItemAtPath:toPath:' not implemented")
    
    // @optional
    fun fileManager_shouldMoveItemAtURL_toURL(fileManager: MemorySegment, srcURL: MemorySegment, dstURL: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldMoveItemAtURL:toURL:' not implemented")
    
    // @optional
    fun fileManager_shouldProceedAfterError_movingItemAtPath_toPath(fileManager: MemorySegment, error: MemorySegment, srcPath: MemorySegment, dstPath: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldProceedAfterError:movingItemAtPath:toPath:' not implemented")
    
    // @optional
    fun fileManager_shouldProceedAfterError_movingItemAtURL_toURL(fileManager: MemorySegment, error: MemorySegment, srcURL: MemorySegment, dstURL: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldProceedAfterError:movingItemAtURL:toURL:' not implemented")
    
    // @optional
    fun fileManager_shouldLinkItemAtPath_toPath(fileManager: MemorySegment, srcPath: MemorySegment, dstPath: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldLinkItemAtPath:toPath:' not implemented")
    
    // @optional
    fun fileManager_shouldLinkItemAtURL_toURL(fileManager: MemorySegment, srcURL: MemorySegment, dstURL: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldLinkItemAtURL:toURL:' not implemented")
    
    // @optional
    fun fileManager_shouldProceedAfterError_linkingItemAtPath_toPath(fileManager: MemorySegment, error: MemorySegment, srcPath: MemorySegment, dstPath: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldProceedAfterError:linkingItemAtPath:toPath:' not implemented")
    
    // @optional
    fun fileManager_shouldProceedAfterError_linkingItemAtURL_toURL(fileManager: MemorySegment, error: MemorySegment, srcURL: MemorySegment, dstURL: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldProceedAfterError:linkingItemAtURL:toURL:' not implemented")
    
    // @optional
    fun fileManager_shouldRemoveItemAtPath(fileManager: MemorySegment, path: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldRemoveItemAtPath:' not implemented")
    
    // @optional
    fun fileManager_shouldRemoveItemAtURL(fileManager: MemorySegment, URL: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldRemoveItemAtURL:' not implemented")
    
    // @optional
    fun fileManager_shouldProceedAfterError_removingItemAtPath(fileManager: MemorySegment, error: MemorySegment, path: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldProceedAfterError:removingItemAtPath:' not implemented")
    
    // @optional
    fun fileManager_shouldProceedAfterError_removingItemAtURL(fileManager: MemorySegment, error: MemorySegment, URL: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'fileManager:shouldProceedAfterError:removingItemAtURL:' not implemented")
    
}

