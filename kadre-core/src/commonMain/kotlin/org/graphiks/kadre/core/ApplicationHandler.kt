/**
 * Main interface of the kadre application handler.
 *
 * Scope: pure Kotlin interface, no native reference.
 */
package org.graphiks.kadre.core

/**
 * Handler for the application lifecycle and the loop's events.
 *
 * The implementation of this interface is the business entry point of any
 * kadre application. The event loop invokes the methods of this handler in
 * response to system events and lifecycle state changes.
 *
 * The [canCreateSurfaces] and [windowEvent] methods are mandatory
 * (no default implementation). All other methods have an empty default
 * implementation and may be overridden as needed.
 */
interface ApplicationHandler {

    /**
     * Called when the platform allows the creation of rendering surfaces.
     *
     * Mandatory — no default implementation.
     *
     * This is the ideal moment to create windows and initialize the rendering
     * pipeline.
     *
     * @param eventLoop Active event loop, allowing windows to be created.
     */
    fun canCreateSurfaces(eventLoop: ActiveEventLoop)

    /**
     * Called when a window event is received.
     *
     * Mandatory — no default implementation.
     *
     * The window event types will be defined in GRA-123.
     *
     * @param eventLoop Active event loop.
     * @param windowId  Identifier of the window that emitted the event.
     * @param event     Received event (Any type pending GRA-123).
     */
    fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any)

    /**
     * Called when an input device event is received.
     *
     * @param eventLoop Active event loop.
     * @param deviceId  Identifier of the device that emitted the event.
     * @param event     Received event (Any type pending GRA-123).
     */
    fun deviceEvent(eventLoop: ActiveEventLoop, deviceId: DeviceId, event: Any): Unit = Unit

    /**
     * Called at the start of each event loop iteration,
     * before the accumulated events are dispatched.
     *
     * @param eventLoop  Active event loop.
     * @param startCause Cause that triggered this new iteration.
     */
    fun newEvents(eventLoop: ActiveEventLoop, startCause: StartCause): Unit = Unit

    /**
     * Called when all events of the current iteration have been dispatched
     * and the loop is about to go into a waiting state.
     *
     * @param eventLoop Active event loop.
     */
    fun aboutToWait(eventLoop: ActiveEventLoop): Unit = Unit

    /**
     * Called when the application resumes execution after a suspension.
     *
     * @param eventLoop Active event loop.
     */
    fun resumed(eventLoop: ActiveEventLoop): Unit = Unit

    /**
     * Called when the application is about to be suspended.
     *
     * @param eventLoop Active event loop.
     */
    fun suspended(eventLoop: ActiveEventLoop): Unit = Unit

    /**
     * Called when the platform requests the destruction of rendering surfaces.
     *
     * This is the ideal moment to release the graphics resources tied to the
     * surfaces before they are invalidated.
     *
     * @param eventLoop Active event loop.
     */
    fun destroySurfaces(eventLoop: ActiveEventLoop): Unit = Unit
}
