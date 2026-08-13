package org.graphiks.kextract.kotlin

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.callbacks.ValidatedCallbackBindings
import org.graphiks.kextract.kotlin.utils.KotlinIdentifierAllocator
import org.graphiks.kextract.kotlin.utils.KotlinNameMangler
import java.util.IdentityHashMap

internal enum class KotlinKmpSourceSet {
    COMMON,
    JVM,
    NATIVE,
    ANDROID,
}

private fun common() = setOf(KotlinKmpSourceSet.COMMON)
private fun jvm() = setOf(KotlinKmpSourceSet.JVM)
private fun native() = setOf(KotlinKmpSourceSet.NATIVE)
private fun android() = setOf(KotlinKmpSourceSet.ANDROID)
private fun commonJvmNative() = setOf(
    KotlinKmpSourceSet.COMMON,
    KotlinKmpSourceSet.JVM,
    KotlinKmpSourceSet.NATIVE,
)
private fun allSourceSets() = KotlinKmpSourceSet.entries.toSet()

internal enum class KotlinKmpRuntimeSymbol(
    val qualifiedName: String,
    val sourceSets: Set<KotlinKmpSourceSet>,
    val preferredName: String = qualifiedName.substringAfterLast('.'),
    val preserveOperatorName: Boolean = false,
) {
    NATIVE_ADDRESS("org.graphiks.kffi.NativeAddress", allSourceSets()),
    NATIVE_ENGINE("org.graphiks.kffi.engine.NativeEngine", android()),
    CALLBACK("org.graphiks.kffi.Callback", common()),
    CALLBACK_EXCEPTION_HANDLER("org.graphiks.kffi.CallbackExceptionHandler", allSourceSets()),
    CALLBACK_POLICY("org.graphiks.kffi.CallbackPolicy", allSourceSets()),
    CALLBACK_REGISTRATION("org.graphiks.kffi.CallbackRegistration", allSourceSets()),
    CALLBACK_RUNTIME("org.graphiks.kffi.CallbackRuntime", allSourceSets()),
    CALLBACK_RUNTIME_API("org.graphiks.kffi.CallbackRuntimeApi", allSourceSets()),
    CALLBACK_TYPE("org.graphiks.kffi.CallbackType", common()),
    PREPARED_CALLBACK_REGISTRATION("org.graphiks.kffi.PreparedCallbackRegistration", allSourceSets()),
    UNSAFE_CALLBACK_REARM_API("org.graphiks.kffi.UnsafeCallbackRearmApi", allSourceSets()),
    C_STRING("org.graphiks.kffi.CString", allSourceSets()),
    ARRAY_HOLDER("org.graphiks.kffi.ArrayHolder", allSourceSets()),
    MEMORY_ALLOCATOR("org.graphiks.kffi.MemoryAllocator", allSourceSets()),
    MEMORY_BUFFER("org.graphiks.kffi.MemoryBuffer", android()),
    C_STRUCTURE("org.graphiks.kffi.CStructure", jvm()),
    FIND_OR_THROW("org.graphiks.kffi.findOrThrow", jvm()),
    TO_C_STRING("org.graphiks.kffi.toCString", native()),
    TO_ADDRESS("org.graphiks.kffi.toAddress", android()),

    ARENA("java.lang.foreign.Arena", jvm()),
    FUNCTION_DESCRIPTOR("java.lang.foreign.FunctionDescriptor", jvm()),
    GROUP_LAYOUT("java.lang.foreign.GroupLayout", jvm()),
    LINKER("java.lang.foreign.Linker", jvm()),
    MEMORY_LAYOUT("java.lang.foreign.MemoryLayout", jvm()),
    MEMORY_SEGMENT("java.lang.foreign.MemorySegment", jvm()),
    SEGMENT_ALLOCATOR("java.lang.foreign.SegmentAllocator", jvm()),
    VALUE_LAYOUT("java.lang.foreign.ValueLayout", jvm()),
    METHOD_HANDLE("java.lang.invoke.MethodHandle", jvm()),
    METHOD_HANDLES("java.lang.invoke.MethodHandles", jvm()),
    VAR_HANDLE("java.lang.invoke.VarHandle", jvm()),
    GROUP_ELEMENT("java.lang.foreign.MemoryLayout.PathElement.groupElement", jvm()),

    BYTE_VAR("kotlinx.cinterop.ByteVar", native()),
    C_OPAQUE_POINTER("kotlinx.cinterop.COpaquePointer", native()),
    C_OPAQUE_POINTER_VAR("kotlinx.cinterop.COpaquePointerVar", native()),
    C_VALUE("kotlinx.cinterop.CValue", native()),
    DOUBLE_VAR("kotlinx.cinterop.DoubleVar", native()),
    FLOAT_VAR("kotlinx.cinterop.FloatVar", native()),
    INT_VAR("kotlinx.cinterop.IntVar", native()),
    LONG_VAR("kotlinx.cinterop.LongVar", native()),
    SHORT_VAR("kotlinx.cinterop.ShortVar", native()),
    UBYTE_VAR("kotlinx.cinterop.UByteVar", native()),
    UINT_VAR("kotlinx.cinterop.UIntVar", native()),
    ULONG_VAR("kotlinx.cinterop.ULongVar", native()),
    USHORT_VAR("kotlinx.cinterop.UShortVar", native()),
    C_VALUE_FACTORY("kotlinx.cinterop.cValue", native()),
    GET("kotlinx.cinterop.get", native(), preserveOperatorName = true),
    POINTED("kotlinx.cinterop.pointed", native()),
    PTR("kotlinx.cinterop.ptr", native()),
    REINTERPRET("kotlinx.cinterop.reinterpret", native()),
    SET("kotlinx.cinterop.set", native(), preserveOperatorName = true),
    SIZE_OF("kotlinx.cinterop.sizeOf", native()),
    STATIC_C_FUNCTION("kotlinx.cinterop.staticCFunction", native()),
    USE_CONTENTS("kotlinx.cinterop.useContents", native()),

    JNA_POINTER("com.sun.jna.Pointer", android()),
    JNA_CALLBACK_REFERENCE("com.sun.jna.CallbackReference", android()),
    JNA_STRUCTURE("com.sun.jna.Structure", android()),
    JNA_UNION("com.sun.jna.Union", android()),

    OPT_IN("kotlin.OptIn", allSourceSets()),
    SUPPRESS("kotlin.Suppress", jvm()),
    UNSUPPORTED_OPERATION_EXCEPTION("kotlin.UnsupportedOperationException", android()),
    JVM_FIELD("kotlin.jvm.JvmField", android()),
    JVM_INLINE("kotlin.jvm.JvmInline", setOf(KotlinKmpSourceSet.JVM, KotlinKmpSourceSet.ANDROID)),
    JVM_STATIC("kotlin.jvm.JvmStatic", jvm()),
    ;
}

private data class KotlinKmpJnaHelperNames(
    val byReference: String,
    val byValue: String,
)

internal class KotlinKmpNamePlan private constructor(
    val topLevelNames: Set<String>,
    val renderedRuntimeNames: Set<String>,
    private val runtimeNames: Map<KotlinKmpRuntimeSymbol, String>,
    private val declarationNames: IdentityHashMap<Declaration, String>,
    private val parameterNames: IdentityHashMap<Declaration.Variable, String>,
    private val memberNames: IdentityHashMap<Declaration.Variable, String>,
    private val opaqueHandleAliases: IdentityHashMap<Declaration.Scoped, Declaration.Typedef>,
    private val jnaHelperNames: IdentityHashMap<Declaration.Scoped, KotlinKmpJnaHelperNames>,
    private val jnaHelperNamesByRecordName: Map<String, KotlinKmpJnaHelperNames>,
) {
    fun runtime(symbol: KotlinKmpRuntimeSymbol): String = runtimeNames.getValue(symbol)

    fun importLine(symbol: KotlinKmpRuntimeSymbol): String {
        val rendered = runtime(symbol)
        return if (rendered == symbol.preferredName) {
            "import ${symbol.qualifiedName}"
        } else {
            "import ${symbol.qualifiedName} as $rendered"
        }
    }

    fun declaration(declaration: Declaration): String = declarationNames.getValue(declaration)

    fun parameter(parameter: Declaration.Variable): String = parameterNames.getValue(parameter)

    fun member(field: Declaration.Variable): String = memberNames.getValue(field)

    fun rawIdentifier(declaration: Declaration): String = KotlinNameMangler.escape(declaration.name())

    fun publicRecordClassifier(record: Declaration.Scoped): String =
        opaqueHandleAliases[record]?.let { alias -> declaration(alias) } ?: declaration(record)

    fun nativeCinteropClassifier(declaration: Declaration.Scoped): String =
        "webgpu.native.${rawIdentifier(declaration)}"

    fun jnaByReference(record: Declaration.Scoped): String = jnaHelperNames.getValue(record).byReference

    fun jnaByValue(record: Declaration.Scoped): String = jnaHelperNames.getValue(record).byValue

    fun jnaByReference(recordName: String): String = jnaHelperNamesByRecordName.getValue(recordName).byReference

    fun jnaByValue(recordName: String): String = jnaHelperNamesByRecordName.getValue(recordName).byValue

    companion object {
        private val RECORD_RESERVED_MEMBERS = setOf(
            "handler",
            "Companion",
            "ByReference",
            "ByValue",
            "allocate",
            "allocateArray",
            "invoke",
        )

        fun create(
            scoped: Declaration.Scoped,
            callbackBindings: ValidatedCallbackBindings,
        ): KotlinKmpNamePlan {
            val cTopLevelNames = KotlinKmpExternalNameCollector.collect(scoped, callbackBindings)
            val runtimeAllocator = KotlinIdentifierAllocator(cTopLevelNames)
            val runtimeNames = KotlinKmpRuntimeSymbol.entries.associateWith { symbol ->
                if (symbol.preserveOperatorName && symbol.preferredName !in cTopLevelNames) {
                    symbol.preferredName
                } else if (symbol.preferredName !in cTopLevelNames) {
                    runtimeAllocator.allocate(symbol.preferredName, "KffiRuntime")
                } else {
                    runtimeAllocator.allocate("Kffi${symbol.preferredName}", "KffiRuntime")
                }
            }
            val declarationNames = IdentityHashMap<Declaration, String>()
            val parameterNames = IdentityHashMap<Declaration.Variable, String>()
            val memberNames = IdentityHashMap<Declaration.Variable, String>()
            val jnaHelperNames = IdentityHashMap<Declaration.Scoped, KotlinKmpJnaHelperNames>()
            val collectedDeclarations = IdentityHashMap<Declaration, Unit>()
            val plannedTopLevelNames = linkedSetOf<String>()
            val topLevelAllocator = KotlinIdentifierAllocator()

            fun allocateTopLevel(declaration: Declaration) {
                if (declarationNames.containsKey(declaration)) return
                val name = topLevelAllocator.allocate(declaration.name(), "declaration")
                declarationNames[declaration] = name
                plannedTopLevelNames += name
            }

            val rootMembers = scoped.members()
            val rootConstants = rootMembers.filterIsInstance<Declaration.Constant>().filterNot(Skip::isPresent)
            val flagTypedefs = rootMembers
                .filterIsInstance<Declaration.Typedef>()
                .filterNot(Skip::isPresent)
                .filter { typedef ->
                    typedef.name() != "WGPUFlags" &&
                        rootConstants.any { it.name().startsWith("${typedef.name()}_") }
                }
            val flagConstants = flagTypedefs.flatMap { typedef ->
                rootConstants.filter { it.name().startsWith("${typedef.name()}_") }
            }.toSet()
            val callbackTypedefs = callbackBindings.callbacks.map { it.typedef }

            fun isOpaqueHandleTypedef(declaration: Declaration.Typedef): Boolean {
                if (callbackTypedefs.any { it === declaration }) return false
                val inner = declaration.type()
                if (inner !is Type.Delegated || inner.kind() != Type.Delegated.Kind.POINTER) return false
                val pointee = inner.type()
                return pointee is Type.Declared &&
                    pointee.tree().kind() == Declaration.Scoped.Kind.STRUCT &&
                    pointee.tree().name().isNotEmpty() &&
                    pointee.tree().name().endsWith("Impl")
            }

            val opaqueHandleAliases = IdentityHashMap<Declaration.Scoped, Declaration.Typedef>()
            rootMembers
                .filterIsInstance<Declaration.Typedef>()
                .filterNot(Skip::isPresent)
                .filter(::isOpaqueHandleTypedef)
                .forEach { typedef ->
                    val pointer = typedef.type() as Type.Delegated
                    val pointee = pointer.type() as Type.Declared
                    opaqueHandleAliases.putIfAbsent(pointee.tree(), typedef)
                }

            rootMembers.forEach { declaration ->
                if (Skip.isPresent(declaration)) return@forEach
                when (declaration) {
                    is Declaration.Function -> allocateTopLevel(declaration)
                    is Declaration.Typedef -> {
                        if (declaration in flagTypedefs || isOpaqueHandleTypedef(declaration)) {
                            allocateTopLevel(declaration)
                        }
                    }
                    is Declaration.Constant -> if (declaration in flagConstants) allocateTopLevel(declaration)
                    is Declaration.Scoped -> when (declaration.kind()) {
                        Declaration.Scoped.Kind.STRUCT,
                        Declaration.Scoped.Kind.UNION,
                        -> {
                            val name = declaration.name()
                            if (
                                name.isNotEmpty() &&
                                !name.contains("unnamed") &&
                                !(name.endsWith("Impl") && declaration.members().isEmpty())
                            ) {
                                allocateTopLevel(declaration)
                            }
                        }
                        Declaration.Scoped.Kind.ENUM -> {
                            val name = declaration.name()
                            if (name.isNotEmpty() && !name.contains("unnamed")) {
                                allocateTopLevel(declaration)
                                if (!isOptionsStyle(name)) {
                                    declaration.members()
                                        .filterIsInstance<Declaration.Constant>()
                                        .filterNot(Skip::isPresent)
                                        .forEach(::allocateTopLevel)
                                }
                            }
                        }
                        else -> Unit
                    }
                    else -> Unit
                }
            }

            val collector = object {
                fun collectType(type: Type) {
                    when (type) {
                        is Type.Declared -> collect(type.tree())
                        is Type.Delegated -> collectType(type.type())
                        is Type.Array -> collectType(type.elementType())
                        is Type.Function -> {
                            collectType(type.returnType())
                            type.argumentTypes().forEach(::collectType)
                        }
                    }
                }

                fun collect(declaration: Declaration) {
                    if (collectedDeclarations.put(declaration, Unit) != null) return
                    if (!declarationNames.containsKey(declaration)) {
                        declarationNames[declaration] = KotlinNameMangler.mangle(declaration.name())
                    }
                    when (declaration) {
                        is Declaration.Function -> {
                            collectType(declaration.type())
                            val parameters = KotlinIdentifierAllocator()
                            declaration.parameters().forEachIndexed { index, parameter ->
                                parameterNames[parameter] = parameters.allocate(parameter.name(), "arg$index")
                                collect(parameter)
                            }
                        }
                        is Declaration.Typedef -> collectType(declaration.type())
                        is Declaration.Variable -> collectType(declaration.type())
                        is Declaration.Scoped -> {
                            if (
                                !Skip.isPresent(declaration) &&
                                declaration.kind() in setOf(Declaration.Scoped.Kind.STRUCT, Declaration.Scoped.Kind.UNION)
                            ) {
                                val fields = declaration.members()
                                    .filterIsInstance<Declaration.Variable>()
                                    .filterNot(Skip::isPresent)
                                val memberAllocator = KotlinIdentifierAllocator(RECORD_RESERVED_MEMBERS)
                                fields.forEach { field ->
                                    memberNames[field] = memberAllocator.allocate(field.name(), "field")
                                }
                                val jnaHelperAllocator = KotlinIdentifierAllocator(fields.map(Declaration.Variable::name))
                                jnaHelperNames[declaration] = KotlinKmpJnaHelperNames(
                                    byReference = jnaHelperAllocator.allocate("ByReference", "JnaByReference"),
                                    byValue = jnaHelperAllocator.allocate("ByValue", "JnaByValue"),
                                )
                            }
                            if (
                                !Skip.isPresent(declaration) &&
                                declaration.kind() == Declaration.Scoped.Kind.ENUM &&
                                isOptionsStyle(declaration.name())
                            ) {
                                val constants = KotlinIdentifierAllocator(OPTIONS_ENUM_RESERVED_MEMBERS)
                                declaration.members()
                                    .filterIsInstance<Declaration.Constant>()
                                    .filterNot(Skip::isPresent)
                                    .forEach { constant ->
                                        declarationNames[constant] = constants.allocate(constant.name(), "constant")
                                    }
                            }
                            declaration.members().forEach(::collect)
                        }
                    }
                }
            }
            collector.collect(scoped)
            val jnaHelperNamesByRecordName = jnaHelperNames.entries.associate { (record, names) ->
                declarationNames.getValue(record) to names
            }

            return KotlinKmpNamePlan(
                topLevelNames = plannedTopLevelNames,
                renderedRuntimeNames = runtimeNames.values.toSet(),
                runtimeNames = runtimeNames,
                declarationNames = declarationNames,
                parameterNames = parameterNames,
                memberNames = memberNames,
                opaqueHandleAliases = opaqueHandleAliases,
                jnaHelperNames = jnaHelperNames,
                jnaHelperNamesByRecordName = jnaHelperNamesByRecordName,
            )
        }

        private val OPTIONS_ENUM_RESERVED_MEMBERS = setOf(
            "rawValue",
            "Companion",
            "plus",
            "contains",
            "o",
        )

        private fun isOptionsStyle(name: String): Boolean =
            name.endsWith("Options") || name.endsWith("Flags") || name.endsWith("Mask")
    }
}
