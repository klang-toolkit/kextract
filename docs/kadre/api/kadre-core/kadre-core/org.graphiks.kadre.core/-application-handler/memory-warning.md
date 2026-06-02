//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[ApplicationHandler](index.md)/[memoryWarning](memory-warning.md)

# memoryWarning

[common]\
open fun [memoryWarning](memory-warning.md)(eventLoop: [ActiveEventLoop](../-active-event-loop/index.md))

Called when the system notifies the application of a low-memory condition.

Mobile backends only:

-
   iOS / UIKit : `applicationDidReceiveMemoryWarning` / `didReceiveMemoryWarning`.
-
   Android     : `onTrimMemory(TRIM_MEMORY_RUNNING_CRITICAL)`.

On all other backends this method is never called. Applications should release any caches or optional resources in response to this callback.

Default implementation is a no-op. Never throws. TODO R5-MiscWindow: wire in UIKit and Android backends.

#### Parameters

common

| | |
|---|---|
| eventLoop | Active event loop. |