/**
 * Re-export of kadre-core public types into the org.graphiks.kadre package.
 *
 * Consumers of the kadre facade import only from
 * `org.graphiks.kadre.*` — they do not need to know about kadre-core.
 *
 * GRA-129: re-exports via commonMain typealiases.
 */
package org.graphiks.kadre

// ── Core interfaces ──────────────────────────────────────────────────

/** @see org.graphiks.kadre.core.ApplicationHandler */
typealias ApplicationHandler = org.graphiks.kadre.core.ApplicationHandler

/** @see org.graphiks.kadre.core.ActiveEventLoop */
typealias ActiveEventLoop = org.graphiks.kadre.core.ActiveEventLoop

/** @see org.graphiks.kadre.core.EventLoopProxy */
typealias EventLoopProxy = org.graphiks.kadre.core.EventLoopProxy

/** @see org.graphiks.kadre.core.Window */
typealias Window = org.graphiks.kadre.core.Window

// ── Configuration ─────────────────────────────────────────────────────────────

/** @see org.graphiks.kadre.core.WindowAttributes */
typealias WindowAttributes = org.graphiks.kadre.core.WindowAttributes

// ── Identifiers ──────────────────────────────────────────────────────────────

/** @see org.graphiks.kadre.core.WindowId */
typealias WindowId = org.graphiks.kadre.core.WindowId

/** @see org.graphiks.kadre.core.DeviceId */
typealias DeviceId = org.graphiks.kadre.core.DeviceId

// ── Control flow ──────────────────────────────────────────────────────────

/** @see org.graphiks.kadre.core.ControlFlow */
typealias ControlFlow = org.graphiks.kadre.core.ControlFlow

/** @see org.graphiks.kadre.core.StartCause */
typealias StartCause = org.graphiks.kadre.core.StartCause

// ── Events ────────────────────────────────────────────────────────────────

/** @see org.graphiks.kadre.core.WindowEvent */
typealias WindowEvent = org.graphiks.kadre.core.WindowEvent

/** @see org.graphiks.kadre.core.DeviceEvent */
typealias DeviceEvent = org.graphiks.kadre.core.DeviceEvent

// ── Native handles ────────────────────────────────────────────────────────────

/** @see org.graphiks.kadre.core.RawWindowHandle */
typealias RawWindowHandle = org.graphiks.kadre.core.RawWindowHandle

/** @see org.graphiks.kadre.core.RawDisplayHandle */
typealias RawDisplayHandle = org.graphiks.kadre.core.RawDisplayHandle

// ── DPI types ─────────────────────────────────────────────────────────────────

/** @see org.graphiks.kadre.core.PhysicalSize */
typealias PhysicalSize<T> = org.graphiks.kadre.core.PhysicalSize<T>

/** @see org.graphiks.kadre.core.LogicalSize */
typealias LogicalSize<T> = org.graphiks.kadre.core.LogicalSize<T>

/** @see org.graphiks.kadre.core.PhysicalPosition */
typealias PhysicalPosition<T> = org.graphiks.kadre.core.PhysicalPosition<T>

/** @see org.graphiks.kadre.core.LogicalPosition */
typealias LogicalPosition<T> = org.graphiks.kadre.core.LogicalPosition<T>
