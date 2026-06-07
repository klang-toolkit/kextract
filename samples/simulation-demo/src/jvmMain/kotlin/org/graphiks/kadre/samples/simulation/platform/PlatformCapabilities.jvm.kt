package org.graphiks.kadre.samples.simulation.platform

import org.graphiks.kadre.samples.simulation.Capability
import org.graphiks.kadre.samples.simulation.Platform

actual object PlatformCapabilities {
    actual fun supports(capability: Capability, platform: Platform): Boolean {
        return when (platform) {
            Platform.MACOS -> supportsMacos(capability)
            Platform.WINDOWS -> supportsWindows(capability)
            Platform.LINUX_X11 -> supportsX11(capability)
            Platform.LINUX_WAYLAND -> supportsWayland(capability)
            else -> false
        }
    }

    actual val currentPlatform: Platform
        get() = Platform.current()

    private fun supportsMacos(capability: Capability): Boolean = when (capability) {
        Capability.KEYBOARD -> true
        Capability.MOUSE -> true
        Capability.TOUCH -> true
        Capability.MULTI_TOUCH -> true
        Capability.GAMEPAD -> false
        Capability.IME -> true
        Capability.CURSOR_GRAB -> true
        Capability.CURSOR_POSITION -> true
        Capability.CURSOR_HITTEST -> true
        Capability.MULTI_WINDOW -> true
    }

    private fun supportsWindows(capability: Capability): Boolean = when (capability) {
        Capability.KEYBOARD -> true
        Capability.MOUSE -> true
        Capability.TOUCH -> false
        Capability.MULTI_TOUCH -> false
        Capability.GAMEPAD -> false
        Capability.IME -> false
        Capability.CURSOR_GRAB -> false
        Capability.CURSOR_POSITION -> false
        Capability.CURSOR_HITTEST -> false
        Capability.MULTI_WINDOW -> true
    }

    private fun supportsX11(capability: Capability): Boolean = when (capability) {
        Capability.KEYBOARD -> true
        Capability.MOUSE -> true
        Capability.TOUCH -> false
        Capability.MULTI_TOUCH -> false
        Capability.GAMEPAD -> false
        Capability.IME -> false
        Capability.CURSOR_GRAB -> false
        Capability.CURSOR_POSITION -> false
        Capability.CURSOR_HITTEST -> false
        Capability.MULTI_WINDOW -> true
    }

    private fun supportsWayland(capability: Capability): Boolean = when (capability) {
        Capability.KEYBOARD -> true
        Capability.MOUSE -> true
        Capability.TOUCH -> false
        Capability.MULTI_TOUCH -> false
        Capability.GAMEPAD -> false
        Capability.IME -> false
        Capability.CURSOR_GRAB -> false
        Capability.CURSOR_POSITION -> false
        Capability.CURSOR_HITTEST -> false
        Capability.MULTI_WINDOW -> true
    }
}
