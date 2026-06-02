//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[setFullscreen](set-fullscreen.md)

# setFullscreen

[common]\
abstract fun [setFullscreen](set-fullscreen.md)(fullscreen: [Fullscreen](../-fullscreen/index.md)?)

Enters or exits fullscreen mode.

-
   Pass [Fullscreen.Borderless](../-fullscreen/-borderless/index.md) to cover the monitor without a mode change.
-
   Pass [Fullscreen.Exclusive](../-fullscreen/-exclusive/index.md) to request exclusive fullscreen (desktop only).
-
   Pass null to exit fullscreen and return to the windowed state.

Backends that do not support [Fullscreen.Exclusive](../-fullscreen/-exclusive/index.md) (Wayland, Web, Android, UIKit) treat it as [Fullscreen.Borderless](../-fullscreen/-borderless/index.md) and do NOT throw.

#### Parameters

common

| | |
|---|---|
| fullscreen | New fullscreen state, or null to exit fullscreen. |