package org.graphiks.kadre.wayland.portal

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.*
import kotlinx.coroutines.*
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

/**
 * Capture session using xdg-desktop-portal ScreenCast interface.
 * 
 * This session handles the full portal flow:
 * 1. CreateSession - establishes a session with the portal
 * 2. SelectSources - prompts user to select display/window
 * 3. Start - begins the capture stream
 * 4. OpenPipeWireRemote - gets a file descriptor for the stream
 * 5. Read frames via mmap on the file descriptor
 * 
 * Note: This implementation requires xdg-desktop-portal with ScreenCast support
 * (typically provided by xdg-desktop-portal-gnome or xdg-desktop-portal-kde).
 */
internal class XdpPortalCaptureSession(
    source: CaptureSource,
    config: CaptureConfig,
) : CaptureSession(source, config) {
    
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val arena = Arena.ofShared()
    
    // Portal state
    private var sessionHandle: String? = null
    private var streamNodeId: Int? = null
    private var pipeWireRemote: XdpPipeWire.PipeWireRemoteResult? = null
    
    // Frame state
    private var currentFrame: XdpPipeWire.Frame? = null
    private var isCapturing = false
    private var frameCount = 0
    
    init {
        scope.launch {
            try {
                startPortalSession()
            } catch (e: Exception) {
                _frames.tryEmit(createErrorFrame(e))
                close()
            }
        }
    }
    
    /**
     * Start the portal session flow.
     */
    private suspend fun startPortalSession() {
        // Step 1: Create session
        sessionHandle = XdpPortal.createSession("kadre")
            ?: throw CaptureError.Internal(RuntimeException("Failed to create portal session"))
        
        println("[XdpPortal] Session created: $sessionHandle")
        
        // Step 2: Select sources based on capture source type
        val types = when (source) {
            is CaptureSource.Display -> listOf("monitor")
            is CaptureSource.Window -> listOf("window")
        }
        
        val cursorMode = if (config.captureCursor) "embedded" else "none"
        
        val success = XdpPortal.selectSources(
            sessionHandle!!,
            types = types,
            multiple = false,
            cursorMode = cursorMode
        )
        
        if (!success) {
            throw CaptureError.Internal(RuntimeException("Failed to select sources"))
        }
        
        println("[XdpPortal] Sources selected")
        
        // Step 3: Start the session
        val startResult = XdpPortal.startSession(sessionHandle!!, null)
        
        if (startResult == null) {
            throw CaptureError.Internal(RuntimeException("Failed to start session"))
        }
        
        streamNodeId = startResult.streamNodeId
        println("[XdpPortal] Session started, stream node ID: $streamNodeId")
        
        // Step 4: Open PipeWire remote to get file descriptor
        pipeWireRemote = XdpPipeWire.openPipeWireRemote(sessionHandle!!, streamNodeId!!)
            ?: throw CaptureError.Internal(RuntimeException("Failed to open PipeWire remote"))
        
        println("[XdpPortal] PipeWire remote opened: ${pipeWireRemote!!.width}x${pipeWireRemote!!.height}, format=${pipeWireRemote!!.format}")
        
        // Step 5: Start capture loop
        isCapturing = true
        captureLoop()
    }
    
    /**
     * Main capture loop - reads frames from the mmap'd file descriptor.
     */
    private suspend fun captureLoop() {
        while (isCapturing && scope.isActive) {
            try {
                val pwFrame = readNextFrame()
                if (pwFrame != null) {
                    val captureFrame = XdpPipeWire.toCaptureFrame(pwFrame, config.pixelFormat)
                    _frames.tryEmit(captureFrame)
                    XdpPipeWire.releaseFrame(pwFrame)
                    frameCount++
                    println("[XdpPortal] Frame $frameCount captured: ${captureFrame.size.width}x${captureFrame.size.height}")
                }
                
                // Control frame rate
                val delayMs = if (config.frameRate > 0) 1000L / config.frameRate else 33L
                delay(delayMs)
            } catch (e: Exception) {
                // Log error but continue
                System.err.println("[XdpPortal] Frame capture error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
    
    /**
     * Read the next frame from the PipeWire remote file descriptor.
     */
    private suspend fun readNextFrame(): XdpPipeWire.Frame? {
        val remote = pipeWireRemote ?: return null
        
        return withContext(Dispatchers.IO) {
            XdpPipeWire.readFrameFromFd(
                remote.fd,
                remote.width,
                remote.height,
                remote.stride,
                remote.format
            )
        }
    }
    
    /**
     * Create an error frame for error reporting.
     */
    private fun createErrorFrame(e: Exception): CaptureFrame {
        val errorData = ByteArray(100 * 100 * 4)  // 100x100 red error image
        // Fill with red
        for (i in errorData.indices step 4) {
            errorData[i] = 0xFF.toByte()     // R
            errorData[i + 1] = 0x00.toByte() // G
            errorData[i + 2] = 0x00.toByte() // B
            errorData[i + 3] = 0xFF.toByte() // A
        }
        
        return CaptureFrame(
            size = PhysicalSize(100, 100),
            format = PixelFormat.RGBA8,
            stride = 400,
            data = errorData,
            timestampNanos = System.nanoTime()
        )
    }
    
    override fun close() {
        isCapturing = false
        
        // Cleanup PipeWire remote
        pipeWireRemote?.let { remote ->
            try {
                // Close the file descriptor
                // Note: In a real implementation, we'd also signal the portal to stop
            } catch (_: Exception) {}
        }
        pipeWireRemote = null
        
        // Cleanup portal session
        sessionHandle?.let { handle ->
            XdpPortal.closeSession(handle)
        }
        sessionHandle = null
        streamNodeId = null
        
        // Cleanup coroutines
        scope.cancel()
        arena.close()
        
        println("[XdpPortal] Session closed")
    }
}
