package org.graphiks.kadre.wayland

// xdg_wm_base opcodes
internal const val XDG_WM_BASE_DESTROY: Int = 0
internal const val XDG_WM_BASE_CREATE_POSITIONER: Int = 1
internal const val XDG_WM_BASE_GET_XDG_SURFACE: Int = 2
internal const val XDG_WM_BASE_PONG: Int = 3

// xdg_surface opcodes
internal const val XDG_SURFACE_DESTROY: Int = 0
internal const val XDG_SURFACE_GET_TOPLEVEL: Int = 1
internal const val XDG_SURFACE_SET_WINDOW_GEOMETRY: Int = 3
internal const val XDG_SURFACE_ACK_CONFIGURE: Int = 4

// xdg_toplevel opcodes
internal const val XDG_TOPLEVEL_DESTROY: Int = 0
internal const val XDG_TOPLEVEL_SET_PARENT: Int = 1
internal const val XDG_TOPLEVEL_SET_TITLE: Int = 2
internal const val XDG_TOPLEVEL_SET_APP_ID: Int = 3
internal const val XDG_TOPLEVEL_SHOW_WINDOW_MENU: Int = 4
internal const val XDG_TOPLEVEL_MOVE: Int = 5
internal const val XDG_TOPLEVEL_RESIZE: Int = 6
internal const val XDG_TOPLEVEL_SET_MAX_SIZE: Int = 7
internal const val XDG_TOPLEVEL_SET_MIN_SIZE: Int = 8
internal const val XDG_TOPLEVEL_SET_MAXIMIZED: Int = 9
internal const val XDG_TOPLEVEL_UNSET_MAXIMIZED: Int = 10
internal const val XDG_TOPLEVEL_SET_MINIMIZED: Int = 13
internal const val XDG_TOPLEVEL_SET_FULLSCREEN: Int = 11
internal const val XDG_TOPLEVEL_UNSET_FULLSCREEN: Int = 12

// xdg_decoration_manager_v1 opcodes
internal const val XDG_DECORATION_MANAGER_DESTROY: Int = 0
internal const val XDG_DECORATION_MANAGER_GET_TOPLEVEL_DECORATION: Int = 1

// xdg_toplevel_decoration_v1 opcodes
internal const val XDG_TOPLEVEL_DECORATION_DESTROY: Int = 0
internal const val XDG_TOPLEVEL_DECORATION_SET_MODE: Int = 1

// Decoration modes
internal const val XDG_TOPLEVEL_DECORATION_MODE_CLIENT_SIDE: Int = 1
internal const val XDG_TOPLEVEL_DECORATION_MODE_SERVER_SIDE: Int = 2

// xdg_toplevel.resize edges
internal const val XDG_TOPLEVEL_RESIZE_EDGE_NONE: Int = 0
internal const val XDG_TOPLEVEL_RESIZE_EDGE_TOP: Int = 1
internal const val XDG_TOPLEVEL_RESIZE_EDGE_BOTTOM: Int = 2
internal const val XDG_TOPLEVEL_RESIZE_EDGE_LEFT: Int = 4
internal const val XDG_TOPLEVEL_RESIZE_EDGE_TOP_LEFT: Int = 5
internal const val XDG_TOPLEVEL_RESIZE_EDGE_BOTTOM_LEFT: Int = 6
internal const val XDG_TOPLEVEL_RESIZE_EDGE_RIGHT: Int = 8
internal const val XDG_TOPLEVEL_RESIZE_EDGE_TOP_RIGHT: Int = 9
internal const val XDG_TOPLEVEL_RESIZE_EDGE_BOTTOM_RIGHT: Int = 10

// WL_MARSHAL_FLAG_DESTROY
internal const val WL_MARSHAL_FLAG_DESTROY: Int = 1
