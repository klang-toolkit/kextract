/**
 * Offscreen GPU capture on Windows.
 *
 * As on macOS, `WGPU.requestAdapter` requires a non-null surface. On Windows, the
 * surface is created from an `HWND`. So we create a **hidden Win32 window** (system
 * class "STATIC", style `WS_POPUP` without `WS_VISIBLE`) solely to satisfy
 * `requestAdapter`; rendering then targets exclusively an offscreen texture, so
 * no window appears on screen.
 *
 * Backend: `WGPUInstanceBackend.Primary` (DX12/Vulkan depending on availability). On
 * CI runners without a dedicated GPU, wgpu may fall back to a software adapter (WARP DX12).
 *
 * Win32 bindings via Panama FFM (user32.dll / kernel32.dll), null on other OSes.
 */
package org.graphiks.kadre.samples.hellotriangle

import ffi.JvmNativeAddress
import io.ygdrasil.webgpu.WGPU
import io.ygdrasil.webgpu.WGPUInstanceBackend
import io.ygdrasil.webgpu.WGPULowLevelApi
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

private val linker: Linker = Linker.nativeLinker()

private val user32: SymbolLookup? by lazy {
    try { SymbolLookup.libraryLookup("user32.dll", Arena.global()) } catch (_: Throwable) { null }
}
private val kernel32: SymbolLookup? by lazy {
    try { SymbolLookup.libraryLookup("kernel32.dll", Arena.global()) } catch (_: Throwable) { null }
}

private fun SymbolLookup?.downcall(name: String, desc: FunctionDescriptor): MethodHandle? {
    this ?: return null
    return this.find(name).map { linker.downcallHandle(it, desc) }.orElse(null)
}

/** HMODULE GetModuleHandleW(LPCWSTR lpModuleName) — NULL → handle of the current process. */
private val getModuleHandleW: MethodHandle? by lazy {
    kernel32.downcall(
        "GetModuleHandleW",
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
    )
}

/** HWND CreateWindowExW(...) — cf. Win32. */
private val createWindowExW: MethodHandle? by lazy {
    user32.downcall(
        "CreateWindowExW",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,   // HWND
            ValueLayout.JAVA_INT,  // dwExStyle
            ValueLayout.ADDRESS,   // lpClassName
            ValueLayout.ADDRESS,   // lpWindowName
            ValueLayout.JAVA_INT,  // dwStyle
            ValueLayout.JAVA_INT,  // X
            ValueLayout.JAVA_INT,  // Y
            ValueLayout.JAVA_INT,  // nWidth
            ValueLayout.JAVA_INT,  // nHeight
            ValueLayout.ADDRESS,   // hWndParent
            ValueLayout.ADDRESS,   // hMenu
            ValueLayout.ADDRESS,   // hInstance
            ValueLayout.ADDRESS,   // lpParam
        ),
    )
}

/** void DestroyWindow(HWND) — best effort to release the hidden window. */
private val destroyWindow: MethodHandle? by lazy {
    user32.downcall("DestroyWindow", FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS))
}

private const val WS_POPUP: Int = 0x80000000.toInt()

/** Allocates a NUL-terminated wide string (UTF-16LE) in [arena]. */
private fun Arena.wideString(s: String): MemorySegment {
    val seg = allocate((s.length + 1).toLong() * 2)
    for (i in s.indices) seg.setAtIndex(ValueLayout.JAVA_CHAR, i.toLong(), s[i])
    seg.setAtIndex(ValueLayout.JAVA_CHAR, s.length.toLong(), 0.toChar())
    return seg
}

/**
 * Windows capture: Instance (Primary) + hidden Win32 window → surface → common readback.
 */
@OptIn(WGPULowLevelApi::class)
internal fun captureWindows(path: String) {
    val instance = WGPU.createInstance(WGPUInstanceBackend.Primary)
        ?: error("Échec création WGPU Instance (Primary)")

    val createHandle = createWindowExW
    val moduleHandle = getModuleHandleW
    if (createHandle == null || moduleHandle == null) {
        instance.close()
        error("Bindings Win32 indisponibles (user32/kernel32) — capture Windows impossible")
    }

    Arena.ofConfined().use { arena ->
        val hInstance = moduleHandle.invokeExact(MemorySegment.NULL) as MemorySegment
        val className = arena.wideString("STATIC")
        val hwnd = createHandle.invokeExact(
            0,                       // dwExStyle
            className,               // lpClassName (predefined system class)
            MemorySegment.NULL,      // lpWindowName
            WS_POPUP,                // dwStyle — invisible (no WS_VISIBLE)
            0, 0,                    // X, Y
            CAPTURE_WIDTH, CAPTURE_HEIGHT,
            MemorySegment.NULL,      // hWndParent
            MemorySegment.NULL,      // hMenu
            hInstance,               // hInstance
            MemorySegment.NULL,      // lpParam
        ) as MemorySegment

        if (hwnd.address() == 0L) {
            instance.close()
            error("CreateWindowExW a échoué (HWND nul)")
        }

        val surface = instance.getSurfaceFromWindows(
            JvmNativeAddress(MemorySegment.ofAddress(hInstance.address())),
            JvmNativeAddress(MemorySegment.ofAddress(hwnd.address())),
        ) ?: run {
            destroyWindow?.invoke(hwnd)
            instance.close()
            error("Échec création Surface depuis HWND")
        }

        try {
            renderSurfaceToPng(instance, surface, path)
        } finally {
            destroyWindow?.invoke(hwnd)
        }
    }
}
