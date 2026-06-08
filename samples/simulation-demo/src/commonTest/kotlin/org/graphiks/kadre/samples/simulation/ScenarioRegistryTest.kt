package org.graphiks.kadre.samples.simulation

import kotlin.test.*

class ScenarioRegistryTest {

    @BeforeTest
    fun setUp() {
        ScenarioRegistry.clear()
        ScenarioRegistry.markInitialized()
    }

    @AfterTest
    fun tearDown() {
        ScenarioRegistry.clear()
    }

    @Test
    fun `register scenario should add to registry`() {
        assertTrue(ScenarioRegistry.all().isEmpty())
        ScenarioRegistry.register(SimpleScenario(id = "test-1", title = "Test Scenario"))

        assertEquals(1, ScenarioRegistry.all().size)
        assertNotNull(ScenarioRegistry.get("test-1"))
    }

    @Test
    fun `get by id should return correct scenario`() {
        ScenarioRegistry.register(SimpleScenario(id = "test-a", title = "A"))
        ScenarioRegistry.register(SimpleScenario(id = "test-b", title = "B"))

        val result = ScenarioRegistry.get("test-a")
        assertNotNull(result)
        assertEquals("test-a", result.scenario.id)
        assertEquals("A", result.scenario.title)
    }

    @Test
    fun `get by non-existent id should return null`() {
        assertNull(ScenarioRegistry.get("non-existent"))
    }

    @Test
    fun `get by category should filter correctly`() {
        ScenarioRegistry.register(SimpleScenario(id = "k1", title = "K1", category = "Keyboard"))
        ScenarioRegistry.register(SimpleScenario(id = "k2", title = "K2", category = "Keyboard"))
        ScenarioRegistry.register(SimpleScenario(id = "m1", title = "M1", category = "Mouse"))

        val keyboardScenarios = ScenarioRegistry.getByCategory("Keyboard")
        assertEquals(2, keyboardScenarios.size)
        assertTrue(keyboardScenarios.all { it.scenario.category == "Keyboard" })
    }

    @Test
    fun `get categories should return unique set`() {
        ScenarioRegistry.register(SimpleScenario(id = "k1", title = "K1", category = "Keyboard"))
        ScenarioRegistry.register(SimpleScenario(id = "m1", title = "M1", category = "Mouse"))
        ScenarioRegistry.register(SimpleScenario(id = "w1", title = "W1", category = "Window"))

        val categories = ScenarioRegistry.getCategories()
        assertEquals(3, categories.size)
        assertTrue(categories.containsAll(setOf("Keyboard", "Mouse", "Window")))
    }

    @Test
    fun `clear should remove all scenarios`() {
        ScenarioRegistry.register(SimpleScenario(id = "t1", title = "T1"))
        assertEquals(1, ScenarioRegistry.all().size)

        ScenarioRegistry.clear()
        ScenarioRegistry.markInitialized()
        assertTrue(ScenarioRegistry.all().isEmpty())
    }

    @Test
    fun `register with ScenarioMetadata should work`() {
        val scenario = SimpleScenario(id = "meta-test", title = "Meta")
        val metadata = ScenarioMetadata(
            scenario = scenario,
            availableOn = setOf(Platform.MACOS)
        )
        ScenarioRegistry.register(metadata)

        val result = ScenarioRegistry.get("meta-test")
        assertNotNull(result)
        assertEquals(setOf(Platform.MACOS), result.availableOn)
    }

    @Test
    fun `registering multiple scenarios should accumulate`() {
        ScenarioRegistry.register(SimpleScenario(id = "s1", title = "S1"))
        ScenarioRegistry.register(SimpleScenario(id = "s2", title = "S2"))

        assertEquals(2, ScenarioRegistry.all().size)
    }

    @Test
    fun `getAvailableFor should respect platform availability`() {
        val macOnly = ScenarioMetadata(
            scenario = SimpleScenario(id = "mac-only", title = "Mac Only"),
            platformSupport = mapOf(
                Platform.MACOS to SupportLevel.FULL,
                Platform.LINUX_X11 to SupportLevel.NOT_AVAILABLE
            ),
            availableOn = setOf(Platform.MACOS)
        )
        ScenarioRegistry.register(macOnly)

        val linuxScenarios = ScenarioRegistry.getAvailableFor(Platform.LINUX_X11)
        assertTrue(linuxScenarios.isEmpty())
    }

    @Test
    fun `getAvailableFor should include scenarios available for all platforms`() {
        val universal = ScenarioMetadata(
            scenario = SimpleScenario(id = "universal", title = "Universal"),
            availableOn = Platform.ALL
        )
        ScenarioRegistry.register(universal)

        val macScenarios = ScenarioRegistry.getAvailableFor(Platform.MACOS)
        assertTrue(macScenarios.any { it.scenario.id == "universal" })
    }

    @Test
    fun `register scenario defaults availableOn to all platforms`() {
        ScenarioRegistry.register(SimpleScenario(id = "default", title = "Default"))

        val meta = ScenarioRegistry.get("default")
        assertNotNull(meta)
        assertEquals(Platform.ALL, meta.availableOn)
    }
}

class SimpleScenario(
    override val id: String,
    override val title: String,
    override val description: String = "Test scenario",
    override val category: String = "Test",
    override val requiredCapabilities: Set<Capability> = emptySet(),
    override val priority: Int = 0
) : Scenario {
    override fun start(
        window: org.graphiks.kadre.core.Window,
        eventLoop: org.graphiks.kadre.core.ActiveEventLoop,
        onEvent: (ScenarioEvent) -> Unit
    ) {}
    override fun stop() {}
    override fun collectResult(durationMs: Long): ScenarioResult {
        return ScenarioResult(true, durationMs, 0, 0, platform = Platform.current())
    }
}
