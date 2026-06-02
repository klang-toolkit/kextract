package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent
import kotlin.math.PI

internal enum class UIKitGestureState {
    Began,
    Changed,
    Ended,
    Cancelled,
    Failed,
}

internal class UIKitGestureMapper {
    private var pinchLastScale = 0.0
    private var rotationLastRadians = 0.0
    private var panLastX = 0.0
    private var panLastY = 0.0

    fun pinch(state: UIKitGestureState, scale: Double): WindowEvent.PinchGesture {
        val (phase, delta) = when (state) {
            UIKitGestureState.Began -> {
                pinchLastScale = scale
                TouchPhase.Started to 0.0
            }
            UIKitGestureState.Changed -> {
                val lastScale = pinchLastScale
                pinchLastScale = scale
                TouchPhase.Moved to scale - lastScale
            }
            UIKitGestureState.Ended -> {
                val lastScale = pinchLastScale
                pinchLastScale = 0.0
                TouchPhase.Moved to scale - lastScale
            }
            UIKitGestureState.Cancelled, UIKitGestureState.Failed -> {
                pinchLastScale = 0.0
                TouchPhase.Cancelled to -scale
            }
        }
        return WindowEvent.PinchGesture(deviceId = null, delta = delta, phase = phase)
    }

    fun rotation(state: UIKitGestureState, radians: Double): WindowEvent.RotationGesture {
        val (phase, deltaRadians) = when (state) {
            UIKitGestureState.Began -> {
                rotationLastRadians = 0.0
                TouchPhase.Started to 0.0
            }
            UIKitGestureState.Changed -> {
                val lastRotation = rotationLastRadians
                rotationLastRadians = radians
                TouchPhase.Moved to radians - lastRotation
            }
            UIKitGestureState.Ended -> {
                val lastRotation = rotationLastRadians
                rotationLastRadians = 0.0
                TouchPhase.Ended to radians - lastRotation
            }
            UIKitGestureState.Cancelled, UIKitGestureState.Failed -> {
                rotationLastRadians = 0.0
                TouchPhase.Cancelled to -radians
            }
        }
        return WindowEvent.RotationGesture(
            deviceId = null,
            deltaDegrees = (-deltaRadians * 180.0 / PI).toFloat(),
            phase = phase,
        )
    }

    fun pan(state: UIKitGestureState, x: Double, y: Double): WindowEvent.PanGesture {
        val (phase, dx, dy) = when (state) {
            UIKitGestureState.Began -> {
                panLastX = x
                panLastY = y
                Triple(TouchPhase.Started, 0.0, 0.0)
            }
            UIKitGestureState.Changed -> {
                val lastX = panLastX
                val lastY = panLastY
                panLastX = x
                panLastY = y
                Triple(TouchPhase.Moved, x - lastX, y - lastY)
            }
            UIKitGestureState.Ended -> {
                val lastX = panLastX
                val lastY = panLastY
                panLastX = 0.0
                panLastY = 0.0
                Triple(TouchPhase.Ended, x - lastX, y - lastY)
            }
            UIKitGestureState.Cancelled, UIKitGestureState.Failed -> {
                val lastX = panLastX
                val lastY = panLastY
                panLastX = 0.0
                panLastY = 0.0
                Triple(TouchPhase.Cancelled, -lastX, -lastY)
            }
        }
        return WindowEvent.PanGesture(
            deviceId = null,
            delta = PhysicalPosition(dx.toFloat(), dy.toFloat()),
            phase = phase,
        )
    }
}
