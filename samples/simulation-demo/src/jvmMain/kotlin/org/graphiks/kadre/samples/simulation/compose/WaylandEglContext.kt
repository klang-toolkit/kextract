package org.graphiks.kadre.samples.simulation.compose

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT

private const val EGL_OPENGL_API = 0x30A2
private const val EGL_NONE = 0x3038

class WaylandEglContext(displayAddr: Long, surfaceAddr: Long) : GlContext {

    private val wlDisplay = NativeFfi.ptr(displayAddr)
    private val wlSurface = NativeFfi.ptr(surfaceAddr)
    private val arena = Arena.ofShared()

    private var eglDisplay = MemorySegment.NULL
    private var eglContext = MemorySegment.NULL
    private var eglSurface = MemorySegment.NULL
    private var eglWindow = MemorySegment.NULL
    private var created = false

    private var width = 1
    private var height = 1

    override fun makeCurrent() {
        if (!created) create()
        eglMakeCurrent.call(eglDisplay, eglSurface, eglSurface, eglContext)
        eglSwapInterval.call(eglDisplay, 0)
    }

    private fun create() {
        eglDisplay = eglGetDisplay.call(wlDisplay) as MemorySegment
        check(eglDisplay != MemorySegment.NULL) { "eglGetDisplay returned EGL_NO_DISPLAY" }
        check((eglInitialize.call(eglDisplay, MemorySegment.NULL, MemorySegment.NULL) as Int) != 0) {
            "eglInitialize failed"
        }
        check((eglBindAPI.call(EGL_OPENGL_API) as Int) != 0) { "eglBindAPI(EGL_OPENGL_API) failed" }

        val configAttrs = intArrayOf(
            0x3033, 0x0004,
            0x3040, 0x0008,
            0x3024, 8,
            0x3023, 8,
            0x3022, 8,
            0x3021, 8,
            0x3025, 24,
            0x3026, 8,
            EGL_NONE,
        )
        val attrSeg = intSegment(configAttrs)
        val configs = arena.allocate(ADDRESS.byteSize())
        val numConfig = arena.allocate(JAVA_INT.byteSize())
        check((eglChooseConfig.call(eglDisplay, attrSeg, configs, 1, numConfig) as Int) != 0) {
            "eglChooseConfig failed"
        }
        check(numConfig.get(JAVA_INT, 0L) > 0) { "eglChooseConfig returned no configs" }
        val config = configs.get(ADDRESS, 0L)

        eglWindow = wlEglWindowCreate.call(wlSurface, width, height) as MemorySegment
        check(eglWindow != MemorySegment.NULL) { "wl_egl_window_create failed" }

        eglSurface = eglCreateWindowSurface.call(eglDisplay, config, eglWindow, MemorySegment.NULL) as MemorySegment
        check(eglSurface != MemorySegment.NULL) { "eglCreateWindowSurface failed" }

        val ctxAttrs = intSegment(intArrayOf(EGL_NONE))
        eglContext = eglCreateContext.call(eglDisplay, config, MemorySegment.NULL, ctxAttrs) as MemorySegment
        check(eglContext != MemorySegment.NULL) { "eglCreateContext failed" }
        created = true
    }

    override fun swapBuffers() {
        eglSwapBuffers.call(eglDisplay, eglSurface)
    }

    override fun resize(widthPx: Int, heightPx: Int) {
        if (widthPx <= 0 || heightPx <= 0) return
        width = widthPx
        height = heightPx
        if (eglWindow != MemorySegment.NULL) {
            wlEglWindowResize.call(eglWindow, widthPx, heightPx, 0, 0)
        }
    }

    override fun drawableSize(): GlContext.Size = GlContext.Size(width, height)

    override fun dispose() {
        runCatching { eglMakeCurrent.call(eglDisplay, MemorySegment.NULL, MemorySegment.NULL, MemorySegment.NULL) }
        runCatching { if (eglSurface != MemorySegment.NULL) eglDestroySurface.call(eglDisplay, eglSurface) }
        runCatching { if (eglContext != MemorySegment.NULL) eglDestroyContext.call(eglDisplay, eglContext) }
        runCatching { if (eglWindow != MemorySegment.NULL) wlEglWindowDestroy.call(eglWindow) }
    }

    private fun intSegment(values: IntArray): MemorySegment {
        val seg = arena.allocate(JAVA_INT.byteSize() * values.size)
        values.forEachIndexed { i, v -> seg.set(JAVA_INT, JAVA_INT.byteSize() * i, v) }
        return seg
    }

    private companion object {
        val egl = NativeFfi.lookup("EGL", "libEGL.so.1", "libEGL.so")
        val wlEgl = NativeFfi.lookup("wayland-egl", "libwayland-egl.so.1", "libwayland-egl.so")

        val wlEglWindowCreate = NativeFfi.handle(wlEgl, "wl_egl_window_create", FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_INT, JAVA_INT))
        val wlEglWindowResize = NativeFfi.handle(wlEgl, "wl_egl_window_resize", FunctionDescriptor.ofVoid(ADDRESS, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT))
        val wlEglWindowDestroy = NativeFfi.handle(wlEgl, "wl_egl_window_destroy", FunctionDescriptor.ofVoid(ADDRESS))

        val eglGetDisplay = NativeFfi.handle(egl, "eglGetDisplay", FunctionDescriptor.of(ADDRESS, ADDRESS))
        val eglInitialize = NativeFfi.handle(egl, "eglInitialize", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS, ADDRESS))
        val eglBindAPI = NativeFfi.handle(egl, "eglBindAPI", FunctionDescriptor.of(JAVA_INT, JAVA_INT))
        val eglChooseConfig = NativeFfi.handle(egl, "eglChooseConfig", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS, ADDRESS, JAVA_INT, ADDRESS))
        val eglCreateWindowSurface = NativeFfi.handle(egl, "eglCreateWindowSurface", FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS, ADDRESS, ADDRESS))
        val eglCreateContext = NativeFfi.handle(egl, "eglCreateContext", FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS, ADDRESS, ADDRESS))
        val eglMakeCurrent = NativeFfi.handle(egl, "eglMakeCurrent", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS, ADDRESS, ADDRESS))
        val eglSwapBuffers = NativeFfi.handle(egl, "eglSwapBuffers", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS))
        val eglSwapInterval = NativeFfi.handle(egl, "eglSwapInterval", FunctionDescriptor.of(JAVA_INT, ADDRESS, JAVA_INT))
        val eglDestroySurface = NativeFfi.handle(egl, "eglDestroySurface", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS))
        val eglDestroyContext = NativeFfi.handle(egl, "eglDestroyContext", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS))
    }
}
