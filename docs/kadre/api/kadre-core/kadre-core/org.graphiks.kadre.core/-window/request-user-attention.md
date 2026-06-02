//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[requestUserAttention](request-user-attention.md)

# requestUserAttention

[common]\
open fun [requestUserAttention](request-user-attention.md)(requestType: [UserAttentionType](../-user-attention-type/index.md)?)

Requests the platform to attract the user's attention (taskbar / dock icon).

Passing null cancels an active attention request.

Platform behaviour:

-
   AppKit : `NSApp.requestUserAttention` / `cancelUserAttentionRequest`.
-
   Win32  : `FlashWindowEx` (FLASHW_TRAY / FLASHW_TIMER).
-
   Others : no-op documented.

Default implementation is a no-op. Never throws. TODO R5-MiscWindow: wire in AppKit and Win32 backends.

#### Parameters

common

| | |
|---|---|
| requestType | Attention level, or null to cancel the current request. |