package org.graphiks.kadre.android.capture

import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.media.projection.MediaProjectionManager
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.CaptureConfig
import org.graphiks.kadre.core.capture.CaptureError
import org.graphiks.kadre.core.capture.CapturePermission
import org.graphiks.kadre.core.capture.CaptureSession
import org.graphiks.kadre.core.capture.CaptureSource
import org.graphiks.kadre.core.capture.DisplayInfo
import org.graphiks.kadre.core.capture.ScreenCapturer
import org.graphiks.kadre.core.capture.WindowInfo

class AndroidScreenCapturer(
    private val activity: ComponentActivity,
) : ScreenCapturer {

    private data class Grant(val resultCode: Int, val data: Intent)

    private var grant: Grant? = null

    override suspend fun enumerateDisplays(): List<DisplayInfo> {
        val manager = activity.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        return manager.displays.map { display ->
            val metrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            display.getRealMetrics(metrics)
            DisplayInfo(
                id = display.displayId.toLong(),
                name = display.name,
                position = PhysicalPosition(0, 0),
                resolution = PhysicalSize(metrics.widthPixels, metrics.heightPixels),
                scaleFactor = metrics.density.toDouble(),
            )
        }
    }

    override suspend fun enumerateWindows(): List<WindowInfo> = emptyList()

    override suspend fun createSession(
        source: CaptureSource,
        config: CaptureConfig,
    ): CaptureSession {
        val display = source as? CaptureSource.Display
            ?: throw CaptureError.Unsupported("Android supports only Display capture sources")
        val (resultCode, data) = grant
            ?: throw CaptureError.PermissionDenied("Grant screen capture permission via requestPermission() first")
        return withContext(Dispatchers.Main) {
            val manager = activity.getSystemService(Context.MEDIA_PROJECTION_SERVICE)
                as MediaProjectionManager
            val projection = manager.getMediaProjection(resultCode, data)
                ?: throw CaptureError.Internal(IllegalStateException("MediaProjection is null"))
            AndroidCaptureSession(display, config, projection, activity)
        }
    }

    override suspend fun requestPermission(): CapturePermission = withContext(Dispatchers.Main) {
        val manager = activity.getSystemService(Context.MEDIA_PROJECTION_SERVICE)
            as MediaProjectionManager
        val deferred = kotlinx.coroutines.CompletableDeferred<CapturePermission>()
        val launcher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result: ActivityResult ->
            if (result.resultCode == android.app.Activity.RESULT_OK && result.data != null) {
                grant = Grant(result.resultCode, result.data!!)
                deferred.complete(CapturePermission.Granted)
            } else {
                deferred.complete(
                    CapturePermission.Denied("User declined screen capture permission"),
                )
            }
        }
        launcher.launch(manager.createScreenCaptureIntent())
        deferred.await()
    }

    override fun permissionStatus(): CapturePermission {
        return if (grant != null) CapturePermission.Granted else CapturePermission.Pending
    }
}
