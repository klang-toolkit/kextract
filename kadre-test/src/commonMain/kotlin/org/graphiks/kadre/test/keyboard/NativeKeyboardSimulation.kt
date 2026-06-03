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

/**
 * Explicit backend-shaped keyboard input used to prove a mapper path.
 *
 * This is intentionally a small commonMain model rather than the real platform
 * object. Backends can attach the raw fields they consumed without requiring
 * common tests to depend on DOM, Win32, AppKit, or Android classes.
 */
interface NativeKeyboardInput {
    val backend: KeyboardBackend
    val fidelity: KeyboardEventFidelity
    val description: String

    fun verifyMappedEvent(event: KeyEvent): List<String>
}

data class WebDomKeyboardEventInput(
    val type: String,
    val key: String,
    val code: String,
    val location: Int = 0,
    val repeat: Boolean = false,
    val altKey: Boolean = false,
    val ctrlKey: Boolean = false,
    val metaKey: Boolean = false,
    val shiftKey: Boolean = false,
    override val fidelity: KeyboardEventFidelity = KeyboardEventFidelity.NativeMessage,
) : NativeKeyboardInput {
    override val backend: KeyboardBackend = KeyboardBackend.Web
    override val description: String = "Web DOM KeyboardEvent(type=$type, key=$key, code=$code)"

    override fun verifyMappedEvent(event: KeyEvent): List<String> {
        val failures = mutableListOf<String>()
        fun expect(condition: Boolean, message: String) {
            if (!condition) failures += "$description: $message"
        }
        val expectedState = when (type) {
            "keydown" -> KeyState.Pressed
            "keyup" -> KeyState.Released
            else -> null
        }
        val expectedLocation = when (location) {
            0 -> KeyLocation.Standard
            1 -> KeyLocation.Left
            2 -> KeyLocation.Right
            3 -> KeyLocation.Numpad
            else -> null
        }

        expect(expectedState != null, "type must be keydown or keyup, got $type")
        expectedState?.let { expect(event.state == it, "state expected $it, got ${event.state}") }
        expect(expectedLocation != null, "location must be 0, 1, 2 or 3, got $location")
        expectedLocation?.let { expect(event.location == it, "location expected $it, got ${event.location}") }
        expect(event.native.nativeCode == NativeKeyCode.Web(code), "nativeCode expected ${NativeKeyCode.Web(code)}, got ${event.native.nativeCode}")
        expect(event.native.nativeKey == NativeLogicalKey.Web(key), "nativeKey expected ${NativeLogicalKey.Web(key)}, got ${event.native.nativeKey}")
        expect(event.repeat == repeat, "repeat expected $repeat, got ${event.repeat}")
        expect(event.modifiers.shift == shiftKey, "shift modifier expected $shiftKey, got ${event.modifiers.shift}")
        expect(event.modifiers.ctrl == ctrlKey, "ctrl modifier expected $ctrlKey, got ${event.modifiers.ctrl}")
        expect(event.modifiers.alt == altKey, "alt modifier expected $altKey, got ${event.modifiers.alt}")
        expect(event.modifiers.meta == metaKey, "meta modifier expected $metaKey, got ${event.modifiers.meta}")

        return failures
    }
}

data class Win32KeyboardMessageInput(
    val message: UInt,
    val wParam: UInt,
    val lParam: UInt,
    val virtualKey: Long,
    val scanCode: Long?,
    val extended: Boolean = false,
    val repeatCount: Int = 1,
    override val fidelity: KeyboardEventFidelity = KeyboardEventFidelity.NativeMessage,
) : NativeKeyboardInput {
    override val backend: KeyboardBackend = KeyboardBackend.Win32
    override val description: String = "Win32 keyboard message(message=$message, virtualKey=$virtualKey, scanCode=$scanCode)"

    override fun verifyMappedEvent(event: KeyEvent): List<String> {
        val rawScanCode = ((lParam shr 16) and 0xffu).toLong()
        val lParamRepeatCount = (lParam and 0xffffu).toInt()
        val lParamExtended = ((lParam shr 24) and 0x1u) == 1u
        val expectedScanCode = if (lParamExtended) 0xe000L + rawScanCode else rawScanCode
        val expectedState = when (message) {
            WM_KEYDOWN, WM_SYSKEYDOWN -> KeyState.Pressed
            WM_KEYUP, WM_SYSKEYUP -> KeyState.Released
            else -> null
        }
        val expectedCode = NativeKeyCode.Win32(scanCode = scanCode ?: 0, virtualKey = virtualKey)
        val expectedKey = NativeLogicalKey.Win32(virtualKey = virtualKey)
        val failures = mutableListOf<String>()
        fun expect(condition: Boolean, message: String) {
            if (!condition) failures += "$description: $message"
        }

        expect(expectedState != null, "message must be WM_KEYDOWN/WM_KEYUP/WM_SYSKEYDOWN/WM_SYSKEYUP, got $message")
        expectedState?.let { expect(event.state == it, "state expected $it, got ${event.state}") }
        expect(wParam.toLong() == virtualKey, "wParam expected virtualKey $virtualKey, got ${wParam.toLong()}")
        expect(scanCode == null || scanCode == expectedScanCode, "scanCode expected $expectedScanCode from lParam, got $scanCode")
        expect(lParamExtended == extended, "extended expected $lParamExtended from lParam, got $extended")
        expect(lParamRepeatCount == repeatCount, "repeatCount expected $lParamRepeatCount from lParam, got $repeatCount")
        expect(event.native.nativeCode == expectedCode, "nativeCode expected $expectedCode, got ${event.native.nativeCode}")
        expect(event.native.nativeKey == expectedKey, "nativeKey expected $expectedKey, got ${event.native.nativeKey}")
        expect(event.repeat == repeatCount > 1, "repeat expected ${repeatCount > 1}, got ${event.repeat}")

        return failures
    }

    companion object {
        private const val WM_KEYDOWN: UInt = 0x0100u
        private const val WM_KEYUP: UInt = 0x0101u
        private const val WM_SYSKEYDOWN: UInt = 0x0104u
        private const val WM_SYSKEYUP: UInt = 0x0105u
    }
}

/**
 * Attaches a backend-shaped input to the Kadre event observed after the backend
 * mapper/runtime has consumed that input.
 *
 * This class does not map native input to Kadre events. The caller must invoke
 * the platform mapper/runtime first, then pass the observed event here. That
 * keeps the harness from becoming a mock mapper.
 */
class NativeKeyboardInputAdapter<I : NativeKeyboardInput>(
    val backend: KeyboardBackend,
    val acceptedFidelities: Set<KeyboardEventFidelity>,
    val mapperName: String,
) {
    init {
        require(backend != KeyboardBackend.Common) {
            "Native keyboard input adapters must target a concrete backend"
        }
        require(acceptedFidelities.isNotEmpty()) {
            "Native keyboard input adapters require at least one accepted fidelity"
        }
        require(KeyboardEventFidelity.KadreEvent !in acceptedFidelities) {
            "Native keyboard input adapters cannot accept L4 KadreEvent"
        }
        require(mapperName.isNotBlank()) {
            "Native keyboard input adapters require a mapper/runtime name"
        }
    }

    fun evidenceFor(input: I, observedEvent: KeyEvent): KeyboardEventEvidence {
        require(input.backend == backend) {
            "Adapter '$mapperName' targets $backend but received ${input.backend} native input"
        }
        require(input.fidelity in acceptedFidelities) {
            "Adapter '$mapperName' does not accept ${input.fidelity}; expected one of $acceptedFidelities"
        }

        return KeyboardEventEvidence(
            fidelity = input.fidelity,
            event = observedEvent,
            source = mapperName,
            nativeInput = input,
        )
    }
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
                val nativeInput = evidence.nativeInput
                if (nativeInput == null) {
                    failures += "event[$index] for BackendRuntime scenario '$name' has no native input evidence"
                } else {
                    if (nativeInput.backend != backend) {
                        failures += "event[$index] for scenario '$name' has ${nativeInput.backend} native input, expected $backend"
                    }
                    if (nativeInput.fidelity != fidelity) {
                        failures += "event[$index] for scenario '$name' has ${nativeInput.fidelity} native input, expected $fidelity"
                    }
                    if (nativeInput.fidelity != evidence.fidelity) {
                        failures += "event[$index] for scenario '$name' has ${evidence.fidelity} evidence but ${nativeInput.fidelity} native input"
                    }
                    failures += nativeInput.verifyMappedEvent(evidence.event).map { "event[$index] for scenario '$name': $it" }
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
    val nativeInput: NativeKeyboardInput? = null,
)

data class KeyboardValidationResult(val failures: List<String>) {
    val passed: Boolean get() = failures.isEmpty()
}

enum class KeyboardProofStatus {
    Done,
    Partial,
}

enum class KeyboardProofCoverageKind {
    ApiTable,
    CommonContract,
    BackendRuntime,
}

data class KeyboardProofGatePolicy(
    val acceptedDoneFidelityByBackend: Map<KeyboardBackend, Set<KeyboardEventFidelity>> = defaultAcceptedDoneFidelityByBackend,
) {
    fun acceptsDoneProof(backend: KeyboardBackend, fidelity: KeyboardEventFidelity): Boolean =
        fidelity in acceptedDoneFidelityByBackend[backend].orEmpty()

    fun acceptedDoneFor(backend: KeyboardBackend): Set<KeyboardEventFidelity> =
        acceptedDoneFidelityByBackend[backend].orEmpty()

    companion object {
        val defaultAcceptedDoneFidelityByBackend: Map<KeyboardBackend, Set<KeyboardEventFidelity>> = mapOf(
            KeyboardBackend.Web to setOf(KeyboardEventFidelity.RealOsEvent),
            KeyboardBackend.Android to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
            KeyboardBackend.Win32 to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage),
            KeyboardBackend.X11 to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
            KeyboardBackend.Wayland to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
            KeyboardBackend.AppKit to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
            KeyboardBackend.UIKit to setOf(KeyboardEventFidelity.RealOsEvent, KeyboardEventFidelity.NativeMessage, KeyboardEventFidelity.NativeFixture),
        )
    }
}

data class KeyboardProofReport(
    val target: String,
    val status: KeyboardProofStatus,
    val entries: List<KeyboardProofEntry>,
    val gaps: List<String> = emptyList(),
    val pullRequest: String? = null,
    val commit: String? = null,
) {
    fun validate(policy: KeyboardProofGatePolicy = KeyboardProofGatePolicy()): KeyboardProofGateResult {
        val failures = mutableListOf<String>()
        if (target.isBlank()) {
            failures += "Keyboard proof report requires a target"
        }
        if (status == KeyboardProofStatus.Partial && gaps.isEmpty()) {
            failures += "Partial keyboard proof report '$target' must list remaining gaps"
        }
        if (status == KeyboardProofStatus.Done && gaps.isNotEmpty()) {
            failures += "Done keyboard proof report '$target' cannot list remaining gaps"
        }
        entries.forEachIndexed { index, entry ->
            failures += entry.validate(index, status, policy)
        }
        if (status == KeyboardProofStatus.Done && entries.none { it.isAcceptedBackendRuntimeProof(policy) }) {
            failures += "Done keyboard proof report '$target' requires at least one accepted BackendRuntime proof"
        }

        return KeyboardProofGateResult(failures)
    }

    fun toJsonString(): String = buildString {
        append("{")
        appendJsonField("target", target)
        append(",")
        appendJsonField("status", status.name)
        append(",\"entries\":[")
        entries.forEachIndexed { index, entry ->
            if (index > 0) append(",")
            append(entry.toJsonString())
        }
        append("],")
        appendJsonArrayField("gaps", gaps)
        pullRequest?.let {
            append(",")
            appendJsonField("pullRequest", it)
        }
        commit?.let {
            append(",")
            appendJsonField("commit", it)
        }
        append("}")
    }
}

data class KeyboardProofEntry(
    val scenario: String,
    val backend: KeyboardBackend,
    val scope: KeyboardValidationScope,
    val fidelity: KeyboardEventFidelity,
    val coverageKind: KeyboardProofCoverageKind,
    val nativeInput: String? = null,
    val mapper: String? = null,
    val observedEventCount: Int = 0,
    val gaps: List<String> = emptyList(),
) {
    fun validate(
        index: Int,
        reportStatus: KeyboardProofStatus,
        policy: KeyboardProofGatePolicy = KeyboardProofGatePolicy(),
    ): List<String> {
        val failures = mutableListOf<String>()
        val prefix = "proof[$index] '$scenario'"
        if (scenario.isBlank()) {
            failures += "proof[$index] requires a scenario"
        }
        if (reportStatus == KeyboardProofStatus.Done && gaps.isNotEmpty()) {
            failures += "$prefix cannot list remaining gaps in a Done report"
        }
        if (backend == KeyboardBackend.Common && scope == KeyboardValidationScope.BackendRuntime) {
            failures += "$prefix targets Common but BackendRuntime requires a concrete backend"
        }
        if (scope == KeyboardValidationScope.BackendRuntime || coverageKind == KeyboardProofCoverageKind.BackendRuntime) {
            if (scope != KeyboardValidationScope.BackendRuntime) {
                failures += "$prefix has BackendRuntime coverage but scope is $scope"
            }
            if (coverageKind != KeyboardProofCoverageKind.BackendRuntime) {
                failures += "$prefix has BackendRuntime scope but coverage is $coverageKind"
            }
            if (fidelity == KeyboardEventFidelity.KadreEvent) {
                failures += "$prefix uses L4 KadreEvent, not a backend runtime proof"
            }
            if (reportStatus == KeyboardProofStatus.Done && !policy.acceptsDoneProof(backend, fidelity)) {
                failures += "$prefix uses $fidelity for $backend, expected one of ${policy.acceptedDoneFor(backend)}"
            }
            if (nativeInput.isNullOrBlank()) {
                failures += "$prefix must record a native input or fixture"
            }
            if (mapper.isNullOrBlank()) {
                failures += "$prefix must record the mapper/runtime traversed"
            }
            if (observedEventCount <= 0) {
                failures += "$prefix must record at least one observed Kadre event"
            }
        }

        return failures
    }

    fun isAcceptedBackendRuntimeProof(policy: KeyboardProofGatePolicy): Boolean =
        scope == KeyboardValidationScope.BackendRuntime &&
            coverageKind == KeyboardProofCoverageKind.BackendRuntime &&
            backend != KeyboardBackend.Common &&
            fidelity != KeyboardEventFidelity.KadreEvent &&
            policy.acceptsDoneProof(backend, fidelity) &&
            !nativeInput.isNullOrBlank() &&
            !mapper.isNullOrBlank() &&
            observedEventCount > 0 &&
            gaps.isEmpty()

    fun toJsonString(): String = buildString {
        append("{")
        appendJsonField("scenario", scenario)
        append(",")
        appendJsonField("backend", backend.name)
        append(",")
        appendJsonField("scope", scope.name)
        append(",")
        appendJsonField("fidelity", fidelity.name)
        append(",")
        appendJsonField("coverageKind", coverageKind.name)
        nativeInput?.let {
            append(",")
            appendJsonField("nativeInput", it)
        }
        mapper?.let {
            append(",")
            appendJsonField("mapper", it)
        }
        append(",\"observedEventCount\":")
        append(observedEventCount)
        append(",")
        appendJsonArrayField("gaps", gaps)
        append("}")
    }
}

data class KeyboardProofGateResult(val failures: List<String>) {
    val passed: Boolean get() = failures.isEmpty()
}

fun KeyboardScenario.proofEntry(
    evidence: List<KeyboardEventEvidence>,
    coverageKind: KeyboardProofCoverageKind = when (scope) {
        KeyboardValidationScope.BackendRuntime -> KeyboardProofCoverageKind.BackendRuntime
        KeyboardValidationScope.CommonContract -> KeyboardProofCoverageKind.CommonContract
    },
    gaps: List<String> = emptyList(),
): KeyboardProofEntry = KeyboardProofEntry(
    scenario = name,
    backend = backend,
    scope = scope,
    fidelity = fidelity,
    coverageKind = coverageKind,
    nativeInput = evidence.mapNotNull { it.nativeInput?.description }.distinct().joinToString().takeIf { it.isNotBlank() },
    mapper = evidence.mapNotNull { it.source }.distinct().joinToString().takeIf { it.isNotBlank() },
    observedEventCount = evidence.size,
    gaps = gaps,
)

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
    val keyWithoutModifiers: String? = null,
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

private fun StringBuilder.appendJsonField(name: String, value: String) {
    append("\"")
    append(name)
    append("\":\"")
    append(value.jsonEscaped())
    append("\"")
}

private fun StringBuilder.appendJsonArrayField(name: String, values: List<String>) {
    append("\"")
    append(name)
    append("\":[")
    values.forEachIndexed { index, value ->
        if (index > 0) append(",")
        append("\"")
        append(value.jsonEscaped())
        append("\"")
    }
    append("]")
}

private fun String.jsonEscaped(): String = buildString {
    for (char in this@jsonEscaped) {
        when (char) {
            '\\' -> append("\\\\")
            '"' -> append("\\\"")
            '\n' -> append("\\n")
            '\r' -> append("\\r")
            '\t' -> append("\\t")
            else -> append(char)
        }
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
    var keyWithoutModifiers: String? = null
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
