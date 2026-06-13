package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSApplication
 * Superclass: NSResponder
 * Protocols: NSUserInterfaceValidations, NSMenuItemValidation, NSAccessibilityElement, NSAccessibility
 */
open class NSApplication(override val ptr: MemorySegment) : NSResponder(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSApplication") }
        
        fun detachDrawingThread_toTarget_withObject(selector: MemorySegment, target: MemorySegment, argument: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("detachDrawingThread:toTarget:withObject:")
            ObjCRuntime.msgSend(null, _class, sel, selector, target, argument)
        }
        
        fun sharedApplication(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedApplication")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun hide(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("hide:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun unhide(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unhide:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun unhideWithoutActivation(): Unit {
        val sel = ObjCRuntime.sel("unhideWithoutActivation")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun windowWithWindowNumber(windowNum: Long): MemorySegment {
        val sel = ObjCRuntime.sel("windowWithWindowNumber:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, windowNum) as MemorySegment
    }
    
    open fun deactivate(): Unit {
        val sel = ObjCRuntime.sel("deactivate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun activateIgnoringOtherApps(ignoreOtherApps: Boolean): Unit {
        val sel = ObjCRuntime.sel("activateIgnoringOtherApps:")
        ObjCRuntime.msgSend(null, ptr, sel, ignoreOtherApps)
    }
    
    open fun activate(): Unit {
        val sel = ObjCRuntime.sel("activate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun yieldActivationToApplication(application: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("yieldActivationToApplication:")
        ObjCRuntime.msgSend(null, ptr, sel, application)
    }
    
    open fun yieldActivationToApplicationWithBundleIdentifier(bundleIdentifier: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("yieldActivationToApplicationWithBundleIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, bundleIdentifier)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun yieldActivationToApplicationWithBundleIdentifier(bundleIdentifier: String): Unit = yieldActivationToApplicationWithBundleIdentifier(ObjCRuntime.newNSString(Arena.global(), bundleIdentifier))
    
    open fun hideOtherApplications(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("hideOtherApplications:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun unhideAllApplications(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unhideAllApplications:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun finishLaunching(): Unit {
        val sel = ObjCRuntime.sel("finishLaunching")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun run(): Unit {
        val sel = ObjCRuntime.sel("run")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun runModalForWindow(window: MemorySegment): Long {
        val sel = ObjCRuntime.sel("runModalForWindow:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, window) as Long
    }
    
    open fun stop(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("stop:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun stopModal(): Unit {
        val sel = ObjCRuntime.sel("stopModal")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun stopModalWithCode(returnCode: Long): Unit {
        val sel = ObjCRuntime.sel("stopModalWithCode:")
        ObjCRuntime.msgSend(null, ptr, sel, returnCode)
    }
    
    open fun abortModal(): Unit {
        val sel = ObjCRuntime.sel("abortModal")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun beginModalSessionForWindow(window: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("beginModalSessionForWindow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, window) as MemorySegment
    }
    
    open fun runModalSession(session: MemorySegment): Long {
        val sel = ObjCRuntime.sel("runModalSession:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, session) as Long
    }
    
    open fun endModalSession(session: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("endModalSession:")
        ObjCRuntime.msgSend(null, ptr, sel, session)
    }
    
    open fun terminate(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("terminate:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun requestUserAttention(requestType: MemorySegment): Long {
        val sel = ObjCRuntime.sel("requestUserAttention:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, requestType) as Long
    }
    
    open fun cancelUserAttentionRequest(request: Long): Unit {
        val sel = ObjCRuntime.sel("cancelUserAttentionRequest:")
        ObjCRuntime.msgSend(null, ptr, sel, request)
    }
    
    open fun enumerateWindowsWithOptions_usingBlock(options: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateWindowsWithOptions:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, options, block)
    }
    
    open fun preventWindowOrdering(): Unit {
        val sel = ObjCRuntime.sel("preventWindowOrdering")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setWindowsNeedUpdate(needUpdate: Boolean): Unit {
        val sel = ObjCRuntime.sel("setWindowsNeedUpdate:")
        ObjCRuntime.msgSend(null, ptr, sel, needUpdate)
    }
    
    open fun updateWindows(): Unit {
        val sel = ObjCRuntime.sel("updateWindows")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun activationPolicy(): MemorySegment {
        val sel = ObjCRuntime.sel("activationPolicy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun setActivationPolicy(activationPolicy: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setActivationPolicy:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, activationPolicy) as Boolean
    }
    
    open fun reportException(exception: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reportException:")
        ObjCRuntime.msgSend(null, ptr, sel, exception)
    }
    
    open fun replyToApplicationShouldTerminate(shouldTerminate: Boolean): Unit {
        val sel = ObjCRuntime.sel("replyToApplicationShouldTerminate:")
        ObjCRuntime.msgSend(null, ptr, sel, shouldTerminate)
    }
    
    open fun replyToOpenOrPrint(reply: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replyToOpenOrPrint:")
        ObjCRuntime.msgSend(null, ptr, sel, reply)
    }
    
    open fun orderFrontCharacterPalette(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("orderFrontCharacterPalette:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    // @property sharedApplication
    open fun sharedApplication(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedApplication")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property delegate
    /** @return id<NSApplicationDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property mainWindow
    open fun mainWindow(): MemorySegment {
        val sel = ObjCRuntime.sel("mainWindow")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property keyWindow
    open fun keyWindow(): MemorySegment {
        val sel = ObjCRuntime.sel("keyWindow")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property active
    open fun isActive(): Boolean {
        val sel = ObjCRuntime.sel("isActive")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property hidden
    open fun isHidden(): Boolean {
        val sel = ObjCRuntime.sel("isHidden")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property running
    open fun isRunning(): Boolean {
        val sel = ObjCRuntime.sel("isRunning")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property applicationShouldSuppressHighDynamicRangeContent
    open fun applicationShouldSuppressHighDynamicRangeContent(): Boolean {
        val sel = ObjCRuntime.sel("applicationShouldSuppressHighDynamicRangeContent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property modalWindow
    open fun modalWindow(): MemorySegment {
        val sel = ObjCRuntime.sel("modalWindow")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property windows
    /** @return NSArray<NSWindow *> * */
    open fun windows(): MemorySegment {
        val sel = ObjCRuntime.sel("windows")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property mainMenu
    open fun mainMenu(): MemorySegment {
        val sel = ObjCRuntime.sel("mainMenu")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMainMenu(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMainMenu:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property helpMenu
    open fun helpMenu(): MemorySegment {
        val sel = ObjCRuntime.sel("helpMenu")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHelpMenu(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHelpMenu:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property applicationIconImage
    open fun applicationIconImage(): MemorySegment {
        val sel = ObjCRuntime.sel("applicationIconImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setApplicationIconImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setApplicationIconImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property dockTile
    open fun dockTile(): MemorySegment {
        val sel = ObjCRuntime.sel("dockTile")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property presentationOptions
    open fun presentationOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("presentationOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPresentationOptions(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPresentationOptions:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property currentSystemPresentationOptions
    open fun currentSystemPresentationOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("currentSystemPresentationOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property occlusionState
    open fun occlusionState(): MemorySegment {
        val sel = ObjCRuntime.sel("occlusionState")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property protectedDataAvailable
    open fun isProtectedDataAvailable(): Boolean {
        val sel = ObjCRuntime.sel("isProtectedDataAvailable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSAppearanceCustomization on NSApplication ─────────────────────────────────────────

fun NSApplication.appearance(): MemorySegment {
    val sel = ObjCRuntime.sel("appearance")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSApplication.setAppearance(appearance: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAppearance:")
    ObjCRuntime.msgSend(null, this.ptr, sel, appearance)
}

fun NSApplication.effectiveAppearance(): MemorySegment {
    val sel = ObjCRuntime.sel("effectiveAppearance")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSEvent on NSApplication ─────────────────────────────────────────

fun NSApplication.sendEvent(event: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("sendEvent:")
    ObjCRuntime.msgSend(null, this.ptr, sel, event)
}

fun NSApplication.postEvent_atStart(event: MemorySegment, atStart: Boolean): Unit {
    val sel = ObjCRuntime.sel("postEvent:atStart:")
    ObjCRuntime.msgSend(null, this.ptr, sel, event, atStart)
}

fun NSApplication.nextEventMatchingMask_untilDate_inMode_dequeue(mask: MemorySegment, expiration: MemorySegment, mode: MemorySegment, deqFlag: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("nextEventMatchingMask:untilDate:inMode:dequeue:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, mask, expiration, mode, deqFlag) as MemorySegment
}

fun NSApplication.discardEventsMatchingMask_beforeEvent(mask: MemorySegment, lastEvent: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("discardEventsMatchingMask:beforeEvent:")
    ObjCRuntime.msgSend(null, this.ptr, sel, mask, lastEvent)
}

fun NSApplication.currentEvent(): MemorySegment {
    val sel = ObjCRuntime.sel("currentEvent")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSResponder on NSApplication ─────────────────────────────────────────

fun NSApplication.sendAction_to_from(action: MemorySegment, target: MemorySegment, sender: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("sendAction:to:from:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, action, target, sender) as Boolean
}

fun NSApplication.targetForAction(action: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("targetForAction:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, action) as MemorySegment
}

fun NSApplication.targetForAction_to_from(action: MemorySegment, target: MemorySegment, sender: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("targetForAction:to:from:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, action, target, sender) as MemorySegment
}

fun NSApplication.tryToPerform_with(action: MemorySegment, `object`: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("tryToPerform:with:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, action, `object`) as Boolean
}

fun NSApplication.validRequestorForSendType_returnType(sendType: MemorySegment, returnType: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("validRequestorForSendType:returnType:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, sendType, returnType) as MemorySegment
}

// ── Category: NSWindowsMenu on NSApplication ─────────────────────────────────────────

fun NSApplication.arrangeInFront(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("arrangeInFront:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSApplication.removeWindowsItem(win: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeWindowsItem:")
    ObjCRuntime.msgSend(null, this.ptr, sel, win)
}

fun NSApplication.addWindowsItem_title_filename(win: MemorySegment, string: MemorySegment, isFilename: Boolean): Unit {
    val sel = ObjCRuntime.sel("addWindowsItem:title:filename:")
    ObjCRuntime.msgSend(null, this.ptr, sel, win, string, isFilename)
}

fun NSApplication.changeWindowsItem_title_filename(win: MemorySegment, string: MemorySegment, isFilename: Boolean): Unit {
    val sel = ObjCRuntime.sel("changeWindowsItem:title:filename:")
    ObjCRuntime.msgSend(null, this.ptr, sel, win, string, isFilename)
}

fun NSApplication.updateWindowsItem(win: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("updateWindowsItem:")
    ObjCRuntime.msgSend(null, this.ptr, sel, win)
}

fun NSApplication.miniaturizeAll(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("miniaturizeAll:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSApplication.windowsMenu(): MemorySegment {
    val sel = ObjCRuntime.sel("windowsMenu")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSApplication.setWindowsMenu(windowsMenu: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setWindowsMenu:")
    ObjCRuntime.msgSend(null, this.ptr, sel, windowsMenu)
}

// ── Category: NSFullKeyboardAccess on NSApplication ─────────────────────────────────────────

fun NSApplication.isFullKeyboardAccessEnabled(): Boolean {
    val sel = ObjCRuntime.sel("isFullKeyboardAccessEnabled")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSServicesMenu on NSApplication ─────────────────────────────────────────

fun NSApplication.registerServicesMenuSendTypes_returnTypes(sendTypes: MemorySegment, returnTypes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("registerServicesMenuSendTypes:returnTypes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sendTypes, returnTypes)
}

fun NSApplication.servicesMenu(): MemorySegment {
    val sel = ObjCRuntime.sel("servicesMenu")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSApplication.setServicesMenu(servicesMenu: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setServicesMenu:")
    ObjCRuntime.msgSend(null, this.ptr, sel, servicesMenu)
}

// ── Category: NSServicesHandling on NSApplication ─────────────────────────────────────────

fun NSApplication.servicesProvider(): MemorySegment {
    val sel = ObjCRuntime.sel("servicesProvider")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSApplication.setServicesProvider(servicesProvider: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setServicesProvider:")
    ObjCRuntime.msgSend(null, this.ptr, sel, servicesProvider)
}

// ── Category: NSStandardAboutPanel on NSApplication ─────────────────────────────────────────

fun NSApplication.orderFrontStandardAboutPanel(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("orderFrontStandardAboutPanel:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSApplication.orderFrontStandardAboutPanelWithOptions(optionsDictionary: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("orderFrontStandardAboutPanelWithOptions:")
    ObjCRuntime.msgSend(null, this.ptr, sel, optionsDictionary)
}

// ── Category: NSApplicationLayoutDirection on NSApplication ─────────────────────────────────────────

fun NSApplication.userInterfaceLayoutDirection(): MemorySegment {
    val sel = ObjCRuntime.sel("userInterfaceLayoutDirection")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSRestorableUserInterface on NSApplication ─────────────────────────────────────────

fun NSApplication.disableRelaunchOnLogin(): Unit {
    val sel = ObjCRuntime.sel("disableRelaunchOnLogin")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSApplication.enableRelaunchOnLogin(): Unit {
    val sel = ObjCRuntime.sel("enableRelaunchOnLogin")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

// ── Category: NSRemoteNotifications on NSApplication ─────────────────────────────────────────

fun NSApplication.registerForRemoteNotifications(): Unit {
    val sel = ObjCRuntime.sel("registerForRemoteNotifications")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSApplication.unregisterForRemoteNotifications(): Unit {
    val sel = ObjCRuntime.sel("unregisterForRemoteNotifications")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSApplication.registerForRemoteNotificationTypes(types: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("registerForRemoteNotificationTypes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, types)
}

fun NSApplication.isRegisteredForRemoteNotifications(): Boolean {
    val sel = ObjCRuntime.sel("isRegisteredForRemoteNotifications")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSApplication.enabledRemoteNotificationTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("enabledRemoteNotificationTypes")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSDeprecated on NSApplication ─────────────────────────────────────────

fun NSApplication.runModalForWindow_relativeToWindow(window: MemorySegment, docWindow: MemorySegment): Long {
    val sel = ObjCRuntime.sel("runModalForWindow:relativeToWindow:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, window, docWindow) as Long
}

fun NSApplication.beginModalSessionForWindow_relativeToWindow(window: MemorySegment, docWindow: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("beginModalSessionForWindow:relativeToWindow:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, window, docWindow) as MemorySegment
}

fun NSApplication.application_printFiles(sender: MemorySegment, filenames: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("application:printFiles:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender, filenames)
}

fun NSApplication.beginSheet_modalForWindow_modalDelegate_didEndSelector_contextInfo(sheet: MemorySegment, docWindow: MemorySegment, modalDelegate: MemorySegment, didEndSelector: MemorySegment, contextInfo: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("beginSheet:modalForWindow:modalDelegate:didEndSelector:contextInfo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sheet, docWindow, modalDelegate, didEndSelector, contextInfo)
}

fun NSApplication.endSheet(sheet: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("endSheet:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sheet)
}

fun NSApplication.endSheet_returnCode(sheet: MemorySegment, returnCode: Long): Unit {
    val sel = ObjCRuntime.sel("endSheet:returnCode:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sheet, returnCode)
}

fun NSApplication.makeWindowsPerform_inOrder(selector: MemorySegment, inOrder: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("makeWindowsPerform:inOrder:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, selector, inOrder) as MemorySegment
}

fun NSApplication.context(): MemorySegment {
    val sel = ObjCRuntime.sel("context")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSApplicationHelpExtension on NSApplication ─────────────────────────────────────────

fun NSApplication.activateContextHelpMode(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("activateContextHelpMode:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSApplication.showHelp(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("showHelp:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

// ── Category: NSTouchBarCustomization on NSApplication ─────────────────────────────────────────

fun NSApplication.toggleTouchBarCustomizationPalette(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("toggleTouchBarCustomizationPalette:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

fun NSApplication.isAutomaticCustomizeTouchBarMenuItemEnabled(): Boolean {
    val sel = ObjCRuntime.sel("isAutomaticCustomizeTouchBarMenuItemEnabled")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSApplication.setAutomaticCustomizeTouchBarMenuItemEnabled(automaticCustomizeTouchBarMenuItemEnabled: Boolean): Unit {
    val sel = ObjCRuntime.sel("setAutomaticCustomizeTouchBarMenuItemEnabled:")
    ObjCRuntime.msgSend(null, this.ptr, sel, automaticCustomizeTouchBarMenuItemEnabled)
}

// ── Category: NSColorPanel on NSApplication ─────────────────────────────────────────

fun NSApplication.orderFrontColorPanel(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("orderFrontColorPanel:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

// ── Category: NSPageLayoutPanel on NSApplication ─────────────────────────────────────────

fun NSApplication.runPageLayout(sender: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("runPageLayout:")
    ObjCRuntime.msgSend(null, this.ptr, sel, sender)
}

// ── Category: NSScripting on NSApplication ─────────────────────────────────────────

/** @return NSArray<NSDocument *> * */
fun NSApplication.orderedDocuments(): MemorySegment {
    val sel = ObjCRuntime.sel("orderedDocuments")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<NSWindow *> * */
fun NSApplication.orderedWindows(): MemorySegment {
    val sel = ObjCRuntime.sel("orderedWindows")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSUserInterfaceItemSearching on NSApplication ─────────────────────────────────────────

fun NSApplication.registerUserInterfaceItemSearchHandler(handler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("registerUserInterfaceItemSearchHandler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, handler)
}

fun NSApplication.unregisterUserInterfaceItemSearchHandler(handler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("unregisterUserInterfaceItemSearchHandler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, handler)
}

fun NSApplication.searchString_inUserInterfaceItemString_searchRange_foundRange(searchString: MemorySegment, stringToSearch: MemorySegment, searchRange: MemorySegment, foundRange: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("searchString:inUserInterfaceItemString:searchRange:foundRange:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, searchString, stringToSearch, searchRange, foundRange) as Boolean
}

// ── Category: NSWindowRestoration on NSApplication ─────────────────────────────────────────

fun NSApplication.restoreWindowWithIdentifier_state_completionHandler(identifier: MemorySegment, state: MemorySegment, completionHandler: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("restoreWindowWithIdentifier:state:completionHandler:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, identifier, state, completionHandler) as Boolean
}

// ── Category: NSRestorableStateExtension on NSApplication ─────────────────────────────────────────

fun NSApplication.extendStateRestoration(): Unit {
    val sel = ObjCRuntime.sel("extendStateRestoration")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSApplication.completeStateRestoration(): Unit {
    val sel = ObjCRuntime.sel("completeStateRestoration")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

