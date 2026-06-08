package org.graphiks.kadre.samples.simulation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
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

        if (appHandler.activeScenarioId == "game-simple" && appHandler.currentScenarioState.isRunning) {
            GameView(
                appHandler = appHandler,
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        } else if (appHandler.currentScenarioState.message != null) {
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

// ── CLI interactive mode ─────────────────────────────────────────────

class CliDisplayState {
    var currentScenario by mutableStateOf<ScenarioMetadata?>(null)
    var scenarioMessage by mutableStateOf("")
    var remainingTime by mutableIntStateOf(0)
    var results by mutableStateOf(listOf<Pair<String, ScenarioResult>>())
    var currentIndex by mutableIntStateOf(0)
    var totalCount by mutableIntStateOf(0)
    var isRunning by mutableStateOf(false)
    var isAllDone by mutableStateOf(false)
    var eventLog by mutableStateOf(listOf<String>())
    var gameData by mutableStateOf<Map<String, Any>>(emptyMap())

    fun logEvent(entry: String) {
        val last = eventLog.lastOrNull()
        if (last != null) {
            val base = last.replace(Regex(" \\(\\d+\\)$"), "")
            if (base == entry) {
                val count = Regex("\\((\\d+)\\)$").find(last)?.groupValues?.get(1)?.toIntOrNull() ?: 1
                eventLog = eventLog.dropLast(1) + "$entry (${count + 1})"
                return
            }
        }
        eventLog = (eventLog + entry).takeLast(200)
    }

    fun clearEventLog() {
        eventLog = emptyList()
    }
}

@Composable
fun CliScenarioDisplay(state: CliDisplayState) {
    SimulationDemoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.padding(24.dp).fillMaxSize()) {
                Text(
                    text = "Kadre Simulation Demo",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (state.isAllDone) {
                    AllDoneSummary(state.results)
                } else {
                    state.currentScenario?.let { meta ->
                        if (state.totalCount > 1) {
                            Text(
                                text = "Scénario ${state.currentIndex}/${state.totalCount}",
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.primary
                            )
                        }

                        Text(
                            text = meta.scenario.title,
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Text(
                            text = meta.scenario.description,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )

                        Spacer(Modifier.height(24.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = 4.dp
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "État: ${if (state.isRunning) "▶ En cours..." else "⏹ Terminé"}",
                                    style = MaterialTheme.typography.h6
                                )
                                Spacer(Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Temps restant: ${state.remainingTime}s",
                                        style = MaterialTheme.typography.body1
                                    )
                                    Text(
                                        text = "Événements: ${state.results.lastOrNull()?.second?.eventsReceived ?: 0}",
                                        style = MaterialTheme.typography.body1
                                    )
                                }

                                if (state.scenarioMessage.isNotBlank()) {
                                    Spacer(Modifier.height(12.dp))
                                    Text(
                                        text = state.scenarioMessage,
                                        style = MaterialTheme.typography.body1,
                                        color = MaterialTheme.colors.primary
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        if (state.currentScenario?.scenario?.id == "game-simple" && state.isRunning) {
                            GameView(
                                data = state.gameData,
                                message = state.scenarioMessage,
                                modifier = Modifier.weight(1f).fillMaxWidth()
                            )
                        } else if (state.eventLog.isNotEmpty()) {
                            Text(
                                text = "Événements:",
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Card(
                                modifier = Modifier
                                    .heightIn(max = 300.dp)
                                    .fillMaxWidth(),
                                elevation = 2.dp
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp)
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    state.eventLog.takeLast(60).forEach { entry ->
                                        Text(
                                            text = entry,
                                            style = MaterialTheme.typography.caption,
                                            fontFamily = FontFamily.Monospace,
                                            modifier = Modifier.padding(vertical = 1.dp)
                                        )
                                    }
                                }
                            }
                        }

                        if (state.results.isNotEmpty()) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Résultats:",
                                style = MaterialTheme.typography.h6
                            )
                            state.results.forEach { (_, result) ->
                                ResultCard(result = result)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AllDoneSummary(results: List<Pair<String, ScenarioResult>>) {
    Column(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
        Text(
            text = "✅ Tous les scénarios sont terminés",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val successes = results.count { it.second.success }
        Text(
            text = "$successes/${results.size} scénarios réussis",
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(Modifier.height(16.dp))

        results.forEach { (id, result) ->
            Card(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = id, style = MaterialTheme.typography.subtitle2)
                    Text(
                        text = if (result.success) "✅ Succès" else "❌ Échec",
                        style = MaterialTheme.typography.caption
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
    }
}
