@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.ClangUnnamedRecord
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.ARRAY_HOLDER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JVM_FIELD
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_ALLOCATOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_BUFFER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ENGINE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.TO_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpSourceSet
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackBindingEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackAndroidEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackModel
import org.graphiks.kextract.kotlin.callbacks.KotlinDirectFunctionBindingModel
import org.graphiks.kextract.kotlin.abi.AndroidFieldLayout
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayout
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayoutPlan
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiContext
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex
import org.graphiks.kextract.kotlin.abi.KotlinKmpCAbiType
import org.graphiks.kextract.kotlin.models.KotlinSourceFile

internal class KotlinKmpAndroidBuilder(
    private val targetPackage: String,
    private val className: String,
    private val libraryName: String,
    private val callbackModels: List<KotlinCallbackModel>,
    private val directBindingModels: List<KotlinDirectFunctionBindingModel>,
    private val namePlan: KotlinKmpNamePlan,
    private val layoutPlan: AndroidRecordLayoutPlan,
    private val abiIndex: KotlinKmpAbiIndex,
) : Declaration.Visitor<Unit> {

    private val builder = SourceBuilder()
    private val files = mutableListOf<KotlinSourceFile>()
    private val generatedNames = mutableSetOf<String>()
    private val callbackTypeNames = callbackModels.mapTo(mutableSetOf(), KotlinCallbackModel::typeName)
    private val typeMapper = KmpTypeMapper(namePlan, arraysAsHolders = false, abiIndex = abiIndex)
    private val nativeAddress = namePlan.runtime(NATIVE_ADDRESS)
    private val nativeEngine = namePlan.runtime(NATIVE_ENGINE)
    private val cString = namePlan.runtime(C_STRING)
    private val arrayHolder = namePlan.runtime(ARRAY_HOLDER)
    private val memoryAllocator = namePlan.runtime(MEMORY_ALLOCATOR)
    private val memoryBuffer = namePlan.runtime(MEMORY_BUFFER)
    private val toAddress = namePlan.runtime(TO_ADDRESS)

    private val excludedBridgeSymbols = setOf(
        KotlinKmpRuntimeSymbol.JNA_POINTER,
        KotlinKmpRuntimeSymbol.JNA_STRUCTURE,
        KotlinKmpRuntimeSymbol.JNA_UNION,
        KotlinKmpRuntimeSymbol.JNA_CALLBACK_REFERENCE,
        JVM_FIELD,
    )

    private val memoryScalarPrimitives = setOf(
        "Byte", "UByte", "Short", "UShort", "Int", "UInt", "Long", "ULong", "Float", "Double",
    )

    init {
        if (targetPackage.isNotEmpty()) {
            builder.appendLine("package $targetPackage")
            builder.appendLine()
        }

        KotlinKmpRuntimeSymbol.entries
            .filter { KotlinKmpSourceSet.ANDROID in it.sourceSets }
            .filterNot { it in excludedBridgeSymbols }
            .forEach { builder.appendLine(namePlan.importLine(it)) }
        builder.appendLine()
    }

    override fun visitScoped(decl: Declaration.Scoped) {
        if (Skip.isPresent(decl)) return
        when (decl.kind()) {
            Declaration.Scoped.Kind.STRUCT,
            Declaration.Scoped.Kind.UNION -> {
                val structName = namePlan.declaration(decl)
                if (structName.isEmpty() || structName.contains("unnamed")) return
                if (structName.endsWith("Impl") && decl.members().isEmpty()) return
                if (!generatedNames.add(structName)) return
                if (structName == "WGPUNativeDisplayHandle") {
                    emitNativeDisplayHandle(decl)
                    return
                }

                val layout = layoutPlan[decl]
                val sizeBytes = layout.sizeBytes
                val fields = decl.members().filterIsInstance<Declaration.Variable>().filterNot(Skip::isPresent)

                // 1. Generate the Bridge Actual Interface
                builder.appendLine("actual interface $structName {")
                builder.indent()

                fields.forEach { field ->
                    val fieldName = namePlan.member(field)
                    val fieldType = typeMapper.mapType(field.type())
                    builder.appendLine("actual var $fieldName: ${interfaceFieldType(fieldType)}")
                }
                builder.appendLine("actual val handler: $nativeAddress")

                // Companion object
                builder.appendLine("actual companion object {")
                builder.indent()
                builder.appendLine("actual operator fun invoke(address: $nativeAddress): $structName = ByReference(address)")
                builder.appendLine("actual fun allocate(allocator: $memoryAllocator): $structName = ByReference(allocator.allocateBuffer(${sizeBytes}uL).handler)")
                builder.appendLine("actual fun allocateArray(allocator: $memoryAllocator, size: UInt, provider: (UInt, $structName) -> Unit): $arrayHolder<$structName> {")
                builder.indent()
                // Zero size still bumps a non-null pointer; callers treat that as an empty, harmless region.
                builder.appendLine("val buffer = allocator.allocateBuffer(${sizeBytes}uL * size)")
                builder.appendLine("val result = $arrayHolder<$structName>(buffer.handler)")
                builder.appendLine("repeat(size.toInt()) { index ->")
                builder.indent()
                builder.appendLine("provider(index.toUInt(), ByValue($nativeAddress(buffer.handler.rawValue + index.toLong() * ${sizeBytes}L)))")
                builder.unindent()
                builder.appendLine("}")
                builder.appendLine("return result")
                builder.unindent()
                builder.appendLine("}")
                builder.unindent()
                builder.appendLine("}") // End companion object

                // 2. Generate the memory-backed implementations
                emitMemoryRecordImpl(structName, layout, fields, "ByReference")
                emitMemoryRecordImpl(structName, layout, fields, "ByValue")

                builder.unindent()
                builder.appendLine("}") // End actual interface
                builder.appendLine()
            }
            Declaration.Scoped.Kind.TOPLEVEL -> {
                for (member in decl.members()) {
                    member.accept(this)
                }
                KotlinCallbackAndroidEmitter(
                    typeMapper::mapFunctionType,
                    ::mapJnaType,
                    namePlan,
                ).emit(builder, callbackModels)
                KotlinCallbackBindingEmitter(typeMapper::mapFunctionType, namePlan).emitAndroid(
                    builder,
                    directBindingModels,
                ) { function, asLastExpression, argExpr ->
                    emitEngineDowncall(function, asLastExpression, argExpr)
                }
            }
            else -> {}
        }

        if (decl.kind() == Declaration.Scoped.Kind.TOPLEVEL) {
            files.add(
                KotlinSourceFile(
                    targetPackage,
                    className + "Android",
                    builder.toString(),
                    sourceRoot = "androidMain/kotlin",
                ),
            )
        }
    }

    override fun visitFunction(decl: Declaration.Function) {
        if (Skip.isPresent(decl)) return
        val name = namePlan.declaration(decl)
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        val params = decl.parameters().joinToString(", ") { param ->
            "${namePlan.parameter(param)}: ${typeMapper.mapFunctionType(param.type())}"
        }
        builder.appendLine("private val ${name}_ADDR: Long by lazy { $nativeEngine.resolveSymbol(\"${escapeKotlinString(decl.name())}\") }")
        builder.appendLine("actual fun $name($params): $returnType {")
        builder.indent()
        emitEngineDowncall(decl) { parameter ->
            namePlan.parameter(parameter)
        }
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }
    override fun visitVariable(decl: Declaration.Variable) {}
    override fun visitTypedef(decl: Declaration.Typedef) {
        if (Skip.isPresent(decl)) return
        val name = namePlan.declaration(decl)
        if (name.isEmpty()) return
        if (name in callbackTypeNames || typeMapper.callbackFunction(decl.type()) != null) return
        val inner = decl.type()
        if (inner is Type.Delegated && inner.kind() == Type.Delegated.Kind.POINTER) {
            val pointee = inner.type()
            if (pointee is Type.Declared && pointee.tree().kind() == Declaration.Scoped.Kind.STRUCT) {
                val pointeeName = pointee.tree().name()
                if (pointeeName.isNotEmpty() && pointeeName.endsWith("Impl")) {
                    if (!generatedNames.add(name)) return
                    builder.appendLine("@kotlin.jvm.JvmInline")
                    builder.appendLine("actual value class $name actual constructor(actual val handler: $nativeAddress)")
                    builder.appendLine()
                }
            }
        }
    }
    override fun visitConstant(decl: Declaration.Constant) {}
    override fun visitObjCClass(decl: Declaration.ObjCClass) {}
    override fun visitObjCProtocol(decl: Declaration.ObjCProtocol) {}
    override fun visitObjCCategory(decl: Declaration.ObjCCategory) {}

    fun getFiles(): List<KotlinSourceFile> = files

    /**
     * The typed engine wrapper name `call<R><N><ARGS>` for [type], or `null` when the
     * signature carries a struct-by-value arg or return and must ride the `callGeneric`
     * path instead. R ∈ V/I/L/P/D/F/S/B (V void, P pointer, others by scalar carrier),
     * N = arg count, ARGS = one letter per arg (I Int, L Long, P pointer-as-Long,
     * D Double, F Float, S Short, B Byte). The engine C table implements a subset today;
     * kextract emits against the full scheme and M5.5 grows the table to cover wgpu.
     */
    private fun wrapperForm(type: Type.Function): String? {
        val returnLetter = engineReturnLetter(type.returnType()) ?: return null
        val argLetters = type.argumentTypes().map { arg ->
            engineArgLetter(KotlinKmpCAbiType.from(arg, KotlinKmpAbiContext.DIRECT))
        }
        if (argLetters.any { it == null }) return null
        val letters = argLetters.joinToString("") { it ?: "" }
        return "call$returnLetter${argLetters.size}$letters"
    }

    private fun engineReturnLetter(type: Type): String? {
        if (type is Type.Primitive && type.kind() == Type.Primitive.Kind.Void) return "V"
        return engineArgLetter(KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.DIRECT))
    }

    private fun engineArgLetter(abi: KotlinKmpCAbiType): String? = when (abi) {
        is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
            KotlinKmpCAbiType.Scalar.Kind.BOOL,
            KotlinKmpCAbiType.Scalar.Kind.I32,
            -> "I"
            KotlinKmpCAbiType.Scalar.Kind.I8 -> "B"
            KotlinKmpCAbiType.Scalar.Kind.I16,
            KotlinKmpCAbiType.Scalar.Kind.CHAR16,
            -> "S"
            KotlinKmpCAbiType.Scalar.Kind.I64 -> "L"
            KotlinKmpCAbiType.Scalar.Kind.F32 -> "F"
            KotlinKmpCAbiType.Scalar.Kind.F64 -> "D"
        }
        is KotlinKmpCAbiType.Address -> "P"
        is KotlinKmpCAbiType.StructValue -> null
    }

    /**
     * Emits the engine downcall statements for [function] into the current [builder].
     * [argExpr] resolves each C parameter to the Kotlin expression carrying its value
     * (the parameter name for `actual fun`s; the preflight parameter for direct bindings).
     * Typed signatures use the `call<R><N><ARGS>` wrapper; struct-by-value signatures use
     * `callGeneric` with a packed argument buffer and a documented typeSpec.
     *
     * When [asLastExpression] is set the downcall is emitted as the final expression of an
     * enclosing lambda (an Android direct-binding preflight), so value conversions are
     * emitted without an unqualified `return` (which is prohibited inside a lambda) and the
     * call itself is the lambda's value.
     */
    private fun emitEngineDowncall(
        function: Declaration.Function,
        asLastExpression: Boolean = false,
        argExpr: (Declaration.Variable) -> String,
    ) {
        val wrapper = wrapperForm(function.type())
        if (wrapper != null) {
            emitTypedDowncall(function, wrapper, asLastExpression, argExpr)
        } else {
            emitGenericDowncall(function, asLastExpression, argExpr)
        }
    }

    private fun emitTypedDowncall(
        function: Declaration.Function,
        wrapper: String,
        asLastExpression: Boolean = false,
        argExpr: (Declaration.Variable) -> String,
    ) {
        val engineArgs = function.parameters()
            .map { toEngineArgument(argExpr(it), it.type()) }
            .joinToString(", ")
        val call = "$nativeEngine.$wrapper(${functionAddress(function)}" +
            (if (engineArgs.isEmpty()) "" else ", $engineArgs") + ")"
        emitEngineReturn(function.type().returnType(), call, asLastExpression)
    }

    private fun functionAddress(function: Declaration.Function): String =
        "${namePlan.declaration(function)}_ADDR"

    private fun toEngineArgument(name: String, type: Type): String {
        val kmpType = typeMapper.mapFunctionType(type)
        return when (val abi = KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.DIRECT)) {
            is KotlinKmpCAbiType.Address -> when {
                kmpType == "$nativeAddress?" -> "$name.${toAddress}()"
                kmpType == nativeAddress -> "$name.rawValue"
                kmpType.endsWith("?") -> "$name?.handler?.rawValue ?: 0L"
                else -> "$name.handler.rawValue"
            }
            is KotlinKmpCAbiType.Scalar -> when {
                typeMapper.isOptionsEnumType(type) ->
                    abiIndex.enum(typeMapper.enumDeclaration(type)).optionsRawToJvmCarrier("$name.rawValue")
                typeMapper.isEnumType(type) ->
                    abiIndex.enum(typeMapper.enumDeclaration(type)).toJvmCarrier(name)
                kmpType == "Boolean" -> "if ($name) 1 else 0"
                kmpType == "UInt" -> "$name.toInt()"
                kmpType == "ULong" -> "$name.toLong()"
                kmpType == "UShort" -> "$name.toShort()"
                kmpType == "UByte" -> "$name.toByte()"
                else -> name
            }
            is KotlinKmpCAbiType.StructValue ->
                error("struct-by-value arguments ride the callGeneric path, not toEngineArgument")
        }
    }

    private fun emitEngineReturn(type: Type, call: String, asLastExpression: Boolean = false) {
        val returnType = typeMapper.mapFunctionType(type)
        val resultPrefix = if (asLastExpression) "" else "return "
        if (returnType == "Unit") {
            builder.appendLine(call)
            if (!asLastExpression) builder.appendLine("return")
            return
        }
        when {
            typeMapper.isOptionsEnumType(type) -> {
                val scalar = abiIndex.enum(typeMapper.enumDeclaration(type))
                builder.appendLine(
                    "$resultPrefix$returnType(${scalar.jvmCarrierToOptionsRaw(narrowEngineCarrier(call, scalar))})",
                )
            }
            typeMapper.isEnumType(type) -> {
                val scalar = abiIndex.enum(typeMapper.enumDeclaration(type))
                builder.appendLine("$resultPrefix${scalar.fromJvmCarrier(narrowEngineCarrier(call, scalar))}")
            }
            returnType == "$nativeAddress?" -> builder.appendLine("$resultPrefix$call.takeIf { it != 0L }?.let(::$nativeAddress)")
            returnType == "$cString?" -> builder.appendLine("$resultPrefix$call.takeIf { it != 0L }?.let(::$nativeAddress)?.let(::$cString)")
            returnType.endsWith("?") && returnsPointer(type) -> {
                val nonNullable = returnType.removeSuffix("?")
                builder.appendLine("$resultPrefix$call.takeIf { it != 0L }?.let(::$nativeAddress)?.let(::$nonNullable)")
            }
            else -> {
                val scalar = KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.DIRECT) as KotlinKmpCAbiType.Scalar
                when {
                    scalar.kind == KotlinKmpCAbiType.Scalar.Kind.BOOL -> builder.appendLine("$resultPrefix$call != 0L")
                    scalar.unsigned && scalar.kind == KotlinKmpCAbiType.Scalar.Kind.I8 -> builder.appendLine("$resultPrefix$call.toByte().toUByte()")
                    scalar.unsigned && scalar.kind == KotlinKmpCAbiType.Scalar.Kind.I16 -> builder.appendLine("$resultPrefix$call.toShort().toUShort()")
                    scalar.unsigned && scalar.kind == KotlinKmpCAbiType.Scalar.Kind.I32 -> builder.appendLine("$resultPrefix$call.toInt().toUInt()")
                    scalar.unsigned && scalar.kind == KotlinKmpCAbiType.Scalar.Kind.I64 -> builder.appendLine("$resultPrefix$call.toULong()")
                    scalar.kind == KotlinKmpCAbiType.Scalar.Kind.I8 -> builder.appendLine("$resultPrefix$call.toByte()")
                    scalar.kind == KotlinKmpCAbiType.Scalar.Kind.I16 -> builder.appendLine("$resultPrefix$call.toShort()")
                    scalar.kind == KotlinKmpCAbiType.Scalar.Kind.I32 -> builder.appendLine("$resultPrefix$call.toInt()")
                    scalar.kind == KotlinKmpCAbiType.Scalar.Kind.I64 -> builder.appendLine("$resultPrefix$call")
                    else -> builder.appendLine("$resultPrefix$call")
                }
            }
        }
    }

    /** Narrows the engine's Long (or F/D) result to the scalar's jvmCarrier expression. */
    private fun narrowEngineCarrier(call: String, scalar: KotlinKmpCAbiType.Scalar): String = when (scalar.kind) {
        KotlinKmpCAbiType.Scalar.Kind.I8 -> "$call.toByte()"
        KotlinKmpCAbiType.Scalar.Kind.I16, KotlinKmpCAbiType.Scalar.Kind.CHAR16 -> "$call.toShort()"
        KotlinKmpCAbiType.Scalar.Kind.I32 -> "$call.toInt()"
        else -> call
    }

    /**
     * Struct-by-value downcalls ride `callGeneric(fn, argc, typeSpec, argsPtr, outPtr)`.
     * typeSpec is `"<return>:<arg0>,<arg1>,..."` where each code is `v p i8 u8 i16 u16
     * i32 u32 i64 u64 f32 f64` or `s<size>` for a struct-by-value slot. The argument
     * buffer packs each arg in declaration order at its natural alignment (scalars and
     * pointers at their carrier width, structs at their C record alignment); the out
     * buffer receives the return value (struct bytes, or the scalar carrier).
     *
     * NOTE: the engine's generic reader currently IGNORES typeSpec and reads every arg
     * as an 8-byte uint64 carrier, so struct-by-value args packed here at full size are
     * truncated to 8 bytes at runtime (43 of 44 wgpu generic sites). Tracking:
     * TODO(M6/P2) implement per-arg ffi_type selection in the engine reader to honor
     * typeSpec exactly as this emission packs it.
     */
    private fun emitGenericDowncall(
        function: Declaration.Function,
        asLastExpression: Boolean = false,
        argExpr: (Declaration.Variable) -> String,
    ) {
        val functionType = function.type()
        val params = function.parameters()
        val returnType = typeMapper.mapFunctionType(functionType.returnType())
        val returnAbi = if (returnType == "Unit") {
            null
        } else {
            KotlinKmpCAbiType.from(functionType.returnType(), KotlinKmpAbiContext.DIRECT)
        }

        var cursor = 0L
        val typeSpecCodes = mutableListOf<String>()
        val slotEmissions = mutableListOf<(SourceBuilder) -> Unit>()
        params.forEachIndexed { _, parameter ->
            val abi = KotlinKmpCAbiType.from(parameter.type(), KotlinKmpAbiContext.DIRECT)
            when (abi) {
                is KotlinKmpCAbiType.StructValue -> {
                    val layout = layoutPlan[abi.declaration]
                    val offset = alignTo(cursor, layout.alignmentBytes)
                    cursor = offset + layout.sizeBytes
                    typeSpecCodes += "s${layout.sizeBytes}"
                    slotEmissions += emitStructSlot(parameter, argExpr, layout.sizeBytes, offset)
                }
                else -> {
                    val carrierBytes = engineCarrierBytes(abi)
                    val offset = alignTo(cursor, carrierBytes)
                    cursor = offset + carrierBytes
                    typeSpecCodes += engineTypeSpecCode(abi)
                    slotEmissions += emitScalarSlot(parameter, argExpr, abi, offset)
                }
            }
        }
        val returnCode = when {
            returnAbi == null -> "v"
            returnAbi is KotlinKmpCAbiType.StructValue -> "s${layoutPlan[returnAbi.declaration].sizeBytes}"
            else -> engineTypeSpecCode(returnAbi)
        }
        val typeSpec = "\"$returnCode:${typeSpecCodes.joinToString(",")}\""
        val argsSize = cursor.coerceAtLeast(8L)
        val outSize = when (val abi = returnAbi) {
            is KotlinKmpCAbiType.StructValue -> layoutPlan[abi.declaration].sizeBytes
            else -> 8L
        }

        builder.appendLine("val args = $memoryAllocator().allocateBuffer(${argsSize}uL)")
        slotEmissions.forEach { it(builder) }
        builder.appendLine("val out = $memoryAllocator().allocateBuffer(${outSize}uL)")
        builder.appendLine(
            "$nativeEngine.callGeneric(${functionAddress(function)}, ${params.size}, " +
                "$typeSpec, args.handler.rawValue, out.handler.rawValue)",
        )
        emitGenericReturn(functionType.returnType(), returnType, returnAbi, asLastExpression)
    }

    private fun emitStructSlot(
        parameter: Declaration.Variable,
        argExpr: (Declaration.Variable) -> String,
        sizeBytes: Long,
        offset: Long,
    ): (SourceBuilder) -> Unit = { target ->
        val bytes = "${argExpr(parameter)}Bytes"
        target.appendLine("val $bytes = ByteArray($sizeBytes)")
        target.appendLine(
            "$memoryBuffer(${argExpr(parameter)}.handler, ${sizeBytes}uL).readBytes($bytes, 0u, 0uL, ${sizeBytes}uL)",
        )
        target.appendLine("args.writeBytes($bytes, 0u, ${offset}uL, ${sizeBytes}uL)")
    }

    private fun emitScalarSlot(
        parameter: Declaration.Variable,
        argExpr: (Declaration.Variable) -> String,
        abi: KotlinKmpCAbiType,
        offset: Long,
    ): (SourceBuilder) -> Unit = { target ->
        val name = argExpr(parameter)
        val value = when (abi) {
            is KotlinKmpCAbiType.Address -> when (typeMapper.mapFunctionType(parameter.type())) {
                "$nativeAddress?" -> "$name.${toAddress}()"
                nativeAddress -> "$name.rawValue"
                else -> "$name?.handler?.rawValue ?: 0L"
            }
            is KotlinKmpCAbiType.Scalar -> {
                val kmpType = typeMapper.mapFunctionType(parameter.type())
                when {
                    typeMapper.isOptionsEnumType(parameter.type()) ->
                        abiIndex.enum(typeMapper.enumDeclaration(parameter.type())).optionsRawToJvmCarrier("$name.rawValue")
                    typeMapper.isEnumType(parameter.type()) ->
                        abiIndex.enum(typeMapper.enumDeclaration(parameter.type())).toJvmCarrier(name)
                    abi.kind == KotlinKmpCAbiType.Scalar.Kind.BOOL -> "(if ($name) 1 else 0).toByte()"
                    abi.unsigned && abi.kind == KotlinKmpCAbiType.Scalar.Kind.I8 -> "$name.toByte()"
                    abi.unsigned && abi.kind == KotlinKmpCAbiType.Scalar.Kind.I16 -> "$name.toShort()"
                    abi.unsigned && abi.kind == KotlinKmpCAbiType.Scalar.Kind.I32 -> "$name.toInt()"
                    abi.unsigned && abi.kind == KotlinKmpCAbiType.Scalar.Kind.I64 -> "$name.toLong()"
                    else -> when (kmpType) {
                        "UInt" -> "$name.toInt()"
                        "ULong" -> "$name.toLong()"
                        "UShort" -> "$name.toShort()"
                        "UByte" -> "$name.toByte()"
                        else -> name
                    }
                }
            }
            is KotlinKmpCAbiType.StructValue -> error("unreachable: struct slots pack separately")
        }
        target.appendLine("args.${engineWritePrimitive(abi)}($value, ${offset}uL)")
    }

    private fun emitGenericReturn(
        type: Type,
        returnType: String,
        returnAbi: KotlinKmpCAbiType?,
        asLastExpression: Boolean = false,
    ) {
        if (returnType == "Unit" || returnAbi == null) return
        val resultPrefix = if (asLastExpression) "" else "return "
        when (returnAbi) {
            is KotlinKmpCAbiType.StructValue -> {
                builder.appendLine("$resultPrefix$returnType.ByValue(out.handler)")
            }
            is KotlinKmpCAbiType.Address -> when {
                returnType == "$nativeAddress?" -> builder.appendLine(resultPrefix + "out.readLong(0uL).takeIf { it != 0L }?.let(::$nativeAddress)")
                returnType == "$cString?" -> builder.appendLine(resultPrefix + "out.readLong(0uL).takeIf { it != 0L }?.let(::$nativeAddress)?.let(::$cString)")
                returnType.endsWith("?") -> {
                    val nonNullable = returnType.removeSuffix("?")
                    builder.appendLine(resultPrefix + "out.readLong(0uL).takeIf { it != 0L }?.let(::$nativeAddress)?.let(::$nonNullable)")
                }
                else -> builder.appendLine(resultPrefix + "out.readLong(0uL)")
            }
            is KotlinKmpCAbiType.Scalar -> {
                val read = engineReadPrimitive(returnAbi)
                when {
                    typeMapper.isOptionsEnumType(type) -> {
                        val scalar = abiIndex.enum(typeMapper.enumDeclaration(type))
                        builder.appendLine(
                            "$resultPrefix$returnType(${scalar.jvmCarrierToOptionsRaw("out.$read(0uL)")})",
                        )
                    }
                    typeMapper.isEnumType(type) -> {
                        val scalar = abiIndex.enum(typeMapper.enumDeclaration(type))
                        builder.appendLine("$resultPrefix${scalar.fromJvmCarrier("out.$read(0uL)")}")
                    }
                    returnAbi.kind == KotlinKmpCAbiType.Scalar.Kind.BOOL ->
                        builder.appendLine(resultPrefix + "out.readLong(0uL) != 0L")
                    returnAbi.kind == KotlinKmpCAbiType.Scalar.Kind.I8 ->
                        builder.appendLine(resultPrefix + "out.readByte(0uL)")
                    returnAbi.kind == KotlinKmpCAbiType.Scalar.Kind.I16 ->
                        builder.appendLine(resultPrefix + "out.readShort(0uL)")
                    returnAbi.kind == KotlinKmpCAbiType.Scalar.Kind.I32 ->
                        builder.appendLine(resultPrefix + "out.readInt(0uL)")
                    returnAbi.kind == KotlinKmpCAbiType.Scalar.Kind.I64 ->
                        builder.appendLine(resultPrefix + "out.readLong(0uL)")
                    returnAbi.kind == KotlinKmpCAbiType.Scalar.Kind.F32 ->
                        builder.appendLine(resultPrefix + "out.readFloat(0uL)")
                    returnAbi.kind == KotlinKmpCAbiType.Scalar.Kind.F64 ->
                        builder.appendLine(resultPrefix + "out.readDouble(0uL)")
                    else -> builder.appendLine(resultPrefix + "out.readLong(0uL)")
                }
            }
        }
    }

    /** Width in bytes of an engine argument carrier for the generic packed buffer. */
    private fun engineCarrierBytes(abi: KotlinKmpCAbiType): Long = when (abi) {
        is KotlinKmpCAbiType.Address -> 8L
        is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
            KotlinKmpCAbiType.Scalar.Kind.BOOL,
            KotlinKmpCAbiType.Scalar.Kind.I8,
            -> 1L
            KotlinKmpCAbiType.Scalar.Kind.I16,
            KotlinKmpCAbiType.Scalar.Kind.CHAR16,
            -> 2L
            KotlinKmpCAbiType.Scalar.Kind.I32,
            KotlinKmpCAbiType.Scalar.Kind.F32,
            -> 4L
            KotlinKmpCAbiType.Scalar.Kind.I64,
            KotlinKmpCAbiType.Scalar.Kind.F64,
            -> 8L
        }
        is KotlinKmpCAbiType.StructValue -> error("unreachable: struct slots pack separately")
    }

    private fun engineTypeSpecCode(abi: KotlinKmpCAbiType): String = when (abi) {
        is KotlinKmpCAbiType.Address -> "p"
        is KotlinKmpCAbiType.StructValue -> error("unreachable: struct slots pack separately")
        is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
            KotlinKmpCAbiType.Scalar.Kind.BOOL -> "b8"
            KotlinKmpCAbiType.Scalar.Kind.I8 -> if (abi.unsigned) "u8" else "i8"
            KotlinKmpCAbiType.Scalar.Kind.I16, KotlinKmpCAbiType.Scalar.Kind.CHAR16 -> if (abi.unsigned) "u16" else "i16"
            KotlinKmpCAbiType.Scalar.Kind.I32 -> if (abi.unsigned) "u32" else "i32"
            KotlinKmpCAbiType.Scalar.Kind.I64 -> if (abi.unsigned) "u64" else "i64"
            KotlinKmpCAbiType.Scalar.Kind.F32 -> "f32"
            KotlinKmpCAbiType.Scalar.Kind.F64 -> "f64"
        }
    }

    private fun engineWritePrimitive(abi: KotlinKmpCAbiType): String = when (abi) {
        is KotlinKmpCAbiType.Address -> "writeLong"
        is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
            KotlinKmpCAbiType.Scalar.Kind.BOOL,
            KotlinKmpCAbiType.Scalar.Kind.I8,
            -> "writeByte"
            KotlinKmpCAbiType.Scalar.Kind.I16,
            KotlinKmpCAbiType.Scalar.Kind.CHAR16,
            -> "writeShort"
            KotlinKmpCAbiType.Scalar.Kind.I32 -> "writeInt"
            KotlinKmpCAbiType.Scalar.Kind.I64 -> "writeLong"
            KotlinKmpCAbiType.Scalar.Kind.F32 -> "writeFloat"
            KotlinKmpCAbiType.Scalar.Kind.F64 -> "writeDouble"
        }
        is KotlinKmpCAbiType.StructValue -> error("unreachable")
    }

    private fun engineReadPrimitive(abi: KotlinKmpCAbiType.Scalar): String = when (abi.kind) {
        KotlinKmpCAbiType.Scalar.Kind.BOOL,
        KotlinKmpCAbiType.Scalar.Kind.I8,
        -> "readByte"
        KotlinKmpCAbiType.Scalar.Kind.I16,
        KotlinKmpCAbiType.Scalar.Kind.CHAR16,
        -> "readShort"
        KotlinKmpCAbiType.Scalar.Kind.I32 -> "readInt"
        KotlinKmpCAbiType.Scalar.Kind.I64 -> "readLong"
        KotlinKmpCAbiType.Scalar.Kind.F32 -> "readFloat"
        KotlinKmpCAbiType.Scalar.Kind.F64 -> "readDouble"
    }

    private fun alignTo(value: Long, alignment: Long): Long =
        if (alignment <= 1L) value else ((value + alignment - 1L) / alignment) * alignment

    private fun returnsPointer(type: Type): Boolean = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> true
        type is Type.Delegated -> returnsPointer(type.type())
        else -> false
    }

    private fun escapeKotlinString(value: String): String = value
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("$", "\\$")

    private fun emitNativeDisplayHandle(decl: Declaration.Scoped) {
        val layout = layoutPlan[decl]
        val sizeBytes = layout.sizeBytes
        val unionField = decl.members()
            .filterIsInstance<Declaration.Variable>()
            .filterNot(Skip::isPresent)
            .firstOrNull { typeMapper.declaredUnion(it.type()) != null }
        val fields = decl.members()
            .filterIsInstance<Declaration.Variable>()
            .filterNot(Skip::isPresent)
            .filterNot { it == unionField }
        val unionFields = nativeDisplayUnionFields(decl)
        val dataOffset = unionField?.let { layout.field(it.name()).offsetBytes } ?: 0L
        val typeOffset = layout.field("type").offsetBytes

        builder.appendLine("actual interface WGPUNativeDisplayHandle {")
        builder.indent()
        fields.forEach { field ->
            val fieldType = typeMapper.mapType(field.type())
            builder.appendLine("actual var ${namePlan.member(field)}: ${interfaceFieldType(fieldType)}")
        }
        unionFields.forEach { field ->
            val type = typeMapper.mapType(field.type())
            val setter = field.name().replaceFirstChar { it.titlecase() }
            builder.appendLine("actual val ${namePlan.member(field)}: $type?")
            builder.appendLine("actual fun set$setter(value: $type)")
        }
        builder.appendLine("actual val handler: $nativeAddress")

        builder.appendLine("actual companion object {")
        builder.indent()
        builder.appendLine("actual operator fun invoke(address: $nativeAddress): WGPUNativeDisplayHandle = ByReference(address)")
        builder.appendLine("actual fun allocate(allocator: $memoryAllocator): WGPUNativeDisplayHandle = ByReference(allocator.allocateBuffer(${sizeBytes}uL).handler)")
        builder.appendLine("actual fun allocateArray(allocator: $memoryAllocator, size: UInt, provider: (UInt, WGPUNativeDisplayHandle) -> Unit): $arrayHolder<WGPUNativeDisplayHandle> {")
        builder.indent()
        // Zero size still bumps a non-null pointer; callers treat that as an empty, harmless region.
        builder.appendLine("val buffer = allocator.allocateBuffer(${sizeBytes}uL * size)")
        builder.appendLine("val result = $arrayHolder<WGPUNativeDisplayHandle>(buffer.handler)")
        builder.appendLine("repeat(size.toInt()) { index ->")
        builder.indent()
        builder.appendLine("provider(index.toUInt(), ByValue($nativeAddress(buffer.handler.rawValue + index.toLong() * ${sizeBytes}L)))")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("return result")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")

        emitNativeDisplayHandleImpl("ByReference", layout, unionFields, typeOffset, dataOffset)
        emitNativeDisplayHandleImpl("ByValue", layout, unionFields, typeOffset, dataOffset)

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitNativeDisplayHandleImpl(
        name: String,
        layout: AndroidRecordLayout,
        unionFields: List<Declaration.Variable>,
        typeOffset: Long,
        dataOffset: Long,
    ) {
        val sizeBytes = layout.sizeBytes
        val typeScalar = abiIndex.enum(typeMapper.enumDeclaration(layout.field("type").field.type()))
        val (typeRead, typeWrite, typeCast) = enumMemoryPrimitives(typeScalar)
        builder.appendLine()
        builder.appendLine("class $name(val handle: $nativeAddress = $nativeAddress(0L)) : WGPUNativeDisplayHandle {")
        builder.indent()
        builder.appendLine("private val buffer: $memoryBuffer by lazy { $memoryBuffer(handle, ${sizeBytes}uL) }")
        builder.appendLine("override var type: WGPUNativeDisplayHandleType")
        builder.indent()
        builder.appendLine("get() = buffer.$typeRead(${typeOffset}uL) as WGPUNativeDisplayHandleType")
        builder.appendLine("set(value) { buffer.$typeWrite($typeCast, ${typeOffset}uL) }")
        builder.unindent()
        unionFields.forEach { field ->
            val fieldName = namePlan.member(field)
            val type = typeMapper.mapType(field.type())
            val setter = field.name().replaceFirstChar { it.titlecase() }
            val discriminator = "WGPUNativeDisplayHandleType_$setter"
            val memberSize = layoutPlan[requireNotNull(canonicalRecordDeclaration(field.type()))].sizeBytes
            builder.appendLine("override val $fieldName: $type?")
            builder.indent()
            builder.appendLine("get() = if (type != $discriminator) null else $type.ByValue($nativeAddress(handle.rawValue + ${dataOffset}L))")
            builder.unindent()
            builder.appendLine("override fun set$setter(value: $type) {")
            builder.indent()
            builder.appendLine("type = $discriminator")
            builder.appendLine("val bytes = ByteArray($memberSize)")
            builder.appendLine("$memoryBuffer(value.handler, ${memberSize}uL).readBytes(bytes, 0u, 0uL, ${memberSize}uL)")
            builder.appendLine("buffer.writeBytes(bytes, 0u, ${dataOffset}uL, ${memberSize}uL)")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.appendLine("override val handler: $nativeAddress")
        builder.indent()
        builder.appendLine("get() = handle")
        builder.unindent()
        builder.unindent()
        builder.appendLine("}")
    }

    private fun nativeDisplayUnionFields(decl: Declaration.Scoped): List<Declaration.Variable> =
        decl.members()
            .filterIsInstance<Declaration.Variable>()
            .filterNot(Skip::isPresent)
            .firstOrNull { typeMapper.declaredUnion(it.type()) != null }
            ?.type()
            ?.let(typeMapper::declaredUnion)
            ?.members()
            ?.filterIsInstance<Declaration.Variable>()
            ?: decl.members()
                .filterIsInstance<Declaration.Scoped>()
                .firstOrNull { it.kind() == Declaration.Scoped.Kind.UNION }
                ?.members()
                ?.filterIsInstance<Declaration.Variable>()
            ?: emptyList()

    private fun emitMemoryRecordImpl(
        structName: String,
        layout: AndroidRecordLayout,
        fields: List<Declaration.Variable>,
        implName: String,
    ) {
        val sizeBytes = layout.sizeBytes
        builder.appendLine()
        builder.appendLine("class $implName(val handle: $nativeAddress = $nativeAddress(0L)) : $structName {")
        builder.indent()
        builder.appendLine("private val buffer: $memoryBuffer by lazy { $memoryBuffer(handle, ${sizeBytes}uL) }")
        fields.forEach { field ->
            val propertyName = namePlan.member(field)
            val fieldType = typeMapper.mapType(field.type())
            builder.appendLine("override var $propertyName: ${interfaceFieldType(fieldType)}")
            builder.indent()
            emitMemoryFieldAccessors(field, fieldType, layout.field(field.name()))
            builder.unindent()
        }
        builder.appendLine("override val handler: $nativeAddress")
        builder.indent()
        builder.appendLine("get() = handle")
        builder.unindent()
        builder.unindent()
        builder.appendLine("}")
    }

    private fun interfaceFieldType(fieldType: String): String = when {
        fieldType == cString -> "$cString?"
        fieldType.startsWith(arrayHolder) ->
            // Inline C array fields would be wrong with pointer accessors; no wgpu struct uses them today.
            "$fieldType?"
        else -> fieldType
    }

    private fun emitMemoryFieldAccessors(
        field: Declaration.Variable,
        fieldType: String,
        fieldLayout: AndroidFieldLayout,
    ) {
        val offset = fieldLayout.offsetBytes
        val propertyName = namePlan.member(field)
        when {
            fieldType == cString -> {
                builder.appendLine("get() = buffer.readPointer(${offset}uL).takeIf { it.rawValue != 0L }?.let(::$cString)")
                builder.appendLine("set(value) { buffer.writePointer(value?.handler ?: $nativeAddress(0L), ${offset}uL) }")
            }
            fieldType == nativeAddress -> {
                builder.appendLine("get() = buffer.readPointer(${offset}uL)")
                builder.appendLine("set(value) { buffer.writePointer(value, ${offset}uL) }")
            }
            fieldType == "$nativeAddress?" -> {
                builder.appendLine("get() = buffer.readPointer(${offset}uL).takeIf { it.rawValue != 0L }")
                builder.appendLine("set(value) { buffer.writePointer(value ?: $nativeAddress(0L), ${offset}uL) }")
            }
            typeMapper.isOptionsEnumType(field.type()) -> {
                val scalar = abiIndex.enum(typeMapper.enumDeclaration(field.type()))
                val (read, write) = memoryPrimitives(scalar)
                builder.appendLine("get() = $fieldType(${scalar.jvmCarrierToOptionsRaw("buffer.$read(${offset}uL)")})")
                builder.appendLine("set(value) { buffer.$write(${scalar.optionsRawToJvmCarrier("value.rawValue")}, ${offset}uL) }")
            }
            typeMapper.isEnumType(field.type()) -> {
                val scalar = abiIndex.enum(typeMapper.enumDeclaration(field.type()))
                val (read, write, cast) = enumMemoryPrimitives(scalar)
                builder.appendLine("get() = buffer.$read(${offset}uL) as $fieldType")
                builder.appendLine("set(value) { buffer.$write($cast, ${offset}uL) }")
            }
            isStructType(field.type()) -> {
                val fieldSize = fieldLayout.sizeBytes
                builder.appendLine("get() = $fieldType.ByValue($nativeAddress(handle.rawValue + ${offset}L))")
                builder.appendLine("set(value) {")
                builder.indent()
                builder.appendLine("val bytes = ByteArray($fieldSize)")
                builder.appendLine("$memoryBuffer(value.handler, ${fieldSize}uL).readBytes(bytes, 0u, 0uL, ${fieldSize}uL)")
                builder.appendLine("buffer.writeBytes(bytes, 0u, ${offset}uL, ${fieldSize}uL)")
                builder.unindent()
                builder.appendLine("}")
            }
            fieldType == "Boolean" -> {
                check(fieldLayout.sizeBytes == carrierBytesFor(fieldType)) {
                    "field ${fieldLayout.cName}: C size ${fieldLayout.sizeBytes} != carrier ${carrierBytesFor(fieldType)}"
                }
                builder.appendLine("get() = buffer.readByte(${offset}uL) != 0.toByte()")
                builder.appendLine("set(value) { buffer.writeByte(if (value) 1 else 0, ${offset}uL) }")
            }
            fieldType in memoryScalarPrimitives -> {
                val (read, write) = memoryPrimitives(fieldType)
                val carrierBytes = carrierBytesFor(fieldType)
                check(fieldLayout.sizeBytes == carrierBytes) {
                    "field ${fieldLayout.cName}: C size ${fieldLayout.sizeBytes} != carrier $carrierBytes"
                }
                builder.appendLine("get() = buffer.$read(${offset}uL)")
                builder.appendLine("set(value) { buffer.$write(value, ${offset}uL) }")
            }
            fieldType.endsWith("?") -> {
                val nonOpt = fieldType.removeSuffix("?")
                builder.appendLine("get() = buffer.readPointer(${offset}uL).takeIf { it.rawValue != 0L }?.let { $nonOpt(it) }")
                builder.appendLine("set(value) { buffer.writePointer(value?.handler ?: $nativeAddress(0L), ${offset}uL) }")
            }
            else -> {
                builder.appendLine("get() = buffer.readPointer(${offset}uL).takeIf { it.rawValue != 0L }?.let { $fieldType(it) } ?: error(\"$propertyName is null\")")
                builder.appendLine("set(value) { buffer.writePointer(value.handler, ${offset}uL) }")
            }
        }
    }

    /** Kotlin carrier width in bytes for a scalar mapped type; guards 32-bit (armeabi-v7a) over-reads. */
    private fun carrierBytesFor(fieldType: String): Long = when (fieldType) {
        "Byte", "UByte", "Boolean" -> 1L
        "Short", "UShort" -> 2L
        "Int", "UInt", "Float" -> 4L
        "Long", "ULong", "Double" -> 8L
        else -> error("No carrier width for mapped type $fieldType")
    }

    private fun memoryPrimitives(fieldType: String): Pair<String, String> = when (fieldType) {
        "Byte" -> "readByte" to "writeByte"
        "UByte" -> "readUByte" to "writeUByte"
        "Short" -> "readShort" to "writeShort"
        "UShort" -> "readUShort" to "writeUShort"
        "Int" -> "readInt" to "writeInt"
        "UInt" -> "readUInt" to "writeUInt"
        "Long" -> "readLong" to "writeLong"
        "ULong" -> "readULong" to "writeULong"
        "Float" -> "readFloat" to "writeFloat"
        "Double" -> "readDouble" to "writeDouble"
        else -> error("No memory primitive for mapped type $fieldType")
    }

    private fun memoryPrimitives(scalar: KotlinKmpCAbiType.Scalar): Pair<String, String> =
        when (scalar.kind) {
            KotlinKmpCAbiType.Scalar.Kind.I8,
            KotlinKmpCAbiType.Scalar.Kind.BOOL,
            -> "readByte" to "writeByte"
            KotlinKmpCAbiType.Scalar.Kind.I16,
            KotlinKmpCAbiType.Scalar.Kind.CHAR16,
            -> "readShort" to "writeShort"
            KotlinKmpCAbiType.Scalar.Kind.I32 -> "readInt" to "writeInt"
            KotlinKmpCAbiType.Scalar.Kind.I64 -> "readLong" to "writeLong"
            KotlinKmpCAbiType.Scalar.Kind.F32 -> "readFloat" to "writeFloat"
            KotlinKmpCAbiType.Scalar.Kind.F64 -> "readDouble" to "writeDouble"
        }

    private fun enumMemoryPrimitives(scalar: KotlinKmpCAbiType.Scalar): Triple<String, String, String> =
        when (scalar.kind) {
            KotlinKmpCAbiType.Scalar.Kind.I8 ->
                if (scalar.unsigned) Triple("readUByte", "writeUByte", "value.toUByte()")
                else Triple("readByte", "writeByte", "value")
            KotlinKmpCAbiType.Scalar.Kind.I16 ->
                if (scalar.unsigned) Triple("readUShort", "writeUShort", "value.toUShort()")
                else Triple("readShort", "writeShort", "value")
            KotlinKmpCAbiType.Scalar.Kind.I32 ->
                if (scalar.unsigned) Triple("readUInt", "writeUInt", "value.toUInt()")
                else Triple("readInt", "writeInt", "value")
            KotlinKmpCAbiType.Scalar.Kind.I64 ->
                if (scalar.unsigned) Triple("readULong", "writeULong", "value.toULong()")
                else Triple("readLong", "writeLong", "value")
            else -> error("Unsupported enum carrier ${scalar.kind}")
        }

    private fun isStructType(type: Type): Boolean = when {
        type is Type.Declared -> {
            val tree = type.tree()
            (tree.kind() == Declaration.Scoped.Kind.STRUCT || tree.kind() == Declaration.Scoped.Kind.UNION) &&
                    tree.members().filterIsInstance<Declaration.Variable>().isNotEmpty() &&
                    !ClangUnnamedRecord.isPresent(tree)
        }
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> {
            val inner = type.type()
            isStructType(inner)
        }
        else -> false
    }

    private fun canonicalRecordDeclaration(type: Type): Declaration.Scoped? = when (type) {
        is Type.Declared -> type.tree().takeIf { record ->
            record.kind() == Declaration.Scoped.Kind.STRUCT || record.kind() == Declaration.Scoped.Kind.UNION
        }
        is Type.Delegated -> canonicalRecordDeclaration(type.type())
        is Type.Array -> canonicalRecordDeclaration(type.elementType())
        else -> null
    }

    private fun mapJnaType(type: Type): String {
        return when {
        typeMapper.isEnumType(type) -> "Int"
        type is Type.Primitive -> mapJnaPrimitive(type.kind())
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.UNSIGNED -> {
            val inner = type.type()
            if (inner is Type.Primitive) {
                when (inner.kind()) {
                    Type.Primitive.Kind.Char -> "Byte"
                    Type.Primitive.Kind.Short -> "Short"
                    Type.Primitive.Kind.Int -> "Int"
                    Type.Primitive.Kind.Long, Type.Primitive.Kind.LongLong -> "Long"
                    else -> "Int"
                }
            } else {
                "Int"
            }
        }
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> "com.sun.jna.Pointer?"
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> {
            val inner = type.type()
            when {
                typeMapper.isEnumType(inner) -> "Int"
                isStructType(inner) -> "com.sun.jna.Pointer?"
                inner is Type.Primitive -> mapJnaPrimitive(inner.kind())
                inner is Type.Delegated && inner.kind() == Type.Delegated.Kind.UNSIGNED -> {
                    val innerInner = inner.type()
                    if (innerInner is Type.Primitive) {
                        when (innerInner.kind()) {
                            Type.Primitive.Kind.Char -> "Byte"
                            Type.Primitive.Kind.Short -> "Short"
                            Type.Primitive.Kind.Int -> "Int"
                            Type.Primitive.Kind.Long, Type.Primitive.Kind.LongLong -> "Long"
                            else -> "Int"
                        }
                    } else {
                        "Int"
                    }
                }
                else -> "com.sun.jna.Pointer?"
            }
        }
        type is Type.Declared -> {
            val tree = type.tree()
            if (tree.kind() == Declaration.Scoped.Kind.STRUCT || tree.kind() == Declaration.Scoped.Kind.UNION) {
                "com.sun.jna.Pointer?"
            } else if (tree.kind() == Declaration.Scoped.Kind.ENUM) {
                "Int"
            } else {
                "com.sun.jna.Pointer?"
            }
        }
        else -> "com.sun.jna.Pointer?"
    }
    }

    private fun mapJnaPrimitive(kind: Type.Primitive.Kind): String = when (kind) {
        Type.Primitive.Kind.Bool -> "Int"
        Type.Primitive.Kind.Char -> "Byte"
        Type.Primitive.Kind.Short -> "Short"
        Type.Primitive.Kind.Int -> "Int"
        Type.Primitive.Kind.Long, Type.Primitive.Kind.LongLong -> "Long"
        Type.Primitive.Kind.Float -> "Float"
        Type.Primitive.Kind.Double -> "Double"
        else -> "com.sun.jna.Pointer?"
    }
}
