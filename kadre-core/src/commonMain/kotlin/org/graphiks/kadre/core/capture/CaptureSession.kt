package org.graphiks.kadre.core.capture

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first

abstract class CaptureSession(
    val source: CaptureSource,
    val config: CaptureConfig,
) : AutoCloseable {
    protected val _frames = MutableSharedFlow<CaptureFrame>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val frames: SharedFlow<CaptureFrame> = _frames

    suspend fun captureSingle(): CaptureFrame = frames.first()
}
