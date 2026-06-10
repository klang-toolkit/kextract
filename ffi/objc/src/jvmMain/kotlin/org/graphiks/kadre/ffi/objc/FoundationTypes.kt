/**
 * Type aliases for the Obj-C/Foundation types missing from the kextract-generated file.
 *
 * These types are standard Objective-C API aliases that are not included
 * in the generated file when filtering via --include-objc-class is used.
 *
 * Generated for kextract v0.0.2 — reduced compared to v0.0.0-test6 thanks to the bonus
 * from https://github.com/klang-toolkit/kextract/pull/23 which now emits the
 * fundamental primitive typealiases (BOOL, CGFloat, NSInteger, NSTimeInterval).
 *
 * Still to be provided manually:
 *  - The "pointer/struct" typedefs (NSPoint, NSSize, NSRect, NSWindowFrameAutosaveName, …)
 *    which are dropped by the --include-objc-class filter (see the include filter behaviour
 *    documented in the `isFoundationalTypealias` comment on the kextract side).
 *  - The [NSUInteger] override: v0.0.2 emits `typealias NSUInteger = Any` because
 *    Kotlin has no native `unsigned long` type. We replace it with [Long] —
 *    valid as long as the sign bit is not significant. Tracked upstream at
 *    https://github.com/klang-toolkit/kextract/issues/29.
 *
 * TODO: When kextract generates these types automatically, remove this file.
 */
package org.graphiks.kadre.ffi.objc

import java.lang.foreign.MemorySegment

// ── C / CoreFoundation primitives ────────────────────────────────────────────

/**
 * NSUInteger (64-bit unsigned integer on 64-bit platforms).
 *
 * kextract v0.0.2 emits `typealias NSUInteger = Any` because Kotlin has no native
 * `unsigned long`. We strip that line from `AppKit_h.kt` and override here with
 * [Long] so consumers can do arithmetic. The high bit is rarely meaningful in
 * AppKit (sizes, indices, counts).
 *
 * Upstream tracking: https://github.com/klang-toolkit/kextract/issues/29
 */
typealias NSUInteger = Long

// ── Structs passed as MemorySegment via FFM ───────────────────────────────────

/** NSPoint / CGPoint — struct {x: CGFloat, y: CGFloat} passed via MemorySegment */
typealias NSPoint = MemorySegment

/** NSSize / CGSize — struct {width: CGFloat, height: CGFloat} passed via MemorySegment */
typealias NSSize = MemorySegment

/** NSRect / CGRect — struct {origin: NSPoint, size: NSSize} passed via MemorySegment */
typealias NSRect = MemorySegment

// ── NSString typealiases ─────────────────────────────────────────────────────

/** NSWindowFrameAutosaveName (NSString *) */
typealias NSWindowFrameAutosaveName = MemorySegment

/** NSWindowPersistableFrameDescriptor (NSString *) */
typealias NSWindowPersistableFrameDescriptor = MemorySegment

/** NSWindowTabbingIdentifier (NSString *) */
typealias NSWindowTabbingIdentifier = MemorySegment

/** NSPasteboardType (NSString *) */
typealias NSPasteboardType = MemorySegment

// NSWindowLevel, NSModalResponse, NSToolTipTag — now emitted by kextract v0.0.2.

// ── NSObject protocol ────────────────────────────────────────────────────────

/**
 * Marker interface for Obj-C object wrappers.
 * All AppKit class wrappers must implement this interface
 * by exposing their [ptr] (MemorySegment to the native Obj-C object).
 */
interface NSObject {
    val ptr: MemorySegment
}
