/**
 * R2 — Monitor enumeration for the X11 backend.
 *
 * Strategy (in priority order):
 *  1. XRandR (libXrandr.so.2): XRRGetScreenResources + XRRGetCrtcInfo → real monitor list.
 *  2. Xinerama (libXinerama.so.1): XineramaQueryScreens → one entry per physical screen.
 *  3. Synthetic fallback: one monitor derived from DefaultScreen + DisplayWidth/DisplayHeight.
 *
 * All libraries are loaded lazily; the file compiles on macOS/Windows (null handles → no-op).
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.VideoMode
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

// ── X11 monitor handle ────────────────────────────────────────────────────────

/** X11 implementation of [MonitorHandle]. */
internal class X11MonitorHandle(
    override val id: Long,
    override val name: String?,
    override val position: PhysicalPosition<Int>,
    override val scaleFactor: Double,
    override val currentVideoMode: VideoMode?,
    override val videoModes: List<VideoMode>,
) : MonitorHandle

// ── Lazy library loading ──────────────────────────────────────────────────────

private val linkerX11: Linker = Linker.nativeLinker()

private val libXrandr: java.lang.foreign.SymbolLookup? by lazy {
    try {
        java.lang.foreign.SymbolLookup.libraryLookup("libXrandr.so.2", Arena.global())
    } catch (_: Throwable) { null }
}

private val libXinerama: java.lang.foreign.SymbolLookup? by lazy {
    try {
        java.lang.foreign.SymbolLookup.libraryLookup("libXinerama.so.1", Arena.global())
    } catch (_: Throwable) { null }
}

private fun java.lang.foreign.SymbolLookup?.downcallX(name: String, desc: FunctionDescriptor): MethodHandle? {
    this ?: return null
    return this.find(name).map { linkerX11.downcallHandle(it, desc) }.orElse(null)
}

// XRandR
private val xrrGetScreenResources: MethodHandle? by lazy {
    libXrandr.downcallX("XRRGetScreenResources", FunctionDescriptor.of(
        ValueLayout.ADDRESS,    // XRRScreenResources*
        ValueLayout.ADDRESS,    // Display*
        ValueLayout.JAVA_LONG,  // Window (root)
    ))
}

private val xrrFreeScreenResources: MethodHandle? by lazy {
    libXrandr.downcallX("XRRFreeScreenResources", FunctionDescriptor.ofVoid(
        ValueLayout.ADDRESS,    // XRRScreenResources*
    ))
}

private val xrrGetCrtcInfo: MethodHandle? by lazy {
    libXrandr.downcallX("XRRGetCrtcInfo", FunctionDescriptor.of(
        ValueLayout.ADDRESS,    // XRRCrtcInfo*
        ValueLayout.ADDRESS,    // Display*
        ValueLayout.ADDRESS,    // XRRScreenResources*
        ValueLayout.JAVA_LONG,  // RRCrtc crtc (XID = unsigned long)
    ))
}

private val xrrFreeCrtcInfo: MethodHandle? by lazy {
    libXrandr.downcallX("XRRFreeCrtcInfo", FunctionDescriptor.ofVoid(
        ValueLayout.ADDRESS,    // XRRCrtcInfo*
    ))
}

// Xinerama
private val xineramaQueryScreens: MethodHandle? by lazy {
    libXinerama.downcallX("XineramaQueryScreens", FunctionDescriptor.of(
        ValueLayout.ADDRESS,    // XineramaScreenInfo*
        ValueLayout.ADDRESS,    // Display*
        ValueLayout.ADDRESS,    // int* number (out)
    ))
}

// Standard X11 geometry
private val xDisplayWidth: MethodHandle? by lazy {
    libX11.downcallX("XDisplayWidth", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,   // int
        ValueLayout.ADDRESS,    // Display*
        ValueLayout.JAVA_INT,   // int screen_number
    ))
}

private val xDisplayHeight: MethodHandle? by lazy {
    libX11.downcallX("XDisplayHeight", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,   // int
        ValueLayout.ADDRESS,    // Display*
        ValueLayout.JAVA_INT,   // int screen_number
    ))
}

// ── Monitor enumeration ───────────────────────────────────────────────────────

/**
 * Returns all monitors for the given X11 display, trying RANDR, then Xinerama,
 * then a single synthetic monitor.
 *
 * @param displayPtr  Address of the Display* pointer.
 * @param screen      Default screen number.
 * @param scaleFactor DPI scale factor (read from Xft.dpi at startup).
 */
internal fun enumerateX11Monitors(
    displayPtr: Long,
    screen: Int,
    scaleFactor: Double,
): List<X11MonitorHandle> {
    val display = MemorySegment.ofAddress(displayPtr)

    // 1. Try RANDR
    val randrMonitors = tryRandr(display, screen, scaleFactor)
    if (randrMonitors != null) return randrMonitors

    // 2. Try Xinerama
    val xineramaMonitors = tryXinerama(display, scaleFactor)
    if (xineramaMonitors != null) return xineramaMonitors

    // 3. Synthetic fallback: single monitor from DisplayWidth/DisplayHeight
    return listOf(syntheticMonitor(display, screen, scaleFactor))
}

// ── RANDR ─────────────────────────────────────────────────────────────────────

// XRRScreenResources offsets (opaque struct — we read only the field we need):
//   int ncrtc @ offset 8 (after timestamp and configTimestamp which are 4 bytes each on 32-bit
//   but on 64-bit: timestamp=unsigned long=8, configTimestamp=unsigned long=8, ncrtc=int=4 at offset 24)
//   RRCrtc* crtcs @ offset 32 (pointer on 64-bit after int ncrtc + 4 bytes padding)
// Layout XRRScreenResources (64-bit Linux, Xrandr.h) — count and pointer interleave:
//   [0]  Time timestamp        (8)
//   [8]  Time configTimestamp  (8)
//   [16] int ncrtc             (4)
//   [20] (4 padding → 8-byte pointer alignment)
//   [24] RRCrtc* crtcs         (8)
//   [32] int noutput           (4)
//   [40] RROutput* outputs     (8)
//   [48] int nmode / [56] XRRModeInfo* modes
private const val RR_SCREEN_NCRTC_OFFSET: Long = 16L
private const val RR_SCREEN_CRTCS_OFFSET: Long = 24L

// XRRCrtcInfo offsets (64-bit Linux):
//   [0]  unsigned long timestamp (8)
//   [8]  int x (4)
//   [12] int y (4)
//   [16] unsigned int width (4)
//   [20] unsigned int height (4)
//   [24] (mode/rotation/etc — 8 bytes)
//   [32] int noutput (4)
private const val RR_CRTC_TIMESTAMP_OFFSET: Long = 0L
private const val RR_CRTC_X_OFFSET: Long = 8L
private const val RR_CRTC_Y_OFFSET: Long = 12L
private const val RR_CRTC_WIDTH_OFFSET: Long = 16L
private const val RR_CRTC_HEIGHT_OFFSET: Long = 20L

private fun tryRandr(display: MemorySegment, screen: Int, scale: Double): List<X11MonitorHandle>? {
    val getRes    = xrrGetScreenResources ?: return null
    val freeRes   = xrrFreeScreenResources
    val getCrtc   = xrrGetCrtcInfo ?: return null
    val freeCrtc  = xrrFreeCrtcInfo
    val rootMH    = xRootWindow ?: return null

    return try {
        val root: Long = rootMH.invokeExact(display, screen) as Long
        val resSeg = getRes.invokeExact(display, root) as MemorySegment
        if (resSeg == MemorySegment.NULL || resSeg.address() == 0L) return null

        // Reinterpret as a larger segment to access the struct fields
        val res = resSeg.reinterpret(64L)
        val ncrtc = res.get(ValueLayout.JAVA_INT, RR_SCREEN_NCRTC_OFFSET)
        val crtcsPtr = res.get(ValueLayout.ADDRESS, RR_SCREEN_CRTCS_OFFSET)

        val monitors = mutableListOf<X11MonitorHandle>()
        for (i in 0 until ncrtc) {
            val crtcId = crtcsPtr.reinterpret(ncrtc.toLong() * 8L)
                .get(ValueLayout.JAVA_LONG, i.toLong() * 8L)
            val crtcSeg = getCrtc.invokeExact(display, resSeg, crtcId) as MemorySegment
            if (crtcSeg == MemorySegment.NULL || crtcSeg.address() == 0L) continue

            val crtc = crtcSeg.reinterpret(32L)
            val x = crtc.get(ValueLayout.JAVA_INT, RR_CRTC_X_OFFSET)
            val y = crtc.get(ValueLayout.JAVA_INT, RR_CRTC_Y_OFFSET)
            val w = crtc.get(ValueLayout.JAVA_INT, RR_CRTC_WIDTH_OFFSET)
            val h = crtc.get(ValueLayout.JAVA_INT, RR_CRTC_HEIGHT_OFFSET)

            try { freeCrtc?.invokeExact(crtcSeg) } catch (_: Throwable) {}

            if (w <= 0 || h <= 0) continue

            monitors.add(X11MonitorHandle(
                id             = crtcId,
                name           = null,
                position       = PhysicalPosition(x, y),
                scaleFactor    = scale,
                currentVideoMode = VideoMode(PhysicalSize(w, h), null, null),
                videoModes     = emptyList(),
            ))
        }

        try { freeRes?.invokeExact(resSeg) } catch (_: Throwable) {}

        if (monitors.isEmpty()) null else monitors
    } catch (_: Throwable) { null }
}

// ── Xinerama ──────────────────────────────────────────────────────────────────

// XineramaScreenInfo: screen_number(int=4), x_org(short=2), y_org(short=2), width(short=2), height(short=2) = 12 bytes
private const val XINERAMA_INFO_SIZE: Long = 12L
private const val XINERAMA_X_OFFSET: Long = 4L
private const val XINERAMA_Y_OFFSET: Long = 6L
private const val XINERAMA_W_OFFSET: Long = 8L
private const val XINERAMA_H_OFFSET: Long = 10L

private fun tryXinerama(display: MemorySegment, scale: Double): List<X11MonitorHandle>? {
    val queryScreens = xineramaQueryScreens ?: return null
    return try {
        Arena.ofConfined().use { arena ->
            val countPtr = arena.allocate(ValueLayout.JAVA_INT)
            val infoArr = queryScreens.invokeExact(display, countPtr) as MemorySegment
            if (infoArr == MemorySegment.NULL || infoArr.address() == 0L) return null
            val count = countPtr.get(ValueLayout.JAVA_INT, 0L)
            if (count <= 0) return null

            val monitors = mutableListOf<X11MonitorHandle>()
            val data = infoArr.reinterpret(count.toLong() * XINERAMA_INFO_SIZE)
            for (i in 0 until count) {
                val base = i.toLong() * XINERAMA_INFO_SIZE
                val x = data.get(ValueLayout.JAVA_SHORT, base + XINERAMA_X_OFFSET).toInt()
                val y = data.get(ValueLayout.JAVA_SHORT, base + XINERAMA_Y_OFFSET).toInt()
                val w = data.get(ValueLayout.JAVA_SHORT, base + XINERAMA_W_OFFSET).toInt()
                val h = data.get(ValueLayout.JAVA_SHORT, base + XINERAMA_H_OFFSET).toInt()
                if (w > 0 && h > 0) {
                    monitors.add(X11MonitorHandle(
                        id               = i.toLong(),
                        name             = null,
                        position         = PhysicalPosition(x, y),
                        scaleFactor      = scale,
                        currentVideoMode = VideoMode(PhysicalSize(w, h), null, null),
                        videoModes       = emptyList(),
                    ))
                }
            }
            // XFree the info array
            try { libX11?.find("XFree")?.map { linkerX11.downcallHandle(it, FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)) }
                ?.orElse(null)?.invokeExact(infoArr) } catch (_: Throwable) {}

            if (monitors.isEmpty()) null else monitors
        }
    } catch (_: Throwable) { null }
}

// ── Synthetic fallback ────────────────────────────────────────────────────────

private fun syntheticMonitor(display: MemorySegment, screen: Int, scale: Double): X11MonitorHandle {
    val w = try { xDisplayWidth?.invokeExact(display, screen) as? Int ?: 1920 } catch (_: Throwable) { 1920 }
    val h = try { xDisplayHeight?.invokeExact(display, screen) as? Int ?: 1080 } catch (_: Throwable) { 1080 }
    return X11MonitorHandle(
        id               = 0L,
        name             = null,
        position         = PhysicalPosition(0, 0),
        scaleFactor      = scale,
        currentVideoMode = VideoMode(PhysicalSize(w, h), null, null),
        videoModes       = emptyList(),
    )
}
