@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinJvmNativeBundleIndex
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.ARRAY_HOLDER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRUCTURE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.FIND_OR_THROW
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.FUNCTION_DESCRIPTOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.GROUP_ELEMENT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.GROUP_LAYOUT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JVM_INLINE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.LINKER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_ALLOCATOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_LAYOUT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_SEGMENT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.METHOD_HANDLE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.SEGMENT_ALLOCATOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.ARENA
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.VALUE_LAYOUT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.VAR_HANDLE
import org.graphiks.kextract.kotlin.KotlinKmpSourceSet
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackBindingEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackJvmEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackModel
import org.graphiks.kextract.kotlin.callbacks.KotlinDirectFunctionBindingModel
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import org.graphiks.kextract.pipeline.LayoutUtils
import org.graphiks.kextract.kotlin.utils.TypeMapper
import org.graphiks.kextract.pipeline.isStructOrUnion
import org.graphiks.kextract.pipeline.isEnum
import org.graphiks.kextract.pipeline.Options
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiContext
import org.graphiks.kextract.kotlin.abi.KotlinKmpCAbiType
import org.graphiks.kextract.kotlin.utils.KotlinIdentifierAllocator

internal class KotlinKmpJvmBuilder(
    private val targetPackage: String,
    private val className: String,
    private val callbackModels: List<KotlinCallbackModel>,
    private val directBindingModels: List<KotlinDirectFunctionBindingModel>,
    private val namePlan: KotlinKmpNamePlan,
    private val recordLayouts: KotlinJvmRecordLayoutPlan,
    private val abiIndex: KotlinKmpAbiIndex,
    private val libraries: List<Options.Library>,
    private val jvmNativeBundleIndex: KotlinJvmNativeBundleIndex,
    private val privateNames: KotlinIdentifierAllocator,
) : Declaration.Visitor<Unit> {

    private val builder = SourceBuilder()
    private val files = mutableListOf<KotlinSourceFile>()
    private val generatedNames = mutableSetOf<String>()
    private val generatedStructNames = mutableSetOf<String>()
    private val genericStructDeclarations = linkedSetOf<Declaration.Scoped>()
    private val callbackTypeNames = callbackModels.mapTo(mutableSetOf(), KotlinCallbackModel::typeName)
    private val typeMapper = KmpTypeMapper(
        namePlan,
        abiIndex = abiIndex,
    )
    private val nativeAddress = namePlan.runtime(NATIVE_ADDRESS)
    private val jvmDowncallEngine = "org.graphiks.kffi.engine.JvmDowncallEngine"
    private val cString = namePlan.runtime(C_STRING)
    private val arrayHolder = namePlan.runtime(ARRAY_HOLDER)
    private val memoryAllocator = namePlan.runtime(MEMORY_ALLOCATOR)
    private val memorySegment = namePlan.runtime(MEMORY_SEGMENT)
    private val cStructure = namePlan.runtime(C_STRUCTURE)
    private val varHandle = namePlan.runtime(VAR_HANDLE)
    private val groupElement = namePlan.runtime(GROUP_ELEMENT)
    private val memoryLayout = namePlan.runtime(MEMORY_LAYOUT)
    private val valueLayout = namePlan.runtime(VALUE_LAYOUT)
    private val groupLayout = namePlan.runtime(GROUP_LAYOUT)
    private val nativeBootstrapName = libraries.takeIf { it.isNotEmpty() }?.let {
        privateNames.allocate("KextractNativeBootstrap", "nativeBootstrap")
    }

    init {
        if (targetPackage.isNotEmpty()) {
            builder.appendLine("package $targetPackage")
            builder.appendLine()
        }

        KotlinKmpRuntimeSymbol.entries
            .filter { KotlinKmpSourceSet.JVM in it.sourceSets }
            .forEach { builder.appendLine(namePlan.importLine(it)) }
        builder.appendLine()
        nativeBootstrapName?.let { bootstrapName ->
            KotlinJvmNativeBootstrapEmitter(
                libraries = libraries,
                bundleIndex = jvmNativeBundleIndex,
                bootstrapName = bootstrapName,
                delegateResolverName = namePlan.runtime(FIND_OR_THROW),
                memorySegmentName = memorySegment,
            ).emit(builder)
        }
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
                generatedStructNames.add(structName)
                if (structName == "WGPUNativeDisplayHandle") {
                    emitNativeDisplayHandle(decl)
                    return
                }

                builder.appendLine("actual interface $structName : $cStructure {")
                builder.indent()

                val fields = decl.members().filterIsInstance<Declaration.Variable>().filterNot(Skip::isPresent)

                // Visit members as actual properties
                fields.forEach { field ->
                    val fieldName = namePlan.member(field)
                    val fieldType = typeMapper.mapType(field.type())
                    if (fieldType == cString) {
                        builder.appendLine("actual var $fieldName: $cString?")
                    } else if (fieldType.startsWith(arrayHolder)) {
                        builder.appendLine("actual var $fieldName: $fieldType?")
                    } else if (fieldType.endsWith("?") || fieldType == nativeAddress) {
                        builder.appendLine("actual var $fieldName: $fieldType")
                    } else {
                        builder.appendLine("actual var $fieldName: $fieldType")
                    }
                }

                builder.appendLine("actual override val handler: $nativeAddress")

                // Companion object
                builder.appendLine("actual companion object {")
                builder.indent()

                // Define layout
                emitGroupLayout(decl)
                builder.appendLine()

                // VarHandles for value fields
                fields.forEach { field ->
                    val fieldName = namePlan.member(field)
                    val cFieldName = field.name()
                    val isArray = isArrayType(field.type())
                    val isStruct = typeMapper.isInlineStructOrUnion(field.type())
                    if (!isArray && !isStruct) {
                        builder.appendLine("val ${fieldName}_VH: $varHandle = layout.varHandle($groupElement(\"$cFieldName\"))")
                    }
                }
                builder.appendLine()

                builder.appendLine("actual operator fun invoke(address: $nativeAddress): $structName = ByReference(address)")
                builder.appendLine("actual fun allocate(allocator: $memoryAllocator): $structName = ByReference(allocator.allocate(layout.byteSize()))")
                builder.appendLine("actual fun allocateArray(allocator: $memoryAllocator, size: UInt, provider: (UInt, $structName) -> Unit): $arrayHolder<$structName> {")
                builder.indent()
                builder.appendLine("val byteSize = layout.byteSize()")
                builder.appendLine("val segment = allocator.allocate(byteSize * size.toLong())")
                builder.appendLine("for (i in 0 until size.toInt()) {")
                builder.indent()
                builder.appendLine("val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::$nativeAddress)")
                builder.appendLine("provider(i.toUInt(), ByReference(slice))")
                builder.unindent()
                builder.appendLine("}")
                builder.appendLine("return $arrayHolder(segment)")
                builder.unindent()
                builder.appendLine("}")
                builder.unindent()
                builder.appendLine("}") // End companion object

                // @JvmInline value class ByReference implementation
                builder.appendLine()
                builder.appendLine("@${namePlan.runtime(JVM_INLINE)}")
                builder.appendLine("value class ByReference(override val handler: $nativeAddress) : $structName {")
                builder.indent()

                fields.forEach { field ->
                    val fieldName = namePlan.member(field)
                    val cFieldName = field.name()
                    val fieldType = typeMapper.mapType(field.type())
                    val isArray = isArrayType(field.type())
                    val isStruct = typeMapper.isInlineStructOrUnion(field.type())

                    if (isArray) {
                        // Array field using asSlice
                        builder.appendLine("override var $fieldName: $fieldType")
                        builder.indent()
                        builder.appendLine("get() = handler.handler.asSlice(Companion.layout.byteOffset($groupElement(\"$cFieldName\")), Companion.layout.select($groupElement(\"$cFieldName\")).byteSize()).let(::$nativeAddress)")
                        builder.appendLine("set(value) {")
                        builder.indent()
                        builder.appendLine("$memorySegment.copy(value.handler, 0L, handler.handler, Companion.layout.byteOffset($groupElement(\"$cFieldName\")), Companion.layout.select($groupElement(\"$cFieldName\")).byteSize())")
                        builder.unindent()
                        builder.appendLine("}")
                        builder.unindent()
                    } else if (isStruct) {
                        val nonOptType = fieldType.removeSuffix("?")
                        builder.appendLine("override var $fieldName: $fieldType")
                        builder.indent()
                        builder.appendLine("get() = $nonOptType($nativeAddress(handler.handler.asSlice(Companion.layout.byteOffset($groupElement(\"$cFieldName\")), Companion.layout.select($groupElement(\"$cFieldName\")).byteSize())))")
                        builder.appendLine("set(value) {")
                        builder.indent()
                        builder.appendLine("$memorySegment.copy(value.handler.handler, 0L, handler.handler, Companion.layout.byteOffset($groupElement(\"$cFieldName\")), Companion.layout.select($groupElement(\"$cFieldName\")).byteSize())")
                        builder.unindent()
                        builder.appendLine("}")
                        builder.unindent()
                    } else {
                        // VarHandle based get/set
                        val propType = if (fieldType == cString) "$cString?" else fieldType
                        builder.appendLine("override var $fieldName: $propType")
                        builder.indent()
                        val canonical = typeMapper.canonicalKmpType(field.type())
                        val optionsScalar = field.type()
                            .takeIf(typeMapper::isOptionsEnumType)
                            ?.let(typeMapper::enumDeclaration)
                            ?.let(abiIndex::enum)
                        when {
                            fieldType == cString -> {
                                builder.appendLine("get() = (${fieldName}_VH.get(handler.handler, 0L) as? $memorySegment)?.let(::$nativeAddress)?.let(::$cString)")
                                builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value?.handler?.handler ?: $memorySegment.NULL)")
                            }
                            fieldType == nativeAddress -> {
                                builder.appendLine("get() = (${fieldName}_VH.get(handler.handler, 0L) as? $memorySegment)?.let(::$nativeAddress) ?: $nativeAddress($memorySegment.NULL)")
                                builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value.handler)")
                            }
                            fieldType == "$nativeAddress?" -> {
                                builder.appendLine("get() = (${fieldName}_VH.get(handler.handler, 0L) as? $memorySegment)?.takeIf { it != $memorySegment.NULL }?.let(::$nativeAddress)")
                                builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value?.handler ?: $memorySegment.NULL)")
                            }
                            optionsScalar != null -> {
                                val rawExpression =
                                    "${fieldName}_VH.get(handler.handler, 0L) as ${optionsScalar.jvmCarrier}"
                                builder.appendLine(
                                    "get() = $fieldType(${optionsScalar.jvmCarrierToOptionsRaw(rawExpression)})",
                                )
                                builder.appendLine(
                                    "set(value) = ${fieldName}_VH.set(handler.handler, 0L, " +
                                        "${optionsScalar.optionsRawToJvmCarrier("value.rawValue")})",
                                )
                            }
                            canonical == "Boolean" -> {
                                builder.appendLine("get() = ${fieldName}_VH.get(handler.handler, 0L) as Boolean")
                                builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value)")
                            }
                            canonical == "UInt" -> {
                                builder.appendLine("get() = (${fieldName}_VH.get(handler.handler, 0L) as Int).toUInt() as $fieldType")
                                builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value.toInt())")
                            }
                            canonical == "ULong" -> {
                                builder.appendLine("get() = (${fieldName}_VH.get(handler.handler, 0L) as Long).toULong() as $fieldType")
                                builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value.toLong())")
                            }
                            canonical == "UShort" -> {
                                builder.appendLine("get() = (${fieldName}_VH.get(handler.handler, 0L) as Short).toUShort() as $fieldType")
                                builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value.toShort())")
                            }
                            canonical == "UByte" -> {
                                builder.appendLine("get() = (${fieldName}_VH.get(handler.handler, 0L) as Byte).toUByte() as $fieldType")
                                builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value.toByte())")
                            }
                            else -> {
                                if (fieldType.endsWith("?")) {
                                    val nonOptType = fieldType.removeSuffix("?")
                                    builder.appendLine("get() = (${fieldName}_VH.get(handler.handler, 0L) as? $memorySegment)?.let(::$nativeAddress)?.let { $nonOptType(it) }")
                                    builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value?.handler?.handler ?: $memorySegment.NULL)")
                                } else {
                                    builder.appendLine("get() = ${fieldName}_VH.get(handler.handler, 0L) as $fieldType")
                                    builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value)")
                                }
                            }
                        }
                        builder.unindent()
                    }
                }

                builder.unindent()
                builder.appendLine("}") // End value class ByReference

                builder.unindent()
                builder.appendLine("}") // End actual interface
                builder.appendLine()
            }
            Declaration.Scoped.Kind.TOPLEVEL -> {
                for (member in decl.members()) {
                    member.accept(this)
                }
                KotlinCallbackJvmEmitter(typeMapper::mapFunctionType, namePlan).emit(builder, callbackModels)
                KotlinCallbackBindingEmitter(typeMapper::mapFunctionType, namePlan).emitJvm(
                    builder,
                    directBindingModels,
                    ::toRawJvmArgument,
                )
                emitJvmEngineStructRegistrations()
            }
            else -> {}
        }

        if (decl.kind() == Declaration.Scoped.Kind.TOPLEVEL) {
            files.add(
                KotlinSourceFile(
                    targetPackage,
                    className + "Jvm",
                    builder.toString().trimEnd() + "\n",
                    sourceRoot = "jvmMain/kotlin",
                ),
            )
        }
    }

    private fun isArrayType(type: Type): Boolean = when {
        type is Type.Array -> true
        type is Type.Delegated -> isArrayType(type.type())
        else -> false
    }

    private fun emitNativeDisplayHandle(decl: Declaration.Scoped) {
        val unionField = inlineUnionField(decl)
        val fields = decl.members()
            .filterIsInstance<Declaration.Variable>()
            .filterNot { it == unionField }
        val union = unionField?.type()?.let(typeMapper::declaredUnion)
            ?: decl.members()
                .filterIsInstance<Declaration.Scoped>()
                .firstOrNull { it.kind() == Declaration.Scoped.Kind.UNION }
            ?: return
        val unionFields = union.members().filterIsInstance<Declaration.Variable>()
        val unionOffsetBits = unionField
            ?.let { org.graphiks.kextract.DeclarationImpl.ClangOffsetOf.get(it) }
            ?: org.graphiks.kextract.DeclarationImpl.AnonymousStruct.getOrThrow(union).offset
            ?: 0L
        val unionSizeBits = unionField
            ?.let { org.graphiks.kextract.DeclarationImpl.ClangSizeOf.get(it) }
            ?: org.graphiks.kextract.DeclarationImpl.ClangSizeOf.get(union)
            ?: 0L
        val structSizeBits = org.graphiks.kextract.DeclarationImpl.ClangSizeOf.get(decl) ?: 0L

        builder.appendLine("actual interface WGPUNativeDisplayHandle : $cStructure {")
        builder.indent()

        fields.forEach { field ->
            builder.appendLine("actual var ${namePlan.member(field)}: ${typeMapper.mapType(field.type())}")
        }
        unionFields.forEach { field ->
            val fieldType = typeMapper.mapType(field.type())
            val setter = field.name().replaceFirstChar { it.titlecase() }
            builder.appendLine("actual val ${namePlan.member(field)}: $fieldType?")
            builder.appendLine("actual fun set$setter(value: $fieldType)")
        }
        builder.appendLine("actual override val handler: $nativeAddress")

        builder.appendLine("actual companion object {")
        builder.indent()
        builder.appendLine("val layout: $groupLayout = $memoryLayout.structLayout(")
        builder.indent()
        var currentOffsetBits = 0L
        fields.forEach { field ->
            val offsetBits = org.graphiks.kextract.DeclarationImpl.ClangOffsetOf.get(field) ?: currentOffsetBits
            if (offsetBits > currentOffsetBits) {
                val paddingBytes = (offsetBits - currentOffsetBits) / 8
                if (paddingBytes > 0) builder.appendLine("$memoryLayout.paddingLayout($paddingBytes),")
            }
            builder.appendLine("${planJvmRuntimeNames(LayoutUtils.layoutString(field.type(), abiIndex))}.withName(\"${field.name()}\"),")
            currentOffsetBits = offsetBits + (org.graphiks.kextract.DeclarationImpl.ClangSizeOf.get(field) ?: 0L)
        }
        if (unionOffsetBits > currentOffsetBits) {
            val paddingBytes = (unionOffsetBits - currentOffsetBits) / 8
            if (paddingBytes > 0) builder.appendLine("$memoryLayout.paddingLayout($paddingBytes),")
        }
        builder.appendLine("$memoryLayout.sequenceLayout(${unionSizeBits / 8L}, $valueLayout.JAVA_BYTE).withName(\"value\")${if (structSizeBits > unionOffsetBits + unionSizeBits) "," else ""}")
        currentOffsetBits = unionOffsetBits + unionSizeBits
        if (structSizeBits > currentOffsetBits) {
            val paddingBytes = (structSizeBits - currentOffsetBits) / 8
            if (paddingBytes > 0) builder.appendLine("$memoryLayout.paddingLayout($paddingBytes)")
        }
        builder.unindent()
        builder.appendLine(").withName(\"WGPUNativeDisplayHandle\")")
        builder.appendLine()
        fields.forEach { field ->
            builder.appendLine("val ${namePlan.member(field)}_VH: $varHandle = layout.varHandle($groupElement(\"${field.name()}\"))")
        }
        builder.appendLine("private val valueOffset: Long = layout.byteOffset($groupElement(\"value\"))")
        builder.appendLine()
        builder.appendLine("actual operator fun invoke(address: $nativeAddress): WGPUNativeDisplayHandle = ByReference(address)")
        builder.appendLine("actual fun allocate(allocator: $memoryAllocator): WGPUNativeDisplayHandle = ByReference(allocator.allocate(layout.byteSize()))")
        builder.appendLine("actual fun allocateArray(allocator: $memoryAllocator, size: UInt, provider: (UInt, WGPUNativeDisplayHandle) -> Unit): $arrayHolder<WGPUNativeDisplayHandle> {")
        builder.indent()
        builder.appendLine("val byteSize = layout.byteSize()")
        builder.appendLine("val segment = allocator.allocate(byteSize * size.toLong())")
        builder.appendLine("for (i in 0 until size.toInt()) {")
        builder.indent()
        builder.appendLine("val slice = segment.handler.asSlice(i.toLong() * byteSize, byteSize).let(::$nativeAddress)")
        builder.appendLine("provider(i.toUInt(), ByReference(slice))")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("return $arrayHolder(segment)")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")

        builder.appendLine()
        builder.appendLine("@${namePlan.runtime(JVM_INLINE)}")
        builder.appendLine("value class ByReference(override val handler: $nativeAddress) : WGPUNativeDisplayHandle {")
        builder.indent()
        fields.forEach { field ->
            val fieldType = typeMapper.mapType(field.type())
            val fieldName = namePlan.member(field)
            builder.appendLine("override var $fieldName: $fieldType")
            builder.indent()
            builder.appendLine("get() = (${fieldName}_VH.get(handler.handler, 0L) as Int).toUInt() as $fieldType")
            builder.appendLine("set(value) = ${fieldName}_VH.set(handler.handler, 0L, value.toInt())")
            builder.unindent()
        }
        unionFields.forEach { field ->
            val fieldName = namePlan.member(field)
            val fieldType = typeMapper.mapType(field.type())
            val setter = fieldName.replaceFirstChar { it.titlecase() }
            val discriminator = "WGPUNativeDisplayHandleType_$setter"
            builder.appendLine("override val $fieldName: $fieldType?")
            builder.indent()
            builder.appendLine("get() = if (type == $discriminator) $fieldType($nativeAddress(handler.handler.asSlice(valueOffset, $fieldType.layout.byteSize()))) else null")
            builder.unindent()
            builder.appendLine("override fun set$setter(value: $fieldType) {")
            builder.indent()
            builder.appendLine("type = $discriminator")
            builder.appendLine("$memorySegment.copy(value.handler.handler, 0L, handler.handler, valueOffset, $fieldType.layout.byteSize())")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.unindent()
        builder.appendLine("}")

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    override fun visitFunction(decl: Declaration.Function) {
        if (Skip.isPresent(decl)) return
        emitFunction(decl)
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

    private fun emitGroupLayout(
        decl: Declaration.Scoped,
    ) {
        recordLayouts[decl].render(builder)
    }

    private fun emitFunction(decl: Declaration.Function) {
        if (usesGenericJvmDowncall(decl)) {
            emitGenericFunction(decl)
        } else {
            emitDirectFunction(decl)
        }
    }

    private fun emitDirectFunction(decl: Declaration.Function) {
        val name = namePlan.declaration(decl)
        val cName = decl.name()
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        val params = decl.parameters().map { param ->
            val paramName = namePlan.parameter(param)
            "$paramName: ${typeMapper.mapFunctionType(param.type())}"
        }
        val rawArgs = decl.parameters().map { param ->
            val paramName = namePlan.parameter(param)
            toRawJvmArgument(paramName, param.type())
        }
        val invokeArgs = if (returnsStructByValue(decl.type().returnType())) {
            listOf("(${namePlan.runtime(ARENA)}.ofAuto() as ${namePlan.runtime(SEGMENT_ALLOCATOR)})") + rawArgs
        } else {
            rawArgs
        }.joinToString(", ")
        val invoke = "${name}_HANDLE.invokeExact($invokeArgs)"
        builder.appendLine("private val ${name}_DESC: ${namePlan.runtime(FUNCTION_DESCRIPTOR)} = ${planJvmRuntimeNames(LayoutUtils.functionDescriptorString(decl.type(), abiIndex))}")
        val resolver = nativeBootstrapName?.let { "$it.resolve" } ?: namePlan.runtime(FIND_OR_THROW)
        builder.appendLine("private val ${name}_ADDR: $memorySegment by lazy { $resolver(\"$cName\") }")
        builder.appendLine("private val ${name}_HANDLE: ${namePlan.runtime(METHOD_HANDLE)} by lazy { ${namePlan.runtime(LINKER)}.nativeLinker().downcallHandle(${name}_ADDR, ${name}_DESC) }")
        builder.appendLine("actual fun $name(${params.joinToString(", ")}): $returnType {")
        builder.indent()
        emitFunctionReturn(decl.type().returnType(), returnType, invoke)
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitGenericFunction(decl: Declaration.Function) {
        val name = namePlan.declaration(decl)
        val cName = decl.name()
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        val params = decl.parameters().map { param ->
            val paramName = namePlan.parameter(param)
            "$paramName: ${typeMapper.mapFunctionType(param.type())}"
        }
        val rawArgs = decl.parameters().map { param ->
            val paramName = namePlan.parameter(param)
            toRawJvmGenericArgument(paramName, param.type())
        }
        rememberGenericStructs(decl.type().returnType())
        decl.type().argumentTypes().forEach(::rememberGenericStructs)
        val shape = buildString {
            append("$jvmDowncallEngine.FunctionShape(")
            append("result = ")
            append(jvmAbiTypeExpression(decl.type().returnType()))
            append(", arguments = listOf(")
            append(decl.type().argumentTypes().joinToString(", ") { jvmAbiTypeExpression(it) })
            append("))")
        }
        builder.appendLine("private val ${name}_SHAPE: $jvmDowncallEngine.FunctionShape = $shape")
        builder.appendLine("private val ${name}_ADDR: Long by lazy { $jvmDowncallEngine.resolveSymbol(\"$cName\") }")
        val invokeArgs = listOf("${name}_ADDR", "${name}_SHAPE") + rawArgs
        val invoke = "$jvmDowncallEngine.callGeneric(${invokeArgs.joinToString(", ")})"
        builder.appendLine("actual fun $name(${params.joinToString(", ")}): $returnType {")
        builder.indent()
        emitFunctionReturn(decl.type().returnType(), returnType, invoke)
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun usesGenericJvmDowncall(decl: Declaration.Function): Boolean =
        directBindingModels.none { it.binding.function === decl } &&
            (isGenericStructValue(decl.type().returnType()) ||
                decl.type().argumentTypes().any(::isGenericStructValue))

    private fun isGenericStructValue(type: Type): Boolean {
        val abi = runCatching { KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.DIRECT) }
            .getOrNull() ?: return false
        return abi is KotlinKmpCAbiType.StructValue &&
            abi.declaration.kind() == Declaration.Scoped.Kind.STRUCT
    }

    private fun jvmAbiTypeExpression(type: Type): String {
        if (type is Type.Primitive && type.kind() == Type.Primitive.Kind.Void) {
            return "$jvmDowncallEngine.AbiType.Void"
        }
        val abi = try {
            KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.DIRECT)
        } catch (unsupported: UnsupportedOperationException) {
            if (type is Type.Primitive && type.kind() == Type.Primitive.Kind.Long) {
                KotlinKmpCAbiType.Scalar(KotlinKmpCAbiType.Scalar.Kind.I64, unsigned = false)
            } else {
                throw unsupported
            }
        }
        val prefix = "$jvmDowncallEngine.AbiType"
        return when (abi) {
            is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
                KotlinKmpCAbiType.Scalar.Kind.BOOL -> "$prefix.Bool"
                KotlinKmpCAbiType.Scalar.Kind.I8 -> "$prefix.I8"
                KotlinKmpCAbiType.Scalar.Kind.I16 -> "$prefix.I16"
                KotlinKmpCAbiType.Scalar.Kind.I32 -> "$prefix.I32"
                KotlinKmpCAbiType.Scalar.Kind.I64 -> "$prefix.I64"
                KotlinKmpCAbiType.Scalar.Kind.CHAR16 -> "$prefix.Char16"
                KotlinKmpCAbiType.Scalar.Kind.F32 -> "$prefix.F32"
                KotlinKmpCAbiType.Scalar.Kind.F64 -> "$prefix.F64"
            }
            is KotlinKmpCAbiType.Address -> "$prefix.Pointer"
            is KotlinKmpCAbiType.StructValue ->
                "$prefix.Struct(\"${namePlan.declaration(abi.declaration)}\")"
        }
    }

    private fun toRawJvmGenericArgument(name: String, type: Type): String {
        val kmpType = typeMapper.mapFunctionType(type)
        val rawType = rawJvmType(type)
        return when {
            rawType != memorySegment -> toRawJvmArgument(name, type)
            kmpType == "$nativeAddress?" -> name
            kmpType == nativeAddress -> name
            kmpType.endsWith("?") -> "$name?.handler"
            kmpType == memorySegment -> name
            else -> "$name.handler"
        }
    }

    private fun emitJvmEngineStructRegistrations() {
        genericStructDeclarations.forEach { declaration ->
            emitJvmEngineStructRegistration(recordLayouts[declaration])
        }
    }

    private fun emitJvmEngineStructRegistration(layout: KotlinJvmRecordLayout) {
        val layoutName = namePlan.declaration(layout.declaration)
        val registrationName = privateNames.allocate("${layoutName}JvmLayoutRegistration", "jvmLayoutRegistration")
        val fields = buildList {
            var previousEnd = 0L
            layout.members.forEach { member ->
                val gap = member.offsetBytes - previousEnd
                if (gap > 0L) {
                    add(
                        "$jvmDowncallEngine.StructField(" +
                            "cName = \"__padding_${size}\", " +
                            "kind = $jvmDowncallEngine.FieldKind.PADDING, " +
                            "offsetBytes = $gap",
                    )
                }
                add(renderJvmEngineField(member))
                previousEnd = member.offsetBytes + member.sizeBytes
            }
            val finalPadding = layout.sizeBytes - previousEnd
            if (finalPadding > 0L) {
                add(
                    "$jvmDowncallEngine.StructField(" +
                        "cName = \"__padding_${size}\", " +
                        "kind = $jvmDowncallEngine.FieldKind.PADDING, " +
                        "offsetBytes = $finalPadding",
                )
            }
        }
        builder.appendLine("private val $registrationName = $jvmDowncallEngine.registerStructLayout(")
        builder.indent()
        builder.appendLine("name = \"$layoutName\",")
        builder.appendLine("sizeBytes = $layoutName.layout.byteSize(),")
        builder.appendLine("alignmentBytes = $layoutName.layout.byteAlignment(),")
        builder.appendLine("fields = listOf(")
        builder.indent()
        fields.forEachIndexed { index, field ->
            val comma = if (index < fields.lastIndex) "," else ""
            builder.appendLine("$field)$comma")
        }
        builder.unindent()
        builder.appendLine(")")
        builder.unindent()
        builder.appendLine(")")
    }

    private fun rememberGenericStructs(type: Type) {
        arrayType(type)?.let { array ->
            rememberGenericStructs(array.elementType())
            return
        }
        val abi = runCatching { KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.DIRECT) }
            .getOrNull() ?: return
        if (abi is KotlinKmpCAbiType.StructValue &&
            abi.declaration.kind() == Declaration.Scoped.Kind.STRUCT &&
            genericStructDeclarations.add(abi.declaration)
        ) {
            abi.declaration.members()
                .filterIsInstance<Declaration.Variable>()
                .forEach { field -> rememberGenericStructs(field.type()) }
        }
    }

    private fun renderJvmEngineField(member: KotlinJvmRecordMemberLayout): String {
        val array = arrayType(member.field.type())
        if (array != null) {
            val element = engineFieldKind(array.elementType())
            val elementName = engineFieldStructName(array.elementType())
            return "$jvmDowncallEngine.StructField(" +
                "cName = \"${member.cName}\", " +
                "kind = $jvmDowncallEngine.FieldKind.ARRAY, " +
                "offsetBytes = ${member.offsetBytes}L, " +
                "arrayElementKind = $jvmDowncallEngine.FieldKind.$element, " +
                "arrayLength = ${array.elementCount() ?: 0L}L" +
                (elementName?.let { ", arrayElementName = \"$it\"" } ?: "")
        }
        val kind = engineFieldKind(member.field.type())
        val structName = engineFieldStructName(member.field.type())
        return "$jvmDowncallEngine.StructField(" +
            "cName = \"${member.cName}\", " +
            "kind = $jvmDowncallEngine.FieldKind.$kind, " +
            "offsetBytes = ${member.offsetBytes}L" +
            (structName?.let { ", structName = \"$it\"" } ?: "")
    }

    private fun engineFieldKind(type: Type): String = when (val abi = KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.FIELD)) {
        is KotlinKmpCAbiType.Scalar -> when (abi.kind) {
            KotlinKmpCAbiType.Scalar.Kind.BOOL,
            KotlinKmpCAbiType.Scalar.Kind.I8 -> "INT8"
            KotlinKmpCAbiType.Scalar.Kind.I16,
            KotlinKmpCAbiType.Scalar.Kind.CHAR16 -> "INT16"
            KotlinKmpCAbiType.Scalar.Kind.I32 -> "INT32"
            KotlinKmpCAbiType.Scalar.Kind.I64 -> "INT64"
            KotlinKmpCAbiType.Scalar.Kind.F32 -> "FLOAT32"
            KotlinKmpCAbiType.Scalar.Kind.F64 -> "FLOAT64"
        }
        is KotlinKmpCAbiType.Address -> "POINTER"
        is KotlinKmpCAbiType.StructValue -> "STRUCT"
    }

    private fun engineFieldStructName(type: Type): String? =
        (KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.FIELD) as? KotlinKmpCAbiType.StructValue)
            ?.declaration
            ?.let(namePlan::declaration)

    private fun arrayType(type: Type): Type.Array? = when (type) {
        is Type.Array -> type
        is Type.Delegated -> arrayType(type.type())
        else -> null
    }

    private fun emitFunctionReturn(type: Type, returnType: String, invoke: String) {
        if (returnType == "Unit") {
            builder.appendLine(invoke)
            builder.appendLine("return")
            return
        }
        val rawType = rawJvmType(type)
        when {
            typeMapper.isEnumType(type) -> {
                val scalar = abiIndex.enum(typeMapper.enumDeclaration(type))
                val rawExpression = "$invoke as ${scalar.jvmCarrier}"
                if (typeMapper.isOptionsEnumType(type)) {
                    builder.appendLine(
                        "return $returnType(${scalar.jvmCarrierToOptionsRaw(rawExpression)})",
                    )
                } else {
                    builder.appendLine("return ${scalar.fromJvmCarrier(rawExpression)}")
                }
            }
            returnType == "$nativeAddress?" -> {
                builder.appendLine("return ($invoke as $memorySegment).takeIf { it != $memorySegment.NULL }?.let(::$nativeAddress)")
            }
            returnType == "$cString?" -> {
                builder.appendLine("return ($invoke as $memorySegment).takeIf { it != $memorySegment.NULL }?.let(::$nativeAddress)?.let(::$cString)")
            }
            returnType.endsWith("?") && rawType == memorySegment -> {
                val nonOpt = returnType.removeSuffix("?")
                builder.appendLine("return ($invoke as $memorySegment).takeIf { it != $memorySegment.NULL }?.let(::$nativeAddress)?.let { $nonOpt(it) }")
            }
            rawType == "Int" && returnType == "Boolean" -> {
                builder.appendLine("return (($invoke as Int) != 0)")
            }
            rawType == "Int" && returnType == "UInt" -> {
                builder.appendLine("return ($invoke as Int).toUInt()")
            }
            rawType == "Long" && returnType == "ULong" -> {
                builder.appendLine("return ($invoke as Long).toULong()")
            }
            rawType == memorySegment && returnsStructByValue(type) -> {
                builder.appendLine("return $returnType($nativeAddress($invoke as $memorySegment))")
            }
            else -> {
                builder.appendLine("return $invoke as $returnType")
            }
        }
    }

    private fun returnsStructByValue(type: Type): Boolean =
        rawJvmType(type) == memorySegment &&
            !returnsPointer(type) &&
            typeMapper.mapFunctionType(type) in generatedStructNames

    private fun returnsPointer(type: Type): Boolean = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> true
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> returnsPointer(type.type())
        else -> false
    }

    private fun toRawJvmArgument(name: String, type: Type): String {
        val kmpType = typeMapper.mapFunctionType(type)
        val rawType = rawJvmType(type)
        return when {
            rawType == memorySegment && kmpType == "$nativeAddress?" -> "$name?.handler ?: $memorySegment.NULL"
            rawType == memorySegment && kmpType == "$cString?" -> "$name?.handler?.handler ?: $memorySegment.NULL"
            rawType == memorySegment && kmpType.startsWith(arrayHolder) -> "$name?.handler?.handler ?: $memorySegment.NULL"
            rawType == memorySegment && kmpType.endsWith("?") -> "$name?.handler?.handler ?: $memorySegment.NULL"
            rawType == memorySegment -> "$name.handler.handler"
            typeMapper.isOptionsEnumType(type) ->
                abiIndex.enum(typeMapper.enumDeclaration(type))
                    .optionsRawToJvmCarrier("$name.rawValue")
            typeMapper.isEnumType(type) ->
                abiIndex.enum(typeMapper.enumDeclaration(type)).toJvmCarrier(name)
            rawType == "Int" && kmpType == "UInt" -> "$name.toInt()"
            rawType == "Int" && kmpType == "Boolean" -> "if ($name) 1 else 0"
            rawType == "Long" && kmpType == "ULong" -> "$name.toLong()"
            rawType == "Short" && kmpType == "UShort" -> "$name.toShort()"
            rawType == "Byte" && kmpType == "UByte" -> "$name.toByte()"
            else -> name
        }
    }

    private fun rawJvmType(type: Type): String = when {
        type is Type.Primitive -> typeMapper.mapPrimitive(type.kind())
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
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> rawJvmType(type.type())
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> memorySegment
        typeMapper.isEnumType(type) -> abiIndex.enum(typeMapper.enumDeclaration(type)).jvmCarrier
        type is Type.Declared && type.isStructOrUnion() -> memorySegment
        type is Type.Array -> memorySegment
        type is Type.Function -> memorySegment
        else -> memorySegment
    }

    private fun planJvmRuntimeNames(rendered: String): String =
        listOf(VALUE_LAYOUT, MEMORY_LAYOUT).fold(rendered) { value, symbol ->
            value.replace(symbol.preferredName, namePlan.runtime(symbol))
        }

    private fun inlineUnionField(decl: Declaration.Scoped): Declaration.Variable? =
        decl.members()
            .filterIsInstance<Declaration.Variable>()
            .firstOrNull { typeMapper.declaredUnion(it.type()) != null }

}
