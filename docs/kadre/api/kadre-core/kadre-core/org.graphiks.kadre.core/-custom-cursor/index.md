//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[CustomCursor](index.md)

# CustomCursor

[common]\
class [CustomCursor](index.md)

Opaque handle to a platform-allocated custom cursor.

Obtained via [ActiveEventLoop.createCustomCursor](../-active-event-loop/create-custom-cursor.md); passed to [Window.setCustomCursor](../-window/set-custom-cursor.md). The `id` is an opaque platform-specific identifier (e.g. native pointer cast to Long).