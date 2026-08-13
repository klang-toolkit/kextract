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
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JNA_LIBRARY
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JNA_NATIVE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JNA_POINTER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JVM_FIELD
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_ALLOCATOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_BUFFER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpSourceSet
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackBindingEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackAndroidEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackModel
import org.graphiks.kextract.kotlin.callbacks.KotlinDirectFunctionBindingModel
import org.graphiks.kextract.kotlin.abi.AndroidFieldLayout
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayout
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayoutPlan
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
    private val jnaBuilder = SourceBuilder()
    private val jnaFunctionsBuilder = SourceBuilder()
    private val files = mutableListOf<KotlinSourceFile>()
    private val generatedNames = mutableSetOf<String>()
    private val callbackTypeNames = callbackModels.mapTo(mutableSetOf(), KotlinCallbackModel::typeName)
    private val androidPackage = if (targetPackage.isEmpty()) "android" else "$targetPackage.android"
    private val typeMapper = KmpTypeMapper(namePlan, arraysAsHolders = false, abiIndex = abiIndex)
    private val nativeAddress = namePlan.runtime(NATIVE_ADDRESS)
    private val cString = namePlan.runtime(C_STRING)
    private val arrayHolder = namePlan.runtime(ARRAY_HOLDER)
    private val memoryAllocator = namePlan.runtime(MEMORY_ALLOCATOR)
    private val memoryBuffer = namePlan.runtime(MEMORY_BUFFER)
    private val jnaPointer = namePlan.runtime(JNA_POINTER)
    private val jnaLibrary = namePlan.runtime(JNA_LIBRARY)
    private val jnaNative = namePlan.runtime(JNA_NATIVE)

    private val excludedBridgeSymbols = setOf(
        KotlinKmpRuntimeSymbol.JNA_LIBRARY,
        KotlinKmpRuntimeSymbol.JNA_NATIVE,
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
        jnaBuilder.appendLine("package $androidPackage")
        jnaBuilder.appendLine()

        KotlinKmpRuntimeSymbol.entries
            .filter { KotlinKmpSourceSet.ANDROID in it.sourceSets }
            .filterNot { it in excludedBridgeSymbols }
            .forEach { builder.appendLine(namePlan.importLine(it)) }
        builder.appendLine()

        listOf(JNA_LIBRARY, JNA_NATIVE, JNA_POINTER)
            .forEach { jnaBuilder.appendLine(namePlan.importLine(it)) }
        jnaBuilder.appendLine()
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
                jnaBuilder.appendLine("internal interface ${className}Library : $jnaLibrary {")
                jnaBuilder.indent()
                jnaBuilder.appendBlock(jnaFunctionsBuilder.toString().trimEnd())
                jnaBuilder.unindent()
                jnaBuilder.appendLine("}")
                jnaBuilder.appendLine()
                jnaBuilder.appendLine("internal val ${className}LibraryInstance: ${className}Library by lazy {")
                jnaBuilder.indent()
                jnaBuilder.appendLine("$jnaNative.load(\"${escapeKotlinString(libraryName)}\", ${className}Library::class.java)")
                jnaBuilder.unindent()
                jnaBuilder.appendLine("}")
                jnaBuilder.appendLine()
                KotlinCallbackAndroidEmitter(
                    typeMapper::mapFunctionType,
                    ::mapJnaType,
                    namePlan,
                ).emit(builder, callbackModels)
                KotlinCallbackBindingEmitter(typeMapper::mapFunctionType, namePlan).emitAndroid(
                    builder,
                    directBindingModels,
                    ::toRawJnaArgument,
                ) { function ->
                    "$androidPackage.${className}LibraryInstance.${namePlan.rawIdentifier(function)}"
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
            files.add(
                KotlinSourceFile(
                    androidPackage,
                    className,
                    jnaBuilder.toString(),
                    sourceRoot = "androidMain/kotlin",
                ),
            )
        }
    }

    override fun visitFunction(decl: Declaration.Function) {
        if (Skip.isPresent(decl)) return
        val name = namePlan.declaration(decl)
        val rawName = namePlan.rawIdentifier(decl)
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        val params = decl.parameters().joinToString(", ") { param ->
            "${namePlan.parameter(param)}: ${typeMapper.mapFunctionType(param.type())}"
        }
        val rawParams = decl.parameters().joinToString(", ") { param ->
            "${namePlan.parameter(param)}: ${rawJnaFunctionType(param.type())}"
        }
        val rawReturnType = rawJnaFunctionType(decl.type().returnType())
        jnaFunctionsBuilder.appendLine("fun $rawName($rawParams): $rawReturnType")

        val rawArguments = decl.parameters().joinToString(", ") { param ->
            toRawJnaArgument(namePlan.parameter(param), param.type())
        }
        val call = "$androidPackage.${className}LibraryInstance.$rawName($rawArguments)"
        builder.appendLine("actual fun $name($params): $returnType {")
        builder.indent()
        emitFunctionReturn(decl.type().returnType(), returnType, call)
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

    private fun rawJnaFunctionType(type: Type): String =
        if (type is Type.Primitive && type.kind() == Type.Primitive.Kind.Void) {
            "Unit"
        } else {
            mapJnaType(type)
        }

    private fun toRawJnaArgument(name: String, type: Type): String {
        val kmpType = typeMapper.mapFunctionType(type)
        val rawType = rawJnaFunctionType(type)
        return when {
            typeMapper.isOptionsEnumType(type) ->
                abiIndex.enum(typeMapper.enumDeclaration(type)).optionsRawToJvmCarrier("$name.rawValue")
            typeMapper.isEnumType(type) ->
                abiIndex.enum(typeMapper.enumDeclaration(type)).toJvmCarrier(name)
            returnsStructByValue(type) -> {
                // TODO(M5.3): emit struct-by-value downcalls through the engine; currently transitional JNA emission is uncompilable.
                val record = requireNotNull(canonicalRecordDeclaration(type))
                val rawByValue =
                    "$androidPackage.${namePlan.declaration(record)}.${namePlan.jnaByValue(record)}"
                "$rawByValue($name.handler).apply { read() }"
            }
            rawType == "$jnaPointer?" && kmpType in setOf(nativeAddress, "$nativeAddress?") -> name
            rawType == "$jnaPointer?" && kmpType == "$cString?" -> "$name?.handler"
            rawType == "$jnaPointer?" && kmpType.startsWith(arrayHolder) -> "$name?.handler"
            rawType == "$jnaPointer?" && kmpType.endsWith("?") -> "$name?.handler"
            rawType == "$jnaPointer?" -> "$name.handler"
            rawType == "Int" && kmpType == "Boolean" -> "if ($name) 1 else 0"
            rawType == "Int" && kmpType == "UInt" -> "$name.toInt()"
            rawType == "Long" && kmpType == "ULong" -> "$name.toLong()"
            rawType == "Short" && kmpType == "UShort" -> "$name.toShort()"
            rawType == "Byte" && kmpType == "UByte" -> "$name.toByte()"
            else -> name
        }
    }

    private fun emitFunctionReturn(type: Type, returnType: String, call: String) {
        if (returnType == "Unit") {
            builder.appendLine(call)
            builder.appendLine("return")
            return
        }
        val rawType = rawJnaFunctionType(type)
        when {
            typeMapper.isOptionsEnumType(type) -> {
                val scalar = abiIndex.enum(typeMapper.enumDeclaration(type))
                builder.appendLine(
                    "return $returnType(${scalar.jvmCarrierToOptionsRaw(call)})",
                )
            }
            typeMapper.isEnumType(type) -> {
                val scalar = abiIndex.enum(typeMapper.enumDeclaration(type))
                builder.appendLine("return ${scalar.fromJvmCarrier(call)}")
            }
            returnType == "$nativeAddress?" -> builder.appendLine("return $call")
            returnType == "$cString?" -> builder.appendLine("return $call?.let(::$cString)")
            returnType.endsWith("?") && returnsPointer(type) -> {
                val nonNullable = returnType.removeSuffix("?")
                builder.appendLine("return $call?.let { $nonNullable(it) }")
            }
            returnsStructByValue(type) ->
                // TODO(M5.3): emit struct-by-value downcalls through the engine; currently transitional JNA emission is uncompilable.
                builder.appendLine("return $returnType.ByValue($call)")
            rawType == "Int" && returnType == "Boolean" -> builder.appendLine("return $call != 0")
            rawType == "Int" && returnType == "UInt" -> builder.appendLine("return $call.toUInt()")
            rawType == "Long" && returnType == "ULong" -> builder.appendLine("return $call.toULong()")
            rawType == "Short" && returnType == "UShort" -> builder.appendLine("return $call.toUShort()")
            rawType == "Byte" && returnType == "UByte" -> builder.appendLine("return $call.toUByte()")
            else -> builder.appendLine("return $call")
        }
    }

    private fun returnsStructByValue(type: Type): Boolean =
        !returnsPointer(type) && canonicalRecordDeclaration(type) != null

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
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> "$jnaPointer?"
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> {
            val inner = type.type()
            when {
                typeMapper.isEnumType(inner) -> "Int"
                isStructType(inner) -> "$jnaPointer?"
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
                else -> "$jnaPointer?"
            }
        }
        type is Type.Declared -> {
            val tree = type.tree()
            if (tree.kind() == Declaration.Scoped.Kind.STRUCT || tree.kind() == Declaration.Scoped.Kind.UNION) {
                "$jnaPointer?"
            } else if (tree.kind() == Declaration.Scoped.Kind.ENUM) {
                "Int"
            } else {
                "$jnaPointer?"
            }
        }
        else -> "$jnaPointer?"
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
        else -> "$jnaPointer?"
    }
}
