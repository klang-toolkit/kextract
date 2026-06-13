package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSEvent
 * Superclass: NSObject
 * Protocols: NSCopying, NSCoding
 */
open class NSEvent(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSEvent") }
        
        fun eventWithEventRef(eventRef: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("eventWithEventRef:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, eventRef) as MemorySegment
        }
        
        fun eventWithCGEvent(cgEvent: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("eventWithCGEvent:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, cgEvent) as MemorySegment
        }
        
        fun startPeriodicEventsAfterDelay_withPeriod(delay: Double, period: Double): Unit {
            val sel = ObjCRuntime.sel("startPeriodicEventsAfterDelay:withPeriod:")
            ObjCRuntime.msgSend(null, _class, sel, delay, period)
        }
        
        fun stopPeriodicEvents(): Unit {
            val sel = ObjCRuntime.sel("stopPeriodicEvents")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
        fun mouseEventWithType_location_modifierFlags_timestamp_windowNumber_context_eventNumber_clickCount_pressure(type: MemorySegment, location: MemorySegment, flags: MemorySegment, time: Double, wNum: Long, unusedPassNil: MemorySegment, eNum: Long, cNum: Long, pressure: Float): MemorySegment {
            val sel = ObjCRuntime.sel("mouseEventWithType:location:modifierFlags:timestamp:windowNumber:context:eventNumber:clickCount:pressure:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, type, ObjCRuntime.ObjCStructArg(location, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), flags, time, wNum, unusedPassNil, eNum, cNum, pressure) as MemorySegment
        }
        
        fun keyEventWithType_location_modifierFlags_timestamp_windowNumber_context_characters_charactersIgnoringModifiers_isARepeat_keyCode(type: MemorySegment, location: MemorySegment, flags: MemorySegment, time: Double, wNum: Long, unusedPassNil: MemorySegment, keys: MemorySegment, ukeys: MemorySegment, flag: Boolean, code: Short): MemorySegment {
            val sel = ObjCRuntime.sel("keyEventWithType:location:modifierFlags:timestamp:windowNumber:context:characters:charactersIgnoringModifiers:isARepeat:keyCode:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, type, ObjCRuntime.ObjCStructArg(location, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), flags, time, wNum, unusedPassNil, keys, ukeys, flag, code) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun keyEventWithType_location_modifierFlags_timestamp_windowNumber_context_characters_charactersIgnoringModifiers_isARepeat_keyCode(type: MemorySegment, location: MemorySegment, flags: MemorySegment, time: Double, wNum: Long, unusedPassNil: MemorySegment, keys: String, ukeys: String, flag: Boolean, code: Short): MemorySegment = keyEventWithType_location_modifierFlags_timestamp_windowNumber_context_characters_charactersIgnoringModifiers_isARepeat_keyCode(type, location, flags, time, wNum, unusedPassNil, ObjCRuntime.newNSString(Arena.global(), keys), ObjCRuntime.newNSString(Arena.global(), ukeys), flag, code)
        
        fun enterExitEventWithType_location_modifierFlags_timestamp_windowNumber_context_eventNumber_trackingNumber_userData(type: MemorySegment, location: MemorySegment, flags: MemorySegment, time: Double, wNum: Long, unusedPassNil: MemorySegment, eNum: Long, tNum: Long, `data`: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("enterExitEventWithType:location:modifierFlags:timestamp:windowNumber:context:eventNumber:trackingNumber:userData:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, type, ObjCRuntime.ObjCStructArg(location, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), flags, time, wNum, unusedPassNil, eNum, tNum, `data`) as MemorySegment
        }
        
        fun otherEventWithType_location_modifierFlags_timestamp_windowNumber_context_subtype_data1_data2(type: MemorySegment, location: MemorySegment, flags: MemorySegment, time: Double, wNum: Long, unusedPassNil: MemorySegment, subtype: Short, d1: Long, d2: Long): MemorySegment {
            val sel = ObjCRuntime.sel("otherEventWithType:location:modifierFlags:timestamp:windowNumber:context:subtype:data1:data2:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, type, ObjCRuntime.ObjCStructArg(location, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), flags, time, wNum, unusedPassNil, subtype, d1, d2) as MemorySegment
        }
        
        fun addGlobalMonitorForEventsMatchingMask_handler(mask: MemorySegment, block: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("addGlobalMonitorForEventsMatchingMask:handler:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, mask, block) as MemorySegment
        }
        
        fun addLocalMonitorForEventsMatchingMask_handler(mask: MemorySegment, block: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("addLocalMonitorForEventsMatchingMask:handler:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, mask, block) as MemorySegment
        }
        
        fun removeMonitor(eventMonitor: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("removeMonitor:")
            ObjCRuntime.msgSend(null, _class, sel, eventMonitor)
        }
        
        fun isMouseCoalescingEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isMouseCoalescingEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun setMouseCoalescingEnabled(mouseCoalescingEnabled: Boolean): Unit {
            val sel = ObjCRuntime.sel("setMouseCoalescingEnabled:")
            ObjCRuntime.msgSend(null, _class, sel, mouseCoalescingEnabled)
        }
        
        fun isSwipeTrackingFromScrollEventsEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isSwipeTrackingFromScrollEventsEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun mouseLocation(): MemorySegment {
            val sel = ObjCRuntime.sel("mouseLocation")
            return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), _class, sel) as MemorySegment
        }
        
        fun modifierFlags(): MemorySegment {
            val sel = ObjCRuntime.sel("modifierFlags")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun pressedMouseButtons(): Long {
            val sel = ObjCRuntime.sel("pressedMouseButtons")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, _class, sel) as Long
        }
        
        fun doubleClickInterval(): Double {
            val sel = ObjCRuntime.sel("doubleClickInterval")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel) as Double
        }
        
        fun keyRepeatDelay(): Double {
            val sel = ObjCRuntime.sel("keyRepeatDelay")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel) as Double
        }
        
        fun keyRepeatInterval(): Double {
            val sel = ObjCRuntime.sel("keyRepeatInterval")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel) as Double
        }
        
    }
    
    open fun charactersByApplyingModifiers(modifiers: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("charactersByApplyingModifiers:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, modifiers) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun charactersByApplyingModifiersAsString(modifiers: MemorySegment): String = ObjCRuntime.toJavaString(charactersByApplyingModifiers(modifiers))
    
    /** @return NSSet<NSTouch *> * */
    open fun touchesMatchingPhase_inView(phase: MemorySegment, view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("touchesMatchingPhase:inView:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, phase, view) as MemorySegment
    }
    
    /** @return NSSet<NSTouch *> * */
    open fun allTouches(): MemorySegment {
        val sel = ObjCRuntime.sel("allTouches")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** @return NSSet<NSTouch *> * */
    open fun touchesForView(view: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("touchesForView:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, view) as MemorySegment
    }
    
    /** @return NSArray<NSTouch *> * */
    open fun coalescedTouchesForTouch(touch: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("coalescedTouchesForTouch:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, touch) as MemorySegment
    }
    
    open fun trackSwipeEventWithOptions_dampenAmountThresholdMin_max_usingHandler(options: MemorySegment, minDampenThreshold: Double, maxDampenThreshold: Double, trackingHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("trackSwipeEventWithOptions:dampenAmountThresholdMin:max:usingHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, options, minDampenThreshold, maxDampenThreshold, trackingHandler)
    }
    
    // @property type
    open fun type(): MemorySegment {
        val sel = ObjCRuntime.sel("type")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property modifierFlags
    open fun modifierFlags(): MemorySegment {
        val sel = ObjCRuntime.sel("modifierFlags")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property timestamp
    open fun timestamp(): Double {
        val sel = ObjCRuntime.sel("timestamp")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property window
    open fun window(): MemorySegment {
        val sel = ObjCRuntime.sel("window")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property windowNumber
    open fun windowNumber(): Long {
        val sel = ObjCRuntime.sel("windowNumber")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property context
    open fun context(): MemorySegment {
        val sel = ObjCRuntime.sel("context")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property clickCount
    open fun clickCount(): Long {
        val sel = ObjCRuntime.sel("clickCount")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property buttonNumber
    open fun buttonNumber(): Long {
        val sel = ObjCRuntime.sel("buttonNumber")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property eventNumber
    open fun eventNumber(): Long {
        val sel = ObjCRuntime.sel("eventNumber")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property pressure
    open fun pressure(): Float {
        val sel = ObjCRuntime.sel("pressure")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    
    // @property locationInWindow
    open fun locationInWindow(): MemorySegment {
        val sel = ObjCRuntime.sel("locationInWindow")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel) as MemorySegment
    }
    
    // @property deltaX
    open fun deltaX(): Double {
        val sel = ObjCRuntime.sel("deltaX")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property deltaY
    open fun deltaY(): Double {
        val sel = ObjCRuntime.sel("deltaY")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property deltaZ
    open fun deltaZ(): Double {
        val sel = ObjCRuntime.sel("deltaZ")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property hasPreciseScrollingDeltas
    open fun hasPreciseScrollingDeltas(): Boolean {
        val sel = ObjCRuntime.sel("hasPreciseScrollingDeltas")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property scrollingDeltaX
    open fun scrollingDeltaX(): Double {
        val sel = ObjCRuntime.sel("scrollingDeltaX")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property scrollingDeltaY
    open fun scrollingDeltaY(): Double {
        val sel = ObjCRuntime.sel("scrollingDeltaY")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property momentumPhase
    open fun momentumPhase(): MemorySegment {
        val sel = ObjCRuntime.sel("momentumPhase")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property directionInvertedFromDevice
    open fun isDirectionInvertedFromDevice(): Boolean {
        val sel = ObjCRuntime.sel("isDirectionInvertedFromDevice")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property characters
    open fun characters(): MemorySegment {
        val sel = ObjCRuntime.sel("characters")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun charactersAsString(): String = ObjCRuntime.toJavaString(characters())
    
    // @property charactersIgnoringModifiers
    open fun charactersIgnoringModifiers(): MemorySegment {
        val sel = ObjCRuntime.sel("charactersIgnoringModifiers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun charactersIgnoringModifiersAsString(): String = ObjCRuntime.toJavaString(charactersIgnoringModifiers())
    
    // @property ARepeat
    open fun isARepeat(): Boolean {
        val sel = ObjCRuntime.sel("isARepeat")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property keyCode
    open fun keyCode(): Short {
        val sel = ObjCRuntime.sel("keyCode")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, ptr, sel) as Short
    }
    
    // @property trackingNumber
    open fun trackingNumber(): Long {
        val sel = ObjCRuntime.sel("trackingNumber")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property userData
    open fun userData(): MemorySegment {
        val sel = ObjCRuntime.sel("userData")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property trackingArea
    open fun trackingArea(): MemorySegment {
        val sel = ObjCRuntime.sel("trackingArea")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property subtype
    open fun subtype(): MemorySegment {
        val sel = ObjCRuntime.sel("subtype")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property data1
    open fun data1(): Long {
        val sel = ObjCRuntime.sel("data1")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property data2
    open fun data2(): Long {
        val sel = ObjCRuntime.sel("data2")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property eventRef
    open fun eventRef(): MemorySegment {
        val sel = ObjCRuntime.sel("eventRef")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property CGEvent
    open fun CGEvent(): MemorySegment {
        val sel = ObjCRuntime.sel("CGEvent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property mouseCoalescingEnabled
    open fun isMouseCoalescingEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isMouseCoalescingEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setMouseCoalescingEnabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setMouseCoalescingEnabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property magnification
    open fun magnification(): Double {
        val sel = ObjCRuntime.sel("magnification")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property deviceID
    open fun deviceID(): Long {
        val sel = ObjCRuntime.sel("deviceID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property rotation
    open fun rotation(): Float {
        val sel = ObjCRuntime.sel("rotation")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    
    // @property absoluteX
    open fun absoluteX(): Long {
        val sel = ObjCRuntime.sel("absoluteX")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property absoluteY
    open fun absoluteY(): Long {
        val sel = ObjCRuntime.sel("absoluteY")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property absoluteZ
    open fun absoluteZ(): Long {
        val sel = ObjCRuntime.sel("absoluteZ")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property buttonMask
    open fun buttonMask(): MemorySegment {
        val sel = ObjCRuntime.sel("buttonMask")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property tilt
    open fun tilt(): MemorySegment {
        val sel = ObjCRuntime.sel("tilt")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel) as MemorySegment
    }
    
    // @property tangentialPressure
    open fun tangentialPressure(): Float {
        val sel = ObjCRuntime.sel("tangentialPressure")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    
    // @property vendorDefined
    open fun vendorDefined(): MemorySegment {
        val sel = ObjCRuntime.sel("vendorDefined")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property vendorID
    open fun vendorID(): Long {
        val sel = ObjCRuntime.sel("vendorID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property tabletID
    open fun tabletID(): Long {
        val sel = ObjCRuntime.sel("tabletID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property pointingDeviceID
    open fun pointingDeviceID(): Long {
        val sel = ObjCRuntime.sel("pointingDeviceID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property systemTabletID
    open fun systemTabletID(): Long {
        val sel = ObjCRuntime.sel("systemTabletID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property vendorPointingDeviceType
    open fun vendorPointingDeviceType(): Long {
        val sel = ObjCRuntime.sel("vendorPointingDeviceType")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property pointingDeviceSerialNumber
    open fun pointingDeviceSerialNumber(): Long {
        val sel = ObjCRuntime.sel("pointingDeviceSerialNumber")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property uniqueID
    open fun uniqueID(): Long {
        val sel = ObjCRuntime.sel("uniqueID")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property capabilityMask
    open fun capabilityMask(): Long {
        val sel = ObjCRuntime.sel("capabilityMask")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property pointingDeviceType
    open fun pointingDeviceType(): MemorySegment {
        val sel = ObjCRuntime.sel("pointingDeviceType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property enteringProximity
    open fun isEnteringProximity(): Boolean {
        val sel = ObjCRuntime.sel("isEnteringProximity")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property phase
    open fun phase(): MemorySegment {
        val sel = ObjCRuntime.sel("phase")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property stage
    open fun stage(): Long {
        val sel = ObjCRuntime.sel("stage")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property stageTransition
    open fun stageTransition(): Double {
        val sel = ObjCRuntime.sel("stageTransition")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property associatedEventsMask
    open fun associatedEventsMask(): MemorySegment {
        val sel = ObjCRuntime.sel("associatedEventsMask")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property pressureBehavior
    open fun pressureBehavior(): MemorySegment {
        val sel = ObjCRuntime.sel("pressureBehavior")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property swipeTrackingFromScrollEventsEnabled
    open fun isSwipeTrackingFromScrollEventsEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isSwipeTrackingFromScrollEventsEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property mouseLocation
    open fun mouseLocation(): MemorySegment {
        val sel = ObjCRuntime.sel("mouseLocation")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel) as MemorySegment
    }
    
    // @property pressedMouseButtons
    open fun pressedMouseButtons(): Long {
        val sel = ObjCRuntime.sel("pressedMouseButtons")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property doubleClickInterval
    open fun doubleClickInterval(): Double {
        val sel = ObjCRuntime.sel("doubleClickInterval")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property keyRepeatDelay
    open fun keyRepeatDelay(): Double {
        val sel = ObjCRuntime.sel("keyRepeatDelay")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property keyRepeatInterval
    open fun keyRepeatInterval(): Double {
        val sel = ObjCRuntime.sel("keyRepeatInterval")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
}

