package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDocumentController
 * Superclass: NSObject
 * Protocols: NSCoding, NSMenuItemValidation, NSUserInterfaceValidations
 */
open class NSDocumentController(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDocumentController") }
        
        fun sharedDocumentController(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedDocumentController")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun documentForURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("documentForURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun documentForWindow(window: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("documentForWindow:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, window) as MemorySegment
    }
    
    open fun addDocument(document: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, document)
    }
    
    open fun removeDocument(document: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, document)
    }
    
    open fun newDocument(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("newDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun openUntitledDocumentAndDisplay_error(displayDocument: Boolean, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("openUntitledDocumentAndDisplay:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, displayDocument, outError) as MemorySegment
    }
    
    open fun makeUntitledDocumentOfType_error(typeName: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("makeUntitledDocumentOfType:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, typeName, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun makeUntitledDocumentOfType_error(typeName: String, outError: MemorySegment): MemorySegment = makeUntitledDocumentOfType_error(ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun openDocument(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("openDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    /** @return NSArray<NSURL *> * */
    open fun URLsFromRunningOpenPanel(): MemorySegment {
        val sel = ObjCRuntime.sel("URLsFromRunningOpenPanel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun runModalOpenPanel_forTypes(openPanel: MemorySegment, types: MemorySegment): Long {
        val sel = ObjCRuntime.sel("runModalOpenPanel:forTypes:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, openPanel, types) as Long
    }
    
    open fun beginOpenPanelWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginOpenPanelWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun beginOpenPanel_forTypes_completionHandler(openPanel: MemorySegment, inTypes: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginOpenPanel:forTypes:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, openPanel, inTypes, completionHandler)
    }
    
    open fun openDocumentWithContentsOfURL_display_completionHandler(url: MemorySegment, displayDocument: Boolean, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("openDocumentWithContentsOfURL:display:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, url, displayDocument, completionHandler)
    }
    
    open fun makeDocumentWithContentsOfURL_ofType_error(url: MemorySegment, typeName: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("makeDocumentWithContentsOfURL:ofType:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, typeName, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun makeDocumentWithContentsOfURL_ofType_error(url: MemorySegment, typeName: String, outError: MemorySegment): MemorySegment = makeDocumentWithContentsOfURL_ofType_error(url, ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun reopenDocumentForURL_withContentsOfURL_display_completionHandler(urlOrNil: MemorySegment, contentsURL: MemorySegment, displayDocument: Boolean, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reopenDocumentForURL:withContentsOfURL:display:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, urlOrNil, contentsURL, displayDocument, completionHandler)
    }
    
    open fun makeDocumentForURL_withContentsOfURL_ofType_error(urlOrNil: MemorySegment, contentsURL: MemorySegment, typeName: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("makeDocumentForURL:withContentsOfURL:ofType:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, urlOrNil, contentsURL, typeName, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun makeDocumentForURL_withContentsOfURL_ofType_error(urlOrNil: MemorySegment, contentsURL: MemorySegment, typeName: String, outError: MemorySegment): MemorySegment = makeDocumentForURL_withContentsOfURL_ofType_error(urlOrNil, contentsURL, ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun saveAllDocuments(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("saveAllDocuments:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun reviewUnsavedDocumentsWithAlertTitle_cancellable_delegate_didReviewAllSelector_contextInfo(title: MemorySegment, cancellable: Boolean, delegate: MemorySegment, didReviewAllSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reviewUnsavedDocumentsWithAlertTitle:cancellable:delegate:didReviewAllSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, title, cancellable, delegate, didReviewAllSelector, contextInfo)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun reviewUnsavedDocumentsWithAlertTitle_cancellable_delegate_didReviewAllSelector_contextInfo(title: String, cancellable: Boolean, delegate: MemorySegment, didReviewAllSelector: MemorySegment, contextInfo: MemorySegment): Unit = reviewUnsavedDocumentsWithAlertTitle_cancellable_delegate_didReviewAllSelector_contextInfo(ObjCRuntime.newNSString(Arena.global(), title), cancellable, delegate, didReviewAllSelector, contextInfo)
    
    open fun closeAllDocumentsWithDelegate_didCloseAllSelector_contextInfo(delegate: MemorySegment, didCloseAllSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("closeAllDocumentsWithDelegate:didCloseAllSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, delegate, didCloseAllSelector, contextInfo)
    }
    
    open fun duplicateDocumentWithContentsOfURL_copying_displayName_error(url: MemorySegment, duplicateByCopying: Boolean, displayNameOrNil: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("duplicateDocumentWithContentsOfURL:copying:displayName:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, duplicateByCopying, displayNameOrNil, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun duplicateDocumentWithContentsOfURL_copying_displayName_error(url: MemorySegment, duplicateByCopying: Boolean, displayNameOrNil: String, outError: MemorySegment): MemorySegment = duplicateDocumentWithContentsOfURL_copying_displayName_error(url, duplicateByCopying, ObjCRuntime.newNSString(Arena.global(), displayNameOrNil), outError)
    
    open fun standardShareMenuItem(): MemorySegment {
        val sel = ObjCRuntime.sel("standardShareMenuItem")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun presentError_modalForWindow_delegate_didPresentSelector_contextInfo(error: MemorySegment, window: MemorySegment, delegate: MemorySegment, didPresentSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("presentError:modalForWindow:delegate:didPresentSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, error, window, delegate, didPresentSelector, contextInfo)
    }
    
    open fun presentError(error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("presentError:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, error) as Boolean
    }
    
    open fun willPresentError(error: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("willPresentError:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, error) as MemorySegment
    }
    
    open fun clearRecentDocuments(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("clearRecentDocuments:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun noteNewRecentDocument(document: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("noteNewRecentDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, document)
    }
    
    open fun noteNewRecentDocumentURL(url: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("noteNewRecentDocumentURL:")
        ObjCRuntime.msgSend(null, ptr, sel, url)
    }
    
    open fun typeForContentsOfURL_error(url: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("typeForContentsOfURL:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, outError) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun typeForContentsOfURL_errorAsString(url: MemorySegment, outError: MemorySegment): String = ObjCRuntime.toJavaString(typeForContentsOfURL_error(url, outError))
    
    open fun documentClassForType(typeName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("documentClassForType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, typeName) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun documentClassForType(typeName: String): MemorySegment = documentClassForType(ObjCRuntime.newNSString(Arena.global(), typeName))
    
    open fun displayNameForType(typeName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("displayNameForType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, typeName) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun displayNameForTypeAsString(typeName: MemorySegment): String = ObjCRuntime.toJavaString(displayNameForType(typeName))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun displayNameForType(typeName: String): MemorySegment = displayNameForType(ObjCRuntime.newNSString(Arena.global(), typeName))
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun displayNameForTypeAsString(typeName: String): String = ObjCRuntime.toJavaString(displayNameForType(ObjCRuntime.newNSString(Arena.global(), typeName)))
    
    open fun validateUserInterfaceItem(item: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("validateUserInterfaceItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, item) as Boolean
    }
    
    // @property sharedDocumentController
    open fun sharedDocumentController(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedDocumentController")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property documents
    /** @return NSArray<__kindof NSDocument *> * */
    open fun documents(): MemorySegment {
        val sel = ObjCRuntime.sel("documents")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property currentDocument
    open fun currentDocument(): MemorySegment {
        val sel = ObjCRuntime.sel("currentDocument")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property currentDirectory
    open fun currentDirectory(): MemorySegment {
        val sel = ObjCRuntime.sel("currentDirectory")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun currentDirectoryAsString(): String = ObjCRuntime.toJavaString(currentDirectory())
    
    // @property autosavingDelay
    open fun autosavingDelay(): Double {
        val sel = ObjCRuntime.sel("autosavingDelay")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setAutosavingDelay(value: Double) {
        val sel = ObjCRuntime.sel("setAutosavingDelay:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasEditedDocuments
    open fun hasEditedDocuments(): Boolean {
        val sel = ObjCRuntime.sel("hasEditedDocuments")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property allowsAutomaticShareMenu
    open fun allowsAutomaticShareMenu(): Boolean {
        val sel = ObjCRuntime.sel("allowsAutomaticShareMenu")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property maximumRecentDocumentCount
    open fun maximumRecentDocumentCount(): Long {
        val sel = ObjCRuntime.sel("maximumRecentDocumentCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property recentDocumentURLs
    /** @return NSArray<NSURL *> * */
    open fun recentDocumentURLs(): MemorySegment {
        val sel = ObjCRuntime.sel("recentDocumentURLs")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property defaultType
    open fun defaultType(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun defaultTypeAsString(): String = ObjCRuntime.toJavaString(defaultType())
    
    // @property documentClassNames
    /** @return NSArray<NSString *> * */
    open fun documentClassNames(): MemorySegment {
        val sel = ObjCRuntime.sel("documentClassNames")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSDeprecated on NSDocumentController ─────────────────────────────────────────

fun NSDocumentController.openDocumentWithContentsOfURL_display_error(url: MemorySegment, displayDocument: Boolean, outError: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("openDocumentWithContentsOfURL:display:error:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, displayDocument, outError) as MemorySegment
}

fun NSDocumentController.reopenDocumentForURL_withContentsOfURL_error(url: MemorySegment, contentsURL: MemorySegment, outError: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("reopenDocumentForURL:withContentsOfURL:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, contentsURL, outError) as Boolean
}

fun NSDocumentController.fileExtensionsFromType(typeName: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileExtensionsFromType:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, typeName) as MemorySegment
}

fun NSDocumentController.typeFromFileExtension(fileNameExtensionOrHFSFileType: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("typeFromFileExtension:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, fileNameExtensionOrHFSFileType) as MemorySegment
}

fun NSDocumentController.documentForFileName(fileName: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("documentForFileName:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, fileName) as MemorySegment
}

fun NSDocumentController.fileNamesFromRunningOpenPanel(): MemorySegment {
    val sel = ObjCRuntime.sel("fileNamesFromRunningOpenPanel")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSDocumentController.makeDocumentWithContentsOfFile_ofType(fileName: MemorySegment, type: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("makeDocumentWithContentsOfFile:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, fileName, type) as MemorySegment
}

fun NSDocumentController.makeDocumentWithContentsOfURL_ofType(url: MemorySegment, type: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("makeDocumentWithContentsOfURL:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, type) as MemorySegment
}

fun NSDocumentController.makeUntitledDocumentOfType(type: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("makeUntitledDocumentOfType:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, type) as MemorySegment
}

fun NSDocumentController.openDocumentWithContentsOfFile_display(fileName: MemorySegment, display: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("openDocumentWithContentsOfFile:display:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, fileName, display) as MemorySegment
}

fun NSDocumentController.openDocumentWithContentsOfURL_display(url: MemorySegment, display: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("openDocumentWithContentsOfURL:display:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, display) as MemorySegment
}

fun NSDocumentController.openUntitledDocumentOfType_display(type: MemorySegment, display: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("openUntitledDocumentOfType:display:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, type, display) as MemorySegment
}

fun NSDocumentController.setShouldCreateUI(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setShouldCreateUI:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSDocumentController.shouldCreateUI(): Boolean {
    val sel = ObjCRuntime.sel("shouldCreateUI")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSWindowRestoration on NSDocumentController ─────────────────────────────────────────

