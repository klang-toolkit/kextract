package org.graphiks.kadre.x11

import org.graphiks.kadre.core.PhysicalPosition
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

internal object X11DragAndDrop {

    private const val XDND_VERSION: Long = 5L

    fun sendXdndStatus(
        display: MemorySegment,
        targetWindow: Long,
        sourceWindow: Long,
        accept: Boolean,
        x: Int,
        y: Int,
    ) {
        val xdndStatusAtom = x11DragAndDropAtom(display, "XdndStatus")
        if (xdndStatusAtom == 0L) return
        val xSendEvent = xSendEvent ?: return
        try {
            Arena.ofConfined().use { arena ->
                val eventBuf = arena.allocate(96L, 8L)
                eventBuf.set(ValueLayout.JAVA_INT, 0L, ClientMessage)
                eventBuf.set(ValueLayout.JAVA_LONG, 32L, targetWindow)
                eventBuf.set(ValueLayout.JAVA_LONG, 40L, xdndStatusAtom)
                eventBuf.set(ValueLayout.JAVA_INT, 48L, 32)
                eventBuf.set(ValueLayout.JAVA_LONG, 56L, sourceWindow)
                eventBuf.set(ValueLayout.JAVA_LONG, 64L,
                    if (accept) 1L else 0L)
                eventBuf.set(ValueLayout.JAVA_LONG, 72L,
                    (x.toLong() shl 16) or (y.toLong() and 0xFFFFL))
                eventBuf.set(ValueLayout.JAVA_LONG, 80L, 0L)
                eventBuf.set(ValueLayout.JAVA_LONG, 88L,
                    (1L shl 2) or 1L)
                xSendEvent.invokeExact(display, sourceWindow, 0, 0L, eventBuf) as? Int
                xFlush?.invokeExact(display) as? Int
            }
        } catch (_: Throwable) {}
    }

    fun sendXdndFinished(
        display: MemorySegment,
        targetWindow: Long,
        sourceWindow: Long,
        accept: Boolean,
    ) {
        val xdndFinishedAtom = x11DragAndDropAtom(display, "XdndFinished")
        if (xdndFinishedAtom == 0L) return
        val xSendEvent = xSendEvent ?: return
        try {
            Arena.ofConfined().use { arena ->
                val eventBuf = arena.allocate(96L, 8L)
                eventBuf.set(ValueLayout.JAVA_INT, 0L, ClientMessage)
                eventBuf.set(ValueLayout.JAVA_LONG, 32L, targetWindow)
                eventBuf.set(ValueLayout.JAVA_LONG, 40L, xdndFinishedAtom)
                eventBuf.set(ValueLayout.JAVA_INT, 48L, 32)
                eventBuf.set(ValueLayout.JAVA_LONG, 56L, sourceWindow)
                eventBuf.set(ValueLayout.JAVA_LONG, 64L,
                    if (accept) 1L else 0L)
                eventBuf.set(ValueLayout.JAVA_LONG, 72L, 0L)
                eventBuf.set(ValueLayout.JAVA_LONG, 80L, 0L)
                eventBuf.set(ValueLayout.JAVA_LONG, 88L, 0L)
                xSendEvent.invokeExact(display, sourceWindow, 0, 0L, eventBuf) as? Int
                xFlush?.invokeExact(display) as? Int
            }
        } catch (_: Throwable) {}
    }

    fun requestDropData(
        display: MemorySegment,
        targetWindow: Long,
        xdndSelectionAtom: Long,
        targetAtom: Long,
        time: Long,
    ): Boolean {
        val convert = xConvertSelection ?: return false
        return try {
            val status = convert.invokeExact(
                display,
                xdndSelectionAtom,
                targetAtom,
                xdndSelectionAtom,
                targetWindow,
                time,
            ) as Int
            status != 0
        } catch (_: Throwable) {
            false
        }
    }

    fun readSelectionProperty(
        getProperty: java.lang.invoke.MethodHandle?,
        free: java.lang.invoke.MethodHandle?,
        display: MemorySegment,
        window: Long,
        property: Long,
    ): String? {
        if (getProperty == null) return null
        return try {
            Arena.ofConfined().use { arena ->
                val actualType = arena.allocate(ValueLayout.JAVA_LONG)
                val actualFormat = arena.allocate(ValueLayout.JAVA_INT)
                val nitems = arena.allocate(ValueLayout.JAVA_LONG)
                val bytesAfter = arena.allocate(ValueLayout.JAVA_LONG)
                val propReturn = arena.allocate(ValueLayout.ADDRESS)
                val status = getProperty.invokeExact(
                    display,
                    window,
                    property,
                    0L,
                    0x7FFFFFFF.toLong(),
                    0,
                    0L,
                    actualType,
                    actualFormat,
                    nitems,
                    bytesAfter,
                    propReturn,
                ) as Int
                if (status != 0) return@use null
                val ptr = propReturn.get(ValueLayout.ADDRESS, 0L)
                if (ptr == MemorySegment.NULL || ptr.address() == 0L) return@use null
                try {
                    val count = nitems.get(ValueLayout.JAVA_LONG, 0L)
                    val format = actualFormat.get(ValueLayout.JAVA_INT, 0L)
                    if (count <= 0L || format == 0) return@use null
                    val byteSize = when (format) {
                        8 -> count
                        16 -> count * 2L
                        32 -> count * 8L
                        else -> return@use null
                    }
                    val bytes = ByteArray(byteSize.toInt())
                    for (i in bytes.indices) {
                        bytes[i] = ptr.get(ValueLayout.JAVA_BYTE, i.toLong())
                    }
                    bytes.toString(Charsets.UTF_8).trimEnd('\u0000')
                } finally {
                    if (free != null) {
                        try { free.invokeExact(ptr) as Int } catch (_: Throwable) {}
                    }
                }
            }
        } catch (_: Throwable) {
            null
        }
    }

    fun parseUriList(data: String): List<String> {
        if (data.isBlank()) return emptyList()
        val result = mutableListOf<String>()
        for (line in data.lines()) {
            val trimmed = line.trim()
            if (trimmed.isEmpty() || trimmed.startsWith('#')) continue
            val path = uriToPath(trimmed) ?: trimmed
            result.add(path)
        }
        return result
    }

    private fun uriToPath(uri: String): String? {
        if (!uri.startsWith("file://")) return null
        val path = uri.removePrefix("file://")
        return decodePercent(path)
    }

    private fun decodePercent(s: String): String {
        val sb = StringBuilder(s.length)
        var i = 0
        while (i < s.length) {
            val c = s[i]
            if (c == '%' && i + 2 < s.length) {
                val hi = s[i + 1].digitToIntOrNull(16)
                val lo = s[i + 2].digitToIntOrNull(16)
                if (hi != null && lo != null) {
                    sb.append((hi * 16 + lo).toChar())
                    i += 3
                    continue
                }
            }
            sb.append(c)
            i++
        }
        return sb.toString()
    }
}

internal fun x11DragAndDropAtom(display: MemorySegment, name: String): Long {
    val intern = xInternAtom ?: return 0L
    return try {
        Arena.ofConfined().use { arena ->
            val bytes = name.toByteArray(Charsets.US_ASCII)
            val ptr = arena.allocate(bytes.size.toLong() + 1)
            for (i in bytes.indices) ptr.set(ValueLayout.JAVA_BYTE, i.toLong(), bytes[i])
            ptr.set(ValueLayout.JAVA_BYTE, bytes.size.toLong(), 0)
            intern.invokeExact(display, ptr, 0) as Long
        }
    } catch (_: Throwable) {
        0L
    }
}
