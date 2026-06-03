//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setContentProtected](set-content-protected.md)

# setContentProtected

[common]\
open fun [setContentProtected](set-content-protected.md)(protected: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)): [WindowRequestResult](../-window-request-result/index.md)

Enables or disables screen-capture protection for this window.

When `true`, the window content is excluded from screenshots and screen recordings. Platform behaviour:

-
   Win32  : `SetWindowDisplayAffinity(WDA_EXCLUDEFROMCAPTURE)`.
-
   AppKit : `NSWindow.sharingType = NSWindowSharingNone`.
-
   X11/Wayland: success no-op, matching winit; no portable capture-protection mechanism.
-
   Others : [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md).

Default implementation returns [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md). Never throws. AppKit and Win32 are wired; X11 and Wayland are success no-ops like winit.

#### Parameters

common

| | |
|---|---|
| protected | `true` to enable content protection, `false` to disable. |