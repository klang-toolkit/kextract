package org.graphiks.kadre.samples.simulation.platform

import org.graphiks.kadre.samples.simulation.Capability
import org.graphiks.kadre.samples.simulation.Platform

expect object PlatformCapabilities {
    fun supports(capability: Capability, platform: Platform): Boolean
    val currentPlatform: Platform
}
