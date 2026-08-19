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
import org.graphiks.kextract.kotlin.builders.SourceBuilder
import org.graphiks.kextract.kotlin.builders.canonicalRecordDeclaration
import org.graphiks.kextract.kotlin.builders.isStructType
import org.graphiks.kextract.pipeline.isEnum

internal class KotlinCallbackAndroidEmitter(
    private val mapType: (Type) -> String,
    private val mapJnaType: (Type) -> String,
    private val mapJnaFieldType: (Type) -> String,
    private val namePlan: KotlinKmpNamePlan,
) {
    fun emit(builder: SourceBuilder, callbacks: List<KotlinCallbackModel>) {
        emitJnaStructCarriers(builder, callbacks)
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
        if (callback.isEngineUpcallFit()) {
            emitEngineUpcallTrampoline(builder, callback)
        } else {
            emitJnaTrampoline(builder, callback)
        }
    }

    /**
     * M4.1's [UPCALL_ENGINE] closure is fixed to the CIF `(uint32_t value, void * routing_userdata)`
     * -> void, dispatching to a static `dispatch(token: Long, value: Int)`. A callback fits that
     * engine only when its raw C signature is exactly that shape: a single 32-bit integer value
     * argument followed by the routing userdata, with nothing else in between.
     */
    private fun KotlinCallbackModel.isEngineUpcallFit(): Boolean {
        if (!hasRoutingUserdata) return false
        val value = parameters.singleOrNull() ?: return false
        /* I32-backed enums need the JNA path until the shared enum-application bug is fixed (TODO(M5.5)) */
        if (isEnum(value.type)) return false
        val routing = routingUserdataParameter ?: return false
        if (routing.index < value.index) return false
        val scalar = value.cAbiType as? KotlinKmpCAbiType.Scalar ?: return false
        return scalar.kind == KotlinKmpCAbiType.Scalar.Kind.I32
    }

    private fun emitEngineUpcallTrampoline(builder: SourceBuilder, callback: KotlinCallbackModel) {
        builder.appendLine("@${namePlan.runtime(OPT_IN)}(${namePlan.runtime(CALLBACK_RUNTIME_API)}::class)")
        builder.appendLine("private object ${callback.trampolineName} {")
        builder.indent()
        builder.appendLine("val address: ${namePlan.runtime(NATIVE_ADDRESS)} by lazy {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(NATIVE_ADDRESS)}(${namePlan.runtime(UPCALL_ENGINE)}.allocateTrampoline(")
        builder.indent()
        builder.appendLine("dispatcherClass = ${callback.trampolineName}::class.java,")
        builder.appendLine("dispatchMethod = \"dispatch\",")
        builder.appendLine("dispatchSig = \"(JI)V\",")
        builder.unindent()
        builder.appendLine("))")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
        builder.appendLine("@${namePlan.runtime(JVM_STATIC)}")
        builder.appendLine("fun dispatch(token: Long, value: Int) {")
        builder.indent()
        builder.appendLine("try {")
        builder.indent()
        builder.appendLine("${namePlan.runtime(CALLBACK_RUNTIME)}.dispatchSafely(")
        builder.indent()
        builder.appendLine("type = ${callback.runtimeTypeName},")
        builder.appendLine("userdata = ${namePlan.runtime(NATIVE_ADDRESS)}(token),")
        builder.unindent()
        builder.appendLine(") { callback ->")
        builder.indent()
        builder.appendLine("callback.invoke(${adaptEngineValue(callback)})")
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
