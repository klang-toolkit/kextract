package org.graphiks.kadre.samples.simulation.compose

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG

class X11GlxContext(displayAddr: Long, private val window: Long) : GlContext {

    private val display = NativeFfi.ptr(displayAddr)
    private val arena = Arena.ofShared()
    private var ctx = MemorySegment.NULL
    private var created = false

    override fun makeCurrent() {
        if (!created) create()
        glXMakeCurrent.call(display, window, ctx)
    }

    private fun create() {
        val screen = xDefaultScreen.call(display) as Int

        val attrs = intArrayOf(
            4,
            5,
            8, 8,
            9, 8,
            10, 8,
            11, 8,
            12, 24,
            13, 8,
            0,
        )
        val attrSeg = arena.allocate(JAVA_INT.byteSize() * attrs.size)
        attrs.forEachIndexed { i, v -> attrSeg.set(JAVA_INT, JAVA_INT.byteSize() * i, v) }

        val visual = glXChooseVisual.call(display, screen, attrSeg) as MemorySegment
        check(visual != MemorySegment.NULL) { "glXChooseVisual returned NULL (no suitable visual)" }

        ctx = glXCreateContext.call(display, visual, MemorySegment.NULL, 1) as MemorySegment
        check(ctx != MemorySegment.NULL) { "glXCreateContext failed" }
        created = true
    }

    override fun swapBuffers() {
        glXSwapBuffers.call(display, window)
    }

    override fun resize(widthPx: Int, heightPx: Int) {
    }

    override fun drawableSize(): GlContext.Size {
        val out = arena.allocate(64)
        xGetGeometry.call(
            display, window,
            out.asSlice(0L, 8L),
            out.asSlice(8L, 4L),
            out.asSlice(12L, 4L),
            out.asSlice(16L, 4L),
            out.asSlice(20L, 4L),
            out.asSlice(24L, 4L),
            out.asSlice(28L, 4L),
        )
        return GlContext.Size(out.get(JAVA_INT, 16L), out.get(JAVA_INT, 20L))
    }

    override fun dispose() {
        runCatching { glXMakeCurrent.call(display, 0L, MemorySegment.NULL) }
        runCatching { if (ctx != MemorySegment.NULL) glXDestroyContext.call(display, ctx) }
    }

    private companion object {
        val x11 = NativeFfi.lookup("X11", "libX11.so.6", "libX11.so")
        val gl = NativeFfi.lookup("GL", "libGL.so.1", "libGL.so")

        val xDefaultScreen = NativeFfi.handle(x11, "XDefaultScreen", FunctionDescriptor.of(JAVA_INT, ADDRESS))
        val xGetGeometry = NativeFfi.handle(
            x11, "XGetGeometry",
            FunctionDescriptor.of(JAVA_INT, ADDRESS, JAVA_LONG, ADDRESS, ADDRESS, ADDRESS, ADDRESS, ADDRESS, ADDRESS, ADDRESS),
        )

        val glXChooseVisual = NativeFfi.handle(gl, "glXChooseVisual", FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_INT, ADDRESS))
        val glXCreateContext = NativeFfi.handle(gl, "glXCreateContext", FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS, ADDRESS, JAVA_INT))
        val glXMakeCurrent = NativeFfi.handle(gl, "glXMakeCurrent", FunctionDescriptor.of(JAVA_INT, ADDRESS, JAVA_LONG, ADDRESS))
        val glXSwapBuffers = NativeFfi.handle(gl, "glXSwapBuffers", FunctionDescriptor.ofVoid(ADDRESS, JAVA_LONG))
        val glXDestroyContext = NativeFfi.handle(gl, "glXDestroyContext", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS))
    }
}
