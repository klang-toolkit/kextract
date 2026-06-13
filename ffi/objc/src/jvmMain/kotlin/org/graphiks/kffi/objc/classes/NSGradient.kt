package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSGradient
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSGradient(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSGradient") }
        
    }
    
    open fun initWithStartingColor_endingColor(startingColor: MemorySegment, endingColor: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithStartingColor:endingColor:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, startingColor, endingColor) as MemorySegment
    }
    
    open fun initWithColors(colorArray: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithColors:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, colorArray) as MemorySegment
    }
    
    open fun initWithColorsAndLocations(firstColor: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithColorsAndLocations:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, firstColor) as MemorySegment
    }
    
    open fun initWithColors_atLocations_colorSpace(colorArray: MemorySegment, locations: MemorySegment, colorSpace: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithColors:atLocations:colorSpace:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, colorArray, locations, colorSpace) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun drawFromPoint_toPoint_options(startingPoint: MemorySegment, endingPoint: MemorySegment, options: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawFromPoint:toPoint:options:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(startingPoint, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), ObjCRuntime.ObjCStructArg(endingPoint, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), options)
    }
    
    open fun drawInRect_angle(rect: MemorySegment, angle: Double): Unit {
        val sel = ObjCRuntime.sel("drawInRect:angle:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), angle)
    }
    
    open fun drawInBezierPath_angle(path: MemorySegment, angle: Double): Unit {
        val sel = ObjCRuntime.sel("drawInBezierPath:angle:")
        ObjCRuntime.msgSend(null, ptr, sel, path, angle)
    }
    
    open fun drawFromCenter_radius_toCenter_radius_options(startCenter: MemorySegment, startRadius: Double, endCenter: MemorySegment, endRadius: Double, options: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawFromCenter:radius:toCenter:radius:options:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(startCenter, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), startRadius, ObjCRuntime.ObjCStructArg(endCenter, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), endRadius, options)
    }
    
    open fun drawInRect_relativeCenterPosition(rect: MemorySegment, relativeCenterPosition: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawInRect:relativeCenterPosition:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(relativeCenterPosition, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun drawInBezierPath_relativeCenterPosition(path: MemorySegment, relativeCenterPosition: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawInBezierPath:relativeCenterPosition:")
        ObjCRuntime.msgSend(null, ptr, sel, path, ObjCRuntime.ObjCStructArg(relativeCenterPosition, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")))
    }
    
    open fun getColor_location_atIndex(color: MemorySegment, location: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("getColor:location:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, color, location, index)
    }
    
    open fun interpolatedColorAtLocation(location: Double): MemorySegment {
        val sel = ObjCRuntime.sel("interpolatedColorAtLocation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, location) as MemorySegment
    }
    
    // @property colorSpace
    open fun colorSpace(): MemorySegment {
        val sel = ObjCRuntime.sel("colorSpace")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property numberOfColorStops
    open fun numberOfColorStops(): Long {
        val sel = ObjCRuntime.sel("numberOfColorStops")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
}

