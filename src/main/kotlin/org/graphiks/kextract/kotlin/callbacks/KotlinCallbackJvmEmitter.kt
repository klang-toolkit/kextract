package org.graphiks.kextract.kotlin.callbacks

import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.ARENA
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_EXCEPTION_HANDLER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_POLICY
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_REGISTRATION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME_API
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.FUNCTION_DESCRIPTOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JVM_DOWNCALL_ENGINE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JVM_STATIC
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JVM_UPCALL_ENGINE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.LINKER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_SEGMENT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.METHOD_HANDLE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.METHOD_HANDLES
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.OPT_IN
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.PREPARED_CALLBACK_REGISTRATION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.UNSAFE_CALLBACK_REARM_API
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.VALUE_LAYOUT
import org.graphiks.kextract.kotlin.abi.KotlinKmpCAbiType
import org.graphiks.kextract.kotlin.builders.SourceBuilder
import org.graphiks.kextract.pipeline.isEnum

internal class KotlinCallbackJvmEmitter(
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
        if (callback.isEngineUpcallFit()) {
            emitEngineUpcallTrampoline(builder, callback)
        } else {
            emitFfmTrampoline(builder, callback)
        }
    }

    /**
     * M4.2's [JVM_UPCALL_ENGINE] closure encodes every raw parameter with a single
     * dispatchSig letter (I/J/F/D/Z) and resolves the static dispatcher through
     * `privateLookupIn` — the generated trampoline references the engine only,
     * never Linker/MethodHandles/FunctionDescriptor. The dispatcher reads raw
     * JVM carriers (Long for pointers, Int/Long/Float/Double/Boolean scalars)
     * and routes via CallbackRuntime.dispatchSafely, userdata in its real C
     * position (last parameter for wgpu).
     */
    private fun KotlinCallbackModel.isEngineUpcallFit(): Boolean =
        rawParameters().all { parameter ->
            when (val abi = parameter.cAbiType) {
                is KotlinKmpCAbiType.Address -> abi.pointerDepth == 1
                is KotlinKmpCAbiType.Scalar -> abi.jvmCarrier in ENGINE_CARRIERS
                is KotlinKmpCAbiType.StructValue -> false
            }
        }

    private fun emitEngineUpcallTrampoline(builder: SourceBuilder, callback: KotlinCallbackModel) {
        builder.appendLine("@${namePlan.runtime(OPT_IN)}(${namePlan.runtime(CALLBACK_RUNTIME_API)}::class)")
        builder.appendLine("private object ${callback.trampolineName} {")
        builder.indent()
        builder.appendLine("val address: ${namePlan.runtime(NATIVE_ADDRESS)} by lazy {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(JVM_UPCALL_ENGINE)}.allocateTrampoline(")
        builder.indent()
        builder.appendLine("dispatcherClass = ${callback.trampolineName}::class.java,")
        builder.appendLine("dispatchMethod = \"dispatch\",")
        builder.appendLine("dispatchSig = \"${dispatchSignature(callback)}\",")
        builder.unindent()
        builder.appendLine(")")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
        builder.appendLine("@${namePlan.runtime(JVM_STATIC)}")
        builder.appendLine("fun dispatch(")
        builder.indent()
        callback.rawParameters().forEach { parameter ->
            builder.appendLine("${parameter.name}: ${engineCarrier(parameter.cAbiType)},")
        }
        builder.unindent()
        builder.appendLine(") {")
        builder.indent()
        builder.appendLine("try {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(CALLBACK_RUNTIME)}.dispatchSafely(")
        builder.indent()
        builder.appendLine("type = ${callback.runtimeTypeName},")
        builder.appendLine("userdata = ${routingUserdataExpression(callback)},")
        builder.unindent()
        builder.appendLine(") { callback ->")
        builder.indent()
        emitInvocation(builder, callback, ::adaptEngineArgument)
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
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    /** "(IIJ)V" — raw parameters in C order, routing userdata included. */
    private fun dispatchSignature(callback: KotlinCallbackModel): String {
        val parameters = callback.rawParameters().joinToString("") { parameter ->
            when (val abi = parameter.cAbiType) {
                is KotlinKmpCAbiType.Address -> "J"
                is KotlinKmpCAbiType.Scalar -> when (abi.jvmCarrier) {
                    "Int" -> "I"
                    "Long" -> "J"
                    "Float" -> "F"
                    "Double" -> "D"
                    "Boolean" -> "Z"
                    else -> error("Engine-unfit scalar carrier ${abi.jvmCarrier} for ${parameter.name}")
                }
                is KotlinKmpCAbiType.StructValue ->
                    error("Engine-unfit struct-by-value parameter ${parameter.name}")
            }
        }
        return "($parameters)V"
    }

    private fun engineCarrier(cAbiType: KotlinKmpCAbiType): String = when (cAbiType) {
        is KotlinKmpCAbiType.Address -> "Long"
        is KotlinKmpCAbiType.Scalar -> cAbiType.jvmCarrier
        is KotlinKmpCAbiType.StructValue -> error("Engine-unfit struct-by-value parameter")
    }

    private fun routingUserdataExpression(callback: KotlinCallbackModel): String =
        callback.routingUserdataParameter
            ?.name
            ?.let { "$it.takeIf { it != 0L }?.let(::${namePlan.runtime(NATIVE_ADDRESS)})" }
            ?: "null"

    /**
     * FFM-based trampoline kept for callback shapes [JVM_UPCALL_ENGINE] cannot
     * express (struct-by-value parameters, narrow I8/I16/CHAR16 scalars,
     * multi-indirection pointers). Debt documented in the M4 plan: upcalls
     * struct-by-value non supportés, handover P3.
     */
    private fun emitFfmTrampoline(builder: SourceBuilder, callback: KotlinCallbackModel) {
        val rawParameters = callback.rawParameters()
        val functionDescriptor = namePlan.runtime(FUNCTION_DESCRIPTOR)
        val descriptor = "$functionDescriptor.ofVoid(" +
            rawParameters.joinToString(", ") { parameter ->
                val cAbiType = parameter.cAbiType
                if (cAbiType is KotlinKmpCAbiType.StructValue) {
                    "${namePlan.runtime(JVM_DOWNCALL_ENGINE)}.structLayout(" +
                        "\"${namePlan.declaration(cAbiType.declaration)}\")"
                } else {
                    planJvmLayout(cAbiType.jvmLayout)
                }
            } +
            ")"

        builder.appendLine("@${namePlan.runtime(OPT_IN)}(${namePlan.runtime(CALLBACK_RUNTIME_API)}::class)")
        builder.appendLine("private object ${callback.trampolineName} {")
        builder.indent()
        builder.appendLine("private val descriptor: $functionDescriptor = $descriptor")
        builder.appendLine("private val methodHandle: ${namePlan.runtime(METHOD_HANDLE)} by lazy {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(METHOD_HANDLES)}.lookup().findStatic(")
        builder.indent()
        builder.appendLine("${callback.trampolineName}::class.java,")
        builder.appendLine("\"invoke\",")
        builder.appendLine("descriptor.toMethodType(),")
        builder.unindent()
        builder.appendLine(")")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("val address: ${namePlan.runtime(NATIVE_ADDRESS)} by lazy {")
        builder.indent()
        builder.appendLine(
            "${namePlan.runtime(NATIVE_ADDRESS)}(" +
                "${namePlan.runtime(LINKER)}.nativeLinker().upcallStub(methodHandle, descriptor, ${namePlan.runtime(ARENA)}.global()).address())",
        )
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
        builder.appendLine("@${namePlan.runtime(JVM_STATIC)}")
        builder.appendLine("private fun invoke(")
        builder.indent()
        rawParameters.forEach { parameter ->
            builder.appendLine("${parameter.name}: ${planJvmCarrier(parameter.cAbiType.jvmCarrier)},")
        }
        builder.unindent()
        builder.appendLine(") {")
        builder.indent()
        builder.appendLine("try {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(CALLBACK_RUNTIME)}.dispatchSafely(")
        builder.indent()
        builder.appendLine("type = ${callback.runtimeTypeName},")
        val routingUserdata = callback.routingUserdataParameter
            ?.name
            ?.let {
                "$it.takeIf { it != ${namePlan.runtime(MEMORY_SEGMENT)}.NULL }" +
                    "?.let { ${namePlan.runtime(NATIVE_ADDRESS)}(it.address()) }"
            }
            ?: "null"
        builder.appendLine("userdata = $routingUserdata,")
        builder.unindent()
        builder.appendLine(") { callback ->")
        builder.indent()
        emitInvocation(builder, callback, ::adaptJvmArgument)
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
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitInvocation(
        builder: SourceBuilder,
        callback: KotlinCallbackModel,
        adapt: (KotlinCallbackParameter) -> String,
    ) {
        if (callback.parameters.size <= 1) {
            val arguments = callback.parameters.joinToString(", ") { adapt(it) }
            builder.appendLine("callback.invoke($arguments)")
            return
        }

        builder.appendLine("callback.invoke(")
        builder.indent()
        callback.parameters.forEach { parameter ->
            builder.appendLine("${adapt(parameter)},")
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

    /**
     * Converts the engine's raw JVM carriers (Long for pointers, Int/Long/
     * Float/Double/Boolean scalars) to the Kotlin-facing invoke types. A 0L
     * raw pointer is the null sentinel. Engine-fit shapes never carry
     * struct-by-value, narrow scalars, or multi-indirection pointers.
     */
    private fun adaptEngineArgument(parameter: KotlinCallbackParameter): String {
        val name = parameter.name
        val type = parameter.type
        val cAbiType = parameter.cAbiType
        val mapped = mapType(type)
        return when {
            isEnum(type) && isOptionsStyle(mapped) ->
                "$mapped(${optionsRawValue(name, cAbiType)})"
            isEnum(type) -> enumApplicationValue(name, mapped, cAbiType)
            mapped == "UInt" -> "$name.toUInt()"
            mapped == "ULong" -> "$name.toULong()"
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(NATIVE_ADDRESS)}?" ->
                "$name.takeIf { it != 0L }?.let(::${namePlan.runtime(NATIVE_ADDRESS)})"
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(C_STRING)}?" ->
                "$name.takeIf { it != 0L }?.let(::${namePlan.runtime(NATIVE_ADDRESS)})?.let(::${namePlan.runtime(C_STRING)})"
            cAbiType is KotlinKmpCAbiType.Address && mapped.endsWith("?") -> {
                val nonNullable = mapped.removeSuffix("?")
                "$name.takeIf { it != 0L }?.let(::${namePlan.runtime(NATIVE_ADDRESS)})?.let { $nonNullable(it) }"
            }
            else -> name
        }
    }

    private fun adaptJvmArgument(parameter: KotlinCallbackParameter): String {
        val name = parameter.name
        val type = parameter.type
        val cAbiType = parameter.cAbiType
        val mapped = mapType(type)
        return when {
            isEnum(type) && isOptionsStyle(mapped) ->
                "$mapped(${optionsRawValue(name, cAbiType)})"
            isEnum(type) -> enumApplicationValue(name, mapped, cAbiType)
            mapped == "UInt" -> "$name.toUInt()"
            mapped == "ULong" -> "$name.toULong()"
            mapped == "UShort" -> "$name.toUShort()"
            mapped == "UByte" -> "$name.toUByte()"
            cAbiType is KotlinKmpCAbiType.StructValue ->
                "$mapped(${namePlan.runtime(NATIVE_ADDRESS)}($name.address()))"
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(NATIVE_ADDRESS)}?" ->
                "$name.takeIf { it != ${namePlan.runtime(MEMORY_SEGMENT)}.NULL }" +
                    "?.let { ${namePlan.runtime(NATIVE_ADDRESS)}(it.address()) }"
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(C_STRING)}?" ->
                "$name.takeIf { it != ${namePlan.runtime(MEMORY_SEGMENT)}.NULL }" +
                    "?.let { ${namePlan.runtime(NATIVE_ADDRESS)}(it.address()) }?.let(::${namePlan.runtime(C_STRING)})"
            cAbiType is KotlinKmpCAbiType.Address && mapped.endsWith("?") -> {
                val nonNullable = mapped.removeSuffix("?")
                if (cAbiType.pointerDepth > 1) {
                        "$name.takeIf { it != ${namePlan.runtime(MEMORY_SEGMENT)}.NULL }" +
                        "?.reinterpret(${namePlan.runtime(VALUE_LAYOUT)}.ADDRESS.byteSize())" +
                        "?.get(${namePlan.runtime(VALUE_LAYOUT)}.ADDRESS, 0L)" +
                        "?.takeIf { it != ${namePlan.runtime(MEMORY_SEGMENT)}.NULL }" +
                        "?.let { ${namePlan.runtime(NATIVE_ADDRESS)}(it.address()) }?.let { $nonNullable(it) }"
                } else {
                    "$name.takeIf { it != ${namePlan.runtime(MEMORY_SEGMENT)}.NULL }" +
                        "?.let { ${namePlan.runtime(NATIVE_ADDRESS)}(it.address()) }?.let { $nonNullable(it) }"
                }
            }
            else -> name
        }
    }

    private fun optionsRawValue(name: String, cAbiType: KotlinKmpCAbiType): String {
        val scalar = cAbiType as? KotlinKmpCAbiType.Scalar
            ?: error("Options callback parameter must have a scalar C ABI type")
        if (scalar.jvmCarrier == "Long") return name
        if (!scalar.unsigned) return "$name.toLong()"
        return when (scalar.kind) {
            KotlinKmpCAbiType.Scalar.Kind.I8 -> "$name.toUByte().toLong()"
            KotlinKmpCAbiType.Scalar.Kind.I16 -> "$name.toUShort().toLong()"
            KotlinKmpCAbiType.Scalar.Kind.I32 -> "$name.toUInt().toLong()"
            KotlinKmpCAbiType.Scalar.Kind.CHAR16 -> "$name.code.toLong()"
            else -> "$name.toLong()"
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

    private fun planJvmLayout(layout: String): String =
        layout.replace("ValueLayout", namePlan.runtime(VALUE_LAYOUT))

    private fun planJvmCarrier(carrier: String): String =
        if (carrier == "MemorySegment") namePlan.runtime(MEMORY_SEGMENT) else carrier
}

/** dispatchSig letters the engine can express: I/J/F/D/Z carriers. */
private val ENGINE_CARRIERS = setOf("Int", "Long", "Float", "Double", "Boolean")
