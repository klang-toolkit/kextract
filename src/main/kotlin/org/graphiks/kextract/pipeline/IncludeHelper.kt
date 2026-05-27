package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import java.io.IOException
import java.io.UncheckedIOException
import java.nio.file.Path
import kotlin.io.path.bufferedWriter
import java.util.EnumMap
import java.util.TreeMap
import java.util.TreeSet

class IncludeHelper {

    enum class IncludeKind {
        CONSTANT, VAR, FUNCTION, TYPEDEF, STRUCT, UNION,
        OBJC_CLASS, OBJC_PROTOCOL, OBJC_CATEGORY;

        fun optionName(): String = "include-" + name.lowercase().replace('_', '-')

        companion object {
            fun fromDeclaration(d: Declaration): IncludeKind = when (d) {
                is Declaration.Constant    -> CONSTANT
                is Declaration.Variable    -> VAR
                is Declaration.Function    -> FUNCTION
                is Declaration.Typedef     -> TYPEDEF
                is Declaration.ObjCClass   -> OBJC_CLASS
                is Declaration.ObjCProtocol -> OBJC_PROTOCOL
                is Declaration.ObjCCategory -> OBJC_CATEGORY
                is Declaration.Scoped      -> fromScoped(d)
                else -> throw IllegalStateException("Cannot get here!")
            }

            fun fromScoped(scoped: Declaration.Scoped): IncludeKind = when (scoped.kind()) {
                Declaration.Scoped.Kind.STRUCT -> STRUCT
                Declaration.Scoped.Kind.UNION  -> UNION
                else -> throw IllegalStateException("Cannot get here!")
            }
        }
    }

    private val includesSymbolNamesByKind: EnumMap<IncludeKind, MutableSet<String>> =
        EnumMap(IncludeKind::class.java)
    private val usedDeclarations: MutableSet<Declaration> = mutableSetOf()
    var dumpIncludesFile: String? = null

    fun addSymbol(kind: IncludeKind, symbolName: String) {
        includesSymbolNamesByKind.getOrPut(kind) { mutableSetOf() }.add(symbolName)
    }

    fun isIncludedAsTypedef(name: String): Boolean {
        if (!isEnabled()) return true
        val names = includesSymbolNamesByKind[IncludeKind.TYPEDEF] ?: return false
        return names.contains(name)
    }

    fun isIncluded(variable: Declaration.Variable): Boolean =
        checkIncludedAndAddIfNeeded(IncludeKind.VAR, variable)

    fun isIncluded(function: Declaration.Function): Boolean =
        checkIncludedAndAddIfNeeded(IncludeKind.FUNCTION, function)

    fun isIncluded(constant: Declaration.Constant): Boolean =
        checkIncludedAndAddIfNeeded(IncludeKind.CONSTANT, constant)

    fun isIncluded(typedef: Declaration.Typedef): Boolean =
        checkIncludedAndAddIfNeeded(IncludeKind.TYPEDEF, typedef)

    fun isIncluded(scoped: Declaration.Scoped): Boolean =
        checkIncludedAndAddIfNeeded(IncludeKind.fromScoped(scoped), scoped)

    fun isIncluded(objcClass: Declaration.ObjCClass): Boolean =
        checkIncludedAndAddIfNeeded(IncludeKind.OBJC_CLASS, objcClass)

    fun isIncluded(objcProtocol: Declaration.ObjCProtocol): Boolean =
        checkIncludedAndAddIfNeeded(IncludeKind.OBJC_PROTOCOL, objcProtocol)

    fun isIncluded(objcCategory: Declaration.ObjCCategory): Boolean =
        checkIncludedAndAddIfNeeded(IncludeKind.OBJC_CATEGORY, objcCategory)

    private fun checkIncludedAndAddIfNeeded(kind: IncludeKind, declaration: Declaration): Boolean {
        val included = isIncludedInternal(kind, declaration)
        if (included && dumpIncludesFile != null) {
            usedDeclarations.add(declaration)
        }
        return included
    }

    private fun isIncludedInternal(kind: IncludeKind, declaration: Declaration): Boolean {
        if (!isEnabled()) return true
        val names = includesSymbolNamesByKind[kind] ?: return false
        return names.contains(declaration.name())
    }

    fun isEnabled(): Boolean = includesSymbolNamesByKind.isNotEmpty()

    fun dumpIncludes() {
        try {
            Path.of(dumpIncludesFile!!).bufferedWriter().use { writer ->
                val declsByPath = usedDeclarations.filter { it.pos().path != null }
                    .groupingBy { it.pos().path!! }
                    .foldTo(
                        TreeMap<Path, TreeSet<Declaration>>(Path::compareTo),
                        { _, _ -> TreeSet(compareBy { it.name() }) },
                        { _, acc, d -> acc.also { it.add(d) } }
                    )
                var lineSep = ""
                for ((path, decls) in declsByPath) {
                    writer.append(lineSep)
                    writer.append("#### Extracted from: $path\n\n")
                    val declsByKind = decls.groupBy { IncludeKind.fromDeclaration(it) }
                    val maxLengthOptionCol = decls.maxOf { it.name().length } +
                        2 + IncludeKind.FUNCTION.optionName().length + 1
                    for ((kind, kindDecls) in declsByKind) {
                        for (d in kindDecls) {
                            writer.append(
                                "--${kind.optionName()} ${d.name()}".padEnd(maxLengthOptionCol) + " # header: $path\n"
                            )
                        }
                    }
                    lineSep = "\n"
                }
            }
        } catch (e: IOException) {
            throw UncheckedIOException(e)
        }
    }
}
