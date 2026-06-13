package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDocument
 * Superclass: NSObject
 * Protocols: NSEditorRegistration, NSFilePresenter, NSMenuItemValidation, NSUserInterfaceValidations
 */
open class NSDocument(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDocument") }
        
        fun canConcurrentlyReadDocumentsOfType(typeName: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("canConcurrentlyReadDocumentsOfType:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, typeName) as Boolean
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun canConcurrentlyReadDocumentsOfType(typeName: String): Boolean = canConcurrentlyReadDocumentsOfType(ObjCRuntime.newNSString(Arena.global(), typeName))
        
        fun isNativeType(type: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("isNativeType:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, type) as Boolean
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun isNativeType(type: String): Boolean = isNativeType(ObjCRuntime.newNSString(Arena.global(), type))
        
        fun autosavesInPlace(): Boolean {
            val sel = ObjCRuntime.sel("autosavesInPlace")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun preservesVersions(): Boolean {
            val sel = ObjCRuntime.sel("preservesVersions")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun autosavesDrafts(): Boolean {
            val sel = ObjCRuntime.sel("autosavesDrafts")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        /** @return NSArray<NSString *> * */
        fun readableTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("readableTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSString *> * */
        fun writableTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("writableTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun usesUbiquitousStorage(): Boolean {
            val sel = ObjCRuntime.sel("usesUbiquitousStorage")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithType_error(typeName: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithType:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, typeName, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithType_error(typeName: String, outError: MemorySegment): MemorySegment = initWithType_error(ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun initWithContentsOfURL_ofType_error(url: MemorySegment, typeName: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentsOfURL:ofType:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, typeName, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithContentsOfURL_ofType_error(url: MemorySegment, typeName: String, outError: MemorySegment): MemorySegment = initWithContentsOfURL_ofType_error(url, ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun initForURL_withContentsOfURL_ofType_error(urlOrNil: MemorySegment, contentsURL: MemorySegment, typeName: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initForURL:withContentsOfURL:ofType:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, urlOrNil, contentsURL, typeName, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initForURL_withContentsOfURL_ofType_error(urlOrNil: MemorySegment, contentsURL: MemorySegment, typeName: String, outError: MemorySegment): MemorySegment = initForURL_withContentsOfURL_ofType_error(urlOrNil, contentsURL, ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun performActivityWithSynchronousWaiting_usingBlock(waitSynchronously: Boolean, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performActivityWithSynchronousWaiting:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, waitSynchronously, block)
    }
    
    open fun continueActivityUsingBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("continueActivityUsingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun continueAsynchronousWorkOnMainThreadUsingBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("continueAsynchronousWorkOnMainThreadUsingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun performSynchronousFileAccessUsingBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performSynchronousFileAccessUsingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun performAsynchronousFileAccessUsingBlock(block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performAsynchronousFileAccessUsingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, block)
    }
    
    open fun revertDocumentToSaved(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("revertDocumentToSaved:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun revertToContentsOfURL_ofType_error(url: MemorySegment, typeName: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("revertToContentsOfURL:ofType:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url, typeName, outError) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun revertToContentsOfURL_ofType_error(url: MemorySegment, typeName: String, outError: MemorySegment): Boolean = revertToContentsOfURL_ofType_error(url, ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun readFromURL_ofType_error(url: MemorySegment, typeName: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("readFromURL:ofType:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url, typeName, outError) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun readFromURL_ofType_error(url: MemorySegment, typeName: String, outError: MemorySegment): Boolean = readFromURL_ofType_error(url, ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun readFromFileWrapper_ofType_error(fileWrapper: MemorySegment, typeName: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("readFromFileWrapper:ofType:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, fileWrapper, typeName, outError) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun readFromFileWrapper_ofType_error(fileWrapper: MemorySegment, typeName: String, outError: MemorySegment): Boolean = readFromFileWrapper_ofType_error(fileWrapper, ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun readFromData_ofType_error(`data`: MemorySegment, typeName: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("readFromData:ofType:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, `data`, typeName, outError) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun readFromData_ofType_error(`data`: MemorySegment, typeName: String, outError: MemorySegment): Boolean = readFromData_ofType_error(`data`, ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun writeToURL_ofType_error(url: MemorySegment, typeName: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("writeToURL:ofType:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url, typeName, outError) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun writeToURL_ofType_error(url: MemorySegment, typeName: String, outError: MemorySegment): Boolean = writeToURL_ofType_error(url, ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun fileWrapperOfType_error(typeName: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("fileWrapperOfType:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, typeName, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun fileWrapperOfType_error(typeName: String, outError: MemorySegment): MemorySegment = fileWrapperOfType_error(ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun dataOfType_error(typeName: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dataOfType:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, typeName, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun dataOfType_error(typeName: String, outError: MemorySegment): MemorySegment = dataOfType_error(ObjCRuntime.newNSString(Arena.global(), typeName), outError)
    
    open fun unblockUserInteraction(): Unit {
        val sel = ObjCRuntime.sel("unblockUserInteraction")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun writeSafelyToURL_ofType_forSaveOperation_error(url: MemorySegment, typeName: MemorySegment, saveOperation: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("writeSafelyToURL:ofType:forSaveOperation:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url, typeName, saveOperation, outError) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun writeSafelyToURL_ofType_forSaveOperation_error(url: MemorySegment, typeName: String, saveOperation: MemorySegment, outError: MemorySegment): Boolean = writeSafelyToURL_ofType_forSaveOperation_error(url, ObjCRuntime.newNSString(Arena.global(), typeName), saveOperation, outError)
    
    open fun writeToURL_ofType_forSaveOperation_originalContentsURL_error(url: MemorySegment, typeName: MemorySegment, saveOperation: MemorySegment, absoluteOriginalContentsURL: MemorySegment, outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("writeToURL:ofType:forSaveOperation:originalContentsURL:error:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url, typeName, saveOperation, absoluteOriginalContentsURL, outError) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun writeToURL_ofType_forSaveOperation_originalContentsURL_error(url: MemorySegment, typeName: String, saveOperation: MemorySegment, absoluteOriginalContentsURL: MemorySegment, outError: MemorySegment): Boolean = writeToURL_ofType_forSaveOperation_originalContentsURL_error(url, ObjCRuntime.newNSString(Arena.global(), typeName), saveOperation, absoluteOriginalContentsURL, outError)
    
    /** @return NSDictionary<NSString *,id> * */
    open fun fileAttributesToWriteToURL_ofType_forSaveOperation_originalContentsURL_error(url: MemorySegment, typeName: MemorySegment, saveOperation: MemorySegment, absoluteOriginalContentsURL: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("fileAttributesToWriteToURL:ofType:forSaveOperation:originalContentsURL:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, typeName, saveOperation, absoluteOriginalContentsURL, outError) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun fileAttributesToWriteToURL_ofType_forSaveOperation_originalContentsURL_error(url: MemorySegment, typeName: String, saveOperation: MemorySegment, absoluteOriginalContentsURL: MemorySegment, outError: MemorySegment): MemorySegment = fileAttributesToWriteToURL_ofType_forSaveOperation_originalContentsURL_error(url, ObjCRuntime.newNSString(Arena.global(), typeName), saveOperation, absoluteOriginalContentsURL, outError)
    
    open fun saveDocument(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("saveDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun saveDocumentAs(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("saveDocumentAs:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun saveDocumentTo(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("saveDocumentTo:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun saveDocumentWithDelegate_didSaveSelector_contextInfo(delegate: MemorySegment, didSaveSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("saveDocumentWithDelegate:didSaveSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, delegate, didSaveSelector, contextInfo)
    }
    
    open fun runModalSavePanelForSaveOperation_delegate_didSaveSelector_contextInfo(saveOperation: MemorySegment, delegate: MemorySegment, didSaveSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("runModalSavePanelForSaveOperation:delegate:didSaveSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, saveOperation, delegate, didSaveSelector, contextInfo)
    }
    
    open fun prepareSavePanel(savePanel: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("prepareSavePanel:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, savePanel) as Boolean
    }
    
    open fun saveToURL_ofType_forSaveOperation_delegate_didSaveSelector_contextInfo(url: MemorySegment, typeName: MemorySegment, saveOperation: MemorySegment, delegate: MemorySegment, didSaveSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("saveToURL:ofType:forSaveOperation:delegate:didSaveSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, url, typeName, saveOperation, delegate, didSaveSelector, contextInfo)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun saveToURL_ofType_forSaveOperation_delegate_didSaveSelector_contextInfo(url: MemorySegment, typeName: String, saveOperation: MemorySegment, delegate: MemorySegment, didSaveSelector: MemorySegment, contextInfo: MemorySegment): Unit = saveToURL_ofType_forSaveOperation_delegate_didSaveSelector_contextInfo(url, ObjCRuntime.newNSString(Arena.global(), typeName), saveOperation, delegate, didSaveSelector, contextInfo)
    
    open fun saveToURL_ofType_forSaveOperation_completionHandler(url: MemorySegment, typeName: MemorySegment, saveOperation: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("saveToURL:ofType:forSaveOperation:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, url, typeName, saveOperation, completionHandler)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun saveToURL_ofType_forSaveOperation_completionHandler(url: MemorySegment, typeName: String, saveOperation: MemorySegment, completionHandler: MemorySegment): Unit = saveToURL_ofType_forSaveOperation_completionHandler(url, ObjCRuntime.newNSString(Arena.global(), typeName), saveOperation, completionHandler)
    
    open fun canAsynchronouslyWriteToURL_ofType_forSaveOperation(url: MemorySegment, typeName: MemorySegment, saveOperation: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("canAsynchronouslyWriteToURL:ofType:forSaveOperation:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, url, typeName, saveOperation) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun canAsynchronouslyWriteToURL_ofType_forSaveOperation(url: MemorySegment, typeName: String, saveOperation: MemorySegment): Boolean = canAsynchronouslyWriteToURL_ofType_forSaveOperation(url, ObjCRuntime.newNSString(Arena.global(), typeName), saveOperation)
    
    open fun checkAutosavingSafetyAndReturnError(outError: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("checkAutosavingSafetyAndReturnError:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, outError) as Boolean
    }
    
    open fun scheduleAutosaving(): Unit {
        val sel = ObjCRuntime.sel("scheduleAutosaving")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun autosaveDocumentWithDelegate_didAutosaveSelector_contextInfo(delegate: MemorySegment, didAutosaveSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("autosaveDocumentWithDelegate:didAutosaveSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, delegate, didAutosaveSelector, contextInfo)
    }
    
    open fun autosaveWithImplicitCancellability_completionHandler(autosavingIsImplicitlyCancellable: Boolean, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("autosaveWithImplicitCancellability:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, autosavingIsImplicitlyCancellable, completionHandler)
    }
    
    open fun browseDocumentVersions(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("browseDocumentVersions:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun stopBrowsingVersionsWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("stopBrowsingVersionsWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun canCloseDocumentWithDelegate_shouldCloseSelector_contextInfo(delegate: MemorySegment, shouldCloseSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("canCloseDocumentWithDelegate:shouldCloseSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, delegate, shouldCloseSelector, contextInfo)
    }
    
    open fun close(): Unit {
        val sel = ObjCRuntime.sel("close")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun duplicateDocument(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("duplicateDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun duplicateDocumentWithDelegate_didDuplicateSelector_contextInfo(delegate: MemorySegment, didDuplicateSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("duplicateDocumentWithDelegate:didDuplicateSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, delegate, didDuplicateSelector, contextInfo)
    }
    
    open fun duplicateAndReturnError(outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("duplicateAndReturnError:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, outError) as MemorySegment
    }
    
    open fun renameDocument(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("renameDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun moveDocumentToUbiquityContainer(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveDocumentToUbiquityContainer:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun moveDocument(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun moveDocumentWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveDocumentWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun moveToURL_completionHandler(url: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("moveToURL:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, url, completionHandler)
    }
    
    open fun lockDocument(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("lockDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun unlockDocument(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unlockDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun lockDocumentWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("lockDocumentWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun lockWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("lockWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun unlockDocumentWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unlockDocumentWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun unlockWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unlockWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun runPageLayout(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("runPageLayout:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun runModalPageLayoutWithPrintInfo_delegate_didRunSelector_contextInfo(printInfo: MemorySegment, delegate: MemorySegment, didRunSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("runModalPageLayoutWithPrintInfo:delegate:didRunSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, printInfo, delegate, didRunSelector, contextInfo)
    }
    
    open fun preparePageLayout(pageLayout: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("preparePageLayout:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, pageLayout) as Boolean
    }
    
    open fun shouldChangePrintInfo(newPrintInfo: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("shouldChangePrintInfo:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, newPrintInfo) as Boolean
    }
    
    open fun printDocument(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("printDocument:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun printDocumentWithSettings_showPrintPanel_delegate_didPrintSelector_contextInfo(printSettings: MemorySegment, showPrintPanel: Boolean, delegate: MemorySegment, didPrintSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("printDocumentWithSettings:showPrintPanel:delegate:didPrintSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, printSettings, showPrintPanel, delegate, didPrintSelector, contextInfo)
    }
    
    open fun printOperationWithSettings_error(printSettings: MemorySegment, outError: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("printOperationWithSettings:error:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, printSettings, outError) as MemorySegment
    }
    
    open fun runModalPrintOperation_delegate_didRunSelector_contextInfo(printOperation: MemorySegment, delegate: MemorySegment, didRunSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("runModalPrintOperation:delegate:didRunSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, printOperation, delegate, didRunSelector, contextInfo)
    }
    
    open fun saveDocumentToPDF(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("saveDocumentToPDF:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun shareDocumentWithSharingService_completionHandler(sharingService: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("shareDocumentWithSharingService:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, sharingService, completionHandler)
    }
    
    open fun prepareSharingServicePicker(sharingServicePicker: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("prepareSharingServicePicker:")
        ObjCRuntime.msgSend(null, ptr, sel, sharingServicePicker)
    }
    
    open fun updateChangeCount(change: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("updateChangeCount:")
        ObjCRuntime.msgSend(null, ptr, sel, change)
    }
    
    open fun changeCountTokenForSaveOperation(saveOperation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("changeCountTokenForSaveOperation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, saveOperation) as MemorySegment
    }
    
    open fun updateChangeCountWithToken_forSaveOperation(changeCountToken: MemorySegment, saveOperation: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("updateChangeCountWithToken:forSaveOperation:")
        ObjCRuntime.msgSend(null, ptr, sel, changeCountToken, saveOperation)
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
    
    open fun willNotPresentError(error: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("willNotPresentError:")
        ObjCRuntime.msgSend(null, ptr, sel, error)
    }
    
    open fun makeWindowControllers(): Unit {
        val sel = ObjCRuntime.sel("makeWindowControllers")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun windowControllerWillLoadNib(windowController: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("windowControllerWillLoadNib:")
        ObjCRuntime.msgSend(null, ptr, sel, windowController)
    }
    
    open fun windowControllerDidLoadNib(windowController: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("windowControllerDidLoadNib:")
        ObjCRuntime.msgSend(null, ptr, sel, windowController)
    }
    
    open fun setWindow(window: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, window)
    }
    
    open fun addWindowController(windowController: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addWindowController:")
        ObjCRuntime.msgSend(null, ptr, sel, windowController)
    }
    
    open fun removeWindowController(windowController: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeWindowController:")
        ObjCRuntime.msgSend(null, ptr, sel, windowController)
    }
    
    open fun showWindows(): Unit {
        val sel = ObjCRuntime.sel("showWindows")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun shouldCloseWindowController_delegate_shouldCloseSelector_contextInfo(windowController: MemorySegment, delegate: MemorySegment, shouldCloseSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("shouldCloseWindowController:delegate:shouldCloseSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, windowController, delegate, shouldCloseSelector, contextInfo)
    }
    
    open fun defaultDraftName(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultDraftName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun defaultDraftNameAsString(): String = ObjCRuntime.toJavaString(defaultDraftName())
    
    /** @return NSArray<NSString *> * */
    open fun writableTypesForSaveOperation(saveOperation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("writableTypesForSaveOperation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, saveOperation) as MemorySegment
    }
    
    open fun fileNameExtensionForType_saveOperation(typeName: MemorySegment, saveOperation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("fileNameExtensionForType:saveOperation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, typeName, saveOperation) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun fileNameExtensionForType_saveOperationAsString(typeName: MemorySegment, saveOperation: MemorySegment): String = ObjCRuntime.toJavaString(fileNameExtensionForType_saveOperation(typeName, saveOperation))
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun fileNameExtensionForType_saveOperation(typeName: String, saveOperation: MemorySegment): MemorySegment = fileNameExtensionForType_saveOperation(ObjCRuntime.newNSString(Arena.global(), typeName), saveOperation)
    
    /** Convenience overload — [String] parameters and [String] return type. */
    fun fileNameExtensionForType_saveOperationAsString(typeName: String, saveOperation: MemorySegment): String = ObjCRuntime.toJavaString(fileNameExtensionForType_saveOperation(ObjCRuntime.newNSString(Arena.global(), typeName), saveOperation))
    
    open fun validateUserInterfaceItem(item: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("validateUserInterfaceItem:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, item) as Boolean
    }
    
    open fun relinquishPresentedItemToReader(reader: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("relinquishPresentedItemToReader:")
        ObjCRuntime.msgSend(null, ptr, sel, reader)
    }
    
    open fun relinquishPresentedItemToWriter(writer: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("relinquishPresentedItemToWriter:")
        ObjCRuntime.msgSend(null, ptr, sel, writer)
    }
    
    open fun savePresentedItemChangesWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("savePresentedItemChangesWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun accommodatePresentedItemDeletionWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("accommodatePresentedItemDeletionWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun presentedItemDidMoveToURL(newURL: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("presentedItemDidMoveToURL:")
        ObjCRuntime.msgSend(null, ptr, sel, newURL)
    }
    
    open fun presentedItemDidChange(): Unit {
        val sel = ObjCRuntime.sel("presentedItemDidChange")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun presentedItemDidChangeUbiquityAttributes(attributes: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("presentedItemDidChangeUbiquityAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, attributes)
    }
    
    open fun presentedItemDidGainVersion(version: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("presentedItemDidGainVersion:")
        ObjCRuntime.msgSend(null, ptr, sel, version)
    }
    
    open fun presentedItemDidLoseVersion(version: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("presentedItemDidLoseVersion:")
        ObjCRuntime.msgSend(null, ptr, sel, version)
    }
    
    open fun presentedItemDidResolveConflictVersion(version: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("presentedItemDidResolveConflictVersion:")
        ObjCRuntime.msgSend(null, ptr, sel, version)
    }
    
    // @property fileType
    open fun fileType(): MemorySegment {
        val sel = ObjCRuntime.sel("fileType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFileType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFileType:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun fileTypeAsString(): String = ObjCRuntime.toJavaString(fileType())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setFileType(value: String) = setFileType(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property fileURL
    open fun fileURL(): MemorySegment {
        val sel = ObjCRuntime.sel("fileURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFileURL(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFileURL:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property fileModificationDate
    open fun fileModificationDate(): MemorySegment {
        val sel = ObjCRuntime.sel("fileModificationDate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFileModificationDate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFileModificationDate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property draft
    open fun isDraft(): Boolean {
        val sel = ObjCRuntime.sel("isDraft")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDraft(value: Boolean) {
        val sel = ObjCRuntime.sel("setDraft:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property entireFileLoaded
    open fun isEntireFileLoaded(): Boolean {
        val sel = ObjCRuntime.sel("isEntireFileLoaded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property autosavingIsImplicitlyCancellable
    open fun autosavingIsImplicitlyCancellable(): Boolean {
        val sel = ObjCRuntime.sel("autosavingIsImplicitlyCancellable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property keepBackupFile
    open fun keepBackupFile(): Boolean {
        val sel = ObjCRuntime.sel("keepBackupFile")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property backupFileURL
    open fun backupFileURL(): MemorySegment {
        val sel = ObjCRuntime.sel("backupFileURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property savePanelShowsFileFormatsControl
    open fun savePanelShowsFileFormatsControl(): Boolean {
        val sel = ObjCRuntime.sel("savePanelShowsFileFormatsControl")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property fileNameExtensionWasHiddenInLastRunSavePanel
    open fun fileNameExtensionWasHiddenInLastRunSavePanel(): Boolean {
        val sel = ObjCRuntime.sel("fileNameExtensionWasHiddenInLastRunSavePanel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property fileTypeFromLastRunSavePanel
    open fun fileTypeFromLastRunSavePanel(): MemorySegment {
        val sel = ObjCRuntime.sel("fileTypeFromLastRunSavePanel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun fileTypeFromLastRunSavePanelAsString(): String = ObjCRuntime.toJavaString(fileTypeFromLastRunSavePanel())
    
    // @property hasUnautosavedChanges
    open fun hasUnautosavedChanges(): Boolean {
        val sel = ObjCRuntime.sel("hasUnautosavedChanges")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property autosavesInPlace
    open fun autosavesInPlace(): Boolean {
        val sel = ObjCRuntime.sel("autosavesInPlace")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property preservesVersions
    open fun preservesVersions(): Boolean {
        val sel = ObjCRuntime.sel("preservesVersions")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property browsingVersions
    open fun isBrowsingVersions(): Boolean {
        val sel = ObjCRuntime.sel("isBrowsingVersions")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property autosavesDrafts
    open fun autosavesDrafts(): Boolean {
        val sel = ObjCRuntime.sel("autosavesDrafts")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property autosavingFileType
    open fun autosavingFileType(): MemorySegment {
        val sel = ObjCRuntime.sel("autosavingFileType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun autosavingFileTypeAsString(): String = ObjCRuntime.toJavaString(autosavingFileType())
    
    // @property autosavedContentsFileURL
    open fun autosavedContentsFileURL(): MemorySegment {
        val sel = ObjCRuntime.sel("autosavedContentsFileURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAutosavedContentsFileURL(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAutosavedContentsFileURL:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property locked
    open fun isLocked(): Boolean {
        val sel = ObjCRuntime.sel("isLocked")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property printInfo
    open fun printInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("printInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPrintInfo(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPrintInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property PDFPrintOperation
    open fun PDFPrintOperation(): MemorySegment {
        val sel = ObjCRuntime.sel("PDFPrintOperation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property allowsDocumentSharing
    open fun allowsDocumentSharing(): Boolean {
        val sel = ObjCRuntime.sel("allowsDocumentSharing")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property previewRepresentableActivityItems
    /** @return NSArray<id<NSPreviewRepresentableActivityItem>> * */
    open fun previewRepresentableActivityItems(): MemorySegment {
        val sel = ObjCRuntime.sel("previewRepresentableActivityItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPreviewRepresentableActivityItems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPreviewRepresentableActivityItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property documentEdited
    open fun isDocumentEdited(): Boolean {
        val sel = ObjCRuntime.sel("isDocumentEdited")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property inViewingMode
    open fun isInViewingMode(): Boolean {
        val sel = ObjCRuntime.sel("isInViewingMode")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property undoManager
    open fun undoManager(): MemorySegment {
        val sel = ObjCRuntime.sel("undoManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUndoManager(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUndoManager:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasUndoManager
    open fun hasUndoManager(): Boolean {
        val sel = ObjCRuntime.sel("hasUndoManager")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHasUndoManager(value: Boolean) {
        val sel = ObjCRuntime.sel("setHasUndoManager:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property windowNibName
    open fun windowNibName(): MemorySegment {
        val sel = ObjCRuntime.sel("windowNibName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property windowControllers
    /** @return NSArray<__kindof NSWindowController *> * */
    open fun windowControllers(): MemorySegment {
        val sel = ObjCRuntime.sel("windowControllers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property displayName
    open fun displayName(): MemorySegment {
        val sel = ObjCRuntime.sel("displayName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDisplayName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDisplayName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun displayNameAsString(): String = ObjCRuntime.toJavaString(displayName())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setDisplayName(value: String) = setDisplayName(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property windowForSheet
    open fun windowForSheet(): MemorySegment {
        val sel = ObjCRuntime.sel("windowForSheet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property readableTypes
    /** @return NSArray<NSString *> * */
    open fun readableTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("readableTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property writableTypes
    /** @return NSArray<NSString *> * */
    open fun writableTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("writableTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property usesUbiquitousStorage
    open fun usesUbiquitousStorage(): Boolean {
        val sel = ObjCRuntime.sel("usesUbiquitousStorage")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property presentedItemURL
    open fun presentedItemURL(): MemorySegment {
        val sel = ObjCRuntime.sel("presentedItemURL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property observedPresentedItemUbiquityAttributes
    /** @return NSSet<NSURLResourceKey> * */
    open fun observedPresentedItemUbiquityAttributes(): MemorySegment {
        val sel = ObjCRuntime.sel("observedPresentedItemUbiquityAttributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSDeprecated on NSDocument ─────────────────────────────────────────

fun NSDocument.saveToURL_ofType_forSaveOperation_error(url: MemorySegment, typeName: MemorySegment, saveOperation: MemorySegment, outError: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("saveToURL:ofType:forSaveOperation:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, typeName, saveOperation, outError) as Boolean
}

fun NSDocument.dataRepresentationOfType(type: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataRepresentationOfType:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, type) as MemorySegment
}

fun NSDocument.fileAttributesToWriteToFile_ofType_saveOperation(fullDocumentPath: MemorySegment, documentTypeName: MemorySegment, saveOperationType: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileAttributesToWriteToFile:ofType:saveOperation:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, fullDocumentPath, documentTypeName, saveOperationType) as MemorySegment
}

fun NSDocument.fileName(): MemorySegment {
    val sel = ObjCRuntime.sel("fileName")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSDocument.fileWrapperRepresentationOfType(type: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("fileWrapperRepresentationOfType:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, type) as MemorySegment
}

fun NSDocument.initWithContentsOfFile_ofType(absolutePath: MemorySegment, typeName: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfFile:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, absolutePath, typeName) as MemorySegment
}

fun NSDocument.initWithContentsOfURL_ofType(url: MemorySegment, typeName: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithContentsOfURL:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, typeName) as MemorySegment
}

fun NSDocument.loadDataRepresentation_ofType(`data`: MemorySegment, type: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("loadDataRepresentation:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, `data`, type) as Boolean
}

fun NSDocument.loadFileWrapperRepresentation_ofType(wrapper: MemorySegment, type: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("loadFileWrapperRepresentation:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, wrapper, type) as Boolean
}

fun NSDocument.printShowingPrintPanel(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("printShowingPrintPanel:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSDocument.readFromFile_ofType(fileName: MemorySegment, type: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("readFromFile:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, fileName, type) as Boolean
}

fun NSDocument.readFromURL_ofType(url: MemorySegment, type: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("readFromURL:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, type) as Boolean
}

fun NSDocument.revertToSavedFromFile_ofType(fileName: MemorySegment, type: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("revertToSavedFromFile:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, fileName, type) as Boolean
}

fun NSDocument.revertToSavedFromURL_ofType(url: MemorySegment, type: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("revertToSavedFromURL:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, type) as Boolean
}

fun NSDocument.runModalPageLayoutWithPrintInfo(printInfo: MemorySegment): Long {
    val sel = ObjCRuntime.sel("runModalPageLayoutWithPrintInfo:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel, printInfo) as Long
}

fun NSDocument.saveToFile_saveOperation_delegate_didSaveSelector_contextInfo(fileName: MemorySegment, saveOperation: MemorySegment, delegate: MemorySegment, didSaveSelector: MemorySegment, contextInfo: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("saveToFile:saveOperation:delegate:didSaveSelector:contextInfo:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fileName, saveOperation, delegate, didSaveSelector, contextInfo)
}

fun NSDocument.setFileName(fileName: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setFileName:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fileName)
}

fun NSDocument.writeToFile_ofType(fileName: MemorySegment, type: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("writeToFile:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, fileName, type) as Boolean
}

fun NSDocument.writeToFile_ofType_originalFile_saveOperation(fullDocumentPath: MemorySegment, documentTypeName: MemorySegment, fullOriginalDocumentPath: MemorySegment, saveOperationType: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("writeToFile:ofType:originalFile:saveOperation:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, fullDocumentPath, documentTypeName, fullOriginalDocumentPath, saveOperationType) as Boolean
}

fun NSDocument.writeToURL_ofType(url: MemorySegment, type: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("writeToURL:ofType:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, url, type) as Boolean
}

fun NSDocument.writeWithBackupToFile_ofType_saveOperation(fullDocumentPath: MemorySegment, documentTypeName: MemorySegment, saveOperationType: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("writeWithBackupToFile:ofType:saveOperation:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, fullDocumentPath, documentTypeName, saveOperationType) as Boolean
}

fun NSDocument.shouldRunSavePanelWithAccessoryView(): Boolean {
    val sel = ObjCRuntime.sel("shouldRunSavePanelWithAccessoryView")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

// ── Category: NSUserActivity on NSDocument ─────────────────────────────────────────

fun NSDocument.updateUserActivityState(activity: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("updateUserActivityState:")
    ObjCRuntime.msgSend(null, this.ptr, sel, activity)
}

fun NSDocument.userActivity(): MemorySegment {
    val sel = ObjCRuntime.sel("userActivity")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSDocument.setUserActivity(userActivity: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setUserActivity:")
    ObjCRuntime.msgSend(null, this.ptr, sel, userActivity)
}

// ── Category: NSScripting on NSDocument ─────────────────────────────────────────

fun NSDocument.handleSaveScriptCommand(command: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("handleSaveScriptCommand:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, command) as MemorySegment
}

fun NSDocument.handleCloseScriptCommand(command: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("handleCloseScriptCommand:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, command) as MemorySegment
}

fun NSDocument.handlePrintScriptCommand(command: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("handlePrintScriptCommand:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, command) as MemorySegment
}

fun NSDocument.lastComponentOfFileName(): MemorySegment {
    val sel = ObjCRuntime.sel("lastComponentOfFileName")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSDocument.setLastComponentOfFileName(lastComponentOfFileName: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setLastComponentOfFileName:")
    ObjCRuntime.msgSend(null, this.ptr, sel, lastComponentOfFileName)
}

fun NSDocument.objectSpecifier(): MemorySegment {
    val sel = ObjCRuntime.sel("objectSpecifier")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSRestorableState on NSDocument ─────────────────────────────────────────

fun NSDocument.restoreDocumentWindowWithIdentifier_state_completionHandler(identifier: MemorySegment, state: MemorySegment, completionHandler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("restoreDocumentWindowWithIdentifier:state:completionHandler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, identifier, state, completionHandler)
}

fun NSDocument.encodeRestorableStateWithCoder(coder: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeRestorableStateWithCoder:")
    ObjCRuntime.msgSend(null, this.ptr, sel, coder)
}

fun NSDocument.encodeRestorableStateWithCoder_backgroundQueue(coder: MemorySegment, queue: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("encodeRestorableStateWithCoder:backgroundQueue:")
    ObjCRuntime.msgSend(null, this.ptr, sel, coder, queue)
}

fun NSDocument.restoreStateWithCoder(coder: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("restoreStateWithCoder:")
    ObjCRuntime.msgSend(null, this.ptr, sel, coder)
}

fun NSDocument.invalidateRestorableState(): Unit {
    val sel = ObjCRuntime.sel("invalidateRestorableState")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

// Class method: +[NSDocument allowedClassesForRestorableStateKeyPath:]
fun NSDocument_allowedClassesForRestorableStateKeyPath(keyPath: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("allowedClassesForRestorableStateKeyPath:")
    val cls = ObjCRuntime.getClass("NSDocument")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, keyPath) as MemorySegment
}

// Class method: +[NSDocument restorableStateKeyPaths]
fun NSDocument_restorableStateKeyPaths(): MemorySegment {
    val sel = ObjCRuntime.sel("restorableStateKeyPaths")
    val cls = ObjCRuntime.getClass("NSDocument")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// @property restorableStateKeyPaths
/** @return NSArray<NSString *> * */
fun NSDocument.restorableStateKeyPaths(): MemorySegment {
    val sel = ObjCRuntime.sel("restorableStateKeyPaths")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

