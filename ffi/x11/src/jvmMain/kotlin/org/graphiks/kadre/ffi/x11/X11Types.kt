package org.graphiks.kadre.ffi.x11

// XEvent type constants
const val KeyPress: Int = 2
const val KeyRelease: Int = 3
const val ButtonPress: Int = 4
const val ButtonRelease: Int = 5
const val MotionNotify: Int = 6
const val EnterNotify: Int = 7
const val LeaveNotify: Int = 8
const val FocusIn: Int = 9
const val FocusOut: Int = 10
const val Expose: Int = 12
const val VisibilityNotify: Int = 15
const val ConfigureNotify: Int = 22
const val ClientMessage: Int = 33
const val DestroyNotify: Int = 17

// XSelectInput event masks
const val KeyPressMask: Long = 1L
const val KeyReleaseMask: Long = 2L
const val ButtonPressMask: Long = 4L
const val ButtonReleaseMask: Long = 8L
const val PointerMotionMask: Long = 64L
const val VisibilityChangeMask: Long = 65536L
const val FocusChangeMask: Long = 2097152L
const val EnterWindowMask: Long = 16L
const val LeaveWindowMask: Long = 32L
const val ExposureMask: Long = 32768L
const val StructureNotifyMask: Long = 131072L
const val SubstructureNotifyMask: Long = 524288L
const val SubstructureRedirectMask: Long = 1048576L

// XChangeWindowAttributes valuemask bits (X.h)
const val CWOverrideRedirect: Long = 1L shl 9

// XSetWindowAttributes struct size and override_redirect field offset (LP64)
const val XSETWINDOWATTRIBUTES_SIZE: Long = 112L
const val XSETWINDOWATTRIBUTES_ALIGN: Long = 8L
const val XSETWINDOWATTR_OVERRIDE_REDIRECT_OFFSET: Long = 88L

// XClientMessageEvent LP64 offsets within the XEvent union.
const val XCLIENT_SEND_EVENT_OFFSET: Long = 16L
const val XCLIENT_DISPLAY_OFFSET: Long = 24L
const val XCLIENT_WINDOW_OFFSET: Long = 32L
const val XCLIENT_MESSAGE_TYPE_OFFSET: Long = 40L
const val XCLIENT_FORMAT_OFFSET: Long = 48L
const val XCLIENT_DATA_L0_OFFSET: Long = 56L
const val XCLIENT_DATA_L1_OFFSET: Long = 64L
const val XCLIENT_DATA_L2_OFFSET: Long = 72L
const val XCLIENT_DATA_L3_OFFSET: Long = 80L
const val XCLIENT_DATA_L4_OFFSET: Long = 88L

// SelectionNotify event type and XSelectionEvent offsets (LP64)
const val SelectionNotify: Int = 31

const val XSELECTION_REQUESTOR_OFFSET: Long = 32L
const val XSELECTION_SELECTION_OFFSET: Long = 40L
const val XSELECTION_TARGET_OFFSET: Long = 48L
const val XSELECTION_PROPERTY_OFFSET: Long = 56L
