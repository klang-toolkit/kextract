package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSImage
 * Superclass: NSObject
 */
open class NSImage(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSImage") }
        
        fun imageNamed(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageNamed:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        fun imageWithSystemSymbolName_accessibilityDescription(name: MemorySegment, description: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithSystemSymbolName:accessibilityDescription:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, description) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun imageWithSystemSymbolName_accessibilityDescription(name: String, description: String): MemorySegment = imageWithSystemSymbolName_accessibilityDescription(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), description))
        
        fun imageWithSystemSymbolName_variableValue_accessibilityDescription(name: MemorySegment, value: Double, description: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithSystemSymbolName:variableValue:accessibilityDescription:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, value, description) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun imageWithSystemSymbolName_variableValue_accessibilityDescription(name: String, value: Double, description: String): MemorySegment = imageWithSystemSymbolName_variableValue_accessibilityDescription(ObjCRuntime.newNSString(Arena.global(), name), value, ObjCRuntime.newNSString(Arena.global(), description))
        
        fun imageWithSymbolName_variableValue(name: MemorySegment, value: Double): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithSymbolName:variableValue:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, value) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun imageWithSymbolName_variableValue(name: String, value: Double): MemorySegment = imageWithSymbolName_variableValue(ObjCRuntime.newNSString(Arena.global(), name), value)
        
        fun imageWithSymbolName_bundle_variableValue(name: MemorySegment, bundle: MemorySegment, value: Double): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithSymbolName:bundle:variableValue:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, bundle, value) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun imageWithSymbolName_bundle_variableValue(name: String, bundle: MemorySegment, value: Double): MemorySegment = imageWithSymbolName_bundle_variableValue(ObjCRuntime.newNSString(Arena.global(), name), bundle, value)
        
        fun imageWithSize_flipped_drawingHandler(size: MemorySegment, drawingHandlerShouldBeCalledWithFlippedContext: Boolean, drawingHandler: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithSize:flipped:drawingHandler:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), drawingHandlerShouldBeCalledWithFlippedContext, drawingHandler) as MemorySegment
        }
        
        fun canInitWithPasteboard(pasteboard: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("canInitWithPasteboard:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, pasteboard) as Boolean
        }
        
        /** @return NSArray<NSString *> * */
        fun imageTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("imageTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSArray<NSString *> * */
        fun imageUnfilteredTypes(): MemorySegment {
            val sel = ObjCRuntime.sel("imageUnfilteredTypes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithSize(size: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSize:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"))) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun initWithData(`data`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`) as MemorySegment
    }
    
    open fun initWithContentsOfFile(fileName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentsOfFile:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, fileName) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithContentsOfFile(fileName: String): MemorySegment = initWithContentsOfFile(ObjCRuntime.newNSString(Arena.global(), fileName))
    
    open fun initWithContentsOfURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentsOfURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun initByReferencingFile(fileName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initByReferencingFile:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, fileName) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initByReferencingFile(fileName: String): MemorySegment = initByReferencingFile(ObjCRuntime.newNSString(Arena.global(), fileName))
    
    open fun initByReferencingURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initByReferencingURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun initWithPasteboard(pasteboard: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPasteboard:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pasteboard) as MemorySegment
    }
    
    open fun initWithDataIgnoringOrientation(`data`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDataIgnoringOrientation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`) as MemorySegment
    }
    
    open fun setName(string: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, string) as Boolean
    }
    
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun drawAtPoint_fromRect_operation_fraction(point: MemorySegment, fromRect: MemorySegment, op: MemorySegment, delta: Double): Unit {
        val sel = ObjCRuntime.sel("drawAtPoint:fromRect:operation:fraction:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), ObjCRuntime.ObjCStructArg(fromRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), op, delta)
    }
    
    open fun drawInRect_fromRect_operation_fraction(rect: MemorySegment, fromRect: MemorySegment, op: MemorySegment, delta: Double): Unit {
        val sel = ObjCRuntime.sel("drawInRect:fromRect:operation:fraction:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(fromRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), op, delta)
    }
    
    open fun drawInRect_fromRect_operation_fraction_respectFlipped_hints(dstSpacePortionRect: MemorySegment, srcSpacePortionRect: MemorySegment, op: MemorySegment, requestedAlpha: Double, respectContextIsFlipped: Boolean, hints: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawInRect:fromRect:operation:fraction:respectFlipped:hints:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(dstSpacePortionRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(srcSpacePortionRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), op, requestedAlpha, respectContextIsFlipped, hints)
    }
    
    open fun drawRepresentation_inRect(imageRep: MemorySegment, rect: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("drawRepresentation:inRect:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, imageRep, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as Boolean
    }
    
    open fun drawInRect(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawInRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun recache(): Unit {
        val sel = ObjCRuntime.sel("recache")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun TIFFRepresentationUsingCompression_factor(comp: MemorySegment, factor: Float): MemorySegment {
        val sel = ObjCRuntime.sel("TIFFRepresentationUsingCompression:factor:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, comp, factor) as MemorySegment
    }
    
    open fun addRepresentations(imageReps: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addRepresentations:")
        ObjCRuntime.msgSend(null, ptr, sel, imageReps)
    }
    
    open fun addRepresentation(imageRep: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addRepresentation:")
        ObjCRuntime.msgSend(null, ptr, sel, imageRep)
    }
    
    open fun removeRepresentation(imageRep: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeRepresentation:")
        ObjCRuntime.msgSend(null, ptr, sel, imageRep)
    }
    
    open fun initWithCGImage_size(cgImage: MemorySegment, size: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGImage:size:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, cgImage, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"))) as MemorySegment
    }
    
    open fun CGImageForProposedRect_context_hints(proposedDestRect: MemorySegment, referenceContext: MemorySegment, hints: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("CGImageForProposedRect:context:hints:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, proposedDestRect, referenceContext, hints) as MemorySegment
    }
    
    open fun bestRepresentationForRect_context_hints(rect: MemorySegment, referenceContext: MemorySegment, hints: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("bestRepresentationForRect:context:hints:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), referenceContext, hints) as MemorySegment
    }
    
    open fun hitTestRect_withImageDestinationRect_context_hints_flipped(testRectDestSpace: MemorySegment, imageRectDestSpace: MemorySegment, context: MemorySegment, hints: MemorySegment, flipped: Boolean): Boolean {
        val sel = ObjCRuntime.sel("hitTestRect:withImageDestinationRect:context:hints:flipped:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, ObjCRuntime.ObjCStructArg(testRectDestSpace, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(imageRectDestSpace, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), context, hints, flipped) as Boolean
    }
    
    open fun recommendedLayerContentsScale(preferredContentsScale: Double): Double {
        val sel = ObjCRuntime.sel("recommendedLayerContentsScale:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, preferredContentsScale) as Double
    }
    
    open fun layerContentsForContentsScale(layerContentsScale: Double): MemorySegment {
        val sel = ObjCRuntime.sel("layerContentsForContentsScale:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, layerContentsScale) as MemorySegment
    }
    
    open fun imageWithSymbolConfiguration(configuration: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageWithSymbolConfiguration:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, configuration) as MemorySegment
    }
    
    open fun imageWithLocale(locale: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageWithLocale:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, locale) as MemorySegment
    }
    
    // @property size
    open fun size(): MemorySegment {
        val sel = ObjCRuntime.sel("size")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    open fun setSize(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSize:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")))
    }
    
    // @property backgroundColor
    open fun backgroundColor(): MemorySegment {
        val sel = ObjCRuntime.sel("backgroundColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBackgroundColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBackgroundColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesEPSOnResolutionMismatch
    open fun usesEPSOnResolutionMismatch(): Boolean {
        val sel = ObjCRuntime.sel("usesEPSOnResolutionMismatch")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesEPSOnResolutionMismatch(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesEPSOnResolutionMismatch:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property prefersColorMatch
    open fun prefersColorMatch(): Boolean {
        val sel = ObjCRuntime.sel("prefersColorMatch")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPrefersColorMatch(value: Boolean) {
        val sel = ObjCRuntime.sel("setPrefersColorMatch:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property matchesOnMultipleResolution
    open fun matchesOnMultipleResolution(): Boolean {
        val sel = ObjCRuntime.sel("matchesOnMultipleResolution")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setMatchesOnMultipleResolution(value: Boolean) {
        val sel = ObjCRuntime.sel("setMatchesOnMultipleResolution:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property matchesOnlyOnBestFittingAxis
    open fun matchesOnlyOnBestFittingAxis(): Boolean {
        val sel = ObjCRuntime.sel("matchesOnlyOnBestFittingAxis")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setMatchesOnlyOnBestFittingAxis(value: Boolean) {
        val sel = ObjCRuntime.sel("setMatchesOnlyOnBestFittingAxis:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property TIFFRepresentation
    open fun TIFFRepresentation(): MemorySegment {
        val sel = ObjCRuntime.sel("TIFFRepresentation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property representations
    /** @return NSArray<NSImageRep *> * */
    open fun representations(): MemorySegment {
        val sel = ObjCRuntime.sel("representations")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property valid
    open fun isValid(): Boolean {
        val sel = ObjCRuntime.sel("isValid")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property delegate
    /** @return id<NSImageDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property imageTypes
    /** @return NSArray<NSString *> * */
    open fun imageTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("imageTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property imageUnfilteredTypes
    /** @return NSArray<NSString *> * */
    open fun imageUnfilteredTypes(): MemorySegment {
        val sel = ObjCRuntime.sel("imageUnfilteredTypes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property cacheMode
    open fun cacheMode(): MemorySegment {
        val sel = ObjCRuntime.sel("cacheMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCacheMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCacheMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property alignmentRect
    open fun alignmentRect(): MemorySegment {
        val sel = ObjCRuntime.sel("alignmentRect")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    open fun setAlignmentRect(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAlignmentRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    // @property template
    open fun isTemplate(): Boolean {
        val sel = ObjCRuntime.sel("isTemplate")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setTemplate(value: Boolean) {
        val sel = ObjCRuntime.sel("setTemplate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property accessibilityDescription
    open fun accessibilityDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("accessibilityDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAccessibilityDescription(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAccessibilityDescription:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun accessibilityDescriptionAsString(): String = ObjCRuntime.toJavaString(accessibilityDescription())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setAccessibilityDescription(value: String) = setAccessibilityDescription(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property capInsets
    open fun capInsets(): MemorySegment {
        val sel = ObjCRuntime.sel("capInsets")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), ptr, sel) as MemorySegment
    }
    open fun setCapInsets(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCapInsets:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets")))
    }
    
    // @property resizingMode
    open fun resizingMode(): MemorySegment {
        val sel = ObjCRuntime.sel("resizingMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setResizingMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setResizingMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property symbolConfiguration
    open fun symbolConfiguration(): MemorySegment {
        val sel = ObjCRuntime.sel("symbolConfiguration")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property locale
    open fun locale(): MemorySegment {
        val sel = ObjCRuntime.sel("locale")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category:  on NSImage ─────────────────────────────────────────

// ── Category: Deprecated on NSImage ─────────────────────────────────────────

fun NSImage.initWithIconRef(iconRef: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithIconRef:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, iconRef) as MemorySegment
}

fun NSImage.bestRepresentationForDevice(deviceDescription: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("bestRepresentationForDevice:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, deviceDescription) as MemorySegment
}

fun NSImage.lockFocus(): Unit {
    val sel = ObjCRuntime.sel("lockFocus")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSImage.lockFocusFlipped(flipped: Boolean): Unit {
    val sel = ObjCRuntime.sel("lockFocusFlipped:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flipped)
}

fun NSImage.unlockFocus(): Unit {
    val sel = ObjCRuntime.sel("unlockFocus")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

fun NSImage.setFlipped(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setFlipped:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSImage.isFlipped(): Boolean {
    val sel = ObjCRuntime.sel("isFlipped")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSImage.setScalesWhenResized(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setScalesWhenResized:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSImage.scalesWhenResized(): Boolean {
    val sel = ObjCRuntime.sel("scalesWhenResized")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSImage.setDataRetained(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setDataRetained:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSImage.isDataRetained(): Boolean {
    val sel = ObjCRuntime.sel("isDataRetained")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSImage.setCachedSeparately(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setCachedSeparately:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSImage.isCachedSeparately(): Boolean {
    val sel = ObjCRuntime.sel("isCachedSeparately")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSImage.setCacheDepthMatchesImageDepth(flag: Boolean): Unit {
    val sel = ObjCRuntime.sel("setCacheDepthMatchesImageDepth:")
    ObjCRuntime.msgSend(null, this.ptr, sel, flag)
}

fun NSImage.cacheDepthMatchesImageDepth(): Boolean {
    val sel = ObjCRuntime.sel("cacheDepthMatchesImageDepth")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSImage.dissolveToPoint_fraction(point: MemorySegment, fraction: Double): Unit {
    val sel = ObjCRuntime.sel("dissolveToPoint:fraction:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point, fraction)
}

fun NSImage.dissolveToPoint_fromRect_fraction(point: MemorySegment, rect: MemorySegment, fraction: Double): Unit {
    val sel = ObjCRuntime.sel("dissolveToPoint:fromRect:fraction:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point, rect, fraction)
}

fun NSImage.compositeToPoint_operation(point: MemorySegment, operation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("compositeToPoint:operation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point, operation)
}

fun NSImage.compositeToPoint_fromRect_operation(point: MemorySegment, rect: MemorySegment, operation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("compositeToPoint:fromRect:operation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point, rect, operation)
}

fun NSImage.compositeToPoint_operation_fraction(point: MemorySegment, operation: MemorySegment, fraction: Double): Unit {
    val sel = ObjCRuntime.sel("compositeToPoint:operation:fraction:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point, operation, fraction)
}

fun NSImage.compositeToPoint_fromRect_operation_fraction(point: MemorySegment, rect: MemorySegment, operation: MemorySegment, fraction: Double): Unit {
    val sel = ObjCRuntime.sel("compositeToPoint:fromRect:operation:fraction:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point, rect, operation, fraction)
}

fun NSImage.lockFocusOnRepresentation(imageRepresentation: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("lockFocusOnRepresentation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, imageRepresentation)
}

fun NSImage.cancelIncrementalLoad(): Unit {
    val sel = ObjCRuntime.sel("cancelIncrementalLoad")
    ObjCRuntime.msgSend(null, this.ptr, sel)
}

// Class method: +[NSImage imageUnfilteredFileTypes]
fun NSImage_imageUnfilteredFileTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("imageUnfilteredFileTypes")
    val cls = ObjCRuntime.getClass("NSImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSImage imageUnfilteredPasteboardTypes]
fun NSImage_imageUnfilteredPasteboardTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("imageUnfilteredPasteboardTypes")
    val cls = ObjCRuntime.getClass("NSImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSImage imageFileTypes]
fun NSImage_imageFileTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("imageFileTypes")
    val cls = ObjCRuntime.getClass("NSImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSImage imagePasteboardTypes]
fun NSImage_imagePasteboardTypes(): MemorySegment {
    val sel = ObjCRuntime.sel("imagePasteboardTypes")
    val cls = ObjCRuntime.getClass("NSImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

