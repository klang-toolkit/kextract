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
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.JVM_DOWNCALL_ENGINE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.LINKER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_ALLOCATOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_BUFFER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_SEGMENT
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.METHOD_HANDLE
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.METHOD_HANDLES
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.VALUE_LAYOUT
import org.graphiks.kextract.kotlin.KotlinKmpSourceSet
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiContext
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex
import org.graphiks.kextract.kotlin.abi.KotlinKmpCAbiType
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackBindingEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackJvmEmitter
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackModel
import org.graphiks.kextract.kotlin.callbacks.KotlinDirectFunctionBindingModel
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
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
     * java.lang.foreign / java.lang.invoke symbols. Struct emission and function
     * downcalls are engine-backed (no FFM), so these imports are emitted only
     * when the callback paths actually reference them (the M4.2 FFM trampoline
     * fallback for callback shapes JvmUpcallEngine cannot express). Everything
     * else is imported unconditionally, matching the other source-set builders.
     */
    private val usedSymbols = mutableSetOf<KotlinKmpRuntimeSymbol>()

    init {
        nativeBootstrapName?.let { bootstrapName ->
            KotlinJvmNativeBootstrapEmitter(
                libraries = libraries,
                bundleIndex = jvmNativeBundleIndex,
                bootstrapName = bootstrapName,
                delegateResolverName = namePlan.runtime(FIND_OR_THROW),
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
                    ::emitEngineDowncall,
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
        emitEngineFunction(decl)
    }

    /**
     * Émission struct-by-value (M5.2bis, étendue M5.3) : la signature est couverte
     * par un wrapper du moteur construit depuis le registre de layouts — jamais de
     * FunctionDescriptor ni de MemoryLayout dans le code généré.
     *
     * Formes couvertes par la table actuelle du moteur (union des signatures wgpu) :
     *
     * - retour struct seul, avec 0+ arguments scalaires/pointeurs :
     *   `callStructReturn&lt;RetName&gt;(fn, allocator, …)` (ex. wgpuDeviceGetLostFuture) ;
     * - retour struct + un struct par valeur en dernier argument :
     *   `callStructReturn&lt;RetName&gt;&lt;ArgStructName&gt;(fn, allocator, …, structPtr)`
     *   (ex. les fonctions async à retour WGPUFuture et callbackInfo par valeur) ;
     * - struct par valeur en dernier argument, retour Unit :
     *   `callStructArg&lt;ArgStructName&gt;(fn, …, structPtr)` (SetLabel/…, FreeMembers) ;
     * - struct par valeur seul en argument, retour pointeur :
     *   `callStructArg&lt;ArgStructName&gt;(fn, structPtr): Long` (wgpuGetProcAddress).
     *
     * Toute autre forme (plusieurs structs, struct non dernier, retour non couvert)
     * ou tout struct hors [jvmEngineStructWrappers] échoue à la génération avec un
     * message clair plutôt que d'émettre un fichier qui ne compile pas.
     */
    private val jvmEngineStructWrappers = setOf(
        "Box",
        // wgpu : StringView par valeur (SetLabel/PushDebugGroup/InsertDebugMarker/GetProcAddress)
        "WGPUStringView",
        // wgpu : structs par valeur des fonctions *FreeMembers
        "WGPUAdapterInfo",
        "WGPUSupportedFeatures",
        "WGPUSupportedInstanceFeatures",
        "WGPUSupportedWGSLLanguageFeatures",
        "WGPUSurfaceCapabilities",
        // wgpu : retour WGPUFuture des fonctions async
        "WGPUFuture",
        // wgpu : callbackInfo par valeur des fonctions async (retour WGPUFuture)
        "WGPUQueueWorkDoneCallbackInfo",
        "WGPUPopErrorScopeCallbackInfo",
        "WGPUCompilationInfoCallbackInfo",
        "WGPURequestAdapterCallbackInfo",
        "WGPURequestDeviceCallbackInfo",
        "WGPUCreateRenderPipelineAsyncCallbackInfo",
        "WGPUCreateComputePipelineAsyncCallbackInfo",
        "WGPUBufferMapCallbackInfo",
    )

    /**
     * Signatures EXACTES des wrappers struct-by-value du moteur, miroir de la table
     * de JvmDowncallEngine : nom du wrapper → formes `&lt;argLetters&gt;|&lt;returnKind&gt;`
     * où argLetters encode chaque paramètre (I/L/P/F/D/S/B comme les wrappers
     * scalaires, S = struct par valeur à sa position C) et returnKind vaut V (Unit),
     * P (pointeur, résultat Long du moteur) ou S (struct par valeur, ByValue).
     * Toute signature hors table échoue à la génération plutôt que d'émettre un
     * appel irrésolu (arity/type) qui ne compilerait pas.
     */
    private val jvmEngineStructWrapperShapes: Map<String, Set<String>> = mapOf(
        "callStructArgBox" to setOf("S|V"),
        "callStructArgWGPUStringView" to setOf("S|P", "PS|V"),
        "callStructArgWGPUAdapterInfo" to setOf("S|V"),
        "callStructArgWGPUSupportedFeatures" to setOf("S|V"),
        "callStructArgWGPUSupportedInstanceFeatures" to setOf("S|V"),
        "callStructArgWGPUSupportedWGSLLanguageFeatures" to setOf("S|V"),
        "callStructArgWGPUSurfaceCapabilities" to setOf("S|V"),
        "callStructReturnBox" to setOf("I|S"),
        "callStructReturnWGPUFuture" to setOf("P|S"),
        "callStructReturnWGPUFutureWGPUQueueWorkDoneCallbackInfo" to setOf("PS|S"),
        "callStructReturnWGPUFutureWGPUPopErrorScopeCallbackInfo" to setOf("PS|S"),
        "callStructReturnWGPUFutureWGPUCompilationInfoCallbackInfo" to setOf("PS|S"),
        "callStructReturnWGPUFutureWGPURequestAdapterCallbackInfo" to setOf("PPS|S"),
        "callStructReturnWGPUFutureWGPURequestDeviceCallbackInfo" to setOf("PPS|S"),
        "callStructReturnWGPUFutureWGPUCreateRenderPipelineAsyncCallbackInfo" to setOf("PPS|S"),
        "callStructReturnWGPUFutureWGPUCreateComputePipelineAsyncCallbackInfo" to setOf("PPS|S"),
        "callStructReturnWGPUFutureWGPUBufferMapCallbackInfo" to setOf("PLLLS|S"),
    )

    private fun emitStructByValueFunction(
        decl: Declaration.Function,
        structArgs: List<Declaration.Variable>,
        structReturn: Boolean,
    ) {
        val name = namePlan.declaration(decl)
        val cName = decl.name()
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        val cParams = decl.parameters()
        val argStruct = structArgs.singleOrNull()
        val argStructIsLast = argStruct != null && argStruct == cParams.last()
        val returnStructName = if (structReturn) {
            typeMapper.mapFunctionType(decl.type().returnType())
        } else {
            null
        }
        val argStructName = argStruct?.let { typeMapper.mapFunctionType(it.type()) }
        if (structArgs.size > 1 || (argStruct != null && !argStructIsLast)) {
            error(structByValueShapeError(decl, structReturn))
        }
        val missingWrapperStruct = listOfNotNull(returnStructName, argStructName)
            .firstOrNull { it !in jvmEngineStructWrappers }
        if (missingWrapperStruct != null) {
            error(
                "struct-by-value wrapper for '$missingWrapperStruct' not yet implemented in " +
                    "JvmDowncallEngine (M5.3 extends the table)",
            )
        }
        val wrapper = when {
            returnStructName != null && argStructName != null ->
                "callStructReturn$returnStructName$argStructName"
            returnStructName != null -> "callStructReturn$returnStructName"
            else -> "callStructArg$argStructName"
        }
        val argLetters = buildString {
            for (param in cParams) {
                if (namePlan.parameter(param) == argStruct?.let { namePlan.parameter(it) }) {
                    append("S")
                    continue
                }
                val letter = engineArgLetter(KotlinKmpCAbiType.from(param.type(), KotlinKmpAbiContext.DIRECT))
                if (letter == null) {
                    error(structByValueShapeError(decl, structReturn))
                }
                append(letter)
            }
        }
        val returnKind = when {
            structReturn -> "S"
            returnType == "Unit" -> "V"
            KotlinKmpCAbiType.from(decl.type().returnType(), KotlinKmpAbiContext.DIRECT)
                is KotlinKmpCAbiType.Address -> "P"
            else -> error(structByValueShapeError(decl, structReturn))
        }
        if ("$argLetters|$returnKind" !in (jvmEngineStructWrapperShapes[wrapper] ?: emptySet())) {
            error(structByValueShapeError(decl, structReturn))
        }
        val params = (
            typeMapper.allocatorParams(decl.type().returnType()) +
                decl.parameters().map { param ->
                    "${namePlan.parameter(param)}: ${typeMapper.mapFunctionType(param.type())}"
                }
            ).joinToString(", ")
        builder.appendLine("private val ${name}_ADDR: Long by lazy { ${symbolResolver()}(\"$cName\") }")
        builder.appendLine("actual fun $name($params): $returnType {")
        builder.indent()
        val call = structByValueCall(decl, returnStructName, argStruct)
        when {
            structReturn -> builder.appendLine("return $returnType.ByValue($call)")
            returnType == "Unit" -> {
                builder.appendLine(call)
                builder.appendLine("return")
            }
            else -> {
                // Retour pointeur (wgpuGetProcAddress) : même conversion de résultat
                // que les downcalls scalaires.
                emitEngineReturn(decl.type().returnType(), call)
            }
        }
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    /**
     * L'appel wrapper du moteur pour une fonction struct-by-value : les arguments
     * scalaires/pointeurs sont convertis en carriers bruts (toRawJvmArgument), le
     * struct par valeur est passé par son `handler.rawValue` à sa position C (dernier
     * argument). L'allocator n'est passé que pour les retours struct (convention FFM).
     */
    private fun structByValueCall(
        decl: Declaration.Function,
        returnStructName: String?,
        argStruct: Declaration.Variable?,
    ): String {
        val argStructName = argStruct?.let { typeMapper.mapFunctionType(it.type()) }
        val wrapper = when {
            returnStructName != null && argStructName != null ->
                "callStructReturn$returnStructName$argStructName"
            returnStructName != null -> "callStructReturn$returnStructName"
            else -> "callStructArg$argStructName"
        }
        val argStructParamName = argStruct?.let { namePlan.parameter(it) }
        val rawArgs = decl.parameters().map { param ->
            val paramName = namePlan.parameter(param)
            if (paramName == argStructParamName) {
                "$paramName.handler.rawValue"
            } else {
                toRawJvmArgument(paramName, param.type())
            }
        }
        return "$jvmDowncallEngine.$wrapper(${functionAddress(decl)}" +
            (if (returnStructName != null) ", allocator" else "") +
            rawArgs.joinToString("") { ", $it" } + ")"
    }

    private fun structByValueShapeError(decl: Declaration.Function, structReturn: Boolean): String {
        val signature = decl.parameters().joinToString(", ") { param ->
            "${param.name()}: ${typeMapper.mapFunctionType(param.type())}"
        }
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        return "struct-by-value ${if (structReturn) "return" else "arg"} shape not supported by JVM engine: " +
            "$returnType ${decl.name()}($signature)"
    }

    /**
     * Émission downcall générique (M5.2) : le bloc DESC/ADDR/HANDLE/invokeExact FFM est
     * remplacé par un `_ADDR: Long` paresseux (bootstrap ou findOrThrow) et un appel
     * wrapper du moteur (`JvmDowncallEngine.call&lt;R&gt;&lt;N&gt;&lt;ARGS&gt;`) — aucun
     * java.lang.foreign / java.lang.invoke dans le code généré. Les formes couvertes
     * sont celles de la table actuelle du moteur ([jvmEngineWrappers]) ; toute autre
     * forme échoue à la génération avec un message clair plutôt que d'émettre un
     * fichier qui ne compile pas. M5.3 étend la table pour couvrir l'union des
     * signatures wgpu.
     */
    private fun emitEngineFunction(decl: Declaration.Function) {
        val name = namePlan.declaration(decl)
        val cName = decl.name()
        val returnType = typeMapper.mapFunctionType(decl.type().returnType())
        val wrapper = wrapperForm(decl.type())
        if (wrapper !in jvmEngineWrappers) {
            error(
                "downcall shape $wrapper for '$name' not yet implemented in " +
                    "JvmDowncallEngine (M5.3 extends the table)",
            )
        }
        builder.appendLine("private val ${name}_ADDR: Long by lazy { ${symbolResolver()}(\"$cName\") }")
        val params = decl.parameters().map { param ->
            val paramName = namePlan.parameter(param)
            "$paramName: ${typeMapper.mapFunctionType(param.type())}"
        }.joinToString(", ")
        builder.appendLine("actual fun $name($params): $returnType {")
        builder.indent()
        emitEngineDowncall(decl)
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    /**
     * Wrappers scalaires/pointeurs implémentés par JvmDowncallEngine (table M2.1,
     * complétée M5.2bis par les formes struct-by-value). M5.3 étend la table pour
     * couvrir l'union des signatures wgpu — toute forme hors table échoue ici à la
     * génération avec un message clair.
     */
    private val jvmEngineWrappers = setOf(
        "callV0", "callV1P", "callV2PP", "callV3PPL", "callV4PPPP", "callV5PIIII",
        "callI0", "callI1I", "callI1P", "callI4IIII", "callL8LLLLLLLL",
        "callP1P", "callP2PP", "callP2PI", "callP3PLL",
        "callF1P", "callD1P",
        // Union des signatures wgpu (M5.3)
        "callI2PP", "callI2PI", "callL1P", "callI4PLPL",
        "callV2PI", "callV3PLP", "callV5PPLPL", "callV6PPPLPP",
        "callV6PIIIII", "callV5PIPLP", "callV5PPILL", "callV5PIPLL",
        "callI3PPP", "callL3PPP", "callL3PLP", "callI3PIP",
        "callV1I", "callV4PIIP", "callV4PPLI", "callV6PPLPLI",
        "callV3PPI", "callV4PPLL", "callV6PPLPLL", "callV6PPIIPL",
        "callV4PIII", "callV7PFFFFFF",
    )

    /**
     * Le nom du wrapper typé `call<R><N><ARGS>` pour [type]. R ∈ V/I/L/P/D/F (V void,
     * P pointeur, autres par carrier scalaire), N = nombre d'arguments, ARGS = une
     * lettre par argument (I Int, L Long, P pointeur-en-Long, D Double, F Float,
     * S Short, B Byte). Symétrique de KotlinKmpAndroidBuilder ; contrairement au
     * moteur Android (retours Long uniformes), le moteur JVM a des retours typés
     * (callF1P → Float, callD1P → Double) — l'émission du retour s'y adapte.
     */
    private fun wrapperForm(type: Type.Function): String {
        val returnLetter = engineReturnLetter(type.returnType())
        val argLetters = type.argumentTypes().map { arg ->
            engineArgLetter(KotlinKmpCAbiType.from(arg, KotlinKmpAbiContext.DIRECT))
        }
        val letters = argLetters.joinToString("") { it ?: "" }
        return "call$returnLetter${argLetters.size}$letters"
    }

    private fun engineReturnLetter(type: Type): String {
        if (type is Type.Primitive && type.kind() == Type.Primitive.Kind.Void) return "V"
        return engineArgLetter(KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.DIRECT))
            ?: error("struct-by-value returns ride the engine layout wrappers, not wrapperForm")
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
     * Émet l'appel wrapper du moteur pour [function]. [argExpr] résout chaque
     * paramètre C vers l'expression Kotlin qui porte sa valeur (le nom du paramètre
     * pour les `actual fun`s ; le paramètre prévol pour les direct bindings).
     * [asLastExpression] marque le downcall comme expression finale d'un lambda
     * englobant (preflight de direct binding) — les conversions de valeur sont émises
     * sans `return` qualifié (interdit dans un lambda) et l'appel est la valeur du lambda.
     */
    private fun emitEngineDowncall(
        function: Declaration.Function,
        asLastExpression: Boolean = false,
        argExpr: (Declaration.Variable) -> String = { parameter -> namePlan.parameter(parameter) },
    ) {
        val wrapper = wrapperForm(function.type())
        val engineArgs = function.parameters()
            .map { toEngineArgument(argExpr(it), it.type()) }
            .joinToString(", ")
        val call = "$jvmDowncallEngine.$wrapper(${functionAddress(function)}" +
            (if (engineArgs.isEmpty()) "" else ", $engineArgs") + ")"
        emitEngineReturn(function.type().returnType(), call, asLastExpression)
    }

    private fun functionAddress(function: Declaration.Function): String =
        "${namePlan.declaration(function)}_ADDR"

    /**
     * L'expression qui résout un symbole natif en Long : le bootstrap déclaré
     * (`KextractNativeBootstrap.resolve`, qui charge les bibliothèques) quand des
     * libraries sont configurées, sinon `findOrThrow`. Partagée par l'émission
     * générique et struct-by-value.
     */
    private fun symbolResolver(): String =
        nativeBootstrapName?.let { "$it.resolve" } ?: namePlan.runtime(FIND_OR_THROW)

    private fun toEngineArgument(name: String, type: Type): String {
        val kmpType = typeMapper.mapFunctionType(type)
        return when (val abi = KotlinKmpCAbiType.from(type, KotlinKmpAbiContext.DIRECT)) {
            is KotlinKmpCAbiType.Address -> when {
                kmpType == nativeAddress -> "$name.rawValue"
                kmpType == "$nativeAddress?" -> "$name?.rawValue ?: 0L"
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
                error("struct-by-value arguments ride the engine layout wrappers, not toEngineArgument")
        }
    }

    /**
     * Convertit le résultat du wrapper moteur vers le type Kotlin de retour. Le moteur
     * JVM est typé : les wrappers callI…/callL…/callP… retournent Long, callF1P Float,
     * callD1P Double — les scalaires larges sont donc convertis depuis Long
     * (toInt/toShort/…), les flottants et les adresses (takeIf != 0L → NativeAddress)
     * passent tels quels.
     */
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

    /** Rétrécit le résultat Long du moteur vers le jvmCarrier du scalaire (I8/I16/I32). */
    private fun narrowEngineCarrier(call: String, scalar: KotlinKmpCAbiType.Scalar): String = when (scalar.kind) {
        KotlinKmpCAbiType.Scalar.Kind.I8 -> "$call.toByte()"
        KotlinKmpCAbiType.Scalar.Kind.I16, KotlinKmpCAbiType.Scalar.Kind.CHAR16 -> "$call.toShort()"
        KotlinKmpCAbiType.Scalar.Kind.I32 -> "$call.toInt()"
        else -> call
    }

    private fun returnsPointer(type: Type): Boolean = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> true
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> returnsPointer(type.type())
        else -> false
    }

    private fun toRawJvmArgument(name: String, type: Type): String {
        val kmpType = typeMapper.mapFunctionType(type)
        val rawType = rawJvmType(type)
        return when {
            typeMapper.isOptionsEnumType(type) ->
                abiIndex.enum(typeMapper.enumDeclaration(type))
                    .optionsRawToJvmCarrier("$name.rawValue")
            typeMapper.isEnumType(type) ->
                abiIndex.enum(typeMapper.enumDeclaration(type)).toJvmCarrier(name)
            kmpType == "$nativeAddress?" -> "$name?.rawValue ?: 0L"
            kmpType == "$cString?" -> "$name?.handler?.rawValue ?: 0L"
            kmpType.startsWith(arrayHolder) -> "$name?.handler?.rawValue ?: 0L"
            kmpType.endsWith("?") -> "$name?.handler?.rawValue ?: 0L"
            kmpType == nativeAddress -> "$name.rawValue"
            kmpType == cString -> "$name.handler.rawValue"
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
        typeMapper.isEnumType(type) -> abiIndex.enum(typeMapper.enumDeclaration(type)).jvmCarrier
        // Pointeurs, structs par valeur, tableaux et fonctions : convertis par
        // toEngineArgument/toRawJvmArgument selon le type Kotlin mappé — jamais
        // de carrier FFM dans le code généré (M5.2).
        else -> "Long"
    }

    private companion object {
        // FFM imports émis conditionnellement : uniquement quand le fallback
        // trampoline FFM des callbacks (M4.2, formes non engine-fit) les référence.
        val JVM_FFM_SYMBOLS = setOf(
            ARENA,
            FUNCTION_DESCRIPTOR,
            LINKER,
            MEMORY_SEGMENT,
            METHOD_HANDLE,
            METHOD_HANDLES,
            VALUE_LAYOUT,
        )
    }

}
