package org.graphiks.kadre.wayland
import org.graphiks.kadre.ffi.wayland.*

import org.graphiks.kadre.core.CursorImage
import org.graphiks.kadre.ffi.wayland.generated.wl_shm_format
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

internal object WaylandCustomCursorStore {
    private val cursors = ConcurrentHashMap<Long, CursorImage>()
    private val nextId = AtomicLong(1L)

    fun store(image: CursorImage): Long {
        val id = nextId.getAndIncrement()
        cursors[id] = image
        return id
    }

    fun get(id: Long): CursorImage? = cursors[id]

    fun remove(id: Long) { cursors.remove(id) }
}

/**
 * Converts RGBA bytes to a wl_shm ARGB8888 premultiplied buffer,
 * creates a wl_buffer, attaches it to the cursor surface.
 *
 * @param surfacePtr   The cursor wl_surface (created once per window).
 * @param shmPtr       wl_shm* proxy for pool creation.
 * @param displayPtr   wl_display* for flush.
 * @param width        Image width in pixels.
 * @param height       Image height in pixels.
 * @param rgba         Raw RGBA byte data (4 bytes per pixel).
 * @param hotspotX     Cursor hotspot X coordinate.
 * @param hotspotY     Cursor hotspot Y coordinate.
 * @return The wl_buffer* address, or 0 on failure.
 */
internal fun applyCursorSurface(
    surfacePtr: Long,
    shmPtr: Long,
    displayPtr: Long,
    width: Int,
    height: Int,
    rgba: ByteArray,
    hotspotX: Int,
    hotspotY: Int,
): Long {
    if (surfacePtr == 0L || shmPtr == 0L || width <= 0 || height <= 0) return 0L

    val stride = width * 4
    val size = height * stride

    // ── 1. Create shared memory fd ──────────────────────────────────────────
    val fd: Int = createShmFd("kadre-cursor", size) ?: return 0L

    // ── 2. Map the fd ──────────────────────────────────────────────────────
    val mapped = nativeMmap?.let { mmap ->
        try {
            mmap.invokeExact(MemorySegment.NULL, size.toLong(), PROT_READ or PROT_WRITE, MAP_SHARED, fd, 0L) as MemorySegment
        } catch (_: Throwable) { MemorySegment.NULL }
    } ?: MemorySegment.NULL

    if (mapped == MemorySegment.NULL || mapped.address() == MAP_FAILED_PTR) {
        nativeClose?.invokeExact(fd)
        return 0L
    }

    // ── 3. Write premultiplied ARGB8888 pixel data ─────────────────────────
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

    // Unmap — the kernel keeps the pages alive while the fd is open (MAP_SHARED).
    nativeMunmap?.invokeExact(mapped, size.toLong())

    // ── 4. Create wl_shm_pool ──────────────────────────────────────────────
    val poolMarshal = wlShmCreatePool ?: run {
        nativeClose?.invokeExact(fd)
        return 0L
    }
    val poolIface = wlShmPoolInterface ?: run {
        nativeClose?.invokeExact(fd)
        return 0L
    }
    val poolPtr: MemorySegment = try {
        poolMarshal.invokeExact(
            MemorySegment.ofAddress(shmPtr),
            0,                        // opcode: create_pool
            poolIface,
            1,                        // version
            0,                        // flags
            fd,
            size,
            MemorySegment.NULL,       // new_id = NULL (libwayland creates)
        ) as MemorySegment
    } catch (_: Throwable) {
        MemorySegment.NULL
    }
    if (poolPtr == MemorySegment.NULL || poolPtr.address() == 0L) {
        nativeClose?.invokeExact(fd)
        return 0L
    }

    // Close our fd — the pool holds its own reference
    nativeClose?.invokeExact(fd)

    // ── 5. Create wl_buffer from pool ──────────────────────────────────────
    val bufMarshal = wlShmPoolCreateBuffer ?: run {
        wlProxyMarshalFlagsVoid?.invokeExact(poolPtr, 1, MemorySegment.NULL, 1, WL_MARSHAL_FLAG_DESTROY)
        return 0L
    }
    val bufIface = wlBufferInterface ?: run {
        wlProxyMarshalFlagsVoid?.invokeExact(poolPtr, 1, MemorySegment.NULL, 1, WL_MARSHAL_FLAG_DESTROY)
        return 0L
    }
    val bufPtr: Long = try {
        (bufMarshal.invokeExact(
            poolPtr,
            0,                        // opcode: create_buffer
            bufIface,
            1,                        // version
            0,                        // flags
            0,                        // offset
            width,
            height,
            stride,
            wl_shm_format.WL_SHM_FORMAT_ARGB8888.value.toInt(), // format
            MemorySegment.NULL,       // new_id = NULL
        ) as MemorySegment).address()
    } catch (_: Throwable) {
        0L
    }

    // Destroy the pool (the buffer holds its own reference)
    wlProxyMarshalFlagsVoid?.invokeExact(poolPtr, 1, MemorySegment.NULL, 1, WL_MARSHAL_FLAG_DESTROY)

    if (bufPtr == 0L) return 0L

    // ── 6. Attach buffer to cursor surface ─────────────────────────────────
    wlSurfaceAttach(surfacePtr, bufPtr, 0, 0)

    // ── 7. Damage the full surface ─────────────────────────────────────────
    wlSurfaceDamage(surfacePtr, 0, 0, Int.MAX_VALUE, Int.MAX_VALUE)

    // ── 8. Commit the cursor surface ───────────────────────────────────────
    wlSurfaceCommit(surfacePtr)

    // ── 9. Flush ───────────────────────────────────────────────────────────
    wlDisplayFlush?.let { flush ->
        try { flush.invokeExact(MemorySegment.ofAddress(displayPtr)) as Int } catch (_: Throwable) {}
    }

    return bufPtr
}

/**
 * Creates an fd-backed shared memory region.
 * Tries memfd_create first, falls back to shm_open.
 *
 * @param name  Label for the memory region.
 * @param size  Required size in bytes.
 * @return The fd, or null on failure.
 */
internal fun createShmFd(name: String, size: Int): Int? {
    // Try memfd_create first
    if (nativeMemfdCreate != null) {
        val arena = Arena.ofConfined()
        try {
            val nameSeg = arena.allocateFrom(name)
            val fd = nativeMemfdCreate!!.invokeExact(nameSeg, 0) as Int
            if (fd >= 0) {
                val rc = nativeFtruncate?.let { ftrunc ->
                    try { ftrunc.invokeExact(fd, size.toLong()) as Int } catch (_: Throwable) { -1 }
                } ?: -1
                if (rc == 0) return fd
                nativeClose?.invokeExact(fd)
            }
        } catch (_: Throwable) {
        } finally {
            arena.close()
        }
    }

    // Fallback: shm_open
    if (nativeShmOpen != null && nativeShmUnlink != null) {
        val arena = Arena.ofConfined()
        try {
            val shmName = "/kadre-cursor-" + java.util.UUID.randomUUID()
            val nameSeg = arena.allocateFrom(shmName)
            val fd = nativeShmOpen!!.invokeExact(nameSeg, O_RDWR or O_CREAT or O_EXCL, 384) as Int
            if (fd >= 0) {
                // Unlink immediately so the name goes away
                nativeShmUnlink!!.invokeExact(nameSeg)
                val rc = nativeFtruncate?.let { ftrunc ->
                    try { ftrunc.invokeExact(fd, size.toLong()) as Int } catch (_: Throwable) { -1 }
                } ?: -1
                if (rc == 0) return fd
                nativeClose?.invokeExact(fd)
            }
        } catch (_: Throwable) {
        } finally {
            arena.close()
        }
    }

    return null
}

/**
 * Calls wl_surface.commit (opcode 6) to make pending state take effect.
 */
internal fun wlSurfaceCommit(surfacePtr: Long) {
    val marshal = wlProxyMarshalFlagsVoid ?: return
    try {
        marshal.invokeExact(
            MemorySegment.ofAddress(surfacePtr),
            6,                     // opcode: wl_surface.commit
            MemorySegment.NULL,    // wl_interface* (NULL)
            1,                     // version
            0,                     // flags
        )
    } catch (_: Throwable) {}
}
