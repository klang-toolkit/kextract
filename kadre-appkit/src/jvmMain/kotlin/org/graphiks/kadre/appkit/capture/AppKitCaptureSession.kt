package org.graphiks.kadre.appkit.capture

import org.graphiks.kffi.objc.ObjCSubclassing
import org.graphiks.kffi.objc.ObjCRuntime
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.CaptureConfig
import org.graphiks.kadre.core.capture.CaptureFrame
import org.graphiks.kadre.core.capture.CaptureSession
import org.graphiks.kadre.core.capture.CaptureSource
import org.graphiks.kadre.core.capture.PixelFormat
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.util.concurrent.CountDownLatch
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

class AppKitCaptureSession
constructor(
    source: CaptureSource,
    config: CaptureConfig,
    private val nativeStream: MemorySegment,
) : CaptureSession(source, config) {

    private val arena = Arena.global()
    private val linker = Linker.nativeLinker()
    private var started = false

    private val cvPixelBufferLockBaseAddress: java.lang.invoke.MethodHandle
    private val cvPixelBufferUnlockBaseAddress: java.lang.invoke.MethodHandle
    private val cvPixelBufferGetBaseAddress: java.lang.invoke.MethodHandle
    private val cvPixelBufferGetWidth: java.lang.invoke.MethodHandle
    private val cvPixelBufferGetHeight: java.lang.invoke.MethodHandle
    private val cvPixelBufferGetBytesPerRow: java.lang.invoke.MethodHandle
    private val cmSampleBufferGetImageBuffer: java.lang.invoke.MethodHandle

    private val delegate: MemorySegment
    private val delegateArena: Arena = Arena.ofShared()

    init {
        val coreVideoLib = SymbolLookup.libraryLookup(
            "/System/Library/Frameworks/CoreVideo.framework/CoreVideo", arena,
        )
        val coreMediaLib = SymbolLookup.libraryLookup(
            "/System/Library/Frameworks/CoreMedia.framework/CoreMedia", arena,
        )

        cvPixelBufferLockBaseAddress = linker.downcallHandle(
            coreVideoLib.find("CVPixelBufferLockBaseAddress").orElseThrow(),
            FunctionDescriptor.of(JAVA_INT, ADDRESS, JAVA_LONG),
        )
        cvPixelBufferUnlockBaseAddress = linker.downcallHandle(
            coreVideoLib.find("CVPixelBufferUnlockBaseAddress").orElseThrow(),
            FunctionDescriptor.of(JAVA_INT, ADDRESS, JAVA_LONG),
        )
        cvPixelBufferGetBaseAddress = linker.downcallHandle(
            coreVideoLib.find("CVPixelBufferGetBaseAddress").orElseThrow(),
            FunctionDescriptor.of(ADDRESS, ADDRESS),
        )
        cvPixelBufferGetWidth = linker.downcallHandle(
            coreVideoLib.find("CVPixelBufferGetWidth").orElseThrow(),
            FunctionDescriptor.of(JAVA_LONG, ADDRESS),
        )
        cvPixelBufferGetHeight = linker.downcallHandle(
            coreVideoLib.find("CVPixelBufferGetHeight").orElseThrow(),
            FunctionDescriptor.of(JAVA_LONG, ADDRESS),
        )
        cvPixelBufferGetBytesPerRow = linker.downcallHandle(
            coreVideoLib.find("CVPixelBufferGetBytesPerRow").orElseThrow(),
            FunctionDescriptor.of(JAVA_LONG, ADDRESS),
        )
        cmSampleBufferGetImageBuffer = linker.downcallHandle(
            coreMediaLib.find("CMSampleBufferGetImageBuffer").orElseThrow(),
            FunctionDescriptor.of(ADDRESS, ADDRESS),
        )

        val cbMethodHandle = MethodHandles.lookup().findVirtual(
            AppKitCaptureSession::class.java,
            "handleStreamOutput",
            MethodType.methodType(
                Void.TYPE,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
                MemorySegment::class.java,
                java.lang.Long.TYPE,
            ),
        ).bindTo(this)

        val streamOutputDesc = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, ADDRESS, JAVA_LONG)
        val streamOutputImp = linker.upcallStub(cbMethodHandle, streamOutputDesc, delegateArena)

        delegate = createDelegate(streamOutputImp)
        ObjCRuntime.msgSend(null, nativeStream, ObjCRuntime.sel("setDelegate:"), delegate)
        startCapture()
    }

    private fun createDelegate(imp: MemorySegment): MemorySegment {
        val className = "KadreStreamOutputDelegate_${System.identityHashCode(this)}"
        val cls = ObjCSubclassing.allocateClass("NSObject", className)
        ObjCSubclassing.addMethod(cls, "stream:didOutputSampleBuffer:ofType:", imp, "v@:@@q")
        ObjCSubclassing.registerClass(cls)
        return ObjCRuntime.msgSend(ADDRESS, cls, ObjCRuntime.sel("new")) as MemorySegment
    }

    @Suppress("unused")
    fun handleStreamOutput(
        self: MemorySegment,
        _cmd: MemorySegment,
        stream: MemorySegment,
        sampleBuffer: MemorySegment,
        type: Long,
    ) {
        if (type != 0L) return
        onSampleBuffer(sampleBuffer)
    }

    private fun startCapture() {
        val callback = ObjCCallback1()
        val blockArena = Arena.ofShared()
        try {
            val upcallStub = linker.upcallStub(callback.methodHandle, callback.fnDescriptor, blockArena)
            val block = ObjCBlocks.create(upcallStub, blockArena)
            ObjCRuntime.msgSend(null, nativeStream, ObjCRuntime.sel("startCaptureWithCompletionHandler:"), block)
            callback.await(5000)
            started = true
        } finally {
            blockArena.close()
        }
    }

    private fun onSampleBuffer(sampleBuffer: MemorySegment) {
        val imageBuffer = cmSampleBufferGetImageBuffer.invokeExact(sampleBuffer) as MemorySegment
        if (imageBuffer == MemorySegment.NULL) return

        cvPixelBufferLockBaseAddress.invokeExact(imageBuffer, 1L)

        try {
            val width = (cvPixelBufferGetWidth.invokeExact(imageBuffer) as Long).toInt()
            val height = (cvPixelBufferGetHeight.invokeExact(imageBuffer) as Long).toInt()
            val bytesPerRow = (cvPixelBufferGetBytesPerRow.invokeExact(imageBuffer) as Long).toInt()
            val baseAddr = cvPixelBufferGetBaseAddress.invokeExact(imageBuffer) as MemorySegment

            val dataSize = bytesPerRow * height
            val data = ByteArray(dataSize)
            val src = baseAddr.reinterpret(dataSize.toLong())
            MemorySegment.copy(src, java.lang.foreign.ValueLayout.JAVA_BYTE, 0, data, 0, dataSize)

            val frame = CaptureFrame(
                size = PhysicalSize(width, height),
                format = PixelFormat.BGRA8,
                stride = bytesPerRow,
                data = data,
                timestampNanos = System.nanoTime(),
            )
            _frames.tryEmit(frame)
        } finally {
            cvPixelBufferUnlockBaseAddress.invokeExact(imageBuffer, 1L)
        }
    }

    override fun close() {
        if (started) {
            started = false
            val stopArena = Arena.ofShared()
            val handler = ObjCCallback1 { _ ->
                delegateArena.close()
                stopArena.close()
            }
            val upcallStub = linker.upcallStub(handler.methodHandle, handler.fnDescriptor, stopArena)
            val block = ObjCBlocks.create(upcallStub, stopArena)
            ObjCRuntime.msgSend(null, nativeStream, ObjCRuntime.sel("stopCaptureWithCompletionHandler:"), block)
            ObjCRuntime.msgSend(null, nativeStream, ObjCRuntime.sel("release"))
            handler.await(5000)
        } else {
            delegateArena.close()
        }
    }
}
