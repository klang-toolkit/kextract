package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSWindowDelegate
 * Inherits protocols: NSObject
 */
interface NSWindowDelegate {
    // @optional
    fun windowShouldClose(sender: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'windowShouldClose:' not implemented")
    
    // @optional
    fun windowWillReturnFieldEditor_toObject(sender: MemorySegment, client: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillReturnFieldEditor:toObject:' not implemented")
    
    // @optional
    fun windowWillResize_toSize(sender: MemorySegment, frameSize: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillResize:toSize:' not implemented")
    
    // @optional
    fun windowWillUseStandardFrame_defaultFrame(window: MemorySegment, newFrame: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillUseStandardFrame:defaultFrame:' not implemented")
    
    // @optional
    fun windowShouldZoom_toFrame(window: MemorySegment, newFrame: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'windowShouldZoom:toFrame:' not implemented")
    
    // @optional
    fun windowWillReturnUndoManager(window: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillReturnUndoManager:' not implemented")
    
    // @optional
    fun window_willPositionSheet_usingRect(window: MemorySegment, sheet: MemorySegment, rect: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'window:willPositionSheet:usingRect:' not implemented")
    
    // @optional
    fun window_shouldPopUpDocumentPathMenu(window: MemorySegment, menu: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'window:shouldPopUpDocumentPathMenu:' not implemented")
    
    // @optional
    fun window_shouldDragDocumentWithEvent_from_withPasteboard(window: MemorySegment, event: MemorySegment, dragImageLocation: MemorySegment, pasteboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'window:shouldDragDocumentWithEvent:from:withPasteboard:' not implemented")
    
    // @optional
    fun window_willUseFullScreenContentSize(window: MemorySegment, proposedSize: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'window:willUseFullScreenContentSize:' not implemented")
    
    // @optional
    fun window_willUseFullScreenPresentationOptions(window: MemorySegment, proposedOptions: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'window:willUseFullScreenPresentationOptions:' not implemented")
    
    /** @return NSArray<NSWindow *> * */
    // @optional
    fun customWindowsToEnterFullScreenForWindow(window: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'customWindowsToEnterFullScreenForWindow:' not implemented")
    
    // @optional
    fun window_startCustomAnimationToEnterFullScreenWithDuration(window: MemorySegment, duration: Double): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'window:startCustomAnimationToEnterFullScreenWithDuration:' not implemented")
    
    // @optional
    fun windowDidFailToEnterFullScreen(window: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidFailToEnterFullScreen:' not implemented")
    
    /** @return NSArray<NSWindow *> * */
    // @optional
    fun customWindowsToExitFullScreenForWindow(window: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'customWindowsToExitFullScreenForWindow:' not implemented")
    
    // @optional
    fun window_startCustomAnimationToExitFullScreenWithDuration(window: MemorySegment, duration: Double): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'window:startCustomAnimationToExitFullScreenWithDuration:' not implemented")
    
    /** @return NSArray<NSWindow *> * */
    // @optional
    fun customWindowsToEnterFullScreenForWindow_onScreen(window: MemorySegment, screen: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'customWindowsToEnterFullScreenForWindow:onScreen:' not implemented")
    
    // @optional
    fun window_startCustomAnimationToEnterFullScreenOnScreen_withDuration(window: MemorySegment, screen: MemorySegment, duration: Double): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'window:startCustomAnimationToEnterFullScreenOnScreen:withDuration:' not implemented")
    
    // @optional
    fun windowDidFailToExitFullScreen(window: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidFailToExitFullScreen:' not implemented")
    
    // @optional
    fun window_willResizeForVersionBrowserWithMaxPreferredSize_maxAllowedSize(window: MemorySegment, maxPreferredFrameSize: MemorySegment, maxAllowedFrameSize: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'window:willResizeForVersionBrowserWithMaxPreferredSize:maxAllowedSize:' not implemented")
    
    // @optional
    fun window_willEncodeRestorableState(window: MemorySegment, state: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'window:willEncodeRestorableState:' not implemented")
    
    // @optional
    fun window_didDecodeRestorableState(window: MemorySegment, state: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'window:didDecodeRestorableState:' not implemented")
    
    /** @return NSArray<id<NSPreviewRepresentableActivityItem>> * */
    // @optional
    fun previewRepresentableActivityItemsForWindow(window: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'previewRepresentableActivityItemsForWindow:' not implemented")
    
    // @optional
    fun windowForSharingRequestFromWindow(window: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'windowForSharingRequestFromWindow:' not implemented")
    
    // @optional
    fun windowDidResize(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidResize:' not implemented")
    
    // @optional
    fun windowDidExpose(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidExpose:' not implemented")
    
    // @optional
    fun windowWillMove(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillMove:' not implemented")
    
    // @optional
    fun windowDidMove(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidMove:' not implemented")
    
    // @optional
    fun windowDidBecomeKey(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidBecomeKey:' not implemented")
    
    // @optional
    fun windowDidResignKey(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidResignKey:' not implemented")
    
    // @optional
    fun windowDidBecomeMain(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidBecomeMain:' not implemented")
    
    // @optional
    fun windowDidResignMain(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidResignMain:' not implemented")
    
    // @optional
    fun windowWillClose(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillClose:' not implemented")
    
    // @optional
    fun windowWillMiniaturize(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillMiniaturize:' not implemented")
    
    // @optional
    fun windowDidMiniaturize(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidMiniaturize:' not implemented")
    
    // @optional
    fun windowDidDeminiaturize(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidDeminiaturize:' not implemented")
    
    // @optional
    fun windowDidUpdate(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidUpdate:' not implemented")
    
    // @optional
    fun windowDidChangeScreen(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidChangeScreen:' not implemented")
    
    // @optional
    fun windowDidChangeScreenProfile(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidChangeScreenProfile:' not implemented")
    
    // @optional
    fun windowDidChangeBackingProperties(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidChangeBackingProperties:' not implemented")
    
    // @optional
    fun windowWillBeginSheet(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillBeginSheet:' not implemented")
    
    // @optional
    fun windowDidEndSheet(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidEndSheet:' not implemented")
    
    // @optional
    fun windowWillStartLiveResize(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillStartLiveResize:' not implemented")
    
    // @optional
    fun windowDidEndLiveResize(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidEndLiveResize:' not implemented")
    
    // @optional
    fun windowWillEnterFullScreen(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillEnterFullScreen:' not implemented")
    
    // @optional
    fun windowDidEnterFullScreen(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidEnterFullScreen:' not implemented")
    
    // @optional
    fun windowWillExitFullScreen(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillExitFullScreen:' not implemented")
    
    // @optional
    fun windowDidExitFullScreen(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidExitFullScreen:' not implemented")
    
    // @optional
    fun windowWillEnterVersionBrowser(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillEnterVersionBrowser:' not implemented")
    
    // @optional
    fun windowDidEnterVersionBrowser(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidEnterVersionBrowser:' not implemented")
    
    // @optional
    fun windowWillExitVersionBrowser(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowWillExitVersionBrowser:' not implemented")
    
    // @optional
    fun windowDidExitVersionBrowser(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidExitVersionBrowser:' not implemented")
    
    // @optional
    fun windowDidChangeOcclusionState(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'windowDidChangeOcclusionState:' not implemented")
    
}

