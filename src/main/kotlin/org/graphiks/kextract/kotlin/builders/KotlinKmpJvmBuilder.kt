@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.ClangEnumType
import org.graphiks.kextract.DeclarationImpl.ClangUnnamedRecord
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinJvmNativeBundleIndex
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.ARENA
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.ARRAY_HOLDER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.C_STRING
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.FIND_OR_THROW
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.FUNCTION_DESCRIPTOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.GROUP_ELEMENT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.GROUP_LAYOUT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JVM_DOWNCALL_ENGINE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.LINKER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_ALLOCATOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_BUFFER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_LAYOUT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_SEGMENT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.METHOD_HANDLE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.METHOD_HANDLES
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.SEGMENT_ALLOCATOR
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
import org.graphiks.kextract.pipeline.isStructOrUnion
import org.graphiks.kextract.pipeline.isEnum
import org.graphiks.kextract.pipeline.Options
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
    private val memoryBuffer = namePlan.runtime(MEMORY_BUFFER)
    private val jvmDowncallEngine = namePlan.runtime(JVM_DOWNCALL_ENGINE)
    private val nativeBootstrapName = libraries.takeIf { it.isNotEmpty() }?.let {
        privateNames.allocate("KextractNativeBootstrap", "nativeBootstrap")
    }

    /**
     * Enregistrements `registerStructLayout` accumulés pendant l'émission des
     * records, émis en bloc au niveau fichier (getFiles). Placement au niveau
     * fichier (et non dans le companion) : les initialiseurs de niveau fichier
     * s'exécutent au chargement de la classe façade, donc avant tout downcall
     * du fichier — l'ordre d'initialisation des companions de structs imbriqués
     * n'est pas garanti, et `Box.ByValue(...)` (classe imbriquée) n'initialise
     * pas le companion.
     */
    private val structLayoutRegistrations = mutableListOf<String>()

    /**
     * java.lang.foreign / java.lang.invoke symbols. Struct emission is
     * memory-backed (no FFM), so these imports are emitted only when the
     * downcall / callback paths actually reference them (M5.2+ removes the
     * downcall side entirely). Everything else is imported unconditionally,
     * matching the other source-set builders.
     */
    private val usedSymbols = mutableSetOf<KotlinKmpRuntimeSymbol>()

    init {
        nativeBootstrapName?.let { bootstrapName ->
            usedSymbols += MEMORY_SEGMENT
            KotlinJvmNativeBootstrapEmitter(
                libraries = libraries,
                bundleIndex = jvmNativeBundleIndex,
                bootstrapName = bootstrapName,
                delegateResolverName = namePlan.runtime(FIND_OR_THROW),
                memorySegmentName = namePlan.runtime(MEMORY_SEGMENT),
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

                val layout = recordLayouts[decl]
                val sizeBytes = layout.sizeBytes
                val fields = decl.members().filterIsInstance<Declaration.Variable>().filterNot(Skip::isPresent)

                // Enregistrement des métadonnées de layout pour le moteur (M5.2bis) :
                // le GroupLayout FFM des structs par valeur est construit côté moteur,
                // jamais dans le code généré.
                structLayoutRegistrations += structLayoutRegistration(structName, layout, decl.kind())

                // 1. The actual interface (memory-backed, no CStructure base)
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

                // 2. The memory-backed implementations
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
                if (callbackModels.isNotEmpty()) {
                    // Callback trampolines still ride FFM upcall stubs until P3 handover.
                    usedSymbols += setOf(
                        FUNCTION_DESCRIPTOR, LINKER, ARENA, METHOD_HANDLE, METHOD_HANDLES,
                        MEMORY_SEGMENT, VALUE_LAYOUT,
                    )
                }
                KotlinCallbackJvmEmitter(typeMapper::mapFunctionType, namePlan).emit(builder, callbackModels)
                KotlinCallbackBindingEmitter(typeMapper::mapFunctionType, namePlan).emitJvm(
                    builder,
                    directBindingModels,
                    ::toRawJvmArgument,
                )
            }
            else -> {}
        }
    }

    private fun interfaceFieldType(fieldType: String): String = when {
        fieldType == cString -> "$cString?"
        fieldType.startsWith(arrayHolder) -> "$fieldType?"
        else -> fieldType
    }

    /**
     * Rend l'appel `JvmDowncallEngine.registerStructLayout(...)` pour [layout].
     *
     * Les membres sont convertis en `StructField` avec les offsets Clang ; un champ
     * PADDING explicite est émis pour chaque écart entre champs consécutifs (et le
     * padding final) — l'offsetBytes d'un champ PADDING porte la TAILLE du gap, le
     * moteur faisant `paddingLayout(gap)`. Pour une union, un unique padding de la
     * taille totale représente le bloc par valeur (les membres se chevauchent et ne
     * peuvent pas être émis comme éléments séquentiels d'un GroupLayout).
     */
    private fun structLayoutRegistration(
        structName: String,
        layout: KotlinJvmRecordLayout,
        kind: Declaration.Scoped.Kind,
    ): String = buildString {
        val engine = jvmDowncallEngine
        val fields = if (kind == Declaration.Scoped.Kind.UNION) {
            listOf("$engine.StructField(\"__pad\", $engine.FieldKind.PADDING, ${layout.sizeBytes}L)")
        } else {
            val entries = mutableListOf<String>()
            var cursor = 0L
            layout.members.forEach { member ->
                val gap = member.offsetBytes - cursor
                if (gap > 0) {
                    entries += "$engine.StructField(\"__pad\", $engine.FieldKind.PADDING, ${gap}L)"
                }
                entries += structFieldEntries(member)
                cursor = member.offsetBytes + member.sizeBytes
            }
            val trailing = layout.sizeBytes - cursor
            if (trailing > 0) {
                entries += "$engine.StructField(\"__pad\", $engine.FieldKind.PADDING, ${trailing}L)"
            }
            entries
        }
        appendLine("$engine.registerStructLayout(")
        appendLine("    \"$structName\",")
        appendLine("    ${layout.sizeBytes}L, ${layout.alignmentBytes}L,")
        appendLine("    listOf(")
        fields.forEach { field ->
            appendLine("        $field,")
        }
        appendLine("    ),")
        appendLine(")")
    }

    /**
     * Entrées `StructField` pour un membre : un champ STRUCT porte le nom
     * enregistré du type imbriqué (résolu par le moteur dans structLayout), un
     * tableau est aplati en champs élémentaires (le GroupLayout séquentiel doit
     * reproduire la taille Clang du tableau).
     */
    private fun structFieldEntries(member: KotlinJvmRecordMemberLayout): List<String> {
        val engine = jvmDowncallEngine
        if (isAnonymousRecord(member.field.type())) {
            // Record anonyme (union/struct inline) : son nom planifié dépend du chemin
            // d'en-tête — un padding de la taille Clang du membre reproduit le bloc par
            // valeur sans identité, et garde l'émission indépendante du chemin.
            return listOf("$engine.StructField(\"__pad\", $engine.FieldKind.PADDING, ${member.sizeBytes}L)")
        }
        return structFieldKinds(member.field.type()).map { (kind, structTypeName) ->
            val cName = structTypeName ?: member.cName
            "$engine.StructField(\"$cName\", $engine.FieldKind.$kind, ${member.offsetBytes}L)"
        }
    }

    private fun isAnonymousRecord(type: Type): Boolean = when {
        type is Type.Declared && type.isStructOrUnion() -> ClangUnnamedRecord.isPresent(type.tree())
        type is Type.Delegated -> isAnonymousRecord(type.type())
        else -> false
    }

    /** (FieldKind, nom du type struct pour STRUCT) par type C, tableaux aplatis. */
    private fun structFieldKinds(type: Type): List<Pair<String, String?>> = when {
        type is Type.Array -> {
            val count = type.elementCount() ?: 1L
            buildList { repeat(count.toInt()) { addAll(structFieldKinds(type.elementType())) } }
        }
        type is Type.Declared && type.isEnum() -> {
            val underlying = requireNotNull(ClangEnumType.get(type.tree())) {
                "Enum ${type.tree().name()} has no Clang underlying type"
            }
            structFieldKinds(underlying)
        }
        type is Type.Declared && type.isStructOrUnion() ->
            listOf("STRUCT" to namePlan.declaration(type.tree()))
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER ->
            listOf("POINTER" to null)
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.UNSIGNED ->
            structFieldKinds(type.type()).map { (kind, structTypeName) ->
                scalarFieldKind(kind, unsigned = true) to structTypeName
            }
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.SIGNED ->
            structFieldKinds(type.type())
        type is Type.Delegated -> structFieldKinds(type.type())
        type is Type.Function -> listOf("POINTER" to null)
        type is Type.Primitive -> listOf(scalarFieldKind(primitiveKind(type.kind()), unsigned = false) to null)
        // Types erronés (inconnus de Clang) : traités comme adresses par l'émission mémoire.
        else -> listOf("POINTER" to null)
    }

    private fun primitiveKind(kind: Type.Primitive.Kind): String = when (kind) {
        Type.Primitive.Kind.Bool -> "INT8"
        Type.Primitive.Kind.Char -> "INT8"
        Type.Primitive.Kind.Char16 -> "UINT16"
        Type.Primitive.Kind.Short -> "INT16"
        Type.Primitive.Kind.Int -> "INT32"
        Type.Primitive.Kind.Long, Type.Primitive.Kind.LongLong -> "INT64"
        Type.Primitive.Kind.Float -> "FLOAT32"
        Type.Primitive.Kind.Double -> "FLOAT64"
        Type.Primitive.Kind.Int128 -> "INT64"
        Type.Primitive.Kind.LongDouble -> "FLOAT64"
        else -> error("Unsupported scalar field kind: $kind")
    }

    private fun scalarFieldKind(kind: String, unsigned: Boolean): String = when {
        unsigned && kind == "INT8" -> "UINT8"
        unsigned && kind == "INT16" -> "UINT16"
        unsigned && kind == "INT32" -> "UINT32"
        unsigned && kind == "INT64" -> "UINT64"
        else -> kind
    }

    private fun emitMemoryRecordImpl(
        structName: String,
        layout: KotlinJvmRecordLayout,
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
            val fieldLayout = layout.field(field.name())
            emitMemoryFieldAccessors(
                builder = builder,
                typeMapper = typeMapper,
                abiIndex = abiIndex,
                nativeAddress = nativeAddress,
                cString = cString,
                memoryBuffer = memoryBuffer,
                field = field,
                propertyName = propertyName,
                fieldType = fieldType,
                offsetBytes = fieldLayout.offsetBytes,
                sizeBytes = fieldLayout.sizeBytes,
            )
            builder.unindent()
        }
        builder.appendLine("override val handler: $nativeAddress")
        builder.indent()
        builder.appendLine("get() = handle")
        builder.unindent()
        builder.unindent()
        builder.appendLine("}")
    }

    private fun emitNativeDisplayHandle(decl: Declaration.Scoped) {
        val layout = recordLayouts[decl]
        val sizeBytes = layout.sizeBytes
        val unionField = decl.members()
            .filterIsInstance<Declaration.Variable>()
            .filterNot(Skip::isPresent)
            .firstOrNull { typeMapper.declaredUnion(it.type()) != null }
        val fields = decl.members()
            .filterIsInstance<Declaration.Variable>()
            .filterNot(Skip::isPresent)
            .filterNot { it == unionField }
        val unionFields = nativeDisplayUnionFields(typeMapper, decl)
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
        layout: KotlinJvmRecordLayout,
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
            val memberSize = recordLayouts[requireNotNull(canonicalRecordDeclaration(field.type()))].sizeBytes
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

    fun getFiles(): List<KotlinSourceFile> {
        val header = buildString {
            if (targetPackage.isNotEmpty()) {
                append("package $targetPackage")
                append("\n\n")
            }
            KotlinKmpRuntimeSymbol.entries
                .filter { KotlinKmpSourceSet.JVM in it.sourceSets }
                .filter { it !in JVM_FFM_SYMBOLS || it in usedSymbols }
                .forEach { appendLine(namePlan.importLine(it)) }
            appendLine()
        }
        val registrations = if (structLayoutRegistrations.isEmpty()) {
            ""
        } else {
            buildString {
                appendLine()
                appendLine("// Layouts des structs par valeur : enregistrés au chargement du fichier")
                appendLine("// (classe façade), donc avant tout downcall — les companions de structs")
                appendLine("// imbriqués ne sont pas garantis initialisés à ce moment.")
                appendLine("private val __kffiJvmStructLayouts: Unit = run {")
                structLayoutRegistrations.forEach { registration ->
                    registration.lineSequence().forEach { line ->
                        appendLine("    $line")
                    }
                }
                appendLine("}")
            }
        }
        return listOf(
            KotlinSourceFile(
                targetPackage,
                className + "Jvm",
                header + builder.toString().trimEnd() + registrations + "\n",
                sourceRoot = "jvmMain/kotlin",
            ),
        )
    }

    private fun emitFunction(decl: Declaration.Function) {
        val structArgs = decl.parameters().filter { typeMapper.returnsStructByValue(it.type()) }
        val structReturn = typeMapper.returnsStructByValue(decl.type().returnType())
        if (structArgs.isNotEmpty() || structReturn) {
            emitStructByValueFunction(decl, structArgs, structReturn)
            return
        }
        emitFfmFunction(decl)
    }

    /**
     * Émission struct-by-value (M5.2bis) : la signature est couverte par un wrapper
     * du moteur construit depuis le registre de layouts — jamais de FunctionDescriptor
     * ni de MemoryLayout dans le code généré. Formes couvertes par la table actuelle
     * du moteur : retour struct avec un unique argument scalaire Int
     * (callStructReturn&lt;Name&gt;), ou unique argument struct avec retour Unit
     * (callStructArg&lt;Name&gt;). Toute autre forme échoue à la génération avec un
     * message clair plutôt que d'émettre un fichier qui ne compile pas ; M5.2
     * généralise la table des formes.
     */
    private fun emitStructByValueFunction(
        decl: Declaration.Function,
        structArgs: List<Declaration.Variable>,
        structReturn: Boolean,
    ) {
        val name = namePlan.declaration(decl)
        val cName = decl.name()
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        val supported = when {
            structReturn ->
                structArgs.isEmpty() &&
                    decl.parameters().size == 1 &&
                    rawJvmType(decl.parameters().single().type()) == "Int"
            else ->
                structArgs.size == 1 &&
                    decl.parameters().size == 1 &&
                    returnType == "Unit"
        }
        if (!supported) {
            error(structByValueShapeError(decl, structReturn))
        }
        val params = (
            typeMapper.allocatorParams(decl.type().returnType()) +
                decl.parameters().map { param ->
                    "${namePlan.parameter(param)}: ${typeMapper.mapFunctionType(param.type())}"
                }
            ).joinToString(", ")
        builder.appendLine("private val ${name}_ADDR: Long by lazy { $jvmDowncallEngine.resolveSymbol(\"$cName\") }")
        builder.appendLine("actual fun $name($params): $returnType {")
        builder.indent()
        if (structReturn) {
            val structName = typeMapper.mapFunctionType(decl.type().returnType())
            val rawArgs = decl.parameters().map { param ->
                val paramName = namePlan.parameter(param)
                toRawJvmArgument(paramName, param.type())
            }
            val call = "$jvmDowncallEngine.callStructReturn$structName(${name}_ADDR, allocator" +
                rawArgs.joinToString("") { ", $it" } + ")"
            builder.appendLine("return $returnType.ByValue($call)")
        } else {
            val structParam = decl.parameters().single()
            val paramName = namePlan.parameter(structParam)
            val structName = typeMapper.mapFunctionType(structParam.type())
            builder.appendLine("$jvmDowncallEngine.callStructArg$structName(${name}_ADDR, $paramName.handler.rawValue)")
            builder.appendLine("return")
        }
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun structByValueShapeError(decl: Declaration.Function, structReturn: Boolean): String {
        val signature = decl.parameters().joinToString(", ") { param ->
            "${param.name()}: ${typeMapper.mapFunctionType(param.type())}"
        }
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        return "struct-by-value ${if (structReturn) "return" else "arg"} shape not supported by JVM engine: " +
            "$returnType ${decl.name()}($signature)"
    }

    private fun emitFfmFunction(decl: Declaration.Function) {
        usedSymbols += setOf(FUNCTION_DESCRIPTOR, MEMORY_SEGMENT, METHOD_HANDLE, LINKER, VALUE_LAYOUT, MEMORY_LAYOUT)
        if (returnsStructByValue(decl.type().returnType())) {
            usedSymbols += setOf(ARENA, SEGMENT_ALLOCATOR)
        }
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
        // The allocator parameter is signature-parity only until M5: the body still
        // allocates the struct return from an internal Arena.ofAuto().
        val signatureParams = (typeMapper.allocatorParams(decl.type().returnType()) + params).joinToString(", ")
        builder.appendLine("actual fun $name($signatureParams): $returnType {")
        builder.indent()
        emitFunctionReturn(decl.type().returnType(), returnType, invoke)
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
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
    private val memorySegment: String = namePlan.runtime(MEMORY_SEGMENT)

    private companion object {
        val JVM_FFM_SYMBOLS = setOf(
            ARENA,
            FUNCTION_DESCRIPTOR,
            GROUP_ELEMENT,
            GROUP_LAYOUT,
            LINKER,
            MEMORY_LAYOUT,
            MEMORY_SEGMENT,
            METHOD_HANDLE,
            METHOD_HANDLES,
            SEGMENT_ALLOCATOR,
            VALUE_LAYOUT,
            VAR_HANDLE,
        )
    }

}
