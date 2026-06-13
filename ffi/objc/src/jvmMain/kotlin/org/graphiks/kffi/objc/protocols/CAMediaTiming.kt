package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: CAMediaTiming
 */
interface CAMediaTiming {
    fun beginTime(): Double
    
    fun setBeginTime(beginTime: Double): Unit
    
    fun duration(): Double
    
    fun setDuration(duration: Double): Unit
    
    fun speed(): Float
    
    fun setSpeed(speed: Float): Unit
    
    fun timeOffset(): Double
    
    fun setTimeOffset(timeOffset: Double): Unit
    
    fun repeatCount(): Float
    
    fun setRepeatCount(repeatCount: Float): Unit
    
    fun repeatDuration(): Double
    
    fun setRepeatDuration(repeatDuration: Double): Unit
    
    fun autoreverses(): Boolean
    
    fun setAutoreverses(autoreverses: Boolean): Unit
    
    fun fillMode(): MemorySegment
    
    fun setFillMode(fillMode: MemorySegment): Unit
    
}

