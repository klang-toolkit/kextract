package org.graphiks.kadre.samples.compose.showcase

import androidx.compose.runtime.Composable

interface ShowcaseFeature {
    val id: String
    val title: String
    val description: String
    val category: FeatureCategory
    val devOnly: Boolean
    @Composable fun Content(platformContext: PlatformContext)
}
