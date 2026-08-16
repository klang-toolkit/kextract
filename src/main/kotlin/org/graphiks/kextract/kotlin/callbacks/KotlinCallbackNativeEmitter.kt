package org.graphiks.kextract.kotlin.callbacks

import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_OPAQUE_POINTER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_OPAQUE_POINTER_VAR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_VALUE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_EXCEPTION_HANDLER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_POLICY
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_REGISTRATION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME_API
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.OPT_IN
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.POINTED
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.PREPARED_CALLBACK_REGISTRATION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.REINTERPRET
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.STATIC_C_FUNCTION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.UNSAFE_CALLBACK_REARM_API
import org.graphiks.kextract.kotlin.abi.KotlinKmpCAbiType
import org.graphiks.kextract.kotlin.builders.SourceBuilder
import org.graphiks.kextract.pipeline.isEnum

internal class KotlinCallbackNativeEmitter(
    private val mapType: (Type) -> String,
    private val namePlan: KotlinKmpNamePlan,
) {
    fun emit(builder: SourceBuilder, callbacks: List<KotlinCallbackModel>) {
        callbacks.forEach { callback ->
            emitTrampoline(builder, callback)
            emitRegistrationOperation(builder, callback, "register", internal = false)
            emitRegistrationOperation(builder, callback, "prepare", internal = true)
            if (!callback.hasRoutingUserdata) {
                builder.appendLine("@${namePlan.runtime(UNSAFE_CALLBACK_REARM_API)}")
                emitRegistrationOperation(
                    builder,
                    callback,
                    "rearmAfterNativeQuiescence",
                    internal = false,
                )
            }
        }
    }

    private fun emitTrampoline(builder: SourceBuilder, callback: KotlinCallbackModel) {
        val rawParameters = callback.rawParameters()
        val functionTypes = rawParameters.map { planNativeCarrier(it.cAbiType) } + "Unit"
        val parameterNames = rawParameters.joinToString(", ") { it.name }
        val lambdaStart = if (parameterNames.isEmpty()) "{" else "{ $parameterNames ->"

        builder.appendLine("@${namePlan.runtime(OPT_IN)}(${namePlan.runtime(CALLBACK_RUNTIME_API)}::class)")
        builder.appendLine(
            "private val ${callback.trampolineName} = " +
                "${namePlan.runtime(STATIC_C_FUNCTION)}<${functionTypes.joinToString(", ")}> $lambdaStart",
        )
        builder.indent()
        builder.appendLine("try {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(CALLBACK_RUNTIME)}.dispatchSafely(")
        builder.indent()
        builder.appendLine("type = ${callback.runtimeTypeName},")
        val routingUserdata = callback.routingUserdataParameter
            ?.name
            ?.let { "$it?.let { ${namePlan.runtime(NATIVE_ADDRESS)}.fromPointer(it) }" }
            ?: "null"
        builder.appendLine("userdata = $routingUserdata,")
        builder.unindent()
        builder.appendLine(") { callback ->")
        builder.indent()
        emitInvocation(builder, callback)
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("} catch (failure: Throwable) {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(CALLBACK_RUNTIME)}.reportUnroutedFailure(failure)")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitInvocation(builder: SourceBuilder, callback: KotlinCallbackModel) {
        if (callback.parameters.size <= 1) {
            val arguments = callback.parameters.joinToString(", ") { adaptNativeArgument(it) }
            builder.appendLine("callback.invoke($arguments)")
            return
        }

        builder.appendLine("callback.invoke(")
        builder.indent()
        callback.parameters.forEach { parameter ->
            builder.appendLine("${adaptNativeArgument(parameter)},")
        }
        builder.unindent()
        builder.appendLine(")")
    }

    private fun emitRegistrationOperation(
        builder: SourceBuilder,
        callback: KotlinCallbackModel,
        operation: String,
        internal: Boolean,
    ) {
        builder.appendLine("@${namePlan.runtime(OPT_IN)}(${namePlan.runtime(CALLBACK_RUNTIME_API)}::class)")
        val visibility = if (internal) "internal " else ""
        builder.appendLine("${visibility}actual fun ${callback.typeName}.Companion.$operation(")
        builder.indent()
        builder.appendLine("policy: ${namePlan.runtime(CALLBACK_POLICY)},")
        builder.appendLine("onError: ${namePlan.runtime(CALLBACK_EXCEPTION_HANDLER)},")
        builder.appendLine("callback: ${callback.typeName},")
        builder.unindent()
        val registrationType = if (internal) namePlan.runtime(PREPARED_CALLBACK_REGISTRATION) else namePlan.runtime(CALLBACK_REGISTRATION)
        builder.appendLine("): $registrationType<${callback.typeName}> = ${namePlan.runtime(CALLBACK_RUNTIME)}.$operation(")
        builder.indent()
        builder.appendLine("type = ${callback.runtimeTypeName},")
        builder.appendLine("trampoline = ${namePlan.runtime(NATIVE_ADDRESS)}.fromPointer(${callback.trampolineName}),")
        builder.appendLine("policy = policy,")
        builder.appendLine("onError = onError,")
        builder.appendLine("callback = callback,")
        builder.unindent()
        builder.appendLine(")")
        builder.appendLine()
    }

    private fun adaptNativeArgument(parameter: KotlinCallbackParameter): String {
        val name = parameter.name
        val type = parameter.type
        val cAbiType = parameter.cAbiType
        val mapped = mapType(type)
        return when {
            isEnum(type) && isOptionsStyle(mapped) -> "$mapped($name.toLong())"
            isEnum(type) -> enumApplicationValue(name, mapped, cAbiType)
            cAbiType is KotlinKmpCAbiType.StructValue -> "$mapped.ByValue($name)"
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(NATIVE_ADDRESS)}?" ->
                "$name?.let { ${namePlan.runtime(NATIVE_ADDRESS)}.fromPointer(it) }"
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(C_STRING)}?" ->
                "$name?.let { ${namePlan.runtime(NATIVE_ADDRESS)}.fromPointer(it) }?.let(::${namePlan.runtime(C_STRING)})"
            cAbiType is KotlinKmpCAbiType.Address && mapped.endsWith("?") -> {
                val nonNullable = mapped.removeSuffix("?")
                if (cAbiType.pointerDepth > 1) {
                    "$name?.${namePlan.runtime(REINTERPRET)}<${namePlan.runtime(C_OPAQUE_POINTER_VAR)}>()?.${namePlan.runtime(POINTED)}?.value" +
                        "?.let { ${namePlan.runtime(NATIVE_ADDRESS)}.fromPointer(it) }?.let { $nonNullable(it) }"
                } else {
                    "$name?.let { ${namePlan.runtime(NATIVE_ADDRESS)}.fromPointer(it) }?.let { $nonNullable(it) }"
                }
            }
            else -> name
        }
    }

    private fun enumApplicationValue(
        name: String,
        mapped: String,
        cAbiType: KotlinKmpCAbiType,
    ): String {
        val scalar = cAbiType as? KotlinKmpCAbiType.Scalar
            ?: error("Enum callback parameter must have a scalar C ABI type")
        return when (scalar.nativeCarrier) {
            "ULong" -> "$name.toULong() as $mapped"
            "UInt" -> "$name.toUInt() as $mapped"
            "UShort" -> "$name.toUShort() as $mapped"
            "UByte" -> "$name.toUByte() as $mapped"
            else -> name
        }
    }

    private fun isEnum(type: Type): Boolean = when {
        type is Type.Declared -> type.isEnum()
        type is Type.Delegated -> isEnum(type.type())
        else -> false
    }

    private fun isOptionsStyle(typeName: String): Boolean =
        typeName.endsWith("Options") || typeName.endsWith("Flags") || typeName.endsWith("Mask")

    private fun KotlinCallbackModel.rawParameters(): List<KotlinCallbackParameter> =
        (parameters + listOfNotNull(routingUserdataParameter)).sortedBy(KotlinCallbackParameter::index)

    private fun planNativeCarrier(cAbiType: KotlinKmpCAbiType): String = when (cAbiType) {
        is KotlinKmpCAbiType.Scalar -> cAbiType.nativeCarrier
        is KotlinKmpCAbiType.Address -> "${namePlan.runtime(C_OPAQUE_POINTER)}?"
        is KotlinKmpCAbiType.StructValue ->
            "${namePlan.runtime(C_VALUE)}<${namePlan.nativeCinteropClassifier(cAbiType.declaration)}>"
    }
}
