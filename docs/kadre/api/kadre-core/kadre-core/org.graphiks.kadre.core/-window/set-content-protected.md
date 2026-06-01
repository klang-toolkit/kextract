//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setContentProtected](set-content-protected.md)

# setContentProtected

[common]\
open fun [setContentProtected](set-content-protected.md)(protected: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))

Enables or disables screen-capture protection for this window.

When `true`, the window content is excluded from screenshots and screen recordings. Platform behaviour:

- 
   Win32  : `SetWindowDisplayAffinity(WDA_EXCLUDEFROMCAPTURE)`.
- 
   AppKit : `NSWindow.sharingType = NSWindowSharingNone`.
- 
   Others : no-op documented.

Default implementation is a no-op. Never throws. TODO R5-MiscWindow: wire in Win32 and AppKit backends.

#### Parameters

common

| | |
|---|---|
| protected | `true` to enable content protection, `false` to disable. |