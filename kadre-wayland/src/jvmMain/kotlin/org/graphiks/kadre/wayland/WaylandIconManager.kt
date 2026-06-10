package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.wayland.generated.wl_shm_format
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Manages `xdg_toplevel_icon_manager_v1` for setting window icons.
 *
 * Each window should hold its own instance. When the protocol extension is
 * unavailable ([iconManagerPtr] is 0), all operations silently no-op.
 *
 * @param iconManagerPtr  Address of the bound `xdg_toplevel_icon_manager_v1*` proxy,
 *                        or 0 if the protocol extension is unavailable.
 * @param shmPtr          `wl_shm*` proxy for creating shared memory buffers.
 * @param displayPtr      `wl_display*` for flushing the display connection.
 */
internal class WaylandIconManager(
    private val iconManagerPtr: Long,
    private val shmPtr: Long,
    private val displayPtr: Long,
) {
    /**
     * Sets the window icon on [toplevelPtr] from RGBA pixel data.
     *
     * When [icon] is null, resets the toplevel icon (clears it).
     * When the protocol is unavailable, silently no-ops.
     */
    fun setWindowIcon(icon: Icon?, toplevelPtr: Long) {
        if (iconManagerPtr == 0L || toplevelPtr == 0L) return

        if (icon == null) {
            setNullIcon(toplevelPtr)
            return
        }
        if (icon.width <= 0 || icon.height <= 0 || icon.rgba.isEmpty()) {
            setNullIcon(toplevelPtr)
            return
        }

        val iconPtr = createIcon() ?: return

        val bufPtr = createShmBuffer(shmPtr, icon.width, icon.height, icon.rgba)
        if (bufPtr == 0L) {
            destroyIconObject(iconPtr)
            return
        }

        addBuffer(iconPtr, bufPtr)

        commitIcon(iconPtr)

        setIcon(toplevelPtr, iconPtr)

        destroyWlBuffer(bufPtr)
        destroyIconObject(iconPtr)
        flushDisplay()
    }

    /** Sets a null icon (clears the toplevel icon). */
    private fun setNullIcon(toplevelPtr: Long) {
        val marshal = wlProxyMarshalFlagsTwoObjects ?: return
        try {
            marshal.invokeExact(
                MemorySegment.ofAddress(iconManagerPtr),
                2,                          // opcode: set_icon
                MemorySegment.NULL,          // wl_interface* (NULL)
                1,                           // version
                0,                           // flags
                MemorySegment.ofAddress(toplevelPtr),
                MemorySegment.NULL,          // icon = NULL (clear)
            )
            flushDisplay()
        } catch (_: Throwable) {}
    }

    /** Creates a new xdg_toplevel_icon_v1 object. */
    private fun createIcon(): Long? {
        val marshal = wlProxyMarshalNewId ?: return null
        val iface = xdgToplevelIconV1Interface
        return try {
            val result = marshal.invokeExact(
                MemorySegment.ofAddress(iconManagerPtr),
                1,                          // opcode: create_icon
                iface,
                1,                           // version
                0,                           // flags
                MemorySegment.NULL,          // new_id = NULL
            ) as MemorySegment
            val addr = result.address()
            if (addr == 0L) null else addr
        } catch (_: Throwable) { null }
    }

    /** Adds a wl_buffer to the icon. */
    private fun addBuffer(iconPtr: Long, bufferPtr: Long) {
        val marshal = wlProxyMarshalFlagsObject ?: return
        try {
            marshal.invokeExact(
                MemorySegment.ofAddress(iconPtr),
                2,                          // opcode: add_buffer
                MemorySegment.NULL,          // wl_interface* (NULL)
                1,                           // version
                0,                           // flags
                MemorySegment.ofAddress(bufferPtr),
            )
        } catch (_: Throwable) {}
    }

    /** Commits the icon, making pending state take effect. */
    private fun commitIcon(iconPtr: Long) {
        val marshal = wlProxyMarshalFlagsVoid ?: return
        try {
            marshal.invokeExact(
                MemorySegment.ofAddress(iconPtr),
                3,                          // opcode: commit
                MemorySegment.NULL,          // wl_interface* (NULL)
                1,                           // version
                0,                           // flags
            )
        } catch (_: Throwable) {}
    }

    /** Sets the icon on a toplevel surface. */
    private fun setIcon(toplevelPtr: Long, iconPtr: Long) {
        val marshal = wlProxyMarshalFlagsTwoObjects ?: return
        try {
            marshal.invokeExact(
                MemorySegment.ofAddress(iconManagerPtr),
                2,                          // opcode: set_icon
                MemorySegment.NULL,          // wl_interface* (NULL)
                1,                           // version
                0,                           // flags
                MemorySegment.ofAddress(toplevelPtr),
                MemorySegment.ofAddress(iconPtr),
            )
        } catch (_: Throwable) {}
    }

    /** Destroys an xdg_toplevel_icon_v1 object. */
    private fun destroyIconObject(iconPtr: Long) {
        val marshal = wlProxyMarshalFlagsVoid ?: return
        try {
            marshal.invokeExact(
                MemorySegment.ofAddress(iconPtr),
                0,                          // opcode: destroy
                MemorySegment.NULL,          // wl_interface* (NULL)
                1,                           // version
                WL_MARSHAL_FLAG_DESTROY,     // flags
            )
        } catch (_: Throwable) {}
    }

    /** Destroys a wl_buffer via wl_buffer.destroy (opcode 0, destructor). */
    private fun destroyWlBuffer(bufferPtr: Long) {
        if (bufferPtr == 0L) return
        val marshal = wlProxyMarshalFlagsVoid ?: return
        try {
            marshal.invokeExact(
                MemorySegment.ofAddress(bufferPtr),
                0,                          // opcode: wl_buffer.destroy
                MemorySegment.NULL,
                1,                           // version
                WL_MARSHAL_FLAG_DESTROY,     // flags
            )
        } catch (_: Throwable) {}
    }

    /** Flushes the display connection. */
    private fun flushDisplay() {
        wlDisplayFlush?.let { flush ->
            try {
                flush.invokeExact(MemorySegment.ofAddress(displayPtr)) as Int
            } catch (_: Throwable) {}
        }
    }

    companion object {
        /**
         * Creates a wl_shm buffer from RGBA pixel data.
         * Returns the wl_buffer* address, or 0 on failure.
         */
        internal fun createShmBuffer(
            shmPtr: Long,
            width: Int,
            height: Int,
            rgba: ByteArray,
        ): Long {
            if (shmPtr == 0L || width <= 0 || height <= 0) return 0L

            val stride = width * 4
            val size = height * stride

            val fd = createShmFd("kadre-icon", size) ?: return 0L

            val mapped = nativeMmap?.let { mmap ->
                try {
                    mmap.invokeExact(MemorySegment.NULL, size.toLong(), PROT_READ or PROT_WRITE, MAP_SHARED, fd, 0L) as MemorySegment
                } catch (_: Throwable) { MemorySegment.NULL }
            } ?: MemorySegment.NULL

            if (mapped == MemorySegment.NULL || mapped.address() == MAP_FAILED_PTR) {
                nativeClose?.invokeExact(fd)
                return 0L
            }

            try {
                val n = rgba.size / 4
                for (i in 0 until n) {
                    val off = i * 4
                    val r = rgba[off].toInt() and 0xFF
                    val g = rgba[off + 1].toInt() and 0xFF
                    val b = rgba[off + 2].toInt() and 0xFF
                    val a = rgba[off + 3].toInt() and 0xFF
                    val rp = r * a / 255
                    val gp = g * a / 255
                    val bp = b * a / 255
                    val argb = (a shl 24) or (rp shl 16) or (gp shl 8) or bp
                    mapped.set(ValueLayout.JAVA_INT, i * 4L, argb)
                }
            } catch (_: Throwable) {
                nativeMunmap?.invokeExact(mapped, size.toLong())
                nativeClose?.invokeExact(fd)
                return 0L
            }

            nativeMunmap?.invokeExact(mapped, size.toLong())

            val poolMarshal = wlShmCreatePool ?: run { nativeClose?.invokeExact(fd); return 0L }
            val poolIface = wlShmPoolInterface ?: run { nativeClose?.invokeExact(fd); return 0L }
            val poolPtr = try {
                poolMarshal.invokeExact(
                    MemorySegment.ofAddress(shmPtr), 0, poolIface, 1, 0, fd, size, MemorySegment.NULL,
                ) as MemorySegment
            } catch (_: Throwable) { MemorySegment.NULL }

            if (poolPtr == MemorySegment.NULL || poolPtr.address() == 0L) {
                nativeClose?.invokeExact(fd)
                return 0L
            }

            nativeClose?.invokeExact(fd)

            val bufMarshal = wlShmPoolCreateBuffer ?: run {
                wlProxyMarshalFlagsVoid?.invokeExact(poolPtr, 1, MemorySegment.NULL, 1, WL_MARSHAL_FLAG_DESTROY)
                return 0L
            }
            val bufIface = wlBufferInterface ?: run {
                wlProxyMarshalFlagsVoid?.invokeExact(poolPtr, 1, MemorySegment.NULL, 1, WL_MARSHAL_FLAG_DESTROY)
                return 0L
            }
            val bufPtr = try {
                (bufMarshal.invokeExact(
                    poolPtr, 0, bufIface, 1, 0, 0, width, height, stride,
                    wl_shm_format.WL_SHM_FORMAT_ARGB8888.value.toInt(), MemorySegment.NULL,
                ) as MemorySegment).address()
            } catch (_: Throwable) { 0L }

            wlProxyMarshalFlagsVoid?.invokeExact(poolPtr, 1, MemorySegment.NULL, 1, WL_MARSHAL_FLAG_DESTROY)

            return bufPtr
        }
    }
}
