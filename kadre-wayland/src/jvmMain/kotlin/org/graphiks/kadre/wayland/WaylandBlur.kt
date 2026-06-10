/**
 * WaylandBlur — blur background effect support for Kadre Wayland backend.
 *
 * Supports two optional Wayland protocols:
 *  1. `ext_background_effect_v1` — wlroots protocol (used by Sway, River, KWin 6+)
 *  2. `org_kde_kwin_blur_manager` — KDE KWin 5.x protocol
 *
 * Tries `ext_background_effect_v1` first (modern), falls back to
 * `org_kde_kwin_blur` if unavailable. Silent no-op if neither is available.
 */
package org.graphiks.kadre.wayland

import java.lang.foreign.MemorySegment

/**
 * Manages blur background effect for a single [WaylandWindow].
 *
 * The constructor takes pointers to the two possible blur protocol managers.
 * At most one should be non-zero (whichever the compositor exposes), but
 * [setBlur] tries `ext_background_effect_v1` first regardless.
 *
 * @param extBackgroundEffectManagerPtr  ext_background_effect_v1* or 0.
 * @param kwinBlurManagerPtr             org_kde_kwin_blur_manager* or 0.
 * @param surfacePtr                     wl_surface* this window owns.
 */
internal class WaylandBlur(
    private val extBackgroundEffectManagerPtr: Long,
    private val kwinBlurManagerPtr: Long,
    private val surfacePtr: Long,
) {
    /** ext_background_effect_surface_v1 proxy, or 0 if not created. */
    private var effectSurfacePtr: Long = 0L

    /** org_kde_kwin_blur proxy, or 0 if not created. */
    private var kwinBlurPtr: Long = 0L

    /**
     * Enables or disables background blur on this surface.
     *
     * @param blur `true` to enable blur, `false` to disable.
     */
    fun setBlur(blur: Boolean) {
        if (surfacePtr == 0L) return
        if (extBackgroundEffectManagerPtr != 0L) {
            setBlurExtBackgroundEffect(blur)
        } else if (kwinBlurManagerPtr != 0L) {
            setBlurKwin(blur)
        }
    }

    private fun setBlurExtBackgroundEffect(blur: Boolean) {
        val create = extBackgroundEffectV1Create ?: return

        if (blur && effectSurfacePtr == 0L) {
            effectSurfacePtr = try {
                (create.invokeExact(
                    MemorySegment.ofAddress(extBackgroundEffectManagerPtr),
                    1, // ext_background_effect_v1.create opcode
                    extBackgroundEffectSurfaceV1Interface,
                    1, // version
                    0, // flags
                    MemorySegment.ofAddress(surfacePtr),
                    MemorySegment.NULL,
                ) as MemorySegment).address()
            } catch (_: Throwable) {
                0L
            }
        }

        if (effectSurfacePtr != 0L) {
            val setEffects = wlProxyMarshalFlagsUint ?: return
            try {
                setEffects.invokeExact(
                    MemorySegment.ofAddress(effectSurfacePtr),
                    1, // ext_background_effect_surface_v1.set_background_effects opcode
                    MemorySegment.NULL,
                    1, // version
                    0, // flags
                    if (blur) 1 else 0,
                )
            } catch (_: Throwable) { }
        }

        if (!blur && effectSurfacePtr != 0L) {
            val destroy = wlProxyMarshalFlagsVoid ?: return
            try {
                destroy.invokeExact(
                    MemorySegment.ofAddress(effectSurfacePtr),
                    0, // ext_background_effect_surface_v1.destroy opcode
                    MemorySegment.NULL,
                    1, // version
                    0, // flags
                )
            } catch (_: Throwable) { }
            effectSurfacePtr = 0L
        }
    }

    private fun setBlurKwin(blur: Boolean) {
        val create = kwinBlurManagerCreate ?: return

        if (blur && kwinBlurPtr == 0L) {
            kwinBlurPtr = try {
                (create.invokeExact(
                    MemorySegment.ofAddress(kwinBlurManagerPtr),
                    1, // org_kde_kwin_blur_manager.create opcode
                    orgKdeKwinBlurInterface,
                    1, // version
                    0, // flags
                    MemorySegment.ofAddress(surfacePtr),
                    MemorySegment.NULL,
                ) as MemorySegment).address()
            } catch (_: Throwable) {
                0L
            }

            if (kwinBlurPtr != 0L) {
                setKwinBlurRegion()
            }
        }

        if (!blur && kwinBlurPtr != 0L) {
            val destroy = wlProxyMarshalFlagsVoid ?: return
            try {
                destroy.invokeExact(
                    MemorySegment.ofAddress(kwinBlurPtr),
                    0, // org_kde_kwin_blur.destroy opcode
                    MemorySegment.NULL,
                    1, // version
                    0, // flags
                )
            } catch (_: Throwable) { }
            kwinBlurPtr = 0L
        }
    }

    private fun setKwinBlurRegion() {
        val setRegion = wlProxyMarshalFlagsObject ?: return
        try {
            setRegion.invokeExact(
                MemorySegment.ofAddress(kwinBlurPtr),
                1, // org_kde_kwin_blur.set_region opcode
                MemorySegment.NULL,
                1, // version
                0, // flags
                MemorySegment.NULL, // NULL = full-surface blur effect
            )
        } catch (_: Throwable) { }
    }
}
