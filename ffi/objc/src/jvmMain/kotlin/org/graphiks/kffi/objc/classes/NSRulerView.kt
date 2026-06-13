package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSRulerView
 * Superclass: NSView
 */
open class NSRulerView(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSRulerView") }
        
        fun registerUnitWithName_abbreviation_unitToPointsConversionFactor_stepUpCycle_stepDownCycle(unitName: MemorySegment, abbreviation: MemorySegment, conversionFactor: Double, stepUpCycle: MemorySegment, stepDownCycle: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("registerUnitWithName:abbreviation:unitToPointsConversionFactor:stepUpCycle:stepDownCycle:")
            ObjCRuntime.msgSend(null, _class, sel, unitName, abbreviation, conversionFactor, stepUpCycle, stepDownCycle)
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun registerUnitWithName_abbreviation_unitToPointsConversionFactor_stepUpCycle_stepDownCycle(unitName: MemorySegment, abbreviation: String, conversionFactor: Double, stepUpCycle: MemorySegment, stepDownCycle: MemorySegment): Unit = registerUnitWithName_abbreviation_unitToPointsConversionFactor_stepUpCycle_stepDownCycle(unitName, ObjCRuntime.newNSString(Arena.global(), abbreviation), conversionFactor, stepUpCycle, stepDownCycle)
        
    }
    
    override fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun initWithScrollView_orientation(scrollView: MemorySegment, orientation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithScrollView:orientation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, scrollView, orientation) as MemorySegment
    }
    
    open fun addMarker(marker: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addMarker:")
        ObjCRuntime.msgSend(null, ptr, sel, marker)
    }
    
    open fun removeMarker(marker: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeMarker:")
        ObjCRuntime.msgSend(null, ptr, sel, marker)
    }
    
    open fun trackMarker_withMouseEvent(marker: MemorySegment, event: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("trackMarker:withMouseEvent:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, marker, event) as Boolean
    }
    
    open fun moveRulerlineFromLocation_toLocation(oldLocation: Double, newLocation: Double): Unit {
        val sel = ObjCRuntime.sel("moveRulerlineFromLocation:toLocation:")
        ObjCRuntime.msgSend(null, ptr, sel, oldLocation, newLocation)
    }
    
    open fun invalidateHashMarks(): Unit {
        val sel = ObjCRuntime.sel("invalidateHashMarks")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun drawHashMarksAndLabelsInRect(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawHashMarksAndLabelsInRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    open fun drawMarkersInRect(rect: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawMarkersInRect:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")))
    }
    
    // @property scrollView
    open fun scrollView(): MemorySegment {
        val sel = ObjCRuntime.sel("scrollView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setScrollView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setScrollView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
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
    
    // @property baselineLocation
    open fun baselineLocation(): Double {
        val sel = ObjCRuntime.sel("baselineLocation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property requiredThickness
    open fun requiredThickness(): Double {
        val sel = ObjCRuntime.sel("requiredThickness")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property ruleThickness
    open fun ruleThickness(): Double {
        val sel = ObjCRuntime.sel("ruleThickness")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setRuleThickness(value: Double) {
        val sel = ObjCRuntime.sel("setRuleThickness:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property reservedThicknessForMarkers
    open fun reservedThicknessForMarkers(): Double {
        val sel = ObjCRuntime.sel("reservedThicknessForMarkers")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setReservedThicknessForMarkers(value: Double) {
        val sel = ObjCRuntime.sel("setReservedThicknessForMarkers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property reservedThicknessForAccessoryView
    open fun reservedThicknessForAccessoryView(): Double {
        val sel = ObjCRuntime.sel("reservedThicknessForAccessoryView")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setReservedThicknessForAccessoryView(value: Double) {
        val sel = ObjCRuntime.sel("setReservedThicknessForAccessoryView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property measurementUnits
    open fun measurementUnits(): MemorySegment {
        val sel = ObjCRuntime.sel("measurementUnits")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMeasurementUnits(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMeasurementUnits:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property originOffset
    open fun originOffset(): Double {
        val sel = ObjCRuntime.sel("originOffset")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setOriginOffset(value: Double) {
        val sel = ObjCRuntime.sel("setOriginOffset:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property clientView
    open fun clientView(): MemorySegment {
        val sel = ObjCRuntime.sel("clientView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setClientView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setClientView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property markers
    /** @return NSArray<NSRulerMarker *> * */
    open fun markers(): MemorySegment {
        val sel = ObjCRuntime.sel("markers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMarkers(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMarkers:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property accessoryView
    open fun accessoryView(): MemorySegment {
        val sel = ObjCRuntime.sel("accessoryView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAccessoryView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAccessoryView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property flipped
    override fun isFlipped(): Boolean {
        val sel = ObjCRuntime.sel("isFlipped")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

