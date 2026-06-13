package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextAttachment
 * Superclass: NSObject
 * Protocols: NSTextAttachmentLayout, NSSecureCoding
 */
open class NSTextAttachment(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextAttachment") }
        
        fun textAttachmentViewProviderClassForFileType(fileType: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("textAttachmentViewProviderClassForFileType:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, fileType) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun textAttachmentViewProviderClassForFileType(fileType: String): MemorySegment = textAttachmentViewProviderClassForFileType(ObjCRuntime.newNSString(Arena.global(), fileType))
        
        fun registerTextAttachmentViewProviderClass_forFileType(textAttachmentViewProviderClass: MemorySegment, fileType: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("registerTextAttachmentViewProviderClass:forFileType:")
            ObjCRuntime.msgSend(null, _class, sel, textAttachmentViewProviderClass, fileType)
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun registerTextAttachmentViewProviderClass_forFileType(textAttachmentViewProviderClass: MemorySegment, fileType: String): Unit = registerTextAttachmentViewProviderClass_forFileType(textAttachmentViewProviderClass, ObjCRuntime.newNSString(Arena.global(), fileType))
        
    }
    
    open fun initWithData_ofType(contentData: MemorySegment, uti: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithData:ofType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, contentData, uti) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithData_ofType(contentData: MemorySegment, uti: String): MemorySegment = initWithData_ofType(contentData, ObjCRuntime.newNSString(Arena.global(), uti))
    
    open fun initWithFileWrapper(fileWrapper: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFileWrapper:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, fileWrapper) as MemorySegment
    }
    
    // @property contents
    open fun contents(): MemorySegment {
        val sel = ObjCRuntime.sel("contents")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContents(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContents:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
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
    
    // @property image
    open fun image(): MemorySegment {
        val sel = ObjCRuntime.sel("image")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property bounds
    open fun bounds(): MemorySegment {
        val sel = ObjCRuntime.sel("bounds")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    open fun setBounds(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBounds:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    // @property fileWrapper
    open fun fileWrapper(): MemorySegment {
        val sel = ObjCRuntime.sel("fileWrapper")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFileWrapper(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFileWrapper:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property attachmentCell
    /** @return id<NSTextAttachmentCell> */
    open fun attachmentCell(): MemorySegment {
        val sel = ObjCRuntime.sel("attachmentCell")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttachmentCell(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttachmentCell:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property lineLayoutPadding
    open fun lineLayoutPadding(): Double {
        val sel = ObjCRuntime.sel("lineLayoutPadding")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setLineLayoutPadding(value: Double) {
        val sel = ObjCRuntime.sel("setLineLayoutPadding:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsTextAttachmentView
    open fun allowsTextAttachmentView(): Boolean {
        val sel = ObjCRuntime.sel("allowsTextAttachmentView")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsTextAttachmentView(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsTextAttachmentView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesTextAttachmentView
    open fun usesTextAttachmentView(): Boolean {
        val sel = ObjCRuntime.sel("usesTextAttachmentView")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSTextAttachment_Deprecation on NSTextAttachment ─────────────────────────────────────────

