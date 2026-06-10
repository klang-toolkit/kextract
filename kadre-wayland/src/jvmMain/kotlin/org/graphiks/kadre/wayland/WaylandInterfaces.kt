package org.graphiks.kadre.wayland

// Interface names for wl_registry_bind
internal const val WL_COMPOSITOR_INTERFACE_NAME: String = "wl_compositor"
internal const val XDG_WM_BASE_INTERFACE_NAME: String = "xdg_wm_base"
internal const val XDG_DECORATION_MANAGER_INTERFACE_NAME: String = "zxdg_decoration_manager_v1"
internal const val WL_SEAT_INTERFACE_NAME: String = "wl_seat"
internal const val WL_OUTPUT_INTERFACE_NAME: String = "wl_output"
internal const val ZWP_TEXT_INPUT_MANAGER_V3_INTERFACE_NAME: String = "zwp_text_input_manager_v3"
internal const val WL_SHM_INTERFACE_NAME: String = "wl_shm"
internal const val ZWLRS_SCREENCOPY_MANAGER_V1_INTERFACE_NAME: String = "zwlr_screencopy_manager_v1"
internal const val XDG_TOPLEVEL_ICON_MANAGER_INTERFACE_NAME: String = "xdg_toplevel_icon_manager_v1"
internal const val XDG_ACTIVATION_V1_INTERFACE_NAME: String = "xdg_activation_v1"

internal const val EXT_BACKGROUND_EFFECT_V1_INTERFACE_NAME: String = "ext_background_effect_v1"
internal const val ORG_KDE_KWIN_BLUR_MANAGER_INTERFACE_NAME: String = "org_kde_kwin_blur_manager"

// Minimum supported versions
internal const val XDG_WM_BASE_VERSION: Int = 2
internal const val WL_COMPOSITOR_VERSION: Int = 4
