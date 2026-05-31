/**
 * Base types used in kadre-core.
 *
 * Contains the identifiers, the control flow types, and the start causes
 * of the event loop.
 *
 * Scope: pure Kotlin types, no native reference.
 */
package org.graphiks.kadre.core

import kotlin.jvm.JvmInline

/**
 * Unique identifier of a window.
 *
 * Wraps an opaque long integer assigned by the platform.
 */
@JvmInline
value class WindowId(val value: Long)

/**
 * Unique identifier of an input device.
 *
 * Wraps an opaque long integer assigned by the platform.
 */
@JvmInline
value class DeviceId(val value: Long)

/**
 * Control of the event loop's execution flow.
 *
 * Allows the application to dictate the waiting behavior between iterations
 * of the event loop.
 */
sealed class ControlFlow {
    /** Waits indefinitely until the next event. */
    object Wait : ControlFlow()

    /** Returns immediately without waiting for an event. */
    object Poll : ControlFlow()

    /**
     * Waits until a specific instant (in milliseconds since the Unix epoch)
     * or until the next event.
     *
     * @param instant Target instant expressed in milliseconds since the Unix epoch.
     */
    data class WaitUntil(val instant: Long) : ControlFlow()
}

/**
 * Cause of the start or resumption of an event loop iteration.
 */
sealed class StartCause {
    /** The event loop has just been initialized. */
    object Init : StartCause()

    /** The event loop has been polled (Poll mode). */
    object Poll : StartCause()

    /**
     * The wait was cancelled before the planned instant.
     *
     * @param requestedResume Original target instant, or null if it was not set.
     */
    data class WaitCancelled(val requestedResume: Long? = null) : StartCause()

    /**
     * The target wait instant has been reached.
     *
     * @param requestedResume Original target instant.
     * @param start Instant at which the resumption actually occurred.
     */
    data class ResumeTimeReached(val requestedResume: Long, val start: Long) : StartCause()
}

// PhysicalSize is defined in Dpi.kt (GRA-121) — stub removed.
