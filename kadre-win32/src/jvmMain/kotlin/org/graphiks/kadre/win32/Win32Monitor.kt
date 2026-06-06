/**
 * R2 — Monitor enumeration for the Win32 backend.
 *
 * Uses:
 *  - EnumDisplayMonitors  — enumerate all connected monitors
 *  - GetMonitorInfoW      — read MONITORINFOEX (name, rect, flags)
 *  - EnumDisplaySettingsW — enumerate display modes per adapter name
 *  - MonitorFromWindow    — find the monitor containing a given HWND
 *
 * All functions are loaded lazily; the file compiles on macOS/Linux
 * (null MethodHandles = graceful no-op).
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.VideoMode
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

// ── Win32 monitor constants ───────────────────────────────────────────────────

/** MONITORINFOF_PRIMARY — the monitor is the primary display. */
private const val MONITORINFOF_PRIMARY: Int = 0x00000001

/** MONITOR_DEFAULTTONEAREST — use the nearest monitor when the window is off-screen. */
private const val MONITOR_DEFAULTTONEAREST: Int = 0x00000002

/** ENUM_CURRENT_SETTINGS — use the current display mode. */
private const val ENUM_CURRENT_SETTINGS: Int = -1

/** ENUM_REGISTRY_SETTINGS — enumerate modes from the registry. */
private const val ENUM_REGISTRY_SETTINGS: Int = -2

// DEVMODE field offsets (DEVMODEW layout, Unicode, 220 bytes on Win32/64):
//   dmDeviceName: 32 WCHARs = 64 bytes     [0]
//   dmSpecVersion: WORD (2)                  [64]
//   dmDriverVersion: WORD (2)                [66]
//   dmSize: WORD (2)                         [68]
//   dmDriverExtra: WORD (2)                  [70]
//   dmFields: DWORD (4)                      [72]
//   (union / reserved 40 bytes)              [76..115]
//   dmColor: SHORT (2)                       [116]
//   dmDuplex: SHORT (2)                      [118]
//   dmYResolution: SHORT (2)                 [120]
//   dmTTOption: SHORT (2)                    [122]
//   dmCollate: SHORT (2)                     [124]
//   dmFormName: 32 WCHARs = 64 bytes         [126]
//   dmLogPixels: WORD (2)                    [190]
//   dmBitsPerPel: DWORD (4)                  [192]
//   dmPelsWidth:  DWORD (4)                  [196]
//   dmPelsHeight: DWORD (4)                  [200]
//   ...
//   dmDisplayFrequency: DWORD (4)            [212]
private const val DEVMODE_SIZE: Long = 220L
private const val DEVMODE_ALIGN: Long = 4L
private const val DEVMODE_BITS_PER_PEL_OFFSET: Long = 192L
private const val DEVMODE_PELS_WIDTH_OFFSET: Long = 196L
private const val DEVMODE_PELS_HEIGHT_OFFSET: Long = 200L
private const val DEVMODE_DISPLAY_FREQUENCY_OFFSET: Long = 212L

// MONITORINFOEXW layout (72 + 32 WCHARs = 72 + 64 = 136 bytes on 64-bit):
//   cbSize: DWORD (4)                        [0]
//   rcMonitor: RECT (16)                     [4]
//   rcWork: RECT (16)                        [20]
//   dwFlags: DWORD (4)                       [36]
//   szDevice: 32 WCHARs = 64 bytes           [40]
private const val MONITORINFOEX_SIZEOF: Int = 104
// With 64-bit alignment, cbSize is 4 bytes at offset 0; the padding may make size 104 bytes.
// On 64-bit Windows the MONITORINFOEXW struct is 104 bytes (with natural alignment).
private const val MONITORINFOEX_CB_SIZE_OFFSET: Long = 0L
private const val MONITORINFOEX_RC_MONITOR_OFFSET: Long = 4L    // RECT: left,top,right,bottom
private const val MONITORINFOEX_DW_FLAGS_OFFSET: Long = 36L
private const val MONITORINFOEX_SZ_DEVICE_OFFSET: Long = 40L    // WCHAR[32] = 64 bytes

// ── Lazy MethodHandles ────────────────────────────────────────────────────────

private val linkerW32: Linker = Linker.nativeLinker()

private fun SymbolLookup?.downcallW32(name: String, desc: FunctionDescriptor): MethodHandle? {
    this ?: return null
    return find(name).map { linkerW32.downcallHandle(it, desc) }.orElse(null)
}

internal val enumDisplayMonitors: MethodHandle? by lazy {
    user32.downcallW32("EnumDisplayMonitors", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,   // BOOL return
        ValueLayout.ADDRESS,    // HDC hdc (NULL)
        ValueLayout.ADDRESS,    // LPRECT lprcClip (NULL)
        ValueLayout.ADDRESS,    // MONITORENUMPROC lpfnEnum (upcall)
        ValueLayout.JAVA_LONG,  // LPARAM dwData
    ))
}

internal val getMonitorInfoW: MethodHandle? by lazy {
    user32.downcallW32("GetMonitorInfoW", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,   // BOOL
        ValueLayout.ADDRESS,    // HMONITOR hMonitor
        ValueLayout.ADDRESS,    // LPMONITORINFO lpmi
    ))
}

internal val enumDisplaySettingsW: MethodHandle? by lazy {
    user32.downcallW32("EnumDisplaySettingsW", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,   // BOOL
        ValueLayout.ADDRESS,    // LPCWSTR lpszDeviceName
        ValueLayout.JAVA_INT,   // DWORD iModeNum
        ValueLayout.ADDRESS,    // DEVMODEW *lpDevMode
    ))
}

internal val monitorFromWindow: MethodHandle? by lazy {
    user32.downcallW32("MonitorFromWindow", FunctionDescriptor.of(
        ValueLayout.ADDRESS,    // HMONITOR
        ValueLayout.ADDRESS,    // HWND hwnd
        ValueLayout.JAVA_INT,   // DWORD dwFlags
    ))
}

// ── Win32MonitorHandle ────────────────────────────────────────────────────────

/** Win32 implementation of [MonitorHandle]. */
internal class Win32MonitorHandle(
    override val id: Long,         // HMONITOR address
    override val name: String?,
    override val position: PhysicalPosition<Int>,
    val isPrimary: Boolean,
    val physicalWidth: Int,
    val physicalHeight: Int,
    override val scaleFactor: Double,
    override val currentVideoMode: VideoMode?,
    override val videoModes: List<VideoMode>,
) : MonitorHandle

// ── Monitor enumeration ───────────────────────────────────────────────────────

/**
 * Enumerates all connected monitors via EnumDisplayMonitors + GetMonitorInfoW.
 *
 * Returns an empty list on non-Windows platforms (MethodHandles are null).
 */
internal fun enumerateWin32Monitors(): List<Win32MonitorHandle> {
    val enumHandle = enumDisplayMonitors ?: return emptyList()
    val infoHandle = getMonitorInfoW ?: return emptyList()

    val handles = mutableListOf<Win32MonitorHandle>()

    // We need a shared arena that outlives the callback (upcall stubs must stay alive).
    Arena.ofConfined().use { arena ->
        // Upcall: BOOL CALLBACK MonitorEnumProc(HMONITOR, HDC, LPRECT, LPARAM)
        val mh = MethodHandles.lookup().findStatic(
            Win32MonitorEnumProc::class.java,
            "callback",
            MethodType.methodType(
                Int::class.java,
                MemorySegment::class.java,   // HMONITOR
                MemorySegment::class.java,   // HDC
                MemorySegment::class.java,   // LPRECT
                Long::class.java,            // LPARAM
            )
        )
        val desc = FunctionDescriptor.of(
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_LONG,
        )
        val stub = linkerW32.upcallStub(mh, desc, arena)

        // Reset the global accumulator before the enumeration
        Win32MonitorEnumProc.reset(infoHandle)

        try {
            enumHandle.invokeExact(
                MemorySegment.NULL, // HDC = NULL → all monitors
                MemorySegment.NULL, // clip rect = NULL
                stub,
                0L,                 // LPARAM (not used)
            ) as Int
        } catch (_: Throwable) {}

        handles.addAll(Win32MonitorEnumProc.harvest())
    }

    return handles
}

/**
 * Returns the monitor that contains (or is nearest to) the given HWND.
 */
internal fun win32MonitorFromHwnd(hwnd: MemorySegment): Win32MonitorHandle? {
    val fromWin = monitorFromWindow ?: return null
    val infoHandle = getMonitorInfoW ?: return null
    return try {
        val hMonitor = fromWin.invokeExact(hwnd, MONITOR_DEFAULTTONEAREST) as MemorySegment
        if (hMonitor == MemorySegment.NULL || hMonitor.address() == 0L) return null
        readMonitorInfo(hMonitor, infoHandle)
    } catch (_: Throwable) { null }
}

// ── MonitorEnumProc helper (JVM static callback) ─────────────────────────────

/**
 * Static callback class for EnumDisplayMonitors.
 *
 * EnumDisplayMonitors requires a C-callable function pointer (upcall stub).
 * Since upcall stubs must reference @JvmStatic methods, this object holds the
 * shared state used during one enumeration call.
 */
internal object Win32MonitorEnumProc {

    @Volatile private var infoHandle: MethodHandle? = null
    private val accumulated = mutableListOf<Win32MonitorHandle>()

    fun reset(infoHandle: MethodHandle) {
        this.infoHandle = infoHandle
        accumulated.clear()
    }

    fun harvest(): List<Win32MonitorHandle> = accumulated.toList()

    @JvmStatic
    fun callback(
        hMonitor: MemorySegment,
        @Suppress("UNUSED_PARAMETER") hDc: MemorySegment,
        @Suppress("UNUSED_PARAMETER") lpRect: MemorySegment,
        @Suppress("UNUSED_PARAMETER") lParam: Long,
    ): Int {
        val h = infoHandle ?: return 1 // continue enum
        try {
            val info = readMonitorInfo(hMonitor, h)
            if (info != null) accumulated.add(info)
        } catch (_: Throwable) {}
        return 1 // TRUE = continue enumeration
    }
}

// ── Read MONITORINFOEXW ───────────────────────────────────────────────────────

private fun readMonitorInfo(hMonitor: MemorySegment, infoHandle: MethodHandle): Win32MonitorHandle? {
    return try {
        Arena.ofConfined().use { arena ->
            val info = arena.allocate(MONITORINFOEX_SIZEOF.toLong(), 4L)
            // Set cbSize field
            info.set(ValueLayout.JAVA_INT, MONITORINFOEX_CB_SIZE_OFFSET, MONITORINFOEX_SIZEOF)
            val ok = infoHandle.invokeExact(hMonitor, info) as Int
            if (ok == 0) return null

            // rcMonitor: RECT = {left, top, right, bottom} in virtual screen coordinates
            val left   = info.get(ValueLayout.JAVA_INT, MONITORINFOEX_RC_MONITOR_OFFSET)
            val top    = info.get(ValueLayout.JAVA_INT, MONITORINFOEX_RC_MONITOR_OFFSET + 4)
            val right  = info.get(ValueLayout.JAVA_INT, MONITORINFOEX_RC_MONITOR_OFFSET + 8)
            val bottom = info.get(ValueLayout.JAVA_INT, MONITORINFOEX_RC_MONITOR_OFFSET + 12)
            val flags  = info.get(ValueLayout.JAVA_INT, MONITORINFOEX_DW_FLAGS_OFFSET)

            val width  = right - left
            val height = bottom - top

            // szDevice: WCHAR[32] at offset 40
            val deviceName = readWString(info, MONITORINFOEX_SZ_DEVICE_OFFSET, 32)

            // Current video mode via EnumDisplaySettingsW(ENUM_CURRENT_SETTINGS)
            val currentMode = readCurrentMode(arena, deviceName)
            val allModes = readAllModes(arena, deviceName)

            // Scale factor: physical DPI / 96. We use the current mode frequency as a proxy;
            // on Windows the scale factor is reported per-window (GetDpiForWindow), not per-monitor
            // directly via public API without extra Win8.1+ calls. We fall back to 1.0 here.
            // A more precise implementation would use GetDpiForMonitor (shcore.dll, Win8.1+).
            val scale = 1.0

            Win32MonitorHandle(
                id            = hMonitor.address(),
                name          = deviceName.ifEmpty { null },
                position      = PhysicalPosition(left, top),
                isPrimary     = (flags and MONITORINFOF_PRIMARY) != 0,
                physicalWidth = width,
                physicalHeight= height,
                scaleFactor   = scale,
                currentVideoMode = currentMode,
                videoModes    = allModes,
            )
        }
    } catch (_: Throwable) { null }
}

private fun readCurrentMode(arena: Arena, deviceName: String): VideoMode? {
    val enumHandle = enumDisplaySettingsW ?: return null
    return try {
        val devMode = arena.allocate(DEVMODE_SIZE, DEVMODE_ALIGN)
        devMode.set(ValueLayout.JAVA_SHORT, 68L, 220.toShort()) // dmSize = sizeof(DEVMODEW), required by EnumDisplaySettingsW
        val namePtr = arena.allocateWString(deviceName)
        val ok = enumHandle.invokeExact(namePtr, ENUM_CURRENT_SETTINGS, devMode) as Int
        if (ok == 0) return null
        val w    = devMode.get(ValueLayout.JAVA_INT, DEVMODE_PELS_WIDTH_OFFSET)
        val h    = devMode.get(ValueLayout.JAVA_INT, DEVMODE_PELS_HEIGHT_OFFSET)
        val bpp  = devMode.get(ValueLayout.JAVA_INT, DEVMODE_BITS_PER_PEL_OFFSET)
        val freq = devMode.get(ValueLayout.JAVA_INT, DEVMODE_DISPLAY_FREQUENCY_OFFSET)
        VideoMode(
            size              = PhysicalSize(w, h),
            bitDepth          = if (bpp > 0) bpp / 3 else null, // bits per channel ≈ total / 3
            refreshRateMilliHz = if (freq > 0) freq * 1000 else null,
        )
    } catch (_: Throwable) { null }
}

private fun readAllModes(arena: Arena, deviceName: String): List<VideoMode> {
    val enumHandle = enumDisplaySettingsW ?: return emptyList()
    val modes = mutableListOf<VideoMode>()
    return try {
        val devMode = arena.allocate(DEVMODE_SIZE, DEVMODE_ALIGN)
        devMode.set(ValueLayout.JAVA_SHORT, 68L, 220.toShort()) // dmSize = sizeof(DEVMODEW), required by EnumDisplaySettingsW
        val namePtr = arena.allocateWString(deviceName)
        var i = 0
        while (true) {
            val ok = enumHandle.invokeExact(namePtr, i, devMode) as Int
            if (ok == 0) break
            val w    = devMode.get(ValueLayout.JAVA_INT, DEVMODE_PELS_WIDTH_OFFSET)
            val h    = devMode.get(ValueLayout.JAVA_INT, DEVMODE_PELS_HEIGHT_OFFSET)
            val bpp  = devMode.get(ValueLayout.JAVA_INT, DEVMODE_BITS_PER_PEL_OFFSET)
            val freq = devMode.get(ValueLayout.JAVA_INT, DEVMODE_DISPLAY_FREQUENCY_OFFSET)
            if (w > 0 && h > 0) {
                modes.add(VideoMode(
                    size               = PhysicalSize(w, h),
                    bitDepth           = if (bpp > 0) bpp / 3 else null,
                    refreshRateMilliHz = if (freq > 0) freq * 1000 else null,
                ))
            }
            i++
            if (i > 256) break // safety cap
        }
        modes
    } catch (_: Throwable) { modes }
}

/** Reads a null-terminated WCHAR string from a MemorySegment at a given offset. */
private fun readWString(seg: MemorySegment, offset: Long, maxChars: Int): String {
    val chars = StringBuilder()
    for (i in 0 until maxChars) {
        val c = seg.get(ValueLayout.JAVA_SHORT, offset + i * 2L).toInt().toChar()
        if (c == ' ') break
        chars.append(c)
    }
    return chars.toString()
}
