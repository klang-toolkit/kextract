package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextLayoutManager
 * Superclass: NSObject
 * Protocols: NSSecureCoding, NSTextSelectionDataSource
 */
open class NSTextLayoutManager(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextLayoutManager") }
        
        /** @return NSDictionary<NSAttributedStringKey,id> * */
        fun linkRenderingAttributes(): MemorySegment {
            val sel = ObjCRuntime.sel("linkRenderingAttributes")
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
    
    open fun replaceTextContentManager(textContentManager: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceTextContentManager:")
        ObjCRuntime.msgSend(null, ptr, sel, textContentManager)
    }
    
    open fun ensureLayoutForRange(range: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("ensureLayoutForRange:")
        ObjCRuntime.msgSend(null, ptr, sel, range)
    }
    
    open fun ensureLayoutForBounds(bounds: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("ensureLayoutForBounds:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(bounds, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun invalidateLayoutForRange(range: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("invalidateLayoutForRange:")
        ObjCRuntime.msgSend(null, ptr, sel, range)
    }
    
    open fun textLayoutFragmentForPosition(position: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("textLayoutFragmentForPosition:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(position, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun textLayoutFragmentForLocation(location: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("textLayoutFragmentForLocation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, location) as MemorySegment
    }
    
    /** @return id<NSTextLocation> */
    open fun enumerateTextLayoutFragmentsFromLocation_options_usingBlock(location: MemorySegment, options: MemorySegment, block: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("enumerateTextLayoutFragmentsFromLocation:options:usingBlock:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, location, options, block) as MemorySegment
    }
    
    open fun enumerateRenderingAttributesFromLocation_reverse_usingBlock(location: MemorySegment, reverse: Boolean, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateRenderingAttributesFromLocation:reverse:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, location, reverse, block)
    }
    
    open fun setRenderingAttributes_forTextRange(renderingAttributes: MemorySegment, textRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setRenderingAttributes:forTextRange:")
        ObjCRuntime.msgSend(null, ptr, sel, renderingAttributes, textRange)
    }
    
    open fun addRenderingAttribute_value_forTextRange(renderingAttribute: MemorySegment, value: MemorySegment, textRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addRenderingAttribute:value:forTextRange:")
        ObjCRuntime.msgSend(null, ptr, sel, renderingAttribute, value, textRange)
    }
    
    open fun removeRenderingAttribute_forTextRange(renderingAttribute: MemorySegment, textRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeRenderingAttribute:forTextRange:")
        ObjCRuntime.msgSend(null, ptr, sel, renderingAttribute, textRange)
    }
    
    open fun invalidateRenderingAttributesForTextRange(textRange: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("invalidateRenderingAttributesForTextRange:")
        ObjCRuntime.msgSend(null, ptr, sel, textRange)
    }
    
    /** @return NSDictionary<NSAttributedStringKey,id> * */
    open fun renderingAttributesForLink_atLocation(link: MemorySegment, location: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("renderingAttributesForLink:atLocation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, link, location) as MemorySegment
    }
    
    open fun enumerateTextSegmentsInRange_type_options_usingBlock(textRange: MemorySegment, type: MemorySegment, options: MemorySegment, block: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("enumerateTextSegmentsInRange:type:options:usingBlock:")
        ObjCRuntime.msgSend(null, ptr, sel, textRange, type, options, block)
    }
    
    open fun replaceContentsInRange_withTextElements(range: MemorySegment, textElements: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceContentsInRange:withTextElements:")
        ObjCRuntime.msgSend(null, ptr, sel, range, textElements)
    }
    
    open fun replaceContentsInRange_withAttributedString(range: MemorySegment, attributedString: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replaceContentsInRange:withAttributedString:")
        ObjCRuntime.msgSend(null, ptr, sel, range, attributedString)
    }
    
    // @property delegate
    /** @return id<NSTextLayoutManagerDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesFontLeading
    open fun usesFontLeading(): Boolean {
        val sel = ObjCRuntime.sel("usesFontLeading")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesFontLeading(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesFontLeading:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property limitsLayoutForSuspiciousContents
    open fun limitsLayoutForSuspiciousContents(): Boolean {
        val sel = ObjCRuntime.sel("limitsLayoutForSuspiciousContents")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setLimitsLayoutForSuspiciousContents(value: Boolean) {
        val sel = ObjCRuntime.sel("setLimitsLayoutForSuspiciousContents:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesHyphenation
    open fun usesHyphenation(): Boolean {
        val sel = ObjCRuntime.sel("usesHyphenation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesHyphenation(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesHyphenation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property resolvesNaturalAlignmentWithBaseWritingDirection
    open fun resolvesNaturalAlignmentWithBaseWritingDirection(): Boolean {
        val sel = ObjCRuntime.sel("resolvesNaturalAlignmentWithBaseWritingDirection")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setResolvesNaturalAlignmentWithBaseWritingDirection(value: Boolean) {
        val sel = ObjCRuntime.sel("setResolvesNaturalAlignmentWithBaseWritingDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property textContentManager
    open fun textContentManager(): MemorySegment {
        val sel = ObjCRuntime.sel("textContentManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property textContainer
    open fun textContainer(): MemorySegment {
        val sel = ObjCRuntime.sel("textContainer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTextContainer(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTextContainer:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usageBoundsForTextContainer
    open fun usageBoundsForTextContainer(): MemorySegment {
        val sel = ObjCRuntime.sel("usageBoundsForTextContainer")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property textViewportLayoutController
    open fun textViewportLayoutController(): MemorySegment {
        val sel = ObjCRuntime.sel("textViewportLayoutController")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property layoutQueue
    open fun layoutQueue(): MemorySegment {
        val sel = ObjCRuntime.sel("layoutQueue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLayoutQueue(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLayoutQueue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property textSelections
    /** @return NSArray<NSTextSelection *> * */
    open fun textSelections(): MemorySegment {
        val sel = ObjCRuntime.sel("textSelections")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTextSelections(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTextSelections:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property textSelectionNavigation
    open fun textSelectionNavigation(): MemorySegment {
        val sel = ObjCRuntime.sel("textSelectionNavigation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTextSelectionNavigation(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTextSelectionNavigation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property renderingAttributesValidator
    open fun renderingAttributesValidator(): MemorySegment {
        val sel = ObjCRuntime.sel("renderingAttributesValidator")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setRenderingAttributesValidator(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setRenderingAttributesValidator:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property linkRenderingAttributes
    /** @return NSDictionary<NSAttributedStringKey,id> * */
    open fun linkRenderingAttributes(): MemorySegment {
        val sel = ObjCRuntime.sel("linkRenderingAttributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

