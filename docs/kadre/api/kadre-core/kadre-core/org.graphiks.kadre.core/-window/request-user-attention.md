//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[requestUserAttention](request-user-attention.md)

# requestUserAttention

[common]\
open fun [requestUserAttention](request-user-attention.md)(requestType: [UserAttentionType](../-user-attention-type/index.md)?): [WindowRequestResult](../-window-request-result/index.md)

Requests the platform to attract the user's attention (taskbar / dock icon).

Passing null cancels an active attention request.

Platform behaviour:

-
   AppKit : `NSApp.requestUserAttention` / `cancelUserAttentionRequest`.
-
   Win32  : `FlashWindowEx` (FLASHW_TRAY / FLASHW_TIMERNOFG).
-
   X11    : `WM_HINTS` urgency flag.
-
   Wayland: currently unsupported in Kadre; winit can use `xdg_activation_v1` when the compositor exposes it.
-
   Others : [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md).

Default implementation returns [WindowRequestResult.Failure](../-window-request-result/-failure/index.md) with [RequestError.Unsupported](../-request-error/-unsupported/index.md). Never throws. AppKit, Win32, and X11 are wired.

#### Parameters

common

| | |
|---|---|
| requestType | Attention level, or null to cancel the current request. |
