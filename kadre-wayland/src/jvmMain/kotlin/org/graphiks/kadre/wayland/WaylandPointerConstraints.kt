package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.WindowRequestResult
import java.lang.foreign.MemorySegment

/**
 * Manages `zwp_pointer_constraints_v1` for pointer confinement and locking.
 *
 * Each window should hold its own instance. Only one active constraint (locked or
 * confined) is tracked at a time per instance.
 *
 * @param constraintsPtr  Address of the bound `zwp_pointer_constraints_v1*` proxy,
 *                        or 0 if the protocol extension is unavailable.
 */
internal class WaylandPointerConstraints(
    private val constraintsPtr: Long,
) {
    private var activeLockedPtr: Long = 0L
    private var activeConfinedPtr: Long = 0L

    /**
     * Initiates a pointer grab on [surfacePtr] using [pointerPtr].
     *
     * @param surfacePtr  `wl_surface*` of the window requesting the grab.
     * @param pointerPtr  `wl_pointer*` of the current seat.
     * @param mode        One of [CursorGrabMode.Confined], [CursorGrabMode.Locked],
     *                    or [CursorGrabMode.None] to release.
     */
    fun grab(
        surfacePtr: Long,
        pointerPtr: Long,
        mode: CursorGrabMode,
    ): WindowRequestResult {
        // None always succeeds (no-op release).
        if (mode == CursorGrabMode.None) {
            releaseInternal()
            return WindowRequestResult.Success
        }

        if (constraintsPtr == 0L) {
            return WindowRequestResult.Failure(
                RequestError.Unsupported("zwp_pointer_constraints_v1 not available"),
            )
        }

        // Release any active constraint first.
        releaseInternal()

        return when (mode) {
            CursorGrabMode.Confined -> doConfine(surfacePtr, pointerPtr)
            CursorGrabMode.Locked -> doLock(surfacePtr, pointerPtr)
            CursorGrabMode.None -> WindowRequestResult.Success
        }
    }

    /**
     * Releases any active pointer constraint (locked or confined).
     * Safe to call even when no constraint is active.
     */
    fun release() {
        releaseInternal()
    }

    private fun releaseInternal() {
        val voidMarshal = wlProxyMarshalFlagsVoid
        if (voidMarshal == null) {
            activeLockedPtr = 0L
            activeConfinedPtr = 0L
            return
        }
        if (activeLockedPtr != 0L) {
            try {
                voidMarshal.invokeExact(
                    MemorySegment.ofAddress(activeLockedPtr),
                    0,                          // opcode: destroy
                    MemorySegment.NULL,          // wl_interface* (NULL)
                    1,                          // version
                    0,                          // flags
                )
            } catch (_: Throwable) { }
            activeLockedPtr = 0L
        }
        if (activeConfinedPtr != 0L) {
            try {
                voidMarshal.invokeExact(
                    MemorySegment.ofAddress(activeConfinedPtr),
                    0,                          // opcode: destroy
                    MemorySegment.NULL,          // wl_interface* (NULL)
                    1,                          // version
                    0,                          // flags
                )
            } catch (_: Throwable) { }
            activeConfinedPtr = 0L
        }
    }

    private fun doLock(surfacePtr: Long, pointerPtr: Long): WindowRequestResult {
        val marshal = wlPointerConstraintsLockPointer ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("wl_proxy_marshal_flags not available"),
        )
        val lockedIface = zwpLockedPointerV1Interface
        return try {
            val result = marshal.invokeExact(
                MemorySegment.ofAddress(constraintsPtr),
                1,                                  // opcode: lock_pointer
                lockedIface,
                1,                                  // version
                0,                                  // flags
                MemorySegment.ofAddress(surfacePtr),
                MemorySegment.ofAddress(pointerPtr),
                MemorySegment.NULL,                 // region = NULL
                ZWP_POINTER_CONSTRAINTS_V1_LIFETIME_PERSISTENT,
                MemorySegment.NULL,                 // new_id = NULL
            ) as MemorySegment
            activeLockedPtr = result.address()
            if (activeLockedPtr == 0L) {
                WindowRequestResult.Failure(RequestError.Unsupported("zwp_locked_pointer_v1 creation failed"))
            } else {
                WindowRequestResult.Success
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.Unsupported("lock_pointer failed: $t"))
        }
    }

    private fun doConfine(surfacePtr: Long, pointerPtr: Long): WindowRequestResult {
        val marshal = wlPointerConstraintsConfinePointer ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("wl_proxy_marshal_flags not available"),
        )
        val confinedIface = zwpConfinedPointerV1Interface
        return try {
            val result = marshal.invokeExact(
                MemorySegment.ofAddress(constraintsPtr),
                2,                                  // opcode: confine_pointer
                confinedIface,
                1,                                  // version
                0,                                  // flags
                MemorySegment.ofAddress(surfacePtr),
                MemorySegment.ofAddress(pointerPtr),
                MemorySegment.NULL,                 // region = NULL
                ZWP_POINTER_CONSTRAINTS_V1_LIFETIME_PERSISTENT,
                MemorySegment.NULL,                 // new_id = NULL
            ) as MemorySegment
            activeConfinedPtr = result.address()
            if (activeConfinedPtr == 0L) {
                WindowRequestResult.Failure(RequestError.Unsupported("zwp_confined_pointer_v1 creation failed"))
            } else {
                WindowRequestResult.Success
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.Unsupported("confine_pointer failed: $t"))
        }
    }
}
