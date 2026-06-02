package org.graphiks.kadre.test.keyboard

import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyLocation
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.NativeLogicalKey
import org.graphiks.kadre.core.PhysicalKey

/**
 * Fidelity of a keyboard validation path.
 *
 * L4 validates Kadre contracts and application behavior only. Backend parity
 * requires L1, L2 or L3 so the mapper sees a system-shaped input.
 */
enum class KeyboardEventFidelity {
    /** L1: the OS or browser produces the event for a real window/canvas. */
    RealOsEvent,

    /** L2: the exact native message received by a backend is injected. */
    NativeMessage,

    /** L3: a realistic native structure is passed through the backend mapper. */
    NativeFixture,

    /** L4: a normalized Kadre event is injected directly. */
    KadreEvent,
}

enum class KeyboardValidationScope {
    /** Common API or application behavior; L4 is acceptable here. */
    CommonContract,

    /** Backend mapper/runtime behavior; L4 is never sufficient. */
    BackendRuntime,
}

enum class KeyboardBackend {
    Common,
    Web,
    Android,
    Win32,
    X11,
    Wayland,
    AppKit,
    UIKit,
}

data class KeyboardBackendValidationPolicy(
    val acceptedFidelityByBackend: Map<KeyboardBackend, Set<KeyboardEventFidelity>> = defaultAcceptedFidelityByBackend,
) {
    fun accepts(backend: KeyboardBackend, fidelity: KeyboardEventFidelity): Boolean =
        fidelity in acceptedFidelityByBackend[backend].orEmpty()

    fun acceptedFor(backend: KeyboardBackend): Set<KeyboardEventFidelity> =
        acceptedFidelityByBackend[backend].orEmpty()

    companion object {
        val defaultAcceptedFidelityByBackend: Map<KeyboardBackend, Set<KeyboardEventFidelity>> = mapOf(
            KeyboardBackend.Common to setOf(KeyboardEventFidelity.KadreEvent),
            KeyboardBackend.Web to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
            KeyboardBackend.Android to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
            KeyboardBackend.Win32 to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage),
            KeyboardBackend.X11 to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
            KeyboardBackend.Wayland to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
            KeyboardBackend.AppKit to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
            KeyboardBackend.UIKit to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
        )
    }
}

data class KeyboardScenario(
    val name: String,
    val backend: KeyboardBackend,
    val fidelity: KeyboardEventFidelity,
    val scope: KeyboardValidationScope,
    val expectations: List<ExpectedKeyEvent>,
) {
    fun verify(
        actualEvents: List<KeyEvent>,
        policy: KeyboardBackendValidationPolicy = KeyboardBackendValidationPolicy(),
    ): KeyboardValidationResult =
        verifyEvidence(
            actualEvents.map { KeyboardEventEvidence(KeyboardEventFidelity.KadreEvent, it) },
            policy,
        )

    fun verifyEvidence(
        actualEvents: List<KeyboardEventEvidence>,
        policy: KeyboardBackendValidationPolicy = KeyboardBackendValidationPolicy(),
    ): KeyboardValidationResult {
        val failures = mutableListOf<String>()
        if (scope == KeyboardValidationScope.BackendRuntime && fidelity == KeyboardEventFidelity.KadreEvent) {
            failures += "BackendRuntime scenario '$name' cannot be validated with L4 KadreEvent"
        }
        if (backend == KeyboardBackend.Common && scope == KeyboardValidationScope.BackendRuntime) {
            failures += "BackendRuntime scenario '$name' must target a concrete backend"
        }
        if (scope == KeyboardValidationScope.BackendRuntime && !policy.accepts(backend, fidelity)) {
            failures += "BackendRuntime scenario '$name' uses $fidelity for $backend, expected one of ${policy.acceptedFor(backend)}"
        }
        if (scope == KeyboardValidationScope.BackendRuntime) {
            actualEvents.forEachIndexed { index, evidence ->
                if (evidence.fidelity == KeyboardEventFidelity.KadreEvent) {
                    failures += "event[$index] for BackendRuntime scenario '$name' has L4 KadreEvent evidence"
                }
                if (evidence.fidelity != fidelity) {
                    failures += "event[$index] for scenario '$name' has ${evidence.fidelity} evidence, expected $fidelity"
                }
            }
        }
        if (actualEvents.size != expectations.size) {
            failures += "Scenario '$name' expected ${expectations.size} key event(s), got ${actualEvents.size}"
        }

        val count = minOf(actualEvents.size, expectations.size)
        for (index in 0 until count) {
            failures += expectations[index].verify(actualEvents[index].event, index)
        }

        return KeyboardValidationResult(failures)
    }
}

data class KeyboardEventEvidence(
    val fidelity: KeyboardEventFidelity,
    val event: KeyEvent,
    val source: String? = null,
)

data class KeyboardValidationResult(val failures: List<String>) {
    val passed: Boolean get() = failures.isEmpty()
}

data class ExpectedKeyEvent(
    val label: String,
    val physicalKey: PhysicalKey? = null,
    val logicalKey: LogicalKey? = null,
    val state: KeyState? = null,
    val modifiers: KeyboardModifiers? = null,
    val requiredModifiers: KeyboardModifiers? = null,
    val location: KeyLocation? = null,
    val repeat: Boolean? = null,
    val synthetic: Boolean? = null,
    val text: String? = null,
    val textWithAllModifiers: String? = null,
    val keyWithoutModifiers: LogicalKey? = null,
    val nativeCode: NativeKeyCode? = null,
    val nativeKey: NativeLogicalKey? = null,
) {
    fun verify(actual: KeyEvent, index: Int): List<String> {
        val prefix = "event[$index] '$label'"
        val failures = mutableListOf<String>()
        fun expect(condition: Boolean, message: String) {
            if (!condition) failures += "$prefix: $message"
        }

        physicalKey?.let { expect(actual.physicalKey == it, "physicalKey expected $it, got ${actual.physicalKey}") }
        logicalKey?.let { expect(actual.logicalKey == it, "logicalKey expected $it, got ${actual.logicalKey}") }
        state?.let { expect(actual.state == it, "state expected $it, got ${actual.state}") }
        modifiers?.let { expect(actual.modifiers == it, "modifiers expected $it, got ${actual.modifiers}") }
        requiredModifiers?.let {
            expect(actual.modifiers.contains(it), "modifiers expected to contain $it, got ${actual.modifiers}")
        }
        location?.let { expect(actual.location == it, "location expected $it, got ${actual.location}") }
        repeat?.let { expect(actual.repeat == it, "repeat expected $it, got ${actual.repeat}") }
        synthetic?.let { expect(actual.synthetic == it, "synthetic expected $it, got ${actual.synthetic}") }
        text?.let { expect(actual.text == it, "text expected '$it', got '${actual.text}'") }
        textWithAllModifiers?.let {
            expect(actual.textWithAllModifiers == it, "textWithAllModifiers expected '$it', got '${actual.textWithAllModifiers}'")
        }
        keyWithoutModifiers?.let {
            expect(actual.keyWithoutModifiers == it, "keyWithoutModifiers expected $it, got ${actual.keyWithoutModifiers}")
        }
        nativeCode?.let { expect(actual.native.nativeCode == it, "nativeCode expected $it, got ${actual.native.nativeCode}") }
        nativeKey?.let { expect(actual.native.nativeKey == it, "nativeKey expected $it, got ${actual.native.nativeKey}") }

        return failures
    }
}

class KeyboardScenarioBuilder internal constructor(
    private val name: String,
    private val backend: KeyboardBackend,
    private val fidelity: KeyboardEventFidelity,
    private val scope: KeyboardValidationScope,
) {
    private val expectations = mutableListOf<ExpectedKeyEvent>()

    fun expectKeyEvent(label: String, block: ExpectedKeyEventBuilder.() -> Unit) {
        expectations += ExpectedKeyEventBuilder(label).apply(block).build()
    }

    fun expectKeyPress(
        label: String,
        physicalKey: PhysicalKey,
        logicalKey: LogicalKey,
        modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
        block: ExpectedKeyEventBuilder.() -> Unit = {},
    ) {
        expectKeyEvent(label) {
            this.physicalKey = physicalKey
            this.logicalKey = logicalKey
            this.state = KeyState.Pressed
            this.modifiers = modifiers
            block()
        }
    }

    fun expectKeyRelease(
        label: String,
        physicalKey: PhysicalKey,
        logicalKey: LogicalKey,
        modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
        block: ExpectedKeyEventBuilder.() -> Unit = {},
    ) {
        expectKeyEvent(label) {
            this.physicalKey = physicalKey
            this.logicalKey = logicalKey
            this.state = KeyState.Released
            this.modifiers = modifiers
            block()
        }
    }

    internal fun build(): KeyboardScenario =
        KeyboardScenario(name, backend, fidelity, scope, expectations.toList())
}

class ExpectedKeyEventBuilder internal constructor(private val label: String) {
    var physicalKey: PhysicalKey? = null
    var logicalKey: LogicalKey? = null
    var state: KeyState? = null
    var modifiers: KeyboardModifiers? = null
    var requiredModifiers: KeyboardModifiers? = null
    var location: KeyLocation? = null
    var repeat: Boolean? = null
    var synthetic: Boolean? = null
    var text: String? = null
    var textWithAllModifiers: String? = null
    var keyWithoutModifiers: LogicalKey? = null
    var nativeCode: NativeKeyCode? = null
    var nativeKey: NativeLogicalKey? = null

    internal fun build(): ExpectedKeyEvent = ExpectedKeyEvent(
        label = label,
        physicalKey = physicalKey,
        logicalKey = logicalKey,
        state = state,
        modifiers = modifiers,
        requiredModifiers = requiredModifiers,
        location = location,
        repeat = repeat,
        synthetic = synthetic,
        text = text,
        textWithAllModifiers = textWithAllModifiers,
        keyWithoutModifiers = keyWithoutModifiers,
        nativeCode = nativeCode,
        nativeKey = nativeKey,
    )
}

fun nativeKeyboardScenario(
    name: String,
    backend: KeyboardBackend,
    fidelity: KeyboardEventFidelity,
    scope: KeyboardValidationScope,
    block: KeyboardScenarioBuilder.() -> Unit,
): KeyboardScenario =
    KeyboardScenarioBuilder(name, backend, fidelity, scope).apply(block).build()
