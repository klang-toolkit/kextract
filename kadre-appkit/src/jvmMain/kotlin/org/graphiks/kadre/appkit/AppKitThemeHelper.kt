/**
 * R3 — AppKit theme helpers.
 *
 * Reads and sets the NSAppearance to map macOS Light/Dark mode to [Theme].
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.ffi.objc.ObjCRuntime
import org.graphiks.kadre.core.Theme
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Helpers for reading/setting NSAppearance on an NSWindow.
 */
internal object AppKitThemeHelper {

    /**
     * Returns the effective theme for the given NSWindow pointer.
     *
     * Reads `[nsWindow effectiveAppearance]` and checks the appearance name
     * for "Dark" to decide between [Theme.Dark] and [Theme.Light].
     * Returns null if the call fails (non-macOS, or ObjC runtime not available).
     */
    fun effectiveTheme(nsWindowPtr: MemorySegment): Theme? = try {
        val appearance = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            nsWindowPtr,
            ObjCRuntime.sel("effectiveAppearance"),
        ) as MemorySegment
        if (appearance == MemorySegment.NULL || appearance.address() == 0L) return null

        // Read the appearance name (NSAppearanceName is an NSString typedef)
        val namePtr = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            appearance,
            ObjCRuntime.sel("name"),
        ) as MemorySegment
        val name = objcStringToKotlin(namePtr)
        if (name != null && name.contains("Dark", ignoreCase = true)) Theme.Dark else Theme.Light
    } catch (_: Throwable) { null }

    /**
     * Sets the NSAppearance on [nsWindowPtr].
     *
     * - [Theme.Dark]  → NSAppearanceNameDarkAqua
     * - [Theme.Light] → NSAppearanceNameAqua
     * - null          → restores the system default (nil appearance)
     */
    fun setTheme(nsWindowPtr: MemorySegment, theme: Theme?) {
        try {
            val appearanceClass = ObjCRuntime.getClass("NSAppearance")
            val appearance: MemorySegment = if (theme == null) {
                MemorySegment.NULL
            } else {
                val nameSel = if (theme == Theme.Dark) "NSAppearanceNameDarkAqua" else "NSAppearanceNameAqua"
                // NSAppearanceName constants are NSString globals, accessed via ObjC class lookup
                val nameStr = makeNSString(nameSel) ?: return
                ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    appearanceClass,
                    ObjCRuntime.sel("appearanceNamed:"),
                    nameStr,
                ) as MemorySegment
            }
            ObjCRuntime.msgSend(null, nsWindowPtr, ObjCRuntime.sel("setAppearance:"), appearance)
        } catch (_: Throwable) {}
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    /**
     * Converts an NSString pointer to a Kotlin String via UTF8String.
     * Returns null on failure.
     */
    private fun objcStringToKotlin(nsString: MemorySegment): String? = try {
        if (nsString == MemorySegment.NULL || nsString.address() == 0L) null
        else {
            val utf8Ptr = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                nsString,
                ObjCRuntime.sel("UTF8String"),
            ) as MemorySegment
            if (utf8Ptr == MemorySegment.NULL || utf8Ptr.address() == 0L) null
            else {
                // Read null-terminated UTF-8 bytes
                val bytes = buildList {
                    var i = 0L
                    while (true) {
                        val b = utf8Ptr.get(ValueLayout.JAVA_BYTE, i)
                        if (b == 0.toByte()) break
                        add(b)
                        i++
                    }
                }.toByteArray()
                String(bytes, Charsets.UTF_8)
            }
        }
    } catch (_: Throwable) { null }

    /**
     * Creates an NSString from a Kotlin String.
     * Returns null on failure.
     */
    private fun makeNSString(value: String): MemorySegment? = try {
        val nsStringClass = ObjCRuntime.getClass("NSString")
        java.lang.foreign.Arena.ofConfined().use { arena ->
            val bytes = value.toByteArray(Charsets.UTF_8)
            val buf = arena.allocate(bytes.size.toLong() + 1)
            bytes.forEachIndexed { i, b -> buf.set(ValueLayout.JAVA_BYTE, i.toLong(), b) }
            buf.set(ValueLayout.JAVA_BYTE, bytes.size.toLong(), 0)
            ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                nsStringClass,
                ObjCRuntime.sel("stringWithUTF8String:"),
                buf,
            ) as MemorySegment
        }
    } catch (_: Throwable) { null }
}
