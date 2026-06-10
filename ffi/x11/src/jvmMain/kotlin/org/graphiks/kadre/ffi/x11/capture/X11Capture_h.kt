package org.graphiks.kadre.ffi.x11.capture

import org.graphiks.kadre.ffi.x11.libX11
import org.graphiks.kadre.ffi.x11.libXext
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

// ── Lazy library loading ───────────────────────────────────────────────────────

val libXcomposite: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("libXcomposite.so.1", Arena.global())
    } catch (_: Throwable) { null }
}

private val libc: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("libc.so.6", Arena.global())
    } catch (_: Throwable) { null }
}

private val linkerCap: Linker = Linker.nativeLinker()

private fun SymbolLookup?.downcallCap(name: String, desc: FunctionDescriptor): MethodHandle? {
    this ?: return null
    return this.find(name).map { linkerCap.downcallHandle(it, desc) }.orElse(null)
}

// ── XImage struct field offsets (LP64 Linux) ───────────────────────────────────
// XImage* layout (first fields, checked against libX11 source):
//   int width          @ 0   (4)
//   int height         @ 4   (4)
//   int xoffset        @ 8   (4)
//   int format         @ 12  (4)
//   char* data         @ 16  (8)     ← pointer to pixel data
//   int byte_order     @ 24  (4)
//   int bitmap_unit    @ 28  (4)
//   int bitmap_bit_order @ 32 (4)
//   int bitmap_pad     @ 36  (4)
//   int depth          @ 40  (4)
//   int bytes_per_line @ 44  (4)    ← stride
//   int bits_per_pixel @ 48  (4)
const val XIMAGE_DATA_OFFSET: Long = 16L
const val XIMAGE_BYTES_PER_LINE_OFFSET: Long = 44L
const val XIMAGE_BITS_PER_PIXEL_OFFSET: Long = 48L

// ── XShmSegmentInfo struct layout (LP64 Linux) ─────────────────────────────────
//   ShmSeg shmseg (unsigned long) @ 0  (8)
//   int shmid                     @ 8  (4)
//   Bool readOnly (int)           @ 12 (4)
//   char* shmaddr                 @ 16 (8)
const val XSHM_SEGINFO_SIZE: Long = 24L
const val XSHM_SHMPIX_OFFSET: Long = 0L  // shmseg (XID)
const val XSHM_SHMD_OFFSET: Long = 8L     // shmid
const val XSHM_READONLY_OFFSET: Long = 12L
const val XSHM_ADDR_OFFSET: Long = 16L    // shmaddr pointer

// ── X11 constants ──────────────────────────────────────────────────────────────

const val Xlib_ZPixmap: Int = 2
const val Xlib_AllPlanes: Long = -1L

// XShmGetImage format constant (same as ZPixmap)
const val XSHM_ZPIXMAP: Int = 2

// XCompositeRedirect constants
const val CompositeRedirectAutomatic: Int = 1

// XGetWindowProperty constants
const val XGetPropertyDelete: Long = 1L
const val XGetPropertyKeep: Long = 0L
const val AnyPropertyType: Long = 0L

// shmget constants (Linux: IPC_CREAT = 01000 octal = 512 decimal)
const val IPC_PRIVATE: Int = 0
const val IPC_CREAT: Int = 512
const val IPC_RMID: Int = 0

// XGetWindowAttributes offset in XWindowAttributes struct (for map_state)
// XWindowAttributes layout (LP64):
//   int x, y; int width, height; int border_width; int depth;
//   Visual* visual; Window root; int class; int bit_gravity;
//   int win_gravity; int backing_store; unsigned long backing_planes;
//   unsigned long backing_pixel; Bool save_under; Bool map_is_installed;
//   int map_state; AllocNone alloc; ...
// For getting map_state, we need its offset. On LP64:
//   after: x(4) y(4) width(4) height(4) border_width(4) depth(4) = 24
//   visual*(8) = 32, root(8) = 40, class(4) = 44, bit_gravity(4) = 48,
//   win_gravity(4) = 52, backing_store(4) = 56, backing_planes(8) = 64,
//   backing_pixel(8) = 72, save_under(4) = 76, map_is_installed(4) = 80,
//   map_state(4) = 84
const val XWINDOWATTR_MAP_STATE_OFFSET: Long = 84L
const val IsViewable: Int = 2

// NetWM constants
const val NET_WM_WINDOW_TYPE_NORMAL: Long = 0L
const val NET_WM_WINDOW_TYPE_DOCK: Long = 1L
const val NET_WM_WINDOW_TYPE_DIALOG: Long = 2L
const val NET_WM_WINDOW_TYPE_UTILITY: Long = 3L
const val NET_WM_WINDOW_TYPE_TOOLBAR: Long = 4L
const val NET_WM_WINDOW_TYPE_SPLASH: Long = 5L
const val NET_WM_WINDOW_TYPE_DESKTOP: Long = 6L

// ── FFM bindings: libXext (for MIT-SHM) ────────────────────────────────────────

val xShmQueryExtension: MethodHandle? by lazy {
    libXext.downcallCap("XShmQueryExtension", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // Bool
        ValueLayout.ADDRESS,     // Display*
    ))
}

val xShmCreateImage: MethodHandle? by lazy {
    libXext.downcallCap("XShmCreateImage", FunctionDescriptor.of(
        ValueLayout.ADDRESS,     // XImage* return
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.ADDRESS,     // Visual*
        ValueLayout.JAVA_INT,    // unsigned int depth
        ValueLayout.JAVA_INT,    // int format (ZPixmap)
        ValueLayout.ADDRESS,     // char* data (NULL or shmaddr)
        ValueLayout.ADDRESS,     // XShmSegmentInfo*
        ValueLayout.JAVA_INT,    // unsigned int width
        ValueLayout.JAVA_INT,    // unsigned int height
    ))
}

val xShmAttach: MethodHandle? by lazy {
    libXext.downcallCap("XShmAttach", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // Bool
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.ADDRESS,     // XShmSegmentInfo*
    ))
}

val xShmDetach: MethodHandle? by lazy {
    libXext.downcallCap("XShmDetach", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // Bool
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.ADDRESS,     // XShmSegmentInfo*
    ))
}

val xShmGetImage: MethodHandle? by lazy {
    libXext.downcallCap("XShmGetImage", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // Bool
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.JAVA_LONG,   // Drawable (Window or Pixmap XID)
        ValueLayout.ADDRESS,     // XImage*
        ValueLayout.JAVA_INT,    // int x
        ValueLayout.JAVA_INT,    // int y
        ValueLayout.JAVA_LONG,   // unsigned long plane_mask (AllPlanes)
    ))
}

// ── FFM bindings: libX11 (additional capture functions) ────────────────────────

val xGetImage: MethodHandle? by lazy {
    libX11.downcallCap("XGetImage", FunctionDescriptor.of(
        ValueLayout.ADDRESS,     // XImage* return
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.JAVA_LONG,   // Drawable
        ValueLayout.JAVA_INT,    // int x
        ValueLayout.JAVA_INT,    // int y
        ValueLayout.JAVA_INT,    // unsigned int width
        ValueLayout.JAVA_INT,    // unsigned int height
        ValueLayout.JAVA_LONG,   // unsigned long plane_mask
        ValueLayout.JAVA_INT,    // int format (ZPixmap)
    ))
}

val xDestroyImage: MethodHandle? by lazy {
    libX11.downcallCap("XDestroyImage", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // int return (or void, varies)
        ValueLayout.ADDRESS,     // XImage*
    ))
}

val xDefaultScreen: MethodHandle? by lazy {
    libX11.downcallCap("XDefaultScreen", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // int screen_number
        ValueLayout.ADDRESS,     // Display*
    ))
}

val xDefaultRootWindow: MethodHandle? by lazy {
    libX11.downcallCap("XDefaultRootWindow", FunctionDescriptor.of(
        ValueLayout.JAVA_LONG,   // Window (XID)
        ValueLayout.ADDRESS,     // Display*
    ))
}

val xDefaultVisual: MethodHandle? by lazy {
    libX11.downcallCap("XDefaultVisual", FunctionDescriptor.of(
        ValueLayout.ADDRESS,     // Visual*
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.JAVA_INT,    // int screen_number
    ))
}

val xDefaultDepth: MethodHandle? by lazy {
    libX11.downcallCap("XDefaultDepth", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // int depth
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.JAVA_INT,    // int screen_number
    ))
}

val xQueryTree: MethodHandle? by lazy {
    libX11.downcallCap("XQueryTree", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // Status
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.JAVA_LONG,   // Window w
        ValueLayout.ADDRESS,     // Window* root_return
        ValueLayout.ADDRESS,     // Window* parent_return
        ValueLayout.ADDRESS,     // Window** children_return
        ValueLayout.ADDRESS,     // unsigned int* nchildren_return
    ))
}

val xGetWindowAttributes: MethodHandle? by lazy {
    libX11.downcallCap("XGetWindowAttributes", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // Status
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.JAVA_LONG,   // Window
        ValueLayout.ADDRESS,     // XWindowAttributes*
    ))
}

val xSync: MethodHandle? by lazy {
    libX11.downcallCap("XSync", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,    // Status
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.JAVA_INT,    // Bool discard
    ))
}

// ── FFM bindings: libXcomposite ────────────────────────────────────────────────

val xCompositeNameWindowPixmap: MethodHandle? by lazy {
    libXcomposite.downcallCap("XCompositeNameWindowPixmap", FunctionDescriptor.of(
        ValueLayout.JAVA_LONG,   // Pixmap return
        ValueLayout.ADDRESS,     // Display*
        ValueLayout.JAVA_LONG,   // Window
    ))
}

// ── FFM bindings: libc (system V shared memory) ────────────────────────────────

val shmget: MethodHandle? by lazy {
    libc.downcallCap("shmget", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,            // int shmid (or -1 on error)
        ValueLayout.JAVA_INT,            // key_t key
        ValueLayout.JAVA_LONG,           // size_t size
        ValueLayout.JAVA_INT,            // int shmflg
    ))
}

val shmat: MethodHandle? by lazy {
    libc.downcallCap("shmat", FunctionDescriptor.of(
        ValueLayout.ADDRESS,             // void* return
        ValueLayout.JAVA_INT,            // int shmid
        ValueLayout.ADDRESS,             // const void* shmaddr (NULL)
        ValueLayout.JAVA_INT,            // int shmflg (0)
    ))
}

val shmdt: MethodHandle? by lazy {
    libc.downcallCap("shmdt", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,            // int return (0 on success)
        ValueLayout.ADDRESS,             // const void* shmaddr
    ))
}

val shmctl: MethodHandle? by lazy {
    libc.downcallCap("shmctl", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,            // int return
        ValueLayout.JAVA_INT,            // int shmid
        ValueLayout.JAVA_INT,            // int cmd (IPC_RMID=0)
        ValueLayout.ADDRESS,             // struct shmid_ds* (NULL for IPC_RMID)
    ))
}

// ── BGRA → RGBA conversion ────────────────────────────────────────────────────

fun bgraToRgba(data: ByteArray): ByteArray {
    val result = data.copyOf()
    var i = 0
    while (i + 4 <= result.size) {
        val b = result[i]
        val r = result[i + 2]
        result[i] = r
        result[i + 2] = b
        i += 4
    }
    return result
}
