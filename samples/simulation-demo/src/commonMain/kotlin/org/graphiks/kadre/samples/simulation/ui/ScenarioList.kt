package org.graphiks.kadre.samples.simulation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.graphiks.kadre.samples.simulation.*

@Composable
fun ScenarioList(
    scenarios: List<ScenarioMetadata>,
    onScenarioSelected: (ScenarioMetadata) -> Unit,
    modifier: Modifier = Modifier
) {
    if (scenarios.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = "Aucun scénario disponible",
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        }
        return
    }

    val scenariosByCategory = scenarios.groupBy { it.scenario.category }

    LazyColumn(modifier = modifier) {
        scenariosByCategory.forEach { (category, categoryScenarios) ->
            stickyHeader {
                CategoryHeader(category = category)
            }

            items(categoryScenarios.sortedByDescending { it.scenario.priority }) { metadata ->
                ScenarioCard(
                    metadata = metadata,
                    onClick = { onScenarioSelected(metadata) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun CategoryHeader(category: String) {
    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "📁 $category",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(8.dp)
        )
    }
}
