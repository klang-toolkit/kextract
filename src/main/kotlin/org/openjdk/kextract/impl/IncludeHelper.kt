/*
 * Copyright (c) 2021, 2026, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.kextract.impl

import org.openjdk.kextract.Declaration
import java.io.IOException
import java.io.UncheckedIOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.Comparator
import java.util.EnumMap
import java.util.TreeMap
import java.util.TreeSet

class IncludeHelper {

    enum class IncludeKind {
        CONSTANT, VAR, FUNCTION, TYPEDEF, STRUCT, UNION;

        fun optionName(): String = "include-" + name.lowercase()

        companion object {
            fun fromDeclaration(d: Declaration): IncludeKind = when (d) {
                is Declaration.Constant -> CONSTANT
                is Declaration.Variable -> VAR
                is Declaration.Function -> FUNCTION
                is Declaration.Typedef  -> TYPEDEF
                is Declaration.Scoped   -> fromScoped(d)
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
    private val usedDeclarations: MutableSet<Declaration> = HashSet()
    var dumpIncludesFile: String? = null

    fun addSymbol(kind: IncludeKind, symbolName: String) {
        includesSymbolNamesByKind.getOrPut(kind) { HashSet() }.add(symbolName)
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

    private fun checkIncludedAndAddIfNeeded(kind: IncludeKind, declaration: Declaration): Boolean {
        val included = isIncludedInternal(kind, declaration)
        if (included && dumpIncludesFile != null) {
            usedDeclarations.add(declaration)
        }
        return included
    }

    private fun isIncludedInternal(kind: IncludeKind, declaration: Declaration): Boolean {
        if (!isEnabled()) return true
        val names = includesSymbolNamesByKind.getOrPut(kind) { HashSet() }
        return names.contains(declaration.name())
    }

    fun isEnabled(): Boolean = includesSymbolNamesByKind.isNotEmpty()

    fun dumpIncludes() {
        try {
            Files.newBufferedWriter(Path.of(dumpIncludesFile!!), StandardOpenOption.CREATE).use { writer ->
                val declsByPath = usedDeclarations.filter { it.pos().path() != null }
                    .groupingBy { it.pos().path()!! }
                    .foldTo(
                        TreeMap<Path, TreeSet<Declaration>>(Path::compareTo),
                        { _, _ -> TreeSet(Comparator.comparing(Declaration::name)) },
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
                                String.format(
                                    "%-${maxLengthOptionCol}s %s",
                                    "--${kind.optionName()} ${d.name()}",
                                    "# header: $path\n"
                                )
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
