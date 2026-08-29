// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinHeaderBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.pipeline.LayoutUtils
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Generates Kotlin code for functions, variables, and constants.
 */
class KotlinHeaderBuilder(
    private val builder: SourceBuilder,
    private val toplevel: KotlinToplevelBuilder,
    private val variadicArgs: Map<String, Int> = emptyMap(),
) {

    private val typeLowerer = CFunctionTypeLowerer(toplevel)

    fun visitFunction(decl: Declaration.Function) {
        val name = toplevel.javaName(decl.name())
        val returnLowering = typeLowerer.lower(decl.type().returnType())
        val returnType = returnLowering.kotlinType
        val returnsStruct = isStructType(decl.type().returnType())
        val params = paramString(decl, returnsStruct)
        val allocatorPrefix = if (returnsStruct) "allocator, " else ""
        val fixedCount = decl.type().argumentTypes().size
        val variadicCount = if (decl.type().varargs()) variadicArgs[decl.name()] ?: 0 else 0
        val paramNames = allocatorPrefix + (0 until fixedCount + variadicCount).joinToString(", ") { index ->
            if (index < fixedCount) {
                typeLowerer.lower(decl.type().argumentTypes()[index]).lowerArgument("arg$index")
            } else {
                "arg$index"
            }
        }

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : ${decl.name()} ${decl.type()}")
        builder.appendLine(" */")

        // Function descriptor, address and handle (toplevel properties)
        builder.appendLine("private val ${name}_DESC: FunctionDescriptor = ${functionDescriptorString(decl)}")
        val lookupExpr = when {
            toplevel.isWin32Mode -> "_lookup(\"${toplevel.lookupName(decl)}\")"
            toplevel.hasLookup -> "LOOKUP"
            else -> "SymbolLookup.loaderLookup()"
        }
        val lookupName = toplevel.lookupName(decl)

        if (toplevel.isInitMethod) {
            builder.appendLine("private var ${name}_HANDLE: MethodHandle? = null")
            builder.appendLine()
            toplevel.initSlot.appendLine(
                "${name}_HANDLE = $lookupExpr.find(\"$lookupName\")" +
                ".map { Linker.nativeLinker().downcallHandle(it, ${name}_DESC) }.orElse(null)"
            )
        } else {
            // Use find(name).orElseThrow() rather than findOrThrow(name) for compatibility with
            // older Kotlin/JDK toolchains that haven't seen SymbolLookup.findOrThrow (added JDK 22).
            builder.appendLine(
                "private val ${name}_ADDR: MemorySegment = $lookupExpr.find(\"$lookupName\").orElseThrow()"
            )
            if (variadicCount > 0) {
                builder.appendLine("private val ${name}_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(")
                builder.appendLine("    ${name}_ADDR, ${name}_DESC,")
                builder.appendLine("    Linker.Option.firstVariadicArg(${fixedCount}),")
                builder.appendLine(")")
            } else {
                builder.appendLine("private val ${name}_HANDLE: MethodHandle = Linker.nativeLinker().downcallHandle(${name}_ADDR, ${name}_DESC)")
            }
            builder.appendLine()
        }

        // Function
        val isVoid = returnType == "Unit"
        builder.appendLine("fun ${name}(${params}): ${returnType} {")
        builder.indent()
        if (toplevel.isInitMethod) {
            builder.appendLine("check(_initialized) { \"Win32 $name called before init()\" }")
            builder.appendLine("val _handle = ${name}_HANDLE ?: return${if (isVoid) "" else " ${returnDefault(returnType)}"}")
        }
        builder.appendLine("try {")
        builder.indent()
        if (toplevel.isInitMethod) {
            if (isVoid) {
                builder.appendLine("_handle.invokeExact(${paramNames})")
            } else {
                builder.appendLine("return ${returnLowering.reconstruct("_handle.invokeExact(${paramNames})")}")
            }
        } else {
            if (isVoid) {
                builder.appendLine("${name}_HANDLE.invokeExact(${paramNames})")
            } else {
                builder.appendLine("return ${returnLowering.reconstruct("${name}_HANDLE.invokeExact(${paramNames})")}")
            }
        }
        builder.unindent()
        builder.appendLine("} catch (ex: Error) {")
        builder.indent()
        builder.appendLine("throw ex")
        builder.unindent()
        builder.appendLine("} catch (ex: RuntimeException) {")
        builder.indent()
        builder.appendLine("throw ex")
        builder.unindent()
        builder.appendLine("} catch (ex: Throwable) {")
        builder.indent()
        if (toplevel.isInitMethod && isVoid) {
            builder.appendLine("// ignore — null handle on non-Windows")
        } else if (toplevel.isInitMethod) {
            builder.appendLine("return ${returnDefault(returnType)}")
        } else {
            builder.appendLine("throw AssertionError(\"should not reach here\", ex)")
        }
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()

        emitTypedStructAdapter(decl, name)
    }

    /**
     * Bridges structs shared by raw C and Objective-C surfaces without exposing
     * the nominal wrapper's internal MemorySegment carrier to consumers.
     */
    private fun emitTypedStructAdapter(decl: Declaration.Function, rawName: String) {
        if (decl.type().varargs()) return
        val returnRecord = TypeMapper.namedStruct(decl.type().returnType())
            ?.takeIf { toplevel.isObjCSurfaceStruct(it.declaration) }
        val argumentRecords = decl.type().argumentTypes().map { type ->
            TypeMapper.namedStruct(type)?.takeIf { toplevel.isObjCSurfaceStruct(it.declaration) }
        }
        if (returnRecord == null && argumentRecords.all { it == null }) return

        val adapterName = if (argumentRecords.any { it != null }) {
            rawName
        } else {
            toplevel.typedCAdapterName(rawName)
        }
        val rawReturnsStruct = isStructType(decl.type().returnType())
        val parameters = buildList {
            if (rawReturnsStruct) add("allocator: SegmentAllocator")
            decl.type().argumentTypes().forEachIndexed { index, type ->
                val record = argumentRecords[index]
                val kotlinType = record?.let { toplevel.javaName(it.publicName) }
                    ?: toplevel.mapType(type)
                add("arg$index: $kotlinType")
            }
        }.joinToString(", ")
        val returnType = returnRecord?.let { toplevel.javaName(it.publicName) }
            ?: toplevel.mapType(decl.type().returnType())
        val rawArguments = buildList {
            if (rawReturnsStruct) add("allocator")
            argumentRecords.forEachIndexed { index, record ->
                add(if (record == null) "arg$index" else "arg$index.segment")
            }
        }.joinToString(", ")
        val rawCall = "$rawName($rawArguments)"

        builder.appendLine("fun $adapterName($parameters): $returnType {")
        builder.indent()
        when {
            returnRecord != null -> {
                val recordName = toplevel.javaName(returnRecord.publicName)
                builder.appendLine("return $recordName($rawCall)")
            }
            returnType == "Unit" -> builder.appendLine(rawCall)
            else -> builder.appendLine("return $rawCall")
        }
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun isStructType(type: Type): Boolean = when (type) {
        is Type.Declared -> {
            val tree = type.tree()
            tree is Declaration.Scoped &&
                (tree.kind() == Declaration.Scoped.Kind.STRUCT ||
                 tree.kind() == Declaration.Scoped.Kind.UNION)
        }
        is Type.Delegated -> when (type.kind()) {
            Type.Delegated.Kind.TYPEDEF -> isStructType(type.type())
            else -> false
        }
        else -> false
    }

    fun visitVariable(decl: Declaration.Variable) {
        val name = toplevel.javaName(decl.name())
        val type = toplevel.mapType(decl.type())
        val lookupName = toplevel.lookupName(decl)

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : ${decl.name()} ${decl.type()}")
        builder.appendLine(" */")

        val varLookupExpr = when {
            toplevel.isWin32Mode -> "_lookup(\"$lookupName\")"
            toplevel.hasLookup -> "LOOKUP"
            else -> "SymbolLookup.loaderLookup()"
        }

        // Aggregate-typed global (struct/union/array) → expose its address segment directly,
        // because a scalar VarHandle cannot get/set a whole record at once.
        if (isAggregateGlobal(decl.type())) {
            if (toplevel.isInitMethod) {
                builder.appendLine("private var ${name}: MemorySegment? = null")
                builder.appendLine()
                toplevel.initSlot.appendLine(
                    "${name} = $varLookupExpr.find(\"$lookupName\").orElse(null)"
                )
            } else {
                builder.appendLine(
                    "val ${name}: MemorySegment = $varLookupExpr.find(\"$lookupName\").orElseThrow()"
                )
                builder.appendLine()
            }
            return
        }

        val isStruct = isStructType(decl.type())

        // Variable Layout, Segment and Handle (toplevel properties)
        // Struct types use MemoryLayout (GroupLayout) instead of ValueLayout
        // Use `by lazy` to keep <clinit> small.
        val layoutType = if (isStruct) "MemoryLayout" else "ValueLayout"
        builder.appendLine("private val ${name}_LAYOUT: $layoutType by lazy { ${layoutString(decl.type())} }")

        if (toplevel.isInitMethod) {
            builder.appendLine("private var ${name}_SEGMENT: MemorySegment? = null")
            builder.appendLine("private var ${name}_VH: VarHandle? = null")
            builder.appendLine()
            toplevel.initSlot.appendLine(
                "${name}_SEGMENT = $varLookupExpr.find(\"$lookupName\").orElse(null)"
            )
            toplevel.initSlot.appendLine(
                "${name}_VH = ${name}_SEGMENT?.let { ${name}_LAYOUT.varHandle() }"
            )
        } else {
            builder.appendLine(
                "private val ${name}_SEGMENT: MemorySegment by lazy { $varLookupExpr.find(\"$lookupName\").orElseThrow() }"
            )
            builder.appendLine("private val ${name}_VH: VarHandle by lazy { ${name}_LAYOUT.varHandle() }")
            builder.appendLine()
        }

        // Property (getter/setter)
        builder.appendLine("var ${name}: ${type}")
        builder.indent()
        if (toplevel.isInitMethod) {
            builder.appendLine("get() {")
            builder.indent()
            builder.appendLine("check(_initialized) { \"Win32 $name accessed before init()\" }")
            builder.appendLine("val _seg = ${name}_SEGMENT ?: return ${returnDefault(type)}")
            builder.appendLine("return ${name}_VH!!.get(_seg) as ${type}")
            builder.unindent()
            builder.appendLine("}")
            builder.appendLine("set(value) {")
            builder.indent()
            builder.appendLine("check(_initialized) { \"Win32 $name accessed before init()\" }")
            builder.appendLine("val _seg = ${name}_SEGMENT ?: return")
            builder.appendLine("${name}_VH!!.set(_seg, value)")
            builder.unindent()
            builder.appendLine("}")
        } else {
            builder.appendLine("get() = ${name}_VH.get(${name}_SEGMENT) as ${type}")
            builder.appendLine("set(value) = ${name}_VH.set(${name}_SEGMENT, value)")
        }
        builder.unindent()
        builder.appendLine()
    }

    /**
     * True when a global variable's type is an aggregate (struct/union/array) rather than a
     * scalar. Typedef / qualifier indirections are unwrapped; pointers stay scalar (a pointer
     * global is a plain ADDRESS value). Aggregate globals are exposed as their address segment
     * because a scalar VarHandle cannot get/set a whole record.
     */
    private fun isAggregateGlobal(type: Type): Boolean = when (type) {
        is Type.Declared -> type.tree().kind().let {
            it == Declaration.Scoped.Kind.STRUCT || it == Declaration.Scoped.Kind.UNION
        }
        is Type.Array -> true
        is Type.Delegated -> when (type.kind()) {
            Type.Delegated.Kind.TYPEDEF,
            Type.Delegated.Kind.VOLATILE,
            Type.Delegated.Kind.ATOMIC -> isAggregateGlobal(type.type())
            else -> false
        }
        else -> false
    }

    fun visitConstant(decl: Declaration.Constant) {
        val name = toplevel.javaName(decl.name())
        val value = decl.value()

        // Skip constants whose values are not valid Kotlin literals
        // (e.g. unresolvable macros that produce values like "$DARWIN_EXTSN")
        val valueStr = value.toString()
        if (valueStr.contains('$') || valueStr.contains(' ') ||
            (!isValidKotlinLiteral(value) && valueStr.any { it.isLetter() && it != 'L' && it != 'f' && it != 'F' })) {
            // Emit as comment only
            builder.appendLine("// Skipped constant ${decl.name()}: value '$valueStr' is not a valid Kotlin literal")
            builder.appendLine()
            return
        }

        val enumDecl = KotlinEnumSupport.resolveEnum(decl.type())
        val generatedEnumName = enumDecl?.let(toplevel::generatedEnumKotlinName)
        val type = generatedEnumName ?: toplevel.mapType(decl.type())

        // Skip string-valued constants when the type is MemorySegment (e.g. char* macros):
        // a Kotlin String literal cannot be assigned to a MemorySegment at compile time.
        if (value is String && type == "MemorySegment") {
            builder.appendLine("// Skipped constant ${decl.name()}: String value cannot be represented as MemorySegment")
            builder.appendLine()
            return
        }

        // Skip numeric-valued constants when the type is MemorySegment (e.g. function-pointer
        // or opaque-pointer macros): a numeric literal cannot produce a MemorySegment.
        if (type == "MemorySegment" && (value is Long || value is Int)) {
            builder.appendLine("// Skipped constant ${decl.name()}: numeric value cannot be represented as MemorySegment")
            builder.appendLine()
            return
        }

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : #define ${decl.name()} ${value}")
        builder.appendLine(" */")

        // Format the value as a valid Kotlin literal, fixing type mismatches with casts
        val kotlinValue = constantKotlinLiteral(
            value,
            valueStr,
            type,
            enumDecl.takeIf { generatedEnumName != null },
        )

        // Constant
        builder.appendLine("fun ${name}(): ${type} = $kotlinValue")
        builder.appendLine()
    }

    private fun constantKotlinLiteral(
        value: Any,
        valueStr: String,
        type: String,
        enumDecl: Declaration.Scoped?,
    ): String {
        val enumValue = when (value) {
            is Long -> value
            is Int -> value.toLong()
            else -> null
        }
        if (enumDecl != null && enumValue != null) {
            val literal = if (enumValue == Long.MIN_VALUE) "Long.MIN_VALUE" else "${enumValue}L"
            return if (KotlinEnumSupport.isOptionsStyle(enumDecl) ||
                toplevel.isObjCSurfaceEnum(enumDecl)) {
                "$type($literal)"
            } else {
                "$type.fromValue($literal)"
            }
        }
        return when (value) {
            is String -> "\"${value}\""
            is Long -> if (type == "Int") {
                if (value == Long.MIN_VALUE) "Int.MIN_VALUE" else "($valueStr).toInt()"
            } else {
                if (value == Long.MIN_VALUE) "Long.MIN_VALUE" else valueStr
            }
            is Float -> if (type == "Double") "(${valueStr}f).toDouble()" else "${valueStr}f"
            is Double -> if (type == "Float") "($valueStr).toFloat()" else valueStr
            else -> valueStr
        }
    }

    private fun isValidKotlinLiteral(value: Any): Boolean = when (value) {
        is Int, is Long, is Float, is Double -> true
        is String -> true
        else -> false
    }

    // --- Utilities (call Java LayoutUtils) ---
    fun layoutString(type: Type): String = toplevel.layoutString(type)

    fun functionDescriptorString(decl: Declaration.Function): String =
        toplevel.functionDescriptorString(decl.type(), variadicArgs[decl.name()] ?: 0)

    /**
     * Returns the default (null-safe) return value for a given type,
     * used when the function handle is null (non-Windows platform).
     */
    private fun returnDefault(type: String): String = when {
        type == "Unit" || type == "Void" -> ""
        type == "Boolean" -> "false"
        type == "Long" -> "0L"
        type == "Float" -> "0f"
        type == "Double" -> "0.0"
        type == "Char" -> "'\\u0000'"
        type == "Int" || type == "Short" || type == "Byte" -> "0"
        type == "MemorySegment" -> "MemorySegment.NULL"
        else -> "0"
    }

    private fun paramString(decl: Declaration.Function, prependAllocator: Boolean = false): String {
        val fixedCount = decl.type().argumentTypes().size
        val variadicCount = if (decl.type().varargs()) variadicArgs[decl.name()] ?: 0 else 0
        val totalArgs = fixedCount + variadicCount
        val args = (0 until totalArgs).map { i ->
            if (i < fixedCount) {
                "arg${i}: ${toplevel.mapType(decl.type().argumentTypes()[i])}"
            } else {
                "arg${i}: MemorySegment"
            }
        }
        return if (prependAllocator) {
            listOf("allocator: SegmentAllocator") + args
        } else {
            args
        }.joinToString(", ")
    }
}
