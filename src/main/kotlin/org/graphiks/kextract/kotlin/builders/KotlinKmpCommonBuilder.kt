@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.getAttribute
import org.graphiks.kextract.callbacks.ValidatedCallbackBindings
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.ARRAY_HOLDER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_ALLOCATOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpSourceSet
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex
import org.graphiks.kextract.kotlin.abi.KotlinKmpCAbiType
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackBindingEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackCommonEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackModel
import org.graphiks.kextract.kotlin.callbacks.KotlinDirectFunctionBindingModel
import org.graphiks.kextract.kotlin.models.KotlinSourceFile

internal class KotlinKmpCommonBuilder(
    private val targetPackage: String,
    private val className: String,
    private val callbackModels: List<KotlinCallbackModel>,
    private val callbackModelsByCanonicalId: Map<String, KotlinCallbackModel>,
    private val directBindingModels: List<KotlinDirectFunctionBindingModel>,
    private val callbackBindings: ValidatedCallbackBindings,
    private val namePlan: KotlinKmpNamePlan,
    private val abiIndex: KotlinKmpAbiIndex,
) : Declaration.Visitor<Unit> {

    private val builder = SourceBuilder()
    private val files = mutableListOf<KotlinSourceFile>()
    private val generatedNames = mutableSetOf<String>()
    var needsPlatformAvailability: Boolean = false
        private set
    private val typeMapper = KmpTypeMapper(
        namePlan,
        abiIndex = abiIndex,
    )
    private val nativeAddress = namePlan.runtime(NATIVE_ADDRESS)
    private val cString = namePlan.runtime(C_STRING)
    private val arrayHolder = namePlan.runtime(ARRAY_HOLDER)
    private val memoryAllocator = namePlan.runtime(MEMORY_ALLOCATOR)

    init {
        if (targetPackage.isNotEmpty()) {
            builder.appendLine("package $targetPackage")
            builder.appendLine()
        }

        KotlinKmpRuntimeSymbol.entries
            .filter { KotlinKmpSourceSet.COMMON in it.sourceSets }
            .filter { it != CALLBACK_RUNTIME || directBindingModels.isNotEmpty() }
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

                emitKDoc(decl)
                emitPlatformAvailability(decl)
                builder.appendLine("expect interface $structName {")
                builder.indent()

                // Visit members
                decl.members().filterIsInstance<Declaration.Variable>().filterNot(Skip::isPresent).forEach { field ->
                    val fieldName = namePlan.member(field)
                    val fieldType = typeMapper.mapType(field.type())
                    emitKDoc(field)
                    emitPlatformAvailability(field)
                    if (fieldType == cString) {
                        builder.appendLine("var $fieldName: $cString?")
                    } else if (fieldType.startsWith(arrayHolder)) {
                        builder.appendLine("var $fieldName: $fieldType?")
                    } else if (fieldType.endsWith("?") || fieldType == nativeAddress) {
                        builder.appendLine("var $fieldName: $fieldType")
                    } else {
                        builder.appendLine("var $fieldName: $fieldType")
                    }
                }

                builder.appendLine("val handler: $nativeAddress")

                // Companion object
                builder.appendLine("companion object {")
                builder.indent()
                builder.appendLine("operator fun invoke(address: $nativeAddress): $structName")
                builder.appendLine("fun allocate(allocator: $memoryAllocator): $structName")
                builder.appendLine("fun allocateArray(allocator: $memoryAllocator, size: UInt, provider: (UInt, $structName) -> Unit): $arrayHolder<$structName>")
                builder.unindent()
                builder.appendLine("}")

                builder.unindent()
                builder.appendLine("}")
                builder.appendLine()
            }
            Declaration.Scoped.Kind.ENUM -> {
                val name = namePlan.declaration(decl)
                if (name.isNotEmpty() && !name.contains("unnamed")) {
                    if (!generatedNames.add(name)) return
                    val constants = decl.members().filterIsInstance<Declaration.Constant>().filterNot(Skip::isPresent)
                    emitKDoc(decl)
                    if (isOptionsStyleName(name)) {
                        emitValueClass(decl, name, constants, abiIndex.enum(decl))
                    } else {
                        emitEnumClass(decl, constants)
                    }
                }
            }
            Declaration.Scoped.Kind.TOPLEVEL -> {
                emitFlagTypedefs(decl)
                for (member in decl.members()) {
                    member.accept(this)
                }
                KotlinCallbackCommonEmitter(typeMapper::mapFunctionType, namePlan).emit(builder, callbackModels)
                KotlinCallbackBindingEmitter(typeMapper::mapFunctionType, namePlan).emitCommon(
                    builder,
                    directBindingModels,
                    callbackBindings.callbackInfoBindings,
                    callbackModelsByCanonicalId,
                )
            }
            else -> {}
        }

        if (decl.kind() == Declaration.Scoped.Kind.TOPLEVEL) {
            files.add(
                KotlinSourceFile(
                    targetPackage,
                    className + "Common",
                    builder.toString(),
                    sourceRoot = "commonMain/kotlin",
                ),
            )
        }
    }

    private fun emitEnumClass(decl: Declaration.Scoped, constants: List<Declaration.Constant>) {
        val name = namePlan.declaration(decl)
        val scalar = abiIndex.enum(decl)
        val applicationType = scalar.kotlinType
        emitPlatformAvailability(decl)
        builder.appendLine("typealias ${name} = $applicationType")
        for (c in constants) {
            emitKDoc(c)
            emitPlatformAvailability(c)
            builder.appendLine(
                "const val ${namePlan.declaration(c)} : ${name} = " +
                    scalar.enumConstantLiteral(c.value().toLongValue()),
            )
        }
        builder.appendLine()
    }

    private fun emitValueClass(
        decl: Declaration.Scoped,
        name: String,
        constants: List<Declaration.Constant>,
        scalar: KotlinKmpCAbiType.Scalar,
    ) {
        emitPlatformAvailability(decl)
        builder.appendLine("@kotlin.jvm.JvmInline")
        builder.appendLine("value class ${name}(val rawValue: Long) {")
        builder.indent()

        if (constants.isNotEmpty()) {
            builder.appendLine("companion object {")
            builder.indent()
            for (c in constants) {
                emitKDoc(c)
                emitPlatformAvailability(c)
                builder.appendLine(
                    "val ${namePlan.declaration(c)} = ${name}(" +
                        "${scalar.enumConstantOptionsRawLiteral(c.value().toLongValue())})",
                )
            }
            builder.unindent()
            builder.appendLine("}")
            builder.appendLine()
        }

        builder.appendLine("operator fun plus(o: ${name}) = ${name}(rawValue or o.rawValue)")
        builder.appendLine("operator fun contains(o: ${name}) = (rawValue and o.rawValue) != 0L")

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitFlagTypedefs(decl: Declaration.Scoped) {
        val typedefs = decl.members()
            .filterIsInstance<Declaration.Typedef>()
            .filterNot(Skip::isPresent)
        val constants = decl.members().filterIsInstance<Declaration.Constant>().filterNot(Skip::isPresent)
        val flagTypedefs = typedefs
            .filter { typedef ->
                typedef.name() != "WGPUFlags" &&
                    constants.any { it.name().startsWith("${typedef.name()}_") }
            }

        flagTypedefs.forEach { typedef ->
            val cFlagName = typedef.name()
            val flagName = namePlan.declaration(typedef)
            if (!generatedNames.add(flagName)) return@forEach

            emitKDoc(typedef)
            emitPlatformAvailability(typedef)
            builder.appendLine("typealias $flagName = ULong")
            constants
                .filter { it.name().startsWith("${cFlagName}_") }
                .forEach { constant ->
                    emitKDoc(constant)
                    emitPlatformAvailability(constant)
                    builder.appendLine("const val ${namePlan.declaration(constant)} : $flagName = ${constant.value().toLongValue().toKotlinULongLiteral()}")
                }
            builder.appendLine()
        }
    }

    private fun emitNativeDisplayHandle(decl: Declaration.Scoped) {
        val unionField = inlineUnionField(decl)
        val fields = decl.members()
            .filterIsInstance<Declaration.Variable>()
            .filterNot { it == unionField }
        val unionFields = nativeDisplayUnionFields(decl)

        emitKDoc(decl)
        emitPlatformAvailability(decl)
        builder.appendLine("expect interface WGPUNativeDisplayHandle {")
        builder.indent()

        fields.forEach { field ->
            emitKDoc(field)
            emitPlatformAvailability(field)
            builder.appendLine("var ${namePlan.member(field)}: ${typeMapper.mapType(field.type())}")
        }
        unionFields.forEach { field ->
            val type = typeMapper.mapType(field.type())
            val setter = field.name().replaceFirstChar { it.titlecase() }
            emitKDoc(field)
            emitPlatformAvailability(field)
            builder.appendLine("val ${namePlan.member(field)}: $type?")
            emitPlatformAvailability(field)
            builder.appendLine("fun set$setter(value: $type)")
        }

        builder.appendLine("val handler: $nativeAddress")
        builder.appendLine("companion object {")
        builder.indent()
        builder.appendLine("operator fun invoke(address: $nativeAddress): WGPUNativeDisplayHandle")
        builder.appendLine("fun allocate(allocator: $memoryAllocator): WGPUNativeDisplayHandle")
        builder.appendLine("fun allocateArray(allocator: $memoryAllocator, size: UInt, provider: (UInt, WGPUNativeDisplayHandle) -> Unit): $arrayHolder<WGPUNativeDisplayHandle>")
        builder.unindent()
        builder.appendLine("}")

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun nativeDisplayUnionFields(decl: Declaration.Scoped): List<Declaration.Variable> =
        inlineUnionField(decl)?.type()?.let(typeMapper::declaredUnion)?.members()?.filterIsInstance<Declaration.Variable>()
            ?: decl.members()
                .filterIsInstance<Declaration.Scoped>()
                .firstOrNull { it.kind() == Declaration.Scoped.Kind.UNION }
                ?.members()
                ?.filterIsInstance<Declaration.Variable>()
            ?: emptyList()

    private fun inlineUnionField(decl: Declaration.Scoped): Declaration.Variable? =
        decl.members()
            .filterIsInstance<Declaration.Variable>()
            .firstOrNull { typeMapper.declaredUnion(it.type()) != null }

    private fun Any.toLongValue(): Long = when (this) {
        is Long -> this
        is Int  -> this.toLong()
        else    -> toString().toLongOrNull() ?: 0L
    }

    private fun Long.toKotlinLongLiteral(): String =
        if (this == Long.MIN_VALUE) "Long.MIN_VALUE" else "${this}L"

    private fun Long.toKotlinULongLiteral(): String =
        "${java.lang.Long.toUnsignedString(this)}uL"

    override fun visitFunction(decl: Declaration.Function) {
        if (Skip.isPresent(decl)) return
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        val params = (
            typeMapper.allocatorParams(decl.type().returnType()) +
                decl.parameters().map { param ->
                    "${namePlan.parameter(param)}: ${typeMapper.mapFunctionType(param.type())}"
                }
        ).joinToString(", ")
        emitKDoc(decl)
        emitPlatformAvailability(decl)
        builder.appendLine("expect fun ${namePlan.declaration(decl)}($params): $returnType")
        builder.appendLine()
    }
    override fun visitVariable(decl: Declaration.Variable) {}
    override fun visitTypedef(decl: Declaration.Typedef) {
        if (Skip.isPresent(decl)) return
        val name = namePlan.declaration(decl)
        if (name.isEmpty()) return
        if (typeMapper.callbackFunction(decl.type()) != null) return
        val inner = decl.type()
        if (inner is Type.Delegated && inner.kind() == Type.Delegated.Kind.POINTER) {
            val pointee = inner.type()
            if (pointee is Type.Declared && pointee.tree().kind() == Declaration.Scoped.Kind.STRUCT) {
                val pointeeName = pointee.tree().name()
                if (pointeeName.isNotEmpty() && pointeeName.endsWith("Impl")) {
                    if (!generatedNames.add(name)) return
                    emitKDoc(decl)
                    emitPlatformAvailability(decl)
                    builder.appendLine("expect value class $name(val handler: $nativeAddress)")
                    builder.appendLine()
                }
            }
        }
    }
    override fun visitConstant(decl: Declaration.Constant) {}
    override fun visitObjCClass(decl: Declaration.ObjCClass) {}
    override fun visitObjCProtocol(decl: Declaration.ObjCProtocol) {}
    override fun visitObjCCategory(decl: Declaration.ObjCCategory) {}

    private fun emitPlatformAvailability(declaration: Declaration) {
        val availability = declaration.getAttribute<Declaration.PlatformAvailability>() ?: return
        needsPlatformAvailability = true
        for (entry in availability.entries) {
            builder.appendLine("@PlatformAvailability(")
            builder.indent()
            builder.appendLine("platform = ${entry.platform.asKotlinString()},")
            builder.appendLine("introducedMajor = ${entry.introduced?.major ?: -1},")
            builder.appendLine("introducedMinor = ${entry.introduced?.minor ?: -1},")
            builder.appendLine("introducedSubminor = ${entry.introduced?.subminor ?: -1},")
            builder.appendLine("deprecated = ${entry.deprecated != null || entry.deprecatedWithoutVersion},")
            builder.appendLine("deprecatedMajor = ${entry.deprecated?.major ?: -1},")
            builder.appendLine("deprecatedMinor = ${entry.deprecated?.minor ?: -1},")
            builder.appendLine("deprecatedSubminor = ${entry.deprecated?.subminor ?: -1},")
            builder.appendLine("obsoletedMajor = ${entry.obsoleted?.major ?: -1},")
            builder.appendLine("obsoletedMinor = ${entry.obsoleted?.minor ?: -1},")
            builder.appendLine("obsoletedSubminor = ${entry.obsoleted?.subminor ?: -1},")
            builder.appendLine("unavailable = ${entry.unavailable},")
            builder.appendLine("message = ${entry.message.asKotlinString()},")
            builder.unindent()
            builder.appendLine(")")
        }
    }

    private fun String.asKotlinString(): String = buildString {
        append('\"')
        for (character in this@asKotlinString) {
            append(
                when (character) {
                    '\\' -> "\\\\"
                    '\"' -> "\\\""
                    '\n' -> "\\n"
                    '\r' -> "\\r"
                    '\t' -> "\\t"
                    else -> character
                },
            )
        }
        append('\"')
    }

    fun getFiles(): List<KotlinSourceFile> = files

    private fun emitKDoc(decl: Declaration) {
        emitKDoc(DeclarationImpl.SourceComment.get(decl))
    }

    private fun emitKDoc(comment: DeclarationImpl.SourceComment?) {
        val text = normalizeKDoc(comment ?: return) ?: return
        builder.appendLine("/**")
        text.lines().forEach { line ->
            if (line.isBlank()) {
                builder.appendLine(" *")
            } else {
                builder.appendLine(" * ${line.replace("*/", "* /").replace("/*", "/ *")}")
            }
        }
        builder.appendLine(" */")
    }

    private fun normalizeKDoc(comment: DeclarationImpl.SourceComment): String? {
        val source = comment.raw.takeIf { it.isNotBlank() } ?: comment.brief
        val lines = source.trim()
            .removePrefix("/**")
            .removePrefix("/*!")
            .removePrefix("/*")
            .removeSuffix("*/")
            .lines()
            .map { line ->
                line.trim()
                    .removePrefix("///")
                    .removePrefix("//!")
                    .removePrefix("//")
                    .removePrefix("*")
                    .trim()
            }
            .dropWhile { it.isBlank() }
            .dropLastWhile { it.isBlank() }
        return lines.joinToString("\n").takeIf { it.isNotBlank() }
    }

}
