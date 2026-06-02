package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent

internal object AppKitGestureMapper {
    // NSEventType values from AppKit.NSEventType.
    internal const val EVENT_TYPE_ROTATE = 18L
    internal const val EVENT_TYPE_MAGNIFY = 30L
    internal const val EVENT_TYPE_SMART_MAGNIFY = 32L
    internal const val EVENT_TYPE_PRESSURE = 34L

    // NSEventPhase values from AppKit.NSEventPhase.
    internal const val PHASE_NONE = 0L
    internal const val PHASE_BEGAN = 1L
    internal const val PHASE_STATIONARY = 2L
    internal const val PHASE_CHANGED = 4L
    internal const val PHASE_ENDED = 8L
    internal const val PHASE_CANCELLED = 16L
    internal const val PHASE_MAY_BEGIN = 32L

    fun phase(rawPhase: Long): TouchPhase? = when (rawPhase) {
        PHASE_BEGAN -> TouchPhase.Started
        PHASE_ENDED -> TouchPhase.Ended
        PHASE_CANCELLED -> TouchPhase.Cancelled
        PHASE_CHANGED -> TouchPhase.Moved
        else -> null
    }

    fun pointerMovedPosition(
        locationXPoints: Double,
        locationYPoints: Double,
        contentWidthPoints: Double,
        contentHeightPoints: Double,
        scaleFactor: Double,
        pressedMouseButtons: Long,
    ): PhysicalPosition<Double>? {
        val isOutsideClientArea = locationXPoints.isSignNegative() ||
            locationYPoints.isSignNegative() ||
            locationXPoints > contentWidthPoints ||
            locationYPoints > contentHeightPoints

        if (isOutsideClientArea && pressedMouseButtons == 0L) {
            return null
        }

        return PhysicalPosition(
            locationXPoints * scaleFactor,
            (contentHeightPoints - locationYPoints) * scaleFactor,
        )
    }

    private fun Double.isSignNegative(): Boolean =
        java.lang.Double.doubleToRawLongBits(this) < 0L

    fun event(
        eventType: Long,
        deviceId: DeviceId?,
        phase: TouchPhase,
        magnification: Double,
        rotationDegrees: Float,
        pressure: Float,
        stage: Long,
    ): WindowEvent? = when (eventType) {
        EVENT_TYPE_MAGNIFY -> WindowEvent.PinchGesture(deviceId, magnification, phase)
        EVENT_TYPE_ROTATE -> WindowEvent.RotationGesture(deviceId, rotationDegrees, phase)
        EVENT_TYPE_SMART_MAGNIFY -> WindowEvent.DoubleTapGesture(deviceId)
        EVENT_TYPE_PRESSURE -> WindowEvent.TouchpadPressure(deviceId, pressure, stage)
        else -> null
    }
}
