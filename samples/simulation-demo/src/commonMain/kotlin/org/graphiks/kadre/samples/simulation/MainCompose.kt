package org.graphiks.kadre.samples.simulation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.graphiks.kadre.samples.simulation.theme.SimulationDemoTheme
import org.graphiks.kadre.samples.simulation.platform.PlatformCapabilities
import org.graphiks.kadre.samples.simulation.ui.*

@Composable
fun SimulationDemoMain(appHandler: SimulationAppHandler) {
    val navigationState = rememberNavigationState()
    var selectedScenario by remember { mutableStateOf<ScenarioMetadata?>(null) }
    var availableScenarios by remember { mutableStateOf(emptyList<ScenarioMetadata>()) }

    LaunchedEffect(Unit) {
        availableScenarios = ScenarioRegistry.getAvailableFor(PlatformCapabilities.currentPlatform)
    }

    SimulationDemoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            when (navigationState.currentScreen) {
                Screen.MENU -> MenuScreen(
                    scenarios = availableScenarios,
                    selectedScenario = selectedScenario,
                    onScenarioSelected = { metadata ->
                        selectedScenario = metadata
                    },
                    onStartScenario = { metadata ->
                        selectedScenario = metadata
                        appHandler.launchScenario(metadata.scenario) {}
                        navigationState.navigateTo(Screen.SCENARIO)
                    },
                    onShowInfo = { metadata ->
                        selectedScenario = metadata
                        navigationState.navigateTo(Screen.INFO)
                    },
                    modifier = Modifier.fillMaxSize()
                )

                Screen.INFO -> InfoScreen(
                    metadata = selectedScenario,
                    onBack = {
                        navigationState.goBack()
                    },
                    modifier = Modifier.fillMaxSize()
                )

                Screen.SCENARIO -> ScenarioScreen(
                    appHandler = appHandler,
                    onBack = {
                        appHandler.returnToMenu()
                        navigationState.reset()
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun MenuScreen(
    scenarios: List<ScenarioMetadata>,
    selectedScenario: ScenarioMetadata?,
    onScenarioSelected: (ScenarioMetadata) -> Unit,
    onStartScenario: (ScenarioMetadata) -> Unit,
    onShowInfo: (ScenarioMetadata) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Kadre Simulation Demo",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ScenarioList(
            scenarios = scenarios,
            onScenarioSelected = { metadata ->
                onScenarioSelected(metadata)
            },
            modifier = Modifier.weight(1f)
        )

        selectedScenario?.let { metadata ->
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                StartButton(
                    onClick = { onStartScenario(metadata) },
                    modifier = Modifier.weight(1f)
                )

                OutlinedButton(
                    onClick = { onShowInfo(metadata) }
                ) {
                    Text("Info")
                }
            }
        }
    }
}

@Composable
fun InfoScreen(
    metadata: ScenarioMetadata?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        BackButton(onClick = onBack)
        Spacer(modifier = Modifier.height(8.dp))
        InfoPanel(
            metadata = metadata,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ScenarioScreen(
    appHandler: SimulationAppHandler,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackButton(onClick = onBack)

            if (appHandler.currentScenarioState.isRunning) {
                Text(
                    text = "Scénario en cours d'exécution...",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (appHandler.currentScenarioState.message != null) {
            Text(
                text = appHandler.currentScenarioState.message ?: "",
                style = MaterialTheme.typography.body1
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (appHandler.results.isNotEmpty()) {
            Text(
                text = "Résultats:",
                style = MaterialTheme.typography.h6
            )

            appHandler.results.forEach { result ->
                ResultCard(result = result)
            }
        }
    }
}

@Composable
fun ResultCard(result: ScenarioResult) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = if (result.success) "✅ Succès" else "❌ Échec",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = "Événements: ${result.eventsReceived}/${result.eventsExpected}",
                style = MaterialTheme.typography.caption
            )
            if (result.errors.isNotEmpty()) {
                Text(
                    text = "Erreurs: ${result.errors.joinToString(", ")}",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}
