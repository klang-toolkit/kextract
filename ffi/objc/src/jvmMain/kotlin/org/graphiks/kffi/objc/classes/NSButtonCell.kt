package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSButtonCell
 * Superclass: NSActionCell
 */
open class NSButtonCell(override val ptr: MemorySegment) : NSActionCell(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSButtonCell") }
        
    }
    
    override fun initTextCell(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initTextCell:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string) as MemorySegment
    }
    
    override fun initImageCell(image: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initImageCell:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, image) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun setButtonType(type: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setButtonType:")
        ObjCRuntime.msgSend(null, ptr, sel, type)
    }
    
    open fun setPeriodicDelay_interval(delay: Float, interval: Float): Unit {
        val sel = ObjCRuntime.sel("setPeriodicDelay:interval:")
        ObjCRuntime.msgSend(null, ptr, sel, delay, interval)
    }
    
    override fun getPeriodicDelay_interval(delay: MemorySegment, interval: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getPeriodicDelay:interval:")
        ObjCRuntime.msgSend(null, ptr, sel, delay, interval)
    }
    
    open fun performClick(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("performClick:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun mouseEntered(event: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("mouseEntered:")
        ObjCRuntime.msgSend(null, ptr, sel, event)
    }
    
    open fun mouseExited(event: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("mouseExited:")
        ObjCRuntime.msgSend(null, ptr, sel, event)
    }
    
    open fun drawBezelWithFrame_inView(frame: MemorySegment, controlView: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawBezelWithFrame:inView:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(frame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), controlView)
    }
    
    open fun drawImage_withFrame_inView(image: MemorySegment, frame: MemorySegment, controlView: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawImage:withFrame:inView:")
        ObjCRuntime.msgSend(null, ptr, sel, image, ObjCRuntime.ObjCStructArg(frame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), controlView)
    }
    
    open fun drawTitle_withFrame_inView(title: MemorySegment, frame: MemorySegment, controlView: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("drawTitle:withFrame:inView:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, title, ObjCRuntime.ObjCStructArg(frame, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), controlView) as MemorySegment
    }
    
    // @property bezelStyle
    open fun bezelStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("bezelStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBezelStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBezelStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property highlightsBy
    open fun highlightsBy(): MemorySegment {
        val sel = ObjCRuntime.sel("highlightsBy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHighlightsBy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHighlightsBy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsStateBy
    open fun showsStateBy(): MemorySegment {
        val sel = ObjCRuntime.sel("showsStateBy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setShowsStateBy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setShowsStateBy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property title
    override fun title(): MemorySegment {
        val sel = ObjCRuntime.sel("title")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property attributedTitle
    open fun attributedTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("attributedTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttributedTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttributedTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property alternateTitle
    open fun alternateTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("alternateTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAlternateTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAlternateTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun alternateTitleAsString(): String = ObjCRuntime.toJavaString(alternateTitle())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setAlternateTitle(value: String) = setAlternateTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property attributedAlternateTitle
    open fun attributedAlternateTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("attributedAlternateTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttributedAlternateTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttributedAlternateTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property alternateImage
    open fun alternateImage(): MemorySegment {
        val sel = ObjCRuntime.sel("alternateImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAlternateImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAlternateImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property imagePosition
    open fun imagePosition(): MemorySegment {
        val sel = ObjCRuntime.sel("imagePosition")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setImagePosition(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setImagePosition:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property imageScaling
    open fun imageScaling(): MemorySegment {
        val sel = ObjCRuntime.sel("imageScaling")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setImageScaling(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setImageScaling:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property keyEquivalent
    override fun keyEquivalent(): MemorySegment {
        val sel = ObjCRuntime.sel("keyEquivalent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setKeyEquivalent(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setKeyEquivalent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property keyEquivalentModifierMask
    open fun keyEquivalentModifierMask(): MemorySegment {
        val sel = ObjCRuntime.sel("keyEquivalentModifierMask")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setKeyEquivalentModifierMask(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setKeyEquivalentModifierMask:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property transparent
    open fun isTransparent(): Boolean {
        val sel = ObjCRuntime.sel("isTransparent")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setTransparent(value: Boolean) {
        val sel = ObjCRuntime.sel("setTransparent:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property opaque
    override fun isOpaque(): Boolean {
        val sel = ObjCRuntime.sel("isOpaque")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property imageDimsWhenDisabled
    open fun imageDimsWhenDisabled(): Boolean {
        val sel = ObjCRuntime.sel("imageDimsWhenDisabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setImageDimsWhenDisabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setImageDimsWhenDisabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsBorderOnlyWhileMouseInside
    open fun showsBorderOnlyWhileMouseInside(): Boolean {
        val sel = ObjCRuntime.sel("showsBorderOnlyWhileMouseInside")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsBorderOnlyWhileMouseInside(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsBorderOnlyWhileMouseInside:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property sound
    open fun sound(): MemorySegment {
        val sel = ObjCRuntime.sel("sound")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSound(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSound:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
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
    
}

// ── Category: NSDeprecated on NSButtonCell ─────────────────────────────────────────

fun NSButtonCell.setTitleWithMnemonic(stringWithAmpersand: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setTitleWithMnemonic:")
    ObjCRuntime.msgSend(null, this.ptr, sel, stringWithAmpersand)
}

fun NSButtonCell.setAlternateTitleWithMnemonic(stringWithAmpersand: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setAlternateTitleWithMnemonic:")
    ObjCRuntime.msgSend(null, this.ptr, sel, stringWithAmpersand)
}

fun NSButtonCell.setAlternateMnemonicLocation(location: Long): Unit {
    val sel = ObjCRuntime.sel("setAlternateMnemonicLocation:")
    ObjCRuntime.msgSend(null, this.ptr, sel, location)
}

fun NSButtonCell.alternateMnemonicLocation(): Long {
    val sel = ObjCRuntime.sel("alternateMnemonicLocation")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, this.ptr, sel) as Long
}

fun NSButtonCell.alternateMnemonic(): MemorySegment {
    val sel = ObjCRuntime.sel("alternateMnemonic")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSButtonCell.setKeyEquivalentFont_size(fontName: MemorySegment, fontSize: Double): Unit {
    val sel = ObjCRuntime.sel("setKeyEquivalentFont:size:")
    ObjCRuntime.msgSend(null, this.ptr, sel, fontName, fontSize)
}

fun NSButtonCell.gradientType(): MemorySegment {
    val sel = ObjCRuntime.sel("gradientType")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSButtonCell.setGradientType(gradientType: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setGradientType:")
    ObjCRuntime.msgSend(null, this.ptr, sel, gradientType)
}

fun NSButtonCell.keyEquivalentFont(): MemorySegment {
    val sel = ObjCRuntime.sel("keyEquivalentFont")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSButtonCell.setKeyEquivalentFont(keyEquivalentFont: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setKeyEquivalentFont:")
    ObjCRuntime.msgSend(null, this.ptr, sel, keyEquivalentFont)
}

