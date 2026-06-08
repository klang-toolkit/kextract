package org.graphiks.kadre.samples.simulation

import kotlin.test.*

class CliTest {

    @Test
    fun `parse empty args returns default INTERACTIVE`() {
        val args = Cli.parse(emptyList())
        assertEquals(Cli.Action.INTERACTIVE, args.action)
    }

    @Test
    fun `parse --list sets LIST action`() {
        val args = Cli.parse(listOf("--list"))
        assertEquals(Cli.Action.LIST, args.action)
    }

    @Test
    fun `parse --scenario sets RUN action with scenario ID`() {
        val args = Cli.parse(listOf("--scenario", "keyboard-basic"))
        assertEquals(Cli.Action.RUN, args.action)
        assertEquals("keyboard-basic", args.scenarioId)
    }

    @Test
    fun `parse --scenario without ID does not set RUN action`() {
        val args = Cli.parse(listOf("--scenario"))
        assertEquals(Cli.Action.INTERACTIVE, args.action)
        assertNull(args.scenarioId)
    }

    @Test
    fun `parse --all with --scenario sets RUN_ALL`() {
        val args = Cli.parse(listOf("--all", "--scenario", "test"))
        assertEquals(Cli.Action.RUN_ALL, args.action)
    }

    @Test
    fun `parse --all without --scenario sets RUN_ALL action`() {
        val args = Cli.parse(listOf("--all"))
        assertEquals(Cli.Action.RUN_ALL, args.action)
        assertTrue(args.all)
    }

    @Test
    fun `parse --duration sets duration value`() {
        val args = Cli.parse(listOf("--duration", "30"))
        assertEquals(30, args.duration)
    }

    @Test
    fun `parse --duration with invalid value falls back to default`() {
        val args = Cli.parse(listOf("--duration", "not-a-number"))
        assertEquals(5, args.duration)
    }

    @Test
    fun `parse --duration without value stays at default`() {
        val args = Cli.parse(listOf("--duration"))
        assertEquals(5, args.duration)
    }

    @Test
    fun `parse --output sets output path`() {
        val args = Cli.parse(listOf("--output", "results.json"))
        assertEquals("results.json", args.output)
    }

    @Test
    fun `parse --output without value returns null output`() {
        val args = Cli.parse(listOf("--output"))
        assertNull(args.output)
    }

    @Test
    fun `parse --interactive sets INTERACTIVE action`() {
        val args = Cli.parse(listOf("--interactive"))
        assertEquals(Cli.Action.INTERACTIVE, args.action)
    }

    @Test
    fun `parse --info sets info flag`() {
        val args = Cli.parse(listOf("--info"))
        assertTrue(args.info)
    }

    @Test
    fun `parse complex args combination`() {
        val args = Cli.parse(listOf(
            "--scenario", "mouse-clicks",
            "--duration", "15",
            "--output", "out.json"
        ))
        assertEquals(Cli.Action.RUN, args.action)
        assertEquals("mouse-clicks", args.scenarioId)
        assertEquals(15, args.duration)
        assertEquals("out.json", args.output)
    }

    @Test
    fun `parse -h and --help should not crash`() {
        Cli.parse(listOf("-h"))
        Cli.parse(listOf("--help"))
    }

    @Test
    fun `parse --scenario overrides --list when ordered after`() {
        val args = Cli.parse(listOf("--list", "--scenario", "test"))
        assertEquals(Cli.Action.RUN, args.action)
        assertEquals("test", args.scenarioId)
    }

    @Test
    fun `parse --list overrides --scenario when ordered after`() {
        val args = Cli.parse(listOf("--scenario", "test", "--list"))
        assertEquals(Cli.Action.LIST, args.action)
    }

    @Test
    fun `execute --list should return 0`() {
        ScenarioRegistry.clear()
        try {
            val exitCode = Cli.execute(Cli.CliArgs(action = Cli.Action.LIST))
            assertEquals(0, exitCode)
        } finally {
            ScenarioRegistry.clear()
        }
    }
}
