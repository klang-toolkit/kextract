package org.graphiks.kadre.samples.simulation

import org.graphiks.kadre.samples.simulation.platform.PlatformCapabilities

object ScenarioRegistry {
    private val _scenarios = mutableListOf<ScenarioMetadata>()
    private var _initialized = false

    fun initialize() {
        if (_initialized) return
        _initialized = true

        // Call all scenario registration functions
        val registrationFunctions = listOf(
            "org.graphiks.kadre.samples.simulation.scenarios.keyboard.KeyboardScenariosKt",
            "org.graphiks.kadre.samples.simulation.scenarios.mouse.MouseScenariosKt",
            "org.graphiks.kadre.samples.simulation.scenarios.window.WindowScenariosKt",
            "org.graphiks.kadre.samples.simulation.scenarios.touch.TouchScenariosKt",
            "org.graphiks.kadre.samples.simulation.scenarios.integration.IntegrationScenariosKt"
        )

        registrationFunctions.forEach { className ->
            try {
                Class.forName(className)
                    .getDeclaredMethod("register")
                    .invoke(null)
            } catch (e: Exception) {
                // Ignore - class may not exist yet
            }
        }
    }

    fun all(): List<ScenarioMetadata> {
        if (!_initialized) initialize()
        return _scenarios.toList()
    }

    fun get(id: String): ScenarioMetadata? {
        if (!_initialized) initialize()
        return _scenarios.find { it.scenario.id == id }
    }

    fun getAvailableFor(platform: Platform): List<ScenarioMetadata> {
        if (!_initialized) initialize()
        return _scenarios.filter { metadata ->
            metadata.availableOn.contains(platform)
        }.filter { metadata ->
            metadata.platformSupport.getOrDefault(platform, SupportLevel.FULL) != SupportLevel.NOT_AVAILABLE &&
            metadata.scenario.requiredCapabilities.all { capability ->
                PlatformCapabilities.supports(capability, platform)
            }
        }.sortedByDescending { it.scenario.priority }
    }

    fun getByCategory(category: String): List<ScenarioMetadata> {
        if (!_initialized) initialize()
        return _scenarios.filter { it.scenario.category == category }
    }

    fun getCategories(): Set<String> {
        if (!_initialized) initialize()
        return _scenarios.map { it.scenario.category }.toSet()
    }

    fun register(metadata: ScenarioMetadata) {
        if (!_initialized) initialize()
        _scenarios.add(metadata)
    }

    fun register(scenario: Scenario) {
        if (!_initialized) initialize()
        register(ScenarioMetadata(
            scenario = scenario,
            platformSupport = Platform.ALL.associateWith { SupportLevel.FULL },
            availableOn = Platform.ALL
        ))
    }

    /**
     * Clears all registered scenarios and resets initialization state.
     */
    internal fun clear() {
        _scenarios.clear()
        _initialized = false
    }

    /**
     * Marks the registry as initialized without loading scenarios.
     * Used in tests to prevent reflection-based initialization from adding real scenarios.
     */
    internal fun markInitialized() {
        _initialized = true
    }
}

fun registerScenarios() {
    ScenarioRegistry.initialize()
}
