package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

internal const val WL_DATA_DEVICE_MANAGER_VERSION: Int = 3
private const val WL_DATA_OFFER_ACCEPT_OPCODE: Int = 0
private const val WL_DATA_OFFER_RECEIVE_OPCODE: Int = 1
private const val WL_DATA_OFFER_DESTROY_OPCODE: Int = 2
private const val WL_DATA_OFFER_FINISH_OPCODE: Int = 3

/**
 * wl_data_device listener vtable order:
 *   0: data_offer(data, device, id)
 *   1: enter(data, device, serial, surface, x, y, id)
 *   2: leave(data, device)
 *   3: motion(data, device, time, x, y)
 *   4: drop(data, device)
 *   5: selection(data, device, id)
 */
private class WlDataDeviceListener(
    private val dnd: WaylandDragAndDrop,
) {
    @Suppress("UNUSED_PARAMETER")
    fun onDataOffer(data: MemorySegment, device: MemorySegment, offer: MemorySegment) {
        dnd.onDataOffer(offer.address())
    }

    @Suppress("UNUSED_PARAMETER")
    fun onEnter(
        data: MemorySegment, device: MemorySegment,
        serial: Int, surface: MemorySegment, xFixed: Int, yFixed: Int, offer: MemorySegment,
    ) {
        dnd.onEnter(serial, surface.address(), xFixed, yFixed, offer.address())
    }

    @Suppress("UNUSED_PARAMETER")
    fun onLeave(data: MemorySegment, device: MemorySegment) {
        dnd.onLeave()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onMotion(data: MemorySegment, device: MemorySegment, time: Int, xFixed: Int, yFixed: Int) {
        dnd.onMotion(xFixed, yFixed)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onDrop(data: MemorySegment, device: MemorySegment) {
        dnd.onDrop()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onSelection(data: MemorySegment, device: MemorySegment, offer: MemorySegment) { /* no-op */ }
}

/**
 * Handles Drag & Drop events from `wl_data_device`.
 *
 * One instance per seat. Created when the `wl_data_device_manager` global is
 * available and a `wl_data_device` has been obtained via `get_data_device`.
 *
 * @param dataDevicePtr  Address of the bound `wl_data_device*`.
 * @param displayPtr     Address of the `wl_display*` for flush.
 * @param onEvent        Sink for emitted [WindowEvent]s (routed to the event queue).
 */
internal class WaylandDragAndDrop(
    private val dataDevicePtr: Long,
    private val displayPtr: Long,
    private val onEvent: (surfacePtr: Long, event: WindowEvent) -> Unit,
) {
    private var currentOffer: Long = 0L
    private var currentSerial: Int = 0
    private var currentSurface: Long = 0L
    private var lastFixedX: Int = 0
    private var lastFixedY: Int = 0

    /**
     * Installs the wl_data_device listener. Called once after construction.
     */
    fun installListener(arena: Arena, addListener: java.lang.invoke.MethodHandle) {
        val listener = WlDataDeviceListener(this)
        val lookup = MethodHandles.lookup()
        val ptr = ValueLayout.ADDRESS.byteSize()

        val dataOfferStub = upcallStub(
            lookup.findVirtual(WlDataDeviceListener::class.java, "onDataOffer",
                MethodType.methodType(Void.TYPE,
                    MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java,
                )).bindTo(listener),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
            arena,
        )
        val enterStub = upcallStub(
            lookup.findVirtual(WlDataDeviceListener::class.java, "onEnter",
                MethodType.methodType(Void.TYPE,
                    MemorySegment::class.java, MemorySegment::class.java,
                    Int::class.javaPrimitiveType, MemorySegment::class.java,
                    Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, MemorySegment::class.java,
                )).bindTo(listener),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS,
                ValueLayout.JAVA_INT, ValueLayout.ADDRESS,
                ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS),
            arena,
        )
        val leaveStub = upcallStub(
            lookup.findVirtual(WlDataDeviceListener::class.java, "onLeave",
                MethodType.methodType(Void.TYPE,
                    MemorySegment::class.java, MemorySegment::class.java,
                )).bindTo(listener),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
            arena,
        )
        val motionStub = upcallStub(
            lookup.findVirtual(WlDataDeviceListener::class.java, "onMotion",
                MethodType.methodType(Void.TYPE,
                    MemorySegment::class.java, MemorySegment::class.java,
                    Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
                )).bindTo(listener),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS,
                ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
            arena,
        )
        val dropStub = upcallStub(
            lookup.findVirtual(WlDataDeviceListener::class.java, "onDrop",
                MethodType.methodType(Void.TYPE,
                    MemorySegment::class.java, MemorySegment::class.java,
                )).bindTo(listener),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
            arena,
        )
        val selectionStub = upcallStub(
            lookup.findVirtual(WlDataDeviceListener::class.java, "onSelection",
                MethodType.methodType(Void.TYPE,
                    MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java,
                )).bindTo(listener),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
            arena,
        )

        val vtable = arena.allocate(ptr * 6)
        vtable.set(ValueLayout.ADDRESS, 0L,      dataOfferStub)
        vtable.set(ValueLayout.ADDRESS, ptr,     enterStub)
        vtable.set(ValueLayout.ADDRESS, ptr * 2, leaveStub)
        vtable.set(ValueLayout.ADDRESS, ptr * 3, motionStub)
        vtable.set(ValueLayout.ADDRESS, ptr * 4, dropStub)
        vtable.set(ValueLayout.ADDRESS, ptr * 5, selectionStub)
        runCatching { addListener.invokeExact(MemorySegment.ofAddress(dataDevicePtr), vtable, MemorySegment.NULL) as Int }
    }

    // ── wl_data_device event handlers ─────────────────────────────────────────

    fun onDataOffer(offerPtr: Long) {
        cleanupCurrentOffer()
        currentOffer = offerPtr
    }

    fun onEnter(serial: Int, surfacePtr: Long, xFixed: Int, yFixed: Int, offerPtr: Long) {
        currentSerial = serial
        currentSurface = surfacePtr
        lastFixedX = xFixed
        lastFixedY = yFixed

        acceptOffer("text/uri-list")

        val position = PhysicalPosition(wlFixedToDouble(xFixed), wlFixedToDouble(yFixed))
        onEvent(surfacePtr, WindowEvent.DragEntered(position, emptyList()))
    }

    fun onMotion(xFixed: Int, yFixed: Int) {
        if (currentSurface == 0L) return
        lastFixedX = xFixed
        lastFixedY = yFixed
        val position = PhysicalPosition(wlFixedToDouble(xFixed), wlFixedToDouble(yFixed))
        onEvent(currentSurface, WindowEvent.DragMoved(position))
    }

    fun onDrop() {
        if (currentSurface == 0L || currentOffer == 0L) {
            onLeave()
            return
        }

        val paths = receivePaths()
        val position = PhysicalPosition(wlFixedToDouble(lastFixedX), wlFixedToDouble(lastFixedY))
        onEvent(currentSurface, WindowEvent.DragDropped(position, paths))

        finishOffer()
        cleanupCurrentOffer()
        currentSurface = 0L
    }

    fun onLeave() {
        if (currentSurface == 0L) return
        onEvent(currentSurface, WindowEvent.DragLeft)
        cleanupCurrentOffer()
        currentSurface = 0L
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private fun acceptOffer(mimeType: String?) {
        val accept = wlDataOfferAccept ?: return
        if (currentOffer == 0L) return
        if (mimeType != null) {
            Arena.ofConfined().use { arena ->
                val typeSeg = arena.allocateFrom(mimeType)
                try {
                    accept.invokeExact(
                        MemorySegment.ofAddress(currentOffer),
                        WL_DATA_OFFER_ACCEPT_OPCODE,
                        MemorySegment.NULL,
                        WL_DATA_DEVICE_MANAGER_VERSION,
                        0,
                        currentSerial,
                        typeSeg,
                    )
                } catch (_: Throwable) { }
            }
        } else {
            try {
                accept.invokeExact(
                    MemorySegment.ofAddress(currentOffer),
                    WL_DATA_OFFER_ACCEPT_OPCODE,
                    MemorySegment.NULL,
                    WL_DATA_DEVICE_MANAGER_VERSION,
                    0,
                    currentSerial,
                    MemorySegment.NULL,
                )
            } catch (_: Throwable) { }
        }
    }

    private fun receivePaths(): List<String> {
        val receive = wlDataOfferReceive ?: return emptyList()
        val pipe2 = nativePipe2 ?: return emptyList()
        if (currentOffer == 0L) return emptyList()

        val pipeFds = Arena.ofConfined().use { arena ->
            val seg = arena.allocate(8)
            val rc = try { pipe2.invokeExact(seg, O_CLOEXEC) as Int } catch (_: Throwable) { -1 }
            if (rc != 0) return emptyList()
            seg.get(ValueLayout.JAVA_INT, 0L) to seg.get(ValueLayout.JAVA_INT, 4L)
        }
        val (readFd, writeFd) = pipeFds

        try {
            Arena.ofConfined().use { arena ->
                receive.invokeExact(
                    MemorySegment.ofAddress(currentOffer),
                    WL_DATA_OFFER_RECEIVE_OPCODE,
                    MemorySegment.NULL,
                    WL_DATA_DEVICE_MANAGER_VERSION,
                    0,
                    arena.allocateFrom("text/uri-list"),
                    writeFd,
                )
            }
            flushDisplay()
        } catch (_: Throwable) {
            closeFd(writeFd); closeFd(readFd)
            return emptyList()
        } finally {
            closeFd(writeFd)
        }

        val data = readFdContents(readFd)
        closeFd(readFd)
        return parseUriList(data)
    }

    private fun finishOffer() {
        val finish = wlDataOfferFinish ?: return
        if (currentOffer == 0L) return
        try {
            finish.invokeExact(
                MemorySegment.ofAddress(currentOffer),
                WL_DATA_OFFER_FINISH_OPCODE,
                MemorySegment.NULL,
                WL_DATA_DEVICE_MANAGER_VERSION,
                0,
            )
        } catch (_: Throwable) { }
    }

    private fun cleanupCurrentOffer() {
        if (currentOffer != 0L) {
            val destroy = wlProxyMarshalFlagsVoid
            if (destroy != null) {
                try {
                    destroy.invokeExact(
                        MemorySegment.ofAddress(currentOffer),
                        WL_DATA_OFFER_DESTROY_OPCODE,
                        MemorySegment.NULL,
                        WL_DATA_DEVICE_MANAGER_VERSION,
                        WL_MARSHAL_FLAG_DESTROY,
                    )
                } catch (_: Throwable) { }
            }
            currentOffer = 0L
        }
    }

    private fun flushDisplay() {
        wlDisplayFlush?.let { flush ->
            try { flush.invokeExact(MemorySegment.ofAddress(displayPtr)) as Int } catch (_: Throwable) { }
        }
    }

    companion object {
        private fun readFdContents(fd: Int): ByteArray {
            val read = nativeRead ?: return ByteArray(0)
            val out = mutableListOf<ByteArray>()
            var total = 0
            try {
                while (true) {
                    val chunk = Arena.ofConfined().use { arena ->
                        val bufSeg = arena.allocate(4096)
                        val bytesRead = try {
                            read.invokeExact(fd, bufSeg, 4096L) as Long
                        } catch (_: Throwable) { -1L }
                        if (bytesRead <= 0) return@use null
                        val result = ByteArray(bytesRead.toInt())
                        val srcSlice = bufSeg.asSlice(0L, bytesRead)
                        for (i in 0 until bytesRead.toInt()) {
                            result[i] = srcSlice.get(ValueLayout.JAVA_BYTE, i.toLong())
                        }
                        result
                    } ?: break
                    out.add(chunk)
                    total += chunk.size
                }
            } catch (_: Throwable) { }
            val result = ByteArray(total)
            var offset = 0
            for (chunk in out) {
                System.arraycopy(chunk, 0, result, offset, chunk.size)
                offset += chunk.size
            }
            return result
        }

        internal fun parseUriList(data: ByteArray): List<String> {
            if (data.isEmpty()) return emptyList()
            val text = try { String(data, Charsets.UTF_8) } catch (_: Throwable) { return emptyList() }
            return text.lines()
                .map { it.trim() }
                .filter { it.isNotEmpty() && !it.startsWith("#") }
                .mapNotNull { line ->
                    if (!line.startsWith("file://")) return@mapNotNull null
                    val path = line.removePrefix("file://")
                    decodePercent(path)
                }
        }

        private fun decodePercent(s: String): String {
            val sb = StringBuilder()
            var i = 0
            while (i < s.length) {
                val c = s[i]
                if (c == '%' && i + 2 < s.length) {
                    val hi = s[i + 1].digitToIntOrNull(16) ?: return s
                    val lo = s[i + 2].digitToIntOrNull(16) ?: return s
                    sb.append((hi * 16 + lo).toChar())
                    i += 3
                } else {
                    sb.append(c)
                    i++
                }
            }
            return sb.toString()
        }

        private fun closeFd(fd: Int) {
            val close = nativeClose ?: return
            try { close.invokeExact(fd) as Int } catch (_: Throwable) { }
        }
    }
}
