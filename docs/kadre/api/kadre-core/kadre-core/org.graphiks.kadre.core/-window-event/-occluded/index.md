//[kadre-core](../../../../index.md)/[org.graphiks.kadre.core](../../index.md)/[WindowEvent](../index.md)/[Occluded](index.md)

# Occluded

[common]\
data class [Occluded](index.md)(val occluded: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)) : [WindowEvent](../index.md)

The window's occlusion state changed.

Emitted when the window becomes hidden behind other windows (occluded = true) or becomes visible again (occluded = false).

Platform support:

-
   AppKit: `NSWindowDidChangeOcclusionStateNotification` — TODO.
-
   Web: Page Visibility API (`visibilitychange`) — TODO.
-
   Win32 / X11 / Wayland / Android / UIKit: no-op documented.

## Constructors

| | |
|---|---|
| [Occluded](-occluded.md) | [common]<br>constructor(occluded: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [occluded](occluded.md) | [common]<br>val [occluded](occluded.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>`true` if the window is now occluded, `false` if it is visible. |