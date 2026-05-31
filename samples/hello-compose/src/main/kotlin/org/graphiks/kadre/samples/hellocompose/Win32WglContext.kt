/**
 * Windows OpenGL context via WGL, bound to a Kadre Win32 window (HWND).
 *
 * Creates a double-buffered RGBA8 + 8-bit-stencil pixel format on the window DC, a legacy
 * WGL context, and presents with SwapBuffers. Skiko loads GL function pointers once the
 * context is current.
 */
package org.graphiks.kadre.samples.hellocompose

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT

class Win32WglContext(hwndAddr: Long) : GlContext {

    private val hwnd = NativeFfi.ptr(hwndAddr)
    private val arena = Arena.ofShared()

    private var hdc = MemorySegment.NULL
    private var hglrc = MemorySegment.NULL
    private var created = false

    override fun makeCurrent() {
        if (!created) create()
        wglMakeCurrent.call(hdc, hglrc)
    }

    private fun create() {
        hdc = getDC.call(hwnd) as MemorySegment
        check(hdc != MemorySegment.NULL) { "GetDC(hwnd) returned NULL" }

        // PIXELFORMATDESCRIPTOR (40 bytes). Request DRAW_TO_WINDOW | SUPPORT_OPENGL | DOUBLEBUFFER.
        val pfd = arena.allocate(40)
        pfd.set(ValueLayout.JAVA_SHORT, 0L, 40.toShort())          // nSize
        pfd.set(ValueLayout.JAVA_SHORT, 2L, 1.toShort())           // nVersion
        pfd.set(JAVA_INT, 4L, 0x4 or 0x20 or 0x1)                  // dwFlags
        pfd.set(ValueLayout.JAVA_BYTE, 8L, 0.toByte())             // iPixelType = PFD_TYPE_RGBA
        pfd.set(ValueLayout.JAVA_BYTE, 9L, 32.toByte())            // cColorBits
        pfd.set(ValueLayout.JAVA_BYTE, 16L, 8.toByte())            // cAlphaBits
        pfd.set(ValueLayout.JAVA_BYTE, 23L, 24.toByte())           // cDepthBits
        pfd.set(ValueLayout.JAVA_BYTE, 24L, 8.toByte())            // cStencilBits
        pfd.set(ValueLayout.JAVA_BYTE, 26L, 0.toByte())            // iLayerType = PFD_MAIN_PLANE

        val pf = choosePixelFormat.call(hdc, pfd) as Int
        check(pf != 0) { "ChoosePixelFormat failed" }
        check((setPixelFormat.call(hdc, pf, pfd) as Int) != 0) { "SetPixelFormat failed" }

        hglrc = wglCreateContext.call(hdc) as MemorySegment
        check(hglrc != MemorySegment.NULL) { "wglCreateContext failed" }
        created = true
    }

    override fun swapBuffers() {
        swapBuffersFn.call(hdc)
    }

    override fun resize(widthPx: Int, heightPx: Int) {
        // The window's default framebuffer tracks the client area automatically.
    }

    override fun drawableSize(): GlContext.Size {
        val rect = arena.allocate(16) // RECT { LONG left, top, right, bottom }
        getClientRect.call(hwnd, rect)
        val left = rect.get(JAVA_INT, 0L)
        val top = rect.get(JAVA_INT, 4L)
        val right = rect.get(JAVA_INT, 8L)
        val bottom = rect.get(JAVA_INT, 12L)
        return GlContext.Size(right - left, bottom - top)
    }

    override fun dispose() {
        runCatching { wglMakeCurrent.call(MemorySegment.NULL, MemorySegment.NULL) }
        runCatching { if (hglrc != MemorySegment.NULL) wglDeleteContext.call(hglrc) }
        runCatching { if (hdc != MemorySegment.NULL) releaseDC.call(hwnd, hdc) }
    }

    private companion object {
        val user32 = NativeFfi.lookup("user32")
        val gdi32 = NativeFfi.lookup("gdi32")
        val opengl32 = NativeFfi.lookup("opengl32")

        val getDC = NativeFfi.handle(user32, "GetDC", FunctionDescriptor.of(ADDRESS, ADDRESS))
        val releaseDC = NativeFfi.handle(user32, "ReleaseDC", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS))
        val getClientRect = NativeFfi.handle(user32, "GetClientRect", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS))

        val choosePixelFormat = NativeFfi.handle(gdi32, "ChoosePixelFormat", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS))
        val setPixelFormat = NativeFfi.handle(gdi32, "SetPixelFormat", FunctionDescriptor.of(JAVA_INT, ADDRESS, JAVA_INT, ADDRESS))
        val swapBuffersFn = NativeFfi.handle(gdi32, "SwapBuffers", FunctionDescriptor.of(JAVA_INT, ADDRESS))

        val wglCreateContext = NativeFfi.handle(opengl32, "wglCreateContext", FunctionDescriptor.of(ADDRESS, ADDRESS))
        val wglMakeCurrent = NativeFfi.handle(opengl32, "wglMakeCurrent", FunctionDescriptor.of(JAVA_INT, ADDRESS, ADDRESS))
        val wglDeleteContext = NativeFfi.handle(opengl32, "wglDeleteContext", FunctionDescriptor.of(JAVA_INT, ADDRESS))
    }
}
