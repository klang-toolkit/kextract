@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.ARRAY_HOLDER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.BYTE_VAR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_OPAQUE_POINTER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_VALUE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_VALUE_FACTORY
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.GET
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_ALLOCATOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.OPT_IN
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.POINTED
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.PTR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.REINTERPRET
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.SET
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.SIZE_OF
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.USE_CONTENTS
import org.graphiks.kextract.kotlin.KotlinKmpSourceSet
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackBindingEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinDirectFunctionBindingModel
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackModel
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackNativeEmitter
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import org.graphiks.kextract.pipeline.isStructOrUnion
import org.graphiks.kextract.pipeline.isEnum

internal class KotlinKmpNativeBuilder(
    private val targetPackage: String,
    private val className: String,
    private val callbackModels: List<KotlinCallbackModel>,
    private val directBindingModels: List<KotlinDirectFunctionBindingModel>,
    private val namePlan: KotlinKmpNamePlan,
    private val abiIndex: KotlinKmpAbiIndex,
) : Declaration.Visitor<Unit> {

    private val builder = SourceBuilder()
    private val files = mutableListOf<KotlinSourceFile>()
    private val generatedNames = mutableSetOf<String>()
    private val generatedStructNames = mutableSetOf<String>()
    private val callbackTypeNames = callbackModels.mapTo(mutableSetOf(), KotlinCallbackModel::typeName)
    private val typeMapper = KmpTypeMapper(
        namePlan,
        abiIndex = abiIndex,
    )
    private val nativeAddress = namePlan.runtime(NATIVE_ADDRESS)
    private val cString = namePlan.runtime(C_STRING)
    private val arrayHolder = namePlan.runtime(ARRAY_HOLDER)
    private val memoryAllocator = namePlan.runtime(MEMORY_ALLOCATOR)
    private val cValue = namePlan.runtime(C_VALUE)
    private val cOpaquePointer = namePlan.runtime(C_OPAQUE_POINTER)
    private val byteVar = namePlan.runtime(BYTE_VAR)
    private val cinteropGet = namePlan.runtime(GET)
    private val cinteropSet = namePlan.runtime(SET)
    private val sizeOf = namePlan.runtime(SIZE_OF)
    private val pointed = namePlan.runtime(POINTED)
    private val ptr = namePlan.runtime(PTR)
    private val reinterpret = namePlan.runtime(REINTERPRET)
    private val useContents = namePlan.runtime(USE_CONTENTS)

    init {
        builder.appendLine("@file:${namePlan.runtime(OPT_IN)}(kotlinx.cinterop.ExperimentalForeignApi::class)")
        builder.appendLine()
        if (targetPackage.isNotEmpty()) {
            builder.appendLine("package $targetPackage")
            builder.appendLine()
        }

        KotlinKmpRuntimeSymbol.entries
            .filter { KotlinKmpSourceSet.NATIVE in it.sourceSets }
            .forEach { builder.appendLine(namePlan.importLine(it)) }
        builder.appendLine()
    }

    override fun visitScoped(decl: Declaration.Scoped) {
        if (Skip.isPresent(decl)) return
        when (decl.kind()) {
            Declaration.Scoped.Kind.STRUCT,
            Declaration.Scoped.Kind.UNION -> {
                val structName = namePlan.declaration(decl)
                val nativeStructClassifier = namePlan.nativeCinteropClassifier(decl)
                if (structName.isEmpty() || structName.contains("unnamed")) return
                if (structName.endsWith("Impl") && decl.members().isEmpty()) return
                if (!generatedNames.add(structName)) return
                generatedStructNames.add(structName)
                if (structName == "WGPUNativeDisplayHandle") {
                    emitNativeDisplayHandle()
                    return
                }

                val fields = decl.members().filterIsInstance<Declaration.Variable>().filterNot(Skip::isPresent)

                builder.appendLine("actual interface $structName {")
                builder.indent()

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
                builder.appendLine("actual val handler: $nativeAddress")

                // Companion object
                builder.appendLine("actual companion object {")
                builder.indent()
                builder.appendLine("actual operator fun invoke(address: $nativeAddress): $structName = ByReference(address)")
                if (fields.isEmpty()) {
                    builder.appendLine("actual fun allocate(allocator: $memoryAllocator): $structName =")
                    builder.appendLine("    ByReference(allocator.allocate(8L))")
                } else {
                    builder.appendLine("actual fun allocate(allocator: $memoryAllocator): $structName =")
                    builder.appendLine("    ByReference(allocator.allocate($sizeOf<$nativeStructClassifier>().toLong()))")
                }
                builder.appendLine()
                builder.appendLine("actual fun allocateArray(allocator: $memoryAllocator, size: UInt, provider: (UInt, $structName) -> Unit): $arrayHolder<$structName> {")
                builder.indent()
                if (fields.isEmpty()) {
                    builder.appendLine("val byteSize = 8L")
                } else {
                    builder.appendLine("val byteSize = $sizeOf<$nativeStructClassifier>().toLong()")
                }
                builder.appendLine("val segment = allocator.allocate(byteSize * size.toLong())")
                builder.appendLine("for (i in 0 until size.toInt()) {")
                builder.indent()
                builder.appendLine("val rawAddr = segment.rawValue + i.toLong() * byteSize")
                builder.appendLine("provider(i.toUInt(), ByReference($nativeAddress(rawAddr)))")
                builder.unindent()
                builder.appendLine("}")
                builder.appendLine("return $arrayHolder(segment)")
                builder.unindent()
                builder.appendLine("}")
                builder.unindent()
                builder.appendLine("}") // End companion

                if (fields.isNotEmpty()) {
                    builder.appendLine()
                    builder.appendLine("    value class ByValue(val handle: $cValue<$nativeStructClassifier>) : $structName {")
                    builder.indent()
                    builder.appendLine("override val handler: $nativeAddress")
                    builder.appendLine("    get() = error(\"should not be call on CValue\")")
                    builder.appendLine()
                    fields.forEach { field ->
                        val fieldName = namePlan.rawIdentifier(field)
                        val propertyName = namePlan.member(field)
                        val fieldType = typeMapper.mapType(field.type())
                        if (fieldType == cString) {
                            builder.appendLine("override var $propertyName: $cString?")
                        } else if (fieldType.startsWith(arrayHolder)) {
                            builder.appendLine("override var $propertyName: $fieldType?")
                        } else if (fieldType.endsWith("?") || fieldType == nativeAddress) {
                            builder.appendLine("override var $propertyName: $fieldType")
                        } else {
                            builder.appendLine("override var $propertyName: $fieldType")
                        }
                        builder.indent()
                        when (fieldType) {
                            cString -> {
                                builder.appendLine("get() = handle.$useContents { this.$fieldName?.let { $cString($nativeAddress(it)) } }")
                                builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                            }
                            nativeAddress -> {
                                builder.appendLine("get() = handle.$useContents { this.$fieldName?.let(::$nativeAddress) ?: $nativeAddress(0L) }")
                                builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                            }
                            "$nativeAddress?" -> {
                                builder.appendLine("get() = handle.$useContents { this.$fieldName?.let(::$nativeAddress) }")
                                builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                            }
                            "Boolean" -> {
                                builder.appendLine("get() = handle.$useContents { this.$fieldName }")
                                builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                            }
                            "Byte", "Short", "Int", "Long", "Float", "Double", "UByte", "UShort", "UInt", "ULong" -> {
                                builder.appendLine("get() = handle.$useContents { this.$fieldName }")
                                builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                            }
                            else -> {
                                if (typeMapper.isOptionsEnumType(field.type())) {
                                    val scalar = abiIndex.enum(typeMapper.enumDeclaration(field.type()))
                                    builder.appendLine(
                                        "get() = handle.$useContents { $fieldType(" +
                                            "${scalar.kotlinScalarToOptionsRaw("this.$fieldName")}) }",
                                    )
                                    builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                                } else if (typeMapper.isInlineStructOrUnion(field.type())) {
                                    val isOpt = fieldType == cString || fieldType.startsWith(arrayHolder) || fieldType.endsWith("?")
                                    if (isOpt) {
                                        val nonOpt = fieldType.removeSuffix("?")
                                        builder.appendLine("get() = handle.$useContents { this.$fieldName?.let(::$nativeAddress)?.let { $nonOpt.ByReference(it) } }")
                                        builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                                    } else {
                                        builder.appendLine("get() = handle.$useContents { $fieldType.ByReference($nativeAddress(this.$fieldName.$ptr)) }")
                                        builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                                    }
                                } else {
                                    when {
                                        fieldType == cString -> {
                                            builder.appendLine("get() = handle.$useContents { this.$fieldName?.let { $cString($nativeAddress(it)) } }")
                                            builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                                        }
                                        fieldType == nativeAddress -> {
                                            builder.appendLine("get() = handle.$useContents { this.$fieldName?.let(::$nativeAddress) ?: $nativeAddress(0L) }")
                                            builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                                        }
                                        fieldType == "$nativeAddress?" -> {
                                            builder.appendLine("get() = handle.$useContents { this.$fieldName?.let(::$nativeAddress) }")
                                            builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                                        }
                                        fieldType.endsWith("?") -> {
                                            val nonOpt = fieldType.removeSuffix("?")
                                            builder.appendLine("get() = handle.$useContents { this.$fieldName?.let(::$nativeAddress)?.let { $nonOpt(it) } }")
                                            builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                                        }
                                        else -> {
                                            builder.appendLine("get() = handle.$useContents { this.$fieldName as $fieldType }")
                                            builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
                                        }
                                    }
                                }
                            }
                        }
                        builder.unindent()
                    }
                    builder.unindent()
                    builder.appendLine("    }")
                }

                // ByReference implementation using type-safe C-Interop delegation
                builder.appendLine()
                builder.appendLine("class ByReference(override val handler: $nativeAddress) : $structName {")
                builder.indent()
                if (fields.isEmpty()) {
                    builder.appendLine("private val struct: $cOpaquePointer")
                    builder.appendLine("    get() = handler.pointer")
                } else {
                    builder.appendLine("private val struct: $nativeStructClassifier")
                    builder.appendLine("    get() = handler.pointer.$reinterpret<$nativeStructClassifier>().$pointed")
                }
                builder.appendLine()
                fields.forEach { field ->
                    val fieldName = namePlan.rawIdentifier(field)
                    val propertyName = namePlan.member(field)
                    val fieldType = typeMapper.mapType(field.type())
                    if (fieldType == cString) {
                        builder.appendLine("override var $propertyName: $cString?")
                    } else if (fieldType.startsWith(arrayHolder)) {
                        builder.appendLine("override var $propertyName: $fieldType?")
                    } else if (fieldType.endsWith("?") || fieldType == nativeAddress) {
                        builder.appendLine("override var $propertyName: $fieldType")
                    } else {
                        builder.appendLine("override var $propertyName: $fieldType")
                    }
                    builder.indent()
                    when (fieldType) {
                        cString -> {
                            builder.appendLine("get() = struct.$fieldName?.let { $cString($nativeAddress(it)) }")
                            builder.appendLine("set(value) { struct.$fieldName = value?.handler?.pointer?.takeIf { value.handler.rawValue != 0L }?.$reinterpret() }")
                        }
                        nativeAddress -> {
                            builder.appendLine("get() = struct.$fieldName?.let(::$nativeAddress) ?: $nativeAddress(0L)")
                            builder.appendLine("set(value) { struct.$fieldName = value.pointer.takeIf { value.rawValue != 0L }?.$reinterpret() }")
                        }
                        "$nativeAddress?" -> {
                            builder.appendLine("get() = struct.$fieldName?.let(::$nativeAddress)")
                            builder.appendLine("set(value) { struct.$fieldName = value?.pointer?.takeIf { value.rawValue != 0L }?.$reinterpret() }")
                        }
                        "Boolean" -> {
                            builder.appendLine("get() = struct.$fieldName")
                            builder.appendLine("set(value) { struct.$fieldName = value }")
                        }
                        "Byte", "Short", "Int", "Long", "Float", "Double", "UByte", "UShort", "UInt", "ULong" -> {
                            builder.appendLine("get() = struct.$fieldName")
                            builder.appendLine("set(value) { struct.$fieldName = value }")
                        }
                        else -> {
                            if (typeMapper.isOptionsEnumType(field.type())) {
                                val scalar = abiIndex.enum(typeMapper.enumDeclaration(field.type()))
                                builder.appendLine(
                                    "get() = $fieldType(" +
                                        "${scalar.kotlinScalarToOptionsRaw("struct.$fieldName")})",
                                )
                                builder.appendLine(
                                    "set(value) { struct.$fieldName = " +
                                        "${scalar.optionsRawToKotlinScalar("value.rawValue")} }",
                                )
                            } else if (typeMapper.isInlineStructOrUnion(field.type())) {
                                val isOpt = fieldType == cString || fieldType.startsWith(arrayHolder) || fieldType.endsWith("?")
                                if (isOpt) {
                                    val nonOpt = fieldType.removeSuffix("?")
                                    builder.appendLine("get() = struct.$fieldName?.let(::$nativeAddress)?.let { $nonOpt.ByReference(it) }")
                                    builder.appendLine("set(value) { struct.$fieldName = value?.handler?.pointer?.takeIf { value.handler.rawValue != 0L }?.$reinterpret() }")
                                } else {
                                    val nativeFieldClassifier = namePlan.nativeCinteropClassifier(
                                        requireNotNull(typeMapper.declaredRecord(field.type())),
                                    )
                                    builder.appendLine("get() = $fieldType.ByReference($nativeAddress(struct.$fieldName.$ptr))")
                                    builder.appendLine("set(value) {")
                                    builder.indent()
                                    builder.appendLine("val destBytes = struct.$fieldName.$ptr.$reinterpret<$byteVar>()")
                                    builder.appendLine("val srcBytes = value.handler.pointer.$reinterpret<$byteVar>()")
                                    builder.appendLine("val byteSize = $sizeOf<$nativeFieldClassifier>().toLong()")
                                    builder.appendLine("for (i in 0L until byteSize) {")
                                    builder.indent()
                                    builder.appendLine(byteCopyAssignment("destBytes", "srcBytes", "i.toInt()"))
                                    builder.unindent()
                                    builder.appendLine("}")
                                    builder.unindent()
                                    builder.appendLine("}")
                                }
                            } else {
                                when {
                                    fieldType == cString -> {
                                        builder.appendLine("get() = struct.$fieldName?.let { $cString($nativeAddress(it)) }")
                                        builder.appendLine("set(value) { struct.$fieldName = value?.handler?.pointer?.takeIf { value.handler.rawValue != 0L }?.$reinterpret() }")
                                    }
                                    fieldType == nativeAddress -> {
                                        builder.appendLine("get() = struct.$fieldName?.let(::$nativeAddress) ?: $nativeAddress(0L)")
                                        builder.appendLine("set(value) { struct.$fieldName = value.pointer.takeIf { value.rawValue != 0L }?.$reinterpret() }")
                                    }
                                    fieldType == "$nativeAddress?" -> {
                                        builder.appendLine("get() = struct.$fieldName?.let(::$nativeAddress)")
                                        builder.appendLine("set(value) { struct.$fieldName = value?.pointer?.takeIf { value.rawValue != 0L }?.$reinterpret() }")
                                    }
                                    fieldType.endsWith("?") -> {
                                        val nonOpt = fieldType.removeSuffix("?")
                                        builder.appendLine("get() = struct.$fieldName?.let(::$nativeAddress)?.let { $nonOpt(it) }")
                                        builder.appendLine("set(value) { struct.$fieldName = value?.handler?.pointer?.takeIf { value.handler.rawValue != 0L }?.$reinterpret() }")
                                    }
                                    else -> {
                                        builder.appendLine("get() = struct.$fieldName as $fieldType")
                                        builder.appendLine("set(value) { struct.$fieldName = value }")
                                    }
                                }
                            }
                        }
                    }
                    builder.unindent()
                }
                builder.unindent()
                builder.appendLine("}") // End ByReference

                builder.unindent()
                builder.appendLine("}") // End actual interface
                builder.appendLine()

                // Generate toCValue extension function for structure by-value passing
                if (fields.isNotEmpty()) {
                    builder.appendLine("fun $structName.toCValue(): $cValue<$nativeStructClassifier> = ${namePlan.runtime(C_VALUE_FACTORY)} {")
                    builder.indent()
                    fields.forEach { field ->
                        val fieldName = namePlan.rawIdentifier(field)
                        val propertyName = namePlan.member(field)
                        val fieldType = typeMapper.mapType(field.type())
                        if (typeMapper.isOptionsEnumType(field.type())) {
                            val scalar = abiIndex.enum(typeMapper.enumDeclaration(field.type()))
                            builder.appendLine(
                                "this.$fieldName = " +
                                    scalar.optionsRawToKotlinScalar("this@toCValue.$propertyName.rawValue"),
                            )
                        } else if (typeMapper.isInlineStructOrUnion(field.type())) {
                            val nativeFieldClassifier = namePlan.nativeCinteropClassifier(
                                requireNotNull(typeMapper.declaredRecord(field.type())),
                            )
                            builder.appendLine("val dest_$propertyName = this.$fieldName.$ptr.$reinterpret<$byteVar>()")
                            builder.appendLine("val src_$propertyName = this@toCValue.$propertyName.handler.pointer.$reinterpret<$byteVar>()")
                            builder.appendLine("val size_$propertyName = $sizeOf<$nativeFieldClassifier>().toLong()")
                            builder.appendLine("for (i in 0L until size_$propertyName) {")
                            builder.indent()
                            builder.appendLine(
                                byteCopyAssignment("dest_$propertyName", "src_$propertyName", "i.toInt()"),
                            )
                            builder.unindent()
                            builder.appendLine("}")
                        } else {
                            when (fieldType) {
                                cString -> {
                                    builder.appendLine("this.$fieldName = this@toCValue.$propertyName?.handler?.pointer?.takeIf { this@toCValue.$propertyName?.handler?.rawValue != 0L }?.$reinterpret()")
                                }
                                nativeAddress -> {
                                    builder.appendLine("this.$fieldName = this@toCValue.$propertyName.pointer?.takeIf { this@toCValue.$propertyName.rawValue != 0L }?.$reinterpret()")
                                }
                                "$nativeAddress?" -> {
                                    builder.appendLine("this.$fieldName = this@toCValue.$propertyName?.pointer?.takeIf { this@toCValue.$propertyName?.rawValue != 0L }?.$reinterpret()")
                                }
                                "Boolean" -> {
                                    builder.appendLine("this.$fieldName = this@toCValue.$propertyName")
                                }
                                else -> {
                                    if (fieldType.endsWith("?")) {
                                        builder.appendLine("this.$fieldName = this@toCValue.$propertyName?.handler?.pointer?.takeIf { this@toCValue.$propertyName?.handler?.rawValue != 0L }?.$reinterpret()")
                                    } else {
                                        builder.appendLine("this.$fieldName = this@toCValue.$propertyName")
                                    }
                                }
                            }
                        }
                    }
                    builder.unindent()
                    builder.appendLine("}")
                    builder.appendLine()
                }
            }
            Declaration.Scoped.Kind.TOPLEVEL -> {
                for (member in decl.members()) {
                    member.accept(this)
                }
                KotlinCallbackNativeEmitter(typeMapper::mapFunctionType, namePlan).emit(builder, callbackModels)
                KotlinCallbackBindingEmitter(typeMapper::mapFunctionType, namePlan).emitNative(
                    builder,
                    directBindingModels,
                    ::toNativeArgument,
                )
            }
            else -> {}
        }

        if (decl.kind() == Declaration.Scoped.Kind.TOPLEVEL) {
            files.add(
                KotlinSourceFile(
                    targetPackage,
                    className + "Native",
                    builder.toString(),
                    sourceRoot = "nativeMain/kotlin",
                ),
            )
        }
    }

    override fun visitFunction(decl: Declaration.Function) {
        if (Skip.isPresent(decl)) return
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        val params = buildList {
            if (typeMapper.returnsStructByValue(decl.type().returnType())) {
                add("allocator: $memoryAllocator")
            }
            decl.parameters().forEach { param ->
                val name = namePlan.parameter(param)
                add("$name: ${typeMapper.mapFunctionType(param.type())}")
            }
        }.joinToString(", ")
        val args = decl.parameters().map { param ->
            val name = namePlan.parameter(param)
            toNativeArgument(name, param.type())
        }.joinToString(", ")
        val call = "webgpu.native.${namePlan.rawIdentifier(decl)}($args)"
        builder.appendLine("actual fun ${namePlan.declaration(decl)}($params): $returnType {")
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

    private fun emitFunctionReturn(type: Type, returnType: String, call: String) {
        if (returnType == "Unit") {
            builder.appendLine(call)
            builder.appendLine("return")
            return
        }
        when {
            typeMapper.isOptionsEnumType(type) -> {
                val scalar = abiIndex.enum(typeMapper.enumDeclaration(type))
                builder.appendLine(
                    "return $returnType(${scalar.kotlinScalarToOptionsRaw(call)})",
                )
            }
            returnsStructByValue(type) -> builder.appendLine("return $returnType.ByValue($call)")
            returnType == "$nativeAddress?" -> builder.appendLine("return $call?.let(::$nativeAddress)")
            returnType == "$cString?" -> builder.appendLine("return $call?.let(::$nativeAddress)?.let(::$cString)")
            returnType.endsWith("?") && returnsPointer(type) -> {
                val nonOpt = returnType.removeSuffix("?")
                builder.appendLine("return $call?.let(::$nativeAddress)?.let { $nonOpt(it) }")
            }
            else -> builder.appendLine("return $call")
        }
    }

    private fun toNativeArgument(name: String, type: Type): String {
        val kmpType = typeMapper.mapFunctionType(type)
        return when {
            typeMapper.isOptionsEnumType(type) ->
                abiIndex.enum(typeMapper.enumDeclaration(type))
                    .optionsRawToKotlinScalar("$name.rawValue")
            kmpType == "$cString?" -> "$name?.handler?.pointer?.takeIf { $name.handler.rawValue != 0L }?.$reinterpret()"
            typeMapper.callbackFunction(type) != null ->
                "$name?.pointer?.takeIf { $name.rawValue != 0L }?.$reinterpret()"
            kmpType == "$nativeAddress?" -> {
                val cast = when (name) {
                    "dynamicOffsets" -> namePlan.runtime(KotlinKmpRuntimeSymbol.UINT_VAR)
                    "submissionIndex" -> namePlan.runtime(KotlinKmpRuntimeSymbol.ULONG_VAR)
                    else -> nativePointerVarType(type)
                }
                if (cast == null) {
                    if (typeMapper.pointerDepth(type) > 1) {
                        "$name?.pointer?.takeIf { $name.rawValue != 0L }?.$reinterpret()"
                    } else {
                        "$name?.pointer?.takeIf { $name.rawValue != 0L }"
                    }
                } else {
                    "$name?.pointer?.takeIf { $name.rawValue != 0L }?.$reinterpret<$cast>()"
                }
            }
            kmpType.endsWith("?") && returnsPointer(type) -> "$name?.handler?.pointer?.takeIf { $name.handler.rawValue != 0L }?.$reinterpret()"
            returnsStructByValue(type) -> "$name.toCValue()"
            else -> name
        }
    }

    private fun returnsPointer(type: Type): Boolean = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> true
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> returnsPointer(type.type())
        else -> false
    }

    private fun returnsStructByValue(type: Type): Boolean =
        !returnsPointer(type) &&
            typeMapper.mapFunctionType(type).let { it in generatedStructNames || it == "WGPUNativeDisplayHandle" }

    private fun nativePointerVarType(type: Type): String? {
        val pointee = when {
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> type.type()
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> return nativePointerVarType(type.type())
            else -> return null
        }
        return when {
            pointee is Type.Delegated && pointee.kind() == Type.Delegated.Kind.UNSIGNED -> {
                when ((pointee.type() as? Type.Primitive)?.kind()) {
                    Type.Primitive.Kind.Char -> namePlan.runtime(KotlinKmpRuntimeSymbol.UBYTE_VAR)
                    Type.Primitive.Kind.Short -> namePlan.runtime(KotlinKmpRuntimeSymbol.USHORT_VAR)
                    Type.Primitive.Kind.Int -> namePlan.runtime(KotlinKmpRuntimeSymbol.UINT_VAR)
                    Type.Primitive.Kind.Long, Type.Primitive.Kind.LongLong -> namePlan.runtime(KotlinKmpRuntimeSymbol.ULONG_VAR)
                    else -> null
                }
            }
            pointee is Type.Primitive -> {
                when (pointee.kind()) {
                    Type.Primitive.Kind.Char -> namePlan.runtime(KotlinKmpRuntimeSymbol.BYTE_VAR)
                    Type.Primitive.Kind.Short -> namePlan.runtime(KotlinKmpRuntimeSymbol.SHORT_VAR)
                    Type.Primitive.Kind.Int -> namePlan.runtime(KotlinKmpRuntimeSymbol.INT_VAR)
                    Type.Primitive.Kind.Long, Type.Primitive.Kind.LongLong -> namePlan.runtime(KotlinKmpRuntimeSymbol.LONG_VAR)
                    Type.Primitive.Kind.Float -> namePlan.runtime(KotlinKmpRuntimeSymbol.FLOAT_VAR)
                    Type.Primitive.Kind.Double -> namePlan.runtime(KotlinKmpRuntimeSymbol.DOUBLE_VAR)
                    else -> null
                }
            }
            else -> null
        }
    }

    private fun emitNativeDisplayHandle() {
        builder.appendLine("actual interface WGPUNativeDisplayHandle {")
        builder.indent()
        builder.appendLine("actual var type: WGPUNativeDisplayHandleType")
        builder.appendLine("actual val xlib: WGPUXlibDisplayHandle?")
        builder.appendLine("actual fun setXlib(value: WGPUXlibDisplayHandle)")
        builder.appendLine("actual val xcb: WGPUXcbDisplayHandle?")
        builder.appendLine("actual fun setXcb(value: WGPUXcbDisplayHandle)")
        builder.appendLine("actual val wayland: WGPUWaylandDisplayHandle?")
        builder.appendLine("actual fun setWayland(value: WGPUWaylandDisplayHandle)")
        builder.appendLine("actual val handler: $nativeAddress")
        builder.appendLine("actual companion object {")
        builder.indent()
        builder.appendLine("actual operator fun invoke(address: $nativeAddress): WGPUNativeDisplayHandle = ByReference(address)")
        builder.appendLine("actual fun allocate(allocator: $memoryAllocator): WGPUNativeDisplayHandle =")
        builder.appendLine("    ByReference(allocator.allocate($sizeOf<webgpu.native.WGPUNativeDisplayHandle>().toLong()))")
        builder.appendLine("actual fun allocateArray(allocator: $memoryAllocator, size: UInt, provider: (UInt, WGPUNativeDisplayHandle) -> Unit): $arrayHolder<WGPUNativeDisplayHandle> {")
        builder.indent()
        builder.appendLine("val byteSize = $sizeOf<webgpu.native.WGPUNativeDisplayHandle>().toLong()")
        builder.appendLine("val segment = allocator.allocate(byteSize * size.toLong())")
        builder.appendLine("for (i in 0 until size.toInt()) {")
        builder.indent()
        builder.appendLine("val rawAddr = segment.rawValue + i.toLong() * byteSize")
        builder.appendLine("provider(i.toUInt(), ByReference($nativeAddress(rawAddr)))")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("return $arrayHolder(segment)")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
        builder.appendLine("value class ByValue(val handle: $cValue<webgpu.native.WGPUNativeDisplayHandle>) : WGPUNativeDisplayHandle {")
        builder.indent()
        builder.appendLine("override val handler: $nativeAddress")
        builder.appendLine("    get() = error(\"should not be call on CValue\")")
        emitNativeDisplayHandleNativeProperties("handle.$useContents { this }", byValue = true)
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
        builder.appendLine("class ByReference(override val handler: $nativeAddress) : WGPUNativeDisplayHandle {")
        builder.indent()
        builder.appendLine("private val struct: webgpu.native.WGPUNativeDisplayHandle")
        builder.appendLine("    get() = handler.pointer.$reinterpret<webgpu.native.WGPUNativeDisplayHandle>().$pointed")
        emitNativeDisplayHandleNativeProperties("struct", byValue = false)
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
        builder.appendLine("fun WGPUNativeDisplayHandle.toCValue(): $cValue<webgpu.native.WGPUNativeDisplayHandle> = ${namePlan.runtime(C_VALUE_FACTORY)} {")
        builder.indent()
        builder.appendLine("this.type = this@toCValue.type")
        builder.appendLine("this@toCValue.xlib?.let {")
        builder.indent()
        builder.appendLine("val destBytes = this.data.xlib.$ptr.$reinterpret<$byteVar>()")
        builder.appendLine("val srcBytes = it.handler.pointer.$reinterpret<$byteVar>()")
        builder.appendLine(
            "for (i in 0 until $sizeOf<webgpu.native.WGPUXlibDisplayHandle>()) " +
                byteCopyAssignment("destBytes", "srcBytes", "i"),
        )
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("this@toCValue.xcb?.let {")
        builder.indent()
        builder.appendLine("val destBytes = this.data.xcb.$ptr.$reinterpret<$byteVar>()")
        builder.appendLine("val srcBytes = it.handler.pointer.$reinterpret<$byteVar>()")
        builder.appendLine(
            "for (i in 0 until $sizeOf<webgpu.native.WGPUXcbDisplayHandle>()) " +
                byteCopyAssignment("destBytes", "srcBytes", "i"),
        )
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine("this@toCValue.wayland?.let {")
        builder.indent()
        builder.appendLine("val destBytes = this.data.wayland.$ptr.$reinterpret<$byteVar>()")
        builder.appendLine("val srcBytes = it.handler.pointer.$reinterpret<$byteVar>()")
        builder.appendLine(
            "for (i in 0 until $sizeOf<webgpu.native.WGPUWaylandDisplayHandle>()) " +
                byteCopyAssignment("destBytes", "srcBytes", "i"),
        )
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitNativeDisplayHandleNativeProperties(receiver: String, byValue: Boolean) {
        builder.appendLine("override var type: WGPUNativeDisplayHandleType")
        builder.indent()
        if (byValue) {
            builder.appendLine("get() = $receiver.type")
            builder.appendLine("set(value) { error(\"Setters not supported on ByValue\") }")
        } else {
            builder.appendLine("get() = $receiver.type")
            builder.appendLine("set(value) { $receiver.type = value }")
        }
        builder.unindent()
        listOf("xlib" to "WGPUXlibDisplayHandle", "xcb" to "WGPUXcbDisplayHandle", "wayland" to "WGPUWaylandDisplayHandle").forEach { (field, type) ->
            val setter = field.replaceFirstChar { it.titlecase() }
            builder.appendLine("override val $field: $type?")
            builder.indent()
            builder.appendLine("get() = if (type == WGPUNativeDisplayHandleType_$setter) $type.ByReference($nativeAddress($receiver.data.$field.$ptr)) else null")
            builder.unindent()
            builder.appendLine("override fun set$setter(value: $type) {")
            builder.indent()
            if (byValue) {
                builder.appendLine("error(\"Setters not supported on ByValue\")")
            } else {
                builder.appendLine("$receiver.type = WGPUNativeDisplayHandleType_$setter")
                builder.appendLine("val destBytes = $receiver.data.$field.$ptr.$reinterpret<$byteVar>()")
                builder.appendLine("val srcBytes = value.handler.pointer.$reinterpret<$byteVar>()")
                builder.appendLine(
                    "for (i in 0 until $sizeOf<webgpu.native.$type>()) " +
                        byteCopyAssignment("destBytes", "srcBytes", "i"),
                )
            }
            builder.unindent()
            builder.appendLine("}")
        }
    }

    private fun isOptionsStyle(name: String): Boolean =
        name.endsWith("Options") || name.endsWith("Flags") || name.endsWith("Mask") || name == "WGPUInstanceBackend" || name == "WGPUInstanceFlag" || name == "WGPUFlags"

    private fun byteCopyAssignment(destination: String, source: String, index: String): String =
        if (cinteropGet == "get" && cinteropSet == "set") {
            "$destination[$index] = $source[$index]"
        } else {
            "$destination.$cinteropSet($index, $source.$cinteropGet($index))"
        }

}
