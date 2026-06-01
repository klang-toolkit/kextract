//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)/[prePresentNotify](pre-present-notify.md)

# prePresentNotify

[common]\
abstract fun [prePresentNotify](pre-present-notify.md)()

Notifies the compositor that the window is about to present a frame.

On Wayland this triggers `wl_surface.pre_commit` / frame optimizations. On other backends this is a no-op.