package org.graphiks.kadre.win32.capture

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.*
import org.graphiks.kadre.ffi.win32.*
import org.graphiks.kadre.win32.*
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

class Win32ScreenCapturer : ScreenCapturer {

    override suspend fun enumerateDisplays(): List<DisplayInfo> {
        val monitors = enumerateWin32Monitors()
        if (monitors.isEmpty()) return emptyList()
        return monitors.map { m ->
            DisplayInfo(
                id = m.id,
                name = m.name,
                position = m.position,
                resolution = PhysicalSize(m.physicalWidth, m.physicalHeight),
                scaleFactor = m.scaleFactor,
            )
        }
    }

    override suspend fun enumerateWindows(): List<WindowInfo> {
        val getDesktop = getDesktopWindow ?: return emptyList()
        val getWin = getWindow ?: return emptyList()
        val getWR = getWindowRect ?: return emptyList()
        val getWT = getWindowTextW ?: return emptyList()
        val isVis = isWindowVisible ?: return emptyList()
        val getWTPid = getWindowThreadProcessId ?: return emptyList()

        return try {
            val desktop = getDesktop.invokeExact() as MemorySegment
            val windows = mutableListOf<WindowInfo>()

            Arena.ofConfined().use { arena ->
                    var hwnd = getWin.invokeExact(desktop, GW_CHILD) as MemorySegment
                    var iterations = 0
                    while (hwnd.address() != 0L && iterations < 10000) {
                    val visible = (isVis.invokeExact(hwnd) as Int) != 0
                    if (visible) {
                        val rect = arena.allocate(RECT_SIZE, RECT_ALIGN)
                        val rectOk = getWR.invokeExact(hwnd, rect) as Int
                        if (rectOk != 0) {
                            val left = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_LEFT)
                            val top = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_TOP)
                            val right = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_RIGHT)
                            val bottom = rect.get(ValueLayout.JAVA_INT, RECT_OFFSET_BOTTOM)
                            val width = right - left
                            val height = bottom - top

                            val titleBuf = arena.allocate(512L, 2L)
                            val chars = getWT.invokeExact(hwnd, titleBuf, 256) as Int

                            val title = if (chars > 0) {
                                readWideString(titleBuf, chars)
                            } else null

                            val appName = getApplicationName(hwnd, getWTPid)

                            windows.add(WindowInfo(
                                id = hwnd.address(),
                                title = title,
                                applicationName = appName,
                                position = PhysicalPosition(left, top),
                                size = PhysicalSize(width, height),
                            ))
                        }
                    }
                    hwnd = getWin.invokeExact(hwnd, GW_HWNDNEXT) as MemorySegment
                    iterations++
                }
            }

            windows
        } catch (_: Throwable) {
            emptyList()
        }
    }

    override suspend fun createSession(
        source: CaptureSource,
        config: CaptureConfig,
    ): CaptureSession {
        return when (source) {
            is CaptureSource.Display -> {
                val rect = resolveDisplayRect(source.id)
                Win32CaptureSession(source, config, hwnd = null, rect = rect)
            }
            is CaptureSource.Window -> {
                Win32CaptureSession(source, config, hwnd = source.id, rect = null)
            }
        }
    }

    override suspend fun requestPermission(): CapturePermission =
        CapturePermission.Granted

    override fun permissionStatus(): CapturePermission =
        CapturePermission.Granted

    private fun resolveDisplayRect(displayId: Long): Win32MonitorRect? {
        val infoHandle = getMonitorInfoW ?: return null
        return try {
            Arena.ofConfined().use { arena ->
                val info = arena.allocate(104L, 4L)
                info.set(ValueLayout.JAVA_INT, 0L, 104)
                val hMonitor = MemorySegment.ofAddress(displayId)
                val ok = infoHandle.invokeExact(hMonitor, info) as Int
                if (ok == 0) return null

                val left = info.get(ValueLayout.JAVA_INT, 4L)
                val top = info.get(ValueLayout.JAVA_INT, 8L)
                val right = info.get(ValueLayout.JAVA_INT, 12L)
                val bottom = info.get(ValueLayout.JAVA_INT, 16L)

                Win32MonitorRect(left, top, right, bottom)
            }
        } catch (_: Throwable) {
            null
        }
    }
}



private fun getApplicationName(hwnd: MemorySegment, getWTPid: MethodHandle): String? {
    val openProc = openProcess ?: return null
    val queryImage = queryFullProcessImageNameW ?: return null
    val closeH = closeHandle ?: return null

    return try {
        Arena.ofConfined().use { arena ->
            val pidPtr = arena.allocate(ValueLayout.JAVA_INT)
            getWTPid.invokeExact(hwnd, pidPtr) as Int
            val pid = pidPtr.get(ValueLayout.JAVA_INT, 0L)
            if (pid == 0) return null

            val hProcess = openProc.invokeExact(
                PROCESS_QUERY_LIMITED_INFORMATION, 0, pid
            ) as MemorySegment
            if (hProcess.address() == 0L) return null

            try {
                val buf = arena.allocate(520L, 2L)
                val sizePtr = arena.allocate(ValueLayout.JAVA_INT)
                sizePtr.set(ValueLayout.JAVA_INT, 0L, 260)

                val ok = queryImage.invokeExact(hProcess, 0, buf, sizePtr) as Int
                if (ok == 0) return null

                val len = sizePtr.get(ValueLayout.JAVA_INT, 0L)
                readWideString(buf, len)
            } finally {
                try { closeH.invokeExact(hProcess) } catch (_: Throwable) {}
            }
        }
    } catch (_: Throwable) {
        null
    }
}

internal fun readWideString(seg: MemorySegment, maxChars: Int): String {
    val sb = StringBuilder()
    for (i in 0 until maxChars.coerceAtMost(260)) {
        val c = seg.get(ValueLayout.JAVA_SHORT, i * 2L).toInt().toChar()
        if (c.code == 0) break
        sb.append(c)
    }
    return sb.toString()
}


