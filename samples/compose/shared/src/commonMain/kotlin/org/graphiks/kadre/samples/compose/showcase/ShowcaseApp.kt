package org.graphiks.kadre.samples.compose.showcase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.graphiks.kadre.samples.compose.showcase.features.CaptureDemo
import org.graphiks.kadre.samples.compose.showcase.features.CoroutinesDemo
import org.graphiks.kadre.samples.compose.showcase.features.KeyTestDemo
import org.graphiks.kadre.samples.compose.showcase.features.NativeFfiDemo

private val allFeatures: List<ShowcaseFeature> = listOf(
    KeyTestDemo,
    CoroutinesDemo,
    NativeFfiDemo,
    CaptureDemo,
)

private val userFeatures = allFeatures.filter { !it.devOnly }
private val featuresByCategory = userFeatures.groupBy { it.category }

@Composable
fun ShowcaseApp(platformContext: PlatformContext) {
    MaterialTheme {
        var selectedCategory by remember { mutableStateOf(featuresByCategory.keys.first()) }
        var selectedFeature by remember { mutableStateOf<ShowcaseFeature?>(null) }

        BoxWithConstraints {
            val isWide = maxWidth >= 840.dp
            if (isWide) {
                DesktopLayout(
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it },
                    selectedFeature = selectedFeature,
                    onFeatureSelected = { selectedFeature = it },
                    platformContext = platformContext,
                )
            } else {
                MobileLayout(
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it },
                    selectedFeature = selectedFeature,
                    onFeatureSelected = { selectedFeature = it },
                    platformContext = platformContext,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DesktopLayout(
    selectedCategory: FeatureCategory,
    onCategorySelected: (FeatureCategory) -> Unit,
    selectedFeature: ShowcaseFeature?,
    onFeatureSelected: (ShowcaseFeature?) -> Unit,
    platformContext: PlatformContext,
) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Compose Showcase",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp),
                )
                featuresByCategory.forEach { (category, _) ->
                    NavigationDrawerItem(
                        label = { Text(category.displayName) },
                        selected = category == selectedCategory,
                        onClick = { onCategorySelected(category); onFeatureSelected(null) },
                    )
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(selectedCategory.displayName) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                )
            },
        ) { padding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 280.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize().padding(padding),
            ) {
                items(featuresByCategory[selectedCategory].orEmpty()) { feature ->
                    if (platformContext.isFeatureSupported(feature.category)) {
                        FeatureCard(
                            feature = feature,
                            onClick = { onFeatureSelected(feature) },
                            platformContext = platformContext,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MobileLayout(
    selectedCategory: FeatureCategory,
    onCategorySelected: (FeatureCategory) -> Unit,
    selectedFeature: ShowcaseFeature?,
    onFeatureSelected: (ShowcaseFeature?) -> Unit,
    platformContext: PlatformContext,
) {
    if (selectedFeature != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(selectedFeature.title) },
                    navigationIcon = {
                        IconButton(onClick = { onFeatureSelected(null) }) {
                            Text("\u2190")
                        }
                    },
                )
            },
        ) { padding ->
            Box(Modifier.fillMaxSize().padding(padding)) {
                selectedFeature.Content(platformContext)
            }
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Compose Showcase") })
            },
            bottomBar = {
                NavigationBar {
                    featuresByCategory.keys.forEach { category ->
                        NavigationBarItem(
                            selected = category == selectedCategory,
                            onClick = { onCategorySelected(category) },
                            label = { Text(category.displayName) },
                            icon = {},
                        )
                    }
                }
            },
        ) { padding ->
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize().padding(padding),
            ) {
                items(featuresByCategory[selectedCategory].orEmpty()) { feature ->
                    if (platformContext.isFeatureSupported(feature.category)) {
                        FeatureCard(
                            feature = feature,
                            onClick = { onFeatureSelected(feature) },
                            platformContext = platformContext,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FeatureCard(
    feature: ShowcaseFeature,
    onClick: () -> Unit,
    platformContext: PlatformContext,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(feature.title, style = MaterialTheme.typography.titleMedium)
            Text(feature.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}
