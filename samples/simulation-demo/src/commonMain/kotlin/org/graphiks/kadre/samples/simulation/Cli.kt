package org.graphiks.kadre.samples.simulation

object Cli {

    data class CliArgs(
        val action: Action = Action.INTERACTIVE,
        val scenarioId: String? = null,
        val output: String? = null,
        val duration: Int = 5,
        val info: Boolean = false,
        val all: Boolean = false
    )

    enum class Action {
        LIST,
        INFO,
        RUN,
        RUN_ALL,
        INTERACTIVE
    }

    fun parse(args: List<String>): CliArgs {
        if (args.isEmpty()) return CliArgs()

        var result = CliArgs()

        var i = 0
        while (i < args.size) {
            when (args[i]) {
                "--list" -> result = result.copy(action = Action.LIST)
                "--scenario" -> {
                    i++
                    if (i < args.size) {
                        result = result.copy(
                            action = Action.RUN,
                            scenarioId = args[i]
                        )
                    }
                }
                "--all" -> result = result.copy(all = true)
                "--output" -> {
                    i++
                    if (i < args.size) {
                        result = result.copy(output = args[i])
                    }
                }
                "--duration" -> {
                    i++
                    if (i < args.size) {
                        result = result.copy(duration = args[i].toIntOrNull() ?: 5)
                    }
                }
                "--info" -> result = result.copy(info = true)
                "--interactive" -> result = result.copy(action = Action.INTERACTIVE)
                "-h", "--help" -> {
                    printHelp()
                    return CliArgs(action = Action.LIST)
                }
            }
            i++
        }

        if (result.all) {
            result = result.copy(action = Action.RUN_ALL)
        }

        return result
    }

    fun execute(args: CliArgs): Int {
        registerScenarios()

        return when (args.action) {
            Action.LIST -> executeList()
            Action.INFO -> executeInfo(args)
            Action.INTERACTIVE -> 1
            Action.RUN, Action.RUN_ALL -> 1
        }
    }

    private fun executeList(): Int {
        println("=== Kadre Simulation Demo - Available Scenarios ===")
        println()

        val scenarios = ScenarioRegistry.all()
        if (scenarios.isEmpty()) {
            println("No scenarios registered.")
            return 0
        }

        val byCategory = scenarios.groupBy { it.scenario.category }
        byCategory.forEach { (category, items) ->
            println("$category")
            items.sortedByDescending { it.scenario.priority }.forEach { meta ->
                println("  ${meta.scenario.id}: ${meta.scenario.title}")
                println("    ${meta.scenario.description}")
            }
            println()
        }

        println("${scenarios.size} scenario(s) total")
        return 0
    }

    private fun executeInfo(args: CliArgs): Int {
        val id = args.scenarioId ?: return printError("Usage: --scenario <id> --info")
        val meta = ScenarioRegistry.get(id) ?: return printError("Scenario '$id' not found")

        println("=== ${meta.scenario.title} ===")
        println("ID: ${meta.scenario.id}")
        println("Category: ${meta.scenario.category}")
        println("Description: ${meta.scenario.description}")
        println("Priority: ${meta.scenario.priority}")
        println("Required capabilities: ${meta.scenario.requiredCapabilities.joinToString(", ") { it.name }}")
        println()

        println("Platform support:")
        Platform.ALL.forEach { platform ->
            val level = meta.platformSupport.getOrDefault(platform, SupportLevel.FULL)
            println("  $platform: $level")
        }

        return 0
    }

    fun printResult(result: ScenarioResult) {
        println("  Success: ${result.success}")
        println("  Duration: ${result.durationMs}ms")
        println("  Events: ${result.eventsReceived}/${result.eventsExpected}")
        if (result.errors.isNotEmpty()) {
            println("  Errors:")
            result.errors.forEach { println("    - $it") }
        }
        if (result.warnings.isNotEmpty()) {
            println("  Warnings:")
            result.warnings.forEach { println("    - $it") }
        }
        println("  Platform: ${result.platform}")
    }

    fun exportJson(result: ScenarioResult, path: String) {
        val json = buildJson(result)
        writeFile(path, json)
        println("Result exported to $path")
    }

    fun exportJsonResults(
        results: List<Pair<ScenarioMetadata, ScenarioResult>>,
        path: String
    ) {
        val json = buildString {
            appendLine("[")
            results.forEachIndexed { index, (meta, result) ->
                appendLine("  {")
                appendLine("    \"scenario_id\": \"${meta.scenario.id}\",")
                appendLine("    \"scenario_title\": \"${meta.scenario.title}\",")
                appendLine("    \"success\": ${result.success},")
                appendLine("    \"duration_ms\": ${result.durationMs},")
                appendLine("    \"events_received\": ${result.eventsReceived},")
                appendLine("    \"events_expected\": ${result.eventsExpected},")
                appendLine("    \"errors\": [${result.errors.joinToString(", ") { "\"$it\"" }}],")
                appendLine("    \"warnings\": [${result.warnings.joinToString(", ") { "\"$it\"" }}],")
                appendLine("    \"platform\": \"${result.platform}\"")
                append(if (index < results.size - 1) "  },\n" else "  }\n")
            }
            appendLine("]")
        }
        writeFile(path, json)
        println("Results exported to $path")
    }

    private fun buildJson(result: ScenarioResult): String {
        return buildString {
            appendLine("{")
            appendLine("  \"success\": ${result.success},")
            appendLine("  \"duration_ms\": ${result.durationMs},")
            appendLine("  \"events_received\": ${result.eventsReceived},")
            appendLine("  \"events_expected\": ${result.eventsExpected},")
            appendLine("  \"errors\": [${result.errors.joinToString(", ") { "\"$it\"" }}],")
            appendLine("  \"warnings\": [${result.warnings.joinToString(", ") { "\"$it\"" }}],")
            appendLine("  \"platform\": \"${result.platform}\"")
            appendLine("}")
        }
    }

    private fun writeFile(path: String, content: String) {
        try {
            val file = java.io.File(path)
            file.writeText(content)
        } catch (e: Exception) {
            println("Unable to write file '$path': ${e.message}")
        }
    }

    private fun printHelp() {
        println("""
Kadre Simulation Demo - CLI

Usage:
  --list                    List all scenarios
  --scenario <id>           Run a specific scenario (interactive)
  --all                     Run all scenarios sequentially
  --duration <sec>          Scenario duration (default: 5s)
  --output <path>           Export results as JSON
  --info                    Show scenario info
  --interactive             Interactive mode (GUI)
  -h, --help                Show this help

Examples:
  ./gradlew :samples:simulation-demo:run --list
  ./gradlew :samples:simulation-demo:run --scenario keyboard-basic --duration 10
  ./gradlew :samples:simulation-demo:run --all --output results.json
        """.trimIndent())
    }

    private fun printError(message: String): Int {
        println("$message")
        println("Use --help to see available options.")
        return 1
    }
}
