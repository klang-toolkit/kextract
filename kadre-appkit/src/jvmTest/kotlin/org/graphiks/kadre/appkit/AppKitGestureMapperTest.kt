package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull

class AppKitGestureMapperTest {
    @Test
    fun `NSEventPhase maps to Kadre touch phases`() {
        assertEquals(TouchPhase.Started, AppKitGestureMapper.phase(AppKitGestureMapper.PHASE_BEGAN))
        assertEquals(TouchPhase.Moved, AppKitGestureMapper.phase(AppKitGestureMapper.PHASE_CHANGED))
        assertEquals(TouchPhase.Ended, AppKitGestureMapper.phase(AppKitGestureMapper.PHASE_ENDED))
        assertEquals(TouchPhase.Cancelled, AppKitGestureMapper.phase(AppKitGestureMapper.PHASE_CANCELLED))
        assertNull(AppKitGestureMapper.phase(AppKitGestureMapper.PHASE_MAY_BEGIN))
        assertNull(AppKitGestureMapper.phase(AppKitGestureMapper.PHASE_STATIONARY))
        assertNull(AppKitGestureMapper.phase(AppKitGestureMapper.PHASE_NONE))
        assertNull(AppKitGestureMapper.phase(999L))
    }

    @Test
    fun `magnify maps to pinch gesture`() {
        val event = AppKitGestureMapper.event(
            eventType = AppKitGestureMapper.EVENT_TYPE_MAGNIFY,
            deviceId = DeviceId(7L),
            phase = TouchPhase.Moved,
            magnification = 0.25,
            rotationDegrees = 0f,
            pressure = 0f,
            stage = 0L,
        )

        assertIs<WindowEvent.PinchGesture>(event)
        assertEquals(DeviceId(7L), event.deviceId)
        assertEquals(0.25, event.delta)
        assertEquals(TouchPhase.Moved, event.phase)
    }

    @Test
    fun `rotate maps to rotation gesture in degrees`() {
        val event = AppKitGestureMapper.event(
            eventType = AppKitGestureMapper.EVENT_TYPE_ROTATE,
            deviceId = DeviceId(7L),
            phase = TouchPhase.Moved,
            magnification = 0.0,
            rotationDegrees = -12.5f,
            pressure = 0f,
            stage = 0L,
        )

        assertIs<WindowEvent.RotationGesture>(event)
        assertEquals(DeviceId(7L), event.deviceId)
        assertEquals(-12.5f, event.deltaDegrees)
        assertEquals(TouchPhase.Moved, event.phase)
    }

    @Test
    fun `smart magnify maps to double tap gesture`() {
        val event = AppKitGestureMapper.event(
            eventType = AppKitGestureMapper.EVENT_TYPE_SMART_MAGNIFY,
            deviceId = DeviceId(7L),
            phase = TouchPhase.Moved,
            magnification = 0.0,
            rotationDegrees = 0f,
            pressure = 0f,
            stage = 0L,
        )

        assertIs<WindowEvent.DoubleTapGesture>(event)
        assertEquals(DeviceId(7L), event.deviceId)
    }

    @Test
    fun `pressure maps to touchpad pressure`() {
        val event = AppKitGestureMapper.event(
            eventType = AppKitGestureMapper.EVENT_TYPE_PRESSURE,
            deviceId = DeviceId(7L),
            phase = TouchPhase.Moved,
            magnification = 0.0,
            rotationDegrees = 0f,
            pressure = 0.75f,
            stage = 2L,
        )

        assertIs<WindowEvent.TouchpadPressure>(event)
        assertEquals(DeviceId(7L), event.deviceId)
        assertEquals(0.75f, event.pressure)
        assertEquals(2L, event.stage)
    }

    @Test
    fun `unsupported AppKit gesture type maps to null`() {
        assertNull(
            AppKitGestureMapper.event(
                eventType = 999L,
                deviceId = DeviceId(7L),
                phase = TouchPhase.Moved,
                magnification = 0.0,
                rotationDegrees = 0f,
                pressure = 0f,
                stage = 0L,
            )
        )
    }

    @Test
    fun `gesture pointer move is suppressed outside client area when no mouse button is down`() {
        assertNull(
            AppKitGestureMapper.pointerMovedPosition(
                locationXPoints = -1.0,
                locationYPoints = 10.0,
                contentWidthPoints = 100.0,
                contentHeightPoints = 50.0,
                scaleFactor = 2.0,
                pressedMouseButtons = 0L,
            )
        )
        assertNull(
            AppKitGestureMapper.pointerMovedPosition(
                locationXPoints = -0.0,
                locationYPoints = 10.0,
                contentWidthPoints = 100.0,
                contentHeightPoints = 50.0,
                scaleFactor = 2.0,
                pressedMouseButtons = 0L,
            )
        )
        assertNull(
            AppKitGestureMapper.pointerMovedPosition(
                locationXPoints = 10.0,
                locationYPoints = 51.0,
                contentWidthPoints = 100.0,
                contentHeightPoints = 50.0,
                scaleFactor = 2.0,
                pressedMouseButtons = 0L,
            )
        )
    }

    @Test
    fun `gesture pointer move is kept outside client area when a mouse button is down`() {
        val position = AppKitGestureMapper.pointerMovedPosition(
            locationXPoints = -1.0,
            locationYPoints = 10.0,
            contentWidthPoints = 100.0,
            contentHeightPoints = 50.0,
            scaleFactor = 2.0,
            pressedMouseButtons = 1L,
        )

        assertEquals(-2.0, position?.x)
        assertEquals(80.0, position?.y)
    }

    @Test
    fun `gesture pointer move converts client points to physical top-left coordinates`() {
        val position = AppKitGestureMapper.pointerMovedPosition(
            locationXPoints = 12.5,
            locationYPoints = 10.0,
            contentWidthPoints = 100.0,
            contentHeightPoints = 50.0,
            scaleFactor = 2.0,
            pressedMouseButtons = 0L,
        )

        assertEquals(25.0, position?.x)
        assertEquals(80.0, position?.y)
    }
}
