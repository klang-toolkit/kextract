package org.graphiks.kadre.x11

// XEvent type constants
internal const val KeyPress: Int = 2
internal const val KeyRelease: Int = 3
internal const val ButtonPress: Int = 4
internal const val ButtonRelease: Int = 5
internal const val MotionNotify: Int = 6
internal const val EnterNotify: Int = 7
internal const val LeaveNotify: Int = 8
internal const val FocusIn: Int = 9
internal const val FocusOut: Int = 10
internal const val Expose: Int = 12
internal const val VisibilityNotify: Int = 15
internal const val ConfigureNotify: Int = 22
internal const val ClientMessage: Int = 33
internal const val DestroyNotify: Int = 17

// XSelectInput event masks
internal const val KeyPressMask: Long = 1L
internal const val KeyReleaseMask: Long = 2L
internal const val ButtonPressMask: Long = 4L
internal const val ButtonReleaseMask: Long = 8L
internal const val PointerMotionMask: Long = 64L
internal const val VisibilityChangeMask: Long = 65536L
internal const val FocusChangeMask: Long = 2097152L
internal const val EnterWindowMask: Long = 16L
internal const val LeaveWindowMask: Long = 32L
internal const val ExposureMask: Long = 32768L
internal const val StructureNotifyMask: Long = 131072L
internal const val SubstructureNotifyMask: Long = 524288L
internal const val SubstructureRedirectMask: Long = 1048576L
