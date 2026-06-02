package org.graphiks.kadre.test.keyboard

import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyLocation
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.NamedKey
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.NativeLogicalKey
import org.graphiks.kadre.core.PhysicalKey
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NativeKeyboardSimulationTest {

    @Test
    fun backendRuntimeCannotBeValidatedWithKadreEventFidelity() {
        val scenario = nativeKeyboardScenario(
            name = "bad backend validation",
            backend = KeyboardBackend.Web,
            fidelity = KeyboardEventFidelity.KadreEvent,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "arrow down",
                physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            )
        }

        val result = scenario.verify(
            listOf(
                keyEvent(
                    physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                    logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("cannot be validated with L4 KadreEvent") })
    }

    @Test
    fun nativeFixtureFidelityCanValidateBackendRuntime() {
        val scenario = nativeKeyboardScenario(
            name = "win32 extended arrow",
            backend = KeyboardBackend.Win32,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "extended arrow down",
                physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            ) {
                location = KeyLocation.Standard
                repeat = false
                nativeCode = NativeKeyCode.Win32(scanCode = 0xE050, virtualKey = 0x28)
                nativeKey = NativeLogicalKey.Win32(virtualKey = 0x28)
            }
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                        logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
                        native = NativeKeyInfo(
                            nativeCode = NativeKeyCode.Win32(scanCode = 0xE050, virtualKey = 0x28),
                            nativeKey = NativeLogicalKey.Win32(virtualKey = 0x28),
                        )
                    ),
                    nativeInput = Win32KeyboardMessageInput(
                        message = 0x0100u,
                        wParam = 0x28u,
                        lParam = 0x01500001u,
                        virtualKey = 0x28,
                        scanCode = 0xE050,
                        extended = true,
                    )
                )
            )
        )

        assertTrue(result.passed, result.failures.joinToString())
    }

    @Test
    fun rawKeyEventsAreTreatedAsKadreEventFidelityAndRejectedForBackendRuntime() {
        val scenario = nativeKeyboardScenario(
            name = "raw event cannot prove backend",
            backend = KeyboardBackend.Win32,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "arrow down",
                physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            )
        }

        val result = scenario.verify(
            listOf(
                keyEvent(
                    physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                    logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
                    native = NativeKeyInfo(
                        nativeCode = NativeKeyCode.Win32(scanCode = 0xE050, virtualKey = 0x28),
                        nativeKey = NativeLogicalKey.Win32(virtualKey = 0x28),
                    )
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("has L4 KadreEvent evidence") })
    }

    @Test
    fun backendPolicyRejectsUnsupportedFidelityForBackend() {
        val scenario = nativeKeyboardScenario(
            name = "web fixture declared as win32",
            backend = KeyboardBackend.Win32,
            fidelity = KeyboardEventFidelity.NativeFixture,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "arrow down",
                physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            )
        }

        val result = scenario.verify(
            listOf(
                keyEvent(
                    physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                    logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("uses NativeFixture for Win32") })
    }

    @Test
    fun webBackendAcceptsNativeMessageFidelity() {
        val scenario = nativeKeyboardScenario(
            name = "dom keyboard event",
            backend = KeyboardBackend.Web,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "arrow down",
                physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            )
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                        logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
                        native = NativeKeyInfo(
                            nativeCode = NativeKeyCode.Web("ArrowDown"),
                            nativeKey = NativeLogicalKey.Web("ArrowDown"),
                        ),
                    ),
                    nativeInput = WebDomKeyboardEventInput(
                        type = "keydown",
                        key = "ArrowDown",
                        code = "ArrowDown",
                    )
                )
            )
        )

        assertTrue(result.passed, result.failures.joinToString())
    }

    @Test
    fun backendRuntimeRequiresNativeInputEvidence() {
        val scenario = nativeKeyboardScenario(
            name = "web native proof missing",
            backend = KeyboardBackend.Web,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "arrow down",
                physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            )
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                        logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
                    )
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("has no native input evidence") })
    }

    @Test
    fun nativeInputBackendMustMatchScenarioBackend() {
        val scenario = nativeKeyboardScenario(
            name = "win32 proof backed by dom event",
            backend = KeyboardBackend.Win32,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "arrow down",
                physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            )
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                        logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
                    ),
                    nativeInput = WebDomKeyboardEventInput(
                        type = "keydown",
                        key = "ArrowDown",
                        code = "ArrowDown",
                    )
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("has Web native input, expected Win32") })
    }

    @Test
    fun nativeInputFidelityMustMatchScenarioAndEvidenceFidelity() {
        val scenario = nativeKeyboardScenario(
            name = "dom event with fixture adapter",
            backend = KeyboardBackend.Web,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "key a",
                physicalKey = PhysicalKey.Code(KeyCode.KeyA),
                logicalKey = LogicalKey.Character("a"),
            )
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.KeyA),
                        logicalKey = LogicalKey.Character("a"),
                    ),
                    nativeInput = WebDomKeyboardEventInput(
                        type = "keydown",
                        key = "a",
                        code = "KeyA",
                        fidelity = KeyboardEventFidelity.NativeFixture,
                    )
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("has NativeFixture native input, expected NativeMessage") })
        assertTrue(result.failures.any { it.contains("has NativeMessage evidence but NativeFixture native input") })
    }

    @Test
    fun nativeInputMustMatchMappedEventNativeIdentity() {
        val scenario = nativeKeyboardScenario(
            name = "dom key identity mismatch",
            backend = KeyboardBackend.Web,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "key a",
                physicalKey = PhysicalKey.Code(KeyCode.KeyA),
                logicalKey = LogicalKey.Character("a"),
            )
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.KeyA),
                        logicalKey = LogicalKey.Character("a"),
                        native = NativeKeyInfo(
                            nativeCode = NativeKeyCode.Web("KeyB"),
                            nativeKey = NativeLogicalKey.Web("a"),
                        ),
                    ),
                    nativeInput = WebDomKeyboardEventInput(
                        type = "keydown",
                        key = "a",
                        code = "KeyA",
                    )
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("nativeCode expected Web(code=KeyA)") })
    }

    @Test
    fun webNativeInputMustMatchDomTypeAndLocation() {
        val scenario = nativeKeyboardScenario(
            name = "dom keyup numpad mismatch",
            backend = KeyboardBackend.Web,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyEvent("numpad release") {
                physicalKey = PhysicalKey.Code(KeyCode.Numpad1)
                logicalKey = LogicalKey.Character("1")
                state = KeyState.Released
                location = KeyLocation.Numpad
            }
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.Numpad1),
                        logicalKey = LogicalKey.Character("1"),
                        location = KeyLocation.Standard,
                    ),
                    nativeInput = WebDomKeyboardEventInput(
                        type = "keyup",
                        key = "1",
                        code = "Numpad1",
                        location = 3,
                    )
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("state expected Released") })
        assertTrue(result.failures.any { it.contains("location expected Numpad") })
    }

    @Test
    fun win32NativeInputMustMatchMessageFields() {
        val scenario = nativeKeyboardScenario(
            name = "win32 lparam mismatch",
            backend = KeyboardBackend.Win32,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "arrow down",
                physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            )
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                        logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
                        native = NativeKeyInfo(
                            nativeCode = NativeKeyCode.Win32(scanCode = 0xE050, virtualKey = 0x28),
                            nativeKey = NativeLogicalKey.Win32(virtualKey = 0x28),
                        ),
                    ),
                    nativeInput = Win32KeyboardMessageInput(
                        message = 0x0100u,
                        wParam = 0x29u,
                        lParam = 0x00480002u,
                        virtualKey = 0x28,
                        scanCode = 0xE050,
                        extended = true,
                        repeatCount = 1,
                    )
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("wParam expected virtualKey 40") })
        assertTrue(result.failures.any { it.contains("scanCode expected 72") })
        assertTrue(result.failures.any { it.contains("extended expected false") })
        assertTrue(result.failures.any { it.contains("repeatCount expected 2") })
    }

    @Test
    fun webRealOsAdapterProducesBackendEvidenceFromDomInput() {
        val scenario = nativeKeyboardScenario(
            name = "playwright dom key a",
            backend = KeyboardBackend.Web,
            fidelity = KeyboardEventFidelity.RealOsEvent,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "key a",
                physicalKey = PhysicalKey.Code(KeyCode.KeyA),
                logicalKey = LogicalKey.Character("a"),
            ) {
                text = "a"
                nativeCode = NativeKeyCode.Web("KeyA")
                nativeKey = NativeLogicalKey.Web("a")
            }
        }
        val adapter = NativeKeyboardInputAdapter<WebDomKeyboardEventInput>(
            backend = KeyboardBackend.Web,
            acceptedFidelities = setOf(KeyboardEventFidelity.RealOsEvent),
            mapperName = "playwright-dom-keyboard",
        )
        val input = WebDomKeyboardEventInput(
            type = "keydown",
            key = "a",
            code = "KeyA",
            fidelity = KeyboardEventFidelity.RealOsEvent,
        )
        val observedEvent = keyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyA),
            logicalKey = LogicalKey.Character("a"),
            text = "a",
            native = NativeKeyInfo(
                nativeCode = NativeKeyCode.Web("KeyA"),
                nativeKey = NativeLogicalKey.Web("a"),
            ),
        )

        val result = scenario.verifyEvidence(listOf(adapter.evidenceFor(input, observedEvent)))

        assertTrue(result.passed, result.failures.joinToString())
    }

    @Test
    fun win32NativeMessageAdapterProducesBackendEvidenceFromMessageInput() {
        val scenario = nativeKeyboardScenario(
            name = "win32 keydown arrow down",
            backend = KeyboardBackend.Win32,
            fidelity = KeyboardEventFidelity.NativeMessage,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "arrow down",
                physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            ) {
                nativeCode = NativeKeyCode.Win32(scanCode = 0xE050, virtualKey = 0x28)
                nativeKey = NativeLogicalKey.Win32(virtualKey = 0x28)
            }
        }
        val adapter = NativeKeyboardInputAdapter<Win32KeyboardMessageInput>(
            backend = KeyboardBackend.Win32,
            acceptedFidelities = setOf(KeyboardEventFidelity.NativeMessage),
            mapperName = "win32-message-keyboard",
        )
        val input = Win32KeyboardMessageInput(
            message = 0x0100u,
            wParam = 0x28u,
            lParam = 0x01500001u,
            virtualKey = 0x28,
            scanCode = 0xE050,
            extended = true,
        )
        val observedEvent = keyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
            logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
            native = NativeKeyInfo(
                nativeCode = NativeKeyCode.Win32(scanCode = 0xE050, virtualKey = 0x28),
                nativeKey = NativeLogicalKey.Win32(virtualKey = 0x28),
            ),
        )

        val result = scenario.verifyEvidence(listOf(adapter.evidenceFor(input, observedEvent)))

        assertTrue(result.passed, result.failures.joinToString())
    }

    @Test
    fun adapterRejectsUnsupportedInputFidelityBeforeScenarioValidation() {
        val adapter = NativeKeyboardInputAdapter<WebDomKeyboardEventInput>(
            backend = KeyboardBackend.Web,
            acceptedFidelities = setOf(KeyboardEventFidelity.RealOsEvent),
            mapperName = "playwright-dom-keyboard",
        )
        val input = WebDomKeyboardEventInput(
            type = "keydown",
            key = "a",
            code = "KeyA",
            fidelity = KeyboardEventFidelity.NativeFixture,
        )
        val observedEvent = keyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyA),
            logicalKey = LogicalKey.Character("a"),
        )

        val failure = runCatching { adapter.evidenceFor(input, observedEvent) }.exceptionOrNull()

        assertTrue(failure?.message?.contains("does not accept NativeFixture") == true)
    }

    @Test
    fun adapterRejectsL4KadreEventFidelity() {
        val failure = runCatching {
            NativeKeyboardInputAdapter<WebDomKeyboardEventInput>(
                backend = KeyboardBackend.Web,
                acceptedFidelities = setOf(KeyboardEventFidelity.KadreEvent),
                mapperName = "bad-adapter",
            )
        }.exceptionOrNull()

        assertTrue(failure?.message?.contains("cannot accept L4 KadreEvent") == true)
    }

    @Test
    fun adapterRequiresNamedMapperForAuditableProof() {
        val failure = runCatching {
            NativeKeyboardInputAdapter<WebDomKeyboardEventInput>(
                backend = KeyboardBackend.Web,
                acceptedFidelities = setOf(KeyboardEventFidelity.RealOsEvent),
                mapperName = " ",
            )
        }.exceptionOrNull()

        assertTrue(failure?.message?.contains("require a mapper/runtime name") == true)
    }

    @Test
    fun mismatchReportNamesTheFailedField() {
        val scenario = nativeKeyboardScenario(
            name = "web printable",
            backend = KeyboardBackend.Web,
            fidelity = KeyboardEventFidelity.RealOsEvent,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "key a",
                physicalKey = PhysicalKey.Code(KeyCode.KeyA),
                logicalKey = LogicalKey.Character("a"),
            ) {
                text = "a"
            }
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.RealOsEvent,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.KeyA),
                        logicalKey = LogicalKey.Character("q"),
                        text = "q",
                    ),
                    nativeInput = WebDomKeyboardEventInput(
                        type = "keydown",
                        key = "q",
                        code = "KeyA",
                        fidelity = KeyboardEventFidelity.RealOsEvent,
                    )
                )
            )
        )

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("logicalKey expected Character(text=a)") })
        assertTrue(result.failures.any { it.contains("text expected 'a'") })
    }

    @Test
    fun requiredModifiersAllowAdditionalModifiers() {
        val scenario = nativeKeyboardScenario(
            name = "shortcut modifiers",
            backend = KeyboardBackend.Web,
            fidelity = KeyboardEventFidelity.NativeFixture,
            scope = KeyboardValidationScope.BackendRuntime,
        ) {
            expectKeyPress(
                label = "ctrl shift s",
                physicalKey = PhysicalKey.Code(KeyCode.KeyS),
                logicalKey = LogicalKey.Character("s"),
                modifiers = KeyboardModifiers.Ctrl + KeyboardModifiers.Shift,
            ) {
                requiredModifiers = KeyboardModifiers.Ctrl
                keyWithoutModifiers = LogicalKey.Character("s")
            }
        }

        val result = scenario.verifyEvidence(
            listOf(
                evidence(
                    fidelity = KeyboardEventFidelity.NativeFixture,
                    event = keyEvent(
                        physicalKey = PhysicalKey.Code(KeyCode.KeyS),
                        logicalKey = LogicalKey.Character("s"),
                        modifiers = KeyboardModifiers.Ctrl + KeyboardModifiers.Shift,
                        keyWithoutModifiers = LogicalKey.Character("s"),
                        native = NativeKeyInfo(
                            nativeCode = NativeKeyCode.Web("KeyS"),
                            nativeKey = NativeLogicalKey.Web("s"),
                        ),
                    ),
                    nativeInput = WebDomKeyboardEventInput(
                        type = "keydown",
                        key = "s",
                        code = "KeyS",
                        ctrlKey = true,
                        shiftKey = true,
                        fidelity = KeyboardEventFidelity.NativeFixture,
                    )
                )
            )
        )

        assertTrue(result.passed, result.failures.joinToString())
    }

    private fun keyEvent(
        physicalKey: PhysicalKey,
        logicalKey: LogicalKey,
        state: KeyState = KeyState.Pressed,
        modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
        location: KeyLocation = KeyLocation.Standard,
        repeat: Boolean = false,
        text: String? = null,
        keyWithoutModifiers: LogicalKey? = null,
        native: NativeKeyInfo = NativeKeyInfo(),
    ): KeyEvent = KeyEvent(
        physicalKey = physicalKey,
        logicalKey = logicalKey,
        state = state,
        modifiers = modifiers,
        location = location,
        repeat = repeat,
        text = text,
        keyWithoutModifiers = keyWithoutModifiers,
        native = native,
    )

    private fun evidence(
        fidelity: KeyboardEventFidelity,
        event: KeyEvent,
        nativeInput: NativeKeyboardInput? = null,
    ): KeyboardEventEvidence = KeyboardEventEvidence(
        fidelity = fidelity,
        event = event,
        source = "test",
        nativeInput = nativeInput,
    )
}
