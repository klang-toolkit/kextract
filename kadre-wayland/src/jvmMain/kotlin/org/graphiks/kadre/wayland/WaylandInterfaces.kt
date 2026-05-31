package org.graphiks.kadre.wayland

// Interface names for wl_registry_bind
internal const val WL_COMPOSITOR_INTERFACE_NAME: String = "wl_compositor"
internal const val XDG_WM_BASE_INTERFACE_NAME: String = "xdg_wm_base"
internal const val XDG_DECORATION_MANAGER_INTERFACE_NAME: String = "zxdg_decoration_manager_v1"
internal const val WL_SEAT_INTERFACE_NAME: String = "wl_seat"
internal const val WL_OUTPUT_INTERFACE_NAME: String = "wl_output"

// Minimum supported versions
internal const val XDG_WM_BASE_VERSION: Int = 2
internal const val WL_COMPOSITOR_VERSION: Int = 4
