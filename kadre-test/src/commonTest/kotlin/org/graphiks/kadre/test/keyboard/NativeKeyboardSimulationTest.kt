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
                keyWithoutModifiers = "s"
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
                        keyWithoutModifiers = "s",
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

    @Test
    fun doneProofReportRejectsL4OnlyBackendRuntimeProof() {
        val report = KeyboardProofReport(
            target = "web runtime parity",
            status = KeyboardProofStatus.Done,
            entries = listOf(
                KeyboardProofEntry(
                    scenario = "raw key event",
                    backend = KeyboardBackend.Web,
                    scope = KeyboardValidationScope.BackendRuntime,
                    fidelity = KeyboardEventFidelity.KadreEvent,
                    coverageKind = KeyboardProofCoverageKind.BackendRuntime,
                    observedEventCount = 1,
                )
            ),
        )

        val result = report.validate()

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("uses L4 KadreEvent") })
        assertTrue(result.failures.any { it.contains("requires at least one accepted BackendRuntime proof") })
    }

    @Test
    fun doneProofReportRejectsWebBackendWithoutL1Proof() {
        val report = KeyboardProofReport(
            target = "web runtime parity",
            status = KeyboardProofStatus.Done,
            entries = listOf(
                KeyboardProofEntry(
                    scenario = "dom fixture key a",
                    backend = KeyboardBackend.Web,
                    scope = KeyboardValidationScope.BackendRuntime,
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    coverageKind = KeyboardProofCoverageKind.BackendRuntime,
                    nativeInput = "DOM KeyboardEvent fixture",
                    mapper = "kadre-web-dom-keyboard",
                    observedEventCount = 1,
                )
            ),
        )

        val result = report.validate()

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("uses NativeMessage for Web") })
    }

    @Test
    fun doneProofReportAcceptsWin32NativeMessageRuntimeProof() {
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
            )
        }
        val adapter = NativeKeyboardInputAdapter<Win32KeyboardMessageInput>(
            backend = KeyboardBackend.Win32,
            acceptedFidelities = setOf(KeyboardEventFidelity.NativeMessage),
            mapperName = "win32-message-runtime",
        )
        val input = Win32KeyboardMessageInput(
            message = 0x0100u,
            wParam = 0x28u,
            lParam = 0x01500001u,
            virtualKey = 0x28,
            scanCode = 0xE050,
            extended = true,
        )
        val evidence = listOf(
            adapter.evidenceFor(
                input,
                keyEvent(
                    physicalKey = PhysicalKey.Code(KeyCode.ArrowDown),
                    logicalKey = LogicalKey.Named(NamedKey.ArrowDown),
                    native = NativeKeyInfo(
                        nativeCode = NativeKeyCode.Win32(scanCode = 0xE050, virtualKey = 0x28),
                        nativeKey = NativeLogicalKey.Win32(virtualKey = 0x28),
                    ),
                )
            )
        )
        val report = KeyboardProofReport(
            target = "win32 arrow runtime parity",
            status = KeyboardProofStatus.Done,
            entries = listOf(scenario.proofEntry(evidence)),
            pullRequest = "https://github.com/ygdrasil-io/poc-koreos/pull/example",
            commit = "abcdef0",
        )

        val result = report.validate()

        assertTrue(result.passed, result.failures.joinToString())
    }

    @Test
    fun partialProofReportAllowsMissingBackendRuntimeProofWithExplicitGaps() {
        val report = KeyboardProofReport(
            target = "web runtime parity",
            status = KeyboardProofStatus.Partial,
            entries = listOf(
                KeyboardProofEntry(
                    scenario = "key table coverage",
                    backend = KeyboardBackend.Common,
                    scope = KeyboardValidationScope.CommonContract,
                    fidelity = KeyboardEventFidelity.KadreEvent,
                    coverageKind = KeyboardProofCoverageKind.ApiTable,
                    observedEventCount = 0,
                    gaps = listOf("backend runtime proof not wired"),
                )
            ),
            gaps = listOf("Web L1 Playwright still required"),
        )

        val result = report.validate()

        assertTrue(result.passed, result.failures.joinToString())
    }

    @Test
    fun partialProofReportAllowsProvisionalBackendRuntimeProofWithExplicitGaps() {
        val report = KeyboardProofReport(
            target = "web runtime parity",
            status = KeyboardProofStatus.Partial,
            entries = listOf(
                KeyboardProofEntry(
                    scenario = "dom fixture key a",
                    backend = KeyboardBackend.Web,
                    scope = KeyboardValidationScope.BackendRuntime,
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    coverageKind = KeyboardProofCoverageKind.BackendRuntime,
                    nativeInput = "DOM KeyboardEvent fixture",
                    mapper = "kadre-web-dom-keyboard",
                    observedEventCount = 1,
                    gaps = listOf("L1 Playwright proof still required for Done"),
                )
            ),
            gaps = listOf("Web L1 Playwright still required"),
        )

        val result = report.validate()

        assertTrue(result.passed, result.failures.joinToString())
    }

    @Test
    fun doneProofReportRejectsRemainingGaps() {
        val report = KeyboardProofReport(
            target = "win32 arrow runtime parity",
            status = KeyboardProofStatus.Done,
            entries = listOf(
                KeyboardProofEntry(
                    scenario = "win32 keydown arrow down",
                    backend = KeyboardBackend.Win32,
                    scope = KeyboardValidationScope.BackendRuntime,
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    coverageKind = KeyboardProofCoverageKind.BackendRuntime,
                    nativeInput = "Win32 keyboard message(message=256, virtualKey=40, scanCode=57424)",
                    mapper = "win32-message-runtime",
                    observedEventCount = 1,
                    gaps = listOf("WM_CHAR not covered"),
                )
            ),
            gaps = listOf("dead keys not covered"),
        )

        val result = report.validate()

        assertFalse(result.passed)
        assertTrue(result.failures.any { it.contains("cannot list remaining gaps") })
        assertTrue(result.failures.any { it.contains("Done keyboard proof report") })
    }

    @Test
    fun proofReportJsonDistinguishesCoverageKindsAndNativeProofFields() {
        val report = KeyboardProofReport(
            target = "win32 arrow runtime parity",
            status = KeyboardProofStatus.Partial,
            entries = listOf(
                KeyboardProofEntry(
                    scenario = "win32 keydown arrow down",
                    backend = KeyboardBackend.Win32,
                    scope = KeyboardValidationScope.BackendRuntime,
                    fidelity = KeyboardEventFidelity.NativeMessage,
                    coverageKind = KeyboardProofCoverageKind.BackendRuntime,
                    nativeInput = "Win32 keyboard message(message=256, virtualKey=40, scanCode=57424)",
                    mapper = "win32-message-runtime",
                    observedEventCount = 1,
                    gaps = listOf("WM_CHAR not covered"),
                )
            ),
            gaps = listOf("WM_CHAR not covered"),
            pullRequest = "https://github.com/ygdrasil-io/poc-koreos/pull/example",
            commit = "abcdef0",
        )

        val json = report.toJsonString()

        assertTrue(json.contains("\"target\":\"win32 arrow runtime parity\""))
        assertTrue(json.contains("\"coverageKind\":\"BackendRuntime\""))
        assertTrue(json.contains("\"fidelity\":\"NativeMessage\""))
        assertTrue(json.contains("\"nativeInput\":\"Win32 keyboard message"))
        assertTrue(json.contains("\"mapper\":\"win32-message-runtime\""))
        assertTrue(json.contains("\"gaps\":[\"WM_CHAR not covered\"]"))
    }

    private fun keyEvent(
        physicalKey: PhysicalKey,
        logicalKey: LogicalKey,
        state: KeyState = KeyState.Pressed,
        modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
        location: KeyLocation = KeyLocation.Standard,
        repeat: Boolean = false,
        text: String? = null,
        keyWithoutModifiers: String? = null,
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
