//[kadre-core](../../../index.md)/[org.graphiks.kadre.core](../index.md)/[Window](index.md)

# Window

[common]\
interface [Window](index.md)

Abstraction of a native window created by the event loop.

The concrete implementations are provided by the platform modules (kadre-appkit, etc.).

## Properties

| Name | Summary |
|---|---|
| [fullscreen](fullscreen.md) | [common]<br>abstract val [fullscreen](fullscreen.md): [Fullscreen](../-fullscreen/index.md)?<br>Returns the current fullscreen state, or null if the window is not fullscreen. |
| [id](id.md) | [common]<br>abstract val [id](id.md): [WindowId](../-window-id/index.md)<br>Unique identifier of the window. |
| [innerSize](inner-size.md) | [common]<br>abstract val [innerSize](inner-size.md): [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;<br>Returns the inner size of the window in physical pixels (rendering surface, without the decorations). |
| [isDecorated](is-decorated.md) | [common]<br>abstract val [isDecorated](is-decorated.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Returns whether the window currently has platform decorations. |
| [isMaximized](is-maximized.md) | [common]<br>abstract val [isMaximized](is-maximized.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Returns whether the window is currently maximized. |
| [isMinimized](is-minimized.md) | [common]<br>abstract val [isMinimized](is-minimized.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Returns whether the window is currently minimized. |
| [isResizable](is-resizable.md) | [common]<br>abstract val [isResizable](is-resizable.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Returns whether the window can be resized by the user. |
| [isVisible](is-visible.md) | [common]<br>abstract val [isVisible](is-visible.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Returns whether the window is currently visible. |
| [outerPosition](outer-position.md) | [common]<br>abstract val [outerPosition](outer-position.md): [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;<br>Returns the outer position of the window on the screen in physical pixels (top-left corner of the window frame, including decorations). |
| [outerSize](outer-size.md) | [common]<br>abstract val [outerSize](outer-size.md): [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;<br>Returns the outer size of the window in physical pixels (rendering surface plus the platform decorations). |
| [rawDisplayHandle](raw-display-handle.md) | [common]<br>abstract val [rawDisplayHandle](raw-display-handle.md): [RawDisplayHandle](../-raw-display-handle/index.md)<br>Returns the native handle of the display. |
| [rawWindowHandle](raw-window-handle.md) | [common]<br>abstract val [rawWindowHandle](raw-window-handle.md): [RawWindowHandle](../-raw-window-handle/index.md)<br>Returns the native handle of the rendering surface. |
| [scaleFactor](scale-factor.md) | [common]<br>abstract val [scaleFactor](scale-factor.md): [Double](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-double/index.html)<br>Returns the scale factor between logical and physical pixels for this window. |
| [theme](theme.md) | [common]<br>abstract val [theme](theme.md): [Theme](../-theme/index.md)?<br>Returns the current system theme as seen by this window, or null if unknown. |
| [title](title.md) | [common]<br>abstract val [title](title.md): [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html)<br>Returns the current title of the window's title bar. |

## Functions

| Name | Summary |
|---|---|
| [close](close.md) | [common]<br>abstract fun [close](close.md)()<br>Closes the window. |
| [currentMonitor](current-monitor.md) | [common]<br>abstract fun [currentMonitor](current-monitor.md)(): [MonitorHandle](../-monitor-handle/index.md)?<br>Returns the monitor that currently contains the majority of the window, or null if the information is not available. |
| [dragResizeWindow](drag-resize-window.md) | [common]<br>open fun [dragResizeWindow](drag-resize-window.md)(direction: [ResizeDirection](../-resize-direction/index.md))<br>Initiates a user-driven window resize from the current cursor position. |
| [dragWindow](drag-window.md) | [common]<br>open fun [dragWindow](drag-window.md)()<br>Initiates a user-driven window drag from the current cursor position. |
| [prePresentNotify](pre-present-notify.md) | [common]<br>abstract fun [prePresentNotify](pre-present-notify.md)()<br>Notifies the compositor that the window is about to present a frame. |
| [requestRedraw](request-redraw.md) | [common]<br>abstract fun [requestRedraw](request-redraw.md)()<br>Requests a redraw of the window at the next iteration. |
| [requestUserAttention](request-user-attention.md) | [common]<br>open fun [requestUserAttention](request-user-attention.md)(requestType: [UserAttentionType](../-user-attention-type/index.md)?)<br>Requests the platform to attract the user's attention (taskbar / dock icon). |
| [resetDeadKeys](reset-dead-keys.md) | [common]<br>abstract fun [resetDeadKeys](reset-dead-keys.md)()<br>Resets any pending dead-key state for this window. |
| [setBlur](set-blur.md) | [common]<br>abstract fun [setBlur](set-blur.md)(blur: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Enables or disables a blur effect behind the window. |
| [setContentProtected](set-content-protected.md) | [common]<br>open fun [setContentProtected](set-content-protected.md)(protected: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Enables or disables screen-capture protection for this window. |
| [setCursor](set-cursor.md) | [common]<br>abstract fun [setCursor](set-cursor.md)(cursor: [CursorIcon](../-cursor-icon/index.md))<br>Changes the cursor shape displayed over this window. |
| [setCursorGrab](set-cursor-grab.md) | [common]<br>abstract fun [setCursorGrab](set-cursor-grab.md)(mode: [CursorGrabMode](../-cursor-grab-mode/index.md))<br>Sets the cursor grab mode for this window. |
| [setCursorHittest](set-cursor-hittest.md) | [common]<br>abstract fun [setCursorHittest](set-cursor-hittest.md)(hittest: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Enables or disables cursor hit-testing for this window. |
| [setCursorPosition](set-cursor-position.md) | [common]<br>abstract fun [setCursorPosition](set-cursor-position.md)(position: [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;)<br>Warps the cursor to the given position (physical pixels, relative to the top-left of the window's client area). |
| [setCursorVisible](set-cursor-visible.md) | [common]<br>abstract fun [setCursorVisible](set-cursor-visible.md)(visible: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Shows or hides the cursor while it is inside this window. |
| [setCustomCursor](set-custom-cursor.md) | [common]<br>open fun [setCustomCursor](set-custom-cursor.md)(cursor: [CustomCursor](../-custom-cursor/index.md))<br>Applies a previously created custom cursor to this window. |
| [setDecorations](set-decorations.md) | [common]<br>abstract fun [setDecorations](set-decorations.md)(decorated: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Shows or hides the window's platform decorations (title bar, borders). |
| [setFullscreen](set-fullscreen.md) | [common]<br>abstract fun [setFullscreen](set-fullscreen.md)(fullscreen: [Fullscreen](../-fullscreen/index.md)?)<br>Enters or exits fullscreen mode. |
| [setImeAllowed](set-ime-allowed.md) | [common]<br>open fun [setImeAllowed](set-ime-allowed.md)(allowed: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Enables or disables IME (Input Method Editor) input for this window. |
| [setImeCursorArea](set-ime-cursor-area.md) | [common]<br>open fun [setImeCursorArea](set-ime-cursor-area.md)(position: [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;, size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;)<br>Notifies the IME of the text cursor's current position and bounding box. |
| [setImePurpose](set-ime-purpose.md) | [common]<br>open fun [setImePurpose](set-ime-purpose.md)(purpose: [ImePurpose](../-ime-purpose/index.md))<br>Hints the IME about the intended purpose of the focused text field. |
| [setMaximized](set-maximized.md) | [common]<br>abstract fun [setMaximized](set-maximized.md)(maximized: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Maximizes or restores the window. |
| [setMaxSurfaceSize](set-max-surface-size.md) | [common]<br>abstract fun [setMaxSurfaceSize](set-max-surface-size.md)(size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;?)<br>Sets the maximum surface size constraint. |
| [setMinimized](set-minimized.md) | [common]<br>abstract fun [setMinimized](set-minimized.md)(minimized: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Minimizes or restores the window. |
| [setMinSurfaceSize](set-min-surface-size.md) | [common]<br>abstract fun [setMinSurfaceSize](set-min-surface-size.md)(size: [PhysicalSize](../-physical-size/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;?)<br>Sets the minimum surface size constraint. |
| [setOuterPosition](set-outer-position.md) | [common]<br>abstract fun [setOuterPosition](set-outer-position.md)(position: [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;)<br>Moves the window so that its outer top-left corner is at [position](set-outer-position.md) (in physical screen pixels). |
| [setResizable](set-resizable.md) | [common]<br>abstract fun [setResizable](set-resizable.md)(resizable: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Sets whether the window can be resized by the user. |
| [setTheme](set-theme.md) | [common]<br>abstract fun [setTheme](set-theme.md)(theme: [Theme](../-theme/index.md)?)<br>Requests a specific theme for this window. |
| [setTitle](set-title.md) | [common]<br>abstract fun [setTitle](set-title.md)(title: [String](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/index.html))<br>Sets the title shown in the window's title bar. |
| [setTransparent](set-transparent.md) | [common]<br>abstract fun [setTransparent](set-transparent.md)(transparent: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Makes the window background transparent. |
| [setVisible](set-visible.md) | [common]<br>abstract fun [setVisible](set-visible.md)(visible: [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html))<br>Makes the window visible or invisible. |
| [setWindowIcon](set-window-icon.md) | [common]<br>abstract fun [setWindowIcon](set-window-icon.md)(icon: [Icon](../-icon/index.md)?)<br>Sets the application icon shown in the taskbar / dock. |
| [setWindowLevel](set-window-level.md) | [common]<br>abstract fun [setWindowLevel](set-window-level.md)(level: [WindowLevel](../-window-level/index.md))<br>Sets the Z-order level of this window. |
| [showWindowMenu](show-window-menu.md) | [common]<br>open fun [showWindowMenu](show-window-menu.md)(position: [PhysicalPosition](../-physical-position/index.md)&lt;[Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)&gt;)<br>Shows the platform window menu (system / title-bar context menu) at the given position. |