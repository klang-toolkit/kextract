/**
 * R2 — Monitor enumeration via NSScreen for AppKit.
 *
 * NSScreen is not included in --include-objc-class so we use ObjCRuntime.msgSend
 * for all calls, following the same pattern as the rest of the AppKit backend.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kffi.objc.ObjCRuntime
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.VideoMode
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * AppKit implementation of [MonitorHandle] backed by an NSScreen pointer.
 *
 * The screen pointer is not retained beyond the call: we eagerly read all properties
 * at construction time, which is safe since this is called from the main thread.
 *
 * @param screenPtr  NSScreen instance pointer (may be null/NULL → returns a fallback handle).
 * @param isPrimary  Whether this screen is `NSScreen.mainScreen`.
 */
internal class AppKitMonitorHandle(
    screenPtr: MemorySegment,
    private val isPrimary: Boolean,
) : MonitorHandle {

    override val id: Long = screenPtr.address()

    override val name: String? = readScreenName(screenPtr)

    override val position: PhysicalPosition<Int> = readScreenPosition(screenPtr)

    override val scaleFactor: Double = readBackingScaleFactor(screenPtr)

    override val currentVideoMode: VideoMode? = readCurrentVideoMode(screenPtr, scaleFactor)

    override val videoModes: List<VideoMode> = emptyList() // NSScreen does not enumerate alternative modes

    companion object {

        /**
         * Returns all connected screens as [AppKitMonitorHandle] objects.
         *
         * Queries NSScreen.screens (an NSArray), iterates over it via ObjC messaging,
         * and maps each element to a [AppKitMonitorHandle].
         */
        fun allScreens(): List<AppKitMonitorHandle> {
            return try {
                val nsScreenClass = ObjCRuntime.getClass("NSScreen")
                val mainScreen = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    nsScreenClass,
                    ObjCRuntime.sel("mainScreen"),
                ) as MemorySegment
                val mainAddr = mainScreen.address()

                // NSScreen.screens → NSArray*
                val screensArray = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    nsScreenClass,
                    ObjCRuntime.sel("screens"),
                ) as MemorySegment
                if (screensArray == MemorySegment.NULL || screensArray.address() == 0L) {
                    return listOf(AppKitMonitorHandle(mainScreen, true))
                }

                val count = ObjCRuntime.msgSend(
                    ValueLayout.JAVA_LONG,
                    screensArray,
                    ObjCRuntime.sel("count"),
                ) as Long

                (0 until count).map { i ->
                    val screen = ObjCRuntime.msgSend(
                        ValueLayout.ADDRESS,
                        screensArray,
                        ObjCRuntime.sel("objectAtIndex:"),
                        i,
                    ) as MemorySegment
                    AppKitMonitorHandle(screen, screen.address() == mainAddr)
                }
            } catch (_: Throwable) {
                emptyList()
            }
        }

        /**
         * Returns the primary monitor (NSScreen.mainScreen).
         */
        fun primaryScreen(): AppKitMonitorHandle? {
            return try {
                val nsScreenClass = ObjCRuntime.getClass("NSScreen")
                val mainScreen = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    nsScreenClass,
                    ObjCRuntime.sel("mainScreen"),
                ) as MemorySegment
                if (mainScreen == MemorySegment.NULL || mainScreen.address() == 0L) null
                else AppKitMonitorHandle(mainScreen, true)
            } catch (_: Throwable) {
                null
            }
        }

        // ── NSScreen property readers ─────────────────────────────────────────

        private fun readBackingScaleFactor(screenPtr: MemorySegment): Double {
            return try {
                ObjCRuntime.msgSend(
                    ValueLayout.JAVA_DOUBLE,
                    screenPtr,
                    ObjCRuntime.sel("backingScaleFactor"),
                ) as Double
            } catch (_: Throwable) { 1.0 }
        }

        /**
         * Reads the screen frame (in Cocoa logical coordinates, origin at bottom-left)
         * and converts the origin to physical pixels.
         *
         * NSScreen.frame is a CGRect (4 × CGFloat = 32 bytes, 8-byte aligned).
         */
        private fun readScreenPosition(screenPtr: MemorySegment): PhysicalPosition<Int> {
            return try {
                val frame = ObjCRuntime.msgSendStret(
                    java.lang.foreign.MemoryLayout.structLayout(
                        java.lang.foreign.MemoryLayout.structLayout(
                            ValueLayout.JAVA_DOUBLE.withName("x"),
                            ValueLayout.JAVA_DOUBLE.withName("y"),
                        ).withName("origin"),
                        java.lang.foreign.MemoryLayout.structLayout(
                            ValueLayout.JAVA_DOUBLE.withName("width"),
                            ValueLayout.JAVA_DOUBLE.withName("height"),
                        ).withName("size"),
                    ).withName("CGRect"),
                    screenPtr,
                    ObjCRuntime.sel("frame"),
                ) as MemorySegment
                val scale = readBackingScaleFactor(screenPtr)
                val x = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 0)
                val y = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 1)
                PhysicalPosition((x * scale).toInt(), (y * scale).toInt())
            } catch (_: Throwable) { PhysicalPosition(0, 0) }
        }

        /**
         * Reads the localizedName of an NSScreen (macOS 10.15+).
         * Falls back to null if the selector is not available.
         */
        private fun readScreenName(screenPtr: MemorySegment): String? {
            return try {
                val nsStringPtr = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    screenPtr,
                    ObjCRuntime.sel("localizedName"),
                ) as MemorySegment
                if (nsStringPtr == MemorySegment.NULL || nsStringPtr.address() == 0L) return null
                // Convert NSString → Kotlin String via UTF8String
                val utf8Ptr = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    nsStringPtr,
                    ObjCRuntime.sel("UTF8String"),
                ) as MemorySegment
                if (utf8Ptr == MemorySegment.NULL || utf8Ptr.address() == 0L) return null
                utf8Ptr.reinterpret(256).getString(0)
            } catch (_: Throwable) { null }
        }

        /**
         * Reads the current video mode from NSScreen.frame size + backingScaleFactor.
         *
         * NSScreen does not expose the refresh rate or bit depth via public APIs
         * without CoreGraphics, so only the resolution is available.
         */
        private fun readCurrentVideoMode(screenPtr: MemorySegment, scale: Double): VideoMode? {
            return try {
                val frame = ObjCRuntime.msgSendStret(
                    java.lang.foreign.MemoryLayout.structLayout(
                        java.lang.foreign.MemoryLayout.structLayout(
                            ValueLayout.JAVA_DOUBLE.withName("x"),
                            ValueLayout.JAVA_DOUBLE.withName("y"),
                        ).withName("origin"),
                        java.lang.foreign.MemoryLayout.structLayout(
                            ValueLayout.JAVA_DOUBLE.withName("width"),
                            ValueLayout.JAVA_DOUBLE.withName("height"),
                        ).withName("size"),
                    ).withName("CGRect"),
                    screenPtr,
                    ObjCRuntime.sel("frame"),
                ) as MemorySegment
                val w = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 2)
                val h = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 3)
                VideoMode(
                    size = PhysicalSize((w * scale).toInt(), (h * scale).toInt()),
                    bitDepth = null,
                    refreshRateMilliHz = null,
                )
            } catch (_: Throwable) { null }
        }
    }
}
