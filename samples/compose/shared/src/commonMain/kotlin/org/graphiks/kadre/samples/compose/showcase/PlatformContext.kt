package org.graphiks.kadre.samples.compose.showcase

expect class PlatformContext {
    fun isFeatureSupported(category: FeatureCategory): Boolean
}
