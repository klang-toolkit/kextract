package org.graphiks.kadre.x11.capture

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.*
import org.graphiks.kadre.ffi.x11.*
import org.graphiks.kadre.ffi.x11.capture.*
import org.graphiks.kadre.x11.enumerateX11Monitors

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class X11ScreenCapturer : ScreenCapturer {

    override suspend fun enumerateDisplays(): List<DisplayInfo> {
        val displayPtr = openDisplay() ?: return emptyList()
        return try {
            val display = MemorySegment.ofAddress(displayPtr)
            val screen = defaultScreenNumber(display)
            val monitors = enumerateX11Monitors(displayPtr, screen, 1.0)
            monitors.map { m ->
                DisplayInfo(
                    id = m.id,
                    name = m.name,
                    position = m.position,
                    resolution = (m.currentVideoMode?.size ?: PhysicalSize(0, 0)),
                    scaleFactor = m.scaleFactor,
                )
            }
        } finally {
            closeDisplay(displayPtr)
        }
    }

    override suspend fun enumerateWindows(): List<WindowInfo> {
        val displayPtr = openDisplay() ?: return emptyList()
        return try {
            val display = MemorySegment.ofAddress(displayPtr)
            enumerateX11Windows(display, displayPtr)
        } finally {
            closeDisplay(displayPtr)
        }
    }

    override suspend fun createSession(
        source: CaptureSource,
        config: CaptureConfig,
    ): CaptureSession {
        val displayPtr = openDisplay()
            ?: throw CaptureError.Internal(RuntimeException("Failed to open X11 display"))
        return X11CaptureSession(source, config, displayPtr)
    }

    override suspend fun requestPermission(): CapturePermission =
        CapturePermission.Granted

    override fun permissionStatus(): CapturePermission =
        CapturePermission.Granted

    // ── Internal helpers ───────────────────────────────────────────────────

    private fun openDisplay(): Long? {
        val xOpen = xOpenDisplay ?: return null
        return try {
            val result = xOpen.invokeExact(MemorySegment.NULL) as MemorySegment
            if (result == MemorySegment.NULL || result.address() == 0L) null
            else result.address()
        } catch (_: Throwable) { null }
    }

    private fun closeDisplay(ptr: Long) {
        val xClose = xCloseDisplay ?: return
        try {
            xClose.invokeExact(MemorySegment.ofAddress(ptr))
        } catch (_: Throwable) {}
    }

    private fun defaultScreenNumber(display: MemorySegment): Int {
        val fn = xDefaultScreen ?: return 0
        return try { fn.invokeExact(display) as Int } catch (_: Throwable) { 0 }
    }
}

// ── Window enumeration ──────────────────────────────────────────────────────────

internal fun enumerateX11Windows(
    display: MemorySegment,
    displayPtr: Long,
): List<WindowInfo> {
    val rootWindow = xDefaultRootWindow ?: return emptyList()
    val queryTree = xQueryTree ?: return emptyList()
    val root = try {
        rootWindow.invokeExact(display) as Long
    } catch (_: Throwable) { return emptyList() }

    // Try _NET_CLIENT_LIST first
    val netClientList = getNetClientList(display, root)
    val windowIds = if (netClientList != null) netClientList else {
        // Fallback: all children via XQueryTree
        val children = queryTreeChildren(display, root, queryTree)
        children ?: return emptyList()
    }

    return windowIds.mapNotNull { wid ->
        windowInfoFromId(display, displayPtr, wid)
    }
}

private fun getNetClientList(display: MemorySegment, root: Long): List<Long>? {
    val xIntern = xInternAtom ?: return null
    val xGetProp = xGetWindowProperty ?: return null
    val xFreeFn = xFree ?: return null

    return try {
        Arena.ofConfined().use { arena ->
            val netClientListAtom = xIntern.invokeExact(
                display, arena.allocateFrom("_NET_CLIENT_LIST"), 0
            ) as Long
            if (netClientListAtom == 0L) return@use null

            val actualType = arena.allocate(ValueLayout.JAVA_LONG)
            val actualFormat = arena.allocate(ValueLayout.JAVA_INT)
            val nitems = arena.allocate(ValueLayout.JAVA_LONG)
            val bytesAfter = arena.allocate(ValueLayout.JAVA_LONG)
            val propPtr = arena.allocate(ValueLayout.ADDRESS)

            val status = xGetProp.invokeExact(
                display, root, netClientListAtom,
                0L, 0x7FFFFFFFL,  // offset=0, length=max
                0L,  // delete=False
                19L, // XA_WINDOW = 19
                actualType, actualFormat, nitems, bytesAfter, propPtr,
            ) as Int

            if (status != 0) {
                val dataPtr = propPtr.get(ValueLayout.ADDRESS, 0L)
                if (dataPtr == MemorySegment.NULL || dataPtr.address() == 0L) return@use null
                val count = nitems.get(ValueLayout.JAVA_LONG, 0L).toInt()
                if (count <= 0) return@use null

                val windows = mutableListOf<Long>()
                val data = dataPtr.reinterpret(count.toLong() * 8L)
                for (i in 0 until count) {
                    windows.add(data.get(ValueLayout.JAVA_LONG, i.toLong() * 8L))
                }
                try { xFreeFn.invokeExact(dataPtr) } catch (_: Throwable) {}
                windows
            } else null
        }
    } catch (_: Throwable) { null }
}

internal fun queryTreeChildren(
    display: MemorySegment,
    window: Long,
    queryTree: java.lang.invoke.MethodHandle? = xQueryTree,
): List<Long>? {
    val qt = queryTree ?: return null
    return try {
        Arena.ofConfined().use { arena ->
            val rootRet = arena.allocate(ValueLayout.JAVA_LONG)
            val parentRet = arena.allocate(ValueLayout.JAVA_LONG)
            val childrenRet = arena.allocate(ValueLayout.ADDRESS)
            val nchildrenRet = arena.allocate(ValueLayout.JAVA_INT)

            val status = qt.invokeExact(
                display, window,
                rootRet, parentRet, childrenRet, nchildrenRet,
            ) as Int
            if (status == 0) return@use null

            val childrenPtr = childrenRet.get(ValueLayout.ADDRESS, 0L)
            val nchildren = nchildrenRet.get(ValueLayout.JAVA_INT, 0L)
            if (childrenPtr == MemorySegment.NULL || childrenPtr.address() == 0L || nchildren <= 0) return@use null

            val windows = mutableListOf<Long>()
            val data = childrenPtr.reinterpret(nchildren.toLong() * 8L)
            for (i in 0 until nchildren) {
                windows.add(data.get(ValueLayout.JAVA_LONG, i.toLong() * 8L))
            }
            try { xFree?.invokeExact(childrenPtr) } catch (_: Throwable) {}
            windows
        }
    } catch (_: Throwable) { null }
}

internal fun windowInfoFromId(
    display: MemorySegment,
    displayPtr: Long,
    windowId: Long,
): WindowInfo? {
    return try {
        val xGetGeom = xGetGeometry ?: return null
        val xGetProp = xGetWindowProperty ?: return null
        val xFreeFn = xFree ?: return null
        val xIntern = xInternAtom ?: return null

        Arena.ofConfined().use { arena ->
            // Check if window is viewable
            val attrFn = xGetWindowAttributes
            if (attrFn != null) {
                val attrBuf = arena.allocate(128L, 8L)
                val attrStatus = attrFn.invokeExact(display, windowId, attrBuf) as Int
                if (attrStatus == 0) return@use null
                val mapState = attrBuf.get(ValueLayout.JAVA_INT, XWINDOWATTR_MAP_STATE_OFFSET)
                if (mapState != IsViewable) return@use null
            }

            // Get geometry for position and size
            val rootRet = arena.allocate(ValueLayout.JAVA_LONG)
            val xRet = arena.allocate(ValueLayout.JAVA_INT)
            val yRet = arena.allocate(ValueLayout.JAVA_INT)
            val wRet = arena.allocate(ValueLayout.JAVA_INT)
            val hRet = arena.allocate(ValueLayout.JAVA_INT)
            val bwRet = arena.allocate(ValueLayout.JAVA_INT)
            val depthRet = arena.allocate(ValueLayout.JAVA_INT)

            val geomStatus = xGetGeom.invokeExact(
                display, windowId,
                rootRet, xRet, yRet, wRet, hRet, bwRet, depthRet,
            ) as Int
            if (geomStatus == 0) return@use null

            val x = xRet.get(ValueLayout.JAVA_INT, 0L)
            val y = yRet.get(ValueLayout.JAVA_INT, 0L)
            val width = wRet.get(ValueLayout.JAVA_INT, 0L)
            val height = hRet.get(ValueLayout.JAVA_INT, 0L)
            if (width <= 0 || height <= 0) return@use null

            // Convert to root coordinates
            val translateFn = xTranslateCoordinates
            var rootX = x
            var rootY = y
            if (translateFn != null) {
                try {
                    val destX = arena.allocate(ValueLayout.JAVA_INT)
                    val destY = arena.allocate(ValueLayout.JAVA_INT)
                    val childRet = arena.allocate(ValueLayout.JAVA_LONG)
                    val translateStatus = translateFn.invokeExact(
                        display, windowId, rootRet.get(ValueLayout.JAVA_LONG, 0L),
                        x, y, destX, destY, childRet,
                    ) as Int
                    if (translateStatus != 0) {
                        rootX = destX.get(ValueLayout.JAVA_INT, 0L)
                        rootY = destY.get(ValueLayout.JAVA_INT, 0L)
                    }
                } catch (_: Throwable) {}
            }

            // Get window title (_NET_WM_NAME)
            val title = try {
                val netWmNameAtom = xIntern.invokeExact(
                    display, arena.allocateFrom("_NET_WM_NAME"), 0
                ) as Long
                if (netWmNameAtom != 0L) {
                    readWindowPropertyUtf8(display, windowId, netWmNameAtom, xGetProp, xFreeFn, arena)
                } else null
            } catch (_: Throwable) {
                null
            }

            // Fallback title: WM_NAME
            val finalTitle = title ?: try {
                val wmNameAtom = xIntern.invokeExact(
                    display, arena.allocateFrom("WM_NAME"), 0
                ) as Long
                if (wmNameAtom != 0L) {
                    readWindowPropertyUtf8(display, windowId, wmNameAtom, xGetProp, xFreeFn, arena)
                } else null
            } catch (_: Throwable) { null }

            // Application name from _NET_WM_PID
            val applicationName = try {
                val netWmPidAtom = xIntern.invokeExact(
                    display, arena.allocateFrom("_NET_WM_PID"), 0
                ) as Long
                if (netWmPidAtom != 0L) null else null // PID resolution not implemented
            } catch (_: Throwable) { null }

            WindowInfo(
                id = windowId,
                title = finalTitle,
                applicationName = applicationName,
                position = PhysicalPosition(rootX, rootY),
                size = PhysicalSize(width, height),
            )
        }
    } catch (_: Throwable) { null }
}

internal fun readWindowPropertyUtf8(
    display: MemorySegment,
    window: Long,
    atom: Long,
    getProp: java.lang.invoke.MethodHandle,
    freeFn: java.lang.invoke.MethodHandle,
    arena: Arena,
): String? {
    return try {
        val actualType = arena.allocate(ValueLayout.JAVA_LONG)
        val actualFormat = arena.allocate(ValueLayout.JAVA_INT)
        val nitems = arena.allocate(ValueLayout.JAVA_LONG)
        val bytesAfter = arena.allocate(ValueLayout.JAVA_LONG)
        val propPtr = arena.allocate(ValueLayout.ADDRESS)

        val status = getProp.invokeExact(
            display, window, atom,
            0L, 0x7FFFFFFFL,
            0L,
            0L,  // AnyPropertyType
            actualType, actualFormat, nitems, bytesAfter, propPtr,
        ) as Int

        if (status == 0) return null

        val dataPtr = propPtr.get(ValueLayout.ADDRESS, 0L)
        if (dataPtr == MemorySegment.NULL || dataPtr.address() == 0L) return null
        val count = nitems.get(ValueLayout.JAVA_LONG, 0L).toInt()
        if (count <= 0) return null

        val format = actualFormat.get(ValueLayout.JAVA_INT, 0L)
        val str: String? = if (format == 8 && count > 0) {
            val data = dataPtr.reinterpret(count.toLong())
            val bytes = ByteArray(count)
            MemorySegment.copy(data, ValueLayout.JAVA_BYTE, 0, bytes, 0, count)
            // Find null terminator and decode as UTF-8
            val nullIdx = bytes.indexOf(0)
            val textBytes = if (nullIdx >= 0) bytes.copyOfRange(0, nullIdx) else bytes
            textBytes.toString(Charsets.UTF_8)
        } else {
            null
        }

        try { freeFn.invokeExact(dataPtr) } catch (_: Throwable) {}
        str
    } catch (_: Throwable) { null }
}
