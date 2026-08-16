package org.graphiks.kextract.integration

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.graphiks.kextract.callbacks.CallbackBindingsConfig
import org.graphiks.kextract.callbacks.DirectFunctionBinding

class KmpJvmDirectCallbackTransactionTest : FreeSpec({
    "throwing JVM carrier conversion happens before callback preparation or symbol resolution" {
        val bindings = CallbackBindingsConfig().also { config ->
            config.directFunctionBindings = listOf(
                DirectFunctionBinding().also { binding ->
                    binding.function = "function:sample_set_callback"
                    binding.callbackParameter = "callback"
                    binding.callbackType = "typedef:SampleCallback"
                    binding.routingUserdataParameter = "userdata"
                },
            )
        }
        val generated = generateKmpSources(
            header =
                """
                typedef struct SamplePayload { int value; } SamplePayload;
                typedef void (*SampleCallback)(void *userdata);
                void sample_set_callback(
                    SamplePayload* payload,
                    SampleCallback callback,
                    void *userdata
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

                import org.graphiks.kffi.CallbackPolicy
                import org.graphiks.kffi.CallbackRuntime
                import org.graphiks.kffi.NativeAddress
                import sample.bindings.SampleCallback
                import sample.bindings.SamplePayload
                import sample.bindings.sample_set_callback

                class SentinelConversionFailure : RuntimeException()

                fun runProbe(): IntArray {
                    CallbackRuntime.prepareCount = 0
                    CallbackRuntime.symbolResolutionCount = 0
                    val payload = object : SamplePayload {
                        override var value: Int = 7
                        override val handler: NativeAddress
                            get() = throw SentinelConversionFailure()
                    }
                    var caughtSentinel = 0
                    try {
                        sample_set_callback(
                            payload = payload,
                            policy = CallbackPolicy.ONCE,
                            callback = SampleCallback { },
                        )
                    } catch (_: SentinelConversionFailure) {
                        caughtSentinel = 1
                    }
                    return intArrayOf(
                        caughtSentinel,
                        CallbackRuntime.prepareCount,
                        CallbackRuntime.symbolResolutionCount,
                    )
                }
                """.trimIndent(),
            facadeClassName = "ProbeKt",
            methodName = "runProbe",
        ) as IntArray

        result.toList() shouldBe listOf(
            1, // SentinelConversionFailure was the observed failure.
            0, // CallbackRuntime.prepare was never entered.
            0, // findOrThrow was never entered.
        )
    }
})
