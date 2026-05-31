/**
 * Type aliases pour les types Obj-C/Foundation manquants dans le fichier généré par kextract.
 *
 * Ces types sont des alias standard de l'API Objective-C qui ne sont pas inclus
 * dans le fichier généré lorsque le filtrage par --include-objc-class est utilisé.
 *
 * Généré pour kextract v0.0.2 — réduit par rapport à v0.0.0-test6 grâce au bonus
 * de https://github.com/klang-toolkit/kextract/pull/23 qui émet désormais les
 * typealiases primitifs fondamentaux (BOOL, CGFloat, NSInteger, NSTimeInterval).
 *
 * Reste à fournir manuellement :
 *  - Les typedefs « pointer/struct » (NSPoint, NSSize, NSRect, NSWindowFrameAutosaveName, …)
 *    qui sont écartés par le filtre --include-objc-class (cf. include filter behaviour
 *    documenté dans le commentaire du `isFoundationalTypealias` côté kextract).
 *  - L'override de [NSUInteger] : la v0.0.2 émet `typealias NSUInteger = Any` car
 *    Kotlin n'a pas de type `unsigned long` natif. On le remplace par [Long] —
 *    valable tant que le bit de signe n'est pas significatif. Tracé upstream
 *    https://github.com/klang-toolkit/kextract/issues/29.
 *
 * TODO: Quand kextract génèrera ces types automatiquement, supprimer ce fichier.
 */
package org.graphiks.kadre.appkit.bindings

import java.lang.foreign.MemorySegment

// ── Primitives C / CoreFoundation ────────────────────────────────────────────

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

// ── Structs passés comme MemorySegment via FFM ────────────────────────────────

/** NSPoint / CGPoint — struct {x: CGFloat, y: CGFloat} passé via MemorySegment */
typealias NSPoint = MemorySegment

/** NSSize / CGSize — struct {width: CGFloat, height: CGFloat} passé via MemorySegment */
typealias NSSize = MemorySegment

/** NSRect / CGRect — struct {origin: NSPoint, size: NSSize} passé via MemorySegment */
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

// NSWindowLevel, NSModalResponse, NSToolTipTag — désormais émis par kextract v0.0.2.

// ── NSObject protocol ────────────────────────────────────────────────────────

/**
 * Marqueur interface pour les wrappers d'objets Obj-C.
 * Tous les wrappers de classes AppKit doivent implémenter ce interface
 * en exposant leur [ptr] (MemorySegment vers l'objet Obj-C natif).
 */
interface NSObject {
    val ptr: MemorySegment
}
