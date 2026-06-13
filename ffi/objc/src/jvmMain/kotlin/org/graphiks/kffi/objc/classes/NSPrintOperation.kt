package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPrintOperation
 * Superclass: NSObject
 */
open class NSPrintOperation(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPrintOperation") }
        
        fun printOperationWithView_printInfo(view: MemorySegment, printInfo: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("printOperationWithView:printInfo:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, view, printInfo) as MemorySegment
        }
        
        fun PDFOperationWithView_insideRect_toData_printInfo(view: MemorySegment, rect: MemorySegment, `data`: MemorySegment, printInfo: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("PDFOperationWithView:insideRect:toData:printInfo:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, view, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), `data`, printInfo) as MemorySegment
        }
        
        fun PDFOperationWithView_insideRect_toPath_printInfo(view: MemorySegment, rect: MemorySegment, path: MemorySegment, printInfo: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("PDFOperationWithView:insideRect:toPath:printInfo:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, view, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), path, printInfo) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun PDFOperationWithView_insideRect_toPath_printInfo(view: MemorySegment, rect: MemorySegment, path: String, printInfo: MemorySegment): MemorySegment = PDFOperationWithView_insideRect_toPath_printInfo(view, rect, ObjCRuntime.newNSString(Arena.global(), path), printInfo)
        
        fun EPSOperationWithView_insideRect_toData_printInfo(view: MemorySegment, rect: MemorySegment, `data`: MemorySegment, printInfo: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("EPSOperationWithView:insideRect:toData:printInfo:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, view, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), `data`, printInfo) as MemorySegment
        }
        
        fun EPSOperationWithView_insideRect_toPath_printInfo(view: MemorySegment, rect: MemorySegment, path: MemorySegment, printInfo: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("EPSOperationWithView:insideRect:toPath:printInfo:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, view, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), path, printInfo) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun EPSOperationWithView_insideRect_toPath_printInfo(view: MemorySegment, rect: MemorySegment, path: String, printInfo: MemorySegment): MemorySegment = EPSOperationWithView_insideRect_toPath_printInfo(view, rect, ObjCRuntime.newNSString(Arena.global(), path), printInfo)
        
        fun printOperationWithView(view: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("printOperationWithView:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, view) as MemorySegment
        }
        
        fun PDFOperationWithView_insideRect_toData(view: MemorySegment, rect: MemorySegment, `data`: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("PDFOperationWithView:insideRect:toData:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, view, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), `data`) as MemorySegment
        }
        
        fun EPSOperationWithView_insideRect_toData(view: MemorySegment, rect: MemorySegment, `data`: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("EPSOperationWithView:insideRect:toData:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, view, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), `data`) as MemorySegment
        }
        
        fun currentOperation(): MemorySegment {
            val sel = ObjCRuntime.sel("currentOperation")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun setCurrentOperation(currentOperation: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setCurrentOperation:")
            ObjCRuntime.msgSend(null, _class, sel, currentOperation)
        }
        
    }
    
    open fun runOperationModalForWindow_delegate_didRunSelector_contextInfo(docWindow: MemorySegment, delegate: MemorySegment, didRunSelector: MemorySegment, contextInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("runOperationModalForWindow:delegate:didRunSelector:contextInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, docWindow, delegate, didRunSelector, contextInfo)
    }
    
    open fun runOperation(): Boolean {
        val sel = ObjCRuntime.sel("runOperation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun createContext(): MemorySegment {
        val sel = ObjCRuntime.sel("createContext")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun destroyContext(): Unit {
        val sel = ObjCRuntime.sel("destroyContext")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun deliverResult(): Boolean {
        val sel = ObjCRuntime.sel("deliverResult")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun cleanUpOperation(): Unit {
        val sel = ObjCRuntime.sel("cleanUpOperation")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property currentOperation
    open fun currentOperation(): MemorySegment {
        val sel = ObjCRuntime.sel("currentOperation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCurrentOperation(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCurrentOperation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property copyingOperation
    open fun isCopyingOperation(): Boolean {
        val sel = ObjCRuntime.sel("isCopyingOperation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property preferredRenderingQuality
    open fun preferredRenderingQuality(): MemorySegment {
        val sel = ObjCRuntime.sel("preferredRenderingQuality")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property jobTitle
    open fun jobTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("jobTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setJobTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setJobTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun jobTitleAsString(): String = ObjCRuntime.toJavaString(jobTitle())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setJobTitle(value: String) = setJobTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property showsPrintPanel
    open fun showsPrintPanel(): Boolean {
        val sel = ObjCRuntime.sel("showsPrintPanel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsPrintPanel(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsPrintPanel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsProgressPanel
    open fun showsProgressPanel(): Boolean {
        val sel = ObjCRuntime.sel("showsProgressPanel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsProgressPanel(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsProgressPanel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property printPanel
    open fun printPanel(): MemorySegment {
        val sel = ObjCRuntime.sel("printPanel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPrintPanel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPrintPanel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property PDFPanel
    open fun PDFPanel(): MemorySegment {
        val sel = ObjCRuntime.sel("PDFPanel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPDFPanel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPDFPanel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property canSpawnSeparateThread
    open fun canSpawnSeparateThread(): Boolean {
        val sel = ObjCRuntime.sel("canSpawnSeparateThread")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setCanSpawnSeparateThread(value: Boolean) {
        val sel = ObjCRuntime.sel("setCanSpawnSeparateThread:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pageOrder
    open fun pageOrder(): MemorySegment {
        val sel = ObjCRuntime.sel("pageOrder")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPageOrder(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPageOrder:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property view
    open fun view(): MemorySegment {
        val sel = ObjCRuntime.sel("view")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
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
    
    // @property context
    open fun context(): MemorySegment {
        val sel = ObjCRuntime.sel("context")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property pageRange
    open fun pageRange(): MemorySegment {
        val sel = ObjCRuntime.sel("pageRange")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    
    // @property currentPage
    open fun currentPage(): Long {
        val sel = ObjCRuntime.sel("currentPage")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
}

// ── Category: NSDeprecated on NSPrintOperation ─────────────────────────────────────────

fun NSPrintOperation.setAccessoryView(view: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAccessoryView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, view)
}

fun NSPrintOperation.accessoryView(): MemorySegment {
    val sel = ObjCRuntime.sel("accessoryView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSPrintOperation.setJobStyleHint(hint: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setJobStyleHint:")
    ObjCRuntime.msgSend(null, this.ptr, sel, hint)
}

fun NSPrintOperation.jobStyleHint(): MemorySegment {
    val sel = ObjCRuntime.sel("jobStyleHint")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSPrintOperation.setShowPanels(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setShowPanels:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSPrintOperation.showPanels(): Boolean {
    val sel = ObjCRuntime.sel("showPanels")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

