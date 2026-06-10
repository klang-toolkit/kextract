package org.graphiks.kadre.samples.compose.showcase.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.graphiks.kadre.samples.compose.showcase.FeatureCategory
import org.graphiks.kadre.samples.compose.showcase.PlatformContext
import org.graphiks.kadre.samples.compose.showcase.ShowcaseFeature

object CaptureDemo : ShowcaseFeature {
    override val id = "capture"
    override val title = "Offscreen Capture"
    override val description = "Raster and windowed GPU capture for headless CI verification"
    override val category = FeatureCategory.Capture
    override val devOnly = true

    @Composable
    override fun Content(platformContext: PlatformContext) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
        ) {
            Text("Offscreen Capture & CI")
            Text("Internal feature \u2014 used for headless rendering verification.")
        }
    }
}
