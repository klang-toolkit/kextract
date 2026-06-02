package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.TouchPhase
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class UIKitGestureMapperTest {
    @Test
    fun `pinch matches winit phases and incremental deltas`() {
        val mapper = UIKitGestureMapper()

        mapper.pinch(UIKitGestureState.Began, scale = 1.2).also {
            assertEquals(TouchPhase.Started, it.phase)
            assertEquals(0.0, it.delta)
        }
        mapper.pinch(UIKitGestureState.Changed, scale = 1.5).also {
            assertEquals(TouchPhase.Moved, it.phase)
            assertEquals(0.3, it.delta, absoluteTolerance = 0.000001)
        }
        mapper.pinch(UIKitGestureState.Ended, scale = 1.7).also {
            assertEquals(TouchPhase.Moved, it.phase)
            assertEquals(0.2, it.delta, absoluteTolerance = 0.000001)
        }
    }

    @Test
    fun `pinch cancellation reverses recognizer scale like winit`() {
        val mapper = UIKitGestureMapper()

        mapper.pinch(UIKitGestureState.Began, scale = 1.1)
        mapper.pinch(UIKitGestureState.Cancelled, scale = 1.4).also {
            assertEquals(TouchPhase.Cancelled, it.phase)
            assertEquals(-1.4, it.delta)
        }
    }

    @Test
    fun `rotation matches winit sign phase and incremental degrees`() {
        val mapper = UIKitGestureMapper()

        mapper.rotation(UIKitGestureState.Began, radians = 0.25).also {
            assertEquals(TouchPhase.Started, it.phase)
            assertEquals(0f, it.deltaDegrees, absoluteTolerance = 0.0001f)
        }
        mapper.rotation(UIKitGestureState.Changed, radians = PI / 2).also {
            assertEquals(TouchPhase.Moved, it.phase)
            assertEquals(-90f, it.deltaDegrees, absoluteTolerance = 0.0001f)
        }
        mapper.rotation(UIKitGestureState.Ended, radians = PI).also {
            assertEquals(TouchPhase.Ended, it.phase)
            assertEquals(-90f, it.deltaDegrees, absoluteTolerance = 0.0001f)
        }
    }

    @Test
    fun `rotation cancellation reverses current rotation then applies winit sign`() {
        val mapper = UIKitGestureMapper()

        mapper.rotation(UIKitGestureState.Began, radians = 0.0)
        mapper.rotation(UIKitGestureState.Cancelled, radians = PI / 2).also {
            assertEquals(TouchPhase.Cancelled, it.phase)
            assertEquals(90f, it.deltaDegrees, absoluteTolerance = 0.0001f)
        }
    }

    @Test
    fun `pan matches winit points and incremental deltas`() {
        val mapper = UIKitGestureMapper()

        mapper.pan(UIKitGestureState.Began, x = 10.0, y = 20.0).also {
            assertEquals(TouchPhase.Started, it.phase)
            assertEquals(0f, it.delta.x)
            assertEquals(0f, it.delta.y)
        }
        mapper.pan(UIKitGestureState.Changed, x = 14.0, y = 18.0).also {
            assertEquals(TouchPhase.Moved, it.phase)
            assertEquals(4f, it.delta.x)
            assertEquals(-2f, it.delta.y)
        }
        mapper.pan(UIKitGestureState.Ended, x = 15.5, y = 21.0).also {
            assertEquals(TouchPhase.Ended, it.phase)
            assertEquals(1.5f, it.delta.x)
            assertEquals(3f, it.delta.y)
        }
    }

    @Test
    fun `pan cancellation reverses the last stored translation like winit`() {
        val mapper = UIKitGestureMapper()

        mapper.pan(UIKitGestureState.Began, x = 10.0, y = 20.0)
        mapper.pan(UIKitGestureState.Changed, x = 14.0, y = 18.0)
        mapper.pan(UIKitGestureState.Cancelled, x = 30.0, y = 40.0).also {
            assertEquals(TouchPhase.Cancelled, it.phase)
            assertEquals(-14f, it.delta.x)
            assertEquals(-18f, it.delta.y)
        }
    }
}
