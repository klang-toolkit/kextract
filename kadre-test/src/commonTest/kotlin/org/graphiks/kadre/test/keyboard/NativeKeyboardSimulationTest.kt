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
                    )
                )
            )
        )

        assertTrue(result.passed, result.failures.joinToString())
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
            backend = KeyboardBackend.Android,
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
    ): KeyboardEventEvidence = KeyboardEventEvidence(
        fidelity = fidelity,
        event = event,
        source = "test",
    )
}
