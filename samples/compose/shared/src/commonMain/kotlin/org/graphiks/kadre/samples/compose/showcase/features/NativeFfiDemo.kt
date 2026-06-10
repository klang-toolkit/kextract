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

object NativeFfiDemo : ShowcaseFeature {
    override val id = "native-ffi"
    override val title = "Native FFI"
    override val description = "Panama FFM helpers for GL context backends"
    override val category = FeatureCategory.Platform
    override val devOnly = false

    @Composable
    override fun Content(platformContext: PlatformContext) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
        ) {
            Text("Panama FFM Native Helpers")
            Text("Symbol lookup, method handle creation, and pointer utilities used by GL contexts.")
        }
    }
}
