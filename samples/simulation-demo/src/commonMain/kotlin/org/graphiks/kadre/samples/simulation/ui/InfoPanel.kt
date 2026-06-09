package org.graphiks.kadre.samples.simulation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import org.graphiks.kadre.samples.simulation.*

@Composable
fun InfoPanel(
    metadata: ScenarioMetadata?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (metadata == null) {
            Text(
                text = "Select a scenario to see details",
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                style = MaterialTheme.typography.body1
            )
            return
        }

        Text(
            text = metadata.scenario.title,
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = metadata.scenario.description,
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Information",
            style = MaterialTheme.typography.subtitle2
        )

        Spacer(modifier = Modifier.height(8.dp))

        MetadataRow("ID", metadata.scenario.id)
        MetadataRow("Category", metadata.scenario.category)
        MetadataRow("Priority", metadata.scenario.priority.toString())

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Required capabilities",
            style = MaterialTheme.typography.subtitle2
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (metadata.scenario.requiredCapabilities.isEmpty()) {
            Text(
                text = "None",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        } else {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                metadata.scenario.requiredCapabilities.forEach { capability ->
                    CapabilityChip(capability = capability)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Platform support",
            style = MaterialTheme.typography.subtitle2
        )

        Spacer(modifier = Modifier.height(8.dp))

        Platform.ALL.forEach { platform ->
            val supportLevel = metadata.platformSupport.getOrDefault(platform, SupportLevel.FULL)
            PlatformSupportRow(
                platform = platform,
                supportLevel = supportLevel,
                limitation = metadata.limitations[platform]
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun MetadataRow(label: String, value: String) {
    Row {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.caption
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun CapabilityChip(capability: Capability) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(
                MaterialTheme.colors.primary.copy(alpha = 0.2f),
                RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = capability.name.lowercase().replace('_', ' '),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun PlatformSupportRow(
    platform: Platform,
    supportLevel: SupportLevel,
    limitation: String?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = platform.name,
            style = MaterialTheme.typography.caption
        )
        SupportBadge(supportLevel = supportLevel)
    }
}

@Composable
fun FlowRow(
    horizontalArrangement: Arrangement.Horizontal,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val spacingPx = with(density) { horizontalArrangement.spacing.toPx().toInt() }

        var y = 0
        var x = 0
        var maxHeightInRow = 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                if (x + placeable.width > constraints.maxWidth) {
                    x = 0
                    y += maxHeightInRow
                    maxHeightInRow = 0
                }

                placeable.place(x, y)

                x += placeable.width + spacingPx
                maxHeightInRow = maxOf(maxHeightInRow, placeable.height)
            }
        }
    }
}
