package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPrintInfo
 * Superclass: NSObject
 * Protocols: NSCopying, NSCoding
 */
open class NSPrintInfo(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPrintInfo") }
        
        fun sharedPrintInfo(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedPrintInfo")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun setSharedPrintInfo(sharedPrintInfo: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setSharedPrintInfo:")
            ObjCRuntime.msgSend(null, _class, sel, sharedPrintInfo)
        }
        
        fun defaultPrinter(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultPrinter")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithDictionary(attributes: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDictionary:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, attributes) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** @return NSMutableDictionary<NSPrintInfoAttributeKey,id> * */
    open fun dictionary(): MemorySegment {
        val sel = ObjCRuntime.sel("dictionary")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun setUpPrintOperationDefaultValues(): Unit {
        val sel = ObjCRuntime.sel("setUpPrintOperationDefaultValues")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun PMPrintSession(): MemorySegment {
        val sel = ObjCRuntime.sel("PMPrintSession")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun PMPageFormat(): MemorySegment {
        val sel = ObjCRuntime.sel("PMPageFormat")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun PMPrintSettings(): MemorySegment {
        val sel = ObjCRuntime.sel("PMPrintSettings")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun updateFromPMPageFormat(): Unit {
        val sel = ObjCRuntime.sel("updateFromPMPageFormat")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun updateFromPMPrintSettings(): Unit {
        val sel = ObjCRuntime.sel("updateFromPMPrintSettings")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun takeSettingsFromPDFInfo(inPDFInfo: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("takeSettingsFromPDFInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, inPDFInfo)
    }
    
    // @property sharedPrintInfo
    open fun sharedPrintInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedPrintInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSharedPrintInfo(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSharedPrintInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property paperName
    open fun paperName(): MemorySegment {
        val sel = ObjCRuntime.sel("paperName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPaperName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPaperName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property paperSize
    open fun paperSize(): MemorySegment {
        val sel = ObjCRuntime.sel("paperSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setPaperSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPaperSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property orientation
    open fun orientation(): MemorySegment {
        val sel = ObjCRuntime.sel("orientation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setOrientation(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setOrientation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property scalingFactor
    open fun scalingFactor(): Double {
        val sel = ObjCRuntime.sel("scalingFactor")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setScalingFactor(value: Double) {
        val sel = ObjCRuntime.sel("setScalingFactor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property leftMargin
    open fun leftMargin(): Double {
        val sel = ObjCRuntime.sel("leftMargin")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setLeftMargin(value: Double) {
        val sel = ObjCRuntime.sel("setLeftMargin:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property rightMargin
    open fun rightMargin(): Double {
        val sel = ObjCRuntime.sel("rightMargin")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setRightMargin(value: Double) {
        val sel = ObjCRuntime.sel("setRightMargin:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property topMargin
    open fun topMargin(): Double {
        val sel = ObjCRuntime.sel("topMargin")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setTopMargin(value: Double) {
        val sel = ObjCRuntime.sel("setTopMargin:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property bottomMargin
    open fun bottomMargin(): Double {
        val sel = ObjCRuntime.sel("bottomMargin")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setBottomMargin(value: Double) {
        val sel = ObjCRuntime.sel("setBottomMargin:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property horizontallyCentered
    open fun isHorizontallyCentered(): Boolean {
        val sel = ObjCRuntime.sel("isHorizontallyCentered")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHorizontallyCentered(value: Boolean) {
        val sel = ObjCRuntime.sel("setHorizontallyCentered:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property verticallyCentered
    open fun isVerticallyCentered(): Boolean {
        val sel = ObjCRuntime.sel("isVerticallyCentered")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setVerticallyCentered(value: Boolean) {
        val sel = ObjCRuntime.sel("setVerticallyCentered:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property horizontalPagination
    open fun horizontalPagination(): MemorySegment {
        val sel = ObjCRuntime.sel("horizontalPagination")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHorizontalPagination(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHorizontalPagination:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property verticalPagination
    open fun verticalPagination(): MemorySegment {
        val sel = ObjCRuntime.sel("verticalPagination")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setVerticalPagination(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setVerticalPagination:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property jobDisposition
    open fun jobDisposition(): MemorySegment {
        val sel = ObjCRuntime.sel("jobDisposition")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setJobDisposition(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setJobDisposition:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property printer
    open fun printer(): MemorySegment {
        val sel = ObjCRuntime.sel("printer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPrinter(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPrinter:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property imageablePageBounds
    open fun imageablePageBounds(): MemorySegment {
        val sel = ObjCRuntime.sel("imageablePageBounds")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property localizedPaperName
    open fun localizedPaperName(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedPaperName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun localizedPaperNameAsString(): String = ObjCRuntime.toJavaString(localizedPaperName())
    
    // @property defaultPrinter
    open fun defaultPrinter(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultPrinter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property printSettings
    /** @return NSMutableDictionary<NSPrintInfoSettingKey,id> * */
    open fun printSettings(): MemorySegment {
        val sel = ObjCRuntime.sel("printSettings")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selectionOnly
    open fun isSelectionOnly(): Boolean {
        val sel = ObjCRuntime.sel("isSelectionOnly")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSelectionOnly(value: Boolean) {
        val sel = ObjCRuntime.sel("setSelectionOnly:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSDeprecated on NSPrintInfo ─────────────────────────────────────────

// Class method: +[NSPrintInfo setDefaultPrinter:]
fun NSPrintInfo_setDefaultPrinter(printer: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setDefaultPrinter:")
    val cls = ObjCRuntime.getClass("NSPrintInfo")
    ObjCRuntime.msgSend(null, cls, sel, printer)
}

// Class method: +[NSPrintInfo sizeForPaperName:]
fun NSPrintInfo_sizeForPaperName(name: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("sizeForPaperName:")
    val cls = ObjCRuntime.getClass("NSPrintInfo")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), cls, sel, name) as MemorySegment
}

