/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this code; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.kextract.newimpl

import org.openjdk.kextract.impl.IncludeHelper
import java.util.*

/**
 * Configuration options for kextract (Kotlin version).
 * Immutable data class built via Builder pattern.
 */
class Options private constructor(
    // The args for parsing C
    val clangArgs: List<String>,
    // The list of library names
    val libraries: List<Library>,
    // The symbol lookup kind
    val useSystemLoadLibrary: Boolean,
    // target package
    val targetPackage: String,
    // output directory
    val outputDir: String,
    // name of the shared class
    val sharedClassName: String?,
    // Include helper
    val includeHelper: IncludeHelper
) {

    /**
     * A data class describing a shared library.
     */
    data class Library(
        val libSpec: String,
        val specKind: SpecKind
    ) {
        enum class SpecKind {
            NAME, PATH
        }

        companion object {
            @JvmStatic
            fun parse(optionString: String): Library {
                val specKind = if (optionString.startsWith(":")) SpecKind.PATH else SpecKind.NAME
                return if (specKind == SpecKind.PATH) {
                    if (optionString.length == 1) throw IllegalArgumentException("Empty library specifier")
                    Library(optionString.substring(1), specKind)
                } else {
                    Library(optionString, specKind)
                }
            }

            @JvmStatic
            fun toQuotedName(lib: Library): String = lib.libSpec.replace("\\", "\\\\")
        }
    }

    /**
     * Builder for Options.
     */
    class Builder {
        private val clangArgs: MutableList<String> = mutableListOf()
        private val libraries: MutableList<Library> = mutableListOf()
        private var useSystemLoadLibrary: Boolean = false
        private var targetPackage: String = ""
        private var outputDir: String = "."
        private var sharedClassName: String? = null
        private val includeHelper: IncludeHelper = IncludeHelper()
        private var built: Boolean = false

        fun addClangArg(arg: String): Builder = apply { clangArgs.add(arg) }
        fun addLibrary(library: Library): Builder = apply { libraries.add(library) }
        fun setUseSystemLoadLibrary(value: Boolean): Builder = apply { useSystemLoadLibrary = value }
        fun setOutputDir(value: String): Builder = apply { outputDir = value }
        fun setTargetPackage(value: String): Builder = apply { targetPackage = value }
        fun setDumpIncludeFile(value: String): Builder = apply { includeHelper.dumpIncludesFile = value }
        fun setSharedClassName(value: String?): Builder = apply { sharedClassName = value }

        fun addIncludeSymbol(kind: IncludeHelper.IncludeKind, symbolName: String): Builder = apply {
            includeHelper.addSymbol(kind, symbolName)
        }

        fun build(): Options {
            check(!built) { "Builder can only be used once" }
            built = true
            return Options(
                clangArgs = Collections.unmodifiableList(clangArgs),
                libraries = Collections.unmodifiableList(libraries),
                useSystemLoadLibrary = useSystemLoadLibrary,
                targetPackage = targetPackage,
                outputDir = outputDir,
                sharedClassName = sharedClassName,
                includeHelper = includeHelper
            )
        }
    }

    companion object {
        @JvmStatic
        fun builder(): Builder = Builder()
    }
}
