// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinToplevelBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.cli.DllMap
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import org.graphiks.kextract.kotlin.utils.KotlinNameMangler
import org.graphiks.kextract.kotlin.utils.TypeMapper
import org.graphiks.kextract.pipeline.LayoutUtils
import org.graphiks.kextract.pipeline.Options
import java.util.IdentityHashMap

/**
 * Top-level builder for Kotlin files.
 * Coordinates generation of all declarations (structs, functions, ObjC classes, etc.).
 */
class KotlinToplevelBuilder(
    private val targetPackage: String,
    val className: String,
    private val headerName: String,
    private val libraries: List<Options.Library> = emptyList(),
    private val useSystemLoadLibrary: Boolean = false,
    private val splitOutput: Boolean = false,
    private val variadicArgs: Map<String, Int> = emptyMap(),
    private val win32Abi: Boolean = false,
    private val dllMap: DllMap? = null,
    private val useInitMethod: Boolean = false,
) : Declaration.Visitor<Unit> {
    private val slots = LinkedHashMap<String, SourceBuilder>()
    private val files = mutableListOf<KotlinSourceFile>()

    /** Accumulates initialization statements for the generated init() function. */
    val initSlot: SourceBuilder = SourceBuilder()

    /** Base name derived from header filename (e.g. "AppKit" from "AppKit_h"). */
    private val headerBaseName: String = className.removeSuffix("_h")

    private val headerBuilder get() = KotlinHeaderBuilder(mainSlot, this, variadicArgs)
    private val structBuilder get() = KotlinStructBuilder(mainSlot, this)
    private val typedefBuilder get() = KotlinTypedefBuilder(mainSlot, this)
    private val objcProtocolBuilder get() = KotlinObjCProtocolBuilder(mainSlot, this)
    private val objcCategoryBuilder get() = KotlinObjCCategoryBuilder(mainSlot, this)
    // objcClassBuilder is recreated after the TOPLEVEL pre-scan populates generatedClassNames
    private var objcClassBuilder: KotlinObjCClassBuilder = KotlinObjCClassBuilder(mainSlot, this)

    /** Set after TOPLEVEL pre-scan for split-mode per-class builders. */
    private var _generatedClassNames: Set<String> = emptySet()

    /**
     * Maps each generated class name → superclass name (or null if root).
     * Built during the TOPLEVEL prescan.
     */
    private var _classHierarchy: ClassHierarchy = emptyMap()

    /**
     * Maps each generated class name → set of Kotlin method / property accessor signatures
     * declared directly on that class.  Built during the TOPLEVEL prescan and used for
     * override detection.
     */
    private var _classMethodSignatures: Map<String, Set<String>> = emptyMap()

    private var _externalEnumConstants =
        IdentityHashMap<Declaration.Scoped, MutableList<Declaration.Constant>>()
    private var _topLevelEnumsByName: Map<String, List<Declaration.Scoped>> = emptyMap()
    private val _objcSurfaceEnums = IdentityHashMap<Declaration.Scoped, Boolean>()
    private val _objcSurfaceValueStructs = IdentityHashMap<Declaration.Scoped, Boolean>()
    private val _objcSurfacePointerStructs = IdentityHashMap<Declaration.Scoped, Boolean>()
    private val _objcSurfaceValueRecordKeys = mutableSetOf<String>()
    private val _objcSurfacePointerRecordKeys = mutableSetOf<String>()
    private val _objcSurfaceRecordRepresentatives = IdentityHashMap<Declaration.Scoped, Boolean>()
    private var _objcSurfacePointerTypedefNames: Set<String> = emptySet()
    private var _topLevelDeclarationNames: Set<String> = emptySet()

    /** Counter for round-robin split across multiple function files (avoids <clinit> > 64KB). */
    private var _functionBatch: Int = 0
    private var _functionCount: Int = 0
    private val FUNCTIONS_PER_BATCH = 300

    /**
     * Mutable set of Kotlin signatures already emitted by category extension functions,
     * keyed by the extended class name.  Shared across all [KotlinObjCCategoryBuilder]
     * instances for the same class so that two categories with overlapping method names
     * do not produce "conflicting overloads".
     */
    private val _categorySignatures: MutableMap<String, MutableSet<String>> = mutableMapOf()

    /** Set to true once we synthesise the NSObject root class. */
    private var _nsObjectGenerated: Boolean = false

    private fun getOrCreateSlot(key: String): SourceBuilder = slots.getOrPut(key) {
        val sb = SourceBuilder()
        // When splitting output into multiple compilation units, every file needs its own
        // package declaration and FFM imports.  The main slot gets these in the init block
        // below; sub-slots get them here.
        if (splitOutput && key != "_main") {
            if (targetPackage.isNotEmpty()) {
                sb.appendLine("package ${targetPackage}")
                sb.appendLine()
            }
            sb.appendLine("import java.lang.invoke.*")
            sb.appendLine("import java.lang.foreign.*")
            sb.appendLine("import java.lang.foreign.MemoryLayout.PathElement.*")
            sb.appendLine()
        }
        sb
    }
    private val mainSlot: SourceBuilder get() = getOrCreateSlot("_main")

    /** True if any ObjC declaration was encountered — triggers ObjCRuntime.kt emission. */
    var needsObjCRuntime: Boolean = false
        private set

    /** True when a LOOKUP val was generated (libraries were provided). */
    val hasLookup: Boolean get() = libraries.isNotEmpty() || win32Abi

    /** True when generating Win32 bindings with per-DLL lookups. */
    val isWin32Mode: Boolean get() = win32Abi

    fun mapType(type: org.graphiks.kextract.Type): String = TypeMapper.map(type, win32Abi)

    fun layoutString(type: org.graphiks.kextract.Type): String = LayoutUtils.layoutString(type, win32Abi)

    fun functionDescriptorString(type: org.graphiks.kextract.Type.Function, variadicCount: Int): String =
        LayoutUtils.functionDescriptorString(type, variadicCount, win32Abi)

    /** True when generating an init() method instead of eager static initializers. */
    val isInitMethod: Boolean get() = useInitMethod

    init {
        // Package declaration
        if (targetPackage.isNotEmpty()) {
            mainSlot.appendLine("package ${targetPackage}")
            mainSlot.appendLine()
        }

        // Standard imports
        mainSlot.appendLine("import java.lang.invoke.*")
        mainSlot.appendLine("import java.lang.foreign.*")
        mainSlot.appendLine("import java.lang.foreign.MemoryLayout.PathElement.*")
        mainSlot.appendLine()

        // Helper constants for layouts
        mainSlot.appendLine("private object kextract_runtime {")
        mainSlot.indent()
        mainSlot.appendLine("val C_BOOL: ValueLayout = ValueLayout.JAVA_BOOLEAN")
        mainSlot.appendLine("val C_CHAR: ValueLayout = ValueLayout.JAVA_BYTE")
        mainSlot.appendLine("val C_SHORT: ValueLayout = ValueLayout.JAVA_SHORT")
        mainSlot.appendLine("val C_INT: ValueLayout = ValueLayout.JAVA_INT")
        mainSlot.appendLine("val C_LONG: ValueLayout = ValueLayout.${if (win32Abi) "JAVA_INT" else "JAVA_LONG"}")
        mainSlot.appendLine("val C_LONG_LONG: ValueLayout = ValueLayout.JAVA_LONG")
        if (win32Abi) mainSlot.appendLine("val C_WCHAR: ValueLayout = ValueLayout.JAVA_CHAR")
        mainSlot.appendLine("val C_FLOAT: ValueLayout = ValueLayout.JAVA_FLOAT")
        mainSlot.appendLine("val C_DOUBLE: ValueLayout = ValueLayout.JAVA_DOUBLE")
        mainSlot.appendLine("val C_POINTER: ValueLayout = ValueLayout.ADDRESS")
        mainSlot.unindent()
        mainSlot.appendLine("}")
        mainSlot.appendLine()

        // Symbol lookup — loads native libraries and exposes a single LOOKUP
        if (libraries.isNotEmpty()) {
            mainSlot.appendLine("private val LOOKUP: SymbolLookup = run {")
            mainSlot.indent()
            if (useSystemLoadLibrary) {
                for (lib in libraries) {
                    mainSlot.appendLine("System.loadLibrary(\"${lib.libSpec}\")")
                }
                mainSlot.appendLine("SymbolLookup.loaderLookup()")
            } else {
                mainSlot.appendLine("var lu: SymbolLookup = SymbolLookup.loaderLookup()")
                for (lib in libraries) {
                    val lookup = when (lib.specKind) {
                        Options.Library.SpecKind.PATH ->
                            "SymbolLookup.libraryLookup(\"${Options.Library.toQuotedName(lib)}\", Arena.global())"
                        Options.Library.SpecKind.NAME ->
                            "SymbolLookup.libraryLookup(\"${lib.libSpec}\", Arena.global())"
                    }
                    mainSlot.appendLine("lu = $lookup.or(lu)")
                }
                mainSlot.appendLine("lu")
            }
            mainSlot.unindent()
            mainSlot.appendLine("}")
            mainSlot.appendLine()
        }

        // Win32 mode: generate per-DLL lookups with cross-platform try/catch safety
        if (win32Abi && dllMap != null) {
            val dllNames = dllMap.dllMap.keys.toSortedSet()
            for (dllName in dllNames) {
                val varName = dllLookupVarName(dllName)
                if (useInitMethod) {
                    mainSlot.appendLine("private var $varName: SymbolLookup? = null")
                    initSlot.appendLine("$varName = try {")
                    initSlot.indent()
                    initSlot.appendLine("SymbolLookup.libraryLookup(\"$dllName\", Arena.global())")
                    initSlot.unindent()
                    initSlot.appendLine("} catch (ex: Throwable) {")
                    initSlot.indent()
                    initSlot.appendLine("null")
                    initSlot.unindent()
                    initSlot.appendLine("}")
                } else {
                    mainSlot.appendLine("private val $varName: SymbolLookup? = try {")
                    mainSlot.indent()
                    mainSlot.appendLine("SymbolLookup.libraryLookup(\"$dllName\", Arena.global())")
                    mainSlot.unindent()
                    mainSlot.appendLine("} catch (ex: Throwable) {")
                    mainSlot.indent()
                    mainSlot.appendLine("null")
                    mainSlot.unindent()
                    mainSlot.appendLine("}")
                }
            }
            mainSlot.appendLine()

            if (useInitMethod) {
                mainSlot.appendLine("@Volatile private var _initialized: Boolean = false")
                mainSlot.appendLine()
            }

            // Build symbol→DLL mapping for the _lookup helper
            val dllSymbols = linkedMapOf<String, MutableList<String>>()
            for ((dll, entry) in dllMap.dllMap) {
                val syms = dllSymbols.getOrPut(dll) { mutableListOf() }
                syms.addAll(entry.functions)
                syms.addAll(entry.constants)
            }

            mainSlot.appendLine("private fun _lookup(symbol: String): SymbolLookup {")
            mainSlot.indent()
            mainSlot.appendLine("return when (symbol) {")
            mainSlot.indent()
            for ((dll, syms) in dllSymbols) {
                val varName = dllLookupVarName(dll)
                mainSlot.appendLine("${syms.joinToString(", ") { "\"$it\"" }} -> $varName ?: SymbolLookup.loaderLookup()")
            }
            mainSlot.appendLine("else -> SymbolLookup.loaderLookup()")
            mainSlot.unindent()
            mainSlot.appendLine("}")
            mainSlot.unindent()
            mainSlot.appendLine("}")
            mainSlot.appendLine()
        }
    }

    override fun visitScoped(decl: Declaration.Scoped) {
        if (Skip.isPresent(decl)) return
        if (!shouldEmitObjCSurfaceRecord(decl)) return
        when (decl.kind()) {
            Declaration.Scoped.Kind.STRUCT -> {
                if (splitOutput) {
                    KotlinStructBuilder(getOrCreateSlot("types"), this).visitStruct(decl)
                } else {
                    structBuilder.visitStruct(decl)
                }
            }
            Declaration.Scoped.Kind.UNION  -> {
                if (splitOutput) {
                    KotlinStructBuilder(getOrCreateSlot("types"), this).visitUnion(decl)
                } else {
                    structBuilder.visitUnion(decl)
                }
            }
            Declaration.Scoped.Kind.ENUM   -> {
                // Only generate for named enums with constants.
                // Anonymous enums (name == "") appear only as typedef targets and are never
                // emitted here since the typedef path handles them via typealias.
                // For ObjC fixed-underlying-type enums (typedef enum : long { … } Foo),
                // clang creates a named ENUM scoped with the typedef name, and the redundant
                // typedef is filtered — so this is the only place we emit the enum class.
                if (decl.name().isNotEmpty()) {
                    val externalConstants = _externalEnumConstants[decl].orEmpty()
                    val target = if (splitOutput) {
                        val slotKey = if (KotlinEnumSupport.isOptionsStyle(decl)) "options" else "enums"
                        getOrCreateSlot(slotKey)
                    } else {
                        mainSlot
                    }
                    KotlinEnumBuilder(target, this, externalConstants).visitEnum(decl)
                }
            }
            else -> {
                // TOPLEVEL: pre-scan before generating code.
                if (decl.kind() == Declaration.Scoped.Kind.TOPLEVEL) {
                    _topLevelDeclarationNames = decl.members().map { javaName(it.name()) }.toSet()
                    // Mark constants from named ENUMs inside TOPLEVEL as Skip so they are not
                    // re-visited as standalone items. They will be emitted inside their enum.
                    decl.members()
                        .filterIsInstance<Declaration.Scoped>()
                        .filter { it.kind() == Declaration.Scoped.Kind.ENUM && it.name().isNotEmpty() && !Skip.isPresent(it) }
                        .forEach { enumScoped ->
                            enumScoped.members()
                                .filterIsInstance<Declaration.Constant>()
                                .forEach { constant ->
                                    Skip.with(constant)
                                }
                        }
                    collectExternalEnumConstants(decl)
                    markObjCSurfaceTypes(decl)
                    // Collect generated ObjCClass names so the class builder can emit superclass
                    // clauses only for classes that will actually be generated (GRA-79).
                    val generatedObjCClassNames = decl.members()
                        .filterIsInstance<Declaration.ObjCClass>()
                        .filter { !Skip.isPresent(it) }
                        .map { it.name() }
                        .toSet()
                    val modifiedClassNames = generatedObjCClassNames.toMutableSet()
                    // If a generated class references NSObject as superclass but NSObject is
                    // itself not being generated (it lives in the SDK but outside the
                    // --include-framework filter), synthesize it so that:
                    //   1. Subclasses can emit ": NSObject(ptr)" and "override val ptr"
                    //   2. Categories on NSObject (extension functions) can resolve "this.ptr"
                    val needsNSObject = generatedObjCClassNames.any { clsName ->
                        decl.members()
                            .filterIsInstance<Declaration.ObjCClass>()
                            .firstOrNull { it.name() == clsName && !Skip.isPresent(it) }
                            ?.superClass() == "NSObject"
                    }
                    if (needsNSObject && "NSObject" !in generatedObjCClassNames) {
                        modifiedClassNames.add("NSObject")
                    }
                    _generatedClassNames = modifiedClassNames

                    // Build class hierarchy and method-signature maps for override detection.
                    val hierarchy = mutableMapOf<String, String?>()
                    val methodSigs = mutableMapOf<String, Set<String>>()
                    for (cls in decl.members().filterIsInstance<Declaration.ObjCClass>().filter { !Skip.isPresent(it) }) {
                        hierarchy[cls.name()] = cls.superClass()
                        methodSigs[cls.name()] = extractClassSignatures(cls)
                    }
                    _classHierarchy = hierarchy
                    _classMethodSignatures = methodSigs
                    objcClassBuilder = KotlinObjCClassBuilder(
                        mainSlot, this, generatedObjCClassNames,
                        hierarchy, methodSigs
                    )
                }
                // Process all members
                for (d in decl.members()) {
                    d.accept(this)
                }
                // After all ObjC classes have been visited, synthesise NSObject if it was
                // added to _generatedClassNames during the prescan but never visited.
                if ("NSObject" in _generatedClassNames && !_nsObjectGenerated) {
                    generateNSObjectClass()
                }
            }
        }

        // Only add file for TOPLEVEL scoped (not for nested structs/unions)
        if (decl.kind() == Declaration.Scoped.Kind.TOPLEVEL) {
            if (useInitMethod) {
                mainSlot.appendLine()
                mainSlot.appendLine("/**")
                mainSlot.appendLine(" * Initializes all Win32 bindings.")
                mainSlot.appendLine(" * Must be called before any binding function on Windows.")
                mainSlot.appendLine(" * Safe to call on non-Windows (no-op, all symbols stay null).")
                mainSlot.appendLine(" */")
                mainSlot.appendLine("@Synchronized")
                mainSlot.appendLine("fun init() {")
                mainSlot.indent()
                mainSlot.appendLine("if (_initialized) return")
                mainSlot.appendBlock(initSlot.toString().trimEnd())
                mainSlot.appendLine()
                mainSlot.appendLine("_initialized = true")
                mainSlot.unindent()
                mainSlot.appendLine("}")
                mainSlot.appendLine()
            }
            if (!splitOutput) {
                files.add(KotlinSourceFile(targetPackage, className, mainSlot.toString()))
            }
        }
    }

    override fun visitFunction(decl: Declaration.Function) {
        if (Skip.isPresent(decl)) return
        if (splitOutput) {
            val slotKey = if (_functionBatch == 0) "functions" else "functions$_functionBatch"
            KotlinHeaderBuilder(getOrCreateSlot(slotKey), this, variadicArgs).visitFunction(decl)
            _functionCount++
            if (_functionCount >= FUNCTIONS_PER_BATCH) {
                _functionCount = 0
                _functionBatch++
            }
        } else {
            headerBuilder.visitFunction(decl)
        }
    }

    override fun visitVariable(decl: Declaration.Variable) {
        if (Skip.isPresent(decl)) return
        if (splitOutput) {
            val slotKey = if (_functionBatch == 0) "functions" else "functions$_functionBatch"
            KotlinHeaderBuilder(getOrCreateSlot(slotKey), this, variadicArgs).visitVariable(decl)
            _functionCount++
            if (_functionCount >= FUNCTIONS_PER_BATCH) {
                _functionCount = 0
                _functionBatch++
            }
        } else {
            headerBuilder.visitVariable(decl)
        }
    }

    override fun visitTypedef(decl: Declaration.Typedef) {
        if (Skip.isPresent(decl)) return
        val sb = if (splitOutput) getOrCreateSlot("types") else mainSlot
        KotlinTypedefBuilder(sb, this).visitTypedef(decl)
    }

    override fun visitConstant(decl: Declaration.Constant) {
        if (Skip.isPresent(decl)) return
        if (splitOutput) {
            KotlinHeaderBuilder(getOrCreateSlot("enums"), this, variadicArgs).visitConstant(decl)
        } else {
            headerBuilder.visitConstant(decl)
        }
    }

    override fun visitObjCClass(decl: Declaration.ObjCClass) {
        if (Skip.isPresent(decl)) return
        needsObjCRuntime = true
        if (splitOutput) {
            val sb = getOrCreateSlot("class.${decl.name()}")
            KotlinObjCClassBuilder(sb, this, _generatedClassNames, _classHierarchy, _classMethodSignatures).visitClass(decl)
        } else {
            objcClassBuilder.visitClass(decl)
        }
    }

    private fun generateNSObjectClass() {
        if (!splitOutput) return
        _nsObjectGenerated = true
        val sb = getOrCreateSlot("class.NSObject")
        sb.appendLine("/**")
        sb.appendLine(" * Kotlin/JVM wrapper for root class NSObject.")
        sb.appendLine(" * Synthesised because it is referenced as a superclass by generated classes")
        sb.appendLine(" * but was not included in the framework filter set.")
        sb.appendLine(" */")
        sb.appendLine("open class NSObject(open val ptr: MemorySegment) {")
        sb.indent()
        sb.appendLine("companion object {")
        sb.indent()
        sb.appendLine("private val _class: MemorySegment by lazy { ObjCRuntime.getClass(\"NSObject\") }")
        sb.unindent()
        sb.appendLine("}")
        sb.appendLine()
        sb.unindent()
        sb.appendLine("}")
        sb.appendLine()
    }

    override fun visitObjCProtocol(decl: Declaration.ObjCProtocol) {
        if (Skip.isPresent(decl)) return
        needsObjCRuntime = true
        // Skip protocols whose name collides with a generated class (e.g. NSAccessibilityElement
        // exists as both @interface and @protocol) — the class takes precedence.
        if (decl.name() in _generatedClassNames) return
        if (splitOutput) {
            val sb = getOrCreateSlot("protocol.${decl.name()}")
            KotlinObjCProtocolBuilder(sb, this, _generatedClassNames).visitProtocol(decl)
        } else {
            objcProtocolBuilder.visitProtocol(decl)
        }
    }

    override fun visitObjCCategory(decl: Declaration.ObjCCategory) {
        if (Skip.isPresent(decl)) return
        needsObjCRuntime = true
        if (splitOutput) {
            val sb = getOrCreateSlot("class.${decl.extendedClass()}")
            val classSigs = _classMethodSignatures[decl.extendedClass()] ?: emptySet()
            val shared = _categorySignatures.getOrPut(decl.extendedClass()) {
                classSigs.toMutableSet()
            }
            KotlinObjCCategoryBuilder(sb, this, shared).visitCategory(decl)
        } else {
            objcCategoryBuilder.visitCategory(decl)
        }
    }

    fun getFiles(): List<KotlinSourceFile> {
        if (splitOutput) {
            return slots.map { (key, sb) ->
                val (subdir, name) = slotToFile(key)
                KotlinSourceFile(targetPackage, name, sb.toString(), subdir)
            }
        }
        return files
    }

    private fun slotToFile(key: String): Pair<String, String> = when {
        key == "_main" -> "" to className
        key == "types" -> "types" to "${headerBaseName}Types"
        key == "enums" -> "enums" to "${headerBaseName}Enums"
        key == "options" -> "options" to "${headerBaseName}Options"
        key == "functions" -> "functions" to "${headerBaseName}Functions"
        key.startsWith("functions") -> "functions" to "${headerBaseName}Functions_${key.removePrefix("functions")}"
        key.startsWith("class.") -> "classes" to key.removePrefix("class.")
        key.startsWith("protocol.") -> "protocols" to key.removePrefix("protocol.")
        else -> "" to key.replace('.', '_')
    }

    private fun numericValue(value: Any): Long? = when (value) {
        is Long -> value
        is Int -> value.toLong()
        else -> null
    }

    private fun collectExternalEnumConstants(decl: Declaration.Scoped) {
        val enumIndex = linkedMapOf<String, MutableList<Declaration.Scoped>>()
        decl.members()
            .filterIsInstance<Declaration.Scoped>()
            .filter {
                it.kind() == Declaration.Scoped.Kind.ENUM &&
                    it.name().isNotEmpty()
            }
            .forEach { enumDecl ->
                val candidates = enumIndex.getOrPut(enumDecl.name()) { mutableListOf() }
                if (candidates.none { it === enumDecl }) candidates.add(enumDecl)
            }
        _topLevelEnumsByName = enumIndex

        val external = IdentityHashMap<Declaration.Scoped, MutableList<Declaration.Constant>>()
        for (constant in decl.members().filterIsInstance<Declaration.Constant>()) {
            if (Skip.isPresent(constant)) continue
            val value = numericValue(constant.value()) ?: continue
            val resolvedEnum = KotlinEnumSupport.resolveEnum(constant.type()) ?: continue
            val generatedEnum = resolveGeneratedEnum(resolvedEnum) ?: continue
            val exactDuplicate = generatedEnum.members()
                .filterIsInstance<Declaration.Constant>()
                .any {
                    it.name() == constant.name() && numericValue(it.value()) == value
                }
            if (exactDuplicate) {
                Skip.with(constant)
                continue
            }
            external.getOrPut(generatedEnum) { mutableListOf() }.add(constant)
        }
        _externalEnumConstants = external
    }

    fun generatedEnumKotlinName(enumDecl: Declaration.Scoped): String? =
        resolveGeneratedEnum(enumDecl)?.let { javaName(it.name()) }

    fun isObjCSurfaceEnum(enumDecl: Declaration.Scoped): Boolean =
        _objcSurfaceEnums.containsKey(enumDecl)

    fun isObjCSurfaceStruct(structDecl: Declaration.Scoped): Boolean =
        _objcSurfaceValueStructs.containsKey(structDecl) ||
            recordKey(structDecl)?.let(_objcSurfaceValueRecordKeys::contains) == true

    fun isObjCSurfacePointerStruct(structDecl: Declaration.Scoped): Boolean =
        _objcSurfacePointerStructs.containsKey(structDecl) ||
            recordKey(structDecl)?.let(_objcSurfacePointerRecordKeys::contains) == true

    internal fun hasObjCSurfacePointerTypedef(name: String): Boolean =
        name in _objcSurfacePointerTypedefNames

    internal fun typedCAdapterName(rawName: String): String {
        var candidate = "${rawName}Typed"
        while (candidate in _topLevelDeclarationNames) candidate += "Adapter"
        return candidate
    }

    internal fun resolveObjCEnum(type: org.graphiks.kextract.Type): Declaration.Scoped? {
        KotlinEnumSupport.resolveEnum(type)?.let { resolved ->
            resolveGeneratedEnum(resolved)?.let { return it }
        }
        var current = type
        while (current is org.graphiks.kextract.Type.Delegated &&
            current.kind() != org.graphiks.kextract.Type.Delegated.Kind.POINTER) {
            if (current.kind() == org.graphiks.kextract.Type.Delegated.Kind.TYPEDEF) {
                val candidates = _topLevelEnumsByName[current.name()].orEmpty()
                    .filterNot(Skip::isPresent)
                if (candidates.size == 1) return candidates.single()
            }
            current = current.type()
        }
        return null
    }

    internal fun hasGeneratedEnum(name: String): Boolean =
        _topLevelEnumsByName[name].orEmpty().count { !Skip.isPresent(it) } == 1

    private fun markObjCSurfaceTypes(toplevel: Declaration.Scoped) {
        fun markPointerStruct(struct: Declaration.Scoped) {
            _objcSurfacePointerStructs[struct] = true
            recordKey(struct)?.let(_objcSurfacePointerRecordKeys::add)
        }

        fun markValueStruct(struct: Declaration.Scoped) {
            if (_objcSurfaceValueStructs.put(struct, true) != null) return
            recordKey(struct)?.let(_objcSurfaceValueRecordKeys::add)
            markPointerStruct(struct)
            for (field in struct.members().filterIsInstance<Declaration.Variable>()) {
                TypeMapper.namedStruct(field.type())?.declaration?.let(::markValueStruct)
                TypeMapper.pointedStruct(field.type())?.declaration?.let(::markPointerStruct)
                resolveObjCEnum(field.type())?.let { _objcSurfaceEnums[it] = true }
            }
        }

        fun mark(type: org.graphiks.kextract.Type) {
            TypeMapper.namedStruct(type)?.declaration?.let(::markValueStruct)
            TypeMapper.pointedStruct(type)?.declaration?.let(::markPointerStruct)
            val generated = resolveObjCEnum(type) ?: return
            _objcSurfaceEnums[generated] = true
        }

        for (declaration in toplevel.members()) {
            if (Skip.isPresent(declaration)) continue
            when (declaration) {
                is Declaration.ObjCClass -> {
                    declaration.methods().forEach { method ->
                        mark(method.returnType())
                        method.parameters().forEach { mark(it.type()) }
                    }
                    declaration.properties().forEach { mark(it.type()) }
                }
                is Declaration.ObjCProtocol -> {
                    declaration.methods().forEach { method ->
                        mark(method.returnType())
                        method.parameters().forEach { mark(it.type()) }
                    }
                    declaration.properties().forEach { mark(it.type()) }
                }
                is Declaration.ObjCCategory -> {
                    declaration.methods().forEach { method ->
                        mark(method.returnType())
                        method.parameters().forEach { mark(it.type()) }
                    }
                    declaration.properties().forEach { mark(it.type()) }
                }
            }
        }

        fun markCValueUse(
            type: org.graphiks.kextract.Type,
            pointerStructs: IdentityHashMap<Declaration.Scoped, Boolean>,
        ) {
            val struct = TypeMapper.namedStruct(type)
            if (struct != null) {
                if (pointerStructs.containsKey(struct.declaration)) {
                    markValueStruct(struct.declaration)
                }
                return
            }
            when {
                type is org.graphiks.kextract.Type.Array ->
                    markCValueUse(type.elementType(), pointerStructs)
                type is org.graphiks.kextract.Type.Delegated &&
                    type.kind() != org.graphiks.kextract.Type.Delegated.Kind.POINTER ->
                    markCValueUse(type.type(), pointerStructs)
            }
        }

        // Resolve direct C consumers before legacy record fields so declaration order cannot
        // decide whether a field's record is already pointer-capable.
        do {
            val valueCount = _objcSurfaceValueStructs.size
            val pointerCount = _objcSurfacePointerStructs.size
            for (declaration in toplevel.members()) {
                if (Skip.isPresent(declaration)) continue
                when (declaration) {
                    is Declaration.Function -> {
                        markCValueUse(declaration.type().returnType(), _objcSurfacePointerStructs)
                        declaration.type().argumentTypes().forEach {
                            markCValueUse(it, _objcSurfacePointerStructs)
                        }
                    }
                    is Declaration.Variable ->
                        markCValueUse(declaration.type(), _objcSurfacePointerStructs)
                }
            }
        } while (
            valueCount != _objcSurfaceValueStructs.size ||
            pointerCount != _objcSurfacePointerStructs.size
        )

        // Keep the direct-consumer provenance stable. Pointer wrappers discovered while
        // materializing legacy layouts must not retroactively turn their owner non-legacy.
        val directPointerStructs =
            IdentityHashMap<Declaration.Scoped, Boolean>(_objcSurfacePointerStructs)
        do {
            val valueCount = _objcSurfaceValueStructs.size
            val pointerCount = _objcSurfacePointerStructs.size
            for (declaration in toplevel.members()) {
                if (Skip.isPresent(declaration)) continue
                if (declaration is Declaration.Scoped) {
                    val isLegacyRecord =
                        (declaration.kind() == Declaration.Scoped.Kind.STRUCT ||
                            declaration.kind() == Declaration.Scoped.Kind.UNION) &&
                            !directPointerStructs.containsKey(declaration)
                    if (isLegacyRecord) {
                        declaration.members()
                            .filterIsInstance<Declaration.Variable>()
                            .forEach {
                                markCValueUse(it.type(), _objcSurfacePointerStructs)
                            }
                    }
                }
            }
        } while (
            valueCount != _objcSurfaceValueStructs.size ||
            pointerCount != _objcSurfacePointerStructs.size
        )

        selectObjCSurfaceRecordRepresentatives(toplevel)

        _objcSurfacePointerTypedefNames = toplevel.members()
            .filterIsInstance<Declaration.Typedef>()
            .filterNot(Skip::isPresent)
            .mapNotNull { typedef ->
                TypeMapper.pointedStruct(typedef.type())
                    ?.takeIf { isObjCSurfacePointerStruct(it.declaration) }
                    ?.let { javaName(typedef.name()) }
            }
            .toSet()
    }

    private fun selectObjCSurfaceRecordRepresentatives(toplevel: Declaration.Scoped) {
        _objcSurfaceRecordRepresentatives.clear()
        for (key in _objcSurfacePointerRecordKeys) {
            val candidates = toplevel.members()
                .filterIsInstance<Declaration.Scoped>()
                .filterNot(Skip::isPresent)
                .filter { recordKey(it) == key }
            val exactUses = if (key in _objcSurfaceValueRecordKeys) {
                _objcSurfaceValueStructs
            } else {
                _objcSurfacePointerStructs
            }
            val representative = candidates.firstOrNull(exactUses::containsKey)
                ?: candidates.maxByOrNull { candidate ->
                    (if (candidate.members().isNotEmpty()) 1 else 0)
                }
            if (representative != null) {
                _objcSurfaceRecordRepresentatives[representative] = true
            }
        }
    }

    private fun shouldEmitObjCSurfaceRecord(record: Declaration.Scoped): Boolean {
        val key = recordKey(record) ?: return true
        return key !in _objcSurfacePointerRecordKeys ||
            _objcSurfaceRecordRepresentatives.containsKey(record)
    }

    private fun recordKey(record: Declaration.Scoped): String? =
        record.name().takeIf(String::isNotEmpty)?.let { "${record.kind()}:$it" }

    private fun resolveGeneratedEnum(enumDecl: Declaration.Scoped): Declaration.Scoped? {
        val candidates = _topLevelEnumsByName[enumDecl.name()].orEmpty()
        val exact = candidates.firstOrNull { it === enumDecl }
        if (exact != null) return if (Skip.isPresent(exact)) null else exact

        return candidates.filterNot(Skip::isPresent).singleOrNull()
    }

    fun javaName(name: String): String = KotlinNameMangler.mangle(name)

    fun lookupName(decl: Declaration): String = decl.name()

    /** Converts a DLL filename to a valid Kotlin identifier for the lookup variable. */
    private fun dllLookupVarName(dll: String): String =
        "_DLL_" + dll.uppercase().replace(Regex("[^A-Z0-9_]"), "_")

    /**
     * Extracts the set of Kotlin method and property-accessor signatures from an ObjC class
     * declaration.  These are used downstream to detect method overrides.
     */
    private fun extractClassSignatures(decl: Declaration.ObjCClass): Set<String> {
        val sigs = mutableSetOf<String>()
        for (method in decl.methods()) {
            sigs.add(KotlinObjCClassBuilder.kotlinName(method.selector()))
        }
        for (prop in decl.properties()) {
            sigs.add(KotlinObjCClassBuilder.kotlinName(prop.getterSelector()))
            if (!prop.isReadOnly()) {
                sigs.add(KotlinObjCClassBuilder.kotlinName(prop.setterSelector().removeSuffix(":")))
            }
        }
        return sigs
    }
}
