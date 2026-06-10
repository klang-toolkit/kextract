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

object KeyTestDemo : ShowcaseFeature {
    override val id = "keytest"
    override val title = "Keyboard Test"
    override val description = "Headless keyboard input forwarding self-test"
    override val category = FeatureCategory.Keyboard
    override val devOnly = false

    @Composable
    override fun Content(platformContext: PlatformContext) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
        ) {
            Text("Keyboard Input Forwarding")
            Text("Demonstrates AWT KeyEvent \u2192 Compose key event conversion.")
        }
    }
}
