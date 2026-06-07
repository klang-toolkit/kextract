@file:OptIn(androidx.compose.material.ExperimentalMaterialApi::class)
package org.graphiks.kadre.samples.simulation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.graphiks.kadre.samples.simulation.*

@Composable
fun ScenarioCard(
    metadata: ScenarioMetadata,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentPlatform = Platform.current()
    val supportLevel = metadata.platformSupport.getOrDefault(currentPlatform, SupportLevel.FULL)

    Card(
        onClick = { if (supportLevel != SupportLevel.NOT_AVAILABLE) onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = if (supportLevel == SupportLevel.NOT_AVAILABLE) 0.dp else 4.dp,
        backgroundColor = when (supportLevel) {
            SupportLevel.NOT_AVAILABLE -> MaterialTheme.colors.surface.copy(alpha = 0.5f)
            else -> MaterialTheme.colors.surface
        },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = metadata.scenario.title,
                    style = MaterialTheme.typography.h6,
                    color = if (supportLevel == SupportLevel.NOT_AVAILABLE) {
                        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    } else {
                        MaterialTheme.colors.onSurface
                    }
                )
                SupportBadge(supportLevel = supportLevel)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = metadata.scenario.description,
                style = MaterialTheme.typography.body2,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = if (supportLevel == SupportLevel.NOT_AVAILABLE) {
                    MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                } else {
                    MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                CategoryChip(category = metadata.scenario.category)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = metadata.scenario.id,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun SupportBadge(supportLevel: SupportLevel) {
    val (color, text) = when (supportLevel) {
        SupportLevel.FULL -> Pair(Color(0xFF4CAF50), "✓")
        SupportLevel.PARTIAL -> Pair(Color(0xFFFFC107), "⚠")
        SupportLevel.STUB -> Pair(Color(0xFFFF9800), "△")
        SupportLevel.NOT_AVAILABLE -> Pair(MaterialTheme.colors.error, "✗")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(24.dp)
            .background(color, RoundedCornerShape(4.dp))
    ) {
        Text(text)
    }
}

@Composable
fun CategoryChip(category: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(
                MaterialTheme.colors.primary.copy(alpha = 0.2f),
                RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = category,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.caption
        )
    }
}
