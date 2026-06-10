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

object CoroutinesDemo : ShowcaseFeature {
    override val id = "coroutines"
    override val title = "Coroutines Demo"
    override val description = "Kadre event loop driven by coroutines with delay()"
    override val category = FeatureCategory.Concurrency
    override val devOnly = false

    @Composable
    override fun Content(platformContext: PlatformContext) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
        ) {
            Text("Coroutine-driven Kadre Loop")
            Text("Demonstrates EventLoopDispatcher, delay(), and structured shutdown.")
        }
    }
}
