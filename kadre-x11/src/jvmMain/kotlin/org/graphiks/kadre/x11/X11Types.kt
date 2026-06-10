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

// XChangeWindowAttributes valuemask bits (X.h)
internal const val CWOverrideRedirect: Long = 1L shl 9

// XSetWindowAttributes struct size and override_redirect field offset (LP64)
internal const val XSETWINDOWATTRIBUTES_SIZE: Long = 112L
internal const val XSETWINDOWATTRIBUTES_ALIGN: Long = 8L
internal const val XSETWINDOWATTR_OVERRIDE_REDIRECT_OFFSET: Long = 88L

// XClientMessageEvent LP64 offsets within the XEvent union.
internal const val XCLIENT_SEND_EVENT_OFFSET: Long = 16L
internal const val XCLIENT_DISPLAY_OFFSET: Long = 24L
internal const val XCLIENT_WINDOW_OFFSET: Long = 32L
internal const val XCLIENT_MESSAGE_TYPE_OFFSET: Long = 40L
internal const val XCLIENT_FORMAT_OFFSET: Long = 48L
internal const val XCLIENT_DATA_L0_OFFSET: Long = 56L
internal const val XCLIENT_DATA_L1_OFFSET: Long = 64L
internal const val XCLIENT_DATA_L2_OFFSET: Long = 72L
internal const val XCLIENT_DATA_L3_OFFSET: Long = 80L
internal const val XCLIENT_DATA_L4_OFFSET: Long = 88L

// SelectionNotify event type and XSelectionEvent offsets (LP64)
internal const val SelectionNotify: Int = 31

internal const val XSELECTION_REQUESTOR_OFFSET: Long = 32L
internal const val XSELECTION_SELECTION_OFFSET: Long = 40L
internal const val XSELECTION_TARGET_OFFSET: Long = 48L
internal const val XSELECTION_PROPERTY_OFFSET: Long = 56L
