package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSScrollView
 * Superclass: NSView
 * Protocols: NSTextFinderBarContainer
 */
open class NSScrollView(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSScrollView") }
        
        fun frameSizeForContentSize_horizontalScrollerClass_verticalScrollerClass_borderType_controlSize_scrollerStyle(cSize: MemorySegment, horizontalScrollerClass: MemorySegment, verticalScrollerClass: MemorySegment, type: MemorySegment, controlSize: MemorySegment, scrollerStyle: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("frameSizeForContentSize:horizontalScrollerClass:verticalScrollerClass:borderType:controlSize:scrollerStyle:")
            return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), _class, sel, ObjCRuntime.ObjCStructArg(cSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), horizontalScrollerClass, verticalScrollerClass, type, controlSize, scrollerStyle) as MemorySegment
        }
        
        fun contentSizeForFrameSize_horizontalScrollerClass_verticalScrollerClass_borderType_controlSize_scrollerStyle(fSize: MemorySegment, horizontalScrollerClass: MemorySegment, verticalScrollerClass: MemorySegment, type: MemorySegment, controlSize: MemorySegment, scrollerStyle: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("contentSizeForFrameSize:horizontalScrollerClass:verticalScrollerClass:borderType:controlSize:scrollerStyle:")
            return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), _class, sel, ObjCRuntime.ObjCStructArg(fSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), horizontalScrollerClass, verticalScrollerClass, type, controlSize, scrollerStyle) as MemorySegment
        }
        
        fun frameSizeForContentSize_hasHorizontalScroller_hasVerticalScroller_borderType(cSize: MemorySegment, hFlag: Boolean, vFlag: Boolean, type: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("frameSizeForContentSize:hasHorizontalScroller:hasVerticalScroller:borderType:")
            return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), _class, sel, ObjCRuntime.ObjCStructArg(cSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), hFlag, vFlag, type) as MemorySegment
        }
        
        fun contentSizeForFrameSize_hasHorizontalScroller_hasVerticalScroller_borderType(fSize: MemorySegment, hFlag: Boolean, vFlag: Boolean, type: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("contentSizeForFrameSize:hasHorizontalScroller:hasVerticalScroller:borderType:")
            return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), _class, sel, ObjCRuntime.ObjCStructArg(fSize, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), hFlag, vFlag, type) as MemorySegment
        }
        
    }
    
    override fun initWithFrame(frameRect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFrame:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(frameRect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun tile(): Unit {
        val sel = ObjCRuntime.sel("tile")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun reflectScrolledClipView(cView: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("reflectScrolledClipView:")
        ObjCRuntime.msgSend(null, ptr, sel, cView)
    }
    
    override fun scrollWheel(event: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scrollWheel:")
        ObjCRuntime.msgSend(null, ptr, sel, event)
    }
    
    open fun flashScrollers(): Unit {
        val sel = ObjCRuntime.sel("flashScrollers")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun magnifyToFitRect(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("magnifyToFitRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun setMagnification_centeredAtPoint(magnification: Double, point: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setMagnification:centeredAtPoint:")
        ObjCRuntime.msgSend(null, ptr, sel, magnification, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun addFloatingSubview_forAxis(view: MemorySegment, axis: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addFloatingSubview:forAxis:")
        ObjCRuntime.msgSend(null, ptr, sel, view, axis)
    }
    
    // @property documentVisibleRect
    open fun documentVisibleRect(): MemorySegment {
        val sel = ObjCRuntime.sel("documentVisibleRect")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property contentSize
    open fun contentSize(): MemorySegment {
        val sel = ObjCRuntime.sel("contentSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    
    // @property documentView
    open fun documentView(): MemorySegment {
        val sel = ObjCRuntime.sel("documentView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDocumentView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDocumentView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property contentView
    open fun contentView(): MemorySegment {
        val sel = ObjCRuntime.sel("contentView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContentView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property documentCursor
    open fun documentCursor(): MemorySegment {
        val sel = ObjCRuntime.sel("documentCursor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDocumentCursor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDocumentCursor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property borderType
    open fun borderType(): MemorySegment {
        val sel = ObjCRuntime.sel("borderType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBorderType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBorderType:")
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
    
    // @property drawsBackground
    open fun drawsBackground(): Boolean {
        val sel = ObjCRuntime.sel("drawsBackground")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDrawsBackground(value: Boolean) {
        val sel = ObjCRuntime.sel("setDrawsBackground:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasVerticalScroller
    open fun hasVerticalScroller(): Boolean {
        val sel = ObjCRuntime.sel("hasVerticalScroller")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHasVerticalScroller(value: Boolean) {
        val sel = ObjCRuntime.sel("setHasVerticalScroller:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property hasHorizontalScroller
    open fun hasHorizontalScroller(): Boolean {
        val sel = ObjCRuntime.sel("hasHorizontalScroller")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setHasHorizontalScroller(value: Boolean) {
        val sel = ObjCRuntime.sel("setHasHorizontalScroller:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property verticalScroller
    open fun verticalScroller(): MemorySegment {
        val sel = ObjCRuntime.sel("verticalScroller")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setVerticalScroller(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setVerticalScroller:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property horizontalScroller
    open fun horizontalScroller(): MemorySegment {
        val sel = ObjCRuntime.sel("horizontalScroller")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHorizontalScroller(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHorizontalScroller:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property autohidesScrollers
    open fun autohidesScrollers(): Boolean {
        val sel = ObjCRuntime.sel("autohidesScrollers")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutohidesScrollers(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutohidesScrollers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property horizontalLineScroll
    open fun horizontalLineScroll(): Double {
        val sel = ObjCRuntime.sel("horizontalLineScroll")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setHorizontalLineScroll(value: Double) {
        val sel = ObjCRuntime.sel("setHorizontalLineScroll:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property verticalLineScroll
    open fun verticalLineScroll(): Double {
        val sel = ObjCRuntime.sel("verticalLineScroll")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setVerticalLineScroll(value: Double) {
        val sel = ObjCRuntime.sel("setVerticalLineScroll:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property lineScroll
    open fun lineScroll(): Double {
        val sel = ObjCRuntime.sel("lineScroll")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setLineScroll(value: Double) {
        val sel = ObjCRuntime.sel("setLineScroll:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property horizontalPageScroll
    open fun horizontalPageScroll(): Double {
        val sel = ObjCRuntime.sel("horizontalPageScroll")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setHorizontalPageScroll(value: Double) {
        val sel = ObjCRuntime.sel("setHorizontalPageScroll:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property verticalPageScroll
    open fun verticalPageScroll(): Double {
        val sel = ObjCRuntime.sel("verticalPageScroll")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setVerticalPageScroll(value: Double) {
        val sel = ObjCRuntime.sel("setVerticalPageScroll:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property pageScroll
    open fun pageScroll(): Double {
        val sel = ObjCRuntime.sel("pageScroll")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setPageScroll(value: Double) {
        val sel = ObjCRuntime.sel("setPageScroll:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property scrollsDynamically
    open fun scrollsDynamically(): Boolean {
        val sel = ObjCRuntime.sel("scrollsDynamically")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setScrollsDynamically(value: Boolean) {
        val sel = ObjCRuntime.sel("setScrollsDynamically:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property scrollerStyle
    open fun scrollerStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("scrollerStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScrollerStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScrollerStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property scrollerKnobStyle
    open fun scrollerKnobStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("scrollerKnobStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScrollerKnobStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScrollerKnobStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property horizontalScrollElasticity
    open fun horizontalScrollElasticity(): MemorySegment {
        val sel = ObjCRuntime.sel("horizontalScrollElasticity")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHorizontalScrollElasticity(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHorizontalScrollElasticity:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property verticalScrollElasticity
    open fun verticalScrollElasticity(): MemorySegment {
        val sel = ObjCRuntime.sel("verticalScrollElasticity")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setVerticalScrollElasticity(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setVerticalScrollElasticity:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property usesPredominantAxisScrolling
    open fun usesPredominantAxisScrolling(): Boolean {
        val sel = ObjCRuntime.sel("usesPredominantAxisScrolling")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setUsesPredominantAxisScrolling(value: Boolean) {
        val sel = ObjCRuntime.sel("setUsesPredominantAxisScrolling:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowsMagnification
    open fun allowsMagnification(): Boolean {
        val sel = ObjCRuntime.sel("allowsMagnification")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsMagnification(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsMagnification:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property magnification
    open fun magnification(): Double {
        val sel = ObjCRuntime.sel("magnification")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMagnification(value: Double) {
        val sel = ObjCRuntime.sel("setMagnification:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property maxMagnification
    open fun maxMagnification(): Double {
        val sel = ObjCRuntime.sel("maxMagnification")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMaxMagnification(value: Double) {
        val sel = ObjCRuntime.sel("setMaxMagnification:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property minMagnification
    open fun minMagnification(): Double {
        val sel = ObjCRuntime.sel("minMagnification")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMinMagnification(value: Double) {
        val sel = ObjCRuntime.sel("setMinMagnification:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property automaticallyAdjustsContentInsets
    open fun automaticallyAdjustsContentInsets(): Boolean {
        val sel = ObjCRuntime.sel("automaticallyAdjustsContentInsets")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticallyAdjustsContentInsets(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticallyAdjustsContentInsets:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property contentInsets
    open fun contentInsets(): MemorySegment {
        val sel = ObjCRuntime.sel("contentInsets")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), ptr, sel) as MemorySegment
    }
    open fun setContentInsets(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentInsets:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets")))
    }
    
    // @property scrollerInsets
    open fun scrollerInsets(): MemorySegment {
        val sel = ObjCRuntime.sel("scrollerInsets")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), ptr, sel) as MemorySegment
    }
    open fun setScrollerInsets(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScrollerInsets:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets")))
    }
    
}

// ── Category: NSRulerSupport on NSScrollView ─────────────────────────────────────────

fun NSScrollView.rulersVisible(): Boolean {
    val sel = ObjCRuntime.sel("rulersVisible")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSScrollView.setRulersVisible(rulersVisible: Boolean): Unit {
    val sel = ObjCRuntime.sel("setRulersVisible:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rulersVisible)
}

fun NSScrollView.hasHorizontalRuler(): Boolean {
    val sel = ObjCRuntime.sel("hasHorizontalRuler")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSScrollView.setHasHorizontalRuler(hasHorizontalRuler: Boolean): Unit {
    val sel = ObjCRuntime.sel("setHasHorizontalRuler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, hasHorizontalRuler)
}

fun NSScrollView.hasVerticalRuler(): Boolean {
    val sel = ObjCRuntime.sel("hasVerticalRuler")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel) as Boolean
}

fun NSScrollView.setHasVerticalRuler(hasVerticalRuler: Boolean): Unit {
    val sel = ObjCRuntime.sel("setHasVerticalRuler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, hasVerticalRuler)
}

fun NSScrollView.horizontalRulerView(): MemorySegment {
    val sel = ObjCRuntime.sel("horizontalRulerView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSScrollView.setHorizontalRulerView(horizontalRulerView: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setHorizontalRulerView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, horizontalRulerView)
}

fun NSScrollView.verticalRulerView(): MemorySegment {
    val sel = ObjCRuntime.sel("verticalRulerView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSScrollView.setVerticalRulerView(verticalRulerView: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setVerticalRulerView:")
    ObjCRuntime.msgSend(null, this.ptr, sel, verticalRulerView)
}

// Class method: +[NSScrollView rulerViewClass]
fun NSScrollView_rulerViewClass(): MemorySegment {
    val sel = ObjCRuntime.sel("rulerViewClass")
    val cls = ObjCRuntime.getClass("NSScrollView")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSScrollView setRulerViewClass:]
fun NSScrollView_setRulerViewClass(rulerViewClass: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setRulerViewClass:")
    val cls = ObjCRuntime.getClass("NSScrollView")
    ObjCRuntime.msgSend(null, cls, sel, rulerViewClass)
}

// @property rulerViewClass
fun NSScrollView.rulerViewClass(): MemorySegment {
    val sel = ObjCRuntime.sel("rulerViewClass")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}
fun NSScrollView.setRulerViewClass(value: MemorySegment) {
    val sel = ObjCRuntime.sel("setRulerViewClass:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value)
}

// ── Category: NSFindBarSupport on NSScrollView ─────────────────────────────────────────

fun NSScrollView.findBarPosition(): MemorySegment {
    val sel = ObjCRuntime.sel("findBarPosition")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSScrollView.setFindBarPosition(findBarPosition: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setFindBarPosition:")
    ObjCRuntime.msgSend(null, this.ptr, sel, findBarPosition)
}

