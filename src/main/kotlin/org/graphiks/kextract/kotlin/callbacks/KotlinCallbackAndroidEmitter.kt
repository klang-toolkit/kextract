package org.graphiks.kextract.kotlin.callbacks

import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_EXCEPTION_HANDLER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_POLICY
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_REGISTRATION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME_API
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.OPT_IN
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.PREPARED_CALLBACK_REGISTRATION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.UNSAFE_CALLBACK_REARM_API
import org.graphiks.kextract.kotlin.abi.KotlinKmpCAbiType
import org.graphiks.kextract.kotlin.builders.SourceBuilder
import org.graphiks.kextract.pipeline.isEnum

internal class KotlinCallbackAndroidEmitter(
    private val mapType: (Type) -> String,
    private val mapJnaType: (Type) -> String,
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
        val jnaType = "${callback.typeName}Jna"
        val rawParameters = callback.rawParameters()
        builder.appendLine("private fun interface $jnaType : com.sun.jna.Callback {")
        builder.indent()
        builder.appendLine("fun invoke(")
        builder.indent()
        rawParameters.forEach { parameter ->
            builder.appendLine("${parameter.name}: ${mapJnaType(parameter.type)},")
        }
        builder.unindent()
        builder.appendLine(")")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()

        val parameterNames = rawParameters.joinToString(", ", transform = KotlinCallbackParameter::name)
        val lambdaStart = if (parameterNames.isEmpty()) {
            "$jnaType {"
        } else {
            "$jnaType { $parameterNames ->"
        }
        builder.appendLine("@${namePlan.runtime(OPT_IN)}(${namePlan.runtime(CALLBACK_RUNTIME_API)}::class)")
        builder.appendLine("private object ${callback.trampolineName} {")
        builder.indent()
        builder.appendLine("private val callback: $jnaType = $lambdaStart")
        builder.indent()
        builder.appendLine("try {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(CALLBACK_RUNTIME)}.dispatchSafely(")
        builder.indent()
        builder.appendLine("type = ${callback.runtimeTypeName},")
        builder.appendLine("userdata = ${routingUserdataConversion(callback)},")
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
        builder.appendLine("val address: ${namePlan.runtime(NATIVE_ADDRESS)} by lazy {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(NATIVE_ADDRESS)}(com.sun.jna.Pointer.nativeValue(com.sun.jna.CallbackReference.getFunctionPointer(callback)))")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitInvocation(builder: SourceBuilder, callback: KotlinCallbackModel) {
        if (callback.parameters.size <= 1) {
            val arguments = callback.parameters.joinToString(", ") { adaptJnaArgument(it) }
            builder.appendLine("callback.invoke($arguments)")
            return
        }

        builder.appendLine("callback.invoke(")
        builder.indent()
        callback.parameters.forEach { parameter ->
            builder.appendLine("${adaptJnaArgument(parameter)},")
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
        builder.appendLine("trampoline = ${callback.trampolineName}.address,")
        builder.appendLine("policy = policy,")
        builder.appendLine("onError = onError,")
        builder.appendLine("callback = callback,")
        builder.unindent()
        builder.appendLine(")")
        builder.appendLine()
    }

    private fun adaptJnaArgument(parameter: KotlinCallbackParameter): String {
        val name = parameter.name
        val mapped = mapType(parameter.type)
        val cAbiType = parameter.cAbiType
        return when {
            isEnum(parameter.type) && isOptionsStyle(mapped) ->
                "$mapped(${optionsRawValue(name, cAbiType)})"
            isEnum(parameter.type) -> enumApplicationValue(name, mapped, cAbiType)
            mapped == "UInt" -> "$name.toUInt()"
            mapped == "ULong" -> "$name.toULong()"
            mapped == "UShort" -> "$name.toUShort()"
            mapped == "UByte" -> "$name.toUByte()"
            mapped == "Boolean" && mapJnaType(parameter.type) == "Int" -> "$name != 0"
            cAbiType is KotlinKmpCAbiType.StructValue -> "$mapped.ByValue($name)"
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(NATIVE_ADDRESS)}?" ->
                jnaAddressConversion(name)
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(C_STRING)}?" ->
                "$name?.let(::${namePlan.runtime(C_STRING)})"
            cAbiType is KotlinKmpCAbiType.Address && mapped.endsWith("?") -> {
                val nonNullable = mapped.removeSuffix("?")
                if (cAbiType.pointerDepth > 1) {
                    "$name?.getPointer(0L)?.let { $nonNullable(it) }"
                } else {
                    "$name?.let { $nonNullable(it) }"
                }
            }
            else -> name
        }
    }

    /** Converts a JNA `Pointer` upcall argument to the runtime [NATIVE_ADDRESS] wrapper. */
    private fun jnaAddressConversion(name: String): String =
        "$name?.takeIf { com.sun.jna.Pointer.nativeValue(it) != 0L }" +
            "?.let { ${namePlan.runtime(NATIVE_ADDRESS)}(com.sun.jna.Pointer.nativeValue(it)) }"

    private fun routingUserdataConversion(callback: KotlinCallbackModel): String {
        val name = callback.routingUserdataParameter?.name ?: return "null"
        return jnaAddressConversion(name)
    }

    private fun optionsRawValue(name: String, cAbiType: KotlinKmpCAbiType): String {
        val scalar = cAbiType as? KotlinKmpCAbiType.Scalar
            ?: error("Options callback parameter must have a scalar C ABI type")
        return scalar.jvmCarrierToOptionsRaw(name)
    }

    private fun enumApplicationValue(
        name: String,
        mapped: String,
        cAbiType: KotlinKmpCAbiType,
    ): String {
        val scalar = cAbiType as? KotlinKmpCAbiType.Scalar
            ?: error("Enum callback parameter must have a scalar C ABI type")
        return when (scalar.kotlinType) {
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
}
