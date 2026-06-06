@file:OptIn(ExperimentalForeignApi::class)

package org.graphiks.kadre.uikit.capture

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.capture.CaptureConfig
import org.graphiks.kadre.core.capture.CapturePermission
import org.graphiks.kadre.core.capture.CaptureSession
import org.graphiks.kadre.core.capture.CaptureSource
import org.graphiks.kadre.core.capture.DisplayInfo
import org.graphiks.kadre.core.capture.ScreenCapturer
import org.graphiks.kadre.core.capture.WindowInfo
import platform.CoreGraphics.CGRect
import platform.UIKit.UIScreen

class UIKitScreenCapturer : ScreenCapturer {

    override suspend fun enumerateDisplays(): List<DisplayInfo> {
        val screen = UIScreen.mainScreen
        val scale = screen.scale
        var ox = 0; var oy = 0; var w = 0; var h = 0
        screen.bounds.useContents {
            ox = this.origin.x.toInt()
            oy = this.origin.y.toInt()
            w = this.size.width.toInt()
            h = this.size.height.toInt()
        }
        return listOf(
            DisplayInfo(
                id = 0L,
                name = "Main",
                position = PhysicalPosition(ox, oy),
                resolution = PhysicalSize(
                    (w * scale).toInt(),
                    (h * scale).toInt(),
                ),
                scaleFactor = scale.toDouble(),
            )
        )
    }

    override suspend fun enumerateWindows(): List<WindowInfo> = emptyList()

    override suspend fun createSession(
        source: CaptureSource,
        config: CaptureConfig,
    ): CaptureSession {
        require(source is CaptureSource.Display) {
            "iOS supports display capture only"
        }
        return UIKitCaptureSession(source as CaptureSource.Display, config)
    }

    override suspend fun requestPermission(): CapturePermission = CapturePermission.Pending

    override fun permissionStatus(): CapturePermission = CapturePermission.Pending
}
