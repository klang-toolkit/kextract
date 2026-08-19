package org.graphiks.kextract.kotlin.callbacks

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_EXCEPTION_HANDLER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_POLICY
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_REGISTRATION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME_API
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JVM_STATIC
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.OPT_IN
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.UPCALL_ENGINE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.PREPARED_CALLBACK_REGISTRATION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.UNSAFE_CALLBACK_REARM_API
import org.graphiks.kextract.kotlin.abi.KotlinKmpCAbiType
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayoutPlan
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiContext
import org.graphiks.kextract.kotlin.builders.SourceBuilder
import org.graphiks.kextract.kotlin.builders.canonicalRecordDeclaration
import org.graphiks.kextract.kotlin.builders.isStructType
import org.graphiks.kextract.pipeline.isEnum

internal class KotlinCallbackAndroidEmitter(
    private val mapType: (Type) -> String,
    private val mapJnaType: (Type) -> String,
    private val mapJnaFieldType: (Type) -> String,
    private val namePlan: KotlinKmpNamePlan,
    private val layoutPlan: AndroidRecordLayoutPlan,
) {
    fun emit(builder: SourceBuilder, callbacks: List<KotlinCallbackModel>) {
        emitJnaStructCarriers(builder, callbacks.filterNot { it.engineAbiSignature() != null })
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

    /**
     * JNA needs a real Structure.ByValue carrier for C records passed by value.
     * A Pointer carrier has the wrong ABI: it shifts every argument following the
     * record, which is especially visible for callbacks carrying userdata.
     */
    private fun emitJnaStructCarriers(builder: SourceBuilder, callbacks: List<KotlinCallbackModel>) {
        val declarations = mutableListOf<Declaration.Scoped>()
        val seen = mutableSetOf<String>()

        fun collect(type: Type) {
            if (!isStructType(type)) return
            val declaration = canonicalRecordDeclaration(type) ?: return
            val name = namePlan.declaration(declaration)
            if (!seen.add(name)) return
            declarations += declaration
            declaration.members()
                .filterIsInstance<Declaration.Variable>()
                .filterNot(Skip::isPresent)
                .forEach { collect(it.type()) }
        }

        callbacks.asSequence()
            .flatMap { it.rawParameters().asSequence() }
            .forEach { collect(it.type) }

        declarations.forEach { declaration ->
            val name = namePlan.declaration(declaration)
            val fields = declaration.members()
                .filterIsInstance<Declaration.Variable>()
                .filterNot(Skip::isPresent)
            builder.appendLine("private open class ${name}Jna : com.sun.jna.Structure {")
            builder.indent()
            fields.forEach { field ->
                val fieldType = mapJnaFieldType(field.type())
                builder.appendLine("@JvmField var ${namePlan.member(field)}: $fieldType = ${jnaDefaultValue(fieldType)}")
            }
            builder.appendLine()
            builder.appendLine("constructor() : super()")
            builder.appendLine("constructor(pointer: com.sun.jna.Pointer?) : super(pointer)")
            builder.appendLine(
                "override fun getFieldOrder() = listOf<String>(${fields.joinToString(", ") { "\"${namePlan.member(it)}\"" }})",
            )
            builder.appendLine()
            builder.appendLine(
                "class ByValue(pointer: com.sun.jna.Pointer? = null) : ${name}Jna(pointer), com.sun.jna.Structure.ByValue",
            )
            builder.unindent()
            builder.appendLine("}")
            builder.appendLine()
        }
    }

    private fun jnaDefaultValue(type: String): String = when (type) {
        "Byte", "Short", "Int", "Long", "Float", "Double" -> "0"
        "com.sun.jna.Pointer?" -> "null"
        else -> "$type()"
    }

    private fun emitTrampoline(builder: SourceBuilder, callback: KotlinCallbackModel) {
        val abiSignature = callback.engineAbiSignature()
        if (abiSignature != null) {
            emitEngineUpcallTrampoline(builder, callback, abiSignature)
        } else {
            emitJnaTrampoline(builder, callback)
        }
    }

    /**
     * Builds the C ABI descriptor consumed by kffi's Android libffi bridge.
     * kffi reserves a final `ptr` parameter for callback routing userdata.
     * Unsupported or interleaved routing shapes retain the JNA fallback so
     * other consumers are not silently emitted with an invalid ABI.
     */
    private fun KotlinCallbackModel.engineAbiSignature(): String? = runCatching {
        val rawParameters = rawParameters()
        if (routingUserdataParameter != null && rawParameters.lastOrNull() !== routingUserdataParameter) {
            return null
        }
        if (routingUserdataParameter == null && rawParameters.lastOrNull()?.cAbiType is KotlinKmpCAbiType.Address) {
            return null
        }
        "v(${rawParameters.joinToString(",") { abiTypeSpec(it.cAbiType) }})"
    }.getOrNull()

    private fun abiTypeSpec(abi: KotlinKmpCAbiType): String = when (abi) {
        is KotlinKmpCAbiType.Address -> "ptr"
        is KotlinKmpCAbiType.StructValue -> {
            require(abi.declaration.kind() == Declaration.Scoped.Kind.STRUCT) {
                "Android upcall ABI does not support union arguments by value: ${abi.declaration.name()}"
            }
            val fields = layoutPlan[abi.declaration].fields
                .flatMap { abiFieldTypeSpecs(it.field.type()) }
            require(fields.isNotEmpty()) {
                "Android upcall ABI does not support empty structs by value: ${abi.declaration.name()}"
            }
            "struct(${fields.joinToString(",")})"
        }
        is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
            KotlinKmpCAbiType.Scalar.Kind.BOOL -> "u8"
            KotlinKmpCAbiType.Scalar.Kind.I8 -> if (abi.unsigned) "u8" else "i8"
            KotlinKmpCAbiType.Scalar.Kind.I16 -> if (abi.unsigned) "u16" else "i16"
            KotlinKmpCAbiType.Scalar.Kind.CHAR16 -> "u16"
            KotlinKmpCAbiType.Scalar.Kind.I32 -> if (abi.unsigned) "u32" else "i32"
            KotlinKmpCAbiType.Scalar.Kind.I64 -> if (abi.unsigned) "u64" else "i64"
            KotlinKmpCAbiType.Scalar.Kind.F32 -> "float"
            KotlinKmpCAbiType.Scalar.Kind.F64 -> "double"
        }
    }

    private fun abiFieldTypeSpecs(type: Type): List<String> = when (type) {
        is Type.Array -> {
            val count = requireNotNull(type.elementCount()) {
                "Android upcall ABI requires fixed-size struct arrays"
            }
            require(count > 0L) { "Android upcall ABI does not support empty struct arrays" }
            List(count.toInt()) { abiFieldTypeSpecs(type.elementType()) }.flatten()
        }
        else -> listOf(abiTypeSpec(KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.CALLBACK)))
    }

    private fun KotlinCallbackModel.dispatchJvmSignature(): String {
        val carriers = buildList {
            if (hasRoutingUserdata) add("J")
            addAll(parameters.map { jniCarrier(it.cAbiType) })
        }.joinToString("")
        return "($carriers)V"
    }

    private fun jniCarrier(abi: KotlinKmpCAbiType): String = when (abi) {
        is KotlinKmpCAbiType.Address,
        is KotlinKmpCAbiType.StructValue,
        -> "J"
        is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
            KotlinKmpCAbiType.Scalar.Kind.BOOL,
            KotlinKmpCAbiType.Scalar.Kind.I8,
            -> "B"
            KotlinKmpCAbiType.Scalar.Kind.I16,
            KotlinKmpCAbiType.Scalar.Kind.CHAR16,
            -> "S"
            KotlinKmpCAbiType.Scalar.Kind.I32 -> "I"
            KotlinKmpCAbiType.Scalar.Kind.I64 -> "J"
            KotlinKmpCAbiType.Scalar.Kind.F32 -> "F"
            KotlinKmpCAbiType.Scalar.Kind.F64 -> "D"
        }
    }

    private fun emitEngineUpcallTrampoline(
        builder: SourceBuilder,
        callback: KotlinCallbackModel,
        abiSignature: String,
    ) {
        builder.appendLine("@${namePlan.runtime(OPT_IN)}(${namePlan.runtime(CALLBACK_RUNTIME_API)}::class)")
        builder.appendLine("private object ${callback.trampolineName} {")
        builder.indent()
        builder.appendLine("val address: ${namePlan.runtime(NATIVE_ADDRESS)} by lazy {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(NATIVE_ADDRESS)}(${namePlan.runtime(UPCALL_ENGINE)}.allocateTrampoline(")
        builder.indent()
        builder.appendLine("dispatcherClass = ${callback.trampolineName}::class.java,")
        builder.appendLine("dispatchMethod = \"dispatch\",")
        builder.appendLine("dispatchJvmSignature = \"${callback.dispatchJvmSignature()}\",")
        builder.appendLine("dispatchAbiSignature = \"$abiSignature\",")
        builder.unindent()
        builder.appendLine("))")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
        builder.appendLine("@${namePlan.runtime(JVM_STATIC)}")
        val dispatchParameters = buildList {
            if (callback.hasRoutingUserdata) add("token: Long")
            addAll(callback.parameters.map { "${it.name}: ${jniKotlinType(it.cAbiType)}" })
        }
        builder.appendLine("fun dispatch(${dispatchParameters.joinToString(", ")}) {")
        builder.indent()
        builder.appendLine("try {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(CALLBACK_RUNTIME)}.dispatchSafely(")
        builder.indent()
        builder.appendLine("type = ${callback.runtimeTypeName},")
        builder.appendLine(
            "userdata = ${if (callback.hasRoutingUserdata) "${namePlan.runtime(NATIVE_ADDRESS)}(token)" else "null"},",
        )
        builder.unindent()
        builder.appendLine(") { callback ->")
        builder.indent()
        emitEngineInvocation(builder, callback)
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

    /**
     * JNA-based trampoline kept for callback shapes the M4.1 upcall engine cannot express
     * (no routing userdata, or routed signatures with more/different arguments than the fixed
     * `(uint32_t value, void * routing_userdata)` CIF).
     */
    private fun emitJnaTrampoline(builder: SourceBuilder, callback: KotlinCallbackModel) {
        builder.appendLine("// TODO(M5.5): emit this callback through ${namePlan.runtime(UPCALL_ENGINE)} once its")
        builder.appendLine("// fixed (uint32_t value, void * routing_userdata) CIF generalizes to this shape.")
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

    private fun jniKotlinType(abi: KotlinKmpCAbiType): String = when (abi) {
        is KotlinKmpCAbiType.Address,
        is KotlinKmpCAbiType.StructValue,
        -> "Long"
        is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
            KotlinKmpCAbiType.Scalar.Kind.BOOL,
            KotlinKmpCAbiType.Scalar.Kind.I8,
            -> "Byte"
            KotlinKmpCAbiType.Scalar.Kind.I16,
            KotlinKmpCAbiType.Scalar.Kind.CHAR16,
            -> "Short"
            KotlinKmpCAbiType.Scalar.Kind.I32 -> "Int"
            KotlinKmpCAbiType.Scalar.Kind.I64 -> "Long"
            KotlinKmpCAbiType.Scalar.Kind.F32 -> "Float"
            KotlinKmpCAbiType.Scalar.Kind.F64 -> "Double"
        }
    }

    private fun emitEngineInvocation(builder: SourceBuilder, callback: KotlinCallbackModel) {
        if (callback.parameters.size <= 1) {
            val arguments = callback.parameters.joinToString(", ") { adaptEngineArgument(it) }
            builder.appendLine("callback.invoke($arguments)")
            return
        }

        builder.appendLine("callback.invoke(")
        builder.indent()
        callback.parameters.forEach { parameter ->
            builder.appendLine("${adaptEngineArgument(parameter)},")
        }
        builder.unindent()
        builder.appendLine(")")
    }

    private fun adaptEngineArgument(parameter: KotlinCallbackParameter): String {
        val name = parameter.name
        val mapped = mapType(parameter.type)
        val cAbiType = parameter.cAbiType
        return when {
            isEnum(parameter.type) && isOptionsStyle(mapped) ->
                "$mapped(${optionsRawValue(name, cAbiType)})"
            isEnum(parameter.type) -> enumApplicationValue(name, mapped, cAbiType)
            cAbiType is KotlinKmpCAbiType.StructValue ->
                "$mapped.ByValue(${namePlan.runtime(NATIVE_ADDRESS)}($name))"
            cAbiType is KotlinKmpCAbiType.Address ->
                nativeAddressConversion(name, mapped)
            mapped == "Boolean" -> "$name != 0.toByte()"
            mapped == "UInt" -> "$name.toUInt()"
            mapped == "ULong" -> "$name.toULong()"
            mapped == "UShort" -> "$name.toUShort()"
            mapped == "UByte" -> "$name.toUByte()"
            else -> name
        }
    }

    private fun nativeAddressConversion(name: String, mapped: String): String {
        val address = "$name.takeIf { it != 0L }?.let { ${namePlan.runtime(NATIVE_ADDRESS)}(it) }"
        return when {
            mapped == "${namePlan.runtime(NATIVE_ADDRESS)}?" -> address
            mapped == "${namePlan.runtime(C_STRING)}?" -> "$address?.let(::${namePlan.runtime(C_STRING)})"
            mapped.endsWith("?") -> {
                val nonNullable = mapped.removeSuffix("?")
                "$address?.let { $nonNullable(it) }"
            }
            else -> name
        }
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
            cAbiType is KotlinKmpCAbiType.StructValue ->
                "$mapped.ByValue(${jnaStructValueConversion(name)})"
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(NATIVE_ADDRESS)}?" ->
                jnaAddressConversion(name)
            cAbiType is KotlinKmpCAbiType.Address && mapped == "${namePlan.runtime(C_STRING)}?" ->
                jnaCStringConversion(name)
            cAbiType is KotlinKmpCAbiType.Address && mapped.endsWith("?") -> {
                val nonNullable = mapped.removeSuffix("?")
                if (cAbiType.pointerDepth > 1) {
                    "$name?.getPointer(0L)?.let { $nonNullable(${jnaNativeAddressOf("it")}) }"
                } else {
                    "${jnaAddressConversion(name)}?.let { $nonNullable(it) }"
                }
            }
            else -> name
        }
    }

    /** Converts the engine's fixed `(jlong token, jint value)` dispatch args to the application arg. */
    private fun adaptEngineValue(callback: KotlinCallbackModel): String {
        val parameter = callback.parameters.single()
        val mapped = mapType(parameter.type)
        val cAbiType = parameter.cAbiType
        return when {
            isEnum(parameter.type) && isOptionsStyle(mapped) ->
                "$mapped(${optionsRawValue("value", cAbiType)})"
            isEnum(parameter.type) -> enumApplicationValue("value", mapped, cAbiType)
            mapped == "UInt" -> "value.toUInt()"
            else -> "value"
        }
    }

    /** Converts a JNA `Pointer` upcall argument to the runtime [NATIVE_ADDRESS] wrapper. */
    private fun jnaAddressConversion(name: String): String =
        "$name?.takeIf { com.sun.jna.Pointer.nativeValue(it) != 0L }" +
            "?.let { ${namePlan.runtime(NATIVE_ADDRESS)}(com.sun.jna.Pointer.nativeValue(it)) }"

    /** Converts a JNA by-value Structure upcall argument to a memory-backed record view. */
    private fun jnaStructValueConversion(name: String): String =
        "${namePlan.runtime(NATIVE_ADDRESS)}(com.sun.jna.Pointer.nativeValue($name.getPointer()))"

    /** Converts a JNA `Pointer` upcall argument to a [C_STRING], preserving null. */
    private fun jnaCStringConversion(name: String): String =
        "$name?.takeIf { com.sun.jna.Pointer.nativeValue(it) != 0L }" +
            "?.let { ${namePlan.runtime(C_STRING)}(${namePlan.runtime(NATIVE_ADDRESS)}(com.sun.jna.Pointer.nativeValue(it))) }"

    /** [NATIVE_ADDRESS] of a non-null JNA `Pointer` expression (must not be null at that point). */
    private fun jnaNativeAddressOf(expression: String): String =
        "${namePlan.runtime(NATIVE_ADDRESS)}(com.sun.jna.Pointer.nativeValue($expression))"

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
            // TODO(M5.5): a non-options I32 enum lands here and emits `callback.invoke(name)` with
            // the raw Int carrier against an enum-typed invoke param, which does not compile.
            // Shared by the JVM/native/Android emitters; fix the cross-emitter bug here.
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
