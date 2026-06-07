package org.graphiks.kadre.samples.simulation

object Cli {

    data class CliArgs(
        val action: Action = Action.INTERACTIVE,
        val scenarioId: String? = null,
        val headless: Boolean = false,
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
                "--headless" -> result = result.copy(headless = true)
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
            Action.RUN -> executeRun(args)
            Action.RUN_ALL -> executeRunAll(args)
            Action.INTERACTIVE -> 1
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

    private fun executeRun(args: CliArgs): Int {
        val id = args.scenarioId ?: return printError("Usage: --scenario <id>")
        val meta = ScenarioRegistry.get(id) ?: return printError("Scenario '$id' not found")

        println("Running '${meta.scenario.title}'...")

        val result = meta.scenario.runHeadless(listOf("--duration", args.duration.toString()))

        printResult(result)

        if (args.output != null) {
            exportJson(result, args.output)
        }

        return if (result.success) 0 else 1
    }

    private fun executeRunAll(args: CliArgs): Int {
        val scenarios = ScenarioRegistry.all()
        val results = mutableListOf<Pair<ScenarioMetadata, ScenarioResult>>()
        var exitCode = 0

        println("=== Running all scenarios (headless) ===")
        println()

        scenarios.sortedByDescending { it.scenario.priority }.forEach { meta ->
            print("Running '${meta.scenario.id}'... ")
            val result = meta.scenario.runHeadless(
                listOf("--duration", args.duration.toString())
            )
            results.add(meta to result)
            println(if (result.success) "OK" else "FAILED")
            if (!result.success) exitCode = 1
        }

        println()
        println("=== Summary ===")
        val successes = results.count { it.second.success }
        println("$successes/${results.size} scenarios succeeded")

        if (args.output != null) {
            exportJsonResults(results, args.output)
        }

        return exitCode
    }

    private fun printResult(result: ScenarioResult) {
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

    private fun exportJson(result: ScenarioResult, path: String) {
        val json = buildJson(result)
        writeFile(path, json)
        println("Result exported to $path")
    }

    private fun exportJsonResults(
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
  --scenario <id>           Run a specific scenario
  --all --headless          Run all scenarios in headless mode
  --duration <sec>          Scenario duration (default: 5s)
  --output <path>           Export results as JSON
  --info                    Show scenario info
  --interactive             Interactive mode (GUI)
  -h, --help                Show this help

Examples:
  ./gradlew :samples:simulation-demo:run --list
  ./gradlew :samples:simulation-demo:run --scenario keyboard-basic
  ./gradlew :samples:simulation-demo:run --all --headless --output results.json
        """.trimIndent())
    }

    private fun printError(message: String): Int {
        println("$message")
        println("Use --help to see available options.")
        return 1
    }
}
