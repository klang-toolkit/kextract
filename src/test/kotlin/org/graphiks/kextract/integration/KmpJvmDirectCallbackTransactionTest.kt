package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.graphiks.kextract.callbacks.CallbackBindingsConfig
import org.graphiks.kextract.callbacks.DirectFunctionBinding

class KmpJvmDirectCallbackTransactionTest : FreeSpec({
    "JVM prepared-call defers symbol resolution and surfaces carrier failures at invocation" {
        val bindings = CallbackBindingsConfig().also { config ->
            config.directFunctionBindings = listOf(
                DirectFunctionBinding().also { binding ->
                    binding.function = "function:sample_set_callback"
                    binding.callbackParameter = "callback"
                    binding.callbackType = "typedef:SampleCallback"
                },
            )
        }
        val generated = generateKmpSources(
            header =
                """
                typedef struct SamplePayload { int value; } SamplePayload;
                typedef void (*SampleCallback)(void);
                void sample_set_callback(
                    SamplePayload* payload,
                    SampleCallback callback
                );
                """.trimIndent(),
            callbackBindings = bindings,
        )
        val result = compileAndInvokeGeneratedKmpJvm(
            generated = generated,
            probePackage = "sample.probe",
            probeSource =
                """
                package sample.probe

                import org.graphiks.kffi.CallbackRuntime
                import org.graphiks.kffi.NativeAddress
                import org.graphiks.kffi.TestNativeSymbols
                import sample.bindings.SamplePayload
                import sample.bindings.sample_set_callbackCallbackBindingPreflight
                import java.lang.foreign.MemorySegment

                class SentinelConversionFailure : RuntimeException()

                fun runProbe(): IntArray {
                    CallbackRuntime.symbolResolutionCount = 0
                    // Symbole factice : le downcall n'est jamais exécuté — la
                    // conversion du payload lève avant l'appel moteur.
                    TestNativeSymbols.register("sample_set_callback", MemorySegment.ofAddress(0x1234L))
                    val payload = object : SamplePayload {
                        override var value: Int = 7
                        override val handler: NativeAddress
                            get() = throw SentinelConversionFailure()
                    }
                    // Le preflight ne convertit rien : la résolution du symbole (lazy
                    // _ADDR) et les conversions d'arguments vivent dans le lambda.
                    val preparedCall = sample_set_callbackCallbackBindingPreflight(payload = payload)
                    val resolvedAfterPreflight = CallbackRuntime.symbolResolutionCount
                    var caughtSentinel = 0
                    try {
                        preparedCall(NativeAddress(0x1000L))
                    } catch (_: SentinelConversionFailure) {
                        caughtSentinel = 1
                    }
                    return intArrayOf(
                        caughtSentinel,
                        resolvedAfterPreflight,
                        CallbackRuntime.symbolResolutionCount,
                    )
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "runProbe",
        ) as IntArray

        result.toList() shouldBe listOf(
            1, // SentinelConversionFailure surfaced when the prepared call was invoked.
            0, // findOrThrow was never entered by the preflight itself.
            1, // The downcall resolved the symbol before the throwing conversion.
        )
    }
})
