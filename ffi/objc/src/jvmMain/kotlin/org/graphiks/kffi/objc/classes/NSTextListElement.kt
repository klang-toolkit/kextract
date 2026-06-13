package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextListElement
 * Superclass: NSTextParagraph
 */
open class NSTextListElement(override val ptr: MemorySegment) : NSTextParagraph(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextListElement") }
        
        fun textListElementWithContents_markerAttributes_textList_childElements(contents: MemorySegment, markerAttributes: MemorySegment, textList: MemorySegment, children: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("textListElementWithContents:markerAttributes:textList:childElements:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, contents, markerAttributes, textList, children) as MemorySegment
        }
        
        fun textListElementWithChildElements_textList_nestingLevel(children: MemorySegment, textList: MemorySegment, nestingLevel: Long): MemorySegment {
            val sel = ObjCRuntime.sel("textListElementWithChildElements:textList:nestingLevel:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, children, textList, nestingLevel) as MemorySegment
        }
        
    }
    
    open fun initWithParentElement_textList_contents_markerAttributes_childElements(parent: MemorySegment, textList: MemorySegment, contents: MemorySegment, markerAttributes: MemorySegment, children: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithParentElement:textList:contents:markerAttributes:childElements:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, parent, textList, contents, markerAttributes, children) as MemorySegment
    }
    
    override fun initWithAttributedString(attributedString: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithAttributedString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, attributedString) as MemorySegment
    }
    
    // @property textList
    open fun textList(): MemorySegment {
        val sel = ObjCRuntime.sel("textList")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property contents
    open fun contents(): MemorySegment {
        val sel = ObjCRuntime.sel("contents")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property markerAttributes
    /** @return NSDictionary<NSAttributedStringKey,id> * */
    open fun markerAttributes(): MemorySegment {
        val sel = ObjCRuntime.sel("markerAttributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property attributedString
    override fun attributedString(): MemorySegment {
        val sel = ObjCRuntime.sel("attributedString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property childElements
    /** @return NSArray<NSTextListElement *> * */
    override fun childElements(): MemorySegment {
        val sel = ObjCRuntime.sel("childElements")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property parentElement
    override fun parentElement(): MemorySegment {
        val sel = ObjCRuntime.sel("parentElement")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

